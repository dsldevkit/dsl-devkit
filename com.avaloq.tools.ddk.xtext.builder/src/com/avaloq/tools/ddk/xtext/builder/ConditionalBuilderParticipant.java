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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xtext.builder.BuilderParticipant;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;


/**
 * A base class for all custom builder participants. Contains functionalities to test whether for a given resource the generation should be executed.
 */
public class ConditionalBuilderParticipant extends BuilderParticipant {

  private static final String GENERATION_FILE_SRC_DIRECTORY = "src"; //$NON-NLS-1$

  @Override
  public void build(final IBuildContext context, final IProgressMonitor monitor) throws CoreException {
    if (!isBuilderParticipantEnabled() && context.getBuildType() != BuildType.CLEAN) {
      return;
    }
    super.build(context, monitor);
  }

  /**
   * Determines whether DDK builder participants should regenerate their artifacts on workspace builds. Disabled workspace-wide for all DDK languages at once by
   * the master switch on the languages' Compiler preference pages (see {@link BuilderParticipantSettings}); enabled by default.
   * <p>
   * Note that the switch governs every {@code ConditionalBuilderParticipant} descendant that delegates to {@code super.build(...)} — including participants of
   * downstream languages built on this class. Subclasses overriding {@link #build(IBuildContext, IProgressMonitor)} without delegating to {@code super} must
   * check this method themselves to be covered by the switch. Explicit {@code CLEAN} builds are exempt from the gate: while the switch disables regeneration, a
   * Project &gt; Clean still deletes the generated artifacts (which then stay deleted until the switch is re-enabled).
   *
   * @return {@code true} if generation should run, {@code false} if the DDK-wide master switch disables it
   */
  protected boolean isBuilderParticipantEnabled() {
    return !BuilderParticipantSettings.isGenerationDisabled();
  }

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
