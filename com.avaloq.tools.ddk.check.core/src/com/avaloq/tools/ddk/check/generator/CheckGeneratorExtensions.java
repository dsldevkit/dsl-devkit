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
package com.avaloq.tools.ddk.check.generator;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.TriggerKind;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.util.CheckUtil;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.internal.ui.text.javadoc.JavaDoc2HTMLTextReader;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.xbase.lib.ListExtensions;

import static com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.*;

public class CheckGeneratorExtensions {

  protected String _qualifiedIssueCodeName(XIssueExpression issue) {
    String result = issueCode(issue);
    if (result == null) {
      return null;
    } else {
      return issueCodesClassName(parent(issue, CheckCatalog.class)) + "." + result;
    }
  }

  /* Returns the qualified Java name for an issue code. */
  protected String _qualifiedIssueCodeName(Context context) {
    return issueCodesClassName(parent(context, CheckCatalog.class)) + "." + issueCode(context);
  }

  /* Gets the simple issue code name for a check. */
  protected static String _issueCode(Check check) {
    if (null != check.getName()) {
      return splitCamelCase(check.getName()).toUpperCase();
    } else {
      return "ERROR_ISSUE_CODE_NAME_CHECK"; // should only happen if the ID is missing, which will fail a validation
    }
  }

  /* Gets the simple issue code name for an issue expression. */
  protected static String _issueCode(XIssueExpression issue) {
    if (issue.getIssueCode() != null) {
      return splitCamelCase(issue.getIssueCode()).toUpperCase();
    } else if (issue.getCheck() != null && !issue.getCheck().eIsProxy()) {
      return issueCode(issue.getCheck());
    } else if (parent(issue, Check.class) != null) {
      return issueCode(parent(issue, Check.class));
    } else {
      return "ERROR_ISSUE_CODE_NAME_XISSUEEXPRESSION"; // should not happen
    }
  }

  /* Gets the simple issue code name for a check. */
  protected static String _issueName(Check check) {
    if (null != check.getName()) {
      return check.getName();
    } else {
      return "ErrorIssueCodeNameCheck"; // should only happen if the ID is missing, which will fail a validation
    }
  }

  /* Gets the simple issue code name for an issue expression. */
  protected static String _issueName(XIssueExpression issue) {
    if (issue.getIssueCode() != null) {
      return issue.getIssueCode();
    } else if (issue.getCheck() != null && !issue.getCheck().eIsProxy()) {
      return issueName(issue.getCheck());
    } else if (parent(issue, Check.class) != null) {
      return issueName(parent(issue, Check.class));
    } else {
      return "ErrorIssueCodeName_XIssueExpresion"; // should not happen
    }
  }

  public static String issueCodePrefix(CheckCatalog catalog) {
    return catalog.getPackageName() + "." + issueCodesClassName(catalog) + ".";
  }

  /* Returns the <b>value</b> of an issue code. */
  public static String issueCodeValue(EObject object, String issueName) {
    CheckCatalog catalog = parent(object, CheckCatalog.class);
    return issueCodePrefix(catalog) + CheckUtil.toIssueCodeName(splitCamelCase(issueName));
  }

  /* Gets the issue label for a Check. */
  protected String _issueLabel(Check check) {
    return check.getLabel();
  }

  /* Gets the issue label for an issue expression. */
  protected String _issueLabel(XIssueExpression issue) {
    if (issue.getCheck() != null && !issue.getCheck().eIsProxy()) {
      return issueLabel(issue.getCheck());
    } else if (parent(issue, Check.class) != null) {
      return issueLabel(parent(issue, Check.class));
    } else {
      return "ERROR_ISSUE_LABEL_XISSUEEXPRESSION"; // should not happen
    }
  }

  /* Converts a string such as "AbcDef" to "ABC_DEF". */
  public static String splitCamelCase(String string) {
    return string.replaceAll(
      String.format(
        "%s|%s|%s",
        "(?<=[A-Z])(?=[A-Z][a-z])",
        "(?<=[^A-Z_])(?=[A-Z])",
        "(?<=[A-Za-z])(?=[^A-Za-z_])"
      ),
      "_"
    );
  }

  public CheckType checkType(Check check) {
    /* TODO handle the case of independent check implementations
     * An Implementation is not a Check and has no kind,
     * but it may execute checks of various types.
     * As it is we treat them all as FAST regardless of declared kind.
     */
    TriggerKind kind = check != null ? check.getKind() : null;
    if (kind == null) {
      kind = TriggerKind.FAST;
    }

    return switch (kind) {
      case EXPENSIVE -> CheckType.EXPENSIVE;
      case NORMAL -> CheckType.NORMAL;
      case FAST -> CheckType.FAST;
    };
  }

  /* Returns a default CheckType for a non-Check context. */
  public CheckType checkType(Context context) {
    EObject container = context.eContainer();
    Check check = (container instanceof Check) ? (Check) container : null;
    return checkType(check);
  }

  public String checkTypeQName(Context context) {
    return "CheckType." + checkType(context);
  }

  public Iterable<XIssueExpression> issues(EObject object) {
    return Iterables.filter(EcoreUtil2.eAllContents(object), XIssueExpression.class);
  }

  public Iterable<XIssueExpression> issues(CheckCatalog catalog) {
    return Iterables.concat(ListExtensions.map(catalog.getAllChecks(), check -> issues(check)));
  }

  public Iterable<XIssueExpression> issues(Implementation implementation) {
    return issues(implementation.getContext());
  }

  /* Returns all Check and Implementation Issues for a CheckCatalog. Issues are not necessarily unique. */
  public Iterable<XIssueExpression> checkAndImplementationIssues(CheckCatalog catalog) {
    Iterable<XIssueExpression> checkIssues = issues(catalog); // Issues for all Checks
    Iterable<XIssueExpression> implIssues = Iterables.concat(ListExtensions.map(catalog.getImplementations(), impl -> issues(impl))); // Issues for all Implementations
    return Iterables.concat(checkIssues, implIssues); // all Issue instances
  }

  public Check issuedCheck(XIssueExpression expression) {
    if (expression.getCheck() != null) {
      return expression.getCheck();
    } else {
      Check containerCheck = EcoreUtil2.getContainerOfType(expression, Check.class);
      if (containerCheck != null) {
        return containerCheck;
        //TODO we obviously need a validation in the language so that there is always a value here!
      }
      return null;
    }
  }

  /**
   * Gets the IFile which is associated with given object's eResource, or <code>null</code> if none
   * could be determined.
   */
  public IFile fileForObject(EObject object) {
    Resource res = object.eResource();
    if (res.getURI().isPlatform()) {
      return (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(res.getURI().toPlatformString(true));
    }
    return null;
  }

  /**
   * Gets the IProject which is associated with a given EObject or <code>null</code>
   * if none could be determined.
   */
  public IProject projectForObject(EObject object) {
    IFile file = object != null ? fileForObject(object) : null;
    return file != null ? file.getProject() : null;
  }

  /**
   * Gets the name of the project in which given object is contained.
   */
  public String bundleName(EObject object) {
    IProject proj = projectForObject(object);
    if (proj != null) {
      return proj.getName();
    }
    return null;
  }

  /*
   *  Replace binding placeholders of a message with "...".
   */
  public String replacePlaceholder(String message) {
    Pattern p = Pattern.compile("\\{[0-9]+\\}");
    Matcher m = p.matcher(message);
    return m.replaceAll("...");
  }

  /*
   *  Format the Check description for Eclipse Help
   */
  public String formatDescription(String comment) {
    if (comment == null) {
      return null;
    }
    try {
      JavaDoc2HTMLTextReader reader = new JavaDoc2HTMLTextReader(new StringReader(comment));
      return reader.getString();
    } catch (Exception e) {
      return null;
    }
  }

  public Set<String> getContents(CheckCatalog catalog, String path) {
    IProject project = projectForObject(catalog);
    if (project != null) { // In some compiler tests we may not have a project.
      IFile file = project.getFile(new Path(path));
      if (file.exists()) {
        try (InputStreamReader reader = new InputStreamReader(file.getContents())) {
          List<String> content = CharStreams.readLines(reader);
          return Sets.<String>newTreeSet(content);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
    return new LinkedHashSet<>();
  }

  public String qualifiedIssueCodeName(EObject context) {
    if (context instanceof Context) {
      return _qualifiedIssueCodeName((Context) context);
    } else if (context instanceof XIssueExpression) {
      return _qualifiedIssueCodeName((XIssueExpression) context);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(context).toString());
    }
  }

  public static String issueCode(EObject check) {
    if (check instanceof Check) {
      return _issueCode((Check) check);
    } else if (check instanceof XIssueExpression) {
      return _issueCode((XIssueExpression) check);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(check).toString());
    }
  }

  public static String issueName(EObject check) {
    if (check instanceof Check) {
      return _issueName((Check) check);
    } else if (check instanceof XIssueExpression) {
      return _issueName((XIssueExpression) check);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(check).toString());
    }
  }

  public String issueLabel(EObject check) {
    if (check instanceof Check) {
      return _issueLabel((Check) check);
    } else if (check instanceof XIssueExpression) {
      return _issueLabel((XIssueExpression) check);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(check).toString());
    }
  }
}
