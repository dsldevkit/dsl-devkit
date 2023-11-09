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
package com.avaloq.tools.ddk.check.generator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.generator.GenModelAccess;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;


/**
 * Utility class for querying GenModels.
 */
@SuppressWarnings({"deprecation", "removal"})
public final class CheckGenModelUtil {

  private static final String GENMODEL_EXTENSION = "genmodel"; //$NON-NLS-1$
  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(CheckGenModelUtil.class);

  /**
   * Private Constructor (singleton).
   */
  private CheckGenModelUtil() {
    // empty constructor
  }

  /**
   * Returns the qualified package interface name for the given epackage (model).
   *
   * @param ePackage
   *          the model
   * @return the package interface name
   */
  public static String getQualifiedPackageInterfaceName(final EPackage ePackage) {
    if (ePackage.getClass() == EPackageImpl.class) {
      // EPackage loaded from ecore model
      GenPackage genPackage = findGenPackage(ePackage);
      if (genPackage != null) {
        return genPackage.getQualifiedPackageInterfaceName();
      }
    } else {
      // EPackage loaded from Java
      Class<?>[] interfaces = ePackage.getClass().getInterfaces();
      if (interfaces != null && interfaces.length > 0) {
        return interfaces[0].getName();
      }
    }
    return null;
  }

  /**
   * Returns the genmodel for the given model element.
   *
   * @param eModelElement
   *          the model element
   * @return the genmodel
   */
  public static GenModel findGenModel(final EModelElement eModelElement) {
    return findGenModel(eModelElement, eModelElement.eResource() != null ? eModelElement.eResource().getResourceSet() : new ResourceSetImpl());
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

    GenModel resultGenModel = null;
    try {
      GenPackage genPackage = GenModelAccess.getGenPackage(ePackage, resourceSet);
      resultGenModel = genPackage.getGenModel();
      if (resultGenModel != null) {
        return resultGenModel;
      }
      // CHECKSTYLE:CHECK-OFF IllegalCatch
    } catch (RuntimeException e) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
    }
    try {
      resultGenModel = getGenModelUsingHeuristics(ePackage);
    } catch (final IllegalStateException e) {
      LOGGER.error(NLS.bind("Exception in findGenModel ({0})", eModelElement), e); //$NON-NLS-1$
    }

    return resultGenModel;
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
   * Loads the given resource, and if it contains a GenModel for the given ePackage, resturns that.
   *
   * @param uri
   *          to load resource from
   * @param ePackage
   *          to check for
   * @param resourceSet
   *          to use for loading resources
   * @return the GenModel for the given ePackage, or null if none found.
   */
  private static GenModel loadGenModel(final URI uri, final EPackage ePackage, final ResourceSet resourceSet) {
    try {
      Resource res = resourceSet.getResource(uri, true);
      for (GenModel model : Iterables.filter(res.getContents(), GenModel.class)) {
        if (model.findGenPackage(ePackage) != null) {
          return model;
        }
      }
      // CHECKSTYLE:CHECK-OFF IllegalCatch
    } catch (Exception ex) {
      // CHECKSTYLE:CHECK-ON IllegalCatch
    }
    return null;
  }

  /**
   * Returns the genmodel for the given EPackage.
   *
   * @param ePackage
   *          the model element
   * @return the genmodel
   */
  private static GenModel getGenModelUsingHeuristics(final EPackage ePackage) {
    final Resource res = ePackage.eResource();
    ResourceSet resourceSet = res.getResourceSet();
    if (resourceSet == null) {
      resourceSet = new ResourceSetImpl();
      resourceSet.getResources().add(res);
    }

    final URIConverter uriConverter = resourceSet.getURIConverter();
    URI uri = uriConverter.normalize(res.getURI());
    final URI originalURI = uri; // NOPMD - We want the value before reassignment
    URI baseURI = uri.trimFileExtension();
    uri = baseURI.appendFileExtension(GENMODEL_EXTENSION);
    uri = uriConverter.normalize(uri);
    if ("http".equals(uri.scheme())/* toString().equals(EcorePackage.eNS_URI) */) { //$NON-NLS-1$
      return null; // optimization, because we are not interested in the extension for the Ecore model.
                   // otherwise getResource will go on the internet to load the model and we loose 20 seconds on each call!
    }

    GenModel result = null;
    // First try: same uri as containing ecore model, with file extension switched to genmodel
    if (uriConverter.exists(uri, null)) {
      result = loadGenModel(uri, ePackage, resourceSet);
    }
    // If the first try failed: look for any *.genmodel files in the same folder.
    if (result == null) {
      baseURI = baseURI.trimSegments(1);
      IContainer[] folders = determineContainersToCheck(baseURI);
      try {
        result = findGenModelInContainers(folders, baseURI, ePackage, resourceSet);
      } catch (CoreException e) {
        // Ignore
      }
    }
    if (result == null) {
      throw new IllegalStateException("No genmodel found for " + originalURI); //$NON-NLS-1$
    }
    return result;
  }

  /**
   * Given a base URI, figure out which {@link IFolder}, if any, it refers to.
   *
   * @param baseURI
   *          to find the folder(s) for; must not be {@code null}
   * @return an array of all folders mapping to that URI, or an empty array if none do.
   */
  private static IContainer[] determineContainersToCheck(final URI baseURI) {
    Preconditions.checkNotNull(baseURI);
    IContainer[] result = {};
    if (baseURI.isPlatformResource() || baseURI.isFile()) {
      IWorkspaceRoot workspace = EcorePlugin.getWorkspaceRoot();
      if (workspace != null) {
        if (baseURI.isFile()) {
          result = workspace.findContainersForLocationURI(java.net.URI.create(baseURI.toString()));
        } else {
          // Must be a platform/resource URI
          IPath platformPath = new Path(baseURI.toPlatformString(true));
          IFolder folder = workspace.getFolder(platformPath);
          if (folder != null) {
            result = new IContainer[] {folder};
          }
        }
      }
    }
    return result;
  }

  /**
   * Given an array of {@link IContainers}, searches through all *.genmodel files in all {@link IFolders}, ignoring nested folders, trying to find a
   * {@link GenModel} for the given {@link EPackage}.
   *
   * @param containers
   *          To search, may be empty, but must not be {@code null}
   * @param baseURI
   *          of these containers; must not be {@code null}
   * @param ePackage
   *          to find a genmodel for; must not be {@code null}
   * @param resourceSet
   *          to use for resource loading; must not be {@code null}
   * @return the genmodel, if found, or {@code null} if not.
   * @throws CoreException
   *           if enumerating folder contents does so
   */
  private static GenModel findGenModelInContainers(final IContainer[] containers, final URI baseURI, final EPackage ePackage, final ResourceSet resourceSet) throws CoreException {
    Preconditions.checkNotNull(containers);
    Preconditions.checkNotNull(baseURI);
    Preconditions.checkNotNull(ePackage);
    Preconditions.checkNotNull(resourceSet);
    final URIConverter uriConverter = resourceSet.getURIConverter();
    for (IContainer container : containers) {
      if (!(container instanceof IFolder)) {
        continue;
      }
      IResource[] resources = container.members();
      for (IResource r : resources) {
        if (r.exists() && r instanceof IFile && GENMODEL_EXTENSION.equals(r.getFileExtension())) {
          URI uriToTry = uriConverter.normalize(baseURI.appendSegment(URI.encodeSegment(r.getName(), false)));
          GenModel result = loadGenModel(uriToTry, ePackage, resourceSet);
          if (result != null) {
            return result;
          }
        }
      }
    }
    return null;
  }

}
