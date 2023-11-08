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
package com.avaloq.tools.ddk.check.runtime.configuration;

import java.util.List;


/**
 * Represents a strategy to access check configuration preferences by preference
 * keys. All operations will return given default values if no preference found
 * for given keys.
 */
public interface ICheckConfigurationStore {

  /**
   * Return the value stored in the configuration store for the given key.
   * If the key is not defined then return the specified default value.
   * Use the canonical scope lookup order for finding the preference value.
   *
   * @param key
   *          the name of the preference (optionally including its path)
   * @param defaultValue
   *          the value to use if the preference is not defined
   * @return the value of the preference or the given default value
   * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer
   */
  boolean getBoolean(String key, boolean defaultValue);

  /**
   * Return the value stored in the configuration store for the given key.
   * If the key is not defined then return the specified default value.
   * Use the canonical scope lookup order for finding the preference value.
   *
   * @param key
   *          the name of the preference (optionally including its path)
   * @param defaultValue
   *          the value to use if the preference is not defined
   * @return the value of the preference or the given default value
   */
  int getInt(String key, int defaultValue);

  /**
   * Return the value stored in the configuration store for the given key.
   * If the key is not defined then return the specified default value.
   * Use the canonical scope lookup order for finding the preference value.
   *
   * @param key
   *          the name of the preference (optionally including its path)
   * @param defaultValue
   *          the value to use if the preference is not defined
   * @return the value of the preference or the given default value
   */
  String getString(String key, String defaultValue);

  /**
   * Gets a list of strings from the preference identified by the key. If the preference is not set, returns the default value given.
   *
   * @param key
   *          to look for
   * @param defaultValue
   *          to use if the preference identified by the key is not set
   * @return the value of the preference, or the default value if the preference is not set.
   */
  List<String> getStrings(String key, List<String> defaultValue);

  /**
   * Gets a list of booleans from the preference identified by the key. If the preference is not set, returns the default value given.
   *
   * @param key
   *          to look for
   * @param defaultValue
   *          to use if the preference identified by the key is not set
   * @return the value of the preference, or the default value if the preference is not set.
   */
  List<Boolean> getBooleans(String key, List<Boolean> defaultValue);

  /**
   * Gets a list of integers from the preference identified by the key. If the preference is not set, returns the default value given.
   *
   * @param key
   *          to look for
   * @param defaultValue
   *          to use if the preference identified by the key is not set
   * @return the value of the preference, or the default value if the preference is not set.
   */
  List<Integer> getIntegers(String key, List<Integer> defaultValue);

}
