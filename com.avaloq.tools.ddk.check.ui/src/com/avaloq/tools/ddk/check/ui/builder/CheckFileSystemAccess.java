/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.ui.builder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.xtext.builder.JDTAwareEclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.util.RuntimeIOException;


/**
 * Touches a generated file whose content did not change only if its trace file changed.
 * <p>
 * Touching makes the Java builder compile the file again, which installs the debug information from the trace file. A file with unchanged content and an
 * unchanged trace file needs neither.
 * </p>
 */
public class CheckFileSystemAccess extends JDTAwareEclipseResourceFileSystemAccess2 {

  /** Stands for the content of a trace file that could not be read. */
  private static final byte[] UNREADABLE = {};

  @Override
  protected boolean shouldTouch(final IFile file) throws CoreException {
    return false; // generateFile touches the file if its trace file changed
  }

  /**
   * {@inheritDoc}
   * <p>
   * An unchanged file is touched after the callback was told about the update.
   * </p>
   */
  @Override
  protected void generateFile(final IFile file, final InputStream content, final IFile traceFile, final CharSequence traceContent, final OutputConfiguration outputConfig) {
    if (file == null || traceFile == null || !file.exists() || !outputConfig.isOverrideExistingResources()) {
      super.generateFile(file, content, traceFile, traceContent, outputConfig);
      return;
    }
    long stamp = file.getModificationStamp();
    byte[] trace = read(traceFile);
    super.generateFile(file, content, traceFile, traceContent, outputConfig);
    if (file.getModificationStamp() == stamp && !isSame(trace, read(traceFile))) {
      try {
        file.touch(getMonitor());
      } catch (CoreException e) {
        throw new RuntimeIOException(e);
      }
    }
  }

  /**
   * Reads the given file.
   *
   * @param file
   *          the file to read, must not be {@code null}
   * @return the content of the file, {@code null} if it does not exist, or {@link #UNREADABLE} if it cannot be read
   */
  @SuppressWarnings("PMD.ReturnEmptyCollectionRatherThanNull") // a missing file differs from an empty one
  private byte[] read(final IFile file) {
    if (!file.exists()) {
      return null;
    }
    try (InputStream stream = file.getContents(true)) {
      return stream.readAllBytes();
    } catch (CoreException | IOException e) {
      return UNREADABLE;
    }
  }

  /**
   * Whether a file had the same content before and after it was written.
   *
   * @param before
   *          the content before, as returned by {@link #read(IFile)}
   * @param after
   *          the content after, as returned by {@link #read(IFile)}
   * @return {@code true} if both could be read and are equal
   */
  @SuppressWarnings("PMD.CompareObjectsWithEquals") // UNREADABLE is a marker instance
  private boolean isSame(final byte[] before, final byte[] after) {
    return before != UNREADABLE && after != UNREADABLE && Arrays.equals(before, after);
  }

}
