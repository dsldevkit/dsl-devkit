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
package com.avaloq.tools.ddk.check.core.test

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.check.XIssueExpression
import com.google.inject.Inject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.xbase.XbasePackage
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(typeof(CheckUiInjectorProvider))
@RunWith(typeof(XtextRunner))
class BugAig830 {

  @Inject
  ParseHelper<CheckCatalog> parser

  def private getModel() '''
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
  '''

  /* Tests that EPackages which are not of declared target language can be referenced. */
  @Test
  def void bugAig830() {
    val model = parser.parse(getModel())
    val issue = EcoreUtil2::getAllContentsOfType(model, typeof(XIssueExpression)).get(0)
    Assert::assertNotNull(issue.markerFeature)
    Assert::assertFalse(issue.markerFeature.eIsProxy)
    Assert::assertEquals(XbasePackage.Literals::XVARIABLE_DECLARATION, issue.markerFeature.EContainingClass)
  }

}
