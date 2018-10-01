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
package com.avaloq.tools.ddk.xtext.parser.antlr;

import java.util.Locale;


/**
 * Provides source file type information.
 */
public class SourceFileType {
  private final String fileExtension;

  /**
   * Creates a new instance of {@link SourceFileType}.
   *
   * @param fileExtension
   *          the file extension, must not be {@code null}
   */
  public SourceFileType(final String fileExtension) {
    this.fileExtension = fileExtension.toUpperCase(Locale.ENGLISH);
  }

  /**
   * Returns the file extension.
   *
   * @return the file extension, never {@code null}
   */
  public String getFileExtension() {
    return fileExtension;
  }

  /**
   * Checks whether the given file extension matches this {@link SourceFileType}.
   *
   * @param otherExtension
   *          the file extension in upper case to check, must not be {@code null}
   * @return {@code true} if the file extension matches, {@code false} otherwise or if there is no source type information available
   */
  public boolean matches(final String otherExtension) {
    return fileExtension != null && fileExtension.hashCode() == otherExtension.hashCode();
  }
}
