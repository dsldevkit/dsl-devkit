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
package com.avaloq.tools.ddk.check.ui.wizard;

import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.CompilationUnit;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.ui.wizards.NewTypeWizardPage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.xtext.Grammar;

import com.avaloq.tools.ddk.check.CheckConstants;
import com.avaloq.tools.ddk.check.ui.util.CheckResourceUtil;
import com.avaloq.tools.ddk.check.ui.util.FieldInitializerUtil;
import com.avaloq.tools.ddk.check.util.GrammarHelper;
import com.avaloq.tools.ddk.check.validation.CheckJavaValidatorUtil;
import com.google.common.collect.Sets;
import com.google.inject.Inject;


/**
 * Displays a {@link org.eclipse.jface.wizard.WizardPage page} for the {@link com.avaloq.tools.ddk.check.ui.wizard.NewCheckFileWizard
 * new Check Catalog wizard}.
 */
// CHECKSTYLE:OFF (data coupling)
@SuppressWarnings("restriction")
public class NewCheckCatalogWizardPage extends NewTypeWizardPage {
  // CHECKSTYLE:ON

  private static final int PAGE_WIDTH_HINT = 200;

  /** The Constant DEFAULT_COLUMN_NUMBER. */
  private static final int DEFAULT_COLUMN_NUMBER = 4;

  /** Field ID of the grammar input field. */
  protected static final String GRAMMAR = "NewCheckCatalogWizardPage" + ".grammar"; // TODO NLS???

  /** The resource. */
  private IResource resource;

  /** The field initializer utility. Sets source folder and package name as selected in the UI. */
  @Inject
  private FieldInitializerUtil fieldInitializerUtil;

  /** The resource utility gets defaults for the combo fields. */
  @Inject
  private CheckResourceUtil resourceUtil;

  /** The grammar status. */
  private IStatus grammarStatus;

  /** The selected element. */
  private IJavaElement element;

  private Grammar grammar;

  private final CheckProjectInfo projectInfo;

  /** Is anything selected for the file creation. */
  private final StatusInfo selectionStatus = new StatusInfo();

  /** Validator for input validation. */
  @Inject
  private CheckJavaValidatorUtil validator;

  /**
   * Instantiates a new new check catalog wizard page.
   */
  @Inject
  public NewCheckCatalogWizardPage() {
    super(NewTypeWizardPage.CLASS_TYPE, "NewCheckCatalogWizardPage");
    setTitle(Messages.CATALOG_WIZARD_TITLE);
    setDescription(Messages.CATALOG_WIZARD_DESCRIPTION);
    projectInfo = new CheckProjectInfo();
    grammarStatus = new StatusInfo();
  }

  /** {@inheritDoc} */
  @Override
  protected String getTypeNameLabel() {
    return Messages.CATALOG_FIELD_NAME_LABEL;
  }

  /**
   * Gets the catalog name.
   * 
   * @return the catalog name
   */
  public String getCatalogName() {
    return getTypeName();
  }

  /**
   * Gets the resource.
   * 
   * @return the resource
   */
  public IResource getResource() {
    return resource;
  }

  /**
   * Gets the package name.
   * 
   * @return the package name
   */
  public String getPackageName() {
    return getPackageText();
  }

  /** {@inheritDoc} */
  @Override
  protected String getPackageLabel() {
    return Messages.PACKAGE_NAME_LABEL;
  }

  public Grammar getGrammar() {
    return grammar;
  }

  /**
   * Validate grammar.
   * 
   * @return the status (ERROR or OK)
   */
  private IStatus validateGrammarAccess() {
    StatusInfo status = new StatusInfo();
    if (getGrammar() == null || (getGrammar().getName().length() == 0)) {
      status.setError(Messages.CHOOSE_GRAMMAR_ID);
    }
    // update Check ProjectInfo
    if (status.isOK()) {
      projectInfo.setGrammar(getGrammar());
    }
    return status;
  }

  /** {@inheritDoc} */
  public void createControl(final Composite parent) {
    initializeDialogUnits(parent);

    final Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.numColumns = DEFAULT_COLUMN_NUMBER;
    composite.setLayout(layout);
    composite.setFont(parent.getFont());

    // Create controls
    createPackageControls(composite, DEFAULT_COLUMN_NUMBER);
    createSeparator(composite, DEFAULT_COLUMN_NUMBER);

    createTypeNameControls(composite, DEFAULT_COLUMN_NUMBER);
    createGrammarsControls(composite);

    // If no project was selected for file creation, disable the controls of the file wizard
    // and state an Error in the header.
    if (selectionStatus.matches(IStatus.ERROR)) {
      Control[] children = composite.getChildren();
      for (Control child : children) {
        child.setEnabled(false);
      }
      this.setDescription(selectionStatus.getMessage());
      // TODO set an information icon
      // ImageDescriptor image = ImageDescriptor.createFromImage(IconAndMessageDialog.getInfoImage());
      // this.setImageDescriptor(image);
    }
    setPageComplete(false);
    setControl(composite);
  }

  /**
   * Creates the grammars controls.
   * 
   * @param composite
   *          the composite
   */
  private void createGrammarsControls(final Composite composite) {
    Label generatorConfigLabel = new Label(composite, SWT.NONE);
    generatorConfigLabel.setText(Messages.GRAMMAR_FIELD_NAME_LABEL);

    Combo generatorConfigurationField = new Combo(composite, SWT.READ_ONLY);
    GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
    data.widthHint = PAGE_WIDTH_HINT; // prevents shrinking of combo view
    generatorConfigurationField.setLayoutData(data);
    generatorConfigurationField.setFont(composite.getFont());

    ComboViewer grammars = new ComboViewer(generatorConfigurationField);
    grammars.setContentProvider(new ArrayContentProvider());
    grammars.setLabelProvider(new LabelProvider() {
      @Override
      public String getText(final Object object) {
        if (object instanceof Grammar) {
          return new GrammarHelper((Grammar) object).getLabelName();
        }
        return super.getText(object);
      }
    });
    grammars.setInput(resourceUtil.getGrammars());

    // React to the selection of the viewer
    // Note that the viewer return the real object and not just a string
    // representation
    grammars.addSelectionChangedListener(new ISelectionChangedListener() {
      public void selectionChanged(final SelectionChangedEvent event) {
        if (event.getSelection() instanceof StructuredSelection) {
          grammar = (Grammar) ((StructuredSelection) event.getSelection()).getFirstElement();
          grammarStatus = validateGrammarAccess();
          handleFieldChanged(GRAMMAR);
        }
      }
    });
    grammars.setSelection(null); // no pre-defined grammar to be used, forces the user to make a decision about which language to target
  }

  /**
   * Initializes the page with selected elements.
   * 
   * @param selection
   *          the selection
   */
  protected void init(final IStructuredSelection selection) {
    // check if anything is selected at all; note that it is currently possible to create a new
    // check file even if there is no project in the workspace (it is created in the External Plug-in Libraries
    // directory of the workspace root)
    if (!selection.isEmpty()) {
      element = fieldInitializerUtil.getSelectedResource(selection);
    } else {
      selectionStatus.setError(Messages.NO_PROJECT_DEFINED);
    }
  }

  @Override
  public void setVisible(final boolean visible) {
    if (visible) {
      // don't initialize this page before it becomes visible because -if a project shall be created - it needs
      // information from the first page for its initialization.
      if (previousPageIsProjectPage()) {
        setTitle(Messages.PROJECT_WIZARD_TITLE);
      } else {
        setTitle(Messages.CATALOG_WIZARD_TITLE);
      }
      initContainerPage(element);
      initTypePage(element);
    }

    super.setVisible(visible);
    setFocus();
  }

  /**
   * ensures that an entered value is still inserted in the field after a page change ("< back", "next >").
   * 
   * @param catalogName
   *          the catalog name to be set
   * @param canBeModified
   *          if <code>true</code> the package fragment is
   *          editable; otherwise it is read-only.
   */
  @Override
  public void setTypeName(final String catalogName, final boolean canBeModified) {
    String catalog = catalogName;
    if (getCatalogName().length() != 0) {
      catalog = getCatalogName();
    }
    super.setTypeName(catalog, canBeModified);
  }

  /**
   * Sets the package fragment. Sets an initial package, if possible.
   * 
   * @param pack
   *          the package fragment to be set
   * @param canBeModified
   *          if <code>true</code> the package fragment is
   *          editable; otherwise it is read-only
   */
  @Override
  public void setPackageFragment(final IPackageFragment pack, final boolean canBeModified) {
    super.setPackageFragment(getPackageFragment(), canBeModified);
  }

  /**
   * Creates a fragment with the same name as the project name.
   * It is used to show an initial package name in the project wizard.
   * 
   * @param name
   *          name of the initial package
   * @return the initial package fragment
   */
  private IPackageFragment createInitialFragment(final String name) {
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(((NewCheckProjectWizardPage) getPreviousPage()).getProjectName());
    IFolder src = project.getFolder(com.avaloq.tools.ddk.check.ui.wizard.CheckProjectCreator.SRC_ROOT);
    IJavaProject jProject = JavaCore.create(project);
    IPackageFragmentRoot root = jProject.getPackageFragmentRoot(src);
    return root.getPackageFragment(name);
  }

  /**
   * Checks if the previous page is the first page of the new project wizard.
   * 
   * @return true, if the previous page is the project wizard's first page
   */
  private boolean previousPageIsProjectPage() {
    return getPreviousPage() instanceof NewCheckProjectWizardPage;
  }

  /**
   * Do status update.
   */
  private void doStatusUpdate() {
    IStatus[] status = new IStatus[] {fPackageStatus, fTypeNameStatus, grammarStatus};
    updateStatus(status);
  }

  /** {@inheritDoc} */
  @Override
  protected void handleFieldChanged(final String fieldName) {
    super.handleFieldChanged(fieldName);
    grammarStatus = validateGrammarAccess();
    doStatusUpdate();
  }

  /**
   * Gets the output file name.
   * 
   * @return the output file name
   */
  public String getOutputFileName() {
    return getTypeName() + '.' + CheckConstants.FILE_EXTENSION;
  }

  /**
   * Gets the output folder. Returns <code>null</code> if no package has been specified.
   * 
   * @return the output folder or <code>null</code>
   */
  public IFolder getOutputFolder() {
    if (getPackageFragment() == null) {
      return null;
    }
    return (IFolder) getPackageFragment().getResource();
  }

  /**
   * Gets the project info.
   * 
   * @return the project info
   */
  public CheckProjectInfo getProjectInfo() {
    return projectInfo;
  }

  /** {@inheritDoc} */
  @Override
  protected IStatus packageChanged() {
    super.packageChanged(); // makes modifications to read-only field in parent class; required!
    IStatus status = validator.checkPackageName(getPackageName());
    if (status.isOK()) {
      projectInfo.setPackageName(getPackageName());
    }
    return status;
  }

  /**
   * Ensures that the project creation is done with the current package fragment.
   * Especially when the package name is changed.
   * 
   * @return the current package fragment.
   */
  @Override
  public IPackageFragment getPackageFragment() {
    IPackageFragment packageFragment = super.getPackageFragment();
    if (previousPageIsProjectPage()) {

      if (getPackageText() == null || getPackageText().length() == 0) {
        packageFragment = createInitialFragment(((NewCheckProjectWizardPage) getPreviousPage()).getProjectName());
      } else {
        packageFragment = createInitialFragment(getPackageText());
      }
    } else if (this.element instanceof IPackageFragment && packageFragment.getElementName().length() == 0) {
      // initially, get the selected package (if selected).
      packageFragment = (IPackageFragment) this.element;
    } else if (this.element instanceof CompilationUnit && packageFragment.getElementName().length() == 0) {
      // This happens if a Java file is selected
      if (((CompilationUnit) this.element).getParent() instanceof IPackageFragment) { // NOPMD
        packageFragment = (IPackageFragment) ((CompilationUnit) this.element).getParent();
      }
    }
    // update projectInfo with current package fragment
    projectInfo.setPackageFragment(packageFragment);
    return packageFragment;
  }

  /** {@inheritDoc} */
  @Override
  public IStatus typeNameChanged() {
    super.typeNameChanged();
    IStatus status = validator.checkCatalogName(getCatalogName());

    if (!previousPageIsProjectPage()) {

      IPackageFragment packageFragment = getPackageFragment();

      if (packageFragment != null && catalogExists(packageFragment.getResource())) {
        return new Status(IStatus.ERROR, status.getPlugin(), NLS.bind(com.avaloq.tools.ddk.check.validation.Messages.CheckJavaValidator_CATALOG_NAME_STATUS, com.avaloq.tools.ddk.check.validation.Messages.CheckJavaValidator_EXISTS));
      }
    }
    if (!status.matches(IStatus.ERROR)) {
      projectInfo.setCatalogName(getCatalogName());
    }
    return status;
  }

  /**
   * Checks if a given catalog name already exists in the project.
   * 
   * @param packageFragment
   *          the package in which the file is looked for
   * @return true, if catalog exists
   */
  private boolean catalogExists(final IResource packageFragment) {
    final Set<IResource> foundResources = Sets.newHashSet();
    final String catalogName = getCatalogName() + '.' + CheckConstants.FILE_EXTENSION;
    IResourceVisitor catalogNameVisitor = new IResourceVisitor() {
      public boolean visit(final IResource res) throws CoreException {
        String resourceName = res.getName();
        if (catalogName.equalsIgnoreCase(resourceName)) {
          foundResources.add(res);
        }
        return foundResources.isEmpty();
      }
    };
    try {
      packageFragment.accept(catalogNameVisitor);
      return !foundResources.isEmpty();
    } catch (CoreException e) {
      // packageFragment does not yet exist. Therefore, the catalog name is unique.
      return false;
    }
  }

  /**
   * Does not set the message to "OK" when there is no error.
   * <p>
   * Is a copy of com.avaloq.tools.ddk.check.ui.wizard.NewCheckProjectWizardPage.updateStatus(IStatus).
   * </p>
   * <p>
   * {@inheritDoc}
   */
  @Override
  protected void updateStatus(final IStatus status) {
    IStatus result = status;
    if (status.isOK()) {
      // avoid setting the message to "OK" when there is no problem
      if (previousPageIsProjectPage()) {
        result = new Status(status.getSeverity(), status.getPlugin(), Messages.PROJECT_WIZARD_COMPLETE);
      } else {
        result = new Status(status.getSeverity(), status.getPlugin(), Messages.CATALOG_WIZARD_COMPLETE);
      }
    }
    super.updateStatus(result);
  }
}

