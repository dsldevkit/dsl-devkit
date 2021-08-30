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
package com.avaloq.tools.ddk.check.generator.quickfix;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.ui.generator.quickfix.QuickfixProviderFragment;


/**
 * Fragment that generates a default quickfix provider implementation extending
 * the {@link com.avaloq.tools.ddk.check.runtime.ui.quickfix.DefaultCheckQuickfixProvider default Check Quickfix provider}.
 * <p>
 * Note that the {@link QuickfixProviderFragment superclass} creates the necessary IssueResolutionProvider binding.
 */
@SuppressWarnings("deprecation")
public class CheckQuickfixProviderFragment extends QuickfixProviderFragment {

  /**
   * Gets the required dependency for the {@link com.avaloq.tools.ddk.check.runtime.ui.quickfix.DefaultCheckQuickfixProvider
   * Check quickfix provider}.
   * <p>
   * {@inheritDoc}
   */
  @Override
  public String[] getRequiredBundlesUi(final Grammar grammar) {
    return new String[] {"com.avaloq.tools.ddk.check.runtime.ui"}; //$NON-NLS-1$
  }

}
