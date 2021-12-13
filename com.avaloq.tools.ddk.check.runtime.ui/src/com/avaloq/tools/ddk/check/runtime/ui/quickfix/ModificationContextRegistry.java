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
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.editor.model.XtextDocument;

import com.avaloq.tools.ddk.check.runtime.quickfix.ICoreXtextDocument;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;


/**
 * Default implementation of {@link IModificationContextRegistry}.
 */
@Singleton
public class ModificationContextRegistry implements IModificationContextRegistry {
  @Inject
  private Provider<XtextResourceSet> resourceSetProvider;
  @Inject
  private Provider<XtextDocument> documentProvider;

  private final ThreadLocal<XtextResourceSet> resourceSet = new ThreadLocal<XtextResourceSet>() {
    @Override
    protected XtextResourceSet initialValue() {
      return resourceSetProvider.get();
    }
  };

  private final ThreadLocal<Map<URI, ICoreXtextDocument>> xtextDocuments = new ThreadLocal<Map<URI, ICoreXtextDocument>>() {
    @Override
    protected Map<URI, ICoreXtextDocument> initialValue() {
      return Maps.newHashMap();
    }
  };

  /** {@inheritDoc} */
  public Map<URI, ICoreXtextDocument> getAllDocuments() {
    return xtextDocuments.get();
  }

  /** {@inheritDoc} */
  public void clear() {
    xtextDocuments.remove();
    resourceSet.remove();
  }

  /** {@inheritDoc} */
  public ICoreXtextDocument getXtextDocument(final URI uri) {
    final URI trimmedUri = uri.trimFragment();
    ICoreXtextDocument xtextDocument = xtextDocuments.get().get(trimmedUri);
    if (xtextDocument == null) {
      XtextResource xtextResource = getXtextResource(trimmedUri);
      xtextDocument = createDocument(xtextResource);
      xtextDocuments.get().put(trimmedUri, xtextDocument);
    }
    return xtextDocument;
  }

  /**
   * Creates a new document with the contents of the given {@link XtextResource}.
   * 
   * @param resource
   *          the resource to be used as input to the document
   * @return the created document
   */
  private ICoreXtextDocument createDocument(final XtextResource resource) {
    XtextDocument document = documentProvider.get();
    if (resource.getParseResult() != null && resource.getParseResult().getRootNode() != null) {
      document.set(resource.getParseResult().getRootNode().getText());
    }
    document.setInput(resource);
    return new XtextDocumentAdapter(document);
  }

  /**
   * Fetches the Resource matching the given URI.
   * 
   * @param uri
   *          the URI to be loaded
   * @return the loaded resource
   */
  private XtextResource getXtextResource(final URI uri) {
    return (XtextResource) resourceSet.get().getResource(uri, true);
  }
}

