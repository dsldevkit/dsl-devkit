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

package com.avaloq.tools.ddk.xtext.generator.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.xtext.generator.parser.antlr.KeywordHelper;

import com.google.common.base.Ascii;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


/**
 * Customization of {@link KeywordHelper} from Xtext.
 * <p>
 * Main difference and the reason to "copy" this class it to bring back only keywords reachable from the first rule of the given grammar.
 * </p>
 * <p>
 * The second change gives constants that represent keywords meaningful suffixes that simplify debugging of the grammar. We have not changed the prefix KEYWORD_
 * because of multiple dependencies on that prefix. We kept the numbering.
 * </p>
 */
public class AcfKeywordHelper implements Adapter {

  private final BiMap<CharSequence, String> keywordValueToToken;
  private final boolean ignoreCase;

  private static final Map<Character, String> SPECIAL_SYMBOL_MAP = Maps.newHashMap();

  /**
   * Initializes map with suffixes for special symbols.
   */
  static {
    SPECIAL_SYMBOL_MAP.put('[', "_SQBROP"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put(']', "_SQBRCL"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('!', "_EXC"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('=', "_EQ"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('<', "_LS"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('>', "_GR"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('(', "_POP"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put(')', "_PCL"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('|', "_VL"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('*', "_STAR"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('+', "_PLUS"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put(',', "_COMMA"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('-', "_MINUS"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('.', "_DOT"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('/', "_SLASH"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put(':', "_CLN"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put(';', "_SC"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('#', "_NUMB"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('"', "_QUOTE"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('$', "_DOLLAR"); //$NON-NLS-1$
    SPECIAL_SYMBOL_MAP.put('_', "_UNDERSC"); //$NON-NLS-1$
  }

  /**
   * Creates a new instance of {@link AcfKeywordHelper}.
   * The helper will be attached to the resource set containing the given grammar model as an adapter.
   *
   * @param grammar
   *          Root grammar model
   * @param ignoreCase
   *          Whether the keywords are case sensitive
   */
  public AcfKeywordHelper(final Grammar grammar, final boolean ignoreCase) {
    this.ignoreCase = ignoreCase;
    keywordValueToToken = createKeywordMap(grammar);
    Resource grammarResource = grammar.eResource();
    // Flattened grammars have null grammarResource
    if (grammarResource != null) {
      grammar.eResource().getResourceSet().eAdapters().add(this);
    }
  }

  /**
   * Detaches keyword helper from the resource set containing the given grammar model.
   *
   * @param grammar
   *          Root grammar model
   */
  public void discardHelper(final Grammar grammar) {
    grammar.eResource().getResourceSet().eAdapters().remove(this);
  }

  /**
   * Returns keyword helper for the resource set in which the given model element is contained.
   *
   * @param context
   *          Some grammar element
   * @return Keyword helper
   */
  public static AcfKeywordHelper getHelper(final EObject context) {
    for (Adapter candidate : context.eResource().getResourceSet().eAdapters()) {
      if (candidate instanceof AcfKeywordHelper) {
        return (AcfKeywordHelper) candidate;
      }
    }
    return null;
  }

  /**
   * Returns a rule name for the given keyword value.
   *
   * @param keywordValue
   *          Keyword value
   * @return Rule name
   */
  public String getRuleName(final String keywordValue) {
    return keywordValueToToken.get(createKey(keywordValue));
  }

  /**
   * Returns keyword value for the given rule name.
   *
   * @param ruleName
   *          Rule name
   * @return Keyword value
   */
  public String getKeywordValue(final String ruleName) {
    return keywordValueToToken.inverse().get(ruleName).toString();
  }

  /**
   * Checks whether the rule with the given name is a keyword rule.
   *
   * @param ruleName
   *          Name of a rule
   * @return {@code true} if the rule is a keyword rule, {@code false} otherwise
   */
  public boolean isKeywordRule(final String ruleName) {
    return keywordValueToToken.containsValue(ruleName);
  }

  /**
   * Returns all keywords.
   *
   * @return Set of keywords as strings
   */
  public Set<String> getAllKeywords() {
    Set<String> result = new TreeSet<>(KeywordHelper.keywordComparator);
    for (CharSequence cs : keywordValueToToken.keySet()) {
      result.add(cs.toString());
    }

    return Collections.unmodifiableSet(result);
  }

  /**
   * Initializes the keyword map.
   * Searches for keywords that belong to grammar rules reachable from the first rule of the given grammar.
   *
   * @param grammar
   *          Root grammar
   * @return Keyword map
   */
  private BiMap<CharSequence, String> createKeywordMap(final Grammar grammar) {
    List<ParserRule> parserRules = ParserSemanticPredicatesUtil.allReachableParserRules(grammar);
    List<EnumRule> enumRules = ParserSemanticPredicatesUtil.allReachableEnumRules(grammar);
    Iterator<EObject> iter = Iterators.concat(EcoreUtil.<EObject> getAllContents(parserRules), EcoreUtil.<EObject> getAllContents(enumRules));
    Iterator<Keyword> filtered = Iterators.filter(iter, Keyword.class);
    Iterator<String> transformed = Iterators.transform(filtered, Keyword::getValue);
    Set<String> treeSet = Sets.newTreeSet(new Comparator<String>() {
      @Override
      public int compare(final String o1, final String o2) {
        if (o1.length() == o2.length()) {
          return o1.compareTo(o2);
        }
        return Integer.compare(o1.length(), o2.length());
      }
    });
    Iterators.addAll(treeSet, transformed);
    BiMap<CharSequence, String> result = HashBiMap.create();
    int i = 1;
    for (String s : treeSet) {
      CharSequence key = createKey(s);
      if (!result.containsKey(key)) {
        result.put(key, createKeywordName(i, s));
        i++;
      }
    }
    return result;
  }

  /**
   * Generates a name for keyword grammar rule.
   *
   * @param count
   *          Numeric id for the name to ensure uniqueness
   * @param value
   *          Original value of the keyword
   * @return Rule name
   */
  private String createKeywordName(final int count, final String value) {
    StringBuilder name = new StringBuilder();
    name.append("KEYWORD_"); //$NON-NLS-1$
    name.append(count);
    name.append('_');
    for (char ch : value.toCharArray()) {
      char upch = Ascii.toUpperCase(ch);
      if (upch >= 'A' && upch <= 'Z' || upch == '_') {
        name.append(upch);
      } else if (SPECIAL_SYMBOL_MAP.containsKey(upch)) {
        name.append(SPECIAL_SYMBOL_MAP.get(upch));
      }
    }
    return name.toString();
  }

  /**
   * Creates a key for the given string.
   *
   * @param s
   *          String
   * @return Case sensitive or case insensitive key
   */
  private CharSequence createKey(final String s) {
    if (ignoreCase) {
      return new IgnoreCaseString(s);
    } else {
      return s;
    }
  }

  @Override
  public Notifier getTarget() {
    return null;
  }

  @Override
  public boolean isAdapterForType(final Object type) {
    return false;
  }

  @Override
  public void notifyChanged(final Notification notification) {
  }

  @Override
  public void setTarget(final Notifier newTarget) {
  }
}

/* Copyright (c) Avaloq Group AG */