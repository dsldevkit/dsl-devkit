/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.test.generator;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.eclipse.xtext.junit4.ui.util.JavaProjectSetupUtil;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.junit.AfterClass;
import org.junit.Assert;

import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * A base class for xtext generator tests. Allows creating a project and adding files.
 */
@SuppressWarnings({"PMD.AbstractClassWithoutAbstractMethod", "restriction"})
public abstract class AbstractGeneratorTest {
  private static final String MESSAGE_GENERATED_FILE_MUST_EXIST = "Generated file ''{0}'' must exist.";
  private static final String MESSAGE_GENERATED_CODE_MUST_BE_CORRECT = "Generated contents of ''{0}'' must be correct.";

  private static final String FORWARD_SLASH = "/"; //$NON-NLS-1$

  private static final Logger LOGGER = Logger.getLogger(AbstractGeneratorTest.class);

  private static Map<String, IProject> projects = new HashMap<String, IProject>();

  private final Set<IFile> files = newHashSet();
  protected static final List<String> REQUIRED_BUNDLES = newArrayList(//
  "org.eclipse.xtext.xbase.lib", //$NON-NLS-1$
  "org.eclipse.xtend2.lib", // //$NON-NLS-1$
  "org.eclipse.emf.ecore", //$NON-NLS-1$
  "org.eclipse.xtext", // //$NON-NLS-1$
  "org.eclipse.osgi", //$NON-NLS-1$
  "org.eclipse.xtend", //$NON-NLS-1$
  "org.eclipse.core.runtime", //$NON-NLS-1$
  "org.eclipse.xtext.xbase" //$NON-NLS-1$
  );

  @Inject
  private FileExtensionProvider fileExtensionProvider;

  @Inject
  private Provider<PluginProjectFactory> projectFactoryProvider;

  /**
   * Clean up after all tests have terminated.
   */
  @AfterClass
  public static void cleanUp() {
    try {
      IResourcesSetupUtil.cleanWorkspace();
    } catch (CoreException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  /**
   * Initializes a project with the given name and sources.
   *
   * @param projectName
   *          the name of the project
   * @param sourceFileNames
   *          the source file names, mapping input filename to output filename
   * @throws CoreException
   *           the {@link CoreException}
   */
  public void initializeProject(final String projectName, final ImmutableMap<String, String> sourceFileNames) throws CoreException {
    initializeProject(projectName, null, REQUIRED_BUNDLES, null, null, sourceFileNames);
  }

  /**
   * Initializes a project with the given name, required bundles and sources.
   *
   * @param projectName
   *          the name of the project
   * @param folders
   *          the folders to create (source and source-generated folders will be created unless methods are overridden and specified as null or empty), or
   *          {@code null} if none
   * @param requiredBundles
   *          required bundles of the project to be created, or {@code null} if none
   * @param importedPackages
   *          imported packages of the project to be created, or {@code null} if none
   * @param exportedPackages
   *          exported packages of the project to be created, or {@code null} if none
   * @param sourceFileNames
   *          the source file names, mapping input filename to output filename
   * @throws CoreException
   *           the {@link CoreException}
   */
  public void initializeProject(final String projectName, final List<String> folders, final List<String> requiredBundles, final List<String> importedPackages, final List<String> exportedPackages, final ImmutableMap<String, String> sourceFileNames) throws CoreException {
    // a project must be created
    createPluginProject(projectName, folders, requiredBundles, importedPackages, exportedPackages);
    // sources are copied into the project and then built by the Xtext builder
    addSourcesToWorkspace(projectName, sourceFileNames);

    // wait for build to finish, otherwise included catalog may not be resolvable
    IResourcesSetupUtil.waitForAutoBuild();
  }

  /**
   * Gets the full file name.
   *
   * @param projectName
   *          the name of the project
   * @param fileName
   *          the file name
   * @return the full file name
   */
  protected String getFullFileName(final String projectName, final String fileName) {
    if (fileName.startsWith(FORWARD_SLASH)) {
      // it is assumed that a full path is provided
      return fileName;
    }
    if (fileName.contains(FORWARD_SLASH)) {
      // it is assumed that a fully qualified file name in the boundaries of the current project is provided, only add the project name suffix
      return '/' + projectName + '/' + fileName;
    }
    String extension = (fileName.indexOf('.') != -1) ? "" : '.' + fileExtensionProvider.getPrimaryFileExtension(); //$NON-NLS-1$
    return getSourceFolderPath(projectName) + fileName + extension;
  }

  /**
   * Gets the file with given file name.
   *
   * @param fileName
   *          the file name
   * @return the file
   */
  protected IFile getFile(final String fileName) {
    final String fileNameWithExtension = fileName.indexOf('.') > 0 ? fileName : fileName + '.' + fileExtensionProvider.getPrimaryFileExtension();
    return Iterables.find(files, new Predicate<IFile>() {
      @Override
      public boolean apply(final IFile input) {
        return fileNameWithExtension.equals(input.getName());
      }
    });
  }

  /**
   * Gets the project with the given name or creates and returns it if it is non-existent.
   *
   * @param projectName
   *          the name of the project
   * @return the project
   * @throws CoreException
   *           the core exception
   */
  protected IProject getOrCreatePluginProject(final String projectName) throws CoreException {
    if (!projects.containsKey(projectName) || projects.get(projectName) == null) {
      return createPluginProject(projectName, null, REQUIRED_BUNDLES, null, null);
    }
    return projects.get(projectName);
  }

  /**
   * Creates and returns a project with the given name and the given required bundles.
   *
   * @param projectName
   *          the name of the project
   * @param folders
   *          the folders to create (source and source-generated folders will be created unless methods are overridden and specified as null or empty)
   * @param requiredBundles
   *          required bundles of the project to be created, or {@code null} if none
   * @param importedPackages
   *          imported packages of the project to be created, or {@code null} if none
   * @param exportedPackages
   *          exported packages of the project to be created, or {@code null} if none
   * @return the project
   * @throws CoreException
   *           the core exception
   */
  protected IProject createPluginProject(final String projectName, final List<String> folders, final List<String> requiredBundles, final List<String> importedPackages, final List<String> exportedPackages) throws CoreException { // NOPMD
                                                                                                                                                                                                                                    // NPathComplexity
    final PluginProjectFactory projectFactory = projectFactoryProvider.get();
    projectFactory.setProjectName(projectName);
    String sourceFolderName = getSourceFolderName();
    if (sourceFolderName != null && sourceFolderName.length() > 0) {
      projectFactory.addFolders(Arrays.asList(sourceFolderName));
    }
    String sourceFolderGeneratedName = getSourceFolderGeneratedName();
    if (sourceFolderGeneratedName != null && sourceFolderGeneratedName.length() > 0) {
      projectFactory.addFolders(Arrays.asList(sourceFolderGeneratedName));
    }
    if (folders != null) {
      projectFactory.addFolders(folders);
    }
    projectFactory.addBuilderIds(JavaCore.BUILDER_ID, "org.eclipse.pde.ManifestBuilder", "org.eclipse.pde.SchemaBuilder", XtextProjectHelper.BUILDER_ID); //$NON-NLS-1$ //$NON-NLS-2$
    projectFactory.addProjectNatures(JavaCore.NATURE_ID, IBundleProjectDescription.PLUGIN_NATURE, XtextProjectHelper.NATURE_ID);
    if (requiredBundles != null) {
      projectFactory.addRequiredBundles(requiredBundles);
    }
    if (importedPackages != null) {
      projectFactory.addImportedPackages(importedPackages);
    }
    if (exportedPackages != null) {
      projectFactory.addExportedPackages(exportedPackages);
    }
    final IProject[] result = new IProject[1];
    WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

      @Override
      protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        result[0] = projectFactory.createProject(monitor, null);
        IJavaProject javaProject = JavaCore.create(result[0]);
        JavaProjectSetupUtil.makeJava5Compliant(javaProject);
        JavaProjectSetupUtil.addJreClasspathEntry(javaProject);
      }
    };
    try {
      operation.run(new NullProgressMonitor());
    } catch (InvocationTargetException e) {
      return null;
    } catch (InterruptedException e) {
      return null;
    }
    return projects.put(projectName, result[0]);
  }

  /**
   * Returns the name of the source folder.
   *
   * @return the name of the source folder
   */
  protected String getSourceFolderName() {
    return "src"; //$NON-NLS-1$
  }

  /**
   * Returns the name of the source folder for generated sources.
   *
   * @return the name of the source folder for generated sources
   */
  protected String getSourceFolderGeneratedName() {
    return "src-gen"; //$NON-NLS-1$
  }

  /**
   * Adds sources with given file names to the {@link #getSourceFolderName()} folder of current project. If resources
   * to be added to workspace do not contain a file extension in their file name, it is nevertheless added on files
   * created.
   *
   * @param projectName
   *          the name of the project
   * @param sourceFileNames
   *          the source file names
   */
  public void addSourcesToWorkspace(final String projectName, final List<String> sourceFileNames) {
    addSourcesToWorkspace(projectName, Maps.uniqueIndex(sourceFileNames, Functions.<String> identity()));
  }

  /**
   * Adds sources with given file names to the {@link #getSourceFolderName()} folder of current project. If resources
   * to be added to workspace do not contain a file extension in their file name, it is nevertheless added on files
   * created.
   *
   * @param projectName
   *          the name of the project
   * @param sourceFileNames
   *          the source file names, mapping input filename to output filename
   */
  public void addSourcesToWorkspace(final String projectName, final ImmutableMap<String, String> sourceFileNames) {
    try {
      new WorkspaceModifyOperation() {
        @Override
        protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
          for (String inputFileName : sourceFileNames.keySet()) {
            // Calculate output filename
            final String outputFileName = sourceFileNames.get(inputFileName);
            try {
              final String contents = getContents(inputFileName);
              // Create URI including file extension
              URI resourceURI = URI.createPlatformResourceURI(getFullFileName(projectName, outputFileName), true);
              IResourcesSetupUtil.createFile(resourceURI.toPlatformString(true), contents);
            } catch (IOException e) {
              LOGGER.error("failed adding file to workspace: " + outputFileName, e); //$NON-NLS-1$
              Assert.fail("Error adding file " + outputFileName + " to workspace: " + e.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
            }
          }
        }
      }.run(new NullProgressMonitor());
    } catch (InvocationTargetException e) {
      Assert.fail("Error adding files to workspace: " + e.getMessage()); //$NON-NLS-1$
    } catch (InterruptedException e) {
      Assert.fail("Error adding files to workspace: " + e.getMessage()); //$NON-NLS-1$
    }
  }

  /**
   * Gets the full path to the {@link #getSourceFolderName()} folder.
   *
   * @param projectName
   *          the name of the project
   * @return the source folder path
   */
  protected String getSourceFolderPath(final String projectName) {
    return '/' + projectName + '/' + getSourceFolderName() + '/';
  }

  /**
   * Gets the {@link IFile} of the given project and path.
   *
   * @param projectName
   *          the name of the project
   * @param filePath
   *          the file path
   * @return the {@link IFile} of the given project and path
   */
  protected IFile getFileFromProject(final String projectName, final String filePath) {
    return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getFile(filePath);
  }

  /**
   * Gets the contents of a given file. Contents are returned as a single string.
   *
   * @param file
   *          the file
   * @return the file contents
   * @throws CoreException
   *           thrown if file could not be read
   * @throws IOException
   *           thrown if file could not be read
   */
  public String getContents(final IFile file) throws IOException, CoreException {
    InputStreamReader reader = null;
    try {
      reader = new InputStreamReader(file.getContents());
      return normalizeLineBreaks(CharStreams.toString(reader));
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          LOGGER.info("Failed to close test file " + file.getName());
        }
      }
    }
  }

  /**
   * Gets the contents of a specified resource using the default charset.
   *
   * @param resourceName
   *          the name of the resource
   * @return the normalized contents of the specified resource
   * @throws IOException
   *           if model could not be read
   */
  public String getContents(final String resourceName) throws IOException {
    URL resource = Resources.getResource(this.getClass(), resourceName);
    return normalizeLineBreaks(Resources.toString(resource, Charset.defaultCharset()));
  }

  /**
   * Asserts that generation was successful, i.e. the given file exists, and the content is expected.
   *
   * @param projectName
   *          the name of the project
   * @param fileName
   *          the name of the generated file
   * @param expectedGeneratedContent
   *          the expected content of the generated file
   * @throws IOException
   *           the {@link IOException}
   * @throws CoreException
   *           the {@link CoreException}
   */
  public void assertFileGenerated(final String projectName, final String fileName, final String expectedGeneratedContent) throws IOException, CoreException {
    IFile generatedFile = getFileFromProject(projectName, fileName);
    Assert.assertTrue(MessageFormat.format(MESSAGE_GENERATED_FILE_MUST_EXIST, generatedFile.toString()), generatedFile.exists());

    String actualGeneratedContent = getContents(generatedFile);
    Assert.assertEquals(MessageFormat.format(MESSAGE_GENERATED_CODE_MUST_BE_CORRECT, generatedFile.toString()), expectedGeneratedContent, actualGeneratedContent);
  }

  /**
   * Replaces all <code>\r\n</code> with <code>\n</code>.
   *
   * @param text
   *          input text
   * @return the normalized test
   */
  public String normalizeLineBreaks(final String text) {
    return text.replaceAll("\r\n", "\n");
  }
}
