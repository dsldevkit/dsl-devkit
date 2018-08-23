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
package com.avaloq.tools.ddk.xtext.linking;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.util.IResourceScopeCache;
import org.eclipse.xtext.util.Triple;

import com.avaloq.tools.ddk.xtext.modelcache.IModelCacheManager;


/**
 * Common interface of LazyLinkingResource with custom implementations.
 */
public interface ILazyLinkingResource2 extends Resource, Resource.Internal {

  /**
   * Use as the value for a {@link com.google.inject.name.Named} annotation on a {@link Boolean} value bound in the language injector to explicitly enable or
   * disable parallel loading. By default parallel loading is enabled.
   */
  String PARALLEL_LOADING_SUPPORT = ILazyLinkingResource2.class.getName() + ".PARALLEL_LOADING_SUPPORT"; //$NON-NLS-1$

  /**
   * Gets the model manager for this resource or creates one if it does not exist.
   *
   * @return the model manager, never {@code null}
   */
  IModelCacheManager getModelManager();

  /**
   * Creates diagnostics for given triple.
   *
   * @param triple
   *          triple <EObject, EReference, INode>, must not be {@code null}
   */
  void createAndAddDiagnostic(final Triple<EObject, EReference, INode> triple);

  /**
   * Fetches the source text for this resource.
   *
   * @return a copy of the source text, never {@code null}
   * @throws IOException
   *           when loading the source text fails
   */
  String getSourceText() throws IOException;

  /**
   * Sets whether this resource is currently being loaded.
   * <p>
   * Enabling this prevents model inference from ocurring when calling {@link #getContents()}.
   * </p>
   *
   * @param loading
   *          whether the resource is being loaded
   */
  void setLoading(final boolean loading);

  /**
   * Sets the parse result for this resource.
   *
   * @param parseResult
   *          the parse result containing the EMF and Node model, must not be {@code null}
   * @param refreshSyntaxErrors
   *          whether diagnostics should be created for syntax errors
   */
  void setParseResult(final ParseResult parseResult, final boolean refreshSyntaxErrors);

  /**
   * Sets the parse result for this resource.
   *
   * @param parseResult
   *          the parse result containing the EMF and Node model, must not be {@code null}
   */
  void setParseResult(IParseResult parseResult);

  /**
   * Checks whether this resource is fully initialized.
   *
   * @return true if this resource is initializing or fully initialized
   */
  boolean isInitialized();

  /**
   * Returns the scope cache for this resource.
   *
   * @return The IResourceScopeCache object, never {@code null}
   */
  IResourceScopeCache getCache();

  /**
   * Installs this derived state.
   *
   * @param isPrelinkingPhase
   *          indicates that this resource is in prelinking phase
   */
  void installDerivedState(final boolean isPrelinkingPhase);

  /**
   * Returns an instance from the {@link #injector Guice injector} for the given key (class).
   *
   * @param <T>
   *          type of object to return
   * @param key
   *          class to look up service with
   * @return Guice injected service matching given key
   */
  <T> T getService(final Class<? extends T> key);

  /**
   * Gets the parse result containing the EMF and Node model.
   *
   * @return the parse result containing the EMF and Node model, must not be {@code null}
   */
  IParseResult getParseResult();
}
