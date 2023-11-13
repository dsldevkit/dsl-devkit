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
package com.avaloq.tools.ddk.xtext.test.ui.quickfix;

import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.resource.XtextSyntaxDiagnostic;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.IssueModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.validation.Issue;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;

import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreModificationContext;
import com.avaloq.tools.ddk.check.runtime.ui.quickfix.CoreIssueModificationContext;
import com.avaloq.tools.ddk.check.runtime.ui.quickfix.IssueResolutionWrapper;
import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextEditorTest;
import com.avaloq.tools.ddk.xtext.ui.util.UiThreadDispatcher;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;


/**
 * Test existence and application of QuickFixes for a given language.
 * Subclasses provide a validation test file which contains the model the test is based on.
 * The name of the quick fix test file must be provided in the method getQuickFixFileName().
 * Besides overriding the abstract method getQuickFixFileName(), subclasses have to implement
 * the test method itself which tests for existence and resolutions of the diagnostic issues.
 */
@SuppressWarnings({"PMD.UseObjectForClearerAPI", "nls"})
public abstract class AbstractQuickFixTest extends AbstractXtextEditorTest {

  private IssueResolutionProvider getIssueResolutionProvider() {
    return getXtextTestUtil().get(IssueResolutionProvider.class);
  }

  /**
   * Results of the diagnostic, a list of Issue.
   *
   * @return the list of issues
   */
  private List<Issue> getIssueList() {
    return getXtextTestUtil().getIssues(getDocument());
  }

  /**
   * Set up test by opening a text editor with the validation test file and triggering validation.
   */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    closeEditor(getEditor(), false);
  }

  @Override
  protected void beforeEachTest() {
    super.beforeEachTest();
    if (getTestSource() != null) {
      openEditor(getTestSourceFileName());
    }
  }

  @Override
  protected void afterEachTest() {
    super.afterEachTest();
    closeEditor(getEditor(), false);
  }

  /**
   * Assert that the diagnostic result (issueList) contains an Issue of the given issueCode.
   *
   * @param issueCode
   *          the code of the expected issue, may be {@code null}
   */
  protected void assertHasIssue(final String issueCode) {
    Assert.assertFalse("Issue " + issueCode + " is empty", issuesWith(issueCode).isEmpty());
  }

  /**
   * Assert that diagnostic result (issueList) contains a QuickFix of the given issueCode.
   *
   * @param issueCode
   *          the code of the issue for which a quickfix is expected to exist, may be {@code null}
   */
  protected void assertHasQuickFix(final String issueCode) {
    Assert.assertFalse("No resolutions found for issue " + issueCode, resolutionsFor(issueCode).isEmpty());
  }

  /**
   * Assert that diagnostic result (issueList) contains a QuickFix of the given issueCode.
   *
   * @param issueCode
   *          the code of the issue for which a quickfix is expected to exist, may be {@code null}
   * @param quickfixLabel
   *          the label of the quickfix, may be {@code null}
   */
  protected void assertHasQuickFix(final String issueCode, final String quickfixLabel) {
    Assert.assertFalse("No resolutions found for issue " + issueCode, resolutionsFor(issueCode, quickfixLabel).isEmpty());
  }

  /**
   * Assert that diagnostic result (issueList) contains the exact number of the given quickfix label in the proposal of the given issueCode.
   *
   * @param issueCode
   *          the code of the issue for which a quickfix is expected to exist, may be {@code null}
   * @param quickfixLabel
   *          the label of the quickfix, may be {@code null}
   * @param numberOfQuickfixProposal
   *          the number of expected quickfix proposal, must not be {@code null}
   */
  protected void assertHasQuickFix(final String issueCode, final String quickfixLabel, final int numberOfQuickfixProposal) {
    Assert.assertEquals("Number of resolutions found for issue " + issueCode
        + " does not match the expected number of quickfix proposal", resolutionsFor(issueCode, quickfixLabel).size(), numberOfQuickfixProposal);
  }

  /**
   * Assert that diagnostic result (issueList) of a given source does not contain a QuickFix of the given issueCode.
   *
   * @param sourceFileName
   *          the source file name, must not be {@code null}
   * @param sourceFileContent
   *          the source file content, must not be {@code null}
   * @param issueCode
   *          the issue code for which no QuickFix must exist, must not be {@code null}
   */
  protected void assertNoQuickFix(final String sourceFileName, final String sourceFileContent, final String issueCode) {
    assertNoQuickFix(sourceFileName, sourceFileContent, issueCode, null);
  }

  /**
   * Assert that diagnostic result (issueList) of a given source does not contain a QuickFix of the given issueCode.
   *
   * @param sourceFileName
   *          the source file name, must not be {@code null}
   * @param sourceFileContent
   *          the source file content, must not be {@code null}
   * @param issueCode
   *          the issue code for which no QuickFix must exist, must not be {@code null}
   * @param quickfixLabel
   *          the quickfix label, may be {@code null}
   */
  protected void assertNoQuickFix(final String sourceFileName, final String sourceFileContent, final String issueCode, final String quickfixLabel) {
    createTestSource(sourceFileName, sourceFileContent);
    openEditor(sourceFileName);
    try {
      Assert.assertTrue("No resolutions expected for issue " + issueCode + " on source " + sourceFileName, resolutionsFor(issueCode, quickfixLabel).isEmpty());
    } finally {
      closeEditor(getEditor(), false);
    }
  }

  /**
   * Assert that application of the quick fixes for the given issueCode resolve the problem.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   */
  protected void assertQuickFixSuccessful(final String issueCode) {
    assertQuickFixSuccessful(issueCode, null);
  }

  /**
   * Assert that application of the quick fixes for the given issueCode and label resolve the problem.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   * @param quickfixLabel
   *          the label of the quickfix, may be {@code null}
   */
  protected void assertQuickFixSuccessful(final String issueCode, final String quickfixLabel) {
    for (final IssueResolution issueResolution : sortResolutionsByOffsetDecreasing(resolutionsFor(issueCode, quickfixLabel))) {
      UiThreadDispatcher.dispatchAndWait(new Runnable() {
        @Override
        public void run() {
          issueResolution.apply();
        }
      });
    }
    waitForValidation();
    Assert.assertTrue("Resolutions for issue " + issueCode + " with quickfix " + quickfixLabel
        + "are not empty", resolutionsFor(issueCode, quickfixLabel).isEmpty());
  }

  /**
   * Sort issue resolutions by offset in document decreasing.
   *
   * @param resolutions
   *          resolutions to sort
   * @return a copy of {@code resolutions} sorted by offset in document decreasing
   */
  protected List<IssueResolution> sortResolutionsByOffsetDecreasing(final List<IssueResolution> resolutions) {

    final Function<IssueResolution, Integer> getLocationFunction = new Function<IssueResolution, Integer>() {

      @Override
      public Integer apply(final IssueResolution from) {
        if (from != null) {
          if (from instanceof IssueResolutionWrapper) {
            ICoreModificationContext context = ((IssueResolutionWrapper) from).getCoreModificationContext();
            if (context instanceof CoreIssueModificationContext) {
              return ((CoreIssueModificationContext) context).getIssue().getOffset();
            }
          } else {
            IModificationContext context = from.getModificationContext();
            if (context instanceof IssueModificationContext) {
              return ((IssueModificationContext) context).getIssue().getOffset();
            }
          }
        }
        return Integer.MIN_VALUE;
      }
    };
    Ordering<IssueResolution> ordering = Ordering.natural().onResultOf(getLocationFunction).reverse();
    return new ArrayList<IssueResolution>(ordering.sortedCopy(resolutions));
  }

  /**
   * Assert that the test source has no syntax error.
   */
  protected void assertNoSyntaxError() {
    Assert.assertFalse("The source has syntax errors", Iterables.any(getTestSource().getXtextResource().getErrors(), Predicates.instanceOf(XtextSyntaxDiagnostic.class)));
  }

  /**
   * Assert that application of the target quickfix was successful and the text of the resulting document equals the expected text.
   * The method ensures that there is one and only one quickfix for the given issue code with the given label.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   * @param quickfixLabel
   *          the label of the quick fix that should be applied, may be {@code null}
   * @param sourceFileName
   *          the name of the source being tested
   * @param sourceContent
   *          the content of the source being tested
   * @param expectedContent
   *          the name of the file containing the expected result after applying the quick fix
   */
  protected void assertQuickFixExistsAndSuccessfulInKernelSource(final String issueCode, final String quickfixLabel, final String sourceFileName, final String sourceContent, final String expectedContent) {
    assertQuickFixExistsAndSuccessful(issueCode, quickfixLabel, sourceFileName, sourceContent, expectedContent, false);
  }

  /**
   * Assert that application of the target quickfix was successful and the text of the resulting document equals the expected text (ignoring formatting).
   * The method ensures that there is one and only one quickfix for the given issue code with the given label.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   * @param quickfixLabel
   *          the label of the quick fix that should be applied, may be {@code null}
   * @param sourceFileName
   *          the name of the source being tested
   * @param sourceContent
   *          the content of the source being tested
   * @param expectedContent
   *          the name of the file containing the expected result after applying the quick fix
   */
  protected void assertQuickFixExistsAndSuccessfulInKernelSourceIgnoreFormatting(final String issueCode, final String quickfixLabel, final String sourceFileName, final String sourceContent, final String expectedContent) {
    assertQuickFixExistsAndSuccessful(issueCode, quickfixLabel, sourceFileName, sourceContent, expectedContent, true);
  }

  /**
   * Assert that application of the target quickfix was successful and the text of the resulting document equals the expected text.
   * The method ensures that there is one and only one quickfix for the given issue code with the given label.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   * @param quickfixLabel
   *          the label of the quick fix that should be applied, may be {@code null}
   * @param sourceFileName
   *          the name of the source being tested
   * @param sourceContent
   *          the content of the source being tested
   * @param expectedContent
   *          the name of the file containing the expected result after applying the quick fix
   */
  protected void assertQuickFixExistsAndSuccessfulInCustomerSource(final String issueCode, final String quickfixLabel, final String sourceFileName, final String sourceContent, final String expectedContent) {
    assertQuickFixExistsAndSuccessful(issueCode, quickfixLabel, CUSTOMER_SOURCE_PREFIX.concat(sourceFileName), sourceContent, expectedContent, false);
  }

  /**
   * Assert that application of the target quickfix was successful and the text of the resulting document equals the expected text (ignoring formatting).
   * The method ensures that there is one and only one quickfix for the given issue code with the given label.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   * @param quickfixLabel
   *          the label of the quick fix that should be applied, may be {@code null}
   * @param sourceFileName
   *          the name of the source being tested
   * @param sourceContent
   *          the content of the source being tested
   * @param expectedContent
   *          the name of the file containing the expected result after applying the quick fix
   */
  protected void assertQuickFixExistsAndSuccessfulInCustomerSourceIgnoreFormatting(final String issueCode, final String quickfixLabel, final String sourceFileName, final String sourceContent, final String expectedContent) {
    assertQuickFixExistsAndSuccessful(issueCode, quickfixLabel, CUSTOMER_SOURCE_PREFIX.concat(sourceFileName), sourceContent, expectedContent, true);
  }

  /**
   * Assert that application of the target quickfix was successful and the text of the resulting document equals the expected text.
   * The method ensures that there is one and only one quickfix for the given issue code with the given label.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   * @param quickfixLabel
   *          the label of the quick fix that should be applied, may be {@code null}
   * @param sourceFileName
   *          the name of the source being tested
   * @param sourceContent
   *          the content of the source being tested
   * @param expectedContent
   *          the name of the file containing the expected result after applying the quick fix
   * @param ignoreFormatting
   *          ignore formatting
   */
  private void assertQuickFixExistsAndSuccessful(final String issueCode, final String quickfixLabel, final String sourceFileName, final String sourceContent, final String expectedContent, final boolean ignoreFormatting) {
    createTestSource(sourceFileName, sourceContent);
    openEditor(sourceFileName);
    assertQuickFixExistsAndSuccessful(issueCode, quickfixLabel, expectedContent, ignoreFormatting);
    closeEditor(getEditor(), false);
  }

  /**
   * Assert that application of the target quickfix was successful and the text of the resulting document equals the expected text.
   * The method ensures that there is one and only one quickfix for the given issue code with the given label.
   *
   * @param issueCode
   *          the code of the issue that should have been fixed, may be {@code null}
   * @param quickfixLabel
   *          the label of the quick fix that should be applied, may be {@code null}
   * @param expectedContent
   *          the name of the file containing the expected result after applying the quick fix
   * @param ignoreFormatting
   *          ignore formatting
   */
  private void assertQuickFixExistsAndSuccessful(final String issueCode, final String quickfixLabel, final String expectedContent, final boolean ignoreFormatting) {
    // Assert amount of quickfixes
    int resolutionCount = resolutionsFor(issueCode, quickfixLabel).size();
    Assert.assertEquals(String.format("There must be exactly one quickfix with label '%s' for issue '%s', but found '%d'.", quickfixLabel, issueCode, resolutionCount), resolutionCount, 1);
    // Apply quickfix
    UiThreadDispatcher.dispatchAndWait(new Runnable() {
      @Override
      public void run() {
        List<IssueResolution> resolutions = resolutionsFor(issueCode, quickfixLabel);
        if (!resolutions.isEmpty()) {
          resolutions.get(0).apply();
        }
      }
    });
    waitForValidation();
    Assert.assertTrue("Resolutions for issue " + issueCode + " with quickfix " + quickfixLabel
        + "are not empty", resolutionsFor(issueCode, quickfixLabel).isEmpty());
    String actualContent = getDocument().get();
    assertQuickFixProducesExpectedOutput(expectedContent, actualContent, ignoreFormatting);
  }

  /**
   * Assert that quick fix produces expected output.
   *
   * @param expectedContent
   *          the expected content
   * @param actualContent
   *          the actual content
   * @param ignoreFormatting
   *          the ignore formatting
   */
  private void assertQuickFixProducesExpectedOutput(final String expectedContent, final String actualContent, final boolean ignoreFormatting) {
    String message = "Quickfix didn't produce the expected output.";
    String expected = expectedContent.replaceAll(CR_LF, LF);
    String actual = actualContent.replaceAll(CR_LF, LF);
    if (ignoreFormatting) {
      MatcherAssert.assertThat(message, actual, equalToIgnoringWhiteSpace(expected));
    } else {
      assertEquals(message, expected, actual);
    }
  }

  /**
   * Finds all issues with a specific issue code.
   *
   * @param issueCode
   *          to filter for, may be {@code null}
   * @return {@link List} of issues with a specific code
   */
  private List<Issue> issuesWith(final String issueCode) {
    List<Issue> issues = new ArrayList<Issue>();
    if (issueCode == null) {
      return issues;
    }
    for (Issue issue : getIssueList()) {
      if (issueCode.equals(issue.getCode())) {
        issues.add(issue);
      }
    }
    return issues;
  }

  /**
   * Finds all resolutions for issues with a specific issue code.
   *
   * @param issueCode
   *          to find resolutions for, may be {@code null}
   * @return {@link List} of resolutions for issues with a specific code
   */
  private List<IssueResolution> resolutionsFor(final String issueCode) {
    return resolutionsFor(issueCode, null);
  }

  /**
   * Finds all resolutions for issues with a specific issue code.
   *
   * @param issueCode
   *          to find resolutions for, may be {@code null}
   * @param quickfixLabel
   *          to find resolutions for, may be {@code null}
   * @return {@link List} of resolutions for issues with a specific code
   */
  private List<IssueResolution> resolutionsFor(final String issueCode, final String quickfixLabel) {
    final List<IssueResolution> resolutions = new ArrayList<IssueResolution>();

    for (final Issue issue : issuesWith(issueCode)) {
      UiThreadDispatcher.dispatchAndWait(new Runnable() {
        @Override
        public void run() {
          if (quickfixLabel == null) {
            resolutions.addAll(getIssueResolutionProvider().getResolutions(issue));
          } else {
            for (IssueResolution r : getIssueResolutionProvider().getResolutions(issue)) {
              if (quickfixLabel.equals(r.getLabel())) {
                resolutions.add(r);
              }
            }
          }
        }
      });
    }

    return resolutions;
  }
}
