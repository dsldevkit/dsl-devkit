/**
 */
package com.avaloq.tools.ddk.check.check;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Trigger Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.check.check.CheckPackage#getTriggerKind()
 * @model
 * @generated
 */
public enum TriggerKind implements Enumerator
{
  /**
   * The '<em><b>Fast</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FAST_VALUE
   * @generated
   * @ordered
   */
  FAST(0, "fast", "live"),

  /**
   * The '<em><b>Normal</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NORMAL_VALUE
   * @generated
   * @ordered
   */
  NORMAL(1, "normal", "onSave"),

  /**
   * The '<em><b>Expensive</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXPENSIVE_VALUE
   * @generated
   * @ordered
   */
  EXPENSIVE(2, "expensive", "onDemand");

  /**
   * The '<em><b>Fast</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FAST
   * @model name="fast" literal="live"
   * @generated
   * @ordered
   */
  public static final int FAST_VALUE = 0;

  /**
   * The '<em><b>Normal</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NORMAL
   * @model name="normal" literal="onSave"
   * @generated
   * @ordered
   */
  public static final int NORMAL_VALUE = 1;

  /**
   * The '<em><b>Expensive</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXPENSIVE
   * @model name="expensive" literal="onDemand"
   * @generated
   * @ordered
   */
  public static final int EXPENSIVE_VALUE = 2;

  /**
   * An array of all the '<em><b>Trigger Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final TriggerKind[] VALUES_ARRAY =
    new TriggerKind[]
    {
      FAST,
      NORMAL,
      EXPENSIVE,
    };

  /**
   * A public read-only list of all the '<em><b>Trigger Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<TriggerKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Trigger Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static TriggerKind get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      TriggerKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Trigger Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static TriggerKind getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      TriggerKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Trigger Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static TriggerKind get(int value)
  {
    switch (value)
    {
      case FAST_VALUE: return FAST;
      case NORMAL_VALUE: return NORMAL;
      case EXPENSIVE_VALUE: return EXPENSIVE;
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
  private TriggerKind(int value, String name, String literal)
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
  
} //TriggerKind
