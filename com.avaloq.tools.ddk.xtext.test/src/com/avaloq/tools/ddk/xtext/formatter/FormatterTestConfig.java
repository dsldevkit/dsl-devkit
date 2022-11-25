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
package com.avaloq.tools.ddk.xtext.formatter;

import java.util.List;

import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.formatting.IIndentationInformation;

import com.avaloq.tools.ddk.xtext.formatter.services.FormatterTestLanguageGrammarAccess;
import com.avaloq.tools.ddk.xtext.formatting.AbstractExtendedFormatter;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry;
import com.avaloq.tools.ddk.xtext.formatting.IIndentationInformationWithDefaults;
import com.google.inject.Inject;


public class FormatterTestConfig extends AbstractExtendedFormatter {

  private final IIndentationInformation indentInfo = new IIndentationInformationWithDefaults() {
    @Override
    public String getIndentString() {
      return "\t"; //$NON-NLS-1$
    }

    @Override
    public int getDefaultIndentation() {
      return 1;
    }
  };

  @Override
  protected IIndentationInformation getIndentInfo() {
    return indentInfo;
  }

  @Inject
  private FormatterTestLanguageGrammarAccess grammarAccess;

  @Override
  protected void configureAcsFormatting(final ExtendedFormattingConfig c) {
    FormatterTestLanguageGrammarAccess f = (FormatterTestLanguageGrammarAccess) getGrammarAccess();

    c.setAutoLinewrap(30);

    // Root
    c.setColumn(0).before(f.getRootAccess().getTestKeyword_0());

    // TestLinewrap
    c.setLinewrap().after(f.getTestLinewrapAccess().getLinewrapKeyword_1());

    // Line
    c.setLinewrap().after(f.getLineAccess().getSemicolonKeyword_1());
    c.setNoSpace().before(f.getLineAccess().getSemicolonKeyword_1());

    // TestIndentation
    c.setIndentationIncrement().after(f.getTestIndentationAccess().getLeftCurlyBracketKeyword_2());
    c.setIndentationDecrement().before(f.getTestIndentationAccess().getRightCurlyBracketKeyword_4());
    c.setIndentationIncrement().after(f.getMethAccess().getLeftParenthesisKeyword_2());
    c.setIndentationDecrement().before(f.getMethAccess().getRightParenthesisKeyword_4());
    c.setLinewrap().after(f.getTestIndentationAccess().getLeftCurlyBracketKeyword_2());
    c.setLinewrap().after(f.getTestIndentationAccess().getRightCurlyBracketKeyword_4());
    c.setLinewrap().after(f.getTestIndentationAccess().getSemiAssignment_5());
    c.setNoLinewrap().between(f.getTestIndentationAccess().getRightCurlyBracketKeyword_4(), f.getTestIndentationAccess().getSemiAssignment_5());
    c.setNoSpace().before(f.getTestIndentationAccess().getSemiAssignment_5());

    // TestColumn
    c.setLinewrap().before(f.getTestColumnAccess().getColumnKeyword_1());
    c.setColumn(2).before(f.getTestColumnAccess().getColumnKeyword_1());
    c.setColumn(2).before(f.getTestColumnAccess().getNameAssignment_2());
    c.setLinewrap(2).after(f.getTestColumnAccess().getNameAssignment_2());
    c.setAbsoluteColumnFormatting(f.getTestColumnAccess().getItemKeyword_3_0(), "    column     ".length());

    // TestOffset
    c.setLinewrap().before(f.getTestOffsetAccess().getOffsetKeyword_1());
    c.setLinewrap().before(f.getTestOffsetAccess().getValueKeyword_2());
    c.setLinewrap().before(f.getTestOffsetAccess().getPairKeyword_4());
    c.setOffset("value    ".length()).after(f.getTestOffsetAccess().getValueKeyword_2());
    c.increaseIndentationAround(f.getTestOffsetAccess().getValueKeyword_2());
    c.setOffset("pair  ".length()).after(f.getTestOffsetAccess().getPairKeyword_4());
    c.setOffset("pair      ".length()).after(f.getTestOffsetAccess().getFirstAssignment_5());
    c.increaseIndentationAround(f.getTestOffsetAccess().getPairKeyword_4());
    c.increaseIndentationAround(f.getTestOffsetAccess().getPairKeyword_4());

    // TestPadding
    c.setLinewrap().before(f.getTestRightPaddingAccess().getPaddingKeyword_0());
    c.setRightPadding(0).after(f.getTestRightPaddingAccess().getPaddingKeyword_0());
    c.setRightPadding(5).after(f.getTestRightPaddingAccess().getP1Assignment_1());
    c.setRightPadding(5).after(f.getTestRightPaddingAccess().getP2Assignment_2());

    // Assign
    c.setNoSpace().around(f.getAssignAccess().getOpAssignment_1());
    c.setNoSpace().before(f.getAssignAccess().getCommaKeyword_3_1_0());

    // Meth
    c.setNoSpace().around(f.getMethAccess().getLeftParenthesisKeyword_2());
    c.setNoSpace().before(f.getMethAccess().getRightParenthesisKeyword_4());
    c.setNoSpace().around(f.getMethAccess().getCommaKeyword_3_1_0());
    c.setNoLinewrap().before(f.getMethAccess().getCommaKeyword_3_1_0());
    c.setIndentation(f.getMethAccess().getLeftParenthesisKeyword_2(), f.getMethAccess().getRightParenthesisKeyword_4());

    // Param
    c.setNoLinewrap().around(f.getParamAccess().getColonKeyword_1());
    c.setNoSpace().around(f.getParamAccess().getColonKeyword_1());

    // Space
    c.setSpace("     ").after(f.getSpaceAccess().getSpaceKeyword_0());

    // TestLinewrapMinMax
    c.setLinewrap(2, 3, 5).after(f.getTestLinewrapMinMaxAccess().getWrapminmaxKeyword_1());

    // FqnObj
    c.setLinewrap().before(f.getFqnObjAccess().getNameFQNParserRuleCall_1_0());

    // FqnRef
    c.setLinewrap().before(f.getFqnRefAccess().getRefAssignment_1());

    // Enumeration
    c.setNoSpace().between(f.getEnumerationAccess().getValAssignment_2_1(), f.getEnumerationAccess().getCommaKeyword_2_0());
    c.setLinewrap().before(f.getEnumerationAccess().getValEnum1EnumRuleCall_2_1_0());

    // Datatypes
    c.setLinewrap().after(f.getDatatype1Rule());
    c.setLinewrap().before(f.getDatatype2Rule());
    c.setLinewrap().between(f.getDatatype2Rule(), f.getDatatype3Rule());
    c.setLinewrap().between(f.getDatatype3Rule(), f.getDatatypesAccess().getKw3Keyword_5());

    // comments
    c.setNoLinewrap().before(f.getSL_COMMENTRule());

  }

  @Override
  protected TerminalRule getSLCommentRule() {
    return grammarAccess.getSL_COMMENTRule();
  }

  @Override
  protected TerminalRule getMLCommentRule() {
    return grammarAccess.getML_COMMENTRule();
  }

  @Override
  protected boolean isUnformattedContent(final String content) {
    return false;
  }

  @Override
  public String executeCustomPostFormatAction(final ExtendedLineEntry lineEntry, final List<ExtendedLineEntry> previousEntries) {
    return null;
  }
}
