/**
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Group AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.validation;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.google.inject.Inject;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xtype.XtypePackage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckApiAccessValidationsTest {

  @Inject
  private ParseHelper<CheckCatalog> parser;

  @Inject
  private ValidationTestHelper helper;

  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  private CheckCatalog getTestSource(final String importText) throws Exception {
    return parser.parse(
        "package com.avaloq.example.stuff.checks\n"
            + "import " + importText + "\n"
            + "catalog CommonChecks\n"
            + "for grammar com.avaloq.tools.ddk.check.TestLanguage\n"
            + "{\n"
            + "}");
  }

  @Test
  public void testNonApiAccessDisallowed() throws Exception {
    final CheckCatalog model = getTestSource("com.avaloq.tools.ddk.check.check.impl.CheckImpl"); //Not OK - impl not defined as API.
    helper.assertWarning(model, XtypePackage.Literals.XIMPORT_DECLARATION, IssueCodes.NON_API_IMPORTED);
  }

  @Test
  public void testDefinedApiAccessable() throws Exception {
    final CheckCatalog model = getTestSource("com.avaloq.tools.ddk.check.check.Check"); //OK! There's an API spec in this plugin's plugin.xml
    helper.assertNoIssue(model, XtypePackage.Literals.XIMPORT_DECLARATION, IssueCodes.NON_API_IMPORTED);
  }

  @Test
  public void testNonAvaloqTypeAccessable() throws Exception {
    final CheckCatalog model = getTestSource("java.util.HashMap"); //OK! Not in com.avaloq.*
    helper.assertNoIssue(model, XtypePackage.Literals.XIMPORT_DECLARATION, IssueCodes.NON_API_IMPORTED);
  }
}
