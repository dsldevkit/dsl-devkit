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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@SuppressWarnings("nls")
public class GeneratorSupport {

  /** Class-wide logger. */
  private static final Logger LOG = LogManager.getLogger(GeneratorSupport.class);

  /** The values memoized by the innermost {@link #executeWithProjectResourceLoader} call running on the current thread. */
  private static final ThreadLocal<Map<Object, Object>> MEMOIZED_VALUES = new ThreadLocal<>();

  /**
   * Executes a given operation using a custom resource loader which will load resources using the classpath of the given project, provided that it is a Java
   * project.
   * <p>
   * The operation can {@link #memoize(Object, Supplier) memoize} values for the duration of this call.
   * </p>
   *
   * @param project
   *          context project, can also be {@code null}
   * @param runnable
   *          operation to run
   */
  public void executeWithProjectResourceLoader(final IProject project, final Runnable runnable) {
    ResourceLoader oldResourceLoader = ResourceLoaderFactory.getCurrentThreadResourceLoader();
    ResourceLoader resourceLoader = createResourceLoader(project);
    Map<Object, Object> oldMemoizedValues = MEMOIZED_VALUES.get();
    try {
      ResourceLoaderFactory.setCurrentThreadResourceLoader(resourceLoader);
      MEMOIZED_VALUES.set(new HashMap<>());
      runnable.run();
    } finally {
      if (oldMemoizedValues == null) {
        MEMOIZED_VALUES.remove();
      } else {
        MEMOIZED_VALUES.set(oldMemoizedValues);
      }
      ResourceLoaderFactory.setCurrentThreadResourceLoader(oldResourceLoader);
      if (resourceLoader instanceof CustomResourceLoader) {
        ((CustomResourceLoader) resourceLoader).close();
      }
    }
  }

  /**
   * Returns the value memoized under the given key by the innermost {@link #executeWithProjectResourceLoader} call running
   * on the current thread, computing and memoizing it on first request. Outside of such a call the value is computed on
   * every request.
   * <p>
   * The memoized values are discarded when that call returns, so they live for one generation pass at most and never
   * outlive the index and classpath state they were computed from. Only memoize values that are fully determined by the
   * key and by that state.
   * </p>
   *
   * @param <T>
   *          the type of the value
   * @param key
   *          the key identifying the value, must not be {@code null} and must implement {@link Object#equals(Object)}
   *          and {@link Object#hashCode()} consistently
   * @param supplier
   *          computes the value, must not be {@code null}; the value may be {@code null}
   * @return the memoized or computed value, may be {@code null}
   */
  @SuppressWarnings("unchecked")
  public <T> T memoize(final Object key, final Supplier<? extends T> supplier) {
    final Map<Object, Object> memoizedValues = MEMOIZED_VALUES.get();
    if (memoizedValues == null) {
      return supplier.get();
    }
    if (memoizedValues.containsKey(key)) {
      return (T) memoizedValues.get(key);
    }
    final T value = supplier.get();
    memoizedValues.put(key, value);
    return value;
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

    void close() {
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
