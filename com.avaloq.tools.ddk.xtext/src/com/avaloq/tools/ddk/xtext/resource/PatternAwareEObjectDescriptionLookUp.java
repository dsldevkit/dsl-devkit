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
package com.avaloq.tools.ddk.xtext.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.EObjectDescriptionLookUp;

import com.avaloq.tools.ddk.caching.CacheManager;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNameLookup;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNamePattern;
import com.avaloq.tools.ddk.xtext.util.EObjectUtil;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;


/**
 * Implementation of {@link EObjectDescriptionLookUp} which understands {@link QualifiedNamePattern} in
 * {@link #getExportedObjects(EClass, QualifiedName, boolean)}.
 */
public class PatternAwareEObjectDescriptionLookUp extends EObjectDescriptionLookUp {

  @SuppressWarnings("PMD.AvoidUsingVolatile")
  private volatile QualifiedNameLookup<IEObjectDescription> nameToObjectsLookup;

  public PatternAwareEObjectDescriptionLookUp(final List<IEObjectDescription> exportedObjects) {
    super(exportedObjects);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjects(final EClass type, final QualifiedName name, final boolean ignoreCase) {
    QualifiedName lowerCase = name.toLowerCase(); // NOPMD UseLocaleWithCaseConversions not a String!
    QualifiedNameLookup<IEObjectDescription> lookup = getNameToObjectsLookup();
    Collection<IEObjectDescription> values;
    final boolean isPattern = lowerCase instanceof QualifiedNamePattern;
    if (isPattern) {
      values = lookup.get((QualifiedNamePattern) lowerCase, false);
    } else {
      values = lookup.get(lowerCase);
    }
    if (values == null) {
      return Collections.emptyList();
    }
    Predicate<IEObjectDescription> predicate = ignoreCase ? input -> EcoreUtil2.isAssignableFrom(type, input.getEClass())
        : input -> isPattern ? EcoreUtil2.isAssignableFrom(type, input.getEClass()) && ((QualifiedNamePattern) name).matches(name)
            : name.equals(input.getName()) && EcoreUtil2.isAssignableFrom(type, input.getEClass());
    return Collections2.filter(values, predicate);
  }

  @Override
  public Iterable<IEObjectDescription> getExportedObjectsByObject(final EObject object) {
    // cannot really filter by EClass, as "object" may be a dummy proxy of arbitrary type
    final String fragment = EObjectUtil.getURIFragment(object);
    return Iterables.filter(getExportedObjects(), input -> fragment.equals(input.getEObjectURI().fragment()));
  }

  @Override
  public void setExportedObjects(final List<IEObjectDescription> allDescriptions) {
    synchronized (this) {
      super.setExportedObjects(allDescriptions);
      this.nameToObjectsLookup = null; // NOPMD NullAssignment necessary
    }
  }

  @Override
  protected Map<QualifiedName, List<IEObjectDescription>> getNameToObjects() {
    throw new UnsupportedOperationException();
  }

  protected QualifiedNameLookup<IEObjectDescription> getNameToObjectsLookup() {
    if (nameToObjectsLookup == null) {
      synchronized (this) {
        // CHECKSTYLE:OFF with volatile it is ok
        if (nameToObjectsLookup == null) {
          // CHECKSTYLE:ON with volatile it is ok
          Iterable<IEObjectDescription> allDescriptions = getExportedObjects();
          QualifiedNameLookup<IEObjectDescription> localMap = CacheManager.getInstance().createNameLookupCache("PatternAwareEObjectDescriptionLookUp#localMap", IEObjectDescription.class, false); //$NON-NLS-1$
          if (allDescriptions instanceof RandomAccess) {
            List<IEObjectDescription> asList = (List<IEObjectDescription>) allDescriptions;
            for (int i = 0; i < asList.size(); i++) {
              IEObjectDescription description = asList.get(i);
              localMap.put(description.getName().toLowerCase(), description);
            }
          } else {
            for (IEObjectDescription description : allDescriptions) {
              localMap.put(description.getName().toLowerCase(), description);
            }
          }
          this.nameToObjectsLookup = localMap;
        }
      }
    }
    return this.nameToObjectsLookup;
  }

}
