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
package com.avaloq.tools.ddk.check.generator;

import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;

import com.avaloq.tools.ddk.xtext.generator.GeneratorModule;


/**
 * Generator module of GenerateTestLanguage.mwe2.
 */
public class TestLanguageGeneratorModule extends GeneratorModule {

  @Override
  public Class<? extends XtextGeneratorNaming> bindXtextGeneratorNaming() {
    return TestLanguageGeneratorNaming.class;
  }
}
