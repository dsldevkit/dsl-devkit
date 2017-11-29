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
    public String getGrammarFileName() { return "../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g"; }



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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:68:1: entryRuleValidModel returns [EObject current=null] : iv_ruleValidModel= ruleValidModel EOF ;
    public final EObject entryRuleValidModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValidModel = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:69:2: (iv_ruleValidModel= ruleValidModel EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:70:2: iv_ruleValidModel= ruleValidModel EOF
            {
             newCompositeNode(grammarAccess.getValidModelRule()); 
            pushFollow(FOLLOW_ruleValidModel_in_entryRuleValidModel75);
            iv_ruleValidModel=ruleValidModel();

            state._fsp--;

             current =iv_ruleValidModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleValidModel85); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:77:1: ruleValidModel returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* ) ;
    public final EObject ruleValidModel() throws RecognitionException {
        EObject current = null;

        EObject lv_imports_0_0 = null;

        EObject lv_categories_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:80:28: ( ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:81:1: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:81:1: ( ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )* )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:81:2: ( (lv_imports_0_0= ruleImport ) )* ( (lv_categories_1_0= ruleCategory ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:81:2: ( (lv_imports_0_0= ruleImport ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:82:1: (lv_imports_0_0= ruleImport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:82:1: (lv_imports_0_0= ruleImport )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:83:3: lv_imports_0_0= ruleImport
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getValidModelAccess().getImportsImportParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleImport_in_ruleValidModel131);
            	    lv_imports_0_0=ruleImport();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getValidModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"imports",
            	            		lv_imports_0_0, 
            	            		"Import");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:99:3: ( (lv_categories_1_0= ruleCategory ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==12) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:100:1: (lv_categories_1_0= ruleCategory )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:100:1: (lv_categories_1_0= ruleCategory )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:101:3: lv_categories_1_0= ruleCategory
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getValidModelAccess().getCategoriesCategoryParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCategory_in_ruleValidModel153);
            	    lv_categories_1_0=ruleCategory();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getValidModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"categories",
            	            		lv_categories_1_0, 
            	            		"Category");
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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:125:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:126:2: (iv_ruleImport= ruleImport EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:127:2: iv_ruleImport= ruleImport EOF
            {
             newCompositeNode(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport190);
            iv_ruleImport=ruleImport();

            state._fsp--;

             current =iv_ruleImport; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport200); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:134:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:137:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:138:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:138:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:138:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleImport237); 

                	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:142:1: ( (otherlv_1= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:143:1: (otherlv_1= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:143:1: (otherlv_1= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:144:3: otherlv_1= RULE_STRING
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getImportRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImport257); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:163:1: entryRuleCategory returns [EObject current=null] : iv_ruleCategory= ruleCategory EOF ;
    public final EObject entryRuleCategory() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCategory = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:164:2: (iv_ruleCategory= ruleCategory EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:165:2: iv_ruleCategory= ruleCategory EOF
            {
             newCompositeNode(grammarAccess.getCategoryRule()); 
            pushFollow(FOLLOW_ruleCategory_in_entryRuleCategory293);
            iv_ruleCategory=ruleCategory();

            state._fsp--;

             current =iv_ruleCategory; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCategory303); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:172:1: ruleCategory returns [EObject current=null] : (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:175:28: ( (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:176:1: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:176:1: (otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:176:3: otherlv_0= 'category' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'label' ( (lv_label_3_0= RULE_STRING ) ) (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )? otherlv_6= '{' ( (lv_rules_7_0= ruleRule ) )* otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleCategory340); 

                	newLeafNode(otherlv_0, grammarAccess.getCategoryAccess().getCategoryKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:180:1: ( (lv_name_1_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:181:1: (lv_name_1_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:181:1: (lv_name_1_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:182:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleCategory357); 

            			newLeafNode(lv_name_1_0, grammarAccess.getCategoryAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getCategoryRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleCategory374); 

                	newLeafNode(otherlv_2, grammarAccess.getCategoryAccess().getLabelKeyword_2());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:202:1: ( (lv_label_3_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:203:1: (lv_label_3_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:203:1: (lv_label_3_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:204:3: lv_label_3_0= RULE_STRING
            {
            lv_label_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleCategory391); 

            			newLeafNode(lv_label_3_0, grammarAccess.getCategoryAccess().getLabelSTRINGTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getCategoryRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_3_0, 
                    		"STRING");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:220:2: (otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:220:4: otherlv_4= 'description' ( (lv_description_5_0= RULE_STRING ) )
                    {
                    otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleCategory409); 

                        	newLeafNode(otherlv_4, grammarAccess.getCategoryAccess().getDescriptionKeyword_4_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:224:1: ( (lv_description_5_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:225:1: (lv_description_5_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:225:1: (lv_description_5_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:226:3: lv_description_5_0= RULE_STRING
                    {
                    lv_description_5_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleCategory426); 

                    			newLeafNode(lv_description_5_0, grammarAccess.getCategoryAccess().getDescriptionSTRINGTerminalRuleCall_4_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getCategoryRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,15,FOLLOW_15_in_ruleCategory445); 

                	newLeafNode(otherlv_6, grammarAccess.getCategoryAccess().getLeftCurlyBracketKeyword_5());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:246:1: ( (lv_rules_7_0= ruleRule ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17||LA4_0==20||(LA4_0>=22 && LA4_0<=23)||(LA4_0>=31 && LA4_0<=35)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:247:1: (lv_rules_7_0= ruleRule )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:247:1: (lv_rules_7_0= ruleRule )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:248:3: lv_rules_7_0= ruleRule
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getCategoryAccess().getRulesRuleParserRuleCall_6_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleRule_in_ruleCategory466);
            	    lv_rules_7_0=ruleRule();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getCategoryRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"rules",
            	            		lv_rules_7_0, 
            	            		"Rule");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            otherlv_8=(Token)match(input,16,FOLLOW_16_in_ruleCategory479); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:276:1: entryRuleRule returns [EObject current=null] : iv_ruleRule= ruleRule EOF ;
    public final EObject entryRuleRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:277:2: (iv_ruleRule= ruleRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:278:2: iv_ruleRule= ruleRule EOF
            {
             newCompositeNode(grammarAccess.getRuleRule()); 
            pushFollow(FOLLOW_ruleRule_in_entryRuleRule515);
            iv_ruleRule=ruleRule();

            state._fsp--;

             current =iv_ruleRule; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRule525); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:285:1: ruleRule returns [EObject current=null] : (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule ) ;
    public final EObject ruleRule() throws RecognitionException {
        EObject current = null;

        EObject this_NativeRule_0 = null;

        EObject this_PredefinedRule_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:288:28: ( (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:289:1: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:289:1: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:290:5: this_NativeRule_0= ruleNativeRule
                    {
                     
                            newCompositeNode(grammarAccess.getRuleAccess().getNativeRuleParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleNativeRule_in_ruleRule572);
                    this_NativeRule_0=ruleNativeRule();

                    state._fsp--;

                     
                            current = this_NativeRule_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:300:5: this_PredefinedRule_1= rulePredefinedRule
                    {
                     
                            newCompositeNode(grammarAccess.getRuleAccess().getPredefinedRuleParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_rulePredefinedRule_in_ruleRule599);
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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:316:1: entryRulePredefinedRule returns [EObject current=null] : iv_rulePredefinedRule= rulePredefinedRule EOF ;
    public final EObject entryRulePredefinedRule() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredefinedRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:317:2: (iv_rulePredefinedRule= rulePredefinedRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:318:2: iv_rulePredefinedRule= rulePredefinedRule EOF
            {
             newCompositeNode(grammarAccess.getPredefinedRuleRule()); 
            pushFollow(FOLLOW_rulePredefinedRule_in_entryRulePredefinedRule634);
            iv_rulePredefinedRule=rulePredefinedRule();

            state._fsp--;

             current =iv_rulePredefinedRule; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePredefinedRule644); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:325:1: rulePredefinedRule returns [EObject current=null] : (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule ) ;
    public final EObject rulePredefinedRule() throws RecognitionException {
        EObject current = null;

        EObject this_SizeRule_0 = null;

        EObject this_RangeRule_1 = null;

        EObject this_UniqueRule_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:328:28: ( (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:329:1: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:329:1: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:330:5: this_SizeRule_0= ruleSizeRule
                    {
                     
                            newCompositeNode(grammarAccess.getPredefinedRuleAccess().getSizeRuleParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleSizeRule_in_rulePredefinedRule691);
                    this_SizeRule_0=ruleSizeRule();

                    state._fsp--;

                     
                            current = this_SizeRule_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:340:5: this_RangeRule_1= ruleRangeRule
                    {
                     
                            newCompositeNode(grammarAccess.getPredefinedRuleAccess().getRangeRuleParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleRangeRule_in_rulePredefinedRule718);
                    this_RangeRule_1=ruleRangeRule();

                    state._fsp--;

                     
                            current = this_RangeRule_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:350:5: this_UniqueRule_2= ruleUniqueRule
                    {
                     
                            newCompositeNode(grammarAccess.getPredefinedRuleAccess().getUniqueRuleParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleUniqueRule_in_rulePredefinedRule745);
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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:366:1: entryRuleNativeRule returns [EObject current=null] : iv_ruleNativeRule= ruleNativeRule EOF ;
    public final EObject entryRuleNativeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNativeRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:367:2: (iv_ruleNativeRule= ruleNativeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:368:2: iv_ruleNativeRule= ruleNativeRule EOF
            {
             newCompositeNode(grammarAccess.getNativeRuleRule()); 
            pushFollow(FOLLOW_ruleNativeRule_in_entryRuleNativeRule780);
            iv_ruleNativeRule=ruleNativeRule();

            state._fsp--;

             current =iv_ruleNativeRule; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNativeRule790); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:375:1: ruleNativeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:378:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:379:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:379:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:379:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) ( (lv_severity_3_0= ruleSeverityKind ) ) ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'label' ( (lv_label_6_0= RULE_STRING ) ) (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )? otherlv_9= 'message' ( (lv_message_10_0= RULE_STRING ) ) otherlv_11= 'context' otherlv_12= '{' ( (lv_contexts_13_0= ruleNativeContext ) )+ otherlv_14= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:379:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:381:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:381:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:382:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0());
            	
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:385:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:386:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:386:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop7:
            do {
                int alt7=3;
                int LA7_0 = input.LA(1);

                if ( LA7_0 ==17 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt7=1;
                }
                else if ( LA7_0 >=31 && LA7_0<=33 && getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt7=2;
                }


                switch (alt7) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:388:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:388:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:389:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:389:107: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:390:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:393:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:393:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:393:16: ( (lv_optional_1_0= 'optional' ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:394:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:394:1: (lv_optional_1_0= 'optional' )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:395:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_17_in_ruleNativeRule878); 

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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:415:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:415:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:416:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:416:107: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:417:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getNativeRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:420:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:420:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleNativeRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:420:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:421:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:421:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:422:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNativeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCheckKind_in_ruleNativeRule966);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getNativeRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"CheckKind");
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

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:452:2: ( (lv_severity_3_0= ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:453:1: (lv_severity_3_0= ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:453:1: (lv_severity_3_0= ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:454:3: lv_severity_3_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getNativeRuleAccess().getSeveritySeverityKindEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleSeverityKind_in_ruleNativeRule1027);
            lv_severity_3_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getNativeRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_3_0, 
                    		"SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:470:2: ( (lv_name_4_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:471:1: (lv_name_4_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:471:1: (lv_name_4_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:472:3: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNativeRule1044); 

            			newLeafNode(lv_name_4_0, grammarAccess.getNativeRuleAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_4_0, 
                    		"ID");
            	    

            }


            }

            otherlv_5=(Token)match(input,13,FOLLOW_13_in_ruleNativeRule1061); 

                	newLeafNode(otherlv_5, grammarAccess.getNativeRuleAccess().getLabelKeyword_3());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:492:1: ( (lv_label_6_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:493:1: (lv_label_6_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:493:1: (lv_label_6_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:494:3: lv_label_6_0= RULE_STRING
            {
            lv_label_6_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNativeRule1078); 

            			newLeafNode(lv_label_6_0, grammarAccess.getNativeRuleAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_6_0, 
                    		"STRING");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:510:2: (otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==14) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:510:4: otherlv_7= 'description' ( (lv_description_8_0= RULE_STRING ) )
                    {
                    otherlv_7=(Token)match(input,14,FOLLOW_14_in_ruleNativeRule1096); 

                        	newLeafNode(otherlv_7, grammarAccess.getNativeRuleAccess().getDescriptionKeyword_5_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:514:1: ( (lv_description_8_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:515:1: (lv_description_8_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:515:1: (lv_description_8_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:516:3: lv_description_8_0= RULE_STRING
                    {
                    lv_description_8_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNativeRule1113); 

                    			newLeafNode(lv_description_8_0, grammarAccess.getNativeRuleAccess().getDescriptionSTRINGTerminalRuleCall_5_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_8_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_9=(Token)match(input,18,FOLLOW_18_in_ruleNativeRule1132); 

                	newLeafNode(otherlv_9, grammarAccess.getNativeRuleAccess().getMessageKeyword_6());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:536:1: ( (lv_message_10_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:537:1: (lv_message_10_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:537:1: (lv_message_10_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:538:3: lv_message_10_0= RULE_STRING
            {
            lv_message_10_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleNativeRule1149); 

            			newLeafNode(lv_message_10_0, grammarAccess.getNativeRuleAccess().getMessageSTRINGTerminalRuleCall_7_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"message",
                    		lv_message_10_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_11=(Token)match(input,19,FOLLOW_19_in_ruleNativeRule1166); 

                	newLeafNode(otherlv_11, grammarAccess.getNativeRuleAccess().getContextKeyword_8());
                
            otherlv_12=(Token)match(input,15,FOLLOW_15_in_ruleNativeRule1178); 

                	newLeafNode(otherlv_12, grammarAccess.getNativeRuleAccess().getLeftCurlyBracketKeyword_9());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:562:1: ( (lv_contexts_13_0= ruleNativeContext ) )+
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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:563:1: (lv_contexts_13_0= ruleNativeContext )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:563:1: (lv_contexts_13_0= ruleNativeContext )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:564:3: lv_contexts_13_0= ruleNativeContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getNativeRuleAccess().getContextsNativeContextParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleNativeContext_in_ruleNativeRule1199);
            	    lv_contexts_13_0=ruleNativeContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getNativeRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_13_0, 
            	            		"NativeContext");
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

            otherlv_14=(Token)match(input,16,FOLLOW_16_in_ruleNativeRule1212); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:592:1: entryRuleSizeRule returns [EObject current=null] : iv_ruleSizeRule= ruleSizeRule EOF ;
    public final EObject entryRuleSizeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSizeRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:593:2: (iv_ruleSizeRule= ruleSizeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:594:2: iv_ruleSizeRule= ruleSizeRule EOF
            {
             newCompositeNode(grammarAccess.getSizeRuleRule()); 
            pushFollow(FOLLOW_ruleSizeRule_in_entryRuleSizeRule1248);
            iv_ruleSizeRule=ruleSizeRule();

            state._fsp--;

             current =iv_ruleSizeRule; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSizeRule1258); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:601:1: ruleSizeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:604:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:605:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:605:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:605:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'size' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'size' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:605:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:607:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:607:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:608:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0());
            	
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:611:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:612:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:612:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( LA10_0 ==17 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt10=1;
                }
                else if ( LA10_0 >=31 && LA10_0<=33 && getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:614:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:614:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:615:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:615:105: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:616:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:619:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:619:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:619:16: ( (lv_optional_1_0= 'optional' ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:620:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:620:1: (lv_optional_1_0= 'optional' )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:621:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_17_in_ruleSizeRule1346); 

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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:641:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:641:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:642:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:642:105: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:643:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getSizeRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:646:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:646:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleSizeRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:646:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:647:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:647:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:648:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSizeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCheckKind_in_ruleSizeRule1434);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSizeRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"CheckKind");
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

            otherlv_3=(Token)match(input,20,FOLLOW_20_in_ruleSizeRule1486); 

                	newLeafNode(otherlv_3, grammarAccess.getSizeRuleAccess().getSizeKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:682:1: ( (lv_severity_4_0= ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:683:1: (lv_severity_4_0= ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:683:1: (lv_severity_4_0= ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:684:3: lv_severity_4_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getSizeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSeverityKind_in_ruleSizeRule1507);
            lv_severity_4_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSizeRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_4_0, 
                    		"SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:700:2: ( (lv_name_5_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:701:1: (lv_name_5_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:701:1: (lv_name_5_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:702:3: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSizeRule1524); 

            			newLeafNode(lv_name_5_0, grammarAccess.getSizeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSizeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_5_0, 
                    		"ID");
            	    

            }


            }

            otherlv_6=(Token)match(input,13,FOLLOW_13_in_ruleSizeRule1541); 

                	newLeafNode(otherlv_6, grammarAccess.getSizeRuleAccess().getLabelKeyword_4());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:722:1: ( (lv_label_7_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:723:1: (lv_label_7_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:723:1: (lv_label_7_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:724:3: lv_label_7_0= RULE_STRING
            {
            lv_label_7_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSizeRule1558); 

            			newLeafNode(lv_label_7_0, grammarAccess.getSizeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSizeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_7_0, 
                    		"STRING");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:740:2: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==14) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:740:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_14_in_ruleSizeRule1576); 

                        	newLeafNode(otherlv_8, grammarAccess.getSizeRuleAccess().getDescriptionKeyword_6_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:744:1: ( (lv_description_9_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:745:1: (lv_description_9_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:745:1: (lv_description_9_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:746:3: lv_description_9_0= RULE_STRING
                    {
                    lv_description_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSizeRule1593); 

                    			newLeafNode(lv_description_9_0, grammarAccess.getSizeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSizeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_9_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:762:4: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==18) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:762:6: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_18_in_ruleSizeRule1613); 

                        	newLeafNode(otherlv_10, grammarAccess.getSizeRuleAccess().getMessageKeyword_7_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:766:1: ( (lv_message_11_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:767:1: (lv_message_11_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:767:1: (lv_message_11_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:768:3: lv_message_11_0= RULE_STRING
                    {
                    lv_message_11_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSizeRule1630); 

                    			newLeafNode(lv_message_11_0, grammarAccess.getSizeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSizeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"message",
                            		lv_message_11_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,20,FOLLOW_20_in_ruleSizeRule1649); 

                	newLeafNode(otherlv_12, grammarAccess.getSizeRuleAccess().getSizeKeyword_8());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:788:1: ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )?
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
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:788:2: ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..'
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:788:2: ( (lv_min_13_0= RULE_INT ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:789:1: (lv_min_13_0= RULE_INT )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:789:1: (lv_min_13_0= RULE_INT )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:790:3: lv_min_13_0= RULE_INT
                    {
                    lv_min_13_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleSizeRule1667); 

                    			newLeafNode(lv_min_13_0, grammarAccess.getSizeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSizeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"min",
                            		lv_min_13_0, 
                            		"INT");
                    	    

                    }


                    }

                    otherlv_14=(Token)match(input,21,FOLLOW_21_in_ruleSizeRule1684); 

                        	newLeafNode(otherlv_14, grammarAccess.getSizeRuleAccess().getFullStopFullStopKeyword_9_1());
                        

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:810:3: ( (lv_max_15_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:811:1: (lv_max_15_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:811:1: (lv_max_15_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:812:3: lv_max_15_0= RULE_INT
            {
            lv_max_15_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleSizeRule1703); 

            			newLeafNode(lv_max_15_0, grammarAccess.getSizeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSizeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"max",
                    		lv_max_15_0, 
                    		"INT");
            	    

            }


            }

            otherlv_16=(Token)match(input,19,FOLLOW_19_in_ruleSizeRule1720); 

                	newLeafNode(otherlv_16, grammarAccess.getSizeRuleAccess().getContextKeyword_11());
                
            otherlv_17=(Token)match(input,15,FOLLOW_15_in_ruleSizeRule1732); 

                	newLeafNode(otherlv_17, grammarAccess.getSizeRuleAccess().getLeftCurlyBracketKeyword_12());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:836:1: ( (lv_contexts_18_0= ruleSimpleContext ) )+
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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:837:1: (lv_contexts_18_0= ruleSimpleContext )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:837:1: (lv_contexts_18_0= ruleSimpleContext )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:838:3: lv_contexts_18_0= ruleSimpleContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getSizeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSimpleContext_in_ruleSizeRule1753);
            	    lv_contexts_18_0=ruleSimpleContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getSizeRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_18_0, 
            	            		"SimpleContext");
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

            otherlv_19=(Token)match(input,16,FOLLOW_16_in_ruleSizeRule1766); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:866:1: entryRuleRangeRule returns [EObject current=null] : iv_ruleRangeRule= ruleRangeRule EOF ;
    public final EObject entryRuleRangeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRangeRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:867:2: (iv_ruleRangeRule= ruleRangeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:868:2: iv_ruleRangeRule= ruleRangeRule EOF
            {
             newCompositeNode(grammarAccess.getRangeRuleRule()); 
            pushFollow(FOLLOW_ruleRangeRule_in_entryRuleRangeRule1802);
            iv_ruleRangeRule=ruleRangeRule();

            state._fsp--;

             current =iv_ruleRangeRule; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRangeRule1812); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:875:1: ruleRangeRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:878:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:879:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:879:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:879:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'range' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'range' ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )? ( (lv_max_15_0= RULE_INT ) ) otherlv_16= 'context' otherlv_17= '{' ( (lv_contexts_18_0= ruleSimpleContext ) )+ otherlv_19= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:879:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:881:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:881:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:882:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0());
            	
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:885:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:886:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:886:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop15:
            do {
                int alt15=3;
                int LA15_0 = input.LA(1);

                if ( LA15_0 ==17 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt15=1;
                }
                else if ( LA15_0 >=31 && LA15_0<=33 && getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt15=2;
                }


                switch (alt15) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:888:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:888:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:889:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:889:106: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:890:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:893:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:893:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:893:16: ( (lv_optional_1_0= 'optional' ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:894:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:894:1: (lv_optional_1_0= 'optional' )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:895:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_17_in_ruleRangeRule1900); 

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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:915:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:915:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:916:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:916:106: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:917:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getRangeRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:920:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:920:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleRangeRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:920:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:921:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:921:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:922:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCheckKind_in_ruleRangeRule1988);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"CheckKind");
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

            otherlv_3=(Token)match(input,22,FOLLOW_22_in_ruleRangeRule2040); 

                	newLeafNode(otherlv_3, grammarAccess.getRangeRuleAccess().getRangeKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:956:1: ( (lv_severity_4_0= ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:957:1: (lv_severity_4_0= ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:957:1: (lv_severity_4_0= ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:958:3: lv_severity_4_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getRangeRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSeverityKind_in_ruleRangeRule2061);
            lv_severity_4_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRangeRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_4_0, 
                    		"SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:974:2: ( (lv_name_5_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:975:1: (lv_name_5_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:975:1: (lv_name_5_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:976:3: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleRangeRule2078); 

            			newLeafNode(lv_name_5_0, grammarAccess.getRangeRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_5_0, 
                    		"ID");
            	    

            }


            }

            otherlv_6=(Token)match(input,13,FOLLOW_13_in_ruleRangeRule2095); 

                	newLeafNode(otherlv_6, grammarAccess.getRangeRuleAccess().getLabelKeyword_4());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:996:1: ( (lv_label_7_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:997:1: (lv_label_7_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:997:1: (lv_label_7_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:998:3: lv_label_7_0= RULE_STRING
            {
            lv_label_7_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleRangeRule2112); 

            			newLeafNode(lv_label_7_0, grammarAccess.getRangeRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_7_0, 
                    		"STRING");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1014:2: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==14) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1014:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_14_in_ruleRangeRule2130); 

                        	newLeafNode(otherlv_8, grammarAccess.getRangeRuleAccess().getDescriptionKeyword_6_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1018:1: ( (lv_description_9_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1019:1: (lv_description_9_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1019:1: (lv_description_9_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1020:3: lv_description_9_0= RULE_STRING
                    {
                    lv_description_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleRangeRule2147); 

                    			newLeafNode(lv_description_9_0, grammarAccess.getRangeRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_9_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1036:4: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==18) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1036:6: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_18_in_ruleRangeRule2167); 

                        	newLeafNode(otherlv_10, grammarAccess.getRangeRuleAccess().getMessageKeyword_7_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1040:1: ( (lv_message_11_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1041:1: (lv_message_11_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1041:1: (lv_message_11_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1042:3: lv_message_11_0= RULE_STRING
                    {
                    lv_message_11_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleRangeRule2184); 

                    			newLeafNode(lv_message_11_0, grammarAccess.getRangeRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"message",
                            		lv_message_11_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,22,FOLLOW_22_in_ruleRangeRule2203); 

                	newLeafNode(otherlv_12, grammarAccess.getRangeRuleAccess().getRangeKeyword_8());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1062:1: ( ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..' )?
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
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1062:2: ( (lv_min_13_0= RULE_INT ) ) otherlv_14= '..'
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1062:2: ( (lv_min_13_0= RULE_INT ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1063:1: (lv_min_13_0= RULE_INT )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1063:1: (lv_min_13_0= RULE_INT )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1064:3: lv_min_13_0= RULE_INT
                    {
                    lv_min_13_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleRangeRule2221); 

                    			newLeafNode(lv_min_13_0, grammarAccess.getRangeRuleAccess().getMinINTTerminalRuleCall_9_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getRangeRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"min",
                            		lv_min_13_0, 
                            		"INT");
                    	    

                    }


                    }

                    otherlv_14=(Token)match(input,21,FOLLOW_21_in_ruleRangeRule2238); 

                        	newLeafNode(otherlv_14, grammarAccess.getRangeRuleAccess().getFullStopFullStopKeyword_9_1());
                        

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1084:3: ( (lv_max_15_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1085:1: (lv_max_15_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1085:1: (lv_max_15_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1086:3: lv_max_15_0= RULE_INT
            {
            lv_max_15_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleRangeRule2257); 

            			newLeafNode(lv_max_15_0, grammarAccess.getRangeRuleAccess().getMaxINTTerminalRuleCall_10_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getRangeRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"max",
                    		lv_max_15_0, 
                    		"INT");
            	    

            }


            }

            otherlv_16=(Token)match(input,19,FOLLOW_19_in_ruleRangeRule2274); 

                	newLeafNode(otherlv_16, grammarAccess.getRangeRuleAccess().getContextKeyword_11());
                
            otherlv_17=(Token)match(input,15,FOLLOW_15_in_ruleRangeRule2286); 

                	newLeafNode(otherlv_17, grammarAccess.getRangeRuleAccess().getLeftCurlyBracketKeyword_12());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1110:1: ( (lv_contexts_18_0= ruleSimpleContext ) )+
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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1111:1: (lv_contexts_18_0= ruleSimpleContext )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1111:1: (lv_contexts_18_0= ruleSimpleContext )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1112:3: lv_contexts_18_0= ruleSimpleContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getRangeRuleAccess().getContextsSimpleContextParserRuleCall_13_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSimpleContext_in_ruleRangeRule2307);
            	    lv_contexts_18_0=ruleSimpleContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getRangeRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_18_0, 
            	            		"SimpleContext");
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

            otherlv_19=(Token)match(input,16,FOLLOW_16_in_ruleRangeRule2320); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1140:1: entryRuleUniqueRule returns [EObject current=null] : iv_ruleUniqueRule= ruleUniqueRule EOF ;
    public final EObject entryRuleUniqueRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUniqueRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1141:2: (iv_ruleUniqueRule= ruleUniqueRule EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1142:2: iv_ruleUniqueRule= ruleUniqueRule EOF
            {
             newCompositeNode(grammarAccess.getUniqueRuleRule()); 
            pushFollow(FOLLOW_ruleUniqueRule_in_entryRuleUniqueRule2356);
            iv_ruleUniqueRule=ruleUniqueRule();

            state._fsp--;

             current =iv_ruleUniqueRule; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUniqueRule2366); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1149:1: ruleUniqueRule returns [EObject current=null] : ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1152:28: ( ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1153:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1153:1: ( ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1153:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) ) otherlv_3= 'unique' ( (lv_severity_4_0= ruleSeverityKind ) ) ( (lv_name_5_0= RULE_ID ) ) otherlv_6= 'label' ( (lv_label_7_0= RULE_STRING ) ) (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )? (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )? otherlv_12= 'context' otherlv_13= '{' ( (lv_contexts_14_0= ruleDuplicateContext ) )+ otherlv_15= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1153:2: ( ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1155:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1155:1: ( ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1156:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            {
             
            	  getUnorderedGroupHelper().enter(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0());
            	
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1159:2: ( ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1160:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1160:3: ( ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) ) | ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) ) )*
            loop20:
            do {
                int alt20=3;
                int LA20_0 = input.LA(1);

                if ( LA20_0 ==17 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
                    alt20=1;
                }
                else if ( LA20_0 >=31 && LA20_0<=33 && getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
                    alt20=2;
                }


                switch (alt20) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1162:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1162:4: ({...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1163:5: {...}? => ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1163:107: ( ({...}? => ( (lv_optional_1_0= 'optional' ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1164:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 0);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1167:6: ({...}? => ( (lv_optional_1_0= 'optional' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1167:7: {...}? => ( (lv_optional_1_0= 'optional' ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1167:16: ( (lv_optional_1_0= 'optional' ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1168:1: (lv_optional_1_0= 'optional' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1168:1: (lv_optional_1_0= 'optional' )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1169:3: lv_optional_1_0= 'optional'
            	    {
            	    lv_optional_1_0=(Token)match(input,17,FOLLOW_17_in_ruleUniqueRule2454); 

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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1189:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1189:4: ({...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1190:5: {...}? => ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    {
            	    if ( ! getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "getUnorderedGroupHelper().canSelect(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1)");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1190:107: ( ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1191:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    {
            	     
            	    	 				  getUnorderedGroupHelper().select(grammarAccess.getUniqueRuleAccess().getUnorderedGroup_0(), 1);
            	    	 				
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1194:6: ({...}? => ( (lv_checkKind_2_0= ruleCheckKind ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1194:7: {...}? => ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    {
            	    if ( !((true)) ) {
            	        throw new FailedPredicateException(input, "ruleUniqueRule", "true");
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1194:16: ( (lv_checkKind_2_0= ruleCheckKind ) )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1195:1: (lv_checkKind_2_0= ruleCheckKind )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1195:1: (lv_checkKind_2_0= ruleCheckKind )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1196:3: lv_checkKind_2_0= ruleCheckKind
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getUniqueRuleAccess().getCheckKindCheckKindEnumRuleCall_0_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleCheckKind_in_ruleUniqueRule2542);
            	    lv_checkKind_2_0=ruleCheckKind();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getUniqueRuleRule());
            	    	        }
            	           		set(
            	           			current, 
            	           			"checkKind",
            	            		lv_checkKind_2_0, 
            	            		"CheckKind");
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

            otherlv_3=(Token)match(input,23,FOLLOW_23_in_ruleUniqueRule2594); 

                	newLeafNode(otherlv_3, grammarAccess.getUniqueRuleAccess().getUniqueKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1230:1: ( (lv_severity_4_0= ruleSeverityKind ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1231:1: (lv_severity_4_0= ruleSeverityKind )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1231:1: (lv_severity_4_0= ruleSeverityKind )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1232:3: lv_severity_4_0= ruleSeverityKind
            {
             
            	        newCompositeNode(grammarAccess.getUniqueRuleAccess().getSeveritySeverityKindEnumRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleSeverityKind_in_ruleUniqueRule2615);
            lv_severity_4_0=ruleSeverityKind();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getUniqueRuleRule());
            	        }
                   		set(
                   			current, 
                   			"severity",
                    		lv_severity_4_0, 
                    		"SeverityKind");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1248:2: ( (lv_name_5_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1249:1: (lv_name_5_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1249:1: (lv_name_5_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1250:3: lv_name_5_0= RULE_ID
            {
            lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleUniqueRule2632); 

            			newLeafNode(lv_name_5_0, grammarAccess.getUniqueRuleAccess().getNameIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUniqueRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_5_0, 
                    		"ID");
            	    

            }


            }

            otherlv_6=(Token)match(input,13,FOLLOW_13_in_ruleUniqueRule2649); 

                	newLeafNode(otherlv_6, grammarAccess.getUniqueRuleAccess().getLabelKeyword_4());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1270:1: ( (lv_label_7_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1271:1: (lv_label_7_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1271:1: (lv_label_7_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1272:3: lv_label_7_0= RULE_STRING
            {
            lv_label_7_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleUniqueRule2666); 

            			newLeafNode(lv_label_7_0, grammarAccess.getUniqueRuleAccess().getLabelSTRINGTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getUniqueRuleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_7_0, 
                    		"STRING");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1288:2: (otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==14) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1288:4: otherlv_8= 'description' ( (lv_description_9_0= RULE_STRING ) )
                    {
                    otherlv_8=(Token)match(input,14,FOLLOW_14_in_ruleUniqueRule2684); 

                        	newLeafNode(otherlv_8, grammarAccess.getUniqueRuleAccess().getDescriptionKeyword_6_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1292:1: ( (lv_description_9_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1293:1: (lv_description_9_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1293:1: (lv_description_9_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1294:3: lv_description_9_0= RULE_STRING
                    {
                    lv_description_9_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleUniqueRule2701); 

                    			newLeafNode(lv_description_9_0, grammarAccess.getUniqueRuleAccess().getDescriptionSTRINGTerminalRuleCall_6_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getUniqueRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"description",
                            		lv_description_9_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1310:4: (otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==18) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1310:6: otherlv_10= 'message' ( (lv_message_11_0= RULE_STRING ) )
                    {
                    otherlv_10=(Token)match(input,18,FOLLOW_18_in_ruleUniqueRule2721); 

                        	newLeafNode(otherlv_10, grammarAccess.getUniqueRuleAccess().getMessageKeyword_7_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1314:1: ( (lv_message_11_0= RULE_STRING ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1315:1: (lv_message_11_0= RULE_STRING )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1315:1: (lv_message_11_0= RULE_STRING )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1316:3: lv_message_11_0= RULE_STRING
                    {
                    lv_message_11_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleUniqueRule2738); 

                    			newLeafNode(lv_message_11_0, grammarAccess.getUniqueRuleAccess().getMessageSTRINGTerminalRuleCall_7_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getUniqueRuleRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"message",
                            		lv_message_11_0, 
                            		"STRING");
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_12=(Token)match(input,19,FOLLOW_19_in_ruleUniqueRule2757); 

                	newLeafNode(otherlv_12, grammarAccess.getUniqueRuleAccess().getContextKeyword_8());
                
            otherlv_13=(Token)match(input,15,FOLLOW_15_in_ruleUniqueRule2769); 

                	newLeafNode(otherlv_13, grammarAccess.getUniqueRuleAccess().getLeftCurlyBracketKeyword_9());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1340:1: ( (lv_contexts_14_0= ruleDuplicateContext ) )+
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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1341:1: (lv_contexts_14_0= ruleDuplicateContext )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1341:1: (lv_contexts_14_0= ruleDuplicateContext )
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1342:3: lv_contexts_14_0= ruleDuplicateContext
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getUniqueRuleAccess().getContextsDuplicateContextParserRuleCall_10_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleDuplicateContext_in_ruleUniqueRule2790);
            	    lv_contexts_14_0=ruleDuplicateContext();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getUniqueRuleRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"contexts",
            	            		lv_contexts_14_0, 
            	            		"DuplicateContext");
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

            otherlv_15=(Token)match(input,16,FOLLOW_16_in_ruleUniqueRule2803); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1372:1: entryRuleSimpleContext returns [EObject current=null] : iv_ruleSimpleContext= ruleSimpleContext EOF ;
    public final EObject entryRuleSimpleContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleContext = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1373:2: (iv_ruleSimpleContext= ruleSimpleContext EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1374:2: iv_ruleSimpleContext= ruleSimpleContext EOF
            {
             newCompositeNode(grammarAccess.getSimpleContextRule()); 
            pushFollow(FOLLOW_ruleSimpleContext_in_entryRuleSimpleContext2841);
            iv_ruleSimpleContext=ruleSimpleContext();

            state._fsp--;

             current =iv_ruleSimpleContext; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleContext2851); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1381:1: ruleSimpleContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' ) ;
    public final EObject ruleSimpleContext() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1384:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1385:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1385:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1385:2: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1385:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1386:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1386:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1387:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getSimpleContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getSimpleContextAccess().getContextTypeEClassCrossReference_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleSimpleContext2899);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1400:2: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==24) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1400:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleSimpleContext2912); 

                        	newLeafNode(otherlv_1, grammarAccess.getSimpleContextAccess().getNumberSignKeyword_1_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1404:1: ( (otherlv_2= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1405:1: (otherlv_2= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1405:1: (otherlv_2= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1406:3: otherlv_2= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getSimpleContextRule());
                    	        }
                            
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSimpleContext2932); 

                    		newLeafNode(otherlv_2, grammarAccess.getSimpleContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
                    	

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,25,FOLLOW_25_in_ruleSimpleContext2946); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1429:1: entryRuleDuplicateContext returns [EObject current=null] : iv_ruleDuplicateContext= ruleDuplicateContext EOF ;
    public final EObject entryRuleDuplicateContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDuplicateContext = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1430:2: (iv_ruleDuplicateContext= ruleDuplicateContext EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1431:2: iv_ruleDuplicateContext= ruleDuplicateContext EOF
            {
             newCompositeNode(grammarAccess.getDuplicateContextRule()); 
            pushFollow(FOLLOW_ruleDuplicateContext_in_entryRuleDuplicateContext2982);
            iv_ruleDuplicateContext=ruleDuplicateContext();

            state._fsp--;

             current =iv_ruleDuplicateContext; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDuplicateContext2992); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1438:1: ruleDuplicateContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1441:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1442:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1442:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1442:2: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? otherlv_3= 'marker' ( ( ruleQualifiedID ) ) otherlv_5= '#' ( (otherlv_6= RULE_ID ) )? otherlv_7= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1442:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1443:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1443:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1444:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDuplicateContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getDuplicateContextAccess().getContextTypeEClassCrossReference_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleDuplicateContext3040);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1457:2: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==24) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1457:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleDuplicateContext3053); 

                        	newLeafNode(otherlv_1, grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_1_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1461:1: ( (otherlv_2= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1462:1: (otherlv_2= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1462:1: (otherlv_2= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1463:3: otherlv_2= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getDuplicateContextRule());
                    	        }
                            
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDuplicateContext3073); 

                    		newLeafNode(otherlv_2, grammarAccess.getDuplicateContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
                    	

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,26,FOLLOW_26_in_ruleDuplicateContext3087); 

                	newLeafNode(otherlv_3, grammarAccess.getDuplicateContextAccess().getMarkerKeyword_2());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1478:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1479:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1479:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1480:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDuplicateContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getDuplicateContextAccess().getMarkerTypeEClassCrossReference_3_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleDuplicateContext3110);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,24,FOLLOW_24_in_ruleDuplicateContext3122); 

                	newLeafNode(otherlv_5, grammarAccess.getDuplicateContextAccess().getNumberSignKeyword_4());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1497:1: ( (otherlv_6= RULE_ID ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==RULE_ID) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1498:1: (otherlv_6= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1498:1: (otherlv_6= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1499:3: otherlv_6= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getDuplicateContextRule());
                    	        }
                            
                    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDuplicateContext3142); 

                    		newLeafNode(otherlv_6, grammarAccess.getDuplicateContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_5_0()); 
                    	

                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,25,FOLLOW_25_in_ruleDuplicateContext3155); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1522:1: entryRuleNativeContext returns [EObject current=null] : iv_ruleNativeContext= ruleNativeContext EOF ;
    public final EObject entryRuleNativeContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNativeContext = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1523:2: (iv_ruleNativeContext= ruleNativeContext EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1524:2: iv_ruleNativeContext= ruleNativeContext EOF
            {
             newCompositeNode(grammarAccess.getNativeContextRule()); 
            pushFollow(FOLLOW_ruleNativeContext_in_entryRuleNativeContext3191);
            iv_ruleNativeContext=ruleNativeContext();

            state._fsp--;

             current =iv_ruleNativeContext; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNativeContext3201); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1531:1: ruleNativeContext returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1534:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1535:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1535:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1535:2: ( ( ruleQualifiedID ) ) (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )? ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )? (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )? (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )? otherlv_13= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1535:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1536:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1536:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1537:3: ruleQualifiedID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getNativeContextRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getNativeContextAccess().getContextTypeEClassCrossReference_0_0()); 
            	    
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleNativeContext3249);
            ruleQualifiedID();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1550:2: (otherlv_1= '#' ( (otherlv_2= RULE_ID ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==24) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1550:4: otherlv_1= '#' ( (otherlv_2= RULE_ID ) )
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleNativeContext3262); 

                        	newLeafNode(otherlv_1, grammarAccess.getNativeContextAccess().getNumberSignKeyword_1_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1554:1: ( (otherlv_2= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1555:1: (otherlv_2= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1555:1: (otherlv_2= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1556:3: otherlv_2= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                            
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNativeContext3282); 

                    		newLeafNode(otherlv_2, grammarAccess.getNativeContextAccess().getContextFeatureEStructuralFeatureCrossReference_1_1_0()); 
                    	

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1567:4: ( ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==27) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1567:5: ( (lv_named_3_0= 'as' ) ) ( (lv_givenName_4_0= RULE_ID ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1567:5: ( (lv_named_3_0= 'as' ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1568:1: (lv_named_3_0= 'as' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1568:1: (lv_named_3_0= 'as' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1569:3: lv_named_3_0= 'as'
                    {
                    lv_named_3_0=(Token)match(input,27,FOLLOW_27_in_ruleNativeContext3303); 

                            newLeafNode(lv_named_3_0, grammarAccess.getNativeContextAccess().getNamedAsKeyword_2_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                           		setWithLastConsumed(current, "named", true, "as");
                    	    

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1582:2: ( (lv_givenName_4_0= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1583:1: (lv_givenName_4_0= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1583:1: (lv_givenName_4_0= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1584:3: lv_givenName_4_0= RULE_ID
                    {
                    lv_givenName_4_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNativeContext3333); 

                    			newLeafNode(lv_givenName_4_0, grammarAccess.getNativeContextAccess().getGivenNameIDTerminalRuleCall_2_1_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"givenName",
                            		lv_givenName_4_0, 
                            		"ID");
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1600:4: (otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==26) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1600:6: otherlv_5= 'marker' ( ( ruleQualifiedID ) ) (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) )
                    {
                    otherlv_5=(Token)match(input,26,FOLLOW_26_in_ruleNativeContext3353); 

                        	newLeafNode(otherlv_5, grammarAccess.getNativeContextAccess().getMarkerKeyword_3_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1604:1: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1605:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1605:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1606:3: ruleQualifiedID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getNativeContextAccess().getMarkerTypeEClassCrossReference_3_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleNativeContext3376);
                    ruleQualifiedID();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1619:2: (otherlv_7= '#' ( (otherlv_8= RULE_ID ) ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1619:4: otherlv_7= '#' ( (otherlv_8= RULE_ID ) )
                    {
                    otherlv_7=(Token)match(input,24,FOLLOW_24_in_ruleNativeContext3389); 

                        	newLeafNode(otherlv_7, grammarAccess.getNativeContextAccess().getNumberSignKeyword_3_2_0());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1623:1: ( (otherlv_8= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1624:1: (otherlv_8= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1624:1: (otherlv_8= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1625:3: otherlv_8= RULE_ID
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getNativeContextRule());
                    	        }
                            
                    otherlv_8=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleNativeContext3409); 

                    		newLeafNode(otherlv_8, grammarAccess.getNativeContextAccess().getMarkerFeatureEStructuralFeatureCrossReference_3_2_1_0()); 
                    	

                    }


                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1636:5: (otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==28) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1636:7: otherlv_9= 'quickfixes' otherlv_10= '{' ( (lv_quickFixes_11_0= ruleQuickFix ) )+ otherlv_12= '}'
                    {
                    otherlv_9=(Token)match(input,28,FOLLOW_28_in_ruleNativeContext3425); 

                        	newLeafNode(otherlv_9, grammarAccess.getNativeContextAccess().getQuickfixesKeyword_4_0());
                        
                    otherlv_10=(Token)match(input,15,FOLLOW_15_in_ruleNativeContext3437); 

                        	newLeafNode(otherlv_10, grammarAccess.getNativeContextAccess().getLeftCurlyBracketKeyword_4_1());
                        
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1644:1: ( (lv_quickFixes_11_0= ruleQuickFix ) )+
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
                    	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1645:1: (lv_quickFixes_11_0= ruleQuickFix )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1645:1: (lv_quickFixes_11_0= ruleQuickFix )
                    	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1646:3: lv_quickFixes_11_0= ruleQuickFix
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getNativeContextAccess().getQuickFixesQuickFixParserRuleCall_4_2_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleQuickFix_in_ruleNativeContext3458);
                    	    lv_quickFixes_11_0=ruleQuickFix();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getNativeContextRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"quickFixes",
                    	            		lv_quickFixes_11_0, 
                    	            		"QuickFix");
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

                    otherlv_12=(Token)match(input,16,FOLLOW_16_in_ruleNativeContext3471); 

                        	newLeafNode(otherlv_12, grammarAccess.getNativeContextAccess().getRightCurlyBracketKeyword_4_3());
                        

                    }
                    break;

            }

            otherlv_13=(Token)match(input,25,FOLLOW_25_in_ruleNativeContext3485); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1678:1: entryRuleQuickFix returns [EObject current=null] : iv_ruleQuickFix= ruleQuickFix EOF ;
    public final EObject entryRuleQuickFix() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQuickFix = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1679:2: (iv_ruleQuickFix= ruleQuickFix EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1680:2: iv_ruleQuickFix= ruleQuickFix EOF
            {
             newCompositeNode(grammarAccess.getQuickFixRule()); 
            pushFollow(FOLLOW_ruleQuickFix_in_entryRuleQuickFix3521);
            iv_ruleQuickFix=ruleQuickFix();

            state._fsp--;

             current =iv_ruleQuickFix; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQuickFix3531); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1687:1: ruleQuickFix returns [EObject current=null] : ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1690:28: ( ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1691:1: ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1691:1: ( ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';' )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1691:2: ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )? otherlv_1= 'fix' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'label' ( (lv_label_4_0= RULE_STRING ) ) otherlv_5= 'message' ( (lv_message_6_0= RULE_STRING ) ) otherlv_7= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1691:2: ( (lv_quickFixKind_0_0= ruleQuickFixKind ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>=36 && LA32_0<=37)) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1692:1: (lv_quickFixKind_0_0= ruleQuickFixKind )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1692:1: (lv_quickFixKind_0_0= ruleQuickFixKind )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1693:3: lv_quickFixKind_0_0= ruleQuickFixKind
                    {
                     
                    	        newCompositeNode(grammarAccess.getQuickFixAccess().getQuickFixKindQuickFixKindEnumRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQuickFixKind_in_ruleQuickFix3577);
                    lv_quickFixKind_0_0=ruleQuickFixKind();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getQuickFixRule());
                    	        }
                           		set(
                           			current, 
                           			"quickFixKind",
                            		lv_quickFixKind_0_0, 
                            		"QuickFixKind");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,29,FOLLOW_29_in_ruleQuickFix3590); 

                	newLeafNode(otherlv_1, grammarAccess.getQuickFixAccess().getFixKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1713:1: ( (lv_name_2_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1714:1: (lv_name_2_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1714:1: (lv_name_2_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1715:3: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQuickFix3607); 

            			newLeafNode(lv_name_2_0, grammarAccess.getQuickFixAccess().getNameIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getQuickFixRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleQuickFix3624); 

                	newLeafNode(otherlv_3, grammarAccess.getQuickFixAccess().getLabelKeyword_3());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1735:1: ( (lv_label_4_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1736:1: (lv_label_4_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1736:1: (lv_label_4_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1737:3: lv_label_4_0= RULE_STRING
            {
            lv_label_4_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleQuickFix3641); 

            			newLeafNode(lv_label_4_0, grammarAccess.getQuickFixAccess().getLabelSTRINGTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getQuickFixRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"label",
                    		lv_label_4_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_5=(Token)match(input,18,FOLLOW_18_in_ruleQuickFix3658); 

                	newLeafNode(otherlv_5, grammarAccess.getQuickFixAccess().getMessageKeyword_5());
                
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1757:1: ( (lv_message_6_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1758:1: (lv_message_6_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1758:1: (lv_message_6_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1759:3: lv_message_6_0= RULE_STRING
            {
            lv_message_6_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleQuickFix3675); 

            			newLeafNode(lv_message_6_0, grammarAccess.getQuickFixAccess().getMessageSTRINGTerminalRuleCall_6_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getQuickFixRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"message",
                    		lv_message_6_0, 
                    		"STRING");
            	    

            }


            }

            otherlv_7=(Token)match(input,25,FOLLOW_25_in_ruleQuickFix3692); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1787:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1788:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1789:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
             newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3729);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;

             current =iv_ruleQualifiedID.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID3740); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1796:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1799:28: ( ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1800:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1800:1: ( (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1800:2: (this_ID_0= RULE_ID kw= '::' )* this_ID_2= RULE_ID
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1800:2: (this_ID_0= RULE_ID kw= '::' )*
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
            	    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1800:7: this_ID_0= RULE_ID kw= '::'
            	    {
            	    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID3781); 

            	    		current.merge(this_ID_0);
            	        
            	     
            	        newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0_0()); 
            	        
            	    kw=(Token)match(input,30,FOLLOW_30_in_ruleQualifiedID3799); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_0_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID3816); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1828:1: ruleCheckKind returns [Enumerator current=null] : ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) ) ;
    public final Enumerator ruleCheckKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1830:28: ( ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1831:1: ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1831:1: ( (enumLiteral_0= 'fast' ) | (enumLiteral_1= 'normal' ) | (enumLiteral_2= 'expensive' ) )
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
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1831:2: (enumLiteral_0= 'fast' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1831:2: (enumLiteral_0= 'fast' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1831:4: enumLiteral_0= 'fast'
                    {
                    enumLiteral_0=(Token)match(input,31,FOLLOW_31_in_ruleCheckKind3875); 

                            current = grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getCheckKindAccess().getFastEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1837:6: (enumLiteral_1= 'normal' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1837:6: (enumLiteral_1= 'normal' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1837:8: enumLiteral_1= 'normal'
                    {
                    enumLiteral_1=(Token)match(input,32,FOLLOW_32_in_ruleCheckKind3892); 

                            current = grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getCheckKindAccess().getNormalEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1843:6: (enumLiteral_2= 'expensive' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1843:6: (enumLiteral_2= 'expensive' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1843:8: enumLiteral_2= 'expensive'
                    {
                    enumLiteral_2=(Token)match(input,33,FOLLOW_33_in_ruleCheckKind3909); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1853:1: ruleSeverityKind returns [Enumerator current=null] : ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) ) ;
    public final Enumerator ruleSeverityKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1855:28: ( ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1856:1: ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1856:1: ( (enumLiteral_0= 'error' ) | (enumLiteral_1= 'warning' ) )
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
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1856:2: (enumLiteral_0= 'error' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1856:2: (enumLiteral_0= 'error' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1856:4: enumLiteral_0= 'error'
                    {
                    enumLiteral_0=(Token)match(input,34,FOLLOW_34_in_ruleSeverityKind3954); 

                            current = grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1862:6: (enumLiteral_1= 'warning' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1862:6: (enumLiteral_1= 'warning' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1862:8: enumLiteral_1= 'warning'
                    {
                    enumLiteral_1=(Token)match(input,35,FOLLOW_35_in_ruleSeverityKind3971); 

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
    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1872:1: ruleQuickFixKind returns [Enumerator current=null] : ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) ) ;
    public final Enumerator ruleQuickFixKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1874:28: ( ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) ) )
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1875:1: ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1875:1: ( (enumLiteral_0= 'semantic' ) | (enumLiteral_1= 'textual' ) )
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
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1875:2: (enumLiteral_0= 'semantic' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1875:2: (enumLiteral_0= 'semantic' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1875:4: enumLiteral_0= 'semantic'
                    {
                    enumLiteral_0=(Token)match(input,36,FOLLOW_36_in_ruleQuickFixKind4016); 

                            current = grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getQuickFixKindAccess().getSemanticEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1881:6: (enumLiteral_1= 'textual' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1881:6: (enumLiteral_1= 'textual' )
                    // ../com.avaloq.tools.ddk.xtext.valid/src-gen/com/avaloq/tools/ddk/xtext/valid/parser/antlr/internal/InternalValid.g:1881:8: enumLiteral_1= 'textual'
                    {
                    enumLiteral_1=(Token)match(input,37,FOLLOW_37_in_ruleQuickFixKind4033); 

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
    static final String DFA5_eotS =
        "\7\uffff";
    static final String DFA5_eofS =
        "\7\uffff";
    static final String DFA5_minS =
        "\5\21\2\uffff";
    static final String DFA5_maxS =
        "\5\43\2\uffff";
    static final String DFA5_acceptS =
        "\5\uffff\1\1\1\2";
    static final String DFA5_specialS =
        "\7\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "\1\1\2\uffff\1\6\1\uffff\2\6\7\uffff\1\2\1\3\1\4\2\5",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "289:1: (this_NativeRule_0= ruleNativeRule | this_PredefinedRule_1= rulePredefinedRule )";
        }
    }
    static final String DFA6_eotS =
        "\10\uffff";
    static final String DFA6_eofS =
        "\10\uffff";
    static final String DFA6_minS =
        "\5\21\3\uffff";
    static final String DFA6_maxS =
        "\5\41\3\uffff";
    static final String DFA6_acceptS =
        "\5\uffff\1\1\1\2\1\3";
    static final String DFA6_specialS =
        "\10\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "\1\1\2\uffff\1\5\1\uffff\1\6\1\7\7\uffff\1\2\1\3\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "329:1: (this_SizeRule_0= ruleSizeRule | this_RangeRule_1= ruleRangeRule | this_UniqueRule_2= ruleUniqueRule )";
        }
    }
 

    public static final BitSet FOLLOW_ruleValidModel_in_entryRuleValidModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleValidModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleValidModel131 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_ruleCategory_in_ruleValidModel153 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport190 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleImport237 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCategory_in_entryRuleCategory293 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCategory303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleCategory340 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleCategory357 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleCategory374 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleCategory391 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_14_in_ruleCategory409 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleCategory426 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleCategory445 = new BitSet(new long[]{0x0000000F80D30000L});
    public static final BitSet FOLLOW_ruleRule_in_ruleCategory466 = new BitSet(new long[]{0x0000000F80D30000L});
    public static final BitSet FOLLOW_16_in_ruleCategory479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRule_in_entryRuleRule515 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRule525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNativeRule_in_ruleRule572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePredefinedRule_in_ruleRule599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePredefinedRule_in_entryRulePredefinedRule634 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePredefinedRule644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSizeRule_in_rulePredefinedRule691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeRule_in_rulePredefinedRule718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUniqueRule_in_rulePredefinedRule745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNativeRule_in_entryRuleNativeRule780 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNativeRule790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleNativeRule878 = new BitSet(new long[]{0x0000000F80020000L});
    public static final BitSet FOLLOW_ruleCheckKind_in_ruleNativeRule966 = new BitSet(new long[]{0x0000000F80020000L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_ruleNativeRule1027 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNativeRule1044 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleNativeRule1061 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNativeRule1078 = new BitSet(new long[]{0x0000000000044000L});
    public static final BitSet FOLLOW_14_in_ruleNativeRule1096 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNativeRule1113 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleNativeRule1132 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleNativeRule1149 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleNativeRule1166 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleNativeRule1178 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleNativeContext_in_ruleNativeRule1199 = new BitSet(new long[]{0x0000000000010020L});
    public static final BitSet FOLLOW_16_in_ruleNativeRule1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSizeRule_in_entryRuleSizeRule1248 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSizeRule1258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleSizeRule1346 = new BitSet(new long[]{0x0000000380120000L});
    public static final BitSet FOLLOW_ruleCheckKind_in_ruleSizeRule1434 = new BitSet(new long[]{0x0000000380120000L});
    public static final BitSet FOLLOW_20_in_ruleSizeRule1486 = new BitSet(new long[]{0x0000000F80020000L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_ruleSizeRule1507 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSizeRule1524 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleSizeRule1541 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSizeRule1558 = new BitSet(new long[]{0x0000000000144000L});
    public static final BitSet FOLLOW_14_in_ruleSizeRule1576 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSizeRule1593 = new BitSet(new long[]{0x0000000000140000L});
    public static final BitSet FOLLOW_18_in_ruleSizeRule1613 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSizeRule1630 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleSizeRule1649 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleSizeRule1667 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleSizeRule1684 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleSizeRule1703 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleSizeRule1720 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleSizeRule1732 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleSimpleContext_in_ruleSizeRule1753 = new BitSet(new long[]{0x0000000000010020L});
    public static final BitSet FOLLOW_16_in_ruleSizeRule1766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRangeRule_in_entryRuleRangeRule1802 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRangeRule1812 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleRangeRule1900 = new BitSet(new long[]{0x0000000380420000L});
    public static final BitSet FOLLOW_ruleCheckKind_in_ruleRangeRule1988 = new BitSet(new long[]{0x0000000380420000L});
    public static final BitSet FOLLOW_22_in_ruleRangeRule2040 = new BitSet(new long[]{0x0000000F80020000L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_ruleRangeRule2061 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleRangeRule2078 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleRangeRule2095 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleRangeRule2112 = new BitSet(new long[]{0x0000000000444000L});
    public static final BitSet FOLLOW_14_in_ruleRangeRule2130 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleRangeRule2147 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_18_in_ruleRangeRule2167 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleRangeRule2184 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleRangeRule2203 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleRangeRule2221 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleRangeRule2238 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleRangeRule2257 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleRangeRule2274 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleRangeRule2286 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleSimpleContext_in_ruleRangeRule2307 = new BitSet(new long[]{0x0000000000010020L});
    public static final BitSet FOLLOW_16_in_ruleRangeRule2320 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUniqueRule_in_entryRuleUniqueRule2356 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUniqueRule2366 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleUniqueRule2454 = new BitSet(new long[]{0x0000000380820000L});
    public static final BitSet FOLLOW_ruleCheckKind_in_ruleUniqueRule2542 = new BitSet(new long[]{0x0000000380820000L});
    public static final BitSet FOLLOW_23_in_ruleUniqueRule2594 = new BitSet(new long[]{0x0000000F80020000L});
    public static final BitSet FOLLOW_ruleSeverityKind_in_ruleUniqueRule2615 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleUniqueRule2632 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleUniqueRule2649 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleUniqueRule2666 = new BitSet(new long[]{0x00000000000C4000L});
    public static final BitSet FOLLOW_14_in_ruleUniqueRule2684 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleUniqueRule2701 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_18_in_ruleUniqueRule2721 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleUniqueRule2738 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleUniqueRule2757 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleUniqueRule2769 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleDuplicateContext_in_ruleUniqueRule2790 = new BitSet(new long[]{0x0000000000010020L});
    public static final BitSet FOLLOW_16_in_ruleUniqueRule2803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleContext_in_entryRuleSimpleContext2841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleContext2851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleSimpleContext2899 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_24_in_ruleSimpleContext2912 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSimpleContext2932 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleSimpleContext2946 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDuplicateContext_in_entryRuleDuplicateContext2982 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDuplicateContext2992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleDuplicateContext3040 = new BitSet(new long[]{0x0000000005000000L});
    public static final BitSet FOLLOW_24_in_ruleDuplicateContext3053 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDuplicateContext3073 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleDuplicateContext3087 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleDuplicateContext3110 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleDuplicateContext3122 = new BitSet(new long[]{0x0000000002000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDuplicateContext3142 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleDuplicateContext3155 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNativeContext_in_entryRuleNativeContext3191 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNativeContext3201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleNativeContext3249 = new BitSet(new long[]{0x000000001F000000L});
    public static final BitSet FOLLOW_24_in_ruleNativeContext3262 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNativeContext3282 = new BitSet(new long[]{0x000000001E000000L});
    public static final BitSet FOLLOW_27_in_ruleNativeContext3303 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNativeContext3333 = new BitSet(new long[]{0x0000000016000000L});
    public static final BitSet FOLLOW_26_in_ruleNativeContext3353 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleNativeContext3376 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleNativeContext3389 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleNativeContext3409 = new BitSet(new long[]{0x0000000012000000L});
    public static final BitSet FOLLOW_28_in_ruleNativeContext3425 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleNativeContext3437 = new BitSet(new long[]{0x0000003020000000L});
    public static final BitSet FOLLOW_ruleQuickFix_in_ruleNativeContext3458 = new BitSet(new long[]{0x0000003020010000L});
    public static final BitSet FOLLOW_16_in_ruleNativeContext3471 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleNativeContext3485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuickFix_in_entryRuleQuickFix3521 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQuickFix3531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuickFixKind_in_ruleQuickFix3577 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleQuickFix3590 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQuickFix3607 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleQuickFix3624 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleQuickFix3641 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleQuickFix3658 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleQuickFix3675 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleQuickFix3692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3729 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID3740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID3781 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleQualifiedID3799 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID3816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleCheckKind3875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleCheckKind3892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleCheckKind3909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleSeverityKind3954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleSeverityKind3971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleQuickFixKind4016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleQuickFixKind4033 = new BitSet(new long[]{0x0000000000000002L});

}
