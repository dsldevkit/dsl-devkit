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
package com.avaloq.tools.ddk.xtext.test.jvmmodel;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import com.avaloq.tools.ddk.xtext.test.modelinference.AbstractModelInferrerTest;


/**
 * Base class for JVM model inference test implementations.
 */
public abstract class AbstractJvmModelInferrerTest extends AbstractModelInferrerTest {

  /** {@inheritDoc} */
  @Override
  protected Set<EObject> getInferredElements(final EObject sourceElement) {
    return InferredJvmModelUtil.getInferredElements(sourceElement);
  }

  /**
   * Returns an inferred element of specified name and type for a given source element.
   * 
   * @param sourceElement
   *          a source {@link EObject}, must not be {@code null}
   * @param name
   *          the name of the element, must not be {@code null}
   * @param clazz
   *          the type of the element, must not be {@code null}
   * @return an inferred element, or {@code null} if nothing has been found
   */
  @Override
  protected EObject getInferredElement(final EObject sourceElement, final String name, final Class<? extends EObject> clazz) {
    return InferredJvmModelUtil.getInferredElement(sourceElement, name, clazz);
  }
}

