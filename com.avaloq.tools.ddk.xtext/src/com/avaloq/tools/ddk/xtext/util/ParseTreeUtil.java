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
package com.avaloq.tools.ddk.xtext.util;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.avaloq.tools.ddk.xtext.linking.LazyLinkingResource2;
import com.google.common.collect.Iterables;


/**
 * Utility working on abstract syntax trees.
 */
public final class ParseTreeUtil {

  /** To be used in conjunction with NLS binding. States that a EStructuralFeature is not known for given EClass. */
  private static final String UNKNOWN_FEATURE_MESSAGE = "Unknown feature {0} for object of class {1}"; //$NON-NLS-1$

  private ParseTreeUtil() {
  }

  /**
   * Provides an iterable wrapper for a parse tree root node.
   *
   * @param <T>
   *          Element type to iterate over
   * @param rootNode
   *          Parse tree root node
   * @param clazz
   *          Type of class to iterate over. Needed because generics are only
   *          compile time in Java
   * @return IterableParseTree
   */
  public static <T extends INode> Iterable<T> getIterableParseTree(final ICompositeNode rootNode, final Class<T> clazz) {
    return Iterables.filter(rootNode.getChildren(), clazz);
  }

  /**
   * Returns the source text assigned to the given feature of the given object. Does not work for multi-valued features. The source text will be converted using
   * the corresponding value converter.
   *
   * @param object
   *          the semantic object
   * @param feature
   *          the feature, e.g. CodetabdefPackage.Literals.VERSION_TAG
   * @return the parsed string
   */
  public static String getParsedString(final EObject object, final EStructuralFeature feature) {
    if (object == null) {
      throw new IllegalArgumentException("null object"); //$NON-NLS-1$
    }
    return getParsedString(object, feature, true);
  }

  /**
   * Returns the source text assigned to the given feature of the given object. Does not work for multi-valued features. Optionally also converts the source
   * text using the corresponding value converter.
   *
   * @param object
   *          the object
   * @param feature
   *          the EStructuralFeature, e.g. CodetabdefPackage.Literals.VERSION_TAG
   * @param convert
   *          whether or not the parsed string should be run through the value converter
   * @return the parsed string
   */
  public static String getParsedString(final EObject object, final EStructuralFeature feature, final boolean convert) {
    if (!object.eClass().getEAllStructuralFeatures().contains(feature)) {
      throw new IllegalArgumentException(MessageFormat.format(UNKNOWN_FEATURE_MESSAGE, feature.getName(), object.eClass().getName()));
    }
    return getParsedStringUnchecked(object, feature, convert);
  }

  /**
   * As {@link #getParsedString(EObject, EStructuralFeature)}, except that the feature name is passed by name. <br>
   * Always converts using the value converter.
   *
   * @param object
   *          the object
   * @param feature
   *          the feature name
   * @return the parsed string
   */
  public static String getParsedString(final EObject object, final String feature) {
    return getParsedString(object, feature, true);
  }

  /**
   * As {@link #getParsedString(EObject, EStructuralFeature)}, except that the feature name is passed by name.
   *
   * @param object
   *          the object
   * @param featureName
   *          the feature name
   * @param convert
   *          {@code true} if the parsed string from the parse tree model should be converted using the corresponding value converter, else {@code false}
   * @return the parsed string
   */
  public static String getParsedString(final EObject object, final String featureName, final boolean convert) {
    EStructuralFeature feature = object.eClass().getEStructuralFeature(featureName);
    if (feature == null) {
      throw new IllegalArgumentException(MessageFormat.format(UNKNOWN_FEATURE_MESSAGE, feature, object.eClass().getName()));
    }
    return getParsedStringUnchecked(object, feature, convert);
  }

  /**
   * Ensure the node model loaded.
   *
   * @param resource
   *          the resource
   */
  public static void ensureNodeModelLoaded(final Resource resource) {
    if (resource instanceof LazyLinkingResource2 lazyLinkinResource && lazyLinkinResource.isLoadedFromStorage()) {
      EObject root = resource.getContents().get(0);
      if (root != null && NodeModelUtils.getNode(root) instanceof ICompositeNode composite) {
        composite.getText(); // side-effect on removing com.avaloq.tools.ddk.xtext.resource.persistence.ProxyCompositeNode
      }
    }
  }

  /**
   * Returns the source text assigned to the given feature of the given object. Does not work for multi-valued features. Optionally also converts the source
   * text using the corresponding value converter. Conversion is only performed for keywords, rule call or cross reference grammar rules.
   * <p>
   * This method does not perform a check to make sure the feature matches the given object.
   *
   * @param object
   *          the semantic object
   * @param feature
   *          the feature to be considered when parsing the parse tree model
   * @param convert
   *          {@code true} if the parsed string needs conversion using its value converter
   * @return the parsed string from the node model
   */
  public static String getParsedStringUnchecked(final EObject object, final EStructuralFeature feature, final boolean convert) {
    INode node = Iterables.getFirst(NodeModelUtils.findNodesForFeature(object, feature), null);

    if (node == null) {
      ensureNodeModelLoaded(object.eResource());
      node = Iterables.getFirst(NodeModelUtils.findNodesForFeature(object, feature), null);
    }
    if (node != null) {
      if (convert) {
        final LazyLinkingResource res = (LazyLinkingResource) object.eResource();
        EObject grammarElement = node.getGrammarElement();
        if (res != null && (grammarElement instanceof Keyword || grammarElement instanceof RuleCall || grammarElement instanceof CrossReference)) {
          final DefaultLinkingService linkingService = (DefaultLinkingService) res.getLinkingService();
          return linkingService.getCrossRefNodeAsString(node);
        }
      }
      // result may contain escape sequences or quotes
      return NodeModelUtils.getTokenText(node);
    }
    return null;
  }

  private static String getTerminalString(final INode node) {
    final Stream<ILeafNode> leaves = StreamSupport.stream(node.getLeafNodes().spliterator(), false);
    return leaves.filter(leaf -> !leaf.isHidden()).map(ILeafNode::getText).collect(Collectors.joining(""));//$NON-NLS-1$
  }

  /**
   * Gets a list of all the terminal strings contained in a model object for a given feature.
   *
   * @param modelObject
   *          EObject model whose enclosed terminals are considered
   * @param feature
   *          EStructuralFeature feature to consider
   * @return the list of strings defined by the terminals enclosed in modelObject for assigning values to feature
   */
  public static List<String> getParsedStrings(final EObject modelObject, final EStructuralFeature feature) {
    final List<INode> nodes = NodeModelUtils.findNodesForFeature(modelObject, feature);
    return nodes.stream().map(ParseTreeUtil::getTerminalString).collect(Collectors.toList());
  }

}
