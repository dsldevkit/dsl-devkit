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
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall;
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensions;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.avaloq.tools.ddk.xtext.scope.ScopeUtil;
import com.avaloq.tools.ddk.xtext.scope.scope.Extension;
import com.avaloq.tools.ddk.xtext.scope.scope.Injection;
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.google.inject.Inject;


@SuppressWarnings({"checkstyle:MethodName", "nls", "PMD.UnusedFormalParameter"})
public class ScopeProviderX {

  @Inject
  private Naming naming;

  @Inject
  private GeneratorUtilX generatorUtilX;

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
    final String qualifier;
    if (it.getTargetType() != null) {
      qualifier = it.getTargetType().getEPackage().getName() + "_" + it.getTargetType().getName();
    } else {
      qualifier = it.getContextType().getEPackage().getName() + "_" + it.getContextType().getName() + "_" + it.getReference().getName();
    }
    return getScopeName(it) + "_" + qualifier;
  }

  public String locatorString(final EObject it) {
    final String[] segments = generatorUtilX.location(it).split("/");
    return Strings.convertToJavaString(segments.length == 0 ? null : segments[segments.length - 1]);
  }

  public String calledFeature(final FeatureCall it) {
    final EList<String> id = it.getType().getId();
    return id.isEmpty() ? null : id.get(0);
  }

  public EStructuralFeature feature(final FeatureCall it) {
    return scopeType(it).getEStructuralFeature(calledFeature(it));
  }

  /*
   * SCOPE RULES
   */
  protected List<ScopeRule> _allScopeRules(final Void it) {
    return new ArrayList<>();
  }

  protected List<ScopeRule> _allScopeRules(final ScopeDefinition it) {
    return collectAllScopeRules(getModel(it), it);
  }

  public List<ScopeRule> collectAllScopeRules(final ScopeModel it, final ScopeDefinition def) {
    final List<ScopeRule> myScopeRules = new ArrayList<>();
    for (final ScopeDefinition d : it.getScopes()) {
      if (isEqual(d, def)) {
        myScopeRules.addAll(d.getRules());
      }
    }
    final List<ScopeRule> result = new ArrayList<>();
    for (final ScopeModel included : it.getIncludedScopes()) {
      result.addAll(collectAllScopeRules(included, def));
    }
    result.addAll(myScopeRules);
    return result;
  }

  public List<ScopeRule> sortedRules(final Collection<ScopeRule> it) {
    return ScopingGeneratorUtil.sortedRules(it);
  }

  public Set<ScopeRule> filterUniqueRules(final List<ScopeRule> it) {
    final Set<ScopeRule> result = new LinkedHashSet<>();
    for (final ScopeRule r : it) {
      result.add(it.stream().filter(r2 -> hasSameContext(r2, r)).findFirst().orElse(null));
    }
    return result;
  }

  protected boolean _isEqual(final ScopeRule a, final ScopeRule b) {
    return hasSameContext(a, b)
        // && ((a.name === null) == (b.name === null)) && (a.name === null || a.name.matches (b.name))
        && Objects.equals(ExpressionExtensions.serialize(a.getContext().getGuard()), ExpressionExtensions.serialize(b.getContext().getGuard()));
  }

  public boolean hasSameContext(final ScopeRule a, final ScopeRule b) {
    return Objects.equals(ruleSignature(a), ruleSignature(b));
  }

  // Hrmph. Use naming here, otherwise we'll get strange (and wrong) results in the GenerateAllAPSLs workflow for netwStruct?!
  private /*cached*/ String ruleSignature(final ScopeRule s) {
    return ScopeUtil.getSignature(s);
  }

  /*
   * SCOPE DEFINITIONS
   */
  // returns the list of all local and inherited scope definition (skipping any shadowed or extended scope definitions)
  protected List<ScopeDefinition> _allScopes(final ScopeModel it) {
    final EList<ScopeDefinition> myScopes = it.getScopes();
    final List<ScopeDefinition> result = new ArrayList<>();
    for (final ScopeModel included : it.getIncludedScopes()) {
      result.addAll(allScopes(included));
    }
    result.removeIf(s -> hasScope(myScopes, s));
    result.addAll(myScopes);
    return result;
  }

  protected List<ScopeDefinition> _allScopes(final Void it) {
    return new ArrayList<>();
  }

  public String getScopeName(final ScopeDefinition it) {
    return it.getName() == null ? "scope" : it.getName();
  }

  public boolean hasScope(final List<ScopeDefinition> list, final ScopeDefinition scope) {
    return list.stream().anyMatch(s -> isEqual(s, scope));
  }

  protected boolean _isEqual(final ScopeDefinition a, final ScopeDefinition b) {
    return Objects.equals(getScopeName(a), getScopeName(b)) && isEqual(a.getTargetType(), b.getTargetType())
        && isEqual(a.getReference(), b.getReference());
  }

  /*
   * SCOPE TYPE
   */
  protected EClass _scopeType(final ScopeDefinition it) {
    return it.getReference() != null ? it.getReference().getEReferenceType() : it.getTargetType();
  }

  protected EClass _scopeType(final ScopeRule it) {
    return scopeType(getScope(it));
  }

  protected EClass _scopeType(final Expression it) {
    return getScope(it) != null ? scopeType(getScope(it)) : getNamingDef(it).getType();
  }

  public ENamedElement typeOrRef(final ScopeDefinition it) {
    return it.getReference() != null ? it.getReference() : it.getTargetType();
  }

  public EReference contextRef(final ScopeRule it) {
    return getScope(it).getReference();
  }

  /*
   * Injections
   */
  // returns the list of all local and inherited injections (skipping any shadowed injections)
  protected List<Injection> _allInjections(final ScopeModel it) {
    final EList<Injection> myInjections = it.getInjections();
    final List<Injection> result = new ArrayList<>();
    for (final ScopeModel included : it.getIncludedScopes()) {
      result.addAll(allInjections(included));
    }
    result.removeIf(i -> hasInjection(myInjections, i));
    result.addAll(myInjections);
    return result;
  }

  protected List<Injection> _allInjections(final Void it) {
    return new ArrayList<>();
  }

  public boolean hasInjection(final List<Injection> list, final Injection injection) {
    return list.stream().anyMatch(i -> isEqual(i, injection));
  }

  protected boolean _isEqual(final Injection a, final Injection b) {
    return Objects.equals(a.getType(), b.getType()) && Objects.equals(a.getName(), b.getName());
  }

  /*
   * Extensions
   */
  /**
   * Returns the extension declarations visible to the given scope model: its own declarations first, followed by
   * those of the (transitively) included scope models. Duplicates (by qualified extension name) are removed,
   * keeping the first occurrence.
   * <p>
   * The order matters: an extension operation is resolved by name against this list, so the model's own extension
   * classes must shadow those it inherits, exactly as the legacy classic Xtend execution context did (its
   * {@code Resource.getImportedExtensions()} listed the model before its included models).
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @return the visible extension declarations in resolution order, never {@code null}
   */
  protected List<Extension> _allExtensions(final ScopeModel it) {
    final List<Extension> result = new ArrayList<>();
    final Set<String> seen = new LinkedHashSet<>();
    for (final Extension declaration : collectExtensions(it, new LinkedHashSet<>())) {
      if (seen.add(declaration.getExtension())) {
        result.add(declaration);
      }
    }
    return result;
  }

  protected List<Extension> _allExtensions(final Void it) {
    return new ArrayList<>();
  }

  /**
   * Collects the extension declarations of the given scope model followed by those of the scope models it includes,
   * depth first. The given set guards against include cycles and diamonds.
   *
   * @param it
   *          the scope model, must not be {@code null}
   * @param visited
   *          the scope models already visited, must not be {@code null}
   * @return the collected extension declarations, never {@code null}
   */
  private List<Extension> collectExtensions(final ScopeModel it, final Set<ScopeModel> visited) {
    final List<Extension> result = new ArrayList<>();
    if (!visited.add(it)) {
      return result;
    }
    result.addAll(it.getExtensions());
    for (final ScopeModel included : it.getIncludedScopes()) {
      if (included != null) {
        result.addAll(collectExtensions(included, visited));
      }
    }
    return result;
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
    final EList<EObject> contents = it.eResource().getContents();
    return (ScopeModel) (contents.isEmpty() ? null : contents.get(0));
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
      return type.cast(it);
    } else {
      return eContainer(it.eContainer(), type);
    }
  }

  /*
   * ECORE
   */
  protected boolean _isEqual(final EClass a, final EClass b) {
    return Objects.equals(a, b) || haveSameName(a, b) && Objects.equals(a.getEPackage().getNsURI(), b.getEPackage().getNsURI());
  }

  protected boolean _isEqual(final Void a, final Void b) {
    return true;
  }

  protected boolean _isEqual(final EObject a, final Void b) {
    return false;
  }

  protected boolean _isEqual(final Void a, final EObject b) {
    return false;
  }

  protected boolean _isEqual(final EReference a, final EReference b) {
    return Objects.equals(a, b) || haveSameName(a, b) && isEqual(a.getEContainingClass(), b.getEContainingClass());
  }

  /**
   * Tests whether both given elements are non-{@code null} and carry the same name.
   *
   * @param a
   *          the first element, may be {@code null}
   * @param b
   *          the second element, may be {@code null}
   * @return {@code true} if both are non-{@code null} and equally named
   */
  private static boolean haveSameName(final ENamedElement a, final ENamedElement b) {
    return a != null && b != null && Objects.equals(a.getName(), b.getName());
  }

  public List<ScopeRule> allScopeRules(final ScopeDefinition it) {
    return it != null ? _allScopeRules(it) : _allScopeRules((Void) null);
  }

  public boolean isEqual(final EObject a, final EObject b) {
    if (a instanceof EReference && b instanceof EReference) {
      return _isEqual((EReference) a, (EReference) b);
    } else if (a instanceof EClass && b instanceof EClass) {
      return _isEqual((EClass) a, (EClass) b);
    } else if (a instanceof Injection && b instanceof Injection) {
      return _isEqual((Injection) a, (Injection) b);
    } else if (a instanceof ScopeDefinition && b instanceof ScopeDefinition) {
      return _isEqual((ScopeDefinition) a, (ScopeDefinition) b);
    } else if (a instanceof ScopeRule && b instanceof ScopeRule) {
      return _isEqual((ScopeRule) a, (ScopeRule) b);
    } else if (a != null && b == null) {
      return _isEqual(a, (Void) null);
    } else if (a == null && b != null) {
      return _isEqual((Void) null, b);
    } else {
      return _isEqual((Void) null, (Void) null);
    }
  }

  public List<ScopeDefinition> allScopes(final ScopeModel it) {
    return it != null ? _allScopes(it) : _allScopes((Void) null);
  }

  public EClass scopeType(final EObject it) {
    return switch (it) {
      case Expression expression -> _scopeType(expression);
      case ScopeDefinition definition -> _scopeType(definition);
      case ScopeRule rule -> _scopeType(rule);
      case null, default -> throw new IllegalArgumentException("Unhandled parameter types: " + Arrays.<Object>asList(it).toString());
    };
  }

  public List<Injection> allInjections(final ScopeModel it) {
    return it != null ? _allInjections(it) : _allInjections((Void) null);
  }

  public List<Extension> allExtensions(final ScopeModel it) {
    return it != null ? _allExtensions(it) : _allExtensions((Void) null);
  }

}
