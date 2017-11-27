/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.common.ui.contentassist;

import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.templates.TemplateProposal;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalComparator;


/**
 * Comparator for Contentassist ordering.
 * If templates exists always display them first in alphabetical order.
 */
public class TemplatesFirstCompletionProposalComparator implements ICompletionProposalComparator {

  /** {@inheritDoc} */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public int compare(final ICompletionProposal o1, final ICompletionProposal o2) {

    // Template sorting by relevance and then alphabetically by name
    if (o1 instanceof TemplateProposal && o2 instanceof TemplateProposal) {
      TemplateProposal t1 = (TemplateProposal) o1;
      TemplateProposal t2 = (TemplateProposal) o2;
      if (t1.getRelevance() == t2.getRelevance()) {
        return o1.getDisplayString().compareTo(o2.getDisplayString());
      }
      return ((Integer) t1.getRelevance()).compareTo(t1.getRelevance());
    }
    // Templates always first
    if (o1 instanceof TemplateProposal) {
      return -1;
    } else if (o2 instanceof TemplateProposal) {
      return 1;
    }
    // Fallback
    if ((o1 instanceof Comparable) && (o2 instanceof Comparable)) {
      return ((Comparable) o1).compareTo(o2);
    }
    return o1.getDisplayString().compareTo(o2.getDisplayString());
  }
}

