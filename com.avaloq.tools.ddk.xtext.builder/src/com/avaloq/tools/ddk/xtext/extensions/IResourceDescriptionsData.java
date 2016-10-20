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
package com.avaloq.tools.ddk.xtext.extensions;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;

import com.avaloq.tools.ddk.xtext.resource.extensions.IResourceDescriptions2;


/**
 * Marker interface; should be implemented by {@link org.eclipse.xtext.builder.builderState.ResourceDescriptionsData ResourceDescriptionsData}.
 */
public interface IResourceDescriptionsData extends IResourceDescriptions2 {

  /**
   * Add a new resource description.
   *
   * @param uri
   *          of the resource
   * @param newDescription
   *          the description
   */
  void addDescription(URI uri, IResourceDescription newDescription);

  /**
   * Remove a resource description.
   *
   * @param uri
   *          of the resource whose description shall be removed.
   */
  void removeDescription(URI uri);

  /**
   * Create a copy of this index.
   *
   * @return the copied index.
   */
  ResourceDescriptionsData copy();

  /**
   * Include all the given resource descriptions into this index, i.e. into the internal resource-description data representation by the one supplied as
   * {@link IResourceDescription}s, usually from an externally serialized
   * representation.
   *
   * @param descriptions
   *          The {@link IResourceDescription}s to add.
   */
  void importData(Iterable<IResourceDescription> descriptions);

  /**
   * Remove all resource descriptions from this index.
   */
  void clear();

  /**
   * Indicate that the index will be changed. Subsequent changes are guaranteed to be permanent only after the next call to {@link commitChanges}.
   */
  void beginChanges();

  /**
   * Flushes the currently buffered changes without committing them to make them available to queries within this transaction. The implementation is also
   * permitted to make the buffered changes available to queries <em>before</em> this method is called.
   */
  void flushChanges();

  /**
   * Make all pending changes in this index permanent.
   */
  void commitChanges();

  /**
   * Rollback all pending changes.
   */
  void rollbackChanges();
}
