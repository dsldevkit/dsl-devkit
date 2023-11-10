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
package com.avaloq.tools.ddk.xtext.ui.editor;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.DescriptionUtils;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescription.Event;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.DirtyStateEditorSupport;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;


/**
 * Fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=383919 (honor container visibility).
 * Can be removed once bug 383919 is fixed completely (proper delegation to resource description manager *and* proper testing for java resources in the
 * DefaultResourceDescriptionManager).
 */
public class FixedDirtyStateEditorSupport extends DirtyStateEditorSupport {

  @Inject
  private IResourceDescriptions resourceDescriptions;

  @Inject(optional = true)
  private DescriptionUtils descriptionUtils;

  
  @Override
  protected boolean isReparseRequired(final XtextResource resource, final Event event) { // NOPMD (NPath complexity)
    IResourceDescription.Manager resourceDescriptionManager = resource.getResourceServiceProvider().getResourceDescriptionManager();
    final IResourceDescription description = resourceDescriptionManager.getResourceDescription(resource);
    // The DefaultResourceDescriptionManager honors container visibility only for platform and archive URIs... oh well; let's do it explicitly here.
    final IContainer.Manager containerManager = resource.getResourceServiceProvider().getContainerManager();
    // Unfortunately, we need a Collection in the call to isAffected() below...
    List<Delta> visibleChanges = Lists.newArrayList(Iterables.filter(event.getDeltas(), new Predicate<Delta>() {
      private List<IContainer> containers;

      private Iterable<IContainer> getContainers() {
        // Getting the containers is a relatively expensive operation. We delay doing this until we really need it.
        if (containers == null) {
          containers = containerManager.getVisibleContainers(description, resourceDescriptions);
        }
        return containers;
      }

      @Override
      public boolean apply(final Delta delta) {
        if (delta.haveEObjectDescriptionsChanged()) {
          if (delta.getNew() == null) {
            // If it was deleted, we don't know whether it was visible. Hmphhh.
            return true;
          }
          if (delta.getUri().isFile()) {
            // Apparently these are java-resources, which are always visible?! (c.f. Xtext bug 383919.)
            return true;
          }
          for (IContainer container : getContainers()) {
            if (container.hasResourceDescription(delta.getUri())) {
              return true;
            }
          }
        }
        return false;
      }
    }));
    // Fix Xtext bug 383919: delegate the whole checking to the description manager, which may have optimized processing in place anyway.
    if (resourceDescriptionManager.isAffected(visibleChanges, description, resourceDescriptions)) {
      return true;
    }
    if (!isDirty() && !getDirtyStateManager().hasContent(resource.getURI())) {
      IResourceDescription originalDescription = resourceDescriptions.getResourceDescription(resource.getURI());
      if (originalDescription != null && descriptionUtils != null) {
        Set<URI> outgoingReferences = descriptionUtils.collectOutgoingReferences(originalDescription);
        for (Delta delta : visibleChanges) {
          if (outgoingReferences.contains(delta.getUri())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  protected void initDirtyResource(final IXtextDocument document) {
    // Called in the UI thread.
    resourceDescriptions.isEmpty(); // Make sure index is loaded, DB access set up.
    super.initDirtyResource(document);
  }
}
