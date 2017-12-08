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

package com.avaloq.tools.ddk.xtext.export.generator

import com.avaloq.tools.ddk.xtext.export.export.ExportModel
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject

class ResourceDescriptionManagerGenerator {

  @Inject extension Naming
  @Inject extension ExportGeneratorX

  def generate(ExportModel model, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    val grammar = model.grammar
    val usedGrammars = if (grammar != null) grammar.usedGrammars else newArrayList
    val extendedGrammar = if (usedGrammars.isEmpty || usedGrammars.head.name.endsWith('.Terminals')) null else usedGrammars.head
    '''
      package «model.resourceDescriptionManager.toJavaPackage»;

      import java.util.Set;

      import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;
      «IF extendedGrammar != null»
      import «extendedGrammar.resourceDescriptionManager»;
      import com.google.common.collect.ImmutableSet;
      import com.google.common.collect.Sets;
      «ENDIF»
      import com.google.inject.Singleton;


      /**
       * Resource description manager for «model.name» resources.
       */
      @Singleton
      public class «model.resourceDescriptionManager.toSimpleName» extends AbstractCachingResourceDescriptionManager {

        public static final Set<String> INTERESTING_EXTS = «IF extendedGrammar != null»ImmutableSet.copyOf(Sets.union(«extendedGrammar.resourceDescriptionManager.toSimpleName».INTERESTING_EXTS, of(/*add extensions here*/)));«ELSE»all();«ENDIF»

        @Override
        protected Set<String> getInterestingExtensions() {
          return INTERESTING_EXTS;
        }

      }
    '''
  }

}