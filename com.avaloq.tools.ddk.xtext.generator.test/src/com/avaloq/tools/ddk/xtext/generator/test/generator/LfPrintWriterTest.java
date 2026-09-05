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
package com.avaloq.tools.ddk.xtext.generator.test.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.avaloq.tools.ddk.xtext.generator.parser.antlr.KeywordAnalysisHelper;


/**
 * Guarantees that {@code KeywordAnalysisHelper}'s report writer terminates lines with LF on
 * every platform. The keyword reports are committed to git, so a platform-dependent
 * {@link PrintWriter#println()} would rewrite them on every Windows build. All
 * {@code println(...)} overloads are specified to terminate via {@code println()}, so
 * asserting the no-argument terminator covers every call site.
 */
@SuppressWarnings("nls")
public class LfPrintWriterTest {

  @TempDir
  private File tempDir;

  @Test
  public void lfPrintWriterTerminatesWithLfOnly() throws Exception {
    File file = new File(tempDir, "report.txt");
    Class<?> lfPrintWriter = Class.forName(KeywordAnalysisHelper.class.getName() + "$LfPrintWriter", true, KeywordAnalysisHelper.class.getClassLoader());
    Constructor<?> constructor = lfPrintWriter.getDeclaredConstructor(File.class);
    constructor.setAccessible(true);
    try (PrintWriter writer = (PrintWriter) constructor.newInstance(file)) {
      writer.println("first");
      writer.println();
      writer.print("second");
      writer.println(42);
    }
    assertEquals("first\n\nsecond42\n", Files.readString(file.toPath(), StandardCharsets.UTF_8), "every println termination must be a bare LF");
  }

}
