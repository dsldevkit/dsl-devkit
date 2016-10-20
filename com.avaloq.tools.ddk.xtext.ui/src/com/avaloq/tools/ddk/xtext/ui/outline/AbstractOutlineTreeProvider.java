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
package com.avaloq.tools.ddk.xtext.ui.outline;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.BackgroundOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.EStructuralFeatureNode;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineNodeFactory;
import org.eclipse.xtext.ui.label.ILabelProviderImageDescriptorExtension;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.util.Tuples;

import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Base class for ACF customization of the default outline structure.
 */
public abstract class AbstractOutlineTreeProvider extends BackgroundOutlineTreeProvider {

  private final PolymorphicDispatcher<EObjectNode> createNodeDispatcher = PolymorphicDispatcher.createForSingleTarget("_createNode", 2, 3, this); //$NON-NLS-1$
  private final PolymorphicDispatcher<Object> textDispatcher = PolymorphicDispatcher.createForSingleTarget("_text", 1, 2, this); //$NON-NLS-1$
  private final PolymorphicDispatcher<ImageDescriptor> imageDispatcher = PolymorphicDispatcher.createForSingleTarget("_image", 1, 2, this); //$NON-NLS-1$
  private final PolymorphicDispatcher<Boolean> isLeafDispatcher = PolymorphicDispatcher.createForSingleTarget("_isLeaf", 1, 2, this); //$NON-NLS-1$
  private final PolymorphicDispatcher<Void> createChildrenDispatcher = PolymorphicDispatcher.createForSingleTarget("_createChildren", 2, 2, this); //$NON-NLS-1$

  @Inject
  private ILocationInFileProvider locationInFileProvider;

  @Inject
  private ILabelProvider labelProvider;

  @Inject
  private OutlineNodeFactory factory;

  /**
   * {@link ENamedElement}s relevant for outline presentation.
   *
   * @return relevant {@link ENamedElement}s
   */
  protected abstract List<ENamedElement> getRelevantElements();

  /**
   * Check if the given {@link EObject} is a relevant element.
   * An element is relevant if it is a relevant {@link EClass}, or if it is contained in a relevant {@link EStructuralFeature}.
   *
   * @param modelElement
   *          the {@link EObject} to check
   * @return {@code true} if the given {@link EObject} is relevant, {@code false} otherwise
   */
  private boolean isRelevantElement(final EObject modelElement) {
    // check if modelElement is a relevant EClass
    for (ENamedElement element : getRelevantElements()) {
      if (element instanceof EClass && ((EClass) element).isInstance(modelElement)) {
        return true;
      }
    }
    // check if modelElement is contained in a relevant EStructuralFeature
    return isContainedInRelevantFeature(modelElement);
  }

  @Override
  public void createChildren(final IOutlineNode parentNode, final EObject modelElement) {
    if (modelElement != null && parentNode.hasChildren()) {
      createChildrenDispatcher.invoke(parentNode, modelElement);
    }
  }

  /**
   * Default for createChildrenDispatcher with outline node as a parent node.
   *
   * @param parentNode
   *          the {@link IOutlineNode}
   * @param modelElement
   *          the {@link EObject} model element
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  public void _createChildren(final IOutlineNode parentNode, final EObject modelElement) {
    // CHECKSTYLE:CHECK-ON
    if (modelElement != null && parentNode.hasChildren()) {
      if (parentNode instanceof DocumentRootNode) {
        internalCreateChildren((DocumentRootNode) parentNode, modelElement);
      } else if (parentNode instanceof EStructuralFeatureNode) {
        internalCreateChildren((EStructuralFeatureNode) parentNode, modelElement);
      } else {
        internalCreateChildren(parentNode, modelElement);
      }
    }
  }

  /**
   * Default for createChildrenDispatcher with document root as a parent node.
   *
   * @param parentNode
   *          the {@link DocumentRootNode}
   * @param modelElement
   *          the {@link EObject} model element
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  public void _createChildren(final DocumentRootNode parentNode, final EObject modelElement) {
    // CHECKSTYLE:CHECK-ON
    internalCreateChildren((IOutlineNode) parentNode, modelElement);
  }

  /**
   * Do not create Model nodes in the outline if the model node itself is not specified as required element.
   * In that case, when the valid model is processed it is not added to the outline but its children are.
   *
   * @param parentNode
   *          the parent {@link IOutlineNode}
   * @param modelElement
   *          a valid {@link EObject}
   */
  @Override
  protected void internalCreateChildren(final DocumentRootNode parentNode, final EObject modelElement) {
    if (getRelevantElements().contains(modelElement.eClass())) {
      createNode(parentNode, modelElement);
    } else {
      createChildrenDispatcher.invoke(parentNode, modelElement);
    }
  }

  /**
   * Creates children for all {@link EStructuralFeatures} (that are set) of the given {@link EObject} model element. {@inheritDoc}
   *
   * @param parentNode
   *          the parent {@link IOutlineNode}
   * @param modelElement
   *          a valid {@link EObject}
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void internalCreateChildren(final IOutlineNode parentNode, final EObject modelElement) {
    // from all structural features, select only those which are set and retrieve the text location
    // TODO : sorting for feature lists needs to be based on the objects in the list, not the feature.
    List<Pair<Integer, EStructuralFeature>> pairsLocationFeature = Lists.newArrayList();
    for (EStructuralFeature structuralFeature : modelElement.eClass().getEAllStructuralFeatures()) {
      if (modelElement.eIsSet(structuralFeature)) {
        int offset = 0;
        List<INode> nodes = NodeModelUtils.findNodesForFeature(modelElement, structuralFeature);
        if (!nodes.isEmpty()) {
          offset = nodes.get(0).getTotalOffset();
        }
        pairsLocationFeature.add(Tuples.create(offset, structuralFeature));
      }
    }
    // sort the features according to their appearance in the source text
    Collections.sort(pairsLocationFeature, new Comparator<Pair<Integer, EStructuralFeature>>() {
      @Override
      public int compare(final Pair<Integer, EStructuralFeature> o1, final Pair<Integer, EStructuralFeature> o2) {
        return o1.getFirst() - o2.getFirst();
      }
    });
    // go through the sorted list of children and create nodes
    for (Pair<Integer, EStructuralFeature> pairLocationFeature : pairsLocationFeature) {
      EStructuralFeature feature = pairLocationFeature.getSecond();
      if (feature instanceof EAttribute) {
        if (getRelevantElements().contains(feature)) {
          createNodeDispatcher.invoke(parentNode, modelElement, feature);
        }
      } else if (feature instanceof EReference && ((EReference) feature).isContainment()) { // only consider containment references
        EList<EObject> featureElements;
        Object value = modelElement.eGet(feature);
        if (feature.isMany()) {
          featureElements = (EList<EObject>) value;
        } else {
          featureElements = new BasicEList<EObject>();
          featureElements.add((EObject) value);
        }
        for (EObject childElement : featureElements) {
          if (childElement == null) {
            continue;
          }
          if (isRelevantElement(childElement)) {
            createNodeDispatcher.invoke(parentNode, childElement);
          } else {
            createChildrenDispatcher.invoke(parentNode, childElement);
          }
        }
      }
    }
  }

  /**
   * Determines whether the given modelElement is a leaf node by dispatching to the most appropriate signature of _isLeaf().
   *
   * @param modelElement
   *          EObject
   * @return result of the most appropriate _isLeaf() signature
   */
  @Override
  protected boolean isLeaf(final EObject modelElement) {
    if (modelElement == null) {
      return true;
    }
    return isLeafDispatcher.invoke(modelElement);
  }

  /**
   * Check if the given attribute for the given {@link EObject} is a leaf. {@inheritDoc}
   *
   * @param modelElement
   *          the {@link EObject} to check
   * @return {@code true} if the given {@link EObject} is a leaf, {@code false} otherwise
   */
  protected boolean _isLeaf(final EObject modelElement) {
    // Unfortunately, we need to calculate the isLeaf property before creating the node, because the outline view does not refresh the expandable icon even
    // though the node later receives children.
    for (EObject childElement : modelElement.eContents()) {
      if (isRelevantElement(childElement)) {
        return false;
      }
    }
    // recursively check children
    for (EObject childElement : modelElement.eContents()) {
      boolean isLeaf = isLeafDispatcher.invoke(childElement);
      if (!isLeaf) {
        return false;
      }
    }
    return true;
  }

  @Override
  public ImageDescriptor getImageDescriptor(final Object modelElement) {
    return imageDispatcher.invoke(modelElement);
  }

  /**
   * Default for imageDispatcher with two arguments.
   *
   * @param modelElement
   *          the {@link EObject} model element
   * @param eAttribute
   *          the {@link EAttribute}
   * @return the {@link Image}
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  protected ImageDescriptor _image(final Object modelElement, final Object eAttribute) {
    // CHECKSTYLE:CHECK-ON
    if (getLabelProvider() instanceof ILabelProviderImageDescriptorExtension) {
      return ((ILabelProviderImageDescriptorExtension) getLabelProvider()).getImageDescriptor(Tuples.create(modelElement, eAttribute));
    } else {
      return null;
    }
  }

  /**
   * Default for imageDispatcher with two arguments.
   *
   * @param modelElement
   *          the {@link EObject} model element
   * @return the {@link Image}
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  protected ImageDescriptor _image(final Object modelElement) {
    // CHECKSTYLE:CHECK-ON
    if (getLabelProvider() instanceof ILabelProviderImageDescriptorExtension) {
      return ((ILabelProviderImageDescriptorExtension) getLabelProvider()).getImageDescriptor(modelElement);
    } else {
      return null;
    }
  }

  /**
   * Default for textDispatcher with two arguments.
   *
   * @param modelElement
   *          the {@link EObject} model element
   * @return the label text
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  protected Object _text(final Object modelElement) {
    // CHECKSTYLE:CHECK-ON
    if (getLabelProvider() instanceof IStyledLabelProvider) {
      return ((IStyledLabelProvider) getLabelProvider()).getStyledText(modelElement);
    } else {
      return getLabelProvider().getText(modelElement);
    }
  }

  @Override
  protected Object getText(final Object modelElement) {
    return textDispatcher.invoke(modelElement);
  }

  /**
   * Check if the given {@link EObject} is contained in a relevant {@link EStructuralFeature}.
   *
   * @param modelElement
   *          the {@link EObject} to check
   * @return {@code true} if the given {@link EObject} is contained in a relevant {@link EStructuralFeature}, {@code false} otherwise
   */
  private boolean isContainedInRelevantFeature(final EObject modelElement) {
    return getRelevantElements().contains(modelElement.eContainingFeature());
  }

  /**
   * Creates a new structural feature node with a given image description and label.
   *
   * @param parentNode
   *          the parent {@link IOutlineNode}
   * @param owner
   *          a valid model element as {@link EObject}
   * @param feature
   *          a structural feature {@link EStructuralFeature}
   * @param image
   *          an image descriptor {@link ImageDescriptor}
   * @param text
   *          the label text
   * @param isLeaf
   *          true if feature is a leaf
   * @return newly created structural feature node
   */
  protected EStructuralFeatureNode createEStructuralFeatureNode(final IOutlineNode parentNode, final EObject owner, final EStructuralFeature feature, final ImageDescriptor image, final Object text, final boolean isLeaf) {
    boolean isFeatureSet = owner.eIsSet(feature);
    EStructuralFeatureNode eStructuralFeatureNode = new EStructuralFeatureNode(owner, feature, parentNode, image, text, isLeaf || !isFeatureSet);
    if (isFeatureSet) {
      ITextRegion region = getLocationInFileProvider().getFullTextRegion(owner, feature, 0);
      if (feature.isMany()) {
        int numValues = ((Collection<?>) owner.eGet(feature)).size();
        ITextRegion fullTextRegion = getLocationInFileProvider().getFullTextRegion(owner, feature, numValues - 1);
        if (fullTextRegion != null) {
          region = region.merge(fullTextRegion);
        }
      }
      eStructuralFeatureNode.setTextRegion(region);
    }
    return eStructuralFeatureNode;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EObjectNode createNode(final IOutlineNode parent, final EObject modelElement) {
    return createNodeDispatcher.invoke(parent, modelElement);
  }

  /**
   * Creates an outline node if the given {@link EAttribute} of the given {@link EObject} is a relevant model element.
   *
   * @param parentNode
   *          the parent {@link IOutlineNode}
   * @param modelElement
   *          a valid model element as {@link EObject}
   * @param eAttribute
   *          a valid {@link EAttribute}
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  protected void _createNode(final IOutlineNode parentNode, final EObject modelElement, final EAttribute eAttribute) {
    // CHECKSTYLE:CHECK-ON
    createEStructuralFeatureNode(parentNode, modelElement, eAttribute, imageDispatcher.invoke(modelElement, eAttribute), textDispatcher.invoke(modelElement), isLeafDispatcher.invoke(modelElement));
  }

  /**
   * Default for createNodeDispatcher with outline node as a parent node.
   *
   * @param parentNode
   *          the {@link IOutlineNode}
   * @param modelElement
   *          the {@link EObject} model element
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  protected void _createNode(final IOutlineNode parentNode, final EObject modelElement) {
    // CHECKSTYLE:CHECK-ON
    factory.createEObjectNode(parentNode, modelElement, imageDispatcher.invoke(modelElement), textDispatcher.invoke(modelElement), isLeafDispatcher.invoke(modelElement));
  }

  /**
   * Default for createNodeDispatcher with document root as a parent node.
   *
   * @param parentNode
   *          the {@link DocumentRootNode}
   * @param modelElement
   *          the {@link EObject} model element
   */
  // CHECKSTYLE:CHECK-OFF Name (dispatcher enforces names starting with underscore)
  protected void _createNode(final DocumentRootNode parentNode, final EObject modelElement) {
    // CHECKSTYLE:CHECK-ON
    Object text = textDispatcher.invoke(modelElement);
    if (text == null) {
      text = modelElement.eResource().getURI().trimFileExtension().lastSegment();
    }
    factory.createEObjectNode(parentNode, modelElement, imageDispatcher.invoke(modelElement), text, isLeafDispatcher.invoke(modelElement));
  }

  public ILocationInFileProvider getLocationInFileProvider() {
    return locationInFileProvider;
  }

  @Override
  public ILabelProvider getLabelProvider() {
    return labelProvider;
  }

  public OutlineNodeFactory getFactory() {
    return factory;
  }

}
