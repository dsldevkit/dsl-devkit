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
package com.avaloq.tools.ddk.check.ui.test;

import static com.avaloq.tools.ddk.test.ui.swtbot.util.SwtBotWizardUtil.selectProjectFolder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.ui.util.PluginProjectFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.check.ui.test.internal.CheckWizardUiTestInjectorProvider;
import com.avaloq.tools.ddk.check.ui.test.util.CheckWizardTestUtil;
import com.avaloq.tools.ddk.check.ui.util.CheckResourceUtil;
import com.avaloq.tools.ddk.check.ui.wizard.Messages;
import com.avaloq.tools.ddk.test.core.BugTest;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWizardBot;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * The CheckProjectWizardTestBackup tests the Check Project Wizard.
 */
@InjectWith(CheckWizardUiTestInjectorProvider.class)
@RunWith(XtextRunner.class)
public class CheckCatalogWizardTest {

  /** This is the name of the catalog wizard. It's the name SWTBot uses to look up the wizard. */
  private static final String CHECK_CATALOG = "Check Catalog";
  private static final String SRC_FOLDER = "src";

  /** input strings for wizard testing. */
  private static final String VALID_PACKAGE_NAME = "p.p";

  private static final Logger LOG = Logger.getLogger(CheckCatalogWizardTest.class);

  private SwtWizardBot wizard;
  private static IProject project;

  @Inject
  private Provider<PluginProjectFactory> projectFactoryProvider;

  /**
   * Creates a project.
   *
   * @return a created project
   */
  private IProject createProject() {
    final PluginProjectFactory factory = projectFactoryProvider.get();
    factory.setProjectName("project.name");

    factory.addFolders(Collections.singletonList(SRC_FOLDER));
    factory.addBuilderIds(JavaCore.BUILDER_ID);
    factory.addProjectNatures(JavaCore.NATURE_ID);

    final IProject[] result = new IProject[1];
    WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

      @Override
      protected void execute(final IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        result[0] = factory.createProject(monitor, null);
        IJavaProject javaProject = JavaCore.create(result[0]);
        IFolder sourceFolder = result[0].getFolder(SRC_FOLDER);
        IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(sourceFolder);
        try {
          root.createPackageFragment(VALID_PACKAGE_NAME, false, null);
        } catch (JavaModelException e1) {
          LOG.error("create package" + e1.getMessage());
        }
      }
    };
    try {
      operation.run(new NullProgressMonitor());
    } catch (InvocationTargetException e) {
      return null;
    } catch (InterruptedException e) {
      return null;
    }
    return result[0];
  }

  /**
   * Start again the Check project wizard before every test.
   * <p>
   * Initializes this test class pre-loading grammar access instances, which might involve bundle activation and class loading.
   * </p>
   */
  @Before
  public void setUp() {
    wizard = new SwtWizardBot();
    project = createProject();
    for (Grammar g : new CheckResourceUtil().getGrammars()) { // make sure all grammars accessible for combo field.
      g.getName();
    }
  }

  /**
   * Initializes the wizard bot for creating a new check catalog.
   */
  private void initializeWizardBot() {
    wizard.openNewWizard(CHECK_CATALOG);
    wizard.waitUntilWizardPageAppears(CHECK_CATALOG);
  }

  /**
   * Test if the package field contains the selected package.
   */
  @Test
  public void testPackageSelected() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    initializeWizardBot();
    // because the selected item is a package initially, this package is shown in the field.
    assertEquals("Initial package is entered", VALID_PACKAGE_NAME, wizard.textWithLabel(Messages.PACKAGE_NAME_LABEL).getText());
  }

  /**
   * Test if catalog wizard is enabled if a project folder is selected.
   */
  @Test
  public void testCheckCatalogWizardIsEnabled() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    // open the check catalog wizard after having selected the project source folder.
    // that way, the wizard page should be enabled.
    initializeWizardBot();

    assertEquals("new check catalog page was loaded", Messages.CATALOG_WIZARD_WINDOW_TITLE, wizard.activeShell().getText());
    wizard.waitUntil(Conditions.widgetIsEnabled(wizard.textWithLabel(Messages.CATALOG_FIELD_NAME_LABEL)), SwtWizardBot.SWTBOT_TIMEOUT);
    wizard.waitUntil(Conditions.widgetIsEnabled(wizard.textWithLabel(Messages.PACKAGE_NAME_LABEL)), SwtWizardBot.SWTBOT_TIMEOUT);
    CheckWizardTestUtil.assertButtonsEnabled(false, true, false, wizard);
  }

  /**
   * Test if the package field is empty, if the selected item is no package.
   */
  @Test
  public void testInitiallyNoPackageSelected() {
    selectProjectFolder(wizard, SRC_FOLDER);
    initializeWizardBot();

    // because the selected item is not a package initially, the field has to be empty.
    assertSame("Initially, no package is selected", wizard.textWithLabel(Messages.PACKAGE_NAME_LABEL).getText().length(), 0);
  }

  /**
   * Test if the next and finish button are disabled if the package name is invalid.
   */
  @Test
  public void testPackageNameInvalid() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    // open the check catalog wizard after having selected the project source folder.
    initializeWizardBot();

    CheckWizardTestUtil.packageName(wizard, ".package.name", CheckWizardTestUtil.FINISH_DISABLED);
    CheckWizardTestUtil.packageName(wizard, "Package.name", CheckWizardTestUtil.FINISH_DISABLED);
    CheckWizardTestUtil.packageName(wizard, "packageName", CheckWizardTestUtil.FINISH_DISABLED);
    CheckWizardTestUtil.packageName(wizard, "package. name", CheckWizardTestUtil.FINISH_DISABLED);
    CheckWizardTestUtil.packageName(wizard, "package..name", CheckWizardTestUtil.FINISH_DISABLED);
  }

  /**
   * Test if the finish button is enabled if the package name is valid.
   */
  @Test
  public void testPackageNameValid() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    // open the check catalog wizard after having selected the project source folder.
    initializeWizardBot();

    CheckWizardTestUtil.packageName(wizard, "mypackage", CheckWizardTestUtil.FINISH_ENABLED);
    CheckWizardTestUtil.packageName(wizard, "my.package", CheckWizardTestUtil.FINISH_ENABLED);
  }

  /**
   * Test if the next and finish button are disabled if the catalog name is invalid.
   */
  @Test
  public void testCatalogNameInvalid() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    // open the check catalog wizard after having selected the project source folder.
    initializeWizardBot();

    CheckWizardTestUtil.catalogName(wizard, "catalogname.", CheckWizardTestUtil.FINISH_DISABLED);
    CheckWizardTestUtil.catalogName(wizard, "catalog*name", CheckWizardTestUtil.FINISH_DISABLED);
    CheckWizardTestUtil.catalogName(wizard, ",catalogname", CheckWizardTestUtil.FINISH_DISABLED);
  }

  /**
   * Tests that discouraged catalog names are accepted.
   */
  @Test
  public void testCatalogNameDiscouraged() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    initializeWizardBot();

    // TODO the finish button is enabled, but also test that a warning status is shown
    CheckWizardTestUtil.catalogName(wizard, "lowerCase", CheckWizardTestUtil.FINISH_ENABLED);
    CheckWizardTestUtil.catalogName(wizard, "lowercase", CheckWizardTestUtil.FINISH_ENABLED);
  }

  /**
   * Tests valid catalog names.
   */
  @Test
  public void testCatalogName() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    initializeWizardBot();

    // TODO the finish button is enabled, but also test that NO warning status is shown
    CheckWizardTestUtil.catalogName(wizard, "UpperCase", CheckWizardTestUtil.FINISH_ENABLED);
    CheckWizardTestUtil.catalogName(wizard, "Uppercase", CheckWizardTestUtil.FINISH_ENABLED);
  }

  /**
   * Tests that initially no grammar is selected, so pressing finish is disabled if no grammar is chosen.
   */
  @Test
  @BugTest("AIG-708")
  public void testInitiallyNoGrammarSelected() {
    selectProjectFolder(wizard, VALID_PACKAGE_NAME);
    initializeWizardBot();

    CheckWizardTestUtil.grammarName(wizard, SwtWizardBot.UNDEFINED_COMBO_BOX_INDEX, CheckWizardTestUtil.FINISH_DISABLED);
  }

  /**
   * Close the wizard after every test.
   *
   * @throws CoreException
   *           if project doesn't exist.
   */
  @After
  public void tearDown() throws CoreException {
    wizard.closeWizard();
    project.delete(true, new NullProgressMonitor());
  }

}
