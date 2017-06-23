package com.avaloq.tools.ddk.xtext.scope.parser.antlr.internal; 

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
import com.avaloq.tools.ddk.xtext.scope.services.ScopeGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalScopeParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_INT", "RULE_REAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'scoping'", "'with'", "'import'", "'as'", "'extension'", "'case'", "'naming'", "'{'", "'}'", "'='", "';'", "'scope'", "'('", "')'", "'#'", "'context'", "'>>'", "'*'", "'['", "']'", "'|'", "'factory'", "'scopeof'", "','", "'find'", "'key'", "'prefix'", "'data'", "'domains'", "'export'", "'::'", "'.'", "'let'", "':'", "'->'", "'?'", "'if'", "'then'", "'else'", "'switch'", "'default'", "'||'", "'&&'", "'implies'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'/'", "'!'", "'typeSelect'", "'collect'", "'select'", "'selectFirst'", "'reject'", "'exists'", "'notExists'", "'sortBy'", "'forAll'", "'true'", "'false'", "'null'", "'GLOBALVAR'", "'new'", "'Collection'", "'List'", "'Set'", "'sensitive'", "'insensitive'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__55=55;
    public static final int T__12=12;
    public static final int T__56=56;
    public static final int T__13=13;
    public static final int T__57=57;
    public static final int T__14=14;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=7;
    public static final int RULE_REAL=6;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__67=67;
    public static final int T__24=24;
    public static final int T__68=68;
    public static final int T__25=25;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__77=77;
    public static final int T__34=34;
    public static final int T__78=78;
    public static final int T__35=35;
    public static final int T__79=79;
    public static final int T__36=36;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__74=74;
    public static final int T__31=31;
    public static final int T__75=75;
    public static final int T__32=32;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__84=84;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalScopeParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalScopeParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalScopeParser.tokenNames; }
    public String getGrammarFileName() { return "../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     
     	private ScopeGrammarAccess grammarAccess;
     	
        public InternalScopeParser(TokenStream input, ScopeGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "ScopeModel";	
       	}
       	
       	@Override
       	protected ScopeGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleScopeModel"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:74:1: entryRuleScopeModel returns [EObject current=null] : iv_ruleScopeModel= ruleScopeModel EOF ;
    public final EObject entryRuleScopeModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeModel = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:75:2: (iv_ruleScopeModel= ruleScopeModel EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:76:2: iv_ruleScopeModel= ruleScopeModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeModelRule()); 
            }
            pushFollow(FOLLOW_ruleScopeModel_in_entryRuleScopeModel81);
            iv_ruleScopeModel=ruleScopeModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeModel; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeModel91); if (state.failed) return current;

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
    // $ANTLR end "entryRuleScopeModel"


    // $ANTLR start "ruleScopeModel"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:83:1: ruleScopeModel returns [EObject current=null] : (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_naming_6_0= ruleNamingSection ) )? ( (lv_scopes_7_0= ruleScopeDefinition ) )* ) ;
    public final EObject ruleScopeModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_imports_4_0 = null;

        EObject lv_extensions_5_0 = null;

        EObject lv_naming_6_0 = null;

        EObject lv_scopes_7_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:86:28: ( (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_naming_6_0= ruleNamingSection ) )? ( (lv_scopes_7_0= ruleScopeDefinition ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:87:1: (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_naming_6_0= ruleNamingSection ) )? ( (lv_scopes_7_0= ruleScopeDefinition ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:87:1: (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_naming_6_0= ruleNamingSection ) )? ( (lv_scopes_7_0= ruleScopeDefinition ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:87:3: otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_naming_6_0= ruleNamingSection ) )? ( (lv_scopes_7_0= ruleScopeDefinition ) )*
            {
            otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleScopeModel128); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeModelAccess().getScopingKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:91:1: ( (lv_name_1_0= ruleDottedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:92:1: (lv_name_1_0= ruleDottedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:92:1: (lv_name_1_0= ruleDottedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:93:3: lv_name_1_0= ruleDottedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeModelAccess().getNameDottedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleDottedID_in_ruleScopeModel149);
            lv_name_1_0=ruleDottedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"DottedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:109:2: (otherlv_2= 'with' ( ( ruleDottedID ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:109:4: otherlv_2= 'with' ( ( ruleDottedID ) )
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleScopeModel162); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getScopeModelAccess().getWithKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:113:1: ( ( ruleDottedID ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:114:1: ( ruleDottedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:114:1: ( ruleDottedID )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:115:3: ruleDottedID
                    {
                    if ( state.backtracking==0 ) {
                       
                      		  /* */ 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeModelRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeModelAccess().getIncludedScopesScopeModelCrossReference_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleDottedID_in_ruleScopeModel189);
                    ruleDottedID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:131:4: ( (lv_imports_4_0= ruleImport ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==14) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:132:1: (lv_imports_4_0= ruleImport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:132:1: (lv_imports_4_0= ruleImport )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:133:3: lv_imports_4_0= ruleImport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getImportsImportParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImport_in_ruleScopeModel212);
            	    lv_imports_4_0=ruleImport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"imports",
            	              		lv_imports_4_0, 
            	              		"Import");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:149:3: ( (lv_extensions_5_0= ruleExtension ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:150:1: (lv_extensions_5_0= ruleExtension )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:150:1: (lv_extensions_5_0= ruleExtension )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:151:3: lv_extensions_5_0= ruleExtension
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getExtensionsExtensionParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExtension_in_ruleScopeModel234);
            	    lv_extensions_5_0=ruleExtension();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"extensions",
            	              		lv_extensions_5_0, 
            	              		"Extension");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:167:3: ( (lv_naming_6_0= ruleNamingSection ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=17 && LA4_0<=18)) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:168:1: (lv_naming_6_0= ruleNamingSection )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:168:1: (lv_naming_6_0= ruleNamingSection )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:169:3: lv_naming_6_0= ruleNamingSection
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeModelAccess().getNamingNamingSectionParserRuleCall_5_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNamingSection_in_ruleScopeModel256);
                    lv_naming_6_0=ruleNamingSection();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
                      	        }
                             		set(
                             			current, 
                             			"naming",
                              		lv_naming_6_0, 
                              		"NamingSection");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:185:3: ( (lv_scopes_7_0= ruleScopeDefinition ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==23) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:186:1: (lv_scopes_7_0= ruleScopeDefinition )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:186:1: (lv_scopes_7_0= ruleScopeDefinition )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:187:3: lv_scopes_7_0= ruleScopeDefinition
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getScopesScopeDefinitionParserRuleCall_6_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleScopeDefinition_in_ruleScopeModel278);
            	    lv_scopes_7_0=ruleScopeDefinition();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"scopes",
            	              		lv_scopes_7_0, 
            	              		"ScopeDefinition");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleScopeModel"


    // $ANTLR start "entryRuleImport"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:211:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:212:2: (iv_ruleImport= ruleImport EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:213:2: iv_ruleImport= ruleImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportRule()); 
            }
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport315);
            iv_ruleImport=ruleImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport325); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:220:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:223:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:224:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:224:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:224:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )?
            {
            otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleImport362); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:228:1: ( (otherlv_1= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:229:1: (otherlv_1= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:229:1: (otherlv_1= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:230:3: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getImportRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImport386); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
              	
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:244:2: (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:244:4: otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) )
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_15_in_ruleImport399); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportAccess().getAsKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:248:1: ( (lv_name_3_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:249:1: (lv_name_3_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:249:1: (lv_name_3_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:250:3: lv_name_3_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImportAccess().getNameIdentifierParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleImport420);
                    lv_name_3_0=ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getImportRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_3_0, 
                              		"Identifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleExtension"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:274:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:275:2: (iv_ruleExtension= ruleExtension EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:276:2: iv_ruleExtension= ruleExtension EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExtensionRule()); 
            }
            pushFollow(FOLLOW_ruleExtension_in_entryRuleExtension458);
            iv_ruleExtension=ruleExtension();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExtension; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExtension468); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExtension"


    // $ANTLR start "ruleExtension"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:283:1: ruleExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_extension_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:286:28: ( (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:287:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:287:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:287:3: otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleExtension505); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExtensionAccess().getExtensionKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:291:1: ( (lv_extension_1_0= ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:292:1: (lv_extension_1_0= ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:292:1: (lv_extension_1_0= ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:293:3: lv_extension_1_0= ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExtensionAccess().getExtensionQualifiedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExtension526);
            lv_extension_1_0=ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExtensionRule());
              	        }
                     		set(
                     			current, 
                     			"extension",
                      		lv_extension_1_0, 
                      		"QualifiedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleExtension"


    // $ANTLR start "entryRuleNamingSection"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:317:1: entryRuleNamingSection returns [EObject current=null] : iv_ruleNamingSection= ruleNamingSection EOF ;
    public final EObject entryRuleNamingSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingSection = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:318:2: (iv_ruleNamingSection= ruleNamingSection EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:319:2: iv_ruleNamingSection= ruleNamingSection EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingSectionRule()); 
            }
            pushFollow(FOLLOW_ruleNamingSection_in_entryRuleNamingSection562);
            iv_ruleNamingSection=ruleNamingSection();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingSection; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamingSection572); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNamingSection"


    // $ANTLR start "ruleNamingSection"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:326:1: ruleNamingSection returns [EObject current=null] : ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' ) ;
    public final EObject ruleNamingSection() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Enumerator lv_casing_2_0 = null;

        EObject lv_namings_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:329:28: ( ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:330:1: ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:330:1: ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:330:2: () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:330:2: ()
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:331:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getNamingSectionAccess().getNamingSectionAction_0(),
                          current);
                  
            }

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:339:2: (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==17) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:339:4: otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) )
                    {
                    otherlv_1=(Token)match(input,17,FOLLOW_17_in_ruleNamingSection622); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNamingSectionAccess().getCaseKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:343:1: ( (lv_casing_2_0= ruleCasing ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:344:1: (lv_casing_2_0= ruleCasing )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:344:1: (lv_casing_2_0= ruleCasing )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:345:3: lv_casing_2_0= ruleCasing
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingSectionAccess().getCasingCasingEnumRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleCasing_in_ruleNamingSection643);
                    lv_casing_2_0=ruleCasing();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamingSectionRule());
                      	        }
                             		set(
                             			current, 
                             			"casing",
                              		lv_casing_2_0, 
                              		"Casing");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,18,FOLLOW_18_in_ruleNamingSection657); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getNamingSectionAccess().getNamingKeyword_2());
                  
            }
            otherlv_4=(Token)match(input,19,FOLLOW_19_in_ruleNamingSection669); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getNamingSectionAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:369:1: ( (lv_namings_5_0= ruleNamingDefinition ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:370:1: (lv_namings_5_0= ruleNamingDefinition )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:370:1: (lv_namings_5_0= ruleNamingDefinition )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:371:3: lv_namings_5_0= ruleNamingDefinition
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getNamingSectionAccess().getNamingsNamingDefinitionParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleNamingDefinition_in_ruleNamingSection690);
            	    lv_namings_5_0=ruleNamingDefinition();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getNamingSectionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"namings",
            	              		lv_namings_5_0, 
            	              		"NamingDefinition");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            otherlv_6=(Token)match(input,20,FOLLOW_20_in_ruleNamingSection703); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getNamingSectionAccess().getRightCurlyBracketKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleNamingSection"


    // $ANTLR start "entryRuleNamingDefinition"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:399:1: entryRuleNamingDefinition returns [EObject current=null] : iv_ruleNamingDefinition= ruleNamingDefinition EOF ;
    public final EObject entryRuleNamingDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingDefinition = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:400:2: (iv_ruleNamingDefinition= ruleNamingDefinition EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:401:2: iv_ruleNamingDefinition= ruleNamingDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleNamingDefinition_in_entryRuleNamingDefinition739);
            iv_ruleNamingDefinition=ruleNamingDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamingDefinition749); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNamingDefinition"


    // $ANTLR start "ruleNamingDefinition"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:408:1: ruleNamingDefinition returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' ) ;
    public final EObject ruleNamingDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_naming_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:411:28: ( ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:412:1: ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:412:1: ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:412:2: ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:412:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:413:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:413:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:414:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getNamingDefinitionRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNamingDefinitionAccess().getTypeEClassCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleNamingDefinition801);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleNamingDefinition813); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNamingDefinitionAccess().getEqualsSignKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:434:1: ( (lv_naming_2_0= ruleNaming ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:435:1: (lv_naming_2_0= ruleNaming )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:435:1: (lv_naming_2_0= ruleNaming )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:436:3: lv_naming_2_0= ruleNaming
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNamingDefinitionAccess().getNamingNamingParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNaming_in_ruleNamingDefinition834);
            lv_naming_2_0=ruleNaming();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getNamingDefinitionRule());
              	        }
                     		set(
                     			current, 
                     			"naming",
                      		lv_naming_2_0, 
                      		"Naming");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,22,FOLLOW_22_in_ruleNamingDefinition846); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getNamingDefinitionAccess().getSemicolonKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleNamingDefinition"


    // $ANTLR start "entryRuleScopeDefinition"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:464:1: entryRuleScopeDefinition returns [EObject current=null] : iv_ruleScopeDefinition= ruleScopeDefinition EOF ;
    public final EObject entryRuleScopeDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeDefinition = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:465:2: (iv_ruleScopeDefinition= ruleScopeDefinition EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:466:2: iv_ruleScopeDefinition= ruleScopeDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleScopeDefinition_in_entryRuleScopeDefinition882);
            iv_ruleScopeDefinition=ruleScopeDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeDefinition892); if (state.failed) return current;

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
    // $ANTLR end "entryRuleScopeDefinition"


    // $ANTLR start "ruleScopeDefinition"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:473:1: ruleScopeDefinition returns [EObject current=null] : (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' ) ;
    public final EObject ruleScopeDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_rules_9_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:476:28: ( (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:477:1: (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:477:1: (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:477:3: otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleScopeDefinition929); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeDefinitionAccess().getScopeKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:481:1: (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==24) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:481:3: otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleScopeDefinition942); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getScopeDefinitionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:485:1: ( (lv_name_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:486:1: (lv_name_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:486:1: (lv_name_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:487:3: lv_name_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getNameIdentifierParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleScopeDefinition963);
                    lv_name_2_0=ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScopeDefinitionRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_2_0, 
                              		"Identifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,25,FOLLOW_25_in_ruleScopeDefinition975); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getScopeDefinitionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:507:3: ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) )
            int alt10=2;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:507:4: ( ( ruleQualifiedID ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:507:4: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:508:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:508:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:509:3: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {
                       
                      		  /* */ 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDefinitionRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getTargetTypeEClassCrossReference_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1005);
                    ruleQualifiedID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:526:6: ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:526:6: ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:526:7: ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:526:7: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:527:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:527:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:528:3: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {
                       
                      		  /* */ 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDefinitionRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getContextTypeEClassCrossReference_2_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1039);
                    ruleQualifiedID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_6=(Token)match(input,26,FOLLOW_26_in_ruleScopeDefinition1051); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getScopeDefinitionAccess().getNumberSignKeyword_2_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:548:1: ( ( ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:549:1: ( ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:549:1: ( ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:550:3: ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      		  /* */ 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDefinitionRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getReferenceEReferenceCrossReference_2_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleScopeDefinition1078);
                    ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,19,FOLLOW_19_in_ruleScopeDefinition1092); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getScopeDefinitionAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:570:1: ( (lv_rules_9_0= ruleScopeRule ) )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==27) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:571:1: (lv_rules_9_0= ruleScopeRule )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:571:1: (lv_rules_9_0= ruleScopeRule )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:572:3: lv_rules_9_0= ruleScopeRule
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getRulesScopeRuleParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleScopeRule_in_ruleScopeDefinition1113);
            	    lv_rules_9_0=ruleScopeRule();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getScopeDefinitionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"rules",
            	              		lv_rules_9_0, 
            	              		"ScopeRule");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

            otherlv_10=(Token)match(input,20,FOLLOW_20_in_ruleScopeDefinition1126); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_10, grammarAccess.getScopeDefinitionAccess().getRightCurlyBracketKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleScopeDefinition"


    // $ANTLR start "entryRuleScopeRule"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:600:1: entryRuleScopeRule returns [EObject current=null] : iv_ruleScopeRule= ruleScopeRule EOF ;
    public final EObject entryRuleScopeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:601:2: (iv_ruleScopeRule= ruleScopeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:602:2: iv_ruleScopeRule= ruleScopeRule EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeRuleRule()); 
            }
            pushFollow(FOLLOW_ruleScopeRule_in_entryRuleScopeRule1162);
            iv_ruleScopeRule=ruleScopeRule();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeRule; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeRule1172); if (state.failed) return current;

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
    // $ANTLR end "entryRuleScopeRule"


    // $ANTLR start "ruleScopeRule"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:609:1: ruleScopeRule returns [EObject current=null] : (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' ) ;
    public final EObject ruleScopeRule() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_context_1_0 = null;

        EObject lv_exprs_3_0 = null;

        EObject lv_exprs_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:612:28: ( (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:613:1: (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:613:1: (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:613:3: otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,27,FOLLOW_27_in_ruleScopeRule1209); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeRuleAccess().getContextKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:617:1: ( (lv_context_1_0= ruleScopeContext ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:618:1: (lv_context_1_0= ruleScopeContext )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:618:1: (lv_context_1_0= ruleScopeContext )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:619:3: lv_context_1_0= ruleScopeContext
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeRuleAccess().getContextScopeContextParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleScopeContext_in_ruleScopeRule1230);
            lv_context_1_0=ruleScopeContext();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getScopeRuleRule());
              	        }
                     		set(
                     			current, 
                     			"context",
                      		lv_context_1_0, 
                      		"ScopeContext");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleScopeRule1242); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getScopeRuleAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:639:1: ( (lv_exprs_3_0= ruleScopeExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:640:1: (lv_exprs_3_0= ruleScopeExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:640:1: (lv_exprs_3_0= ruleScopeExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:641:3: lv_exprs_3_0= ruleScopeExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeRuleAccess().getExprsScopeExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleScopeExpression_in_ruleScopeRule1263);
            lv_exprs_3_0=ruleScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getScopeRuleRule());
              	        }
                     		add(
                     			current, 
                     			"exprs",
                      		lv_exprs_3_0, 
                      		"ScopeExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:657:2: (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==28) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:657:4: otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) )
            	    {
            	    otherlv_4=(Token)match(input,28,FOLLOW_28_in_ruleScopeRule1276); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getScopeRuleAccess().getGreaterThanSignGreaterThanSignKeyword_4_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:661:1: ( (lv_exprs_5_0= ruleScopeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:662:1: (lv_exprs_5_0= ruleScopeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:662:1: (lv_exprs_5_0= ruleScopeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:663:3: lv_exprs_5_0= ruleScopeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeRuleAccess().getExprsScopeExpressionParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleScopeExpression_in_ruleScopeRule1297);
            	    lv_exprs_5_0=ruleScopeExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getScopeRuleRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"exprs",
            	              		lv_exprs_5_0, 
            	              		"ScopeExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            otherlv_6=(Token)match(input,22,FOLLOW_22_in_ruleScopeRule1311); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getScopeRuleAccess().getSemicolonKeyword_5());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleScopeRule"


    // $ANTLR start "entryRuleScopeContext"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:691:1: entryRuleScopeContext returns [EObject current=null] : iv_ruleScopeContext= ruleScopeContext EOF ;
    public final EObject entryRuleScopeContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeContext = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:692:2: (iv_ruleScopeContext= ruleScopeContext EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:693:2: iv_ruleScopeContext= ruleScopeContext EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeContextRule()); 
            }
            pushFollow(FOLLOW_ruleScopeContext_in_entryRuleScopeContext1347);
            iv_ruleScopeContext=ruleScopeContext();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeContext; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeContext1357); if (state.failed) return current;

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
    // $ANTLR end "entryRuleScopeContext"


    // $ANTLR start "ruleScopeContext"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:700:1: ruleScopeContext returns [EObject current=null] : ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? ) ;
    public final EObject ruleScopeContext() throws RecognitionException {
        EObject current = null;

        Token lv_global_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_guard_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:703:28: ( ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:704:1: ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:704:1: ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:704:2: ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )?
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:704:2: ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==29) ) {
                alt13=1;
            }
            else if ( (LA13_0==RULE_ID) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:704:3: ( (lv_global_0_0= '*' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:704:3: ( (lv_global_0_0= '*' ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:705:1: (lv_global_0_0= '*' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:705:1: (lv_global_0_0= '*' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:706:3: lv_global_0_0= '*'
                    {
                    lv_global_0_0=(Token)match(input,29,FOLLOW_29_in_ruleScopeContext1401); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_global_0_0, grammarAccess.getScopeContextAccess().getGlobalAsteriskKeyword_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeContextRule());
                      	        }
                             		setWithLastConsumed(current, "global", true, "*");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:720:6: ( ( ruleQualifiedID ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:720:6: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:721:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:721:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:722:3: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {
                       
                      		  /* */ 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeContextRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeContextAccess().getContextTypeEClassCrossReference_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleScopeContext1447);
                    ruleQualifiedID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:738:3: (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==30) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:738:5: otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']'
                    {
                    otherlv_2=(Token)match(input,30,FOLLOW_30_in_ruleScopeContext1461); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getScopeContextAccess().getLeftSquareBracketKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:742:1: ( (lv_guard_3_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:743:1: (lv_guard_3_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:743:1: (lv_guard_3_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:744:3: lv_guard_3_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeContextAccess().getGuardExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleScopeContext1482);
                    lv_guard_3_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScopeContextRule());
                      	        }
                             		set(
                             			current, 
                             			"guard",
                              		lv_guard_3_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,31,FOLLOW_31_in_ruleScopeContext1494); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getScopeContextAccess().getRightSquareBracketKeyword_1_2());
                          
                    }

                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleScopeContext"


    // $ANTLR start "entryRuleScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:772:1: entryRuleScopeExpression returns [EObject current=null] : iv_ruleScopeExpression= ruleScopeExpression EOF ;
    public final EObject entryRuleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:773:2: (iv_ruleScopeExpression= ruleScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:774:2: iv_ruleScopeExpression= ruleScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleScopeExpression_in_entryRuleScopeExpression1532);
            iv_ruleScopeExpression=ruleScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeExpression1542); if (state.failed) return current;

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
    // $ANTLR end "entryRuleScopeExpression"


    // $ANTLR start "ruleScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:781:1: ruleScopeExpression returns [EObject current=null] : ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? ) ;
    public final EObject ruleScopeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        EObject this_ScopeDelegation_0 = null;

        EObject this_FactoryExpression_1 = null;

        EObject this_NamedScopeExpression_2 = null;

        EObject lv_prune_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:784:28: ( ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:785:1: ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:785:1: ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:785:2: (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )?
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:785:2: (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression )
            int alt15=3;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt15=1;
                }
                break;
            case 33:
                {
                alt15=2;
                }
                break;
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case RULE_ID:
            case 19:
            case 24:
            case 36:
            case 44:
            case 48:
            case 51:
            case 63:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
                {
                alt15=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:786:2: this_ScopeDelegation_0= ruleScopeDelegation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getScopeDelegationParserRuleCall_0_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleScopeDelegation_in_ruleScopeExpression1593);
                    this_ScopeDelegation_0=ruleScopeDelegation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ScopeDelegation_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:799:2: this_FactoryExpression_1= ruleFactoryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getFactoryExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFactoryExpression_in_ruleScopeExpression1623);
                    this_FactoryExpression_1=ruleFactoryExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_FactoryExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:812:2: this_NamedScopeExpression_2= ruleNamedScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getNamedScopeExpressionParserRuleCall_0_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNamedScopeExpression_in_ruleScopeExpression1653);
                    this_NamedScopeExpression_2=ruleNamedScopeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_NamedScopeExpression_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:823:2: (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==32) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:823:4: otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,32,FOLLOW_32_in_ruleScopeExpression1666); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getScopeExpressionAccess().getVerticalLineKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:827:1: ( (lv_prune_4_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:828:1: (lv_prune_4_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:828:1: (lv_prune_4_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:829:3: lv_prune_4_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeExpressionAccess().getPruneExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleScopeExpression1687);
                    lv_prune_4_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScopeExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"prune",
                              		lv_prune_4_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleScopeExpression"


    // $ANTLR start "entryRuleFactoryExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:853:1: entryRuleFactoryExpression returns [EObject current=null] : iv_ruleFactoryExpression= ruleFactoryExpression EOF ;
    public final EObject entryRuleFactoryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFactoryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:854:2: (iv_ruleFactoryExpression= ruleFactoryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:855:2: iv_ruleFactoryExpression= ruleFactoryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFactoryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleFactoryExpression_in_entryRuleFactoryExpression1725);
            iv_ruleFactoryExpression=ruleFactoryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFactoryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFactoryExpression1735); if (state.failed) return current;

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
    // $ANTLR end "entryRuleFactoryExpression"


    // $ANTLR start "ruleFactoryExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:862:1: ruleFactoryExpression returns [EObject current=null] : (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) ) ;
    public final EObject ruleFactoryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expr_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:865:28: ( (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:866:1: (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:866:1: (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:866:3: otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,33,FOLLOW_33_in_ruleFactoryExpression1772); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getFactoryExpressionAccess().getFactoryKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:870:1: ( (lv_expr_1_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:871:1: (lv_expr_1_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:871:1: (lv_expr_1_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:872:3: lv_expr_1_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFactoryExpressionAccess().getExprExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleFactoryExpression1793);
            lv_expr_1_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getFactoryExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"expr",
                      		lv_expr_1_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleFactoryExpression"


    // $ANTLR start "entryRuleScopeDelegation"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:896:1: entryRuleScopeDelegation returns [EObject current=null] : iv_ruleScopeDelegation= ruleScopeDelegation EOF ;
    public final EObject entryRuleScopeDelegation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeDelegation = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:897:2: (iv_ruleScopeDelegation= ruleScopeDelegation EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:898:2: iv_ruleScopeDelegation= ruleScopeDelegation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeDelegationRule()); 
            }
            pushFollow(FOLLOW_ruleScopeDelegation_in_entryRuleScopeDelegation1829);
            iv_ruleScopeDelegation=ruleScopeDelegation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeDelegation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeDelegation1839); if (state.failed) return current;

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
    // $ANTLR end "entryRuleScopeDelegation"


    // $ANTLR start "ruleScopeDelegation"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:905:1: ruleScopeDelegation returns [EObject current=null] : (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' ) ;
    public final EObject ruleScopeDelegation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_delegate_2_0 = null;

        EObject lv_external_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:908:28: ( (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:909:1: (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:909:1: (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:909:3: otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')'
            {
            otherlv_0=(Token)match(input,34,FOLLOW_34_in_ruleScopeDelegation1876); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeDelegationAccess().getScopeofKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleScopeDelegation1888); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getScopeDelegationAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:917:1: ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=RULE_STRING && LA17_0<=RULE_ID)||LA17_0==19||LA17_0==24||LA17_0==44||LA17_0==48||LA17_0==51||LA17_0==63||(LA17_0>=65 && LA17_0<=82)) ) {
                alt17=1;
            }
            else if ( (LA17_0==36) ) {
                alt17=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:917:2: ( (lv_delegate_2_0= ruleExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:917:2: ( (lv_delegate_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:918:1: (lv_delegate_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:918:1: (lv_delegate_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:919:3: lv_delegate_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getDelegateExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleScopeDelegation1910);
                    lv_delegate_2_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScopeDelegationRule());
                      	        }
                             		set(
                             			current, 
                             			"delegate",
                              		lv_delegate_2_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:936:6: ( (lv_external_3_0= ruleGlobalScopeExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:936:6: ( (lv_external_3_0= ruleGlobalScopeExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:937:1: (lv_external_3_0= ruleGlobalScopeExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:937:1: (lv_external_3_0= ruleGlobalScopeExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:938:3: lv_external_3_0= ruleGlobalScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getExternalGlobalScopeExpressionParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleGlobalScopeExpression_in_ruleScopeDelegation1937);
                    lv_external_3_0=ruleGlobalScopeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScopeDelegationRule());
                      	        }
                             		set(
                             			current, 
                             			"external",
                              		lv_external_3_0, 
                              		"GlobalScopeExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:954:3: (otherlv_4= ',' ( ( ruleIdentifier ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==35) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:954:5: otherlv_4= ',' ( ( ruleIdentifier ) )
                    {
                    otherlv_4=(Token)match(input,35,FOLLOW_35_in_ruleScopeDelegation1951); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getScopeDelegationAccess().getCommaKeyword_3_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:958:1: ( ( ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:959:1: ( ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:959:1: ( ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:960:3: ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      		  /* */ 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDelegationRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getScopeScopeDefinitionCrossReference_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleScopeDelegation1978);
                    ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,25,FOLLOW_25_in_ruleScopeDelegation1992); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getScopeDelegationAccess().getRightParenthesisKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleScopeDelegation"


    // $ANTLR start "entryRuleNamedScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:988:1: entryRuleNamedScopeExpression returns [EObject current=null] : iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF ;
    public final EObject entryRuleNamedScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamedScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:989:2: (iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:990:2: iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamedScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleNamedScopeExpression_in_entryRuleNamedScopeExpression2028);
            iv_ruleNamedScopeExpression=ruleNamedScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamedScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamedScopeExpression2038); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNamedScopeExpression"


    // $ANTLR start "ruleNamedScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:997:1: ruleNamedScopeExpression returns [EObject current=null] : ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? ) ;
    public final EObject ruleNamedScopeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_caseDef_2_0=null;
        Token otherlv_4=null;
        EObject this_GlobalScopeExpression_0 = null;

        EObject this_SimpleScopeExpression_1 = null;

        Enumerator lv_casing_3_0 = null;

        EObject lv_naming_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1000:28: ( ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1001:1: ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1001:1: ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1001:2: (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )?
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1001:2: (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==36) ) {
                alt19=1;
            }
            else if ( ((LA19_0>=RULE_STRING && LA19_0<=RULE_ID)||LA19_0==19||LA19_0==24||LA19_0==44||LA19_0==48||LA19_0==51||LA19_0==63||(LA19_0>=65 && LA19_0<=82)) ) {
                alt19=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1002:2: this_GlobalScopeExpression_0= ruleGlobalScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getGlobalScopeExpressionParserRuleCall_0_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalScopeExpression_in_ruleNamedScopeExpression2089);
                    this_GlobalScopeExpression_0=ruleGlobalScopeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_GlobalScopeExpression_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1015:2: this_SimpleScopeExpression_1= ruleSimpleScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getSimpleScopeExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleScopeExpression_in_ruleNamedScopeExpression2119);
                    this_SimpleScopeExpression_1=ruleSimpleScopeExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_SimpleScopeExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1026:2: ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==17) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1026:3: ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1026:3: ( (lv_caseDef_2_0= 'case' ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1027:1: (lv_caseDef_2_0= 'case' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1027:1: (lv_caseDef_2_0= 'case' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1028:3: lv_caseDef_2_0= 'case'
                    {
                    lv_caseDef_2_0=(Token)match(input,17,FOLLOW_17_in_ruleNamedScopeExpression2138); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_caseDef_2_0, grammarAccess.getNamedScopeExpressionAccess().getCaseDefCaseKeyword_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getNamedScopeExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "caseDef", true, "case");
                      	    
                    }

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1041:2: ( (lv_casing_3_0= ruleCasing ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1042:1: (lv_casing_3_0= ruleCasing )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1042:1: (lv_casing_3_0= ruleCasing )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1043:3: lv_casing_3_0= ruleCasing
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getCasingCasingEnumRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleCasing_in_ruleNamedScopeExpression2172);
                    lv_casing_3_0=ruleCasing();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamedScopeExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"casing",
                              		lv_casing_3_0, 
                              		"Casing");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1059:4: (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==15) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1059:6: otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) )
                    {
                    otherlv_4=(Token)match(input,15,FOLLOW_15_in_ruleNamedScopeExpression2187); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getNamedScopeExpressionAccess().getAsKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1063:1: ( (lv_naming_5_0= ruleNaming ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1064:1: (lv_naming_5_0= ruleNaming )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1064:1: (lv_naming_5_0= ruleNaming )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1065:3: lv_naming_5_0= ruleNaming
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getNamingNamingParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNaming_in_ruleNamedScopeExpression2208);
                    lv_naming_5_0=ruleNaming();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamedScopeExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"naming",
                              		lv_naming_5_0, 
                              		"Naming");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleNamedScopeExpression"


    // $ANTLR start "entryRuleGlobalScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1089:1: entryRuleGlobalScopeExpression returns [EObject current=null] : iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF ;
    public final EObject entryRuleGlobalScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1090:2: (iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1091:2: iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalScopeExpression_in_entryRuleGlobalScopeExpression2246);
            iv_ruleGlobalScopeExpression=ruleGlobalScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalScopeExpression2256); if (state.failed) return current;

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
    // $ANTLR end "entryRuleGlobalScopeExpression"


    // $ANTLR start "ruleGlobalScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1098:1: ruleGlobalScopeExpression returns [EObject current=null] : (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) ) )? (otherlv_11= ',' otherlv_12= 'data' otherlv_13= '=' otherlv_14= '(' ( (lv_data_15_0= ruleDataExpression ) ) (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )* otherlv_18= ')' )? (otherlv_19= ',' otherlv_20= 'domains' otherlv_21= '=' ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) ) )? otherlv_29= ')' ) ;
    public final EObject ruleGlobalScopeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token lv_domains_22_0=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        EObject lv_name_6_0 = null;

        EObject lv_prefix_10_0 = null;

        EObject lv_data_15_0 = null;

        EObject lv_data_17_0 = null;

        AntlrDatatypeRuleToken lv_domains_23_0 = null;

        AntlrDatatypeRuleToken lv_domains_25_0 = null;

        AntlrDatatypeRuleToken lv_domains_27_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1101:28: ( (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) ) )? (otherlv_11= ',' otherlv_12= 'data' otherlv_13= '=' otherlv_14= '(' ( (lv_data_15_0= ruleDataExpression ) ) (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )* otherlv_18= ')' )? (otherlv_19= ',' otherlv_20= 'domains' otherlv_21= '=' ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) ) )? otherlv_29= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1102:1: (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) ) )? (otherlv_11= ',' otherlv_12= 'data' otherlv_13= '=' otherlv_14= '(' ( (lv_data_15_0= ruleDataExpression ) ) (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )* otherlv_18= ')' )? (otherlv_19= ',' otherlv_20= 'domains' otherlv_21= '=' ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) ) )? otherlv_29= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1102:1: (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) ) )? (otherlv_11= ',' otherlv_12= 'data' otherlv_13= '=' otherlv_14= '(' ( (lv_data_15_0= ruleDataExpression ) ) (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )* otherlv_18= ')' )? (otherlv_19= ',' otherlv_20= 'domains' otherlv_21= '=' ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) ) )? otherlv_29= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1102:3: otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) ) )? (otherlv_11= ',' otherlv_12= 'data' otherlv_13= '=' otherlv_14= '(' ( (lv_data_15_0= ruleDataExpression ) ) (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )* otherlv_18= ')' )? (otherlv_19= ',' otherlv_20= 'domains' otherlv_21= '=' ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) ) )? otherlv_29= ')'
            {
            otherlv_0=(Token)match(input,36,FOLLOW_36_in_ruleGlobalScopeExpression2293); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalScopeExpressionAccess().getFindKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleGlobalScopeExpression2305); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1110:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1111:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1111:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1112:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getGlobalScopeExpressionRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getTypeEClassCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleGlobalScopeExpression2332);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1128:2: ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) ) )?
            int alt22=3;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==35) ) {
                int LA22_1 = input.LA(2);

                if ( (LA22_1==38) ) {
                    alt22=2;
                }
                else if ( (LA22_1==37) ) {
                    alt22=1;
                }
            }
            switch (alt22) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1128:3: (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1128:3: (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1128:5: otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,35,FOLLOW_35_in_ruleGlobalScopeExpression2346); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_3_0_0());
                          
                    }
                    otherlv_4=(Token)match(input,37,FOLLOW_37_in_ruleGlobalScopeExpression2358); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getGlobalScopeExpressionAccess().getKeyKeyword_3_0_1());
                          
                    }
                    otherlv_5=(Token)match(input,21,FOLLOW_21_in_ruleGlobalScopeExpression2370); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_3_0_2());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1140:1: ( (lv_name_6_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1141:1: (lv_name_6_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1141:1: (lv_name_6_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1142:3: lv_name_6_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getNameExpressionParserRuleCall_3_0_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2391);
                    lv_name_6_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"name",
                              		lv_name_6_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1159:6: (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1159:6: (otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1159:8: otherlv_7= ',' otherlv_8= 'prefix' otherlv_9= '=' ( (lv_prefix_10_0= ruleExpression ) )
                    {
                    otherlv_7=(Token)match(input,35,FOLLOW_35_in_ruleGlobalScopeExpression2411); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_3_1_0());
                          
                    }
                    otherlv_8=(Token)match(input,38,FOLLOW_38_in_ruleGlobalScopeExpression2423); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getGlobalScopeExpressionAccess().getPrefixKeyword_3_1_1());
                          
                    }
                    otherlv_9=(Token)match(input,21,FOLLOW_21_in_ruleGlobalScopeExpression2435); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_3_1_2());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1171:1: ( (lv_prefix_10_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1172:1: (lv_prefix_10_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1172:1: (lv_prefix_10_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1173:3: lv_prefix_10_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getPrefixExpressionParserRuleCall_3_1_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2456);
                    lv_prefix_10_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"prefix",
                              		lv_prefix_10_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1189:5: (otherlv_11= ',' otherlv_12= 'data' otherlv_13= '=' otherlv_14= '(' ( (lv_data_15_0= ruleDataExpression ) ) (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )* otherlv_18= ')' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==35) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==39) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1189:7: otherlv_11= ',' otherlv_12= 'data' otherlv_13= '=' otherlv_14= '(' ( (lv_data_15_0= ruleDataExpression ) ) (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )* otherlv_18= ')'
                    {
                    otherlv_11=(Token)match(input,35,FOLLOW_35_in_ruleGlobalScopeExpression2472); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_4_0());
                          
                    }
                    otherlv_12=(Token)match(input,39,FOLLOW_39_in_ruleGlobalScopeExpression2484); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getGlobalScopeExpressionAccess().getDataKeyword_4_1());
                          
                    }
                    otherlv_13=(Token)match(input,21,FOLLOW_21_in_ruleGlobalScopeExpression2496); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_4_2());
                          
                    }
                    otherlv_14=(Token)match(input,24,FOLLOW_24_in_ruleGlobalScopeExpression2508); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_4_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1205:1: ( (lv_data_15_0= ruleDataExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1206:1: (lv_data_15_0= ruleDataExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1206:1: (lv_data_15_0= ruleDataExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1207:3: lv_data_15_0= ruleDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDataDataExpressionParserRuleCall_4_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2529);
                    lv_data_15_0=ruleDataExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                      	        }
                             		add(
                             			current, 
                             			"data",
                              		lv_data_15_0, 
                              		"DataExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1223:2: (otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==35) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1223:4: otherlv_16= ',' ( (lv_data_17_0= ruleDataExpression ) )
                    	    {
                    	    otherlv_16=(Token)match(input,35,FOLLOW_35_in_ruleGlobalScopeExpression2542); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_16, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_4_5_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1227:1: ( (lv_data_17_0= ruleDataExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1228:1: (lv_data_17_0= ruleDataExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1228:1: (lv_data_17_0= ruleDataExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1229:3: lv_data_17_0= ruleDataExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDataDataExpressionParserRuleCall_4_5_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2563);
                    	    lv_data_17_0=ruleDataExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"data",
                    	              		lv_data_17_0, 
                    	              		"DataExpression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);

                    otherlv_18=(Token)match(input,25,FOLLOW_25_in_ruleGlobalScopeExpression2577); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_18, grammarAccess.getGlobalScopeExpressionAccess().getRightParenthesisKeyword_4_6());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1249:3: (otherlv_19= ',' otherlv_20= 'domains' otherlv_21= '=' ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==35) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1249:5: otherlv_19= ',' otherlv_20= 'domains' otherlv_21= '=' ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) )
                    {
                    otherlv_19=(Token)match(input,35,FOLLOW_35_in_ruleGlobalScopeExpression2592); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_19, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_5_0());
                          
                    }
                    otherlv_20=(Token)match(input,40,FOLLOW_40_in_ruleGlobalScopeExpression2604); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getGlobalScopeExpressionAccess().getDomainsKeyword_5_1());
                          
                    }
                    otherlv_21=(Token)match(input,21,FOLLOW_21_in_ruleGlobalScopeExpression2616); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_21, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_5_2());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1261:1: ( ( (lv_domains_22_0= '*' ) ) | ( (lv_domains_23_0= ruleIdentifier ) ) | (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' ) )
                    int alt26=3;
                    switch ( input.LA(1) ) {
                    case 29:
                        {
                        alt26=1;
                        }
                        break;
                    case RULE_ID:
                        {
                        alt26=2;
                        }
                        break;
                    case 24:
                        {
                        alt26=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 26, 0, input);

                        throw nvae;
                    }

                    switch (alt26) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1261:2: ( (lv_domains_22_0= '*' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1261:2: ( (lv_domains_22_0= '*' ) )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1262:1: (lv_domains_22_0= '*' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1262:1: (lv_domains_22_0= '*' )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1263:3: lv_domains_22_0= '*'
                            {
                            lv_domains_22_0=(Token)match(input,29,FOLLOW_29_in_ruleGlobalScopeExpression2635); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_domains_22_0, grammarAccess.getGlobalScopeExpressionAccess().getDomainsAsteriskKeyword_5_3_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getGlobalScopeExpressionRule());
                              	        }
                                     		addWithLastConsumed(current, "domains", lv_domains_22_0, "*");
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1277:6: ( (lv_domains_23_0= ruleIdentifier ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1277:6: ( (lv_domains_23_0= ruleIdentifier ) )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1278:1: (lv_domains_23_0= ruleIdentifier )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1278:1: (lv_domains_23_0= ruleIdentifier )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1279:3: lv_domains_23_0= ruleIdentifier
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2675);
                            lv_domains_23_0=ruleIdentifier();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"domains",
                                      		lv_domains_23_0, 
                                      		"Identifier");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 3 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1296:6: (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1296:6: (otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')' )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1296:8: otherlv_24= '(' ( (lv_domains_25_0= ruleIdentifier ) ) (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )* otherlv_28= ')'
                            {
                            otherlv_24=(Token)match(input,24,FOLLOW_24_in_ruleGlobalScopeExpression2694); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_24, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_5_3_2_0());
                                  
                            }
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1300:1: ( (lv_domains_25_0= ruleIdentifier ) )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1301:1: (lv_domains_25_0= ruleIdentifier )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1301:1: (lv_domains_25_0= ruleIdentifier )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1302:3: lv_domains_25_0= ruleIdentifier
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2715);
                            lv_domains_25_0=ruleIdentifier();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"domains",
                                      		lv_domains_25_0, 
                                      		"Identifier");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1318:2: (otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) ) )*
                            loop25:
                            do {
                                int alt25=2;
                                int LA25_0 = input.LA(1);

                                if ( (LA25_0==35) ) {
                                    alt25=1;
                                }


                                switch (alt25) {
                            	case 1 :
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1318:4: otherlv_26= ',' ( (lv_domains_27_0= ruleIdentifier ) )
                            	    {
                            	    otherlv_26=(Token)match(input,35,FOLLOW_35_in_ruleGlobalScopeExpression2728); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_26, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_5_3_2_2_0());
                            	          
                            	    }
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1322:1: ( (lv_domains_27_0= ruleIdentifier ) )
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1323:1: (lv_domains_27_0= ruleIdentifier )
                            	    {
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1323:1: (lv_domains_27_0= ruleIdentifier )
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1324:3: lv_domains_27_0= ruleIdentifier
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_2_2_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2749);
                            	    lv_domains_27_0=ruleIdentifier();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"domains",
                            	              		lv_domains_27_0, 
                            	              		"Identifier");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop25;
                                }
                            } while (true);

                            otherlv_28=(Token)match(input,25,FOLLOW_25_in_ruleGlobalScopeExpression2763); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_28, grammarAccess.getGlobalScopeExpressionAccess().getRightParenthesisKeyword_5_3_2_3());
                                  
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_29=(Token)match(input,25,FOLLOW_25_in_ruleGlobalScopeExpression2779); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_29, grammarAccess.getGlobalScopeExpressionAccess().getRightParenthesisKeyword_6());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleGlobalScopeExpression"


    // $ANTLR start "entryRuleDataExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1356:1: entryRuleDataExpression returns [EObject current=null] : iv_ruleDataExpression= ruleDataExpression EOF ;
    public final EObject entryRuleDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1357:2: (iv_ruleDataExpression= ruleDataExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1358:2: iv_ruleDataExpression= ruleDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDataExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleDataExpression_in_entryRuleDataExpression2815);
            iv_ruleDataExpression=ruleDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDataExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataExpression2825); if (state.failed) return current;

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
    // $ANTLR end "entryRuleDataExpression"


    // $ANTLR start "ruleDataExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1365:1: ruleDataExpression returns [EObject current=null] : (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression ) ;
    public final EObject ruleDataExpression() throws RecognitionException {
        EObject current = null;

        EObject this_MatchDataExpression_0 = null;

        EObject this_LambdaDataExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1368:28: ( (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1369:1: (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1369:1: (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==RULE_ID) ) {
                alt28=1;
            }
            else if ( (LA28_0==30) ) {
                alt28=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1370:2: this_MatchDataExpression_0= ruleMatchDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDataExpressionAccess().getMatchDataExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleMatchDataExpression_in_ruleDataExpression2875);
                    this_MatchDataExpression_0=ruleMatchDataExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_MatchDataExpression_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1383:2: this_LambdaDataExpression_1= ruleLambdaDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDataExpressionAccess().getLambdaDataExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLambdaDataExpression_in_ruleDataExpression2905);
                    this_LambdaDataExpression_1=ruleLambdaDataExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_LambdaDataExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleDataExpression"


    // $ANTLR start "entryRuleMatchDataExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1402:1: entryRuleMatchDataExpression returns [EObject current=null] : iv_ruleMatchDataExpression= ruleMatchDataExpression EOF ;
    public final EObject entryRuleMatchDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMatchDataExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1403:2: (iv_ruleMatchDataExpression= ruleMatchDataExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1404:2: iv_ruleMatchDataExpression= ruleMatchDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMatchDataExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMatchDataExpression_in_entryRuleMatchDataExpression2940);
            iv_ruleMatchDataExpression=ruleMatchDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMatchDataExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMatchDataExpression2950); if (state.failed) return current;

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
    // $ANTLR end "entryRuleMatchDataExpression"


    // $ANTLR start "ruleMatchDataExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1411:1: ruleMatchDataExpression returns [EObject current=null] : ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) ) ;
    public final EObject ruleMatchDataExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        EObject lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1414:28: ( ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1415:1: ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1415:1: ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1415:2: ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1415:2: ( (lv_key_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1416:1: (lv_key_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1416:1: (lv_key_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1417:3: lv_key_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMatchDataExpressionAccess().getKeyIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleMatchDataExpression2996);
            lv_key_0_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMatchDataExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"key",
                      		lv_key_0_0, 
                      		"Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleMatchDataExpression3008); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMatchDataExpressionAccess().getEqualsSignKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1437:1: ( (lv_value_2_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1438:1: (lv_value_2_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1438:1: (lv_value_2_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1439:3: lv_value_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMatchDataExpressionAccess().getValueExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleMatchDataExpression3029);
            lv_value_2_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getMatchDataExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_2_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleMatchDataExpression"


    // $ANTLR start "entryRuleLambdaDataExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1463:1: entryRuleLambdaDataExpression returns [EObject current=null] : iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF ;
    public final EObject entryRuleLambdaDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaDataExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1464:2: (iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1465:2: iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLambdaDataExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLambdaDataExpression_in_entryRuleLambdaDataExpression3065);
            iv_ruleLambdaDataExpression=ruleLambdaDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLambdaDataExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLambdaDataExpression3075); if (state.failed) return current;

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
    // $ANTLR end "entryRuleLambdaDataExpression"


    // $ANTLR start "ruleLambdaDataExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1472:1: ruleLambdaDataExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' ) ;
    public final EObject ruleLambdaDataExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_desc_1_0 = null;

        EObject lv_value_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1475:28: ( (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1476:1: (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1476:1: (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1476:3: otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,30,FOLLOW_30_in_ruleLambdaDataExpression3112); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLambdaDataExpressionAccess().getLeftSquareBracketKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1480:1: ( (lv_desc_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1481:1: (lv_desc_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1481:1: (lv_desc_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1482:3: lv_desc_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLambdaDataExpressionAccess().getDescIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLambdaDataExpression3133);
            lv_desc_1_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLambdaDataExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"desc",
                      		lv_desc_1_0, 
                      		"Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,32,FOLLOW_32_in_ruleLambdaDataExpression3145); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLambdaDataExpressionAccess().getVerticalLineKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1502:1: ( (lv_value_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1503:1: (lv_value_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1503:1: (lv_value_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1504:3: lv_value_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLambdaDataExpressionAccess().getValueExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLambdaDataExpression3166);
            lv_value_3_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLambdaDataExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"value",
                      		lv_value_3_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,31,FOLLOW_31_in_ruleLambdaDataExpression3178); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLambdaDataExpressionAccess().getRightSquareBracketKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleLambdaDataExpression"


    // $ANTLR start "entryRuleSimpleScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1532:1: entryRuleSimpleScopeExpression returns [EObject current=null] : iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF ;
    public final EObject entryRuleSimpleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1533:2: (iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1534:2: iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleScopeExpression_in_entryRuleSimpleScopeExpression3214);
            iv_ruleSimpleScopeExpression=ruleSimpleScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleScopeExpression3224); if (state.failed) return current;

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
    // $ANTLR end "entryRuleSimpleScopeExpression"


    // $ANTLR start "ruleSimpleScopeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1541:1: ruleSimpleScopeExpression returns [EObject current=null] : ( (lv_expr_0_0= ruleExpression ) ) ;
    public final EObject ruleSimpleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1544:28: ( ( (lv_expr_0_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1545:1: ( (lv_expr_0_0= ruleExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1545:1: ( (lv_expr_0_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1546:1: (lv_expr_0_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1546:1: (lv_expr_0_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1547:3: lv_expr_0_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleScopeExpressionAccess().getExprExpressionParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleSimpleScopeExpression3269);
            lv_expr_0_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSimpleScopeExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"expr",
                      		lv_expr_0_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleSimpleScopeExpression"


    // $ANTLR start "entryRuleNaming"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1571:1: entryRuleNaming returns [EObject current=null] : iv_ruleNaming= ruleNaming EOF ;
    public final EObject entryRuleNaming() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNaming = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1572:2: (iv_ruleNaming= ruleNaming EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1573:2: iv_ruleNaming= ruleNaming EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingRule()); 
            }
            pushFollow(FOLLOW_ruleNaming_in_entryRuleNaming3304);
            iv_ruleNaming=ruleNaming();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNaming; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNaming3314); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNaming"


    // $ANTLR start "ruleNaming"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1580:1: ruleNaming returns [EObject current=null] : ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) ) ;
    public final EObject ruleNaming() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_names_0_0 = null;

        EObject lv_names_2_0 = null;

        EObject lv_names_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1583:28: ( ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1584:1: ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1584:1: ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) )
            int alt30=2;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1584:2: ( (lv_names_0_0= ruleNamingExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1584:2: ( (lv_names_0_0= ruleNamingExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1585:1: (lv_names_0_0= ruleNamingExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1585:1: (lv_names_0_0= ruleNamingExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1586:3: lv_names_0_0= ruleNamingExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNamingExpression_in_ruleNaming3360);
                    lv_names_0_0=ruleNamingExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamingRule());
                      	        }
                             		add(
                             			current, 
                             			"names",
                              		lv_names_0_0, 
                              		"NamingExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1603:6: (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1603:6: (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1603:8: otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleNaming3379); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNamingAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1607:1: ( (lv_names_2_0= ruleNamingExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1608:1: (lv_names_2_0= ruleNamingExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1608:1: (lv_names_2_0= ruleNamingExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1609:3: lv_names_2_0= ruleNamingExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNamingExpression_in_ruleNaming3400);
                    lv_names_2_0=ruleNamingExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamingRule());
                      	        }
                             		add(
                             			current, 
                             			"names",
                              		lv_names_2_0, 
                              		"NamingExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1625:2: (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==35) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1625:4: otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,35,FOLLOW_35_in_ruleNaming3413); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getNamingAccess().getCommaKeyword_1_2_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1629:1: ( (lv_names_4_0= ruleNamingExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1630:1: (lv_names_4_0= ruleNamingExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1630:1: (lv_names_4_0= ruleNamingExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1631:3: lv_names_4_0= ruleNamingExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleNamingExpression_in_ruleNaming3434);
                    	    lv_names_4_0=ruleNamingExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getNamingRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"names",
                    	              		lv_names_4_0, 
                    	              		"NamingExpression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleNaming3448); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getNamingAccess().getRightParenthesisKeyword_1_3());
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleNaming"


    // $ANTLR start "entryRuleNamingExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1659:1: entryRuleNamingExpression returns [EObject current=null] : iv_ruleNamingExpression= ruleNamingExpression EOF ;
    public final EObject entryRuleNamingExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1660:2: (iv_ruleNamingExpression= ruleNamingExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1661:2: iv_ruleNamingExpression= ruleNamingExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleNamingExpression_in_entryRuleNamingExpression3485);
            iv_ruleNamingExpression=ruleNamingExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamingExpression3495); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNamingExpression"


    // $ANTLR start "ruleNamingExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1668:1: ruleNamingExpression returns [EObject current=null] : ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) ) ;
    public final EObject ruleNamingExpression() throws RecognitionException {
        EObject current = null;

        Token lv_export_0_0=null;
        Token lv_factory_1_0=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1671:28: ( ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1672:1: ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1672:1: ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==41) ) {
                alt32=1;
            }
            else if ( ((LA32_0>=RULE_STRING && LA32_0<=RULE_ID)||LA32_0==19||LA32_0==24||LA32_0==33||LA32_0==44||LA32_0==48||LA32_0==51||LA32_0==63||(LA32_0>=65 && LA32_0<=82)) ) {
                alt32=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1672:2: ( (lv_export_0_0= 'export' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1672:2: ( (lv_export_0_0= 'export' ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1673:1: (lv_export_0_0= 'export' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1673:1: (lv_export_0_0= 'export' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1674:3: lv_export_0_0= 'export'
                    {
                    lv_export_0_0=(Token)match(input,41,FOLLOW_41_in_ruleNamingExpression3538); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_export_0_0, grammarAccess.getNamingExpressionAccess().getExportExportKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getNamingExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "export", true, "export");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1688:6: ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1688:6: ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1688:7: ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1688:7: ( (lv_factory_1_0= 'factory' ) )?
                    int alt31=2;
                    int LA31_0 = input.LA(1);

                    if ( (LA31_0==33) ) {
                        alt31=1;
                    }
                    switch (alt31) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1689:1: (lv_factory_1_0= 'factory' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1689:1: (lv_factory_1_0= 'factory' )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1690:3: lv_factory_1_0= 'factory'
                            {
                            lv_factory_1_0=(Token)match(input,33,FOLLOW_33_in_ruleNamingExpression3576); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_factory_1_0, grammarAccess.getNamingExpressionAccess().getFactoryFactoryKeyword_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getNamingExpressionRule());
                              	        }
                                     		setWithLastConsumed(current, "factory", true, "factory");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1703:3: ( (lv_expression_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1704:1: (lv_expression_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1704:1: (lv_expression_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1705:3: lv_expression_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingExpressionAccess().getExpressionExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleNamingExpression3611);
                    lv_expression_2_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamingExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"expression",
                              		lv_expression_2_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleNamingExpression"


    // $ANTLR start "entryRuleQualifiedID"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1729:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1730:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1731:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3649);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID3660); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1738:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Identifier_0 = null;

        AntlrDatatypeRuleToken this_Identifier_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1741:28: ( (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1742:1: (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1742:1: (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1743:5: this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getQualifiedIDAccess().getIdentifierParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleQualifiedID3707);
            this_Identifier_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_Identifier_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1753:1: (kw= '::' this_Identifier_2= ruleIdentifier )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==42) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1754:2: kw= '::' this_Identifier_2= ruleIdentifier
            	    {
            	    kw=(Token)match(input,42,FOLLOW_42_in_ruleQualifiedID3726); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getQualifiedIDAccess().getIdentifierParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleQualifiedID3748);
            	    this_Identifier_2=ruleIdentifier();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_Identifier_2);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              afterParserOrEnumRuleCall();
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleQualifiedID"


    // $ANTLR start "entryRuleDottedID"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1778:1: entryRuleDottedID returns [String current=null] : iv_ruleDottedID= ruleDottedID EOF ;
    public final String entryRuleDottedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDottedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1779:2: (iv_ruleDottedID= ruleDottedID EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1780:2: iv_ruleDottedID= ruleDottedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDottedIDRule()); 
            }
            pushFollow(FOLLOW_ruleDottedID_in_entryRuleDottedID3796);
            iv_ruleDottedID=ruleDottedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDottedID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDottedID3807); if (state.failed) return current;

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
    // $ANTLR end "entryRuleDottedID"


    // $ANTLR start "ruleDottedID"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1787:1: ruleDottedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* ) ;
    public final AntlrDatatypeRuleToken ruleDottedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Identifier_0 = null;

        AntlrDatatypeRuleToken this_Identifier_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1790:28: ( (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1791:1: (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1791:1: (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1792:5: this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getDottedIDAccess().getIdentifierParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleDottedID3854);
            this_Identifier_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_Identifier_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1802:1: (kw= '.' this_Identifier_2= ruleIdentifier )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==43) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1803:2: kw= '.' this_Identifier_2= ruleIdentifier
            	    {
            	    kw=(Token)match(input,43,FOLLOW_43_in_ruleDottedID3873); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getDottedIDAccess().getFullStopKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getDottedIDAccess().getIdentifierParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleDottedID3895);
            	    this_Identifier_2=ruleIdentifier();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_Identifier_2);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              afterParserOrEnumRuleCall();
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleDottedID"


    // $ANTLR start "entryRuleExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1827:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1828:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1829:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression3942);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression3952); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1836:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1839:28: ( (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1840:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1840:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            int alt35=3;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1841:2: this_LetExpression_0= ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLetExpression_in_ruleExpression4002);
                    this_LetExpression_0=ruleLetExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_LetExpression_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1854:2: this_CastedExpression_1= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCastedExpression_in_ruleExpression4032);
                    this_CastedExpression_1=ruleCastedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_CastedExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1867:2: this_ChainExpression_2= ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleChainExpression_in_ruleExpression4062);
                    this_ChainExpression_2=ruleChainExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ChainExpression_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleLetExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1888:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1889:2: (iv_ruleLetExpression= ruleLetExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1890:2: iv_ruleLetExpression= ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLetExpression_in_entryRuleLetExpression4099);
            iv_ruleLetExpression=ruleLetExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetExpression4109); if (state.failed) return current;

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
    // $ANTLR end "entryRuleLetExpression"


    // $ANTLR start "ruleLetExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1897:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
    public final EObject ruleLetExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_identifier_1_0 = null;

        EObject lv_varExpr_3_0 = null;

        EObject lv_target_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1900:28: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1901:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1901:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1901:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,44,FOLLOW_44_in_ruleLetExpression4146); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1905:1: ( (lv_identifier_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1906:1: (lv_identifier_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1906:1: (lv_identifier_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1907:3: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLetExpression4167);
            lv_identifier_1_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"identifier",
                      		lv_identifier_1_0, 
                      		"Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleLetExpression4179); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1927:1: ( (lv_varExpr_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1928:1: (lv_varExpr_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1928:1: (lv_varExpr_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1929:3: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression4200);
            lv_varExpr_3_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"varExpr",
                      		lv_varExpr_3_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,45,FOLLOW_45_in_ruleLetExpression4212); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1949:1: ( (lv_target_5_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1950:1: (lv_target_5_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1950:1: (lv_target_5_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1951:3: lv_target_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression4233);
            lv_target_5_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getLetExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"target",
                      		lv_target_5_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleLetExpression"


    // $ANTLR start "entryRuleCastedExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1975:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1976:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1977:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression4269);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCastedExpression4279); if (state.failed) return current;

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
    // $ANTLR end "entryRuleCastedExpression"


    // $ANTLR start "ruleCastedExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1984:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1987:28: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1988:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1988:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1988:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleCastedExpression4316); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1992:1: ( (lv_type_1_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1993:1: (lv_type_1_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1993:1: (lv_type_1_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1994:3: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleCastedExpression4337);
            lv_type_1_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCastedExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_1_0, 
                      		"Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleCastedExpression4349); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2014:1: ( (lv_target_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2015:1: (lv_target_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2015:1: (lv_target_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2016:3: lv_target_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCastedExpression4370);
            lv_target_3_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCastedExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"target",
                      		lv_target_3_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleCastedExpression"


    // $ANTLR start "entryRuleChainExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2040:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2041:2: (iv_ruleChainExpression= ruleChainExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2042:2: iv_ruleChainExpression= ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainExpression_in_entryRuleChainExpression4406);
            iv_ruleChainExpression=ruleChainExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainExpression4416); if (state.failed) return current;

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
    // $ANTLR end "entryRuleChainExpression"


    // $ANTLR start "ruleChainExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2049:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2052:28: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2053:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2053:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2054:2: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression4466);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ChainedExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2065:1: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==46) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2065:2: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2065:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2066:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,46,FOLLOW_46_in_ruleChainExpression4490); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2078:1: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2079:1: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2079:1: (lv_next_3_0= ruleChainedExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2080:3: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression4511);
            	    lv_next_3_0=ruleChainedExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getChainExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"next",
            	              		lv_next_3_0, 
            	              		"ChainedExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleChainExpression"


    // $ANTLR start "entryRuleChainedExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2104:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2105:2: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2106:2: iv_ruleChainedExpression= ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression4549);
            iv_ruleChainedExpression=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainedExpression4559); if (state.failed) return current;

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
    // $ANTLR end "entryRuleChainedExpression"


    // $ANTLR start "ruleChainedExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2113:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2116:28: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2117:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2117:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            int alt37=3;
            switch ( input.LA(1) ) {
            case 48:
                {
                alt37=1;
                }
                break;
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case RULE_ID:
            case 19:
            case 24:
            case 63:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
                {
                alt37=2;
                }
                break;
            case 51:
                {
                alt37=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }

            switch (alt37) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2118:2: this_IfExpressionKw_0= ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression4609);
                    this_IfExpressionKw_0=ruleIfExpressionKw();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_IfExpressionKw_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2131:2: this_IfExpressionTri_1= ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression4639);
                    this_IfExpressionTri_1=ruleIfExpressionTri();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_IfExpressionTri_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2144:2: this_SwitchExpression_2= ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSwitchExpression_in_ruleChainedExpression4669);
                    this_SwitchExpression_2=ruleSwitchExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_SwitchExpression_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleChainedExpression"


    // $ANTLR start "entryRuleIfExpressionTri"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2163:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2164:2: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2165:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri4704);
            iv_ruleIfExpressionTri=ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionTri; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionTri4714); if (state.failed) return current;

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
    // $ANTLR end "entryRuleIfExpressionTri"


    // $ANTLR start "ruleIfExpressionTri"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2172:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2175:28: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2176:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2176:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2177:2: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleIfExpressionTri4764);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2188:1: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==47) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2188:2: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2188:2: ()
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2189:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_2=(Token)match(input,47,FOLLOW_47_in_ruleIfExpressionTri4788); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2201:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2202:1: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2202:1: (lv_thenPart_3_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2203:3: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri4809);
                    lv_thenPart_3_0=ruleChainedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getIfExpressionTriRule());
                      	        }
                             		set(
                             			current, 
                             			"thenPart",
                              		lv_thenPart_3_0, 
                              		"ChainedExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,45,FOLLOW_45_in_ruleIfExpressionTri4821); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2223:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2224:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2224:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2225:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri4842);
                    lv_elsePart_5_0=ruleChainedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getIfExpressionTriRule());
                      	        }
                             		set(
                             			current, 
                             			"elsePart",
                              		lv_elsePart_5_0, 
                              		"ChainedExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleIfExpressionTri"


    // $ANTLR start "entryRuleIfExpressionKw"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2249:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2250:2: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2251:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw4880);
            iv_ruleIfExpressionKw=ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionKw; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionKw4890); if (state.failed) return current;

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
    // $ANTLR end "entryRuleIfExpressionKw"


    // $ANTLR start "ruleIfExpressionKw"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2258:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2261:28: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2262:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2262:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2262:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            otherlv_0=(Token)match(input,48,FOLLOW_48_in_ruleIfExpressionKw4927); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2266:1: ( (lv_condition_1_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2267:1: (lv_condition_1_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2267:1: (lv_condition_1_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2268:3: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw4948);
            lv_condition_1_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getIfExpressionKwRule());
              	        }
                     		set(
                     			current, 
                     			"condition",
                      		lv_condition_1_0, 
                      		"ChainedExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,49,FOLLOW_49_in_ruleIfExpressionKw4960); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2288:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2289:1: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2289:1: (lv_thenPart_3_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2290:3: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw4981);
            lv_thenPart_3_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getIfExpressionKwRule());
              	        }
                     		set(
                     			current, 
                     			"thenPart",
                      		lv_thenPart_3_0, 
                      		"ChainedExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2306:2: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==50) ) {
                int LA39_1 = input.LA(2);

                if ( (synpred44_InternalScope()) ) {
                    alt39=1;
                }
            }
            switch (alt39) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2306:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,50,FOLLOW_50_in_ruleIfExpressionKw4994); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2310:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2311:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2311:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2312:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5015);
                    lv_elsePart_5_0=ruleChainedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getIfExpressionKwRule());
                      	        }
                             		set(
                             			current, 
                             			"elsePart",
                              		lv_elsePart_5_0, 
                              		"ChainedExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleIfExpressionKw"


    // $ANTLR start "entryRuleSwitchExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2336:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2337:2: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2338:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression5053);
            iv_ruleSwitchExpression=ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSwitchExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchExpression5063); if (state.failed) return current;

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
    // $ANTLR end "entryRuleSwitchExpression"


    // $ANTLR start "ruleSwitchExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2345:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
    public final EObject ruleSwitchExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_switchExpr_2_0 = null;

        EObject lv_case_5_0 = null;

        EObject lv_defaultExpr_8_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2348:28: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2349:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2349:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2349:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,51,FOLLOW_51_in_ruleSwitchExpression5100); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2353:1: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==24) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2353:3: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleSwitchExpression5113); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2357:1: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2358:1: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2358:1: (lv_switchExpr_2_0= ruleOrExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2359:3: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression5134);
                    lv_switchExpr_2_0=ruleOrExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getSwitchExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"switchExpr",
                              		lv_switchExpr_2_0, 
                              		"OrExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,25,FOLLOW_25_in_ruleSwitchExpression5146); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,19,FOLLOW_19_in_ruleSwitchExpression5160); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2383:1: ( (lv_case_5_0= ruleCase ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( (LA41_0==17) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2384:1: (lv_case_5_0= ruleCase )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2384:1: (lv_case_5_0= ruleCase )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2385:3: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleCase_in_ruleSwitchExpression5181);
            	    lv_case_5_0=ruleCase();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getSwitchExpressionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"case",
            	              		lv_case_5_0, 
            	              		"Case");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);

            otherlv_6=(Token)match(input,52,FOLLOW_52_in_ruleSwitchExpression5194); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
                  
            }
            otherlv_7=(Token)match(input,45,FOLLOW_45_in_ruleSwitchExpression5206); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2409:1: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2410:1: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2410:1: (lv_defaultExpr_8_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2411:3: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression5227);
            lv_defaultExpr_8_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSwitchExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"defaultExpr",
                      		lv_defaultExpr_8_0, 
                      		"OrExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_9=(Token)match(input,20,FOLLOW_20_in_ruleSwitchExpression5239); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getSwitchExpressionAccess().getRightCurlyBracketKeyword_7());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleSwitchExpression"


    // $ANTLR start "entryRuleCase"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2439:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2440:2: (iv_ruleCase= ruleCase EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2441:2: iv_ruleCase= ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_ruleCase_in_entryRuleCase5275);
            iv_ruleCase=ruleCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCase; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCase5285); if (state.failed) return current;

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
    // $ANTLR end "entryRuleCase"


    // $ANTLR start "ruleCase"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2448:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2451:28: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2452:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2452:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2452:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleCase5322); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2456:1: ( (lv_condition_1_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2457:1: (lv_condition_1_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2457:1: (lv_condition_1_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2458:3: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase5343);
            lv_condition_1_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCaseRule());
              	        }
                     		set(
                     			current, 
                     			"condition",
                      		lv_condition_1_0, 
                      		"OrExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,45,FOLLOW_45_in_ruleCase5355); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2478:1: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2479:1: (lv_thenPar_3_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2479:1: (lv_thenPar_3_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2480:3: lv_thenPar_3_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase5376);
            lv_thenPar_3_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCaseRule());
              	        }
                     		set(
                     			current, 
                     			"thenPar",
                      		lv_thenPar_3_0, 
                      		"OrExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleCase"


    // $ANTLR start "entryRuleOrExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2504:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2505:2: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2506:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression5412);
            iv_ruleOrExpression=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression5422); if (state.failed) return current;

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
    // $ANTLR end "entryRuleOrExpression"


    // $ANTLR start "ruleOrExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2513:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2516:28: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2517:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2517:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2518:2: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression5472);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2529:1: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==53) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2529:2: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2529:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2530:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2538:2: ( (lv_operator_2_0= '||' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2539:1: (lv_operator_2_0= '||' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2539:1: (lv_operator_2_0= '||' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2540:3: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,53,FOLLOW_53_in_ruleOrExpression5502); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_operator_2_0, grammarAccess.getOrExpressionAccess().getOperatorVerticalLineVerticalLineKeyword_1_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getOrExpressionRule());
            	      	        }
            	             		setWithLastConsumed(current, "operator", lv_operator_2_0, "||");
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2553:2: ( (lv_right_3_0= ruleAndExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2554:1: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2554:1: (lv_right_3_0= ruleAndExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2555:3: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression5536);
            	    lv_right_3_0=ruleAndExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getOrExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"AndExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleOrExpression"


    // $ANTLR start "entryRuleAndExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2579:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2580:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2581:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression5574);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression5584); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAndExpression"


    // $ANTLR start "ruleAndExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2588:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2591:28: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2592:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2592:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2593:2: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression5634);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2604:1: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==54) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2604:2: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2604:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2605:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2613:2: ( (lv_operator_2_0= '&&' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2614:1: (lv_operator_2_0= '&&' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2614:1: (lv_operator_2_0= '&&' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2615:3: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,54,FOLLOW_54_in_ruleAndExpression5664); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_operator_2_0, grammarAccess.getAndExpressionAccess().getOperatorAmpersandAmpersandKeyword_1_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getAndExpressionRule());
            	      	        }
            	             		setWithLastConsumed(current, "operator", lv_operator_2_0, "&&");
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2628:2: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2629:1: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2629:1: (lv_right_3_0= ruleImpliesExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2630:3: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression5698);
            	    lv_right_3_0=ruleImpliesExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getAndExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"ImpliesExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleAndExpression"


    // $ANTLR start "entryRuleImpliesExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2654:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2655:2: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2656:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression5736);
            iv_ruleImpliesExpression=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpression5746); if (state.failed) return current;

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
    // $ANTLR end "entryRuleImpliesExpression"


    // $ANTLR start "ruleImpliesExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2663:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2666:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2667:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2667:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2668:2: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression5796);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2679:1: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==55) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2679:2: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2679:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2680:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2688:2: ( (lv_operator_2_0= 'implies' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2689:1: (lv_operator_2_0= 'implies' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2689:1: (lv_operator_2_0= 'implies' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2690:3: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,55,FOLLOW_55_in_ruleImpliesExpression5826); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_operator_2_0, grammarAccess.getImpliesExpressionAccess().getOperatorImpliesKeyword_1_1_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getImpliesExpressionRule());
            	      	        }
            	             		setWithLastConsumed(current, "operator", lv_operator_2_0, "implies");
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2703:2: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2704:1: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2704:1: (lv_right_3_0= ruleRelationalExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2705:3: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression5860);
            	    lv_right_3_0=ruleRelationalExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getImpliesExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"RelationalExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleImpliesExpression"


    // $ANTLR start "entryRuleRelationalExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2729:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2730:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2731:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression5898);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression5908); if (state.failed) return current;

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
    // $ANTLR end "entryRuleRelationalExpression"


    // $ANTLR start "ruleRelationalExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2738:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
    public final EObject ruleRelationalExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_1=null;
        Token lv_operator_2_2=null;
        Token lv_operator_2_3=null;
        Token lv_operator_2_4=null;
        Token lv_operator_2_5=null;
        Token lv_operator_2_6=null;
        EObject this_AdditiveExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2741:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2742:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2742:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2743:2: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression5958);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2754:1: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=56 && LA46_0<=61)) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2754:2: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2754:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2755:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2763:2: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2764:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2764:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2765:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2765:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    int alt45=6;
            	    switch ( input.LA(1) ) {
            	    case 56:
            	        {
            	        alt45=1;
            	        }
            	        break;
            	    case 57:
            	        {
            	        alt45=2;
            	        }
            	        break;
            	    case 58:
            	        {
            	        alt45=3;
            	        }
            	        break;
            	    case 59:
            	        {
            	        alt45=4;
            	        }
            	        break;
            	    case 60:
            	        {
            	        alt45=5;
            	        }
            	        break;
            	    case 61:
            	        {
            	        alt45=6;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 45, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt45) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2766:3: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,56,FOLLOW_56_in_ruleRelationalExpression5990); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_operator_2_1, grammarAccess.getRelationalExpressionAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "operator", lv_operator_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2778:8: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,57,FOLLOW_57_in_ruleRelationalExpression6019); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_operator_2_2, grammarAccess.getRelationalExpressionAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "operator", lv_operator_2_2, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2790:8: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,58,FOLLOW_58_in_ruleRelationalExpression6048); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_operator_2_3, grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_2());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "operator", lv_operator_2_3, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 4 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2802:8: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,59,FOLLOW_59_in_ruleRelationalExpression6077); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_operator_2_4, grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_3());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "operator", lv_operator_2_4, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 5 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2814:8: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,60,FOLLOW_60_in_ruleRelationalExpression6106); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_operator_2_5, grammarAccess.getRelationalExpressionAccess().getOperatorGreaterThanSignKeyword_1_1_0_4());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "operator", lv_operator_2_5, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 6 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2826:8: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,61,FOLLOW_61_in_ruleRelationalExpression6135); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_operator_2_6, grammarAccess.getRelationalExpressionAccess().getOperatorLessThanSignKeyword_1_1_0_5());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getRelationalExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "operator", lv_operator_2_6, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2841:2: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2842:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2842:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2843:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression6172);
            	    lv_right_3_0=ruleAdditiveExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getRelationalExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"AdditiveExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleRelationalExpression"


    // $ANTLR start "entryRuleAdditiveExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2867:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2868:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2869:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression6210);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression6220); if (state.failed) return current;

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
    // $ANTLR end "entryRuleAdditiveExpression"


    // $ANTLR start "ruleAdditiveExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2876:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2879:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2880:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2880:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2881:2: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6270);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2892:1: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=62 && LA48_0<=63)) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2892:2: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2892:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2893:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2901:2: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2902:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2902:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2903:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2903:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    int alt47=2;
            	    int LA47_0 = input.LA(1);

            	    if ( (LA47_0==62) ) {
            	        alt47=1;
            	    }
            	    else if ( (LA47_0==63) ) {
            	        alt47=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 47, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt47) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2904:3: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,62,FOLLOW_62_in_ruleAdditiveExpression6302); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_2_1, grammarAccess.getAdditiveExpressionAccess().getNamePlusSignKeyword_1_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getAdditiveExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2916:8: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,63,FOLLOW_63_in_ruleAdditiveExpression6331); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_2_2, grammarAccess.getAdditiveExpressionAccess().getNameHyphenMinusKeyword_1_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getAdditiveExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_2_2, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2931:2: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2932:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2932:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2933:3: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6368);
            	    lv_params_3_0=ruleMultiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getAdditiveExpressionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"params",
            	              		lv_params_3_0, 
            	              		"MultiplicativeExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleAdditiveExpression"


    // $ANTLR start "entryRuleMultiplicativeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2957:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2958:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2959:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression6406);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression6416); if (state.failed) return current;

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
    // $ANTLR end "entryRuleMultiplicativeExpression"


    // $ANTLR start "ruleMultiplicativeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2966:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2969:28: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2970:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2970:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2971:2: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6466);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryOrInfixExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2982:1: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( (LA50_0==29||LA50_0==64) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2982:2: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2982:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2983:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2991:2: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2992:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2992:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2993:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2993:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    int alt49=2;
            	    int LA49_0 = input.LA(1);

            	    if ( (LA49_0==29) ) {
            	        alt49=1;
            	    }
            	    else if ( (LA49_0==64) ) {
            	        alt49=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 49, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt49) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2994:3: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,29,FOLLOW_29_in_ruleMultiplicativeExpression6498); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_2_1, grammarAccess.getMultiplicativeExpressionAccess().getNameAsteriskKeyword_1_1_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_2_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3006:8: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,64,FOLLOW_64_in_ruleMultiplicativeExpression6527); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_2_2, grammarAccess.getMultiplicativeExpressionAccess().getNameSolidusKeyword_1_1_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getMultiplicativeExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_2_2, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3021:2: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3022:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3022:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3023:3: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6564);
            	    lv_params_3_0=ruleUnaryOrInfixExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getMultiplicativeExpressionRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"params",
            	              		lv_params_3_0, 
            	              		"UnaryOrInfixExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleMultiplicativeExpression"


    // $ANTLR start "entryRuleUnaryOrInfixExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3047:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3048:2: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3049:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression6602);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOrInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression6612); if (state.failed) return current;

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
    // $ANTLR end "entryRuleUnaryOrInfixExpression"


    // $ANTLR start "ruleUnaryOrInfixExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3056:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3059:28: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3060:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3060:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==63||LA51_0==65) ) {
                alt51=1;
            }
            else if ( ((LA51_0>=RULE_STRING && LA51_0<=RULE_ID)||LA51_0==19||LA51_0==24||(LA51_0>=66 && LA51_0<=82)) ) {
                alt51=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3061:2: this_UnaryExpression_0= ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression6662);
                    this_UnaryExpression_0=ruleUnaryExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_UnaryExpression_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3074:2: this_InfixExpression_1= ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression6692);
                    this_InfixExpression_1=ruleInfixExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_InfixExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleUnaryOrInfixExpression"


    // $ANTLR start "entryRuleUnaryExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3093:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3094:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3095:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression6727);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression6737); if (state.failed) return current;

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
    // $ANTLR end "entryRuleUnaryExpression"


    // $ANTLR start "ruleUnaryExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3102:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3105:28: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3106:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3106:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3106:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3106:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3107:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3107:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3108:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3108:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==65) ) {
                alt52=1;
            }
            else if ( (LA52_0==63) ) {
                alt52=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3109:3: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,65,FOLLOW_65_in_ruleUnaryExpression6782); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_1, grammarAccess.getUnaryExpressionAccess().getNameExclamationMarkKeyword_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getUnaryExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3121:8: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,63,FOLLOW_63_in_ruleUnaryExpression6811); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_2, grammarAccess.getUnaryExpressionAccess().getNameHyphenMinusKeyword_0_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getUnaryExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_2, null);
                      	    
                    }

                    }
                    break;

            }


            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3136:2: ( (lv_params_1_0= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3137:1: (lv_params_1_0= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3137:1: (lv_params_1_0= ruleInfixExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3138:3: lv_params_1_0= ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression6848);
            lv_params_1_0=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUnaryExpressionRule());
              	        }
                     		add(
                     			current, 
                     			"params",
                      		lv_params_1_0, 
                      		"InfixExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleUnaryExpression"


    // $ANTLR start "entryRuleInfixExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3162:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3163:2: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3164:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression6884);
            iv_ruleInfixExpression=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression6894); if (state.failed) return current;

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
    // $ANTLR end "entryRuleInfixExpression"


    // $ANTLR start "ruleInfixExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3171:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
    public final EObject ruleInfixExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_13=null;
        Token lv_name_14_0=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token lv_name_20_1=null;
        Token lv_name_20_2=null;
        Token lv_name_20_3=null;
        Token lv_name_20_4=null;
        Token lv_name_20_5=null;
        Token lv_name_20_6=null;
        Token lv_name_20_7=null;
        Token lv_name_20_8=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        EObject this_PrimaryExpression_0 = null;

        AntlrDatatypeRuleToken lv_name_3_0 = null;

        EObject lv_params_5_0 = null;

        EObject lv_params_7_0 = null;

        EObject lv_type_11_0 = null;

        EObject lv_type_16_0 = null;

        AntlrDatatypeRuleToken lv_var_22_0 = null;

        EObject lv_exp_24_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3174:28: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3175:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3175:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3176:2: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression6944);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3187:1: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop57:
            do {
                int alt57=5;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==43) ) {
                    switch ( input.LA(2) ) {
                    case 80:
                    case 81:
                    case 82:
                        {
                        alt57=2;
                        }
                        break;
                    case RULE_ID:
                        {
                        int LA57_4 = input.LA(3);

                        if ( (LA57_4==EOF||LA57_4==15||LA57_4==17||LA57_4==20||LA57_4==22||LA57_4==25||(LA57_4>=28 && LA57_4<=29)||(LA57_4>=31 && LA57_4<=32)||LA57_4==35||(LA57_4>=42 && LA57_4<=43)||(LA57_4>=45 && LA57_4<=47)||(LA57_4>=49 && LA57_4<=50)||(LA57_4>=52 && LA57_4<=64)) ) {
                            alt57=2;
                        }
                        else if ( (LA57_4==24) ) {
                            alt57=1;
                        }


                        }
                        break;
                    case 66:
                        {
                        alt57=3;
                        }
                        break;
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                    case 71:
                    case 72:
                    case 73:
                    case 74:
                        {
                        alt57=4;
                        }
                        break;

                    }

                }


                switch (alt57) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3187:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3187:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3187:3: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3187:3: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3188:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,43,FOLLOW_43_in_ruleInfixExpression6969); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3200:1: ( (lv_name_3_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3201:1: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3201:1: (lv_name_3_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3202:3: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression6990);
            	    lv_name_3_0=ruleIdentifier();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getInfixExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"name",
            	              		lv_name_3_0, 
            	              		"Identifier");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_4=(Token)match(input,24,FOLLOW_24_in_ruleInfixExpression7002); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3222:1: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt54=2;
            	    int LA54_0 = input.LA(1);

            	    if ( ((LA54_0>=RULE_STRING && LA54_0<=RULE_ID)||LA54_0==19||LA54_0==24||LA54_0==44||LA54_0==48||LA54_0==51||LA54_0==63||(LA54_0>=65 && LA54_0<=82)) ) {
            	        alt54=1;
            	    }
            	    switch (alt54) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3222:2: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3222:2: ( (lv_params_5_0= ruleExpression ) )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3223:1: (lv_params_5_0= ruleExpression )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3223:1: (lv_params_5_0= ruleExpression )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3224:3: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression7024);
            	            lv_params_5_0=ruleExpression();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElementForParent(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		add(
            	                     			current, 
            	                     			"params",
            	                      		lv_params_5_0, 
            	                      		"Expression");
            	              	        afterParserOrEnumRuleCall();
            	              	    
            	            }

            	            }


            	            }

            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3240:2: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop53:
            	            do {
            	                int alt53=2;
            	                int LA53_0 = input.LA(1);

            	                if ( (LA53_0==35) ) {
            	                    alt53=1;
            	                }


            	                switch (alt53) {
            	            	case 1 :
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3240:4: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,35,FOLLOW_35_in_ruleInfixExpression7037); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	          
            	            	    }
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3244:1: ( (lv_params_7_0= ruleExpression ) )
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3245:1: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3245:1: (lv_params_7_0= ruleExpression )
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3246:3: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression7058);
            	            	    lv_params_7_0=ruleExpression();

            	            	    state._fsp--;
            	            	    if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	      	        if (current==null) {
            	            	      	            current = createModelElementForParent(grammarAccess.getInfixExpressionRule());
            	            	      	        }
            	            	             		add(
            	            	             			current, 
            	            	             			"params",
            	            	              		lv_params_7_0, 
            	            	              		"Expression");
            	            	      	        afterParserOrEnumRuleCall();
            	            	      	    
            	            	    }

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop53;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,25,FOLLOW_25_in_ruleInfixExpression7074); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3267:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3267:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3267:7: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3267:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3268:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_10=(Token)match(input,43,FOLLOW_43_in_ruleInfixExpression7106); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3280:1: ( (lv_type_11_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3281:1: (lv_type_11_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3281:1: (lv_type_11_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3282:3: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression7127);
            	    lv_type_11_0=ruleType();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getInfixExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"type",
            	              		lv_type_11_0, 
            	              		"Type");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3299:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3299:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3299:7: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3299:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3300:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_13=(Token)match(input,43,FOLLOW_43_in_ruleInfixExpression7159); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3312:1: ( (lv_name_14_0= 'typeSelect' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3313:1: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3313:1: (lv_name_14_0= 'typeSelect' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3314:3: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,66,FOLLOW_66_in_ruleInfixExpression7177); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              newLeafNode(lv_name_14_0, grammarAccess.getInfixExpressionAccess().getNameTypeSelectKeyword_1_2_2_0());
            	          
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	      	        }
            	             		setWithLastConsumed(current, "name", lv_name_14_0, "typeSelect");
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_15=(Token)match(input,24,FOLLOW_24_in_ruleInfixExpression7202); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3331:1: ( (lv_type_16_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3332:1: (lv_type_16_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3332:1: (lv_type_16_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3333:3: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression7223);
            	    lv_type_16_0=ruleType();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getInfixExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"type",
            	              		lv_type_16_0, 
            	              		"Type");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_17=(Token)match(input,25,FOLLOW_25_in_ruleInfixExpression7235); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3354:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3354:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3354:7: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3354:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3355:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_19=(Token)match(input,43,FOLLOW_43_in_ruleInfixExpression7267); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3367:1: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3368:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3368:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3369:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3369:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    int alt55=8;
            	    switch ( input.LA(1) ) {
            	    case 67:
            	        {
            	        alt55=1;
            	        }
            	        break;
            	    case 68:
            	        {
            	        alt55=2;
            	        }
            	        break;
            	    case 69:
            	        {
            	        alt55=3;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt55=4;
            	        }
            	        break;
            	    case 71:
            	        {
            	        alt55=5;
            	        }
            	        break;
            	    case 72:
            	        {
            	        alt55=6;
            	        }
            	        break;
            	    case 73:
            	        {
            	        alt55=7;
            	        }
            	        break;
            	    case 74:
            	        {
            	        alt55=8;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 55, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt55) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3370:3: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,67,FOLLOW_67_in_ruleInfixExpression7287); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_1, grammarAccess.getInfixExpressionAccess().getNameCollectKeyword_1_3_2_0_0());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_1, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3382:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,68,FOLLOW_68_in_ruleInfixExpression7316); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_2, grammarAccess.getInfixExpressionAccess().getNameSelectKeyword_1_3_2_0_1());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_2, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 3 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3394:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,69,FOLLOW_69_in_ruleInfixExpression7345); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_3, grammarAccess.getInfixExpressionAccess().getNameSelectFirstKeyword_1_3_2_0_2());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_3, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 4 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3406:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,70,FOLLOW_70_in_ruleInfixExpression7374); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_4, grammarAccess.getInfixExpressionAccess().getNameRejectKeyword_1_3_2_0_3());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_4, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 5 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3418:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,71,FOLLOW_71_in_ruleInfixExpression7403); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_5, grammarAccess.getInfixExpressionAccess().getNameExistsKeyword_1_3_2_0_4());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_5, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 6 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3430:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,72,FOLLOW_72_in_ruleInfixExpression7432); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_6, grammarAccess.getInfixExpressionAccess().getNameNotExistsKeyword_1_3_2_0_5());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_6, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 7 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3442:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,73,FOLLOW_73_in_ruleInfixExpression7461); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_7, grammarAccess.getInfixExpressionAccess().getNameSortByKeyword_1_3_2_0_6());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_7, null);
            	              	    
            	            }

            	            }
            	            break;
            	        case 8 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3454:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,74,FOLLOW_74_in_ruleInfixExpression7490); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                      newLeafNode(lv_name_20_8, grammarAccess.getInfixExpressionAccess().getNameForAllKeyword_1_3_2_0_7());
            	                  
            	            }
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElement(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		setWithLastConsumed(current, "name", lv_name_20_8, null);
            	              	    
            	            }

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    otherlv_21=(Token)match(input,24,FOLLOW_24_in_ruleInfixExpression7518); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3473:1: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
            	    int alt56=2;
            	    int LA56_0 = input.LA(1);

            	    if ( (LA56_0==RULE_ID) ) {
            	        int LA56_1 = input.LA(2);

            	        if ( (LA56_1==32) ) {
            	            alt56=1;
            	        }
            	    }
            	    switch (alt56) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3473:2: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3473:2: ( (lv_var_22_0= ruleIdentifier ) )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3474:1: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3474:1: (lv_var_22_0= ruleIdentifier )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3475:3: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression7540);
            	            lv_var_22_0=ruleIdentifier();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              	        if (current==null) {
            	              	            current = createModelElementForParent(grammarAccess.getInfixExpressionRule());
            	              	        }
            	                     		set(
            	                     			current, 
            	                     			"var",
            	                      		lv_var_22_0, 
            	                      		"Identifier");
            	              	        afterParserOrEnumRuleCall();
            	              	    
            	            }

            	            }


            	            }

            	            otherlv_23=(Token)match(input,32,FOLLOW_32_in_ruleInfixExpression7552); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3495:3: ( (lv_exp_24_0= ruleExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3496:1: (lv_exp_24_0= ruleExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3496:1: (lv_exp_24_0= ruleExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3497:3: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression7575);
            	    lv_exp_24_0=ruleExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getInfixExpressionRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"exp",
            	              		lv_exp_24_0, 
            	              		"Expression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_25=(Token)match(input,25,FOLLOW_25_in_ruleInfixExpression7587); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6());
            	          
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleInfixExpression"


    // $ANTLR start "entryRulePrimaryExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3525:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3526:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3527:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression7626);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression7636); if (state.failed) return current;

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
    // $ANTLR end "entryRulePrimaryExpression"


    // $ANTLR start "rulePrimaryExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3534:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
    public final EObject rulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Literal_0 = null;

        EObject this_FeatureCall_1 = null;

        EObject this_ListLiteral_2 = null;

        EObject this_ConstructorCallExpression_3 = null;

        EObject this_GlobalVarExpression_4 = null;

        EObject this_ParanthesizedExpression_5 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3537:28: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3538:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3538:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            int alt58=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case 75:
            case 76:
            case 77:
                {
                alt58=1;
                }
                break;
            case RULE_ID:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 80:
            case 81:
            case 82:
                {
                alt58=2;
                }
                break;
            case 19:
                {
                alt58=3;
                }
                break;
            case 79:
                {
                alt58=4;
                }
                break;
            case 78:
                {
                alt58=5;
                }
                break;
            case 24:
                {
                alt58=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3539:2: this_Literal_0= ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression7686);
                    this_Literal_0=ruleLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_Literal_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3552:2: this_FeatureCall_1= ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFeatureCall_in_rulePrimaryExpression7716);
                    this_FeatureCall_1=ruleFeatureCall();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_FeatureCall_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3565:2: this_ListLiteral_2= ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListLiteral_in_rulePrimaryExpression7746);
                    this_ListLiteral_2=ruleListLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ListLiteral_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3578:2: this_ConstructorCallExpression_3= ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression7776);
                    this_ConstructorCallExpression_3=ruleConstructorCallExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ConstructorCallExpression_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3591:2: this_GlobalVarExpression_4= ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression7806);
                    this_GlobalVarExpression_4=ruleGlobalVarExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_GlobalVarExpression_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 6 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3604:2: this_ParanthesizedExpression_5= ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression7836);
                    this_ParanthesizedExpression_5=ruleParanthesizedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ParanthesizedExpression_5; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "rulePrimaryExpression"


    // $ANTLR start "entryRuleLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3623:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3624:2: (iv_ruleLiteral= ruleLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3625:2: iv_ruleLiteral= ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral7871);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral7881); if (state.failed) return current;

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
    // $ANTLR end "entryRuleLiteral"


    // $ANTLR start "ruleLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3632:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3635:28: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3636:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3636:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            int alt59=5;
            switch ( input.LA(1) ) {
            case 75:
            case 76:
                {
                alt59=1;
                }
                break;
            case RULE_INT:
                {
                alt59=2;
                }
                break;
            case 77:
                {
                alt59=3;
                }
                break;
            case RULE_REAL:
                {
                alt59=4;
                }
                break;
            case RULE_STRING:
                {
                alt59=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }

            switch (alt59) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3637:2: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral7931);
                    this_BooleanLiteral_0=ruleBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_BooleanLiteral_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3650:2: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral7961);
                    this_IntegerLiteral_1=ruleIntegerLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_IntegerLiteral_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3663:2: this_NullLiteral_2= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral7991);
                    this_NullLiteral_2=ruleNullLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_NullLiteral_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3676:2: this_RealLiteral_3= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealLiteral_in_ruleLiteral8021);
                    this_RealLiteral_3=ruleRealLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_RealLiteral_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 5 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3689:2: this_StringLiteral_4= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral8051);
                    this_StringLiteral_4=ruleStringLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_StringLiteral_4; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleLiteral"


    // $ANTLR start "entryRuleBooleanLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3708:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3709:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3710:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral8086);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral8096); if (state.failed) return current;

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
    // $ANTLR end "entryRuleBooleanLiteral"


    // $ANTLR start "ruleBooleanLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3717:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3720:28: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3721:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3721:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3722:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3722:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3723:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3723:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==75) ) {
                alt60=1;
            }
            else if ( (LA60_0==76) ) {
                alt60=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }
            switch (alt60) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3724:3: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,75,FOLLOW_75_in_ruleBooleanLiteral8140); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_val_0_1, grammarAccess.getBooleanLiteralAccess().getValTrueKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBooleanLiteralRule());
                      	        }
                             		setWithLastConsumed(current, "val", lv_val_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3736:8: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,76,FOLLOW_76_in_ruleBooleanLiteral8169); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_val_0_2, grammarAccess.getBooleanLiteralAccess().getValFalseKeyword_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getBooleanLiteralRule());
                      	        }
                             		setWithLastConsumed(current, "val", lv_val_0_2, null);
                      	    
                    }

                    }
                    break;

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleBooleanLiteral"


    // $ANTLR start "entryRuleIntegerLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3759:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3760:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3761:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral8220);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral8230); if (state.failed) return current;

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
    // $ANTLR end "entryRuleIntegerLiteral"


    // $ANTLR start "ruleIntegerLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3768:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3771:28: ( ( (lv_val_0_0= RULE_INT ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3772:1: ( (lv_val_0_0= RULE_INT ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3772:1: ( (lv_val_0_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3773:1: (lv_val_0_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3773:1: (lv_val_0_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3774:3: lv_val_0_0= RULE_INT
            {
            lv_val_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral8271); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_val_0_0, grammarAccess.getIntegerLiteralAccess().getValINTTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getIntegerLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"val",
                      		lv_val_0_0, 
                      		"INT");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleIntegerLiteral"


    // $ANTLR start "entryRuleNullLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3798:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3799:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3800:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral8311);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral8321); if (state.failed) return current;

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
    // $ANTLR end "entryRuleNullLiteral"


    // $ANTLR start "ruleNullLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3807:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3810:28: ( ( (lv_val_0_0= 'null' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3811:1: ( (lv_val_0_0= 'null' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3811:1: ( (lv_val_0_0= 'null' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3812:1: (lv_val_0_0= 'null' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3812:1: (lv_val_0_0= 'null' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3813:3: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,77,FOLLOW_77_in_ruleNullLiteral8363); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_val_0_0, grammarAccess.getNullLiteralAccess().getValNullKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getNullLiteralRule());
              	        }
                     		setWithLastConsumed(current, "val", lv_val_0_0, "null");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleNullLiteral"


    // $ANTLR start "entryRuleRealLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3834:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3835:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3836:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral8411);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealLiteral8421); if (state.failed) return current;

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
    // $ANTLR end "entryRuleRealLiteral"


    // $ANTLR start "ruleRealLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3843:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3846:28: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3847:1: ( (lv_val_0_0= RULE_REAL ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3847:1: ( (lv_val_0_0= RULE_REAL ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3848:1: (lv_val_0_0= RULE_REAL )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3848:1: (lv_val_0_0= RULE_REAL )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3849:3: lv_val_0_0= RULE_REAL
            {
            lv_val_0_0=(Token)match(input,RULE_REAL,FOLLOW_RULE_REAL_in_ruleRealLiteral8462); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_val_0_0, grammarAccess.getRealLiteralAccess().getValREALTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getRealLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"val",
                      		lv_val_0_0, 
                      		"REAL");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleRealLiteral"


    // $ANTLR start "entryRuleStringLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3873:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3874:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3875:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral8502);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral8512); if (state.failed) return current;

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
    // $ANTLR end "entryRuleStringLiteral"


    // $ANTLR start "ruleStringLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3882:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3885:28: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3886:1: ( (lv_val_0_0= RULE_STRING ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3886:1: ( (lv_val_0_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3887:1: (lv_val_0_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3887:1: (lv_val_0_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3888:3: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral8553); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_val_0_0, grammarAccess.getStringLiteralAccess().getValSTRINGTerminalRuleCall_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getStringLiteralRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"val",
                      		lv_val_0_0, 
                      		"STRING");
              	    
            }

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleStringLiteral"


    // $ANTLR start "entryRuleParanthesizedExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3912:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3913:2: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3914:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression8593);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParanthesizedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression8603); if (state.failed) return current;

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
    // $ANTLR end "entryRuleParanthesizedExpression"


    // $ANTLR start "ruleParanthesizedExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3921:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3924:28: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3925:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3925:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3925:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleParanthesizedExpression8640); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression8665);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,25,FOLLOW_25_in_ruleParanthesizedExpression8676); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getParanthesizedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleParanthesizedExpression"


    // $ANTLR start "entryRuleGlobalVarExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3953:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3954:2: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3955:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression8712);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalVarExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression8722); if (state.failed) return current;

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
    // $ANTLR end "entryRuleGlobalVarExpression"


    // $ANTLR start "ruleGlobalVarExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3962:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3965:28: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3966:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3966:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3966:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,78,FOLLOW_78_in_ruleGlobalVarExpression8759); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3970:1: ( (lv_name_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3971:1: (lv_name_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3971:1: (lv_name_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3972:3: lv_name_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression8780);
            lv_name_1_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getGlobalVarExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_1_0, 
                      		"Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleGlobalVarExpression"


    // $ANTLR start "entryRuleFeatureCall"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3996:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3997:2: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3998:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall8816);
            iv_ruleFeatureCall=ruleFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureCall8826); if (state.failed) return current;

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
    // $ANTLR end "entryRuleFeatureCall"


    // $ANTLR start "ruleFeatureCall"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4005:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4008:28: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4009:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4009:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            int alt61=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA61_1 = input.LA(2);

                if ( (LA61_1==EOF||LA61_1==15||LA61_1==17||LA61_1==20||LA61_1==22||LA61_1==25||(LA61_1>=28 && LA61_1<=29)||(LA61_1>=31 && LA61_1<=32)||LA61_1==35||(LA61_1>=42 && LA61_1<=43)||(LA61_1>=45 && LA61_1<=47)||(LA61_1>=49 && LA61_1<=50)||(LA61_1>=52 && LA61_1<=64)) ) {
                    alt61=2;
                }
                else if ( (LA61_1==24) ) {
                    alt61=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 61, 1, input);

                    throw nvae;
                }
                }
                break;
            case 80:
            case 81:
            case 82:
                {
                alt61=2;
                }
                break;
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
                {
                alt61=3;
                }
                break;
            case 66:
                {
                alt61=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4010:2: this_OperationCall_0= ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleOperationCall_in_ruleFeatureCall8876);
                    this_OperationCall_0=ruleOperationCall();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_OperationCall_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4022:6: ( (lv_type_1_0= ruleType ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4022:6: ( (lv_type_1_0= ruleType ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4023:1: (lv_type_1_0= ruleType )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4023:1: (lv_type_1_0= ruleType )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4024:3: lv_type_1_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleFeatureCall8902);
                    lv_type_1_0=ruleType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getFeatureCallRule());
                      	        }
                             		set(
                             			current, 
                             			"type",
                              		lv_type_1_0, 
                              		"Type");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4042:2: this_CollectionExpression_2= ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionExpression_in_ruleFeatureCall8933);
                    this_CollectionExpression_2=ruleCollectionExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_CollectionExpression_2; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 4 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4055:2: this_TypeSelectExpression_3= ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall8963);
                    this_TypeSelectExpression_3=ruleTypeSelectExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_TypeSelectExpression_3; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleFeatureCall"


    // $ANTLR start "entryRuleOperationCall"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4074:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4075:2: (iv_ruleOperationCall= ruleOperationCall EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4076:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_ruleOperationCall_in_entryRuleOperationCall8998);
            iv_ruleOperationCall=ruleOperationCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCall9008); if (state.failed) return current;

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
    // $ANTLR end "entryRuleOperationCall"


    // $ANTLR start "ruleOperationCall"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4083:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleOperationCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_params_2_0 = null;

        EObject lv_params_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4086:28: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4087:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4087:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4087:2: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4087:2: ( (lv_name_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4088:1: (lv_name_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4088:1: (lv_name_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4089:3: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleOperationCall9054);
            lv_name_0_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getOperationCallRule());
              	        }
                     		set(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleOperationCall9066); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4109:1: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( ((LA63_0>=RULE_STRING && LA63_0<=RULE_ID)||LA63_0==19||LA63_0==24||LA63_0==44||LA63_0==48||LA63_0==51||LA63_0==63||(LA63_0>=65 && LA63_0<=82)) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4109:2: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4109:2: ( (lv_params_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4110:1: (lv_params_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4110:1: (lv_params_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4111:3: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall9088);
                    lv_params_2_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getOperationCallRule());
                      	        }
                             		add(
                             			current, 
                             			"params",
                              		lv_params_2_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4127:2: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop62:
                    do {
                        int alt62=2;
                        int LA62_0 = input.LA(1);

                        if ( (LA62_0==35) ) {
                            alt62=1;
                        }


                        switch (alt62) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4127:4: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,35,FOLLOW_35_in_ruleOperationCall9101); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4131:1: ( (lv_params_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4132:1: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4132:1: (lv_params_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4133:3: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall9122);
                    	    lv_params_4_0=ruleExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getOperationCallRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"params",
                    	              		lv_params_4_0, 
                    	              		"Expression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop62;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleOperationCall9138); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getOperationCallAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleOperationCall"


    // $ANTLR start "entryRuleListLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4161:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4162:2: (iv_ruleListLiteral= ruleListLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4163:2: iv_ruleListLiteral= ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleListLiteral_in_entryRuleListLiteral9174);
            iv_ruleListLiteral=ruleListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListLiteral9184); if (state.failed) return current;

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
    // $ANTLR end "entryRuleListLiteral"


    // $ANTLR start "ruleListLiteral"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4170:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4173:28: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4174:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4174:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4174:2: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4174:2: ()
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4175:2: 
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListLiteralAccess().getListLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,19,FOLLOW_19_in_ruleListLiteral9233); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4187:1: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( ((LA65_0>=RULE_STRING && LA65_0<=RULE_ID)||LA65_0==19||LA65_0==24||LA65_0==44||LA65_0==48||LA65_0==51||LA65_0==63||(LA65_0>=65 && LA65_0<=82)) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4187:2: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4187:2: ( (lv_elements_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4188:1: (lv_elements_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4188:1: (lv_elements_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4189:3: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral9255);
                    lv_elements_2_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getListLiteralRule());
                      	        }
                             		add(
                             			current, 
                             			"elements",
                              		lv_elements_2_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4205:2: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==35) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4205:4: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,35,FOLLOW_35_in_ruleListLiteral9268); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4209:1: ( (lv_elements_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4210:1: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4210:1: (lv_elements_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4211:3: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral9289);
                    	    lv_elements_4_0=ruleExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getListLiteralRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"elements",
                    	              		lv_elements_4_0, 
                    	              		"Expression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop64;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,20,FOLLOW_20_in_ruleListLiteral9305); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getListLiteralAccess().getRightCurlyBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleListLiteral"


    // $ANTLR start "entryRuleConstructorCallExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4239:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4240:2: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4241:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression9341);
            iv_ruleConstructorCallExpression=ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorCallExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstructorCallExpression9351); if (state.failed) return current;

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
    // $ANTLR end "entryRuleConstructorCallExpression"


    // $ANTLR start "ruleConstructorCallExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4248:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4251:28: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4252:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4252:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4252:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,79,FOLLOW_79_in_ruleConstructorCallExpression9388); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4256:1: ( (lv_type_1_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4257:1: (lv_type_1_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4257:1: (lv_type_1_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4258:3: lv_type_1_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression9409);
            lv_type_1_0=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getConstructorCallExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_1_0, 
                      		"SimpleType");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleConstructorCallExpression"


    // $ANTLR start "entryRuleTypeSelectExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4282:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4283:2: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4284:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression9445);
            iv_ruleTypeSelectExpression=ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeSelectExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTypeSelectExpression9455); if (state.failed) return current;

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
    // $ANTLR end "entryRuleTypeSelectExpression"


    // $ANTLR start "ruleTypeSelectExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4291:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4294:28: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4295:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4295:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4295:2: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4295:2: ( (lv_name_0_0= 'typeSelect' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4296:1: (lv_name_0_0= 'typeSelect' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4296:1: (lv_name_0_0= 'typeSelect' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4297:3: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,66,FOLLOW_66_in_ruleTypeSelectExpression9498); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                      newLeafNode(lv_name_0_0, grammarAccess.getTypeSelectExpressionAccess().getNameTypeSelectKeyword_0_0());
                  
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getTypeSelectExpressionRule());
              	        }
                     		setWithLastConsumed(current, "name", lv_name_0_0, "typeSelect");
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleTypeSelectExpression9523); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4314:1: ( (lv_type_2_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4315:1: (lv_type_2_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4315:1: (lv_type_2_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4316:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleTypeSelectExpression9544);
            lv_type_2_0=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getTypeSelectExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_2_0, 
                      		"Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,25,FOLLOW_25_in_ruleTypeSelectExpression9556); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getTypeSelectExpressionAccess().getRightParenthesisKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleTypeSelectExpression"


    // $ANTLR start "entryRuleCollectionExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4344:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4345:2: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4346:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression9592);
            iv_ruleCollectionExpression=ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionExpression9602); if (state.failed) return current;

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
    // $ANTLR end "entryRuleCollectionExpression"


    // $ANTLR start "ruleCollectionExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4353:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
    public final EObject ruleCollectionExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        Token lv_name_0_3=null;
        Token lv_name_0_4=null;
        Token lv_name_0_5=null;
        Token lv_name_0_6=null;
        Token lv_name_0_7=null;
        Token lv_name_0_8=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_var_2_0 = null;

        EObject lv_exp_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4356:28: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4357:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4357:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4357:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4357:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4358:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4358:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4359:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4359:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            int alt66=8;
            switch ( input.LA(1) ) {
            case 67:
                {
                alt66=1;
                }
                break;
            case 68:
                {
                alt66=2;
                }
                break;
            case 69:
                {
                alt66=3;
                }
                break;
            case 70:
                {
                alt66=4;
                }
                break;
            case 71:
                {
                alt66=5;
                }
                break;
            case 72:
                {
                alt66=6;
                }
                break;
            case 73:
                {
                alt66=7;
                }
                break;
            case 74:
                {
                alt66=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }

            switch (alt66) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4360:3: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,67,FOLLOW_67_in_ruleCollectionExpression9647); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_1, grammarAccess.getCollectionExpressionAccess().getNameCollectKeyword_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4372:8: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,68,FOLLOW_68_in_ruleCollectionExpression9676); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_2, grammarAccess.getCollectionExpressionAccess().getNameSelectKeyword_0_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_2, null);
                      	    
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4384:8: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,69,FOLLOW_69_in_ruleCollectionExpression9705); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_3, grammarAccess.getCollectionExpressionAccess().getNameSelectFirstKeyword_0_0_2());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_3, null);
                      	    
                    }

                    }
                    break;
                case 4 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4396:8: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,70,FOLLOW_70_in_ruleCollectionExpression9734); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_4, grammarAccess.getCollectionExpressionAccess().getNameRejectKeyword_0_0_3());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_4, null);
                      	    
                    }

                    }
                    break;
                case 5 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4408:8: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,71,FOLLOW_71_in_ruleCollectionExpression9763); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_5, grammarAccess.getCollectionExpressionAccess().getNameExistsKeyword_0_0_4());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_5, null);
                      	    
                    }

                    }
                    break;
                case 6 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4420:8: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,72,FOLLOW_72_in_ruleCollectionExpression9792); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_6, grammarAccess.getCollectionExpressionAccess().getNameNotExistsKeyword_0_0_5());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_6, null);
                      	    
                    }

                    }
                    break;
                case 7 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4432:8: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,73,FOLLOW_73_in_ruleCollectionExpression9821); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_7, grammarAccess.getCollectionExpressionAccess().getNameSortByKeyword_0_0_6());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_7, null);
                      	    
                    }

                    }
                    break;
                case 8 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4444:8: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,74,FOLLOW_74_in_ruleCollectionExpression9850); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_name_0_8, grammarAccess.getCollectionExpressionAccess().getNameForAllKeyword_0_0_7());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "name", lv_name_0_8, null);
                      	    
                    }

                    }
                    break;

            }


            }


            }

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleCollectionExpression9878); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4463:1: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==RULE_ID) ) {
                int LA67_1 = input.LA(2);

                if ( (LA67_1==32) ) {
                    alt67=1;
                }
            }
            switch (alt67) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4463:2: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4463:2: ( (lv_var_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4464:1: (lv_var_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4464:1: (lv_var_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4465:3: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleCollectionExpression9900);
                    lv_var_2_0=ruleIdentifier();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getCollectionExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"var",
                              		lv_var_2_0, 
                              		"Identifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,32,FOLLOW_32_in_ruleCollectionExpression9912); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4485:3: ( (lv_exp_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4486:1: (lv_exp_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4486:1: (lv_exp_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4487:3: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCollectionExpression9935);
            lv_exp_4_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCollectionExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"exp",
                      		lv_exp_4_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleCollectionExpression9947); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getCollectionExpressionAccess().getRightParenthesisKeyword_4());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleCollectionExpression"


    // $ANTLR start "entryRuleType"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4515:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4516:2: (iv_ruleType= ruleType EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4517:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType9983);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType9993); if (state.failed) return current;

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
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4524:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4527:28: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4528:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4528:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( ((LA68_0>=80 && LA68_0<=82)) ) {
                alt68=1;
            }
            else if ( (LA68_0==RULE_ID) ) {
                alt68=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }
            switch (alt68) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4529:2: this_CollectionType_0= ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionType_in_ruleType10043);
                    this_CollectionType_0=ruleCollectionType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_CollectionType_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4542:2: this_SimpleType_1= ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleType_in_ruleType10073);
                    this_SimpleType_1=ruleSimpleType();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_SimpleType_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleCollectionType"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4561:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4562:2: (iv_ruleCollectionType= ruleCollectionType EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4563:2: iv_ruleCollectionType= ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionType_in_entryRuleCollectionType10108);
            iv_ruleCollectionType=ruleCollectionType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionType10118); if (state.failed) return current;

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
    // $ANTLR end "entryRuleCollectionType"


    // $ANTLR start "ruleCollectionType"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4570:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
    public final EObject ruleCollectionType() throws RecognitionException {
        EObject current = null;

        Token lv_cl_0_1=null;
        Token lv_cl_0_2=null;
        Token lv_cl_0_3=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_id1_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4573:28: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4574:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4574:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4574:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4574:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4575:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4575:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4576:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4576:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            int alt69=3;
            switch ( input.LA(1) ) {
            case 80:
                {
                alt69=1;
                }
                break;
            case 81:
                {
                alt69=2;
                }
                break;
            case 82:
                {
                alt69=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }

            switch (alt69) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4577:3: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,80,FOLLOW_80_in_ruleCollectionType10163); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_cl_0_1, grammarAccess.getCollectionTypeAccess().getClCollectionKeyword_0_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionTypeRule());
                      	        }
                             		setWithLastConsumed(current, "cl", lv_cl_0_1, null);
                      	    
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4589:8: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,81,FOLLOW_81_in_ruleCollectionType10192); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_cl_0_2, grammarAccess.getCollectionTypeAccess().getClListKeyword_0_0_1());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionTypeRule());
                      	        }
                             		setWithLastConsumed(current, "cl", lv_cl_0_2, null);
                      	    
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4601:8: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,82,FOLLOW_82_in_ruleCollectionType10221); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_cl_0_3, grammarAccess.getCollectionTypeAccess().getClSetKeyword_0_0_2());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getCollectionTypeRule());
                      	        }
                             		setWithLastConsumed(current, "cl", lv_cl_0_3, null);
                      	    
                    }

                    }
                    break;

            }


            }


            }

            otherlv_1=(Token)match(input,30,FOLLOW_30_in_ruleCollectionType10249); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4620:1: ( (lv_id1_2_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4621:1: (lv_id1_2_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4621:1: (lv_id1_2_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4622:3: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleCollectionType10270);
            lv_id1_2_0=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getCollectionTypeRule());
              	        }
                     		set(
                     			current, 
                     			"id1",
                      		lv_id1_2_0, 
                      		"SimpleType");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,31,FOLLOW_31_in_ruleCollectionType10282); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getCollectionTypeAccess().getRightSquareBracketKeyword_3());
                  
            }

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleCollectionType"


    // $ANTLR start "entryRuleSimpleType"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4650:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4651:2: (iv_ruleSimpleType= ruleSimpleType EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4652:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleType_in_entryRuleSimpleType10318);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleType10328); if (state.failed) return current;

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
    // $ANTLR end "entryRuleSimpleType"


    // $ANTLR start "ruleSimpleType"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4659:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4662:28: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4663:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4663:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4663:2: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4663:2: ( (lv_id_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4664:1: (lv_id_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4664:1: (lv_id_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4665:3: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType10374);
            lv_id_0_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getSimpleTypeRule());
              	        }
                     		add(
                     			current, 
                     			"id",
                      		lv_id_0_0, 
                      		"Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4681:2: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop70:
            do {
                int alt70=2;
                int LA70_0 = input.LA(1);

                if ( (LA70_0==42) ) {
                    alt70=1;
                }


                switch (alt70) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4681:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,42,FOLLOW_42_in_ruleSimpleType10387); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4685:1: ( (lv_id_2_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4686:1: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4686:1: (lv_id_2_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4687:3: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType10408);
            	    lv_id_2_0=ruleIdentifier();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getSimpleTypeRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"id",
            	              		lv_id_2_0, 
            	              		"Identifier");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop70;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleSimpleType"


    // $ANTLR start "entryRuleIdentifier"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4711:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4712:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4713:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier10447);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier10458); if (state.failed) return current;

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
    // $ANTLR end "entryRuleIdentifier"


    // $ANTLR start "ruleIdentifier"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4720:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4723:28: (this_ID_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4724:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIdentifier10497); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getIdentifierAccess().getIDTerminalRuleCall()); 
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleIdentifier"


    // $ANTLR start "ruleCasing"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4739:1: ruleCasing returns [Enumerator current=null] : ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) ) ;
    public final Enumerator ruleCasing() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4741:28: ( ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4742:1: ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4742:1: ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==83) ) {
                alt71=1;
            }
            else if ( (LA71_0==84) ) {
                alt71=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4742:2: (enumLiteral_0= 'sensitive' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4742:2: (enumLiteral_0= 'sensitive' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4742:4: enumLiteral_0= 'sensitive'
                    {
                    enumLiteral_0=(Token)match(input,83,FOLLOW_83_in_ruleCasing10555); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getCasingAccess().getSENSITIVEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getCasingAccess().getSENSITIVEEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4748:6: (enumLiteral_1= 'insensitive' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4748:6: (enumLiteral_1= 'insensitive' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4748:8: enumLiteral_1= 'insensitive'
                    {
                    enumLiteral_1=(Token)match(input,84,FOLLOW_84_in_ruleCasing10572); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getCasingAccess().getINSENSITIVEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_1, grammarAccess.getCasingAccess().getINSENSITIVEEnumLiteralDeclaration_1()); 
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
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
    // $ANTLR end "ruleCasing"

    // $ANTLR start synpred32_InternalScope
    public final void synpred32_InternalScope_fragment() throws RecognitionException {   
        EObject lv_names_0_0 = null;


        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1584:2: ( ( (lv_names_0_0= ruleNamingExpression ) ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1584:2: ( (lv_names_0_0= ruleNamingExpression ) )
        {
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1584:2: ( (lv_names_0_0= ruleNamingExpression ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1585:1: (lv_names_0_0= ruleNamingExpression )
        {
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1585:1: (lv_names_0_0= ruleNamingExpression )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1586:3: lv_names_0_0= ruleNamingExpression
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_0_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleNamingExpression_in_synpred32_InternalScope3360);
        lv_names_0_0=ruleNamingExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred32_InternalScope

    // $ANTLR start synpred39_InternalScope
    public final void synpred39_InternalScope_fragment() throws RecognitionException {   
        EObject this_CastedExpression_1 = null;


        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1854:2: (this_CastedExpression_1= ruleCastedExpression )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1854:2: this_CastedExpression_1= ruleCastedExpression
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleCastedExpression_in_synpred39_InternalScope4032);
        this_CastedExpression_1=ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred39_InternalScope

    // $ANTLR start synpred44_InternalScope
    public final void synpred44_InternalScope_fragment() throws RecognitionException {   
        Token otherlv_4=null;
        EObject lv_elsePart_5_0 = null;


        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2306:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2306:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
        {
        otherlv_4=(Token)match(input,50,FOLLOW_50_in_synpred44_InternalScope4994); if (state.failed) return ;
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2310:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2311:1: (lv_elsePart_5_0= ruleChainedExpression )
        {
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2311:1: (lv_elsePart_5_0= ruleChainedExpression )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2312:3: lv_elsePart_5_0= ruleChainedExpression
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleChainedExpression_in_synpred44_InternalScope5015);
        lv_elsePart_5_0=ruleChainedExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred44_InternalScope

    // Delegated rules

    public final boolean synpred32_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred39_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred39_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred44_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred44_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA30 dfa30 = new DFA30(this);
    protected DFA35 dfa35 = new DFA35(this);
    static final String DFA10_eotS =
        "\6\uffff";
    static final String DFA10_eofS =
        "\6\uffff";
    static final String DFA10_minS =
        "\1\7\1\23\1\7\2\uffff\1\23";
    static final String DFA10_maxS =
        "\1\7\1\52\1\7\2\uffff\1\52";
    static final String DFA10_acceptS =
        "\3\uffff\1\2\1\1\1\uffff";
    static final String DFA10_specialS =
        "\6\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\1",
            "\1\4\6\uffff\1\3\17\uffff\1\2",
            "\1\5",
            "",
            "",
            "\1\4\6\uffff\1\3\17\uffff\1\2"
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "507:3: ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) )";
        }
    }
    static final String DFA30_eotS =
        "\40\uffff";
    static final String DFA30_eofS =
        "\40\uffff";
    static final String DFA30_minS =
        "\1\4\3\uffff\1\0\33\uffff";
    static final String DFA30_maxS =
        "\1\122\3\uffff\1\0\33\uffff";
    static final String DFA30_acceptS =
        "\1\uffff\1\1\35\uffff\1\2";
    static final String DFA30_specialS =
        "\4\uffff\1\0\33\uffff}>";
    static final String[] DFA30_transitionS = {
            "\4\1\13\uffff\1\1\4\uffff\1\4\10\uffff\1\1\7\uffff\1\1\2\uffff"+
            "\1\1\3\uffff\1\1\2\uffff\1\1\13\uffff\1\1\1\uffff\22\1",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA30_eot = DFA.unpackEncodedString(DFA30_eotS);
    static final short[] DFA30_eof = DFA.unpackEncodedString(DFA30_eofS);
    static final char[] DFA30_min = DFA.unpackEncodedStringToUnsignedChars(DFA30_minS);
    static final char[] DFA30_max = DFA.unpackEncodedStringToUnsignedChars(DFA30_maxS);
    static final short[] DFA30_accept = DFA.unpackEncodedString(DFA30_acceptS);
    static final short[] DFA30_special = DFA.unpackEncodedString(DFA30_specialS);
    static final short[][] DFA30_transition;

    static {
        int numStates = DFA30_transitionS.length;
        DFA30_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA30_transition[i] = DFA.unpackEncodedString(DFA30_transitionS[i]);
        }
    }

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = DFA30_eot;
            this.eof = DFA30_eof;
            this.min = DFA30_min;
            this.max = DFA30_max;
            this.accept = DFA30_accept;
            this.special = DFA30_special;
            this.transition = DFA30_transition;
        }
        public String getDescription() {
            return "1584:1: ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_4 = input.LA(1);

                         
                        int index30_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred32_InternalScope()) ) {s = 1;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index30_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 30, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA35_eotS =
        "\36\uffff";
    static final String DFA35_eofS =
        "\36\uffff";
    static final String DFA35_minS =
        "\1\4\1\uffff\1\0\33\uffff";
    static final String DFA35_maxS =
        "\1\122\1\uffff\1\0\33\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String DFA35_specialS =
        "\2\uffff\1\0\33\uffff}>";
    static final String[] DFA35_transitionS = {
            "\4\3\13\uffff\1\3\4\uffff\1\2\23\uffff\1\1\3\uffff\1\3\2\uffff"+
            "\1\3\13\uffff\1\3\1\uffff\22\3",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "1840:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA35_2 = input.LA(1);

                         
                        int index35_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred39_InternalScope()) ) {s = 29;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index35_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 35, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleScopeModel_in_entryRuleScopeModel81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeModel91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleScopeModel128 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleDottedID_in_ruleScopeModel149 = new BitSet(new long[]{0x0000000000876002L});
    public static final BitSet FOLLOW_13_in_ruleScopeModel162 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleDottedID_in_ruleScopeModel189 = new BitSet(new long[]{0x0000000000874002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleScopeModel212 = new BitSet(new long[]{0x0000000000874002L});
    public static final BitSet FOLLOW_ruleExtension_in_ruleScopeModel234 = new BitSet(new long[]{0x0000000000870002L});
    public static final BitSet FOLLOW_ruleNamingSection_in_ruleScopeModel256 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_ruleScopeDefinition_in_ruleScopeModel278 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport315 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport325 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleImport362 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport386 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_ruleImport399 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleImport420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExtension_in_entryRuleExtension458 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExtension468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleExtension505 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExtension526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingSection_in_entryRuleNamingSection562 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamingSection572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleNamingSection622 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_ruleCasing_in_ruleNamingSection643 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleNamingSection657 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleNamingSection669 = new BitSet(new long[]{0x0000000000100080L});
    public static final BitSet FOLLOW_ruleNamingDefinition_in_ruleNamingSection690 = new BitSet(new long[]{0x0000000000100080L});
    public static final BitSet FOLLOW_20_in_ruleNamingSection703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingDefinition_in_entryRuleNamingDefinition739 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamingDefinition749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleNamingDefinition801 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleNamingDefinition813 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleNaming_in_ruleNamingDefinition834 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleNamingDefinition846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeDefinition_in_entryRuleScopeDefinition882 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeDefinition892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleScopeDefinition929 = new BitSet(new long[]{0x0000000001000080L});
    public static final BitSet FOLLOW_24_in_ruleScopeDefinition942 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleScopeDefinition963 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleScopeDefinition975 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1005 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1039 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleScopeDefinition1051 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleScopeDefinition1078 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleScopeDefinition1092 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_ruleScopeRule_in_ruleScopeDefinition1113 = new BitSet(new long[]{0x0000000008100000L});
    public static final BitSet FOLLOW_20_in_ruleScopeDefinition1126 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeRule_in_entryRuleScopeRule1162 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeRule1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleScopeRule1209 = new BitSet(new long[]{0x0000000020000080L});
    public static final BitSet FOLLOW_ruleScopeContext_in_ruleScopeRule1230 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleScopeRule1242 = new BitSet(new long[]{0x80091216010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleScopeExpression_in_ruleScopeRule1263 = new BitSet(new long[]{0x0000000010400000L});
    public static final BitSet FOLLOW_28_in_ruleScopeRule1276 = new BitSet(new long[]{0x80091216010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleScopeExpression_in_ruleScopeRule1297 = new BitSet(new long[]{0x0000000010400000L});
    public static final BitSet FOLLOW_22_in_ruleScopeRule1311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeContext_in_entryRuleScopeContext1347 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeContext1357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleScopeContext1401 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleScopeContext1447 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleScopeContext1461 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleScopeContext1482 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleScopeContext1494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeExpression_in_entryRuleScopeExpression1532 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeExpression1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeDelegation_in_ruleScopeExpression1593 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_ruleFactoryExpression_in_ruleScopeExpression1623 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_ruleNamedScopeExpression_in_ruleScopeExpression1653 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32_in_ruleScopeExpression1666 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleScopeExpression1687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFactoryExpression_in_entryRuleFactoryExpression1725 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFactoryExpression1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleFactoryExpression1772 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleFactoryExpression1793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeDelegation_in_entryRuleScopeDelegation1829 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeDelegation1839 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleScopeDelegation1876 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleScopeDelegation1888 = new BitSet(new long[]{0x80091212010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleScopeDelegation1910 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_ruleGlobalScopeExpression_in_ruleScopeDelegation1937 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleScopeDelegation1951 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleScopeDelegation1978 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleScopeDelegation1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamedScopeExpression_in_entryRuleNamedScopeExpression2028 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamedScopeExpression2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalScopeExpression_in_ruleNamedScopeExpression2089 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_ruleSimpleScopeExpression_in_ruleNamedScopeExpression2119 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_17_in_ruleNamedScopeExpression2138 = new BitSet(new long[]{0x0000000000000000L,0x0000000000180000L});
    public static final BitSet FOLLOW_ruleCasing_in_ruleNamedScopeExpression2172 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_ruleNamedScopeExpression2187 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleNaming_in_ruleNamedScopeExpression2208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalScopeExpression_in_entryRuleGlobalScopeExpression2246 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalScopeExpression2256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleGlobalScopeExpression2293 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleGlobalScopeExpression2305 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleGlobalScopeExpression2332 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleGlobalScopeExpression2346 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_ruleGlobalScopeExpression2358 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleGlobalScopeExpression2370 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2391 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleGlobalScopeExpression2411 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleGlobalScopeExpression2423 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleGlobalScopeExpression2435 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2456 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleGlobalScopeExpression2472 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_39_in_ruleGlobalScopeExpression2484 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleGlobalScopeExpression2496 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleGlobalScopeExpression2508 = new BitSet(new long[]{0x0000000040000080L});
    public static final BitSet FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2529 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleGlobalScopeExpression2542 = new BitSet(new long[]{0x0000000040000080L});
    public static final BitSet FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2563 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_25_in_ruleGlobalScopeExpression2577 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleGlobalScopeExpression2592 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleGlobalScopeExpression2604 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleGlobalScopeExpression2616 = new BitSet(new long[]{0x0000000021000080L});
    public static final BitSet FOLLOW_29_in_ruleGlobalScopeExpression2635 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2675 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_24_in_ruleGlobalScopeExpression2694 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2715 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleGlobalScopeExpression2728 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2749 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_25_in_ruleGlobalScopeExpression2763 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleGlobalScopeExpression2779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataExpression_in_entryRuleDataExpression2815 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataExpression2825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMatchDataExpression_in_ruleDataExpression2875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLambdaDataExpression_in_ruleDataExpression2905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMatchDataExpression_in_entryRuleMatchDataExpression2940 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMatchDataExpression2950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleMatchDataExpression2996 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleMatchDataExpression3008 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleMatchDataExpression3029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLambdaDataExpression_in_entryRuleLambdaDataExpression3065 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLambdaDataExpression3075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleLambdaDataExpression3112 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLambdaDataExpression3133 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleLambdaDataExpression3145 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLambdaDataExpression3166 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleLambdaDataExpression3178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleScopeExpression_in_entryRuleSimpleScopeExpression3214 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleScopeExpression3224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSimpleScopeExpression3269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNaming_in_entryRuleNaming3304 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNaming3314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingExpression_in_ruleNaming3360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleNaming3379 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleNamingExpression_in_ruleNaming3400 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleNaming3413 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleNamingExpression_in_ruleNaming3434 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_25_in_ruleNaming3448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingExpression_in_entryRuleNamingExpression3485 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamingExpression3495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleNamingExpression3538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleNamingExpression3576 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleNamingExpression3611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3649 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID3660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleQualifiedID3707 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_ruleQualifiedID3726 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleQualifiedID3748 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_ruleDottedID_in_entryRuleDottedID3796 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDottedID3807 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleDottedID3854 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_ruleDottedID3873 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleDottedID3895 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression3942 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression3952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_ruleExpression4002 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_ruleExpression4032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_ruleExpression4062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_entryRuleLetExpression4099 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetExpression4109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleLetExpression4146 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLetExpression4167 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleLetExpression4179 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression4200 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleLetExpression4212 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression4233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression4269 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCastedExpression4279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleCastedExpression4316 = new BitSet(new long[]{0x0000000000000080L,0x0000000000070000L});
    public static final BitSet FOLLOW_ruleType_in_ruleCastedExpression4337 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleCastedExpression4349 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCastedExpression4370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_entryRuleChainExpression4406 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainExpression4416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression4466 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_46_in_ruleChainExpression4490 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression4511 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression4549 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainedExpression4559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression4609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression4639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_ruleChainedExpression4669 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri4704 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionTri4714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleIfExpressionTri4764 = new BitSet(new long[]{0x0000800000000002L});
    public static final BitSet FOLLOW_47_in_ruleIfExpressionTri4788 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri4809 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleIfExpressionTri4821 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri4842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw4880 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionKw4890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleIfExpressionKw4927 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw4948 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49_in_ruleIfExpressionKw4960 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw4981 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50_in_ruleIfExpressionKw4994 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression5053 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchExpression5063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleSwitchExpression5100 = new BitSet(new long[]{0x0000000001080000L});
    public static final BitSet FOLLOW_24_in_ruleSwitchExpression5113 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression5134 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleSwitchExpression5146 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleSwitchExpression5160 = new BitSet(new long[]{0x0010000000020000L});
    public static final BitSet FOLLOW_ruleCase_in_ruleSwitchExpression5181 = new BitSet(new long[]{0x0010000000020000L});
    public static final BitSet FOLLOW_52_in_ruleSwitchExpression5194 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleSwitchExpression5206 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression5227 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleSwitchExpression5239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCase_in_entryRuleCase5275 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCase5285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleCase5322 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase5343 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleCase5355 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase5376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression5412 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression5422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression5472 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_53_in_ruleOrExpression5502 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression5536 = new BitSet(new long[]{0x0020000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression5574 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression5584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression5634 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_54_in_ruleAndExpression5664 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression5698 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression5736 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpression5746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression5796 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_55_in_ruleImpliesExpression5826 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression5860 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression5898 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression5908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression5958 = new BitSet(new long[]{0x3F00000000000002L});
    public static final BitSet FOLLOW_56_in_ruleRelationalExpression5990 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_57_in_ruleRelationalExpression6019 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_58_in_ruleRelationalExpression6048 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_59_in_ruleRelationalExpression6077 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_60_in_ruleRelationalExpression6106 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_61_in_ruleRelationalExpression6135 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression6172 = new BitSet(new long[]{0x3F00000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression6210 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression6220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6270 = new BitSet(new long[]{0xC000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleAdditiveExpression6302 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_63_in_ruleAdditiveExpression6331 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6368 = new BitSet(new long[]{0xC000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression6406 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression6416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6466 = new BitSet(new long[]{0x0000000020000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_29_in_ruleMultiplicativeExpression6498 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_64_in_ruleMultiplicativeExpression6527 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6564 = new BitSet(new long[]{0x0000000020000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression6602 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression6612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression6662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression6692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression6727 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression6737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleUnaryExpression6782 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_63_in_ruleUnaryExpression6811 = new BitSet(new long[]{0x80000000010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression6848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression6884 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression6894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression6944 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_ruleInfixExpression6969 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression6990 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleInfixExpression7002 = new BitSet(new long[]{0x80091202030800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression7024 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleInfixExpression7037 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression7058 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression7074 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_ruleInfixExpression7106 = new BitSet(new long[]{0x0000000000000080L,0x0000000000070000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression7127 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_ruleInfixExpression7159 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_66_in_ruleInfixExpression7177 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleInfixExpression7202 = new BitSet(new long[]{0x0000000000000080L,0x0000000000070000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression7223 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression7235 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_ruleInfixExpression7267 = new BitSet(new long[]{0x0000000000000000L,0x00000000000007F8L});
    public static final BitSet FOLLOW_67_in_ruleInfixExpression7287 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_68_in_ruleInfixExpression7316 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_69_in_ruleInfixExpression7345 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_70_in_ruleInfixExpression7374 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_71_in_ruleInfixExpression7403 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_72_in_ruleInfixExpression7432 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_73_in_ruleInfixExpression7461 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_74_in_ruleInfixExpression7490 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleInfixExpression7518 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression7540 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleInfixExpression7552 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression7575 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression7587 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression7626 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression7636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression7686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_rulePrimaryExpression7716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_rulePrimaryExpression7746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression7776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression7806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression7836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral7871 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral7881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral7931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral7961 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral7991 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_ruleLiteral8021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral8051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral8086 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral8096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleBooleanLiteral8140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleBooleanLiteral8169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral8220 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral8230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral8271 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral8311 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral8321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleNullLiteral8363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral8411 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealLiteral8421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_in_ruleRealLiteral8462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral8502 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral8512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral8553 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression8593 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression8603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleParanthesizedExpression8640 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression8665 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleParanthesizedExpression8676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression8712 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression8722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleGlobalVarExpression8759 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression8780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall8816 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureCall8826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_ruleFeatureCall8876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_ruleFeatureCall8902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_ruleFeatureCall8933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall8963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_entryRuleOperationCall8998 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCall9008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleOperationCall9054 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleOperationCall9066 = new BitSet(new long[]{0x80091202030800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall9088 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_35_in_ruleOperationCall9101 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall9122 = new BitSet(new long[]{0x0000000802000000L});
    public static final BitSet FOLLOW_25_in_ruleOperationCall9138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_entryRuleListLiteral9174 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListLiteral9184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleListLiteral9233 = new BitSet(new long[]{0x80091202011800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral9255 = new BitSet(new long[]{0x0000000800100000L});
    public static final BitSet FOLLOW_35_in_ruleListLiteral9268 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral9289 = new BitSet(new long[]{0x0000000800100000L});
    public static final BitSet FOLLOW_20_in_ruleListLiteral9305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression9341 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstructorCallExpression9351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleConstructorCallExpression9388 = new BitSet(new long[]{0x0000000000000080L,0x0000000000070000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression9409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression9445 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTypeSelectExpression9455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_ruleTypeSelectExpression9498 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleTypeSelectExpression9523 = new BitSet(new long[]{0x0000000000000080L,0x0000000000070000L});
    public static final BitSet FOLLOW_ruleType_in_ruleTypeSelectExpression9544 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleTypeSelectExpression9556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression9592 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionExpression9602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_ruleCollectionExpression9647 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_68_in_ruleCollectionExpression9676 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_69_in_ruleCollectionExpression9705 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_70_in_ruleCollectionExpression9734 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_71_in_ruleCollectionExpression9763 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_72_in_ruleCollectionExpression9792 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_73_in_ruleCollectionExpression9821 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_74_in_ruleCollectionExpression9850 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleCollectionExpression9878 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleCollectionExpression9900 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleCollectionExpression9912 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCollectionExpression9935 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleCollectionExpression9947 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType9983 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType9993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_ruleType10043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleType10073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_entryRuleCollectionType10108 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionType10118 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleCollectionType10163 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_81_in_ruleCollectionType10192 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_82_in_ruleCollectionType10221 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleCollectionType10249 = new BitSet(new long[]{0x0000000000000080L,0x0000000000070000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleCollectionType10270 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleCollectionType10282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_entryRuleSimpleType10318 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleType10328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType10374 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_ruleSimpleType10387 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType10408 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier10447 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier10458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIdentifier10497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ruleCasing10555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_ruleCasing10572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingExpression_in_synpred32_InternalScope3360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_synpred39_InternalScope4032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_synpred44_InternalScope4994 = new BitSet(new long[]{0x80091202010800F0L,0x000000000007FFFEL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_synpred44_InternalScope5015 = new BitSet(new long[]{0x0000000000000002L});

}