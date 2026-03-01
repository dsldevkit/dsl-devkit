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
import java.nio.charset.StandardCharsets;
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

import static com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.issueCodesClassName;
import static com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.parent;

@SuppressWarnings({"checkstyle:MethodName"})
public class CheckGeneratorExtensions {

  /**
   * Returns the qualified Java name for an issue code.
   *
   * @param issue
   *          the issue expression
   * @return the qualified issue code name, or {@code null} if the issue code is null
   */
  protected String _qualifiedIssueCodeName(final XIssueExpression issue) {
    String result = issueCode(issue);
    if (result == null) {
      return null;
    } else {
      return issueCodesClassName(parent(issue, CheckCatalog.class)) + "." + result;
    }
  }

  /**
   * Returns the qualified Java name for an issue code.
   *
   * @param context
   *          the context
   * @return the qualified issue code name
   */
  protected String _qualifiedIssueCodeName(final Context context) {
    return issueCodesClassName(parent(context, CheckCatalog.class)) + "." + issueCode(context);
  }

  /**
   * Gets the simple issue code name for a check.
   *
   * @param check
   *          the check
   * @return the issue code string
   */
  protected static String _issueCode(final Check check) {
    if (null != check.getName()) {
      return splitCamelCase(check.getName()).toUpperCase();
    } else {
      return "ERROR_ISSUE_CODE_NAME_CHECK"; // should only happen if the ID is missing, which will fail a validation
    }
  }

  /**
   * Gets the simple issue code name for an issue expression.
   *
   * @param issue
   *          the issue expression
   * @return the issue code string
   */
  protected static String _issueCode(final XIssueExpression issue) {
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

  /**
   * Gets the simple issue code name for a check.
   *
   * @param check
   *          the check
   * @return the issue name string
   */
  protected static String _issueName(final Check check) {
    if (null != check.getName()) {
      return check.getName();
    } else {
      return "ErrorIssueCodeNameCheck"; // should only happen if the ID is missing, which will fail a validation
    }
  }

  /**
   * Gets the simple issue code name for an issue expression.
   *
   * @param issue
   *          the issue expression
   * @return the issue name string
   */
  protected static String _issueName(final XIssueExpression issue) {
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

  /**
   * Returns the issue code prefix for a catalog.
   *
   * @param catalog
   *          the check catalog
   * @return the issue code prefix
   */
  public static String issueCodePrefix(final CheckCatalog catalog) {
    return catalog.getPackageName() + "." + issueCodesClassName(catalog) + ".";
  }

  /**
   * Returns the value of an issue code.
   *
   * @param object
   *          the EObject context
   * @param issueName
   *          the issue name
   * @return the issue code value
   */
  public static String issueCodeValue(final EObject object, final String issueName) {
    CheckCatalog catalog = parent(object, CheckCatalog.class);
    return issueCodePrefix(catalog) + CheckUtil.toIssueCodeName(splitCamelCase(issueName));
  }

  /**
   * Gets the issue label for a Check.
   *
   * @param check
   *          the check
   * @return the label
   */
  protected String _issueLabel(final Check check) {
    return check.getLabel();
  }

  /**
   * Gets the issue label for an issue expression.
   *
   * @param issue
   *          the issue expression
   * @return the label
   */
  protected String _issueLabel(final XIssueExpression issue) {
    if (issue.getCheck() != null && !issue.getCheck().eIsProxy()) {
      return issueLabel(issue.getCheck());
    } else if (parent(issue, Check.class) != null) {
      return issueLabel(parent(issue, Check.class));
    } else {
      return "ERROR_ISSUE_LABEL_XISSUEEXPRESSION"; // should not happen
    }
  }

  /* Converts a string such as "AbcDef" to "ABC_DEF". */
  public static String splitCamelCase(final String string) {
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

  /**
   * Returns the CheckType for a check.
   *
   * @param check
   *          the check
   * @return the check type
   */
  public CheckType checkType(final Check check) {
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

  /**
   * Returns a default CheckType for a non-Check context.
   *
   * @param context
   *          the context
   * @return the check type
   */
  public CheckType checkType(final Context context) {
    EObject container = context.eContainer();
    Check check = (container instanceof Check) ? (Check) container : null;
    return checkType(check);
  }

  /**
   * Returns the qualified CheckType name for a context.
   *
   * @param context
   *          the context
   * @return the qualified check type name
   */
  public String checkTypeQName(final Context context) {
    return "CheckType." + checkType(context);
  }

  /**
   * Returns all issue expressions contained in an EObject.
   *
   * @param object
   *          the object to search
   * @return the issue expressions
   */
  public Iterable<XIssueExpression> issues(final EObject object) {
    return Iterables.filter(EcoreUtil2.eAllContents(object), XIssueExpression.class);
  }

  /**
   * Returns all issue expressions for all checks in a catalog.
   *
   * @param catalog
   *          the check catalog
   * @return the issue expressions
   */
  public Iterable<XIssueExpression> issues(final CheckCatalog catalog) {
    return Iterables.concat(ListExtensions.map(catalog.getAllChecks(), check -> issues(check)));
  }

  /**
   * Returns all issue expressions for an implementation.
   *
   * @param implementation
   *          the implementation
   * @return the issue expressions
   */
  public Iterable<XIssueExpression> issues(final Implementation implementation) {
    return issues(implementation.getContext());
  }

  /**
   * Returns all Check and Implementation Issues for a CheckCatalog. Issues are not necessarily unique.
   *
   * @param catalog
   *          the check catalog
   * @return all issue expressions
   */
  public Iterable<XIssueExpression> checkAndImplementationIssues(final CheckCatalog catalog) {
    Iterable<XIssueExpression> checkIssues = issues(catalog); // Issues for all Checks
    Iterable<XIssueExpression> implIssues = Iterables.concat(ListExtensions.map(catalog.getImplementations(), impl -> issues(impl))); // Issues for all Implementations
    return Iterables.concat(checkIssues, implIssues); // all Issue instances
  }

  /**
   * Returns the check associated with an issue expression.
   *
   * @param expression
   *          the issue expression
   * @return the associated check, or {@code null}
   */
  public Check issuedCheck(final XIssueExpression expression) {
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
   *
   * @param object
   *          the EObject
   * @return the associated file, or {@code null}
   */
  public IFile fileForObject(final EObject object) {
    Resource res = object.eResource();
    if (res.getURI().isPlatform()) {
      return (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(res.getURI().toPlatformString(true));
    }
    return null;
  }

  /**
   * Gets the IProject which is associated with a given EObject or <code>null</code>
   * if none could be determined.
   *
   * @param object
   *          the EObject
   * @return the associated project, or {@code null}
   */
  public IProject projectForObject(final EObject object) {
    IFile file = object != null ? fileForObject(object) : null;
    return file != null ? file.getProject() : null;
  }

  /**
   * Gets the name of the project in which given object is contained.
   *
   * @param object
   *          the EObject
   * @return the bundle name, or {@code null}
   */
  public String bundleName(final EObject object) {
    IProject proj = projectForObject(object);
    if (proj != null) {
      return proj.getName();
    }
    return null;
  }

  /**
   * Replace binding placeholders of a message with "...".
   *
   * @param message
   *          the message
   * @return the message with placeholders replaced
   */
  public String replacePlaceholder(final String message) {
    Pattern p = Pattern.compile("\\{[0-9]+\\}");
    Matcher m = p.matcher(message);
    return m.replaceAll("...");
  }

  /**
   * Format the Check description for Eclipse Help.
   *
   * @param comment
   *          the comment to format
   * @return the formatted HTML, or {@code null}
   */
  // CHECKSTYLE:CHECK-OFF IllegalCatch
  public String formatDescription(final String comment) {
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
  // CHECKSTYLE:CHECK-ON IllegalCatch

  /**
   * Gets the contents of a file in the project.
   *
   * @param catalog
   *          the check catalog
   * @param path
   *          the file path
   * @return the set of lines
   * @throws IllegalStateException
   *          if the file cannot be read
   */
  public Set<String> getContents(final CheckCatalog catalog, final String path) {
    IProject project = projectForObject(catalog);
    if (project != null) { // In some compiler tests we may not have a project.
      IFile file = project.getFile(new Path(path));
      if (file.exists()) {
        // CHECKSTYLE:CHECK-OFF IllegalCatch
        try (InputStreamReader reader = new InputStreamReader(file.getContents(), StandardCharsets.UTF_8)) {
          List<String> content = CharStreams.readLines(reader);
          return Sets.<String>newTreeSet(content);
        } catch (Exception e) {
          throw new IllegalStateException(e);
        }
        // CHECKSTYLE:CHECK-ON IllegalCatch
      }
    }
    return new LinkedHashSet<>();
  }

  /**
   * Returns the qualified issue code name for an EObject.
   *
   * @param context
   *          the EObject context
   * @return the qualified issue code name
   * @throws IllegalArgumentException
   *          if the parameter type is not handled
   */
  public String qualifiedIssueCodeName(final EObject context) {
    if (context instanceof Context) {
      return _qualifiedIssueCodeName((Context) context);
    } else if (context instanceof XIssueExpression) {
      return _qualifiedIssueCodeName((XIssueExpression) context);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(context).toString());
    }
  }

  /**
   * Returns the issue code for an EObject.
   *
   * @param check
   *          the EObject (Check or XIssueExpression)
   * @return the issue code string
   * @throws IllegalArgumentException
   *          if the parameter type is not handled
   */
  public static String issueCode(final EObject check) {
    if (check instanceof Check) {
      return _issueCode((Check) check);
    } else if (check instanceof XIssueExpression) {
      return _issueCode((XIssueExpression) check);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(check).toString());
    }
  }

  /**
   * Returns the issue name for an EObject.
   *
   * @param check
   *          the EObject (Check or XIssueExpression)
   * @return the issue name string
   * @throws IllegalArgumentException
   *          if the parameter type is not handled
   */
  public static String issueName(final EObject check) {
    if (check instanceof Check) {
      return _issueName((Check) check);
    } else if (check instanceof XIssueExpression) {
      return _issueName((XIssueExpression) check);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.<Object>asList(check).toString());
    }
  }

  /**
   * Returns the issue label for an EObject.
   *
   * @param check
   *          the EObject (Check or XIssueExpression)
   * @return the label string
   * @throws IllegalArgumentException
   *          if the parameter type is not handled
   */
  public String issueLabel(final EObject check) {
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
