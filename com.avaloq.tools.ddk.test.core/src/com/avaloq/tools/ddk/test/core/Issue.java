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
package com.avaloq.tools.ddk.test.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation for test classes and methods used to define a related issue and whether it is fixed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Issue {
  /**
   * The related issue.
   * 
   * @return the related issue, never {@code null}
   */
  String value();

  /**
   * Returns whether the issue is fixed.
   * 
   * @return {@code true} if the issue is fixed (default), {@code false} otherwise
   */
  boolean fixed() default true;
}
