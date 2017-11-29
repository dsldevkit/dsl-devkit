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
package com.avaloq.tools.ddk.check.ui.test.contentassist;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Tests content assist in Check models.
 */
@SuppressWarnings({"PMD.SignatureDeclareThrowsException", "PMD.AvoidFinalLocalVariable"})
public class BugAig931Test extends AbstractCheckContentAssistBugTest implements IJavaProjectProvider {

  /**
   * Verifies that given completions exist.
   * 
   * @param completionProposals
   *          the actual completion proposals
   * @param expected
   *          the expected display strings
   */
  private void completionsExist(final ICompletionProposal[] completionProposals, final String... expected) {
    List<String> actual = Lists.newArrayList(Iterables.transform(Lists.newArrayList(completionProposals), //
        new Function<ICompletionProposal, String>() {
          @Override
          public String apply(final ICompletionProposal input) {
            return input.getDisplayString();
          }
        }));
    for (String string : expected) {
      Assert.assertTrue(NLS.bind("Expected {0} but found {1}", Arrays.toString(expected), actual), actual.contains(string));
    }
  }

  /**
   * Tests bug https://jira.int.sys.net/browse/AIG-931.
   * 
   * @throws Exception
   *           the exception
   */
  @Test
  public void testBugAig931() throws Exception {
    final String partialModel = "package p catalog T for grammar com.avaloq.tools.ddk.check.Check { error \"X\" for ";
    final String[] expectedContextTypeProposals = {"EObject - org.eclipse.emf.ecore", "JvmType - org.eclipse.xtext.common.types"};
    new UIJob("compute completion proposals") {
      @SuppressWarnings("restriction")
      @Override
      public IStatus runInUIThread(final IProgressMonitor monitor) {
        try {
          completionsExist(newBuilder().append(partialModel).computeCompletionProposals(), expectedContextTypeProposals);
          // CHECKSTYLE:OFF
        } catch (Exception e) {
          // CHECKSTYLE:ON
          return new Status(Status.ERROR, "com.avaloq.tools.ddk.check.ui.test", 1, e.getMessage(), e);
        }
        return Status.OK_STATUS;
      }
    };
  }
}

