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
package com.avaloq.tools.ddk.test.core.data;

/**
 * The Class User.
 */
public class User {

  private final String username;
  private final String password;
  private final String fullUsername;

  public User(final String username, final String password, final String fullUsername) {
    this.username = username;
    this.password = password;
    this.fullUsername = fullUsername;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  /**
   * Returns combination of name,surname and username for the given user.
   *
   * @return full user name
   */
  public String getFullUsername() {
    if (fullUsername != null) {
      return fullUsername + " (" + username + ")";
    }
    return fullUsername;
  }

}
