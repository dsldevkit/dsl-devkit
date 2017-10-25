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
package com.avaloq.tools.ddk.check.ui.test.quickfix

import com.avaloq.tools.ddk.check.ui.quickfix.Messages
import com.avaloq.tools.ddk.check.validation.IssueCodes
import com.avaloq.tools.ddk.test.core.BugTest
import com.avaloq.tools.ddk.test.ui.junit.runners.SwtBotRecordingTestRunner
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot
import com.avaloq.tools.ddk.test.ui.swtbot.condition.WaitForEquals
import com.avaloq.tools.ddk.test.ui.swtbot.util.ProblemsViewTestUtil
import org.apache.log4j.Logger
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException
import org.eclipse.xtext.diagnostics.Diagnostic
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test quickfixes for Check files.
 */
@RunWith(SwtBotRecordingTestRunner)
class CheckQuickfixTest extends AbstractCheckQuickfixTest {

  static val LOGGER = Logger.getLogger(CheckQuickfixTest);

  static val PACKAGE_NAME = "com.avaloq.test"

  val SwtWorkbenchBot bot = new SwtWorkbenchBot

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
    cleanUp
  }

  override protected afterEachTest() {
    cleanUp
    super.afterEachTest
  }

  /**
   * Close all shells and editors and remove all sources.
   */
  def private void cleanUp() {
    bot.closeAllShells
    bot.closeAllEditors
    for (testSource : testProjectManager.testSources) {
      testProjectManager.removeTestSource(testSource)
    }
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
    val beforeIssues = getXtextTestUtil().getIssues(getDocument());
    assertHasQuickFix(Diagnostic::LINKING_DIAGNOSTIC, quickfixLabel);
    assertQuickFixSuccessful(Diagnostic::LINKING_DIAGNOSTIC, quickfixLabel);
    val afterIssues = getXtextTestUtil().getIssues(getDocument());
    Assert.assertTrue(afterIssues.size < beforeIssues.size);
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
   * Tolerate up to ten timeouts, because occasionally new markers don't appear or not all markers are fixed;
   * the problems appears to be in Eclipse.
   */
  @Test
  def void testBulkApplyingQuickfix() {

    // ARRANGE
    val catalogNames = #[testSourceModelName, testSourceModelName + "2"]
    val checkLabels = #["Check with no explicit ID", "Another check with no explicit ID"]
    val expectedMarkers = catalogNames.length * checkLabels.length

    // Show all error markers
    ProblemsViewTestUtil.showProblemsView(bot)
    ProblemsViewTestUtil.showAllErrors(bot)
    ProblemsViewTestUtil.groupByNone(bot)

    for (var remainingAttempts = 9; 0 <= remainingAttempts; remainingAttempts--) {

      // Ensure autobuilding is enabled
      val oldAutoBuildState = testProjectManager.setAutobuild(true)
      try {

        // Add catalogs containing multiple instances of the same quickfixable marker
        for (catalogName : catalogNames) {
          createTestSource(getTestSourceFileName(catalogName), '''
            package «PACKAGE_NAME»

            catalog «catalogName»
            for grammar org.eclipse.xtext.Xtext {
              «FOR checkLabel : checkLabels»

              live error "«checkLabel»"
              message "«checkLabel»" {
              }
              «ENDFOR»
            }
          ''')
        }

        // Build the catalogs, and wait for the expected markers to appear
        testProjectManager.build
        val markersTreeBot = ProblemsViewTestUtil.getMarkersTree(bot)
        bot.waitUntil(new WaitForEquals("Not all expected markers appeared.", [expectedMarkers], [markersTreeBot.allItems.length]))

        // ACT
        // Disable autobuilding, to avoid losing focus while selecting markers
        testProjectManager.autobuild = false
        testProjectManager.build

        // Bulk-apply quickfixes on all markers, ensuring that all markers remain selected
        ProblemsViewTestUtil.bulkApplyQuickfix(bot, Messages.CheckQuickfixProvider_ADD_ID_LABEL, markersTreeBot.allItems)
        bot.waitUntil(new WaitForEquals("Not all markers are still selected.", [expectedMarkers], [markersTreeBot.selectionCount]))

        // Save all modified files and build the catalogs
        bot.editors.forEach[save]
        testProjectManager.autobuild = true
        testProjectManager.build

        // ASSERT
        // Check that all markers are fixed
        bot.waitUntil(new WaitForEquals("Some markers were not quickfixed.", [0], [markersTreeBot.allItems.length]))

        // Pass
        return
      } catch (TimeoutException exception) {
        if (0 == remainingAttempts) {
          // Fail
          throw exception
        } else {
          // Log and retry
          LOGGER.warn(exception.message)
        }

      } finally {
        testProjectManager.autobuild = oldAutoBuildState
        cleanUp
      }
    }
  }

}

