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

import org.eclipse.ui.IEditorReference;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;


/**
 * A custom matcher that identifies an editor by applying a regular expression
 * on its name.
 */
public class EditorRegExMatcher extends BaseMatcher<IEditorReference> {
  /** The regular expression matcher for the editor's name. */
  private final Matcher regExMatcher;

  public EditorRegExMatcher(final String regex) {
    super();
    Pattern pattern = Pattern.compile(regex);
    this.regExMatcher = pattern.matcher("");
  }

  /** {@inheritDoc} */
  public boolean matches(final Object item) {
    if (item instanceof IEditorReference) {
      IEditorReference ref = (IEditorReference) item;
      regExMatcher.reset(ref.getName());
      if (regExMatcher.matches()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Describe to.
   * 
   * @param description
   *          the description {@inheritDoc}
   */
  public void describeTo(final Description description) {
    description.appendText("an editor with name matching regex");
  }

  /**
   * Convenience method to obtain a matcher of this type.
   * 
   * @param regex
   *          the regex
   * @return the base matcher
   */
  public static BaseMatcher<IEditorReference> withName(final String regex) {
    return new EditorRegExMatcher(regex);
  }

}
