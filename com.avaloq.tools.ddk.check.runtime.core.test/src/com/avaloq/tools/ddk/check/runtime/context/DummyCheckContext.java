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


/**
 * Dummy implementation of the {@link ICheckContext} interface for testing purposes. Contains predicates for three issue codes
 */
public class DummyCheckContext extends AbstractCheckContext {

  /**
   * Enables check for this code. Not a pattern to be followed in real operations, since not having the code annotated has the same effect.
   *
   * @param context
   *          Model element context for validation
   * @return {@code true} always
   */
  @CheckContext(issueCodes = {CheckContextTest.ENABLED_AND_DISABLED_ISSUE_CODE})
  public boolean enableForEnabledDisabledCode(final EObject context) {
    return ENABLED;
  }

  /**
   * Disables checks for these three codes.
   *
   * @param context
   *          Model element context for validation
   * @return {@code false} always
   */
  @CheckContext(issueCodes = {CheckContextTest.DISABLED_ISSUE_CODE, CheckContextTest.DISABLED_AND_ENABLED_ISSUE_CODE,
      CheckContextTest.ENABLED_AND_DISABLED_ISSUE_CODE})
  public boolean disableForCodes(final EObject context) {
    return DISABLED;
  }

  /**
   * Enables checks for these two codes. Not a pattern to be followed in real operations, since not having the code annotated has the same effect.
   *
   * @param context
   *          Model element context for validation
   * @return {@code true} always
   */
  @CheckContext(issueCodes = {CheckContextTest.ENABLED_ISSUE_CODE, CheckContextTest.DISABLED_AND_ENABLED_ISSUE_CODE})
  public boolean enabledForCodes(final EObject context) {
    return ENABLED;
  }

}
