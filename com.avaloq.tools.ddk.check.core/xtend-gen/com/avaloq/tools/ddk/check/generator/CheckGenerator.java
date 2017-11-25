/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.generator;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckCompiler;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorConstants;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.avaloq.tools.ddk.check.util.CheckUtil;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.generator.AbstractFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CheckGenerator extends JvmModelGenerator {
  @Inject
  @Extension
  private CheckGeneratorExtensions generatorExtensions;
  
  @Inject
  @Extension
  private CheckGeneratorNaming _checkGeneratorNaming;
  
  @Inject
  private CheckCompiler compiler;
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    super.doGenerate(resource, fsa);
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<CheckCatalog> _filter = Iterables.<CheckCatalog>filter(_iterable, CheckCatalog.class);
    for (final CheckCatalog catalog : _filter) {
      {
        String _issueCodesFilePath = this._checkGeneratorNaming.issueCodesFilePath(catalog);
        CharSequence _compileIssueCodes = this.compileIssueCodes(catalog);
        fsa.generateFile(_issueCodesFilePath, _compileIssueCodes);
        String _standaloneSetupPath = this._checkGeneratorNaming.standaloneSetupPath(catalog);
        CharSequence _compileStandaloneSetup = this.compileStandaloneSetup(catalog);
        fsa.generateFile(_standaloneSetupPath, _compileStandaloneSetup);
        String _serviceRegistryClassName = CheckUtil.serviceRegistryClassName();
        String _serviceRegistryClassName_1 = CheckUtil.serviceRegistryClassName();
        CharSequence _generateServiceRegistry = this.generateServiceRegistry(catalog, _serviceRegistryClassName_1, fsa);
        fsa.generateFile(_serviceRegistryClassName, 
          CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT, _generateServiceRegistry);
        String _docFileName = this._checkGeneratorNaming.docFileName(catalog);
        CharSequence _compileDoc = this.compileDoc(catalog);
        fsa.generateFile(_docFileName, CheckGeneratorConstants.CHECK_DOC_OUTPUT, _compileDoc);
      }
    }
  }
  
  /**
   * Documentation compiler, generates HTML output.
   */
  public CharSequence compileDoc(final CheckCatalog catalog) {
    StringConcatenation _builder = new StringConcatenation();
    final CharSequence body = this.bodyDoc(catalog);
    _builder.newLineIfNotEmpty();
    _builder.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    _builder.newLine();
    _builder.append("<html>");
    _builder.newLine();
    _builder.append("<head>");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("<link rel=\"stylesheet\" href=\"PLUGINS_ROOT/com.avaloq.tools.ddk.check.runtime.ui/css/check.css\" type=\"text/css\">");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("<title>");
    String _name = catalog.getName();
    _builder.append(_name, "  ");
    _builder.append("</title>");
    _builder.newLineIfNotEmpty();
    _builder.append("</head>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<body>");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("<h1>Check Catalog ");
    String _name_1 = catalog.getName();
    _builder.append(_name_1, "  ");
    _builder.append("</h1>");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    String _description = catalog.getDescription();
    final String formattedDescription = this.generatorExtensions.formatDescription(_description);
    _builder.newLineIfNotEmpty();
    {
      boolean _notEquals = (!Objects.equal(formattedDescription, null));
      if (_notEquals) {
        _builder.append("  ");
        _builder.append("<p>");
        _builder.append(formattedDescription, "  ");
        _builder.append("</p>");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("  ");
    _builder.append(body, "  ");
    _builder.newLineIfNotEmpty();
    _builder.append("</body>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("</html>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence bodyDoc(final CheckCatalog catalog) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<Check> _checks = catalog.getChecks();
      for(final Check check : _checks) {
        _builder.append("<div id=\"");
        String _contextId = this._checkGeneratorNaming.getContextId(check);
        _builder.append(_contextId, "");
        _builder.append("\" class=\"description\"><h3>");
        String _label = check.getLabel();
        _builder.append(_label, "");
        _builder.append(" <span class=\"thin\">(");
        SeverityKind _defaultSeverity = check.getDefaultSeverity();
        String _name = _defaultSeverity.name();
        String _lowerCase = _name.toLowerCase();
        _builder.append(_lowerCase, "");
        _builder.append(")</span></h3>");
        _builder.newLineIfNotEmpty();
        String _description = check.getDescription();
        final String formattedCheckDescription = this.generatorExtensions.formatDescription(_description);
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals = (!Objects.equal(formattedCheckDescription, null));
          if (_notEquals) {
            _builder.append(formattedCheckDescription, "");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("<p><i>Message: </i>");
        String _message = check.getMessage();
        String _replacePlaceholder = this.generatorExtensions.replacePlaceholder(_message);
        _builder.append(_replacePlaceholder, "");
        _builder.append("</p><br></div>");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      EList<Category> _categories = catalog.getCategories();
      for(final Category category : _categories) {
        _builder.append("<div class=\"category\">");
        _builder.newLine();
        _builder.append("  ");
        _builder.append("<h2 id=\"");
        String _contextId_1 = this._checkGeneratorNaming.getContextId(category);
        _builder.append(_contextId_1, "  ");
        _builder.append("\">");
        String _label_1 = category.getLabel();
        _builder.append(_label_1, "  ");
        _builder.append("</h2>");
        _builder.newLineIfNotEmpty();
        _builder.append("  ");
        String _description_1 = category.getDescription();
        final String formattedCateogryDescription = this.generatorExtensions.formatDescription(_description_1);
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals_1 = (!Objects.equal(formattedCateogryDescription, null));
          if (_notEquals_1) {
            _builder.append("  ");
            _builder.append(formattedCateogryDescription, "  ");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          EList<Check> _checks_1 = category.getChecks();
          for(final Check check_1 : _checks_1) {
            _builder.append("  ");
            _builder.append("<div id=\"");
            String _contextId_2 = this._checkGeneratorNaming.getContextId(check_1);
            _builder.append(_contextId_2, "  ");
            _builder.append("\" class=\"description\">");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("<h3>");
            String _label_2 = check_1.getLabel();
            _builder.append(_label_2, "    ");
            _builder.append(" <span class=\"thin\">(");
            SeverityKind _defaultSeverity_1 = check_1.getDefaultSeverity();
            String _name_1 = _defaultSeverity_1.name();
            String _lowerCase_1 = _name_1.toLowerCase();
            _builder.append(_lowerCase_1, "    ");
            _builder.append(")</span></h3>");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("  ");
            String _description_2 = check_1.getDescription();
            final String formattedCheckDescription_1 = this.generatorExtensions.formatDescription(_description_2);
            _builder.newLineIfNotEmpty();
            {
              boolean _notEquals_2 = (!Objects.equal(formattedCheckDescription_1, null));
              if (_notEquals_2) {
                _builder.append("  ");
                _builder.append("  ");
                _builder.append(formattedCheckDescription_1, "    ");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("  ");
            _builder.append("  ");
            _builder.append("<p><i>Message: </i>");
            String _message_1 = check_1.getMessage();
            String _replacePlaceholder_1 = this.generatorExtensions.replacePlaceholder(_message_1);
            _builder.append(_replacePlaceholder_1, "    ");
            _builder.append("</p>");
            _builder.newLineIfNotEmpty();
            _builder.append("  ");
            _builder.append("</div>");
            _builder.newLine();
          }
        }
        _builder.append("</div>");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  /**
   * Creates an IssueCodes file for a Check Catalog. Every Check Catalog will have its own file
   * of issue codes.
   */
  public CharSequence compileIssueCodes(final CheckCatalog catalog) {
    CharSequence _xblockexpression = null;
    {
      final Iterable<XIssueExpression> allIssues = this.generatorExtensions.checkAndImplementationIssues(catalog);
      final Function1<XIssueExpression, String> _function = (XIssueExpression issue) -> {
        return CheckGeneratorExtensions.issueCode(issue);
      };
      Iterable<String> _map = IterableExtensions.<XIssueExpression, String>map(allIssues, _function);
      final Set<String> allIssueNames = IterableExtensions.<String>toSet(_map);
      StringConcatenation _builder = new StringConcatenation();
      {
        String _packageName = catalog.getPackageName();
        boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_packageName);
        boolean _not = (!_isNullOrEmpty);
        if (_not) {
          _builder.append("package ");
          String _packageName_1 = catalog.getPackageName();
          _builder.append(_packageName_1, "");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* Issue codes which may be used to address validation issues (for instance in quickfixes).");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("@SuppressWarnings(\"all\")");
      _builder.newLine();
      _builder.append("public final class ");
      String _issueCodesClassName = CheckGeneratorNaming.issueCodesClassName(catalog);
      _builder.append(_issueCodesClassName, "");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      {
        List<String> _sort = IterableExtensions.<String>sort(allIssueNames);
        for(final String name : _sort) {
          _builder.append("  ");
          _builder.append("public static final String ");
          _builder.append(name, "  ");
          _builder.append(" = \"");
          String _issueCodeValue = CheckGeneratorExtensions.issueCodeValue(catalog, name);
          _builder.append(_issueCodeValue, "  ");
          _builder.append("\";");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("  ");
      _builder.append("private ");
      String _issueCodesClassName_1 = CheckGeneratorNaming.issueCodesClassName(catalog);
      _builder.append(_issueCodesClassName_1, "  ");
      _builder.append("() {");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.append("// Prevent instantiation.");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  /**
   * Generates the Java standalone setup class which will be called by the ServiceReigstry.
   */
  public CharSequence compileStandaloneSetup(final CheckCatalog catalog) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _packageName = catalog.getPackageName();
      boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_packageName);
      boolean _not = (!_isNullOrEmpty);
      if (_not) {
        _builder.append("package ");
        String _packageName_1 = catalog.getPackageName();
        _builder.append(_packageName_1, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    _builder.append("import org.apache.log4j.Logger;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.runtime.configuration.ModelLocation;");
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckCatalogRegistry;");
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorRegistry;");
    _builder.newLine();
    _builder.append("import com.avaloq.tools.ddk.check.runtime.registry.ICheckValidatorStandaloneSetup;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Standalone setup for ");
    String _name = catalog.getName();
    _builder.append(_name, " ");
    _builder.append(" as required by the standalone builder.");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("@SuppressWarnings(\"nls\")");
    _builder.newLine();
    _builder.append("public class ");
    String _standaloneSetupClassName = this._checkGeneratorNaming.standaloneSetupClassName(catalog);
    _builder.append(_standaloneSetupClassName, "");
    _builder.append(" implements ICheckValidatorStandaloneSetup {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("private static final Logger LOG = Logger.getLogger(");
    String _standaloneSetupClassName_1 = this._checkGeneratorNaming.standaloneSetupClassName(catalog);
    _builder.append(_standaloneSetupClassName_1, "  ");
    _builder.append(".class);");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("private static final String GRAMMAR_NAME = \"");
    Grammar _grammar = catalog.getGrammar();
    String _name_1 = _grammar.getName();
    _builder.append(_name_1, "  ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("private static final String CATALOG_FILE_PATH = \"");
    String _checkFilePath = this._checkGeneratorNaming.checkFilePath(catalog);
    _builder.append(_checkFilePath, "  ");
    _builder.append("\";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("/** {@inheritDoc} */");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("public void doSetup() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("ICheckValidatorRegistry.INSTANCE.registerValidator(GRAMMAR_NAME, new ");
    String _validatorClassName = this._checkGeneratorNaming.validatorClassName(catalog);
    _builder.append(_validatorClassName, "    ");
    _builder.append("());");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("ICheckCatalogRegistry.INSTANCE.registerCatalog(GRAMMAR_NAME, new ModelLocation(");
    _builder.newLine();
    _builder.append("      ");
    String _standaloneSetupClassName_2 = this._checkGeneratorNaming.standaloneSetupClassName(catalog);
    _builder.append(_standaloneSetupClassName_2, "      ");
    _builder.append(".class.getClassLoader().getResource(CATALOG_FILE_PATH), CATALOG_FILE_PATH));");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("LOG.info(\"Standalone setup done for ");
    String _checkFilePath_1 = this._checkGeneratorNaming.checkFilePath(catalog);
    _builder.append(_checkFilePath_1, "    ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("  ");
    _builder.append("@Override");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("public String toString() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return \"CheckValidatorSetup(");
    Resource _eResource = catalog.eResource();
    URI _uRI = _eResource.getURI();
    String _path = _uRI.path();
    _builder.append(_path, "    ");
    _builder.append(")\";");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  /**
   * Writes contents of the service registry file containing fully qualified class names of all validators.
   * See also http://docs.oracle.com/javase/1.4.2/docs/api/javax/imageio/spi/ServiceRegistry.html
   */
  public CharSequence generateServiceRegistry(final CheckCatalog catalog, final String serviceRegistryFileName, final IFileSystemAccess fsa) {
    CharSequence _xblockexpression = null;
    {
      Map<String, OutputConfiguration> _outputConfigurations = ((AbstractFileSystemAccess) fsa).getOutputConfigurations();
      final OutputConfiguration config = _outputConfigurations.get(CheckGeneratorConstants.CHECK_REGISTRY_OUTPUT);
      String _outputDirectory = config.getOutputDirectory();
      String _plus = (_outputDirectory + "/");
      final String path = (_plus + serviceRegistryFileName);
      final Set<String> contents = this.generatorExtensions.getContents(catalog, path);
      String _qualifiedStandaloneSetupClassName = this._checkGeneratorNaming.qualifiedStandaloneSetupClassName(catalog);
      contents.add(_qualifiedStandaloneSetupClassName);
      StringConcatenation _builder = new StringConcatenation();
      {
        for(final String c : contents) {
          _builder.append(c, "");
          _builder.newLineIfNotEmpty();
        }
      }
      _xblockexpression = _builder;
    }
    return _xblockexpression;
  }
  
  @Override
  public ITreeAppendable _generateMember(final JvmField field, final ITreeAppendable appendable, final GeneratorConfig config) {
    if ((field.isFinal() && (!field.isStatic()))) {
      final FormalParameter parameter = this.compiler.getFormalParameter(field);
      boolean _notEquals = (!Objects.equal(parameter, null));
      if (_notEquals) {
        return appendable;
      }
    }
    return super._generateMember(field, appendable, config);
  }
}
