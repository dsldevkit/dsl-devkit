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

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.DerivedStateAwareResource;

import com.avaloq.tools.ddk.xtext.scoping.ImplicitReferencesAdapter;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Manager for associations between source and inferred model elements that allows for additional inference to be done by external providers (i.e. outside of a
 * language core plugin).
 * Also this associator registers implicit inference dependencies.
 * This is needed for those cases when a resource loads others to perform inference.
 * Registering dependencies will ensure that the inference will be re-triggered once one (or many) of those 'other' resources are changed.
 */
@Singleton
public class ExtendableInferredModelAssociator extends InferredModelAssociator {

  private static final Logger LOGGER = Logger.getLogger(ExtendableInferredModelAssociator.class);

  private final ThreadLocal<Deque<Resource>> inferenceStackLocal = ThreadLocal.withInitial(ArrayDeque::new);

  @Inject
  private IModelInferrerFeatureExtensionService additionalInferrers;

  @Override
  public void installDerivedState(final DerivedStateAwareResource resource, final boolean isPreLinkingPhase) {
    Deque<Resource> inferenceStack = getInferenceStack();
    super.installDerivedState(resource, isPreLinkingPhase);
    try {
      inferenceStack.push(resource);
      try {
        additionalInferrers.inferTargetModel(resource.getContents().get(0), createAcceptor(resource), isPreLinkingPhase);
        // CHECKSTYLE:OFF
      } catch (RuntimeException e) {
        // CHECKSTYLE:ON
        LOGGER.error("Failed to install additional derived state for resource " + resource.getURI(), e); //$NON-NLS-1$
      }
    } finally {
      inferenceStack.pop();
    }
  }

  protected Deque<Resource> getInferenceStack() {
    return inferenceStackLocal.get();
  }

  @Override
  public void associate(final EObject sourceModelElement, final EObject inferredModelElement) {
    super.associate(sourceModelElement, inferredModelElement);
    addInferenceDependency(sourceModelElement);
  }

  @Override
  public void associatePrimary(final EObject sourceModelElement, final EObject inferredModelElement) {
    super.associatePrimary(sourceModelElement, inferredModelElement);
    addInferenceDependency(sourceModelElement);
  }

  /**
   * Registers a dependency from the resource the inference was originally trigger for to the currently processed one.
   *
   * @param sourceModelElement
   *          primary model element, must not be {@code null}
   */
  private void addInferenceDependency(final EObject sourceModelElement) {
    Resource originalResource = getInferenceStack().peekFirst();
    if (originalResource != null) {
      final URI originalResourceUri = originalResource.getURI();
      final URI currentResourceUri = sourceModelElement.eResource().getURI();
      if (originalResourceUri != null && currentResourceUri != null && !originalResourceUri.equals(currentResourceUri)) {
        ImplicitReferencesAdapter.findOrCreate(originalResource).addInferenceDependency(currentResourceUri);
      }
    }
  }

}
