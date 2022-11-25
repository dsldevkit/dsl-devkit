/**
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Group AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.format.generator

import com.avaloq.tools.ddk.xtext.format.FormatStandaloneSetup
import com.avaloq.tools.ddk.xtext.formatting.AbstractExtendedFormatter
import com.avaloq.tools.ddk.xtext.formatting.DirectNodeModelStreamer
import com.avaloq.tools.ddk.xtext.formatting.RegionNodeModelFormatter
import com.google.inject.Inject
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager;
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
import org.eclipse.xtext.xtext.generator.util.BooleanGeneratorOption

import static org.eclipse.xtext.GrammarUtil.*

import static extension org.eclipse.xtext.xtext.generator.model.TypeReference.*

/**
 * MWE fragment for the format language.
 */
class FormatFragment2 extends AbstractStubGeneratingFragment {

  @Inject FileAccessFactory fileAccessFactory
  @Inject extension XtextGeneratorNaming
  @Inject extension GrammarAccessExtensions

  static final String RUNTIME_PLUGIN = "com.avaloq.tools.ddk.xtext"

  val generateFormatStub = new BooleanGeneratorOption(true)

  def boolean isGenerateFormatStub() {
    generateFormatStub.get
  }

  def void setGenerateFormatStub(boolean generateStub) {
    this.generateFormatStub.set(generateStub)
  }

  /**
   * Class-wide logger.
   */
  static final Logger LOGGER = LogManager::getLogger(typeof(FormatFragment2))

  /**
   * The model for the format resource.
   */
  var baseFormatterClassName = AbstractExtendedFormatter.name

  /**
   * Set the super type / base class of the formatter.
   * @param baseFormatterClass the FQN of the base formatter
   */
  def void setBaseFormatterClassName(String baseFormatterClass) {
    baseFormatterClassName = baseFormatterClass
  }

  /**
   * Get the super type / base class of the formatter.
   * @return the FQN of the base formatter
   */
  def getBaseFormatterClassName() {
    return baseFormatterClassName
  }

  protected def TypeReference getFormatterStub(Grammar grammar) {
    new TypeReference(grammar.runtimeBasePackage + '.formatting.' + getSimpleName(grammar) + 'Formatter')
  }

  protected def String getFormatStub(Grammar grammar) {
    val formatter = grammar.formatterStub.path
    formatter.substring(0, formatter.lastIndexOf('/') + 1) + getSimpleName(grammar) + '.format'
  }

  override void generate() {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info('''executing generate for «getClass().getName()»'''.toString)
    }

    new GuiceModuleAccess.BindingFactory()
    .addTypeToType(IFormatter.typeRef, new TypeReference(FormatGeneratorUtil::getFormatterName(grammar, "")))
    .addTypeToType(INodeModelFormatter.typeRef, RegionNodeModelFormatter.typeRef)
    .addTypeToType(INodeModelStreamer.typeRef, DirectNodeModelStreamer.typeRef)
    .contributeTo(language.runtimeGenModule)
    if (projectConfig.runtime.manifest !== null) {
      projectConfig.runtime.manifest.requiredBundles += "org.eclipse.emf.ecore"
      projectConfig.runtime.manifest.requiredBundles += RUNTIME_PLUGIN
    }
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += RUNTIME_PLUGIN
    }

    if (projectConfig.genericIde.manifest !== null) {
      projectConfig.genericIde.manifest.requiredBundles+= RUNTIME_PLUGIN
    }

    FormatStandaloneSetup.doSetup()
    doGenerateStubFiles()
  }

  protected def doGenerateStubFiles() {
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
    if (isGenerateFormatStub) {
      val formatFile = doGetFormatStubFile
      formatFile?.writeTo(projectConfig.runtime.src)
    }
  }

  protected def doGetXtendStubFile() {
    val xtendFile = fileAccessFactory.createXtendFile(grammar.formatterStub)
    xtendFile.resourceSet = language.resourceSet

    xtendFile.content = '''
      import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry
      import java.util.List

      /**
       * This class contains custom formatting declarations.
       *
       * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting
       * on how and when to use it.
       *
       * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example
       */
      class «grammar.formatterStub.simpleName» extends «FormatGeneratorUtil::getFormatterName(grammar, "Abstract")» {

        @«Inject» extension «grammar.grammarAccess»

        override executeCustomPostFormatAction(ExtendedLineEntry lineEntry, List<ExtendedLineEntry> previousEntries) {
          // TODO Auto-generated method stub
          return null
        }

        override protected getMLCommentRule() {
          // TODO Auto-generated method stub
          return ML_COMMENTRule
        }

        override protected getSLCommentRule() {
          // TODO Auto-generated method stub
          return SL_COMMENTRule
        }

        override protected isUnformattedContent(String content) {
          // TODO Auto-generated method stub
          return false
        }

      }
    '''
    return xtendFile
  }

  protected def doGetJavaStubFile() {
    val javaFile = fileAccessFactory.createJavaFile(grammar.formatterStub)
    javaFile.resourceSet = language.resourceSet

    javaFile.content = '''
      import com.avaloq.tools.ddk.xtext.formatting.ExtendedLineEntry;
      import java.util.List;

      import org.eclipse.xtext.TerminalRule;

      /**
       * This class contains custom formatting declarations.
       *
       * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#formatting
       * on how and when to use it.
       *
       * Also see {@link org.eclipse.xtext.xtext.XtextFormatter} as an example
       */
      public class «grammar.formatterStub.simpleName» extends «FormatGeneratorUtil::getFormatterName(grammar, "Abstract")» {

        @«Inject» «grammar.grammarAccess» grammarAccess;

        @Override
        protected boolean isUnformattedContent(String content) {
          // TODO Auto-generated method stub
          return false;
        }

        @Override
        protected TerminalRule getSLCommentRule() {
          // TODO Auto-generated method stub
          return grammarAccess.getSL_COMMENTRule();
        }

        @Override
        protected TerminalRule getMLCommentRule() {
          // TODO Auto-generated method stub
          return grammarAccess.getML_COMMENTRule();
        }

        @Override
        public String executeCustomPostFormatAction(ExtendedLineEntry lineEntry, List<ExtendedLineEntry> previousEntries) {
          // TODO Auto-generated method stub
          return null;
        }

      }
    '''
    return javaFile
  }

  protected def doGetFormatStubFile() {
    val formatFile = fileAccessFactory.createTextFile(grammar.formatStub)

    formatFile.content = '''
      formatter for «grammar.name»

      '''
    return formatFile
  }
}
