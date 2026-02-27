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
package com.avaloq.tools.ddk.check.compiler;

import org.eclipse.xtext.xbase.compiler.GeneratorConfig;

public class CheckGeneratorConfig extends GeneratorConfig {

  private final String GENERATE_DOCUMENTATION_PROPERTY = "com.avaloq.tools.ddk.check.GenerateDocumentationForAllChecks";

  private boolean generateLanguageInternalChecks = false;

  public boolean isGenerateLanguageInternalChecks() {
    return generateLanguageInternalChecks;
  }

  public void setGenerateLanguageInternalChecks(boolean generateLanguageInternalChecks) {
    this.generateLanguageInternalChecks = generateLanguageInternalChecks;
  }

  public boolean doGenerateDocumentationForAllChecks() {
    return Boolean.parseBoolean(System.getProperty(GENERATE_DOCUMENTATION_PROPERTY));
  }
}
