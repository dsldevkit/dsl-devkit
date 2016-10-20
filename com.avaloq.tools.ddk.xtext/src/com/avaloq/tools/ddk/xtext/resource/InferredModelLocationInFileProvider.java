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
package com.avaloq.tools.ddk.xtext.resource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.resource.DefaultLocationInFileProvider;
import org.eclipse.xtext.util.ITextRegion;

import com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations;
import com.google.inject.Inject;


/**
 * Provider for the text location of a source model element given an inferred model element.
 */
public class InferredModelLocationInFileProvider extends DefaultLocationInFileProvider {

  @Inject(optional = true)
  private IInferredModelAssociations modelAssociations;

  @Override
  public ITextRegion getFullTextRegion(final EObject element) {
    return super.getFullTextRegion(convertToSource(element));
  }

  @Override
  public ITextRegion getFullTextRegion(final EObject owner, final EStructuralFeature feature, final int indexInList) {
    return super.getFullTextRegion(convertToSource(owner), feature, indexInList);
  }

  @Override
  public ITextRegion getSignificantTextRegion(final EObject element) {
    return super.getSignificantTextRegion(convertToSource(element));
  }

  @Override
  public ITextRegion getSignificantTextRegion(final EObject owner, final EStructuralFeature feature, final int indexInList) {
    return super.getSignificantTextRegion(convertToSource(owner), feature, indexInList);
  }

  /**
   * Convert an inferred-model element into its source model element.
   *
   * @param element
   *          the model element to convert.
   * @return if {@code element} is an inferred-model element, then return its corresponding
   *         primary source-model element; else return the {@code element} itself.
   */
  protected EObject convertToSource(final EObject element) {
    if (element == null) {
      return null;
    }
    if (modelAssociations != null) {
      EObject sourceElements = modelAssociations.getPrimarySourceModelElement(element);
      return sourceElements == null ? element : sourceElements;
    } else {
      return element;
    }
  }

}
