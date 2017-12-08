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

package com.avaloq.tools.ddk.xtext.scope.generator

import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
import com.google.inject.Inject
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.scoping.IScopeProvider

/**
 * Scope generator generating the {@link IScopeProvider} implementation for a given scope file.
 */
class ScopeGenerator implements IGenerator {

  @Inject
  extension Naming
  @Inject
  ScopeProviderGenerator scopeProvider
  @Inject
  ScopeNameProviderGenerator nameProvider
  @Inject
  GenModelUtilX genModelUtil
  @Inject
  GeneratorSupport generatorSupport

  CompilationContext compilationContext

  override doGenerate(Resource input, IFileSystemAccess fsa) {
    if (input == null || input.contents.empty || !(input.contents.head instanceof ScopeModel)) {
      return
    }
    val model = input.contents.head as ScopeModel
    genModelUtil.resource = model.eResource
    var IProject project = null
    if (input.URI.isPlatformResource) {
      val res = ResourcesPlugin.workspace.root.findMember(input.URI.toPlatformString(true))
      if (res != null) {
        project = res.project
      }
    }

    generatorSupport.executeWithProjectResourceLoader(project, [
      compilationContext = ScopingGeneratorUtil.getCompilationContext(model, genModelUtil)

      generateScopeNameProvider(model, fsa)
      generateScopeProvider(model, fsa)
    ])
  }

  def generateScopeProvider(ScopeModel model, IFileSystemAccess fsa) {
    val fileName = (model.name.toJavaPackage + ".scoping.").replace('.', '/') + model.name.toSimpleName + "ScopeProvider.java";
    fsa.generateFile(fileName, scopeProvider.generate(model, nameProvider, compilationContext, genModelUtil))
  }

  def generateScopeNameProvider(ScopeModel model, IFileSystemAccess fsa) {
    val fileName = (model.name.toJavaPackage + ".scoping.").replace('.', '/') + model.name.toSimpleName + "ScopeNameProvider.java";
    fsa.generateFile(fileName, nameProvider.generate(model, compilationContext, genModelUtil))
  }

}
