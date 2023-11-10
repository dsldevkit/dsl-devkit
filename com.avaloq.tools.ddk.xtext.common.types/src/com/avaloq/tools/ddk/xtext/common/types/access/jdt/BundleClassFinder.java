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
package com.avaloq.tools.ddk.xtext.common.types.access.jdt;

import org.eclipse.xtext.common.types.access.binary.BinaryClass;
import org.eclipse.xtext.common.types.access.binary.BinaryClassFinder;
import org.osgi.framework.Bundle;


/**
 * Finds and loads classes in a bundle.
 */
public class BundleClassFinder extends BinaryClassFinder {

  private final Bundle bundle;

  /**
   * Creates a new instance of {@link BundleClassFinder}.
   *
   * @param classLoader
   *          the {@link ClassLoader} to use, must not be {@code null}
   * @param bundle
   *          the {@link Bundle}, must not be {@code null}
   */
  public BundleClassFinder(final ClassLoader classLoader, final Bundle bundle) {
    super(classLoader);
    this.bundle = bundle;
  }

  @Override
  public BinaryClass forName(final String name, final ClassLoader classLoader) throws ClassNotFoundException {
    // getClassLoader may return null for bootstrap class loader
    ClassLoader loader = bundle.loadClass(name).getClassLoader();
    if (loader != null) {
      return super.forName(name, loader);
    } else {
      return super.forName(name, classLoader);
    }
  }
}
