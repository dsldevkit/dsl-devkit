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
package com.avaloq.tools.ddk.check.core.test;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;


/**
 * Tests that the java code generated from BugDsl27 has no errors.
 */
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
class BugDsl27Test extends AbstractCheckGenerationTestCase {

  /**
   * Tests that our test source compiles fine.
   */
  @Test
  void testGeneratedCodeHasNoErrors() {
    try (InputStream sourceStream = BugDsl27Test.class.getResourceAsStream("bugdsl27/BugDsl27")) {
      generateAndCompile(sourceStream);
    } catch (IOException exception) {
      LOGGER.info("Failed to close the test file");
    }
  }
}
