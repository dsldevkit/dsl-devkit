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
package com.avaloq.tools.ddk.xtext.expression.generator.type;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;


/**
 * Local replacement for {@code org.eclipse.xtend.typesystem.emf.EmfRegistryMetaModel}.
 * Resolves types from a set of registered EMF packages.
 */
@SuppressWarnings("nls")
public class EmfRegistryMetaModel {

  /** The namespace delimiter used in qualified type names. */
  public static final String NS_DELIM = "::";

  private final EPackage[] packages;

  /**
   * Creates a meta model with the given packages.
   *
   * @param packages
   *          the EMF packages
   */
  public EmfRegistryMetaModel(final EPackage... packages) {
    this.packages = packages;
  }

  /**
   * Returns all registered packages.
   *
   * @return the packages
   */
  public EPackage[] allPackages() {
    return packages;
  }

  /**
   * Resolves a type by its qualified name.
   *
   * @param name
   *          the qualified type name (e.g. "ecore::EObject")
   * @return the type, or {@code null}
   */
  public XtendType getTypeForName(final String name) {
    if (name == null) {
      return null;
    }
    final String[] frags = name.split(NS_DELIM);
    if (frags.length == 2) {
      return findTypeInPackages(frags[0], frags[1]);
    } else if (frags.length == 1) {
      // Try unqualified name across all packages
      return findTypeBySimpleName(frags[0]);
    }
    return null;
  }

  /**
   * Finds a type in the registered packages by package name and classifier name.
   *
   * @param packageName
   *          the package name
   * @param classifierName
   *          the classifier name
   * @return the type, or {@code null}
   */
  protected XtendType findTypeInPackages(final String packageName, final String classifierName) {
    for (EPackage ePackage : allPackages()) {
      if (packageName.equals(ePackage.getName())) {
        EClassifier classifier = ePackage.getEClassifier(classifierName);
        if (classifier instanceof EClass) {
          return new EClassXtendType((EClass) classifier);
        }
      }
    }
    // Also check the global EMF package registry
    for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
      EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
      if (ePackage != null && packageName.equals(ePackage.getName())) {
        EClassifier classifier = ePackage.getEClassifier(classifierName);
        if (classifier instanceof EClass) {
          return new EClassXtendType((EClass) classifier);
        }
      }
    }
    return null;
  }

  /**
   * Finds a type by simple name across all registered packages.
   *
   * @param simpleName
   *          the simple type name
   * @return the type, or {@code null}
   */
  private XtendType findTypeBySimpleName(final String simpleName) {
    for (EPackage ePackage : allPackages()) {
      EClassifier classifier = ePackage.getEClassifier(simpleName);
      if (classifier instanceof EClass) {
        return new EClassXtendType((EClass) classifier);
      }
    }
    return null;
  }
}
