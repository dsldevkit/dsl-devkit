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

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.generator.AbstractFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

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


@SuppressWarnings("nls")
// CHECKSTYLE:CONSTANTS-OFF
public class CheckGenerator extends JvmModelGenerator {

  @Inject
  private CheckGeneratorExtensions generatorExtensions;

  @Inject
  private CheckGeneratorNaming naming;

  @Inject
  private CheckCompiler compiler;

  @Inject
  private ICheckGeneratorConfigProvider generatorConfigProvider;

  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    final LfNormalizingFileSystemAccess lfFsa = new LfNormalizingFileSystemAccess((IFileSystemAccess2) fsa);
    super.doGenerate(resource, lfFsa); // Generate validator, catalog, and preference initializer from inferred Jvm models.
    final CheckGeneratorConfig config = generatorConfigProvider.get(resource == null ? null : resource.getURI());
    for (final CheckCatalog catalog : Iterables.filter(IteratorExtensions.toIterable(resource.getAllContents()), CheckCatalog.class)) {
      lfFsa.generateFile(naming.issueCodesFilePath(catalog), compileIssueCodes(catalog));
      lfFsa.generateFile(naming.standaloneSetupPath(catalog), compileStandaloneSetup(catalog));

      // change output path for service registry
      lfFsa.generateFile(
          CheckUtil.serviceRegistryClassName(),
          CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT,
          generateServiceRegistry(catalog, CheckUtil.serviceRegistryClassName(), fsa));
      // generate documentation for SCA-checks only
      if (config != null && (config.doGenerateDocumentationForAllChecks() || !config.isGenerateLanguageInternalChecks())) {
        // change output path for html files to docs/
        lfFsa.generateFile(naming.docFileName(catalog), CheckGeneratorConstants.CHECK_DOC_OUTPUT, compileDoc(catalog));
      }
    }
  }

  /**
   * Documentation compiler, generates a self-contained HTML page per catalog.
   *
   * @param catalog
   *          the catalog to document
   * @return the HTML page contents
   */
  public CharSequence compileDoc(final CheckCatalog catalog) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("<!DOCTYPE html>");
    builder.newLine();
    builder.append("<html lang=\"en\">");
    builder.newLine();
    builder.append("<head>");
    builder.newLine();
    builder.append("  ");
    builder.append("<meta charset=\"UTF-8\">");
    builder.newLine();
    builder.append("  ");
    builder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
    builder.newLine();
    builder.append("  ");
    builder.append("<title>");
    builder.append(catalog.getName(), "  ");
    builder.append("</title>");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("<style>");
    builder.newLine();
    builder.append(CheckDocumentationTemplates.STYLE);
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("</style>");
    builder.newLine();
    builder.append("</head>");
    builder.newLine();
    builder.append("<body>");
    builder.newLine();
    builder.append("  ");
    builder.append("<header class=\"catalog-header\">");
    builder.newLine();
    builder.append("    ");
    builder.append("<a class=\"back-link\" href=\"../index.html#");
    builder.append(catalog.getName(), "    ");
    builder.append("\">← All catalogs</a>");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("<h1>");
    builder.append(catalog.getName(), "    ");
    builder.append("</h1>");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    final String formattedDescription = generatorExtensions.formatDescription(catalog.getDescription());
    builder.newLineIfNotEmpty();
    if (formattedDescription != null) {
      builder.append("    ");
      builder.append("<p>");
      builder.append(formattedDescription, "    ");
      builder.append("</p>");
      builder.newLineIfNotEmpty();
    }
    if (!catalog.getChecks().isEmpty() || !catalog.getCategories().isEmpty()) {
      builder.append("    ");
      builder.append("<nav class=\"jump\">");
      builder.newLine();
      builder.append("    ");
      builder.append("  ");
      builder.append("<strong>Jump to</strong>");
      builder.newLine();
      builder.append("    ");
      builder.append("  ");
      builder.append("<ul>");
      builder.newLine();
      for (final Check check : catalog.getChecks()) {
        builder.append("    ");
        builder.append("    ");
        builder.append("<li><a href=\"#");
        builder.append(naming.getContextId(check), "        ");
        builder.append("\">");
        builder.append(check.getLabel(), "        ");
        builder.append("</a></li>");
        builder.newLineIfNotEmpty();
      }
      for (final Category category : catalog.getCategories()) {
        builder.append("    ");
        builder.append("    ");
        builder.append("<li><a href=\"#");
        builder.append(naming.getContextId(category), "        ");
        builder.append("\">");
        builder.append(category.getLabel(), "        ");
        builder.append("</a></li>");
        builder.newLineIfNotEmpty();
      }
      builder.append("    ");
      builder.append("  ");
      builder.append("</ul>");
      builder.newLine();
      builder.append("    ");
      builder.append("</nav>");
      builder.newLine();
    }
    builder.append("  ");
    builder.append("</header>");
    builder.newLine();
    builder.append("  ");
    builder.append("<main>");
    builder.newLine();
    builder.append("    ");
    builder.append(bodyDoc(catalog), "    ");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("</main>");
    builder.newLine();
    builder.append("</body>");
    builder.newLine();
    builder.append("</html>");
    builder.newLine();
    return builder;
  }

  /**
   * Renders the body of the documentation page: one article per check, grouped by category.
   *
   * @param catalog
   *          the catalog whose checks and categories are rendered
   * @return the HTML body fragment
   */
  public CharSequence bodyDoc(final CheckCatalog catalog) {
    StringConcatenation builder = new StringConcatenation();
    for (final Check check : catalog.getChecks()) {
      builder.append(checkArticle(check));
      builder.newLineIfNotEmpty();
    }
    for (final Category category : catalog.getCategories()) {
      builder.append("<section class=\"category\">");
      builder.newLine();
      builder.append("  ");
      builder.append("<h2 id=\"");
      builder.append(naming.getContextId(category), "  ");
      builder.append("\">");
      builder.append(category.getLabel(), "  ");
      builder.append("</h2>");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      final String formattedCategoryDescription = generatorExtensions.formatDescription(category.getDescription());
      builder.newLineIfNotEmpty();
      if (formattedCategoryDescription != null) {
        builder.append("  ");
        builder.append("<p>");
        builder.append(formattedCategoryDescription, "  ");
        builder.append("</p>");
        builder.newLineIfNotEmpty();
      }
      for (final Check check : category.getChecks()) {
        builder.append("  ");
        builder.append(checkArticle(check), "  ");
        builder.newLineIfNotEmpty();
      }
      builder.append("</section>");
      builder.newLine();
    }
    return builder;
  }

  /**
   * Renders a single check as an HTML {@code <article>} block.
   *
   * @param check
   *          the check to render
   * @return the HTML article fragment
   */
  private CharSequence checkArticle(final Check check) {
    StringConcatenation builder = new StringConcatenation();
    builder.append("<article class=\"check\" id=\"");
    builder.append(naming.getContextId(check));
    builder.append("\">");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("<header>");
    builder.newLine();
    builder.append("    ");
    builder.append("<h3>");
    builder.append(check.getLabel(), "    ");
    builder.append(" <a class=\"anchor\" href=\"#");
    builder.append(naming.getContextId(check), "    ");
    builder.append("\">#</a></h3>");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("<span class=\"severity sev-");
    builder.append(check.getDefaultSeverity().name().toLowerCase(), "    ");
    builder.append("\">");
    builder.append(check.getDefaultSeverity().name().toLowerCase(), "    ");
    builder.append("</span>");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("</header>");
    builder.newLine();
    builder.append("  ");
    final String formattedCheckDescription = generatorExtensions.formatDescription(check.getDescription());
    builder.newLineIfNotEmpty();
    if (formattedCheckDescription != null) {
      builder.append("  ");
      builder.append(formattedCheckDescription, "  ");
      builder.newLineIfNotEmpty();
    }
    builder.append("  ");
    builder.append("<pre class=\"message\">");
    builder.append(generatorExtensions.replacePlaceholder(check.getMessage()), "  ");
    builder.append("</pre>");
    builder.newLineIfNotEmpty();
    builder.append("</article>");
    builder.newLine();
    return builder;
  }

  /**
   * Creates an IssueCodes file for a Check Catalog. Every Check Catalog will have its own file
   * of issue codes.
   *
   * @param catalog
   *          the catalog to generate issue codes for
   * @return the Java source of the issue codes class
   */
  public CharSequence compileIssueCodes(final CheckCatalog catalog) {
    final Iterable<XIssueExpression> allIssues = generatorExtensions.checkAndImplementationIssues(catalog);
    final Map<String, String> allIssueNames = IterableExtensions.toMap(allIssues, CheckGeneratorExtensions::issueCode, CheckGeneratorExtensions::issueName);
    StringConcatenation builder = new StringConcatenation();
    if (!StringExtensions.isNullOrEmpty(catalog.getPackageName())) {
      builder.append("package ");
      builder.append(catalog.getPackageName());
      builder.append(";");
      builder.newLineIfNotEmpty();
    }
    builder.newLine();
    builder.append("/**");
    builder.newLine();
    builder.append(" ");
    builder.append("* Issue codes which may be used to address validation issues (for instance in quickfixes).");
    builder.newLine();
    builder.append(" ");
    builder.append("*/");
    builder.newLine();
    builder.append("@SuppressWarnings(\"all\")");
    builder.newLine();
    builder.append("public final class ");
    builder.append(CheckGeneratorNaming.issueCodesClassName(catalog));
    builder.append(" {");
    builder.newLineIfNotEmpty();
    builder.newLine();
    for (final String issueCode : IterableExtensions.sort(allIssueNames.keySet())) {
      builder.append("  ");
      builder.append("public static final String ");
      builder.append(issueCode, "  ");
      builder.append(" = \"");
      builder.append(CheckGeneratorExtensions.issueCodeValue(catalog, allIssueNames.get(issueCode)), "  ");
      builder.append("\";");
      builder.newLineIfNotEmpty();
    }
    builder.newLine();
    builder.append("  ");
    builder.append("private ");
    builder.append(CheckGeneratorNaming.issueCodesClassName(catalog), "  ");
    builder.append("() {");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("// Prevent instantiation.");
    builder.newLine();
    builder.append("  ");
    builder.append("}");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    return builder;
  }

  /**
   * Generates the Java standalone setup class which will be called by the ServiceRegistry.
   *
   * @param catalog
   *          the catalog to generate the standalone setup for
   * @return the Java source of the standalone setup class
   */
  public CharSequence compileStandaloneSetup(final CheckCatalog catalog) {
    StringConcatenation builder = new StringConcatenation();
    if (!StringExtensions.isNullOrEmpty(catalog.getPackageName())) {
      builder.append("package ");
      builder.append(catalog.getPackageName());
      builder.append(";");
      builder.newLineIfNotEmpty();
    }
    builder.newLine();
    builder.append("import org.apache.logging.log4j.Logger;");
    builder.newLine();
    builder.append("import org.apache.logging.log4j.LogManager;");
    builder.newLine();
    builder.newLine();
    builder.append("import com.avaloq.tools.ddk.check.runtime.configuration.ModelLocation;");
    builder.newLine();
    builder.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;");
    builder.newLine();
    builder.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;");
    builder.newLine();
    builder.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorStandaloneSetup;");
    builder.newLine();
    builder.newLine();
    builder.append("/**");
    builder.newLine();
    builder.append(" ");
    builder.append("* Standalone setup for ");
    builder.append(catalog.getName(), " ");
    builder.append(" as required by the standalone builder.");
    builder.newLineIfNotEmpty();
    builder.append(" ");
    builder.append("*/");
    builder.newLine();
    builder.append("@SuppressWarnings(\"nls\")");
    builder.newLine();
    builder.append("public class ");
    builder.append(naming.standaloneSetupClassName(catalog));
    builder.append(" implements ICheckValidatorStandaloneSetup {");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("  ");
    builder.append("private static final Logger LOG = LogManager.getLogger(");
    builder.append(naming.standaloneSetupClassName(catalog), "  ");
    builder.append(".class);");
    builder.newLineIfNotEmpty();
    if (catalog.getGrammar() != null) {
      builder.append("  ");
      builder.append("private static final String GRAMMAR_NAME = \"");
      builder.append(catalog.getGrammar().getName(), "  ");
      builder.append("\";");
      builder.newLineIfNotEmpty();
    }
    builder.append("  ");
    builder.append("private static final String CATALOG_FILE_PATH = \"");
    builder.append(naming.checkFilePath(catalog), "  ");
    builder.append("\";");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("  ");
    builder.append("@Override");
    builder.newLine();
    builder.append("  ");
    builder.append("public void doSetup() {");
    builder.newLine();
    builder.append("    ");
    builder.append("ICheckValidatorRegistry.INSTANCE.registerValidator(");
    if (catalog.getGrammar() != null) {
      builder.append("GRAMMAR_NAME,");
    }
    builder.append(" new ");
    builder.append(naming.validatorClassName(catalog), "    ");
    builder.append("());");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("ICheckCatalogRegistry.INSTANCE.registerCatalog(");
    if (catalog.getGrammar() != null) {
      builder.append("GRAMMAR_NAME,");
    }
    builder.append(" new ModelLocation(");
    builder.newLineIfNotEmpty();
    builder.append("      ");
    builder.append(naming.standaloneSetupClassName(catalog), "      ");
    builder.append(".class.getClassLoader().getResource(CATALOG_FILE_PATH), CATALOG_FILE_PATH));");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("LOG.info(\"Standalone setup done for ");
    builder.append(naming.checkFilePath(catalog), "    ");
    builder.append("\");");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("}");
    builder.newLine();
    builder.newLine();
    builder.append("  ");
    builder.append("@Override");
    builder.newLine();
    builder.append("  ");
    builder.append("public String toString() {");
    builder.newLine();
    builder.append("    ");
    builder.append("return \"CheckValidatorSetup(");
    builder.append(catalog.eResource().getURI().path(), "    ");
    builder.append(")\";");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("}");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    return builder;
  }

  /**
   * Writes contents of the service registry file containing fully qualified class names of all validators.
   * See also http://docs.oracle.com/javase/1.4.2/docs/api/javax/imageio/spi/ServiceRegistry.html
   *
   * @param catalog
   *          the catalog whose standalone setup is registered
   * @param serviceRegistryFileName
   *          the service registry file name
   * @param fsa
   *          the file system access used to resolve the output configuration
   * @return the service registry file contents
   */
  public CharSequence generateServiceRegistry(final CheckCatalog catalog, final String serviceRegistryFileName, final IFileSystemAccess fsa) {
    final OutputConfiguration config = ((AbstractFileSystemAccess) fsa).getOutputConfigurations().get(CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT);
    final String path = config.getOutputDirectory() + "/" + serviceRegistryFileName;
    final Set<String> contents = generatorExtensions.getContents(catalog, path);
    contents.add(naming.qualifiedStandaloneSetupClassName(catalog));
    StringConcatenation builder = new StringConcatenation();
    for (final String c : contents) {
      builder.append(c);
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  @Override
  public ITreeAppendable _generateMember(final JvmField field, final ITreeAppendable appendable, final GeneratorConfig config) {
    // Suppress generation of the "artificial" fields for FormalParameters in check impls, but not elsewhere.
    if (field.isFinal() && !field.isStatic()) { // A bit hacky to use this as the distinction...
      final FormalParameter parameter = compiler.getFormalParameter(field);
      if (parameter != null) {
        return appendable;
      }
    }
    return super._generateMember(field, appendable, config);
  }
// CHECKSTYLE:CONSTANTS-ON
}
