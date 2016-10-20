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
package com.avaloq.tools.ddk.xtext.serializer.tokens;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.linking.impl.LinkingHelper;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.serializer.tokens.CrossReferenceSerializer;
import org.eclipse.xtext.serializer.tokens.SerializerScopeProviderBinding;
import org.eclipse.xtext.util.EmfFormatter;

import com.google.inject.Inject;


/**
 * Extension of the new Xtext CrossReferenceSerializer.
 * Some useful functionality carried over from the old one: {@link org.eclipse.xtext.parsetree.reconstr.impl.CrossReferenceSerializer}.
 */
public class DdkCrossReferenceSerializer extends CrossReferenceSerializer {

  @Inject
  private LinkingHelper linkingHelper;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  @Inject
  @SerializerScopeProviderBinding
  private IScopeProvider scopeProvider;

  @Inject
  private IValueConverterService valueConverter;

  /**
   * Returns a value, converted according to the grammar element's rule. Based on
   * {@link org.eclipse.xtext.parsetree.reconstr.impl.CrossReferenceSerializer#getConvertedValue}
   *
   * @param unconverted
   *          the unconverted value, must not be{@code null}
   * @param grammarElement
   *          the cross reference, must not be {@code null}
   * @return the converted value or {@code null} if conversion failed
   */
  protected String getConvertedValue(final String unconverted, final AbstractElement grammarElement) {
    String ruleName = linkingHelper.getRuleNameFrom(grammarElement);
    if (ruleName == null) {
      throw new IllegalStateException("Could not determine targeted rule name for " //$NON-NLS-1$
          + EmfFormatter.objPath(grammarElement));
    }
    return valueConverter.toString(unconverted, ruleName);
  }

  /**
   * Returns the unconverted link text for the grammar element's scope. Based on
   * {@link org.eclipse.xtext.parsetree.reconstr.impl.CrossReferenceSerializer#getUnconvertedLinkText}
   *
   * @param object
   *          the object, must not be {@code null}
   * @param reference
   *          the reference, must not be {@code null}
   * @param context
   *          the context, must not be{@code null}
   * @return the unconverted link text or {@code null} if the {@code object} is not in the {@code context}'s scope
   */
  protected String getUnconvertedLinkText(final EObject object, final EReference reference, final EObject context) {
    IScope scope = scopeProvider.getScope(context, reference);
    if (scope == null || scope == IScope.NULLSCOPE) {
      return null;
    }
    IEObjectDescription eObjectDescription = scope.getSingleElement(object);
    if (eObjectDescription != null) {
      return qualifiedNameConverter.toString(eObjectDescription.getName());
    }
    return null;
  }

}
