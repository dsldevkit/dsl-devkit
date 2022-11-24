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
package com.avaloq.tools.ddk.xtext.test;

import static java.util.Collections.singleton;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.ui.resource.Storage2UriMapperImpl;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;


/**
 * A base class for util test classes, which prepares common required mocks.
 */
public abstract class AbstractUtilTest extends AbstractXtextTest {

  public static final String TEST_PROJECT_NAME = "TestProjectName";
  private static final String DUMMY_PATH = TEST_PROJECT_NAME + "/TEST/";

  // CHECKSTYLE:CHECK-OFF VisibilityModifierCheck
  protected static Delta delta;
  protected static IResourceDescription oldDesc;
  protected static IResourceDescription newDesc;
  protected static URI uriCorrect;
  protected static Resource resource;
  protected static IStorage2UriMapper mapperCorrect;
  protected static IFile file;

  // CHECKSTYLE:CHECK-ON VisibilityModifierCheck

  /**
   * Prepare mocks for all tests.
   */
  public static void prepareMocksBase() {
    oldDesc = mock(IResourceDescription.class);
    newDesc = mock(IResourceDescription.class);
    delta = mock(Delta.class);
    resource = mock(Resource.class);
    uriCorrect = mock(URI.class);
    when(uriCorrect.isPlatformResource()).thenReturn(true);
    when(uriCorrect.isFile()).thenReturn(true);
    when(uriCorrect.toFileString()).thenReturn(DUMMY_PATH);
    when(uriCorrect.toPlatformString(true)).thenReturn(DUMMY_PATH);
    when(delta.getNew()).thenReturn(newDesc);
    when(delta.getOld()).thenReturn(oldDesc);
    when(delta.getUri()).thenReturn(uriCorrect);
    when(resource.getURI()).thenReturn(uriCorrect);
    file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uriCorrect.toPlatformString(true)));
    Iterable<Pair<IStorage, IProject>> storages = singleton(Tuples.<IStorage, IProject> create(file, file.getProject()));
    mapperCorrect = mock(Storage2UriMapperImpl.class);
    when(mapperCorrect.getStorages(uriCorrect)).thenReturn(storages);
  }

}

