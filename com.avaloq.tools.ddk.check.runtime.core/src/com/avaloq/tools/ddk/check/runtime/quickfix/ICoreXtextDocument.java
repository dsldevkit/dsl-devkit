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
package com.avaloq.tools.ddk.check.runtime.quickfix;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.concurrent.IReadAccess;
import org.eclipse.xtext.util.concurrent.IWriteAccess;


/**
 * Copy of org.eclipse.xtext.ui.editor.model.IXtextDocument with limitations. Among others, the following super-
 * classes are not extended:
 * <ul>
 * <li>{@link org.eclipse.jface.text.IDocument}
 * <li>{@link org.eclipse.jface.text.IDocumentExtension3}
 * </ul>
 * <p>
 * This class is UI-independent.
 */
// CHECKSTYLE:OFF
public interface ICoreXtextDocument extends IReadAccess<XtextResource>, IWriteAccess<XtextResource> {

  public <T> T getAdapter(Class<T> adapterType);

  /**
   * The identifier of the default position category.
   */
  final static String DEFAULT_CATEGORY = "__dflt_position_category"; //$NON-NLS-1$

  /**
   * The identifier of the default partition content type.
   */
  final static String DEFAULT_CONTENT_TYPE = "__dftl_partition_content_type"; //$NON-NLS-1$

  /* --------------- text access and manipulation --------------------------- */

  /**
   * Returns the full content of the document, including any hidden sections.
   *
   * @return the full content
   */
  String getFullContent();

  /**
   * Returns the character at the given document offset in this document.
   *
   * @param offset
   *          a document offset
   * @return the character at the offset
   * @exception BadLocationException
   *              if the offset is invalid in this document
   */
  char getChar(int offset) throws BadLocationException;

  /**
   * Returns the number of characters in this document.
   *
   * @return the number of characters in this document
   */
  int getLength();

  /**
   * Returns this document's complete text.
   *
   * @return the document's complete text
   */
  String get();

  /**
   * Returns this document's text for the specified range.
   *
   * @param offset
   *          the document offset
   * @param length
   *          the length of the specified range
   * @return the document's text for the specified range
   * @exception BadLocationException
   *              if the range is invalid in this document
   */
  String get(int offset, int length) throws BadLocationException;

  /**
   * Replaces the content of the document with the given text.
   * Sends a <code>DocumentEvent</code> to all registered <code>IDocumentListener</code>.
   * This method is a convenience method for <code>replace(0, getLength(), text)</code>.
   *
   * @param text
   *          the new content of the document
   * @see DocumentEvent
   * @see IDocumentListener
   */
  void set(String text);

  /**
   * Substitutes the given text for the specified document range.
   * Sends a <code>DocumentEvent</code> to all registered <code>IDocumentListener</code>.
   *
   * @param offset
   *          the document offset
   * @param length
   *          the length of the specified range
   * @param text
   *          the substitution text
   * @exception BadLocationException
   *              if the offset is invalid in this document
   * @see DocumentEvent
   * @see IDocumentListener
   */
  void replace(int offset, int length, String text) throws BadLocationException;

  /* ---------------------- line information -------------------------------- */

  /**
   * Returns the length of the given line including the line's delimiter.
   *
   * @param line
   *          the line of interest
   * @return the length of the line
   * @exception BadLocationException
   *              if the line number is invalid in this document
   */
  int getLineLength(int line) throws BadLocationException;

  /**
   * Returns the number of the line at which the character of the specified position is located.
   * The first line has the line number 0. A new line starts directly after a line
   * delimiter. <code>(offset == document length)</code> is a valid argument although there is no
   * corresponding character.
   *
   * @param offset
   *          the document offset
   * @return the number of the line
   * @exception BadLocationException
   *              if the offset is invalid in this document
   */
  int getLineOfOffset(int offset) throws BadLocationException;

  /**
   * Determines the offset of the first character of the given line.
   *
   * @param line
   *          the line of interest
   * @return the document offset
   * @exception BadLocationException
   *              if the line number is invalid in this document
   */
  int getLineOffset(int line) throws BadLocationException;

  /**
   * Returns a description of the specified line. The line is described by its
   * offset and its length excluding the line's delimiter.
   *
   * @param line
   *          the line of interest
   * @return a line description
   * @exception BadLocationException
   *              if the line number is invalid in this document
   */
  IRegion getLineInformation(int line) throws BadLocationException;

  /**
   * Returns a description of the line at the given offset.
   * The description contains the offset and the length of the line
   * excluding the line's delimiter.
   *
   * @param offset
   *          the offset whose line should be described
   * @return a region describing the line
   * @exception BadLocationException
   *              if offset is invalid in this document
   */
  IRegion getLineInformationOfOffset(int offset) throws BadLocationException;

  /**
   * Returns the number of lines in this document
   *
   * @return the number of lines in this document
   */
  int getNumberOfLines();

  /**
   * Returns the number of lines which are occupied by a given text range.
   *
   * @param offset
   *          the offset of the specified text range
   * @param length
   *          the length of the specified text range
   * @return the number of lines occupied by the specified range
   * @exception BadLocationException
   *              if specified range is invalid in this tracker
   */
  int getNumberOfLines(int offset, int length) throws BadLocationException;

  /**
   * Computes the number of lines in the given text. For a given
   * implementer of this interface this method returns the same
   * result as <code>set(text); getNumberOfLines()</code>.
   *
   * @param text
   *          the text whose number of lines should be computed
   * @return the number of lines in the given text
   */
  int computeNumberOfLines(String text);

  /* ------------------ line delimiter conversion --------------------------- */

  /**
   * Returns the document's legal line delimiters.
   *
   * @return the document's legal line delimiters
   */
  String[] getLegalLineDelimiters();

  /**
   * Returns the line delimiter of that line or <code>null</code> if the
   * line is not closed with a line delimiter.
   *
   * @param line
   *          the line of interest
   * @return the line's delimiter or <code>null</code> if line does not have a delimiter
   * @exception BadLocationException
   *              if the line number is invalid in this document
   */
  String getLineDelimiter(int line) throws BadLocationException;

  /* ---------------------------- search ------------------------------------ */

  /**
   * Returns the offset of a given search string in the document based on a set of search criteria.
   *
   * @param startOffset
   *          document offset at which search starts
   * @param findString
   *          the string to find
   * @param forwardSearch
   *          the search direction
   * @param caseSensitive
   *          indicates whether lower and upper case should be distinguished
   * @param wholeWord
   *          indicates whether the findString should be limited by white spaces as
   *          defined by Character.isWhiteSpace
   * @return the offset of the first occurrence of findString based on the parameters or -1 if no match is found
   * @exception BadLocationException
   *              if startOffset is an invalid document offset
   * @deprecated as of 3.0 search is provided by {@link FindReplaceDocumentAdapter}
   */
  @Deprecated
  int search(int startOffset, String findString, boolean forwardSearch, boolean caseSensitive, boolean wholeWord) throws BadLocationException;

}
