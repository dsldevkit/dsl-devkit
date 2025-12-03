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
package com.avaloq.tools.ddk.check.ui.wizard;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.ui.editor.tasks.dialogfields.DialogField;
import org.eclipse.xtext.ui.editor.tasks.dialogfields.IDialogFieldListener;
import org.eclipse.xtext.ui.editor.tasks.dialogfields.StringDialogField;

import com.avaloq.tools.ddk.check.validation.CheckJavaValidatorUtil;
import com.google.inject.Inject;


/**
 * The CustomCheck Wizard Page allows setting the name, the dsl
 * dependency, the grammarId for the new check catalog project.
 * Furthermore, the page allows setting the catalog name and a
 * new check file.
 */
@SuppressWarnings({"restriction", "nls"})
public class NewCheckProjectWizardPage extends NewTypeWizardPage {

  private static final int DEFAULT_COLUMN_NUMBER = 4;

  private final StringDialogField projectName;

  private IStatus projectNameStatus;

  @Inject
  private CheckJavaValidatorUtil validator;

  /** Field ID of the project input field. */
  protected static final String PROJECT = "NewCheckProjectWizardPage.project";

  /**
   * Constructor for NewCheckProjectWizardPage.
   */
  @Inject
  public NewCheckProjectWizardPage() {
    super(NewTypeWizardPage.CLASS_TYPE, "NewCheckProjectWizardPage");
    setTitle(Messages.PROJECT_WIZARD_TITLE);
    setDescription(Messages.PROJECT_WIZARD_DESCRIPTION);
    projectName = new StringDialogField();
    projectNameStatus = new StatusInfo();
  }

  private IDialogFieldListener getProjectValueListener() {
    return new IDialogFieldListener() {
      @Override
      public void dialogFieldChanged(final DialogField field) {
        projectNameStatus = validator.checkProjectName(getProjectName());
        handleFieldChanged(field.toString());
      }
    };
  }

  /**
   * Creates the control.
   *
   * @param parent
   *          the parent
   * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
   */
  @Override
  public void createControl(final Composite parent) {
    initializeDialogUnits(parent);
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setFont(parent.getFont());
    GridLayout layout = new GridLayout();
    layout.numColumns = DEFAULT_COLUMN_NUMBER;
    composite.setLayout(layout);

    createProjectControls(composite, DEFAULT_COLUMN_NUMBER);

    setControl(composite);
    handleFieldChanged(null);
  }

  /**
   * Does not set the message to "OK" when there is no error.
   * <p>
   * Is a copy of com.avaloq.tools.ddk.check.ui.wizard.NewCheckCatalogWizardPage.updateStatus(IStatus).
   * </p>
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected void updateStatus(final IStatus status) {
    IStatus result = status;
    if (status.isOK()) {
      // avoid setting the message to "OK" when there is no problem
      result = new Status(status.getSeverity(), status.getPlugin(), "");
    }
    super.updateStatus(result);
  }

  /**
   * Creates the grammars controls.
   *
   * @param composite
   *          the composite
   * @param columns
   *          the columns
   */
  private void createProjectControls(final Composite composite, final int columns) {
    projectName.setDialogFieldListener(getProjectValueListener());
    projectName.setLabelText(Messages.PROJECT_NAME_LABEL);
    projectName.doFillIntoGrid(composite, columns);

    DialogField.createEmptySpace(composite);

    Text text = projectName.getTextControl(null);
    GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
    text.setLayoutData(gridData);
    projectName.setFocus();
  }

  @Override
  protected void handleFieldChanged(final String fieldName) {
    super.handleFieldChanged(fieldName);
    projectNameStatus = validator.checkProjectName(getProjectName());
    updateStatus(projectNameStatus);
  }

  /**
   * Gets the project name.
   *
   * @return the project name
   */
  public String getProjectName() {
    return projectName.getText();
  }

}
