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
package com.avaloq.tools.ddk.xtext.format.naming;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.XtextSwitch;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * This class provides name functions for IEObjectDescriptions in scope functions.
 */
public class FormatScopeNameProvider {

  private static final Function<EObject, QualifiedName> BASE_NAME_FUNCTION = new Function<EObject, QualifiedName>() {
    private final XtextSwitch<String> nameSwitch = new XtextSwitch<String>() {

      @Override
      public String caseGrammar(final org.eclipse.xtext.Grammar object) {
        return GrammarUtil.getSimpleName(object);
      };

      @Override
      public String caseAbstractRule(final org.eclipse.xtext.AbstractRule object) {
        return object.getName();
      };

      @Override
      public String caseKeyword(final Keyword object) {
        return '"' + object.getValue() + '"';
      }

      @Override
      public String caseAssignment(final Assignment object) {
        return object.getFeature();
      }

      @Override
      public String caseRuleCall(final RuleCall object) {
        return object.getRule().getName();
      }

      @Override
      public String caseCompoundElement(final org.eclipse.xtext.CompoundElement object) {
        return "";
      };
    };

    @Override
    public QualifiedName apply(final EObject arg0) {
      return QualifiedName.create(nameSwitch.doSwitch(arg0));
    }
  };

  public Function<EObject, QualifiedName> getBaseNameFunction() {
    return BASE_NAME_FUNCTION;
  }

  /**
   * Returns a name function which returns an element's (1-based) index in a list as name.
   *
   * @param all
   *          List of all candidates
   * @return name function for elements in all
   */
  public Function<EObject, QualifiedName> getIndexNameFunction(final List<? extends EObject> all) {
    return new Function<EObject, QualifiedName>() {
      @Override
      public QualifiedName apply(final EObject from) {
        return QualifiedName.create(String.valueOf(all.indexOf(from) + 1));
      }
    };
  }

  /**
   * Returns a name function which returns an element's name based on its base name ({@link #getBaseNameFunction()})
   * and its index in a list of peers.
   *
   * @param all
   *          List of all candidates
   * @return name function for elements in all
   */
  public Function<EObject, QualifiedName> getIndexParameterNameFunction(final List<? extends EObject> all) {
    return getIndexParameterNameFunction(getBaseNameFunction(), all);
  }

  /**
   * Returns a name function which returns an element's name based on baseNameFunction
   * and its index in a list of peers.
   *
   * @param all
   *          List of all candidates
   * @param baseNameFunction
   *          name function to use for name prefix
   * @return name function for elements in all
   */
  public Function<EObject, QualifiedName> getIndexParameterNameFunction(final Function<EObject, QualifiedName> baseNameFunction, final List<? extends EObject> all) {
    return new Function<EObject, QualifiedName>() {
      @Override
      public QualifiedName apply(final EObject from) {
        final QualifiedName baseName = baseNameFunction.apply(from);
        Iterable<? extends EObject> nameTwins = Iterables.filter(all, new Predicate<EObject>() {
          @Override
          public boolean apply(final EObject input) {
            return baseName.equals(baseNameFunction.apply(input));
          }
        });
        if (Iterables.size(nameTwins) < 2) {
          return baseName;
        } else {
          StringBuilder name = new StringBuilder(baseName.toString());
          name.append('(');
          name.append(getIndexNameFunction(Lists.newArrayList(nameTwins)).apply(from));
          name.append(',');
          name.append(Iterables.size(nameTwins));
          name.append(')');
          return QualifiedName.create(name.toString());
        }
      }
    };
  }

  /**
   * Returns a name function which returns constantName for any input.
   *
   * @param constantName
   *          - the name to return
   * @return name function with constant name
   */
  public Function<EObject, QualifiedName> getConstantNameFunction(final String constantName) {
    return new Function<EObject, QualifiedName>() {
      @Override
      public QualifiedName apply(final EObject from) {
        return QualifiedName.create(constantName);
      }
    };
  }
}
