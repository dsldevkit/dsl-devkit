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

package com.avaloq.tools.ddk.xtext.ui.quickfix;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IMarkerHelpRegistry;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IWorkbench;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.editor.quickfix.ILanguageResourceHelper;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.ui.util.IssueUtil;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;
import org.eclipse.xtext.validation.Issue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import com.avaloq.tools.ddk.check.runtime.ui.quickfix.IModificationContextRegistry;
import com.avaloq.tools.ddk.xtext.ui.quickfix.WorkbenchMarkerResolutionGenerator.WorkbenchResolutionAdapter;
import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;


public class WorkbenchResolutionAdaptorRunTest {

  private static final String TEST_FILE_NAME = "TestFileName"; //$NON-NLS-1$

  private final WorkbenchMarkerResolutionGenerator.RegistryProvider mockRegistryProvider = mock(WorkbenchMarkerResolutionGenerator.RegistryProvider.class);
  private final IMarkerHelpRegistry mockMarkerHelpRegistry = mock(IMarkerHelpRegistry.class);
  private final IStorage2UriMapper mockStorage2UriMapper = mock(IStorage2UriMapper.class);
  private final ILanguageResourceHelper mockLanguageResourceHelper = mock(ILanguageResourceHelper.class);
  private final IssueResolutionProvider mockIssueResolutionProvider = mock(IssueResolutionProvider.class);

  private final Injector injector = Guice.createInjector(new AbstractModule() {
    @Override
    protected void configure() {
      bind(String.class).annotatedWith(Names.named(Constants.LANGUAGE_NAME)).toInstance("com.avaloq.tools.dsl.TestLang"); //$NON-NLS-1$
      bind(IWorkbench.class).toInstance(mock(IWorkbench.class));
      bind(IResourceDescriptions.class).toInstance(mock(IResourceDescriptions.class));
      bind(IWorkspace.class).toInstance(mock(IWorkspace.class));

      bind(IModificationContextRegistry.class).toInstance(mock(IModificationContextRegistry.class));

      bind(WorkbenchMarkerResolutionGenerator.RegistryProvider.class).toInstance(mockRegistryProvider);
      bind(IStorage2UriMapper.class).toInstance(mockStorage2UriMapper);
      bind(ILanguageResourceHelper.class).toInstance(mockLanguageResourceHelper);
      bind(IssueResolutionProvider.class).toInstance(mockIssueResolutionProvider);
    }
  });

  private IssueResolution mockIssueResolution;
  private IMarker mockMarker;
  private IMarkerResolution[] mockMarkerResolutions;
  private IFile mockFile;

  private WorkbenchResolutionAdapter adapter;
  private final WorkbenchMarkerResolutionGenerator wmrg = injector.getInstance(WorkbenchMarkerResolutionGenerator.class);

  @Before
  public void setUp() throws Exception {
    wmrg.setIssueUtil(new IssueUtil());

    when(mockRegistryProvider.get()).thenReturn(mockMarkerHelpRegistry);

    mockIssueResolution = mock(IssueResolution.class);
    mockMarker = mock(IMarker.class);

    IMarkerResolution mockMarkerResolution = wmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockMarker);

    mockMarkerResolutions = new IMarkerResolution[] {mockMarkerResolution};

    mockFile = mock(IFile.class);

    when(mockIssueResolutionProvider.getResolutions(Matchers.any(Issue.class))).thenReturn(Lists.newArrayList(mockIssueResolution));
  }

  private void mockMarkerResource(final URI uri) throws CoreException {
    // Resource is a file and so gets processed
    when(mockMarker.getResource()).thenReturn(mockFile);
    when(mockFile.getName()).thenReturn(uri.lastSegment());
    when(mockFile.findMarkers(anyString(), anyBoolean(), anyInt())).thenReturn(new IMarker[] {});
    when(mockMarker.getAttribute(eq(Issue.URI_KEY), anyString())).thenReturn(uri.toString());
    when(mockMarker.isSubtypeOf(eq(MarkerTypes.ANY_VALIDATION))).thenReturn(true);
    when(mockStorage2UriMapper.getUri(eq(mockFile))).thenReturn(uri);
    @SuppressWarnings("unchecked")
    Iterable<Pair<IStorage, IProject>> storages = Lists.newArrayList(Tuples.create((IStorage) mockFile, mock(IProject.class)));
    when(mockStorage2UriMapper.getStorages(eq(uri))).thenReturn(storages);
    when(mockLanguageResourceHelper.isLanguageResource(eq(mockFile))).thenReturn(true);
  }

  @Test
  public void testRun() throws CoreException {
    adapter = wmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockMarker);
    when(mockMarkerHelpRegistry.getResolutions(mockMarker)).thenReturn(mockMarkerResolutions);

    mockMarkerResource(URI.createURI("platform://dummy/" + TEST_FILE_NAME)); //$NON-NLS-1$

    /* Test call */
    adapter.run(mockMarker);

    // Resolution is applied
    verify(mockIssueResolution, times(1)).apply();
  }

  @Test
  public void testSingleRun() throws CoreException {
    adapter = wmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockMarker);
    when(mockMarkerHelpRegistry.getResolutions(mockMarker)).thenReturn(mockMarkerResolutions);

    mockMarkerResource(URI.createURI("platform://dummy/" + TEST_FILE_NAME)); //$NON-NLS-1$

    /* Test call */
    adapter.run(new IMarker[] {mockMarker}, null);

    verify(mockIssueResolution, times(1)).apply();
  }

}
