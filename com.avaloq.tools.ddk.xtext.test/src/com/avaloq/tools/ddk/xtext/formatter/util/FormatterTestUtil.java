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
package com.avaloq.tools.ddk.xtext.formatter.util;

import com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguageStandaloneSetup;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.google.inject.Injector;


public class FormatterTestUtil extends AbstractXtextTestUtil {
  private static final FormatterTestUtil INSTANCE = new FormatterTestUtil();
  private final Injector injector = new FormatterTestLanguageStandaloneSetup().createInjectorAndDoEMFRegistration();

  public static FormatterTestUtil getInstance() {
    return INSTANCE;
  }

  @Override
  protected Injector getInjector() {
    return injector;
  }

}

