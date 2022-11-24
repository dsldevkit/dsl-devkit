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
package com.avaloq.tools.ddk.check.ui.builder.util;

/**
 * The enumeration used for identifying plugin extension IDs.
 */
public enum ExtensionType {
  ALL, // Matches all extension types
  VALIDATOR, // Check validator extension
  QUICKFIX, // Check quickfix extension
  PREFERENCE_INITIALIZER, // Extension for preference initializers
  TOC, // Extension for check documentation
  CONTEXTS, // Extension for context sensitive help
  MARKERHELP // Extension for marker help
}

