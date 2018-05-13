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

import org.eclipse.xtext.resource.DerivedStateAwareResource;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Manager for associations between source and inferred model elements that allows for additional inference to be done by external providers (i.e. outside of a
 * language core plugin).
 */
@Singleton
public class ExtendableInferredModelAssociator extends InferredModelAssociator {

  @Inject
  private IModelInferrerFeatureExtensionService additionalInferrers;

  @Override
  public void installDerivedState(final DerivedStateAwareResource resource, final boolean isPreLinkingPhase) {
    super.installDerivedState(resource, isPreLinkingPhase);
    additionalInferrers.inferTargetModel(resource.getContents().get(0), createAcceptor(resource), isPreLinkingPhase);
  }

}
