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

package com.avaloq.tools.ddk.xtext.generator.languageconstants;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.IXtextGeneratorFileSystemAccess;
import org.eclipse.xtext.xtext.generator.model.JavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.XtextGeneratorFileSystemAccess;
import org.eclipse.xtend2.lib.StringConcatenationClient;

public class LanguageConstantsFragment2 extends AbstractXtextGeneratorFragment {

  @Inject
  private FileAccessFactory fileAccessFactory;

  @Inject
  private XtextGeneratorNaming xtextGeneratorNaming;

  /** Class-wide logger. */
  private static final Logger LOGGER = LogManager.getLogger(LanguageConstantsFragment2.class);

  /**
   * An alternative implementation is to use
   * com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil.getFileExtensions(org.eclipse.xtext.Grammar).
   * However, we want to use the preferred file extension.
   */
  private String preferredFileExtension;

  private String metamodelSrcGenPath;

  private IXtextGeneratorFileSystemAccess metamodelSrcGen;

  public void setPreferredFileExtension(final String preferredFileExtension) {
    this.preferredFileExtension = preferredFileExtension;
  }

  public String getMetamodelSrcGenPath() {
    return metamodelSrcGenPath;
  }

  public void setMetamodelSrcGenPath(final String metamodelSrcGenPath) {
    this.metamodelSrcGenPath = metamodelSrcGenPath;
  }

  protected IXtextGeneratorFileSystemAccess getMetamodelSrcGen() {
    return metamodelSrcGen;
  }

  /**
   * Returns the preferred file extension. If not manually set, the
   * first item in {@link #getFileExtensions() fileExtensions} is returned.
   *
   * @return the preferred file extension
   */
  public String getPreferredFileExtension() {
    if (preferredFileExtension != null) {
      return preferredFileExtension;
    } else if (!getLanguage().getFileExtensions().isEmpty()) {
      return getLanguage().getFileExtensions().get(0);
    } else {
      return GrammarUtil.getSimpleName(getGrammar()).toLowerCase();
    }
  }

  @Override
  public void generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", getClass().getName()));
    }
    if (getLanguage().getFileExtensions().isEmpty()) {
      LOGGER.error(NLS.bind("There must be at least one extension for {0}", getGrammar().getName()));
      return;
    }
    doGenerateFiles();
  }

  public void doGenerateFiles() {
    final JavaFileAccess constantsFile = doGetConstantsClassFile();
    if (constantsFile != null) {
      constantsFile.writeTo(getMetamodelSrcGen());
    }
  }

  // CHECKSTYLE:CONSTANTS-OFF
  public JavaFileAccess doGetConstantsClassFile() {
    final TypeReference typeReference = getTypeReference(getGrammar());
    final JavaFileAccess javaFile = fileAccessFactory.createJavaFile(typeReference);
    javaFile.setResourceSet(getLanguage().getResourceSet());

    final String grammarName = getGrammar().getName();
    final String simpleName = typeReference.getSimpleName();
    final String preferredExt = getPreferredFileExtension().replaceAll("%20", " ");
    final String allExtensions = String.join(",", getLanguage().getFileExtensions());
    final String grammarSimpleName = GrammarUtil.getSimpleName(getGrammar());

    javaFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("/**");
        target.newLineIfNotEmpty();
        target.append(" * Provides language specific constants for " + grammarName + ".");
        target.newLineIfNotEmpty();
        target.append(" *");
        target.newLineIfNotEmpty();
        target.append(" * Theses constants are used e.g. by the test plug-ins.");
        target.newLineIfNotEmpty();
        target.append(" */");
        target.newLineIfNotEmpty();
        target.append("@SuppressWarnings(\"nls\")");
        target.newLineIfNotEmpty();
        target.append("public final class " + simpleName + " {");
        target.newLineIfNotEmpty();
        target.append("  public static final String GRAMMAR = \"" + grammarName + "\";");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("  /** Preferred file extension (for testing). */");
        target.newLineIfNotEmpty();
        target.append("  public static final String FILE_EXTENSION = \"" + preferredExt + "\";");
        target.newLineIfNotEmpty();
        target.append("  /** All file extensions. */");
        target.newLineIfNotEmpty();
        target.append("  public static final String FILE_EXTENSIONS = \"" + allExtensions + "\";");
        target.newLineIfNotEmpty();
        target.append("  /** Private constant specifying an URI pattern that match all files of the preferred extension. */");
        target.newLineIfNotEmpty();
        target.append("  private static final String ALL_" + grammarSimpleName.toUpperCase() + "_URI = \"*.\"+FILE_EXTENSION;");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("  private " + simpleName + "() {}");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("  /**");
        target.newLineIfNotEmpty();
        target.append("   * An URI pattern that matches all files of the preferred file extension.");
        target.newLineIfNotEmpty();
        target.append("   *");
        target.newLineIfNotEmpty();
        target.append("   * @return this pattern");
        target.newLineIfNotEmpty();
        target.append("   */");
        target.newLineIfNotEmpty();
        target.append("  public static final String getAll" + grammarSimpleName + "URI() {");
        target.newLineIfNotEmpty();
        target.append("    return ALL_" + grammarSimpleName.toUpperCase() + "_URI;");
        target.newLineIfNotEmpty();
        target.append("  }");
        target.newLineIfNotEmpty();
        target.newLineIfNotEmpty();
        target.append("}");
        target.newLineIfNotEmpty();
      }
    });

    return javaFile;
  }
  // CHECKSTYLE:CONSTANTS-ON

  /**
   * Returns the type reference of the Constants class.
   *
   * @param grammar
   *          the grammar
   * @return the type reference
   */
  public TypeReference getTypeReference(final Grammar grammar) {
    return new TypeReference(xtextGeneratorNaming.getRuntimeBasePackage(grammar) + "." + GrammarUtil.getSimpleName(grammar) + "Constants");
  }

  @Override
  public void initialize(final Injector injector) {
    super.initialize(injector);
    if (metamodelSrcGenPath != null && !metamodelSrcGenPath.isEmpty()) {
      metamodelSrcGen = new XtextGeneratorFileSystemAccess(metamodelSrcGenPath, true);
      metamodelSrcGen.initialize(injector);
    } else {
      metamodelSrcGen = getProjectConfig().getRuntime().getSrcGen();
    }
  }

}
