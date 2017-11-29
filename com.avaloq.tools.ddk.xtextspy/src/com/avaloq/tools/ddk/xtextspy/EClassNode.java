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

import java.util.List;

import org.eclipse.emf.ecore.EClass;

import com.google.common.collect.Lists;


/**
 * Representation of an EClass in a tree.
 */
public class EClassNode {

  private final EClass eClass;
  private final EClassNode parentNode;
  private List<EClassNode> children;

  /**
   * Instantiates a new e class node.
   * 
   * @param eClass
   *          the e class
   * @param parent
   *          the parent
   */
  public EClassNode(final EClass eClass, final EClassNode parent) {
    this.eClass = eClass;
    this.parentNode = parent;
  }

  /**
   * Gets the e class.
   * 
   * @return the e class
   */
  public EClass getEClass() {
    return eClass;
  }

  /**
   * Gets the parent.
   * 
   * @return the parent
   */
  public EClassNode getParent() {
    return parentNode;
  }

  /**
   * Gets the children.
   * 
   * @return the children
   */
  public List<EClassNode> getChildren() {
    if (children == null) {
      initializeChildren();
    }
    return children;
  }

  /**
   * Checks for children.
   * 
   * @return true, if successful
   */
  public boolean hasChildren() {
    return !eClass.getESuperTypes().isEmpty();
  }

  /**
   * Initialize children.
   */
  private void initializeChildren() {
    children = Lists.newArrayList();
    for (EClass superEClass : eClass.getESuperTypes()) {
      children.add(new EClassNode(superEClass, this));
    }
  }
}

