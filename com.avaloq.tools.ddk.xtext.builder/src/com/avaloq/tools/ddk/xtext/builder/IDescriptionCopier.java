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
package com.avaloq.tools.ddk.xtext.builder;

import org.eclipse.xtext.builder.builderState.BuilderStateUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;

import com.google.inject.ImplementedBy;


/**
 * Used by the builder to create a copy of an IResourceDescription which is then included in the new builder state.
 */
@ImplementedBy(IDescriptionCopier.BuilderStateDescriptionCopier.class)
public interface IDescriptionCopier {

  /**
   * Returns a copy of the given resource description.
   *
   * @param desc
   *          description to copy
   * @return copied description
   */
  IResourceDescription copy(IResourceDescription desc);

  /**
   * Returns a copy of the given object description.
   *
   * @param desc
   *          description to copy
   * @return copied description
   */
  IEObjectDescription copy(IEObjectDescription desc);

  /**
   * Returns a copy of the given reference description.
   *
   * @param desc
   *          description to copy
   * @return copied description
   */
  IReferenceDescription copy(IReferenceDescription desc);

  /**
   * Default implementation of {@link IDescriptionCopier} which uses the Xtext default {@link BuilderStateUtil} helper.
   */
  class BuilderStateDescriptionCopier implements IDescriptionCopier {

    @Override
    public IResourceDescription copy(final IResourceDescription desc) {
      return BuilderStateUtil.create(desc);
    }

    @Override
    public IEObjectDescription copy(final IEObjectDescription desc) {
      return BuilderStateUtil.create(desc);
    }

    @Override
    public IReferenceDescription copy(final IReferenceDescription desc) {
      return BuilderStateUtil.create(desc);
    }

  }

}
