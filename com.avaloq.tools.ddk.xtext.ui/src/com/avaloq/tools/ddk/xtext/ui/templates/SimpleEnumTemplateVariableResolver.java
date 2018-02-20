package com.avaloq.tools.ddk.xtext.ui.templates;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.xtext.ui.editor.templates.AbstractTemplateVariableResolver;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContext;


/**
 * Simple enumeration template where enumeration is passed as string arguments to the template.
 */
public class SimpleEnumTemplateVariableResolver extends AbstractTemplateVariableResolver {

  public SimpleEnumTemplateVariableResolver() {
    super("SimpleEnum", ""); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public List<String> resolveValues(final TemplateVariable variable, final XtextTemplateContext castedContext) {
    return variable.getVariableType().getParams().stream().filter(param -> param instanceof String).collect(Collectors.toList());
  }

}
