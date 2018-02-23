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
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IAcceptor;

import com.avaloq.tools.ddk.xtext.extension.ILanguageExtensionsService;
import com.avaloq.tools.ddk.xtext.extension.ILanguageExtensions;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;


/**
 * Default implementation relying on {@link ILanguageExtensions}.
 */
@Singleton
public class DefaultExportFeatureExtensionService implements IExportFeatureExtensionService {

  private final List<IExportFeatureExtension> exportFeatureExtensions = Lists.newArrayList();

  @Inject
  public DefaultExportFeatureExtensionService(final ILanguageExtensionsService contributionService, final Injector injector) {
    for (ILanguageExtensions contribution : contributionService.getExtensions(injector)) {
      IExportFeatureExtension export = contribution.get(IExportFeatureExtension.class);
      if (export != null) {
        exportFeatureExtensions.add(export);
      }
    }
  }

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
    return exportFeatureExtensions.stream().flatMap(c -> c.getExportedEClasses(resource).stream()).collect(Collectors.toSet());
  }

  @Override
  public boolean createEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor) {
    boolean traverse = false;
    for (IExportFeatureExtension contribution : exportFeatureExtensions) {
      traverse |= contribution.createEObjectDescriptions(object, acceptor);
    }
    return traverse;
  }

  private <T> T returnFirst(final Function<IExportFeatureExtension, T> mapper) {
    return exportFeatureExtensions.stream().map(mapper).filter(Objects::nonNull).findFirst().orElse(null);
  }

}
