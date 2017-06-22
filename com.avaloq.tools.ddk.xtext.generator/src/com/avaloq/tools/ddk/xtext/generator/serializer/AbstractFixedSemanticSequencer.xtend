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
package com.avaloq.tools.ddk.xtext.generator.serializer

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Grammar
import org.eclipse.xtext.generator.Naming
import org.eclipse.xtext.generator.grammarAccess.GrammarAccess
import org.eclipse.xtext.generator.serializer.AbstractSemanticSequencer
import org.eclipse.xtext.generator.serializer.JavaEMFFile
import org.eclipse.xtext.generator.serializer.SemanticSequencerUtil
import org.eclipse.xtext.generator.serializer.SerializerGenFileNames
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer
import org.eclipse.xtext.serializer.sequencer.GenericSequencer
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer
import org.eclipse.xtext.serializer.sequencer.ITransientValueService
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider.IConstraint
import org.apache.log4j.Logger

class AbstractFixedSemanticSequencer extends AbstractSemanticSequencer {

  val LOG = Logger.getLogger(AbstractFixedSemanticSequencer);
  val METHOD_SEPARATOR = "\n\n"

  @Inject Grammar grammar

  @Inject extension GrammarAccess grammarAccess

  @Inject extension SemanticSequencerUtil sequencerUtil

  @Inject SerializerGenFileNames names

  @Inject extension Naming

  override getFileContents(SerializerGenFileNames.GenFileName filename) {
    val file = new JavaEMFFile(grammar.eResource.resourceSet, filename.packageName, fileHeader);

    file.imported(EObject)
    file.imported(GenericSequencer)
    file.imported(ISemanticSequencer)
    file.imported(ITransientValueService)
    file.imported(ISemanticSequenceAcceptor)
    file.imported(ISemanticSequencerDiagnosticProvider)
    file.imported(ISerializationDiagnostic.Acceptor)
    file.imported(Inject)
    file.imported(Provider)
    if (annotationImports != null)
      annotationImports.split("(import)|;").map[trim].filter[!empty].forEach[file.imported(it)]

    val localConstraints = grammar.grammarConstraints
    val superConstraints = grammar.superGrammar.grammarConstraints
    val newLocalConstraints = localConstraints.filter[type !== null && !superConstraints.contains(it)].toSet
    val superGrammar = if(localConstraints.exists[superConstraints.contains(it)])
        file.imported(names.semanticSequencer.getQualifiedName(grammar.usedGrammars.head))
      else
        file.imported(AbstractDelegatingSemanticSequencer)
    val methodSignatures = newHashSet()
    val clazz = names.abstractSemanticSequencer.getSimpleName(grammar)
    val _abstract = if (filename.isAbstract) "abstract " else ""
    file.body = '''
      «classAnnotations»@SuppressWarnings("all")
      public «_abstract»class «filename.simpleName» extends «superGrammar» {

        @Inject
        private «file.imported(grammar.gaFQName)» grammarAccess;

        «file.genMethodCreateSequence()»

        «FOR c : newLocalConstraints.sort SEPARATOR METHOD_SEPARATOR»
        «IF methodSignatures.add(c.simpleName -> c.type)»
        «file.genMethodSequence(c)»
        «ELSE»
        «LOG.warn("Skipped generating duplicate method in " + clazz)»
        «file.genMethodSequenceComment(c)»
        «ENDIF»
        «ENDFOR»
      }
    '''
    file.toString
  } //        //«localConstraints.filter(e|e.type!=null && !superConstraints.contains(e)).sort.join("\n\n",[e|file.genMethodSequence(e)])»

  private def genMethodSequenceComment(JavaEMFFile file, IConstraint c) '''
       // This method is commented out because it has the same signature as another method in this class.
       // This is probably a bug in Xtext's serializer, please report it here:
       // https://bugs.eclipse.org/bugs/enter_bug.cgi?product=TMF
       //
       //
       // Constraint:
       //     «IF c.body === null»{«c.type.name»}«ELSE»«c.body.toString.replaceAll("\\n","\n//     ")»«ENDIF»
       //
       // protected void sequence_«c.simpleName»(EObject context, «c.type» semanticObject) { }
   '''
}

