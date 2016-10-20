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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.AbstractMetamodelDeclaration;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.eclipse.xtext.resource.DerivedStateAwareResource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;


/**
 * A base linking service class.
 * Specialized linking service that can find grammars (xtext, xmi, or xtextbin) quickly. Using scoping to do so loads all
 * installed language bundles, which may take a long time if there are many languages installed.
 */
public abstract class AbstractDdkLinkingService extends DefaultLinkingService {

  private static final String HTTP_SCHEME = "http"; //$NON-NLS-1$

  @Override
  public List<EObject> getLinkedObjects(final EObject context, final EReference ref, final INode node) {
    return super.getLinkedObjects(context, ref, node);
  }

  /**
   * Returns the grammar name based on the given {@link INode}.
   *
   * @param node
   *          for which the grammar name should be inferred
   * @return grammar name
   */
  protected abstract String getGrammarName(final INode node);

  /**
   * Tries to find a grammar.
   *
   * @param resourceSet
   *          to use for loading
   * @param node
   *          parse subtree for the reference
   * @return A singleton list containing the grammar, or an empty list if not found.
   */
  protected List<EObject> getUsedGrammar(final ResourceSet resourceSet, final INode node) {
    // Code copied and adapted from XtextLinkingService.
    try {
      // First look in the resource set; we might have it loaded already.
      String grammarName = getGrammarName(node);
      if (grammarName != null) {
        List<Resource> resources = resourceSet.getResources();
        for (int i = 0; i < resources.size(); i++) {
          Resource resource = resources.get(i);
          EObject rootElement = null;
          if (resource instanceof XtextResource) {
            IParseResult parseResult = ((XtextResource) resource).getParseResult();
            if (parseResult != null) {
              rootElement = parseResult.getRootASTElement();
            }
          } else if (!resource.getContents().isEmpty()) {
            rootElement = resource.getContents().get(0);
          }
          if (rootElement instanceof Grammar) {
            Grammar otherGrammar = (Grammar) rootElement;
            if (grammarName.equals(otherGrammar.getName())) {
              if (resource instanceof DerivedStateAwareResource) {
                resource.getContents();
              }
              return Collections.<EObject> singletonList(otherGrammar);
            }
          }
        }
        // Not loaded yet: first try .xtext, and if that fails, try .xtextbin, finally .xmi. One of them must be present.
        // Try to register EPackages in the workspace first, so that the XtextLinkingService will also find them.
        registerKnownEPackages(grammarName, resourceSet);
        Grammar result = loadGrammar(grammarName, "xtext", resourceSet); //$NON-NLS-1$
        if (result == null) {
          result = loadGrammar(grammarName, "xtextbin", resourceSet); // Since Xtext 2.4.3 //$NON-NLS-1$
          if (result == null) {
            result = loadGrammar(grammarName, "xmi", resourceSet); // Earlier xtext versions, or with xmlVersion set in the generator //$NON-NLS-1$
          }
          // The grammar loaded from the XMI may contain a number or proxies with http:// URIs. If we resolve those normally,
          // we'll end up going to the internet. Hence we'll have to fix them up here. We start with "false" for the "doLoad"
          // parameter because we would have loaded and registered the grammar's base model already above in registerKnownEPackages().
          if (result != null) {
            fixUpEPackageProxies(result, resourceSet, false);
          }
        }
        if (result != null) {
          return Collections.<EObject> singletonList(result);
        }
      }
      return Collections.emptyList();
    } catch (ValueConverterException e) {
      return Collections.emptyList();
    }
  }

  /**
   * Tries to load the given grammar though the classpath, using the given extension.
   *
   * @param grammarName
   *          of the grammar to load
   * @param extension
   *          of the file to look for
   * @param resourceSet
   *          to use for loading
   * @return the Grammar, if loaded, or null otherwise
   */
  private Grammar loadGrammar(final String grammarName, final String extension, final ResourceSet resourceSet) {
    URI grammarURI = URI.createURI(ClasspathUriUtil.CLASSPATH_SCHEME + ":/" + grammarName.replace('.', '/')).appendFileExtension(extension); //$NON-NLS-1$
    // BTW: this classpath-URI construction is also used in the XtextLinkingService and is the reason why a grammar named
    // foo.bar.SomeGrammar *must* be in a package "foo.bar". Xtext does not try the hack below (which anyway will work in
    // special circumstances only). The hack is probably still worth doing, since in particular the ASMD workspace is or
    // at least was once setup wrongly in a way that could indeed be fixed by this hack.
    Grammar grammar = tryLoadGrammar(grammarURI, grammarName, resourceSet);
    if (grammar == null) {
      // Hack alert: let's try without the path to accommodate broken workspace setups. Note that lastSegment() includes the extension!
      grammar = tryLoadGrammar(URI.createURI(grammarURI.scheme() + ":/" + grammarURI.lastSegment()), grammarName, resourceSet); //$NON-NLS-1$
    }
    return grammar;
  }

  /**
   * Tries to load a grammar from a given URI.
   *
   * @param uri
   *          to load the grammar from
   * @param name
   *          of the grammar to load
   * @param resourceSet
   *          to use for loading
   * @return The grammar, if the resource could be loaded and contains a grammar of the given name, or null otherwise.
   */
  private Grammar tryLoadGrammar(final URI uri, final String name, final ResourceSet resourceSet) {
    try {
      URI normalizedURI = normalizeClasspathURI(uri, resourceSet);
      final Resource resource = resourceSet.getResource(normalizedURI, true);
      if (!resource.getContents().isEmpty()) {
        final Grammar usedGrammar = (Grammar) resource.getContents().get(0);
        if (name.equals(usedGrammar.getName())) {
          return usedGrammar;
        }
      }
      return null;
    } catch (ClasspathUriResolutionException e) {
      return null;
    }
  }

  /**
   * Normalize a classpath URI. Code from XtextLinkingService.
   *
   * @param uri
   *          to normalize
   * @param resourceSet
   *          to do the normalization with
   * @return the normalized URI
   */
  URI normalizeClasspathURI(final URI uri, final ResourceSet resourceSet) {
    if (resourceSet instanceof XtextResourceSet) {
      XtextResourceSet set = (XtextResourceSet) resourceSet;
      return set.getClasspathUriResolver().resolve(set.getClasspathURIContext(), uri);
    } else {
      return resourceSet.getURIConverter().normalize(uri);
    }
  }

  /**
   * Tries to load an EPackage from a given URI.
   *
   * @param uri
   *          to load the EPackage from
   * @param resourceSet
   *          to use for loading
   * @return The EPackage, if the resource could be loaded, or null otherwise.
   */
  private EPackage loadEPackage(final URI uri, final ResourceSet resourceSet) {
    try {
      URI normalizedURI = normalizeClasspathURI(uri, resourceSet);
      final Resource resource = resourceSet.getResource(normalizedURI, true);
      if (!resource.getContents().isEmpty()) {
        return (EPackage) resource.getContents().get(0);
      }
      return null;
    } catch (ClasspathUriResolutionException e) {
      return null;
    }
  }

  /**
   * When a grammar is loaded through XMI, it contains a lot of proxies with http:// URIs. Trying to resolve those will make us
   * go onto the Internet. Not good. Therefore, we resolve these links here explicitly for our grammar.
   * <p>
   * Since we know that the rest of check will only ever query getMetamodelDeclarations().getEPackages(), we only fix up that.
   * </p>
   *
   * @param grammar
   *          to fix
   * @param resourceSet
   *          to use
   * @param doLoad
   *          whether to load EPackages
   */
  private void fixUpEPackageProxies(final Grammar grammar, final ResourceSet resourceSet, final boolean doLoad) {
    boolean registeredPackages = !doLoad;
    for (AbstractMetamodelDeclaration model : grammar.getMetamodelDeclarations()) {
      InternalEObject ePackage = (InternalEObject) model.eGet(XtextPackage.Literals.ABSTRACT_METAMODEL_DECLARATION__EPACKAGE, false);
      if (ePackage.eIsProxy()) {
        URI nsURI = ePackage.eProxyURI().trimFragment();
        if (HTTP_SCHEME.equalsIgnoreCase(nsURI.scheme())) {
          EPackage resolved = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
          if (resolved == null && !registeredPackages) {
            // Not in the registry. Hmmm. For customers, who always work against deployed languages, we always find the
            // EPackage in the registry. This can only happen if we're in the workspace. So, how do we find the EPackage?
            // Assume it has the same name as the grammar, with extension "ecore", and can be found via the classpath.
            registerKnownEPackages(grammar.getName(), resourceSet);
            registeredPackages = true;
            resolved = resourceSet.getPackageRegistry().getEPackage(nsURI.toString());
          }
          try {
            model.eSetDeliver(false);
            model.eSet(XtextPackage.Literals.ABSTRACT_METAMODEL_DECLARATION__EPACKAGE, resolved);
          } finally {
            model.eSetDeliver(true);
          }
        }
      }
    }
    // Used grammars are in the same XMI, linked locally, so that should be safe. We guard against proxies all the same.
    for (Grammar used : grammar.getUsedGrammars()) {
      if (used != null && !used.eIsProxy()) {
        fixUpEPackageProxies(used, resourceSet, true);
      }
    }
  }

  /**
   * Loads an ecore model with the same name as the grammar, if it exists, and registers it.
   * Also then follows all (resolved) crosslinks and registers any new packages found.
   * Does so transitively.
   *
   * @param grammarName
   *          the name to try
   * @param resourceSet
   *          to use for loading
   */
  protected void registerKnownEPackages(final String grammarName, final ResourceSet resourceSet) {
    URI ecoreURI = URI.createURI(ClasspathUriUtil.CLASSPATH_SCHEME + ":/" + grammarName.replace('.', '/')).appendFileExtension("ecore"); //$NON-NLS-1$//$NON-NLS-2$
    EPackage pack = loadEPackage(ecoreURI, resourceSet);
    if (pack == null) {
      // Hack alert: let's try without the path to accommodate broken workspace setups. Note that lastSegment() includes the extension!
      pack = loadEPackage(URI.createURI(ecoreURI.scheme() + ":/" + ecoreURI.lastSegment()), resourceSet); //$NON-NLS-1$
    }
    if (pack != null) {
      registerEPackagesTransitively(pack);
    }
  }

  /**
   * Globally registers the given EPackage, plus all resolved EPackages referenced from it.
   *
   * @param pack
   *          to register
   */
  private void registerEPackagesTransitively(final EPackage pack) {
    // Register globally. First, not all code uses the resourceSet's local registry, and second, that local registry should
    // delegate to the global one anyway.
    if (!EPackage.Registry.INSTANCE.containsKey(pack.getNsURI()) || isPlatformOverride(pack)) {
      // Make sure that workspace packages override deployed packages.
      EPackage.Registry.INSTANCE.put(pack.getNsURI(), pack);
    }
    EcoreUtil.resolveAll(pack);
    Iterator<EObject> contents = EcoreUtil.getAllProperContents(pack, true);
    while (contents.hasNext()) {
      EObject obj = contents.next();
      for (EObject other : obj.eCrossReferences()) {
        if (!other.eIsProxy()) {
          EPackage otherPackage = EcoreUtil2.getContainerOfType(other, EPackage.class);
          if (otherPackage != null && !pack.equals(otherPackage)) {
            String otherURI = otherPackage.getNsURI();
            if (!EPackage.Registry.INSTANCE.containsKey(otherURI) || isPlatformOverride(pack)) {
              registerEPackagesTransitively(otherPackage);
            }
          }
        }
      }
    }
  }

  /**
   * Determines whether the given EPackage is a workspace override of some other registered EPackage.
   *
   * @param pack
   *          to check
   * @return {@code true} if the EPackage is in the workspace but overrides some other already registered package, {@code false} otherwise.
   */
  private boolean isPlatformOverride(final EPackage pack) {
    Resource r = pack.eResource();
    if (r != null && r.getURI().isPlatformResource()) {
      EPackage alreadyRegistered = EPackage.Registry.INSTANCE.getEPackage(pack.getNsURI());
      Resource r2 = alreadyRegistered.eResource();
      if (r2 != null && r2.getURI().isPlatformPlugin()) {
        return true;
      }
    }
    return false;
  }

}
