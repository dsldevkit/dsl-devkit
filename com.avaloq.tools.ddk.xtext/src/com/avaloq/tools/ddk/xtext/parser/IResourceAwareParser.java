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
package com.avaloq.tools.ddk.xtext.parser;

/**
 * A parser that is aware of a potential resource container providing the parse input.
 */
public interface IResourceAwareParser {

  /**
   * Sets the file extension of the resource being parsed.
   *
   * @param fileExtension
   *          the file extension of the resource, may be {@code null}
   */
  void setFileExtension(String fileExtension);

  /**
   * Returns the file extension of the resource being parsed.
   *
   * @return the file extension of the resource being parsed, or {@code null} if not known
   */
  String getFileExtension();
}
