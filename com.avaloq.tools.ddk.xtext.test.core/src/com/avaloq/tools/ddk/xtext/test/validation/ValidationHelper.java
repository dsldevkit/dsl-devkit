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

import static org.junit.Assert.fail;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


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
    return document.readOnly(new IUnitOfWork<List<Issue>, XtextResource>() {
      public List<Issue> exec(final XtextResource state) {
        return state.getResourceServiceProvider().getResourceValidator().validate(state, CheckMode.ALL, null);
      }
    });
  }

  /**
   * Assert no {@link Resource#getErrors() syntax or linking errors} exist in the resource of the given object.
   * 
   * @param obj
   *          the object to test for errors
   */
  public void assertNoSyntaxOrLinkingErrors(final EObject obj) {
    final EList<Resource.Diagnostic> errors = obj.eResource().getErrors();
    if (!errors.isEmpty()) {
      fail(AbstractValidationTest.NO_ERRORS_FOUND_ON_RESOURCE_MESSAGE + "; found " + getErrorMessages(errors));
    }
  }

  /**
   * Gets the error messages.
   * 
   * @param errors
   *          the errors
   * @return the error messages
   */
  List<String> getErrorMessages(final EList<Resource.Diagnostic> errors) {
    List<String> messages = Lists.newArrayList(Iterables.transform(errors, new Function<Resource.Diagnostic, String>() {
      public String apply(final Resource.Diagnostic input) {
        return input.getMessage();
      }
    }));
    return messages;
  }

}

