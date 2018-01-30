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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.util.IAcceptor;

import com.avaloq.tools.ddk.xtext.contribution.ILanguageContribution;
import com.avaloq.tools.ddk.xtext.contribution.ILanguageContributionService;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;


/**
 * Default implementation relying on {@link ILanguageContribution}.
 */
public class DefaultAdditionalInferrersService implements IAdditionalInferrersService {

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String languageName;

  @Inject
  private ILanguageContributionService contributionService;

  @Inject
  private Injector injector;

  private List<IAdditionalModelInferrer> inferrers;

  @Override
  public void inferTargetModel(final EObject sourceModelElement, final IAcceptor<EObject> acceptor, final boolean isPrelinkingPhase) {
    if (inferrers == null) {
      List<IAdditionalModelInferrer> unsortedInferrers = Lists.newArrayList();
      for (ILanguageContribution contribution : contributionService.getContributions(languageName)) {
        IAdditionalModelInferrer inferrer = contribution.getAdditionalModelInferrer();
        if (inferrer != null) {
          injector.injectMembers(inferrer);
          unsortedInferrers.add(inferrer);
        }
      }
      // We order the inferrers found by their class name to keep a strict ordering
      inferrers = unsortedInferrers.stream().sorted().collect(Collectors.toList());
    }
    for (IAdditionalModelInferrer inferrer : inferrers) {
      inferrer.inferTargetModel(sourceModelElement, acceptor, isPrelinkingPhase);
    }
  }

}
