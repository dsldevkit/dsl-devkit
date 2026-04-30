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
package com.avaloq.tools.ddk.xtext.test.validation;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;

import com.google.common.collect.Lists;


/**
 * Helper methods relating to model validation.
 */
public class ValidationHelper extends ValidationTestHelper {

  private static final String NO_ERRORS_FOUND_ON_RESOURCE_MESSAGE = "Expected no errors on resource"; //$NON-NLS-1$

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
    final EList<Resource.Diagnostic> errors = obj.eResource().getErrors();
    if (!errors.isEmpty()) {
      fail(NO_ERRORS_FOUND_ON_RESOURCE_MESSAGE + "; found " + Lists.transform(errors, Resource.Diagnostic::getMessage)); //$NON-NLS-1$
    }
  }

}
