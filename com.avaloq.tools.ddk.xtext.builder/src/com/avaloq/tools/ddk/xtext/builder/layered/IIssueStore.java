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
package com.avaloq.tools.ddk.xtext.builder.layered;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.validation.Issue;

import com.google.common.collect.Multimap;


/**
 * An interface for managing issues.
 */
public interface IIssueStore {

  /**
   * @return all stored issues.
   */
  Multimap<URI, Issue> getIssues();

  /**
   * Returns the issues store for a particular resource uri.
   *
   * @param uri
   *          to get the issues for
   * @return the issues. Never returns {@code null}, but may return an empty iterable.
   */
  Iterable<Issue> getIssues(URI uri);

  /**
   * Store issues for a particular {@link URI}.
   *
   * @param forUri
   *          {@link URI} to which the issues belong
   * @param issues
   *          to store
   */
  void storeIssues(URI forUri, Iterable<Issue> issues);
}
