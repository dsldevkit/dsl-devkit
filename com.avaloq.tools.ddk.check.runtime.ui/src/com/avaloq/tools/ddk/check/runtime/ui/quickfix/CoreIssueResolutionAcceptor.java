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
package com.avaloq.tools.ddk.check.runtime.ui.quickfix;

import java.util.List;

import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.check.runtime.quickfix.CoreIssueResolution;
import com.avaloq.tools.ddk.check.runtime.quickfix.CoreSemanticModificationWrapper;
import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreModification;
import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreSemanticModification;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Copy of org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor.
 * <p>
 * Defines a concrete acceptor for issue resolutions. Is used in quickfix implementations to add document modifications.
 */
// CHECKSTYLE:OFF copied class
public class CoreIssueResolutionAcceptor implements ICoreIssueResolutionAcceptor2 {

  private final List<CoreIssueResolution> issueResolutions = Lists.newArrayList();

  private final CoreIssueModificationContext.Factory modificationContextFactory;

  @Inject
  public CoreIssueResolutionAcceptor(final CoreIssueModificationContext.Factory modificationContextFactory) {
    this.modificationContextFactory = modificationContextFactory;
  }

  public void accept(final Issue issue, final String label, final String description, final String image, final ICoreModification modification) {
    issueResolutions.add(new CoreIssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue), modification));
  }

  public void accept(final Issue issue, final String label, final String description, final String image, final ICoreSemanticModification semanticModification) {
    CoreSemanticModificationWrapper modificationWrapper = new CoreSemanticModificationWrapper(issue.getUriToProblem(), semanticModification);
    issueResolutions.add(new CoreIssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue), modificationWrapper));
  }

  public List<CoreIssueResolution> getIssueResolutions() {
    return issueResolutions;
  }

}

