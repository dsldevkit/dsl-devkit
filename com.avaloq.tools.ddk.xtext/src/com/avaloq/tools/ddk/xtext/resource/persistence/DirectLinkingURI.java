/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.resource.persistence;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;


public class DirectLinkingURI extends URI {

  private final String uri;

  protected DirectLinkingURI(final String uri) {
    super(uri.hashCode());
    this.uri = uri;
  }

  @Override
  public String scheme() {
    int indexOf = uri.indexOf(':');
    if (indexOf == -1) {
      return null;
    }
    return uri.substring(0, indexOf);
  }

  @Override
  public String fileExtension() {
    int indexOf = uri.lastIndexOf('.');
    if (indexOf == -1) {
      return null;
    }
    return uri.substring(indexOf + 1);
  }

  @Override
  public String[] segments() {
    int indexOf = uri.indexOf(':');
    if (indexOf != -1) {
      return uri.substring(indexOf + 2).split("/"); //$NON-NLS-1$
    }
    return uri.split("/"); //$NON-NLS-1$
  }

  @Override
  public URI appendFragment(final String fragment) {
    return new DirectLinkingURI(uri + FRAGMENT_SEPARATOR + fragment);
  }

  @Override
  public URI trimFragment() {
    int indexOf = uri.indexOf('#');
    if (indexOf == -1) {
      return this;
    }
    return new DirectLinkingURI(uri.substring(0, indexOf));
  }

  @Override
  public String fragment() {
    int indexOf = uri.indexOf('#');
    if (indexOf == -1) {
      return null;
    }
    return uri.substring(indexOf + 1);
  }

  //
  @Override
  public boolean isRelative() {
    return scheme() == null;
  }

  @Override
  protected boolean isBase() {
    return true;
  }

  @Override
  public boolean isHierarchical() {
    return true;
  }

  @Override
  public boolean hasAuthority() {
    return false;
  }

  @Override
  public boolean hasOpaquePart() {
    return false;
  }

  @Override
  public boolean hasDevice() {
    return false;
  }

  @Override
  public boolean hasPath() {
    return false;
  }

  @Override
  protected boolean hasDeviceOrPath() {
    return false;
  }

  @Override
  public boolean hasAbsolutePath() {
    return true;
  }

  @Override
  public boolean hasRelativePath() {
    return isRelative();
  }

  @Override
  public boolean hasEmptyPath() {
    return false;
  }

  @Override
  public boolean hasQuery() {
    return false;
  }

  @Override
  public boolean hasFragment() {
    return uri.indexOf('#') != -1;
  }

  @Override
  public boolean isCurrentDocumentReference() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean isFile() {
    return false;
  }

  @Override
  public boolean isPlatform() {
    return "platform".equals(scheme()); //$NON-NLS-1$
  }

  @Override
  public boolean isPlatformResource() {
    return uri.startsWith("platform://resource"); //$NON-NLS-1$
  }

  @Override
  public boolean isPlatformPlugin() {
    return uri.startsWith("platform://plugin"); //$NON-NLS-1$
  }

  @Override
  public boolean isArchive() {
    return false;
  }

  @Override
  protected boolean segmentsEqual(final URI uri) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String opaquePart() {
    return null;
  }

  @Override
  public String authority() {
    return null;
  }

  @Override
  public String userInfo() {
    return null;
  }

  @Override
  public String host() {
    return null;
  }

  @Override
  public String port() {
    return null;
  }

  @Override
  public String device() {
    return null;
  }

  @Override
  protected String[] rawSegments() {
    return segments();
  }

  @Override
  public List<String> segmentsList() {
    return Arrays.asList(segments());
  }

  @Override
  public int segmentCount() {
    return segments().length;
  }

  @Override
  public String segment(final int i) {
    return segments()[i];
  }

  @Override
  public String lastSegment() {
    String[] array = segments();
    return array[array.length - 1];
  }

  @Override
  public String path() {
    return null;
  }

  @Override
  public String devicePath() {
    return null;
  }

  @Override
  public String query() {
    return null;
  }

  @Override
  public URI appendQuery(final String query) {
    throw new UnsupportedOperationException();
  }

  @Override
  public URI trimQuery() {
    return this;
  }

  @Override
  public URI resolve(final URI base, final boolean preserveRootParents) {
    if (uri.startsWith("../../")) {
      return new DirectLinkingURI("ctx:/src/" + uri.substring(6));
    }
    if (uri.startsWith("../")) {
      return new DirectLinkingURI("ctx:/src/AAA/" + uri.substring(3));
    }
    int lastIndexOf = base.toString().lastIndexOf("/"); //$NON-NLS-1$
    return new DirectLinkingURI(base.toString().substring(0, lastIndexOf + 1) + uri);
  }

  @Override
  public URI deresolve(final URI base, final boolean preserveRootParents, final boolean anyRelPath, final boolean shorterRelPath) {
    throw new UnsupportedOperationException();
  }

  @Override
  protected String[] collapseSegments(final boolean preserveRootParents) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return uri;
  }

  /**
   * If the hash code is <code>0</code> then most likely we've got a pending lazy {@link LazyFragmentInitializer}.
   */
  @Override
  public int hashCode() {
    return hashCode;
  }

  @Override
  public boolean equals(final Object object) {
    if (object == this) {
      return true;
    }

    if (object instanceof DirectLinkingURI directLinkingUri) {
      return directLinkingUri.uri == this.uri;
    }

    if (object instanceof URI emfURI) {
      return Objects.equals(this.uri, emfURI.toString());
    }
    return false;
  }

  @Override
  public String toFileString() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toPlatformString(final boolean decode) {
    throw new UnsupportedOperationException();
  }

  @Override
  public URI appendSegment(final String segment) {
    throw new UnsupportedOperationException();

  }

  @Override
  public URI appendSegments(final String[] segments) {
    throw new UnsupportedOperationException();
  }

  @Override
  public URI trimSegments(final int i) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasTrailingPathSeparator() {
    return false;
  }

  @Override
  public URI appendFileExtension(final String fileExtension) {
    throw new UnsupportedOperationException();

  }

  @Override
  public URI trimFileExtension() {
    throw new UnsupportedOperationException();

  }

  @Override
  public URI replacePrefix(final URI oldPrefix, final URI newPrefix) {
    throw new UnsupportedOperationException();

  }
}
