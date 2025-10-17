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
package com.avaloq.tools.ddk.check.ui.test.internal;

import org.eclipse.xtext.testing.IInjectorProvider;

import com.google.inject.Injector;


/**
 * The Class CheckWizardUiTestInjectorProvider is a copy of com/avaloq/tools/ddk/check/CheckUiInjectorProvider.java.
 */
public class CheckWizardUiTestInjectorProvider implements IInjectorProvider {

  @Override
  public Injector getInjector() {
    return com.avaloq.tools.ddk.check.ui.internal.CheckActivator.getInstance().getInjector("com.avaloq.tools.ddk.check.Check"); //$NON-NLS-1$
  }
}
