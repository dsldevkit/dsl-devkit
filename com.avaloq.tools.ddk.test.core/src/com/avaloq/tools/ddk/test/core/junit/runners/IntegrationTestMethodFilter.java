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
package com.avaloq.tools.ddk.test.core.junit.runners;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;

import com.avaloq.tools.ddk.test.core.IntegrationTest;


/**
 * A test method filter that makes sure only tests annotated with @{@link IntegrationTest} are run.
 */
public class IntegrationTestMethodFilter extends Filter {

  @Override
  public boolean shouldRun(final Description description) {
    assert FilterRegistry.isTestMethod(description);
    return description.getAnnotation(IntegrationTest.class) != null;
  }

  @Override
  public String describe() {
    return IntegrationTestMethodFilter.class.getSimpleName();
  }
}
