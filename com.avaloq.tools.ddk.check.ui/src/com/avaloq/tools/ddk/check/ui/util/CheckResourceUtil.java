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
package com.avaloq.tools.ddk.check.ui.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.shared.Access;

import com.avaloq.tools.ddk.check.util.GrammarHelper;
import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;


/**
 * Provides utility operations for Eclipse resources.
 */
public class CheckResourceUtil {

  // TODO refactor into GrammarHelper

  private static final Logger LOGGER = LogManager.getLogger(CheckResourceUtil.class);

  /**
   * Gets the name of the resource related to given document.
   *
   * @param xtextDocument
   *          the xtext document
   * @return the name of the resource being edited with given document
   */
  public String getNameOfResource(final IXtextDocument xtextDocument) {
    IFile file = xtextDocument.getAdapter(IFile.class);
    if (file != null && file.getName() != null) {
      final String fileName = file.getName();
      int indexOfDotBeforeExtension = fileName.lastIndexOf('.');
      return indexOfDotBeforeExtension > 0 ? fileName.substring(0, indexOfDotBeforeExtension) : fileName;
    }
    return null;
  }

  /**
   * Gets the name of containing package.
   *
   * @param xtextDocument
   *          the xtext document
   * @return the name of containing package
   */
  public String getNameOfContainingPackage(final IXtextDocument xtextDocument) {
    IFile file = xtextDocument.getAdapter(IFile.class);
    if (file != null && file.getParent() instanceof IFolder) {
      IJavaProject javaProject = JavaCore.create(file.getProject());
      try {
        IPackageFragment myFragment = javaProject.findPackageFragment(file.getParent().getFullPath());
        return myFragment.getElementName();
      } catch (JavaModelException e) {
        LOGGER.error("Could not determine package for file of given document");
      }
    }
    return null;
  }

  /**
   * Looks up all exported grammars.
   *
   * @return an iterable of grammar
   */
  public List<Grammar> getGrammars() {
    Iterable<Grammar> result = allGrammars();

    Ordering<Grammar> byNameOrdering = new Ordering<Grammar>() {
      @Override
      public int compare(final Grammar left, final Grammar right) {
        return Ordering.<String> natural().compare(new GrammarHelper(left).getLabelName(), new GrammarHelper(right).getLabelName());
      }
    };
    return byNameOrdering.sortedCopy(Sets.newHashSet(Iterables.filter(result, Predicates.notNull())));
  }

  /**
   * Gets all available grammars.
   * <p>
   * The result contains no null entries.
   * </p>
   *
   * @return an iterator over all grammars in the workspace followed by all those in the registry.
   */
  private Iterable<Grammar> allGrammars() {
    final ResourceSet resourceSetForResolve = new ResourceSetImpl();
    final Function<IEObjectDescription, Grammar> description2GrammarTransform = new Function<IEObjectDescription, Grammar>() {
      @Override
      public Grammar apply(final IEObjectDescription desc) {
        EObject obj = desc.getEObjectOrProxy();
        if (obj != null && obj.eIsProxy()) {
          obj = EcoreUtil.resolve(obj, resourceSetForResolve);
        }
        if (obj instanceof Grammar && !obj.eIsProxy()) {
          return (Grammar) obj;
        } else {
          return null;
        }

      }
    };

    final Iterable<IEObjectDescription> grammarDescriptorsFromIndex = Access.getIResourceDescriptions().get().getExportedObjectsByType(XtextPackage.Literals.GRAMMAR);
    return Iterables.concat(Iterables.filter(Iterables.transform(grammarDescriptorsFromIndex, description2GrammarTransform), Predicates.notNull()), allGrammarsFromRegistry());
  }

  /**
   * Gets all grammars in the resource service provider registry.
   * <p>
   * The result contains no null entries.
   * </p>
   *
   * @return an iterable over all grammars in the resource service provider registry
   */
  public Iterable<Grammar> allGrammarsFromRegistry() {
    final IResourceServiceProvider.Registry reg = IResourceServiceProvider.Registry.INSTANCE;
    Map<String, Object> extensionToFactoryMap = reg.getExtensionToFactoryMap();
    Set<String> keySet = extensionToFactoryMap.keySet();

    Function<String, Grammar> function = new Function<String, Grammar>() {
      @Override
      public Grammar apply(final String from) {
        URI dummyUri = URI.createURI("foo:/foo." + from); //$NON-NLS-1$
        try {
          return reg.getResourceServiceProvider(dummyUri).get(IGrammarAccess.class).getGrammar();
          // CHECKSTYLE:OFF
        } catch (Exception e) {
          // CHECKSTYLE:ON
        }
        return null;
      }
    };
    return Iterables.filter(Iterables.transform(keySet, function), Predicates.notNull());
  }

}

