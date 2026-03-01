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

package com.avaloq.tools.ddk.xtext.check.generator.quickfix;

import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

import com.google.inject.Inject;


@SuppressWarnings("nls")
public class CheckQuickfixProviderFragment2 extends AbstractXtextGeneratorFragment {

  private static final String UI_PLUGIN = "com.avaloq.tools.ddk.check.runtime.ui";

  @Inject
  private XtextGeneratorNaming xtextGeneratorNaming;

  @Inject
  private FileAccessFactory fileAccessFactory;

  protected TypeReference getQuickfixProviderClass(final Grammar g) {
    return new TypeReference(
        xtextGeneratorNaming.getEclipsePluginBasePackage(g) + ".quickfix." + GrammarUtil.getSimpleName(g) + "QuickfixProvider");
  }

  @Override
  public void generate() {
    if (getProjectConfig().getEclipsePlugin().getManifest() != null) {
      getProjectConfig().getEclipsePlugin().getManifest().getRequiredBundles().add(UI_PLUGIN);
    }

    if (getProjectConfig().getEclipsePlugin().getSrc() != null) {
      generateQuickfixProvider();
    }

    if (getProjectConfig().getEclipsePlugin().getPluginXml() != null) {
      addRegistrationToPluginXml();
    }
  }

  protected void generateQuickfixProvider() {
    final StringConcatenationClient content = new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation builder) {
        builder.append("""
            public class %s extends DefaultCheckQuickfixProvider {

            //  @Fix(MyJavaValidator.INVALID_NAME)
            //  public void capitalizeName(final Issue issue, IssueResolutionAcceptor acceptor) {
            //    acceptor.accept(issue, "Capitalize name", "Capitalize the name.", "upcase.png", new IModification() {
            //      public void apply(IModificationContext context) throws BadLocationException {
            //        IXtextDocument xtextDocument = context.getXtextDocument();
            //        String firstLetter = xtextDocument.get(issue.getOffset(), 1);
            //        xtextDocument.replace(issue.getOffset(), 1, firstLetter.toUpperCase());
            //      }
            //    });
            //  }

            }
            """.formatted(getQuickfixProviderClass(getGrammar()).getSimpleName()));
      }
    };
    // CHECKSTYLE:CONSTANTS-ON
    fileAccessFactory.createJavaFile(getQuickfixProviderClass(getGrammar()), content).writeTo(getProjectConfig().getEclipsePlugin().getSrc());
  }

  protected void addRegistrationToPluginXml() {
    final TypeReference executableExtensionFactory = xtextGeneratorNaming.getEclipsePluginExecutableExtensionFactory(getGrammar());
    getProjectConfig().getEclipsePlugin().getPluginXml().getEntries().add("""
        <!-- quickfix marker resolution generator -->
        <extension
              point="org.eclipse.ui.ide.markerResolution">
           <markerResolutionGenerator
                 class="%s:org.eclipse.xtext.ui.editor.quickfix.MarkerResolutionGenerator">
           </markerResolutionGenerator>
        </extension>
        """.formatted(executableExtensionFactory));
  }
}
