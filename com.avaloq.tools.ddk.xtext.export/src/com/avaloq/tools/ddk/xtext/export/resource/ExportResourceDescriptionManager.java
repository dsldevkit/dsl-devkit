/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.export.resource;

import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.common.types.access.impl.URIHelperConstants;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionManager;

import com.google.inject.Singleton;


/**
 * Resource description manager for export models which ignores deltas of Java types.
 * <p>
 * The inferred JVM model of an export model references the Java interfaces of the exported EClasses, so the Xbase
 * description manager records them as imported names and outgoing references. The builder then re-processes every export
 * model whenever JDT reports a structural change to one of those interfaces, although the generated code only depends
 * on the EMF model (the ecore and genmodel), whose own changes still affect export models.
 * </p>
 */
@Singleton
public class ExportResourceDescriptionManager extends XbaseResourceDescriptionManager {

  @Override
  public boolean isAffected(final Delta delta, final IResourceDescription candidate) {
    return !isJavaTypeDelta(delta) && super.isAffected(delta, candidate);
  }

  @Override
  public boolean isAffected(final Collection<Delta> deltas, final IResourceDescription candidate, final IResourceDescriptions context) {
    final List<Delta> nonJavaDeltas = deltas.stream().filter(delta -> !isJavaTypeDelta(delta)).toList();
    return !nonJavaDeltas.isEmpty() && super.isAffected(nonJavaDeltas, candidate, context);
  }

  /**
   * Returns whether the given delta describes a Java type, as reported by JDT through the {@code java:/} URI scheme.
   *
   * @param delta
   *          the delta, must not be {@code null}
   * @return {@code true} if the delta is about a Java type
   */
  private static boolean isJavaTypeDelta(final Delta delta) {
    return URIHelperConstants.PROTOCOL.equals(delta.getUri().scheme());
  }
}
