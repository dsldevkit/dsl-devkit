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
package com.avaloq.tools.ddk.xtext.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.avaloq.tools.ddk.xtext.builder.BuilderParticipantSettings;


/**
 * Adds the workspace-wide "disable DDK code generation" master switch on top of a DDK language's Compiler preference page content. The switch is one shared
 * preference for all DDK languages (see {@link BuilderParticipantSettings}), surfaced identically on the Check, Scope, Export and Format Compiler pages. All
 * live instances mirror each other instantly (before Apply): toggling the switch on one page updates it on every other open page, so the four checkboxes always
 * agree and no page can save a stale value over another page's change. Nothing is persisted until the dialog applies; Cancel discards the pending state.
 * <p>
 * While active, the per-language options below the switch are grayed out: they keep their values but have no effect, because the builder participants skip
 * generation altogether. Each control's own enablement is captured before graying out and restored afterwards, so options the stock page keeps disabled (e.g.
 * controls dependent on an unchecked option) are not force-enabled. When the switch is untouched, the stock page enablement is never modified.
 */
public final class BuilderParticipantMasterSwitch {

  /** Live instances within the preference dialog; used to mirror the pending (unsaved) state across the DDK languages' pages. */
  private static final List<BuilderParticipantMasterSwitch> INSTANCES = new CopyOnWriteArrayList<>();

  private final Composite container;
  private final Button checkbox;
  private final Control languageContent;

  /** Pre-gray-out enablement of every control in the language content; non-null exactly while the content is grayed out. */
  private Map<Control, Boolean> enablementSnapshot;

  /**
   * Creates the master switch above the language-specific content.
   *
   * @param parent
   *          the parent composite, must not be {@code null}
   * @param languageContentFactory
   *          creates the language's own preference content (typically {@code super::doCreateContents}), must not be {@code null}
   */
  public BuilderParticipantMasterSwitch(final Composite parent, final Function<Composite, Control> languageContentFactory) {
    container = new Composite(parent, SWT.NONE);
    final GridLayout layout = new GridLayout(1, false);
    layout.marginWidth = 0;
    layout.marginHeight = 0;
    container.setLayout(layout);
    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    checkbox = new Button(container, SWT.CHECK);
    checkbox.setText("Disable DDK code generation on workspace builds (all DDK languages)"); //$NON-NLS-1$
    checkbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

    languageContent = languageContentFactory.apply(container);
    if (languageContent.getLayoutData() == null) {
      languageContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    checkbox.setSelection(pendingOrPersistedState());
    checkbox.addListener(SWT.Selection, event -> broadcast(checkbox.getSelection()));
    INSTANCES.add(this);
    container.addListener(SWT.Dispose, event -> INSTANCES.remove(this));
    applyGrayOut(checkbox.getSelection());
  }

  /**
   * Returns the control containing the switch and the language content, to be returned from {@code doCreateContents}.
   *
   * @return the wrapping control, never {@code null}
   */
  public Control getControl() {
    return container;
  }

  /**
   * Persists the switch; to be called from the configuration block's {@code performOk}. All live instances agree on the value (they mirror each other), so it
   * is irrelevant which visited page saves first.
   *
   * @return {@code true} if this save re-enabled generation (flipped the persisted state from disabled to enabled), signalling that a rebuild of the workspace
   *         is required to catch up on changes made while generation was off
   */
  public boolean save() {
    final boolean wasDisabled = BuilderParticipantSettings.isGenerationDisabled();
    final boolean disabled = checkbox.getSelection();
    BuilderParticipantSettings.setGenerationDisabled(disabled);
    return wasDisabled && !disabled;
  }

  /**
   * Resets the switch to its default (generation enabled) on all open pages; to be called from the configuration block's {@code performDefaults}.
   */
  public void restoreDefaults() {
    broadcast(false);
  }

  private static boolean pendingOrPersistedState() {
    for (final BuilderParticipantMasterSwitch instance : INSTANCES) {
      if (!instance.checkbox.isDisposed()) {
        return instance.checkbox.getSelection();
      }
    }
    return BuilderParticipantSettings.isGenerationDisabled();
  }

  private static void broadcast(final boolean disabled) {
    for (final BuilderParticipantMasterSwitch instance : INSTANCES) {
      if (!instance.checkbox.isDisposed()) {
        instance.checkbox.setSelection(disabled);
        instance.applyGrayOut(disabled);
      }
    }
  }

  private void applyGrayOut(final boolean disabled) {
    if (disabled && enablementSnapshot == null) {
      enablementSnapshot = new HashMap<>();
      captureAndDisable(languageContent);
    } else if (!disabled && enablementSnapshot != null) {
      for (final Map.Entry<Control, Boolean> entry : enablementSnapshot.entrySet()) {
        if (!entry.getKey().isDisposed()) {
          entry.getKey().setEnabled(entry.getValue());
        }
      }
      enablementSnapshot = null;
    }
  }

  private void captureAndDisable(final Control control) {
    enablementSnapshot.put(control, control.getEnabled());
    control.setEnabled(false);
    if (control instanceof Composite) {
      for (final Control child : ((Composite) control).getChildren()) {
        captureAndDisable(child);
      }
    }
  }

}
