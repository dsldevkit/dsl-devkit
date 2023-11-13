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
package com.avaloq.tools.ddk.xtext.scope.linking;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResource;

import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel;
import com.avaloq.tools.ddk.xtext.scope.scope.ScopePackage;
import com.avaloq.tools.ddk.xtext.scope.services.ScopeGrammarAccess;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * Scope specific linking service to handle includes of other scope files. We don't use the scope provider for this as scope files
 * from anywhere can be included.
 */
public class ScopeLinkingService extends DefaultLinkingService {

  /** Class-wide logger. */
  private static final Logger LOG = LogManager.getLogger(ScopeLinkingService.class);

  @Inject
  @Named(Constants.LANGUAGE_NAME)
  private String languageName;
  @Inject
  private IValueConverterService valueConverterService;
  @Inject
  private FileExtensionProvider fileExtensionProvider;
  @Inject
  private ScopeGrammarAccess grammarAccess;

  @Override
  public List<EObject> getLinkedObjects(final EObject context, final EReference ref, final INode node) {
    if (ref == ScopePackage.Literals.SCOPE_MODEL__INCLUDED_SCOPES) {
      return getIncludedModel((ScopeModel) context, node);
    }
    return super.getLinkedObjects(context, ref, node);
  }

  /**
   * Returns the {@link ScopeModel#getIncludes() included scope model} corresponding to the given node from the node model.
   *
   * @param context
   *          context object
   * @param node
   *          parser node corresponding to include reference
   * @return referenced {@link ScopeModel} to include
   */
  private List<EObject> getIncludedModel(final ScopeModel context, final INode node) {
    // based on XtextLinkingService#getUsedGrammar()
    try {
      String scopeModelName = (String) valueConverterService.toValue("", grammarAccess.getDottedIDRule().getName(), node);
      if (scopeModelName != null) {
        final ResourceSet resourceSet = context.eResource().getResourceSet();
        List<Resource> resources = resourceSet.getResources();
        for (int i = 0; i < resources.size(); i++) {
          Resource resource = resources.get(i);
          EObject rootElement = null;
          if (resource instanceof XtextResource && ((XtextResource) resource).getLanguageName().equals(languageName) && !resource.getContents().isEmpty()) {
            rootElement = resource.getContents().get(0);
          }
          if (rootElement instanceof ScopeModel) {
            ScopeModel otherModel = (ScopeModel) rootElement;
            if (scopeModelName.equals(otherModel.getName())) {
              return Collections.<EObject> singletonList(otherModel);
            }
          }
        }
        URI classpathURI = URI.createURI(ClasspathUriUtil.CLASSPATH_SCHEME + ":/" + scopeModelName.replace('.', '/') + "."
            + fileExtensionProvider.getPrimaryFileExtension());
        final Resource resource = resourceSet.getResource(classpathURI, true);
        if (!resource.getContents().isEmpty()) {
          final ScopeModel otherModel = (ScopeModel) resource.getContents().get(0);
          if (scopeModelName.equals(otherModel.getName())) {
            return Collections.<EObject> singletonList(otherModel);
          }
        }
      }
      return Collections.emptyList();
    } catch (ClasspathUriResolutionException e) {
      LOG.warn("Cannot load included scope.", e);
      return Collections.emptyList();
    } catch (ValueConverterException e) {
      LOG.warn("Cannot convert included scope name.", e);
      return Collections.emptyList();
    }
  }
}
