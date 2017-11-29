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

package com.avaloq.tools.ddk.check.test.runtime;

import com.avaloq.tools.ddk.check.validation.IssueCodes;


/**
 * Xtend has a problem linking directly to {@link IssueCodes}. This is a workaround.
 */
public final class IssueCodesConstants {
  public static final String GREETING_NAME_PREFIX = IssueCodes.GREETING_NAME_PREFIX;

  private IssueCodesConstants() {
    // prevent creating an instance
  }
}
