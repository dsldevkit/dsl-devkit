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
package com.avaloq.tools.ddk.checkcfg.generator;

import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Properties;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.AbstractFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

public class CheckCfgGenerator implements IGenerator {

  @Inject
  private CheckConfigurationPropertiesGenerator propertiesGenerator;

  public String outputPath() {
    return ".settings";
  }

  public String fileName(final CheckConfiguration configuration) {
    return ICheckConfigurationStoreService.DEFAULT_CHECK_CONFIGURATION_NODE + ".prefs";
  }

  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    if (fsa instanceof AbstractFileSystemAccess abstractFsa) {
      abstractFsa.setOutputPath(outputPath());
    }
    for (final CheckConfiguration configuration : Iterables.filter(IteratorExtensions.toIterable(resource.getAllContents()), CheckConfiguration.class)) {
      fsa.generateFile(fileName(configuration), compile(configuration));
    }
  }

  public CharSequence compile(final CheckConfiguration config) {
    final Properties properties = propertiesGenerator.convertToProperties(config);
    final StringBuilder builder = new StringBuilder();
    for (final Object k : properties.keySet()) {
      builder.append(k).append("=").append(properties.get(k)).append("\n");
    }
    return builder;
  }
}
