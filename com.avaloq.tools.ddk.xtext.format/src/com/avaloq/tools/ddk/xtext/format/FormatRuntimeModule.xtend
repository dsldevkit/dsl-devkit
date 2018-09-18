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
package com.avaloq.tools.ddk.xtext.format

import com.avaloq.tools.ddk.xtext.format.conversion.FormatValueConverterService
import com.avaloq.tools.ddk.xtext.format.generator.FormatOutputConfigurationProvider
import com.avaloq.tools.ddk.xtext.format.naming.FormatQualifiedNameConverter
import com.avaloq.tools.ddk.xtext.format.naming.FormatQualifiedNameProvider
import com.avaloq.tools.ddk.xtext.format.resource.FormatResource
import com.avaloq.tools.ddk.xtext.format.resource.FormatResourceDescriptionStrategy
import com.avaloq.tools.ddk.xtext.format.scoping.FormatLinkingService
import com.avaloq.tools.ddk.xtext.format.scoping.FormatScopeProvider
import com.avaloq.tools.ddk.xtext.parser.FixedPartialParsingHelper
import com.google.inject.Binder
import com.google.inject.name.Names
import org.eclipse.xtext.generator.IOutputConfigurationProvider
import org.eclipse.xtext.linking.LinkingScopeProviderBinding
import org.eclipse.xtext.resource.containers.ResourceSetBasedAllContainersStateProvider
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class FormatRuntimeModule extends AbstractFormatRuntimeModule {

  override bindXtextResource() {
    return FormatResource
  }

  /**
   * Binds a class loader required by the resource manager.
   *
   * @return bound class loader instance
   */
  override bindClassLoaderToInstance() {
    return getClass().getClassLoader()
  }

  override bindIValueConverterService() {
    return FormatValueConverterService
  }

  /**
   * Binds custom qualified name converter.
   *
   * @return the implementation
   */
  override bindIQualifiedNameConverter() {
    return FormatQualifiedNameConverter
  }

  /** {@inheritDoc} */
  override bindIQualifiedNameProvider() {
    return FormatQualifiedNameProvider
  }

  /**
   * Workaround for Bug 416913. To be removed with DSL-596
   *
   * @return {@link FixedPartialParsingHelper}
   */
  override bindIPartialParserHelper() {
    return FixedPartialParsingHelper
  }

  /** {@inheritDoc} */
  override bindIScopeProvider() {
    return FormatScopeProvider
  }

  /** {@inheritDoc} */
  override configureLinkingIScopeProvider(Binder binder) {
    binder.bind(IScopeProvider).annotatedWith(LinkingScopeProviderBinding).to(FormatScopeProvider)
  }

  /** {@inheritDoc} */
  override
  // CHECKSTYLE:OFF
   bindIAllContainersState$Provider() {
    // CHECKSTYLE:ON
    return ResourceSetBasedAllContainersStateProvider
  }

  /** {@inheritDoc} */
  override bindILinkingService() {
    return FormatLinkingService
  }

  /** {@inheritDoc} */
  override bindIDefaultResourceDescriptionStrategy() {
    return FormatResourceDescriptionStrategy
  }

  /**
   * Binds a custom output configuration provider.
   *
   * @return the format output configuration provider
   */
  def Class<? extends IOutputConfigurationProvider> bindIOutputConfigurationProvider() {
    return FormatOutputConfigurationProvider
  }

  /** {@inheritDoc} */
  override configureIScopeProviderDelegate(Binder binder) {
    binder.bind(IScopeProvider).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(XImportSectionNamespaceScopeProvider)
  }

}
