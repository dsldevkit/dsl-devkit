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
package com.avaloq.tools.ddk.check.generator

import com.avaloq.tools.ddk.check.check.Check
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.util.CheckUtil
import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.AbstractFileSystemAccess
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IFileSystemAccess2
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator

import static org.eclipse.xtext.xbase.lib.IteratorExtensions.*
import com.avaloq.tools.ddk.check.check.FormalParameter
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import org.eclipse.xtext.common.types.JvmField
import org.eclipse.xtext.xbase.compiler.GeneratorConfig

import static extension com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions.*
import static extension com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.*
import com.avaloq.tools.ddk.check.compiler.ICheckGeneratorConfigProvider

class CheckGenerator extends JvmModelGenerator {

  @Inject extension CheckGeneratorExtensions generatorExtensions
  @Inject extension CheckGeneratorNaming
  @Inject CheckCompiler compiler
  @Inject ICheckGeneratorConfigProvider generatorConfigProvider;

  override void doGenerate(Resource resource, IFileSystemAccess fsa) {
    val lfFsa = new LfNormalizingFileSystemAccess(fsa as IFileSystemAccess2)
    super.doGenerate(resource, lfFsa); // Generate validator, catalog, and preference initializer from inferred Jvm models.
    val config = generatorConfigProvider.get(resource?.URI);
    for (catalog : toIterable(resource.allContents).filter(typeof(CheckCatalog))) {

      lfFsa.generateFile(catalog.issueCodesFilePath, catalog.compileIssueCodes)
      lfFsa.generateFile(catalog.standaloneSetupPath, catalog.compileStandaloneSetup)

      // change output path for service registry
      lfFsa.generateFile(
        CheckUtil::serviceRegistryClassName,
        CheckGeneratorConstants::CHECK_REGISTRY_OUTPUT,
        catalog.generateServiceRegistry(CheckUtil::serviceRegistryClassName, fsa)
      )
      // generate documentation for SCA-checks only
      if(config !== null && (config.doGenerateDocumentationForAllChecks || !config.generateLanguageInternalChecks)){
        // change output path for html files to docs/
        lfFsa.generateFile(catalog.docFileName, CheckGeneratorConstants::CHECK_DOC_OUTPUT, catalog.compileDoc)
      }
    }
  }

  /* Documentation compiler, generates a self-contained HTML page per catalog. */
  def compileDoc (CheckCatalog catalog)'''
    <!DOCTYPE html>
    <html lang="en">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>«catalog.name»</title>
      <style>
    «CheckDocumentationTemplates.STYLE»
      </style>
    </head>
    <body>
      <header class="catalog-header">
        <a class="back-link" href="../index.html#«catalog.name»">← All catalogs</a>
        <h1>«catalog.name»</h1>
        «val formattedDescription = catalog.description.formatDescription»
        «IF formattedDescription !== null»
        <p>«formattedDescription»</p>
        «ENDIF»
        «IF !catalog.checks.empty || !catalog.categories.empty»
        <nav class="jump">
          <strong>Jump to</strong>
          <ul>
            «FOR check : catalog.checks»
            <li><a href="#«check.contextId»">«check.label»</a></li>
            «ENDFOR»
            «FOR category : catalog.categories»
            <li><a href="#«category.contextId»">«category.label»</a></li>
            «ENDFOR»
          </ul>
        </nav>
        «ENDIF»
      </header>
      <main>
        «bodyDoc(catalog)»
      </main>
    </body>
    </html>
  '''

  def bodyDoc(CheckCatalog catalog)'''
    «FOR check : catalog.checks»
      «checkArticle(check)»
    «ENDFOR»
    «FOR category : catalog.categories»
      <section class="category">
        <h2 id="«category.contextId»">«category.label»</h2>
        «val formattedCategoryDescription = category.description.formatDescription»
        «IF formattedCategoryDescription !== null»
        <p>«formattedCategoryDescription»</p>
        «ENDIF»
        «FOR check : category.checks»
          «checkArticle(check)»
        «ENDFOR»
      </section>
    «ENDFOR»
  '''

  def private checkArticle(Check check)'''
    <article class="check" id="«check.contextId»">
      <header>
        <h3>«check.label» <a class="anchor" href="#«check.contextId»">#</a></h3>
        <span class="severity sev-«check.defaultSeverity.name().toLowerCase»">«check.defaultSeverity.name().toLowerCase»</span>
      </header>
      «val formattedCheckDescription = check.description.formatDescription»
      «IF formattedCheckDescription !== null»
        «formattedCheckDescription»
      «ENDIF»
      <pre class="message">«check.message.replacePlaceholder»</pre>
    </article>
  '''

   /*
    * Creates an IssueCodes file for a Check Catalog. Every Check Catalog will have its own file
    * of issue codes.
    */
  def compileIssueCodes(CheckCatalog catalog) {
    val allIssues = catalog.checkAndImplementationIssues // all Issue instances
    val allIssueNames = allIssues.toMap([issue|issue.issueCode()], [issue|issue.issueName()]) // *all* issue names, unordered

    '''
    «IF !(catalog.packageName.isNullOrEmpty)»
    package «catalog.packageName»;
    «ENDIF»

    /**
     * Issue codes which may be used to address validation issues (for instance in quickfixes).
     */
    @SuppressWarnings("all")
    public final class «catalog.issueCodesClassName» {

      «FOR issueCode:allIssueNames.keySet.sort»
      public static final String «issueCode» = "«issueCodeValue(catalog, allIssueNames.get(issueCode))»";
      «ENDFOR»

      private «catalog.issueCodesClassName»() {
        // Prevent instantiation.
      }
    }
    '''
  }

  /*
   * Generates the Java standalone setup class which will be called by the ServiceRegistry.
   */
  def compileStandaloneSetup(CheckCatalog catalog) {
    '''
    «IF !(catalog.packageName.isNullOrEmpty)»
    package «catalog.packageName»;
    «ENDIF»

    import org.apache.logging.log4j.Logger;
    import org.apache.logging.log4j.LogManager;

    import com.avaloq.tools.ddk.check.runtime.configuration.ModelLocation;
    import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;
    import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;
    import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorStandaloneSetup;

    /**
     * Standalone setup for «catalog.name» as required by the standalone builder.
     */
    @SuppressWarnings("nls")
    public class «catalog.standaloneSetupClassName» implements ICheckValidatorStandaloneSetup {

      private static final Logger LOG = LogManager.getLogger(«catalog.standaloneSetupClassName».class);
      «IF catalog.grammar !== null»
      private static final String GRAMMAR_NAME = "«catalog.grammar.name»";
      «ENDIF»
      private static final String CATALOG_FILE_PATH = "«catalog.checkFilePath»";

      @Override
      public void doSetup() {
        ICheckValidatorRegistry.INSTANCE.registerValidator(«IF catalog.grammar !== null»GRAMMAR_NAME,«ENDIF» new «catalog.validatorClassName»());
        ICheckCatalogRegistry.INSTANCE.registerCatalog(«IF catalog.grammar !== null»GRAMMAR_NAME,«ENDIF» new ModelLocation(
          «catalog.standaloneSetupClassName».class.getClassLoader().getResource(CATALOG_FILE_PATH), CATALOG_FILE_PATH));
        LOG.info("Standalone setup done for «catalog.checkFilePath»");
      }

      @Override
      public String toString() {
        return "CheckValidatorSetup(«catalog.eResource.URI.path»)";
      }
    }
    '''
  }

  /*
   * Writes contents of the service registry file containing fully qualified class names of all validators.
   * See also http://docs.oracle.com/javase/1.4.2/docs/api/javax/imageio/spi/ServiceRegistry.html
   */
  def generateServiceRegistry(CheckCatalog catalog, String serviceRegistryFileName, IFileSystemAccess fsa) {
    val config = (fsa as AbstractFileSystemAccess).outputConfigurations.get(CheckGeneratorConstants::CHECK_REGISTRY_OUTPUT)
    val path = config.outputDirectory + "/" + serviceRegistryFileName
    val contents = catalog.getContents(path)
    contents.add(catalog.qualifiedStandaloneSetupClassName)
    '''
    «FOR c:contents»
    «c»
    «ENDFOR»
    '''
  }

  override ITreeAppendable _generateMember(JvmField field, ITreeAppendable appendable, GeneratorConfig config) {
    // Suppress generation of the "artificial" fields for FormalParameters in check impls, but not elsewhere.
    if (field.final && !field.static) { // A bit hacky to use this as the distinction...
      val FormalParameter parameter = compiler.getFormalParameter(field);
      if (parameter !== null) {
        return appendable;
      }
    }
    return super._generateMember(field, appendable, config);
  }
}

