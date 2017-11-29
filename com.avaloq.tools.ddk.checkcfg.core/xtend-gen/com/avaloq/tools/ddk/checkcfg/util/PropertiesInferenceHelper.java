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

import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.impl.CheckFactoryImpl;
import com.avaloq.tools.ddk.checkcfg.CheckCfgUtil;
import com.avaloq.tools.ddk.checkcfg.ICheckCfgPropertySpecification;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckConfiguration;
import com.avaloq.tools.ddk.checkcfg.checkcfg.CheckcfgPackage;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfigurableSection;
import com.avaloq.tools.ddk.checkcfg.checkcfg.ConfiguredParameter;
import com.avaloq.tools.ddk.xtext.util.ParseTreeUtil;
import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.xbase.XBooleanLiteral;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeReferenceBuilder;

@SuppressWarnings("all")
public class PropertiesInferenceHelper {
  @Inject
  private JvmTypeReferenceBuilder.Factory typeRefBuilderFactory;
  
  private final static String BOOLEAN = "boolean";
  
  private final static String STRING = "java.lang.String";
  
  private final static String NUMBER = "int";
  
  private final static String STRING_LIST = "List<java.lang.String>";
  
  private final static String NUMBER_LIST = "List<java.lang.Integer>";
  
  private final static String BOOLEAN_LIST = "List<java.lang.Boolean>";
  
  public EList<FormalParameter> getProperties(final CheckConfiguration checkConfiguration, final EList<FormalParameter> properties) {
    EList<FormalParameter> _xblockexpression = null;
    {
      Resource _eResource = checkConfiguration.eResource();
      ResourceSet _resourceSet = _eResource.getResourceSet();
      final JvmTypeReferenceBuilder referenceBuilder = this.typeRefBuilderFactory.create(_resourceSet);
      final List<ConfigurableSection> configurableSections = EcoreUtil2.<ConfigurableSection>getAllContentsOfType(checkConfiguration, ConfigurableSection.class);
      configurableSections.add(0, checkConfiguration);
      for (final ConfigurableSection section : configurableSections) {
        {
          final EList<ConfiguredParameter> parameters = section.getParameterConfigurations();
          for (final ConfiguredParameter parameter : parameters) {
            {
              final FormalParameter formalParameter = this.inferFormalParameter(parameter, referenceBuilder);
              boolean _notEquals = (!Objects.equal(formalParameter, null));
              if (_notEquals) {
                properties.add(formalParameter);
              }
            }
          }
        }
      }
      final Collection<ICheckCfgPropertySpecification> contributions = CheckCfgUtil.getAllPropertyContributions();
      for (final ICheckCfgPropertySpecification contribution : contributions) {
        {
          final FormalParameter formalParameter = this.inferFormalParameter(contribution, referenceBuilder);
          boolean _notEquals = (!Objects.equal(formalParameter, null));
          if (_notEquals) {
            properties.add(formalParameter);
          }
        }
      }
      _xblockexpression = properties;
    }
    return _xblockexpression;
  }
  
  public JvmTypeReference inferType(final ICheckCfgPropertySpecification contribution, final JvmTypeReferenceBuilder referenceBuilder) {
    JvmTypeReference _switchResult = null;
    ICheckCfgPropertySpecification.PropertyType _type = contribution.getType();
    if (_type != null) {
      switch (_type) {
        case BOOLEAN:
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.BOOLEAN);
          break;
        case NUMBER:
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.NUMBER);
          break;
        case STRING:
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.STRING);
          break;
        case NUMBERS:
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.NUMBER_LIST);
          break;
        case STRINGS:
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.STRING_LIST);
          break;
        case BOOLEANS:
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.BOOLEAN_LIST);
          break;
        default:
          _switchResult = null;
          break;
      }
    } else {
      _switchResult = null;
    }
    return _switchResult;
  }
  
  public JvmTypeReference inferListType(final XListLiteral newValue, final JvmTypeReferenceBuilder referenceBuilder) {
    JvmTypeReference _xblockexpression = null;
    {
      EList<XExpression> _elements = newValue.getElements();
      int _size = _elements.size();
      boolean _lessThan = (_size < 1);
      if (_lessThan) {
        return null;
      }
      JvmTypeReference _switchResult = null;
      EList<XExpression> _elements_1 = newValue.getElements();
      XExpression _get = _elements_1.get(0);
      boolean _matched = false;
      if (_get instanceof XBooleanLiteral) {
        _matched=true;
        _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.BOOLEAN_LIST);
      }
      if (!_matched) {
        if (_get instanceof XNumberLiteral) {
          _matched=true;
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.NUMBER_LIST);
        }
      }
      if (!_matched) {
        if (_get instanceof XStringLiteral) {
          _matched=true;
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.STRING_LIST);
        }
      }
      if (!_matched) {
        _switchResult = null;
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  public JvmTypeReference inferType(final ConfiguredParameter parameter, final JvmTypeReferenceBuilder referenceBuilder) {
    JvmTypeReference _xblockexpression = null;
    {
      final XExpression newValue = parameter.getNewValue();
      JvmTypeReference _switchResult = null;
      boolean _matched = false;
      if (newValue instanceof XBooleanLiteral) {
        _matched=true;
        _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.BOOLEAN);
      }
      if (!_matched) {
        if (newValue instanceof XNumberLiteral) {
          _matched=true;
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.NUMBER);
        }
      }
      if (!_matched) {
        if (newValue instanceof XStringLiteral) {
          _matched=true;
          _switchResult = referenceBuilder.typeRef(PropertiesInferenceHelper.STRING);
        }
      }
      if (!_matched) {
        if (newValue instanceof XListLiteral) {
          _matched=true;
          _switchResult = this.inferListType(((XListLiteral)newValue), referenceBuilder);
        }
      }
      if (!_matched) {
        _switchResult = null;
      }
      _xblockexpression = _switchResult;
    }
    return _xblockexpression;
  }
  
  public FormalParameter inferFormalParameter(final ConfiguredParameter parameter, final JvmTypeReferenceBuilder referenceBuilder) {
    FormalParameter _xblockexpression = null;
    {
      boolean _equals = Objects.equal(parameter, null);
      if (_equals) {
        return null;
      }
      String _parsedString = ParseTreeUtil.getParsedString(parameter, CheckcfgPackage.Literals.CONFIGURED_PARAMETER__PARAMETER);
      JvmTypeReference _inferType = this.inferType(parameter, referenceBuilder);
      _xblockexpression = this.inferFormalParameter(_parsedString, _inferType);
    }
    return _xblockexpression;
  }
  
  public FormalParameter inferFormalParameter(final ICheckCfgPropertySpecification contribution, final JvmTypeReferenceBuilder referenceBuilder) {
    FormalParameter _xblockexpression = null;
    {
      boolean _equals = Objects.equal(contribution, null);
      if (_equals) {
        return null;
      }
      String _name = contribution.getName();
      JvmTypeReference _inferType = this.inferType(contribution, referenceBuilder);
      _xblockexpression = this.inferFormalParameter(_name, _inferType);
    }
    return _xblockexpression;
  }
  
  public FormalParameter inferFormalParameter(final String name, final JvmTypeReference type) {
    FormalParameter _xblockexpression = null;
    {
      boolean _equals = Objects.equal(type, null);
      if (_equals) {
        return null;
      }
      final FormalParameter formalParameter = CheckFactoryImpl.eINSTANCE.createFormalParameter();
      formalParameter.setName(name);
      formalParameter.setType(type);
      _xblockexpression = formalParameter;
    }
    return _xblockexpression;
  }
  
  public static PropertiesInferenceHelper getHelper() {
    URI _createURI = URI.createURI("DUMMY.checkcfg");
    IResourceServiceProvider _resourceServiceProvider = IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(_createURI);
    return _resourceServiceProvider.<PropertiesInferenceHelper>get(
      PropertiesInferenceHelper.class);
  }
}
