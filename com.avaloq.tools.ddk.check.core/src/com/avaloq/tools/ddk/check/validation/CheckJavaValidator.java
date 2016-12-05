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
package com.avaloq.tools.ddk.check.validation;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.ComposedChecks;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.typesystem.conformance.TypeConformanceComputationArgument;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.ContextVariable;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.SeverityKind;
import com.avaloq.tools.ddk.check.check.SeverityRange;
import com.avaloq.tools.ddk.check.check.XGuardExpression;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions;
import com.avaloq.tools.ddk.check.resource.CheckLocationInFileProvider;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * The Java validator for Check model.
 */
@ComposedChecks(validators = {ClasspathBasedChecks.class, FormalParameterChecks.class})
public class CheckJavaValidator extends AbstractCheckJavaValidator {

  @Inject
  private CheckGeneratorExtensions generatorExtensions;

  /** The validator utility used by both the Java validator and the wizards. */
  @Inject
  private CheckJavaValidatorUtil javaValidatorUtil;

  @Inject
  private CheckLocationInFileProvider locationInFileProvider;

  private final Set<EReference> checkTypeConformanceCheckedReferences = //
  ImmutableSet.of(CheckPackage.Literals.XGUARD_EXPRESSION__GUARD, //
      CheckPackage.Literals.XISSUE_EXPRESSION__MARKER_OBJECT, //
      CheckPackage.Literals.XISSUE_EXPRESSION__MARKER_INDEX, //
      CheckPackage.Literals.XISSUE_EXPRESSION__MESSAGE_PARAMETERS, //
      CheckPackage.Literals.XISSUE_EXPRESSION__ISSUE_DATA);

  @Override
  protected boolean isValueExpectedRecursive(final XExpression expr) {
    EObject container = expr.eContainer();
    if (container instanceof XIssueExpression || container instanceof XGuardExpression) {
      return true;
    }
    return super.isValueExpectedRecursive(expr);
  }

  /**
   * Checks that type of a default expression of a formal parameter actually matches the declared type.
   * 
   * @param parameter
   *          to check
   */
  @Check
  public void checkFormalParameterTypeConformance(final FormalParameter parameter) {
    JvmTypeReference jvmType = parameter.getType();
    XExpression value = parameter.getRight();
    if (jvmType == null || value == null) {
      return;
    }
    LightweightTypeReference declaredType = toLightweightTypeReference(jvmType);
    LightweightTypeReference valueType = getActualType(value);
    if (!declaredType.isAssignableFrom(valueType, new TypeConformanceComputationArgument())) {
      error(Messages.CheckJavaValidator_FormalParameterType_Incompatibility, value, null, IssueCodes.FORMAL_PARAMETER_TYPE);
    }
  }

  private static final Set<String> BASE_TYPE_NAMES = ImmutableSet.of("java.lang.String", "java.lang.Integer", "java.lang.Boolean"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  private static final Set<String> ALLOWED_TYPE_NAMES = ImmutableSet.<String> builder() //
  .addAll(BASE_TYPE_NAMES) //
  .add("boolean", "int") //
  .addAll(Iterables.transform(BASE_TYPE_NAMES, new Function<String, String>() {
    public String apply(final String base) {
      return "java.util.List<" + base + '>';
    }
  })) //
  .build();

  /**
   * Checks that the declared type of a formal parameter is one of: String, Boolean, Integer, boolean, int, or
   * a List type with String, Boolean, or Integer element type.
   * 
   * @param parameter
   *          to check
   */
  @Check
  public void checkFormalParameterDeclaredType(final FormalParameter parameter) {
    JvmTypeReference jvmType = parameter.getType();
    if (jvmType == null) {
      return;
    }
    String typeName = jvmType.getQualifiedName();
    if (!ALLOWED_TYPE_NAMES.contains(typeName)) {
      error(Messages.CheckJavaValidator_FormalParameterAllowedBaseTypes, jvmType, null, IssueCodes.FORMAL_PARAMETER_BASE_TYPE);
    }
  }

  /**
   * Gets duplicates of a given type based on a guard (predicate). A given function is used for converting an instance of type T
   * to a string which is used for checking for duplicates.
   * 
   * @param <T>
   *          the generic type
   * @param predicate
   *          the predicate acting as a guard
   * @param function
   *          returns a string for an instance of type T
   * @param elements
   *          the elements to be checked
   * @return the duplicates
   */
  private <T extends EObject> Iterable<T> getDuplicates(final Predicate<T> predicate, final Function<T, String> function, final Iterable<T> elements) {
    List<T> result = Lists.newArrayList();
    Multimap<String, T> multiMap = Multimaps.newMultimap(Maps.<String, Collection<T>> newHashMap(), new Supplier<Collection<T>>() {
      public Collection<T> get() {
        return Lists.<T> newArrayList();
      }
    });
    for (final T candidate : elements) {
      if (predicate.apply(candidate)) {
        multiMap.put(function.apply(candidate), candidate);
      }
    }
    for (String elem : multiMap.keySet()) {
      final Collection<T> duplicates = multiMap.get(elem);
      if (duplicates.size() > 1) {
        result.addAll(duplicates);
      }
    }

    return result;
  }

  /**
   * Creates an error or a warning depending on the severity of given status. Delegates to
   * {@link #warning(String, EObject, EStructuralFeature, String, String...)
   * warning} or {@link #error(String, EObject, EStructuralFeature, String, String...) error}.
   * 
   * @param status
   *          a status with severity ERROR or WARNING
   * @param message
   *          the issue message
   * @param context
   *          the context object
   * @param feature
   *          the structural feature
   * @param code
   *          the issue code
   * @param issueData
   *          the issue data
   */
  private void issue(final IStatus status, final String message, final EObject context, final EStructuralFeature feature, final String code, final String... issueData) {
    if (status.matches(IStatus.WARNING)) {
      warning(message, context, feature, code, issueData);
    } else if (status.matches(IStatus.ERROR)) {
      error(message, context, feature, code, issueData);
    }
  }

  public Set<EReference> getTypeConformanceCheckedReferences() {
    return checkTypeConformanceCheckedReferences;
  }

  /**
   * Checks that Check names are unique. A Check name may only occur once in a Check Catalog.
   * 
   * @param catalog
   *          the check catalog
   */
  @Check
  public void checkCheckNamesAreUnique(final CheckCatalog catalog) {
    Function<com.avaloq.tools.ddk.check.check.Check, QualifiedName> function = new Function<com.avaloq.tools.ddk.check.check.Check, QualifiedName>() {
      public QualifiedName apply(final com.avaloq.tools.ddk.check.check.Check from) {
        return QualifiedName.create(from.getName()); // no need to create a fully qualified name with check catalog here, it is only used for
                                                     // comparing check instances
      }
    };
    UniquenessJavaValidationHelper<com.avaloq.tools.ddk.check.check.Check> helper = //
    new UniquenessJavaValidationHelper<com.avaloq.tools.ddk.check.check.Check>(function, getMessageAcceptor()) {
      @Override
      public String getMessage(final com.avaloq.tools.ddk.check.check.Check duplicate) {
        return NLS.bind("Duplicate Check name: {0}", duplicate.getName()); //$NON-NLS-1$
      }
    };

    final Iterable<com.avaloq.tools.ddk.check.check.Check> allChecksWithName = Iterables.filter(catalog.getAllChecks(), new Predicate<com.avaloq.tools.ddk.check.check.Check>() {
      public boolean apply(final com.avaloq.tools.ddk.check.check.Check input) {
        return input.getName() != null;
      }
    });
    helper.errorOnDuplicates(allChecksWithName, CheckPackage.Literals.CHECK__LABEL, IssueCodes.DUPLICATE_CHECK);
    // TODO: the duplicate validation helper could also provide an interface that accepts a Function that calculates the feature
    // (in our case id or label) instead of a static feature
  }

  /**
   * Checks that Category names are unique across the Check Catalog.
   * 
   * @param catalog
   *          the check catalog
   */
  @Check
  public void checkCategoryNamesAreUnique(final CheckCatalog catalog) {
    Function<Category, QualifiedName> function = new Function<Category, QualifiedName>() {
      public QualifiedName apply(final Category from) {
        return QualifiedName.create(from.getName());
      }
    };
    UniquenessJavaValidationHelper<Category> helper = new UniquenessJavaValidationHelper<Category>(function, getMessageAcceptor()) {
      @Override
      public String getMessage(final Category duplicate) {
        return NLS.bind("Duplicate Category name: {0}", duplicate.getName()); //$NON-NLS-1$
      }
    };

    helper.errorOnDuplicates(catalog.getCategories(), CheckPackage.Literals.CATEGORY__LABEL, IssueCodes.DUPLICATE_CATEGORY);
  }

  /**
   * Checks that return expressions do not occur in check implementations.
   * 
   * @param expression
   *          the expression
   */
  @Check
  public void checkReturnExpressions(final XReturnExpression expression) {
    if (expression.getExpression() != null) {
      error(Messages.CheckJavaValidator_NO_RETURN_IN_CHECK_IMPL, XbasePackage.Literals.XRETURN_EXPRESSION__EXPRESSION, IssueCodes.RETURN_IN_IMPL);
    }
  }

  /**
   * Check that a marker index in an issue statement is only provided for multi-valued features.
   * 
   * @param expression
   *          the issue expression
   */
  @Check
  public void checkFeature(final XIssueExpression expression) {
    if (expression.getMarkerIndex() != null && expression.getMarkerFeature() != null && !expression.getMarkerFeature().eIsProxy()) {
      if (!expression.getMarkerFeature().isMany()) {// NOPMD readability
        error(Messages.CheckJavaValidator_MARKER_INDEX_MANY, CheckPackage.Literals.XISSUE_EXPRESSION__MARKER_INDEX, IssueCodes.MARKER_INDEX_MANY);
      }
    }
  }

  /**
   * Checks that a final check does not allow defining a severity range.
   * 
   * @param check
   *          the check to be checked
   */
  @Check
  public void checkSeverityRangesNotAllowedForFinalCheck(final com.avaloq.tools.ddk.check.check.Check check) {
    if (check.isFinal() && check.getSeverityRange() != null) {
      error(Messages.CheckJavaValidator_NO_SEVERITY_RANGE_FOR_FINAL_CHECK, check, CheckPackage.Literals.CHECK__FINAL, IssueCodes.SEVERITY_RANGES_FOR_FINAL_CHECK);
    }
  }

  /**
   * Checks that checks are documented.
   * 
   * @param check
   *          the check
   */
  @Check
  public void checkDocumentationOnCheck(final com.avaloq.tools.ddk.check.check.Check check) {
    if (check.getName() != null) {
      final String documentation = check.getDescription();
      if (Strings.isEmpty(documentation)) {
        warning(NLS.bind(Messages.CheckJavaValidator_DOCUMENTATION_MISSING, "Check", check.getName()), locationInFileProvider.getIdentifierFeature(check), IssueCodes.MISSING_DOCUMENTATION_ON_CHECK); //$NON-NLS-1$
      }
    }
  }

  /**
   * Checks that implementations are documented.
   * 
   * @param implementation
   *          the implementation
   */
  @Check
  public void checkDocumentationOnImplementation(final Implementation implementation) {
    if (implementation.getName() != null) {
      final String documentation = implementation.getDescription();
      if (Strings.isEmpty(documentation)) {
        warning(NLS.bind(Messages.CheckJavaValidator_DOCUMENTATION_MISSING, "Definition", implementation.getName()), CheckPackage.Literals.IMPLEMENTATION__NAME, IssueCodes.MISSING_DOCUMENTATION_ON_IMPLEMENTATION); //$NON-NLS-1$
      }
    }
  }

  /**
   * Verifies that the given catalog can be interpreted as Java identifier.
   * 
   * @param catalog
   *          the catalog to validate
   */
  @Check
  public void checkCatalogName(final CheckCatalog catalog) {
    if (!Strings.isEmpty(catalog.getName())) {
      IStatus status = javaValidatorUtil.checkCatalogName(catalog.getName());
      if (!status.isOK()) {
        issue(status, status.getMessage(), catalog, CheckPackage.Literals.CHECK_CATALOG__NAME, IssueCodes.INVALID_CATALOG_NAME);
      }
    }
  }

  /**
   * Verifies that the given category can be interpreted as Java identifier.
   * 
   * @param category
   *          the category to validate
   */
  @Check
  public void checkCategoryName(final Category category) {
    if (!Strings.isEmpty(category.getName())) {
      IStatus status = javaValidatorUtil.checkCategoryName(category.getName());
      if (!status.isOK()) {
        issue(status, status.getMessage(), category, locationInFileProvider.getIdentifierFeature(category), IssueCodes.INVALID_CATEGORY_NAME);
      }
    }
  }

  /**
   * Checks that given package name is a valid fully qualified name.
   * 
   * @see {@link CheckJavaValidatorUtil#isValidFullyQualifiedName(String)}
   * @param catalog
   *          the catalog
   */
  @Check
  public void checkPackageName(final CheckCatalog catalog) {
    IStatus status = javaValidatorUtil.checkPackageName(catalog.getPackageName());
    if (!status.isOK()) {
      issue(status, status.getMessage(), catalog, CheckPackage.Literals.CHECK_CATALOG__PACKAGE_NAME, IssueCodes.INVALID_PACKAGE_NAME);
    }
  }

  /**
   * Verifies that the given check can be interpreted as Java identifier.
   * 
   * @param check
   *          the check to validate
   */
  @Check
  public void checkCheckName(final com.avaloq.tools.ddk.check.check.Check check) {
    IStatus status = javaValidatorUtil.checkCheckName(check.getName());
    if (!status.isOK()) {
      issue(status, status.getMessage(), check, locationInFileProvider.getIdentifierFeature(check), IssueCodes.INVALID_CHECK_NAME);
    }
  }

  /**
   * Checks catalogs for circular dependencies in included catalogs. A catalog cannot include itself and may not include another catalog which includes the
   * current one.
   * 
   * @param catalog
   *          the catalog to be checked
   */
  @Check
  public void checkCircularDependency(final CheckCatalog catalog) {
    if (catalog.getIncludedCatalogs() == null) {
      return;
    }
    final Set<CheckCatalog> visitedCatalogs = Sets.newHashSet(catalog);
    CheckCatalog current = catalog.getIncludedCatalogs();
    while (current != null) {
      if (visitedCatalogs.add(current)) {
        current = current.getIncludedCatalogs();
      } else {
        error(Messages.CheckJavaValidator_CIRCULAR_DEPENDENCY_IN_INCLUDED_CATALOGS, catalog, CheckPackage.Literals.CHECK_CATALOG__INCLUDED_CATALOGS, IssueCodes.INCLUDED_CATALOGS_WITH_CIRCULAR_DEPENDENCIES);
        break;
      }
    }
  }

  /**
   * Recursively gets all included grammar names.
   * 
   * @param grammar
   *          the grammar to start the search with
   * @return the set of all grammar names
   */
  private Set<String> getAllUsedGrammarNames(final Grammar grammar) {
    return Sets.newHashSet(Iterables.transform(GrammarUtil.allUsedGrammars(grammar), new Function<Grammar, String>() {
      public String apply(final Grammar grammar) {
        return grammar.getName();
      }
    }));
  }

  /**
   * Checks that an included catalog is configured for the same grammar as given catalog.
   * 
   * @param catalog
   *          the catalog to be checked
   */
  @Check
  public void checkLanguageOfIncludedCatalog(final CheckCatalog catalog) {
    if (catalog.getIncludedCatalogs() == null || catalog.getIncludedCatalogs().getGrammar() == null || catalog.getGrammar() == null) {
      return;
    }
    final Grammar grammar = catalog.getGrammar();

    Set<String> allUsedGrammarNames = getAllUsedGrammarNames(grammar);

    final Grammar includedGrammar = catalog.getIncludedCatalogs().getGrammar();
    if (!includedGrammar.getName().equals(grammar.getName()) && !(allUsedGrammarNames.contains(includedGrammar.getName()))) {
      error(NLS.bind(Messages.CheckJavaValidator_INVALID_GRAMMAR_OF_INCLUDED_CATALOG, includedGrammar.getName(), grammar.getName()), catalog, CheckPackage.Literals.CHECK_CATALOG__INCLUDED_CATALOGS, IssueCodes.INCLUDED_CATALOG_GRAMMAR_MISMATCH);
    }
  }

  /**
   * Checks that an issue expression refers to check.
   * This check is either implicit (inside a context) or explicit (inside an independent implementation)
   * 
   * @param expression
   *          the expression
   */
  @Check
  public void checkIssuedCheck(final XIssueExpression expression) {
    if (generatorExtensions.issuedCheck(expression) == null) {
      error(Messages.CheckJavaValidator_ISSUE_REFERS_TO_CHECK, CheckPackage.Literals.XISSUE_EXPRESSION__CHECK, IssueCodes.ISSUED_CHECK);
    }
  }

  /**
   * Checks that an issue expression provides all required bindings.
   * 
   * @param expression
   *          the expression
   */
  @Check
  public void checkIssuedBindings(final XIssueExpression expression) {
    if (generatorExtensions.issuedCheck(expression) == null) {
      return;
    }
    int boundParameters = expression.getMessageParameters().size();
    String message = generatorExtensions.issuedCheck(expression).getMessage();
    if (Strings.isEmpty(message)) {
      return;
    }
    try {
      int requiredBindings = new MessageFormat(message).getFormatsByArgumentIndex().length;

      if (boundParameters != requiredBindings) {
        error(NLS.bind(Messages.CheckJavaValidator_ISSUED_BINDINGS, requiredBindings, boundParameters), CheckPackage.Literals.XISSUE_EXPRESSION__MESSAGE_PARAMETERS, IssueCodes.ISSUED_BINDINGS);
      }
      // CHECKSTYLE:OFF
    } catch (IllegalArgumentException e) {
      // CHECKSTYLE:ON
      // do nothing here, because we can have potentially wrong messages given to MessageFormat, e.g. "{}"
    }

  }

  /**
   * Checks that no explicit .
   * This check is either implicit (inside a context) or explicit (inside an independent implementation)
   * 
   * @param expression
   *          the expression
   */
  @Check
  public void checkImplicitIssuedCheck(final XIssueExpression expression) {
    com.avaloq.tools.ddk.check.check.Check container = EcoreUtil2.getContainerOfType(expression, com.avaloq.tools.ddk.check.check.Check.class);
    if (container == null) {
      return;
    }

    if (expression.getCheck() != null && !expression.getCheck().eIsProxy()) {
      if (expression.getCheck() == container) {
        // if the issued check is that of the containing issue, then we only emit a warning
        warning(Messages.CheckJavaValidator_ISSUE_REFERS_TO_IMPLICIT_CHECK, CheckPackage.Literals.XISSUE_EXPRESSION__CHECK, IssueCodes.IMPLICIT_ISSUED_CHECK);
      } else {
        error(Messages.CheckJavaValidator_ISSUE_REFERS_TO_EXPLICIT_CHECK, CheckPackage.Literals.XISSUE_EXPRESSION__CHECK, IssueCodes.IMPLICIT_ISSUED_CHECK);
      }
    }
  }

  /**
   * Checks that at least one issue expression exists in a context's constraints.
   * <p>
   * To be executed on save only as the error will otherwise be distracting.
   * </p>
   * 
   * @param context
   *          the context to be checked
   */
  @Check(CheckType.NORMAL)
  public void checkIssueExpressionExists(final Context context) {
    if (context.getContextVariable() == null || context.getConstraint() == null) {
      return;
    }

    if (Iterables.isEmpty(generatorExtensions.issues(context))) {
      error(Messages.CheckJavaValidator_MISSING_ISSUE_EXPRESSION, context, CheckPackage.Literals.CONTEXT__CONSTRAINT, IssueCodes.MISSING_ISSUE_EXPRESSION);
    }
  }

  /**
   * Issues a warning that guard statements are deprecated.
   * 
   * @param context
   *          the guard expression
   */
  @Check
  public void checkGuardsDeprecated(final XGuardExpression context) {
    warning(Messages.CheckJavaValidator_GUARDS_DEPRECATED, context, null, IssueCodes.GUARDS_DEPRECATED);
  }

  /**
   * Checks that if guards have been provided as part of a context's constraint, they appear in the
   * beginning of a block expression.
   * 
   * @param context
   *          the context for which the constraint is checked
   */
  @SuppressWarnings("unchecked")
  @Check
  public void checkGuardsFirst(final Context context) {
    if (context.getContextVariable() == null || context.getConstraint() == null) {
      return;
    }

    List<XGuardExpression> guards = EcoreUtil2.eAllOfType(context.getConstraint(), XGuardExpression.class);
    if (Iterables.isEmpty(guards)) {
      return; // no guards makes check irrelevant
    }

    EList<XExpression> topLevelExpressions = (EList<XExpression>) ((XBlockExpression) context.getConstraint()).eGet(XbasePackage.Literals.XBLOCK_EXPRESSION__EXPRESSIONS);

    for (final XGuardExpression guard : guards) {
      final EStructuralFeature eContainingFeature = guard.eContainingFeature();
      if (guard.eContainer() != null && eContainingFeature != null) {
        if (guard.eContainer().eGet(eContainingFeature) != topLevelExpressions) {
          // guards not allowed in nested expressions, must be on root level
          error(Messages.CheckJavaValidator_GUARDS_COME_FIRST, guard, null, IssueCodes.GUARDS_COME_FIRST);
        } else {
          // check that guards precede other expressions
          int pos = topLevelExpressions.indexOf(guard);
          if (pos > 0) {
            for (int i = 0; i < pos; i++) {
              if (!(topLevelExpressions.get(i) instanceof XGuardExpression)) {
                error(Messages.CheckJavaValidator_GUARDS_COME_FIRST, guard, null, IssueCodes.GUARDS_COME_FIRST);
                break;
              }
            }
          }
        }
      }
    }
  }

  /**
   * Checks that a context's context type is an EClass.
   * 
   * @param context
   *          the context
   */
  @Check
  public void checkContextTypeIsEClass(final ContextVariable context) {
    final JvmTypeReference contextType = context.getType();
    if (contextType == null) {
      // Check only applicable if context type has been defined
      return;
    }
    JvmType type = contextType.getType();
    try {
      if (type != null && !EObject.class.isAssignableFrom(Class.forName(type.getIdentifier()))) {
        error(Messages.CheckJavaValidator_CONTEXT_TYPE_IS_ECLASS, context, CheckPackage.Literals.CONTEXT_VARIABLE__TYPE, IssueCodes.CONTEXT_TYPE_IS_ECLASS);
      }
      // CHECKSTYLE:OFF
    } catch (ClassNotFoundException e) {
      // CHECKSTYLE:ON
    }
  }

  /**
   * Checks that a check's context types are all unique.
   * 
   * @param check
   *          the check
   */
  // TODO there is no reason for a context type to be unique, except that we are too lazy to generate the right code, right?
  @Check
  public void checkContextTypeIsUnique(final com.avaloq.tools.ddk.check.check.Check check) {
    if (check.getContexts().size() < 1) {
      return;
    }
    Multimap<String, Context> mm = Multimaps.newMultimap(Maps.<String, Collection<Context>> newHashMap(), new Supplier<Collection<Context>>() {
      public Collection<Context> get() {
        return Lists.newArrayList();
      }
    });
    for (final Context c : check.getContexts()) {
      final ContextVariable var = c.getContextVariable();
      if (var != null) {
        final JvmTypeReference contextType = var.getType();
        if (contextType != null && contextType.getType() != null && !contextType.getType().eIsProxy()) {
          mm.put(contextType.getType().getIdentifier(), c);
        }
      }
    }
    for (String type : mm.keys()) {
      final Collection<Context> duplicatesForType = mm.get(type);
      if (duplicatesForType.size() > 1) {
        for (final Context duplicateContext : duplicatesForType) {
          error(Messages.CheckJavaValidator_CONTEXT_TYPES_UNIQUE, duplicateContext.getContextVariable(), //
              CheckPackage.Literals.CONTEXT_VARIABLE__TYPE, IssueCodes.CONTEXT_TYPES_NOT_UNIQUE);
        }
      }
    }
  }

  /**
   * Checks that a Check defines parameters with unique names.
   * 
   * @param check
   *          the check to be checked
   */
  @Check
  public void checkFormalParameterNamesUnique(final com.avaloq.tools.ddk.check.check.Check check) {
    if (check.getFormalParameters().size() < 2) {
      return;
    }
    Function<FormalParameter, String> function = new Function<FormalParameter, String>() {
      public String apply(final FormalParameter from) {
        return from.getName();
      }
    };
    for (final FormalParameter p : getDuplicates(Predicates.<FormalParameter> alwaysTrue(), function, check.getFormalParameters())) {
      error(Messages.CheckJavaValidator_DUPLICATE_PARAMETER_DEFINITION, p, XbasePackage.Literals.XVARIABLE_DECLARATION__NAME, ValidationMessageAcceptor.INSIGNIFICANT_INDEX, IssueCodes.DUPLICATE_PARAMETER_DEFINITION);
    }

  }

  /**
   * Check severity range order.
   * 
   * @param check
   *          the check
   */
  @Check
  public void checkSeverityRangeOrder(final com.avaloq.tools.ddk.check.check.Check check) {
    if (check.getSeverityRange() == null) {
      return; // only check checks with severity range
    }
    final SeverityRange range = check.getSeverityRange();
    if (range.getMinSeverity().compareTo(range.getMaxSeverity()) < 0) {
      error(NLS.bind(Messages.CheckJavaValidator_ILLEGAL_SEVERITY_RANGE_ORDER, range.getMaxSeverity().getName(), range.getMinSeverity().getName()), check.getSeverityRange(), null, IssueCodes.ILLEGAL_SEVERITY_RANGE_ORDER);
    }
  }

  /**
   * Checks that the default severity is within given severity range. There must not be a conflict such
   * that the severity range defines severities do not contain the default.
   * <p>
   * Note that this check works even if {@link #checkSeverityRangeOrder(com.avaloq.tools.ddk.check.check.Check)} is violated.
   * </p>
   * 
   * @param check
   *          the check
   */
  @Check
  public void checkDefaultSeverityInRange(final com.avaloq.tools.ddk.check.check.Check check) {
    if (check.getSeverityRange() == null) {
      return; // only applicable if both range and default severity given
    }
    final SeverityRange range = check.getSeverityRange();
    final SeverityKind minSeverity = SeverityKind.get(Math.min(range.getMinSeverity().getValue(), range.getMaxSeverity().getValue()));
    final SeverityKind maxSeverity = SeverityKind.get(Math.max(range.getMinSeverity().getValue(), range.getMaxSeverity().getValue()));
    if (check.getDefaultSeverity().compareTo(minSeverity) < 0 || check.getDefaultSeverity().compareTo(maxSeverity) > 0) {
      List<String> issueCodes = Lists.newArrayList();
      SeverityKind temp = minSeverity;
      while (temp != null && temp.compareTo(maxSeverity) <= 0) {
        issueCodes.add(temp.getName());
        temp = com.avaloq.tools.ddk.check.check.SeverityKind.get(temp.getValue() + 1);
      }

      String[] codes = issueCodes.toArray(new String[issueCodes.size()]);
      error(Messages.CheckJavaValidator_DEFAULT_SEVERITY_NOT_IN_RANGE, check, CheckPackage.Literals.CHECK__DEFAULT_SEVERITY, IssueCodes.DEFAULT_SEVERITY_NOT_IN_RANGE, issueCodes.isEmpty() ? null // NOPMD
          : codes);
    }
  }

  /**
   * Checks that checks in <em>final</em> catalogs have no redundant properties. For instance, a check is
   * implicitly final if the container catalog is final.
   * 
   * @param catalog
   *          the catalog
   */
  @Check
  public void checkCheckInFinalCatalog(final CheckCatalog catalog) {
    if (!catalog.isFinal() || catalog.getAllChecks().isEmpty()) {
      return;
    }

    for (final com.avaloq.tools.ddk.check.check.Check check : catalog.getAllChecks()) {
      if (check.isFinal()) {
        warning(Messages.CheckJavaValidator_CHECK_IMPLICITLY_FINAL, check, CheckPackage.Literals.CHECK__FINAL, IssueCodes.CHECK_IMPLICITLY_FINAL);
      }
    }
  }

}

