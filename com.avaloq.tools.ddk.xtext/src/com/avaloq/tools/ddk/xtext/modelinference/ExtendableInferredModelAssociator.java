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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Manager for associations between source and inferred model elements that allows for additional inference to be done by external providers (i.e. outside of a
 * language core plugin).
 */
@Singleton
public class ExtendableInferredModelAssociator extends InferredModelAssociator {

  private static final Logger LOGGER = LogManager.getLogger(ExtendableInferredModelAssociator.class);

  @Inject
  private IModelInferrerFeatureExtensionService additionalInferrers;

  @Override
  protected void inferTargetModel(final EObject eObject, final IAcceptor<EObject> acceptor, final boolean isPreLinkingPhase) {
    super.inferTargetModel(eObject, acceptor, isPreLinkingPhase);
    try {
      additionalInferrers.inferTargetModel(eObject, acceptor, isPreLinkingPhase);
      // CHECKSTYLE:OFF
    } catch (RuntimeException e) {
      // CHECKSTYLE:ON
      LOGGER.error("Failed to install additional derived state for resource " + eObject.eResource().getURI(), e); //$NON-NLS-1$
    }
  }

}
