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
package com.avaloq.tools.ddk.check.runtime.core.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.validation.AbstractCheckValidator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;


/**
 * Performs some basic validation tests on the {@link AbstractCheckValidator}.
 */
public class CheckValidatorTest extends AbstractCheckValidator {
  /**
   * Represents the dummy language which is also registered in the plugin.xml.
   */
  private static final String DUMMY_LANGUAGE_ID = "com.avaloq.tools.ddk.check.extensionpoint.tests.language";

  /**
   * Returns a dummy language ID.
   * 
   * @return the host language
   */
  @Override
  protected String getHostLanguage() {
    return DUMMY_LANGUAGE_ID;
  }

  /**
   * Tests that the dummy validator is found via the check validator extension point.
   * 
   * @see {@link com.avaloq.tools.ddk.check.extensionpoint.test.validation.DummyValidator}
   */
  @Test
  public void testAtLeastOneValidatorFound() {
    ICheckValidatorImpl dummyValidator = Iterables.getOnlyElement(getValidators(Maps.newHashMap(), null));
    assertEquals("Dummy validator found", "DummyValidator", dummyValidator.getClass().getSimpleName());
  }
}

