/*******************************************************************************
 * Copyright (c) 2016-2018 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.ui.test.quickfix

import com.avaloq.tools.ddk.check.ui.quickfix.Messages
import com.avaloq.tools.ddk.check.validation.IssueCodes
import com.avaloq.tools.ddk.test.core.BugTest
import com.avaloq.tools.ddk.test.core.Flaky
import com.avaloq.tools.ddk.test.ui.junit.runners.SwtBotRecordingTestRunner
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot
import com.avaloq.tools.ddk.test.ui.swtbot.util.ContextActionUiTestUtil
import com.avaloq.tools.ddk.test.ui.swtbot.util.ProblemsViewTestUtil
import org.eclipse.swt.widgets.Table
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory
import org.eclipse.swtbot.swt.finder.waits.Conditions
import org.eclipse.xtext.diagnostics.Diagnostic
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * Test quickfixes for Check files.
 */
@RunWith(SwtBotRecordingTestRunner)
class CheckQuickfixTest extends AbstractCheckQuickfixTest {
  static val PACKAGE_NAME = "com.avaloq.test"

  static val QUICK_FIX_CONTEXT_MENU_ITEM = "Quick Fix"
  static val QUICK_FIX_DIALOG_TITLE = "Quick Fix"
  static val PROBLEMS_LABEL_TEXT = "Problems:"
  static val FINISH_BUTTON_TEXT = "Finish"

  val SwtWorkbenchBot bot = new SwtWorkbenchBot
  var boolean oldAutoBuildState

  def private String getTestSourceFileName(String catalogName) {
    return '''«PACKAGE_NAME.replace(".", "/")»/«catalogName».«xtextTestUtil.getFileExtension»'''
  }

  override protected getTestSourceFileName() {
    return getTestSourceFileName(testSourceModelName)
  }

  override protected registerRequiredSources() {
  }

  override protected getTestSource() {
    return null
  }

  override protected beforeEachTest() {
    super.beforeEachTest

    // Disable autobuilding, so it doesn't steal focus as we are trying to use a marker's context menu.
    // And also to speed things up a bit.
    oldAutoBuildState = testProjectManager.setAutobuild(false)
  }

  override protected afterEachTest() {
    bot.closeAllShells
    bot.closeAllEditors
    for (testSource : testProjectManager.testSources) {
      testProjectManager.removeTestSource(testSource)
    }
    testProjectManager.autobuild = oldAutoBuildState

    super.afterEachTest
  }

  @Test
  @BugTest(value="DSL-244")
  def testImportFix() {
    createTestSource(testSourceFileName, '''
      package «PACKAGE_NAME»

      catalog «testSourceModelName» for grammar org.eclipse.xtext.Xtext
      {
        /** Missing import test */
        warning TestWarning "Test Warning"
        message "This is a Test Warning" {
          for AbstractRule c {
            issue
          }
        }
      }
    ''')
    openEditor(testSourceFileName)
    val quickfixLabel = "Import 'AbstractRule' (org.eclipse.xtext)"
    val beforeIssues = xtextTestUtil.getIssues(document)
    assertHasQuickFix(Diagnostic::LINKING_DIAGNOSTIC, quickfixLabel)
    assertQuickFixSuccessful(Diagnostic::LINKING_DIAGNOSTIC, quickfixLabel)
    val afterIssues = xtextTestUtil.getIssues(document)
    Assert.assertTrue(afterIssues.size < beforeIssues.size)
  }

  /**
   * Test the Add ID quickfix.
   */
  @Test
  def testAddID() {

    // ARRANGE
    val sourceContent = '''
      package «PACKAGE_NAME»

      catalog «testSourceModelName»
      for grammar org.eclipse.xtext.Xtext {

        warning "Test Warning"
        message "This is a Test Warning" {
        }
      }
    '''
    val expectedContent = '''
      package «PACKAGE_NAME»

      catalog «testSourceModelName»
      for grammar org.eclipse.xtext.Xtext {

        warning TestWarning "Test Warning"
        message "This is a Test Warning" {
        }
      }
    '''

    // ACT and ASSERT
    assertQuickFixExistsAndSuccessfulInCustomerSource(IssueCodes::MISSING_ID_ON_CHECK,
      Messages.CheckQuickfixProvider_ADD_ID_LABEL, testSourceFileName, sourceContent, expectedContent)
  }

  /**
   * Test bulk-applying a quickfix.
   */
  @Test
  @Flaky // Occasionally, only three out of four errors get quickfixed. The problem appears to be in Eclipse.
  def void testBulkApplyingQuickfix() {

    // ARRANGE
    val catalogNames = #[testSourceModelName, testSourceModelName + "2"]
    val checkLabels = #["Check with no explicit ID", "Another check with no explicit ID"]
    val expectedErrors = catalogNames.length * checkLabels.length

    // Add catalogs containing multiple instances of the same quickfixable error
    for (catalogName : catalogNames) {
      createTestSource(getTestSourceFileName(catalogName), '''
        package «PACKAGE_NAME»

        catalog «catalogName»
        for grammar com.avaloq.tools.ddk.check.Check {
          «FOR checkLabel : checkLabels»

          live error "«checkLabel»"
          message "«checkLabel»" {
          }
          «ENDFOR»
        }
      ''')
    }

    // Build the catalogs, and wait for the expected errors to appear in the Problems view
    testProjectManager.fullBuild
    ProblemsViewTestUtil.showAllErrors(bot)
    ProblemsViewTestUtil.expectedErrorsWarnings(bot, expectedErrors, 0)

    // ACT
    // Select all errors in the Problems view
    ProblemsViewTestUtil.getMarkersTree(bot).select(ProblemsViewTestUtil.getTasks(bot))
    assertEquals("All errors are selected", expectedErrors, ProblemsViewTestUtil.getMarkersTree(bot).selectionCount)

    // Bulk-apply the quickfix, ensuring that all errors remain selected
    ContextActionUiTestUtil.clickContextMenu(ProblemsViewTestUtil.getMarkersTree(bot), QUICK_FIX_CONTEXT_MENU_ITEM)
    bot.waitUntil(Conditions.shellIsActive(QUICK_FIX_DIALOG_TITLE));
    bot.waitUntil(Conditions.waitForWidget(WidgetMatcherFactory.allOf(WidgetMatcherFactory.widgetOfType(typeof(Table)),
      WidgetMatcherFactory.withLabel(PROBLEMS_LABEL_TEXT))));
    val tableBot = bot.tableWithLabel(PROBLEMS_LABEL_TEXT)
    bot.waitUntil(Conditions.tableHasRows(tableBot, expectedErrors));
    for (var row = 0; expectedErrors > row; row++) {
      assertTrue('''Error «row» is checked in the dialog''', tableBot.getTableItem(row).checked)
    }
    bot.clickButton(FINISH_BUTTON_TEXT)
    bot.waitWhile(Conditions.shellIsActive(QUICK_FIX_DIALOG_TITLE));

    // Save all modified files and build the catalogs
    bot.editors.forEach[save]
    testProjectManager.fullBuild

    // ASSERT
    // Check that all errors are fixed
    ProblemsViewTestUtil.expectedErrorsWarnings(bot, 0, 0)
  }

}

