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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.junit4.validation.AssertableDiagnostics;
import org.eclipse.xtext.junit4.validation.AssertableDiagnostics.DiagnosticPredicate;
import org.eclipse.xtext.junit4.validation.ValidatorTester;
import org.eclipse.xtext.validation.AbstractValidationDiagnostic;

import com.google.common.collect.Iterables;


/**
 * Base class for valid validation tests.
 */
public abstract class AbstractValidValidationTest extends AbstractValidationTest {

  // --------------------------------------------------------------------------
  // ValidAssertableDiagnostics
  // --------------------------------------------------------------------------

  /**
   * This class can be removed once https://bugs.eclipse.org/bugs/show_bug.cgi?id=374743 is solved.
   */
  protected static class ValidAssertableDiagnostics extends AssertableDiagnostics {

    public ValidAssertableDiagnostics(final Diagnostic diag) {
      super(diag);
      throw new UnsupportedOperationException();
    }

    /**
     * Returns predicate matching any diagnostic with given issue code and message fragment.
     *
     * @param issueCode
     *          issue code to match
     * @param messageFragment
     *          message fragment (substring) to match
     * @return predicate
     */
    public static Pred diagnostic(final String issueCode, final String messageFragment) {
      return new Pred(null, null, issueCode, messageFragment);
    }

    /**
     * Returns predicate matching any diagnostic with given issue code.
     *
     * @param issueCode
     *          issue code to match
     * @return predicate
     */
    public static Pred diagnostic(final String issueCode) {
      return new Pred(null, null, issueCode, null);
    }
  }

  /**
   * Returns the validation tester generated for this test.
   *
   * @return the validation tester generated for this test
   */
  protected abstract ValidatorTester<?> getTester();

  // --------------------------------------------------------------------------
  // Validation methods
  // --------------------------------------------------------------------------

  /**
   * Validate element against issue codes.
   * Test will fail if the validator did NOT fail with any of the issue codes.
   * i.e. this test expects a validation issue
   *
   * @param element
   *          the element
   * @param issueCodes
   *          the issue codes
   */
  protected void validate(final EObject element, final String... issueCodes) {
    for (String issueCode : issueCodes) {
      getTester().validate(element).assertAny(ValidAssertableDiagnostics.diagnostic(issueCode));
    }
  }

  /**
   * Validate element against issue codes.
   * Test will fail if the validator did fail with any of the issue codes.
   * i.e. this test does not expect a validation issue
   *
   * @param element
   *          the element
   * @param issueCodes
   *          the issue codes
   */
  protected void validateNot(final EObject element, final String... issueCodes) {
    for (String issueCode : issueCodes) {
      assertNot(getTester().validate(element), ValidAssertableDiagnostics.diagnostic(issueCode));
    }
  }

  /**
   * Checks that there is no occurrence of a diagnostic predicate in a diagnostic list.
   *
   * @param validate
   *          the diagnostic list in which to look for a diagnostic predicate
   * @param predicate
   *          the predicate diagnostic to look for
   */
  private void assertNot(final AssertableDiagnostics validate, final DiagnosticPredicate predicate) {
    if (Iterables.any(Iterables.filter(validate.getAllDiagnostics(), AbstractValidationDiagnostic.class), predicate)) {
      fail("Predicate " + predicate.toString() + " found.");
    }
  }

  /**
   * Validate element has no issues.
   * Test will fail if any issue found for element.
   *
   * @param element
   *          the element
   */
  protected void validateOK(final EObject element) {
    assertFalse("Element " + element.toString() + " has validation errors.", getTester().validate(element).getAllDiagnostics().iterator().hasNext());
  }

}
