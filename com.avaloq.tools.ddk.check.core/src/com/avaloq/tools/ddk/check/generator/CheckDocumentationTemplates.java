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
import java.util.Objects;

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
  public static final String STYLE = buildStyle();

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
    builder.append("<toc label=\"");
    builder.append(TOC_LABEL);
    builder.append("\" link_to=\"");
    builder.append(TOC_ANCHOR);
    builder.append("\">");
    builder.newLineIfNotEmpty();
    for (final CheckCatalog catalog : sorted) {
      builder.append("   ");
      builder.append("<topic href=\"");
      builder.append(docRef(catalog), "   ");
      builder.append("\" label=\"");
      builder.append(catalog.getName(), "   ");
      builder.append("\" sort=\"false\">");
      builder.newLineIfNotEmpty();
      for (final Category category : catalog.getCategories()) {
        builder.append("<topic href=\"");
        builder.append(docRef(catalog));
        builder.append("#");
        builder.append(naming.getContextId(category));
        builder.append("\" label=\"");
        builder.append(attrEscape(category.getLabel()));
        builder.append("\">");
        builder.newLineIfNotEmpty();
        for (final Check check : category.getChecks()) {
          builder.append("      ");
          builder.append("<topic href=\"");
          builder.append(docRef(catalog), "      ");
          builder.append("#");
          builder.append(naming.getContextId(check), "      ");
          builder.append("\" label=\"");
          builder.append(attrEscape(check.getLabel()), "      ");
          builder.append("\">");
          builder.newLineIfNotEmpty();
          builder.append("      ");
          builder.append("</topic>");
          builder.newLine();
        }
        builder.append("</topic>");
        builder.newLine();
      }
      for (final Check check : catalog.getChecks()) {
        builder.append("<topic href=\"");
        builder.append(docRef(catalog));
        builder.append("#");
        builder.append(naming.getContextId(check));
        builder.append("\" label=\"");
        builder.append(attrEscape(check.getLabel()));
        builder.append("\">");
        builder.newLineIfNotEmpty();
        builder.append("</topic>");
        builder.newLine();
      }
      builder.append("   ");
      builder.append("</topic>");
      builder.newLine();
    }
    builder.append("</toc>");
    builder.newLine();
    return builder;
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
                attrEscape(check.getLabel()),
                docRef(parentCatalog(check)) + "#" + naming.getContextId(check))),
        entry -> entry.id);
    StringConcatenation builder = new StringConcatenation();
    builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    builder.newLine();
    builder.append("<contexts>");
    builder.newLine();
    for (final ContextEntry e : entries) {
      builder.append("<context id=\"");
      builder.append(e.id);
      builder.append("\">");
      builder.newLineIfNotEmpty();
      builder.append("   ");
      builder.append("<topic href=\"");
      builder.append(e.href, "   ");
      builder.append("\" label=\"");
      builder.append(e.label, "   ");
      builder.append("\"/>");
      builder.newLineIfNotEmpty();
      builder.append("</context>");
      builder.newLine();
    }
    builder.append("</contexts>");
    builder.newLine();
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
  private String attrEscape(final String s) {
    if (s == null) {
      return null;
    }
    return s.replace("&", "&amp;")
        .replace("'", "&apos;")
        .replace("\"", "&quot;")
        .replace("<", "&lt;")
        .replace(">", "&gt;");
  }

  /**
   * Builds the {@link #STYLE} stylesheet, preserving the exact CSS layout and line breaks.
   *
   * @return the stylesheet text
   */
  private static String buildStyle() {
    StringConcatenation builder = new StringConcatenation();
    builder.append(":root {");
    builder.newLine();
    builder.append("  ");
    builder.append("--bg: #ffffff;");
    builder.newLine();
    builder.append("  ");
    builder.append("--text: #1f2328;");
    builder.newLine();
    builder.append("  ");
    builder.append("--text-muted: #57606a;");
    builder.newLine();
    builder.append("  ");
    builder.append("--border: #d0d7de;");
    builder.newLine();
    builder.append("  ");
    builder.append("--card-bg: #f6f8fa;");
    builder.newLine();
    builder.append("  ");
    builder.append("--code-bg: #f6f8fa;");
    builder.newLine();
    builder.append("  ");
    builder.append("--link: #0969da;");
    builder.newLine();
    builder.append("  ");
    builder.append("--accent: #218bff;");
    builder.newLine();
    builder.append("  ");
    builder.append("--sev-error-bg: #ffe5e5; --sev-error-text: #82071e; --sev-error-border: #ffadb0;");
    builder.newLine();
    builder.append("  ");
    builder.append("--sev-warning-bg: #fff8c5; --sev-warning-text: #633c01; --sev-warning-border: #f3df5b;");
    builder.newLine();
    builder.append("  ");
    builder.append("--sev-info-bg: #ddf4ff; --sev-info-text: #054a72; --sev-info-border: #80ccff;");
    builder.newLine();
    builder.append("  ");
    builder.append("--sev-ignore-bg: #eaeef2; --sev-ignore-text: #57606a; --sev-ignore-border: #d0d7de;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("@media (prefers-color-scheme: dark) {");
    builder.newLine();
    builder.append("  ");
    builder.append(":root {");
    builder.newLine();
    builder.append("    ");
    builder.append("--bg: #0d1117;");
    builder.newLine();
    builder.append("    ");
    builder.append("--text: #e6edf3;");
    builder.newLine();
    builder.append("    ");
    builder.append("--text-muted: #8d96a0;");
    builder.newLine();
    builder.append("    ");
    builder.append("--border: #30363d;");
    builder.newLine();
    builder.append("    ");
    builder.append("--card-bg: #161b22;");
    builder.newLine();
    builder.append("    ");
    builder.append("--code-bg: #161b22;");
    builder.newLine();
    builder.append("    ");
    builder.append("--link: #58a6ff;");
    builder.newLine();
    builder.append("    ");
    builder.append("--accent: #1f6feb;");
    builder.newLine();
    builder.append("    ");
    builder.append("--sev-error-bg: #3d1419; --sev-error-text: #ffa198; --sev-error-border: #6e1216;");
    builder.newLine();
    builder.append("    ");
    builder.append("--sev-warning-bg: #3a2c00; --sev-warning-text: #f0d97c; --sev-warning-border: #7a5a00;");
    builder.newLine();
    builder.append("    ");
    builder.append("--sev-info-bg: #0c2d4a; --sev-info-text: #79c0ff; --sev-info-border: #1f6feb;");
    builder.newLine();
    builder.append("    ");
    builder.append("--sev-ignore-bg: #1c2128; --sev-ignore-text: #8d96a0; --sev-ignore-border: #30363d;");
    builder.newLine();
    builder.append("  ");
    builder.append("}");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("* { box-sizing: border-box; }");
    builder.newLine();
    builder.append("body {");
    builder.newLine();
    builder.append("  ");
    builder.append("font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, sans-serif;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-size: 16px;");
    builder.newLine();
    builder.append("  ");
    builder.append("line-height: 1.55;");
    builder.newLine();
    builder.append("  ");
    builder.append("color: var(--text);");
    builder.newLine();
    builder.append("  ");
    builder.append("background: var(--bg);");
    builder.newLine();
    builder.append("  ");
    builder.append("max-width: 820px;");
    builder.newLine();
    builder.append("  ");
    builder.append("margin: 0 auto;");
    builder.newLine();
    builder.append("  ");
    builder.append("padding: 2rem 1.25rem 4rem;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("h1 { font-size: 1.875rem; margin: 0 0 0.5rem; }");
    builder.newLine();
    builder.append("h2 { font-size: 1.375rem; margin: 2rem 0 1rem; padding-bottom: 0.4rem; border-bottom: 1px solid var(--border); }");
    builder.newLine();
    builder.append("h3 { font-size: 1.05rem; margin: 0; }");
    builder.newLine();
    builder.append("p { margin: 0.5rem 0; }");
    builder.newLine();
    builder.append("a { color: var(--link); text-decoration: none; }");
    builder.newLine();
    builder.append("a:hover { text-decoration: underline; }");
    builder.newLine();
    builder.append("header.catalog-header { margin-bottom: 1.5rem; padding-bottom: 1rem; border-bottom: 1px solid var(--border); }");
    builder.newLine();
    builder.append("header.catalog-header p { color: var(--text-muted); }");
    builder.newLine();
    builder.append("a.back-link { display: inline-block; margin-bottom: 0.75rem; font-size: 0.875rem; color: var(--text-muted); }");
    builder.newLine();
    builder.append("a.back-link:hover { color: var(--link); }");
    builder.newLine();
    builder.append("nav.jump {");
    builder.newLine();
    builder.append("  ");
    builder.append("margin-top: 1rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("padding: 0.75rem 1rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("background: var(--card-bg);");
    builder.newLine();
    builder.append("  ");
    builder.append("border: 1px solid var(--border);");
    builder.newLine();
    builder.append("  ");
    builder.append("border-radius: 6px;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-size: 0.9rem;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("nav.jump strong { display: block; margin-bottom: 0.4rem; font-size: 0.8rem; text-transform: uppercase; letter-spacing: 0.05em; color: var(--text-muted); }");
    builder.newLine();
    builder.append("nav.jump ul { margin: 0; padding-left: 1.25rem; }");
    builder.newLine();
    builder.append("nav.jump li { margin: 0.15rem 0; }");
    builder.newLine();
    builder.append("section.category { margin: 2rem 0; }");
    builder.newLine();
    builder.append("section.category > p { color: var(--text-muted); margin-bottom: 0.75rem; }");
    builder.newLine();
    builder.append("article.check {");
    builder.newLine();
    builder.append("  ");
    builder.append("margin: 0.75rem 0;");
    builder.newLine();
    builder.append("  ");
    builder.append("padding: 0.85rem 1.15rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("background: var(--card-bg);");
    builder.newLine();
    builder.append("  ");
    builder.append("border: 1px solid var(--border);");
    builder.newLine();
    builder.append("  ");
    builder.append("border-radius: 6px;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("article.check > header {");
    builder.newLine();
    builder.append("  ");
    builder.append("display: flex;");
    builder.newLine();
    builder.append("  ");
    builder.append("align-items: baseline;");
    builder.newLine();
    builder.append("  ");
    builder.append("gap: 0.6rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("flex-wrap: wrap;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("article.check a.anchor {");
    builder.newLine();
    builder.append("  ");
    builder.append("visibility: hidden;");
    builder.newLine();
    builder.append("  ");
    builder.append("color: var(--text-muted);");
    builder.newLine();
    builder.append("  ");
    builder.append("font-weight: 400;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-size: 0.85em;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("article.check > header:hover a.anchor { visibility: visible; }");
    builder.newLine();
    builder.append("article.check:target {");
    builder.newLine();
    builder.append("  ");
    builder.append("border-color: var(--accent);");
    builder.newLine();
    builder.append("  ");
    builder.append("box-shadow: 0 0 0 3px rgba(33, 139, 255, 0.18);");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append(".severity {");
    builder.newLine();
    builder.append("  ");
    builder.append("display: inline-block;");
    builder.newLine();
    builder.append("  ");
    builder.append("padding: 0.1rem 0.6rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-size: 0.72rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-weight: 600;");
    builder.newLine();
    builder.append("  ");
    builder.append("text-transform: uppercase;");
    builder.newLine();
    builder.append("  ");
    builder.append("letter-spacing: 0.05em;");
    builder.newLine();
    builder.append("  ");
    builder.append("border-radius: 999px;");
    builder.newLine();
    builder.append("  ");
    builder.append("border: 1px solid transparent;");
    builder.newLine();
    builder.append("  ");
    builder.append("white-space: nowrap;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append(".severity.sev-error   { background: var(--sev-error-bg);   color: var(--sev-error-text);   border-color: var(--sev-error-border); }");
    builder.newLine();
    builder.append(".severity.sev-warning { background: var(--sev-warning-bg); color: var(--sev-warning-text); border-color: var(--sev-warning-border); }");
    builder.newLine();
    builder.append(".severity.sev-info    { background: var(--sev-info-bg);    color: var(--sev-info-text);    border-color: var(--sev-info-border); }");
    builder.newLine();
    builder.append(".severity.sev-ignore  { background: var(--sev-ignore-bg);  color: var(--sev-ignore-text);  border-color: var(--sev-ignore-border); }");
    builder.newLine();
    builder.append("pre.message {");
    builder.newLine();
    builder.append("  ");
    builder.append("margin: 0.6rem 0 0;");
    builder.newLine();
    builder.append("  ");
    builder.append("padding: 0.55rem 0.8rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("background: var(--code-bg);");
    builder.newLine();
    builder.append("  ");
    builder.append("border: 1px solid var(--border);");
    builder.newLine();
    builder.append("  ");
    builder.append("border-radius: 6px;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-family: ui-monospace, SFMono-Regular, \"SF Mono\", Menlo, Consolas, monospace;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-size: 0.85rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("white-space: pre-wrap;");
    builder.newLine();
    builder.append("  ");
    builder.append("word-break: break-word;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("pre.message::before {");
    builder.newLine();
    builder.append("  ");
    builder.append("content: \"Message\";");
    builder.newLine();
    builder.append("  ");
    builder.append("display: block;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-family: inherit;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-size: 0.68rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("font-weight: 600;");
    builder.newLine();
    builder.append("  ");
    builder.append("text-transform: uppercase;");
    builder.newLine();
    builder.append("  ");
    builder.append("letter-spacing: 0.05em;");
    builder.newLine();
    builder.append("  ");
    builder.append("color: var(--text-muted);");
    builder.newLine();
    builder.append("  ");
    builder.append("margin-bottom: 0.2rem;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("ul.catalog-list { list-style: none; padding: 0; margin: 1.5rem 0 0; }");
    builder.newLine();
    builder.append("ul.catalog-list li {");
    builder.newLine();
    builder.append("  ");
    builder.append("margin: 0.75rem 0;");
    builder.newLine();
    builder.append("  ");
    builder.append("padding: 1rem 1.25rem;");
    builder.newLine();
    builder.append("  ");
    builder.append("background: var(--card-bg);");
    builder.newLine();
    builder.append("  ");
    builder.append("border: 1px solid var(--border);");
    builder.newLine();
    builder.append("  ");
    builder.append("border-radius: 6px;");
    builder.newLine();
    builder.append("}");
    builder.newLine();
    builder.append("ul.catalog-list li h2 { margin: 0; border-bottom: none; padding-bottom: 0; font-size: 1.2rem; }");
    builder.newLine();
    builder.append("ul.catalog-list li p { color: var(--text-muted); margin: 0.25rem 0 0; }");
    builder.newLine();
    return builder.toString();
  }

  /** Immutable record of a single context-help entry, sortable by id. */
  private static final class ContextEntry {
    private final String id;
    private final String label;
    private final String href;

    /**
     * Creates a context-help entry.
     *
     * @param id
     *          the context id
     * @param label
     *          the display label
     * @param href
     *          the documentation reference
     */
    ContextEntry(final String id, final String label, final String href) {
      this.id = id;
      this.label = label;
      this.href = href;
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, label, href);
    }

    @Override
    public boolean equals(final Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }
      ContextEntry other = (ContextEntry) obj;
      return Objects.equals(id, other.id) && Objects.equals(label, other.label) && Objects.equals(href, other.href);
    }

    @Override
    public String toString() {
      return "ContextEntry [id=" + id + ", label=" + label + ", href=" + href + "]";
    }
  }
// CHECKSTYLE:CONSTANTS-ON
}
