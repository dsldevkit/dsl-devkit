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
package com.avaloq.tools.ddk.xtext.export.generator;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.UserData;
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.inject.Inject;


public class ResourceDescriptionConstantsGenerator {

  @Inject
  private CodeGenerationX codeGenerationX;

  @Inject
  private Naming naming;

  @Inject
  private ExportGeneratorX exportGeneratorX;

  public CharSequence generate(final ExportModel it, final CompilationContext ctx, final GenModelUtilX genModelUtil) {
    final StringBuilder sb = new StringBuilder();
    sb.append("package ");
    sb.append(naming.toJavaPackage(exportGeneratorX.getResourceDescriptionConstants(it)));
    sb.append(";\n");
    sb.append("\n");
    sb.append("public interface ");
    sb.append(naming.toSimpleName(exportGeneratorX.getResourceDescriptionConstants(it)));
    sb.append(" {\n");
    final EList<Export> types = it.getExports();
    for (final Export c : types) {
      if (!c.getType().isAbstract()) {
        final EList<EAttribute> a = c.getAllEAttributes();
        final List<UserData> d = exportGeneratorX.allUserData(c);
        if (!a.isEmpty() || !d.isEmpty()) {
          sb.append("  // Export ");
          sb.append(c.getType().getName());
          sb.append("\n");
          if (!a.isEmpty()) {
            for (final EAttribute attr : a) {
              sb.append("  public static final String ");
              sb.append(exportGeneratorX.constantName(attr, c.getType()));
              sb.append(" = \"");
              sb.append(codeGenerationX.javaEncode(attr.getName()));
              sb.append("\"; //$NON-NLS-1$\n");
            }
          }
          if (!d.isEmpty()) {
            for (final UserData data : d) {
              sb.append("  public static final String ");
              sb.append(exportGeneratorX.constantName(data, c.getType()));
              sb.append(" = \"");
              sb.append(codeGenerationX.javaEncode(data.getName()));
              sb.append("\"; //$NON-NLS-1$\n");
            }
          }
          sb.append("\n");
        }
      }
    }
    sb.append("}\n");
    return sb;
  }

}
