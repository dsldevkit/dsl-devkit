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
package com.avaloq.tools.ddk.xtext.builder.resourceloader;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.builder.resourceloader.AbstractResourceLoader;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.util.Triple;
import org.eclipse.xtext.util.Tuples;

import com.avaloq.tools.ddk.xtext.builder.tracing.LoaderDequeueEvent;
import com.avaloq.tools.ddk.xtext.builder.tracing.ResourceLoadEvent;
import com.avaloq.tools.ddk.xtext.tracing.ITraceSet;
import com.avaloq.tools.ddk.xtext.util.EmfResourceSetUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * TODO: Delete once https://bugs.eclipse.org/bugs/show_bug.cgi?id=360445 is solved.
 */
public class ParallelResourceLoader extends AbstractResourceLoader {

  private static final long MAX_WAIT_TIME = TimeUnit.SECONDS.toMillis(300);
  private static final long SLOW_LOADING_TIME = TimeUnit.SECONDS.toMillis(20);

  private static final Logger LOGGER = Logger.getLogger(ParallelResourceLoader.class);

  @Inject
  private ITraceSet traceSet;

  private final int nThreads;
  private final int queueSize;
  private long timeout;

  public ParallelResourceLoader(final IResourceSetProvider resourceSetProvider, final Sorter sorter, final int nThreads, final int queueSize) {
    super(resourceSetProvider, sorter);
    this.nThreads = nThreads;
    this.queueSize = queueSize;
    this.timeout = MAX_WAIT_TIME;
  }

  public long getTimeout() {
    return timeout;
  }

  /**
   * Sets the maximum timeout which should be waited for when calling {@link ParallelLoadOperation#next()}.
   *
   * @param time
   *          time
   * @param unit
   *          unit
   */
  public void setTimeout(final long time, final TimeUnit unit) {
    this.timeout = unit.toMillis(time);
  }

  public int getNThreads() {
    return nThreads;
  }

  /** {@inheritDoc} */
  @Override
  public LoadOperation create(final ResourceSet parent, final IProject project) {
    return new CheckedLoadOperation(new ParallelLoadOperation(parent, project));
  }

  /** {@inheritDoc} */
  @Override
  protected Resource loadResource(final URI uri, final ResourceSet localResourceSet, final ResourceSet parentResourceSet) {
    Resource resource = super.loadResource(uri, localResourceSet, parentResourceSet);
    if (localResourceSet.getResources().size() > 1) {
      for (Resource loadedResource : Lists.newArrayList(localResourceSet.getResources())) {
        if (!loadedResource.equals(resource)) {
          loadedResource.unload();
        }
      }
    }
    EmfResourceSetUtil.clearResourceSetWithoutNotifications(localResourceSet);
    return resource;
  }

  /**
   * Parallel load operation implementation.
   */
  protected class ParallelLoadOperation implements LoadOperation {

    private final BlockingQueue<Triple<URI, Resource, Throwable>> resourceQueue;
    private final Set<URI> currentlyProcessedUris = Collections.synchronizedSet(Sets.newHashSetWithExpectedSize(getNThreads() * 2));
    private final ThreadLocal<ResourceSet> resourceSetProvider;
    private final ExecutorService executor;
    private final ResourceSet parent;
    private final long waitTime;

    private int toProcess;
    private Collection<URI> workload;

    public ParallelLoadOperation(final ResourceSet parent, final IProject project) {
      this.parent = parent;
      if (queueSize == -1) {
        this.resourceQueue = new LinkedBlockingQueue<Triple<URI, Resource, Throwable>>();
      } else if (queueSize == 0) {
        this.resourceQueue = new SynchronousQueue<Triple<URI, Resource, Throwable>>();
      } else {
        this.resourceQueue = new ArrayBlockingQueue<Triple<URI, Resource, Throwable>>(queueSize);
      }
      this.resourceSetProvider = new ThreadLocal<ResourceSet>() {
        @Override
        protected ResourceSet initialValue() {
          ResourceSet resourceSet = getResourceSetProvider().get(project);
          resourceSet.getLoadOptions().putAll(parent.getLoadOptions());
          // we are not loading as part of a build
          resourceSet.getLoadOptions().remove(ResourceDescriptionsProvider.NAMED_BUILDER_SCOPE);
          resourceSet.setURIConverter(parent.getURIConverter());
          return resourceSet;
        }
      };
      this.executor = Executors.newFixedThreadPool(nThreads);
      this.waitTime = getTimeout();
    }

    public ExecutorService getExecutor() {
      return executor;
    }

    public Collection<URI> getWorkload() {
      return workload;
    }

    /**
     * Resource load job to load a single resource.
     */
    private class ResourceLoadJob implements Runnable {
      private final URI uri;

      ResourceLoadJob(final URI uri) {
        this.uri = uri;
      }

      /** {@inheritDoc} */
      @Override
      public void run() {
        loadResource(uri);
      }
    }

    /** {@inheritDoc} */
    @Override
    public LoadResult next() {
      try {
        traceSet.started(LoaderDequeueEvent.class);
        if (!hasNext()) {
          throw new NoSuchElementException("The resource queue is empty or the execution was cancelled."); //$NON-NLS-1$
        }
        Triple<URI, Resource, Throwable> result = null;
        try {
          result = resourceQueue.poll(waitTime, TimeUnit.MILLISECONDS);
          toProcess--;
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        if (result == null) {
          String currentUris = null;
          synchronized (currentlyProcessedUris) {
            currentUris = Joiner.on(", ").join(currentlyProcessedUris); //$NON-NLS-1$
          }
          throw new LoadOperationException(null, new TimeoutException("Resource load job didn't return a result after " + waitTime //$NON-NLS-1$
              + " ms. Resources being currently loaded: " + currentUris)); //$NON-NLS-1$
        }

        URI uri = result.getFirst();
        Throwable throwable = result.getThird();

        if (throwable != null) { // rethrow errors in the main thread
          if (throwable instanceof WrappedException) {
            throw new LoadOperationException(uri, ((WrappedException) throwable).exception());
          } else if (throwable instanceof Exception) {
            throw new LoadOperationException(uri, (Exception) throwable);
          } else {
            throw (Error) throwable;
          }
        }
        return new LoadResult(result.getSecond(), uri);
      } finally {
        traceSet.ended(LoaderDequeueEvent.class);
      }
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasNext() {
      return toProcess > 0;
    }

    /** {@inheritDoc} */
    @Override
    public void load(final Collection<URI> uris) {
      toProcess += uris.size();
      workload = getSorter().sort(uris);
      startThreads();
    }

    /**
     * Start the threads.
     */
    protected void startThreads() {
      for (final URI uri : workload) {
        executor.execute(new ResourceLoadJob(uri));
      }
      executor.shutdown();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<URI> cancel() {
      toProcess = 0;
      List<Runnable> jobs = executor.shutdownNow();
      List<URI> ret = Lists.newArrayList();
      for (Runnable job : jobs) {
        if (job instanceof ResourceLoadJob) {
          ret.add(((ResourceLoadJob) job).uri);
        }
      }
      return ret;
    }

    /**
     * Load the {@link Resource} with the given {@link URI} and publish the load result.
     *
     * @param uri
     *          the {@link URI} of the {@link Resource} to be loaded
     */
    protected void loadResource(final URI uri) {
      Throwable exception = null;
      Resource resource = null;
      currentlyProcessedUris.add(uri);

      try {
        resource = doLoadResource(uri);
        // CHECKSTYLE:OFF
      } catch (Throwable t) {
        // CHECKSTYLE:ON
        exception = t;
      }

      currentlyProcessedUris.remove(uri);
      publishLoadResult(Tuples.create(uri, resource, exception));
    }

    /**
     * Load the {@link Resource} with the given {@link URI}. If this is too slow, report a warning.
     *
     * @param uri
     *          the {@link URI} of the {@link Resource} to be loaded
     * @return the loaded resource
     */
    private Resource doLoadResource(final URI uri) {
      final long startElapsed = System.currentTimeMillis();
      try {
        traceSet.started(ResourceLoadEvent.class, uri);
        ResourceSet localResourceSet = resourceSetProvider.get();
        return ParallelResourceLoader.this.loadResource(uri, localResourceSet, parent);
      } finally {
        traceSet.ended(ResourceLoadEvent.class);
        final long elapsedTime = System.currentTimeMillis() - startElapsed;
        if (elapsedTime > SLOW_LOADING_TIME) {
          LOGGER.warn("Slow loading of source " + uri + ": " + elapsedTime + " ms"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
      }
    }

    /**
     * Publish the result of loading a {@link Resource}.
     *
     * @param result
     *          the result of resource loading
     */
    private void publishLoadResult(final Triple<URI, Resource, Throwable> result) {
      try {
        resourceQueue.put(result); // Block here if queue is full
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

}
