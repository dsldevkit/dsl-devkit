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
import com.avaloq.tools.ddk.check.ui.builder.util.CheckContextsExtensionHelper;
import com.avaloq.tools.ddk.check.ui.builder.util.ExtensionType;
import com.avaloq.tools.ddk.check.ui.test.internal.CheckWizardUiTestInjectorProvider;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import junit.framework.TestCase;


/**
 * Tests CheckContextExtensionUtil.
 */
@SuppressWarnings({"restriction", "nls"})
@InjectWith(CheckWizardUiTestInjectorProvider.class)
@RunWith(XtextRunner.class)
public class CheckContextsExtensionTest extends TestCase {

  private static final String CATALOG_WITH_FIRST_CHECK_LIVE = "package com.test catalog c for grammar g { live error \"First Check\"{ for g { issue }}}";

  private CheckCatalog catalog;
  private IPluginModelBase pluginModel;

  @Inject
  private ParseHelper<CheckCatalog> parser;
  @Inject
  private CheckContextsExtensionHelper contextsUtil;
  @Inject
  private IWorkspace workspace;

  @Override
  @Before
  public void setUp() throws Exception {
    catalog = parser.parse(CATALOG_WITH_FIRST_CHECK_LIVE);
    IFile pluginxml = workspace.getRoot().getFile(new Path("/test/plugin.xml"));
    pluginModel = new WorkspacePluginModel(pluginxml, false);
  }

  /**
   * Tests if the contexts extension is correctly created.
   *
   * @throws CoreException
   *           core exception
   */
  @Test
  public void testCreateExtension() throws CoreException {
    IPluginExtension extension = contextsUtil.addExtensionToPluginBase(pluginModel, catalog, ExtensionType.CONTEXTS, null);
    // Test if the extension has been created.
    assertEquals("Contexts extension has been created.", CheckContextsExtensionHelper.CONTEXTS_EXTENSION_POINT_ID, extension.getPoint());
    // Test if the attributes are set correctly
    String contextsFileName = ((IPluginElement) extension.getChildren()[0]).getAttribute(CheckContextsExtensionHelper.FILE_ATTRIBUTE_TAG).getValue();
    assertEquals("Contexts File name is set correctly", CheckContextsExtensionHelper.CONTEXTS_FILE_NAME, contextsFileName);
  }

  /**
   * Test if isExtensionUpdateRequired is true, if the file path attribute is not as expected.
   *
   * @throws CoreException
   *           the core exception
   */
  @Test
  public void testIsExtensionUpdateRequiredTrue() throws CoreException {
    IPluginExtension extension = createErroneousExtension();
    Iterable<IPluginElement> elements = Iterables.filter(Lists.newArrayList(extension.getChildren()), IPluginElement.class);
    assertTrue("Extension update is required", contextsUtil.isExtensionUpdateRequired(catalog, extension, elements));
  }

  /**
   * Test if isExtensionUpdateRequired returns false if a correct extension already exists.
   *
   * @throws CoreException
   *           the core exception
   */
  @Test
  public void testIsExtensionUpdateRequiredFalse() throws CoreException {
    IPluginExtension extension = contextsUtil.addExtensionToPluginBase(pluginModel, catalog, ExtensionType.CONTEXTS, null);
    Iterable<IPluginElement> elements = Iterables.filter(Lists.newArrayList(extension.getChildren()), IPluginElement.class);
    assertFalse("No extension update is required ", contextsUtil.isExtensionUpdateRequired(catalog, extension, elements));
  }

  /**
   * Creates the extension with wrong file path.
   *
   * @return the i plugin extension
   * @throws CoreException
   *           the core exception
   */
  private IPluginExtension createErroneousExtension() throws CoreException {
    IPluginExtension extension = pluginModel.getFactory().createExtension();
    extension.setPoint(CheckContextsExtensionHelper.CONTEXTS_EXTENSION_POINT_ID);
    extension.setName(contextsUtil.getExtensionPointName(catalog));
    IPluginElement element = extension.getModel().getFactory().createElement(extension);
    element.setName(CheckContextsExtensionHelper.CONTEXT_ELEMENT);
    element.setAttribute(CheckContextsExtensionHelper.FILE_ATTRIBUTE_TAG, "dummy_name");
    extension.add(element);

    return extension;
  }
}
