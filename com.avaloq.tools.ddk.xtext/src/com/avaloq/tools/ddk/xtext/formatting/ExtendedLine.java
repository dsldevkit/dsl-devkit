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
package com.avaloq.tools.ddk.xtext.formatting;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;

import com.avaloq.tools.ddk.xtext.formatting.locators.ILinewrapLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LinewrapLocatorFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoFormatLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.SubtractingLinewrapLocatorFacade;
import com.google.common.collect.Lists;


/**
 * This class overrides {@link org.eclipse.xtext.formatting.impl.FormattingConfigBasedStream.Line} in order to support processing of other locators than those
 * defined by {@link org.eclipse.xtext.formatting.impl.FormattingConfig}.
 */
public class ExtendedLine extends ExtendedFormattingConfigBasedStream.AbstractExtendedLine {

  /**
   * All entries are contained in this list.
   */
  private final List<ExtendedLineEntry> lineEntries = Lists.newArrayList();
  /**
   * The initialLinewrap is used to force a line wrap after an automatic line
   * break. Automatic line breaks occur if the length of the line exceeds
   * FormattingConfig.getCharsPerLine().
   */
  private ILinewrapLocator initialLinewrapLocator;
  private final ExtendedFormattingConfigBasedStream configBasedStream;
  /**
   * This represents the new lines and indentation of the line.
   */
  private WrapEntry linewrapEntry;

  /**
   * Creates a new instance of {@link com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfigBasedStream.AbstractExtendedLine}.
   */
  public ExtendedLine(final ExtendedFormattingConfigBasedStream configBasedStream) { // NOPMD - false positive by PMD
    configBasedStream.super();
    this.configBasedStream = configBasedStream;
  }

  /**
   * Gets the associated {@link ExtendedFormattingConfigBasedStream}.
   *
   * @return the {@link ExtendedFormattingConfigBasedStream}.
   */
  public ExtendedFormattingConfigBasedStream getConfigBasedStream() {
    return configBasedStream;
  }

  /**
   * Check if line is empty.
   * A line is considered empty if it does not contain any non-white space entries.
   *
   * @return true if line empty
   */
  protected boolean isLineEmpty() {
    return getEntries().isEmpty();
  }

  /**
   * Checks if an entry is the first in this line.
   *
   * @param entry
   *          the entry to be tested
   * @return true, if the entry is first
   */
  protected boolean isFirstEntry(final ExtendedLineEntry entry) {
    return getEntries().indexOf(entry) == 0;
  }

  @Override
  public ExtendedLine addEntry(final ExtendedLineEntry lineEntry) throws IOException {
    if (mustFormatLineEntry(lineEntry)) {
      return formatLineEntry(lineEntry);
    } else {
      return preserveFormat(lineEntry);
    }
  }

  /**
   * Determine whether a LineEntry must be formatted.
   *
   * @param lineEntry
   *          the line entry to be tested
   * @return true, if must format
   */
  protected boolean mustFormatLineEntry(final ExtendedLineEntry lineEntry) {
    return !(isPreserveSpaces() && lineEntry.getPreservedWS() != null);
  }

  /**
   * Checks whether the given {@link ExtendedLineEntry} is a single line comment.
   *
   * @param entry
   *          the entry to check
   * @return {@code true} if the entry is an SL-Comment, otherwise {@code false}
   */
  protected boolean isSLComment(final ExtendedLineEntry entry) {
    if (isCommentEntry(entry)) {
      final AbstractRule grammarElement = (AbstractRule) entry.getGrammarElement();
      return "SL_COMMENT".equals(grammarElement.getName()); //$NON-NLS-1$
    }
    return false;
  }

  /**
   * Checks whether the given {@link ExtendedLineEntry} is a multiline comment.
   *
   * @param entry
   *          the entry to check
   * @return {@code true} if the entry is a ML-Comment, otherwise {@code false}
   */
  protected boolean isMLComment(final ExtendedLineEntry entry) {
    if (isCommentEntry(entry)) {
      final AbstractRule grammarElement = (AbstractRule) entry.getGrammarElement();
      return "ML_COMMENT".equals(grammarElement.getName()); //$NON-NLS-1$
    }
    return false;
  }

  /**
   * Utility method to decide whether a line entry is a comment.
   *
   * @param lineEntry
   *          the line entry to check
   * @return {@code true} if the entry c a comment, otherwise {@code false}
   */
  protected boolean isCommentEntry(final ExtendedLineEntry lineEntry) {
    final EObject grammarElement = lineEntry.getGrammarElement();
    return grammarElement instanceof AbstractRule && getHiddenTokenHelper().isComment((AbstractRule) grammarElement);
  }

  /**
   * Checks whether a line entry is a text field.
   *
   * @param lineEntry
   *          the line entry to check
   * @return {@code true} if the entry is a text field, otherwise {@code false}
   */
  private boolean isTextField(final ExtendedLineEntry lineEntry) {
    final EObject grammarElement = lineEntry.getGrammarElement();
    return grammarElement instanceof RuleCall && "STRING".equals(((RuleCall) grammarElement).getRule().getName()); //$NON-NLS-1$
  }

  /**
   * Checks whether a line entry can be ignored in calculating the length of a line.
   *
   * @param lineEntry
   *          the line entry to check
   * @return {@code true} if the line can be ignored (i.e. it's a comment or a text field), otherwise {@code false}.
   */
  private boolean isIgnorableEntry(final ExtendedLineEntry lineEntry) {
    boolean noFormat = lineEntry.getEntryLocators().stream().anyMatch(t -> t instanceof NoFormatLocator);
    return isCommentEntry(lineEntry) || isTextField(lineEntry) || noFormat;
  }

  /**
   * Checks whether a given {@link ExtendedLineEntry} is ignorable (i.e. text or comment) and is what makes a line length exceed the associated
   * {@link ExtendedFormattingConfigBasedStream} limit.
   *
   * @param entry
   *          the {@link ExtendedLineEntry} to check, must not be {@code null}
   * @param totalLineLength
   *          the line length
   * @return {@code true} if the given entry is a text or a comment and makes the total line length exceed the limit, otherwise {@code false}
   */
  private boolean isIgnorableEntryExceedingLimit(final ExtendedLineEntry entry, final int totalLineLength) {
    return isIgnorableEntry(entry) && totalLineLength > configBasedStream.getCharsPerLine()
        && (totalLineLength - entry.getLength() < configBasedStream.getCharsPerLine());
  }

  /**
   * Checks whether a line entry is not line breaking.
   * I.e. the entry itself is explicitly non breakable or it can be ignored (it is a comment, a text field or it's a entry withe explicit no_format rules.
   *
   * @param lineEntry
   *          the {@link ExtendedLineEntry} to check, must not be {@code null}
   * @return {@code true} if the line entry is a non-linebreaking entry, {@code false} otherwise.
   */
  private boolean isNonLinebreakingEntry(final ExtendedLineEntry lineEntry) {
    return !lineEntry.isBreakable() || isIgnorableEntry(lineEntry);
  }

  /**
   * Add a line entry to the line and prepend it with whitespace according to
   * formatter configuration.
   * The line will be wrapped if:
   * - the new line entry would cause line's length to exceed the maximum line length
   * - one of lineEntries locators is a line wrap locator.
   * The line will be flushed (written to out-stream) if a line wrap occurs.
   *
   * @param lineEntry
   *          - the line entry to be appended to the line
   * @return Line - the line containing line entry or an empty new line
   * @throws IOException
   *           - exception propagated from the underlying token stream.
   */
  protected ExtendedLine formatLineEntry(final ExtendedLineEntry lineEntry) throws IOException {
    ExtendedLine newLine = this;
    final WrapEntry wrapEntry = processLinewraps(lineEntry);
    if (!isLineEmpty() && wrapEntry.isWrap()) {
      flush();
      return ((ExtendedLine) configBasedStream.createLine()).addEntry(lineEntry);
    } else if (isLineEmpty()) {
      setLinewrapEntry(wrapEntry);
      lineEntry.setLeadingSpaceEntry(processSpaces(lineEntry));
      getEntries().add(lineEntry);
    } else {
      final SpaceEntry spaceEntry = processSpaces(lineEntry);
      final int totalLineLength = getAbsoluteLineLength(lineEntry) + spaceEntry.getLength() + lineEntry.countCharactersInFirstLine();
      ExtendedLineEntry previousEntry = getEntries().get(getEntries().size() - 1);
      boolean isFirstFormattableEntry = getLastBreakableLineEntry() == null && !previousEntry.isFormattingDisabled();

      if (isNonLinebreakingEntry(lineEntry) || totalLineLength <= (configBasedStream.getCharsPerLine()) || isFirstFormattableEntry
          || isIgnorableEntryExceedingLimit(previousEntry, totalLineLength)) {
        if (!lineEntry.isFormattingDisabled()) {
          lineEntry.setLeadingSpaceEntry(spaceEntry);
        }
        String postProcessedValue = getConfigBasedStream().getFormatter().executeCustomPostFormatAction(lineEntry, getEntries());
        if (postProcessedValue != null) {
          lineEntry.setValue(postProcessedValue);
        }

        getEntries().add(lineEntry);
      } else {
        newLine = breakLine();
        return newLine.addEntry(lineEntry);
      }
    }
    final int lastLineLength = lineEntry.countCharactersInLastLine();
    if (lastLineLength >= 0) {
      flush();
      newLine = (ExtendedLine) configBasedStream.createLine();
      if (lastLineLength > 0) {
        newLine.setInitialLinewrapLocator();
      } else {
        newLine.setInitialLinewrapLocator(new SubtractingLinewrapLocatorFacade(configBasedStream.getFormattingConfig()));
      }
    }
    return newLine;
  }

  /**
   * Append a line entry to this line but ignore any locators.
   * The line entry is prepended with LineEntry.leadingWS white space.
   *
   * @param lineEntry
   *          - the line entry to be appended to line
   * @return Line - the line to which line entry was added / {code}this{code}
   */
  protected ExtendedLine preserveFormat(final ExtendedLineEntry lineEntry) {
    lineEntry.setLeadingSpaceEntry(new SpaceEntry(getHiddenTokenHelper(), this, lineEntry) {
      @Override
      public String getValue() {
        return lineEntry.getPreservedWS();
      }

    });
    String postProcessedValue = getConfigBasedStream().getFormatter().executeCustomPostFormatAction(lineEntry, getEntries());
    if (postProcessedValue != null) {
      lineEntry.setValue(postProcessedValue);
    }
    getEntries().add(lineEntry);
    return this;
  }

  /**
   * Gets the column offset preceding the given entry in this line.
   *
   * @param entry
   *          the entry
   * @return the column of the given entry
   */
  public int getColumn(final ExtendedLineEntry entry) {
    return (getLinewrapEntry() == null ? 0 : getLinewrapEntry().getIndentString().length()) + getOffset(entry);
  }

  /**
   * Gets the column offset preceding the given entry in this line, not including the line's indentation.
   *
   * @param entry
   *          the entry
   * @return the offset of the given entry
   */
  public int getOffset(final ExtendedLineEntry entry) {
    int column = 0;
    for (ExtendedLineEntry lineEntry : getEntries()) {
      if (Objects.equals(entry, lineEntry)) {
        break;
      }
      int charactersInLastLine = lineEntry.countCharactersInLastLine();
      if (charactersInLastLine != -1) {
        column = charactersInLastLine;
      } else {
        if (entry.isFormattingDisabled()) {
          final String preservedWS = lineEntry.getPreservedWS();
          final int preservedWSLength = preservedWS != null ? preservedWS.length() : 0;
          column = lineEntry.getIndent() + (lineEntry.getLength() + preservedWSLength);
        } else {
          if (lineEntry.isFormattingDisabled() && lineEntry.countExistingLeadingNewlines() > 0) {
            column = 0;
          }
          column += lineEntry.getLength();
          if (lineEntry.isFormattingDisabled()) {
            int newLineIndex = lineEntry.getLeadingWS().lastIndexOf("\n") + 1; //$NON-NLS-1$
            if (newLineIndex > 0) {
              column += lineEntry.getLeadingWS().substring(newLineIndex).length();
            }
          } else {
            column += lineEntry.getLeadingWS().length();
          }
        }
      }
    }
    return column;
  }

  /**
   * Returns the line length from the beginning of the line till the given entry, excluding whitespaces just before the given entry providing that the given
   * entry is not the first entry in the line.
   *
   * @param entry
   *          line entry
   * @return int - the length of this line
   */
  public int getAbsoluteLineLength(final ExtendedLineEntry entry) {
    int lineLength;
    int previousEntryIndex = findPredecessorOrLastEntryIndex(entry);

    if (entry != null) {
      ExtendedLineEntry previousEntry = getEntries().get(previousEntryIndex);
      // computing the line length based on the position of the previous (preceeding) entry
      if (previousEntry != null && !previousEntry.equals(entry)) {
        lineLength = getColumn(entry);
        // case when the previous entry is the first in the line and it is aligned to the column
        if (previousEntryIndex == 0 && previousEntry.isFixedLocatorOpening() && entry.containsVariableLocator()) {
          lineLength += previousEntry.getColumnIndent();
          // case when the entry is relative to column alignment of the other entry which is not a direct predecessor (some entries between entry aligned with
          // column and the given entry) not necessarily in the same line AND the previous entry is within column alignment
        } else if (previousEntry.getColumnIndent() > 0 && !previousEntry.isFixedLocatorClosing() && !previousEntry.isFixedLocatorOpening()
            && entry.isFixedLocatorOpening()) {
          if (!existsIndirectColumnAlignedPredecessor(entry, previousEntry)) {
            lineLength += previousEntry.getColumnIndent();
          }
          // case when the given entry is the first entry in the line
        } else if (previousEntryIndex == 0 && entry.isFixedLocatorOpening() && previousEntry.getColumnIndent() > 0) {
          lineLength = entry.getColumnIndent();
          // case when the entry is relative to column alignment of the other entry which is not a direct predecessor (some entries between entry aligned with
          // column and the given entry) AND the previous entry is NOT within column alignment
        } else if (lineEntries.get(0) != previousEntry && lineEntries.get(0) != entry && lineEntries.get(0).getColumnIndent() > 0) {
          lineLength += lineEntries.get(0).getColumnIndent();
        }
        // TODO add here other cases when (if) identified
      } else {
        // case when the entry is the first entry in the new line and it is aligned with the column
        if (previousEntry != null && previousEntry.isFixedLocatorOpening() && previousEntryIndex == 0) {
          lineLength = previousEntry.getColumnIndent();
          lineLength += previousEntry.getValue().length();
          // case when the entry is the first entry in the new line and it is NOT aligned with the column
        } else {
          lineLength = getColumn(entry);
        }
      }
      // all other cases
    } else {
      lineLength = getColumn(entry);
    }
    return lineLength;
  }

  /**
   * Checks if exists a entry that is a indirect predecessor of the given entry that is aligned using columns.
   *
   * @param entry
   *          current entry
   * @param previousEntry
   *          previous entry
   * @return
   *         true if indirect predecessor exists
   */
  private boolean existsIndirectColumnAlignedPredecessor(final ExtendedLineEntry entry, final ExtendedLineEntry previousEntry) {
    for (ExtendedLineEntry lineEntry : lineEntries) {
      if (!Objects.equals(lineEntry, previousEntry) && !Objects.equals(lineEntry, entry)
          && (lineEntry.isFixedLocatorClosing() || lineEntry.isFixedLocatorOpening())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns an index of the line entry that occurs before the given entry in the line or if the given entry does not occur - the last entry from the line.
   *
   * @param entry
   *          of which the predecessor should be found
   * @return index of the predecessor or last element of the line if the line does not contain the given element
   */
  public int findPredecessorOrLastEntryIndex(final ExtendedLineEntry entry) {
    int index = 0;
    boolean predecessorExists = false;
    for (int i = 0; i < getEntries().size(); i++) {
      if (getEntries().get(i).equals(entry)) {
        index = i;
        predecessorExists = true;
        break;
      }
    }
    if (index == 0 && !predecessorExists) {
      // last entry from the line
      index = getEntries().size() - 1;
    } else if (index != 0) {
      index--;
    }
    return index;
  }

  @Override
  public void flush() throws IOException {
    flush(getOutStream(), getEntries().size());
  }

  /**
   * Write part of this line to a token stream. The part to be written are
   * all line entries from 0 .. endIndex.
   *
   * @param intoStream
   *          - the token stream into which to write this line.
   * @param endIndex
   *          the index of the last entry to flush, exclusive.
   * @throws IOException
   *           - propagated exception from underlying token stream
   */
  @Override
  protected void flush(final ITokenStream intoStream, final int endIndex) throws IOException {
    if (firstNotIgnored == 0 && getLinewrapEntry() != null) {
      intoStream.writeHidden(getLinewrapEntry().getGrammarElement(), getLinewrapEntry().getValue());
    }
    for (int i = firstNotIgnored; i < endIndex; i++) {
      final ExtendedLineEntry entry = getEntries().get(i);
      int previousEntryIndex = findPredecessorOrLastEntryIndex(entry);
      final ExtendedLineEntry previousEntry = lineEntries.get(previousEntryIndex);
      final boolean shouldBreakTheLine = entry.isFixedLocatorOpening() && entry.isFixed() && (getAbsoluteLineLength(entry) >= entry.getColumnIndent());
      final boolean isPreviousDifferentThanCurrent = (previousEntryIndex == 0 && !previousEntry.equals(entry));
      final boolean isPreviousColumnAlignedAndNotUtmost = (previousEntry.getColumnIndent() > 0 && !previousEntry.isFixedLocatorClosing()
          && !previousEntry.isFixedLocatorOpening());
      final boolean isCurrentBreakableAndColumnAligned = !entry.isNoBreak() && entry.isFixedLocatorOpening() && entry.isFixedLocatorClosing();

      if (shouldBreakTheLine || (isCurrentBreakableAndColumnAligned && isPreviousDifferentThanCurrent && isPreviousColumnAlignedAndNotUtmost)) { // nobreak
        intoStream.writeHidden(entry.getGrammarElement(), "\n" + SpaceEntry.createPadding(entry.getColumnIndent())); //$NON-NLS-1$
      } else {
        intoStream.writeHidden(entry.getGrammarElement(), entry.getLeadingWS());
      }
      if (entry.isHidden()) {
        intoStream.writeHidden(entry.getGrammarElement(), entry.getValue());
      } else {
        intoStream.writeSemantic(entry.getGrammarElement(), entry.getValue());
      }
    }
    firstNotIgnored = 0; // Reset
  }

  private int firstNotIgnored;

  /**
   * If called then all adds to the line before this point will be ignored in the output.
   * Used by partial formatting. Allows to preserve formatting rules e.g. columns.
   */
  public void activateIgnoreEarlierEntries() {
    firstNotIgnored = getEntries().size();
  }

  /**
   * Break a line before last breakable entry.
   *
   * @return the line
   * @throws IOException
   *           Signals that an I/O exception has occurred.
   */
  protected ExtendedLine breakLine() throws IOException {
    final ExtendedLineEntry lastBreakableEntry = getLastBreakableLineEntry();
    ExtendedLine newLine = null;
    if (lastBreakableEntry != null) {
      final int lastBrakableIndex = getEntries().indexOf(lastBreakableEntry);
      flush(getOutStream(), lastBrakableIndex);
      newLine = (ExtendedLine) configBasedStream.createLine();
      newLine.setInitialLinewrapLocator();
      final List<ExtendedLineEntry> entries = getEntries();
      for (int i = lastBrakableIndex; i < entries.size(); i++) {
        newLine = newLine.addEntry(entries.get(i));
      }
    } else if (getEntries().get(getEntries().size() - 1).isFormattingDisabled()) {
      flush(getOutStream(), getEntries().size());
      newLine = (ExtendedLine) configBasedStream.createLine();
      newLine.setInitialLinewrapLocator();
    }
    return newLine;
  }

  /**
   * Create white-space entry given a line entry. The resulting SpaceEntry represents a combination
   * of the entry's locators.
   * This operation is intended to be executed BEFORE adding line entry to line.
   *
   * @param entry
   *          - the line entry whose locators are to be processed.
   * @return SpaceEntry - a white-space entry consisting of 0 or more blanks.
   */
  public SpaceEntry processSpaces(final ExtendedLineEntry entry) {
    return new SpaceEntry(getHiddenTokenHelper(), this, entry);
  }

  /**
   * Create white-space entry given a line entry. The resulting WrapEntry represents a combination
   * of the entry's indentation and line wrap locators.
   * This operation is intended to be executed BEFORE adding line entry to line for which {@link #isLineEmpty()} is true.
   *
   * @param lineEntry
   *          - the line entry whose locators are to be processed.
   * @return WrapEntry - a white-space entry consisting of 0 or more new-lines and 0 or more blanks.
   */
  protected WrapEntry processLinewraps(final ExtendedLineEntry lineEntry) {
    return new WrapEntry(getHiddenTokenHelper(), this, lineEntry);
  }

  /**
   * Returns the indent of the line.
   *
   * @return the indent, a non-null String
   */
  public String getIndentString() {
    if (isLineEmpty()) {
      return ""; //$NON-NLS-1$
    }
    return getLinewrapEntry().getIndentString();
  }

  @Override
  public String getIndentation(final int indentation) { // NOPMD UselessOverridingMethod:
    return super.getIndentation(indentation);
  }

  /**
   * Get a list of all line entries (white-space entries as well as semantical entries).
   *
   * @return List - a list of all AcfLineEntry in by this line
   */
  public List<ExtendedLineEntry> getEntries() {
    return lineEntries;
  }

  /**
   * Clears all the line entries.
   */
  public void emptyEntries() {
    this.lineEntries.clear();
  }

  /**
   * Get the last breakable line entry.
   * A line entry is breakable if:
   * - it is not the first line entry in the line.
   * - it does not have a NoLinewrapLocator.
   * - its formatting is not disabled by rule.
   *
   * @return AcfLineEntry - the last entry in this line which is breakable.
   */
  protected ExtendedLineEntry getLastBreakableLineEntry() {
    for (int i = getEntries().size() - 1; i > -1; i--) {
      final ExtendedLineEntry entry = getEntries().get(i);
      if (!entry.isFormattingDisabled() && entry.isBreakable() && getPrecedingLineEntry(entry) != null) {
        return entry;
      }
    }
    return null;
  }

  /**
   * Get the line entry before given entry or the line's last entry if given entry is not found in line.
   *
   * @param entry
   *          - entry at which to start searching for a line entry
   * @return AcfLineEntry - the line entry preceding given entry or NULL if line is empty.
   */
  public ExtendedLineEntry getPrecedingLineEntry(final ExtendedLineEntry entry) {
    ExtendedLineEntry result = null;
    for (ExtendedLineEntry le : getEntries()) {
      if (!Objects.equals(le, entry)) {
        result = le;
      } else {
        return result;
      }
    }
    return result;
  }

  /**
   * Force an initial line wrap regardless of the first line entry's locators.
   * This method is only intended to be used for new / empty lines which are created
   * as a result of an automatic line wrap.
   */
  void setInitialLinewrapLocator() {
    setInitialLinewrapLocator(new LinewrapLocatorFacade(configBasedStream.getFormattingConfig(), 1));
  }

  /**
   * Sets initial line-wrap locator.
   *
   * @param linewrap
   *          locator
   */
  void setInitialLinewrapLocator(final ILinewrapLocator linewrap) {
    this.initialLinewrapLocator = linewrap;
  }

  ILinewrapLocator getInitialLinewrapLocator() {
    return initialLinewrapLocator;
  }

  protected void setLinewrapEntry(final WrapEntry linewrapEntry) {
    this.linewrapEntry = linewrapEntry;
  }

  protected WrapEntry getLinewrapEntry() {
    return linewrapEntry;
  }
}
