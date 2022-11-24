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
package com.avaloq.tools.ddk.xtext.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.CharacterCodingException;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;


/**
 * An Xtext resource that also manages an optional GMF part.
 */
public interface IXtextGMFResource extends Resource {

  /**
   * Tests if resource is a combined Xtext-GMF resource.
   * 
   * @return true if resource is loaded and has an GMF part.
   */
  boolean isCombinedResource();

  /**
   * Gets the diagram text part of resource, if present.
   * 
   * @return the diagram text if <code>isCombinedResource</code>, else an empty string.
   */
  String getDiagramText();

  /**
   * Gets the Xtext part of resource as an input stream.
   * 
   * @return returns the xtext part of resource if loaded, else an empty stream.
   */
  InputStream getXtextInput();

  /**
   * Serializes the corresponding Xtext model onto an output stream.
   * This is the behavior that Xtext clients expect, so the diagram
   * part will not be part of the output.
   * 
   * @param outputStream
   *          the output stream for serialization.
   * @param options
   *          additional options for serialization.
   * @throws IOException
   *           if an I/O error occurs during serialization.
   */
  void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException;

  /**
   * Update this resource so that its diagram part is the same that of
   * the specified resource.
   * 
   * @param fromResource
   *          the resource from which to take the diagram part.
   * @throws CharacterCodingException
   *           if unable to encode <code>fromResource.getDiagramText()</code>
   */
  void updateDiagram(IXtextGMFResource fromResource) throws CharacterCodingException;

}

