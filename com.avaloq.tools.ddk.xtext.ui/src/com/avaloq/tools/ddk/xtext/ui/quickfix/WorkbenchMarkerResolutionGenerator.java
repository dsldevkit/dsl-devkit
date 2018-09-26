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

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.apache.commons.lang.NotImplementedException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMarkerHelpRegistry;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;
import org.eclipse.xtext.ui.MarkerTypes;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.quickfix.ILanguageResourceHelper;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.editor.quickfix.MarkerResolutionGenerator;
import org.eclipse.xtext.ui.util.IssueUtil;

import com.avaloq.tools.ddk.check.runtime.ui.quickfix.IModificationContextRegistry;
import com.avaloq.tools.ddk.check.runtime.ui.quickfix.IssueResolutionWrapper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * Extend the Xtext MarkerResolutionGenerator to return resolutions of WorkbenchMarkerResolution.
 * This provide support for bulk application of resolutions via the markers (Problem) view.
 */
public class WorkbenchMarkerResolutionGenerator extends MarkerResolutionGenerator {

  @Inject
  private ILanguageResourceHelper languageResourceHelper;
  @Inject
  private IModificationContextRegistry modificationContext;
  @Inject
  private LocationAwareIssueUtil injectedIssueUtil;
  private IssueUtil suppliedIssueUtil;

  /**
   * {@inheritDoc}
   * Note : this method is largely copied from the superclass, all we need to override is the call to
   * getAdaptedResolutions to provider the marker.
   */
  @Override
  public IMarkerResolution[] getResolutions(final IMarker marker) {
    final IMarkerResolution[] emptyResult = new IMarkerResolution[0];
    try {
      if (!marker.isSubtypeOf(MarkerTypes.ANY_VALIDATION)) {
        return emptyResult;
      }
    } catch (CoreException e) {
      return emptyResult;
    }
    if (!languageResourceHelper.isLanguageResource(marker.getResource())) {
      return emptyResult;
    }

    final XtextEditor editor = getEditor(marker.getResource());
    if (editor != null) {
      final IAnnotationModel annotationModel = editor.getDocumentProvider().getAnnotationModel(editor.getEditorInput());
      if (annotationModel != null && !isMarkerStillValid(marker, annotationModel)) {
        return emptyResult;
      }
    }

    final Iterable<IssueResolution> resolutions = getResolutionProvider().getResolutions(getIssueUtil().createIssue(marker));
    if (editor != null && editor.isEditorInputReadOnly()) {
      editor.close(false);
    }

    return getAdaptedResolutions(resolutions, marker);
  }

  @Override
  public IssueUtil getIssueUtil() {
    return (null != suppliedIssueUtil) ? suppliedIssueUtil : injectedIssueUtil;
  }

  @Override
  public void setIssueUtil(final IssueUtil issueUtil) {
    this.suppliedIssueUtil = issueUtil;
  }

  @Override
  @Deprecated
  public XtextEditor getEditor(final IResource resource) {
    if (Display.getCurrent() != null) {
      return super.getEditor(resource);
    }
    return null;
  }

  /**
   * {@inheritDoc}
   * This method may not be called in this implementation.
   * Use getAdaptedResolutions(final List<IssueResolution> resolutions, final IMarker marker).
   *
   * @throws {@code
   *           NotImplementedException} if called
   */
  @Override
  protected IMarkerResolution[] getAdaptedResolutions(final List<IssueResolution> resolutions) {
    throw new NotImplementedException();
  }

  /**
   * Adapt IssueResolution to IMarkerResolution, specifically WorkbenchMarkerResolution.
   *
   * @param resolutions
   *          the issue resolutions
   * @param marker
   *          the marker associated with the issue
   * @return an array of corresponding WorkbenchMarkerResolution
   */
  protected IMarkerResolution[] getAdaptedResolutions(final Iterable<IssueResolution> resolutions, final IMarker marker) {
    return StreamSupport.stream(resolutions.spliterator(), false).map(resolution -> getAdaptedResolution(resolution, marker)).toArray(IMarkerResolution[]::new);
  }

  /**
   * Adapt IssueResolution to IMarkerResolution, specifically WorkbenchMarkerResolution.
   *
   * @param resolution
   *          the issue resolution
   * @param marker
   *          the marker associated with the issue
   * @return a corresponding WorkbenchMarkerResolution
   */
  protected IMarkerResolution getAdaptedResolution(final IssueResolution resolution, final IMarker marker) {
    return new WorkbenchResolutionAdapter(resolution, marker);
  }

  /**
   * An adapter to enable the generator to return WorkbenchMarkerResolutions.
   * The main functionality is provided by findOtherMarkers which provides the information to 'group' the
   * markers. Run is extended to process the 'othermarkers'.
   */
  public class WorkbenchResolutionAdapter extends WorkbenchMarkerResolution {

    private final IssueResolution resolution;
    private final IMarker resolutionMarker;
    private final String markerCode;

    public IssueResolution getResolution() {
      return resolution;
    }

    public WorkbenchResolutionAdapter(final IssueResolution resolution, final IMarker marker) {
      super();
      this.resolution = resolution;
      this.resolutionMarker = marker;
      this.markerCode = getIssueUtil().getCode(marker);
    }

    @Override
    public String getLabel() {
      return resolution.getLabel();
    }

    /**
     * {@inheritDoc}
     * Markers are resolved in reverse order of line number.
     * This allows multiple resolutions to be applied to a single file as
     * we avoid an update 'pushing text down' and invalidating line number information.
     */
    @Override
    public void run(final IMarker[] markers, final IProgressMonitor monitor) {
      for (final IMarker marker : MarkerSorter.sortByLineNumber(Arrays.asList(markers))) {
        run(marker);
      }
    }

    /**
     * Returns the context URI for a file for a given marker.
     *
     * @param marker
     *          the marker
     * @return the markers file URI , <code>null</code> if we cannot find a file.
     */
    protected URI getMarkerResourceUri(final IMarker marker) {
      final URI uriToProblem = getIssueUtil().getUriToProblem(marker);
      return uriToProblem == null ? null : uriToProblem.trimFragment();
    }

    @Override
    public void run(final IMarker marker) {
      for (final IMarkerResolution markerResolution : getResolutions(marker)) {
        applyResolution(markerResolution);
      }
    }

    /**
     * If we have a Core resolution execute in current thread, otherwise run in UI thread.
     *
     * @param markerResolution
     *          the marker resolution to apply
     */
    protected void applyResolution(final IMarkerResolution markerResolution) {
      final IssueResolution issueResolution = ((WorkbenchResolutionAdapter) markerResolution).getResolution();
      if (issueResolution instanceof IssueResolutionWrapper) {
        issueResolution.apply();
      } else {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
          @Override
          public void run() {
            issueResolution.apply();
          }
        });
      }
    }

    @Override
    public String getDescription() {
      return resolution.getDescription();
    }

    @Override
    public Image getImage() {
      return WorkbenchMarkerResolutionGenerator.this.getImage(resolution);
    }

    @Override
    @SuppressWarnings("PMD.UseVarargs")
    public IMarker[] findOtherMarkers(final IMarker[] markers) {
      final List<IMarker> result = Lists.newArrayList();
      for (final IMarker marker : markers) {
        if (!resolutionMarker.equals(marker) && markerCode.equals(getIssueUtil().getCode(marker))) {
          XtextEditor editor = null;
          if (Display.getCurrent() != null) {
            editor = findEditor(marker.getResource());
          }
          if (editor != null) {
            // if the resource is in an open editor compare the annotation with the marker
            final IAnnotationModel annotationModel = editor.getDocumentProvider().getAnnotationModel(editor.getEditorInput());
            if (annotationModel != null && isMarkerStillValid(marker, annotationModel)) {
              result.add(marker);
            }
          } else {
            result.add(marker);
          }
        }
      }
      return result.toArray(new IMarker[result.size()]);
    }

    public IModificationContextRegistry getModificationContext() {
      return modificationContext;
    }

  }

  /**
   * RegistryProvider - used to inject the IMarkerHelpRegistry, allows this to be overridden/mocked for testing.
   */
  public static class RegistryProvider implements Provider<IMarkerHelpRegistry> {

    @Override
    public IMarkerHelpRegistry get() {
      return IDE.getMarkerHelpRegistry();
    }

  }

}
