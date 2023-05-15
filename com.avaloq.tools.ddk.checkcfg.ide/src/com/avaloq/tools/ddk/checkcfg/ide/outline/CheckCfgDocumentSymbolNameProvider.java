/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.checkcfg.ide.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ide.server.symbol.DocumentSymbolMapper.DocumentSymbolNameProvider;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;


/**
 * Symbol name provider for CheckCfg sources.
 */
public class CheckCfgDocumentSymbolNameProvider extends DocumentSymbolNameProvider {
  @Override
  public String getName(final EObject object) {
    if (object instanceof CheckConfiguration) {
      CheckConfiguration configuration = (CheckConfiguration) object;
      return configuration.getName();
    }
    if (object instanceof ConfiguredLanguageValidator) {
      ConfiguredLanguageValidator language = (ConfiguredLanguageValidator) object;
      return language.getLanguage();
    }
    if (object instanceof ConfiguredCatalog) {
      ConfiguredCatalog catalog = (ConfiguredCatalog) object;
      return catalog.getCatalog().getName();
    }
    if (object instanceof ConfiguredCheck) {
      ConfiguredCheck check = (ConfiguredCheck) object;
      return check.getCheck().getLabel();
    }
    return null;
  }
}
