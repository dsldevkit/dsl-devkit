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
package com.avaloq.tools.ddk.checkcfg.scoping;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCatalog;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredCheck;


/**
 * Configures the Check Configuration scope provider.
 */
public class CheckCfgScopeProvider extends XImportSectionNamespaceScopeProvider {

  private final IQualifiedNameProvider checkQualifiedNameProvider = new IQualifiedNameProvider.AbstractImpl() {
    @Override
    public QualifiedName getFullyQualifiedName(final EObject obj) {
      if (obj instanceof Check) {
        return QualifiedName.create(((Check) obj).getName());
      } else if (obj instanceof FormalParameter) {
        return QualifiedName.create(((FormalParameter) obj).getName());
      }
      return null;
    }
  };

  @Override
  public IScope getScope(final EObject context, final EReference reference) {
    if (reference == CheckcfgPackage.Literals.CONFIGURED_CHECK__CHECK) {
      // Note that context object can be either a configured check (if 'optional' keyword has been provided
      // so that a new instance is created and the configured catalog does not contain any configured checks
      // yet) or a configured catalog (in all other cases)
      final ConfiguredCatalog configuredCatalog = EcoreUtil2.getContainerOfType(context, ConfiguredCatalog.class);
      if (configuredCatalog == null || configuredCatalog.getCatalog() == null) {
        return IScope.NULLSCOPE;
      }
      CheckCatalog catalog = configuredCatalog.getCatalog();
      return Scopes.scopeFor(catalog.getAllChecks(), checkQualifiedNameProvider, IScope.NULLSCOPE);
    } else if (reference == CheckcfgPackage.Literals.CONFIGURED_PARAMETER__PARAMETER) {
      // a new list of FormalParameters to scope to
      EList<FormalParameter> parameters = ECollections.newBasicEList();
      final ConfiguredCheck configuredCheck = EcoreUtil2.getContainerOfType(context, ConfiguredCheck.class);
      if (configuredCheck != null) {
        // add FormalParameter definitions from linked check
        parameters.addAll(configuredCheck.getCheck().getFormalParameters());
      }
      // add inferred FormalParameters (properties)
      final CheckConfiguration checkConfiguration = EcoreUtil2.getContainerOfType(context, CheckConfiguration.class);
      parameters.addAll(checkConfiguration.getProperties());
      return Scopes.scopeFor(parameters, checkQualifiedNameProvider, IScope.NULLSCOPE);
    }
    return super.getScope(context, reference);
  }
}
