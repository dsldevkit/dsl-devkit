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

import java.util.Collection;


/**
 * Global service that manages contributions to all languages.
 * <p>
 * This service is implemented as a global singleton, one instance for all DSLs.
 * </p>
 */
public interface ILanguageContributionService {

  /**
   * Gets all contributions for the given language.
   *
   * @param languageName
   *          the language name
   * @return the collection of contributions, may be empty, never {@code null}
   */
  Collection<ILanguageContribution> getContributions(String languageName);

}
