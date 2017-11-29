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
package com.avaloq.tools.ddk.xtext.test.ui;

import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.test.ui.junit.runners.SwtBotRecordingTestRunner;
import com.avaloq.tools.ddk.test.ui.swtbot.SwtWorkbenchBot;
import com.avaloq.tools.ddk.test.ui.swtbot.util.SwtBotUtil;


/**
 * AbstractXtextEditorUITest extends AbstractXtextEditorTest by providing SWT bot functionalities.
 */
@RunWith(SwtBotRecordingTestRunner.class)
public abstract class AbstractXtextEditorUiTest extends AbstractXtextEditorTest {

  /**
   * Initialize the {@link org.eclipse.swtbot.swt.finder.SWTBot}.
   */
  public void initializeSWTBot() {
    getTestInformation().putTestObject(SwtWorkbenchBot.class, SwtBotUtil.initializeBot());
  }

  @Override
  protected void beforeAllTests() {
    super.beforeAllTests();
    initializeSWTBot();
    getBot().closeWelcomePage();
  }

  public SwtWorkbenchBot getBot() {
    return (SwtWorkbenchBot) getTestInformation().getTestObject(SwtWorkbenchBot.class);
  }

}
