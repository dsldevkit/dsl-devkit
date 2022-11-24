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
package com.avaloq.tools.ddk.check.ui.builder;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.pde.core.IBaseModel;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPlugin;
import org.eclipse.pde.core.plugin.IPluginAttribute;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginElement;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.TargetPlatformHelper;
import org.eclipse.pde.internal.core.plugin.Plugin;
import org.eclipse.pde.internal.core.plugin.PluginAttribute;
import org.eclipse.pde.internal.core.plugin.PluginElement;
import org.eclipse.pde.internal.core.plugin.PluginExtension;
import org.eclipse.pde.internal.core.plugin.PluginHandler;
import org.eclipse.pde.internal.core.plugin.WorkspaceFragmentModel;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModel;
import org.eclipse.pde.internal.core.plugin.WorkspacePluginModelBase;
import org.eclipse.pde.internal.core.project.PDEProject;
import org.eclipse.pde.internal.ui.PDEPlugin;
import org.eclipse.pde.internal.ui.util.ModelModification;
import org.eclipse.pde.internal.ui.util.PDEModelUtility;
import org.eclipse.swt.SWTException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.IBuildContext;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.ui.resource.IStorage2UriMapper;
import org.eclipse.xtext.util.MergeableManifest2;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.avaloq.tools.ddk.check.check.CheckCatalog;
import com.avaloq.tools.ddk.check.check.CheckPackage;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckExtensionHelperManager;
import com.avaloq.tools.ddk.check.ui.builder.util.CheckProjectHelper;
import com.avaloq.tools.ddk.check.ui.internal.Activator;
import com.avaloq.tools.ddk.check.util.GrammarHelper;
import com.avaloq.tools.ddk.xtext.ui.util.RuntimeProjectUtil;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.inject.Inject;


/**
 * The class responsible for the manipulation of plugin.xml extensions.
 */

// CHECKSTYLE:OFF (abstraction coupling)
@SuppressWarnings({"restriction"})
class CheckExtensionGenerator {
  // CHECKSTYLE:ON
  private static final Logger LOGGER = LogManager.getLogger(CheckExtensionGenerator.class);

  public static final String PREFERENCE_PLUGIN_XML_FILENAME = "PluginXmlFilename";
  public static final String STANDARD_PLUGIN_FILENAME = ICoreConstants.PLUGIN_FILENAME_DESCRIPTOR;
  public static final String STANDARD_FRAGMENT_FILENAME = ICoreConstants.FRAGMENT_FILENAME_DESCRIPTOR;

  @Inject
  private CheckProjectHelper projectHelper;

  @Inject
  private CheckExtensionHelperManager manager;

  @Inject
  private IStorage2UriMapper mapper;

  /**
   * A plug-in model that can be loaded/saved from a file.
   */
  protected static final class CheckWorkspacePluginModel extends WorkspacePluginModel {

    private static final long serialVersionUID = 1L;

    public CheckWorkspacePluginModel(final IFile file, final boolean abbreviated) {
      super(file, abbreviated);
    }

    @Override
    public IPluginBase createPluginBase() {
      Plugin plugin = new CheckPlugin(!isEditable());
      plugin.setModel(this);
      return plugin;
    }

    @Override
    public IPlugin getPlugin() {
      return (IPlugin) getPluginBase();
    }

    @Override
    public IPluginAttribute createAttribute(final IPluginElement element) {
      CheckPluginAttribute attribute = new CheckPluginAttribute();
      attribute.setModel(this);
      attribute.setParent(element);
      return attribute;
    }

  }

  /**
   * A {@code Plugin} that can load/save plugin xml from file other than "plugin.xml".
   */
  protected static final class CheckPlugin extends Plugin {

    private static final long serialVersionUID = 1L;

    public CheckPlugin(final boolean readOnly) {
      super(readOnly);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected void processChild(final Node child) {
      super.processChild(child);
      String name = child.getNodeName().toLowerCase(Locale.ENGLISH);
      if ("extension".equals(name)) {
        if (fExtensions == null) {
          fExtensions = new ArrayList();
        }
        CheckPluginExtension extension = new CheckPluginExtension();
        extension.setModel(getModel());
        extension.setParent(this);
        fExtensions.add(extension);
        extension.setInTheModel(true);
        extension.checkLoad(child);
      }
    }

  }

  /**
   * A {@code PluginExtension} that can be loaded from an xml file.
   */
  protected static final class CheckPluginExtension extends PluginExtension {

    private static final long serialVersionUID = 1L;

    /**
     * Gets the content of a PluginAttribute from an xml node.
     *
     * @param node
     *          the node to get the plugin attribute from
     * @param name
     *          the name of the attribute
     * @return string
     */
    String getCheckNodeAttribute(final Node node, final String name) {
      Node attribute = node.getAttributes().getNamedItem(name);
      if (attribute != null) {
        return attribute.getNodeValue();
      }
      return null;
    }

    /**
     * Load a plug-in extension from a node in a plugin xml file.
     * <p>
     * Mirrors the package-level-access method {@code load} from {@code PluginExtension}
     * </p>
     *
     * @param node
     *          the node to load from
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void checkLoad(final Node node) {
      this.fID = getCheckNodeAttribute(node, "id"); //$NON-NLS-1$
      fName = getCheckNodeAttribute(node, "name"); //$NON-NLS-1$
      fPoint = getCheckNodeAttribute(node, "point"); //$NON-NLS-1$

      if (fChildren == null) {
        fChildren = new ArrayList();
      }
      NodeList children = node.getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        Node child = children.item(i);
        if (child.getNodeType() == Node.ELEMENT_NODE) {
          CheckPluginElement childElement = new CheckPluginElement();
          childElement.setModel(getModel());
          childElement.setInTheModel(true);
          childElement.setParent(this);
          this.fChildren.add(childElement);
          childElement.checkLoad(child);
        }
      }
      fStartLine = Integer.parseInt(getCheckNodeAttribute(node, "line")); //$NON-NLS-1$
    }

    /**
     * A {@code PluginElement} that can be loaded from an xml file.
     */
    protected class CheckPluginElement extends PluginElement {

      private static final long serialVersionUID = 1L;

      /**
       * Checks that a text string is not empty, i.e., whitespace.
       *
       * @param text
       *          the text to check
       * @return
       *         true if {@code text} is not whitespace
       */
      boolean checkIsNotEmpty(final String text) {
        for (int i = 0; i < text.length(); i++) {
          if (!(Character.isWhitespace(text.charAt(i)))) {
            return true;
          }
        }
        return false;
      }

      /**
       * Loads a plugin element.
       *
       * @param node
       *          the xml node to load it from.
       */
      @SuppressWarnings({"unchecked", "rawtypes"})
      void checkLoad(final Node node) {
        fName = node.getNodeName();
        if (fAttributes == null) {
          fAttributes = new Hashtable();
        }
        NamedNodeMap attributes = node.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
          Node attribute = attributes.item(i);
          IPluginAttribute att = getModel().getFactory().createAttribute(this);
          ((CheckPluginAttribute) att).checkLoad(attribute);
          ((PluginAttribute) att).setInTheModel(true);
          this.fAttributes.put(attribute.getNodeName(), att);
        }

        if (fChildren == null) {
          fChildren = new ArrayList();
        }
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
          Node child = children.item(i);
          if (child.getNodeType() == Node.ELEMENT_NODE) {
            CheckPluginElement childElement = new CheckPluginElement();
            childElement.setModel(getModel());
            childElement.setInTheModel(true);
            this.fChildren.add(childElement);
            childElement.setParent(this);
            childElement.checkLoad(child);
          } else if (child.getNodeType() == Node.TEXT_NODE && child.getNodeValue() != null) {
            String text = child.getNodeValue();
            text = text.trim();
            if (checkIsNotEmpty(text)) {
              this.fText = text;
            }
          }
        }
      }

    }

  }

  /**
   * A plug-in attribute for an extension.
   */
  protected static final class CheckPluginAttribute extends PluginAttribute {

    private static final long serialVersionUID = 1L;

    /**
     * Load an attribute from a node in a plug-in xml file.
     * <p>
     * Mirrors the package-level-access method {@code load} from {@code PluginAttribute}
     * </p>
     *
     * @param node
     *          the node to load from
     */
    void checkLoad(final Node node) {
      fName = node.getNodeName();
      fValue = node.getNodeValue();
    }

  }

  /**
   * Add an extension to the <code>plugin.xml</code> file.
   *
   * @param context
   *          build context
   * @param delta
   *          resource delta
   * @param monitor
   *          progress monitor
   * @throws CoreException
   *           the core exception
   */
  public void changePluginXmlFile(final IBuildContext context, final Delta delta, final IProgressMonitor monitor) throws CoreException {
    URI uri = delta.getUri();
    CheckCatalog catalog = projectHelper.getCatalog(context, uri);
    if (catalog == null) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.getPluginId(), IStatus.ERROR, "No Catalog found", null)); //$NON-NLS-1$
    }

    IFile file = getPluginFile(uri);
    if (!validPluginFile(file)) {
      createNewFile(catalog, file, monitor);
    } else {
      if (!catalogValidates(catalog)) {
        removeExtensions(file, catalog, monitor);
      } else {
        updateExtensions(file, catalog, monitor);
        // TODO improve by only updating the manifest if the validator extension has changed..
      }
    }
    mergeManifest(catalog, monitor);
  }

  /**
   * Catalog validates.
   *
   * @param catalog
   *          the catalog
   * @return true, if successful
   */
  private boolean catalogValidates(final CheckCatalog catalog) {
    EValidator.Registry eValidatorRegistry = EValidator.Registry.INSTANCE;
    EValidator eValidator = eValidatorRegistry.getEValidator(catalog.eClass().getEPackage());
    BasicDiagnostic diag = new BasicDiagnostic();
    eValidator.validate(catalog, diag, Maps.newHashMap());
    Iterable<Diagnostic> children = diag.getChildren();
    Iterable<Diagnostic> errors = Iterables.filter(children, new Predicate<Diagnostic>() {
      @Override
      public boolean apply(final Diagnostic input) {
        return input.getSeverity() == Diagnostic.ERROR;
      }
    });
    return Iterables.isEmpty(errors);
  }

  /**
   * Modifies an existing <code>plugin.xml</code> file.
   *
   * @param pluginXmlFile
   *          the plugin.xml file to modify
   * @param catalog
   *          the check catalog
   * @param monitor
   *          progress monitor
   * @throws CoreException
   *           if an error occurred while modifying the plugin.xml file
   * @see org.eclipse.pde.internal.ui.wizards.product.ProductIntroOperation
   */
  private void updateExtensions(final IFile pluginXmlFile, final CheckCatalog catalog, final IProgressMonitor monitor) throws CoreException {
    IStatus status = PDEPlugin.getWorkspace().validateEdit(new IFile[] {pluginXmlFile}, null); // null means don't contact me...
    if (!status.isOK()) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.getPluginId(), IStatus.ERROR, "Could not update plugin extensions", null));
    }
    if (!STANDARD_PLUGIN_FILENAME.equals(pluginXmlFile.getName())) {
      WorkspacePluginModel pluginModel = new CheckWorkspacePluginModel(pluginXmlFile, false);
      IPluginBase base = pluginModel.getPluginBase();
      base.setSchemaVersion(TargetPlatformHelper.getSchemaVersion());
      pluginModel.load();
      manager.updateExtensions(catalog, pluginModel, monitor);
      manager.addExtensions(catalog, pluginModel, monitor);
      manager.sortAllExtensions(pluginModel, monitor);
      pluginModel.save();
    } else {
      final ModelModification modification = new ModelModification(pluginXmlFile) {
        @Override
        protected void modifyModel(final IBaseModel model, final IProgressMonitor monitor) throws CoreException {// NOPMD NPath complexity
          if (!(model instanceof IPluginModelBase)) {
            return;
          }
          IPluginModelBase pluginModel = (IPluginModelBase) model;
          manager.updateExtensions(catalog, pluginModel, monitor);
          manager.addExtensions(catalog, pluginModel, monitor);
          manager.sortAllExtensions(pluginModel, monitor);
        }

        @Override
        public boolean saveOpenEditor() {
          return false; // do nothing with any open editor...
        }
      };
      modifyModel(modification, monitor);
    }
  }

  /**
   * Removes extensions registered for a check catalog. This operation has extension point specific behavior.
   * Validator, quickfix and preference extensions can safely be removed without any checking. The marker help
   * extension, however, contains elements from all catalogs of current project. Therefore only elements belonging
   * to given catalog may be removed, and not the whole extension. Table of contents and contexts extensions are
   * never removed once created - they are not catalog specific and do not harm.
   *
   * @param file
   *          the plugin.xml file to be modified
   * @param object
   *          the object
   * @param monitor
   *          a progress monitor
   * @throws CoreException
   *           if the file is read-only and cannot be edited
   */
  private void removeExtensions(final IFile file, final Object object, final IProgressMonitor monitor) throws CoreException {
    IStatus status = PDEPlugin.getWorkspace().validateEdit(new IFile[] {file}, null); // null means don't contact me...
    if (status.getSeverity() != IStatus.OK) {
      throw new CoreException(new Status(IStatus.ERROR, Activator.getPluginId(), IStatus.ERROR, "Could not remove plugin extensions", null));
    }

    if (!STANDARD_PLUGIN_FILENAME.equals(file.getName())) {
      WorkspacePluginModel pluginModel = new CheckWorkspacePluginModel(file, false);
      IPluginBase base = pluginModel.getPluginBase();
      base.setSchemaVersion(TargetPlatformHelper.getSchemaVersion());
      pluginModel.load();
      if (object instanceof IEObjectDescription) {
        manager.removeExtensions((IEObjectDescription) object, pluginModel, monitor); // called when catalog is deleted
      } else if (object instanceof CheckCatalog) {
        manager.removeExtensions((CheckCatalog) object, pluginModel, monitor); // called in order to remove extensions in an invalid model
      }
      pluginModel.save();
    } else {
      final ModelModification mod = new ModelModification(file) {
        @Override
        protected void modifyModel(final IBaseModel model, final IProgressMonitor monitor) throws CoreException {
          if (!(model instanceof IPluginModelBase)) {
            return;
          }
          IPluginModelBase pluginModel = (IPluginModelBase) model;
          if (object instanceof IEObjectDescription) {
            manager.removeExtensions((IEObjectDescription) object, pluginModel, monitor); // called when catalog is deleted
          } else if (object instanceof CheckCatalog) {
            manager.removeExtensions((CheckCatalog) object, pluginModel, monitor); // called in order to remove extensions in an invalid model
          }
        }

        @Override
        public boolean saveOpenEditor() {
          return false; // prevent modifications to open plugin manifest editor; the editor will become dirty once the modification is performed
        }
      };
      modifyModel(mod, monitor);
    }
  }

  /**
   * Merges the {@code MANIFEST.MF} file with new plug-in dependencies. Adds dependencies as returned by
   *
   * @param catalog
   *          check catalog
   * @param monitor
   *          progress monitor {@link com.avaloq.tools.ddk.check.util.GrammarHelper#getRequiredBundleSymbolicNames()} of current
   *          check catalog to the MANIFEST.MF file.
   */
  private void mergeManifest(final CheckCatalog catalog, final IProgressMonitor monitor) {
    final IProject project = RuntimeProjectUtil.getProject(catalog.eResource().getURI(), mapper);
    final IFile file = PDEProject.getManifest(project);

    if (file.exists() && catalog.getGrammar() != null) {
      InputStream fileContents = null;
      try {
        fileContents = file.getContents();
        MergeableManifest2 manifest = new MergeableManifest2(fileContents, project.getName());
        fileContents.close();
        manifest.addRequiredBundles(new GrammarHelper(catalog.getGrammar()).getRequiredBundleSymbolicNames());
        if (manifest.isModified()) {
          ByteArrayOutputStream os = new ByteArrayOutputStream();
          manifest.write(os);
          os.close();
          file.setContents(new ByteArrayInputStream(os.toByteArray()), false, false, monitor);
        }
      } catch (IOException e) {
        throw new WrappedException(e);
      } catch (CoreException e) {
        throw new WrappedException(e);
      } finally {
        if (fileContents != null) {
          try {
            fileContents.close();
          } catch (IOException e) {
            LOGGER.warn("Could not close the Manifest file after modifying it.", e);
          }
        }
      }
    }

  }

  /**
   * Performs modifications to the Plugin Model.
   *
   * @param modification
   *          a modification
   * @param monitor
   *          progress monitor
   */
  public void modifyModel(final ModelModification modification, final IProgressMonitor monitor) {
    if (monitor.isCanceled()) {
      throw new OperationCanceledException();
    }
    try {
      PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
        @Override
        public void run() {
          PDEModelUtility.modifyModel(modification, monitor);
        }
      });
    } catch (SWTException e) {
      // If the build was cancelled while in syncExec() it will throw an SWTException
      if (monitor.isCanceled()) {
        throw new OperationCanceledException();
      } else {
        throw e;
      }
    }
  }

  /**
   * Handles deletion of catalogs or modifications thereof. If given delta contains catalog descriptions in
   * its previous state, it must be checked if extensions must be removed from the {@code plugin.xml} file.
   *
   * @param delta
   *          resource delta
   * @param monitor
   *          progress monitor
   * @throws CoreException
   *           the core exception
   */
  public void handleCatalogDeletion(final Delta delta, final IProgressMonitor monitor) throws CoreException {
    IFile file = getPluginFile(delta.getUri());
    if (validPluginFile(file)) {
      Iterable<IEObjectDescription> catalogs = delta.getOld().getExportedObjectsByType(CheckPackage.Literals.CHECK_CATALOG);
      // if catalogs were found, they were either deleted or contents thereof were modified/removed
      for (IEObjectDescription obj : catalogs) {
        removeExtensions(file, obj, monitor);
      }
    }
  }

  /**
   * Get the plugin.xml file of current project, returns {@code null} if no project is available.
   *
   * @param catalogUri
   *          URI of catalog
   * @return the plugin.xml file or {@code null}
   */
  protected IFile getPluginFile(final URI catalogUri) {
    final IProject project = RuntimeProjectUtil.getProject(catalogUri, mapper);
    if (project == null) {
      return null;
    }
    String pluginXmlFilename = projectHelper.getProjectPreference(project, PREFERENCE_PLUGIN_XML_FILENAME);
    if (pluginXmlFilename != null) {
      return project.getFile(pluginXmlFilename);
    } else {
      return PluginRegistry.findModel(project) instanceof IFragmentModel ? PDEProject.getFragmentXml(project) : PDEProject.getPluginXml(project);
    }
  }

  /**
   * Checks if the given plugin file is valid.
   *
   * @param file
   *          plugin.xml file to check, must not be {@code null}
   * @return {@code true} if the file is valid
   */
  private boolean validPluginFile(final IFile file) {
    if (file == null || !file.exists()) {
      return false;
    }
    try {
      SAXParserFactory.newInstance().newSAXParser().parse(new BufferedInputStream(file.getContents(true)), new PluginHandler(false));
      return true;
    } catch (SAXException | IOException | ParserConfigurationException | CoreException e) {
      return false;
    }
  }

  /**
   * Create a new plugin.xml file.
   *
   * @param catalog
   *          the built catalog
   * @param pluginxml
   *          the file (not existing)
   * @param monitor
   *          progress monitor
   * @throws CoreException
   *           if the plug-in extension could not be created
   * @see org.eclipse.pde.internal.ui.wizards.product.ProductIntroOperation
   */
  private void createNewFile(final CheckCatalog catalog, final IFile pluginxml, final IProgressMonitor monitor) throws CoreException {
    WorkspacePluginModelBase pluginModel = (WorkspacePluginModelBase) getModel(pluginxml);
    IPluginBase base = pluginModel.getPluginBase();
    base.setSchemaVersion(TargetPlatformHelper.getSchemaVersion());

    manager.addExtensions(catalog, pluginModel, monitor);
    manager.sortAllExtensions(pluginModel, monitor);

    pluginModel.save();
  }

  /**
   * Returns the plugin model associated to the given file.
   *
   * @param file
   *          a plugin.xml file
   * @return a plugin model
   */
  protected IPluginModelBase getModel(final IFile file) {
    if (STANDARD_FRAGMENT_FILENAME.equals(file.getName())) {
      return new WorkspaceFragmentModel(file, false);
    } else {
      return new WorkspacePluginModel(file, false);
    }
  }

}
