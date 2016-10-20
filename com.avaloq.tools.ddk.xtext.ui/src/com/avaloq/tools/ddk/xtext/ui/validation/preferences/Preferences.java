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
package com.avaloq.tools.ddk.xtext.ui.validation.preferences;

import com.avaloq.tools.ddk.xtext.ui.Activator;


/**
 * The Class Preferences defines utility functions for Xtend extensions to check
 * the Valid Preferences properties for rules generated in the Check language.
 */
public final class Preferences {

  /**
   * Private constructor (no instances of this class are allowed).
   */
  private Preferences() {}

  /**
   * Checks if the rule corresponding to the given key is enabled.
   *
   * @param preferenceKey
   *          the preference key
   * @return true, if is enabled
   */
  public static boolean isEnabled(final String preferenceKey) {
    return !isDisabled(preferenceKey);
  }

  /**
   * Checks whether the rule corresponding to the given key is disabled.
   *
   * @param preferenceKey
   *          the preference key
   * @return true, if is disabled
   */
  public static boolean isDisabled(final String preferenceKey) {
    // remember: the preference is whether the key is disabled (true) or not
    // (false)
    return Activator.getDefault().getPreferenceStore().getBoolean(preferenceKey);
  }

}
