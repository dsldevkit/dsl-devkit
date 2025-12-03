/*******************************************************************************
 * Copyright (c) 2025 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.xtext.XtextRuntimeModule;
import org.eclipse.xtext.resource.IFragmentProvider;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.eclipse.xtext.util.Modules2;

import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProviderTest.TestSelectorFragmentProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;


public class AbstractXtextTestsInjectorProvider implements IInjectorProvider {

  @Override
  public Injector getInjector() {
    return Guice.createInjector(Modules2.mixin(new XtextRuntimeModule(), new AbstractModule() {
      @Override
      protected void configure() {
        bind(IFragmentProvider.class).to(TestSelectorFragmentProvider.class);
      }
    }));
  }
}
