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
package com.avaloq.tools.ddk.xtext.format.resource;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.XBlockExpression;

import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.GrammarRule;
import com.avaloq.tools.ddk.xtext.resource.AbstractFingerprintComputer;
import com.avaloq.tools.ddk.xtext.resource.IFingerprintComputer;


/**
 * A resource description strategy class used to dispatch what types of {@link EObject} should be indexed (exported) under what name.
 * Because usually objects are indexed using their name the problem might occur when a user changes only value of the object e.g. changing value of the constant
 * (not its name) would not trigger a cascade propagation of this change from the base format specification (.format file) to all dependent (child) format
 * specifications. Therefore, besides a standard export, all the objects are exported under their fingerprints. Therefore when the content (text, value) of the
 * object changes this would cause a proper invalidations of the dependent formats.
 * <p>
 * Invariant: the fingerprint of a {@link FormatConfiguration} is a function of the resource URI and source text of the configuration and of its resolved
 * ancestors, and of nothing else. Object identity does not participate, so reloading unchanged sources yields identical exports, while any change to an
 * inherited source (including comments and line endings, which move generated source locations) propagates to every dependent configuration. Renaming or
 * moving an inherited source propagates as well, because the generated code records inherited source locations by file name.
 */
@SuppressWarnings("nls")
public class FormatResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {

  /** Initial profile capacity; whole source texts of the inheritance chain are appended. */
  private static final int PROFILE_CAPACITY = 8192;

  /**
   * A fingerprint computer that computes the hash using the source text of the given {@link EObject}; a {@link FormatConfiguration} is hashed together with
   * the source text of its whole inheritance chain.
   */
  private final IFingerprintComputer fingerprintComputer = new AbstractFingerprintComputer() {

    @Override
    protected ExportItem fingerprint(final EObject obj) {
      final StringBuilder profile = new StringBuilder(PROFILE_CAPACITY);
      if (obj instanceof FormatConfiguration configuration) {
        addInheritanceChain(profile, configuration);
      } else if (obj != null) {
        addProfile(profile, NodeModelUtils.getTokenText(NodeModelUtils.getNode(obj)));
      }
      return new ExportItem(profile);
    }

    /**
     * Adds the resource URI and root node text of the given configuration and of each resolved ancestor; a proxy ancestor contributes its proxy URI and ends
     * the chain.
     *
     * @param profile
     *          the string builder building the fingerprint
     * @param configuration
     *          the configuration whose inheritance chain is walked
     */
    private void addInheritanceChain(final StringBuilder profile, final FormatConfiguration configuration) {
      final Set<FormatConfiguration> visited = Collections.newSetFromMap(new IdentityHashMap<>());
      FormatConfiguration current = configuration;
      while (current != null && visited.add(current)) {
        if (current.eIsProxy()) {
          addProfile(profile, ((InternalEObject) current).eProxyURI().toString());
          return;
        }
        addProfile(profile, current.eResource() == null ? "" : current.eResource().getURI().toString());
        final INode node = NodeModelUtils.getNode(current);
        addProfile(profile, node == null ? "" : node.getRootNode().getText());
        current = current.getExtendedFormatConfiguration();
      }
    }
  };

  @Override
  public boolean createEObjectDescriptions(final EObject eObject, final IAcceptor<IEObjectDescription> acceptor) {

    if (eObject instanceof XBlockExpression || isXbaseLocalVariableName(eObject)) {
      return false;
    }

    boolean indexObject = false;
    if (eObject instanceof FormatConfiguration || (eObject.eContainer() instanceof FormatConfiguration && NodeModelUtils.getNode(eObject) != null)) {
      acceptor.accept(EObjectDescription.create(fingerprintComputer.computeFingerprint(eObject), eObject));
      indexObject = true;
    }
    boolean indexDefault = createDescriptionsForNonXbaseFormalParameters(eObject, acceptor);

    return indexDefault || indexObject;
  }

  /**
   * Creates description for objects that are not a Xbase formal parameters.
   *
   * @param eObject
   *          for which description should be created
   * @param acceptor
   *          accepting descriptions
   * @return true if description was created correctly
   */
  public boolean createDescriptionsForNonXbaseFormalParameters(final EObject eObject, final IAcceptor<IEObjectDescription> acceptor) {
    return shouldCreateDescriptionForObject(eObject) && super.createEObjectDescriptions(eObject, acceptor);
  }

  private boolean shouldCreateDescriptionForObject(final EObject eObject) {
    String objectString = eObject.toString();
    return (!(objectString.contains("self") || objectString.contains("config") || objectString.contains("elements") || objectString.contains("object")));
  }

  /**
   * Checks whether given EObject represents a Xbase local variable.
   *
   * @param eObject
   *          to be checked
   * @return true if the given object does not represent a xbase local variable
   */
  public boolean isXbaseLocalVariableName(final EObject eObject) {
    INode semanticNode = NodeModelUtils.getNode(eObject);
    if (semanticNode != null) {
      INode leafNode = NodeModelUtils.findLeafNodeAtOffset(semanticNode, semanticNode.getTotalOffset());
      if (leafNode != null) {
        AbstractRule containingRule = GrammarUtil.containingRule(leafNode.getGrammarElement());
        if (containingRule != null && "ValidID".equals(containingRule.getName())) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns fully qualified name of the model type which formatting is described by the given rule.
   *
   * @param rule
   *          a grammar rule
   * @return fully qualified name of the model type
   */
  public String getModelTypeName(final GrammarRule rule) {
    String ruleName = rule.getTargetRule().getName();
    String ruleContainingGrammarName = rule.getTargetRule().getType().getMetamodel().getAlias();
    String grammarElementName = "com.avaloq.tools.dsl." + ruleContainingGrammarName + "." + ruleContainingGrammarName + "." + ruleName;
    if (ruleContainingGrammarName == null) {
      grammarElementName = EcoreUtil2.getContainerOfType(rule.getTargetRule().getType().getMetamodel(), Grammar.class).getName().toLowerCase() + "." + ruleName;
    }
    return grammarElementName;
  }
}
