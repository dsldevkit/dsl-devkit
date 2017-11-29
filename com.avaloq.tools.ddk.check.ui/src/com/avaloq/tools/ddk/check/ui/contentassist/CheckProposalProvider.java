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

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider.Filter;
import org.eclipse.xtext.common.ui.contentassist.TerminalsProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.conversion.XbaseQualifiedNameValueConverter;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.ui.labeling.CheckImages;
import com.avaloq.tools.ddk.check.ui.util.CheckResourceUtil;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;


/**
 * Configures a proposal provider for the Check editor.
 */
public class CheckProposalProvider extends AbstractCheckProposalProvider {

  // @Format-Off
  /** Cache of the available eClasses and parameter types to make proposals more responsive. */
  private static final Set<String> REGISTERED_ECLASS_NAMES = ImmutableSet.copyOf(()->{
      do {
        try {
          return EPackage.Registry.INSTANCE.keySet().stream()
          .map(nsUri -> EPackage.Registry.INSTANCE.getEPackage(nsUri).getEClassifiers())
          .flatMap(List::stream)
          .map(eClass -> eClass.getInstanceClassName())
          .filter(Objects::nonNull) // immutable set copies cannot contain null
          .collect(Collectors.toList())
          .iterator();
        } catch (ConcurrentModificationException unused) {} // In case the registry resolves some packages,
      } while (true);                                       // keep trying to cache it.
    });
  // @Format-On
  private static final Filter FORMAL_PARAMETER_JVM_CLASS_FILTER = new Filter() {
    // @Format-Off
    private final Set<String> formalParameterJvmClassNames = ImmutableSet.copyOf(
        Stream.of(String.class, Boolean.class, Integer.class, List.class)
        .map(clazz -> clazz.getName())
        .iterator());
    // @Format-On

    @Override
    public int getSearchFor() {
      return IJavaSearchConstants.TYPE;
    }

    @Override
    public boolean accept(final int modifiers, final char[] packageName, final char[] simpleTypeName, final char[][] enclosingTypeNames, final String path) {
      return formalParameterJvmClassNames.contains(new String(packageName) + '.' + new String(simpleTypeName));
    }
  };

  @Inject
  private CheckResourceUtil resourceUtil;
  @Inject
  private XbaseQualifiedNameValueConverter qualifiedNameValueConverter;
  @Inject
  private CheckImages checkImages;
  @Inject
  private InternalTerminalsProposalProvider terminalsProposalProvider;

  private boolean noMoreProposals;

  /**
   * Disable the Xbase proposal provider from proposing all JVM types.
   */
  private void shortCircuit() {
    noMoreProposals = true;
    super.reset();
  }

  @Override
  public void reset() {
    noMoreProposals = false;
    super.reset();
  }

  @Override
  public boolean isLastMode() {
    return noMoreProposals || super.isLastMode();
  }

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
    completeJavaTypes(context, TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE, true, qualifiedNameValueConverter, new Filter() {
      // @Format-Off
      private final Set<String> resourceEClassNames = model.eResource().getResourceSet()
          .getResources().stream()
          .map(resource -> resource.getContents()).flatMap(List::stream)
          .filter(obj -> obj instanceof EPackage)
          .map(obj -> ((EPackage) obj).getEClassifiers()).flatMap(List::stream)
          .map(eClass -> eClass.getEPackage().getName() + '.' + eClass.getName())
          .collect(Collectors.toSet());
      // @Format-On

      @Override
      public int getSearchFor() {
        return IJavaSearchConstants.INTERFACE;
      }

      @Override
      public boolean accept(final int modifiers, final char[] packageName, final char[] simpleTypeName, final char[][] enclosingTypeNames, final String path) {
        String fullPackageName = new String(packageName);
        String simpleClassName = new String(simpleTypeName);
        String ePackageName = fullPackageName.substring(fullPackageName.lastIndexOf('.') + 1);
        return REGISTERED_ECLASS_NAMES.contains(fullPackageName + '.' + simpleClassName) || resourceEClassNames.contains(ePackageName + '.' + simpleClassName);
      }
    }, acceptor);
    shortCircuit();
  }

  /** {@inheritDoc} */
  @Override
  // CHECKSTYLE:OFF
  public void completeFormalParameter_Type(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    // CHECKSTYLE:ON
    completeJavaTypes(context, CheckPackage.Literals.FORMAL_PARAMETER__TYPE, true, qualifiedNameValueConverter, FORMAL_PARAMETER_JVM_CLASS_FILTER, acceptor);
    shortCircuit();
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
