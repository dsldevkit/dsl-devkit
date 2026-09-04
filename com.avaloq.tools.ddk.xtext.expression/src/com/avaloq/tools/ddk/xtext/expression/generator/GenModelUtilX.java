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
package com.avaloq.tools.ddk.xtext.expression.generator;

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
import org.eclipse.emf.ecore.EObject;
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

import com.google.inject.Inject;


@SuppressWarnings({"nls", "checkstyle:MethodName", "PMD.UnusedFormalParameter"})
public class GenModelUtilX {

  private static final String DOES_NOT_EXIST = "DOES_NOT_EXIST";

  @Inject
  private Naming naming;

  @Inject
  private IGlobalScopeProvider globalScopeProvider;
  @Inject
  private ResourceDescriptionsProvider resourceDescriptionsProvider;

  /**
   * The current model resource used for GenPackage lookups. This utility is normally a Guice singleton, so
   * multiple Xtext workers (the editor reconciler, the workspace builder, content assist, etc.) share the same
   * instance and would otherwise race on a single field. Storing the context per-thread isolates each worker's
   * lookups from the others.
   */
  private final ThreadLocal<Resource> context = new ThreadLocal<>();

  public void setResource(final Resource resource) {
    context.set(resource);
  }

  public Resource getContext() {
    return context.get();
  }

  public String qualifiedPackageInterfaceName(final EPackage ePackage) {
    final GenPackage genPackage = genPackage(ePackage);
    if (genPackage != null) {
      return genPackage.getQualifiedPackageInterfaceName();
    } else if (!Objects.equals(ePackage.getClass(), EPackageImpl.class)) {
      return firstInterface(ePackage.getClass()).getName();
    }
    // GenModel lookup failed (typically because the editor reconciler runs before the .genmodel index is
    // populated). Resolve via the EMF registry: the plugin-registered EPackage for this nsURI is a Java
    // class whose first interface is the generated *Package interface.
    if (ePackage.getNsURI() != null) {
      final EPackage registered = EPackage.Registry.INSTANCE.getEPackage(ePackage.getNsURI());
      if (registered != null && registered != ePackage && !Objects.equals(registered.getClass(), EPackageImpl.class)) {
        final Class<?>[] interfaces = registered.getClass().getInterfaces();
        if (interfaces.length != 0) {
          return interfaces[0].getName();
        }
      }
    }
    return null;
  }

  public String qualifiedSwitchClassName(final EPackage ePackage) {
    final GenPackage genPackage = genPackage(ePackage);
    if (genPackage != null && genPackage.isLiteralsInterface()) {
      return genPackage.getUtilitiesPackageName() + "." + genPackage.getSwitchClassName();
    } else {
      return naming.toJavaPackage(qualifiedPackageInterfaceName(ePackage)) + ".util." + toFirstUpper(ePackage.getName()) + "Switch"; // heuristic
    }
  }

  protected String _literalIdentifier(final EStructuralFeature feature) {
    final EClass eClass = feature.getEContainingClass();
    if (eClass == null) {
      // EStructuralFeature with no containing class - an unresolved EReference proxy. This happens during
      // editor reconciliation: a .scope file like `scope T#ref { ... }` records `s.reference` as a cross
      // reference whose scope is `T.eAllReferences`; if `T` itself is mid-resolve (e.g. the imported
      // EPackage is not yet indexed), `s.reference` returns the still-proxy EReference, which has no
      // container until linking completes. The generated body cannot reference this feature; emit a
      // sentinel so the body string parses and the reconciler can keep going. The next reconcile cycle
      // will retry against a fully-linked model and produce the correct literal.
      return DOES_NOT_EXIST;
    }
    final EPackage ePackage = eClass.getEPackage();
    final GenPackage genPackage = genPackage(ePackage);
    if (genPackage != null && genPackage.isLiteralsInterface()) {
      return literalIdentifier(eClass) + "__" + format(feature.getName()).toUpperCase();
    } else {
      return qualifiedPackageInterfaceName(ePackage) + ".eINSTANCE.get" + eClass.getName() + "_" + toFirstUpper(feature.getName()) + "()";
    }
  }

  protected String _literalIdentifier(final EClass eClass) {
    final GenPackage genPackage = genPackage(eClass.getEPackage());
    if (genPackage != null && genPackage.isLiteralsInterface()) {
      return genPackage.getQualifiedPackageInterfaceName() + ".Literals." + format(eClass.getName()).toUpperCase();
    } else {
      return qualifiedPackageInterfaceName(eClass.getEPackage()) + ".eINSTANCE.get" + eClass.getName() + "()";
    }
  }

  // defined to simplify debugging generator problems
  protected String _literalIdentifier(final ENamedElement element) {
    return DOES_NOT_EXIST;
  }

  // defined to simplify debugging generator problems
  protected String _literalIdentifier(final Void element) {
    return DOES_NOT_EXIST;
  }

  // e.g. EcorePackage.ENAMED_ELEMENT
  public String classifierIdLiteral(final EClass eClass) {
    return qualifiedPackageInterfaceName(eClass.getEPackage()) + "." + format(eClass.getName()).toUpperCase();
  }

  protected String _instanceClassName(final Void classifier) {
    return "";
  }

  protected String _instanceClassName(final EClassifier classifier) {
    if (classifier.getInstanceClassName() != null) {
      return classifier.getInstanceClassName();
    }
    final String fromRegistry = instanceClassNameFromRegistry(classifier);
    if (fromRegistry != null) {
      return fromRegistry;
    }
    if (classifier.getName() != null) {
      return classifier.getName();
    }
    return EObject.class.getName();
  }

  protected String _instanceClassName(final EDataType dataType) {
    if (dataType.getInstanceClassName() != null) {
      return dataType.getInstanceClassName();
    }
    final String fromRegistry = instanceClassNameFromRegistry(dataType);
    if (fromRegistry != null) {
      return fromRegistry;
    }
    final GenDataType gdt = genDataType(dataType);
    if (gdt != null) {
      return gdt.getQualifiedInstanceClassName();
    }
    // Unresolvable classifier (typically a proxy seen during editor reconciliation before linking
    // completes). Fall back to Object so the inferred Java method signature is well-formed and the
    // reconciler can keep going; the next reconcile cycle will retry with the resolved type.
    return Object.class.getName();
  }

  protected String _instanceClassName(final EClass eClass) {
    if (eClass.getInstanceClassName() != null) {
      return eClass.getInstanceClassName();
    }
    final String fromRegistry = instanceClassNameFromRegistry(eClass);
    if (fromRegistry != null) {
      return fromRegistry;
    }
    final GenClass gc = genClass(eClass);
    if (gc != null) {
      return gc.getQualifiedInterfaceName();
    }
    // Unresolvable EClass (typically a proxy seen during editor reconciliation before linking
    // completes). Fall back to EObject so the inferred Java method signature is well-formed and the
    // reconciler can keep going; the next reconcile cycle will retry with the resolved type.
    return EObject.class.getName();
  }

  /**
   * Returns the Java instance class name for the given classifier by looking its EPackage up in the EMF
   * registry. Workspace {@code .ecore} files load their EClasses as bare {@link EPackageImpl} instances whose
   * {@code instanceClassName} is unset; the corresponding plugin-registered EPackage (loaded from the generated
   * Java class) carries the field on every EClassifier. Cross-referencing the registry by nsURI lets us resolve
   * the Java name without needing the workspace GenModel to be indexed - which the inferrer cannot rely on
   * during editor reconciliation, since the {@code .genmodel} index population lags behind the open file.
   *
   * @param classifier
   *          the classifier whose Java name is needed, may be {@code null}
   * @return the Java instance class name, or {@code null} if the EPackage is not in the registry or the
   *         registry counterpart cannot be matched
   */
  private String instanceClassNameFromRegistry(final EClassifier classifier) {
    final EPackage ePackage = classifier.getEPackage();
    if (ePackage == null) {
      return null;
    }
    final String nsURI = ePackage.getNsURI();
    if (nsURI == null) {
      return null;
    }
    final EPackage registered = EPackage.Registry.INSTANCE.getEPackage(nsURI);
    if (registered == null || registered == ePackage) {
      // Either the package is not in the registry, or what we already have IS the registry copy (and we
      // already checked its instanceClassName field above).
      return null;
    }
    final EClassifier counterpart = registered.getEClassifier(classifier.getName());
    if (counterpart == null) {
      return null;
    }
    return counterpart.getInstanceClassName();
  }

  public GenPackage genPackage(final EModelElement element) {
    final EPackage ePackage = EcoreUtil2.getContainerOfType(element, EPackage.class);
    final Resource ctx = context.get();
    if (globalScopeProvider != null && ctx != null) {
      final IScope scope = globalScopeProvider.getScope(ctx, GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES, null);
      if (scope != null && ePackage != null) {
        final IEObjectDescription desc = scope.getSingleElement(QualifiedName.create(ePackage.getNsURI()));
        if (desc != null) {
          return (GenPackage) EcoreUtil.resolve(desc.getEObjectOrProxy(), ctx);
        } else {
          final IResourceDescriptions resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(ctx);
          final Iterator<IEObjectDescription> descs = resourceDescriptions
              .getExportedObjects(GenModelPackage.Literals.GEN_PACKAGE, QualifiedName.create(ePackage.getNsURI()), false).iterator();
          if (descs.hasNext()) {
            return (GenPackage) EcoreUtil.resolve(descs.next().getEObjectOrProxy(), ctx);
          }
          // In case Xcore is installed GenPackages will be indexed using GenPackage#getQualifiedPackageName()
          for (final IEObjectDescription candidate : resourceDescriptions.getExportedObjectsByType(GenModelPackage.Literals.GEN_PACKAGE)) {
            if (!Objects.equals(candidate.getName().getLastSegment(), ePackage.getName())) {
              continue;
            }
            final GenPackage resolvedCanidate = (GenPackage) EcoreUtil.resolve(candidate.getEObjectOrProxy(), ctx);
            if (!resolvedCanidate.eIsProxy() && Objects.equals(resolvedCanidate.getEcorePackage(), ePackage)) {
              return resolvedCanidate;
            }
          }
        }
      }
    }
    final ResourceSet resourceSet;
    if (ctx != null) {
      resourceSet = ctx.getResourceSet();
    } else if (element.eResource().getResourceSet() != null) {
      resourceSet = element.eResource().getResourceSet();
    } else {
      resourceSet = new ResourceSetImpl();
    }
    return ePackage != null ? GenModelUtil2.findGenPackage(ePackage, resourceSet) : null;
  }

  public GenModel genModel(final EModelElement element) {
    final GenPackage genPackage = genPackage(element);
    return genPackage != null ? genPackage.getGenModel() : null;
  }

  public GenClass genClass(final EClass eClass) {
    final GenPackage genPackage = genPackage(eClass);
    return genPackage != null ? (GenClass) genPackage.getGenModel().findGenClassifier(eClass) : null;
  }

  public GenDataType genDataType(final EDataType dataType) {
    final GenPackage genPackage = genPackage(dataType);
    return genPackage != null ? (GenDataType) genPackage.getGenModel().findGenClassifier(dataType) : null;
  }

  public String format(final String name) {
    return GenModelUtil2.format(name);
  }

  public String literalIdentifier(final ENamedElement element) {
    return switch (element) {
      case EClass eClass -> _literalIdentifier(eClass);
      case EStructuralFeature feature -> _literalIdentifier(feature);
      case null -> _literalIdentifier((Void) null);
      default -> _literalIdentifier(element);
    };
  }

  public String instanceClassName(final EClassifier classifier) {
    return switch (classifier) {
      case EClass eClass -> _instanceClassName(eClass);
      case EDataType dataType -> _instanceClassName(dataType);
      case null -> _instanceClassName((Void) null);
      default -> _instanceClassName(classifier);
    };
  }

  /**
   * Returns the first interface directly implemented by the given class, or {@code null} if it implements none.
   * <p>
   * Reproduces the {@code IterableExtensions.head} semantics the Xtend source relied on, where an empty
   * sequence yields {@code null} rather than failing.
   * </p>
   *
   * @param type
   *          the class whose first declared interface is wanted, must not be {@code null}
   * @return the first declared interface, or {@code null} if there is none
   */
  private static Class<?> firstInterface(final Class<?> type) {
    final Class<?>[] interfaces = type.getInterfaces();
    return interfaces.length == 0 ? null : interfaces[0];
  }

  /**
   * Returns the given string with its first character converted to upper case.
   * <p>
   * Reproduces the {@code StringExtensions.toFirstUpper} semantics the Xtend source relied on, including its
   * pass-through of {@code null}, of the empty string and of strings that already start upper case.
   * </p>
   *
   * @param value
   *          the string to convert, may be {@code null}
   * @return the converted string, {@code null} if the input was {@code null}
   */
  private static String toFirstUpper(final String value) {
    if (value == null || value.isEmpty() || Character.isUpperCase(value.charAt(0))) {
      return value;
    }
    if (value.length() == 1) {
      return value.toUpperCase();
    }
    return value.substring(0, 1).toUpperCase() + value.substring(1);
  }

}
