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

package com.avaloq.tools.ddk.xtext.export.generator

import com.avaloq.tools.ddk.xtext.export.export.ExportModel
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject

class ExportFeatureExtensionGenerator {

  @Inject extension Naming
  @Inject extension ExportGeneratorX

  def generate(ExportModel it, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    '''
      package «exportFeatureExtension.toJavaPackage»;

      import org.eclipse.xtext.naming.IQualifiedNameProvider;
      import com.avaloq.tools.ddk.xtext.resource.AbstractExportFeatureExtension;
      import com.avaloq.tools.ddk.xtext.resource.AbstractResourceDescriptionStrategy;
      import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider;
      import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;
      import com.google.inject.Inject;

      import «exportedNamesProvider»;


      public class «exportFeatureExtension.toSimpleName» extends AbstractExportFeatureExtension {

        @Inject
        private «exportedNamesProvider.toSimpleName» namesProvider;

        @Inject
        private «fingerprintComputer.toSimpleName» fingerprintComputer;

        @Inject
        private «fragmentProvider.toSimpleName» fragmentProvider;

        @Inject
        private «resourceDescriptionStrategy.toSimpleName» resourceDescriptionStrategy;

        @Override
        protected IQualifiedNameProvider getNamesProvider() {
          return namesProvider;
        }

        @Override
        protected IFingerprintComputer getFingerprintComputer() {
          return fingerprintComputer;
        }

        @Override
        protected AbstractSelectorFragmentProvider getFragmentProvider() {
          return fragmentProvider;
        }

        @Override
        protected AbstractResourceDescriptionStrategy getResourceDescriptionStrategy() {
          return resourceDescriptionStrategy;
        }

      }
    '''
  }

}
