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

import static com.avaloq.tools.ddk.check.runtime.CheckRuntimeConstants.API_EXTENSION_POINT;
import static org.eclipse.osgi.util.NLS.bind;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;
import org.eclipse.xtext.xtype.XImportSection;
import org.eclipse.xtext.xtype.XtypePackage;

import com.google.common.collect.Sets;


/**
 * Checks for check dsl access to API. Based on check provided extension point providing API specifications.
 */
public class ApiAccessChecks extends AbstractDeclarativeValidator {

  private static final String AVALOQ_PREFIX = "com.avaloq."; //$NON-NLS-1$

  private static final String API_PACKAGE = "package"; //$NON-NLS-1$
  // @Format-Off
  private static final Set<String> PUBLIC_API = Platform.getExtensionRegistry() != null
                                                 ?  Stream.of(Platform.getExtensionRegistry().getConfigurationElementsFor(API_EXTENSION_POINT))
                                                          .map(config -> config.getAttribute(API_PACKAGE))
                                                          .filter(Objects::nonNull)
                                                          .map(api -> api.toLowerCase(Locale.ENGLISH))
                                                          .collect(Collectors.toSet())
                                                 :  Sets.newHashSet();
  // @Format-On

  @Override
  public void register(final EValidatorRegistrar registrar) {
    // do nothing
  }

  /**
   * Check that imports from packages designated as "API" in an extension point check API specification. If no specification, ignore.
   *
   * @param importSection
   *          import to be validated
   */
  @Check
  public void checkImportNonPublicApi(final XImportSection importSection) {
    if (!PUBLIC_API.isEmpty()) {
      importSection.getImportDeclarations().forEach(xImport -> {
        if (xImport.isWildcard()) {
          // We know this isn't null and ends with .* from the "isWildcard"
          String nameSpace = xImport.getImportedNamespace();
          validatePackageAccess(nameSpace.substring(0, nameSpace.length() - 2), xImport, XtypePackage.Literals.XIMPORT_DECLARATION__IMPORTED_TYPE);
        } else if (xImport.getImportedType() != null) {
          validatePackageAccess(xImport.getImportedType().getPackageName(), xImport, XtypePackage.Literals.XIMPORT_DECLARATION__IMPORTED_TYPE);
        }
      });
    }
  }

  /**
   * Check that imports from packages designated as "API" in an extension point check API specification. If no specification, ignore.
   *
   * @param typeReference
   *          type reference to be validated
   */
  @Check
  public void checkQualifiedJvmTypeRefNonPublicApi(final JvmTypeReference typeReference) {
    if (!PUBLIC_API.isEmpty()) {
      final JvmType type = typeReference.getType();
      if (type instanceof JvmDeclaredType) {
        validatePackageAccess(((JvmDeclaredType) type).getPackageName(), typeReference, TypesPackage.Literals.JVM_PARAMETERIZED_TYPE_REFERENCE__TYPE);
      }
    }
  }

  /**
   * Given an object accessing a given package, validate and provide a warning if not API.
   *
   * @param packageName
   *          the package being accessed, may be {@code null}
   * @param object
   *          the EObject of access, must not be {@code null}
   * @param feature
   *          the Structural feature to display the warning on, must not be {@code null}
   */
  private void validatePackageAccess(final String packageName, final EObject object, final EStructuralFeature feature) {
    if (packageName != null) {
      String packageLower = packageName.toLowerCase(Locale.ENGLISH);
      if (packageLower != null && packageLower.startsWith(AVALOQ_PREFIX) && !PUBLIC_API.contains(packageLower.toLowerCase(Locale.ENGLISH))) {
        warning(bind(Messages.ApiAccessChecks_IMPORT_NON_PUBLIC_API, packageLower), object, feature, IssueCodes.NON_API_IMPORTED);
      }
    }
  }
}