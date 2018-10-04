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
package com.avaloq.tools.ddk.typesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.typesystem.IParameterMatchChecker.IMatchResult;
import com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter;


/**
 * A class for matching an actual parameter list with a formal parameter list.
 */
public class ParameterListMatcher {

  /**
   * A parameter match is an association of an actual parameter, a formal parameter and the match result
   * when the actual is checked against the formal. The actual or formal parameter may be {@code null} if
   * they were matched by position and there was a {@code null} in that position for either one or both.
   */
  public class ParameterMatch {

    private final IActualParameter actual;
    private final IFormalParameter formal;
    private final IMatchResult matchResult;

    ParameterMatch(final IActualParameter actual, final IFormalParameter formal, final IMatchResult result) {
      this.actual = actual;
      this.formal = formal;
      this.matchResult = result != null ? result : IParameterMatchChecker.MISMATCH;
    }

    /**
     * Gets the match's actual parameter.
     *
     * @return the match's actual parameter, may be {@code null}
     */
    public IActualParameter getActualParameter() {
      return actual;
    }

    /**
     * Gets the match's formal parameter.
     *
     * @return the match's formal parameter, may be {@code null}
     */
    public IFormalParameter getFormalParameter() {
      return formal;
    }

    /**
     * Gets the match's result.
     *
     * @return the match's result, never {@code null}
     */
    public IParameterMatchChecker.IMatchResult getMatchResult() {
      return matchResult;
    }

  }

  /**
   * A parameter-list match status indicate success or failure when matching actual and formal parameter lists.
   * The order of the literals for errors gives the severity of the error, with the first being the least severe.
   */
  public enum ParameterListMatchStatus {
    /** All actual parameters successfully matched with formal parameters. */
    MATCH_SUCCESSFUL,
    /** The actual parameter list was missing an actual parameter for a mandatory formal parameter. */
    MISSING_MANDATORY,
    /** One or more actual parameters did not match a formal parameter. */
    PARAMETER_MISMATCH,
    /** The actual parameter list had more actual parameters than there are formal parameters in the formal parameter list. */
    TOO_MANY_ACTUALS,
    /** An actual parameter is invalid, e.g., is null or has a name that is null or the empty string. */
    INVALID_ACTUAL,
    /** A formal parameter is null. */
    INVALID_FORMAL,
    /** A formal parameter is a duplicate named parameter. */
    DUPLICATE_PARAMETER,
    /** A formal parameter has a name that is null or the empty string. */
    INVALID_FORMAL_NAME,
    /** A formal parameter is an unnamed parameter that occurred after a named parameter. */
    UNNAMED_FORMAL_AFTER_NAMED
  }

  /**
   * A parameter-list match result gives detailed information about the result of matching an actual and formal parameter list.
   */
  public class ParameterListMatchResult {

    /** The status of the resulting match. */
    private ParameterListMatchStatus status = ParameterListMatchStatus.MATCH_SUCCESSFUL;

    /** The list of resulting parameter matches. */
    private final List<ParameterMatch> parameterMatches = new ArrayList<ParameterMatch>(5);

    /** Unmatched mandatory formal parameters left over after all actual parameters have been matched. */
    private List<IFormalParameter> unmatchedMandatoryFormals;

    /** Named formal parameters that are duplicates of previously consumed named formal parameters. */
    private List<INamedFormalParameter> duplicateNamedFormals;

    /** Unnamed formal parameters that occur after named formal parameters. */
    private List<IFormalParameter> unnamedFormalsAfterNamedFormal;

    // Note - in addition to the match results needed for the caller above, we also maintain state that
    // is used during the matching process.

    /** The parameter match checker supplied by a client. */
    private IParameterMatchChecker matchChecker;

    /** Formal parameters that have been consumed, either by position or name. */
    private Set<IFormalParameter> consumedFormals;

    /**
     * Named formal parameters that have been consumed, i.e., matched by name; the formal parameters in this map are a subset of {@link #consumedFormals}.
     */
    private Map<String, INamedFormalParameter> consumedNamedFormals;

    /**
     * Sets the status of the parameter list match.
     * <p>
     * Only the most severe status is reported.
     * </p>
     *
     * @param listMatchStatus
     *          the parameter list match status to set, must not be {@code null}
     */
    protected void setStatus(final ParameterListMatchStatus listMatchStatus) {
      if (listMatchStatus.ordinal() > this.status.ordinal()) {
        this.status = listMatchStatus;
      }
    }

    /**
     * Gets the status of the match.
     * <p>
     * In the case where there was more than one error during the match, only the most severe error is reported. Errors for individual matches can be examined
     * via {@link #getMatches}.
     * </p>
     *
     * @return the status of the match
     */
    public ParameterListMatchStatus getStatus() {
      return status;
    }

    /**
     * Gets the list of matches between actual and formal parameters, along with their match result.
     *
     * @return a list containing the matching of actual to formal parameters and the result of each match, never {@code null}
     */
    public List<ParameterMatch> getMatches() {
      return Collections.unmodifiableList(getParameterMatches());
    }

    /**
     * Gets the number of parameters that matched successfully.
     *
     * @return the number of parameters that matched successfully
     */
    public int getNumberMatched() {
      int result = 0;
      for (ParameterMatch match : getParameterMatches()) {
        if (match.getMatchResult().getStatus() == IParameterMatchChecker.MatchStatus.MATCH) {
          result += 1;
        }
      }
      return result;
    }

    /**
     * Adds a parameter match to the list of parameter matches.
     * <p>
     * If the status of the parameter match is not {@link IParameterMatchChecker.MatchStatus#MATCH}, i.e., the match is not successful, then the status of
     * {@code this} is set to {@link ParameterListMatchStatus#PARAMETER_MISMATCH}.
     *
     * @see #setStatus
     * @param match
     *          the parameter match to add, must not be {@code null}
     */
    protected void addParameterMatch(final ParameterMatch match) {
      getParameterMatches().add(match);
      if (match.getMatchResult().getStatus() != IParameterMatchChecker.MatchStatus.MATCH) {
        setStatus(ParameterListMatchStatus.PARAMETER_MISMATCH);
      }
    }

    protected List<ParameterMatch> getParameterMatches() {
      return parameterMatches;
    }

    protected IParameterMatchChecker getMatchChecker() {
      return matchChecker;
    }

    protected void setMatchChecker(final IParameterMatchChecker matchChecker) {
      this.matchChecker = matchChecker;
    }

    /**
     * Updates the match result with both a status and a parameter match.
     *
     * @param listMatchStatus
     *          the parameter list match status to set
     * @param match
     *          the parameter match to add, must not be {@code null}
     * @see #addParameterMatch(ParameterMatch)
     * @see #setStatus(ParameterListMatchStatus)
     */
    protected void updateResult(final ParameterListMatchStatus listMatchStatus, final ParameterMatch match) {
      addParameterMatch(match);
      setStatus(listMatchStatus);
    }

    /**
     * Gets the set of formal parameters that have been consumed so far during the matching process.
     *
     * @return the set of formal parameters that have been consumed so far during the matching process, never {@code null}
     */
    protected Set<IFormalParameter> getConsumedFormals() {
      if (consumedFormals == null) {
        consumedFormals = new HashSet<IFormalParameter>();
      }
      return consumedFormals;
    }

    /**
     * Adds a formal parameter to the set of parameters consumed so far during the matching process and updates status.
     * <p>
     * Checks if {@code formal} is a duplicate named formal and, if so, adds it to the list of duplicate formals.<br>
     * The result status is updated if {@code formal} is an invalid formal parameter.
     * </p>
     *
     * @param formal
     *          the formal parameter to add, may be {@code null}
     */
    protected void addConsumedFormal(final IFormalParameter formal) {
      if (formal != null) {
        getConsumedFormals().add(formal);
        if (formal instanceof INamedFormalParameter) {
          INamedFormalParameter namedFormal = (INamedFormalParameter) formal;
          String name = namedFormal.getName();
          if (!Strings.isEmpty(name)) {
            if (getConsumedNamedFormals().containsKey(name) && !formal.isMulti()) {
              addDuplicateNamedFormal(namedFormal);
              setStatus(ParameterListMatchStatus.DUPLICATE_PARAMETER);
              return;
            } else {
              getConsumedNamedFormals().put(name, namedFormal);
              return;
            }
          } else {
            setStatus(ParameterListMatchStatus.INVALID_FORMAL_NAME);
            return;
          }
        } else {
          return; // is an unnamed formal
        }
      }
      setStatus(ParameterListMatchStatus.INVALID_FORMAL); // Cannot match
    }

    /**
     * Gets the set of parameters consumed by name so far during the matching process.
     *
     * @return the set of parameters consumed by name so far during the matching process, never {@code null}
     */
    protected Map<String, INamedFormalParameter> getConsumedNamedFormals() {
      if (consumedNamedFormals == null) {
        consumedNamedFormals = new HashMap<String, INamedFormalParameter>();
      }
      return consumedNamedFormals;
    }

    /**
     * Adds a formal parameter to the set of unmatched formal parameters.
     *
     * @param formal
     *          the formal parameter to add, must not be {@code null}
     */
    protected void addUnmatchedMandatoryFormal(final IFormalParameter formal) {
      if (unmatchedMandatoryFormals == null) {
        unmatchedMandatoryFormals = new LinkedList<IFormalParameter>();
      }
      if (formal.isMandatory()) {
        unmatchedMandatoryFormals.add(formal);
      }
    }

    /**
     * Gets the (possibly empty) list of unmatched, mandatory formal parameters.
     * <p>
     * The order of the parameters in the result is the order in which they occur in the formal parameter list.
     * </p>
     *
     * @return the list of unmatched formal parameters, never {@code null}
     */
    public List<IFormalParameter> getUnmatchedMandatoryFormalParameters() {
      if (unmatchedMandatoryFormals != null) {
        return Collections.unmodifiableList(unmatchedMandatoryFormals);
      } else {
        return Collections.emptyList();
      }
    }

    /**
     * Add named formal parameter to the list of duplicate named formal parameters.
     *
     * @param formal
     *          the named formal parameter to add
     */
    protected void addDuplicateNamedFormal(final INamedFormalParameter formal) {
      if (duplicateNamedFormals == null) {
        duplicateNamedFormals = new ArrayList<INamedFormalParameter>(1);
      }
      duplicateNamedFormals.add(formal);
    }

    /**
     * Gets the list of duplicate named formal parameters, if any.
     *
     * @return a list containing all named formal parameters having the same name as a preceding named formal parameter.
     */
    public List<INamedFormalParameter> getDuplicateNamedFormals() {
      if (duplicateNamedFormals != null) {
        return Collections.unmodifiableList(duplicateNamedFormals);
      } else {
        return Collections.emptyList();
      }
    }

    /**
     * Adds a formal parameter to the list of unnamed formals that occurred after a named parameter and update status.
     * <p>
     * Updates status with {@link ParameterListMatchStatus#INVALID_FORMAL}; Note - ignores {@code formal} if it is a named formal parameter.
     * </p>
     *
     * @param formal
     *          the formal parameter to add
     */
    protected void addUnnamedFormalAfterNamed(final IFormalParameter formal) {
      if (!(formal instanceof INamedFormalParameter)) {
        if (unnamedFormalsAfterNamedFormal == null) {
          unnamedFormalsAfterNamedFormal = new ArrayList<IFormalParameter>(1);
        }
        unnamedFormalsAfterNamedFormal.add(formal);
        setStatus(ParameterListMatchStatus.UNNAMED_FORMAL_AFTER_NAMED);
      }
    }

    /**
     * Gets the (possibly empty) list of unnamed formal parameters that occurred after a named formal parameter.
     *
     * @return the list of unnamed formal parameters that occurred after a named formal parameter, never {@code null}
     */
    public List<IFormalParameter> getUnnamedFormalsAfterNamed() {
      if (unnamedFormalsAfterNamedFormal != null) {
        return Collections.unmodifiableList(unnamedFormalsAfterNamedFormal);
      } else {
        return Collections.emptyList();
      }

    }

  }

  /**
   * Convenience method which calls {@link #match(Iterable, Iterable, IParameterMatchChecker, boolean, boolean)} with the last argument
   * ({@code forceMatchPositional}) as {@code false}.
   *
   * @param actualParameters
   *          the actual parameters to match, must not be {@code null}
   * @param formalParameters
   *          the formal parameters to match against, must not be {@code null}. Must also be idempotent, that is, if you traverse this iterator more than once
   *          then it must yield exactly the same elements each time.
   * @param parameterMatchChecker
   *          a parameter match checker to perform additional checks between an actual and formal parameter that have already matched
   *          by position or name, must not be {@code null}
   * @param caseSensitiveNames
   *          true if actual and formal parameter name matching should be case sensitive
   * @return a parameter list match result containing the details of the match, never {@code null}
   * @see #match(Iterable, Iterable, IParameterMatchChecker, boolean, boolean)
   */
  public ParameterListMatchResult match(final Iterable<? extends IActualParameter> actualParameters, final Iterable<? extends IFormalParameter> formalParameters, final IParameterMatchChecker parameterMatchChecker, final boolean caseSensitiveNames) {
    return match(actualParameters, formalParameters, parameterMatchChecker, caseSensitiveNames, false);
  }

  /**
   * Matches an actual parameter list against a formal parameter list.
   * <p>
   * This method takes care of matching the positional and named actual parameters in {@code actualParameters} with the formal parameters in
   * {@code formalParameters}. For each actual match it calls the {@code matchChecker} to perform any additional checks on the matched actual and formal
   * parameter. An "actual match" means that both the actual parameter and the formal parameter are not null. Furthermore, if the actual parameter is a named
   * actual parameter then the matched formal parameter's name is the same as the matched actual parameter's name. Matching the names of named actual and formal
   * parameters is case sensitive or case insensitive as specified by {@code caseSensitiveNames}.
   * <p>
   * In the returned result, if {@link ParameterListMatchResult#getStatus} is {@link ParameterListMatchStatus#TOO_MANY_ACTUALS} then the list given by
   * {@link ParameterListMatchResult#getMatches} contains an entry for every actual parameter in {@code actualParameters} up to and including the
   * "actual parameter too far". Actual parameters after this "parameter too far" will not be in the list of parameter matches. <br>
   * Otherwise, the list given by {@link ParameterListMatchResult#getMatches()} contains an entry for every actual parameter in {@code actualParameters},
   * including null actual parameters. Each entry contains the actual parameter, the formal parameter it matched, if any, and the {@link IMatchResult} of
   * matching that actual parameter to that formal parameter.
   * </p>
   *
   * @param actualParameters
   *          the actual parameters to match, must not be {@code null}
   * @param formalParameters
   *          the formal parameters to match against, must not be {@code null}. Must also be idempotent, that is, if you traverse this iterator more than once
   *          then it must yield exactly the same elements each time.
   * @param parameterMatchChecker
   *          a parameter match checker to perform additional checks between an actual and formal parameter that have already matched
   *          by position or name, must not be {@code null}
   * @param caseSensitiveNames
   *          true if actual and formal parameter name matching should be case sensitive
   * @param forceMatchPositional
   *          true if also named parameters should be matched by position
   * @return a parameter list match result containing the details of the match, never {@code null}
   */
  public ParameterListMatchResult match(final Iterable<? extends IActualParameter> actualParameters, final Iterable<? extends IFormalParameter> formalParameters, final IParameterMatchChecker parameterMatchChecker, final boolean caseSensitiveNames, final boolean forceMatchPositional) {
    ParameterListMatchResult listMatchResult = new ParameterListMatchResult();
    listMatchResult.setMatchChecker(parameterMatchChecker);
    Iterator<? extends IActualParameter> actuals = actualParameters.iterator();
    Iterator<? extends IFormalParameter> formals = formalParameters.iterator();
    if (!actuals.hasNext() && !formals.hasNext()) {
      return listMatchResult; // note - ParameterListMatchResult is initialized with status success as default.
    }
    IActualParameter nextActual = matchPositionalActuals(listMatchResult, actuals, formals, forceMatchPositional);
    if (nextActual != null) { // != null means there is at least one named actual
      // only the remaining formals can be correctly matched by name.
      Map<String, IFormalParameter> formalsToMatchByName = initFormalsToMatchByName(formals, listMatchResult, caseSensitiveNames);
      matchNamedActuals(listMatchResult, nextActual, actuals, formalsToMatchByName, caseSensitiveNames);
    }
    // At this point all actual parameters have been consumed.
    // Now check for unnamed parameters after named parameters and duplicate or mandatory formal parameters that have not yet been consumed.
    boolean namedFormalEncountered = false;
    for (IFormalParameter formal : formalParameters) {
      if (formal == null) {
        continue; // we ignore null formal parameters if they weren't matched by position or caught by initFormalsToMatchByName()
      }
      if (formal instanceof INamedFormalParameter) {
        namedFormalEncountered = true;
      } else {
        if (namedFormalEncountered) {
          listMatchResult.addUnnamedFormalAfterNamed(formal);
        }
      }
      if (listMatchResult.getConsumedFormals().contains(formal)) {
        continue; // we ignore null formal parameters if they weren't matched by position or caught by initFormalsToMatchByName()
      }
      if (formal.isMandatory()) {
        listMatchResult.setStatus(ParameterListMatchStatus.MISSING_MANDATORY);
        listMatchResult.addUnmatchedMandatoryFormal(formal);
      }
      listMatchResult.addConsumedFormal(formal); // note - will catch duplicate formals
    }
    return listMatchResult;
  }

  /**
   * Initializes the formals-to-match-by-name from the remaining formal parameters.
   *
   * @param remainingFormals
   *          an iterator listing the remaining formal parameters, must not be {@code null}
   * @param listMatchResult
   *          the parameter list map result for setting the status, must not be {@code null}
   * @param caseSensitiveNames
   *          true if actual and formal parameter name matching should be case sensitive
   * @return the initialized map of formal parameters to match, never {@code null}
   */
  private Map<String, IFormalParameter> initFormalsToMatchByName(final Iterator<? extends IFormalParameter> remainingFormals, final ParameterListMatchResult listMatchResult, final boolean caseSensitiveNames) {
    Map<String, IFormalParameter> resultMap = new HashMap<String, IFormalParameter>();
    while (remainingFormals.hasNext()) {
      IFormalParameter formal = remainingFormals.next();
      if (formal instanceof INamedFormalParameter) {
        String formalName = ((INamedFormalParameter) formal).getName();
        boolean validName = formalName != null && formalName.length() != 0;
        if (validName && !caseSensitiveNames) {
          formalName = formalName.toLowerCase(Locale.getDefault());
        }
        if (validName && !listMatchResult.getConsumedNamedFormals().containsKey(formalName) && !resultMap.containsKey(formalName)) {
          resultMap.put(formalName, formal);
        } else if (!validName) {
          listMatchResult.setStatus(ParameterListMatchStatus.INVALID_FORMAL_NAME);
        }
      } else { // it is either null or an unnamed parameter
        if (formal == null) {
          listMatchResult.setStatus(ParameterListMatchStatus.INVALID_FORMAL);
        }
      }
    }
    return resultMap;
  }

  /**
   * Matches the positional part of an actual parameter list.
   * <p>
   * Marches down the actual parameter list and the formal parameter list in lock step and matches corresponding parameters until it encounters a named actual
   * parameter and {@code forceMatchPositional} is {@code false}. While doing so it:
   * <ul>
   * <li>Adds these matched parameters to the list of matched parameters in {@code listMatchResult}.
   * <li>Updates the status in {@code listMatchResult} if an error is encountered.
   * <li>adds the matched formal parameters to the {@code usedFormals} map.
   * </ul>
   * </p>
   *
   * @param listMatchResult
   *          a parameter list match result that can be updated with an error status and a list of matched parameters, must not be {@code null}
   * @param actuals
   *          the actual parameters to match, must not be {@code null}
   * @param formals
   *          the formal parameters to match against, must not be {@code null}
   * @param forceMatchPositional
   *          true if also named parameters should be matched by position
   * @return the first named actual parameter or {@code null} if the actual parameter list has been exhausted
   */
  private IActualParameter matchPositionalActuals(final ParameterListMatchResult listMatchResult, final Iterator<? extends IActualParameter> actuals, final Iterator<? extends IFormalParameter> formals, final boolean forceMatchPositional) {
    IFormalParameter currentFormal = null;
    while (actuals.hasNext()) {
      IActualParameter actual = actuals.next();
      if (!forceMatchPositional && actual instanceof INamedActualParameter) {
        return actual;
      }
      if (currentFormal == null || !currentFormal.isMulti()) {
        if (formals.hasNext()) {
          currentFormal = formals.next();
        } else {
          listMatchResult.updateResult(ParameterListMatchStatus.TOO_MANY_ACTUALS, new ParameterMatch(actual, null, IParameterMatchChecker.MISMATCH));
          return null;
        }
      }
      if (actual == null) {
        listMatchResult.updateResult(ParameterListMatchStatus.INVALID_ACTUAL, new ParameterMatch(null, currentFormal, IParameterMatchChecker.INVALID_ACTUAL));
      } else if (currentFormal == null) {
        listMatchResult.updateResult(ParameterListMatchStatus.INVALID_FORMAL, new ParameterMatch(actual, null, IParameterMatchChecker.INVALID_FORMAL));
      } else {
        // current formal != null and is either a multi-formal or the next formal in the list. Also, actual != null
        IMatchResult matchResult = listMatchResult.getMatchChecker().checkMatch(actual, currentFormal);
        listMatchResult.addParameterMatch(new ParameterMatch(actual, currentFormal, matchResult));
      }
      listMatchResult.addConsumedFormal(currentFormal);
    }
    return null;
  }

  /**
   * Matches the named actual parameter part of an actual parameter list against a set of formal parameters.
   * <p>
   * Marches down the given actual parameter list and matches them to corresponding formal parameters by name. While doing so it:
   * <ul>
   * <li>Adds these matched parameters to the list of matched parameters in {@code listMatchResult}.
   * <li>Updates the status in {@code listMatchResult} if an error is encountered.
   * <li>adds the matched formal parameters to the {@code usedFormals} map.
   * </ul>
   * </p>
   *
   * @param listMatchResult
   *          a parameter list match result that can be updated with an error status and a list of matched parameters, must not be {@code null}
   * @param firstActual
   *          the first named actual parameter to match, must not be {@code null}
   * @param remainingActuals
   *          an iterator containing the remaining actual parameters to match, must not be {@code null}
   * @param formalsToMatchByName
   *          a map containing the formal parameters that can be matched by name, must not be {@code null}
   * @param caseSensitiveNames
   *          true if actual and formal parameter name matching should be case sensitive
   */
  private void matchNamedActuals(final ParameterListMatchResult listMatchResult, final IActualParameter firstActual, final Iterator<? extends IActualParameter> remainingActuals, final Map<String, IFormalParameter> formalsToMatchByName, final boolean caseSensitiveNames) {
    Set<String> usedActualNames = new HashSet<String>(); // keeps track of used actual names for detecting duplicates
    Set<EObject> containers = Collections.newSetFromMap(new IdentityHashMap<>()); // keeps track to which parameter list an argument belongs
    // process firstActual
    ParameterMatch parameterResult = matchNamedActual(firstActual, formalsToMatchByName, usedActualNames, listMatchResult, caseSensitiveNames, containers);
    listMatchResult.updateResult(translateMatchResult(parameterResult.getMatchResult().getStatus()), parameterResult);
    // process remaining actuals
    while (remainingActuals.hasNext()) {
      parameterResult = matchNamedActual(remainingActuals.next(), formalsToMatchByName, usedActualNames, listMatchResult, caseSensitiveNames, containers);
      listMatchResult.updateResult(translateMatchResult(parameterResult.getMatchResult().getStatus()), parameterResult);
    }
  }

  /**
   * Matches a named actual parameter against a set of formal parameters.
   *
   * @param actual
   *          the actual parameter to match, may be {@code null}
   * @param formalsToMatchByName
   *          the formal parameters that can be matched by name, must not be {@code null}
   * @param usedActualNames
   *          a set containing the names of named actual parameters that have already been consumed, must not be {@code null}
   * @param listMatchResult
   *          the parameter list match result containing the state of the match process, must not be {@code null}
   * @param caseSensitiveNames
   *          true if actual and formal parameter name matching should be case sensitive
   * @param containers
   *          set containing eContainers of the named actual parameters seen so far, must not be {@code null}
   * @return the parameter match
   *         a parameter match for the {@code actual}. If the {@code actual} is {@code null} then the status of the match is
   *         {@link IParameterMatchChecker.INVALID_ACTUAL}, never {@code null}
   */
  private ParameterMatch matchNamedActual(final IActualParameter actual, final Map<String, IFormalParameter> formalsToMatchByName, final Set<String> usedActualNames, final ParameterListMatchResult listMatchResult, final boolean caseSensitiveNames, final Set<EObject> containers) {
    if (!(actual instanceof INamedActualParameter)) {
      return matchPositionalAfterNamed(actual, containers);
    }
    containers.add(actual.eContainer());
    String actualName = ((INamedActualParameter) actual).getName();
    if (actualName == null || actualName.length() == 0) {
      return new ParameterMatch(actual, null, IParameterMatchChecker.INVALID_ACTUAL);
    }
    if (!caseSensitiveNames) {
      actualName = actualName.toLowerCase(Locale.getDefault());
    }
    boolean actualNameAlreadyUsed = // NOPMD - Result may change before usage
        !usedActualNames.add(actualName);
    IFormalParameter formal = formalsToMatchByName.get(actualName);
    if (formal == null) {
      formal = listMatchResult.getConsumedNamedFormals().get(actualName);
      if (formal != null) { // i.e., formal in consumedFormalNames\formalsToMatchByName = named formals matched by position
        return new ParameterMatch(actual, formal, IParameterMatchChecker.NAMED_ACTUAL_MATCHES_POSITIONAL);
      } else {
        return new ParameterMatch(actual, null, IParameterMatchChecker.NO_MATCH_FOR_NAMED_ACTUAL);
      }
    }
    if (!formal.isMulti() && actualNameAlreadyUsed) {
      return new ParameterMatch(actual, formal, IParameterMatchChecker.DUPLICATE_NAMED_ACTUAL); // no need to update usedFormals
    }
    IMatchResult matchResult = listMatchResult.getMatchChecker().checkMatch(actual, formal);
    listMatchResult.addConsumedFormal(formal);
    return new ParameterMatch(actual, formal, matchResult);
  }

  /**
   * Matches a positional parameter encountered after one or more named parameters.
   *
   * @param actual
   *          the actual parameter to match, may be {@code null}
   * @param containers
   *          set containing eContainers of the named actual parameters seen so far, must not be {@code null}
   * @return the parameter match
   *         a parameter match for the {@code actual}. If the {@code actual} is {@code null} then the status of the match is
   *         {@link IParameterMatchChecker.INVALID_ACTUAL}, never {@code null}
   */
  private ParameterMatch matchPositionalAfterNamed(final IActualParameter actual, final Set<EObject> containers) {
    if (actual == null) {
      return new ParameterMatch(actual, null, IParameterMatchChecker.INVALID_ACTUAL);
    } else if (containers.contains(actual.eContainer())) { // Named parameter already seen in this parameter list
      return new ParameterMatch(actual, null, IParameterMatchChecker.POSITIONAL_AFTER_NAMED);
    } else {
      return new ParameterMatch(actual, null, IParameterMatchChecker.MATCH);
    }
  }

  /**
   * Translates a parameter match result status for a single parameter into a parameter list match status for the the list.
   *
   * @param status
   *          the parameter match status to translate
   * @return the translated parameter match status
   */
  private ParameterListMatchStatus translateMatchResult(final IParameterMatchChecker.MatchStatus status) {
    switch (status) {
    case INVALID_ACTUAL:
    case POSITIONAL_AFTER_NAMED:
    case DUPLICATE_NAMED_ACTUAL:
    case NAMED_ACTUAL_MATCHES_POSITIONAL:
      return ParameterListMatchStatus.INVALID_ACTUAL;
    case INVALID_FORMAL:
      return ParameterListMatchStatus.INVALID_FORMAL;
    case MATCH:
      return ParameterListMatchStatus.MATCH_SUCCESSFUL;
    case MISMATCH:
      return ParameterListMatchStatus.PARAMETER_MISMATCH;
    default:
      return ParameterListMatchStatus.PARAMETER_MISMATCH;
    }
  }

}
