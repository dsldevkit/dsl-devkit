/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.runtime.core.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.check.runtime.issue.ICheckValidatorImpl;
import com.avaloq.tools.ddk.check.runtime.validation.AbstractCheckValidator;
import com.google.common.collect.ImmutableMap;


/**
 * Tests the dispatch of {@link AbstractCheckValidator} to the check catalogs which have checks for an object.
 */
@SuppressWarnings("nls")
public class AbstractCheckValidatorTest {

  /**
   * Check catalog recording the objects it validates and the classes it is asked about.
   */
  private static final class RecordingCheckImpl implements ICheckValidatorImpl {
    private final Class<?> checkedType;
    private final boolean valid;
    private final List<EObject> validated = new ArrayList<>();
    private final List<Class<?>> askedTypes = new ArrayList<>();

    /**
     * Creates a catalog.
     *
     * @param checkedType
     *          the type it has checks for, or {@code null} to keep the default of {@link ICheckValidatorImpl#hasChecksFor(Class)}
     * @param valid
     *          the result of its validation
     */
    RecordingCheckImpl(final Class<?> checkedType, final boolean valid) {
      this.checkedType = checkedType;
      this.valid = valid;
    }

    @Override
    public boolean hasChecksFor(final Class<?> type) {
      askedTypes.add(type);
      return checkedType == null ? ICheckValidatorImpl.super.hasChecksFor(type) : checkedType.isAssignableFrom(type);
    }

    @Override
    public boolean validate(final EClass eClass, final EObject eObject, final DiagnosticChain diagnostics, final Map<Object, Object> context) {
      validated.add(eObject);
      return valid;
    }

    @Override
    public String getQualifiedCatalogName() {
      return "Recording";
    }

    @Override
    public ImmutableMap<String, String> getIssueCodeToLabelMap() {
      return ImmutableMap.of();
    }
  }

  /**
   * Check validator for the given catalogs.
   */
  private static final class TestCheckValidator extends AbstractCheckValidator {
    private final List<ICheckValidatorImpl> catalogs;

    /**
     * Creates a validator.
     *
     * @param catalogs
     *          the catalogs to dispatch to
     */
    TestCheckValidator(final ICheckValidatorImpl... catalogs) {
      this.catalogs = List.of(catalogs);
    }

    @Override
    protected String getHostLanguage() {
      return "test";
    }

    @Override
    protected Iterable<? extends ICheckValidatorImpl> internalCollectValidators(final String language, final EObject object) {
      return catalogs;
    }

    /**
     * Validates an object.
     *
     * @param eObject
     *          the object
     * @param context
     *          the validation context
     * @return whether the object validates without issues
     */
    boolean validate(final EObject eObject, final Map<Object, Object> context) {
      return internalValidate(eObject.eClass(), eObject, null, context);
    }
  }

  @Test
  public void testCatalogsAreOnlyCalledForObjectsTheyHaveChecksFor() {
    RecordingCheckImpl classifierCatalog = new RecordingCheckImpl(EClassifier.class, true);
    RecordingCheckImpl defaultCatalog = new RecordingCheckImpl(null, true);
    RecordingCheckImpl annotationCatalog = new RecordingCheckImpl(EAnnotation.class, true);
    TestCheckValidator validator = new TestCheckValidator(classifierCatalog, defaultCatalog, annotationCatalog);

    EcoreFactory factory = EcoreFactory.eINSTANCE;
    List<EObject> objects = List.of(factory.createEClass(), factory.createEAttribute(), factory.createEDataType(), factory.createEClass());
    Map<Object, Object> context = new HashMap<>();
    for (EObject object : objects) {
      assertTrue(validator.validate(object, context), "No catalog reported an error");
    }

    assertEquals(List.of(objects.get(0), objects.get(2), objects.get(3)), classifierCatalog.validated, "The classifier catalog validated the classifiers");
    assertEquals(objects, defaultCatalog.validated, "A catalog keeping the default validated every object");
    assertEquals(List.of(), annotationCatalog.validated, "The annotation catalog validated nothing");
    assertEquals(List.of(objects.get(0).getClass(), objects.get(1).getClass(), objects.get(2).getClass()), classifierCatalog.askedTypes, "Each class was looked up once");
  }

  @Test
  public void testResultCombinesTheCalledCatalogs() {
    TestCheckValidator validator = new TestCheckValidator(new RecordingCheckImpl(EClassifier.class, false), new RecordingCheckImpl(null, true));
    Map<Object, Object> context = new HashMap<>();
    assertFalse(validator.validate(EcoreFactory.eINSTANCE.createEClass(), context), "The failing classifier catalog was called for a class");
    assertTrue(validator.validate(EcoreFactory.eINSTANCE.createEAttribute(), context), "The failing classifier catalog was not called for an attribute");
  }

}
