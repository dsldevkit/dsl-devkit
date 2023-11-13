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
package com.avaloq.tools.ddk.xtext.test.valid.util;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.valid.ValidConstants;
import com.avaloq.tools.ddk.xtext.valid.ui.internal.ValidActivator;
import com.google.inject.Injector;


public class ValidTestUtil extends AbstractXtextTestUtil {
  private static final ValidTestUtil INSTANCE = new ValidTestUtil();

  public static ValidTestUtil getInstance() {
    return INSTANCE;
  }

  @Override
  protected Injector getInjector() {
    return ValidActivator.getInstance().getInjector(ValidConstants.GRAMMAR);
  }

}
