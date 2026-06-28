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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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
import com.google.inject.Injector;
import com.google.inject.Provider;


/**
 * Eclipse application that emits the full Check documentation tree (HTML pages
 * plus the Eclipse-Help {@code toc.xml} / {@code contexts.xml}) from every
 * {@code .check} file under a source directory, without requiring an Eclipse
 * workbench.
 *
 * Arguments (positional): {@code <sourceDir> <docsDir>} where {@code docsDir}
 * is the project's {@code docs/} folder. HTML pages land in
 * {@code <docsDir>/content/<Catalog>.html}; the two help-system files land
 * directly in {@code <docsDir>/}.
 */
@SuppressWarnings({"nls", "PMD.SystemPrintln"})
public class CheckDocApplication implements IApplication {

  /** Unix line separator used for all written output, independent of the platform. */
  private static final String LF = "\n";

  @Override
  public Object start(final IApplicationContext context) throws IOException {
    String[] args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
    if (args == null || args.length < 2) {
      System.err.println("Usage: -application com.avaloq.tools.ddk.check.core.docApplication <sourceDir> <docsDir>");
      return 1;
    }
    Path sourceDir = Path.of(args[0]).toRealPath();
    Path docsDir = Path.of(args[1]);
    Path contentDir = docsDir.resolve("content");
    Files.createDirectories(contentDir);

    Injector injector = new CheckStandaloneSetup().createInjectorAndDoEMFRegistration();
    CheckGenerator generator = injector.getInstance(CheckGenerator.class);
    CheckDocumentationTemplates docTemplates = injector.getInstance(CheckDocumentationTemplates.class);
    Provider<XtextResourceSet> resourceSets = injector.getProvider(XtextResourceSet.class);
    XtextResourceSet resourceSet = resourceSets.get();

    List<Path> checkFiles = new ArrayList<>();
    try (Stream<Path> walk = Files.walk(sourceDir)) {
      walk.filter(p -> p.toString().endsWith(".check"))
          .filter(CheckDocApplication::isUserSource)
          .forEach(checkFiles::add);
    }

    List<CheckCatalog> catalogs = new ArrayList<>();
    for (Path checkFile : checkFiles) {
      Resource resource = resourceSet.getResource(URI.createFileURI(checkFile.toAbsolutePath().toString()), true);
      for (EObject root : resource.getContents()) {
        if (root instanceof CheckCatalog catalog) {
          catalogs.add(catalog);
          Path target = contentDir.resolve(catalog.getName() + ".html");
          writeLf(target, generator.compileDoc(catalog));
          System.out.println("Wrote " + target);
        }
      }
    }

    if (!catalogs.isEmpty()) {
      Path toc = docsDir.resolve("toc.xml");
      writeLf(toc, docTemplates.compileToc(catalogs));
      System.out.println("Wrote " + toc);
      Path contexts = docsDir.resolve("contexts.xml");
      writeLf(contexts, docTemplates.compileContexts(catalogs));
      System.out.println("Wrote " + contexts);
      Path index = docsDir.resolve("index.html");
      writeLf(index, docTemplates.compileIndex(catalogs));
      System.out.println("Wrote " + index);
    }

    System.out.println("Processed " + checkFiles.size() + " .check files (" + catalogs.size() + " catalogs)");
    return IApplication.EXIT_OK;
  }

  /**
   * Writes {@code content} to {@code target} with Unix ({@code \n}) line endings, regardless of the
   * platform separator the {@link org.eclipse.xtend2.lib.StringConcatenation} default constructor picks
   * up via {@code System.lineSeparator()}. This keeps headless output byte-identical to the in-IDE path
   * (which normalizes via {@code LfNormalizingFileSystemAccess}) and to the committed snapshot on Windows.
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

  /** True iff {@code p} contains no path segment named {@code target} or starting with a dot. */
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
