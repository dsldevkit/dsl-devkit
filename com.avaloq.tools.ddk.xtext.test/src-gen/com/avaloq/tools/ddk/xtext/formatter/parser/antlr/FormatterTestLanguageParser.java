/*
 * generated by Xtext
 */
package com.avaloq.tools.ddk.xtext.formatter.parser.antlr;

import com.avaloq.tools.ddk.xtext.formatter.parser.antlr.internal.InternalFormatterTestLanguageParser;
import com.avaloq.tools.ddk.xtext.formatter.services.FormatterTestLanguageGrammarAccess;
import com.google.inject.Inject;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;

public class FormatterTestLanguageParser extends AbstractAntlrParser {

	@Inject
	private FormatterTestLanguageGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalFormatterTestLanguageParser createParser(XtextTokenStream stream) {
		return new InternalFormatterTestLanguageParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Root";
	}

	public FormatterTestLanguageGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(FormatterTestLanguageGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
