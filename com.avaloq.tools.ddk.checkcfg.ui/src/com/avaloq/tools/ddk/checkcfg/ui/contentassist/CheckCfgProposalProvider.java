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
package com.avaloq.tools.ddk.checkcfg.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.google.inject.Inject;


/**
 * Configures the Check Configuration proposal provider (content assist).
 */
public class CheckCfgProposalProvider extends AbstractCheckCfgProposalProvider {

  @Inject
  private CheckCfgUtil checkCfgUtil;

  @Override
  protected boolean isKeywordWorthyToPropose(final Keyword keyword) {
    return true; // show all keywords
  }

  @Override
  // CHECKSTYLE:OFF
  public void completeConfiguredParameter_NewValue(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    // TODO filter depending on type of linked parameter
    FormalParameter parameter = ((ConfiguredParameter) model).getParameter();
    ICheckCfgPropertySpecification propertySpecification = null;
    String[] validValues = null;
    if (parameter != null) {
      propertySpecification = CheckCfgUtil.getPropertySpecification(parameter.getName());
      if (propertySpecification != null) {
        validValues = propertySpecification.getExpectedValues();
      }
    }
    if (validValues != null && validValues.length > 0) {
      String info = propertySpecification.getInfo();
      for (String validValue : validValues) {
        ICompletionProposal proposal = createCompletionProposal(String.format("\"%s\"", validValue), new StyledString(validValue), getImage(model), 0, context.getPrefix(), context); //$NON-NLS-1$
        if (proposal instanceof ConfigurableCompletionProposal) {
          ((ConfigurableCompletionProposal) proposal).setAdditionalProposalInfo(info);
        }
        acceptor.accept(proposal);
      }
      return;
    }
    super.completeConfiguredParameter_NewValue(model, assignment, context, acceptor);
  }

  @Override
  // CHECKSTYLE:OFF
  public void completeConfiguredLanguageValidator_Language(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    for (String language : checkCfgUtil.getAllLanguages()) {
      acceptor.accept(createCompletionProposal(language, language, null, context));
    }
  }
}
