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
package com.avaloq.tools.ddk.xtext.valid.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ComposedChecks;

import com.avaloq.tools.ddk.xtext.valid.valid.Category;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeContext;
import com.avaloq.tools.ddk.xtext.valid.valid.NativeRule;
import com.avaloq.tools.ddk.xtext.valid.valid.PredefinedRule;
import com.avaloq.tools.ddk.xtext.valid.valid.QuickFix;
import com.avaloq.tools.ddk.xtext.valid.valid.Rule;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidModel;
import com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage;


/**
 * The Class ValidJavaValidator.
 */
@SuppressWarnings("nls")
@ComposedChecks(validators = {org.eclipse.xtext.validation.NamesAreUniqueValidator.class})
public class ValidValidator extends AbstractValidValidator {

  private static final String LABEL_SHOULD_NOT_END_WITH_A_DOT = "Label should not end with a '.'";
  private static final String DESCRIPTION_SHOULD_END_WITH_A_DOT = "Description should end with a '.'";
  private static final String DOT = ".";
  private static final String DESCRIPTION_SHOULD_NOT_BE_EMPTY = "Description should not be empty";
  private static final String LABEL_SHALL_NOT_BE_EMPTY = "Label shall not be empty";
  private static final String CONTEXT_FEATURE_CANNOT_BE_SPECIFIED_IF_A_MARKER_TYPE_IS_GIVEN = "Context feature cannot be specified if a markerType is given.";
  private static final String CATEGORY_SHOULD_CONTAIN_AT_LEAST_ONE_RULE = "Category should contain at least one rule.";
  private static final String NAME_SHOULD_START_WITH_A_CAPITAL = "Name should start with a capital";

  public static final String NO_PREDEFINED_RULE = "ValidJavaValidator.NO_PREDEFINED_RULE"; //$NON-NLS-1$
  public static final String CATEGORY_FIRST_UPPER_NAME = "ValidJavaValidator.CATEGORY_FIRST_UPPER_NAME"; //$NON-NLS-1$
  public static final String RULE_FIRST_UPPER_NAME = "ValidJavaValidator.RULE_FIRST_UPPER_NAME"; //$NON-NLS-1$
  public static final String QUICK_FIX_FIRST_UPPER_NAME = "ValidJavaValidator.QUICK_FIX_FIRST_UPPER_NAME"; //$NON-NLS-1$
  public static final String NATIVE_CONTEXT_FIRST_UPPER_NAME = "ValidJavaValidator.NATIVE_CONTEXT_FIRST_UPPER_NAME"; //$NON-NLS-1$
  public static final String CATEGORY_EMPTY = "ValidJavaValidator.CATEGORY_EMPTY"; //$NON-NLS-1$
  public static final String NATIVE_CONTEXT_CONTEXT_FEATURE = "ValidJavaValidator.NATIVE_CONTEXT_CONTEXT_FEATURE"; //$NON-NLS-1$
  public static final String CATEGORY_LABEL = "ValidJavaValidator.CATEGORY_LABEL"; //$NON-NLS-1$
  public static final String RULE_LABEL = "ValidJavaValidator.RULE_LABEL"; //$NON-NLS-1$
  public static final String QUICK_FIX_LABEL = "ValidJavaValidator.QUICK_FIX_LABEL"; //$NON-NLS-1$
  public static final String UNIQUE_NATIVE_CONTEXT_NAME = "ValidJavaValidator.UNIQUE_NATIVE_CONTEXT_NAME"; //$NON-NLS-1$
  public static final String RULE_DESCRIPTION_ENDS_WITH_DOT = "ValidJavaValidator.RULE_DESCRIPTION_ENDS_WITH_DOT"; //$NON-NLS-1$
  public static final String CATEGORY_LABEL_ENDS_WITH_DOT = "ValidJavaValidator.CATEGORY_LABEL_ENDS_WITH_DOT"; //$NON-NLS-1$
  public static final String RULE_LABEL_ENDS_WITH_DOT = "ValidJavaValidator.RULE_LABEL_ENDS_WITH_DOT"; //$NON-NLS-1$
  public static final String QUICK_FIX_LABEL_ENDS_WITH_DOT = "ValidJavaValidator.QUICK_FIX_LABEL_ENDS_WITH_DOT"; //$NON-NLS-1$
  public static final String CATEGORY_DESCRIPTION = "ValidJavaValidator.CATEGORY_DESCRIPTION"; //$NON-NLS-1$
  public static final String CHECK_DESCRIPTION_RULE = "ValidJavaValidator.CHECK_DESCRIPTION_RULE"; //$NON-NLS-1$
  public static final String CATEGORY_DESCRIPTION_ENDS_WITH_DOT = "ValidJavaValidator.CATEGORY_DESCRIPTION_ENDS_WITH_DOT"; //$NON-NLS-1$

  /**
   * Predefined Rules are disabled for the time being.
   *
   * @param predefinedRule
   *          any predefined rule
   */
  @Check(CheckType.FAST)
  public void checkNoSizeRule(final PredefinedRule predefinedRule) {
    error("Size rules are not yet supported.", predefinedRule, ValidPackage.Literals.RULE__NAME, NO_PREDEFINED_RULE);
  }

  /**
   * Check that a category name starts with an upper-case character.
   *
   * @param category
   *          the category
   */
  @Check(CheckType.FAST)
  public void checkCategoryFirstUpperName(final Category category) {
    if (!Character.isUpperCase(category.getName().charAt(0))) {
      error(NAME_SHOULD_START_WITH_A_CAPITAL, ValidPackage.Literals.CATEGORY__NAME, CATEGORY_FIRST_UPPER_NAME);
    }

  }

  /**
   * Check that a rule name starts with an upper-case character.
   *
   * @param rule
   *          the rule
   */
  @Check(CheckType.FAST)
  public void checkRuleFirstUpperName(final Rule rule) {
    if (!Character.isUpperCase(rule.getName().charAt(0))) {
      error(NAME_SHOULD_START_WITH_A_CAPITAL, ValidPackage.Literals.RULE__NAME, RULE_FIRST_UPPER_NAME);
    }
  }

  /**
   * Check that a quick fix name starts with an upper-case character.
   *
   * @param quickFix
   *          the quick fix
   */
  @Check(CheckType.FAST)
  public void checkQuickFixFirstUpperName(final QuickFix quickFix) {
    if (!Character.isUpperCase(quickFix.getName().charAt(0))) {
      error(NAME_SHOULD_START_WITH_A_CAPITAL, ValidPackage.Literals.QUICK_FIX__NAME, QUICK_FIX_FIRST_UPPER_NAME);
    }

  }

  /**
   * Check that a native context name starts with an upper-case character.
   *
   * @param nativeContext
   *          the native context
   */
  @Check(CheckType.FAST)
  public void checkNativeContextFirstUpperName(final NativeContext nativeContext) {
    if (nativeContext.isNamed() && !Character.isUpperCase(nativeContext.getGivenName().charAt(0))) {
      error(NAME_SHOULD_START_WITH_A_CAPITAL, ValidPackage.Literals.NATIVE_CONTEXT__GIVEN_NAME, NATIVE_CONTEXT_FIRST_UPPER_NAME);
    }

  }

  /**
   * Check that a category has at least one rule.
   *
   * @param category
   *          the category
   */
  @Check(CheckType.FAST)
  public void checkCategoryEmpty(final Category category) {
    if (category.getRules().isEmpty()) {
      warning(CATEGORY_SHOULD_CONTAIN_AT_LEAST_ONE_RULE, null, CATEGORY_EMPTY);
    }
  }

  // Names must be unique is replaced by fragment <composedCheck value="org.eclipse.xtext.validation.NamesAreUniqueValidator"/>

  /**
   * Check that the native context type has no feature if a marker type is present.
   *
   * @param nativeContext
   *          the native context
   */
  @Check(CheckType.FAST)
  public void checkNativeContextContextFeature(final NativeContext nativeContext) {
    guard(nativeContext.getContextFeature() != null);
    if (nativeContext.getMarkerType() != null) {
      error(CONTEXT_FEATURE_CANNOT_BE_SPECIFIED_IF_A_MARKER_TYPE_IS_GIVEN, ValidPackage.Literals.CONTEXT__CONTEXT_FEATURE, NATIVE_CONTEXT_CONTEXT_FEATURE);
    }
  }

  /**
   * Check that a label is defined for the category.
   *
   * @param category
   *          the category
   */
  @Check(CheckType.FAST)
  public void checkCategoryLabel(final Category category) {
    if (category.getLabel().length() == 0) {
      error(LABEL_SHALL_NOT_BE_EMPTY, ValidPackage.Literals.CATEGORY__LABEL, CATEGORY_LABEL);
    }
  }

  /**
   * Check that a label is defined for the rule.
   *
   * @param rule
   *          the rule
   */
  @Check(CheckType.FAST)
  public void checkRuleLabel(final Rule rule) {
    if (rule.getLabel().length() == 0) {
      error(LABEL_SHALL_NOT_BE_EMPTY, ValidPackage.Literals.RULE__LABEL, RULE_LABEL);
    }

  }

  /**
   * Check that a label is defined for the quick fix.
   *
   * @param quickFix
   *          the quick fix
   */
  @Check(CheckType.FAST)
  public void checkQuickFixLabel(final QuickFix quickFix) {
    if (quickFix.getLabel().length() == 0) {
      error(LABEL_SHALL_NOT_BE_EMPTY, ValidPackage.Literals.QUICK_FIX__LABEL, QUICK_FIX_LABEL);

    }
  }

  /**
   * Check that a description is defined for the category.
   *
   * @param category
   *          the category
   */
  @Check(CheckType.FAST)
  public void checkCategoryDescription(final Category category) {
    // TODO[BAS] are descriptions really optional?
    if (category.getDescription() != null && category.getDescription().length() == 0) {
      warning(DESCRIPTION_SHOULD_NOT_BE_EMPTY, ValidPackage.Literals.CATEGORY__DESCRIPTION, CATEGORY_DESCRIPTION);
    }

  }

  /**
   * Check that a description is defined for the rule.
   *
   * @param rule
   *          the rule
   */
  @Check(CheckType.FAST)
  public void checkCheckDescriptionRule(final Rule rule) {
    if (rule.getDescription() == null || rule.getDescription().length() == 0) {
      warning(DESCRIPTION_SHOULD_NOT_BE_EMPTY, ValidPackage.Literals.RULE__DESCRIPTION, CHECK_DESCRIPTION_RULE);
    }
  }

  /**
   * Gets the name of a native context (either the alias -- given name -- or the name of the enclosing rule).
   *
   * @param context
   *          the context
   * @return the name
   */
  private String getName(final NativeContext context) {
    return (context.isNamed()) ? context.getGivenName() : ((NativeRule) context.eContainer()).getName();
  }

  /**
   * Check that, for each context type, the name of a context is unique inside a model.
   *
   * @param validModel
   *          the valid model
   */
  @Check(CheckType.FAST)
  public void checkUniqueNativeContextName(final ValidModel validModel) {

    // for each name store the element of its first occurrence
    final Map<String, NativeContext> firstOccurrenceOfName = new HashMap<String, NativeContext>();
    // the set of duplicate names
    final Set<String> duplicateNames = new HashSet<String>();

    for (final Category category : validModel.getCategories()) {
      for (final Rule rule : category.getRules()) {
        if (rule instanceof NativeRule) {
          for (final NativeContext context : ((NativeRule) rule).getContexts()) {
            final String name = getName(context);
            // if the name already occurred we have a duplicate name and hence an error
            final NativeContext firstContext = firstOccurrenceOfName.get(name);
            if (firstContext != null && firstContext.getContextType() == context.getContextType()) {
              duplicateNames.add(name);
              // note the second parameter t
              // it is essential difference to the first example
              error("Name " + name
                  + " must be unique for a given context type (introduce an \"as\" clause)", context, ValidPackage.Literals.NATIVE_CONTEXT__GIVEN_NAME, UNIQUE_NATIVE_CONTEXT_NAME);
            } else {
              // otherwise store the name as first occurrence
              firstOccurrenceOfName.put(name, context);
            }
          }
        }
      }
    }

    // now create the error for the first occurrence of a duplicate name
    for (final String duplicate : duplicateNames) {
      error("Name " + duplicate
          + " must be unique for a given context (introduce an \"as\" clause)", firstOccurrenceOfName.get(duplicate), ValidPackage.Literals.NATIVE_CONTEXT__GIVEN_NAME, UNIQUE_NATIVE_CONTEXT_NAME);
    }

  }

  /**
   * Check that the description of a category ends with a dot.
   *
   * @param category
   *          the category
   */
  @Check(CheckType.FAST)
  public void checkCategoryDescriptionEndsWithDot(final Category category) {
    guard(category.getDescription() != null);
    if (!category.getDescription().endsWith(DOT)) {
      warning(DESCRIPTION_SHOULD_END_WITH_A_DOT, ValidPackage.Literals.CATEGORY__DESCRIPTION, CATEGORY_DESCRIPTION_ENDS_WITH_DOT);
    }
  }

  /**
   * Check that the description of a rule ends with a dot.
   *
   * @param rule
   *          the rule
   */
  @Check(CheckType.FAST)
  public void checkRuleDescriptionEndsWithDot(final Rule rule) {
    guard(rule.getDescription() != null);
    if (!rule.getDescription().endsWith(DOT)) {
      warning(DESCRIPTION_SHOULD_END_WITH_A_DOT, ValidPackage.Literals.RULE__DESCRIPTION, RULE_DESCRIPTION_ENDS_WITH_DOT);
    }
  }

  /**
   * Check that the label of a category doesn't end with a dot.
   *
   * @param category
   *          the category
   */
  @Check(CheckType.FAST)
  public void checkCategoryLabelEndsWithDot(final Category category) {
    guard(category.getLabel().length() > 0);
    if (category.getLabel().endsWith(DOT)) {
      warning(LABEL_SHOULD_NOT_END_WITH_A_DOT, ValidPackage.Literals.CATEGORY__LABEL, CATEGORY_LABEL_ENDS_WITH_DOT);
    }
  }

  /**
   * Check that the label of a rule doesn't end with a dot.
   *
   * @param rule
   *          the rule
   */
  @Check(CheckType.FAST)
  public void checkRuleLabelEndsWithDot(final Rule rule) {
    guard(rule.getLabel().length() > 0);
    if (rule.getLabel().endsWith(DOT)) {
      warning(LABEL_SHOULD_NOT_END_WITH_A_DOT, ValidPackage.Literals.RULE__LABEL, RULE_LABEL_ENDS_WITH_DOT);
    }
  }

  /**
   * Check that the label of a quick fix doesn't end with a dot.
   *
   * @param quickFix
   *          the quick fix
   */
  @Check(CheckType.FAST)
  public void checkQuickFixLabelEndsWithDot(final QuickFix quickFix) {
    guard(quickFix.getLabel().length() > 0);

    if (quickFix.getLabel().endsWith(DOT)) {
      warning(LABEL_SHOULD_NOT_END_WITH_A_DOT, ValidPackage.Literals.QUICK_FIX__LABEL, QUICK_FIX_LABEL_ENDS_WITH_DOT);
    }
  }

}
