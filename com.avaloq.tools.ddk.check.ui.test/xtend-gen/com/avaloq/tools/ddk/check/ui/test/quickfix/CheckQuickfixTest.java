/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.ui.test.quickfix;

import com.avaloq.tools.ddk.check.ui.quickfix.Messages;
import com.avaloq.tools.ddk.check.ui.test.quickfix.AbstractCheckQuickfixTest;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.validation.Issue;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class CheckQuickfixTest extends AbstractCheckQuickfixTest {
  @Override
  protected void registerRequiredSources() {
    String _testSourceFileName = this.getTestSourceFileName();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.avaloq.test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("catalog TestCatalog for grammar org.eclipse.xtext.Xtext");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("/** Missing import test */");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("warning TestWarning \"Test Warning\" ()");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("message \"This is a Test Warning\" {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("for AbstractRule c {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("issue");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.addKernelSourceToWorkspace(_testSourceFileName, _builder);
  }
  
  @Override
  protected String getTestSourceFileName() {
    return "com/avaloq/test/TestCatalog.check";
  }
  
  @Test
  @BugTest(value = "DSL-244")
  public void testImportFix() {
    final String quickfixLabel = "Import \'AbstractRule\' (org.eclipse.xtext)";
    AbstractXtextTestUtil _xtextTestUtil = this.getXtextTestUtil();
    IXtextDocument _document = this.getDocument();
    final List<Issue> beforeIssues = _xtextTestUtil.getIssues(_document);
    this.assertHasQuickFix(Diagnostic.LINKING_DIAGNOSTIC, quickfixLabel);
    this.assertQuickFixSuccessful(Diagnostic.LINKING_DIAGNOSTIC, quickfixLabel);
    AbstractXtextTestUtil _xtextTestUtil_1 = this.getXtextTestUtil();
    IXtextDocument _document_1 = this.getDocument();
    final List<Issue> afterIssues = _xtextTestUtil_1.getIssues(_document_1);
    int _size = afterIssues.size();
    int _size_1 = beforeIssues.size();
    boolean _lessThan = (_size < _size_1);
    Assert.assertTrue(_lessThan);
  }
  
  /**
   * Test the Add ID quickfix.
   */
  @Test
  public void testAddID() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package com.avaloq.test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("catalog TestCatalog");
    _builder.newLine();
    _builder.append("for grammar org.eclipse.xtext.Xtext {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("warning \"Test Warning\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("message \"This is a Test Warning\" {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final String sourceContent = _builder.toString();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package com.avaloq.test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("catalog TestCatalog");
    _builder_1.newLine();
    _builder_1.append("for grammar org.eclipse.xtext.Xtext {");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("warning TestWarning \"Test Warning\"");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("message \"This is a Test Warning\" {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    final String expectedContent = _builder_1.toString();
    String _testSourceFileName = this.getTestSourceFileName();
    this.assertQuickFixExistsAndSuccessfulInCustomerSource(IssueCodes.MISSING_ID_ON_CHECK, 
      Messages.CheckQuickfixProvider_ADD_ID_LABEL, _testSourceFileName, sourceContent, expectedContent);
  }
}
