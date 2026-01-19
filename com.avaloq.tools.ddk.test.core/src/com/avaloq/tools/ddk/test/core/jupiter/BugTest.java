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

import org.junit.jupiter.api.Test;

/**
 * An annotation used to indicate which bug issues a test method or test class covers.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Test
public @interface BugTest {
  /**
   * The list of bug issues.
   * 
   * @return the list of bug issues
   */
  String[] value();

  /**
   * Returns whether the corresponding bug is already fixed. Bug tests for unresolved bugs are disabled for common test runs.
   * 
   * @return <code>true</code> for unresolved bugs; <code>false</code> for fixed bugs.
   */
  boolean unresolved() default false;

}
