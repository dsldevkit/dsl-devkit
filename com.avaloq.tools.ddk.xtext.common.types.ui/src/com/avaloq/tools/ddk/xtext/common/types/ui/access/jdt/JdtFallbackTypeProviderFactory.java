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
package com.avaloq.tools.ddk.xtext.common.types.ui.access.jdt;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.impl.IndexedJvmTypeAccess;
import org.eclipse.xtext.common.types.access.impl.TypeResourceServices;
import org.eclipse.xtext.common.types.access.impl.URIHelperConstants;
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.eclipse.xtext.common.types.access.jdt.IJdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.eclipse.xtext.common.types.access.jdt.NullJdtTypeProvider;

import com.avaloq.tools.ddk.xtext.common.types.access.jdt.BundleAwareTypeProvider;
import com.google.inject.Inject;


/**
 * A factory for creating JdtFallbackTypeProvider objects.
 * <p>
 * Handles both Java projects and plain projects (with no classpath).
 * </p>
 */
public class JdtFallbackTypeProviderFactory extends JdtTypeProviderFactory {

  private final ClassLoader classLoader;
  private final IndexedJvmTypeAccess indexedJvmTypeAccess;
  private final TypeResourceServices typeResourceServices;

  /**
   * Creates a new instance of {@link JdtFallbackTypeProviderFactory}.
   *
   * @param javaProjectProvider
   *          the {@link IJavaProjectProvider}, may be {@code null}
   * @param classLoader
   *          the {@link ClassLoader}, must not be {@code null}
   * @param indexedJvmTypeAccess
   *          the {@link IndexedJvmTypeAccess}, may be {@code null}
   * @param typeResourceServices
   *          the {@link TypeResourceServices}, may be {@code null}
   */
  @Inject
  public JdtFallbackTypeProviderFactory(final IJavaProjectProvider javaProjectProvider, final ClassLoader classLoader, final IndexedJvmTypeAccess indexedJvmTypeAccess, final TypeResourceServices typeResourceServices) {
    super(javaProjectProvider);
    this.classLoader = classLoader;
    this.indexedJvmTypeAccess = indexedJvmTypeAccess;
    this.typeResourceServices = typeResourceServices;
  }

  @Override
  public IJdtTypeProvider createTypeProvider(final ResourceSet resourceSet) {
    IJdtTypeProvider result = super.createTypeProvider(resourceSet);
    return createBundleAwareDelegate(result, resourceSet);
  }

  /**
   * Creates a new bundle aware JdtFallbackTypeProvider.
   * <p>
   * <em>Note</em>: Uses a custom bundle class finder to locate and load classes.
   * </p>
   * 
   * @param typeProvider
   *          the {@link IJdtTypeProvider}, may be {@code null}
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   * @return the {@link IJdtTypeProvider}, never {@code null}
   */
  protected IJdtTypeProvider createBundleAwareDelegate(final IJdtTypeProvider typeProvider, final ResourceSet resourceSet) {
    IJdtTypeProvider firstProvider = typeProvider instanceof NullJdtTypeProvider ? null : typeProvider;
    BundleAwareTypeProvider bundleAwareTypeProvider = new BundleAwareTypeProvider(classLoader, resourceSet, indexedJvmTypeAccess, typeResourceServices);
    JdtFallbackTypeProvider result = new JdtFallbackTypeProvider(firstProvider, bundleAwareTypeProvider);
    resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(URIHelperConstants.PROTOCOL, result);
    return result;
  }

  @Override
  public IJvmTypeProvider findTypeProvider(final ResourceSet resourceSet) {
    IJvmTypeProvider result = super.findTypeProvider(resourceSet);
    if ((result instanceof IJdtTypeProvider) && !(result instanceof JdtFallbackTypeProvider)) {
      return createBundleAwareDelegate((IJdtTypeProvider) result, resourceSet);
    }
    return result;
  }
}
