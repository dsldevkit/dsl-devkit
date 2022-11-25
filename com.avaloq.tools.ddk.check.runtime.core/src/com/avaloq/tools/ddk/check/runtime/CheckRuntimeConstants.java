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

package com.avaloq.tools.ddk.check.runtime;

/**
 * Constants for the check runtime core project.
 */
public final class CheckRuntimeConstants {

  /** The ID of the check API extension point. */
  public static final String API_EXTENSION_POINT = "com.avaloq.tools.ddk.check.runtime.core.checkapi"; //$NON-NLS-1$

  /** Suffix used in issue codes class names. */
  public static final String ISSUE_CODES_CLASS_NAME_SUFFIX = "IssueCodes"; //$NON-NLS-1$

  private CheckRuntimeConstants() {}
}
