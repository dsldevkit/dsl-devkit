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
package com.avaloq.tools.ddk.xtext.test.contentassist;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextMarkerBasedTest;
import com.avaloq.tools.ddk.xtext.test.AcfContentAssistProcessorTestBuilder;
import com.avaloq.tools.ddk.xtext.test.TagCompilationParticipant;
import com.google.common.collect.Maps;
import com.google.inject.Injector;


/**
 * The Class AcfContentAssistTest provides utility operations for non-UI content assist
 * tests. It allows testing content assist on non-complete models step by step, as a user
 * would also use it when defining a new model in the corresponding editor.
 */
// CHECKSTYLE:OFF
public abstract class AbstractAcfContentAssistTest extends AbstractXtextMarkerBasedTest {
  private final AcfContentAssistMarkerTagsInfo acfContentAssistMarkerTagInfo = new AcfContentAssistMarkerTagsInfo();
  private static final String AT_LEAST_ONE_PROPOSAL_WAS_PROVIDED = "At least one proposal was provided";
  private static final String EXPECTED_PROPOSALS_NOT_SET = "No expected proposals provided to check against actual proposals.";
  // CHECKSTYLE:ON
  private static final String SEPARATOR = ", ";

  /**
   * Creates a new completion proposal builder.
   *
   * @return the content assist processor test builder
   */
  protected AcfContentAssistProcessorTestBuilder newBuilder() {
    try {
      return new AcfContentAssistProcessorTestBuilder(getXtextTestUtil().get(Injector.class), getTestUtil());
      // CHECKSTYLE:OFF
    } catch (Exception e) {
      // CHECKSTYLE:ON
      throw new WrappedException("Could not create ContentAssistProcessorTestBuilder.", e);
    }
  }

  /**
   * Gets the completion proposal display strings.
   *
   * @param computedProposals
   *          the computed proposals
   * @return the completion proposal display strings
   */
  private String getCompletionProposalDisplayStrings(final ICompletionProposal... computedProposals) {
    String result = "";
    for (ICompletionProposal p : computedProposals) {
      result += p.getDisplayString() + SEPARATOR; // NOPMD
    }
    if (result != null && result.length() > SEPARATOR.length()) {
      return result.substring(0, result.length() - SEPARATOR.length());
    }
    return null;
  }

  /**
   * Iterate over given computed completion proposals and compare resulting display string with each expected proposal text. Fail if one or more expected
   * proposals not found.
   *
   * @param computedProposals
   *          the computed proposals
   * @param positiveTest
   *          indicates the type of test.
   *          TRUE: the test is positive and must fail if proposals not found.
   *          FALSE: the test is negative and must fail if proposals found.
   * @param proposals
   *          the expected proposals as display strings
   */
  private void assertCompletionProposal(final ICompletionProposal[] computedProposals, final boolean positiveTest, final String... proposals) {
    assertNotEquals(AT_LEAST_ONE_PROPOSAL_WAS_PROVIDED, proposals.length, 0);
    for (final String s : proposals) {
      boolean foundProposal = false;
      for (ICompletionProposal p : computedProposals) {
        if (s.equals(p.getDisplayString())) {
          foundProposal = true;
          break;
        }
      }
      if (positiveTest && !foundProposal) {
        fail(MessageFormat.format("Expected to find proposal \"{0}\" but could only find \"{1}\"", s, getCompletionProposalDisplayStrings(computedProposals)));
      } else if (!positiveTest && foundProposal) {
        fail(MessageFormat.format("Not expected to find proposal \"{0}\" but found \"{1}\"", s, getCompletionProposalDisplayStrings(computedProposals)));
      }
    }
  }

  /**
   * Iterate over given computed completion proposals and compare resulting display string with each expected proposal text. Fail if one or more expected
   * proposals not found.
   *
   * @param computedProposals
   *          the computed proposals
   * @param expectedProposals
   *          the expected proposals as display strings
   */
  protected void assertCompletionProposal(final ICompletionProposal[] computedProposals, final String... expectedProposals) {
    assertCompletionProposal(computedProposals, true, expectedProposals);
  }

  /**
   * Iterate over given computed completion proposals and compare resulting display string with each expected proposal text. Fail if one or more expected
   * proposals not found.
   *
   * @param cursorPosition
   *          the cursor position within the main test source
   * @param expectedProposals
   *          the expected proposals as display strings
   */
  protected void assertCompletionProposal(final int cursorPosition, final String... expectedProposals) {
    assertCompletionProposal(newBuilder().computeCompletionProposals(getTestSource(), cursorPosition), expectedProposals);
  }

  /**
   * Iterate over given computed completion proposals and compare resulting display string with each expected proposal text. Fail if one or more not expected
   * proposals found.
   *
   * @param computedProposals
   *          the computed proposals
   * @param expectedProposals
   *          the not expected proposals as display strings
   */
  protected void assertNotCompletionProposal(final ICompletionProposal[] computedProposals, final String... expectedProposals) {
    assertCompletionProposal(computedProposals, false, expectedProposals);
  }

  /**
   * Ensures that the list of expected proposals corresponds exactly the list of computed proposals.
   *
   * @param computedProposals
   *          the computed proposals
   * @param expectedProposals
   *          the expected proposals as display strings
   */
  protected void assertExactlyCompletionProposal(final ICompletionProposal[] computedProposals, final String... expectedProposals) {
    assertNotEquals(AT_LEAST_ONE_PROPOSAL_WAS_PROVIDED, expectedProposals.length, 0);

    Set<String> computedProposalsAsSet = new HashSet<String>();
    for (ICompletionProposal p : computedProposals) {
      computedProposalsAsSet.add(p.getDisplayString());
    }

    Set<String> expectedProposalsAsSet = new HashSet<String>();
    for (final String s : expectedProposals) {
      expectedProposalsAsSet.add(s);
    }

    if (computedProposalsAsSet.size() != expectedProposalsAsSet.size()) {
      // Calculate missing templates
      Set<String> missing = new HashSet<String>(expectedProposalsAsSet);
      missing.removeAll(computedProposalsAsSet);
      if (!missing.isEmpty()) {
        fail(MessageFormat.format("Proposals not found: \"{0}\".", missing.toString()));
      }
      // Calculate false positives
      Set<String> notExpected = new HashSet<String>(computedProposalsAsSet);
      notExpected.removeAll(expectedProposalsAsSet);
      if (!notExpected.isEmpty()) {
        fail(MessageFormat.format("Not expected: \"{0}\".", notExpected.toString()));
      }
    }
  }

  /**
   * Asserts the expected valid and invalid proposals based on the given registered source filename.
   *
   * @param sourceFileName
   *          the filename of the test source that the proposals were to be computed from, must not be {@code null}
   */
  @SuppressWarnings("restriction")
  private void assertSourceProposals(final String sourceFileName) {
    try {
      AcfContentAssistProcessorTestBuilder builder = newBuilder().append(getTestSource(sourceFileName).getContent());
      assertFalse(EXPECTED_PROPOSALS_NOT_SET, (acfContentAssistMarkerTagInfo.expectedProposalMap.isEmpty()
          && acfContentAssistMarkerTagInfo.notExpectedProposalMap.isEmpty() && acfContentAssistMarkerTagInfo.expectedExactlyProposalMap.isEmpty()));
      for (int markerId : getUsedTagsItems()) {
        final ICompletionProposal[] proposals = builder.computeCompletionProposals(getOffsetForTag(markerId));
        if (acfContentAssistMarkerTagInfo.expectedProposalMap.containsKey(markerId)) {
          assertCompletionProposal(proposals, acfContentAssistMarkerTagInfo.expectedProposalMap.get(markerId));
        }
        if (acfContentAssistMarkerTagInfo.notExpectedProposalMap.containsKey(markerId)) {
          assertNotCompletionProposal(proposals, acfContentAssistMarkerTagInfo.notExpectedProposalMap.get(markerId));
        }
        if (acfContentAssistMarkerTagInfo.expectedExactlyProposalMap.containsKey(markerId)) {
          assertExactlyCompletionProposal(proposals, acfContentAssistMarkerTagInfo.expectedExactlyProposalMap.get(markerId));
        }
      }
      // CHECKSTYLE:OFF
    } catch (Exception e) {
      // CHECKSTYLE:ON
      throw new WrappedException("Could not assert the expected valid and invalid proposals.", e);
    }
  }

  /**
   * Asserts the proposals for the given kernel test filename and content.
   *
   * @param sourceFileName
   *          the filename of the test source that the proposals were to be computed from, must not be {@code null}
   * @param sourceContent
   *          the content of the test source, must not be {@code null}
   */
  protected void assertKernelSourceProposals(final String sourceFileName, final CharSequence sourceContent) {
    addKernelSourceToWorkspace(sourceFileName, sourceContent);
    assertSourceProposals(sourceFileName);
  }

  /**
   * Asserts the proposals for the given customer test filename and content.
   *
   * @param sourceFileName
   *          the filename of the test source that the proposals were to be computed from, must not be {@code null}
   * @param sourceContent
   *          the content of the test source, must not be {@code null}
   */
  protected void assertCustomerSourceProposals(final String sourceFileName, final CharSequence sourceContent) {
    addCustomerSourceToWorkspace(sourceFileName, sourceContent);
    assertSourceProposals(sourceFileName);
  }

  /**
   * Registers the marker with the given proposals. Each of the expected proposal must have a match with the computed proposals on the marked position.
   *
   * @param proposals
   *          the expected proposals, must not be {@code null}
   * @return mark text to be inserted in the source file, never {@code null}
   */
  protected String expected(final String... proposals) {
    Integer markerId = getTag();
    acfContentAssistMarkerTagInfo.expectedProposalMap.put(markerId, proposals);
    return mark(markerId);
  }

  /**
   * Registers the marker with the given proposals. The expected proposals must EXACTLY match the computed proposals on the marked position.
   *
   * @param proposals
   *          the exact expected proposals, must not be {@code null}
   * @return mark text to be inserted in the source file, never {@code null}
   */
  protected String expectedExactly(final String... proposals) {
    Integer markerId = getTag();
    acfContentAssistMarkerTagInfo.expectedExactlyProposalMap.put(markerId, proposals);
    return mark(markerId);
  }

  /**
   * Registers the marker with the given proposals. Each of the expected proposal must NOT match with any of the computed proposals on the marked position.
   *
   * @param proposals
   *          the expected invalid proposals, must not be {@code null}
   * @return mark text to be inserted in the source file, never {@code null}
   */
  protected String notExpected(final String... proposals) {
    Integer markerId = getTag();
    acfContentAssistMarkerTagInfo.notExpectedProposalMap.put(markerId, proposals);
    return mark(markerId);
  }

  /**
   * Stores the valid proposals in a corresponding map using the given marker id.
   * This method will return the marker id that will be used to {@link #mark()} the position in
   * which the valid proposals are expected.
   * Use {@link #getTag()} for the marker id when accessing locally. For global declarations, use @Tag annotation for marker id.
   *
   * @param markerId
   *          the unique marker id, must not be {@code null}}
   * @param proposals
   *          the expected valid proposals, must not be {@code null}
   * @return the marker id, never {@code null}
   */
  protected int expected(final int markerId, final String... proposals) {
    acfContentAssistMarkerTagInfo.expectedProposalMap.put(markerId, proposals);
    return markerId;
  }

  /**
   * Stores the invalid proposals in a corresponding map using the given marker id.
   * This method will return the marker id that will be used to {@link #mark()} the position in which the proposals are not expected.
   * Use {@link #getTag()} for the marker id when accessing locally. For global declarations, use @Tag annotation for marker id.
   *
   * @param markerId
   *          the unique marker id, must not be {@code null}}
   * @param proposals
   *          the expected invalid proposals, must not be {@code null}
   * @return the marker id, never {@code null}
   */
  protected int notExpected(final int markerId, final String... proposals) {
    acfContentAssistMarkerTagInfo.notExpectedProposalMap.put(markerId, proposals);
    return markerId;
  }

  /**
   * Stores the exact valid proposals in a corresponding map using the given marker id.
   * This method will return the marker id that will be used to {@link #mark()} the position in which the exact proposals are expected.
   * Use {@link #getTag()} for the marker id when accessing locally. For global declarations, use @Tag annotation for marker id.
   *
   * @param markerId
   *          the unique marker id, must not be {@code null}}
   * @param proposals
   *          the expected exact proposals, must not be {@code null}
   * @return the marker id, never {@code null}
   */
  protected int expectedExactly(final int markerId, final String... proposals) {
    acfContentAssistMarkerTagInfo.expectedExactlyProposalMap.put(markerId, proposals);
    return markerId;
  }

  /**
   * This class preserves information about tags in the sources for all tests within
   * a content assist test class.
   */
  private final class AcfContentAssistMarkerTagsInfo extends MarkerTagsInfo {
    private final Map<Integer, String[]> expectedProposalMap = Maps.newHashMap();
    private final Map<Integer, String[]> notExpectedProposalMap = Maps.newHashMap();
    private final Map<Integer, String[]> expectedExactlyProposalMap = Maps.newHashMap();

    @Override
    public void clearTags(final long maxId) {
      super.clearTags(maxId);
      expectedProposalMap.entrySet().removeIf(entry -> entry.getKey() < TagCompilationParticipant.COUNTER_BASE);
      notExpectedProposalMap.entrySet().removeIf(entry -> entry.getKey() < TagCompilationParticipant.COUNTER_BASE);
      expectedExactlyProposalMap.entrySet().removeIf(entry -> entry.getKey() < TagCompilationParticipant.COUNTER_BASE);
    }
  }

  @Override
  protected AcfContentAssistMarkerTagsInfo getMarkerTagsInfo() {
    AcfContentAssistMarkerTagsInfo info = (AcfContentAssistMarkerTagsInfo) getTestInformation().getTestObject(AcfContentAssistMarkerTagsInfo.class);
    if (info == null) {
      info = acfContentAssistMarkerTagInfo;
      getTestInformation().putTestObject(AcfContentAssistMarkerTagsInfo.class, acfContentAssistMarkerTagInfo);
    }
    return info;
  }

}
