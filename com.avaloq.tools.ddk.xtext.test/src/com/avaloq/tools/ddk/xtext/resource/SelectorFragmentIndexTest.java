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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.google.inject.Inject;


/**
 * Tests the numeric parts of selector fragment segments of {@link AbstractSelectorFragmentProvider} with multi-digit indices.
 */
@SuppressWarnings("nls")
@InjectWith(AbstractXtextTestsInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class SelectorFragmentIndexTest {

  private static final int KEYWORDS = 12;

  @Inject
  private ParseHelper<EObject> parseHelper;

  @Test
  public void testMultiDigitSelectorIndex() {
    Grammar grammar = parse("grammar foo.Foo\ngenerate foo 'http://www.foo.com/foo'\nFoo:" + " 'x'".repeat(KEYWORDS) + ";");
    Resource resource = grammar.eResource();
    List<AbstractElement> keywords = ((Group) grammar.getRules().get(0).getAlternatives()).getElements();

    String fragment = resource.getURIFragment(keywords.get(KEYWORDS - 1));
    assertEquals("/0/5(0='Foo')#0/2/3(3='x')#11", fragment);
    assertSame(keywords.get(KEYWORDS - 1), resource.getEObject(fragment));
    assertSame(keywords.get(10), resource.getEObject("/0/5(0='Foo')#0/2/3(3='x')#10"));
    assertSame(keywords.get(0), resource.getEObject("/0/5(0='Foo')#0/2/3(3='x')#0"));
    assertNull(resource.getEObject("/0/5(0='Foo')#0/2/3(3='x')#12"));
  }

  private Grammar parse(final String model) {
    try {
      return (Grammar) parseHelper.parse(model);
    } catch (Exception e) { // NOPMD AvoidCatchingGenericException
      throw new AssertionError(e);
    }
  }

}
