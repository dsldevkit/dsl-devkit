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

import com.google.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory;
import org.eclipse.xtext.xtext.generator.model.TypeReference;

public class CheckQuickfixProviderFragment2 extends AbstractXtextGeneratorFragment {

  private static final String UI_PLUGIN = "com.avaloq.tools.ddk.check.runtime.ui";

  @Inject
  private XtextGeneratorNaming xtextGeneratorNaming;

  @Inject
  private FileAccessFactory fileAccessFactory;

  protected TypeReference getQuickfixProviderClass(final Grammar g) {
    return new TypeReference(
      xtextGeneratorNaming.getEclipsePluginBasePackage(g) + ".quickfix." + GrammarUtil.getSimpleName(g) + "QuickfixProvider"
    );
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
    // CHECKSTYLE:CONSTANTS-OFF
    StringConcatenationClient content = new StringConcatenationClient() {
      @Override
      protected void appendTo(final TargetStringConcatenation builder) {
        builder.append("public class ");
        builder.append(getQuickfixProviderClass(getGrammar()).getSimpleName());
        builder.append(" extends DefaultCheckQuickfixProvider {");
        builder.newLineIfNotEmpty();
        builder.newLine();
        builder.append("//  @Fix(MyJavaValidator.INVALID_NAME)");
        builder.newLine();
        builder.append("//  public void capitalizeName(final Issue issue, IssueResolutionAcceptor acceptor) {");
        builder.newLine();
        builder.append("//    acceptor.accept(issue, \"Capitalize name\", \"Capitalize the name.\", \"upcase.png\", new IModification() {");
        builder.newLine();
        builder.append("//      public void apply(IModificationContext context) throws BadLocationException {");
        builder.newLine();
        builder.append("//        IXtextDocument xtextDocument = context.getXtextDocument();");
        builder.newLine();
        builder.append("//        String firstLetter = xtextDocument.get(issue.getOffset(), 1);");
        builder.newLine();
        builder.append("//        xtextDocument.replace(issue.getOffset(), 1, firstLetter.toUpperCase());");
        builder.newLine();
        builder.append("//      }");
        builder.newLine();
        builder.append("//    });");
        builder.newLine();
        builder.append("//  }");
        builder.newLine();
        builder.newLine();
        builder.append("}");
        builder.newLine();
      }
    };
    // CHECKSTYLE:CONSTANTS-ON
    fileAccessFactory.createJavaFile(getQuickfixProviderClass(getGrammar()), content).writeTo(getProjectConfig().getEclipsePlugin().getSrc());
  }

  protected void addRegistrationToPluginXml() {
    final TypeReference executableExtensionFactory = xtextGeneratorNaming.getEclipsePluginExecutableExtensionFactory(getGrammar());
    // CHECKSTYLE:CONSTANTS-OFF
    StringConcatenation builder = new StringConcatenation();
    builder.append("<!-- quickfix marker resolution generator -->");
    builder.newLine();
    builder.append("<extension");
    builder.newLine();
    builder.append("      ");
    builder.append("point=\"org.eclipse.ui.ide.markerResolution\">");
    builder.newLine();
    builder.append("   ");
    builder.append("<markerResolutionGenerator");
    builder.newLine();
    builder.append("         ");
    builder.append("class=\"");
    builder.append(executableExtensionFactory, "         ");
    builder.append(":org.eclipse.xtext.ui.editor.quickfix.MarkerResolutionGenerator\">");
    builder.newLineIfNotEmpty();
    builder.append("   ");
    builder.append("</markerResolutionGenerator>");
    builder.newLine();
    builder.append("</extension>");
    builder.newLine();
    // CHECKSTYLE:CONSTANTS-ON
    getProjectConfig().getEclipsePlugin().getPluginXml().getEntries().add(builder.toString());
  }
}
