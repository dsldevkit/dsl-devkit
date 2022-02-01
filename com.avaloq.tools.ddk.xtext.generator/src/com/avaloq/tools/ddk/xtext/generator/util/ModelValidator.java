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
package com.avaloq.tools.ddk.xtext.generator.util;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Inject;


/**
 * ModelValidator. Utility class used by the generator to check the content of files used in fragments.
 */
public class ModelValidator {

  private static final String MESSAGE_TEMPLATE = "{0}:{1}: {2}"; //$NON-NLS-1$

  /** The checker. */
  @Inject
  private IResourceValidator resourceValidator;

  /**
   * Validate the resource and notify the logger of any error and warning.
   *
   * @param resource
   *          the resource
   * @param logger
   *          the logger
   * @return the list of issues found in the resource
   */
  public List<Issue> validate(final Resource resource, final Logger logger) {
    EcoreUtil.resolveAll(resource);
    final List<Issue> issues = resourceValidator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);

    for (final Issue issue : issues) {
      logIssue(resource, issue, logger);
    }
    return issues;
  }

  /**
   * Log issue.
   *
   * @param resource
   *          the resource
   * @param issue
   *          the issue
   * @param logger
   *          the logger
   */
  private void logIssue(final Resource resource, final Issue issue, final Logger logger) {
    final String message = NLS.bind(MESSAGE_TEMPLATE, new Object[] {resource.getURI().lastSegment(), issue.getLineNumber(), issue.getMessage()});
    final Severity severity = issue.getSeverity();
    switch (severity) {
    case ERROR:
      logger.error(message);
      break;
    case WARNING:
      logger.warn(message);
      break;
    case INFO:
      if (logger.isInfoEnabled()) {
        logger.info(message);
      }
      break;

    default:
      break;
    }
  }
}
