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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.builder.JDTAwareEclipseResourceFileSystemAccess2;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider;
import com.avaloq.tools.ddk.check.ui.test.AbstractCheckTestCase;


/**
 * Tests when the Check builder touches a generated file whose content did not change.
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "nls"})
@InjectWith(CheckUiInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
public class CheckFileSystemAccessTest extends AbstractCheckTestCase {

  private static final String CATALOG = """
      package %1$s

      import com.avaloq.tools.ddk.check.check.Check

      catalog Touch
      for grammar com.avaloq.tools.ddk.check.Check {
      %2$s
        live error SomeCheck "Some check"
        message "Some check." {
          for Check elem {
            issue on elem
          }
        }
      %3$s
      }
      """;

  private static final String OTHER_CHECK = """
        live error OtherCheck "Other check"
        message "Other check." {
          for Check elem {
            issue on elem
          }
        }
      """;

  private String packageName;
  private IFile catalog;
  private IFile generated;
  private IFile trace;

  /**
   * The Check builder still adds new output folders to the class path of the project.
   */
  @Test
  public void testAddsOutputFoldersToClasspath() {
    EclipseResourceFileSystemAccess2 fileSystemAccess = getInjector().getInstance(EclipseResourceFileSystemAccess2.class);
    assertEquals("CheckFileSystemAccess", fileSystemAccess.getClass().getSimpleName(), "the Check builder uses its own file system access");
    assertInstanceOf(JDTAwareEclipseResourceFileSystemAccess2.class, fileSystemAccess, "new output folders become source folders");
  }

  /**
   * A generated file is not touched when the catalog is generated again without any change.
   */
  @Test
  public void testUnchangedCatalog() throws Exception {
    buildCatalog("touchunchanged");
    final long generatedStamp = generated.getModificationStamp();
    final long traceStamp = trace.getModificationStamp();

    catalog.touch(new NullProgressMonitor());
    IResourcesSetupUtil.waitForBuild();

    assertNotEquals(traceStamp, trace.getModificationStamp(), "the catalog is generated again");
    assertEquals(generatedStamp, generated.getModificationStamp(), "the unchanged generated file is not touched");
  }

  /**
   * A generated file is touched when only the lines of the catalog shift, because its debug information depends on them.
   */
  @Test
  public void testShiftedLines() throws Exception {
    buildCatalog("touchshifted");
    final long generatedStamp = generated.getModificationStamp();
    final String generatedContent = getContents(generated);
    final byte[] traceContent = readBytes(trace);

    setCatalog("\n\n", "");
    IResourcesSetupUtil.waitForBuild();

    assertEquals(generatedContent, getContents(generated), "precondition: the generated file does not change");
    assertFalse(Arrays.equals(traceContent, readBytes(trace)), "precondition: the trace file changes");
    assertNotEquals(generatedStamp, generated.getModificationStamp(), "the generated file is touched");
  }

  /**
   * A generated file is written when its content changes.
   */
  @Test
  public void testChangedContent() throws Exception {
    buildCatalog("touchchanged");
    final long generatedStamp = generated.getModificationStamp();
    final String generatedContent = getContents(generated);

    setCatalog("", OTHER_CHECK);
    IResourcesSetupUtil.waitForBuild();

    assertNotEquals(generatedContent, getContents(generated), "the generated file changes");
    assertNotEquals(generatedStamp, generated.getModificationStamp(), "the generated file is written");
  }

  private void buildCatalog(final String name) throws Exception {
    packageName = name;
    catalog = createFile("src/" + packageName + "/Touch.check", String.format(CATALOG, packageName, "", ""));
    IResourcesSetupUtil.waitForBuild();
    generated = catalog.getProject().getFile("src-gen/" + packageName + "/TouchCheckImpl.java");
    trace = catalog.getProject().getFile("src-gen/" + packageName + "/.TouchCheckImpl.java._trace");
    assertTrue(generated.exists(), "the build generates " + generated);
    assertTrue(trace.exists(), "the build generates " + trace);
  }

  private byte[] readBytes(final IFile file) throws Exception {
    try (InputStream stream = file.getContents(true)) {
      return stream.readAllBytes();
    }
  }

  private void setCatalog(final String before, final String after) throws Exception {
    byte[] content = String.format(CATALOG, packageName, before, after).getBytes(StandardCharsets.UTF_8);
    catalog.setContents(new ByteArrayInputStream(content), true, false, new NullProgressMonitor());
  }

}
