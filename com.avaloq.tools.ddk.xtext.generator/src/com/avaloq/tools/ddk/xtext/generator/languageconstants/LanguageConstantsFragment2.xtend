/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.generator.languageconstants

import com.google.inject.Inject
import org.apache.log4j.Logger
import org.eclipse.osgi.util.NLS
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtext.xtext.generator.model.XtextGeneratorFileSystemAccess

class LanguageConstantsFragment2 extends AbstractXtextGeneratorFragment {

  @Inject FileAccessFactory fileAccessFactory
  @Inject extension XtextGeneratorNaming

  /** Class-wide logger. */
  val Logger LOGGER = Logger.getLogger(LanguageConstantsFragment2);

  /** Variables / Fragment Parameters. */
  var String preferredFileExtension

  /** The corresponding outlet. */
  var IFileSystemAccess2 metamodelSrcGenOutlet

  /**
   * An alternative implementation is to use
   * com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil.getFileExtensions(org.eclipse.xtext.Grammar).
   * However, we want to use the preferred file extension.
   *
   * @param fileExtension
   *          the preferred file extension
   */
  def void setPreferedFileExtension(String fileExtension) {
    this.preferredFileExtension = fileExtension
  }

  /**
   * Returns the preferred file extension. If not manually set, the
   * first item in {@link fileExtensions} is returned.
   *
   * @param grammar
   *          the grammar for which the preferred file extension applies
   * @return the preferred file extension
   */
  def String getPreferredFileExtension() {
    if (preferredFileExtension !== null) {
      return preferredFileExtension
    } else if (!language.fileExtensions.empty) {
      return language.fileExtensions.get(0);
    } else {
      return GrammarUtil.getSimpleName(grammar).toLowerCase();
    }
  }

  /**
   * Used to set outlet SRC_TEST or SRC_TEST_GEN - all other outlets are ignored.
   *
   * @param outlet
   *          the outlet
   */
  def void addOutlet(XtextGeneratorFileSystemAccess outlet) {
    metamodelSrcGenOutlet = outlet
  }

  def IFileSystemAccess2 getOutlet() {
    if (metamodelSrcGenOutlet !== null) {
      return metamodelSrcGenOutlet
    }
    return projectConfig.runtime.srcGen
  }

  override generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", class.name))
    }
    if (language.fileExtensions.size == 0) {
      LOGGER.error(NLS.bind("There must be at least one extension for {0}", grammar.name))
      return
    }
    doGenerateFiles()
  }

  def doGenerateFiles() {
    val constantsFile = doGetConstantsClassFile
    constantsFile?.writeTo(outlet)
  }

  def doGetConstantsClassFile() {
    val typeReference = grammar.getTypeReference("")
    val javaFile = fileAccessFactory.createJavaFile(typeReference)
    javaFile.resourceSet = language.resourceSet

    javaFile.content =
    '''
      package «typeReference.packageName»;

      /**
       * Provides language specific constants for «grammar.name».
       *
       * Theses constants are used e.g. by the test plug-ins.
       */
      @SuppressWarnings("nls")
      public final class «typeReference.simpleName» {
        public static final String GRAMMAR = "«grammar.name»";

        /** Preferred file extension (for testing). */
        public static final String FILE_EXTENSION = "«preferredFileExtension.replaceAll("%20"," ")»";
        /** All file extensions. */
        public static final String FILE_EXTENSIONS = "«language.fileExtensions.join(",")»";
        /** Private constant specifying an URI pattern that match all files of the preferred extension. */
        private static final String ALL_«grammar.name.toUpperCase()»_URI = "*."+FILE_EXTENSION;

        private «typeReference.simpleName»() {}

        /**
         * An URI pattern that matches all files of the preferred file extension.
         *
         * @return this pattern
         */
        public static final String getAll«grammar.name»URI() {
          return ALL_«grammar.name.toUpperCase()»_URI;
        }

      }
    '''

    return javaFile
  }

  /**
   * Returns the type reference of the Constants class.
   *
   * @param grammar
   *          the grammar
   * @param prefix
   *          the prefix (e.g. "Abstract", "Default", ...
   * @return the type reference
   */
  def TypeReference getTypeReference(Grammar grammar, String prefix) {
    return new TypeReference(grammar.runtimeBasePackage + "." + prefix + GrammarUtil.getSimpleName(grammar) + "Constants")
  }

}
