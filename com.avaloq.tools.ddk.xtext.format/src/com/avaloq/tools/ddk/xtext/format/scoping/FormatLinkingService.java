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
package com.avaloq.tools.ddk.xtext.format.scoping;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.ClasspathUriResolutionException;
import org.eclipse.xtext.resource.SynchronizedXtextResourceSet;

import com.avaloq.tools.ddk.xtext.format.format.Constant;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.FormatPackage;
import com.avaloq.tools.ddk.xtext.linking.AbstractDdkLinkingService;


/**
 * A linking service for the Format language.
 */
public class FormatLinkingService extends AbstractDdkLinkingService {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<EObject> getLinkedObjects(final EObject context, final EReference ref, final INode node) {
    if (ref == FormatPackage.Literals.FORMAT_CONFIGURATION__TARGET_GRAMMAR) {
      List<EObject> usedGrammars = getUsedGrammar(context.eResource().getResourceSet(), node);
      if (!usedGrammars.isEmpty()) {
        return usedGrammars;
      } else {
        return super.getLinkedObjects(context, ref, node);
      }
    } else if (ref == FormatPackage.Literals.FORMAT_CONFIGURATION__EXTENDED_FORMAT_CONFIGURATION) {
      List<EObject> extendedFormatConfigurations = getExtendedFormatConfiguration(context.eResource().getResourceSet(), node);
      if (!extendedFormatConfigurations.isEmpty()) {
        return extendedFormatConfigurations;
      } else {
        return super.getLinkedObjects(context, ref, node);
      }
    } else if ((ref == FormatPackage.Literals.INT_VALUE__REFERENCE || ref == FormatPackage.Literals.STRING_VALUE__REFERENCE)
        && !(context.eResource().getResourceSet() instanceof SynchronizedXtextResourceSet)) {
      List<EObject> res = super.getLinkedObjects(context, ref, node);
      if (res == null || res.isEmpty()) {
        return getConstant(context.eResource().getResourceSet(), node);
      } else {
        return res;
      }
    } else if (ref == FormatPackage.Literals.RULE__OVERRIDE) {
      return getExtendedFormatConfiguration(context.eResource().getResourceSet(), node);
    }

    return super.getLinkedObjects(context, ref, node);
  }

  /**
   * Tries to find {@link Constant} that my be defined in the same formatter or in any formatter that is extended by the current formatter.
   * An appropriate constant should be matched by comparing its name with the desired one.
   *
   * @param resourceSet
   *          to be used for loading
   * @param node
   *          parse subtree for the reference
   * @return A singleton list containing the desired constant, or an empty list if not found.
   */
  private List<EObject> getConstant(final ResourceSet resourceSet, final INode node) {
    String constantFullyQualifiedName = NodeModelUtils.getTokenText(node);
    String formatName = URI.createURI(constantFullyQualifiedName).trimFileExtension().toString();
    if (formatName != null) {
      FormatConfiguration result = loadExtendedFormatConfiguration(formatName, resourceSet);
      if (result != null) {
        EList<Constant> constants = result.getConstants();
        for (Constant constant : constants) {
          if (constantFullyQualifiedName.equals(formatName + "." + constant.getName())) {
            return Collections.<EObject> singletonList(constant);
          }
        }
      }
    }
    return Collections.emptyList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getGrammarName(final INode node) {
    return NodeModelUtils.getTokenText(node);
  }

  /**
   * Tries to find {@link FormatConfiguration} for the base formatter ({@code with} clause).
   *
   * @param resourceSet
   *          to be used for loading
   * @param node
   *          parse subtree for the reference
   * @return A singleton list containing the base format configuration, or an empty list if not found.
   */
  private List<EObject> getExtendedFormatConfiguration(final ResourceSet resourceSet, final INode node) {
    String formatName = NodeModelUtils.getTokenText(node);
    registerKnownEPackages(formatName, resourceSet);
    if (formatName != null) {
      FormatConfiguration result = loadExtendedFormatConfiguration(formatName, resourceSet);
      if (result != null) {
        return Collections.<EObject> singletonList(result);
      }
    }
    return Collections.emptyList();
  }

  /**
   * Tries to load the given {@link FormatConfiguration} from given resource set.
   *
   * @param formatName
   *          of the formatter configuration to load
   * @param resourceSet
   *          to use for loading
   * @return the {@link FormatConfiguration}, if loaded, or null otherwise
   */
  private FormatConfiguration loadExtendedFormatConfiguration(final String formatName, final ResourceSet resourceSet) {
    URI grammarURI = buildExtendedFormatConfigurationURI(formatName);
    try {
      final Resource resource = resourceSet.getResource(grammarURI, true);
      if (!resource.getContents().isEmpty()) {
        return (FormatConfiguration) resource.getContents().get(0);
      }
      return null;
    } catch (WrappedException e) {
      return null;
    } catch (ClasspathUriResolutionException e) {
      return null;
    }
  }

  /**
   * Creates a URI for given format name (equivalent to the grammar name).
   *
   * @param formatName
   *          for which URI should be crated
   * @return URI of the {@code .format} file
   */
  private URI buildExtendedFormatConfigurationURI(final String formatName) {
    URI formatURI = URI.createPlatformResourceURI(formatName, true);
    String[] formatSegments = formatName.split("\\.");
    formatURI = formatURI.trimFileExtension();
    formatURI = formatURI.appendFileExtension("core");
    formatURI = formatURI.appendSegment("src");
    formatURI = formatURI.appendSegments(formatSegments);
    formatURI = formatURI.trimSegments(1);
    formatURI = formatURI.appendSegment("formatting");
    formatURI = formatURI.appendSegment(formatSegments[formatSegments.length - 1]);
    formatURI = formatURI.appendFileExtension("format");
    return formatURI;
  }

}
