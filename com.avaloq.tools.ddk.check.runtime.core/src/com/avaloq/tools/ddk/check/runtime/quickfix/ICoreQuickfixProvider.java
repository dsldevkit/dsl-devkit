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
package com.avaloq.tools.ddk.check.runtime.quickfix;

/**
 * Defines the generic type of all Check quickfix provider implementations. The Check quickfix
 * extension point contract only allows implementing classes to register.
 * <p>
 * This class is UI-independent.
 * <p>
 * Classes which implement this interface must follow the following naming pattern:
 * Name must start with the catalog name and have <em>QuickfixProvider</em>
 * as suffix. It must be located in the same Java package as the catalog file.
 */
public interface ICoreQuickfixProvider {
}
