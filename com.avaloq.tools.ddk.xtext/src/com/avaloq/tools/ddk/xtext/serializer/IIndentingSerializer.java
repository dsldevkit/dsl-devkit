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
package com.avaloq.tools.ddk.xtext.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.serializer.ISerializer;

import com.google.inject.ImplementedBy;


/**
 * Extend {@link ISerializer} in order to enrich it with an interface to provide initial indentation.
 */
@ImplementedBy(IndentingSerializer.class)
public interface IIndentingSerializer extends ISerializer {

  /**
   * Serialize the given object with an initial indentation.
   *
   * @param obj
   *          the obj
   * @param options
   *          the options
   * @param initialIndentation
   *          the initial indentation
   * @return the serialized object
   */
  String serialize(EObject obj, SaveOptions options, String initialIndentation);
}
