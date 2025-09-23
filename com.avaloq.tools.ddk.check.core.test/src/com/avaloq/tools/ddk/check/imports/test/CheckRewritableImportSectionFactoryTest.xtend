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

package com.avaloq.tools.ddk.check.imports.test

import com.avaloq.tools.ddk.check.core.test.AbstractCheckTestCase
import com.avaloq.tools.ddk.check.imports.CheckRewritableImportSectionFactory
import com.google.inject.Inject
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Tests for {@link CheckRewritableImportSectionFactoryTest}.
 */
@RunWith(XtextRunner)
class CheckRewritableImportSectionFactoryTest extends AbstractCheckTestCase {

  @Inject CheckRewritableImportSectionFactory factory;

  /**
   * Test parse() when passed a null XtextResource
   */
  @Test(expected=NullPointerException)
  def void testParseWithNullXtextResource() {
    factory.parse(null)
  }

  /**
   * Happy-path test for parse()
   */
  @Test
  def void testParseSuccessful() {
    // ARRANGE
    val resource = resourceSet.createResource(URI.createURI("dummy:/test")) as XtextResource

    // ACT
    val rewritableImportSection = factory.parse(resource)

    // ASSERT
    assertNotNull("parse() should return an object", rewritableImportSection)
    assertTrue("parse() should return a RewritableImportSection with sort=true", rewritableImportSection.sort)
  }

}

