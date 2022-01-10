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
package com.avaloq.tools.ddk.xtext.test.modelinference;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.avaloq.tools.ddk.typesystem.typemodel.INamedElement;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextMarkerBasedTest;
import com.google.common.collect.Lists;


/**
 * Abstract base class for model inference test implementations.
 */
public abstract class AbstractModelInferrerTest extends AbstractXtextMarkerBasedTest {

  private boolean oldAutoBuildState;

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    oldAutoBuildState = getTestProjectManager().setAutobuild(true);
  }

  /** {@inheritDoc} */
  @Override
  protected void afterAllTests() {
    getTestProjectManager().setAutobuild(oldAutoBuildState);
    super.afterAllTests();
  }

  /**
   * Returns the inferred elements for the given source element.
   *
   * @param sourceElement
   *          a source {@link EObject}, must not be {@code null} *
   * @return the inferred elements for the given source element, never {@code null}
   */
  protected abstract Set<EObject> getInferredElements(final EObject sourceElement);

  /**
   * Validates the list of sources for correctness.
   *
   * @param sourceNames
   *          list of sources, must not be {@code null}
   */
  protected void validateSources(final String... sourceNames) {
    for (String sourceName : sourceNames) {
      getXtextTestUtil().validateSource(sourceName, getTestProjectManager().getTestSource(sourceName).getContent());
    }
  }

  /** {@inheritDoc} */
  @Override
  protected List<String> getRequiredSourceFileNames() {
    return Lists.newArrayList(); // Override the behavior of the parent
  }

  /**
   * Builds the workspace or waits until it is built. Updates the {@link TestState} after that.
   */
  protected void build() {
    getTestProjectManager().build();
  }

  /**
   * Asserts that nothing has been inferred for the tagged element.
   *
   * @param tag
   *          A tag for an element
   */
  protected void assertNoInference(final int tag) {
    assertNoInference(getObjectForTag(tag));
  }

  /**
   * Asserts that nothing has been inferred for a given source element.
   *
   * @param sourceElement
   *          a source {@link EObject}, must not be {@code null}
   */
  protected void assertNoInference(final EObject sourceElement) {
    final Set<EObject> inferredElements = getInferredElements(sourceElement);
    assertTrue("Unexpected inferred elements found: " + inferredElements.toString(), inferredElements.isEmpty());
  }

  /**
   * Returns an inferred element of specified name and type for a given tag.
   * <p>
   * <em>Note</em>: Returns the first encountered, if multiple elements have the same name.
   * </p>
   *
   * @param tag
   *          the tag for the element that should infer an element, must not be {@code null}
   * @param name
   *          the name of the element, must not be {@code null}
   * @param clazz
   *          the type of the element, must not be {@code null}
   * @return an inferred element, or {@code null} if nothing has been found
   */
  protected EObject getInferredElement(final int tag, final String name, final Class<? extends EObject> clazz) {
    return getInferredElement(getObjectForTag(tag), name, clazz);
  }

  /**
   * Returns an inferred element of specified name and type for a given source element.
   *
   * @param sourceElement
   *          a source {@link EObject}, must not be {@code null}
   * @param name
   *          the name of the element, must not be {@code null}
   * @param clazz
   *          the type of the element, must not be {@code null}
   * @return an inferred element, or {@code null} if nothing has been found
   */
  protected EObject getInferredElement(final EObject sourceElement, final String name, final Class<? extends EObject> clazz) {
    final Set<EObject> inferredElements = getInferredElements(sourceElement);
    EObject target = null;
    for (final EObject obj : inferredElements) {
      if (clazz.isAssignableFrom(obj.getClass()) && ((INamedElement) obj).getName().equals(name)) {
        target = EcoreUtil.resolve(obj, sourceElement);
        break;
      }
    }
    return target;
  }
}

