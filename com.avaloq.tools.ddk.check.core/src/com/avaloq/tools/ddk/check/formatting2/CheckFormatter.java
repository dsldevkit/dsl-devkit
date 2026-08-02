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
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericArrayTypeReference;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeConstraint;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmWildcardTypeReference;
import org.eclipse.xtext.formatting2.IFormattableDocument;
import org.eclipse.xtext.formatting2.IHiddenRegionFormatter;
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
import org.eclipse.xtext.xbase.lib.XbaseGenerated;
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
    document.interior(open, close, (IHiddenRegionFormatter it) -> {
      it.lowPriority();
      it.indent();
    });
    document.append(open, (IHiddenRegionFormatter it) -> {
      it.lowPriority();
      it.newLine();
    });
    document.prepend(close, (IHiddenRegionFormatter it) -> {
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
        document.prepend(region, (IHiddenRegionFormatter it) -> {
          it.lowPriority();
          it.autowrap(132);
        });
        firstRegion = false;
      } else {
        document.prepend(region, (IHiddenRegionFormatter it) -> {
          it.lowPriority();
          it.oneSpace();
          it.autowrap(132);
        });
      }
    }
  }
  // CHECKSTYLE:CHECK-ON MagicNumber

  protected void _format(final CheckCatalog checkcatalog, final IFormattableDocument document) {
    document.prepend(checkcatalog, (IHiddenRegionFormatter it) -> {
      it.noSpace();
      it.setNewLines(0);
    });
    document.append(checkcatalog, (IHiddenRegionFormatter it) -> {
      it.noSpace();
      it.setNewLines(0, 0, 1);
    });
    final ISemanticRegion finalKw = regionFor(checkcatalog).keyword("final");
    final ISemanticRegion catalog = regionFor(checkcatalog).keyword("catalog");
    if (finalKw != null) {
      document.prepend(finalKw, (IHiddenRegionFormatter it) -> {
        it.setNewLines(1, 2, 2);
      });
    } else {
      document.prepend(catalog, (IHiddenRegionFormatter it) -> {
        it.setNewLines(1, 1, 2);
      });
    }
    final ISemanticRegion forKw = regionFor(checkcatalog).keyword("for");
    document.prepend(forKw, (IHiddenRegionFormatter it) -> {
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
      document.prepend(importDeclarations, (IHiddenRegionFormatter it) -> {
        it.setNewLines(1, 1, 2);
      });

      this.format(importDeclarations, document);
    }
  }

  protected void _format(final Category category, final IFormattableDocument document) {
    document.prepend(category, (IHiddenRegionFormatter it) -> {
      it.setNewLines(1, 2, 2);
    });
    formatCurlyBracket(category, document);

    // Generated model traversal
    for (final Check checks : category.getChecks()) {
      this.format(checks, document);
    }
  }

  protected void _format(final Check check, final IFormattableDocument document) {
    document.prepend(check, (IHiddenRegionFormatter it) -> {
      it.setNewLines(1, 2, 2);
    });
    final ISemanticRegion open = regionFor(check).keyword("(");
    final ISemanticRegion close = regionFor(check).keyword(")");
    document.interior(open, close, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.noSpace();
    }); // High priority to override formatting from adjacent regions and parent formatter.
    final ISemanticRegion message = regionFor(check).keyword("message");
    document.prepend(message, (IHiddenRegionFormatter it) -> {
      it.setNewLines(1, 1, 2);
    });
    formatCurlyBracket(check, document);

    // Generated model traversal
    this.format(check.getSeverityRange(), document);
    for (final FormalParameter formalParameters : check.getFormalParameters()) {
      // ADDED: formatting added around comma.
      // High priority to override formatting from adjacent regions and parent formatter.
      final ISemanticRegion comma = immediatelyFollowing(formalParameters).keyword(",");
      document.prepend(comma, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
      document.append(comma, (IHiddenRegionFormatter it) -> {
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
    document.surround(range, (IHiddenRegionFormatter it) -> {
      it.noSpace();
    });
    final ISemanticRegion open = regionFor(severityrange).keyword("(");
    document.append(open, (IHiddenRegionFormatter it) -> {
      it.noSpace();
    });
    final ISemanticRegion close = regionFor(severityrange).keyword(")");
    document.prepend(close, (IHiddenRegionFormatter it) -> {
      it.noSpace();
    });
    document.append(close, (IHiddenRegionFormatter it) -> {
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
    document.prepend(implementation, (IHiddenRegionFormatter it) -> {
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
    document.surround(context, (IHiddenRegionFormatter it) -> {
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
    document.prepend(xguardexpression, (IHiddenRegionFormatter it) -> {
      it.setNewLines(1, 2, 2);
    });

    // Generated model traversal
    this.format(xguardexpression.getGuard(), document);
  }

  protected void _format(final XIssueExpression xissueexpression, final IFormattableDocument document) {
    // High priority to override formatting from adjacent regions and parent formatter.
    document.prepend(xissueexpression, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.setNewLines(1, 2, 2);
    });
    checkGrammarAccess.getXIssueExpressionAccess().findKeywords("#").forEach((Keyword kw) -> {
      final ISemanticRegion hash = regionFor(xissueexpression).keyword(kw);
      document.surround(hash, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
    });
    final ISemanticRegion openSquare = regionFor(xissueexpression).keyword("[");
    document.surround(openSquare, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.noSpace();
    });
    final ISemanticRegion closeSquare = regionFor(xissueexpression).keyword("]");
    document.prepend(closeSquare, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.noSpace();
    });
    checkGrammarAccess.getXIssueExpressionAccess().findKeywords("(").forEach((Keyword kw) -> {
      final ISemanticRegion open = regionFor(xissueexpression).keyword(kw);
      document.append(open, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
    });
    checkGrammarAccess.getXIssueExpressionAccess().findKeywords(")").forEach((Keyword kw) -> {
      final ISemanticRegion close = regionFor(xissueexpression).keyword(kw);
      document.prepend(close, (IHiddenRegionFormatter it) -> {
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
      document.prepend(comma, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
      document.append(comma, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.oneSpace();
      });

      this.format(messageParameters, document);
    }
    for (final XExpression issueData : xissueexpression.getIssueData()) {
      // ADDED: formatting added around comma
      final ISemanticRegion comma = immediatelyFollowing(issueData).keyword(",");
      document.prepend(comma, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
      document.append(comma, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.oneSpace();
      });

      this.format(issueData, document);
    }
  }

  @Override
  protected void _format(final XIfExpression xifexpression, final IFormattableDocument document) {
    // High priority to override formatting from adjacent regions and parent formatter.
    document.prepend(xifexpression, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.setNewLines(1, 1, 2);
    });
    final ISemanticRegion open = regionFor(xifexpression).keyword("(");
    final ISemanticRegion close = regionFor(xifexpression).keyword(")");
    document.prepend(open, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.oneSpace();
    });
    document.append(open, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.noSpace();
    });
    document.prepend(close, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.noSpace();
    });
    document.append(close, (IHiddenRegionFormatter it) -> {
      it.highPriority();
      it.setNewLines(0);
      it.oneSpace();
    });
    final ISemanticRegion elseKw = regionFor(xifexpression).keyword("else");
    document.surround(elseKw, (IHiddenRegionFormatter it) -> {
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
    checkGrammarAccess.getXMemberFeatureCallAccess().findKeywords(".").forEach((Keyword kw) -> {
      final ISemanticRegion dot = regionFor(xfeaturecall).keyword(kw);
      document.append(dot, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
    });
    checkGrammarAccess.getXMemberFeatureCallAccess().findKeywords("?.").forEach((Keyword kw) -> {
      final ISemanticRegion queryDot = regionFor(xfeaturecall).keyword(kw);
      document.append(queryDot, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
    });
    checkGrammarAccess.getXMemberFeatureCallAccess().findKeywords("::").forEach((Keyword kw) -> {
      final ISemanticRegion colonColon = regionFor(xfeaturecall).keyword(kw);
      document.append(colonColon, (IHiddenRegionFormatter it) -> {
        it.highPriority();
        it.noSpace();
      });
    });

    // defer to super class for model traversal
    super._format(xfeaturecall, document);
  }

  @Override
  @XbaseGenerated
  public void format(final Object element, final IFormattableDocument document) {
    if (element instanceof JvmTypeParameter jvmTypeParameter) {
      _format(jvmTypeParameter, document);
    } else if (element instanceof JvmFormalParameter jvmFormalParameter) {
      _format(jvmFormalParameter, document);
    } else if (element instanceof XtextResource xtextResource) {
      _format(xtextResource, document);
    } else if (element instanceof XAssignment xAssignment) {
      _format(xAssignment, document);
    } else if (element instanceof XBinaryOperation xBinaryOperation) {
      _format(xBinaryOperation, document);
    } else if (element instanceof XDoWhileExpression xDoWhileExpression) {
      _format(xDoWhileExpression, document);
    } else if (element instanceof XFeatureCall xFeatureCall) {
      _format(xFeatureCall, document);
    } else if (element instanceof XListLiteral xListLiteral) {
      _format(xListLiteral, document);
    } else if (element instanceof XMemberFeatureCall xMemberFeatureCall) {
      _format(xMemberFeatureCall, document);
    } else if (element instanceof XPostfixOperation xPostfixOperation) {
      _format(xPostfixOperation, document);
    } else if (element instanceof XUnaryOperation xUnaryOperation) {
      _format(xUnaryOperation, document);
    } else if (element instanceof XWhileExpression xWhileExpression) {
      _format(xWhileExpression, document);
    } else if (element instanceof XFunctionTypeRef xFunctionTypeRef) {
      _format(xFunctionTypeRef, document);
    } else if (element instanceof Category category) {
      _format(category, document);
    } else if (element instanceof Check check) {
      _format(check, document);
    } else if (element instanceof CheckCatalog checkCatalog) {
      _format(checkCatalog, document);
    } else if (element instanceof Context context) {
      _format(context, document);
    } else if (element instanceof Implementation implementation) {
      _format(implementation, document);
    } else if (element instanceof Member member) {
      _format(member, document);
    } else if (element instanceof XGuardExpression xGuardExpression) {
      _format(xGuardExpression, document);
    } else if (element instanceof XIssueExpression xIssueExpression) {
      _format(xIssueExpression, document);
    } else if (element instanceof JvmGenericArrayTypeReference jvmGenericArrayTypeReference) {
      _format(jvmGenericArrayTypeReference, document);
    } else if (element instanceof JvmParameterizedTypeReference jvmParameterizedTypeReference) {
      _format(jvmParameterizedTypeReference, document);
    } else if (element instanceof JvmWildcardTypeReference jvmWildcardTypeReference) {
      _format(jvmWildcardTypeReference, document);
    } else if (element instanceof XBasicForLoopExpression xBasicForLoopExpression) {
      _format(xBasicForLoopExpression, document);
    } else if (element instanceof XBlockExpression xBlockExpression) {
      _format(xBlockExpression, document);
    } else if (element instanceof XCastedExpression xCastedExpression) {
      _format(xCastedExpression, document);
    } else if (element instanceof XClosure xClosure) {
      _format(xClosure, document);
    } else if (element instanceof XCollectionLiteral xCollectionLiteral) {
      _format(xCollectionLiteral, document);
    } else if (element instanceof XConstructorCall xConstructorCall) {
      _format(xConstructorCall, document);
    } else if (element instanceof XForLoopExpression xForLoopExpression) {
      _format(xForLoopExpression, document);
    } else if (element instanceof XIfExpression xIfExpression) {
      _format(xIfExpression, document);
    } else if (element instanceof XInstanceOfExpression xInstanceOfExpression) {
      _format(xInstanceOfExpression, document);
    } else if (element instanceof XReturnExpression xReturnExpression) {
      _format(xReturnExpression, document);
    } else if (element instanceof XSwitchExpression xSwitchExpression) {
      _format(xSwitchExpression, document);
    } else if (element instanceof XSynchronizedExpression xSynchronizedExpression) {
      _format(xSynchronizedExpression, document);
    } else if (element instanceof XThrowExpression xThrowExpression) {
      _format(xThrowExpression, document);
    } else if (element instanceof XTryCatchFinallyExpression xTryCatchFinallyExpression) {
      _format(xTryCatchFinallyExpression, document);
    } else if (element instanceof XTypeLiteral xTypeLiteral) {
      _format(xTypeLiteral, document);
    } else if (element instanceof XVariableDeclaration xVariableDeclaration) {
      _format(xVariableDeclaration, document);
    } else if (element instanceof XAnnotation xAnnotation) {
      _format(xAnnotation, document);
    } else if (element instanceof ContextVariable contextVariable) {
      _format(contextVariable, document);
    } else if (element instanceof FormalParameter formalParameter) {
      _format(formalParameter, document);
    } else if (element instanceof SeverityRange severityRange) {
      _format(severityRange, document);
    } else if (element instanceof JvmTypeConstraint jvmTypeConstraint) {
      _format(jvmTypeConstraint, document);
    } else if (element instanceof XExpression xExpression) {
      _format(xExpression, document);
    } else if (element instanceof XImportDeclaration xImportDeclaration) {
      _format(xImportDeclaration, document);
    } else if (element instanceof XImportSection xImportSection) {
      _format(xImportSection, document);
    } else if (element instanceof EObject eObject) {
      _format(eObject, document);
    } else if (element == null) {
      _format((Void) null, document);
    } else {
      _format(element, document);
    }
  }
}
