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
package com.avaloq.tools.ddk.xtext.builder;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IResourceDescription.Delta;

import com.google.inject.ImplementedBy;


/**
 * A post-processor, called for each resource after the builder has either removed it or is done with it in its second build phase
 * (including validation and marker updating).
 */
@ImplementedBy(DefaultResourcePostProcessor.class)
public interface IResourcePostProcessor {

  /**
   * Post-processes one given resource delta.
   * 
   * @param delta
   *          information about the changed resource. Never <code>null</code>.
   * @param resourceSet
   *          context resource set from which the updated resource may be obtained; may be {@code null} if the delta represents a resource deletion.
   * @param monitor
   *          the progress monitor to use for reporting progress to the user. It is the caller's responsibility
   *          to call done() on the given monitor. Accepts null, indicating that no progress should be
   *          reported and that the operation cannot be cancelled.
   */
  void process(Delta delta, ResourceSet resourceSet, IProgressMonitor monitor);

}

