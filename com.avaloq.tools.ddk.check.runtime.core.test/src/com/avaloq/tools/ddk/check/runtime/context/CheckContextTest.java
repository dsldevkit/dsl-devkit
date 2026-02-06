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
package com.avaloq.tools.ddk.check.runtime.context;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;


/**
 * Provides some tests of the reflective {@link AbstractCheckContext} framework.
 */
@SuppressWarnings("nls")
class CheckContextTest {

  static final String ENABLED_ISSUE_CODE = "Enabled.Issue.Code";
  static final String DISABLED_ISSUE_CODE = "Disabled.Issue.Code";
  static final String DISABLED_AND_ENABLED_ISSUE_CODE = "Disabled.Enabled.Issue.Code";
  static final String ENABLED_AND_DISABLED_ISSUE_CODE = "Enaabled.Disabled.Issue.Code";
  static final String NOT_MENTIONED_ISSUE_CODE = "Not.Mentioned.Issue.Code";
  private static final EObject DUMMY_CONTEXT = null; // Wrap up null nicely

  /**
   * Tests that the {@link DummyCheckContext} properly marks issue codes as enabled and disabled.
   */
  @Test
  void testIssuesEnabledDisabled() {
    ICheckContext checkContext = new DummyCheckContext();
    assertTrue(checkContext.isCheckValid(DUMMY_CONTEXT, ENABLED_ISSUE_CODE), "Check an issue code in annotations but enabled is still enabled");
    assertTrue(checkContext.isCheckValid(DUMMY_CONTEXT, NOT_MENTIONED_ISSUE_CODE), "Check an issue code not in any annotations is still enabled");
    assertFalse(checkContext.isCheckValid(DUMMY_CONTEXT, DISABLED_ISSUE_CODE), "Check an issue code in annotations is disabled");
    assertFalse(checkContext.isCheckValid(DUMMY_CONTEXT, DISABLED_AND_ENABLED_ISSUE_CODE), "Check disabling has priority over enabling");
    assertFalse(checkContext.isCheckValid(DUMMY_CONTEXT, ENABLED_AND_DISABLED_ISSUE_CODE), "Check disabling has priority over enabling, using different order");

  }
}
