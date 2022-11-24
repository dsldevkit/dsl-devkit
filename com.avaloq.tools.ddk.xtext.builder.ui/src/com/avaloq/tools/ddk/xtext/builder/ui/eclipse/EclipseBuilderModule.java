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

package com.avaloq.tools.ddk.xtext.builder.ui.eclipse;

import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.service.AbstractGenericModule;

import com.avaloq.tools.ddk.xtext.builder.ui.builder.ProjectAwareClusteringBuilderState;


/**
 * Builder module supporting DSL development in Eclipse.
 */
public class EclipseBuilderModule extends AbstractGenericModule {

  /**
   * Binds the ProjectAwareClusteringState to avoid getting stuck rebuilding things and improive performance.
   *
   * @return {@link ProjectAwareClusteringBuilderState}
   */
  public Class<? extends IBuilderState> bindIBuilderState() {
    return ProjectAwareClusteringBuilderState.class;
  }

}
