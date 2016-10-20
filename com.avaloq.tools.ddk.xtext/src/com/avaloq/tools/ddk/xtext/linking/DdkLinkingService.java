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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.impl.ImportedNamesAdapter;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.xtext.linking.ImportedNamesTypesAdapter.WrappingTypedScope;
import com.avaloq.tools.ddk.xtext.naming.QualifiedNames;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * Extended linking service that computes imported names centrally in one place, instead of wrapping scopes and stuff.
 * This is much simpler, far less intrusive, doesn't require us to migrate now to the new concept of local and global
 * scope providers, and should work just as well as their default mechanism of wrapping all global scopes by something
 * that records each input parameter of 'getContentByName'. It also avoids problems with our using query_ operations
 * and then doing a getContents() or getAllContents() on them: if the scopes returned by these queries were wrapped,
 * as they would need to be since they're "global" scopes, we'd get errors because the standard wrapper of xtext does
 * not allow calling these operations on global scopes. (That's actually an interface-breaking restriction that is
 * rather ugly.)
 * The only potential problem so far is that results of queries made in template variable resolvers do not result in
 * the names being added here, and that customized linking services that may "manually" link to objects from other
 * resources may need to call importObject explicitly.
 */
public class DdkLinkingService extends DefaultLinkingService {

  @Inject
  private ICrossReferenceHelper crossRefHelper;

  @Inject
  private ResourceDescriptionsProvider provider;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  @Inject
  private Provider<ImportedNamesTypesAdapter> importedNamesAdapterProvider;

  /**
   * Register a name as imported.
   *
   * @param context
   *          context object within which a reference is being set to some object using "name" for the lookup, must not be {@code null}
   * @param name
   *          the lookup name, may be {@code null}
   * @param type
   *          the lookup type, may be {@code null}
   * @param target
   *          the associated target eObject. If not {@code null} then all names for this eObject are also recorded
   *          as imported, provided they're not the same as the {@code name} parameter. May be {@code null}
   */
  public void importObject(final EObject context, final String name, final EObject target, final EClass type) {
    // import exported name for resolved references
    ImportedNamesTypesAdapter adapter = getImportedNamesAdapter(context);
    if (target != null) {
      QualifiedName targetName = null;
      final IResourceDescriptions resourceDescriptions = provider.getResourceDescriptions(context.eResource());
      Iterator<IEObjectDescription> exports = resourceDescriptions.getExportedObjectsByObject(target).iterator();
      if (exports.hasNext()) {
        targetName = exports.next().getName();
        if (targetName != null && !targetName.isEmpty()) {
          final QualifiedName lowerCaseName = targetName.toLowerCase();// NOPMD targetName not a String!
          adapter.getImportedNames().add(lowerCaseName);
          if (adapter.getImportedNamesTypes().containsKey(lowerCaseName)) {
            adapter.getImportedNamesTypes().get(lowerCaseName).add(type);
          } else {
            adapter.getImportedNamesTypes().put(lowerCaseName, Sets.newHashSet(type));
          }

        }
      }
    } else if (name != null && name.length() > 0) { // import parsed string for unresolved references
      QualifiedName unresolvedName = QualifiedNames.toUnresolvedName(name);
      adapter.getImportedNames().add(unresolvedName);
      if (adapter.getImportedNamesTypes().containsKey(unresolvedName)) {
        adapter.getImportedNamesTypes().get(unresolvedName).add(type);
      } else {
        adapter.getImportedNamesTypes().put(unresolvedName, Sets.newHashSet(type));
      }
    }
  }

  /**
   * Check whether a name import should be added for the given cross-reference.
   * <p>
   * This default implementation will return {@code true} in either of the following cases:
   * <ul>
   * <li>the target is {@code null} and the reference is <em>not</em> {@link ICrossReferenceHelper#isOptionalReference(EObject, EReference, INode)
   * optional}</li>
   * <li>the reference is <em>not</em> {@code null} and should be {@link ICrossReferenceHelper#exportReference(EObject, EReference, EObject) exported}</li>
   * </ul>
   *
   * @param context
   *          The context of the reference
   * @param ref
   *          The meta model cross-reference
   * @param target
   *          The target object of the reference; may also be {@code null}
   * @param node
   *          The parse tree node corresponding to the cross-reference
   * @return {@code true} if the object is imported or unresolved
   */
  protected boolean isImportRequired(final EObject context, final EReference ref, final EObject target, final INode node) {
    // TODO never import names for optional references? whether they resolve or not?
    return (target == null && !crossRefHelper.isOptionalReference(context, ref, node))
        || (target != null && crossRefHelper.exportReference(context, ref, target));
  }

  /** {@inheritDoc} */
  @Override
  public List<EObject> getLinkedObjects(final EObject context, final EReference ref, final INode node) {
    final EClass requiredType = ref.getEReferenceType();
    if (requiredType == null) {
      return Collections.emptyList();
    }

    final String s = getCrossRefNodeAsString(node);
    if (s != null && s.length() > 0) {
      QualifiedName qualifiedLinkName = qualifiedNameConverter.toQualifiedName(s);
      final EObject target = getSingleElement(context, ref, qualifiedLinkName);
      if (isImportRequired(context, ref, target, node)) {
        importObject(context, s, target, requiredType);
      }
      if (target != null) {
        return Collections.singletonList(target);
      }
    }
    return Collections.emptyList();
  }

  /**
   * Gets an {@link EObject} that best matches a given context, reference, and qualified name.
   * <p>
   * The concept of a "best match" is "in the eye of the beholder", that is, the context and reference. The default case is the first element returned from the
   * scope for the given context and reference that matches the given qualified name.
   * </p>
   *
   * @param context
   *          the context of the {@code reference}, must not be {@code null}
   * @param reference
   *          the reference for which the result element should be suitable, must not be {@code null}
   * @param qualifiedLinkName
   *          the name that the result element should match, must not be {@code null}
   * @return an eObject that best matches {@code qualifiedLinkName} in {@code scope} given the {@code context} and {@code reference},
   *         may by {@code null}
   */
  protected EObject getSingleElement(final EObject context, final EReference reference, final QualifiedName qualifiedLinkName) {
    IEObjectDescription desc = null;
    IScope scope = getScope(context, reference);
    if (scope instanceof WrappingTypedScope) {
      desc = ((WrappingTypedScope) scope).getSingleElement(qualifiedLinkName, reference);
    } else {
      desc = scope.getSingleElement(qualifiedLinkName);
    }
    return desc == null ? null : desc.getEObjectOrProxy();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ImportedNamesTypesAdapter getImportedNamesAdapter(final EObject context) {
    ImportedNamesAdapter adapter = ImportedNamesAdapter.find(context.eResource());
    if (adapter instanceof ImportedNamesTypesAdapter) {
      return (ImportedNamesTypesAdapter) adapter;
    }
    ImportedNamesTypesAdapter importedNamesTypeAdapter = importedNamesAdapterProvider.get();
    context.eResource().eAdapters().add(importedNamesTypeAdapter);
    return importedNamesTypeAdapter;
  }

}
