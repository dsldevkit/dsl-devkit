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
package com.avaloq.tools.ddk.checkcfg.scoping;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.resource.BatchLinkingService;


/** Switches off batch linking. batch linking appears to not work for our check language. */
public class CheckCfgBatchLinkingService extends BatchLinkingService {

  @Override
  public boolean isBatchLinkable(final EReference reference) {
    return false;
  }

}
