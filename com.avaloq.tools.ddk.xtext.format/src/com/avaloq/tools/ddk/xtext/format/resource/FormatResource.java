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
package com.avaloq.tools.ddk.xtext.format.resource;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.resource.BatchLinkableResource;
import org.eclipse.xtext.xtext.ecoreInference.Xtext2EcoreTransformer;


/**
 * This subclass of {@link BatchLinkableResource} makes sure that when {@link #getContents()} is called by
 * {@link Xtext2EcoreTransformer#removeGeneratedPackages()} it won't end up installing this {@link FormatResource resource's} derived state. That would not work
 * correctly because the Xtext resource will not yet have been linked at this point.
 * <p>
 * The implementation is a hack of sorts, but this will have to serve until a cleaner solution can be found.
 */
@SuppressWarnings("restriction")
public class FormatResource extends BatchLinkableResource {

  private static final Logger LOGGER = Logger.getLogger(FormatResource.class);

  private static Method removeGeneratedPackagesMethod;

  static {
    try {
      removeGeneratedPackagesMethod = Xtext2EcoreTransformer.class.getMethod("removeGeneratedPackages");
    } catch (NoSuchMethodException | SecurityException e) {
      LOGGER.error("Failed to find method Xtext2EcoreTransformer.removeGeneratedPackages()", e);
    }
  }

  @Override
  public EList<EObject> getContents() {
    synchronized (getLock()) {
      if (!fullyInitialized && calledBy(removeGeneratedPackagesMethod)) {
        // bypass the derived state computation, as the required Xtext resource has not been linked yet
        return doGetContents();
      }
      return super.getContents();
    }
  }

  /**
   * Checks if the given method is on the current thread's {@link Thread#getStackTrace() call stack}.
   *
   * @param method
   *          method to check, can also be {@code null}
   * @return {@code true} if {@code method} is non-null and on the call stack
   */
  private boolean calledBy(final Method method) {
    if (method == null) {
      return false;
    }
    for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
      if (element.getMethodName().equals(method.getName()) && element.getClassName().equals(method.getDeclaringClass().getName())) {
        return true;
      }
    }
    return false;
  }
}

