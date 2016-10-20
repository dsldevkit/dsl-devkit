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
package com.avaloq.tools.ddk.xtext.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Utility methods for EClasses.
 */
public final class EClasses {

  private EClasses() {
    // No public constructor
  }

  /**
   * Find the {@link EClass} for a given {@link URI}.
   *
   * @param uri
   *          of the EClass to find.
   * @return the EClass, if found, or {@code null} otherwise.
   */
  public static EClass getEClass(final URI uri) {
    EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri.trimFragment().toString());
    if (ePackage != null) {
      if (ePackage.eResource() != null) {
        return (EClass) ePackage.eResource().getEObject(uri.fragment());
      }
      for (EClassifier eClassifier : ePackage.getEClassifiers()) {
        if (EcoreUtil.getURI(eClassifier).equals(uri) && eClassifier instanceof EClass) {
          return (EClass) eClassifier;
        }
      }
    }
    return null;
  }

  /**
   * Find the {@link EReference} for a given {@link URI}.
   *
   * @param uri
   *          of the EReference to find.
   * @return the EReference, if found, or {@code null} otherwise.
   */
  public static EReference getEReference(final URI uri) {
    EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri.trimFragment().toString());
    if (ePackage == null) {
      return null;
    }
    if (ePackage.eResource() != null) {
      EObject eObject = ePackage.eResource().getEObject(uri.fragment());
      return eObject instanceof EReference ? (EReference) eObject : null; // NOPMD Null assignment
    }
    for (EClassifier eClassifier : ePackage.getEClassifiers()) {
      if (eClassifier instanceof EClass) {
        for (EReference ref : ((EClass) eClassifier).getEReferences()) {
          if (EcoreUtil.getURI(ref).equals(uri)) {
            return ref;
          }
        }
      }
    }
    return null;
  }

  /**
   * Find instantiable and subtypes of a given type.
   *
   * @param eType
   *          of which to find subtypes
   * @param candidates
   *          set of candidates to check
   * @return the subtypes
   */
  public static List<EClass> findInstantiableCompatibleTypes(final EClass eType, final Collection<EClass> candidates) {
    Set<EClass> result = Sets.newHashSet();
    if (isInstantiatableSubType(eType, eType)) {
      result.add(eType);
    }
    for (EClass candidate : candidates) {
      if (isInstantiatableSubType(candidate, eType)) {
        result.add(candidate);
      }
    }
    return Lists.newArrayList(result);
  }

  /**
   * Determine whether a type is an instantiable subtype of another type.
   *
   * @param c
   *          EClass to check.
   * @param superType
   *          of which c should be a subtype.
   * @return {@code true}, if c is instantiable and assignment-compatible with {@code superType}.
   */
  private static boolean isInstantiatableSubType(final EClass c, final EClass superType) {
    return !c.isAbstract() && !c.isInterface() && EcoreUtil2.isAssignableFrom(superType, c);
  }

}
