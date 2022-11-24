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
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject

class ResourceDescriptionConstantsGenerator {

  @Inject extension CodeGenerationX
  @Inject extension Naming
  @Inject extension ExportGeneratorX

  def generate(ExportModel it, CompilationContext ctx, extension GenModelUtilX genModelUtil) {
    '''
      package «getResourceDescriptionConstants().toJavaPackage()»;

      public interface «getResourceDescriptionConstants().toSimpleName()» {
        «val types = it.exports»
        «FOR c : types.filter[!it.type.abstract]»
          «val a = c.allEAttributes»
          «val d = c.allUserData()»
          «IF !a.isEmpty || !d.isEmpty»
            // Export «c.type.name»
            «IF !a.isEmpty»
              «FOR attr : a»
                public static final String «attr.constantName(c.type)» = "«attr.name.javaEncode()»"; //$NON-NLS-1$
              «ENDFOR»
            «ENDIF»
            «IF !d.isEmpty»
              «FOR data : d»
                public static final String «data.constantName(c.type)» = "«data.name.javaEncode()»"; //$NON-NLS-1$
              «ENDFOR»
            «ENDIF»

          «ENDIF»
        «ENDFOR»
      }
    '''
  }

}