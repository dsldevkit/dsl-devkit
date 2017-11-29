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
package com.avaloq.tools.ddk.xtext.test;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResource;


/**
 * Lazy loading enabled data container for all source relevant information.
 */
public class XtextTestSource extends EmfTestSource {

  /**
   * Creates a new instance of {@link XtextTestSource}.
   * 
   * @param sourceFileName
   *          the source file name, must not be {@code null}
   * @param content
   *          the source content, must not be {@code null}
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   */
  public XtextTestSource(final String sourceFileName, final String content, final ResourceSet resourceSet) {
    super(sourceFileName, content, resourceSet);
  }

  /**
   * Returns the {@link XtextResource}.
   *
   * @return the {@link XtextResource}, or {@code null}
   */
  public XtextResource getXtextResource() {
    return (XtextResource) super.getResource();
  }

  /**
   * Internal method to return the current {@link #resource}.
   *
   * @return current Xtext resource, or {@code null}
   */
  protected XtextResource basicGetXtextResource() {
    return (XtextResource) super.basicGetResource();
  }
}
