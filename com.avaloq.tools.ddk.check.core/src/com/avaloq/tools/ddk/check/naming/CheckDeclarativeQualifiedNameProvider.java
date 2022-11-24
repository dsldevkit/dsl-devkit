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
package com.avaloq.tools.ddk.check.naming;

import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.scoping.XbaseQualifiedNameProvider;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.google.inject.Inject;


/**
 * Configures qualified names for Check model elements.
 */
public class CheckDeclarativeQualifiedNameProvider extends XbaseQualifiedNameProvider {

  @Inject
  private IQualifiedNameConverter converter;

  /**
   * Gets the qualified name for a {@link CheckCatalog Check Catalog}.
   * 
   * @param catalog
   *          the check catalog
   * @return the fully qualified name
   */
  QualifiedName qualifiedName(final CheckCatalog catalog) {
    return converter.toQualifiedName(catalog.getPackageName() + "." + catalog.getName());
  }

  /**
   * Creates a qualified name for a given Check instance. The first segment of the
   * qualified name corresponds to the parent catalog name.
   * 
   * @param check
   *          the check
   * @return the qualified name
   */
  QualifiedName qualifiedName(final Check check) {
    // name of check can be null while editing
    String name = check.getName();
    return qualifiedName(EcoreUtil2.getContainerOfType(check, CheckCatalog.class)).append(name != null ? name : "null");
  }

}

