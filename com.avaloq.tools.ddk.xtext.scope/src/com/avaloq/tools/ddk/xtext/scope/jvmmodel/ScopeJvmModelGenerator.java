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
package com.avaloq.tools.ddk.xtext.scope.jvmmodel;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.xtext.JvmMemberInitializableResource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;

/** Rebuilds an incomplete editor model before generating Scope providers. */
public class ScopeJvmModelGenerator extends JvmModelGenerator {

  /**
   * Marks member inference that encountered an unresolved model signature.
   *
   * @param resource
   *          the resource whose provider members are incomplete
   */
  public static void markIncomplete(final Resource resource) {
    if (!isIncomplete(resource)) {
      resource.eAdapters().add(new IncompleteInference());
    }
  }

  /**
   * Clears a marker after all provider members have been inferred successfully.
   *
   * @param resource
   *          the resource whose provider members are complete
   */
  public static void clearIncomplete(final Resource resource) {
    resource.eAdapters().removeIf(IncompleteInference.class::isInstance);
  }

  private static boolean isIncomplete(final Resource resource) {
    return resource.eAdapters().stream().anyMatch(IncompleteInference.class::isInstance);
  }

  @Override
  public void doGenerate(final Resource input, final IFileSystemAccess fsa) {
    input.getContents();
    if (input instanceof JvmMemberInitializableResource initializable) {
      initializable.ensureJvmMembersInitialized();
    }
    if (isIncomplete(input) && input instanceof DerivedStateAwareResource resource) {
      clearIncomplete(resource);
      resource.discardDerivedState();
      resource.installDerivedState(false);
      if (resource instanceof JvmMemberInitializableResource initializable) {
        initializable.ensureJvmMembersInitialized();
      }
    }
    if (isIncomplete(input)) {
      throw new IllegalStateException("Cannot generate Scope providers with unresolved model signatures"); //$NON-NLS-1$
    }
    super.doGenerate(input, fsa);
  }

  /** Resource-local marker, never copied to another resource or persisted. */
  private static final class IncompleteInference extends AdapterImpl {
  }
}
