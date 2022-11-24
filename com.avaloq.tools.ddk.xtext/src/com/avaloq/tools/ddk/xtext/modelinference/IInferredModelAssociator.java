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

import org.eclipse.emf.ecore.EObject;


/**
 * When a source model is transformed into an inferred model, elements in the
 * source model may be associated with elements in the inferred model. This
 * class allows one to create these associations.<br>
 * An association is bidirectional and can be many-to-many. A given
 * inferred-model element may be associated with at most one distinguished
 * source-model element referred to as its primary source-model element.<br>
 * The primary source-model element is in some sense the "definitive source"
 * of the transformed, inferred-model element.<br>
 * Based on {@link org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociator} <br>
 *
 * @see com.avaloq.tools.ddk.xtext.modelinference.IInferredModelAssociations
 */
public interface IInferredModelAssociator {

  /**
   * Associate a source-model element with an inferred-model element.
   *
   * @param sourceModelElement
   *          the source-model element to associate with the {@code inferredModelElement}
   * @param inferredModelElement
   *          the inferred-model element to associate with the {@code sourceModelElement}
   */
  void associate(EObject sourceModelElement, EObject inferredModelElement);

  /**
   * Associate a source-model element as the primary element with a inferred-model element.
   *
   * @param sourceModelElement
   *          the source-model element to be associated as the primary source-model element
   *          with the {@code inferredModelElement}
   * @param inferredModelElement
   *          the inferred-model element to associate with the {@code sourceModelElement}
   */
  void associatePrimary(EObject sourceModelElement, EObject inferredModelElement);

}
