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


/** A test implementation of {@link ICheckCfgPropertySpecification} that specifies expected values. */
public final class TestPropertySpecificationWithExpectedValues implements ICheckCfgPropertySpecification {

  public static final TestPropertySpecificationWithExpectedValues INSTANCE = new TestPropertySpecificationWithExpectedValues();

  private TestPropertySpecificationWithExpectedValues() {}

  
  @Override
  public String getName() {
    return "testProperty";
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
    return new String[] {"allowed"};
  }

}
