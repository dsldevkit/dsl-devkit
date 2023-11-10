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

import java.util.regex.Pattern;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;


/**
 * A test method filter that makes sure to run only those tests, which have a fully qualified name that matches a specified regular expression.
 * <p>
 * <em>Note</em>: The regular expression needs to be defined using the property {@value #PROPERTY_NAME_PREFIX}.
 * </p>
 */
public class NameMethodFilter extends Filter {

  private static final String PROPERTY_NAME_PREFIX = "com.avaloq.test.namemethodfilter";
  private static final String REGULAR_EXPRESSION = System.getProperty(PROPERTY_NAME_PREFIX, "");
  private static final Pattern PATTERN = Pattern.compile(REGULAR_EXPRESSION);

  @Override
  public boolean shouldRun(final Description description) {
    assert FilterRegistry.isTestMethod(description);
    return PATTERN.matcher(getQualifiedMethodName(description)).matches();
  }

  @Override
  public String describe() {
    return NameMethodFilter.class.getSimpleName();
  }

  /**
   * Returns the qualified method name of the given {@link Description}.
   * 
   * @param description
   *          the {@link Description} which must describe a test method, must not be {@code null}
   * @return the qualified method name of the given {@link Description}
   */
  private String getQualifiedMethodName(final Description description) {
    assert FilterRegistry.isTestMethod(description);
    return description.getClassName() + "." + description.getMethodName();
  }
}
