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

import java.util.Map;

import org.eclipse.emf.common.util.URI;

import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreXtextDocument;
import com.google.inject.ImplementedBy;


/**
 * Keeps track of documents that were opened and modified by a given {@link CoreIssueModificationContext}.
 */
@ImplementedBy(ModificationContextRegistry.class)
public interface IModificationContextRegistry {
  /**
   * Fetches a {@link ICoreXtextDocument} for the given URI.
   * 
   * @param uri
   *          the URI of the source to load
   * @return an document containing the source identified by the given URI
   */
  ICoreXtextDocument getXtextDocument(URI uri);

  /**
   * Returns all documents that were opened during the application of quick fixes.
   * 
   * @return a map from URI to documents
   */
  Map<URI, ICoreXtextDocument> getAllDocuments();

  /**
   * Clears the resources and documents that were loaded by the current thread.
   */
  void clear();
}

