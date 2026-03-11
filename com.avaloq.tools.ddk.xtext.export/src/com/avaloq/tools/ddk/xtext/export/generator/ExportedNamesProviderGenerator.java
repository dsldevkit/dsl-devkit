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
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Grammar;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.common.collect.ListMultimap;
import com.google.inject.Inject;


public class ExportedNamesProviderGenerator {

  @Inject
  private CodeGenerationX codeGenerationX;
  @Inject
  private GeneratorUtilX generatorUtilX;
  @Inject
  private Naming naming;
  @Inject
  private ExportGeneratorX exportGeneratorX;

  public CharSequence generate(final ExportModel it, final CompilationContext ctx, final GenModelUtilX genModelUtil) {
    final Grammar grammar = exportGeneratorX.getGrammar(it);
    // CHECKSTYLE:CONSTANTS-OFF
    final StringBuilder sb = new StringBuilder(2048);
    sb.append("package ").append(naming.toJavaPackage(exportGeneratorX.getExportedNamesProvider(it))).append(";\n");
    sb.append('\n');
    sb.append("import org.eclipse.emf.ecore.EClass;\n");
    sb.append("import org.eclipse.emf.ecore.EObject;\n");
    sb.append("import org.eclipse.emf.ecore.EPackage;\n");
    sb.append("import org.eclipse.xtext.naming.QualifiedName;\n");
    sb.append('\n');
    sb.append("import com.avaloq.tools.ddk.xtext.naming.AbstractExportedNameProvider;\n");
    sb.append('\n');
    sb.append('\n');
    sb.append("/**\n");
    sb.append(" * Qualified name provider for grammar ");
    String grammarName = grammar != null ? grammar.getName() : null;
    sb.append(grammarName != null ? grammarName : naming.toSimpleName(exportGeneratorX.getExportedNamesProvider(it)));
    sb.append(" providing the qualified names for exported objects.\n");
    sb.append(" */\n");
    sb.append("public class ").append(naming.toSimpleName(exportGeneratorX.getExportedNamesProvider(it))).append(" extends AbstractExportedNameProvider {\n");
    sb.append('\n');
    if (!it.getExports().isEmpty()) {
      final List<Export> types = it.getExports();
      sb.append("  @Override\n");
      sb.append("  public QualifiedName qualifiedName(final EObject object) {\n");
      sb.append("    EClass eClass = object.eClass();\n");
      sb.append("    EPackage ePackage = eClass.getEPackage();\n");
      final Set<EClass> exportedEClasses = types.stream().map(Export::getType).collect(Collectors.toSet());
      final ListMultimap<EPackage, Export> exportsMap = exportGeneratorX.sortedExportsByEPackage(types);
      List<EPackage> sortedPackages = exportsMap.keySet().stream()
          .sorted((a, b) -> a.getNsURI().compareTo(b.getNsURI()))
          .collect(Collectors.toList());
      for (EPackage p : sortedPackages) {
        sb.append("    if (ePackage == ").append(genModelUtil.qualifiedPackageInterfaceName(p)).append(".eINSTANCE) {\n");
        sb.append("      int classifierID = eClass.getClassifierID();\n");
        sb.append("      switch (classifierID) {\n");
        for (EClassifier classifier : p.getEClassifiers()) {
          if (classifier instanceof EClass c && exportedEClasses.stream().anyMatch(e -> e.isSuperTypeOf(c))) {
            sb.append("      case ").append(genModelUtil.classifierIdLiteral(c)).append(": {\n");
            sb.append("        return qualifiedName((").append(genModelUtil.instanceClassName(c)).append(") object);\n");
            sb.append("      }\n");
          }
        }
        sb.append("      default:\n");
        sb.append("        return null;\n");
        sb.append("      }\n");
        sb.append("    }\n");
      }
      sb.append("    return null;\n");
      sb.append("  }\n");
      sb.append('\n');
      for (Export c : types) {
        sb.append("  /**\n");
        sb.append("   * Return the qualified name under which a ").append(c.getType().getName()).append(" object is exported, or <code>null</code> if the object should not be exported.\n");
        sb.append("   *\n");
        sb.append("   * @param obj\n");
        sb.append("   *          The object to be exported\n");
        sb.append("   * @return The object's qualified name, or <code>null</code> if the object is not to be exported\n");
        sb.append("   */\n");
        sb.append("  protected QualifiedName qualifiedName(final ").append(genModelUtil.instanceClassName(c.getType())).append(" obj) {\n");
        sb.append("    ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(c))).append('\n');
        if (c.getNaming() != null) {
          sb.append("    final Object name = ").append(codeGenerationX.javaExpression(c.getNaming(), ctx.clone("obj", c.getType()))).append(";\n");
          sb.append("    return name != null ? ");
          if (c.isQualifiedName()) {
            sb.append("getConverter().toQualifiedName(String.valueOf(name))");
          } else {
            sb.append("qualifyWithContainerName(obj, String.valueOf(name))");
          }
          sb.append(" : null;\n");
        } else {
          sb.append("    return ");
          if (c.isQualifiedName()) {
            sb.append("getConverter().toQualifiedName(getResolver().apply(obj))");
          } else {
            sb.append("qualifyWithContainerName(obj, getResolver().apply(obj))");
          }
          sb.append("; // \"name\" attribute by default\n");
        }
        sb.append("  }\n");
        sb.append('\n');
      }
    }
    sb.append("}\n");
    // CHECKSTYLE:CONSTANTS-ON
    return sb;
  }
}
