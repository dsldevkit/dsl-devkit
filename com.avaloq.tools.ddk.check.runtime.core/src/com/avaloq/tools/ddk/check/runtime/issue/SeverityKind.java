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
package com.avaloq.tools.ddk.check.runtime.issue;

// This is a copy of the check SeverityKind, we duplicate this class to avoid a dependency on the DSL plug-in.
/**
 * The severity kind supported.
 */
public enum SeverityKind {
  ERROR,
  WARNING,
  INFO,
  IGNORE
}

