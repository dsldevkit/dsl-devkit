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
package com.avaloq.tools.ddk.check.core.test;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@InjectWith(CheckUiInjectorProvider.class)
@RunWith(XtextRunner.class)
@SuppressWarnings("all")
public class BugAig830 {
  @Inject
  private ParseHelper<CheckCatalog> parser;
  
  private CharSequence getModel() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package abc");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.xbase.XVariableDeclaration");
    _builder.newLine();
    _builder.append("catalog Abc");
    _builder.newLine();
    _builder.append("for grammar com.avaloq.tools.ddk.check.Check {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("live error \"Test\" {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("for XVariableDeclaration v {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("issue on v#name");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Tests that EPackages which are not of declared target language can be referenced.
   */
  @Test
  public void bugAig830() {
    try {
      CharSequence _model = this.getModel();
      final CheckCatalog model = this.parser.parse(_model);
      List<XIssueExpression> _allContentsOfType = EcoreUtil2.<XIssueExpression>getAllContentsOfType(model, XIssueExpression.class);
      final XIssueExpression issue = _allContentsOfType.get(0);
      EStructuralFeature _markerFeature = issue.getMarkerFeature();
      Assert.assertNotNull(_markerFeature);
      EStructuralFeature _markerFeature_1 = issue.getMarkerFeature();
      boolean _eIsProxy = _markerFeature_1.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      EStructuralFeature _markerFeature_2 = issue.getMarkerFeature();
      EClass _eContainingClass = _markerFeature_2.getEContainingClass();
      Assert.assertEquals(XbasePackage.Literals.XVARIABLE_DECLARATION, _eContainingClass);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
