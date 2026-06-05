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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.EcoreUtil2;

import com.google.common.base.Preconditions;


/**
 * Utility class for querying GenModels.
 */
public final class GenModelUtil2 {

  /** File extension of EMF generator models. */
  private static final String GENMODEL_EXTENSION = "genmodel"; //$NON-NLS-1$

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(GenModelUtil2.class);

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
   * {@link CodeGenUtil#format(String, char, String, boolean, boolean)}
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
    EPackage ePackage = EcoreUtil2.getContainerOfType(eModelElement, EPackage.class);
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
      GenPackage genPackage = findGenPackageInResourceSet(ePackage, resourceSet);
      if (genPackage != null) {
        return genPackage.getGenModel();
      }
      // CHECKSTYLE:CHECK-OFF IllegalCatch
    } catch (RuntimeException e) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
      LOGGER.error("Exception in findGenModel ({})", eModelElement, e); //$NON-NLS-1$
    }

    return null;
  }

  /**
   * Finds the GenPackage for the given EPackage in the given resource set.
   * <p>
   * Contrary to {@link org.eclipse.xtext.xtext.generator.util.GenModelUtil2#getGenPackage(EPackage, ResourceSet)} this
   * implementation is safe to call while the resource set is still being populated. That implementation calls
   * {@link Resource#getContents()} on <em>every</em> resource of the set; doing so installs the derived state of the
   * visited resources (running, for instance, the JVM model inferrer of another scope or export model), which loads
   * further resources into the very list being iterated and thus raises a
   * {@link java.util.ConcurrentModificationException}. Here only genmodel resources - which carry no derived state -
   * are inspected, and the iteration runs over copies of the resource list until all of them have been visited.
   *
   * @param ePackage
   *          the package to find the GenPackage for, must not be {@code null}
   * @param resourceSet
   *          the resource set to search, must not be {@code null}
   * @return the GenPackage, or {@code null} if none could be found
   */
  private static GenPackage findGenPackageInResourceSet(final EPackage ePackage, final ResourceSet resourceSet) {
    final String nsURI = ePackage.getNsURI();
    if (nsURI == null) {
      return null;
    }
    final URI genModelURI = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false).get(nsURI);
    if (genModelURI != null) {
      return findGenPackageInGenModelResource(resourceSet.getResource(genModelURI, true), ePackage);
    }
    // Resolving a genmodel loads the genmodels of its usedGenPackages, so further genmodel resources may appear in
    // the resource set while the scan runs. Repeat over a fresh copy of the resource list until every genmodel has
    // been visited; the live list must not be iterated directly as it grows during the scan.
    final Set<Resource> visited = new HashSet<>();
    boolean foundUnvisitedGenModel = true;
    while (foundUnvisitedGenModel) {
      foundUnvisitedGenModel = false;
      for (final Resource resource : new ArrayList<>(resourceSet.getResources())) {
        if (isGenModelResource(resource) && visited.add(resource)) {
          foundUnvisitedGenModel = true;
          final GenPackage genPackage = findGenPackageInGenModelResource(resource, ePackage);
          if (genPackage != null) {
            return genPackage;
          }
        }
      }
    }
    return null;
  }

  /**
   * Returns whether the given resource holds an EMF generator model.
   *
   * @param resource
   *          the resource to test, must not be {@code null}
   * @return {@code true} if the resource is a genmodel resource
   */
  private static boolean isGenModelResource(final Resource resource) {
    return resource.getURI() != null && GENMODEL_EXTENSION.equals(resource.getURI().fileExtension());
  }

  /**
   * Finds the GenPackage for the given EPackage among the top level contents of a genmodel resource. Only the first
   * level is inspected, as GenModels are always root elements.
   *
   * @param genModelResource
   *          the genmodel resource, may be {@code null}
   * @param ePackage
   *          the package to find the GenPackage for, must not be {@code null}
   * @return the GenPackage, or {@code null} if the resource declares no matching GenPackage
   */
  private static GenPackage findGenPackageInGenModelResource(final Resource genModelResource, final EPackage ePackage) {
    if (genModelResource == null) {
      return null;
    }
    for (final EObject content : genModelResource.getContents()) {
      if (content instanceof GenModel genmodel) {
        final GenPackage result = findGenPackage(genmodel, ePackage);
        if (result != null) {
          if (result.getEcorePackage() != null) {
            result.getEcorePackage().getEClassifiers(); // ensure the referenced Ecore model is resolved
          }
          return result;
        }
      }
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
    return genModel != null ? findGenPackage(genModel, ePackage) : null;
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
    return genModel != null ? findGenPackage(genModel, ePackage) : null;
  }

  /**
   * Finds the GenPackage for the given EPackage in the given GenModel.
   * <p>
   * {@link GenModel#findGenPackage(EPackage)} matches by identity, while a GenModel references its own copy of the
   * Ecore model. EPackages inferred from a grammar or loaded from another Ecore resource are therefore only found
   * through their namespace URI.
   *
   * @param genModel
   *          the genmodel to search, must not be {@code null}
   * @param ePackage
   *          the package to find the GenPackage for, must not be {@code null}
   * @return the GenPackage, or {@code null} if the genmodel declares no matching GenPackage
   */
  private static GenPackage findGenPackage(final GenModel genModel, final EPackage ePackage) {
    final GenPackage result = genModel.findGenPackage(ePackage);
    if (result == null && ePackage.getNsURI() != null) {
      for (final GenPackage candidate : genModel.getGenPackages()) {
        if (ePackage.getNsURI().equals(candidate.getNSURI())) {
          return candidate;
        }
      }
    }
    return result;
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
    uri = uri.trimFileExtension().appendFileExtension(GENMODEL_EXTENSION);
    uri = uriConverter.normalize(uri);
    if ("http".equals(uri.scheme())/* toString().equals(EcorePackage.eNS_URI) */) { //$NON-NLS-1$
      return null; // optimization, because we are not interested in the extension for the Ecore model.
                   // otherwise getResource will go on the internet to load the model and we loose 20 seconds on each call!
    }

    if (uriConverter.exists(uri, null)) {
      try {
        return resourceSet.getResource(uri, true);
      } catch (final WrappedException e) {
        throw new IllegalStateException("could not retrieve resource for URI " + uri //$NON-NLS-1$
            + " please add URI maps for all relevant Ecore models to the workflow.", e); //$NON-NLS-1$
      }
    } else {
      return null;
    }
  }

}
