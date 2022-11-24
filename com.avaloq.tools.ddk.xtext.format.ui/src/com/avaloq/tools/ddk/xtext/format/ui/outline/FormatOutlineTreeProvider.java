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
package com.avaloq.tools.ddk.xtext.format.ui.outline;

import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;

import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.ui.outline.AbstractOutlineTreeProvider;
import com.google.common.collect.Lists;


/**
 * Customization of the default outline structure.
 */
public class FormatOutlineTreeProvider extends AbstractOutlineTreeProvider {

  private static final List<ENamedElement> ELEMENTS = Lists.newArrayList(FormatPackage.Literals.FORMAT_CONFIGURATION, FormatPackage.Literals.RULE);

  @Override
  protected List<ENamedElement> getRelevantElements() {
    return ELEMENTS;
  }

}
