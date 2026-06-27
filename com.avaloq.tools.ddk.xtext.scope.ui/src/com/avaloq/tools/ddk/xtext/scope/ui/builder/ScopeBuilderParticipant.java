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
package com.avaloq.tools.ddk.xtext.scope.ui.builder;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.avaloq.tools.ddk.xtext.builder.ConditionalBuilderParticipant;


/**
 * A builder participant for the Scope DSL that honors the per-language preference controlling whether the generated artifacts are regenerated on a workspace
 * build.
 */
public class ScopeBuilderParticipant extends ConditionalBuilderParticipant {

  @Override
  public void build(final IBuildContext context, final IProgressMonitor monitor) throws CoreException {
    if (!isBuilderParticipantEnabled(context)) {
      return;
    }
    super.build(context, monitor);
  }

  @Override
  protected String getBuilderParticipantDisabledPreferenceKey() {
    return ScopeBuilderPreferenceAccess.PREF_DISABLE_BUILDER_PARTICIPANT;
  }

}
