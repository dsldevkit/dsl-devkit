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

package com.avaloq.tools.ddk.xtext.contribution;

import com.avaloq.tools.ddk.xtext.modelinference.IAdditionalModelInferrer;
import com.avaloq.tools.ddk.xtext.resource.IAdditionalExport;


/**
 * Default implementation providing no contribution.
 */
public abstract class AbstractLanguageContribution implements ILanguageContribution {

  @Override
  public IAdditionalModelInferrer getAdditionalModelInferrer() {
    return null;
  }

  @Override
  public IAdditionalExport getAdditionalExport() {
    return null;
  }

}
