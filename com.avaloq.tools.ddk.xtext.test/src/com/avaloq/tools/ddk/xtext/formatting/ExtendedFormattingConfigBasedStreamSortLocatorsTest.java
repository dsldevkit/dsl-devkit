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
package com.avaloq.tools.ddk.xtext.formatting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.xtext.XtextFactory;
import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.ElementLocator;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoFormatLocator;


/**
 * Tests the private {@code sortLocators} of {@link ExtendedFormattingConfigBasedStream}, which must order column locators (opening before closing, then by
 * column) regardless of other locators interleaved in the list.
 */
@SuppressWarnings({"PMD.JUnitAssertionsShouldIncludeMessage", "nls"})
public class ExtendedFormattingConfigBasedStreamSortLocatorsTest {

  private static final String OPEN_1 = "open@1";
  private static final String OPEN_5 = "open@5";

  private final FormattingConfig config = new FormattingConfig(null, null, null);

  private final ExtendedFormattingConfigBasedStream stream = new ExtendedFormattingConfigBasedStream(null, null, config, null, null, false, null);

  @Test
  public void testFixedLocatorsAreSortedByColumn() throws ReflectiveOperationException {
    List<ElementLocator> locators = new ArrayList<>(List.of(opening(5), opening(1)));
    sortLocators(locators);
    assertEquals(List.of(OPEN_1, OPEN_5), describeFixedLocators(locators));
  }

  @Disabled("Documents RO-1, see formal/BUGS.md; enable with the fix")
  @Test
  public void testFixedLocatorsAreSortedByColumnAcrossOtherLocator() throws ReflectiveOperationException {
    List<ElementLocator> locators = new ArrayList<>(List.of(opening(5), new NoFormatLocator(config), opening(1)));
    sortLocators(locators);
    assertEquals(List.of(OPEN_1, OPEN_5), describeFixedLocators(locators));
  }

  @Disabled("Documents RO-1, see formal/BUGS.md; enable with the fix")
  @Test
  public void testOpeningFixedLocatorPrecedesClosingAcrossOtherLocator() throws ReflectiveOperationException {
    List<ElementLocator> locators = new ArrayList<>(List.of(closing(5), new NoFormatLocator(config), opening(5)));
    sortLocators(locators);
    assertEquals(List.of(OPEN_5, "close@5"), describeFixedLocators(locators));
  }

  private FixedLocator opening(final int column) {
    return new FixedLocator(config, column, false, false, false);
  }

  private FixedLocator closing(final int column) {
    FixedLocator locator = new FixedLocator(config, column, false, false, false);
    locator.after(XtextFactory.eINSTANCE.createKeyword());
    return locator;
  }

  private void sortLocators(final List<ElementLocator> locators) throws ReflectiveOperationException {
    Method method = ExtendedFormattingConfigBasedStream.class.getDeclaredMethod("sortLocators", List.class);
    method.setAccessible(true); // NOPMD AvoidAccessibilityAlteration - sortLocators is private
    try {
      method.invoke(stream, locators);
    } catch (InvocationTargetException e) {
      throw new IllegalStateException(e.getCause());
    }
  }

  private static List<String> describeFixedLocators(final List<ElementLocator> locators) {
    List<String> result = new ArrayList<>();
    for (ElementLocator locator : locators) {
      if (locator instanceof FixedLocator fixed) {
        result.add((fixed.getLeft() == null ? "open@" : "close@") + fixed.getColumn());
      }
    }
    return result;
  }
}
