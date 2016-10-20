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
package com.avaloq.tools.ddk.xtext.build;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.CompilerPhases;


/**
 * Global utility class for information about build phases.
 */
public final class DdkBuildPhases {

  /**
   * We can't use injection because we need to use it inside DdkResourceDescription, which is not created through injection.
   * This works because we exploit the fact that CompilerPhases is just a utility class that checks for the presence of an
   * adapter of a particular type on the resource set; CompilerPhases itself is stateless.
   */
  private static CompilerPhases compilerPhases = new CompilerPhases();

  /**
   * Announces whether the builder is currently indexing on this resource set.
   *
   * @param resourceSet
   *          the builder uses; must not be {@code null}
   * @param isIndexing
   *          whether the builder is in the first (indexing) or second (validation & generation) build phase.
   */
  public static void setIndexing(final ResourceSet resourceSet, final boolean isIndexing) {
    compilerPhases.setIndexing(resourceSet, isIndexing);
  }

  /**
   * Determines whether the resource set of the given {@link Notifier} is one on whioch the builder is currently indexing.
   *
   * @param context
   *          determining the resource set. May be {@code null}, in which case {@code false} is returned.
   * @return {@code true} if the builder is currently indexing on this resource set.
   */
  public static boolean isIndexing(final Notifier context) {
    return compilerPhases.isIndexing(context);
  }

  /**
   * Inhibit instantiation.
   */
  private DdkBuildPhases() {}

}
