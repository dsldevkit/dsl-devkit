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

package com.avaloq.tools.ddk.checkcfg.checkcfg.impl;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.xtext.EcoreUtil2;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection;
import com.avaloq.tools.ddk.checkcfg.util.PropertiesInferenceHelper;


/**
 * The Class CheckConfigurationImplCustom.
 */
public class CheckConfigurationImplCustom extends CheckConfigurationImpl {

  /** {@inheritDoc} */
  @Override
  public EList<FormalParameter> getProperties() {
    EList<FormalParameter> properties = new EObjectContainmentEList<FormalParameter>(FormalParameter.class, this, CheckcfgPackage.CHECK_CONFIGURATION__PROPERTIES);
    PropertiesInferenceHelper helper = PropertiesInferenceHelper.getHelper();
    return helper.getProperties(this, properties);
  }

  @Override
  public EList<ConfigurableSection> getConfigurableSections() {
    return ECollections.asEList(EcoreUtil2.getAllContentsOfType(this, ConfigurableSection.class));
  }
}
