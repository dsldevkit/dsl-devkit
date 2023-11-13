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
package com.avaloq.tools.ddk.xtext.ui.util;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;


/**
 * Utility for Xtext.
 */
public final class XtextUtil {

  /**
   * Provides the Xtext resource from a Xtext Document.
   *
   * @param document
   *          The XtextDocument
   * @return XtexRessource
   */
  public static XtextResource getXtextRessource(final IXtextDocument document) {
    return document.readOnly(new IUnitOfWork<XtextResource, XtextResource>() {
      @Override
      public XtextResource exec(final XtextResource state) {
        return state;
      }
    });
  }

  private XtextUtil() {
  }
}
