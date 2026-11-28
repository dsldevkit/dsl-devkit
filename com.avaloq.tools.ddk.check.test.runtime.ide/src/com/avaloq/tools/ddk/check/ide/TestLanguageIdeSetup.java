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
package com.avaloq.tools.ddk.check.ide;

import org.eclipse.xtext.util.Modules2;

import com.avaloq.tools.ddk.check.TestLanguageRuntimeModule;
import com.avaloq.tools.ddk.check.TestLanguageStandaloneSetup;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Initialization support for running Xtext languages as language servers.
 */
public class TestLanguageIdeSetup extends TestLanguageStandaloneSetup {

  @Override
  public Injector createInjector() {
    return Guice.createInjector(Modules2.mixin(new TestLanguageRuntimeModule(), new TestLanguageIdeModule()));
  }

}
