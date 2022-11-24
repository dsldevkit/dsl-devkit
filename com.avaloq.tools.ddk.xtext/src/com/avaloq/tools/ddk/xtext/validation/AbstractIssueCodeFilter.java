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
package com.avaloq.tools.ddk.xtext.validation;

import java.util.Collection;

import org.eclipse.xtext.validation.Issue;

import com.google.inject.Singleton;


/**
 * Base implementation for issue filters based (solely) on issue codes. Maintains different
 * sets of issue codes to ignore, based on the state of the filter.
 */
@Singleton
public abstract class AbstractIssueCodeFilter implements IIssueFilter {

  /**
   * Gets the collection of issue codes to ignore in stand-alone build.
   *
   * @return the collection
   */
  protected abstract Collection<String> getIgnoredCodes();

  /** {@inheritDoc} */
  @Override
  public boolean includeIssue(final Issue issue) {
    if (issue == null) {
      return false;
    }
    Collection<String> currentSet = getIgnoredCodes();
    if (currentSet == null) {
      return true;
    }
    String issueCode = issue.getCode();
    return issueCode != null && !currentSet.contains(issueCode);
  }

}
