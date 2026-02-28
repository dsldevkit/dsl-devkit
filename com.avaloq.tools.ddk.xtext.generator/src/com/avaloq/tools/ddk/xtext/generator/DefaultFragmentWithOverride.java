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

package com.avaloq.tools.ddk.xtext.generator;

import com.google.inject.Injector;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.IXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.Issues;

/**
 * Allow different fragments to be generated depending on a condition
 *
 * By default we generate the defaultFragment (or nothing if it is null)
 * if useOverride is true, we generate the overrideFragment (or nothing if it is null)
 *
 */
public class DefaultFragmentWithOverride extends AbstractXtextGeneratorFragment {

  /**
   * Whether to use the override fragment. False by default
   */
  private boolean useOverride = false;

  private IXtextGeneratorFragment defaultFragment;

  private IXtextGeneratorFragment overrideFragment;

  public boolean isUseOverride() {
    return useOverride;
  }

  public void setUseOverride(final boolean useOverride) {
    this.useOverride = useOverride;
  }

  public IXtextGeneratorFragment getDefaultFragment() {
    return defaultFragment;
  }

  public void setDefaultFragment(final IXtextGeneratorFragment defaultFragment) {
    this.defaultFragment = defaultFragment;
  }

  public IXtextGeneratorFragment getOverrideFragment() {
    return overrideFragment;
  }

  public void setOverrideFragment(final IXtextGeneratorFragment overrideFragment) {
    this.overrideFragment = overrideFragment;
  }

  @Override
  public void generate() {
    IXtextGeneratorFragment fragment = useOverride ? overrideFragment : defaultFragment;
    if (fragment != null) {
      fragment.generate();
    }
  }

  @Override
  public void initialize(final Injector injector) {
    super.initialize(injector);
    if (overrideFragment != null) {
      overrideFragment.initialize(injector);
    }
    if (defaultFragment != null) {
      defaultFragment.initialize(injector);
    }
  }

  @Override
  public void checkConfiguration(final Issues issues) {
    overrideFragment.checkConfiguration(issues);
    defaultFragment.checkConfiguration(issues);
  }

}
/* Copyright (c) Avaloq Group AG */
