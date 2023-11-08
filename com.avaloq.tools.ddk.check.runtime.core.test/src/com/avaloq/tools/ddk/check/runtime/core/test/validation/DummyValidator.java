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
package com.avaloq.tools.ddk.check.runtime.core.test.validation;

import com.avaloq.tools.ddk.check.runtime.issue.DefaultCheckImpl;
import com.google.common.collect.ImmutableMap;


/**
 * Empty class used to create an instance only.
 */
public class DummyValidator extends DefaultCheckImpl {

  @Override
  public String getQualifiedCatalogName() {
    return null;
  }

  @Override
  public final ImmutableMap<String, String> getIssueCodeToLabelMap() {
    return null;
  }
}
