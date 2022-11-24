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
package com.avaloq.tools.ddk.xtext.test;

/**
 * Raised when a test wants to annotate a method with either a {@link @BeforeClass} or a {@link @AfterClass} annotation.
 */
class IllegalJUnitAnnotation extends RuntimeException {
  private static final long serialVersionUID = 1L;

  IllegalJUnitAnnotation() {
    super("Invalid annotation found. BeforeClass and AfterClass annotations are not permitted when using the AbstractXtextTest framework. Use the methods 'beforeAllTests' and 'afterAllTests' instead.");
  }

  IllegalJUnitAnnotation(final String message) {
    super(message);
  }
}

