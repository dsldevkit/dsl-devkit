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
package com.avaloq.tools.ddk.xtext.export.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

import com.avaloq.tools.ddk.xtext.export.services.ExportGrammarAccess;
import com.avaloq.tools.ddk.xtext.export.services.ExportGrammarAccess.ExportElements;
import com.avaloq.tools.ddk.xtext.export.services.ExportGrammarAccess.ExportModelElements;
import com.avaloq.tools.ddk.xtext.export.services.ExportGrammarAccess.ExtensionElements;


/**
 * Formatting configuration for the export language.
 */
public class ExportFormatter extends AbstractDeclarativeFormatter {

  /**
   * Maximum length of a line for the formatter.
   */
  private static final int MAXIMUM_LINE_LENGTH = 140;

  @Override
  protected void configureFormatting(final FormattingConfig config) { // NOPMD NPathComplexity by wth on 24.11.10 07:39
    final ExportGrammarAccess grammar = (ExportGrammarAccess) getGrammarAccess();

    config.setAutoLinewrap(MAXIMUM_LINE_LENGTH);

    // Comments
    config.setLinewrap(0, 1, 2).before(grammar.getSL_COMMENTRule());
    config.setLinewrap(0, 1, 2).before(grammar.getML_COMMENTRule());
    config.setLinewrap(0, 1, 1).after(grammar.getML_COMMENTRule());

    // general keywords
    for (final Pair<Keyword, Keyword> pair : grammar.findKeywordPairs("(", ")")) { //$NON-NLS-1$ //$NON-NLS-2$
      config.setNoSpace().before(pair.getFirst());
      config.setNoSpace().after(pair.getFirst());
      config.setNoSpace().before(pair.getSecond());
    }
    for (final Pair<Keyword, Keyword> pair : grammar.findKeywordPairs("[", "]")) { //$NON-NLS-1$ //$NON-NLS-2$
      config.setNoSpace().after(pair.getFirst());
      config.setNoSpace().before(pair.getSecond());
    }
    for (final Pair<Keyword, Keyword> pair : grammar.findKeywordPairs("{", "}")) { //$NON-NLS-1$ //$NON-NLS-2$
      if (pair.getFirst() == grammar.getListLiteralAccess().getLeftCurlyBracketKeyword_1()) {
        continue;
      }
      config.setLinewrap().after(pair.getFirst());
      config.setIndentationIncrement().after(pair.getFirst());
      config.setLinewrap().before(pair.getSecond());
      config.setIndentationDecrement().before(pair.getSecond());
      config.setLinewrap().after(pair.getSecond());
    }

    for (final Keyword delimiter : grammar.findKeywords("@", "+")) { //$NON-NLS-1$ //$NON-NLS-2$
      if (delimiter == grammar.getAdditiveExpressionAccess().getNamePlusSignKeyword_1_1_0_0()) {
        continue;
      }
      config.setNoSpace().after(delimiter);
    }
    for (final Keyword delimiter : grammar.findKeywords(",")) { //$NON-NLS-1$
      config.setNoSpace().before(delimiter);
    }
    for (final Keyword delimiter : grammar.findKeywords(";")) { //$NON-NLS-1$
      config.setNoSpace().before(delimiter);
      config.setLinewrap().after(delimiter);
    }
    for (final Keyword delimiter : grammar.findKeywords("#", "::", ".")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      config.setNoSpace().around(delimiter);
    }

    // ExportSection
    final ExportModelElements exportModelElements = grammar.getExportModelAccess();
    config.setLinewrap(1).after(exportModelElements.getImportsAssignment_1());
    config.setLinewrap(2).between(exportModelElements.getImportsAssignment_1(), exportModelElements.getExtensionsAssignment_2());
    config.setLinewrap(1).before(exportModelElements.getExtensionsAssignment_2());
    config.setLinewrap(2).before(exportModelElements.getExportsAssignment_4());
    config.setLinewrap(2).before(exportModelElements.getGroup_3());

    // Extension
    final ExtensionElements extensionsElements = grammar.getExtensionAccess();
    config.setLinewrap().after(extensionsElements.getGroup());

    // Export
    final ExportElements exportElements = grammar.getExportAccess();
    config.setLinewrap(2).before(exportElements.getGroup());

  }

}
