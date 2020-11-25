/**
 */
package com.avaloq.tools.ddk.xtext.valid.valid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Quick Fix Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.valid.valid.ValidPackage#getQuickFixKind()
 * @model
 * @generated
 */
public enum QuickFixKind implements Enumerator
{
  /**
   * The '<em><b>Semantic</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SEMANTIC_VALUE
   * @generated
   * @ordered
   */
  SEMANTIC(0, "semantic", "semantic"),

  /**
   * The '<em><b>Textual</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #TEXTUAL_VALUE
   * @generated
   * @ordered
   */
  TEXTUAL(1, "textual", "textual");

  /**
   * The '<em><b>Semantic</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SEMANTIC
   * @model name="semantic"
   * @generated
   * @ordered
   */
  public static final int SEMANTIC_VALUE = 0;

  /**
   * The '<em><b>Textual</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #TEXTUAL
   * @model name="textual"
   * @generated
   * @ordered
   */
  public static final int TEXTUAL_VALUE = 1;

  /**
   * An array of all the '<em><b>Quick Fix Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final QuickFixKind[] VALUES_ARRAY =
    new QuickFixKind[]
    {
      SEMANTIC,
      TEXTUAL,
    };

  /**
   * A public read-only list of all the '<em><b>Quick Fix Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<QuickFixKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Quick Fix Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static QuickFixKind get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      QuickFixKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Quick Fix Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static QuickFixKind getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      QuickFixKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Quick Fix Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static QuickFixKind get(int value)
  {
    switch (value)
    {
      case SEMANTIC_VALUE: return SEMANTIC;
      case TEXTUAL_VALUE: return TEXTUAL;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private QuickFixKind(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //QuickFixKind
