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

package com.avaloq.tools.ddk.check.core.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.Validate;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.eclipse.xtext.xbase.compiler.OnTheFlyJavaCompiler.EclipseRuntimeDependentJavaCompiler;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckGenerator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * An abstract test class for tests on Check models. Allows creating a project, adding files, and generating and compiling the project.
 */
@SuppressWarnings("deprecation")
public class AbstractCheckGenerationTestCase extends AbstractCheckTestCase {

  @Inject
  private CheckGenerator generator;

  @Inject
  private EclipseRuntimeDependentJavaCompiler javaCompiler;

  @Inject
  private IOutputConfigurationProvider outputConfigurationProvider;

  @Inject
  private Injector injector;

  protected static final String VALIDATOR_NAME_SUFFIX = "CheckImpl";
  protected static final String CATALOG_NAME_SUFFIX = "CheckCatalog";
  protected static final ImmutableSet<String> GENERATED_FILES = ImmutableSet.of(VALIDATOR_NAME_SUFFIX, CATALOG_NAME_SUFFIX, "IssueCodes", "PreferenceInitializer", "StandaloneSetup");

  /**
   * Get Java compiler.
   *
   * @return Java compiler
   */
  protected EclipseRuntimeDependentJavaCompiler getJavaCompiler() {
    return javaCompiler;
  }

  /**
   * Generate and compile a Check.
   *
   * @param sourceStream
   *          stream containing the Check, must not be {@code null}
   * @return map of class name to compiled class, never {@code null}
   */
  public Map<String, Class<?>> generateAndCompile(final InputStream sourceStream) {
    Validate.notNull(sourceStream, "Argument sourceStream may not be null");

    XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
    Resource res = resourceSet.createResource(URI.createURI("BugDsl27.check"));
    try {
      res.load(sourceStream, null);
    } catch (IOException e) {
      fail("Could not load test resource" + e.getMessage());
    }
    CheckCatalog root = (CheckCatalog) res.getContents().get(0);
    assertNotNull("Resource should contain a CheckCatalog", root);
    // We also should have some Jvm model here.
    JvmType type = null;
    for (EObject obj : res.getContents()) {
      if (obj instanceof JvmType) {
        type = (JvmType) obj;
        break;
      }
    }
    assertNotNull("Should have an inferred Jvm model", type);
    // Run the generator using an in-memory file system access
    InMemoryFileSystemAccess fsa = new InMemoryFileSystemAccess();
    for (OutputConfiguration output : outputConfigurationProvider.getOutputConfigurations()) {
      fsa.getOutputConfigurations().put(output.getName(), output);
    }
    generator.doGenerate(res, fsa);
    // We now should have a number of files.
    String baseName = root.getPackageName() + '.' + root.getName();
    String basePath = baseName.replace('.', '/');
    Map<String, String> sources = Maps.newHashMap();
    for (String name : GENERATED_FILES) {
      sources.put(baseName + name, fsa.readTextFile(basePath + name + ".java", IFileSystemAccess.DEFAULT_OUTPUT).toString());
    }
    // Compile the generated Java files. Raises an IllegalArgumentException if compilation failed.
    try {
      // Put together a classpath for the compiler. Since we don't know exactly what pathes would be in the transitive closure
      // that eclipse computes from the plugin dependencies, and we also cannot get conveniently at the pathes used by our
      // own class loader, let eclipse do the work: create our test project and then get the resolved classpath entries from
      // that.
      IProject project = getOrCreatePluginProject();
      IResourcesSetupUtil.reallyWaitForAutoBuild();
      // enumerateContents(project);
      IJavaProject javaProject = JavaCore.create(project);
      javaCompiler.clearClassPath();
      for (IClasspathEntry entry : javaProject.getResolvedClasspath(true)) {
        javaCompiler.addClassPath(entry.getPath().toString());
      }
      // Set our own class loader, otherwise the compiler fails. It can compile the sources, but then fails to load them,
      // as the implementation only can load from its temporary folder. Alternatively, we could create our own URLClassLoader
      // with all the classpathes we added above. The javaCompiler evidently is not intended to be used for sources that
      // reference external classes, but only for self-contained test sets. However, we don't have that here; our
      // generated classes do reference quite a few other classes.
      javaCompiler.setParentClassLoader(getClass().getClassLoader());
      final Map<String, Class<?>> compiledClasses = javaCompiler.compileToClasses(sources);
      assertEquals("All sources should have been compiled", sources.size(), compiledClasses.size());
      return compiledClasses;

      // CHECKSTYLE:OFF Yes, catch anything
    } catch (Exception e) {
      // CHECKSTYLE:ON
      fail("Java compilation failed: " + e.getMessage());
      return Collections.emptyMap();
    }
  }

}
