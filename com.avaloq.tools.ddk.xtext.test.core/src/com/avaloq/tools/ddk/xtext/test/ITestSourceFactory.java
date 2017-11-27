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
package com.avaloq.tools.ddk.xtext.test;

import com.google.inject.ImplementedBy;


/**
 * Factory for creating test sources.
 */
@ImplementedBy(XtextTestSourceFactory.class)
public interface ITestSourceFactory {

  /**
   * Determines if this factory can create a test source for the given test source name.
   * 
   * @param name
   *          name to check
   * @return {@code true} if a test source with the given name can be created using this factory
   */
  boolean isFactoryFor(final String name);

  /**
   * Creates a test source with the given name and content.
   * 
   * @param name
   *          name of test source
   * @param content
   *          test source content
   * @return created test source
   */
  TestSource createTestSource(final String name, final String content);
}

