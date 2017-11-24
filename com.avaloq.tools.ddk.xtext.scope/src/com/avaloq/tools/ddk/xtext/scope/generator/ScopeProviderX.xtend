/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. it program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies it distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.scope.generator

import com.avaloq.tools.ddk.xtext.expression.expression.Expression
import com.avaloq.tools.ddk.xtext.expression.expression.FeatureCall
import com.avaloq.tools.ddk.xtext.expression.generator.CodeGenerationX
import com.avaloq.tools.ddk.xtext.expression.generator.ExpressionExtensionsX
import com.avaloq.tools.ddk.xtext.expression.generator.GeneratorUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.avaloq.tools.ddk.xtext.scope.ScopeUtil
import com.avaloq.tools.ddk.xtext.scope.scope.Injection
import com.avaloq.tools.ddk.xtext.scope.scope.NamedScopeExpression
import com.avaloq.tools.ddk.xtext.scope.scope.NamingDefinition
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeDefinition
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeModel
import com.avaloq.tools.ddk.xtext.scope.scope.ScopeRule
import com.google.inject.Inject
import java.util.Collection
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.ENamedElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature

class ScopeProviderX {

  @Inject
  extension Naming
  @Inject
  extension GeneratorUtilX
  @Inject
  extension CodeGenerationX
  @Inject
  extension ExpressionExtensionsX

  /*
   * CODE GENERATION
   */
  def getScopeProvider(ScopeModel model) {
    model.name.toJavaPackage() + ".scoping." + model.name.toSimpleName() + "ScopeProvider"
  }

  def getScopeNameProvider(ScopeModel model) {
    model.name.toJavaPackage() + ".scoping." + model.name.toSimpleName() + "ScopeNameProvider"
  }

  // returns the name of the scope method generated for the given scope definition
  def String scopeMethodName(ScopeDefinition it) {
    getScopeName() + '_' + (if (targetType != null) targetType.EPackage.name + '_' + targetType.name else contextType.EPackage.name + '_' + contextType.name + '_' + reference.name)
  }

  def String locatorString(EObject it) {
    location().split('/').last().javaEncode()
  }

  def String calledFeature(FeatureCall it) {
    type.id.head
  }

  def EStructuralFeature feature(FeatureCall it) {
    scopeType().getEStructuralFeature(calledFeature())
  }

  /*
   * SCOPE RULES
   */
  def dispatch List<ScopeRule> allScopeRules(Void it) {
    newArrayList()
  }

  def dispatch List<ScopeRule> allScopeRules(ScopeDefinition it) {
    getModel().collectAllScopeRules(it)
  }

  def private List<ScopeRule> collectAllScopeRules(ScopeModel it, ScopeDefinition ^def) {
    val d = scopes.filter(d|d.isEqual(^def))
    val myScopeRules = if (d == null) newArrayList else d.map[rules].flatten
    val result =
      if (includedScopes.isEmpty)
        newArrayList
       else
        includedScopes.map[collectAllScopeRules(def)].flatten().toList
    result.addAll(myScopeRules)
    result
  }

  def List<ScopeRule> sortedRules(Collection<ScopeRule> it) {
    ScopingGeneratorUtil.sortedRules(it)
  }

  def Set<ScopeRule> filterUniqueRules(List<ScopeRule> it) {
    map(r|findFirst(r2|r2.hasSameContext(r))).toSet()
  }

  def dispatch boolean isEqual(ScopeRule a, ScopeRule b) {
    a.hasSameContext(b)
    // && ((a.name == null) == (b.name == null)) && (a.name == null || a.name.matches (b.name))
    && a.context.guard.serialize() == b.context.guard.serialize()
  }

  def boolean hasSameContext(ScopeRule a, ScopeRule b) {
    a.ruleSignature() == b.ruleSignature()
  }

  // Hrmph. Use naming here, otherwise we'll get strange (and wrong) results in the GenerateAllAPSLs workflow for netwStruct?!
  def private /*cached*/ String ruleSignature(ScopeRule s) {
    ScopeUtil.getSignature(s)
  }

  /*
   * SCOPE DEFINITIONS
   */
  // returns the list of all local and inherited scope definition (skipping any shadowed or extended scope definitions)
  def dispatch List<ScopeDefinition> allScopes(ScopeModel it) {
    val myScopes = it.scopes
    val result = if (it.includedScopes.isEmpty) newArrayList else it.includedScopes.map[allScopes()].flatten.toList
    result.removeIf(s|myScopes.hasScope(s))
    result.addAll(myScopes)
    result
  }

  def dispatch List<ScopeDefinition> allScopes(Void it) {
    newArrayList
  }

  def String getScopeName(ScopeDefinition it) {
    if (it.name == null) 'scope' else it.name
  }

  def boolean hasScope(List<ScopeDefinition> list, ScopeDefinition scope) {
    if (list.isEmpty) false else !(list.filter(s|s.isEqual(scope)).isEmpty)
  }

  def dispatch boolean isEqual(ScopeDefinition a, ScopeDefinition b) {
    a.getScopeName() == b.getScopeName() && a.targetType.isEqual(b.targetType) && a.reference.isEqual(b.reference)
  }

  /*
   * SCOPE TYPE
   */
  def dispatch EClass scopeType(ScopeDefinition it) {
    if (reference != null) reference.EReferenceType else targetType
  }

  def dispatch EClass scopeType(ScopeRule it) {
    getScope().scopeType()
  }

  def dispatch EClass scopeType(Expression it) {
    if (getScope() != null) getScope().scopeType() else getNamingDef().type
  }

  def ENamedElement typeOrRef(ScopeDefinition it) {
    if (reference != null) reference else targetType
  }

  def EReference contextRef(ScopeRule it) {
    getScope().reference
  }

  /*
   * Injections
   */
  // returns the list of all local and inherited injections (skipping any shadowed injections)
  def dispatch List<Injection> allInjections(ScopeModel it) {
    val myInjections = it.injections
    val result = if (it.includedScopes.isEmpty) newArrayList else it.includedScopes.map[allInjections()].flatten.toList
    result.removeIf(i|myInjections.hasInjection(i))
    result.addAll(myInjections)
    result
  }

  def dispatch List<Injection> allInjections(Void it) {
    newArrayList
  }

  def boolean hasInjection(List<Injection> list, Injection injection) {
    if (list.isEmpty) false else !(list.filter(i|i.isEqual(injection)).isEmpty)
  }

  def dispatch boolean isEqual(Injection a, Injection b) {
    a.type == b.type && a.name == b.name
  }

  /*
   * SCOPE EXPRESSIONS
   */
  def boolean isCaseInsensitive(NamedScopeExpression it) {
    ScopingGeneratorUtil.isCaseInsensitive(it)
  }

  /*
   * ECONTAINER
   */
  def ScopeModel getModel(EObject it) {
    it.eResource().contents.head as ScopeModel
  }

  def /*cached*/ ScopeDefinition getScope(EObject it) {
    eContainer(ScopeDefinition)
  }

  def /*cached*/ NamingDefinition getNamingDef(EObject it) {
    eContainer(NamingDefinition)
  }

  def <T extends EObject> T eContainer(EObject it, Class<T> type) {
    if (it == null) return null
    else if (type.isInstance(it)) it as T
    else eContainer().eContainer(type)
  }

  /*
   * ECORE
   */
  def dispatch boolean isEqual(EClass a, EClass b) {
    a == b || (a != null && b != null && a.name == b.name && a.EPackage.nsURI == b.EPackage.nsURI)
  }

  def dispatch boolean isEqual(Void a, Void b) {
    true
  }

  def dispatch boolean isEqual(EObject a, Void b) {
    false
  }

  def dispatch boolean isEqual(Void a, EObject b) {
    false
  }

  def dispatch boolean isEqual(EReference a, EReference b) {
    a == b || (a != null && b != null && a.name == b.name && a.EContainingClass.isEqual(b.EContainingClass))
  }

}