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

package com.avaloq.tools.ddk.xtext.modelinference;

import com.avaloq.tools.ddk.xtext.extension.ILanguageFeatureExtension;


/**
 * A model inferrer to run additional inference for a specific language outside of the basic inference offered in its core plugin.
 * <p>
 * Note that {@link IModelInferrer} is a common API for extension implementation (actual extension) and extensions service
 * (aggregation of all extensions which is used by a DSL to consume extensions).
 * </p>
 */
public interface IModelInferrerFeatureExtension extends IModelInferrer, Comparable<IModelInferrerFeatureExtension>, ILanguageFeatureExtension {

  /**
   * Compares this {@link IModelInferrerFeatureExtension} with the specified Compares this object with the specified object for order.
   * The name of the class implementing this interface is used for the comparison.
   * With this method implementations of {@link IModelInferrerFeatureExtension} can be always ordered alphabetically in a stable manner.
   * This is necessary to achieve a stable inference across build phases.
   **/
  @Override
  default int compareTo(final IModelInferrerFeatureExtension otherInferrer) {
    return this.getClass().getName().compareTo(otherInferrer.getClass().getName());
  }
}
