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
package com.avaloq.tools.ddk.checkcfg.ui.quickfix;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;
import com.avaloq.tools.ddk.checkcfg.checkcfg.SeverityKind;
import com.avaloq.tools.ddk.checkcfg.validation.IssueCodes;


/**
 * Configures the Check Configuration quickfix provider.
 */
public class CheckCfgQuickfixProvider extends DefaultQuickfixProvider {

  private static final String NO_IMAGE = null;

  /**
   * Removes a duplicate catalog configuration.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.DUPLICATE_CATALOG_CONFIGURATION)
  public void removeDuplicateCatalogConfiguration(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CATALOG_LABEL, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CATALOG_DESCN, null, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        CheckConfiguration configuration = EcoreUtil2.getContainerOfType(element, CheckConfiguration.class);
        configuration.getLegacyCatalogConfigurations().remove(element);
      }
    });
  }

  /**
   * Fix severity by setting it to a legal value as is defined by severity range of referenced check. Legal
   * severities are passed as issue data (org.eclipse.xtext.validation.Issue#getData()).
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.SEVERITY_NOT_ALLOWED)
  public void fixSeverityToMaxSeverity(final Issue issue, final IssueResolutionAcceptor acceptor) {
    if (issue.getData() != null) {
      for (final String severityProposal : issue.getData()) {
        final String label = NLS.bind(Messages.CheckCfgQuickfixProvider_CORRECT_SEVERITY_LABEL, severityProposal);
        final String descn = NLS.bind(Messages.CheckCfgQuickfixProvider_CORRECT_SEVERITY_DESCN, severityProposal);

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
   * Removes a duplicate check configuration.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.DUPLICATE_CHECK_CONFIGURATION)
  public void removeDuplicateCheckConfiguration(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CHECK_LABEL, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_CHECK_DESCN, null, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        ConfiguredCatalog catalog = EcoreUtil2.getContainerOfType(element, ConfiguredCatalog.class);
        catalog.getCheckConfigurations().remove(element);
      }
    });
  }

  /**
   * Removes a duplicate parameter configuration.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.DUPLICATE_PARAMETER_CONFIGURATION)
  public void removeDuplicateParameterConfiguration(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_PARAM_LABEL, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_PARAM_DESCN, null, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        ConfiguredCheck check = EcoreUtil2.getContainerOfType(element, ConfiguredCheck.class);
        check.getParameterConfigurations().remove(element);
      }
    });
  }

  /**
   * Removes a duplicate language configuration.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.DUPLICATE_LANGUAGE_CONFIGURATION)
  public void removeDuplicateLanguageConfiguration(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_LANG_CONFIG_LABEL, Messages.CheckCfgQuickfixProvider_REMOVE_DUPLICATE_LANG_CONFIG_DESC, null, (element, context) -> {
      final CheckConfiguration check = EcoreUtil2.getContainerOfType(element, CheckConfiguration.class);
      final String languageName = ((ConfiguredLanguageValidator) element).getLanguage();
      if (check != null && languageName != null) {
        final List<ConfiguredLanguageValidator> allMatchingLanguages = check.getLanguageValidatorConfigurations().stream().filter(langName -> languageName.equals(langName.getLanguage())).collect(Collectors.toList());
        if (allMatchingLanguages.size() <= 1) {
          return; // Something went wrong. Validation guarantees 2.
        }
        for (ConfiguredLanguageValidator duplicate : IterableExtensions.tail(allMatchingLanguages)) {
          // For each matching language after the first, merge contents into first and remove the language
          IterableExtensions.head(allMatchingLanguages).getParameterConfigurations().addAll(duplicate.getParameterConfigurations());
          IterableExtensions.head(allMatchingLanguages).getCatalogConfigurations().addAll(duplicate.getCatalogConfigurations());
          check.getLanguageValidatorConfigurations().remove(duplicate);
        }
      }
    });
  }

  /**
   * Removes the configured values of a disabled check.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.DISABLED_CHECK_NOT_CONFIGURED)
  public void removeConfiguredParamsOfDisabledCheck(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckCfgQuickfixProvider_REMOVE_CONFIGURED_PARAM_LABEL, Messages.CheckCfgQuickfixProvider_REMOVE_CONFIGURED_PARAM_DESCN, null, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        ConfiguredCheck check = EcoreUtil2.getContainerOfType(element, ConfiguredCheck.class);
        check.getParameterConfigurations().removeAll(check.getParameterConfigurations());
      }
    });
  }

  /**
   * Reset the severity of a configured check which is final to {@code default}.
   *
   * @param issue
   *          the issue
   * @param acceptor
   *          the acceptor
   */
  @Fix(IssueCodes.FINAL_CHECK_NOT_CONFIGURABLE)
  public void resetSeverityOfFinalCheck(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, Messages.CheckCfgQuickfixProvider_CORRECT_SEVERITY_OF_FINAL_CHECK_LABEL, Messages.CheckCfgQuickfixProvider_CORRECT_SEVERITY_OF_FINAL_CHECK_DESCN, null, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        ConfiguredCheck check = EcoreUtil2.getContainerOfType(element, ConfiguredCheck.class);
        check.setSeverity(SeverityKind.DEFAULT);
      }
    });
  }
}
