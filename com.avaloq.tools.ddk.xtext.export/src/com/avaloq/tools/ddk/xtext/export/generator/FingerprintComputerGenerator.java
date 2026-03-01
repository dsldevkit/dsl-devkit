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
import org.eclipse.emf.ecore.EPackage;

import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Interface;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceExpression;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceField;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceItem;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation;
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.inject.Inject;


@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class FingerprintComputerGenerator {

  @Inject
  private CodeGenerationX codeGenerationX;
  @Inject
  private GeneratorUtilX generatorUtilX;
  @Inject
  private Naming naming;
  @Inject
  private ExportGeneratorX exportGeneratorX;

  public CharSequence generate(final ExportModel it, final CompilationContext ctx, final GenModelUtilX genModelUtil) {
    // CHECKSTYLE:CONSTANTS-OFF
    final StringBuilder sb = new StringBuilder(2048);
    sb.append("package ").append(naming.toJavaPackage(exportGeneratorX.getFingerprintComputer(it))).append(";\n");
    sb.append('\n');
    sb.append("import org.eclipse.emf.ecore.EObject;\n");
    if (!it.getInterfaces().isEmpty()) {
      sb.append("import org.eclipse.emf.ecore.EPackage;\n");
      sb.append("import org.eclipse.emf.ecore.util.Switch;\n");
    }
    sb.append('\n');
    sb.append("import com.avaloq.tools.ddk.xtext.resource.AbstractStreamingFingerprintComputer;\n");
    sb.append('\n');
    sb.append("import com.google.common.hash.Hasher;\n");
    sb.append('\n');
    sb.append('\n');
    sb.append("public class ").append(naming.toSimpleName(exportGeneratorX.getFingerprintComputer(it))).append(" extends AbstractStreamingFingerprintComputer {\n");
    sb.append('\n');
    if (it.getInterfaces().isEmpty()) {
      sb.append("  // no fingerprint defined\n");
      sb.append("  @Override\n");
      sb.append("  public String computeFingerprint(final org.eclipse.emf.ecore.resource.Resource resource) {\n");
      sb.append("    return null;\n");
      sb.append("  }\n");
      sb.append('\n');
    }
    sb.append("  private final ThreadLocal<Hasher> hasherAccess = new ThreadLocal<Hasher>();\n");
    sb.append('\n');

    final Set<EPackage> packages = it.getInterfaces().stream()
        .map(f -> f.getType().getEPackage())
        .collect(Collectors.toCollection(java.util.LinkedHashSet::new));
    final List<EPackage> sortedPackages = packages.stream()
        .sorted((a, b) -> a.getNsURI().compareTo(b.getNsURI()))
        .collect(Collectors.toList());

    for (EPackage p : sortedPackages) {
      sb.append("  private final Switch<Hasher> ").append(p.getName()).append("Switch = new ").append(genModelUtil.qualifiedSwitchClassName(p)).append("<Hasher>() {\n");
      final List<Interface> interfacesForPackage = it.getInterfaces().stream()
          .filter(f -> f.getType().getEPackage() == p)
          .collect(Collectors.toList());
      for (Interface f : interfacesForPackage) {
        sb.append('\n');
        sb.append("    ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(f))).append('\n');
        sb.append("    @Override\n");
        sb.append("    public Hasher case").append(f.getType().getName()).append("(final ").append(genModelUtil.instanceClassName(f.getType())).append(" obj) {\n");
        sb.append("      final Hasher hasher = hasherAccess.get();\n");
        if (f.getGuard() != null) {
          sb.append("      if (!(").append(codeGenerationX.javaExpression(f.getGuard(), ctx.clone("obj", f.getType()))).append(")) {\n");
          sb.append("        return hasher;\n");
          sb.append("      }\n");
        }
        sb.append("      hasher.putUnencodedChars(obj.eClass().getName()).putChar(ITEM_SEP);\n");
        final List<Interface> superFPs = exportGeneratorX.getSuperInterfaces(f, f.getType());
        for (Interface superFingerprint : superFPs) {
          for (InterfaceItem superItem : superFingerprint.getItems()) {
            sb.append("      ").append(doProfile(superItem, ctx, genModelUtil, superFingerprint.getType()));
          }
        }
        for (InterfaceItem item : f.getItems()) {
          sb.append("      ").append(doProfile(item, ctx, genModelUtil, f.getType()));
        }
        sb.append("      return hasher;\n");
        sb.append("    }\n");
      }
      sb.append("  };\n");
      sb.append('\n');
    }

    sb.append("  @Override\n");
    sb.append("  protected void fingerprint(final EObject object, final Hasher hasher) {\n");
    sb.append("    hasherAccess.set(hasher);\n");
    if (!it.getInterfaces().isEmpty()) {
      sb.append("    final EPackage ePackage = object.eClass().getEPackage();\n");
      for (EPackage p : sortedPackages) {
        sb.append("    if (ePackage == ").append(genModelUtil.qualifiedPackageInterfaceName(p)).append(".eINSTANCE) {\n");
        sb.append("      ").append(p.getName()).append("Switch.doSwitch(object);\n");
        sb.append("    }\n");
      }
    }
    sb.append("    hasherAccess.set(null);\n");
    sb.append("  }\n");
    sb.append("}\n");
    // CHECKSTYLE:CONSTANTS-ON
    return sb;
  }

  /**
   * Public dispatcher for doProfile.
   *
   * @param it
   *          the interface item
   * @param ctx
   *          the compilation context
   * @param genModelUtil
   *          the gen model utility
   * @param type
   *          the EClass type
   * @return the generated profile code
   */
  public CharSequence doProfile(final InterfaceItem it, final CompilationContext ctx, final GenModelUtilX genModelUtil, final EClass type) {
    if (it instanceof InterfaceExpression interfaceExpression) {
      return _doProfile(interfaceExpression, ctx, genModelUtil, type);
    } else if (it instanceof InterfaceField interfaceField) {
      return _doProfile(interfaceField, ctx, genModelUtil, type);
    } else if (it instanceof InterfaceNavigation interfaceNavigation) {
      return _doProfile(interfaceNavigation, ctx, genModelUtil, type);
    } else {
      return _doProfileDefault(it, ctx, genModelUtil, type);
    }
  }

  protected CharSequence _doProfileDefault(final InterfaceItem it, final CompilationContext ctx, final GenModelUtilX genModelUtil, final EClass type) {
    return "ERROR" + it.toString() + " " + generatorUtilX.javaContributorComment(generatorUtilX.location(it));
  }

  // CHECKSTYLE:CONSTANTS-OFF
  protected CharSequence _doProfile(final InterfaceField it, final CompilationContext ctx, final GenModelUtilX genModelUtil, final EClass type) {
    final StringBuilder sb = new StringBuilder(128);
    if (it.getField().isMany() && it.isUnordered()) {
      sb.append("fingerprintFeature(obj, ").append(genModelUtil.literalIdentifier(it.getField())).append(", FingerprintOrder.UNORDERED, hasher);\n");
    } else {
      sb.append("fingerprintFeature(obj, ").append(genModelUtil.literalIdentifier(it.getField())).append(", hasher);\n");
    }
    sb.append("hasher.putChar(ITEM_SEP);\n");
    return sb;
  }

  protected CharSequence _doProfile(final InterfaceNavigation it, final CompilationContext ctx, final GenModelUtilX genModelUtil, final EClass type) {
    final StringBuilder sb = new StringBuilder(128);
    if (it.getRef().isMany() && it.isUnordered()) {
      sb.append("fingerprintRef(obj, ").append(genModelUtil.literalIdentifier(it.getRef())).append(", FingerprintOrder.UNORDERED, hasher);\n");
    } else {
      sb.append("fingerprintRef(obj, ").append(genModelUtil.literalIdentifier(it.getRef())).append(", hasher);\n");
    }
    sb.append("hasher.putChar(ITEM_SEP);\n");
    return sb;
  }

  protected CharSequence _doProfile(final InterfaceExpression it, final CompilationContext ctx, final GenModelUtilX genModelUtil, final EClass type) {
    final StringBuilder sb = new StringBuilder(128);
    sb.append("fingerprintExpr(").append(codeGenerationX.javaExpression(it.getExpr(), ctx.clone("obj", type)))
        .append(", obj, FingerprintOrder.").append(it.isUnordered() ? "UNORDERED" : "ORDERED")
        .append(", FingerprintIndirection.").append(it.isRef() ? "INDIRECT" : "DIRECT")
        .append(", hasher);\n");
    sb.append("hasher.putChar(ITEM_SEP);\n");
    return sb;
  }
  // CHECKSTYLE:CONSTANTS-ON
}
