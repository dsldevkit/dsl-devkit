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
package com.avaloq.tools.ddk.xtext.scope.generator;

import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.EcoreUtil2;

import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.avaloq.tools.ddk.xtext.scope.scope.Casing;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;


/**
 * Various utility functions for the scoping generator.
 */
public final class ScopingGeneratorUtil {

  /**
   * Inhibit public instantiation.
   */
  private ScopingGeneratorUtil() {
    // No public constructor
  }

  /**
   * Determine whether a certain scope expression is case sensitive or not.
   *
   * @param expr
   *          The scope expression.
   * @return true, if the expression is case insensitive; false otherwise.
   */
  public static boolean isCaseInsensitive(final NamedScopeExpression expr) {
    Casing casing;
    if (expr.isCaseDef()) {
      casing = expr.getCasing();
    } else {
      NamingSection naming = EcoreUtil2.getContainerOfType(expr, ScopeModel.class).getNaming();
      casing = naming != null ? naming.getCasing() : Casing.SENSITIVE;
    }
    return casing == Casing.INSENSITIVE;
  }

  /**
   * Sort rules according to context type (more specific rules must come first).
   *
   * @param rules
   *          rules to sort
   * @return sorted list of all rules
   */
  public static List<ScopeRule> sortedRules(final Collection<ScopeRule> rules) {
    return EClassComparator.sortedGroups(rules, r -> r.getContext().getContextType());
  }

}
