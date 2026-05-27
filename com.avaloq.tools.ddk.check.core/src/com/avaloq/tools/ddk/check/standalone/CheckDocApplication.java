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

  @Override
  public Object start(final IApplicationContext context) throws IOException {
    String[] args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
    if (args == null || args.length < 2) {
      System.err.println("Usage: -application com.avaloq.tools.ddk.check.core.docApplication <sourceDir> <docsDir>");
      return 1;
    }
    Path sourceDir = Path.of(args[0]);
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
          .filter(p -> { String n = p.toString().replace('\\', '/'); return !n.contains("/target/") && !n.matches(".*\\/\\.[^/]+/.*"); })
          .forEach(checkFiles::add);
    }

    List<CheckCatalog> catalogs = new ArrayList<>();
    for (Path checkFile : checkFiles) {
      Resource resource = resourceSet.getResource(URI.createFileURI(checkFile.toAbsolutePath().toString()), true);
      for (EObject root : resource.getContents()) {
        if (root instanceof CheckCatalog catalog) {
          catalogs.add(catalog);
          Path target = contentDir.resolve(catalog.getName() + ".html");
          Files.writeString(target, generator.compileDoc(catalog).toString());
          System.out.println("Wrote " + target);
        }
      }
    }

    if (!catalogs.isEmpty()) {
      Path toc = docsDir.resolve("toc.xml");
      Files.writeString(toc, docTemplates.compileToc(catalogs).toString());
      System.out.println("Wrote " + toc);
      Path contexts = docsDir.resolve("contexts.xml");
      Files.writeString(contexts, docTemplates.compileContexts(catalogs).toString());
      System.out.println("Wrote " + contexts);
      Path index = docsDir.resolve("index.html");
      Files.writeString(index, docTemplates.compileIndex(catalogs).toString());
      System.out.println("Wrote " + index);
    }

    System.out.println("Processed " + checkFiles.size() + " .check files (" + catalogs.size() + " catalogs)");
    return IApplication.EXIT_OK;
  }

  @Override
  public void stop() {
    // no-op
  }
}
