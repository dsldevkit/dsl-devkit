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
package com.avaloq.tools.ddk.xtext.export.generator;

import java.util.Set;

import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.OutputConfigurationProvider;


/**
 * Provides output configurations for the generation of export files.
 */
public class ExportOutputConfigurationProvider extends OutputConfigurationProvider {

  public static final String STUB_OUTPUT = "STUB_OUTPUT"; //$NON-NLS-1$

  @Override
  public Set<OutputConfiguration> getOutputConfigurations() {
    Set<OutputConfiguration> configurations = super.getOutputConfigurations();

    configurations.add(getStubConfig());
    configurations.add(getDefaultConfig());

    return configurations;
  }

  /**
   * Creates output configuration for generated stubs.
   *
   * @return output configuration
   */
  private OutputConfiguration getStubConfig() {
    OutputConfiguration config = new OutputConfiguration(STUB_OUTPUT);
    config.setDescription("Output configuration for stubs"); //$NON-NLS-1$
    config.setOverrideExistingResources(false);
    config.setCanClearOutputDirectory(false);
    config.setCleanUpDerivedResources(false);
    config.setOutputDirectory("src"); //$NON-NLS-1$
    return config;
  }

  /**
   * Creates output configuration for regular generated files.
   *
   * @return output configuration
   */
  private OutputConfiguration getDefaultConfig() {
    OutputConfiguration config = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
    config.setDescription("Output configuration for generated classes"); //$NON-NLS-1$
    config.setOverrideExistingResources(true);
    config.setOutputDirectory("src-gen"); //$NON-NLS-1$
    return config;
  }
}
