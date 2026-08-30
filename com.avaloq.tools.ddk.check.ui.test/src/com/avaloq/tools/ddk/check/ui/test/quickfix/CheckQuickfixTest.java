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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.validation.Issue;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.check.ui.quickfix.Messages;
import com.avaloq.tools.ddk.check.validation.IssueCodes;
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
// CHECKSTYLE:CONSTANTS-OFF
public class CheckQuickfixTest extends AbstractCheckQuickfixTest {

  private static final String PACKAGE_NAME = "com.avaloq.test";
  private static final long ASYNC_UI_TIMEOUT = 60000;

  private final SwtWorkbenchBot bot = new SwtWorkbenchBot();
  private boolean oldAutoBuildState;

  public String getTestSourceFileName(final String catalogName) {
    return PACKAGE_NAME.replace(".", "/") + '/' + catalogName + '.' + getXtextTestUtil().getFileExtension();
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
    final String source = """
        package %s

        catalog %s for grammar org.eclipse.xtext.Xtext
        {
          /** Missing import test */
          warning TestWarning "Test Warning"
          message "This is a Test Warning" {
            for AbstractRule c {
              issue
            }
          }
        }
        """.formatted(PACKAGE_NAME, getTestSourceModelName());
    createTestSource(getTestSourceFileName(), source);
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
    final String sourceContent = """
        package %s

        catalog %s
        for grammar org.eclipse.xtext.Xtext {

          warning "Test Warning"
          message "This is a Test Warning" {
          }
        }
        """.formatted(PACKAGE_NAME, getTestSourceModelName());

    final String expectedContent = """
        package %s

        catalog %s
        for grammar org.eclipse.xtext.Xtext {

          warning TestWarning "Test Warning"
          message "This is a Test Warning" {
          }
        }
        """.formatted(PACKAGE_NAME, getTestSourceModelName());

    // ACT and ASSERT
    assertQuickFixExistsAndSuccessfulInCustomerSource(IssueCodes.MISSING_ID_ON_CHECK, Messages.CheckQuickfixProvider_ADD_ID_LABEL, getTestSourceFileName(), sourceContent, expectedContent);
  }

  /**
   * Test bulk-applying a quickfix to multiple markers via the Problems view's Quick Fix dialog.
   */
  @Test
  public void testBulkApplyingQuickfix() {
    // ARRANGE
    final List<String> catalogNames = List.of(getTestSourceModelName(), getTestSourceModelName() + "2");
    final String label1 = "Check with no explicit ID";
    final String label2 = "Another check with no explicit ID";
    final List<String> checkLabels = List.of(label1, label2);
    final int expectedMarkers = catalogNames.size() * checkLabels.size();

    // Show all error markers
    ProblemsViewTestUtil.showProblemsView(bot);
    ProblemsViewTestUtil.showAllErrors(bot);
    ProblemsViewTestUtil.groupByNone(bot);

    // Add catalogs containing multiple instances of the same quickfixable marker
    final List<XtextTestSource> catalogSources = catalogNames.stream().map(catalogName -> createTestSource(getTestSourceFileName(catalogName), """
          package %s

          catalog %s
          for grammar org.eclipse.xtext.Xtext {

            live error "%s"
            message "%s" {
            }

            live error "%s"
            message "%s" {
            }
          }
          """.formatted(PACKAGE_NAME, catalogName, label1, label1, label2, label2))).toList();

    // Build the catalogs, and wait for the expected markers to appear
    getTestProjectManager().build();
    waitForWorkspaceMarkers(catalogSources, expectedMarkers);
    final SWTBotTree markersTreeBot = ProblemsViewTestUtil.getMarkersTree(bot);
    final Set<String> sourceFileNames = catalogSources.stream().map(source -> source.getiFile().getName()).collect(Collectors.toSet());
    Predicate<? super SWTBotTreeItem> checkResourceFilter = item -> sourceFileNames.contains(item.row().get(1));
    waitForProblemsViewMarkers(markersTreeBot, checkResourceFilter, expectedMarkers, "Not all expected markers appeared in the Problems view.");

    // ACT
    // Disable autobuilding, to avoid losing focus while selecting markers
    getTestProjectManager().setAutobuild(false);
    getTestProjectManager().build();

    // Bulk-apply quickfixes on all markers, ensuring that all markers remain selected
    ProblemsViewTestUtil.bulkApplyQuickfix(bot, Messages.CheckQuickfixProvider_ADD_ID_LABEL, Arrays.stream(markersTreeBot.getAllItems()).filter(checkResourceFilter).toArray(SWTBotTreeItem[]::new));
    bot.waitUntil(new WaitForEquals<>("Not all markers are still selected.", () -> expectedMarkers, () -> markersTreeBot.selectionCount()), ASYNC_UI_TIMEOUT);

    // Save all modified files and build the catalogs
    bot.editors().forEach(editor -> editor.save());
    getTestProjectManager().setAutobuild(true);
    getTestProjectManager().build();

    // ASSERT
    // Check that all markers are fixed
    waitForWorkspaceMarkers(catalogSources, 0);
    waitForProblemsViewMarkers(markersTreeBot, checkResourceFilter, 0, "Some markers were not removed from the Problems view.");
  }

  private void waitForWorkspaceMarkers(final List<XtextTestSource> catalogSources, final int expectedMarkers) {
    bot.waitUntil(new WaitForEquals<>("Workspace marker count did not reach the expected value.", () -> expectedMarkers, () -> countWorkspaceMarkers(catalogSources)), ASYNC_UI_TIMEOUT);
  }

  private int countWorkspaceMarkers(final List<XtextTestSource> catalogSources) {
    int markerCount = 0;
    try {
      for (XtextTestSource source : catalogSources) {
        for (IMarker marker : source.getiFile().findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ZERO)) {
          if (IssueCodes.MISSING_ID_ON_CHECK.equals(marker.getAttribute(Issue.CODE_KEY))) {
            markerCount++;
          }
        }
      }
      return markerCount;
    } catch (CoreException e) {
      throw new WrappedException("Could not read workspace markers.", e);
    }
  }

  private void waitForProblemsViewMarkers(final SWTBotTree markersTreeBot, final Predicate<? super SWTBotTreeItem> markerFilter, final int expectedMarkers, final String failureMessage) {
    bot.waitUntil(new WaitForEquals<>(failureMessage, () -> expectedMarkers, () -> Arrays.stream(markersTreeBot.getAllItems()).filter(markerFilter).toList().size()), ASYNC_UI_TIMEOUT);
  }
}
// CHECKSTYLE:CONSTANTS-ON
