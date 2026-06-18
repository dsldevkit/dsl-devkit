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
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;

import com.google.inject.Inject;


/**
 * A base class for all custom builder participants. Contains functionalities to test whether for a given resource the generation should be executed.
 */
public class ConditionalBuilderParticipant extends BuilderParticipant {

  private static final String GENERATION_FILE_SRC_DIRECTORY = "src"; //$NON-NLS-1$

  /**
   * Used to resolve the per-language preference store that controls whether this participant regenerates on workspace build. Optional so that non-UI,
   * stand-alone or test instantiations (where no {@link IPreferenceStoreAccess} is bound) keep working; combined with a {@code null} preference key the gate is
   * then inert and the participant always runs.
   */
  @Inject(optional = true)
  private IPreferenceStoreAccess preferenceStoreAccess;

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

  /**
   * Returns the preference key of the boolean "disable builder participant" preference. Languages that want to expose that preference override this to return
   * their own (per-language store namespaced) key; the default {@code null} means the gate is inert and the participant always runs, keeping behaviour
   * unchanged for participants that do not opt in.
   * <p>
   * The preference is modelled as a {@code disable} flag deliberately: a boolean preference defaults to {@code false} natively, so "not disabled" (= enabled) is
   * the default in every context (IDE, headless, tests) without depending on a preference initializer or scope-chained default ever having run.
   *
   * @return the boolean "disabled" preference key, or {@code null} to always run (no gate)
   */
  protected String getBuilderParticipantDisabledPreferenceKey() {
    return null;
  }

  /**
   * Determines whether this participant should regenerate its artifacts for the given build. Enabled by default; disabled only when the per-language preference
   * identified by {@link #getBuilderParticipantDisabledPreferenceKey()} is explicitly set to {@code true} in the project's context preference store.
   * <p>
   * The gate is inert (always returns {@code true}) when no preference key is supplied or when no {@link IPreferenceStoreAccess} is bound (non-UI/test setups).
   *
   * @param context
   *          the build context, must not be {@code null}
   * @return {@code true} if generation should run, {@code false} if the participant is disabled for the workspace
   */
  protected boolean isBuilderParticipantEnabled(final IBuildContext context) {
    final String key = getBuilderParticipantDisabledPreferenceKey();
    if (key == null || preferenceStoreAccess == null) {
      return true;
    }
    return !preferenceStoreAccess.getContextPreferenceStore(context.getBuiltProject()).getBoolean(key);
  }

}
