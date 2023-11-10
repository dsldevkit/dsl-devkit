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
package com.avaloq.tools.ddk.xtext.formatting;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.formatting.IElementMatcherProvider.IElementMatcher;
import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.ElementLocator;
import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.ElementPattern;
import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.LocatorType;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.formatting.impl.FormattingConfigBasedStream;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parsetree.reconstr.IHiddenTokenHelper;
import org.eclipse.xtext.parsetree.reconstr.ITokenStream;

import com.avaloq.tools.ddk.xtext.formatting.locators.ColumnLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IConditionalLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IIndentationLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IParametrizedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorEndFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.IndentationLocatorStartFacade;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorActivator;
import com.avaloq.tools.ddk.xtext.formatting.locators.LocatorParameterCalculator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoFormatLocator;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * The class ExtendedFormattingConfigBasedStream extends {@link org.eclipse.xtext.formatting.impl.FormattingConfigBasedStream} to
 * replace its inner classes Line and LineEntry to give support for more locators. Those include RightPaddingLocator
 * and ColumnLocator.
 */
public class ExtendedFormattingConfigBasedStream extends FormattingConfigBasedStream implements IDelegatingTokenStream {

  private static final Logger LOGGER = LogManager.getLogger(ExtendedFormattingConfigBasedStream.class);
  private final Stack<Integer> columnIndents = new Stack<Integer>();
  private final Stack<Integer> initialIndents = new Stack<Integer>();
  private final Set<NoFormatLocator> noFormatLocators = new HashSet<NoFormatLocator>();

  private INode currentNode;

  private final AbstractExtendedFormatter formatter;

  private static final String NEW_LINE = "\n"; //$NON-NLS-1$

  /** Boolean flag to signify that the formatting should be stopped. */
  private boolean explicitFormattingOff;
  private Integer currentColumn;
  private final Map<EObject, Integer> columnMap = new HashMap<EObject, Integer>();
  private final NoFormatLocator noFormatMemento;
  private int formatDisablingDirectives;

  /**
   * Stores a value of 'preserveSpaces' preference. (defined in the superclass)
   * Is used to reset the old value after the formatting has been switched off and then turned on again.
   */
  private boolean storedPreserveSpacesValue;

  // @Format-Off
  public ExtendedFormattingConfigBasedStream(final ITokenStream out, final String indentation, final FormattingConfig cfg, final IElementMatcher<ElementPattern> matcher,
                                        final IHiddenTokenHelper hiddenTokenHelper, final boolean preserveSpaces, final AbstractExtendedFormatter formatter) {
  // @Format-On
    super(out, indentation, cfg, matcher, hiddenTokenHelper, preserveSpaces);
    this.storedPreserveSpacesValue = preserveSpaces;
    this.formatter = formatter;
    this.noFormatMemento = new NoFormatLocator(cfg);
  }

  /**
   * Gets the associated {@link AbstractExtendedFormatter}.
   *
   * @return the {@link AbstractExtendedFormatter}
   */
  public AbstractExtendedFormatter getFormatter() {
    return formatter;
  }

  protected void setCurrentColumn(final Integer columnOffset) {
    this.currentColumn = columnOffset;
  }

  
  @Override
  public ITokenStream getDelegateStream() {
    return this.out;
  }

  
  @Override
  public void setDelegateStream(final ITokenStream delegate) {
    this.out = delegate;
  }

  /**
   * Disables or re-enables formatting based on the given set of {@link ElementLocator}s.
   * I.e. if there is at least one {@link NoFormatLocator} in the given set, otherwise no action is taken.
   *
   * @param locators
   *          the {@link ElementLocator}s to check
   */
  private void handleNoFormatLocators(final Set<ElementLocator> locators) {
    if (this.noFormatLocators.remove(this.noFormatMemento) && this.noFormatLocators.isEmpty() && !this.explicitFormattingOff) {
      // Removing the existing NoFormat memento and re-enabling formatting
      this.preserveSpaces = this.storedPreserveSpacesValue;
    }
    for (ElementLocator locator : locators) {
      if (locator instanceof NoFormatLocator) {
        if (!this.noFormatLocators.remove(locator)) {
          if (locator.getType().equals(LocatorType.BETWEEN)) {
            // In case we are dealing with a 'between' locator we only need to add a NoFormat 'memento'.
            // In this way we do not format anything until the following element
            this.noFormatLocators.add(noFormatMemento);
          } else {
            this.noFormatLocators.add((NoFormatLocator) locator);
          }
          if (this.noFormatLocators.size() == 1 && !this.explicitFormattingOff) {
            this.storedPreserveSpacesValue = this.preserveSpaces;
            this.preserveSpaces = true;
          }
        } else if (this.noFormatLocators.isEmpty() && !this.explicitFormattingOff) {
          // In case the last NoFormat locator was dealt with, we add add a NoFormat 'memento'.
          // So that we effectively don't format anything until the following element
          this.noFormatLocators.add(noFormatMemento);
        }
      }
    }
  }

  /**
   * Checks whether formatting is disabled with the use of the 'no_format' construct or with the use of annotation in the source being processed.
   *
   * @return {@code true} if formatting is disabled with the 'no_format' construct or with the source annotation or both, otherwise {@code false}
   */
  public boolean isFormattingDisabled() {
    return !this.noFormatLocators.isEmpty() || this.explicitFormattingOff;
  }

  @Override
  public LineEntry createLineEntry(final EObject grammarElement, final String value, final boolean isHidden, final Set<ElementLocator> beforeLocators, final String leadingWS, final int indent, final ParserRule hiddenTokenDefinition) {
    String newValue = value;
    String newLeadingWS = leadingWS;

    Set<ElementLocator> activeLocators;
    if (isFormattingDisabled() || formatter.isUnformattedContent(value)) {
      activeLocators = Collections.emptySet();
    } else {
      activeLocators = beforeLocators;
    }

    ExtendedLineEntry lineEntry = new ExtendedLineEntry(this, grammarElement, newValue, isHidden, activeLocators, newLeadingWS, indent, hiddenTokenDefinition);
    lineEntry.setColumnIndent(emptySafeStackPeek(columnIndents), emptySafeStackPeek(initialIndents));
    lineEntry.setInitialIndent(emptySafeStackPeek(initialIndents));

    return lineEntry;
  }

  /**
   * Peeking an element from a stack of integers. Returns 0 if stack is empty.
   *
   * @param stack
   *          stack of integers
   * @return
   *         top element or 0 if stack empty
   */
  private int emptySafeStackPeek(final Stack<Integer> stack) {
    if (!stack.empty()) {
      return stack.peek();
    }
    return 0;
  }

  /**
   * Popping an element from a stack of integers. Nothing happens if the stack is empty..
   *
   * @param stack
   *          stack of integers
   */
  private void emptySafeStackPop(final Stack<Integer> stack) {
    if (!stack.empty()) {
      stack.pop();
    }
  }

  @Override
  public Line createLine() {
    return new ExtendedLine(this);
  }

  @Override
  protected String getLineSeparator() {
    // TODO cleanup. this is required for the Xtext 2.3.1 migration due to commit
    // http://git.eclipse.org/c/tmf/org.eclipse.xtext.git/commit/?id=689e8b08fd3af5cd0dc7df48294cdb1f9b6cc4a8
    return NEW_LINE;
  }

  /**
   * This method throws an {@link UnsupportedOperationException}. {@inheritDoc}
   */
  @Override
  protected Line createLine(final int leftover) {
    throw new UnsupportedOperationException();
  }

  /**
   * This method throws an {@link UnsupportedOperationException}. {@inheritDoc}
   */
  @Override
  protected Line createLine(final List<LineEntry> entries) {
    throw new UnsupportedOperationException();
  }

  /**
   * This method throws an {@link UnsupportedOperationException}. {@inheritDoc}
   */
  @Override
  protected Line createLine(final List<LineEntry> initialEntries, final int leftover) {
    throw new UnsupportedOperationException();
  }

  @Override
  @SuppressWarnings("PMD.NPathComplexity")
  protected Set<ElementLocator> collectLocators(final EObject grammarElement) {
    List<ElementLocator> result = Lists.newArrayList(activeRangeLocators);
    Collection<ElementLocator> locators = Sets.newHashSet();
    Set<ElementLocator> noFormatBaseLocators = Sets.newHashSet();
    List<ElementLocator> conditionalLocators = Lists.newArrayList();
    List<ElementLocator> conditionalNoFormatLocators = Lists.newArrayList();
    List<ElementLocator> parametrizedLocators = Lists.newArrayList();

    if (grammarElement instanceof AbstractElement) {
      for (ElementPattern pattern : matcher.matchNext((AbstractElement) grammarElement)) {
        ElementLocator eloc = pattern.getLocator();

        if (eloc instanceof IParametrizedLocator) {
          parametrizedLocators.add(eloc);
        } else if (eloc instanceof IConditionalLocator) {
          if (eloc instanceof NoFormatLocator) {
            conditionalNoFormatLocators.add(eloc);
          } else {
            conditionalLocators.add(eloc);
          }
        } else if (eloc instanceof NoFormatLocator) {
          noFormatBaseLocators.add(eloc);
        } else {
          locators.add(eloc);
        }
      }
    }

    handleConditionalLocators(conditionalNoFormatLocators);
    noFormatBaseLocators.addAll(conditionalNoFormatLocators);
    handleNoFormatLocators(noFormatBaseLocators);

    if ((last instanceof AbstractRule && hiddenTokenHelper.isComment((AbstractRule) last))
        || (grammarElement instanceof AbstractRule && hiddenTokenHelper.isComment((AbstractRule) grammarElement))) {
      processColumnLocatorsBeforeComments();
      if (NodeModelUtils.findActualSemanticObjectFor(currentNode) != null) {
        locators.addAll(collectLocatorsForComments(locators, last, grammarElement));
      } else {
        locators = collectLocatorsForComments(locators, last, grammarElement);
      }
    }

    last = grammarElement;
    for (ElementLocator locator : locators) {
      if (locator.getType() == LocatorType.RANGE && !activeRangeLocators.add(locator)) {
        activeRangeLocators.remove(locator);
      }
    }
    if (!isFormattingDisabled()) {
      result.addAll(conditionalLocators);
      result.addAll(handleParametrizedLocators(parametrizedLocators));
    }
    result.addAll(locators);

    if (!isFormattingDisabled()) {
      handleConditionalLocators(result);
    }

    for (ElementLocator locator : result) {
      if (locator instanceof IndentationLocatorStartFacade) {
        indentationLevel = indentationLevel + ((IndentationLocatorStartFacade) locator).getIndentation();
      } else if (locator instanceof IndentationLocatorEndFacade) {
        indentationLevel = indentationLevel - ((IndentationLocatorEndFacade) locator).getIndentation();
      }
    }

    sortLocators(result);
    processColumnLocators(result);

    return Sets.newHashSet(result);
  }

  /**
   * Iterates over a given list of collected parameterized locators and calculates the actual value of their parameters.
   *
   * @param locators
   *          list of locators
   * @return The list of the parameterized locators with their newly calculated parameters.
   */
  private List<ElementLocator> handleParametrizedLocators(final List<ElementLocator> locators) {
    List<ElementLocator> parameterizedLocators = Lists.newArrayList();
    for (ElementLocator locator : Iterables.filter(locators, Predicates.instanceOf(IParametrizedLocator.class))) {
      if (locator instanceof IConditionalLocator && !isActive((IConditionalLocator) locator)) {
        continue;
      }
      final LocatorParameterCalculator<?> parameterCalculator = ((IParametrizedLocator) locator).getLocatorParameterCalculator();

      Type genericInterfaceType = parameterCalculator.getClass().getGenericInterfaces()[0];
      Type genericType = ((ParameterizedType) genericInterfaceType).getActualTypeArguments()[0];
      EObject semanticNode = findSemanticNode(currentNode, genericType);

      if (semanticNode != null) {
        Class<?> semanticNodeType = getSemanticNodeType(semanticNode);
        try {
          Method calculateParameterMethod = parameterCalculator.getClass().getDeclaredMethod("calculateParameter", semanticNodeType, Integer.class); //$NON-NLS-1$
          if (columnMap.get(semanticNode) == null) {
            columnMap.put(semanticNode, currentColumn);
          }
          Integer parameter = (Integer) calculateParameterMethod.invoke(parameterCalculator, semanticNode, columnMap.get(semanticNode));
          if (locator instanceof ColumnLocator) {
            ((ColumnLocator) locator).setColumn(parameter.intValue());
            parameterizedLocators.add(locator);
          } else if (locator instanceof FixedLocator) {
            ((FixedLocator) locator).setColumn(parameter.intValue());
            parameterizedLocators.add(locator);
          } else if (locator instanceof IIndentationLocator) {
            ((IIndentationLocator) locator).setIndentation(parameter);
            parameterizedLocators.add(locator);
          }
          // CHECKSTYLE:OFF if the locator activator fails, simply disable the locator instead of completely failing to format a source
        } catch (Exception e) {
          // CHECKSTYLE:ON
          LOGGER.error(NLS.bind("Failed to calculate the parameter for the paramterized locator for {0}", semanticNodeType), e); //$NON-NLS-1$
        }
      }
    }
    return parameterizedLocators;
  }

  /**
   * Finds the semanticNode {@link EObject} that is associated to the container node of the given {@link INode}.
   *
   * @param givenNode
   *          the {@link INode} to find the semantic node for
   * @param expectedType
   *          The type of the semantic node to find
   * @return the semantic node found, {@code null} if none was found.
   */
  private EObject findSemanticNode(final INode givenNode, final Type expectedType) {
    INode node = givenNode;
    // Node type
    EObject semanticNode = NodeModelUtils.findActualSemanticObjectFor(node);
    if (semanticNode == null) {
      return null;
    }
    Class<?> semanticNodeType = getSemanticNodeType(semanticNode);

    // Find correct node
    while (!expectedType.equals(semanticNodeType)) {
      if (semanticNode == null) {
        break;
      }
      if (semanticNode.eContainer() == null) {
        while (semanticNode != null && semanticNode.eContainer() == null) {
          node = findPrevious(node);
          semanticNode = NodeModelUtils.findActualSemanticObjectFor(node);
          semanticNodeType = getSemanticNodeType(semanticNode);
        }

      } else {
        semanticNode = semanticNode.eContainer();
        semanticNodeType = getSemanticNodeType(semanticNode);
      }
    }
    return semanticNode;
  }

  private boolean isActive(final IConditionalLocator locator) {
    final LocatorActivator<?> locatorActivator = locator.getLocatorActivator();

    Type genericInterfaceType = locatorActivator.getClass().getGenericInterfaces()[0];
    Type genericType = ((ParameterizedType) genericInterfaceType).getActualTypeArguments()[0];
    EObject semanticNode = findSemanticNode(currentNode, genericType);

    // Check if locator is activated
    boolean isActive = false; // If we fail to execute the locator activator, leave the locator disabled

    if (semanticNode != null) {
      Class<?> semanticNodeType = getSemanticNodeType(semanticNode);
      try {
        Method activateMethod = locatorActivator.getClass().getDeclaredMethod("activate", semanticNodeType, Integer.class); //$NON-NLS-1$
        if (columnMap.get(semanticNode) == null) {
          columnMap.put(semanticNode, currentColumn);
        }
        isActive = (Boolean) activateMethod.invoke(locatorActivator, semanticNode, columnMap.get(semanticNode));
        // CHECKSTYLE:OFF if the locator activator fails, simply disable the locator instead of completely failing to format a source
      } catch (Exception e) {
        // CHECKSTYLE:ON
        LOGGER.error(NLS.bind("Failed to execute the locator activator for {0}", semanticNodeType), e); //$NON-NLS-1$
      }
    }
    return isActive;
  }

  /**
   * Iterates over list of collected locators and activates/deactivates conditional locators.
   *
   * @param result
   *          list of locators
   */
  public void handleConditionalLocators(final List<ElementLocator> result) {
    List<ElementLocator> inactiveConditionalLocators = Lists.newArrayList();
    for (ElementLocator locator : Iterables.filter(result, Predicates.instanceOf(IConditionalLocator.class))) {
      if (!isActive((IConditionalLocator) locator)) {
        inactiveConditionalLocators.add(locator);
      }
    }
    result.removeAll(inactiveConditionalLocators);
  }

  /**
   * Returns interface type of semantic model object.
   *
   * @param semanticNode
   *          eObject
   * @return type of semantic model object
   */
  public Class<?> getSemanticNodeType(final EObject semanticNode) {
    if (semanticNode == null) {
      return null;
    }
    Class<?>[] interfaces = semanticNode.getClass().getInterfaces();
    if (interfaces.length <= 0) {
      // In case of *ImplCustom object we need to get their superclass's interface
      interfaces = semanticNode.getClass().getSuperclass().getInterfaces();
    }

    return interfaces.length > 0 ? interfaces[0] : null;
  }

  /**
   * Returns predecessor of the given node in the parse tree.
   * Copied method from: org.eclipse.xtext.parsetree.reconstr.impl.NodeIterator
   *
   * @param node
   *          for which predecessor should be found
   * @return predecessor
   */
  private INode findPrevious(final INode node) {
    ICompositeNode parent = node.getParent();
    if (parent == null) {
      return null;
    }
    INode predecessor = node.getPreviousSibling();
    if (predecessor != null) {
      while (predecessor instanceof ICompositeNode) {
        INode lastChild = ((ICompositeNode) predecessor).getLastChild();
        if (lastChild == null) {
          return predecessor;
        }
        predecessor = lastChild;
      }
      return predecessor;
    }
    return parent;
  }

  /**
   * Checks whether in a given collection exists a complementary locator to the given locator. I.e. if given is column opening then it checks if collections
   * contains closing locator for the same column indentation and vice versa.
   *
   * @param locators
   *          list of locators
   * @param locator
   *          given locator
   * @return
   *         true if given collection of locators contains complementary locator to the given one
   */
  public boolean containsOpposite(final List<ElementLocator> locators, final ElementLocator locator) {
    for (ElementLocator candidateLocator : locators) {
      boolean isSameIndentColumnLocator = candidateLocator instanceof FixedLocator
          && ((FixedLocator) candidateLocator).getColumn() == ((FixedLocator) locator).getColumn();
      if (isSameIndentColumnLocator) {
        boolean isOppositeToAfter = ((FixedLocator) candidateLocator).getRight() != null && ((FixedLocator) locator).getRight() == null;
        boolean isOppositeToBefore = ((FixedLocator) candidateLocator).getLeft() != null && ((FixedLocator) locator).getLeft() == null;
        if (isOppositeToAfter || isOppositeToBefore) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Processes end of column locators occuring just before comment sections.
   */
  private void processColumnLocatorsBeforeComments() {
    // workaround: xtext implementation assumes that when the comment occurs other locators (just before the comment) are not caught
    if (last instanceof AbstractElement) {
      emptySafeStackPop(columnIndents);
      emptySafeStackPop(initialIndents);
    }
  }

  /**
   * Sorts list of locators as the order of processed locators is important. First come the column opening locators and then column closing locators. Within
   * each group locators are sorted according to the column indentation value in increasing order.
   *
   * @param locators
   *          list of locators to be processed
   */
  private void sortLocators(final List<ElementLocator> locators) {
    Collections.sort(locators, new Comparator<ElementLocator>() {
      @Override
      public int compare(final ElementLocator o1, final ElementLocator o2) {
        if (o1 instanceof FixedLocator && o2 instanceof FixedLocator) {
          if (o1.getLeft() != null && o2.getLeft() == null) {
            return 1;
          } else if (o1.getLeft() == null && o2.getLeft() != null) {
            return -1;
          } else {
            return Integer.valueOf(((FixedLocator) o1).getColumn()).compareTo(((FixedLocator) o2).getColumn());
          }
        } else {
          return 0;
        }
      }
    });
  }

  /**
   * Browses list of locators, if faces {@link FixedLocator} then update column indentation and initial indentation stacks.
   *
   * @param locators
   *          list of locators to be processed
   */
  private void processColumnLocators(final List<ElementLocator> locators) {
    Set<Integer> duplicatedColumnLocatorsIndicator = new HashSet<Integer>();
    for (ElementLocator locator : locators) {
      if (locator instanceof FixedLocator) {
        if (locator.getRight() != null && !duplicatedColumnLocatorsIndicator.contains(((FixedLocator) locator).getColumn())) {
          initialIndents.push(Integer.valueOf(indentationLevel));
          columnIndents.push(((FixedLocator) locator).getColumn());
          duplicatedColumnLocatorsIndicator.add(((FixedLocator) locator).getColumn());
        }
        if (locator.getLeft() != null) {
          int locatorIndexOnStack = columnIndents.lastIndexOf(((FixedLocator) locator).getColumn());
          if (locatorIndexOnStack != -1) {
            columnIndents.remove(locatorIndexOnStack);
            initialIndents.remove(locatorIndexOnStack);
          } else {
            emptySafeStackPop(columnIndents);
            emptySafeStackPop(initialIndents);
          }
        }
      }
    }
  }

  /**
   * This class extends LineEntry in order to make some of its methods public and allow its extension by ExtendedLineEntry.
   */
  public abstract class AbstractExtendedLineEntry extends LineEntry {
    public AbstractExtendedLineEntry(final EObject grammarElement, final String value, final boolean isHidden, final Set<ElementLocator> beforeLocators, final String leadingWS, final int indent, final ParserRule hiddenTokenDefinition) {
      super(grammarElement, value, isHidden, beforeLocators, leadingWS, indent, hiddenTokenDefinition);
    }

    @Override
    public boolean isBreakable() { // NOPMD - super.isBreakable() is protected, need to make it public
      return super.isBreakable();
    }

    @Override
    public int countCharactersInLastLine() { // NOPMD UselessOverridingMethod: called by WrapEntry
      return super.countCharactersInLastLine();
    }
  }

  /**
   * This class extends Line in order to support handling of ExtendedLineEntry elements.
   */
  public abstract class AbstractExtendedLine extends Line {

    @Override
    public final Line add(final LineEntry lineEntry) throws IOException {
      return addEntry((ExtendedLineEntry) lineEntry);
    }

    /**
     * Add an {@link ExtendedLineEntry} to this line.
     * The line will be flushed when full or when a line entry has a line wrap
     * locator. In that case a new line will be created and returned.
     *
     * @param lineEntry
     *          - the entry to be added to this line
     * @return {@link ExtendedLine} - the current line
     * @throws IOException
     *           - the exception propagated from the underlying {@link ITokenStream}
     */
    public abstract ExtendedLine addEntry(ExtendedLineEntry lineEntry) throws IOException;

    public boolean isPreserveSpaces() {
      return preserveSpaces;
    }

    public ITokenStream getOutStream() {
      return out;
    }

    public IHiddenTokenHelper getHiddenTokenHelper() {
      return hiddenTokenHelper;
    }

  }

  public FormattingConfig getFormattingConfig() {
    return cfg;
  }

  public int getCharsPerLine() {
    return cfg.getCharsPerLine();
  }

  /**
   * Sets the currently processed node.
   * Adjusts the state of the stream if formatting directive(s) encountered.
   *
   * @param node
   *          current node
   */
  public void setNode(final INode node) {
    this.currentNode = node;
    if (isCommentNode(node)) {
      String commentText = node.getText();
      if (!Strings.isNullOrEmpty(commentText)) {
        formatter.executeFormattingDirectives(commentText, this);
      }
    }
  }

  /**
   * Increments the amount of directives that disable formatting that are currently active.
   * If it is the first one formatting will be be switched off.
   */
  public void incrementFormatDisablingDirectiveCount() {
    if (this.formatDisablingDirectives == 0) {
      setFormattingActive(false);
    }
    this.formatDisablingDirectives++;
  }

  /**
   * Decrements the amount of directives that disable formatting that are currently active.
   * If no more directives are active formatting will be be switched on.
   */
  public void decrementFormatDisablingDirectiveCount() {
    this.formatDisablingDirectives--;
    if (this.formatDisablingDirectives == 0) {
      setFormattingActive(true);
    }
  }

  /**
   * Helper method that allows to switch on\off formatting of the stream.
   *
   * @param formattingActive
   *          true to switch on, false off
   */
  public void setFormattingActive(final boolean formattingActive) {
    if (!formattingActive) {
      this.explicitFormattingOff = true;
      if (noFormatLocators.isEmpty()) {
        this.storedPreserveSpacesValue = this.preserveSpaces;
        this.preserveSpaces = true;
      }
    } else {
      this.explicitFormattingOff = false;
      if (noFormatLocators.isEmpty()) {
        this.noFormatLocators.add(noFormatMemento);
      }
    }
  }

  /**
   * Checks if the given node represents a comment.
   *
   * @param node
   *          leaf node, possibly {@code null}
   * @return true if this is a comment, false otherwise
   */
  private boolean isCommentNode(final INode node) {
    if (node instanceof ILeafNode && node.getGrammarElement() != null) {
      EObject grammarElement = node.getGrammarElement();
      if (grammarElement instanceof TerminalRule) {
        return hiddenTokenHelper.isComment((TerminalRule) grammarElement);
      }
    }
    return false;
  }

}
