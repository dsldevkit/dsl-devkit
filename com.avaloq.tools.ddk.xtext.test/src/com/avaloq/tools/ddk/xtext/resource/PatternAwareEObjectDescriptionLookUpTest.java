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
package com.avaloq.tools.ddk.xtext.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;


@SuppressWarnings({"PMD.JUnitAssertionsShouldIncludeMessage", "nls"})
public class PatternAwareEObjectDescriptionLookUpTest {

  private static final String FOO = "Foo";
  private static final String FOO_BAR = "FooBar";
  private static final String FOO_PREFIX = "Foo*";
  private static final String OTHER = "Other";

  private final PatternAwareEObjectDescriptionLookUp lookUp = new PatternAwareEObjectDescriptionLookUp(List.of(//
      description(FOO), description(FOO_BAR), description("foobar"), description(OTHER)));
  @Test
  public void testCaseSensitivePatternMatchesCandidateNames() {
    assertEquals(ImmutableSet.of(FOO, FOO_BAR), names(lookUp.getExportedObjects(EcorePackage.Literals.ECLASS, QualifiedNamePattern.create(FOO_PREFIX), false)));
  }
  @Test
  public void testCaseSensitiveExactPattern() {
    assertEquals(ImmutableSet.of(OTHER), names(lookUp.getExportedObjects(EcorePackage.Literals.ECLASS, QualifiedNamePattern.create(OTHER), false)));
  }

  @Test
  public void testCaseInsensitivePattern() {
    assertEquals(ImmutableSet.of(FOO, FOO_BAR, "foobar"), names(lookUp.getExportedObjects(EcorePackage.Literals.ECLASS, QualifiedNamePattern.create("foo*"), true)));
  }

  @Test
  public void testCaseSensitivePlainName() {
    assertEquals(ImmutableSet.of(FOO_BAR), names(lookUp.getExportedObjects(EcorePackage.Literals.ECLASS, QualifiedName.create(FOO_BAR), false)));
  }

  @Test
  public void testPatternRespectsType() {
    assertEquals(ImmutableSet.of(), names(lookUp.getExportedObjects(EcorePackage.Literals.EDATA_TYPE, QualifiedNamePattern.create(FOO_PREFIX), false)));
  }

  private static IEObjectDescription description(final String name) {
    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName(name);
    return EObjectDescription.create(QualifiedName.create(name), eClass);
  }

  private static Set<String> names(final Iterable<IEObjectDescription> descriptions) {
    return ImmutableSet.copyOf(Iterables.transform(descriptions, d -> d.getName().toString()));
  }
}
