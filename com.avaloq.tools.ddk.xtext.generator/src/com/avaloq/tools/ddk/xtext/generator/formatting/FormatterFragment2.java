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

import com.avaloq.tools.ddk.xtext.formatting.DirectNodeModelStreamer;
import com.avaloq.tools.ddk.xtext.formatting.RegionNodeModelFormatter;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.osgi.util.NLS;
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
import org.eclipse.xtend2.lib.StringConcatenationClient;

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

  // CHECKSTYLE:CONSTANTS-OFF
  protected TypeReference getFormatterStub(final Grammar grammar) {
    return new TypeReference(xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".formatting." + GrammarUtil.getSimpleName(grammar) + "Formatter");
  }
  // CHECKSTYLE:CONSTANTS-ON

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

    final String stubSimpleName = getFormatterStub(getGrammar()).getSimpleName();
    final TypeReference grammarAccessRef = grammarAccessExtensions.getGrammarAccess(getGrammar());

    // CHECKSTYLE:CONSTANTS-OFF
    xtendFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter");
        target.newLineIfNotEmpty();
        target.append("import org.eclipse.xtext.formatting.impl.FormattingConfig");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("/**");
        target.newLineIfNotEmpty();
        target.append(" * This class contains custom formatting declarations.");
        target.newLineIfNotEmpty();
        target.append(" *");
        target.newLineIfNotEmpty();
        target.append(" * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting");
        target.newLineIfNotEmpty();
        target.append(" * on how and when to use it.");
        target.newLineIfNotEmpty();
        target.append(" *");
        target.newLineIfNotEmpty();
        target.append(" * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example");
        target.newLineIfNotEmpty();
        target.append(" */");
        target.newLineIfNotEmpty();
        target.append("class " + stubSimpleName + " extends AbstractDeclarativeFormatter {");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("  @");
        target.append(TypeReference.typeRef(Inject.class));
        target.append(" extension ");
        target.append(grammarAccessRef);
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("  override protected void configureFormatting(FormattingConfig c) {");
        target.newLineIfNotEmpty();
        target.append("// It's usually a good idea to activate the following three statements.");
        target.newLineIfNotEmpty();
        target.append("// They will add and preserve newlines around comments");
        target.newLineIfNotEmpty();
        target.append("//    c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)");
        target.newLineIfNotEmpty();
        target.append("//    c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)");
        target.newLineIfNotEmpty();
        target.append("//    c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)");
        target.newLineIfNotEmpty();
        target.append("  }");
        target.newLineIfNotEmpty();
        target.append("}");
        target.newLineIfNotEmpty();
      }
    });
    // CHECKSTYLE:CONSTANTS-ON
    return xtendFile;
  }

  protected JavaFileAccess doGetJavaStubFile() {
    final JavaFileAccess javaFile = fileAccessFactory.createJavaFile(getFormatterStub(getGrammar()));
    javaFile.setResourceSet(getLanguage().getResourceSet());

    final String stubSimpleName = getFormatterStub(getGrammar()).getSimpleName();
    final TypeReference grammarAccessRef = grammarAccessExtensions.getGrammarAccess(getGrammar());

    // CHECKSTYLE:CONSTANTS-OFF
    javaFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;");
        target.newLineIfNotEmpty();
        target.append("import org.eclipse.xtext.formatting.impl.FormattingConfig;");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("/**");
        target.newLineIfNotEmpty();
        target.append(" * This class contains custom formatting declarations.");
        target.newLineIfNotEmpty();
        target.append(" *");
        target.newLineIfNotEmpty();
        target.append(" * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting");
        target.newLineIfNotEmpty();
        target.append(" * on how and when to use it.");
        target.newLineIfNotEmpty();
        target.append(" *");
        target.newLineIfNotEmpty();
        target.append(" * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example");
        target.newLineIfNotEmpty();
        target.append(" */");
        target.newLineIfNotEmpty();
        target.append("class " + stubSimpleName + " extends AbstractDeclarativeFormatter {");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("  @");
        target.append(TypeReference.typeRef(Inject.class));
        target.append(" ");
        target.append(grammarAccessRef);
        target.append(" grammarAccess;");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("  @Override");
        target.newLineIfNotEmpty();
        target.append("    protected void configureFormatting(FormattingConfig c) {");
        target.newLineIfNotEmpty();
        target.append("// It's usually a good idea to activate the following three statements.");
        target.newLineIfNotEmpty();
        target.append("// They will add and preserve newlines around comments");
        target.newLineIfNotEmpty();
        target.append("//    c.setLinewrap(0, 1, 2).before(grammarAccess.getSL_COMMENTRule());");
        target.newLineIfNotEmpty();
        target.append("//    c.setLinewrap(0, 1, 2).before(grammarAccess.getML_COMMENTRule());");
        target.newLineIfNotEmpty();
        target.append("//    c.setLinewrap(0, 1, 1).after(grammarAccess.getML_COMMENTRule());");
        target.newLineIfNotEmpty();
        target.append("  }");
        target.newLineIfNotEmpty();
        target.append("}");
        target.newLineIfNotEmpty();
      }
    });
    // CHECKSTYLE:CONSTANTS-ON
    return javaFile;
  }

}
