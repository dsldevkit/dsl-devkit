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
package com.avaloq.tools.ddk.check.ui.test.quickfix;

import com.avaloq.tools.ddk.check.ui.test.util.CheckXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.AbstractXtextTestUtil;
import com.avaloq.tools.ddk.xtext.test.ui.quickfix.AbstractQuickFixTest;


/**
 * Base class for Check-specific quickfix tests.
 */
public class AbstractCheckQuickfixTest extends AbstractQuickFixTest {

  @Override
  protected AbstractXtextTestUtil getXtextTestUtil() {
    return CheckXtextTestUtil.getInstance();
  }
}
