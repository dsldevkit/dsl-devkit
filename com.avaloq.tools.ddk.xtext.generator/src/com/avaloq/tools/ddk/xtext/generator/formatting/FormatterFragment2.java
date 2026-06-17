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

package com.avaloq.tools.ddk.xtext.generator.formatting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.formatting.IFormatter;
import org.eclipse.xtext.formatting.INodeModelFormatter;
import org.eclipse.xtext.formatting.INodeModelStreamer;
import org.eclipse.xtext.xtext.generator.AbstractStubGeneratingFragment;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.XtendFileAccess;

import com.avaloq.tools.ddk.xtext.formatting.DirectNodeModelStreamer;
import com.avaloq.tools.ddk.xtext.formatting.RegionNodeModelFormatter;
import com.google.inject.Inject;

@SuppressWarnings("nls")
public class FormatterFragment2 extends AbstractStubGeneratingFragment {

  @Inject
  private FileAccessFactory fileAccessFactory;

  @Inject
  private XtextGeneratorNaming xtextGeneratorNaming;

  @Inject
  private GrammarAccessExtensions grammarAccessExtensions;

  /**
   * Class-wide logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(FormatterFragment2.class);

  protected TypeReference getFormatterStub(final Grammar grammar) {
    return new TypeReference(xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".formatting." + GrammarUtil.getSimpleName(grammar) + "Formatter");
  }

  @Override
  public void generate() {
    if (!isGenerateStub()) {
      return;
    }
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", getClass().getName()));
    }

    new GuiceModuleAccess.BindingFactory()
        .addTypeToType(TypeReference.typeRef(IFormatter.class), getFormatterStub(getLanguage().getGrammar()))
        .addTypeToType(TypeReference.typeRef(INodeModelFormatter.class), TypeReference.typeRef(RegionNodeModelFormatter.class))
        .addTypeToType(TypeReference.typeRef(INodeModelStreamer.class), TypeReference.typeRef(DirectNodeModelStreamer.class))
        .contributeTo(getLanguage().getRuntimeGenModule());
    if (getProjectConfig().getRuntime().getManifest() != null) {
      getProjectConfig().getRuntime().getManifest().getExportedPackages().add(xtextGeneratorNaming.getRuntimeBasePackage(getGrammar()) + ".formatting");
    }
    doGenerateStubFile();
  }

  protected void doGenerateStubFile() {
    if (!isGenerateStub()) {
      return;
    }
    if (isGenerateXtendStub()) {
      final XtendFileAccess xtendFile = doGetXtendStubFile();
      if (xtendFile != null) {
        xtendFile.writeTo(getProjectConfig().getRuntime().getSrc());
      }
    } else {
      final JavaFileAccess javaFile = doGetJavaStubFile();
      if (javaFile != null) {
        javaFile.writeTo(getProjectConfig().getRuntime().getSrc());
      }
    }
  }

  protected XtendFileAccess doGetXtendStubFile() {
    final XtendFileAccess xtendFile = fileAccessFactory.createXtendFile(getFormatterStub(getGrammar()));
    xtendFile.setResourceSet(getLanguage().getResourceSet());

    // CHECKSTYLE:CONSTANTS-OFF
    xtendFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation builder) {
        builder.append("import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter");
        builder.newLine();
        builder.append("import org.eclipse.xtext.formatting.impl.FormattingConfig");
        builder.newLine();
        builder.newLine();
        builder.append("/**");
        builder.newLine();
        builder.append(" * This class contains custom formatting declarations.");
        builder.newLine();
        builder.append(" *");
        builder.newLine();
        builder.append(" * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting");
        builder.newLine();
        builder.append(" * on how and when to use it.");
        builder.newLine();
        builder.append(" *");
        builder.newLine();
        builder.append(" * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example");
        builder.newLine();
        builder.append(" */");
        builder.newLine();
        builder.append("class ");
        builder.append(getFormatterStub(getGrammar()).getSimpleName());
        builder.append(" extends AbstractDeclarativeFormatter {");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  ");
        builder.append("@");
        builder.append(Inject.class, "  ");
        builder.append(" extension ");
        builder.append(grammarAccessExtensions.getGrammarAccess(getGrammar()), "  ");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  ");
        builder.append("override protected void configureFormatting(FormattingConfig c) {");
        builder.newLine();
        builder.append("// It's usually a good idea to activate the following three statements.");
        builder.newLine();
        builder.append("// They will add and preserve newlines around comments");
        builder.newLine();
        builder.append("//    c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)");
        builder.newLine();
        builder.append("//    c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)");
        builder.newLine();
        builder.append("//    c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)");
        builder.newLine();
        builder.append("  ");
        builder.append("}");
        builder.newLine();
        builder.append("}");
        builder.newLine();
      }
    });
    // CHECKSTYLE:CONSTANTS-ON
    return xtendFile;
  }

  protected JavaFileAccess doGetJavaStubFile() {
    final JavaFileAccess javaFile = fileAccessFactory.createJavaFile(getFormatterStub(getGrammar()));
    javaFile.setResourceSet(getLanguage().getResourceSet());

    // CHECKSTYLE:CONSTANTS-OFF
    javaFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation builder) {
        builder.append("import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;");
        builder.newLine();
        builder.append("import org.eclipse.xtext.formatting.impl.FormattingConfig;");
        builder.newLine();
        builder.newLine();
        builder.append("/**");
        builder.newLine();
        builder.append(" * This class contains custom formatting declarations.");
        builder.newLine();
        builder.append(" *");
        builder.newLine();
        builder.append(" * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting");
        builder.newLine();
        builder.append(" * on how and when to use it.");
        builder.newLine();
        builder.append(" *");
        builder.newLine();
        builder.append(" * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example");
        builder.newLine();
        builder.append(" */");
        builder.newLine();
        builder.append("class ");
        builder.append(getFormatterStub(getGrammar()).getSimpleName());
        builder.append(" extends AbstractDeclarativeFormatter {");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  ");
        builder.append("@");
        builder.append(Inject.class, "  ");
        builder.append(" ");
        builder.append(grammarAccessExtensions.getGrammarAccess(getGrammar()), "  ");
        builder.append(" grammarAccess;");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  ");
        builder.append("@Override");
        builder.newLine();
        builder.append("    ");
        builder.append("protected void configureFormatting(FormattingConfig c) {");
        builder.newLine();
        builder.append("// It's usually a good idea to activate the following three statements.");
        builder.newLine();
        builder.append("// They will add and preserve newlines around comments");
        builder.newLine();
        builder.append("//    c.setLinewrap(0, 1, 2).before(grammarAccess.getSL_COMMENTRule());");
        builder.newLine();
        builder.append("//    c.setLinewrap(0, 1, 2).before(grammarAccess.getML_COMMENTRule());");
        builder.newLine();
        builder.append("//    c.setLinewrap(0, 1, 1).after(grammarAccess.getML_COMMENTRule());");
        builder.newLine();
        builder.append("  ");
        builder.append("}");
        builder.newLine();
        builder.append("}");
        builder.newLine();
      }
    });
    // CHECKSTYLE:CONSTANTS-ON
    return javaFile;
  }

}
