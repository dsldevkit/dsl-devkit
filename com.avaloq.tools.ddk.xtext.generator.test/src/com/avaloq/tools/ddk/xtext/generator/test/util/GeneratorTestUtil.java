/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.generator.test.util;

import com.avaloq.tools.ddk.xtext.expression.ExpressionStandaloneSetup;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.google.inject.Injector;


public class GeneratorTestUtil extends AbstractXtextTestUtil {
  private static final GeneratorTestUtil INSTANCE = new GeneratorTestUtil();

  public static GeneratorTestUtil getInstance() {
    return INSTANCE;
  }

  @Override
  protected Injector getInjector() {
    return new ExpressionStandaloneSetup().createInjector();
  }

}

