/**
 */
package com.avaloq.tools.ddk.xtext.format.format;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Matcher Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.avaloq.tools.ddk.xtext.format.format.FormatPackage#getMatcherType()
 * @model
 * @generated
 */
public enum MatcherType implements Enumerator
{
  /**
   * The '<em><b>Before</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BEFORE_VALUE
   * @generated
   * @ordered
   */
  BEFORE(0, "before", "before"),

  /**
   * The '<em><b>After</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AFTER_VALUE
   * @generated
   * @ordered
   */
  AFTER(1, "after", "after"),

  /**
   * The '<em><b>Around</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AROUND_VALUE
   * @generated
   * @ordered
   */
  AROUND(2, "around", "around"),

  /**
   * The '<em><b>Between</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BETWEEN_VALUE
   * @generated
   * @ordered
   */
  BETWEEN(3, "between", "between"),

  /**
   * The '<em><b>Range</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #RANGE_VALUE
   * @generated
   * @ordered
   */
  RANGE(4, "range", "range");

  /**
   * The '<em><b>Before</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Before</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BEFORE
   * @model name="before"
   * @generated
   * @ordered
   */
  public static final int BEFORE_VALUE = 0;

  /**
   * The '<em><b>After</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>After</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AFTER
   * @model name="after"
   * @generated
   * @ordered
   */
  public static final int AFTER_VALUE = 1;

  /**
   * The '<em><b>Around</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Around</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AROUND
   * @model name="around"
   * @generated
   * @ordered
   */
  public static final int AROUND_VALUE = 2;

  /**
   * The '<em><b>Between</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Between</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BETWEEN
   * @model name="between"
   * @generated
   * @ordered
   */
  public static final int BETWEEN_VALUE = 3;

  /**
   * The '<em><b>Range</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Range</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #RANGE
   * @model name="range"
   * @generated
   * @ordered
   */
  public static final int RANGE_VALUE = 4;

  /**
   * An array of all the '<em><b>Matcher Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final MatcherType[] VALUES_ARRAY =
    new MatcherType[]
    {
      BEFORE,
      AFTER,
      AROUND,
      BETWEEN,
      RANGE,
    };

  /**
   * A public read-only list of all the '<em><b>Matcher Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<MatcherType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Matcher Type</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static MatcherType get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      MatcherType result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Matcher Type</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static MatcherType getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      MatcherType result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Matcher Type</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static MatcherType get(int value)
  {
    switch (value)
    {
      case BEFORE_VALUE: return BEFORE;
      case AFTER_VALUE: return AFTER;
      case AROUND_VALUE: return AROUND;
      case BETWEEN_VALUE: return BETWEEN;
      case RANGE_VALUE: return RANGE;
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
  private MatcherType(int value, String name, String literal)
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
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
  
} //MatcherType
