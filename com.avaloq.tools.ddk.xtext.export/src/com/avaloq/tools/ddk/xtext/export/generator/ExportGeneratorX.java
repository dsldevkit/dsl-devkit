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
package com.avaloq.tools.ddk.xtext.export.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Grammar;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Interface;
import com.avaloq.tools.ddk.xtext.export.export.UserData;
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtil2;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtil;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.common.collect.ListMultimap;
import com.google.inject.Inject;


@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ExportGeneratorX {

  private static final int URI_PACKAGE_START_INDEX = 3;

  @Inject
  private Naming naming;

  public String getName(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    return uri.trimFileExtension().lastSegment();
  }

  public Grammar getGrammar(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // Grammar should be set correctly for export extensions, not yet for normal export sources
    if (model.getTargetGrammar() != null) {
      return model.getTargetGrammar();
    }
    final org.eclipse.emf.ecore.resource.Resource grammarResource = model.eResource().getResourceSet().getResource(
        uri.trimSegments(1).appendSegment(uri.trimFileExtension().lastSegment() + ".xtext"), true);
    return grammarResource != null && !grammarResource.getContents().isEmpty()
        ? (Grammar) grammarResource.getContents().get(0)
        : null;
  }

  public String getExportedNamesProvider(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(URI_PACKAGE_START_INDEX, uri.segmentCount() - 1)) + ".naming." + getName(model) + "ExportedNamesProvider";
  }

  public String getResourceDescriptionManager(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(URI_PACKAGE_START_INDEX, uri.segmentCount() - 1)) + ".resource." + getName(model) + "ResourceDescriptionManager";
  }

  public String getResourceDescriptionManager(final Grammar grammar) {
    return naming.toJavaPackage(grammar.getName()) + ".resource." + naming.toSimpleName(grammar.getName()) + "ResourceDescriptionManager";
  }

  public String getResourceDescriptionStrategy(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(URI_PACKAGE_START_INDEX, uri.segmentCount() - 1)) + ".resource." + getName(model) + "ResourceDescriptionStrategy";
  }

  public String getResourceDescriptionConstants(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(URI_PACKAGE_START_INDEX, uri.segmentCount() - 1)) + ".resource." + getName(model) + "ResourceDescriptionConstants";
  }

  public String getFingerprintComputer(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(URI_PACKAGE_START_INDEX, uri.segmentCount() - 1)) + ".resource." + getName(model) + "FingerprintComputer";
  }

  public String getFragmentProvider(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(URI_PACKAGE_START_INDEX, uri.segmentCount() - 1)) + ".resource." + getName(model) + "FragmentProvider";
  }

  public String getExportFeatureExtension(final ExportModel model) {
    final org.eclipse.emf.common.util.URI uri = model.eResource().getURI();
    // TODO we still need to add a package to the models. Extension models already have a name in contrast to cases above
    return String.join(".", uri.segmentsList().subList(URI_PACKAGE_START_INDEX, uri.segmentCount() - 1)) + ".resource." + model.getName() + "ExportFeatureExtension";
  }

  /**
   * Return the export specification for a type's supertype, if any, or null otherwise.
   *
   * @param it
   *          the export specification
   * @return the export specification for the supertype, or {@code null}
   */
  public Export superType(final Export it) {
    if (it.getType().getESuperTypes().isEmpty()) {
      return null;
    } else {
      return exportForType((ExportModel) it.eContainer(), it.getType().getESuperTypes().get(0));
    }
  }

  /**
   * Return the export specification for a given type.
   *
   * @param it
   *          the export model
   * @param type
   *          the type to look for
   * @return the matching export, or {@code null}
   */
  public Export exportForType(final ExportModel it, final EClassifier type) {
    return it.getExports().stream()
        .filter(c -> c.getType().getName().equals(type.getName()) && c.getType().getEPackage().getNsURI().equals(type.getEPackage().getNsURI()))
        .findFirst()
        .orElse(null);
  }

  /**
   * Return a combined list of all user data specifications; including those on supertypes.
   *
   * <p>Public dispatcher for the dispatch methods.</p>
   *
   * @param it
   *          the export or {@code null}
   * @return combined list of user data
   * @throws IllegalArgumentException
   *           if the parameter type is not handled
   */
  public List<UserData> allUserData(final Object it) {
    if (it instanceof Export e) {
      return _allUserData(e);
    } else if (it == null) {
      return _allUserData((Void) null);
    } else {
      throw new IllegalArgumentException("Unhandled parameter type: " + it);
    }
  }

  /**
   * Return a combined list of all user data specifications; including those on supertypes.
   *
   * @param it
   *          the export
   * @return combined list of user data
   */
  protected List<UserData> _allUserData(final Export it) {
    final List<UserData> result = allUserData(superType(it));
    result.addAll(it.getUserData());
    return result;
  }

  /**
   * Sentinel for the above.
   *
   * @param it
   *          unused sentinel parameter
   * @return empty list
   */
  protected List<UserData> _allUserData(final Void it) {
    return new ArrayList<>();
  }

  /**
   * Return all the interface specification for the supertypes of a type.
   *
   * @param it
   *          the interface specification
   * @param type
   *          the EClass type
   * @return list of super interfaces
   */
  public List<Interface> getSuperInterfaces(final Interface it, final EClass type) {
    if (type.getESuperTypes().isEmpty()) {
      return new ArrayList<>();
    } else {
      return getInterfacesForType((ExportModel) it.eContainer(), type.getESuperTypes().get(0));
    }
  }

  /**
   * Return all interface specifications that apply to a certain type; including those that are defined for supertypes.
   *
   * @param it
   *          the export model
   * @param type
   *          the EClass type
   * @return list of matching interfaces
   */
  public List<Interface> getInterfacesForType(final ExportModel it, final EClass type) {
    final List<Interface> filtered = it.getInterfaces().stream()
        .filter(f -> f.getType() == type)
        .collect(java.util.stream.Collectors.toList());
    final List<Interface> result = filtered.isEmpty() ? new ArrayList<>() : new ArrayList<>(List.of(filtered.get(0)));
    if (!type.getESuperTypes().isEmpty()) {
      result.addAll(getInterfacesForType(it, type.getESuperTypes().get(0)));
    }
    return result;
  }

  /**
   * Returns a constant name for an Attribute field.
   *
   * @param attribute
   *          the attribute
   * @param exportType
   *          the export type
   * @return the constant name
   */
  public String constantName(final EAttribute attribute, final EClass exportType) {
    return (GenModelUtil2.format(exportType.getName()) + "__" + GenModelUtil2.format(attribute.getName())).toUpperCase();
  }

  /**
   * Returns a constant name for a UserData field.
   *
   * @param data
   *          the user data
   * @param exportType
   *          the export type
   * @return the constant name
   */
  public String constantName(final UserData data, final EClass exportType) {
    return (GenModelUtil2.format(exportType.getName()) + "__" + GenModelUtil2.format(data.getName())).toUpperCase();
  }

  /**
   * Sort exports according to package and type (more specific rules must come first).
   *
   * @param exports
   *          exports to sort
   * @return sorted map of all exports
   */
  public ListMultimap<EPackage, Export> sortedExportsByEPackage(final Collection<Export> exports) {
    return EClassComparator.sortedEPackageGroups(exports, e -> e.getType());
  }

  /**
   * Returns a type map for the given exports.
   *
   * @param exports
   *          export objects to map
   * @param grammar
   *          Xtext grammar
   * @return mappings
   */
  public Map<EClass, Export> typeMap(final Collection<Export> exports, final Grammar grammar) {
    return GeneratorUtil.typeMap(exports, grammar, e -> e.getType());
  }
}
