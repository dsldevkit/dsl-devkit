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

package com.avaloq.tools.ddk.checkcfg.util

import com.avaloq.tools.ddk.check.check.FormalParameter
import com.avaloq.tools.ddk.check.check.impl.CheckFactoryImpl
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter
import com.avaloq.tools.ddk.xtext.util.ParseTreeUtil
import com.google.inject.Inject
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.common.types.JvmTypeReference
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.xbase.XBooleanLiteral
import org.eclipse.xtext.xbase.XNumberLiteral
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification
import org.eclipse.xtext.xbase.XListLiteral

class PropertiesInferenceHelper {
  @Inject JvmTypeReferenceBuilder.Factory typeRefBuilderFactory;

  static val BOOLEAN = "boolean"
  static val STRING = "java.lang.String"
  static val NUMBER = "int"
  static val STRING_LIST = "List<java.lang.String>"
  static val NUMBER_LIST = "List<java.lang.Integer>"
  static val BOOLEAN_LIST = "List<java.lang.Boolean>"

  def getProperties(CheckConfiguration checkConfiguration, EList<FormalParameter> properties) {
    val referenceBuilder = typeRefBuilderFactory.create(checkConfiguration.eResource().resourceSet)

    // get all ConfigurableSections
    val configurableSections = EcoreUtil2.getAllContentsOfType(checkConfiguration, typeof(ConfigurableSection))
    // the CheckConfiguration itself is a configurable section
    configurableSections.add(0, checkConfiguration)

    // infer properties for all sections
    for (section : configurableSections) {
      val parameters = section.parameterConfigurations;
      for (parameter : parameters) {
        val formalParameter = parameter.inferFormalParameter(referenceBuilder)
        if (formalParameter != null) {
          properties.add(formalParameter)
        }
      }
    }

    // add contributed properties
    val contributions = CheckCfgUtil.allPropertyContributions
    for (contribution : contributions) {
      val formalParameter = contribution.inferFormalParameter(referenceBuilder)
      if (formalParameter != null) {
        properties.add(formalParameter)
      }
    }
    properties
  }

  def inferType(ICheckCfgPropertySpecification contribution, JvmTypeReferenceBuilder referenceBuilder) {
    switch contribution.type {
      case ICheckCfgPropertySpecification.PropertyType.BOOLEAN: referenceBuilder.typeRef(BOOLEAN)
      case ICheckCfgPropertySpecification.PropertyType.NUMBER: referenceBuilder.typeRef(NUMBER)
      case ICheckCfgPropertySpecification.PropertyType.STRING: referenceBuilder.typeRef(STRING)
      case ICheckCfgPropertySpecification.PropertyType.NUMBERS: referenceBuilder.typeRef(NUMBER_LIST)
      case ICheckCfgPropertySpecification.PropertyType.STRINGS: referenceBuilder.typeRef(STRING_LIST)
      case ICheckCfgPropertySpecification.PropertyType.BOOLEANS: referenceBuilder.typeRef(BOOLEAN_LIST)
      default: null
    }
  }

  def inferListType(XListLiteral newValue, JvmTypeReferenceBuilder referenceBuilder) {
    if (newValue.elements.size < 1) {
      return null
    }
    switch newValue.elements.get(0) {
      XBooleanLiteral: referenceBuilder.typeRef(BOOLEAN_LIST)
      XNumberLiteral: referenceBuilder.typeRef(NUMBER_LIST)
      XStringLiteral: referenceBuilder.typeRef(STRING_LIST)
      default: null
    }
  }

  def inferType(ConfiguredParameter parameter, JvmTypeReferenceBuilder referenceBuilder) {
    val newValue = parameter.newValue
    switch newValue {
      XBooleanLiteral: referenceBuilder.typeRef(BOOLEAN)
      XNumberLiteral: referenceBuilder.typeRef(NUMBER)
      XStringLiteral: referenceBuilder.typeRef(STRING)
      XListLiteral: inferListType(newValue, referenceBuilder)
      default: null
    }
  }

  def inferFormalParameter(ConfiguredParameter parameter, JvmTypeReferenceBuilder referenceBuilder) {
    if (parameter == null) {
      return null
    }
    inferFormalParameter(ParseTreeUtil.getParsedString(parameter, CheckcfgPackage.Literals.CONFIGURED_PARAMETER__PARAMETER),
      inferType(parameter, referenceBuilder))
  }

  def inferFormalParameter(ICheckCfgPropertySpecification contribution, JvmTypeReferenceBuilder referenceBuilder) {
    if (contribution == null) {
      return null
    }
    inferFormalParameter(contribution.name, inferType(contribution, referenceBuilder))
  }

  def inferFormalParameter(String name, JvmTypeReference type) {
    if (type == null) {
      return null
    }
    val formalParameter = CheckFactoryImpl.eINSTANCE.createFormalParameter();
    formalParameter.name = name
    formalParameter.type = type
    formalParameter
  }

  def static getHelper() {
    IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(URI.createURI("DUMMY.checkcfg")).get(
      typeof(PropertiesInferenceHelper));
  }

}