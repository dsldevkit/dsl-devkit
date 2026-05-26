/*******************************************************************************
 * Copyright (c) 2026 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.check.generator

import com.avaloq.tools.ddk.check.check.CheckCatalog
import com.google.inject.Inject

import static extension com.avaloq.tools.ddk.check.generator.CheckGeneratorNaming.*

/**
 * Emits the Eclipse-Help {@code toc.xml} and context-help {@code contexts.xml}
 * files for a set of {@link CheckCatalog}s as plain XML strings, without any
 * dependency on PDE-internal APIs (TocModel / CtxHelpModel et al.). Designed to
 * run both inside the Xtext generator and from the headless
 * {@code CheckDocApplication}.
 *
 * The output format is byte-compatible with the files PDE used to mutate in
 * place: three-space indentation, alphabetic ordering for both catalog topics
 * and context entries, and apostrophes escaped as {@code &apos;} in attributes.
 */
class CheckDocumentationTemplates {

  static val TOC_LABEL = "Check Catalogs"
  static val TOC_ANCHOR = "../com.avaloq.tools.ddk.check.runtime.ui/toc.xml#checkdocumentation"
  static val DOCS_REF_PREFIX = "docs/content/"

  @Inject extension CheckGeneratorNaming naming

  /** Build the contents of {@code docs/toc.xml} aggregating every catalog in {@code catalogs}. */
  def CharSequence compileToc(Iterable<CheckCatalog> catalogs) {
    val sorted = catalogs.sortBy[name]
    '''
      <?xml version="1.0" encoding="UTF-8"?>
      <toc label="«TOC_LABEL»" link_to="«TOC_ANCHOR»">
      «FOR catalog : sorted»
         <topic href="«catalog.docRef»" label="«catalog.name»" sort="false">
      «FOR category : catalog.categories»
            <topic href="«catalog.docRef»#«category.contextId»" label="«category.label.attrEscape»">
      «FOR check : category.checks»
               <topic href="«catalog.docRef»#«check.contextId»" label="«check.label.attrEscape»">
               </topic>
      «ENDFOR»
            </topic>
      «ENDFOR»
      «FOR check : catalog.checks»
            <topic href="«catalog.docRef»#«check.contextId»" label="«check.label.attrEscape»">
            </topic>
      «ENDFOR»
         </topic>
      «ENDFOR»
      </toc>
    '''
  }

  /** Build the contents of {@code docs/contexts.xml} aggregating every check across {@code catalogs}. */
  def CharSequence compileContexts(Iterable<CheckCatalog> catalogs) {
    val entries = catalogs
      .flatMap[allChecks]
      .map[check | new ContextEntry(check.contextId, check.label.attrEscape, parentCatalog(check).docRef + "#" + check.contextId)]
      .sortBy[id]
    '''
      <?xml version="1.0" encoding="UTF-8"?>
      <contexts>
      «FOR e : entries»
         <context id="«e.id»">
            <topic href="«e.href»" label="«e.label»"/>
         </context>
      «ENDFOR»
      </contexts>
    '''
  }

  def private String docRef(CheckCatalog c) {
    DOCS_REF_PREFIX + c.docFileName
  }

  def private CheckCatalog parentCatalog(org.eclipse.emf.ecore.EObject o) {
    org.eclipse.xtext.EcoreUtil2.getContainerOfType(o, CheckCatalog)
  }

  /** Escape characters that have special meaning inside an XML attribute value. */
  def private String attrEscape(String s) {
    s?.replace("&", "&amp;")
      ?.replace("'", "&apos;")
      ?.replace("\"", "&quot;")
      ?.replace("<", "&lt;")
      ?.replace(">", "&gt;")
  }

  @org.eclipse.xtend.lib.annotations.Data
  private static class ContextEntry {
    String id
    String label
    String href
  }
}
