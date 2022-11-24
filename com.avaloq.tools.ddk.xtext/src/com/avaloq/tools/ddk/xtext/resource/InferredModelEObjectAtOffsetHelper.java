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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;

import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations;
import com.google.inject.Inject;


/**
 * A helper class to locate the source EObject at a given offset in a source when
 * the EObject at that location is an inferred-model element.
 */
public class InferredModelEObjectAtOffsetHelper extends EObjectAtOffsetHelper {

  @Inject
  private IInferredModelAssociations modelAssociations;

  @Override
  public EObject resolveCrossReferencedElementAt(final XtextResource resource, final int offset) {
    return convertToSource(super.resolveCrossReferencedElementAt(resource, offset));
  }

  @Override
  public EObject resolveElementAt(final XtextResource resource, final int offset) {
    return convertToSource(super.resolveElementAt(resource, offset));
  }

  /**
   * Convert an inferred-model element into its source-model element.
   *
   * @param element
   *          the model element to convert.
   * @return if {@code element} is an inferred-model element, then returns its corresponding
   *         primary source-model element; else returns the {@code element} itself.
   */
  protected EObject convertToSource(final EObject element) {
    if (element == null) {
      return null;
    }
    EObject sourceElements = modelAssociations.getPrimarySourceModelElement(element);
    return sourceElements == null ? element : sourceElements;
  }

}
