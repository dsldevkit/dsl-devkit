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

package com.avaloq.tools.ddk.check.generator.quickfix

import org.eclipse.xtext.xtext.generator.AbstractXtextGeneratorFragment
import com.google.inject.Inject
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming
import org.eclipse.xtext.xtext.generator.model.FileAccessFactory
import org.eclipse.xtext.xtext.generator.model.TypeReference
import org.eclipse.xtext.Grammar

import static extension org.eclipse.xtext.xtext.generator.util.GrammarUtil2.*

class CheckQuickfixProviderFragment2 extends AbstractXtextGeneratorFragment {

  static val UI_PLUGIN = "com.avaloq.tools.ddk.check.runtime.ui"

  @Inject extension XtextGeneratorNaming

  @Inject FileAccessFactory fileAccessFactory

  def protected TypeReference getQuickfixProviderClass(Grammar g) {
    return new TypeReference(
      g.eclipsePluginBasePackage + ".quickfix." + g.simpleName + "QuickfixProvider"
    )
  }

  override generate() {
    if (projectConfig.eclipsePlugin.manifest !== null) {
      projectConfig.eclipsePlugin.manifest.requiredBundles += UI_PLUGIN
    }

    if (projectConfig.eclipsePlugin.src !== null) {
      generateQuickfixProvider
    }

    if (projectConfig.eclipsePlugin.pluginXml !== null) {
      addRegistrationToPluginXml
    }
  }

  protected def generateQuickfixProvider() {
    fileAccessFactory.createJavaFile(grammar.quickfixProviderClass, '''
      public class «grammar.quickfixProviderClass.simpleName» extends DefaultCheckQuickfixProvider {

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
    ''').writeTo(projectConfig.eclipsePlugin.src)
  }

  protected def addRegistrationToPluginXml() {
    val executableExtensionFactory = grammar.eclipsePluginExecutableExtensionFactory
    projectConfig.eclipsePlugin.pluginXml.entries += '''
      <!-- quickfix marker resolution generator -->
      <extension
            point="org.eclipse.ui.ide.markerResolution">
         <markerResolutionGenerator
               class="«executableExtensionFactory»:org.eclipse.xtext.ui.editor.quickfix.MarkerResolutionGenerator">
         </markerResolutionGenerator>
      </extension>
    '''
  }
}
