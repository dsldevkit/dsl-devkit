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
package com.avaloq.tools.ddk.xtext.format.generator;

import java.util.Set;

import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.generator.OutputConfigurationProvider;

import com.avaloq.tools.ddk.xtext.format.FormatConstants;


/**
 * Provides output configurations for the generation of formatters.
 */
@SuppressWarnings("nls")
public class FormatOutputConfigurationProvider extends OutputConfigurationProvider {

  @Override
  public Set<OutputConfiguration> getOutputConfigurations() {
    Set<OutputConfiguration> configurations = super.getOutputConfigurations();

    configurations.add(getFormatterConfig());
    configurations.add(getAbstractFormatterConfig());

    return configurations;
  }

  /**
   * Creates output configuration for a Formatter.
   *
   * @return output configuration
   */
  private OutputConfiguration getFormatterConfig() {
    OutputConfiguration config = new OutputConfiguration(FormatConstants.FORMATTER);
    config.setDescription("Output configuration for a formatter");
    config.setOverrideExistingResources(false);
    config.setCanClearOutputDirectory(false);
    config.setCleanUpDerivedResources(false);
    config.setOutputDirectory("src");
    return config;
  }

  /**
   * Creates output configuration for a AbstractFormatter.
   *
   * @return output configuration
   */
  private OutputConfiguration getAbstractFormatterConfig() {
    OutputConfiguration config = new OutputConfiguration(FormatConstants.ABSTRACT_FORMATTER);
    config.setDescription("Output configuration for a abstract formatter");
    config.setOverrideExistingResources(true);
    config.setOutputDirectory("src-gen");
    return config;
  }
}
