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

import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig
import org.eclipse.xtext.xtext.generator.model.project.SubProjectConfig

class ProjectConfig extends StandardProjectConfig {
  static val TESTS_PROJECT_SUFFIX = '.test'
  static val UI_PROJECT_SUFFIX = '.ui'
  static val IDE_PROJECT_SUFFIX = UI_PROJECT_SUFFIX


  override protected computeName(SubProjectConfig project) {
    if (mavenLayout) {
      return super.computeName(project)
    }
    switch project {
      case runtime: baseName
      case runtimeTest: baseName + TESTS_PROJECT_SUFFIX
      case genericIde: baseName + IDE_PROJECT_SUFFIX
      case eclipsePlugin: baseName + UI_PROJECT_SUFFIX
      case eclipsePluginTest: baseName + UI_PROJECT_SUFFIX + TESTS_PROJECT_SUFFIX
      default: super.computeName(project)
    }
  }

}
