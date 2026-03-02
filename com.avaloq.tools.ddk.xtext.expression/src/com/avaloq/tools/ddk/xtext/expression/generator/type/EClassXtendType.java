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
package com.avaloq.tools.ddk.xtext.expression.generator.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;


/**
 * Local replacement for {@code org.eclipse.xtend.typesystem.emf.EClassType}.
 * A type backed by an EMF {@link EClass}.
 */
public class EClassXtendType implements XtendType {

  private final EClass eClass;

  /**
   * Creates a new type backed by the given EClass.
   *
   * @param eClass
   *          the EClass
   */
  public EClassXtendType(final EClass eClass) {
    this.eClass = eClass;
  }

  /**
   * Returns the underlying {@link EClass}.
   *
   * @return the EClass
   */
  public EClass getEClass() {
    return eClass;
  }

  @Override
  public String getName() {
    return eClass.getEPackage().getName() + "::" + eClass.getName(); //$NON-NLS-1$
  }

  @Override
  public Object newInstance() {
    if (eClass.isAbstract() || eClass.isInterface()) {
      return new Object();
    }
    return eClass.getEPackage().getEFactoryInstance().create(eClass);
  }

  @Override
  public List<XtendOperation> getAllOperations() {
    List<XtendOperation> result = new ArrayList<>();
    for (EOperation op : eClass.getEAllOperations()) {
      result.add(new EOperationXtendOperation(op));
    }
    return result;
  }

  @Override
  public boolean isAssignableFrom(final XtendType other) {
    if (other instanceof EClassXtendType) {
      return eClass.isSuperTypeOf(((EClassXtendType) other).getEClass());
    }
    return false;
  }

  /**
   * Wraps an {@link EOperation} as an {@link XtendOperation}.
   */
  private static class EOperationXtendOperation implements XtendOperation {
    private final EOperation operation;

    /**
     * Creates a new wrapper for the given EOperation.
     *
     * @param operation
     *          the EOperation
     */
    EOperationXtendOperation(final EOperation operation) {
      this.operation = operation;
    }

    @Override
    public String getName() {
      return operation.getName();
    }

    @Override
    public List<? extends XtendType> getParameterTypes() {
      return Collections.emptyList();
    }
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof EClassXtendType) {
      return eClass.equals(((EClassXtendType) obj).eClass);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return eClass.hashCode();
  }
}
