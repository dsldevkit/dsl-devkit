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

package com.avaloq.tools.ddk.xtext.generator.ui.contentAssist; // NOPMD PackageCase

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.GeneratedJavaFileAccess;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.ui.contentAssist.ContentAssistFragment2;

import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations;
import com.avaloq.tools.ddk.xtext.generator.parser.common.GrammarRuleAnnotations.SemanticPredicate;
import com.google.inject.Inject;


@SuppressWarnings({"checkstyle:MethodName", "PMD.UnusedFormalParameter", "PMD.PackageCase"})
public class AnnotationAwareContentAssistFragment2 extends ContentAssistFragment2 {

  /**
   * Whether the Proposal Provider extensions should be generated - default is true.
   */
  private boolean generateProposalProvider = true;

  @Inject
  private FileAccessFactory fileAccessFactory;

  @Inject
  private GrammarRuleAnnotations annotations;

  public boolean isGenerateProposalProvider() {
    return generateProposalProvider;
  }

  public void setGenerateProposalProvider(final boolean generateProposalProvider) {
    this.generateProposalProvider = generateProposalProvider;
  }

  @Override
  public void generate() {
    if (generateProposalProvider) {
      super.generate();
    }
  }

  // CHECKSTYLE:CONSTANTS-OFF
  // generation of the 'Abstract...ProposalProvider'

  @Override
  protected void generateGenJavaProposalProvider() {
    Grammar grammar = getGrammar();
    // excluded features are those that stem from inherited grammars,
    //  they are handled by the super grammars' proposal provider
    final Set<String> excludedFqnFeatureNames = getFQFeatureNamesToExclude(grammar);
    final Set<String> processedNames = new HashSet<>();
    annotations.annotateGrammar(grammar);
    // determine all assignments within the grammar that are not excluded and not handled yet
    final List<Assignment> assignments = new ArrayList<>();
    for (Assignment assignment : GrammarUtil.containedAssignments(grammar)) {
      String fqFeatureName = getFQFeatureName(assignment);
      if (!processedNames.contains(fqFeatureName) && !excludedFqnFeatureNames.contains(fqFeatureName)) {
        processedNames.add(fqFeatureName);
        assignments.add(assignment);
      }
    }

    // determine keyword rules
    final List<AbstractRule> keywordRules = new ArrayList<>();
    for (AbstractRule rule : grammar.getRules()) {
      String fqFeatureName = getFQFeatureName(rule);
      SemanticPredicate predAnnotation = annotations.getSemanticPredicateAnnotation(rule);
      if (!processedNames.contains(fqFeatureName) && !excludedFqnFeatureNames.contains(fqFeatureName)
        && predAnnotation != null && predAnnotation.getKeywords() != null
      ) {
        processedNames.add(fqFeatureName);
        keywordRules.add(rule);
      }
    }

    // determine the (remaining) rules that are not excluded and not handled yet
    final List<AbstractRule> remainingRules = new ArrayList<>();
    for (AbstractRule rule : grammar.getRules()) {
      String fqnFeatureName = getFQFeatureName(rule);
      if (!processedNames.contains(fqnFeatureName) && !excludedFqnFeatureNames.contains(fqnFeatureName)) {
        processedNames.add(fqnFeatureName);
        remainingRules.add(rule);
      }
    }

    // take the non-abstract class signature for the src-gen class in case of !generateStub
    //  as proposalProviders of sub languages refer to 'grammar.proposalProviderClass',
    //  see 'getGenProposalProviderSuperClass(...)'
    final TypeReference genClass =
      isGenerateStub() ? getGenProposalProviderClass(grammar) : getProposalProviderClass(grammar);

    GeneratedJavaFileAccess javaFile = fileAccessFactory.createGeneratedJavaFile(genClass);
    final TypeReference superClass = getGenProposalProviderSuperClass(grammar);

    javaFile.setTypeComment(new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("/**");
        target.newLine();
        target.append(" * Represents a generated, default implementation of superclass {@link ");
        target.append(superClass);
        target.append("}.");
        target.newLine();
        target.append(" * Methods are dynamically dispatched on the first parameter, i.e., you can override them");
        target.newLine();
        target.append(" * with a more concrete subtype.");
        target.newLine();
        target.append(" */");
        target.newLine();
      }
    });

    javaFile.setContent(new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("public ");
        if (isGenerateStub()) {
          target.append("abstract ");
        }
        target.append("class ");
        target.append(genClass.getSimpleName());
        target.append(" extends ");
        target.append(superClass);
        target.append(" {");
        target.newLineIfNotEmpty();
        target.newLine();

        if (!assignments.isEmpty()) {
          for (Assignment assignment : assignments) {
            target.append(handleAssignment(assignment));
          }
          target.newLine();
        }
        if (!keywordRules.isEmpty()) {
          for (AbstractRule rule : keywordRules) {
            target.append("  public void complete");
            target.append(getFQFeatureName(rule));
            target.append("(");
            target.append(EObject.class);
            target.append(" model, ");
            target.append(RuleCall.class);
            target.append(" ruleCall, ");
            target.append(getContentAssistContextClass());
            target.append(" context, ");
            target.append(getICompletionProposalAcceptorClass());
            target.append(" acceptor) {");
            target.newLineIfNotEmpty();
            target.append(handleKeywordRule(rule));
            target.append("  }");
            target.newLineIfNotEmpty();
          }
          target.newLine();
        }
        for (AbstractRule rule : remainingRules) {
          target.append("  public void complete");
          target.append(getFQFeatureName(rule));
          target.append("(");
          target.append(EObject.class);
          target.append(" model, ");
          target.append(RuleCall.class);
          target.append(" ruleCall, ");
          target.append(getContentAssistContextClass());
          target.append(" context, ");
          target.append(getICompletionProposalAcceptorClass());
          target.append(" acceptor) {");
          target.newLineIfNotEmpty();
          target.append("    // subclasses may override");
          target.newLineIfNotEmpty();
          target.append("  }");
          target.newLineIfNotEmpty();
        }
        target.append("}");
        target.newLineIfNotEmpty();
      }
    });
    javaFile.writeTo(getProjectConfig().getEclipsePlugin().getSrcGen());
  }

  private StringConcatenationClient handleKeywordRule(final AbstractRule rule) {
    return new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        SemanticPredicate predAnnotation = annotations.getSemanticPredicateAnnotation(rule);
        if (predAnnotation != null && predAnnotation.getKeywords() != null) {
          for (String keyword : predAnnotation.getKeywords()) {
            target.append("    acceptor.accept(createCompletionProposal(\"");
            target.append(keyword);
            target.append("\", context));");
            target.newLineIfNotEmpty();
          }
        }
      }
    };
  }

  private StringConcatenationClient handleAssignment(final Assignment assignment) {
    // determine all assignments within 'assignment's containing parser rule
    //  assigning the same feature, obtain their expected terminals, ...
    final List<AbstractElement> terminals = new ArrayList<>();
    for (Assignment a : GrammarUtil.containedAssignments(GrammarUtil.containingParserRule(assignment))) {
      if (a.getFeature().equals(assignment.getFeature())) {
        terminals.add(a.getTerminal());
      }
    }

    // ... and determine the types of those terminals
    final Set<EClass> terminalTypes = new HashSet<>();
    for (AbstractElement terminal : terminals) {
      terminalTypes.add(terminal.eClass());
    }

    return new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("  public void complete");
        target.append(getFQFeatureName(assignment));
        target.append("(");
        target.append(EObject.class);
        target.append(" model, ");
        target.append(Assignment.class);
        target.append(" assignment, ");
        target.append(getContentAssistContextClass());
        target.append(" context, ");
        target.append(getICompletionProposalAcceptorClass());
        target.append(" acceptor) {");
        target.newLineIfNotEmpty();
        if (terminalTypes.size() > 1) {
          target.append(handleAssignmentOptions(terminals));
        } else {
          target.append("    ");
          target.append(assignmentTerminal(assignment.getTerminal(), new StringConcatenationClient() {
            @Override
            protected void appendTo(final TargetStringConcatenation target) {
              target.append("assignment.getTerminal()");
            }
          }));
        }
        target.append("  }");
        target.newLineIfNotEmpty();
      }
    };
  }

  private StringConcatenationClient handleAssignmentOptions(final Iterable<AbstractElement> terminals) {
    final Set<EClass> processedTerminals = new HashSet<>();

    // for each type of terminal occurring in 'terminals' ...
    final List<AbstractElement> candidates = new ArrayList<>();
    for (AbstractElement terminal : terminals) {
      if (!processedTerminals.contains(terminal.eClass())) {
        processedTerminals.add(terminal.eClass());
        candidates.add(terminal);
      }
    }

    // ... generate an 'instanceof' clause
    return new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        for (AbstractElement terminal : candidates) {
          target.append("    if (assignment.getTerminal() instanceof ");
          target.append(terminal.eClass().getInstanceClass());
          target.append(") {");
          target.newLineIfNotEmpty();
          target.append("      ");
          target.append(assignmentTerminal(terminal, new StringConcatenationClient() {
            @Override
            protected void appendTo(final TargetStringConcatenation target) {
              target.append("assignment.getTerminal()");
            }
          }));
          target.append("    }");
          target.newLineIfNotEmpty();
        }
      }
    };
  }

  // dispatch methods for assignmentTerminal

  private StringConcatenationClient assignmentTerminal(final AbstractElement element, final StringConcatenationClient accessor) {
    if (element instanceof Alternatives) {
      return _assignmentTerminal((Alternatives) element, accessor);
    } else if (element instanceof CrossReference) {
      return _assignmentTerminal((CrossReference) element, accessor);
    } else if (element instanceof RuleCall) {
      return _assignmentTerminal((RuleCall) element, accessor);
    } else {
      return _assignmentTerminal(element, accessor);
    }
  }

  private StringConcatenationClient _assignmentTerminal(final AbstractElement element, final StringConcatenationClient accessor) {
    return new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("// subclasses may override");
        target.newLineIfNotEmpty();
      }
    };
  }

  private StringConcatenationClient _assignmentTerminal(final CrossReference element, final StringConcatenationClient accessor) {
    return new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("lookupCrossReference(((");
        target.append(CrossReference.class);
        target.append(")");
        target.append(accessor);
        target.append("), context, acceptor);");
        target.newLineIfNotEmpty();
      }
    };
  }

  private StringConcatenationClient _assignmentTerminal(final RuleCall element, final StringConcatenationClient accessor) {
    return new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        target.append("completeRuleCall(((");
        target.append(RuleCall.class);
        target.append(")");
        target.append(accessor);
        target.append("), context, acceptor);");
        target.newLineIfNotEmpty();
      }
    };
  }

  private StringConcatenationClient _assignmentTerminal(final Alternatives alternatives, final StringConcatenationClient accessor) {
    return new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation target) {
        List<AbstractElement> elements = alternatives.getElements();
        for (int i = 0; i < elements.size(); i++) {
          final int index = i;
          target.append(assignmentTerminal(elements.get(i), new StringConcatenationClient() {
            @Override
            protected void appendTo(final TargetStringConcatenation target) {
              target.append("((");
              target.append(Alternatives.class);
              target.append(")");
              target.append(accessor);
              target.append(").getElements().get(");
              target.append(Integer.toString(index));
              target.append(")");
            }
          }));
        }
      }
    };
  }

  // CHECKSTYLE:CONSTANTS-ON
  // helper methods

  private TypeReference getContentAssistContextClass() {
    return new TypeReference("org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext");
  }

  private TypeReference getICompletionProposalAcceptorClass() {
    return new TypeReference("org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor");
  }

  private String getFQFeatureName(final AbstractRule r) {
    return "_" + r.getName();
  }

  private String getFQFeatureName(final Assignment a) {
    String ruleName = GrammarUtil.containingParserRule(a).getName();
    String firstUpper = ruleName.substring(0, 1).toUpperCase() + ruleName.substring(1);
    String featureName = a.getFeature();
    String featureFirstUpper = featureName.substring(0, 1).toUpperCase() + featureName.substring(1);
    return firstUpper + "_" + featureFirstUpper;
  }

}
