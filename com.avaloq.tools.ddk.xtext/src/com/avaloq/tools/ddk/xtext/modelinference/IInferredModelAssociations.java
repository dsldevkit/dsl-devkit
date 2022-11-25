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
package com.avaloq.tools.ddk.xtext.modelinference;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;


/**
 * When a source model is transformed into an inferred model, elements in the
 * source model may be associated with elements in the inferred model. This
 * class allows one to get inferred-model elements associated with a given
 * source-model element and vice versa.<br>
 * Based on {@link org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations} <br>
 *
 * @see com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociator
 */
public interface IInferredModelAssociations {

  /**
   * Get the set of inferred-model elements associated with a source-model element.
   *
   * @see com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociator#associate()
   * @param sourceModelElement
   *          the source-model element for which to get the associated inferred-model elements
   * @return a set containing the inferred-model elements associated with {@code sourceModelElement}
   */
  Set<EObject> getInferredModelElements(EObject sourceModelElement);

  /**
   * Get the set of source-model elements associated with an inferred-model element.
   *
   * @see com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociator#associate()
   * @param inferredModelElement
   *          the inferred-model element for which to get the source-model elements
   * @return a set containing the source-model elements associated with the {@code inferredModelElement}
   */
  Set<EObject> getSourceModelElements(EObject inferredModelElement);

  /**
   * Get the primary source-model element associated with an inferred-model element.
   *
   * @see com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociator#associatePrimary()
   * @param inferredModelElement
   *          the inferred-model element for which to get the associated primary source-model element
   * @return the primary source-model element associated with the {@code inferredModelElement}
   */
  EObject getPrimarySourceModelElement(EObject inferredModelElement);

}
