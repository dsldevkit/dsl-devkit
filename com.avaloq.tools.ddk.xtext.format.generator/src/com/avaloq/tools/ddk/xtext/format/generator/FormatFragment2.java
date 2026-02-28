/**
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Group AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.format.generator;

import com.avaloq.tools.ddk.xtext.format.FormatStandaloneSetup;
import com.avaloq.tools.ddk.xtext.formatting.AbstractExtendedFormatter;
import com.avaloq.tools.ddk.xtext.formatting.DirectNodeModelStreamer;
import com.avaloq.tools.ddk.xtext.formatting.RegionNodeModelFormatter;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.eclipse.xtext.xtext.generator.model.ManifestAccess;
import org.eclipse.xtext.xtext.generator.model.TextFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.XtendFileAccess;
import org.eclipse.xtext.xtext.generator.util.BooleanGeneratorOption;

/**
 * MWE fragment for the format language.
 */
public class FormatFragment2 extends AbstractStubGeneratingFragment {

  @Inject
  private FileAccessFactory fileAccessFactory;

  @Inject
  private XtextGeneratorNaming xtextGeneratorNaming;

  @Inject
  private GrammarAccessExtensions grammarAccessExtensions;

  private static final String RUNTIME_PLUGIN = "com.avaloq.tools.ddk.xtext";

  private final BooleanGeneratorOption generateFormatStub = new BooleanGeneratorOption(true);

  public boolean isGenerateFormatStub() {
    return generateFormatStub.get();
  }

  public void setGenerateFormatStub(final boolean generateStub) {
    generateFormatStub.set(generateStub);
  }

  /**
   * Class-wide logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(FormatFragment2.class);

  /**
   * The model for the format resource.
   */
  private String baseFormatterClassName = AbstractExtendedFormatter.class.getName();

  /**
   * Set the super type / base class of the formatter.
   * @param baseFormatterClass the FQN of the base formatter
   */
  public void setBaseFormatterClassName(final String baseFormatterClass) {
    baseFormatterClassName = baseFormatterClass;
  }

  /**
   * Get the super type / base class of the formatter.
   * @return the FQN of the base formatter
   */
  public String getBaseFormatterClassName() {
    return baseFormatterClassName;
  }

  protected TypeReference getFormatterStub(final Grammar grammar) {
    return new TypeReference(xtextGeneratorNaming.getRuntimeBasePackage(grammar) + ".formatting." + GrammarUtil.getSimpleName(grammar) + "Formatter");
  }

  protected String getFormatStub(final Grammar grammar) {
    final String formatter = getFormatterStub(grammar).getPath();
    return formatter.substring(0, formatter.lastIndexOf('/') + 1) + GrammarUtil.getSimpleName(grammar) + ".format";
  }

  @Override
  public void generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("executing generate for " + getClass().getName());
    }

    new GuiceModuleAccess.BindingFactory()
        .addTypeToType(TypeReference.typeRef(IFormatter.class), new TypeReference(FormatGeneratorUtil.getFormatterName(getGrammar(), "")))
        .addTypeToType(TypeReference.typeRef(INodeModelFormatter.class), TypeReference.typeRef(RegionNodeModelFormatter.class))
        .addTypeToType(TypeReference.typeRef(INodeModelStreamer.class), TypeReference.typeRef(DirectNodeModelStreamer.class))
        .contributeTo(getLanguage().getRuntimeGenModule());

    ManifestAccess manifest = getProjectConfig().getRuntime().getManifest();
    if (manifest != null) {
      manifest.getRequiredBundles().add("org.eclipse.emf.ecore");
      manifest.getRequiredBundles().add(RUNTIME_PLUGIN);
    }
    ManifestAccess eclipsePluginManifest = getProjectConfig().getEclipsePlugin().getManifest();
    if (eclipsePluginManifest != null) {
      eclipsePluginManifest.getRequiredBundles().add(RUNTIME_PLUGIN);
    }
    ManifestAccess genericIdeManifest = getProjectConfig().getGenericIde().getManifest();
    if (genericIdeManifest != null) {
      genericIdeManifest.getRequiredBundles().add(RUNTIME_PLUGIN);
    }

    FormatStandaloneSetup.doSetup();
    doGenerateStubFiles();
  }

  protected void doGenerateStubFiles() {
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
    if (isGenerateFormatStub()) {
      final TextFileAccess formatFile = doGetFormatStubFile();
      if (formatFile != null) {
        formatFile.writeTo(getProjectConfig().getRuntime().getSrc());
      }
    }
  }

  protected XtendFileAccess doGetXtendStubFile() {
    final XtendFileAccess xtendFile = fileAccessFactory.createXtendFile(getFormatterStub(getGrammar()));
    xtendFile.setResourceSet(getLanguage().getResourceSet());

    xtendFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(TargetStringConcatenation builder) {
        builder.append("import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry");
        builder.newLine();
        builder.append("import java.util.List");
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
        builder.append(" extends ");
        builder.append(FormatGeneratorUtil.getFormatterName(getGrammar(), "Abstract"));
        builder.append(" {");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  @");
        builder.append(Inject.class, "  ");
        builder.append(" extension ");
        builder.append(grammarAccessExtensions.getGrammarAccess(getGrammar()), "  ");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  override executeCustomPostFormatAction(ExtendedLineEntry lineEntry, List<ExtendedLineEntry> previousEntries) {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return null");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("  override protected getMLCommentRule() {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return ML_COMMENTRule");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("  override protected getSLCommentRule() {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return SL_COMMENTRule");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("  override protected isUnformattedContent(String content) {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return false");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("}");
        builder.newLine();
      }
    });
    return xtendFile;
  }

  protected JavaFileAccess doGetJavaStubFile() {
    final JavaFileAccess javaFile = fileAccessFactory.createJavaFile(getFormatterStub(getGrammar()));
    javaFile.setResourceSet(getLanguage().getResourceSet());

    javaFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(TargetStringConcatenation builder) {
        builder.append("import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry;");
        builder.newLine();
        builder.append("import java.util.List;");
        builder.newLine();
        builder.newLine();
        builder.append("import org.eclipse.xtext.TerminalRule;");
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
        builder.append("public class ");
        builder.append(getFormatterStub(getGrammar()).getSimpleName());
        builder.append(" extends ");
        builder.append(FormatGeneratorUtil.getFormatterName(getGrammar(), "Abstract"));
        builder.append(" {");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  @");
        builder.append(Inject.class, "  ");
        builder.append(" ");
        builder.append(grammarAccessExtensions.getGrammarAccess(getGrammar()), "  ");
        builder.append(" grammarAccess;");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("  @Override");
        builder.newLine();
        builder.append("  protected boolean isUnformattedContent(String content) {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return false;");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("  @Override");
        builder.newLine();
        builder.append("  protected TerminalRule getSLCommentRule() {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return grammarAccess.getSL_COMMENTRule();");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("  @Override");
        builder.newLine();
        builder.append("  protected TerminalRule getMLCommentRule() {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return grammarAccess.getML_COMMENTRule();");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("  @Override");
        builder.newLine();
        builder.append("  public String executeCustomPostFormatAction(ExtendedLineEntry lineEntry, List<ExtendedLineEntry> previousEntries) {");
        builder.newLine();
        builder.append("    // TODO Auto-generated method stub");
        builder.newLine();
        builder.append("    return null;");
        builder.newLine();
        builder.append("  }");
        builder.newLine();
        builder.newLine();
        builder.append("}");
        builder.newLine();
      }
    });
    return javaFile;
  }

  protected TextFileAccess doGetFormatStubFile() {
    final TextFileAccess formatFile = fileAccessFactory.createTextFile(getFormatStub(getGrammar()));

    formatFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(TargetStringConcatenation builder) {
        builder.append("formatter for ");
        builder.append(getGrammar().getName());
        builder.newLineIfNotEmpty();
        builder.newLine();
      }
    });
    return formatFile;
  }
}
