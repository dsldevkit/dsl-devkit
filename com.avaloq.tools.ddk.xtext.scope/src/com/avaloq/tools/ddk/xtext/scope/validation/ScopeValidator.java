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
package com.avaloq.tools.ddk.xtext.scope.validation;

import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.internal.xtend.xtend.XtendFile;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtend.expression.Resource;
import org.eclipse.xtend.expression.ResourceManager;
import org.eclipse.xtend.expression.ResourceManagerDefaultImpl;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.serializer.ISerializer;
import org.eclipse.xtext.validation.Check;

import com.avaloq.tools.ddk.xtext.expression.expression.Expression;
import com.avaloq.tools.ddk.xtext.scope.ScopeUtil;
import com.avaloq.tools.ddk.xtext.scope.scope.Extension;
import com.avaloq.tools.ddk.xtext.scope.scope.GlobalScopeExpression;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.NamingSection;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeContext;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDelegation;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule;
import com.avaloq.tools.ddk.xtext.scope.scope.SimpleScopeExpression;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * Validations for the build language.
 */
@SuppressWarnings("nls")
public class ScopeValidator extends AbstractScopeValidator {

  private static final String DEFAULT_SCOPE_NAME = "scope";

  @Inject
  private ISerializer serializer;

  /**
   * Verifies that all referenced extensions can be found.
   *
   * @param model
   *          scope model to check
   */
  @Check
  public void checkExtensions(final ScopeModel model) {
    ResourceManager resourceManager = null;
    if (Platform.isRunning()) {
      // FIXME: xpand
      // IXtendXpandProject project = Activator.getExtXptModelManager().findProject(ResourcesPlugin.getWorkspace().getRoot().getFile(new
      // Path(model.eResource().getURI().toPlatformString(true))).getProject());
      // if (project != null) {
      // resourceManager = new XpandPluginExecutionContext(project).getResourceManager();
      // }
    } else {
      resourceManager = new ResourceManagerDefaultImpl();
    }
    if (resourceManager == null) {
      return;
    }
    for (Extension ext : model.getExtensions()) {
      final Resource res = resourceManager.loadResource(ext.getExtension(), XtendFile.FILE_EXTENSION);
      if (res == null) {
        error(NLS.bind(Messages.extensionNotFound, ext.getExtension()), ext, ScopePackage.Literals.EXTENSION__EXTENSION, null);
      }
    }
  }

  /**
   * Verify that the context reference is a cross reference (i.e. no containment or container reference).
   * <p>
   * Verify that the reference specified is actually owner by the type given. Issues a warning otherwise.
   *
   * @param def
   *          The ScopeDefinition.
   */
  @Check
  public void checkScopeDefinitionReference(final ScopeDefinition def) {
    if (def.getReference() == null) {
      return;
    }
    if (def.getReference().isContainment() || def.getReference().isContainer()) {
      error(Messages.contextReferenceMustNotBeContainment, ScopePackage.Literals.SCOPE_DEFINITION__REFERENCE);
    }
    if (def.getReference().getEContainingClass() != def.getContextType()) {
      warning(NLS.bind(Messages.referenceNotOwnedByType, def.getReference().getName(), def.getContextType().getName()), ScopePackage.Literals.SCOPE_DEFINITION__REFERENCE);
    }
  }

  /**
   * Verify that a scope delegation within a named scope definition is matched.
   *
   * @param delegation
   *          scope delegation
   */
  @Check
  public void checkScopeDelegationReference(final ScopeDelegation delegation) {
    if (delegation.getScope() != null && DEFAULT_SCOPE_NAME.equals(delegation.getScope().getName())) {
      warning("Delegating to default scope doesn't require named scope syntax", ScopePackage.Literals.SCOPE_DELEGATION__SCOPE);
    } else if (delegation.getScope() == null) {
      ScopeDefinition containingScopeDefinition = EcoreUtil2.getContainerOfType(delegation, ScopeDefinition.class);
      if (!isDefaultScope(containingScopeDefinition)
          && findMatchingUnnamedScope(containingScopeDefinition, (ScopeModel) containingScopeDefinition.eContainer()) == null) {
        error("No matching unnamed scope found for delegation", null);
      }
    }
  }

  /**
   * Finds an unnamed scope matching the given named scope.
   *
   * @param scope
   *          scope definition
   * @param model
   *          scope model
   * @return matching unnamed scope definition
   */
  private ScopeDefinition findMatchingUnnamedScope(final ScopeDefinition scope, final ScopeModel model) {
    EClass matchType = scope.getReference() != null ? scope.getReference().getEReferenceType() : scope.getTargetType();
    for (ScopeDefinition def : model.getScopes()) {
      if (isDefaultScope(def)) {
        if ((def.getTargetType() != null && def.getTargetType().isSuperTypeOf(matchType)) // NOPMD
            || (def.getReference() != null && def.getReference() == scope.getReference())) {
          return def;
        }
      }
    }
    for (ScopeModel included : model.getIncludedScopes()) {
      ScopeDefinition result = findMatchingUnnamedScope(scope, included);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  /**
   * Checks that all scope definitions are unique. If not a warning is issued.
   *
   * @param context
   *          scoping section to check
   */
  @Check
  public void checkScopeDefinitionUniqueness(final ScopeModel context) { // NOPMD
    final Map<String, ScopeDefinition> profileMap = Maps.newHashMap();
    for (ScopeDefinition def : context.getScopes()) {
      String profile = null;
      if (isDefaultScope(def)) {
        profile = def.getTargetType() != null ? def.getTargetType().toString() : def.getReference().toString();
      } else {
        profile = def.getName();
      }
      final ScopeDefinition other = profileMap.get(profile);
      if (other != null) {
        EStructuralFeature feature = isDefaultScope(def)
            ? (def.getTargetType() != null ? ScopePackage.Literals.SCOPE_DEFINITION__TARGET_TYPE : ScopePackage.Literals.SCOPE_DEFINITION__REFERENCE)
            : ScopePackage.Literals.SCOPE_DEFINITION__NAME;
        errorOnDuplicate(def, other, Messages.duplicatedScopeDefinition, feature);
      } else {
        profileMap.put(profile, def);
      }

      if (!isDefaultScope(def)) {
        ScopeDefinition base = getOverriddenScope(context, def);
        if (base != null && (def.getTargetType() != base.getTargetType() || def.getReference() != base.getReference())) {
          errorOnDuplicate(def, base, Messages.wrongScopeDefinitionSignature, ScopePackage.Literals.SCOPE_DEFINITION__NAME);
        }
      }
    }
  }

  /**
   * Gets the base scope for a given scope if it exists.
   *
   * @param context
   *          context model
   * @param def
   *          scope definition
   * @return base scope definition in inherited scope model or {@code null}
   */
  private ScopeDefinition getOverriddenScope(final ScopeModel context, final ScopeDefinition def) {
    for (ScopeDefinition base : getAllInheritedScopeDefinitions(context)) {
      // CHECKSTYLE:OFF
      if ((isDefaultScope(base) && (def.getTargetType() == base.getTargetType() && def.getReference() == base.getReference()))
          || (!isDefaultScope(base) && def.getName().equals(base.getName()))) {
        // CHECKSTYLE:ON
        return base;
      }
    }
    return null;
  }

  /**
   * Checks if the given scope is the default scope.
   *
   * @param def
   *          scope to check
   * @return {@code true} if it is the default scope
   */
  private boolean isDefaultScope(final ScopeDefinition def) {
    return def.getName() == null || def.getName().equals(DEFAULT_SCOPE_NAME);
  }

  /**
   * Checks that the all local scope rules (i.e. excluding inherited rules) have a unique context (context type, reference,
   * guard).
   *
   * @param context
   *          scoping section to check
   */
  @Check
  public void checkScopeRuleUniqueness(final ScopeModel context) {
    final Map<String, ScopeRule> profileMap = Maps.newHashMap();
    for (ScopeDefinition def : context.getScopes()) {
      for (ScopeRule rule : def.getRules()) {
        final Expression guard = rule.getContext().getGuard();
        final String profile = ScopeUtil.getSignature(rule) + ":" + (guard != null ? serializer.serialize(guard) : ""); //$NON-NLS-1$ //$NON-NLS-2$
        final ScopeRule other = profileMap.get(profile);
        if (other != null) {
          errorOnDuplicate(rule, other, Messages.duplicatedScopeRule, ScopePackage.Literals.SCOPE_RULE__CONTEXT);
        } else {
          profileMap.put(profile, rule);
        }
      }
    }
  }

  /**
   * Checks that the local scope rules do not override any inherited scope rules (not supported).
   *
   * @param context
   *          scoping section to check
   */
  @Check
  public void checkScopeRuleOverriding(final ScopeModel context) {
    final Set<ScopeDefinition> inheritedDefinitions = getAllInheritedScopeDefinitions(context);
    if (inheritedDefinitions.isEmpty()) {
      return;
    }

    final Map<String, ScopeRule> profileMap = Maps.newHashMap();
    for (ScopeDefinition def : inheritedDefinitions) {
      for (ScopeRule rule : def.getRules()) {
        final String profile = ScopeUtil.getSignature(rule);
        profileMap.put(profile, rule);
      }
    }

    for (ScopeDefinition def : context.getScopes()) {
      for (ScopeRule rule : def.getRules()) {
        final String profile = ScopeUtil.getSignature(rule);
        final ScopeRule other = profileMap.get(profile);
        if (other != null) {
          errorOnDuplicate(rule, other, Messages.overriddenInheritedScopeRule, ScopePackage.Literals.SCOPE_RULE__CONTEXT);
        }
      }
    }
  }

  /**
   * Verifies that a matching default name function exists for scope expressions without explicit name functions.
   *
   * @param expr
   *          scope expression to check
   */
  @Check
  public void checkNameFunctionExists(final SimpleScopeExpression expr) {
    if (expr.getNaming() != null && !expr.getNaming().getNames().isEmpty()) {
      return;
    }
    ScopeDefinition def = EcoreUtil2.getContainerOfType(expr, ScopeDefinition.class);
    ScopeModel model = EcoreUtil2.getContainerOfType(expr, ScopeModel.class);
    if (def != null && model != null) {
      EClass scopeType = getType(def);
      NamingSection namingSection = model.getNaming();
      if (namingSection != null) {
        for (NamingDefinition naming : namingSection.getNamings()) {
          if (naming.getType() != null && isLeftMostSuperType(naming.getType(), scopeType)) {
            return;
          }
        }
      }
      error(NLS.bind(Messages.missingNameFunction, scopeType.getName()), ScopePackage.Literals.SIMPLE_SCOPE_EXPRESSION__EXPR);
    }
  }

  /**
   * Check if {@code someClass} is equal to {@code otherClass} or is one of the (recursively) left-most supertypes of {@code otherClass}.
   */
  private boolean isLeftMostSuperType(final EClass someClass, final EClass otherClass) {
    if (someClass == otherClass) { // NOPMD
      return true;
    } else if (otherClass != null && !otherClass.getESuperTypes().isEmpty()) {
      return isLeftMostSuperType(someClass, otherClass.getESuperTypes().get(0));
    }
    return false;
  }

  /**
   * Checks that a global scope expression used in a scope rule returns elements matching the scope definition.
   *
   * @param expr
   *          find expression to check
   */
  @Check
  public void checkReturnTypeCompatibility(final GlobalScopeExpression expr) {
    EClass actual = expr.getType();
    if (expr.eContainer() instanceof ScopeRule && actual != null) {
      EClass expected = getType(EcoreUtil2.getContainerOfType(expr, ScopeDefinition.class));
      if (!expected.isSuperTypeOf(actual)) {
        error(NLS.bind(Messages.typeMismatch, actual.getName(), expected.getName()), ScopePackage.Literals.GLOBAL_SCOPE_EXPRESSION__TYPE);
      }
    }
  }

  /**
   * Checks that a scope delegation used in a scope rule returns elements matching the scope definition.
   *
   * @param delegation
   *          scope delegation to check
   */
  @Check
  public void checkReturnTypeCompatibility(final ScopeDelegation delegation) {
    if (delegation.getScope() != null && !isDefaultScope(delegation.getScope())) {
      EClass expected = getType(EcoreUtil2.getContainerOfType(delegation, ScopeDefinition.class));
      EClass actual = getType(delegation.getScope());
      if (!expected.isSuperTypeOf(actual)) {
        warning(NLS.bind(Messages.typeMismatch, actual.getName(), expected.getName()), ScopePackage.Literals.SCOPE_DELEGATION__SCOPE);
      }
    }
  }

  /**
   * Checks that a guarded scope rule is matched by a unguarded default scope rule.
   *
   * @param context
   *          scope rule to check
   */
  @Check
  public void checkGuardDefaultExists(final ScopeRule context) {
    if (context.getContext() == null || context.getContext().getGuard() == null) {
      return;
    }

    ScopeDefinition def = EcoreUtil2.getContainerOfType(context, ScopeDefinition.class);
    ScopeContext ctx = context.getContext();
    boolean defaultFound = false;

    for (ScopeRule r : def.getRules()) {
      ScopeContext c = r.getContext();
      if (c != null && c.getGuard() == null && c.getContextType() == ctx.getContextType()) {
        defaultFound = true;
        break;
      }
    }

    if (!defaultFound) {
      warning("No matching default scope rule defined", ScopePackage.Literals.SCOPE_RULE__CONTEXT);
    }
  }

  /**
   * Returns the type of elements of a scope definition.
   *
   * @param scope
   *          scope definition
   * @return EClass for scope definition elements
   */
  private EClass getType(final ScopeDefinition scope) {
    return scope.getReference() != null ? scope.getReference().getEReferenceType() : scope.getTargetType();
  }

  /**
   * Returns a locator string for the given EObject consisting of the resource name and the line number from the node model.
   *
   * @param obj
   *          object to get locator string for
   * @return the locator string
   */
  private String getLocation(final EObject obj) {
    final ICompositeNode node = NodeModelUtils.getNode(obj);
    return obj.eResource().getURI().lastSegment() + (node != null ? ":" + node.getStartLine() : ""); //$NON-NLS-1$
  }

  /**
   * Returns a set comprising all inherited scope definitions (excluding local scope definitions).
   *
   * @param context
   *          scope model to retrieve inherited scope definitions for
   * @return all inherited (recursively) scope definitions
   */
  private Set<ScopeDefinition> getAllInheritedScopeDefinitions(final ScopeModel context) {
    final Set<ScopeDefinition> defs = Sets.newLinkedHashSet();
    for (ScopeModel include : context.getIncludedScopes()) {
      defs.addAll(include.getScopes());
      defs.addAll(getAllInheritedScopeDefinitions(include));
    }
    return defs;
  }

  /**
   * Produces ERROR diagnostic(s) detailing that the two given objects are duplicates of each other. Diagnostics will only be produced for objects which are in
   * the same resource which is currently being validated.
   *
   * @param obj
   *          object
   * @param other
   *          duplicate object
   * @param message
   *          error message which will be bound with the {@link #getLocation(EObject) locator string} of the respective object
   * @param feature
   *          feature of object to report issue against
   */
  private void errorOnDuplicate(final EObject obj, final EObject other, final String message, final EStructuralFeature feature) {
    if (obj.eResource() == getCurrentObject().eResource()) {
      error(NLS.bind(message, getLocation(other)), obj, feature, null);
    }
    if (other.eResource() == getCurrentObject().eResource()) {
      error(NLS.bind(message, getLocation(obj)), other, feature, null);
    }
  }

}
