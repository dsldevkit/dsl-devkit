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
 * Extends the specified language.
 */
public interface ILanguageContribution {

  /**
   * Gets the full qualified name of the language supported.
   *
   * @return the language name
   */
  String getTargetLanguageName();

  /**
   * Returns the additional model inferrer for this contribution, or {@code null} if no additional inference is necessary.
   *
   * @return a model inferrer, may be {@code null}
   */
  IAdditionalModelInferrer getAdditionalModelInferrer();

  /**
   * Gets the contributions to export.
   *
   * @return the additional export
   */
  IAdditionalExport getAdditionalExport();

}
