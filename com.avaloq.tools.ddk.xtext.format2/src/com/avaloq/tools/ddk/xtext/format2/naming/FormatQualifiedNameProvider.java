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
package com.avaloq.tools.ddk.xtext.format2.naming;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.scoping.XbaseQualifiedNameProvider;

import com.avaloq.tools.ddk.xtext.format2.format2.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format2.format2.Format2Package;


/**
 * QualifiedNameProvider for Format - currently only provides for FormatConfiguration elements.
 */
public class FormatQualifiedNameProvider extends XbaseQualifiedNameProvider {

  /**
   * Qualified name for FormatConfiguration.
   *
   * @param element
   *          the FormatConfiguration
   * @return the qualified name
   */
  public QualifiedName qualifiedName(final FormatConfiguration element) {
    if (element.eResource() != null && element.eResource().getURI() != null) {
      StringBuilder grammarNameBuilder = new StringBuilder();
      for (INode node : NodeModelUtils.findNodesForFeature(element, Format2Package.Literals.FORMAT_CONFIGURATION__TARGET_GRAMMAR)) {
        grammarNameBuilder.append(NodeModelUtils.getTokenText(node));
      }
      return getConverter().toQualifiedName(grammarNameBuilder.toString());
    }
    return null;
  }
}
