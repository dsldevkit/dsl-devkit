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
package com.avaloq.tools.ddk.xtext.ui.editor.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;


/**
 * An Xtext document provider for editing combined Xtext GMF resources.
 */
public class XtextGMFDocumentProvider extends ResponsiveXtextDocumentProvider {

  private static final int READ_BUFFER_SIZE = 2048;

  @Override
  protected void doSaveDocument(final IProgressMonitor monitor, final Object element, final IDocument document, final boolean overwrite) throws CoreException {
    if (document instanceof XtextGMFDocument) {
      XtextGMFDocument documentImpl = (XtextGMFDocument) document;
      super.doSaveDocument(monitor, element, documentImpl.createFullContentsDocument(), overwrite);
    } else {
      super.doSaveDocument(monitor, element, document, overwrite);
    }
  }

  @Override
  protected boolean setDocumentContent(final IDocument document, final IEditorInput editorInput, final String encoding) throws CoreException {
    boolean result = super.setDocumentContent(document, editorInput, encoding);
    if (result && document instanceof XtextGMFDocument) {
      XtextGMFDocument castedDocument = (XtextGMFDocument) document;
      // When the file contents are replaced, the superclass method FileDocumentProvider.handleElementContentChanged
      // creates a *new* document as part of the update. Along the way, a new Wfd resource is loaded into this new document.
      // But this new document is thrown away, along with the new resource containing the replaced diagram content!
      // As a result the elementInfo mapping does not get updated, and so it still maps to the old document.
      // This is not a problem for the normal Xtext editor because it will get updated via the builder and/or
      // dirtyStateManager via a re-parse.
      // We, however, have to update the currently mapped document to use the newly loaded resource. Otherwise we will
      // loose the new diagram part.
      if (getElementInfo(editorInput) != null) {
        IDocument mappedDocument = getElementInfo(editorInput).fDocument;
        if (mappedDocument != document && mappedDocument instanceof XtextGMFDocument) { // NOPMD
          ((XtextGMFDocument) mappedDocument).updateResourceDiagram(castedDocument);
        }
      }
      // Set the document content so that it only contains the Xtext part.
      // This has to be done quietly, so the initialization of the editor is not getting interrupted
      setDocumentContentQuietly(castedDocument, castedDocument.getXtextInput(), encoding);
    }
    return result;
  }

  /**
   * Sets the document content quietly (without updating time stamp and canceling readers).
   * 
   * @param document
   *          the document for which the content will be replaced, must not be {@code null}
   * @param contentStream
   *          the content stream containing the new text, must not be {@code null}
   * @param encoding
   *          the encoding of the stream, if {@code null} a default encoding will be used
   * @throws CoreException
   *           the core exception if IO operation fails
   */
  protected void setDocumentContentQuietly(final XtextGMFDocument document, final InputStream contentStream, final String encoding) throws CoreException {
    Reader in = null;

    try {
      String actualEncoding = encoding;
      if (actualEncoding == null) {
        actualEncoding = getDefaultEncoding();
      }

      in = new BufferedReader(new InputStreamReader(contentStream, actualEncoding), DEFAULT_FILE_SIZE);
      StringBuffer buffer = new StringBuffer(DEFAULT_FILE_SIZE);
      char[] readBuffer = new char[READ_BUFFER_SIZE];
      int n = in.read(readBuffer);
      while (n > 0) {
        buffer.append(readBuffer, 0, n);
        n = in.read(readBuffer);
      }

      document.setQuietly(buffer.toString());

    } catch (IOException e) {
      String message = (e.getMessage() != null ? e.getMessage() : ""); //$NON-NLS-1$
      IStatus s = new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, IStatus.OK, message, e);
      throw new CoreException(s);
    } finally {
      try {
        if (in != null) {
          in.close();
        } else {
          contentStream.close();
        }
      } catch (IOException e) {
        // Nothing
      }
    }
  }

}
