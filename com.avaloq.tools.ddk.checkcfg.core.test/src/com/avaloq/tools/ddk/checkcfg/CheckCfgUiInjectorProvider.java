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

package com.avaloq.tools.ddk.checkcfg;

import org.eclipse.xtext.testing.IInjectorProvider;

import com.google.inject.Injector;


public class CheckCfgUiInjectorProvider implements IInjectorProvider {

  @Override
  public Injector getInjector() {
    return com.avaloq.tools.ddk.checkcfg.ui.internal.CheckcfgActivator.getInstance().getInjector("com.avaloq.tools.ddk.checkcfg.CheckCfg"); //$NON-NLS-1$
  }

}
