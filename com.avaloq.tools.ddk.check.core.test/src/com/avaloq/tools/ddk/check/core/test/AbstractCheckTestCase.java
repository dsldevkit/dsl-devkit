/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.core.test;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.ui.internal.CheckActivator;
import com.avaloq.tools.ddk.xtext.test.PluginTestProjectManager;
import com.avaloq.tools.ddk.xtext.test.TestSource;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;


/**
 * An abstract test class for tests on Check models. Allows creating a project and adding files.
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "restriction", "nls"})
public abstract class AbstractCheckTestCase {
  private static final int TWO_KILO_BYTES = 2048;
  protected static final Logger LOGGER = LogManager.getLogger(AbstractCheckTestCase.class);
  private static final PluginTestProjectManager PROJECT_MANAGER = new PluginTestProjectManager(CheckActivator.getInstance().getInjector(CheckConstants.GRAMMAR));

  private final Set<IFile> files = newHashSet();

  @Inject
  private Provider<XtextResourceSet> resourceSetProvider;

  @BeforeEach
  void setUp() throws Exception {
    getInjector().injectMembers(this);
  }

  /**
   * Prepares the workspace for project based tests.
   *
   * @throws Exception
   *           the exception
   */
  @BeforeAll
  public static void prepareWorkspace() throws Exception {
    PROJECT_MANAGER.setup(ImmutableList.<TestSource> of());
  }

  /**
   * Gets the injector. May be overridden by clients.
   *
   * @return the injector
   */
  protected Injector getInjector() {
    return CheckActivator.getInstance().getInjector(CheckConstants.GRAMMAR);
  }

  public String getFileExtension() {
    return get(FileExtensionProvider.class).getFileExtensions().iterator().next();
  }

  /**
   * Gets the generic instance of type T.
   *
   * @param <T>
   *          the generic type
   * @param clazz
   *          the class of requested instance
   * @return the instance
   */
  public <T> T get(final Class<T> clazz) {
    return getInjector().getInstance(clazz);
  }

  /**
   * Clean up after all tests have terminated.
   */
  @AfterAll
  public static void cleanUp() {
    PROJECT_MANAGER.teardown();
  }

  public Set<IFile> getFiles() {
    return files;
  }

  /**
   * Creates a file with given name and contents.
   *
   * @param fileName
   *          the file name
   * @param content
   *          the file content
   * @return the file
   * @throws Exception
   *           the exception
   */
  public IFile createFile(final String fileName, final String content) throws Exception { // NOPMD
    String fullFileName = getFullFileName(fileName);
    IFile file = IResourcesSetupUtil.createFile(fullFileName, content);
    getFiles().add(file);
    return file;
  }

  /**
   * Gets the full file name.
   *
   * @param fileName
   *          the file name
   * @return the full file name
   */
  protected String getFullFileName(final String fileName) {
    if (fileName.contains("/")) {
      // it is assumed that a fully qualified file name is provided, only add the project name suffix
      return '/' + PluginTestProjectManager.TEST_PROJECT_NAME + '/' + fileName;
    }
    return getSourceFolderPath() + getFileName(fileName);
  }

  /**
   * Gets the file name with its primary file extension. Is used for test data resources not containing their file
   * extension (if the check plugins are installed this would lead to problems as the check builder participant
   * generates files on the fly).
   *
   * @param name
   *          the name
   * @return the file name with file extension
   */
  protected String getFileName(final String name) {
    String extension = (name.indexOf('.') != -1) ? "" : '.' + getFileExtension();
    return name + extension;
  }

  /**
   * Gets the contents of a given file. Contents are returned as a single string.
   *
   * @param file
   *          the file
   * @return the file contents
   * @throws Exception
   *           the exception
   */
  public String getContents(final IFile file) throws Exception { // NOPMD
    InputStream inputStream = file.getContents();
    try {
      byte[] buffer = new byte[TWO_KILO_BYTES];
      int bytesRead;
      StringBuffer b = new StringBuffer();
      do {
        bytesRead = inputStream.read(buffer);
        if (bytesRead != -1) {
          b.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
        }
      } while (bytesRead != -1);
      return b.toString();
    } finally {
      inputStream.close();
    }
  }

  /**
   * Gets the model.
   *
   * @param fileName
   *          the file name
   * @param content
   *          the content
   * @return the model
   * @throws Exception
   *           the exception
   */
  public EObject getModel(final String fileName, final String content) throws Exception { // NOPMD
    IFile file = createFile(fileName, content);
    Resource resource = get(XtextResourceSet.class).createResource(uri(file));
    resource.load(new StringInputStream(content), null);
    assertEquals(0, resource.getErrors().size(), resource.getErrors().toString());
    return resource.getContents().get(0);
  }

  /**
   * Gets the URI for a given file.
   *
   * @param file
   *          the file
   * @return the URI
   */
  public URI uri(final IFile file) {
    return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
  }

  /**
   * Gets the resource set.
   *
   * @return the resource set
   */
  public ResourceSet getResourceSet() {
    return resourceSetProvider.get();
  }

  /**
   * Gets the top-level model object contained by a file with given file name.
   *
   * @param fileName
   *          the file name
   * @return the top-level model object
   * @throws Exception
   *           the exception
   */
  public EObject getModel(final String fileName) throws Exception { // NOPMD
    IFile file = getFile(fileName);
    Resource resource = get(XtextResourceSet.class).createResource(uri(file));
    InputStream s = null;
    try {
      s = file.getContents();
      resource.load(s, null);
    } finally {
      if (s != null) {
        try {
          s.close();
        } catch (IOException e) {
          LOGGER.info("Failed to close test file " + fileName);
        }
      }
    }
    assertEquals(0, resource.getErrors().size(), resource.getErrors().toString());
    return resource.getContents().get(0);
  }

  /**
   * Gets the file with given file name.
   *
   * @param fileName
   *          the file name
   * @return the file
   */
  private IFile getFile(final String fileName) {
    return Iterables.find(files, new Predicate<IFile>() {
      @Override
      public boolean apply(final IFile input) {
        return getFileName(fileName).equals(input.getName());
      }
    });
  }

  /**
   * Gets the project or creates and returns it if it is <code>null</code>.
   *
   * @return the project
   * @throws CoreException
   *           the core exception
   */
  protected IProject getOrCreatePluginProject() throws CoreException {
    IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
    assertNotNull(workspaceRoot, "No workspace; project cannot be created or found");
    IProject project = workspaceRoot.getProject(PluginTestProjectManager.TEST_PROJECT_NAME);
    if (!project.exists()) {
      try {
        project = PluginTestProjectManager.createPluginProject(getInjector(), PluginTestProjectManager.TEST_PROJECT_NAME);
      } catch (CoreException e) {
        fail(e.getMessage());
      }
    }
    return project;
  }

  /**
   * Gets the source folder name.
   *
   * @return the source folder name
   */
  protected String getSourceFolderName() {
    return PluginTestProjectManager.DEFAULT_SOURCE_FOLDER;
  }

  /**
   * Adds sources with given file names to the {@link #getSourceFolderName()} folder of current project. If resources
   * to be added to workspace do not contain a file extension in their file name, it is nevertheless added on files
   * created.
   *
   * @param clazz
   *          the clazz
   * @param sourceFileNames
   *          the source file names
   */
  public void addSourcesToWorkspace(final Class<? extends AbstractCheckTestCase> clazz, final List<String> sourceFileNames) {
    try {
      new WorkspaceModifyOperation() {
        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
          for (final String fileName : sourceFileNames) {
            try {
              final String contents = getNormalizedContents(clazz, fileName);

              // Create URI including file extension
              URI resourceURI = URI.createPlatformResourceURI(getFullFileName(fileName), true);
              IFile file = IResourcesSetupUtil.createFile(resourceURI.toPlatformString(true), contents);
              getFiles().add(file);
            } catch (IOException e) {
              LOGGER.error("failed adding file to workspace: " + fileName, e);
              fail("Error adding file " + fileName + " to workspace: " + e.getMessage());
            }
          }
        }
      }.run(new NullProgressMonitor());
    } catch (InvocationTargetException e) {
      fail("Error adding files to workspace: " + e.getMessage());
    } catch (InterruptedException e) {
      fail("Error adding files to workspace: " + e.getMessage());
    }
    assertEquals(sourceFileNames.size(), getFiles().size(), "All files successfully added to workspace");
  }

  /**
   * Gets the full path to the {@link #getSourceFolderName()} folder.
   *
   * @return the source folder path
   */
  protected String getSourceFolderPath() {
    return '/' + PluginTestProjectManager.TEST_PROJECT_NAME + '/' + getSourceFolderName() + '/';
  }

  /**
   * Gets the normalized contents.
   *
   * @param clazz
   *          the class
   * @param modelName
   *          the contents
   * @return the normalized contents
   * @throws IOException
   *           if model could not be read
   */
  private String getNormalizedContents(final Class<? extends AbstractCheckTestCase> clazz, final String modelName) throws IOException {
    try (InputStreamReader s = new InputStreamReader(clazz.getResourceAsStream(modelName), StandardCharsets.UTF_8)) {
      return CharStreams.toString(s);
    }
  }

}
