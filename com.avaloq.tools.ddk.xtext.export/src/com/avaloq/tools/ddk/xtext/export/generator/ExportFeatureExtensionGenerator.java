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

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.inject.Inject;


@SuppressWarnings("PMD.UnusedFormalParameter")
public class ExportFeatureExtensionGenerator {

  @Inject
  private Naming naming;

  @Inject
  private ExportGeneratorX exportGeneratorX;

  public CharSequence generate(final ExportModel it, final CompilationContext ctx, final GenModelUtilX genModelUtil) {
    // CHECKSTYLE:CONSTANTS-OFF
    final StringBuilder sb = new StringBuilder(2048);
    sb.append("package ");
    sb.append(naming.toJavaPackage(exportGeneratorX.getExportFeatureExtension(it)));
    sb.append(";\n");
    sb.append('\n');
    sb.append("import org.eclipse.xtext.naming.IQualifiedNameProvider;\n");
    sb.append("import com.avaloq.tools.ddk.xtext.resource.AbstractExportFeatureExtension;\n");
    sb.append("import com.avaloq.tools.ddk.xtext.resource.AbstractResourceDescriptionStrategy;\n");
    sb.append("import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider;\n");
    sb.append("import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;\n");
    sb.append("import com.google.inject.Inject;\n");
    sb.append('\n');
    sb.append("import ");
    sb.append(exportGeneratorX.getExportedNamesProvider(it));
    sb.append(";\n");
    sb.append('\n');
    sb.append('\n');
    sb.append("public class ");
    sb.append(naming.toSimpleName(exportGeneratorX.getExportFeatureExtension(it)));
    sb.append(" extends AbstractExportFeatureExtension {\n");
    sb.append('\n');
    sb.append("  @Inject\n");
    sb.append("  private ");
    sb.append(naming.toSimpleName(exportGeneratorX.getExportedNamesProvider(it)));
    sb.append(" namesProvider;\n");
    sb.append('\n');
    sb.append("  @Inject\n");
    sb.append("  private ");
    sb.append(naming.toSimpleName(exportGeneratorX.getFingerprintComputer(it)));
    sb.append(" fingerprintComputer;\n");
    sb.append('\n');
    sb.append("  @Inject\n");
    sb.append("  private ");
    sb.append(naming.toSimpleName(exportGeneratorX.getFragmentProvider(it)));
    sb.append(" fragmentProvider;\n");
    sb.append('\n');
    sb.append("  @Inject\n");
    sb.append("  private ");
    sb.append(naming.toSimpleName(exportGeneratorX.getResourceDescriptionStrategy(it)));
    sb.append(" resourceDescriptionStrategy;\n");
    sb.append('\n');
    sb.append("  @Override\n");
    sb.append("  protected IQualifiedNameProvider getNamesProvider() {\n");
    sb.append("    return namesProvider;\n");
    sb.append("  }\n");
    sb.append('\n');
    sb.append("  @Override\n");
    sb.append("  protected IFingerprintComputer getFingerprintComputer() {\n");
    sb.append("    return fingerprintComputer;\n");
    sb.append("  }\n");
    sb.append('\n');
    sb.append("  @Override\n");
    sb.append("  protected AbstractSelectorFragmentProvider getFragmentProvider() {\n");
    sb.append("    return fragmentProvider;\n");
    sb.append("  }\n");
    sb.append('\n');
    sb.append("  @Override\n");
    sb.append("  protected AbstractResourceDescriptionStrategy getResourceDescriptionStrategy() {\n");
    sb.append("    return resourceDescriptionStrategy;\n");
    sb.append("  }\n");
    sb.append('\n');
    sb.append("}\n");
    // CHECKSTYLE:CONSTANTS-ON
    return sb;
  }

}
