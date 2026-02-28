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
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Grammar;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.common.collect.ListMultimap;
import com.google.inject.Inject;


public class FragmentProviderGenerator {

  @Inject
  private GeneratorUtilX generatorUtilX;
  @Inject
  private Naming naming;
  @Inject
  private ExportGeneratorX exportGeneratorX;

  public CharSequence generate(final ExportModel it, final CompilationContext ctx, final GenModelUtilX genModelUtil) {
    final Grammar grammar = exportGeneratorX.getGrammar(it);
    final List<Export> fingerprintedExports = it.getExports().stream()
        .filter(e -> e.isFingerprint() && e.getFragmentAttribute() != null)
        .collect(Collectors.toList());
    final StringBuilder sb = new StringBuilder();
    sb.append("package ").append(naming.toJavaPackage(exportGeneratorX.getFragmentProvider(it))).append(";\n");
    sb.append("\n");
    if (!fingerprintedExports.isEmpty()) {
      sb.append("import org.eclipse.emf.ecore.EClass;\n");
    }
    if (!fingerprintedExports.isEmpty() || it.isExtension()) {
      sb.append("import org.eclipse.emf.ecore.EObject;\n");
    }
    if (!fingerprintedExports.isEmpty()) {
      sb.append("import org.eclipse.emf.ecore.EPackage;\n");
    }
    sb.append("\n");
    sb.append("import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProvider;\n");
    sb.append("\n");
    sb.append("\n");
    sb.append("public class ").append(naming.toSimpleName(exportGeneratorX.getFragmentProvider(it))).append(" extends AbstractSelectorFragmentProvider {\n");
    sb.append("\n");
    if (!fingerprintedExports.isEmpty()) {
      sb.append("  @Override\n");
      sb.append("  public boolean appendFragmentSegment(final EObject object, StringBuilder builder) {\n");
      sb.append("    EClass eClass = object.eClass();\n");
      sb.append("    EPackage ePackage = eClass.getEPackage();\n");
      final Map<EClass, Export> typeMap = exportGeneratorX.typeMap(fingerprintedExports, grammar);
      final ListMultimap<EPackage, Export> sortedExportsMap = exportGeneratorX.sortedExportsByEPackage(fingerprintedExports);
      for (EPackage p : sortedExportsMap.keySet()) {
        sb.append("    if (ePackage == ").append(genModelUtil.qualifiedPackageInterfaceName(p)).append(".eINSTANCE) {\n");
        sb.append("      int classifierID = eClass.getClassifierID();\n");
        sb.append("      switch (classifierID) {\n");
        for (EClassifier classifier : p.getEClassifiers()) {
          if (classifier instanceof EClass c) {
            if (fingerprintedExports.stream().map(Export::getType).anyMatch(e -> e.isSuperTypeOf(c))) {
              final Export e = typeMap.get(c);
              sb.append("      ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(e))).append("\n");
              sb.append("      case ").append(genModelUtil.classifierIdLiteral(c)).append(": {\n");
              sb.append("        return appendFragmentSegment((").append(genModelUtil.instanceClassName(c)).append(") object, builder);\n");
              sb.append("      }\n");
            }
          }
        }
        sb.append("      default:\n");
        sb.append("        return super.appendFragmentSegment(object, builder);\n");
        sb.append("      }\n");
        sb.append("    }\n");
      }
      sb.append("    return super.appendFragmentSegment(object, builder);\n");
      sb.append("  }\n");
    }
    sb.append("\n");
    if (it.isExtension()) {
      sb.append("  @Override\n");
      sb.append("  protected boolean appendFragmentSegmentFallback(final EObject object, StringBuilder builder) {\n");
      sb.append("    // For export extension we must return false, so the logic will try other extensions\n");
      sb.append("    return false;\n");
      sb.append("  }\n");
      sb.append("\n");
    }
    for (Export e : fingerprintedExports) {
      sb.append("  protected boolean appendFragmentSegment(final ").append(genModelUtil.instanceClassName(e.getType())).append(" obj, StringBuilder builder) {\n");
      sb.append("    return computeSelectorFragmentSegment(obj, ").append(genModelUtil.literalIdentifier(e.getFragmentAttribute())).append(", ").append(e.isFragmentUnique()).append(", builder);\n");
      sb.append("  }\n");
      sb.append("\n");
    }
    sb.append("}\n");
    return sb;
  }
}
