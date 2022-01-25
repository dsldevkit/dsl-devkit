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
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager;
import org.eclipse.osgi.util.NLS
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.GrammarUtil
import static extension org.eclipse.xtext.GrammarUtil.*
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.xtext.generator.model.XtextGeneratorFileSystemAccess
import com.google.inject.Injector
import org.eclipse.xtext.xtext.generator.model.IXtextGeneratorFileSystemAccess

class LanguageConstantsFragment2 extends AbstractXtextGeneratorFragment {

  @Inject FileAccessFactory fileAccessFactory
  @Inject extension XtextGeneratorNaming

  /** Class-wide logger. */
  val static Logger LOGGER = LogManager.getLogger(LanguageConstantsFragment2);

  /**
   * An alternative implementation is to use
   * com.avaloq.tools.ddk.xtext.generator.util.GeneratorUtil.getFileExtensions(org.eclipse.xtext.Grammar).
   * However, we want to use the preferred file extension.
   */
  @Accessors(PUBLIC_SETTER) String preferredFileExtension

  @Accessors String metamodelSrcGenPath

  @Accessors(PROTECTED_GETTER) IXtextGeneratorFileSystemAccess metamodelSrcGen

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
    constantsFile?.writeTo(getMetamodelSrcGen)
  }

  def doGetConstantsClassFile() {
    val typeReference = grammar.typeReference
    val javaFile = fileAccessFactory.createJavaFile(typeReference)
    javaFile.resourceSet = language.resourceSet

    javaFile.content =
    '''
      /**
       * Provides language specific constants for «grammar.name».
       *
       * Theses constants are used e.g. by the test plug-ins.
       */
      @SuppressWarnings("nls")
      public final class «typeReference.simpleName» {
        public static final String GRAMMAR = "«grammar.name»";

        /** Preferred file extension (for testing). */
        public static final String FILE_EXTENSION = "«getPreferredFileExtension.replaceAll("%20"," ")»";
        /** All file extensions. */
        public static final String FILE_EXTENSIONS = "«language.fileExtensions.join(",")»";
        /** Private constant specifying an URI pattern that match all files of the preferred extension. */
        private static final String ALL_«grammar.simpleName.toUpperCase()»_URI = "*."+FILE_EXTENSION;

        private «typeReference.simpleName»() {}

        /**
         * An URI pattern that matches all files of the preferred file extension.
         *
         * @return this pattern
         */
        public static final String getAll«grammar.simpleName»URI() {
          return ALL_«grammar.simpleName.toUpperCase()»_URI;
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
   * @return the type reference
   */
  def TypeReference getTypeReference(Grammar grammar) {
    return new TypeReference(grammar.runtimeBasePackage + "." + GrammarUtil.getSimpleName(grammar) + "Constants")
  }

  override initialize(Injector injector) {
    super.initialize(injector)
    if (!metamodelSrcGenPath.isNullOrEmpty) {
      metamodelSrcGen = new XtextGeneratorFileSystemAccess(metamodelSrcGenPath, true)
      metamodelSrcGen.initialize(injector)
    } else {
      metamodelSrcGen = projectConfig.runtime.srcGen
    }
  }

}
