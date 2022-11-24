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

package com.avaloq.tools.ddk.xtext.generator.formatting

import com.avaloq.tools.ddk.xtext.formatting.DirectNodeModelStreamer
import com.avaloq.tools.ddk.xtext.formatting.RegionNodeModelFormatter
import com.google.inject.Inject
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager;
import org.eclipse.osgi.util.NLS
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.formatting.IFormatter
import org.eclipse.xtext.formatting.INodeModelFormatter
import org.eclipse.xtext.formatting.INodeModelStreamer
import org.eclipse.xtext.xtext.generator.AbstractStubGeneratingFragment
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory
import org.eclipse.xtext.xtext.generator.model.GuiceModuleAccess
import org.eclipse.xtext.xtext.generator.model.TypeReference

import static org.eclipse.xtext.GrammarUtil.*

import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*

class FormatterFragment2 extends AbstractStubGeneratingFragment {

  @Inject FileAccessFactory fileAccessFactory
  @Inject extension XtextGeneratorNaming
  @Inject extension GrammarAccessExtensions

  /**
   * Class-wide logger.
   */
  static final Logger LOGGER = LogManager::getLogger(typeof(FormatterFragment2))

  protected def TypeReference getFormatterStub(Grammar grammar) {
    new TypeReference(grammar.runtimeBasePackage + '.formatting.' + getSimpleName(grammar) + 'Formatter')
  }

  override generate() {
    if (!isGenerateStub()) {
      return;
    }
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info(NLS.bind("executing generate for {0}", getClass().getName()));
    }

    new GuiceModuleAccess.BindingFactory()
    .addTypeToType(IFormatter.typeRef, language.grammar.formatterStub)
    .addTypeToType(INodeModelFormatter.typeRef, RegionNodeModelFormatter.typeRef)
    .addTypeToType(INodeModelStreamer.typeRef, DirectNodeModelStreamer.typeRef)
    .contributeTo(language.runtimeGenModule)
    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest.exportedPackages += grammar.runtimeBasePackage + '.formatting'
    }
    doGenerateStubFile()
  }

  protected def doGenerateStubFile() {
    if(!isGenerateStub) {
      return
    }
    if (isGenerateXtendStub) {
      val xtendFile = doGetXtendStubFile
      xtendFile?.writeTo(projectConfig.runtime.src)
    } else {
      val javaFile = doGetJavaStubFile
      javaFile?.writeTo(projectConfig.runtime.src)
    }
  }

  protected def doGetXtendStubFile() {
    val xtendFile = fileAccessFactory.createXtendFile(grammar.formatterStub)
    xtendFile.resourceSet = language.resourceSet

    xtendFile.content = '''
      import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
      import org.eclipse.xtext.formatting.impl.FormattingConfig

      /**
       * This class contains custom formatting declarations.
       *
       * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting
       * on how and when to use it.
       *
       * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example
       */
      class «grammar.formatterStub.simpleName» extends AbstractDeclarativeFormatter {

        @«Inject» extension «grammar.grammarAccess»

        override protected void configureFormatting(FormattingConfig c) {
      // It's usually a good idea to activate the following three statements.
      // They will add and preserve newlines around comments
      //    c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)
      //    c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)
      //    c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)
        }
      }
    '''
    return xtendFile
  }

  protected def doGetJavaStubFile() {
    val javaFile = fileAccessFactory.createJavaFile(grammar.formatterStub)
    javaFile.resourceSet = language.resourceSet

    javaFile.content = '''
      import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
      import org.eclipse.xtext.formatting.impl.FormattingConfig;

      /**
       * This class contains custom formatting declarations.
       *
       * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting
       * on how and when to use it.
       *
       * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example
       */
      class «grammar.formatterStub.simpleName» extends AbstractDeclarativeFormatter {

        @«Inject» «grammar.grammarAccess» grammarAccess;

        @Override
          protected void configureFormatting(FormattingConfig c) {
      // It's usually a good idea to activate the following three statements.
      // They will add and preserve newlines around comments
      //    c.setLinewrap(0, 1, 2).before(grammarAccess.getSL_COMMENTRule());
      //    c.setLinewrap(0, 1, 2).before(grammarAccess.getML_COMMENTRule());
      //    c.setLinewrap(0, 1, 1).after(grammarAccess.getML_COMMENTRule());
        }
      }
    '''
    return javaFile
  }

}
