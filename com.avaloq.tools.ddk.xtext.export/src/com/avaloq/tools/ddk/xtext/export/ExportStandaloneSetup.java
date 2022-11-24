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
package com.avaloq.tools.ddk.xtext.export;

/**
 * Initialization support for running Xtext languages
 * without equinox extension registry.
 */
public class ExportStandaloneSetup extends ExportStandaloneSetupGenerated {

  /**
   * Do the standard stand-alone setup operations: create an injector and do the EMF registration.
   */
  public static void doSetup() {
    new ExportStandaloneSetup().createInjectorAndDoEMFRegistration();
  }
}
