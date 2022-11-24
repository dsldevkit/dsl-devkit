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
package com.avaloq.tools.ddk.check.runtime.core.registry;

import java.util.List;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Provides some test cases for the Check extension point.
 */
public class CheckExtensionPointTests extends TestCase {

  private static final String DUMMY_EXTENSION_ID = "com.avaloq.tools.ddk.check.runtime.core.test";
  private static final String CHECK_EXTENSION_ID = "com.avaloq.tools.ddk.check.runtime.core";
  private static final String TARGET_CLASS_ATTRIBUTE = "targetClass";
  private static final String CHECK_EXTENSION_POINT_SIMPLE_NAME = "check";
  private static final String LANGUAGE_ATTRIBUTE = "language";

  /**
   * Finds all registered extension points.
   * 
   * @return the list of registered extension points
   */
  private List<IExtensionPoint> findExtensionPoints() {
    return Lists.newArrayList(Platform.getExtensionRegistry().getExtensionPoints());
  }

  /**
   * Finds the check extension point.
   * 
   * @param extensionPoints
   *          the collection of extension points in which to search
   * @return the check extension point
   * @see #CHECK_EXTENSION_ID
   * @see #CHECK_EXTENSION_POINT_SIMPLE_NAME
   */
  private IExtensionPoint findCheckExtensionPoint(final List<IExtensionPoint> extensionPoints) {
    return Iterables.find(extensionPoints, new Predicate<IExtensionPoint>() {
      public boolean apply(final IExtensionPoint input) {
        return CHECK_EXTENSION_ID.equals(input.getNamespaceIdentifier()) && CHECK_EXTENSION_POINT_SIMPLE_NAME.equals(input.getSimpleIdentifier());
      }
    });
  }

  /**
   * Finds a configuration element with given attribute value.
   * 
   * @param elements
   *          the configuration elements of an extension point
   * @param attributeName
   *          the attribute name to look for
   * @return the <em>first</em> configuration element with given attribute
   *         name found
   */
  private IConfigurationElement findConfigurationElement(final IConfigurationElement[] elements, final String attributeName) {
    for (IConfigurationElement element : elements) {
      String attribute = element.getAttribute(attributeName);
      if (attribute != null) {
        return element;
      }
    }
    return null;
  }

  /**
   * Tests that the check extension point can be found in the global registry
   * of extension points.
   * 
   * @see Platform#getExtensionRegistry()
   */
  @Test
  public void testExtensionPointIsRegistered() {
    try {
      findCheckExtensionPoint(findExtensionPoints());
    } catch (NoSuchElementException e) {
      fail("Could not find extension point");
    }
  }

  /**
   * Tests that this plug-in is a registered client for the check extension
   * point.
   */
  @Test
  public void testDummyClientRegistered() {
    assertNotNull("Test plugin successfully registered to the check extension point", findCheckExtensionPoint(findExtensionPoints()).getExtension(DUMMY_EXTENSION_ID));
  }

  /**
   * Tests that a non-existing client is not registered with the extension
   * point registry.
   */
  @Test
  public void testInvalidClientNotRegistered() {
    assertNull("Non-existing client not registered to the check extension point", findCheckExtensionPoint(findExtensionPoints()).getExtension("a.b.c"));
  }

  /**
   * Tests that the check extension point has an attribute {@value #TARGET_CLASS_ATTRIBUTE}.
   */
  @Test
  public void testTargetClassAttributeFound() {
    IExtensionPoint point = findCheckExtensionPoint(findExtensionPoints());
    assertNotNull("Found a configuration element with attribute \"targetClass\"", findConfigurationElement(point.getConfigurationElements(), TARGET_CLASS_ATTRIBUTE));
  }

  /**
   * Tests that the check extension point has an attribute {@value #LANGUAGE_ATTRIBUTE}.
   */
  @Test
  public void testLanguageAttributeFound() {
    IExtensionPoint point = findCheckExtensionPoint(findExtensionPoints());
    assertNotNull("Found a configuration element with attribute \"language\"", findConfigurationElement(point.getConfigurationElements(), LANGUAGE_ATTRIBUTE));
  }
}

