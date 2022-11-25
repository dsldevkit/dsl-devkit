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
package com.avaloq.tools.ddk.xtext.valid;

import org.eclipse.emf.ecore.EPackage;

import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;
import com.google.inject.Injector;


/**
 * Initialization support for running Xtext languages without equinox extension registry.
 */
public class ValidStandaloneSetup extends ValidStandaloneSetupGenerated {

  /** Do setup and EMF registration. */
  public static void doSetup() {
    new ValidStandaloneSetup().createInjectorAndDoEMFRegistration();
  }

  @Override
  public void register(final Injector injector) {
    EPackage.Registry.INSTANCE.put(ValidPackage.eINSTANCE.getNsURI(), ValidPackage.eINSTANCE);
    super.register(injector);
  }
}
