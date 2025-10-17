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
package com.avaloq.tools.ddk.check.validation;

import static org.eclipse.xtext.util.Strings.equal;
import static org.eclipse.xtext.util.Strings.notNull;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;


/**
 * Largely inspired from org.eclipse.xtext.xtend2.validation.ClasspathBasedChecks.
 * TODO: This code should go in utility functions...
 */
@SuppressWarnings("nls")
public class ClasspathBasedChecks extends AbstractDeclarativeValidator {

  private static final String SLASH = "/";
  private static final String DOT = ".";

  @Override
  public void register(final EValidatorRegistrar registrar) {
    // do nothing
  }

  /**
   * Verifies that a given catalog file has the same name as the name given in the model.
   * Also verifies that the given package exists and that the file is in that package.
   *
   * @param catalog
   *          a check catalog
   */
  @Check
  public void checkFileNamingConventions(final CheckCatalog catalog) {
    Resource resource = catalog.eResource();
    URI resourceURI = resource.getURI();
    String packageName = catalog.getPackageName();
    StringBuilder classpathURIBuilder = new StringBuilder(ClasspathUriUtil.CLASSPATH_SCHEME);
    classpathURIBuilder.append(":/");
    if (packageName != null) {
      classpathURIBuilder.append(packageName.replace(DOT, SLASH)).append(SLASH);
    }
    classpathURIBuilder.append(resourceURI.lastSegment());
    URI classpathURI = URI.createURI(classpathURIBuilder.toString());
    URIConverter uriConverter = resource.getResourceSet().getURIConverter();
    try {
      URI normalizedClasspathURI = uriConverter.normalize(classpathURI);
      URI normalizedResourceURI = uriConverter.normalize(resourceURI);
      // Must normalize both URIs... however, pre-Xtext 2.4.3 we only normalized the classpath URI, and it worked?!
      // Just to be sure we don't break anything, leave that earlier behavior in.
      if (!normalizedResourceURI.equals(normalizedClasspathURI) && !resourceURI.equals(normalizedClasspathURI)) {
        reportInvalidPackage(catalog, packageName, null);
      }
    } catch (ClasspathUriResolutionException e) {
      reportInvalidPackage(catalog, packageName, null);
    }
    String catalogName = catalog.getName();
    if (catalogName != null && !equal(resourceURI.trimFileExtension().lastSegment(), catalogName)) {
      error("The catalog '" + (packageName != null ? notNull(packageName) + DOT : "") + catalogName
          + "' must be defined in its own file", catalog, CheckPackage.Literals.CHECK_CATALOG__NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.WRONG_FILE);
    }
  }

  /**
   * Reports an invalid package error.
   *
   * @param catalog
   *          the catalog
   * @param givenPackageName
   *          the given package name
   * @param expectedPackageName
   *          the expected name
   */
  protected void reportInvalidPackage(final CheckCatalog catalog, final String givenPackageName, final String expectedPackageName) {
    if (expectedPackageName != null) {
      error("The declared package '" + notNull(givenPackageName)
          + "' does not match the expected package", catalog, CheckPackage.Literals.CHECK_CATALOG__PACKAGE_NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.WRONG_PACKAGE, expectedPackageName);
    } else {
      error("The declared package '" + notNull(givenPackageName)
          + "' does not match the expected package", catalog, CheckPackage.Literals.CHECK_CATALOG__PACKAGE_NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.WRONG_PACKAGE);
    }
  }
}
