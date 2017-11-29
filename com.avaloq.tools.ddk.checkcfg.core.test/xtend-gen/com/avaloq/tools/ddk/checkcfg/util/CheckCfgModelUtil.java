/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.checkcfg.util;

import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Provides utility operations for Check Configuration model stubs. Only partial models
 * are returned as strings.
 */
@SuppressWarnings("all")
public class CheckCfgModelUtil {
  public String basicModel(final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("check configuration ");
    _builder.append(name, "");
    _builder.append(" {");
    return _builder.toString();
  }
  
  public String basicModel() {
    return this.basicModel("testing");
  }
  
  public String basicModelWithCatalog() {
    String _basicModel = this.basicModel();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("catalog Sample {");
    String _string = _builder.toString();
    return (_basicModel + _string);
  }
  
  public String basicModelWithTest() {
    String _basicModelWithCatalog = this.basicModelWithCatalog();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Test");
    String _string = _builder.toString();
    return (_basicModelWithCatalog + _string);
  }
  
  public String basicModelWithDisabledTest() {
    String _basicModelWithCatalog = this.basicModelWithCatalog();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("ignore Test");
    String _string = _builder.toString();
    return (_basicModelWithCatalog + _string);
  }
}
