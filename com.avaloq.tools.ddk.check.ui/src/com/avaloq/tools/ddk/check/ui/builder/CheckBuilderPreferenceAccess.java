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

package com.avaloq.tools.ddk.check.ui.builder;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;
import org.eclipse.xtext.xbase.ui.builder.XbaseBuilderPreferenceAccess;

import com.avaloq.tools.ddk.check.compiler.CheckGeneratorConfig;
import com.google.inject.Inject;


/**
 * Additional configurations for Check DSL.
 */
public class CheckBuilderPreferenceAccess extends XbaseBuilderPreferenceAccess {

  /**
   * Preference identifier for language internal checks.
   */
  public static final String PREF_GENERATE_LANGUAGE_INTERNAL_CHECKS = "generateLanguageInternalChecks"; //$NON-NLS-1$

  /**
   * Initializer for check-specific generator preferences.
   */
  public static class Initializer extends XbaseBuilderPreferenceAccess.Initializer {

    @Override
    protected void initializeBuilderPreferences(final IPreferenceStore store) {
      super.initializeBuilderPreferences(store);
      store.setDefault(PREF_GENERATE_LANGUAGE_INTERNAL_CHECKS, false);
    }

  }

  @Inject
  private IPreferenceStoreAccess preferenceStoreAccess;

  /**
   * Load builder preferences for check.
   *
   * @param generatorConfig
   *          the generator config, must not be {@code null}
   * @param context
   *          the context, must not be {@code null}
   */
  public void loadBuilderPreferences(final CheckGeneratorConfig generatorConfig, final Object context) {
    super.loadBuilderPreferences(generatorConfig, context);
    IPreferenceStore preferenceStore = preferenceStoreAccess.getContextPreferenceStore(context);
    generatorConfig.setGenerateLanguageInternalChecks(preferenceStore.getBoolean(PREF_GENERATE_LANGUAGE_INTERNAL_CHECKS));
  }

}
