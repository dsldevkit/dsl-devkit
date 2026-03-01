package com.avaloq.tools.ddk.xtext.expression.generator;

import com.google.inject.Inject;
import java.util.Iterator;
import java.util.Objects;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.xbase.lib.StringExtensions;


@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class GenModelUtilX {

  @Inject
  private Naming naming;

  @Inject
  private IGlobalScopeProvider globalScopeProvider;

  @Inject
  private ResourceDescriptionsProvider resourceDescriptionsProvider;

  private Resource context;

  // CHECKSTYLE:CHECK-OFF HiddenField
  public void setResource(final Resource context) {
    this.context = context;
  }
  // CHECKSTYLE:CHECK-ON HiddenField

  public /* cached */ String qualifiedPackageInterfaceName(final EPackage it) {
    final GenPackage genPackage = genPackage(it);
    if (genPackage != null) {
      return genPackage.getQualifiedPackageInterfaceName();
    } else if (!Objects.equals(it.getClass(), EPackageImpl.class)) {
      return it.getClass().getInterfaces()[0].getName();
    }
    return null;
  }

  // CHECKSTYLE:CONSTANTS-OFF
  public String qualifiedSwitchClassName(final EPackage it) {
    final GenPackage genPackage = genPackage(it);
    if (genPackage != null && genPackage.isLiteralsInterface()) {
      return genPackage.getUtilitiesPackageName() + "." + genPackage.getSwitchClassName();
    } else {
      return naming.toJavaPackage(qualifiedPackageInterfaceName(it)) + ".util." + StringExtensions.toFirstUpper(it.getName()) + "Switch"; // heuristic
    }
  }

  protected /* cached */ String _literalIdentifier(final EStructuralFeature it) {
    final EClass eClass = it.getEContainingClass();
    final EPackage ePackage = eClass.getEPackage();
    final GenPackage genPackage = genPackage(ePackage);
    if (genPackage != null && genPackage.isLiteralsInterface()) {
      return literalIdentifier(eClass) + "__" + format(it.getName()).toUpperCase();
    } else {
      return qualifiedPackageInterfaceName(ePackage) + ".eINSTANCE.get" + eClass.getName() + "_" + StringExtensions.toFirstUpper(it.getName()) + "()";
    }
  }

  protected /* cached */ String _literalIdentifier(final EClass it) {
    final GenPackage genPackage = genPackage(it.getEPackage());
    if (genPackage != null && genPackage.isLiteralsInterface()) {
      return genPackage.getQualifiedPackageInterfaceName() + ".Literals." + format(it.getName()).toUpperCase();
    } else {
      return qualifiedPackageInterfaceName(it.getEPackage()) + ".eINSTANCE.get" + it.getName() + "()";
    }
  }

  // defined to simplify debugging generator problems
  protected /* cached */ String _literalIdentifier(final ENamedElement it) {
    return "DOES_NOT_EXIST";
  }

  // defined to simplify debugging generator problems
  protected /* cached */ String _literalIdentifier(final Void it) {
    return "DOES_NOT_EXIST";
  }

  // e.g. EcorePackage.ENAMED_ELEMENT
  public /* cached */ String classifierIdLiteral(final EClass it) {
    return qualifiedPackageInterfaceName(it.getEPackage()) + "." + format(it.getName()).toUpperCase();
  }
  // CHECKSTYLE:CONSTANTS-ON

  protected /* cached */ String _instanceClassName(final Void it) {
    return "";
  }

  protected /* cached */ String _instanceClassName(final EClassifier it) {
    if (it.getInstanceClassName() != null) {
      return it.getInstanceClassName();
    }
    return it.getName();
  }

  protected /* cached */ String _instanceClassName(final EDataType it) {
    if (it.getInstanceClassName() != null) {
      return it.getInstanceClassName();
    }
    return genDataType(it).getQualifiedInstanceClassName();
  }

  protected /* cached */ String _instanceClassName(final EClass it) {
    if (it.getInstanceClassName() != null) {
      return it.getInstanceClassName();
    }
    return genClass(it).getQualifiedInterfaceName();
  }

  public /* cached */ GenPackage genPackage(final EModelElement it) {
    final EPackage ePackage = EcoreUtil2.getContainerOfType(it, EPackage.class);
    if (globalScopeProvider != null && context != null) {
      final IScope scope = globalScopeProvider.getScope(context, GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES, null);
      if (scope != null && ePackage != null) {
        final IEObjectDescription desc = scope.getSingleElement(QualifiedName.create(ePackage.getNsURI()));
        if (desc != null) {
          return (GenPackage) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
        } else {
          final IResourceDescriptions resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(context);
          final Iterator<IEObjectDescription> descs = resourceDescriptions.getExportedObjects(GenModelPackage.Literals.GEN_PACKAGE, QualifiedName.create(ePackage.getNsURI()), false).iterator();
          if (descs.hasNext()) {
            return (GenPackage) EcoreUtil.resolve(descs.next().getEObjectOrProxy(), context);
          }
          // In case Xcore is installed GenPackages will be indexed using GenPackage#getQualifiedPackageName()
          for (final IEObjectDescription candidate : resourceDescriptions.getExportedObjectsByType(GenModelPackage.Literals.GEN_PACKAGE)) {
            if (Objects.equals(candidate.getName().getLastSegment(), ePackage.getName())) {
              final GenPackage resolvedCanidate = (GenPackage) EcoreUtil.resolve(candidate.getEObjectOrProxy(), context);
              if (!resolvedCanidate.eIsProxy() && Objects.equals(resolvedCanidate.getEcorePackage(), ePackage)) {
                return resolvedCanidate;
              }
            }
          }
        }
      }
    }
    ResourceSet resourceSet;
    if (context != null) {
      resourceSet = context.getResourceSet();
    } else if (it.eResource().getResourceSet() != null) {
      resourceSet = it.eResource().getResourceSet();
    } else {
      resourceSet = new ResourceSetImpl();
    }
    return ePackage != null ? GenModelUtil2.findGenPackage(ePackage, resourceSet) : null;
  }

  public /* cached */ GenModel genModel(final EModelElement it) {
    final GenPackage genPackage = genPackage(it);
    return genPackage != null ? genPackage.getGenModel() : null;
  }

  public /* cached */ GenClass genClass(final EClass it) {
    final GenPackage genPackage = genPackage(it);
    return genPackage != null ? (GenClass) genPackage.getGenModel().findGenClassifier(it) : null;
  }

  public /* cached */ GenDataType genDataType(final EDataType it) {
    final GenPackage genPackage = genPackage(it);
    return genPackage != null ? (GenDataType) genPackage.getGenModel().findGenClassifier(it) : null;
  }

  public /* cached */ String format(final String name) {
    return GenModelUtil2.format(name);
  }

  public String literalIdentifier(final ENamedElement it) {
    if (it instanceof EClass eClass) {
      return _literalIdentifier(eClass);
    } else if (it instanceof EStructuralFeature eStructuralFeature) {
      return _literalIdentifier(eStructuralFeature);
    } else if (it != null) {
      return _literalIdentifier(it);
    } else {
      return _literalIdentifier((Void) null);
    }
  }

  public String instanceClassName(final EClassifier it) {
    if (it instanceof EClass eClass) {
      return _instanceClassName(eClass);
    } else if (it instanceof EDataType eDataType) {
      return _instanceClassName(eDataType);
    } else if (it != null) {
      return _instanceClassName(it);
    } else {
      return _instanceClassName((Void) null);
    }
  }
}
