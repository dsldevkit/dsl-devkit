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

package com.avaloq.tools.ddk.check.ui.builder;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.Pair;

import com.avaloq.tools.ddk.check.compiler.CheckGeneratorConfig;
import com.avaloq.tools.ddk.check.compiler.ICheckGeneratorConfigProvider;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;


/**
 * Provides extended generator config with Check-specific properties.
 */
public class CheckEclipseGeneratorConfigProvider implements ICheckGeneratorConfigProvider {

  @Inject
  private IStorage2UriMapper storage2UriMapper;

  @Inject
  private CheckBuilderPreferenceAccess xbaseBuilderPreferenceAccess;

  @Override
  public CheckGeneratorConfig get(final URI uri) {
    CheckGeneratorConfig result = new CheckGeneratorConfig();
    IProject project = null;
    if (uri != null) {
      Pair<IStorage, IProject> pair = Iterables.getFirst(storage2UriMapper.getStorages(uri), null);
      if (pair != null) {
        project = pair.getSecond();
      }
    }
    xbaseBuilderPreferenceAccess.loadBuilderPreferences(result, project);
    return result;
  }
}
