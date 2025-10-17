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
package com.avaloq.tools.ddk.xtext.test.linking;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;


/**
 * Test linking of a given input file.
 */
@SuppressWarnings("PMD.AbstractClassWithoutAnyMethod")
public abstract class AbstractLinkingTest extends AbstractXtextTest {
  private static final String NOT_RESOLVED_MESSAGE = "Cross-reference has not been resolved."; //$NON-NLS-1$

  /**
   * Asserts that the actualObject equals the expectedObject.
   *
   * @param expectedObject
   *          the expected object
   * @param actualObject
   *          the actual object
   */
  protected void assertReferenceResolved(final EObject expectedObject, final EObject actualObject) {
    assertEquals(NOT_RESOLVED_MESSAGE, EcoreUtil.getURI(expectedObject), EcoreUtil.getURI(actualObject));
  }
}
