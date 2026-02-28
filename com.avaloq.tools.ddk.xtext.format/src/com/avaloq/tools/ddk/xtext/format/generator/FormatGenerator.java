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
package com.avaloq.tools.ddk.xtext.format.generator;

import static com.avaloq.tools.ddk.xtext.format.generator.FormatGeneratorUtil.getFormatterName;

import com.avaloq.tools.ddk.xtext.format.FormatConstants;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter;
import org.eclipse.xtext.xbase.compiler.ErrorSafeExtensions;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.TreeAppendableUtil;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

/**
 * Generates code from your model files on save.
 *
 * Currently this generator is not used at all.
 * This is a side effect of the usage of the org.eclipse.xtext.generator.generator.GeneratorFragment.GeneratorFragment,
 * which generates this file besides necessary contents for org.eclipse.xtext.builder.BuilderParticipant.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
public class FormatGenerator extends JvmModelGenerator {

  @Inject
  private TreeAppendableUtil _treeAppendableUtil;

  @Inject
  private ErrorSafeExtensions _errorSafeExtensions;

  public String getSingleCommentDocumentation(final EObject it, final ITreeAppendable appendable, final GeneratorConfig config) {
    final DocumentationAdapter adapter = IterableExtensions.<DocumentationAdapter>head(Iterables.<DocumentationAdapter>filter(it.eAdapters(), DocumentationAdapter.class));
    String documentation = null;
    if (adapter != null) {
      documentation = adapter.getDocumentation();
    }
    boolean isNullOrEmpty = documentation == null || documentation.isEmpty();
    if (!isNullOrEmpty) {
      return adapter.getDocumentation();
    }
    return null;
  }

  @Override
  protected ITreeAppendable _generateMember(final JvmField it, final ITreeAppendable appendable, final GeneratorConfig config) {
    appendable.newLine();
    final ITreeAppendable tracedAppendable = appendable.trace(it);
    generateAnnotations(it.getAnnotations(), tracedAppendable, true, config);
    generateModifier(it, tracedAppendable, config);
    _errorSafeExtensions.serializeSafely(it.getType(), "Object", tracedAppendable);
    tracedAppendable.append(" ");
    _treeAppendableUtil.traceSignificant(tracedAppendable, it).append(it.getSimpleName());
    generateInitialization(it, tracedAppendable, config);
    tracedAppendable.append(";");
    String documentation = getSingleCommentDocumentation(it, appendable, config);
    if (documentation != null && documentation.endsWith("\r\n")) {
      documentation = documentation.substring(0, documentation.length() - 2);
    }
    if (documentation != null) {
      tracedAppendable.append(" // ");
      tracedAppendable.append(documentation);
    }
    return tracedAppendable;
  }

  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    super.doGenerate(resource, fsa); // Generate the abstract formatter from inferred Jvm models.

    for (final FormatConfiguration model : Iterables.<FormatConfiguration>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), FormatConfiguration.class)) {
      String path = getFormatterName(model, "").replace(".", "/") + ".java";
      fsa.generateFile(path, FormatConstants.FORMATTER, generateSrc(model));
    }
  }

  public CharSequence generateSrc(final FormatConfiguration model) {
    final StringBuilder builder = new StringBuilder();
    builder.append("package ").append(Strings.skipLastToken(getFormatterName(model, ""), ".")).append(";\n");
    builder.append("\n");
    builder.append("/**\n");
    builder.append(" * The formatting configuration for ").append(Strings.lastToken(model.getTargetGrammar().getName(), ".")).append(".\n");
    builder.append(" */\n");
    builder.append("public class ").append(Strings.lastToken(getFormatterName(model, ""), ".")).append(" extends ").append(Strings.lastToken(getFormatterName(model, "Abstract"), ".")).append(" {\n");
    builder.append("  // TODO: Provide a correct implementation of getSLCommentRule() and getMLCommentRule() in this class\n");
    builder.append("}\n");
    return builder;
  }

  @Override
  public ITreeAppendable generateMember(final JvmMember it, final ITreeAppendable appendable, final GeneratorConfig config) {
    if (it instanceof JvmConstructor) {
      return _generateMember((JvmConstructor) it, appendable, config);
    } else if (it instanceof JvmOperation) {
      return _generateMember((JvmOperation) it, appendable, config);
    } else if (it instanceof JvmField jvmField) {
      return _generateMember(jvmField, appendable, config);
    } else if (it instanceof JvmDeclaredType) {
      return _generateMember((JvmDeclaredType) it, appendable, config);
    } else if (it != null) {
      return _generateMember(it, appendable, config);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + java.util.Arrays.asList(it, appendable, config).toString());
    }
  }
}
