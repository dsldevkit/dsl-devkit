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
package com.avaloq.tools.ddk.check.core.test.util;

import org.eclipse.xtext.testing.GlobalRegistries;
import org.eclipse.xtext.testing.GlobalRegistries.GlobalStateMemento;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;


/**
 * {@link CheckInjectorProvider} which restores the global EMF registries to their state before each test.
 * <p>
 * The generated provider restores the state from before its first use instead, which wipes registrations made since by other bundles, e.g. the
 * Check validator registered by the Check UI plug-in, and breaks later tests using the UI injector.
 */
public class RegistryIsolatingCheckInjectorProvider extends CheckInjectorProvider {

  private GlobalStateMemento stateBeforeTest;

  @Override
  public void setupRegistry() {
    stateBeforeTest = GlobalRegistries.makeCopyOfGlobalState();
    super.setupRegistry();
  }

  @Override
  public void restoreRegistry() {
    stateBeforeTest.restoreGlobalState();
  }

}
