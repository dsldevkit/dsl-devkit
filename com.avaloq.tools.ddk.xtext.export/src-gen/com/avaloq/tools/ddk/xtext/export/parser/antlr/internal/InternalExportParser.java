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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_REAL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'interface'", "'{'", "'}'", "'import'", "'as'", "'extension'", "'['", "']'", "'='", "','", "';'", "'+'", "'@'", "'eval'", "'('", "')'", "'export'", "'lookup'", "'qualified'", "'uri-fragment'", "'unique'", "'attribute'", "'object-fingerprint'", "'resource-fingerprint'", "'field'", "'data'", "'::'", "'let'", "':'", "'->'", "'?'", "'if'", "'then'", "'else'", "'switch'", "'default'", "'case'", "'||'", "'&&'", "'implies'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'-'", "'*'", "'/'", "'!'", "'.'", "'typeSelect'", "'collect'", "'select'", "'selectFirst'", "'reject'", "'exists'", "'notExists'", "'sortBy'", "'forAll'", "'|'", "'true'", "'false'", "'null'", "'GLOBALVAR'", "'new'", "'Collection'", "'List'", "'Set'"
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
    public static final int RULE_ID=5;
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



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:73:1: entryRuleExportModel returns [EObject current=null] : iv_ruleExportModel= ruleExportModel EOF ;
    public final EObject entryRuleExportModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExportModel = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:74:2: (iv_ruleExportModel= ruleExportModel EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:75:2: iv_ruleExportModel= ruleExportModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExportModelRule()); 
            }
            pushFollow(FOLLOW_ruleExportModel_in_entryRuleExportModel81);
            iv_ruleExportModel=ruleExportModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExportModel; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExportModel91); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:82:1: ruleExportModel returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ ) ;
    public final EObject ruleExportModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_imports_0_0 = null;

        EObject lv_extensions_1_0 = null;

        EObject lv_interfaces_4_0 = null;

        EObject lv_exports_6_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:85:28: ( ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:86:1: ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:86:1: ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:86:2: ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:86:2: ( (lv_imports_0_0= ruleImport ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==15) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:87:1: (lv_imports_0_0= ruleImport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:87:1: (lv_imports_0_0= ruleImport )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:88:3: lv_imports_0_0= ruleImport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getImportsImportParserRuleCall_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImport_in_ruleExportModel137);
            	    lv_imports_0_0=ruleImport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"imports",
            	              		lv_imports_0_0, 
            	              		"Import");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:104:3: ( (lv_extensions_1_0= ruleExtension ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:105:1: (lv_extensions_1_0= ruleExtension )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:105:1: (lv_extensions_1_0= ruleExtension )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:106:3: lv_extensions_1_0= ruleExtension
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getExtensionsExtensionParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExtension_in_ruleExportModel159);
            	    lv_extensions_1_0=ruleExtension();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"extensions",
            	              		lv_extensions_1_0, 
            	              		"Extension");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:122:3: (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:122:5: otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}'
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleExportModel173); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getExportModelAccess().getInterfaceKeyword_2_0());
                          
                    }
                    otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleExportModel185); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getExportModelAccess().getLeftCurlyBracketKeyword_2_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:130:1: ( (lv_interfaces_4_0= ruleInterface ) )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==RULE_ID) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:131:1: (lv_interfaces_4_0= ruleInterface )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:131:1: (lv_interfaces_4_0= ruleInterface )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:132:3: lv_interfaces_4_0= ruleInterface
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getExportModelAccess().getInterfacesInterfaceParserRuleCall_2_2_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleInterface_in_ruleExportModel206);
                    	    lv_interfaces_4_0=ruleInterface();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      	        if (current==null) {
                    	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
                    	      	        }
                    	             		add(
                    	             			current, 
                    	             			"interfaces",
                    	              		lv_interfaces_4_0, 
                    	              		"Interface");
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

                    otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleExportModel219); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getExportModelAccess().getRightCurlyBracketKeyword_2_3());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:152:3: ( (lv_exports_6_0= ruleExport ) )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==28) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:153:1: (lv_exports_6_0= ruleExport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:153:1: (lv_exports_6_0= ruleExport )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:154:3: lv_exports_6_0= ruleExport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getExportsExportParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExport_in_ruleExportModel242);
            	    lv_exports_6_0=ruleExport();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"exports",
            	              		lv_exports_6_0, 
            	              		"Export");
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:178:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:179:2: (iv_ruleImport= ruleImport EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:180:2: iv_ruleImport= ruleImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportRule()); 
            }
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport279);
            iv_ruleImport=ruleImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport289); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:187:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:190:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:191:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:191:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:191:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleImport326); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:195:1: ( (otherlv_1= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:196:1: (otherlv_1= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:196:1: (otherlv_1= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:197:3: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getImportRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImport350); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
              	
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:211:2: (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==16) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:211:4: otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleImport363); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportAccess().getAsKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:215:1: ( (lv_name_3_0= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:216:1: (lv_name_3_0= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:216:1: (lv_name_3_0= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:217:3: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleImport380); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:241:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:242:2: (iv_ruleExtension= ruleExtension EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:243:2: iv_ruleExtension= ruleExtension EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExtensionRule()); 
            }
            pushFollow(FOLLOW_ruleExtension_in_entryRuleExtension423);
            iv_ruleExtension=ruleExtension();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExtension; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExtension433); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:250:1: ruleExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_extension_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:253:28: ( (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:254:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:254:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:254:3: otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleExtension470); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExtensionAccess().getExtensionKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:258:1: ( (lv_extension_1_0= ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:259:1: (lv_extension_1_0= ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:259:1: (lv_extension_1_0= ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:260:3: lv_extension_1_0= ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExtensionAccess().getExtensionQualifiedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExtension491);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:286:1: entryRuleInterface returns [EObject current=null] : iv_ruleInterface= ruleInterface EOF ;
    public final EObject entryRuleInterface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterface = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:287:2: (iv_ruleInterface= ruleInterface EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:288:2: iv_ruleInterface= ruleInterface EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceRule()); 
            }
            pushFollow(FOLLOW_ruleInterface_in_entryRuleInterface529);
            iv_ruleInterface=ruleInterface();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterface; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterface539); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:295:1: ruleInterface returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:298:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:299:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:299:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:299:2: ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:299:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:300:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:300:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:301:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInterfaceAccess().getTypeEClassCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleInterface591);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:317:2: (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==18) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:317:4: otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']'
                    {
                    otherlv_1=(Token)match(input,18,FOLLOW_18_in_ruleInterface604); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getInterfaceAccess().getLeftSquareBracketKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:321:1: ( (lv_guard_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:322:1: (lv_guard_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:322:1: (lv_guard_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:323:3: lv_guard_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInterfaceAccess().getGuardExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleInterface625);
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

                    otherlv_3=(Token)match(input,19,FOLLOW_19_in_ruleInterface637); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getInterfaceAccess().getRightSquareBracketKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:343:3: (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==20) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:343:5: otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    {
            	    otherlv_4=(Token)match(input,20,FOLLOW_20_in_ruleInterface652); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInterfaceAccess().getEqualsSignKeyword_2_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:347:1: ( (lv_items_5_0= ruleInterfaceItem ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:348:1: (lv_items_5_0= ruleInterfaceItem )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:348:1: (lv_items_5_0= ruleInterfaceItem )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:349:3: lv_items_5_0= ruleInterfaceItem
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleInterfaceItem_in_ruleInterface673);
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:365:2: (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    loop8:
            	    do {
            	        int alt8=2;
            	        int LA8_0 = input.LA(1);

            	        if ( (LA8_0==21) ) {
            	            alt8=1;
            	        }


            	        switch (alt8) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:365:4: otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    {
            	    	    otherlv_6=(Token)match(input,21,FOLLOW_21_in_ruleInterface686); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_6, grammarAccess.getInterfaceAccess().getCommaKeyword_2_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:369:1: ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:370:1: (lv_items_7_0= ruleInterfaceItem )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:370:1: (lv_items_7_0= ruleInterfaceItem )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:371:3: lv_items_7_0= ruleInterfaceItem
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleInterfaceItem_in_ruleInterface707);
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
            	    	    break loop8;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            otherlv_8=(Token)match(input,22,FOLLOW_22_in_ruleInterface723); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:399:1: entryRuleInterfaceItem returns [EObject current=null] : iv_ruleInterfaceItem= ruleInterfaceItem EOF ;
    public final EObject entryRuleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceItem = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:400:2: (iv_ruleInterfaceItem= ruleInterfaceItem EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:401:2: iv_ruleInterfaceItem= ruleInterfaceItem EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceItemRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceItem_in_entryRuleInterfaceItem759);
            iv_ruleInterfaceItem=ruleInterfaceItem();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceItem; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceItem769); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:408:1: ruleInterfaceItem returns [EObject current=null] : (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) ;
    public final EObject ruleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject this_InterfaceField_0 = null;

        EObject this_InterfaceNavigation_1 = null;

        EObject this_InterfaceExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:411:28: ( (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:412:1: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:412:1: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
            int alt10=3;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case 23:
                {
                alt10=1;
                }
                break;
            case 24:
                {
                alt10=2;
                }
                break;
            case 25:
                {
                alt10=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:413:2: this_InterfaceField_0= ruleInterfaceField
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceFieldParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceField_in_ruleInterfaceItem819);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:426:2: this_InterfaceNavigation_1= ruleInterfaceNavigation
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceNavigationParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceNavigation_in_ruleInterfaceItem849);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:439:2: this_InterfaceExpression_2= ruleInterfaceExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceExpression_in_ruleInterfaceItem879);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:458:1: entryRuleInterfaceField returns [EObject current=null] : iv_ruleInterfaceField= ruleInterfaceField EOF ;
    public final EObject entryRuleInterfaceField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceField = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:459:2: (iv_ruleInterfaceField= ruleInterfaceField EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:460:2: iv_ruleInterfaceField= ruleInterfaceField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceFieldRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceField_in_entryRuleInterfaceField914);
            iv_ruleInterfaceField=ruleInterfaceField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceField; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceField924); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:467:1: ruleInterfaceField returns [EObject current=null] : ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceField() throws RecognitionException {
        EObject current = null;

        Token lv_unordered_0_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:470:28: ( ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:471:1: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:471:1: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:471:2: ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:471:2: ( (lv_unordered_0_0= '+' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:472:1: (lv_unordered_0_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:472:1: (lv_unordered_0_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:473:3: lv_unordered_0_0= '+'
                    {
                    lv_unordered_0_0=(Token)match(input,23,FOLLOW_23_in_ruleInterfaceField967); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:486:3: ( (otherlv_1= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:487:1: (otherlv_1= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:487:1: (otherlv_1= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:488:3: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceFieldRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterfaceField1005); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:510:1: entryRuleInterfaceNavigation returns [EObject current=null] : iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF ;
    public final EObject entryRuleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceNavigation = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:511:2: (iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:512:2: iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceNavigationRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceNavigation_in_entryRuleInterfaceNavigation1041);
            iv_ruleInterfaceNavigation=ruleInterfaceNavigation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceNavigation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceNavigation1051); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:519:1: ruleInterfaceNavigation returns [EObject current=null] : (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_unordered_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:522:28: ( (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:523:1: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:523:1: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:523:3: otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleInterfaceNavigation1088); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInterfaceNavigationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:527:1: ( (lv_unordered_1_0= '+' ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==23) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:528:1: (lv_unordered_1_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:528:1: (lv_unordered_1_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:529:3: lv_unordered_1_0= '+'
                    {
                    lv_unordered_1_0=(Token)match(input,23,FOLLOW_23_in_ruleInterfaceNavigation1106); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:542:3: ( (otherlv_2= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:543:1: (otherlv_2= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:543:1: (otherlv_2= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:544:3: otherlv_2= RULE_ID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceNavigationRule());
              	        }
                      
            }
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterfaceNavigation1144); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:566:1: entryRuleInterfaceExpression returns [EObject current=null] : iv_ruleInterfaceExpression= ruleInterfaceExpression EOF ;
    public final EObject entryRuleInterfaceExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:567:2: (iv_ruleInterfaceExpression= ruleInterfaceExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:568:2: iv_ruleInterfaceExpression= ruleInterfaceExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceExpression_in_entryRuleInterfaceExpression1180);
            iv_ruleInterfaceExpression=ruleInterfaceExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceExpression1190); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:575:1: ruleInterfaceExpression returns [EObject current=null] : (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:578:28: ( (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:579:1: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:579:1: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:579:3: otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_25_in_ruleInterfaceExpression1227); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInterfaceExpressionAccess().getEvalKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:583:1: ( (lv_ref_1_0= '@' ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==24) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:584:1: (lv_ref_1_0= '@' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:584:1: (lv_ref_1_0= '@' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:585:3: lv_ref_1_0= '@'
                    {
                    lv_ref_1_0=(Token)match(input,24,FOLLOW_24_in_ruleInterfaceExpression1245); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:598:3: ( (lv_unordered_2_0= '+' ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==23) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:599:1: (lv_unordered_2_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:599:1: (lv_unordered_2_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:600:3: lv_unordered_2_0= '+'
                    {
                    lv_unordered_2_0=(Token)match(input,23,FOLLOW_23_in_ruleInterfaceExpression1277); if (state.failed) return current;
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

            otherlv_3=(Token)match(input,26,FOLLOW_26_in_ruleInterfaceExpression1303); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getInterfaceExpressionAccess().getLeftParenthesisKeyword_3());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:617:1: ( (lv_expr_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:618:1: (lv_expr_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:618:1: (lv_expr_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:619:3: lv_expr_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInterfaceExpressionAccess().getExprExpressionParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleInterfaceExpression1324);
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

            otherlv_5=(Token)match(input,27,FOLLOW_27_in_ruleInterfaceExpression1336); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:647:1: entryRuleExport returns [EObject current=null] : iv_ruleExport= ruleExport EOF ;
    public final EObject entryRuleExport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:648:2: (iv_ruleExport= ruleExport EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:649:2: iv_ruleExport= ruleExport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExportRule()); 
            }
            pushFollow(FOLLOW_ruleExport_in_entryRuleExport1372);
            iv_ruleExport=ruleExport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExport1382); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:656:1: ruleExport returns [EObject current=null] : (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' ) ;
    public final EObject ruleExport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_allowLookup_1_0=null;
        Token otherlv_3=null;
        Token lv_qualifiedName_4_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token lv_fragmentUnique_12_0=null;
        Token otherlv_13=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token lv_fingerprint_18_0=null;
        Token lv_resourceFingerprint_19_0=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_30=null;
        Token otherlv_31=null;
        EObject lv_naming_5_0 = null;

        EObject lv_guard_7_0 = null;

        EObject lv_attributes_22_0 = null;

        EObject lv_attributes_24_0 = null;

        EObject lv_userData_27_0 = null;

        EObject lv_userData_29_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:659:28: ( (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:660:1: (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:660:1: (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:660:3: otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_ruleExport1419); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExportAccess().getExportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:664:1: ( (lv_allowLookup_1_0= 'lookup' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==29) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:665:1: (lv_allowLookup_1_0= 'lookup' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:665:1: (lv_allowLookup_1_0= 'lookup' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:666:3: lv_allowLookup_1_0= 'lookup'
                    {
                    lv_allowLookup_1_0=(Token)match(input,29,FOLLOW_29_in_ruleExport1437); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_allowLookup_1_0, grammarAccess.getExportAccess().getAllowLookupLookupKeyword_1_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExportRule());
                      	        }
                             		setWithLastConsumed(current, "allowLookup", true, "lookup");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:679:3: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:680:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:680:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:681:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getExportRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExportAccess().getTypeEClassCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExport1478);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:697:2: (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==16) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:697:4: otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleExport1491); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getExportAccess().getAsKeyword_3_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:701:1: ( (lv_qualifiedName_4_0= 'qualified' ) )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==30) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:702:1: (lv_qualifiedName_4_0= 'qualified' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:702:1: (lv_qualifiedName_4_0= 'qualified' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:703:3: lv_qualifiedName_4_0= 'qualified'
                            {
                            lv_qualifiedName_4_0=(Token)match(input,30,FOLLOW_30_in_ruleExport1509); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_qualifiedName_4_0, grammarAccess.getExportAccess().getQualifiedNameQualifiedKeyword_3_1_0());
                                  
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:716:3: ( (lv_naming_5_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:717:1: (lv_naming_5_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:717:1: (lv_naming_5_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:718:3: lv_naming_5_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExportAccess().getNamingExpressionParserRuleCall_3_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleExport1544);
                    lv_naming_5_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExportRule());
                      	        }
                             		set(
                             			current, 
                             			"naming",
                              		lv_naming_5_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:734:4: (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==18) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:734:6: otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']'
                    {
                    otherlv_6=(Token)match(input,18,FOLLOW_18_in_ruleExport1559); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getExportAccess().getLeftSquareBracketKeyword_4_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:738:1: ( (lv_guard_7_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:739:1: (lv_guard_7_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:739:1: (lv_guard_7_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:740:3: lv_guard_7_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExportAccess().getGuardExpressionParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleExport1580);
                    lv_guard_7_0=ruleExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExportRule());
                      	        }
                             		set(
                             			current, 
                             			"guard",
                              		lv_guard_7_0, 
                              		"Expression");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_8=(Token)match(input,19,FOLLOW_19_in_ruleExport1592); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getExportAccess().getRightSquareBracketKeyword_4_2());
                          
                    }

                    }
                    break;

            }

            otherlv_9=(Token)match(input,13,FOLLOW_13_in_ruleExport1606); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getExportAccess().getLeftCurlyBracketKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:764:1: (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==31) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:764:3: otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';'
                    {
                    otherlv_10=(Token)match(input,31,FOLLOW_31_in_ruleExport1619); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getExportAccess().getUriFragmentKeyword_6_0());
                          
                    }
                    otherlv_11=(Token)match(input,20,FOLLOW_20_in_ruleExport1631); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getExportAccess().getEqualsSignKeyword_6_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:772:1: ( (lv_fragmentUnique_12_0= 'unique' ) )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==32) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:773:1: (lv_fragmentUnique_12_0= 'unique' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:773:1: (lv_fragmentUnique_12_0= 'unique' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:774:3: lv_fragmentUnique_12_0= 'unique'
                            {
                            lv_fragmentUnique_12_0=(Token)match(input,32,FOLLOW_32_in_ruleExport1649); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_fragmentUnique_12_0, grammarAccess.getExportAccess().getFragmentUniqueUniqueKeyword_6_2_0());
                                  
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

                    otherlv_13=(Token)match(input,33,FOLLOW_33_in_ruleExport1675); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getExportAccess().getAttributeKeyword_6_3());
                          
                    }
                    otherlv_14=(Token)match(input,26,FOLLOW_26_in_ruleExport1687); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getExportAccess().getLeftParenthesisKeyword_6_4());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:795:1: ( (otherlv_15= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:796:1: (otherlv_15= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:796:1: (otherlv_15= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:797:3: otherlv_15= RULE_ID
                    {
                    if ( state.backtracking==0 ) {
                       
                      		  /* */ 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getExportRule());
                      	        }
                              
                    }
                    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExport1711); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_15, grammarAccess.getExportAccess().getFragmentAttributeEAttributeCrossReference_6_5_0()); 
                      	
                    }

                    }


                    }

                    otherlv_16=(Token)match(input,27,FOLLOW_27_in_ruleExport1723); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getExportAccess().getRightParenthesisKeyword_6_6());
                          
                    }
                    otherlv_17=(Token)match(input,22,FOLLOW_22_in_ruleExport1735); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_17, grammarAccess.getExportAccess().getSemicolonKeyword_6_7());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:819:3: ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=34 && LA22_0<=35)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:819:4: ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';'
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:819:4: ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) )
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==34) ) {
                        alt21=1;
                    }
                    else if ( (LA21_0==35) ) {
                        alt21=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 0, input);

                        throw nvae;
                    }
                    switch (alt21) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:819:5: ( (lv_fingerprint_18_0= 'object-fingerprint' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:819:5: ( (lv_fingerprint_18_0= 'object-fingerprint' ) )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:820:1: (lv_fingerprint_18_0= 'object-fingerprint' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:820:1: (lv_fingerprint_18_0= 'object-fingerprint' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:821:3: lv_fingerprint_18_0= 'object-fingerprint'
                            {
                            lv_fingerprint_18_0=(Token)match(input,34,FOLLOW_34_in_ruleExport1757); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_fingerprint_18_0, grammarAccess.getExportAccess().getFingerprintObjectFingerprintKeyword_7_0_0_0());
                                  
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
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:835:6: ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:835:6: ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:836:1: (lv_resourceFingerprint_19_0= 'resource-fingerprint' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:836:1: (lv_resourceFingerprint_19_0= 'resource-fingerprint' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:837:3: lv_resourceFingerprint_19_0= 'resource-fingerprint'
                            {
                            lv_resourceFingerprint_19_0=(Token)match(input,35,FOLLOW_35_in_ruleExport1794); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_resourceFingerprint_19_0, grammarAccess.getExportAccess().getResourceFingerprintResourceFingerprintKeyword_7_0_1_0());
                                  
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

                    otherlv_20=(Token)match(input,22,FOLLOW_22_in_ruleExport1820); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getExportAccess().getSemicolonKeyword_7_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:854:3: ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )*
            loop25:
            do {
                int alt25=3;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==36) ) {
                    alt25=1;
                }
                else if ( (LA25_0==37) ) {
                    alt25=2;
                }


                switch (alt25) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:854:4: (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:854:4: (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:854:6: otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';'
            	    {
            	    otherlv_21=(Token)match(input,36,FOLLOW_36_in_ruleExport1836); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getExportAccess().getFieldKeyword_8_0_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:858:1: ( (lv_attributes_22_0= ruleAttribute ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:859:1: (lv_attributes_22_0= ruleAttribute )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:859:1: (lv_attributes_22_0= ruleAttribute )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:860:3: lv_attributes_22_0= ruleAttribute
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleExport1857);
            	    lv_attributes_22_0=ruleAttribute();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"attributes",
            	              		lv_attributes_22_0, 
            	              		"Attribute");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:876:2: (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )*
            	    loop23:
            	    do {
            	        int alt23=2;
            	        int LA23_0 = input.LA(1);

            	        if ( (LA23_0==21) ) {
            	            alt23=1;
            	        }


            	        switch (alt23) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:876:4: otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) )
            	    	    {
            	    	    otherlv_23=(Token)match(input,21,FOLLOW_21_in_ruleExport1870); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_23, grammarAccess.getExportAccess().getCommaKeyword_8_0_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:880:1: ( (lv_attributes_24_0= ruleAttribute ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:881:1: (lv_attributes_24_0= ruleAttribute )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:881:1: (lv_attributes_24_0= ruleAttribute )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:882:3: lv_attributes_24_0= ruleAttribute
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleAttribute_in_ruleExport1891);
            	    	    lv_attributes_24_0=ruleAttribute();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      	        if (current==null) {
            	    	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	    	      	        }
            	    	             		add(
            	    	             			current, 
            	    	             			"attributes",
            	    	              		lv_attributes_24_0, 
            	    	              		"Attribute");
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

            	    otherlv_25=(Token)match(input,22,FOLLOW_22_in_ruleExport1905); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getExportAccess().getSemicolonKeyword_8_0_3());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:903:6: (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:903:6: (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:903:8: otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';'
            	    {
            	    otherlv_26=(Token)match(input,37,FOLLOW_37_in_ruleExport1925); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_26, grammarAccess.getExportAccess().getDataKeyword_8_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:907:1: ( (lv_userData_27_0= ruleUserData ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:908:1: (lv_userData_27_0= ruleUserData )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:908:1: (lv_userData_27_0= ruleUserData )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:909:3: lv_userData_27_0= ruleUserData
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUserData_in_ruleExport1946);
            	    lv_userData_27_0=ruleUserData();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"userData",
            	              		lv_userData_27_0, 
            	              		"UserData");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:925:2: (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )*
            	    loop24:
            	    do {
            	        int alt24=2;
            	        int LA24_0 = input.LA(1);

            	        if ( (LA24_0==21) ) {
            	            alt24=1;
            	        }


            	        switch (alt24) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:925:4: otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) )
            	    	    {
            	    	    otherlv_28=(Token)match(input,21,FOLLOW_21_in_ruleExport1959); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_28, grammarAccess.getExportAccess().getCommaKeyword_8_1_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:929:1: ( (lv_userData_29_0= ruleUserData ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:930:1: (lv_userData_29_0= ruleUserData )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:930:1: (lv_userData_29_0= ruleUserData )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:931:3: lv_userData_29_0= ruleUserData
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleUserData_in_ruleExport1980);
            	    	    lv_userData_29_0=ruleUserData();

            	    	    state._fsp--;
            	    	    if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      	        if (current==null) {
            	    	      	            current = createModelElementForParent(grammarAccess.getExportRule());
            	    	      	        }
            	    	             		add(
            	    	             			current, 
            	    	             			"userData",
            	    	              		lv_userData_29_0, 
            	    	              		"UserData");
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

            	    otherlv_30=(Token)match(input,22,FOLLOW_22_in_ruleExport1994); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_30, grammarAccess.getExportAccess().getSemicolonKeyword_8_1_3());
            	          
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            otherlv_31=(Token)match(input,14,FOLLOW_14_in_ruleExport2009); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_31, grammarAccess.getExportAccess().getRightCurlyBracketKeyword_9());
                  
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:963:1: entryRuleUserData returns [EObject current=null] : iv_ruleUserData= ruleUserData EOF ;
    public final EObject entryRuleUserData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUserData = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:964:2: (iv_ruleUserData= ruleUserData EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:965:2: iv_ruleUserData= ruleUserData EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUserDataRule()); 
            }
            pushFollow(FOLLOW_ruleUserData_in_entryRuleUserData2045);
            iv_ruleUserData=ruleUserData();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUserData; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUserData2055); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:972:1: ruleUserData returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) ;
    public final EObject ruleUserData() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:975:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:976:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:976:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:976:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:976:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:977:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:977:1: (lv_name_0_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:978:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleUserData2097); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleUserData2114); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getUserDataAccess().getEqualsSignKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:998:1: ( (lv_expr_2_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:999:1: (lv_expr_2_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:999:1: (lv_expr_2_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1000:3: lv_expr_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUserDataAccess().getExprExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleUserData2135);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1024:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1025:2: (iv_ruleAttribute= ruleAttribute EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1026:2: iv_ruleAttribute= ruleAttribute EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAttributeRule()); 
            }
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute2171);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAttribute; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute2181); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1033:1: ruleAttribute returns [EObject current=null] : ( (otherlv_0= RULE_ID ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1036:28: ( ( (otherlv_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1037:1: ( (otherlv_0= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1037:1: ( (otherlv_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1038:1: (otherlv_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1038:1: (otherlv_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1039:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {
               
              		  /* */ 
              		
            }
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getAttributeRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2229); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1061:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1062:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1063:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID2265);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID2276); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1070:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1073:28: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1074:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1074:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1074:6: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID2316); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1081:1: (kw= '::' this_ID_2= RULE_ID )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==38) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1082:2: kw= '::' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,38,FOLLOW_38_in_ruleQualifiedID2335); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_1_0()); 
            	          
            	    }
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID2350); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_ID_2);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_ID_2, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_1_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    break loop26;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1102:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1103:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1104:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression2397);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2407); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1111:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1114:28: ( (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1115:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1115:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            int alt27=3;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1116:2: this_LetExpression_0= ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLetExpression_in_ruleExpression2457);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1129:2: this_CastedExpression_1= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCastedExpression_in_ruleExpression2487);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1142:2: this_ChainExpression_2= ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleChainExpression_in_ruleExpression2517);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1163:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1164:2: (iv_ruleLetExpression= ruleLetExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1165:2: iv_ruleLetExpression= ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLetExpression_in_entryRuleLetExpression2554);
            iv_ruleLetExpression=ruleLetExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetExpression2564); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1172:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1175:28: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1176:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1176:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1176:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,39,FOLLOW_39_in_ruleLetExpression2601); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1180:1: ( (lv_identifier_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1181:1: (lv_identifier_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1181:1: (lv_identifier_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1182:3: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLetExpression2622);
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

            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleLetExpression2634); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1202:1: ( (lv_varExpr_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1203:1: (lv_varExpr_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1203:1: (lv_varExpr_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1204:3: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression2655);
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

            otherlv_4=(Token)match(input,40,FOLLOW_40_in_ruleLetExpression2667); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1224:1: ( (lv_target_5_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1225:1: (lv_target_5_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1225:1: (lv_target_5_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1226:3: lv_target_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression2688);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1250:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1251:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1252:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression2724);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCastedExpression2734); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1259:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1262:28: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1263:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1263:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1263:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleCastedExpression2771); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1267:1: ( (lv_type_1_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1268:1: (lv_type_1_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1268:1: (lv_type_1_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1269:3: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleCastedExpression2792);
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

            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleCastedExpression2804); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1289:1: ( (lv_target_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1290:1: (lv_target_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1290:1: (lv_target_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1291:3: lv_target_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCastedExpression2825);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1315:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1316:2: (iv_ruleChainExpression= ruleChainExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1317:2: iv_ruleChainExpression= ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainExpression_in_entryRuleChainExpression2861);
            iv_ruleChainExpression=ruleChainExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainExpression2871); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1324:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1327:28: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1328:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1328:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1329:2: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression2921);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ChainedExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1340:1: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==41) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1340:2: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1340:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1341:2: 
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

            	    otherlv_2=(Token)match(input,41,FOLLOW_41_in_ruleChainExpression2945); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1353:1: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1354:1: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1354:1: (lv_next_3_0= ruleChainedExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1355:3: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression2966);
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
            	    break loop28;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1379:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1380:2: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1381:2: iv_ruleChainedExpression= ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression3004);
            iv_ruleChainedExpression=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainedExpression3014); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1388:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1391:28: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1392:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1392:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            int alt29=3;
            switch ( input.LA(1) ) {
            case 43:
                {
                alt29=1;
                }
                break;
            case RULE_STRING:
            case RULE_ID:
            case RULE_INT:
            case RULE_REAL:
            case 13:
            case 26:
            case 58:
            case 61:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
                {
                alt29=2;
                }
                break;
            case 46:
                {
                alt29=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }

            switch (alt29) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1393:2: this_IfExpressionKw_0= ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression3064);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1406:2: this_IfExpressionTri_1= ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression3094);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1419:2: this_SwitchExpression_2= ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSwitchExpression_in_ruleChainedExpression3124);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1438:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1439:2: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1440:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri3159);
            iv_ruleIfExpressionTri=ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionTri; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionTri3169); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1447:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1450:28: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1451:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1451:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1452:2: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleIfExpressionTri3219);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1463:1: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==42) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1463:2: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1463:2: ()
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1464:2: 
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

                    otherlv_2=(Token)match(input,42,FOLLOW_42_in_ruleIfExpressionTri3243); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1476:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1477:1: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1477:1: (lv_thenPart_3_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1478:3: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3264);
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

                    otherlv_4=(Token)match(input,40,FOLLOW_40_in_ruleIfExpressionTri3276); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1498:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1499:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1499:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1500:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3297);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1524:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1525:2: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1526:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw3335);
            iv_ruleIfExpressionKw=ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionKw; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionKw3345); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1533:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1536:28: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1537:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1537:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1537:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            otherlv_0=(Token)match(input,43,FOLLOW_43_in_ruleIfExpressionKw3382); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1541:1: ( (lv_condition_1_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1542:1: (lv_condition_1_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1542:1: (lv_condition_1_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1543:3: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3403);
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

            otherlv_2=(Token)match(input,44,FOLLOW_44_in_ruleIfExpressionKw3415); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1563:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1564:1: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1564:1: (lv_thenPart_3_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1565:3: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3436);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1581:2: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==45) ) {
                int LA31_1 = input.LA(2);

                if ( (synpred35_InternalExport()) ) {
                    alt31=1;
                }
            }
            switch (alt31) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1581:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,45,FOLLOW_45_in_ruleIfExpressionKw3449); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1585:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1586:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1586:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1587:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3470);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1611:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1612:2: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1613:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression3508);
            iv_ruleSwitchExpression=ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSwitchExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchExpression3518); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1620:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1623:28: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1624:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1624:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1624:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,46,FOLLOW_46_in_ruleSwitchExpression3555); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1628:1: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==26) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1628:3: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleSwitchExpression3568); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1632:1: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1633:1: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1633:1: (lv_switchExpr_2_0= ruleOrExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1634:3: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression3589);
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

                    otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleSwitchExpression3601); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleSwitchExpression3615); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1658:1: ( (lv_case_5_0= ruleCase ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==48) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1659:1: (lv_case_5_0= ruleCase )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1659:1: (lv_case_5_0= ruleCase )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1660:3: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleCase_in_ruleSwitchExpression3636);
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
            	    break loop33;
                }
            } while (true);

            otherlv_6=(Token)match(input,47,FOLLOW_47_in_ruleSwitchExpression3649); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
                  
            }
            otherlv_7=(Token)match(input,40,FOLLOW_40_in_ruleSwitchExpression3661); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1684:1: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1685:1: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1685:1: (lv_defaultExpr_8_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1686:3: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression3682);
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

            otherlv_9=(Token)match(input,14,FOLLOW_14_in_ruleSwitchExpression3694); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1714:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1715:2: (iv_ruleCase= ruleCase EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1716:2: iv_ruleCase= ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_ruleCase_in_entryRuleCase3730);
            iv_ruleCase=ruleCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCase; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCase3740); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1723:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1726:28: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1727:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1727:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1727:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,48,FOLLOW_48_in_ruleCase3777); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1731:1: ( (lv_condition_1_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1732:1: (lv_condition_1_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1732:1: (lv_condition_1_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1733:3: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase3798);
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

            otherlv_2=(Token)match(input,40,FOLLOW_40_in_ruleCase3810); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1753:1: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1754:1: (lv_thenPar_3_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1754:1: (lv_thenPar_3_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1755:3: lv_thenPar_3_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase3831);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1779:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1780:2: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1781:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression3867);
            iv_ruleOrExpression=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression3877); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1788:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1791:28: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1792:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1792:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1793:2: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression3927);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1804:1: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==49) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1804:2: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1804:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1805:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1813:2: ( (lv_operator_2_0= '||' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1814:1: (lv_operator_2_0= '||' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1814:1: (lv_operator_2_0= '||' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1815:3: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,49,FOLLOW_49_in_ruleOrExpression3957); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1828:2: ( (lv_right_3_0= ruleAndExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1829:1: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1829:1: (lv_right_3_0= ruleAndExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1830:3: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression3991);
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
    // $ANTLR end "ruleOrExpression"


    // $ANTLR start "entryRuleAndExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1854:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1855:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1856:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression4029);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression4039); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1863:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1866:28: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1867:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1867:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1868:2: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression4089);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1879:1: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==50) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1879:2: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1879:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1880:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1888:2: ( (lv_operator_2_0= '&&' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1889:1: (lv_operator_2_0= '&&' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1889:1: (lv_operator_2_0= '&&' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1890:3: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,50,FOLLOW_50_in_ruleAndExpression4119); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1903:2: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1904:1: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1904:1: (lv_right_3_0= ruleImpliesExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1905:3: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression4153);
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
    // $ANTLR end "ruleAndExpression"


    // $ANTLR start "entryRuleImpliesExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1929:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1930:2: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1931:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression4191);
            iv_ruleImpliesExpression=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpression4201); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1938:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1941:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1942:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1942:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1943:2: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4251);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1954:1: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==51) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1954:2: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1954:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1955:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1963:2: ( (lv_operator_2_0= 'implies' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1964:1: (lv_operator_2_0= 'implies' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1964:1: (lv_operator_2_0= 'implies' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1965:3: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,51,FOLLOW_51_in_ruleImpliesExpression4281); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1978:2: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1979:1: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1979:1: (lv_right_3_0= ruleRelationalExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1980:3: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4315);
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
    // $ANTLR end "ruleImpliesExpression"


    // $ANTLR start "entryRuleRelationalExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2004:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2005:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2006:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression4353);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression4363); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2013:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2016:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2017:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2017:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2018:2: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4413);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2029:1: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( ((LA38_0>=52 && LA38_0<=57)) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2029:2: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2029:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2030:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2038:2: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2039:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2039:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2040:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2040:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    int alt37=6;
            	    switch ( input.LA(1) ) {
            	    case 52:
            	        {
            	        alt37=1;
            	        }
            	        break;
            	    case 53:
            	        {
            	        alt37=2;
            	        }
            	        break;
            	    case 54:
            	        {
            	        alt37=3;
            	        }
            	        break;
            	    case 55:
            	        {
            	        alt37=4;
            	        }
            	        break;
            	    case 56:
            	        {
            	        alt37=5;
            	        }
            	        break;
            	    case 57:
            	        {
            	        alt37=6;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2041:3: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,52,FOLLOW_52_in_ruleRelationalExpression4445); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2053:8: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,53,FOLLOW_53_in_ruleRelationalExpression4474); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2065:8: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,54,FOLLOW_54_in_ruleRelationalExpression4503); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2077:8: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,55,FOLLOW_55_in_ruleRelationalExpression4532); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2089:8: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,56,FOLLOW_56_in_ruleRelationalExpression4561); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2101:8: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,57,FOLLOW_57_in_ruleRelationalExpression4590); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2116:2: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2117:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2117:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2118:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4627);
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
    // $ANTLR end "ruleRelationalExpression"


    // $ANTLR start "entryRuleAdditiveExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2142:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2143:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2144:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression4665);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression4675); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2151:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2154:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2155:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2155:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2156:2: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4725);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2167:1: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==23||LA40_0==58) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2167:2: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2167:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2168:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2176:2: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2177:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2177:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2178:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2178:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    int alt39=2;
            	    int LA39_0 = input.LA(1);

            	    if ( (LA39_0==23) ) {
            	        alt39=1;
            	    }
            	    else if ( (LA39_0==58) ) {
            	        alt39=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 39, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt39) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2179:3: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,23,FOLLOW_23_in_ruleAdditiveExpression4757); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2191:8: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,58,FOLLOW_58_in_ruleAdditiveExpression4786); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2206:2: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2207:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2207:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2208:3: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4823);
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
            	    break loop40;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2232:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2233:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2234:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4861);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression4871); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2241:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2244:28: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2245:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2245:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2246:2: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4921);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryOrInfixExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2257:1: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=59 && LA42_0<=60)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2257:2: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2257:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2258:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2266:2: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2267:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2267:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2268:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2268:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    int alt41=2;
            	    int LA41_0 = input.LA(1);

            	    if ( (LA41_0==59) ) {
            	        alt41=1;
            	    }
            	    else if ( (LA41_0==60) ) {
            	        alt41=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 41, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt41) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2269:3: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,59,FOLLOW_59_in_ruleMultiplicativeExpression4953); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2281:8: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,60,FOLLOW_60_in_ruleMultiplicativeExpression4982); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2296:2: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2297:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2297:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2298:3: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression5019);
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
    // $ANTLR end "ruleMultiplicativeExpression"


    // $ANTLR start "entryRuleUnaryOrInfixExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2322:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2323:2: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2324:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression5057);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOrInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression5067); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2331:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2334:28: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2335:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2335:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==58||LA43_0==61) ) {
                alt43=1;
            }
            else if ( ((LA43_0>=RULE_STRING && LA43_0<=RULE_REAL)||LA43_0==13||LA43_0==26||(LA43_0>=63 && LA43_0<=71)||(LA43_0>=73 && LA43_0<=80)) ) {
                alt43=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2336:2: this_UnaryExpression_0= ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression5117);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2349:2: this_InfixExpression_1= ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression5147);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2368:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2369:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2370:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression5182);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression5192); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2377:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2380:28: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2381:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2381:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2381:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2381:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2382:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2382:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2383:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2383:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==61) ) {
                alt44=1;
            }
            else if ( (LA44_0==58) ) {
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2384:3: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,61,FOLLOW_61_in_ruleUnaryExpression5237); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2396:8: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,58,FOLLOW_58_in_ruleUnaryExpression5266); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2411:2: ( (lv_params_1_0= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2412:1: (lv_params_1_0= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2412:1: (lv_params_1_0= ruleInfixExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2413:3: lv_params_1_0= ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression5303);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2437:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2438:2: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2439:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression5339);
            iv_ruleInfixExpression=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression5349); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2446:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2449:28: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2450:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2450:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2451:2: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression5399);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2462:1: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop49:
            do {
                int alt49=5;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==62) ) {
                    switch ( input.LA(2) ) {
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                    case 68:
                    case 69:
                    case 70:
                    case 71:
                        {
                        alt49=4;
                        }
                        break;
                    case 78:
                    case 79:
                    case 80:
                        {
                        alt49=2;
                        }
                        break;
                    case RULE_ID:
                        {
                        int LA49_5 = input.LA(3);

                        if ( (LA49_5==26) ) {
                            alt49=1;
                        }
                        else if ( (LA49_5==EOF||(LA49_5>=13 && LA49_5<=14)||(LA49_5>=18 && LA49_5<=19)||(LA49_5>=21 && LA49_5<=23)||LA49_5==27||LA49_5==38||(LA49_5>=40 && LA49_5<=42)||(LA49_5>=44 && LA49_5<=45)||(LA49_5>=47 && LA49_5<=60)||LA49_5==62) ) {
                            alt49=2;
                        }


                        }
                        break;
                    case 63:
                        {
                        alt49=3;
                        }
                        break;

                    }

                }


                switch (alt49) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2462:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2462:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2462:3: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2462:3: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2463:2: 
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

            	    otherlv_2=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5424); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2475:1: ( (lv_name_3_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2476:1: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2476:1: (lv_name_3_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2477:3: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression5445);
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

            	    otherlv_4=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression5457); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2497:1: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt46=2;
            	    int LA46_0 = input.LA(1);

            	    if ( ((LA46_0>=RULE_STRING && LA46_0<=RULE_REAL)||LA46_0==13||LA46_0==26||LA46_0==39||LA46_0==43||LA46_0==46||LA46_0==58||LA46_0==61||(LA46_0>=63 && LA46_0<=71)||(LA46_0>=73 && LA46_0<=80)) ) {
            	        alt46=1;
            	    }
            	    switch (alt46) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2497:2: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2497:2: ( (lv_params_5_0= ruleExpression ) )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2498:1: (lv_params_5_0= ruleExpression )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2498:1: (lv_params_5_0= ruleExpression )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2499:3: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression5479);
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

            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2515:2: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop45:
            	            do {
            	                int alt45=2;
            	                int LA45_0 = input.LA(1);

            	                if ( (LA45_0==21) ) {
            	                    alt45=1;
            	                }


            	                switch (alt45) {
            	            	case 1 :
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2515:4: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,21,FOLLOW_21_in_ruleInfixExpression5492); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	          
            	            	    }
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2519:1: ( (lv_params_7_0= ruleExpression ) )
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2520:1: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2520:1: (lv_params_7_0= ruleExpression )
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2521:3: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression5513);
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
            	            	    break loop45;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,27,FOLLOW_27_in_ruleInfixExpression5529); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2542:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2542:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2542:7: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2542:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2543:2: 
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

            	    otherlv_10=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5561); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2555:1: ( (lv_type_11_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2556:1: (lv_type_11_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2556:1: (lv_type_11_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2557:3: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression5582);
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
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2574:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2574:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2574:7: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2574:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2575:2: 
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

            	    otherlv_13=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5614); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2587:1: ( (lv_name_14_0= 'typeSelect' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2588:1: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2588:1: (lv_name_14_0= 'typeSelect' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2589:3: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,63,FOLLOW_63_in_ruleInfixExpression5632); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression5657); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2606:1: ( (lv_type_16_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2607:1: (lv_type_16_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2607:1: (lv_type_16_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2608:3: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression5678);
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

            	    otherlv_17=(Token)match(input,27,FOLLOW_27_in_ruleInfixExpression5690); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2629:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2629:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2629:7: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2629:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2630:2: 
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

            	    otherlv_19=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5722); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2642:1: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2643:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2643:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2644:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2644:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    int alt47=8;
            	    switch ( input.LA(1) ) {
            	    case 64:
            	        {
            	        alt47=1;
            	        }
            	        break;
            	    case 65:
            	        {
            	        alt47=2;
            	        }
            	        break;
            	    case 66:
            	        {
            	        alt47=3;
            	        }
            	        break;
            	    case 67:
            	        {
            	        alt47=4;
            	        }
            	        break;
            	    case 68:
            	        {
            	        alt47=5;
            	        }
            	        break;
            	    case 69:
            	        {
            	        alt47=6;
            	        }
            	        break;
            	    case 70:
            	        {
            	        alt47=7;
            	        }
            	        break;
            	    case 71:
            	        {
            	        alt47=8;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2645:3: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,64,FOLLOW_64_in_ruleInfixExpression5742); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2657:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,65,FOLLOW_65_in_ruleInfixExpression5771); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2669:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,66,FOLLOW_66_in_ruleInfixExpression5800); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2681:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,67,FOLLOW_67_in_ruleInfixExpression5829); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2693:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,68,FOLLOW_68_in_ruleInfixExpression5858); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2705:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,69,FOLLOW_69_in_ruleInfixExpression5887); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2717:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,70,FOLLOW_70_in_ruleInfixExpression5916); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2729:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,71,FOLLOW_71_in_ruleInfixExpression5945); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression5973); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2748:1: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
            	    int alt48=2;
            	    int LA48_0 = input.LA(1);

            	    if ( (LA48_0==RULE_ID) ) {
            	        int LA48_1 = input.LA(2);

            	        if ( (LA48_1==72) ) {
            	            alt48=1;
            	        }
            	    }
            	    switch (alt48) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2748:2: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2748:2: ( (lv_var_22_0= ruleIdentifier ) )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2749:1: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2749:1: (lv_var_22_0= ruleIdentifier )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2750:3: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression5995);
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

            	            otherlv_23=(Token)match(input,72,FOLLOW_72_in_ruleInfixExpression6007); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2770:3: ( (lv_exp_24_0= ruleExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2771:1: (lv_exp_24_0= ruleExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2771:1: (lv_exp_24_0= ruleExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2772:3: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression6030);
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

            	    otherlv_25=(Token)match(input,27,FOLLOW_27_in_ruleInfixExpression6042); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6());
            	          
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
    // $ANTLR end "ruleInfixExpression"


    // $ANTLR start "entryRulePrimaryExpression"
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2800:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2801:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2802:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression6081);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression6091); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2809:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2812:28: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2813:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2813:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            int alt50=6;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_INT:
            case RULE_REAL:
            case 73:
            case 74:
            case 75:
                {
                alt50=1;
                }
                break;
            case RULE_ID:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 78:
            case 79:
            case 80:
                {
                alt50=2;
                }
                break;
            case 13:
                {
                alt50=3;
                }
                break;
            case 77:
                {
                alt50=4;
                }
                break;
            case 76:
                {
                alt50=5;
                }
                break;
            case 26:
                {
                alt50=6;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2814:2: this_Literal_0= ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression6141);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2827:2: this_FeatureCall_1= ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFeatureCall_in_rulePrimaryExpression6171);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2840:2: this_ListLiteral_2= ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListLiteral_in_rulePrimaryExpression6201);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2853:2: this_ConstructorCallExpression_3= ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression6231);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2866:2: this_GlobalVarExpression_4= ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression6261);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2879:2: this_ParanthesizedExpression_5= ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression6291);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2898:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2899:2: (iv_ruleLiteral= ruleLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2900:2: iv_ruleLiteral= ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral6326);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral6336); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2907:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2910:28: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2911:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2911:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            int alt51=5;
            switch ( input.LA(1) ) {
            case 73:
            case 74:
                {
                alt51=1;
                }
                break;
            case RULE_INT:
                {
                alt51=2;
                }
                break;
            case 75:
                {
                alt51=3;
                }
                break;
            case RULE_REAL:
                {
                alt51=4;
                }
                break;
            case RULE_STRING:
                {
                alt51=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }

            switch (alt51) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2912:2: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral6386);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2925:2: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral6416);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2938:2: this_NullLiteral_2= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral6446);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2951:2: this_RealLiteral_3= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealLiteral_in_ruleLiteral6476);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2964:2: this_StringLiteral_4= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral6506);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2983:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2984:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2985:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral6541);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral6551); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2992:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2995:28: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2996:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2996:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2997:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2997:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2998:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2998:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==73) ) {
                alt52=1;
            }
            else if ( (LA52_0==74) ) {
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2999:3: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,73,FOLLOW_73_in_ruleBooleanLiteral6595); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3011:8: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,74,FOLLOW_74_in_ruleBooleanLiteral6624); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3034:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3035:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3036:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral6675);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral6685); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3043:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3046:28: ( ( (lv_val_0_0= RULE_INT ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3047:1: ( (lv_val_0_0= RULE_INT ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3047:1: ( (lv_val_0_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3048:1: (lv_val_0_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3048:1: (lv_val_0_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3049:3: lv_val_0_0= RULE_INT
            {
            lv_val_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral6726); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3073:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3074:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3075:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral6766);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral6776); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3082:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3085:28: ( ( (lv_val_0_0= 'null' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3086:1: ( (lv_val_0_0= 'null' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3086:1: ( (lv_val_0_0= 'null' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3087:1: (lv_val_0_0= 'null' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3087:1: (lv_val_0_0= 'null' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3088:3: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,75,FOLLOW_75_in_ruleNullLiteral6818); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3109:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3110:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3111:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral6866);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealLiteral6876); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3118:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3121:28: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3122:1: ( (lv_val_0_0= RULE_REAL ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3122:1: ( (lv_val_0_0= RULE_REAL ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3123:1: (lv_val_0_0= RULE_REAL )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3123:1: (lv_val_0_0= RULE_REAL )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3124:3: lv_val_0_0= RULE_REAL
            {
            lv_val_0_0=(Token)match(input,RULE_REAL,FOLLOW_RULE_REAL_in_ruleRealLiteral6917); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3148:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3149:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3150:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral6957);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral6967); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3157:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3160:28: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3161:1: ( (lv_val_0_0= RULE_STRING ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3161:1: ( (lv_val_0_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3162:1: (lv_val_0_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3162:1: (lv_val_0_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3163:3: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral7008); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3187:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3188:2: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3189:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression7048);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParanthesizedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression7058); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3196:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3199:28: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3200:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3200:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3200:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleParanthesizedExpression7095); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression7120);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleParanthesizedExpression7131); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3228:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3229:2: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3230:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression7167);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalVarExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression7177); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3237:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3240:28: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3241:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3241:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3241:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,76,FOLLOW_76_in_ruleGlobalVarExpression7214); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3245:1: ( (lv_name_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3246:1: (lv_name_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3246:1: (lv_name_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3247:3: lv_name_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression7235);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3271:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3272:2: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3273:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall7271);
            iv_ruleFeatureCall=ruleFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureCall7281); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3280:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3283:28: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3284:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3284:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            int alt53=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==26) ) {
                    alt53=1;
                }
                else if ( (LA53_1==EOF||(LA53_1>=13 && LA53_1<=14)||(LA53_1>=18 && LA53_1<=19)||(LA53_1>=21 && LA53_1<=23)||LA53_1==27||LA53_1==38||(LA53_1>=40 && LA53_1<=42)||(LA53_1>=44 && LA53_1<=45)||(LA53_1>=47 && LA53_1<=60)||LA53_1==62) ) {
                    alt53=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    throw nvae;
                }
                }
                break;
            case 78:
            case 79:
            case 80:
                {
                alt53=2;
                }
                break;
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
                {
                alt53=3;
                }
                break;
            case 63:
                {
                alt53=4;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3285:2: this_OperationCall_0= ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleOperationCall_in_ruleFeatureCall7331);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3297:6: ( (lv_type_1_0= ruleType ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3297:6: ( (lv_type_1_0= ruleType ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3298:1: (lv_type_1_0= ruleType )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3298:1: (lv_type_1_0= ruleType )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3299:3: lv_type_1_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleFeatureCall7357);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3317:2: this_CollectionExpression_2= ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionExpression_in_ruleFeatureCall7388);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3330:2: this_TypeSelectExpression_3= ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall7418);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3349:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3350:2: (iv_ruleOperationCall= ruleOperationCall EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3351:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_ruleOperationCall_in_entryRuleOperationCall7453);
            iv_ruleOperationCall=ruleOperationCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCall7463); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3358:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3361:28: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3362:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3362:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3362:2: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3362:2: ( (lv_name_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3363:1: (lv_name_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3363:1: (lv_name_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3364:3: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleOperationCall7509);
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

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleOperationCall7521); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3384:1: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( ((LA55_0>=RULE_STRING && LA55_0<=RULE_REAL)||LA55_0==13||LA55_0==26||LA55_0==39||LA55_0==43||LA55_0==46||LA55_0==58||LA55_0==61||(LA55_0>=63 && LA55_0<=71)||(LA55_0>=73 && LA55_0<=80)) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3384:2: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3384:2: ( (lv_params_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3385:1: (lv_params_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3385:1: (lv_params_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3386:3: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall7543);
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3402:2: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==21) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3402:4: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleOperationCall7556); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3406:1: ( (lv_params_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3407:1: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3407:1: (lv_params_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3408:3: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall7577);
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
                    	    break loop54;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,27,FOLLOW_27_in_ruleOperationCall7593); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3436:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3437:2: (iv_ruleListLiteral= ruleListLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3438:2: iv_ruleListLiteral= ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleListLiteral_in_entryRuleListLiteral7629);
            iv_ruleListLiteral=ruleListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListLiteral7639); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3445:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3448:28: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3449:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3449:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3449:2: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3449:2: ()
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3450:2: 
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

            otherlv_1=(Token)match(input,13,FOLLOW_13_in_ruleListLiteral7688); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3462:1: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( ((LA57_0>=RULE_STRING && LA57_0<=RULE_REAL)||LA57_0==13||LA57_0==26||LA57_0==39||LA57_0==43||LA57_0==46||LA57_0==58||LA57_0==61||(LA57_0>=63 && LA57_0<=71)||(LA57_0>=73 && LA57_0<=80)) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3462:2: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3462:2: ( (lv_elements_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3463:1: (lv_elements_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3463:1: (lv_elements_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3464:3: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral7710);
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3480:2: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==21) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3480:4: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleListLiteral7723); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3484:1: ( (lv_elements_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3485:1: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3485:1: (lv_elements_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3486:3: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral7744);
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
                    	    break loop56;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleListLiteral7760); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3514:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3515:2: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3516:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression7796);
            iv_ruleConstructorCallExpression=ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorCallExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstructorCallExpression7806); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3523:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3526:28: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3527:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3527:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3527:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,77,FOLLOW_77_in_ruleConstructorCallExpression7843); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3531:1: ( (lv_type_1_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3532:1: (lv_type_1_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3532:1: (lv_type_1_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3533:3: lv_type_1_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression7864);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3557:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3558:2: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3559:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression7900);
            iv_ruleTypeSelectExpression=ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeSelectExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTypeSelectExpression7910); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3566:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3569:28: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3570:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3570:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3570:2: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3570:2: ( (lv_name_0_0= 'typeSelect' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3571:1: (lv_name_0_0= 'typeSelect' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3571:1: (lv_name_0_0= 'typeSelect' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3572:3: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,63,FOLLOW_63_in_ruleTypeSelectExpression7953); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleTypeSelectExpression7978); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3589:1: ( (lv_type_2_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3590:1: (lv_type_2_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3590:1: (lv_type_2_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3591:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleTypeSelectExpression7999);
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

            otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleTypeSelectExpression8011); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3619:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3620:2: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3621:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression8047);
            iv_ruleCollectionExpression=ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionExpression8057); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3628:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3631:28: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3632:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3632:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3632:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3632:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3633:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3633:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3634:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3634:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            int alt58=8;
            switch ( input.LA(1) ) {
            case 64:
                {
                alt58=1;
                }
                break;
            case 65:
                {
                alt58=2;
                }
                break;
            case 66:
                {
                alt58=3;
                }
                break;
            case 67:
                {
                alt58=4;
                }
                break;
            case 68:
                {
                alt58=5;
                }
                break;
            case 69:
                {
                alt58=6;
                }
                break;
            case 70:
                {
                alt58=7;
                }
                break;
            case 71:
                {
                alt58=8;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3635:3: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,64,FOLLOW_64_in_ruleCollectionExpression8102); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3647:8: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,65,FOLLOW_65_in_ruleCollectionExpression8131); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3659:8: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,66,FOLLOW_66_in_ruleCollectionExpression8160); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3671:8: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,67,FOLLOW_67_in_ruleCollectionExpression8189); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3683:8: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,68,FOLLOW_68_in_ruleCollectionExpression8218); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3695:8: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,69,FOLLOW_69_in_ruleCollectionExpression8247); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3707:8: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,70,FOLLOW_70_in_ruleCollectionExpression8276); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3719:8: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,71,FOLLOW_71_in_ruleCollectionExpression8305); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleCollectionExpression8333); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3738:1: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==RULE_ID) ) {
                int LA59_1 = input.LA(2);

                if ( (LA59_1==72) ) {
                    alt59=1;
                }
            }
            switch (alt59) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3738:2: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3738:2: ( (lv_var_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3739:1: (lv_var_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3739:1: (lv_var_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3740:3: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleCollectionExpression8355);
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

                    otherlv_3=(Token)match(input,72,FOLLOW_72_in_ruleCollectionExpression8367); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3760:3: ( (lv_exp_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3761:1: (lv_exp_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3761:1: (lv_exp_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3762:3: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCollectionExpression8390);
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

            otherlv_5=(Token)match(input,27,FOLLOW_27_in_ruleCollectionExpression8402); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3790:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3791:2: (iv_ruleType= ruleType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3792:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType8438);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType8448); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3799:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3802:28: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3803:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3803:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( ((LA60_0>=78 && LA60_0<=80)) ) {
                alt60=1;
            }
            else if ( (LA60_0==RULE_ID) ) {
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3804:2: this_CollectionType_0= ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionType_in_ruleType8498);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3817:2: this_SimpleType_1= ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleType_in_ruleType8528);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3836:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3837:2: (iv_ruleCollectionType= ruleCollectionType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3838:2: iv_ruleCollectionType= ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionType_in_entryRuleCollectionType8563);
            iv_ruleCollectionType=ruleCollectionType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionType8573); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3845:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3848:28: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3849:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3849:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3849:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3849:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3850:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3850:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3851:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3851:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            int alt61=3;
            switch ( input.LA(1) ) {
            case 78:
                {
                alt61=1;
                }
                break;
            case 79:
                {
                alt61=2;
                }
                break;
            case 80:
                {
                alt61=3;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3852:3: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,78,FOLLOW_78_in_ruleCollectionType8618); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3864:8: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,79,FOLLOW_79_in_ruleCollectionType8647); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3876:8: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,80,FOLLOW_80_in_ruleCollectionType8676); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,18,FOLLOW_18_in_ruleCollectionType8704); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3895:1: ( (lv_id1_2_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3896:1: (lv_id1_2_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3896:1: (lv_id1_2_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3897:3: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleCollectionType8725);
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

            otherlv_3=(Token)match(input,19,FOLLOW_19_in_ruleCollectionType8737); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3925:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3926:2: (iv_ruleSimpleType= ruleSimpleType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3927:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleType_in_entryRuleSimpleType8773);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleType8783); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3934:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3937:28: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3938:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3938:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3938:2: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3938:2: ( (lv_id_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3939:1: (lv_id_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3939:1: (lv_id_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3940:3: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType8829);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3956:2: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==38) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3956:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,38,FOLLOW_38_in_ruleSimpleType8842); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3960:1: ( (lv_id_2_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3961:1: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3961:1: (lv_id_2_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3962:3: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType8863);
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
            	    break loop62;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3986:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3987:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3988:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier8902);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier8913); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3995:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3998:28: (this_ID_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3999:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIdentifier8952); if (state.failed) return current;
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

    // $ANTLR start synpred30_InternalExport
    public final void synpred30_InternalExport_fragment() throws RecognitionException {   
        EObject this_CastedExpression_1 = null;


        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1129:2: (this_CastedExpression_1= ruleCastedExpression )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1129:2: this_CastedExpression_1= ruleCastedExpression
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleCastedExpression_in_synpred30_InternalExport2487);
        this_CastedExpression_1=ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred30_InternalExport

    // $ANTLR start synpred35_InternalExport
    public final void synpred35_InternalExport_fragment() throws RecognitionException {   
        Token otherlv_4=null;
        EObject lv_elsePart_5_0 = null;


        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1581:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1581:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
        {
        otherlv_4=(Token)match(input,45,FOLLOW_45_in_synpred35_InternalExport3449); if (state.failed) return ;
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1585:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1586:1: (lv_elsePart_5_0= ruleChainedExpression )
        {
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1586:1: (lv_elsePart_5_0= ruleChainedExpression )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1587:3: lv_elsePart_5_0= ruleChainedExpression
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleChainedExpression_in_synpred35_InternalExport3470);
        lv_elsePart_5_0=ruleChainedExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred35_InternalExport

    // Delegated rules

    public final boolean synpred30_InternalExport() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_InternalExport_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_InternalExport() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_InternalExport_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA27 dfa27 = new DFA27(this);
    static final String DFA27_eotS =
        "\36\uffff";
    static final String DFA27_eofS =
        "\36\uffff";
    static final String DFA27_minS =
        "\1\4\1\uffff\1\0\33\uffff";
    static final String DFA27_maxS =
        "\1\120\1\uffff\1\0\33\uffff";
    static final String DFA27_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String DFA27_specialS =
        "\2\uffff\1\0\33\uffff}>";
    static final String[] DFA27_transitionS = {
            "\4\3\5\uffff\1\3\14\uffff\1\2\14\uffff\1\1\3\uffff\1\3\2\uffff"+
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

    static final short[] DFA27_eot = DFA.unpackEncodedString(DFA27_eotS);
    static final short[] DFA27_eof = DFA.unpackEncodedString(DFA27_eofS);
    static final char[] DFA27_min = DFA.unpackEncodedStringToUnsignedChars(DFA27_minS);
    static final char[] DFA27_max = DFA.unpackEncodedStringToUnsignedChars(DFA27_maxS);
    static final short[] DFA27_accept = DFA.unpackEncodedString(DFA27_acceptS);
    static final short[] DFA27_special = DFA.unpackEncodedString(DFA27_specialS);
    static final short[][] DFA27_transition;

    static {
        int numStates = DFA27_transitionS.length;
        DFA27_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA27_transition[i] = DFA.unpackEncodedString(DFA27_transitionS[i]);
        }
    }

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = DFA27_eot;
            this.eof = DFA27_eof;
            this.min = DFA27_min;
            this.max = DFA27_max;
            this.accept = DFA27_accept;
            this.special = DFA27_special;
            this.transition = DFA27_transition;
        }
        public String getDescription() {
            return "1115:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA27_2 = input.LA(1);

                         
                        int index27_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_InternalExport()) ) {s = 29;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index27_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 27, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleExportModel_in_entryRuleExportModel81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExportModel91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleExportModel137 = new BitSet(new long[]{0x0000000010029000L});
    public static final BitSet FOLLOW_ruleExtension_in_ruleExportModel159 = new BitSet(new long[]{0x0000000010021000L});
    public static final BitSet FOLLOW_12_in_ruleExportModel173 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleExportModel185 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleInterface_in_ruleExportModel206 = new BitSet(new long[]{0x0000000000004020L});
    public static final BitSet FOLLOW_14_in_ruleExportModel219 = new BitSet(new long[]{0x0000000010021000L});
    public static final BitSet FOLLOW_ruleExport_in_ruleExportModel242 = new BitSet(new long[]{0x0000000010021002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport279 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleImport326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport350 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleImport363 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleImport380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExtension_in_entryRuleExtension423 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExtension433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleExtension470 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExtension491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterface_in_entryRuleInterface529 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterface539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleInterface591 = new BitSet(new long[]{0x0000000000540000L});
    public static final BitSet FOLLOW_18_in_ruleInterface604 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInterface625 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleInterface637 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_20_in_ruleInterface652 = new BitSet(new long[]{0x0000000003800020L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_ruleInterface673 = new BitSet(new long[]{0x0000000000700000L});
    public static final BitSet FOLLOW_21_in_ruleInterface686 = new BitSet(new long[]{0x0000000003800020L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_ruleInterface707 = new BitSet(new long[]{0x0000000000700000L});
    public static final BitSet FOLLOW_22_in_ruleInterface723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_entryRuleInterfaceItem759 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceItem769 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceField_in_ruleInterfaceItem819 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceNavigation_in_ruleInterfaceItem849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceExpression_in_ruleInterfaceItem879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceField_in_entryRuleInterfaceField914 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceField924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleInterfaceField967 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterfaceField1005 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceNavigation_in_entryRuleInterfaceNavigation1041 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceNavigation1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleInterfaceNavigation1088 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_23_in_ruleInterfaceNavigation1106 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterfaceNavigation1144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceExpression_in_entryRuleInterfaceExpression1180 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceExpression1190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleInterfaceExpression1227 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_24_in_ruleInterfaceExpression1245 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_ruleInterfaceExpression1277 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInterfaceExpression1303 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInterfaceExpression1324 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleInterfaceExpression1336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExport_in_entryRuleExport1372 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExport1382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleExport1419 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_29_in_ruleExport1437 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExport1478 = new BitSet(new long[]{0x0000000000052000L});
    public static final BitSet FOLLOW_16_in_ruleExport1491 = new BitSet(new long[]{0xA4004880440020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_30_in_ruleExport1509 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExport1544 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_18_in_ruleExport1559 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExport1580 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleExport1592 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleExport1606 = new BitSet(new long[]{0x0000003C80004000L});
    public static final BitSet FOLLOW_31_in_ruleExport1619 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleExport1631 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_32_in_ruleExport1649 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleExport1675 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleExport1687 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExport1711 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleExport1723 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleExport1735 = new BitSet(new long[]{0x0000003C00004000L});
    public static final BitSet FOLLOW_34_in_ruleExport1757 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_35_in_ruleExport1794 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleExport1820 = new BitSet(new long[]{0x0000003000004000L});
    public static final BitSet FOLLOW_36_in_ruleExport1836 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleExport1857 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_21_in_ruleExport1870 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleExport1891 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_22_in_ruleExport1905 = new BitSet(new long[]{0x0000003000004000L});
    public static final BitSet FOLLOW_37_in_ruleExport1925 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleUserData_in_ruleExport1946 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_21_in_ruleExport1959 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleUserData_in_ruleExport1980 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_22_in_ruleExport1994 = new BitSet(new long[]{0x0000003000004000L});
    public static final BitSet FOLLOW_14_in_ruleExport2009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUserData_in_entryRuleUserData2045 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUserData2055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleUserData2097 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleUserData2114 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleUserData2135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute2171 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute2181 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2229 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID2265 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID2276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID2316 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_ruleQualifiedID2335 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID2350 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression2397 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_ruleExpression2457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_ruleExpression2487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_ruleExpression2517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_entryRuleLetExpression2554 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetExpression2564 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleLetExpression2601 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLetExpression2622 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleLetExpression2634 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression2655 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleLetExpression2667 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression2688 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression2724 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCastedExpression2734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleCastedExpression2771 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleCastedExpression2792 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleCastedExpression2804 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCastedExpression2825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_entryRuleChainExpression2861 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainExpression2871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression2921 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_ruleChainExpression2945 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression2966 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression3004 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainedExpression3014 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression3064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression3094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_ruleChainedExpression3124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri3159 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionTri3169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleIfExpressionTri3219 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_ruleIfExpressionTri3243 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3264 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleIfExpressionTri3276 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw3335 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionKw3345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleIfExpressionKw3382 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3403 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_ruleIfExpressionKw3415 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3436 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleIfExpressionKw3449 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression3508 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchExpression3518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleSwitchExpression3555 = new BitSet(new long[]{0x0000000004002000L});
    public static final BitSet FOLLOW_26_in_ruleSwitchExpression3568 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression3589 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleSwitchExpression3601 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleSwitchExpression3615 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_ruleCase_in_ruleSwitchExpression3636 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_47_in_ruleSwitchExpression3649 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleSwitchExpression3661 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression3682 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleSwitchExpression3694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCase_in_entryRuleCase3730 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCase3740 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleCase3777 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase3798 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleCase3810 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase3831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression3867 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression3877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression3927 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_49_in_ruleOrExpression3957 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression3991 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression4029 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression4039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression4089 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50_in_ruleAndExpression4119 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression4153 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression4191 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpression4201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4251 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_ruleImpliesExpression4281 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4315 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression4353 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression4363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4413 = new BitSet(new long[]{0x03F0000000000002L});
    public static final BitSet FOLLOW_52_in_ruleRelationalExpression4445 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_53_in_ruleRelationalExpression4474 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_54_in_ruleRelationalExpression4503 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_55_in_ruleRelationalExpression4532 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_56_in_ruleRelationalExpression4561 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_57_in_ruleRelationalExpression4590 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4627 = new BitSet(new long[]{0x03F0000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression4665 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression4675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4725 = new BitSet(new long[]{0x0400000000800002L});
    public static final BitSet FOLLOW_23_in_ruleAdditiveExpression4757 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_58_in_ruleAdditiveExpression4786 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4823 = new BitSet(new long[]{0x0400000000800002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4861 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression4871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4921 = new BitSet(new long[]{0x1800000000000002L});
    public static final BitSet FOLLOW_59_in_ruleMultiplicativeExpression4953 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_60_in_ruleMultiplicativeExpression4982 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression5019 = new BitSet(new long[]{0x1800000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression5057 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression5067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression5117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression5147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression5182 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression5192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleUnaryExpression5237 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_58_in_ruleUnaryExpression5266 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression5303 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression5339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression5349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression5399 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5424 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression5445 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression5457 = new BitSet(new long[]{0xA40048800C0020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression5479 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_21_in_ruleInfixExpression5492 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression5513 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_27_in_ruleInfixExpression5529 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5561 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression5582 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5614 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleInfixExpression5632 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression5657 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression5678 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleInfixExpression5690 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5722 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000FFL});
    public static final BitSet FOLLOW_64_in_ruleInfixExpression5742 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_65_in_ruleInfixExpression5771 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_66_in_ruleInfixExpression5800 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_67_in_ruleInfixExpression5829 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_68_in_ruleInfixExpression5858 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_69_in_ruleInfixExpression5887 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_70_in_ruleInfixExpression5916 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_71_in_ruleInfixExpression5945 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression5973 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression5995 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleInfixExpression6007 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression6030 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleInfixExpression6042 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression6081 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression6091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression6141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_rulePrimaryExpression6171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_rulePrimaryExpression6201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression6231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression6261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression6291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral6326 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral6336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral6386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral6416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral6446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_ruleLiteral6476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral6506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral6541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral6551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleBooleanLiteral6595 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleBooleanLiteral6624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral6675 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral6685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral6726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral6766 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral6776 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleNullLiteral6818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral6866 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealLiteral6876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_in_ruleRealLiteral6917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral6957 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral6967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral7008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression7048 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression7058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleParanthesizedExpression7095 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression7120 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleParanthesizedExpression7131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression7167 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression7177 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleGlobalVarExpression7214 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression7235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall7271 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureCall7281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_ruleFeatureCall7331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_ruleFeatureCall7357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_ruleFeatureCall7388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall7418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_entryRuleOperationCall7453 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCall7463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleOperationCall7509 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleOperationCall7521 = new BitSet(new long[]{0xA40048800C0020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall7543 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_21_in_ruleOperationCall7556 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall7577 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_27_in_ruleOperationCall7593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_entryRuleListLiteral7629 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListLiteral7639 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleListLiteral7688 = new BitSet(new long[]{0xA4004880040060F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral7710 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_21_in_ruleListLiteral7723 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral7744 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_14_in_ruleListLiteral7760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression7796 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstructorCallExpression7806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleConstructorCallExpression7843 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression7864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression7900 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTypeSelectExpression7910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleTypeSelectExpression7953 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleTypeSelectExpression7978 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleTypeSelectExpression7999 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleTypeSelectExpression8011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression8047 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionExpression8057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleCollectionExpression8102 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_65_in_ruleCollectionExpression8131 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_66_in_ruleCollectionExpression8160 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_67_in_ruleCollectionExpression8189 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_68_in_ruleCollectionExpression8218 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_69_in_ruleCollectionExpression8247 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_70_in_ruleCollectionExpression8276 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_71_in_ruleCollectionExpression8305 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleCollectionExpression8333 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleCollectionExpression8355 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleCollectionExpression8367 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCollectionExpression8390 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleCollectionExpression8402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType8438 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType8448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_ruleType8498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleType8528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_entryRuleCollectionType8563 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionType8573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleCollectionType8618 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_79_in_ruleCollectionType8647 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_80_in_ruleCollectionType8676 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleCollectionType8704 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleCollectionType8725 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCollectionType8737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_entryRuleSimpleType8773 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleType8783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType8829 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_ruleSimpleType8842 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType8863 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier8902 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier8913 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIdentifier8952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_synpred30_InternalExport2487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_synpred35_InternalExport3449 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_synpred35_InternalExport3470 = new BitSet(new long[]{0x0000000000000002L});

}