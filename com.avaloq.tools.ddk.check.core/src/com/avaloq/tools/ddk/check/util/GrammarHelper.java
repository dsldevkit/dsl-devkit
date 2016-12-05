/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleReference;


/**
 * The Class GrammarHelper.
 */
public class GrammarHelper {

  private final Grammar grammar;

  /**
   * Instantiates a new grammar helper.
   * 
   * @param grammar
   *          the grammar
   */
  public GrammarHelper(final Grammar grammar) {
    this.grammar = grammar;
  }

  /**
   * Gets the bundle symbolic name.
   * 
   * @param classe
   *          the class
   * @return the bundle symbolic name
   */
  protected String getBundleSymbolicName(final Class<?> classe) {
    ClassLoader cl = classe.getClassLoader();
    if (cl instanceof BundleReference) {
      Bundle bundle = ((BundleReference) cl).getBundle();
      if (bundle != null) {
        return bundle.getSymbolicName();
      }
    }
    return null;
  }

  /**
   * @return a text representation of the grammar in which the last segment
   *         appears first (like in content assist.)
   */
  public String getLabelName() {
    String name = getGrammar().getName();
    return name.substring(name.lastIndexOf('.') == -1 ? 0 : name.lastIndexOf('.') + 1) + " - " + name;
  }

  /**
   * Gets the Java interface that corresponds to a grammar's top-level grammar rule.
   * 
   * @param metamodelDeclaration
   *          the metamodel declaration
   * @return the first interface corresponding to a grammar's top-level grammar rule
   */
  public Class<?> getFirstInterface(final AbstractMetamodelDeclaration metamodelDeclaration) {
    return getFirstInterface(metamodelDeclaration.getEPackage());
  }

  /**
   * Gets the Java interface that corresponds to a grammar's top-level grammar rule.
   * 
   * @param ePackage
   *          the EPackage for the grammar
   * @return the first interface corresponding to a grammar's top-level grammar rule
   */
  public Class<?> getFirstInterface(final EPackage ePackage) {
    return ePackage.getClass().getInterfaces()[0];
  }

  /**
   * Gets the grammar.
   * 
   * @return the grammar
   */
  public Grammar getGrammar() {
    // no need to cache, the GrammarProvider does it for us...
    return grammar;
  }

  /**
   * Gets the name of the bundles required for this grammar.
   * 
   * @return the required bundle symbolic names
   */
  public Set<String> getRequiredBundleSymbolicNames() {
    Set<String> set = new HashSet<String>();
    for (AbstractMetamodelDeclaration declaration : GrammarUtil.allMetamodelDeclarations(getGrammar())) {
      set.add(getBundleSymbolicName(getFirstInterface(declaration)));
      for (EClassifier classifier : declaration.getEPackage().getEClassifiers()) {
        if (classifier instanceof EClass) {
          for (EClass superType : ((EClass) classifier).getEAllSuperTypes()) {
            set.add(getBundleSymbolicName(getFirstInterface(superType.getEPackage())));
          }
        }
      }
    }
    return set;
  }

  /**
   * Returns the list of metamodel declarations Java packages.
   * 
   * @return the metamodel packages
   */
  public List<String> getMetamodelPackages() {
    ArrayList<String> list = new ArrayList<String>();
    for (AbstractMetamodelDeclaration metamodelDeclaration : getGrammar().getMetamodelDeclarations()) {
      list.add(getFirstInterface(metamodelDeclaration).getPackage().getName());
    }
    return list;
  }

}

