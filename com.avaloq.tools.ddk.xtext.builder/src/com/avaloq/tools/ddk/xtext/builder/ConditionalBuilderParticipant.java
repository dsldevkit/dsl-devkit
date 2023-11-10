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

import org.eclipse.xtext.builder.BuilderParticipant;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;


/**
 * A base class for all custom builder participants. Contains functionalities to test whether for a given resource the generation should be executed.
 */
public class ConditionalBuilderParticipant extends BuilderParticipant {

  private static final String GENERATION_FILE_SRC_DIRECTORY = "src"; //$NON-NLS-1$

  /**
   * Checks whether {@link BuilderParticipant} should run for a given {@link Delta} and it has no errors.
   *
   * @param delta
   *          structure corresponding to the changed file
   * @param resourceServiceProvider
   *          used to check whether BuilderParticipant containing this {@link IResourceServiceProvider} should be called for given {@link Delta}
   * @return true if participant should run for given delta, false otherwise
   */
  public boolean isAffected(final Delta delta, final IResourceServiceProvider resourceServiceProvider) {
    return hasCorrectExtension(delta, resourceServiceProvider) && isSourceOriginated(delta);
  }

  /**
   * Checks whether a file described by given delta has a correct extension to be handled by concrete BuilderParticipan.
   *
   * @param delta
   *          structure corresponding to the changed file
   * @param resourceServiceProvider
   *          used to check whether BuilderParticipant containing this {@link IResourceServiceProvider} should be called for given {@link Delta}
   * @return true if file associated with given delta has extension that should be handled by participant owning given {@link IResourceServiceProvider}
   */
  public boolean hasCorrectExtension(final Delta delta, final IResourceServiceProvider resourceServiceProvider) {
    return resourceServiceProvider.canHandle(delta.getUri());
  }

  /**
   * Checks whether a file described by given delta belongs only to the source path. Generation should not be performed for deltas stored e.g. in "bin"
   * directory
   *
   * @param delta
   *          structure corresponding to the changed file
   * @return true if file belongs to the "src" directory
   */
  public boolean isSourceOriginated(final Delta delta) {
    return delta.getUri().segments().length > 2 && GENERATION_FILE_SRC_DIRECTORY.equalsIgnoreCase(delta.getUri().segments()[2].trim());
  }

}
