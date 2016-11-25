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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;

import com.avaloq.tools.ddk.xtext.util.EObjectUtil;


/**
 * Abstract implementation of an IContextSupplier, offering additional utility methods to simplify code generation.
 * The idea here is that the generated context suppliers deriuve from this class and use the makeIterable method
 * to handle single-valued and list-valued xtend expressions the same by always calling makeIterable.
 */
public abstract class AbstractContextSupplier implements IContextSupplier {

  /**
   * Prepare an iterable of context objects for use by a delegating scope, filtering out all null elements and proxies.
   *
   * @param elements
   *          an Iterable
   * @return The Iterable passed as a parameter
   */
  protected Iterable<? extends EObject> makeIterable(final Iterable<? extends EObject> elements) {
    return EObjectUtil.filterProxies(elements);
  }

  /**
   * Convert a single object to an iterable. If the parameter is null or a proxy, an empty list is returned.
   *
   * @param element
   *          The object
   * @return An iterable containing exactly the object.
   */
  protected Iterable<? extends EObject> makeIterable(final EObject element) {
    if (element == null || element.eIsProxy()) {
      return Collections.emptyList();
    }
    return Collections.singleton(element);
  }

}
