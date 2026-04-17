/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.builder;

import static org.mockito.Mockito.mock;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.xtext.builder.impl.BuildScheduler;
import org.eclipse.xtext.testing.IInjectorProvider;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;


public class XtextBuilderInjectorProvider implements IInjectorProvider {

  @Override
  public Injector getInjector() {
    return Guice.createInjector(new AbstractModule() {
      @SuppressWarnings({"deprecation", "restriction"})
      @Override
      protected void configure() {
        bind(BuildScheduler.class).toInstance(mock(BuildScheduler.class));
        bind(IWorkspace.class).toInstance(mock(IWorkspace.class));
      }
    });
  }

}
