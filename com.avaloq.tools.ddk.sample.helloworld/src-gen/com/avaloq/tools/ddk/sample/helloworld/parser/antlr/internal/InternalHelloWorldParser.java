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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalHelloWorldParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Hello'", "'!'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_INT=5;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
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

        public InternalHelloWorldParser(TokenStream input, HelloWorldGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
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
    // InternalHelloWorld.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalHelloWorld.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalHelloWorld.g:65:2: iv_ruleModel= ruleModel EOF
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
    // InternalHelloWorld.g:71:1: ruleModel returns [EObject current=null] : ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) ) ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_greetings_0_0 = null;

        EObject lv_keywordsExample_1_0 = null;



        	enterRule();

        try {
            // InternalHelloWorld.g:77:2: ( ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) ) ) )
            // InternalHelloWorld.g:78:2: ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) ) )
            {
            // InternalHelloWorld.g:78:2: ( ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) ) )
            // InternalHelloWorld.g:79:3: ( (lv_greetings_0_0= ruleGreeting ) )* ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )
            {
            // InternalHelloWorld.g:79:3: ( (lv_greetings_0_0= ruleGreeting ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalHelloWorld.g:80:4: (lv_greetings_0_0= ruleGreeting )
            	    {
            	    // InternalHelloWorld.g:80:4: (lv_greetings_0_0= ruleGreeting )
            	    // InternalHelloWorld.g:81:5: lv_greetings_0_0= ruleGreeting
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

            // InternalHelloWorld.g:98:3: ( (lv_keywordsExample_1_0= ruleKeywordsExample ) )
            // InternalHelloWorld.g:99:4: (lv_keywordsExample_1_0= ruleKeywordsExample )
            {
            // InternalHelloWorld.g:99:4: (lv_keywordsExample_1_0= ruleKeywordsExample )
            // InternalHelloWorld.g:100:5: lv_keywordsExample_1_0= ruleKeywordsExample
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
    // InternalHelloWorld.g:121:1: entryRuleGreeting returns [EObject current=null] : iv_ruleGreeting= ruleGreeting EOF ;
    public final EObject entryRuleGreeting() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGreeting = null;


        try {
            // InternalHelloWorld.g:121:49: (iv_ruleGreeting= ruleGreeting EOF )
            // InternalHelloWorld.g:122:2: iv_ruleGreeting= ruleGreeting EOF
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
    // InternalHelloWorld.g:128:1: ruleGreeting returns [EObject current=null] : (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' ) ;
    public final EObject ruleGreeting() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalHelloWorld.g:134:2: ( (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' ) )
            // InternalHelloWorld.g:135:2: (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' )
            {
            // InternalHelloWorld.g:135:2: (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' )
            // InternalHelloWorld.g:136:3: otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getGreetingAccess().getHelloKeyword_0());
            		
            // InternalHelloWorld.g:140:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalHelloWorld.g:141:4: (lv_name_1_0= RULE_ID )
            {
            // InternalHelloWorld.g:141:4: (lv_name_1_0= RULE_ID )
            // InternalHelloWorld.g:142:5: lv_name_1_0= RULE_ID
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
    // InternalHelloWorld.g:166:1: entryRuleKeywordsExample returns [EObject current=null] : iv_ruleKeywordsExample= ruleKeywordsExample EOF ;
    public final EObject entryRuleKeywordsExample() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleKeywordsExample = null;


        try {
            // InternalHelloWorld.g:166:56: (iv_ruleKeywordsExample= ruleKeywordsExample EOF )
            // InternalHelloWorld.g:167:2: iv_ruleKeywordsExample= ruleKeywordsExample EOF
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
    // InternalHelloWorld.g:173:1: ruleKeywordsExample returns [EObject current=null] : ( ( (lv_option_0_0= ruleOptionOne ) ) | ( (lv_option_1_0= ruleOptionTwo ) ) ) ;
    public final EObject ruleKeywordsExample() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_option_0_0 = null;

        AntlrDatatypeRuleToken lv_option_1_0 = null;



        	enterRule();

        try {
            // InternalHelloWorld.g:179:2: ( ( ( (lv_option_0_0= ruleOptionOne ) ) | ( (lv_option_1_0= ruleOptionTwo ) ) ) )
            // InternalHelloWorld.g:180:2: ( ( (lv_option_0_0= ruleOptionOne ) ) | ( (lv_option_1_0= ruleOptionTwo ) ) )
            {
            // InternalHelloWorld.g:180:2: ( ( (lv_option_0_0= ruleOptionOne ) ) | ( (lv_option_1_0= ruleOptionTwo ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ID) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==RULE_ID) ) {
                    alt2=2;
                }
                else if ( (LA2_1==EOF) ) {
                    alt2=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalHelloWorld.g:181:3: ( (lv_option_0_0= ruleOptionOne ) )
                    {
                    // InternalHelloWorld.g:181:3: ( (lv_option_0_0= ruleOptionOne ) )
                    // InternalHelloWorld.g:182:4: (lv_option_0_0= ruleOptionOne )
                    {
                    // InternalHelloWorld.g:182:4: (lv_option_0_0= ruleOptionOne )
                    // InternalHelloWorld.g:183:5: lv_option_0_0= ruleOptionOne
                    {

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
                    // InternalHelloWorld.g:201:3: ( (lv_option_1_0= ruleOptionTwo ) )
                    {
                    // InternalHelloWorld.g:201:3: ( (lv_option_1_0= ruleOptionTwo ) )
                    // InternalHelloWorld.g:202:4: (lv_option_1_0= ruleOptionTwo )
                    {
                    // InternalHelloWorld.g:202:4: (lv_option_1_0= ruleOptionTwo )
                    // InternalHelloWorld.g:203:5: lv_option_1_0= ruleOptionTwo
                    {

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
    // InternalHelloWorld.g:224:1: entryRuleOptionOne returns [String current=null] : iv_ruleOptionOne= ruleOptionOne EOF ;
    public final String entryRuleOptionOne() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOptionOne = null;


        try {
            // InternalHelloWorld.g:224:49: (iv_ruleOptionOne= ruleOptionOne EOF )
            // InternalHelloWorld.g:225:2: iv_ruleOptionOne= ruleOptionOne EOF
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
    // InternalHelloWorld.g:231:1: ruleOptionOne returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_KeyOne_0= ruleKeyOne ;
    public final AntlrDatatypeRuleToken ruleOptionOne() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_KeyOne_0 = null;



        	enterRule();

        try {
            // InternalHelloWorld.g:237:2: (this_KeyOne_0= ruleKeyOne )
            // InternalHelloWorld.g:238:2: this_KeyOne_0= ruleKeyOne
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
    // InternalHelloWorld.g:251:1: entryRuleOptionTwo returns [String current=null] : iv_ruleOptionTwo= ruleOptionTwo EOF ;
    public final String entryRuleOptionTwo() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOptionTwo = null;


        try {
            // InternalHelloWorld.g:251:49: (iv_ruleOptionTwo= ruleOptionTwo EOF )
            // InternalHelloWorld.g:252:2: iv_ruleOptionTwo= ruleOptionTwo EOF
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
    // InternalHelloWorld.g:258:1: ruleOptionTwo returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther ) ;
    public final AntlrDatatypeRuleToken ruleOptionTwo() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_KeyTwo_0 = null;

        AntlrDatatypeRuleToken this_KeyOther_1 = null;



        	enterRule();

        try {
            // InternalHelloWorld.g:264:2: ( (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther ) )
            // InternalHelloWorld.g:265:2: (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther )
            {
            // InternalHelloWorld.g:265:2: (this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther )
            // InternalHelloWorld.g:266:3: this_KeyTwo_0= ruleKeyTwo this_KeyOther_1= ruleKeyOther
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


    // $ANTLR start "entryRuleKeyOne"
    // InternalHelloWorld.g:290:1: entryRuleKeyOne returns [String current=null] : iv_ruleKeyOne= ruleKeyOne EOF ;
    public final String entryRuleKeyOne() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleKeyOne = null;


        try {
            // InternalHelloWorld.g:290:46: (iv_ruleKeyOne= ruleKeyOne EOF )
            // InternalHelloWorld.g:291:2: iv_ruleKeyOne= ruleKeyOne EOF
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
    // InternalHelloWorld.g:297:1: ruleKeyOne returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleKeyOne() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalHelloWorld.g:303:2: (this_ID_0= RULE_ID )
            // InternalHelloWorld.g:304:2: this_ID_0= RULE_ID
            {
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
    // InternalHelloWorld.g:314:1: entryRuleKeyTwo returns [String current=null] : iv_ruleKeyTwo= ruleKeyTwo EOF ;
    public final String entryRuleKeyTwo() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleKeyTwo = null;


        try {
            // InternalHelloWorld.g:314:46: (iv_ruleKeyTwo= ruleKeyTwo EOF )
            // InternalHelloWorld.g:315:2: iv_ruleKeyTwo= ruleKeyTwo EOF
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
    // InternalHelloWorld.g:321:1: ruleKeyTwo returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleKeyTwo() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalHelloWorld.g:327:2: (this_ID_0= RULE_ID )
            // InternalHelloWorld.g:328:2: this_ID_0= RULE_ID
            {
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
    // InternalHelloWorld.g:338:1: entryRuleKeyOther returns [String current=null] : iv_ruleKeyOther= ruleKeyOther EOF ;
    public final String entryRuleKeyOther() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleKeyOther = null;


        try {
            // InternalHelloWorld.g:338:48: (iv_ruleKeyOther= ruleKeyOther EOF )
            // InternalHelloWorld.g:339:2: iv_ruleKeyOther= ruleKeyOther EOF
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
    // InternalHelloWorld.g:345:1: ruleKeyOther returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleKeyOther() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalHelloWorld.g:351:2: (this_ID_0= RULE_ID )
            // InternalHelloWorld.g:352:2: this_ID_0= RULE_ID
            {
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

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});

}