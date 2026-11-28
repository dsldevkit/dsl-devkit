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
package com.avaloq.tools.ddk.check.ui.test.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckPreferencesExtensionHelper;
import com.avaloq.tools.ddk.check.ui.builder.util.ExtensionType;
import com.avaloq.tools.ddk.check.ui.test.internal.CheckWizardUiTestInjectorProvider;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Tests that the preferences extension declares the check configuration scope only when its class is available.
 */
@SuppressWarnings({"restriction", "nls"})
@InjectWith(CheckWizardUiTestInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckPreferencesExtensionTest {

  private static final String CATALOG = "package com.test catalog c for grammar g { live error \"First Check\"{ for g { issue }}}";
  private static final String INITIALIZER = "initializer";

  private CheckCatalog catalog;
  private IPluginModelBase pluginModel;

  @Inject
  private ParseHelper<CheckCatalog> parser;
  @Inject
  private CheckPreferencesExtensionHelper preferencesUtil;
  @Inject
  private IWorkspace workspace;

  @BeforeEach
  public void setUp() {
    try {
      catalog = parser.parse(CATALOG);
    } catch (Exception e) {
      throw new AssertionError(e);
    }
    IFile pluginxml = workspace.getRoot().getFile(new Path("/test/plugin.xml"));
    pluginModel = new WorkspacePluginModel(pluginxml, false);
  }

  /**
   * Without the check configuration scope class, the extension has only the initializer, and needs no update.
   *
   * @throws CoreException
   *           core exception
   */
  @Test
  public void testCreateExtensionWithoutScope() throws CoreException {
    IPluginExtension extension = preferencesUtil.addExtensionToPluginBase(pluginModel, catalog, ExtensionType.PREFERENCE_INITIALIZER, null);
    assertEquals(1, extension.getChildCount(), "only the initializer");
    assertEquals(INITIALIZER, extension.getChildren()[0].getName(), "initializer element");
    assertFalse(preferencesUtil.isExtensionUpdateRequired(catalog, extension, elements(extension)), "no update required");
  }

  /**
   * An extension with a scope whose class is not available is updated to drop the scope.
   *
   * @throws CoreException
   *           core exception
   */
  @Test
  public void testUpdateRemovesUnavailableScope() throws CoreException {
    IPluginExtension extension = preferencesUtil.addExtensionToPluginBase(pluginModel, catalog, ExtensionType.PREFERENCE_INITIALIZER, null);
    IPluginElement scope = extension.getModel().getFactory().createElement(extension);
    scope.setName("scope");
    scope.setAttribute("name", "checkcfgproject");
    extension.add(scope);
    assertTrue(preferencesUtil.isExtensionUpdateRequired(catalog, extension, elements(extension)), "update required");
    preferencesUtil.updateExtension(catalog, extension);
    assertEquals(1, extension.getChildCount(), "scope removed");
    assertEquals(INITIALIZER, extension.getChildren()[0].getName(), "initializer kept");
  }

  private static Iterable<IPluginElement> elements(final IPluginExtension extension) {
    return Iterables.filter(Lists.newArrayList(extension.getChildren()), IPluginElement.class);
  }
}
