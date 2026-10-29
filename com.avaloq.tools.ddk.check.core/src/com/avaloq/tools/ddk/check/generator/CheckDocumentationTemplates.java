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
package com.avaloq.tools.ddk.check.generator;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

import com.avaloq.tools.ddk.check.check.Category;
import com.avaloq.tools.ddk.check.check.Check;
import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.google.inject.Inject;

/**
 * Emits the Eclipse-Help {@code toc.xml} and context-help {@code contexts.xml}
 * files for a set of {@link CheckCatalog}s as plain XML strings, plus the
 * standalone-browser {@code index.html} landing page. Also exposes the shared
 * {@link #STYLE} block used by both the index and the per-catalog pages.
 */
@SuppressWarnings("nls")
// CHECKSTYLE:CONSTANTS-OFF
public class CheckDocumentationTemplates {

  private static final String TOC_LABEL = "Check Catalogs";
  private static final String TOC_ANCHOR = "../com.avaloq.tools.ddk.check.runtime.ui/toc.xml#checkdocumentation";
  private static final String DOCS_REF_PREFIX = "docs/content/";

  /**
   * Single-source-of-truth stylesheet used by the index page and every per-catalog
   * HTML page. Inlined into the page {@code <head>} so the output is self-contained
   * and renders in any standard browser without external assets.
   */
  public static final String STYLE = """
      :root {
        --bg: #ffffff;
        --text: #1f2328;
        --text-muted: #57606a;
        --border: #d0d7de;
        --card-bg: #f6f8fa;
        --code-bg: #f6f8fa;
        --link: #0969da;
        --accent: #218bff;
        --sev-error-bg: #ffe5e5; --sev-error-text: #82071e; --sev-error-border: #ffadb0;
        --sev-warning-bg: #fff8c5; --sev-warning-text: #633c01; --sev-warning-border: #f3df5b;
        --sev-info-bg: #ddf4ff; --sev-info-text: #054a72; --sev-info-border: #80ccff;
        --sev-ignore-bg: #eaeef2; --sev-ignore-text: #57606a; --sev-ignore-border: #d0d7de;
      }
      @media (prefers-color-scheme: dark) {
        :root {
          --bg: #0d1117;
          --text: #e6edf3;
          --text-muted: #8d96a0;
          --border: #30363d;
          --card-bg: #161b22;
          --code-bg: #161b22;
          --link: #58a6ff;
          --accent: #1f6feb;
          --sev-error-bg: #3d1419; --sev-error-text: #ffa198; --sev-error-border: #6e1216;
          --sev-warning-bg: #3a2c00; --sev-warning-text: #f0d97c; --sev-warning-border: #7a5a00;
          --sev-info-bg: #0c2d4a; --sev-info-text: #79c0ff; --sev-info-border: #1f6feb;
          --sev-ignore-bg: #1c2128; --sev-ignore-text: #8d96a0; --sev-ignore-border: #30363d;
        }
      }
      * { box-sizing: border-box; }
      body {
        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
        font-size: 16px;
        line-height: 1.55;
        color: var(--text);
        background: var(--bg);
        max-width: 820px;
        margin: 0 auto;
        padding: 2rem 1.25rem 4rem;
      }
      h1 { font-size: 1.875rem; margin: 0 0 0.5rem; }
      h2 { font-size: 1.375rem; margin: 2rem 0 1rem; padding-bottom: 0.4rem; border-bottom: 1px solid var(--border); }
      h3 { font-size: 1.05rem; margin: 0; }
      p { margin: 0.5rem 0; }
      a { color: var(--link); text-decoration: none; }
      a:hover { text-decoration: underline; }
      header.catalog-header { margin-bottom: 1.5rem; padding-bottom: 1rem; border-bottom: 1px solid var(--border); }
      header.catalog-header p { color: var(--text-muted); }
      nav.jump {
        margin-top: 1rem;
        padding: 0.75rem 1rem;
        background: var(--card-bg);
        border: 1px solid var(--border);
        border-radius: 6px;
        font-size: 0.9rem;
      }
      nav.jump strong { display: block; margin-bottom: 0.4rem; font-size: 0.8rem; text-transform: uppercase; letter-spacing: 0.05em; color: var(--text-muted); }
      nav.jump ul { margin: 0; padding-left: 1.25rem; }
      nav.jump li { margin: 0.15rem 0; }
      section.category { margin: 2rem 0; }
      section.category > p { color: var(--text-muted); margin-bottom: 0.75rem; }
      article.check {
        margin: 0.75rem 0;
        padding: 0.85rem 1.15rem;
        background: var(--card-bg);
        border: 1px solid var(--border);
        border-radius: 6px;
      }
      article.check > header {
        display: flex;
        align-items: baseline;
        gap: 0.6rem;
        flex-wrap: wrap;
      }
      article.check a.anchor {
        visibility: hidden;
        color: var(--text-muted);
        font-weight: 400;
        font-size: 0.85em;
      }
      article.check > header:hover a.anchor { visibility: visible; }
      article.check:target {
        border-color: var(--accent);
        box-shadow: 0 0 0 3px rgba(33, 139, 255, 0.18);
      }
      .severity {
        display: inline-block;
        padding: 0.1rem 0.6rem;
        font-size: 0.72rem;
        font-weight: 600;
        text-transform: uppercase;
        letter-spacing: 0.05em;
        border-radius: 999px;
        border: 1px solid transparent;
        white-space: nowrap;
      }
      .severity.sev-error   { background: var(--sev-error-bg);   color: var(--sev-error-text);   border-color: var(--sev-error-border); }
      .severity.sev-warning { background: var(--sev-warning-bg); color: var(--sev-warning-text); border-color: var(--sev-warning-border); }
      .severity.sev-info    { background: var(--sev-info-bg);    color: var(--sev-info-text);    border-color: var(--sev-info-border); }
      .severity.sev-ignore  { background: var(--sev-ignore-bg);  color: var(--sev-ignore-text);  border-color: var(--sev-ignore-border); }
      pre.message {
        margin: 0.6rem 0 0;
        padding: 0.55rem 0.8rem;
        background: var(--code-bg);
        border: 1px solid var(--border);
        border-radius: 6px;
        font-family: ui-monospace, SFMono-Regular, "SF Mono", Menlo, Consolas, monospace;
        font-size: 0.85rem;
        white-space: pre-wrap;
        word-break: break-word;
      }
      pre.message::before {
        content: "Message";
        display: block;
        font-family: inherit;
        font-size: 0.68rem;
        font-weight: 600;
        text-transform: uppercase;
        letter-spacing: 0.05em;
        color: var(--text-muted);
        margin-bottom: 0.2rem;
      }
      ul.catalog-list { list-style: none; padding: 0; margin: 1.5rem 0 0; }
      ul.catalog-list li {
        margin: 0.75rem 0;
        padding: 1rem 1.25rem;
        background: var(--card-bg);
        border: 1px solid var(--border);
        border-radius: 6px;
      }
      ul.catalog-list li h2 { margin: 0; border-bottom: none; padding-bottom: 0; font-size: 1.2rem; }
      ul.catalog-list li p { color: var(--text-muted); margin: 0.25rem 0 0; }
      """;

  @Inject
  private CheckGeneratorNaming naming;

  @Inject
  private CheckGeneratorExtensions extensions;

  /**
   * Build the contents of {@code docs/toc.xml} aggregating every catalog in {@code catalogs}.
   *
   * @param catalogs
   *          the catalogs to aggregate
   * @return the {@code toc.xml} contents
   */
  public CharSequence compileToc(final Iterable<CheckCatalog> catalogs) {
    final List<CheckCatalog> sorted = IterableExtensions.sortBy(catalogs, CheckCatalog::getName);
    StringConcatenation builder = new StringConcatenation();
    builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    builder.newLine();
    builder.append("<toc label=\"" + TOC_LABEL + "\" link_to=\"" + TOC_ANCHOR + "\">");
    builder.newLine();
    for (final CheckCatalog catalog : sorted) {
      builder.append("   <topic href=\"" + attrEscape(docRef(catalog)) + "\" label=\"" + attrEscape(catalog.getName()) + "\" sort=\"false\">");
      builder.newLine();
      for (final Category category : catalog.getCategories()) {
        builder.append("      <topic href=\"" + attrEscape(docRef(catalog) + "#" + naming.getContextId(category)) + "\" label=\""
            + attrEscape(category.getLabel()) + "\">");
        builder.newLine();
        for (final Check check : category.getChecks()) {
          appendCheckTopic(builder, "         ", catalog, check);
        }
        builder.append("      </topic>");
        builder.newLine();
      }
      for (final Check check : catalog.getChecks()) {
        appendCheckTopic(builder, "      ", catalog, check);
      }
      builder.append("   </topic>");
      builder.newLine();
    }
    builder.append("</toc>");
    return builder;
  }

  /**
   * Appends the leaf {@code <topic>} element of a check to a {@code toc.xml} under construction.
   *
   * @param builder
   *          the {@code toc.xml} under construction
   * @param indent
   *          the indentation of the element
   * @param catalog
   *          the catalog documenting the check
   * @param check
   *          the check
   */
  private void appendCheckTopic(final StringConcatenation builder, final String indent, final CheckCatalog catalog, final Check check) {
    builder.append(indent + "<topic href=\"" + attrEscape(docRef(catalog) + "#" + naming.getContextId(check)) + "\" label=\""
        + attrEscape(check.getLabel()) + "\">");
    builder.newLine();
    builder.append(indent + "</topic>");
    builder.newLine();
  }

  /**
   * Build the contents of {@code docs/contexts.xml} aggregating every check across {@code catalogs}.
   *
   * @param catalogs
   *          the catalogs whose checks are aggregated
   * @return the {@code contexts.xml} contents
   */
  public CharSequence compileContexts(final Iterable<CheckCatalog> catalogs) {
    final List<ContextEntry> entries = IterableExtensions.sortBy(
        IterableExtensions.map(
            IterableExtensions.flatMap(catalogs, CheckCatalog::getAllChecks),
            check -> new ContextEntry(
                naming.getContextId(check),
                check.getLabel(),
                docRef(parentCatalog(check)) + "#" + naming.getContextId(check))),
        ContextEntry::id);
    StringConcatenation builder = new StringConcatenation();
    builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    builder.newLine();
    builder.append("<contexts>");
    builder.newLine();
    for (final ContextEntry e : entries) {
      builder.append("   <context id=\"" + attrEscape(e.id()) + "\">");
      builder.newLine();
      builder.append("      <topic href=\"" + attrEscape(e.href()) + "\" label=\"" + attrEscape(e.label()) + "\"/>");
      builder.newLine();
      builder.append("   </context>");
      builder.newLine();
    }
    builder.append("</contexts>");
    return builder;
  }

  /**
   * Build the contents of {@code docs/index.html} listing every catalog with a link to its page.
   *
   * @param catalogs
   *          the catalogs to list
   * @return the {@code index.html} contents
   */
  public CharSequence compileIndex(final Iterable<CheckCatalog> catalogs) {
    final List<CheckCatalog> sorted = IterableExtensions.sortBy(catalogs, CheckCatalog::getName);
    StringConcatenation builder = new StringConcatenation();
    builder.append("<!DOCTYPE html>");
    builder.newLine();
    builder.append("<html lang=\"en\">");
    builder.newLine();
    builder.append("<head>");
    builder.newLine();
    builder.append("  ");
    builder.append("<meta charset=\"UTF-8\">");
    builder.newLine();
    builder.append("  ");
    builder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
    builder.newLine();
    builder.append("  ");
    builder.append("<title>Check Catalogs</title>");
    builder.newLine();
    builder.append("  ");
    builder.append("<style>");
    builder.newLine();
    builder.append(STYLE);
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("</style>");
    builder.newLine();
    builder.append("</head>");
    builder.newLine();
    builder.append("<body>");
    builder.newLine();
    builder.append("  ");
    builder.append("<header class=\"catalog-header\">");
    builder.newLine();
    builder.append("    ");
    builder.append("<h1>Check Catalogs</h1>");
    builder.newLine();
    builder.append("    ");
    builder.append("<p>");
    builder.append(sorted.size(), "    ");
    builder.append(" catalog");
    if (sorted.size() != 1) {
      builder.append("s");
    }
    builder.append(" documented.</p>");
    builder.newLineIfNotEmpty();
    builder.append("  ");
    builder.append("</header>");
    builder.newLine();
    builder.append("  ");
    builder.append("<main>");
    builder.newLine();
    builder.append("    ");
    builder.append("<ul class=\"catalog-list\">");
    builder.newLine();
    for (final CheckCatalog catalog : sorted) {
      builder.append("      ");
      builder.append("<li id=\"");
      builder.append(catalog.getName(), "      ");
      builder.append("\">");
      builder.newLineIfNotEmpty();
      builder.append("      ");
      builder.append("  ");
      builder.append("<h2><a href=\"");
      builder.append(indexRef(catalog), "        ");
      builder.append("\">");
      builder.append(catalog.getName(), "        ");
      builder.append("</a></h2>");
      builder.newLineIfNotEmpty();
      builder.append("      ");
      builder.append("  ");
      final String description = extensions.formatDescription(catalog.getDescription());
      builder.newLineIfNotEmpty();
      if (description != null) {
        builder.append("      ");
        builder.append("  ");
        builder.append("<p>");
        builder.append(description, "        ");
        builder.append("</p>");
        builder.newLineIfNotEmpty();
      }
      builder.append("      ");
      builder.append("</li>");
      builder.newLine();
    }
    builder.append("    ");
    builder.append("</ul>");
    builder.newLine();
    builder.append("  ");
    builder.append("</main>");
    builder.newLine();
    builder.append("</body>");
    builder.newLine();
    builder.append("</html>");
    builder.newLine();
    return builder;
  }

  /**
   * Reference used from {@code toc.xml}/{@code contexts.xml} (relative to {@code docs/}).
   *
   * @param c
   *          the catalog
   * @return the documentation reference path
   */
  private String docRef(final CheckCatalog c) {
    return DOCS_REF_PREFIX + naming.docFileName(c);
  }

  /**
   * Returns the catalog containing the given object.
   *
   * @param o
   *          the contained object
   * @return the enclosing catalog, or {@code null} if none
   */
  private CheckCatalog parentCatalog(final EObject o) {
    return EcoreUtil2.getContainerOfType(o, CheckCatalog.class);
  }

  /**
   * Path used from {@code index.html} (same directory as {@code content/}).
   *
   * @param c
   *          the catalog
   * @return the relative reference path
   */
  private String indexRef(final CheckCatalog c) {
    return "content/" + naming.docFileName(c);
  }

  /**
   * Escape characters that have special meaning inside an XML attribute value.
   *
   * @param s
   *          the raw attribute value, may be {@code null}
   * @return the escaped value, or {@code null} if {@code s} is {@code null}
   */
  private static String attrEscape(final String s) {
    if (s == null) {
      return null;
    }
    return htmlEscape(s).replace("'", "&apos;");
  }

  /**
   * Escape characters that have special meaning in HTML text or a double-quoted HTML attribute value.
   *
   * @param s
   *          the raw text, may be {@code null}
   * @return the escaped text, or {@code null} if {@code s} is {@code null}
   */
  static String htmlEscape(final String s) {
    if (s == null) {
      return null;
    }
    return s.replace("&", "&amp;")
        .replace("\"", "&quot;")
        .replace("<", "&lt;")
        .replace(">", "&gt;");
  }

  /**
   * A single context-help entry.
   *
   * @param id
   *          the context id
   * @param label
   *          the display label
   * @param href
   *          the documentation reference
   */
  private record ContextEntry(String id, String label, String href) {
  }
// CHECKSTYLE:CONSTANTS-ON
}
