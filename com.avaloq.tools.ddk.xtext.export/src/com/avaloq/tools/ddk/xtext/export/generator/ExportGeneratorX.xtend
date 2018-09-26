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

package com.avaloq.tools.ddk.xtext.export.generator

import com.avaloq.tools.ddk.xtext.export.export.Export
import com.avaloq.tools.ddk.xtext.export.export.ExportModel
import com.avaloq.tools.ddk.xtext.export.export.Interface
import com.avaloq.tools.ddk.xtext.export.export.UserData
import com.avaloq.tools.ddk.xtext.expression.generator.EClassComparator
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtil2
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtil
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.common.collect.ListMultimap
import com.google.inject.Inject
import java.util.Collection
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtext.Grammar

class ExportGeneratorX {

  @Inject
  extension Naming

  def String getName(ExportModel model) {
    val uri = model.eResource().getURI();
    return uri.trimFileExtension().lastSegment();
  }

  def Grammar getGrammar(ExportModel model) {
    val uri = model.eResource.URI
    // Grammar should be set correctly for export extensions, not yet for normal export sources
    if(model.targetGrammar !== null) {
      return model.targetGrammar;
    }
    val grammarResource = model.eResource.resourceSet.getResource(uri.trimSegments(1).appendSegment(uri.trimFileExtension.lastSegment + '.xtext'), true)
    return grammarResource?.contents.head as Grammar
  }

  def String getExportedNamesProvider(ExportModel model) {
    val uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(3, uri.segmentCount() - 1)) + ".naming." + getName(model) + "ExportedNamesProvider";
  }

  def String getResourceDescriptionManager(ExportModel model) {
    val uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(3, uri.segmentCount() - 1)) + ".resource." + getName(model) + "ResourceDescriptionManager";
  }

  def String getResourceDescriptionManager(Grammar grammar) {
    return grammar.name.toJavaPackage + ".resource." + grammar.name.toSimpleName + "ResourceDescriptionManager";
  }

  def String getResourceDescriptionStrategy(ExportModel model) {
    val uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(3, uri.segmentCount() - 1)) + ".resource." + getName(model) + "ResourceDescriptionStrategy";
  }

  def String getResourceDescriptionConstants(ExportModel model) {
    val uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(3, uri.segmentCount() - 1)) + ".resource." + getName(model) + "ResourceDescriptionConstants";
  }

  def String getFingerprintComputer(ExportModel model) {
    val uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(3, uri.segmentCount() - 1)) + ".resource." + getName(model) + "FingerprintComputer";
  }

  def String getFragmentProvider(ExportModel model) {
    val uri = model.eResource().getURI();
    // TODO this is a hack; to support modularization we should probably add name to export models (as with scope models)
    return String.join(".", uri.segmentsList().subList(3, uri.segmentCount() - 1)) + ".resource." + getName(model) + "FragmentProvider";
  }

  def String getExportFeatureExtension(ExportModel model) {
    val uri = model.eResource().getURI();
    // TODO we still need to add a package to the models. Extension models already have a name in contrast to cases above
    return String.join(".", uri.segmentsList().subList(3, uri.segmentCount() - 1)) + ".resource." + model.name + "ExportFeatureExtension";
  }

  /**
   * Return the export specification for a type's supertype, if any, or null otherwise.
   */
  def Export superType(Export it) {
    if (type.ESuperTypes.isEmpty) null else (eContainer() as ExportModel).exportForType(type.ESuperTypes.get(0))
  }

  /**
   * Return the export specification for a given type.
   */
  def Export exportForType(ExportModel it, EClassifier type) {
      exports.findFirst(c|c.type.name == type.name && c.type.EPackage.nsURI == type.EPackage.nsURI)
  }

  /**
   * Return a combined list of all user data specifications; including those on supertypes.
   */
  def dispatch List<UserData> allUserData(Export it) {
    val result = superType.allUserData()
    result.addAll(it.userData)
    return result
  }

  /**
   * Sentinel for the above.
   */
  def dispatch List<UserData> allUserData(Void it) {
    newArrayList
  }

  /**
   * Return all the interface specification for the supertypes of a type.
   */
  def List<Interface> getSuperInterfaces(Interface it, EClass type) {
    if (type.ESuperTypes.isEmpty) newArrayList else (eContainer() as ExportModel).getInterfacesForType(type.ESuperTypes.get(0))
  }

  /**
   * Return all interface specifications that apply to a certain type; including those that are defined for supertypes.
   */
  def List<Interface> getInterfacesForType(ExportModel it, EClass type) {
    val f = interfaces.filter(f|f.type == type)
    val result = if (f.isEmpty) newArrayList else newArrayList(f.get(0))
    if (!type.ESuperTypes.isEmpty) {
      result.addAll(getInterfacesForType(type.ESuperTypes.get(0)))
    }
    return result
  }

  /**
   * Returns a constant name for an Attribute field
   */
  def String constantName(EAttribute attribute, EClass exportType) {
     (GenModelUtil2.format(exportType.name) + "__" + GenModelUtil2.format(attribute.name)).toUpperCase()
  }


  /**
   * Returns a constant name for a UserData field
   */
  def String constantName(UserData data, EClass exportType) {
    (GenModelUtil2.format(exportType.name) + "__" + GenModelUtil2.format(data.name)).toUpperCase()
  }

  /**
   * Sort exports according to package and type (more specific rules must come first).
   *
   * @param exports
   *          exports to sort
   * @return sorted map of all exports
   */
  def ListMultimap<EPackage, Export> sortedExportsByEPackage(Collection<Export> exports) {
    return EClassComparator.sortedEPackageGroups(exports, [e|e.type])
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
  def Map<EClass, Export> typeMap(Collection<Export> exports, Grammar grammar) {
    return GeneratorUtil.typeMap(exports, grammar, [e|e.type])
  }

}
