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
package com.avaloq.tools.ddk.xtext.ui.labeling;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.impl.HiddenLeafNode;
import org.eclipse.xtext.nodemodel.impl.LeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.label.DeclarativeLabelProvider;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.PolymorphicDispatcher;
import org.eclipse.xtext.util.PolymorphicDispatcher.ErrorHandler;
import org.eclipse.xtext.util.Strings;

import com.avaloq.tools.ddk.xtext.ui.Messages;
import com.google.inject.Inject;


/**
 * Enables polymorphic dispatching of "getStyledLabel" in order to allow
 * provision of styled labels for specific classes.
 * Also supports labels for {@link EAttribute}s.
 */
public abstract class AbstractLabelProvider extends DeclarativeLabelProvider {
  private static final String IMAGE_METHOD_NAME = "image"; //$NON-NLS-1$
  private static final int MAX_FEATURE_VALUE_LENGTH = 34;
  private static final String FEATURE_SEPARATOR = " = "; //$NON-NLS-1$
  private static final String FEATURE_TYPE = "type"; //$NON-NLS-1$
  private static final String FEATURE_NAME = "name"; //$NON-NLS-1$
  private static final String GET_STYLED_LABEL_METHOD_NAME = "getStyledLabel"; //$NON-NLS-1$
  public static final String NAME_SEPARATOR = " : "; //$NON-NLS-1$
  public static final String CONTINUED = " ..."; //$NON-NLS-1$
  private static final String ANNOTATION_SEPARATOR = " - "; //$NON-NLS-1$
  private static final RGB GREY_COLOR = new RGB(105, 105, 105);

  @Inject
  private FileExtensionProvider extensionProvider;

  /**
   * A built-in styler using the {@link org.eclipse.jface.preference.JFacePreferences#QUALIFIER_COLOR} managed in the JFace color registry (See
   * {@link org.eclipse.jface.resource.JFaceResources#getColorRegistry()}).
   */
  public static final StyledString.Styler STRIKETHROUGH_STYLER = new StyledString.Styler() {
    @Override
    public void applyStyles(final TextStyle textStyle) {
      textStyle.strikeout = true;
    }
  };

  /**
   * A {@link StyledString.Styler} with a grey foreground color.
   */
  public static final StyledString.Styler GREY_FOREGROUND_STYLER = new StyledString.Styler() {

    @Override
    public void applyStyles(final TextStyle textStyle) {
      textStyle.foreground = new Color(PlatformUI.getWorkbench().getDisplay(), GREY_COLOR);
    }
  };

  @Inject
  public AbstractLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }

  private final PolymorphicDispatcher<Object> getStyledLabelMethod = PolymorphicDispatcher.createForSingleTarget(GET_STYLED_LABEL_METHOD_NAME, 1, 2, this);

  private final PolymorphicDispatcher<Object> imageDispatcher = new PolymorphicDispatcher<Object>(IMAGE_METHOD_NAME, 1, 2, Collections.singletonList(this), new ErrorHandler<Object>() {
    @Override
    public Object handle(final Object[] params, final Throwable e) {
      return handleImageError(params, e);
    }
  });

  /**
   * {@inheritDoc}
   */
  @Override
  protected final Object doGetText(final Object object) {
    if (object instanceof Pair<?, ?>) {
      Pair<?, ?> pair = (Pair<?, ?>) object;
      return getStyledLabelMethod.invoke(pair.getFirst(), pair.getSecond());
    }
    return getStyledLabelMethod.invoke(object);
  }

  @Override
  protected Object doGetImage(final Object object) {
    if (object instanceof Pair<?, ?>) {
      Pair<?, ?> pair = (Pair<?, ?>) object;
      return imageDispatcher.invoke(pair.getFirst(), pair.getSecond());
    }
    return super.doGetImage(object);
  }

  /**
   * Default implementation for image dispatcher taking two arguments returning {@code null}.
   *
   * @param element
   *          the first argument
   * @param feature
   *          the second argument
   * @return {@code null} by default
   */
  @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
  public Object image(final Object element, final Object feature) {
    return null;
  }

  /**
   * Fall back if subclasses don't implement getStyledLabel for a specific class.
   *
   * @param object
   *          object to format
   * @return "to-string"-able object representing a label for the object provided
   */
  public final Object getStyledLabel(final Object object) {
    return super.doGetText(object);
  }

  /**
   * Default implementation for getStyledLabel dispatcher taking two arguments returning {@code null}.
   *
   * @param element
   *          the first argument
   * @param feature
   *          the second argument
   * @return {@code null} by default
   */
  @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
  public Object getStyledLabel(final Object element, final Object feature) {
    return null;
  }

  /**
   * Creates a default styled string for the given {@link EObject} model element.
   *
   * @param eObject
   *          the {@link EObject} model element
   * @return the styled string for the given {@link EObject} model element
   */
  public Object getStyledLabel(final EObject eObject) {
    // first check if object is foreign to this grammar
    if (isForeignXtextObject(eObject)) {
      return getForeignObjectLabel(eObject);
    }
    // first check if object has a name
    String name = getNameOfObject(eObject);
    if (name != null) {
      return qualifiedStyledString(name, getTypeOfObject(eObject));
    }
    // secondly check first parsed element (keyword / feature)
    ICompositeNode node = NodeModelUtils.getNode(eObject);
    if (node != null) {
      Iterator<ILeafNode> leafNodesIterator = node.getLeafNodes().iterator();
      while (leafNodesIterator.hasNext()) {
        ILeafNode genericLeafNode = leafNodesIterator.next();
        if (!(genericLeafNode instanceof HiddenLeafNode)) {
          LeafNode leafNode = (LeafNode) genericLeafNode;
          return getLabelName(leafNode.getText(), eObject.eClass().getName(), true);
        }
      }
    }
    // fallback
    return super.doGetText(eObject);
  }

  /**
   * Creates a default styled label for the given {@link EStructuralFeature} of the given {@link EObject} model element.
   *
   * @param modelElement
   *          the {@link EObject} model element
   * @param feature
   *          the {@link EStructuralFeature} for which to return the label
   * @return the styled label text
   */
  // CHECKSTYLE:OFF
  protected Object getStyledLabel(final EObject modelElement, final EStructuralFeature feature) {
    // CHECKSTYLE:ON
    // by default find a previous keyword and use it as label name
    String name = findKeywordBeforeFeature(modelElement, feature);
    if (name == null) {
      // if no previous keyword found, then the fall back is the attribute name
      name = feature.getName();
    }
    if (settingCreateAssignment()) {
      String valueString = null;
      Object value = modelElement.eGet(feature);
      if (value != null) {
        if (feature instanceof EAttribute && value != null) {
          valueString = value.toString();
        } else if (value instanceof EObject) {
          Object styledLabel = getStyledLabel((EObject) value);
          if (styledLabel != null) {
            valueString = styledLabel.toString();
          }
        } else {
          Object styledLabel = getStyledLabel(value);
          if (styledLabel != null) {
            valueString = styledLabel.toString();
          }
        }
        if (valueString != null && valueString.length() > MAX_FEATURE_VALUE_LENGTH) {
          StringBuilder stringBuilder = new StringBuilder(valueString.substring(0, MAX_FEATURE_VALUE_LENGTH - CONTINUED.length()));
          stringBuilder.append(CONTINUED);
          valueString = stringBuilder.toString();
        }
      }
      return assignmentStyledString(name, valueString);
    } else {
      return name;
    }
  }

  /**
   * Searches for a keyword before the {@link EStructuralFeature} of the given {@link EObject} model element.
   *
   * @param modelElement
   *          the {@link EObject} model element
   * @param feature
   *          the {@link EStructuralFeature}
   * @return the keyword before the {@link EStructuralFeature} of the given {@link EObject} model element, or {@code null} if no keyword was found
   */
  protected String findKeywordBeforeFeature(final EObject modelElement, final EStructuralFeature feature) {
    List<INode> nodes = NodeModelUtils.findNodesForFeature(modelElement, feature);
    if (!nodes.isEmpty()) {
      INode node = nodes.get(0);
      while (!(node.getGrammarElement() instanceof Keyword) && node.hasPreviousSibling()) {
        node = node.getPreviousSibling();
      }
      if (node.getGrammarElement() instanceof Keyword) {
        return node.getText();
      }
    }
    return null;
  }

  /**
   * Determines a default name string for the given {@link EObject} model element.
   *
   * @param eObject
   *          the {@link EObject} model element
   * @return the name string for the given {@link EObject} model element, or {@code null} if no name was found
   */
  protected String getNameOfObject(final EObject eObject) {
    Object name = getFeature(eObject, FEATURE_NAME);
    if (name instanceof String) {
      return (String) name;
    }
    return null;
  }

  /**
   * Determines a default type string for the given {@link EObject} model element.
   *
   * @param eObject
   *          the {@link EObject} model element
   * @return the type string for the given {@link EObject} model element
   */
  protected String getTypeOfObject(final EObject eObject) {
    Object type = getFeature(eObject, FEATURE_TYPE);
    if (type == null) {
      return eObject.eClass().getName();
    }
    if (!(type instanceof EObject)) {
      return type.getClass().getSimpleName();
    }
    EObject eType = (EObject) type;
    if (eType.eIsProxy()) {
      return eType.eClass().getName();
    }
    // first check if type object is foreign to this grammar
    if (isForeignXtextObject(eType)) {
      return getForeignObjectLabel(eType);
    }
    // otherwise continue with name feature
    Object name = getFeature(eType, FEATURE_NAME);
    if (name == null) {
      return eType.eClass().getName();
    }
    return name.toString();
  }

  /**
   * The setting if features shall be labeled using an assignment.
   * By default, this is true.
   *
   * @return {@code} false by default
   */
  @SuppressWarnings("PMD.EmptyMethodInAbstractClassShouldBeAbstract")
  protected boolean settingCreateAssignment() {
    return true;
  }

  /**
   * Creates an assignment styled string using the given left and right hand sides.
   *
   * @param name
   *          the left part of the assignment
   * @param value
   *          the right part of the assignment
   * @return an assignment styled string using the given left and right hand side
   */
  protected Object assignmentStyledString(final String name, final String value) {
    String safeName = name != null ? name : Messages.AbstractAcfLabelProvider_NAME_FALLBACK;
    String safeValue = value != null ? value : Messages.AbstractAcfLabelProvider_NULL;
    StyledString styledString = new StyledString(safeName);
    styledString.append(FEATURE_SEPARATOR, StyledString.QUALIFIER_STYLER);
    styledString.append(safeValue, StyledString.QUALIFIER_STYLER);
    return styledString;
  }

  /**
   * Create a qualified styled label out of name and qualifier.
   *
   * @param name
   *          String name of Node
   * @param qualifier
   *          String qualifying description of Node
   * @param qualifierPrefix
   *          Prefix to prepend to qualifier
   * @param qualifierPostfix
   *          Postfix to append to qualifier
   * @return styled label of the form
   *         <name><qualifierPrefix><qualifier><qualifierPostfix>
   */
  @SuppressWarnings(value = "PMD.UseObjectForClearerAPI")
  protected StyledString qualifiedStyledString(final String name, final String qualifier, final String qualifierPrefix, final String qualifierPostfix) {
    String safeName = name != null ? name : Messages.AbstractAcfLabelProvider_NAME_FALLBACK;
    StyledString styledString = new StyledString(safeName);
    styledString.append(qualifierPrefix, StyledString.QUALIFIER_STYLER);
    styledString.append(qualifier, StyledString.QUALIFIER_STYLER);
    styledString.append(qualifierPostfix, StyledString.QUALIFIER_STYLER);
    return styledString;
  }

  /**
   * Create a qualified styled label out of name and qualifier.
   *
   * @param name
   *          String name of Node
   * @param qualifier
   *          String qualifying description of Node
   * @param qualifierSeparator
   *          Separator between <name> and <qualifier>
   * @return styled label of the form <name><qualifierSeparator><qualifier>
   */
  protected final StyledString qualifiedStyledString(final String name, final String qualifier, final String qualifierSeparator) {
    return qualifiedStyledString(name, qualifier, qualifierSeparator, ""); //$NON-NLS-1$
  }

  /**
   * Create a qualified styled label out of name and qualifier.
   *
   * @param name
   *          String name of Node
   * @param qualifier
   *          String qualifying description of Node
   * @return styled label of the form <name> " : " <qualifier>
   */
  protected final StyledString qualifiedStyledString(final String name, final String qualifier) {
    return qualifiedStyledString(name, qualifier, NAME_SEPARATOR);
  }

  /**
   * Returns a label's name.
   *
   * @param name
   *          Name to use if not null
   * @param fallbackName
   *          Name to use if "name" is null
   * @param toLowerCase
   *          True if the name is to be converted to lower case
   * @return The label's name
   */
  protected final String getLabelName(final String name, final String fallbackName, final boolean toLowerCase) {
    if (name == null) {
      return fallbackName;
    } else {
      return toLowerCase ? name.toLowerCase(Locale.getDefault()) : name;
    }
  }

  /**
   * Returns a label's annotated name.
   *
   * @param name
   *          Name to use if not null
   * @param annotation
   *          Annotation to append to the name (separated with a dash)
   * @param fallbackName
   *          Name to use if "name" is null
   * @return The label's annotated name
   */
  protected final String getAnnotatedLabelName(final String name, final String annotation, final String fallbackName) {
    String label = getLabelName(name, fallbackName, true);
    if (Strings.isEmpty(annotation)) {
      return label;
    }
    return new StringBuffer(label).append(ANNOTATION_SEPARATOR).append(annotation).toString();
  }

  /**
   * Checks if the given object is in a resource foreign to this language.
   *
   * @param object
   *          object to check
   * @return {@code true} if the given object is foreign
   */
  protected boolean isForeignXtextObject(final EObject object) {
    return object.eResource() instanceof XtextResource && !extensionProvider.getFileExtensions().contains(object.eResource().getURI().fileExtension());
  }

  /**
   * Get the label from the given eObject's {@link IResourceServiceProvider}.
   *
   * @param eObject
   *          the target object
   * @return the label for the given eObject
   */
  private String getForeignObjectLabel(final EObject eObject) {
    IResourceServiceProvider serviceProvider = ((XtextResource) eObject.eResource()).getResourceServiceProvider();
    ILabelProvider labelProvider = serviceProvider.get(ILabelProvider.class);
    if (labelProvider != null) {
      return labelProvider.getText(eObject);
    }
    return null;
  }

  /**
   * Get structural feature value with the given feature name.
   *
   * @param eObject
   *          the target object
   * @param featureName
   *          the name of the feature
   * @return the feature value
   */
  private Object getFeature(final EObject eObject, final String featureName) {
    EStructuralFeature feature = eObject.eClass().getEStructuralFeature(featureName);
    if (feature != null) {
      return eObject.eGet(feature);
    }
    return null;
  }

}
