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
import java.io.InputStream;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.diagnostics.ExceptionDiagnostic;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.serialization.SerializationUtil;
import org.eclipse.xtext.parser.IParser;
import org.eclipse.xtext.parser.ParseResult;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.IDerivedStateComputer;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.Triple;

import com.avaloq.tools.ddk.xtext.build.BuildPhases;
import com.avaloq.tools.ddk.xtext.modelcache.BinaryModelCacheManager.ModelStatus;
import com.avaloq.tools.ddk.xtext.modelcache.IModelCacheManager;
import com.avaloq.tools.ddk.xtext.modelcache.ModelCacheManagerFactory;
import com.avaloq.tools.ddk.xtext.modelcache.ResourceModelType;
import com.avaloq.tools.ddk.xtext.parser.IResourceAwareParser;
import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.tracing.ResourceInferenceEvent;
import com.avaloq.tools.ddk.xtext.util.RecordUnresolvableXrefs;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;


/**
 * The class overrides {@link org.eclipse.xtext.linking.lazy.LazyLinkingResource#createAndAddDiagnostic(org.eclipse.xtext.util.Triple)} so that diagnostics may
 * be intercepted by {@link IDiagnosticFilter}.
 * It also overrides getEObject in order to mark unresolvable cross-references.
 */
// Node Model serialization is a restricted API of Xtext. Until Xtext provides a proper serialization API, we need to access its internal components.
public class LazyLinkingResource2 extends DerivedStateAwareResource implements ILazyLinkingResource2 {

  /** Class-wide logger. */
  private static final Logger LOGGER = Logger.getLogger(LazyLinkingResource2.class);

  @Inject
  private ITraceSet traceSet;

  /**
   * Global key to store a flag in the resource scope cache whether lazy linking has been done.
   */
  private static final String LAZY_CROSS_REFERENCES_RESOLVED = ILazyLinkingResource2.class.getName() + ".LAZY_CROSS_REFERENCES_RESOLVED"; //$NON-NLS-1$

  /** Filter for the creation of linking diagnostics (unresolved references, ...). */
  @Inject
  private IDiagnosticFilter diagnosticFilter;

  @Inject
  private Injector injector;

  @Inject
  private ModelCacheManagerFactory modelCacheManagerFactory;

  private IModelCacheManager modelManager;

  /** {@inheritDoc} */
  @Override
  protected void doLoad(final InputStream inputStream, final Map<?, ?> options) throws IOException {
    getModelManager().setAllModelsToStatus(ModelStatus.UNLOADED);
    if (!getModelManager().loadBinaryModels(ResourceModelType.EMF)) {
      super.doLoad(inputStream, options);
      getModelManager().setAllModelsToStatus(ModelStatus.LOADED);
      getModelManager().saveBinaryModels();
    }
  }

  /**
   * Sets the parse result for this resource.
   *
   * @param parseResult
   *          the parse result containing the EMF and Node model, must not be {@code null}
   * @param refreshSyntaxErrors
   *          whether diagnostics should be created for syntax errors
   */
  @Override
  public void setParseResult(final ParseResult parseResult, final boolean refreshSyntaxErrors) {
    setParseResult(parseResult);
    if (refreshSyntaxErrors) {
      addSyntaxErrors();
    }
  }

  /**
   * Fetches the source text for this resource.
   *
   * @return a copy of the source text, never {@code null}
   * @throws IOException
   *           when loading the source text fails
   */
  @Override
  public String getSourceText() throws IOException {
    return SerializationUtil.getCompleteContent(this);
  }

  /**
   * Sets whether this resource is currently being loaded.
   * <p>
   * Enabling this prevents model inference from ocurring when calling {@link #getContents()}.
   * </p>
   *
   * @param loading
   *          whether the resource is being loaded
   */
  @Override
  public void setLoading(final boolean loading) {
    isLoading = loading;
  }

  /**
   * Gets the model manager for this resource or creates one if it doesn't exist.
   *
   * @return the model manager, never {@code null}
   */
  @Override
  public IModelCacheManager getModelManager() {
    if (modelManager == null) {
      modelManager = modelCacheManagerFactory.createModelCacheManager(this);
    }
    return modelManager;
  }

  /** {@inheritDoc} */
  @Override
  public synchronized EObject getEObject(final String uriFragment) {
    try {
      final EObject result = super.getEObject(uriFragment);
      if (result == null && getEncoder().isCrossLinkFragment(this, uriFragment)) {
        RecordUnresolvableXrefs.recordUnresolvableXref(getResourceSet());
      }
      return result;
    } catch (FastLazyURIEncoder.DecodingError err) {
      RuntimeException cause = err.getCause();
      getErrors().add(new ExceptionDiagnostic(cause));
      throw new WrappedException(cause);
    } catch (WrappedException e) {
      boolean logged = false;
      try {
        if (getEncoder().isCrossLinkFragment(this, uriFragment)) {
          Triple<EObject, EReference, INode> triple = getEncoder().decode(this, uriFragment);
          INode node = triple.getThird();
          final String nodeName = getLinkingHelper().getCrossRefNodeAsString(node, true);
          LOGGER.error("Resolution of uriFragment '" + uriFragment + "' in the resource '" + this.getURI() + "' node_name " + nodeName //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
              + " line " + node.getStartLine() + " offset " + node.getOffset() + " failed.", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
          logged = true;
        }
        // CHECKSTYLE:OFF
      } catch (RuntimeException e1) {
        // CHECKSTYLE:ON
        // ignore
      }
      if (!logged) {
        LOGGER.error("Resolution of uriFragment '" + uriFragment + "' in the resource '" + this.getURI() + "' failed.", e); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      }
      throw e;
    }
  }

  /**
   * Returns an instance from the {@link #injector Guice injector} for the given key (class).
   *
   * @param <T>
   *          type of object to return
   * @param key
   *          class to look up service with
   * @return Guice injected service matching given key
   */
  @Override
  public <T> T getService(final Class<? extends T> key) {
    return injector.getInstance(key);
  }

  /** Simple flag class for use in an IResourceScopeCache. */
  private static class LazyResolutionFlag {
    private boolean done;

    LazyResolutionFlag() {
      done = false;
    }

    public boolean isDone() {
      return done;
    }

    /** Set the flag to true, indicating that lazy resolution was completely done. */
    public void setDone() {
      done = true;
    }
  }

  @Override
  public void resolveLazyCrossReferences(final CancelIndicator mon) {
    // We must set that flag only if the xtext proxy resolution was not canceled. If it was, we must re-do the proxy resolution (the
    // super implementation takes care to only resolve xtext proxies, so this will not fully resolve even if run several times). If
    // we unconditionally set the flag, a canceled linking run may leave behind xtext proxies, and because the flag will be set,
    // subsequent invocations will be no-ops. As a result, we'll have xtext proxies during validation.
    //
    // Furthermore, it's probably nonsense to have two threads execute this simultaneously on the same resource, so we'll serialize
    // this.
    //
    // Unfortunately, we have no set() on IResourceScopeCache. We'll have to get that ourselves using an indirection...
    final LazyResolutionFlag lazyLinking = getCache().get(LAZY_CROSS_REFERENCES_RESOLVED, this, new Provider<LazyResolutionFlag>() {
      @Override
      public LazyResolutionFlag get() {
        return new LazyResolutionFlag(); // Initially false
      }
    });
    synchronized (lazyLinking) {
      if (!lazyLinking.isDone()) {
        super.resolveLazyCrossReferences(mon);
        if (mon == null || !mon.isCanceled()) {
          lazyLinking.setDone();
          // And make sure that the flag is in the cache!!
          getCache().get(LAZY_CROSS_REFERENCES_RESOLVED, this, new Provider<LazyResolutionFlag>() {
            @Override
            public LazyResolutionFlag get() {
              return lazyLinking;
            }
          });
        }
      }
    }
  }

  @Override
  public void createAndAddDiagnostic(final Triple<EObject, EReference, INode> triple) {
    if (isValidationDisabled()) {
      return;
    }
    if (diagnosticFilter.suppressDiagnostic(triple)) {
      return;
    } else {
      super.createAndAddDiagnostic(triple);
      if (LOGGER.isDebugEnabled()) {
        // call to super does not return the new diagnostic so create a temporary one
        Diagnostic error = createDiagnostic(triple, createDiagnosticMessage(triple));
        LOGGER.debug(getURI() + " error: " + error); //$NON-NLS-1$
      }
    }
  }

  /**
   * Default implementation of {@link IDiagnosticFilter} which does not suppress any diagnostic.
   */
  public static class DefaultDiagnosticFilter implements IDiagnosticFilter {

    @Inject
    private ICrossReferenceHelper crossReferenceHelper;

    /**
     * {@inheritDoc}
     *
     * @return true if the {@link ICrossReferenceHelper} returns true
     */
    @Override
    public boolean suppressDiagnostic(final Triple<EObject, EReference, INode> triple) {
      final EObject context = triple.getFirst();
      final EReference reference = triple.getSecond();
      final INode node = triple.getThird();
      return crossReferenceHelper.isOptionalReference(context, reference, node);
    }
  }

  @Override
  protected EObject handleCyclicResolution(final Triple<EObject, EReference, INode> triple) {
    try {
      return super.handleCyclicResolution(triple);
    } catch (AssertionError ae) {
      throw new IllegalStateException(ae.getMessage() + " in resource " + getURI(), ae); //$NON-NLS-1$
    }
  }

  @Inject(optional = true)
  private IDerivedStateComputer derivedStateComputer;

  @Override
  public void discardDerivedState() {
    if (derivedStateComputer != null && fullyInitialized && !isInitializing) {
      try {
        isInitializing = true;
        derivedStateComputer.discardDerivedState(this);
        fullyInitialized = false;
      } finally {
        isInitializing = false;
        getCache().clear(this); // Why?
      }
    }
  }

  @Override
  public void installDerivedState(final boolean isPrelinkingPhase) {
    if (derivedStateComputer != null && !fullyInitialized && !isInitializing && !isLoadedFromStorage()) {
      try {
        traceSet.started(ResourceInferenceEvent.class, getURI());
        isInitializing = true;
        derivedStateComputer.installDerivedState(this, isPrelinkingPhase);
        fullyInitialized = true;
      } finally {
        isInitializing = false;
        getCache().clear(this); // Why?
        traceSet.ended(ResourceInferenceEvent.class);
      }
    }
  }

  @Override
  public synchronized EList<EObject> getContents() {
    // CHECKSTYLE:OFF method copied from Xtext to adjust the value for 'installDerivedState' method's argument
    if (isLoaded && !isLoading && !isInitializing && !isUpdating && !fullyInitialized && !isLoadedFromStorage()) {
      // CHECKSTYLE:ON
      try {
        eSetDeliver(false);
        installDerivedState(BuildPhases.isIndexing(this));
      } finally {
        eSetDeliver(true);
      }
    }
    return doGetContents();
  }

  @Override
  public boolean isInitialized() {
    return fullyInitialized || isInitializing || isLoadedFromStorage();
  }

  @Override
  @SuppressWarnings("UselessOverridingMethod")
  // Well, it's not: need to make the method public.
  // Reason: DerivedStateAwareResource.getContents triggers model inference for the linking phase, even when we're actually in the export phase.
  // Thus, during the export phase, we have to be able to get to the contents of a resource without triggering model inference.
  public EList<EObject> doGetContents() {
    return super.doGetContents();
  }

  /** {@inheritDoc} */
  @Override
  public void setURI(final URI uri) {
    initializeParser(getParser(), uri);
    super.setURI(uri);
  }

  /** {@inheritDoc} */
  @Override
  public void setParser(final IParser parser) {
    initializeParser(parser, getURI());
    super.setParser(parser);
  }

  /**
   * Initializes the parser with resource related parameters.
   *
   * @param parser
   *          the {@link IParser} to initialize, may be {@code null}
   * @param resourceUri
   *          the resource {@link URI} that the parser should consider, may be {@code null}
   */
  private void initializeParser(final IParser parser, final URI resourceUri) {
    if (parser instanceof IResourceAwareParser) {
      final IResourceAwareParser resourceParser = (IResourceAwareParser) parser;
      if (resourceUri == null) {
        resourceParser.setFileExtension(null);
      } else {
        resourceParser.setFileExtension(URI.decode(resourceUri.fileExtension())); // if uri.fileExtension() is null, URI.decode will return null
      }
    }
  }
}
