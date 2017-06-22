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
package com.avaloq.tools.ddk.xtext.generator.serializer;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.grammarAccess.GrammarAccess;
import org.eclipse.xtext.generator.serializer.AbstractSemanticSequencer;
import org.eclipse.xtext.generator.serializer.JavaEMFFile;
import org.eclipse.xtext.generator.serializer.SemanticSequencerUtil;
import org.eclipse.xtext.generator.serializer.SerializerGenFileNames;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.analysis.IGrammarConstraintProvider;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;

@SuppressWarnings("all")
public class AbstractFixedSemanticSequencer extends AbstractSemanticSequencer {
  private final Logger LOG = Logger.getLogger(AbstractFixedSemanticSequencer.class);
  
  private final String METHOD_SEPARATOR = "\n\n";
  
  @Inject
  private Grammar grammar;
  
  @Inject
  @Extension
  private GrammarAccess grammarAccess;
  
  @Inject
  @Extension
  private SemanticSequencerUtil sequencerUtil;
  
  @Inject
  private SerializerGenFileNames names;
  
  @Inject
  @Extension
  private Naming _naming;
  
  @Override
  public CharSequence getFileContents(final SerializerGenFileNames.GenFileName filename) {
    String _xblockexpression = null;
    {
      Resource _eResource = this.grammar.eResource();
      ResourceSet _resourceSet = _eResource.getResourceSet();
      String _packageName = filename.getPackageName();
      String _fileHeader = this._naming.fileHeader();
      final JavaEMFFile file = new JavaEMFFile(_resourceSet, _packageName, _fileHeader);
      file.imported(EObject.class);
      file.imported(GenericSequencer.class);
      file.imported(ISemanticSequencer.class);
      file.imported(ITransientValueService.class);
      file.imported(ISemanticSequenceAcceptor.class);
      file.imported(ISemanticSequencerDiagnosticProvider.class);
      file.imported(ISerializationDiagnostic.Acceptor.class);
      file.imported(Inject.class);
      file.imported(Provider.class);
      String _annotationImports = this._naming.annotationImports();
      boolean _notEquals = (!Objects.equal(_annotationImports, null));
      if (_notEquals) {
        String _annotationImports_1 = this._naming.annotationImports();
        String[] _split = _annotationImports_1.split("(import)|;");
        final Function1<String, String> _function = (String it) -> {
          return it.trim();
        };
        List<String> _map = ListExtensions.<String, String>map(((List<String>)Conversions.doWrapArray(_split)), _function);
        final Function1<String, Boolean> _function_1 = (String it) -> {
          boolean _isEmpty = it.isEmpty();
          return Boolean.valueOf((!_isEmpty));
        };
        Iterable<String> _filter = IterableExtensions.<String>filter(_map, _function_1);
        final Consumer<String> _function_2 = (String it) -> {
          file.imported(it);
        };
        _filter.forEach(_function_2);
      }
      final Collection<IGrammarConstraintProvider.IConstraint> localConstraints = this.sequencerUtil.getGrammarConstraints(this.grammar);
      Grammar _superGrammar = this.sequencerUtil.getSuperGrammar(this.grammar);
      final Collection<IGrammarConstraintProvider.IConstraint> superConstraints = this.sequencerUtil.getGrammarConstraints(_superGrammar);
      final Function1<IGrammarConstraintProvider.IConstraint, Boolean> _function_3 = (IGrammarConstraintProvider.IConstraint it) -> {
        return Boolean.valueOf(((it.getType() != null) && (!superConstraints.contains(it))));
      };
      Iterable<IGrammarConstraintProvider.IConstraint> _filter_1 = IterableExtensions.<IGrammarConstraintProvider.IConstraint>filter(localConstraints, _function_3);
      final Set<IGrammarConstraintProvider.IConstraint> newLocalConstraints = IterableExtensions.<IGrammarConstraintProvider.IConstraint>toSet(_filter_1);
      String _xifexpression = null;
      final Function1<IGrammarConstraintProvider.IConstraint, Boolean> _function_4 = (IGrammarConstraintProvider.IConstraint it) -> {
        return Boolean.valueOf(superConstraints.contains(it));
      };
      boolean _exists = IterableExtensions.<IGrammarConstraintProvider.IConstraint>exists(localConstraints, _function_4);
      if (_exists) {
        SerializerGenFileNames.GenFileName _semanticSequencer = this.names.getSemanticSequencer();
        EList<Grammar> _usedGrammars = this.grammar.getUsedGrammars();
        Grammar _head = IterableExtensions.<Grammar>head(_usedGrammars);
        String _qualifiedName = _semanticSequencer.getQualifiedName(_head);
        _xifexpression = file.imported(_qualifiedName);
      } else {
        _xifexpression = file.imported(AbstractDelegatingSemanticSequencer.class);
      }
      final String superGrammar = _xifexpression;
      final HashSet<Pair<String, EClass>> methodSignatures = CollectionLiterals.<Pair<String, EClass>>newHashSet();
      SerializerGenFileNames.GenFileName _abstractSemanticSequencer = this.names.getAbstractSemanticSequencer();
      final String clazz = _abstractSemanticSequencer.getSimpleName(this.grammar);
      String _xifexpression_1 = null;
      boolean _isAbstract = filename.isAbstract();
      if (_isAbstract) {
        _xifexpression_1 = "abstract ";
      } else {
        _xifexpression_1 = "";
      }
      final String _abstract = _xifexpression_1;
      StringConcatenation _builder = new StringConcatenation();
      String _classAnnotations = this._naming.classAnnotations();
      _builder.append(_classAnnotations, "");
      _builder.append("@SuppressWarnings(\"all\")");
      _builder.newLineIfNotEmpty();
      _builder.append("public ");
      _builder.append(_abstract, "");
      _builder.append("class ");
      String _simpleName = filename.getSimpleName();
      _builder.append(_simpleName, "");
      _builder.append(" extends ");
      _builder.append(superGrammar, "");
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("  ");
      _builder.append("@Inject");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("private ");
      String _gaFQName = this.grammarAccess.gaFQName(this.grammar);
      String _imported = file.imported(_gaFQName);
      _builder.append(_imported, "  ");
      _builder.append(" grammarAccess;");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("  ");
      CharSequence _genMethodCreateSequence = this.genMethodCreateSequence(file);
      _builder.append(_genMethodCreateSequence, "  ");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      {
        List<IGrammarConstraintProvider.IConstraint> _sort = IterableExtensions.<IGrammarConstraintProvider.IConstraint>sort(newLocalConstraints);
        boolean _hasElements = false;
        for(final IGrammarConstraintProvider.IConstraint c : _sort) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(this.METHOD_SEPARATOR, "  ");
          }
          {
            String _simpleName_1 = c.getSimpleName();
            EClass _type = c.getType();
            Pair<String, EClass> _mappedTo = Pair.<String, EClass>of(_simpleName_1, _type);
            boolean _add = methodSignatures.add(_mappedTo);
            if (_add) {
              _builder.append("  ");
              CharSequence _genMethodSequence = this.genMethodSequence(file, c);
              _builder.append(_genMethodSequence, "  ");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("  ");
              this.LOG.warn(("Skipped generating duplicate method in " + clazz));
              _builder.newLineIfNotEmpty();
              _builder.append("  ");
              CharSequence _genMethodSequenceComment = this.genMethodSequenceComment(file, c);
              _builder.append(_genMethodSequenceComment, "  ");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.append("}");
      _builder.newLine();
      file.setBody(_builder.toString());
      _xblockexpression = file.toString();
    }
    return _xblockexpression;
  }
  
  private CharSequence genMethodSequenceComment(final JavaEMFFile file, final IGrammarConstraintProvider.IConstraint c) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("// This method is commented out because it has the same signature as another method in this class.");
    _builder.newLine();
    _builder.append("// This is probably a bug in Xtext\'s serializer, please report it here:");
    _builder.newLine();
    _builder.append("// https://bugs.eclipse.org/bugs/enter_bug.cgi?product=TMF");
    _builder.newLine();
    _builder.append("//");
    _builder.newLine();
    _builder.append("//");
    _builder.newLine();
    _builder.append("// Constraint:");
    _builder.newLine();
    _builder.append("//     ");
    {
      IGrammarConstraintProvider.IConstraintElement _body = c.getBody();
      boolean _tripleEquals = (_body == null);
      if (_tripleEquals) {
        _builder.append("{");
        EClass _type = c.getType();
        String _name = _type.getName();
        _builder.append(_name, "");
        _builder.append("}");
      } else {
        IGrammarConstraintProvider.IConstraintElement _body_1 = c.getBody();
        String _string = _body_1.toString();
        String _replaceAll = _string.replaceAll("\\n", "\n//     ");
        _builder.append(_replaceAll, "");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("//");
    _builder.newLine();
    _builder.append("// protected void sequence_");
    String _simpleName = c.getSimpleName();
    _builder.append(_simpleName, "");
    _builder.append("(EObject context, ");
    EClass _type_1 = c.getType();
    _builder.append(_type_1, "");
    _builder.append(" semanticObject) { }");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
