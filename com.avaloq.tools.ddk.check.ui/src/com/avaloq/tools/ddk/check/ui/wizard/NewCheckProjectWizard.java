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

import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;
import org.eclipse.xtext.ui.wizard.XtextNewProjectWizard;

import com.google.inject.Inject;


/**
 * This is a new plugin project wizard. Its role is to create a new plugin
 * project including the necessary dependencies for a check project. The wizard
 * creates one file with the extension "check" in the src folder
 * of the new project.
 */

public class NewCheckProjectWizard extends XtextNewProjectWizard { // extends Wizard implements INewWizard

  private final NewCheckProjectWizardPage newProjectPage;
  private final NewCheckCatalogWizardPage newCatalogPage;

  /**
   * Constructor for the check project wizard. The pages required by this wizard are not created by hand, but by Guice (see also plugin.xml).
   */
  @Inject
  public NewCheckProjectWizard(final IProjectCreator creator, final NewCheckProjectWizardPage newProjectPage, final NewCheckCatalogWizardPage newCatalogPage) {
    super(creator);
    this.newProjectPage = newProjectPage;
    this.newCatalogPage = newCatalogPage;
    setNeedsProgressMonitor(true);
    setWindowTitle(Messages.PROJECT_WIZARD_WINDOW_TITLE);
  }

  /**
   * Adding the page to the wizard.
   */
  @Override
  public void addPages() {
    addPage(newProjectPage);
    addPage(newCatalogPage);
  }

  /**
   * Get the project info instance from the new file page. It contains all information except for the project name.
   *
   * @return the project info instance
   */
  @Override
  protected IProjectInfo getProjectInfo() {
    CheckProjectInfo projectInfo = newCatalogPage.getProjectInfo();
    projectInfo.setProjectName(newProjectPage.getProjectName()); // the project name is only required by the project creator; in case of the new file wizard,
                                                                 // the project name is neither available nor needed
    if (projectInfo.getPackageName().isBlank()) {
      // If no package name was provided, set the project name as package name
      // Should not happen as package name is mandatory
      projectInfo.setPackageName(projectInfo.getProjectName());
    }
    return projectInfo;
  }
}
