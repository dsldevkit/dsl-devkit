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
package com.avaloq.tools.ddk.xtext.ui.validation.preferences;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.ui.editor.validation.MarkerCreator;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;

import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * Preference page for Valid rules and categories. This page allows the user to
 * select among optional rules (grouped by categories) which must be evaluated
 * when validating an Xtext document (of type Valid.) All preferences are stored
 * in the ch.paranor.au.xtext.core settings (i.e. not in the grammar/language
 * settings.)
 */
// CHECKSTYLE:OFF
public abstract class AbstractValidPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IWorkbenchPropertyPage {
  // CHECKSTYLE:ON
  /** Logging. */
  protected static final Logger LOGGER = Logger.getLogger(AbstractValidPreferencePage.class);

  /** Label Text for the Perform Expensive Checks checkbox. */
  private static final String EXPENSIVE_CHECKS_TOGGLE_MESSAGE = "Perform expensive checks"; //$NON-NLS-1$

  /** Preference key for the Revalidate Dialog perfom expensive validation toggle button. */
  private static final String PERFORM_EXPENSIVE_VALIDATION = "perform_expensive_validation"; //$NON-NLS-1$

  /** Revalidate Dialog Title. */
  private static final String REVALIDATE_DIALOG_TITLE = "Error/Warning settings changed"; //$NON-NLS-1$

  /** Revalidate Dialog Message. */
  private static final String REVALIDATE_DIALOG_MESSAGE = "The Error/Warning settings have changed. A full revalidation is required for changes to take effect. Revalidate now?"; //$NON-NLS-1$

  /** The Constant VALIDATION_JOB_MESSAGE. This human readable message is shown to the user in the progress view. */
  private static final String VALIDATION_JOB_MESSAGE = "Validating and updating markers"; //$NON-NLS-1$

  /** Layout's hint for the height of the message text. */
  private static final int MESSAGE_HEIGHT_HINT = 50;

  /** Layout's hint for the description of the message text. */
  private static final int DESCRIPTION_HEIGHT_HINT = 100;

  /** Layout's hint for the width of the severity icon. */
  private static final int SEVERITY_IMAGE_WIDTH_HINT = 16;

  /** Number of columns in the Error details pane. */
  private static final int GRID_COLUMNS = 5;

  /** The tree viewer control. */
  private ValidPreferenceCheckedTreeViewer treeViewer;

  /** The widget for the rules description. */
  private Text description;

  /** The widget for the rules message. */
  private Text message;

  /** The widget for the rules severity. */
  private Label severity;

  /** The image for the rules severity. */
  private Label severityImage;

  /** The widget for the evaluation mode. */
  private Label evaluationMode;

  /** The language name (injected by the constructor). */
  private final String languageName;

  /** The file extensions (injected by the constructor). */
  private final String fileExtensions;

  /** The workbench. */
  private IWorkbench workbench;

  /** The project (currently unused). */
  private IProject project;

  /** The categories (top-level elements). */
  private List<IPreferenceItem> categories;

  /** The injected resource descriptions. */
  @Inject
  private IResourceDescriptions resourceDescriptions;

  /** The injected resource service provider. */
  @Inject
  private IResourceServiceProvider resourceServiceProvider;

  /** The injected resource set. */
  @Inject
  private ResourceSet resourceSet;

  /** The injected marker creator. */
  @Inject
  private MarkerCreator markerCreator;

  /** The injected storage to URI mapper. */
  @Inject
  private IStorage2UriMapper storage2UriMapper;

  /** Scoped Preference Store. */
  private final ScopedPreferenceStore preferences;

  /**
   * Instantiates a new valid preference page for a given Xtext language.
   *
   * @param languageName
   *          the language name
   * @param fileExtensions
   *          the file extensions
   */
  @Inject
  public AbstractValidPreferencePage(@Named(Constants.LANGUAGE_NAME) final String languageName, @Named(Constants.FILE_EXTENSIONS) final String fileExtensions) {
    super();
    this.languageName = languageName;
    this.fileExtensions = fileExtensions;

    preferences = new ScopedPreferenceStore(InstanceScope.INSTANCE, getPreferenceStoreName());
    setPreferenceStore(preferences);
  }

  /**
   * The name of the preference page.
   *
   * @return by default, should be the fully qualified language name (non null)
   */
  public abstract String getPreferenceStoreName();

  /**
   * Gets the categories.
   *
   * @return the categories
   */
  public List<IPreferenceItem> getCategories() {
    return categories;
  }

  /**
   * Sets the categories.
   *
   * @param categories
   *          the new categories
   */
  public void setCategories(final List<IPreferenceItem> categories) {
    this.categories = categories;
  }

  /**
   * Inits the worbench.
   *
   * @param paramIWorkbench
   *          the workbench {@inheritDoc}
   */
  @Override
  public void init(final IWorkbench paramIWorkbench) {
    this.workbench = paramIWorkbench;
  }

  /**
   * Gets the workbench.
   *
   * @return the workbench
   */
  protected IWorkbench getWorkbench() {
    return workbench;
  }

  /**
   * Sets the element.
   *
   * @param element
   *          the new element {@inheritDoc}
   */
  @Override
  public void setElement(final IAdaptable element) {
    this.project = (IProject) element.getAdapter(IProject.class);
  }

  /**
   * Gets the element.
   *
   * @return the element {@inheritDoc}
   */
  @Override
  public IAdaptable getElement() {
    return project;
  }

  /**
   * Gets the language name.
   *
   * @return the language name
   */
  protected String getLanguageName() {
    return this.languageName;
  }

  /**
   * Gets the file extensions.
   *
   * @return the file extensions
   */
  protected String getFileExtensions() {
    return this.fileExtensions;
  }

  /**
   * Gets the preference key under which the rule disabled/enabled preference
   * is stored.
   *
   * @param item
   *          the item
   * @return the preference key
   */
  private String getPreferenceKey(final IPreferenceItem item) {
    return getLanguageName() + ValidPreferenceConstants.PREFERENCE_SEPARATOR + item.getKey() + ValidPreferenceConstants.PREFERENCE_SEPARATOR + "disabled"; //$NON-NLS-1$
  }

  /**
   * Perform OK button.
   *
   * @return true, if successful {@inheritDoc}
   */
  @Override
  public boolean performOk() {
    try {
      if (preferences.needsSaving()) {
        preferences.save();
      }
    } catch (final IOException e) {
      LOGGER.error("Unable to save general preference page preferences", e); //$NON-NLS-1$
    }

    boolean preferenceChanged = false;
    for (final IPreferenceItem item : (IPreferenceItem[]) ((ValidModelTreeContentProvider) treeViewer.getContentProvider()).getElements(null)) {
      preferenceChanged = preferenceChanged | preferenceChanged(item);
    }

    if (preferenceChanged) {
      final MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(this.getShell(), REVALIDATE_DIALOG_TITLE, REVALIDATE_DIALOG_MESSAGE, EXPENSIVE_CHECKS_TOGGLE_MESSAGE, getPreferenceStore().getBoolean(PERFORM_EXPENSIVE_VALIDATION), null, null);

      final boolean yesWeCan = (dialog.getReturnCode() == IDialogConstants.YES_ID);
      final boolean performExpensiveValidations = dialog.getToggleState();

      getPreferenceStore().setValue(PERFORM_EXPENSIVE_VALIDATION, dialog.getToggleState());

      if (yesWeCan) {
        final ValidMarkerUpdateJob updateJob = new ValidMarkerUpdateJob(VALIDATION_JOB_MESSAGE, this.fileExtensions, this.resourceSet, this.markerCreator, this.resourceDescriptions, this.resourceServiceProvider, performExpensiveValidations, storage2UriMapper);
        // The following gets a global lock on all updateJob, no matter what the language is.
        // The reason is that we want to avoid the update job to consume too much memory, but
        // we may decide to change that to a lock on a language basis...
        updateJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
        updateJob.schedule();
      }

    }

    return super.performOk();
  }

  /**
   * Reacts to the OK event for a given preference item (category/rule).
   *
   * @param item
   *          the item
   * @return true, if the preferences have changed with regards to the preferences previously stored
   */
  public boolean preferenceChanged(final IPreferenceItem item) {
    boolean preferenceChanged = false;
    if (item instanceof PreferenceRule) {
      final String key = getPreferenceKey(item);
      // we store whether a key is DISABLED, not whether it is enabled!
      final boolean oldPreferencevalue = getPreferenceStore().getBoolean(key);
      final boolean newPreferenceValue = !treeViewer.getChecked(item);
      if (oldPreferencevalue != newPreferenceValue) {
        getPreferenceStore().setValue(key, newPreferenceValue);
        preferenceChanged = true;
      }
    } else if (item instanceof PreferenceCategory) {
      for (final IPreferenceItem containedItem : ((PreferenceCategory) item).getRules()) {
        preferenceChanged = preferenceChanged | preferenceChanged(containedItem);
      }
    }
    return preferenceChanged;
  }

  /**
   * Reacts to the "Defaults" events for a given preference item
   * (category/rule). All rules are mandatory by default.
   *
   * @param item
   *          the item
   */
  public void internalPerformDefaults(final IPreferenceItem item) {
    if (item instanceof PreferenceRule) {
      treeViewer.setChecked(item, true);
    } else if (item instanceof PreferenceCategory) {
      for (final IPreferenceItem containedItem : ((PreferenceCategory) item).getRules()) {
        internalPerformDefaults(containedItem);
      }
    }
  }

  /**
   * Perform Defaults button. {@inheritDoc}
   */
  @Override
  protected void performDefaults() {
    for (final IPreferenceItem item : (IPreferenceItem[]) ((ValidModelTreeContentProvider) treeViewer.getContentProvider()).getElements(null)) {
      internalPerformDefaults(item);
    }
    super.performDefaults();
  }

  /**
   * Creates the contents.
   *
   * @param parent
   *          the parent
   * @return the control {@inheritDoc}
   */
  @Override
  protected Control createContents(final Composite parent) {
    final Composite container = new Composite(parent, SWT.NULL);
    final GridLayout layout = new GridLayout();
    layout.numColumns = GRID_COLUMNS;
    container.setLayout(layout);

    treeViewer = new ValidPreferenceCheckedTreeViewer(container);
    treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(final SelectionChangedEvent event) {
        description.setText(""); //$NON-NLS-1$
        message.setText(""); //$NON-NLS-1$
        severityImage.setImage(null);
        severity.setText(""); //$NON-NLS-1$
        evaluationMode.setText(""); //$NON-NLS-1$
        final Object selection = ((TreeSelection) event.getSelection()).getFirstElement();
        if (selection instanceof IPreferenceItem) {
          description.setText(((IPreferenceItem) selection).getDescription());
          severityImage.setImage(((IPreferenceItem) selection).getUndecoratedImage());
          if (selection instanceof PreferenceCategory) {
            severity.setText("Category"); //$NON-NLS-1$
          } else if (selection instanceof PreferenceRule) {
            final PreferenceRule rule = (PreferenceRule) selection;
            message.setText(rule.getMessage());
            severity.setText(rule.getSeverity().toString());
            evaluationMode.setText(rule.getEvaluationMode().toString());
          }
        }
      }
    });

    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, GRID_COLUMNS, 1);
    gridData.grabExcessVerticalSpace = true;
    treeViewer.getControl().setLayoutData(gridData);
    treeViewer.setContentProvider(new ValidModelTreeContentProvider(this));
    treeViewer.setLabelProvider(new ValidModelTreeLabelProvider());
    treeViewer.setUseHashlookup(true);

    final Label severityLabel = new Label(container, SWT.NONE);
    severityLabel.setText("Kind:"); //$NON-NLS-1$
    severityLabel.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1));

    severityImage = new Label(container, SWT.NONE);
    gridData = new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1);
    gridData.widthHint = SEVERITY_IMAGE_WIDTH_HINT;
    severityImage.setLayoutData(gridData);
    severity = new Label(container, SWT.NONE);
    severity.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));

    final Label evaluationModeLabel = new Label(container, SWT.NONE);
    evaluationModeLabel.setText("Evaluation mode:"); //$NON-NLS-1$
    evaluationModeLabel.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1));

    evaluationMode = new Label(container, SWT.NONE);
    evaluationMode.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));

    final Label descriptionLabel = new Label(container, SWT.NONE);
    descriptionLabel.setText("Description:"); //$NON-NLS-1$
    descriptionLabel.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, GRID_COLUMNS, 1));
    // CHECKSTYLE:OFF
    description = new Text(container, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
    // CHECKSTYLE:ON
    description.setEditable(false);
    gridData = new GridData(SWT.FILL, SWT.LEFT, true, false, GRID_COLUMNS, 1);
    gridData.heightHint = DESCRIPTION_HEIGHT_HINT;
    description.setLayoutData(gridData);

    final Label messageLabel = new Label(container, SWT.NONE);
    messageLabel.setText("Message:"); //$NON-NLS-1$
    messageLabel.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, GRID_COLUMNS, 1));

    // CHECKSTYLE:OFF
    message = new Text(container, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
    // CHECKSTYLE:ON
    message.setEditable(false);
    gridData = new GridData(SWT.FILL, SWT.LEFT, true, false, GRID_COLUMNS, 1);
    gridData.heightHint = MESSAGE_HEIGHT_HINT;
    message.setLayoutData(gridData);

    return container;
  }

  /**
   * Update the content before becoming visible.
   *
   * @param visible
   *          the visible
   */
  @Override
  public void setVisible(final boolean visible) {
    if (visible) {
      treeViewer.setInput("root"); //$NON-NLS-1$ // pass a non-null that will be ignored

      treeViewer.expandAll(); // this is dumb, but required if we want the
      for (final TreeItem item : treeViewer.getTree().getItems()) {
        initialize(item);
      }
      // initial grayed states to be calculated...
      treeViewer.collapseAll();
    }
    super.setVisible(visible);
  }

  /**
   * Initializes a given TreeViewer "widget" item according to the tree viewer
   * content provider model.
   *
   * @param item
   *          the item to initialize
   */
  private void initialize(final TreeItem item) {
    boolean containsChecked = false;
    boolean containsUnchecked = false;

    if (item.getData() instanceof PreferenceRule) {
      if (((PreferenceRule) item.getData()).isOptional()) {
        item.setChecked(!getPreferenceStore().getBoolean(getPreferenceKey(((PreferenceRule) item.getData()))));
      } else {
        item.setGrayed(true);
        item.setChecked(true);
      }
    } else {
      for (final TreeItem child : item.getItems()) {
        initialize(child);
        containsChecked |= child.getChecked();
        containsUnchecked |= (!child.getChecked() || child.getGrayed());
      }
      item.setChecked(containsChecked);
      item.setGrayed(containsChecked && containsUnchecked);
    }
  }

  /**
   * Replace the localization patterns ({0}, {1}, ...) with "..." inside the
   * source string.
   *
   * @param source
   *          the source
   * @param searchPattern
   *          the search pattern
   * @param replacementPattern
   *          the replacement pattern
   * @return the string
   */
  public static String replace(final String source, final String searchPattern, final String replacementPattern) {
    // Compile regular expression
    final Pattern pattern = Pattern.compile(searchPattern);

    // Replace all occurrences of pattern in input
    final Matcher matcher = pattern.matcher(source);
    return matcher.replaceAll(replacementPattern);
  }

}
