/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
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
  public String getScopeProvider(final ScopeModel model) {
    return naming.toJavaPackage(model.getName()) + ".scoping." + naming.toSimpleName(model.getName()) + "ScopeProvider";
  }

  public String getScopeNameProvider(final ScopeModel model) {
    return naming.toJavaPackage(model.getName()) + ".scoping." + naming.toSimpleName(model.getName()) + "ScopeNameProvider";
  }

  // returns the name of the scope method generated for the given scope definition
  public String scopeMethodName(final ScopeDefinition it) {
    return getScopeName(it) + "_" + (it.getTargetType() != null ? it.getTargetType().getEPackage().getName() + "_" + it.getTargetType().getName() : it.getContextType().getEPackage().getName() + "_" + it.getContextType().getName() + "_" + it.getReference().getName());
  }

  public String locatorString(final EObject it) {
    final String location = generatorUtilX.location(it);
    final String[] parts = location.split("/");
    final String last = parts.length > 0 ? parts[parts.length - 1] : null;
    return codeGenerationX.javaEncode(last);
  }

  public String calledFeature(final FeatureCall it) {
    return it.getType().getId().get(0);
  }

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

  public List<ScopeRule> allScopeRules(final ScopeDefinition it) {
    if (it != null) {
      return _allScopeRules(it);
    } else {
      return _allScopeRules((Void) null);
    }
  }

  public List<ScopeRule> collectAllScopeRules(final ScopeModel it, final ScopeDefinition def) {
    final List<ScopeRule> myScopeRules = new ArrayList<>();
    for (final ScopeDefinition d : it.getScopes()) {
      if (isEqual(d, def)) {
        myScopeRules.addAll(d.getRules());
      }
    }
    final List<ScopeRule> result;
    if (it.getIncludedScopes().isEmpty()) {
      result = new ArrayList<>();
    } else {
      result = new ArrayList<>();
      for (final ScopeModel included : it.getIncludedScopes()) {
        result.addAll(collectAllScopeRules(included, def));
      }
    }
    result.addAll(myScopeRules);
    return result;
  }

  public List<ScopeRule> sortedRules(final Collection<ScopeRule> it) {
    return ScopingGeneratorUtil.sortedRules(it);
  }

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
    final List<ScopeDefinition> result;
    if (it.getIncludedScopes().isEmpty()) {
      result = new ArrayList<>();
    } else {
      result = new ArrayList<>();
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

  public List<ScopeDefinition> allScopes(final ScopeModel it) {
    if (it != null) {
      return _allScopes(it);
    } else {
      return _allScopes((Void) null);
    }
  }

  public String getScopeName(final ScopeDefinition it) {
    if (it.getName() == null) {
      return "scope";
    } else {
      return it.getName();
    }
  }

  public boolean hasScope(final List<ScopeDefinition> list, final ScopeDefinition scope) {
    if (list.isEmpty()) {
      return false;
    } else {
      return list.stream().anyMatch(s -> isEqual(s, scope));
    }
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

  public ENamedElement typeOrRef(final ScopeDefinition it) {
    if (it.getReference() != null) {
      return it.getReference();
    } else {
      return it.getTargetType();
    }
  }

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
    final List<Injection> result;
    if (it.getIncludedScopes().isEmpty()) {
      result = new ArrayList<>();
    } else {
      result = new ArrayList<>();
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

  public List<Injection> allInjections(final ScopeModel it) {
    if (it != null) {
      return _allInjections(it);
    } else {
      return _allInjections((Void) null);
    }
  }

  public boolean hasInjection(final List<Injection> list, final Injection injection) {
    if (list.isEmpty()) {
      return false;
    } else {
      return list.stream().anyMatch(i -> isEqual(i, injection));
    }
  }

  // dispatch isEqual(Injection, Injection)
  protected boolean _isEqual(final Injection a, final Injection b) {
    return a.getType().equals(b.getType()) && a.getName().equals(b.getName());
  }

  /*
   * SCOPE EXPRESSIONS
   */
  public boolean isCaseInsensitive(final NamedScopeExpression it) {
    return ScopingGeneratorUtil.isCaseInsensitive(it);
  }

  /*
   * ECONTAINER
   */
  public ScopeModel getModel(final EObject it) {
    return (ScopeModel) it.eResource().getContents().get(0);
  }

  public /*cached*/ ScopeDefinition getScope(final EObject it) {
    return eContainer(it, ScopeDefinition.class);
  }

  public /*cached*/ NamingDefinition getNamingDef(final EObject it) {
    return eContainer(it, NamingDefinition.class);
  }

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
  protected boolean _isEqual(final EClass a, final EClass b) {
    return a == b || (a != null && b != null && a.getName().equals(b.getName()) && a.getEPackage().getNsURI().equals(b.getEPackage().getNsURI()));
  }

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
  protected boolean _isEqual(final EReference a, final EReference b) {
    return a == b || (a != null && b != null && a.getName().equals(b.getName()) && isEqual(a.getEContainingClass(), b.getEContainingClass()));
  }

  // Public dispatcher for isEqual - handles all type combinations
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
