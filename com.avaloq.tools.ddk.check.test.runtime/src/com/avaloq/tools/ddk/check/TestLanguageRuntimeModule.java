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
package com.avaloq.tools.ddk.check;

import org.eclipse.xtext.parser.antlr.IPartialParsingHelper;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.service.SingletonBinding;

import com.avaloq.tools.ddk.check.resource.TestLanguageResourceDescriptionManager;
import com.avaloq.tools.ddk.check.runtime.validation.AbstractCheckValidator;
import com.avaloq.tools.ddk.check.validation.TestLanguageCheckValidator;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class TestLanguageRuntimeModule extends com.avaloq.tools.ddk.check.AbstractTestLanguageRuntimeModule {

  /**
   * Allows Check clients to register validators for this language.
   * 
   * @return the default check validator
   */
  @SingletonBinding(eager = true)
  public Class<? extends AbstractCheckValidator> bindAbstractCheckValidator() {
    return TestLanguageCheckValidator.class;
  }

  /**
   * Binds resource description manager.
   * 
   * @return {@link TestLanguageResourceDescriptionManager}
   */
  // CHECKSTYLE:OFF
  @SuppressWarnings("PMD.AvoidDollarSigns")
  public Class<? extends IResourceDescription.Manager> bindIResourceDescription$Manager() {
    // CHECKSTYLE:ON
    return TestLanguageResourceDescriptionManager.class;
  }
}

