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
package com.avaloq.tools.ddk.xtext.test.ui.hyperlinking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.hyperlinking.XtextHyperlink;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;

import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextEditorTest;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;


/**
 * Base class for hyperlink helper tests.
 */
public abstract class AbstractHyperlinkHelperTest extends AbstractXtextEditorTest {

  /** The Constant LEAF_NOT_FOUND_VALUE is returned when a leaf node is not found. */
  protected static final int LEAF_NOT_FOUND_VALUE = -1;

  /**
   * Checks if there is exactly one hyperlink at the specified offset.
   *
   * @param offset
   *          the position at which to look for a hyperlink
   */
  protected void assertOffsetHasHyperlink(final int offset) {
    Assert.assertEquals(1, getOffsetHyperlinks(offset).size());
  }

  /**
   * Checks that there is no hyperlink at the specified offset.
   *
   * @param offset
   *          the position at which to look for hyperlinks
   */
  protected void assertOffsetHasNoHyperlink(final int offset) {
    Assert.assertEquals(0, getOffsetHyperlinks(offset).size());
  }

  /**
   * Checks if there is exactly one hyperlink on the object marked by the given tag.
   *
   * @param tag
   *          The tag for which to find the offset where the hyperlink should be
   */
  protected void assertHasHyperlink(final int tag) {
    assertHasHyperlinks(tag, 1);
  }

  /**
   * Checks that there is no hyperlink on the object marked by the given tag.
   *
   * @param tag
   *          The tag for which to find the offset where the hyperlink should be
   */
  protected void assertNoHyperlink(final int tag) {
    assertHasHyperlinks(tag, 0);
  }

  /**
   * Checks if there is a given number of hyperlinks on the object marked by the given tag.
   *
   * @param tag
   *          the tag marking the object where the hyperlinks should be
   * @param numberOfHyperlinks
   *          number of expected hyperlinks
   */
  protected void assertHasHyperlinks(final int tag, final int numberOfHyperlinks) {
    Assert.assertEquals(numberOfHyperlinks, getHyperlinks(tag).size());
  }

  /**
   * Checks if there is a hyperlink on the object marked by the given tag and that it points to the target URI.
   *
   * @param tag
   *          the tag marking the object where the hyperlink should be
   * @param target
   *          the target URI pointed to by the hyperlink of the given tag
   */
  protected void assertHasHyperlinks(final int tag, final URI target) {
    Collection<URI> actualTargets = Sets.newLinkedHashSet();
    for (IHyperlink hyperlink : getHyperlinks(tag)) {
      if (hyperlink instanceof XtextHyperlink) {
        actualTargets.add(((XtextHyperlink) hyperlink).getURI());
      }
    }
    MatcherAssert.assertThat(actualTargets, CoreMatchers.hasItem(target));
  }

  /**
   * Checks if there is a given number of hyperlinks in the specified source on the object marked by the given tag.
   *
   * @param resource
   *          the resource in which to look for hyperlinks, must not be {@code null}
   * @param tag
   *          the tag marking the object where the hyperlinks should be
   * @param numberOfHyperlinks
   *          number of expected hyperlinks
   */
  protected void assertHasHyperlinks(final XtextResource resource, final int tag, final int numberOfHyperlinks) {
    Assert.assertEquals(numberOfHyperlinks, getHyperlinks(resource, tag).size());
  }

  /**
   * Retrieves the hyperlinks found at a given offset.
   *
   * @param offset
   *          the position at which to look for hyperlinks
   * @return a list of hyperlinks, never {@code null}
   */
  protected List<IHyperlink> getOffsetHyperlinks(final int offset) {
    return getOffsetHyperlinks(getXtextTestResource(), offset);
  }

  /**
   * Retrieves the hyperlinks found on the object marked by the given tag.
   *
   * @param tag
   *          the tag marking the object where the hyperlinks should be
   * @return a list of hyperlinks, never {@code null}
   */
  protected List<IHyperlink> getHyperlinks(final int tag) {
    return getHyperlinks((XtextResource) getObjectForTag(tag).eResource(), tag);
  }

  /**
   * Retrieves the hyperlinks found in a given source at a given offset.
   *
   * @param resource
   *          the resource in which to look for hyperlinks, must not be {@code null}
   * @param offset
   *          the position at which to look for hyperlinks
   * @return a list of hyperlinks, never {@code null}
   */
  protected List<IHyperlink> getOffsetHyperlinks(final XtextResource resource, final int offset) {
    IHyperlink[] hyperlinks = UIThreadRunnable.syncExec(new Result<IHyperlink[]>() {
      @Override
      public IHyperlink[] run() {
        return getTestUtil().get(IHyperlinkHelper.class).createHyperlinksByOffset(resource, offset, true);
      }
    });
    return hyperlinks != null ? Arrays.asList(hyperlinks) : new ArrayList<IHyperlink>();
  }

  /**
   * Retrieves the hyperlinks found on the object marked by the given tag in the given source.
   *
   * @param resource
   *          the resource in which to look for hyperlinks, must not be {@code null}
   * @param tag
   *          tag marking the object where the hyperlinks should be
   * @return a list of hyperlinks, never {@code null}
   */
  protected List<IHyperlink> getHyperlinks(final XtextResource resource, final int tag) {
    return getOffsetHyperlinks(resource, getOffsetForTag(tag));
  }

  /**
   * Gets the offset for given text by analyzing the parse tree and looking for leaf nodes having
   * a text attribute matching given value. Returns the first instance found and an error value if
   * no match found.
   *
   * @param model
   *          the model
   * @param text
   *          the text
   * @return the offset for text
   */
  protected int getOffsetForText(final EObject model, final String text) {
    Iterable<ILeafNode> parseTreeNodes = NodeModelUtils.getNode(model).getLeafNodes();

    try {
      ILeafNode result = Iterables.find(parseTreeNodes, new Predicate<ILeafNode>() {
        @Override
        public boolean apply(final ILeafNode input) {
          return text.equals(input.getText());
        }
      });
      return result.getOffset();
    } catch (NoSuchElementException e) {
      return LEAF_NOT_FOUND_VALUE;
    }
  }
}
