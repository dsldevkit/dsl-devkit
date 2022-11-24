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
package com.avaloq.tools.ddk.checkcfg;

import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parser.antlr.IPartialParsingHelper;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xbase.resource.BatchLinkingService;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;

import com.avaloq.tools.ddk.check.scoping.ExtensionPointAwareScopeProvider;
import com.avaloq.tools.ddk.checkcfg.conversion.CheckCfgValueConverterService;
import com.avaloq.tools.ddk.checkcfg.generator.CheckCfgGenerator;
import com.avaloq.tools.ddk.checkcfg.naming.CheckCfgDeclarativeQualifiedNameProvider;
import com.avaloq.tools.ddk.checkcfg.resource.CheckCfgLocationInFileProvider;
import com.avaloq.tools.ddk.checkcfg.scoping.CheckCfgBatchLinkingService;
import com.avaloq.tools.ddk.checkcfg.scoping.CheckCfgScopeProvider;
import com.avaloq.tools.ddk.xtext.parser.FixedPartialParsingHelper;
import com.google.inject.name.Names;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
public class CheckCfgRuntimeModule extends com.avaloq.tools.ddk.checkcfg.AbstractCheckCfgRuntimeModule {

  /**
   * Custom location in file provider used for revealing and highlighting a model element in the editor.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public Class<? extends ILocationInFileProvider> bindILocationInFileProvider() {
    return CheckCfgLocationInFileProvider.class;
  }

  /**
   * Binds a custom value converter service.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return CheckCfgValueConverterService.class;
  }

  @Override
  public Class<? extends IScopeProvider> bindIScopeProvider() {
    return CheckCfgScopeProvider.class;
  }

  /** Creates a properties file in the .settings folder. {@inheritDoc} */
  @Override
  public Class<? extends IGenerator> bindIGenerator() {
    return CheckCfgGenerator.class;
  }

  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return CheckCfgDeclarativeQualifiedNameProvider.class;
  }

  /** A global scope provider finding check models in the plugin registry. {@inheritDoc} */
  @Override
  public Class<? extends IGlobalScopeProvider> bindIGlobalScopeProvider() {
    return ExtensionPointAwareScopeProvider.class;
  }

  @Override
  public void configureIScopeProviderDelegate(final com.google.inject.Binder binder) {
    binder.bind(IScopeProvider.class).annotatedWith(Names.named(org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(XImportSectionNamespaceScopeProvider.class);
  }

  @Override
  public void configureLinkingIScopeProvider(final com.google.inject.Binder binder) {
    binder.bind(IScopeProvider.class).annotatedWith(org.eclipse.xtext.linking.LinkingScopeProviderBinding.class).to(CheckCfgScopeProvider.class);
  }

  /**
   * Switches off batch linking.
   *
   * @return CheckBatchLinkingService.class
   */
  public Class<? extends BatchLinkingService> bindBatchLinkingService() {
    return CheckCfgBatchLinkingService.class;
  }

  /**
   * Workaround for Bug 416913. To be removed with DSL-596
   *
   * @return {@link FixedPartialParsingHelper}
   */
  @Override
  public Class<? extends IPartialParsingHelper> bindIPartialParserHelper() {
    return FixedPartialParsingHelper.class;
  }
}
