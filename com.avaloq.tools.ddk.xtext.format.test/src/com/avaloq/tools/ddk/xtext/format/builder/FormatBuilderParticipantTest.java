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
package com.avaloq.tools.ddk.xtext.format.builder;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.format.ui.builder.FormatBuilderParticipant;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * A test class for {@link FormatBuilderParticipant} and com.avaloq.tools.ddk.xtext.ui.builder.AbstractConditionalBuilderParticipant
 */
public class FormatBuilderParticipantTest extends AbstractXtextTest {

  private static final String TEST_PROJECT_NAME = "resource";

  private static final String[] CORRECT_URI_SEGMENTS = new String[] {TEST_PROJECT_NAME, "com.avaloq.tools.ddk.xtext", "src", "com", "avaloq", "tools", "ddk",
      "xtext"};

  private static final String[] BIN_URI_SEGMENTS = new String[] {TEST_PROJECT_NAME, "com.avaloq.tools.ddk.xtext", "bin", "com", "avaloq", "tools", "ddk",
      "xtext"};

  private static final String[] INCORRECT_URI_SEGMENTS = new String[] {TEST_PROJECT_NAME, "com.avaloq.tools.ddk.xtext"};

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
    assertTrue("Check if the delta resource has correct extension", participant.hasCorrectExtension(delta, resourceServiceProvider));
    assertFalse("Check if the delta resource has incorrect extension", participant.hasCorrectExtension(delta, resourceServiceProvider));
  }

  /**
   * Tests whether a {@link Delta} resource comes form the right (SRC) directory to be used by org.eclipse.xtext.builder.BuilderParticipant.
   */
  @Test
  public void isSourceOriginatedTest() {
    when(uriCorrect.segments()).thenReturn(CORRECT_URI_SEGMENTS);
    assertTrue("Check if the delta resource has correct URI and comes from SRC directory", participant.isSourceOriginated(delta));
    when(uriCorrect.segments()).thenReturn(BIN_URI_SEGMENTS);
    assertFalse("Check if the delta resource has correct URI and does not come from SRC directory", participant.isSourceOriginated(delta));
    when(uriCorrect.segments()).thenReturn(INCORRECT_URI_SEGMENTS);
    assertFalse("Check if the delta resource has incorrect URI and comes from SRC directory", participant.isSourceOriginated(delta));
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
  public static class IsUri extends ArgumentMatcher<URI> {

    /** {@inheritDoc} */
    @Override
    public boolean matches(final Object uri) {
      return (uri instanceof URI);
    }
  }
}

