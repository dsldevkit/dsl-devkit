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
package com.avaloq.tools.ddk.xtext.test.ui.folding;

import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Position;
import org.eclipse.xtext.ui.editor.folding.DefaultFoldedPosition;
import org.eclipse.xtext.ui.editor.folding.FoldedPosition;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionProvider;
import org.junit.Assert;
import org.junit.Test;

import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextEditorTest;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Base class for folding tests.
 */
public abstract class AbstractFoldingTest extends AbstractXtextEditorTest {

  /**
   * Checks that the folding region of the document under test are located at the expected positions.
   *
   * @param expectedPositions
   *          the offsets and lengths of the folding regions
   */
  protected void assertFoldingRegions(final Set<Position> expectedPositions) {
    Comparator<Position> comparator = new Comparator<Position>() {
      @Override
      public int compare(final Position p1, final Position p2) {
        return Integer.signum(p1.getOffset() - p2.getOffset());
      }
    };

    final List<Position> expectedList = Lists.newArrayList(expectedPositions);
    Collection<FoldedPosition> foldingRegions = getXtextTestUtil().get(IFoldingRegionProvider.class).getFoldingRegions(getDocument());
    final List<Position> actualPositions = Lists.newArrayList(Iterables.transform(foldingRegions, new Function<FoldedPosition, Position>() {
      @Override
      public Position apply(final FoldedPosition from) {
        return new Position(from.offset, from.length);
      }
    }));

    List<Position> unmatchedExpectedPositions = Lists.newArrayList(Iterables.filter(expectedPositions, new Predicate<Position>() {
      @Override
      public boolean apply(final Position p) {
        return !actualPositions.contains(p);
      }
    }));
    if (!unmatchedExpectedPositions.isEmpty()) {
      Collections.sort(unmatchedExpectedPositions, comparator);

      List<Position> unmatchedActualPositions = Lists.newArrayList(Iterables.filter(actualPositions, new Predicate<Position>() {
        @Override
        public boolean apply(final Position p) {
          return !expectedList.contains(p);
        }
      }));
      Collections.sort(unmatchedActualPositions, comparator);
      StringBuffer message = new StringBuffer();
      message.append("Unmatched Expected Positions:").append(unmatchedExpectedPositions).append('\n');
      message.append("Unmatched Actual Positions:").append(unmatchedActualPositions);
      Assert.assertTrue(message.toString(), unmatchedExpectedPositions.isEmpty());
    }
  }

  /**
   * Verifies that FoldedPositions are valid.
   * If the assertion fails that is probably due to an ITextRegion.EMPTY_REGION being provided for the object's significant text region.
   */
  @Test
  @BugTest("ACF-2605")
  public void testFoldedPositions() {
    Collection<FoldedPosition> foldingRegions = getXtextTestUtil().get(IFoldingRegionProvider.class).getFoldingRegions(getDocument());
    for (DefaultFoldedPosition foldedPosition : Iterables.filter(foldingRegions, DefaultFoldedPosition.class)) {
      try {
        Assert.assertFalse("Illegal significant region for FoldedPosition " + foldedPosition, foldedPosition.computeCaptionOffset(getDocument()) < 0);
        /* If the above assertion fails that is probably due to an ITextRegion.EMPTY_REGION being provided for the object's significant text region. */
      } catch (BadLocationException e) {
        fail("Bad location for FoldedPosition: " + e.getMessage());
      }
    }
  }
}
