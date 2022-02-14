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
    // InternalValid.g:65:1: entryRuleValidModel returns [EObject current=null] : iv_ruleValidModel= ruleValidModel EOF ;
    public final EObject entryRuleValidModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValidModel = null;


        try {
            // InternalValid.g:65:51: (iv_ruleValidModel= ruleValidModel EOF )
            // InternalValid.g:66:2: iv_ruleValidModel= ruleValidModel EOF
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
    // InternalValid.g:72:1: ruleValidModel returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* ) ;
    public final EObject ruleValidModel() throws RecognitionException {
        EObject current = null;

        EObject lv_imports_0_0 = null;

        EObject lv_categories_1_0 = null;



        	enterRule();

        try {
            // InternalValid.g:78:2: ( ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* ) )
            // InternalValid.g:79:2: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* )
            {
            // InternalValid.g:79:2: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* )
            // InternalValid.g:80:3: ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )*
            {
            // InternalValid.g:80:3: ( (lv_imports_0_0= ruleImport ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalValid.g:81:4: (lv_imports_0_0= ruleImport )
            	    {
            	    // InternalValid.g:81:4: (lv_imports_0_0= ruleImport )
            	    // InternalValid.g:82:5: lv_imports_0_0= ruleImport
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
            	    // InternalValid.g:100:4: (lv_categories_1_0= ruleCategory )
            	    {
            	    // InternalValid.g:100:4: (lv_categories_1_0= ruleCategory )
            	    // InternalValid.g:101:5: lv_categories_1_0= ruleCategory
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
    // InternalValid.g:122:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalValid.g:122:47: (iv_ruleImport= ruleImport EOF )
            // InternalValid.g:123:2: iv_ruleImport= ruleImport EOF
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
    // InternalValid.g:129:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalValid.g:135:2: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) ) )
            // InternalValid.g:136:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) )
            {
            // InternalValid.g:136:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) )
            // InternalValid.g:137:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,11,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
            		
            // InternalValid.g:141:3: ( (otherlv_1= RULE_STRING ) )
            // InternalValid.g:142:4: (otherlv_1= RULE_STRING )
            {
            // InternalValid.g:142:4: (otherlv_1= RULE_STRING )
            // InternalValid.g:143:5: otherlv_1= RULE_STRING
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
    // InternalValid.g:158:1: entryRuleCategory returns [EObject current=null] : iv_ruleCategory= ruleCategory EOF ;
    public final EObject entryRuleCategory() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCategory = null;


        try {
            // InternalValid.g:158:49: (iv_ruleCategory= ruleCategory EOF )
            // InternalValid.g:159:2: iv_ruleCategory= ruleCategory EOF
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
    // InternalValid.g:165:1: ruleCategory returns [EObject current=null] : (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' ) ;
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
            // InternalValid.g:171:2: ( (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' ) )
            // InternalValid.g:172:2: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' )
            {
            // InternalValid.g:172:2: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' )
            // InternalValid.g:173:3: otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getCategoryAccess().getCategoryKeyword_0());
            		
            // InternalValid.g:177:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalValid.g:178:4: (lv_name_1_0= RULE_ID )
            {
            // InternalValid.g:178:4: (lv_name_1_0= RULE_ID )
            // InternalValid.g:179:5: lv_name_1_0= RULE_ID
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
            		
            // InternalValid.g:199:3: ( (lv_label_3_0= RULE_STRING ) )
            // InternalValid.g:200:4: (lv_label_3_0= RULE_STRING )
            {
            // InternalValid.g:200:4: (lv_label_3_0= RULE_STRING )
            // InternalValid.g:201:5: lv_label_3_0= RULE_STRING
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

            // InternalValid.g:217:3: (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalValid.g:218:4: otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_4, grammarAccess.getCategoryAccess().getDescriptionKeyword_4_0());
                    			
                    // InternalValid.g:222:4: ( (lv_description_5_0= RULE_STRING ) )
                    // InternalValid.g:223:5: (lv_description_5_0= RULE_STRING )
                    {
                    // InternalValid.g:223:5: (lv_description_5_0= RULE_STRING )
                    // InternalValid.g:224:6: lv_description_5_0= RULE_STRING
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
            		
            // InternalValid.g:245:3: ( (lv_rules_7_0= ruleRule ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17||LA4_0==20||(LA4_0>=22 && LA4_0<=23)||(LA4_0>=31 && LA4_0<=35)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalValid.g:246:4: (lv_rules_7_0= ruleRule )
            	    {
            	    // InternalValid.g:246:4: (lv_rules_7_0= ruleRule )
            	    // InternalValid.g:247:5: lv_rules_7_0= ruleRule
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
    // InternalValid.g:272:1: entryRuleRule returns [EObject current=null] : iv_ruleRule= ruleRule EOF ;
    public final EObject entryRuleRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRule = null;


        try {
            // InternalValid.g:272:45: (iv_ruleRule= ruleRule EOF )
            // InternalValid.g:273:2: iv_ruleRule= ruleRule EOF
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
    // InternalValid.g:279:1: ruleRule returns [EObject current=null] : (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule ) ;
    public final EObject ruleRule() throws RecognitionException {
        EObject current = null;

        EObject this_NativeRule_0 = null;

        EObject this_PredefinedRule_1 = null;



        	enterRule();

        try {
            // InternalValid.g:285:2: ( (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule ) )
            // InternalValid.g:286:2: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )
            {
            // InternalValid.g:286:2: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // InternalValid.g:287:3: this_NativeRule_0= ruleNativeRule
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
                    // InternalValid.g:296:3: this_PredefinedRule_1= rulePredefinedRule
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
    // InternalValid.g:308:1: entryRulePredefinedRule returns [EObject current=null] : iv_rulePredefinedRule= rulePredefinedRule EOF ;
    public final EObject entryRulePredefinedRule() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredefinedRule = null;


        try {
            // InternalValid.g:308:55: (iv_rulePredefinedRule= rulePredefinedRule EOF )
            // InternalValid.g:309:2: iv_rulePredefinedRule= rulePredefinedRule EOF
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
    // InternalValid.g:315:1: rulePredefinedRule returns [EObject current=null] : (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule ) ;
    public final EObject rulePredefinedRule() throws RecognitionException {
        EObject current = null;

        EObject this_SizeRule_0 = null;

        EObject this_RangeRule_1 = null;

        EObject this_UniqueRule_2 = null;



        	enterRule();

        try {
            // InternalValid.g:321:2: ( (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule ) )
            // InternalValid.g:322:2: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )
            {
            // InternalValid.g:322:2: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // InternalValid.g:323:3: this_SizeRule_0= ruleSizeRule
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
                    // InternalValid.g:332:3: this_RangeRule_1= ruleRangeRule
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
                    // InternalValid.g:341:3: this_UniqueRule_2= ruleUniqueRule
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
    // InternalValid.g:353:1: entryRuleNativeRule returns [EObject current=null] : iv_ruleNativeRule= ruleNativeRule EOF ;
    public final EObject entryRuleNativeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNativeRule = null;


        try {
            // InternalValid.g:353:51: (iv_ruleNativeRule= ruleNativeRule EOF )
            // InternalValid.g:354:2: iv_ruleNativeRule= ruleNativeRule EOF
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
    // InternalValid.g:360:1: ruleNativeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' ) ;
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
            // InternalValid.g:366:2: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' ) )
            // InternalValid.g:367:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' )
            {
            // InternalValid.g:367:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' )
            // InternalValid.g:368:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}'
            {
            // InternalValid.g:368:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:369:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:369:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:370:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            				
            // InternalValid.g:373:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:374:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:374:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
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
            	    // InternalValid.g:375:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:375:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:376:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:376:107: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:377:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0);
            	    					
            	    // InternalValid.g:380:9: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:380:10: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "true");
            	    }
            	    // InternalValid.g:380:19: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:380:20: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:380:20: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:381:10: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_11); 

            	    										newLeafNode(lv_optional_1_0, grammarAccess.getNativeRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getNativeRuleRule());
            	    										}
            	    										setWithLastConsumed(current, "optional", lv_optional_1_0 != null, "optional");
            	    									

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:398:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:398:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:399:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:399:107: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:400:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1);
            	    					
            	    // InternalValid.g:403:9: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:403:10: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "true");
            	    }
            	    // InternalValid.g:403:19: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:403:20: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:403:20: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:404:10: lv_checkKind_2_0= ruleCheckKind
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

            // InternalValid.g:433:3: ( (lv_severity_3_0= ruleSeverityKind ) )
            // InternalValid.g:434:4: (lv_severity_3_0= ruleSeverityKind )
            {
            // InternalValid.g:434:4: (lv_severity_3_0= ruleSeverityKind )
            // InternalValid.g:435:5: lv_severity_3_0= ruleSeverityKind
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

            // InternalValid.g:452:3: ( (lv_name_4_0= RULE_ID ) )
            // InternalValid.g:453:4: (lv_name_4_0= RULE_ID )
            {
            // InternalValid.g:453:4: (lv_name_4_0= RULE_ID )
            // InternalValid.g:454:5: lv_name_4_0= RULE_ID
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
            		
            // InternalValid.g:474:3: ( (lv_label_6_0= RULE_STRING ) )
            // InternalValid.g:475:4: (lv_label_6_0= RULE_STRING )
            {
            // InternalValid.g:475:4: (lv_label_6_0= RULE_STRING )
            // InternalValid.g:476:5: lv_label_6_0= RULE_STRING
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

            // InternalValid.g:492:3: (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==14) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalValid.g:493:4: otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_7, grammarAccess.getNativeRuleAccess().getDescriptionKeyword_5_0());
                    			
                    // InternalValid.g:497:4: ( (lv_description_8_0= RULE_STRING ) )
                    // InternalValid.g:498:5: (lv_description_8_0= RULE_STRING )
                    {
                    // InternalValid.g:498:5: (lv_description_8_0= RULE_STRING )
                    // InternalValid.g:499:6: lv_description_8_0= RULE_STRING
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
            		
            // InternalValid.g:520:3: ( (lv_message_10_0= RULE_STRING ) )
            // InternalValid.g:521:4: (lv_message_10_0= RULE_STRING )
            {
            // InternalValid.g:521:4: (lv_message_10_0= RULE_STRING )
            // InternalValid.g:522:5: lv_message_10_0= RULE_STRING
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
            		
            // InternalValid.g:546:3: ( (lv_contexts_13_0= ruleNativeContext ) )+
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
            	    // InternalValid.g:547:4: (lv_contexts_13_0= ruleNativeContext )
            	    {
            	    // InternalValid.g:547:4: (lv_contexts_13_0= ruleNativeContext )
            	    // InternalValid.g:548:5: lv_contexts_13_0= ruleNativeContext
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
    // InternalValid.g:573:1: entryRuleSizeRule returns [EObject current=null] : iv_ruleSizeRule= ruleSizeRule EOF ;
    public final EObject entryRuleSizeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSizeRule = null;


        try {
            // InternalValid.g:573:49: (iv_ruleSizeRule= ruleSizeRule EOF )
            // InternalValid.g:574:2: iv_ruleSizeRule= ruleSizeRule EOF
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
    // InternalValid.g:580:1: ruleSizeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) ;
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
            // InternalValid.g:586:2: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) )
            // InternalValid.g:587:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            {
            // InternalValid.g:587:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            // InternalValid.g:588:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}'
            {
            // InternalValid.g:588:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:589:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:589:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:590:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            				
            // InternalValid.g:593:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:594:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:594:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
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
            	    // InternalValid.g:595:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:595:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:596:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:596:105: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:597:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0);
            	    					
            	    // InternalValid.g:600:9: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:600:10: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "true");
            	    }
            	    // InternalValid.g:600:19: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:600:20: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:600:20: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:601:10: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_16); 

            	    										newLeafNode(lv_optional_1_0, grammarAccess.getSizeRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getSizeRuleRule());
            	    										}
            	    										setWithLastConsumed(current, "optional", lv_optional_1_0 != null, "optional");
            	    									

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:618:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:618:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:619:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:619:105: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:620:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1);
            	    					
            	    // InternalValid.g:623:9: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:623:10: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "true");
            	    }
            	    // InternalValid.g:623:19: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:623:20: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:623:20: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:624:10: lv_checkKind_2_0= ruleCheckKind
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
            		
            // InternalValid.g:657:3: ( (lv_severity_4_0= ruleSeverityKind ) )
            // InternalValid.g:658:4: (lv_severity_4_0= ruleSeverityKind )
            {
            // InternalValid.g:658:4: (lv_severity_4_0= ruleSeverityKind )
            // InternalValid.g:659:5: lv_severity_4_0= ruleSeverityKind
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

            // InternalValid.g:676:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalValid.g:677:4: (lv_name_5_0= RULE_ID )
            {
            // InternalValid.g:677:4: (lv_name_5_0= RULE_ID )
            // InternalValid.g:678:5: lv_name_5_0= RULE_ID
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
            		
            // InternalValid.g:698:3: ( (lv_label_7_0= RULE_STRING ) )
            // InternalValid.g:699:4: (lv_label_7_0= RULE_STRING )
            {
            // InternalValid.g:699:4: (lv_label_7_0= RULE_STRING )
            // InternalValid.g:700:5: lv_label_7_0= RULE_STRING
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

            // InternalValid.g:716:3: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==14) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalValid.g:717:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_8, grammarAccess.getSizeRuleAccess().getDescriptionKeyword_6_0());
                    			
                    // InternalValid.g:721:4: ( (lv_description_9_0= RULE_STRING ) )
                    // InternalValid.g:722:5: (lv_description_9_0= RULE_STRING )
                    {
                    // InternalValid.g:722:5: (lv_description_9_0= RULE_STRING )
                    // InternalValid.g:723:6: lv_description_9_0= RULE_STRING
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

            // InternalValid.g:740:3: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==18) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalValid.g:741:4: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_5); 

                    				newLeafNode(otherlv_10, grammarAccess.getSizeRuleAccess().getMessageKeyword_7_0());
                    			
                    // InternalValid.g:745:4: ( (lv_message_11_0= RULE_STRING ) )
                    // InternalValid.g:746:5: (lv_message_11_0= RULE_STRING )
                    {
                    // InternalValid.g:746:5: (lv_message_11_0= RULE_STRING )
                    // InternalValid.g:747:6: lv_message_11_0= RULE_STRING
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
            		
            // InternalValid.g:768:3: ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )?
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
                    // InternalValid.g:769:4: ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..'
                    {
                    // InternalValid.g:769:4: ( (lv_min_13_0= RULE_INT ) )
                    // InternalValid.g:770:5: (lv_min_13_0= RULE_INT )
                    {
                    // InternalValid.g:770:5: (lv_min_13_0= RULE_INT )
                    // InternalValid.g:771:6: lv_min_13_0= RULE_INT
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

            // InternalValid.g:792:3: ( (lv_max_15_0= RULE_INT ) )
            // InternalValid.g:793:4: (lv_max_15_0= RULE_INT )
            {
            // InternalValid.g:793:4: (lv_max_15_0= RULE_INT )
            // InternalValid.g:794:5: lv_max_15_0= RULE_INT
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
            		
            // InternalValid.g:818:3: ( (lv_contexts_18_0= ruleSimpleContext ) )+
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
            	    // InternalValid.g:819:4: (lv_contexts_18_0= ruleSimpleContext )
            	    {
            	    // InternalValid.g:819:4: (lv_contexts_18_0= ruleSimpleContext )
            	    // InternalValid.g:820:5: lv_contexts_18_0= ruleSimpleContext
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
    // InternalValid.g:845:1: entryRuleRangeRule returns [EObject current=null] : iv_ruleRangeRule= ruleRangeRule EOF ;
    public final EObject entryRuleRangeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRangeRule = null;


        try {
            // InternalValid.g:845:50: (iv_ruleRangeRule= ruleRangeRule EOF )
            // InternalValid.g:846:2: iv_ruleRangeRule= ruleRangeRule EOF
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
    // InternalValid.g:852:1: ruleRangeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) ;
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
            // InternalValid.g:858:2: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) )
            // InternalValid.g:859:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            {
            // InternalValid.g:859:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            // InternalValid.g:860:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}'
            {
            // InternalValid.g:860:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:861:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:861:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:862:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            				
            // InternalValid.g:865:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:866:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:866:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
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
            	    // InternalValid.g:867:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:867:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:868:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:868:106: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:869:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0);
            	    					
            	    // InternalValid.g:872:9: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:872:10: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "true");
            	    }
            	    // InternalValid.g:872:19: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:872:20: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:872:20: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:873:10: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_22); 

            	    										newLeafNode(lv_optional_1_0, grammarAccess.getRangeRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getRangeRuleRule());
            	    										}
            	    										setWithLastConsumed(current, "optional", lv_optional_1_0 != null, "optional");
            	    									

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:890:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:890:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:891:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:891:106: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:892:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1);
            	    					
            	    // InternalValid.g:895:9: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:895:10: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "true");
            	    }
            	    // InternalValid.g:895:19: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:895:20: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:895:20: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:896:10: lv_checkKind_2_0= ruleCheckKind
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
            		
            // InternalValid.g:929:3: ( (lv_severity_4_0= ruleSeverityKind ) )
            // InternalValid.g:930:4: (lv_severity_4_0= ruleSeverityKind )
            {
            // InternalValid.g:930:4: (lv_severity_4_0= ruleSeverityKind )
            // InternalValid.g:931:5: lv_severity_4_0= ruleSeverityKind
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

            // InternalValid.g:948:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalValid.g:949:4: (lv_name_5_0= RULE_ID )
            {
            // InternalValid.g:949:4: (lv_name_5_0= RULE_ID )
            // InternalValid.g:950:5: lv_name_5_0= RULE_ID
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
            		
            // InternalValid.g:970:3: ( (lv_label_7_0= RULE_STRING ) )
            // InternalValid.g:971:4: (lv_label_7_0= RULE_STRING )
            {
            // InternalValid.g:971:4: (lv_label_7_0= RULE_STRING )
            // InternalValid.g:972:5: lv_label_7_0= RULE_STRING
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

            // InternalValid.g:988:3: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==14) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalValid.g:989:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_8, grammarAccess.getRangeRuleAccess().getDescriptionKeyword_6_0());
                    			
                    // InternalValid.g:993:4: ( (lv_description_9_0= RULE_STRING ) )
                    // InternalValid.g:994:5: (lv_description_9_0= RULE_STRING )
                    {
                    // InternalValid.g:994:5: (lv_description_9_0= RULE_STRING )
                    // InternalValid.g:995:6: lv_description_9_0= RULE_STRING
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

            // InternalValid.g:1012:3: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalValid.g:1013:4: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_5); 

                    				newLeafNode(otherlv_10, grammarAccess.getRangeRuleAccess().getMessageKeyword_7_0());
                    			
                    // InternalValid.g:1017:4: ( (lv_message_11_0= RULE_STRING ) )
                    // InternalValid.g:1018:5: (lv_message_11_0= RULE_STRING )
                    {
                    // InternalValid.g:1018:5: (lv_message_11_0= RULE_STRING )
                    // InternalValid.g:1019:6: lv_message_11_0= RULE_STRING
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
            		
            // InternalValid.g:1040:3: ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )?
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
                    // InternalValid.g:1041:4: ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..'
                    {
                    // InternalValid.g:1041:4: ( (lv_min_13_0= RULE_INT ) )
                    // InternalValid.g:1042:5: (lv_min_13_0= RULE_INT )
                    {
                    // InternalValid.g:1042:5: (lv_min_13_0= RULE_INT )
                    // InternalValid.g:1043:6: lv_min_13_0= RULE_INT
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

            // InternalValid.g:1064:3: ( (lv_max_15_0= RULE_INT ) )
            // InternalValid.g:1065:4: (lv_max_15_0= RULE_INT )
            {
            // InternalValid.g:1065:4: (lv_max_15_0= RULE_INT )
            // InternalValid.g:1066:5: lv_max_15_0= RULE_INT
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
            		
            // InternalValid.g:1090:3: ( (lv_contexts_18_0= ruleSimpleContext ) )+
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
            	    // InternalValid.g:1091:4: (lv_contexts_18_0= ruleSimpleContext )
            	    {
            	    // InternalValid.g:1091:4: (lv_contexts_18_0= ruleSimpleContext )
            	    // InternalValid.g:1092:5: lv_contexts_18_0= ruleSimpleContext
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
    // InternalValid.g:1117:1: entryRuleUniqueRule returns [EObject current=null] : iv_ruleUniqueRule= ruleUniqueRule EOF ;
    public final EObject entryRuleUniqueRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUniqueRule = null;


        try {
            // InternalValid.g:1117:51: (iv_ruleUniqueRule= ruleUniqueRule EOF )
            // InternalValid.g:1118:2: iv_ruleUniqueRule= ruleUniqueRule EOF
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
    // InternalValid.g:1124:1: ruleUniqueRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' ) ;
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
            // InternalValid.g:1130:2: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' ) )
            // InternalValid.g:1131:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' )
            {
            // InternalValid.g:1131:2: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' )
            // InternalValid.g:1132:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}'
            {
            // InternalValid.g:1132:3: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // InternalValid.g:1133:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // InternalValid.g:1133:4: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // InternalValid.g:1134:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            				  getUnorderedGroupHelper().enter(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            				
            // InternalValid.g:1137:5: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // InternalValid.g:1138:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // InternalValid.g:1138:6: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
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
            	    // InternalValid.g:1139:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // InternalValid.g:1139:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // InternalValid.g:1140:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // InternalValid.g:1140:107: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // InternalValid.g:1141:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0);
            	    					
            	    // InternalValid.g:1144:9: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // InternalValid.g:1144:10: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "true");
            	    }
            	    // InternalValid.g:1144:19: ( (lv_optional_1_0= 'optional' ) )
            	    // InternalValid.g:1144:20: (lv_optional_1_0= 'optional' )
            	    {
            	    // InternalValid.g:1144:20: (lv_optional_1_0= 'optional' )
            	    // InternalValid.g:1145:10: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_26); 

            	    										newLeafNode(lv_optional_1_0, grammarAccess.getUniqueRuleAccess().getOptionalOptionalKeyword_0_0_0());
            	    									

            	    										if (current==null) {
            	    											current = createModelElement(grammarAccess.getUniqueRuleRule());
            	    										}
            	    										setWithLastConsumed(current, "optional", lv_optional_1_0 != null, "optional");
            	    									

            	    }


            	    }


            	    }

            	     
            	    						getUnorderedGroupHelper().returnFromSelection(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalValid.g:1162:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // InternalValid.g:1162:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // InternalValid.g:1163:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // InternalValid.g:1163:107: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // InternalValid.g:1164:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {

            	    						getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1);
            	    					
            	    // InternalValid.g:1167:9: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // InternalValid.g:1167:10: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "true");
            	    }
            	    // InternalValid.g:1167:19: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // InternalValid.g:1167:20: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // InternalValid.g:1167:20: (lv_checkKind_2_0= ruleCheckKind )
            	    // InternalValid.g:1168:10: lv_checkKind_2_0= ruleCheckKind
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
            		
            // InternalValid.g:1201:3: ( (lv_severity_4_0= ruleSeverityKind ) )
            // InternalValid.g:1202:4: (lv_severity_4_0= ruleSeverityKind )
            {
            // InternalValid.g:1202:4: (lv_severity_4_0= ruleSeverityKind )
            // InternalValid.g:1203:5: lv_severity_4_0= ruleSeverityKind
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

            // InternalValid.g:1220:3: ( (lv_name_5_0= RULE_ID ) )
            // InternalValid.g:1221:4: (lv_name_5_0= RULE_ID )
            {
            // InternalValid.g:1221:4: (lv_name_5_0= RULE_ID )
            // InternalValid.g:1222:5: lv_name_5_0= RULE_ID
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
            		
            // InternalValid.g:1242:3: ( (lv_label_7_0= RULE_STRING ) )
            // InternalValid.g:1243:4: (lv_label_7_0= RULE_STRING )
            {
            // InternalValid.g:1243:4: (lv_label_7_0= RULE_STRING )
            // InternalValid.g:1244:5: lv_label_7_0= RULE_STRING
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

            // InternalValid.g:1260:3: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==14) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalValid.g:1261:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_5); 

                    				newLeafNode(otherlv_8, grammarAccess.getUniqueRuleAccess().getDescriptionKeyword_6_0());
                    			
                    // InternalValid.g:1265:4: ( (lv_description_9_0= RULE_STRING ) )
                    // InternalValid.g:1266:5: (lv_description_9_0= RULE_STRING )
                    {
                    // InternalValid.g:1266:5: (lv_description_9_0= RULE_STRING )
                    // InternalValid.g:1267:6: lv_description_9_0= RULE_STRING
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

            // InternalValid.g:1284:3: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==18) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalValid.g:1285:4: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_5); 

                    				newLeafNode(otherlv_10, grammarAccess.getUniqueRuleAccess().getMessageKeyword_7_0());
                    			
                    // InternalValid.g:1289:4: ( (lv_message_11_0= RULE_STRING ) )
                    // InternalValid.g:1290:5: (lv_message_11_0= RULE_STRING )
                    {
                    // InternalValid.g:1290:5: (lv_message_11_0= RULE_STRING )
                    // InternalValid.g:1291:6: lv_message_11_0= RULE_STRING
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
            		
            // InternalValid.g:1316:3: ( (lv_contexts_14_0= ruleDuplicateContext ) )+
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
            	    // InternalValid.g:1317:4: (lv_contexts_14_0= ruleDuplicateContext )
            	    {
            	    // InternalValid.g:1317:4: (lv_contexts_14_0= ruleDuplicateContext )
            	    // InternalValid.g:1318:5: lv_contexts_14_0= ruleDuplicateContext
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
    // InternalValid.g:1343:1: entryRuleSimpleContext returns [EObject current=null] : iv_ruleSimpleContext= ruleSimpleContext EOF ;
    public final EObject entryRuleSimpleContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleContext = null;


        try {
            // InternalValid.g:1343:54: (iv_ruleSimpleContext= ruleSimpleContext EOF )
            // InternalValid.g:1344:2: iv_ruleSimpleContext= ruleSimpleContext EOF
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
    // InternalValid.g:1350:1: ruleSimpleContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' ) ;
    public final EObject ruleSimpleContext() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalValid.g:1356:2: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' ) )
            // InternalValid.g:1357:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' )
            {
            // InternalValid.g:1357:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' )
            // InternalValid.g:1358:3: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';'
            {
            // InternalValid.g:1358:3: ( ( ruleQualifiedID ) )
            // InternalValid.g:1359:4: ( ruleQualifiedID )
            {
            // InternalValid.g:1359:4: ( ruleQualifiedID )
            // InternalValid.g:1360:5: ruleQualifiedID
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

            // InternalValid.g:1374:3: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==24) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalValid.g:1375:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_6); 

                    				newLeafNode(otherlv_1, grammarAccess.getSimpleContextAccess().getNumberSignKeyword_1_0());
                    			
                    // InternalValid.g:1379:4: ( (otherlv_2= RULE_ID ) )
                    // InternalValid.g:1380:5: (otherlv_2= RULE_ID )
                    {
                    // InternalValid.g:1380:5: (otherlv_2= RULE_ID )
                    // InternalValid.g:1381:6: otherlv_2= RULE_ID
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
    // InternalValid.g:1401:1: entryRuleDuplicateContext returns [EObject current=null] : iv_ruleDuplicateContext= ruleDuplicateContext EOF ;
    public final EObject entryRuleDuplicateContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDuplicateContext = null;


        try {
            // InternalValid.g:1401:57: (iv_ruleDuplicateContext= ruleDuplicateContext EOF )
            // InternalValid.g:1402:2: iv_ruleDuplicateContext= ruleDuplicateContext EOF
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
    // InternalValid.g:1408:1: ruleDuplicateContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' ) ;
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
            // InternalValid.g:1414:2: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' ) )
            // InternalValid.g:1415:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' )
            {
            // InternalValid.g:1415:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' )
            // InternalValid.g:1416:3: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';'
            {
            // InternalValid.g:1416:3: ( ( ruleQualifiedID ) )
            // InternalValid.g:1417:4: ( ruleQualifiedID )
            {
            // InternalValid.g:1417:4: ( ruleQualifiedID )
            // InternalValid.g:1418:5: ruleQualifiedID
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

            // InternalValid.g:1432:3: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==24) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalValid.g:1433:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_6); 

                    				newLeafNode(otherlv_1, grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_1_0());
                    			
                    // InternalValid.g:1437:4: ( (otherlv_2= RULE_ID ) )
                    // InternalValid.g:1438:5: (otherlv_2= RULE_ID )
                    {
                    // InternalValid.g:1438:5: (otherlv_2= RULE_ID )
                    // InternalValid.g:1439:6: otherlv_2= RULE_ID
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
            		
            // InternalValid.g:1455:3: ( ( ruleQualifiedID ) )
            // InternalValid.g:1456:4: ( ruleQualifiedID )
            {
            // InternalValid.g:1456:4: ( ruleQualifiedID )
            // InternalValid.g:1457:5: ruleQualifiedID
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
            		
            // InternalValid.g:1475:3: ( (otherlv_6= RULE_ID ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalValid.g:1476:4: (otherlv_6= RULE_ID )
                    {
                    // InternalValid.g:1476:4: (otherlv_6= RULE_ID )
                    // InternalValid.g:1477:5: otherlv_6= RULE_ID
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
    // InternalValid.g:1496:1: entryRuleNativeContext returns [EObject current=null] : iv_ruleNativeContext= ruleNativeContext EOF ;
    public final EObject entryRuleNativeContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNativeContext = null;


        try {
            // InternalValid.g:1496:54: (iv_ruleNativeContext= ruleNativeContext EOF )
            // InternalValid.g:1497:2: iv_ruleNativeContext= ruleNativeContext EOF
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
    // InternalValid.g:1503:1: ruleNativeContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' ) ;
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
            // InternalValid.g:1509:2: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' ) )
            // InternalValid.g:1510:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' )
            {
            // InternalValid.g:1510:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' )
            // InternalValid.g:1511:3: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';'
            {
            // InternalValid.g:1511:3: ( ( ruleQualifiedID ) )
            // InternalValid.g:1512:4: ( ruleQualifiedID )
            {
            // InternalValid.g:1512:4: ( ruleQualifiedID )
            // InternalValid.g:1513:5: ruleQualifiedID
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

            // InternalValid.g:1527:3: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==24) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalValid.g:1528:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_6); 

                    				newLeafNode(otherlv_1, grammarAccess.getNativeContextAccess().getNumberSignKeyword_1_0());
                    			
                    // InternalValid.g:1532:4: ( (otherlv_2= RULE_ID ) )
                    // InternalValid.g:1533:5: (otherlv_2= RULE_ID )
                    {
                    // InternalValid.g:1533:5: (otherlv_2= RULE_ID )
                    // InternalValid.g:1534:6: otherlv_2= RULE_ID
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

            // InternalValid.g:1546:3: ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==27) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalValid.g:1547:4: ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) )
                    {
                    // InternalValid.g:1547:4: ( (lv_named_3_0= 'as' ) )
                    // InternalValid.g:1548:5: (lv_named_3_0= 'as' )
                    {
                    // InternalValid.g:1548:5: (lv_named_3_0= 'as' )
                    // InternalValid.g:1549:6: lv_named_3_0= 'as'
                    {
                    lv_named_3_0=(Token)match(input,27,FOLLOW_6); 

                    						newLeafNode(lv_named_3_0, grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getNativeContextRule());
                    						}
                    						setWithLastConsumed(current, "named", lv_named_3_0 != null, "as");
                    					

                    }


                    }

                    // InternalValid.g:1561:4: ( (lv_givenName_4_0= RULE_ID ) )
                    // InternalValid.g:1562:5: (lv_givenName_4_0= RULE_ID )
                    {
                    // InternalValid.g:1562:5: (lv_givenName_4_0= RULE_ID )
                    // InternalValid.g:1563:6: lv_givenName_4_0= RULE_ID
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

            // InternalValid.g:1580:3: (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==26) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalValid.g:1581:4: otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) )
                    {
                    otherlv_5=(Token)match(input,26,FOLLOW_6); 

                    				newLeafNode(otherlv_5, grammarAccess.getNativeContextAccess().getMarkerKeyword_3_0());
                    			
                    // InternalValid.g:1585:4: ( ( ruleQualifiedID ) )
                    // InternalValid.g:1586:5: ( ruleQualifiedID )
                    {
                    // InternalValid.g:1586:5: ( ruleQualifiedID )
                    // InternalValid.g:1587:6: ruleQualifiedID
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

                    // InternalValid.g:1601:4: (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) )
                    // InternalValid.g:1602:5: otherlv_7= '#' ( (otherlv_8= RULE_ID ) )
                    {
                    otherlv_7=(Token)match(input,24,FOLLOW_6); 

                    					newLeafNode(otherlv_7, grammarAccess.getNativeContextAccess().getNumberSignKeyword_3_2_0());
                    				
                    // InternalValid.g:1606:5: ( (otherlv_8= RULE_ID ) )
                    // InternalValid.g:1607:6: (otherlv_8= RULE_ID )
                    {
                    // InternalValid.g:1607:6: (otherlv_8= RULE_ID )
                    // InternalValid.g:1608:7: otherlv_8= RULE_ID
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

            // InternalValid.g:1621:3: (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==28) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalValid.g:1622:4: otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}'
                    {
                    otherlv_9=(Token)match(input,28,FOLLOW_9); 

                    				newLeafNode(otherlv_9, grammarAccess.getNativeContextAccess().getQuickfixesKeyword_4_0());
                    			
                    otherlv_10=(Token)match(input,15,FOLLOW_39); 

                    				newLeafNode(otherlv_10, grammarAccess.getNativeContextAccess().getLeftCurlyBracketKeyword_4_1());
                    			
                    // InternalValid.g:1630:4: ( (lv_quickFixes_11_0= ruleQuickFix ) )+
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
                    	    // InternalValid.g:1631:5: (lv_quickFixes_11_0= ruleQuickFix )
                    	    {
                    	    // InternalValid.g:1631:5: (lv_quickFixes_11_0= ruleQuickFix )
                    	    // InternalValid.g:1632:6: lv_quickFixes_11_0= ruleQuickFix
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
    // InternalValid.g:1662:1: entryRuleQuickFix returns [EObject current=null] : iv_ruleQuickFix= ruleQuickFix EOF ;
    public final EObject entryRuleQuickFix() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQuickFix = null;


        try {
            // InternalValid.g:1662:49: (iv_ruleQuickFix= ruleQuickFix EOF )
            // InternalValid.g:1663:2: iv_ruleQuickFix= ruleQuickFix EOF
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
    // InternalValid.g:1669:1: ruleQuickFix returns [EObject current=null] : ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' ) ;
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
            // InternalValid.g:1675:2: ( ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' ) )
            // InternalValid.g:1676:2: ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' )
            {
            // InternalValid.g:1676:2: ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' )
            // InternalValid.g:1677:3: ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';'
            {
            // InternalValid.g:1677:3: ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=36 && LA32_0<=37)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // InternalValid.g:1678:4: (lv_quickFixKind_0_0= ruleQuickFixKind )
                    {
                    // InternalValid.g:1678:4: (lv_quickFixKind_0_0= ruleQuickFixKind )
                    // InternalValid.g:1679:5: lv_quickFixKind_0_0= ruleQuickFixKind
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
            		
            // InternalValid.g:1700:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalValid.g:1701:4: (lv_name_2_0= RULE_ID )
            {
            // InternalValid.g:1701:4: (lv_name_2_0= RULE_ID )
            // InternalValid.g:1702:5: lv_name_2_0= RULE_ID
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
            		
            // InternalValid.g:1722:3: ( (lv_label_4_0= RULE_STRING ) )
            // InternalValid.g:1723:4: (lv_label_4_0= RULE_STRING )
            {
            // InternalValid.g:1723:4: (lv_label_4_0= RULE_STRING )
            // InternalValid.g:1724:5: lv_label_4_0= RULE_STRING
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
            		
            // InternalValid.g:1744:3: ( (lv_message_6_0= RULE_STRING ) )
            // InternalValid.g:1745:4: (lv_message_6_0= RULE_STRING )
            {
            // InternalValid.g:1745:4: (lv_message_6_0= RULE_STRING )
            // InternalValid.g:1746:5: lv_message_6_0= RULE_STRING
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
    // InternalValid.g:1770:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalValid.g:1770:51: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalValid.g:1771:2: iv_ruleQualifiedID= ruleQualifiedID EOF
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
    // InternalValid.g:1777:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalValid.g:1783:2: ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID ) )
            // InternalValid.g:1784:2: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID )
            {
            // InternalValid.g:1784:2: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID )
            // InternalValid.g:1785:3: (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID
            {
            // InternalValid.g:1785:3: (this_ID_0= RULE_ID kw= '::' )*
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
            	    // InternalValid.g:1786:4: this_ID_0= RULE_ID kw= '::'
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
    // InternalValid.g:1810:1: ruleCheckKind returns [Enumerator current=null] : ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) ) ;
    public final Enumerator ruleCheckKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalValid.g:1816:2: ( ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) ) )
            // InternalValid.g:1817:2: ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) )
            {
            // InternalValid.g:1817:2: ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) )
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
                    // InternalValid.g:1818:3: (enumLiteral_0= 'fast' )
                    {
                    // InternalValid.g:1818:3: (enumLiteral_0= 'fast' )
                    // InternalValid.g:1819:4: enumLiteral_0= 'fast'
                    {
                    enumLiteral_0=(Token)match(input,31,FOLLOW_2); 

                    				current = grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:1826:3: (enumLiteral_1= 'normal' )
                    {
                    // InternalValid.g:1826:3: (enumLiteral_1= 'normal' )
                    // InternalValid.g:1827:4: enumLiteral_1= 'normal'
                    {
                    enumLiteral_1=(Token)match(input,32,FOLLOW_2); 

                    				current = grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalValid.g:1834:3: (enumLiteral_2= 'expensive' )
                    {
                    // InternalValid.g:1834:3: (enumLiteral_2= 'expensive' )
                    // InternalValid.g:1835:4: enumLiteral_2= 'expensive'
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
    // InternalValid.g:1845:1: ruleSeverityKind returns [Enumerator current=null] : ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) ) ;
    public final Enumerator ruleSeverityKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalValid.g:1851:2: ( ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) ) )
            // InternalValid.g:1852:2: ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) )
            {
            // InternalValid.g:1852:2: ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) )
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
                    // InternalValid.g:1853:3: (enumLiteral_0= 'error' )
                    {
                    // InternalValid.g:1853:3: (enumLiteral_0= 'error' )
                    // InternalValid.g:1854:4: enumLiteral_0= 'error'
                    {
                    enumLiteral_0=(Token)match(input,34,FOLLOW_2); 

                    				current = grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:1861:3: (enumLiteral_1= 'warning' )
                    {
                    // InternalValid.g:1861:3: (enumLiteral_1= 'warning' )
                    // InternalValid.g:1862:4: enumLiteral_1= 'warning'
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
            // InternalValid.g:1878:2: ( ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) ) )
            // InternalValid.g:1879:2: ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) )
            {
            // InternalValid.g:1879:2: ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) )
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
                    // InternalValid.g:1880:3: (enumLiteral_0= 'semantic' )
                    {
                    // InternalValid.g:1880:3: (enumLiteral_0= 'semantic' )
                    // InternalValid.g:1881:4: enumLiteral_0= 'semantic'
                    {
                    enumLiteral_0=(Token)match(input,36,FOLLOW_2); 

                    				current = grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalValid.g:1888:3: (enumLiteral_1= 'textual' )
                    {
                    // InternalValid.g:1888:3: (enumLiteral_1= 'textual' )
                    // InternalValid.g:1889:4: enumLiteral_1= 'textual'
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
            return "286:2: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )";
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
            return "322:2: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )";
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
