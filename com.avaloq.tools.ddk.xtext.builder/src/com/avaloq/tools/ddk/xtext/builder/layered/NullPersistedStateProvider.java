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
package com.avaloq.tools.ddk.xtext.builder.layered;

import org.eclipse.xtext.builder.builderState.PersistedStateProvider;
import org.eclipse.xtext.resource.IResourceDescription;


/**
 * The persisted state provider is superseded by the {@link IXtextTargetPlatformManager}.
 */
public class NullPersistedStateProvider implements PersistedStateProvider {

  @Override
  public Iterable<IResourceDescription> load() {
    throw new UnsupportedOperationException();
  }

}
