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
package com.avaloq.tools.ddk.typesystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.eclipse.emf.common.util.EList;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.typesystem.builtintypemodel.BuiltInTypeModel;
import com.avaloq.tools.ddk.typesystem.builtintypemodel.InternalType;
import com.avaloq.tools.ddk.typesystem.typemodel.INamedType;


/**
 * Tests that the BuiltInType model instance is correct and complete.
 */
@SuppressWarnings("nls")
public class BuiltInTypeModelAccessTest {

  @Test
  void testLoadModel() {
    BuiltInTypeModelAccess typeModelInstance = BuiltInTypeModelAccess.getInstance();
    BuiltInTypeModel model = typeModelInstance.getModel();
    assertNotNull(model, "Model was created");
    EList<InternalType> types = model.getInternalTypes();
    assertFalse(types.isEmpty(), "Model has built-in internal types");
    for (InternalType type : types) {
      String typeName = type.getName();
      assertFalse(typeName == null || "".equals(typeName), "Internal type has name");
    }
  }

  private void assertPresence(final String name) {
    INamedType t = BuiltInTypeModelAccess.getInstance().getInternalType(name);
    assertNotNull(t, "Type \"" + name + "\" found");
    assertEquals(name, t.getName(), "Actual type name matches");
  }

  @Test
  void testHasInternalTypes() {
    assertPresence(BuiltInTypeModelAccess.ANY_TYPE_NAME);
    assertPresence(BuiltInTypeModelAccess.ERROR_TYPE_NAME);
    assertPresence(BuiltInTypeModelAccess.UNDETERMINED_TYPE_NAME);
    assertPresence(BuiltInTypeModelAccess.VOID_TYPE_NAME);
  }

  @Test
  void testInvalidInternalTypeNames() {
    assertNull(BuiltInTypeModelAccess.getInstance().getInternalType(null), "Null name returns null");
    assertNull(BuiltInTypeModelAccess.getInstance().getInternalType(""), "Empty name returns null");
    assertNull(BuiltInTypeModelAccess.getInstance().getInternalType("xyz!!"), "Non-existant name returns null");
  }

  @Test
  void testUniqueInternalTypes() {
    assertUnique(BuiltInTypeModelAccess.ANY_TYPE_NAME);
    assertUnique(BuiltInTypeModelAccess.ERROR_TYPE_NAME);
    assertUnique(BuiltInTypeModelAccess.UNDETERMINED_TYPE_NAME);
    assertUnique(BuiltInTypeModelAccess.VOID_TYPE_NAME);
  }

  private void assertUnique(final String typeName) {
    BuiltInTypeModelAccess instance = BuiltInTypeModelAccess.getInstance();
    assertSame(instance.getInternalType(typeName), instance.getInternalType(typeName), "type " + typeName + " is unique");
  }
}
