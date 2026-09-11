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
package com.avaloq.tools.ddk.xtext.format.resource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.avaloq.tools.ddk.xtext.format.FormatInjectorProvider;
import com.avaloq.tools.ddk.xtext.format.format.FormatConfiguration;
import com.avaloq.tools.ddk.xtext.format.format.FormatFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;

/** Regression fixtures for stable exports and transitive Format configuration changes. */
@ExtendWith(InjectionExtension.class)
@InjectWith(FormatInjectorProvider.class)
@SuppressWarnings("nls")
public class FormatResourceDescriptionStrategyTest {

  private static final String LF = "\n";

  private static final String CRLF = "\r\n";

  private static final String BASE_NAME = "Base";

  private static final String CHILD_NAME = "Child";

  private static final String EMPTY_NAME = "Empty";

  @Inject
  private ParseHelper<FormatConfiguration> parser;

  @Inject
  private IDefaultResourceDescriptionStrategy strategy;

  @Inject
  private Provider<XtextResourceSet> resourceSets;

  @Test
  public void identicalReloadsHaveIdenticalExports() throws Exception {
    for (String newline : List.of(LF, CRLF)) {
      String source = "formatter for example.Base" + newline + "const int SPACING = 1;" + newline;
      assertEquals(exports(parse(BASE_NAME, source)), exports(parse(BASE_NAME, source)), "object identity must not affect the index");
    }
  }

  @Test
  public void inheritedChangesPropagateThroughEmptyIntermediateFormats() throws Exception {
    FormatConfiguration base = parse(BASE_NAME, "formatter for example.Base\nconst int SPACING = 1;\n");
    FormatConfiguration child = parse(CHILD_NAME, "formatter for example.Child with example.Base\n");
    FormatConfiguration grandchild = parse("Grandchild", "formatter for example.Grandchild with example.Child\n");
    child.setExtendedFormatConfiguration(base);
    grandchild.setExtendedFormatConfiguration(child);
    List<String> before = exports(grandchild);
    FormatConfiguration changed = parse(BASE_NAME, "formatter for example.Base\nconst int SPACING = 2;\n");
    child.setExtendedFormatConfiguration(changed);
    assertNotEquals(before, exports(grandchild), "a real change must reach the grandchild, even through an empty child");
  }

  @Test
  public void inheritedSourceLocationsParticipateInExports() throws Exception {
    String source = "formatter for example.Base\nconst int SPACING = 1;\n";
    FormatConfiguration child = parse(CHILD_NAME, "formatter for example.Child with example.Base\nconst int LOCAL = 0;\n");
    child.setExtendedFormatConfiguration(parse(BASE_NAME, source));
    List<String> before = exports(child);
    child.setExtendedFormatConfiguration(parse(BASE_NAME, "// inserted line\n" + source));
    assertNotEquals(before, exports(child), "inherited source references must be regenerated after a line shift");
    before = exports(child);
    child.setExtendedFormatConfiguration(parse(BASE_NAME, ("// inserted line\n" + source).replace(LF, CRLF)));
    assertNotEquals(before, exports(child), "line-ending changes affect trace offsets");
  }

  @Test
  public void configurationWithoutLocalDeclarationsStillHasAContentFingerprint() throws Exception {
    assertNotEquals(exports(parse(EMPTY_NAME, "formatter for example.Empty\n")),
        exports(parse(EMPTY_NAME, "// inserted line\nformatter for example.Empty\n")));
  }

  @Test
  public void cyclicInheritanceTerminates() throws Exception {
    FormatConfiguration first = parse("First", "formatter for example.First\n");
    FormatConfiguration second = parse("Second", "formatter for example.Second\n");
    first.setExtendedFormatConfiguration(second);
    second.setExtendedFormatConfiguration(first);
    assertTimeoutPreemptively(Duration.ofSeconds(5), () -> assertEquals(exports(first), exports(first)));
  }

  @Test
  public void unresolvedBaseCanBecomeResolvedWithoutLosingItsContentChange() throws Exception {
    FormatConfiguration child = parse(CHILD_NAME, "formatter for example.Child with example.Base\n");
    FormatConfiguration proxy = FormatFactory.eINSTANCE.createFormatConfiguration();
    ((InternalEObject) proxy).eSetProxyURI(URI.createURI("synthetic:/Base.format#/0"));
    child.setExtendedFormatConfiguration(proxy);
    List<String> unresolved = exports(child);
    assertEquals(unresolved, exports(child), "unresolved proxies must have a stable fingerprint");
    child.setExtendedFormatConfiguration(parse(BASE_NAME, "formatter for example.Base\nconst int SPACING = 1;\n"));
    assertNotEquals(unresolved, exports(child), "resolved base content must enter the fingerprint");
  }

  @Test
  public void baseSelectionAndSuperclassArePartOfTheConfiguration() throws Exception {
    String source = "formatter for example.Child with example.First\n";
    assertNotEquals(exports(parse(CHILD_NAME, source)), exports(parse(CHILD_NAME, source.replace("First", "Second"))));
    assertNotEquals(exports(parse(CHILD_NAME, "formatter for example.Child\n")),
        exports(parse(CHILD_NAME, "formatter for example.Child extends example.CustomFormatter\n")));
  }

  private FormatConfiguration parse(final String name, final String source) {
    return assertDoesNotThrow(() -> parser.parse(source, URI.createURI("synthetic:/" + name + ".format"), resourceSets.get()));
  }

  private List<String> exports(final FormatConfiguration format) {
    List<IEObjectDescription> descriptions = new ArrayList<>();
    strategy.createEObjectDescriptions(format, descriptions::add);
    for (EObject child : format.eContents()) {
      strategy.createEObjectDescriptions(child, descriptions::add);
    }
    return descriptions.stream().map(d -> d.getName().toString() + "@" + d.getEObjectURI()).sorted().toList();
  }
}
