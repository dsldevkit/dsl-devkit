/*
 * generated by Xtext
 */
package com.avaloq.tools.ddk.check.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class CheckAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("com/avaloq/tools/ddk/check/parser/antlr/internal/InternalCheck.tokens");
	}
}
