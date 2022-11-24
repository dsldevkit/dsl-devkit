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
package com.avaloq.tools.ddk.check.runtime.configuration;

import java.net.URL;

import org.osgi.framework.Bundle;


/**
 * Describes a mapping of check catalogs registered with the check plugin extension to
 * model location objects storing derived and cached data relevant to scoping and type providers.
 */
public class BundleAwareModelLocation extends AbstractModelLocation implements IModelLocation {

  private final Bundle bundle;

  public BundleAwareModelLocation(final URL catalogUrl, final Bundle bundle) {
    super(catalogUrl);
    this.bundle = bundle;
  }

  public Bundle getBundle() {
    return bundle;
  }

}
