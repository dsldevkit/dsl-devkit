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

package com.avaloq.tools.ddk.xtext.scope.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensionsX;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.avaloq.tools.ddk.xtext.scope.ScopeUtil;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.google.inject.Inject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ScopeProviderX {

  @Inject
  private Naming naming;
  @Inject
  private GeneratorUtilX generatorUtilX;
  @Inject
  private CodeGenerationX codeGenerationX;
  @Inject
  private ExpressionExtensionsX expressionExtensionsX;

  /*
   * CODE GENERATION
   */

  /**
   * Returns the fully qualified name of the scope provider for the given model.
   *
   * @param model
   *          the scope model
   * @return the scope provider name
   */
  public String getScopeProvider(final ScopeModel model) {
    return naming.toJavaPackage(model.getName()) + ".scoping." + naming.toSimpleName(model.getName()) + "ScopeProvider";
  }

  /**
   * Returns the fully qualified name of the scope name provider for the given model.
   *
   * @param model
   *          the scope model
   * @return the scope name provider name
   */
  public String getScopeNameProvider(final ScopeModel model) {
    return naming.toJavaPackage(model.getName()) + ".scoping." + naming.toSimpleName(model.getName()) + "ScopeNameProvider";
  }

  /**
   * Returns the name of the scope method generated for the given scope definition.
   *
   * @param it
   *          the scope definition
   * @return the scope method name
   */
  public String scopeMethodName(final ScopeDefinition it) {
    return getScopeName(it) + "_" + (it.getTargetType() != null ? it.getTargetType().getEPackage().getName() + "_" + it.getTargetType().getName() : it.getContextType().getEPackage().getName() + "_" + it.getContextType().getName() + "_" + it.getReference().getName());
  }

  /**
   * Returns the locator string for the given object.
   *
   * @param it
   *          the EObject
   * @return the encoded locator string
   */
  public String locatorString(final EObject it) {
    final String location = generatorUtilX.location(it);
    final String[] parts = location.split("/");
    final String last = parts.length > 0 ? parts[parts.length - 1] : null;
    return codeGenerationX.javaEncode(last);
  }

  /**
   * Returns the called feature name from a feature call.
   *
   * @param it
   *          the feature call
   * @return the called feature name
   */
  public String calledFeature(final FeatureCall it) {
    return it.getType().getId().get(0);
  }

  /**
   * Returns the structural feature for the given feature call.
   *
   * @param it
   *          the feature call
   * @return the structural feature
   */
  public EStructuralFeature feature(final FeatureCall it) {
    return scopeType(it).getEStructuralFeature(calledFeature(it));
  }

  /*
   * SCOPE RULES
   */
  // dispatch allScopeRules
  protected List<ScopeRule> _allScopeRules(final Void it) {
    return new ArrayList<>();
  }

  protected List<ScopeRule> _allScopeRules(final ScopeDefinition it) {
    return collectAllScopeRules(getModel(it), it);
  }

  /**
   * Returns all scope rules for the given scope definition.
   *
   * @param it
   *          the scope definition
   * @return the list of all scope rules
   */
  public List<ScopeRule> allScopeRules(final ScopeDefinition it) {
    if (it != null) {
      return _allScopeRules(it);
    } else {
      return _allScopeRules((Void) null);
    }
  }

  /**
   * Collects all scope rules from the model and included scopes.
   *
   * @param it
   *          the scope model
   * @param def
   *          the scope definition to match
   * @return the collected scope rules
   */
  public List<ScopeRule> collectAllScopeRules(final ScopeModel it, final ScopeDefinition def) {
    final List<ScopeRule> myScopeRules = new ArrayList<>();
    for (final ScopeDefinition d : it.getScopes()) {
      if (isEqual(d, def)) {
        myScopeRules.addAll(d.getRules());
      }
    }
    final List<ScopeRule> result = new ArrayList<>();
    if (!it.getIncludedScopes().isEmpty()) {
      for (final ScopeModel included : it.getIncludedScopes()) {
        result.addAll(collectAllScopeRules(included, def));
      }
    }
    result.addAll(myScopeRules);
    return result;
  }

  /**
   * Returns the rules sorted.
   *
   * @param it
   *          the collection of scope rules
   * @return the sorted list of rules
   */
  public List<ScopeRule> sortedRules(final Collection<ScopeRule> it) {
    return ScopingGeneratorUtil.sortedRules(it);
  }

  /**
   * Filters the unique rules from the list.
   *
   * @param it
   *          the list of scope rules
   * @return the set of unique rules
   */
  public Set<ScopeRule> filterUniqueRules(final List<ScopeRule> it) {
    return it.stream()
        .map(r -> it.stream().filter(r2 -> hasSameContext(r2, r)).findFirst().orElse(null))
        .collect(Collectors.toSet());
  }

  // dispatch isEqual(ScopeRule, ScopeRule)
  protected boolean _isEqual(final ScopeRule a, final ScopeRule b) {
    return hasSameContext(a, b)
        // && ((a.name === null) == (b.name === null)) && (a.name === null || a.name.matches (b.name))
        && Objects.equals(expressionExtensionsX.serialize(a.getContext().getGuard()), expressionExtensionsX.serialize(b.getContext().getGuard()));
  }

  /**
   * Returns whether two scope rules have the same context.
   *
   * @param a
   *          the first scope rule
   * @param b
   *          the second scope rule
   * @return true if the rules have the same context
   */
  public boolean hasSameContext(final ScopeRule a, final ScopeRule b) {
    return ruleSignature(a).equals(ruleSignature(b));
  }

  // Hrmph. Use naming here, otherwise we'll get strange (and wrong) results in the GenerateAllAPSLs workflow for netwStruct?!
  private /*cached*/ String ruleSignature(final ScopeRule s) {
    return ScopeUtil.getSignature(s);
  }

  /*
   * SCOPE DEFINITIONS
   */
  // returns the list of all local and inherited scope definition (skipping any shadowed or extended scope definitions)
  // dispatch allScopes
  protected List<ScopeDefinition> _allScopes(final ScopeModel it) {
    final List<ScopeDefinition> myScopes = it.getScopes();
    final List<ScopeDefinition> result = new ArrayList<>();
    if (!it.getIncludedScopes().isEmpty()) {
      for (final ScopeModel included : it.getIncludedScopes()) {
        result.addAll(allScopes(included));
      }
    }
    result.removeIf(s -> hasScope(myScopes, s));
    result.addAll(myScopes);
    return result;
  }

  protected List<ScopeDefinition> _allScopes(final Void it) {
    return new ArrayList<>();
  }

  /**
   * Returns the list of all local and inherited scope definitions.
   *
   * @param it
   *          the scope model
   * @return the list of all scope definitions
   */
  public List<ScopeDefinition> allScopes(final ScopeModel it) {
    if (it != null) {
      return _allScopes(it);
    } else {
      return _allScopes((Void) null);
    }
  }

  /**
   * Returns the scope name for the given definition.
   *
   * @param it
   *          the scope definition
   * @return the scope name, or "scope" if not set
   */
  public String getScopeName(final ScopeDefinition it) {
    if (it.getName() == null) {
      return "scope";
    } else {
      return it.getName();
    }
  }

  /**
   * Returns whether the list contains a scope equal to the given scope.
   *
   * @param list
   *          the list of scope definitions
   * @param scope
   *          the scope definition to check
   * @return true if the list contains an equal scope
   */
  public boolean hasScope(final List<ScopeDefinition> list, final ScopeDefinition scope) {
    return !list.isEmpty() && list.stream().anyMatch(s -> isEqual(s, scope));
  }

  // dispatch isEqual(ScopeDefinition, ScopeDefinition)
  protected boolean _isEqual(final ScopeDefinition a, final ScopeDefinition b) {
    return getScopeName(a).equals(getScopeName(b)) && isEqual(a.getTargetType(), b.getTargetType()) && isEqual(a.getReference(), b.getReference());
  }

  /*
   * SCOPE TYPE
   */
  // dispatch scopeType
  protected EClass _scopeType(final ScopeDefinition it) {
    if (it.getReference() != null) {
      return it.getReference().getEReferenceType();
    } else {
      return it.getTargetType();
    }
  }

  protected EClass _scopeType(final ScopeRule it) {
    return scopeType(getScope(it));
  }

  protected EClass _scopeType(final Expression it) {
    if (getScope(it) != null) {
      return scopeType(getScope(it));
    } else {
      return getNamingDef(it).getType();
    }
  }

  /**
   * Returns the scope type for the given object.
   *
   * @param it
   *          the EObject (ScopeDefinition, ScopeRule, or Expression)
   * @return the scope EClass type
   * @throws IllegalArgumentException
   *           if the parameter type is not handled
   */
  public EClass scopeType(final EObject it) {
    if (it instanceof ScopeDefinition scopeDefinition) {
      return _scopeType(scopeDefinition);
    } else if (it instanceof ScopeRule scopeRule) {
      return _scopeType(scopeRule);
    } else if (it instanceof Expression expression) {
      return _scopeType(expression);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + it);
    }
  }

  /**
   * Returns the type or reference of the scope definition.
   *
   * @param it
   *          the scope definition
   * @return the type or reference
   */
  public ENamedElement typeOrRef(final ScopeDefinition it) {
    if (it.getReference() != null) {
      return it.getReference();
    } else {
      return it.getTargetType();
    }
  }

  /**
   * Returns the context reference of the scope rule.
   *
   * @param it
   *          the scope rule
   * @return the context reference
   */
  public EReference contextRef(final ScopeRule it) {
    return getScope(it).getReference();
  }

  /*
   * Injections
   */
  // returns the list of all local and inherited injections (skipping any shadowed injections)
  // dispatch allInjections
  protected List<Injection> _allInjections(final ScopeModel it) {
    final List<Injection> myInjections = it.getInjections();
    final List<Injection> result = new ArrayList<>();
    if (!it.getIncludedScopes().isEmpty()) {
      for (final ScopeModel included : it.getIncludedScopes()) {
        result.addAll(allInjections(included));
      }
    }
    result.removeIf(i -> hasInjection(myInjections, i));
    result.addAll(myInjections);
    return result;
  }

  protected List<Injection> _allInjections(final Void it) {
    return new ArrayList<>();
  }

  /**
   * Returns the list of all local and inherited injections.
   *
   * @param it
   *          the scope model
   * @return the list of all injections
   */
  public List<Injection> allInjections(final ScopeModel it) {
    if (it != null) {
      return _allInjections(it);
    } else {
      return _allInjections((Void) null);
    }
  }

  /**
   * Returns whether the list contains an injection equal to the given injection.
   *
   * @param list
   *          the list of injections
   * @param injection
   *          the injection to check
   * @return true if the list contains an equal injection
   */
  public boolean hasInjection(final List<Injection> list, final Injection injection) {
    return !list.isEmpty() && list.stream().anyMatch(i -> isEqual(i, injection));
  }

  // dispatch isEqual(Injection, Injection)
  protected boolean _isEqual(final Injection a, final Injection b) {
    return a.getType().equals(b.getType()) && a.getName().equals(b.getName());
  }

  /*
   * SCOPE EXPRESSIONS
   */

  /**
   * Returns whether the named scope expression is case insensitive.
   *
   * @param it
   *          the named scope expression
   * @return true if case insensitive
   */
  public boolean isCaseInsensitive(final NamedScopeExpression it) {
    return ScopingGeneratorUtil.isCaseInsensitive(it);
  }

  /*
   * ECONTAINER
   */

  /**
   * Returns the scope model from the given object's resource.
   *
   * @param it
   *          the EObject
   * @return the scope model
   */
  public ScopeModel getModel(final EObject it) {
    return (ScopeModel) it.eResource().getContents().get(0);
  }

  /**
   * Returns the enclosing scope definition for the given object.
   *
   * @param it
   *          the EObject
   * @return the enclosing scope definition
   */
  public /*cached*/ ScopeDefinition getScope(final EObject it) {
    return eContainer(it, ScopeDefinition.class);
  }

  /**
   * Returns the enclosing naming definition for the given object.
   *
   * @param it
   *          the EObject
   * @return the enclosing naming definition
   */
  public /*cached*/ NamingDefinition getNamingDef(final EObject it) {
    return eContainer(it, NamingDefinition.class);
  }

  /**
   * Finds the nearest ancestor of the given type.
   *
   * @param <T>
   *          the ancestor type
   * @param it
   *          the EObject
   * @param type
   *          the class of the ancestor type
   * @return the nearest ancestor of the given type, or null
   */
  public <T extends EObject> T eContainer(final EObject it, final Class<T> type) {
    if (it == null) {
      return null;
    } else if (type.isInstance(it)) {
      @SuppressWarnings("unchecked")
      final T result = (T) it;
      return result;
    } else {
      return eContainer(it.eContainer(), type);
    }
  }

  /*
   * ECORE
   */
  // dispatch isEqual(EClass, EClass)
  // CHECKSTYLE:CHECK-OFF BooleanExpressionComplexity
  protected boolean _isEqual(final EClass a, final EClass b) {
    return a == b || (a != null && b != null && a.getName().equals(b.getName()) && a.getEPackage().getNsURI().equals(b.getEPackage().getNsURI()));
  }
  // CHECKSTYLE:CHECK-ON BooleanExpressionComplexity

  protected boolean _isEqualVoidVoid(final Void a, final Void b) {
    return true;
  }

  protected boolean _isEqualObjectVoid(final EObject a, final Void b) {
    return false;
  }

  protected boolean _isEqualVoidObject(final Void a, final EObject b) {
    return false;
  }

  // dispatch isEqual(EReference, EReference)
  // CHECKSTYLE:CHECK-OFF BooleanExpressionComplexity
  protected boolean _isEqual(final EReference a, final EReference b) {
    return a == b || (a != null && b != null && a.getName().equals(b.getName()) && isEqual(a.getEContainingClass(), b.getEContainingClass()));
  }
  // CHECKSTYLE:CHECK-ON BooleanExpressionComplexity

  /**
   * Public dispatcher for isEqual - handles all type combinations.
   *
   * @param a
   *          the first object
   * @param b
   *          the second object
   * @return true if the two objects are considered equal
   * @throws IllegalArgumentException
   *           if the parameter types are not handled
   */
  public boolean isEqual(final Object a, final Object b) {
    if (a instanceof ScopeRule ruleA && b instanceof ScopeRule ruleB) {
      return _isEqual(ruleA, ruleB);
    } else if (a instanceof ScopeDefinition defA && b instanceof ScopeDefinition defB) {
      return _isEqual(defA, defB);
    } else if (a instanceof Injection injA && b instanceof Injection injB) {
      return _isEqual(injA, injB);
    } else if (a instanceof EReference refA && b instanceof EReference refB) {
      return _isEqual(refA, refB);
    } else if (a instanceof EClass classA && b instanceof EClass classB) {
      return _isEqual(classA, classB);
    } else if (a == null && b == null) {
      return _isEqualVoidVoid(null, null);
    } else if (a instanceof EObject && b == null) {
      return _isEqualObjectVoid((EObject) a, null);
    } else if (a == null && b instanceof EObject) {
      return _isEqualVoidObject(null, (EObject) b);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " + a + ", " + b);
    }
  }
}
