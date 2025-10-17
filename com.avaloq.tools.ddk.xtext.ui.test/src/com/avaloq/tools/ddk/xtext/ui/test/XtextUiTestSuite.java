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

package com.avaloq.tools.ddk.xtext.ui.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.test.core.junit.runners.DiscerningSuite;
import com.avaloq.tools.ddk.xtext.ui.quickfix.WorkbenchResolutionAdaptorRunTest;
import com.avaloq.tools.ddk.xtext.ui.quickfix.WorkbenchResolutionAdaptorTest;
import com.avaloq.tools.ddk.xtext.ui.templates.ResourceNameTemplateVariableResolverTest;
import com.avaloq.tools.ddk.xtext.ui.templates.SimpleEnumTemplateVariableResolverTest;
import com.avaloq.tools.ddk.xtext.ui.templates.TemplateProposalProviderHelperTest;


/**
 * Junit5 version of test suites. does not implement the logic in our {@link DiscerningSuite}.
 */
@Suite
@SelectClasses({
// @Format-Off
  WorkbenchResolutionAdaptorTest.class,
  WorkbenchResolutionAdaptorRunTest.class,
  SimpleEnumTemplateVariableResolverTest.class,
  ResourceNameTemplateVariableResolverTest.class,
  TemplateProposalProviderHelperTest.class
  // @Format-On
})
public class XtextUiTestSuite {
}
