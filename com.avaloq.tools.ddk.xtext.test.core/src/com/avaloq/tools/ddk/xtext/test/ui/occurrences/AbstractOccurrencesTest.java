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
package com.avaloq.tools.ddk.xtext.test.ui.occurrences;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.Set;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtext.ui.editor.occurrences.Messages;
import org.junit.After;
import org.junit.Before;

import com.avaloq.tools.ddk.test.core.util.JobChangeListener;
import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextUiTest;
import com.google.common.collect.Sets;


/**
 * Tests xtext editor occurrences.
 */
public abstract class AbstractOccurrencesTest extends AbstractXtextUiTest {

  private static final char SEMANTIC_MARKER_CODE = 's';
  private static final char CURSOR_MARKER_CODE = 'c';
  private static final char NOSEMANTIC_MARKER_CODE = 'n';
  private static final char MARKER = '#';
  private static final int TIMEOUT_FOR_MARKING = 4000;
  private static final Color NO_COLOR = null;
  private final JobChangeListener jobChangeListener = new JobChangeListener();

  /**
   * Mark cursor position.
   *
   * @return the cursor marker, never {@code null}
   */
  public String cursor() {
    return String.valueOf(MARKER) + CURSOR_MARKER_CODE + MARKER;
  }

  /**
   * Mark position for semantic occurrence.
   *
   * @return the semantic occurrence marker, never {@code null}
   */
  public String semantic() {
    return String.valueOf(MARKER) + SEMANTIC_MARKER_CODE + MARKER;
  }

  /**
   * Mark position for no semantic occurrence.
   *
   * @return the marker with no semantic occurrence, never {@code null}
   */
  public String noSemantic() {
    return String.valueOf(MARKER) + NOSEMANTIC_MARKER_CODE + MARKER;
  }

  /**
   * Stores occurrences to be checked.
   */
  private static class OccurrencesToCheck {
    private int cursorOffset;
    private final Set<Integer> semanticMarkers = Sets.newHashSet();
    private final Set<Integer> noSemanticMarkers = Sets.newHashSet();

    /**
     * Set where the cursor should be placed to test occurrences.
     *
     * @param offset
     *          cursor offset
     */
    public void setCursor(final int offset) {
      this.cursorOffset = offset;
    }

    /**
     * Semantic occurrences.
     *
     * @param offset
     *          cursor offset
     */
    public void addSemanticOccurrence(final int offset) {
      semanticMarkers.add(offset);
    }

    /**
     * No semantic occurrences.
     *
     * @param offset
     *          cursor offset
     */
    public void addNoSemanticOccurence(final int offset) {
      noSemanticMarkers.add(offset);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addKernelSourceToWorkspace(final String sourceFileName, final CharSequence sourceContent) {

    OccurrencesToCheck occurrences = (OccurrencesToCheck) getTestInformation().getTestObject(OccurrencesToCheck.class);
    if (occurrences == null) {
      occurrences = new OccurrencesToCheck();
      getTestInformation().putTestObject(OccurrencesToCheck.class, occurrences);
    }
    StringBuilder content = new StringBuilder();
    int pos = 0;
    int originalLength = sourceContent.length();
    while (pos < originalLength) {
      char ch = sourceContent.charAt(pos++);
      if (ch == MARKER && pos < originalLength - 2 && sourceContent.charAt(pos + 1) == MARKER) {
        ch = sourceContent.charAt(pos);
        if (ch == SEMANTIC_MARKER_CODE) {
          occurrences.addSemanticOccurrence(content.length());
        } else if (ch == NOSEMANTIC_MARKER_CODE) {
          occurrences.addNoSemanticOccurence(content.length());
        } else if (ch == CURSOR_MARKER_CODE) {
          if (occurrences.cursorOffset != 0) {
            throw new WrappedException("Cursor can be set only once", null); //$NON-NLS-1$
          }
          occurrences.setCursor(content.length());
        } else {
          throw new WrappedException("Invalid marker in the test", null); //$NON-NLS-1$
        }
        pos += 2;
        ch = sourceContent.charAt(pos++);
      }
      content.append(ch);
    }
    super.addKernelSourceToWorkspace(sourceFileName, content);

  }

  /**
   * Sets up the {@link JobChangeListener}.
   */
  @Before
  public void setUpJobListener() {
    Job.getJobManager().addJobChangeListener(jobChangeListener);
  }

  /**
   * Tears down the {@link JobChangeListener}.
   */
  @After
  public void tearDownJobListener() {
    Job.getJobManager().removeJobChangeListener(jobChangeListener);
    jobChangeListener.reset();
  }

  private final DefaultCondition occurenceJobCondition = new DefaultCondition() {
    @Override
    public boolean test() {
      return jobChangeListener.isJobDone(Messages.OccurrenceMarker_MarkOccurrenceJob_title);
    }

    @Override
    public String getFailureMessage() {
      return "Error waiting for occurrences";
    }
  };

  /**
   * Tests occurrences.
   *
   * @throws BadLocationException
   *           if thrown means bad test
   */
  public void testOccurrences() throws BadLocationException {
    // Occurrence job starts after we open an editor, need to wait
    getBot().waitUntil(occurenceJobCondition, TIMEOUT_FOR_MARKING);
    SWTBotEditor editorBot = getBot().activeEditor();
    SWTBotEclipseEditor eBot = editorBot.toTextEditor();

    OccurrencesToCheck occurrencesMap = (OccurrencesToCheck) getTestInformation().getTestObject(OccurrencesToCheck.class);

    IEditorPart editorPart = editorBot.getReference().getEditor(true);
    ITextEditor editor = editorPart.getAdapter(ITextEditor.class);
    IDocumentProvider provider = editor.getDocumentProvider();
    IDocument document = provider.getDocument(editorPart.getEditorInput());
    int cursorLine = document.getLineOfOffset(occurrencesMap.cursorOffset);
    int cursorColumn = occurrencesMap.cursorOffset - document.getLineOffset(cursorLine);

    // Reset the listener and trigger the second occurrence job
    jobChangeListener.reset();
    eBot.navigateTo(cursorLine, cursorColumn);
    eBot.pressShortcut(Keystrokes.LEFT);
    getBot().waitUntil(occurenceJobCondition, TIMEOUT_FOR_MARKING);

    for (int offset : occurrencesMap.semanticMarkers) {
      Color color = getColorAtOffset(eBot, document, offset);
      assertNotSame("Semantical reference selection colored", color, NO_COLOR); //$NON-NLS-1$
    }

    for (int offset : occurrencesMap.noSemanticMarkers) {
      Color color = getColorAtOffset(eBot, document, offset);
      assertSame("No semantical reference selection should not be colored", color, NO_COLOR); //$NON-NLS-1$
    }
  }

  /**
   * Returns background color at the given offset in the given document.
   *
   * @param eBot
   *          editor bot, may be {@code null}
   * @param document
   *          document, may be {@code null}
   * @param offset
   *          offset in the document
   * @return color, may be {@code null}
   * @throws BadLocationException
   *           if offset is not valid for the document
   */
  private Color getColorAtOffset(final SWTBotEclipseEditor eBot, final IDocument document, final int offset) throws BadLocationException {
    int line = document.getLineOfOffset(offset);
    int column = offset - document.getLineOffset(line);
    return eBot.getStyle(line, column).background;
  }

}
