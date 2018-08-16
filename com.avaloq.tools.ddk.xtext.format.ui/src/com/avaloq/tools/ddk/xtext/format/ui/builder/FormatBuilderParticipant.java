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
package com.avaloq.tools.ddk.xtext.format.ui.builder;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;

import com.avaloq.tools.ddk.xtext.builder.ConditionalBuilderParticipant;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.generator.FormatGenerator;
import com.google.inject.Inject;


/**
 * A Builder Participant for the Format Language, that takes care of the plugin extension registry.
 */
public class FormatBuilderParticipant extends ConditionalBuilderParticipant {

  @Inject
  private IResourceServiceProvider resourceServiceProvider;

  @Inject
  private FormatGenerator generator;

  @Override
  public void build(final IBuildContext context, final IProgressMonitor monitor) throws CoreException {
    if (!isEnabled(context)) {
      return;
    }
    clearResourceSet(context.getResourceSet());
    super.build(context, monitor);
  }

  @Override
  protected void handleChangedContents(final Delta delta, final IBuildContext context, final EclipseResourceFileSystemAccess2 fsa) throws CoreException {

    if (isAffected(delta, resourceServiceProvider)) {
      Resource resource = context.getResourceSet().getResource(delta.getUri(), true);
      if (shouldGenerate(resource, context) && resource.getContents().get(0) instanceof FormatConfiguration) {
        generator.doGenerate(resource, fsa);
      }
    }
  }
}
