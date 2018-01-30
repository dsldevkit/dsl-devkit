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

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IAcceptor;

import com.avaloq.tools.ddk.xtext.contribution.ILanguageContribution;
import com.avaloq.tools.ddk.xtext.contribution.ILanguageContributionService;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Named;


/**
 * Default implementation relying on {@link ILanguageContribution}.
 */
@Singleton
public class DefaultAdditionalExportService implements IAdditionalExportService {

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String languageName;

  @Inject
  private ILanguageContributionService contributionService;

  @Inject
  private Injector injector;

  private List<IAdditionalExport> exportContributions;

  @Override
  public QualifiedName qualifiedName(final EObject object) {
    return returnFirst(c -> c.qualifiedName(object));
  }

  @Override
  public String computeFingerprint(final EObject object) {
    return returnFirst(c -> c.computeFingerprint(object));
  }

  @Override
  public CharSequence getFragmentSegment(final EObject object) {
    return returnFirst(c -> c.getFragmentSegment(object));
  }

  @Override
  public Set<EClass> getExportedEClasses(final Resource resource) {
    ensureInitilaized();
    return exportContributions.stream().flatMap(c -> c.getExportedEClasses(resource).stream()).collect(Collectors.toSet());
  }

  @Override
  public boolean doCreateEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor) {
    boolean traverse = false;
    for (IAdditionalExport contribution : exportContributions) {
      traverse |= contribution.doCreateEObjectDescriptions(object, acceptor);
    }
    return traverse;
  }

  private <T> T returnFirst(final Function<IAdditionalExport, T> mapper) {
    ensureInitilaized();
    return exportContributions.stream().map(mapper).filter(Objects::nonNull).findFirst().orElse(null);
  }

  private void ensureInitilaized() {
    if (exportContributions == null) {
      exportContributions = Lists.newArrayList();
      for (ILanguageContribution contribution : contributionService.getContributions(languageName)) {
        IAdditionalExport export = contribution.getAdditionalExport();
        if (export != null) {
          injector.injectMembers(export);
          exportContributions.add(export);
        }
      }
    }
  }

}
