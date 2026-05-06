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

package com.avaloq.tools.ddk.check.generator;

import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.generator.IFileSystemAccess2;

import com.google.common.base.Preconditions;


/**
 * A delegating {@link IFileSystemAccess2} that normalizes line endings to LF ({@code \n})
 * before writing content. This ensures generated files are platform-independent regardless
 * of the OS on which the build runs.
 *
 * <p>Implements {@link IFileSystemAccess2} so that {@code instanceof} checks in the framework
 * (e.g., in {@code JvmModelGenerator}) continue to work and no behavior is lost.</p>
 */
public class LfNormalizingFileSystemAccess implements IFileSystemAccess2 {

  private final IFileSystemAccess2 delegate;

  /**
   * Wraps the given delegate. Callers that hold the weaker {@link org.eclipse.xtext.generator.IFileSystemAccess}
   * (e.g. from Xtext's {@code Generator2#doGenerate(Resource, IFileSystemAccess)}) must cast at the
   * call site — every default Xtext FSA implementation is also an {@link IFileSystemAccess2}.
   *
   * @param delegate the delegate to wrap, must not be {@code null}
   */
  public LfNormalizingFileSystemAccess(final IFileSystemAccess2 delegate) {
    this.delegate = Preconditions.checkNotNull(delegate);
  }

  @Override
  public void generateFile(final String fileName, final CharSequence contents) {
    delegate.generateFile(fileName, normalizeLineEndings(contents));
  }

  @Override
  public void generateFile(final String fileName, final String outputConfigName, final CharSequence contents) {
    delegate.generateFile(fileName, outputConfigName, normalizeLineEndings(contents));
  }

  @Override
  public void deleteFile(final String fileName) {
    delegate.deleteFile(fileName);
  }

  @Override
  public void generateFile(final String fileName, final InputStream content) {
    delegate.generateFile(fileName, content);
  }

  @Override
  public void generateFile(final String fileName, final String outputConfigName, final InputStream content) {
    delegate.generateFile(fileName, outputConfigName, content);
  }

  @Override
  public URI getURI(final String fileName, final String outputConfigName) {
    return delegate.getURI(fileName, outputConfigName);
  }

  @Override
  public URI getURI(final String fileName) {
    return delegate.getURI(fileName);
  }

  @Override
  public void deleteFile(final String fileName, final String outputConfigName) {
    delegate.deleteFile(fileName, outputConfigName);
  }

  @Override
  public InputStream readBinaryFile(final String fileName, final String outputConfigName) {
    return delegate.readBinaryFile(fileName, outputConfigName);
  }

  @Override
  public InputStream readBinaryFile(final String fileName) {
    return delegate.readBinaryFile(fileName);
  }

  @Override
  public CharSequence readTextFile(final String fileName, final String outputConfigName) {
    return delegate.readTextFile(fileName, outputConfigName);
  }

  @Override
  public CharSequence readTextFile(final String fileName) {
    return delegate.readTextFile(fileName);
  }

  @Override
  public boolean isFile(final String path, final String outputConfigurationName) {
    return delegate.isFile(path, outputConfigurationName);
  }

  @Override
  public boolean isFile(final String path) {
    return delegate.isFile(path);
  }

  private static CharSequence normalizeLineEndings(final CharSequence content) {
    if (content == null) {
      return null;
    }
    String text = content.toString();
    if (text.indexOf('\r') < 0) {
      return content;
    }
    return text.replace("\r\n", "\n").replace("\r", "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }

}
