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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.ui.IViewReference;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


/**
 * A custom matcher that identifies a view by applying a regular expression
 * on its name.
 */
public class ViewRegExMatcher extends BaseMatcher<IViewReference> {
  /** The regular expression matcher for the view's name. */
  private final Matcher regExMatcher;

  public ViewRegExMatcher(final String regex) {
    Pattern pattern = Pattern.compile(regex);
    this.regExMatcher = pattern.matcher("");
  }

  /** {@inheritDoc} */
  public boolean matches(final Object item) {
    if (item instanceof IViewReference) {
      IViewReference ref = (IViewReference) item;
      regExMatcher.reset(ref.getTitle());
      if (regExMatcher.matches()) {
        return true;
      }
    }
    return false;
  }

  /** {@inheritDoc} */
  public void describeTo(final Description description) {
    description.appendText("a view with title matching regex");
  }

  /**
   * Convenience method to obtain a matcher of this type.
   * 
   * @param regex
   *          the regex
   * @return the base matcher
   */
  public static BaseMatcher<IViewReference> withName(final String regex) {
    return new ViewRegExMatcher(regex);
  }

}
