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
package com.avaloq.tools.ddk.xtext.expression.generator;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.generator.GenModelAccess;

import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Preconditions;


/**
 * Utility class for querying GenModels.
 */
public final class GenModelUtil2 {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(GenModelUtil2.class);

  /**
   * Private Constructor (singleton).
   */
  private GenModelUtil2() {
    // empty constructor
  }

  /**
   * Returns the qualified package interface name for the given epackage (model).
   *
   * @param ePackage
   *          the model
   * @return the package interface name
   */
  public static String qualifiedPackageInterfaceName(final EPackage ePackage) {
    return ePackage.getClass() == EPackageImpl.class ? findGenPackage(ePackage).getQualifiedPackageInterfaceName()
        : ePackage.getClass().getInterfaces()[0].getName();
  }

  /**
   * Formats a name by parsing it into words separated by underscores and/or mixed-casing and then
   * recombining them using the specified separator.
   * {@see CodeGenUtil#format(String, char, String, boolean, boolean)
   *
   * @param name
   *          the name to format
   * @return the formatted name
   */
  public static String format(final String name) {
    return CodeGenUtil.format(name, '_', null, false, false);
  }

  /**
   * Returns the genmodel for the given model element.
   *
   * @param eModelElement
   *          the model element
   * @return the genmodel
   */
  public static GenModel findGenModel(final EModelElement eModelElement) {
    ResourceSet resourceSet = eModelElement.eResource() != null && eModelElement.eResource().getResourceSet() != null
        ? eModelElement.eResource().getResourceSet()
        : new ResourceSetImpl();
    return findGenModel(eModelElement, resourceSet);
  }

  /**
   * Finds the GenPackage for a given EModelElement and ResourceSet. If the EPackage of given model element
   * is the EcorePackage.eINSTANCE, <code>null</code> is returned.
   *
   * @param eModelElement
   *          the e model element
   * @param resourceSet
   *          the resource set
   * @return the GenModel or <code>null</code>
   */
  public static GenModel findGenModel(final EModelElement eModelElement, final ResourceSet resourceSet) {
    Preconditions.checkNotNull(eModelElement);
    EPackage ePackage = EObjectUtil.eContainer(eModelElement, EPackage.class);
    if (ePackage == EcorePackage.eINSTANCE) {
      return null;
    }

    try {
      final Resource res = getGenModelResource(eModelElement);
      if (res != null) {
        Object result = res.getContents().get(0);
        if (result instanceof GenModel) {
          return (GenModel) result;
        }
      }
    } catch (final IllegalStateException e) {
      // empty
    }

    try {
      GenPackage genPackage = GenModelAccess.getGenPackage(ePackage, resourceSet);
      GenModel result = genPackage.getGenModel();
      if (result != null) {
        return result;
      }
      // CHECKSTYLE:CHECK-OFF IllegalCatch
    } catch (RuntimeException e) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      LOGGER.error(NLS.bind("Exception in findGenModel ({0})", eModelElement), e);
    }

    return null;
  }

  /**
   * Returns the genpackage for the given epackage.
   *
   * @param ePackage
   *          the model
   * @return the genpackage
   */
  public static GenPackage findGenPackage(final EPackage ePackage) {
    Preconditions.checkNotNull(ePackage);
    final GenModel genModel = findGenModel(ePackage);
    return genModel != null ? genModel.findGenPackage(ePackage) : null;
  }

  /**
   * Finds the GenPackage for a given EPackage and ResourceSet. If the GenModel is <code>null</code>, <code>null</code> is returned.
   *
   * @param ePackage
   *          the e package
   * @param resourceSet
   *          the resource set
   * @return the GenPackage or <code>null</code> if the GenModel is <code>null</code>
   */
  public static GenPackage findGenPackage(final EPackage ePackage, final ResourceSet resourceSet) {
    Preconditions.checkNotNull(ePackage);
    final GenModel genModel = findGenModel(ePackage, resourceSet);
    return genModel != null ? genModel.findGenPackage(ePackage) : null;
  }

  /**
   * Returns the Java package for the edit plug-in.
   *
   * @param ePackage
   *          the model
   * @return the edit plugin package name or null
   */
  public static String editPluginPackageName(final EPackage ePackage) {
    Preconditions.checkNotNull(ePackage);
    final GenModel genModel = findGenModel(ePackage);
    return genModel != null ? genModel.getEditPluginPackageName() : null;
  }

  /**
   * Returns the genclass for the given eclass.
   *
   * @param eClass
   *          the eclass
   * @return the genclass
   */
  public static GenClass findGenClass(final EClass eClass) {
    Preconditions.checkNotNull(eClass);
    final GenModel genModel = findGenModel(eClass);
    return genModel != null ? (GenClass) genModel.findGenClassifier(eClass) : null;
  }

  /**
   * Returns the name for the given eclass instance.
   *
   * @param eClass
   *          the eclass
   * @return the class name as a string
   */
  public static String getInstanceClassName(final EClass eClass) {
    return eClass.getInstanceClassName() != null ? eClass.getInstanceClassName()
        : (findGenClass(eClass) != null ? findGenClass(eClass).getQualifiedInterfaceName() : eClass.getName());
  }

  /**
   * Returns the gendatatype for the given edatatype.
   *
   * @param eDataType
   *          the edatatype
   * @return the gendatatype
   */
  public static GenDataType findGenDataType(final EDataType eDataType) {
    Preconditions.checkNotNull(eDataType);
    final GenModel genModel = findGenModel(eDataType);
    return genModel != null ? (GenDataType) genModel.findGenClassifier(eDataType) : null;
  }

  /**
   * Returns the name for the given eclass instance.
   *
   * @param eDataType
   *          the data type
   * @return the class name as a string
   */
  public static String getInstanceClassName(final EDataType eDataType) {
    return eDataType.getInstanceClassName() != null ? eDataType.getInstanceClassName()
        : (findGenDataType(eDataType) != null ? findGenDataType(eDataType).getQualifiedInstanceClassName() : eDataType.getName());
  }

  /**
   * Returns the genmodel resource for the given model element.
   *
   * @param eModelElement
   *          the model element
   * @return the genmodel resource
   */
  private static Resource getGenModelResource(final EModelElement eModelElement) {
    final Resource res = eModelElement.eResource();
    ResourceSet resourceSet = res.getResourceSet();
    if (resourceSet == null) {
      resourceSet = new ResourceSetImpl();
      resourceSet.getResources().add(res);
    }

    URI uri = res.getURI();

    final URIConverter uriConverter = URIConverter.INSTANCE;
    uri = uriConverter.normalize(uri);
    uri = uri.trimFileExtension().appendFileExtension("genmodel"); //$NON-NLS-1$
    uri = uriConverter.normalize(uri);
    if (uri.scheme().equals("http")/* toString().equals(EcorePackage.eNS_URI) */) { //$NON-NLS-1$
      return null; // optimization, because we are not interested in the extension for the Ecore model.
                   // otherwise getResource will go on the internet to load the model and we loose 20 seconds on each call!
    }

    if (uriConverter.exists(uri, null)) {
      Resource genModelResource = null;
      try {
        genModelResource = resourceSet.getResource(uri, true);
      } catch (final WrappedException we) {
        throw new IllegalStateException("could not retrieve resource for URI " + uri //$NON-NLS-1$
            + " please add URI maps for all relevant Ecore models to the workflow.", we); //$NON-NLS-1$
      }
      return genModelResource;
    } else {
      return null;
    }
  }

}
