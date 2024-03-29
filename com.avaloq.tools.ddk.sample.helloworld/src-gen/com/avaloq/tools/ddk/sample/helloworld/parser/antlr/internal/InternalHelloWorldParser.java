package com.avaloq.tools.ddk.sample.helloworld.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.avaloq.tools.ddk.sample.helloworld.services.HelloWorldGrammarAccess;
import com.avaloq.tools.ddk.sample.helloworld.grammar.AbstractHelloWorldSemanticPredicates;
import com.avaloq.tools.ddk.xtext.parser.antlr.ParserContext;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHelloWorldParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Hello'", "'!'", "'four'", "'five'", "'six'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__15=15;
    public static final int RULE_INT=5;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalHelloWorldParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalHelloWorldParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalHelloWorldParser.tokenNames; }
    public String getGrammarFileName() { return "InternalHelloWorld.g"; }



        private HelloWorldGrammarAccess grammarAccess;
        private AbstractHelloWorldSemanticPredicates predicates;
        private ParserContext parserContext;

        public InternalHelloWorldParser(TokenStream input, HelloWorldGrammarAccess grammarAccess, ParserContext parserContext, AbstractHelloWorldSemanticPredicates predicates) {
            this(input);
            this.grammarAccess = grammarAccess;
            this.predicates = predicates;
            this.parserContext = parserContext;
            parserContext.setTokenStream(input);
            registerRules(grammarAccess.getGrammar());
        }

        /**
         * Set token stream in parser context.
         * @param input Token stream
         */
        @Override
        public void setTokenStream(TokenStream input) {
          super.setTokenStream(input);
          if(parserContext != null){
            parserContext.setTokenStream(input);
          }
        }

        @Override
        protected String getFirstRuleName() {
          return "Model";
        }

        @Override
        protected HelloWorldGrammarAccess getGrammarAccess() {
          return grammarAccess;
        }




    // $ANTLR start "entryRuleModel"
    // InternalHelloWorld.g:83:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalHelloWorld.g:83:46: (iv_ruleModel= ruleModel EOF )
            // InternalHelloWorld.g:84:3: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalHelloWorld.g:90:1: ruleModel returns [EObject current=null] : ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_greetings_0_0 = null;

        EObject lv_keywordsExample_1_0 = null;



          enterRule();

        try {
            // InternalHelloWorld.g:96:2: ( ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )? ) )
            // InternalHelloWorld.g:97:3: ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )? )
            {
            // InternalHelloWorld.g:97:3: ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )? )
            // InternalHelloWorld.g:98:5: ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )?
            {
            // InternalHelloWorld.g:98:5: ( (lv_greetings_0_0= ruleGreeting ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalHelloWorld.g:99:7: (lv_greetings_0_0= ruleGreeting )
            	    {
            	    // InternalHelloWorld.g:99:7: (lv_greetings_0_0= ruleGreeting )
            	    // InternalHelloWorld.g:100:8: lv_greetings_0_0= ruleGreeting
            	    {

            	          	  newCompositeNode(grammarAccess.getModelAccess().getGreetingsGreetingParserRuleCall_0_0());
            	          	
            	    pushFollow(FOLLOW_3);
            	    lv_greetings_0_0=ruleGreeting();

            	    state._fsp--;


            	          	  if (current==null) {
            	          	    current = createModelElementForParent(grammarAccess.getModelRule());
            	          	  }
            	          	  add(
            	          	    current,
            	          	    "greetings",
            	          	    lv_greetings_0_0,
            	          	    "com.avaloq.tools.ddk.sample.helloworld.HelloWorld.Greeting");
            	          	  afterParserOrEnumRuleCall();
            	          	

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalHelloWorld.g:117:5: ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID||(LA2_0>=13 && LA2_0<=14)) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalHelloWorld.g:118:7: (lv_keywordsExample_1_0= ruleKeywordsExample )
                    {
                    // InternalHelloWorld.g:118:7: (lv_keywordsExample_1_0= ruleKeywordsExample )
                    // InternalHelloWorld.g:119:8: lv_keywordsExample_1_0= ruleKeywordsExample
                    {

                          	  newCompositeNode(grammarAccess.getModelAccess().getKeywordsExampleKeywordsExampleParserRuleCall_1_0());
                          	
                    pushFollow(FOLLOW_2);
                    lv_keywordsExample_1_0=ruleKeywordsExample();

                    state._fsp--;


                          	  if (current==null) {
                          	    current = createModelElementForParent(grammarAccess.getModelRule());
                          	  }
                          	  set(
                          	    current,
                          	    "keywordsExample",
                          	    lv_keywordsExample_1_0,
                          	    "com.avaloq.tools.ddk.sample.helloworld.HelloWorld.KeywordsExample");
                          	  afterParserOrEnumRuleCall();
                          	

                    }


                    }
                    break;

            }


            }


            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleGreeting"
    // InternalHelloWorld.g:140:1: entryRuleGreeting returns [EObject current=null] : iv_ruleGreeting= ruleGreeting EOF ;
    public final EObject entryRuleGreeting() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGreeting = null;


        try {
            // InternalHelloWorld.g:140:49: (iv_ruleGreeting= ruleGreeting EOF )
            // InternalHelloWorld.g:141:3: iv_ruleGreeting= ruleGreeting EOF
            {
             newCompositeNode(grammarAccess.getGreetingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGreeting=ruleGreeting();

            state._fsp--;

             current =iv_ruleGreeting; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGreeting"


    // $ANTLR start "ruleGreeting"
    // InternalHelloWorld.g:147:1: ruleGreeting returns [EObject current=null] : (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' ) ;
    public final EObject ruleGreeting() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;


          enterRule();

        try {
            // InternalHelloWorld.g:153:2: ( (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' ) )
            // InternalHelloWorld.g:154:3: (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' )
            {
            // InternalHelloWorld.g:154:3: (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' )
            // InternalHelloWorld.g:155:5: otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

                  newLeafNode(otherlv_0, grammarAccess.getGreetingAccess().getHelloKeyword_0());
                
            // InternalHelloWorld.g:159:5: ( (lv_name_1_0= RULE_ID ) )
            // InternalHelloWorld.g:160:7: (lv_name_1_0= RULE_ID )
            {
            // InternalHelloWorld.g:160:7: (lv_name_1_0= RULE_ID )
            // InternalHelloWorld.g:161:8: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

                  	  newLeafNode(lv_name_1_0, grammarAccess.getGreetingAccess().getNameIDTerminalRuleCall_1_0());
                  	

                  	  if (current==null) {
                  	    current = createModelElement(grammarAccess.getGreetingRule());
                  	  }
                  	  setWithLastConsumed(
                  	    current,
                  	    "name",
                  	    lv_name_1_0,
                  	    "org.eclipse.xtext.common.Terminals.ID");
                  	

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_2); 

                  newLeafNode(otherlv_2, grammarAccess.getGreetingAccess().getExclamationMarkKeyword_2());
                

            }


            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGreeting"


    // $ANTLR start "entryRuleKeywordsExample"
    // InternalHelloWorld.g:185:1: entryRuleKeywordsExample returns [EObject current=null] : iv_ruleKeywordsExample= ruleKeywordsExample EOF ;
    public final EObject entryRuleKeywordsExample() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeywordsExample = null;


        try {
            // InternalHelloWorld.g:185:56: (iv_ruleKeywordsExample= ruleKeywordsExample EOF )
            // InternalHelloWorld.g:186:3: iv_ruleKeywordsExample= ruleKeywordsExample EOF
            {
             newCompositeNode(grammarAccess.getKeywordsExampleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeywordsExample=ruleKeywordsExample();

            state._fsp--;

             current =iv_ruleKeywordsExample; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeywordsExample"


    // $ANTLR start "ruleKeywordsExample"
    // InternalHelloWorld.g:192:1: ruleKeywordsExample returns [EObject current=null] : ( ( ({...}? =>lv_option_0_0= ruleOptionOne ) ) | ( ({...}? =>lv_option_1_0= ruleOptionTwo ) ) | ( (lv_option_2_0= ruleOptionThree ) ) | ( (lv_option_3_0= ruleOptionFour ) ) ) ;
    public final EObject ruleKeywordsExample() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_option_0_0 = null;

        AntlrDatatypeRuleToken lv_option_1_0 = null;

        AntlrDatatypeRuleToken lv_option_2_0 = null;

        AntlrDatatypeRuleToken lv_option_3_0 = null;



          enterRule();

        try {
            // InternalHelloWorld.g:198:2: ( ( ( ({...}? =>lv_option_0_0= ruleOptionOne ) ) | ( ({...}? =>lv_option_1_0= ruleOptionTwo ) ) | ( (lv_option_2_0= ruleOptionThree ) ) | ( (lv_option_3_0= ruleOptionFour ) ) ) )
            // InternalHelloWorld.g:199:3: ( ( ({...}? =>lv_option_0_0= ruleOptionOne ) ) | ( ({...}? =>lv_option_1_0= ruleOptionTwo ) ) | ( (lv_option_2_0= ruleOptionThree ) ) | ( (lv_option_3_0= ruleOptionFour ) ) )
            {
            // InternalHelloWorld.g:199:3: ( ( ({...}? =>lv_option_0_0= ruleOptionOne ) ) | ( ({...}? =>lv_option_1_0= ruleOptionTwo ) ) | ( (lv_option_2_0= ruleOptionThree ) ) | ( (lv_option_3_0= ruleOptionFour ) ) )
            int alt3=4;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) && (((predicates.isKeyTwoEnabled(parserContext))||(predicates.isKeyOneEnabled(parserContext))))) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==EOF) && ((predicates.isKeyOneEnabled(parserContext)))) {
                    alt3=1;
                }
                else if ( (LA3_1==RULE_ID) && ((predicates.isKeyTwoEnabled(parserContext)))) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA3_0==13) ) {
                alt3=3;
            }
            else if ( (LA3_0==14) ) {
                alt3=4;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalHelloWorld.g:200:5: ( ({...}? =>lv_option_0_0= ruleOptionOne ) )
                    {
                    // InternalHelloWorld.g:200:5: ( ({...}? =>lv_option_0_0= ruleOptionOne ) )
                    // InternalHelloWorld.g:201:7: ({...}? =>lv_option_0_0= ruleOptionOne )
                    {
                    // InternalHelloWorld.g:201:7: ({...}? =>lv_option_0_0= ruleOptionOne )
                    // InternalHelloWorld.g:202:8: {...}? =>lv_option_0_0= ruleOptionOne
                    {
                    if ( !((predicates.isKeyOneEnabled(parserContext))) ) {
                        throw new FailedPredicateException(input, "ruleKeywordsExample", "predicates.isKeyOneEnabled(parserContext)");
                    }

                          	  newCompositeNode(grammarAccess.getKeywordsExampleAccess().getOptionOptionOneParserRuleCall_0_0());
                          	
                    pushFollow(FOLLOW_2);
                    lv_option_0_0=ruleOptionOne();

                    state._fsp--;


                          	  if (current==null) {
                          	    current = createModelElementForParent(grammarAccess.getKeywordsExampleRule());
                          	  }
                          	  set(
                          	    current,
                          	    "option",
                          	    lv_option_0_0,
                          	    "com.avaloq.tools.ddk.sample.helloworld.HelloWorld.OptionOne");
                          	  afterParserOrEnumRuleCall();
                          	

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalHelloWorld.g:221:5: ( ({...}? =>lv_option_1_0= ruleOptionTwo ) )
                    {
                    // InternalHelloWorld.g:221:5: ( ({...}? =>lv_option_1_0= ruleOptionTwo ) )
                    // InternalHelloWorld.g:222:7: ({...}? =>lv_option_1_0= ruleOptionTwo )
                    {
                    // InternalHelloWorld.g:222:7: ({...}? =>lv_option_1_0= ruleOptionTwo )
                    // InternalHelloWorld.g:223:8: {...}? =>lv_option_1_0= ruleOptionTwo
                    {
                    if ( !((predicates.isKeyTwoEnabled(parserContext))) ) {
                        throw new FailedPredicateException(input, "ruleKeywordsExample", "predicates.isKeyTwoEnabled(parserContext)");
                    }

                          	  newCompositeNode(grammarAccess.getKeywordsExampleAccess().getOptionOptionTwoParserRuleCall_1_0());
                          	
                    pushFollow(FOLLOW_2);
                    lv_option_1_0=ruleOptionTwo();

                    state._fsp--;


                          	  if (current==null) {
                          	    current = createModelElementForParent(grammarAccess.getKeywordsExampleRule());
                          	  }
                          	  set(
                          	    current,
                          	    "option",
                          	    lv_option_1_0,
                          	    "com.avaloq.tools.ddk.sample.helloworld.HelloWorld.OptionTwo");
                          	  afterParserOrEnumRuleCall();
                          	

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalHelloWorld.g:242:5: ( (lv_option_2_0= ruleOptionThree ) )
                    {
                    // InternalHelloWorld.g:242:5: ( (lv_option_2_0= ruleOptionThree ) )
                    // InternalHelloWorld.g:243:7: (lv_option_2_0= ruleOptionThree )
                    {
                    // InternalHelloWorld.g:243:7: (lv_option_2_0= ruleOptionThree )
                    // InternalHelloWorld.g:244:8: lv_option_2_0= ruleOptionThree
                    {

                          	  newCompositeNode(grammarAccess.getKeywordsExampleAccess().getOptionOptionThreeParserRuleCall_2_0());
                          	
                    pushFollow(FOLLOW_2);
                    lv_option_2_0=ruleOptionThree();

                    state._fsp--;


                          	  if (current==null) {
                          	    current = createModelElementForParent(grammarAccess.getKeywordsExampleRule());
                          	  }
                          	  set(
                          	    current,
                          	    "option",
                          	    lv_option_2_0,
                          	    "com.avaloq.tools.ddk.sample.helloworld.HelloWorld.OptionThree");
                          	  afterParserOrEnumRuleCall();
                          	

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalHelloWorld.g:262:5: ( (lv_option_3_0= ruleOptionFour ) )
                    {
                    // InternalHelloWorld.g:262:5: ( (lv_option_3_0= ruleOptionFour ) )
                    // InternalHelloWorld.g:263:7: (lv_option_3_0= ruleOptionFour )
                    {
                    // InternalHelloWorld.g:263:7: (lv_option_3_0= ruleOptionFour )
                    // InternalHelloWorld.g:264:8: lv_option_3_0= ruleOptionFour
                    {

                          	  newCompositeNode(grammarAccess.getKeywordsExampleAccess().getOptionOptionFourParserRuleCall_3_0());
                          	
                    pushFollow(FOLLOW_2);
                    lv_option_3_0=ruleOptionFour();

                    state._fsp--;


                          	  if (current==null) {
                          	    current = createModelElementForParent(grammarAccess.getKeywordsExampleRule());
                          	  }
                          	  set(
                          	    current,
                          	    "option",
                          	    lv_option_3_0,
                          	    "com.avaloq.tools.ddk.sample.helloworld.HelloWorld.OptionFour");
                          	  afterParserOrEnumRuleCall();
                          	

                    }


                    }


                    }
                    break;

            }


            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeywordsExample"


    // $ANTLR start "entryRuleOptionOne"
    // InternalHelloWorld.g:285:1: entryRuleOptionOne returns [String current=null] : iv_ruleOptionOne= ruleOptionOne EOF ;
    public final String entryRuleOptionOne() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOptionOne = null;


        try {
            // InternalHelloWorld.g:285:49: (iv_ruleOptionOne= ruleOptionOne EOF )
            // InternalHelloWorld.g:286:3: iv_ruleOptionOne= ruleOptionOne EOF
            {
             newCompositeNode(grammarAccess.getOptionOneRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionOne=ruleOptionOne();

            state._fsp--;

             current =iv_ruleOptionOne.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptionOne"


    // $ANTLR start "ruleOptionOne"
    // InternalHelloWorld.g:292:1: ruleOptionOne returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_KeyOne_0= ruleKeyOne ;
    public final AntlrDatatypeRuleToken ruleOptionOne() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_KeyOne_0 = null;



          enterRule();

        try {
            // InternalHelloWorld.g:298:2: (this_KeyOne_0= ruleKeyOne )
            // InternalHelloWorld.g:299:3: this_KeyOne_0= ruleKeyOne
            {

                newCompositeNode(grammarAccess.getOptionOneAccess().getKeyOneParserRuleCall());
              
            pushFollow(FOLLOW_2);
            this_KeyOne_0=ruleKeyOne();

            state._fsp--;


                current.merge(this_KeyOne_0);
              

                afterParserOrEnumRuleCall();
              

            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptionOne"


    // $ANTLR start "entryRuleOptionTwo"
    // InternalHelloWorld.g:312:1: entryRuleOptionTwo returns [String current=null] : iv_ruleOptionTwo= ruleOptionTwo EOF ;
    public final String entryRuleOptionTwo() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOptionTwo = null;


        try {
            // InternalHelloWorld.g:312:49: (iv_ruleOptionTwo= ruleOptionTwo EOF )
            // InternalHelloWorld.g:313:3: iv_ruleOptionTwo= ruleOptionTwo EOF
            {
             newCompositeNode(grammarAccess.getOptionTwoRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionTwo=ruleOptionTwo();

            state._fsp--;

             current =iv_ruleOptionTwo.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptionTwo"


    // $ANTLR start "ruleOptionTwo"
    // InternalHelloWorld.g:319:1: ruleOptionTwo returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther ) ;
    public final AntlrDatatypeRuleToken ruleOptionTwo() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_KeyTwo_0 = null;

        AntlrDatatypeRuleToken this_KeyOther_1 = null;



          enterRule();

        try {
            // InternalHelloWorld.g:325:2: ( (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther ) )
            // InternalHelloWorld.g:326:3: (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther )
            {
            // InternalHelloWorld.g:326:3: (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther )
            // InternalHelloWorld.g:327:5: this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther
            {

                  newCompositeNode(grammarAccess.getOptionTwoAccess().getKeyTwoParserRuleCall_0());
                
            pushFollow(FOLLOW_4);
            this_KeyTwo_0=ruleKeyTwo();

            state._fsp--;


                  current.merge(this_KeyTwo_0);
                

                  afterParserOrEnumRuleCall();
                

                  newCompositeNode(grammarAccess.getOptionTwoAccess().getKeyOtherParserRuleCall_1());
                
            pushFollow(FOLLOW_2);
            this_KeyOther_1=ruleKeyOther();

            state._fsp--;


                  current.merge(this_KeyOther_1);
                

                  afterParserOrEnumRuleCall();
                

            }


            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptionTwo"


    // $ANTLR start "entryRuleOptionThree"
    // InternalHelloWorld.g:351:1: entryRuleOptionThree returns [String current=null] : iv_ruleOptionThree= ruleOptionThree EOF ;
    public final String entryRuleOptionThree() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOptionThree = null;


        try {
            // InternalHelloWorld.g:351:51: (iv_ruleOptionThree= ruleOptionThree EOF )
            // InternalHelloWorld.g:352:3: iv_ruleOptionThree= ruleOptionThree EOF
            {
             newCompositeNode(grammarAccess.getOptionThreeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionThree=ruleOptionThree();

            state._fsp--;

             current =iv_ruleOptionThree.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptionThree"


    // $ANTLR start "ruleOptionThree"
    // InternalHelloWorld.g:358:1: ruleOptionThree returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_SimpleKeyFour_0= ruleSimpleKeyFour ;
    public final AntlrDatatypeRuleToken ruleOptionThree() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_SimpleKeyFour_0 = null;



          enterRule();

        try {
            // InternalHelloWorld.g:364:2: (this_SimpleKeyFour_0= ruleSimpleKeyFour )
            // InternalHelloWorld.g:365:3: this_SimpleKeyFour_0= ruleSimpleKeyFour
            {

                newCompositeNode(grammarAccess.getOptionThreeAccess().getSimpleKeyFourParserRuleCall());
              
            pushFollow(FOLLOW_2);
            this_SimpleKeyFour_0=ruleSimpleKeyFour();

            state._fsp--;


                current.merge(this_SimpleKeyFour_0);
              

                afterParserOrEnumRuleCall();
              

            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptionThree"


    // $ANTLR start "entryRuleOptionFour"
    // InternalHelloWorld.g:378:1: entryRuleOptionFour returns [String current=null] : iv_ruleOptionFour= ruleOptionFour EOF ;
    public final String entryRuleOptionFour() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOptionFour = null;


        try {
            // InternalHelloWorld.g:378:50: (iv_ruleOptionFour= ruleOptionFour EOF )
            // InternalHelloWorld.g:379:3: iv_ruleOptionFour= ruleOptionFour EOF
            {
             newCompositeNode(grammarAccess.getOptionFourRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionFour=ruleOptionFour();

            state._fsp--;

             current =iv_ruleOptionFour.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptionFour"


    // $ANTLR start "ruleOptionFour"
    // InternalHelloWorld.g:385:1: ruleOptionFour returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SimpleKeyFive_0= ruleSimpleKeyFive this_EnumLikeOther_1= ruleEnumLikeOther ) ;
    public final AntlrDatatypeRuleToken ruleOptionFour() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_SimpleKeyFive_0 = null;

        AntlrDatatypeRuleToken this_EnumLikeOther_1 = null;



          enterRule();

        try {
            // InternalHelloWorld.g:391:2: ( (this_SimpleKeyFive_0= ruleSimpleKeyFive this_EnumLikeOther_1= ruleEnumLikeOther ) )
            // InternalHelloWorld.g:392:3: (this_SimpleKeyFive_0= ruleSimpleKeyFive this_EnumLikeOther_1= ruleEnumLikeOther )
            {
            // InternalHelloWorld.g:392:3: (this_SimpleKeyFive_0= ruleSimpleKeyFive this_EnumLikeOther_1= ruleEnumLikeOther )
            // InternalHelloWorld.g:393:5: this_SimpleKeyFive_0= ruleSimpleKeyFive this_EnumLikeOther_1= ruleEnumLikeOther
            {

                  newCompositeNode(grammarAccess.getOptionFourAccess().getSimpleKeyFiveParserRuleCall_0());
                
            pushFollow(FOLLOW_6);
            this_SimpleKeyFive_0=ruleSimpleKeyFive();

            state._fsp--;


                  current.merge(this_SimpleKeyFive_0);
                

                  afterParserOrEnumRuleCall();
                

                  newCompositeNode(grammarAccess.getOptionFourAccess().getEnumLikeOtherParserRuleCall_1());
                
            pushFollow(FOLLOW_2);
            this_EnumLikeOther_1=ruleEnumLikeOther();

            state._fsp--;


                  current.merge(this_EnumLikeOther_1);
                

                  afterParserOrEnumRuleCall();
                

            }


            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptionFour"


    // $ANTLR start "entryRuleKeyOne"
    // InternalHelloWorld.g:417:1: entryRuleKeyOne returns [String current=null] : iv_ruleKeyOne= ruleKeyOne EOF ;
    public final String entryRuleKeyOne() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleKeyOne = null;


        try {
            // InternalHelloWorld.g:417:46: (iv_ruleKeyOne= ruleKeyOne EOF )
            // InternalHelloWorld.g:418:3: iv_ruleKeyOne= ruleKeyOne EOF
            {
             newCompositeNode(grammarAccess.getKeyOneRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeyOne=ruleKeyOne();

            state._fsp--;

             current =iv_ruleKeyOne.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeyOne"


    // $ANTLR start "ruleKeyOne"
    // InternalHelloWorld.g:424:1: ruleKeyOne returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : {...}?this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleKeyOne() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


          enterRule();

        try {
            // InternalHelloWorld.g:430:2: ({...}?this_ID_0= RULE_ID )
            // InternalHelloWorld.g:431:3: {...}?this_ID_0= RULE_ID
            {
            if ( !((predicates.isKeyOneEnabled(parserContext) /* @ErrorMessage(getKeyOneEnabledMessage) */)) ) {
                throw new FailedPredicateException(input, "ruleKeyOne", "predicates.isKeyOneEnabled(parserContext) /* @ErrorMessage(getKeyOneEnabledMessage) */");
            }
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                current.merge(this_ID_0);
              

                newLeafNode(this_ID_0, grammarAccess.getKeyOneAccess().getIDTerminalRuleCall());
              

            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeyOne"


    // $ANTLR start "entryRuleKeyTwo"
    // InternalHelloWorld.g:442:1: entryRuleKeyTwo returns [String current=null] : iv_ruleKeyTwo= ruleKeyTwo EOF ;
    public final String entryRuleKeyTwo() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleKeyTwo = null;


        try {
            // InternalHelloWorld.g:442:46: (iv_ruleKeyTwo= ruleKeyTwo EOF )
            // InternalHelloWorld.g:443:3: iv_ruleKeyTwo= ruleKeyTwo EOF
            {
             newCompositeNode(grammarAccess.getKeyTwoRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeyTwo=ruleKeyTwo();

            state._fsp--;

             current =iv_ruleKeyTwo.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeyTwo"


    // $ANTLR start "ruleKeyTwo"
    // InternalHelloWorld.g:449:1: ruleKeyTwo returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : {...}?this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleKeyTwo() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


          enterRule();

        try {
            // InternalHelloWorld.g:455:2: ({...}?this_ID_0= RULE_ID )
            // InternalHelloWorld.g:456:3: {...}?this_ID_0= RULE_ID
            {
            if ( !((predicates.isKeyTwoEnabled(parserContext) /* @ErrorMessage(getKeyTwoEnabledMessage) */)) ) {
                throw new FailedPredicateException(input, "ruleKeyTwo", "predicates.isKeyTwoEnabled(parserContext) /* @ErrorMessage(getKeyTwoEnabledMessage) */");
            }
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                current.merge(this_ID_0);
              

                newLeafNode(this_ID_0, grammarAccess.getKeyTwoAccess().getIDTerminalRuleCall());
              

            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeyTwo"


    // $ANTLR start "entryRuleKeyOther"
    // InternalHelloWorld.g:467:1: entryRuleKeyOther returns [String current=null] : iv_ruleKeyOther= ruleKeyOther EOF ;
    public final String entryRuleKeyOther() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleKeyOther = null;


        try {
            // InternalHelloWorld.g:467:48: (iv_ruleKeyOther= ruleKeyOther EOF )
            // InternalHelloWorld.g:468:3: iv_ruleKeyOther= ruleKeyOther EOF
            {
             newCompositeNode(grammarAccess.getKeyOtherRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleKeyOther=ruleKeyOther();

            state._fsp--;

             current =iv_ruleKeyOther.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleKeyOther"


    // $ANTLR start "ruleKeyOther"
    // InternalHelloWorld.g:474:1: ruleKeyOther returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : {...}?this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleKeyOther() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


          enterRule();

        try {
            // InternalHelloWorld.g:480:2: ({...}?this_ID_0= RULE_ID )
            // InternalHelloWorld.g:481:3: {...}?this_ID_0= RULE_ID
            {
            if ( !((predicates.isKeyOtherEnabled(parserContext) /* @ErrorMessage(getKeyOtherEnabledMessage) */)) ) {
                throw new FailedPredicateException(input, "ruleKeyOther", "predicates.isKeyOtherEnabled(parserContext) /* @ErrorMessage(getKeyOtherEnabledMessage) */");
            }
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                current.merge(this_ID_0);
              

                newLeafNode(this_ID_0, grammarAccess.getKeyOtherAccess().getIDTerminalRuleCall());
              

            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleKeyOther"


    // $ANTLR start "entryRuleSimpleKeyFour"
    // InternalHelloWorld.g:492:1: entryRuleSimpleKeyFour returns [String current=null] : iv_ruleSimpleKeyFour= ruleSimpleKeyFour EOF ;
    public final String entryRuleSimpleKeyFour() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSimpleKeyFour = null;


        try {
            // InternalHelloWorld.g:492:53: (iv_ruleSimpleKeyFour= ruleSimpleKeyFour EOF )
            // InternalHelloWorld.g:493:3: iv_ruleSimpleKeyFour= ruleSimpleKeyFour EOF
            {
             newCompositeNode(grammarAccess.getSimpleKeyFourRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleKeyFour=ruleSimpleKeyFour();

            state._fsp--;

             current =iv_ruleSimpleKeyFour.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleKeyFour"


    // $ANTLR start "ruleSimpleKeyFour"
    // InternalHelloWorld.g:499:1: ruleSimpleKeyFour returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'four' ;
    public final AntlrDatatypeRuleToken ruleSimpleKeyFour() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


          enterRule();

        try {
            // InternalHelloWorld.g:505:2: (kw= 'four' )
            // InternalHelloWorld.g:506:3: kw= 'four'
            {
            kw=(Token)match(input,13,FOLLOW_2); 

                current.merge(kw);
                newLeafNode(kw, grammarAccess.getSimpleKeyFourAccess().getFourKeyword());
              

            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleKeyFour"


    // $ANTLR start "entryRuleSimpleKeyFive"
    // InternalHelloWorld.g:514:1: entryRuleSimpleKeyFive returns [String current=null] : iv_ruleSimpleKeyFive= ruleSimpleKeyFive EOF ;
    public final String entryRuleSimpleKeyFive() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSimpleKeyFive = null;


        try {
            // InternalHelloWorld.g:514:53: (iv_ruleSimpleKeyFive= ruleSimpleKeyFive EOF )
            // InternalHelloWorld.g:515:3: iv_ruleSimpleKeyFive= ruleSimpleKeyFive EOF
            {
             newCompositeNode(grammarAccess.getSimpleKeyFiveRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleKeyFive=ruleSimpleKeyFive();

            state._fsp--;

             current =iv_ruleSimpleKeyFive.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleKeyFive"


    // $ANTLR start "ruleSimpleKeyFive"
    // InternalHelloWorld.g:521:1: ruleSimpleKeyFive returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'five' ;
    public final AntlrDatatypeRuleToken ruleSimpleKeyFive() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


          enterRule();

        try {
            // InternalHelloWorld.g:527:2: (kw= 'five' )
            // InternalHelloWorld.g:528:3: kw= 'five'
            {
            kw=(Token)match(input,14,FOLLOW_2); 

                current.merge(kw);
                newLeafNode(kw, grammarAccess.getSimpleKeyFiveAccess().getFiveKeyword());
              

            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleKeyFive"


    // $ANTLR start "entryRuleEnumLikeOther"
    // InternalHelloWorld.g:536:1: entryRuleEnumLikeOther returns [String current=null] : iv_ruleEnumLikeOther= ruleEnumLikeOther EOF ;
    public final String entryRuleEnumLikeOther() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEnumLikeOther = null;


        try {
            // InternalHelloWorld.g:536:53: (iv_ruleEnumLikeOther= ruleEnumLikeOther EOF )
            // InternalHelloWorld.g:537:3: iv_ruleEnumLikeOther= ruleEnumLikeOther EOF
            {
             newCompositeNode(grammarAccess.getEnumLikeOtherRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumLikeOther=ruleEnumLikeOther();

            state._fsp--;

             current =iv_ruleEnumLikeOther.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumLikeOther"


    // $ANTLR start "ruleEnumLikeOther"
    // InternalHelloWorld.g:543:1: ruleEnumLikeOther returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'four' | kw= 'five' | kw= 'six' ) ;
    public final AntlrDatatypeRuleToken ruleEnumLikeOther() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


          enterRule();

        try {
            // InternalHelloWorld.g:549:2: ( (kw= 'four' | kw= 'five' | kw= 'six' ) )
            // InternalHelloWorld.g:550:3: (kw= 'four' | kw= 'five' | kw= 'six' )
            {
            // InternalHelloWorld.g:550:3: (kw= 'four' | kw= 'five' | kw= 'six' )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt4=1;
                }
                break;
            case 14:
                {
                alt4=2;
                }
                break;
            case 15:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalHelloWorld.g:551:5: kw= 'four'
                    {
                    kw=(Token)match(input,13,FOLLOW_2); 

                          current.merge(kw);
                          newLeafNode(kw, grammarAccess.getEnumLikeOtherAccess().getFourKeyword_0());
                        

                    }
                    break;
                case 2 :
                    // InternalHelloWorld.g:557:5: kw= 'five'
                    {
                    kw=(Token)match(input,14,FOLLOW_2); 

                          current.merge(kw);
                          newLeafNode(kw, grammarAccess.getEnumLikeOtherAccess().getFiveKeyword_1());
                        

                    }
                    break;
                case 3 :
                    // InternalHelloWorld.g:563:5: kw= 'six'
                    {
                    kw=(Token)match(input,15,FOLLOW_2); 

                          current.merge(kw);
                          newLeafNode(kw, grammarAccess.getEnumLikeOtherAccess().getSixKeyword_2());
                        

                    }
                    break;

            }


            }


              leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEnumLikeOther"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000006812L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x000000000000E000L});

}