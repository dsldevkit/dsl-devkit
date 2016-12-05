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
package com.avaloq.tools.ddk.check.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider;
import org.eclipse.xtext.common.types.xtext.ui.TypeMatchFilters;
import org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.ui.labeling.CheckImages;
import com.avaloq.tools.ddk.check.ui.util.CheckResourceUtil;
import com.google.inject.Inject;


/**
 * Configures a proposal provider for the Check editor.
 */
@SuppressWarnings("restriction")
public class CheckProposalProvider extends AbstractCheckProposalProvider {

  @Inject
  private CheckResourceUtil resourceUtil;

  @Inject
  private ITypesProposalProvider typeProposalProvider;

  @Inject
  private TypeReferences typeReferences;

  @Inject
  private CheckImages checkImages;

  @Inject
  private InternalTerminalsProposalProvider terminalsProposalProvider;

  /**
   * Provides access to <code>super.super.*</code> methods.
   */
  static class InternalTerminalsProposalProvider extends TerminalsProposalProvider {

    /** {@inheritDoc} */
    @Override
    // CHECKSTYLE:OFF
    public void complete_ID(final EObject model, final RuleCall ruleCall, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
      // CHECKSTYLE:ON
      if (getAssignedFeature(ruleCall).equalsIgnoreCase(CheckConstants.IT)) { // TODO this is ugly
        acceptor.accept(createCompletionProposal(getAssignedFeature(ruleCall).toLowerCase(), getAssignedFeature(ruleCall).toLowerCase(), null, context));
        return;
      }
      super.complete_ID(model, ruleCall, context, acceptor);
    }

    /**
     * Get the name assigned feature in an Assignment rule call.
     * 
     * @param call
     *          a rule call
     * @return the assigned feature
     */
    private String getAssignedFeature(final RuleCall call) {
      Assignment ass = GrammarUtil.containingAssignment(call);
      if (ass != null) {
        String result = ass.getFeature();
        if (result.equals(result.toLowerCase())) { // NOPMD
          result = Strings.toFirstUpper(result);
        }
        return result;
      }
      return null;
    }
  }

  /**
   * Decide if a completion proposal should be shown for a {@link Keyword keyword} or not. The default behavior will not display
   * keywords of length 1 and also only if the first letter is a character. This is not desired for Check language as there are
   * keywords such as curly braces which are mandatory.
   * 
   * @param keyword
   *          the keyword
   * @return <code>true</code>, if the keyword should be displayed as a valid proposal
   */
  @Override
  protected boolean isKeywordWorthyToPropose(final Keyword keyword) {
    return keyword.getValue().length() > 1 || "()[]{}@#".contains(keyword.getValue());
  }

  /** {@inheritDoc} */
  @Override
  // CHECKSTYLE:OFF
  public void complete_ValidID(final EObject model, final RuleCall ruleCall, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    super.complete_ID(model, ruleCall, context, acceptor);
    terminalsProposalProvider.complete_ID(model, ruleCall, context, acceptor);
  }

  /** {@inheritDoc} */
  @Override
  // CHECKSTYLE:OFF
  public void complete_ID(final EObject model, final RuleCall ruleCall, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    super.complete_ID(model, ruleCall, context, acceptor);
    terminalsProposalProvider.complete_ID(model, ruleCall, context, acceptor);
  }

  /** {@inheritDoc} */
  @Override
  // CHECKSTYLE:OFF
  public void completeContextVariable_Type(final EObject model, final org.eclipse.xtext.Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    if (!context.getPrefix().endsWith(".")) {
      JvmType parameterType = typeReferences.findDeclaredType(EObject.class, model);
      typeProposalProvider.createSubTypeProposals(parameterType, this, context, CheckPackage.Literals.CONTEXT_VARIABLE__TYPE, TypeMatchFilters.all(IJavaSearchConstants.INTERFACE), acceptor);
      reset(); // experimental code, disable Xbase proposal provider from proposing all JVM types instead of the content of the scope...
    }
  }

  /** {@inheritDoc} */
  @Override
  // CHECKSTYLE:OFF
  public void completeCheckCatalog_PackageName(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    String currentPackage = resourceUtil.getNameOfContainingPackage(context.getDocument());
    if (currentPackage != null) {
      acceptor.accept(this.createCompletionProposal(currentPackage, NLS.bind("Use current package: {0}", currentPackage), checkImages.forPackage(), context));
      return;
    }
    super.completeCheckCatalog_PackageName(model, assignment, context, acceptor);
  }

  /** {@inheritDoc} */
  @Override
  // CHECKSTYLE:OFF
  public void completeCheckCatalog_Name(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    String currentResourceName = resourceUtil.getNameOfResource(context.getDocument());
    if (currentResourceName != null) {
      acceptor.accept(this.createCompletionProposal(currentResourceName, NLS.bind("Use current file name: {0}", currentResourceName), checkImages.forResource(), context));
      return;
    }
    super.completeCheckCatalog_Name(model, assignment, context, acceptor);
  }

}

