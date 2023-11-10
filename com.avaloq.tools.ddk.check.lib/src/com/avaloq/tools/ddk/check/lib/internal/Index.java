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
package com.avaloq.tools.ddk.check.lib.internal;

import java.util.Locale;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.avaloq.tools.ddk.check.lib.IIndex;
import com.avaloq.tools.ddk.xtext.scoping.ContainerQuery;
import com.avaloq.tools.ddk.xtext.scoping.IDomain;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * A concrete implementation of IIndex.
 */
@Singleton
public final class Index implements IIndex {

  @Inject
  private IDomain.Mapper domainMapper;

  @Inject
  private IQualifiedNameConverter nameConverter;

  protected Index() {
    // Prevent explicit instantiations. To be used via injection.
  }

  
  @Override
  public IIndex.Query newQuery(final EClass type, final String namePattern) {
    if (type == null || namePattern == null || namePattern.length() == 0) {
      throw new IllegalArgumentException(Messages.Index_NullArgumentInQuery);
    }
    return new Query(domainMapper, nameConverter, type).withName(namePattern);
  }

  
  @Override
  public IIndex.Query newQuery(final EClass type, final QualifiedName namePattern) {
    if (type == null || namePattern == null || namePattern.isEmpty()) {
      throw new IllegalArgumentException(Messages.Index_NullArgumentInQuery);
    }
    return new Query(domainMapper, nameConverter, type).withName(namePattern);
  }

  /**
   * A query to run against the model cache. Queries retrieve objects based on their type and name, and possibly based on additional index data.
   */
  private static final class Query implements IIndex.Query {

    private final ContainerQuery.Builder realQuery;
    private final IQualifiedNameConverter nameConverter;

    /**
     * Creates a new query for a type, using a given domain mapper and name converter.
     *
     * @param mapper
     *          to use
     * @param nameConverter
     *          to use
     * @param type
     *          to look for
     * @return a query object
     */
    protected Query(final IDomain.Mapper mapper, final IQualifiedNameConverter nameConverter, final EClass type) {
      this.realQuery = ContainerQuery.newBuilder(mapper, type);
      this.nameConverter = nameConverter;
    }

    /**
     * Restricts the query to match only index entries with a given name pattern.
     * <p>
     * <em>Note:</em> throws {@link IllegalArgumentException} if the name pattern has a wildcard, but not at the end.
     * </p>
     *
     * @param namePattern
     *          to look for, may end in a wildcard "*".
     * @return the query.
     */
    private Query withName(final String namePattern) {
      realQuery.name(namePattern);
      return this;
    }

    /**
     * Restricts the query to match only index entries with a given name pattern.
     *
     * @param namePattern
     *          to look for.
     * @return the query.
     */
    private Query withName(final QualifiedName namePattern) {
      realQuery.name(namePattern);
      return this;
    }

    
    @Override
    public Query withData(final String key, final String value) {
      if (key == null || value == null) {
        throw new IllegalArgumentException(Messages.Index_NullQueryData);
      }
      realQuery.data(key, value);
      return this;
    }

    
    @Override
    public Iterable<IIndex.Entry> run(final EObject context) {
      if (context == null) {
        throw new IllegalArgumentException(Messages.Index_NullQueryContext);
      }
      return Iterables.transform(realQuery.execute(context), new Function<IEObjectDescription, IIndex.Entry>() {
        @Override
        public IIndex.Entry apply(final IEObjectDescription desc) {
          return new Entry(context, nameConverter, desc);
        }
      });
    }
  }

  /**
   * A class describing an index entry; i.e., a result object of an index {@link Query}.
   */
  private static final class Entry implements IIndex.Entry {

    private final IQualifiedNameConverter nameConverter;
    private final EObject context;
    private final IEObjectDescription delegate;

    /**
     * Creates a new wrapper for an IEObjectDescription, using the given name converter and query context object. The context object is used to resolve the
     * EObject, if needed.
     *
     * @param context
     *          of the query that produced this result
     * @param nameConverter
     *          to use
     * @param internalDescription
     *          to wrap
     */
    protected Entry(final EObject context, final IQualifiedNameConverter nameConverter, final IEObjectDescription internalDescription) {
      this.context = context;
      this.nameConverter = nameConverter;
      this.delegate = internalDescription;
    }

    
    @Override
    public String getName() {
      return nameConverter.toString(getQualifiedName());
    }

    
    @Override
    public QualifiedName getQualifiedName() {
      return delegate.getQualifiedName();
    }

    
    @Override
    public EClass getType() {
      return delegate.getEClass();
    }

    
    @Override
    public EObject getModelObject() {
      EObject result = delegate.getEObjectOrProxy();
      if (result != null && result.eIsProxy()) {
        result = EcoreUtil.resolve(result, context);
      }
      return result;
    }

    
    @Override
    public String[] getDataKeys() {
      String[] result = delegate.getUserDataKeys();
      if (result == null) {
        result = new String[0];
      }
      return result;
    }

    
    @Override
    public String getData(final String key) {
      if (key == null) {
        throw new IllegalArgumentException(Messages.Index_NullKeyInEntry);
      }
      return delegate.getUserData(key);
    }

    
    @Override
    public String getSourceName() {
      final URI uri = delegate.getEObjectURI().trimFragment();
      final String name = uri.lastSegment();
      if (name != null) {
        final String extension = uri.fileExtension();
        final String result = URI.decode(extension != null ? name + '.' + extension : name);
        return result.toUpperCase(Locale.getDefault());
      }
      return null;
    }

  }
}
