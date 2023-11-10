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
package com.avaloq.tools.ddk.xtext.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assume.assumeNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.ui.resource.Storage2UriMapperImpl;
import org.eclipse.xtext.util.Pair;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.AbstractUtilTest;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * A test class for {@link RuntimeProjectUtil}
 */
public class RuntimeProjectUtilTest extends AbstractUtilTest {

  private static final String WORKSPACE_PATH = ResourcesPlugin.getWorkspace().getRoot().getLocationURI().getPath();

  public static final ImmutableList<String> SOURCE_NAMES = ImmutableList.of("FormatBuilderParticipantInput.format", "FormatBuilderParticipantOutput.format");

  private static URI uriInCorrect;
  private static IStorage2UriMapper mapperInCorrect;

  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return new AbstractXtextTestUtil() {

      @Override
      @Deprecated
      protected Injector getInjector() {
        return Guice.createInjector(new org.eclipse.xtext.XtextRuntimeModule());
      }

    };
  }

  @Override
  protected List<String> getRequiredSourceFileNames() {
    return SOURCE_NAMES;
  }

  /**
   * Prepare mocks for all tests.
   */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    prepareMocksBase();
    uriInCorrect = mock(URI.class);
    mapperInCorrect = mock(Storage2UriMapperImpl.class);
    when(mapperInCorrect.getStorages(uriInCorrect)).thenReturn(Collections.<Pair<IStorage, IProject>> emptySet());
    when(mapperInCorrect.getStorages(uriCorrect)).thenReturn(Collections.<Pair<IStorage, IProject>> emptySet());
  }

  /**
   * Tests extracting project path from a {@link Resource}.
   */
  @Test
  public void getPathProjectTest() {
    assertEquals("Check if the correct project path has been returned", WORKSPACE_PATH + "/"
        + TEST_PROJECT_NAME, RuntimeProjectUtil.getPathProject(resource, mapperCorrect));
  }

  /**
   * Tests extracting project name from a {@link URI}.
   */
  @Test
  public void getProjectCorrectTest() {
    super.addSourceToWorkspace(SOURCE_NAMES.get(0));
    IProject iproject = RuntimeProjectUtil.getProject(uriCorrect, mapperCorrect);
    assumeNotNull(iproject);
    assertEquals("Check if the correct project name has been returned", TEST_PROJECT_NAME, iproject.getName());
  }

  /**
   * Checks when passed {@link IStorage2UriMapper} is broken then null instead of project name expected.
   */
  @Test
  public void getProjectInCorrectTest() {
    super.addSourceToWorkspace(SOURCE_NAMES.get(0));
    IProject iproject = RuntimeProjectUtil.getProject(uriCorrect, mapperInCorrect);
    assertNull("When passed IStorage2UriMapper is broken then null instead of project name expected", iproject);

  }

  /**
   * Tests correct delegation of responsibility to the {@link IStorage2UriMapper}.
   */
  @Test
  public void findFileStorageCorrectTest() {
    super.addSourceToWorkspace(SOURCE_NAMES.get(0));
    assertEquals("Check if the correct file has been returned", RuntimeProjectUtil.findFileStorage(uriCorrect, mapperCorrect), file);
  }

  /**
   * Checks when passed {@link IStorage2UriMapper} is broken then no file expected.
   */
  @Test
  public void findFileStorageInCorrectTest() {
    assertNull("When passed IStorage2UriMapper is broken then no file expected", RuntimeProjectUtil.findFileStorage(uriInCorrect, mapperInCorrect));
  }

}
