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
package com.avaloq.tools.ddk.xtext;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.avaloq.tools.ddk.xtext.builder.XtextBuildTriggerTest;
import com.avaloq.tools.ddk.xtext.jupiter.formatter.FormatterTest;
import com.avaloq.tools.ddk.xtext.linking.AbstractFragmentProviderTest;
import com.avaloq.tools.ddk.xtext.linking.ShortFragmentProviderTest;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePatternTest;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNameSegmentTreeLookupTest;
import com.avaloq.tools.ddk.xtext.resource.AbstractSelectorFragmentProviderTest;
import com.avaloq.tools.ddk.xtext.resource.BugAig1084;
import com.avaloq.tools.ddk.xtext.resource.ResourceDescriptionDeltaTest;
import com.avaloq.tools.ddk.xtext.util.RuntimeProjectUtilTest;


/**
 * Empty class serving only as holder for JUnit5 annotations.
 */
// @Format-Off
@Suite
@SelectClasses({
  AbstractFragmentProviderTest.class,
  ShortFragmentProviderTest.class,
  AbstractSelectorFragmentProviderTest.class,
  ResourceDescriptionDeltaTest.class,
  XtextBuildTriggerTest.class,
  FormatterTest.class,
  QualifiedNamePatternTest.class,
  BugAig1084.class,
  RuntimeProjectUtilTest.class,
  QualifiedNameSegmentTreeLookupTest.class})
// @Format-On
public class XtextTestSuite {
}
