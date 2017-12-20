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
package com.avaloq.tools.ddk.xtext.builder.tracing;

import com.avaloq.tools.ddk.xtext.tracing.TraceEvent;


/**
 * An event representing an operation on an index.
 */
public class IndexQueryEvent extends TraceEvent {

  /**
   * Represents the collection of traceable index queries.
   */
  @SuppressWarnings("nls")
  public enum Query {
    INSERT_RESOURCES("insertResources(Iterable<IResourceDescription>, boolean)", "Persist Resources"),
    INSERT_ISSUES("insertIssues(Multimap<URI, Issue>)", "Persist Issues"),
    DELETE_RESOURCES("deleteResourceDescriptions(Iterable<URI>)", "Delete Resources"),
    LOAD_RESOURCES("loadResources(Collection<URI>)", "Load Resources"),
    GET_EXPORTED_OBJECTS("getExportedObjects()", "Fetch All"),
    GET_EXPORTED_OBJECTS_BY_URI("getExportedObjects(URI)", "Fetch by URI"),
    GET_EXPORTED_OBJECTS_BY_NAME("getExportedObjects(EClass, QualifiedName, boolean)", "Fetch by Name"),
    GET_EXPORTED_OBJECTS_BY_URI_NAME("getExportedObjects(URI, EClass, QualifiedName, boolean)", "Fetch by URI\\Name"),
    GET_EXPORTED_OBJECTS_BY_ECLASS("getExportedObjectsByType(EClass)", "Fetch by EClass"),
    GET_EXPORTED_OBJECTS_BY_URI_ECLASS("getExportedObjectsByType(URI, EClass)", "Fetch by URI\\EClass"),
    GET_EXPORTED_OBJECTS_BY_OBJECT("getExportedObjectsByObject(EObject)", "Fetch by Model");

    private final String queryName;
    private final String shortName;

    Query(final String queryName, final String shortName) {
      this.queryName = queryName;
      this.shortName = shortName;
    }

    @Override
    public String toString() {
      return queryName;
    }

    public String getShortName() {
      return shortName;
    }

  }

  /**
   * Creates a new instance of {@link IndexQueryEvent}.
   *
   * @param trigger
   *          event trigger
   * @param data
   *          event data
   */
  public IndexQueryEvent(final Trigger trigger, final Object... data) {
    super(trigger, data);
  }

}
