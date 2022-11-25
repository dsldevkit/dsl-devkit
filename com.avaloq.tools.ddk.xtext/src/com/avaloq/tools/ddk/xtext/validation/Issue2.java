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
package com.avaloq.tools.ddk.xtext.validation;

import org.eclipse.xtext.validation.Issue;


/**
 * An issue that has an additional property, called a fingerprint. It's supposed to be a short hash,
 * typically the hex representation of some MD5 or similar hash.
 */
public interface Issue2 extends Issue {

  /**
   * Gets the fingerprint.
   *
   * @return the fingerprint, if set, or {@code null} if not set.
   */
  String getFingerprint();

  /**
   * Sets the fingerprint.
   * <p>
   * Remember that a fingerprint is supposed to be short. Implementations shall accept at least strings up to 32 characters (enough for a hex representation of
   * a 128bit MD5 hash), but may choose to accept longer strings, too. If the parameter is longer than the maximum length an implementation accepts, the
   * implementation shall raise {@link IllegalArgumentException}.
   * </p>
   *
   * @param fingerprint
   *          to set. May be {@code null}.
   */
  void setFingerprint(String fingerprint);

}
