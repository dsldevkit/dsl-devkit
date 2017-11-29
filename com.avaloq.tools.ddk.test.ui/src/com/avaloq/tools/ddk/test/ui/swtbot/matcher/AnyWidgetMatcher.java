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
package com.avaloq.tools.ddk.test.ui.swtbot.matcher;

import org.eclipse.swt.widgets.Widget;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


/**
 * A custom matcher that simply matches all widgets presented by a {@link org.eclipse.swtbot.swt.finder.finders.Finder}.
 */
public class AnyWidgetMatcher extends BaseMatcher<Widget> {

  /** {@inheritDoc} */
  public boolean matches(final Object item) {
    if (item instanceof Widget) {
      return true;
    }
    return false;
  }

  /** {@inheritDoc} */
  public void describeTo(final Description description) {
    description.appendText("any widget not null");
  }

  /**
   * Convenience method to obtain a matcher of this type.
   * 
   * @return the base matcher
   */
  public static BaseMatcher<Widget> anyWidget() {
    return new AnyWidgetMatcher();
  }

}
