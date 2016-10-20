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
package com.avaloq.tools.ddk.xtext.format.generator;

import com.avaloq.tools.ddk.xtext.format.FormatConstants;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.generator.FormatGeneratorUtil;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.common.types.JvmAnnotationReference;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.xbase.compiler.DocumentationAdapter;
import org.eclipse.xtext.xbase.compiler.ErrorSafeExtensions;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator;
import org.eclipse.xtext.xbase.compiler.TreeAppendableUtil;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Generates code from your model files on save.
 * 
 * Currently this generator is not used at all.
 * This is a side effect of the usage of the org.eclipse.xtext.generator.generator.GeneratorFragment.GeneratorFragment,
 * which generates this file besides necessary contents for org.eclipse.xtext.builder.BuilderParticipant.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
@SuppressWarnings("all")
public class FormatGenerator extends JvmModelGenerator {
  @Inject
  @Extension
  private Naming naming;
  
  @Inject
  @Extension
  private TreeAppendableUtil _treeAppendableUtil;
  
  @Inject
  @Extension
  private ErrorSafeExtensions _errorSafeExtensions;
  
  public String getSingleCommentDocumentation(final EObject it, final ITreeAppendable appendable, final GeneratorConfig config) {
    EList<Adapter> _eAdapters = it.eAdapters();
    Iterable<DocumentationAdapter> _filter = Iterables.<DocumentationAdapter>filter(_eAdapters, DocumentationAdapter.class);
    final DocumentationAdapter adapter = IterableExtensions.<DocumentationAdapter>head(_filter);
    String _documentation = null;
    if (adapter!=null) {
      _documentation=adapter.getDocumentation();
    }
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_documentation);
    boolean _not = (!_isNullOrEmpty);
    if (_not) {
      return adapter.getDocumentation();
    }
    return null;
  }
  
  @Override
  protected ITreeAppendable _generateMember(final JvmField it, final ITreeAppendable appendable, final GeneratorConfig config) {
    ITreeAppendable _xblockexpression = null;
    {
      appendable.newLine();
      final ITreeAppendable tracedAppendable = appendable.trace(it);
      EList<JvmAnnotationReference> _annotations = it.getAnnotations();
      this.generateAnnotations(_annotations, tracedAppendable, true, config);
      this.generateModifier(it, tracedAppendable, config);
      JvmTypeReference _type = it.getType();
      this._errorSafeExtensions.serializeSafely(_type, "Object", tracedAppendable);
      tracedAppendable.append(" ");
      ITreeAppendable _traceSignificant = this._treeAppendableUtil.traceSignificant(tracedAppendable, it);
      String _simpleName = it.getSimpleName();
      _traceSignificant.append(_simpleName);
      this.generateInitialization(it, tracedAppendable, config);
      tracedAppendable.append(";");
      String documentation = this.getSingleCommentDocumentation(it, appendable, config);
      boolean _endsWith = documentation.endsWith("\r\n");
      if (_endsWith) {
        int _length = documentation.length();
        int _minus = (_length - 2);
        String _substring = documentation.substring(0, _minus);
        documentation = _substring;
      }
      ITreeAppendable _xifexpression = null;
      boolean _notEquals = (!Objects.equal(documentation, null));
      if (_notEquals) {
        ITreeAppendable _xblockexpression_1 = null;
        {
          tracedAppendable.append(" // ");
          _xblockexpression_1 = tracedAppendable.append(documentation);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    super.doGenerate(resource, fsa);
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<FormatConfiguration> _filter = Iterables.<FormatConfiguration>filter(_iterable, FormatConfiguration.class);
    for (final FormatConfiguration model : _filter) {
      Grammar _targetGrammar = model.getTargetGrammar();
      String _formatterName = FormatGeneratorUtil.getFormatterName(_targetGrammar, "");
      String _asPath = this.naming.asPath(_formatterName);
      String _plus = (_asPath + ".java");
      CharSequence _generateSrc = this.generateSrc(model);
      fsa.generateFile(_plus, FormatConstants.FORMATTER, _generateSrc);
    }
  }
  
  public CharSequence generateSrc(final FormatConfiguration model) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    Grammar _targetGrammar = model.getTargetGrammar();
    String _formatterName = FormatGeneratorUtil.getFormatterName(_targetGrammar, "");
    String _packageName = this.naming.toPackageName(_formatterName);
    _builder.append(_packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* The formatting configuration for ");
    Grammar _targetGrammar_1 = model.getTargetGrammar();
    String _name = _targetGrammar_1.getName();
    String _simpleName = this.naming.toSimpleName(_name);
    _builder.append(_simpleName, " ");
    _builder.append(".");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    Grammar _targetGrammar_2 = model.getTargetGrammar();
    String _formatterName_1 = FormatGeneratorUtil.getFormatterName(_targetGrammar_2, "");
    String _simpleName_1 = this.naming.toSimpleName(_formatterName_1);
    _builder.append(_simpleName_1, "");
    _builder.append(" extends ");
    Grammar _targetGrammar_3 = model.getTargetGrammar();
    String _formatterName_2 = FormatGeneratorUtil.getFormatterName(_targetGrammar_3, "Abstract");
    String _simpleName_2 = this.naming.toSimpleName(_formatterName_2);
    _builder.append(_simpleName_2, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("  ");
    _builder.append("// TODO: Provide a correct implementation of getSLCommentRule() and getMLCommentRule() in this class");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public ITreeAppendable generateMember(final JvmMember it, final ITreeAppendable appendable, final GeneratorConfig config) {
    if (it instanceof JvmConstructor) {
      return _generateMember((JvmConstructor)it, appendable, config);
    } else if (it instanceof JvmOperation) {
      return _generateMember((JvmOperation)it, appendable, config);
    } else if (it instanceof JvmField) {
      return _generateMember((JvmField)it, appendable, config);
    } else if (it instanceof JvmDeclaredType) {
      return _generateMember((JvmDeclaredType)it, appendable, config);
    } else if (it != null) {
      return _generateMember(it, appendable, config);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(it, appendable, config).toString());
    }
  }
}
