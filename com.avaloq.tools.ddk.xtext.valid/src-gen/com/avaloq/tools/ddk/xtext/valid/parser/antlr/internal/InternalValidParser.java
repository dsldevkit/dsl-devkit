package com.avaloq.tools.ddk.xtext.valid.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.avaloq.tools.ddk.xtext.valid.services.ValidGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalValidParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'import'", "'category'", "'label'", "'description'", "'{'", "'}'", "'optional'", "'message'", "'context'", "'size'", "'..'", "'range'", "'unique'", "'#'", "';'", "'marker'", "'as'", "'quickfixes'", "'fix'", "'::'", "'fast'", "'normal'", "'expensive'", "'error'", "'warning'", "'semantic'", "'textual'"
    };
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=5;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalValidParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalValidParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalValidParser.tokenNames; }
    public String getGrammarFileName() { return "InternalValid.g"; }



     	private ValidGrammarAccess grammarAccess;
     	
        public InternalValidParser(TokenStream input, ValidGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "ValidModel";	
       	}
       	
       	@Override
       	protected ValidGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleValidModel"
    // InternalValid.g:68:1: entryRuleValidModel returns [EObject current=null] : iv_ruleValidModel= ruleValidModel EOF ;
    public final EObject entryRuleValidModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValidModel = null;


        try {
            // InternalValid.g:69:2: (iv_ruleValidModel= ruleValidModel EOF )
            // InternalValid.g:70:2: iv_ruleValidModel= ruleValidModel EOF
            {
             newCompositeNode(grammarAccess.getValidModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValidModel=ruleValidModel();

            state._fsp--;

             current =iv_ruleValidModel; 
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
    // $ANTLR end "entryRuleValidModel"


    // $ANTLR start "ruleValidModel"
    // InternalValid.g:77:1: ruleValidModel returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* ) ;
    public final EObject ruleValidModel() throws RecognitionException {
        EObject current = null;

        EObject lv_imports_0_0 = null;

        EObject lv_categories_1_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:80:28: ( ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* ) )
            // InternalValid.g:81:1: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* )
            {
            // InternalValid.g:81:1: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* )
            // InternalValid.g:81:2: ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )*
            {
            // InternalValid.g:81:2: ( (lv_imports_0_0= ruleImport ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalValid.g:82:1: (lv_imports_0_0= ruleImport )
            	    {
            	    // InternalValid.g:82:1: (lv_imports_0_0= ruleImport )
            	    // InternalValid.g:83:3: lv_imports_0_0= ruleImport
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getValidModelAccess().getImportsImportParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_3);
            	    lv_imports_0_0=ruleImport();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getValidModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"imports",
            	            		lv_imports_0_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.Import");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalValid.g:99:3: ( (lv_categories_1_0= ruleCategory ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==12) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalValid.g:100:1: (lv_categories_1_0= ruleCategory )
            	    {
            	    // InternalValid.g:100:1: (lv_categories_1_0= ruleCategory )
            	    // InternalValid.g:101:3: lv_categories_1_0= ruleCategory
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getValidModelAccess().getCategoriesCategoryParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_4);
            	    lv_categories_1_0=ruleCategory();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getValidModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"categories",
            	            		lv_categories_1_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.Category");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


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
    // $ANTLR end "ruleValidModel"


    // $ANTLR start "entryRuleImport"
    // InternalValid.g:125:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalValid.g:126:2: (iv_ruleImport= ruleImport EOF )
            // InternalValid.g:127:2: iv_ruleImport= ruleImport EOF
            {
             newCompositeNode(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImport=ruleImport();

            state._fsp--;

             current =iv_ruleImport; 
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
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalValid.g:134:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // InternalValid.g:137:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) ) )
            // InternalValid.g:138:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) )
            {
            // InternalValid.g:138:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) )
            // InternalValid.g:138:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,11,FOLLOW_5); 

                	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                
            // InternalValid.g:142:1: ( (otherlv_1= RULE_STRING ) )
            // InternalValid.g:143:1: (otherlv_1= RULE_STRING )
            {
            // InternalValid.g:143:1: (otherlv_1= RULE_STRING )
            // InternalValid.g:144:3: otherlv_1= RULE_STRING
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getImportRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_2); 

            		newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
            	

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
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleCategory"
    // InternalValid.g:163:1: entryRuleCategory returns [EObject current=null] : iv_ruleCategory= ruleCategory EOF ;
    public final EObject entryRuleCategory() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCategory = null;


        try {
            // InternalValid.g:164:2: (iv_ruleCategory= ruleCategory EOF )
            // InternalValid.g:165:2: iv_ruleCategory= ruleCategory EOF
            {
             newCompositeNode(grammarAccess.getCategoryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCategory=ruleCategory();

            state._fsp--;

             current =iv_ruleCategory; 
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
    // $ANTLR end "entryRuleCategory"


    // $ANTLR start "ruleCategory"
    // InternalValid.g:172:1: ruleCategory returns [EObject current=null] : (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' ) ;
    public final EObject ruleCategory() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_label_3_0=null;
        Token otherlv_4=null;
        Token lv_description_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_rules_7_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:175:28: ( (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' ) )
            // InternalValid.g:176:1: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' )
            {
            // InternalValid.g:176:1: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' )
            // InternalValid.g:176:3: otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_6); 

                	newLeafNode(otherlv_0, grammarAccess.getCategoryAccess().getCategoryKeyword_0());
                
            // InternalValid.g:180:1: ( (lv_name_1_0= RULE_ID ) )
            // InternalValid.g:181:1: (lv_name_1_0= RULE_ID )
            {
            // InternalValid.g:181:1: (lv_name_1_0= RULE_ID )
            // InternalValid.g:182:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            			newLeafNode(lv_name_1_0, grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getCategoryRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_5); 

                	newLeafNode(otherlv_2, grammarAccess.getCategoryAccess().getLabelKeyword_2());
                
            // InternalValid.g:202:1: ( (lv_label_3_0= RULE_STRING ) )
            // InternalValid.g:203:1: (lv_label_3_0= RULE_STRING )
            {
            // InternalValid.g:203:1: (lv_label_3_0= RULE_STRING )
            // InternalValid.g:204:3: lv_label_3_0= RULE_STRING
            {
            lv_label_3_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            			newLeafNode(lv_label_3_0, grammarAccess.getCategoryAccess().getLabelSTRINGTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getCategoryRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_3_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalValid.g:220:2: (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalValid.g:220:4: otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,14,FOLLOW_5); 

                        	newLeafNode(otherlv_4, grammarAccess.getCategoryAccess().getDescriptionKeyword_4_0());
                        
                    // InternalValid.g:224:1: ( (lv_description_5_0= RULE_STRING ) )
                    // InternalValid.g:225:1: (lv_description_5_0= RULE_STRING )
                    {
                    // InternalValid.g:225:1: (lv_description_5_0= RULE_STRING )
                    // InternalValid.g:226:3: lv_description_5_0= RULE_STRING
                    {
                    lv_description_5_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

                    			newLeafNode(lv_description_5_0, grammarAccess.getCategoryAccess().getDescriptionSTRINGTerminalRuleCall_4_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getCategoryRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,15,FOLLOW_10); 

                	newLeafNode(otherlv_6, grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_5());
                
            // InternalValid.g:246:1: ( (lv_rules_7_0= ruleRule ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17||LA4_0==20||(LA4_0>=22 && LA4_0<=23)||(LA4_0>=31 && LA4_0<=35)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalValid.g:247:1: (lv_rules_7_0= ruleRule )
            	    {
            	    // InternalValid.g:247:1: (lv_rules_7_0= ruleRule )
            	    // InternalValid.g:248:3: lv_rules_7_0= ruleRule
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getCategoryAccess().getRulesRuleParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_10);
            	    lv_rules_7_0=ruleRule();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getCategoryRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"rules",
            	            		lv_rules_7_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.Rule");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_8=(Token)match(input,16,FOLLOW_2); 

                	newLeafNode(otherlv_8, grammarAccess.getCategoryAccess().getRightCurlyBracketKeyword_7());
                

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
    // $ANTLR end "ruleCategory"


    // $ANTLR start "entryRuleRule"
    // InternalValid.g:276:1: entryRuleRule returns [EObject current=null] : iv_ruleRule= ruleRule EOF ;
    public final EObject entryRuleRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRule = null;


        try {
            // InternalValid.g:277:2: (iv_ruleRule= ruleRule EOF )
            // InternalValid.g:278:2: iv_ruleRule= ruleRule EOF
            {
             newCompositeNode(grammarAccess.getRuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRule=ruleRule();

            state._fsp--;

             current =iv_ruleRule; 
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
    // $ANTLR end "entryRuleRule"


    // $ANTLR start "ruleRule"
    // InternalValid.g:285:1: ruleRule returns [EObject current=null] : (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule ) ;
    public final EObject ruleRule() throws RecognitionException {
        EObject current = null;

        EObject this_NativeRule_0 = null;

        EObject this_PredefinedRule_1 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:288:28: ( (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule ) )
            // InternalValid.g:289:1: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )
            {
            // InternalValid.g:289:1: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // InternalValid.g:290:5: this_NativeRule_0= ruleNativeRule
                    {
                     
                            newCompositeNode(grammarAccess.getRuleAccess().getNativeRuleParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_NativeRule_0=ruleNativeRule();

                    state._fsp--;

                     
                            current = this_NativeRule_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalValid.g:300:5: this_PredefinedRule_1= rulePredefinedRule
                    {
                     
                            newCompositeNode(grammarAccess.getRuleAccess().getPredefinedRuleParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_PredefinedRule_1=rulePredefinedRule();

                    state._fsp--;

                     
                            current = this_PredefinedRule_1; 
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "ruleRule"


    // $ANTLR start "entryRulePredefinedRule"
    // InternalValid.g:316:1: entryRulePredefinedRule returns [EObject current=null] : iv_rulePredefinedRule= rulePredefinedRule EOF ;
    public final EObject entryRulePredefinedRule() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredefinedRule = null;


        try {
            // InternalValid.g:317:2: (iv_rulePredefinedRule= rulePredefinedRule EOF )
            // InternalValid.g:318:2: iv_rulePredefinedRule= rulePredefinedRule EOF
            {
             newCompositeNode(grammarAccess.getPredefinedRuleRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePredefinedRule=rulePredefinedRule();

            state._fsp--;

             current =iv_rulePredefinedRule; 
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
    // $ANTLR end "entryRulePredefinedRule"


    // $ANTLR start "rulePredefinedRule"
    // InternalValid.g:325:1: rulePredefinedRule returns [EObject current=null] : (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule ) ;
    public final EObject rulePredefinedRule() throws RecognitionException {
        EObject current = null;

        EObject this_SizeRule_0 = null;

        EObject this_RangeRule_1 = null;

        EObject this_UniqueRule_2 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:328:28: ( (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule ) )
            // InternalValid.g:329:1: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )
            {
            // InternalValid.g:329:1: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // InternalValid.g:330:5: this_SizeRule_0= ruleSizeRule
                    {
                     
                            newCompositeNode(grammarAccess.getPredefinedRuleAccess().getSizeRuleParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_2);
                    this_SizeRule_0=ruleSizeRule();

                    state._fsp--;

                     
                            current = this_SizeRule_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // InternalValid.g:340:5: this_RangeRule_1= ruleRangeRule
                    {
                     
                            newCompositeNode(grammarAccess.getPredefinedRuleAccess().getRangeRuleParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_2);
                    this_RangeRule_1=ruleRangeRule();

                    state._fsp--;

                     
                            current = this_RangeRule_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // InternalValid.g:350:5: this_UniqueRule_2= ruleUniqueRule
                    {
                     
                            newCompositeNode(grammarAccess.getPredefinedRuleAccess().getUniqueRuleParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_2);
                    this_UniqueRule_2=ruleUniqueRule();

                    state._fsp--;

                     
                            current = this_UniqueRule_2; 
                            afterParserOrEnumRuleCall();
                        

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
    // $ANTLR end "rulePredefinedRule"


    // $ANTLR start "entryRuleNativeRule"
    // InternalValid.g:366:1: entryRuleNativeRule returns [EObject current=null] : iv_ruleNativeRule= ruleNativeRule EOF ;
    public final EObject entryRuleNativeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNativeRule = null;


        try {
            // InternalValid.g:367:2: (iv_ruleNativeRule= ruleNativeRule EOF )
            // InternalValid.g:368:2: iv_ruleNativeRule= ruleNativeRule EOF
            {
             newCompositeNode(grammarAccess.getNativeRuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNativeRule=ruleNativeRule();

            state._fsp--;

             current =iv_ruleNativeRule; 
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
    // $ANTLR end "entryRuleNativeRule"


    // $ANTLR start "ruleNativeRule"
    // InternalValid.g:375:1: ruleNativeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' ) ;
    public final EObject ruleNativeRule() throws RecognitionException {
        EObject current = null;

        Token lv_optional_1_0=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token lv_label_6_0=null;
        Token otherlv_7=null;
        Token lv_description_8_0=null;
        Token otherlv_9=null;
        Token lv_message_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Enumerator lv_checkKind_2_0 = null;

        Enumerator lv_severity_3_0 = null;

        EObject lv_contexts_13_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:378:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' ) )
            // InternalValid.g:379:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' )
            {
            // InternalValid.g:379:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' )
            // InternalValid.g:379:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}'
            {
            // InternalValid.g:379:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:381:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:381:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:382:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	
            // InternalValid.g:385:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:386:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:386:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( LA7_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt7=1;
                }
                else if ( LA7_0 >= 31 && LA7_0 <= 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalValid.g:388:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:388:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:389:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:389:107: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:390:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // InternalValid.g:393:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:393:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "true");
            	    }
            	    // InternalValid.g:393:16: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:394:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:394:1: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:395:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_11); 

            	            newLeafNode(lv_optional_1_0, grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	        

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getNativeRuleRule());
            	    	        }
            	           		setWithLastConsumed(current, "optional", true, "optional");
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:415:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:415:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:416:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:416:107: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:417:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // InternalValid.g:420:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:420:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "true");
            	    }
            	    // InternalValid.g:420:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:421:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:421:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:422:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNativeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_11);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getNativeRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.CheckKind");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	

            }

            // InternalValid.g:452:2: ( (lv_severity_3_0= ruleSeverityKind ) )
            // InternalValid.g:453:1: (lv_severity_3_0= ruleSeverityKind )
            {
            // InternalValid.g:453:1: (lv_severity_3_0= ruleSeverityKind )
            // InternalValid.g:454:3: lv_severity_3_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getNativeRuleAccess().getSeveritySeverityKindEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_6);
            lv_severity_3_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNativeRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_3_0, 
                    		"com.avaloq.tools.ddk.xtext.valid.Valid.SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalValid.g:470:2: ( (lv_name_4_0= RULE_ID ) )
            // InternalValid.g:471:1: (lv_name_4_0= RULE_ID )
            {
            // InternalValid.g:471:1: (lv_name_4_0= RULE_ID )
            // InternalValid.g:472:3: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            			newLeafNode(lv_name_4_0, grammarAccess.getNativeRuleAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_5=(Token)match(input,13,FOLLOW_5); 

                	newLeafNode(otherlv_5, grammarAccess.getNativeRuleAccess().getLabelKeyword_3());
                
            // InternalValid.g:492:1: ( (lv_label_6_0= RULE_STRING ) )
            // InternalValid.g:493:1: (lv_label_6_0= RULE_STRING )
            {
            // InternalValid.g:493:1: (lv_label_6_0= RULE_STRING )
            // InternalValid.g:494:3: lv_label_6_0= RULE_STRING
            {
            lv_label_6_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            			newLeafNode(lv_label_6_0, grammarAccess.getNativeRuleAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_6_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalValid.g:510:2: (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==14) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalValid.g:510:4: otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_5); 

                        	newLeafNode(otherlv_7, grammarAccess.getNativeRuleAccess().getDescriptionKeyword_5_0());
                        
                    // InternalValid.g:514:1: ( (lv_description_8_0= RULE_STRING ) )
                    // InternalValid.g:515:1: (lv_description_8_0= RULE_STRING )
                    {
                    // InternalValid.g:515:1: (lv_description_8_0= RULE_STRING )
                    // InternalValid.g:516:3: lv_description_8_0= RULE_STRING
                    {
                    lv_description_8_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

                    			newLeafNode(lv_description_8_0, grammarAccess.getNativeRuleAccess().getDescriptionSTRINGTerminalRuleCall_5_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_8_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_9=(Token)match(input,18,FOLLOW_5); 

                	newLeafNode(otherlv_9, grammarAccess.getNativeRuleAccess().getMessageKeyword_6());
                
            // InternalValid.g:536:1: ( (lv_message_10_0= RULE_STRING ) )
            // InternalValid.g:537:1: (lv_message_10_0= RULE_STRING )
            {
            // InternalValid.g:537:1: (lv_message_10_0= RULE_STRING )
            // InternalValid.g:538:3: lv_message_10_0= RULE_STRING
            {
            lv_message_10_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

            			newLeafNode(lv_message_10_0, grammarAccess.getNativeRuleAccess().getMessageSTRINGTerminalRuleCall_7_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"message",
                    		lv_message_10_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            otherlv_11=(Token)match(input,19,FOLLOW_9); 

                	newLeafNode(otherlv_11, grammarAccess.getNativeRuleAccess().getContextKeyword_8());
                
            otherlv_12=(Token)match(input,15,FOLLOW_6); 

                	newLeafNode(otherlv_12, grammarAccess.getNativeRuleAccess().getLeftCurlyBracketKeyword_9());
                
            // InternalValid.g:562:1: ( (lv_contexts_13_0= ruleNativeContext ) )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalValid.g:563:1: (lv_contexts_13_0= ruleNativeContext )
            	    {
            	    // InternalValid.g:563:1: (lv_contexts_13_0= ruleNativeContext )
            	    // InternalValid.g:564:3: lv_contexts_13_0= ruleNativeContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNativeRuleAccess().getContextsNativeContextParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_contexts_13_0=ruleNativeContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getNativeRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_13_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.NativeContext");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            otherlv_14=(Token)match(input,16,FOLLOW_2); 

                	newLeafNode(otherlv_14, grammarAccess.getNativeRuleAccess().getRightCurlyBracketKeyword_11());
                

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
    // $ANTLR end "ruleNativeRule"


    // $ANTLR start "entryRuleSizeRule"
    // InternalValid.g:592:1: entryRuleSizeRule returns [EObject current=null] : iv_ruleSizeRule= ruleSizeRule EOF ;
    public final EObject entryRuleSizeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSizeRule = null;


        try {
            // InternalValid.g:593:2: (iv_ruleSizeRule= ruleSizeRule EOF )
            // InternalValid.g:594:2: iv_ruleSizeRule= ruleSizeRule EOF
            {
             newCompositeNode(grammarAccess.getSizeRuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSizeRule=ruleSizeRule();

            state._fsp--;

             current =iv_ruleSizeRule; 
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
    // $ANTLR end "entryRuleSizeRule"


    // $ANTLR start "ruleSizeRule"
    // InternalValid.g:601:1: ruleSizeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) ;
    public final EObject ruleSizeRule() throws RecognitionException {
        EObject current = null;

        Token lv_optional_1_0=null;
        Token otherlv_3=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token lv_label_7_0=null;
        Token otherlv_8=null;
        Token lv_description_9_0=null;
        Token otherlv_10=null;
        Token lv_message_11_0=null;
        Token otherlv_12=null;
        Token lv_min_13_0=null;
        Token otherlv_14=null;
        Token lv_max_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Enumerator lv_checkKind_2_0 = null;

        Enumerator lv_severity_4_0 = null;

        EObject lv_contexts_18_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:604:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) )
            // InternalValid.g:605:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            {
            // InternalValid.g:605:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            // InternalValid.g:605:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}'
            {
            // InternalValid.g:605:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:607:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:607:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:608:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	
            // InternalValid.g:611:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:612:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:612:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( LA10_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt10=1;
                }
                else if ( LA10_0 >= 31 && LA10_0 <= 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalValid.g:614:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:614:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:615:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:615:105: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:616:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // InternalValid.g:619:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:619:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "true");
            	    }
            	    // InternalValid.g:619:16: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:620:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:620:1: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:621:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_16); 

            	            newLeafNode(lv_optional_1_0, grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	        

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getSizeRuleRule());
            	    	        }
            	           		setWithLastConsumed(current, "optional", true, "optional");
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:641:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:641:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:642:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:642:105: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:643:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // InternalValid.g:646:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:646:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "true");
            	    }
            	    // InternalValid.g:646:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:647:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:647:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:648:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSizeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_16);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSizeRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.CheckKind");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	

            }

            otherlv_3=(Token)match(input,20,FOLLOW_11); 

                	newLeafNode(otherlv_3, grammarAccess.getSizeRuleAccess().getSizeKeyword_1());
                
            // InternalValid.g:682:1: ( (lv_severity_4_0= ruleSeverityKind ) )
            // InternalValid.g:683:1: (lv_severity_4_0= ruleSeverityKind )
            {
            // InternalValid.g:683:1: (lv_severity_4_0= ruleSeverityKind )
            // InternalValid.g:684:3: lv_severity_4_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getSizeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_6);
            lv_severity_4_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSizeRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_4_0, 
                    		"com.avaloq.tools.ddk.xtext.valid.Valid.SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalValid.g:700:2: ( (lv_name_5_0= RULE_ID ) )
            // InternalValid.g:701:1: (lv_name_5_0= RULE_ID )
            {
            // InternalValid.g:701:1: (lv_name_5_0= RULE_ID )
            // InternalValid.g:702:3: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            			newLeafNode(lv_name_5_0, grammarAccess.getSizeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSizeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_5_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_6=(Token)match(input,13,FOLLOW_5); 

                	newLeafNode(otherlv_6, grammarAccess.getSizeRuleAccess().getLabelKeyword_4());
                
            // InternalValid.g:722:1: ( (lv_label_7_0= RULE_STRING ) )
            // InternalValid.g:723:1: (lv_label_7_0= RULE_STRING )
            {
            // InternalValid.g:723:1: (lv_label_7_0= RULE_STRING )
            // InternalValid.g:724:3: lv_label_7_0= RULE_STRING
            {
            lv_label_7_0=(Token)match(input,RULE_STRING,FOLLOW_17); 

            			newLeafNode(lv_label_7_0, grammarAccess.getSizeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSizeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_7_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalValid.g:740:2: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==14) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalValid.g:740:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_5); 

                        	newLeafNode(otherlv_8, grammarAccess.getSizeRuleAccess().getDescriptionKeyword_6_0());
                        
                    // InternalValid.g:744:1: ( (lv_description_9_0= RULE_STRING ) )
                    // InternalValid.g:745:1: (lv_description_9_0= RULE_STRING )
                    {
                    // InternalValid.g:745:1: (lv_description_9_0= RULE_STRING )
                    // InternalValid.g:746:3: lv_description_9_0= RULE_STRING
                    {
                    lv_description_9_0=(Token)match(input,RULE_STRING,FOLLOW_18); 

                    			newLeafNode(lv_description_9_0, grammarAccess.getSizeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSizeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_9_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalValid.g:762:4: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==18) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalValid.g:762:6: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_5); 

                        	newLeafNode(otherlv_10, grammarAccess.getSizeRuleAccess().getMessageKeyword_7_0());
                        
                    // InternalValid.g:766:1: ( (lv_message_11_0= RULE_STRING ) )
                    // InternalValid.g:767:1: (lv_message_11_0= RULE_STRING )
                    {
                    // InternalValid.g:767:1: (lv_message_11_0= RULE_STRING )
                    // InternalValid.g:768:3: lv_message_11_0= RULE_STRING
                    {
                    lv_message_11_0=(Token)match(input,RULE_STRING,FOLLOW_19); 

                    			newLeafNode(lv_message_11_0, grammarAccess.getSizeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSizeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"message",
                            		lv_message_11_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,20,FOLLOW_20); 

                	newLeafNode(otherlv_12, grammarAccess.getSizeRuleAccess().getSizeKeyword_8());
                
            // InternalValid.g:788:1: ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==RULE_INT) ) {
                int LA13_1 = input.LA(2);

                if ( (LA13_1==21) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // InternalValid.g:788:2: ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..'
                    {
                    // InternalValid.g:788:2: ( (lv_min_13_0= RULE_INT ) )
                    // InternalValid.g:789:1: (lv_min_13_0= RULE_INT )
                    {
                    // InternalValid.g:789:1: (lv_min_13_0= RULE_INT )
                    // InternalValid.g:790:3: lv_min_13_0= RULE_INT
                    {
                    lv_min_13_0=(Token)match(input,RULE_INT,FOLLOW_21); 

                    			newLeafNode(lv_min_13_0, grammarAccess.getSizeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSizeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"min",
                            		lv_min_13_0, 
                            		"org.eclipse.xtext.common.Terminals.INT");
                    	    

                    }


                    }

                    otherlv_14=(Token)match(input,21,FOLLOW_20); 

                        	newLeafNode(otherlv_14, grammarAccess.getSizeRuleAccess().getFullStopFullStopKeyword_9_1());
                        

                    }
                    break;

            }

            // InternalValid.g:810:3: ( (lv_max_15_0= RULE_INT ) )
            // InternalValid.g:811:1: (lv_max_15_0= RULE_INT )
            {
            // InternalValid.g:811:1: (lv_max_15_0= RULE_INT )
            // InternalValid.g:812:3: lv_max_15_0= RULE_INT
            {
            lv_max_15_0=(Token)match(input,RULE_INT,FOLLOW_14); 

            			newLeafNode(lv_max_15_0, grammarAccess.getSizeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSizeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"max",
                    		lv_max_15_0, 
                    		"org.eclipse.xtext.common.Terminals.INT");
            	    

            }


            }

            otherlv_16=(Token)match(input,19,FOLLOW_9); 

                	newLeafNode(otherlv_16, grammarAccess.getSizeRuleAccess().getContextKeyword_11());
                
            otherlv_17=(Token)match(input,15,FOLLOW_6); 

                	newLeafNode(otherlv_17, grammarAccess.getSizeRuleAccess().getLeftCurlyBracketKeyword_12());
                
            // InternalValid.g:836:1: ( (lv_contexts_18_0= ruleSimpleContext ) )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_ID) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalValid.g:837:1: (lv_contexts_18_0= ruleSimpleContext )
            	    {
            	    // InternalValid.g:837:1: (lv_contexts_18_0= ruleSimpleContext )
            	    // InternalValid.g:838:3: lv_contexts_18_0= ruleSimpleContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSizeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_contexts_18_0=ruleSimpleContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSizeRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_18_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.SimpleContext");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            otherlv_19=(Token)match(input,16,FOLLOW_2); 

                	newLeafNode(otherlv_19, grammarAccess.getSizeRuleAccess().getRightCurlyBracketKeyword_14());
                

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
    // $ANTLR end "ruleSizeRule"


    // $ANTLR start "entryRuleRangeRule"
    // InternalValid.g:866:1: entryRuleRangeRule returns [EObject current=null] : iv_ruleRangeRule= ruleRangeRule EOF ;
    public final EObject entryRuleRangeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRangeRule = null;


        try {
            // InternalValid.g:867:2: (iv_ruleRangeRule= ruleRangeRule EOF )
            // InternalValid.g:868:2: iv_ruleRangeRule= ruleRangeRule EOF
            {
             newCompositeNode(grammarAccess.getRangeRuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRangeRule=ruleRangeRule();

            state._fsp--;

             current =iv_ruleRangeRule; 
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
    // $ANTLR end "entryRuleRangeRule"


    // $ANTLR start "ruleRangeRule"
    // InternalValid.g:875:1: ruleRangeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) ;
    public final EObject ruleRangeRule() throws RecognitionException {
        EObject current = null;

        Token lv_optional_1_0=null;
        Token otherlv_3=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token lv_label_7_0=null;
        Token otherlv_8=null;
        Token lv_description_9_0=null;
        Token otherlv_10=null;
        Token lv_message_11_0=null;
        Token otherlv_12=null;
        Token lv_min_13_0=null;
        Token otherlv_14=null;
        Token lv_max_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Enumerator lv_checkKind_2_0 = null;

        Enumerator lv_severity_4_0 = null;

        EObject lv_contexts_18_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:878:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) )
            // InternalValid.g:879:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            {
            // InternalValid.g:879:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            // InternalValid.g:879:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}'
            {
            // InternalValid.g:879:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:881:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:881:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:882:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	
            // InternalValid.g:885:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:886:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:886:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( LA15_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt15=1;
                }
                else if ( LA15_0 >= 31 && LA15_0 <= 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalValid.g:888:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:888:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:889:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:889:106: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:890:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // InternalValid.g:893:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:893:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "true");
            	    }
            	    // InternalValid.g:893:16: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:894:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:894:1: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:895:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_22); 

            	            newLeafNode(lv_optional_1_0, grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	        

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getRangeRuleRule());
            	    	        }
            	           		setWithLastConsumed(current, "optional", true, "optional");
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:915:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:915:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:916:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:916:106: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:917:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // InternalValid.g:920:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:920:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "true");
            	    }
            	    // InternalValid.g:920:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:921:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:921:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:922:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_22);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.CheckKind");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	

            }

            otherlv_3=(Token)match(input,22,FOLLOW_11); 

                	newLeafNode(otherlv_3, grammarAccess.getRangeRuleAccess().getRangeKeyword_1());
                
            // InternalValid.g:956:1: ( (lv_severity_4_0= ruleSeverityKind ) )
            // InternalValid.g:957:1: (lv_severity_4_0= ruleSeverityKind )
            {
            // InternalValid.g:957:1: (lv_severity_4_0= ruleSeverityKind )
            // InternalValid.g:958:3: lv_severity_4_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getRangeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_6);
            lv_severity_4_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_4_0, 
                    		"com.avaloq.tools.ddk.xtext.valid.Valid.SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalValid.g:974:2: ( (lv_name_5_0= RULE_ID ) )
            // InternalValid.g:975:1: (lv_name_5_0= RULE_ID )
            {
            // InternalValid.g:975:1: (lv_name_5_0= RULE_ID )
            // InternalValid.g:976:3: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            			newLeafNode(lv_name_5_0, grammarAccess.getRangeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_5_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_6=(Token)match(input,13,FOLLOW_5); 

                	newLeafNode(otherlv_6, grammarAccess.getRangeRuleAccess().getLabelKeyword_4());
                
            // InternalValid.g:996:1: ( (lv_label_7_0= RULE_STRING ) )
            // InternalValid.g:997:1: (lv_label_7_0= RULE_STRING )
            {
            // InternalValid.g:997:1: (lv_label_7_0= RULE_STRING )
            // InternalValid.g:998:3: lv_label_7_0= RULE_STRING
            {
            lv_label_7_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

            			newLeafNode(lv_label_7_0, grammarAccess.getRangeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_7_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalValid.g:1014:2: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==14) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalValid.g:1014:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_5); 

                        	newLeafNode(otherlv_8, grammarAccess.getRangeRuleAccess().getDescriptionKeyword_6_0());
                        
                    // InternalValid.g:1018:1: ( (lv_description_9_0= RULE_STRING ) )
                    // InternalValid.g:1019:1: (lv_description_9_0= RULE_STRING )
                    {
                    // InternalValid.g:1019:1: (lv_description_9_0= RULE_STRING )
                    // InternalValid.g:1020:3: lv_description_9_0= RULE_STRING
                    {
                    lv_description_9_0=(Token)match(input,RULE_STRING,FOLLOW_24); 

                    			newLeafNode(lv_description_9_0, grammarAccess.getRangeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_9_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalValid.g:1036:4: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalValid.g:1036:6: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_5); 

                        	newLeafNode(otherlv_10, grammarAccess.getRangeRuleAccess().getMessageKeyword_7_0());
                        
                    // InternalValid.g:1040:1: ( (lv_message_11_0= RULE_STRING ) )
                    // InternalValid.g:1041:1: (lv_message_11_0= RULE_STRING )
                    {
                    // InternalValid.g:1041:1: (lv_message_11_0= RULE_STRING )
                    // InternalValid.g:1042:3: lv_message_11_0= RULE_STRING
                    {
                    lv_message_11_0=(Token)match(input,RULE_STRING,FOLLOW_25); 

                    			newLeafNode(lv_message_11_0, grammarAccess.getRangeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"message",
                            		lv_message_11_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,22,FOLLOW_20); 

                	newLeafNode(otherlv_12, grammarAccess.getRangeRuleAccess().getRangeKeyword_8());
                
            // InternalValid.g:1062:1: ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_INT) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==21) ) {
                    alt18=1;
                }
            }
            switch (alt18) {
                case 1 :
                    // InternalValid.g:1062:2: ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..'
                    {
                    // InternalValid.g:1062:2: ( (lv_min_13_0= RULE_INT ) )
                    // InternalValid.g:1063:1: (lv_min_13_0= RULE_INT )
                    {
                    // InternalValid.g:1063:1: (lv_min_13_0= RULE_INT )
                    // InternalValid.g:1064:3: lv_min_13_0= RULE_INT
                    {
                    lv_min_13_0=(Token)match(input,RULE_INT,FOLLOW_21); 

                    			newLeafNode(lv_min_13_0, grammarAccess.getRangeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"min",
                            		lv_min_13_0, 
                            		"org.eclipse.xtext.common.Terminals.INT");
                    	    

                    }


                    }

                    otherlv_14=(Token)match(input,21,FOLLOW_20); 

                        	newLeafNode(otherlv_14, grammarAccess.getRangeRuleAccess().getFullStopFullStopKeyword_9_1());
                        

                    }
                    break;

            }

            // InternalValid.g:1084:3: ( (lv_max_15_0= RULE_INT ) )
            // InternalValid.g:1085:1: (lv_max_15_0= RULE_INT )
            {
            // InternalValid.g:1085:1: (lv_max_15_0= RULE_INT )
            // InternalValid.g:1086:3: lv_max_15_0= RULE_INT
            {
            lv_max_15_0=(Token)match(input,RULE_INT,FOLLOW_14); 

            			newLeafNode(lv_max_15_0, grammarAccess.getRangeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"max",
                    		lv_max_15_0, 
                    		"org.eclipse.xtext.common.Terminals.INT");
            	    

            }


            }

            otherlv_16=(Token)match(input,19,FOLLOW_9); 

                	newLeafNode(otherlv_16, grammarAccess.getRangeRuleAccess().getContextKeyword_11());
                
            otherlv_17=(Token)match(input,15,FOLLOW_6); 

                	newLeafNode(otherlv_17, grammarAccess.getRangeRuleAccess().getLeftCurlyBracketKeyword_12());
                
            // InternalValid.g:1110:1: ( (lv_contexts_18_0= ruleSimpleContext ) )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalValid.g:1111:1: (lv_contexts_18_0= ruleSimpleContext )
            	    {
            	    // InternalValid.g:1111:1: (lv_contexts_18_0= ruleSimpleContext )
            	    // InternalValid.g:1112:3: lv_contexts_18_0= ruleSimpleContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_contexts_18_0=ruleSimpleContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_18_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.SimpleContext");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);

            otherlv_19=(Token)match(input,16,FOLLOW_2); 

                	newLeafNode(otherlv_19, grammarAccess.getRangeRuleAccess().getRightCurlyBracketKeyword_14());
                

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
    // $ANTLR end "ruleRangeRule"


    // $ANTLR start "entryRuleUniqueRule"
    // InternalValid.g:1140:1: entryRuleUniqueRule returns [EObject current=null] : iv_ruleUniqueRule= ruleUniqueRule EOF ;
    public final EObject entryRuleUniqueRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUniqueRule = null;


        try {
            // InternalValid.g:1141:2: (iv_ruleUniqueRule= ruleUniqueRule EOF )
            // InternalValid.g:1142:2: iv_ruleUniqueRule= ruleUniqueRule EOF
            {
             newCompositeNode(grammarAccess.getUniqueRuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUniqueRule=ruleUniqueRule();

            state._fsp--;

             current =iv_ruleUniqueRule; 
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
    // $ANTLR end "entryRuleUniqueRule"


    // $ANTLR start "ruleUniqueRule"
    // InternalValid.g:1149:1: ruleUniqueRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' ) ;
    public final EObject ruleUniqueRule() throws RecognitionException {
        EObject current = null;

        Token lv_optional_1_0=null;
        Token otherlv_3=null;
        Token lv_name_5_0=null;
        Token otherlv_6=null;
        Token lv_label_7_0=null;
        Token otherlv_8=null;
        Token lv_description_9_0=null;
        Token otherlv_10=null;
        Token lv_message_11_0=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Enumerator lv_checkKind_2_0 = null;

        Enumerator lv_severity_4_0 = null;

        EObject lv_contexts_14_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:1152:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' ) )
            // InternalValid.g:1153:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' )
            {
            // InternalValid.g:1153:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' )
            // InternalValid.g:1153:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}'
            {
            // InternalValid.g:1153:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:1155:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:1155:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:1156:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	
            // InternalValid.g:1159:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:1160:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:1160:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop20:
            do {
                int alt20=3;
                int LA20_0 = input.LA(1);

                if ( LA20_0 == 17 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt20=1;
                }
                else if ( LA20_0 >= 31 && LA20_0 <= 33 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt20=2;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalValid.g:1162:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:1162:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:1163:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:1163:107: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:1164:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // InternalValid.g:1167:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:1167:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "true");
            	    }
            	    // InternalValid.g:1167:16: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:1168:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:1168:1: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:1169:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_26); 

            	            newLeafNode(lv_optional_1_0, grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	        

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getUniqueRuleRule());
            	    	        }
            	           		setWithLastConsumed(current, "optional", true, "optional");
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:1189:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:1189:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:1190:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:1190:107: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:1191:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // InternalValid.g:1194:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:1194:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "true");
            	    }
            	    // InternalValid.g:1194:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:1195:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:1195:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:1196:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getUniqueRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_26);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getUniqueRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.CheckKind");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }

            	     
            	    	 				  getUnorderedGroupHelper().returnFromSelection(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	    	 				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }


            }

             
            	  getUnorderedGroupHelper().leave(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	

            }

            otherlv_3=(Token)match(input,23,FOLLOW_11); 

                	newLeafNode(otherlv_3, grammarAccess.getUniqueRuleAccess().getUniqueKeyword_1());
                
            // InternalValid.g:1230:1: ( (lv_severity_4_0= ruleSeverityKind ) )
            // InternalValid.g:1231:1: (lv_severity_4_0= ruleSeverityKind )
            {
            // InternalValid.g:1231:1: (lv_severity_4_0= ruleSeverityKind )
            // InternalValid.g:1232:3: lv_severity_4_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getUniqueRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_6);
            lv_severity_4_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUniqueRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_4_0, 
                    		"com.avaloq.tools.ddk.xtext.valid.Valid.SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalValid.g:1248:2: ( (lv_name_5_0= RULE_ID ) )
            // InternalValid.g:1249:1: (lv_name_5_0= RULE_ID )
            {
            // InternalValid.g:1249:1: (lv_name_5_0= RULE_ID )
            // InternalValid.g:1250:3: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            			newLeafNode(lv_name_5_0, grammarAccess.getUniqueRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUniqueRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_5_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_6=(Token)match(input,13,FOLLOW_5); 

                	newLeafNode(otherlv_6, grammarAccess.getUniqueRuleAccess().getLabelKeyword_4());
                
            // InternalValid.g:1270:1: ( (lv_label_7_0= RULE_STRING ) )
            // InternalValid.g:1271:1: (lv_label_7_0= RULE_STRING )
            {
            // InternalValid.g:1271:1: (lv_label_7_0= RULE_STRING )
            // InternalValid.g:1272:3: lv_label_7_0= RULE_STRING
            {
            lv_label_7_0=(Token)match(input,RULE_STRING,FOLLOW_27); 

            			newLeafNode(lv_label_7_0, grammarAccess.getUniqueRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUniqueRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_7_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            // InternalValid.g:1288:2: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==14) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalValid.g:1288:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_5); 

                        	newLeafNode(otherlv_8, grammarAccess.getUniqueRuleAccess().getDescriptionKeyword_6_0());
                        
                    // InternalValid.g:1292:1: ( (lv_description_9_0= RULE_STRING ) )
                    // InternalValid.g:1293:1: (lv_description_9_0= RULE_STRING )
                    {
                    // InternalValid.g:1293:1: (lv_description_9_0= RULE_STRING )
                    // InternalValid.g:1294:3: lv_description_9_0= RULE_STRING
                    {
                    lv_description_9_0=(Token)match(input,RULE_STRING,FOLLOW_28); 

                    			newLeafNode(lv_description_9_0, grammarAccess.getUniqueRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getUniqueRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_9_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalValid.g:1310:4: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==18) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalValid.g:1310:6: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_5); 

                        	newLeafNode(otherlv_10, grammarAccess.getUniqueRuleAccess().getMessageKeyword_7_0());
                        
                    // InternalValid.g:1314:1: ( (lv_message_11_0= RULE_STRING ) )
                    // InternalValid.g:1315:1: (lv_message_11_0= RULE_STRING )
                    {
                    // InternalValid.g:1315:1: (lv_message_11_0= RULE_STRING )
                    // InternalValid.g:1316:3: lv_message_11_0= RULE_STRING
                    {
                    lv_message_11_0=(Token)match(input,RULE_STRING,FOLLOW_14); 

                    			newLeafNode(lv_message_11_0, grammarAccess.getUniqueRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getUniqueRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"message",
                            		lv_message_11_0, 
                            		"org.eclipse.xtext.common.Terminals.STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,19,FOLLOW_9); 

                	newLeafNode(otherlv_12, grammarAccess.getUniqueRuleAccess().getContextKeyword_8());
                
            otherlv_13=(Token)match(input,15,FOLLOW_6); 

                	newLeafNode(otherlv_13, grammarAccess.getUniqueRuleAccess().getLeftCurlyBracketKeyword_9());
                
            // InternalValid.g:1340:1: ( (lv_contexts_14_0= ruleDuplicateContext ) )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==RULE_ID) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalValid.g:1341:1: (lv_contexts_14_0= ruleDuplicateContext )
            	    {
            	    // InternalValid.g:1341:1: (lv_contexts_14_0= ruleDuplicateContext )
            	    // InternalValid.g:1342:3: lv_contexts_14_0= ruleDuplicateContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getUniqueRuleAccess().getContextsDuplicateContextParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_15);
            	    lv_contexts_14_0=ruleDuplicateContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getUniqueRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_14_0, 
            	            		"com.avaloq.tools.ddk.xtext.valid.Valid.DuplicateContext");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);

            otherlv_15=(Token)match(input,16,FOLLOW_2); 

                	newLeafNode(otherlv_15, grammarAccess.getUniqueRuleAccess().getRightCurlyBracketKeyword_11());
                

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
    // $ANTLR end "ruleUniqueRule"


    // $ANTLR start "entryRuleSimpleContext"
    // InternalValid.g:1372:1: entryRuleSimpleContext returns [EObject current=null] : iv_ruleSimpleContext= ruleSimpleContext EOF ;
    public final EObject entryRuleSimpleContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleContext = null;


        try {
            // InternalValid.g:1373:2: (iv_ruleSimpleContext= ruleSimpleContext EOF )
            // InternalValid.g:1374:2: iv_ruleSimpleContext= ruleSimpleContext EOF
            {
             newCompositeNode(grammarAccess.getSimpleContextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSimpleContext=ruleSimpleContext();

            state._fsp--;

             current =iv_ruleSimpleContext; 
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
    // $ANTLR end "entryRuleSimpleContext"


    // $ANTLR start "ruleSimpleContext"
    // InternalValid.g:1381:1: ruleSimpleContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' ) ;
    public final EObject ruleSimpleContext() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // InternalValid.g:1384:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' ) )
            // InternalValid.g:1385:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' )
            {
            // InternalValid.g:1385:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' )
            // InternalValid.g:1385:2: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';'
            {
            // InternalValid.g:1385:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:1386:1: ( ruleQualifiedID )
            {
            // InternalValid.g:1386:1: ( ruleQualifiedID )
            // InternalValid.g:1387:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getSimpleContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getSimpleContextAccess().getContextTypeEClassCrossReference_0_0()); 
            	    
            pushFollow(FOLLOW_29);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalValid.g:1400:2: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==24) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalValid.g:1400:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_6); 

                        	newLeafNode(otherlv_1, grammarAccess.getSimpleContextAccess().getNumberSignKeyword_1_0());
                        
                    // InternalValid.g:1404:1: ( (otherlv_2= RULE_ID ) )
                    // InternalValid.g:1405:1: (otherlv_2= RULE_ID )
                    {
                    // InternalValid.g:1405:1: (otherlv_2= RULE_ID )
                    // InternalValid.g:1406:3: otherlv_2= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getSimpleContextRule());
                    	        }
                            
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_30); 

                    		newLeafNode(otherlv_2, grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
                    	

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,25,FOLLOW_2); 

                	newLeafNode(otherlv_3, grammarAccess.getSimpleContextAccess().getSemicolonKeyword_2());
                

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
    // $ANTLR end "ruleSimpleContext"


    // $ANTLR start "entryRuleDuplicateContext"
    // InternalValid.g:1429:1: entryRuleDuplicateContext returns [EObject current=null] : iv_ruleDuplicateContext= ruleDuplicateContext EOF ;
    public final EObject entryRuleDuplicateContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDuplicateContext = null;


        try {
            // InternalValid.g:1430:2: (iv_ruleDuplicateContext= ruleDuplicateContext EOF )
            // InternalValid.g:1431:2: iv_ruleDuplicateContext= ruleDuplicateContext EOF
            {
             newCompositeNode(grammarAccess.getDuplicateContextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDuplicateContext=ruleDuplicateContext();

            state._fsp--;

             current =iv_ruleDuplicateContext; 
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
    // $ANTLR end "entryRuleDuplicateContext"


    // $ANTLR start "ruleDuplicateContext"
    // InternalValid.g:1438:1: ruleDuplicateContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' ) ;
    public final EObject ruleDuplicateContext() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;

         enterRule(); 
            
        try {
            // InternalValid.g:1441:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' ) )
            // InternalValid.g:1442:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' )
            {
            // InternalValid.g:1442:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' )
            // InternalValid.g:1442:2: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';'
            {
            // InternalValid.g:1442:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:1443:1: ( ruleQualifiedID )
            {
            // InternalValid.g:1443:1: ( ruleQualifiedID )
            // InternalValid.g:1444:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDuplicateContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getDuplicateContextAccess().getContextTypeEClassCrossReference_0_0()); 
            	    
            pushFollow(FOLLOW_31);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalValid.g:1457:2: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==24) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalValid.g:1457:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_6); 

                        	newLeafNode(otherlv_1, grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_1_0());
                        
                    // InternalValid.g:1461:1: ( (otherlv_2= RULE_ID ) )
                    // InternalValid.g:1462:1: (otherlv_2= RULE_ID )
                    {
                    // InternalValid.g:1462:1: (otherlv_2= RULE_ID )
                    // InternalValid.g:1463:3: otherlv_2= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getDuplicateContextRule());
                    	        }
                            
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_32); 

                    		newLeafNode(otherlv_2, grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
                    	

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,26,FOLLOW_6); 

                	newLeafNode(otherlv_3, grammarAccess.getDuplicateContextAccess().getMarkerKeyword_2());
                
            // InternalValid.g:1478:1: ( ( ruleQualifiedID ) )
            // InternalValid.g:1479:1: ( ruleQualifiedID )
            {
            // InternalValid.g:1479:1: ( ruleQualifiedID )
            // InternalValid.g:1480:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDuplicateContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassCrossReference_3_0()); 
            	    
            pushFollow(FOLLOW_33);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,24,FOLLOW_34); 

                	newLeafNode(otherlv_5, grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_4());
                
            // InternalValid.g:1497:1: ( (otherlv_6= RULE_ID ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalValid.g:1498:1: (otherlv_6= RULE_ID )
                    {
                    // InternalValid.g:1498:1: (otherlv_6= RULE_ID )
                    // InternalValid.g:1499:3: otherlv_6= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getDuplicateContextRule());
                    	        }
                            
                    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_30); 

                    		newLeafNode(otherlv_6, grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_5_0()); 
                    	

                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,25,FOLLOW_2); 

                	newLeafNode(otherlv_7, grammarAccess.getDuplicateContextAccess().getSemicolonKeyword_6());
                

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
    // $ANTLR end "ruleDuplicateContext"


    // $ANTLR start "entryRuleNativeContext"
    // InternalValid.g:1522:1: entryRuleNativeContext returns [EObject current=null] : iv_ruleNativeContext= ruleNativeContext EOF ;
    public final EObject entryRuleNativeContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNativeContext = null;


        try {
            // InternalValid.g:1523:2: (iv_ruleNativeContext= ruleNativeContext EOF )
            // InternalValid.g:1524:2: iv_ruleNativeContext= ruleNativeContext EOF
            {
             newCompositeNode(grammarAccess.getNativeContextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNativeContext=ruleNativeContext();

            state._fsp--;

             current =iv_ruleNativeContext; 
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
    // $ANTLR end "entryRuleNativeContext"


    // $ANTLR start "ruleNativeContext"
    // InternalValid.g:1531:1: ruleNativeContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' ) ;
    public final EObject ruleNativeContext() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_named_3_0=null;
        Token lv_givenName_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        EObject lv_quickFixes_11_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:1534:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' ) )
            // InternalValid.g:1535:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' )
            {
            // InternalValid.g:1535:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' )
            // InternalValid.g:1535:2: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';'
            {
            // InternalValid.g:1535:2: ( ( ruleQualifiedID ) )
            // InternalValid.g:1536:1: ( ruleQualifiedID )
            {
            // InternalValid.g:1536:1: ( ruleQualifiedID )
            // InternalValid.g:1537:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getNativeContextAccess().getContextTypeEClassCrossReference_0_0()); 
            	    
            pushFollow(FOLLOW_35);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // InternalValid.g:1550:2: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==24) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalValid.g:1550:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_6); 

                        	newLeafNode(otherlv_1, grammarAccess.getNativeContextAccess().getNumberSignKeyword_1_0());
                        
                    // InternalValid.g:1554:1: ( (otherlv_2= RULE_ID ) )
                    // InternalValid.g:1555:1: (otherlv_2= RULE_ID )
                    {
                    // InternalValid.g:1555:1: (otherlv_2= RULE_ID )
                    // InternalValid.g:1556:3: otherlv_2= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                            
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_36); 

                    		newLeafNode(otherlv_2, grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
                    	

                    }


                    }


                    }
                    break;

            }

            // InternalValid.g:1567:4: ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==27) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalValid.g:1567:5: ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) )
                    {
                    // InternalValid.g:1567:5: ( (lv_named_3_0= 'as' ) )
                    // InternalValid.g:1568:1: (lv_named_3_0= 'as' )
                    {
                    // InternalValid.g:1568:1: (lv_named_3_0= 'as' )
                    // InternalValid.g:1569:3: lv_named_3_0= 'as'
                    {
                    lv_named_3_0=(Token)match(input,27,FOLLOW_6); 

                            newLeafNode(lv_named_3_0, grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                           		setWithLastConsumed(current, "named", true, "as");
                    	    

                    }


                    }

                    // InternalValid.g:1582:2: ( (lv_givenName_4_0= RULE_ID ) )
                    // InternalValid.g:1583:1: (lv_givenName_4_0= RULE_ID )
                    {
                    // InternalValid.g:1583:1: (lv_givenName_4_0= RULE_ID )
                    // InternalValid.g:1584:3: lv_givenName_4_0= RULE_ID
                    {
                    lv_givenName_4_0=(Token)match(input,RULE_ID,FOLLOW_37); 

                    			newLeafNode(lv_givenName_4_0, grammarAccess.getNativeContextAccess().getGivenNameIDTerminalRuleCall_2_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"givenName",
                            		lv_givenName_4_0, 
                            		"org.eclipse.xtext.common.Terminals.ID");
                    	    

                    }


                    }


                    }
                    break;

            }

            // InternalValid.g:1600:4: (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==26) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalValid.g:1600:6: otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) )
                    {
                    otherlv_5=(Token)match(input,26,FOLLOW_6); 

                        	newLeafNode(otherlv_5, grammarAccess.getNativeContextAccess().getMarkerKeyword_3_0());
                        
                    // InternalValid.g:1604:1: ( ( ruleQualifiedID ) )
                    // InternalValid.g:1605:1: ( ruleQualifiedID )
                    {
                    // InternalValid.g:1605:1: ( ruleQualifiedID )
                    // InternalValid.g:1606:3: ruleQualifiedID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getNativeContextAccess().getMarkerTypeEClassCrossReference_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_33);
                    ruleQualifiedID();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // InternalValid.g:1619:2: (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) )
                    // InternalValid.g:1619:4: otherlv_7= '#' ( (otherlv_8= RULE_ID ) )
                    {
                    otherlv_7=(Token)match(input,24,FOLLOW_6); 

                        	newLeafNode(otherlv_7, grammarAccess.getNativeContextAccess().getNumberSignKeyword_3_2_0());
                        
                    // InternalValid.g:1623:1: ( (otherlv_8= RULE_ID ) )
                    // InternalValid.g:1624:1: (otherlv_8= RULE_ID )
                    {
                    // InternalValid.g:1624:1: (otherlv_8= RULE_ID )
                    // InternalValid.g:1625:3: otherlv_8= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                            
                    otherlv_8=(Token)match(input,RULE_ID,FOLLOW_38); 

                    		newLeafNode(otherlv_8, grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_3_2_1_0()); 
                    	

                    }


                    }


                    }


                    }
                    break;

            }

            // InternalValid.g:1636:5: (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==28) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalValid.g:1636:7: otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}'
                    {
                    otherlv_9=(Token)match(input,28,FOLLOW_9); 

                        	newLeafNode(otherlv_9, grammarAccess.getNativeContextAccess().getQuickfixesKeyword_4_0());
                        
                    otherlv_10=(Token)match(input,15,FOLLOW_39); 

                        	newLeafNode(otherlv_10, grammarAccess.getNativeContextAccess().getLeftCurlyBracketKeyword_4_1());
                        
                    // InternalValid.g:1644:1: ( (lv_quickFixes_11_0= ruleQuickFix ) )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==29||(LA30_0>=36 && LA30_0<=37)) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // InternalValid.g:1645:1: (lv_quickFixes_11_0= ruleQuickFix )
                    	    {
                    	    // InternalValid.g:1645:1: (lv_quickFixes_11_0= ruleQuickFix )
                    	    // InternalValid.g:1646:3: lv_quickFixes_11_0= ruleQuickFix
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getNativeContextAccess().getQuickFixesQuickFixParserRuleCall_4_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_40);
                    	    lv_quickFixes_11_0=ruleQuickFix();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getNativeContextRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"quickFixes",
                    	            		lv_quickFixes_11_0, 
                    	            		"com.avaloq.tools.ddk.xtext.valid.Valid.QuickFix");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt30 >= 1 ) break loop30;
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
                    } while (true);

                    otherlv_12=(Token)match(input,16,FOLLOW_30); 

                        	newLeafNode(otherlv_12, grammarAccess.getNativeContextAccess().getRightCurlyBracketKeyword_4_3());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,25,FOLLOW_2); 

                	newLeafNode(otherlv_13, grammarAccess.getNativeContextAccess().getSemicolonKeyword_5());
                

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
    // $ANTLR end "ruleNativeContext"


    // $ANTLR start "entryRuleQuickFix"
    // InternalValid.g:1678:1: entryRuleQuickFix returns [EObject current=null] : iv_ruleQuickFix= ruleQuickFix EOF ;
    public final EObject entryRuleQuickFix() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQuickFix = null;


        try {
            // InternalValid.g:1679:2: (iv_ruleQuickFix= ruleQuickFix EOF )
            // InternalValid.g:1680:2: iv_ruleQuickFix= ruleQuickFix EOF
            {
             newCompositeNode(grammarAccess.getQuickFixRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQuickFix=ruleQuickFix();

            state._fsp--;

             current =iv_ruleQuickFix; 
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
    // $ANTLR end "entryRuleQuickFix"


    // $ANTLR start "ruleQuickFix"
    // InternalValid.g:1687:1: ruleQuickFix returns [EObject current=null] : ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' ) ;
    public final EObject ruleQuickFix() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_label_4_0=null;
        Token otherlv_5=null;
        Token lv_message_6_0=null;
        Token otherlv_7=null;
        Enumerator lv_quickFixKind_0_0 = null;


         enterRule(); 
            
        try {
            // InternalValid.g:1690:28: ( ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' ) )
            // InternalValid.g:1691:1: ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' )
            {
            // InternalValid.g:1691:1: ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' )
            // InternalValid.g:1691:2: ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';'
            {
            // InternalValid.g:1691:2: ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=36 && LA32_0<=37)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalValid.g:1692:1: (lv_quickFixKind_0_0= ruleQuickFixKind )
                    {
                    // InternalValid.g:1692:1: (lv_quickFixKind_0_0= ruleQuickFixKind )
                    // InternalValid.g:1693:3: lv_quickFixKind_0_0= ruleQuickFixKind
                    {
                     
                    	        newCompositeNode(grammarAccess.getQuickFixAccess().getQuickFixKindQuickFixKindEnumRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_41);
                    lv_quickFixKind_0_0=ruleQuickFixKind();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getQuickFixRule());
                    	        }
                           		set(
                           			current, 
                           			"quickFixKind",
                            		lv_quickFixKind_0_0, 
                            		"com.avaloq.tools.ddk.xtext.valid.Valid.QuickFixKind");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,29,FOLLOW_6); 

                	newLeafNode(otherlv_1, grammarAccess.getQuickFixAccess().getFixKeyword_1());
                
            // InternalValid.g:1713:1: ( (lv_name_2_0= RULE_ID ) )
            // InternalValid.g:1714:1: (lv_name_2_0= RULE_ID )
            {
            // InternalValid.g:1714:1: (lv_name_2_0= RULE_ID )
            // InternalValid.g:1715:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            			newLeafNode(lv_name_2_0, grammarAccess.getQuickFixAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getQuickFixRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"org.eclipse.xtext.common.Terminals.ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_5); 

                	newLeafNode(otherlv_3, grammarAccess.getQuickFixAccess().getLabelKeyword_3());
                
            // InternalValid.g:1735:1: ( (lv_label_4_0= RULE_STRING ) )
            // InternalValid.g:1736:1: (lv_label_4_0= RULE_STRING )
            {
            // InternalValid.g:1736:1: (lv_label_4_0= RULE_STRING )
            // InternalValid.g:1737:3: lv_label_4_0= RULE_STRING
            {
            lv_label_4_0=(Token)match(input,RULE_STRING,FOLLOW_13); 

            			newLeafNode(lv_label_4_0, grammarAccess.getQuickFixAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getQuickFixRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_4_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            otherlv_5=(Token)match(input,18,FOLLOW_5); 

                	newLeafNode(otherlv_5, grammarAccess.getQuickFixAccess().getMessageKeyword_5());
                
            // InternalValid.g:1757:1: ( (lv_message_6_0= RULE_STRING ) )
            // InternalValid.g:1758:1: (lv_message_6_0= RULE_STRING )
            {
            // InternalValid.g:1758:1: (lv_message_6_0= RULE_STRING )
            // InternalValid.g:1759:3: lv_message_6_0= RULE_STRING
            {
            lv_message_6_0=(Token)match(input,RULE_STRING,FOLLOW_30); 

            			newLeafNode(lv_message_6_0, grammarAccess.getQuickFixAccess().getMessageSTRINGTerminalRuleCall_6_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getQuickFixRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"message",
                    		lv_message_6_0, 
                    		"org.eclipse.xtext.common.Terminals.STRING");
            	    

            }


            }

            otherlv_7=(Token)match(input,25,FOLLOW_2); 

                	newLeafNode(otherlv_7, grammarAccess.getQuickFixAccess().getSemicolonKeyword_7());
                

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
    // $ANTLR end "ruleQuickFix"


    // $ANTLR start "entryRuleQualifiedID"
    // InternalValid.g:1787:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalValid.g:1788:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalValid.g:1789:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
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
    // $ANTLR end "entryRuleQualifiedID"


    // $ANTLR start "ruleQualifiedID"
    // InternalValid.g:1796:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // InternalValid.g:1799:28: ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID ) )
            // InternalValid.g:1800:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID )
            {
            // InternalValid.g:1800:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID )
            // InternalValid.g:1800:2: (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID
            {
            // InternalValid.g:1800:2: (this_ID_0= RULE_ID kw= '::' )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID) ) {
                    int LA33_1 = input.LA(2);

                    if ( (LA33_1==30) ) {
                        alt33=1;
                    }


                }


                switch (alt33) {
            	case 1 :
            	    // InternalValid.g:1800:7: this_ID_0= RULE_ID kw= '::'
            	    {
            	    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_42); 

            	    		current.merge(this_ID_0);
            	        
            	     
            	        newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0_0()); 
            	        
            	    kw=(Token)match(input,30,FOLLOW_6); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_0_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_2); 

            		current.merge(this_ID_2);
                
             
                newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1()); 
                

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
    // $ANTLR end "ruleQualifiedID"


    // $ANTLR start "ruleCheckKind"
    // InternalValid.g:1828:1: ruleCheckKind returns [Enumerator current=null] : ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) ) ;
    public final Enumerator ruleCheckKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // InternalValid.g:1830:28: ( ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) ) )
            // InternalValid.g:1831:1: ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) )
            {
            // InternalValid.g:1831:1: ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) )
            int alt34=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt34=1;
                }
                break;
            case 32:
                {
                alt34=2;
                }
                break;
            case 33:
                {
                alt34=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // InternalValid.g:1831:2: (enumLiteral_0= 'fast' )
                    {
                    // InternalValid.g:1831:2: (enumLiteral_0= 'fast' )
                    // InternalValid.g:1831:4: enumLiteral_0= 'fast'
                    {
                    enumLiteral_0=(Token)match(input,31,FOLLOW_2); 

                            current = grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:1837:6: (enumLiteral_1= 'normal' )
                    {
                    // InternalValid.g:1837:6: (enumLiteral_1= 'normal' )
                    // InternalValid.g:1837:8: enumLiteral_1= 'normal'
                    {
                    enumLiteral_1=(Token)match(input,32,FOLLOW_2); 

                            current = grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // InternalValid.g:1843:6: (enumLiteral_2= 'expensive' )
                    {
                    // InternalValid.g:1843:6: (enumLiteral_2= 'expensive' )
                    // InternalValid.g:1843:8: enumLiteral_2= 'expensive'
                    {
                    enumLiteral_2=(Token)match(input,33,FOLLOW_2); 

                            current = grammarAccess.getCheckKindAccess().getExpensiveEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getCheckKindAccess().getExpensiveEnumLiteralDeclaration_2()); 
                        

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
    // $ANTLR end "ruleCheckKind"


    // $ANTLR start "ruleSeverityKind"
    // InternalValid.g:1853:1: ruleSeverityKind returns [Enumerator current=null] : ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) ) ;
    public final Enumerator ruleSeverityKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalValid.g:1855:28: ( ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) ) )
            // InternalValid.g:1856:1: ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) )
            {
            // InternalValid.g:1856:1: ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==34) ) {
                alt35=1;
            }
            else if ( (LA35_0==35) ) {
                alt35=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }
            switch (alt35) {
                case 1 :
                    // InternalValid.g:1856:2: (enumLiteral_0= 'error' )
                    {
                    // InternalValid.g:1856:2: (enumLiteral_0= 'error' )
                    // InternalValid.g:1856:4: enumLiteral_0= 'error'
                    {
                    enumLiteral_0=(Token)match(input,34,FOLLOW_2); 

                            current = grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:1862:6: (enumLiteral_1= 'warning' )
                    {
                    // InternalValid.g:1862:6: (enumLiteral_1= 'warning' )
                    // InternalValid.g:1862:8: enumLiteral_1= 'warning'
                    {
                    enumLiteral_1=(Token)match(input,35,FOLLOW_2); 

                            current = grammarAccess.getSeverityKindAccess().getWarningEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getSeverityKindAccess().getWarningEnumLiteralDeclaration_1()); 
                        

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
    // $ANTLR end "ruleSeverityKind"


    // $ANTLR start "ruleQuickFixKind"
    // InternalValid.g:1872:1: ruleQuickFixKind returns [Enumerator current=null] : ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) ) ;
    public final Enumerator ruleQuickFixKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalValid.g:1874:28: ( ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) ) )
            // InternalValid.g:1875:1: ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) )
            {
            // InternalValid.g:1875:1: ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) )
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==36) ) {
                alt36=1;
            }
            else if ( (LA36_0==37) ) {
                alt36=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;
            }
            switch (alt36) {
                case 1 :
                    // InternalValid.g:1875:2: (enumLiteral_0= 'semantic' )
                    {
                    // InternalValid.g:1875:2: (enumLiteral_0= 'semantic' )
                    // InternalValid.g:1875:4: enumLiteral_0= 'semantic'
                    {
                    enumLiteral_0=(Token)match(input,36,FOLLOW_2); 

                            current = grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:1881:6: (enumLiteral_1= 'textual' )
                    {
                    // InternalValid.g:1881:6: (enumLiteral_1= 'textual' )
                    // InternalValid.g:1881:8: enumLiteral_1= 'textual'
                    {
                    enumLiteral_1=(Token)match(input,37,FOLLOW_2); 

                            current = grammarAccess.getQuickFixKindAccess().getTextualEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getQuickFixKindAccess().getTextualEnumLiteralDeclaration_1()); 
                        

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
    // $ANTLR end "ruleQuickFixKind"

    // Delegated rules


    protected DFA5 dfa5 = new DFA5(this);
    protected DFA6 dfa6 = new DFA6(this);
    static final String dfa_1s = "\7\uffff";
    static final String dfa_2s = "\5\21\2\uffff";
    static final String dfa_3s = "\5\43\2\uffff";
    static final String dfa_4s = "\5\uffff\1\1\1\2";
    static final String dfa_5s = "\7\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "289:1: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )";
        }
    }
    static final String dfa_7s = "\10\uffff";
    static final String dfa_8s = "\5\21\3\uffff";
    static final String dfa_9s = "\5\41\3\uffff";
    static final String dfa_10s = "\5\uffff\1\1\1\2\1\3";
    static final String dfa_11s = "\10\uffff}>";
    static final String[] dfa_12s = {
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "",
            "",
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "329:1: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000F80D30000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000F80020000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000010020L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000380120000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000144000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000380420000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000444000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000380820000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x00000000000C4000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000005000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000002000020L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x000000001F000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x000000001E000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000016000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000012000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000003020000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000003020010000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000040000000L});

}
