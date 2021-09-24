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

package com.avaloq.tools.ddk.check.formatting

import com.avaloq.tools.ddk.check.CheckUiInjectorProvider
import com.google.inject.Inject
import org.eclipse.xtext.formatting2.FormatterPreferenceKeys
import org.eclipse.xtext.preferences.MapBasedPreferenceValues
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.formatter.FormatterTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@InjectWith(typeof(CheckUiInjectorProvider))
@RunWith(typeof(XtextRunner))
class CheckFormattingTest {

  @Inject
  extension FormatterTestHelper

  /**
   * Test that correctly formatted Check sources are not modified.
   */
  @Test
  def void testFormattedSource() {
    val input = '''
      package com.avaloq.tools.ddk.check.formatting

      import com.avaloq.tools.ddk.check.check.*

      catalog CheckFormattingTest
      for grammar com.avaloq.tools.ddk.check.Check {

        category "Label" {

          /**
           * @todo Document check. 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890
           */
          live error UniqueID "Label"
          message "message" {
          }

          live error anotherid "Label"
          message "message {0}, {1}" {
          }
        }

        def Name
        for Category list {
          issue anotherid on list bind (3, 2) data ("")
          val size =
            if (list.checks !== null) list.checks.size else 0
          if (list.checks?.size > 1) {
            // SL: string value of list
            String::valueOf(list)
            issue UniqueID on list#checks[0]
          } else {
            /* ML: string value of size       */
            String::valueOf(size)
          }
        }

        @SeverityRange(warning .. error)
        onSave error lastID "Label"
        message "message" {
          // single line comment
        }

        /**
         * This check is javadoc-like commented.
         */
        warning CategoryNamedW "Category named W" (boolean foo = false, boolean bar = true)
        message "Category named 'w'" {
          for Category c {
            if ('w'.equalsIgnoreCase(c.name)) {
              issue
            }
          }
        }
      }
    '''

    assertFormatted[
      // these preferences are usually picked up from the resource, but we are not using a resource here.
      request.preferences = new MapBasedPreferenceValues() => [put(FormatterPreferenceKeys.indentation, '  ')]

      toBeFormatted = input
      expectation = input
    ];
  }

  /**
   * Test that spaces and new lines are added where expected.
   */
  @Test
  def void testWSAdded() {
    val input = '''
      package com.avaloq.tools.ddk.check.formatting import com.avaloq.tools.ddk.check.check.* catalog CheckFormattingTest
      for grammar com.avaloq.tools.ddk.check.Check{category "Label"{/**
           * @todo Document check. 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890
           */live error UniqueID "Label"
          message "message"{}live error anotherid "Label"
          message "message {0}, {1}" {}}
        def Name for Category list{issue anotherid on list bind(3,2)data("")
          val size=if(list.checks !== null)list.checks.size else 0
          if(list.checks?.size>1){
          // SL: string value of list
          String::valueOf(list)issue UniqueID on list#checks[0]}else{/* ML: string value of size       */String::valueOf(size)}}
        @SeverityRange(warning..error)onSave error lastID"Label"message "message" {
          // single line comment
        }/**
         * This check is javadoc-like commented.
         */warning CategoryNamedW "Category named W"(boolean foo=false,boolean bar=true)message "Category named 'w'"{
          for Category c {
          if('w'.equalsIgnoreCase(c.name)){issue}
      }}}
    '''

    val expected = '''
      package com.avaloq.tools.ddk.check.formatting
      import com.avaloq.tools.ddk.check.check.*
      catalog CheckFormattingTest
      for grammar com.avaloq.tools.ddk.check.Check {
        category "Label" {
        /**
         * @todo Document check. 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890
         */
          live error UniqueID "Label"
          message "message" {
          }
          live error anotherid "Label"
          message "message {0}, {1}" {
          }
        }
        def Name
        for Category list {
          issue anotherid on list bind (3, 2) data ("")
          val size =
            if (list.checks !== null) list.checks.size else 0
          if (list.checks?.size > 1) {
            // SL: string value of list
            String::valueOf(list)
            issue UniqueID on list#checks[0]
          } else { /* ML: string value of size       */
            String::valueOf(size)
          }
        }
        @SeverityRange(warning .. error)
        onSave error lastID "Label"
        message "message" {
          // single line comment
        }
        /**
         * This check is javadoc-like commented.
         */
        warning CategoryNamedW "Category named W" (boolean foo = false, boolean bar = true)
        message "Category named 'w'" {
          for Category c {
            if ('w'.equalsIgnoreCase(c.name)) {
              issue
            }
          }
        }
      }
    '''

    assertFormatted[
      // these preferences are usually picked up from the resource, but we are not using a resource here.
      request.preferences = new MapBasedPreferenceValues() => [put(FormatterPreferenceKeys.indentation, '  ')]
      toBeFormatted = input
      expectation = expected
    ];
  }

  /**
   * Test that additional spaces and new lines are removed
   */
  @Test
  def void testWSRemoved() {
    val input = '''
          package
               com.avaloq.tools.ddk.check.formatting



      import
           com.avaloq.tools.ddk.check.check.*



      catalog
          CheckFormattingTest


      for
         grammar
            com.avaloq.tools.ddk.check.Check


            {



        category
            "Label"

             {


          /**
           * @todo Document check. 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890
           */



          live

          error

           UniqueID

            "Label"


          message


           "message"

            {


          }

          live      error      anotherid     "Label"
          message      "message {0}, {1}"           {


          }


        }



        def

         Name


        for

         Category

         list

          {


          issue


           anotherid


            on


             list


              bind


               (

               3

               ,

                2

                )

                 data

                  (

                  ""

                  )


          val

           size

            =


            if

            (

            list
            .
            checks

             !==

             null

             )

             list   .   checks   .   size


              else

              0

          if

           (

           list.checks   ?.   size
            > 1)


             {



            // SL: string value of list


            String

            ::

            valueOf

            (

            list

            )

            issue UniqueID on list   #    checks

               [

              0

             ]
          }


           else


           {



            /* ML: string value of size       */


            String     ::    valueOf   (   size  )


          }


        }



        @

        SeverityRange

        (

        warning

        ..

        error

        )


        onSave error lastID "Label"
        message "message" {
              // single line comment
            }



        /**
         * This check is javadoc-like commented.
         */


        warning    CategoryNamedW    "Category named W"


        (

        boolean

        foo

        =

        false

        ,

        boolean

        bar

        =

        true

        )


        message "Category named 'w'" {


          for Category c {


            if ('w'.equalsIgnoreCase(c.name)) {


              issue


            }


          }


        }


      }


    '''

    val expected = '''
      package com.avaloq.tools.ddk.check.formatting

      import com.avaloq.tools.ddk.check.check.*

      catalog CheckFormattingTest

      for grammar com.avaloq.tools.ddk.check.Check {

        category "Label" {

          /**
           * @todo Document check. 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890 1234567890
           */
          live error UniqueID "Label"

          message "message" {
          }

          live error anotherid "Label"
          message "message {0}, {1}" {
          }
        }

        def Name

        for Category list {

          issue anotherid on list bind (3, 2) data ("")

          val size =

            if (list.checks !== null) list.checks.size else 0

          if (list.checks?.size > 1) {

            // SL: string value of list
            String::valueOf(
              list
            )

            issue UniqueID on list#checks[0]
          } else {

            /* ML: string value of size       */
            String::valueOf(size)

          }

        }

        @SeverityRange(warning .. error)
        onSave error lastID "Label"
        message "message" {
          // single line comment
        }

        /**
         * This check is javadoc-like commented.
         */
        warning CategoryNamedW "Category named W" (boolean foo = false,
        boolean bar = true)

        message "Category named 'w'" {

          for Category c {

            if ('w'.equalsIgnoreCase(c.name)) {

              issue

            }

          }

        }
      }
    '''

    assertFormatted[
      // these preferences are usually picked up from the resource, but we are not using a resource here.
      request.preferences = new MapBasedPreferenceValues() => [put(FormatterPreferenceKeys.indentation, '  ')]
      toBeFormatted = input
      expectation = expected
    ];
  }
}
