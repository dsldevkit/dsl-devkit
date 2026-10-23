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
package com.avaloq.tools.ddk.check.standalone;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.avaloq.tools.ddk.check.CheckStandaloneSetup;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckDocumentationTemplates;
import com.avaloq.tools.ddk.check.generator.CheckGenerator;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.google.inject.Injector;


/**
 * Eclipse application that emits the full Check documentation tree (HTML pages, an
 * {@code index.html} landing page and the Eclipse-Help {@code toc.xml} / {@code contexts.xml})
 * from every {@code .check} file under a source directory, without requiring an Eclipse
 * workbench. Unlike the IDE builder, it documents every catalog regardless of the
 * project's Check generator preferences.
 *
 * Arguments (positional): {@code <sourceDir> <docsDir>} where {@code docsDir}
 * is the project's {@code docs/} folder. HTML pages land in
 * {@code <docsDir>/content/<Catalog>.html}; the other files land directly in
 * {@code <docsDir>/}. The application exits with {@code 1} and writes nothing if a
 * catalog has syntax errors, two catalogs share a simple name (and hence a page), or
 * none is found.
 */
@SuppressWarnings({"nls", "PMD.SystemPrintln"})
public class CheckDocApplication implements IApplication {

  /** Unix line separator used for all written output, independent of the platform. */
  private static final String LF = "\n";

  /** Exit code reported when no documentation was written. */
  private static final Integer EXIT_ERROR = 1;

  @Override
  public Object start(final IApplicationContext context) throws IOException {
    String[] args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
    if (args == null || args.length < 2) {
      System.err.println("Usage: -application com.avaloq.tools.ddk.check.core.docApplication <sourceDir> <docsDir>");
      return EXIT_ERROR;
    }
    Injector injector = new CheckStandaloneSetup().createInjectorAndDoEMFRegistration();
    return generate(injector, Path.of(args[0]).toRealPath(), Path.of(args[1])) ? IApplication.EXIT_OK : EXIT_ERROR;
  }

  /**
   * Writes the documentation tree for every catalog under {@code sourceDir} into {@code docsDir}. Nothing is
   * written if a {@code .check} file has syntax errors, two catalogs would share a page, or no catalog is found;
   * pages of catalogs that no longer exist are deleted.
   *
   * @param injector
   *          the Check language injector
   * @param sourceDir
   *          directory walked recursively for {@code .check} files
   * @param docsDir
   *          the project's {@code docs/} folder
   * @return {@code true} if the documentation was written, {@code false} if errors were reported instead
   * @throws IOException
   *           if reading or writing fails
   */
  public static boolean generate(final Injector injector, final Path sourceDir, final Path docsDir) throws IOException {
    XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);

    List<Path> checkFiles = findCheckFiles(sourceDir);
    List<CheckCatalog> catalogs = new ArrayList<>();
    Map<String, Path> pageSources = new HashMap<>();
    CheckGeneratorNaming naming = injector.getInstance(CheckGeneratorNaming.class);
    boolean hasErrors = false;
    for (Path checkFile : checkFiles) {
      Resource resource = resourceSet.getResource(URI.createFileURI(checkFile.toAbsolutePath().toString()), true);
      for (Resource.Diagnostic error : resource.getErrors()) {
        System.err.println(checkFile + ":" + error.getLine() + ": " + error.getMessage());
        hasErrors = true;
      }
      for (EObject root : resource.getContents()) {
        if (root instanceof CheckCatalog catalog) {
          catalogs.add(catalog);
          Path previous = pageSources.putIfAbsent(naming.docFileName(catalog), checkFile);
          if (previous != null) {
            System.err.println(checkFile + ": catalog " + catalog.getName() + " has the same page name as the catalog in " + previous);
            hasErrors = true;
          }
        }
      }
    }
    if (hasErrors) {
      return false;
    }
    if (catalogs.isEmpty()) {
      System.err.println("No catalogs found under " + sourceDir);
      return false;
    }

    Path contentDir = docsDir.resolve("content");
    Files.createDirectories(contentDir);
    CheckGenerator generator = injector.getInstance(CheckGenerator.class);
    for (CheckCatalog catalog : catalogs) {
      Path target = contentDir.resolve(naming.docFileName(catalog));
      writeLf(target, generator.compileDoc(catalog));
      System.out.println("Wrote " + target);
    }
    deleteStalePages(contentDir, pageSources.keySet());
    CheckDocumentationTemplates docTemplates = injector.getInstance(CheckDocumentationTemplates.class);
    Path toc = docsDir.resolve("toc.xml");
    writeLf(toc, docTemplates.compileToc(catalogs));
    System.out.println("Wrote " + toc);
    Path contexts = docsDir.resolve("contexts.xml");
    writeLf(contexts, docTemplates.compileContexts(catalogs));
    System.out.println("Wrote " + contexts);
    Path index = docsDir.resolve("index.html");
    writeLf(index, docTemplates.compileIndex(catalogs));
    System.out.println("Wrote " + index);

    System.out.println("Processed " + checkFiles.size() + " .check files (" + catalogs.size() + " catalogs)");
    return true;
  }

  /**
   * Deletes the {@code .html} pages in {@code contentDir} that do not belong to a current catalog.
   *
   * @param contentDir
   *          the folder holding the per-catalog pages
   * @param pages
   *          the file names of the pages just written
   * @throws IOException
   *           if the folder cannot be listed or a page cannot be deleted
   */
  private static void deleteStalePages(final Path contentDir, final Set<String> pages) throws IOException {
    List<Path> stale = new ArrayList<>();
    try (DirectoryStream<Path> htmlFiles = Files.newDirectoryStream(contentDir, "*.html")) {
      for (Path page : htmlFiles) {
        if (!pages.contains(contentDir.relativize(page).toString())) {
          stale.add(page);
        }
      }
    }
    for (Path page : stale) {
      Files.delete(page);
      System.out.println("Deleted " + page);
    }
  }

  /**
   * Collects the {@code .check} files under {@code sourceDir}, skipping {@code target} and dot-prefixed
   * directories below it. Only the path relative to {@code sourceDir} is inspected, so the location of the
   * checkout itself (for example under {@code ~/.jenkins}) does not matter.
   *
   * @param sourceDir
   *          directory to walk recursively
   * @return the {@code .check} files found, in walk order
   * @throws IOException
   *           if the directory cannot be walked
   */
  public static List<Path> findCheckFiles(final Path sourceDir) throws IOException {
    List<Path> checkFiles = new ArrayList<>();
    try (Stream<Path> walk = Files.walk(sourceDir)) {
      walk.filter(p -> p.toString().endsWith(".check"))
          .filter(p -> isUserSource(sourceDir.relativize(p)))
          .forEach(checkFiles::add);
    }
    return checkFiles;
  }

  /**
   * Writes {@code content} to {@code target} with Unix ({@code \n}) line endings, regardless of the
   * platform separator the {@link org.eclipse.xtend2.lib.StringConcatenation} default constructor picks
   * up via {@code System.lineSeparator()}. This keeps headless output byte-identical to the in-IDE path
   * (which emits LF through the Check runtime's line-separator binding) and to the committed snapshot on Windows.
   *
   * @param target
   *          file to write
   * @param content
   *          generated content
   * @throws IOException
   *           if writing fails
   */
  private static void writeLf(final Path target, final CharSequence content) throws IOException {
    Files.writeString(target, content.toString().replace("\r\n", LF).replace("\r", LF));
  }

  /** True iff the relative path {@code p} contains no segment named {@code target} or starting with a dot. */
  private static boolean isUserSource(final Path p) {
    for (Path segment : p) {
      String name = segment.toString();
      if ("target".equals(name) || name.startsWith(".")) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void stop() {
    // no-op
  }
}
