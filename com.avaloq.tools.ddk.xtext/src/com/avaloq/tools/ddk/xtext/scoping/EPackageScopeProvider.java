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
package com.avaloq.tools.ddk.xtext.scoping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;
import org.eclipse.xtext.scoping.impl.ScopeBasedSelectable;
import org.eclipse.xtext.scoping.impl.SelectableBasedScope;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * The scope provider class for {@link org.eclipse.emf.ecore.EPackage EPackage}.
 */
public class EPackageScopeProvider {

  private static final Logger LOG = LogManager.getLogger(EPackageScopeProvider.class);

  @Inject
  private IGlobalScopeProvider globalScopeProvider;
  @Inject
  private ResourceDescriptionsProvider descriptionsProvider;
  @Inject
  private IQualifiedNameConverter nameConverter;

  /**
   * Scope for {@link EPackage}. These are read from the registry as well as from the {@link Grammar Xtext grammar} corresponding
   * to the scope model (if any).
   *
   * @param context
   *          context scope DSL model (usually the root element, but any object will do)
   * @param reference
   *          context reference (unused for the time being)
   * @return scope with all available {@link EPackage EPackages} with {@link EPackage#getNsURI()} as name
   */
  // CHECKSTYLE:OFF
  public IScope scope_EPackage(final EObject context, final EReference reference) {
    // CHECKSTYLE:ON
    Resource rsc = context.eResource();
    IScope result = IScope.NULLSCOPE;
    // Add packages from the registry first.
    final Registry packageRegistry = EPackage.Registry.INSTANCE;
    result = new AbstractScope(result, false) {
      @Override
      protected IEObjectDescription getSingleLocalElementByName(final QualifiedName name) {
        return getEPackage(nameConverter.toString(name));
      }

      @Override
      protected Iterable<IEObjectDescription> getAllLocalElements() {
        return Iterables.filter(Iterables.transform(Sets.newHashSet(packageRegistry.keySet()), this::getEPackage), Predicates.notNull());
      }

      private IEObjectDescription getEPackage(final String nsURI) {
        try {
          EPackage ePackage = packageRegistry.getEPackage(nsURI);
          return ePackage != null ? EObjectDescription.create(QualifiedName.create(nsURI), ePackage) : null;
          // CHECKSTYLE:OFF
        } catch (Exception e) {
          // CHECKSTYLE:ON
          LOG.warn("could not load package " + nsURI, e); //$NON-NLS-1$
          return null;
        }
      }
    };
    // Add the index
    IResourceDescriptions descriptions = descriptionsProvider.getResourceDescriptions(context.eResource());
    if (descriptions != null) {
      result = SelectableBasedScope.createScope(result, descriptions, EcorePackage.Literals.EPACKAGE, false);
    }
    // Add the global scope
    result = SelectableBasedScope.createScope(result, new ScopeBasedSelectable(globalScopeProvider.getScope(rsc, reference, null)), EcorePackage.Literals.EPACKAGE, false);
    // Note: we deliberately do NOT add a "packages from the grammar" scope on top here. The previous
    // implementation read the sibling grammar resource via getContents(), which forced its
    // installDerivedState - and that in turn runs Xtext2EcoreTransformer.removeGeneratedPackages, which
    // iterates the entire resource set and force-loads every other DSL resource. When this scope provider
    // is invoked while a sibling DSL resource (.scope, .export, .check) is being linked, that side-effect
    // drags the sibling's JvmModelInferrer into the current linking phase against a not-yet-linked model.
    // The grammar's EPackages are already reachable through the registry (plugin-registered packages)
    // and the index (workspace .ecore and .xtext resources), so the extra grammar-resource scope was
    // belt-and-suspenders. Dropping it removes the recursion trigger without losing coverage.

    return result;
  }

  /**
   * Collects all EPackages referenced by a given grammar and its mixins.
   *
   * @param grammar
   *          grammar to collect packages for
   * @return all referenced EPackages
   */
  public Map<String, EPackage> collectPackages(final Grammar grammar) {
    final Map<String, EPackage> result = new HashMap<String, EPackage>();
    for (final Grammar usedGrammar : grammar.getUsedGrammars()) {
      result.putAll(collectPackages(usedGrammar));
    }
    for (final AbstractMetamodelDeclaration decl : grammar.getMetamodelDeclarations()) {
      final EPackage ePackage = decl.getEPackage();
      if (ePackage != null) { // NOPMD
        result.put(ePackage.getNsURI(), ePackage);
      }
    }
    return result;
  }

  /**
   * Return all the EPackages referenced by a grammar, including its own generated meta model only if
   * it isn't available in the EPackage registry already. The result will include the meta models of
   * all super grammars, as well as the meta model referenced from there.
   *
   * @param grammar
   *          The grammar.
   * @return See comment above.
   */
  public Collection<EPackage> getGrammarEPackages(final Grammar grammar) {
    return collectPackages(grammar).values();
  }
}
