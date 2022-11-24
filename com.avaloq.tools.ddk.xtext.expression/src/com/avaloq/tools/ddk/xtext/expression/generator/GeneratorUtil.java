/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.expression.generator;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.util.XtextSwitch;

import com.avaloq.tools.ddk.xtext.util.EClasses;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 * These utility methods make of for shortcomings in the xtext generator framework and will hopefully find an adequate replacement
 * inside xtext.
 */
public class GeneratorUtil {

  public static final String ISO_8859_1 = "ISO-8859-1";

  /**
   * Hidden constructor.
   */
  protected GeneratorUtil() {
    // do nothing
  }

  /**
   * Returns a set of all EClasses instantiated by the parser of the given grammar.
   *
   * @param grammar
   *          Xtext grammar
   * @return potentially instantiated EClasses
   */
  public static Set<EClass> allInstantiatedTypes(final Grammar grammar) {
    Set<EClass> result = Sets.newLinkedHashSet();
    collectInstantiatedTypes(grammar, result);
    return result;
  }

  /**
   * Collects instantiated EClasses for a grammar and all included grammar recursively.
   *
   * @param grammar
   *          Xtext grammar
   * @param result
   *          set to add instantiated types to
   */
  private static void collectInstantiatedTypes(final Grammar grammar, final Set<EClass> result) {
    XtextSwitch<Void> xtextSwitch = new XtextSwitch<Void>() {
      @Override
      public Void caseParserRule(final ParserRule object) {
        EClassifier eClassifier = object.getType().getClassifier();
        if (eClassifier instanceof EClass) {
          result.add((EClass) eClassifier);
        }
        return null;
      }

      @Override
      public Void caseAction(final Action object) {
        EClassifier eClassifier = object.getType().getClassifier();
        result.add((EClass) eClassifier);
        return null;
      }
    };
    for (TreeIterator<EObject> it = EcoreUtil.getAllProperContents(grammar, false); it.hasNext();) {
      xtextSwitch.doSwitch(it.next());
    }
    for (Grammar includedGrammar : grammar.getUsedGrammars()) {
      collectInstantiatedTypes(includedGrammar, result);
    }
  }

  /**
   * Creates a mapping from EClass to objects as given by the mapping function. The mapped EClass and all its subtypes (as instantiated by the parser) will be
   * mapped.
   *
   * @param <T>
   *          type of objects
   * @param objects
   *          objects to map
   * @param grammar
   *          Xtext grammar
   * @param mapping
   *          mapping function
   * @return mappings
   */
  public static <T> Map<EClass, T> typeMap(final Iterable<T> objects, final Grammar grammar, final Function<T, EClass> mapping) {
    Set<EClass> allInstantiatedTypes = allInstantiatedTypes(grammar);
    Map<EClass, T> result = Maps.newHashMap();
    for (T obj : objects) {
      EClass eClass = mapping.apply(obj);
      result.put(eClass, obj);
      for (EClass subtype : EClasses.findInstantiableCompatibleTypes(eClass, allInstantiatedTypes)) {
        result.put(subtype, obj);
      }
    }
    return result;
  }

  /**
   * Determines whether instances of one EClass potentially could directly or indirectly {@link EObject#eContents() contain} instances of a given set of other
   * EClasses. All registered subtypes of the given type and other transitively checked types will also be considered.
   *
   * @param eClass
   *          EClass to check if it could be the container
   * @param candidates
   *          other potential contained EClasses
   * @param grammar
   *          Xtext grammar
   * @return true if <code>eClass</code> could be the direct or indirect container of any of the given other EClasses
   */
  public static boolean canContain(final EClass eClass, final Set<EClass> candidates, final Grammar grammar) {
    Set<EClass> visited = Sets.newHashSet();
    return internalCanContain(eClass, candidates, allInstantiatedTypes(grammar), visited);
  }

  /**
   * Internal helper method for {@link #canContain(EClass, Set)}.
   *
   * @param eClass
   *          EClass to check if it could be the container
   * @param candidates
   *          other potential contained EClasses
   * @param instantiatedTypes
   *          all types instantiated by the Xtext grammar
   * @param visited
   *          set of visited EClasses (to avoid endless recursion)
   * @return true if <code>eClass</code> could be the direct or indirect container of any of the given other EClasses
   */
  private static boolean internalCanContain(final EClass eClass, final Set<EClass> candidates, final Set<EClass> instantiatedTypes, final Set<EClass> visited) {
    if (!visited.add(eClass)) {
      return false;
    } else if (EcorePackage.Literals.EOBJECT == eClass) {
      return true;
    }

    // check containment references
    for (EReference containment : eClass.getEAllContainments()) {
      if (!containment.isContainment() || containment.isTransient()) {
        // TODO check if it is always correct to skip transient containments
        continue;
      }

      EClass containmentType = containment.getEReferenceType();
      if (candidates.contains(containmentType)) {
        return true;
      } else {
        for (EClass candidate : candidates) {
          if (containmentType.isSuperTypeOf(candidate)) {
            return true;
          }
        }
        if (internalCanContain(containmentType, candidates, instantiatedTypes, visited)) {
          return true;
        }
      }
    }

    // check subtypes
    for (EClass subtype : EClasses.findInstantiableCompatibleTypes(eClass, instantiatedTypes)) {
      if (internalCanContain(subtype, candidates, instantiatedTypes, visited)) {
        return true;
      }
    }

    return false;
  }

}
