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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.IAcceptor;

import com.avaloq.tools.ddk.xtext.extension.ILanguageExtensionsService;
import com.avaloq.tools.ddk.xtext.extension.ILanguageExtensions;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * Default implementation relying on {@link ILanguageExtensions}.
 */
public class DefaultModelInferrerFeatureExtensionService implements IModelInferrerFeatureExtensionService {

  private final List<IModelInferrerFeatureExtension> inferrers;

  @Inject
  public DefaultModelInferrerFeatureExtensionService(final ILanguageExtensionsService contributionService, final Injector injector) {
    List<IModelInferrerFeatureExtension> unsortedInferrers = Lists.newArrayList();
    for (ILanguageExtensions contribution : contributionService.getExtensions(injector)) {
      IModelInferrerFeatureExtension inferrer = contribution.get(IModelInferrerFeatureExtension.class);
      if (inferrer != null) {
        unsortedInferrers.add(inferrer);
      }
    }
    // We order the inferrers found by their class name to keep a strict ordering
    inferrers = unsortedInferrers.stream().sorted().collect(Collectors.toList());
  }

  @Override
  public void inferTargetModel(final EObject sourceModelElement, final IAcceptor<EObject> acceptor, final boolean isPrelinkingPhase) {
    for (IModelInferrerFeatureExtension inferrer : inferrers) {
      inferrer.inferTargetModel(sourceModelElement, acceptor, isPrelinkingPhase);
    }
  }

}
