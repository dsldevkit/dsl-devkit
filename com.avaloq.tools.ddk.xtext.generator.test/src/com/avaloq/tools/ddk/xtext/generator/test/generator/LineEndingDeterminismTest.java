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
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.eclipse.xtext.formatting.ILineSeparatorInformation;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.avaloq.tools.ddk.xtext.formatting.LfLineSeparatorInformation;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * Guarantees that generated-file line endings are decided by the bound
 * {@link ILineSeparatorInformation} — the pipeline's actual enforcement point — and that the
 * {@link LfLineSeparatorInformation} binding normalizes every separator style to LF on the
 * headless ({@link JavaIoFileSystemAccess}) write path, regardless of host platform.
 */
@SuppressWarnings("nls")
public class LineEndingDeterminismTest {

  private static final String MIXED_CONTENT = "a\r\nb\rc\nd";

  @TempDir
  private File tempDir;

  @Test
  public void lfBindingNormalizesAllSeparatorStyles() throws IOException {
    assertEquals("a\nb\nc\nd", generateAndRead(new LfLineSeparatorInformation()), "LF binding must normalize CRLF, CR and LF to LF");
  }

  @Test
  public void harmonizerHonorsConfiguredSeparator() throws IOException {
    assertEquals("a\r\nb\r\nc\r\nd", generateAndRead(() -> "\r\n"), "the post-processor must follow the bound separator; this is the mechanism that made headless output platform-dependent before the LF binding");
  }

  @Test
  public void lfLineSeparatorInformationReturnsLf() {
    assertEquals("\n", new LfLineSeparatorInformation().getLineSeparator(), "LfLineSeparatorInformation must return LF");
  }

  private String generateAndRead(final ILineSeparatorInformation separatorInformation) throws IOException {
    Injector injector = Guice.createInjector(binder -> {
      binder.bind(ILineSeparatorInformation.class).toInstance(separatorInformation);
      binder.bind(IEncodingProvider.class).to(IEncodingProvider.Runtime.class);
    });
    JavaIoFileSystemAccess fsa = injector.getInstance(JavaIoFileSystemAccess.class);
    fsa.setOutputPath(tempDir.getAbsolutePath());
    fsa.generateFile("Sample.txt", MIXED_CONTENT);
    return Files.readString(new File(tempDir, "Sample.txt").toPath(), StandardCharsets.UTF_8);
  }

}
