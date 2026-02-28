/**
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Avaloq Group AG - initial API and implementation
 */
package com.avaloq.tools.ddk.xtext.generator.parser.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.internal.EmfAdaptable;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import org.eclipse.xtext.xtext.RuleWithParameterValues;
import org.eclipse.xtext.xtext.generator.grammarAccess.GrammarAccessExtensions;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

public class GrammarRuleAnnotations {

  @EmfAdaptable
  public static class NoBacktrack {
    public static class NoBacktrackAdapter extends AdapterImpl {
      private GrammarRuleAnnotations.NoBacktrack element;

      public NoBacktrackAdapter(final GrammarRuleAnnotations.NoBacktrack element) {
        this.element = element;
      }

      public GrammarRuleAnnotations.NoBacktrack get() {
        return this.element;
      }

      @Override
      public boolean isAdapterForType(final Object object) {
        return object == GrammarRuleAnnotations.NoBacktrack.class;
      }
    }

    public static GrammarRuleAnnotations.NoBacktrack findInEmfObject(final Notifier emfObject) {
      for (Adapter adapter : emfObject.eAdapters()) {
        if (adapter instanceof GrammarRuleAnnotations.NoBacktrack.NoBacktrackAdapter) {
          return ((GrammarRuleAnnotations.NoBacktrack.NoBacktrackAdapter) adapter).get();
        }
      }
      return null;
    }

    public static GrammarRuleAnnotations.NoBacktrack removeFromEmfObject(final Notifier emfObject) {
      List<Adapter> adapters = emfObject.eAdapters();
      for (int i = 0, max = adapters.size(); i < max; i++) {
        Adapter adapter = adapters.get(i);
        if (adapter instanceof GrammarRuleAnnotations.NoBacktrack.NoBacktrackAdapter) {
          emfObject.eAdapters().remove(i);
          return ((GrammarRuleAnnotations.NoBacktrack.NoBacktrackAdapter) adapter).get();
        }
      }
      return null;
    }

    public void attachToEmfObject(final Notifier emfObject) {
      NoBacktrack result = findInEmfObject(emfObject);
      if (result != null) {
        throw new IllegalStateException("The given EMF object already contains an adapter for NoBacktrack");
      }
      GrammarRuleAnnotations.NoBacktrack.NoBacktrackAdapter adapter = new GrammarRuleAnnotations.NoBacktrack.NoBacktrackAdapter(this);
      emfObject.eAdapters().add(adapter);
    }

    @Override
    @Pure
    public int hashCode() {
      return 1;
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      return b.toString();
    }
  }

  /**
   * Gated predicate: {condition}?=>
   * Validating predicate: {condition message}?
   */
  @EmfAdaptable
  public static class SemanticPredicate {
    public static class SemanticPredicateAdapter extends AdapterImpl {
      private GrammarRuleAnnotations.SemanticPredicate element;

      public SemanticPredicateAdapter(final GrammarRuleAnnotations.SemanticPredicate element) {
        this.element = element;
      }

      public GrammarRuleAnnotations.SemanticPredicate get() {
        return this.element;
      }

      @Override
      public boolean isAdapterForType(final Object object) {
        return object == GrammarRuleAnnotations.SemanticPredicate.class;
      }
    }

    private final String name;

    private final String message;

    private final String grammar;

    private final List<String> keywords;

    public static GrammarRuleAnnotations.SemanticPredicate findInEmfObject(final Notifier emfObject) {
      for (Adapter adapter : emfObject.eAdapters()) {
        if (adapter instanceof GrammarRuleAnnotations.SemanticPredicate.SemanticPredicateAdapter) {
          return ((GrammarRuleAnnotations.SemanticPredicate.SemanticPredicateAdapter) adapter).get();
        }
      }
      return null;
    }

    public static GrammarRuleAnnotations.SemanticPredicate removeFromEmfObject(final Notifier emfObject) {
      List<Adapter> adapters = emfObject.eAdapters();
      for (int i = 0, max = adapters.size(); i < max; i++) {
        Adapter adapter = adapters.get(i);
        if (adapter instanceof GrammarRuleAnnotations.SemanticPredicate.SemanticPredicateAdapter) {
          emfObject.eAdapters().remove(i);
          return ((GrammarRuleAnnotations.SemanticPredicate.SemanticPredicateAdapter) adapter).get();
        }
      }
      return null;
    }

    public void attachToEmfObject(final Notifier emfObject) {
      SemanticPredicate result = findInEmfObject(emfObject);
      if (result != null) {
        throw new IllegalStateException("The given EMF object already contains an adapter for SemanticPredicate");
      }
      GrammarRuleAnnotations.SemanticPredicate.SemanticPredicateAdapter adapter = new GrammarRuleAnnotations.SemanticPredicate.SemanticPredicateAdapter(this);
      emfObject.eAdapters().add(adapter);
    }

    public SemanticPredicate(final String name, final String message, final String grammar, final List<String> keywords) {
      super();
      this.name = name;
      this.message = message;
      this.grammar = grammar;
      this.keywords = keywords;
    }

    @Override
    @Pure
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
      result = prime * result + ((this.message == null) ? 0 : this.message.hashCode());
      result = prime * result + ((this.grammar == null) ? 0 : this.grammar.hashCode());
      return prime * result + ((this.keywords == null) ? 0 : this.keywords.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      GrammarRuleAnnotations.SemanticPredicate other = (GrammarRuleAnnotations.SemanticPredicate) obj;
      if (this.name == null) {
        if (other.name != null) {
          return false;
        }
      } else if (!this.name.equals(other.name)) {
        return false;
      }
      if (this.message == null) {
        if (other.message != null) {
          return false;
        }
      } else if (!this.message.equals(other.message)) {
        return false;
      }
      if (this.grammar == null) {
        if (other.grammar != null) {
          return false;
        }
      } else if (!this.grammar.equals(other.grammar)) {
        return false;
      }
      if (this.keywords == null) {
        if (other.keywords != null) {
          return false;
        }
      } else if (!this.keywords.equals(other.keywords)) {
        return false;
      }
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("name", this.name);
      b.add("message", this.message);
      b.add("grammar", this.grammar);
      b.add("keywords", this.keywords);
      return b.toString();
    }

    @Pure
    public String getName() {
      return this.name;
    }

    @Pure
    public String getMessage() {
      return this.message;
    }

    @Pure
    public String getGrammar() {
      return this.grammar;
    }

    @Pure
    public List<String> getKeywords() {
      return this.keywords;
    }
  }

  @EmfAdaptable
  public static class GrammarAnnotations {
    public static class GrammarAnnotationsAdapter extends AdapterImpl {
      private GrammarRuleAnnotations.GrammarAnnotations element;

      public GrammarAnnotationsAdapter(final GrammarRuleAnnotations.GrammarAnnotations element) {
        this.element = element;
      }

      public GrammarRuleAnnotations.GrammarAnnotations get() {
        return this.element;
      }

      @Override
      public boolean isAdapterForType(final Object object) {
        return object == GrammarRuleAnnotations.GrammarAnnotations.class;
      }
    }

    private final List<GrammarRuleAnnotations.SemanticPredicate> predicates;

    public static GrammarRuleAnnotations.GrammarAnnotations findInEmfObject(final Notifier emfObject) {
      for (Adapter adapter : emfObject.eAdapters()) {
        if (adapter instanceof GrammarRuleAnnotations.GrammarAnnotations.GrammarAnnotationsAdapter) {
          return ((GrammarRuleAnnotations.GrammarAnnotations.GrammarAnnotationsAdapter) adapter).get();
        }
      }
      return null;
    }

    public static GrammarRuleAnnotations.GrammarAnnotations removeFromEmfObject(final Notifier emfObject) {
      List<Adapter> adapters = emfObject.eAdapters();
      for (int i = 0, max = adapters.size(); i < max; i++) {
        Adapter adapter = adapters.get(i);
        if (adapter instanceof GrammarRuleAnnotations.GrammarAnnotations.GrammarAnnotationsAdapter) {
          emfObject.eAdapters().remove(i);
          return ((GrammarRuleAnnotations.GrammarAnnotations.GrammarAnnotationsAdapter) adapter).get();
        }
      }
      return null;
    }

    public void attachToEmfObject(final Notifier emfObject) {
      GrammarAnnotations result = findInEmfObject(emfObject);
      if (result != null) {
        throw new IllegalStateException("The given EMF object already contains an adapter for GrammarAnnotations");
      }
      GrammarRuleAnnotations.GrammarAnnotations.GrammarAnnotationsAdapter adapter = new GrammarRuleAnnotations.GrammarAnnotations.GrammarAnnotationsAdapter(this);
      emfObject.eAdapters().add(adapter);
    }

    public GrammarAnnotations(final List<GrammarRuleAnnotations.SemanticPredicate> predicates) {
      super();
      this.predicates = predicates;
    }

    @Override
    @Pure
    public int hashCode() {
      return 31 * 1 + ((this.predicates == null) ? 0 : this.predicates.hashCode());
    }

    @Override
    @Pure
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      GrammarRuleAnnotations.GrammarAnnotations other = (GrammarRuleAnnotations.GrammarAnnotations) obj;
      if (this.predicates == null) {
        if (other.predicates != null) {
          return false;
        }
      } else if (!this.predicates.equals(other.predicates)) {
        return false;
      }
      return true;
    }

    @Override
    @Pure
    public String toString() {
      ToStringBuilder b = new ToStringBuilder(this);
      b.add("predicates", this.predicates);
      return b.toString();
    }

    @Pure
    public List<GrammarRuleAnnotations.SemanticPredicate> getPredicates() {
      return this.predicates;
    }
  }

  /**
   * Pattern to deactivate backtracking for single rule.
   */
  private static final Pattern NO_BACKTRACK_ANNOTATION_PATTERN = Pattern.compile("@NoBacktrack", Pattern.MULTILINE); // $NON-NLS-1$

  /**
   * Pattern to search for keyword rule annotations and extracting list of comma-separated keywords.
   */
  private static final Pattern KEYWORD_RULE_ANNOTATION_PATTERN = Pattern.compile("@KeywordRule\\(([\\w\\s,]+)\\)",
      Pattern.MULTILINE); // $NON-NLS-1$

  /**
   * Pattern to search for semantic predicate rule annotation that enables the given rule.
   */
  private static final Pattern SEMANTIC_PREDICATE_PATTERN = Pattern.compile("@SemanticPredicate\\(([\\s]*[\\w]+)[\\s]*\\)",
      Pattern.MULTILINE); // $NON-NLS-1$

  /**
   * Splits comma separated list of keywords.
   */
  private static final Splitter KEYWORDS_SPLITTER = Splitter.on(',').trimResults().omitEmptyStrings();

  @Inject
  private GrammarAccessExtensions grammarExtensions;

  public boolean hasNoBacktrackAnnotation(final AbstractRule rule) {
    return getNoBacktrackAnnotation(rule) != null;
  }

  /**
   * Checks if the given element is contained in a rule with a NoBacktrack annotation.
   *
   * @param element
   *          Grammar element
   * @return {@code true} if there is an annotation on the enclosing rule, {@code false} otherwise
   */
  public boolean hasNoBacktrackAnnotation(final AbstractElement element) {
    return hasNoBacktrackAnnotation(GrammarUtil.containingRule(element));
  }

  public boolean hasSemanticPredicate(final AbstractElement element) {
    return findPredicate(element) != null;
  }

  public boolean hasValidatingPredicate(final AbstractRule rule) {
    return getSemanticPredicateAnnotation(rule) != null;
  }

  /**
   * Returns disambiguating/validating semantic predicate.
   *
   * @param element
   *          Xtext grammar element
   * @return A string containing the semantic predicate or an empty string
   */
  public String generateValidatingPredicate(final AbstractRule rule) {
    final SemanticPredicate predicate = getSemanticPredicateAnnotation(rule);
    if (predicate != null) {
      return generateValidatingPredicate(predicate);
    }
    return "";
  }

  public boolean isGatedPredicateRequired(final AbstractElement element) {
    return isStartingWithPredicatedRule(element) && isAlternativeAvailable(element);
  }

  /**
   * Returns gated semantic predicate.
   *
   * @param element
   *          Xtext grammar element
   * @return A string containing the semantic predicate or an empty string
   */
  public String generateGatedPredicate(final AbstractElement element) {
    final SemanticPredicate predicate = findPredicate(element);
    if (predicate != null) {
      return generateGatedPredicate(predicate);
    }
    return "";
  }

  public GrammarAnnotations annotateGrammar(final Grammar grammar) {
    GrammarAnnotations annotations = GrammarAnnotations.findInEmfObject(grammar);
    if (annotations == null) {
      List<SemanticPredicate> collected = grammar.getRules().stream()
          .map(r -> annotateRule(r))
          .filter(a -> a != null)
          .collect(Collectors.toList());
      annotations = new GrammarAnnotations(collected);
      annotations.attachToEmfObject(grammar);

      Set<Grammar> inheritedGrammars = allInheritedGrammars(grammar);
      for (Grammar inheritedGrammar : inheritedGrammars) {
        GrammarAnnotations inheritedAnnotations = GrammarAnnotations.findInEmfObject(inheritedGrammar);
        if (inheritedAnnotations == null) {
          List<SemanticPredicate> inheritedCollected = inheritedGrammar.getRules().stream()
              .map(r -> annotateRule(r))
              .filter(a -> a != null)
              .collect(Collectors.toList());
          inheritedAnnotations = new GrammarAnnotations(inheritedCollected);
          inheritedAnnotations.attachToEmfObject(inheritedGrammar);
        }
        annotations.predicates.addAll(inheritedAnnotations.predicates);
      }
    }
    return annotations;
  }

  public Set<Grammar> allInheritedGrammars(final Grammar grammar) {
    HashSet<Grammar> result = Sets.newHashSet();
    List<ParserRule> allParserRules = GrammarUtil.allParserRules(grammar);
    for (AbstractRule rule : allParserRules) {
      Grammar ruleGrammar = GrammarUtil.getGrammar(rule);
      if (!ruleGrammar.equals(grammar) && this.isSemanticPredicate(rule)) {
        result.add(ruleGrammar);
      }
    }
    return new HashSet<>(result);
  }

  public List<SemanticPredicate> predicates(final Grammar grammar) {
    return annotateGrammar(grammar).predicates;
  }

  /**
   * Checks whether this abstract element leads directly to a keyword rule.
   *
   * @param element
   *          Element call
   * @return {@code true} if the rule leads to a keyword rule
   */
  public boolean isStartingWithPredicatedRule(final AbstractElement element) {
    if (element instanceof CrossReference) {
      return findPredicate(((CrossReference) element).getTerminal()) != null;
    }
    return findPredicate(element) != null;
  }

  /**
   * Finds the predicated rule the given element leads to.
   *
   * @param element
   *          Current element
   * @return Keyword rule or {@code null} if the given element does not lead to a keyword rule
   */
  public SemanticPredicate findPredicate(final AbstractElement element) {
    if (element instanceof RuleCall) {
      final SemanticPredicate predicate = getSemanticPredicateAnnotation(((RuleCall) element).getRule());
      if (predicate != null) {
        return predicate;
      }
      return findPredicate(((RuleCall) element).getRule().getAlternatives());
    }
    if (GrammarUtil.isOptionalCardinality(element)) {
      return null;
    }
    if (element instanceof CrossReference) {
      return findPredicate(((CrossReference) element).getTerminal());
    }
    if (element instanceof Group) {
      final AbstractElement firstNonActionElement = getFirstNonActionElement((Group) element);
      if (firstNonActionElement != null) {
        return findPredicate(firstNonActionElement);
      }
    }
    if (element instanceof Assignment) {
      return findPredicate(((Assignment) element).getTerminal());
    }
    return null;
  }

  public boolean isRuleWithPredicate(final AbstractRule rule) {
    return getSemanticPredicateAnnotation(rule) != null;
  }

  public AbstractElement getFirstNonActionElement(final Group group) {
    for (AbstractElement groupElement : group.getElements()) {
      if (!(groupElement instanceof Action)) {
        return groupElement;
      }
    }
    return null;
  }

  /**
   * Checks whether there is a viable alternative (i.e. not potentially gated by another gated semantic predicate) for the current element.
   * If there is no alternative we should not insert gated semantic predicates, but validating semantic predicates, so we get a better error recovery and
   * a correct message. We should not also propagate validating predicates in the callers. If we do so this will damage error recovery in the callers.
   *
   * @param element
   *          Grammar element
   * @return {@code true} if there is an alternative available and the gated semantic predicate can be inserted
   */
  public boolean isAlternativeAvailable(final AbstractElement element) {
    if (GrammarUtil.isOptionalCardinality(element)) {
      return true;
    }
    final EObject container = element.eContainer();
    if (container instanceof Assignment) {
      return isAlternativeAvailable((AbstractElement) container);
    } else if (container instanceof CrossReference) {
      return isAlternativeAvailable((AbstractElement) container);
    } else if (container instanceof Group) {
      if (isFirstNonActionElement((Group) container, element)) {
        return isAlternativeAvailable((AbstractElement) container);
      }
    } else if (container instanceof Alternatives) {
      for (AbstractElement alternative : ((Alternatives) container).getElements()) {
        if (!Objects.equals(alternative, element)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the given grammar element is the first non action element in the given group.
   *
   * @param group
   *          where the position is checked, must not be {@code null}
   * @param element
   *          to check, must not be {@code null}
   * @return {@code true}, if is first non action element is the given element, {@code false} otherwise
   */
  public boolean isFirstNonActionElement(final Group group, final AbstractElement element) {
    return Objects.equals(element, getFirstNonActionElement(group));
  }

  public String generateGatedPredicate(final SemanticPredicate predicate) {
    StringBuilder sb = new StringBuilder();
    sb.append("{predicates.");
    sb.append(predicate.name);
    sb.append("(parserContext)}?=>");
    return sb.toString();
  }

  public String generateValidatingPredicate(final SemanticPredicate predicate) {
    StringBuilder sb = new StringBuilder();
    sb.append("{predicates.");
    sb.append(predicate.name);
    sb.append("(parserContext) /* @ErrorMessage(");
    sb.append(predicate.message);
    sb.append(") */}?");
    return sb.toString();
  }

  /**
   * Translate annotations in comments into predicate annotations.
   *
   * <p>
   * <em>Gated vs. Disambiguating predicates</em> On the first look a disambiguating semantic predicate
   * would be a right choice. However inserting them would be much more difficult. The reason is that we need
   * not only insert them in the beginning of the current rule, but also propagate before actions into alternatives
   * that call this rule. We have to do the same for gated predicated, however there is one important difference.
   * If we have an alternative like {@code rule: (Keyword1 | Keyword2) Keyword3 ;} then we need to insert the predicate
   * only before the first alternative (Keyword1). If we insert disambiguating semantic predicate before both
   * the exception the predicate may throw can destroy recovery for the entire rule i.e. will not try going to Keyword3.
   * We, however, need validating predicate in the beginning of each rule Keyword2, Keyword3. This requires an
   * extra analysis which might be a next step. Gated predicates we can safely insert before Keyword1 and Keyword2.
   * This will have no negative impact. We still need validating predicates in Keyword rules themselves.
   * </p>
   */
  public SemanticPredicate annotateRule(final AbstractRule rule) {
    final String text = getText(rule);
    SemanticPredicate predicate = null;
    if (text != null) {
      final String ruleGrammarName = GrammarUtil.getSimpleName(GrammarUtil.getGrammar(rule));
      predicate = getKeywordRulePredicate(text, rule.getName(), ruleGrammarName);
      if (predicate != null) {
        predicate.attachToEmfObject(rule);
      }
      final SemanticPredicate semanticPredicate = getSemanticPredicate(text, ruleGrammarName);
      if (semanticPredicate != null) {
        if (predicate != null) {
          throw new IllegalArgumentException("You may not combine keyword annotations with semantic predicate annotations on one rule: " + rule.getName());
        }
        semanticPredicate.attachToEmfObject(rule);
        predicate = semanticPredicate;
      }
      final NoBacktrack noBacktrack = getNoBacktrack(text);
      if (noBacktrack != null) {
        noBacktrack.attachToEmfObject(rule);
      }
    }
    return predicate;
  }

  public SemanticPredicate getSemanticPredicateAnnotation(final AbstractRule rule) {
    if (rule != null) {
      RuleWithParameterValues ruleWithParams = RuleWithParameterValues.findInEmfObject(rule);
      AbstractRule original = null;
      if (ruleWithParams != null) {
        original = ruleWithParams.getOriginal();
      }
      if (original == null) {
        original = rule;
      }
      return SemanticPredicate.findInEmfObject(original);
    }
    return null;
  }

  public NoBacktrack getNoBacktrackAnnotation(final AbstractRule rule) {
    if (rule != null) {
      AbstractRule original = RuleWithParameterValues.findInEmfObject(rule).getOriginal();
      if (original == null) {
        original = rule;
      }
      return NoBacktrack.findInEmfObject(original);
    }
    return null;
  }

  /**
   * Checks if the given rule contains {@code @KeywordRule(kw1,kw2)} annotation.
   */
  public SemanticPredicate getKeywordRulePredicate(final String text, final String ruleName, final String grammar) {
    final Matcher matcher = KEYWORD_RULE_ANNOTATION_PATTERN.matcher(text);
    if (matcher.find()) {
      ArrayList<String> keywordsList = Lists.newArrayList(KEYWORDS_SPLITTER.split(matcher.group(1)));
      return new SemanticPredicate(
          "is" + ruleName + "Enabled",
          "get" + ruleName + "EnabledMessage",
          grammar,
          keywordsList);
    }
    return null;
  }

  /**
   * Checks if the given rule contains {@code @SemanticPredicate(condition)} annotation.
   */
  public SemanticPredicate getSemanticPredicate(final String text, final String grammar) {
    final Matcher matcher = SEMANTIC_PREDICATE_PATTERN.matcher(text);
    if (matcher.find()) {
      return new SemanticPredicate(
          matcher.group(1),
          "get" + matcher.group(1) + "Message",
          grammar,
          null);
    }
    return null;
  }

  /**
   * Checks if the given rule contains a NoBacktrack annotation.
   */
  public NoBacktrack getNoBacktrack(final String text) {
    final Matcher matcher = NO_BACKTRACK_ANNOTATION_PATTERN.matcher(text);
    if (matcher.find()) {
      return new NoBacktrack();
    }
    return null;
  }

  public String getText(final AbstractRule rule) {
    final ICompositeNode node = NodeModelUtils.getNode(rule);
    if (node != null) {
      return node.getText();
    } else {
      return grammarExtensions.grammarFragmentToString(rule, "");
    }
  }

  /**
   * Checks if the given rule contains a keyword rule annotation.
   *
   * @param rule
   *          Grammar rule
   * @return {@code true} if there is an annotation, {@code false} otherwise
   */
  public boolean isSemanticPredicate(final AbstractRule rule) {
    String text = getText(rule);
    if (text != null) {
      final Matcher matcher = SEMANTIC_PREDICATE_PATTERN.matcher(text);
      if (matcher.find()) {
        return true;
      }
    }
    return false;
  }
}
