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
package com.avaloq.tools.ddk.check.ui.test.quickfix;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.List;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.validation.Issue;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.check.ui.quickfix.Messages;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
import com.avaloq.tools.ddk.test.core.Retry;
import com.avaloq.tools.ddk.test.core.jupiter.BugTest;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;
import com.avaloq.tools.ddk.test.ui.swtbot.condition.WaitForEquals;
import com.avaloq.tools.ddk.test.ui.swtbot.util.ProblemsViewTestUtil;
import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.avaloq.tools.ddk.xtext.test.XtextTestSource;


/**
 * Test quickfixes for Check files.
 */
@SuppressWarnings("nls")
public class CheckQuickfixTest extends AbstractCheckQuickfixTest {

  private static final String PACKAGE_NAME = "com.avaloq.test";

  private final SwtWorkbenchBot bot = new SwtWorkbenchBot();
  private boolean oldAutoBuildState;

  public String getTestSourceFileName(final String catalogName) {
    StringBuilder builder = new StringBuilder();
    builder.append(PACKAGE_NAME.replace(".", "/"));
    builder.append("/");
    builder.append(catalogName);
    builder.append(".");
    builder.append(getXtextTestUtil().getFileExtension());
    return builder.toString();
  }

  @Override
  protected String getTestSourceFileName() {
    return getTestSourceFileName(getTestSourceModelName());
  }

  @Override
  protected void registerRequiredSources() {
  }

  @Override
  protected XtextTestSource getTestSource() {
    return null;
  }

  @Override
  protected void beforeEachTest() {
    super.beforeEachTest();
    oldAutoBuildState = getTestProjectManager().setAutobuild(true);
    cleanUp();
  }

  @Override
  protected void afterEachTest() {
    getTestProjectManager().setAutobuild(oldAutoBuildState);
    cleanUp();
    super.afterEachTest();
  }

  /**
   * Close all shells and editors and remove all sources.
   */
  private void cleanUp() {
    bot.closeAllShells();
    bot.closeAllEditors();
    Collection<TestSource> testSources = getTestProjectManager().getTestSources();
    for (final TestSource testSource : testSources) {
      getTestProjectManager().removeTestSource(testSource);
    }
  }

  @Test
  @BugTest(value = "DSL-244")
  public void testImportFix() {
    StringBuilder builder = new StringBuilder();
    builder.append("package ");
    builder.append(PACKAGE_NAME);
    builder.append("\n");
    builder.append("\n");
    builder.append("catalog ");
    builder.append(getTestSourceModelName());
    builder.append(" for grammar org.eclipse.xtext.Xtext\n");
    builder.append("{\n");
    builder.append("  /** Missing import test */\n");
    builder.append("  warning TestWarning \"Test Warning\"\n");
    builder.append("  message \"This is a Test Warning\" {\n");
    builder.append("    for AbstractRule c {\n");
    builder.append("      issue\n");
    builder.append("    }\n");
    builder.append("  }\n");
    builder.append("}\n");
    createTestSource(getTestSourceFileName(), builder.toString());
    openEditor(getTestSourceFileName());
    final String quickfixLabel = "Import 'AbstractRule' (org.eclipse.xtext)";
    final List<Issue> beforeIssues = getXtextTestUtil().getIssues(getDocument());
    assertHasQuickFix(Diagnostic.LINKING_DIAGNOSTIC, quickfixLabel);
    assertQuickFixSuccessful(Diagnostic.LINKING_DIAGNOSTIC, quickfixLabel);
    final List<Issue> afterIssues = getXtextTestUtil().getIssues(getDocument());
    assertTrue(afterIssues.size() < beforeIssues.size());
  }

  /**
   * Test the Add ID quickfix.
   */
  @Test
  public void testAddID() {
    // ARRANGE
    StringBuilder builder = new StringBuilder();
    builder.append("package ");
    builder.append(PACKAGE_NAME);
    builder.append("\n");
    builder.append("\n");
    builder.append("catalog ");
    builder.append(getTestSourceModelName());
    builder.append("\n");
    builder.append("for grammar org.eclipse.xtext.Xtext {\n");
    builder.append("\n");
    builder.append("  warning \"Test Warning\"\n");
    builder.append("  message \"This is a Test Warning\" {\n");
    builder.append("  }\n");
    builder.append("}\n");
    final String sourceContent = builder.toString();

    StringBuilder builder2 = new StringBuilder();
    builder2.append("package ");
    builder2.append(PACKAGE_NAME);
    builder2.append("\n");
    builder2.append("\n");
    builder2.append("catalog ");
    builder2.append(getTestSourceModelName());
    builder2.append("\n");
    builder2.append("for grammar org.eclipse.xtext.Xtext {\n");
    builder2.append("\n");
    builder2.append("  warning TestWarning \"Test Warning\"\n");
    builder2.append("  message \"This is a Test Warning\" {\n");
    builder2.append("  }\n");
    builder2.append("}\n");
    final String expectedContent = builder2.toString();

    // ACT and ASSERT
    assertQuickFixExistsAndSuccessfulInCustomerSource(IssueCodes.MISSING_ID_ON_CHECK,
        Messages.CheckQuickfixProvider_ADD_ID_LABEL, getTestSourceFileName(), sourceContent, expectedContent);
  }

  /**
   * Test bulk-applying a quickfix.
   * Tolerate up to ten timeouts, because occasionally new markers don't appear or not all markers are fixed;
   * the problems appears to be in Eclipse.
   */
  @Test
  @Retry(10)
  public void testBulkApplyingQuickfix() {
    // ARRANGE
    final List<String> catalogNames = List.of(getTestSourceModelName(), getTestSourceModelName() + "2");
    final List<String> checkLabels = List.of("Check with no explicit ID", "Another check with no explicit ID");
    final int expectedMarkers = catalogNames.size() * checkLabels.size();

    // Show all error markers
    ProblemsViewTestUtil.showProblemsView(bot);
    ProblemsViewTestUtil.showAllErrors(bot);
    ProblemsViewTestUtil.groupByNone(bot);

    // Add catalogs containing multiple instances of the same quickfixable marker
    for (final String catalogName : catalogNames) {
      StringBuilder builder = new StringBuilder();
      builder.append("package ");
      builder.append(PACKAGE_NAME);
      builder.append("\n");
      builder.append("\n");
      builder.append("catalog ");
      builder.append(catalogName);
      builder.append("\n");
      builder.append("for grammar org.eclipse.xtext.Xtext {\n");
      for (final String checkLabel : checkLabels) {
        builder.append("\n");
        builder.append("  live error \"");
        builder.append(checkLabel);
        builder.append("\"\n");
        builder.append("  message \"");
        builder.append(checkLabel);
        builder.append("\" {\n");
        builder.append("  }\n");
      }
      builder.append("}\n");
      createTestSource(getTestSourceFileName(catalogName), builder.toString());
    }

    // Build the catalogs, and wait for the expected markers to appear
    getTestProjectManager().build();
    final SWTBotTree markersTreeBot = ProblemsViewTestUtil.getMarkersTree(bot);
    bot.waitUntil(new WaitForEquals<>("Not all expected markers appeared.", () -> expectedMarkers, () -> markersTreeBot.getAllItems().length));

    // ACT
    // Disable autobuilding, to avoid losing focus while selecting markers
    getTestProjectManager().setAutobuild(false);
    getTestProjectManager().build();

    // Bulk-apply quickfixes on all markers, ensuring that all markers remain selected
    ProblemsViewTestUtil.bulkApplyQuickfix(bot, Messages.CheckQuickfixProvider_ADD_ID_LABEL, markersTreeBot.getAllItems());
    bot.waitUntil(new WaitForEquals<>("Not all markers are still selected.", () -> expectedMarkers, () -> markersTreeBot.selectionCount()));

    // Save all modified files and build the catalogs
    bot.editors().forEach(editor -> editor.save());
    getTestProjectManager().setAutobuild(true);
    getTestProjectManager().build();

    // ASSERT
    // Check that all markers are fixed
    try {
      bot.waitUntil(new WaitForEquals<>("Some markers were not quickfixed.", () -> 0, () -> markersTreeBot.getAllItems().length));
    } catch (TimeoutException exception) {
      fail(exception.getMessage());
    }
  }
}
