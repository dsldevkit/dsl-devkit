/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;


/**
 * Access to the workspace-wide setting that disables code generation by the builder participants of all DDK languages at once. The setting is shared by all DDK
 * languages (a single boolean in the {@code com.avaloq.tools.ddk} instance preference node) and is surfaced as the master switch on each language's Compiler
 * preference page. As a plain boolean preference it defaults to {@code false} (generation enabled), so behavior is unchanged unless a user opts in.
 */
public final class BuilderParticipantSettings {

  /** Preference node shared by all DDK languages. */
  public static final String QUALIFIER = "com.avaloq.tools.ddk"; //$NON-NLS-1$

  /** Boolean preference key; {@code true} disables generation by all DDK builder participants on workspace builds. */
  public static final String PREF_DISABLE_BUILDER_PARTICIPANTS = "disableBuilderParticipants"; //$NON-NLS-1$

  private static final Logger LOGGER = LogManager.getLogger(BuilderParticipantSettings.class);

  private BuilderParticipantSettings() {
    // static utility
  }

  /**
   * Returns whether generation by the DDK builder participants is disabled workspace-wide.
   *
   * @return {@code true} if the master switch disables generation, {@code false} otherwise (the default)
   */
  public static boolean isGenerationDisabled() {
    return InstanceScope.INSTANCE.getNode(QUALIFIER).getBoolean(PREF_DISABLE_BUILDER_PARTICIPANTS, false);
  }

  /**
   * Sets whether generation by the DDK builder participants is disabled workspace-wide.
   *
   * @param disabled
   *          {@code true} to disable generation for all DDK languages, {@code false} to re-enable it
   */
  public static void setGenerationDisabled(final boolean disabled) {
    final Preferences node = InstanceScope.INSTANCE.getNode(QUALIFIER);
    node.putBoolean(PREF_DISABLE_BUILDER_PARTICIPANTS, disabled);
    try {
      node.flush();
    } catch (BackingStoreException e) {
      LOGGER.error("Could not persist the DDK builder participant master switch", e); //$NON-NLS-1$
    }
  }

}
