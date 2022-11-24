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
package com.avaloq.tools.ddk.check.ui.quickfix;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.ui.quickfix.XbaseQuickfixProvider;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.Documented;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.check.SeverityRange;
import com.avaloq.tools.ddk.check.check.XGuardExpression;
import com.avaloq.tools.ddk.check.ui.util.CheckResourceUtil;
import com.avaloq.tools.ddk.check.util.CheckUtil;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.google.inject.Inject;


/**
 * Configures a quick fix provider for the Check editor.
 */
public class CheckQuickfixProvider extends XbaseQuickfixProvider {

  @Inject
  private CheckResourceUtil resourceUtil;

  private static final String NO_IMAGE = null;
  private static final String CHECK = "check"; //$NON-NLS-1$

  /**
   * Replaces a guard by an if-expression.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.GUARDS_DEPRECATED)
  public void fixGuardStatement(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_GUARD_DEPRECATED_LABEL, Messages.CheckQuickfixProvider_GUARD_DEPRECATED_DESCN, NO_IMAGE, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        IXtextDocument xtextDocument = context.getXtextDocument();
        String expr = xtextDocument.get(issue.getOffset(), issue.getLength());
        expr = expr.replaceFirst("^guard(\\s*)", "if (!(") + ")) {return}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        xtextDocument.replace(issue.getOffset(), issue.getLength(), expr);
      }
    });
  }

  /**
   * Fix catalog name.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.WRONG_FILE)
  public void fixCatalogName(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_CORRECT_CATALOG_NAME_LABEL, Messages.CheckQuickfixProvider_CORRECT_CATALOG_NAME_DESCN, NO_IMAGE, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        IXtextDocument xtextDocument = context.getXtextDocument();
        IFile file = xtextDocument.getAdapter(IFile.class);
        if (file != null) {
          final String fileName = file.getName();
          final String name = fileName.indexOf('.') > 0 ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
          xtextDocument.replace(issue.getOffset(), issue.getLength(), name);
        }
      }
    });
  }

  /**
   * Fixes the wrong package name.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.WRONG_PACKAGE)
  public void fixPackageName(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_CORRECT_PKG_NAME_LABEL, Messages.CheckQuickfixProvider_CORRECT_PKG_NAME_DESCN, NO_IMAGE, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        IXtextDocument xtextDocument = context.getXtextDocument();
        final String packageName = resourceUtil.getNameOfContainingPackage(xtextDocument);
        if (packageName != null) {
          xtextDocument.replace(issue.getOffset(), issue.getLength(), packageName);
        }
      }
    });
  }

  /**
   * Adds an {@code issue} statement to a constraint. If the constraint already contains other expressions, {@code issue} is appended to the end.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.MISSING_ISSUE_EXPRESSION)
  public void addIssueExpression(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_ADD_ISSUE_EXPR_LABEL, Messages.CheckQuickfixProvider_ADD_ISSUE_EXPR_DESCN, NO_IMAGE, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        IXtextDocument xtextDocument = context.getXtextDocument();
        xtextDocument.replace(issue.getOffset() + issue.getLength() - 1, 0, ' ' + "issue" + ' '); //$NON-NLS-1$
      }
    });
  }

  /**
   * Autodocument a given class.
   *
   * @param <T>
   *          the type of the document
   */
  public class AutoDoc<T extends Documented> implements IModification {
    private final String autodocumentation;
    private final Issue issue;
    private final Class<T> type;

    public AutoDoc(final Class<T> type, final Issue issue, final String documentation) {
      this.autodocumentation = documentation;
      this.issue = issue;
      this.type = type;
    }

    /** {@inheritDoc} */
    @Override
    public void apply(final IModificationContext context) throws BadLocationException {
      final IXtextDocument xtextDocument = context.getXtextDocument();
      xtextDocument.readOnly(new IUnitOfWork.Void<XtextResource>() {
        @Override
        public void process(final XtextResource state) throws Exception { // NOPMD
          final EObject target = EcoreUtil2.getContainerOfType(state.getEObject(issue.getUriToProblem().fragment()), type);
          if (type.isInstance(target)) {
            int offset = NodeModelUtils.findActualNodeFor(target).getOffset();
            int lineOfOffset = xtextDocument.getLineOfOffset(offset);
            int lineOffset = xtextDocument.getLineOffset(lineOfOffset);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < (offset - lineOffset); i++) {
              buffer.append(' ');
            }
            xtextDocument.replace(offset, 0, NLS.bind(autodocumentation, buffer.toString()));
          }
        }
      });
    }
  }

  /**
   * Adds documentation to an undocumented check using the double-asterisk-slash prefix and asterisk-slash suffix.
   * As contents the label of the check is used.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.MISSING_DOCUMENTATION_ON_CHECK)
  public void addDocumentationToCheck(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_ADD_DOCUMENTATION_LABEL, NLS.bind(Messages.CheckQuickfixProvider_ADD_DOCUMENTATION_DESCN, CHECK), NO_IMAGE, // LF
        new AutoDoc<Check>(Check.class, issue, "/**\n{0} * @todo document check\n{0} */\n{0}")); //$NON-NLS-1$
  }

  /**
   * Adds documentation to an undocumented check using the double-asterisk-slash prefix and asterisk-slash suffix.
   * As contents the label of the check is used.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.MISSING_DOCUMENTATION_ON_IMPLEMENTATION)
  public void addDocumentationToImplementation(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_ADD_DOCUMENTATION_LABEL, NLS.bind(Messages.CheckQuickfixProvider_ADD_DOCUMENTATION_DESCN, "definition"), NO_IMAGE, // LF //$NON-NLS-1$
        new AutoDoc<Implementation>(Implementation.class, issue, "/**\n{0} * @todo document implementation\n{0} */\n{0}")); //$NON-NLS-1$
  }

  /**
   * Add explicit ID to a check, autogenerated from its label.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.MISSING_ID_ON_CHECK)
  public void addIdToCheck(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_ADD_ID_LABEL, NLS.bind(Messages.CheckQuickfixProvider_ADD_ID_DESCN, CHECK), NO_IMAGE, (final EObject element, final IModificationContext context) -> {
      final Check check = EcoreUtil2.getContainerOfType(element, Check.class);
      if (check != null) {
        final String label = check.getLabel();
        final String id = CheckUtil.toIdentifier(label);
        check.setId(id);
      }
    });
  }

  /**
   * Removes the guard statement occurring at offending position.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.GUARDS_COME_FIRST)
  @SuppressWarnings("unchecked")
  public void removeGuardStatement(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_REMOVE_GUARD_LABEL, Messages.CheckQuickfixProvider_REMOVE_GUARD_DESCN, NO_IMAGE, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        final XGuardExpression guard = EcoreUtil2.getContainerOfType(element, XGuardExpression.class);
        if (guard != null && guard.eContainingFeature().isMany()) {
          EList<? extends EObject> holder = (EList<? extends EObject>) guard.eContainer().eGet(guard.eContainingFeature());
          if (holder != null && holder.contains(guard)) {
            holder.remove(guard);
          }
        }
      }
    });
  }

  /**
   * Removes the final declaration from the check. Final conflicts with severity range.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.SEVERITY_RANGES_FOR_FINAL_CHECK)
  public void removeFinal(final Issue issue, final IssueResolutionAcceptor acceptor) {
    doTextualTokenReplacement(issue, acceptor, Messages.CheckQuickfixProvider_REMOVE_FINAL_KW_LABEL, Messages.CheckQuickfixProvider_REMOVE_FINAL_KW_DESCN, ""); //$NON-NLS-1$
  }

  /**
   * Removes the severity range declaration from the check. Final conflicts with severity range.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.SEVERITY_RANGES_FOR_FINAL_CHECK)
  public void removeSeverityRange(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_REMOVE_SEVERITY_RANGE_LABEL, Messages.CheckQuickfixProvider_REMOVE_SEVERITY_RANGE_DESCN, NO_IMAGE, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        final Check check = EcoreUtil2.getContainerOfType(element, Check.class);
        if (check != null) {
          check.setSeverityRange(null);
        }
      }
    });
  }

  /**
   * Fixes the severity range order by setting the lower severity level kind first and the severity of higher severity level last.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER)
  public void fixSeverityRangeOrder(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_FIX_SEVERITY_RANGE_ORDER_LABEL, Messages.CheckQuickfixProvider_FIX_SEVERITY_RANGE_ORDER_DESCN, NO_IMAGE, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        final Check check = EcoreUtil2.getContainerOfType(element, Check.class);
        if (check != null && check.getSeverityRange() != null) {
          final SeverityRange range = check.getSeverityRange();
          SeverityKind oldMinSeverity = range.getMinSeverity();
          range.setMinSeverity(range.getMaxSeverity());
          range.setMaxSeverity(oldMinSeverity);
        }
      }
    });
  }

  /**
   * Removes a duplicate parameter definition.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.DUPLICATE_PARAMETER_DEFINITION)
  public void removeDuplicateParameterDefinition(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckQuickfixProvider_REMOVE_PARAM_DEF_LABEL, Messages.CheckQuickfixProvider_REMOVE_PARAM_DEF_DESCN, null, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        Check check = EcoreUtil2.getContainerOfType(element, Check.class);
        check.getFormalParameters().remove(element);
      }
    });
  }

  /**
   * Fixes an illegally set default severity. The default severity must be within given severity range.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE)
  public void fixIllegalDefaultSeverity(final Issue issue, final IssueResolutionAcceptor acceptor) {
    if (issue.getData() != null) {
      for (final String severityProposal : issue.getData()) {
        final String label = NLS.bind(Messages.CheckQuickfixProvider_DEFAULT_SEVERITY_FIX_LABEL, severityProposal);
        final String descn = NLS.bind(Messages.CheckQuickfixProvider_DEFAULT_SEVERITY_FIX_DESCN, severityProposal);

        acceptor.accept(issue, label, descn, NO_IMAGE, new IModification() {
          @Override
          public void apply(final IModificationContext context) throws BadLocationException {
            IXtextDocument xtextDocument = context.getXtextDocument();
            xtextDocument.replace(issue.getOffset(), issue.getLength(), severityProposal);
          }
        });
      }
    }
  }

  /**
   * Fixes an illegally set default severity. The default severity must be within given severity range.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.CHECK_IMPLICITLY_FINAL)
  public void fixRedundantFinalKeyword(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final String label = Messages.CheckQuickfixProvider_REDUNDANT_FINAL_FIX_LABEL;
    final String descn = Messages.CheckQuickfixProvider_REDUNDANT_FINAL_FIX_DESCN;
    doTextualTokenReplacement(issue, acceptor, label, descn, ""); //$NON-NLS-1$
  }

  /**
   * Replaces the marked problem area (as derived by issue offset and length) with given replacement string. May be used
   * to remove obsolete modifiers.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   * @param label
   *          the label
   * @param descn
   *          the description
   * @param replacement
   *          the replacement
   */
  private void doTextualTokenReplacement(final Issue issue, final IssueResolutionAcceptor acceptor, final String label, final String descn, final String replacement) {
    acceptor.accept(issue, label, descn, NO_IMAGE, new IModification() {
      @Override
      public void apply(final IModificationContext context) throws BadLocationException {
        IXtextDocument xtextDocument = context.getXtextDocument();
        xtextDocument.replace(issue.getOffset(), issue.getLength() + 1, replacement);
      }
    });
  }

  //
  // /**
  // * Quickfixes for Wrong Package
  // *
  // * @param issue
  // * raised issue
  // * @param acceptor
  // * the acceptor
  // */
  // @Fix(IssueCodes.WRONG_PACKAGE)
  // public void getWrongPackageQuickfixes(final Issue issue, final IssueResolutionAcceptor acceptor) {
  // acceptor.accept(issue, "Correct package declaration", NLS.bind("Change package to {0}.", issue.getData()[0]), NO_IMAGE, new ISemanticModification() {
  // public void apply(final EObject element, final IModificationContext context) throws Exception {
  // ((CheckCatalog) element).setPackageName(issue.getData()[0]);
  // }
  // });
  // acceptor.accept(issue, "Move to package", "Move to package.", NO_IMAGE, new ISemanticModification() {
  // public void apply(final EObject element, final IModificationContext context) throws Exception {
  // // CompositeChange composite= new CompositeChange(label);
  // // composite.add(new CreatePackageChange(newPack));
  // // composite.add(new MoveCompilationUnitChange(cu, newPack));
  // }
  // });
  // }
}
