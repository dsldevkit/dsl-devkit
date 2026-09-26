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
import java.util.Objects;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
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

  /**
   * The {@link #executeWithProjectResourceLoader} call whose resource loader is installed on the current thread, if any.
   * Nested calls for the same project and resource loader run within it and do not replace it.
   */
  private static final ThreadLocal<ProjectScope> CURRENT_SCOPE = new ThreadLocal<>();

  /**
   * One {@link #executeWithProjectResourceLoader} call: the project, the resource loader installed for it and the values
   * memoized while it runs.
   *
   * @param project
   *          the project, may be {@code null}
   * @param resourceLoader
   *          the resource loader installed for the project
   * @param memoizedValues
   *          the values memoized while the call runs
   */
  private record ProjectScope(IProject project, ResourceLoader resourceLoader, Map<Object, Object> memoizedValues) {
  }

  /**
   * Executes a given operation using a custom resource loader which will load resources using the classpath of the given project, provided that it is a Java
   * project.
   * <p>
   * The operation can {@link #memoize(Object, Supplier) memoize} values for the duration of this call.
   * </p>
   * <p>
   * If the current thread already executes an operation with the resource loader of the same project, the given operation
   * runs directly within that call: it uses the resource loader already installed and shares the memoized values, instead
   * of building another class loader from the project's resolved classpath. A generator can thus establish the resource
   * loader once for a whole resource, and the method bodies it renders nested within that call reuse it.
   * </p>
   *
   * @param project
   *          context project, can also be {@code null}
   * @param runnable
   *          operation to run
   */
  public void executeWithProjectResourceLoader(final IProject project, final Runnable runnable) {
    ProjectScope enclosingScope = CURRENT_SCOPE.get();
    if (enclosingScope != null && Objects.equals(enclosingScope.project(), project)
        && enclosingScope.resourceLoader() == ResourceLoaderFactory.getCurrentThreadResourceLoader()) {
      runnable.run();
      return;
    }
    ResourceLoader oldResourceLoader = ResourceLoaderFactory.getCurrentThreadResourceLoader();
    ResourceLoader resourceLoader = createResourceLoader(project);
    try {
      ResourceLoaderFactory.setCurrentThreadResourceLoader(resourceLoader);
      CURRENT_SCOPE.set(new ProjectScope(project, resourceLoader, new HashMap<>()));
      runnable.run();
    } finally {
      if (enclosingScope == null) {
        CURRENT_SCOPE.remove();
      } else {
        CURRENT_SCOPE.set(enclosingScope);
      }
      ResourceLoaderFactory.setCurrentThreadResourceLoader(oldResourceLoader);
      if (resourceLoader instanceof CustomResourceLoader) {
        ((CustomResourceLoader) resourceLoader).close();
      }
    }
  }

  /**
   * Executes a given operation using a custom resource loader which will load resources using the classpath of the project
   * containing the given resource, see {@link #executeWithProjectResourceLoader(IProject, Runnable)}.
   *
   * @param resource
   *          context resource, must not be {@code null}; if it is not a workspace resource no project is used
   * @param runnable
   *          operation to run
   */
  public void executeWithProjectResourceLoaderOf(final Resource resource, final Runnable runnable) {
    executeWithProjectResourceLoader(projectOf(resource), runnable);
  }

  /**
   * Returns the workspace project containing the given resource, if any.
   *
   * @param resource
   *          the resource, must not be {@code null}
   * @return the containing project, or {@code null} if it cannot be determined
   */
  private IProject projectOf(final Resource resource) {
    final URI uri = resource.getURI();
    if (uri.isPlatformResource()) {
      final IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(uri.toPlatformString(true));
      if (member != null) {
        return member.getProject();
      }
    }
    return null;
  }

  /**
   * Returns the value memoized under the given key by the {@link #executeWithProjectResourceLoader} call whose resource
   * loader is installed on the current thread, computing and memoizing it on first request. That is the outermost call for
   * the current project and resource loader, since nested calls for them run within it. Outside of such a call the value is
   * computed on every request.
   * <p>
   * The memoized values are discarded when that call returns. Callers of {@link #executeWithProjectResourceLoader} that
   * use memoized values must therefore limit the call to one generation pass, so that the values never outlive the index
   * and classpath state they were computed from. Only memoize values that are fully determined by the key and by that
   * state. A {@code null} value is not memoized but computed again on the next request, so that a lookup which found
   * nothing can succeed once more state has been loaded.
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
  public static <T> T memoize(final Object key, final Supplier<? extends T> supplier) {
    final ProjectScope scope = CURRENT_SCOPE.get();
    if (scope == null) {
      return supplier.get();
    }
    final Map<Object, Object> memoizedValues = scope.memoizedValues();
    final Object memoized = memoizedValues.get(key);
    if (memoized != null) {
      return (T) memoized;
    }
    final T value = supplier.get();
    if (value != null) {
      memoizedValues.put(key, value);
    }
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
