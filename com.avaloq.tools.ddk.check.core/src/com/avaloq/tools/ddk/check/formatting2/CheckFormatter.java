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

import java.util.Arrays;

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

@SuppressWarnings({"checkstyle:MethodName"})
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
  // CHECKSTYLE:CHECK-OFF MagicNumber
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
  private void globalFormatting(final IEObjectRegion requestRoot, final IFormattableDocument document) {
    // autowrap everywhere. default to one-space between semantic regions.
    // low priority so that it can be overridden by other custom formatting rules.
    boolean firstRegion = true;
    for (ISemanticRegion region : requestRoot.getAllSemanticRegions()) {
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
    for (Category categories : checkcatalog.getCategories()) {
      this.format(categories, document);
    }
    for (Implementation implementations : checkcatalog.getImplementations()) {
      this.format(implementations, document);
    }
    for (Check checks : checkcatalog.getChecks()) {
      this.format(checks, document);
    }
    for (Member members : checkcatalog.getMembers()) {
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
    for (XImportDeclaration importDeclarations : ximportsection.getImportDeclarations()) {
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
    for (Check checks : category.getChecks()) {
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
    for (FormalParameter formalParameters : check.getFormalParameters()) {
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
    for (Context contexts : check.getContexts()) {
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
    for (XAnnotation annotations : member.getAnnotations()) {
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
    for (XExpression elements : xlistliteral.getElements()) {
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
    for (XExpression messageParameters : xissueexpression.getMessageParameters()) {
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
    for (XExpression issueData : xissueexpression.getIssueData()) {
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
  public void format(final Object xlistliteral, final IFormattableDocument document) {
    if (xlistliteral instanceof JvmTypeParameter) {
      _format((JvmTypeParameter) xlistliteral, document);
    } else if (xlistliteral instanceof JvmFormalParameter) {
      _format((JvmFormalParameter) xlistliteral, document);
    } else if (xlistliteral instanceof XtextResource) {
      _format((XtextResource) xlistliteral, document);
    } else if (xlistliteral instanceof XAssignment) {
      _format((XAssignment) xlistliteral, document);
    } else if (xlistliteral instanceof XBinaryOperation) {
      _format((XBinaryOperation) xlistliteral, document);
    } else if (xlistliteral instanceof XDoWhileExpression) {
      _format((XDoWhileExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XFeatureCall) {
      _format((XFeatureCall) xlistliteral, document);
    } else if (xlistliteral instanceof XListLiteral) {
      _format((XListLiteral) xlistliteral, document);
    } else if (xlistliteral instanceof XMemberFeatureCall) {
      _format((XMemberFeatureCall) xlistliteral, document);
    } else if (xlistliteral instanceof XPostfixOperation) {
      _format((XPostfixOperation) xlistliteral, document);
    } else if (xlistliteral instanceof XUnaryOperation) {
      _format((XUnaryOperation) xlistliteral, document);
    } else if (xlistliteral instanceof XWhileExpression) {
      _format((XWhileExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XFunctionTypeRef) {
      _format((XFunctionTypeRef) xlistliteral, document);
    } else if (xlistliteral instanceof Category) {
      _format((Category) xlistliteral, document);
    } else if (xlistliteral instanceof Check) {
      _format((Check) xlistliteral, document);
    } else if (xlistliteral instanceof CheckCatalog) {
      _format((CheckCatalog) xlistliteral, document);
    } else if (xlistliteral instanceof Context) {
      _format((Context) xlistliteral, document);
    } else if (xlistliteral instanceof Implementation) {
      _format((Implementation) xlistliteral, document);
    } else if (xlistliteral instanceof Member) {
      _format((Member) xlistliteral, document);
    } else if (xlistliteral instanceof XGuardExpression) {
      _format((XGuardExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XIssueExpression) {
      _format((XIssueExpression) xlistliteral, document);
    } else if (xlistliteral instanceof JvmGenericArrayTypeReference) {
      _format((JvmGenericArrayTypeReference) xlistliteral, document);
    } else if (xlistliteral instanceof JvmParameterizedTypeReference) {
      _format((JvmParameterizedTypeReference) xlistliteral, document);
    } else if (xlistliteral instanceof JvmWildcardTypeReference) {
      _format((JvmWildcardTypeReference) xlistliteral, document);
    } else if (xlistliteral instanceof XBasicForLoopExpression) {
      _format((XBasicForLoopExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XBlockExpression) {
      _format((XBlockExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XCastedExpression) {
      _format((XCastedExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XClosure) {
      _format((XClosure) xlistliteral, document);
    } else if (xlistliteral instanceof XCollectionLiteral) {
      _format((XCollectionLiteral) xlistliteral, document);
    } else if (xlistliteral instanceof XConstructorCall) {
      _format((XConstructorCall) xlistliteral, document);
    } else if (xlistliteral instanceof XForLoopExpression) {
      _format((XForLoopExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XIfExpression) {
      _format((XIfExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XInstanceOfExpression) {
      _format((XInstanceOfExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XReturnExpression) {
      _format((XReturnExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XSwitchExpression) {
      _format((XSwitchExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XSynchronizedExpression) {
      _format((XSynchronizedExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XThrowExpression) {
      _format((XThrowExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XTryCatchFinallyExpression) {
      _format((XTryCatchFinallyExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XTypeLiteral) {
      _format((XTypeLiteral) xlistliteral, document);
    } else if (xlistliteral instanceof XVariableDeclaration) {
      _format((XVariableDeclaration) xlistliteral, document);
    } else if (xlistliteral instanceof XAnnotation) {
      _format((XAnnotation) xlistliteral, document);
    } else if (xlistliteral instanceof ContextVariable) {
      _format((ContextVariable) xlistliteral, document);
    } else if (xlistliteral instanceof FormalParameter) {
      _format((FormalParameter) xlistliteral, document);
    } else if (xlistliteral instanceof SeverityRange) {
      _format((SeverityRange) xlistliteral, document);
    } else if (xlistliteral instanceof JvmTypeConstraint) {
      _format((JvmTypeConstraint) xlistliteral, document);
    } else if (xlistliteral instanceof XExpression) {
      _format((XExpression) xlistliteral, document);
    } else if (xlistliteral instanceof XImportDeclaration) {
      _format((XImportDeclaration) xlistliteral, document);
    } else if (xlistliteral instanceof XImportSection) {
      _format((XImportSection) xlistliteral, document);
    } else if (xlistliteral instanceof EObject) {
      _format((EObject) xlistliteral, document);
    } else if (xlistliteral == null) {
      _format((Void) null, document);
    } else if (xlistliteral != null) {
      _format(xlistliteral, document);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(xlistliteral, document).toString());
    }
  }
}
