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
package com.avaloq.tools.ddk.sample.helloworld.label

import com.google.inject.AbstractModule
import com.google.inject.Guice

import com.avaloq.tools.ddk.sample.helloworld.validation.LibraryChecksIssueCodes
import com.avaloq.tools.ddk.check.runtime.label.ICheckRuleLabelProvider
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertNotNull
import static org.junit.jupiter.api.Assertions.assertEquals

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
      protected override configure() {}
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
      assertNotNull(label,"Label should be returned for key " + entry.key);
      assertEquals(entry.value, label,  "Correct label should be returned for key " + entry.key);
    }
  }
}
