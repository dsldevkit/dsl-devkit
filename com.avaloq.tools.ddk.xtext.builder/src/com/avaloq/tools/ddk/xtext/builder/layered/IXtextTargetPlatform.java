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
package com.avaloq.tools.ddk.xtext.builder.layered;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import com.avaloq.tools.ddk.xtext.builder.IDerivedObjectAssociationsStore;
import com.avaloq.tools.ddk.xtext.extensions.IResourceDescriptionsData;
import com.google.inject.ImplementedBy;


/**
 * Abstraction for an Xtext target platform. A platform is an abstraction for an Xtext index plus additional data (we'll get to that shortly).
 * Platforms and their life cycle are managed by the IXtextTargetPlatformManager. The manager may decide to switch platforms while the eclipse
 * is running. In essence, this enables working with different Xtext indices depending in different contexts, even though standard Xtext has only
 * one single global index. For instance, using platforms, we can use different indices in different projects by switching the platform depending
 * on the "current" project. We can also work with resources that are not physically present in the workspace. For instance, if we have a large
 * source repository, one would have to check out the whole repository to work comfortably with standard Xtext. With platforms, we can pre-compute
 * an index over the repository, and use that as part of a platform. We can then check out only the few resources we really want to touch, and use
 * a combination of a local workspace index layered on top of this pre-computed index. Even this layering can be hidden completely inside a platform.
 * <p>
 * When using platforms in this way, there may be Xtext issues for resources present only in this pre-computed index. A platform therefore not only encapsulates
 * a (possibly layered) Xtext index, but also some issue storage for the indexed resources. When switching platforms, it may become necessary to be able to
 * determine whether the current platform is u to date and needs to be switched at all. For this purpose, a platform additionally provides storage for arbitrary
 * metadata in the form of key-value pairs (both of type String).
 * <p>
 * In summary, a platform:
 * <ul>
 * <li>Provides a (possibly layered or otherwise structured) Xtext index: {@link #getIResourceDescriptionsData()}
 * <li>Provides some issue storage: {@link #getIssueStore()}
 * <li>Stores some metadata: {@link #getMetadata(Iterable)} and {@link #setMetadata(Map)}
 * </ul>
 * <p>
 * A platform has a life cycle. Initially, it is created. In this state, the only operations allowed is {@link #getMetadata(Iterable)}. Before either the
 * encapsulated index or the issue store are accessed, the platform must be opened through {@link #open(boolean, IProgressMonitor)}. Once open, all operations
 * are allowed. Once it is no longer in use, a platform must be closed through {@link #close(IProgressMonitor)}.
 */
@ImplementedBy(DefaultXtextTargetPlatform.class)
public interface IXtextTargetPlatform {

  /**
   * Get the platform's metadata. May be called before {@link #open(boolean, IProgressMonitor)}.
   *
   * @param keys
   *          if non-{@code null} retrieve only the metadata for the given keys. Otherwise, all metadata is returned.
   * @param monitor
   *          to report progress
   * @return the metadata. May return {@code null} if the platform doesn't store metadata.
   */
  Map<String, String> getMetadata(Collection<String> keys, final IProgressMonitor monitor);

  /**
   * A new platform must be opened before being used.
   *
   * @param clean
   *          <code>true</code>, if the platform should load "clean". The precise semantics of "clean" is left to the platform. The platform manager sets this
   *          to true if it determines that the platform is not up to date.
   * @param monitor
   *          to report progress
   * @throws IOException
   *           if the platform cannot be opened.
   */
  void open(final boolean clean, final IProgressMonitor monitor) throws IOException;

  /**
   * Store the given metadata in the platform.
   *
   * @param options
   *          the new metadata.
   */
  void setMetadata(Map<String, String> options);

  /**
   * Return an IResourceDescriptionsData wrapping the index data.
   *
   * @return the IResourceDescriptionsData
   */
  IResourceDescriptionsData getIResourceDescriptionsData();

  /**
   * Besides resource descriptions a platform may contain issues. For symmetry reasons,
   * these are managed through an {@link IIssueStore}, just as resource descriptions are
   * managed through an {@link IResourceDescriptionsData}.
   * If a platform does not manage issues, it shall return {@code null}.
   *
   * @return the platform's issue store, or {@code null} if none.
   */
  IIssueStore getIssueStore();

  /**
   * Gets the association store for resources in Xtext index.
   * <p>
   * If a platform does not manage the associations, it shall return {@code null}.
   * </p>
   *
   * @return the association store, or {@code null} if not supported
   */
  IDerivedObjectAssociationsStore getAssociationsStore();

  /**
   * Platforms are supposed to be closed when no longer in use. At any time, there must be only one open platform.
   * The platform manager should ensure that there are never two platforms open at the same time.
   *
   * @param monitor
   *          to report progress
   */
  void close(final IProgressMonitor monitor);
}
