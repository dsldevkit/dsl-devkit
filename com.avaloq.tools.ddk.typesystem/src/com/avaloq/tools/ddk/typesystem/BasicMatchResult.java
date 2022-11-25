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
package com.avaloq.tools.ddk.typesystem;

import com.avaloq.tools.ddk.typesystem.IParameterMatchChecker.IMatchResult;
import com.avaloq.tools.ddk.typesystem.IParameterMatchChecker.MatchStatus;


/**
 * An implementation of IMatchResult that has no information other than the {@link MatchStatus}.
 */
public class BasicMatchResult implements IMatchResult {

  private MatchStatus status = IParameterMatchChecker.MatchStatus.MATCH; // default is successful match

  /**
   * Creates a new instance of {@link BasicMatchResult} with default status {@link IParameterMatchChecker.MatchStatus#MATCH}.
   */
  public BasicMatchResult() {
    // see javadoc comment for content. This comment is for PMD.
  }

  /**
   * Creates a new instance of {@link BasicMatchResult} with the given status.
   */
  public BasicMatchResult(final MatchStatus status) {
    this.status = status;
  }

  public MatchStatus getStatus() {
    return status;
  }

  public void setStatus(final MatchStatus status) {
    this.status = status;
  }

}

