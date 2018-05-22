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
package com.avaloq.tools.ddk.check.generator

import com.avaloq.tools.ddk.check.check.Check
import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.avaloq.tools.ddk.check.check.Context
import com.avaloq.tools.ddk.check.check.Implementation
import com.avaloq.tools.ddk.check.check.TriggerKind
import com.avaloq.tools.ddk.check.check.XIssueExpression
import com.google.common.collect.Iterables
import com.google.common.collect.Sets
import com.google.common.io.CharStreams
import java.io.InputStreamReader
import java.io.StringReader
import java.util.Set
import java.util.regex.Pattern
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.emf.ecore.EObject
import org.eclipse.jdt.internal.ui.text.javadoc.JavaDoc2HTMLTextReader
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.CheckType

import static extension com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.*

class CheckGeneratorExtensions {

  def dispatch String qualifiedIssueCodeName(XIssueExpression issue) {
    val result = issue.issueCode()
    if (result === null) {
      null
    } else {
      issue.parent(typeof(CheckCatalog)).issueCodesClassName + '.' + result
    }
  }

  /* Returns the qualified Java name for an issue code. */
  def dispatch String qualifiedIssueCodeName(Context context) {
    context.parent(typeof(CheckCatalog)).issueCodesClassName + '.' + context.issueCode
  }

  /* Gets the simple issue code name for a check. */
  def static dispatch String issueCode(Check check) {
    if (null !== check.name) {
      check.name.splitCamelCase.toUpperCase
    } else {
      "ERROR_ISSUE_CODE_NAME_CHECK" // should only happen if the ID is missing, which will fail a validation
    }
  }

  /* Gets the simple issue code name for an issue expression. */
  def static dispatch String issueCode(XIssueExpression issue) {
    if (issue.issueCode !== null) {
      issue.issueCode.splitCamelCase.toUpperCase
    } else if (issue.check !== null && !issue.check.eIsProxy) {
      issueCode(issue.check)
    } else if (issue.parent(Check) !== null) {
      issueCode(issue.parent(Check))
    } else {
      "ERROR_ISSUE_CODE_NAME_XISSUEEXPRESSION" // should not happen
    }
  }

  def static issueCodePrefix(CheckCatalog catalog) {
    catalog.packageName + "." + catalog.issueCodesClassName + "."
  }

  /* Returns the <b>value</b> of an issue code. */
  def static issueCodeValue(EObject object, String issueCode) {
    val catalog = object.parent(typeof(CheckCatalog))
    catalog.issueCodePrefix + issueCode.replaceAll("_", ".").toLowerCase
  }

  /* Gets the issue label for a Check. */
  def dispatch String issueLabel(Check check) {
    check.label
  }

  /* Gets the issue label for an issue expression. */
  def dispatch String issueLabel(XIssueExpression issue) {
    if (issue.check !== null && !issue.check.eIsProxy) {
      issueLabel(issue.check)
    } else if (issue.parent(Check) !== null) {
      issueLabel(issue.parent(Check))
    } else {
      "ERROR_ISSUE_LABEL_XISSUEEXPRESSION" // should not happen
    }
  }

  /* Converts a string such as "AbcDef" to "ABC_DEF". */
  def static String splitCamelCase(String string) {
    string.replaceAll(
      String::format(
        "%s|%s|%s",
        "(?<=[A-Z])(?=[A-Z][a-z])",
        "(?<=[^A-Z_])(?=[A-Z])",
        "(?<=[A-Za-z])(?=[^A-Za-z_])"
      ),
      "_"
    )
  }

  def checkType(Context context) {
    val kind = if (context.eContainer instanceof Check) {
        (context.eContainer as Check).kind
      } else {
        TriggerKind::FAST // TODO handle the case of independent check implementations
      }

    return "CheckType." + switch (kind) {
      case TriggerKind::EXPENSIVE: CheckType::EXPENSIVE
      case TriggerKind::NORMAL: CheckType::NORMAL
      case TriggerKind::FAST: CheckType::FAST
    }.toString
  }

  def issues(EObject object) {
    EcoreUtil2::eAllContents(object).filter(typeof(XIssueExpression))
  }

  def issues(CheckCatalog catalog) {
    catalog.allChecks.map(check|check.issues).flatten
  }

  def issues(Implementation implementation) {
    implementation.context.issues
  }

  /* Returns all Check and Implementation Issues for a CheckCatalog. Issues are not necessarily unique. */
  def checkAndImplementationIssues(CheckCatalog catalog) {
    val checkIssues = catalog.issues // Issues for all Checks
    val implIssues = catalog.implementations.map(impl|impl.issues).flatten // Issues for all Implementations
    return Iterables::concat(checkIssues, implIssues) // all Issue instances
  }

  def issuedCheck(XIssueExpression expression) {
    if (expression.check !== null) {
      expression.check
    } else {
      val containerCheck = EcoreUtil2::getContainerOfType(expression, typeof(Check))
      if (containerCheck !== null) {
        containerCheck
        //TODO we obviously need a validation in the language so that there is always a value here!
      }
    }
  }

  /**
   * Gets the IFile which is associated with given object's eResource, or <code>null</code> if none
   * could be determined.
   */
  def IFile fileForObject(EObject object) {
    val res = object.eResource
    if (res.URI.platform) {
      return ResourcesPlugin::workspace.root.findMember(res.URI.toPlatformString(true)) as IFile
    }
    return null
  }

  /**
   * Gets the IProject which is associated with a given EObject or <code>null</code>
   * if none could be determined.
   */
  def IProject projectForObject(EObject object) {
    return object?.fileForObject?.project
  }

  /**
   * Gets the name of the project in which given object is contained.
   */
  def String bundleName(EObject object) {
    val proj = object.projectForObject
    if (proj !== null) {
      return proj.name
    }
    return null
  }

  /*
   *  Replace binding placeholders of a message with "...".
   */
  def String replacePlaceholder(String message) {
    val p = Pattern::compile("\\{[0-9]+\\}")
    val m = p.matcher(message)
    m.replaceAll("...")
  }

  /*
   *  Format the Check description for Eclipse Help
   */
  def String formatDescription(String comment) {
    if (comment === null) {
      return null
    }
    try {
      val reader = new JavaDoc2HTMLTextReader(new StringReader(comment))
      return reader.string
    } catch (Exception e) {
      return null
    }
  }

  def Set<String> getContents(CheckCatalog catalog, String path) {
    val project = catalog.projectForObject
    if (project !== null) { // In some compiler tests we may not have a project.
      val file = project.getFile(new Path(path))
      if (file.exists) {
        val reader = new InputStreamReader(file.getContents())
        try {
          val content = CharStreams::readLines(reader)
          return Sets.<String>newTreeSet(content)
        } finally {
          reader.close
        }
      }
    }
    newLinkedHashSet()
  }

}

