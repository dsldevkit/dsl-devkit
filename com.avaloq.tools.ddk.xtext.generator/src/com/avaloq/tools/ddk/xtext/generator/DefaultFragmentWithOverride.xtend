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

package com.avaloq.tools.ddk.xtext.generator

import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.xtext.generator.IXtextGeneratorFragment
import com.google.inject.Injector
import org.eclipse.xtext.xtext.generator.Issues

/**
 * Allow different fragments to be generated depending on a condition
 *
 * By default we generate the defaultFragment (or nothing if it is null)
 * if useOverride is true, we generate the overrideFragment (or nothing if it is null)
 *
 */
class DefaultFragmentWithOverride extends AbstractXtextGeneratorFragment {

  /**
   * Whether to use the override fragment. False by default
   */
  @Accessors boolean useOverride = false

  @Accessors IXtextGeneratorFragment defaultFragment

  @Accessors IXtextGeneratorFragment overrideFragment

  override generate() {
    (if (useOverride) overrideFragment else defaultFragment)?.generate()
  }

  override initialize(Injector injector) {
    super.initialize(injector)
    overrideFragment?.initialize(injector)
    defaultFragment?.initialize(injector)
  }

  override checkConfiguration(Issues issues) {
    overrideFragment.checkConfiguration(issues)
    defaultFragment.checkConfiguration(issues)
  }

}
/* Copyright (c) Avaloq Group AG */
