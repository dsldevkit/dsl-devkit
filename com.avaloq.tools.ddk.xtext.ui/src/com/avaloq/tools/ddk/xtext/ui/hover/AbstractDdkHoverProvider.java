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
package com.avaloq.tools.ddk.xtext.ui.hover;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.util.PolymorphicDispatcher.ErrorHandler;


/**
 * Abstract hover provider class. Extends default hover provider.
 */
public abstract class AbstractDdkHoverProvider extends DefaultEObjectHoverProvider {

  private static final String GET_FIRST_LINE_METHOD_NAME = "firstLine"; //$NON-NLS-1$

  private final PolymorphicDispatcher<String> getFirstLineMethodDispatcher = new PolymorphicDispatcher<String>(GET_FIRST_LINE_METHOD_NAME, 1, 1, Collections.singletonList(this), new ErrorHandler<String>() {
    @Override
    public String handle(final Object[] params, final Throwable e) {
      return null;
    }
  });

  /**
   * {@link ENamedElement}s with hover.
   *
   * @return {@link ENamedElement}s with hover
   */
  protected abstract List<ENamedElement> getHoverElements();

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getHoverInfoAsHtml(final EObject o) {
    String hover = super.getHoverInfoAsHtml(o);
    return hover != null ? hover.replaceAll("\\r?\\n", "<br/>") : null; //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean hasHover(final EObject modelElement) {
    return getHoverElements().contains(modelElement.eClass());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getFirstLine(final EObject modelElement) {
    return getFirstLineMethodDispatcher.invoke(modelElement);
  }

  /**
   * This function only exists for testing purposes. It provides access to stylesheets which are used in test class.
   *
   * @return CSS stylesheet for hovering info in HTML form, never {@code null}.
   */
  public String getCSS() {
    return getStyleSheet();
  }
}
