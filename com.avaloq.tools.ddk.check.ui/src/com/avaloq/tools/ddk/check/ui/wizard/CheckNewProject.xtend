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
package com.avaloq.tools.ddk.check.ui.wizard

import org.eclipse.xtext.generator.IFileSystemAccess

class CheckNewProject {

  def doGenerate(CheckProjectInfo info, IFileSystemAccess fsa){
     fsa.generateFile(info.fileName, info.fileContent)
  }

  def String fileName(CheckProjectInfo info){
    '''«info.path + info.catalogName + ".check"»'''
  }

  def fileContent(CheckProjectInfo info){
  '''
    package «info.packageName»

    «info.fileImports»

    /**
     *  Check catalog for «info.grammar.name»
     */
    catalog «info.catalogName»
    for grammar «info.grammar.name» {

      // Add categories and checks

    }
    '''
  }

  def fileImports(CheckProjectInfo info) {
    // package where top-level grammar rule interfaces are defined
    if(info.defaultPackageImport != null)
        '''import «info.defaultPackageImport».* '''
    else ""
  }

}
