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

import com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter;


/**
 * An interface for checking that an actual parameter matches a formal parameter.
 * <p>
 * What it means for an actual parameter to match a formal parameter is language specific, but usually includes such things as checking type compatibility.
 * <p>
 */
public interface IParameterMatchChecker {

  /**
   * Defines the possible statuses of a match.
   * <p>
   * This enumeration defines a set of "standard" result statuses along with a catch-all "mismatch" status.
   * </p>
   */
  enum MatchStatus {
    /** The actual parameter matches the formal parameter. */
    MATCH,
    /** The actual parameter is invalid, e.g., a named actual whose name is null or the empty string. */
    INVALID_ACTUAL,
    /** The formal parameter is invalid, e.g., it is incorrectly or incompletely specified. */
    INVALID_FORMAL,
    /** A named actual parameter has the same name as a previously encountered named actual. */
    DUPLICATE_NAMED_ACTUAL,
    /** A named actual parameter matches a formal parameter that was already matched as a positional parameter. */
    NAMED_ACTUAL_MATCHES_POSITIONAL,
    /** A positional parameter (i.e., non-named) follows a named parameter. */
    POSITIONAL_AFTER_NAMED,
    /** The type of the actual parameter does not conform to that of the formal parameter. */
    TYPE_ERROR,
    /** The actual parameter was a named actual parameter and no formal parameter had a matching name. */
    NO_MATCH_FOR_NAMED_ACTUAL,
    /** The actual parameter does not match a formal parameter for any reason (generic). */
    MISMATCH
  }

  // CHECKSTYLE:ON-JavaDocMethod
  /**
   * Specifies the result of a parameter match.
   * <p>
   * A class that implements this interface may maintain more information than just the match status.
   * </p>
   *
   * @see IParameterMatchChecker#checkMatch(IActualParameter, IFormalParameter)
   */
  interface IMatchResult {

    /**
     * Gets the status of a match result.
     *
     * @return the match's status
     * @throws IllegalArgumentException
     */
    MatchStatus getStatus();

  }

  /**
   * Predefined parameter match check results for those who only require a basic implementation.
   */
  // @Format-Off
  IMatchResult MATCH                   = new BasicMatchResult(MatchStatus.MATCH);
  IMatchResult INVALID_ACTUAL          = new BasicMatchResult(MatchStatus.INVALID_ACTUAL);
  IMatchResult INVALID_FORMAL          = new BasicMatchResult(MatchStatus.INVALID_FORMAL);
  IMatchResult DUPLICATE_NAMED_ACTUAL  = new BasicMatchResult(MatchStatus.DUPLICATE_NAMED_ACTUAL);
  IMatchResult NAMED_ACTUAL_MATCHES_POSITIONAL = new BasicMatchResult(MatchStatus.NAMED_ACTUAL_MATCHES_POSITIONAL);
  IMatchResult POSITIONAL_AFTER_NAMED  = new BasicMatchResult(MatchStatus.POSITIONAL_AFTER_NAMED);
  IMatchResult TYPE_ERROR              = new BasicMatchResult(MatchStatus.TYPE_ERROR);
  IMatchResult MISMATCH                = new BasicMatchResult(MatchStatus.MISMATCH);
  IMatchResult NO_MATCH_FOR_NAMED_ACTUAL  = new BasicMatchResult(MatchStatus.NO_MATCH_FOR_NAMED_ACTUAL);
  // @Format-On

  /**
   * Checks if an actual parameter matches a formal parameter.
   * <p>
   * Note that an implementation of {@link IParameterMatchChecker.IMatchResult} may maintain additional information if this method wishes to maintain more than
   * the match status, for
   * example, additional, language-specific information in the case of {@link IParameterMatchChecker.IMatchResult.MISMATCH}. If no additional information is
   * required then an
   * implementation can use one of the predefined match results for convenience.
   * <p>
   *
   * @param actual
   *          the actual parameter to check against {@code formal}
   * @param formal
   *          the formal parameter to check against
   * @return a match result describing the results of the check
   */
  IMatchResult checkMatch(IActualParameter actual, IFormalParameter formal);

}
