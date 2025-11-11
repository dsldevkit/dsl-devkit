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
package com.avaloq.tools.ddk.test.ui.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.test.ui.test.swtbot.DeChKeyboardLayoutTest;
import com.avaloq.tools.ddk.test.ui.test.swtbot.SwtBotRadioTest;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
@Suite
@SelectClasses({
// @Format-Off
  DeChKeyboardLayoutTest.class,
  SwtBotRadioTest.class
// @Format-On
})
public class AllTests {
}
