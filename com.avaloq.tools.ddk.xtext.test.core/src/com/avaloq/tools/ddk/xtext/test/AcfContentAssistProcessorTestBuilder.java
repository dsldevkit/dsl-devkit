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
package com.avaloq.tools.ddk.xtext.test;

// CHECKSTYLE:OFF
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateProposal;
import org.eclipse.xtext.ui.testing.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.ui.testing.util.ResourceLoadHelper;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.util.Tuples;
import org.junit.jupiter.api.Assertions;

import com.google.inject.Injector;


/**
 * This class provides the following extensions to {@link ContentAssistProcessorTestBuilder}:
 * <ul>
 * <li>Return type is {@link AcfContentAssistProcessorTestBuilder} where appropriate, not ContentAssistProcessorTestBuilder
 * <li>{@link #applyText(String, boolean)} also handles {@link XtextTemplateProposal} instances which occur in case of custom content assist templates, not only
 * {@link ConfigurableCompletionProposal}s.
 * </ul>
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "nls"})
public class AcfContentAssistProcessorTestBuilder extends ContentAssistProcessorTestBuilder {
  private final ResourceLoadHelper loadHelper;

  /**
   * Creates a new instance of {@link AcfContentAssistProcessorTestBuilder}.
   *
   * @param injector
   *          the injector to use
   * @param helper
   *          the test utility helper to load resources
   * @throws Exception
   *           in case the injector was not setup beforehand
   */
  public AcfContentAssistProcessorTestBuilder(final Injector injector, final ResourceLoadHelper helper) throws Exception {
    super(injector, helper);
    this.loadHelper = helper;
  }

  @Override
  public AcfContentAssistProcessorTestBuilder reset() throws Exception {
    return (AcfContentAssistProcessorTestBuilder) super.reset();
  }

  @Override
  public AcfContentAssistProcessorTestBuilder append(final String model) throws Exception {
    return (AcfContentAssistProcessorTestBuilder) super.append(model);
  }

  @Override
  public AcfContentAssistProcessorTestBuilder appendNl(final String model) throws Exception {
    return (AcfContentAssistProcessorTestBuilder) super.appendNl(model);
  }

  @Override
  public AcfContentAssistProcessorTestBuilder insert(final String model, final int cursorPosition) throws Exception {
    return (AcfContentAssistProcessorTestBuilder) super.insert(model, cursorPosition);
  }

  @Override
  public AcfContentAssistProcessorTestBuilder cursorBack(final int times) throws Exception {
    return (AcfContentAssistProcessorTestBuilder) super.cursorBack(times);
  }

  /**
   * Applies a content assist proposal using the expected display string.
   *
   * @param expectedDisplayString
   *          the content assist proposal to apply
   * @param appendSpace
   *          whether to append a space
   * @return a new {@link AcfContentAssistProcessorTestBuilder} with the text applied.
   * @throws Exception
   *           if there was a problem loading the xtext resource
   */
  public AcfContentAssistProcessorTestBuilder applyText(final String expectedDisplayString, final boolean appendSpace) throws Exception {
    ICompletionProposal proposal = null;
    for (final ICompletionProposal p : computeCompletionProposals(getModel(), getCursorPosition())) {
      if (expectedDisplayString.equals(p.getDisplayString())) {
        proposal = p;
        break;
      }
    }
    assertNotNull(proposal, MessageFormat.format("\"{0}\" not a valid completion proposal", expectedDisplayString));
    String text = "";
    if (proposal instanceof ConfigurableCompletionProposal) {
      text = ((ConfigurableCompletionProposal) proposal).getReplacementString();
    } else if (proposal instanceof XtextTemplateProposal) {
      // These may occur in the context of custom content assist templates
      text = ((XtextTemplateProposal) proposal).getAdditionalProposalInfo();
    }
    AcfContentAssistProcessorTestBuilder ret = append(text);
    if (appendSpace) {
      return ret.append(" ");
    }
    return ret;
  }

  /**
   * {@inheritDoc} Code copied from parent. Override required to run in UI because of getSourceViewer, which creates a new Shell.
   */
  @Override
  public ICompletionProposal[] computeCompletionProposals(final String currentModelToParse, final int cursorPosition) throws Exception {
    AtomicReference<Pair<ICompletionProposal[], BadLocationException>> result = new AtomicReference<>();

    Display.getDefault().syncExec(() -> {
      final XtextResource xtextResource = loadHelper.getResourceFor(new StringInputStream(currentModelToParse));
      final IXtextDocument xtextDocument = getDocument(xtextResource, currentModelToParse);
      result.set(internalComputeCompletionProposals(cursorPosition, xtextDocument));
    });
    if (result.get().getSecond() != null) {
      throw result.get().getSecond();
    }
    return result.get().getFirst();
  }

  /**
   * {@inheritDoc} Code copied from parent. Override required to run in UI because of getSourceViewer, which creates a new Shell.
   */
  public ICompletionProposal[] computeCompletionProposals(final XtextTestSource testSource, final int cursorPosition) {
    AtomicReference<Pair<ICompletionProposal[], BadLocationException>> result = new AtomicReference<>();
    Display.getDefault().syncExec(() -> {
      final IXtextDocument xtextDocument = getDocument(testSource.getXtextResource(), testSource.getContent());
      result.set(internalComputeCompletionProposals(cursorPosition, xtextDocument));
    });
    if (result.get().getSecond() != null) {
      throw new WrappedException("Error computing completion proposals.", result.get().getSecond());
    }
    return result.get().getFirst();
  }

  /**
   * {@inheritDoc} Code copied from parent. Override required to run in UI because of getSourceViewer, which creates a new Shell.
   */
  @Override
  public ContentAssistProcessorTestBuilder assertMatchString(final String matchString) throws Exception {
    AtomicReference<BadLocationException> exception = new AtomicReference<>();

    Display.getDefault().syncExec(() -> {
      String currentModelToParse = getModel();
      final XtextResource xtextResource = loadHelper.getResourceFor(new StringInputStream(currentModelToParse));
      final IXtextDocument xtextDocument = getDocument(xtextResource, currentModelToParse);
      XtextSourceViewerConfiguration configuration = get(XtextSourceViewerConfiguration.class);
      Shell shell = new Shell();
      try {
        ISourceViewer sourceViewer = getSourceViewer(shell, xtextDocument, configuration);
        IContentAssistant contentAssistant = configuration.getContentAssistant(sourceViewer);
        String contentType = xtextDocument.getContentType(currentModelToParse.length());
        if (contentAssistant.getContentAssistProcessor(contentType) != null) {
          ContentAssistContext.Factory factory = get(ContentAssistContext.Factory.class);
          ContentAssistContext[] contexts = factory.create(sourceViewer, currentModelToParse.length(), xtextResource);
          for (ContentAssistContext context : contexts) {
            Assertions.assertTrue("".equals(context.getPrefix())
                || matchString.equals(context.getPrefix()), "matchString = '" + matchString + "', actual: '" + context.getPrefix() + "'");
          }
        } else {
          Assertions.fail("No content assistant for content type " + contentType);
        }
      } catch (BadLocationException e) {
        exception.set(e);
      } finally {
        shell.dispose();
      }
    });
    if (exception.get() != null) {
      throw exception.get();
    }
    return this;
  }

  /**
   * Internally compute completion proposals.
   *
   * @param cursorPosition
   *          the position of the cursor in the {@link IXtextDocument}
   * @param xtextDocument
   *          the {@link IXtextDocument}
   * @return a pair of {@link ICompletionProposal}[] and {@link BadLocationException}. If the tail argument is not {@code null}, an exception occurred in the UI
   *         thread.
   */
  private Pair<ICompletionProposal[], BadLocationException> internalComputeCompletionProposals(final int cursorPosition, final IXtextDocument xtextDocument) {
    XtextSourceViewerConfiguration configuration = get(XtextSourceViewerConfiguration.class);
    Shell shell = new Shell();
    try {
      ISourceViewer sourceViewer = getSourceViewer(shell, xtextDocument, configuration);
      IContentAssistant contentAssistant = configuration.getContentAssistant(sourceViewer);
      String contentType = xtextDocument.getContentType(cursorPosition);
      IContentAssistProcessor processor = contentAssistant.getContentAssistProcessor(contentType);
      if (processor != null) {
        return Tuples.create(processor.computeCompletionProposals(sourceViewer, cursorPosition), null);
      }
      return Tuples.create(new ICompletionProposal[0], null);
    } catch (BadLocationException e) {
      return Tuples.create(new ICompletionProposal[0], e);
    } finally {
      shell.dispose();
    }
  }
}
// CHECKSTYLE:ON
