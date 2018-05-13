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

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.eclipse.xtext.ui.editor.validation.XtextAnnotation;
import org.eclipse.xtext.validation.Issue;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * Helper class to store IMarker attributes. The attributes are compared against another marker or annotation to determine if they're equivalent.
 * This is useful when comparing IMarkers of pre-checkout sources vs. post-checkout sources.
 * Direct instantiation of MarkerIdentity objects is not supported - use MarkerIdentityFactory instead!
 */
public final class MarkerIdentity {

  /**
   * A factory for creating MarkerIdentity objects.
   */
  public static class MarkerIdentityFactory {
    // corresponds to default attribute value used in org.eclipse.ui.texteditor.MarkerUtilities
    private static final int ATTRIBUTE_MISSING = -1;
    @Inject
    private Provider<MarkerIdentity> provider;
    @Inject
    private LocationAwareIssueUtil issueUtil;

    /**
     * Instantiates a new MarkerIdentity.
     *
     * @param marker
     *          the marker
     * @return MarkerIdentity - this
     */
    public MarkerIdentity create(final IMarker marker) {
      MarkerIdentity result = provider.get();
      result.start = MarkerUtilities.getCharStart(marker);
      result.end = MarkerUtilities.getCharEnd(marker);
      result.problemCode = issueUtil.getCode(marker);
      result.problemURI = issueUtil.getUriToProblem(marker);
      result.resourceURI = result.problemURI == null ? null : result.problemURI.trimFragment();
      result.message = MarkerUtilities.getMessage(marker);
      return result;
    }

    /**
     * Instantiates a new MarkerIdentity.
     *
     * @param annotation
     *          the Annotation
     * @return MarkerIdentity - this
     */
    public MarkerIdentity create(final Annotation annotation) {
      MarkerIdentity result = provider.get();
      if (annotation instanceof XtextAnnotation) {
        Issue issue = ((XtextAnnotation) annotation).getIssue();
        result.start = issue.getOffset();
        result.end = result.start == ATTRIBUTE_MISSING ? ATTRIBUTE_MISSING : result.start + issue.getLength();
        result.message = issue.getMessage();
      } else if (annotation instanceof org.eclipse.ui.texteditor.MarkerAnnotation) {
        result.start = MarkerUtilities.getCharStart(((org.eclipse.ui.texteditor.MarkerAnnotation) annotation).getMarker());
        result.end = MarkerUtilities.getCharEnd(((org.eclipse.ui.texteditor.MarkerAnnotation) annotation).getMarker());
        result.message = MarkerUtilities.getMessage(((org.eclipse.ui.texteditor.MarkerAnnotation) annotation).getMarker());
      } else {
        result.end = ATTRIBUTE_MISSING;
        result.start = ATTRIBUTE_MISSING;
        result.message = null; // NOPMD
      }
      result.problemCode = issueUtil.getCode(annotation);
      result.problemURI = issueUtil.getUriToProblem(annotation);
      result.resourceURI = result.problemURI == null ? null : result.problemURI.trimFragment();
      return result;
    }
  }

  private Integer start;
  private Integer end;
  private String problemCode;
  private String message;
  private URI problemURI;
  private URI resourceURI;

  public Integer getStart() {
    return start;
  }

  public Integer getEnd() {
    return end;
  }

  public String getProblemCode() {
    return problemCode;
  }

  public String getMessage() {
    return message;
  }

  public URI getProblemURI() {
    return problemURI;
  }

  public URI getResourceURI() {
    return resourceURI;
  }

  /**
   * All attributes (URI, offset, length, message, code, ...) matches/same as given IMarker or Annotation.
   *
   * @param other
   *          the other
   * @return true if all relevant attributes are equal
   */
  public boolean matches(final MarkerIdentity other) {
    // CHECKSTYLE:OFF BooleanComplexity
    return Objects.equal(problemCode, other.problemCode)//
        && Objects.equal(start, other.start)//
        && Objects.equal(end, other.end)//
        && Objects.equal(message, other.message)//
        && Objects.equal(resourceURI, other.resourceURI)//
        && Objects.equal(problemURI, other.problemURI);
    // CHECKSTYLE:ON
  }
}
