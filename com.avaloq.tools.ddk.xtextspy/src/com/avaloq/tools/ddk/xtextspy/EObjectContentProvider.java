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

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * ContentProvider for the currently selected EObject.
 * Provides:
 * - key-value pairs where the key is the EAttribute and the value its corresponding value
 * - EStructuralFeatures
 * - EOperations.
 */
public class EObjectContentProvider implements ITreeContentProvider {

  @Inject
  private XtextElementSelectionListener xtextElementSelectionListener;

  /** {@inheritDoc} */
  public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {}

  /** {@inheritDoc} */
  public Object[] getElements(final Object inputElement) {
    EClass selectionElementType = xtextElementSelectionListener.getSelectedElementType();
    if (inputElement instanceof EClass && selectionElementType != null && ((EClass) inputElement).isSuperTypeOf(selectionElementType)) {
      return getChildren(selectionElementType);
    }
    return new Object[] {};
  }

  /** {@inheritDoc} */
  public Object[] getChildren(final Object parentElement) {
    EClass selectionElementType = xtextElementSelectionListener.getSelectedElementType();
    if (selectionElementType != null) {
      return Iterables.toArray(Iterables.concat(valuesForAttributes(selectionElementType.getEAllAttributes()), getReferenceFeatures(selectionElementType), selectionElementType.getEAllOperations()), Object.class);
    }
    return new Object[] {};
  }

  /**
   * Gets all EStructuralFeatures that are not EAttributes.
   * 
   * @param eClass
   *          the EClass
   * @return EStructuralFeatures
   */
  private Iterable<EStructuralFeature> getReferenceFeatures(final EClass eClass) {
    return Iterables.filter(eClass.getEAllStructuralFeatures(), Predicates.not(Predicates.instanceOf(EAttribute.class)));
  }

  /**
   * Retrieve the object's values for the given EAttributes.
   * 
   * @param attributes
   *          the EAttributes
   * @return List<Pair<EAttribute, Object>> the attribute+values - possibly containing null entries
   */
  private List<AttributeValuePair> valuesForAttributes(final EList<EAttribute> attributes) {
    final URI elementUri = xtextElementSelectionListener.getSelectedElementUri();
    XtextEditor editor = xtextElementSelectionListener.getEditor();
    if (editor != null && elementUri != null) {
      return editor.getDocument().readOnly(new IUnitOfWork<List<AttributeValuePair>, XtextResource>() {
        @SuppressWarnings("PMD.SignatureDeclareThrowsException")
        public List<AttributeValuePair> exec(final XtextResource state) throws Exception {
          final EObject eObject = state.getEObject(elementUri.fragment());
          List<AttributeValuePair> pairs = Lists.transform(attributes, new Function<EAttribute, AttributeValuePair>() {
            public AttributeValuePair apply(final EAttribute from) {
              return new AttributeValuePair(from, eObject.eGet(from));
            }
          });
          return pairs;
        }
      });
    }
    return newArrayList();
  }

  /** {@inheritDoc} */
  public void dispose() {}

  /** {@inheritDoc} */
  public Object getParent(final Object element) {
    return null;
  }

  /** {@inheritDoc} */
  public boolean hasChildren(final Object element) {
    return false;
  }

  /**
   * AttributeValuePair aggregates an EAttribute and an EObject's corresponding value.
   */
  public static class AttributeValuePair {
    private final EAttribute attribute;
    private final Object value;

    public AttributeValuePair(final EAttribute attribute, final Object value) {
      this.attribute = attribute;
      this.value = value;
    }

    public EAttribute getAttribute() {
      return attribute;
    }

    public Object getValue() {
      return value;
    }
  }
}
