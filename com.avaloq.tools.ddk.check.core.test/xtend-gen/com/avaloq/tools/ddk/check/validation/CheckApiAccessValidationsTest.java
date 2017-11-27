/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xtype.XtypePackage;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(CheckUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class CheckApiAccessValidationsTest {
  @Inject
  private ParseHelper<CheckCatalog> parser;
  
  @Inject
  private ValidationTestHelper helper;
  
  private CheckCatalog getTestSource(final String importText) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package com.avaloq.example.stuff.checks");
      _builder.newLine();
      _builder.append("import ");
      _builder.append(importText, "");
      _builder.newLineIfNotEmpty();
      _builder.append("catalog CommonChecks");
      _builder.newLine();
      _builder.append("for grammar com.avaloq.tools.ddk.check.TestLanguage");
      _builder.newLine();
      _builder.append("{");
      _builder.newLine();
      _builder.append("}");
      return this.parser.parse(_builder);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNonApiAccessDisallowed() {
    final CheckCatalog model = this.getTestSource("com.avaloq.tools.ddk.check.check.impl.CheckImpl");
    this.helper.assertWarning(model, XtypePackage.Literals.XIMPORT_DECLARATION, IssueCodes.NON_API_IMPORTED);
  }
  
  @Test
  public void testDefinedApiAccessable() {
    final CheckCatalog model = this.getTestSource("com.avaloq.tools.ddk.check.check.Check");
    this.helper.assertNoIssue(model, XtypePackage.Literals.XIMPORT_DECLARATION, IssueCodes.NON_API_IMPORTED);
  }
  
  @Test
  public void testNonAvaloqTypeAccessable() {
    final CheckCatalog model = this.getTestSource("java.util.HashMap");
    this.helper.assertNoIssue(model, XtypePackage.Literals.XIMPORT_DECLARATION, IssueCodes.NON_API_IMPORTED);
  }
}
