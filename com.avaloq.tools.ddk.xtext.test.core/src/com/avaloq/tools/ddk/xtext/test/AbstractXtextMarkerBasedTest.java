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
package com.avaloq.tools.ddk.xtext.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.Procedures;
import org.junit.Assert;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;


/**
 * Abstract class that supports Xtend based test implementations to use markers in the test sources.
 */
public abstract class AbstractXtextMarkerBasedTest extends AbstractXtextTest {

  private static final String INVALID_TEST_CONFIGURATION = "Invalid test configuration. Missing org.eclipse.xtend.lib in MANIFEST.MF in this plugin?"; //$NON-NLS-1$
  private static final String LINE_BREAK = "\n";
  private static final String MARKER_START_GUARD = "##";
  private static final String MARKER_END_GUARD = "#";
  private static final String SPLITTING_LINE = "-------------------------------------------------\n";
  protected static final Pattern PATTERN = Pattern.compile(MARKER_START_GUARD + "(\\d+)" + MARKER_END_GUARD);

  /** The tag id. */
  private int localMarkerIdCounter;

  private final Map<Integer, AbstractModelAssertion> assertions = Maps.newHashMap();
  private final SortedMap<Integer, StringBuilder> errorsOnPosition = Maps.newTreeMap();
  /** Used Tags to find Duplicates. */
  private final Set<Integer> usedTags = Sets.newHashSet();

  /**
   * Indicates if a testing source is a kernel or customer source.
   */
  protected enum TestSourceType {
    CLIENT_ALL,
    CLIENT_CUSTOMER
  }

  // --------------------------------------------------------------------------
  // AbstractModelAssertion
  // --------------------------------------------------------------------------

  /**
   * Interface for testing assertions on a given source position.
   */
  protected abstract class AbstractModelAssertion implements Procedures.Procedure2<EObject, Integer> {

    /** {@inheritDoc} */
    @Override
    public abstract void apply(EObject semanticModel, Integer pos);

  }

  // --------------------------------------------------------------------------
  // Methods of testing framework
  // --------------------------------------------------------------------------

  @Override
  protected void afterEachTest() {
    getMarkerTagsInfo().clearTags(localMarkerIdCounter);
    super.afterEachTest();
    assertions.clear();
    usedTags.clear();
    errorsOnPosition.clear();
  }

  @Override
  protected void beforeEachTest() {
    localMarkerIdCounter = 0;
    super.beforeEachTest();
    Assert.assertFalse(INVALID_TEST_CONFIGURATION, getMarkerTagsInfo().isInvalidTestClass());
  }

  // --------------------------------------------------------------------------
  // Methods to be used by the actual testing classes
  // --------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addKernelSourceToWorkspace(final String sourceFileName, final CharSequence sourceContent) {
    String processedContent = processContentAndRegisterOffsets(sourceFileName, sourceContent);
    super.addKernelSourceToWorkspace(sourceFileName, processedContent);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addCustomerSourceToWorkspace(final String sourceFileName, final CharSequence sourceContent) {
    String processedContent = processContentAndRegisterOffsets(CUSTOMER_SOURCE_PREFIX + sourceFileName, sourceContent);
    super.addCustomerSourceToWorkspace(sourceFileName, processedContent);
  }

  /**
   * Removes tags and stores them in the test info object.
   *
   * @param sourceFileName
   *          Source file name, must not be {@code null}
   * @param sourceContent
   *          Content of the test source (may contain tags), must not be {@code null}
   * @return Content without tags, never {@code null}
   */
  private String processContentAndRegisterOffsets(final String sourceFileName, final CharSequence sourceContent) {
    Map<Integer, Integer> offsets = Maps.newHashMap();
    String content = removeMarkersFromContent(sourceContent, offsets);
    for (Entry<Integer, Integer> tag : offsets.entrySet()) {
      getMarkerTagsInfo().registerRequiredSourceTag(tag.getKey(), sourceFileName, tag.getValue());
    }
    return content;
  }

  /**
   * Removes the Xtend markers from a source.
   *
   * @param sourceContent
   *          the source content, not {@code null}
   * @param tagToOffset
   *          Map to be populated with tag to offset pairs, must not be {@code null}
   * @return the content without markers, never {@code null}
   */
  private String removeMarkersFromContent(final CharSequence sourceContent, final Map<Integer, Integer> tagToOffset) {
    StringBuffer withoutMarkers = new StringBuffer(sourceContent.length());
    Matcher m = PATTERN.matcher(sourceContent);
    while (m.find()) {
      m.appendReplacement(withoutMarkers, "");
      tagToOffset.put(Integer.parseInt(m.group(1)), withoutMarkers.length());
    }
    m.appendTail(withoutMarkers);
    return withoutMarkers.toString();
  }

  /**
   * Returns the model for the given source name and string.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, must not be {@code null}
   * @param sourceContent
   *          source, must not be {@code null}
   * @return Model element for the parsed source, may be {@code null}
   */
  protected EObject getModel(final String sourceFileName, final CharSequence sourceContent) {
    Map<Integer, Integer> offsets = Maps.newHashMap();
    String content = removeMarkersFromContent(sourceContent, offsets);
    EObject root = null;
    try {
      root = getXtextTestUtil().getModel(sourceFileName, content);
      INode node = NodeModelUtils.getNode(root);
      for (Entry<Integer, Integer> tag : offsets.entrySet()) {
        INode leafNode = NodeModelUtils.findLeafNodeAtOffset(node, tag.getValue() + 1);
        EObject context = NodeModelUtils.findActualSemanticObjectFor(leafNode);
        // Search for cross reference
        CrossReference crossReference = null;
        while (leafNode != null) {
          if (leafNode.getGrammarElement() instanceof CrossReference) {
            crossReference = (CrossReference) leafNode.getGrammarElement();
            break;
          }
          leafNode = leafNode.getParent();
        }
        getMarkerTagsInfo().registerLocalTag(tag.getKey(), context, crossReference);
      }
    } catch (IOException e) {
      fail("Exception while creating model from input string: " + e.getMessage()); //$NON-NLS-1$
    }
    return root;
  }

  /**
   * Does the same as get model, but returns void.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content
   * @param sourceContent
   *          source
   */
  protected void registerModel(final String sourceFileName, final CharSequence sourceContent) {
    getModel(sourceFileName, sourceContent);
  }

  /**
   * Validate a kernel source given by a file name and content.
   * All not expected diagnostics are ignored.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, not {@code null}
   * @param sourceContent
   *          source, not {@code null}
   */
  protected void validateKernelSource(final String sourceFileName, final CharSequence sourceContent) {
    validate(sourceFileName, TestSourceType.CLIENT_ALL, sourceContent);
  }

  /**
   * Validate a customer source given by a file name and content.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, not {@code null}
   * @param sourceContent
   *          source, not {@code null}
   */
  protected void validateCustomerSource(final String sourceFileName, final CharSequence sourceContent) {
    validate(sourceFileName, TestSourceType.CLIENT_CUSTOMER, sourceContent);
  }

  // --------------------------------------------------------------------------
  // Methods to be used by more specific abstract classes
  // --------------------------------------------------------------------------

  /**
   * Add assertion to the list of assertions and return the corresponding string marker.
   *
   * @param assertion
   *          assertion to be added, not {@code null}
   * @return
   *         string marker corresponding to the added assertion
   */
  protected String addAssertion(final AbstractModelAssertion assertion) {
    // Assertions are given a local tag as they are local markers affecting and declared in only one source
    Integer markerId = getTag();
    assertions.put(markerId, assertion);
    return mark(markerId);
  }

  /**
   * Creates a mark with the given id. Use this method in tests to insert marks.
   * <p>
   * If the given mark id is 0 then it means that the test was not well configured. All the id-s that are generated by {@link #getTag} start with 1, all the
   * global ids start with {@value com.avaloq.tools.asmd.testbase.scoping.TagCompilationParticipant#COUNTER_BASE}. Since it is unlikely to get local ids wrong,
   * the most common reason for global ids to be wrong is that they were not initialized. Global ids get initialized with active annotation
   * {@link com.avaloq.tools.asmd.testbase.scoping.Tag} that executes {@link com.avaloq.tools.asmd.testbase.scoping.TagCompilationParticipant}. Active
   * annotations do not get executed if {@code org.eclipse.xtend.lib} is missing in {@code MANIFEST.MF}. Thus we report this common mistake to the user via an
   * assertion.
   * </p>
   *
   * @param id
   *          Mark id
   * @return Mark text to be inserted in the source file, never {@code null}
   */
  protected String mark(final int id) {
    Assert.assertFalse("Tag with " + id + " used to mark more than one location.", usedTags.contains(id)); //$NON-NLS-1$ //$NON-NLS-2$
    usedTags.add(id);
    if (id < 1) {
      getMarkerTagsInfo().setTestClassInvalid();
      throw new AssertionError(INVALID_TEST_CONFIGURATION);
    }
    return MARKER_START_GUARD + id + MARKER_END_GUARD;
  }

  /**
   * Memorize an error that was detected during the validation of the current testing file.
   *
   * @param position
   *          position of the error in file, not {@code null}
   * @param error
   *          string with error, not {@code null}
   */
  protected void memorizeErrorOnPosition(final Integer position, final String error) {
    if (!errorsOnPosition.containsKey(position)) {
      errorsOnPosition.put(position, new StringBuilder());
    }
    errorsOnPosition.get(position).append(error);
  }

  /**
   * Processes all the inserted markers in a given source and creates a new {@link XtextTestSource} without the markers.
   *
   * @param sourceFileName
   *          the name of the source, may be {@code null}
   * @param sourceType
   *          the type of the source, may be {@code null}
   * @param sourceContent
   *          the content of the source, must not be {@code null}
   * @return the {@link XtextTestSource} created.
   */
  protected XtextTestSource processMarkers(final String sourceFileName, final TestSourceType sourceType, final CharSequence sourceContent) {
    StringBuilder withoutMarkers = new StringBuilder();
    final Multimap<Integer, AbstractModelAssertion> positionToAssertionMap = LinkedHashMultimap.create();
    Matcher m = PATTERN.matcher(sourceContent);
    int lastEnd = 0;
    while (m.find()) {
      withoutMarkers.append(sourceContent.subSequence(lastEnd, m.start()));
      lastEnd = m.end();
      int markerId = Integer.valueOf(m.group(1));
      // save the position of the marker only if we are dealing with an assertion marker
      AbstractModelAssertion assertionMarker = assertions.get(markerId);
      if (assertionMarker != null) {
        positionToAssertionMap.put(withoutMarkers.length(), assertionMarker);
      }
    }
    // Add the rest part of input string
    withoutMarkers.append(sourceContent.subSequence(lastEnd, sourceContent.length()));

    // Calculate source name
    String fullSourceFileName = "";
    if (sourceType == TestSourceType.CLIENT_CUSTOMER) {
      fullSourceFileName = CUSTOMER_SOURCE_PREFIX;
    }
    fullSourceFileName = fullSourceFileName.concat(sourceFileName);

    EObject semanticModel;
    String withoutMarkersAsString = withoutMarkers.toString();
    XtextTestSource testSource = createTestSource(fullSourceFileName, withoutMarkersAsString);
    semanticModel = testSource.getModel();
    beforeApplyAssertions(testSource);
    // Run validations on markers
    for (Map.Entry<Integer, AbstractModelAssertion> entry : positionToAssertionMap.entries()) {
      entry.getValue().apply(semanticModel, entry.getKey());
    }
    return testSource;
  }

  /**
   * Validate a source given by a file name and content.
   *
   * @param sourceFileName
   *          the file name that should be associated with the parsed content, not {@code null}
   * @param sourceType
   *          defines if the source is a kernel or customer source, not {@code null}
   * @param sourceContent
   *          source, not {@code null}
   */
  protected void validate(final String sourceFileName, final TestSourceType sourceType, final CharSequence sourceContent) {
    XtextTestSource testSource = processMarkers(sourceFileName, sourceType, sourceContent);
    processErrorsFound(testSource.getContent());
    afterValidate();
  }

  /**
   * Processes all the diagnostics in a given source.
   *
   * @param sourceWithoutMarkers
   *          the source to process, must not be {@code null}
   */
  protected void processErrorsFound(final String sourceWithoutMarkers) {
    if (!errorsOnPosition.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      sb.append(memorizedErrorsToString(sourceWithoutMarkers));
      sb.append(SPLITTING_LINE);
      sb.append("List of all found diagnostics:\n");
      sb.append(getAdditionalErrorMessageInformation());
      assertEquals("Errors found. Consider compare view.", sourceWithoutMarkers, sb.toString());
    }
  }

  /**
   * Inject memorized errors into the input file on positions where they were detected.
   *
   * @param source
   *          text of the input testing source, not {@code null}
   * @return
   *         input testing source with injected errors, never {@code null}
   */
  private String memorizedErrorsToString(final String source) {
    StringBuilder result = new StringBuilder();
    StringBuilder errorBuffer = new StringBuilder();
    // Sort positions
    ArrayList<Integer> positions = Lists.newArrayList(errorsOnPosition.keySet());

    int posIdx = 0;
    int sourceIdx = 0;

    while (sourceIdx < source.length()) {
      int lineBreakIndex = source.indexOf('\n', sourceIdx);
      if (lineBreakIndex < 0) {
        lineBreakIndex = source.length();
      }
      while (posIdx < positions.size() && positions.get(posIdx) < lineBreakIndex) {
        int nextPos = positions.get(posIdx);
        result.append(source.substring(sourceIdx, nextPos));
        result.append("<FAILURE ");
        result.append(posIdx + 1);
        result.append('>');
        // Add error message to buffer
        errorBuffer.append(SPLITTING_LINE);
        errorBuffer.append("FAILURE ");
        errorBuffer.append(posIdx + 1);
        errorBuffer.append(": ");
        errorBuffer.append(errorsOnPosition.get(nextPos));
        sourceIdx = nextPos;
        posIdx++;
      }
      if (errorBuffer.length() > 0) {
        errorBuffer.append(SPLITTING_LINE);
      }
      result.append(source.substring(sourceIdx, lineBreakIndex));
      result.append(errorBuffer);
      errorBuffer = new StringBuilder();
      result.append(LINE_BREAK);
      sourceIdx = lineBreakIndex + 1;
    }
    result.append(source.substring(sourceIdx));
    result.append(errorBuffer);
    return result.toString();
  }

  /**
   * Searches an object for the given tag. First checks local tags. If not found then searches this tag in the required sources.
   *
   * @param tag
   *          Tag
   * @return EObject or {@code null}
   */
  protected EObject getObjectForTag(final int tag) {
    EObject object = getMarkerTagsInfo().getModel(tag);
    if (object == null) {
      // Not in source under test
      String sourceName = getMarkerTagsInfo().getSource(tag);
      if (sourceName != null) {
        INode node = NodeModelUtils.findActualNodeFor(getTestSource(sourceName).getModel());
        INode leafNode = NodeModelUtils.findLeafNodeAtOffset(node, getMarkerTagsInfo().getOffset(tag));
        object = NodeModelUtils.findActualSemanticObjectFor(leafNode);
      }
    }
    assertNotNull("Tag " + tag + " should mark an object. Use «mark(TAG)» in a code snippet.", object); //$NON-NLS-1$//$NON-NLS-2$
    return object;
  }

  /**
   * Return the offset for the given tag.
   *
   * @param tag
   *          The tag for which to find the offset
   * @return the offset found or {@code null} if the given tag is not marking an object
   */
  protected Integer getOffsetForTag(final int tag) {
    Integer offset = getMarkerTagsInfo().getOffset(tag);
    assertNotNull("Tag " + tag + " should mark an object. Use «mark(TAG)» in a code snippet.", offset); //$NON-NLS-1$//$NON-NLS-2$
    return offset;
  }

  /**
   * Generate a unique tag id.
   * <p>
   * Use this method for local tags only. Global tags must use @Tag annotation to ensure the same value over multiple instances of the test class.
   * </p>
   *
   * @return Tag id
   */
  public int getTag() {
    localMarkerIdCounter++;
    assertTrue("Too many local tags. Must be less than " + TagCompilationParticipant.COUNTER_BASE //$NON-NLS-1$
        + " per test method", localMarkerIdCounter < TagCompilationParticipant.COUNTER_BASE); //$NON-NLS-1$
    return localMarkerIdCounter;
  }

  /**
   * Return the {@link MarkerTagsInfo} associated to one test class.
   *
   * @return the associated {@link MarkerTagsInfo}
   */
  protected MarkerTagsInfo getMarkerTagsInfo() {
    MarkerTagsInfo info = (MarkerTagsInfo) getTestInformation().getTestObject(MarkerTagsInfo.class);
    if (info == null) {
      info = new MarkerTagsInfo();
      getTestInformation().putTestObject(MarkerTagsInfo.class, info);
    }
    return info;
  }

  /**
   * This class preserves information about tags in the required sources
   * for all tests within one test class. Tags for current test are also stored here.
   * One may prefer in the future to be able to clean tags for the current test after the test.
   */
  protected class MarkerTagsInfo {

    // For sources under test
    /** The tag to model. */
    private final Map<Integer, EObject> tagToModel = Maps.newHashMap();

    /** The tag to cross reference. */
    private final Map<Integer, CrossReference> tagToCrossReference = Maps.newHashMap();

    // For sources added early (only object referencing, no need to search cross references)
    /** The tag to source. */
    private final Map<Integer, String> tagToSource = Maps.newHashMap();

    /** The tag to offset. */
    private final Map<Integer, Integer> tagToOffset = Maps.newHashMap();

    private boolean invalidTestClass;

    /**
     * Registers one tag for the source under test.
     * This source is actually used for testing and we need both: outgoing cross references as well as declared elements.
     *
     * @param tag
     *          New id for the tag
     * @param context
     *          Current object that will correspond to this tag
     * @param crossReference
     *          the cross reference {@code null} for declaration or the corresponding cross reference in the grammar for a reference
     */
    public void registerLocalTag(final int tag, final EObject context, final CrossReference crossReference) {
      tagToModel.put(tag, context);
      if (crossReference != null) {
        tagToCrossReference.put(tag, crossReference);
      }
    }

    /**
     * Register a tag in the required source. Only declarations are supported.
     *
     * @param tag
     *          New tag (must be unique)
     * @param sourceName
     *          Source name
     * @param offset
     *          Offset within the source
     */
    public void registerRequiredSourceTag(final int tag, final String sourceName, final int offset) {
      tagToSource.put(tag, sourceName);
      tagToOffset.put(tag, offset);
    }

    /**
     * Returns the context model element for local tags.
     *
     * @param tag
     *          Tag
     * @return Model element
     */
    public EObject getModel(final int tag) {
      return tagToModel.get(tag);
    }

    /**
     * Returns cross references for local tags.
     *
     * @param tag
     *          Tag
     * @return Cross reference grammar element
     */
    public CrossReference getCrossReference(final int tag) {
      return tagToCrossReference.get(tag);
    }

    /**
     * Returns source name for global tag.
     *
     * @param tag
     *          Tag
     * @return Source name
     */
    public String getSource(final int tag) {
      return tagToSource.get(tag);
    }

    /**
     * Returns offsets for global tags.
     *
     * @param tag
     *          Tag
     * @return Offset
     */
    public Integer getOffset(final int tag) {
      return tagToOffset.get(tag);
    }

    /**
     * Clear local tags from current test. Clears all tags up to the id passed, but not outside the range reserved for local tags.
     *
     * @param maxId
     *          the tag id
     */
    public void clearTags(final long maxId) {
      for (int i = 1; i <= Math.min(maxId, TagCompilationParticipant.COUNTER_BASE - 1); i++) {
        tagToCrossReference.remove(i);
        tagToModel.remove(i);
        tagToOffset.remove(i);
        tagToSource.remove(i);
      }
    }

    public boolean isInvalidTestClass() {
      return invalidTestClass;
    }

    /**
     * Marks the current test class as invalid. All tests within this class will report same error.
     */
    public void setTestClassInvalid() {
      this.invalidTestClass = true;
    }
  }

  /**
   * Before apply assertions.
   *
   * @param testSource
   *          the test source, not {@code null}
   */
  protected void beforeApplyAssertions(final XtextTestSource testSource) {
  }

  /**
   * Gets additional error message information.
   *
   * @return additional error message information, never {@code null}
   */
  protected String getAdditionalErrorMessageInformation() {
    return "";
  }

  /**
   * Returns an unmodifiable view of the tags generated by {@link getTag()}.
   *
   * @return the unmodifiable view of the {@link #usedTags} set
   */
  public Set<Integer> getUsedTagsItems() {
    return Collections.unmodifiableSet(usedTags);
  }

  /**
   * Processing after validations.
   */
  protected void afterValidate() {
  }
}
