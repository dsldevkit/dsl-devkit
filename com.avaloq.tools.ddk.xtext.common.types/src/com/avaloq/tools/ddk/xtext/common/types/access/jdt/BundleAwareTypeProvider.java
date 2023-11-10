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
package com.avaloq.tools.ddk.xtext.common.types.access.jdt;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.access.binary.BinaryClassFinder;
import org.eclipse.xtext.common.types.access.impl.ClasspathTypeProvider;
import org.eclipse.xtext.common.types.access.impl.IndexedJvmTypeAccess;
import org.eclipse.xtext.common.types.access.impl.TypeResourceServices;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.osgi.framework.Bundle;


/**
 * A bundle aware classpath type provider.
 * <p>
 * Provides a JVM classpath type provider handling Xtext resource sets with bundles as classpath URI context. Allows finding resources on the bundle's
 * classpath.
 * </p>
 */
public class BundleAwareTypeProvider extends ClasspathTypeProvider {

  /**
   * Creates a new instance of {@link BundleAwareTypeProvider}.
   * 
   * @param classLoader
   *          the {@link ClassLoader}, may be {@code null}
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   * @param indexedJvmTypeAccess
   *          the {@link IndexedJvmTypeAccess}, may be {@code null}
   */
  public BundleAwareTypeProvider(final ClassLoader classLoader, final ResourceSet resourceSet, final IndexedJvmTypeAccess indexedJvmTypeAccess, final TypeResourceServices typeResourceServices) {
    super(classLoader, resourceSet, indexedJvmTypeAccess, typeResourceServices);
  }

  @Override
  protected BinaryClassFinder createBinaryClassFinder(final ClassLoader classLoader) {
    if (getResourceSet() instanceof XtextResourceSet) {
      Object context = ((XtextResourceSet) getResourceSet()).getClasspathURIContext();
      if (context instanceof Bundle) {
        Bundle bundle = (Bundle) context;
        return new BundleClassFinder(classLoader, bundle);
      }
    }
    return super.createBinaryClassFinder(classLoader);
  }
}
