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
package com.avaloq.tools.ddk.xtext.format.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Event.Listener;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.shared.Access;
import org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.format.FormatConstants;
import com.avaloq.tools.ddk.xtext.format.ui.internal.FormatActivator;
import com.google.inject.Injector;

/** Exercises the real index, linking and builder with a three-level Format inheritance chain. */
@SuppressWarnings("nls")
public class FormatIncrementalBuildTest {

  private static final String PROJECT = "format.fingerprint.regression";
  private static final String BASE = "formatter for org.eclipse.xtext.xbase.Xtype\nconst int SPACING = 1;\nID { rule : no_space around; }\n";
  private static final String CHILD = "formatter for org.eclipse.xtext.xbase.Xbase with org.eclipse.xtext.xbase.Xtype\n";
  private static final String GRANDCHILD = "formatter for org.eclipse.xtext.xbase.annotations.XbaseWithAnnotations with org.eclipse.xtext.xbase.Xbase\nconst int LOCAL = 3;\n";

  private static final String BASE_PATH = "src/repro/Xtype.format";

  private static final String CHILD_FILE = "Xbase.format";

  private static final String GRANDCHILD_FILE = "XbaseWithAnnotations.format";

  private static final String LF = "\n";

  private static final String CRLF = "\r\n";

  @Test
  public void incrementalOutputMatchesFullBuildAndIdenticalSavesStayLocal() throws Exception {
    final boolean autoBuild = setAutoBuilding(false);
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT);
    List<String> indexed = new ArrayList<>();
    Listener listener = event -> {
      for (IResourceDescription.Delta delta : event.getDeltas()) {
        if (delta.getUri().isPlatformResource() && PROJECT.equals(delta.getUri().segment(1)) && "format".equals(delta.getUri().fileExtension())) {
          indexed.add(delta.getUri().lastSegment());
        }
      }
    };
    IBuilderState index = Access.getIBuilderState().get();
    index.addListener(listener);
    try {
      createFixture(project);

      indexed.clear();
      write(project, BASE_PATH, BASE);
      build(IncrementalProjectBuilder.INCREMENTAL_BUILD);
      assertFalse(indexed.contains(CHILD_FILE) || indexed.contains(GRANDCHILD_FILE),
          "identical save must not rebuild descendants: " + indexed);
      assertMatchesFullBuild(project);

      for (String source : List.of(BASE.replace("SPACING = 1", "SPACING = 2"),
          "// shifted source location\n" + BASE, BASE.replace("no_space around", "linewrap before"),
          BASE.replace(LF, CRLF), BASE.replace("ID {", "const int ADDED = 4;\nID {"), BASE.replace("SPACING", "RENAMED"), BASE)) {
        write(project, BASE_PATH, source);
        build(IncrementalProjectBuilder.INCREMENTAL_BUILD);
        assertMatchesFullBuild(project);
      }
      String crlf = BASE.replace(LF, CRLF);
      write(project, BASE_PATH, crlf);
      build(IncrementalProjectBuilder.INCREMENTAL_BUILD);
      assertMatchesFullBuild(project);
      indexed.clear();
      final Map<String, String> unchanged = outputs(project);
      write(project, BASE_PATH, crlf);
      build(IncrementalProjectBuilder.INCREMENTAL_BUILD);
      assertFalse(indexed.contains(CHILD_FILE) || indexed.contains(GRANDCHILD_FILE), "identical CRLF save must stay local");
      assertEquals(unchanged, outputs(project), "identical save must preserve all generated bytes");
    } finally {
      index.removeListener(listener);
      if (project.exists()) {
        project.delete(true, true, null);
      }
      setAutoBuilding(autoBuild);
    }
  }

  @Test
  public void renamedBaseWithUnchangedContentMatchesFullBuild() throws Exception {
    final boolean autoBuild = setAutoBuilding(false);
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT);
    try {
      createFixture(project);
      // The with clause names the grammar, not the file, so the dependents still resolve after the rename.
      IFile renamed = project.getFile("src/repro/XtypeRenamed.format");
      project.getFile(BASE_PATH).move(renamed.getFullPath(), IResource.FORCE, null);
      assertTrue(renamed.exists() && !project.getFile(BASE_PATH).exists(), "rename must move the base file");
      build(IncrementalProjectBuilder.INCREMENTAL_BUILD);
      assertMatchesFullBuild(project);
    } finally {
      if (project.exists()) {
        project.delete(true, true, null);
      }
      setAutoBuilding(autoBuild);
    }
  }

  private boolean setAutoBuilding(final boolean enabled) throws CoreException, InterruptedException {
    IWorkspace workspace = ResourcesPlugin.getWorkspace();
    IWorkspaceDescription description = workspace.getDescription();
    final boolean previous = description.isAutoBuilding();
    description.setAutoBuilding(enabled);
    workspace.setDescription(description);
    Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
    return previous;
  }

  private void createFixture(final IProject project) throws CoreException {
    if (project.exists()) {
      project.delete(true, true, null);
    }
    createProject();
    write(project, BASE_PATH, BASE);
    write(project, "src/repro/Xbase.format", CHILD);
    write(project, "src/repro/XbaseWithAnnotations.format", GRANDCHILD);
    for (String language : List.of("Xtype", "Xbase", "annotations.XbaseWithAnnotations")) {
      String simple = language.substring(language.lastIndexOf('.') + 1);
      String pkg = "org.eclipse.xtext.xbase." + (language.startsWith("annotations.") ? "annotations." : "") + "formatting";
      write(project, "src/" + pkg.replace('.', '/') + "/" + simple + "Formatter.java",
          "package " + pkg + ";\npublic abstract class " + simple + "Formatter extends Abstract" + simple + "Formatter {}\n");
    }
    build(IncrementalProjectBuilder.FULL_BUILD);
    build(IncrementalProjectBuilder.INCREMENTAL_BUILD);
    assertNoErrors(project);
    assertFalse(outputs(project).isEmpty(), "fixture must produce Java and traces");
    assertTrue(outputs(project).keySet().stream().anyMatch(n -> n.endsWith("._trace")), "trace comparison must not be vacuous");
  }

  private void createProject() throws CoreException {
    Injector injector = FormatActivator.getInstance().getInjector(FormatConstants.GRAMMAR);
    PluginProjectFactory factory = injector.getInstance(PluginProjectFactory.class);
    factory.setProjectName(PROJECT);
    factory.addFolders(List.of("src", "src-gen"));
    factory.addBuilderIds(JavaCore.BUILDER_ID, "org.eclipse.pde.ManifestBuilder", "org.eclipse.pde.SchemaBuilder", XtextProjectHelper.BUILDER_ID);
    factory.addProjectNatures(JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", XtextProjectHelper.NATURE_ID);
    factory.addRequiredBundles(List.of("org.eclipse.xtext", "org.eclipse.xtext.xbase", "org.eclipse.xtext.xbase.lib", "org.eclipse.emf.ecore",
        "com.avaloq.tools.ddk.xtext", "com.avaloq.tools.ddk.xtext.format", "org.eclipse.core.runtime"));
    IProject project = factory.createProject(new NullProgressMonitor(), null);
    JavaProjectSetupUtil.addJreClasspathEntry(JavaCore.create(project));
  }

  private void assertMatchesFullBuild(final IProject project) throws CoreException {
    assertNoErrors(project);
    Map<String, String> incremental = outputs(project);
    build(IncrementalProjectBuilder.FULL_BUILD);
    assertNoErrors(project);
    assertEquals(outputs(project), incremental, "incremental Java and trace bytes must match full-build output");
  }

  private void assertNoErrors(final IProject project) throws CoreException {
    List<String> errors = new ArrayList<>();
    for (IMarker marker : project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)) {
      if (marker.getAttribute(IMarker.SEVERITY, 0) == IMarker.SEVERITY_ERROR) {
        errors.add(marker.getResource().getProjectRelativePath() + ": " + marker.getAttribute(IMarker.MESSAGE, ""));
      }
    }
    assertTrue(errors.isEmpty(), "fixture must build without errors: " + errors);
  }

  private Map<String, String> outputs(final IProject project) throws CoreException {
    Map<String, String> result = new TreeMap<>();
    project.getFolder("src-gen").accept(resource -> {
      if (resource instanceof IFile file) {
        try (InputStream in = file.getContents()) {
          result.put(file.getProjectRelativePath().toString(), HexFormat.of().formatHex(in.readAllBytes()));
        } catch (java.io.IOException exception) {
          throw new java.io.UncheckedIOException(exception);
        }
      }
      return true;
    });
    return result;
  }

  private void build(final int kind) throws CoreException {
    ResourcesPlugin.getWorkspace().build(kind, new NullProgressMonitor());
  }

  private void write(final IProject project, final String path, final String content) throws CoreException {
    IFile file = project.getFile(path);
    createFolder(file.getParent());
    try (InputStream in = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))) {
      if (file.exists()) {
        file.setContents(in, IResource.FORCE, null);
      } else {
        file.create(in, true, null);
      }
    } catch (java.io.IOException exception) {
      throw new java.io.UncheckedIOException(exception);
    }
  }

  private void createFolder(final org.eclipse.core.resources.IContainer container) throws CoreException {
    if (container instanceof IFolder folder && !folder.exists()) {
      createFolder(folder.getParent());
      folder.create(true, true, null);
    }
  }
}
