/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.format.jvmmodel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.FormatFactory;
import com.avaloq.tools.ddk.xtext.test.format.util.FormatTestUtil;
import com.avaloq.tools.ddk.xtext.test.jupiter.AbstractXtextTest;


/**
 * Regression test for {@link FormatJvmModelInferrer#inferConstants(FormatConfiguration, JvmGenericType)}.
 *
 * <p>A {@link Constant} without an {@code intValue} or a {@code stringValue} makes
 * {@link FormatJvmModelInferrer#createConstant(FormatConfiguration, Constant)} return {@code null}. The inferrer must
 * skip such {@code null} members instead of leaking them into the JVM model (mirroring the original Xtend {@code +=}
 * null-skip), otherwise an {@code IllegalArgumentException: element: null} surfaces downstream.</p>
 */
@SuppressWarnings("nls")
public class FormatJvmModelInferrerTest extends AbstractXtextTest {

  private final FormatJvmModelInferrer inferrer = getXtextTestUtil().get(FormatJvmModelInferrer.class);

  @Override
  protected FormatTestUtil getXtextTestUtil() {
    return FormatTestUtil.getInstance();
  }

  @Override
  protected String getTestSourceFileName() {
    return null; // the model is fabricated in-memory, no source file is loaded
  }

  /**
   * A value-less constant must not leak a {@code null} member into the inferred type.
   */
  @Test
  public void testInferConstantsSkipsValuelessConstant() {
    final FormatConfiguration format = FormatFactory.eINSTANCE.createFormatConfiguration();
    final Constant valuelessConstant = FormatFactory.eINSTANCE.createConstant();
    valuelessConstant.setName("VALUELESS"); // name only, both intValue and stringValue stay null
    format.getConstants().add(valuelessConstant);

    final Resource resource = new ResourceImpl(URI.createURI("synthetic:/format/valueless.format"));
    resource.getContents().add(format);

    // createConstant returns null for a value-less constant.
    assertNull(inferrer.createConstant(format, valuelessConstant), "value-less constant should yield no JVM member");

    final JvmGenericType type = TypesFactory.eINSTANCE.createJvmGenericType();
    assertDoesNotThrow(() -> inferrer.inferConstants(format, type), "inferConstants must not fail on a value-less constant");
    assertFalse(type.getMembers().contains(null), "the inferred type must not contain a null member");
  }
}
