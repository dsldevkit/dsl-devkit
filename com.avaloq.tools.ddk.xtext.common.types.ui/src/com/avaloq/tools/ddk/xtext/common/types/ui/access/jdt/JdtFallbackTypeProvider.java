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
package com.avaloq.tools.ddk.xtext.common.types.ui.access.jdt;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.IJdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.TypeURIHelper;


/**
 * A JDT type provider capable of dealing with non-Java projects.
 */
public class JdtFallbackTypeProvider implements IJdtTypeProvider, Resource.Factory {

  private final IJdtTypeProvider first;
  private final IJvmTypeProvider second;

  /**
   * Creates a new instance of {@link JdtFallbackTypeProvider}.
   *
   * @param first
   *          the first {@link IJdtTypeProvider}, may be {@code null}
   * @param second
   *          the second {@link IJvmTypeProvider}, must not be {@code null}
   */
  public JdtFallbackTypeProvider(final IJdtTypeProvider first, final IJvmTypeProvider second) {
    this.first = first;
    this.second = second;
  }

  /** {@inheritDoc} */
  @Override
  public JvmType findTypeByName(final String name) {
    return findTypeByName(name, true);
  }

  /** {@inheritDoc} */
  @Override
  public JvmType findTypeByName(final String name, final boolean binaryNestedTypeDelimiter) {
    JvmType result = null;
    try {
      if (first != null) {
        result = first.findTypeByName(name, binaryNestedTypeDelimiter);
      }
      return result != null ? result : second.findTypeByName(name, binaryNestedTypeDelimiter);
    } catch (IllegalStateException ex) {
      throw new WrappedException("Failed to resolve name: " + name, ex); //$NON-NLS-1$
    }
  }

  /** {@inheritDoc} */
  @Override
  public ResourceSet getResourceSet() {
    if (first != null) {
      return first.getResourceSet();
    }
    return second.getResourceSet();
  }

  /** {@inheritDoc} */
  @Override
  public TypeURIHelper getTypeUriHelper() {
    if (first != null) {
      return first.getTypeUriHelper();
    }
    return new TypeURIHelper();
  }

  /** {@inheritDoc} */
  @Override
  public IJavaProject getJavaProject() {
    if (first != null) {
      return first.getJavaProject();
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Resource createResource(final URI uri) {
    if (first instanceof Resource.Factory) {
      return ((Resource.Factory) first).createResource(uri);
    }
    if (second instanceof Resource.Factory) {
      return ((Resource.Factory) second).createResource(uri);
    }
    return null;
  }
}

