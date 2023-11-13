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
package com.avaloq.tools.ddk.xtext.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.util.StringInputStream;

import org.eclipse.emf.common.util.WrappedException;
import com.google.common.io.Resources;


/**
 * Container for all source relevant information.
 */
public class TestSource {

  private static final Pattern NL_PATTERN = Pattern.compile("\\r?\\n");

  private final String sourceFileName;

  private URI uri;
  private IFile file;

  private String content;

  public TestSource(final String sourceFileName, final String content) {
    this.sourceFileName = sourceFileName;
    this.content = content;
  }

  /**
   * Called by {@link ITestProjectManager#addSourceToProject(TestSource)} when this source was added.
   * <p>
   * TODO Implement a cleaner solution where IFile and URI are not stored by TestSource.
   * 
   * @param newFile
   *          file associated with this test source
   * @param newUri
   *          URI associated with this test source
   */
  public void accept(final IFile newFile, final URI newUri) {
    file = newFile;
    uri = newUri;
  }

  /**
   * Returns the sourceFileName.
   * 
   * @return the sourceFileName
   */
  public String getSourceFileName() {
    return sourceFileName;
  }

  /**
   * Returns the iFile.
   * 
   * @return the iFile
   */
  public IFile getiFile() {
    return file;
  }

  /**
   * Returns the Uri.
   * 
   * @return the Uri
   */
  public URI getUri() {
    return uri;
  }

  /**
   * Returns the content.
   * 
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * Set the the source content to the new content given.
   * 
   * @param newContent
   *          String with the new source content
   */
  public void setContent(final String newContent) {
    try {
      file.setContents(new StringInputStream(newContent), true, false, new NullProgressMonitor());
    } catch (CoreException e) {
      throw new WrappedException("failed to write test source content to file " + sourceFileName, e);
    }
    content = newContent;
  }

  /**
   * Loads the given source from the classpath and normalizes the line endings of the content.
   * 
   * @param clazz
   *          context class
   * @param sourceFileName
   *          name of source to load
   * @return source's content
   */
  public static String getResourceContent(final Class<?> clazz, final String sourceFileName) {
    try {
      String content = Resources.toString(Resources.getResource(clazz, sourceFileName), Charset.defaultCharset());
      return normalizeLineSeparators(content, "\n");
    } catch (IOException e) {
      throw new WrappedException("failed to load resource " + sourceFileName, e);
    }
  }

  /**
   * Returns a string with line separators normalized to the given terminator.
   * 
   * @param str
   *          string to normalize, must not be {@code null}
   * @param lineSeparator
   *          separator to use (e.g. "\n"), must not be {@code null}
   * @return string with normalized line separators, never {@code null}
   */
  public static String normalizeLineSeparators(final String str, final String lineSeparator) {
    return NL_PATTERN.matcher(str).replaceAll(lineSeparator);
  }

}
