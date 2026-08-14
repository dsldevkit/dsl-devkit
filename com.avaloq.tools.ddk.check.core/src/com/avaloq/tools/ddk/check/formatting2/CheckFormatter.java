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
package com.avaloq.tools.ddk.check.formatting2;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.regionaccess.IEObjectRegion;
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XBasicForLoopExpression;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XCollectionLiteral;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XDoWhileExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XForLoopExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XPostfixOperation;
import org.eclipse.xtext.xbase.XReturnExpression;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XSynchronizedExpression;
import org.eclipse.xtext.xbase.XThrowExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.XTypeLiteral;
import org.eclipse.xtext.xbase.XUnaryOperation;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XWhileExpression;
import org.eclipse.xtext.xbase.annotations.formatting2.XbaseWithAnnotationsFormatter;
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation;
import org.eclipse.xtext.xtype.XFunctionTypeRef;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XImportSection;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.ContextVariable;
import com.avaloq.tools.ddk.check.check.FormalParameter;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.Member;
import com.avaloq.tools.ddk.check.check.SeverityRange;
import com.avaloq.tools.ddk.check.check.XGuardExpression;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess;
import com.google.inject.Inject;


@SuppressWarnings({"checkstyle:MethodName", "nls"})
public class CheckFormatter extends XbaseWithAnnotationsFormatter {

  @Inject
  private CheckGrammarAccess checkGrammarAccess;

  /**
   * Common formatting for curly brackets that are not handled by the parent formatter.
   *
   * @param semanticElement
   *          the element containing '{' and '}' keywords.
   * @param document
   *          the formattable document.
   */
  private void formatCurlyBracket(final EObject semanticElement, final IFormattableDocument document) {
    // low priority so that it can be overridden by other custom formatting rules.
    final ISemanticRegion open = regionFor(semanticElement).keyword("{");
    final ISemanticRegion close = regionFor(semanticElement).keyword("}");
    document.interior(open, close, it -> {
      it.lowPriority();
      it.indent();
    });
    document.append(open, it -> {
      it.lowPriority();
      it.newLine();
    });
    document.prepend(close, it -> {
      it.lowPriority();
      it.newLine();
    });
  }

  /**
   * Global formatting to be applied across the whole source.
   *
   * @param requestRoot
   *          the top level check catalog element.
   * @param document
   *          the formattable document.
   */
  // CHECKSTYLE:CHECK-OFF MagicNumber
  private void globalFormatting(final IEObjectRegion requestRoot, final IFormattableDocument document) {
    // autowrap everywhere. default to one-space between semantic regions.
    // low priority so that it can be overridden by other custom formatting rules.
    boolean firstRegion = true;
    for (final ISemanticRegion region : requestRoot.getAllSemanticRegions()) {
      if (firstRegion) {
        document.prepend(region, it -> {
          it.lowPriority();
          it.autowrap(132);
        });
        firstRegion = false;
      } else {
        document.prepend(region, it -> {
          it.lowPriority();
          it.oneSpace();
          it.autowrap(132);
        });
      }
    }
  }
  // CHECKSTYLE:CHECK-ON MagicNumber

  protected void _format(final CheckCatalog checkcatalog, final IFormattableDocument document) {
    document.prepend(checkcatalog, it -> {
      it.noSpace();
      it.setNewLines(0);
    });
    document.append(checkcatalog, it -> {
      it.noSpace();
      it.setNewLines(0, 0, 1);
    });
    final ISemanticRegion finalKw = regionFor(checkcatalog).keyword("final");
    final ISemanticRegion catalog = regionFor(checkcatalog).keyword("catalog");
    if (finalKw != null) {
      document.prepend(finalKw, it -> {
        it.setNewLines(1, 2, 2);
      });
    } else {
      document.prepend(catalog, it -> {
        it.setNewLines(1, 1, 2);
      });
    }
    final ISemanticRegion forKw = regionFor(checkcatalog).keyword("for");
    document.prepend(forKw, it -> {
      it.setNewLines(1, 1, 2);
    });
    formatCurlyBracket(checkcatalog, document);

    // Generated model traversal
    this.format(checkcatalog.getImports(), document);
    for (final Category categories : checkcatalog.getCategories()) {
      this.format(categories, document);
    }
    for (final Implementation implementations : checkcatalog.getImplementations()) {
      this.format(implementations, document);
    }
    for (final Check checks : checkcatalog.getChecks()) {
      this.format(checks, document);
    }
    for (final Member members : checkcatalog.getMembers()) {
      this.format(members, document);
    }

    // ADDED: only fill in the gaps after any high priority formatting has been applied.
    IEObjectRegion rootRegion = getTextRegionAccess().regionForRootEObject();
    if (rootRegion != null) {
      globalFormatting(rootRegion, document);
    }
  }

  @Override
  protected void _format(final XImportSection ximportsection, final IFormattableDocument document) {
    // Generated model traversal
    for (final XImportDeclaration importDeclarations : ximportsection.getImportDeclarations()) {
      // ADDED: formatting added before each import
      document.prepend(importDeclarations, it -> {
        it.setNewLines(1, 1, 2);
      });

      this.format(importDeclarations, document);
    }
  }

  protected void _format(final Category category, final IFormattableDocument document) {
    document.prepend(category, it -> {
      it.setNewLines(1, 2, 2);
    });
    formatCurlyBracket(category, document);

    // Generated model traversal
    for (final Check checks : category.getChecks()) {
      this.format(checks, document);
    }
  }

  protected void _format(final Check check, final IFormattableDocument document) {
    document.prepend(check, it -> {
      it.setNewLines(1, 2, 2);
    });
    final ISemanticRegion open = regionFor(check).keyword("(");
    final ISemanticRegion close = regionFor(check).keyword(")");
    document.interior(open, close, it -> {
      it.highPriority();
      it.noSpace();
    }); // High priority to override formatting from adjacent regions and parent formatter.
    final ISemanticRegion message = regionFor(check).keyword("message");
    document.prepend(message, it -> {
      it.setNewLines(1, 1, 2);
    });
    formatCurlyBracket(check, document);

    // Generated model traversal
    this.format(check.getSeverityRange(), document);
    for (final FormalParameter formalParameters : check.getFormalParameters()) {
      // ADDED: formatting added around comma.
      // High priority to override formatting from adjacent regions and parent formatter.
      final ISemanticRegion comma = immediatelyFollowing(formalParameters).keyword(",");
      document.prepend(comma, it -> {
        it.highPriority();
        it.noSpace();
      });
      document.append(comma, it -> {
        it.highPriority();
        it.setNewLines(0, 0, 1);
      });

      this.format(formalParameters, document);
    }
    for (final Context contexts : check.getContexts()) {
      this.format(contexts, document);
    }
  }

  protected void _format(final SeverityRange severityrange, final IFormattableDocument document) {
    final ISemanticRegion range = regionFor(severityrange).keyword("SeverityRange");
    document.surround(range, it -> {
      it.noSpace();
    });
    final ISemanticRegion open = regionFor(severityrange).keyword("(");
    document.append(open, it -> {
      it.noSpace();
    });
    final ISemanticRegion close = regionFor(severityrange).keyword(")");
    document.prepend(close, it -> {
      it.noSpace();
    });
    document.append(close, it -> {
      it.newLine();
    });
  }

  protected void _format(final Member member, final IFormattableDocument document) {
    // Generated model traversal
    for (final XAnnotation annotations : member.getAnnotations()) {
      this.format(annotations, document);
    }
    this.format(member.getType(), document);
    this.format(member.getValue(), document);
  }

  protected void _format(final Implementation implementation, final IFormattableDocument document) {
    document.prepend(implementation, it -> {
      it.setNewLines(1, 2, 2);
    });

    // Generated model traversal
    this.format(implementation.getContext(), document);
  }

  protected void _format(final FormalParameter formalparameter, final IFormattableDocument document) {
    // Generated model traversal
    this.format(formalparameter.getType(), document);
    this.format(formalparameter.getRight(), document);
  }

  protected void _format(final XUnaryOperation xunaryoperation, final IFormattableDocument document) {
    // Generated model traversal
    this.format(xunaryoperation.getOperand(), document);
  }

  protected void _format(final XListLiteral xlistliteral, final IFormattableDocument document) {
    // Generated model traversal
    for (final XExpression elements : xlistliteral.getElements()) {
      this.format(elements, document);
    }
  }

  protected void _format(final Context context, final IFormattableDocument document) {
    document.surround(context, it -> {
      it.setNewLines(1, 2, 2);
    });

    // Generated model traversal
    this.format(context.getContextVariable(), document);
    this.format(context.getConstraint(), document);
  }

  protected void _format(final ContextVariable contextvariable, final IFormattableDocument document) {
    // Generated model traversal
    this.format(contextvariable.getType(), document);
  }

  protected void _format(final XGuardExpression xguardexpression, final IFormattableDocument document) {
    document.prepend(xguardexpression, it -> {
      it.setNewLines(1, 2, 2);
    });

    // Generated model traversal
    this.format(xguardexpression.getGuard(), document);
  }

  protected void _format(final XIssueExpression xissueexpression, final IFormattableDocument document) {
    // High priority to override formatting from adjacent regions and parent formatter.
    document.prepend(xissueexpression, it -> {
      it.highPriority();
      it.setNewLines(1, 2, 2);
    });
    checkGrammarAccess.getXIssueExpressionAccess().findKeywords("#").forEach(kw -> {
      final ISemanticRegion hash = regionFor(xissueexpression).keyword(kw);
      document.surround(hash, it -> {
        it.highPriority();
        it.noSpace();
      });
    });
    final ISemanticRegion openSquare = regionFor(xissueexpression).keyword("[");
    document.surround(openSquare, it -> {
      it.highPriority();
      it.noSpace();
    });
    final ISemanticRegion closeSquare = regionFor(xissueexpression).keyword("]");
    document.prepend(closeSquare, it -> {
      it.highPriority();
      it.noSpace();
    });
    checkGrammarAccess.getXIssueExpressionAccess().findKeywords("(").forEach(kw -> {
      final ISemanticRegion open = regionFor(xissueexpression).keyword(kw);
      document.append(open, it -> {
        it.highPriority();
        it.noSpace();
      });
    });
    checkGrammarAccess.getXIssueExpressionAccess().findKeywords(")").forEach(kw -> {
      final ISemanticRegion close = regionFor(xissueexpression).keyword(kw);
      document.prepend(close, it -> {
        it.highPriority();
        it.noSpace();
      });
    });

    // Generated model traversal
    this.format(xissueexpression.getMarkerObject(), document);
    this.format(xissueexpression.getMarkerIndex(), document);
    this.format(xissueexpression.getMessage(), document);
    for (final XExpression messageParameters : xissueexpression.getMessageParameters()) {
      // ADDED: formatting added around comma
      final ISemanticRegion comma = immediatelyFollowing(messageParameters).keyword(",");
      document.prepend(comma, it -> {
        it.highPriority();
        it.noSpace();
      });
      document.append(comma, it -> {
        it.highPriority();
        it.oneSpace();
      });

      this.format(messageParameters, document);
    }
    for (final XExpression issueData : xissueexpression.getIssueData()) {
      // ADDED: formatting added around comma
      final ISemanticRegion comma = immediatelyFollowing(issueData).keyword(",");
      document.prepend(comma, it -> {
        it.highPriority();
        it.noSpace();
      });
      document.append(comma, it -> {
        it.highPriority();
        it.oneSpace();
      });

      this.format(issueData, document);
    }
  }

  @Override
  protected void _format(final XIfExpression xifexpression, final IFormattableDocument document) {
    // High priority to override formatting from adjacent regions and parent formatter.
    document.prepend(xifexpression, it -> {
      it.highPriority();
      it.setNewLines(1, 1, 2);
    });
    final ISemanticRegion open = regionFor(xifexpression).keyword("(");
    final ISemanticRegion close = regionFor(xifexpression).keyword(")");
    document.prepend(open, it -> {
      it.highPriority();
      it.oneSpace();
    });
    document.append(open, it -> {
      it.highPriority();
      it.noSpace();
    });
    document.prepend(close, it -> {
      it.highPriority();
      it.noSpace();
    });
    document.append(close, it -> {
      it.highPriority();
      it.setNewLines(0);
      it.oneSpace();
    });
    final ISemanticRegion elseKw = regionFor(xifexpression).keyword("else");
    document.surround(elseKw, it -> {
      it.highPriority();
      it.setNewLines(0);
      it.oneSpace();
    });

    // defer to super class for model traversal
    super._format(xifexpression, document);
  }

  @Override
  protected void _format(final XMemberFeatureCall xfeaturecall, final IFormattableDocument document) {
    // set no space after '::' in CheckUtil::hasQualifiedName(..., and also not after plain "." or "?."
    // High priority to override formatting from adjacent regions and parent formatter.
    checkGrammarAccess.getXMemberFeatureCallAccess().findKeywords(".").forEach(kw -> {
      final ISemanticRegion dot = regionFor(xfeaturecall).keyword(kw);
      document.append(dot, it -> {
        it.highPriority();
        it.noSpace();
      });
    });
    checkGrammarAccess.getXMemberFeatureCallAccess().findKeywords("?.").forEach(kw -> {
      final ISemanticRegion queryDot = regionFor(xfeaturecall).keyword(kw);
      document.append(queryDot, it -> {
        it.highPriority();
        it.noSpace();
      });
    });
    checkGrammarAccess.getXMemberFeatureCallAccess().findKeywords("::").forEach(kw -> {
      final ISemanticRegion colonColon = regionFor(xfeaturecall).keyword(kw);
      document.append(colonColon, it -> {
        it.highPriority();
        it.noSpace();
      });
    });

    // defer to super class for model traversal
    super._format(xfeaturecall, document);
  }

  @Override
  public void format(final Object element, final IFormattableDocument document) {
    switch (element) {
      case JvmTypeParameter jvmTypeParameter -> _format(jvmTypeParameter, document);
      case JvmFormalParameter jvmFormalParameter -> _format(jvmFormalParameter, document);
      case XtextResource xtextResource -> _format(xtextResource, document);
      case XAssignment xAssignment -> _format(xAssignment, document);
      case XBinaryOperation xBinaryOperation -> _format(xBinaryOperation, document);
      case XDoWhileExpression xDoWhileExpression -> _format(xDoWhileExpression, document);
      case XFeatureCall xFeatureCall -> _format(xFeatureCall, document);
      case XListLiteral xListLiteral -> _format(xListLiteral, document);
      case XMemberFeatureCall xMemberFeatureCall -> _format(xMemberFeatureCall, document);
      case XPostfixOperation xPostfixOperation -> _format(xPostfixOperation, document);
      case XUnaryOperation xUnaryOperation -> _format(xUnaryOperation, document);
      case XWhileExpression xWhileExpression -> _format(xWhileExpression, document);
      case XFunctionTypeRef xFunctionTypeRef -> _format(xFunctionTypeRef, document);
      case Category category -> _format(category, document);
      case Check check -> _format(check, document);
      case CheckCatalog checkCatalog -> _format(checkCatalog, document);
      case Context context -> _format(context, document);
      case Implementation implementation -> _format(implementation, document);
      case Member member -> _format(member, document);
      case XGuardExpression xGuardExpression -> _format(xGuardExpression, document);
      case XIssueExpression xIssueExpression -> _format(xIssueExpression, document);
      case JvmGenericArrayTypeReference jvmGenericArrayTypeReference -> _format(jvmGenericArrayTypeReference, document);
      case JvmParameterizedTypeReference jvmParameterizedTypeReference -> _format(jvmParameterizedTypeReference, document);
      case JvmWildcardTypeReference jvmWildcardTypeReference -> _format(jvmWildcardTypeReference, document);
      case XBasicForLoopExpression xBasicForLoopExpression -> _format(xBasicForLoopExpression, document);
      case XBlockExpression xBlockExpression -> _format(xBlockExpression, document);
      case XCastedExpression xCastedExpression -> _format(xCastedExpression, document);
      case XClosure xClosure -> _format(xClosure, document);
      case XCollectionLiteral xCollectionLiteral -> _format(xCollectionLiteral, document);
      case XConstructorCall xConstructorCall -> _format(xConstructorCall, document);
      case XForLoopExpression xForLoopExpression -> _format(xForLoopExpression, document);
      case XIfExpression xIfExpression -> _format(xIfExpression, document);
      case XInstanceOfExpression xInstanceOfExpression -> _format(xInstanceOfExpression, document);
      case XReturnExpression xReturnExpression -> _format(xReturnExpression, document);
      case XSwitchExpression xSwitchExpression -> _format(xSwitchExpression, document);
      case XSynchronizedExpression xSynchronizedExpression -> _format(xSynchronizedExpression, document);
      case XThrowExpression xThrowExpression -> _format(xThrowExpression, document);
      case XTryCatchFinallyExpression xTryCatchFinallyExpression -> _format(xTryCatchFinallyExpression, document);
      case XTypeLiteral xTypeLiteral -> _format(xTypeLiteral, document);
      case XVariableDeclaration xVariableDeclaration -> _format(xVariableDeclaration, document);
      case XAnnotation xAnnotation -> _format(xAnnotation, document);
      case ContextVariable contextVariable -> _format(contextVariable, document);
      case FormalParameter formalParameter -> _format(formalParameter, document);
      case SeverityRange severityRange -> _format(severityRange, document);
      case JvmTypeConstraint jvmTypeConstraint -> _format(jvmTypeConstraint, document);
      case XExpression xExpression -> _format(xExpression, document);
      case XImportDeclaration xImportDeclaration -> _format(xImportDeclaration, document);
      case XImportSection xImportSection -> _format(xImportSection, document);
      case EObject eObject -> _format(eObject, document);
      case null -> _format((Void) null, document);
      default -> _format(element, document);
    }
  }
}
