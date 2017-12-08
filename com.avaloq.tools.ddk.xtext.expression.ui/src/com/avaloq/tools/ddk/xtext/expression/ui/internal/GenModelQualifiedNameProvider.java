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

package com.avaloq.tools.ddk.xtext.expression.ui.internal;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider.AbstractImpl;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.util.Tuples;

import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * Qualified name provider which will export any GenModel objects using the corresponding EPackage's nsURI.
 */
public class GenModelQualifiedNameProvider extends AbstractImpl {

  @Inject
  private IResourceScopeCache cache;

  @Override
  public QualifiedName getFullyQualifiedName(final EObject obj) {
    return cache.get(Tuples.pair(obj, getCacheKey()), obj.eResource(), new Provider<QualifiedName>() {
      @Override
      public QualifiedName get() {
        if (obj instanceof GenPackage) {
          String nsUri = ((GenPackage) obj).getNSURI();
          return nsUri != null ? QualifiedName.create(nsUri) : null;
        }
        return null;
      }
    });
  }

  private String getCacheKey() {
    return "fqn";
  }

}
