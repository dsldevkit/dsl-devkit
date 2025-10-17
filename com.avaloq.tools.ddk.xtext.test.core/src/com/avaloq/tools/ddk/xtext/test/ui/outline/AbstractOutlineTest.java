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
package com.avaloq.tools.ddk.xtext.test.ui.outline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.IOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.EStructuralFeatureNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.junit.Assert;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextEditorTest;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Test the outline structure of an input file.
 */
@SuppressWarnings("nls")
public abstract class AbstractOutlineTest extends AbstractXtextEditorTest {
  /**
   * Returns a list of the elements expected in the outline of the default test resource.
   *
   * @return a list of the expected elements
   */
  // TODO : make this abstract once all tests have been refactored (and change all Object to ENamedElement in this class)
  protected List<? extends ENamedElement> getExpectedElements() {
    return null; // NOPMD ReturnEmptyCollectionRatherThanNull
  }

  /**
   * Tests that the outline of the default test resource contains exactly the expected elements.
   */
  @Test
  public void testOutline() {
    if (getExpectedElements() == null) {
      return; // TODO: remove this once all tests have been refactored
    }
    for (ENamedElement element : getExpectedElements()) {
      assertHasOutlineNode(element);
    }
    // assertOutlineNodeTreeConsistsOf(getExpectedElements()); // TODO : enable this once all tests have been refactored
  }

  /**
   * Returns a directory of outline nodes indexed by the class of the represented semantic model node.
   *
   * @return directory of outline nodes indexed by the class of the represented semantic model node
   */
  @SuppressWarnings({"unchecked", "PMD.ReturnEmptyCollectionRatherThanNull"})
  private Map<Object, ArrayList<IOutlineNode>> getOutlineMap() { // NOPMD LooseCoupling
    Object obj = getTestInformation().getTestObject(IOutlineNode.class);
    if (obj instanceof Map<?, ?>) {
      return (Map<Object, ArrayList<IOutlineNode>>) obj; // NOPMD LooseCoupling
    } else {
      return null;
    }
  }

  /**
   * Set up the test by reading the input file and generating the outline tree.
   */
  @Override
  protected final void beforeAllTests() {
    super.beforeAllTests();
    IOutlineTreeProvider provider = getXtextTestUtil().get(IOutlineTreeProvider.class);
    getTestInformation().putTestObject(IOutlineNode.class, new HashMap<Object, ArrayList<IOutlineNode>>()); // NOPMD LooseCoupling
    buildOutlineMap(provider.createRoot(getDocument()));
  }

  /**
   * Assert that the outline tree has nodes for the given class.
   *
   * @param object
   *          the object for which outline nodes are expected.
   */
  protected void assertHasOutlineNode(final Object object) {
    Object key = object;
    if (object instanceof EClass) {
      key = ((EClass) object).getInstanceClass();
    }
    Assert.assertTrue("Outline must contain element '" + object.toString() + "'.", getOutlineMap().containsKey(key));
    Assert.assertFalse("Outline must contain element '" + object.toString() + "'.", getOutlineMap().get(key).isEmpty());
  }

  /**
   * Asserts that the outline tree has any node with the given name.
   *
   * @param nodeName
   *          the name of the node expected in the outline.
   * @return the {@link IOutlineNode} with the given name, {@code null} if it does not exist
   */
  protected IOutlineNode assertHasOutlineNode(final String nodeName) {
    return assertHasOutlineNode(nodeName, null);
  }

  /**
   * Asserts that the outline tree has a node with the given name and type.
   *
   * @param nodeName
   *          the name of the node expected in the outline.
   * @param nodeType
   *          the type of the node expected in the outline.
   * @return the {@link IOutlineNode} with the given name, {@code null} if it does not exist
   */
  protected IOutlineNode assertHasOutlineNode(final String nodeName, final String nodeType) {
    IOutlineTreeProvider provider = getXtextTestUtil().get(IOutlineTreeProvider.class);
    IOutlineNode field = findNode(provider.createRoot(getDocument()), nodeName, nodeType);
    Assert.assertNotNull("Outline must contain element '" + nodeName + "'.", field);
    return field;
  }

  /**
   * Asserts that the outline tree has a node with the given name, type and parent.
   *
   * @param nodeName
   *          the name of the node expected in the outline.
   * @param nodeType
   *          the type of the node expected in the outline, may be {@code null} if only the name of the node is to be tested
   * @param parentName
   *          the name of the parent
   */
  protected void assertHasOutlineNode(final String nodeName, final String nodeType, final String parentName) {
    IOutlineNode field = assertHasOutlineNode(nodeName, nodeType);
    IOutlineNode parent = field.getParent();
    Assert.assertEquals("The element '" + nodeName + "' doesn't belong to the '" + parentName + "' group.", parentName, parent.getText().toString());
  }

  /**
   * Recursively searches for a node with the given name and type.
   *
   * @param node
   *          a root node of a subtree where the desired node is searched for
   * @param nodeName
   *          the name of the node to search, must not be {@code null}
   * @param nodeType
   *          the name of the type of the node to search, may be {@code null} if only the name of the node is to be tested
   * @return
   *         a node with the given name and type (if specified). If such a node is not found, returns null.
   */
  private IOutlineNode findNode(final IOutlineNode node, final String nodeName, final String nodeType) {
    IOutlineNode fieldNode = null;
    String[] textParts = node.getText().toString().split(":");

    if (nodeName.equals(textParts[0].trim()) && (nodeType == null || (textParts.length > 1 && nodeType.equals(textParts[1].trim())))) {
      fieldNode = node;
    } else {
      List<IOutlineNode> children = node.getChildren();
      for (IOutlineNode child : children) {
        fieldNode = findNode(child, nodeName, nodeType);
        if (fieldNode != null) {
          break;
        }
      }
    }

    return fieldNode;
  }

  /**
   * Inspects the outline nodes and checks that given condition applies to at least one of the represented model objects.
   *
   * @param <T>
   *          the generic type
   * @param clazz
   *          the clazz
   * @param predicate
   *          the predicate
   */
  protected <T extends EObject> void assertEObjectInOutlineNodesExists(final Class<T> clazz, final Predicate<T> predicate) {
    final List<T> result = Lists.newArrayList();
    for (final IOutlineNode n : getOutlineMap().get(clazz)) {
      result.add(n.readOnly(new IUnitOfWork<T, EObject>() {
        @Override
        @SuppressWarnings("unchecked")
        public T exec(final EObject state) throws Exception { // NOPMD
          return (T) state;
        }
      }));
    }
    try {
      Assert.assertNotNull(NLS.bind("At least one outline node represents an object of type \"{0}\"", clazz.getName()), Iterables.find(result, predicate));
    } catch (NoSuchElementException e) {
      Assert.fail(NLS.bind("Could not find an object of type \"{0}\" in outline", clazz.getName()));
    }
  }

  /**
   * Assert that the outline tree consists only of classes contained in the given array of Classes.
   *
   * @param classes
   *          the array of classes which constitute the entire outline tree.
   */
  protected void assertOutlineNodeTreeConsistsOf(final Object... classes) {
    List<Object> outlineMapKeySet = new ArrayList<Object>(getOutlineMap().keySet());
    outlineMapKeySet.removeAll(Arrays.asList(classes));
    // assert that only EStructuralFeatures remain
    for (Object object : outlineMapKeySet) {
      Assert.assertTrue("All remaining objects in outlineMap must be of type EStructuralFeature. Found: " + object, object instanceof EStructuralFeature);
    }
  }

  /**
   * Add the given outline node to the outline map.
   *
   * @param node
   *          IOutlineNode to add.
   */
  private void addToOutlineMap(final IOutlineNode node) {
    EStructuralFeature eFeature = null;
    if (node instanceof EObjectNode) {
      eFeature = ((EObjectNode) node).getEObject(getTestSource().getXtextResource()).eContainingFeature();
      // TODO : remove the following part once all tests have been refactored
      Class<?> nodeClazz = ((EObjectNode) node).getEClass().getInstanceClass();
      if (!getOutlineMap().containsKey(nodeClazz)) {
        getOutlineMap().put(nodeClazz, new ArrayList<IOutlineNode>());
      }
      getOutlineMap().get(nodeClazz).add(node);
    } else if (node instanceof EStructuralFeatureNode) {
      eFeature = ((EStructuralFeatureNode) node).getEStructuralFeature();
    }
    if (eFeature == null) {
      return;
    }
    if (!getOutlineMap().containsKey(eFeature)) {
      getOutlineMap().put(eFeature, new ArrayList<IOutlineNode>());
    }
    getOutlineMap().get(eFeature).add(node);
  }

  /**
   * Traverse the outline tree node recursively and build up the outlineMap.
   *
   * @param node
   *          the outline tree node to traverse.
   */
  private void buildOutlineMap(final IOutlineNode node) {
    addToOutlineMap(node);
    // add node's children
    List<IOutlineNode> children = node.getChildren();
    for (IOutlineNode child : children) {
      buildOutlineMap(child);
    }
  }
}
