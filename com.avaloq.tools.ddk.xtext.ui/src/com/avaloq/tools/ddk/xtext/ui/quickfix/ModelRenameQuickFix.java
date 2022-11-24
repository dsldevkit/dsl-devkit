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
package com.avaloq.tools.ddk.xtext.ui.quickfix;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.util.EmfResourceUtil;


/**
 * Textual quick fix which renames the model element identified by the issue to the name of the resource (without file extension).
 */
public class ModelRenameQuickFix extends IUnitOfWork.Void<XtextResource> {

  /** Document the quick fix pertains to. */
  private final IXtextDocument xtextDocument;
  /** Issue against name feature of object to rename. */
  private final Issue issue;

  /**
   * Creates a new quick fix for the given document and issue.
   *
   * @param xtextDocument
   *          document containing the error
   * @param issue
   *          issue for the model element's name feature to be modified
   */
  public ModelRenameQuickFix(final IXtextDocument xtextDocument, final Issue issue) {
    this.xtextDocument = xtextDocument;
    this.issue = issue;
  }

  @Override
  public void process(final XtextResource state) throws BadLocationException {
    final EObject target = state.getEObject(issue.getUriToProblem().fragment());
    final String issueString = xtextDocument.get(issue.getOffset(), issue.getLength());
    final String modelName = EmfResourceUtil.getFileNameWithoutExtension(target);

    xtextDocument.replace(issue.getOffset(), issueString.length(), modelName);
  }
}
