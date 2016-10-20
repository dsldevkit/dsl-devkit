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
package com.avaloq.tools.ddk.xtext.format.ui.typing;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.WorkingCopyOwner;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IMirror;
import org.eclipse.xtext.common.types.access.TypeResource;
import org.eclipse.xtext.common.types.access.impl.IndexedJvmTypeAccess;
import org.eclipse.xtext.common.types.access.impl.IndexedJvmTypeAccess.ShadowedTypeException;
import org.eclipse.xtext.common.types.access.impl.TypeResourceServices;
import org.eclipse.xtext.common.types.access.impl.URIHelperConstants;
import org.eclipse.xtext.common.types.access.jdt.JdtTypeProvider;
import org.eclipse.xtext.common.types.access.jdt.TypeURIHelper;
import org.eclipse.xtext.util.Strings;


/**
 * A customized Jdt provider for format. For a given project it allows to index and link generated types (src-gen directory).
 * All private methods are copied from superclass due to their unavailability in a subclass.
 *
 * @see {@link JdtTypeProvider}
 */
@SuppressWarnings("restriction")
public class FormatJdtTypeProvider extends JdtTypeProvider {

  private static final String PRIMITIVES = URIHelperConstants.PRIMITIVES_URI.segment(0);

  private final TypeURIHelper typeUriHelper;

  private final IJavaProject javaProject;

  public FormatJdtTypeProvider(final IJavaProject javaProject, final ResourceSet resourceSet, final IndexedJvmTypeAccess indexedJvmTypeAccess, final WorkingCopyOwner workingCopyOwner, final TypeResourceServices typeResourceServices) {
    super(javaProject, resourceSet, indexedJvmTypeAccess, workingCopyOwner, typeResourceServices);
    this.javaProject = javaProject;
    this.typeUriHelper = createTypeURIHelper();
  }

  @Override
  public JvmType findTypeByName(final String name) {
    JvmType jvmType = super.findTypeByName(name);
    if (jvmType != null) {
      return jvmType;
    }
    String signature = getSignature(name);
    if (signature == null) {
      return null;
    }
    URI resourceURI = typeUriHelper.createResourceURI(signature);
    if (resourceURI.segment(0) == PRIMITIVES) {
      return findPrimitiveType(signature, resourceURI);
    } else {
      return findObjectType(signature, resourceURI);
    }
  }

  /**
   * Finds object by type.
   * <p>
   * <em>Note:</em> This method is a modified of the private method of the same name in {@link JdtTypeProvider}. It does not check if the type can be linked to
   * in order to allow the 'context' variable to be used in the Format language.
   * </p>
   *
   * @param signature
   *          of the type
   * @param resourceURI
   *          URI of the resource
   * @return corresponding JvmType
   */
  private JvmType findObjectType(final String signature, final URI resourceURI) {
    TypeResource resource = getLoadedResourceForJavaURI(resourceURI);
    try {
      JvmType result = findLoadedOrDerivedObjectType(signature, resourceURI, resource);
      if (result != null || resource != null) {
        return result;
      }
      try {
        return findObjectTypeInJavaProject(signature, resourceURI);
      } catch (JavaModelException e) {
        return null;
      }
    } catch (ShadowedTypeException e) {
      return null;
    }
  }

  /**
   * Finds object type corresponding to the given resource.
   * <p>
   * <em>Note:</em> This method is a modified of the private method of the same name in {@link JdtTypeProvider}. It does not check if the type can be linked to
   * in order to allow the 'context' variable to be used in the Format language.
   * </p>
   *
   * @param resourceURI
   *          URI of resource
   * @return object type of resource
   * @throws JavaModelException
   *           exception
   */
  private IType findObjectTypeInJavaProject(final URI resourceURI) throws JavaModelException {
    String topLevelType = resourceURI.segment(resourceURI.segmentCount() - 1);
    int lastDot = topLevelType.lastIndexOf('.');
    String packageName = null;
    String typeName = topLevelType;
    if (lastDot != -1) {
      typeName = typeName.substring(lastDot + 1);
      packageName = topLevelType.substring(0, lastDot);
    }
    return javaProject.findType(packageName, typeName /* , workingCopyOwner */);
  }

  /**
   * Returns the signature for a given type name.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param name
   *          type name
   * @return signature
   */
  private String getSignature(final String name) {
    if (Strings.isEmpty(name)) {
      throw new IllegalArgumentException("null");
    }
    String signature = null;
    try {
      signature = name.charAt(0) == '[' ? name : Signature.createTypeSignature(name, true);
    } catch (IllegalArgumentException e) {
      return null;
    }
    return signature;
  }

  /**
   * Finds object type for a given resource of a given {@link TypeResource}.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param signature
   *          of the type
   * @param resourceURI
   *          URI of the resource
   * @param resource
   *          for which the type should be returned
   * @return corresponding JvmType
   */
  private JvmType findLoadedOrDerivedObjectType(final String signature, final URI resourceURI, final TypeResource resource) {
    JvmType result = resource != null ? findTypeBySignature(signature, resource) : null;
    if (result != null) {
      return result;
    }
    result = findObjectTypeInIndex(signature, resourceURI);
    if (result != null) {
      return result;
    }
    return null;
  }

  /**
   * Finds object type in a current java project.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param signature
   *          of the type
   * @param resourceURI
   *          URI of the resource
   * @return corresponding JvmType
   * @throws JavaModelException
   *           excpetion
   **/
  private JvmType findObjectTypeInJavaProject(final String signature, final URI resourceURI) throws JavaModelException {
    IType type = findObjectTypeInJavaProject(resourceURI);
    if (type != null) {
      try {
        return createResourceAndFindType(resourceURI, type, signature);
      } catch (IOException ioe) {
        return null;
      } catch (WrappedException wrapped) {
        if (wrapped.getCause() instanceof IOException) {
          return null;
        }
        throw wrapped;
      }
    }
    return null;
  }

  /**
   * Creates type resource and returns corresponding to it JvmType.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param resourceURI
   *          URI of resource
   * @param type
   *          type of resource
   * @param signature
   *          of resource
   * @return desired JvmType
   * @throws IOException
   *           exception
   */
  private JvmType createResourceAndFindType(final URI resourceURI, final IType type, final String signature) throws IOException {
    TypeResource resource = createResource(resourceURI, type);
    resource.load(null);
    return findTypeBySignature(signature, resource);
  }

  /**
   * Creates type resource.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param resourceURI
   *          URI of resource
   * @param type
   *          object type of resource
   * @return type of resource
   */
  private TypeResource createResource(final URI resourceURI, final IType type) {
    TypeResource resource = new TypeResource(resourceURI);
    resource.setIndexedJvmTypeAccess(getIndexedJvmTypeAccess());
    getResourceSet().getResources().add(resource);
    if (type.exists()) {
      IMirror mirror = createMirror(type);
      resource.setMirror(mirror);
    }
    return resource;
  }

  /**
   * Finds object type of resource.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param signature
   *          of resource
   * @param resourceURI
   *          URI of resource
   * @return corresponding object type
   */
  private JvmType findObjectTypeInIndex(final String signature, final URI resourceURI) {
    IndexedJvmTypeAccess indexedJvmTypeAccess = getIndexedJvmTypeAccess();
    if (indexedJvmTypeAccess != null) {
      URI proxyURI = resourceURI.appendFragment(typeUriHelper.getFragment(signature));
      EObject candidate = indexedJvmTypeAccess.getIndexedJvmType(proxyURI, getResourceSet(), true);
      if (candidate instanceof JvmType) {
        return (JvmType) candidate;
      }
    }
    return null;
  }

  /**
   * Returns type resource for the given URI.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param resourceURI
   *          URI of resource
   * @return type resource
   */
  private TypeResource getLoadedResourceForJavaURI(final URI resourceURI) {
    return (TypeResource) getResourceForJavaURI(resourceURI, false);
  }

  /**
   * Finds a primitive type corresponding to the given signature and URI of a resource.
   * <p>
   * <em>Note:</em> This method is a copy of the private method of the same name in {@link JdtTypeProvider}
   * </p>
   *
   * @param signature
   *          of resource
   * @param resourceURI
   *          URI of resource
   * @return corresponding JvmType
   */
  private JvmType findPrimitiveType(final String signature, final URI resourceURI) {
    TypeResource resource = (TypeResource) getResourceForJavaURI(resourceURI, true);
    return findTypeBySignature(signature, resource);
  }

}
