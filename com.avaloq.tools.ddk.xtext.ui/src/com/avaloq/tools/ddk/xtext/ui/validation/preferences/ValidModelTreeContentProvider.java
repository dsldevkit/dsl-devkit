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
package com.avaloq.tools.ddk.xtext.ui.validation.preferences;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.avaloq.tools.ddk.xtext.ui.validation.ValidElement;
import com.avaloq.tools.ddk.xtext.ui.validation.ValidExtension;
import com.avaloq.tools.ddk.xtext.ui.validation.ValidExtensionPointManager;
import com.avaloq.tools.ddk.xtext.ui.validation.preferences.PreferenceRule.EvaluationModeKind;
import com.avaloq.tools.ddk.xtext.ui.validation.preferences.PreferenceRule.SeverityKind;


/**
 * The Class ValidModelTreeContentProvider.
 */
class ValidModelTreeContentProvider implements ITreeContentProvider {

  private static final String TRUE = "true"; //$NON-NLS-1$
  private static final String EXPENSIVE = "EXPENSIVE"; //$NON-NLS-1$
  private static final String MESSAGE = "message"; //$NON-NLS-1$
  private static final String SEVERITY = "severity"; //$NON-NLS-1$
  private static final String OPTIONAL = "optional"; //$NON-NLS-1$
  private static final String FAST = "FAST"; //$NON-NLS-1$
  private static final String WARNING = "WARNING"; //$NON-NLS-1$
  private static final String DESCRIPTION = "description"; //$NON-NLS-1$
  private static final String LABEL = "label"; //$NON-NLS-1$
  private static final String NAME = "name"; //$NON-NLS-1$
  private static final String EVALUATION_MODE = "evaluationMode"; //$NON-NLS-1$

  /** The valid preference page. */
  private final AbstractValidPreferencePage validPreferencePage;

  /**
   * Instantiates a new valid model tree content provider.
   *
   * @param validPreferencePage
   *          the valid preference page
   */
  ValidModelTreeContentProvider(final AbstractValidPreferencePage validPreferencePage) {
    this.validPreferencePage = validPreferencePage;
  }

  /** {@inheritDoc} */
  @Override
  public Object[] getChildren(final Object treeItem) {
    return (treeItem instanceof PreferenceCategory) ? ((PreferenceCategory) treeItem).getRules() : new Object[0];
  }

  /** {@inheritDoc} */
  @Override
  public Object getParent(final Object treeItem) {
    return ((IPreferenceItem) treeItem).getParent();
  }

  /** {@inheritDoc} */
  @Override
  public boolean hasChildren(final Object treeItem) {
    return treeItem instanceof PreferenceCategory && ((PreferenceCategory) treeItem).hasRules();
  }

  /** {@inheritDoc} */
  @Override
  public Object[] getElements(final Object arg0) {
    if (this.validPreferencePage.getCategories() == null) {
      this.validPreferencePage.setCategories(new ArrayList<IPreferenceItem>());

      final ValidExtension[] extensions = ValidExtensionPointManager.getExtensions();
      for (final ValidExtension validExtension : extensions) {
        for (final ValidElement validElement : validExtension.getTopLevelElements()) {
          if (validElement.getLanguage().equals(this.validPreferencePage.getLanguageName())) {
            final IConfigurationElement topElement = validElement.getConfigurationElement();
            for (final IConfigurationElement categoryProxy : topElement.getChildren("category")) { //$NON-NLS-1$
              final PreferenceCategory category = createPreferenceCategory(categoryProxy);
              for (final IConfigurationElement ruleProxy : categoryProxy.getChildren("rule")) { //$NON-NLS-1$
                category.addRule(createPreferenceRule(ruleProxy));
              }
              this.validPreferencePage.getCategories().add(category);
            }
          }
        }
      }
    }
    return this.validPreferencePage.getCategories().toArray(new IPreferenceItem[this.validPreferencePage.getCategories().size()]);
  }

  /**
   * Creates a preference category object based on a configuration element.
   *
   * @param categoryProxy
   *          the configuration element
   * @return the preference category
   */
  private PreferenceCategory createPreferenceCategory(final IConfigurationElement categoryProxy) {
    return new PreferenceCategory(categoryProxy.getAttribute(NAME), categoryProxy.getAttribute(LABEL), categoryProxy.getAttribute(DESCRIPTION));
  }

  /**
   * Get the {@link EvaluationModeKind} of a given rule.
   *
   * @param ruleProxy
   *          the {@link IConfigurationElement} associated to the rule to get the {@link EvaluationModeKind} for, must not be {@code null}
   * @return the {@link EvaluationModeKind} found, never {@code null}
   */
  private EvaluationModeKind getEvaluationMode(final IConfigurationElement ruleProxy) {
    return ruleProxy.getAttribute(EVALUATION_MODE).equalsIgnoreCase(FAST) ? EvaluationModeKind.FAST
        : ruleProxy.getAttribute(EVALUATION_MODE).equalsIgnoreCase(EXPENSIVE) ? EvaluationModeKind.EXPENSIVE : EvaluationModeKind.NORMAL;
  }

  /**
   * Get the {@link SeverityKind} of a given rule.
   *
   * @param ruleProxy
   *          the {@link IConfigurationElement} associated to the rule to get the {@link SeverityKind} for, must not be {@code null}
   * @return the {@link SeverityKind} found, never {@code null}
   */
  private SeverityKind getSeverityKind(final IConfigurationElement ruleProxy) {
    return ruleProxy.getAttribute(SEVERITY).equalsIgnoreCase(WARNING) ? SeverityKind.WARNING : SeverityKind.ERROR;
  }

  /**
   * Check whether a given {@link IConfigurationElement} is an optional rule.
   *
   * @param ruleProxy
   *          the {@link IConfigurationElement} to check, must not be {@code null}
   * @return {@code true} if the associated rule is optional, otherwise {@code false}
   */
  private boolean isRuleOptional(final IConfigurationElement ruleProxy) {
    return ruleProxy.getAttribute(OPTIONAL).equalsIgnoreCase(TRUE) ? true : false;
  }

  /**
   * Creates the preference rule object based on a configuration element.
   *
   * @param ruleProxy
   *          the configuration element
   * @return the preference rule
   */
  private PreferenceRule createPreferenceRule(final IConfigurationElement ruleProxy) {
    return new PreferenceRule(ruleProxy.getAttribute(NAME), ruleProxy.getAttribute(LABEL), ruleProxy.getAttribute(DESCRIPTION), ruleProxy.getAttribute(MESSAGE), getSeverityKind(ruleProxy), getEvaluationMode(ruleProxy), isRuleOptional(ruleProxy));
  }

  /** {@inheritDoc} */
  @Override
  public void dispose() {
    // Nothing to dispose
  }

  /** {@inheritDoc} */
  @Override
  public void inputChanged(final Viewer arg0, final Object arg1, final Object arg2) {
    // Nothing to change
  }
}