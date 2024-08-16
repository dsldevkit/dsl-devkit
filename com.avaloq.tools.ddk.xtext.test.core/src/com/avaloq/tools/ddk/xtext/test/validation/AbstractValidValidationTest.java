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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.testing.validation.AssertableDiagnostics.Pred;
import org.eclipse.xtext.testing.validation.ValidatorTester;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.validation.AbstractDeclarativeValidValidator;
import com.google.common.collect.Iterables;
import com.google.inject.Injector;


/**
 * Base class for valid validation tests.
 */
@SuppressWarnings("nls")
public abstract class AbstractValidValidationTest extends AbstractValidationTest {

  // --------------------------------------------------------------------------
  // ValidAssertableDiagnostics
  // --------------------------------------------------------------------------

  /** The tester. */
  private ValidatorTester<?> tester;

  protected abstract Class<? extends AbstractDeclarativeValidValidator> getValidator();

  /**
   * Returns the validation tester generated for this test.
   *
   * @return the validation tester generated for this test
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  protected ValidatorTester<?> getTester() {
    if (tester == null) {
      AbstractXtextTestUtil testUtil = getTestUtil();
      tester = new ValidatorTester(testUtil.get(getValidator()), testUtil.get(Injector.class));
    }
    return tester;
  }

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
      getTester().validate(element).assertAny(d -> new Pred(null, null, issueCode, null).apply(d));
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
      if (!Iterables.isEmpty(Iterables.filter(getTester().validate(element).getAllDiagnostics(), d -> new Pred(null, null, issueCode, null).apply(d)))) {
        fail("predicate found");
      }
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
