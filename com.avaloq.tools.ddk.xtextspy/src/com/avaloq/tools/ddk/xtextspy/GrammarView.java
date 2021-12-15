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
package com.avaloq.tools.ddk.xtextspy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumLiteralDeclaration;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.util.XtextSwitch;


/**
 * A composite with displaying the name of the Xtext rule corresponding to the current selection.
 */
public class GrammarView extends Composite implements ISelectionChangedListener {

  private static final String EMPTY_STRING = ""; //$NON-NLS-1$
  private static final int COLUMNS = 4;
  private final Text grammarText;
  private final Text ruleText;
  private final Text offsetText;
  private final Text nodeText;

  /**
   * Instantiates a new grammar view.
   * 
   * @param parent
   *          the parent
   * @param style
   *          the style
   */
  public GrammarView(final Composite parent, final int style) {
    super(parent, style);
    GridLayout layout = new GridLayout(COLUMNS, false);
    setLayout(layout);

    GridData gridData = new GridData();
    Label grammarLabel = new Label(this, SWT.NONE);
    grammarLabel.setText(Messages.GrammarView_GrammarLabel);
    grammarLabel.setLayoutData(gridData);

    gridData = new GridData();
    grammarText = new Text(this, SWT.READ_ONLY);
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    grammarText.setLayoutData(gridData);

    gridData = new GridData();
    Label nodeLabel = new Label(this, SWT.NONE);
    nodeLabel.setText(Messages.GrammarView_NodeLabel);
    nodeLabel.setLayoutData(gridData);

    nodeText = new Text(this, SWT.READ_ONLY);
    gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    nodeText.setLayoutData(gridData);

    gridData = new GridData();
    Label ruleLabel = new Label(this, SWT.NONE);
    ruleLabel.setText(Messages.GrammarView_RuleLabel);
    ruleLabel.setLayoutData(gridData);

    ruleText = new Text(this, SWT.READ_ONLY);
    gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    ruleText.setLayoutData(gridData);

    gridData = new GridData();
    Label offsetLabel = new Label(this, SWT.NONE);
    offsetLabel.setText(Messages.GrammarView_OffsetLabel);
    offsetLabel.setLayoutData(gridData);

    offsetText = new Text(this, SWT.READ_ONLY);
    gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    offsetText.setLayoutData(gridData);
  }

  /**
   * Set the parser rule name corresponding to the selection. {@inheritDoc}
   * 
   * @param event
   *          the event
   */
  @SuppressWarnings("PMD.NPathComplexity")
  public void selectionChanged(final SelectionChangedEvent event) {
    XtextElementSelectionListener source = (XtextElementSelectionListener) event.getSelectionProvider();
    AbstractRule rule = source.getRule();
    EObject nodeAbstractElement = source.getNodeGrammarElement();
    String grammarName = rule == null ? (nodeAbstractElement == null ? EMPTY_STRING : GrammarUtil.getGrammar(nodeAbstractElement).getName())
        : GrammarUtil.getGrammar(rule).getName();
    ruleText.setText(rule == null ? EMPTY_STRING : rule.getName());
    grammarText.setText(grammarName);
    nodeText.setText(getText(nodeAbstractElement));
    Integer offset = source.getOffset();
    offsetText.setText(offset == null ? EMPTY_STRING : offset.toString());
  }

  /**
   * Gets the text.
   * 
   * @param grammarElement
   *          the grammar element
   * @return the text
   */
  private String getText(final EObject grammarElement) {
    if (grammarElement == null) {
      return EMPTY_STRING;
    }
    return new XtextSwitch<String>() {
      @Override
      public String caseKeyword(final Keyword object) {
        return Keyword.class.getSimpleName();
      }

      @Override
      public String caseTerminalRule(final TerminalRule object) {
        return object.getName();
      }

      @Override
      public String caseEnumRule(final EnumRule object) {
        return object.getName();
      }

      @Override
      @SuppressWarnings("nls")
      public String caseEnumLiteralDeclaration(final EnumLiteralDeclaration object) {
        return "EnumLiteral";
      }

      @Override
      public String caseRuleCall(final RuleCall object) {
        return object.getRule().getName();
      }

      @Override
      public String caseCrossReference(final CrossReference object) {
        return doSwitch(object.getTerminal());
      }

      @Override
      public String defaultCase(final EObject object) {
        return EMPTY_STRING;
      }
    }.doSwitch(grammarElement);
  }

}

