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
package com.avaloq.tools.ddk.check.generator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.compiler.CheckGeneratorConfig;
import com.avaloq.tools.ddk.check.compiler.ICheckGeneratorConfigProvider;
import com.avaloq.tools.ddk.check.util.CheckUtil;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.generator.AbstractFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

public class CheckGenerator extends JvmModelGenerator {

  @Inject
  private CheckGeneratorExtensions generatorExtensions;

  @Inject
  private CheckGeneratorNaming _checkGeneratorNaming;

  @Inject
  private CheckCompiler compiler;

  @Inject
  private ICheckGeneratorConfigProvider generatorConfigProvider;

  @Override
  public void doGenerate(Resource resource, IFileSystemAccess fsa) {
    super.doGenerate(resource, fsa); // Generate validator, catalog, and preference initializer from inferred Jvm models.
    URI uri = null;
    if (resource != null) {
      uri = resource.getURI();
    }
    final CheckGeneratorConfig config = generatorConfigProvider.get(uri);
    Iterable<CheckCatalog> catalogs = Iterables.<CheckCatalog>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), CheckCatalog.class);
    for (CheckCatalog catalog : catalogs) {
      fsa.generateFile(_checkGeneratorNaming.issueCodesFilePath(catalog), compileIssueCodes(catalog));
      fsa.generateFile(_checkGeneratorNaming.standaloneSetupPath(catalog), compileStandaloneSetup(catalog));

      // change output path for service registry
      fsa.generateFile(
        CheckUtil.serviceRegistryClassName(),
        CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT,
        generateServiceRegistry(catalog, CheckUtil.serviceRegistryClassName(), fsa));
      // generate documentation for SCA-checks only
      if (config != null && (config.doGenerateDocumentationForAllChecks() || !config.isGenerateLanguageInternalChecks())) {
        // change output path for html files to docs/
        fsa.generateFile(_checkGeneratorNaming.docFileName(catalog), CheckGeneratorConstants.CHECK_DOC_OUTPUT, compileDoc(catalog));
      }
    }
  }

  /* Documentation compiler, generates HTML output. */
  public CharSequence compileDoc(CheckCatalog catalog) {
    final CharSequence body = bodyDoc(catalog);
    final StringBuilder sb = new StringBuilder();
    sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
    sb.append("<html>\n");
    sb.append("<head>\n");
    sb.append("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
    sb.append("  <link rel=\"stylesheet\" href=\"PLUGINS_ROOT/com.avaloq.tools.ddk.check.runtime.ui/css/check.css\" type=\"text/css\">\n");
    sb.append("  <title>").append(catalog.getName()).append("</title>\n");
    sb.append("</head>\n");
    sb.append("\n");
    sb.append("<body>\n");
    sb.append("  <h1>Check Catalog ").append(catalog.getName()).append("</h1>\n");
    final String formattedDescription = generatorExtensions.formatDescription(catalog.getDescription());
    if (formattedDescription != null) {
      sb.append("  <p>").append(formattedDescription).append("</p>\n");
    }
    sb.append("  ").append(body).append("\n");
    sb.append("</body>\n");
    sb.append("\n");
    sb.append("</html>\n");
    return sb;
  }

  public CharSequence bodyDoc(CheckCatalog catalog) {
    final StringBuilder sb = new StringBuilder();
    for (Check check : catalog.getChecks()) {
      sb.append("<div id=\"").append(_checkGeneratorNaming.getContextId(check))
        .append("\" class=\"description\"><h3>").append(check.getLabel())
        .append(" <span class=\"thin\">(").append(check.getDefaultSeverity().name().toLowerCase())
        .append(")</span></h3>\n");
      final String formattedCheckDescription = generatorExtensions.formatDescription(check.getDescription());
      if (formattedCheckDescription != null) {
        sb.append(formattedCheckDescription).append("\n");
      }
      sb.append("<p><i>Message: </i>").append(generatorExtensions.replacePlaceholder(check.getMessage()))
        .append("</p><br></div>\n");
    }
    for (Category category : catalog.getCategories()) {
      sb.append("<div class=\"category\">\n");
      sb.append("  <h2 id=\"").append(_checkGeneratorNaming.getContextId(category))
        .append("\">").append(category.getLabel()).append("</h2>\n");
      final String formattedCategoryDescription = generatorExtensions.formatDescription(category.getDescription());
      if (formattedCategoryDescription != null) {
        sb.append("  ").append(formattedCategoryDescription).append("\n");
      }
      for (Check check : category.getChecks()) {
        sb.append("  <div id=\"").append(_checkGeneratorNaming.getContextId(check))
          .append("\" class=\"description\">\n");
        sb.append("    <h3>").append(check.getLabel())
          .append(" <span class=\"thin\">(").append(check.getDefaultSeverity().name().toLowerCase())
          .append(")</span></h3>\n");
        final String formattedCheckDescription = generatorExtensions.formatDescription(check.getDescription());
        if (formattedCheckDescription != null) {
          sb.append("    ").append(formattedCheckDescription).append("\n");
        }
        sb.append("    <p><i>Message: </i>").append(generatorExtensions.replacePlaceholder(check.getMessage()))
          .append("</p>\n");
        sb.append("  </div>\n");
      }
      sb.append("</div>\n");
    }
    return sb;
  }

  /*
   * Creates an IssueCodes file for a Check Catalog. Every Check Catalog will have its own file
   * of issue codes.
   */
  public CharSequence compileIssueCodes(CheckCatalog catalog) {
    final Iterable<XIssueExpression> allIssues = generatorExtensions.checkAndImplementationIssues(catalog);
    final Function1<XIssueExpression, String> keyFunction = (XIssueExpression issue) -> {
      return CheckGeneratorExtensions.issueCode(issue);
    };
    final Function1<XIssueExpression, String> valueFunction = (XIssueExpression issue) -> {
      return CheckGeneratorExtensions.issueName(issue);
    };
    final Map<String, String> allIssueNames = IterableExtensions.<XIssueExpression, String, String>toMap(allIssues, keyFunction, valueFunction);
    final StringBuilder sb = new StringBuilder();
    if (!StringExtensions.isNullOrEmpty(catalog.getPackageName())) {
      sb.append("package ").append(catalog.getPackageName()).append(";\n");
    }
    sb.append("\n");
    sb.append("/**\n");
    sb.append(" * Issue codes which may be used to address validation issues (for instance in quickfixes).\n");
    sb.append(" */\n");
    sb.append("@SuppressWarnings(\"all\")\n");
    sb.append("public final class ").append(CheckGeneratorNaming.issueCodesClassName(catalog)).append(" {\n");
    sb.append("\n");
    final List<String> sortedKeys = IterableExtensions.<String>sort(allIssueNames.keySet());
    for (String issueCode : sortedKeys) {
      sb.append("  public static final String ").append(issueCode)
        .append(" = \"").append(CheckGeneratorExtensions.issueCodeValue(catalog, allIssueNames.get(issueCode)))
        .append("\";\n");
    }
    sb.append("\n");
    sb.append("  private ").append(CheckGeneratorNaming.issueCodesClassName(catalog)).append("() {\n");
    sb.append("    // Prevent instantiation.\n");
    sb.append("  }\n");
    sb.append("}\n");
    return sb;
  }

  /*
   * Generates the Java standalone setup class which will be called by the ServiceRegistry.
   */
  public CharSequence compileStandaloneSetup(CheckCatalog catalog) {
    final StringBuilder sb = new StringBuilder();
    if (!StringExtensions.isNullOrEmpty(catalog.getPackageName())) {
      sb.append("package ").append(catalog.getPackageName()).append(";\n");
    }
    sb.append("\n");
    sb.append("import org.apache.logging.log4j.Logger;\n");
    sb.append("import org.apache.logging.log4j.LogManager;\n");
    sb.append("\n");
    sb.append("import com.avaloq.tools.ddk.check.runtime.configuration.ModelLocation;\n");
    sb.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;\n");
    sb.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;\n");
    sb.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorStandaloneSetup;\n");
    sb.append("\n");
    sb.append("/**\n");
    sb.append(" * Standalone setup for ").append(catalog.getName()).append(" as required by the standalone builder.\n");
    sb.append(" */\n");
    sb.append("@SuppressWarnings(\"nls\")\n");
    sb.append("public class ").append(_checkGeneratorNaming.standaloneSetupClassName(catalog))
      .append(" implements ICheckValidatorStandaloneSetup {\n");
    sb.append("\n");
    sb.append("  private static final Logger LOG = LogManager.getLogger(")
      .append(_checkGeneratorNaming.standaloneSetupClassName(catalog)).append(".class);\n");
    final Grammar grammar = catalog.getGrammar();
    if (grammar != null) {
      sb.append("  private static final String GRAMMAR_NAME = \"")
        .append(grammar.getName()).append("\";\n");
    }
    sb.append("  private static final String CATALOG_FILE_PATH = \"")
      .append(_checkGeneratorNaming.checkFilePath(catalog)).append("\";\n");
    sb.append("\n");
    sb.append("  @Override\n");
    sb.append("  public void doSetup() {\n");
    sb.append("    ICheckValidatorRegistry.INSTANCE.registerValidator(");
    if (grammar != null) {
      sb.append("GRAMMAR_NAME, ");
    }
    sb.append("new ").append(_checkGeneratorNaming.validatorClassName(catalog)).append("());\n");
    sb.append("    ICheckCatalogRegistry.INSTANCE.registerCatalog(");
    if (grammar != null) {
      sb.append("GRAMMAR_NAME, ");
    }
    sb.append("new ModelLocation(\n");
    sb.append("      ").append(_checkGeneratorNaming.standaloneSetupClassName(catalog))
      .append(".class.getClassLoader().getResource(CATALOG_FILE_PATH), CATALOG_FILE_PATH));\n");
    sb.append("    LOG.info(\"Standalone setup done for ")
      .append(_checkGeneratorNaming.checkFilePath(catalog)).append("\");\n");
    sb.append("  }\n");
    sb.append("\n");
    sb.append("  @Override\n");
    sb.append("  public String toString() {\n");
    sb.append("    return \"CheckValidatorSetup(")
      .append(catalog.eResource().getURI().path()).append(")\";\n");
    sb.append("  }\n");
    sb.append("}\n");
    return sb;
  }

  /*
   * Writes contents of the service registry file containing fully qualified class names of all validators.
   * See also http://docs.oracle.com/javase/1.4.2/docs/api/javax/imageio/spi/ServiceRegistry.html
   */
  public CharSequence generateServiceRegistry(CheckCatalog catalog, String serviceRegistryFileName, IFileSystemAccess fsa) {
    final OutputConfiguration config = ((AbstractFileSystemAccess) fsa).getOutputConfigurations().get(CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT);
    final String outputDirectory = config.getOutputDirectory();
    final String path = outputDirectory + "/" + serviceRegistryFileName;
    final Set<String> contents = generatorExtensions.getContents(catalog, path);
    contents.add(_checkGeneratorNaming.qualifiedStandaloneSetupClassName(catalog));
    final StringBuilder sb = new StringBuilder();
    for (String c : contents) {
      sb.append(c).append("\n");
    }
    return sb;
  }

  @Override
  public ITreeAppendable _generateMember(JvmField field, ITreeAppendable appendable, GeneratorConfig config) {
    // Suppress generation of the "artificial" fields for FormalParameters in check impls, but not elsewhere.
    if (field.isFinal() && !field.isStatic()) { // A bit hacky to use this as the distinction...
      final FormalParameter parameter = compiler.getFormalParameter(field);
      if (parameter != null) {
        return appendable;
      }
    }
    return super._generateMember(field, appendable, config);
  }
}
