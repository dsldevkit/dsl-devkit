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
package com.avaloq.tools.ddk.xtext.format.ui.quickfix;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.Issue;

import com.avaloq.tools.ddk.xtext.format.format.Rule;
import com.avaloq.tools.ddk.xtext.format.validation.FormatValidator;


/**
 * The quickfix provider.
 */
public class FormatQuickfixProvider extends DefaultQuickfixProvider {

  /**
   * Semantic quickfix removing the override flag for a rule.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(FormatValidator.OVERRIDE_ILLEGAL_CODE)
  public void removeOverride(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, "Remove override", "Remove override.", null, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        context.getXtextDocument().modify(new IUnitOfWork<Void, XtextResource>() {
          @Override
          public java.lang.Void exec(final XtextResource state) {
            Rule rule = (Rule) state.getEObject(issue.getUriToProblem().fragment());
            rule.setOverride(false);
            return null;
          }
        });
      }
    });
  }

  /**
   * Semantic quickfix setting the override flag for a rule.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(FormatValidator.OVERRIDE_MISSING_CODE)
  public void setOverride(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, "Set override", "Set override flag.", null, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        context.getXtextDocument().modify(new IUnitOfWork<Void, XtextResource>() {
          @Override
          public java.lang.Void exec(final XtextResource state) {
            Rule rule = (Rule) state.getEObject(issue.getUriToProblem().fragment());
            rule.setOverride(true);
            return null;
          }
        });
      }
    });
  }

  /**
   * Syntactic quickfix creating a rule.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(FormatValidator.GRAMMAR_RULE_MISSING_CODE)
  public void addGrammarRule(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, "Create rule", "Create rule " + issue.getData()[0], null, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        final IXtextDocument xtextDocument = context.getXtextDocument();
        xtextDocument.replace(xtextDocument.getLength(), 0, "\n" + issue.getData()[0] + " {\n\t\n}");
      }
    });
  }
}
