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
package com.avaloq.tools.ddk.check.runtime.ui.quickfix;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.avaloq.tools.ddk.check.runtime.quickfix.BadLocationException;
import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreXtextDocument;
import com.avaloq.tools.ddk.check.runtime.quickfix.IRegion;
import com.avaloq.tools.ddk.check.runtime.ui.editor.model.IExtendedContentProvider;


/** Adapter for the XtextDocument class. */
public class XtextDocumentAdapter implements ICoreXtextDocument {

  private final IXtextDocument document;

  public XtextDocumentAdapter(final IXtextDocument document) {
    this.document = document;
  }

  @Override
  public String getFullContent() {
    if (document instanceof IExtendedContentProvider) {
      return ((IExtendedContentProvider) document).createFullContentsDocument().get();
    }
    return document.get();
  }

  /** {@inheritDoc} */
  @Override
  public <T> T readOnly(final IUnitOfWork<T, XtextResource> work) {
    return document.readOnly(work);
  }

  /** {@inheritDoc} */
  @Override
  public <T> T modify(final IUnitOfWork<T, XtextResource> work) {
    return document.modify(work);
  }

  /** {@inheritDoc} */
  @Override
  public <T> T getAdapter(final Class<T> adapterType) {
    return document.getAdapter(adapterType);
  }

  /** {@inheritDoc} */
  @Override
  public char getChar(final int offset) throws BadLocationException {
    try {
      return document.getChar(offset);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getLength() {
    return document.getLength();
  }

  /** {@inheritDoc} */
  @Override
  public String get() {
    return document.get();
  }

  /** {@inheritDoc} */
  @Override
  public String get(final int offset, final int length) throws BadLocationException {
    try {
      return document.get(offset, length);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void set(final String text) {
    document.set(text);
  }

  /** {@inheritDoc} */
  @Override
  public void replace(final int offset, final int length, final String text) throws BadLocationException {
    try {
      document.replace(offset, length, text);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getLineLength(final int line) throws BadLocationException {
    try {
      return document.getLineLength(line);
    } catch (org.eclipse.jface.text.BadLocationException e) {

      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getLineOfOffset(final int offset) throws BadLocationException {
    try {
      return document.getLineOfOffset(offset);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getLineOffset(final int line) throws BadLocationException {
    try {
      return document.getLineOffset(line);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public IRegion getLineInformation(final int line) throws BadLocationException {
    try {
      final org.eclipse.jface.text.IRegion lineInformation = document.getLineInformation(line);
      return new IRegion() {
        @Override
        public int getOffset() {
          return lineInformation.getOffset();
        }

        @Override
        public int getLength() {
          return lineInformation.getLength();
        }
      };
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public IRegion getLineInformationOfOffset(final int offset) throws BadLocationException {
    try {
      final org.eclipse.jface.text.IRegion lineInformationOfOffset = document.getLineInformationOfOffset(offset);
      return new IRegion() {
        @Override
        public int getOffset() {
          return lineInformationOfOffset.getOffset();
        }

        @Override
        public int getLength() {
          return lineInformationOfOffset.getLength();
        }
      };
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }

  }

  /** {@inheritDoc} */
  @Override
  public int getNumberOfLines() {
    return document.getNumberOfLines();
  }

  /** {@inheritDoc} */
  @Override
  public int getNumberOfLines(final int offset, final int length) throws BadLocationException {
    try {
      return document.getNumberOfLines(offset, length);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  public int computeNumberOfLines(final String text) {
    return document.computeNumberOfLines(text);
  }

  /** {@inheritDoc} */
  @Override
  public String[] getLegalLineDelimiters() {
    return document.getLegalLineDelimiters();
  }

  /** {@inheritDoc} */
  @Override
  public String getLineDelimiter(final int line) throws BadLocationException {
    try {
      return document.getLineDelimiter(line);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }

  /** {@inheritDoc} */
  @Override
  @Deprecated
  public int search(final int startOffset, final String findString, final boolean forwardSearch, final boolean caseSensitive, final boolean wholeWord) throws BadLocationException {
    try {
      return document.search(startOffset, findString, forwardSearch, caseSensitive, wholeWord);
    } catch (org.eclipse.jface.text.BadLocationException e) {
      throw new BadLocationException(e);
    }
  }
}

