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
package com.avaloq.tools.ddk.check.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.resource.DefaultResourceUIServiceProvider;

import com.google.inject.Inject;


/**
 * Excludes generated check catalogs from the build.
 * <p>
 * The generator emits a {@link com.avaloq.tools.ddk.check.generator.CheckStubCompiler stub} of every catalog, that is, a check file declaring the public API of
 * the catalog, into the output folder. Without this service provider that stub would be indexed and built like any other check file, which would result in a
 * second catalog with the very same qualified name.
 * </p>
 */
public class CheckResourceUIServiceProvider extends DefaultResourceUIServiceProvider {

  @Inject
  public CheckResourceUIServiceProvider(final IResourceServiceProvider delegate) {
    super(delegate);
  }

  @Override
  public boolean canBuild(final URI uri, final IStorage storage) {
    if (storage instanceof IFile && ((IFile) storage).isDerived(IResource.CHECK_ANCESTORS)) {
      return false;
    }
    return super.canBuild(uri, storage);
  }

}
