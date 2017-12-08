package com.avaloq.tools.ddk.xtext.expression.generator

import com.google.inject.Inject
import org.eclipse.emf.codegen.ecore.genmodel.GenClass
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType
import org.eclipse.emf.codegen.ecore.genmodel.GenModel
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.ENamedElement
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.impl.EPackageImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.scoping.IGlobalScopeProvider
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

class GenModelUtilX {

  @Inject
  extension Naming

  @Inject
  IGlobalScopeProvider globalScopeProvider
  @Inject
  ResourceDescriptionsProvider resourceDescriptionsProvider

  var Resource context

  def setResource(Resource context) {
    this.context = context
  }

  def /*cached*/ String qualifiedPackageInterfaceName(EPackage it) {
    val genPackage = genPackage()
    if (genPackage != null)
      return genPackage.qualifiedPackageInterfaceName
    else if (it.class != EPackageImpl)
      return it.class.interfaces.head.name
    return null
  }

  def String qualifiedSwitchClassName(EPackage it) {
    val genPackage = genPackage()
    if (genPackage != null && genPackage.literalsInterface)
      genPackage.utilitiesPackageName + "." + genPackage.switchClassName
    else
      qualifiedPackageInterfaceName().toJavaPackage + '.util.' + name.toFirstUpper + 'Switch' // heuristic
  }

  def /*cached*/ dispatch String literalIdentifier(EStructuralFeature it) {
    val eClass     = EContainingClass
    val ePackage   = eClass.EPackage
    val genPackage = ePackage.genPackage()
    if (genPackage != null && genPackage.literalsInterface)
       eClass.literalIdentifier() + "__" + name.format().toUpperCase()
    else
      ePackage.qualifiedPackageInterfaceName() + ".eINSTANCE.get" + eClass.name + "_" + name.toFirstUpper() + "()"
  }

  def /*cached*/ dispatch String literalIdentifier(EClass it) {
     val genPackage = EPackage.genPackage()
     if (genPackage != null && genPackage.literalsInterface)
       genPackage.qualifiedPackageInterfaceName + ".Literals." + name.format().toUpperCase()
     else
       EPackage.qualifiedPackageInterfaceName() + ".eINSTANCE.get" + name + "()"
  }

  // defined to simplify debugging generator problems
  def /*cached*/ dispatch String literalIdentifier(ENamedElement it) {
      "DOES_NOT_EXIST"
  }

  // defined to simplify debugging generator problems
  def /*cached*/ dispatch String literalIdentifier(Void it) {
      "DOES_NOT_EXIST"
  }

  // e.g. EcorePackage.ENAMED_ELEMENT
  def /*cached*/ String classifierIdLiteral(EClass it) {
    EPackage.qualifiedPackageInterfaceName() + "." + name.format().toUpperCase()
  }

  def /*cached*/ dispatch String instanceClassName(Void it) {
    ""
  }

  def /*cached*/ dispatch String instanceClassName(EClassifier it) {
    if (instanceClassName != null)
      return instanceClassName
    return name
  }

  def /*cached*/ dispatch String instanceClassName(EDataType it) {
    if (instanceClassName != null)
      return instanceClassName
    return genDataType(it).qualifiedInstanceClassName
  }

  def /*cached*/ dispatch String instanceClassName(EClass it) {
    if (instanceClassName != null)
      return instanceClassName
    return genClass(it).qualifiedInterfaceName
  }

  def /*cached*/ GenPackage genPackage(EModelElement it) {
    val ePackage = EcoreUtil2.getContainerOfType(it, EPackage)
    if (globalScopeProvider != null && context != null) {
      val scope = globalScopeProvider.getScope(context, GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES, null)
      if (scope != null && ePackage != null) {
        val desc = scope.getSingleElement(QualifiedName.create(ePackage.nsURI))
        if (desc != null) {
          return EcoreUtil.resolve(desc.EObjectOrProxy, context) as GenPackage
        } else {
          val descs = resourceDescriptionsProvider.getResourceDescriptions(context).getExportedObjects(GenModelPackage.Literals.GEN_PACKAGE, QualifiedName.create(ePackage.nsURI), false).iterator
          if (descs.hasNext)
            return EcoreUtil.resolve(descs.next.EObjectOrProxy, context) as GenPackage
        }
      }
    }
    val resourceSet = if (context != null) context.resourceSet else if (it.eResource.resourceSet != null) it.eResource.resourceSet else new ResourceSetImpl
    return if (ePackage != null) GenModelUtil2.findGenPackage(ePackage, resourceSet) else null
  }

  def /*cached*/ GenModel genModel(EModelElement it) {
    val genPackage = genPackage(it)
    return if (genPackage != null) genPackage.genModel else null
  }

  def /*cached*/ GenClass genClass(EClass it) {
    val genPackage = genPackage(it)
    return if (genPackage != null) genPackage.genModel.findGenClassifier(it) as GenClass else null
  }

  def /*cached*/ GenDataType genDataType(EDataType it) {
    val genPackage = genPackage(it)
    return if (genPackage != null) genPackage.genModel.findGenClassifier(it) as GenDataType else null;
  }

  def /*cached*/ String format(String name) {
    GenModelUtil2.format(name)
  }

}
