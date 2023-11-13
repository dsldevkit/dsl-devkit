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
package com.avaloq.tools.ddk.xtext.test.format;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.avaloq.tools.ddk.xtext.format.builder.FormatBuilderParticipantTest;
import com.avaloq.tools.ddk.xtext.format.formatting.FormatFormattingTest;
import com.avaloq.tools.ddk.xtext.format.scoping.FormatScopingTest;
import com.avaloq.tools.ddk.xtext.format.validation.FormatValidationTest;


/**
 * Empty class serving only as holder for JUnit4 annotations.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({FormatFormattingTest.class, FormatValidationTest.class, FormatScopingTest.class, FormatBuilderParticipantTest.class})
public class FormatTestSuite {

}
