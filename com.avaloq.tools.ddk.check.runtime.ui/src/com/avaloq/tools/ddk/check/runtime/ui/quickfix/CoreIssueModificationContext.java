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

import org.eclipse.emf.common.util.URI;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreModificationContext;
import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreXtextDocument;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * Copy of org.eclipse.xtext.ui.editor.model.edit.IssueModificationContext.
 * <p>
 * Defines a the concrete context for textual and semantic document modifications.
 */
// CHECKSTYLE:OFF copied class
public class CoreIssueModificationContext implements ICoreModificationContext {

  @Inject
  private IURIEditorOpener editorOpener;
  @Inject
  private IModificationContextRegistry modificationContext;

  private Issue issue;

  public void setIssue(final Issue issue) {
    this.issue = issue;
  }

  public Issue getIssue() {
    return issue;
  }

  @Override
  public ICoreXtextDocument getXtextDocument() {
    return getXtextDocument(issue.getUriToProblem());
  }

  @Override
  public ICoreXtextDocument getXtextDocument(final URI uri) {
    // On UI threads make use of the editor
    if (Display.getCurrent() != null) {
      IEditorPart editor = editorOpener.open(uri, false);
      if (editor instanceof XtextEditor) {
        XtextEditor xtextEditor = (XtextEditor) editor;
        return new XtextDocumentAdapter(xtextEditor.getDocument());
      }
      return null;
    }

    // Otherwise load the resource and create a new document for it
    return modificationContext.getXtextDocument(uri);
  }

  @ImplementedBy(Factory.Default.class)
  public static interface Factory {
    ICoreModificationContext createModificationContext(Issue issue);

    public static class Default implements Factory {

      @Inject
      private Provider<CoreIssueModificationContext> provider;

      @Override
      public ICoreModificationContext createModificationContext(final Issue issue) {
        CoreIssueModificationContext modificationContext = provider.get();
        modificationContext.setIssue(issue);
        return modificationContext;
      }

    }
  }

}
