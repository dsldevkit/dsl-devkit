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
package com.avaloq.tools.ddk.check.core.test;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;


/**
 * Tests that the java code generated from BugDsl27 has no errors.
 */
@InjectWith(CheckInjectorProvider.class)
@RunWith(XtextRunner.class)
public class BugDsl27 extends AbstractCheckGenerationTestCase {

  /**
   * Tests that the java compiler does fail given invalid input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testJavaCompiler() {
    String code = "public class C { public D foo() {} }"; // Should fail compilation since class D unknown.
    getJavaCompiler().compileToClass("C", code);
  }

  /**
   * Tests that our test source compiles fine.
   */
  @Test
  public void testGeneratedCodeHasNoErrors() {
    try (InputStream sourceStream = BugDsl27.class.getResourceAsStream("bugdsl27/BugDsl27")) {
      generateAndCompile(sourceStream);
    } catch (IOException exception) {
      LOGGER.info("Failed to close the test file");
    }
  }
}
