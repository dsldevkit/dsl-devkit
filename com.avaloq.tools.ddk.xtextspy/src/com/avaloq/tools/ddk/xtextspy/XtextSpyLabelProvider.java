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
package com.avaloq.tools.ddk.xtextspy;

import java.util.Collections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.label.DeclarativeLabelProvider;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.util.PolymorphicDispatcher.ErrorHandler;
import org.eclipse.xtext.util.Tuples;

import com.avaloq.tools.ddk.xtextspy.EObjectContentProvider.AttributeValuePair;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;


/**
 * LabelProvider for ECore objects displayed in XtextSpy View.
 */
@SuppressWarnings("nls")
public class XtextSpyLabelProvider extends DeclarativeLabelProvider {

  private static final String MANY_DECORATOR = "EOccurrenceZeroToUnbounded.gif";
  private final PolymorphicDispatcher<Pair<Object, Integer>> decoratorDispatcher = new PolymorphicDispatcher<Pair<Object, Integer>>("decorate", 1, 1, Collections.singletonList(this), new ErrorHandler<Pair<Object, Integer>>() {
    public Pair<Object, Integer> handle(final Object[] params, final Throwable e) {
      return null;
    }
  });

  @Override
  @SuppressWarnings("PMD.AvoidDeeplyNestedIfStmts")
  public Image getImage(final Object element) {
    Image image = super.getImage(element);
    if (image != null) {
      Pair<Object, Integer> decoration = getDecoration(element);
      if (decoration != null && decoration.getFirst() != null) {
        Image overlay = convertToImage(decoration.getFirst());
        if (overlay != null) {
          image = new DecorationOverlayIcon(image, ImageDescriptor.createFromImage(overlay), decoration.getSecond()).createImage();
        }
      }
    }
    return image;
  }

  /**
   * Gets a decoration for an element.
   * 
   * @param element
   *          the element
   * @return Pair with decoration object and quadrant or null
   */
  public Pair<Object, Integer> getDecoration(final Object element) {
    return decoratorDispatcher.invoke(element);
  }

  /**
   * Decorate Object.
   * 
   * @param obj
   *          the obj
   * @return null
   */
  protected Pair<Object, Integer> decorate(final Object obj) {
    return null;
  }

  /**
   * Decorate Void.
   * 
   * @param obj
   *          the obj
   * @return null
   */
  protected Pair<Object, Integer> decorate(final Void obj) {
    return null;
  }

  /**
   * Decorate EStructuralFeature - if feature has multiplicity add * decoration.
   * 
   * @param feature
   *          the feature
   * @return multiplicity decorator if feature is many, else null
   */
  protected Pair<Object, Integer> decorate(final EStructuralFeature feature) {
    if (feature.isMany()) {
      return Tuples.<Object, Integer> create(MANY_DECORATOR, IDecoration.BOTTOM_LEFT);
    }
    return null;
  }

  /**
   * Decorate EOperation - if operation has multiplicity add * decoration.
   * 
   * @param operation
   *          the operation
   * @return multiplicity decorator if feature is many, else null
   */
  protected Pair<Object, Integer> decorate(final EOperation operation) {
    if (operation.isMany()) {
      return Tuples.<Object, Integer> create(MANY_DECORATOR, IDecoration.BOTTOM_LEFT);
    }
    return null;
  }

  /**
   * Simple name of a Class unless it is EOperation, EAttribute or EStructuralFeature.
   * 
   * @param clazz
   *          the clazz
   * @return the name
   */
  protected String text(final Class<?> clazz) {
    if (clazz == EOperation.class) {
      return "Operations";
    }
    if (clazz == EStructuralFeature.class) {
      return "References";
    }
    if (clazz == EAttribute.class) {
      return "Attributes";
    }
    return clazz.getSimpleName();
  }

  /**
   * Icon for a Class if it is EOperation, EAttribute or EStructuralFeature - null otherwise.
   * 
   * @param clazz
   *          the clazz
   * @return the image or null
   */
  protected Object image(final Class<?> clazz) {
    if (clazz == EOperation.class || clazz == EStructuralFeature.class || clazz == EAttribute.class) {
      return PlatformUI.getWorkbench().getSharedImages().getImage(org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT);
      // return org.eclipse.jdt.internal.ui.JavaPluginImages.get(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_LOGICAL_PACKAGE);
      // return PlatformUI.getWorkbench().getSharedImages().getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_LOGICAL_PACKAGE);
      // return PlatformUI.getWorkbench().getSharedImages().getImage(org.eclipse.ui.ISharedImages.IMG_OBJ_PROJECT);
    }
    return null;
  }

  /**
   * Text for ENamedElement.
   * 
   * @param eNamedElement
   *          the ENamedElement
   * @return the name
   */
  protected String text(final ENamedElement eNamedElement) {
    return eNamedElement.getName();
  }

  /**
   * Image for EClassifier.
   * 
   * @param eClassifier
   *          the EClassifier
   * @return the image name
   */
  protected Object image(final EClassifier eClassifier) {
    return "EClass.gif";
    // return PlatformUI.getWorkbench().getSharedImages().getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CLASS);
  }

  /**
   * Text for EDataType.
   * 
   * @param eDataType
   *          the EDataType
   * @return the primitive name
   */
  protected String text(final EDataType eDataType) {
    return eDataType.getInstanceClass().getSimpleName();
  }

  /**
   * Image for EDataType.
   * 
   * @param eDataType
   *          the EDataType
   * @return the image name
   */
  protected Object image(final EDataType eDataType) {
    return PlatformUI.getWorkbench().getSharedImages().getImage(org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_CLASS_DEFAULT);
  }

  /**
   * Text for EStructuralFeature.
   * 
   * @param eStructuralFeature
   *          the EStructuralFeature
   * @return the name
   */
  protected String text(final EStructuralFeature eStructuralFeature) {
    return Joiner.on(" : ").join(eStructuralFeature.getName(), getText(eStructuralFeature.getEType()));
  }

  /**
   * Text for attribute-value pair provided by {@link EObjectContentProvider}.
   * 
   * @param attributeValuePair
   *          the attribute-value pair
   * @return name of attribute and value
   */
  protected String text(final AttributeValuePair attributeValuePair) {
    String attribute = getText(attributeValuePair.getAttribute());
    String value = attributeValuePair.getValue() == null ? "<null>" : attributeValuePair.getValue().toString();
    return attribute + " (" + value + ")";
  }

  /**
   * Image for attribute-value pair provided by {@link EObjectContentProvider}.
   * 
   * @param attributeValuePair
   *          the attribute-value pair
   * @return image for attribute
   */
  protected Object image(final AttributeValuePair attributeValuePair) {
    return getImage(attributeValuePair.getAttribute());
  }

  /**
   * Text for EStructuralFeature.
   * 
   * @param eStructuralFeature
   *          the EStructuralFeature
   * @return the image name
   */
  protected Object image(final EStructuralFeature eStructuralFeature) {
    return "EReference.gif";
  }

  /**
   * Text for EAttribute.
   * 
   * @param eAttribute
   *          the EAttribute
   * @return the image name
   */
  protected Object image(final EAttribute eAttribute) {

    // return PlatformUI.getWorkbench().getSharedImages().getImage(org.eclipse.jdt.ui.ISharedImages.IMG_FIELD_PUBLIC);
    return "EAttribute.gif";
  }

  /**
   * Text for EOperation.
   * 
   * @param eOperation
   *          the EOperation
   * @return the name and types of all EParameters
   */
  protected String text(final EOperation eOperation) {
    return eOperation.getName() + "(" + Joiner.on(", ").join(Iterables.transform(eOperation.getEParameters(), new Function<EParameter, String>() {
      public String apply(final EParameter input) {
        return getText(input.getEType());
      }
    })) + ") : " + (eOperation.getEType() == null ? "Void" : getText(eOperation.getEType()));
  }

  /**
   * Image for EOperation.
   * 
   * @param eOperation
   *          the EOperation
   * @return the image name
   */
  protected Object image(final EOperation eOperation) {
    return "EOperation.gif";
    // return "platform:/plugin/org.eclipse.emf.ecore.edit/icons/full/obj16/EOperation.gif";
  }

  /**
   * Text for an EClassNode.
   * 
   * @param eClassNode
   *          the EClassNode
   * @return text for the EClass
   */
  protected String text(final EClassNode eClassNode) {
    return getText(eClassNode.getEClass());
  }

  /**
   * Image for an EClassNode.
   * 
   * @param eClassNode
   *          the EClassNode
   * @return image for the EClass
   */
  protected Object image(final EClassNode eClassNode) {
    return getImage(eClassNode.getEClass());
  }

  /**
   * Image for an EEnum.
   * 
   * @param eEnum
   *          the EEnum
   * @return image for the EEnum
   */
  protected Object image(final EEnum eEnum) {
    return getImage("EEnum.gif");
  }

  /**
   * Image for an EEnumLiteral.
   * 
   * @param eEnumLiteral
   *          the EEnumLiteral
   * @return image for the EEnumLiteral
   */
  protected Object image(final EEnumLiteral eEnumLiteral) {
    return getImage("EEnumLiteral.gif");
  }
}

