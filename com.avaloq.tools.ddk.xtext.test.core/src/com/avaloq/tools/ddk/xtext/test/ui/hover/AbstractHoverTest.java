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
package com.avaloq.tools.ddk.xtext.test.ui.hover;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.junit.Assert;

import com.avaloq.tools.ddk.xtext.test.AbstractXtextTest;


/**
 * Base class for hover tests.
 */
public abstract class AbstractHoverTest extends AbstractXtextTest {

  /**
   * Set up the test by indexing the labels.
   */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    getTestInformation().putTestObject(AbstractHoverTest.class, new HashMap<Object, ArrayList<String>>());
    buildHoverMap(getSemanticModel());
  }

  /**
   * Returns {@link IEObjectHoverProvider} for hover functionality of the editor.
   *
   * @return the {@link IEObjectHoverProvider} class for hover functionality, never {@code null}
   */
  protected IEObjectHoverProvider getHoverProvider() {
    return getXtextTestUtil().get(IEObjectHoverProvider.class);
  }

  /**
   * Directory of semantic nodes indexed by the class of the represented semantic model node.
   *
   * @return directory of semantic nodes indexed by the class of the represented semantic model node, or {@code null} if none was set up
   */
  @SuppressWarnings("unchecked")
  protected Map<Object, ArrayList<String>> getHoverMap() {
    Object obj = getTestInformation().getTestObject(AbstractHoverTest.class);
    if (obj instanceof Map<?, ?>) {
      return (Map<Object, ArrayList<String>>) obj;
    } else {
      return null;
    }
  }

  /**
   * Adds an element with a hover to the hover map. If there is no valid map, the method ends without adding anything.
   *
   * @param element
   *          the element of the model, must not be {@code null}
   * @param hover
   *          the hover string of the element, must not be {@code null}
   */
  private void addToHoverMap(final Object element, final String hover) {
    Map<Object, ArrayList<String>> hoverMap = getHoverMap();

    if (hoverMap == null) {
      return;
    }

    if (!hoverMap.containsKey(element)) {
      hoverMap.put(element, new ArrayList<String>());
    }
    hoverMap.get(element).add(hover);
  }

  /**
   * Build a directory of node hovers indexed by the {@link EObject}.
   *
   * @param model
   *          the model for which to build the hover map, must not be {@code null}
   */
  private void buildHoverMap(final EObject model) {
    // All contained features
    List<EStructuralFeature> features = model.eClass().getEAllStructuralFeatures();
    for (EStructuralFeature feature : features) {
      if (feature instanceof EReference && model.eIsSet(feature)) {
        EList<EObject> children = getFeatureValues(model, feature);
        boolean referenceAdded = false;
        IEObjectHoverProvider hoverProvider = getHoverProvider();
        for (EObject childModelElement : children) {
          if (!childModelElement.eIsProxy()) {
            buildHoverMap(childModelElement);
            Object element = childModelElement.eClass();
            Object hover = hoverProvider.getHoverInfo(childModelElement, null, null).getInfo();
            if (element != null && hover != null) {
              addToHoverMap(element, hover.toString());
              // also add the hover using the reference feature as key
              if (!referenceAdded) {
                addToHoverMap(feature, hover.toString());
                referenceAdded = true;
              }
            }
          }
        }
      }
    }
  }

  /**
   * Returns values of the given feature.
   *
   * @param model
   *          the model which the given feature belongs to, must not be {@code null}
   * @param feature
   *          the feature of the model with one of many values, must not be {@code null}
   * @return the values of the feature, never {@code null}
   */
  @SuppressWarnings("unchecked")
  private EList<EObject> getFeatureValues(final EObject model, final EStructuralFeature feature) {
    EList<EObject> values = new BasicEList<EObject>();
    if (feature.isMany()) {
      values.addAll((EList<EObject>) model.eGet(feature));
    } else {
      values.add((EObject) model.eGet(feature, false));
    }

    return values;
  }

  /**
   * Tests whether there exists a hover for given element and that it contains given first line.
   *
   * @param element
   *          element of the model with hover, must not be {@code null}
   * @param firstLine
   *          string which represents a first line of the hover of a given element, must not be {@code null}
   */
  protected void assertHover(final ENamedElement element, final String firstLine) {
    assertElementExistInHoverMap(element);
    Assert.assertTrue("Element '" + element.toString() + "' must have first line of hover '" + firstLine + "'. " + "\n\nHoverMap contains:\n"
        + getHoverMap().get(element), hasTextOnFirstLine(getHoverMap().get(element), firstLine));
  }

  /**
   * Tests whether there exists a hover for a given element and that the first line does not contain the given text.
   *
   * @param element
   *          element of the model with hover, must not be {@code null}
   * @param text
   *          string that must not be contained in the first line of the hover text, must not be {@code null}
   */
  protected void assertHoverDoesNotContainText(final ENamedElement element, final String text) {
    assertElementExistInHoverMap(element);
    Assert.assertFalse("Element '" + element.toString() + "' first line of hover must not have '" + text + "'. " + "\n\nHoverMap contains:\n"
        + getHoverMap().get(element), hasTextOnFirstLine(getHoverMap().get(element), text));
  }

  /**
   * Assert that the element given exist in the hover map.
   *
   * @param element
   *          element of the model with hover, must not be {@code null}
   */
  private void assertElementExistInHoverMap(final ENamedElement element) {
    Assert.assertTrue("Element '" + element.toString() + "' must exist.", getHoverMap().containsKey(element));
  }

  /**
   * Cleans the given string from HTML tags.
   * <p>
   * NOTE: The regular expression used to remove tags also matches strings with "<" and ">" characters (e.g. "Value is < 10 and > 0").
   * </p>
   *
   * @param text
   *          a string containing HTML tags, must not be {@code null}
   * @return the string without HTML tags, never {@code null}
   */
  private String removeHTMLTags(final String text) {
    Pattern pattern = Pattern.compile("\\<[^>]*>"); //$NON-NLS-1$
    Matcher matcher = pattern.matcher(text);
    String plainText = matcher.replaceAll("");

    return plainText;
  }

  /**
   * Checks whether there exist a hover containing the given text on the first line.
   *
   * @param hoverList
   *          a list of hovers for particular element type, must not be {@code null}
   * @param textOnFirstLine
   *          a string representing the first line (or part) of the hover text, must not be {@code null}
   * @return true, if there exists a hover from the list which contains the given text in the first line
   */
  private boolean hasTextOnFirstLine(final List<String> hoverList, final String textOnFirstLine) {
    for (String hover : hoverList) {
      String plainHover = removeHTMLTags(hover);
      if (plainHover.contains(textOnFirstLine)) {
        return true;
      }
    }

    return false;
  }
}
