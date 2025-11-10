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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginExtension;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.IPluginObject;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorExtensions;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckMarkerHelpExtensionHelper;
import com.avaloq.tools.ddk.check.ui.builder.util.ExtensionType;
import com.avaloq.tools.ddk.check.ui.test.internal.CheckWizardUiTestInjectorProvider;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * Tests the MarkerHelpExtensionUtil class.
 */
@SuppressWarnings({"restriction", "PMD.SignatureDeclareThrowsException", "nls"})
@InjectWith(CheckWizardUiTestInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckMarkerHelpExtensionTest {

  private static final String SECONDCHECK_CONTEXTID = "null.c_secondcheck";
  private static final String FIRSTCHECK_CONTEXID = "null.c_firstcheck";
  private static final String MARKERTYPE_EXPENSIVE = "org.eclipse.xtext.ui.check.expensive";
  private static final String MARKERTYPE_FAST = "org.eclipse.xtext.ui.check.fast";

  private static final String CATALOG_WITH_FIRST_CHECK_LIVE = "package com.test catalog c for grammar g { live error ID1 \"First Check\"{ for g { issue }}}";
  private static final String CATALOG_WITH_TWO_CHECKS = "package com.test catalog c for grammar g { live error ID1 \"First Check\"{ for g { issue }}"
      + "onDemand warning ID2 \"Second Check\" {for g{issue}}}";
  private static final String CATALOG_WITH_SECOND_CHECK_ONDEMAND = "package com.test catalog c for grammar g {onDemand error ID2 \"Second Check\"{ for g { issue }}}";
  private static final String CATALOG_WITH_FIRST_CHECK_ONDEMAND = "package com.test catalog c for grammar g { onDemand error ID1 \"First Check\"{ for g { issue }}}";
  private static final String CATALOG_CHECK_HAS_TWO_ISSUECODES = "package com.test catalog c for grammar g { onDemand error ID1 \"First Check\"{ for g { issue on g data IssueCodeOne; issue on k;}}}";

  @Inject
  private ParseHelper<CheckCatalog> parser;

  @Inject
  private CheckMarkerHelpExtensionHelper markerUtil;

  @Inject
  private IWorkspace workspace;

  /**
   * Tests if the marker help extension is correctly created.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testCreateExtension() throws Exception {
    IPluginExtension extension = createMarkerHelpExtension(parser.parse(CATALOG_WITH_FIRST_CHECK_LIVE));

    // Test if the extension has been created.
    assertEquals(CheckMarkerHelpExtensionHelper.MARKERHELP_EXTENSION_POINT_ID, extension.getPoint(), "Marker help extension has been created.");

    // Test if the extension contains the marker help element
    IPluginElement element = (IPluginElement) extension.getChildren()[0];
    assertEquals(FIRSTCHECK_CONTEXID, element.getAttribute(CheckMarkerHelpExtensionHelper.CONTEXT_ID_ATTRIBUTE_TAG).getValue(), "The marker help element has the correct context id");
    assertEquals(MARKERTYPE_FAST, element.getAttribute(CheckMarkerHelpExtensionHelper.MARKERTYPE_ATTRIBUTE_TAG).getValue(), "The marker help element has the correct markertype");
  }

  /**
   * Tests, if the marker help extension is updated correctly, i.e. add marker help elements for new checks
   * and delete the elements of removed checks.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testAddElement() throws Exception {
    final CheckCatalog catalogWithOneCheck = parser.parse(CATALOG_WITH_FIRST_CHECK_LIVE);
    IPluginExtension extension = createMarkerHelpExtension(catalogWithOneCheck);

    assertEquals(1, extension.getChildCount(), "Original catalog has one marker help extension");

    CheckCatalog catalogWithTwoChecks = parser.parse(CATALOG_WITH_TWO_CHECKS);
    markerUtil.updateExtension(catalogWithTwoChecks, extension);

    Set<String> contextIds = Sets.newHashSet();
    // Test if the marker help element for the old Check has been removed
    for (IPluginObject element : extension.getChildren()) {
      contextIds.add(((IPluginElement) element).getAttribute(CheckMarkerHelpExtensionHelper.CONTEXT_ID_ATTRIBUTE_TAG).getValue());
    }
    assertEquals(2, extension.getChildCount(), "The extension has two elements");
    assertTrue(contextIds.containsAll(Sets.newHashSet(FIRSTCHECK_CONTEXID, SECONDCHECK_CONTEXTID)), "Both checks are elements of the extension ");
  }

  /**
   * Tests if a marker help element is removed if the corresponding check is deleted.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testRemoveElement() throws Exception {
    final CheckCatalog catalogWithTwoChecks = parser.parse(CATALOG_WITH_TWO_CHECKS);
    IPluginExtension extension = createMarkerHelpExtension(catalogWithTwoChecks);

    assertEquals(2, extension.getChildCount(), "Original catalog has two marker help extensions");

    CheckCatalog catalogWithOneCheck = parser.parse(CATALOG_WITH_SECOND_CHECK_ONDEMAND);
    markerUtil.updateExtension(catalogWithOneCheck, extension);

    assertEquals(1, extension.getChildCount(), "Updated catalog has one marker help extension only");
    assertEquals(SECONDCHECK_CONTEXTID, ((IPluginElement) extension.getChildren()[0]).getAttribute(CheckMarkerHelpExtensionHelper.CONTEXT_ID_ATTRIBUTE_TAG).getValue(), "The element for the removed check has been deleted.");
  }

  /**
   * Tests if the marker type attribute is updated if the trigger kind changes for a check.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testMarkerTypeUpdate() throws Exception {
    IPluginExtension extension = createMarkerHelpExtension(parser.parse(CATALOG_WITH_FIRST_CHECK_LIVE));

    assertEquals(MARKERTYPE_FAST, ((IPluginElement) extension.getChildren()[0]).getAttribute(CheckMarkerHelpExtensionHelper.MARKERTYPE_ATTRIBUTE_TAG).getValue(), "Before update: Markertype is fast.");

    CheckCatalog updateMarkertype = parser.parse(CATALOG_WITH_FIRST_CHECK_ONDEMAND);
    markerUtil.updateExtension(updateMarkertype, extension);

    assertEquals(MARKERTYPE_EXPENSIVE, ((IPluginElement) extension.getChildren()[0]).getAttribute(CheckMarkerHelpExtensionHelper.MARKERTYPE_ATTRIBUTE_TAG).getValue(), "After update: Markertype is expensive.");
  }

  @Inject
  private CheckGeneratorExtensions generatorExtension;

  /**
   * Tests if a marker help element is added if an additional issueCode is added to an existing check.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testCheckHasTwoIssueCodes() throws Exception {
    IPluginExtension extension = createMarkerHelpExtension(parser.parse(CATALOG_WITH_FIRST_CHECK_LIVE));

    CheckCatalog twoIssueCodes = parser.parse(CATALOG_CHECK_HAS_TWO_ISSUECODES);
    markerUtil.updateExtension(twoIssueCodes, extension);

    List<String> issueCodesOfCheck = Lists.newArrayList();
    for (final XIssueExpression i : generatorExtension.issues(twoIssueCodes.getChecks().get(0))) {
      issueCodesOfCheck.add(CheckGeneratorExtensions.issueCodeValue(twoIssueCodes.getChecks().get(0), CheckGeneratorExtensions.issueName(i)));
    }
    List<String> issueCodesInExtension = Lists.newArrayList();
    for (IPluginObject obj : extension.getChildren()) {
      for (IPluginObject element : ((IPluginElement) obj).getChildren()) {
        issueCodesInExtension.add(((IPluginElement) element).getAttribute(CheckMarkerHelpExtensionHelper.ATTRIBUTE_VALUE_TAG).getValue());
      }
    }
    assertTrue(Iterables.elementsEqual(issueCodesInExtension, issueCodesOfCheck), "A marker help element for both issueCodes has been created.");
    assertEquals(2, issueCodesInExtension.size(), "extension has two marker help elements");
  }

  /**
   * Creates a marker help extension.
   *
   * @param catalog
   *          the catalog to create the extension for
   * @return the plugin extension
   * @throws CoreException
   *           the core exception
   */
  private IPluginExtension createMarkerHelpExtension(final CheckCatalog catalog) throws CoreException {
    IFile pluginxml = workspace.getRoot().getFile(new Path("/test/plugin.xml"));
    IPluginModelBase pluginModel = new WorkspacePluginModel(pluginxml, false);
    return markerUtil.addExtensionToPluginBase(pluginModel, catalog, ExtensionType.CONTEXTS, null);
  }

}
