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
package com.avaloq.tools.ddk.check.formatting2;

import com.avaloq.tools.ddk.check.check.Category
import com.avaloq.tools.ddk.check.check.Check
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.check.Context
import com.avaloq.tools.ddk.check.check.ContextVariable
import com.avaloq.tools.ddk.check.check.FormalParameter
import com.avaloq.tools.ddk.check.check.Implementation
import com.avaloq.tools.ddk.check.check.Member
import com.avaloq.tools.ddk.check.check.SeverityRange
import com.avaloq.tools.ddk.check.check.XGuardExpression
import com.avaloq.tools.ddk.check.check.XIssueExpression
import com.avaloq.tools.ddk.check.services.CheckGrammarAccess
import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.XIfExpression
import org.eclipse.xtext.xbase.XListLiteral
import org.eclipse.xtext.xbase.XMemberFeatureCall
import org.eclipse.xtext.xbase.XUnaryOperation
import org.eclipse.xtext.xbase.annotations.formatting2.XbaseWithAnnotationsFormatter
import org.eclipse.xtext.xbase.annotations.xAnnotations.XAnnotation
import org.eclipse.xtext.xtype.XImportDeclaration
import org.eclipse.xtext.xtype.XImportSection

class CheckFormatter extends XbaseWithAnnotationsFormatter {

  @Inject extension CheckGrammarAccess

  /**
   * Common formatting for curly brackets that are not handled by the parent formatter.
   *
   * @param semanticElement
   *          the element containing '{' and '}' keywords.
   * @param document
   *          the formattable document.
   */
  def private void formatCurlyBracket(EObject semanticElement, extension IFormattableDocument document) {
    // low priority so that it can be overridden by other custom formatting rules.
    val open = semanticElement.regionFor.keyword('{')
    val close = semanticElement.regionFor.keyword('}')
    interior(open, close)[lowPriority indent]
    append(open)[lowPriority newLine]
    prepend(close)[lowPriority newLine]
  }

  /**
   * Global formatting to be applied across the whole source.
   *
   * @param checkcatalog
   *          the top level check catalog element.
   * @param document
   *          the formattable document.
   */
  def private void globalFormatting(CheckCatalog checkcatalog, extension IFormattableDocument document) {
    // autowrap everywhere. default to one-space between semantic regions.
    // low priority so that it can be overridden by other custom formatting rules.
    var firstRegion = true
    for(region : checkcatalog.regionForEObject.allSemanticRegions) {
      if (firstRegion) {
        region.prepend[lowPriority autowrap(132)]
        firstRegion = false
      } else {
        region.prepend[lowPriority oneSpace autowrap(132)]
      }
    }
  }

  def dispatch void format(CheckCatalog checkcatalog, extension IFormattableDocument document) {
    prepend(checkcatalog)[noSpace newLines=0]
    append(checkcatalog)[noSpace setNewLines(0, 0, 1)]
    val finalKw = checkcatalog.regionFor.keyword('final')
    val catalog = checkcatalog.regionFor.keyword('catalog')
    if (finalKw !== null) {
      prepend(finalKw)[setNewLines(1, 2, 2)]
    } else {
      prepend(catalog)[setNewLines(1, 1, 2)]
    }
    val forKw = checkcatalog.regionFor.keyword('for')
    prepend(forKw)[setNewLines(1, 1, 2)]
    formatCurlyBracket(checkcatalog, document)

    // Generated model traversal
    format(checkcatalog.getImports(), document);
    for (Category categories : checkcatalog.getCategories()) {
      format(categories, document);
    }
    for (Implementation implementations : checkcatalog.getImplementations()) {
      format(implementations, document);
    }
    for (Check checks : checkcatalog.getChecks()) {
      format(checks, document);
    }
    for (Member members : checkcatalog.getMembers()) {
      format(members, document);
    }

    // ADDED: only fill in the gaps after any high priority formatting has been applied.
    globalFormatting(checkcatalog, document)
  }

  override dispatch void format(XImportSection ximportsection, extension IFormattableDocument document) {
    // Generated model traversal
    for (XImportDeclaration importDeclarations : ximportsection.getImportDeclarations()) {
      // ADDED: formatting added before each import
      prepend(importDeclarations)[setNewLines(1, 1, 2)]

      format(importDeclarations, document);
    }
  }

  def dispatch void format(Category category, extension IFormattableDocument document) {
    prepend(category)[setNewLines(1, 2, 2)]
    formatCurlyBracket(category, document)

    // Generated model traversal
    for (Check checks : category.getChecks()) {
      format(checks, document);
    }
  }

  def dispatch void format(Check check, extension IFormattableDocument document) {
    prepend(check)[setNewLines(1, 2, 2)]
    val open = check.regionFor.keyword('(')
    val close = check.regionFor.keyword(')')
    interior(open, close)[highPriority noSpace] // High priority to override formatting from adjacent regions and parent formatter.
    val message = check.regionFor.keyword('message')
    prepend(message)[setNewLines(1, 1, 2)]
    formatCurlyBracket(check, document)

    // Generated model traversal
    format(check.getSeverityRange(), document);
    for (FormalParameter formalParameters : check.getFormalParameters()) {
      // ADDED: formatting added around comma.
      // High priority to override formatting from adjacent regions and parent formatter.
      val comma = immediatelyFollowing(formalParameters).keyword(',')
      prepend(comma)[highPriority noSpace]
      append(comma)[highPriority setNewLines(0, 0, 1)]

      format(formalParameters, document);
    }
    for (Context contexts : check.getContexts()) {
      format(contexts, document);
    }
  }

  def dispatch void format(SeverityRange severityrange, extension IFormattableDocument document) {
    val range = severityrange.regionFor.keyword('SeverityRange')
    surround(range)[noSpace]
    val open = severityrange.regionFor.keyword('(')
    append(open)[noSpace]
    val close = severityrange.regionFor.keyword(')')
    prepend(close)[noSpace]
    append(close)[newLine]
  }

  def dispatch void format(Member member, extension IFormattableDocument document) {
    // Generated model traversal
    for (XAnnotation annotations : member.getAnnotations()) {
      format(annotations, document);
    }
    format(member.getType(), document);
    format(member.getValue(), document);
  }

  def dispatch void format(Implementation implementation, extension IFormattableDocument document) {
    prepend(implementation)[setNewLines(1, 2, 2)]

    // Generated model traversal
    format(implementation.getContext(), document);
  }

  def dispatch void format(FormalParameter formalparameter, extension IFormattableDocument document) {
    // Generated model traversal
    format(formalparameter.getType(), document);
    format(formalparameter.getRight(), document);
  }

  def dispatch void format(XUnaryOperation xunaryoperation, extension IFormattableDocument document) {
    // Generated model traversal
    format(xunaryoperation.getOperand(), document);
  }

  def dispatch void format(XListLiteral xlistliteral, extension IFormattableDocument document) {
    // Generated model traversal
    for (XExpression elements : xlistliteral.getElements()) {
      format(elements, document);
    }
  }

  def dispatch void format(Context context, extension IFormattableDocument document) {
    surround(context)[setNewLines(1, 2, 2)]

    // Generated model traversal
    format(context.getContextVariable(), document);
    format(context.getConstraint(), document);
  }

  def dispatch void format(ContextVariable contextvariable, extension IFormattableDocument document) {
    // Generated model traversal
    format(contextvariable.getType(), document);
  }

  def dispatch void format(XGuardExpression xguardexpression, extension IFormattableDocument document) {
    prepend(xguardexpression)[setNewLines(1, 2, 2)]

    // Generated model traversal
    format(xguardexpression.getGuard(), document);
  }

  def dispatch void format(XIssueExpression xissueexpression, extension IFormattableDocument document) {
    // High priority to override formatting from adjacent regions and parent formatter.
    prepend(xissueexpression)[highPriority setNewLines(1, 2, 2)]
    XIssueExpressionAccess.findKeywords('#').forEach[
      val hash = xissueexpression.regionFor.keyword(it)
      surround(hash)[highPriority noSpace]
    ]
    val openSquare = xissueexpression.regionFor.keyword('[')
    surround(openSquare)[highPriority noSpace]
    val closeSquare = xissueexpression.regionFor.keyword(']')
    prepend(closeSquare)[highPriority noSpace]
    XIssueExpressionAccess.findKeywords('(').forEach[
      val open = xissueexpression.regionFor.keyword(it)
      append(open)[highPriority noSpace]
    ]
    XIssueExpressionAccess.findKeywords(')').forEach[
      val close = xissueexpression.regionFor.keyword(it)
      prepend(close)[highPriority noSpace]
    ]

    // Generated model traversal
    format(xissueexpression.getMarkerObject(), document);
    format(xissueexpression.getMarkerIndex(), document);
    format(xissueexpression.getMessage(), document);
    for (XExpression messageParameters : xissueexpression.getMessageParameters()) {
      // ADDED: formatting added around comma
      val comma = immediatelyFollowing(messageParameters).keyword(',')
      prepend(comma)[highPriority noSpace]
      append(comma)[highPriority oneSpace]

      format(messageParameters, document);
    }
    for (XExpression issueData : xissueexpression.getIssueData()) {
      // ADDED: formatting added around comma
      val comma = immediatelyFollowing(issueData).keyword(',')
      prepend(comma)[highPriority noSpace]
      append(comma)[highPriority oneSpace]

      format(issueData, document);
    }
  }

  override dispatch void format(XIfExpression xifexpression, extension IFormattableDocument document) {
    // High priority to override formatting from adjacent regions and parent formatter.
    prepend(xifexpression)[highPriority setNewLines(1, 1, 2)]
    val open = xifexpression.regionFor.keyword('(')
    val close = xifexpression.regionFor.keyword(')')
    prepend(open)[highPriority oneSpace]
    append(open)[highPriority noSpace]
    prepend(close)[highPriority noSpace]
    append(close)[highPriority newLines=0 oneSpace]
    val elseKw = xifexpression.regionFor.keyword('else')
    surround(elseKw)[highPriority  newLines=0 oneSpace]

    // defer to super class for model traversal
    super._format(xifexpression, document)
  }

  override dispatch void format(XMemberFeatureCall xfeaturecall, extension IFormattableDocument document) {
    // set no space after '::' in CheckUtil::hasQualifiedName(..., and also not after plain "." or "?."
    // High priority to override formatting from adjacent regions and parent formatter.
    XMemberFeatureCallAccess.findKeywords('.').forEach[
      val dot = xfeaturecall.regionFor.keyword(it)
      append(dot)[highPriority noSpace]
    ]
    XMemberFeatureCallAccess.findKeywords('?.').forEach[
      val queryDot = xfeaturecall.regionFor.keyword(it)
      append(queryDot)[highPriority noSpace]
    ]
    XMemberFeatureCallAccess.findKeywords('::').forEach[
      val colonColon = xfeaturecall.regionFor.keyword(it)
      append(colonColon)[highPriority noSpace]
    ]

    // defer to super class for model traversal
    super._format(xfeaturecall, document)
  }
}
