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
package com.avaloq.tools.ddk.xtext.builder;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.BuildType;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.builder.impl.XtextBuilder;
import org.eclipse.xtext.resource.IResourceDescription.Delta;

import com.google.common.collect.Lists;


/**
 * A simple implementation of {@link IBuildContext} with the possibility to add {@link Delta}s.
 */
public class BuildContext implements IBuildContext {
  private final XtextBuilder builder;
  private final ResourceSet resourceSet;
  private final List<Delta> deltas;
  private final BuildType type;
  private final IProject builtProject;
  private boolean rebuildRequired;

  /**
   * Creates a new instance of {@link BuildContext}.
   * 
   * @param builtProject
   *          the {@link IProject} being built, must not be {@code null}
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   * @param type
   *          the {@link BuildType}, must not be {@code null}
   */
  public BuildContext(final IProject builtProject, final ResourceSet resourceSet, final BuildType type) {
    this(null, builtProject, resourceSet, Lists.<Delta> newArrayList(), type);
  }

  /**
   * Creates a new instance of {@link BuildContext}.
   * 
   * @param builder
   *          the {@link XtextBuilder}, must not be {@code null}
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   * @param deltas
   *          the list of {@link Delta}, must not be {@code null}
   * @param type
   *          the {@link BuildType}, must not be {@code null}
   */
  public BuildContext(final XtextBuilder builder, final ResourceSet resourceSet, final List<Delta> deltas, final BuildType type) {
    this(builder, null, resourceSet, deltas, type);
  }

  /**
   * Creates a new instance of {@link BuildContext}.
   * 
   * @param builder
   *          the {@link XtextBuilder}, may be {@code null}
   * @param builtProject
   *          the {@link IProject} being built, may be {@code null}
   * @param resourceSet
   *          the {@link ResourceSet}, must not be {@code null}
   * @param deltas
   *          the list of {@link Delta}, must not be {@code null}
   * @param type
   *          the {@link BuildType}, must not be {@code null}
   */
  protected BuildContext(final XtextBuilder builder, final IProject builtProject, final ResourceSet resourceSet, final List<Delta> deltas, final BuildType type) {
    this.type = type;
    this.builder = builder;
    this.resourceSet = resourceSet;
    this.deltas = deltas;
    this.builtProject = builtProject;
  }

  @Override
  public IProject getBuiltProject() {
    if (builder != null) {
      return builder.getProject();
    }
    return builtProject;
  }

  @Override
  public List<Delta> getDeltas() {
    return deltas;
  }

  /**
   * Adds the given {@link Delta} to the list of {@link Delta}s.
   * 
   * @param delta
   *          the {@link Delta} to add
   */
  public void addDelta(final Delta delta) {
    deltas.add(delta);
  }

  @Override
  public ResourceSet getResourceSet() {
    return resourceSet;
  }

  @Override
  public void needRebuild() {
    rebuildRequired = true;
    if (builder != null) {
      builder.needRebuild();
    }
  }

  /**
   * Returns {@code true} if a rebuild is required.
   * 
   * @return {@code true} if a rebuild is required, {@code false} otherwise
   */
  public boolean isRebuildRequired() {
    return rebuildRequired;
  }

  @Override
  public BuildType getBuildType() {
    return type;
  }

  @Override
  public boolean isSourceLevelURI(final URI uri) {
    return true;
  }
}
