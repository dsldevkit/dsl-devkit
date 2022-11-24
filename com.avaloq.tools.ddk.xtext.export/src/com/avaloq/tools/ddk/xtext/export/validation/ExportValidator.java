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
package com.avaloq.tools.ddk.xtext.export.validation;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.internal.xtend.xtend.XtendFile;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtend.expression.Resource;
import org.eclipse.xtend.expression.ResourceManager;
import org.eclipse.xtend.expression.ResourceManagerDefaultImpl;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.validation.Check;

import com.avaloq.tools.ddk.xtext.export.export.Attribute;
import com.avaloq.tools.ddk.xtext.export.export.DeclarationForType;
import com.avaloq.tools.ddk.xtext.export.export.Export;
import com.avaloq.tools.ddk.xtext.export.export.ExportModel;
import com.avaloq.tools.ddk.xtext.export.export.ExportPackage;
import com.avaloq.tools.ddk.xtext.export.export.Extension;
import com.avaloq.tools.ddk.xtext.export.export.Interface;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceField;
import com.avaloq.tools.ddk.xtext.export.export.InterfaceNavigation;
import com.avaloq.tools.ddk.xtext.export.export.UserData;
import com.avaloq.tools.ddk.xtext.scoping.AbstractNameFunction;
import com.avaloq.tools.ddk.xtext.scoping.NameFunctions;
import com.avaloq.tools.ddk.xtext.validation.UniquenessJavaValidationHelper;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Validations for the export language.
 */
public class ExportValidator extends AbstractExportValidator {

  /**
   * Verifies that all referenced extensions can be found.
   *
   * @param model
   *          export model to check
   */
  @Check
  public void checkExtensions(final ExportModel model) {
    ResourceManager resourceManager = null;
    if (Platform.isRunning()) {
      // FIXME: sort out xpand
      // IXtendXpandProject project = Activator.getExtXptModelManager().findProject(ResourcesPlugin.getWorkspace().getRoot().getFile(new
      // Path(model.eResource().getURI().toPlatformString(true))).getProject());
      // if (project != null) {
      // resourceManager = new XpandPluginExecutionContext(project).getResourceManager();
      // }
    } else {
      resourceManager = new ResourceManagerDefaultImpl();
    }
    if (resourceManager == null) {
      return;
    }
    for (Extension ext : model.getExtensions()) {
      final Resource res = resourceManager.loadResource(ext.getExtension(), XtendFile.FILE_EXTENSION);
      if (res == null) {
        error(NLS.bind("Extension ''{0}'' not found", ext.getExtension()), ext, ExportPackage.Literals.EXTENSION__EXTENSION, null);
      }
    }
  }

  /**
   * Checks that the interfaces and exports in an export section all are declared for a unique type.
   *
   * @param context
   *          model to check
   */
  @Check
  public void checkInterfaceAndExportUniqueness(final ExportModel context) {
    UniquenessJavaValidationHelper<DeclarationForType> helper = new UniquenessJavaValidationHelper<DeclarationForType>(new AbstractNameFunction() {
      @Override
      public QualifiedName apply(final EObject from) {
        return QualifiedName.create(((DeclarationForType) from).getType().getEPackage().getName(), ((DeclarationForType) from).getType().getName());
      }
    }, getMessageAcceptor()) {
      @Override
      public String getMessage(final DeclarationForType declaration) {
        return "declaration duplicate found: " + declaration.getType().getEPackage().getName() + "::" + declaration.getType().getName();
      }
    };
    helper.errorOnDuplicates(Iterables.filter(context.getInterfaces(), DeclarationForType.class), ExportPackage.Literals.DECLARATION_FOR_TYPE__TYPE);
    helper.errorOnDuplicates(Iterables.filter(context.getExports(), DeclarationForType.class), ExportPackage.Literals.DECLARATION_FOR_TYPE__TYPE);
  }

  /**
   * Checks that the exports don't have overlapping features, as a feature export is inherited by the export of subtypes.
   *
   * @param context
   *          model to check
   */
  @Check
  public void checkExportFieldUniqueness(final ExportModel context) {
    UniquenessJavaValidationHelper<Attribute> helper = new UniquenessJavaValidationHelper<Attribute>(NameFunctions.fromFeature(ExportPackage.Literals.ATTRIBUTE__ATTRIBUTE), getMessageAcceptor()) {
      @Override
      public String getMessage(final Attribute attribute) {
        return "duplicate found: " + attribute.getAttribute().getName();
      }
    };

    UniquenessJavaValidationHelper<UserData> helper2 = new UniquenessJavaValidationHelper<UserData>(NameFunctions.fromFeature(ExportPackage.Literals.USER_DATA__NAME), getMessageAcceptor());

    for (Export export : context.getExports()) {
      helper.errorOnDuplicates(export.getAttributes(), ExportPackage.Literals.ATTRIBUTE__ATTRIBUTE);
      helper2.errorOnDuplicates(export.getUserData(), ExportPackage.Literals.USER_DATA__NAME);
    }
  }

  /**
   * Checks that the interface don't have overlapping features, as a feature export is inherited by the export of subtypes.
   *
   * @param context
   *          model to check
   */
  @Check
  public void checkInterfaceFieldUniqueness(final ExportModel context) {
    UniquenessJavaValidationHelper<InterfaceField> helper1 = new UniquenessJavaValidationHelper<InterfaceField>(new AbstractNameFunction() {
      @Override
      public QualifiedName apply(final EObject from) {
        String name = ((InterfaceField) from).getField().getName();
        return QualifiedName.create(name != null ? name : "");
      }
    }, getMessageAcceptor());
    UniquenessJavaValidationHelper<InterfaceNavigation> helper2 = new UniquenessJavaValidationHelper<InterfaceNavigation>(new AbstractNameFunction() {
      @Override
      public QualifiedName apply(final EObject from) {
        String name = ((InterfaceNavigation) from).getRef().getName();
        return QualifiedName.create(name != null ? name : "");
      }
    }, getMessageAcceptor());

    for (Interface fingertype : context.getInterfaces()) {
      helper1.errorOnDuplicates(Iterables.filter(fingertype.getItems(), InterfaceField.class), ExportPackage.Literals.INTERFACE_FIELD__FIELD);
      helper2.errorOnDuplicates(Iterables.filter(fingertype.getItems(), InterfaceNavigation.class), ExportPackage.Literals.INTERFACE_NAVIGATION__REF);
    }
  }

  /**
   * Checks that a interface field actually refers to a field and not a containment reference which should be an InterfaceNavigation instead.
   *
   * @param context
   *          the interface item to check
   */
  @Check
  public void checkContainmentInterfaceItemIsNavigation(final InterfaceField context) {
    if (!(context.getField() instanceof EReference)) {
      return;
    }
    EReference ref = (EReference) context.getField();
    if (ref.isContainment()) {
      error("Containment references must be used with a reference expression (using '@'): " + ref.getName(), ExportPackage.Literals.INTERFACE_FIELD__FIELD);
    } else if (ref.isContainer()) {
      error("Container references must not be specified as interface items: " + ref.getName(), ExportPackage.Literals.INTERFACE_FIELD__FIELD);
    }
  }

  /**
   * Checks that a userdata clause does not declare a name that is the same as that of a structural feature of the type.
   * A warning is emitted in the general case, and an error if the name already exported.
   *
   * @param context
   *          the export section to check
   */
  @Check
  public void checkUserDataNameAsFeature(final Export context) {
    for (UserData data : context.getUserData()) {
      for (EStructuralFeature feature : context.getType().getEAllStructuralFeatures()) {
        if (data.getName().equals(feature.getName())) {
          if (context.getAllEAttributes().contains(feature)) {
            error(data.getName() + " is already defined as field", data, ExportPackage.Literals.USER_DATA__NAME, null);
          } else {
            error(data.getName() + " has the same name as an existing feature", data, ExportPackage.Literals.USER_DATA__NAME, null);
          }

        }
      }
    }
  }

  /**
   * Check that there is no overlap between the fields exported by a class and its parents.
   *
   * @param context
   *          model to check
   */
  @Check
  public void checkOverlap(final ExportModel context) {
    UniquenessJavaValidationHelper<Attribute> helper = new UniquenessJavaValidationHelper<Attribute>(NameFunctions.fromFeature(ExportPackage.Literals.ATTRIBUTE__ATTRIBUTE), getMessageAcceptor());
    for (Export export : context.getExports()) {
      for (Export candidate : context.getExports()) {
        if (export.getType() != candidate.getType() && candidate.getType().isSuperTypeOf(export.getType())) {
          for (Attribute attribute : helper.findDuplicates(Iterables.concat(export.getAttributes(), candidate.getAttributes()))) {
            if (attribute.eContainer() == export) {
              error("Overlap duplicate found: " + attribute.getAttribute().getName(), attribute, ExportPackage.Literals.ATTRIBUTE__ATTRIBUTE, null);
            }
          }
        }
      }
    }
  }

  /**
   * Make sure only one type of fingerprinting is used.
   *
   * @param context
   *          export model
   */
  @Check
  public void checkConsistentFingerprints(final ExportModel context) {
    List<Export> resourceFingerprintExports = Lists.newArrayList();
    List<Export> objectFingerprintExports = Lists.newArrayList();
    for (Export exp : context.getExports()) {
      if (exp.isResourceFingerprint()) {
        resourceFingerprintExports.add(exp);
      } else if (exp.isFingerprint()) {
        objectFingerprintExports.add(exp);
      }
    }
    if (!objectFingerprintExports.isEmpty() && !resourceFingerprintExports.isEmpty()) {
      for (Export exp : Iterables.concat(resourceFingerprintExports, objectFingerprintExports)) {
        error("Resource and object fingerprints cannot be mixed", exp, exp.isFingerprint() ? ExportPackage.Literals.EXPORT__FINGERPRINT
            : ExportPackage.Literals.EXPORT__RESOURCE_FINGERPRINT, null);
      }
    }
  }

  /**
   * Checks that the interface referenced by a fingerprint is defined.
   *
   * @param context
   *          export to check
   */
  @Check
  public void checkFingerprintInterfaceDefined(final Export context) {
    if (context.isFingerprint() || context.isResourceFingerprint()) {
      ExportModel model = EcoreUtil2.getContainerOfType(context, ExportModel.class);
      Interface match = null;
      for (Interface iface : model.getInterfaces()) {
        if (iface.getType().isSuperTypeOf(context.getType())) {
          match = iface;
          break;
        }
      }
      if (match == null) {
        error("No matching interface specification declared", context.isFingerprint() ? ExportPackage.Literals.EXPORT__FINGERPRINT
            : ExportPackage.Literals.EXPORT__RESOURCE_FINGERPRINT);
      }
    }
  }

  /**
   * Checks that there is an interface specification matching the given navigation.
   *
   * @param context
   *          navigation to check
   */
  @Check
  public void checkReferencedInterfaceDeclared(final InterfaceNavigation context) {
    if (context.getRef() == null || context.getRef().getEReferenceType() == null) {
      return;
    }
    ExportModel model = EcoreUtil2.getContainerOfType(context, ExportModel.class);
    EClass type = context.getRef().getEReferenceType();
    if (findMatchingInterfaces(model, type).isEmpty()) {
      error("No interface specification declared matching type " + type.getName(), ExportPackage.Literals.INTERFACE_NAVIGATION__REF);
    }
  }

  /**
   * Checks that there is at least one export which has a fingerprint if there is an interface specification.
   *
   * @param context
   *          model to check
   */
  @Check
  public void checkInterfaceReferenced(final ExportModel context) {
    if (context.getInterfaces().isEmpty()) {
      return;
    }
    for (Export exp : context.getExports()) {
      if (exp.isResourceFingerprint() || exp.isFingerprint()) {
        return;
      }
    }
    warning("No export has a fingerprint referencing the interface specification", ExportPackage.Literals.EXPORT_MODEL__INTERFACES);
  }

  /**
   * Returns all matching interfaces for a given EClass.
   *
   * @param model
   *          model
   * @param type
   *          type to match
   * @return all matching interfaces or an empty list
   */
  private Collection<Interface> findMatchingInterfaces(final ExportModel model, final EClass type) {
    Collection<Interface> matches = Lists.newArrayList();
    // find exact match
    for (Interface iface : model.getInterfaces()) {
      if (type.equals(iface.getType())) {
        matches.add(iface);
      }
    }
    // find matching supertype
    for (Interface iface : model.getInterfaces()) {
      if (iface.getType() != null && iface.getType().isSuperTypeOf(type)) {
        matches.add(iface);
      }
    }
    // find matching subtype
    for (Interface iface : model.getInterfaces()) {
      if (iface.getType() != null && type.isSuperTypeOf(iface.getType())) {
        matches.add(iface);
      }
    }
    return matches;
  }

}
