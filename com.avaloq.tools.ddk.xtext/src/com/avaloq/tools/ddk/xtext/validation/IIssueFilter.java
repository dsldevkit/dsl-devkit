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

import org.eclipse.xtext.validation.Issue;

import com.google.inject.ImplementedBy;
import com.google.inject.Singleton;


/**
 * A general filter for issues that are to be completely ignored in a stand-alone build. Filters apply to all resources
 * regardless of host language; the idea is that if some langauage's validator generates issues that shall be ignored
 * in the stand-alone build, one provides a filter to filter out these particular issues in all languages. if a filter
 * shall filter out certain issues only in certain types of resources, use the issue's uriToProblem to determine the
 * kind of resource and include that information in the decision.
 */
@ImplementedBy(IIssueFilter.NullIssueFilter.class)
public interface IIssueFilter {

  /**
   * Determines whether a particular issue should be generated or not.
   *
   * @param issue
   *          which we're about to generate
   * @return {@code true} if the issue shall be generated; {@code false} if not.
   */
  boolean includeIssue(Issue issue);

  /** Dummy implementation that always generates all non-null issues. */
  @Singleton
  class NullIssueFilter implements IIssueFilter {

    
    @Override
    public boolean includeIssue(final Issue issue) {
      return issue != null;
    }

  }
}
