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
package com.avaloq.tools.ddk.xtext.build;

import java.sql.Connection;


/**
 * Simple interface of an index that is accessible through a {@link Connection}.
 */
public interface IConnectionBasedIndex {

  /**
   * Get the {@link Connection} that can be used to access the index.
   *
   * @return the {@link Connection}, possibly {@code null}
   */
  Connection getConnection();
}
