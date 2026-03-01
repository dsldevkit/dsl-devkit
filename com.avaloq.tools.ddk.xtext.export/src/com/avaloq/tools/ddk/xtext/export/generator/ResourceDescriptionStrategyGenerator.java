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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.UserData;
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.inject.Inject;


public class ResourceDescriptionStrategyGenerator {

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
    sb.append("package ").append(naming.toJavaPackage(exportGeneratorX.getResourceDescriptionStrategy(it))).append(";\n");
    sb.append('\n');
    sb.append("import java.util.Map;\n");
    sb.append("import java.util.Set;\n");
    sb.append('\n');
    sb.append("import org.eclipse.emf.ecore.EClass;\n");
    sb.append("import org.eclipse.emf.ecore.EObject;\n");
    sb.append("import org.eclipse.emf.ecore.EPackage;\n");
    sb.append("import org.eclipse.emf.ecore.resource.Resource;\n");
    sb.append("import org.eclipse.emf.ecore.util.Switch;\n");
    sb.append("import org.eclipse.xtext.resource.IEObjectDescription;\n");
    sb.append("import org.eclipse.xtext.util.IAcceptor;\n");
    sb.append('\n');
    sb.append("import com.avaloq.tools.ddk.xtext.resource.AbstractResourceDescriptionStrategy;\n");
    if (it.getExports().stream().anyMatch(e -> e.isFingerprint() || e.isResourceFingerprint())) {
      sb.append("import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;\n");
    }
    if (it.getExports().stream().anyMatch(e -> e.isLookup())) {
      sb.append("import com.avaloq.tools.ddk.xtext.resource.DetachableEObjectDescription;\n");
    }
    sb.append("import com.avaloq.tools.ddk.xtext.resource.extensions.AbstractForwardingResourceDescriptionStrategyMap;\n");
    sb.append("import com.google.common.collect.ImmutableMap;\n");
    sb.append("import com.google.common.collect.ImmutableSet;\n");

    final Collection<Export> types = it.getExports();
    sb.append('\n');
    sb.append('\n');
    sb.append("public class ").append(naming.toSimpleName(exportGeneratorX.getResourceDescriptionStrategy(it))).append(" extends AbstractResourceDescriptionStrategy {\n");
    sb.append('\n');

    // EXPORTED_ECLASSES
    sb.append("  private static final Set<EClass> EXPORTED_ECLASSES = ImmutableSet.copyOf(new EClass[] {\n");
    final Map<EClass, Export> e = exportGeneratorX.typeMap(types, exportGeneratorX.getGrammar(it));
    final List<EClass> sortedKeys = e.keySet().stream()
        .sorted((a, b) -> genModelUtil.literalIdentifier(a).compareTo(genModelUtil.literalIdentifier(b)))
        .collect(Collectors.toList());
    for (int i = 0; i < sortedKeys.size(); i++) {
      sb.append("    ").append(genModelUtil.literalIdentifier(sortedKeys.get(i)));
      if (i < sortedKeys.size() - 1) {
        sb.append(',');
      }
      sb.append('\n');
    }
    sb.append("  });\n");
    sb.append('\n');
    sb.append("  @Override\n");
    sb.append("  public Set<EClass> getExportedEClasses(final Resource resource) {\n");
    sb.append("    return EXPORTED_ECLASSES;\n");
    sb.append("  }\n");
    sb.append('\n');

    if (!types.isEmpty()) {
      sb.append("  private final ThreadLocal<IAcceptor<IEObjectDescription>> acceptor = new ThreadLocal<IAcceptor<IEObjectDescription>>();\n");
      sb.append('\n');

      final Set<EPackage> packageSet = types.stream()
          .filter(c -> !c.getType().isAbstract())
          .map(c -> c.getType().getEPackage())
          .collect(Collectors.toCollection(LinkedHashSet::new));
      final List<EPackage> sortedPackages = packageSet.stream()
          .sorted((a, b) -> a.getNsURI().compareTo(b.getNsURI()))
          .collect(Collectors.toList());

      for (EPackage p : sortedPackages) {
        sb.append("  private final Switch<Boolean> ").append(p.getName()).append("ExportSwitch = new ").append(genModelUtil.qualifiedSwitchClassName(p)).append("<Boolean>() {\n");
        sb.append('\n');
        sb.append("    @Override\n");
        sb.append("    public Boolean defaultCase(final EObject obj) {\n");
        sb.append("      return true;\n");
        sb.append("    }\n");

        final List<Export> exportsForPackage = types.stream()
            .filter(c -> !c.getType().isAbstract() && c.getType().getEPackage() == p)
            .collect(Collectors.toList());

        for (Export c : exportsForPackage) {
          sb.append('\n');
          sb.append("    ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(c))).append('\n');
          sb.append("    @Override\n");
          sb.append("    public Boolean case").append(c.getType().getName()).append("(final ").append(genModelUtil.instanceClassName(c.getType())).append(" obj) {\n");
          final String guard = codeGenerationX.javaExpression(c.getGuard(), ctx.clone("obj", c.getType()));
          if (c.getGuard() == null) {
            sb.append(generateCaseBody(it, c, ctx, genModelUtil));
          } else if (!"false".equalsIgnoreCase(guard)) {
            sb.append("      ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(c.getGuard()))).append('\n');
            sb.append("      if (").append(guard).append(") {\n");
            sb.append(generateCaseBody(it, c, ctx, genModelUtil));
            sb.append("      }\n");
          }
          sb.append('\n');

          // can Type contain any nested types ?
          final Set<String> nonAbstractTypeNames = types.stream()
              .map(t -> t.getType())
              .filter(t -> !t.isAbstract())
              .map(t -> t.getName())
              .collect(Collectors.toSet());
          sb.append("      // can ").append(c.getType().getName()).append(" contain any nested ").append(nonAbstractTypeNames).append(" objects ?\n");
          final Set<EClass> nonAbstractTypes = types.stream()
              .map(t -> t.getType())
              .filter(t -> !t.isAbstract())
              .collect(Collectors.toSet());
          sb.append("      return ").append(generatorUtilX.canContain(c.getType(), nonAbstractTypes, exportGeneratorX.getGrammar(it))).append(";\n");
          sb.append("    }\n");
        }
        sb.append("  };\n");
        sb.append('\n');
      }

      sb.append("  @Override\n");
      sb.append("  protected boolean doCreateEObjectDescriptions(final EObject object, final IAcceptor<IEObjectDescription> acceptor) {\n");
      sb.append("    try {\n");
      sb.append("      this.acceptor.set(acceptor);\n");
      sb.append("      final EPackage ePackage = object.eClass().getEPackage();\n");
      for (EPackage p : sortedPackages) {
        sb.append("      if (ePackage == ").append(genModelUtil.qualifiedPackageInterfaceName(p)).append(".eINSTANCE) {\n");
        sb.append("        return ").append(p.getName()).append("ExportSwitch.doSwitch(object);\n");
        sb.append("      }\n");
      }
      if (it.isExtension()) {
        sb.append("      // Extension does not have to cover all EPackages of the language\n");
        sb.append("      return false;\n");
      } else {
        sb.append("      // TODO: generate code for other possible epackages (as defined by grammar)\n");
        sb.append("      return true;\n");
      }
      sb.append("    } finally {\n");
      sb.append("      this.acceptor.set(null);\n");
      sb.append("    }\n");
      sb.append("  }\n");
      sb.append('\n');
    }
    sb.append("}\n");
    // CHECKSTYLE:CONSTANTS-ON
    return sb;
  }

  // CHECKSTYLE:CONSTANTS-OFF
  public CharSequence generateCaseBody(final ExportModel it, final Export c, final CompilationContext ctx, final GenModelUtilX genModelUtil) {
    final List<EAttribute> a = c.getAllEAttributes();
    final List<UserData> d = exportGeneratorX.allUserData(c);
    final StringBuilder sb = new StringBuilder(512);
    // CHECKSTYLE:CHECK-OFF BooleanExpressionComplexity
    if (!a.isEmpty() || !d.isEmpty() || c.isFingerprint() || c.isResourceFingerprint() || c.isLookup()) {
    // CHECKSTYLE:CHECK-ON BooleanExpressionComplexity
      sb.append("      // Use a forwarding map to delay calculation as much as possible; otherwise we may get recursive EObject resolution attempts\n");
      sb.append("      Map<String, String> data = new AbstractForwardingResourceDescriptionStrategyMap() {\n");
      sb.append('\n');
      sb.append("        @Override\n");
      sb.append("        protected void fill(final ImmutableMap.Builder<String, String> builder) {\n");
      sb.append("          Object value = null;\n");
      if (c.isFingerprint()) {
        sb.append("          // Fingerprint\n");
        sb.append("          value = getFingerprint(obj);\n");
        sb.append("          if (value != null) {\n");
        sb.append("            builder.put(IFingerprintComputer.OBJECT_FINGERPRINT, value.toString());\n");
        sb.append("          }\n");
      } else if (c.isResourceFingerprint()) {
        sb.append("          // Resource fingerprint\n");
        sb.append("          value = getFingerprint(obj);\n");
        sb.append("          if (value != null) {\n");
        sb.append("            builder.put(IFingerprintComputer.RESOURCE_FINGERPRINT, value.toString());\n");
        sb.append("          }\n");
      }
      if (c.isLookup()) {
        sb.append("          // Allow lookups\n");
        if (c.getLookupPredicate() != null) {
          sb.append("          ").append(generatorUtilX.javaContributorComment(generatorUtilX.location(c.getLookupPredicate()))).append('\n');
          sb.append("          if (").append(codeGenerationX.javaExpression(c.getLookupPredicate(), ctx.clone("obj", c.getType()))).append(") {\n");
          sb.append("            builder.put(DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());\n");
          sb.append("          }\n");
        } else {
          sb.append("          builder.put(DetachableEObjectDescription.ALLOW_LOOKUP, Boolean.TRUE.toString());\n");
        }
      }
      if (!a.isEmpty()) {
        sb.append("          // Exported attributes\n");
        for (EAttribute attr : a) {
          sb.append("          value = obj.eGet(").append(genModelUtil.literalIdentifier(attr)).append(", false);\n");
          sb.append("          if (value != null) {\n");
          sb.append("            builder.put(").append(naming.toSimpleName(exportGeneratorX.getResourceDescriptionConstants(it))).append('.').append(exportGeneratorX.constantName(attr, c.getType())).append(", value.toString());\n");
          sb.append("          }\n");
        }
      }
      if (!d.isEmpty()) {
        sb.append("          // User data\n");
        for (UserData data : d) {
          sb.append("          value = ").append(codeGenerationX.javaExpression(data.getExpr(), ctx.clone("obj", c.getType()))).append(";\n");
          sb.append("          if (value != null) {\n");
          sb.append("            builder.put(").append(naming.toSimpleName(exportGeneratorX.getResourceDescriptionConstants(it))).append('.').append(exportGeneratorX.constantName(data, c.getType())).append(", value.toString());\n");
          sb.append("          }\n");
        }
      }
      sb.append("        }\n");
      sb.append("      };\n");
      sb.append("      acceptEObjectDescription(obj, data, acceptor.get());\n");
    } else {
      sb.append("      acceptEObjectDescription(obj, acceptor.get());\n");
    }
    return sb;
  }
  // CHECKSTYLE:CONSTANTS-ON
}
