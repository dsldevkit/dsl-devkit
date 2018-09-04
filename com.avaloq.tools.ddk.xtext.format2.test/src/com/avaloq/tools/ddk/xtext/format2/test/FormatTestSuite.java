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
package com.avaloq.tools.ddk.xtext.format2.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.xtext.format2.builder.FormatBuilderParticipantTest;
import com.avaloq.tools.ddk.xtext.format2.formatting.FormatFormattingTest;
import com.avaloq.tools.ddk.xtext.format2.formatting2.FormatFormatting2Test;
import com.avaloq.tools.ddk.xtext.format2.scoping.FormatScopingTest;
import com.avaloq.tools.ddk.xtext.format2.validation.FormatValidationTest;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({FormatFormattingTest.class, FormatFormatting2Test.class, FormatValidationTest.class, FormatScopingTest.class, FormatBuilderParticipantTest.class})
public class FormatTestSuite {

}

