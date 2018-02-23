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
package com.avaloq.tools.ddk.xtext.test.validation;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;


/**
 * Helper methods relating to model validation.
 */
public class ValidationHelper extends ValidationTestHelper {

  /**
   * Validates the provided document and returns a list of issues found.
   *
   * @param document
   *          to validate
   * @return list of issues found
   */
  public List<Issue> getIssues(final IXtextDocument document) {
    return document.readOnly(state -> state.getResourceServiceProvider().getResourceValidator().validate(state, CheckMode.ALL, null));
  }

  /**
   * Assert no {@link org.eclipse.emf.ecore.resource.Resource#getErrors() syntax or linking errors} exist in the resource of the given object.
   *
   * @param obj
   *          the object to test for errors
   */
  public void assertNoSyntaxOrLinkingErrors(final EObject obj) {
    AbstractValidationTest.assertNoErrorsOnResource(obj);
  }

}
