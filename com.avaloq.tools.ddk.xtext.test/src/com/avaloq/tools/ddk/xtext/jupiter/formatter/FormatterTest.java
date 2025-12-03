/*******************************************************************************
 * Copyright (c) 2025 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.jupiter.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.eclipse.xtext.resource.SaveOptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Decl;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.FormatterTestLanguageFactory;
import com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.TestLinewrapMinMax;


/**
 * This class tests the Acs Formatter framework. The tests are basically a copy
 * of the Xtext Formatter tests.
 */
@SuppressWarnings("nls")
public class FormatterTest extends AbstractFormatterTest {
  @Override
  protected FormatterTestUtil getXtextTestUtil() {
    return FormatterTestUtil.getInstance();
  }

  /**
   * This test class does not have a test source file. {@inheritDoc}
   */
  @Override
  protected String getTestSourceFileName() {
    return null;
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrap() throws IOException {
    String model = "test linewrap float val; int x; double y;";
    String expected = "test linewrap\nfloat val;\nint x;\ndouble y;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void keepComments() throws IOException {
    // String model = "test linewrap float val; int x; double y;";
    String model = "// begincomment \ntest linewrap// comment1\n" + "float val;//comment2\n" + "int x;" + "double y; //yoyoyo!\n// endcomment.";
    final String exp = "// begincomment \ntest linewrap// comment1\n" + "float val;//comment2\n" + "int x;\n" + "double y; //yoyoyo!\n// endcomment.";
    assertFormattedPTC(exp, model);
    assertFormattedNM(exp, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test verifies that {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Line} elements are aligned at the specified column.
   *
   * @throws IOException
   */
  @Test
  public void column() throws IOException {
    String model = "test column item int x;";
    String expected = "test\n  column\n\titem          int x;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test verifies that elements can be aligned at column 0 and also that
   * a minimum padding is always inserted.
   *
   * @throws IOException
   */
  @Test
  public void columnMinimumPadding() throws IOException {
    String model = "      test column     name item int x;";
    String expected = "test\n  column name\n\n\titem          int x;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model.trim());
  }

  /**
   * This test verifies that {@link com.avaloq.tools.ddk.xtext.formatter.formatterTestLanguage.Line} elements are aligned with the specified offset.
   *
   * @throws IOException
   */
  @Test
  public void offset() throws IOException {
    String model = "test offset value v pair p1 p2";
    String expected = "test\noffset\n\tvalue    v\n\t\tpair  p1  p2";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test verifies right padding does pad and that there is always minimum padding " ".
   *
   * @throws IOException
   */
  @Test
  public void rightPadding() throws IOException {
    String model = "test padding long_name n2;";
    String expected = "test\npadding long_name n2   ;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void indentation() throws IOException {
    String model = "test indentation { float val; double y; indentation { int x; } }";
    String expected = "test indentation {\n	float val;\n	double y;\n	indentation {\n		int x;\n	}\n}";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void association() throws IOException {
    String model = "test indentation { var = [0,1,2,3,4]; }";
    String expected = "test indentation {\n	var=[ 0, 1, 2, 3, 4 ];\n}";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void indentationAndComments() throws IOException {
    String model = "test /* xxx */ indentation { float val; // some float\n double /* oo */ y; indentation { // some block\n int x; // xxx\n } } // final comment";
    String expected = "test /* xxx */ indentation {\n	float val; // some float\n	double /* oo */ y;\n	indentation { // some block\n		int x; // xxx\n	}\n} // final comment";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   * It has been adapted to
   *
   * @throws IOException
   */
  @Test
  public void indentationAndLineWrap() throws IOException {
    String model = "test indentation { void func(x:int,y:int,s:javalangString, foo:javasqlDate, blupp:mylongtype,  msads:adshdjkhsakdasdkslajdlsask, x:x, a:b, c:d ); }";
    String expected = "test indentation {\n	void func(x:int,y:int,\n\t\ts:javalangString,\n\t\tfoo:javasqlDate,\n\t\tblupp:mylongtype,\n\t\tmsads:adshdjkhsakdasdkslajdlsask,\n\t\tx:x,a:b,c:d);\n}";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void between1() throws IOException {
    String model = "test indentation { indentation { x x; }; }";
    String expected = "test indentation {\n	indentation {\n		x x;\n	};\n}";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void between2() throws IOException {
    String model = "test indentation { indentation { x x; } }";
    String expected = "test indentation {\n	indentation {\n		x x;\n	}\n}";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDatatypeRule() throws IOException {
    String model = "test linewrap fqn ab; fqn xx.yy.zz;";
    String expected = "test linewrap\nfqn\nab;\nfqn\nxx.yy.zz;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDatatypeRulePartial1() throws IOException {
    String model = "test linewrap fqn ab . xx .yy   .zz;";
    String expected = "test linewrap fqn ab.xx.yy.zz;";
    assertFormattedNM(expected, model, 22, 2);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDatatypeRulePartial2() throws IOException {
    String model = "test linewrap fqn ab . xx .yy   .zz;fqn xxx;";
    String expected = "test linewrap fqn\nab.xx.yy.zz;fqn xxx;";
    assertFormattedNM(expected, model, 15, 10);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDatatypeRulePartial3() throws IOException {
    String model = "test linewrap fqn ab . xx .yy   .zz;fqn xxx;";
    String expected = "test linewrap fqn ab.xx.yy.zz;\nfqn xxx;";
    assertFormattedNM(expected, model, 25, 12);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void formatSegment1() throws IOException {
    String model = "test\nindentation {\n    indentation  {  x  x  ;  }  }";
    String expected = "test\nindentation {\n    indentation {\n    	x x;\n    }  }";
    assertFormattedNM(expected, model, 30, 18);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void formatSegment2() throws IOException {
    String model = "test       indentation {\n    indentation  {  x  x  ;  }  }";
    // String expected =
    // "test\nindentation {\n indentation {\n x x;\n } }";
    assertFormattedNM(model, model, 7, 10);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void formatSegment3() throws IOException {
    String model = "     test       indentation {\n    indentation  {  x  x  ;  }  }";
    String expected = "test indentation {\n	indentation {\n		x x;\n	}\n}";
    assertFormattedNM(expected, model, 0, model.length());
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDatatypeRuleRef1() throws IOException {
    String model = "test linewrap fqn ab  .cd .ef; fqnref ab. cd. ef;";
    String expected = "test linewrap\nfqn\nab.cd.ef;\nfqnref\nab.cd.ef;";
    // assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDatatypeRuleRef2() throws IOException {
    String model = "test linewrap fqn ab.cd.ef; fqnref ab.cd.ef;";
    String expected = "test linewrap\nfqn\nab.cd.ef;\nfqnref\nab.cd.ef;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest but it is modified
   * because I think the expected behavior in the original test is wrong.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDatatypeRuleComments() throws IOException {
    String model = "test linewrap/* 1 */fqn/* 2 */ab.cd.ef/* 3 */;/* 4 */fqnref/* 5 */ab.cd.ef/* 6 */;/* 7 */";
    // The expected model string differs from Xtext's -
    // Xtext does not expect a line wrap after the keyword "linewrap"
    // Xtext does not expect a line wrap prior to fqnref assignment
    // etc...
    String expected = "test linewrap/* 1 */ fqn/* 2 */\nab.cd.ef/* 3 */;/* 4 */ fqnref\n/* 5 */ ab.cd.ef/* 6 */;/* 7 */";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void enumeration() throws IOException {
    String model = "test linewrap enum lit1,lit2,lit3,lit1;";
    String expected = "test linewrap\nenum lit1 ,\nlit2,\nlit3,\nlit1;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  // see https://bugs.eclipse.org/bugs/show_bug.cgi?id=312559
  @Test
  public void suppressedWhitespace() throws IOException {
    String model = "test linewrap `f%<b>%a` post;";
    String expected = "test linewrap\n`f%< b >%a` post;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  // TODO: investigate whether to include test or not - currently this test
  // would fail
  @Disabled
  public void suppressedLinewrap() throws IOException {
    String model = "test linewrap\n`foo%abcd%foo%< b\n>%abcd%foo%abcd%foo%abcd%" + "foo%abcd%foo%abcd%foo%abcd%foo%abcd%foo%abcd%foo%xx%foo%abcd%foo%abcd%"
        + "foo%abcd%foo%<\nb >%foo%abcd` post;";
    assertFormattedPTC(model, model);
    assertFormattedNM(model, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapMin() throws IOException {
    String model = "test wrapminmax foo bar;";
    String expected = "test wrapminmax\n\nfoo bar;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapMax() throws IOException {
    String model = "test wrapminmax\n\n\n\n\n\n\n\n\n\n\n\n\nfoo bar;";
    String expected = "test wrapminmax\n\n\n\n\nfoo bar;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapKeep() throws IOException {
    String model = "test wrapminmax\n\n\n\nfoo bar;";
    assertFormattedPTC(model, model);
    assertFormattedNM(model, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void linewrapDefault() {
    FormatterTestLanguageFactory f = FormatterTestLanguageFactory.eINSTANCE;
    TestLinewrapMinMax m = f.createTestLinewrapMinMax();
    Decl d = f.createDecl();
    d.getType().add("xxx");
    d.getName().add("yyy");
    m.getItems().add(d);
    String actual = getSerializer().serialize(m, SaveOptions.newBuilder().format().getOptions());
    String expected = "test wrapminmax\n\n\nxxx yyy;";
    assertEquals(expected, actual, "Default Linewrap");
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void space() throws IOException {
    String model = "test linewrap space foo;";
    String expected = "test linewrap\nspace     foo;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }

  /**
   * This test is copied from
   * org.eclipse.xtext.nodemodel.impl.formatter.FormatterTest.
   *
   * @throws IOException
   */
  @Test
  public void datatypeRules() throws IOException {
    String model = "test linewrap datatypes abc kw1 bcd def kw3;";
    String expected = "test linewrap\ndatatypes abc\nkw1\nbcd\ndef\nkw3;";
    assertFormattedPTC(expected, model);
    assertFormattedNM(expected, model, 0, model.length());
    assertPreserved(model);
  }
}
