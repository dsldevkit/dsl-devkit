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

package com.avaloq.tools.ddk.xtext.expression.ui.internal;

import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.generic.AbstractGenericResourceRuntimeModule;


/**
 * Minimal Guice runtime module for GenModel support.
 */
public class GenModelRuntimeModule extends AbstractGenericResourceRuntimeModule {

  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return GenModelQualifiedNameProvider.class;
  }

  @Override
  protected String getLanguageName() {
    return "org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditorID";
  }

  @Override
  protected String getFileExtensions() {
    return "genmodel";
  }
}
