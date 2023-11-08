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

package com.avaloq.tools.ddk.check.runtime.label;

import com.google.inject.ImplementedBy;


/**
 * Translate Check rule issue codes into human-readable labels.
 */
@ImplementedBy(CheckRuleLabelProvider.class)
public interface ICheckRuleLabelProvider {

  /**
   * Get human-readable label for a Check rule.
   *
   * @param issueCode
   *          issue code for the Check, may be {@code null}
   * @return the label, or {@code null} if the Check is not found
   */
  String getLabel(String issueCode);

}
