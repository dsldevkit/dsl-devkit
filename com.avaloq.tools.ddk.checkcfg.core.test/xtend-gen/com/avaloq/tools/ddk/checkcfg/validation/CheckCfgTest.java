/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.validation;

import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.validation.IssueCodes;
import com.google.inject.Inject;
import junit.framework.TestCase;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(CheckCfgUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class CheckCfgTest extends TestCase {
  @Inject
  private ValidationTestHelper helper;
  
  @Inject
  private ParseHelper<CheckConfiguration> parser;
  
  @Test
  public void testValidLanguageOk() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("check configuration Test");
      _builder.newLine();
      _builder.newLine();
      _builder.append("for com.avaloq.tools.ddk.^check.TestLanguage {");
      _builder.newLine();
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      final CheckConfiguration model = this.parser.parse(_builder);
      this.helper.assertNoIssues(model);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testUnknownLanguageNotOk() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("check configuration Test");
      _builder.newLine();
      _builder.newLine();
      _builder.append("for com.avaloq.tools.ddk.^check.Unknown {");
      _builder.newLine();
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      final CheckConfiguration model = this.parser.parse(_builder);
      this.helper.assertError(model, CheckcfgPackage.Literals.CONFIGURED_LANGUAGE_VALIDATOR, IssueCodes.UNKNOWN_LANGUAGE);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
