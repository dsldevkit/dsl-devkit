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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.testing.InMemoryJavaCompiler;
import org.eclipse.xtext.xbase.testing.JavaSource;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.generator.CheckGenerator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Injector;


/**
 * An abstract test class for tests on Check models. Allows creating a project, adding files, and generating and compiling the project.
 */
@SuppressWarnings("nls")
public class AbstractCheckGenerationTestCase extends AbstractCheckTestCase {

  @Inject
  private CheckGenerator generator;

  @Inject
  private IOutputConfigurationProvider outputConfigurationProvider;

  @Inject
  private Injector injector;

  protected static final String VALIDATOR_NAME_SUFFIX = "CheckImpl";
  protected static final String CATALOG_NAME_SUFFIX = "CheckCatalog";
  protected static final String ISSUE_CODES_SUFFIX = "IssueCodes";
  protected static final ImmutableSet<String> GENERATED_FILES = ImmutableSet.of(VALIDATOR_NAME_SUFFIX, CATALOG_NAME_SUFFIX, ISSUE_CODES_SUFFIX, "PreferenceInitializer", "StandaloneSetup");

  /**
   * Generate and compile a Check.
   *
   * @param sourceStream
   *          stream containing the Check, must not be {@code null}
   * @return map of class name to compiled class, never {@code null}
   */
  public List<JavaSource> generateAndCompile(final InputStream sourceStream) {
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
    List<JavaSource> sources = Lists.newArrayList();
    for (String name : GENERATED_FILES) {
      sources.add(new JavaSource(root.getName() + name, fsa.readTextFile(basePath + name + ".java").toString()));
    }
    // Compile the generated Java files. Raises an IllegalArgumentException if compilation failed.
    try {

      InMemoryJavaCompiler javaCompiler = new InMemoryJavaCompiler(getClass().getClassLoader(), JavaVersion.JAVA8);

      final InMemoryJavaCompiler.Result result = javaCompiler.compile(sources.toArray(new JavaSource[sources.size()]));
      // Due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=541225 we must ignore this warning here
      if (result.getCompilationProblems().stream().anyMatch(p -> "Pb(1102) At least one of the problems in category 'nls' is not analysed due to a compiler option being ignored".equals(p.getMessage()))) {
        assertTrue("All sources should have been compiled without errors " + result.getCompilationProblems(), result.getCompilationProblems().isEmpty());
      }

      return sources;

      // CHECKSTYLE:OFF Yes, catch anything
    } catch (Exception e) {
      // CHECKSTYLE:ON
      fail("Java compilation failed: " + e.getMessage());
      return Collections.emptyList();
    }
  }

}