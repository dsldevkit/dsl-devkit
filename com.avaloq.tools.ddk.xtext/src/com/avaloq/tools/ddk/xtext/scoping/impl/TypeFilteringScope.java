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
package com.avaloq.tools.ddk.xtext.scoping.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.FilteringScope;

import com.google.common.base.Predicate;


/**
 * A type filtering scope implementation.
 */
public class TypeFilteringScope extends FilteringScope {

  public TypeFilteringScope(final IScope delegate, final EClass... allowedTypes) {
    super(delegate, new Predicate<IEObjectDescription>() {
      @Override
      public boolean apply(final IEObjectDescription description) {
        EClass actualType = description.getEObjectOrProxy().eClass();
        for (final EClass eClass : allowedTypes) {
          if (eClass.isSuperTypeOf(actualType)) {
            return true;
          }
        }
        return false;
      }
    });
  }

}
