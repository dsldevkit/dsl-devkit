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
package com.avaloq.tools.ddk.check.scoping;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.nodemodel.INode;

import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess;
import com.avaloq.tools.ddk.xtext.linking.AbstractFastLinkingService;
import com.google.inject.Inject;


/**
 * A linking service for the Check language.
 */
@SuppressWarnings("nls")
public class CheckLinkingService extends AbstractFastLinkingService {

  @Inject
  private IValueConverterService valueConverterService;
  @Inject
  private CheckGrammarAccess grammarAccess;

  @Override
  public List<EObject> getLinkedObjects(final EObject context, final EReference ref, final INode node) {
    if (ref == CheckPackage.Literals.CHECK_CATALOG__GRAMMAR) {
      String grammarName = (String) valueConverterService.toValue("", grammarAccess.getQualifiedNameRule().getName(), node);
      return getUsedGrammar(context.eResource().getResourceSet(), grammarName);
    }
    return super.getLinkedObjects(context, ref, node);
  }

}
