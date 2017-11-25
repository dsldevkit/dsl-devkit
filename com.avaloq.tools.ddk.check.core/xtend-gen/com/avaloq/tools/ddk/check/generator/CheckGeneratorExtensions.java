/**
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 */
package com.avaloq.tools.ddk.check.generator;

import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.Context;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.TriggerKind;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.internal.ui.text.javadoc.JavaDoc2HTMLTextReader;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class CheckGeneratorExtensions {
  /**
   * Returns whether a CheckCatalog has any included CheckCatalogs.
   */
  public boolean hasIncludedCatalogs(final CheckCatalog catalog) {
    final CheckCatalog included = catalog.getIncludedCatalogs();
    return ((!Objects.equal(null, included)) && (!included.eIsProxy()));
  }
  
  protected String _qualifiedIssueCodeName(final XIssueExpression issue) {
    String _xblockexpression = null;
    {
      final String result = CheckGeneratorExtensions.issueCode(issue);
      String _xifexpression = null;
      boolean _equals = Objects.equal(result, null);
      if (_equals) {
        _xifexpression = null;
      } else {
        CheckCatalog _parent = CheckGeneratorNaming.<CheckCatalog>parent(issue, CheckCatalog.class);
        String _issueCodesClassName = CheckGeneratorNaming.issueCodesClassName(_parent);
        String _plus = (_issueCodesClassName + ".");
        _xifexpression = (_plus + result);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  /**
   * Returns the qualified Java name for an issue code.
   */
  protected String _qualifiedIssueCodeName(final Context context) {
    CheckCatalog _parent = CheckGeneratorNaming.<CheckCatalog>parent(context, CheckCatalog.class);
    String _issueCodesClassName = CheckGeneratorNaming.issueCodesClassName(_parent);
    String _plus = (_issueCodesClassName + ".");
    String _issueCode = CheckGeneratorExtensions.issueCode(context);
    return (_plus + _issueCode);
  }
  
  /**
   * Gets the simple issue code name for a check.
   */
  protected static String _issueCode(final Check check) {
    String _xifexpression = null;
    String _name = check.getName();
    boolean _notEquals = (!Objects.equal(null, _name));
    if (_notEquals) {
      String _name_1 = check.getName();
      String _splitCamelCase = CheckGeneratorExtensions.splitCamelCase(_name_1);
      _xifexpression = _splitCamelCase.toUpperCase();
    } else {
      _xifexpression = "ERROR_ISSUE_CODE_NAME_CHECK";
    }
    return _xifexpression;
  }
  
  /**
   * Gets the simple issue code name for an issue expression.
   */
  protected static String _issueCode(final XIssueExpression issue) {
    String _xifexpression = null;
    String _issueCode = issue.getIssueCode();
    boolean _notEquals = (!Objects.equal(_issueCode, null));
    if (_notEquals) {
      String _issueCode_1 = issue.getIssueCode();
      String _splitCamelCase = CheckGeneratorExtensions.splitCamelCase(_issueCode_1);
      _xifexpression = _splitCamelCase.toUpperCase();
    } else {
      String _xifexpression_1 = null;
      if (((issue.getCheck() != null) && (!issue.getCheck().eIsProxy()))) {
        Check _check = issue.getCheck();
        _xifexpression_1 = CheckGeneratorExtensions.issueCode(_check);
      } else {
        String _xifexpression_2 = null;
        Check _parent = CheckGeneratorNaming.<Check>parent(issue, Check.class);
        boolean _notEquals_1 = (!Objects.equal(_parent, null));
        if (_notEquals_1) {
          Check _parent_1 = CheckGeneratorNaming.<Check>parent(issue, Check.class);
          _xifexpression_2 = CheckGeneratorExtensions.issueCode(_parent_1);
        } else {
          _xifexpression_2 = "ERROR_ISSUE_CODE_NAME_XISSUEEXPRESSION";
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public static String issueCodePrefix(final CheckCatalog catalog) {
    String _packageName = catalog.getPackageName();
    String _plus = (_packageName + ".");
    String _issueCodesClassName = CheckGeneratorNaming.issueCodesClassName(catalog);
    String _plus_1 = (_plus + _issueCodesClassName);
    return (_plus_1 + ".");
  }
  
  /**
   * Returns the <b>value</b> of an issue code.
   */
  public static String issueCodeValue(final EObject object, final String issueCode) {
    String _xblockexpression = null;
    {
      final CheckCatalog catalog = CheckGeneratorNaming.<CheckCatalog>parent(object, CheckCatalog.class);
      String _issueCodePrefix = CheckGeneratorExtensions.issueCodePrefix(catalog);
      String _replaceAll = issueCode.replaceAll("_", ".");
      String _lowerCase = _replaceAll.toLowerCase();
      _xblockexpression = (_issueCodePrefix + _lowerCase);
    }
    return _xblockexpression;
  }
  
  /**
   * Gets the issue label for a Check.
   */
  protected String _issueLabel(final Check check) {
    return check.getLabel();
  }
  
  /**
   * Gets the issue label for an issue expression.
   */
  protected String _issueLabel(final XIssueExpression issue) {
    String _xifexpression = null;
    if (((issue.getCheck() != null) && (!issue.getCheck().eIsProxy()))) {
      Check _check = issue.getCheck();
      _xifexpression = this.issueLabel(_check);
    } else {
      String _xifexpression_1 = null;
      Check _parent = CheckGeneratorNaming.<Check>parent(issue, Check.class);
      boolean _notEquals = (!Objects.equal(_parent, null));
      if (_notEquals) {
        Check _parent_1 = CheckGeneratorNaming.<Check>parent(issue, Check.class);
        _xifexpression_1 = this.issueLabel(_parent_1);
      } else {
        _xifexpression_1 = "ERROR_ISSUE_LABEL_XISSUEEXPRESSION";
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  /**
   * Converts a string such as "AbcDef" to "ABC_DEF".
   */
  public static String splitCamelCase(final String string) {
    String _format = String.format(
      "%s|%s|%s", 
      "(?<=[A-Z])(?=[A-Z][a-z])", 
      "(?<=[^A-Z_])(?=[A-Z])", 
      "(?<=[A-Za-z])(?=[^A-Za-z_])");
    return string.replaceAll(_format, 
      "_");
  }
  
  public String checkType(final Context context) {
    TriggerKind _xifexpression = null;
    EObject _eContainer = context.eContainer();
    if ((_eContainer instanceof Check)) {
      EObject _eContainer_1 = context.eContainer();
      _xifexpression = ((Check) _eContainer_1).getKind();
    } else {
      _xifexpression = TriggerKind.FAST;
    }
    final TriggerKind kind = _xifexpression;
    CheckType _switchResult = null;
    if (kind != null) {
      switch (kind) {
        case EXPENSIVE:
          _switchResult = CheckType.EXPENSIVE;
          break;
        case NORMAL:
          _switchResult = CheckType.NORMAL;
          break;
        case FAST:
          _switchResult = CheckType.FAST;
          break;
        default:
          break;
      }
    }
    String _string = _switchResult.toString();
    return ("CheckType." + _string);
  }
  
  public Iterable<XIssueExpression> issues(final EObject object) {
    Iterable<EObject> _eAllContents = EcoreUtil2.eAllContents(object);
    return Iterables.<XIssueExpression>filter(_eAllContents, XIssueExpression.class);
  }
  
  public Iterable<XIssueExpression> issues(final CheckCatalog catalog) {
    EList<Check> _allChecks = catalog.getAllChecks();
    final Function1<Check, Iterable<XIssueExpression>> _function = (Check check) -> {
      return this.issues(check);
    };
    List<Iterable<XIssueExpression>> _map = ListExtensions.<Check, Iterable<XIssueExpression>>map(_allChecks, _function);
    return Iterables.<XIssueExpression>concat(_map);
  }
  
  public Iterable<XIssueExpression> issues(final Implementation implementation) {
    Context _context = implementation.getContext();
    return this.issues(_context);
  }
  
  /**
   * Returns all Check and Implementation Issues for a CheckCatalog. Issues are not necessarily unique.
   */
  public Iterable<XIssueExpression> checkAndImplementationIssues(final CheckCatalog catalog) {
    final Iterable<XIssueExpression> checkIssues = this.issues(catalog);
    EList<Implementation> _implementations = catalog.getImplementations();
    final Function1<Implementation, Iterable<XIssueExpression>> _function = (Implementation impl) -> {
      return this.issues(impl);
    };
    List<Iterable<XIssueExpression>> _map = ListExtensions.<Implementation, Iterable<XIssueExpression>>map(_implementations, _function);
    final Iterable<XIssueExpression> implIssues = Iterables.<XIssueExpression>concat(_map);
    return Iterables.<XIssueExpression>concat(checkIssues, implIssues);
  }
  
  public Check issuedCheck(final XIssueExpression expression) {
    Check _xifexpression = null;
    Check _check = expression.getCheck();
    boolean _notEquals = (!Objects.equal(_check, null));
    if (_notEquals) {
      _xifexpression = expression.getCheck();
    } else {
      Check _xblockexpression = null;
      {
        final Check containerCheck = EcoreUtil2.<Check>getContainerOfType(expression, Check.class);
        Check _xifexpression_1 = null;
        boolean _notEquals_1 = (!Objects.equal(containerCheck, null));
        if (_notEquals_1) {
          _xifexpression_1 = containerCheck;
        }
        _xblockexpression = _xifexpression_1;
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  /**
   * Gets the IFile which is associated with given object's eResource, or <code>null</code> if none
   * could be determined.
   */
  public IFile fileForObject(final EObject object) {
    final Resource res = object.eResource();
    URI _uRI = res.getURI();
    boolean _isPlatform = _uRI.isPlatform();
    if (_isPlatform) {
      IWorkspace _workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRoot _root = _workspace.getRoot();
      URI _uRI_1 = res.getURI();
      String _platformString = _uRI_1.toPlatformString(true);
      IResource _findMember = _root.findMember(_platformString);
      return ((IFile) _findMember);
    }
    return null;
  }
  
  /**
   * Gets the IProject which is associated with a given EObject or <code>null</code>
   * if none could be determined.
   */
  public IProject projectForObject(final EObject object) {
    IFile _fileForObject = null;
    if (object!=null) {
      _fileForObject=this.fileForObject(object);
    }
    IProject _project = null;
    if (_fileForObject!=null) {
      _project=_fileForObject.getProject();
    }
    return _project;
  }
  
  /**
   * Gets the name of the project in which given object is contained.
   */
  public String bundleName(final EObject object) {
    final IProject proj = this.projectForObject(object);
    boolean _notEquals = (!Objects.equal(proj, null));
    if (_notEquals) {
      return proj.getName();
    }
    return null;
  }
  
  /**
   * Replace binding placeholders of a message with "...".
   */
  public String replacePlaceholder(final String message) {
    String _xblockexpression = null;
    {
      final Pattern p = Pattern.compile("\\{[0-9]+\\}");
      final Matcher m = p.matcher(message);
      _xblockexpression = m.replaceAll("...");
    }
    return _xblockexpression;
  }
  
  /**
   * Format the Check description for Eclipse Help
   */
  public String formatDescription(final String comment) {
    boolean _equals = Objects.equal(comment, null);
    if (_equals) {
      return null;
    }
    try {
      StringReader _stringReader = new StringReader(comment);
      final JavaDoc2HTMLTextReader reader = new JavaDoc2HTMLTextReader(_stringReader);
      return reader.getString();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        return null;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public Set<String> getContents(final CheckCatalog catalog, final String path) {
    try {
      LinkedHashSet<String> _xblockexpression = null;
      {
        final IProject project = this.projectForObject(catalog);
        boolean _notEquals = (!Objects.equal(project, null));
        if (_notEquals) {
          Path _path = new Path(path);
          final IFile file = project.getFile(_path);
          boolean _exists = file.exists();
          if (_exists) {
            InputStream _contents = file.getContents();
            final InputStreamReader reader = new InputStreamReader(_contents);
            try {
              final List<String> content = CharStreams.readLines(reader);
              return Sets.<String>newTreeSet(content);
            } finally {
              reader.close();
            }
          }
        }
        _xblockexpression = CollectionLiterals.<String>newLinkedHashSet();
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public String qualifiedIssueCodeName(final EObject context) {
    if (context instanceof Context) {
      return _qualifiedIssueCodeName((Context)context);
    } else if (context instanceof XIssueExpression) {
      return _qualifiedIssueCodeName((XIssueExpression)context);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(context).toString());
    }
  }
  
  public static String issueCode(final EObject check) {
    if (check instanceof Check) {
      return _issueCode((Check)check);
    } else if (check instanceof XIssueExpression) {
      return _issueCode((XIssueExpression)check);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(check).toString());
    }
  }
  
  public String issueLabel(final EObject check) {
    if (check instanceof Check) {
      return _issueLabel((Check)check);
    } else if (check instanceof XIssueExpression) {
      return _issueLabel((XIssueExpression)check);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(check).toString());
    }
  }
}
