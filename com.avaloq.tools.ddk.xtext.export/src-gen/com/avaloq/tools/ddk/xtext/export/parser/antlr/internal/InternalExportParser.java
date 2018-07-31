package com.avaloq.tools.ddk.xtext.export.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.avaloq.tools.ddk.xtext.export.services.ExportGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalExportParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_REAL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'export'", "'extension'", "'for'", "'interface'", "'{'", "'}'", "'import'", "'as'", "'['", "']'", "'='", "','", "';'", "'+'", "'@'", "'eval'", "'('", "')'", "'lookup'", "'qualified'", "'uri-fragment'", "'unique'", "'attribute'", "'object-fingerprint'", "'resource-fingerprint'", "'field'", "'data'", "'::'", "'let'", "':'", "'->'", "'?'", "'if'", "'then'", "'else'", "'switch'", "'default'", "'case'", "'||'", "'&&'", "'implies'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'-'", "'*'", "'/'", "'!'", "'.'", "'typeSelect'", "'collect'", "'select'", "'selectFirst'", "'reject'", "'exists'", "'notExists'", "'sortBy'", "'forAll'", "'|'", "'true'", "'false'", "'null'", "'GLOBALVAR'", "'new'", "'Collection'", "'List'", "'Set'"
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
    public static final int RULE_ID=4;
    public static final int RULE_REAL=7;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
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
    public static final int RULE_STRING=5;
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
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalExportParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalExportParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalExportParser.tokenNames; }
    public String getGrammarFileName() { return "../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g"; }



     	private ExportGrammarAccess grammarAccess;
     	
        public InternalExportParser(TokenStream input, ExportGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "ExportModel";	
       	}
       	
       	@Override
       	protected ExportGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleExportModel"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:67:1: entryRuleExportModel returns [EObject current=null] : iv_ruleExportModel= ruleExportModel EOF ;
    public final EObject entryRuleExportModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExportModel = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:68:2: (iv_ruleExportModel= ruleExportModel EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:69:2: iv_ruleExportModel= ruleExportModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExportModelRule()); 
            }
            pushFollow(FOLLOW_ruleExportModel_in_entryRuleExportModel75);
            iv_ruleExportModel=ruleExportModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExportModel; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExportModel85); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExportModel"


    // $ANTLR start "ruleExportModel"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:76:1: ruleExportModel returns [EObject current=null] : ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ ) ;
    public final EObject ruleExportModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_extension_1_0=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_imports_5_0 = null;

        EObject lv_extensions_6_0 = null;

        EObject lv_interfaces_9_0 = null;

        EObject lv_exports_11_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:79:28: ( ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:1: ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:1: ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:2: (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:2: (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==12) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:4: otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) )
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleExportModel123); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_0, grammarAccess.getExportModelAccess().getExportKeyword_0_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:84:1: ( (lv_extension_1_0= 'extension' ) )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==13) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:85:1: (lv_extension_1_0= 'extension' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:85:1: (lv_extension_1_0= 'extension' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:86:3: lv_extension_1_0= 'extension'
                            {
                            lv_extension_1_0=(Token)match(input,13,FOLLOW_13_in_ruleExportModel141); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_extension_1_0, grammarAccess.getExportModelAccess().getExtensionExtensionKeyword_0_1_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExportModelRule());
                              	        }
                                     		setWithLastConsumed(current, "extension", true, "extension");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:99:3: ( (lv_name_2_0= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:100:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:100:1: (lv_name_2_0= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:101:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExportModel172); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_2_0, grammarAccess.getExportModelAccess().getNameIDTerminalRuleCall_0_2_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExportModelRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_2_0, 
                              		"ID");
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,14,FOLLOW_14_in_ruleExportModel189); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getExportModelAccess().getForKeyword_0_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:121:1: ( ( ruleQualifiedID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:122:1: ( ruleQualifiedID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:122:1: ( ruleQualifiedID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:123:3: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getExportModelRule());
                      	        }
                              
                    }
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExportModelAccess().getTargetGrammarGrammarCrossReference_0_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleQualifiedID_in_ruleExportModel212);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:136:4: ( (lv_imports_5_0= ruleImport ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==18) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:137:1: (lv_imports_5_0= ruleImport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:137:1: (lv_imports_5_0= ruleImport )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:138:3: lv_imports_5_0= ruleImport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getImportsImportParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImport_in_ruleExportModel235);
            	    lv_imports_5_0=ruleImport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"imports",
            	              		lv_imports_5_0, 
            	              		"Import");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:154:3: ( (lv_extensions_6_0= ruleExtension ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==13) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:155:1: (lv_extensions_6_0= ruleExtension )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:155:1: (lv_extensions_6_0= ruleExtension )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:156:3: lv_extensions_6_0= ruleExtension
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getExtensionsExtensionParserRuleCall_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExtension_in_ruleExportModel257);
            	    lv_extensions_6_0=ruleExtension();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"extensions",
            	              		lv_extensions_6_0, 
            	              		"Extension");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:172:3: (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:172:5: otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}'
                    {
                    otherlv_7=(Token)match(input,15,FOLLOW_15_in_ruleExportModel271); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_7, grammarAccess.getExportModelAccess().getInterfaceKeyword_3_0());
                          
                    }
                    otherlv_8=(Token)match(input,16,FOLLOW_16_in_ruleExportModel283); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getExportModelAccess().getLeftCurlyBracketKeyword_3_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:180:1: ( (lv_interfaces_9_0= ruleInterface ) )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==RULE_ID) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:181:1: (lv_interfaces_9_0= ruleInterface )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:181:1: (lv_interfaces_9_0= ruleInterface )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:182:3: lv_interfaces_9_0= ruleInterface
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getExportModelAccess().getInterfacesInterfaceParserRuleCall_3_2_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleInterface_in_ruleExportModel304);
                    	    lv_interfaces_9_0=ruleInterface();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"interfaces",
                    	              		lv_interfaces_9_0, 
                    	              		"Interface");
                    	      	        afterParserOrEnumRuleCall();
                    	      	    
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);

                    otherlv_10=(Token)match(input,17,FOLLOW_17_in_ruleExportModel317); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getExportModelAccess().getRightCurlyBracketKeyword_3_3());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:202:3: ( (lv_exports_11_0= ruleExport ) )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==12) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:203:1: (lv_exports_11_0= ruleExport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:203:1: (lv_exports_11_0= ruleExport )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:204:3: lv_exports_11_0= ruleExport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getExportsExportParserRuleCall_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExport_in_ruleExportModel340);
            	    lv_exports_11_0=ruleExport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"exports",
            	              		lv_exports_11_0, 
            	              		"Export");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
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
    // $ANTLR end "ruleExportModel"


    // $ANTLR start "entryRuleImport"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:228:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:229:2: (iv_ruleImport= ruleImport EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:230:2: iv_ruleImport= ruleImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportRule()); 
            }
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport377);
            iv_ruleImport=ruleImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport387); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:237:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:240:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:241:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:241:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:241:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleImport424); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:245:1: ( (otherlv_1= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:246:1: (otherlv_1= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:246:1: (otherlv_1= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:247:3: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getImportRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImport444); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
              	
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:258:2: (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:258:4: otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,19,FOLLOW_19_in_ruleImport457); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportAccess().getAsKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:262:1: ( (lv_name_3_0= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:263:1: (lv_name_3_0= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:263:1: (lv_name_3_0= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:264:3: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleImport474); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_3_0, grammarAccess.getImportAccess().getNameIDTerminalRuleCall_2_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getImportRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_3_0, 
                              		"ID");
                      	    
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:288:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:289:2: (iv_ruleExtension= ruleExtension EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:290:2: iv_ruleExtension= ruleExtension EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExtensionRule()); 
            }
            pushFollow(FOLLOW_ruleExtension_in_entryRuleExtension517);
            iv_ruleExtension=ruleExtension();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExtension; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExtension527); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:297:1: ruleExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_extension_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:300:28: ( (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:301:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:301:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:301:3: otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,13,FOLLOW_13_in_ruleExtension564); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExtensionAccess().getExtensionKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:305:1: ( (lv_extension_1_0= ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:306:1: (lv_extension_1_0= ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:306:1: (lv_extension_1_0= ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:307:3: lv_extension_1_0= ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExtensionAccess().getExtensionQualifiedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExtension585);
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


    // $ANTLR start "entryRuleInterface"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:333:1: entryRuleInterface returns [EObject current=null] : iv_ruleInterface= ruleInterface EOF ;
    public final EObject entryRuleInterface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterface = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:334:2: (iv_ruleInterface= ruleInterface EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:335:2: iv_ruleInterface= ruleInterface EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceRule()); 
            }
            pushFollow(FOLLOW_ruleInterface_in_entryRuleInterface623);
            iv_ruleInterface=ruleInterface();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterface; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterface633); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterface"


    // $ANTLR start "ruleInterface"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:342:1: ruleInterface returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) ;
    public final EObject ruleInterface() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_guard_2_0 = null;

        EObject lv_items_5_0 = null;

        EObject lv_items_7_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:345:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:346:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:346:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:346:2: ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:346:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:347:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:347:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:348:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInterfaceAccess().getTypeEClassCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleInterface681);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:361:2: (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==20) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:361:4: otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']'
                    {
                    otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleInterface694); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getInterfaceAccess().getLeftSquareBracketKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:365:1: ( (lv_guard_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:366:1: (lv_guard_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:366:1: (lv_guard_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:367:3: lv_guard_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInterfaceAccess().getGuardExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleInterface715);
                    lv_guard_2_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getInterfaceRule());
                      	        }
                             		set(
                             			current, 
                             			"guard",
                              		lv_guard_2_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleInterface727); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getInterfaceAccess().getRightSquareBracketKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:387:3: (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:387:5: otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    {
            	    otherlv_4=(Token)match(input,22,FOLLOW_22_in_ruleInterface742); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInterfaceAccess().getEqualsSignKeyword_2_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:391:1: ( (lv_items_5_0= ruleInterfaceItem ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:392:1: (lv_items_5_0= ruleInterfaceItem )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:392:1: (lv_items_5_0= ruleInterfaceItem )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:393:3: lv_items_5_0= ruleInterfaceItem
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleInterfaceItem_in_ruleInterface763);
            	    lv_items_5_0=ruleInterfaceItem();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getInterfaceRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"items",
            	              		lv_items_5_0, 
            	              		"InterfaceItem");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:409:2: (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    loop10:
            	    do {
            	        int alt10=2;
            	        int LA10_0 = input.LA(1);

            	        if ( (LA10_0==23) ) {
            	            alt10=1;
            	        }


            	        switch (alt10) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:409:4: otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    {
            	    	    otherlv_6=(Token)match(input,23,FOLLOW_23_in_ruleInterface776); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_6, grammarAccess.getInterfaceAccess().getCommaKeyword_2_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:413:1: ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:414:1: (lv_items_7_0= ruleInterfaceItem )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:414:1: (lv_items_7_0= ruleInterfaceItem )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:415:3: lv_items_7_0= ruleInterfaceItem
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleInterfaceItem_in_ruleInterface797);
            	    	    lv_items_7_0=ruleInterfaceItem();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      	        if (current==null) {
            	    	      	            current = createModelElementForParent(grammarAccess.getInterfaceRule());
            	    	      	        }
            	    	             		add(
            	    	             			current, 
            	    	             			"items",
            	    	              		lv_items_7_0, 
            	    	              		"InterfaceItem");
            	    	      	        afterParserOrEnumRuleCall();
            	    	      	    
            	    	    }

            	    	    }


            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop10;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_8=(Token)match(input,24,FOLLOW_24_in_ruleInterface813); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_8, grammarAccess.getInterfaceAccess().getSemicolonKeyword_3());
                  
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
    // $ANTLR end "ruleInterface"


    // $ANTLR start "entryRuleInterfaceItem"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:443:1: entryRuleInterfaceItem returns [EObject current=null] : iv_ruleInterfaceItem= ruleInterfaceItem EOF ;
    public final EObject entryRuleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceItem = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:444:2: (iv_ruleInterfaceItem= ruleInterfaceItem EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:445:2: iv_ruleInterfaceItem= ruleInterfaceItem EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceItemRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceItem_in_entryRuleInterfaceItem849);
            iv_ruleInterfaceItem=ruleInterfaceItem();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceItem; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceItem859); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterfaceItem"


    // $ANTLR start "ruleInterfaceItem"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:452:1: ruleInterfaceItem returns [EObject current=null] : (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) ;
    public final EObject ruleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject this_InterfaceField_0 = null;

        EObject this_InterfaceNavigation_1 = null;

        EObject this_InterfaceExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:455:28: ( (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:456:1: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:456:1: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
            int alt12=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 25:
                {
                alt12=1;
                }
                break;
            case 26:
                {
                alt12=2;
                }
                break;
            case 27:
                {
                alt12=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:457:5: this_InterfaceField_0= ruleInterfaceField
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceFieldParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceField_in_ruleInterfaceItem906);
                    this_InterfaceField_0=ruleInterfaceField();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_InterfaceField_0; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:467:5: this_InterfaceNavigation_1= ruleInterfaceNavigation
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceNavigationParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceNavigation_in_ruleInterfaceItem933);
                    this_InterfaceNavigation_1=ruleInterfaceNavigation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_InterfaceNavigation_1; 
                              afterParserOrEnumRuleCall();
                          
                    }

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:477:5: this_InterfaceExpression_2= ruleInterfaceExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceExpression_in_ruleInterfaceItem960);
                    this_InterfaceExpression_2=ruleInterfaceExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_InterfaceExpression_2; 
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
    // $ANTLR end "ruleInterfaceItem"


    // $ANTLR start "entryRuleInterfaceField"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:493:1: entryRuleInterfaceField returns [EObject current=null] : iv_ruleInterfaceField= ruleInterfaceField EOF ;
    public final EObject entryRuleInterfaceField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceField = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:494:2: (iv_ruleInterfaceField= ruleInterfaceField EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:495:2: iv_ruleInterfaceField= ruleInterfaceField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceFieldRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceField_in_entryRuleInterfaceField995);
            iv_ruleInterfaceField=ruleInterfaceField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceField; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceField1005); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterfaceField"


    // $ANTLR start "ruleInterfaceField"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:502:1: ruleInterfaceField returns [EObject current=null] : ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceField() throws RecognitionException {
        EObject current = null;

        Token lv_unordered_0_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:505:28: ( ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:506:1: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:506:1: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:506:2: ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:506:2: ( (lv_unordered_0_0= '+' ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==25) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:507:1: (lv_unordered_0_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:507:1: (lv_unordered_0_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:508:3: lv_unordered_0_0= '+'
                    {
                    lv_unordered_0_0=(Token)match(input,25,FOLLOW_25_in_ruleInterfaceField1048); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_unordered_0_0, grammarAccess.getInterfaceFieldAccess().getUnorderedPlusSignKeyword_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getInterfaceFieldRule());
                      	        }
                             		setWithLastConsumed(current, "unordered", true, "+");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:521:3: ( (otherlv_1= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:522:1: (otherlv_1= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:522:1: (otherlv_1= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:523:3: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceFieldRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterfaceField1082); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_1, grammarAccess.getInterfaceFieldAccess().getFieldEStructuralFeatureCrossReference_1_0()); 
              	
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
    // $ANTLR end "ruleInterfaceField"


    // $ANTLR start "entryRuleInterfaceNavigation"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:542:1: entryRuleInterfaceNavigation returns [EObject current=null] : iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF ;
    public final EObject entryRuleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceNavigation = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:543:2: (iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:544:2: iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceNavigationRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceNavigation_in_entryRuleInterfaceNavigation1118);
            iv_ruleInterfaceNavigation=ruleInterfaceNavigation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceNavigation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceNavigation1128); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterfaceNavigation"


    // $ANTLR start "ruleInterfaceNavigation"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:551:1: ruleInterfaceNavigation returns [EObject current=null] : (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_unordered_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:554:28: ( (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:555:1: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:555:1: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:555:3: otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleInterfaceNavigation1165); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInterfaceNavigationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:559:1: ( (lv_unordered_1_0= '+' ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==25) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:560:1: (lv_unordered_1_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:560:1: (lv_unordered_1_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:561:3: lv_unordered_1_0= '+'
                    {
                    lv_unordered_1_0=(Token)match(input,25,FOLLOW_25_in_ruleInterfaceNavigation1183); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_unordered_1_0, grammarAccess.getInterfaceNavigationAccess().getUnorderedPlusSignKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getInterfaceNavigationRule());
                      	        }
                             		setWithLastConsumed(current, "unordered", true, "+");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:574:3: ( (otherlv_2= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:575:1: (otherlv_2= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:575:1: (otherlv_2= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:576:3: otherlv_2= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceNavigationRule());
              	        }
                      
            }
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterfaceNavigation1217); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_2, grammarAccess.getInterfaceNavigationAccess().getRefEReferenceCrossReference_2_0()); 
              	
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
    // $ANTLR end "ruleInterfaceNavigation"


    // $ANTLR start "entryRuleInterfaceExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:595:1: entryRuleInterfaceExpression returns [EObject current=null] : iv_ruleInterfaceExpression= ruleInterfaceExpression EOF ;
    public final EObject entryRuleInterfaceExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:596:2: (iv_ruleInterfaceExpression= ruleInterfaceExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:597:2: iv_ruleInterfaceExpression= ruleInterfaceExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceExpression_in_entryRuleInterfaceExpression1253);
            iv_ruleInterfaceExpression=ruleInterfaceExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceExpression1263); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInterfaceExpression"


    // $ANTLR start "ruleInterfaceExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:604:1: ruleInterfaceExpression returns [EObject current=null] : (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
    public final EObject ruleInterfaceExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_ref_1_0=null;
        Token lv_unordered_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_expr_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:607:28: ( (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:608:1: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:608:1: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:608:3: otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,27,FOLLOW_27_in_ruleInterfaceExpression1300); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInterfaceExpressionAccess().getEvalKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:612:1: ( (lv_ref_1_0= '@' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==26) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:613:1: (lv_ref_1_0= '@' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:613:1: (lv_ref_1_0= '@' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:614:3: lv_ref_1_0= '@'
                    {
                    lv_ref_1_0=(Token)match(input,26,FOLLOW_26_in_ruleInterfaceExpression1318); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_ref_1_0, grammarAccess.getInterfaceExpressionAccess().getRefCommercialAtKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getInterfaceExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "ref", true, "@");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:627:3: ( (lv_unordered_2_0= '+' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==25) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:628:1: (lv_unordered_2_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:628:1: (lv_unordered_2_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:629:3: lv_unordered_2_0= '+'
                    {
                    lv_unordered_2_0=(Token)match(input,25,FOLLOW_25_in_ruleInterfaceExpression1350); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_unordered_2_0, grammarAccess.getInterfaceExpressionAccess().getUnorderedPlusSignKeyword_2_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getInterfaceExpressionRule());
                      	        }
                             		setWithLastConsumed(current, "unordered", true, "+");
                      	    
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,28,FOLLOW_28_in_ruleInterfaceExpression1376); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getInterfaceExpressionAccess().getLeftParenthesisKeyword_3());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:646:1: ( (lv_expr_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:647:1: (lv_expr_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:647:1: (lv_expr_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:648:3: lv_expr_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInterfaceExpressionAccess().getExprExpressionParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleInterfaceExpression1397);
            lv_expr_4_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getInterfaceExpressionRule());
              	        }
                     		set(
                     			current, 
                     			"expr",
                      		lv_expr_4_0, 
                      		"Expression");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            otherlv_5=(Token)match(input,29,FOLLOW_29_in_ruleInterfaceExpression1409); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_5, grammarAccess.getInterfaceExpressionAccess().getRightParenthesisKeyword_5());
                  
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
    // $ANTLR end "ruleInterfaceExpression"


    // $ANTLR start "entryRuleExport"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:676:1: entryRuleExport returns [EObject current=null] : iv_ruleExport= ruleExport EOF ;
    public final EObject entryRuleExport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:677:2: (iv_ruleExport= ruleExport EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:678:2: iv_ruleExport= ruleExport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExportRule()); 
            }
            pushFollow(FOLLOW_ruleExport_in_entryRuleExport1445);
            iv_ruleExport=ruleExport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExport1455); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExport"


    // $ANTLR start "ruleExport"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:685:1: ruleExport returns [EObject current=null] : (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' ) ;
    public final EObject ruleExport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_lookup_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_qualifiedName_7_0=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token lv_fragmentUnique_15_0=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_19=null;
        Token otherlv_20=null;
        Token lv_fingerprint_21_0=null;
        Token lv_resourceFingerprint_22_0=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        Token otherlv_31=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        EObject lv_lookupPredicate_3_0 = null;

        EObject lv_naming_8_0 = null;

        EObject lv_guard_10_0 = null;

        EObject lv_attributes_25_0 = null;

        EObject lv_attributes_27_0 = null;

        EObject lv_userData_30_0 = null;

        EObject lv_userData_32_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:688:28: ( (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:689:1: (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:689:1: (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:689:3: otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleExport1492); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExportAccess().getExportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:693:1: ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==30) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:693:2: ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )?
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:693:2: ( (lv_lookup_1_0= 'lookup' ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:694:1: (lv_lookup_1_0= 'lookup' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:694:1: (lv_lookup_1_0= 'lookup' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:695:3: lv_lookup_1_0= 'lookup'
                    {
                    lv_lookup_1_0=(Token)match(input,30,FOLLOW_30_in_ruleExport1511); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_lookup_1_0, grammarAccess.getExportAccess().getLookupLookupKeyword_1_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExportRule());
                      	        }
                             		setWithLastConsumed(current, "lookup", true, "lookup");
                      	    
                    }

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:708:2: (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==20) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:708:4: otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']'
                            {
                            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleExport1537); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getExportAccess().getLeftSquareBracketKeyword_1_1_0());
                                  
                            }
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:712:1: ( (lv_lookupPredicate_3_0= ruleExpression ) )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:713:1: (lv_lookupPredicate_3_0= ruleExpression )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:713:1: (lv_lookupPredicate_3_0= ruleExpression )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:714:3: lv_lookupPredicate_3_0= ruleExpression
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExportAccess().getLookupPredicateExpressionParserRuleCall_1_1_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpression_in_ruleExport1558);
                            lv_lookupPredicate_3_0=ruleExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExportRule());
                              	        }
                                     		set(
                                     			current, 
                                     			"lookupPredicate",
                                      		lv_lookupPredicate_3_0, 
                                      		"Expression");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            otherlv_4=(Token)match(input,21,FOLLOW_21_in_ruleExport1570); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getExportAccess().getRightSquareBracketKeyword_1_1_2());
                                  
                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:734:5: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:735:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:735:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:736:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getExportRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExportAccess().getTypeEClassCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExport1597);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:749:2: (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==19) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:749:4: otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) )
                    {
                    otherlv_6=(Token)match(input,19,FOLLOW_19_in_ruleExport1610); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getExportAccess().getAsKeyword_3_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:753:1: ( (lv_qualifiedName_7_0= 'qualified' ) )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==31) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:754:1: (lv_qualifiedName_7_0= 'qualified' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:754:1: (lv_qualifiedName_7_0= 'qualified' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:755:3: lv_qualifiedName_7_0= 'qualified'
                            {
                            lv_qualifiedName_7_0=(Token)match(input,31,FOLLOW_31_in_ruleExport1628); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_qualifiedName_7_0, grammarAccess.getExportAccess().getQualifiedNameQualifiedKeyword_3_1_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExportRule());
                              	        }
                                     		setWithLastConsumed(current, "qualifiedName", true, "qualified");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:768:3: ( (lv_naming_8_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:769:1: (lv_naming_8_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:769:1: (lv_naming_8_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:770:3: lv_naming_8_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExportAccess().getNamingExpressionParserRuleCall_3_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleExport1663);
                    lv_naming_8_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExportRule());
                      	        }
                             		set(
                             			current, 
                             			"naming",
                              		lv_naming_8_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:786:4: (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==20) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:786:6: otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']'
                    {
                    otherlv_9=(Token)match(input,20,FOLLOW_20_in_ruleExport1678); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_9, grammarAccess.getExportAccess().getLeftSquareBracketKeyword_4_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:790:1: ( (lv_guard_10_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:791:1: (lv_guard_10_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:791:1: (lv_guard_10_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:792:3: lv_guard_10_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExportAccess().getGuardExpressionParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleExport1699);
                    lv_guard_10_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExportRule());
                      	        }
                             		set(
                             			current, 
                             			"guard",
                              		lv_guard_10_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_11=(Token)match(input,21,FOLLOW_21_in_ruleExport1711); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getExportAccess().getRightSquareBracketKeyword_4_2());
                          
                    }

                    }
                    break;

            }

            otherlv_12=(Token)match(input,16,FOLLOW_16_in_ruleExport1725); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_12, grammarAccess.getExportAccess().getLeftCurlyBracketKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:816:1: (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==32) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:816:3: otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';'
                    {
                    otherlv_13=(Token)match(input,32,FOLLOW_32_in_ruleExport1738); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getExportAccess().getUriFragmentKeyword_6_0());
                          
                    }
                    otherlv_14=(Token)match(input,22,FOLLOW_22_in_ruleExport1750); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getExportAccess().getEqualsSignKeyword_6_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:824:1: ( (lv_fragmentUnique_15_0= 'unique' ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==33) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:825:1: (lv_fragmentUnique_15_0= 'unique' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:825:1: (lv_fragmentUnique_15_0= 'unique' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:826:3: lv_fragmentUnique_15_0= 'unique'
                            {
                            lv_fragmentUnique_15_0=(Token)match(input,33,FOLLOW_33_in_ruleExport1768); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_fragmentUnique_15_0, grammarAccess.getExportAccess().getFragmentUniqueUniqueKeyword_6_2_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExportRule());
                              	        }
                                     		setWithLastConsumed(current, "fragmentUnique", true, "unique");
                              	    
                            }

                            }


                            }
                            break;

                    }

                    otherlv_16=(Token)match(input,34,FOLLOW_34_in_ruleExport1794); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getExportAccess().getAttributeKeyword_6_3());
                          
                    }
                    otherlv_17=(Token)match(input,28,FOLLOW_28_in_ruleExport1806); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_17, grammarAccess.getExportAccess().getLeftParenthesisKeyword_6_4());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:847:1: ( (otherlv_18= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:848:1: (otherlv_18= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:848:1: (otherlv_18= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:849:3: otherlv_18= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getExportRule());
                      	        }
                              
                    }
                    otherlv_18=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExport1826); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_18, grammarAccess.getExportAccess().getFragmentAttributeEAttributeCrossReference_6_5_0()); 
                      	
                    }

                    }


                    }

                    otherlv_19=(Token)match(input,29,FOLLOW_29_in_ruleExport1838); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_19, grammarAccess.getExportAccess().getRightParenthesisKeyword_6_6());
                          
                    }
                    otherlv_20=(Token)match(input,24,FOLLOW_24_in_ruleExport1850); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getExportAccess().getSemicolonKeyword_6_7());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:868:3: ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=35 && LA25_0<=36)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:868:4: ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';'
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:868:4: ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) )
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==35) ) {
                        alt24=1;
                    }
                    else if ( (LA24_0==36) ) {
                        alt24=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 0, input);

                        throw nvae;
                    }
                    switch (alt24) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:868:5: ( (lv_fingerprint_21_0= 'object-fingerprint' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:868:5: ( (lv_fingerprint_21_0= 'object-fingerprint' ) )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:869:1: (lv_fingerprint_21_0= 'object-fingerprint' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:869:1: (lv_fingerprint_21_0= 'object-fingerprint' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:870:3: lv_fingerprint_21_0= 'object-fingerprint'
                            {
                            lv_fingerprint_21_0=(Token)match(input,35,FOLLOW_35_in_ruleExport1872); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_fingerprint_21_0, grammarAccess.getExportAccess().getFingerprintObjectFingerprintKeyword_7_0_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExportRule());
                              	        }
                                     		setWithLastConsumed(current, "fingerprint", true, "object-fingerprint");
                              	    
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:884:6: ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:884:6: ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:885:1: (lv_resourceFingerprint_22_0= 'resource-fingerprint' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:885:1: (lv_resourceFingerprint_22_0= 'resource-fingerprint' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:886:3: lv_resourceFingerprint_22_0= 'resource-fingerprint'
                            {
                            lv_resourceFingerprint_22_0=(Token)match(input,36,FOLLOW_36_in_ruleExport1909); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_resourceFingerprint_22_0, grammarAccess.getExportAccess().getResourceFingerprintResourceFingerprintKeyword_7_0_1_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExportRule());
                              	        }
                                     		setWithLastConsumed(current, "resourceFingerprint", true, "resource-fingerprint");
                              	    
                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_23=(Token)match(input,24,FOLLOW_24_in_ruleExport1935); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_23, grammarAccess.getExportAccess().getSemicolonKeyword_7_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:903:3: ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )*
            loop28:
            do {
                int alt28=3;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==37) ) {
                    alt28=1;
                }
                else if ( (LA28_0==38) ) {
                    alt28=2;
                }


                switch (alt28) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:903:4: (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:903:4: (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:903:6: otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';'
            	    {
            	    otherlv_24=(Token)match(input,37,FOLLOW_37_in_ruleExport1951); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_24, grammarAccess.getExportAccess().getFieldKeyword_8_0_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:907:1: ( (lv_attributes_25_0= ruleAttribute ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:908:1: (lv_attributes_25_0= ruleAttribute )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:908:1: (lv_attributes_25_0= ruleAttribute )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:909:3: lv_attributes_25_0= ruleAttribute
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleExport1972);
            	    lv_attributes_25_0=ruleAttribute();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"attributes",
            	              		lv_attributes_25_0, 
            	              		"Attribute");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:925:2: (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )*
            	    loop26:
            	    do {
            	        int alt26=2;
            	        int LA26_0 = input.LA(1);

            	        if ( (LA26_0==23) ) {
            	            alt26=1;
            	        }


            	        switch (alt26) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:925:4: otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) )
            	    	    {
            	    	    otherlv_26=(Token)match(input,23,FOLLOW_23_in_ruleExport1985); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_26, grammarAccess.getExportAccess().getCommaKeyword_8_0_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:929:1: ( (lv_attributes_27_0= ruleAttribute ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:930:1: (lv_attributes_27_0= ruleAttribute )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:930:1: (lv_attributes_27_0= ruleAttribute )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:931:3: lv_attributes_27_0= ruleAttribute
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleAttribute_in_ruleExport2006);
            	    	    lv_attributes_27_0=ruleAttribute();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      	        if (current==null) {
            	    	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	    	      	        }
            	    	             		add(
            	    	             			current, 
            	    	             			"attributes",
            	    	              		lv_attributes_27_0, 
            	    	              		"Attribute");
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

            	    otherlv_28=(Token)match(input,24,FOLLOW_24_in_ruleExport2020); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_28, grammarAccess.getExportAccess().getSemicolonKeyword_8_0_3());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:952:6: (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:952:6: (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:952:8: otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';'
            	    {
            	    otherlv_29=(Token)match(input,38,FOLLOW_38_in_ruleExport2040); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_29, grammarAccess.getExportAccess().getDataKeyword_8_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:956:1: ( (lv_userData_30_0= ruleUserData ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:957:1: (lv_userData_30_0= ruleUserData )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:957:1: (lv_userData_30_0= ruleUserData )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:958:3: lv_userData_30_0= ruleUserData
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUserData_in_ruleExport2061);
            	    lv_userData_30_0=ruleUserData();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"userData",
            	              		lv_userData_30_0, 
            	              		"UserData");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:974:2: (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )*
            	    loop27:
            	    do {
            	        int alt27=2;
            	        int LA27_0 = input.LA(1);

            	        if ( (LA27_0==23) ) {
            	            alt27=1;
            	        }


            	        switch (alt27) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:974:4: otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) )
            	    	    {
            	    	    otherlv_31=(Token)match(input,23,FOLLOW_23_in_ruleExport2074); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_31, grammarAccess.getExportAccess().getCommaKeyword_8_1_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:978:1: ( (lv_userData_32_0= ruleUserData ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:979:1: (lv_userData_32_0= ruleUserData )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:979:1: (lv_userData_32_0= ruleUserData )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:980:3: lv_userData_32_0= ruleUserData
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleUserData_in_ruleExport2095);
            	    	    lv_userData_32_0=ruleUserData();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      	        if (current==null) {
            	    	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	    	      	        }
            	    	             		add(
            	    	             			current, 
            	    	             			"userData",
            	    	              		lv_userData_32_0, 
            	    	              		"UserData");
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

            	    otherlv_33=(Token)match(input,24,FOLLOW_24_in_ruleExport2109); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_33, grammarAccess.getExportAccess().getSemicolonKeyword_8_1_3());
            	          
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            otherlv_34=(Token)match(input,17,FOLLOW_17_in_ruleExport2124); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_34, grammarAccess.getExportAccess().getRightCurlyBracketKeyword_9());
                  
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
    // $ANTLR end "ruleExport"


    // $ANTLR start "entryRuleUserData"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1012:1: entryRuleUserData returns [EObject current=null] : iv_ruleUserData= ruleUserData EOF ;
    public final EObject entryRuleUserData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUserData = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1013:2: (iv_ruleUserData= ruleUserData EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1014:2: iv_ruleUserData= ruleUserData EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUserDataRule()); 
            }
            pushFollow(FOLLOW_ruleUserData_in_entryRuleUserData2160);
            iv_ruleUserData=ruleUserData();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUserData; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUserData2170); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUserData"


    // $ANTLR start "ruleUserData"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1021:1: ruleUserData returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) ;
    public final EObject ruleUserData() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1024:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1025:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1025:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1025:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1025:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1026:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1026:1: (lv_name_0_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1027:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleUserData2212); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(lv_name_0_0, grammarAccess.getUserDataAccess().getNameIDTerminalRuleCall_0_0()); 
              		
            }
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElement(grammarAccess.getUserDataRule());
              	        }
                     		setWithLastConsumed(
                     			current, 
                     			"name",
                      		lv_name_0_0, 
                      		"ID");
              	    
            }

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleUserData2229); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getUserDataAccess().getEqualsSignKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1047:1: ( (lv_expr_2_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1048:1: (lv_expr_2_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1048:1: (lv_expr_2_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1049:3: lv_expr_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUserDataAccess().getExprExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleUserData2250);
            lv_expr_2_0=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getUserDataRule());
              	        }
                     		set(
                     			current, 
                     			"expr",
                      		lv_expr_2_0, 
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
    // $ANTLR end "ruleUserData"


    // $ANTLR start "entryRuleAttribute"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1073:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1074:2: (iv_ruleAttribute= ruleAttribute EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1075:2: iv_ruleAttribute= ruleAttribute EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAttributeRule()); 
            }
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute2286);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAttribute; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute2296); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1082:1: ruleAttribute returns [EObject current=null] : ( (otherlv_0= RULE_ID ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1085:28: ( ( (otherlv_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1086:1: ( (otherlv_0= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1086:1: ( (otherlv_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1087:1: (otherlv_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1087:1: (otherlv_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1088:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getAttributeRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2340); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_0, grammarAccess.getAttributeAccess().getAttributeEAttributeCrossReference_0()); 
              	
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
    // $ANTLR end "ruleAttribute"


    // $ANTLR start "entryRuleQualifiedID"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1107:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1108:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1109:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID2376);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID2387); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1116:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1119:28: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1120:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1120:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1120:6: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID2427); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1127:1: (kw= '::' this_ID_2= RULE_ID )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==39) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1128:2: kw= '::' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,39,FOLLOW_39_in_ruleQualifiedID2446); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_1_0()); 
            	          
            	    }
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID2461); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ID_2);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop29;
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


    // $ANTLR start "entryRuleExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1148:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1149:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1150:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression2508);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2518); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1157:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1160:28: ( (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1161:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1161:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            int alt30=3;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1162:5: this_LetExpression_0= ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLetExpression_in_ruleExpression2565);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1171:6: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1171:6: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1171:7: ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCastedExpression_in_ruleExpression2598);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1182:5: this_ChainExpression_2= ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleChainExpression_in_ruleExpression2626);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1200:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1201:2: (iv_ruleLetExpression= ruleLetExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1202:2: iv_ruleLetExpression= ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLetExpression_in_entryRuleLetExpression2663);
            iv_ruleLetExpression=ruleLetExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetExpression2673); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1209:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1212:28: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1213:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1213:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1213:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,40,FOLLOW_40_in_ruleLetExpression2710); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1217:1: ( (lv_identifier_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1218:1: (lv_identifier_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1218:1: (lv_identifier_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1219:3: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLetExpression2731);
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

            otherlv_2=(Token)match(input,22,FOLLOW_22_in_ruleLetExpression2743); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1239:1: ( (lv_varExpr_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1240:1: (lv_varExpr_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1240:1: (lv_varExpr_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1241:3: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression2764);
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

            otherlv_4=(Token)match(input,41,FOLLOW_41_in_ruleLetExpression2776); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1261:1: ( (lv_target_5_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1262:1: (lv_target_5_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1262:1: (lv_target_5_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1263:3: lv_target_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression2797);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1287:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1288:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1289:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression2833);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCastedExpression2843); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1296:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1299:28: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1300:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1300:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1300:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_ruleCastedExpression2880); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1304:1: ( (lv_type_1_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1305:1: (lv_type_1_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1305:1: (lv_type_1_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1306:3: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleCastedExpression2901);
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

            otherlv_2=(Token)match(input,29,FOLLOW_29_in_ruleCastedExpression2913); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1326:1: ( (lv_target_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1327:1: (lv_target_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1327:1: (lv_target_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1328:3: lv_target_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCastedExpression2934);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1352:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1353:2: (iv_ruleChainExpression= ruleChainExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1354:2: iv_ruleChainExpression= ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainExpression_in_entryRuleChainExpression2970);
            iv_ruleChainExpression=ruleChainExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainExpression2980); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1361:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1364:28: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1365:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1365:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1366:5: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression3027);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ChainedExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1374:1: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==42) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1374:2: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1374:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1375:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,42,FOLLOW_42_in_ruleChainExpression3048); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1384:1: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1385:1: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1385:1: (lv_next_3_0= ruleChainedExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1386:3: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression3069);
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
            	    break loop31;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1410:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1411:2: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1412:2: iv_ruleChainedExpression= ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression3107);
            iv_ruleChainedExpression=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainedExpression3117); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1419:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1422:28: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1423:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1423:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            int alt32=3;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt32=1;
                }
                break;
            case RULE_ID:
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case 16:
            case 28:
            case 59:
            case 62:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
                {
                alt32=2;
                }
                break;
            case 47:
                {
                alt32=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }

            switch (alt32) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1424:5: this_IfExpressionKw_0= ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression3164);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1434:5: this_IfExpressionTri_1= ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression3191);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1444:5: this_SwitchExpression_2= ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSwitchExpression_in_ruleChainedExpression3218);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1460:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1461:2: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1462:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri3253);
            iv_ruleIfExpressionTri=ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionTri; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionTri3263); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1469:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1472:28: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1473:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1473:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1474:5: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleIfExpressionTri3310);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1482:1: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==43) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1482:2: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1482:2: ()
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1483:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_2=(Token)match(input,43,FOLLOW_43_in_ruleIfExpressionTri3331); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1492:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1493:1: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1493:1: (lv_thenPart_3_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1494:3: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3352);
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

                    otherlv_4=(Token)match(input,41,FOLLOW_41_in_ruleIfExpressionTri3364); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1514:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1515:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1515:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1516:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3385);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1540:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1541:2: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1542:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw3423);
            iv_ruleIfExpressionKw=ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionKw; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionKw3433); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1549:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1552:28: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1553:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1553:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1553:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            {
            otherlv_0=(Token)match(input,44,FOLLOW_44_in_ruleIfExpressionKw3470); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1557:1: ( (lv_condition_1_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1558:1: (lv_condition_1_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1558:1: (lv_condition_1_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1559:3: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3491);
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

            otherlv_2=(Token)match(input,45,FOLLOW_45_in_ruleIfExpressionKw3503); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1579:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1580:1: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1580:1: (lv_thenPart_3_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1581:3: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3524);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1597:2: ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==46) ) {
                int LA34_1 = input.LA(2);

                if ( (synpred2_InternalExport()) ) {
                    alt34=1;
                }
            }
            switch (alt34) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1597:3: ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1598:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1598:6: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,46,FOLLOW_46_in_ruleIfExpressionKw3545); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1602:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1603:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1603:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1604:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3566);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1628:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1629:2: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1630:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression3605);
            iv_ruleSwitchExpression=ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSwitchExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchExpression3615); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1637:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1640:28: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1641:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1641:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1641:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,47,FOLLOW_47_in_ruleSwitchExpression3652); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1645:1: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==28) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1645:3: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleSwitchExpression3665); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1649:1: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1650:1: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1650:1: (lv_switchExpr_2_0= ruleOrExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1651:3: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression3686);
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

                    otherlv_3=(Token)match(input,29,FOLLOW_29_in_ruleSwitchExpression3698); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,16,FOLLOW_16_in_ruleSwitchExpression3712); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1675:1: ( (lv_case_5_0= ruleCase ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==49) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1676:1: (lv_case_5_0= ruleCase )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1676:1: (lv_case_5_0= ruleCase )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1677:3: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleCase_in_ruleSwitchExpression3733);
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
            	    break loop36;
                }
            } while (true);

            otherlv_6=(Token)match(input,48,FOLLOW_48_in_ruleSwitchExpression3746); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
                  
            }
            otherlv_7=(Token)match(input,41,FOLLOW_41_in_ruleSwitchExpression3758); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1701:1: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1702:1: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1702:1: (lv_defaultExpr_8_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1703:3: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression3779);
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

            otherlv_9=(Token)match(input,17,FOLLOW_17_in_ruleSwitchExpression3791); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1731:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1732:2: (iv_ruleCase= ruleCase EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1733:2: iv_ruleCase= ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_ruleCase_in_entryRuleCase3827);
            iv_ruleCase=ruleCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCase; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCase3837); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1740:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1743:28: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1744:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1744:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1744:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,49,FOLLOW_49_in_ruleCase3874); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1748:1: ( (lv_condition_1_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1749:1: (lv_condition_1_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1749:1: (lv_condition_1_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1750:3: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase3895);
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

            otherlv_2=(Token)match(input,41,FOLLOW_41_in_ruleCase3907); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1770:1: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1771:1: (lv_thenPar_3_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1771:1: (lv_thenPar_3_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1772:3: lv_thenPar_3_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase3928);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1796:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1797:2: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1798:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression3964);
            iv_ruleOrExpression=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression3974); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1805:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1808:28: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1809:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1809:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1810:5: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression4021);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1818:1: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==50) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1818:2: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1818:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1819:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1824:2: ( (lv_operator_2_0= '||' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1825:1: (lv_operator_2_0= '||' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1825:1: (lv_operator_2_0= '||' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1826:3: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,50,FOLLOW_50_in_ruleOrExpression4048); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1839:2: ( (lv_right_3_0= ruleAndExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1840:1: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1840:1: (lv_right_3_0= ruleAndExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1841:3: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression4082);
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
    // $ANTLR end "ruleOrExpression"


    // $ANTLR start "entryRuleAndExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1865:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1866:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1867:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression4120);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression4130); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1874:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1877:28: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1878:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1878:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1879:5: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression4177);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1887:1: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==51) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1887:2: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1887:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1888:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1893:2: ( (lv_operator_2_0= '&&' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1894:1: (lv_operator_2_0= '&&' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1894:1: (lv_operator_2_0= '&&' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1895:3: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,51,FOLLOW_51_in_ruleAndExpression4204); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1908:2: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1909:1: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1909:1: (lv_right_3_0= ruleImpliesExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1910:3: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression4238);
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
    // $ANTLR end "ruleAndExpression"


    // $ANTLR start "entryRuleImpliesExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1934:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1935:2: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1936:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression4276);
            iv_ruleImpliesExpression=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpression4286); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1943:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1946:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1947:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1947:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1948:5: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4333);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1956:1: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==52) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1956:2: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1956:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1957:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1962:2: ( (lv_operator_2_0= 'implies' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1963:1: (lv_operator_2_0= 'implies' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1963:1: (lv_operator_2_0= 'implies' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1964:3: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,52,FOLLOW_52_in_ruleImpliesExpression4360); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1977:2: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1978:1: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1978:1: (lv_right_3_0= ruleRelationalExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1979:3: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4394);
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
            	    break loop39;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2003:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2004:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2005:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression4432);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression4442); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2012:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2015:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2016:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2016:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2017:5: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4489);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2025:1: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( ((LA41_0>=53 && LA41_0<=58)) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2025:2: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2025:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2026:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2031:2: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2032:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2032:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2033:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2033:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    int alt40=6;
            	    switch ( input.LA(1) ) {
            	    case 53:
            	        {
            	        alt40=1;
            	        }
            	        break;
            	    case 54:
            	        {
            	        alt40=2;
            	        }
            	        break;
            	    case 55:
            	        {
            	        alt40=3;
            	        }
            	        break;
            	    case 56:
            	        {
            	        alt40=4;
            	        }
            	        break;
            	    case 57:
            	        {
            	        alt40=5;
            	        }
            	        break;
            	    case 58:
            	        {
            	        alt40=6;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 40, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt40) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2034:3: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,53,FOLLOW_53_in_ruleRelationalExpression4518); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2046:8: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,54,FOLLOW_54_in_ruleRelationalExpression4547); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2058:8: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,55,FOLLOW_55_in_ruleRelationalExpression4576); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2070:8: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,56,FOLLOW_56_in_ruleRelationalExpression4605); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2082:8: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,57,FOLLOW_57_in_ruleRelationalExpression4634); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2094:8: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,58,FOLLOW_58_in_ruleRelationalExpression4663); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2109:2: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2110:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2110:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2111:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4700);
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
            	    break loop41;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2135:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2136:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2137:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression4738);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression4748); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2144:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2147:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2148:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2148:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2149:5: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4795);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2157:1: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==25||LA43_0==59) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2157:2: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2157:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2158:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2163:2: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2164:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2164:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2165:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2165:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    int alt42=2;
            	    int LA42_0 = input.LA(1);

            	    if ( (LA42_0==25) ) {
            	        alt42=1;
            	    }
            	    else if ( (LA42_0==59) ) {
            	        alt42=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 42, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt42) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2166:3: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,25,FOLLOW_25_in_ruleAdditiveExpression4824); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2178:8: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,59,FOLLOW_59_in_ruleAdditiveExpression4853); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2193:2: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2194:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2194:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2195:3: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4890);
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
    // $ANTLR end "ruleAdditiveExpression"


    // $ANTLR start "entryRuleMultiplicativeExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2219:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2220:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2221:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4928);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression4938); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2228:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2231:28: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2232:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2232:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2233:5: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4985);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryOrInfixExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2241:1: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( ((LA45_0>=60 && LA45_0<=61)) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2241:2: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2241:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2242:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2247:2: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2248:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2248:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2249:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2249:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    int alt44=2;
            	    int LA44_0 = input.LA(1);

            	    if ( (LA44_0==60) ) {
            	        alt44=1;
            	    }
            	    else if ( (LA44_0==61) ) {
            	        alt44=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 44, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt44) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2250:3: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,60,FOLLOW_60_in_ruleMultiplicativeExpression5014); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2262:8: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,61,FOLLOW_61_in_ruleMultiplicativeExpression5043); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2277:2: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2278:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2278:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2279:3: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression5080);
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
    // $ANTLR end "ruleMultiplicativeExpression"


    // $ANTLR start "entryRuleUnaryOrInfixExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2303:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2304:2: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2305:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression5118);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOrInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression5128); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2312:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2315:28: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2316:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2316:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==59||LA46_0==62) ) {
                alt46=1;
            }
            else if ( ((LA46_0>=RULE_ID && LA46_0<=RULE_REAL)||LA46_0==16||LA46_0==28||(LA46_0>=64 && LA46_0<=72)||(LA46_0>=74 && LA46_0<=81)) ) {
                alt46=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2317:5: this_UnaryExpression_0= ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression5175);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2327:5: this_InfixExpression_1= ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression5202);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2343:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2344:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2345:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression5237);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression5247); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2352:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2355:28: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2356:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2356:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2356:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2356:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2357:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2357:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2358:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2358:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==62) ) {
                alt47=1;
            }
            else if ( (LA47_0==59) ) {
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2359:3: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,62,FOLLOW_62_in_ruleUnaryExpression5292); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2371:8: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,59,FOLLOW_59_in_ruleUnaryExpression5321); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2386:2: ( (lv_params_1_0= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2387:1: (lv_params_1_0= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2387:1: (lv_params_1_0= ruleInfixExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2388:3: lv_params_1_0= ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression5358);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2412:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2413:2: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2414:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression5394);
            iv_ruleInfixExpression=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression5404); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2421:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2424:28: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2425:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2425:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2426:5: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression5451);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2434:1: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop52:
            do {
                int alt52=5;
                int LA52_0 = input.LA(1);

                if ( (LA52_0==63) ) {
                    switch ( input.LA(2) ) {
                    case RULE_ID:
                        {
                        int LA52_3 = input.LA(3);

                        if ( (LA52_3==28) ) {
                            alt52=1;
                        }
                        else if ( (LA52_3==EOF||(LA52_3>=16 && LA52_3<=17)||(LA52_3>=20 && LA52_3<=21)||(LA52_3>=23 && LA52_3<=25)||LA52_3==29||LA52_3==39||(LA52_3>=41 && LA52_3<=43)||(LA52_3>=45 && LA52_3<=46)||(LA52_3>=48 && LA52_3<=61)||LA52_3==63) ) {
                            alt52=2;
                        }


                        }
                        break;
                    case 79:
                    case 80:
                    case 81:
                        {
                        alt52=2;
                        }
                        break;
                    case 64:
                        {
                        alt52=3;
                        }
                        break;
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                    case 71:
                    case 72:
                        {
                        alt52=4;
                        }
                        break;

                    }

                }


                switch (alt52) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2434:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2434:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2434:3: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2434:3: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2435:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,63,FOLLOW_63_in_ruleInfixExpression5473); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2444:1: ( (lv_name_3_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2445:1: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2445:1: (lv_name_3_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2446:3: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression5494);
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

            	    otherlv_4=(Token)match(input,28,FOLLOW_28_in_ruleInfixExpression5506); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2466:1: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt49=2;
            	    int LA49_0 = input.LA(1);

            	    if ( ((LA49_0>=RULE_ID && LA49_0<=RULE_REAL)||LA49_0==16||LA49_0==28||LA49_0==40||LA49_0==44||LA49_0==47||LA49_0==59||LA49_0==62||(LA49_0>=64 && LA49_0<=72)||(LA49_0>=74 && LA49_0<=81)) ) {
            	        alt49=1;
            	    }
            	    switch (alt49) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2466:2: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2466:2: ( (lv_params_5_0= ruleExpression ) )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2467:1: (lv_params_5_0= ruleExpression )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2467:1: (lv_params_5_0= ruleExpression )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2468:3: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression5528);
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

            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2484:2: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop48:
            	            do {
            	                int alt48=2;
            	                int LA48_0 = input.LA(1);

            	                if ( (LA48_0==23) ) {
            	                    alt48=1;
            	                }


            	                switch (alt48) {
            	            	case 1 :
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2484:4: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,23,FOLLOW_23_in_ruleInfixExpression5541); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	          
            	            	    }
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2488:1: ( (lv_params_7_0= ruleExpression ) )
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2489:1: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2489:1: (lv_params_7_0= ruleExpression )
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2490:3: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression5562);
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
            	            	    break loop48;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,29,FOLLOW_29_in_ruleInfixExpression5578); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2511:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2511:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2511:7: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2511:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2512:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_10=(Token)match(input,63,FOLLOW_63_in_ruleInfixExpression5607); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2521:1: ( (lv_type_11_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2522:1: (lv_type_11_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2522:1: (lv_type_11_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2523:3: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression5628);
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
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2540:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2540:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2540:7: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2540:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2541:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_13=(Token)match(input,63,FOLLOW_63_in_ruleInfixExpression5657); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2550:1: ( (lv_name_14_0= 'typeSelect' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2551:1: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2551:1: (lv_name_14_0= 'typeSelect' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2552:3: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,64,FOLLOW_64_in_ruleInfixExpression5675); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,28,FOLLOW_28_in_ruleInfixExpression5700); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2569:1: ( (lv_type_16_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2570:1: (lv_type_16_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2570:1: (lv_type_16_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2571:3: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression5721);
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

            	    otherlv_17=(Token)match(input,29,FOLLOW_29_in_ruleInfixExpression5733); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2592:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2592:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2592:7: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2592:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2593:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_19=(Token)match(input,63,FOLLOW_63_in_ruleInfixExpression5762); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2602:1: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2603:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2603:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2604:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2604:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    int alt50=8;
            	    switch ( input.LA(1) ) {
            	    case 65:
            	        {
            	        alt50=1;
            	        }
            	        break;
            	    case 66:
            	        {
            	        alt50=2;
            	        }
            	        break;
            	    case 67:
            	        {
            	        alt50=3;
            	        }
            	        break;
            	    case 68:
            	        {
            	        alt50=4;
            	        }
            	        break;
            	    case 69:
            	        {
            	        alt50=5;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt50=6;
            	        }
            	        break;
            	    case 71:
            	        {
            	        alt50=7;
            	        }
            	        break;
            	    case 72:
            	        {
            	        alt50=8;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 50, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt50) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2605:3: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,65,FOLLOW_65_in_ruleInfixExpression5782); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2617:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,66,FOLLOW_66_in_ruleInfixExpression5811); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2629:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,67,FOLLOW_67_in_ruleInfixExpression5840); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2641:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,68,FOLLOW_68_in_ruleInfixExpression5869); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2653:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,69,FOLLOW_69_in_ruleInfixExpression5898); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2665:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,70,FOLLOW_70_in_ruleInfixExpression5927); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2677:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,71,FOLLOW_71_in_ruleInfixExpression5956); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2689:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,72,FOLLOW_72_in_ruleInfixExpression5985); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,28,FOLLOW_28_in_ruleInfixExpression6013); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2708:1: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
            	    int alt51=2;
            	    int LA51_0 = input.LA(1);

            	    if ( (LA51_0==RULE_ID) ) {
            	        int LA51_1 = input.LA(2);

            	        if ( (LA51_1==73) ) {
            	            alt51=1;
            	        }
            	    }
            	    switch (alt51) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2708:2: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2708:2: ( (lv_var_22_0= ruleIdentifier ) )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2709:1: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2709:1: (lv_var_22_0= ruleIdentifier )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2710:3: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression6035);
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

            	            otherlv_23=(Token)match(input,73,FOLLOW_73_in_ruleInfixExpression6047); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2730:3: ( (lv_exp_24_0= ruleExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2731:1: (lv_exp_24_0= ruleExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2731:1: (lv_exp_24_0= ruleExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2732:3: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression6070);
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

            	    otherlv_25=(Token)match(input,29,FOLLOW_29_in_ruleInfixExpression6082); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6());
            	          
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
    // $ANTLR end "ruleInfixExpression"


    // $ANTLR start "entryRulePrimaryExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2760:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2761:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2762:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression6121);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression6131); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2769:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2772:28: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2773:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2773:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            int alt53=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case 74:
            case 75:
            case 76:
                {
                alt53=1;
                }
                break;
            case RULE_ID:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 79:
            case 80:
            case 81:
                {
                alt53=2;
                }
                break;
            case 16:
                {
                alt53=3;
                }
                break;
            case 78:
                {
                alt53=4;
                }
                break;
            case 77:
                {
                alt53=5;
                }
                break;
            case 28:
                {
                alt53=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }

            switch (alt53) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2774:5: this_Literal_0= ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression6178);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2784:5: this_FeatureCall_1= ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFeatureCall_in_rulePrimaryExpression6205);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2794:5: this_ListLiteral_2= ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListLiteral_in_rulePrimaryExpression6232);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2804:5: this_ConstructorCallExpression_3= ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression6259);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2814:5: this_GlobalVarExpression_4= ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression6286);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2824:5: this_ParanthesizedExpression_5= ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression6313);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2840:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2841:2: (iv_ruleLiteral= ruleLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2842:2: iv_ruleLiteral= ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral6348);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral6358); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2849:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2852:28: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2853:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2853:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            int alt54=5;
            switch ( input.LA(1) ) {
            case 74:
            case 75:
                {
                alt54=1;
                }
                break;
            case RULE_INT:
                {
                alt54=2;
                }
                break;
            case 76:
                {
                alt54=3;
                }
                break;
            case RULE_REAL:
                {
                alt54=4;
                }
                break;
            case RULE_STRING:
                {
                alt54=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }

            switch (alt54) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2854:5: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral6405);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2864:5: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral6432);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2874:5: this_NullLiteral_2= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral6459);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2884:5: this_RealLiteral_3= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealLiteral_in_ruleLiteral6486);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2894:5: this_StringLiteral_4= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral6513);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2910:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2911:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2912:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral6548);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral6558); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2919:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2922:28: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2923:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2923:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2924:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2924:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2925:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2925:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==74) ) {
                alt55=1;
            }
            else if ( (LA55_0==75) ) {
                alt55=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2926:3: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,74,FOLLOW_74_in_ruleBooleanLiteral6602); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2938:8: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,75,FOLLOW_75_in_ruleBooleanLiteral6631); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2961:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2962:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2963:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral6682);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral6692); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2970:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2973:28: ( ( (lv_val_0_0= RULE_INT ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2974:1: ( (lv_val_0_0= RULE_INT ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2974:1: ( (lv_val_0_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2975:1: (lv_val_0_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2975:1: (lv_val_0_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2976:3: lv_val_0_0= RULE_INT
            {
            lv_val_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral6733); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3000:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3001:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3002:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral6773);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral6783); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3009:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3012:28: ( ( (lv_val_0_0= 'null' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3013:1: ( (lv_val_0_0= 'null' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3013:1: ( (lv_val_0_0= 'null' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3014:1: (lv_val_0_0= 'null' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3014:1: (lv_val_0_0= 'null' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3015:3: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,76,FOLLOW_76_in_ruleNullLiteral6825); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3036:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3037:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3038:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral6873);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealLiteral6883); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3045:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3048:28: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3049:1: ( (lv_val_0_0= RULE_REAL ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3049:1: ( (lv_val_0_0= RULE_REAL ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3050:1: (lv_val_0_0= RULE_REAL )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3050:1: (lv_val_0_0= RULE_REAL )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3051:3: lv_val_0_0= RULE_REAL
            {
            lv_val_0_0=(Token)match(input,RULE_REAL,FOLLOW_RULE_REAL_in_ruleRealLiteral6924); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3075:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3076:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3077:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral6964);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral6974); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3084:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3087:28: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3088:1: ( (lv_val_0_0= RULE_STRING ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3088:1: ( (lv_val_0_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3089:1: (lv_val_0_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3089:1: (lv_val_0_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3090:3: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral7015); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3114:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3115:2: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3116:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression7055);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParanthesizedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression7065); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3123:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3126:28: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3127:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3127:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3127:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_ruleParanthesizedExpression7102); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression7124);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,29,FOLLOW_29_in_ruleParanthesizedExpression7135); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3152:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3153:2: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3154:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression7171);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalVarExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression7181); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3161:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3164:28: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3165:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3165:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3165:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,77,FOLLOW_77_in_ruleGlobalVarExpression7218); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3169:1: ( (lv_name_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3170:1: (lv_name_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3170:1: (lv_name_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3171:3: lv_name_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression7239);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3195:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3196:2: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3197:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall7275);
            iv_ruleFeatureCall=ruleFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureCall7285); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3204:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3207:28: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3208:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3208:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            int alt56=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA56_1 = input.LA(2);

                if ( (LA56_1==EOF||(LA56_1>=16 && LA56_1<=17)||(LA56_1>=20 && LA56_1<=21)||(LA56_1>=23 && LA56_1<=25)||LA56_1==29||LA56_1==39||(LA56_1>=41 && LA56_1<=43)||(LA56_1>=45 && LA56_1<=46)||(LA56_1>=48 && LA56_1<=61)||LA56_1==63) ) {
                    alt56=2;
                }
                else if ( (LA56_1==28) ) {
                    alt56=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 1, input);

                    throw nvae;
                }
                }
                break;
            case 79:
            case 80:
            case 81:
                {
                alt56=2;
                }
                break;
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
                {
                alt56=3;
                }
                break;
            case 64:
                {
                alt56=4;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3209:5: this_OperationCall_0= ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleOperationCall_in_ruleFeatureCall7332);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3218:6: ( (lv_type_1_0= ruleType ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3218:6: ( (lv_type_1_0= ruleType ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3219:1: (lv_type_1_0= ruleType )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3219:1: (lv_type_1_0= ruleType )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3220:3: lv_type_1_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleFeatureCall7358);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3238:5: this_CollectionExpression_2= ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionExpression_in_ruleFeatureCall7386);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3248:5: this_TypeSelectExpression_3= ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall7413);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3264:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3265:2: (iv_ruleOperationCall= ruleOperationCall EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3266:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_ruleOperationCall_in_entryRuleOperationCall7448);
            iv_ruleOperationCall=ruleOperationCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCall7458); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3273:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3276:28: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3277:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3277:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3277:2: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3277:2: ( (lv_name_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3278:1: (lv_name_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3278:1: (lv_name_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3279:3: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleOperationCall7504);
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

            otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleOperationCall7516); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3299:1: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( ((LA58_0>=RULE_ID && LA58_0<=RULE_REAL)||LA58_0==16||LA58_0==28||LA58_0==40||LA58_0==44||LA58_0==47||LA58_0==59||LA58_0==62||(LA58_0>=64 && LA58_0<=72)||(LA58_0>=74 && LA58_0<=81)) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3299:2: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3299:2: ( (lv_params_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3300:1: (lv_params_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3300:1: (lv_params_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3301:3: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall7538);
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3317:2: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==23) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3317:4: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,23,FOLLOW_23_in_ruleOperationCall7551); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3321:1: ( (lv_params_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3322:1: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3322:1: (lv_params_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3323:3: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall7572);
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
                    	    break loop57;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,29,FOLLOW_29_in_ruleOperationCall7588); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3351:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3352:2: (iv_ruleListLiteral= ruleListLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3353:2: iv_ruleListLiteral= ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleListLiteral_in_entryRuleListLiteral7624);
            iv_ruleListLiteral=ruleListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListLiteral7634); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3360:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3363:28: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3364:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3364:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3364:2: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3364:2: ()
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3365:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListLiteralAccess().getListLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,16,FOLLOW_16_in_ruleListLiteral7680); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3374:1: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( ((LA60_0>=RULE_ID && LA60_0<=RULE_REAL)||LA60_0==16||LA60_0==28||LA60_0==40||LA60_0==44||LA60_0==47||LA60_0==59||LA60_0==62||(LA60_0>=64 && LA60_0<=72)||(LA60_0>=74 && LA60_0<=81)) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3374:2: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3374:2: ( (lv_elements_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3375:1: (lv_elements_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3375:1: (lv_elements_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3376:3: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral7702);
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3392:2: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==23) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3392:4: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,23,FOLLOW_23_in_ruleListLiteral7715); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3396:1: ( (lv_elements_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3397:1: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3397:1: (lv_elements_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3398:3: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral7736);
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
                    	    break loop59;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,17,FOLLOW_17_in_ruleListLiteral7752); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3426:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3427:2: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3428:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression7788);
            iv_ruleConstructorCallExpression=ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorCallExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstructorCallExpression7798); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3435:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3438:28: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3439:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3439:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3439:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,78,FOLLOW_78_in_ruleConstructorCallExpression7835); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3443:1: ( (lv_type_1_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3444:1: (lv_type_1_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3444:1: (lv_type_1_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3445:3: lv_type_1_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression7856);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3469:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3470:2: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3471:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression7892);
            iv_ruleTypeSelectExpression=ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeSelectExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTypeSelectExpression7902); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3478:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3481:28: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3482:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3482:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3482:2: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3482:2: ( (lv_name_0_0= 'typeSelect' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3483:1: (lv_name_0_0= 'typeSelect' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3483:1: (lv_name_0_0= 'typeSelect' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3484:3: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,64,FOLLOW_64_in_ruleTypeSelectExpression7945); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleTypeSelectExpression7970); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3501:1: ( (lv_type_2_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3502:1: (lv_type_2_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3502:1: (lv_type_2_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3503:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleTypeSelectExpression7991);
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

            otherlv_3=(Token)match(input,29,FOLLOW_29_in_ruleTypeSelectExpression8003); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3531:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3532:2: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3533:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression8039);
            iv_ruleCollectionExpression=ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionExpression8049); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3540:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3543:28: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3544:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3544:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3544:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3544:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3545:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3545:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3546:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3546:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            int alt61=8;
            switch ( input.LA(1) ) {
            case 65:
                {
                alt61=1;
                }
                break;
            case 66:
                {
                alt61=2;
                }
                break;
            case 67:
                {
                alt61=3;
                }
                break;
            case 68:
                {
                alt61=4;
                }
                break;
            case 69:
                {
                alt61=5;
                }
                break;
            case 70:
                {
                alt61=6;
                }
                break;
            case 71:
                {
                alt61=7;
                }
                break;
            case 72:
                {
                alt61=8;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3547:3: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,65,FOLLOW_65_in_ruleCollectionExpression8094); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3559:8: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,66,FOLLOW_66_in_ruleCollectionExpression8123); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3571:8: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,67,FOLLOW_67_in_ruleCollectionExpression8152); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3583:8: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,68,FOLLOW_68_in_ruleCollectionExpression8181); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3595:8: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,69,FOLLOW_69_in_ruleCollectionExpression8210); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3607:8: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,70,FOLLOW_70_in_ruleCollectionExpression8239); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3619:8: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,71,FOLLOW_71_in_ruleCollectionExpression8268); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3631:8: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,72,FOLLOW_72_in_ruleCollectionExpression8297); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,28,FOLLOW_28_in_ruleCollectionExpression8325); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3650:1: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==RULE_ID) ) {
                int LA62_1 = input.LA(2);

                if ( (LA62_1==73) ) {
                    alt62=1;
                }
            }
            switch (alt62) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3650:2: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3650:2: ( (lv_var_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3651:1: (lv_var_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3651:1: (lv_var_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3652:3: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleCollectionExpression8347);
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

                    otherlv_3=(Token)match(input,73,FOLLOW_73_in_ruleCollectionExpression8359); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3672:3: ( (lv_exp_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3673:1: (lv_exp_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3673:1: (lv_exp_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3674:3: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCollectionExpression8382);
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

            otherlv_5=(Token)match(input,29,FOLLOW_29_in_ruleCollectionExpression8394); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3702:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3703:2: (iv_ruleType= ruleType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3704:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType8430);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType8440); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3711:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3714:28: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3715:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3715:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( ((LA63_0>=79 && LA63_0<=81)) ) {
                alt63=1;
            }
            else if ( (LA63_0==RULE_ID) ) {
                alt63=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3716:5: this_CollectionType_0= ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionType_in_ruleType8487);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3726:5: this_SimpleType_1= ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleType_in_ruleType8514);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3742:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3743:2: (iv_ruleCollectionType= ruleCollectionType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3744:2: iv_ruleCollectionType= ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionType_in_entryRuleCollectionType8549);
            iv_ruleCollectionType=ruleCollectionType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionType8559); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3751:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3754:28: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3755:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3755:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3755:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3755:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3756:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3756:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3757:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3757:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            int alt64=3;
            switch ( input.LA(1) ) {
            case 79:
                {
                alt64=1;
                }
                break;
            case 80:
                {
                alt64=2;
                }
                break;
            case 81:
                {
                alt64=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3758:3: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,79,FOLLOW_79_in_ruleCollectionType8604); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3770:8: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,80,FOLLOW_80_in_ruleCollectionType8633); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3782:8: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,81,FOLLOW_81_in_ruleCollectionType8662); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleCollectionType8690); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3801:1: ( (lv_id1_2_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3802:1: (lv_id1_2_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3802:1: (lv_id1_2_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3803:3: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleCollectionType8711);
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

            otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleCollectionType8723); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3831:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3832:2: (iv_ruleSimpleType= ruleSimpleType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3833:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleType_in_entryRuleSimpleType8759);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleType8769); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3840:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3843:28: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3844:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3844:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3844:2: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3844:2: ( (lv_id_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3845:1: (lv_id_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3845:1: (lv_id_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3846:3: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType8815);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3862:2: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==39) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3862:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,39,FOLLOW_39_in_ruleSimpleType8828); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3866:1: ( (lv_id_2_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3867:1: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3867:1: (lv_id_2_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3868:3: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType8849);
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
            	    break loop65;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3892:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3893:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3894:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier8888);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier8899); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3901:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3904:28: (this_ID_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3905:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIdentifier8938); if (state.failed) return current;
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

    // $ANTLR start synpred1_InternalExport
    public final void synpred1_InternalExport_fragment() throws RecognitionException {   
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1171:7: ( ruleCastedExpression )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1171:9: ruleCastedExpression
        {
        pushFollow(FOLLOW_ruleCastedExpression_in_synpred1_InternalExport2582);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalExport

    // $ANTLR start synpred2_InternalExport
    public final void synpred2_InternalExport_fragment() throws RecognitionException {   
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1597:3: ( 'else' )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1597:5: 'else'
        {
        match(input,46,FOLLOW_46_in_synpred2_InternalExport3536); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_InternalExport

    // Delegated rules

    public final boolean synpred1_InternalExport() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalExport_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_InternalExport() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalExport_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA30 dfa30 = new DFA30(this);
    static final String DFA30_eotS =
        "\36\uffff";
    static final String DFA30_eofS =
        "\36\uffff";
    static final String DFA30_minS =
        "\1\4\1\uffff\1\0\33\uffff";
    static final String DFA30_maxS =
        "\1\121\1\uffff\1\0\33\uffff";
    static final String DFA30_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String DFA30_specialS =
        "\2\uffff\1\0\33\uffff}>";
    static final String[] DFA30_transitionS = {
            "\4\3\10\uffff\1\3\13\uffff\1\2\13\uffff\1\1\3\uffff\1\3\2\uffff"+
            "\1\3\13\uffff\1\3\2\uffff\1\3\1\uffff\11\3\1\uffff\10\3",
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
            return "1161:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA30_2 = input.LA(1);

                         
                        int index30_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalExport()) ) {s = 29;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index30_2);
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
 

    public static final BitSet FOLLOW_ruleExportModel_in_entryRuleExportModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExportModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleExportModel123 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_13_in_ruleExportModel141 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExportModel172 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleExportModel189 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExportModel212 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ruleImport_in_ruleExportModel235 = new BitSet(new long[]{0x000000000004B000L});
    public static final BitSet FOLLOW_ruleExtension_in_ruleExportModel257 = new BitSet(new long[]{0x000000000000B000L});
    public static final BitSet FOLLOW_15_in_ruleExportModel271 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleExportModel283 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleInterface_in_ruleExportModel304 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_17_in_ruleExportModel317 = new BitSet(new long[]{0x000000000000B000L});
    public static final BitSet FOLLOW_ruleExport_in_ruleExportModel340 = new BitSet(new long[]{0x000000000000B002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport377 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleImport424 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport444 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_19_in_ruleImport457 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleImport474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExtension_in_entryRuleExtension517 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExtension527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleExtension564 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExtension585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterface_in_entryRuleInterface623 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterface633 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleInterface681 = new BitSet(new long[]{0x0000000001500000L});
    public static final BitSet FOLLOW_20_in_ruleInterface694 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInterface715 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleInterface727 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_22_in_ruleInterface742 = new BitSet(new long[]{0x000000000E000010L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_ruleInterface763 = new BitSet(new long[]{0x0000000001C00000L});
    public static final BitSet FOLLOW_23_in_ruleInterface776 = new BitSet(new long[]{0x000000000E000010L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_ruleInterface797 = new BitSet(new long[]{0x0000000001C00000L});
    public static final BitSet FOLLOW_24_in_ruleInterface813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_entryRuleInterfaceItem849 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceItem859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceField_in_ruleInterfaceItem906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceNavigation_in_ruleInterfaceItem933 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceExpression_in_ruleInterfaceItem960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceField_in_entryRuleInterfaceField995 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceField1005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleInterfaceField1048 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterfaceField1082 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceNavigation_in_entryRuleInterfaceNavigation1118 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceNavigation1128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleInterfaceNavigation1165 = new BitSet(new long[]{0x0000000002000010L});
    public static final BitSet FOLLOW_25_in_ruleInterfaceNavigation1183 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterfaceNavigation1217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceExpression_in_entryRuleInterfaceExpression1253 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceExpression1263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleInterfaceExpression1300 = new BitSet(new long[]{0x0000000016000000L});
    public static final BitSet FOLLOW_26_in_ruleInterfaceExpression1318 = new BitSet(new long[]{0x0000000012000000L});
    public static final BitSet FOLLOW_25_in_ruleInterfaceExpression1350 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleInterfaceExpression1376 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInterfaceExpression1397 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleInterfaceExpression1409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExport_in_entryRuleExport1445 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExport1455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleExport1492 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_30_in_ruleExport1511 = new BitSet(new long[]{0x0000000000100010L});
    public static final BitSet FOLLOW_20_in_ruleExport1537 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExport1558 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleExport1570 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExport1597 = new BitSet(new long[]{0x0000000000190000L});
    public static final BitSet FOLLOW_19_in_ruleExport1610 = new BitSet(new long[]{0x48009100900100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_31_in_ruleExport1628 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExport1663 = new BitSet(new long[]{0x0000000000110000L});
    public static final BitSet FOLLOW_20_in_ruleExport1678 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExport1699 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleExport1711 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleExport1725 = new BitSet(new long[]{0x0000007900020000L});
    public static final BitSet FOLLOW_32_in_ruleExport1738 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleExport1750 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33_in_ruleExport1768 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleExport1794 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleExport1806 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExport1826 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleExport1838 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleExport1850 = new BitSet(new long[]{0x0000007800020000L});
    public static final BitSet FOLLOW_35_in_ruleExport1872 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_36_in_ruleExport1909 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleExport1935 = new BitSet(new long[]{0x0000006000020000L});
    public static final BitSet FOLLOW_37_in_ruleExport1951 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleExport1972 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_23_in_ruleExport1985 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleExport2006 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_24_in_ruleExport2020 = new BitSet(new long[]{0x0000006000020000L});
    public static final BitSet FOLLOW_38_in_ruleExport2040 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleUserData_in_ruleExport2061 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_23_in_ruleExport2074 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleUserData_in_ruleExport2095 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_24_in_ruleExport2109 = new BitSet(new long[]{0x0000006000020000L});
    public static final BitSet FOLLOW_17_in_ruleExport2124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUserData_in_entryRuleUserData2160 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUserData2170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleUserData2212 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleUserData2229 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleUserData2250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute2286 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute2296 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2340 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID2376 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID2387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID2427 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39_in_ruleQualifiedID2446 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID2461 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression2508 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_ruleExpression2565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_ruleExpression2598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_ruleExpression2626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_entryRuleLetExpression2663 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetExpression2673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleLetExpression2710 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLetExpression2731 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleLetExpression2743 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression2764 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleLetExpression2776 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression2797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression2833 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCastedExpression2843 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleCastedExpression2880 = new BitSet(new long[]{0x0000000000000010L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleType_in_ruleCastedExpression2901 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleCastedExpression2913 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCastedExpression2934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_entryRuleChainExpression2970 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainExpression2980 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression3027 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_ruleChainExpression3048 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression3069 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression3107 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainedExpression3117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression3164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression3191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_ruleChainedExpression3218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri3253 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionTri3263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleIfExpressionTri3310 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_ruleIfExpressionTri3331 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3352 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleIfExpressionTri3364 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3385 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw3423 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionKw3433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleIfExpressionKw3470 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3491 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleIfExpressionKw3503 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3524 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_46_in_ruleIfExpressionKw3545 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression3605 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchExpression3615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleSwitchExpression3652 = new BitSet(new long[]{0x0000000010010000L});
    public static final BitSet FOLLOW_28_in_ruleSwitchExpression3665 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression3686 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleSwitchExpression3698 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleSwitchExpression3712 = new BitSet(new long[]{0x0003000000000000L});
    public static final BitSet FOLLOW_ruleCase_in_ruleSwitchExpression3733 = new BitSet(new long[]{0x0003000000000000L});
    public static final BitSet FOLLOW_48_in_ruleSwitchExpression3746 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleSwitchExpression3758 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression3779 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleSwitchExpression3791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCase_in_entryRuleCase3827 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCase3837 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleCase3874 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase3895 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_ruleCase3907 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase3928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression3964 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression3974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression4021 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50_in_ruleOrExpression4048 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression4082 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression4120 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression4130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression4177 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_ruleAndExpression4204 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression4238 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression4276 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpression4286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4333 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_52_in_ruleImpliesExpression4360 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4394 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression4432 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression4442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4489 = new BitSet(new long[]{0x07E0000000000002L});
    public static final BitSet FOLLOW_53_in_ruleRelationalExpression4518 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_54_in_ruleRelationalExpression4547 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_55_in_ruleRelationalExpression4576 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_56_in_ruleRelationalExpression4605 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_57_in_ruleRelationalExpression4634 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_58_in_ruleRelationalExpression4663 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4700 = new BitSet(new long[]{0x07E0000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression4738 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression4748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4795 = new BitSet(new long[]{0x0800000002000002L});
    public static final BitSet FOLLOW_25_in_ruleAdditiveExpression4824 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_59_in_ruleAdditiveExpression4853 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4890 = new BitSet(new long[]{0x0800000002000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4928 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression4938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4985 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleMultiplicativeExpression5014 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_61_in_ruleMultiplicativeExpression5043 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression5080 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression5118 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression5128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression5175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression5202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression5237 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression5247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleUnaryExpression5292 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_59_in_ruleUnaryExpression5321 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression5358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression5394 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression5404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression5451 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleInfixExpression5473 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression5494 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleInfixExpression5506 = new BitSet(new long[]{0x48009100300100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression5528 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_23_in_ruleInfixExpression5541 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression5562 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_29_in_ruleInfixExpression5578 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleInfixExpression5607 = new BitSet(new long[]{0x0000000000000010L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression5628 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleInfixExpression5657 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleInfixExpression5675 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleInfixExpression5700 = new BitSet(new long[]{0x0000000000000010L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression5721 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleInfixExpression5733 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleInfixExpression5762 = new BitSet(new long[]{0x0000000000000000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_65_in_ruleInfixExpression5782 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_66_in_ruleInfixExpression5811 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_67_in_ruleInfixExpression5840 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_68_in_ruleInfixExpression5869 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_69_in_ruleInfixExpression5898 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_70_in_ruleInfixExpression5927 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_71_in_ruleInfixExpression5956 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_72_in_ruleInfixExpression5985 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleInfixExpression6013 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression6035 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_ruleInfixExpression6047 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression6070 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleInfixExpression6082 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression6121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression6131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression6178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_rulePrimaryExpression6205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_rulePrimaryExpression6232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression6259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression6286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression6313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral6348 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral6358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral6405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral6432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral6459 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_ruleLiteral6486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral6513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral6548 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral6558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleBooleanLiteral6602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleBooleanLiteral6631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral6682 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral6692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral6733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral6773 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral6783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleNullLiteral6825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral6873 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealLiteral6883 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_in_ruleRealLiteral6924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral6964 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral6974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral7015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression7055 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression7065 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleParanthesizedExpression7102 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression7124 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleParanthesizedExpression7135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression7171 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression7181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleGlobalVarExpression7218 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression7239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall7275 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureCall7285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_ruleFeatureCall7332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_ruleFeatureCall7358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_ruleFeatureCall7386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall7413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_entryRuleOperationCall7448 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCall7458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleOperationCall7504 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleOperationCall7516 = new BitSet(new long[]{0x48009100300100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall7538 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_23_in_ruleOperationCall7551 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall7572 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_29_in_ruleOperationCall7588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_entryRuleListLiteral7624 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListLiteral7634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleListLiteral7680 = new BitSet(new long[]{0x48009100100300F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral7702 = new BitSet(new long[]{0x0000000000820000L});
    public static final BitSet FOLLOW_23_in_ruleListLiteral7715 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral7736 = new BitSet(new long[]{0x0000000000820000L});
    public static final BitSet FOLLOW_17_in_ruleListLiteral7752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression7788 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstructorCallExpression7798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleConstructorCallExpression7835 = new BitSet(new long[]{0x0000000000000010L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression7856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression7892 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTypeSelectExpression7902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleTypeSelectExpression7945 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleTypeSelectExpression7970 = new BitSet(new long[]{0x0000000000000010L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleType_in_ruleTypeSelectExpression7991 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleTypeSelectExpression8003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression8039 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionExpression8049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_ruleCollectionExpression8094 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_66_in_ruleCollectionExpression8123 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_67_in_ruleCollectionExpression8152 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_68_in_ruleCollectionExpression8181 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_69_in_ruleCollectionExpression8210 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_70_in_ruleCollectionExpression8239 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_71_in_ruleCollectionExpression8268 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_72_in_ruleCollectionExpression8297 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleCollectionExpression8325 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleCollectionExpression8347 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_ruleCollectionExpression8359 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCollectionExpression8382 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleCollectionExpression8394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType8430 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType8440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_ruleType8487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleType8514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_entryRuleCollectionType8549 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionType8559 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleCollectionType8604 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_80_in_ruleCollectionType8633 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_81_in_ruleCollectionType8662 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleCollectionType8690 = new BitSet(new long[]{0x0000000000000010L,0x0000000000038000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleCollectionType8711 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleCollectionType8723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_entryRuleSimpleType8759 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleType8769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType8815 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39_in_ruleSimpleType8828 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType8849 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier8888 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier8899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIdentifier8938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_synpred1_InternalExport2582 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_synpred2_InternalExport3536 = new BitSet(new long[]{0x0000000000000002L});

}