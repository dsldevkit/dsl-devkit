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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.Grammar;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.inject.Inject;


public class ResourceDescriptionManagerGenerator {

  @Inject
  private Naming naming;

  @Inject
  private ExportGeneratorX exportGeneratorX;

  public CharSequence generate(final ExportModel model, final CompilationContext ctx, final GenModelUtilX genModelUtil) {
    final Grammar grammar = exportGeneratorX.getGrammar(model);
    final List<Grammar> usedGrammars = grammar != null ? grammar.getUsedGrammars() : new ArrayList<>();
    final Grammar extendedGrammar = (usedGrammars.isEmpty() || usedGrammars.get(0).getName().endsWith(".Terminals")) ? null : usedGrammars.get(0);
    final StringBuilder sb = new StringBuilder();
    sb.append("package ");
    sb.append(naming.toJavaPackage(exportGeneratorX.getResourceDescriptionManager(model)));
    sb.append(";\n");
    sb.append("\n");
    sb.append("import java.util.Set;\n");
    sb.append("\n");
    sb.append("import com.avaloq.tools.ddk.xtext.resource.AbstractCachingResourceDescriptionManager;\n");
    if (extendedGrammar != null) {
      sb.append("import ");
      sb.append(exportGeneratorX.getResourceDescriptionManager(extendedGrammar));
      sb.append(";\n");
      sb.append("import com.google.common.collect.ImmutableSet;\n");
      sb.append("import com.google.common.collect.Sets;\n");
    }
    sb.append("import com.google.inject.Singleton;\n");
    sb.append("\n");
    sb.append("\n");
    sb.append("/**\n");
    sb.append(" * Resource description manager for ");
    sb.append(exportGeneratorX.getName(model));
    sb.append(" resources.\n");
    sb.append(" */\n");
    sb.append("@Singleton\n");
    sb.append("public class ");
    sb.append(naming.toSimpleName(exportGeneratorX.getResourceDescriptionManager(model)));
    sb.append(" extends AbstractCachingResourceDescriptionManager {\n");
    sb.append("\n");
    sb.append("  public static final Set<String> INTERESTING_EXTS = ");
    if (extendedGrammar != null) {
      sb.append("ImmutableSet.copyOf(Sets.union(");
      sb.append(naming.toSimpleName(exportGeneratorX.getResourceDescriptionManager(extendedGrammar)));
      sb.append(".INTERESTING_EXTS, of(/*add extensions here*/)));\n");
    } else {
      sb.append("all();\n");
    }
    sb.append("\n");
    sb.append("  @Override\n");
    sb.append("  protected Set<String> getInterestingExtensions() {\n");
    sb.append("    return INTERESTING_EXTS;\n");
    sb.append("  }\n");
    sb.append("\n");
    sb.append("}\n");
    return sb;
  }

}
