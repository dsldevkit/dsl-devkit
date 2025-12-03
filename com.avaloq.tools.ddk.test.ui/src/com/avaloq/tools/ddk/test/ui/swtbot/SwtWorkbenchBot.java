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
package com.avaloq.tools.ddk.test.ui.swtbot;

import static org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartId;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.allOf;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.inGroup;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withId;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withMnemonic;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withStyle;
import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withTooltip;

import java.util.List;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import com.avaloq.tools.ddk.test.ui.swtbot.util.PreferenceUtil;


/**
 * Extended {@link SWTWorkbenchBot}.
 */
@SuppressWarnings("nls")
public class SwtWorkbenchBot extends SWTWorkbenchBot {
  private static final int DELAY_WIZARD_PAGE = 1000;
  private static final String TIMEOUT_MSG = "Timeout of {0} ms reached while waiting for Button {1} to become active";

  private ImpatientSwtWorkbenchBot impatientBot;

  @Override
  public void closeAllShells() {
    new FixedDefaultWorkbench(this).closeShellsMatchingName(s -> !s.isEmpty());
  }

  /**
   * Close welcome page.
   */
  public void closeWelcomePage() {
    final List<SWTBotView> introViews = views(withPartId("org.eclipse.ui.internal.introview"));
    for (SWTBotView introView : introViews) {
      introView.close();
    }
  }

  /**
   * Checks if a {@link SWTBotShell} with the given title exists and is open. This call does not wait for the shell to appear.
   *
   * @param title
   *          the title of the {@link SWTBotShell} to look for
   * @return {@code true} if the shell was found open, {@code false} otherwise
   */
  public boolean hasShell(final String title) {
    SWTBotShell shell = getShell(title);
    return shell != null && shell.isOpen();
  }

  /**
   * Returns the {@link SWTBotShell} with the given title. This call does not wait for the shell to appear.
   *
   * @param title
   *          the title of the {@link SWTBotShell} to look for
   * @return the {@link SWTBotShell} with the given title if it was found, {@code null} otherwise
   */
  public SWTBotShell getShell(final String title) {
    for (SWTBotShell shell : shells()) {
      if (shell.getText().equals(title)) {
        return shell;
      }
    }
    return null;
  }

  /**
   * Closes the shell with the given title if it exists. This call does not wait for the shell to appear.
   *
   * @param title
   *          the title of the shell to close
   * @return {@code true} if the shell existed and was open, {@code false} otherwise
   */
  public boolean closeShell(final String title) {
    SWTBotShell shell = getShell(title);
    if (shell != null && shell.isOpen()) {
      shell.close();
      return true;
    }
    return false;
  }

  /**
   * Checks if a {@link SWTBotEditor} with the given title exists.
   *
   * @param editorTitle
   *          the title of the editor
   * @return {@code true} if the editor was found open, {@code false} otherwise
   */
  public boolean hasEditor(final String editorTitle) {
    for (SWTBotEditor editor : editors()) {
      if (editor.getTitle().equals(editorTitle)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a {@link SWTBotEditor} is available.
   *
   * @param swtBotEditor
   *          the {@link SWTBotEditor}
   * @return {@code true} if the editor was found open, {@code false} otherwise
   */
  public boolean hasEditor(final SWTBotEditor swtBotEditor) {
    for (SWTBotEditor editor : editors()) {
      if (editor.getReference().equals(swtBotEditor.getReference())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a {@link SWTBotView} exist.
   *
   * @param swtBotView
   *          the {@link SWTBotView}
   * @return {@code true} if the view was found, {@code false} otherwise
   */
  public boolean hasView(final SWTBotView swtBotView) {
    for (SWTBotView view : views()) {
      if (view.getReference().equals(swtBotView.getReference())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if a {@link SWTBotView} with the given view identifier exist.
   *
   * @param viewId
   *          the id of the view
   * @return {@code true} if the view was found, {@code false} otherwise
   */
  public boolean hasView(final String viewId) {
    return getView(viewId) != null;
  }

  /**
   * Returns the {@link SWTBotView} with the given view identifier.
   *
   * @param viewId
   *          the id of the view
   * @return the {@code SWTBotView} with the given view identifier, {@code null} if no such view exists
   */
  public SWTBotView getView(final String viewId) {
    for (SWTBotView view : views()) {
      if (view.getReference().getId().equals(viewId)) {
        return view;
      }
    }
    return null;
  }

  /**
   * Checks if an active {@link SWTBotEditor} is available.
   *
   * @return {@code true} if an active editor was found, {@code false} otherwise
   */
  public boolean hasActiveEditor() {
    for (SWTBotEditor editor : editors()) {
      if (editor.isActive()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Waits until the workbench window has focus.
   * <p>
   * <em>Note</em>: This method has no timeout.
   * </p>
   */
  public void waitUntilFocused() {
    while (getFocusedWidget() == null) {
      sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
    }
  }

  /**
   * Waits until a wizard page with the given title is visible and active.
   *
   * @param wizardPageTitle
   *          the title of the wizard page
   */
  public void waitUntilWizardPageAppears(final String wizardPageTitle) {
    waitUntil(new ICondition() {
      @Override
      @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
      public boolean test() {
        return syncExec(new BoolResult() {
          @Override
          public Boolean run() {
            SWTBotShell wizardShell = activeShell();
            return wizardShell.widget.getData() instanceof WizardDialog dialog && dialog.getCurrentPage().getTitle().equals(wizardPageTitle);
          }
        });
      }

      @Override
      public void init(final SWTBot paramSWTBot) {
      }

      @Override
      public String getFailureMessage() {
        return NLS.bind("Failed to find the wizard page with title ''{0}''.", wizardPageTitle);
      }
    });
    // additionally, we need to wait until the wizard page has finished loading (button cancel is enabled).
    waitUntil(new ICondition() {
      @Override
      @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
      public boolean test() {
        return button("Cancel").isEnabled();
      }

      @Override
      public void init(final SWTBot bot) {
      }

      @Override
      public String getFailureMessage() {
        return NLS.bind("Failed while waiting for the wizard page with title ''{0}''.", wizardPageTitle);
      }
    });
    sleep(DELAY_WIZARD_PAGE);
  }

  /**
   * Click the button with the given label as soon as it is enabled. Throws WrappedException
   * if the button does not get enabled within the default {@link SWTBotPreferences.TIMEOUT}.
   *
   * @param buttonLabel
   *          the label of the {@link SWTBotButton} to be clicked
   */
  public void clickButton(final String buttonLabel) {
    clickButton(button(buttonLabel));
  }

  /**
   * Click the button with the given label as soon as it is enabled. Throws WrappedException
   * if the button does not get enabled within the default {@link SWTBotPreferences.TIMEOUT}.
   *
   * @param buttonLabel
   *          the label of the {@link SWTBotButton} to be clicked
   * @param timeout
   *          the timeout to wait for. When timeout is reached, a WrappedException is thrown.
   */
  public void clickButton(final String buttonLabel, final long timeout) {
    clickButton(button(buttonLabel), timeout);
  }

  /**
   * Click the given button as soon as it is enabled. Throws WrappedException
   * if the button does not get enabled within the default {@link SWTBotPreferences.TIMEOUT}.
   *
   * @param button
   *          the {@link SWTBotButton} to be clicked
   */
  public void clickButton(final SWTBotButton button) {
    clickButton(button, SWTBotPreferences.TIMEOUT);
  }

  /**
   * Click the given button as soon as it is enabled.
   *
   * @param button
   *          the {@link SWTBotButton} to be clicked
   * @param timeout
   *          the timeout to wait for. When timeout is reached, a WrappedException is thrown.
   * @throws WrappedException
   *           if the button does not become enabled within the timeout
   */
  public void clickButton(final SWTBotButton button, final long timeout) {
    final long endTimeMillis = System.currentTimeMillis() + timeout;
    while (!button.isEnabled() && System.currentTimeMillis() < endTimeMillis) {
      sleep(SWTBotPreferences.DEFAULT_POLL_DELAY);
    }
    if (button.isEnabled()) {
      new SwtBotButton(button).click();
    } else {
      throw new WrappedException(NLS.bind(TIMEOUT_MSG, timeout, button.getText()), null);
    }
  }

  /**
   * Expands the given {@link SWTBotTreeItem} making sure the children are properly refreshed.
   *
   * @param treeItem
   *          the {@link SWTBotTreeItem} to expand
   * @return the expanded {@link SWTBotTreeItem}
   */
  public SWTBotTreeItem expandTreeItem(final SWTBotTreeItem treeItem) {
    CoreSwtbotTools.waitForItem(this, treeItem);
    return treeItem;
  }

  /**
   * Checks if there is a label with the given text, and if it is visible.
   *
   * @param text
   *          the label text
   * @return {@code true} if a label with the given text exists and is visible, {@code false} otherwise
   */
  public boolean hasLabel(final String text) {
    SWTBotLabel label = getLabel(text);
    return label != null && label.isVisible();
  }

  /**
   * Returns the first {@link SWTBotLabel} with the given text, or {@code null} if none was found.
   *
   * @param text
   *          the label text
   * @return the first {@link SWTBotLabel} with the given text, or {@code null} if none was found
   */
  public SWTBotLabel getLabel(final String text) {
    List<Label> labels = finder.findControls(new BaseMatcher<Label>() {
      @Override
      public boolean matches(final Object item) {
        return item instanceof Label && ((Label) item).getText().equals(text);
      }

      @Override
      public void describeTo(final Description description) {
      }
    });
    if (labels.isEmpty()) {
      return null;
    } else {
      return new SWTBotLabel(labels.get(0));
    }
  }

  /**
   * Creates an {@link SwtBotRadio} with the given label and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param label
   *          the label on the widget, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@Code label}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioWithLabel(final String label, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), WidgetMatcherFactory.withLabel(label, finder), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given text and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param mnemonicText
   *          the mnemonicText on the widget, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code mnemonicText}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radio(final String mnemonicText, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), withMnemonic(mnemonicText), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given tooltip and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param tooltip
   *          the tooltip on the widget, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code tooltip}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioWithTooltip(final String tooltip, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), withTooltip(tooltip), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given key, the given key and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param key
   *          the key set on the widget, must not be {@code null}
   * @param value
   *          the value for the key, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code key/value}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioWithId(final String key, final String value, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), withId(key, value), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given value and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param value
   *          the value for the key {@link org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences#DEFAULT_KEY}, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code value}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioWithId(final String value, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), withId(value), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given group and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param inGroup
   *          the inGroup on the widget, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code inGroup}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioInGroup(final String inGroup, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), inGroup(inGroup), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code none}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radio(final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given label, the given group and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param label
   *          the label on the widget, must not be {@code null}
   * @param inGroup
   *          the inGroup on the widget, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code label} with the specified {@code inGroup}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioWithLabelInGroup(final String label, final String inGroup, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), WidgetMatcherFactory.withLabel(label, finder), inGroup(inGroup), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given text, the given group and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param mnemonicText
   *          the mnemonicText on the widget, must not be {@code null}
   * @param inGroup
   *          the inGroup on the widget, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code mnemonicText} with the specified {@code inGroup}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioInGroup(final String mnemonicText, final String inGroup, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), withMnemonic(mnemonicText), inGroup(inGroup), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Creates an {@link SwtBotRadio} with the given tooltip, the given group and the given index.
   * <p>
   * <em>Note</em>: This method may throw a {@link org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException} if the widget is not found or is disposed.
   * </p>
   *
   * @param tooltip
   *          the tooltip on the widget, must not be {@code null}
   * @param inGroup
   *          the inGroup on the widget, must not be {@code null}
   * @param index
   *          the index of the widget, must not be {@code null}
   * @return a {@link SwtBotRadio} with the specified {@code tooltip} with the specified {@code inGroup}, never {@code null}
   */
  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public SwtBotRadio radioWithTooltipInGroup(final String tooltip, final String inGroup, final int index) {
    Matcher matcher = allOf(widgetOfType(Button.class), withTooltip(tooltip), inGroup(inGroup), withStyle(SWT.RADIO, "SWT.RADIO"));
    return new SwtBotRadio((Button) widget(matcher, index), matcher);
  }

  /**
   * Opens preference dialog to a certain path.
   *
   * @param preferencePath
   *          the path to the preference one wants to open, must not be {@code null} or empty
   */
  public void openPreferencePage(final String... preferencePath) {
    PreferenceUtil.openPreferenceDialog(this, preferencePath);
  }

  /**
   * Closes the preference page.
   */
  public void closePreferencePage() {
    PreferenceUtil.closePreferenceDialog(this);
  }

  /**
   * A {@link SWTWorkbenchBot} that only waits a minimal amount of time for widgets to appear.
   *
   * @return the fast bot, never {@code null}
   */
  public SWTWorkbenchBot fastBot() {
    if (impatientBot == null) {
      impatientBot = new ImpatientSwtWorkbenchBot();
    }
    return impatientBot;
  }

  /**
   * Checks for the presence of a table with the given label similar to {@link #tableWithLabel(String)} without throwing a {@link WidgetNotFoundException}.
   *
   * @param label
   *          the label on the widget
   * @return {@code true} or {@code false} without throwing any {@link WidgetNotFoundException}s
   */
  public boolean hasTableWithLabel(final String label) {
    return widgetNotFoundExceptionToBoolean(() -> fastBot().tableWithLabel(label));
  }

  /**
   * Checks that the given function can supply a widget without throwing a {@link WidgetNotFoundException}.
   *
   * @param widgetNotFoundExceptionThrower
   *          the supplier function, must not be {@code null}
   * @return {code true} if the function executes without throwing a {@link WidgetNotFoundException}, {@code false} otherwise
   */
  private boolean widgetNotFoundExceptionToBoolean(final Supplier<? extends AbstractSWTBot<? extends Widget>> widgetNotFoundExceptionThrower) {
    try {
      widgetNotFoundExceptionThrower.get();
    } catch (WidgetNotFoundException e) {
      return false;
    }
    return true;
  }

  /** Helper that doesn't wait for widgets to appear as long as the default settings. */
  private static final class ImpatientSwtWorkbenchBot extends SWTWorkbenchBot {
    static final long MAX_RETRIES = 4; // Actual number of retries will almost certainly be at least 1 fewer than this.
    static final long SHORT_TIME_OUT = 64;
    static final long SHORT_INTERVAL = SHORT_TIME_OUT / MAX_RETRIES;

    @Override
    public void waitUntilWidgetAppears(final ICondition waitForWidget) {
      try {
        waitUntil(waitForWidget, SHORT_TIME_OUT, SHORT_INTERVAL);
      } catch (TimeoutException e) {
        throw new WidgetNotFoundException("Could not find widget.", e); //$NON-NLS-1$
      }
    }
  }

}
