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
package com.avaloq.tools.ddk.check.generator;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XCollectionLiteral;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XSetLiteral;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.Member;
import com.avaloq.tools.ddk.check.check.XGuardExpression;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.google.inject.Inject;


/**
 * The Check compiler used for generating Java code from Check models.
 */
// CHECKSTYLE:CONSTANTS-OFF
public class CheckCompiler extends XbaseCompiler {

  @Inject
  private CheckGeneratorExtensions generatorExtensions;

  @Inject
  private CheckGeneratorNaming generatorNaming;

  @Inject
  private IJvmModelAssociations associations;

  /**
   * Gets the name of the implicit variable in a context.
   *
   * @param expr
   *          any expression
   * @return the parameter name, if the expression is within a context and set, or CheckConstants.IT otherwise.
   */
  private String getContextImplicitVariableName(final XExpression expr) {
    Context ctx = EcoreUtil2.getContainerOfType(expr, Context.class);
    return ctx != null && ctx.getContextVariable().getName() != null ? ctx.getContextVariable().getName() : CheckConstants.IT;
  }

  /**
   * Gets the FormalParameter this feature call references, if any.
   *
   * @param expression
   *          to check
   * @return The source model element (FormalParameter) for this feature call target, or null.
   */
  private FormalParameter getFormalParameter(final XAbstractFeatureCall expression) {
    return getFormalParameter(expression.getFeature());
  }

  /**
   * Gets the FormalParameter this feature call references, if any.
   *
   * @param element
   *          to check
   * @return The source model element (FormalParameter) for this feature call target, or null.
   */
  public FormalParameter getFormalParameter(final JvmIdentifiableElement element) {
    Iterator<EObject> modelElements = associations.getSourceElements(element).iterator();
    FormalParameter parameter = null;
    while (parameter == null && modelElements.hasNext()) {
      EObject obj = modelElements.next();
      if (obj instanceof FormalParameter) {
        parameter = (FormalParameter) obj;
      }
    }
    return parameter;
  }

  /**
   * Gets the Member this feature call references, if any.
   *
   * @param expression
   *          to check
   * @return The source model element (Member) for this feature call target, or null.
   */
  private Member getMember(final XAbstractFeatureCall expression) {
    return getMember(expression.getFeature());
  }

  /**
   * Gets the Member this feature call references, if any.
   *
   * @param element
   *          to check
   * @return The source model element (Member) for this feature call target, or null.
   */
  private Member getMember(final JvmIdentifiableElement element) {
    Iterator<EObject> modelElements = associations.getSourceElements(element).iterator();
    Member member = null;
    while (member == null && modelElements.hasNext()) {
      EObject obj = modelElements.next();
      if (obj instanceof Member) {
        member = (Member) obj;
      }
    }
    return member;

  }

  @Override
  protected boolean isVariableDeclarationRequired(final XExpression expression, final ITreeAppendable b, final boolean recursive) {
    if (expression instanceof XAbstractFeatureCall && getFormalParameter((XAbstractFeatureCall) expression) != null) {
      return false;
    }
    return super.isVariableDeclarationRequired(expression, b, recursive);
  }

  @Override
  // CHECKSTYLE:OFF
  protected void _toJavaExpression(final XAbstractFeatureCall expr, final ITreeAppendable b) {
    // CHECKSTYLE:ON
    FormalParameter parameter = getFormalParameter(expr);
    if (parameter != null) {
      // No Java entities are generated for this. Replace by a call to the getter function.
      b.append(generatorNaming.catalogInstanceName(parameter)).append(".").append(generatorNaming.formalParameterGetterName(parameter));
      b.append("(").append(getContextImplicitVariableName(expr)).append(")");
    } else {
      Member member = getMember(expr);
      if (member != null) {
        // Something isn't quite right in the Jvm model yet... or in the xbase compiler. Don't know what it is, but even if in an inner
        // class, it generates "this.foo" instead of either just "foo" or "OuterClass.this.foo". Force it to produce the latter.
        CheckCatalog catalog = EcoreUtil2.getContainerOfType(member, CheckCatalog.class);
        String catalogName = generatorNaming.validatorClassName(catalog);
        b.append(catalogName + ".this.").append(member.getName());
        return;
      }
      super._toJavaExpression(expr, b);
    }
  }

  @Override
  // CHECKSTYLE:OFF
  protected void _toJavaStatement(final XAbstractFeatureCall expr, final ITreeAppendable b, final boolean isReferenced) {
    // CHECKSTYLE:ON
    FormalParameter parameter = getFormalParameter(expr);
    if (parameter != null) {
      // Nothing to do.
    } else {
      super._toJavaStatement(expr, b, isReferenced);
    }
  }

  @Override
  protected void doInternalToJavaStatement(final XExpression obj, final ITreeAppendable appendable, final boolean isReferenced) {
    if (obj instanceof XGuardExpression) {
      _toJavaStatement((XGuardExpression) obj, appendable, isReferenced);
    } else if (obj instanceof XIssueExpression) {
      _toJavaStatement((XIssueExpression) obj, appendable, isReferenced);
    } else {
      super.doInternalToJavaStatement(obj, appendable, isReferenced);
    }
  }

  @Override
  protected void internalToConvertedExpression(final XExpression obj, final ITreeAppendable appendable) {
    if (obj instanceof XGuardExpression) {
      _toJavaExpression((XGuardExpression) obj, appendable);
    } else if (obj instanceof XIssueExpression) {
      _toJavaExpression((XIssueExpression) obj, appendable);
    } else {
      super.internalToConvertedExpression(obj, appendable);
    }
  }

  /** {@inheritDoc} */
  // CHECKSTYLE:OFF
  protected void _toJavaStatement(final XGuardExpression expr, final ITreeAppendable b, final boolean isReferenced) {
    // CHECKSTYLE:ON
    internalToJavaStatement(expr.getGuard(), b, true);
    b.newLine().append("if (!(");
    internalToJavaExpression(expr.getGuard(), b);
    b.append(")) {").increaseIndentation();
    b.newLine().append("return; ");
    b.decreaseIndentation().newLine().append("}");
  }

  @Override
  protected void appendImmutableCollectionExpression(final XCollectionLiteral literal, final ITreeAppendable b, final String collectionsMethod, final Class<?> guavaHelper, final String guavaHelperMethod) {
    // This is a work-around for a bug in the xbase compiler, which always constructs empty list literals #[] as List<Object>,
    // which then cannot be assigned (without cast) to any typed list. Note that this is not a problem in check; it also occurs
    // in plain xtend.
    if (literal.getElements().isEmpty()) {
      JvmType collectionsClass = findKnownTopLevelType(Collections.class, literal);
      if (collectionsClass != null) {
        if (literal instanceof XListLiteral) {
          b.append(collectionsClass).append(".emptyList()");
          return;
        } else if (literal instanceof XSetLiteral) {
          b.append(collectionsClass).append(".emptySet()");
          return;
        }
      }
    }
    super.appendImmutableCollectionExpression(literal, b, collectionsMethod, guavaHelper, guavaHelperMethod);
  }

  /** {@inheritDoc} */
  // CHECKSTYLE:OFF
  protected void _toJavaExpression(final XGuardExpression expr, final ITreeAppendable b) {
    // CHECKSTYLE:ON
    b.append(getVarName(expr, b));
  }

  /** {@inheritDoc} */
  // CHECKSTYLE:OFF
  protected void _toJavaStatement(final XIssueExpression expr, final ITreeAppendable b, final boolean isReferenced) { // NOPMD NPath complexity
    // CHECKSTYLE:ON
    XExpression markerObject = expr.getMarkerObject();

    for (XExpression param : expr.getMessageParameters()) {
      internalToJavaStatement(param, b, true);
      b.newLine();
    }

    if (markerObject != null) {
      internalToJavaStatement(markerObject, b, true);
      b.newLine();
    }

    XExpression markerIndex = expr.getMarkerIndex();
    if (markerIndex != null) {
      internalToJavaStatement(markerIndex, b, true);
      b.newLine();
    }

    JvmType eObjectType = findKnownTopLevelType(EObject.class, expr);

    for (XExpression data : expr.getIssueData()) {
      internalToJavaStatement(data, b, true);
      b.newLine();
    }

    boolean issueExpressionEqualsImplicitVariable = true;
    if (markerObject != null) {
      issueExpressionEqualsImplicitVariable = markerObject.toString().equals(getContextImplicitVariableName(expr));
    }

    if (!issueExpressionEqualsImplicitVariable) {
      // checking for null context EObject
      b.append("if (");
      internalToConvertedExpression(markerObject, b, getLightweightType(eObjectType));
      b.append(" != null) {").increaseIndentation().newLine();
    }

    // acceptor
    b.append("// Issue diagnostic").newLine();
    b.append(generatorNaming.catalogInstanceName(expr)).append(".accept(").append("getMessageAcceptor()");

    // context object
    b.append(", //").increaseIndentation().newLine();
    if (markerObject != null) {
      internalToConvertedExpression(markerObject, b, getLightweightType(eObjectType));
    } else {
      b.append(getContextImplicitVariableName(expr));
    }
    b.append(", // context EObject").newLine();
    // feature
    EStructuralFeature markerFeature = expr.getMarkerFeature();
    if (markerFeature != null) {
      b.append(findEPackageInterfaceType(markerFeature, expr));
      b.append(".eINSTANCE.get").append(markerFeature.getEContainingClass().getName()).append("_").append(Strings.toFirstUpper(markerFeature.getName())).append("()");
    } else {
      b.append("null");
    }

    b.append(", // EStructuralFeature").newLine();
    // message
    Check check = generatorExtensions.issuedCheck(expr);
    b.append(generatorNaming.catalogInstanceName(check)).append(".get").append(Strings.toFirstUpper(check.getName())).append("Message(");
    if (!expr.getMessageParameters().isEmpty()) {
      boolean first = true;
      for (XExpression param : expr.getMessageParameters()) {
        if (!first) {
          b.append(", ");
        }
        internalToJavaExpression(param, b);
        first = false;
      }
    }
    b.append(")");

    b.append(", // Message").newLine();
    // severity kind
    b.append(generatorNaming.catalogInstanceName(check)).append(".get").append(Strings.toFirstUpper(generatorExtensions.issuedCheck(expr).getName()));
    b.append("SeverityKind(");
    if (markerObject != null) {
      internalToConvertedExpression(markerObject, b, getLightweightType(eObjectType));
    } else {
      b.append(getContextImplicitVariableName(expr));
    }
    // .append(variable)
    b.append(")");

    b.append(", // Severity ").newLine();
    // marker index
    if (markerIndex != null) {
      internalToJavaExpression(markerIndex, b);
    } else {
      b.append(findKnownTopLevelType(ValidationMessageAcceptor.class, expr)).append(".INSIGNIFICANT_INDEX");
    }

    b.append(", // Marker index").newLine();
    // issue codes
    final String qualifiedIssueCodeName = generatorExtensions.qualifiedIssueCodeName(expr);
    b.append(qualifiedIssueCodeName == null ? "null" : qualifiedIssueCodeName);

    // issue data
    for (XExpression data : expr.getIssueData()) {
      b.append(", ");
      internalToJavaExpression(data, b);
    }
    b.append(" // Issue code & data").decreaseIndentation().newLine();

    if (!issueExpressionEqualsImplicitVariable) {
      b.append(");").decreaseIndentation().newLine();
      b.append("} else {").increaseIndentation().newLine();
      b.append("org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(").append(getLoggerClass(expr)).append(");").newLine();
      b.append("StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();").newLine();
      b.append("logger.warn(").append(getLoggerString(expr)).append(");").decreaseIndentation().newLine();
      b.append("}").newLine();
    } else {
      b.append(");");
    }
  }

  private String getLoggerClass(final XIssueExpression expr) {
    return generatorNaming.standaloneSetupClassName(EcoreUtil2.getContainerOfType(expr, CheckCatalog.class)) + ".class";
  }

  private String getLoggerString(final XIssueExpression expr) {
    Check check = generatorExtensions.issuedCheck(expr);
    return "\"An issue was found in " + generatorNaming.catalogInstanceName(check) + "." + Strings.toFirstUpper(check.getName()) //
        + " for \" + " + getContextImplicitVariableName(expr) + ".eResource().getURI() + \" at \" + stackTraceElements[1].toString()";
  }

  /** {@inheritDoc} */
  // CHECKSTYLE:OFF
  @SuppressWarnings("restriction")
  protected JvmType findEPackageInterfaceType(final ENamedElement element, final EObject context) {
    // CHECKSTYLE:ON
    EPackage ePackage = EcoreUtil2.getContainerOfType(element, EPackage.class);
    String qualifiedPackageInterfaceName = CheckGenModelUtil.getQualifiedPackageInterfaceName(ePackage);
    return getTypeComputationServices().getTypeReferences().findDeclaredType(qualifiedPackageInterfaceName, context);
  }

  /** {@inheritDoc} */
  // CHECKSTYLE:OFF
  protected void _toJavaExpression(final XIssueExpression expr, final ITreeAppendable b) {
    // CHECKSTYLE:ON
    b.append(getVarName(expr, b));
  }

  @Override
  protected ITreeAppendable appendTypeArguments(final XAbstractFeatureCall call, final ITreeAppendable original) {
    if (call.eResource() == null) {
      // That can happen four our hand-crafted CheckType.FAST annotations. Since these don't have
      // type arguments, there's nothing to do.
      return original;
    }
    return super.appendTypeArguments(call, original);
  }
}
