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
package com.avaloq.tools.ddk.xtext.ui.editor.model;

import java.io.InputStream;
import java.nio.charset.CharacterCodingException;

import org.apache.log4j.Logger;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.DocumentTokenSource;
import org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer;

import com.avaloq.tools.ddk.xtext.resource.IXtextGMFResource;
import com.avaloq.tools.ddk.check.runtime.ui.editor.model.IExtendedContentProvider;
import com.google.inject.Inject;


/**
 * An Xtext document for editing combined Xtext GMF resources.
 */
public class XtextGMFDocument extends ResponsiveXtextDocument implements IExtendedContentProvider {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(XtextGMFDocument.class);

  private IXtextGMFResource myResource; // shadows private resource of supertype

  @Inject
  public XtextGMFDocument(final DocumentTokenSource tokenSource, final ITextEditComposer composer) {
    super(tokenSource, composer);
  }

  @Override
  public void setInput(final XtextResource resource) {
    super.setInput(resource);
    if (resource instanceof IXtextGMFResource) {
      this.myResource = (IXtextGMFResource) resource;
    }
  }

  /**
   * Creates a document containing the full Xtext and diagram contents.
   *
   * @return a new document with Xtext and diagram contents.
   */
  @Override
  public IDocument createFullContentsDocument() {
    Document result = new Document();
    result.set(this.get() + myResource.getDiagramText(), this.getModificationStamp());
    return result;
  }

  public InputStream getXtextInput() {
    return myResource.getXtextInput();
  }

  /**
   * Update this document with the diagram from the specified document.
   *
   * @param fromDocument
   *          the document to update from
   */
  public void updateResourceDiagram(final XtextGMFDocument fromDocument) {
    try {
      myResource.updateDiagram(fromDocument.myResource);
    } catch (CharacterCodingException e) {
      LOGGER.error("unexpected character encoding error"); //$NON-NLS-1$
      myResource = fromDocument.myResource; // hack, but works in this rare (if ever) case
    }
  }

  /**
   * Set the new content and don't tell anyone.
   *
   * @param text
   *          New content
   */
  public void setQuietly(final String text) {
    getStore().set(text);
    getTracker().set(text);
  }

  @Override
  public void disposeInput() {
    super.disposeInput();
    myResource = null; // NOPMD
  }

}
