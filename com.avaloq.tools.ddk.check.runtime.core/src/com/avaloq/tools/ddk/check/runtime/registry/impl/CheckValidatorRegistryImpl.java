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
package com.avaloq.tools.ddk.check.runtime.registry.impl;

import java.util.Collection;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckImplDescriptor;
import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.inject.Provider;


/**
 * Provides a registry of Check validators. Is required for the standalone builder
 * since extension points cannot be used in a non-Eclipse environment.
 */
@SuppressWarnings("unchecked")
public class CheckValidatorRegistryImpl extends AbstractCheckImplDescriptorRegistry implements ICheckValidatorRegistry {

  private final Multimap<String, ICheckValidatorImpl> concreteValidators = HashMultimap.create();

  @Override
  public Collection<ICheckValidatorImpl> getValidators(final String language) {
    Collection<ICheckValidatorImpl> validators = Lists.newArrayList(concreteValidators.get(language));
    for (ICheckImplDescriptor v : Sets.newHashSet(getDescriptors(language))) {
      if (v instanceof Provider<?>) {
        final ICheckValidatorImpl validator = ((Provider<ICheckValidatorImpl>) v).get();
        if (validator == null) { // may be null if specified target class could not be found
          removeLanguageDescriptor(language, v);
        } else {
          validators.add(validator);
        }
      }
    }
    return validators;
  }

  @Override
  public Collection<ICheckValidatorImpl> getValidators() {
    Collection<ICheckValidatorImpl> validators = Lists.newArrayList(concreteValidators.values());
    for (ICheckImplDescriptor v : Sets.newHashSet(getDescriptors())) {
      if (v instanceof Provider<?>) {
        final ICheckValidatorImpl validator = ((Provider<ICheckValidatorImpl>) v).get();
        if (validator == null) { // may be null if specified target class could not be found
          // Only removes if language was null. Legacy implementation with non-null language will be removed when trying to
          // activate the catalog for that language explicitly through legacy mechanism
          removeLanguageDescriptor(v);
        } else {
          validators.add(validator);
        }
      }
    }
    return validators;
  }

  @Override
  public void registerValidator(final String language, final ICheckValidatorImpl validator) {
    concreteValidators.put(language, validator);
  }

  @Override
  public void removeAllValidators() {
    concreteValidators.clear();
  }

  @Override
  public boolean isEmpty() {
    return concreteValidators.isEmpty();
  }

  @Override
  public void registerValidator(final ICheckValidatorImpl validator) {
    concreteValidators.put(null, validator);
  }

}
