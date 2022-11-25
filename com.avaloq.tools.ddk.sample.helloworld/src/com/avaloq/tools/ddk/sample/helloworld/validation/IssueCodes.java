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
package com.avaloq.tools.ddk.sample.helloworld.validation;

/**
 * Issue codes which may be used to address validation issues (for instance in quickfixes).
 */
@SuppressWarnings("all")
public final class IssueCodes {

  // The prefix for all issue codes
  private static final String ISSUE_CODE_PREFIX = "com.avaloq.tools.ddk.sample.helloworld.validation.IssueCodes.";

  public static final String GREETING_NAME_PREFIX = ISSUE_CODE_PREFIX + "greetingnameprefix";

  private IssueCodes() {
    // Prevent instantiation.
  }
}
