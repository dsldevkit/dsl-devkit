/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.scope.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;

import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorSupport;
import com.google.inject.Inject;


/**
 * Emits the Java source of the inferred scope types, generating all types of a resource with one project resource loader.
 * <p>
 * {@link com.avaloq.tools.ddk.xtext.scope.jvmmodel.ScopeJvmModelInferrer} renders every method body during emission
 * within {@link GeneratorSupport#executeWithProjectResourceLoader(org.eclipse.core.resources.IProject, Runnable)
 * GeneratorSupport#executeWithProjectResourceLoader}. Establishing that resource loader once for the whole resource here
 * lets those nested calls reuse it, instead of resolving the project's classpath and building a class loader for every
 * body.
 * </p>
 */
public class ScopeJvmModelGenerator extends JvmModelGenerator {

  @Inject
  private GeneratorSupport generatorSupport;

  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    if (input.getContents().stream().anyMatch(JvmDeclaredType.class::isInstance)) {
      generatorSupport.executeWithProjectResourceLoaderOf(input, () -> super.doGenerate(input, fsa));
    } else {
      super.doGenerate(input, fsa); // no inferred type, hence no body to render: do not build a class loader
    }
  }

}
