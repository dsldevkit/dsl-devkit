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
package com.avaloq.tools.ddk.check.validation;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.osgi.util.NLS;

import com.google.inject.Singleton;


/**
 * A validator utility class which is intended to be used both by the Java validator
 * and the wizards.
 */
@Singleton
public class CheckJavaValidatorUtil {

  /** Two dots in a row. */
  private static final String DOUBLEDOT = ".."; //$NON-NLS-1$

  /** Dollar sign. */
  private static final String DOLLAR = "$"; //$NON-NLS-1$

  /** The plugin ID. */
  private static final String PLUGIN_ID = "com.avaloq.tools.ddk.check.core";

  /**
   * Checks if a given package or project name is legal. A valid package or project name requires the following:
   * <ul>
   * <li>name does not end with '.'
   * <li>no "double dots" occur in the package name
   * <li>all segments of a fully qualified name fulfill conditions as defined by {@link java.lang.Character#isJavaIdentifierPart(char)}
   * </ul>
   *
   * @param fullyQualifiedName
   *          the package name
   * @return <code>true</code> if given FQN is valid
   */
  private IStatus isValidFullyQualifiedName(final String fullyQualifiedName) {
    if (fullyQualifiedName.length() == 0) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.CheckJavaValidator_EMPTY_NAME);
    }

    if (!Character.isJavaIdentifierStart(fullyQualifiedName.charAt(0))) {
      if (fullyQualifiedName.charAt(0) == ' ') {
        return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_START, Messages.CheckJavaValidator_WHITE_SPACE));
      }
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_START, fullyQualifiedName.charAt(0)));
    }

    if (fullyQualifiedName.contains(DOLLAR)) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_CHARACTER, DOLLAR));
    }

    if (fullyQualifiedName.endsWith(".")) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.CheckJavaValidator_ENDS_WITH_DOT);
    }

    if (fullyQualifiedName.contains(DOUBLEDOT)) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_CHARACTER, DOUBLEDOT));
    }

    String[] sequences = fullyQualifiedName.split("\\.");
    for (String sequence : sequences) {
      if (!isValidName(sequence).isOK()) {
        return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_SEQUENCE_ERROR, isValidName(sequence).getMessage()));
      }
    }

    return Status.OK_STATUS;
  }

  /**
   * Checks if a given file or catalog name is valid. A legal name:
   * <ul>
   * <li>must not be empty
   * <li>must not start or end with a white space
   * <li>fulfils conditions as defined by {@link java.lang.Character#isJavaIdentifierPart(char)}
   * </ul>
   *
   * @param name
   *          the name
   * @return true, if successful
   */
  private IStatus isValidName(final String name) {
    if (name.length() == 0) {
      return new Status(IStatus.ERROR, PLUGIN_ID, Messages.CheckJavaValidator_EMPTY_NAME);
    }

    if (!Character.isJavaIdentifierStart(name.charAt(0))) {
      if (name.charAt(0) == ' ') {
        return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_START, Messages.CheckJavaValidator_WHITE_SPACE));
      }
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_START, name.charAt(0)));
    }

    if (name.contains(DOLLAR)) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_CHARACTER, DOLLAR));
    }

    for (int i = 1; i < name.length(); i++) {
      if (!Character.isJavaIdentifierPart(name.charAt(i))) {
        if (name.charAt(i) == ' ') {
          return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_CHARACTER, Messages.CheckJavaValidator_WHITE_SPACE));
        }
        return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_ILLEGAL_CHARACTER, name.charAt(i)));

      }
    }

    return Status.OK_STATUS;
  }

  /**
   * Controls if the name contains an uppercase letter.
   *
   * @param name
   *          the name
   * @return true if name contains uppercase letters
   */
  private boolean containsUppercaseLetters(final String name) {
    for (int i = 0; i < name.length(); i++) {
      if (Character.isUpperCase(name.charAt(i)) && (name.charAt(i) != '.')) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the workspace member.
   *
   * @param name
   *          the name
   * @return the workspace member
   */
  private IResource getWorkspaceMember(final String name) {
    IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
    if (workspaceRoot == null) {
      return null;
    }
    return workspaceRoot.findMember(new Path(name));
  }

  /**
   * Check project name.
   *
   * @param projectName
   *          the name
   * @return true, if successful
   */
  public IStatus checkProjectName(final String projectName) {
    IStatus status = isValidFullyQualifiedName(projectName);
    if (!status.isOK()) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_PROJECT_NAME_STATUS, status.getMessage()));
    }

    if (containsUppercaseLetters(projectName)) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_PROJECT_NAME_STATUS, Messages.CheckJavaValidator_CONTAINS_UPPERCASE));
    }

    IResource container = getWorkspaceMember(projectName);
    if (container != null) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind("Project {0} " + Messages.CheckJavaValidator_EXISTS, projectName));
    }
    return Status.OK_STATUS;
  }

  /**
   * Check package name.
   *
   * @param packageName
   *          the name
   * @return OK_STATUS, if successful, an error status otherwise
   */
  public IStatus checkPackageName(final String packageName) {
    IStatus status = isValidFullyQualifiedName(packageName);
    if (!status.isOK()) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_PACKAGE_NAME_STATUS, status.getMessage()));
    }
    if (containsUppercaseLetters(packageName)) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_PACKAGE_NAME_STATUS, Messages.CheckJavaValidator_CONTAINS_UPPERCASE));
    }
    return Status.OK_STATUS;
  }

  /**
   * Check catalog name.
   *
   * @param name
   *          the name
   * @return OK_STATUS, if successful, an error status otherwise
   */

  public IStatus checkCatalogName(final String name) {
    IStatus status = isValidName(name);
    if (!status.isOK()) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_CATALOG_NAME_STATUS, status.getMessage()));
    }

    if (Character.isLowerCase(name.charAt(0))) {
      return new Status(IStatus.WARNING, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_CATALOG_NAME_STATUS, Messages.CheckJavaValidator_STARTS_WITH_LOWERCASE_WARNING));
    }

    return Status.OK_STATUS;
  }

  /**
   * Check category name.
   *
   * @param categoryName
   *          the name
   * @return OK_STATUS, if successful, an error status otherwise
   */
  public IStatus checkCategoryName(final String categoryName) {
    IStatus status = isValidName(categoryName);
    if (!status.isOK()) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_CATEGORY_NAME_STATUS, status.getMessage()));
    }
    if (Character.isLowerCase(categoryName.charAt(0))) {
      return new Status(IStatus.WARNING, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_CATEGORY_NAME_STATUS, Messages.CheckJavaValidator_STARTS_WITH_LOWERCASE_WARNING));
    }
    return Status.OK_STATUS;
  }

  /**
   * Checks the name of a Check.
   *
   * @param checkName
   *          the name
   * @return OK_STATUS, if successful, an error status otherwise
   */
  public IStatus checkCheckName(final String checkName) {
    IStatus status = isValidName(checkName);
    if (!status.isOK()) {
      return new Status(IStatus.ERROR, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_CHECK_NAME_STATUS, status.getMessage()));
    }
    if (Character.isLowerCase(checkName.charAt(0))) {
      return new Status(IStatus.WARNING, PLUGIN_ID, NLS.bind(Messages.CheckJavaValidator_CHECK_NAME_STATUS, Messages.CheckJavaValidator_STARTS_WITH_LOWERCASE_WARNING));
    }
    return Status.OK_STATUS;
  }

}
