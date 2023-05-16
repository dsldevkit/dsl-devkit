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
import org.eclipse.lsp4j.SymbolKind;
import org.eclipse.xtext.ide.server.symbol.DocumentSymbolMapper.DocumentSymbolKindProvider;

import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredLanguageValidator;


/**
 * SymbolKind provider for CheckCfg sources.
 */
public class CheckCfgDocumentSymbolKindProvider extends DocumentSymbolKindProvider {
  @Override
  public SymbolKind getSymbolKind(final EObject object) {
    if (object instanceof CheckConfiguration) {
      return SymbolKind.Module;
    }
    if (object instanceof ConfiguredLanguageValidator) {
      return SymbolKind.Package;
    }
    if (object instanceof ConfiguredCatalog) {
      return SymbolKind.Class;
    }
    if (object instanceof ConfiguredCheck) {
      return SymbolKind.Constant;
    }
    return null;
  }
}
