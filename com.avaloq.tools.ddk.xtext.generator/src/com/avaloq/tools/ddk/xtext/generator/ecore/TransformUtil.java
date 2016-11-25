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
package com.avaloq.tools.ddk.xtext.generator.ecore;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.resource.DerivedStateAwareResource;


/**
 * Support routines for Xtend file TransformUtil.ext.
 */
public class TransformUtil {

  private static final Logger LOG = Logger.getLogger(TransformUtil.class);

  /**
   * Private constructor.
   */
  protected TransformUtil() {
    // Nothing to do (but keep Checkstyle happy.)
  }

  /**
   * Maps Ecore type names to the corresponding Ecore object instance.
   *
   * @param ecoreTypeName
   *          The name of the Ecore primitive type to be mapped to its
   *          instance. Permitted values are EString, EInt, EIntegerObject, EBoolean, EBooleanObject, EChar,
   *          ELong, EShort, EDouble, EDate, EFloat.
   *          Note that the use of EBooleanObject is discouraged, since we want to avoid 3-states logic.
   * @return the corresponding EDataType instance if permitted ecoreTypeName,
   *         else null
   */
  public static Object mapType(final String ecoreTypeName) {
    if (ecoreTypeName == null) {
      return null;
    }
    final EcorePackage ecorePkg = EcorePackage.eINSTANCE;
    if ("EString".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEString();
    } else if ("EInt".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEInt();
    } else if ("EIntegerObject".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEIntegerObject();
    } else if ("EBoolean".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEBoolean();
    } else if ("EBooleanObject".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEBooleanObject();
    } else if ("EChar".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEChar();
    } else if ("ELong".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getELong();
    } else if ("EShort".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEShort();
    } else if ("EDouble".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEDouble();
    } else if ("EDate".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEDate();
    } else if ("EFloat".equals(ecoreTypeName)) { //$NON-NLS-1$
      return ecorePkg.getEFloat();
    } else {
      return null;
    }
  }

  /**
   * Sets structural features whose type is {@link org.eclipse.emf.ecore.EEnum} unsettable.
   *
   * @param source
   *          the class whose features are inspected and made unsettable if the type is EEnum
   */
  public static void setEEnumsUnsettable(final EClass source) {
    for (final EStructuralFeature feature : source.getEStructuralFeatures()) {
      if (feature.getEType() instanceof EEnum) {
        feature.setUnsettable(true);
      }
    }
  }

  /**
   * Looks up an EPackage visible in the context of a grammar.
   *
   * @param grammar
   *          context grammar
   * @param packageName
   *          package name
   * @return EPackage or null
   */
  public static EPackage lookupEPackage(final Grammar grammar, final String packageName) {
    try {
      for (AbstractMetamodelDeclaration decl : grammar.getMetamodelDeclarations()) {
        if (decl.getEPackage() != null && packageName.equals(decl.getEPackage().getName())) {
          return decl.getEPackage();
        }
      }
      ResourceSet resourceSet = grammar.eResource().getResourceSet();
      loadImplicitMetamodels(resourceSet);
      // With DerivedStateAwareResources, calling getContents() may cause linking, which may load additional resources into the resource set.
      // If that happens, we get a ConcurrentModificationException in this loop if we just do
      //
      // for (Resource res : resourceSet.getResources()) { ... }
      //
      // Try to guard against that by explicitly copying the list. Since there may be other resource types that may do equally crazy things
      // upon getContents(), we do *not* try to guard by only calling getContents() if (!(res instanceof DerivedStateAwareResource)).
      List<Resource> resources = new ArrayList<Resource>(resourceSet.getResources());
      for (Resource res : resources) {
        // For performance reasons, we exclude DerivedStateAwareResources all the same. We *know* that these are Xtext resources,
        // and these normally do not contain EPackages anyway. GrammarResources in particular are DerivedStateAwareResources, as are all ASMD
        // language resources. GrammarResources invoke linking here!
        if (!(res instanceof DerivedStateAwareResource) && !res.getContents().isEmpty() && res.getContents().get(0) instanceof EPackage) {
          EPackage epkg = (EPackage) res.getContents().get(0);
          if (packageName.equals(epkg.getName())) {
            return epkg;
          }
        }
      }
      Registry reg = resourceSet.getPackageRegistry();
      for (String uri : reg.keySet()) {
        EPackage epkg = reg.getEPackage(uri);
        if (packageName.equals(epkg.getName())) {
          return epkg;
        }
      }
      // CHECKSTYLE:OFF
    } catch (Exception e) {
      // CHECKSTYLE:ON
      LOG.error("error looking up package " + packageName, e);
    }
    LOG.info("no package found with name " + packageName);
    return null;
  }

  /**
   * Looks up an EClass visible in the context of a grammar.
   *
   * @param grammar
   *          context grammar
   * @param packageName
   *          package name
   * @param className
   *          class name
   * @return EClass or null
   */
  public static EClass lookupEClass(final Grammar grammar, final String packageName, final String className) {
    EPackage ePackage = lookupEPackage(grammar, packageName);
    if (ePackage != null) {
      EClassifier eClassifier = ePackage.getEClassifier(className);
      if (eClassifier instanceof EClass) {
        return (EClass) eClassifier;
      }
    }
    LOG.info("no class found with name " + packageName + "::" + className);
    return null;
  }

  /**
   * Loads the metamodel, abstracttypemodel, and refactoringtypemodel EPackages into the given resource set.
   *
   * @param resourceSet
   *          resource set
   */
  private static void loadImplicitMetamodels(final ResourceSet resourceSet) {
    // Are platform URIs correct here? What if this is deployed, and the workspace doesn't have these models? Anyway, isn't this ASMD-specific?
    try {
      resourceSet.getResource(URI.createURI("platform:/plugin/com.avaloq.tools.foundation.xtext.core/metamodel/com/avaloq/tools/dsl/common/AbstractTypeModel.ecore"), true);
      resourceSet.getResource(URI.createURI("platform:/plugin/com.avaloq.tools.foundation.xtext.core/metamodel/com/avaloq/tools/dsl/common/MetaModel.ecore"), true);
      resourceSet.getResource(URI.createURI("platform:/plugin/com.avaloq.tools.refactoring.core/model/RefactoringTypeModel.ecore"), true);
      // CHECKSTYLE:OFF Yes, we want to catch any exception here for now.
      // However, it seems to me this is a dependency on non-SDK stuff and must be removed. Use an extension point to register implicit metamodels?
    } catch (Exception ex) {
      // CHECKSTYLE:ON
      LOG.warn("Cannot load (some) implicit type models", ex);
    }
  }
}
