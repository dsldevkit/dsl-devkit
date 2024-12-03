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
package com.avaloq.tools.ddk.xtext.linking;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.linking.impl.ImportedNamesAdapter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.collect.Maps;


/**
 * Wraps global scopes and collects and holds all names and types for which global scopes have been asked.
 */
public class ImportedNamesTypesAdapter extends ImportedNamesAdapter {

  private final Map<QualifiedName, Set<EClass>> importedNamesTypes = Maps.newHashMap();

  /**
   * Returns registered ImportedNameAdapter for given resource.
   *
   * @param resource
   *          Resource for which adapter should be returned.
   * @return registered importedNamesTypesAdapter.
   */
  public static ImportedNamesTypesAdapter find(final Resource resource) {
    for (Adapter adapter : resource.eAdapters()) {
      if (adapter instanceof ImportedNamesAdapter) {
        return (ImportedNamesTypesAdapter) adapter;
      }
    }
    return null;
  }

  /**
   * WrappingTypedScope defines which elements {@link IEObjectDescription} can be seen in a certain area within a model/program.
   * In addition to the imported names it initializes importedNamesTypes map, which contains tape information for each reference.
   */
  public class WrappingTypedScope extends WrappingScope {

    private final IScope delegate;

    public WrappingTypedScope(final IScope scope) {
      super(scope);
      this.delegate = scope;
    }

    /**
     * Gets an {@link IEObjectDescription} that best matches a given reference and qualified name.
     *
     * @param reference
     *          the reference for which the result element should be suitable, must not be {@code null}
     * @param name
     *          the name that the result element should match, must not be {@code null}
     * @return an eObjectDescription that best matches {@code name} in {@code scope} given the {@code reference},
     *         may by {@code null}
     */
    public IEObjectDescription getSingleElement(final QualifiedName name, final EReference reference) {
      final QualifiedName lowerCase = name.toLowerCase(); // NOPMD UseLocaleWithCaseConversions not a String!
      getImportedNames().add(lowerCase);
      importedNamesTypes.computeIfAbsent(lowerCase, k -> new HashSet<>()).add(reference.getEReferenceType());

      return delegate.getSingleElement(name);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IScope wrap(final IScope scope) {
    return new WrappingTypedScope(scope);
  }

  /**
   * Getter for importedNamesTypes list.
   *
   * @return map of imported names with their types.
   */
  public Map<QualifiedName, Set<EClass>> getImportedNamesTypes() {
    return importedNamesTypes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    importedNamesTypes.clear();
    super.clear();
  }

}
