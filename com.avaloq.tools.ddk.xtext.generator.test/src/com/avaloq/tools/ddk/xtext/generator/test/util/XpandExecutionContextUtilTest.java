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
package com.avaloq.tools.ddk.xtext.generator.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.Output;
import org.eclipse.xtext.generator.Naming;
import org.junit.Test;

import com.avaloq.tools.ddk.xtext.test.AbstractUtilTest;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.generator.util.XpandExecutionContextUtil;
import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;


/**
 * A test class for {@link XpandExecutionContextUtil}
 */
public class XpandExecutionContextUtilTest extends AbstractUtilTest {

  /**
   * Prepare mocks for all tests.
   */
  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    prepareMocksBase();
  }

  /**
   * Tests creation of {@link XpandExecutionContext}.
   */
  @Test
  public void createExecutionContextCorrectTest() {
    XpandExecutionContext xec = XpandExecutionContextUtil.createExecutionContext(resource, delta, mapperCorrect);
    assertNotNull("Successfully created XpandExecutionContext cannot be null", xec);
    Output out = xec.getOutput();
    assertNotNull("The output of the successfully created XpandExecutionContext cannot be null", out);
    Outlet outlet = out.getOutlet(org.eclipse.xtext.generator.Generator.PLUGIN_RT);
    assertNotNull("Outlets of the output of the successfully created XpandExecutionContext cannot be null", outlet);
    assertFalse("An outlet should not allow for append", outlet.isAppend());
    assertFalse("An outlet for PLUGIN should not allow for overwriting", outlet.isOverwrite());
    assertEquals("Check if project path has been generated correctly", outlet.getPath(), RuntimeProjectUtil.getPathProject(resource, mapperCorrect));
    assertTrue("Check if created execution context has correctly configured global variables", xec.getGlobalVariables().containsKey(Naming.GLOBAL_VAR_NAME));
  }

  @Override
  protected List<String> getRequiredSourceFileNames() {
    return ImmutableList.of();
  }

  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return new AbstractXtextTestUtil() {

      @Override
      @Deprecated
      protected Injector getInjector() {
        return Guice.createInjector(new org.eclipse.xtext.XtextRuntimeModule());
      }

    };
  }
}

