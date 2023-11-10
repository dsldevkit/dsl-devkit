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


/**
 * A filter that inverts the filter criteria of the underlying filter.
 */
public class NegatedFilter extends Filter {

  private final Filter underlyingFilter;

  /**
   * Creates a new instance of {@link NegatedFilter}.
   * 
   * @param filter
   *          the filter to negate, must not be {@code null}
   */
  public NegatedFilter(final Filter filter) {
    assert filter != null;
    this.underlyingFilter = filter;
  }

  
  @Override
  public boolean shouldRun(final Description description) {
    return !underlyingFilter.shouldRun(description);
  }

  
  @Override
  public String describe() {
    return "!" + underlyingFilter.describe();
  }
}

