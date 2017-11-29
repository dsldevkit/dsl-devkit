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
package com.avaloq.tools.ddk.test.core;

import java.util.List;


/**
 * Represents a provider for {@link TestEntityAction}s.
 */
public interface ITestEntityActionProvider {

  /**
   * Returns the {@link TestEntityAction}s of this {@link ITestEntityActionProvider}.
   * 
   * @return the {@link TestEntityAction}s, never {@code null}
   */
  List<TestEntityAction> getTestEntityActions();

}

