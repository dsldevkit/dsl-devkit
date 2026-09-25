/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.ui.editor.findrefs;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.xtext.resource.IReferenceDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.ui.editor.findrefs.ReferenceSearchResult;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Tests for {@link FastReferenceSearchResultContentProvider}.
 */
@SuppressWarnings("nls")
public class FastReferenceSearchResultContentProviderTest {

  private static final URI FIRST_SOURCE = URI.createURI("platform:/resource/project/a.test#/0");
  private static final URI SECOND_SOURCE = URI.createURI("platform:/resource/project/b.test#/0");

  @Disabled("Documents REF-2, see formal/BUGS.md; enable with the fix")
  @Test
  public void inputChangedToleratesReferencesAcceptedWhileRepopulating() {
    List<IReferenceDescription> matchingReferences = new ArrayList<>();
    matchingReferences.add(reference(FIRST_SOURCE));
    ReferenceSearchResult searchResult = mock(ReferenceSearchResult.class);
    when(searchResult.getMatchingReferences()).thenReturn(matchingReferences);

    // the search thread accepts another reference while the UI thread is repopulating from the live list
    IResourceDescription resourceDescription = mock(IResourceDescription.class);
    when(resourceDescription.getURI()).thenReturn(FIRST_SOURCE.trimFragment());
    IResourceDescriptions resourceDescriptions = mock(IResourceDescriptions.class);
    when(resourceDescriptions.getResourceDescription(any())).thenAnswer(invocation -> {
      if (matchingReferences.size() == 1) {
        matchingReferences.add(reference(SECOND_SOURCE));
      }
      return resourceDescription;
    });

    FastReferenceSearchResultContentProvider provider = new FastReferenceSearchResultContentProvider(resourceDescriptions);
    TreeViewer viewer = mock(TreeViewer.class);
    assertDoesNotThrow(() -> provider.inputChanged(viewer, null, searchResult));
  }

  private static IReferenceDescription reference(final URI sourceEObjectUri) {
    IReferenceDescription reference = mock(IReferenceDescription.class);
    when(reference.getSourceEObjectUri()).thenReturn(sourceEObjectUri);
    return reference;
  }

}
