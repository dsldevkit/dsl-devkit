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
import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class CheckGeneratorNaming {
  @Inject
  private IQualifiedNameProvider nameProvider;
  
  public <T extends EObject> T parent(final EObject object, final Class<T> c) {
    return EcoreUtil2.<T>getContainerOfType(object, c);
  }
  
  private String asPath(final String javaPackageName) {
    String _xifexpression = null;
    boolean _notEquals = (!Objects.equal(javaPackageName, null));
    if (_notEquals) {
      String _replace = javaPackageName.replace(".", "/");
      _xifexpression = (_replace + "/");
    } else {
      _xifexpression = "";
    }
    return _xifexpression;
  }
  
  /**
   * Gets the class name of the check validator.
   */
  public String validatorClassName(final CheckCatalog c) {
    String _name = c.getName();
    return (_name + "CheckImpl");
  }
  
  /**
   * Gets the fully qualified class name of the check validator.
   */
  public String qualifiedValidatorClassName(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _plus = (_packageName + ".");
    String _validatorClassName = this.validatorClassName(c);
    return (_plus + _validatorClassName);
  }
  
  /**
   * Gets the file path of the check validator.
   */
  public String validatorFilePath(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _asPath = this.asPath(_packageName);
    String _validatorClassName = this.validatorClassName(c);
    String _plus = (_asPath + _validatorClassName);
    return (_plus + ".java");
  }
  
  /**
   * Gets the check catalog class name.
   */
  public String catalogClassName(final CheckCatalog c) {
    String _name = c.getName();
    return (_name + "CheckCatalog");
  }
  
  /**
   * Gets the qualified check catalog class name.
   */
  public String qualifiedCatalogClassName(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _plus = (_packageName + ".");
    String _catalogClassName = this.catalogClassName(c);
    return (_plus + _catalogClassName);
  }
  
  /**
   * Gets the preference initializer class name.
   */
  public String preferenceInitializerClassName(final CheckCatalog c) {
    String _name = c.getName();
    return (_name + "PreferenceInitializer");
  }
  
  /**
   * Gets the qualified standalone setup class name.
   */
  public String qualifiedStandaloneSetupClassName(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _plus = (_packageName + ".");
    String _standaloneSetupClassName = this.standaloneSetupClassName(c);
    return (_plus + _standaloneSetupClassName);
  }
  
  /**
   * Gets the standalone setup class name.
   */
  public String standaloneSetupClassName(final CheckCatalog c) {
    String _name = c.getName();
    return (_name + "StandaloneSetup");
  }
  
  /**
   * Gets the qualified preference initializer class name.
   */
  public String qualifiedPreferenceInitializerClassName(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _plus = (_packageName + ".");
    String _preferenceInitializerClassName = this.preferenceInitializerClassName(c);
    return (_plus + _preferenceInitializerClassName);
  }
  
  /**
   * Gets the standalone setup class file path.
   */
  public String standaloneSetupPath(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _asPath = this.asPath(_packageName);
    String _standaloneSetupClassName = this.standaloneSetupClassName(c);
    String _plus = (_asPath + _standaloneSetupClassName);
    return (_plus + ".java");
  }
  
  /**
   * Gets the documentation file name.
   */
  public String docFileName(final CheckCatalog c) {
    String _name = c.getName();
    return (_name + ".html");
  }
  
  /**
   * Gets the issue codes class name.
   */
  public String issueCodesClassName(final CheckCatalog c) {
    String _name = c.getName();
    return (_name + "IssueCodes");
  }
  
  /**
   * Gets the issue codes file path.
   */
  public String issueCodesFilePath(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _asPath = this.asPath(_packageName);
    String _issueCodesClassName = this.issueCodesClassName(c);
    String _plus = (_asPath + _issueCodesClassName);
    return (_plus + ".java");
  }
  
  /**
   * Gets the quickfix provider class name.
   */
  public String quickfixClassName(final CheckCatalog c) {
    String _name = c.getName();
    return (_name + "QuickfixProvider");
  }
  
  /**
   * Gets the qualified quickfix provider class name.
   */
  public String qualifiedQuickfixClassName(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _plus = (_packageName + ".");
    String _quickfixClassName = this.quickfixClassName(c);
    return (_plus + _quickfixClassName);
  }
  
  /**
   * Gets the quickfix provider file path.
   */
  public String quickfixFilePath(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _asPath = this.asPath(_packageName);
    String _quickfixClassName = this.quickfixClassName(c);
    String _plus = (_asPath + _quickfixClassName);
    return (_plus + ".java");
  }
  
  /**
   * Gets the full path to the check file, e.g. com/avaloq/MyChecks.check.
   */
  public String checkFilePath(final CheckCatalog c) {
    String _packageName = c.getPackageName();
    String _asPath = this.asPath(_packageName);
    String _name = c.getName();
    String _plus = (_asPath + _name);
    return (_plus + ".check");
  }
  
  /**
   * Gets the name of the getter method generated for a formal parameter.
   */
  public String formalParameterGetterName(final FormalParameter p) {
    EObject _eContainer = p.eContainer();
    final Check check = ((Check) _eContainer);
    String _name = check.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    String _plus = ("get" + _firstUpper);
    String _plus_1 = (_plus + "_");
    String _name_1 = p.getName();
    String _firstUpper_1 = StringExtensions.toFirstUpper(_name_1);
    return (_plus_1 + _firstUpper_1);
  }
  
  /**
   * Check catalog instance name in the validator
   */
  public String catalogInstanceName(final EObject object) {
    CheckCatalog _containerOfType = EcoreUtil2.<CheckCatalog>getContainerOfType(object, CheckCatalog.class);
    String _name = _containerOfType.getName();
    String _firstLower = StringExtensions.toFirstLower(_name);
    return (_firstLower + "Catalog");
  }
  
  /**
   * Gets the name of the default validator class.
   */
  public String defaultValidatorClassName() {
    return "DefaultCheckImpl";
  }
  
  /**
   * Gets the fully qualified name of the default validator class.
   */
  public String qualifiedDefaultValidatorClassName() {
    String _defaultValidatorClassName = this.defaultValidatorClassName();
    return ("com.avaloq.tools.ddk.check.runtime.issue." + _defaultValidatorClassName);
  }
  
  /**
   * Gets the prefix for the context id (used in contexts.xml)
   */
  public String getContextIdPrefix(final QualifiedName catalog) {
    String _lastSegment = catalog.getLastSegment();
    String _string = _lastSegment.toString();
    String _lowerCase = _string.toLowerCase();
    return (_lowerCase + "_");
  }
  
  /**
   * Gets the full context id (used in contexts.xml)
   */
  public String getContextId(final Check check) {
    String _xblockexpression = null;
    {
      final CheckCatalog catalog = this.<CheckCatalog>parent(check, CheckCatalog.class);
      QualifiedName _apply = this.nameProvider.apply(catalog);
      String _contextIdPrefix = this.getContextIdPrefix(_apply);
      String _label = check.getLabel();
      String _replaceAll = _label.replaceAll(" ", "");
      String _replaceAll_1 = _replaceAll.replaceAll("\"", "");
      String _replaceAll_2 = _replaceAll_1.replaceAll("\'", "");
      String _lowerCase = _replaceAll_2.toLowerCase();
      _xblockexpression = (_contextIdPrefix + _lowerCase);
    }
    return _xblockexpression;
  }
  
  /**
   * Gets the full context id (used in contexts.xml)
   */
  public String getContextId(final Category category) {
    String _xblockexpression = null;
    {
      final CheckCatalog catalog = this.<CheckCatalog>parent(category, CheckCatalog.class);
      QualifiedName _apply = this.nameProvider.apply(catalog);
      String _contextIdPrefix = this.getContextIdPrefix(_apply);
      String _label = category.getLabel();
      String _replaceAll = _label.replaceAll(" ", "");
      String _replaceAll_1 = _replaceAll.replaceAll("\"", "");
      String _replaceAll_2 = _replaceAll_1.replaceAll("\'", "");
      String _lowerCase = _replaceAll_2.toLowerCase();
      _xblockexpression = (_contextIdPrefix + _lowerCase);
    }
    return _xblockexpression;
  }
}
