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

import com.google.inject.ImplementedBy;


/**
 * Schedule full builds if necessary.
 */
@ImplementedBy(IXtextBuildTrigger.NullXtextBuildTrigger.class)
public interface IXtextBuildTrigger {

  /**
   * Schedule a full build over the whole workspace.
   */
  void scheduleFullBuild();

  /** A null implementation of a build trigger (i.e., do not rebuild). */
  class NullXtextBuildTrigger implements IXtextBuildTrigger {

    /** {@inheritDoc} */
    @Override
    public void scheduleFullBuild() {}

  }
}
