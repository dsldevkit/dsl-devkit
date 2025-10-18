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

package com.avaloq.tools.ddk.xtext.ui.quickfix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.eclipse.core.resources.IMarker;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.util.IssueUtil;
import org.eclipse.xtext.validation.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.ui.quickfix.WorkbenchMarkerResolutionGenerator.WorkbenchResolutionAdapter;


public class WorkbenchResolutionAdaptorTest {

  private static final String TEST_LABEL = "ATestLabel"; //$NON-NLS-1$
  private static final String TEST_DESCRIPTION = "ATestDescription"; //$NON-NLS-1$
  private static final String CODE1 = "CODE1"; //$NON-NLS-1$
  private static final String CODE2 = "CODE2"; //$NON-NLS-1$
  private static final Image TEST_IMAGE = null;

  private final WorkbenchMarkerResolutionGenerator mockWmrg = mock(WorkbenchMarkerResolutionGenerator.class);

  @BeforeEach
  void setUp() throws Exception {
    when(mockWmrg.getIssueUtil()).thenReturn(new IssueUtil());
  }

  @Test
  void testGetLabel() {
    IssueResolution mockIssueResolution = mock(IssueResolution.class);
    IMarker mockMarker = mock(IMarker.class);

    when(mockIssueResolution.getLabel()).thenReturn(TEST_LABEL);

    WorkbenchResolutionAdapter adapter = mockWmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockMarker);

    assertEquals(TEST_LABEL, adapter.getLabel(), "Adapter delegates get label to resolution."); //$NON-NLS-1$
  }

  @Test
  void testGetDescription() {
    IssueResolution mockIssueResolution = mock(IssueResolution.class);
    IMarker mockMarker = mock(IMarker.class);

    when(mockIssueResolution.getDescription()).thenReturn(TEST_DESCRIPTION);

    WorkbenchResolutionAdapter adapter = mockWmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockMarker);

    assertEquals(TEST_DESCRIPTION, adapter.getDescription(), "Adapter delegates get description to resolution."); //$NON-NLS-1$
  }

  @Test
  void testGetImage() {
    IssueResolution mockIssueResolution = mock(IssueResolution.class);
    IMarker mockMarker = mock(IMarker.class);

    when(mockWmrg.getImage(mockIssueResolution)).thenReturn(TEST_IMAGE);

    WorkbenchResolutionAdapter adapter = mockWmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockMarker);

    assertEquals(TEST_IMAGE, adapter.getImage(), "Adapter delegates get Image to resolution."); //$NON-NLS-1$
  }

  @Test
  void findOtherMarkers() {
    IssueResolution mockIssueResolution = mock(IssueResolution.class);
    IMarker mockResolutionMarker = mock(IMarker.class);
    when(mockResolutionMarker.getAttribute(Issue.CODE_KEY, null)).thenReturn(CODE1);
    IMarker mockMarker1 = mock(IMarker.class);
    when(mockMarker1.getAttribute(Issue.CODE_KEY, null)).thenReturn(CODE1);
    IMarker mockMarker2 = mock(IMarker.class);
    when(mockMarker2.getAttribute(Issue.CODE_KEY, null)).thenReturn(CODE2);
    IMarker mockMarker3 = mock(IMarker.class);
    when(mockMarker3.getAttribute(Issue.CODE_KEY, null)).thenReturn(CODE1);

    IMarker[] allMarkers = new IMarker[] {mockMarker1, mockMarker2, mockMarker3};
    IMarker[] matchingMarkers = new IMarker[] {mockMarker1, mockMarker3};

    WorkbenchResolutionAdapter adapter = mockWmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockResolutionMarker);

    assertArrayEquals(matchingMarkers, adapter.findOtherMarkers(allMarkers), "Adapter findOtherMarkers matching on CODE1."); //$NON-NLS-1$

    when(mockResolutionMarker.getAttribute(Issue.CODE_KEY, null)).thenReturn(CODE2);
    matchingMarkers = new IMarker[] {mockMarker2};

    adapter = mockWmrg.new WorkbenchResolutionAdapter(mockIssueResolution, mockResolutionMarker);

    assertArrayEquals(matchingMarkers, adapter.findOtherMarkers(allMarkers), "Adapter findOtherMarkers matching on CODE2."); //$NON-NLS-1$

  }

}
