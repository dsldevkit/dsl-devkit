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
package com.avaloq.tools.ddk.xtext.scope;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;

import com.avaloq.tools.ddk.xtext.scope.scope.Import;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;


/**
 * Simple utility function for the scope language.
 */
public final class ScopeUtil {

  /**
   * No public creator.
   */
  private ScopeUtil() {
    // Do not allow instantiation.
  }

  /**
   * Return the root object of a scope language resource.
   *
   * @param obj
   *          Some object in the resource
   * @return The containing root object, which is a BuildDescription.
   */
  public static ScopeModel getRoot(final EObject obj) {
    final EObject root = EcoreUtil.getRootContainer(obj);
    if (root instanceof ScopeModel) {
      return (ScopeModel) root;
    }
    return null;
  }

  /**
   * Get the name to use for an Import. This is the defined alias, or the EPackages namespace prefix, if there is no alias.
   *
   * @param importedPackage
   *          The Import.
   * @return The name, or null if none found.
   */
  public static String getPackageName(final Import importedPackage) {
    final String result = importedPackage.getName();
    if (result != null) {
      return result;
    }
    final EPackage p = importedPackage.getPackage();
    if (p != null) {
      return p.getNsPrefix();
    }
    return null;
  }

  /**
   * Computes a signature string representation which must be unique for the given rule.
   *
   * @param rule
   *          rule to calculate signature for
   * @return string signature
   */
  @SuppressWarnings("nls")
  public static String getSignature(final ScopeRule rule) { // NOPMD NPathComplexity
    // CHECKSTYLE:OFF MagicNumber
    StringBuffer result = new StringBuffer(60);
    // CHECKSTYLE:ON
    final ScopeDefinition def = EcoreUtil2.getContainerOfType(rule, ScopeDefinition.class);
    result.append(def.getName()).append('_');
    final EClass type = def.getReference() != null ? def.getReference().getEReferenceType() : def.getTargetType();
    final EReference ref = def.getReference();

    if (ref != null) {
      result.append(ref.getEContainingClass().getEPackage().getName()).append('_').append(ref.getEContainingClass().getName()).append('_').append(ref.getName()).append("_EReference");
    } else {
      result.append(type.getEPackage().getName()).append('_').append(type.getName()).append("_EClass");
    }
    result.append('_');
    if (rule.getContext().isGlobal()) {
      result.append("org.eclipse.emf.ecore.resource.Resource");
    } else {
      result.append(rule.getContext().getContextType().getEPackage().getName()).append('_').append(rule.getContext().getContextType().getName());
    }
    return result.toString();
  }
}
