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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_INT", "RULE_REAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'scoping'", "'with'", "'import'", "'as'", "'extension'", "'inject'", "'case'", "'naming'", "'{'", "'}'", "'='", "';'", "'scope'", "'('", "')'", "'#'", "'context'", "'>>'", "'*'", "'['", "']'", "'|'", "'factory'", "'scopeof'", "','", "'find'", "'key'", "'recursive'", "'prefix'", "'data'", "'domains'", "'export'", "'::'", "'.'", "'let'", "':'", "'->'", "'?'", "'if'", "'then'", "'else'", "'switch'", "'default'", "'||'", "'&&'", "'implies'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'/'", "'!'", "'typeSelect'", "'collect'", "'select'", "'selectFirst'", "'reject'", "'exists'", "'notExists'", "'sortBy'", "'forAll'", "'true'", "'false'", "'null'", "'GLOBALVAR'", "'new'", "'Collection'", "'List'", "'Set'", "'sensitive'", "'insensitive'"
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
    public static final int T__85=85;
    public static final int T__42=42;
    public static final int T__86=86;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:83:1: ruleScopeModel returns [EObject current=null] : (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* ) ;
    public final EObject ruleScopeModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_imports_4_0 = null;

        EObject lv_extensions_5_0 = null;

        EObject lv_injections_6_0 = null;

        EObject lv_naming_7_0 = null;

        EObject lv_scopes_8_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:86:28: ( (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:87:1: (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:87:1: (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:87:3: otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )*
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:167:3: ( (lv_injections_6_0= ruleInjection ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:168:1: (lv_injections_6_0= ruleInjection )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:168:1: (lv_injections_6_0= ruleInjection )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:169:3: lv_injections_6_0= ruleInjection
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getInjectionsInjectionParserRuleCall_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleInjection_in_ruleScopeModel256);
            	    lv_injections_6_0=ruleInjection();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"injections",
            	              		lv_injections_6_0, 
            	              		"Injection");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:185:3: ( (lv_naming_7_0= ruleNamingSection ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=18 && LA5_0<=19)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:186:1: (lv_naming_7_0= ruleNamingSection )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:186:1: (lv_naming_7_0= ruleNamingSection )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:187:3: lv_naming_7_0= ruleNamingSection
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeModelAccess().getNamingNamingSectionParserRuleCall_6_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNamingSection_in_ruleScopeModel278);
                    lv_naming_7_0=ruleNamingSection();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
                      	        }
                             		set(
                             			current, 
                             			"naming",
                              		lv_naming_7_0, 
                              		"NamingSection");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:203:3: ( (lv_scopes_8_0= ruleScopeDefinition ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==24) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:204:1: (lv_scopes_8_0= ruleScopeDefinition )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:204:1: (lv_scopes_8_0= ruleScopeDefinition )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:205:3: lv_scopes_8_0= ruleScopeDefinition
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getScopesScopeDefinitionParserRuleCall_7_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleScopeDefinition_in_ruleScopeModel300);
            	    lv_scopes_8_0=ruleScopeDefinition();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getScopeModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"scopes",
            	              		lv_scopes_8_0, 
            	              		"ScopeDefinition");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:229:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:230:2: (iv_ruleImport= ruleImport EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:231:2: iv_ruleImport= ruleImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportRule()); 
            }
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport337);
            iv_ruleImport=ruleImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport347); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:238:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:241:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:242:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:242:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:242:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )?
            {
            otherlv_0=(Token)match(input,14,FOLLOW_14_in_ruleImport384); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:246:1: ( (otherlv_1= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:247:1: (otherlv_1= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:247:1: (otherlv_1= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:248:3: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getImportRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImport408); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
              	
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:262:2: (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:262:4: otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) )
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_15_in_ruleImport421); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportAccess().getAsKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:266:1: ( (lv_name_3_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:267:1: (lv_name_3_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:267:1: (lv_name_3_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:268:3: lv_name_3_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImportAccess().getNameIdentifierParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleImport442);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:292:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:293:2: (iv_ruleExtension= ruleExtension EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:294:2: iv_ruleExtension= ruleExtension EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExtensionRule()); 
            }
            pushFollow(FOLLOW_ruleExtension_in_entryRuleExtension480);
            iv_ruleExtension=ruleExtension();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExtension; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExtension490); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:301:1: ruleExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_extension_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:304:28: ( (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:305:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:305:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:305:3: otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_16_in_ruleExtension527); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExtensionAccess().getExtensionKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:309:1: ( (lv_extension_1_0= ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:310:1: (lv_extension_1_0= ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:310:1: (lv_extension_1_0= ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:311:3: lv_extension_1_0= ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExtensionAccess().getExtensionQualifiedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExtension548);
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


    // $ANTLR start "entryRuleInjection"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:335:1: entryRuleInjection returns [EObject current=null] : iv_ruleInjection= ruleInjection EOF ;
    public final EObject entryRuleInjection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInjection = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:336:2: (iv_ruleInjection= ruleInjection EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:337:2: iv_ruleInjection= ruleInjection EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInjectionRule()); 
            }
            pushFollow(FOLLOW_ruleInjection_in_entryRuleInjection584);
            iv_ruleInjection=ruleInjection();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInjection; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInjection594); if (state.failed) return current;

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
    // $ANTLR end "entryRuleInjection"


    // $ANTLR start "ruleInjection"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:344:1: ruleInjection returns [EObject current=null] : (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) ) ;
    public final EObject ruleInjection() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_type_1_0 = null;

        AntlrDatatypeRuleToken lv_name_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:347:28: ( (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:348:1: (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:348:1: (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:348:3: otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleInjection631); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInjectionAccess().getInjectKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:352:1: ( (lv_type_1_0= ruleDottedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:353:1: (lv_type_1_0= ruleDottedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:353:1: (lv_type_1_0= ruleDottedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:354:3: lv_type_1_0= ruleDottedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInjectionAccess().getTypeDottedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleDottedID_in_ruleInjection652);
            lv_type_1_0=ruleDottedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getInjectionRule());
              	        }
                     		set(
                     			current, 
                     			"type",
                      		lv_type_1_0, 
                      		"DottedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_15_in_ruleInjection664); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getInjectionAccess().getAsKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:374:1: ( (lv_name_3_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:375:1: (lv_name_3_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:375:1: (lv_name_3_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:376:3: lv_name_3_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInjectionAccess().getNameIdentifierParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleInjection685);
            lv_name_3_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getInjectionRule());
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
    // $ANTLR end "ruleInjection"


    // $ANTLR start "entryRuleNamingSection"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:400:1: entryRuleNamingSection returns [EObject current=null] : iv_ruleNamingSection= ruleNamingSection EOF ;
    public final EObject entryRuleNamingSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingSection = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:401:2: (iv_ruleNamingSection= ruleNamingSection EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:402:2: iv_ruleNamingSection= ruleNamingSection EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingSectionRule()); 
            }
            pushFollow(FOLLOW_ruleNamingSection_in_entryRuleNamingSection721);
            iv_ruleNamingSection=ruleNamingSection();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingSection; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamingSection731); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:409:1: ruleNamingSection returns [EObject current=null] : ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:412:28: ( ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:413:1: ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:413:1: ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:413:2: () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:413:2: ()
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:414:2: 
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:422:2: (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==18) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:422:4: otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) )
                    {
                    otherlv_1=(Token)match(input,18,FOLLOW_18_in_ruleNamingSection781); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNamingSectionAccess().getCaseKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:426:1: ( (lv_casing_2_0= ruleCasing ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:427:1: (lv_casing_2_0= ruleCasing )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:427:1: (lv_casing_2_0= ruleCasing )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:428:3: lv_casing_2_0= ruleCasing
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingSectionAccess().getCasingCasingEnumRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleCasing_in_ruleNamingSection802);
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

            otherlv_3=(Token)match(input,19,FOLLOW_19_in_ruleNamingSection816); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getNamingSectionAccess().getNamingKeyword_2());
                  
            }
            otherlv_4=(Token)match(input,20,FOLLOW_20_in_ruleNamingSection828); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getNamingSectionAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:452:1: ( (lv_namings_5_0= ruleNamingDefinition ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:453:1: (lv_namings_5_0= ruleNamingDefinition )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:453:1: (lv_namings_5_0= ruleNamingDefinition )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:454:3: lv_namings_5_0= ruleNamingDefinition
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getNamingSectionAccess().getNamingsNamingDefinitionParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleNamingDefinition_in_ruleNamingSection849);
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
            	    break loop9;
                }
            } while (true);

            otherlv_6=(Token)match(input,21,FOLLOW_21_in_ruleNamingSection862); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:482:1: entryRuleNamingDefinition returns [EObject current=null] : iv_ruleNamingDefinition= ruleNamingDefinition EOF ;
    public final EObject entryRuleNamingDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingDefinition = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:483:2: (iv_ruleNamingDefinition= ruleNamingDefinition EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:484:2: iv_ruleNamingDefinition= ruleNamingDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleNamingDefinition_in_entryRuleNamingDefinition898);
            iv_ruleNamingDefinition=ruleNamingDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamingDefinition908); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:491:1: ruleNamingDefinition returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' ) ;
    public final EObject ruleNamingDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_naming_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:494:28: ( ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:495:1: ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:495:1: ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:495:2: ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:495:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:496:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:496:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:497:3: ruleQualifiedID
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
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleNamingDefinition960);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleNamingDefinition972); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNamingDefinitionAccess().getEqualsSignKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:517:1: ( (lv_naming_2_0= ruleNaming ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:518:1: (lv_naming_2_0= ruleNaming )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:518:1: (lv_naming_2_0= ruleNaming )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:519:3: lv_naming_2_0= ruleNaming
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNamingDefinitionAccess().getNamingNamingParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleNaming_in_ruleNamingDefinition993);
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

            otherlv_3=(Token)match(input,23,FOLLOW_23_in_ruleNamingDefinition1005); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:547:1: entryRuleScopeDefinition returns [EObject current=null] : iv_ruleScopeDefinition= ruleScopeDefinition EOF ;
    public final EObject entryRuleScopeDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeDefinition = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:548:2: (iv_ruleScopeDefinition= ruleScopeDefinition EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:549:2: iv_ruleScopeDefinition= ruleScopeDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeDefinitionRule()); 
            }
            pushFollow(FOLLOW_ruleScopeDefinition_in_entryRuleScopeDefinition1041);
            iv_ruleScopeDefinition=ruleScopeDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeDefinition; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeDefinition1051); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:556:1: ruleScopeDefinition returns [EObject current=null] : (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:559:28: ( (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:560:1: (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:560:1: (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:560:3: otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleScopeDefinition1088); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeDefinitionAccess().getScopeKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:564:1: (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==25) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:564:3: otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleScopeDefinition1101); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getScopeDefinitionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:568:1: ( (lv_name_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:569:1: (lv_name_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:569:1: (lv_name_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:570:3: lv_name_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getNameIdentifierParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleScopeDefinition1122);
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

                    otherlv_3=(Token)match(input,26,FOLLOW_26_in_ruleScopeDefinition1134); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getScopeDefinitionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:590:3: ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:590:4: ( ( ruleQualifiedID ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:590:4: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:591:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:591:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:592:3: ruleQualifiedID
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
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1164);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:609:6: ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:609:6: ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:609:7: ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:609:7: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:610:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:610:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:611:3: ruleQualifiedID
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
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1198);
                    ruleQualifiedID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_6=(Token)match(input,27,FOLLOW_27_in_ruleScopeDefinition1210); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getScopeDefinitionAccess().getNumberSignKeyword_2_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:631:1: ( ( ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:632:1: ( ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:632:1: ( ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:633:3: ruleIdentifier
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
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleScopeDefinition1237);
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

            otherlv_8=(Token)match(input,20,FOLLOW_20_in_ruleScopeDefinition1251); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getScopeDefinitionAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:653:1: ( (lv_rules_9_0= ruleScopeRule ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==28) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:654:1: (lv_rules_9_0= ruleScopeRule )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:654:1: (lv_rules_9_0= ruleScopeRule )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:655:3: lv_rules_9_0= ruleScopeRule
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getRulesScopeRuleParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleScopeRule_in_ruleScopeDefinition1272);
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
            	    if ( cnt12 >= 1 ) break loop12;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            otherlv_10=(Token)match(input,21,FOLLOW_21_in_ruleScopeDefinition1285); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:683:1: entryRuleScopeRule returns [EObject current=null] : iv_ruleScopeRule= ruleScopeRule EOF ;
    public final EObject entryRuleScopeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeRule = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:684:2: (iv_ruleScopeRule= ruleScopeRule EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:685:2: iv_ruleScopeRule= ruleScopeRule EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeRuleRule()); 
            }
            pushFollow(FOLLOW_ruleScopeRule_in_entryRuleScopeRule1321);
            iv_ruleScopeRule=ruleScopeRule();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeRule; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeRule1331); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:692:1: ruleScopeRule returns [EObject current=null] : (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:695:28: ( (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:696:1: (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:696:1: (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:696:3: otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_ruleScopeRule1368); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeRuleAccess().getContextKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:700:1: ( (lv_context_1_0= ruleScopeContext ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:701:1: (lv_context_1_0= ruleScopeContext )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:701:1: (lv_context_1_0= ruleScopeContext )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:702:3: lv_context_1_0= ruleScopeContext
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeRuleAccess().getContextScopeContextParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleScopeContext_in_ruleScopeRule1389);
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

            otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleScopeRule1401); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getScopeRuleAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:722:1: ( (lv_exprs_3_0= ruleScopeExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:723:1: (lv_exprs_3_0= ruleScopeExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:723:1: (lv_exprs_3_0= ruleScopeExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:724:3: lv_exprs_3_0= ruleScopeExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeRuleAccess().getExprsScopeExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleScopeExpression_in_ruleScopeRule1422);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:740:2: (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==29) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:740:4: otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) )
            	    {
            	    otherlv_4=(Token)match(input,29,FOLLOW_29_in_ruleScopeRule1435); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getScopeRuleAccess().getGreaterThanSignGreaterThanSignKeyword_4_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:744:1: ( (lv_exprs_5_0= ruleScopeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:745:1: (lv_exprs_5_0= ruleScopeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:745:1: (lv_exprs_5_0= ruleScopeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:746:3: lv_exprs_5_0= ruleScopeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeRuleAccess().getExprsScopeExpressionParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleScopeExpression_in_ruleScopeRule1456);
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
            	    break loop13;
                }
            } while (true);

            otherlv_6=(Token)match(input,23,FOLLOW_23_in_ruleScopeRule1470); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:774:1: entryRuleScopeContext returns [EObject current=null] : iv_ruleScopeContext= ruleScopeContext EOF ;
    public final EObject entryRuleScopeContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeContext = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:775:2: (iv_ruleScopeContext= ruleScopeContext EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:776:2: iv_ruleScopeContext= ruleScopeContext EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeContextRule()); 
            }
            pushFollow(FOLLOW_ruleScopeContext_in_entryRuleScopeContext1506);
            iv_ruleScopeContext=ruleScopeContext();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeContext; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeContext1516); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:783:1: ruleScopeContext returns [EObject current=null] : ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? ) ;
    public final EObject ruleScopeContext() throws RecognitionException {
        EObject current = null;

        Token lv_global_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_guard_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:786:28: ( ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:787:1: ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:787:1: ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:787:2: ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )?
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:787:2: ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==30) ) {
                alt14=1;
            }
            else if ( (LA14_0==RULE_ID) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:787:3: ( (lv_global_0_0= '*' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:787:3: ( (lv_global_0_0= '*' ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:788:1: (lv_global_0_0= '*' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:788:1: (lv_global_0_0= '*' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:789:3: lv_global_0_0= '*'
                    {
                    lv_global_0_0=(Token)match(input,30,FOLLOW_30_in_ruleScopeContext1560); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:803:6: ( ( ruleQualifiedID ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:803:6: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:804:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:804:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:805:3: ruleQualifiedID
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
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleScopeContext1606);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:821:3: (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==31) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:821:5: otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']'
                    {
                    otherlv_2=(Token)match(input,31,FOLLOW_31_in_ruleScopeContext1620); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getScopeContextAccess().getLeftSquareBracketKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:825:1: ( (lv_guard_3_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:826:1: (lv_guard_3_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:826:1: (lv_guard_3_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:827:3: lv_guard_3_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeContextAccess().getGuardExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleScopeContext1641);
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

                    otherlv_4=(Token)match(input,32,FOLLOW_32_in_ruleScopeContext1653); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:855:1: entryRuleScopeExpression returns [EObject current=null] : iv_ruleScopeExpression= ruleScopeExpression EOF ;
    public final EObject entryRuleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:856:2: (iv_ruleScopeExpression= ruleScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:857:2: iv_ruleScopeExpression= ruleScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleScopeExpression_in_entryRuleScopeExpression1691);
            iv_ruleScopeExpression=ruleScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeExpression1701); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:864:1: ruleScopeExpression returns [EObject current=null] : ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? ) ;
    public final EObject ruleScopeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        EObject this_ScopeDelegation_0 = null;

        EObject this_FactoryExpression_1 = null;

        EObject this_NamedScopeExpression_2 = null;

        EObject lv_prune_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:867:28: ( ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:868:1: ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:868:1: ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:868:2: (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )?
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:868:2: (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression )
            int alt16=3;
            switch ( input.LA(1) ) {
            case 35:
                {
                alt16=1;
                }
                break;
            case 34:
                {
                alt16=2;
                }
                break;
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case RULE_ID:
            case 20:
            case 25:
            case 37:
            case 46:
            case 50:
            case 53:
            case 65:
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
            case 83:
            case 84:
                {
                alt16=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:869:2: this_ScopeDelegation_0= ruleScopeDelegation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getScopeDelegationParserRuleCall_0_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleScopeDelegation_in_ruleScopeExpression1752);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:882:2: this_FactoryExpression_1= ruleFactoryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getFactoryExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFactoryExpression_in_ruleScopeExpression1782);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:895:2: this_NamedScopeExpression_2= ruleNamedScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getNamedScopeExpressionParserRuleCall_0_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNamedScopeExpression_in_ruleScopeExpression1812);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:906:2: (otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==33) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:906:4: otherlv_3= '|' ( (lv_prune_4_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,33,FOLLOW_33_in_ruleScopeExpression1825); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getScopeExpressionAccess().getVerticalLineKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:910:1: ( (lv_prune_4_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:911:1: (lv_prune_4_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:911:1: (lv_prune_4_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:912:3: lv_prune_4_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeExpressionAccess().getPruneExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleScopeExpression1846);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:936:1: entryRuleFactoryExpression returns [EObject current=null] : iv_ruleFactoryExpression= ruleFactoryExpression EOF ;
    public final EObject entryRuleFactoryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFactoryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:937:2: (iv_ruleFactoryExpression= ruleFactoryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:938:2: iv_ruleFactoryExpression= ruleFactoryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFactoryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleFactoryExpression_in_entryRuleFactoryExpression1884);
            iv_ruleFactoryExpression=ruleFactoryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFactoryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFactoryExpression1894); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:945:1: ruleFactoryExpression returns [EObject current=null] : (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) ) ;
    public final EObject ruleFactoryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expr_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:948:28: ( (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:949:1: (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:949:1: (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:949:3: otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,34,FOLLOW_34_in_ruleFactoryExpression1931); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getFactoryExpressionAccess().getFactoryKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:953:1: ( (lv_expr_1_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:954:1: (lv_expr_1_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:954:1: (lv_expr_1_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:955:3: lv_expr_1_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFactoryExpressionAccess().getExprExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleFactoryExpression1952);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:979:1: entryRuleScopeDelegation returns [EObject current=null] : iv_ruleScopeDelegation= ruleScopeDelegation EOF ;
    public final EObject entryRuleScopeDelegation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeDelegation = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:980:2: (iv_ruleScopeDelegation= ruleScopeDelegation EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:981:2: iv_ruleScopeDelegation= ruleScopeDelegation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeDelegationRule()); 
            }
            pushFollow(FOLLOW_ruleScopeDelegation_in_entryRuleScopeDelegation1988);
            iv_ruleScopeDelegation=ruleScopeDelegation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeDelegation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleScopeDelegation1998); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:988:1: ruleScopeDelegation returns [EObject current=null] : (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:991:28: ( (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:992:1: (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:992:1: (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:992:3: otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')'
            {
            otherlv_0=(Token)match(input,35,FOLLOW_35_in_ruleScopeDelegation2035); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeDelegationAccess().getScopeofKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleScopeDelegation2047); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getScopeDelegationAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1000:1: ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=RULE_STRING && LA18_0<=RULE_ID)||LA18_0==20||LA18_0==25||LA18_0==46||LA18_0==50||LA18_0==53||LA18_0==65||(LA18_0>=67 && LA18_0<=84)) ) {
                alt18=1;
            }
            else if ( (LA18_0==37) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1000:2: ( (lv_delegate_2_0= ruleExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1000:2: ( (lv_delegate_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1001:1: (lv_delegate_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1001:1: (lv_delegate_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1002:3: lv_delegate_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getDelegateExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleScopeDelegation2069);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1019:6: ( (lv_external_3_0= ruleGlobalScopeExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1019:6: ( (lv_external_3_0= ruleGlobalScopeExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1020:1: (lv_external_3_0= ruleGlobalScopeExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1020:1: (lv_external_3_0= ruleGlobalScopeExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1021:3: lv_external_3_0= ruleGlobalScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getExternalGlobalScopeExpressionParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleGlobalScopeExpression_in_ruleScopeDelegation2096);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1037:3: (otherlv_4= ',' ( ( ruleIdentifier ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==36) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1037:5: otherlv_4= ',' ( ( ruleIdentifier ) )
                    {
                    otherlv_4=(Token)match(input,36,FOLLOW_36_in_ruleScopeDelegation2110); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getScopeDelegationAccess().getCommaKeyword_3_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1041:1: ( ( ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1042:1: ( ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1042:1: ( ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1043:3: ruleIdentifier
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
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleScopeDelegation2137);
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

            otherlv_6=(Token)match(input,26,FOLLOW_26_in_ruleScopeDelegation2151); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1071:1: entryRuleNamedScopeExpression returns [EObject current=null] : iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF ;
    public final EObject entryRuleNamedScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamedScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1072:2: (iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1073:2: iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamedScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleNamedScopeExpression_in_entryRuleNamedScopeExpression2187);
            iv_ruleNamedScopeExpression=ruleNamedScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamedScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamedScopeExpression2197); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1080:1: ruleNamedScopeExpression returns [EObject current=null] : ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1083:28: ( ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1084:1: ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1084:1: ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1084:2: (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )?
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1084:2: (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==37) ) {
                alt20=1;
            }
            else if ( ((LA20_0>=RULE_STRING && LA20_0<=RULE_ID)||LA20_0==20||LA20_0==25||LA20_0==46||LA20_0==50||LA20_0==53||LA20_0==65||(LA20_0>=67 && LA20_0<=84)) ) {
                alt20=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1085:2: this_GlobalScopeExpression_0= ruleGlobalScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getGlobalScopeExpressionParserRuleCall_0_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalScopeExpression_in_ruleNamedScopeExpression2248);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1098:2: this_SimpleScopeExpression_1= ruleSimpleScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getSimpleScopeExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleScopeExpression_in_ruleNamedScopeExpression2278);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1109:2: ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==18) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1109:3: ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1109:3: ( (lv_caseDef_2_0= 'case' ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1110:1: (lv_caseDef_2_0= 'case' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1110:1: (lv_caseDef_2_0= 'case' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1111:3: lv_caseDef_2_0= 'case'
                    {
                    lv_caseDef_2_0=(Token)match(input,18,FOLLOW_18_in_ruleNamedScopeExpression2297); if (state.failed) return current;
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

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1124:2: ( (lv_casing_3_0= ruleCasing ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1125:1: (lv_casing_3_0= ruleCasing )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1125:1: (lv_casing_3_0= ruleCasing )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1126:3: lv_casing_3_0= ruleCasing
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getCasingCasingEnumRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleCasing_in_ruleNamedScopeExpression2331);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1142:4: (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==15) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1142:6: otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) )
                    {
                    otherlv_4=(Token)match(input,15,FOLLOW_15_in_ruleNamedScopeExpression2346); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getNamedScopeExpressionAccess().getAsKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1146:1: ( (lv_naming_5_0= ruleNaming ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1147:1: (lv_naming_5_0= ruleNaming )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1147:1: (lv_naming_5_0= ruleNaming )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1148:3: lv_naming_5_0= ruleNaming
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getNamingNamingParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNaming_in_ruleNamedScopeExpression2367);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1172:1: entryRuleGlobalScopeExpression returns [EObject current=null] : iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF ;
    public final EObject entryRuleGlobalScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1173:2: (iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1174:2: iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalScopeExpression_in_entryRuleGlobalScopeExpression2405);
            iv_ruleGlobalScopeExpression=ruleGlobalScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalScopeExpression2415); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1181:1: ruleGlobalScopeExpression returns [EObject current=null] : (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' ) ;
    public final EObject ruleGlobalScopeExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token lv_recursivePrefix_8_0=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token lv_domains_23_0=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_29=null;
        Token otherlv_30=null;
        EObject lv_name_6_0 = null;

        EObject lv_prefix_11_0 = null;

        EObject lv_data_16_0 = null;

        EObject lv_data_18_0 = null;

        AntlrDatatypeRuleToken lv_domains_24_0 = null;

        AntlrDatatypeRuleToken lv_domains_26_0 = null;

        AntlrDatatypeRuleToken lv_domains_28_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1184:28: ( (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1185:1: (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1185:1: (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1185:3: otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')'
            {
            otherlv_0=(Token)match(input,37,FOLLOW_37_in_ruleGlobalScopeExpression2452); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalScopeExpressionAccess().getFindKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleGlobalScopeExpression2464); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1193:1: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1194:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1194:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1195:3: ruleQualifiedID
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
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleGlobalScopeExpression2491);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1211:2: ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )?
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==36) ) {
                int LA24_1 = input.LA(2);

                if ( ((LA24_1>=39 && LA24_1<=40)) ) {
                    alt24=2;
                }
                else if ( (LA24_1==38) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1211:3: (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1211:3: (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1211:5: otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,36,FOLLOW_36_in_ruleGlobalScopeExpression2505); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_3_0_0());
                          
                    }
                    otherlv_4=(Token)match(input,38,FOLLOW_38_in_ruleGlobalScopeExpression2517); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getGlobalScopeExpressionAccess().getKeyKeyword_3_0_1());
                          
                    }
                    otherlv_5=(Token)match(input,22,FOLLOW_22_in_ruleGlobalScopeExpression2529); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_3_0_2());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1223:1: ( (lv_name_6_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1224:1: (lv_name_6_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1224:1: (lv_name_6_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1225:3: lv_name_6_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getNameExpressionParserRuleCall_3_0_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2550);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1242:6: (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1242:6: (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1242:8: otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) )
                    {
                    otherlv_7=(Token)match(input,36,FOLLOW_36_in_ruleGlobalScopeExpression2570); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_3_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1246:1: ( (lv_recursivePrefix_8_0= 'recursive' ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==39) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1247:1: (lv_recursivePrefix_8_0= 'recursive' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1247:1: (lv_recursivePrefix_8_0= 'recursive' )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1248:3: lv_recursivePrefix_8_0= 'recursive'
                            {
                            lv_recursivePrefix_8_0=(Token)match(input,39,FOLLOW_39_in_ruleGlobalScopeExpression2588); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_recursivePrefix_8_0, grammarAccess.getGlobalScopeExpressionAccess().getRecursivePrefixRecursiveKeyword_3_1_1_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getGlobalScopeExpressionRule());
                              	        }
                                     		setWithLastConsumed(current, "recursivePrefix", true, "recursive");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    otherlv_9=(Token)match(input,40,FOLLOW_40_in_ruleGlobalScopeExpression2614); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getGlobalScopeExpressionAccess().getPrefixKeyword_3_1_2());
                          
                    }
                    otherlv_10=(Token)match(input,22,FOLLOW_22_in_ruleGlobalScopeExpression2626); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_3_1_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1269:1: ( (lv_prefix_11_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1270:1: (lv_prefix_11_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1270:1: (lv_prefix_11_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1271:3: lv_prefix_11_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getPrefixExpressionParserRuleCall_3_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2647);
                    lv_prefix_11_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                      	        }
                             		set(
                             			current, 
                             			"prefix",
                              		lv_prefix_11_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1287:5: (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==36) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==41) ) {
                    alt26=1;
                }
            }
            switch (alt26) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1287:7: otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')'
                    {
                    otherlv_12=(Token)match(input,36,FOLLOW_36_in_ruleGlobalScopeExpression2663); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_4_0());
                          
                    }
                    otherlv_13=(Token)match(input,41,FOLLOW_41_in_ruleGlobalScopeExpression2675); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getGlobalScopeExpressionAccess().getDataKeyword_4_1());
                          
                    }
                    otherlv_14=(Token)match(input,22,FOLLOW_22_in_ruleGlobalScopeExpression2687); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_4_2());
                          
                    }
                    otherlv_15=(Token)match(input,25,FOLLOW_25_in_ruleGlobalScopeExpression2699); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_15, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_4_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1303:1: ( (lv_data_16_0= ruleDataExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1304:1: (lv_data_16_0= ruleDataExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1304:1: (lv_data_16_0= ruleDataExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1305:3: lv_data_16_0= ruleDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDataDataExpressionParserRuleCall_4_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2720);
                    lv_data_16_0=ruleDataExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                      	        }
                             		add(
                             			current, 
                             			"data",
                              		lv_data_16_0, 
                              		"DataExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1321:2: (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==36) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1321:4: otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) )
                    	    {
                    	    otherlv_17=(Token)match(input,36,FOLLOW_36_in_ruleGlobalScopeExpression2733); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_17, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_4_5_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1325:1: ( (lv_data_18_0= ruleDataExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1326:1: (lv_data_18_0= ruleDataExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1326:1: (lv_data_18_0= ruleDataExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1327:3: lv_data_18_0= ruleDataExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDataDataExpressionParserRuleCall_4_5_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2754);
                    	    lv_data_18_0=ruleDataExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"data",
                    	              		lv_data_18_0, 
                    	              		"DataExpression");
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

                    otherlv_19=(Token)match(input,26,FOLLOW_26_in_ruleGlobalScopeExpression2768); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_19, grammarAccess.getGlobalScopeExpressionAccess().getRightParenthesisKeyword_4_6());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1347:3: (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==36) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1347:5: otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) )
                    {
                    otherlv_20=(Token)match(input,36,FOLLOW_36_in_ruleGlobalScopeExpression2783); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_5_0());
                          
                    }
                    otherlv_21=(Token)match(input,42,FOLLOW_42_in_ruleGlobalScopeExpression2795); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_21, grammarAccess.getGlobalScopeExpressionAccess().getDomainsKeyword_5_1());
                          
                    }
                    otherlv_22=(Token)match(input,22,FOLLOW_22_in_ruleGlobalScopeExpression2807); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_22, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_5_2());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1359:1: ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) )
                    int alt28=3;
                    switch ( input.LA(1) ) {
                    case 30:
                        {
                        alt28=1;
                        }
                        break;
                    case RULE_ID:
                        {
                        alt28=2;
                        }
                        break;
                    case 25:
                        {
                        alt28=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }

                    switch (alt28) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1359:2: ( (lv_domains_23_0= '*' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1359:2: ( (lv_domains_23_0= '*' ) )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1360:1: (lv_domains_23_0= '*' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1360:1: (lv_domains_23_0= '*' )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1361:3: lv_domains_23_0= '*'
                            {
                            lv_domains_23_0=(Token)match(input,30,FOLLOW_30_in_ruleGlobalScopeExpression2826); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_domains_23_0, grammarAccess.getGlobalScopeExpressionAccess().getDomainsAsteriskKeyword_5_3_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getGlobalScopeExpressionRule());
                              	        }
                                     		addWithLastConsumed(current, "domains", lv_domains_23_0, "*");
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1375:6: ( (lv_domains_24_0= ruleIdentifier ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1375:6: ( (lv_domains_24_0= ruleIdentifier ) )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1376:1: (lv_domains_24_0= ruleIdentifier )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1376:1: (lv_domains_24_0= ruleIdentifier )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1377:3: lv_domains_24_0= ruleIdentifier
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2866);
                            lv_domains_24_0=ruleIdentifier();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"domains",
                                      		lv_domains_24_0, 
                                      		"Identifier");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 3 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1394:6: (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1394:6: (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1394:8: otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')'
                            {
                            otherlv_25=(Token)match(input,25,FOLLOW_25_in_ruleGlobalScopeExpression2885); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_25, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_5_3_2_0());
                                  
                            }
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1398:1: ( (lv_domains_26_0= ruleIdentifier ) )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1399:1: (lv_domains_26_0= ruleIdentifier )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1399:1: (lv_domains_26_0= ruleIdentifier )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1400:3: lv_domains_26_0= ruleIdentifier
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2906);
                            lv_domains_26_0=ruleIdentifier();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                              	        }
                                     		add(
                                     			current, 
                                     			"domains",
                                      		lv_domains_26_0, 
                                      		"Identifier");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1416:2: (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )*
                            loop27:
                            do {
                                int alt27=2;
                                int LA27_0 = input.LA(1);

                                if ( (LA27_0==36) ) {
                                    alt27=1;
                                }


                                switch (alt27) {
                            	case 1 :
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1416:4: otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) )
                            	    {
                            	    otherlv_27=(Token)match(input,36,FOLLOW_36_in_ruleGlobalScopeExpression2919); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_27, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_5_3_2_2_0());
                            	          
                            	    }
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1420:1: ( (lv_domains_28_0= ruleIdentifier ) )
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1421:1: (lv_domains_28_0= ruleIdentifier )
                            	    {
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1421:1: (lv_domains_28_0= ruleIdentifier )
                            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1422:3: lv_domains_28_0= ruleIdentifier
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_2_2_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2940);
                            	    lv_domains_28_0=ruleIdentifier();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      	        if (current==null) {
                            	      	            current = createModelElementForParent(grammarAccess.getGlobalScopeExpressionRule());
                            	      	        }
                            	             		add(
                            	             			current, 
                            	             			"domains",
                            	              		lv_domains_28_0, 
                            	              		"Identifier");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop27;
                                }
                            } while (true);

                            otherlv_29=(Token)match(input,26,FOLLOW_26_in_ruleGlobalScopeExpression2954); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_29, grammarAccess.getGlobalScopeExpressionAccess().getRightParenthesisKeyword_5_3_2_3());
                                  
                            }

                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            otherlv_30=(Token)match(input,26,FOLLOW_26_in_ruleGlobalScopeExpression2970); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_30, grammarAccess.getGlobalScopeExpressionAccess().getRightParenthesisKeyword_6());
                  
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1454:1: entryRuleDataExpression returns [EObject current=null] : iv_ruleDataExpression= ruleDataExpression EOF ;
    public final EObject entryRuleDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1455:2: (iv_ruleDataExpression= ruleDataExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1456:2: iv_ruleDataExpression= ruleDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDataExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleDataExpression_in_entryRuleDataExpression3006);
            iv_ruleDataExpression=ruleDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDataExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDataExpression3016); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1463:1: ruleDataExpression returns [EObject current=null] : (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression ) ;
    public final EObject ruleDataExpression() throws RecognitionException {
        EObject current = null;

        EObject this_MatchDataExpression_0 = null;

        EObject this_LambdaDataExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1466:28: ( (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1467:1: (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1467:1: (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_ID) ) {
                alt30=1;
            }
            else if ( (LA30_0==31) ) {
                alt30=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1468:2: this_MatchDataExpression_0= ruleMatchDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDataExpressionAccess().getMatchDataExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleMatchDataExpression_in_ruleDataExpression3066);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1481:2: this_LambdaDataExpression_1= ruleLambdaDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDataExpressionAccess().getLambdaDataExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLambdaDataExpression_in_ruleDataExpression3096);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1500:1: entryRuleMatchDataExpression returns [EObject current=null] : iv_ruleMatchDataExpression= ruleMatchDataExpression EOF ;
    public final EObject entryRuleMatchDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMatchDataExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1501:2: (iv_ruleMatchDataExpression= ruleMatchDataExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1502:2: iv_ruleMatchDataExpression= ruleMatchDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMatchDataExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMatchDataExpression_in_entryRuleMatchDataExpression3131);
            iv_ruleMatchDataExpression=ruleMatchDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMatchDataExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMatchDataExpression3141); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1509:1: ruleMatchDataExpression returns [EObject current=null] : ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) ) ;
    public final EObject ruleMatchDataExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        EObject lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1512:28: ( ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1513:1: ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1513:1: ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1513:2: ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1513:2: ( (lv_key_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1514:1: (lv_key_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1514:1: (lv_key_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1515:3: lv_key_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMatchDataExpressionAccess().getKeyIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleMatchDataExpression3187);
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

            otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleMatchDataExpression3199); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMatchDataExpressionAccess().getEqualsSignKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1535:1: ( (lv_value_2_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1536:1: (lv_value_2_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1536:1: (lv_value_2_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1537:3: lv_value_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMatchDataExpressionAccess().getValueExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleMatchDataExpression3220);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1561:1: entryRuleLambdaDataExpression returns [EObject current=null] : iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF ;
    public final EObject entryRuleLambdaDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaDataExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1562:2: (iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1563:2: iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLambdaDataExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLambdaDataExpression_in_entryRuleLambdaDataExpression3256);
            iv_ruleLambdaDataExpression=ruleLambdaDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLambdaDataExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLambdaDataExpression3266); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1570:1: ruleLambdaDataExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' ) ;
    public final EObject ruleLambdaDataExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_desc_1_0 = null;

        EObject lv_value_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1573:28: ( (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1574:1: (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1574:1: (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1574:3: otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,31,FOLLOW_31_in_ruleLambdaDataExpression3303); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLambdaDataExpressionAccess().getLeftSquareBracketKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1578:1: ( (lv_desc_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1579:1: (lv_desc_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1579:1: (lv_desc_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1580:3: lv_desc_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLambdaDataExpressionAccess().getDescIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLambdaDataExpression3324);
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

            otherlv_2=(Token)match(input,33,FOLLOW_33_in_ruleLambdaDataExpression3336); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLambdaDataExpressionAccess().getVerticalLineKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1600:1: ( (lv_value_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1601:1: (lv_value_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1601:1: (lv_value_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1602:3: lv_value_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLambdaDataExpressionAccess().getValueExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLambdaDataExpression3357);
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

            otherlv_4=(Token)match(input,32,FOLLOW_32_in_ruleLambdaDataExpression3369); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1630:1: entryRuleSimpleScopeExpression returns [EObject current=null] : iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF ;
    public final EObject entryRuleSimpleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleScopeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1631:2: (iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1632:2: iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleScopeExpression_in_entryRuleSimpleScopeExpression3405);
            iv_ruleSimpleScopeExpression=ruleSimpleScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleScopeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleScopeExpression3415); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1639:1: ruleSimpleScopeExpression returns [EObject current=null] : ( (lv_expr_0_0= ruleExpression ) ) ;
    public final EObject ruleSimpleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_0_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1642:28: ( ( (lv_expr_0_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1643:1: ( (lv_expr_0_0= ruleExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1643:1: ( (lv_expr_0_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1644:1: (lv_expr_0_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1644:1: (lv_expr_0_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1645:3: lv_expr_0_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleScopeExpressionAccess().getExprExpressionParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleSimpleScopeExpression3460);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1669:1: entryRuleNaming returns [EObject current=null] : iv_ruleNaming= ruleNaming EOF ;
    public final EObject entryRuleNaming() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNaming = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1670:2: (iv_ruleNaming= ruleNaming EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1671:2: iv_ruleNaming= ruleNaming EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingRule()); 
            }
            pushFollow(FOLLOW_ruleNaming_in_entryRuleNaming3495);
            iv_ruleNaming=ruleNaming();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNaming; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNaming3505); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1678:1: ruleNaming returns [EObject current=null] : ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1681:28: ( ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1682:1: ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1682:1: ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) )
            int alt32=2;
            alt32 = dfa32.predict(input);
            switch (alt32) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1682:2: ( (lv_names_0_0= ruleNamingExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1682:2: ( (lv_names_0_0= ruleNamingExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1683:1: (lv_names_0_0= ruleNamingExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1683:1: (lv_names_0_0= ruleNamingExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1684:3: lv_names_0_0= ruleNamingExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNamingExpression_in_ruleNaming3551);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1701:6: (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1701:6: (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1701:8: otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleNaming3570); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNamingAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1705:1: ( (lv_names_2_0= ruleNamingExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1706:1: (lv_names_2_0= ruleNamingExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1706:1: (lv_names_2_0= ruleNamingExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1707:3: lv_names_2_0= ruleNamingExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNamingExpression_in_ruleNaming3591);
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

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1723:2: (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==36) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1723:4: otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,36,FOLLOW_36_in_ruleNaming3604); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getNamingAccess().getCommaKeyword_1_2_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1727:1: ( (lv_names_4_0= ruleNamingExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1728:1: (lv_names_4_0= ruleNamingExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1728:1: (lv_names_4_0= ruleNamingExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1729:3: lv_names_4_0= ruleNamingExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_1_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleNamingExpression_in_ruleNaming3625);
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
                    	    break loop31;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,26,FOLLOW_26_in_ruleNaming3639); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1757:1: entryRuleNamingExpression returns [EObject current=null] : iv_ruleNamingExpression= ruleNamingExpression EOF ;
    public final EObject entryRuleNamingExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1758:2: (iv_ruleNamingExpression= ruleNamingExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1759:2: iv_ruleNamingExpression= ruleNamingExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleNamingExpression_in_entryRuleNamingExpression3676);
            iv_ruleNamingExpression=ruleNamingExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNamingExpression3686); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1766:1: ruleNamingExpression returns [EObject current=null] : ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) ) ;
    public final EObject ruleNamingExpression() throws RecognitionException {
        EObject current = null;

        Token lv_export_0_0=null;
        Token lv_factory_1_0=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1769:28: ( ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1770:1: ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1770:1: ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==43) ) {
                alt34=1;
            }
            else if ( ((LA34_0>=RULE_STRING && LA34_0<=RULE_ID)||LA34_0==20||LA34_0==25||LA34_0==34||LA34_0==46||LA34_0==50||LA34_0==53||LA34_0==65||(LA34_0>=67 && LA34_0<=84)) ) {
                alt34=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }
            switch (alt34) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1770:2: ( (lv_export_0_0= 'export' ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1770:2: ( (lv_export_0_0= 'export' ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1771:1: (lv_export_0_0= 'export' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1771:1: (lv_export_0_0= 'export' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1772:3: lv_export_0_0= 'export'
                    {
                    lv_export_0_0=(Token)match(input,43,FOLLOW_43_in_ruleNamingExpression3729); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1786:6: ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1786:6: ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1786:7: ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1786:7: ( (lv_factory_1_0= 'factory' ) )?
                    int alt33=2;
                    int LA33_0 = input.LA(1);

                    if ( (LA33_0==34) ) {
                        alt33=1;
                    }
                    switch (alt33) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1787:1: (lv_factory_1_0= 'factory' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1787:1: (lv_factory_1_0= 'factory' )
                            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1788:3: lv_factory_1_0= 'factory'
                            {
                            lv_factory_1_0=(Token)match(input,34,FOLLOW_34_in_ruleNamingExpression3767); if (state.failed) return current;
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

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1801:3: ( (lv_expression_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1802:1: (lv_expression_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1802:1: (lv_expression_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1803:3: lv_expression_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingExpressionAccess().getExpressionExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleNamingExpression3802);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1827:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1828:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1829:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3840);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID3851); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1836:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Identifier_0 = null;

        AntlrDatatypeRuleToken this_Identifier_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1839:28: ( (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1840:1: (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1840:1: (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1841:5: this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getQualifiedIDAccess().getIdentifierParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleQualifiedID3898);
            this_Identifier_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_Identifier_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1851:1: (kw= '::' this_Identifier_2= ruleIdentifier )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==44) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1852:2: kw= '::' this_Identifier_2= ruleIdentifier
            	    {
            	    kw=(Token)match(input,44,FOLLOW_44_in_ruleQualifiedID3917); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getQualifiedIDAccess().getIdentifierParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleQualifiedID3939);
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
            	    break loop35;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1876:1: entryRuleDottedID returns [String current=null] : iv_ruleDottedID= ruleDottedID EOF ;
    public final String entryRuleDottedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDottedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1877:2: (iv_ruleDottedID= ruleDottedID EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1878:2: iv_ruleDottedID= ruleDottedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDottedIDRule()); 
            }
            pushFollow(FOLLOW_ruleDottedID_in_entryRuleDottedID3987);
            iv_ruleDottedID=ruleDottedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDottedID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleDottedID3998); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1885:1: ruleDottedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* ) ;
    public final AntlrDatatypeRuleToken ruleDottedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Identifier_0 = null;

        AntlrDatatypeRuleToken this_Identifier_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1888:28: ( (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1889:1: (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1889:1: (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1890:5: this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getDottedIDAccess().getIdentifierParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleDottedID4045);
            this_Identifier_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_Identifier_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1900:1: (kw= '.' this_Identifier_2= ruleIdentifier )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==45) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1901:2: kw= '.' this_Identifier_2= ruleIdentifier
            	    {
            	    kw=(Token)match(input,45,FOLLOW_45_in_ruleDottedID4064); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getDottedIDAccess().getFullStopKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getDottedIDAccess().getIdentifierParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleDottedID4086);
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
    // $ANTLR end "ruleDottedID"


    // $ANTLR start "entryRuleExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1925:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1926:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1927:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression4133);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression4143); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1934:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1937:28: ( (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1938:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1938:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            int alt37=3;
            alt37 = dfa37.predict(input);
            switch (alt37) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1939:2: this_LetExpression_0= ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLetExpression_in_ruleExpression4193);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1952:2: this_CastedExpression_1= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCastedExpression_in_ruleExpression4223);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1965:2: this_ChainExpression_2= ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleChainExpression_in_ruleExpression4253);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1986:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1987:2: (iv_ruleLetExpression= ruleLetExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1988:2: iv_ruleLetExpression= ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLetExpression_in_entryRuleLetExpression4290);
            iv_ruleLetExpression=ruleLetExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetExpression4300); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1995:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1998:28: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1999:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1999:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1999:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,46,FOLLOW_46_in_ruleLetExpression4337); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2003:1: ( (lv_identifier_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2004:1: (lv_identifier_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2004:1: (lv_identifier_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2005:3: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLetExpression4358);
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

            otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleLetExpression4370); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2025:1: ( (lv_varExpr_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2026:1: (lv_varExpr_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2026:1: (lv_varExpr_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2027:3: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression4391);
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

            otherlv_4=(Token)match(input,47,FOLLOW_47_in_ruleLetExpression4403); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2047:1: ( (lv_target_5_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2048:1: (lv_target_5_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2048:1: (lv_target_5_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2049:3: lv_target_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression4424);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2073:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2074:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2075:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression4460);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCastedExpression4470); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2082:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2085:28: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2086:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2086:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2086:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,25,FOLLOW_25_in_ruleCastedExpression4507); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2090:1: ( (lv_type_1_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2091:1: (lv_type_1_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2091:1: (lv_type_1_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2092:3: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleCastedExpression4528);
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

            otherlv_2=(Token)match(input,26,FOLLOW_26_in_ruleCastedExpression4540); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2112:1: ( (lv_target_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2113:1: (lv_target_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2113:1: (lv_target_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2114:3: lv_target_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCastedExpression4561);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2138:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2139:2: (iv_ruleChainExpression= ruleChainExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2140:2: iv_ruleChainExpression= ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainExpression_in_entryRuleChainExpression4597);
            iv_ruleChainExpression=ruleChainExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainExpression4607); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2147:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2150:28: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2151:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2151:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2152:2: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression4657);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ChainedExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2163:1: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==48) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2163:2: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2163:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2164:2: 
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

            	    otherlv_2=(Token)match(input,48,FOLLOW_48_in_ruleChainExpression4681); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2176:1: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2177:1: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2177:1: (lv_next_3_0= ruleChainedExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2178:3: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression4702);
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
            	    break loop38;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2202:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2203:2: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2204:2: iv_ruleChainedExpression= ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression4740);
            iv_ruleChainedExpression=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainedExpression4750); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2211:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2214:28: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2215:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2215:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            int alt39=3;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt39=1;
                }
                break;
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case RULE_ID:
            case 20:
            case 25:
            case 65:
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
            case 83:
            case 84:
                {
                alt39=2;
                }
                break;
            case 53:
                {
                alt39=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }

            switch (alt39) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2216:2: this_IfExpressionKw_0= ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression4800);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2229:2: this_IfExpressionTri_1= ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression4830);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2242:2: this_SwitchExpression_2= ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSwitchExpression_in_ruleChainedExpression4860);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2261:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2262:2: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2263:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri4895);
            iv_ruleIfExpressionTri=ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionTri; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionTri4905); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2270:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2273:28: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2274:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2274:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2275:2: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleIfExpressionTri4955);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2286:1: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==49) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2286:2: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2286:2: ()
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2287:2: 
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

                    otherlv_2=(Token)match(input,49,FOLLOW_49_in_ruleIfExpressionTri4979); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2299:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2300:1: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2300:1: (lv_thenPart_3_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2301:3: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri5000);
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

                    otherlv_4=(Token)match(input,47,FOLLOW_47_in_ruleIfExpressionTri5012); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2321:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2322:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2322:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2323:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri5033);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2347:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2348:2: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2349:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw5071);
            iv_ruleIfExpressionKw=ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionKw; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionKw5081); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2356:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2359:28: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2360:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2360:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2360:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            otherlv_0=(Token)match(input,50,FOLLOW_50_in_ruleIfExpressionKw5118); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2364:1: ( (lv_condition_1_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2365:1: (lv_condition_1_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2365:1: (lv_condition_1_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2366:3: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5139);
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

            otherlv_2=(Token)match(input,51,FOLLOW_51_in_ruleIfExpressionKw5151); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2386:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2387:1: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2387:1: (lv_thenPart_3_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2388:3: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5172);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2404:2: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==52) ) {
                int LA41_1 = input.LA(2);

                if ( (synpred46_InternalScope()) ) {
                    alt41=1;
                }
            }
            switch (alt41) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2404:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,52,FOLLOW_52_in_ruleIfExpressionKw5185); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2408:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2409:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2409:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2410:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5206);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2434:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2435:2: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2436:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression5244);
            iv_ruleSwitchExpression=ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSwitchExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchExpression5254); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2443:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2446:28: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2447:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2447:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2447:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,53,FOLLOW_53_in_ruleSwitchExpression5291); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2451:1: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==25) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2451:3: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleSwitchExpression5304); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2455:1: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2456:1: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2456:1: (lv_switchExpr_2_0= ruleOrExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2457:3: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression5325);
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

                    otherlv_3=(Token)match(input,26,FOLLOW_26_in_ruleSwitchExpression5337); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,20,FOLLOW_20_in_ruleSwitchExpression5351); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2481:1: ( (lv_case_5_0= ruleCase ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==18) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2482:1: (lv_case_5_0= ruleCase )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2482:1: (lv_case_5_0= ruleCase )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2483:3: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleCase_in_ruleSwitchExpression5372);
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
            	    break loop43;
                }
            } while (true);

            otherlv_6=(Token)match(input,54,FOLLOW_54_in_ruleSwitchExpression5385); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
                  
            }
            otherlv_7=(Token)match(input,47,FOLLOW_47_in_ruleSwitchExpression5397); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2507:1: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2508:1: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2508:1: (lv_defaultExpr_8_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2509:3: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression5418);
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

            otherlv_9=(Token)match(input,21,FOLLOW_21_in_ruleSwitchExpression5430); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2537:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2538:2: (iv_ruleCase= ruleCase EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2539:2: iv_ruleCase= ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_ruleCase_in_entryRuleCase5466);
            iv_ruleCase=ruleCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCase; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCase5476); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2546:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2549:28: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2550:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2550:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2550:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleCase5513); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2554:1: ( (lv_condition_1_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2555:1: (lv_condition_1_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2555:1: (lv_condition_1_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2556:3: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase5534);
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

            otherlv_2=(Token)match(input,47,FOLLOW_47_in_ruleCase5546); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2576:1: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2577:1: (lv_thenPar_3_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2577:1: (lv_thenPar_3_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2578:3: lv_thenPar_3_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase5567);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2602:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2603:2: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2604:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression5603);
            iv_ruleOrExpression=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression5613); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2611:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2614:28: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2615:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2615:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2616:2: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression5663);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2627:1: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==55) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2627:2: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2627:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2628:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2636:2: ( (lv_operator_2_0= '||' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2637:1: (lv_operator_2_0= '||' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2637:1: (lv_operator_2_0= '||' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2638:3: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,55,FOLLOW_55_in_ruleOrExpression5693); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2651:2: ( (lv_right_3_0= ruleAndExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2652:1: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2652:1: (lv_right_3_0= ruleAndExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2653:3: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression5727);
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
    // $ANTLR end "ruleOrExpression"


    // $ANTLR start "entryRuleAndExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2677:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2678:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2679:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression5765);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression5775); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2686:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2689:28: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2690:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2690:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2691:2: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression5825);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2702:1: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==56) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2702:2: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2702:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2703:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2711:2: ( (lv_operator_2_0= '&&' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2712:1: (lv_operator_2_0= '&&' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2712:1: (lv_operator_2_0= '&&' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2713:3: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,56,FOLLOW_56_in_ruleAndExpression5855); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2726:2: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2727:1: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2727:1: (lv_right_3_0= ruleImpliesExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2728:3: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression5889);
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
            	    break loop45;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2752:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2753:2: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2754:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression5927);
            iv_ruleImpliesExpression=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpression5937); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2761:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2764:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2765:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2765:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2766:2: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression5987);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2777:1: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==57) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2777:2: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2777:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2778:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2786:2: ( (lv_operator_2_0= 'implies' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2787:1: (lv_operator_2_0= 'implies' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2787:1: (lv_operator_2_0= 'implies' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2788:3: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,57,FOLLOW_57_in_ruleImpliesExpression6017); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2801:2: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2802:1: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2802:1: (lv_right_3_0= ruleRelationalExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2803:3: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression6051);
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
    // $ANTLR end "ruleImpliesExpression"


    // $ANTLR start "entryRuleRelationalExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2827:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2828:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2829:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression6089);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression6099); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2836:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2839:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2840:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2840:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2841:2: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression6149);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2852:1: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=58 && LA48_0<=63)) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2852:2: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2852:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2853:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2861:2: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2862:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2862:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2863:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2863:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    int alt47=6;
            	    switch ( input.LA(1) ) {
            	    case 58:
            	        {
            	        alt47=1;
            	        }
            	        break;
            	    case 59:
            	        {
            	        alt47=2;
            	        }
            	        break;
            	    case 60:
            	        {
            	        alt47=3;
            	        }
            	        break;
            	    case 61:
            	        {
            	        alt47=4;
            	        }
            	        break;
            	    case 62:
            	        {
            	        alt47=5;
            	        }
            	        break;
            	    case 63:
            	        {
            	        alt47=6;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 47, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt47) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2864:3: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,58,FOLLOW_58_in_ruleRelationalExpression6181); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2876:8: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,59,FOLLOW_59_in_ruleRelationalExpression6210); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2888:8: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,60,FOLLOW_60_in_ruleRelationalExpression6239); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2900:8: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,61,FOLLOW_61_in_ruleRelationalExpression6268); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2912:8: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,62,FOLLOW_62_in_ruleRelationalExpression6297); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2924:8: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,63,FOLLOW_63_in_ruleRelationalExpression6326); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2939:2: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2940:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2940:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2941:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression6363);
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
    // $ANTLR end "ruleRelationalExpression"


    // $ANTLR start "entryRuleAdditiveExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2965:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2966:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2967:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression6401);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression6411); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2974:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2977:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2978:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2978:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2979:2: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6461);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2990:1: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop50:
            do {
                int alt50=2;
                int LA50_0 = input.LA(1);

                if ( ((LA50_0>=64 && LA50_0<=65)) ) {
                    alt50=1;
                }


                switch (alt50) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2990:2: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2990:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2991:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2999:2: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3000:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3000:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3001:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3001:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    int alt49=2;
            	    int LA49_0 = input.LA(1);

            	    if ( (LA49_0==64) ) {
            	        alt49=1;
            	    }
            	    else if ( (LA49_0==65) ) {
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3002:3: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,64,FOLLOW_64_in_ruleAdditiveExpression6493); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3014:8: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,65,FOLLOW_65_in_ruleAdditiveExpression6522); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3029:2: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3030:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3030:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3031:3: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6559);
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
    // $ANTLR end "ruleAdditiveExpression"


    // $ANTLR start "entryRuleMultiplicativeExpression"
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3055:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3056:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3057:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression6597);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression6607); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3064:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3067:28: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3068:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3068:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3069:2: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6657);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryOrInfixExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3080:1: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop52:
            do {
                int alt52=2;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==30||LA52_0==66) ) {
                    alt52=1;
                }


                switch (alt52) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3080:2: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3080:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3081:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3089:2: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3090:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3090:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3091:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3091:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    int alt51=2;
            	    int LA51_0 = input.LA(1);

            	    if ( (LA51_0==30) ) {
            	        alt51=1;
            	    }
            	    else if ( (LA51_0==66) ) {
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3092:3: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,30,FOLLOW_30_in_ruleMultiplicativeExpression6689); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3104:8: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,66,FOLLOW_66_in_ruleMultiplicativeExpression6718); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3119:2: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3120:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3120:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3121:3: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6755);
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
            	    break loop52;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3145:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3146:2: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3147:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression6793);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOrInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression6803); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3154:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3157:28: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3158:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3158:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==65||LA53_0==67) ) {
                alt53=1;
            }
            else if ( ((LA53_0>=RULE_STRING && LA53_0<=RULE_ID)||LA53_0==20||LA53_0==25||(LA53_0>=68 && LA53_0<=84)) ) {
                alt53=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3159:2: this_UnaryExpression_0= ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression6853);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3172:2: this_InfixExpression_1= ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression6883);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3191:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3192:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3193:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression6918);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression6928); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3200:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3203:28: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3204:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3204:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3204:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3204:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3205:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3205:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3206:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3206:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==67) ) {
                alt54=1;
            }
            else if ( (LA54_0==65) ) {
                alt54=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3207:3: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,67,FOLLOW_67_in_ruleUnaryExpression6973); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3219:8: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,65,FOLLOW_65_in_ruleUnaryExpression7002); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3234:2: ( (lv_params_1_0= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3235:1: (lv_params_1_0= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3235:1: (lv_params_1_0= ruleInfixExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3236:3: lv_params_1_0= ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression7039);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3260:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3261:2: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3262:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression7075);
            iv_ruleInfixExpression=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression7085); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3269:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3272:28: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3273:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3273:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3274:2: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression7135);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3285:1: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop59:
            do {
                int alt59=5;
                int LA59_0 = input.LA(1);

                if ( (LA59_0==45) ) {
                    switch ( input.LA(2) ) {
                    case 68:
                        {
                        alt59=3;
                        }
                        break;
                    case 69:
                    case 70:
                    case 71:
                    case 72:
                    case 73:
                    case 74:
                    case 75:
                    case 76:
                        {
                        alt59=4;
                        }
                        break;
                    case 82:
                    case 83:
                    case 84:
                        {
                        alt59=2;
                        }
                        break;
                    case RULE_ID:
                        {
                        int LA59_6 = input.LA(3);

                        if ( (LA59_6==25) ) {
                            alt59=1;
                        }
                        else if ( (LA59_6==EOF||LA59_6==15||LA59_6==18||LA59_6==21||LA59_6==23||LA59_6==26||(LA59_6>=29 && LA59_6<=30)||(LA59_6>=32 && LA59_6<=33)||LA59_6==36||(LA59_6>=44 && LA59_6<=45)||(LA59_6>=47 && LA59_6<=49)||(LA59_6>=51 && LA59_6<=52)||(LA59_6>=54 && LA59_6<=66)) ) {
                            alt59=2;
                        }


                        }
                        break;

                    }

                }


                switch (alt59) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3285:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3285:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3285:3: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3285:3: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3286:2: 
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

            	    otherlv_2=(Token)match(input,45,FOLLOW_45_in_ruleInfixExpression7160); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3298:1: ( (lv_name_3_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3299:1: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3299:1: (lv_name_3_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3300:3: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression7181);
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

            	    otherlv_4=(Token)match(input,25,FOLLOW_25_in_ruleInfixExpression7193); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3320:1: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt56=2;
            	    int LA56_0 = input.LA(1);

            	    if ( ((LA56_0>=RULE_STRING && LA56_0<=RULE_ID)||LA56_0==20||LA56_0==25||LA56_0==46||LA56_0==50||LA56_0==53||LA56_0==65||(LA56_0>=67 && LA56_0<=84)) ) {
            	        alt56=1;
            	    }
            	    switch (alt56) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3320:2: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3320:2: ( (lv_params_5_0= ruleExpression ) )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3321:1: (lv_params_5_0= ruleExpression )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3321:1: (lv_params_5_0= ruleExpression )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3322:3: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression7215);
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

            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3338:2: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop55:
            	            do {
            	                int alt55=2;
            	                int LA55_0 = input.LA(1);

            	                if ( (LA55_0==36) ) {
            	                    alt55=1;
            	                }


            	                switch (alt55) {
            	            	case 1 :
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3338:4: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,36,FOLLOW_36_in_ruleInfixExpression7228); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	          
            	            	    }
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3342:1: ( (lv_params_7_0= ruleExpression ) )
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3343:1: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3343:1: (lv_params_7_0= ruleExpression )
            	            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3344:3: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression7249);
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
            	            	    break loop55;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression7265); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3365:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3365:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3365:7: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3365:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3366:2: 
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

            	    otherlv_10=(Token)match(input,45,FOLLOW_45_in_ruleInfixExpression7297); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3378:1: ( (lv_type_11_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3379:1: (lv_type_11_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3379:1: (lv_type_11_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3380:3: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression7318);
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
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3397:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3397:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3397:7: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3397:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3398:2: 
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

            	    otherlv_13=(Token)match(input,45,FOLLOW_45_in_ruleInfixExpression7350); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3410:1: ( (lv_name_14_0= 'typeSelect' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3411:1: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3411:1: (lv_name_14_0= 'typeSelect' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3412:3: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,68,FOLLOW_68_in_ruleInfixExpression7368); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,25,FOLLOW_25_in_ruleInfixExpression7393); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3429:1: ( (lv_type_16_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3430:1: (lv_type_16_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3430:1: (lv_type_16_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3431:3: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression7414);
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

            	    otherlv_17=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression7426); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3452:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3452:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3452:7: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3452:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3453:2: 
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

            	    otherlv_19=(Token)match(input,45,FOLLOW_45_in_ruleInfixExpression7458); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3465:1: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3466:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3466:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3467:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3467:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    int alt57=8;
            	    switch ( input.LA(1) ) {
            	    case 69:
            	        {
            	        alt57=1;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt57=2;
            	        }
            	        break;
            	    case 71:
            	        {
            	        alt57=3;
            	        }
            	        break;
            	    case 72:
            	        {
            	        alt57=4;
            	        }
            	        break;
            	    case 73:
            	        {
            	        alt57=5;
            	        }
            	        break;
            	    case 74:
            	        {
            	        alt57=6;
            	        }
            	        break;
            	    case 75:
            	        {
            	        alt57=7;
            	        }
            	        break;
            	    case 76:
            	        {
            	        alt57=8;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 57, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt57) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3468:3: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,69,FOLLOW_69_in_ruleInfixExpression7478); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3480:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,70,FOLLOW_70_in_ruleInfixExpression7507); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3492:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,71,FOLLOW_71_in_ruleInfixExpression7536); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3504:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,72,FOLLOW_72_in_ruleInfixExpression7565); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3516:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,73,FOLLOW_73_in_ruleInfixExpression7594); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3528:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,74,FOLLOW_74_in_ruleInfixExpression7623); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3540:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,75,FOLLOW_75_in_ruleInfixExpression7652); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3552:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,76,FOLLOW_76_in_ruleInfixExpression7681); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,25,FOLLOW_25_in_ruleInfixExpression7709); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3571:1: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
            	    int alt58=2;
            	    int LA58_0 = input.LA(1);

            	    if ( (LA58_0==RULE_ID) ) {
            	        int LA58_1 = input.LA(2);

            	        if ( (LA58_1==33) ) {
            	            alt58=1;
            	        }
            	    }
            	    switch (alt58) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3571:2: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3571:2: ( (lv_var_22_0= ruleIdentifier ) )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3572:1: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3572:1: (lv_var_22_0= ruleIdentifier )
            	            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3573:3: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression7731);
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

            	            otherlv_23=(Token)match(input,33,FOLLOW_33_in_ruleInfixExpression7743); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3593:3: ( (lv_exp_24_0= ruleExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3594:1: (lv_exp_24_0= ruleExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3594:1: (lv_exp_24_0= ruleExpression )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3595:3: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression7766);
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

            	    otherlv_25=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression7778); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6());
            	          
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop59;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3623:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3624:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3625:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression7817);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression7827); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3632:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3635:28: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3636:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3636:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            int alt60=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case 77:
            case 78:
            case 79:
                {
                alt60=1;
                }
                break;
            case RULE_ID:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 82:
            case 83:
            case 84:
                {
                alt60=2;
                }
                break;
            case 20:
                {
                alt60=3;
                }
                break;
            case 81:
                {
                alt60=4;
                }
                break;
            case 80:
                {
                alt60=5;
                }
                break;
            case 25:
                {
                alt60=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }

            switch (alt60) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3637:2: this_Literal_0= ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression7877);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3650:2: this_FeatureCall_1= ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFeatureCall_in_rulePrimaryExpression7907);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3663:2: this_ListLiteral_2= ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListLiteral_in_rulePrimaryExpression7937);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3676:2: this_ConstructorCallExpression_3= ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression7967);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3689:2: this_GlobalVarExpression_4= ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression7997);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3702:2: this_ParanthesizedExpression_5= ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression8027);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3721:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3722:2: (iv_ruleLiteral= ruleLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3723:2: iv_ruleLiteral= ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral8062);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral8072); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3730:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3733:28: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3734:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3734:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            int alt61=5;
            switch ( input.LA(1) ) {
            case 77:
            case 78:
                {
                alt61=1;
                }
                break;
            case RULE_INT:
                {
                alt61=2;
                }
                break;
            case 79:
                {
                alt61=3;
                }
                break;
            case RULE_REAL:
                {
                alt61=4;
                }
                break;
            case RULE_STRING:
                {
                alt61=5;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3735:2: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral8122);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3748:2: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral8152);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3761:2: this_NullLiteral_2= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral8182);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3774:2: this_RealLiteral_3= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealLiteral_in_ruleLiteral8212);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3787:2: this_StringLiteral_4= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral8242);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3806:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3807:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3808:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral8277);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral8287); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3815:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3818:28: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3819:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3819:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3820:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3820:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3821:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3821:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==77) ) {
                alt62=1;
            }
            else if ( (LA62_0==78) ) {
                alt62=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3822:3: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,77,FOLLOW_77_in_ruleBooleanLiteral8331); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3834:8: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,78,FOLLOW_78_in_ruleBooleanLiteral8360); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3857:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3858:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3859:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral8411);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral8421); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3866:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3869:28: ( ( (lv_val_0_0= RULE_INT ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3870:1: ( (lv_val_0_0= RULE_INT ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3870:1: ( (lv_val_0_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3871:1: (lv_val_0_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3871:1: (lv_val_0_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3872:3: lv_val_0_0= RULE_INT
            {
            lv_val_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral8462); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3896:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3897:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3898:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral8502);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral8512); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3905:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3908:28: ( ( (lv_val_0_0= 'null' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3909:1: ( (lv_val_0_0= 'null' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3909:1: ( (lv_val_0_0= 'null' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3910:1: (lv_val_0_0= 'null' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3910:1: (lv_val_0_0= 'null' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3911:3: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,79,FOLLOW_79_in_ruleNullLiteral8554); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3932:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3933:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3934:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral8602);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealLiteral8612); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3941:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3944:28: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3945:1: ( (lv_val_0_0= RULE_REAL ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3945:1: ( (lv_val_0_0= RULE_REAL ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3946:1: (lv_val_0_0= RULE_REAL )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3946:1: (lv_val_0_0= RULE_REAL )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3947:3: lv_val_0_0= RULE_REAL
            {
            lv_val_0_0=(Token)match(input,RULE_REAL,FOLLOW_RULE_REAL_in_ruleRealLiteral8653); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3971:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3972:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3973:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral8693);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral8703); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3980:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3983:28: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3984:1: ( (lv_val_0_0= RULE_STRING ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3984:1: ( (lv_val_0_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3985:1: (lv_val_0_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3985:1: (lv_val_0_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:3986:3: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral8744); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4010:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4011:2: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4012:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression8784);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParanthesizedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression8794); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4019:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4022:28: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4023:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4023:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4023:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_25_in_ruleParanthesizedExpression8831); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression8856);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,26,FOLLOW_26_in_ruleParanthesizedExpression8867); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4051:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4052:2: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4053:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression8903);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalVarExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression8913); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4060:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4063:28: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4064:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4064:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4064:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,80,FOLLOW_80_in_ruleGlobalVarExpression8950); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4068:1: ( (lv_name_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4069:1: (lv_name_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4069:1: (lv_name_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4070:3: lv_name_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression8971);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4094:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4095:2: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4096:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall9007);
            iv_ruleFeatureCall=ruleFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureCall9017); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4103:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4106:28: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4107:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4107:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            int alt63=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA63_1 = input.LA(2);

                if ( (LA63_1==EOF||LA63_1==15||LA63_1==18||LA63_1==21||LA63_1==23||LA63_1==26||(LA63_1>=29 && LA63_1<=30)||(LA63_1>=32 && LA63_1<=33)||LA63_1==36||(LA63_1>=44 && LA63_1<=45)||(LA63_1>=47 && LA63_1<=49)||(LA63_1>=51 && LA63_1<=52)||(LA63_1>=54 && LA63_1<=66)) ) {
                    alt63=2;
                }
                else if ( (LA63_1==25) ) {
                    alt63=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    throw nvae;
                }
                }
                break;
            case 82:
            case 83:
            case 84:
                {
                alt63=2;
                }
                break;
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
                {
                alt63=3;
                }
                break;
            case 68:
                {
                alt63=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4108:2: this_OperationCall_0= ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleOperationCall_in_ruleFeatureCall9067);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4120:6: ( (lv_type_1_0= ruleType ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4120:6: ( (lv_type_1_0= ruleType ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4121:1: (lv_type_1_0= ruleType )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4121:1: (lv_type_1_0= ruleType )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4122:3: lv_type_1_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleFeatureCall9093);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4140:2: this_CollectionExpression_2= ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionExpression_in_ruleFeatureCall9124);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4153:2: this_TypeSelectExpression_3= ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall9154);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4172:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4173:2: (iv_ruleOperationCall= ruleOperationCall EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4174:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_ruleOperationCall_in_entryRuleOperationCall9189);
            iv_ruleOperationCall=ruleOperationCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCall9199); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4181:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4184:28: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4185:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4185:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4185:2: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4185:2: ( (lv_name_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4186:1: (lv_name_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4186:1: (lv_name_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4187:3: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleOperationCall9245);
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

            otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleOperationCall9257); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4207:1: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( ((LA65_0>=RULE_STRING && LA65_0<=RULE_ID)||LA65_0==20||LA65_0==25||LA65_0==46||LA65_0==50||LA65_0==53||LA65_0==65||(LA65_0>=67 && LA65_0<=84)) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4207:2: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4207:2: ( (lv_params_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4208:1: (lv_params_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4208:1: (lv_params_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4209:3: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall9279);
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

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4225:2: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop64:
                    do {
                        int alt64=2;
                        int LA64_0 = input.LA(1);

                        if ( (LA64_0==36) ) {
                            alt64=1;
                        }


                        switch (alt64) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4225:4: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,36,FOLLOW_36_in_ruleOperationCall9292); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4229:1: ( (lv_params_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4230:1: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4230:1: (lv_params_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4231:3: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall9313);
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
                    	    break loop64;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,26,FOLLOW_26_in_ruleOperationCall9329); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4259:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4260:2: (iv_ruleListLiteral= ruleListLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4261:2: iv_ruleListLiteral= ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleListLiteral_in_entryRuleListLiteral9365);
            iv_ruleListLiteral=ruleListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListLiteral9375); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4268:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4271:28: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4272:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4272:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4272:2: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4272:2: ()
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4273:2: 
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

            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleListLiteral9424); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4285:1: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( ((LA67_0>=RULE_STRING && LA67_0<=RULE_ID)||LA67_0==20||LA67_0==25||LA67_0==46||LA67_0==50||LA67_0==53||LA67_0==65||(LA67_0>=67 && LA67_0<=84)) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4285:2: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4285:2: ( (lv_elements_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4286:1: (lv_elements_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4286:1: (lv_elements_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4287:3: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral9446);
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

                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4303:2: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop66:
                    do {
                        int alt66=2;
                        int LA66_0 = input.LA(1);

                        if ( (LA66_0==36) ) {
                            alt66=1;
                        }


                        switch (alt66) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4303:4: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,36,FOLLOW_36_in_ruleListLiteral9459); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4307:1: ( (lv_elements_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4308:1: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4308:1: (lv_elements_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4309:3: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral9480);
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
                    	    break loop66;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,21,FOLLOW_21_in_ruleListLiteral9496); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4337:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4338:2: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4339:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression9532);
            iv_ruleConstructorCallExpression=ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorCallExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstructorCallExpression9542); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4346:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4349:28: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4350:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4350:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4350:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,81,FOLLOW_81_in_ruleConstructorCallExpression9579); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4354:1: ( (lv_type_1_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4355:1: (lv_type_1_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4355:1: (lv_type_1_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4356:3: lv_type_1_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression9600);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4380:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4381:2: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4382:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression9636);
            iv_ruleTypeSelectExpression=ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeSelectExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTypeSelectExpression9646); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4389:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4392:28: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4393:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4393:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4393:2: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4393:2: ( (lv_name_0_0= 'typeSelect' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4394:1: (lv_name_0_0= 'typeSelect' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4394:1: (lv_name_0_0= 'typeSelect' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4395:3: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,68,FOLLOW_68_in_ruleTypeSelectExpression9689); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleTypeSelectExpression9714); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4412:1: ( (lv_type_2_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4413:1: (lv_type_2_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4413:1: (lv_type_2_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4414:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleTypeSelectExpression9735);
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

            otherlv_3=(Token)match(input,26,FOLLOW_26_in_ruleTypeSelectExpression9747); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4442:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4443:2: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4444:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression9783);
            iv_ruleCollectionExpression=ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionExpression9793); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4451:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4454:28: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4455:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4455:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4455:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4455:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4456:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4456:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4457:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4457:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            int alt68=8;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt68=1;
                }
                break;
            case 70:
                {
                alt68=2;
                }
                break;
            case 71:
                {
                alt68=3;
                }
                break;
            case 72:
                {
                alt68=4;
                }
                break;
            case 73:
                {
                alt68=5;
                }
                break;
            case 74:
                {
                alt68=6;
                }
                break;
            case 75:
                {
                alt68=7;
                }
                break;
            case 76:
                {
                alt68=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }

            switch (alt68) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4458:3: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,69,FOLLOW_69_in_ruleCollectionExpression9838); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4470:8: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,70,FOLLOW_70_in_ruleCollectionExpression9867); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4482:8: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,71,FOLLOW_71_in_ruleCollectionExpression9896); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4494:8: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,72,FOLLOW_72_in_ruleCollectionExpression9925); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4506:8: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,73,FOLLOW_73_in_ruleCollectionExpression9954); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4518:8: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,74,FOLLOW_74_in_ruleCollectionExpression9983); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4530:8: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,75,FOLLOW_75_in_ruleCollectionExpression10012); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4542:8: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,76,FOLLOW_76_in_ruleCollectionExpression10041); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleCollectionExpression10069); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4561:1: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==RULE_ID) ) {
                int LA69_1 = input.LA(2);

                if ( (LA69_1==33) ) {
                    alt69=1;
                }
            }
            switch (alt69) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4561:2: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4561:2: ( (lv_var_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4562:1: (lv_var_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4562:1: (lv_var_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4563:3: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleCollectionExpression10091);
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

                    otherlv_3=(Token)match(input,33,FOLLOW_33_in_ruleCollectionExpression10103); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4583:3: ( (lv_exp_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4584:1: (lv_exp_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4584:1: (lv_exp_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4585:3: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCollectionExpression10126);
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

            otherlv_5=(Token)match(input,26,FOLLOW_26_in_ruleCollectionExpression10138); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4613:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4614:2: (iv_ruleType= ruleType EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4615:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType10174);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType10184); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4622:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4625:28: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4626:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4626:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( ((LA70_0>=82 && LA70_0<=84)) ) {
                alt70=1;
            }
            else if ( (LA70_0==RULE_ID) ) {
                alt70=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }
            switch (alt70) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4627:2: this_CollectionType_0= ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionType_in_ruleType10234);
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4640:2: this_SimpleType_1= ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleType_in_ruleType10264);
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4659:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4660:2: (iv_ruleCollectionType= ruleCollectionType EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4661:2: iv_ruleCollectionType= ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionType_in_entryRuleCollectionType10299);
            iv_ruleCollectionType=ruleCollectionType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionType10309); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4668:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4671:28: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4672:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4672:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4672:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4672:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4673:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4673:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4674:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4674:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            int alt71=3;
            switch ( input.LA(1) ) {
            case 82:
                {
                alt71=1;
                }
                break;
            case 83:
                {
                alt71=2;
                }
                break;
            case 84:
                {
                alt71=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }

            switch (alt71) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4675:3: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,82,FOLLOW_82_in_ruleCollectionType10354); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4687:8: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,83,FOLLOW_83_in_ruleCollectionType10383); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4699:8: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,84,FOLLOW_84_in_ruleCollectionType10412); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,31,FOLLOW_31_in_ruleCollectionType10440); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4718:1: ( (lv_id1_2_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4719:1: (lv_id1_2_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4719:1: (lv_id1_2_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4720:3: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleCollectionType10461);
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

            otherlv_3=(Token)match(input,32,FOLLOW_32_in_ruleCollectionType10473); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4748:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4749:2: (iv_ruleSimpleType= ruleSimpleType EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4750:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleType_in_entryRuleSimpleType10509);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleType10519); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4757:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4760:28: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4761:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4761:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4761:2: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4761:2: ( (lv_id_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4762:1: (lv_id_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4762:1: (lv_id_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4763:3: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType10565);
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

            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4779:2: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==44) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4779:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,44,FOLLOW_44_in_ruleSimpleType10578); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4783:1: ( (lv_id_2_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4784:1: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4784:1: (lv_id_2_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4785:3: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType10599);
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
            	    break loop72;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4809:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4810:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4811:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier10638);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier10649); if (state.failed) return current;

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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4818:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4821:28: (this_ID_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4822:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIdentifier10688); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4837:1: ruleCasing returns [Enumerator current=null] : ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) ) ;
    public final Enumerator ruleCasing() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4839:28: ( ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) ) )
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4840:1: ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4840:1: ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) )
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==85) ) {
                alt73=1;
            }
            else if ( (LA73_0==86) ) {
                alt73=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 73, 0, input);

                throw nvae;
            }
            switch (alt73) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4840:2: (enumLiteral_0= 'sensitive' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4840:2: (enumLiteral_0= 'sensitive' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4840:4: enumLiteral_0= 'sensitive'
                    {
                    enumLiteral_0=(Token)match(input,85,FOLLOW_85_in_ruleCasing10746); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getCasingAccess().getSENSITIVEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getCasingAccess().getSENSITIVEEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4846:6: (enumLiteral_1= 'insensitive' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4846:6: (enumLiteral_1= 'insensitive' )
                    // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:4846:8: enumLiteral_1= 'insensitive'
                    {
                    enumLiteral_1=(Token)match(input,86,FOLLOW_86_in_ruleCasing10763); if (state.failed) return current;
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

    // $ANTLR start synpred34_InternalScope
    public final void synpred34_InternalScope_fragment() throws RecognitionException {   
        EObject lv_names_0_0 = null;


        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1682:2: ( ( (lv_names_0_0= ruleNamingExpression ) ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1682:2: ( (lv_names_0_0= ruleNamingExpression ) )
        {
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1682:2: ( (lv_names_0_0= ruleNamingExpression ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1683:1: (lv_names_0_0= ruleNamingExpression )
        {
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1683:1: (lv_names_0_0= ruleNamingExpression )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1684:3: lv_names_0_0= ruleNamingExpression
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_0_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleNamingExpression_in_synpred34_InternalScope3551);
        lv_names_0_0=ruleNamingExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred34_InternalScope

    // $ANTLR start synpred41_InternalScope
    public final void synpred41_InternalScope_fragment() throws RecognitionException {   
        EObject this_CastedExpression_1 = null;


        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1952:2: (this_CastedExpression_1= ruleCastedExpression )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:1952:2: this_CastedExpression_1= ruleCastedExpression
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleCastedExpression_in_synpred41_InternalScope4223);
        this_CastedExpression_1=ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred41_InternalScope

    // $ANTLR start synpred46_InternalScope
    public final void synpred46_InternalScope_fragment() throws RecognitionException {   
        Token otherlv_4=null;
        EObject lv_elsePart_5_0 = null;


        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2404:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2404:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
        {
        otherlv_4=(Token)match(input,52,FOLLOW_52_in_synpred46_InternalScope5185); if (state.failed) return ;
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2408:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2409:1: (lv_elsePart_5_0= ruleChainedExpression )
        {
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2409:1: (lv_elsePart_5_0= ruleChainedExpression )
        // ../com.avaloq.tools.ddk.xtext.scope/src-gen/com/avaloq/tools/ddk/xtext/scope/parser/antlr/internal/InternalScope.g:2410:3: lv_elsePart_5_0= ruleChainedExpression
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleChainedExpression_in_synpred46_InternalScope5206);
        lv_elsePart_5_0=ruleChainedExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred46_InternalScope

    // Delegated rules

    public final boolean synpred46_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred46_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred41_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred41_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA32 dfa32 = new DFA32(this);
    protected DFA37 dfa37 = new DFA37(this);
    static final String DFA11_eotS =
        "\6\uffff";
    static final String DFA11_eofS =
        "\6\uffff";
    static final String DFA11_minS =
        "\1\7\1\24\1\7\2\uffff\1\24";
    static final String DFA11_maxS =
        "\1\7\1\54\1\7\2\uffff\1\54";
    static final String DFA11_acceptS =
        "\3\uffff\1\2\1\1\1\uffff";
    static final String DFA11_specialS =
        "\6\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1",
            "\1\4\6\uffff\1\3\20\uffff\1\2",
            "\1\5",
            "",
            "",
            "\1\4\6\uffff\1\3\20\uffff\1\2"
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "590:3: ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) )";
        }
    }
    static final String DFA32_eotS =
        "\40\uffff";
    static final String DFA32_eofS =
        "\40\uffff";
    static final String DFA32_minS =
        "\1\4\3\uffff\1\0\33\uffff";
    static final String DFA32_maxS =
        "\1\124\3\uffff\1\0\33\uffff";
    static final String DFA32_acceptS =
        "\1\uffff\1\1\35\uffff\1\2";
    static final String DFA32_specialS =
        "\4\uffff\1\0\33\uffff}>";
    static final String[] DFA32_transitionS = {
            "\4\1\14\uffff\1\1\4\uffff\1\4\10\uffff\1\1\10\uffff\1\1\2\uffff"+
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

    static final short[] DFA32_eot = DFA.unpackEncodedString(DFA32_eotS);
    static final short[] DFA32_eof = DFA.unpackEncodedString(DFA32_eofS);
    static final char[] DFA32_min = DFA.unpackEncodedStringToUnsignedChars(DFA32_minS);
    static final char[] DFA32_max = DFA.unpackEncodedStringToUnsignedChars(DFA32_maxS);
    static final short[] DFA32_accept = DFA.unpackEncodedString(DFA32_acceptS);
    static final short[] DFA32_special = DFA.unpackEncodedString(DFA32_specialS);
    static final short[][] DFA32_transition;

    static {
        int numStates = DFA32_transitionS.length;
        DFA32_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA32_transition[i] = DFA.unpackEncodedString(DFA32_transitionS[i]);
        }
    }

    class DFA32 extends DFA {

        public DFA32(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 32;
            this.eot = DFA32_eot;
            this.eof = DFA32_eof;
            this.min = DFA32_min;
            this.max = DFA32_max;
            this.accept = DFA32_accept;
            this.special = DFA32_special;
            this.transition = DFA32_transition;
        }
        public String getDescription() {
            return "1682:1: ( ( (lv_names_0_0= ruleNamingExpression ) ) | (otherlv_1= '(' ( (lv_names_2_0= ruleNamingExpression ) ) (otherlv_3= ',' ( (lv_names_4_0= ruleNamingExpression ) ) )* otherlv_5= ')' ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA32_4 = input.LA(1);

                         
                        int index32_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_InternalScope()) ) {s = 1;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index32_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 32, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA37_eotS =
        "\36\uffff";
    static final String DFA37_eofS =
        "\36\uffff";
    static final String DFA37_minS =
        "\1\4\1\uffff\1\0\33\uffff";
    static final String DFA37_maxS =
        "\1\124\1\uffff\1\0\33\uffff";
    static final String DFA37_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String DFA37_specialS =
        "\2\uffff\1\0\33\uffff}>";
    static final String[] DFA37_transitionS = {
            "\4\3\14\uffff\1\3\4\uffff\1\2\24\uffff\1\1\3\uffff\1\3\2\uffff"+
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

    static final short[] DFA37_eot = DFA.unpackEncodedString(DFA37_eotS);
    static final short[] DFA37_eof = DFA.unpackEncodedString(DFA37_eofS);
    static final char[] DFA37_min = DFA.unpackEncodedStringToUnsignedChars(DFA37_minS);
    static final char[] DFA37_max = DFA.unpackEncodedStringToUnsignedChars(DFA37_maxS);
    static final short[] DFA37_accept = DFA.unpackEncodedString(DFA37_acceptS);
    static final short[] DFA37_special = DFA.unpackEncodedString(DFA37_specialS);
    static final short[][] DFA37_transition;

    static {
        int numStates = DFA37_transitionS.length;
        DFA37_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA37_transition[i] = DFA.unpackEncodedString(DFA37_transitionS[i]);
        }
    }

    class DFA37 extends DFA {

        public DFA37(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 37;
            this.eot = DFA37_eot;
            this.eof = DFA37_eof;
            this.min = DFA37_min;
            this.max = DFA37_max;
            this.accept = DFA37_accept;
            this.special = DFA37_special;
            this.transition = DFA37_transition;
        }
        public String getDescription() {
            return "1938:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA37_2 = input.LA(1);

                         
                        int index37_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred41_InternalScope()) ) {s = 29;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index37_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 37, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleScopeModel_in_entryRuleScopeModel81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeModel91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleScopeModel128 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleDottedID_in_ruleScopeModel149 = new BitSet(new long[]{0x00000000010F6002L});
    public static final BitSet FOLLOW_13_in_ruleScopeModel162 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleDottedID_in_ruleScopeModel189 = new BitSet(new long[]{0x00000000010F4002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleScopeModel212 = new BitSet(new long[]{0x00000000010F4002L});
    public static final BitSet FOLLOW_ruleExtension_in_ruleScopeModel234 = new BitSet(new long[]{0x00000000010F0002L});
    public static final BitSet FOLLOW_ruleInjection_in_ruleScopeModel256 = new BitSet(new long[]{0x00000000010E0002L});
    public static final BitSet FOLLOW_ruleNamingSection_in_ruleScopeModel278 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_ruleScopeDefinition_in_ruleScopeModel300 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport337 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_ruleImport384 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport408 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_ruleImport421 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleImport442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExtension_in_entryRuleExtension480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExtension490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleExtension527 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExtension548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInjection_in_entryRuleInjection584 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInjection594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleInjection631 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleDottedID_in_ruleInjection652 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleInjection664 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInjection685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingSection_in_entryRuleNamingSection721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamingSection731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleNamingSection781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_ruleCasing_in_ruleNamingSection802 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleNamingSection816 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleNamingSection828 = new BitSet(new long[]{0x0000000000200080L});
    public static final BitSet FOLLOW_ruleNamingDefinition_in_ruleNamingSection849 = new BitSet(new long[]{0x0000000000200080L});
    public static final BitSet FOLLOW_21_in_ruleNamingSection862 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingDefinition_in_entryRuleNamingDefinition898 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamingDefinition908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleNamingDefinition960 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleNamingDefinition972 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleNaming_in_ruleNamingDefinition993 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleNamingDefinition1005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeDefinition_in_entryRuleScopeDefinition1041 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeDefinition1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleScopeDefinition1088 = new BitSet(new long[]{0x0000000002000080L});
    public static final BitSet FOLLOW_25_in_ruleScopeDefinition1101 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleScopeDefinition1122 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleScopeDefinition1134 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1164 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleScopeDefinition1198 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleScopeDefinition1210 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleScopeDefinition1237 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleScopeDefinition1251 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_ruleScopeRule_in_ruleScopeDefinition1272 = new BitSet(new long[]{0x0000000010200000L});
    public static final BitSet FOLLOW_21_in_ruleScopeDefinition1285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeRule_in_entryRuleScopeRule1321 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeRule1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleScopeRule1368 = new BitSet(new long[]{0x0000000040000080L});
    public static final BitSet FOLLOW_ruleScopeContext_in_ruleScopeRule1389 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleScopeRule1401 = new BitSet(new long[]{0x0024482C021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleScopeExpression_in_ruleScopeRule1422 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_29_in_ruleScopeRule1435 = new BitSet(new long[]{0x0024482C021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleScopeExpression_in_ruleScopeRule1456 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_23_in_ruleScopeRule1470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeContext_in_entryRuleScopeContext1506 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeContext1516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleScopeContext1560 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleScopeContext1606 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_31_in_ruleScopeContext1620 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleScopeContext1641 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleScopeContext1653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeExpression_in_entryRuleScopeExpression1691 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeExpression1701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeDelegation_in_ruleScopeExpression1752 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleFactoryExpression_in_ruleScopeExpression1782 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleNamedScopeExpression_in_ruleScopeExpression1812 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_33_in_ruleScopeExpression1825 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleScopeExpression1846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFactoryExpression_in_entryRuleFactoryExpression1884 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFactoryExpression1894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleFactoryExpression1931 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleFactoryExpression1952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleScopeDelegation_in_entryRuleScopeDelegation1988 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleScopeDelegation1998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleScopeDelegation2035 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleScopeDelegation2047 = new BitSet(new long[]{0x00244824021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleScopeDelegation2069 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_ruleGlobalScopeExpression_in_ruleScopeDelegation2096 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleScopeDelegation2110 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleScopeDelegation2137 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleScopeDelegation2151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamedScopeExpression_in_entryRuleNamedScopeExpression2187 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamedScopeExpression2197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalScopeExpression_in_ruleNamedScopeExpression2248 = new BitSet(new long[]{0x0000000000048002L});
    public static final BitSet FOLLOW_ruleSimpleScopeExpression_in_ruleNamedScopeExpression2278 = new BitSet(new long[]{0x0000000000048002L});
    public static final BitSet FOLLOW_18_in_ruleNamedScopeExpression2297 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_ruleCasing_in_ruleNamedScopeExpression2331 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_ruleNamedScopeExpression2346 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleNaming_in_ruleNamedScopeExpression2367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalScopeExpression_in_entryRuleGlobalScopeExpression2405 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalScopeExpression2415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleGlobalScopeExpression2452 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleGlobalScopeExpression2464 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleGlobalScopeExpression2491 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleGlobalScopeExpression2505 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleGlobalScopeExpression2517 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleGlobalScopeExpression2529 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2550 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleGlobalScopeExpression2570 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_39_in_ruleGlobalScopeExpression2588 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleGlobalScopeExpression2614 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleGlobalScopeExpression2626 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleGlobalScopeExpression2647 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleGlobalScopeExpression2663 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleGlobalScopeExpression2675 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleGlobalScopeExpression2687 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleGlobalScopeExpression2699 = new BitSet(new long[]{0x0000000080000080L});
    public static final BitSet FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2720 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleGlobalScopeExpression2733 = new BitSet(new long[]{0x0000000080000080L});
    public static final BitSet FOLLOW_ruleDataExpression_in_ruleGlobalScopeExpression2754 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_26_in_ruleGlobalScopeExpression2768 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleGlobalScopeExpression2783 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleGlobalScopeExpression2795 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleGlobalScopeExpression2807 = new BitSet(new long[]{0x0000000042000080L});
    public static final BitSet FOLLOW_30_in_ruleGlobalScopeExpression2826 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2866 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_25_in_ruleGlobalScopeExpression2885 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2906 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleGlobalScopeExpression2919 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalScopeExpression2940 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_26_in_ruleGlobalScopeExpression2954 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleGlobalScopeExpression2970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDataExpression_in_entryRuleDataExpression3006 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDataExpression3016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMatchDataExpression_in_ruleDataExpression3066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLambdaDataExpression_in_ruleDataExpression3096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMatchDataExpression_in_entryRuleMatchDataExpression3131 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMatchDataExpression3141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleMatchDataExpression3187 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleMatchDataExpression3199 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleMatchDataExpression3220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLambdaDataExpression_in_entryRuleLambdaDataExpression3256 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLambdaDataExpression3266 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleLambdaDataExpression3303 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLambdaDataExpression3324 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleLambdaDataExpression3336 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLambdaDataExpression3357 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleLambdaDataExpression3369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleScopeExpression_in_entryRuleSimpleScopeExpression3405 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleScopeExpression3415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleSimpleScopeExpression3460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNaming_in_entryRuleNaming3495 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNaming3505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingExpression_in_ruleNaming3551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleNaming3570 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleNamingExpression_in_ruleNaming3591 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleNaming3604 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleNamingExpression_in_ruleNaming3625 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_26_in_ruleNaming3639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingExpression_in_entryRuleNamingExpression3676 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNamingExpression3686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleNamingExpression3729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleNamingExpression3767 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleNamingExpression3802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID3840 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID3851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleQualifiedID3898 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_44_in_ruleQualifiedID3917 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleQualifiedID3939 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_ruleDottedID_in_entryRuleDottedID3987 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDottedID3998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleDottedID4045 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleDottedID4064 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleDottedID4086 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression4133 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression4143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_ruleExpression4193 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_ruleExpression4223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_ruleExpression4253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_entryRuleLetExpression4290 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetExpression4300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleLetExpression4337 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLetExpression4358 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleLetExpression4370 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression4391 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_ruleLetExpression4403 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression4424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression4460 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCastedExpression4470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleCastedExpression4507 = new BitSet(new long[]{0x0000000000000080L,0x00000000001C0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleCastedExpression4528 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleCastedExpression4540 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCastedExpression4561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_entryRuleChainExpression4597 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainExpression4607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression4657 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_48_in_ruleChainExpression4681 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression4702 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression4740 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainedExpression4750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression4800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression4830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_ruleChainedExpression4860 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri4895 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionTri4905 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleIfExpressionTri4955 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_49_in_ruleIfExpressionTri4979 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri5000 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_ruleIfExpressionTri5012 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri5033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw5071 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionKw5081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_ruleIfExpressionKw5118 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5139 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_51_in_ruleIfExpressionKw5151 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5172 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_52_in_ruleIfExpressionKw5185 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw5206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression5244 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchExpression5254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleSwitchExpression5291 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_25_in_ruleSwitchExpression5304 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression5325 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleSwitchExpression5337 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleSwitchExpression5351 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_ruleCase_in_ruleSwitchExpression5372 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_54_in_ruleSwitchExpression5385 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_ruleSwitchExpression5397 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression5418 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleSwitchExpression5430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCase_in_entryRuleCase5466 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCase5476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleCase5513 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase5534 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_ruleCase5546 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase5567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression5603 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression5613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression5663 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_55_in_ruleOrExpression5693 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression5727 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression5765 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression5775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression5825 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_56_in_ruleAndExpression5855 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression5889 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression5927 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpression5937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression5987 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_57_in_ruleImpliesExpression6017 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression6051 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression6089 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression6099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression6149 = new BitSet(new long[]{0xFC00000000000002L});
    public static final BitSet FOLLOW_58_in_ruleRelationalExpression6181 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_59_in_ruleRelationalExpression6210 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_60_in_ruleRelationalExpression6239 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_61_in_ruleRelationalExpression6268 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_62_in_ruleRelationalExpression6297 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_63_in_ruleRelationalExpression6326 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression6363 = new BitSet(new long[]{0xFC00000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression6401 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression6411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6461 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_64_in_ruleAdditiveExpression6493 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_65_in_ruleAdditiveExpression6522 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression6559 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression6597 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression6607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6657 = new BitSet(new long[]{0x0000000040000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_30_in_ruleMultiplicativeExpression6689 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_66_in_ruleMultiplicativeExpression6718 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression6755 = new BitSet(new long[]{0x0000000040000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression6793 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression6803 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression6853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression6883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression6918 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression6928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_ruleUnaryExpression6973 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_65_in_ruleUnaryExpression7002 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression7039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression7075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression7085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression7135 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleInfixExpression7160 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression7181 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression7193 = new BitSet(new long[]{0x00244804061000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression7215 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleInfixExpression7228 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression7249 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression7265 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleInfixExpression7297 = new BitSet(new long[]{0x0000000000000080L,0x00000000001C0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression7318 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleInfixExpression7350 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleInfixExpression7368 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression7393 = new BitSet(new long[]{0x0000000000000080L,0x00000000001C0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression7414 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression7426 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleInfixExpression7458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001FE0L});
    public static final BitSet FOLLOW_69_in_ruleInfixExpression7478 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_70_in_ruleInfixExpression7507 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_71_in_ruleInfixExpression7536 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_72_in_ruleInfixExpression7565 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_73_in_ruleInfixExpression7594 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_74_in_ruleInfixExpression7623 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_75_in_ruleInfixExpression7652 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_76_in_ruleInfixExpression7681 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleInfixExpression7709 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression7731 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleInfixExpression7743 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression7766 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression7778 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression7817 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression7827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression7877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_rulePrimaryExpression7907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_rulePrimaryExpression7937 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression7967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression7997 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression8027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral8062 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral8072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral8122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral8152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral8182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_ruleLiteral8212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral8242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral8277 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral8287 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleBooleanLiteral8331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleBooleanLiteral8360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral8411 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral8421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral8462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral8502 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral8512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleNullLiteral8554 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral8602 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealLiteral8612 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_in_ruleRealLiteral8653 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral8693 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral8703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral8744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression8784 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression8794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleParanthesizedExpression8831 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression8856 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleParanthesizedExpression8867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression8903 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression8913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleGlobalVarExpression8950 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression8971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall9007 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureCall9017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_ruleFeatureCall9067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_ruleFeatureCall9093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_ruleFeatureCall9124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall9154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_entryRuleOperationCall9189 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCall9199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleOperationCall9245 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleOperationCall9257 = new BitSet(new long[]{0x00244804061000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall9279 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_36_in_ruleOperationCall9292 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall9313 = new BitSet(new long[]{0x0000001004000000L});
    public static final BitSet FOLLOW_26_in_ruleOperationCall9329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_entryRuleListLiteral9365 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListLiteral9375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleListLiteral9424 = new BitSet(new long[]{0x00244804023000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral9446 = new BitSet(new long[]{0x0000001000200000L});
    public static final BitSet FOLLOW_36_in_ruleListLiteral9459 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral9480 = new BitSet(new long[]{0x0000001000200000L});
    public static final BitSet FOLLOW_21_in_ruleListLiteral9496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression9532 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstructorCallExpression9542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleConstructorCallExpression9579 = new BitSet(new long[]{0x0000000000000080L,0x00000000001C0000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression9600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression9636 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTypeSelectExpression9646 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleTypeSelectExpression9689 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleTypeSelectExpression9714 = new BitSet(new long[]{0x0000000000000080L,0x00000000001C0000L});
    public static final BitSet FOLLOW_ruleType_in_ruleTypeSelectExpression9735 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleTypeSelectExpression9747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression9783 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionExpression9793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_ruleCollectionExpression9838 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_70_in_ruleCollectionExpression9867 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_71_in_ruleCollectionExpression9896 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_72_in_ruleCollectionExpression9925 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_73_in_ruleCollectionExpression9954 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_74_in_ruleCollectionExpression9983 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_75_in_ruleCollectionExpression10012 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_76_in_ruleCollectionExpression10041 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleCollectionExpression10069 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleCollectionExpression10091 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleCollectionExpression10103 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCollectionExpression10126 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleCollectionExpression10138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType10174 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType10184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_ruleType10234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleType10264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_entryRuleCollectionType10299 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionType10309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_ruleCollectionType10354 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_83_in_ruleCollectionType10383 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_84_in_ruleCollectionType10412 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleCollectionType10440 = new BitSet(new long[]{0x0000000000000080L,0x00000000001C0000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleCollectionType10461 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleCollectionType10473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_entryRuleSimpleType10509 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleType10519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType10565 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_44_in_ruleSimpleType10578 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType10599 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier10638 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier10649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIdentifier10688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_ruleCasing10746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_ruleCasing10763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNamingExpression_in_synpred34_InternalScope3551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_synpred41_InternalScope4223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_synpred46_InternalScope5185 = new BitSet(new long[]{0x00244804021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_synpred46_InternalScope5206 = new BitSet(new long[]{0x0000000000000002L});

}