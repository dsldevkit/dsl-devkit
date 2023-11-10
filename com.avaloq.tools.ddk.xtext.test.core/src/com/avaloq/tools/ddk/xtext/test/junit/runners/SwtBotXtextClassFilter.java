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
package com.avaloq.tools.ddk.xtext.test.junit.runners;

import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Filter;

import com.avaloq.tools.ddk.test.core.junit.runners.FilterRegistry;
import com.avaloq.tools.ddk.test.ui.junit.runners.SwtBotRecordingTestRunner;


/**
 * A test filter that makes sure only SWT bot tests are run.
 * <p>
 * <em>Note</em>: The SWT bot test classes must use the {@link SwtBotRecordingTestRunner} for them to be recognized.
 * </p>
 */
public class SwtBotXtextClassFilter extends Filter {

  /** {@inheritDoc} */
  @Override
  public boolean shouldRun(final Description description) {
    assert FilterRegistry.isTestClass(description);
    final RunWith runWithAnnotation = description.getAnnotation(RunWith.class);
    return runWithAnnotation != null
        && "com.avaloq.tools.ddk.xtext.test.junit.runners.SwtBotRecordingXtextTestRunner".equals(runWithAnnotation.value().getName());
  }

  /** {@inheritDoc} */
  @Override
  public String describe() {
    return SwtBotXtextClassFilter.class.getSimpleName();
  }
}
