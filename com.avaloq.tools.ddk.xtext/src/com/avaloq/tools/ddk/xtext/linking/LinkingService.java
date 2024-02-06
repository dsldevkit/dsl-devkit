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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.impl.ImportedNamesAdapter;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.scoping.IScope;

import com.avaloq.tools.ddk.xtext.linking.ImportedNamesTypesAdapter.WrappingTypedScope;
import com.avaloq.tools.ddk.xtext.scoping.AliasingEObjectDescription;
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
public class LinkingService extends DefaultLinkingService {

  private static final Logger LOGGER = LogManager.getLogger(LinkingService.class);

  @Inject
  private ICrossReferenceHelper crossRefHelper;

  @Inject
  private ResourceDescriptionsProvider provider;

  @Inject
  private IQualifiedNameConverter qualifiedNameConverter;

  @Inject
  private Provider<ImportedNamesTypesAdapter> importedNamesAdapterProvider;

  private void registerNamedType(final EObject context, final QualifiedName name, final EClass type) {
    ImportedNamesTypesAdapter adapter = getImportedNamesAdapter(context);
    adapter.getImportedNames().add(name);
    if (adapter.getImportedNamesTypes().containsKey(name)) {
      adapter.getImportedNamesTypes().get(name).add(type);
    } else {
      adapter.getImportedNamesTypes().put(name, Sets.newHashSet(type));
    }
  }

  /**
   * Register a name as imported.
   *
   * @param context
   *          context object within which a reference is being set to some object, must not be {@code null}
   * @param target
   *          the associated target eObject. Its exported name is recorded as imported, must not be {@code null}
   * @param type
   *          the lookup type, may be {@code null}
   */
  public void importObject(final EObject context, final EObject target, final EClass type) {
    Resource eResource = target.eResource();
    QualifiedName targetName = null;
    if (eResource instanceof LazyLinkingResource2) {
      IQualifiedNameProvider nameProvider = ((LazyLinkingResource2) eResource).getService(IQualifiedNameProvider.class);
      if (nameProvider != null) {
        targetName = nameProvider.getFullyQualifiedName(target);
      }
    } else {
      final IResourceDescriptions resourceDescriptions = provider.getResourceDescriptions(context.eResource());
      Iterator<IEObjectDescription> exports = resourceDescriptions.getExportedObjectsByObject(target).iterator();
      if (exports.hasNext()) {
        targetName = exports.next().getName();
      }
    }
    if (targetName != null && !targetName.isEmpty()) {
      registerNamedType(context, targetName.toLowerCase(), type); // NOPMD targetName not a String!
    }
  }

  /**
   * Register a name as imported.
   *
   * @param context
   *          context object within which a reference is being set to some object, must not be {@code null}
   * @param desc
   *          the associated description. Its name is recorded as imported, must not be {@code null}
   * @param type
   *          the lookup type, may be {@code null}
   */
  public void importObject(final EObject context, final IEObjectDescription desc, final EClass type) {
    QualifiedName targetName;
    if (desc instanceof AliasingEObjectDescription) {
      targetName = ((AliasingEObjectDescription) desc).getOriginalName();
    } else {
      targetName = desc.getName();
    }
    if (targetName != null && !targetName.isEmpty()) {
      registerNamedType(context, targetName.toLowerCase(), type); // NOPMD targetName not a String!
    }
  }

  /**
   * Register a typed unresolved reference.
   *
   * @param context
   *          context object within which a reference is being set to unresolved, must not be {@code null}
   * @param name
   *          the lookup name, may be {@code null}
   * @param type
   *          the lookup type, may be {@code null}
   */
  public void registerUnresolvedReference(final EObject context, final String name, final EClass type) {
    registerNamedType(context, crossRefHelper.toUnresolvedReferenceName(name), type);
  }

  /**
   * Check whether a name import should be added for the given cross-reference.
   * <p>
   * This default implementation will return {@code true} if the reference should be {@link ICrossReferenceHelper#exportReference(EObject, EReference, EObject)}
   * exported
   *
   * @param context
   *          The context of the reference
   * @param ref
   *          The meta model cross-reference
   * @param target
   *          The target object of the reference; may be {@code null}
   * @return {@code true} if the object is imported
   */
  protected boolean isImportRequired(final EObject context, final EReference ref, final EObject target) {
    return crossRefHelper.exportReference(context, ref, target);
  }

  /**
   * Check whether the name of the unresolved reference should be registered.
   * <p>
   * This default implementation will return {@code true} if the reference is <em>not</em>
   * {@link ICrossReferenceHelper#isOptionalReference(EObject, EReference, INode)} optional
   *
   * @param context
   *          The context of the reference
   * @param ref
   *          The meta model cross-reference
   * @param node
   *          The parse tree node corresponding to the cross-reference
   * @return {@code true} if the unresolved references should be registered
   */
  protected boolean doRegisterUnresolvedReference(final EObject context, final EReference ref, final INode node) {
    return !crossRefHelper.isOptionalReference(context, ref, node);
  }

  /**
   * Gets the actual element or a proxy.
   * <p>
   * This default implementation will return just the {@code the actual element or a proxy} of the given candidate,
   * languages with more complex logic (e.g. overloading) can override this method.
   *
   * @param context
   *          The context of the reference
   * @param desc
   *          The descriptor, never {@code null}
   * @param ref
   *          The meta model cross-reference, never {@code null}
   * @return the actual element or a proxy
   */
  protected EObject getEObjectOrProxy(final EObject context, final IEObjectDescription desc, final EReference ref) {
    return desc.getEObjectOrProxy();
  }

  private IEObjectDescription safeGetSingleElement(final EObject context, final EReference ref, final QualifiedName qualifiedLinkName) {
    try {
      return getSingleElement(context, ref, qualifiedLinkName);
    } catch (Exception e) {  // NOPMD
      LOGGER.error("Exception in getSingleElement", e);
    }
    return null;
  }

  @Override
  public List<EObject> getLinkedObjects(final EObject context, final EReference ref, final INode node) {
    final EClass requiredType = ref.getEReferenceType();
    if (requiredType == null) {
      return Collections.emptyList();
    }

    final String linkName = getCrossRefNodeAsString(node);
    if (linkName != null && !linkName.isEmpty()) {
      final QualifiedName qualifiedLinkName = qualifiedNameConverter.toQualifiedName(linkName);
      final IEObjectDescription desc = safeGetSingleElement(context, ref, qualifiedLinkName);
      final EObject target = desc == null ? null : getEObjectOrProxy(context, desc, ref);

      if (target != null) {
        if (isImportRequired(context, ref, target)) {
          if (target.eIsProxy()) {
            importObject(context, desc, ref.getEReferenceType());
          } else {
            importObject(context, target, ref.getEReferenceType());
          }
        }
        return Collections.singletonList(target);
      }

      if (doRegisterUnresolvedReference(context, ref, node)) {
        registerUnresolvedReference(context, linkName, requiredType);
      }
    }
    return Collections.emptyList();

  }

  /**
   * Gets an {@link IEObjectDescription} that best matches a given context, reference, and qualified name.
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
   * @return an IEObjectDescription that best matches {@code qualifiedLinkName} in {@code scope} given the {@code context} and {@code reference},
   *         may by {@code null}
   */
  protected IEObjectDescription getSingleElement(final EObject context, final EReference reference, final QualifiedName qualifiedLinkName) {
    IEObjectDescription desc;
    IScope scope = getScope(context, reference);
    if (scope instanceof WrappingTypedScope) {
      desc = ((WrappingTypedScope) scope).getSingleElement(qualifiedLinkName, reference);
    } else {
      desc = scope.getSingleElement(qualifiedLinkName);
    }
    return desc;
  }

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
