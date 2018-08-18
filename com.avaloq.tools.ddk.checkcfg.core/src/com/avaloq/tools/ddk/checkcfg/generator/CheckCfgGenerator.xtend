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
package com.avaloq.tools.ddk.checkcfg.generator

import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration
import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractFileSystemAccess
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator

import static org.eclipse.xtext.xbase.lib.IteratorExtensions.*

class CheckCfgGenerator implements IGenerator {

  @Inject
  CheckConfigurationPropertiesGenerator propertiesGenerator;

  def outputPath() {
    '.settings'
  }

  def fileName(CheckConfiguration configuration) {
    ICheckConfigurationStoreService.DEFAULT_CHECK_CONFIGURATION_NODE + '.prefs'
  }

  override void doGenerate(Resource resource, IFileSystemAccess fsa) {
    if (fsa instanceof AbstractFileSystemAccess) {
      fsa.setOutputPath(outputPath)
    }
    for (configuration:toIterable(resource.allContents).filter(typeof(CheckConfiguration))) {
      fsa.generateFile(configuration.fileName, configuration.compile)
    }
  }

  def compile(CheckConfiguration config) {
    val properties = propertiesGenerator.convertToProperties(config);
    '''
    «FOR k:properties.keySet»
    «k»=«properties.get(k)»
    «ENDFOR»
    '''
  }
}
