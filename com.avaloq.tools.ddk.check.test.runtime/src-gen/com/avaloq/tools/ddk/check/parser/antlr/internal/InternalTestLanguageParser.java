package com.avaloq.tools.ddk.check.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.avaloq.tools.ddk.check.services.TestLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalTestLanguageParser extends AbstractInternalAntlrParser {
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


        public InternalTestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalTestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalTestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g"; }



     	private TestLanguageGrammarAccess grammarAccess;
     	
        public InternalTestLanguageParser(TokenStream input, TestLanguageGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Model";	
       	}
       	
       	@Override
       	protected TestLanguageGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleModel"
    // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:67:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:68:2: (iv_ruleModel= ruleModel EOF )
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:69:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel75);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel85); 

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
    // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:76:1: ruleModel returns [EObject current=null] : ( (lv_greetings_0_0= ruleGreeting ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_greetings_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:79:28: ( ( (lv_greetings_0_0= ruleGreeting ) )* )
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:80:1: ( (lv_greetings_0_0= ruleGreeting ) )*
            {
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:80:1: ( (lv_greetings_0_0= ruleGreeting ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:81:1: (lv_greetings_0_0= ruleGreeting )
            	    {
            	    // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:81:1: (lv_greetings_0_0= ruleGreeting )
            	    // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:82:3: lv_greetings_0_0= ruleGreeting
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getModelAccess().getGreetingsGreetingParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleGreeting_in_ruleModel130);
            	    lv_greetings_0_0=ruleGreeting();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"greetings",
            	            		lv_greetings_0_0, 
            	            		"Greeting");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


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
    // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:106:1: entryRuleGreeting returns [EObject current=null] : iv_ruleGreeting= ruleGreeting EOF ;
    public final EObject entryRuleGreeting() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGreeting = null;


        try {
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:107:2: (iv_ruleGreeting= ruleGreeting EOF )
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:108:2: iv_ruleGreeting= ruleGreeting EOF
            {
             newCompositeNode(grammarAccess.getGreetingRule()); 
            pushFollow(FOLLOW_ruleGreeting_in_entryRuleGreeting166);
            iv_ruleGreeting=ruleGreeting();

            state._fsp--;

             current =iv_ruleGreeting; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleGreeting176); 

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
    // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:115:1: ruleGreeting returns [EObject current=null] : (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' ) ;
    public final EObject ruleGreeting() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:118:28: ( (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' ) )
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:119:1: (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' )
            {
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:119:1: (otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!' )
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:119:3: otherlv_0= 'Hello' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '!'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleGreeting213); 

                	newLeafNode(otherlv_0, grammarAccess.getGreetingAccess().getHelloKeyword_0());
                
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:123:1: ( (lv_name_1_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:124:1: (lv_name_1_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:124:1: (lv_name_1_0= RULE_ID )
            // ../com.avaloq.tools.ddk.check.test.runtime/src-gen/com/avaloq/tools/ddk/check/parser/antlr/internal/InternalTestLanguage.g:125:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleGreeting230); 

            			newLeafNode(lv_name_1_0, grammarAccess.getGreetingAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getGreetingRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleGreeting247); 

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

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGreeting_in_ruleModel130 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleGreeting_in_entryRuleGreeting166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGreeting176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleGreeting213 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleGreeting230 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleGreeting247 = new BitSet(new long[]{0x0000000000000002L});

}