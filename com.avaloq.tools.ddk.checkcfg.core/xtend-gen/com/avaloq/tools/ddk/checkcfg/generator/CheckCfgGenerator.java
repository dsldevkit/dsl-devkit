/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.generator;

import com.avaloq.tools.ddk.check.runtime.configuration.ICheckConfigurationStoreService;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.generator.CheckConfigurationPropertiesGenerator;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Properties;
import java.util.Set;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.AbstractFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class CheckCfgGenerator implements IGenerator {
  @Inject
  private CheckConfigurationPropertiesGenerator propertiesGenerator;
  
  public String outputPath() {
    return ".settings";
  }
  
  public String fileName(final CheckConfiguration configuration) {
    return (ICheckConfigurationStoreService.DEFAULT_CHECK_CONFIGURATION_NODE + ".prefs");
  }
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    if ((fsa instanceof AbstractFileSystemAccess)) {
      String _outputPath = this.outputPath();
      ((AbstractFileSystemAccess) fsa).setOutputPath(_outputPath);
    }
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<CheckConfiguration> _filter = Iterables.<CheckConfiguration>filter(_iterable, CheckConfiguration.class);
    for (final CheckConfiguration configuration : _filter) {
      String _fileName = this.fileName(configuration);
      CharSequence _compile = this.compile(configuration);
      fsa.generateFile(_fileName, _compile);
    }
  }
  
  public CharSequence compile(final CheckConfiguration config) {
    CharSequence _xblockexpression = null;
    {
      final Properties properties = this.propertiesGenerator.convertToProperties(config);
      StringConcatenation _builder = new StringConcatenation();
      {
        Set<Object> _keySet = properties.keySet();
        for(final Object k : _keySet) {
          _builder.append(k, "");
          _builder.append("=");
          Object _get = properties.get(k);
          _builder.append(_get, "");
          _builder.newLineIfNotEmpty();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
}
