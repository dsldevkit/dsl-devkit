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
package com.avaloq.tools.ddk.xtext.format.ui.typing;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.DefaultWorkingCopyOwner;
import org.eclipse.xtext.common.types.access.impl.TypeResourceServices;
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.eclipse.xtext.common.types.access.jdt.IJdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.IWorkingCopyOwnerProvider;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory;
import org.eclipse.xtext.common.types.access.jdt.NullJdtTypeProvider;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * A factory for creating JdtFallbackTypeProvider objects. Handles both Java projects
 * and plain projects (with no classpath).
 */
@SuppressWarnings("restriction")
@Singleton
public class FormatJdtTypeProviderFactory extends JdtTypeProviderFactory {

  @Inject
  private IWorkingCopyOwnerProvider copyOwnerProvider;

  @Inject
  private TypeResourceServices typeResourceServices;

  /**
   * Creates a new instance of {@link FormatJdtTypeProviderFactory}.
   * Required for object injection.
   */
  public FormatJdtTypeProviderFactory() {
    // required by injector
  }

  public FormatJdtTypeProviderFactory(final IJavaProjectProvider javaProjectProvider) {
    super(javaProjectProvider);
  }

  @Override
  protected IJdtTypeProvider createJdtTypeProvider(final IJavaProject javaProject, final ResourceSet resourceSet) {
    if (javaProject == null) {
      return new NullJdtTypeProvider(resourceSet);
    }
    return new FormatJdtTypeProvider(javaProject, resourceSet, getIndexedJvmTypeAccess(), copyOwnerProvider == null ? DefaultWorkingCopyOwner.PRIMARY
        : copyOwnerProvider.getWorkingCopyOwner(javaProject, resourceSet), typeResourceServices);
  }

}
