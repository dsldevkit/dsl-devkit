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

import static com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.issueCodesClassName;
import static com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.parent;


@SuppressWarnings({"checkstyle:MethodName", "nls"})
public class CheckGeneratorExtensions {

  protected String _qualifiedIssueCodeName(final XIssueExpression issue) {
    final String result = issueCode(issue);
    if (result == null) {
      return null;
    } else {
      return issueCodesClassName(parent(issue, CheckCatalog.class)) + "." + result;
    }
  }

  /* Returns the qualified Java name for an issue code. */
  protected String _qualifiedIssueCodeName(final Context context) {
    return issueCodesClassName(parent(context, CheckCatalog.class)) + "." + issueCode(context);
  }

  /* Gets the simple issue code name for a check. */
  protected static String _issueCode(final Check check) {
    if (null != check.getName()) {
      return splitCamelCase(check.getName()).toUpperCase();
    } else {
      return "ERROR_ISSUE_CODE_NAME_CHECK"; // should only happen if the ID is missing, which will fail a validation
    }
  }

  /* Gets the simple issue code name for an issue expression. */
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

  /* Gets the simple issue code name for a check. */
  protected static String _issueName(final Check check) {
    if (null != check.getName()) {
      return check.getName();
    } else {
      return "ErrorIssueCodeNameCheck"; // should only happen if the ID is missing, which will fail a validation
    }
  }

  /* Gets the simple issue code name for an issue expression. */
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

  public static String issueCodePrefix(final CheckCatalog catalog) {
    return catalog.getPackageName() + "." + issueCodesClassName(catalog) + ".";
  }

  /* Returns the <b>value</b> of an issue code. */
  public static String issueCodeValue(final EObject object, final String issueName) {
    final CheckCatalog catalog = parent(object, CheckCatalog.class);
    return issueCodePrefix(catalog) + CheckUtil.toIssueCodeName(splitCamelCase(issueName));
  }

  /* Gets the issue label for a Check. */
  protected String _issueLabel(final Check check) {
    return check.getLabel();
  }

  /* Gets the issue label for an issue expression. */
  protected String _issueLabel(final XIssueExpression issue) {
    if (issue.getCheck() != null && !issue.getCheck().eIsProxy()) {
      return issueLabel(issue.getCheck());
    } else if (parent(issue, Check.class) != null) {
      return issueLabel(parent(issue, Check.class));
    } else {
      return "ERROR_ISSUE_LABEL_XISSUEEXPRESSION"; // should not happen
    }
  }

  private static final String UPPER_BEFORE_UPPER_LOWER_BOUNDARY = "(?<=[A-Z])(?=[A-Z][a-z])";
  private static final String NON_UPPER_BEFORE_UPPER_BOUNDARY = "(?<=[^A-Z_])(?=[A-Z])";
  private static final String LETTER_BEFORE_NON_LETTER_BOUNDARY = "(?<=[A-Za-z])(?=[^A-Za-z_])";

  /* Converts a string such as "AbcDef" to "ABC_DEF". */
  public static String splitCamelCase(final String string) {
    return string.replaceAll(
      UPPER_BEFORE_UPPER_LOWER_BOUNDARY + "|" + NON_UPPER_BEFORE_UPPER_BOUNDARY + "|" + LETTER_BEFORE_NON_LETTER_BOUNDARY,
      "_");
  }

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

  /* Returns a default CheckType for a non-Check context. */
  public CheckType checkType(final Context context) {
    final EObject container = context.eContainer();
    final Check check = container instanceof Check ? (Check) container : null;
    return checkType(check);
  }

  public String checkTypeQName(final Context context) {
    return "CheckType." + checkType(context);
  }

  public Iterable<XIssueExpression> issues(final EObject object) {
    return Iterables.filter(EcoreUtil2.eAllContents(object), XIssueExpression.class);
  }

  public Iterable<XIssueExpression> issues(final CheckCatalog catalog) {
    return Iterables.concat(ListExtensions.map(catalog.getAllChecks(), check -> issues(check)));
  }

  public Iterable<XIssueExpression> issues(final Implementation implementation) {
    return issues(implementation.getContext());
  }

  /* Returns all Check and Implementation Issues for a CheckCatalog. Issues are not necessarily unique. */
  public Iterable<XIssueExpression> checkAndImplementationIssues(final CheckCatalog catalog) {
    final Iterable<XIssueExpression> checkIssues = issues(catalog); // Issues for all Checks
    final Iterable<XIssueExpression> implIssues = Iterables.concat(ListExtensions.map(catalog.getImplementations(), impl -> issues(impl))); // Issues for all Implementations
    return Iterables.concat(checkIssues, implIssues); // all Issue instances
  }

  public Check issuedCheck(final XIssueExpression expression) {
    if (expression.getCheck() != null) {
      return expression.getCheck();
    } else {
      final Check containerCheck = EcoreUtil2.getContainerOfType(expression, Check.class);
      if (containerCheck != null) {
        return containerCheck;
        //TODO we obviously need a validation in the language so that there is always a value here!
      }
      return null;
    }
  }

  public IFile fileForObject(final EObject object) {
    final Resource res = object.eResource();
    if (res.getURI().isPlatform()) {
      return (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(res.getURI().toPlatformString(true));
    }
    return null;
  }

  public IProject projectForObject(final EObject object) {
    final IFile file = object != null ? fileForObject(object) : null;
    return file != null ? file.getProject() : null;
  }

  public String bundleName(final EObject object) {
    final IProject proj = projectForObject(object);
    if (proj != null) {
      return proj.getName();
    }
    return null;
  }

  public String replacePlaceholder(final String message) {
    final Pattern p = Pattern.compile("\\{[0-9]+\\}");
    final Matcher m = p.matcher(message);
    return m.replaceAll("...");
  }

  /*
   *  Format the Check description for Eclipse Help
   */
  // CHECKSTYLE:CHECK-OFF IllegalCatch
  public String formatDescription(final String comment) {
    if (comment == null) {
      return null;
    }
    try {
      final JavaDoc2HTMLTextReader reader = new JavaDoc2HTMLTextReader(new StringReader(comment));
      return reader.getString();
    } catch (final Exception e) {
      return null;
    }
  }
  // CHECKSTYLE:CHECK-ON IllegalCatch

  // CHECKSTYLE:CHECK-OFF IllegalCatch
  public Set<String> getContents(final CheckCatalog catalog, final String path) {
    final IProject project = projectForObject(catalog);
    if (project != null) { // In some compiler tests we may not have a project.
      final IFile file = project.getFile(new Path(path));
      if (file.exists()) {
        try (InputStreamReader reader = new InputStreamReader(file.getContents(), StandardCharsets.UTF_8)) {
          final List<String> content = CharStreams.readLines(reader);
          return Sets.newTreeSet(content);
        } catch (final RuntimeException e) {
          throw e;
        } catch (final Exception e) {
          throw new IllegalStateException(e);
        }
      }
    }
    return new LinkedHashSet<>();
  }
  // CHECKSTYLE:CHECK-ON IllegalCatch

  public String qualifiedIssueCodeName(final EObject context) {
    if (context instanceof Context context1) {
      return _qualifiedIssueCodeName(context1);
    } else if (context instanceof XIssueExpression xIssueExpression) {
      return _qualifiedIssueCodeName(xIssueExpression);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.asList(context).toString());
    }
  }

  public static String issueCode(final EObject check) {
    if (check instanceof Check check1) {
      return _issueCode(check1);
    } else if (check instanceof XIssueExpression xIssueExpression) {
      return _issueCode(xIssueExpression);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.asList(check).toString());
    }
  }

  public static String issueName(final EObject check) {
    if (check instanceof Check check1) {
      return _issueName(check1);
    } else if (check instanceof XIssueExpression xIssueExpression) {
      return _issueName(xIssueExpression);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.asList(check).toString());
    }
  }

  public String issueLabel(final EObject check) {
    if (check instanceof Check check1) {
      return _issueLabel(check1);
    } else if (check instanceof XIssueExpression xIssueExpression) {
      return _issueLabel(xIssueExpression);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: "
        + Arrays.asList(check).toString());
    }
  }
}
