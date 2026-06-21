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

package com.avaloq.tools.ddk.checkcfg.util;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.xbase.XBooleanLiteral;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder;

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.impl.CheckFactoryImpl;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.xtext.util.ParseTreeUtil;
import com.google.inject.Inject;

@SuppressWarnings("nls")
public class PropertiesInferenceHelper {

  @Inject
  private JvmTypeReferenceBuilder.Factory typeRefBuilderFactory;

  private static final String BOOLEAN = "boolean";
  private static final String STRING = "java.lang.String";
  private static final String NUMBER = "int";
  private static final String STRING_LIST = "List<java.lang.String>";
  private static final String NUMBER_LIST = "List<java.lang.Integer>";
  private static final String BOOLEAN_LIST = "List<java.lang.Boolean>";

  /**
   * Collects all inferred properties for the given check configuration.
   *
   * @param checkConfiguration the check configuration
   * @param properties the list to which inferred properties are added
   * @return the populated list of properties
   */
  public EList<FormalParameter> getProperties(final CheckConfiguration checkConfiguration, final EList<FormalParameter> properties) {
    final JvmTypeReferenceBuilder referenceBuilder = typeRefBuilderFactory.create(checkConfiguration.eResource().getResourceSet());

    // get all ConfigurableSections
    final List<ConfigurableSection> configurableSections = EcoreUtil2.getAllContentsOfType(checkConfiguration, ConfigurableSection.class);
    // the CheckConfiguration itself is a configurable section
    configurableSections.add(0, checkConfiguration);

    // infer properties for all sections
    for (final ConfigurableSection section : configurableSections) {
      final EList<ConfiguredParameter> parameters = section.getParameterConfigurations();
      for (final ConfiguredParameter parameter : parameters) {
        final FormalParameter formalParameter = inferFormalParameter(parameter, referenceBuilder);
        if (formalParameter != null) {
          properties.add(formalParameter);
        }
      }
    }

    // add contributed properties
    final Collection<ICheckCfgPropertySpecification> contributions = CheckCfgUtil.getAllPropertyContributions();
    for (final ICheckCfgPropertySpecification contribution : contributions) {
      final FormalParameter formalParameter = inferFormalParameter(contribution, referenceBuilder);
      if (formalParameter != null) {
        properties.add(formalParameter);
      }
    }
    return properties;
  }

  /**
   * Infers the type of the given property specification.
   *
   * @param contribution the property specification
   * @param referenceBuilder the type reference builder
   * @return the inferred type, or {@code null} if it cannot be inferred
   */
  public JvmTypeReference inferType(final ICheckCfgPropertySpecification contribution, final JvmTypeReferenceBuilder referenceBuilder) {
    final ICheckCfgPropertySpecification.PropertyType type = contribution.getType();
    if (type == null) {
      return null;
    }
    return switch (type) {
      case BOOLEAN -> referenceBuilder.typeRef(BOOLEAN);
      case NUMBER -> referenceBuilder.typeRef(NUMBER);
      case STRING -> referenceBuilder.typeRef(STRING);
      case NUMBERS -> referenceBuilder.typeRef(NUMBER_LIST);
      case STRINGS -> referenceBuilder.typeRef(STRING_LIST);
      case BOOLEANS -> referenceBuilder.typeRef(BOOLEAN_LIST);
    };
  }

  /**
   * Infers the list type from the given list literal.
   *
   * @param newValue the list literal
   * @param referenceBuilder the type reference builder
   * @return the inferred list type, or {@code null} if it cannot be inferred
   */
  public JvmTypeReference inferListType(final XListLiteral newValue, final JvmTypeReferenceBuilder referenceBuilder) {
    if (newValue.getElements().isEmpty()) {
      return null;
    }
    final XExpression firstElement = newValue.getElements().get(0);
    if (firstElement instanceof XBooleanLiteral) {
      return referenceBuilder.typeRef(BOOLEAN_LIST);
    } else if (firstElement instanceof XNumberLiteral) {
      return referenceBuilder.typeRef(NUMBER_LIST);
    } else if (firstElement instanceof XStringLiteral) {
      return referenceBuilder.typeRef(STRING_LIST);
    } else {
      return null;
    }
  }

  /**
   * Infers the type of the given configured parameter.
   *
   * @param parameter the configured parameter
   * @param referenceBuilder the type reference builder
   * @return the inferred type, or {@code null} if it cannot be inferred
   */
  public JvmTypeReference inferType(final ConfiguredParameter parameter, final JvmTypeReferenceBuilder referenceBuilder) {
    final XExpression newValue = parameter.getNewValue();
    if (newValue instanceof XBooleanLiteral) {
      return referenceBuilder.typeRef(BOOLEAN);
    } else if (newValue instanceof XNumberLiteral) {
      return referenceBuilder.typeRef(NUMBER);
    } else if (newValue instanceof XStringLiteral) {
      return referenceBuilder.typeRef(STRING);
    } else if (newValue instanceof XListLiteral xListLiteral) {
      return inferListType(xListLiteral, referenceBuilder);
    } else {
      return null;
    }
  }

  /**
   * Infers a formal parameter from the given configured parameter.
   *
   * @param parameter the configured parameter
   * @param referenceBuilder the type reference builder
   * @return the inferred formal parameter, or {@code null} if it cannot be inferred
   */
  public FormalParameter inferFormalParameter(final ConfiguredParameter parameter, final JvmTypeReferenceBuilder referenceBuilder) {
    if (parameter == null) {
      return null;
    }
    return inferFormalParameter(ParseTreeUtil.getParsedString(parameter, CheckcfgPackage.Literals.CONFIGURED_PARAMETER__PARAMETER),
      inferType(parameter, referenceBuilder));
  }

  /**
   * Infers a formal parameter from the given property specification.
   *
   * @param contribution the property specification
   * @param referenceBuilder the type reference builder
   * @return the inferred formal parameter, or {@code null} if it cannot be inferred
   */
  public FormalParameter inferFormalParameter(final ICheckCfgPropertySpecification contribution, final JvmTypeReferenceBuilder referenceBuilder) {
    if (contribution == null) {
      return null;
    }
    return inferFormalParameter(contribution.getName(), inferType(contribution, referenceBuilder));
  }

  /**
   * Creates a formal parameter with the given name and type.
   *
   * @param name the parameter name
   * @param type the parameter type
   * @return the created formal parameter, or {@code null} if the type is {@code null}
   */
  public FormalParameter inferFormalParameter(final String name, final JvmTypeReference type) {
    if (type == null) {
      return null;
    }
    final FormalParameter formalParameter = CheckFactoryImpl.eINSTANCE.createFormalParameter();
    formalParameter.setName(name);
    formalParameter.setType(type);
    return formalParameter;
  }

  public static PropertiesInferenceHelper getHelper() {
    return IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(URI.createURI("DUMMY.checkcfg")).get(
      PropertiesInferenceHelper.class);
  }

}
