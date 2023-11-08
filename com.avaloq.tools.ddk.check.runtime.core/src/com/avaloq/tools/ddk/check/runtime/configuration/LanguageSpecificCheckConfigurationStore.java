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
 * Wrapper for check preference store. First checks if there is a language specific configuration.
 */
public class LanguageSpecificCheckConfigurationStore implements ICheckConfigurationStore {

  private final ICheckConfigurationStore delegatingStore;
  private final String language;

  public LanguageSpecificCheckConfigurationStore(final ICheckConfigurationStore delegatingStore, final String language) {
    this.delegatingStore = delegatingStore;
    this.language = language;
  }

  /**
   * Gets the language specific key.
   *
   * @param language
   *          the language
   * @param key
   *          the key
   * @return the language specific key
   */
  public static String getLanguageSpecificKey(final String language, final String key) {
    return language + '$' + key;
  }

  @Override
  public boolean getBoolean(final String key, final boolean defaultValue) {

    boolean standardValue = delegatingStore.getBoolean(key, defaultValue);
    return delegatingStore.getBoolean(getLanguageSpecificKey(language, key), standardValue);
  }

  @Override
  public int getInt(final String key, final int defaultValue) {
    int standardValue = delegatingStore.getInt(key, defaultValue);
    return delegatingStore.getInt(getLanguageSpecificKey(language, key), standardValue);
  }

  @Override
  public String getString(final String key, final String defaultValue) {
    String standardValue = delegatingStore.getString(key, defaultValue);
    return delegatingStore.getString(getLanguageSpecificKey(language, key), standardValue);
  }

  @Override
  public List<String> getStrings(final String key, final List<String> defaultValue) {
    List<String> standardValue = delegatingStore.getStrings(key, defaultValue);
    return delegatingStore.getStrings(getLanguageSpecificKey(language, key), standardValue);
  }

  @Override
  public List<Boolean> getBooleans(final String key, final List<Boolean> defaultValue) {
    List<Boolean> standardValue = delegatingStore.getBooleans(key, defaultValue);
    return delegatingStore.getBooleans(getLanguageSpecificKey(language, key), standardValue);
  }

  @Override
  public List<Integer> getIntegers(final String key, final List<Integer> defaultValue) {
    List<Integer> standardValue = delegatingStore.getIntegers(key, defaultValue);
    return delegatingStore.getIntegers(getLanguageSpecificKey(language, key), standardValue);
  }

}
