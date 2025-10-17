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
package com.avaloq.tools.ddk.xtext.test.ui.contentassist;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.contentassist.CompletionProposalComputer;
import org.eclipse.xtext.ui.editor.contentassist.CompletionProposalComputer.State;
import org.junit.Assert;

import com.avaloq.tools.ddk.xtext.common.ui.contentassist.TemplatesFirstCompletionProposalComparator;
import com.avaloq.tools.ddk.xtext.resource.Messages;
import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextEditorTest;
import com.avaloq.tools.ddk.xtext.ui.util.UiThreadDispatcher;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Abstract class for content assist tests.
 */
@SuppressWarnings("nls")
public abstract class AbstractContentAssistUiTest extends AbstractXtextEditorTest {

  private static final String EDITOR_HAS_NO_VIEWER = "Editor has no viewer.";

  /** The Constant NO_OFFSET_FOUND returned when no offset found for given token. */
  private static final int NO_OFFSET_FOUND = -1;

  private ICompletionProposal[] getCompletionProposals() {
    return (ICompletionProposal[]) getTestInformation().getTestObject(ICompletionProposal.class);
  }

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
   * Evaluates completion proposals in a UI thread.
   *
   * @param offset
   *          the offset at which completion proposals should be evaluated
   */
  private void evaluateCompletionProposals(final int offset) {
    UiThreadDispatcher.dispatchAndWait(new Runnable() {
      @Override
      public void run() {
        getTestInformation().putTestObject(ICompletionProposal.class, getDocument().readOnly(createCompletionProposalComputer(offset)));
      }
    });
  }

  /**
   * Gets the completion proposal display strings.
   *
   * @param proposals
   *          the proposals
   * @return the completion proposal display strings
   */
  private List<String> getCompletionProposalDisplayStrings(final ICompletionProposal... proposals) {
    return Lists.newArrayList(Iterables.transform(Arrays.asList(proposals), new Function<ICompletionProposal, String>() {
      @Override
      public String apply(final ICompletionProposal from) {
        return from.getDisplayString();
      }
    }));
  }

  /**
   * Compares two lists of Strings alphabetically.
   *
   * @param contentassistProposals
   *          expected list of proposals
   * @param offset
   *          offset in test file
   */
  protected void assertContentAssist(final List<String> contentassistProposals, final int offset) {
    evaluateCompletionProposals(offset);
    Arrays.sort(getCompletionProposals(), new TemplatesFirstCompletionProposalComparator());
    Assert.assertEquals("Same length", contentassistProposals.size(), getCompletionProposals().length);
    for (int i = 0; i < contentassistProposals.size(); i++) {
      Assert.assertEquals("Same displayed string", contentassistProposals.get(i), getCompletionProposals()[i].getDisplayString());
    }
  }

  /**
   * Assert that application of the target {@link TemplateProposal} was successful and the text of the resulting document equals the expected text.
   *
   * @param sourceFileName
   *          the name of the source being tested, must not be {@code null}
   * @param contentassistProposal
   *          the display string of the content assist proposal, must not be {@code null}
   * @param expectedContent
   *          the expected document content after application of the {@link TemplateProposal}, must not be {@code null}
   * @param offset
   *          offset in test file
   */
  protected void assertTemplateProposalExistsAndSuccessful(final String sourceFileName, final String contentassistProposal, final String expectedContent, final int offset) {
    assertTemplateProposalExistsAndSuccessful(sourceFileName, null, contentassistProposal, expectedContent, offset);
  }

  /**
   * Assert that application of the target {@link TemplateProposal} was successful and the text of the resulting document equals the expected text.
   * Creates a test source with the given {@code sourceFileName} and {@code sourceContent}.
   *
   * @param sourceFileName
   *          the name of the source being tested, must not be {@code null}
   * @param sourceContent
   *          the source content, may be {@code null}
   * @param contentassistProposal
   *          the display string of the content assist proposal, must not be {@code null}
   * @param expectedContent
   *          the expected document content after application of the {@link TemplateProposal}, must not be {@code null}
   * @param offset
   *          offset in test file
   */
  @SuppressWarnings("PMD.UseObjectForClearerAPI")
  protected void assertTemplateProposalExistsAndSuccessful(final String sourceFileName, final String sourceContent, final String contentassistProposal, final String expectedContent, final int offset) {
    if (sourceContent == null) {
      Assert.assertNotNull(String.format("There must be an existing test source with the file name '%s'.", sourceFileName), getTestSource(sourceFileName));
    } else {
      createTestSource(sourceFileName, sourceContent);
    }
    openEditor(sourceFileName);
    evaluateCompletionProposals(offset);
    ICompletionProposal[] completionProposals = getCompletionProposals();

    TemplateProposal templateProposal = null;
    for (int i = 0; i < completionProposals.length && templateProposal == null; i++) {
      ICompletionProposal proposal = completionProposals[i];
      String displayString = proposal.getDisplayString();
      if (proposal instanceof TemplateProposal && contentassistProposal.equals(displayString)) {
        templateProposal = (TemplateProposal) proposal;
      }
    }

    Assert.assertNotNull(String.format("Template proposal '%s' must be found.", contentassistProposal), templateProposal);
    String actualContent = applyTemplateProposal(templateProposal, offset);

    Assert.assertEquals("Editor content must match expected result.", expectedContent.replaceAll(CR_LF, LF), actualContent.replaceAll(CR_LF, LF));
    closeEditor(getEditor(), false);
  }

  /**
   * Apply template proposal.
   *
   * @param templateProposal
   *          the template proposal, must not be {@code null}
   * @param offset
   *          offset in test file
   * @return the document text after application of the template proposal, never {@code null}
   */
  private String applyTemplateProposal(final TemplateProposal templateProposal, final int offset) {
    // Apply proposal
    UiThreadDispatcher.dispatchAndWait(new Runnable() {
      @Override
      public void run() {
        Assert.assertNotNull(EDITOR_HAS_NO_VIEWER, getViewer());
        templateProposal.apply(getViewer(), ' ', 0, offset);
      }
    });

    waitForValidation();
    return getDocument().get();
  }

  /**
   * Assert proposals at offset exist.
   *
   * @param offset
   *          the offset
   * @param proposals
   *          the proposals
   */
  protected void assertProposalsAtOffsetExist(final int offset, final String... proposals) {
    evaluateCompletionProposals(offset);
    final List<String> result = getCompletionProposalDisplayStrings(getCompletionProposals());
    for (final String s : proposals) {
      Assert.assertTrue(Messages.bind("Expected proposal \"{0}\" but found \"{1}\"", s, result), result.contains(s));
    }
  }

  /**
   * Helper function to find the correct CompletionProposalComputer for the given offset.
   *
   * @param offset
   *          offset in test file
   * @return language and offset specific content assist proposal computer
   */
  private CompletionProposalComputer createCompletionProposalComputer(final int offset) {
    XtextSourceViewerConfiguration configuration = getEditor().getXtextSourceViewerConfiguration();
    IContentAssistant contentAssistant = configuration.getContentAssistant(getViewer());
    IContentAssistProcessor contentAssistProcessor;
    try {
      contentAssistProcessor = contentAssistant.getContentAssistProcessor(getDocument().getContentType(offset));
    } catch (BadLocationException e) {
      contentAssistProcessor = getTestUtil().get(IContentAssistProcessor.class);
    }
    if (contentAssistProcessor == null) {
      contentAssistProcessor = getTestUtil().get(IContentAssistProcessor.class);
    }
    return new CompletionProposalComputer((State) contentAssistProcessor, (ITextViewer) getViewer(), offset);
  }

  /**
   * Gets the total offset for given token.
   *
   * @param parserNode
   *          the parser node
   * @param name
   *          the name
   * @return the total offset for given token
   */
  protected int getTotalOffsetForToken(final ICompositeNode parserNode, final String name) {
    int result = NO_OFFSET_FOUND;
    for (final INode n : parserNode.getChildren()) {
      if (n instanceof ILeafNode && name.equals(n.getText())) {
        result = n.getTotalOffset();
      } else if (n instanceof ICompositeNode) {
        result = getTotalOffsetForToken((ICompositeNode) n, name);
      }
    }
    return result;
  }

}
