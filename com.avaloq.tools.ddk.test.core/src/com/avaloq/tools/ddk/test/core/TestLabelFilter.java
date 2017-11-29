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
package com.avaloq.tools.ddk.test.core;

import org.apache.commons.lang.ArrayUtils;
import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;


/**
 * A test filter that makes sure to run only those {@link Description}s that are annotated with a specific {@link TestLabel}.
 */
public class TestLabelFilter extends Filter {
  private final String label;

  public TestLabelFilter(final String label) {
    super();
    this.label = label;
  }

  @Override
  public boolean shouldRun(final Description description) {
    TestLabels labels = description.getAnnotation(TestLabels.class);
    if (labels != null) {
      return ArrayUtils.contains(labels.value(), label);
    }
    return false;
  }

  @Override
  public String describe() {
    return TestLabelFilter.class.getSimpleName();
  }

}
