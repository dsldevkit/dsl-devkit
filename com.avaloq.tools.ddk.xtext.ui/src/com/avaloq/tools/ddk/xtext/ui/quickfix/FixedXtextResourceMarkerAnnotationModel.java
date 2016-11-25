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
package com.avaloq.tools.ddk.xtext.ui.quickfix;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.editor.quickfix.XtextResourceMarkerAnnotationModel;
import org.eclipse.xtext.ui.util.IssueUtil;


/**
 * Fixes the connected state handling of the {@link XtextResourceMarkerAnnotationModel}.
 */
public class FixedXtextResourceMarkerAnnotationModel extends XtextResourceMarkerAnnotationModel {

  private boolean connected;

  /**
   * Creates a new instance of {@link FixedXtextResourceMarkerAnnotationModel}.
   *
   * @param file
   *          the {@link IFile}, must not be {@code null}
   * @param issueResolutionProvider
   *          the {@link IssueResolutionProvider}, must not be {@code null}
   * @param markerUtil
   *          the {@link IssueUtil}, must not be {@code null}
   */
  public FixedXtextResourceMarkerAnnotationModel(final IFile file, final IssueResolutionProvider issueResolutionProvider, final IssueUtil markerUtil) {
    super(file, issueResolutionProvider, markerUtil);
  }

  @Override
  protected void connected() {
    super.connected();
    connected = true;
  }

  @Override
  protected void disconnected() {
    super.disconnected();
    connected = false;
  }

  @Override
  public void updateMarkers(final IDocument document) throws CoreException {
    if (!this.connected) {
      return;
    }
    super.updateMarkers(document);
  }
}

