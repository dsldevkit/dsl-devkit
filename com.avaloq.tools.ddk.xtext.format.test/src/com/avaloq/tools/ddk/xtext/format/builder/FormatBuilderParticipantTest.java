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
package com.avaloq.tools.ddk.xtext.format.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.builder.preferences.BuilderPreferenceAccess;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import com.avaloq.tools.ddk.xtext.format.ui.builder.FormatBuilderParticipant;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTestUtil;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * A test class for {@link FormatBuilderParticipant} and com.avaloq.tools.ddk.xtext.ui.builder.AbstractConditionalBuilderParticipant
 */
@SuppressWarnings("nls")
public class FormatBuilderParticipantTest extends AbstractXtextTest {

  private static final String TEST_PROJECT_NAME = "resource";

  private static final String[] CORRECT_URI_SEGMENTS = new String[] {TEST_PROJECT_NAME, "com.avaloq.tools.ddk.xtext", "src", "com", "avaloq", "tools", "ddk",
      "xtext"};

  private static final String[] BIN_URI_SEGMENTS = new String[] {TEST_PROJECT_NAME, "com.avaloq.tools.ddk.xtext", "bin", "com", "avaloq", "tools", "ddk",
      "xtext"};

  private static final String[] INCORRECT_URI_SEGMENTS = new String[] {TEST_PROJECT_NAME, "com.avaloq.tools.ddk.xtext"};

  private static final String FORMAT_FILE_EXTENSION = "format";

  private static final URI FORMAT_URI = URI.createPlatformResourceURI("/project/src/pkg/My.format", true);

  private static final URI OTHER_URI = URI.createPlatformResourceURI("/project/src/pkg/My.check", true);

  private static Delta delta;
  private static URI uriCorrect;
  private static FormatBuilderParticipant participant;

  /**
   * Prepare mocks for all tests.
   */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    participant = new FormatBuilderParticipant();
    delta = mock(Delta.class);
    uriCorrect = mock(URI.class);
    when(delta.getUri()).thenReturn(uriCorrect);
  }

  /**
   * Tests whether a {@link Delta} resource has a correct extension to be used by org.eclipse.xtext.builder.BuilderParticipant.
   */
  @Test
  public void hasCorrectExtensionTest() {
    IResourceServiceProvider resourceServiceProvider = mock(IResourceServiceProvider.class);
    when(resourceServiceProvider.canHandle(argThat(new IsUri()))).thenReturn(true, false);
    assertTrue(participant.hasCorrectExtension(delta, resourceServiceProvider), "Check if the delta resource has correct extension");
    assertFalse(participant.hasCorrectExtension(delta, resourceServiceProvider), "Check if the delta resource has incorrect extension");
  }

  /**
   * Tests whether a {@link Delta} resource comes form the right (SRC) directory to be used by org.eclipse.xtext.builder.BuilderParticipant.
   */
  @Test
  public void isSourceOriginatedTest() {
    when(uriCorrect.segments()).thenReturn(CORRECT_URI_SEGMENTS);
    assertTrue(participant.isSourceOriginated(delta), "Check if the delta resource has correct URI and comes from SRC directory");
    when(uriCorrect.segments()).thenReturn(BIN_URI_SEGMENTS);
    assertFalse(participant.isSourceOriginated(delta), "Check if the delta resource has correct URI and does not come from SRC directory");
    when(uriCorrect.segments()).thenReturn(INCORRECT_URI_SEGMENTS);
    assertFalse(participant.isSourceOriginated(delta), "Check if the delta resource has incorrect URI and comes from SRC directory");
  }

  /**
   * Tests that a build without a format delta keeps the resources of the build's resource set.
   *
   * @throws CoreException
   *           if the build fails
   */
  @Test
  public void buildWithoutFormatDeltaKeepsResourceSet() throws CoreException {
    ResourceSet resourceSet = build(changedDelta(OTHER_URI), removedDelta(OTHER_URI));
    assertEquals(1, resourceSet.getResources().size(), "A build without format work must keep the resource set");
  }

  /**
   * Tests that a build with a changed format resource clears the build's resource set.
   *
   * @throws CoreException
   *           if the build fails
   */
  @Test
  public void buildWithChangedFormatDeltaClearsResourceSet() throws CoreException {
    ResourceSet resourceSet = build(changedDelta(OTHER_URI), changedDelta(FORMAT_URI));
    assertTrue(resourceSet.getResources().isEmpty(), "A build with a changed format resource must clear the resource set");
  }

  /**
   * Tests that a build with a removed format resource clears the build's resource set.
   *
   * @throws CoreException
   *           if the build fails
   */
  @Test
  public void buildWithRemovedFormatDeltaClearsResourceSet() throws CoreException {
    ResourceSet resourceSet = build(removedDelta(FORMAT_URI));
    assertTrue(resourceSet.getResources().isEmpty(), "A build with a removed format resource must clear the resource set");
  }

  /**
   * Tests that a build with a format resource clears the build's resource set before the generation of the inherited build starts, so that format models
   * are linked against freshly loaded models.
   *
   * @throws CoreException
   *           if the build fails
   */
  @Test
  public void buildClearsResourceSetBeforeGeneration() throws CoreException {
    List<Integer> resourcesAtGeneration = new ArrayList<>();
    build(resourcesAtGeneration, changedDelta(FORMAT_URI));
    assertEquals(List.of(0), resourcesAtGeneration, "The resource set must be cleared before the inherited build collects the deltas to generate");
  }

  /**
   * Runs an enabled participant on a build with the given deltas and a resource set holding one resource.
   *
   * @param deltas
   *          the deltas of the build
   * @return the resource set of the build
   * @throws CoreException
   *           if the build fails
   */
  private ResourceSet build(final Delta... deltas) throws CoreException {
    return build(new ArrayList<>(), deltas);
  }

  /**
   * Runs an enabled participant on a build with the given deltas and a resource set holding one resource.
   * <p>
   * The inherited build starts by collecting the deltas to generate ({@code getRelevantDeltas}); the participant records the size of the resource set at
   * that point and returns no deltas, so that the build stops before generating.
   * </p>
   *
   * @param resourcesAtGeneration
   *          receives the number of resources in the resource set each time the inherited build starts
   * @param deltas
   *          the deltas of the build
   * @return the resource set of the build
   * @throws CoreException
   *           if the build fails
   */
  private ResourceSet build(final List<Integer> resourcesAtGeneration, final Delta... deltas) throws CoreException {
    IResourceServiceProvider resourceServiceProvider = mock(IResourceServiceProvider.class);
    when(resourceServiceProvider.canHandle(any())).thenAnswer(invocation -> FORMAT_FILE_EXTENSION.equals(invocation.<URI> getArgument(0).fileExtension()));
    FormatBuilderParticipant buildParticipant = new FormatBuilderParticipant() {
      @Override
      public IResourceServiceProvider getResourceServiceProvider() {
        return resourceServiceProvider;
      }

      @Override
      protected List<Delta> getRelevantDeltas(final IBuildContext context) {
        resourcesAtGeneration.add(context.getResourceSet().getResources().size());
        return List.of();
      }
    };
    BuilderPreferenceAccess preferenceAccess = mock(BuilderPreferenceAccess.class);
    when(preferenceAccess.isAutoBuildEnabled(any())).thenReturn(true);
    buildParticipant.setBuilderPreferenceAccess(preferenceAccess);

    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResources().add(new ResourceImpl(OTHER_URI));
    IBuildContext context = mock(IBuildContext.class);
    when(context.getResourceSet()).thenReturn(resourceSet);
    when(context.getDeltas()).thenReturn(ImmutableList.copyOf(deltas));

    buildParticipant.build(context, new NullProgressMonitor());
    return resourceSet;
  }

  private static Delta changedDelta(final URI uri) {
    Delta result = removedDelta(uri);
    when(result.getNew()).thenReturn(mock(IResourceDescription.class));
    return result;
  }

  private static Delta removedDelta(final URI uri) {
    Delta result = mock(Delta.class);
    when(result.getUri()).thenReturn(uri);
    when(result.getOld()).thenReturn(mock(IResourceDescription.class));
    return result;
  }

  @Override
  protected List<String> getRequiredSourceFileNames() {
    return ImmutableList.of();
  }

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

  /**
   * A Matcher for {@link URI}.
   */
  public static class IsUri implements ArgumentMatcher<URI> {

    @Override
    public boolean matches(final URI argument) {
      return true;
    }
  }
}
