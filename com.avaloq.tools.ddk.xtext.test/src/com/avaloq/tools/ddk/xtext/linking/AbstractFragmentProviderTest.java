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
package com.avaloq.tools.ddk.xtext.linking;

import org.eclipse.emf.ecore.EObject;
import org.junit.Assert;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProvider;


/**
 * Tests for {@code AbstractFragmentProvider}.
 */
public class AbstractFragmentProviderTest {

  private static final String[] SPECIAL_ESCAPE_CASES = new String[] {"\\", "\\\\", "\\\\\\", "\\#", "\\\\#", "\\\\\\#", "#", "##", "#\\"};

  private static class TestAbstractFragmentProvider extends AbstractFragmentProvider {

    @Override
    public CharSequence getFragmentSegment(final EObject object) {
      return null;
    }

    @Override
    public EObject getEObjectFromSegment(final EObject container, final String segment) {
      return null;
    }

    @Override
    // make method public for testing
    @SuppressWarnings("PMD.UselessOverridingMethod")
    public String escape(final String text) {
      return super.escape(text);
    }

    @Override
    // make method public for testing
    @SuppressWarnings("PMD.UselessOverridingMethod")
    public String unescape(final String text) {
      return super.unescape(text);
    }
  };

  private final TestAbstractFragmentProvider fragmentProvider = new TestAbstractFragmentProvider();

  @Test
  public void testEscape() {
    Assert.assertEquals("foo\\/bar#\\\\", fragmentProvider.escape("foo/bar#\\"));
  }

  @Test
  public void testUnescape() {
    Assert.assertEquals("foo//bar##\\", fragmentProvider.unescape("foo\\/\\/bar##\\\\"));
  }

  @Test
  public void testUnescapeEscape() {
    for (String text : SPECIAL_ESCAPE_CASES) {
      Assert.assertEquals(text, fragmentProvider.unescape(fragmentProvider.escape(text)));
    }
  }

}

