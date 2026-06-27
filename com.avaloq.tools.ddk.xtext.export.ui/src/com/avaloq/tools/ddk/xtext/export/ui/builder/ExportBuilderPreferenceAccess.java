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
package com.avaloq.tools.ddk.xtext.export.ui.builder;

import org.eclipse.xtext.builder.preferences.BuilderPreferenceAccess;


/**
 * Additional builder preferences for the Export DSL.
 */
public class ExportBuilderPreferenceAccess extends BuilderPreferenceAccess {

  /**
   * Preference identifier controlling whether the Export builder participant is disabled on a workspace build. As a boolean preference it defaults to
   * {@code false} (i.e. not disabled = enabled), so existing workspaces keep regenerating; setting it to {@code true} is the opt-in to skip regeneration.
   */
  public static final String PREF_DISABLE_BUILDER_PARTICIPANT = "disableBuilderParticipant"; //$NON-NLS-1$

}
