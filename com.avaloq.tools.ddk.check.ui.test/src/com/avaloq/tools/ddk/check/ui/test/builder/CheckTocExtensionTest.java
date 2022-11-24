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
package com.avaloq.tools.ddk.check.ui.test.builder;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckTocExtensionHelper;
import com.avaloq.tools.ddk.check.ui.builder.util.ExtensionType;
import com.avaloq.tools.ddk.check.ui.test.internal.CheckWizardUiTestInjectorProvider;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Tests the CheckTocExtensionUtil class.
 */
@SuppressWarnings({"restriction", "PMD.SignatureDeclareThrowsException"})
@InjectWith(CheckWizardUiTestInjectorProvider.class)
@RunWith(XtextRunner.class)
public class CheckTocExtensionTest extends TestCase {

  @Inject
  private ParseHelper<CheckCatalog> parser;

  @Inject
  private CheckTocExtensionHelper tocUtil;

  @Inject
  private IWorkspace workspace;

  private static final String CATALOG_WITH_FIRST_CHECK_LIVE = "package com.test catalog c for grammar g { live error \"First Check\"{ for g { issue }}}";

  private CheckCatalog catalog;
  private IPluginModelBase pluginModel;

  @Before
  @Override
  public void setUp() throws Exception {
    catalog = parser.parse(CATALOG_WITH_FIRST_CHECK_LIVE);
    IFile pluginxml = workspace.getRoot().getFile(new Path("/test/plugin.xml"));
    pluginModel = new WorkspacePluginModel(pluginxml, false);

  }

  /**
   * Tests if the toc extension is correctly created.
   * 
   * @throws CoreException
   *           the core exception
   */
  @Test
  public void testCreateExtension() throws CoreException {
    IPluginExtension extension = tocUtil.addExtensionToPluginBase(pluginModel, catalog, ExtensionType.CONTEXTS, null);
    assertEquals("Toc extension has been created", CheckTocExtensionHelper.TOC_EXTENSION_POINT_ID, extension.getPoint());
    assertEquals("Toc extension name is correct", tocUtil.getExtensionPointName(catalog), extension.getName());
    assertEquals("Extension has exactly one element", 1, extension.getChildCount());
    assertEquals("toc.xml file location is correctly set", CheckTocExtensionHelper.TOC_FILE_NAME, ((IPluginElement) extension.getChildren()[0]).getAttribute("file").getValue());
  }

  /**
   * Tests if isExtensionUpdateRequired returns true if only an erroneous extension exists for the check catalog.
   * 
   * @throws CoreException
   *           the core exception
   */
  @Test
  public void testIsExtensionUpdateRequiredTrue() throws CoreException {
    IPluginExtension extension = createErroneousTocExtension();

    Iterable<IPluginElement> elements = Iterables.filter(Lists.newArrayList(extension.getChildren()), IPluginElement.class);
    assertTrue("Toc extension update is required", tocUtil.isExtensionUpdateRequired(catalog, extension, elements));
  }

  /**
   * Creates an erroneous toc extension.
   * 
   * @return the plugin extension
   * @throws CoreException
   *           the core exception
   */
  private IPluginExtension createErroneousTocExtension() throws CoreException {
    IPluginExtension newExtension = pluginModel.getFactory().createExtension();
    newExtension.setPoint(CheckTocExtensionHelper.TOC_EXTENSION_POINT_ID);
    newExtension.setName(tocUtil.getExtensionPointName(catalog));
    IPluginElement element = pluginModel.getFactory().createElement(newExtension);
    element.setName("toc");
    element.setAttribute("file", "dummy/file/location");
    newExtension.add(element);
    return newExtension;
  }

  /**
   * Tests if an update of a toc extension is correctly done.
   * 
   * @throws CoreException
   *           the core exception
   */
  @Test
  public void testUpdateTocExtension() throws CoreException {
    IPluginExtension extension = createErroneousTocExtension();
    assertNotSame("File location is not as expected", CheckTocExtensionHelper.TOC_FILE_NAME, ((IPluginElement) extension.getChildren()[0]).getAttribute("file").getValue());
    tocUtil.updateExtension(catalog, extension);
    assertEquals("Toc file location is now set correctly", CheckTocExtensionHelper.TOC_FILE_NAME, ((IPluginElement) extension.getChildren()[0]).getAttribute("file").getValue());
  }

  /**
   * Tests if isExtensionUpdateRequires returns false if a correct extension already exists.
   * 
   * @throws CoreException
   *           the core exception
   */
  @Test
  public void testIsExtensionUpdateRequiredFalse() throws CoreException {
    IPluginExtension extension = tocUtil.addExtensionToPluginBase(pluginModel, catalog, ExtensionType.CONTEXTS, null);
    Iterable<IPluginElement> elements = Iterables.filter(Lists.newArrayList(extension.getChildren()), IPluginElement.class);
    assertFalse("No toc extension update is required", tocUtil.isExtensionUpdateRequired(catalog, extension, elements));
  }
}

