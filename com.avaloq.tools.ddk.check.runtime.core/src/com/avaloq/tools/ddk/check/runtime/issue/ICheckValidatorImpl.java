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
package com.avaloq.tools.ddk.check.runtime.issue; //TODO rename package

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.ImmutableMap;


/**
 * Defines operations required for validating models.
 */
public interface ICheckValidatorImpl {

  /**
   * Gets the qualified catalog name, consisting of its package name followed by the catalog name,
   * e.g. <code>my.example.DummyCatalog</code>.
   *
   * @return the check catalog's qualified name
   */
  String getQualifiedCatalogName();

  /**
   * Get map of issue code to label.
   *
   * @return Map of issue code to label.
   */
  ImmutableMap<String, String> getIssueCodeToLabelMap(); // NOPMD LooseCoupling

  /**
   * Validate given object of given class. Return {@code true} if the object validates without issues.
   *
   * @param eClass
   *          the e class
   * @param eObject
   *          the e object
   * @param diagnostics
   *          the diagnostics
   * @param context
   *          the context
   * @return {@code true} if the object validates without issues, else {@code false}
   */
  boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context);

  /**
   * Returns whether this validator has checks for objects of the given class, i.e. whether {@link #validate} may do anything for them.
   * {@link com.avaloq.tools.ddk.check.runtime.validation.AbstractCheckValidator AbstractCheckValidator} does not call {@link #validate} for
   * objects of a class for which this returns {@code false}.
   * <p>
   * The default implementation returns {@code true}. Catalogs generated from {@code .check} files return whether the class is a subtype of
   * the type of one of their context variables.
   *
   * @param type
   *          the class of the objects to validate, must not be {@code null}
   * @return {@code false} if {@link #validate} does nothing for objects of the given class
   */
  default boolean hasChecksFor(final Class<?> type) {
    return true;
  }

}
