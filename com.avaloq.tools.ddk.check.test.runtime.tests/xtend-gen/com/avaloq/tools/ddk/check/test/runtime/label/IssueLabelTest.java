/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.test.runtime.label;

import com.avaloq.tools.ddk.check.runtime.label.ICheckRuleLabelProvider;
import com.avaloq.tools.ddk.check.validation.LibraryChecksIssueCodes;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.Assert;
import org.junit.Test;

/**
 * End-to-end test for getting Check labels.
 */
@SuppressWarnings("all")
public class IssueLabelTest {
  /**
   * End-to-end test for getting Check labels.
   */
  @Test
  public void testGetLabel() {
    Injector _createInjector = Guice.createInjector(new AbstractModule() {
      @Override
      protected void configure() {
      }
    });
    final ICheckRuleLabelProvider checkRuleLabelProvider = _createInjector.<ICheckRuleLabelProvider>getInstance(ICheckRuleLabelProvider.class);
    Pair<String, String> _mappedTo = Pair.<String, String>of(LibraryChecksIssueCodes.CACHE_DOESNT_WORK, "Cache doesn\'t work");
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of(LibraryChecksIssueCodes.CACHE_INJECTION_FAILED, "Cache injection failed");
    Pair<String, String> _mappedTo_2 = Pair.<String, String>of(LibraryChecksIssueCodes.CHECK_CATALOG_IS_ACTIVE, "Check catalog is active");
    Pair<String, String> _mappedTo_3 = Pair.<String, String>of(LibraryChecksIssueCodes.FORMAL_PARAMETERS, "Formal Parameters");
    final Map<String, String> expectedMap = Collections.<String, String>unmodifiableMap(CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1, _mappedTo_2, _mappedTo_3));
    Set<Map.Entry<String, String>> _entrySet = expectedMap.entrySet();
    for (final Map.Entry<String, String> entry : _entrySet) {
      {
        String _key = entry.getKey();
        final String label = checkRuleLabelProvider.getLabel(_key);
        String _key_1 = entry.getKey();
        String _plus = ("Label should be returned for key " + _key_1);
        Assert.assertNotNull(_plus, label);
        String _key_2 = entry.getKey();
        String _plus_1 = ("Correct label should be returned for key " + _key_2);
        String _value = entry.getValue();
        Assert.assertEquals(_plus_1, _value, label);
      }
    }
  }
}
