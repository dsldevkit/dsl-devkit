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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.mwe.core.resources.ResourceLoader;
import org.eclipse.emf.mwe.core.resources.ResourceLoaderFactory;
import org.eclipse.emf.mwe.core.resources.ResourceLoaderImpl;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;


/**
 * Support methods for Expression DSL based generators.
 */
public class GeneratorSupport {

  /** Class-wide logger. */
  private static final Logger LOG = Logger.getLogger(GeneratorSupport.class);

  /**
   * Executes a given operation using a custom resource loader which will load resources using the classpath of the given project, provided that it is a Java
   * project.
   *
   * @param project
   *          context project, can also be {@code null}
   * @param runnable
   *          operation to run
   */
  public void executeWithProjectResourceLoader(final IProject project, final Runnable runnable) {
    ResourceLoader oldResourceLoader = ResourceLoaderFactory.getCurrentThreadResourceLoader();
    ResourceLoader resourceLoader = createResourceLoader(project);
    try {
      ResourceLoaderFactory.setCurrentThreadResourceLoader(resourceLoader);
      runnable.run();
    } finally {
      ResourceLoaderFactory.setCurrentThreadResourceLoader(oldResourceLoader);
      if (resourceLoader instanceof CustomResourceLoader) {
        ((CustomResourceLoader) resourceLoader).close();
      }
    }
  }

  /**
   * Custom resource loader which declares a {@link #close()} method to {@link URLClassLoader#close() close} the underlying class loader.
   */
  private static class CustomResourceLoader extends ResourceLoaderImpl {

    private final URLClassLoader loader;

    CustomResourceLoader(final URLClassLoader l) {
      super(l);
      this.loader = l;
    }

    public void close() {
      try {
        loader.close();
      } catch (IOException e) {
        LOG.warn("Error closing class loader or resource loader", e);
      }
    }

  }

  private ResourceLoader createResourceLoader(final IProject project) {
    if (project != null) {
      IJavaProject javaProject = JavaCore.create(project);
      if (javaProject != null) {
        IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        try {
          IClasspathEntry[] classPathEntries = javaProject.getResolvedClasspath(true);
          URL[] urls = new URL[classPathEntries.length];
          for (int i = 0; i < classPathEntries.length; i++) {
            IClasspathEntry entry = classPathEntries[i];
            IPath path = null;
            switch (entry.getEntryKind()) {
            case IClasspathEntry.CPE_PROJECT:
              IJavaProject requiredProject = JavaCore.create((IProject) workspaceRoot.findMember(entry.getPath()));
              if (requiredProject != null) {
                path = workspaceRoot.findMember(requiredProject.getOutputLocation()).getLocation();
              }
              break;
            case IClasspathEntry.CPE_SOURCE:
              path = workspaceRoot.findMember(entry.getPath()).getLocation();
              break;
            default:
              path = entry.getPath();
              break;
            }
            if (path != null) {
              urls[i] = path.toFile().toURI().toURL();
            }
          }
          ClassLoader parentClassLoader = javaProject.getClass().getClassLoader();
          URLClassLoader classLoader = new URLClassLoader(urls, parentClassLoader);
          return new CustomResourceLoader(classLoader);
        } catch (MalformedURLException | CoreException e) {
          LOG.warn("Failed to create class loader for project " + project.getName(), e);
        }
      }
    }
    return new ResourceLoaderImpl(GeneratorSupport.class.getClassLoader());
  }

}
