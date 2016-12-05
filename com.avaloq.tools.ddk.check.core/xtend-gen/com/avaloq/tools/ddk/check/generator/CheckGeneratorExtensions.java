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
import com.avaloq.tools.ddk.check.check.ContextVariable;
import com.avaloq.tools.ddk.check.check.Implementation;
import com.avaloq.tools.ddk.check.check.TriggerKind;
import com.avaloq.tools.ddk.check.check.XIssueExpression;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.inject.Inject;
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
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class CheckGeneratorExtensions {
  @Inject
  @Extension
  private CheckGeneratorNaming _checkGeneratorNaming;
  
  protected String _qualifiedIssueCodeName(final XIssueExpression issue) {
    String _xblockexpression = null;
    {
      final String result = this.issueCode(issue);
      String _xifexpression = null;
      boolean _equals = Objects.equal(result, null);
      if (_equals) {
        _xifexpression = null;
      } else {
        CheckCatalog _parent = this._checkGeneratorNaming.<CheckCatalog>parent(issue, CheckCatalog.class);
        String _issueCodesClassName = this._checkGeneratorNaming.issueCodesClassName(_parent);
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
    CheckCatalog _parent = this._checkGeneratorNaming.<CheckCatalog>parent(context, CheckCatalog.class);
    String _issueCodesClassName = this._checkGeneratorNaming.issueCodesClassName(_parent);
    String _plus = (_issueCodesClassName + ".");
    String _issueCode = this.issueCode(context);
    return (_plus + _issueCode);
  }
  
  /**
   * Gets the simple issue code name for an implementation.
   */
  protected String _issueCode(final Implementation implementation) {
    String _name = implementation.getName();
    String _splitCamelCase = this.splitCamelCase(_name);
    String _upperCase = _splitCamelCase.toUpperCase();
    String _plus = (_upperCase + "_");
    Context _context = implementation.getContext();
    ContextVariable _contextVariable = _context.getContextVariable();
    JvmTypeReference _type = _contextVariable.getType();
    String _simpleName = _type.getSimpleName();
    String _upperCase_1 = _simpleName.toUpperCase();
    return (_plus + _upperCase_1);
  }
  
  /**
   * Gets the simple issue code name for a context.
   */
  protected String _issueCode(final Context context) {
    String _xblockexpression = null;
    {
      String result = null;
      EObject _eContainer = context.eContainer();
      if ((_eContainer instanceof Check)) {
        EObject _eContainer_1 = context.eContainer();
        final Check check = ((Check) _eContainer_1);
        String _name = check.getName();
        String _splitCamelCase = this.splitCamelCase(_name);
        String _upperCase = _splitCamelCase.toUpperCase();
        String _plus = (_upperCase + "_");
        ContextVariable _contextVariable = context.getContextVariable();
        JvmTypeReference _type = _contextVariable.getType();
        String _simpleName = _type.getSimpleName();
        String _upperCase_1 = _simpleName.toUpperCase();
        String _plus_1 = (_plus + _upperCase_1);
        String _plus_2 = (_plus_1 + "_");
        EList<Context> _contexts = check.getContexts();
        int _indexOf = _contexts.indexOf(context);
        String _plus_3 = (_plus_2 + Integer.valueOf(_indexOf));
        result = _plus_3;
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  /**
   * Gets the simple issue code name for an issue expression.
   */
  protected String _issueCode(final XIssueExpression issue) {
    String _xifexpression = null;
    String _issueCode = issue.getIssueCode();
    boolean _notEquals = (!Objects.equal(_issueCode, null));
    if (_notEquals) {
      String _issueCode_1 = issue.getIssueCode();
      String _splitCamelCase = this.splitCamelCase(_issueCode_1);
      _xifexpression = _splitCamelCase.toUpperCase();
    } else {
      String _xifexpression_1 = null;
      Check _parent = this._checkGeneratorNaming.<Check>parent(issue, Check.class);
      boolean _notEquals_1 = (!Objects.equal(_parent, null));
      if (_notEquals_1) {
        Context _parent_1 = this._checkGeneratorNaming.<Context>parent(issue, Context.class);
        _xifexpression_1 = this.issueCode(_parent_1);
      } else {
        String _xifexpression_2 = null;
        Implementation _parent_2 = this._checkGeneratorNaming.<Implementation>parent(issue, Implementation.class);
        boolean _notEquals_2 = (!Objects.equal(_parent_2, null));
        if (_notEquals_2) {
          Implementation _parent_3 = this._checkGeneratorNaming.<Implementation>parent(issue, Implementation.class);
          _xifexpression_2 = this.issueCode(_parent_3);
        } else {
          _xifexpression_2 = "ERROR_ISSUE_CODE_NAME_XISSUEEXPRESSION";
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public String issueCodePrefix(final CheckCatalog catalog) {
    String _packageName = catalog.getPackageName();
    String _plus = (_packageName + ".");
    String _issueCodesClassName = this._checkGeneratorNaming.issueCodesClassName(catalog);
    String _plus_1 = (_plus + _issueCodesClassName);
    return (_plus_1 + ".");
  }
  
  /**
   * Returns the <b>value</b> of an issue code.
   */
  public String issueCodeValue(final EObject object, final String issueCode) {
    String _xblockexpression = null;
    {
      final CheckCatalog catalog = this._checkGeneratorNaming.<CheckCatalog>parent(object, CheckCatalog.class);
      String _issueCodePrefix = this.issueCodePrefix(catalog);
      String _replaceAll = issueCode.replaceAll("_", ".");
      String _lowerCase = _replaceAll.toLowerCase();
      _xblockexpression = (_issueCodePrefix + _lowerCase);
    }
    return _xblockexpression;
  }
  
  /**
   * Converts a string such as "AbcDef" to "ABC_DEF".
   */
  public String splitCamelCase(final String string) {
    String _format = String.format("%s|%s|%s", 
      "(?<=[A-Z])(?=[A-Z][a-z])", 
      "(?<=[^A-Z])(?=[A-Z])", 
      "(?<=[A-Za-z])(?=[^A-Za-z])");
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
              return Sets.<String>newLinkedHashSet(content);
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
  
  public String issueCode(final EObject context) {
    if (context instanceof Context) {
      return _issueCode((Context)context);
    } else if (context instanceof Implementation) {
      return _issueCode((Implementation)context);
    } else if (context instanceof XIssueExpression) {
      return _issueCode((XIssueExpression)context);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(context).toString());
    }
  }
}
