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
package com.avaloq.tools.ddk.xtext.test.ui.syntaxcoloring;

import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

import com.avaloq.tools.ddk.xtext.test.ui.AbstractXtextEditorUiTest;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


/**
 * Base class for syntax coloring tests.
 */
public abstract class AbstractSyntaxColoringTest extends AbstractXtextEditorUiTest {
  private static final String STYLE_ASSERTION_FAILURE_MESSAGE = "The text style at the offset %d must match the expected style.";

  /** The Constant LEAF_NOT_FOUND_VALUE is returned when a leaf node is not found. */
  protected static final int LEAF_NOT_FOUND_VALUE = -1;

  public static final TextStyle TEXT_STYLE_DEFAULT = new TextStyle();
  public static final TextStyle TEXT_STYLE_ERROR = new TextStyle();
  public static final TextStyle TEXT_STYLE_NUMBER = new TextStyle();
  public static final TextStyle TEXT_STYLE_STRING = new TextStyle();
  public static final TextStyle TEXT_STYLE_COMMENT = new TextStyle();
  public static final TextStyle TEXT_STYLE_KEYWORD = new TextStyle();
  public static final TextStyle TEXT_STYLE_PUNCTUATION = new TextStyle();

  static {
    // CHECKSTYLE:OFF
    // default
    // TEXT_STYLE_DEFAULT.setBackgroundColor(new RGB(255, 255, 255));
    // TEXT_STYLE_DEFAULT.setColor(new RGB(0, 0, 0));
    // error
    // TEXT_STYLE_ERROR.setColor(new RGB(255, 0, 0));
    // number
    TEXT_STYLE_NUMBER.setColor(new RGB(125, 125, 125));
    // string
    TEXT_STYLE_STRING.setColor(new RGB(42, 0, 255));
    // comment
    TEXT_STYLE_COMMENT.setColor(new RGB(63, 127, 95));
    // keyword
    TEXT_STYLE_KEYWORD.setColor(new RGB(127, 0, 85));
    TEXT_STYLE_KEYWORD.setStyle(SWT.BOLD);
    // punctuation
    // CHECKSTYLE:ON
  }

  /**
   * Support for syntax coloring assertions.
   */
  private class AbstractSyntaxColoringAssertion extends AbstractModelAssertion {

    /** The syntax coloring text style to check. */
    private final TextStyle textStyle;

    /**
     * Constructor.
     */
    protected AbstractSyntaxColoringAssertion(final TextStyle textStyle) {
      this.textStyle = textStyle;
    }

    /** {@inheritDoc} */
    @Override
    public void apply(final EObject root, final Integer pos) {
      assertTextStyle(pos, textStyle);
    }

  }

  /**
   * Checks the given offset for the given {@link TextStyle}.
   *
   * @param editor
   *          the {@link XtextEditor}
   * @param offset
   *          the position to check
   * @param textAttribute
   *          the expected {@link TextStyle}
   */
  public void assertTextAttribute(final XtextEditor editor, final int offset, final TextAttribute textAttribute) {
    getBot().waitUntil(new DefaultCondition() {
      @Override
      public boolean test() {
        return areEqualStyleRanges(createStyleRange(offset, 1, textAttribute), getStyleRange(editor, offset));
      }

      @Override
      public String getFailureMessage() {
        return String.format(STYLE_ASSERTION_FAILURE_MESSAGE, offset);
      }
    });
  }

  /**
   * Checks the given offset for the given {@link TextStyle}.
   *
   * @param editor
   *          the {@link XtextEditor}
   * @param offset
   *          the position to check
   * @param textStyle
   *          the expected {@link TextStyle}
   */
  public void assertTextStyle(final XtextEditor editor, final int offset, final TextStyle textStyle) {
    assertTextStyle(editor, offset, textStyle, getBot());
  }

  /**
   * Checks the given offset for the given {@link TextStyle}.
   *
   * @param editor
   *          the {@link XtextEditor}
   * @param offset
   *          the position to check
   * @param textStyle
   *          the expected {@link TextStyle}
   * @param bot
   *          instance of swt bot
   */
  public static void assertTextStyle(final XtextEditor editor, final int offset, final TextStyle textStyle, final SWTWorkbenchBot bot) {
    bot.waitUntil(new DefaultCondition() {
      @Override
      public boolean test() {
        return areEqualStyleRanges(createStyleRange(offset, 1, createTextAttribute(textStyle)), getStyleRange(editor, offset));
      }

      @Override
      public String getFailureMessage() {
        return String.format(STYLE_ASSERTION_FAILURE_MESSAGE, offset);
      }
    });
  }

  /**
   * Checks if the two given {@link StyleRange}s are equal, with respect to start, length, font, style, and colors. Underlines are ignored.
   *
   * @param styleRangeA
   *          the first {@link StyleRange}
   * @param styleRangeB
   *          the second {@link StyleRange}
   * @return {@code true} if the given {@link StyleRange}s are equal, {@code false} otherwise
   */
  @SuppressWarnings("PMD.CompareObjectsWithEquals")
  public static boolean areEqualStyleRanges(final StyleRange styleRangeA, final StyleRange styleRangeB) {
    if (styleRangeA == styleRangeB) {
      return true;
    }
    boolean result = styleRangeA != null && styleRangeB != null;
    result &= styleRangeA.start == styleRangeB.start;
    result &= styleRangeA.length == styleRangeB.length;
    result &= styleRangeA.fontStyle == styleRangeB.fontStyle;
    result &= areEqualTextStyles(styleRangeA, styleRangeB);
    return result;
  }

  /**
   * Checks if the two given {@link org.eclipse.swt.graphics.TextStyle}s are equal, with respect to colors, font. Underlines and other attributes are ignored.
   *
   * @param textStyleA
   *          the first {@link org.eclipse.swt.graphics.TextStyle}
   * @param textStyleB
   *          the second {@link org.eclipse.swt.graphics.TextStyle}
   * @return {@code true} if the given {@link org.eclipse.swt.graphics.TextStyle}s are equal, {@code false} otherwise
   */
  @SuppressWarnings("PMD.CompareObjectsWithEquals")
  public static boolean areEqualTextStyles(final org.eclipse.swt.graphics.TextStyle textStyleA, final org.eclipse.swt.graphics.TextStyle textStyleB) {
    if (textStyleA == textStyleB) {
      return true;
    }
    if (textStyleA == null || textStyleB == null) {
      return false;
    }
    if (textStyleA.foreground != null) {
      if (!textStyleA.foreground.equals(textStyleB.foreground)) {
        return false;
      }
    } else if (textStyleB.foreground != null) {
      return false;
    }
    if (textStyleA.font != null) {
      if (!textStyleA.font.equals(textStyleB.font)) {
        return false;
      }
    } else if (textStyleB.font != null) {
      return false;
    }
    return true;
  }

  /**
   * Retrieves the {@link StyleRange} found at a given offset in the given {@link XtextEditor}.
   *
   * @param editor
   *          the {@link XtextEditor}
   * @param offset
   *          the position for which to retrieve the {@link StyleRange}
   * @return the {@link StyleRange} found at the given offset
   */
  public static StyleRange getStyleRange(final XtextEditor editor, final int offset) {
    StyleRange styleRange = UIThreadRunnable.syncExec(new Result<StyleRange>() {
      @Override
      public StyleRange run() {
        StyledText styledText = editor.getInternalSourceViewer().getTextWidget();
        return styledText.getStyleRangeAtOffset(offset);
      }
    });
    if (styleRange == null) {
      // if no style range was found, then the default style is used
      return createStyleRange(offset, 1, createTextAttribute(TEXT_STYLE_DEFAULT));
    }
    return styleRange;
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
  public static int getOffsetForText(final EObject model, final String text) {
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

  /**
   * Creates a {@link StyleRange} from the given parameters.
   *
   * @param offset
   *          the offset
   * @param length
   *          the length of the range
   * @param textAttribute
   *          the {@link TextAttribute}
   * @return a {@link StyleRange} from the given parameters
   */
  public static StyleRange createStyleRange(final int offset, final int length, final TextAttribute textAttribute) {
    int style = textAttribute.getStyle();
    int fontStyle = style & (SWT.ITALIC | SWT.BOLD | SWT.NORMAL);
    StyleRange styleRange = new StyleRange(offset, length, textAttribute.getForeground(), textAttribute.getBackground(), fontStyle);
    styleRange.strikeout = (style & TextAttribute.STRIKETHROUGH) != 0;
    styleRange.underline = (style & TextAttribute.UNDERLINE) != 0;
    styleRange.font = textAttribute.getFont();
    return styleRange;
  }

  /**
   * Creates a {@link TextAttribute} from the given {@link TextStyle}.
   *
   * @param textStyle
   *          a {@link TextStyle}
   * @return a {@link TextAttribute} from the given {@link TextStyle}
   */
  public static TextAttribute createTextAttribute(final TextStyle textStyle) {
    int style = textStyle.getStyle();
    Font fontFromFontData = EditorUtils.fontFromFontData(textStyle.getFontData());
    return new TextAttribute(EditorUtils.colorFromRGB(textStyle.getColor()), EditorUtils.colorFromRGB(textStyle.getBackgroundColor()), style, fontFromFontData);
  }

  /**
   * Registers a new marker with the given text style.
   * <p>
   * The object at the position that will be assigned by the marker in the test file is expected to be styled as the given parameter.
   * </p>
   *
   * @param textStyle
   *          text style that is expected on the EObject at the position of the marker, not {@code null}
   * @return
   *         unique marker that can be used in the input string to mark a position that should be validated, never {@code null}
   */
  protected String textStyle(final TextStyle textStyle) {
    return addAssertion(new AbstractSyntaxColoringAssertion(textStyle));
  }

  /**
   * Checks the given offset for the given {@link TextAttribute}.
   *
   * @param offset
   *          the position to check
   * @param textAttribute
   *          the expected {@link TextAttribute}
   */
  protected void assertTextAttribute(final int offset, final TextAttribute textAttribute) {
    assertTextAttribute(getEditor(), offset, textAttribute);
  }

  /**
   * Checks the given offset for the given {@link TextStyle}.
   *
   * @param offset
   *          the position to check
   * @param textStyle
   *          the expected {@link TextStyle}
   */
  protected void assertTextStyle(final int offset, final TextStyle textStyle) {
    assertTextStyle(getEditor(), offset, textStyle);
  }

  /**
   * Retrieves the {@link StyleRange} found at a given offset.
   *
   * @param offset
   *          the position for which to retrieve the {@link StyleRange}
   * @return the {@link StyleRange} found at a given offset
   */
  protected StyleRange getStyleRange(final int offset) {
    return getStyleRange(getEditor(), offset);
  }
}

