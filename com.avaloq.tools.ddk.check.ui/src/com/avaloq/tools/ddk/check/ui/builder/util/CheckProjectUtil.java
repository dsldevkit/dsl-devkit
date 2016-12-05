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
package com.avaloq.tools.ddk.check.ui.builder.util;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;

import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Class providing utility methods for projects.
 */
public class CheckProjectUtil {

  @Inject
  private IStorage2UriMapper mapper;

  private static final Logger LOGGER = Logger.getLogger(CheckProjectUtil.class);

  /**
   * Gets the catalog plugin path.
   * 
   * @param catalog
   *          the catalog
   * @return the catalog plugin path
   */
  public String getCatalogPluginPath(final CheckCatalog catalog) {
    final URI uri = EcoreUtil.getURI(catalog);
    IFile file = RuntimeProjectUtil.findFileStorage(uri, mapper);
    IJavaProject project = JavaCore.create(file.getProject());
    try {
      IPackageFragment packageFragment = project.findPackageFragment(file.getParent().getFullPath());
      String result = packageFragment.getElementName().replace('.', '/');
      return result + '/' + file.getName();
    } catch (JavaModelException e) {
      LOGGER.error("Could not determine plugin path for catalog " + catalog.getName(), e);
    }
    return null;
  }

  /**
   * Gets the catalog qualified name. Instead of using the qualified name provider, which is based
   * on the object attribute values, this method uses the resource properties for determining the
   * fully qualified name. This is legitimate, because the check catalog FQN consists of the Java
   * package name concatenated with the file name (which must be equal to the model name).
   * 
   * @param catalog
   *          the catalog
   * @return the catalog qualified name
   */
  public String getCatalogQualifiedName(final CheckCatalog catalog) {
    final URI uri = EcoreUtil.getURI(catalog);
    IFile file = RuntimeProjectUtil.findFileStorage(uri, mapper);
    IJavaProject project = JavaCore.create(file.getProject());
    try {
      IPackageFragment packageFragment = project.findPackageFragment(file.getParent().getFullPath());
      final String fileNameWithoutExtension = file.getName().substring(0, file.getName().length() - (file.getFileExtension().length() + 1));
      return packageFragment.getElementName() + '.' + fileNameWithoutExtension;
    } catch (JavaModelException e) {
      LOGGER.error("Could not determine plugin path for catalog " + catalog.getName(), e);
    }
    return null;
  }

  /**
   * Gets the help file given a URI and a file name.
   * 
   * @param uri
   *          a URI required for a file of the same project as the help file
   * @param filename
   *          the expected help file name
   * @return the help file
   * @throws CoreException
   *           the core exception
   */
  public IFile getHelpFile(final URI uri, final String filename) throws CoreException {
    return getHelpFile(RuntimeProjectUtil.getProject(uri, mapper), filename);
  }

  /**
   * Gets file handle for the given project (initialized if it doesn't exist).
   * 
   * @param project
   *          the project
   * @param filename
   *          the filename
   * @return the existing file
   * @throws CoreException
   *           the core exception
   */
  public IFile getHelpFile(final IProject project, final String filename) throws CoreException {
    final List<String> splittedPath = Lists.newArrayList(Splitter.on('/').split(filename));
    for (final String seg : splittedPath) {
      if (splittedPath.indexOf(seg) != splittedPath.size() - 1) {
        IFolder folder = project.getFolder(seg);
        if (!folder.exists()) {
          folder.create(true, true, null);
        }
      }
    }
    IFile file = project.getFile(filename);
    if (!file.exists()) {
      file.create(new ByteArrayInputStream("".getBytes()), true, null);
    }
    return file;
  }

  /**
   * Gets the catalog which has been modified.
   * 
   * @param context
   *          the context
   * @param uri
   *          the uri
   * @return the check catalog or {@code null}
   */
  public CheckCatalog getCatalog(final IBuildContext context, final URI uri) {
    Resource resource = context.getResourceSet().getResource(uri, true);
    if (resource == null || !(resource.getContents().get(0) instanceof CheckCatalog)) { // NOPMD null check required
      return null;
    }
    return (CheckCatalog) resource.getContents().get(0);
  }

}

