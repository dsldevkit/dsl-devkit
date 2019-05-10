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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_INT", "RULE_REAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'scoping'", "'with'", "'import'", "'as'", "'extension'", "'inject'", "'case'", "'naming'", "'{'", "'}'", "'='", "';'", "'scope'", "'('", "')'", "'#'", "'context'", "'>>'", "'*'", "'['", "']'", "'factory'", "'scopeof'", "','", "'find'", "'key'", "'recursive'", "'prefix'", "'data'", "'domains'", "'|'", "'export'", "'::'", "'.'", "'let'", "':'", "'->'", "'?'", "'if'", "'then'", "'else'", "'switch'", "'default'", "'||'", "'&&'", "'implies'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'/'", "'!'", "'typeSelect'", "'collect'", "'select'", "'selectFirst'", "'reject'", "'exists'", "'notExists'", "'sortBy'", "'forAll'", "'true'", "'false'", "'null'", "'GLOBALVAR'", "'new'", "'Collection'", "'List'", "'Set'", "'sensitive'", "'insensitive'"
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
    public String getGrammarFileName() { return "InternalScope.g"; }



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
    // InternalScope.g:68:1: entryRuleScopeModel returns [EObject current=null] : iv_ruleScopeModel= ruleScopeModel EOF ;
    public final EObject entryRuleScopeModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeModel = null;


        try {
            // InternalScope.g:69:2: (iv_ruleScopeModel= ruleScopeModel EOF )
            // InternalScope.g:70:2: iv_ruleScopeModel= ruleScopeModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeModelRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScopeModel=ruleScopeModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeModel; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:77:1: ruleScopeModel returns [EObject current=null] : (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* ) ;
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
            // InternalScope.g:80:28: ( (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* ) )
            // InternalScope.g:81:1: (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* )
            {
            // InternalScope.g:81:1: (otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )* )
            // InternalScope.g:81:3: otherlv_0= 'scoping' ( (lv_name_1_0= ruleDottedID ) ) (otherlv_2= 'with' ( ( ruleDottedID ) ) )? ( (lv_imports_4_0= ruleImport ) )* ( (lv_extensions_5_0= ruleExtension ) )* ( (lv_injections_6_0= ruleInjection ) )* ( (lv_naming_7_0= ruleNamingSection ) )? ( (lv_scopes_8_0= ruleScopeDefinition ) )*
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeModelAccess().getScopingKeyword_0());
                  
            }
            // InternalScope.g:85:1: ( (lv_name_1_0= ruleDottedID ) )
            // InternalScope.g:86:1: (lv_name_1_0= ruleDottedID )
            {
            // InternalScope.g:86:1: (lv_name_1_0= ruleDottedID )
            // InternalScope.g:87:3: lv_name_1_0= ruleDottedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeModelAccess().getNameDottedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_4);
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
                      		"com.avaloq.tools.ddk.xtext.scope.Scope.DottedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalScope.g:103:2: (otherlv_2= 'with' ( ( ruleDottedID ) ) )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalScope.g:103:4: otherlv_2= 'with' ( ( ruleDottedID ) )
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getScopeModelAccess().getWithKeyword_2_0());
                          
                    }
                    // InternalScope.g:107:1: ( ( ruleDottedID ) )
                    // InternalScope.g:108:1: ( ruleDottedID )
                    {
                    // InternalScope.g:108:1: ( ruleDottedID )
                    // InternalScope.g:109:3: ruleDottedID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeModelRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeModelAccess().getIncludedScopesScopeModelCrossReference_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_5);
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

            // InternalScope.g:122:4: ( (lv_imports_4_0= ruleImport ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==14) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalScope.g:123:1: (lv_imports_4_0= ruleImport )
            	    {
            	    // InternalScope.g:123:1: (lv_imports_4_0= ruleImport )
            	    // InternalScope.g:124:3: lv_imports_4_0= ruleImport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getImportsImportParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_5);
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
            	              		"com.avaloq.tools.ddk.xtext.scope.Scope.Import");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalScope.g:140:3: ( (lv_extensions_5_0= ruleExtension ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalScope.g:141:1: (lv_extensions_5_0= ruleExtension )
            	    {
            	    // InternalScope.g:141:1: (lv_extensions_5_0= ruleExtension )
            	    // InternalScope.g:142:3: lv_extensions_5_0= ruleExtension
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getExtensionsExtensionParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_6);
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
            	              		"com.avaloq.tools.ddk.xtext.scope.Scope.Extension");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalScope.g:158:3: ( (lv_injections_6_0= ruleInjection ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==17) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalScope.g:159:1: (lv_injections_6_0= ruleInjection )
            	    {
            	    // InternalScope.g:159:1: (lv_injections_6_0= ruleInjection )
            	    // InternalScope.g:160:3: lv_injections_6_0= ruleInjection
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getInjectionsInjectionParserRuleCall_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_7);
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
            	              		"com.avaloq.tools.ddk.xtext.scope.Scope.Injection");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // InternalScope.g:176:3: ( (lv_naming_7_0= ruleNamingSection ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=18 && LA5_0<=19)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalScope.g:177:1: (lv_naming_7_0= ruleNamingSection )
                    {
                    // InternalScope.g:177:1: (lv_naming_7_0= ruleNamingSection )
                    // InternalScope.g:178:3: lv_naming_7_0= ruleNamingSection
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeModelAccess().getNamingNamingSectionParserRuleCall_6_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_8);
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
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.NamingSection");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }
                    break;

            }

            // InternalScope.g:194:3: ( (lv_scopes_8_0= ruleScopeDefinition ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==24) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalScope.g:195:1: (lv_scopes_8_0= ruleScopeDefinition )
            	    {
            	    // InternalScope.g:195:1: (lv_scopes_8_0= ruleScopeDefinition )
            	    // InternalScope.g:196:3: lv_scopes_8_0= ruleScopeDefinition
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeModelAccess().getScopesScopeDefinitionParserRuleCall_7_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_8);
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
            	              		"com.avaloq.tools.ddk.xtext.scope.Scope.ScopeDefinition");
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
    // InternalScope.g:220:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalScope.g:221:2: (iv_ruleImport= ruleImport EOF )
            // InternalScope.g:222:2: iv_ruleImport= ruleImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleImport=ruleImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImport; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:229:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:232:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? ) )
            // InternalScope.g:233:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? )
            {
            // InternalScope.g:233:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )? )
            // InternalScope.g:233:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )?
            {
            otherlv_0=(Token)match(input,14,FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                  
            }
            // InternalScope.g:237:1: ( (otherlv_1= RULE_STRING ) )
            // InternalScope.g:238:1: (otherlv_1= RULE_STRING )
            {
            // InternalScope.g:238:1: (otherlv_1= RULE_STRING )
            // InternalScope.g:239:3: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getImportRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
              	
            }

            }


            }

            // InternalScope.g:250:2: (otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==15) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalScope.g:250:4: otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) )
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportAccess().getAsKeyword_2_0());
                          
                    }
                    // InternalScope.g:254:1: ( (lv_name_3_0= ruleIdentifier ) )
                    // InternalScope.g:255:1: (lv_name_3_0= ruleIdentifier )
                    {
                    // InternalScope.g:255:1: (lv_name_3_0= ruleIdentifier )
                    // InternalScope.g:256:3: lv_name_3_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getImportAccess().getNameIdentifierParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
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
    // InternalScope.g:280:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // InternalScope.g:281:2: (iv_ruleExtension= ruleExtension EOF )
            // InternalScope.g:282:2: iv_ruleExtension= ruleExtension EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExtensionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExtension=ruleExtension();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExtension; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:289:1: ruleExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_extension_1_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:292:28: ( (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) )
            // InternalScope.g:293:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            {
            // InternalScope.g:293:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            // InternalScope.g:293:3: otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,16,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExtensionAccess().getExtensionKeyword_0());
                  
            }
            // InternalScope.g:297:1: ( (lv_extension_1_0= ruleQualifiedID ) )
            // InternalScope.g:298:1: (lv_extension_1_0= ruleQualifiedID )
            {
            // InternalScope.g:298:1: (lv_extension_1_0= ruleQualifiedID )
            // InternalScope.g:299:3: lv_extension_1_0= ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExtensionAccess().getExtensionQualifiedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.scope.Scope.QualifiedID");
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
    // InternalScope.g:323:1: entryRuleInjection returns [EObject current=null] : iv_ruleInjection= ruleInjection EOF ;
    public final EObject entryRuleInjection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInjection = null;


        try {
            // InternalScope.g:324:2: (iv_ruleInjection= ruleInjection EOF )
            // InternalScope.g:325:2: iv_ruleInjection= ruleInjection EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInjectionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInjection=ruleInjection();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInjection; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:332:1: ruleInjection returns [EObject current=null] : (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) ) ;
    public final EObject ruleInjection() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_type_1_0 = null;

        AntlrDatatypeRuleToken lv_name_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:335:28: ( (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) ) )
            // InternalScope.g:336:1: (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )
            {
            // InternalScope.g:336:1: (otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) ) )
            // InternalScope.g:336:3: otherlv_0= 'inject' ( (lv_type_1_0= ruleDottedID ) ) otherlv_2= 'as' ( (lv_name_3_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInjectionAccess().getInjectKeyword_0());
                  
            }
            // InternalScope.g:340:1: ( (lv_type_1_0= ruleDottedID ) )
            // InternalScope.g:341:1: (lv_type_1_0= ruleDottedID )
            {
            // InternalScope.g:341:1: (lv_type_1_0= ruleDottedID )
            // InternalScope.g:342:3: lv_type_1_0= ruleDottedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInjectionAccess().getTypeDottedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_11);
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
                      		"com.avaloq.tools.ddk.xtext.scope.Scope.DottedID");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getInjectionAccess().getAsKeyword_2());
                  
            }
            // InternalScope.g:362:1: ( (lv_name_3_0= ruleIdentifier ) )
            // InternalScope.g:363:1: (lv_name_3_0= ruleIdentifier )
            {
            // InternalScope.g:363:1: (lv_name_3_0= ruleIdentifier )
            // InternalScope.g:364:3: lv_name_3_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInjectionAccess().getNameIdentifierParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
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
    // InternalScope.g:388:1: entryRuleNamingSection returns [EObject current=null] : iv_ruleNamingSection= ruleNamingSection EOF ;
    public final EObject entryRuleNamingSection() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingSection = null;


        try {
            // InternalScope.g:389:2: (iv_ruleNamingSection= ruleNamingSection EOF )
            // InternalScope.g:390:2: iv_ruleNamingSection= ruleNamingSection EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingSectionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNamingSection=ruleNamingSection();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingSection; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:397:1: ruleNamingSection returns [EObject current=null] : ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' ) ;
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
            // InternalScope.g:400:28: ( ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' ) )
            // InternalScope.g:401:1: ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' )
            {
            // InternalScope.g:401:1: ( () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}' )
            // InternalScope.g:401:2: () (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )? otherlv_3= 'naming' otherlv_4= '{' ( (lv_namings_5_0= ruleNamingDefinition ) )* otherlv_6= '}'
            {
            // InternalScope.g:401:2: ()
            // InternalScope.g:402:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getNamingSectionAccess().getNamingSectionAction_0(),
                          current);
                  
            }

            }

            // InternalScope.g:407:2: (otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==18) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalScope.g:407:4: otherlv_1= 'case' ( (lv_casing_2_0= ruleCasing ) )
                    {
                    otherlv_1=(Token)match(input,18,FOLLOW_12); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getNamingSectionAccess().getCaseKeyword_1_0());
                          
                    }
                    // InternalScope.g:411:1: ( (lv_casing_2_0= ruleCasing ) )
                    // InternalScope.g:412:1: (lv_casing_2_0= ruleCasing )
                    {
                    // InternalScope.g:412:1: (lv_casing_2_0= ruleCasing )
                    // InternalScope.g:413:3: lv_casing_2_0= ruleCasing
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingSectionAccess().getCasingCasingEnumRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_13);
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
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.Casing");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,19,FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getNamingSectionAccess().getNamingKeyword_2());
                  
            }
            otherlv_4=(Token)match(input,20,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getNamingSectionAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // InternalScope.g:437:1: ( (lv_namings_5_0= ruleNamingDefinition ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalScope.g:438:1: (lv_namings_5_0= ruleNamingDefinition )
            	    {
            	    // InternalScope.g:438:1: (lv_namings_5_0= ruleNamingDefinition )
            	    // InternalScope.g:439:3: lv_namings_5_0= ruleNamingDefinition
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getNamingSectionAccess().getNamingsNamingDefinitionParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_15);
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
            	              		"com.avaloq.tools.ddk.xtext.scope.Scope.NamingDefinition");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            otherlv_6=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:467:1: entryRuleNamingDefinition returns [EObject current=null] : iv_ruleNamingDefinition= ruleNamingDefinition EOF ;
    public final EObject entryRuleNamingDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingDefinition = null;


        try {
            // InternalScope.g:468:2: (iv_ruleNamingDefinition= ruleNamingDefinition EOF )
            // InternalScope.g:469:2: iv_ruleNamingDefinition= ruleNamingDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingDefinitionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNamingDefinition=ruleNamingDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingDefinition; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:476:1: ruleNamingDefinition returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' ) ;
    public final EObject ruleNamingDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_naming_2_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:479:28: ( ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' ) )
            // InternalScope.g:480:1: ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' )
            {
            // InternalScope.g:480:1: ( ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';' )
            // InternalScope.g:480:2: ( ( ruleQualifiedID ) ) otherlv_1= '=' ( (lv_naming_2_0= ruleNaming ) ) otherlv_3= ';'
            {
            // InternalScope.g:480:2: ( ( ruleQualifiedID ) )
            // InternalScope.g:481:1: ( ruleQualifiedID )
            {
            // InternalScope.g:481:1: ( ruleQualifiedID )
            // InternalScope.g:482:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getNamingDefinitionRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNamingDefinitionAccess().getTypeEClassCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_16);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getNamingDefinitionAccess().getEqualsSignKeyword_1());
                  
            }
            // InternalScope.g:499:1: ( (lv_naming_2_0= ruleNaming ) )
            // InternalScope.g:500:1: (lv_naming_2_0= ruleNaming )
            {
            // InternalScope.g:500:1: (lv_naming_2_0= ruleNaming )
            // InternalScope.g:501:3: lv_naming_2_0= ruleNaming
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getNamingDefinitionAccess().getNamingNamingParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_18);
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
                      		"com.avaloq.tools.ddk.xtext.scope.Scope.Naming");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,23,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:529:1: entryRuleScopeDefinition returns [EObject current=null] : iv_ruleScopeDefinition= ruleScopeDefinition EOF ;
    public final EObject entryRuleScopeDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeDefinition = null;


        try {
            // InternalScope.g:530:2: (iv_ruleScopeDefinition= ruleScopeDefinition EOF )
            // InternalScope.g:531:2: iv_ruleScopeDefinition= ruleScopeDefinition EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeDefinitionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScopeDefinition=ruleScopeDefinition();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeDefinition; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:538:1: ruleScopeDefinition returns [EObject current=null] : (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' ) ;
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
            // InternalScope.g:541:28: ( (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' ) )
            // InternalScope.g:542:1: (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' )
            {
            // InternalScope.g:542:1: (otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}' )
            // InternalScope.g:542:3: otherlv_0= 'scope' (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )? ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) ) otherlv_8= '{' ( (lv_rules_9_0= ruleScopeRule ) )+ otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,24,FOLLOW_19); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeDefinitionAccess().getScopeKeyword_0());
                  
            }
            // InternalScope.g:546:1: (otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==25) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalScope.g:546:3: otherlv_1= '(' ( (lv_name_2_0= ruleIdentifier ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,25,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getScopeDefinitionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // InternalScope.g:550:1: ( (lv_name_2_0= ruleIdentifier ) )
                    // InternalScope.g:551:1: (lv_name_2_0= ruleIdentifier )
                    {
                    // InternalScope.g:551:1: (lv_name_2_0= ruleIdentifier )
                    // InternalScope.g:552:3: lv_name_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getNameIdentifierParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_20);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,26,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getScopeDefinitionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            // InternalScope.g:572:3: ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // InternalScope.g:572:4: ( ( ruleQualifiedID ) )
                    {
                    // InternalScope.g:572:4: ( ( ruleQualifiedID ) )
                    // InternalScope.g:573:1: ( ruleQualifiedID )
                    {
                    // InternalScope.g:573:1: ( ruleQualifiedID )
                    // InternalScope.g:574:3: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDefinitionRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getTargetTypeEClassCrossReference_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_14);
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
                    // InternalScope.g:588:6: ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) )
                    {
                    // InternalScope.g:588:6: ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) )
                    // InternalScope.g:588:7: ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) )
                    {
                    // InternalScope.g:588:7: ( ( ruleQualifiedID ) )
                    // InternalScope.g:589:1: ( ruleQualifiedID )
                    {
                    // InternalScope.g:589:1: ( ruleQualifiedID )
                    // InternalScope.g:590:3: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDefinitionRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getContextTypeEClassCrossReference_2_1_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_21);
                    ruleQualifiedID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_6=(Token)match(input,27,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getScopeDefinitionAccess().getNumberSignKeyword_2_1_1());
                          
                    }
                    // InternalScope.g:607:1: ( ( ruleIdentifier ) )
                    // InternalScope.g:608:1: ( ruleIdentifier )
                    {
                    // InternalScope.g:608:1: ( ruleIdentifier )
                    // InternalScope.g:609:3: ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDefinitionRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getReferenceEReferenceCrossReference_2_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_14);
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

            otherlv_8=(Token)match(input,20,FOLLOW_22); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getScopeDefinitionAccess().getLeftCurlyBracketKeyword_3());
                  
            }
            // InternalScope.g:626:1: ( (lv_rules_9_0= ruleScopeRule ) )+
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
            	    // InternalScope.g:627:1: (lv_rules_9_0= ruleScopeRule )
            	    {
            	    // InternalScope.g:627:1: (lv_rules_9_0= ruleScopeRule )
            	    // InternalScope.g:628:3: lv_rules_9_0= ruleScopeRule
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeDefinitionAccess().getRulesScopeRuleParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_23);
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
            	              		"com.avaloq.tools.ddk.xtext.scope.Scope.ScopeRule");
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

            otherlv_10=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:656:1: entryRuleScopeRule returns [EObject current=null] : iv_ruleScopeRule= ruleScopeRule EOF ;
    public final EObject entryRuleScopeRule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeRule = null;


        try {
            // InternalScope.g:657:2: (iv_ruleScopeRule= ruleScopeRule EOF )
            // InternalScope.g:658:2: iv_ruleScopeRule= ruleScopeRule EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeRuleRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScopeRule=ruleScopeRule();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeRule; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:665:1: ruleScopeRule returns [EObject current=null] : (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' ) ;
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
            // InternalScope.g:668:28: ( (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' ) )
            // InternalScope.g:669:1: (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' )
            {
            // InternalScope.g:669:1: (otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';' )
            // InternalScope.g:669:3: otherlv_0= 'context' ( (lv_context_1_0= ruleScopeContext ) ) otherlv_2= '=' ( (lv_exprs_3_0= ruleScopeExpression ) ) (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )* otherlv_6= ';'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_24); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeRuleAccess().getContextKeyword_0());
                  
            }
            // InternalScope.g:673:1: ( (lv_context_1_0= ruleScopeContext ) )
            // InternalScope.g:674:1: (lv_context_1_0= ruleScopeContext )
            {
            // InternalScope.g:674:1: (lv_context_1_0= ruleScopeContext )
            // InternalScope.g:675:3: lv_context_1_0= ruleScopeContext
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeRuleAccess().getContextScopeContextParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_16);
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
                      		"com.avaloq.tools.ddk.xtext.scope.Scope.ScopeContext");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,22,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getScopeRuleAccess().getEqualsSignKeyword_2());
                  
            }
            // InternalScope.g:695:1: ( (lv_exprs_3_0= ruleScopeExpression ) )
            // InternalScope.g:696:1: (lv_exprs_3_0= ruleScopeExpression )
            {
            // InternalScope.g:696:1: (lv_exprs_3_0= ruleScopeExpression )
            // InternalScope.g:697:3: lv_exprs_3_0= ruleScopeExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getScopeRuleAccess().getExprsScopeExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_26);
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
                      		"com.avaloq.tools.ddk.xtext.scope.Scope.ScopeExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalScope.g:713:2: (otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==29) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalScope.g:713:4: otherlv_4= '>>' ( (lv_exprs_5_0= ruleScopeExpression ) )
            	    {
            	    otherlv_4=(Token)match(input,29,FOLLOW_25); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getScopeRuleAccess().getGreaterThanSignGreaterThanSignKeyword_4_0());
            	          
            	    }
            	    // InternalScope.g:717:1: ( (lv_exprs_5_0= ruleScopeExpression ) )
            	    // InternalScope.g:718:1: (lv_exprs_5_0= ruleScopeExpression )
            	    {
            	    // InternalScope.g:718:1: (lv_exprs_5_0= ruleScopeExpression )
            	    // InternalScope.g:719:3: lv_exprs_5_0= ruleScopeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getScopeRuleAccess().getExprsScopeExpressionParserRuleCall_4_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_26);
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
            	              		"com.avaloq.tools.ddk.xtext.scope.Scope.ScopeExpression");
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

            otherlv_6=(Token)match(input,23,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:747:1: entryRuleScopeContext returns [EObject current=null] : iv_ruleScopeContext= ruleScopeContext EOF ;
    public final EObject entryRuleScopeContext() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeContext = null;


        try {
            // InternalScope.g:748:2: (iv_ruleScopeContext= ruleScopeContext EOF )
            // InternalScope.g:749:2: iv_ruleScopeContext= ruleScopeContext EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeContextRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScopeContext=ruleScopeContext();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeContext; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:756:1: ruleScopeContext returns [EObject current=null] : ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? ) ;
    public final EObject ruleScopeContext() throws RecognitionException {
        EObject current = null;

        Token lv_global_0_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_guard_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:759:28: ( ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? ) )
            // InternalScope.g:760:1: ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? )
            {
            // InternalScope.g:760:1: ( ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )? )
            // InternalScope.g:760:2: ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) ) (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )?
            {
            // InternalScope.g:760:2: ( ( (lv_global_0_0= '*' ) ) | ( ( ruleQualifiedID ) ) )
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
                    // InternalScope.g:760:3: ( (lv_global_0_0= '*' ) )
                    {
                    // InternalScope.g:760:3: ( (lv_global_0_0= '*' ) )
                    // InternalScope.g:761:1: (lv_global_0_0= '*' )
                    {
                    // InternalScope.g:761:1: (lv_global_0_0= '*' )
                    // InternalScope.g:762:3: lv_global_0_0= '*'
                    {
                    lv_global_0_0=(Token)match(input,30,FOLLOW_27); if (state.failed) return current;
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
                    // InternalScope.g:776:6: ( ( ruleQualifiedID ) )
                    {
                    // InternalScope.g:776:6: ( ( ruleQualifiedID ) )
                    // InternalScope.g:777:1: ( ruleQualifiedID )
                    {
                    // InternalScope.g:777:1: ( ruleQualifiedID )
                    // InternalScope.g:778:3: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeContextRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeContextAccess().getContextTypeEClassCrossReference_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_27);
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

            // InternalScope.g:791:3: (otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==31) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalScope.g:791:5: otherlv_2= '[' ( (lv_guard_3_0= ruleExpression ) ) otherlv_4= ']'
                    {
                    otherlv_2=(Token)match(input,31,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getScopeContextAccess().getLeftSquareBracketKeyword_1_0());
                          
                    }
                    // InternalScope.g:795:1: ( (lv_guard_3_0= ruleExpression ) )
                    // InternalScope.g:796:1: (lv_guard_3_0= ruleExpression )
                    {
                    // InternalScope.g:796:1: (lv_guard_3_0= ruleExpression )
                    // InternalScope.g:797:3: lv_guard_3_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeContextAccess().getGuardExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_28);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:825:1: entryRuleScopeExpression returns [EObject current=null] : iv_ruleScopeExpression= ruleScopeExpression EOF ;
    public final EObject entryRuleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeExpression = null;


        try {
            // InternalScope.g:826:2: (iv_ruleScopeExpression= ruleScopeExpression EOF )
            // InternalScope.g:827:2: iv_ruleScopeExpression= ruleScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScopeExpression=ruleScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:834:1: ruleScopeExpression returns [EObject current=null] : (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) ;
    public final EObject ruleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject this_ScopeDelegation_0 = null;

        EObject this_FactoryExpression_1 = null;

        EObject this_NamedScopeExpression_2 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:837:28: ( (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression ) )
            // InternalScope.g:838:1: (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression )
            {
            // InternalScope.g:838:1: (this_ScopeDelegation_0= ruleScopeDelegation | this_FactoryExpression_1= ruleFactoryExpression | this_NamedScopeExpression_2= ruleNamedScopeExpression )
            int alt16=3;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt16=1;
                }
                break;
            case 33:
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
            case 36:
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
                    // InternalScope.g:839:5: this_ScopeDelegation_0= ruleScopeDelegation
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getScopeDelegationParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:849:5: this_FactoryExpression_1= ruleFactoryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getFactoryExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:859:5: this_NamedScopeExpression_2= ruleNamedScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getScopeExpressionAccess().getNamedScopeExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:875:1: entryRuleFactoryExpression returns [EObject current=null] : iv_ruleFactoryExpression= ruleFactoryExpression EOF ;
    public final EObject entryRuleFactoryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFactoryExpression = null;


        try {
            // InternalScope.g:876:2: (iv_ruleFactoryExpression= ruleFactoryExpression EOF )
            // InternalScope.g:877:2: iv_ruleFactoryExpression= ruleFactoryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFactoryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFactoryExpression=ruleFactoryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFactoryExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:884:1: ruleFactoryExpression returns [EObject current=null] : (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) ) ;
    public final EObject ruleFactoryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_expr_1_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:887:28: ( (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) ) )
            // InternalScope.g:888:1: (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) )
            {
            // InternalScope.g:888:1: (otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) ) )
            // InternalScope.g:888:3: otherlv_0= 'factory' ( (lv_expr_1_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,33,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getFactoryExpressionAccess().getFactoryKeyword_0());
                  
            }
            // InternalScope.g:892:1: ( (lv_expr_1_0= ruleExpression ) )
            // InternalScope.g:893:1: (lv_expr_1_0= ruleExpression )
            {
            // InternalScope.g:893:1: (lv_expr_1_0= ruleExpression )
            // InternalScope.g:894:3: lv_expr_1_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getFactoryExpressionAccess().getExprExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
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
    // InternalScope.g:918:1: entryRuleScopeDelegation returns [EObject current=null] : iv_ruleScopeDelegation= ruleScopeDelegation EOF ;
    public final EObject entryRuleScopeDelegation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScopeDelegation = null;


        try {
            // InternalScope.g:919:2: (iv_ruleScopeDelegation= ruleScopeDelegation EOF )
            // InternalScope.g:920:2: iv_ruleScopeDelegation= ruleScopeDelegation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getScopeDelegationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleScopeDelegation=ruleScopeDelegation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleScopeDelegation; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:927:1: ruleScopeDelegation returns [EObject current=null] : (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' ) ;
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
            // InternalScope.g:930:28: ( (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' ) )
            // InternalScope.g:931:1: (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' )
            {
            // InternalScope.g:931:1: (otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')' )
            // InternalScope.g:931:3: otherlv_0= 'scopeof' otherlv_1= '(' ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) ) (otherlv_4= ',' ( ( ruleIdentifier ) ) )? otherlv_6= ')'
            {
            otherlv_0=(Token)match(input,34,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getScopeDelegationAccess().getScopeofKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,25,FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getScopeDelegationAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalScope.g:939:1: ( ( (lv_delegate_2_0= ruleExpression ) ) | ( (lv_external_3_0= ruleGlobalScopeExpression ) ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( ((LA17_0>=RULE_STRING && LA17_0<=RULE_ID)||LA17_0==20||LA17_0==25||LA17_0==46||LA17_0==50||LA17_0==53||LA17_0==65||(LA17_0>=67 && LA17_0<=84)) ) {
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
                    // InternalScope.g:939:2: ( (lv_delegate_2_0= ruleExpression ) )
                    {
                    // InternalScope.g:939:2: ( (lv_delegate_2_0= ruleExpression ) )
                    // InternalScope.g:940:1: (lv_delegate_2_0= ruleExpression )
                    {
                    // InternalScope.g:940:1: (lv_delegate_2_0= ruleExpression )
                    // InternalScope.g:941:3: lv_delegate_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getDelegateExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalScope.g:958:6: ( (lv_external_3_0= ruleGlobalScopeExpression ) )
                    {
                    // InternalScope.g:958:6: ( (lv_external_3_0= ruleGlobalScopeExpression ) )
                    // InternalScope.g:959:1: (lv_external_3_0= ruleGlobalScopeExpression )
                    {
                    // InternalScope.g:959:1: (lv_external_3_0= ruleGlobalScopeExpression )
                    // InternalScope.g:960:3: lv_external_3_0= ruleGlobalScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getExternalGlobalScopeExpressionParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
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
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.GlobalScopeExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalScope.g:976:3: (otherlv_4= ',' ( ( ruleIdentifier ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==35) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalScope.g:976:5: otherlv_4= ',' ( ( ruleIdentifier ) )
                    {
                    otherlv_4=(Token)match(input,35,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getScopeDelegationAccess().getCommaKeyword_3_0());
                          
                    }
                    // InternalScope.g:980:1: ( ( ruleIdentifier ) )
                    // InternalScope.g:981:1: ( ruleIdentifier )
                    {
                    // InternalScope.g:981:1: ( ruleIdentifier )
                    // InternalScope.g:982:3: ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getScopeDelegationRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getScopeDelegationAccess().getScopeScopeDefinitionCrossReference_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_20);
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

            otherlv_6=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:1007:1: entryRuleNamedScopeExpression returns [EObject current=null] : iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF ;
    public final EObject entryRuleNamedScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamedScopeExpression = null;


        try {
            // InternalScope.g:1008:2: (iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF )
            // InternalScope.g:1009:2: iv_ruleNamedScopeExpression= ruleNamedScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamedScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNamedScopeExpression=ruleNamedScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamedScopeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1016:1: ruleNamedScopeExpression returns [EObject current=null] : ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? ) ;
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
            // InternalScope.g:1019:28: ( ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? ) )
            // InternalScope.g:1020:1: ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? )
            {
            // InternalScope.g:1020:1: ( (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )? )
            // InternalScope.g:1020:2: (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression ) ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )? (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )?
            {
            // InternalScope.g:1020:2: (this_GlobalScopeExpression_0= ruleGlobalScopeExpression | this_SimpleScopeExpression_1= ruleSimpleScopeExpression )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==36) ) {
                alt19=1;
            }
            else if ( ((LA19_0>=RULE_STRING && LA19_0<=RULE_ID)||LA19_0==20||LA19_0==25||LA19_0==46||LA19_0==50||LA19_0==53||LA19_0==65||(LA19_0>=67 && LA19_0<=84)) ) {
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
                    // InternalScope.g:1021:5: this_GlobalScopeExpression_0= ruleGlobalScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getGlobalScopeExpressionParserRuleCall_0_0()); 
                          
                    }
                    pushFollow(FOLLOW_32);
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
                    // InternalScope.g:1031:5: this_SimpleScopeExpression_1= ruleSimpleScopeExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getSimpleScopeExpressionParserRuleCall_0_1()); 
                          
                    }
                    pushFollow(FOLLOW_32);
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

            // InternalScope.g:1039:2: ( ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==18) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalScope.g:1039:3: ( (lv_caseDef_2_0= 'case' ) ) ( (lv_casing_3_0= ruleCasing ) )
                    {
                    // InternalScope.g:1039:3: ( (lv_caseDef_2_0= 'case' ) )
                    // InternalScope.g:1040:1: (lv_caseDef_2_0= 'case' )
                    {
                    // InternalScope.g:1040:1: (lv_caseDef_2_0= 'case' )
                    // InternalScope.g:1041:3: lv_caseDef_2_0= 'case'
                    {
                    lv_caseDef_2_0=(Token)match(input,18,FOLLOW_12); if (state.failed) return current;
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

                    // InternalScope.g:1054:2: ( (lv_casing_3_0= ruleCasing ) )
                    // InternalScope.g:1055:1: (lv_casing_3_0= ruleCasing )
                    {
                    // InternalScope.g:1055:1: (lv_casing_3_0= ruleCasing )
                    // InternalScope.g:1056:3: lv_casing_3_0= ruleCasing
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getCasingCasingEnumRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_10);
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
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.Casing");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalScope.g:1072:4: (otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==15) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalScope.g:1072:6: otherlv_4= 'as' ( (lv_naming_5_0= ruleNaming ) )
                    {
                    otherlv_4=(Token)match(input,15,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getNamedScopeExpressionAccess().getAsKeyword_2_0());
                          
                    }
                    // InternalScope.g:1076:1: ( (lv_naming_5_0= ruleNaming ) )
                    // InternalScope.g:1077:1: (lv_naming_5_0= ruleNaming )
                    {
                    // InternalScope.g:1077:1: (lv_naming_5_0= ruleNaming )
                    // InternalScope.g:1078:3: lv_naming_5_0= ruleNaming
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamedScopeExpressionAccess().getNamingNamingParserRuleCall_2_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
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
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.Naming");
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
    // InternalScope.g:1102:1: entryRuleGlobalScopeExpression returns [EObject current=null] : iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF ;
    public final EObject entryRuleGlobalScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalScopeExpression = null;


        try {
            // InternalScope.g:1103:2: (iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF )
            // InternalScope.g:1104:2: iv_ruleGlobalScopeExpression= ruleGlobalScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleGlobalScopeExpression=ruleGlobalScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalScopeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1111:1: ruleGlobalScopeExpression returns [EObject current=null] : (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' ) ;
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
            // InternalScope.g:1114:28: ( (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' ) )
            // InternalScope.g:1115:1: (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' )
            {
            // InternalScope.g:1115:1: (otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')' )
            // InternalScope.g:1115:3: otherlv_0= 'find' otherlv_1= '(' ( ( ruleQualifiedID ) ) ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )? (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )? (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )? otherlv_30= ')'
            {
            otherlv_0=(Token)match(input,36,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalScopeExpressionAccess().getFindKeyword_0());
                  
            }
            otherlv_1=(Token)match(input,25,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalScope.g:1123:1: ( ( ruleQualifiedID ) )
            // InternalScope.g:1124:1: ( ruleQualifiedID )
            {
            // InternalScope.g:1124:1: ( ruleQualifiedID )
            // InternalScope.g:1125:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getGlobalScopeExpressionRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getTypeEClassCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_31);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalScope.g:1138:2: ( (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) ) | (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) ) )?
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==35) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==37) ) {
                    alt23=1;
                }
                else if ( ((LA23_1>=38 && LA23_1<=39)) ) {
                    alt23=2;
                }
            }
            switch (alt23) {
                case 1 :
                    // InternalScope.g:1138:3: (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) )
                    {
                    // InternalScope.g:1138:3: (otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) ) )
                    // InternalScope.g:1138:5: otherlv_3= ',' otherlv_4= 'key' otherlv_5= '=' ( (lv_name_6_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,35,FOLLOW_33); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_3_0_0());
                          
                    }
                    otherlv_4=(Token)match(input,37,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getGlobalScopeExpressionAccess().getKeyKeyword_3_0_1());
                          
                    }
                    otherlv_5=(Token)match(input,22,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_3_0_2());
                          
                    }
                    // InternalScope.g:1150:1: ( (lv_name_6_0= ruleExpression ) )
                    // InternalScope.g:1151:1: (lv_name_6_0= ruleExpression )
                    {
                    // InternalScope.g:1151:1: (lv_name_6_0= ruleExpression )
                    // InternalScope.g:1152:3: lv_name_6_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getNameExpressionParserRuleCall_3_0_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalScope.g:1169:6: (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) )
                    {
                    // InternalScope.g:1169:6: (otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) ) )
                    // InternalScope.g:1169:8: otherlv_7= ',' ( (lv_recursivePrefix_8_0= 'recursive' ) )? otherlv_9= 'prefix' otherlv_10= '=' ( (lv_prefix_11_0= ruleExpression ) )
                    {
                    otherlv_7=(Token)match(input,35,FOLLOW_34); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_3_1_0());
                          
                    }
                    // InternalScope.g:1173:1: ( (lv_recursivePrefix_8_0= 'recursive' ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==38) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // InternalScope.g:1174:1: (lv_recursivePrefix_8_0= 'recursive' )
                            {
                            // InternalScope.g:1174:1: (lv_recursivePrefix_8_0= 'recursive' )
                            // InternalScope.g:1175:3: lv_recursivePrefix_8_0= 'recursive'
                            {
                            lv_recursivePrefix_8_0=(Token)match(input,38,FOLLOW_35); if (state.failed) return current;
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

                    otherlv_9=(Token)match(input,39,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getGlobalScopeExpressionAccess().getPrefixKeyword_3_1_2());
                          
                    }
                    otherlv_10=(Token)match(input,22,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_3_1_3());
                          
                    }
                    // InternalScope.g:1196:1: ( (lv_prefix_11_0= ruleExpression ) )
                    // InternalScope.g:1197:1: (lv_prefix_11_0= ruleExpression )
                    {
                    // InternalScope.g:1197:1: (lv_prefix_11_0= ruleExpression )
                    // InternalScope.g:1198:3: lv_prefix_11_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getPrefixExpressionParserRuleCall_3_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            // InternalScope.g:1214:5: (otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==35) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==40) ) {
                    alt25=1;
                }
            }
            switch (alt25) {
                case 1 :
                    // InternalScope.g:1214:7: otherlv_12= ',' otherlv_13= 'data' otherlv_14= '=' otherlv_15= '(' ( (lv_data_16_0= ruleDataExpression ) ) (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )* otherlv_19= ')'
                    {
                    otherlv_12=(Token)match(input,35,FOLLOW_36); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_4_0());
                          
                    }
                    otherlv_13=(Token)match(input,40,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getGlobalScopeExpressionAccess().getDataKeyword_4_1());
                          
                    }
                    otherlv_14=(Token)match(input,22,FOLLOW_29); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_4_2());
                          
                    }
                    otherlv_15=(Token)match(input,25,FOLLOW_37); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_15, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_4_3());
                          
                    }
                    // InternalScope.g:1230:1: ( (lv_data_16_0= ruleDataExpression ) )
                    // InternalScope.g:1231:1: (lv_data_16_0= ruleDataExpression )
                    {
                    // InternalScope.g:1231:1: (lv_data_16_0= ruleDataExpression )
                    // InternalScope.g:1232:3: lv_data_16_0= ruleDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDataDataExpressionParserRuleCall_4_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
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
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.DataExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalScope.g:1248:2: (otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) ) )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==35) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // InternalScope.g:1248:4: otherlv_17= ',' ( (lv_data_18_0= ruleDataExpression ) )
                    	    {
                    	    otherlv_17=(Token)match(input,35,FOLLOW_37); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_17, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_4_5_0());
                    	          
                    	    }
                    	    // InternalScope.g:1252:1: ( (lv_data_18_0= ruleDataExpression ) )
                    	    // InternalScope.g:1253:1: (lv_data_18_0= ruleDataExpression )
                    	    {
                    	    // InternalScope.g:1253:1: (lv_data_18_0= ruleDataExpression )
                    	    // InternalScope.g:1254:3: lv_data_18_0= ruleDataExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDataDataExpressionParserRuleCall_4_5_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_31);
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
                    	              		"com.avaloq.tools.ddk.xtext.scope.Scope.DataExpression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);

                    otherlv_19=(Token)match(input,26,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_19, grammarAccess.getGlobalScopeExpressionAccess().getRightParenthesisKeyword_4_6());
                          
                    }

                    }
                    break;

            }

            // InternalScope.g:1274:3: (otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==35) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalScope.g:1274:5: otherlv_20= ',' otherlv_21= 'domains' otherlv_22= '=' ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) )
                    {
                    otherlv_20=(Token)match(input,35,FOLLOW_38); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_5_0());
                          
                    }
                    otherlv_21=(Token)match(input,41,FOLLOW_16); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_21, grammarAccess.getGlobalScopeExpressionAccess().getDomainsKeyword_5_1());
                          
                    }
                    otherlv_22=(Token)match(input,22,FOLLOW_39); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_22, grammarAccess.getGlobalScopeExpressionAccess().getEqualsSignKeyword_5_2());
                          
                    }
                    // InternalScope.g:1286:1: ( ( (lv_domains_23_0= '*' ) ) | ( (lv_domains_24_0= ruleIdentifier ) ) | (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' ) )
                    int alt27=3;
                    switch ( input.LA(1) ) {
                    case 30:
                        {
                        alt27=1;
                        }
                        break;
                    case RULE_ID:
                        {
                        alt27=2;
                        }
                        break;
                    case 25:
                        {
                        alt27=3;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }

                    switch (alt27) {
                        case 1 :
                            // InternalScope.g:1286:2: ( (lv_domains_23_0= '*' ) )
                            {
                            // InternalScope.g:1286:2: ( (lv_domains_23_0= '*' ) )
                            // InternalScope.g:1287:1: (lv_domains_23_0= '*' )
                            {
                            // InternalScope.g:1287:1: (lv_domains_23_0= '*' )
                            // InternalScope.g:1288:3: lv_domains_23_0= '*'
                            {
                            lv_domains_23_0=(Token)match(input,30,FOLLOW_20); if (state.failed) return current;
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
                            // InternalScope.g:1302:6: ( (lv_domains_24_0= ruleIdentifier ) )
                            {
                            // InternalScope.g:1302:6: ( (lv_domains_24_0= ruleIdentifier ) )
                            // InternalScope.g:1303:1: (lv_domains_24_0= ruleIdentifier )
                            {
                            // InternalScope.g:1303:1: (lv_domains_24_0= ruleIdentifier )
                            // InternalScope.g:1304:3: lv_domains_24_0= ruleIdentifier
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_20);
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
                                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 3 :
                            // InternalScope.g:1321:6: (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' )
                            {
                            // InternalScope.g:1321:6: (otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')' )
                            // InternalScope.g:1321:8: otherlv_25= '(' ( (lv_domains_26_0= ruleIdentifier ) ) (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )* otherlv_29= ')'
                            {
                            otherlv_25=(Token)match(input,25,FOLLOW_3); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_25, grammarAccess.getGlobalScopeExpressionAccess().getLeftParenthesisKeyword_5_3_2_0());
                                  
                            }
                            // InternalScope.g:1325:1: ( (lv_domains_26_0= ruleIdentifier ) )
                            // InternalScope.g:1326:1: (lv_domains_26_0= ruleIdentifier )
                            {
                            // InternalScope.g:1326:1: (lv_domains_26_0= ruleIdentifier )
                            // InternalScope.g:1327:3: lv_domains_26_0= ruleIdentifier
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_31);
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
                                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // InternalScope.g:1343:2: (otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) ) )*
                            loop26:
                            do {
                                int alt26=2;
                                int LA26_0 = input.LA(1);

                                if ( (LA26_0==35) ) {
                                    alt26=1;
                                }


                                switch (alt26) {
                            	case 1 :
                            	    // InternalScope.g:1343:4: otherlv_27= ',' ( (lv_domains_28_0= ruleIdentifier ) )
                            	    {
                            	    otherlv_27=(Token)match(input,35,FOLLOW_3); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	          	newLeafNode(otherlv_27, grammarAccess.getGlobalScopeExpressionAccess().getCommaKeyword_5_3_2_2_0());
                            	          
                            	    }
                            	    // InternalScope.g:1347:1: ( (lv_domains_28_0= ruleIdentifier ) )
                            	    // InternalScope.g:1348:1: (lv_domains_28_0= ruleIdentifier )
                            	    {
                            	    // InternalScope.g:1348:1: (lv_domains_28_0= ruleIdentifier )
                            	    // InternalScope.g:1349:3: lv_domains_28_0= ruleIdentifier
                            	    {
                            	    if ( state.backtracking==0 ) {
                            	       
                            	      	        newCompositeNode(grammarAccess.getGlobalScopeExpressionAccess().getDomainsIdentifierParserRuleCall_5_3_2_2_1_0()); 
                            	      	    
                            	    }
                            	    pushFollow(FOLLOW_31);
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
                            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
                            	      	        afterParserOrEnumRuleCall();
                            	      	    
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop26;
                                }
                            } while (true);

                            otherlv_29=(Token)match(input,26,FOLLOW_20); if (state.failed) return current;
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

            otherlv_30=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:1381:1: entryRuleDataExpression returns [EObject current=null] : iv_ruleDataExpression= ruleDataExpression EOF ;
    public final EObject entryRuleDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDataExpression = null;


        try {
            // InternalScope.g:1382:2: (iv_ruleDataExpression= ruleDataExpression EOF )
            // InternalScope.g:1383:2: iv_ruleDataExpression= ruleDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDataExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDataExpression=ruleDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDataExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1390:1: ruleDataExpression returns [EObject current=null] : (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression ) ;
    public final EObject ruleDataExpression() throws RecognitionException {
        EObject current = null;

        EObject this_MatchDataExpression_0 = null;

        EObject this_LambdaDataExpression_1 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1393:28: ( (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression ) )
            // InternalScope.g:1394:1: (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression )
            {
            // InternalScope.g:1394:1: (this_MatchDataExpression_0= ruleMatchDataExpression | this_LambdaDataExpression_1= ruleLambdaDataExpression )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==RULE_ID) ) {
                alt29=1;
            }
            else if ( (LA29_0==31) ) {
                alt29=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // InternalScope.g:1395:5: this_MatchDataExpression_0= ruleMatchDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDataExpressionAccess().getMatchDataExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:1405:5: this_LambdaDataExpression_1= ruleLambdaDataExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getDataExpressionAccess().getLambdaDataExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:1421:1: entryRuleMatchDataExpression returns [EObject current=null] : iv_ruleMatchDataExpression= ruleMatchDataExpression EOF ;
    public final EObject entryRuleMatchDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMatchDataExpression = null;


        try {
            // InternalScope.g:1422:2: (iv_ruleMatchDataExpression= ruleMatchDataExpression EOF )
            // InternalScope.g:1423:2: iv_ruleMatchDataExpression= ruleMatchDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMatchDataExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMatchDataExpression=ruleMatchDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMatchDataExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1430:1: ruleMatchDataExpression returns [EObject current=null] : ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) ) ;
    public final EObject ruleMatchDataExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_key_0_0 = null;

        EObject lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1433:28: ( ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) ) )
            // InternalScope.g:1434:1: ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) )
            {
            // InternalScope.g:1434:1: ( ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) ) )
            // InternalScope.g:1434:2: ( (lv_key_0_0= ruleIdentifier ) ) otherlv_1= '=' ( (lv_value_2_0= ruleExpression ) )
            {
            // InternalScope.g:1434:2: ( (lv_key_0_0= ruleIdentifier ) )
            // InternalScope.g:1435:1: (lv_key_0_0= ruleIdentifier )
            {
            // InternalScope.g:1435:1: (lv_key_0_0= ruleIdentifier )
            // InternalScope.g:1436:3: lv_key_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMatchDataExpressionAccess().getKeyIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_16);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getMatchDataExpressionAccess().getEqualsSignKeyword_1());
                  
            }
            // InternalScope.g:1456:1: ( (lv_value_2_0= ruleExpression ) )
            // InternalScope.g:1457:1: (lv_value_2_0= ruleExpression )
            {
            // InternalScope.g:1457:1: (lv_value_2_0= ruleExpression )
            // InternalScope.g:1458:3: lv_value_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getMatchDataExpressionAccess().getValueExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
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
    // InternalScope.g:1482:1: entryRuleLambdaDataExpression returns [EObject current=null] : iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF ;
    public final EObject entryRuleLambdaDataExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLambdaDataExpression = null;


        try {
            // InternalScope.g:1483:2: (iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF )
            // InternalScope.g:1484:2: iv_ruleLambdaDataExpression= ruleLambdaDataExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLambdaDataExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLambdaDataExpression=ruleLambdaDataExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLambdaDataExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1491:1: ruleLambdaDataExpression returns [EObject current=null] : (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' ) ;
    public final EObject ruleLambdaDataExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_desc_1_0 = null;

        EObject lv_value_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1494:28: ( (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' ) )
            // InternalScope.g:1495:1: (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' )
            {
            // InternalScope.g:1495:1: (otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']' )
            // InternalScope.g:1495:3: otherlv_0= '[' ( (lv_desc_1_0= ruleIdentifier ) ) otherlv_2= '|' ( (lv_value_3_0= ruleExpression ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,31,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLambdaDataExpressionAccess().getLeftSquareBracketKeyword_0());
                  
            }
            // InternalScope.g:1499:1: ( (lv_desc_1_0= ruleIdentifier ) )
            // InternalScope.g:1500:1: (lv_desc_1_0= ruleIdentifier )
            {
            // InternalScope.g:1500:1: (lv_desc_1_0= ruleIdentifier )
            // InternalScope.g:1501:3: lv_desc_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLambdaDataExpressionAccess().getDescIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_40);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,42,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLambdaDataExpressionAccess().getVerticalLineKeyword_2());
                  
            }
            // InternalScope.g:1521:1: ( (lv_value_3_0= ruleExpression ) )
            // InternalScope.g:1522:1: (lv_value_3_0= ruleExpression )
            {
            // InternalScope.g:1522:1: (lv_value_3_0= ruleExpression )
            // InternalScope.g:1523:3: lv_value_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLambdaDataExpressionAccess().getValueExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_28);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:1551:1: entryRuleSimpleScopeExpression returns [EObject current=null] : iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF ;
    public final EObject entryRuleSimpleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleScopeExpression = null;


        try {
            // InternalScope.g:1552:2: (iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF )
            // InternalScope.g:1553:2: iv_ruleSimpleScopeExpression= ruleSimpleScopeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleScopeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSimpleScopeExpression=ruleSimpleScopeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleScopeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1560:1: ruleSimpleScopeExpression returns [EObject current=null] : ( (lv_expr_0_0= ruleExpression ) ) ;
    public final EObject ruleSimpleScopeExpression() throws RecognitionException {
        EObject current = null;

        EObject lv_expr_0_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1563:28: ( ( (lv_expr_0_0= ruleExpression ) ) )
            // InternalScope.g:1564:1: ( (lv_expr_0_0= ruleExpression ) )
            {
            // InternalScope.g:1564:1: ( (lv_expr_0_0= ruleExpression ) )
            // InternalScope.g:1565:1: (lv_expr_0_0= ruleExpression )
            {
            // InternalScope.g:1565:1: (lv_expr_0_0= ruleExpression )
            // InternalScope.g:1566:3: lv_expr_0_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleScopeExpressionAccess().getExprExpressionParserRuleCall_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
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
    // InternalScope.g:1590:1: entryRuleNaming returns [EObject current=null] : iv_ruleNaming= ruleNaming EOF ;
    public final EObject entryRuleNaming() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNaming = null;


        try {
            // InternalScope.g:1591:2: (iv_ruleNaming= ruleNaming EOF )
            // InternalScope.g:1592:2: iv_ruleNaming= ruleNaming EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNaming=ruleNaming();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNaming; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1599:1: ruleNaming returns [EObject current=null] : ( ( ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' ) ) | ( (lv_names_5_0= ruleNamingExpression ) ) ) ;
    public final EObject ruleNaming() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_names_1_0 = null;

        EObject lv_names_3_0 = null;

        EObject lv_names_5_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1602:28: ( ( ( ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' ) ) | ( (lv_names_5_0= ruleNamingExpression ) ) ) )
            // InternalScope.g:1603:1: ( ( ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' ) ) | ( (lv_names_5_0= ruleNamingExpression ) ) )
            {
            // InternalScope.g:1603:1: ( ( ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' ) ) | ( (lv_names_5_0= ruleNamingExpression ) ) )
            int alt31=2;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // InternalScope.g:1603:2: ( ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' ) )
                    {
                    // InternalScope.g:1603:2: ( ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' ) )
                    // InternalScope.g:1603:3: ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' )
                    {
                    // InternalScope.g:1604:4: (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' )
                    // InternalScope.g:1604:6: otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')'
                    {
                    otherlv_0=(Token)match(input,25,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getNamingAccess().getLeftParenthesisKeyword_0_0_0());
                          
                    }
                    // InternalScope.g:1608:1: ( (lv_names_1_0= ruleNamingExpression ) )
                    // InternalScope.g:1609:1: (lv_names_1_0= ruleNamingExpression )
                    {
                    // InternalScope.g:1609:1: (lv_names_1_0= ruleNamingExpression )
                    // InternalScope.g:1610:3: lv_names_1_0= ruleNamingExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_0_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
                    lv_names_1_0=ruleNamingExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamingRule());
                      	        }
                             		add(
                             			current, 
                             			"names",
                              		lv_names_1_0, 
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.NamingExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalScope.g:1626:2: (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==35) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // InternalScope.g:1626:4: otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) )
                    	    {
                    	    otherlv_2=(Token)match(input,35,FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_2, grammarAccess.getNamingAccess().getCommaKeyword_0_0_2_0());
                    	          
                    	    }
                    	    // InternalScope.g:1630:1: ( (lv_names_3_0= ruleNamingExpression ) )
                    	    // InternalScope.g:1631:1: (lv_names_3_0= ruleNamingExpression )
                    	    {
                    	    // InternalScope.g:1631:1: (lv_names_3_0= ruleNamingExpression )
                    	    // InternalScope.g:1632:3: lv_names_3_0= ruleNamingExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_0_0_2_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_31);
                    	    lv_names_3_0=ruleNamingExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getNamingRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"names",
                    	              		lv_names_3_0, 
                    	              		"com.avaloq.tools.ddk.xtext.scope.Scope.NamingExpression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getNamingAccess().getRightParenthesisKeyword_0_0_3());
                          
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalScope.g:1653:6: ( (lv_names_5_0= ruleNamingExpression ) )
                    {
                    // InternalScope.g:1653:6: ( (lv_names_5_0= ruleNamingExpression ) )
                    // InternalScope.g:1654:1: (lv_names_5_0= ruleNamingExpression )
                    {
                    // InternalScope.g:1654:1: (lv_names_5_0= ruleNamingExpression )
                    // InternalScope.g:1655:3: lv_names_5_0= ruleNamingExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingAccess().getNamesNamingExpressionParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
                    lv_names_5_0=ruleNamingExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getNamingRule());
                      	        }
                             		add(
                             			current, 
                             			"names",
                              		lv_names_5_0, 
                              		"com.avaloq.tools.ddk.xtext.scope.Scope.NamingExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
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
    // $ANTLR end "ruleNaming"


    // $ANTLR start "entryRuleNamingExpression"
    // InternalScope.g:1679:1: entryRuleNamingExpression returns [EObject current=null] : iv_ruleNamingExpression= ruleNamingExpression EOF ;
    public final EObject entryRuleNamingExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNamingExpression = null;


        try {
            // InternalScope.g:1680:2: (iv_ruleNamingExpression= ruleNamingExpression EOF )
            // InternalScope.g:1681:2: iv_ruleNamingExpression= ruleNamingExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNamingExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNamingExpression=ruleNamingExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNamingExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1688:1: ruleNamingExpression returns [EObject current=null] : ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) ) ;
    public final EObject ruleNamingExpression() throws RecognitionException {
        EObject current = null;

        Token lv_export_0_0=null;
        Token lv_factory_1_0=null;
        EObject lv_expression_2_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1691:28: ( ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) ) )
            // InternalScope.g:1692:1: ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) )
            {
            // InternalScope.g:1692:1: ( ( (lv_export_0_0= 'export' ) ) | ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) ) )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==43) ) {
                alt33=1;
            }
            else if ( ((LA33_0>=RULE_STRING && LA33_0<=RULE_ID)||LA33_0==20||LA33_0==25||LA33_0==33||LA33_0==46||LA33_0==50||LA33_0==53||LA33_0==65||(LA33_0>=67 && LA33_0<=84)) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }
            switch (alt33) {
                case 1 :
                    // InternalScope.g:1692:2: ( (lv_export_0_0= 'export' ) )
                    {
                    // InternalScope.g:1692:2: ( (lv_export_0_0= 'export' ) )
                    // InternalScope.g:1693:1: (lv_export_0_0= 'export' )
                    {
                    // InternalScope.g:1693:1: (lv_export_0_0= 'export' )
                    // InternalScope.g:1694:3: lv_export_0_0= 'export'
                    {
                    lv_export_0_0=(Token)match(input,43,FOLLOW_2); if (state.failed) return current;
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
                    // InternalScope.g:1708:6: ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) )
                    {
                    // InternalScope.g:1708:6: ( ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) ) )
                    // InternalScope.g:1708:7: ( (lv_factory_1_0= 'factory' ) )? ( (lv_expression_2_0= ruleExpression ) )
                    {
                    // InternalScope.g:1708:7: ( (lv_factory_1_0= 'factory' ) )?
                    int alt32=2;
                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==33) ) {
                        alt32=1;
                    }
                    switch (alt32) {
                        case 1 :
                            // InternalScope.g:1709:1: (lv_factory_1_0= 'factory' )
                            {
                            // InternalScope.g:1709:1: (lv_factory_1_0= 'factory' )
                            // InternalScope.g:1710:3: lv_factory_1_0= 'factory'
                            {
                            lv_factory_1_0=(Token)match(input,33,FOLLOW_17); if (state.failed) return current;
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

                    // InternalScope.g:1723:3: ( (lv_expression_2_0= ruleExpression ) )
                    // InternalScope.g:1724:1: (lv_expression_2_0= ruleExpression )
                    {
                    // InternalScope.g:1724:1: (lv_expression_2_0= ruleExpression )
                    // InternalScope.g:1725:3: lv_expression_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getNamingExpressionAccess().getExpressionExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
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
    // InternalScope.g:1749:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalScope.g:1750:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalScope.g:1751:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedID.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1758:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Identifier_0 = null;

        AntlrDatatypeRuleToken this_Identifier_2 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1761:28: ( (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* ) )
            // InternalScope.g:1762:1: (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* )
            {
            // InternalScope.g:1762:1: (this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )* )
            // InternalScope.g:1763:5: this_Identifier_0= ruleIdentifier (kw= '::' this_Identifier_2= ruleIdentifier )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getQualifiedIDAccess().getIdentifierParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_41);
            this_Identifier_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_Identifier_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:1773:1: (kw= '::' this_Identifier_2= ruleIdentifier )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==44) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalScope.g:1774:2: kw= '::' this_Identifier_2= ruleIdentifier
            	    {
            	    kw=(Token)match(input,44,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getQualifiedIDAccess().getIdentifierParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_41);
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
    // $ANTLR end "ruleQualifiedID"


    // $ANTLR start "entryRuleDottedID"
    // InternalScope.g:1798:1: entryRuleDottedID returns [String current=null] : iv_ruleDottedID= ruleDottedID EOF ;
    public final String entryRuleDottedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDottedID = null;


        try {
            // InternalScope.g:1799:2: (iv_ruleDottedID= ruleDottedID EOF )
            // InternalScope.g:1800:2: iv_ruleDottedID= ruleDottedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getDottedIDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleDottedID=ruleDottedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleDottedID.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1807:1: ruleDottedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* ) ;
    public final AntlrDatatypeRuleToken ruleDottedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_Identifier_0 = null;

        AntlrDatatypeRuleToken this_Identifier_2 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1810:28: ( (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* ) )
            // InternalScope.g:1811:1: (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* )
            {
            // InternalScope.g:1811:1: (this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )* )
            // InternalScope.g:1812:5: this_Identifier_0= ruleIdentifier (kw= '.' this_Identifier_2= ruleIdentifier )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getDottedIDAccess().getIdentifierParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_42);
            this_Identifier_0=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_Identifier_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:1822:1: (kw= '.' this_Identifier_2= ruleIdentifier )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==45) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalScope.g:1823:2: kw= '.' this_Identifier_2= ruleIdentifier
            	    {
            	    kw=(Token)match(input,45,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getDottedIDAccess().getFullStopKeyword_1_0()); 
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	              newCompositeNode(grammarAccess.getDottedIDAccess().getIdentifierParserRuleCall_1_1()); 
            	          
            	    }
            	    pushFollow(FOLLOW_42);
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
    // $ANTLR end "ruleDottedID"


    // $ANTLR start "entryRuleExpression"
    // InternalScope.g:1847:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalScope.g:1848:2: (iv_ruleExpression= ruleExpression EOF )
            // InternalScope.g:1849:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1856:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1859:28: ( (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) )
            // InternalScope.g:1860:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            {
            // InternalScope.g:1860:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            int alt36=3;
            alt36 = dfa36.predict(input);
            switch (alt36) {
                case 1 :
                    // InternalScope.g:1861:5: this_LetExpression_0= ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:1870:6: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    {
                    // InternalScope.g:1870:6: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    // InternalScope.g:1870:7: ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
                    this_CastedExpression_1=ruleCastedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_CastedExpression_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalScope.g:1881:5: this_ChainExpression_2= ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:1899:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // InternalScope.g:1900:2: (iv_ruleLetExpression= ruleLetExpression EOF )
            // InternalScope.g:1901:2: iv_ruleLetExpression= ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLetExpression=ruleLetExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1908:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // InternalScope.g:1911:28: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // InternalScope.g:1912:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // InternalScope.g:1912:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // InternalScope.g:1912:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,46,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
                  
            }
            // InternalScope.g:1916:1: ( (lv_identifier_1_0= ruleIdentifier ) )
            // InternalScope.g:1917:1: (lv_identifier_1_0= ruleIdentifier )
            {
            // InternalScope.g:1917:1: (lv_identifier_1_0= ruleIdentifier )
            // InternalScope.g:1918:3: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_16);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,22,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
                  
            }
            // InternalScope.g:1938:1: ( (lv_varExpr_3_0= ruleExpression ) )
            // InternalScope.g:1939:1: (lv_varExpr_3_0= ruleExpression )
            {
            // InternalScope.g:1939:1: (lv_varExpr_3_0= ruleExpression )
            // InternalScope.g:1940:3: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_43);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_4=(Token)match(input,47,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
                  
            }
            // InternalScope.g:1960:1: ( (lv_target_5_0= ruleExpression ) )
            // InternalScope.g:1961:1: (lv_target_5_0= ruleExpression )
            {
            // InternalScope.g:1961:1: (lv_target_5_0= ruleExpression )
            // InternalScope.g:1962:3: lv_target_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
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
    // InternalScope.g:1986:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // InternalScope.g:1987:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // InternalScope.g:1988:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:1995:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:1998:28: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // InternalScope.g:1999:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // InternalScope.g:1999:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // InternalScope.g:1999:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,25,FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            // InternalScope.g:2003:1: ( (lv_type_1_0= ruleType ) )
            // InternalScope.g:2004:1: (lv_type_1_0= ruleType )
            {
            // InternalScope.g:2004:1: (lv_type_1_0= ruleType )
            // InternalScope.g:2005:3: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_20);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,26,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }
            // InternalScope.g:2025:1: ( (lv_target_3_0= ruleExpression ) )
            // InternalScope.g:2026:1: (lv_target_3_0= ruleExpression )
            {
            // InternalScope.g:2026:1: (lv_target_3_0= ruleExpression )
            // InternalScope.g:2027:3: lv_target_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
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
    // InternalScope.g:2051:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // InternalScope.g:2052:2: (iv_ruleChainExpression= ruleChainExpression EOF )
            // InternalScope.g:2053:2: iv_ruleChainExpression= ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleChainExpression=ruleChainExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2060:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2063:28: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // InternalScope.g:2064:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // InternalScope.g:2064:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // InternalScope.g:2065:5: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_45);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ChainedExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2073:1: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==48) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalScope.g:2073:2: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // InternalScope.g:2073:2: ()
            	    // InternalScope.g:2074:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,48,FOLLOW_17); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	          
            	    }
            	    // InternalScope.g:2083:1: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // InternalScope.g:2084:1: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // InternalScope.g:2084:1: (lv_next_3_0= ruleChainedExpression )
            	    // InternalScope.g:2085:3: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_45);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.ChainedExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop37;
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
    // InternalScope.g:2109:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // InternalScope.g:2110:2: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // InternalScope.g:2111:2: iv_ruleChainedExpression= ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleChainedExpression=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainedExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2118:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2121:28: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // InternalScope.g:2122:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // InternalScope.g:2122:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            int alt38=3;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt38=1;
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
                alt38=2;
                }
                break;
            case 53:
                {
                alt38=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }

            switch (alt38) {
                case 1 :
                    // InternalScope.g:2123:5: this_IfExpressionKw_0= ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:2133:5: this_IfExpressionTri_1= ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:2143:5: this_SwitchExpression_2= ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:2159:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // InternalScope.g:2160:2: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // InternalScope.g:2161:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIfExpressionTri=ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionTri; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2168:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2171:28: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // InternalScope.g:2172:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // InternalScope.g:2172:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // InternalScope.g:2173:5: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_46);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2181:1: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==49) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // InternalScope.g:2181:2: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // InternalScope.g:2181:2: ()
                    // InternalScope.g:2182:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_2=(Token)match(input,49,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                          
                    }
                    // InternalScope.g:2191:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // InternalScope.g:2192:1: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // InternalScope.g:2192:1: (lv_thenPart_3_0= ruleChainedExpression )
                    // InternalScope.g:2193:3: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_43);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.ChainedExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,47,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                          
                    }
                    // InternalScope.g:2213:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // InternalScope.g:2214:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // InternalScope.g:2214:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // InternalScope.g:2215:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.ChainedExpression");
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
    // InternalScope.g:2239:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // InternalScope.g:2240:2: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // InternalScope.g:2241:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIfExpressionKw=ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionKw; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2248:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) ;
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
            // InternalScope.g:2251:28: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) )
            // InternalScope.g:2252:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            {
            // InternalScope.g:2252:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            // InternalScope.g:2252:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            {
            otherlv_0=(Token)match(input,50,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
                  
            }
            // InternalScope.g:2256:1: ( (lv_condition_1_0= ruleChainedExpression ) )
            // InternalScope.g:2257:1: (lv_condition_1_0= ruleChainedExpression )
            {
            // InternalScope.g:2257:1: (lv_condition_1_0= ruleChainedExpression )
            // InternalScope.g:2258:3: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_47);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.ChainedExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,51,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
                  
            }
            // InternalScope.g:2278:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // InternalScope.g:2279:1: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // InternalScope.g:2279:1: (lv_thenPart_3_0= ruleChainedExpression )
            // InternalScope.g:2280:3: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_48);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.ChainedExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalScope.g:2296:2: ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==52) ) {
                int LA40_1 = input.LA(2);

                if ( (synpred3_InternalScope()) ) {
                    alt40=1;
                }
            }
            switch (alt40) {
                case 1 :
                    // InternalScope.g:2296:3: ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    {
                    // InternalScope.g:2297:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    // InternalScope.g:2297:6: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,52,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0_0());
                          
                    }
                    // InternalScope.g:2301:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // InternalScope.g:2302:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // InternalScope.g:2302:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // InternalScope.g:2303:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.ChainedExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

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
    // InternalScope.g:2327:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // InternalScope.g:2328:2: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // InternalScope.g:2329:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSwitchExpression=ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSwitchExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2336:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // InternalScope.g:2339:28: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // InternalScope.g:2340:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // InternalScope.g:2340:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // InternalScope.g:2340:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,53,FOLLOW_49); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
                  
            }
            // InternalScope.g:2344:1: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==25) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalScope.g:2344:3: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,25,FOLLOW_50); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // InternalScope.g:2348:1: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // InternalScope.g:2349:1: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // InternalScope.g:2349:1: (lv_switchExpr_2_0= ruleOrExpression )
                    // InternalScope.g:2350:3: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_20);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.OrExpression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,26,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,20,FOLLOW_51); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // InternalScope.g:2374:1: ( (lv_case_5_0= ruleCase ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==18) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalScope.g:2375:1: (lv_case_5_0= ruleCase )
            	    {
            	    // InternalScope.g:2375:1: (lv_case_5_0= ruleCase )
            	    // InternalScope.g:2376:3: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_51);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Case");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

            otherlv_6=(Token)match(input,54,FOLLOW_43); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
                  
            }
            otherlv_7=(Token)match(input,47,FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
                  
            }
            // InternalScope.g:2400:1: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // InternalScope.g:2401:1: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // InternalScope.g:2401:1: (lv_defaultExpr_8_0= ruleOrExpression )
            // InternalScope.g:2402:3: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_52);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.OrExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_9=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:2430:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // InternalScope.g:2431:2: (iv_ruleCase= ruleCase EOF )
            // InternalScope.g:2432:2: iv_ruleCase= ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCase=ruleCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCase; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2439:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2442:28: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // InternalScope.g:2443:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // InternalScope.g:2443:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // InternalScope.g:2443:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
                  
            }
            // InternalScope.g:2447:1: ( (lv_condition_1_0= ruleOrExpression ) )
            // InternalScope.g:2448:1: (lv_condition_1_0= ruleOrExpression )
            {
            // InternalScope.g:2448:1: (lv_condition_1_0= ruleOrExpression )
            // InternalScope.g:2449:3: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_43);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.OrExpression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_2=(Token)match(input,47,FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
                  
            }
            // InternalScope.g:2469:1: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // InternalScope.g:2470:1: (lv_thenPar_3_0= ruleOrExpression )
            {
            // InternalScope.g:2470:1: (lv_thenPar_3_0= ruleOrExpression )
            // InternalScope.g:2471:3: lv_thenPar_3_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.OrExpression");
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
    // InternalScope.g:2495:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // InternalScope.g:2496:2: (iv_ruleOrExpression= ruleOrExpression EOF )
            // InternalScope.g:2497:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOrExpression=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2504:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2507:28: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // InternalScope.g:2508:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // InternalScope.g:2508:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // InternalScope.g:2509:5: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_53);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2517:1: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==55) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // InternalScope.g:2517:2: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // InternalScope.g:2517:2: ()
            	    // InternalScope.g:2518:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalScope.g:2523:2: ( (lv_operator_2_0= '||' ) )
            	    // InternalScope.g:2524:1: (lv_operator_2_0= '||' )
            	    {
            	    // InternalScope.g:2524:1: (lv_operator_2_0= '||' )
            	    // InternalScope.g:2525:3: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,55,FOLLOW_50); if (state.failed) return current;
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

            	    // InternalScope.g:2538:2: ( (lv_right_3_0= ruleAndExpression ) )
            	    // InternalScope.g:2539:1: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // InternalScope.g:2539:1: (lv_right_3_0= ruleAndExpression )
            	    // InternalScope.g:2540:3: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_53);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.AndExpression");
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
    // $ANTLR end "ruleOrExpression"


    // $ANTLR start "entryRuleAndExpression"
    // InternalScope.g:2564:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // InternalScope.g:2565:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // InternalScope.g:2566:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2573:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2576:28: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // InternalScope.g:2577:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // InternalScope.g:2577:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // InternalScope.g:2578:5: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_54);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2586:1: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( (LA44_0==56) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalScope.g:2586:2: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // InternalScope.g:2586:2: ()
            	    // InternalScope.g:2587:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalScope.g:2592:2: ( (lv_operator_2_0= '&&' ) )
            	    // InternalScope.g:2593:1: (lv_operator_2_0= '&&' )
            	    {
            	    // InternalScope.g:2593:1: (lv_operator_2_0= '&&' )
            	    // InternalScope.g:2594:3: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,56,FOLLOW_50); if (state.failed) return current;
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

            	    // InternalScope.g:2607:2: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // InternalScope.g:2608:1: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // InternalScope.g:2608:1: (lv_right_3_0= ruleImpliesExpression )
            	    // InternalScope.g:2609:3: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_54);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.ImpliesExpression");
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
    // $ANTLR end "ruleAndExpression"


    // $ANTLR start "entryRuleImpliesExpression"
    // InternalScope.g:2633:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // InternalScope.g:2634:2: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // InternalScope.g:2635:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleImpliesExpression=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2642:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2645:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // InternalScope.g:2646:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // InternalScope.g:2646:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // InternalScope.g:2647:5: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_55);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2655:1: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( (LA45_0==57) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalScope.g:2655:2: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // InternalScope.g:2655:2: ()
            	    // InternalScope.g:2656:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalScope.g:2661:2: ( (lv_operator_2_0= 'implies' ) )
            	    // InternalScope.g:2662:1: (lv_operator_2_0= 'implies' )
            	    {
            	    // InternalScope.g:2662:1: (lv_operator_2_0= 'implies' )
            	    // InternalScope.g:2663:3: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,57,FOLLOW_50); if (state.failed) return current;
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

            	    // InternalScope.g:2676:2: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // InternalScope.g:2677:1: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // InternalScope.g:2677:1: (lv_right_3_0= ruleRelationalExpression )
            	    // InternalScope.g:2678:3: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_55);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.RelationalExpression");
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
    // $ANTLR end "ruleImpliesExpression"


    // $ANTLR start "entryRuleRelationalExpression"
    // InternalScope.g:2702:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // InternalScope.g:2703:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // InternalScope.g:2704:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2711:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // InternalScope.g:2714:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // InternalScope.g:2715:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // InternalScope.g:2715:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // InternalScope.g:2716:5: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_56);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2724:1: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( ((LA47_0>=58 && LA47_0<=63)) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalScope.g:2724:2: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // InternalScope.g:2724:2: ()
            	    // InternalScope.g:2725:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalScope.g:2730:2: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // InternalScope.g:2731:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // InternalScope.g:2731:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // InternalScope.g:2732:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // InternalScope.g:2732:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    int alt46=6;
            	    switch ( input.LA(1) ) {
            	    case 58:
            	        {
            	        alt46=1;
            	        }
            	        break;
            	    case 59:
            	        {
            	        alt46=2;
            	        }
            	        break;
            	    case 60:
            	        {
            	        alt46=3;
            	        }
            	        break;
            	    case 61:
            	        {
            	        alt46=4;
            	        }
            	        break;
            	    case 62:
            	        {
            	        alt46=5;
            	        }
            	        break;
            	    case 63:
            	        {
            	        alt46=6;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 46, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt46) {
            	        case 1 :
            	            // InternalScope.g:2733:3: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,58,FOLLOW_50); if (state.failed) return current;
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
            	            // InternalScope.g:2745:8: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,59,FOLLOW_50); if (state.failed) return current;
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
            	            // InternalScope.g:2757:8: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,60,FOLLOW_50); if (state.failed) return current;
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
            	            // InternalScope.g:2769:8: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,61,FOLLOW_50); if (state.failed) return current;
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
            	            // InternalScope.g:2781:8: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,62,FOLLOW_50); if (state.failed) return current;
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
            	            // InternalScope.g:2793:8: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,63,FOLLOW_50); if (state.failed) return current;
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

            	    // InternalScope.g:2808:2: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // InternalScope.g:2809:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // InternalScope.g:2809:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // InternalScope.g:2810:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_56);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.AdditiveExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop47;
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
    // InternalScope.g:2834:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // InternalScope.g:2835:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // InternalScope.g:2836:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2843:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2846:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // InternalScope.g:2847:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // InternalScope.g:2847:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // InternalScope.g:2848:5: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_57);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2856:1: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop49:
            do {
                int alt49=2;
                int LA49_0 = input.LA(1);

                if ( ((LA49_0>=64 && LA49_0<=65)) ) {
                    alt49=1;
                }


                switch (alt49) {
            	case 1 :
            	    // InternalScope.g:2856:2: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // InternalScope.g:2856:2: ()
            	    // InternalScope.g:2857:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalScope.g:2862:2: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // InternalScope.g:2863:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // InternalScope.g:2863:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // InternalScope.g:2864:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // InternalScope.g:2864:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    int alt48=2;
            	    int LA48_0 = input.LA(1);

            	    if ( (LA48_0==64) ) {
            	        alt48=1;
            	    }
            	    else if ( (LA48_0==65) ) {
            	        alt48=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 48, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt48) {
            	        case 1 :
            	            // InternalScope.g:2865:3: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,64,FOLLOW_50); if (state.failed) return current;
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
            	            // InternalScope.g:2877:8: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,65,FOLLOW_50); if (state.failed) return current;
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

            	    // InternalScope.g:2892:2: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // InternalScope.g:2893:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // InternalScope.g:2893:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // InternalScope.g:2894:3: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_57);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.MultiplicativeExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop49;
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
    // InternalScope.g:2918:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // InternalScope.g:2919:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // InternalScope.g:2920:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:2927:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:2930:28: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // InternalScope.g:2931:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // InternalScope.g:2931:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // InternalScope.g:2932:5: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_58);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryOrInfixExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:2940:1: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( (LA51_0==30||LA51_0==66) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // InternalScope.g:2940:2: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // InternalScope.g:2940:2: ()
            	    // InternalScope.g:2941:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // InternalScope.g:2946:2: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // InternalScope.g:2947:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // InternalScope.g:2947:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // InternalScope.g:2948:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // InternalScope.g:2948:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    int alt50=2;
            	    int LA50_0 = input.LA(1);

            	    if ( (LA50_0==30) ) {
            	        alt50=1;
            	    }
            	    else if ( (LA50_0==66) ) {
            	        alt50=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 50, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt50) {
            	        case 1 :
            	            // InternalScope.g:2949:3: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,30,FOLLOW_50); if (state.failed) return current;
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
            	            // InternalScope.g:2961:8: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,66,FOLLOW_50); if (state.failed) return current;
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

            	    // InternalScope.g:2976:2: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // InternalScope.g:2977:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // InternalScope.g:2977:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // InternalScope.g:2978:3: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_58);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.UnaryOrInfixExpression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop51;
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
    // InternalScope.g:3002:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // InternalScope.g:3003:2: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // InternalScope.g:3004:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOrInfixExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3011:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:3014:28: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // InternalScope.g:3015:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // InternalScope.g:3015:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==65||LA52_0==67) ) {
                alt52=1;
            }
            else if ( ((LA52_0>=RULE_STRING && LA52_0<=RULE_ID)||LA52_0==20||LA52_0==25||(LA52_0>=68 && LA52_0<=84)) ) {
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
                    // InternalScope.g:3016:5: this_UnaryExpression_0= ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3026:5: this_InfixExpression_1= ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:3042:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // InternalScope.g:3043:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // InternalScope.g:3044:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3051:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:3054:28: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // InternalScope.g:3055:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // InternalScope.g:3055:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // InternalScope.g:3055:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // InternalScope.g:3055:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // InternalScope.g:3056:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // InternalScope.g:3056:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // InternalScope.g:3057:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // InternalScope.g:3057:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==67) ) {
                alt53=1;
            }
            else if ( (LA53_0==65) ) {
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
                    // InternalScope.g:3058:3: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,67,FOLLOW_50); if (state.failed) return current;
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
                    // InternalScope.g:3070:8: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,65,FOLLOW_50); if (state.failed) return current;
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

            // InternalScope.g:3085:2: ( (lv_params_1_0= ruleInfixExpression ) )
            // InternalScope.g:3086:1: (lv_params_1_0= ruleInfixExpression )
            {
            // InternalScope.g:3086:1: (lv_params_1_0= ruleInfixExpression )
            // InternalScope.g:3087:3: lv_params_1_0= ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.InfixExpression");
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
    // InternalScope.g:3111:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // InternalScope.g:3112:2: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // InternalScope.g:3113:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInfixExpression=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3120:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // InternalScope.g:3123:28: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // InternalScope.g:3124:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // InternalScope.g:3124:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // InternalScope.g:3125:5: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_42);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // InternalScope.g:3133:1: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop58:
            do {
                int alt58=5;
                int LA58_0 = input.LA(1);

                if ( (LA58_0==45) ) {
                    switch ( input.LA(2) ) {
                    case 82:
                    case 83:
                    case 84:
                        {
                        alt58=2;
                        }
                        break;
                    case RULE_ID:
                        {
                        int LA58_4 = input.LA(3);

                        if ( (LA58_4==EOF||LA58_4==15||LA58_4==18||LA58_4==21||LA58_4==23||LA58_4==26||(LA58_4>=29 && LA58_4<=30)||LA58_4==32||LA58_4==35||(LA58_4>=44 && LA58_4<=45)||(LA58_4>=47 && LA58_4<=49)||(LA58_4>=51 && LA58_4<=52)||(LA58_4>=54 && LA58_4<=66)) ) {
                            alt58=2;
                        }
                        else if ( (LA58_4==25) ) {
                            alt58=1;
                        }


                        }
                        break;
                    case 68:
                        {
                        alt58=3;
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
                        alt58=4;
                        }
                        break;

                    }

                }


                switch (alt58) {
            	case 1 :
            	    // InternalScope.g:3133:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // InternalScope.g:3133:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // InternalScope.g:3133:3: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // InternalScope.g:3133:3: ()
            	    // InternalScope.g:3134:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,45,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	          
            	    }
            	    // InternalScope.g:3143:1: ( (lv_name_3_0= ruleIdentifier ) )
            	    // InternalScope.g:3144:1: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // InternalScope.g:3144:1: (lv_name_3_0= ruleIdentifier )
            	    // InternalScope.g:3145:3: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_29);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_4=(Token)match(input,25,FOLLOW_59); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	          
            	    }
            	    // InternalScope.g:3165:1: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt55=2;
            	    int LA55_0 = input.LA(1);

            	    if ( ((LA55_0>=RULE_STRING && LA55_0<=RULE_ID)||LA55_0==20||LA55_0==25||LA55_0==46||LA55_0==50||LA55_0==53||LA55_0==65||(LA55_0>=67 && LA55_0<=84)) ) {
            	        alt55=1;
            	    }
            	    switch (alt55) {
            	        case 1 :
            	            // InternalScope.g:3165:2: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // InternalScope.g:3165:2: ( (lv_params_5_0= ruleExpression ) )
            	            // InternalScope.g:3166:1: (lv_params_5_0= ruleExpression )
            	            {
            	            // InternalScope.g:3166:1: (lv_params_5_0= ruleExpression )
            	            // InternalScope.g:3167:3: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_31);
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
            	                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
            	              	        afterParserOrEnumRuleCall();
            	              	    
            	            }

            	            }


            	            }

            	            // InternalScope.g:3183:2: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop54:
            	            do {
            	                int alt54=2;
            	                int LA54_0 = input.LA(1);

            	                if ( (LA54_0==35) ) {
            	                    alt54=1;
            	                }


            	                switch (alt54) {
            	            	case 1 :
            	            	    // InternalScope.g:3183:4: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,35,FOLLOW_17); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	          
            	            	    }
            	            	    // InternalScope.g:3187:1: ( (lv_params_7_0= ruleExpression ) )
            	            	    // InternalScope.g:3188:1: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // InternalScope.g:3188:1: (lv_params_7_0= ruleExpression )
            	            	    // InternalScope.g:3189:3: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_31);
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
            	            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
            	            	      	        afterParserOrEnumRuleCall();
            	            	      	    
            	            	    }

            	            	    }


            	            	    }


            	            	    }
            	            	    break;

            	            	default :
            	            	    break loop54;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,26,FOLLOW_42); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalScope.g:3210:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // InternalScope.g:3210:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // InternalScope.g:3210:7: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // InternalScope.g:3210:7: ()
            	    // InternalScope.g:3211:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_10=(Token)match(input,45,FOLLOW_44); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	          
            	    }
            	    // InternalScope.g:3220:1: ( (lv_type_11_0= ruleType ) )
            	    // InternalScope.g:3221:1: (lv_type_11_0= ruleType )
            	    {
            	    // InternalScope.g:3221:1: (lv_type_11_0= ruleType )
            	    // InternalScope.g:3222:3: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_42);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Type");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalScope.g:3239:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // InternalScope.g:3239:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // InternalScope.g:3239:7: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // InternalScope.g:3239:7: ()
            	    // InternalScope.g:3240:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_13=(Token)match(input,45,FOLLOW_60); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	          
            	    }
            	    // InternalScope.g:3249:1: ( (lv_name_14_0= 'typeSelect' ) )
            	    // InternalScope.g:3250:1: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // InternalScope.g:3250:1: (lv_name_14_0= 'typeSelect' )
            	    // InternalScope.g:3251:3: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,68,FOLLOW_29); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,25,FOLLOW_44); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	          
            	    }
            	    // InternalScope.g:3268:1: ( (lv_type_16_0= ruleType ) )
            	    // InternalScope.g:3269:1: (lv_type_16_0= ruleType )
            	    {
            	    // InternalScope.g:3269:1: (lv_type_16_0= ruleType )
            	    // InternalScope.g:3270:3: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_20);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Type");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_17=(Token)match(input,26,FOLLOW_42); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalScope.g:3291:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // InternalScope.g:3291:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // InternalScope.g:3291:7: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // InternalScope.g:3291:7: ()
            	    // InternalScope.g:3292:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_19=(Token)match(input,45,FOLLOW_61); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	          
            	    }
            	    // InternalScope.g:3301:1: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // InternalScope.g:3302:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // InternalScope.g:3302:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // InternalScope.g:3303:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // InternalScope.g:3303:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    int alt56=8;
            	    switch ( input.LA(1) ) {
            	    case 69:
            	        {
            	        alt56=1;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt56=2;
            	        }
            	        break;
            	    case 71:
            	        {
            	        alt56=3;
            	        }
            	        break;
            	    case 72:
            	        {
            	        alt56=4;
            	        }
            	        break;
            	    case 73:
            	        {
            	        alt56=5;
            	        }
            	        break;
            	    case 74:
            	        {
            	        alt56=6;
            	        }
            	        break;
            	    case 75:
            	        {
            	        alt56=7;
            	        }
            	        break;
            	    case 76:
            	        {
            	        alt56=8;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 56, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt56) {
            	        case 1 :
            	            // InternalScope.g:3304:3: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,69,FOLLOW_29); if (state.failed) return current;
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
            	            // InternalScope.g:3316:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,70,FOLLOW_29); if (state.failed) return current;
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
            	            // InternalScope.g:3328:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,71,FOLLOW_29); if (state.failed) return current;
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
            	            // InternalScope.g:3340:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,72,FOLLOW_29); if (state.failed) return current;
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
            	            // InternalScope.g:3352:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,73,FOLLOW_29); if (state.failed) return current;
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
            	            // InternalScope.g:3364:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,74,FOLLOW_29); if (state.failed) return current;
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
            	            // InternalScope.g:3376:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,75,FOLLOW_29); if (state.failed) return current;
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
            	            // InternalScope.g:3388:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,76,FOLLOW_29); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,25,FOLLOW_17); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	          
            	    }
            	    // InternalScope.g:3407:1: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
            	    int alt57=2;
            	    int LA57_0 = input.LA(1);

            	    if ( (LA57_0==RULE_ID) ) {
            	        int LA57_1 = input.LA(2);

            	        if ( (LA57_1==42) ) {
            	            alt57=1;
            	        }
            	    }
            	    switch (alt57) {
            	        case 1 :
            	            // InternalScope.g:3407:2: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // InternalScope.g:3407:2: ( (lv_var_22_0= ruleIdentifier ) )
            	            // InternalScope.g:3408:1: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // InternalScope.g:3408:1: (lv_var_22_0= ruleIdentifier )
            	            // InternalScope.g:3409:3: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_40);
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
            	                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
            	              	        afterParserOrEnumRuleCall();
            	              	    
            	            }

            	            }


            	            }

            	            otherlv_23=(Token)match(input,42,FOLLOW_17); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // InternalScope.g:3429:3: ( (lv_exp_24_0= ruleExpression ) )
            	    // InternalScope.g:3430:1: (lv_exp_24_0= ruleExpression )
            	    {
            	    // InternalScope.g:3430:1: (lv_exp_24_0= ruleExpression )
            	    // InternalScope.g:3431:3: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_20);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_25=(Token)match(input,26,FOLLOW_42); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6());
            	          
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop58;
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
    // InternalScope.g:3459:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // InternalScope.g:3460:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // InternalScope.g:3461:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3468:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // InternalScope.g:3471:28: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // InternalScope.g:3472:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // InternalScope.g:3472:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            int alt59=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case 77:
            case 78:
            case 79:
                {
                alt59=1;
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
                alt59=2;
                }
                break;
            case 20:
                {
                alt59=3;
                }
                break;
            case 81:
                {
                alt59=4;
                }
                break;
            case 80:
                {
                alt59=5;
                }
                break;
            case 25:
                {
                alt59=6;
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
                    // InternalScope.g:3473:5: this_Literal_0= ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3483:5: this_FeatureCall_1= ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3493:5: this_ListLiteral_2= ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3503:5: this_ConstructorCallExpression_3= ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3513:5: this_GlobalVarExpression_4= ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3523:5: this_ParanthesizedExpression_5= ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:3539:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // InternalScope.g:3540:2: (iv_ruleLiteral= ruleLiteral EOF )
            // InternalScope.g:3541:2: iv_ruleLiteral= ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3548:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:3551:28: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // InternalScope.g:3552:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // InternalScope.g:3552:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            int alt60=5;
            switch ( input.LA(1) ) {
            case 77:
            case 78:
                {
                alt60=1;
                }
                break;
            case RULE_INT:
                {
                alt60=2;
                }
                break;
            case 79:
                {
                alt60=3;
                }
                break;
            case RULE_REAL:
                {
                alt60=4;
                }
                break;
            case RULE_STRING:
                {
                alt60=5;
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
                    // InternalScope.g:3553:5: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3563:5: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3573:5: this_NullLiteral_2= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3583:5: this_RealLiteral_3= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3593:5: this_StringLiteral_4= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:3609:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // InternalScope.g:3610:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // InternalScope.g:3611:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3618:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;

         enterRule(); 
            
        try {
            // InternalScope.g:3621:28: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // InternalScope.g:3622:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // InternalScope.g:3622:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // InternalScope.g:3623:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // InternalScope.g:3623:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // InternalScope.g:3624:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // InternalScope.g:3624:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==77) ) {
                alt61=1;
            }
            else if ( (LA61_0==78) ) {
                alt61=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // InternalScope.g:3625:3: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,77,FOLLOW_2); if (state.failed) return current;
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
                    // InternalScope.g:3637:8: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,78,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:3660:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // InternalScope.g:3661:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // InternalScope.g:3662:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3669:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // InternalScope.g:3672:28: ( ( (lv_val_0_0= RULE_INT ) ) )
            // InternalScope.g:3673:1: ( (lv_val_0_0= RULE_INT ) )
            {
            // InternalScope.g:3673:1: ( (lv_val_0_0= RULE_INT ) )
            // InternalScope.g:3674:1: (lv_val_0_0= RULE_INT )
            {
            // InternalScope.g:3674:1: (lv_val_0_0= RULE_INT )
            // InternalScope.g:3675:3: lv_val_0_0= RULE_INT
            {
            lv_val_0_0=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
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
                      		"org.eclipse.xtext.common.Terminals.INT");
              	    
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
    // InternalScope.g:3699:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // InternalScope.g:3700:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // InternalScope.g:3701:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3708:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // InternalScope.g:3711:28: ( ( (lv_val_0_0= 'null' ) ) )
            // InternalScope.g:3712:1: ( (lv_val_0_0= 'null' ) )
            {
            // InternalScope.g:3712:1: ( (lv_val_0_0= 'null' ) )
            // InternalScope.g:3713:1: (lv_val_0_0= 'null' )
            {
            // InternalScope.g:3713:1: (lv_val_0_0= 'null' )
            // InternalScope.g:3714:3: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,79,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:3735:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // InternalScope.g:3736:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // InternalScope.g:3737:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3744:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // InternalScope.g:3747:28: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // InternalScope.g:3748:1: ( (lv_val_0_0= RULE_REAL ) )
            {
            // InternalScope.g:3748:1: ( (lv_val_0_0= RULE_REAL ) )
            // InternalScope.g:3749:1: (lv_val_0_0= RULE_REAL )
            {
            // InternalScope.g:3749:1: (lv_val_0_0= RULE_REAL )
            // InternalScope.g:3750:3: lv_val_0_0= RULE_REAL
            {
            lv_val_0_0=(Token)match(input,RULE_REAL,FOLLOW_2); if (state.failed) return current;
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.REAL");
              	    
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
    // InternalScope.g:3774:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // InternalScope.g:3775:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalScope.g:3776:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3783:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // InternalScope.g:3786:28: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // InternalScope.g:3787:1: ( (lv_val_0_0= RULE_STRING ) )
            {
            // InternalScope.g:3787:1: ( (lv_val_0_0= RULE_STRING ) )
            // InternalScope.g:3788:1: (lv_val_0_0= RULE_STRING )
            {
            // InternalScope.g:3788:1: (lv_val_0_0= RULE_STRING )
            // InternalScope.g:3789:3: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
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
                      		"org.eclipse.xtext.common.Terminals.STRING");
              	    
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
    // InternalScope.g:3813:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // InternalScope.g:3814:2: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // InternalScope.g:3815:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParanthesizedExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3822:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:3825:28: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // InternalScope.g:3826:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // InternalScope.g:3826:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // InternalScope.g:3826:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_20);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:3851:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // InternalScope.g:3852:2: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // InternalScope.g:3853:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalVarExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3860:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:3863:28: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // InternalScope.g:3864:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // InternalScope.g:3864:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // InternalScope.g:3864:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,80,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
                  
            }
            // InternalScope.g:3868:1: ( (lv_name_1_0= ruleIdentifier ) )
            // InternalScope.g:3869:1: (lv_name_1_0= ruleIdentifier )
            {
            // InternalScope.g:3869:1: (lv_name_1_0= ruleIdentifier )
            // InternalScope.g:3870:3: lv_name_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
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
    // InternalScope.g:3894:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // InternalScope.g:3895:2: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // InternalScope.g:3896:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFeatureCall=ruleFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCall; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3903:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:3906:28: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // InternalScope.g:3907:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // InternalScope.g:3907:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            int alt62=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA62_1 = input.LA(2);

                if ( (LA62_1==EOF||LA62_1==15||LA62_1==18||LA62_1==21||LA62_1==23||LA62_1==26||(LA62_1>=29 && LA62_1<=30)||LA62_1==32||LA62_1==35||(LA62_1>=44 && LA62_1<=45)||(LA62_1>=47 && LA62_1<=49)||(LA62_1>=51 && LA62_1<=52)||(LA62_1>=54 && LA62_1<=66)) ) {
                    alt62=2;
                }
                else if ( (LA62_1==25) ) {
                    alt62=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 1, input);

                    throw nvae;
                }
                }
                break;
            case 82:
            case 83:
            case 84:
                {
                alt62=2;
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
                alt62=3;
                }
                break;
            case 68:
                {
                alt62=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }

            switch (alt62) {
                case 1 :
                    // InternalScope.g:3908:5: this_OperationCall_0= ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3917:6: ( (lv_type_1_0= ruleType ) )
                    {
                    // InternalScope.g:3917:6: ( (lv_type_1_0= ruleType ) )
                    // InternalScope.g:3918:1: (lv_type_1_0= ruleType )
                    {
                    // InternalScope.g:3918:1: (lv_type_1_0= ruleType )
                    // InternalScope.g:3919:3: lv_type_1_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_2);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Type");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalScope.g:3937:5: this_CollectionExpression_2= ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:3947:5: this_TypeSelectExpression_3= ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:3963:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // InternalScope.g:3964:2: (iv_ruleOperationCall= ruleOperationCall EOF )
            // InternalScope.g:3965:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOperationCall=ruleOperationCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationCall; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:3972:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // InternalScope.g:3975:28: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // InternalScope.g:3976:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // InternalScope.g:3976:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // InternalScope.g:3976:2: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // InternalScope.g:3976:2: ( (lv_name_0_0= ruleIdentifier ) )
            // InternalScope.g:3977:1: (lv_name_0_0= ruleIdentifier )
            {
            // InternalScope.g:3977:1: (lv_name_0_0= ruleIdentifier )
            // InternalScope.g:3978:3: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_29);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,25,FOLLOW_59); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalScope.g:3998:1: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( ((LA64_0>=RULE_STRING && LA64_0<=RULE_ID)||LA64_0==20||LA64_0==25||LA64_0==46||LA64_0==50||LA64_0==53||LA64_0==65||(LA64_0>=67 && LA64_0<=84)) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // InternalScope.g:3998:2: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // InternalScope.g:3998:2: ( (lv_params_2_0= ruleExpression ) )
                    // InternalScope.g:3999:1: (lv_params_2_0= ruleExpression )
                    {
                    // InternalScope.g:3999:1: (lv_params_2_0= ruleExpression )
                    // InternalScope.g:4000:3: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_31);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalScope.g:4016:2: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==35) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // InternalScope.g:4016:4: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,35,FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // InternalScope.g:4020:1: ( (lv_params_4_0= ruleExpression ) )
                    	    // InternalScope.g:4021:1: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // InternalScope.g:4021:1: (lv_params_4_0= ruleExpression )
                    	    // InternalScope.g:4022:3: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_31);
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
                    	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop63;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:4050:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // InternalScope.g:4051:2: (iv_ruleListLiteral= ruleListLiteral EOF )
            // InternalScope.g:4052:2: iv_ruleListLiteral= ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleListLiteral=ruleListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListLiteral; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4059:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:4062:28: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // InternalScope.g:4063:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // InternalScope.g:4063:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // InternalScope.g:4063:2: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // InternalScope.g:4063:2: ()
            // InternalScope.g:4064:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListLiteralAccess().getListLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,20,FOLLOW_62); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // InternalScope.g:4073:1: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( ((LA66_0>=RULE_STRING && LA66_0<=RULE_ID)||LA66_0==20||LA66_0==25||LA66_0==46||LA66_0==50||LA66_0==53||LA66_0==65||(LA66_0>=67 && LA66_0<=84)) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // InternalScope.g:4073:2: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // InternalScope.g:4073:2: ( (lv_elements_2_0= ruleExpression ) )
                    // InternalScope.g:4074:1: (lv_elements_2_0= ruleExpression )
                    {
                    // InternalScope.g:4074:1: (lv_elements_2_0= ruleExpression )
                    // InternalScope.g:4075:3: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_63);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // InternalScope.g:4091:2: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==35) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // InternalScope.g:4091:4: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,35,FOLLOW_17); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // InternalScope.g:4095:1: ( (lv_elements_4_0= ruleExpression ) )
                    	    // InternalScope.g:4096:1: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // InternalScope.g:4096:1: (lv_elements_4_0= ruleExpression )
                    	    // InternalScope.g:4097:3: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_63);
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
                    	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:4125:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // InternalScope.g:4126:2: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // InternalScope.g:4127:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConstructorCallExpression=ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorCallExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4134:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:4137:28: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // InternalScope.g:4138:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // InternalScope.g:4138:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // InternalScope.g:4138:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,81,FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
                  
            }
            // InternalScope.g:4142:1: ( (lv_type_1_0= ruleSimpleType ) )
            // InternalScope.g:4143:1: (lv_type_1_0= ruleSimpleType )
            {
            // InternalScope.g:4143:1: (lv_type_1_0= ruleSimpleType )
            // InternalScope.g:4144:3: lv_type_1_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_2);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.SimpleType");
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
    // InternalScope.g:4168:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // InternalScope.g:4169:2: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // InternalScope.g:4170:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleTypeSelectExpression=ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeSelectExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4177:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:4180:28: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // InternalScope.g:4181:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // InternalScope.g:4181:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // InternalScope.g:4181:2: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // InternalScope.g:4181:2: ( (lv_name_0_0= 'typeSelect' ) )
            // InternalScope.g:4182:1: (lv_name_0_0= 'typeSelect' )
            {
            // InternalScope.g:4182:1: (lv_name_0_0= 'typeSelect' )
            // InternalScope.g:4183:3: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,68,FOLLOW_29); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,25,FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalScope.g:4200:1: ( (lv_type_2_0= ruleType ) )
            // InternalScope.g:4201:1: (lv_type_2_0= ruleType )
            {
            // InternalScope.g:4201:1: (lv_type_2_0= ruleType )
            // InternalScope.g:4202:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_20);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Type");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:4230:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // InternalScope.g:4231:2: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // InternalScope.g:4232:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCollectionExpression=ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionExpression; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4239:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // InternalScope.g:4242:28: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // InternalScope.g:4243:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // InternalScope.g:4243:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // InternalScope.g:4243:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // InternalScope.g:4243:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // InternalScope.g:4244:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // InternalScope.g:4244:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // InternalScope.g:4245:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // InternalScope.g:4245:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            int alt67=8;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt67=1;
                }
                break;
            case 70:
                {
                alt67=2;
                }
                break;
            case 71:
                {
                alt67=3;
                }
                break;
            case 72:
                {
                alt67=4;
                }
                break;
            case 73:
                {
                alt67=5;
                }
                break;
            case 74:
                {
                alt67=6;
                }
                break;
            case 75:
                {
                alt67=7;
                }
                break;
            case 76:
                {
                alt67=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }

            switch (alt67) {
                case 1 :
                    // InternalScope.g:4246:3: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,69,FOLLOW_29); if (state.failed) return current;
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
                    // InternalScope.g:4258:8: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,70,FOLLOW_29); if (state.failed) return current;
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
                    // InternalScope.g:4270:8: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,71,FOLLOW_29); if (state.failed) return current;
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
                    // InternalScope.g:4282:8: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,72,FOLLOW_29); if (state.failed) return current;
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
                    // InternalScope.g:4294:8: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,73,FOLLOW_29); if (state.failed) return current;
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
                    // InternalScope.g:4306:8: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,74,FOLLOW_29); if (state.failed) return current;
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
                    // InternalScope.g:4318:8: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,75,FOLLOW_29); if (state.failed) return current;
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
                    // InternalScope.g:4330:8: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,76,FOLLOW_29); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,25,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // InternalScope.g:4349:1: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==RULE_ID) ) {
                int LA68_1 = input.LA(2);

                if ( (LA68_1==42) ) {
                    alt68=1;
                }
            }
            switch (alt68) {
                case 1 :
                    // InternalScope.g:4349:2: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // InternalScope.g:4349:2: ( (lv_var_2_0= ruleIdentifier ) )
                    // InternalScope.g:4350:1: (lv_var_2_0= ruleIdentifier )
                    {
                    // InternalScope.g:4350:1: (lv_var_2_0= ruleIdentifier )
                    // InternalScope.g:4351:3: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_40);
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
                              		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,42,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // InternalScope.g:4371:3: ( (lv_exp_4_0= ruleExpression ) )
            // InternalScope.g:4372:1: (lv_exp_4_0= ruleExpression )
            {
            // InternalScope.g:4372:1: (lv_exp_4_0= ruleExpression )
            // InternalScope.g:4373:3: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_20);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_5=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:4401:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalScope.g:4402:2: (iv_ruleType= ruleType EOF )
            // InternalScope.g:4403:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4410:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:4413:28: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // InternalScope.g:4414:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // InternalScope.g:4414:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( ((LA69_0>=82 && LA69_0<=84)) ) {
                alt69=1;
            }
            else if ( (LA69_0==RULE_ID) ) {
                alt69=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 69, 0, input);

                throw nvae;
            }
            switch (alt69) {
                case 1 :
                    // InternalScope.g:4415:5: this_CollectionType_0= ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalScope.g:4425:5: this_SimpleType_1= ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalScope.g:4441:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // InternalScope.g:4442:2: (iv_ruleCollectionType= ruleCollectionType EOF )
            // InternalScope.g:4443:2: iv_ruleCollectionType= ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCollectionType=ruleCollectionType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4450:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // InternalScope.g:4453:28: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // InternalScope.g:4454:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // InternalScope.g:4454:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // InternalScope.g:4454:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // InternalScope.g:4454:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // InternalScope.g:4455:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // InternalScope.g:4455:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // InternalScope.g:4456:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // InternalScope.g:4456:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            int alt70=3;
            switch ( input.LA(1) ) {
            case 82:
                {
                alt70=1;
                }
                break;
            case 83:
                {
                alt70=2;
                }
                break;
            case 84:
                {
                alt70=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }

            switch (alt70) {
                case 1 :
                    // InternalScope.g:4457:3: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,82,FOLLOW_64); if (state.failed) return current;
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
                    // InternalScope.g:4469:8: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,83,FOLLOW_64); if (state.failed) return current;
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
                    // InternalScope.g:4481:8: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,84,FOLLOW_64); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,31,FOLLOW_44); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // InternalScope.g:4500:1: ( (lv_id1_2_0= ruleSimpleType ) )
            // InternalScope.g:4501:1: (lv_id1_2_0= ruleSimpleType )
            {
            // InternalScope.g:4501:1: (lv_id1_2_0= ruleSimpleType )
            // InternalScope.g:4502:3: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_28);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.SimpleType");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_3=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:4530:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // InternalScope.g:4531:2: (iv_ruleSimpleType= ruleSimpleType EOF )
            // InternalScope.g:4532:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleType; 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4539:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;


         enterRule(); 
            
        try {
            // InternalScope.g:4542:28: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // InternalScope.g:4543:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // InternalScope.g:4543:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // InternalScope.g:4543:2: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // InternalScope.g:4543:2: ( (lv_id_0_0= ruleIdentifier ) )
            // InternalScope.g:4544:1: (lv_id_0_0= ruleIdentifier )
            {
            // InternalScope.g:4544:1: (lv_id_0_0= ruleIdentifier )
            // InternalScope.g:4545:3: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_41);
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
                      		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // InternalScope.g:4561:2: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop71:
            do {
                int alt71=2;
                int LA71_0 = input.LA(1);

                if ( (LA71_0==44) ) {
                    alt71=1;
                }


                switch (alt71) {
            	case 1 :
            	    // InternalScope.g:4561:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,44,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	          
            	    }
            	    // InternalScope.g:4565:1: ( (lv_id_2_0= ruleIdentifier ) )
            	    // InternalScope.g:4566:1: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // InternalScope.g:4566:1: (lv_id_2_0= ruleIdentifier )
            	    // InternalScope.g:4567:3: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_41);
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
            	              		"com.avaloq.tools.ddk.xtext.expression.Expression.Identifier");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop71;
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
    // InternalScope.g:4591:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalScope.g:4592:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalScope.g:4593:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

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
    // InternalScope.g:4600:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // InternalScope.g:4603:28: (this_ID_0= RULE_ID )
            // InternalScope.g:4604:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
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
    // InternalScope.g:4619:1: ruleCasing returns [Enumerator current=null] : ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) ) ;
    public final Enumerator ruleCasing() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;

         enterRule(); 
        try {
            // InternalScope.g:4621:28: ( ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) ) )
            // InternalScope.g:4622:1: ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) )
            {
            // InternalScope.g:4622:1: ( (enumLiteral_0= 'sensitive' ) | (enumLiteral_1= 'insensitive' ) )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==85) ) {
                alt72=1;
            }
            else if ( (LA72_0==86) ) {
                alt72=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // InternalScope.g:4622:2: (enumLiteral_0= 'sensitive' )
                    {
                    // InternalScope.g:4622:2: (enumLiteral_0= 'sensitive' )
                    // InternalScope.g:4622:4: enumLiteral_0= 'sensitive'
                    {
                    enumLiteral_0=(Token)match(input,85,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current = grammarAccess.getCasingAccess().getSENSITIVEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                              newLeafNode(enumLiteral_0, grammarAccess.getCasingAccess().getSENSITIVEEnumLiteralDeclaration_0()); 
                          
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalScope.g:4628:6: (enumLiteral_1= 'insensitive' )
                    {
                    // InternalScope.g:4628:6: (enumLiteral_1= 'insensitive' )
                    // InternalScope.g:4628:8: enumLiteral_1= 'insensitive'
                    {
                    enumLiteral_1=(Token)match(input,86,FOLLOW_2); if (state.failed) return current;
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

    // $ANTLR start synpred1_InternalScope
    public final void synpred1_InternalScope_fragment() throws RecognitionException {   
        // InternalScope.g:1603:3: ( '(' )
        // InternalScope.g:1603:5: '('
        {
        match(input,25,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalScope

    // $ANTLR start synpred2_InternalScope
    public final void synpred2_InternalScope_fragment() throws RecognitionException {   
        // InternalScope.g:1870:7: ( ruleCastedExpression )
        // InternalScope.g:1870:9: ruleCastedExpression
        {
        pushFollow(FOLLOW_2);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_InternalScope

    // $ANTLR start synpred3_InternalScope
    public final void synpred3_InternalScope_fragment() throws RecognitionException {   
        // InternalScope.g:2296:3: ( 'else' )
        // InternalScope.g:2296:5: 'else'
        {
        match(input,52,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_InternalScope

    // Delegated rules

    public final boolean synpred2_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalScope_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalScope() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalScope_fragment(); // can never throw exception
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
    protected DFA31 dfa31 = new DFA31(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String dfa_1s = "\6\uffff";
    static final String dfa_2s = "\1\7\1\24\1\7\2\uffff\1\24";
    static final String dfa_3s = "\1\7\1\54\1\7\2\uffff\1\54";
    static final String dfa_4s = "\3\uffff\1\1\1\2\1\uffff";
    static final String dfa_5s = "\6\uffff}>";
    static final String[] dfa_6s = {
            "\1\1",
            "\1\3\6\uffff\1\4\20\uffff\1\2",
            "\1\5",
            "",
            "",
            "\1\3\6\uffff\1\4\20\uffff\1\2"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "572:3: ( ( ( ruleQualifiedID ) ) | ( ( ( ruleQualifiedID ) ) otherlv_6= '#' ( ( ruleIdentifier ) ) ) )";
        }
    }
    static final String dfa_7s = "\40\uffff";
    static final String dfa_8s = "\1\4\1\0\36\uffff";
    static final String dfa_9s = "\1\124\1\0\36\uffff";
    static final String dfa_10s = "\2\uffff\1\2\34\uffff\1\1";
    static final String dfa_11s = "\1\uffff\1\0\36\uffff}>";
    static final String[] dfa_12s = {
            "\4\2\14\uffff\1\2\4\uffff\1\1\7\uffff\1\2\11\uffff\1\2\2\uffff\1\2\3\uffff\1\2\2\uffff\1\2\13\uffff\1\2\1\uffff\22\2",
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
            "",
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

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "1603:1: ( ( ( '(' )=> (otherlv_0= '(' ( (lv_names_1_0= ruleNamingExpression ) ) (otherlv_2= ',' ( (lv_names_3_0= ruleNamingExpression ) ) )* otherlv_4= ')' ) ) | ( (lv_names_5_0= ruleNamingExpression ) ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA31_1 = input.LA(1);

                         
                        int index31_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalScope()) ) {s = 31;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index31_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 31, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_13s = "\36\uffff";
    static final String dfa_14s = "\1\4\1\uffff\1\0\33\uffff";
    static final String dfa_15s = "\1\124\1\uffff\1\0\33\uffff";
    static final String dfa_16s = "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String dfa_17s = "\2\uffff\1\0\33\uffff}>";
    static final String[] dfa_18s = {
            "\4\3\14\uffff\1\3\4\uffff\1\2\24\uffff\1\1\3\uffff\1\3\2\uffff\1\3\13\uffff\1\3\1\uffff\22\3",
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

    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final char[] dfa_14 = DFA.unpackEncodedStringToUnsignedChars(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[][] dfa_18 = unpackEncodedStringArray(dfa_18s);

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "1860:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_2 = input.LA(1);

                         
                        int index36_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_InternalScope()) ) {s = 29;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index36_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000010F6002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000010F4002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000000010F0002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000010E0002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000000L,0x0000000000600000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200080L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x00244802021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000002000080L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000010200000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000040000080L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x00244816021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x00244812021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000804000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000048002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000080000080L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000042000080L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000080L,0x00000000001C0000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x00000000021000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0040000000040000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0100000000000002L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0xFC00000000000002L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000040000002L,0x0000000000000004L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x00244802061000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001FE0L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x00244802023000F0L,0x00000000001FFFFAL});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000800200000L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x0000000080000000L});

}