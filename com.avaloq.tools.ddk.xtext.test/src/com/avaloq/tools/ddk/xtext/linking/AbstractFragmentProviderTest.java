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
package com.avaloq.tools.ddk.xtext.linking;

import org.eclipse.emf.ecore.EObject;
import org.junit.Assert;
import org.junit.Test;


/**
 * Tests for {@code AbstractFragmentProvider}.
 */
public class AbstractFragmentProviderTest {

  private static final String[] SPECIAL_ESCAPE_CASES = new String[] {"\\", "\\\\", "\\\\\\", "\\#", "\\\\#", "\\\\\\#", "#", "##", "#\\"};

  private static final class TestAbstractFragmentProvider extends AbstractFragmentProvider {

    @Override
    public boolean appendFragmentSegment(final EObject object, final StringBuilder builder) {
      return false;
    }

    @Override
    public EObject getEObjectFromSegment(final EObject container, final String segment) {
      return null;
    }

    @Override
    // make method public for testing
    @SuppressWarnings("PMD.UselessOverridingMethod")
    public void appendEscaped(final String text, final StringBuilder builder) {
      super.appendEscaped(text, builder);
    }

    @Override
    // make method public for testing
    @SuppressWarnings("PMD.UselessOverridingMethod")
    public String unescape(final String text) {
      return super.unescape(text);
    }
  }

  private final TestAbstractFragmentProvider fragmentProvider = new TestAbstractFragmentProvider();

  @Test
  public void testEscape() {
    StringBuilder builder = new StringBuilder();
    fragmentProvider.appendEscaped("foo/bar#\\", builder);
    Assert.assertEquals("Fragment not properly scaped", builder.toString(), "foo\\/bar#\\\\");
  }

  @Test
  public void testUnescape() {
    Assert.assertEquals("Fragment not properly unscaped", "foo//bar##\\", fragmentProvider.unescape("foo\\/\\/bar##\\\\"));
  }

  @Test
  public void testUnescapeEscape() {
    for (String text : SPECIAL_ESCAPE_CASES) {
      StringBuilder builder = new StringBuilder();
      fragmentProvider.appendEscaped(text, builder);
      Assert.assertEquals("Escaped Characters must be equal", text, fragmentProvider.unescape(builder.toString()));
    }
  }

}
