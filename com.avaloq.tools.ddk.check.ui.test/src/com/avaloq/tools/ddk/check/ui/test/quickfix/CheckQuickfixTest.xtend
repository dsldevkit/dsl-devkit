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

import com.avaloq.tools.ddk.test.core.BugTest
import com.avaloq.tools.ddk.check.ui.quickfix.Messages
import com.avaloq.tools.ddk.check.validation.IssueCodes
import org.eclipse.xtext.diagnostics.Diagnostic
import org.junit.Assert
import org.junit.Test

class CheckQuickfixTest extends AbstractCheckQuickfixTest {
  override protected registerRequiredSources() {
    addKernelSourceToWorkspace(testSourceFileName, '''
      package com.avaloq.test

      catalog TestCatalog for grammar org.eclipse.xtext.Xtext
      {
        /** Missing import test */
        warning TestWarning "Test Warning" ()
        message "This is a Test Warning" {
          for AbstractRule c {
            issue
          }
        }
      }
    ''')
  }

  override protected getTestSourceFileName() {
    return "com/avaloq/test/TestCatalog.check";
  }

  @Test
  @BugTest(value="DSL-244")
  def testImportFix() {
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
      package com.avaloq.test

      catalog TestCatalog
      for grammar org.eclipse.xtext.Xtext {

        warning "Test Warning"
        message "This is a Test Warning" {
        }
      }
    '''
    val expectedContent = '''
      package com.avaloq.test

      catalog TestCatalog
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
}

