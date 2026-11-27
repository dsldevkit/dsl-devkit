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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.ui.test.AbstractCheckTestCase;


/**
 * Tests that the Check builder writes {@code toc.xml}, {@code contexts.xml} and a plug-in file with a non-standard name only when their content changes.
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "nls"})
@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckBuilderWriteIfChangedTest extends AbstractCheckTestCase {

  private static final String TOC_FILE = "docs/toc.xml";
  private static final String CONTEXTS_FILE = "docs/contexts.xml";
  private static final String PLUGIN_FILE = "plugin_check.xml";
  private static final String PLUGIN_FILE_PREFERENCES = CheckConstants.GRAMMAR + ".pluginxml";
  private static final String PLUGIN_FILE_PREFERENCE = "PluginXmlFilename";
  private static final String PLUGIN_FILE_CATALOG = "PluginFile";
  private static final String LABEL = "Some check";
  private static final String OTHER_LABEL = "Renamed check";
  private static final String MESSAGE = "Some message.";
  private static final String OTHER_MESSAGE = "Another message.";

  private IFile catalog;

  /**
   * Rebuilding a catalog whose change does not affect the help files leaves them alone.
   */
  @Test
  public void testUnchangedHelpFilesAreNotWritten() throws Exception {
    IProject project = buildCatalog("UnchangedHelp", LABEL, MESSAGE);
    IFile toc = project.getFile(TOC_FILE);
    IFile contexts = project.getFile(CONTEXTS_FILE);
    assertTrue(getContents(toc).contains(LABEL), "first build writes the topic to " + TOC_FILE);
    assertTrue(getContents(contexts).contains(LABEL), "first build writes the context to " + CONTEXTS_FILE);
    long tocStamp = toc.getModificationStamp();
    long contextsStamp = contexts.getModificationStamp();

    rebuildCatalogWithNewMessage("UnchangedHelp", LABEL);

    assertEquals(tocStamp, toc.getModificationStamp(), TOC_FILE + " is not written");
    assertEquals(contextsStamp, contexts.getModificationStamp(), CONTEXTS_FILE + " is not written");
  }

  /**
   * Renaming a check updates the help files.
   */
  @Test
  public void testChangedHelpFilesAreWritten() throws Exception {
    IProject project = buildCatalog("ChangedHelp", LABEL, MESSAGE);
    IFile toc = project.getFile(TOC_FILE);
    IFile contexts = project.getFile(CONTEXTS_FILE);
    long tocStamp = toc.getModificationStamp();
    long contextsStamp = contexts.getModificationStamp();

    rebuildCatalog("ChangedHelp", OTHER_LABEL, MESSAGE);

    assertNotEquals(tocStamp, toc.getModificationStamp(), TOC_FILE + " is written");
    assertNotEquals(contextsStamp, contexts.getModificationStamp(), CONTEXTS_FILE + " is written");
    assertTrue(getContents(toc).contains(OTHER_LABEL), TOC_FILE + " has the renamed topic");
    assertTrue(getContents(contexts).contains("changedhelp_renamedcheck"), CONTEXTS_FILE + " has the renamed context");
    assertFalse(getContents(contexts).contains("changedhelp_somecheck"), CONTEXTS_FILE + " lost the old context");
  }

  /**
   * Help files that are missing are created again.
   */
  @Test
  public void testMissingHelpFilesAreCreated() throws Exception {
    IProject project = buildCatalog("MissingHelp", LABEL, MESSAGE);
    IFile toc = project.getFile(TOC_FILE);
    IFile contexts = project.getFile(CONTEXTS_FILE);
    toc.delete(true, null);
    contexts.delete(true, null);

    rebuildCatalogWithNewMessage("MissingHelp", LABEL);

    assertTrue(toc.exists(), TOC_FILE + " is created");
    assertTrue(contexts.exists(), CONTEXTS_FILE + " is created");
    assertTrue(getContents(toc).contains(LABEL), TOC_FILE + " has the topic");
    assertTrue(getContents(contexts).contains("missinghelp_somecheck"), CONTEXTS_FILE + " has the context");
  }

  /**
   * A plug-in file with a non-standard name is created, left alone while its extensions do not change, and written when a catalog is added.
   */
  @Test
  public void testNonStandardPluginFile() throws Exception {
    IProject project = getOrCreatePluginProject();
    IEclipsePreferences preferences = new ProjectScope(project).getNode(PLUGIN_FILE_PREFERENCES);
    preferences.put(PLUGIN_FILE_PREFERENCE, PLUGIN_FILE);
    preferences.flush();
    try {
      buildCatalog(PLUGIN_FILE_CATALOG, LABEL, MESSAGE);
      IFile pluginFile = project.getFile(PLUGIN_FILE);
      assertTrue(pluginFile.exists(), PLUGIN_FILE + " is created");
      assertTrue(getContents(pluginFile).contains("writeifchanged.pluginfile.validator"), PLUGIN_FILE + " has the extensions of the catalog");

      rebuildCatalogWithNewMessage(PLUGIN_FILE_CATALOG, LABEL);
      long stamp = pluginFile.getModificationStamp();
      String contents = getContents(pluginFile);

      rebuildCatalog(PLUGIN_FILE_CATALOG, LABEL, MESSAGE);
      assertEquals(stamp, pluginFile.getModificationStamp(), PLUGIN_FILE + " is not written");
      assertEquals(contents, getContents(pluginFile), PLUGIN_FILE + " keeps its content");

      buildCatalog("PluginFileTwo", LABEL, MESSAGE);
      assertNotEquals(stamp, pluginFile.getModificationStamp(), PLUGIN_FILE + " is written");
      assertTrue(getContents(pluginFile).contains("writeifchanged.pluginfiletwo.validator"), PLUGIN_FILE + " has the extensions of the second catalog");
    } finally {
      preferences.remove(PLUGIN_FILE_PREFERENCE);
      preferences.flush();
    }
  }

  private IProject buildCatalog(final String name, final String label, final String message) throws Exception {
    catalog = createFile("src/writeifchanged/" + name + ".check", catalogSource(name, label, message));
    IResourcesSetupUtil.waitForBuild();
    return catalog.getProject();
  }

  private void rebuildCatalog(final String name, final String label, final String message) throws Exception {
    catalog.setContents(new ByteArrayInputStream(catalogSource(name, label, message).getBytes(StandardCharsets.UTF_8)), IResource.FORCE, null);
    IResourcesSetupUtil.waitForBuild();
  }

  /**
   * Rebuilds the catalog with another message, which changes its generated documentation page but none of the files under test.
   */
  private void rebuildCatalogWithNewMessage(final String name, final String label) throws Exception {
    IFile page = catalog.getProject().getFile("docs/content/" + name + ".html");
    long pageStamp = page.getModificationStamp();
    rebuildCatalog(name, label, OTHER_MESSAGE);
    assertNotEquals(pageStamp, page.getModificationStamp(), "precondition: the builder participant processed the catalog");
  }

  private static String catalogSource(final String name, final String label, final String message) {
    return """
        package writeifchanged

        import com.avaloq.tools.ddk.check.check.Check

        catalog %s
        for grammar com.avaloq.tools.ddk.check.Check {

          live error SomeCheck "%s"
          message "%s" {
            for Check elem {
              issue on elem
            }
          }
        }
        """.formatted(name, label, message);
  }
}
