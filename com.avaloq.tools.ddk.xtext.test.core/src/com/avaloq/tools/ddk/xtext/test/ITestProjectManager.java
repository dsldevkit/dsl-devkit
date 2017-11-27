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
package com.avaloq.tools.ddk.xtext.test;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;


/**
 * Interface for managing a test project.
 */
public interface ITestProjectManager {

  /**
   * Set up the test project. This operation includes the creation of the test project.
   * 
   * @param initialSources
   *          initial set of sources to add to test project, never {@code null}
   */
  void setup(Iterable<? extends TestSource> initialSources);

  /**
   * Tear down the test project. This operation includes the deletion of the test project.
   */
  void teardown();

  /**
   * Build the test project.
   */
  void build();

  /**
   * Turns auto build on/off during test execution.
   * <p>
   * Be aware that this flag is set and reset during {@link #setup} and {@link #teardown}!
   * </p>
   * <p>
   * Nevertheless, clients are expected to restore the original value once they are done!
   * </p>
   * 
   * @param autoBuildStatus
   *          boolean {@code true} to activate auto building, {@code false} to turn it off
   * @return the previous setting
   */
  boolean setAutobuild(final boolean autoBuildStatus);

  /**
   * Add the given source to the test project.
   * 
   * @param testSource
   *          the test source to add to the project
   * @return {@link IFile} created for test source
   */
  IFile addSourceToProject(TestSource testSource);

  /**
   * Returns all added test sources.
   * 
   * @return collection of all added test sources.
   */
  Collection<TestSource> getTestSources();

  /**
   * Returns a given test source previously {@link #addSourceToProject(TestSource) added}.
   * 
   * @param sourceName
   *          name of source to retrieve
   * @return test source with given name or {@code null}
   */
  TestSource getTestSource(String sourceName);

  /**
   * Remove the given file from the test project.
   * 
   * @param testSource
   *          the test source to remove
   */
  void removeTestSource(TestSource testSource);

  /**
   * Create the {@link URI} for the encoded file name.
   * 
   * @param encodedFileName
   *          a file name representing the target source. The file name must contain all information needed to create the requested URI. Usually the file type
   *          provides enough information. For the context file system, however, the target container must be encoded in the file name.
   * @return the {@link URI} for the given file name
   */
  URI createTestSourceUri(String encodedFileName);

}

