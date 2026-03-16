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
package com.avaloq.tools.ddk.check.core.test;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.google.inject.Inject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.XbasePackage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class BugAig830 {

  @Inject
  private ParseHelper<CheckCatalog> parser;

  private String getModel() {
    return """
        package abc
        import org.eclipse.xtext.xbase.XVariableDeclaration
        catalog Abc
        for grammar com.avaloq.tools.ddk.check.Check {
          live error "Test" {
            for XVariableDeclaration v {
              issue on v#name
            }
          }
        }
        """;
  }

  /* Tests that EPackages which are not of declared target language can be referenced. */
  @Test
  public void bugAig830() throws Exception {
    final CheckCatalog model = parser.parse(getModel());
    final XIssueExpression issue = EcoreUtil2.getAllContentsOfType(model, XIssueExpression.class).get(0);
    assertNotNull(issue.getMarkerFeature());
    assertFalse(issue.getMarkerFeature().eIsProxy());
    assertEquals(XbasePackage.Literals.XVARIABLE_DECLARATION, issue.getMarkerFeature().getEContainingClass());
  }
}
