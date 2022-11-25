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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.junit.Test;

import com.avaloq.tools.ddk.typesystem.IParameterMatchChecker.IMatchResult;
import com.avaloq.tools.ddk.typesystem.ParameterListMatcher.ParameterListMatchResult;
import com.avaloq.tools.ddk.typesystem.ParameterListMatcher.ParameterListMatchStatus;
import com.avaloq.tools.ddk.typesystem.ParameterListMatcher.ParameterMatch;
import com.avaloq.tools.ddk.typesystem.typemodel.IActualParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.IExpression;
import com.avaloq.tools.ddk.typesystem.typemodel.IFormalParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedActualParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedFormalParameter;
import com.avaloq.tools.ddk.typesystem.typemodel.IType;
import com.avaloq.tools.ddk.typesystem.typemodel.impl.NamedTypeImpl;


// You can't have too many tests
@SuppressWarnings({"PMD.ExcessivePublicCount"})
public class ParameterListMatcherTest {

  private static final String WRONG_NUMBER_OF_UNNAMED_FORMALS_AFTER_NAMED_FORMALS = "wrong number of unnamed formals after named formals";
  private static final String UNNAMED_FORMAL_AFTER_NAMED_NOT_LOCATED = "unnamed formal after named not located";
  private static final String INCORRECT_UNMATCHED_MANDATORY_FORMAL = "incorrect unmatched mandatory formal";
  private static final String INCORRECT_NO_UNMATCHED_MANDATORY_FORMALS = "incorrect no. unmatched mandatory formals";

  private static final String NAME_1 = "one";
  private static final String NAME_2 = "two";
  private static final String NAME_3 = "three";
  private static final String BLANK_NAME = "";

  private static final boolean IS_MANDATORY = true;
  private static final boolean IS_OPTIONAL = false;
  private static final boolean IS_MULTI = true;

  private static final boolean CASE_SENSITIVE = true;
  private static final boolean CASE_INSENSITIVE = false;

  private static final boolean FORCE_MATCH_BY_POSITION = true;

  private final IMatchResult matchResultMatch = IParameterMatchChecker.MATCH;
  private final IMatchResult matchResultTypeError = IParameterMatchChecker.TYPE_ERROR;

  protected class NamedType extends NamedTypeImpl {

    private final String name;

    public NamedType(final String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return name;
    }

  }

  protected class FormalParameter extends EObjectImpl implements IFormalParameter {

    private final boolean mandatory;
    private final boolean multi;
    private final IType type;

    public FormalParameter(final IType type, final boolean isMandatory, final boolean isMulti) {
      this.mandatory = isMandatory;
      this.multi = isMulti;
      this.type = type;
    }

    public FormalParameter(final IType type) {
      this(type, true, false);
    }

    @Override
    public boolean isMandatory() {
      return mandatory;
    }

    @Override
    public boolean isMulti() {
      return multi;
    }

    @Override
    public IType getType() {
      return type;
    }

  }

  protected class NamedFormalParameter extends FormalParameter implements INamedFormalParameter {

    private final String name;

    public NamedFormalParameter(final String name, final IType type, final boolean isMandatory, final boolean isMulti) {
      super(type, isMandatory, isMulti);
      this.name = name;
    }

    public NamedFormalParameter(final String name, final IType type) {
      this(name, type, true, false);
    }

    @Override
    public String getName() {
      return name;
    }

  }

  protected class ParameterList extends EObjectImpl implements InternalEObject {
    // Empty class to make protected constructor visible.
  }

  protected class ActualParameter extends EObjectImpl implements IActualParameter {

    private final IType type;

    public ActualParameter(final IType type) {
      this.type = type;
    }

    public IType getType() {
      return type;
    }

    @Override
    public IExpression getValue() {
      return null;
    }

  }

  protected class NamedActualParameter extends ActualParameter implements INamedActualParameter {

    private final String name;

    public NamedActualParameter(final String name, final IType type) {
      super(type);
      this.name = name;
    }

    @Override
    public String getName() {
      return name;
    }

  }

  protected class ParameterMatcherCheckerTest implements IParameterMatchChecker {

    @Override
    public IMatchResult checkMatch(final IActualParameter actual, final IFormalParameter formal) {
      if (actual instanceof ActualParameter && formal instanceof FormalParameter) {
        if (((ActualParameter) actual).getType() == ((FormalParameter) formal).getType()) {
          return matchResultMatch;
        } else {
          return matchResultTypeError;
        }
      } else {
        fail("incorrect parameters");
        return null;
      }

    }

  }

  private final ParameterListMatcher parameterListMatcher = new ParameterListMatcher();
  private final IParameterMatchChecker parameterMatcher = new ParameterMatcherCheckerTest();

  private final NamedType intType = new NamedType("int");
  private final NamedType textType = new NamedType("text");

  private void checkParameterListResult(final ParameterListMatcher.ParameterListMatchStatus status, final int numberOfMatches, final int matchListSize, final ParameterListMatcher.ParameterListMatchResult result) {
    assertSame("wrong list match status", status, result.getStatus());
    assertSame("wrong number of matched parameters", numberOfMatches, result.getNumberMatched());
    assertSame("wrong match list size", matchListSize, result.getMatches().size());
  }

  private void checkParameterMatch(final IParameterMatchChecker.MatchStatus status, final ActualParameter actual, final FormalParameter formal, final ParameterListMatcher.ParameterMatch match) {
    assertSame("wrong match status", status, match.getMatchResult().getStatus());
    if (actual == null) {
      assertNull("match actual not null", match.getActualParameter());
    } else {
      assertEquals("wrong match actual", actual, match.getActualParameter());
    }
    if (formal == null) {
      assertNull("match formal not null", match.getFormalParameter());
    } else {
      assertEquals("wrong match formal", formal, match.getFormalParameter());
    }
  }

  @Test
  public void testNoneAgainstNone() {
    ParameterListMatchResult matchResult = parameterListMatcher.match(new ArrayList<ActualParameter>(), new ArrayList<NamedFormalParameter>(), parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 0, 0, matchResult);
  }

  @Test
  public void testOneUnnamedAgainstOne() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 1, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
  }

  @Test
  public void testOneNamedAgainstOne() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    List<NamedActualParameter> actuals = new ArrayList<NamedActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 1, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
  }

  @Test
  public void testOneNamedAgainstOneCaseSensitive() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1.toUpperCase(Locale.getDefault()), intType));
    List<NamedActualParameter> actuals = new ArrayList<NamedActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1.toUpperCase(Locale.getDefault()), intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_SENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 1, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    // now test mismatch
    formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    actuals = new ArrayList<NamedActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1.toUpperCase(Locale.getDefault()), intType));
    matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_SENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.PARAMETER_MISMATCH, 0, 1, matchResult);
    matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.NO_MATCH_FOR_NAMED_ACTUAL, actuals.get(0), null, matches.get(0));
  }

  @Test
  public void testOneUnnamedAgainstNone() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.TOO_MANY_ACTUALS, 0, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MISMATCH, actuals.get(0), null, matches.get(0));
  }

  @Test
  public void testOneNamedAgainstNone() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    List<NamedActualParameter> actuals = new ArrayList<NamedActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.PARAMETER_MISMATCH, 0, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.NO_MATCH_FOR_NAMED_ACTUAL, actuals.get(0), null, matches.get(0));
  }

  @Test
  public void testNoneAgainstOne() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    NamedFormalParameter mandatoryParam = new NamedFormalParameter(NAME_1, intType);
    formals.add(mandatoryParam);
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MISSING_MANDATORY, 0, 0, matchResult);
    assertSame(INCORRECT_NO_UNMATCHED_MANDATORY_FORMALS, 1, matchResult.getUnmatchedMandatoryFormalParameters().size());
    assertSame(INCORRECT_UNMATCHED_MANDATORY_FORMAL, mandatoryParam, matchResult.getUnmatchedMandatoryFormalParameters().get(0));
  }

  @Test
  public void testNoneAgainstTwoMandatory() {
    // one mandatory and one not mandatory
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    NamedFormalParameter mandatoryParam1 = new NamedFormalParameter(NAME_1, intType);
    formals.add(mandatoryParam1);
    NamedFormalParameter mandatoryParam2 = new NamedFormalParameter(NAME_2, textType);
    formals.add(mandatoryParam2);
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MISSING_MANDATORY, 0, 0, matchResult);
    assertSame(INCORRECT_NO_UNMATCHED_MANDATORY_FORMALS, 2, matchResult.getUnmatchedMandatoryFormalParameters().size());
    assertSame(INCORRECT_UNMATCHED_MANDATORY_FORMAL, mandatoryParam1, matchResult.getUnmatchedMandatoryFormalParameters().get(0));
    assertSame(INCORRECT_UNMATCHED_MANDATORY_FORMAL, mandatoryParam2, matchResult.getUnmatchedMandatoryFormalParameters().get(1));
  }

  @Test
  public void testNoneAgainstTwoMixed() {
    // one mandatory and one not mandatory
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    NamedFormalParameter mandatoryParam = new NamedFormalParameter(NAME_1, intType);
    formals.add(mandatoryParam);
    formals.add(new NamedFormalParameter(NAME_2, textType, !IS_MANDATORY, !IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MISSING_MANDATORY, 0, 0, matchResult);
    assertSame(INCORRECT_NO_UNMATCHED_MANDATORY_FORMALS, 1, matchResult.getUnmatchedMandatoryFormalParameters().size());
    assertSame(INCORRECT_UNMATCHED_MANDATORY_FORMAL, mandatoryParam, matchResult.getUnmatchedMandatoryFormalParameters().get(0));
  }

  @Test
  public void testThreeUnnamedWithOneTypeError() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.PARAMETER_MISMATCH, 2, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.TYPE_ERROR, actuals.get(2), formals.get(2), matches.get(2));
  }

  @Test
  public void testThreeNamedWithOneTypeError() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType));
    actuals.add(new NamedActualParameter(NAME_3, textType)); // note out of order
    actuals.add(new NamedActualParameter(NAME_2, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.PARAMETER_MISMATCH, 2, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.TYPE_ERROR, actuals.get(1), formals.get(2), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(1), matches.get(2));
  }

  @Test
  public void testThreeMixedAgainstThreeMandatory() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_3, textType)); // note out of order
    actuals.add(new NamedActualParameter(NAME_2, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 3, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(2), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(1), matches.get(2));
  }

  @Test
  public void testTwoMixedAgainstThreeMandatory() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_3, textType)); // note out of order
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MISSING_MANDATORY, 2, 2, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(2), matches.get(1));
  }

  @Test
  public void testTwoMixedAgainstThreeWithOptional() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType, IS_OPTIONAL, !IS_MULTI));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_3, textType)); // note out of order
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 2, 2, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(2), matches.get(1));
  }

  @Test
  public void testTwoUnnamedAgainstThreeWithOptional() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType, IS_OPTIONAL, !IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    check2Successful(formals, actuals);
  }

  @Test
  public void testUnnamedTooManyActuals() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.TOO_MANY_ACTUALS, 2, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    // Note - last actual not in matches because the last one in matches is the "actual parameter too far"
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MISMATCH, actuals.get(2), null, matches.get(2));
  }

  @Test
  public void testFourUnnamedAgainstTwoWithMulti() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType, IS_MANDATORY, IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    check4Successful(formals, actuals);
  }

  @Test
  public void testFourNamedAgainstTwoWithMulti() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType, IS_MANDATORY, IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_2, intType));
    actuals.add(new NamedActualParameter(NAME_1, intType));
    actuals.add(new NamedActualParameter(NAME_2, intType));
    actuals.add(new NamedActualParameter(NAME_2, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 4, 4, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(1), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(0), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(1), matches.get(2));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(3), formals.get(1), matches.get(3));
  }

  @Test
  public void testTwoUnNamedAgainstTwoPositional() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new FormalParameter(textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(textType));
    check2Successful(formals, actuals);
  }

  private void check2Successful(final List<FormalParameter> formals, final List<ActualParameter> actuals) {
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 2, 2, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
  }

  @Test
  public void testFourUnNamedAgainstTwoPositionalwithMulti() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new FormalParameter(textType, IS_MANDATORY, IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(textType));
    actuals.add(new ActualParameter(textType));
    actuals.add(new ActualParameter(textType));
    check4Successful(formals, actuals);
  }

  private void check4Successful(final List<FormalParameter> formals, final List<ActualParameter> actuals) {
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 4, 4, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(1), matches.get(2));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(3), formals.get(1), matches.get(3));
  }

  @Test
  public void testFourUnNamedAgainstTwoPositionalwithMultiAndTypeError() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new FormalParameter(textType, IS_MANDATORY, IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(textType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.PARAMETER_MISMATCH, 3, 4, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.TYPE_ERROR, actuals.get(2), formals.get(1), matches.get(2));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(3), formals.get(1), matches.get(3));
  }

  @Test
  public void testNamedAgainstPositional() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new FormalParameter(textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_2, textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.PARAMETER_MISMATCH, 1, 2, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.NO_MATCH_FOR_NAMED_ACTUAL, actuals.get(1), null, matches.get(1));
  }

  @Test
  public void testMissingMandatoryAgainstPositional() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new FormalParameter(textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MISSING_MANDATORY, 1, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
  }

  @Test
  public void testInvalidActualNull() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, textType));
    formals.add(new NamedFormalParameter(NAME_3, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(textType));
    actuals.add(null);
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterListResult(ParameterListMatchStatus.INVALID_ACTUAL, 2, 3, matchResult);
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.INVALID_ACTUAL, actuals.get(2), formals.get(2), matches.get(2));
  }

  @Test
  public void testInvalidActualNullWithMulti() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, textType, IS_MANDATORY, IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(null);
    actuals.add(new ActualParameter(textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterListResult(ParameterListMatchStatus.INVALID_ACTUAL, 2, 3, matchResult);
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.INVALID_ACTUAL, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(1), matches.get(2));
  }

  @Test
  public void testInvalidNamedActualNullName() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, textType, IS_OPTIONAL, !IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType));
    actuals.add(new NamedActualParameter(null, textType));
    check2WithNullFormal1(formals, actuals);
  }

  @Test
  public void testInvalidNamedActualBlankName() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, textType, IS_OPTIONAL, !IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType));
    actuals.add(new NamedActualParameter(BLANK_NAME, textType));
    check2WithNullFormal1(formals, actuals);
  }

  private void check2WithNullFormal1(final List<NamedFormalParameter> formals, final List<ActualParameter> actuals) {
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_ACTUAL, 1, 2, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.INVALID_ACTUAL, actuals.get(1), null, matches.get(1));
  }

  @Test
  public void testInvalidFormalNull() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(null);
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_3, textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_FORMAL, 2, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.INVALID_FORMAL, actuals.get(1), null, matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(2), matches.get(2));
  }

  @Test
  public void testInvalidFormalNullName() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(null, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_2, intType));
    actuals.add(new NamedActualParameter(NAME_3, textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_FORMAL_NAME, 2, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.NO_MATCH_FOR_NAMED_ACTUAL, actuals.get(1), null, matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(2), matches.get(2));
  }

  @Test
  public void testInvalidActualNullNameInvalidFormalNullName() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(null, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(null, textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_FORMAL_NAME, 2, 3, matchResult); // position matches against null
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.INVALID_ACTUAL, actuals.get(2), null, matches.get(2));
  }

  @Test
  public void testDuplicateFormalName() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_1, intType, IS_OPTIONAL, !IS_MULTI));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_3, textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.DUPLICATE_PARAMETER, 2, 2, matchResult);
    assertSame("have one duplicate formal", 1, matchResult.getDuplicateNamedFormals().size());
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(2), matches.get(1));
  }

  @Test
  public void testDuplicateNamedActual() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_2, textType));
    actuals.add(new NamedActualParameter(NAME_2, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_ACTUAL, 1, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.TYPE_ERROR, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.DUPLICATE_NAMED_ACTUAL, actuals.get(2), formals.get(1), matches.get(2));
  }

  @Test
  public void testPositionalAfterNamed1() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_2, intType));
    actuals.add(new ActualParameter(textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_ACTUAL, 2, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.POSITIONAL_AFTER_NAMED, actuals.get(2), null, matches.get(2));
  }

  @Test
  public void testPositionalAfterNamed2() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, textType));
    formals.add(new NamedFormalParameter(NAME_3, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType));
    actuals.add(new ActualParameter(textType));
    actuals.add(new ActualParameter(intType));
    ParameterList container1 = new ParameterList();
    ParameterList container2 = new ParameterList();
    actuals.get(0).eBasicSetContainer(container1, 0, null);
    actuals.get(1).eBasicSetContainer(container1, 0, null);
    actuals.get(2).eBasicSetContainer(container2, 0, null);
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_ACTUAL, 2, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.POSITIONAL_AFTER_NAMED, actuals.get(1), null, matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), null, matches.get(2));

  }

  @Test
  public void testNamedMatchesPositional() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_1, intType));
    actuals.add(new NamedActualParameter(NAME_3, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.INVALID_ACTUAL, 1, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.NAMED_ACTUAL_MATCHES_POSITIONAL, actuals.get(1), formals.get(0), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.TYPE_ERROR, actuals.get(2), formals.get(2), matches.get(2));
  }

  @Test
  public void testNamedFormalAfterUnnamed1() {
    // these are allowed, match named by position.
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    check2Successful(formals, actuals);
  }

  @Test
  public void testNamedFormalAfterUnnamed2() {
    // these are allowed, match named by name.
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_2, intType));
    check2Successful(formals, actuals);
  }

  @Test
  public void testNamedFormalAfterUnnamed3() {
    // these are allowed, match named by name, unnamed and named optional
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType, !IS_MANDATORY, !IS_MULTI));
    formals.add(new NamedFormalParameter(NAME_2, intType, !IS_MANDATORY, !IS_MULTI));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_2, intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 1, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(1), matches.get(0));
  }

  private void verifyOneUnnamedFormalAfterNamed(final List<FormalParameter> formals, final FormalParameter unnamedFormal, final List<ActualParameter> actuals) {
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.UNNAMED_FORMAL_AFTER_NAMED, 1, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    assertSame(WRONG_NUMBER_OF_UNNAMED_FORMALS_AFTER_NAMED_FORMALS, 1, matchResult.getUnnamedFormalsAfterNamed().size());
    assertSame(UNNAMED_FORMAL_AFTER_NAMED_NOT_LOCATED, unnamedFormal, matchResult.getUnnamedFormalsAfterNamed().get(0));
  }

  @Test
  public void testUnnamedFormalAfterNamed1() {
    // unnamed is matched by position
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    FormalParameter unnamedFormal1 = new FormalParameter(intType);
    formals.add(unnamedFormal1);
    FormalParameter unnamedFormal2 = new FormalParameter(textType);
    formals.add(unnamedFormal2);
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new ActualParameter(intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.UNNAMED_FORMAL_AFTER_NAMED, 2, 2, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    assertSame(WRONG_NUMBER_OF_UNNAMED_FORMALS_AFTER_NAMED_FORMALS, 2, matchResult.getUnnamedFormalsAfterNamed().size());
    assertSame(UNNAMED_FORMAL_AFTER_NAMED_NOT_LOCATED, unnamedFormal1, matchResult.getUnnamedFormalsAfterNamed().get(0));
    assertSame(UNNAMED_FORMAL_AFTER_NAMED_NOT_LOCATED, unnamedFormal2, matchResult.getUnnamedFormalsAfterNamed().get(1));
  }

  @Test
  public void testUnnamedFormalAfterNamed2() {
    // unnamed is optional, at end and not matched because fewer formal parameters
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    FormalParameter unnamedFormal = new FormalParameter(intType, !IS_MANDATORY, !IS_MULTI);
    formals.add(unnamedFormal);
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType));
    verifyOneUnnamedFormalAfterNamed(formals, unnamedFormal, actuals);
  }

  @Test
  public void testUnnamedFormalAfterNamed3() {
    // unnamed is mandatory, at end and not matched because fewer formal parameters
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    FormalParameter unnamedFormal = new FormalParameter(intType, IS_MANDATORY, !IS_MULTI);
    formals.add(unnamedFormal);
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    verifyOneUnnamedFormalAfterNamed(formals, unnamedFormal, actuals);
  }

  @Test
  public void testUnnamedFormalAfterNamed4() {
    // two unnamed formals after named
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new NamedFormalParameter(NAME_2, intType));
    FormalParameter unnamedFormal1 = new FormalParameter(intType);
    formals.add(unnamedFormal1);
    FormalParameter unnamedFormal2 = new FormalParameter(intType);
    formals.add(unnamedFormal2);
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE);
    checkParameterListResult(ParameterListMatchStatus.UNNAMED_FORMAL_AFTER_NAMED, 1, 1, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    assertSame(WRONG_NUMBER_OF_UNNAMED_FORMALS_AFTER_NAMED_FORMALS, 2, matchResult.getUnnamedFormalsAfterNamed().size());
    assertSame(UNNAMED_FORMAL_AFTER_NAMED_NOT_LOCATED, unnamedFormal1, matchResult.getUnnamedFormalsAfterNamed().get(0));
    assertSame(UNNAMED_FORMAL_AFTER_NAMED_NOT_LOCATED, unnamedFormal2, matchResult.getUnnamedFormalsAfterNamed().get(1));
  }

  @Test
  public void testForceMatchByPosition1() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_3, intType)); // note wrong name
    actuals.add(new NamedActualParameter(NAME_1, intType)); // note wrong name
    actuals.add(new NamedActualParameter(NAME_2, textType)); // note wrong name
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE, FORCE_MATCH_BY_POSITION);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 3, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(2), matches.get(2));
  }

  @Test
  public void testForceMatchByPosition2() {
    List<NamedFormalParameter> formals = new ArrayList<NamedFormalParameter>();
    formals.add(new NamedFormalParameter(NAME_1, intType));
    formals.add(new NamedFormalParameter(NAME_2, intType));
    formals.add(new NamedFormalParameter(NAME_3, textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new ActualParameter(intType));
    actuals.add(new NamedActualParameter(NAME_1, intType));
    actuals.add(new ActualParameter(textType));
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE, FORCE_MATCH_BY_POSITION);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 3, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(2), matches.get(2));
  }

  @Test
  public void testForceMatchByPosition3() {
    List<FormalParameter> formals = new ArrayList<FormalParameter>();
    formals.add(new FormalParameter(intType));
    formals.add(new FormalParameter(intType));
    formals.add(new FormalParameter(textType));
    List<ActualParameter> actuals = new ArrayList<ActualParameter>();
    actuals.add(new NamedActualParameter(NAME_1, intType)); // corresponding formal is unnamed
    actuals.add(new NamedActualParameter(NAME_2, intType)); // corresponding formal is unnamed
    actuals.add(new NamedActualParameter(NAME_3, textType)); // corresponding formal is unnamed
    ParameterListMatchResult matchResult = parameterListMatcher.match(actuals, formals, parameterMatcher, CASE_INSENSITIVE, FORCE_MATCH_BY_POSITION);
    checkParameterListResult(ParameterListMatchStatus.MATCH_SUCCESSFUL, 3, 3, matchResult);
    List<ParameterMatch> matches = matchResult.getMatches();
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(0), formals.get(0), matches.get(0));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(1), formals.get(1), matches.get(1));
    checkParameterMatch(IParameterMatchChecker.MatchStatus.MATCH, actuals.get(2), formals.get(2), matches.get(2));
  }

}
