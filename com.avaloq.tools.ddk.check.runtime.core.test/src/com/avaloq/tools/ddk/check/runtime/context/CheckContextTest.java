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

import org.eclipse.emf.ecore.EObject;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;


/**
 * Provides some tests of the reflective {@link AbstractCheckContext} framework.
 */
public class CheckContextTest extends TestCase {

  public static final String ENABLED_ISSUE_CODE = "Enabled.Issue.Code";
  public static final String DISABLED_ISSUE_CODE = "Disabled.Issue.Code";
  public static final String DISABLED_AND_ENABLED_ISSUE_CODE = "Disabled.Enabled.Issue.Code";
  public static final String ENABLED_AND_DISABLED_ISSUE_CODE = "Enaabled.Disabled.Issue.Code";
  public static final String NOT_MENTIONED_ISSUE_CODE = "Not.Mentioned.Issue.Code";
  private static final EObject DUMMY_CONTEXT = null; // Wrap up null nicely

  /**
   * Tests that the {@link DummyCheckContext} properly marks issue codes as enabled and disabled.
   */
  @Test
  public void testIssuesEnabledDisabled() {
    ICheckContext checkContext = new DummyCheckContext();
    Assert.assertTrue("Check an issue code in annotations but enabled is still enabled", checkContext.isCheckValid(DUMMY_CONTEXT, ENABLED_ISSUE_CODE));
    Assert.assertTrue("Check an issue code not in any annotations is still enabled", checkContext.isCheckValid(DUMMY_CONTEXT, NOT_MENTIONED_ISSUE_CODE));
    Assert.assertFalse("Check an issue code in annotations is disabled", checkContext.isCheckValid(DUMMY_CONTEXT, DISABLED_ISSUE_CODE));
    Assert.assertFalse("Check disabling has priority over enabling", checkContext.isCheckValid(DUMMY_CONTEXT, DISABLED_AND_ENABLED_ISSUE_CODE));
    Assert.assertFalse("Check disabling has priority over enabling, using different order", checkContext.isCheckValid(DUMMY_CONTEXT, ENABLED_AND_DISABLED_ISSUE_CODE));

  }
}

