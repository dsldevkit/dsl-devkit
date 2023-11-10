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

package com.avaloq.tools.ddk.test.checkcfg;

import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;


/** A test implementation of {@link ICheckCfgPropertySpecification} that specifies no expected values. */
public final class TestPropertySpecificationWithOutExpectedValues implements ICheckCfgPropertySpecification {

  public static final TestPropertySpecificationWithOutExpectedValues INSTANCE = new TestPropertySpecificationWithOutExpectedValues();

  private TestPropertySpecificationWithOutExpectedValues() {}

  
  @Override
  public String getName() {
    return "testPropertyWithoutExpectations";
  }

  
  @Override
  public PropertyType getType() {
    return PropertyType.STRING;
  }

  
  @Override
  public String getInfo() {
    return "Test Info";
  }

  
  @Override
  public String[] getExpectedValues() {
    return new String[] {};
  }

}
