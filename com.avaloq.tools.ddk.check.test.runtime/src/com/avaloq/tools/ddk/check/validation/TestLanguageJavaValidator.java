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
package com.avaloq.tools.ddk.check.validation;

import org.eclipse.xtext.validation.Check;

import com.avaloq.tools.ddk.check.testLanguage.Greeting;
import com.avaloq.tools.ddk.check.testLanguage.TestLanguagePackage;

//CHECKSTYLE:OFF generated code


/**
 * The Class TestLanguageJavaValidator.
 */
public class TestLanguageJavaValidator extends AbstractTestLanguageJavaValidator {

  @Check
  public void checkGreetingStartsWithCapital(final Greeting greeting) {
    if (greeting.getName() != null && greeting.getName().startsWith("Greeting")) {
      warning("Name should not start with \"Greeting\"", TestLanguagePackage.Literals.GREETING__NAME, IssueCodes.GREETING_NAME_PREFIX);
    }
  }

}
