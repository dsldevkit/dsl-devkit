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

package com.avaloq.tools.ddk.check.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckDocumentationTemplates;
import com.avaloq.tools.ddk.check.generator.CheckGenerator;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * Golden-file regression test for the Check per-catalog documentation generators
 * ({@link CheckGenerator#compileDoc(CheckCatalog)} for the HTML pages and
 * {@link CheckDocumentationTemplates#compileToc(Iterable)} /
 * {@link CheckDocumentationTemplates#compileContexts(Iterable)} for the Eclipse-Help
 * {@code toc.xml} and {@code contexts.xml}).
 *
 * <p>The {@code ExecutionEnvironment.check} and {@code LibraryChecks.check} fixtures and the
 * expected output snapshots are verbatim copies of the committed artifacts in
 * {@code com.avaloq.tools.ddk.check.test.runtime} (sources under {@code src/} and the generated
 * snapshot under {@code docs/}). This test parses the catalogs in-memory, runs the generators
 * and asserts byte-identical output against those golden files, guarding the migrated generators
 * against regressions. It runs in a normal CI test run (no {@code eclipse-run}, no Maven profile).</p>
 */
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckDocGenerationTest extends AbstractCheckTestCase {

  /** Folder (relative to this class' package) holding the input fixtures and golden snapshots. */
  private static final String FIXTURES = "docgen/";

  @Inject
  private CheckGenerator generator;

  @Inject
  private CheckDocumentationTemplates templates;

  @Inject
  private Injector injector;

  @Override
  protected Injector getInjector() {
    return injector;
  }

  /**
   * The per-catalog HTML page emitted by {@link CheckGenerator#compileDoc(CheckCatalog)} must match
   * the committed golden snapshot for each catalog.
   */
  @Test
  public void testCompileDocMatchesGolden() {
    final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    final CheckCatalog executionEnvironment = parse(resourceSet, "ExecutionEnvironment.check");
    final CheckCatalog libraryChecks = parse(resourceSet, "LibraryChecks.check");

    assertGolden("ExecutionEnvironment.html", generator.compileDoc(executionEnvironment));
    assertGolden("LibraryChecks.html", generator.compileDoc(libraryChecks));
  }

  /**
   * The aggregated {@code toc.xml} and {@code contexts.xml} emitted by
   * {@link CheckDocumentationTemplates} across both catalogs must match the committed golden snapshot.
   */
  @Test
  public void testCompileTocAndContextsMatchGolden() {
    final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    final List<CheckCatalog> catalogs = List.of(
        parse(resourceSet, "ExecutionEnvironment.check"),
        parse(resourceSet, "LibraryChecks.check"));

    assertGolden("toc.xml", templates.compileToc(catalogs));
    assertGolden("contexts.xml", templates.compileContexts(catalogs));
  }

  /**
   * Parses a {@code .check} fixture from the {@link #FIXTURES} folder into a {@link CheckCatalog}.
   *
   * @param resourceSet
   *          the resource set to load into
   * @param fileName
   *          the fixture file name (without folder prefix)
   * @return the parsed catalog
   */
  private CheckCatalog parse(final XtextResourceSet resourceSet, final String fileName) {
    final Resource resource = resourceSet.createResource(URI.createURI(FIXTURES + fileName));
    try (InputStream in = getClass().getResourceAsStream(FIXTURES + fileName)) {
      assertNotNull(in, "Missing fixture " + FIXTURES + fileName);
      resource.load(in, null);
    } catch (IOException e) {
      throw new IllegalStateException("Could not load fixture " + fileName, e);
    }
    final CheckCatalog catalog = (CheckCatalog) resource.getContents().get(0);
    assertNotNull(catalog, "Resource " + fileName + " should contain a CheckCatalog");
    return catalog;
  }

  /**
   * Asserts that the generated content equals the committed golden file, comparing with normalized
   * (LF) line endings to mirror the generator's {@code LfNormalizingFileSystemAccess} and stay
   * platform-independent.
   *
   * @param goldenFileName
   *          the golden file name (without folder prefix)
   * @param actual
   *          the generated content
   */
  private void assertGolden(final String goldenFileName, final CharSequence actual) {
    final String expected = readResource(FIXTURES + goldenFileName);
    assertEquals(normalize(expected), normalize(actual.toString()), goldenFileName + " must match the committed golden snapshot");
  }

  /**
   * Reads a classpath resource (relative to this class) as a UTF-8 string.
   *
   * @param name
   *          the resource name
   * @return the resource content
   */
  private String readResource(final String name) {
    final InputStream in = getClass().getResourceAsStream(name);
    assertNotNull(in, "Missing golden resource " + name);
    try (InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
      return CharStreams.toString(reader);
    } catch (IOException e) {
      throw new IllegalStateException("Could not read golden resource " + name, e);
    }
  }

  /**
   * Normalizes line endings to LF, mirroring {@code LfNormalizingFileSystemAccess}.
   *
   * @param text
   *          the text to normalize
   * @return the normalized text
   */
  private static String normalize(final String text) {
    return text.replace("\r\n", "\n").replace("\r", "\n");
  }

}
