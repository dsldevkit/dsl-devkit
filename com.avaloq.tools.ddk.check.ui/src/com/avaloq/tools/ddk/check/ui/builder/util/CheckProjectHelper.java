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
package com.avaloq.tools.ddk.check.ui.builder.util;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;


/**
 * Class providing utility methods for projects.
 */
@Singleton
@SuppressWarnings("nls")
public class CheckProjectHelper {

  private static final Logger LOGGER = LogManager.getLogger(CheckProjectHelper.class);

  @Inject
  private IStorage2UriMapper mapper;

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String languageName;

  private String preferenceFileQualifier;

  /**
   * Gets the preference file qualifier.
   *
   * @return the preference file qualifier
   */
  private String getPreferenceFileQualifier() {
    if (preferenceFileQualifier == null) {
      preferenceFileQualifier = languageName + ".pluginxml";
    }
    return preferenceFileQualifier;
  }

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
      file.create(new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8)), true, null);
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

  /**
   * Checks that the Java file for the given class name exists in the project.
   *
   * @param project
   *          the project, must not be {@code null}
   * @param className
   *          the class name, must not be {@code null}
   * @return whether the corresponding java file exists in the project
   */
  public boolean isJavaFilePresent(final IProject project, final String className) {
    int packageSplitIndex = className.lastIndexOf('.');
    if (packageSplitIndex == -1) {
      throw new IllegalArgumentException(String.format("%s is a simple class name or not a class name at all.", className));
    }
    int fileNameEndIndex = className.indexOf('$');
    if (fileNameEndIndex == -1) {
      fileNameEndIndex = className.length();
    }
    String packageName = className.substring(0, packageSplitIndex);
    String fileName = className.substring(packageSplitIndex + 1, fileNameEndIndex) + ".java";
    try {
      if (project.hasNature(JavaCore.NATURE_ID)) {
        for (IPackageFragmentRoot root : JavaCore.create(project).getAllPackageFragmentRoots()) {
          if (root.getKind() == IPackageFragmentRoot.K_SOURCE) {
            for (IJavaElement element : root.getChildren()) {
              if (element instanceof IPackageFragment && packageName.equals(element.getElementName())
                  && ((IPackageFragment) element).getCompilationUnit(fileName).exists()) {
                return true;
              }
            }
          }
        }
      }
    } catch (CoreException e) {
      LOGGER.error(String.format("Failed to read project %s.", project.getName()), e);
    }
    return false;
  }

  /**
   * Gets the specified preference from the given project.
   *
   * @param project
   *          the project
   * @param preferenceName
   *          the preference name
   * @return the preference value or {@code null} if it is not configured in the project
   */
  public String getProjectPreference(final IProject project, final String preferenceName) {
    ScopedPreferenceStore preferenceStore = new ScopedPreferenceStore(new ProjectScope(project), getPreferenceFileQualifier());
    if (preferenceStore.contains(preferenceName)) {
      return preferenceStore.getString(preferenceName);
    }
    return null;
  }

}
