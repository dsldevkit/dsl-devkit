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
import java.util.Objects;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.Grammar;

import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.Interface;
import com.avaloq.tools.ddk.xtext.export.export.UserData;
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator;
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtil2;
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtil;
import com.avaloq.tools.ddk.xtext.expression.generator.Naming;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.inject.Inject;


@SuppressWarnings({"nls", "checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class ExportGeneratorX {

  /** Index of the first URI segment that belongs to the model's package prefix. */
  private static final int FIRST_PACKAGE_SEGMENT = 3;

  @Inject
  private Naming naming;

  public String getName(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return uri.trimFileExtension().lastSegment();
  }

  public Grammar getGrammar(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    // Grammar should be set correctly for export extensions, not yet for normal export sources
    if (model.getTargetGrammar() != null) {
      return model.getTargetGrammar();
    }
    final URI grammarUri = uri.trimSegments(1).appendSegment(uri.trimFileExtension().lastSegment() + ".xtext");
    final Resource grammarResource = model.eResource().getResourceSet().getResource(grammarUri, true);
    final EList<EObject> contents = grammarResource != null ? grammarResource.getContents() : null;
    return (Grammar) Iterables.getFirst(contents, null);
  }

  public List<String> getPrefix(final URI uri) {
    // TODO we still need to add a package to the models. Extension models already have a name in contrast to cases above
    if (uri.segmentsList().size() > FIRST_PACKAGE_SEGMENT) {
      return uri.segmentsList().subList(FIRST_PACKAGE_SEGMENT, uri.segmentCount() - 1);
    } else {
      return uri.segmentsList().subList(uri.segmentCount() - 2, uri.segmentCount() - 1);
    }
  }

  public String getExportedNamesProvider(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return String.join(".", getPrefix(uri)) + ".naming." + getName(model) + "ExportedNamesProvider";
  }

  public String getResourceDescriptionManager(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return String.join(".", getPrefix(uri)) + ".resource." + getName(model) + "ResourceDescriptionManager";
  }

  public String getResourceDescriptionManager(final Grammar grammar) {
    return naming.toJavaPackage(grammar.getName()) + ".resource." + naming.toSimpleName(grammar.getName()) + "ResourceDescriptionManager";
  }

  public String getResourceDescriptionStrategy(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return String.join(".", getPrefix(uri)) + ".resource." + getName(model) + "ResourceDescriptionStrategy";
  }

  public String getResourceDescriptionConstants(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return String.join(".", getPrefix(uri)) + ".resource." + getName(model) + "ResourceDescriptionConstants";
  }

  public String getFingerprintComputer(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return String.join(".", getPrefix(uri)) + ".resource." + getName(model) + "FingerprintComputer";
  }

  public String getFragmentProvider(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return String.join(".", getPrefix(uri)) + ".resource." + getName(model) + "FragmentProvider";
  }

  public String getExportFeatureExtension(final ExportModel model) {
    final URI uri = model.eResource().getURI();
    return String.join(".", getPrefix(uri)) + ".resource." + model.getName() + "ExportFeatureExtension";
  }

  /**
   * Return the export specification for a type's supertype, if any, or null otherwise.
   *
   * @param export
   *          export specification whose supertype is wanted
   * @return the supertype's export specification, or {@code null} if there is none
   */
  public Export superType(final Export export) {
    if (export.getType().getESuperTypes().isEmpty()) {
      return null;
    }
    return exportForType((ExportModel) export.eContainer(), export.getType().getESuperTypes().get(0));
  }

  /**
   * Return the export specification for a given type.
   *
   * @param model
   *          export model to search
   * @param type
   *          type whose export specification is wanted
   * @return the export specification for the type, or {@code null} if there is none
   */
  public Export exportForType(final ExportModel model, final EClassifier type) {
    return model.getExports().stream()
        .filter(c -> Objects.equals(c.getType().getName(), type.getName())
            && Objects.equals(c.getType().getEPackage().getNsURI(), type.getEPackage().getNsURI()))
        .findFirst().orElse(null);
  }

  /**
   * Return a combined list of all user data specifications; including those on supertypes.
   *
   * @param export
   *          export specification whose user data is wanted
   * @return combined list of all user data specifications
   */
  protected List<UserData> _allUserData(final Export export) {
    final List<UserData> result = allUserData(superType(export));
    result.addAll(export.getUserData());
    return result;
  }

  /**
   * Sentinel for the above.
   *
   * @param export
   *          always {@code null}
   * @return an empty, modifiable list
   */
  protected List<UserData> _allUserData(final Void export) {
    return new ArrayList<>();
  }

  /**
   * Return all the interface specification for the supertypes of a type.
   *
   * @param declaration
   *          interface specification providing the containing export model
   * @param type
   *          type whose supertypes' interface specifications are wanted
   * @return the interface specifications of the type's supertypes
   */
  public List<Interface> getSuperInterfaces(final Interface declaration, final EClass type) {
    if (type.getESuperTypes().isEmpty()) {
      return new ArrayList<>();
    }
    return getInterfacesForType((ExportModel) declaration.eContainer(), type.getESuperTypes().get(0));
  }

  /**
   * Return all interface specifications that apply to a certain type; including those that are defined for supertypes.
   *
   * @param model
   *          export model to search
   * @param type
   *          type whose interface specifications are wanted
   * @return all interface specifications that apply to the type
   */
  public List<Interface> getInterfacesForType(final ExportModel model, final EClass type) {
    final List<Interface> declarations = model.getInterfaces().stream().filter(f -> Objects.equals(f.getType(), type)).toList();
    final List<Interface> result = new ArrayList<>();
    if (!declarations.isEmpty()) {
      result.add(declarations.get(0));
    }
    if (!type.getESuperTypes().isEmpty()) {
      result.addAll(getInterfacesForType(model, type.getESuperTypes().get(0)));
    }
    return result;
  }

  /**
   * Returns a constant name for an Attribute field.
   *
   * @param attribute
   *          attribute to name
   * @param exportType
   *          type the attribute is exported for
   * @return the constant name for the attribute field
   */
  public String constantName(final EAttribute attribute, final EClass exportType) {
    return (GenModelUtil2.format(exportType.getName()) + "__" + GenModelUtil2.format(attribute.getName())).toUpperCase();
  }

  /**
   * Returns a constant name for a UserData field.
   *
   * @param data
   *          user data to name
   * @param exportType
   *          type the user data is exported for
   * @return the constant name for the user data field
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
    return EClassComparator.sortedEPackageGroups(exports, Export::getType);
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
    return GeneratorUtil.typeMap(exports, grammar, Export::getType);
  }

  public List<UserData> allUserData(final Export export) {
    if (export != null) {
      return _allUserData(export);
    } else {
      return _allUserData((Void) null);
    }
  }

}
