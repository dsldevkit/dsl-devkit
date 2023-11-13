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
package com.avaloq.tools.ddk.check.runtime.ui.editor.model;

import org.eclipse.jface.text.IDocument;


/**
 * Implemented by documents that contain extra content that is not directly available to text editors.
 */
public interface IExtendedContentProvider {
  /**
   * Creates a document containing the full contents of the source.
   *
   * @return a new document with the full contents.
   */
  IDocument createFullContentsDocument();
}
