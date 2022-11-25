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
package com.avaloq.tools.ddk.xtext.format.ui.labeling;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;

import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.format.format.WildcardRule;
import com.google.inject.Inject;


/**
 * Provides labels for a EObjects.
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class FormatLabelProvider extends XbaseLabelProvider {

  /**
   * Instantiates a new format label provider.
   *
   * @param delegate
   *          the delegate
   */
  @Inject
  public FormatLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }

  /**
   * Text.
   *
   * @param element
   *          the element
   * @return the string
   */
  public String text(final WildcardRule element) {
    return "*";
  }

  /**
   * Text.
   *
   * @param element
   *          the element
   * @return the string
   */
  public String text(final GrammarRule element) {
    return element.getTargetRule().getName();
  }
}
