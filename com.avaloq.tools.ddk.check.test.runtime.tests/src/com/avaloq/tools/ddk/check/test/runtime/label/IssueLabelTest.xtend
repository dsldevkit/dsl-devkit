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

package com.avaloq.tools.ddk.check.test.runtime.label

import com.avaloq.tools.ddk.check.runtime.label.ICheckRuleLabelProvider
import com.avaloq.tools.ddk.check.validation.LibraryChecksIssueCodes
import com.google.inject.AbstractModule
import com.google.inject.Guice
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

/**
 * End-to-end test for getting Check labels.
 */
class IssueLabelTest {

  /**
   * End-to-end test for getting Check labels.
   */
  @Test
  def testGetLabel() {

    // ARRANGE
    val checkRuleLabelProvider = Guice.createInjector(new AbstractModule() {
      def protected override configure() {}
    }).getInstance(ICheckRuleLabelProvider);

    val expectedMap = #{
      // @Format-Off
      LibraryChecksIssueCodes.CACHE_DOESNT_WORK -> "Cache doesn't work",
      LibraryChecksIssueCodes.CACHE_INJECTION_FAILED -> "Cache injection failed",
      LibraryChecksIssueCodes.CHECK_CATALOG_IS_ACTIVE -> "Check catalog is active",
      LibraryChecksIssueCodes.FORMAL_PARAMETERS -> "Formal Parameters"
    // @Format-On
    }

    for (entry : expectedMap.entrySet) {
      // ACT
      val label = checkRuleLabelProvider.getLabel(entry.key);

      // ASSERT
      assertNotNull("Label should be returned for key " + entry.key, label);
      assertEquals("Correct label should be returned for key " + entry.key, entry.value, label);
    }
  }
}
