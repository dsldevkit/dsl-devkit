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
package com.avaloq.tools.ddk.xtext.format.resource;

import org.eclipse.emf.ecore.EObject;
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
 */
public class FormatResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {

  /**
   * A fingerprint computer that computes the hash using the content (text) and the parent container of the given {@link EObject}.
   */
  private final IFingerprintComputer fingerprintComputer = new AbstractFingerprintComputer() {

    @Override
    protected ExportItem fingerprint(final EObject obj) {
      final StringBuilder profile = new StringBuilder();
      if (obj.eContainer() != null) {
        addProfile(profile, obj.eContainer().toString());
      }
      if (obj != null) {
        addProfile(profile, NodeModelUtils.getTokenText(NodeModelUtils.getNode(obj)));
      }
      return new ExportItem(profile);
    }
  };

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean createEObjectDescriptions(final EObject eObject, final IAcceptor<IEObjectDescription> acceptor) {

    if (eObject instanceof XBlockExpression || isXbaseLocalVariableName(eObject)) {
      return false;
    }

    boolean indexObject = false;
    boolean indexDefault = false;
    String objectFingerprint = null;
    if (fingerprintComputer != null && eObject.eContainer() instanceof FormatConfiguration && NodeModelUtils.getNode(eObject) != null) {
      objectFingerprint = fingerprintComputer.computeFingerprint(eObject);
    }

    if (objectFingerprint != null && !"".equals(objectFingerprint) && eObject.eContainer() instanceof FormatConfiguration) {
      acceptor.accept(EObjectDescription.create(objectFingerprint, eObject));
      indexObject = true;
    }

    indexDefault = createDescriptionsForNonXbaseFormalParameters(eObject, acceptor);

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
    if (!(eObject.toString().contains("self") || eObject.toString().contains("config") || eObject.toString().contains("elements")
        || eObject.toString().contains("object"))) {
      return super.createEObjectDescriptions(eObject, acceptor);
    }
    return false;
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
      AbstractRule containingRule = GrammarUtil.containingRule(leafNode.getGrammarElement());
      if (leafNode != null && containingRule != null && "ValidID".equals(containingRule.getName())) {
        return true;
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
