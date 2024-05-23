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
package com.avaloq.tools.ddk.xtext.test.ui.labeling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;
import org.junit.Assert;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;


/**
 * Test labels of a given input file which is read in during setUpLabelTest().
 */
public abstract class AbstractLabelingTest extends AbstractXtextTest {
  /**
   * Returns a list of pairs with the expected {@link ENamedElement} and the corresponding label string.
   *
   * @return a list of pairs with the expected {@link ENamedElement} and the corresponding label string
   */
  // TODO : make abstract when refactoring all tests (and change from Object to ENamedElement everywhere in this class). remove the suppresswarnings
  @SuppressWarnings({"PMD.EmptyMethodInAbstractClassShouldBeAbstract", "PMD.ReturnEmptyCollectionRatherThanNull"})
  protected List<Pair<ENamedElement, String>> getExpectedElementLabels() {
    return null;
  }

  /**
   * Tests that the expected elements and their labels are exactly identical to all elements of the default test resource.
   */
  @Test
  public void testLabels() {
    if (getExpectedElementLabels() == null) {
      return; // TODO: remove this check once all tests have been refactored
    }
    for (Pair<ENamedElement, String> elementLabel : getExpectedElementLabels()) {
      if (elementLabel.getFirst() instanceof EClass) {
        // TODO: remove this once all tests have been refactored
        assertHasLabel(((EClass) elementLabel.getFirst()).getInstanceClass(), elementLabel.getSecond());
      } else {
        assertHasLabel(elementLabel.getFirst(), elementLabel.getSecond());
      }
    }
    // assertLabelMapConsistsOf(getExpectedElementLabels()); // TODO : revisit and enable this once all tests have been refactored
  }

  /**
   * Set up the test by indexing the labels.
   *
   * @throws Exception
   */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    getTestInformation().putTestObject(AbstractLabelingTest.class, new HashMap<Object, ArrayList<String>>()); // NOPMD LooseCoupling
    buildLabelMap(getSemanticModel());
  }

  private ILabelProvider getLabelProvider() {
    return getXtextTestUtil().get(ILabelProvider.class);
  }

  /**
   * Directory of semantic nodes indexed by the class of the represented semantic model node.
   *
   * @return directory of semantic nodes indexed by the class of the represented semantic model node
   */

  @SuppressWarnings({"unchecked", "PMD.ReturnEmptyCollectionRatherThanNull"})
  private Map<Object, ArrayList<String>> getLabelMap() { // NOPMD LooseCoupling
    Object obj = getTestInformation().getTestObject(AbstractLabelingTest.class);
    if (obj instanceof Map<?, ?>) {
      return (Map<Object, ArrayList<String>>) obj; // NOPMD LooseCoupling
    } else {
      return null;
    }
  }

  /**
   * Assert that there exists a node for the given Class having the given label.
   *
   * @param element
   *          the element
   * @param label
   *          the expected label
   */
  protected void assertHasLabel(final Object element, final String label) {
    Assert.assertTrue("Element '" + element.toString() + "' must exist.", getLabelMap().containsKey(element));
    Assert.assertTrue("Element '" + element.toString() + "' must have label '" + label + "'. LabelMap contains: "
        + getLabelMap().get(element), getLabelMap().get(element).contains(label));
  }

  /**
   * Adds an element with a label to the label map.
   *
   * @param element
   *          the element
   * @param label
   *          the label string
   */
  private void addToLabelMap(final Object element, final String label) {
    if (!getLabelMap().containsKey(element)) {
      getLabelMap().put(element, new ArrayList<String>());
    }
    getLabelMap().get(element).add(label);
  }

  /**
   * Build a directory of node labels indexed by the {@link ENamedElement}.
   *
   * @param model
   *          the model for which to build the label map
   */
  @SuppressWarnings("unchecked")
  private void buildLabelMap(final EObject model) {
    // Allow testing root container level
    String rootLabel = getLabelProvider().getText(model);
    addToLabelMap(model.eClass().getInstanceClass(), rootLabel);
    // All contained features
    List<EStructuralFeature> features = model.eClass().getEAllStructuralFeatures();
    for (EStructuralFeature feature : features) {
      if (!model.eIsSet(feature)) {
        continue;
      }
      if (feature instanceof EReference) {
        EList<EObject> children = new BasicEList<EObject>();
        if (feature.isMany()) {
          children.addAll((EList<EObject>) model.eGet(feature));
        } else {
          children.add((EObject) model.eGet(feature, false));
        }
        boolean referenceAdded = false;
        for (EObject childModelElement : children) {
          if (!childModelElement.eIsProxy() && model.equals(childModelElement.eContainer())) { // ignore crossreferenced objects
            buildLabelMap(childModelElement);
            Object element = childModelElement.eClass().getInstanceClass(); // TODO : change to EClass once all tests are refactored
            String label = getLabelProvider().getText(childModelElement);
            addToLabelMap(element, label);
            // also add the label using the reference feature as key
            if (!referenceAdded) {
              addToLabelMap(feature, label);
              referenceAdded = true;
            }
          }
        }
      } else {
        // TODO : what to do with unbounded EAttribute? -> decide also in LabelProvider
        String label = getLabelProvider().getText(Tuples.create(model, feature));
        addToLabelMap(feature, label);
      }
    }
  }
}
