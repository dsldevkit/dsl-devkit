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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.xtext.xbase.lib.Functions.Function1;
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


@SuppressWarnings({"checkstyle:MethodName", "nls"})
public class CheckGenerator extends JvmModelGenerator {

  @Inject
  private CheckGeneratorExtensions generatorExtensions;

  @Inject
  private CheckGeneratorNaming checkGeneratorNaming;

  @Inject
  private CheckCompiler compiler;

  @Inject
  private ICheckGeneratorConfigProvider generatorConfigProvider;

  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    final LfNormalizingFileSystemAccess lfFsa = new LfNormalizingFileSystemAccess((IFileSystemAccess2) fsa);
    super.doGenerate(resource, lfFsa); // Generate validator, catalog, and preference initializer from inferred Jvm models.
    URI uri = null;
    if (resource != null) {
      uri = resource.getURI();
    }
    final CheckGeneratorConfig config = generatorConfigProvider.get(uri);
    final Iterable<CheckCatalog> catalogs = Iterables.<CheckCatalog>filter(IteratorExtensions.<EObject>toIterable(resource.getAllContents()), CheckCatalog.class);
    for (final CheckCatalog catalog : catalogs) {
      lfFsa.generateFile(checkGeneratorNaming.issueCodesFilePath(catalog), compileIssueCodes(catalog));
      lfFsa.generateFile(checkGeneratorNaming.standaloneSetupPath(catalog), compileStandaloneSetup(catalog));

      // change output path for service registry
      lfFsa.generateFile(
        CheckUtil.serviceRegistryClassName(),
        CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT,
        generateServiceRegistry(catalog, CheckUtil.serviceRegistryClassName(), fsa));
      // generate documentation for SCA-checks only
      if (config != null && (config.doGenerateDocumentationForAllChecks() || !config.isGenerateLanguageInternalChecks())) {
        // change output path for html files to docs/
        lfFsa.generateFile(checkGeneratorNaming.docFileName(catalog), CheckGeneratorConstants.CHECK_DOC_OUTPUT, compileDoc(catalog));
      }
    }
  }

  // CHECKSTYLE:CONSTANTS-OFF the repeated literals are fragments of the emitted HTML/Java source, not nameable constants
  /* Documentation compiler, generates HTML output. */
  public CharSequence compileDoc(final CheckCatalog catalog) {
    StringConcatenation builder = new StringConcatenation();
    final CharSequence body = bodyDoc(catalog);
    builder.newLineIfNotEmpty();
    builder.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    builder.newLine();
    builder.append("<html>");
    builder.newLine();
    builder.append("<head>");
    builder.newLine();
    builder.append("  ");
    builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
    builder.newLine();
    builder.append("  ");
    builder.append("<link rel=\"stylesheet\" href=\"PLUGINS_ROOT/com.avaloq.tools.ddk.check.runtime.ui/css/check.css\" type=\"text/css\">");
    builder.newLine();
    builder.append("  ");
    builder.append("<title>");
    builder.append(catalog.getName(), "  ");
    builder.append("</title>");
    builder.newLineIfNotEmpty();
    builder.append("</head>");
    builder.newLine();
    builder.newLine();
    builder.append("<body>");
    builder.newLine();
    builder.append("  ");
    builder.append("<h1>Check Catalog ");
    builder.append(catalog.getName(), "  ");
    builder.append("</h1>");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    final String formattedDescription = generatorExtensions.formatDescription(catalog.getDescription());
    builder.newLineIfNotEmpty();
    if (formattedDescription != null) {
      builder.append("  ");
      builder.append("<p>");
      builder.append(formattedDescription, "  ");
      builder.append("</p>");
      builder.newLineIfNotEmpty();
    }
    builder.append("  ");
    builder.append(body, "  ");
    builder.newLineIfNotEmpty();
    builder.append("</body>");
    builder.newLine();
    builder.newLine();
    builder.append("</html>");
    builder.newLine();
    return builder;
  }

  public CharSequence bodyDoc(final CheckCatalog catalog) {
    StringConcatenation builder = new StringConcatenation();
    for (final Check check : catalog.getChecks()) {
      builder.append("<div id=\"");
      builder.append(checkGeneratorNaming.getContextId(check));
      builder.append("\" class=\"description\"><h3>");
      builder.append(check.getLabel());
      builder.append(" <span class=\"thin\">(");
      builder.append(check.getDefaultSeverity().name().toLowerCase());
      builder.append(")</span></h3>");
      builder.newLineIfNotEmpty();
      final String formattedCheckDescription = generatorExtensions.formatDescription(check.getDescription());
      builder.newLineIfNotEmpty();
      if (formattedCheckDescription != null) {
        builder.append(formattedCheckDescription);
        builder.newLineIfNotEmpty();
      }
      builder.append("<p><i>Message: </i>");
      builder.append(generatorExtensions.replacePlaceholder(check.getMessage()));
      builder.append("</p><br></div>");
      builder.newLineIfNotEmpty();
    }
    for (final Category category : catalog.getCategories()) {
      builder.append("<div class=\"category\">");
      builder.newLine();
      builder.append("  ");
      builder.append("<h2 id=\"");
      builder.append(checkGeneratorNaming.getContextId(category), "  ");
      builder.append("\">");
      builder.append(category.getLabel(), "  ");
      builder.append("</h2>");
      builder.newLineIfNotEmpty();
      builder.append("  ");
      final String formattedCategoryDescription = generatorExtensions.formatDescription(category.getDescription());
      builder.newLineIfNotEmpty();
      if (formattedCategoryDescription != null) {
        builder.append("  ");
        builder.append(formattedCategoryDescription, "  ");
        builder.newLineIfNotEmpty();
      }
      for (final Check check : category.getChecks()) {
        builder.append("  ");
        builder.append("<div id=\"");
        builder.append(checkGeneratorNaming.getContextId(check), "  ");
        builder.append("\" class=\"description\">");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("  ");
        builder.append("<h3>");
        builder.append(check.getLabel(), "    ");
        builder.append(" <span class=\"thin\">(");
        builder.append(check.getDefaultSeverity().name().toLowerCase(), "    ");
        builder.append(")</span></h3>");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("  ");
        final String formattedCheckDescription = generatorExtensions.formatDescription(check.getDescription());
        builder.newLineIfNotEmpty();
        if (formattedCheckDescription != null) {
          builder.append("  ");
          builder.append("  ");
          builder.append(formattedCheckDescription, "    ");
          builder.newLineIfNotEmpty();
        }
        builder.append("  ");
        builder.append("  ");
        builder.append("<p><i>Message: </i>");
        builder.append(generatorExtensions.replacePlaceholder(check.getMessage()), "    ");
        builder.append("</p>");
        builder.newLineIfNotEmpty();
        builder.append("  ");
        builder.append("</div>");
        builder.newLine();
      }
      builder.append("</div>");
      builder.newLine();
    }
    return builder;
  }

  /*
   * Creates an IssueCodes file for a Check Catalog. Every Check Catalog will have its own file
   * of issue codes.
   */
  public CharSequence compileIssueCodes(final CheckCatalog catalog) {
    final Iterable<XIssueExpression> allIssues = generatorExtensions.checkAndImplementationIssues(catalog);
    final Function1<XIssueExpression, String> keyFunction = (final XIssueExpression issue) -> {
      return CheckGeneratorExtensions.issueCode(issue);
    };
    final Function1<XIssueExpression, String> valueFunction = (final XIssueExpression issue) -> {
      return CheckGeneratorExtensions.issueName(issue);
    };
    final Map<String, String> allIssueNames = IterableExtensions.<XIssueExpression, String, String>toMap(allIssues, keyFunction, valueFunction);
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
    final List<String> sortedCodes = IterableExtensions.<String>sort(allIssueNames.keySet());
    for (final String issueCode : sortedCodes) {
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

  /*
   * Generates the Java standalone setup class which will be called by the ServiceRegistry.
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
    builder.append(checkGeneratorNaming.standaloneSetupClassName(catalog));
    builder.append(" implements ICheckValidatorStandaloneSetup {");
    builder.newLineIfNotEmpty();
    builder.newLine();
    builder.append("  ");
    builder.append("private static final Logger LOG = LogManager.getLogger(");
    builder.append(checkGeneratorNaming.standaloneSetupClassName(catalog), "  ");
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
    builder.append(checkGeneratorNaming.checkFilePath(catalog), "  ");
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
    builder.append(checkGeneratorNaming.validatorClassName(catalog), "    ");
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
    builder.append(checkGeneratorNaming.standaloneSetupClassName(catalog), "      ");
    builder.append(".class.getClassLoader().getResource(CATALOG_FILE_PATH), CATALOG_FILE_PATH));");
    builder.newLineIfNotEmpty();
    builder.append("    ");
    builder.append("LOG.info(\"Standalone setup done for ");
    builder.append(checkGeneratorNaming.checkFilePath(catalog), "    ");
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

  /*
   * Writes contents of the service registry file containing fully qualified class names of all validators.
   * See also http://docs.oracle.com/javase/1.4.2/docs/api/javax/imageio/spi/ServiceRegistry.html
   */
  public CharSequence generateServiceRegistry(final CheckCatalog catalog, final String serviceRegistryFileName, final IFileSystemAccess fsa) {
    final OutputConfiguration config = ((AbstractFileSystemAccess) fsa).getOutputConfigurations().get(CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT);
    final String path = config.getOutputDirectory() + "/" + serviceRegistryFileName;
    final Set<String> contents = generatorExtensions.getContents(catalog, path);
    contents.add(checkGeneratorNaming.qualifiedStandaloneSetupClassName(catalog));
    StringConcatenation builder = new StringConcatenation();
    for (final String c : contents) {
      builder.append(c);
      builder.newLineIfNotEmpty();
    }
    return builder;
  }

  // CHECKSTYLE:CONSTANTS-ON

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
}
