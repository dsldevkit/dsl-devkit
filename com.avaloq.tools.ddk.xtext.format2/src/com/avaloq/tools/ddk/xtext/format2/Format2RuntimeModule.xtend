/*
 * generated by Xtext 2.14.0
 */
package com.avaloq.tools.ddk.xtext.format2

import com.avaloq.tools.ddk.xtext.format2.conversion.FormatValueConverterService
import com.avaloq.tools.ddk.xtext.format2.generator.FormatOutputConfigurationProvider
import com.avaloq.tools.ddk.xtext.format2.naming.FormatQualifiedNameConverter
import com.avaloq.tools.ddk.xtext.format2.naming.FormatQualifiedNameProvider
import com.avaloq.tools.ddk.xtext.format2.resource.FormatResource
import com.avaloq.tools.ddk.xtext.format2.resource.FormatResourceDescriptionStrategy
import com.avaloq.tools.ddk.xtext.format2.scoping.Format2ScopeProvider
import com.avaloq.tools.ddk.xtext.format2.scoping.FormatLinkingService
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
class Format2RuntimeModule extends AbstractFormat2RuntimeModule {


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
    return Format2ScopeProvider
  }

  /** {@inheritDoc} */
  override configureLinkingIScopeProvider(Binder binder) {
    binder.bind(IScopeProvider).annotatedWith(LinkingScopeProviderBinding).to(Format2ScopeProvider)
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
