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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckDocumentationTemplates;
import com.avaloq.tools.ddk.check.generator.CheckGenerator;
import com.avaloq.tools.ddk.check.standalone.CheckDocApplication;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * Golden-file regression test for the Check documentation generators ({@link CheckGenerator#compileDoc(CheckCatalog)},
 * {@link CheckDocumentationTemplates}) and the headless {@link CheckDocApplication}.
 *
 * <p>The {@code ExecutionEnvironment.check} and {@code LibraryChecks.check} fixtures are copies of the catalogs in
 * {@code com.avaloq.tools.ddk.check.test.runtime}, and the golden files are copies of its committed {@code docs/}:
 * {@code toc.xml} and {@code contexts.xml} as written by the PDE-based IDE builder, the HTML pages and
 * {@code index.html} as written by the headless application. Regenerating those docs with the {@code generateCheckDocs}
 * profile must therefore leave them unchanged. {@code Special.check} covers catalog-level checks and escaping.</p>
 */
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckDocGenerationTest extends AbstractCheckTestCase {

  /** Folder (relative to this class' package) holding the input fixtures and golden snapshots. */
  private static final String FIXTURES = "docgen/";

  private static final String EXECUTION_ENVIRONMENT = "ExecutionEnvironment";
  private static final String LIBRARY_CHECKS = "LibraryChecks";
  private static final String TOC = "toc.xml";
  private static final String CONTEXTS = "contexts.xml";
  private static final String INDEX = "index.html";
  private static final String NOTHING_WRITTEN = "nothing may be written on failure";

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
   * the golden snapshot for each catalog.
   */
  @Test
  public void testCompileDocMatchesGolden() {
    final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    final CheckCatalog executionEnvironment = parse(resourceSet, EXECUTION_ENVIRONMENT + ".check");
    final CheckCatalog libraryChecks = parse(resourceSet, LIBRARY_CHECKS + ".check");

    assertGolden(EXECUTION_ENVIRONMENT + ".html", generator.compileDoc(executionEnvironment));
    assertGolden(LIBRARY_CHECKS + ".html", generator.compileDoc(libraryChecks));
  }

  /**
   * The aggregated {@code toc.xml}, {@code contexts.xml} and {@code index.html} across both catalogs must match the
   * golden snapshot, which for {@code toc.xml} and {@code contexts.xml} is the PDE builder's output.
   */
  @Test
  public void testCompileTocContextsAndIndexMatchGolden() {
    final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    final List<CheckCatalog> catalogs = List.of(
        parse(resourceSet, EXECUTION_ENVIRONMENT + ".check"),
        parse(resourceSet, LIBRARY_CHECKS + ".check"));

    assertGolden(TOC, templates.compileToc(catalogs));
    assertGolden(CONTEXTS, templates.compileContexts(catalogs));
    assertGolden(INDEX, templates.compileIndex(catalogs));
  }

  /**
   * Catalog-level checks are nested under their catalog after its categories, and every attribute value and label is
   * escaped, so labels containing XML or HTML markup still produce well-formed output.
   */
  @Test
  public void testCatalogLevelChecksAndEscaping() {
    final CheckCatalog special = parse(injector.getInstance(XtextResourceSet.class), "Special.check");

    assertGolden("Special-toc.xml", templates.compileToc(List.of(special)));
    assertGolden("Special-contexts.xml", templates.compileContexts(List.of(special)));
    final String page = generator.compileDoc(special).toString();
    assertTrue(page.contains("<article class=\"check\" id=\"special_naming&amp;&lt;style&gt;\">"), page);
    assertTrue(page.contains("<h3>Naming &amp; &lt;style&gt; <a class=\"anchor\" href=\"#special_naming&amp;&lt;style&gt;\">"), page);
    assertTrue(page.contains("<h2 id=\"special_tips&amp;tricks\">Tips &amp; &quot;tricks&quot;</h2>"), page);
  }

  /**
   * The headless application writes the whole documentation tree byte-identical to the golden snapshot, with LF line
   * endings, and deletes the pages of catalogs that no longer exist.
   *
   * @param tempDir
   *          scratch directory for the sources and the documentation
   * @throws IOException
   *           if the fixture tree cannot be created or read
   */
  @Test
  public void testGenerateWritesGoldenTree(@TempDir final Path tempDir) throws IOException {
    final Path sourceDir = tempDir.resolve("src");
    final Path docsDir = tempDir.resolve("docs");
    final Path packageDir = sourceDir.resolve("pkg");
    copyFixture(EXECUTION_ENVIRONMENT + ".check", packageDir);
    copyFixture(LIBRARY_CHECKS + ".check", packageDir);
    final Path stale = touch(docsDir.resolve("content/Removed.html"));

    assertTrue(CheckDocApplication.generate(injector, sourceDir, docsDir), "generation must succeed");

    assertBytes(EXECUTION_ENVIRONMENT + ".html", docsDir.resolve("content/" + EXECUTION_ENVIRONMENT + ".html"));
    assertBytes(LIBRARY_CHECKS + ".html", docsDir.resolve("content/" + LIBRARY_CHECKS + ".html"));
    assertBytes(TOC, docsDir.resolve(TOC));
    assertBytes(CONTEXTS, docsDir.resolve(CONTEXTS));
    assertBytes(INDEX, docsDir.resolve(INDEX));
    assertFalse(Files.exists(stale), "pages of removed catalogs must be deleted");
  }

  /**
   * The headless application writes nothing and reports failure for a catalog with syntax errors or when no catalog
   * is found.
   *
   * @param tempDir
   *          scratch directory for the sources and the documentation
   * @throws IOException
   *           if the fixture tree cannot be created
   */
  @Test
  public void testGenerateFailsOnSyntaxErrorsAndMissingCatalogs(@TempDir final Path tempDir) throws IOException {
    final Path sourceDir = tempDir.resolve("src");
    final Path docsDir = tempDir.resolve("docs");
    Files.createDirectories(sourceDir);

    assertFalse(CheckDocApplication.generate(injector, sourceDir, docsDir), "no catalogs must fail");
    Files.writeString(sourceDir.resolve("Broken.check"), "package p catalog {");
    assertFalse(CheckDocApplication.generate(injector, sourceDir, docsDir), "syntax errors must fail");
    assertFalse(Files.exists(docsDir), NOTHING_WRITTEN);
  }

  /**
   * The headless application writes nothing and reports failure when two catalogs in different packages share a
   * simple name, since their pages would overwrite each other.
   *
   * @param tempDir
   *          scratch directory for the sources and the documentation
   * @throws IOException
   *           if the fixture tree cannot be created
   */
  @Test
  public void testGenerateFailsOnCatalogsSharingAPage(@TempDir final Path tempDir) throws IOException {
    final Path sourceDir = tempDir.resolve("src");
    final Path docsDir = tempDir.resolve("docs");
    final Path packageA = sourceDir.resolve("a");
    final Path packageB = sourceDir.resolve("b");
    Files.createDirectories(packageA);
    Files.createDirectories(packageB);
    final String fileName = "Same.check";
    Files.writeString(packageA.resolve(fileName), "package a catalog Same {}");
    Files.writeString(packageB.resolve(fileName), "package b catalog Same {}");

    assertTrue(CheckDocApplication.generate(injector, packageA, tempDir.resolve("single")), "one catalog alone must succeed");
    assertFalse(CheckDocApplication.generate(injector, sourceDir, docsDir), "catalogs sharing a page must fail");
    assertFalse(Files.exists(docsDir), NOTHING_WRITTEN);
  }

  /**
   * The headless source walk skips {@code target} and dot-prefixed directories only below the source
   * directory, so a checkout under a dot-directory (for example {@code ~/.jenkins/workspace}) is still found.
   *
   * @param tempDir
   *          scratch directory for the fake checkout
   * @throws IOException
   *           if the fixture tree cannot be created
   */
  @Test
  public void testFindCheckFilesIgnoresAncestorsOfSourceDir(@TempDir final Path tempDir) throws IOException {
    final Path sourceDir = tempDir.resolve(".jenkins/workspace/src");
    final Path kept = touch(sourceDir.resolve("Kept.check"));
    final Path nested = touch(sourceDir.resolve("pkg/Nested.check"));
    touch(sourceDir.resolve("target/Built.check"));
    touch(sourceDir.resolve(".settings/Hidden.check"));

    assertEquals(Set.of(kept, nested), Set.copyOf(CheckDocApplication.findCheckFiles(sourceDir)));
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
    assertTrue(resource.getErrors().isEmpty(), () -> fileName + " has syntax errors: " + resource.getErrors());
    final CheckCatalog catalog = (CheckCatalog) resource.getContents().get(0);
    assertNotNull(catalog, "Resource " + fileName + " should contain a CheckCatalog");
    return catalog;
  }

  /**
   * Asserts that the generated content equals the golden file, comparing with LF line endings because the generators
   * use the platform line separator; {@link #assertBytes(String, Path)} checks the written files byte for byte.
   *
   * @param goldenFileName
   *          the golden file name (without folder prefix)
   * @param actual
   *          the generated content
   */
  private void assertGolden(final String goldenFileName, final CharSequence actual) {
    final String expected = new String(readResource(FIXTURES + goldenFileName), StandardCharsets.UTF_8);
    assertEquals(expected, actual.toString().replace("\r\n", "\n"), goldenFileName + " must match the golden snapshot");
  }

  /**
   * Asserts that a written file is byte-identical to the golden file.
   *
   * @param goldenFileName
   *          the golden file name (without folder prefix)
   * @param actual
   *          the written file
   * @throws IOException
   *           if the written file cannot be read
   */
  private void assertBytes(final String goldenFileName, final Path actual) throws IOException {
    final String expected = new String(readResource(FIXTURES + goldenFileName), StandardCharsets.UTF_8);
    assertEquals(expected, Files.readString(actual), actual + " must be byte-identical to the golden snapshot");
  }

  /**
   * Copies a fixture into a directory.
   *
   * @param fileName
   *          the fixture file name (without folder prefix)
   * @param targetDir
   *          the directory to copy into
   * @throws IOException
   *           if the copy fails
   */
  private void copyFixture(final String fileName, final Path targetDir) throws IOException {
    Files.createDirectories(targetDir);
    Files.write(targetDir.resolve(fileName), readResource(FIXTURES + fileName));
  }

  /**
   * Reads a classpath resource (relative to this class).
   *
   * @param name
   *          the resource name
   * @return the resource content
   */
  private byte[] readResource(final String name) {
    try (InputStream in = getClass().getResourceAsStream(name)) {
      assertNotNull(in, "Missing resource " + name);
      return in.readAllBytes();
    } catch (IOException e) {
      throw new IllegalStateException("Could not read resource " + name, e);
    }
  }

  /**
   * Creates an empty file and its parent directories.
   *
   * @param file
   *          the file to create
   * @return {@code file}
   * @throws IOException
   *           if the file cannot be created
   */
  private static Path touch(final Path file) throws IOException {
    Files.createDirectories(file.getParent());
    return Files.createFile(file);
  }

}
