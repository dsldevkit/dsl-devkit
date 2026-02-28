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

package com.avaloq.tools.ddk.xtext.scope.generator;

import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.google.inject.Inject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.core.resources.IResource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IGenerator2;
import org.eclipse.xtext.generator.IGeneratorContext;
import org.eclipse.xtext.scoping.IScopeProvider;

/**
 * Scope generator generating the {@link IScopeProvider} implementation for a given scope file.
 */
public class ScopeGenerator implements IGenerator2 {

  @Inject
  private Naming naming;

  @Inject
  private ScopeProviderGenerator scopeProvider;

  @Inject
  private ScopeNameProviderGenerator nameProvider;

  @Inject
  private GenModelUtilX genModelUtil;

  @Inject
  private GeneratorSupport generatorSupport;

  private CompilationContext compilationContext;

  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
    if (input == null || input.getContents().isEmpty() || !(input.getContents().get(0) instanceof ScopeModel)) {
      return;
    }
    final ScopeModel model = (ScopeModel) input.getContents().get(0);
    genModelUtil.setResource(model.eResource());
    IProject project = null;
    if (input.getURI().isPlatformResource()) {
      final IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(input.getURI().toPlatformString(true));
      if (res != null) {
        project = res.getProject();
      }
    }

    generatorSupport.executeWithProjectResourceLoader(project, () -> {
      compilationContext = ScopingGeneratorUtil.getCompilationContext(model, genModelUtil);

      generateScopeNameProvider(model, fsa);
      generateScopeProvider(model, fsa);
    });
  }

  public void generateScopeProvider(final ScopeModel model, final IFileSystemAccess fsa) {
    final String fileName = (naming.toJavaPackage(model.getName()) + ".scoping.").replace('.', '/') + naming.toSimpleName(model.getName()) + "ScopeProvider.java";
    fsa.generateFile(fileName, scopeProvider.generate(model, nameProvider, compilationContext, genModelUtil));
  }

  public void generateScopeNameProvider(final ScopeModel model, final IFileSystemAccess fsa) {
    final String fileName = (naming.toJavaPackage(model.getName()) + ".scoping.").replace('.', '/') + naming.toSimpleName(model.getName()) + "ScopeNameProvider.java";
    fsa.generateFile(fileName, nameProvider.generate(model, compilationContext, genModelUtil));
  }

  @Override
  public void afterGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }

  @Override
  public void beforeGenerate(final Resource input, final IFileSystemAccess2 fsa, final IGeneratorContext context) {
  }

}
