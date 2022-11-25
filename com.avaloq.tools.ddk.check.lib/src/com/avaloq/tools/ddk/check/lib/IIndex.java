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
package com.avaloq.tools.ddk.check.lib;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;

import com.avaloq.tools.ddk.check.lib.internal.Index;
import com.google.inject.ImplementedBy;


/**
 * A representation of the model cache's index. It allows clients to execute simple queries ({@link Query}) against the model cache.
 * Results of queries are reported back as {@link Entry} objects.
 * <p>
 * An index {@link Entry} is not a model object (i.e., {@link EObject}), but a <em>description</em> of a model object. Each such description contains
 * </p>
 * <ul>
 * <li>The name under which the model object appears in the index. This can be obtained through {@code getName()} or {@code getQualifiedName()}.</li>
 * <li>An {@link EClass} specifying the type of the object.</li>
 * <li>Additional data that is stored in the index for the object. It can be obtained using e.g. {@code getData("data_key")}.</li>
 * </ul>
 * <p>
 * The actual model object described can be obtained through {@code getModelObject()}. This will load the resource containing the model object described by the
 * index {@link Entry} into memory.
 * </p>
 * <p>
 * Note that you normally do not need to cache query results; they are cached internally.
 * </p>
 * <p>
 * <b>Usage:</b>
 * </p>
 * <p>
 * <code>import com.avaloq.tools.ddk.check.lib.IIndex</code><br />
 * <code>@Inject IIndex index;</code>
 * </p>
 * <p>
 * Hint: {@link Query} and {@link Entry} are <em>nested interfaces</em>. To reference them in a check catalog, write "IIndex$Query" and "IIndex$Entry",
 * respectively.
 * </p>
 */
@ImplementedBy(Index.class)
public interface IIndex {

  /**
   * Creates a new query on the index, looking for index entries for objects of the given type and the given name (pattern).
   * <p>
   * <em>Note:</em> throws {@link IllegalArgumentException} if the type is {@code null} or the namePattern is {@code null}, empty, or has a wildcard, but not at
   * the end.
   * </p>
   *
   * @param type
   *          of model objects to look for; must not be {@code null}.
   * @param namePattern
   *          of model objects to look for; must not be {@code null}. May end in the wildcard "*", meaning "any number of
   *          arbitrary characters". The wildcard, if any, must appear only at the end.
   * @return the {@link Query} object to run the query.
   */
  Query newQuery(final EClass type, final String namePattern);

  /**
   * Creates a new query on the index, looking for index entries for objects of the given type and the given name (pattern).
   * <p>
   * <em>Note:</em> throws {@link IllegalArgumentException} if the type is {@code null} or the namePattern is {@code null} or empty.
   * </p>
   *
   * @param type
   *          of model objects to look for; must not be {@code null}.
   * @param namePattern
   *          of model objects to look for; must not be {@code null}.
   * @return the {@link Query} object to run the query.
   */
  Query newQuery(final EClass type, final QualifiedName namePattern);

  /**
   * A query to run against the model cache. Queries retrieve objects based on their type and name, and possibly based on additional index data.
   */
  interface Query {

    /**
     * Restricts the query to return only index entries for which the index contains the given key-value pair in its additional data.
     * For some objects, the index stores such additional data. For instance for CODE TABLE rows, it stores under the keys "intl_id"
     * and "user_id" the intl_id and user_id of the row described. This can be used to look for index entries by such data. To
     * obtain the index entry for a code table row with some given intl_id in some (possibly partitioned) code table CODE_FOO, you
     * might use the code
     * <p>
     * <code>@Inject IIndex index;</code><br />
     * <code>index.newQuery(com.avaloq.tools.dsl.codetabdata.codetabdata.CodetabdataPackage$Literals.ROW, "code_foo.*").withData("intl_id", idToLookFor).run(context);</code>
     * </p>
     * <p>
     * <em>Note:</em> throws {@link IllegalArgumentException} if either {@code key} of {@code value} are {@code null}.
     * </p>
     *
     * @param key
     *          of the data to look for; must not be {@code null}.
     * @param value
     *          to look for; must not be {@code null}.
     * @return this object
     */
    Query withData(final String key, final String value);

    /**
     * Runs the query against the index and returns the index entries found.
     * <p>
     * <em>Note:</em> throws {@link IllegalArgumentException} if {@code context} is {@code null}.
     * </p>
     *
     * @param context
     *          object defining the context for the search; must not be {@code null}. Because visibility rules may
     *          differ depending on which resource one starts searching for other objects, every query execution
     *          needs a context object. In a check, you may use the check context object.
     * @return An {@link Iterable} of all index entries found. Never returns {@code null}, but may return an empty result.
     */
    Iterable<Entry> run(final EObject context);

  } // end Query

  /**
   * A result object of an index {@link Query}.
   */
  interface Entry {

    /**
     * Gets the fully qualified name of the entry as stored in the index.
     *
     * @return the name; never {@code null}.
     */
    String getName();

    /**
     * Gets the fully qualified name of the entry as stored in the index.
     *
     * @return the name; never {@code null}.
     */
    QualifiedName getQualifiedName();

    /**
     * Gets the type ({@link EClass}) of the entry as stored in the index.
     *
     * @return the type; never {@code null}.
     */
    EClass getType();

    /**
     * Gets the real model object described by this index entry. This will load the resource containing that object into memory. Use with care
     * lest you use up the available memory, which will slow down the builder and in the worst case lead to an OutOfMemoryException.
     *
     * @return the model object; never {@code null}.
     */
    EObject getModelObject();

    /**
     * Gets all the keys for which this index entry contains additional data.
     *
     * @return the keys. Never returns {@code null} but may return an empty array.
     */
    String[] getDataKeys();

    /**
     * Given a key, returns the data associated with it as stored in the index entry.
     * <p>
     * <em>Note:</em> throws {@link IllegalArgumentException} if {@code key} is {@code null}.
     * </p>
     *
     * @param key
     *          to get the data for; must not be {@code null}.
     * @return the value stored, or {@code null} if none.
     */
    String getData(final String key);

    /**
     * Gets the name of the source the object described is in, in the form "SOURCE_NAME.SOURCE_TYPE", in upper case. Note that the returned string may contain
     * blanks.
     *
     * @return the name of the source, or {@code null} if it could not be determined.
     */
    String getSourceName();

  } // end Entry

}
