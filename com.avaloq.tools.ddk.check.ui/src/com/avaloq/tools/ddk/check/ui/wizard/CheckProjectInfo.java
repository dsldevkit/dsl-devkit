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
package com.avaloq.tools.ddk.check.ui.wizard;

import java.util.Collection;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.GeneratedMetamodel;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.ui.wizard.DefaultProjectInfo;

import com.avaloq.tools.ddk.check.util.GrammarHelper;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;


/**
 * Aggregates project information collected from UI.
 */
@SuppressWarnings("nls")
public class CheckProjectInfo extends DefaultProjectInfo {

  private String catalogName;
  private String path;
  private String packageName;
  private Grammar grammar;
  private GrammarHelper grammarHelper;
  private IPackageFragment packageFragment;

  public String getCatalogName() {
    return catalogName;
  }

  public void setCatalogName(final String catalogName) {
    this.catalogName = catalogName;
  }

  /**
   * Sets the grammar to use for this project and creates the corresponding helper.
   *
   * @param projectGrammar
   *          the grammar to use for this project
   */
  public void setGrammar(final Grammar projectGrammar) {
    this.grammar = projectGrammar;
    this.grammarHelper = new GrammarHelper(projectGrammar);
  }

  public Grammar getGrammar() {
    return grammar;
  }

  public Collection<String> getDslDependency() {
    return grammarHelper.getRequiredBundleSymbolicNames();
  }

  /**
   * Gets the default package import. Returns the package name of the Java package in which the interface
   * of the grammar's top-level grammar rule is located.
   * <p>
   * A typical location for this would be {@code org.xtext.example.mydsl.myDsl}
   * </p>
   *
   * @return the default package import or {@code null} if no default package was found
   */
  public String getDefaultPackageImport() {
    AbstractMetamodelDeclaration defaultDeclaration = grammar.getRules().isEmpty() ? null : grammar.getRules().get(0).getType().getMetamodel(); // NOPMD
    AbstractMetamodelDeclaration declaration = Iterables.find(grammar.getMetamodelDeclarations(), Predicates.instanceOf(GeneratedMetamodel.class), defaultDeclaration);
    if (declaration != null) {
      return grammarHelper.getFirstInterface(declaration).getPackage().getName();
    }
    return null;
  }

  /**
   * Gets the path to where files should be generated. If {@code path} is {@code null}, a default is calculated.
   *
   * @return the path
   */
  public String getPath() {
    if (this.path == null) {
      return "src" + "/" + this.packageName.replace('.', '/') + "/";
    }
    return this.path;
  }

  public void setPath(final String path) {
    this.path = path;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(final String packageName) {
    this.packageName = packageName;
  }

  public IPackageFragment getPackageFragment() {
    return packageFragment;
  }

  public void setPackageFragment(final IPackageFragment packageFragment) {
    this.packageFragment = packageFragment;
  }

  public Collection<String> getMetamodelImports() {
    return Collections2.transform(grammarHelper.getMetamodelPackages(), importPackageName -> importPackageName + ".*");
  }

}
