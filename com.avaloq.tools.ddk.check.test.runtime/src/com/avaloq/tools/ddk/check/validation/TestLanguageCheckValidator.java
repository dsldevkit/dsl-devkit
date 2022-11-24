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
package com.avaloq.tools.ddk.check.validation;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.validation.DefaultCheckValidator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * A check validator that can manually register generated check validators. We should move the CompilerCatalog annotation to DSL-SDK and handle it
 * inside DefaultCheckValidator.
 */
public class TestLanguageCheckValidator extends DefaultCheckValidator {

  @Inject
  private Injector injector;

  @Override
  protected Iterable<? extends ICheckValidatorImpl> internalCollectValidators(final String language, final EObject object) {
    Iterable<? extends ICheckValidatorImpl> result = super.internalCollectValidators(language, object);
    // Add our own check validators.
    return Iterables.concat(builtInCheckValidators(), result);
  }

  /**
   * Returns a list of built-in check validators.
   * 
   * @return the built-in check validators.
   */
  private Iterable<? extends ICheckValidatorImpl> builtInCheckValidators() {
    List<ICheckValidatorImpl> result = Lists.newArrayList();
    ICheckValidatorImpl instance = injector.getInstance(ExecutionEnvironmentCheckImpl.class);
    if (instance != null) {
      result.add(instance);
    }
    instance = injector.getInstance(LibraryChecksCheckImpl.class);
    if (instance != null) {
      result.add(instance);
    }
    return result;
  }
}

