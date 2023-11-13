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
package com.avaloq.tools.ddk.check.ui.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.pde.internal.core.text.IDocumentElementNode;
import org.eclipse.pde.internal.core.util.CoreUtility;
import org.eclipse.pde.internal.ua.core.toc.text.Toc;
import org.eclipse.pde.internal.ua.core.toc.text.TocModel;
import org.eclipse.pde.internal.ua.core.toc.text.TocTopic;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;

import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckProjectHelper;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckTocExtensionHelper;
import com.google.inject.Inject;


/**
 * The class responsible for the manipulation of toc.xml file.
 */
@SuppressWarnings("restriction")
public class CheckTocGenerator {

  @Inject
  private CheckProjectHelper projectHelper;

  @Inject
  private IStorage2UriMapper mapper;

  @Inject
  private CheckGeneratorNaming generatorNaming;

  private static final String TOC_LABEL = "Check Catalogs";
  private static final String ANCHOR_ID = "../com.avaloq.tools.ddk.check.runtime.ui/toc.xml#checkdocumentation";
  private static final String SORT_KEY = "sort";

  /**
   * Removes the topic from toc file.
   * 
   * @param uri
   *          the uri
   * @throws CoreException
   *           a core exception if model could not be loaded.
   */
  public void removeTopicFromTocFile(final URI uri) throws CoreException {
    String topicFieldReference = getFieldReference(uri);
    if (RuntimeProjectUtil.getProject(uri, mapper) != null) {
      TocModel model = loadTocModel(uri);
      removeTopicFromTocFile(model, topicFieldReference);
      model.save();
      model.dispose();
    }
  }

  /**
   * Removes the topic from toc file.
   * 
   * @param model
   *          the model containing the toc
   * @param reference
   *          the reference for the field to remove
   */
  private void removeTopicFromTocFile(final TocModel model, final String reference) {

    IDocumentElementNode[] children = model.getToc().getChildNodes();
    for (IDocumentElementNode child : children) {
      if (child instanceof TocTopic) {
        TocTopic topic = (TocTopic) child;
        if (reference.equals(topic.getFieldRef())) { // NOPMD
          model.getToc().removeChildNode(child);
        }
      }
    }
  }

  /**
   * Returns the reference to the html-page for the given URI.
   * 
   * @param uri
   *          the uri
   * @return
   *         the reference to the html-page
   */
  private String getFieldReference(final URI uri) {
    return "docs/content/" + uri.trimFileExtension().lastSegment() + ".html";
  }

  /**
   * Change toc.xml file if necessary.
   * 
   * @param uri
   *          the uri
   * @param buildContext
   *          the context
   * @throws CoreException
   *           a core exception if model could not be loaded
   */
  public void updateTocModel(final URI uri, final IBuildContext buildContext) throws CoreException {
    TocModel model = loadTocModel(uri);
    if (model.getToc().getFieldLabel() == null) {
      updateToc(model);
    }

    String topicFieldLabel = uri.trimFileExtension().lastSegment();
    String topicFieldReference = getFieldReference(uri);

    removeTopicFromTocFile(model, topicFieldReference);
    addTopicToToc(model, topicFieldLabel, topicFieldReference, uri, buildContext);

    model.save();
    model.dispose();
  }

  /**
   * Adds the topic to toc model.
   * 
   * @param model
   *          the model
   * @param label
   *          topic label
   * @param reference
   *          topic reference
   * @param uri
   *          the uri
   * @param buildContext
   *          the context
   * @throws CoreException
   *           CoreException
   */
  private void addTopicToToc(final TocModel model, final String label, final String reference, final URI uri, final IBuildContext buildContext) throws CoreException {
    TocTopic topic = model.getFactory().createTocTopic();
    topic.setFieldLabel(label);
    topic.setFieldRef(reference);
    topic.setXMLAttribute(SORT_KEY, "false");

    // Add catalogs and checks as sub-topics
    TocTopic categoryTopic;
    TocTopic checkTopic;

    for (Category category : projectHelper.getCatalog(buildContext, uri).getCategories()) {
      categoryTopic = model.getFactory().createTocTopic();
      categoryTopic.setFieldLabel(category.getLabel());
      categoryTopic.setFieldRef(reference + '#' + generatorNaming.getContextId(category));

      for (Check check : category.getChecks()) {
        checkTopic = model.getFactory().createTocTopic();
        checkTopic.setFieldLabel(check.getLabel());
        checkTopic.setFieldRef(reference + '#' + generatorNaming.getContextId(check));
        categoryTopic.addChild(checkTopic);
      }
      topic.addChild(categoryTopic);
    }

    for (Check check : projectHelper.getCatalog(buildContext, uri).getChecks()) {
      checkTopic = model.getFactory().createTocTopic();
      checkTopic.setFieldLabel(check.getLabel());
      checkTopic.setFieldRef(reference + '#' + generatorNaming.getContextId(check));
      topic.addChild(checkTopic);
    }

    // Add TOC at the right position (to have them sorted alphabetically by label)
    IDocumentElementNode[] children = model.getToc().getChildNodes();
    String childLabel;
    boolean added = false;
    for (IDocumentElementNode child : children) {
      if (child instanceof TocTopic) {
        childLabel = ((TocTopic) child).getFieldLabel();
        if (childLabel.compareTo(label) > 0) {
          // childLabel is alphabetically after the label of the new toc
          model.getToc().addChild(topic, (TocTopic) child, true);
          added = true;
          break;
        }
      }
    }

    if (!added) {
      model.getToc().addChild(topic);
    }

  }

  /**
   * Updates the toc model with initial toc field label and anchor point. Saves and returns the model.
   * 
   * @param model
   *          the model
   * @throws CoreException
   *           a core exception
   */
  private void updateToc(final TocModel model) throws CoreException {
    Toc toc = model.getToc();
    toc.setFieldLabel(TOC_LABEL);
    toc.setFieldAnchorTo(ANCHOR_ID);
    toc.setXMLAttribute(SORT_KEY, "true");
    model.save();
  }

  /**
   * Loads toc model and sets toc.xml as underlying resource.
   * 
   * @param uri
   *          the uri
   * @return the toc model
   * @throws CoreException
   *           the core exception if model could not be loaded
   */
  private TocModel loadTocModel(final URI uri) throws CoreException {
    IFile file = projectHelper.getHelpFile(uri, CheckTocExtensionHelper.TOC_FILE_NAME);
    TocModel model = new TocModel(CoreUtility.getTextDocument(file.getContents()), false);
    model.setUnderlyingResource(file);
    model.load(file.getContents(), false);
    return model;
  }

}
