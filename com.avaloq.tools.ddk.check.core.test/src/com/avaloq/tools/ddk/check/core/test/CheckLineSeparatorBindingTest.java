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
package com.avaloq.tools.ddk.check.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.eclipse.xtext.formatting.ILineSeparatorInformation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.check.CheckInjectorProvider;
import com.avaloq.tools.ddk.xtext.formatting.LfLineSeparatorInformation;
import com.google.inject.Inject;


/**
 * Guarantees the Check runtime injector resolves {@link ILineSeparatorInformation} to the LF
 * binding, so headless generation is line-ending-deterministic. The other DDK language
 * runtime modules declare the identical binding method; this test pins the Guice
 * module-convention wiring they all rely on.
 */
@InjectWith(CheckInjectorProvider.class)
@ExtendWith(InjectionExtension.class)
@SuppressWarnings("nls")
public class CheckLineSeparatorBindingTest {

  @Inject
  private ILineSeparatorInformation lineSeparatorInformation;

  @Test
  public void runtimeInjectorBindsLfLineSeparator() {
    assertInstanceOf(LfLineSeparatorInformation.class, lineSeparatorInformation, "Check runtime injector must bind the LF separator information");
    assertEquals("\n", lineSeparatorInformation.getLineSeparator(), "bound separator must be LF");
  }

}
