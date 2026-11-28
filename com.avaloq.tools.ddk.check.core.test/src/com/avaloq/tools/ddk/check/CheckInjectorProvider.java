/*******************************************************************************
 * Copyright (c) 2017 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check;

import org.eclipse.xtext.testing.GlobalRegistries;
import org.eclipse.xtext.testing.GlobalRegistries.GlobalStateMemento;
import org.eclipse.xtext.testing.IInjectorProvider;
import org.eclipse.xtext.testing.IRegistryConfigurator;

import com.google.inject.Injector;


/**
 * Provides the injector of the Check language, created by {@link CheckStandaloneSetup}.
 */
public class CheckInjectorProvider implements IInjectorProvider, IRegistryConfigurator {

  private GlobalStateMemento stateBeforeInjectorCreation;
  private GlobalStateMemento stateAfterInjectorCreation;
  private Injector injector;

  static {
    GlobalRegistries.initializeDefaults();
  }

  @Override
  public Injector getInjector() {
    if (injector == null) {
      stateBeforeInjectorCreation = GlobalRegistries.makeCopyOfGlobalState();
      this.injector = internalCreateInjector();
      stateAfterInjectorCreation = GlobalRegistries.makeCopyOfGlobalState();
    }
    return injector;
  }

  /**
   * Creates the injector and registers the Check language with EMF.
   *
   * @return the {@link Injector}
   */
  protected Injector internalCreateInjector() {
    return new CheckStandaloneSetup().createInjectorAndDoEMFRegistration();
  }

  @Override
  public void restoreRegistry() {
    stateBeforeInjectorCreation.restoreGlobalState();
  }

  @Override
  public void setupRegistry() {
    getInjector();
    stateAfterInjectorCreation.restoreGlobalState();
  }
}
