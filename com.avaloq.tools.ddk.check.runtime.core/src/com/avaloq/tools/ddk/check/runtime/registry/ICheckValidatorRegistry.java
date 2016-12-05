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
package com.avaloq.tools.ddk.check.runtime.registry;

import java.util.Collection;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.registry.impl.CheckValidatorRegistryImpl;


/**
 * Registry used for collecting all validators in a non-OSGi environment.
 */
public interface ICheckValidatorRegistry extends ICheckImplDescriptorRegistry {

  /** The Constant INSTANCE. */
  ICheckValidatorRegistry INSTANCE = new CheckValidatorRegistryImpl();

  /**
   * Get validators for a given language.
   * 
   * @param language
   *          language name
   * @return
   *         collection of registered validators
   */
  Collection<ICheckValidatorImpl> getValidators(final String language);

  /**
   * Get validators for a given language.
   * 
   * @return
   *         collection of registered validators
   */
  Collection<ICheckValidatorImpl> getValidators();

  /**
   * Add a validator. Multiple validators can be registered. Duplicates are not checked.
   * 
   * @param language
   *          Language name to add validator for.
   * @param validator
   *          validator for the given language
   */
  void registerValidator(final String language, final ICheckValidatorImpl validator);

  /**
   * Clear the list of registered providers.
   */
  void removeAllValidators();

  /**
   * Check if there are any validators registered.
   * 
   * @return
   *         {@code TRUE} if there are any validators registered, {@code FALSE} otherwise.
   */
  boolean isEmpty();

}

