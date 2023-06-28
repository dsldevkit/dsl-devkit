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
package com.avaloq.tools.ddk.xtext.scope;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.ILocationInFileProvider;

import com.avaloq.tools.ddk.xtext.scope.conversion.ScopeValueConverterService;
import com.avaloq.tools.ddk.xtext.scope.linking.ScopeLinkingService;
import com.avaloq.tools.ddk.xtext.scope.naming.ScopeQualifiedNameConverter;
import com.avaloq.tools.ddk.xtext.scope.resource.ScopeLocationInFileProvider;
import com.avaloq.tools.ddk.xtext.scope.resource.ScopeResourceDescriptionStrategy;


/**
 * Use this class to register components to be used within the IDE.
 */
public class ScopeRuntimeModule extends AbstractScopeRuntimeModule {

  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return ScopeValueConverterService.class;
  }

  @Override
  public Class<? extends ILinkingService> bindILinkingService() {
    return ScopeLinkingService.class;
  }

  /**
   * Binds a class loader required by the resource manager.
   *
   * @return bound class loader instance
   */
  @Override
  public ClassLoader bindClassLoaderToInstance() {
    return getClass().getClassLoader();
  }

  /**
   * binds custom qualified name converter.
   *
   * @return implementation
   */
  public Class<? extends IQualifiedNameConverter> bindIQualifiedNameConverter() {
    return ScopeQualifiedNameConverter.class;
  }

  @Override
  public Class<? extends ILocationInFileProvider> bindILocationInFileProvider() {
    return ScopeLocationInFileProvider.class;
  }

  /**
   * Binds {@link IDefaultResourceDescriptionStrategy}.
   *
   * @return {@link ScopeResourceDescriptionStrategy}
   */
  public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
    return ScopeResourceDescriptionStrategy.class;
  }

  @Override
  public Class<? extends org.eclipse.xtext.formatting.IFormatter> bindIFormatter() {
    return com.avaloq.tools.ddk.xtext.scope.formatting.ScopeFormatter.class;
  }
}
