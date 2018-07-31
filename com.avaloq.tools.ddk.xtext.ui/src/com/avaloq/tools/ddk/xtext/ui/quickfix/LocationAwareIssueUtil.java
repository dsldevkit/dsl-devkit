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
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.xtext.ui.util.IssueUtil;

import com.avaloq.tools.ddk.xtext.ui.quickfix.MarkerIdentity.MarkerIdentityFactory;
import com.google.inject.Inject;


/**
 * Extension of IssueUtil also taking markers' and annotations' location into account.
 */
public class LocationAwareIssueUtil extends IssueUtil {

  @Inject
  private MarkerIdentityFactory markerIdentityFactory;

  /**
   * Overrides to also include issue location as matching criterion, if present. {@inheritDoc}
   *
   * @param marker
   *          the marker
   * @param annotation
   *          the annotation
   * @return true, if successful
   */
  @Override
  public boolean refersToSameIssue(final IMarker marker, final Annotation annotation) {
    return marker != null && annotation != null && markerIdentityFactory.create(marker).matches(markerIdentityFactory.create(annotation));
  }

  /**
   * Compare a MarkerIdentity against an Annotation.
   *
   * @param markerIdentity
   *          the marker identity
   * @param annotation
   *          the annotation
   * @return true, if successful
   */
  public boolean refersToSameIssue(final MarkerIdentity markerIdentity, final Annotation annotation) {
    return markerIdentity != null && annotation != null && markerIdentity.matches(markerIdentityFactory.create(annotation));
  }

  /**
   * Compare a MarkerIdentity against an IMarker.
   *
   * @param markerIdentity
   *          the marker identity
   * @param marker
   *          the marker
   * @return true, if successful
   */
  public boolean refersToSameIssue(final MarkerIdentity markerIdentity, final IMarker marker) {
    return markerIdentity != null && marker != null && markerIdentity.matches(markerIdentityFactory.create(marker));
  }
}
