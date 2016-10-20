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
package com.avaloq.tools.ddk.xtext.modelinference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.ImplementedBy;


/**
 * A model inferrer maps a given source-model element to one or model target-model elements
 * that "reflect" the semantics of the source-model element. <br>
 * Implementors have to traverse the given source-model element in {@link #inferTargetModel(EObject)} and
 * decide which target-model elements to create according to the state of the source model. <br>
 * <br>
 * Based on {@link org.eclipse.xtext.xbase.jvmmodel.IJvmModelInferrer} by Jan Koehnlein
 */
@ImplementedBy(IModelInferrer.NullImpl.class)
public interface IModelInferrer {

  /**
   * Called for the root element of a resource just once to infer the corresponding target model.
   *
   * @param sourceModelElement
   *          - the root element from the parse result
   * @param acceptor
   *          - an acceptor awaiting the inferred target elements
   * @param isPrelinkingPhase
   *          - whether the call is done in a prelinking phase. During this phase clients may not do
   *          linking which relies on indexed information, because the index might not be fully computed yet.
   */
  void inferTargetModel(EObject sourceModelElement, IAcceptor<EObject> acceptor, boolean isPrelinkingPhase);

  /**
   * A null-implementation. Returns an empty list.
   */
  class NullImpl implements IModelInferrer {

    /** {@inheritDoc} */
    @Override
    public void inferTargetModel(final EObject sourceModelElement, final IAcceptor<EObject> acceptor, final boolean isPrelinkingPhase) {}

  }

}
