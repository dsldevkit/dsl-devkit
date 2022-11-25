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
package com.avaloq.tools.ddk.xtext.ui.validation;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.runtime.IConfigurationElement;


/**
 * This class serves as the implementation base for the element classes of the
 * plug-in extension point <code>ch.paranor.au.xtext.valid.core.valid</code>.
 * 
 * @see ValidExtensionPointManager
 */
public abstract class AbstractValidElementBase {

  protected static final String MESSAGE = "message"; //$NON-NLS-1$
  protected static final String SEVERITY = "severity"; //$NON-NLS-1$
  protected static final String OPTIONAL = "optional"; //$NON-NLS-1$
  protected static final String DESCRIPTION = "description"; //$NON-NLS-1$
  protected static final String LABEL = "label"; //$NON-NLS-1$
  protected static final String NAME = "name"; //$NON-NLS-1$
  protected static final String EVALUATION_MODE = "evaluationMode"; //$NON-NLS-1$  
  protected static final String RULE = "rule"; //$NON-NLS-1$
  protected static final String CATEGORY = "category"; //$NON-NLS-1$
  protected static final String LANGUAGE = "language"; //$NON-NLS-1$

  private static final String MISSING_ATTRIBUTE_0 = "Missing attribute '{0}'"; //$NON-NLS-1$

  /** Logging. */
  protected static final Logger LOGGER = LogManager.getLogger(AbstractValidElementBase.class);

  private final IConfigurationElement configurationElement;

  protected AbstractValidElementBase(final IConfigurationElement configurationElement) {
    this.configurationElement = configurationElement;
  }

  /**
   * Return all child elements of this element that conform to the hierarchy of the
   * XML schema that goes with this extension point. The order the returned elements
   * is not specified here.
   * 
   * @return the child elements of this element
   */
  public AbstractValidElementBase[] getChildElements() {
    AbstractValidElementBase[] childElements = null;
    if (childElements == null) {
      IConfigurationElement[] ce = getConfigurationElement().getChildren();
      ArrayList<AbstractValidElementBase> elements = new ArrayList<AbstractValidElementBase>();
      for (IConfigurationElement element : ce) {
        AbstractValidElementBase e = createChildElement(element);
        if (e != null) {
          elements.add(e);
        }
      }
      childElements = elements.toArray(new AbstractValidElementBase[elements.size()]);
    }
    return childElements;
  }

  /**
   * @return the id that tells one extension instance from another
   */
  public String getExtensionId() {
    return configurationElement.getDeclaringExtension().getSimpleIdentifier();
  }

  /**
   * @return the name of the XML element that defines this class
   */
  public String getElementTypeName() {
    return configurationElement.getName();
  }

  /**
   * Returns the {@link IConfigurationElement} for the XML element this object
   * represents. All attributes of this element are provided
   * via getters of this class. Child elements of this
   * element can be obtained via {@link #getChildElements}.
   * 
   * @return never null
   */
  public IConfigurationElement getConfigurationElement() {
    return configurationElement;
  }

  /**
   * Creates a child element inside the container (configuration element).
   * 
   * @param container
   *          the parent of the newly created element
   * @return the created element
   */
  protected abstract AbstractValidElementBase createChildElement(IConfigurationElement container);

  @Override
  public String toString() {
    StringBuilder b = new StringBuilder(this.getClass().getSimpleName());
    b.append("(\""); //$NON-NLS-1$
    b.append(getElementTypeName());
    b.append("\")"); //$NON-NLS-1$
    return b.toString();
  }

  /**
   * Log a problem.
   * 
   * @param ex
   *          exception manifesting the problem
   * @param msg
   *          custom message
   */
  protected static void logException(final Exception ex, final String msg) {
    LOGGER.error(msg, ex);
  }

  /**
   * Reads the named attribute value from the configuration element. Throws IllegalArgumentException
   * if the name of the attribute is not known
   * 
   * @param configurationElement
   *          the container (configuration element)
   * @param name
   *          name of the attribute
   * @param optional
   *          if true, will return null if not found, otherwise IllegalArgumentException is raised
   * @return the value of the attribute of null if the attribute is optional and not supplied
   */
  protected static String getAttribute(final IConfigurationElement configurationElement, final String name, final boolean optional) {
    String value = configurationElement.getAttribute(name);
    if (value != null) {
      return value;
    }
    if (optional) {
      return null;
    }
    throw new IllegalArgumentException(MessageFormat.format(MISSING_ATTRIBUTE_0, name));
  }
}
