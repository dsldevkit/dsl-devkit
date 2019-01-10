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

package com.avaloq.tools.ddk.xtext.generator.model.project

import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig
import org.eclipse.xtext.xtext.generator.model.project.SubProjectConfig

class ProjectConfig extends StandardProjectConfig {

  @Accessors var String runtimeSuffix = ""
  @Accessors var String testSuffix = "test"
  @Accessors var String eclipsePluginSuffix = "ui"
  @Accessors var String genericIdeSuffix = eclipsePluginSuffix
  @Accessors var boolean forceDisableIdeProject = true

  override protected computeName(SubProjectConfig project) {
    if (mavenLayout) {
      return super.computeName(project)
    }
    switch project {
      case runtime: '''«baseName».«runtimeSuffix»'''
      case runtimeTest: '''«baseName».«runtimeSuffix».«testSuffix»'''
      case genericIde: '''«baseName».«genericIdeSuffix»'''
      case eclipsePlugin: '''«baseName».«eclipsePluginSuffix»'''
      case eclipsePluginTest: '''«baseName».«eclipsePluginSuffix».«testSuffix»'''
      default: super.computeName(project)
    }
  }

  override setDefaults() {
    super.setDefaults
    if (forceDisableIdeProject) {
      genericIde.enabled = false
    }
  }

}

/* Copyright (c) Avaloq Licence AG */