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
package com.avaloq.tools.ddk.xtext.scope.generator;

import com.avaloq.tools.ddk.xtext.generator.expression.CompilationContext;
import com.avaloq.tools.ddk.xtext.generator.util.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.generator.util.Naming;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeNameProviderGenerator;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderGenerator;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopeProviderX;
import com.avaloq.tools.ddk.xtext.scope.generator.ScopingGeneratorUtil;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * Scope generator generating the {@link IScopeProvider} implementation for a given scope file.
 */
@SuppressWarnings("all")
public class ScopeGenerator implements IGenerator {
  @Inject
  @Extension
  private Naming _naming;
  
  @Inject
  private ScopeProviderGenerator scopeProvider;
  
  @Inject
  private ScopeNameProviderGenerator nameProvider;
  
  @Inject
  private GenModelUtilX genModelUtil;
  
  @Inject
  private ScopeProviderX scopeProviderExtensions;
  
  private CompilationContext compilationContext;
  
  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    if (((Objects.equal(input, null) || input.getContents().isEmpty()) || (!(IterableExtensions.<EObject>head(input.getContents()) instanceof ScopeModel)))) {
      return;
    }
    EList<EObject> _contents = input.getContents();
    EObject _head = IterableExtensions.<EObject>head(_contents);
    final ScopeModel model = ((ScopeModel) _head);
    Resource _eResource = model.eResource();
    this.genModelUtil.setResource(_eResource);
    IProject project = null;
    URI _uRI = input.getURI();
    boolean _isPlatformResource = _uRI.isPlatformResource();
    if (_isPlatformResource) {
      IWorkspace _workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot _root = _workspace.getRoot();
      URI _uRI_1 = input.getURI();
      String _platformString = _uRI_1.toPlatformString(true);
      final IResource res = _root.findMember(_platformString);
      boolean _notEquals = (!Objects.equal(res, null));
      if (_notEquals) {
        IProject _project = res.getProject();
        project = _project;
      }
    }
    final Runnable _function = () -> {
      CompilationContext _compilationContext = this.scopeProviderExtensions.compilationContext(model);
      this.compilationContext = _compilationContext;
      this.generateScopeNameProvider(model, fsa);
      this.generateScopeProvider(model, fsa);
    };
    ScopingGeneratorUtil.executeWithProjectResourceLoader(project, _function);
  }
  
  public void generateScopeProvider(final ScopeModel model, final IFileSystemAccess fsa) {
    String _name = model.getName();
    String _javaPackage = this._naming.toJavaPackage(_name);
    String _plus = (_javaPackage + ".scoping.");
    String _replace = _plus.replace(".", "/");
    String _name_1 = model.getName();
    String _simpleName = this._naming.toSimpleName(_name_1);
    String _plus_1 = (_replace + _simpleName);
    final String fileName = (_plus_1 + "ScopeProvider.java");
    CharSequence _generate = this.scopeProvider.generate(model, this.nameProvider, this.compilationContext, this.genModelUtil);
    fsa.generateFile(fileName, _generate);
  }
  
  public void generateScopeNameProvider(final ScopeModel model, final IFileSystemAccess fsa) {
    String _name = model.getName();
    String _javaPackage = this._naming.toJavaPackage(_name);
    String _plus = (_javaPackage + ".scoping.");
    String _replace = _plus.replace(".", "/");
    String _name_1 = model.getName();
    String _simpleName = this._naming.toSimpleName(_name_1);
    String _plus_1 = (_replace + _simpleName);
    final String fileName = (_plus_1 + "ScopeNameProvider.java");
    CharSequence _generate = this.nameProvider.generate(model, this.compilationContext, this.genModelUtil);
    fsa.generateFile(fileName, _generate);
  }
}
