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
import org.eclipse.emf.ecore.EPackage.Registry
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.impl.EPackageImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.scoping.IGlobalScopeProvider
import org.eclipse.emf.ecore.EObject

class GenModelUtilX {

  @Inject
  extension Naming

  @Inject
  IGlobalScopeProvider globalScopeProvider
  @Inject
  ResourceDescriptionsProvider resourceDescriptionsProvider

  /**
   * The current model resource used for GenPackage lookups. This utility is normally a Guice singleton, so
   * multiple Xtext workers (the editor reconciler, the workspace builder, content assist, etc.) share the same
   * instance and would otherwise race on a single field. Storing the context per-thread isolates each worker's
   * lookups from the others.
   */
  val ThreadLocal<Resource> context = new ThreadLocal<Resource>()

  def setResource(Resource context) {
    this.context.set(context)
  }

  def Resource getContext() {
    context.get()
  }

  def /*cached*/ String qualifiedPackageInterfaceName(EPackage it) {
    val genPackage = genPackage()
    if (genPackage !== null)
      return genPackage.qualifiedPackageInterfaceName
    else if (it.class != EPackageImpl)
      return it.class.interfaces.head.name
    // GenModel lookup failed (typically because the editor reconciler runs before the .genmodel index is
    // populated). Resolve via the EMF registry: the plugin-registered EPackage for this nsURI is a Java
    // class whose first interface is the generated *Package interface.
    if (nsURI !== null) {
      val registered = Registry.INSTANCE.getEPackage(nsURI)
      if (registered !== null && registered !== it && registered.class != EPackageImpl) {
        val interfaces = registered.class.interfaces
        if (!interfaces.empty)
          return interfaces.head.name
      }
    }
    return null
  }

  def String qualifiedSwitchClassName(EPackage it) {
    val genPackage = genPackage()
    if (genPackage !== null && genPackage.literalsInterface)
      genPackage.utilitiesPackageName + "." + genPackage.switchClassName
    else
      qualifiedPackageInterfaceName().toJavaPackage + '.util.' + name.toFirstUpper + 'Switch' // heuristic
  }

  def /*cached*/ dispatch String literalIdentifier(EStructuralFeature it) {
    val eClass = EContainingClass
    if (eClass === null) {
      // EStructuralFeature with no containing class - an unresolved EReference proxy. This happens during
      // editor reconciliation: a .scope file like `scope T#ref { ... }` records `s.reference` as a cross
      // reference whose scope is `T.eAllReferences`; if `T` itself is mid-resolve (e.g. the imported
      // EPackage is not yet indexed), `s.reference` returns the still-proxy EReference, which has no
      // container until linking completes. The generated body cannot reference this feature; emit a
      // sentinel so the body string parses and the reconciler can keep going. The next reconcile cycle
      // will retry against a fully-linked model and produce the correct literal.
      return "DOES_NOT_EXIST"
    }
    val ePackage   = eClass.EPackage
    val genPackage = ePackage.genPackage()
    if (genPackage !== null && genPackage.literalsInterface)
       eClass.literalIdentifier() + "__" + name.format().toUpperCase()
    else
      ePackage.qualifiedPackageInterfaceName() + ".eINSTANCE.get" + eClass.name + "_" + name.toFirstUpper() + "()"
  }

  def /*cached*/ dispatch String literalIdentifier(EClass it) {
     val genPackage = EPackage.genPackage()
     if (genPackage !== null && genPackage.literalsInterface)
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
    if (instanceClassName !== null)
      return instanceClassName
    val fromRegistry = instanceClassNameFromRegistry(it)
    if (fromRegistry !== null)
      return fromRegistry
    if (name !== null)
      return name
    return EObject.name
  }

  def /*cached*/ dispatch String instanceClassName(EDataType it) {
    if (instanceClassName !== null)
      return instanceClassName
    val fromRegistry = instanceClassNameFromRegistry(it)
    if (fromRegistry !== null)
      return fromRegistry
    val gdt = genDataType(it)
    if (gdt !== null)
      return gdt.qualifiedInstanceClassName
    // Unresolvable classifier (typically a proxy seen during editor reconciliation before linking
    // completes). Fall back to Object so the inferred Java method signature is well-formed and the
    // reconciler can keep going; the next reconcile cycle will retry with the resolved type.
    return Object.name
  }

  def /*cached*/ dispatch String instanceClassName(EClass it) {
    if (instanceClassName !== null)
      return instanceClassName
    val fromRegistry = instanceClassNameFromRegistry(it)
    if (fromRegistry !== null)
      return fromRegistry
    val gc = genClass(it)
    if (gc !== null)
      return gc.qualifiedInterfaceName
    // Unresolvable EClass (typically a proxy seen during editor reconciliation before linking
    // completes). Fall back to EObject so the inferred Java method signature is well-formed and the
    // reconciler can keep going; the next reconcile cycle will retry with the resolved type.
    return EObject.name
  }

  /**
   * Returns the Java instance class name for the given classifier by looking its EPackage up in the EMF
   * registry. Workspace {@code .ecore} files load their EClasses as bare {@link EPackageImpl} instances whose
   * {@code instanceClassName} is unset; the corresponding plugin-registered EPackage (loaded from the generated
   * Java class) carries the field on every EClassifier. Cross-referencing the registry by nsURI lets us resolve
   * the Java name without needing the workspace GenModel to be indexed - which the inferrer cannot rely on
   * during editor reconciliation, since the {@code .genmodel} index population lags behind the open file.
   *
   * @param it
   *          the classifier whose Java name is needed, may be {@code null}
   * @return the Java instance class name, or {@code null} if the EPackage is not in the registry or the
   *         registry counterpart cannot be matched
   */
  def private String instanceClassNameFromRegistry(EClassifier it) {
    val ePackage = EPackage
    if (ePackage === null) {
      return null
    }
    val nsURI = ePackage.nsURI
    if (nsURI === null) {
      return null
    }
    val registered = Registry.INSTANCE.getEPackage(nsURI)
    if (registered === null || registered === ePackage) {
      // Either the package is not in the registry, or what we already have IS the registry copy (and we
      // already checked its instanceClassName field above).
      return null
    }
    val counterpart = registered.getEClassifier(name)
    if (counterpart === null) {
      return null
    }
    counterpart.instanceClassName
  }

  def /*cached*/ GenPackage genPackage(EModelElement it) {
    val ePackage = EcoreUtil2.getContainerOfType(it, EPackage)
    val ctx = context.get()
    if (globalScopeProvider !== null && ctx !== null) {
      val scope = globalScopeProvider.getScope(ctx, GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES, null)
      if (scope !== null && ePackage !== null) {
        val desc = scope.getSingleElement(QualifiedName.create(ePackage.nsURI))
        if (desc !== null) {
          return EcoreUtil.resolve(desc.EObjectOrProxy, ctx) as GenPackage
        } else {
          val resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(ctx)
          val descs = resourceDescriptions.getExportedObjects(GenModelPackage.Literals.GEN_PACKAGE, QualifiedName.create(ePackage.nsURI), false).iterator
          if (descs.hasNext) {
            return EcoreUtil.resolve(descs.next.EObjectOrProxy, ctx) as GenPackage
          }
          // In case Xcore is installed GenPackages will be indexed using GenPackage#getQualifiedPackageName()
          for (candidate : resourceDescriptions.getExportedObjectsByType(GenModelPackage.Literals.GEN_PACKAGE).filter[name.lastSegment == ePackage.name]) {
            val resolvedCanidate = EcoreUtil.resolve(candidate.EObjectOrProxy, ctx) as GenPackage
            if (!resolvedCanidate.eIsProxy && resolvedCanidate.getEcorePackage == ePackage) {
              return resolvedCanidate
            }
          }
        }
      }
    }
    val resourceSet = if (ctx !== null) ctx.resourceSet else if (it.eResource.resourceSet !== null) it.eResource.resourceSet else new ResourceSetImpl
    return if (ePackage !== null) GenModelUtil2.findGenPackage(ePackage, resourceSet) else null
  }

  def /*cached*/ GenModel genModel(EModelElement it) {
    val genPackage = genPackage(it)
    return if (genPackage !== null) genPackage.genModel else null
  }

  def /*cached*/ GenClass genClass(EClass it) {
    val genPackage = genPackage(it)
    return if (genPackage !== null) genPackage.genModel.findGenClassifier(it) as GenClass else null
  }

  def /*cached*/ GenDataType genDataType(EDataType it) {
    val genPackage = genPackage(it)
    return if (genPackage !== null) genPackage.genModel.findGenClassifier(it) as GenDataType else null;
  }

  def /*cached*/ String format(String name) {
    GenModelUtil2.format(name)
  }

}
