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
package com.avaloq.tools.ddk.checkcfg.validation;

import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.avaloq.tools.ddk.checkcfg.CheckCfgUiInjectorProvider;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.util.CheckCfgModelUtil;
import com.google.inject.Inject;

import junit.framework.TestCase;


/**
 * Performs validations on Check Configuration models.
 */
@InjectWith(CheckCfgUiInjectorProvider.class)
@RunWith(XtextRunner.class)
public class CheckCfgValidationTest extends TestCase {

  @Inject
  private ValidationTestHelper helper;

  @Inject
  private ParseHelper<CheckConfiguration> parser;

  @Inject
  private CheckCfgModelUtil modelUtil;

  /**
   * Tests {@link CheckCfgJavaValidator#checkDisabledCheckIsNotConfigured(com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck)}.
   * <p>
   * A warning is raised if a disabled check has configured parameters.
   * </p>
   *
   * @throws Exception
   *           if a problem occurred parsing the test model
   */
  @Test
  public void testDisabledCheckIsNotConfigured() throws Exception { // NOPMD
    CheckConfiguration model = parser.parse(modelUtil.basicModelWithDisabledTest() + " (val = 0)"); //$NON-NLS-1$
    helper.assertWarning(model, CheckcfgPackage.Literals.CONFIGURED_CHECK, IssueCodes.DISABLED_CHECK_NOT_CONFIGURED);
  }

}
