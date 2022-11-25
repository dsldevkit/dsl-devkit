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
package com.avaloq.tools.ddk.check;

import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.linking.ILinkingService;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.parser.antlr.IPartialParsingHelper;
import org.eclipse.xtext.preferences.IPreferenceValuesProvider;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.persistence.IResourceStorageFacade;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.imports.RewritableImportSection;
import org.eclipse.xtext.xbase.resource.BatchLinkableResourceStorageFacade;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputer;
import org.eclipse.xtext.xbase.util.XExpressionHelper;

import com.avaloq.tools.ddk.check.documentation.CheckEObjectDocumentationProvider;
import com.avaloq.tools.ddk.check.generator.CheckCompiler;
import com.avaloq.tools.ddk.check.generator.CheckOutputConfigurationProvider;
import com.avaloq.tools.ddk.check.imports.CheckRewritableImportSectionFactory;
import com.avaloq.tools.ddk.check.naming.CheckDeclarativeQualifiedNameProvider;
import com.avaloq.tools.ddk.check.resource.CheckLocationInFileProvider;
import com.avaloq.tools.ddk.check.resource.CheckResourceDescriptionStrategy;
import com.avaloq.tools.ddk.check.scoping.CheckLinkingService;
import com.avaloq.tools.ddk.check.scoping.CheckScopeProvider;
import com.avaloq.tools.ddk.check.scoping.ExtensionPointAwareScopeProvider;
import com.avaloq.tools.ddk.check.typing.CheckExpressionHelper;
import com.avaloq.tools.ddk.check.typing.CheckTypeComputer;
import com.avaloq.tools.ddk.xtext.parser.FixedPartialParsingHelper;
import com.google.inject.name.Names;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings({"PMD.CouplingBetweenObjects", "restriction"})
public class CheckRuntimeModule extends com.avaloq.tools.ddk.check.AbstractCheckRuntimeModule {

  public Class<? extends IResourceStorageFacade> bindIResourceStorageFacade() {
    return BatchLinkableResourceStorageFacade.class;
  }

  @Override
  public Class<? extends ILinkingService> bindILinkingService() {
    return CheckLinkingService.class;
  }

  @Override
  public Class<? extends ITypeComputer> bindITypeComputer() {
    return CheckTypeComputer.class;
  }

  @Override
  public Class<? extends IScopeProvider> bindIScopeProvider() {
    return CheckScopeProvider.class;
  }

  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return CheckDeclarativeQualifiedNameProvider.class;
  }

  /**
   * @return a JavaDoc-like documentation provider
   */
  public Class<? extends IEObjectDocumentationProvider> bindIEObjectDocumentationProvider() {
    return CheckEObjectDocumentationProvider.class;
  }

  @Override
  public Class<? extends ILocationInFileProvider> bindILocationInFileProvider() {
    return CheckLocationInFileProvider.class;
  }

  @Override
  public void configureIScopeProviderDelegate(final com.google.inject.Binder binder) {
    binder.bind(IScopeProvider.class).annotatedWith(Names.named(org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(XImportSectionNamespaceScopeProvider.class);
  }

  /** @return a strategy for selecting the objects to export in Index */
  @Override
  public Class<? extends org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
    return CheckResourceDescriptionStrategy.class;
  }

  /** Generates resources from a check model. {@inheritDoc} */
  @Override
  public Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
    return com.avaloq.tools.ddk.check.generator.CheckGenerator.class;
  }

  /** Global scope provider which finds catalogs in the plugin registry. {@inheritDoc} */
  @Override
  public Class<? extends IGlobalScopeProvider> bindIGlobalScopeProvider() {
    return ExtensionPointAwareScopeProvider.class;
  }

  /**
   * Binds a custom output configuration provider.
   *
   * @return the check output configuration provider
   */
  public Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return CheckOutputConfigurationProvider.class;
  }

  /**
   * Binds a custom expression helper.
   *
   * @return CheckExpressionHelper
   */
  public Class<? extends XExpressionHelper> bindXExpressionHelper() {
    return CheckExpressionHelper.class;
  }

  @Override
  public void configureLinkingIScopeProvider(final com.google.inject.Binder binder) {
    binder.bind(IScopeProvider.class).annotatedWith(org.eclipse.xtext.linking.LinkingScopeProviderBinding.class).to(CheckScopeProvider.class);
  }

  /**
   * Sets our own compiler.
   *
   * @return CheckCompiler.class
   */
  public Class<? extends XbaseCompiler> bindXbaseCompiler() {
    return CheckCompiler.class;
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

  /**
   * Use our own override of {@link RewritableImportSection.Factory}, to create {@link RewritableImportSection}s which automatically sort import lines.
   *
   * @return {@link CheckRewritableImportSectionFactory}
   */
  public Class<? extends RewritableImportSection.Factory> bindRewritableImportSectionFactory() {
    return CheckRewritableImportSectionFactory.class;
  }

  public Class<? extends org.eclipse.xtext.formatting2.IFormatter2> bindIFormatter2() {
    return com.avaloq.tools.ddk.check.formatting2.CheckFormatter.class;
  }

  public void configureFormatterPreferences(final com.google.inject.Binder binder) {
    binder.bind(IPreferenceValuesProvider.class).annotatedWith(org.eclipse.xtext.formatting2.FormatterPreferences.class).to(org.eclipse.xtext.formatting2.FormatterPreferenceValuesProvider.class);
  }

}
