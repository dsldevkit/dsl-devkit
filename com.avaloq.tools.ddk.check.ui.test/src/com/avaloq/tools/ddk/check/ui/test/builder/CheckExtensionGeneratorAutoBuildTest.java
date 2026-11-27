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
package com.avaloq.tools.ddk.check.ui.test.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.ui.test.AbstractCheckTestCase;
import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager;


/**
 * Tests that the auto-build settles after the Check builder participant has edited the {@code plugin.xml} of the built project.
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "nls"})
@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckExtensionGeneratorAutoBuildTest extends AbstractCheckTestCase {

  private static final long TIMEOUT_MILLIS = 60_000;
  private static final long QUIET_MILLIS = 1_000;
  private static final long POLL_MILLIS = 50;
  /** Runs of the auto-build job allowed on top of the one canceled run per catalog that needs an edit. */
  private static final int EXTRA_AUTO_BUILDS = 4;

  private static final String ADDED_CATALOG = "AddOne";
  private static final String CHANGED_CATALOG = "ChangeExisting";
  private static final String PLUGIN_XML = "plugin.xml";
  private static final String LANGUAGE_ATTRIBUTE = "language=";
  private static final String CHECK = """

        live error %s "%s"
        message "%s." {
          for Check elem {
            issue on elem
          }
        }
      """;
  private static final String FOR_GRAMMAR = "\nfor grammar com.avaloq.tools.ddk.check.Check";
  private static final String CATALOG = """
      package autobuild

      import com.avaloq.tools.ddk.check.check.Check

      catalog %s%s {
      %s}
      """;

  private final AtomicInteger autoBuilds = new AtomicInteger();
  private final IJobChangeListener autoBuildCounter = new JobChangeAdapter() {
    @Override
    public void aboutToRun(final IJobChangeEvent event) {
      if (event.getJob().belongsTo(ResourcesPlugin.FAMILY_AUTO_BUILD)) {
        autoBuilds.incrementAndGet();
      }
    }
  };

  @AfterEach
  public void stopCounting() {
    Job.getJobManager().removeJobChangeListener(autoBuildCounter);
  }

  /**
   * A catalog added to a project that already has a {@code plugin.xml} gets its extensions.
   */
  @Test
  public void testAddCatalog() throws Exception {
    prepareProject("AddExisting");

    writeCatalogs(List.of(ADDED_CATALOG), true);

    assertAutoBuildSettles(1);
    assertHasExtensions(ADDED_CATALOG);
  }

  /**
   * A catalog that no longer names a grammar gets its existing validator extension updated.
   */
  @Test
  public void testChangeCatalog() throws Exception {
    prepareProject(CHANGED_CATALOG);
    final String before = getContents(pluginXml());

    writeCatalogs(List.of(CHANGED_CATALOG), false);

    assertAutoBuildSettles(1);
    String after = getContents(pluginXml());
    assertHasExtensions(CHANGED_CATALOG);
    assertEquals(count(before, LANGUAGE_ATTRIBUTE) - 1, count(after, LANGUAGE_ATTRIBUTE), "the validator extension loses its language:\n" + after);
  }

  /**
   * Catalogs added together all get their extensions.
   */
  @Test
  public void testAddCatalogsTogether() throws Exception {
    prepareProject("TogetherExisting");
    List<String> catalogs = List.of("TogetherOne", "TogetherTwo", "TogetherThree");

    writeCatalogs(catalogs, true);

    assertAutoBuildSettles(catalogs.size());
    for (String catalog : catalogs) {
      assertHasExtensions(catalog);
    }
  }

  /**
   * Builds a first catalog, so that the project is set up for Check and has built state, and starts counting the runs of the auto-build job.
   *
   * @param catalog
   *          the name of the first catalog
   */
  private void prepareProject(final String catalog) throws Exception {
    assertTrue(ResourcesPlugin.getWorkspace().isAutoBuilding(), "precondition: auto-build is on");
    createFile(catalogPath(catalog), catalogSource(catalog, true));
    IResourcesSetupUtil.waitForBuild();
    assertTrue(waitForAutoBuild(), "precondition: auto-build settles after the explicit build");
    assertHasExtensions(catalog);
    Job.getJobManager().addJobChangeListener(autoBuildCounter);
  }

  /**
   * Creates or overwrites the given catalogs in one workspace operation, so that one auto-build sees them all.
   *
   * @param catalogs
   *          the names of the catalogs
   * @param withGrammar
   *          whether the catalogs name a grammar
   */
  private void writeCatalogs(final List<String> catalogs, final boolean withGrammar) throws Exception {
    ResourcesPlugin.getWorkspace().run(monitor -> {
      for (String catalog : catalogs) {
        IFile file = project().getFile(catalogPath(catalog));
        InputStream source = new ByteArrayInputStream(catalogSource(catalog, withGrammar).getBytes(StandardCharsets.UTF_8));
        if (file.exists()) {
          file.setContents(source, true, false, monitor);
        } else {
          file.create(source, true, monitor);
        }
      }
    }, new NullProgressMonitor());
  }

  private void assertAutoBuildSettles(final int catalogsToEdit) throws Exception {
    boolean settled = waitForAutoBuild();
    int builds = autoBuilds.get();
    assertTrue(settled, "auto-build settles within " + TIMEOUT_MILLIS + " ms; runs of the auto-build job so far: " + builds);
    assertTrue(builds <= catalogsToEdit + EXTRA_AUTO_BUILDS, "few runs of the auto-build job for " + catalogsToEdit + " catalog(s) to edit: " + builds);
  }

  private void assertHasExtensions(final String catalog) throws Exception {
    String pluginXml = getContents(pluginXml());
    assertTrue(pluginXml.contains("/" + catalog + ".check\""), "plugin.xml has the extensions of " + catalog + ":\n" + pluginXml);
  }

  /**
   * Waits until no auto-build has been pending for the quiet period.
   *
   * @return {@code false} if that did not happen before the timeout
   */
  private boolean waitForAutoBuild() throws InterruptedException {
    long deadline = System.currentTimeMillis() + TIMEOUT_MILLIS;
    long quietSince = System.currentTimeMillis();
    int seenBuilds = autoBuilds.get();
    while (System.currentTimeMillis() < deadline) {
      Job[] pending = Job.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD);
      if (pending.length > 0 || autoBuilds.get() != seenBuilds) {
        for (Job job : pending) {
          job.join(Math.max(1, deadline - System.currentTimeMillis()), null);
        }
        seenBuilds = autoBuilds.get();
        quietSince = System.currentTimeMillis();
      } else if (System.currentTimeMillis() - quietSince >= QUIET_MILLIS) {
        return true;
      } else {
        Thread.sleep(POLL_MILLIS);
      }
    }
    return false;
  }

  private static String catalogPath(final String catalog) {
    return "src/autobuild/" + catalog + ".check";
  }

  private static String catalogSource(final String catalog, final boolean withGrammar) {
    return String.format(CATALOG, catalog, withGrammar ? FOR_GRAMMAR : "", String.format(CHECK, catalog, catalog, catalog));
  }

  private static IProject project() {
    return ResourcesPlugin.getWorkspace().getRoot().getProject(PluginTestProjectManager.TEST_PROJECT_NAME);
  }

  private static IFile pluginXml() {
    return project().getFile(PLUGIN_XML);
  }

  private static int count(final String text, final String part) {
    int count = 0;
    for (int i = text.indexOf(part); i >= 0; i = text.indexOf(part, i + 1)) {
      count++;
    }
    return count;
  }
}
