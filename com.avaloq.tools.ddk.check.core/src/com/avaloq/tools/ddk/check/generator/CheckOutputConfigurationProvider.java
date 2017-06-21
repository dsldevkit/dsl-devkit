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
package com.avaloq.tools.ddk.check.generator;

import java.util.Set;

import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.OutputConfigurationProvider;


/**
 * Configures various output configurations. Output configurations are used by the generator
 * when writing resources.
 *
 * @see org.eclipse.xtext.generator.IFileSystemAccess
 */
public class CheckOutputConfigurationProvider extends OutputConfigurationProvider {

  public static final String DOCS_PATH = "docs/content";

  @Override
  public Set<OutputConfiguration> getOutputConfigurations() {
    Set<OutputConfiguration> configurations = super.getOutputConfigurations();

    configurations.add(getCheckDocumentationConfig());
    configurations.add(getServiceRegistryConfig());

    return configurations;
  }

  /**
   * Gets the check documentation output configuration.
   *
   * @return the check documentation configuration
   */
  private OutputConfiguration getCheckDocumentationConfig() {
    OutputConfiguration config = new OutputConfiguration(CheckGeneratorConstants.CHECK_DOC_OUTPUT);
    config.setDescription("Output configuration for check documentation use");
    config.setOutputDirectory(DOCS_PATH);
    config.setCreateOutputDirectory(true);
    config.setCanClearOutputDirectory(true);
    return config;
  }

  /**
   * Gets the service registry output configuration.
   *
   * @return the service registry configuration
   */
  private OutputConfiguration getServiceRegistryConfig() {
    OutputConfiguration config = new OutputConfiguration(CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT);
    config.setDescription("Output configuration for service registry use");
    config.setOutputDirectory("META-INF/services");
    config.setOverrideExistingResources(true);
    config.setCreateOutputDirectory(true);
    config.setCleanUpDerivedResources(false);
    config.setSetDerivedProperty(true);
    return config;
  }

}
