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

import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.inject.ImplementedBy;


/**
 * Strategy to determine whether to process another resource in the builder.
 */
@ImplementedBy(DefaultBuilderResourceLoadStrategy.class)
public interface IBuilderResourceLoadStrategy {

  /**
   * Determine whether the builder may process yet another resource.
   *
   * @param resourceSet
   *          The builder's resource set, containing all currently loaded resources.
   * @param alreadyProcessed
   *          The number of resources already processed.
   * @return true, if the builder shall continue; false if it shall start a new cluster.
   */
  boolean mayProcessAnotherResource(ResourceSet resourceSet, int alreadyProcessed);

  /**
   * Checks whether the builder has reached the minimal amount of free memory, which prevents further progress without clearing the resource set.
   *
   * @return {@code true} if the described condition is met, {@code false} otherwise
   */
  boolean isMemoryLimitReached();

}
