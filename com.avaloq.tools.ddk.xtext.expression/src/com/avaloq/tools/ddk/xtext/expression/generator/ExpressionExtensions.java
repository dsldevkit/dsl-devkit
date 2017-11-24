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
package com.avaloq.tools.ddk.xtext.expression.generator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.serializer.ISerializer;

import com.avaloq.tools.ddk.xtext.expression.ExpressionStandaloneSetup;


/**
 * Utilities for the Expression language.
 */
public final class ExpressionExtensions {

  /** The SERIALIZER. */
  private static final ISerializer SERIALIZER = new ExpressionStandaloneSetup().createInjector().getInstance(ISerializer.class);

  /**
   * Private constructor (Singleton).
   */
  private ExpressionExtensions() {
    // Does nothing
  }

  /**
   * Serialize an object into a String.
   *
   * @param obj
   *          the object
   * @return the string representation
   */
  public static String serialize(final EObject obj) {
    if (obj == null) {
      return ""; //$NON-NLS-1$
    }
    final ICompositeNode node = NodeModelUtils.getNode(obj);
    if (node != null) {
      return node.getText().trim();
    }

    return SERIALIZER.serialize(obj).trim();
  }
}
