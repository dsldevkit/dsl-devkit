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

package com.avaloq.tools.ddk.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Used to suppress FindBugs warnings.
 */
@Retention(RetentionPolicy.CLASS)
public @interface SuppressFBWarnings {
  /**
   * The set of FindBugs warnings that are to be suppressed in
   * annotated element. The value can be a bug category, kind or pattern.
   */
  String[] value() default {};

  /**
   * Optional documentation of the reason why the warning is suppressed.
   */
  String justification() default "";
}