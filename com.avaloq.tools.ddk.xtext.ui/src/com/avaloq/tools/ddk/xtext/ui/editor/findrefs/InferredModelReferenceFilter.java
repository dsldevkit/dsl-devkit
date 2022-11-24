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
package com.avaloq.tools.ddk.xtext.ui.editor.findrefs;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IReferenceDescription;

import com.google.common.base.Predicate;


/**
 * A filter for filtering out inferred model elements.
 */
public class InferredModelReferenceFilter implements Predicate<IReferenceDescription> {

  /**
   * {@inheritDoc}
   *
   * @return true if {@code input} is not an inferred model element.
   */
  @Override
  public boolean apply(final IReferenceDescription input) {
    return !isInferredModelElement(input.getSourceEObjectUri());
  }

  /**
   * Checks a model element's URI to determine if it is an inferred model element.
   *
   * @param elementURI
   *          the URI to check
   * @return true if {@code elementURI} is the URI of an inferred model element; false otherwise.
   */
  protected boolean isInferredModelElement(final URI elementURI) {
    String fragment = elementURI.fragment();
    if (fragment.length() > 1) {
      char firstCharacter = fragment.charAt(1);
      // Normally, URI fragments of non-inferred elements look like: /0/x/y/z/
      // However, there is an exception to this rule, which is WFD that uses a custom FragmentProvider
      // There, EMF feature names are used and fragments look in the following way: /WfdSource/Wfd/x/y/z
      // Hence, the first check is needed to handle this case
      return Character.isDigit(firstCharacter) && firstCharacter != '0';
    }
    return false;
  }

}
