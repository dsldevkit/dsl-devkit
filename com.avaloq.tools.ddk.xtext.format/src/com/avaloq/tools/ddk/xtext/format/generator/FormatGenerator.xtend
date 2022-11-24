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
package com.avaloq.tools.ddk.xtext.format.generator

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.common.types.JvmField
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.Naming
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter
import org.eclipse.xtext.xbase.compiler.ErrorSafeExtensions
import org.eclipse.xtext.xbase.compiler.GeneratorConfig
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator
import org.eclipse.xtext.xbase.compiler.TreeAppendableUtil
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable

import static com.avaloq.tools.ddk.xtext.format.generator.FormatGeneratorUtil.*
import static org.eclipse.xtext.xbase.lib.IteratorExtensions.*
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration
import com.avaloq.tools.ddk.xtext.format.FormatConstants

/**
 * Generates code from your model files on save.
 *
 * Currently this generator is not used at all.
 * This is a side effect of the usage of the org.eclipse.xtext.generator.generator.GeneratorFragment.GeneratorFragment,
 * which generates this file besides necessary contents for org.eclipse.xtext.builder.BuilderParticipant.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
class FormatGenerator extends JvmModelGenerator {

    @Inject extension Naming naming
    @Inject extension TreeAppendableUtil
    @Inject extension ErrorSafeExtensions

    def getSingleCommentDocumentation(EObject it, ITreeAppendable appendable, GeneratorConfig config) {
        val adapter = it.eAdapters.filter(DocumentationAdapter).head
        if (!adapter?.documentation.nullOrEmpty) {
            return adapter.documentation
        }
        return null
    }

    override dispatch generateMember(JvmField it, ITreeAppendable appendable, GeneratorConfig config) {
        appendable.newLine
        val tracedAppendable = appendable.trace(it)
        annotations.generateAnnotations(tracedAppendable, true, config)
        generateModifier(tracedAppendable, config)
        type.serializeSafely("Object", tracedAppendable)
        tracedAppendable.append(" ")
        tracedAppendable.traceSignificant(it).append(simpleName)
        generateInitialization(tracedAppendable, config)
        tracedAppendable.append(";")
        var documentation = getSingleCommentDocumentation(appendable, config)
        if (documentation !== null && documentation.endsWith("\r\n")) {
            documentation = documentation.substring(0, documentation.length - 2)
        }
        if (documentation !== null) {
            tracedAppendable.append(" // ")
            tracedAppendable.append(documentation)
        }
    }

    override void doGenerate(Resource resource, IFileSystemAccess fsa) {
        super.doGenerate(resource, fsa); // Generate the abstract formatter from inferred Jvm models.

        for (model : toIterable(resource.allContents).filter(typeof(FormatConfiguration))) {
            fsa.generateFile(getFormatterName(model, "").asPath + ".java", FormatConstants.FORMATTER,
                model.generateSrc)
        }
    }

    def generateSrc(FormatConfiguration model) '''
        package «getFormatterName(model, "").toPackageName»;

        /**
         * The formatting configuration for «model.targetGrammar.name.toSimpleName».
         */
        public class «getFormatterName(model, "").toSimpleName» extends «getFormatterName(
            model, "Abstract").toSimpleName» {
          // TODO: Provide a correct implementation of getSLCommentRule() and getMLCommentRule() in this class
        }
    '''

}
