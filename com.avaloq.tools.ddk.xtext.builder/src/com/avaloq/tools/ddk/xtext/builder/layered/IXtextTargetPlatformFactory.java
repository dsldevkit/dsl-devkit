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
package com.avaloq.tools.ddk.xtext.builder.layered;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * A factory for target platforms.
 */
@ImplementedBy(IXtextTargetPlatformFactory.Default.class)
public interface IXtextTargetPlatformFactory {

  /**
   * Get a new target platform.
   *
   * @param monitor
   *          to report progress
   * @return the platform
   * @throws CoreException
   *           if there was a problem loading or creating the platform (e.g. incompatibility problems)
   */
  IXtextTargetPlatform get(IProgressMonitor monitor) throws CoreException;

  /**
   * Default implementation which always simply returns an instance bound to the type {@link IXtextTargetPlatform}.
   */
  class Default implements IXtextTargetPlatformFactory {

    @Inject
    private Provider<IXtextTargetPlatform> platformProvider;

    /** {@inheritDoc} */
    @Override
    public IXtextTargetPlatform get(final IProgressMonitor monitor) throws CoreException {
      return platformProvider.get();
    }
  }

}
