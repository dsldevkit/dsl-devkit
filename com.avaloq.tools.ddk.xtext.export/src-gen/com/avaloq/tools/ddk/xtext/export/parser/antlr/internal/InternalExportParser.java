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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:76:1: ruleExportModel returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:79:28: ( ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:1: ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:1: ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+ )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:2: ( (lv_imports_0_0= ruleImport ) )+ ( (lv_extensions_1_0= ruleExtension ) )* (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )? ( (lv_exports_6_0= ruleExport ) )+
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:80:2: ( (lv_imports_0_0= ruleImport ) )+
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
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:81:1: (lv_imports_0_0= ruleImport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:81:1: (lv_imports_0_0= ruleImport )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:82:3: lv_imports_0_0= ruleImport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getImportsImportParserRuleCall_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImport_in_ruleExportModel131);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:98:3: ( (lv_extensions_1_0= ruleExtension ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:99:1: (lv_extensions_1_0= ruleExtension )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:99:1: (lv_extensions_1_0= ruleExtension )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:100:3: lv_extensions_1_0= ruleExtension
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getExtensionsExtensionParserRuleCall_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExtension_in_ruleExportModel153);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:116:3: (otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==12) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:116:5: otherlv_2= 'interface' otherlv_3= '{' ( (lv_interfaces_4_0= ruleInterface ) )+ otherlv_5= '}'
                    {
                    otherlv_2=(Token)match(input,12,FOLLOW_12_in_ruleExportModel167); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getExportModelAccess().getInterfaceKeyword_2_0());
                          
                    }
                    otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleExportModel179); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getExportModelAccess().getLeftCurlyBracketKeyword_2_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:124:1: ( (lv_interfaces_4_0= ruleInterface ) )+
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
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:125:1: (lv_interfaces_4_0= ruleInterface )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:125:1: (lv_interfaces_4_0= ruleInterface )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:126:3: lv_interfaces_4_0= ruleInterface
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getExportModelAccess().getInterfacesInterfaceParserRuleCall_2_2_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleInterface_in_ruleExportModel200);
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

                    otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleExportModel213); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_5, grammarAccess.getExportModelAccess().getRightCurlyBracketKeyword_2_3());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:146:3: ( (lv_exports_6_0= ruleExport ) )+
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
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:147:1: (lv_exports_6_0= ruleExport )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:147:1: (lv_exports_6_0= ruleExport )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:148:3: lv_exports_6_0= ruleExport
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportModelAccess().getExportsExportParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExport_in_ruleExportModel236);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:172:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:173:2: (iv_ruleImport= ruleImport EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:174:2: iv_ruleImport= ruleImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImportRule()); 
            }
            pushFollow(FOLLOW_ruleImport_in_entryRuleImport273);
            iv_ruleImport=ruleImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImport283); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:181:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:184:28: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:185:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:185:1: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:185:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleImport320); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:189:1: ( (otherlv_1= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:190:1: (otherlv_1= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:190:1: (otherlv_1= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:191:3: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getImportRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImport340); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0()); 
              	
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:202:2: (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==16) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:202:4: otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleImport353); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getImportAccess().getAsKeyword_2_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:206:1: ( (lv_name_3_0= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:207:1: (lv_name_3_0= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:207:1: (lv_name_3_0= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:208:3: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleImport370); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:232:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:233:2: (iv_ruleExtension= ruleExtension EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:234:2: iv_ruleExtension= ruleExtension EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExtensionRule()); 
            }
            pushFollow(FOLLOW_ruleExtension_in_entryRuleExtension413);
            iv_ruleExtension=ruleExtension();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExtension; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExtension423); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:241:1: ruleExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_extension_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:244:28: ( (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:245:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:245:1: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:245:3: otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_17_in_ruleExtension460); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExtensionAccess().getExtensionKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:249:1: ( (lv_extension_1_0= ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:250:1: (lv_extension_1_0= ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:250:1: (lv_extension_1_0= ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:251:3: lv_extension_1_0= ruleQualifiedID
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExtensionAccess().getExtensionQualifiedIDParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExtension481);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:277:1: entryRuleInterface returns [EObject current=null] : iv_ruleInterface= ruleInterface EOF ;
    public final EObject entryRuleInterface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterface = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:278:2: (iv_ruleInterface= ruleInterface EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:279:2: iv_ruleInterface= ruleInterface EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceRule()); 
            }
            pushFollow(FOLLOW_ruleInterface_in_entryRuleInterface519);
            iv_ruleInterface=ruleInterface();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterface; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterface529); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:286:1: ruleInterface returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:289:28: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:290:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:290:1: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:290:2: ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:290:2: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:291:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:291:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:292:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInterfaceAccess().getTypeEClassCrossReference_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleInterface577);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:305:2: (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==18) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:305:4: otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']'
                    {
                    otherlv_1=(Token)match(input,18,FOLLOW_18_in_ruleInterface590); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getInterfaceAccess().getLeftSquareBracketKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:309:1: ( (lv_guard_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:310:1: (lv_guard_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:310:1: (lv_guard_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:311:3: lv_guard_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getInterfaceAccess().getGuardExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleInterface611);
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

                    otherlv_3=(Token)match(input,19,FOLLOW_19_in_ruleInterface623); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getInterfaceAccess().getRightSquareBracketKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:331:3: (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==20) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:331:5: otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    {
            	    otherlv_4=(Token)match(input,20,FOLLOW_20_in_ruleInterface638); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInterfaceAccess().getEqualsSignKeyword_2_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:335:1: ( (lv_items_5_0= ruleInterfaceItem ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:336:1: (lv_items_5_0= ruleInterfaceItem )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:336:1: (lv_items_5_0= ruleInterfaceItem )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:337:3: lv_items_5_0= ruleInterfaceItem
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleInterfaceItem_in_ruleInterface659);
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:353:2: (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    loop8:
            	    do {
            	        int alt8=2;
            	        int LA8_0 = input.LA(1);

            	        if ( (LA8_0==21) ) {
            	            alt8=1;
            	        }


            	        switch (alt8) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:353:4: otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    {
            	    	    otherlv_6=(Token)match(input,21,FOLLOW_21_in_ruleInterface672); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_6, grammarAccess.getInterfaceAccess().getCommaKeyword_2_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:357:1: ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:358:1: (lv_items_7_0= ruleInterfaceItem )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:358:1: (lv_items_7_0= ruleInterfaceItem )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:359:3: lv_items_7_0= ruleInterfaceItem
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleInterfaceItem_in_ruleInterface693);
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

            otherlv_8=(Token)match(input,22,FOLLOW_22_in_ruleInterface709); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:387:1: entryRuleInterfaceItem returns [EObject current=null] : iv_ruleInterfaceItem= ruleInterfaceItem EOF ;
    public final EObject entryRuleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceItem = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:388:2: (iv_ruleInterfaceItem= ruleInterfaceItem EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:389:2: iv_ruleInterfaceItem= ruleInterfaceItem EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceItemRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceItem_in_entryRuleInterfaceItem745);
            iv_ruleInterfaceItem=ruleInterfaceItem();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceItem; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceItem755); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:396:1: ruleInterfaceItem returns [EObject current=null] : (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) ;
    public final EObject ruleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject this_InterfaceField_0 = null;

        EObject this_InterfaceNavigation_1 = null;

        EObject this_InterfaceExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:399:28: ( (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:400:1: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:400:1: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:401:5: this_InterfaceField_0= ruleInterfaceField
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceFieldParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceField_in_ruleInterfaceItem802);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:411:5: this_InterfaceNavigation_1= ruleInterfaceNavigation
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceNavigationParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceNavigation_in_ruleInterfaceItem829);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:421:5: this_InterfaceExpression_2= ruleInterfaceExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInterfaceExpression_in_ruleInterfaceItem856);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:437:1: entryRuleInterfaceField returns [EObject current=null] : iv_ruleInterfaceField= ruleInterfaceField EOF ;
    public final EObject entryRuleInterfaceField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceField = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:438:2: (iv_ruleInterfaceField= ruleInterfaceField EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:439:2: iv_ruleInterfaceField= ruleInterfaceField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceFieldRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceField_in_entryRuleInterfaceField891);
            iv_ruleInterfaceField=ruleInterfaceField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceField; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceField901); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:446:1: ruleInterfaceField returns [EObject current=null] : ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceField() throws RecognitionException {
        EObject current = null;

        Token lv_unordered_0_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:449:28: ( ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:450:1: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:450:1: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:450:2: ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:450:2: ( (lv_unordered_0_0= '+' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==23) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:451:1: (lv_unordered_0_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:451:1: (lv_unordered_0_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:452:3: lv_unordered_0_0= '+'
                    {
                    lv_unordered_0_0=(Token)match(input,23,FOLLOW_23_in_ruleInterfaceField944); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:465:3: ( (otherlv_1= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:466:1: (otherlv_1= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:466:1: (otherlv_1= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:467:3: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceFieldRule());
              	        }
                      
            }
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterfaceField978); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:486:1: entryRuleInterfaceNavigation returns [EObject current=null] : iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF ;
    public final EObject entryRuleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceNavigation = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:487:2: (iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:488:2: iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceNavigationRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceNavigation_in_entryRuleInterfaceNavigation1014);
            iv_ruleInterfaceNavigation=ruleInterfaceNavigation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceNavigation; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceNavigation1024); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:495:1: ruleInterfaceNavigation returns [EObject current=null] : (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_unordered_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:498:28: ( (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:499:1: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:499:1: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:499:3: otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleInterfaceNavigation1061); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInterfaceNavigationAccess().getCommercialAtKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:503:1: ( (lv_unordered_1_0= '+' ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==23) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:504:1: (lv_unordered_1_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:504:1: (lv_unordered_1_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:505:3: lv_unordered_1_0= '+'
                    {
                    lv_unordered_1_0=(Token)match(input,23,FOLLOW_23_in_ruleInterfaceNavigation1079); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:518:3: ( (otherlv_2= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:519:1: (otherlv_2= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:519:1: (otherlv_2= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:520:3: otherlv_2= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getInterfaceNavigationRule());
              	        }
                      
            }
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleInterfaceNavigation1113); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:539:1: entryRuleInterfaceExpression returns [EObject current=null] : iv_ruleInterfaceExpression= ruleInterfaceExpression EOF ;
    public final EObject entryRuleInterfaceExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:540:2: (iv_ruleInterfaceExpression= ruleInterfaceExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:541:2: iv_ruleInterfaceExpression= ruleInterfaceExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInterfaceExpression_in_entryRuleInterfaceExpression1149);
            iv_ruleInterfaceExpression=ruleInterfaceExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInterfaceExpression1159); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:548:1: ruleInterfaceExpression returns [EObject current=null] : (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:551:28: ( (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:552:1: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:552:1: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:552:3: otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_25_in_ruleInterfaceExpression1196); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getInterfaceExpressionAccess().getEvalKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:556:1: ( (lv_ref_1_0= '@' ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==24) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:557:1: (lv_ref_1_0= '@' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:557:1: (lv_ref_1_0= '@' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:558:3: lv_ref_1_0= '@'
                    {
                    lv_ref_1_0=(Token)match(input,24,FOLLOW_24_in_ruleInterfaceExpression1214); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:571:3: ( (lv_unordered_2_0= '+' ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==23) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:572:1: (lv_unordered_2_0= '+' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:572:1: (lv_unordered_2_0= '+' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:573:3: lv_unordered_2_0= '+'
                    {
                    lv_unordered_2_0=(Token)match(input,23,FOLLOW_23_in_ruleInterfaceExpression1246); if (state.failed) return current;
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

            otherlv_3=(Token)match(input,26,FOLLOW_26_in_ruleInterfaceExpression1272); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_3, grammarAccess.getInterfaceExpressionAccess().getLeftParenthesisKeyword_3());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:590:1: ( (lv_expr_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:591:1: (lv_expr_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:591:1: (lv_expr_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:592:3: lv_expr_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getInterfaceExpressionAccess().getExprExpressionParserRuleCall_4_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleInterfaceExpression1293);
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

            otherlv_5=(Token)match(input,27,FOLLOW_27_in_ruleInterfaceExpression1305); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:620:1: entryRuleExport returns [EObject current=null] : iv_ruleExport= ruleExport EOF ;
    public final EObject entryRuleExport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExport = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:621:2: (iv_ruleExport= ruleExport EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:622:2: iv_ruleExport= ruleExport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExportRule()); 
            }
            pushFollow(FOLLOW_ruleExport_in_entryRuleExport1341);
            iv_ruleExport=ruleExport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExport; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExport1351); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:629:1: ruleExport returns [EObject current=null] : (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:632:28: ( (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:633:1: (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:633:1: (otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:633:3: otherlv_0= 'export' ( (lv_allowLookup_1_0= 'lookup' ) )? ( ( ruleQualifiedID ) ) (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )? (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )? otherlv_9= '{' (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )? ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )? ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )* otherlv_31= '}'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_ruleExport1388); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getExportAccess().getExportKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:637:1: ( (lv_allowLookup_1_0= 'lookup' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==29) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:638:1: (lv_allowLookup_1_0= 'lookup' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:638:1: (lv_allowLookup_1_0= 'lookup' )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:639:3: lv_allowLookup_1_0= 'lookup'
                    {
                    lv_allowLookup_1_0=(Token)match(input,29,FOLLOW_29_in_ruleExport1406); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:652:3: ( ( ruleQualifiedID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:653:1: ( ruleQualifiedID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:653:1: ( ruleQualifiedID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:654:3: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getExportRule());
              	        }
                      
            }
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExportAccess().getTypeEClassCrossReference_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_ruleExport1443);
            ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:667:2: (otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==16) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:667:4: otherlv_3= 'as' ( (lv_qualifiedName_4_0= 'qualified' ) )? ( (lv_naming_5_0= ruleExpression ) )
                    {
                    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleExport1456); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getExportAccess().getAsKeyword_3_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:671:1: ( (lv_qualifiedName_4_0= 'qualified' ) )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0==30) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:672:1: (lv_qualifiedName_4_0= 'qualified' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:672:1: (lv_qualifiedName_4_0= 'qualified' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:673:3: lv_qualifiedName_4_0= 'qualified'
                            {
                            lv_qualifiedName_4_0=(Token)match(input,30,FOLLOW_30_in_ruleExport1474); if (state.failed) return current;
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:686:3: ( (lv_naming_5_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:687:1: (lv_naming_5_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:687:1: (lv_naming_5_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:688:3: lv_naming_5_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExportAccess().getNamingExpressionParserRuleCall_3_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleExport1509);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:704:4: (otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==18) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:704:6: otherlv_6= '[' ( (lv_guard_7_0= ruleExpression ) ) otherlv_8= ']'
                    {
                    otherlv_6=(Token)match(input,18,FOLLOW_18_in_ruleExport1524); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_6, grammarAccess.getExportAccess().getLeftSquareBracketKeyword_4_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:708:1: ( (lv_guard_7_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:709:1: (lv_guard_7_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:709:1: (lv_guard_7_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:710:3: lv_guard_7_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExportAccess().getGuardExpressionParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleExport1545);
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

                    otherlv_8=(Token)match(input,19,FOLLOW_19_in_ruleExport1557); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_8, grammarAccess.getExportAccess().getRightSquareBracketKeyword_4_2());
                          
                    }

                    }
                    break;

            }

            otherlv_9=(Token)match(input,13,FOLLOW_13_in_ruleExport1571); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_9, grammarAccess.getExportAccess().getLeftCurlyBracketKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:734:1: (otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==31) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:734:3: otherlv_10= 'uri-fragment' otherlv_11= '=' ( (lv_fragmentUnique_12_0= 'unique' ) )? otherlv_13= 'attribute' otherlv_14= '(' ( (otherlv_15= RULE_ID ) ) otherlv_16= ')' otherlv_17= ';'
                    {
                    otherlv_10=(Token)match(input,31,FOLLOW_31_in_ruleExport1584); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_10, grammarAccess.getExportAccess().getUriFragmentKeyword_6_0());
                          
                    }
                    otherlv_11=(Token)match(input,20,FOLLOW_20_in_ruleExport1596); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getExportAccess().getEqualsSignKeyword_6_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:742:1: ( (lv_fragmentUnique_12_0= 'unique' ) )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==32) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:743:1: (lv_fragmentUnique_12_0= 'unique' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:743:1: (lv_fragmentUnique_12_0= 'unique' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:744:3: lv_fragmentUnique_12_0= 'unique'
                            {
                            lv_fragmentUnique_12_0=(Token)match(input,32,FOLLOW_32_in_ruleExport1614); if (state.failed) return current;
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

                    otherlv_13=(Token)match(input,33,FOLLOW_33_in_ruleExport1640); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_13, grammarAccess.getExportAccess().getAttributeKeyword_6_3());
                          
                    }
                    otherlv_14=(Token)match(input,26,FOLLOW_26_in_ruleExport1652); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getExportAccess().getLeftParenthesisKeyword_6_4());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:765:1: ( (otherlv_15= RULE_ID ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:766:1: (otherlv_15= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:766:1: (otherlv_15= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:767:3: otherlv_15= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      			if (current==null) {
                      	            current = createModelElement(grammarAccess.getExportRule());
                      	        }
                              
                    }
                    otherlv_15=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExport1672); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		newLeafNode(otherlv_15, grammarAccess.getExportAccess().getFragmentAttributeEAttributeCrossReference_6_5_0()); 
                      	
                    }

                    }


                    }

                    otherlv_16=(Token)match(input,27,FOLLOW_27_in_ruleExport1684); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getExportAccess().getRightParenthesisKeyword_6_6());
                          
                    }
                    otherlv_17=(Token)match(input,22,FOLLOW_22_in_ruleExport1696); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_17, grammarAccess.getExportAccess().getSemicolonKeyword_6_7());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:786:3: ( ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';' )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( ((LA22_0>=34 && LA22_0<=35)) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:786:4: ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) ) otherlv_20= ';'
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:786:4: ( ( (lv_fingerprint_18_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) ) )
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
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:786:5: ( (lv_fingerprint_18_0= 'object-fingerprint' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:786:5: ( (lv_fingerprint_18_0= 'object-fingerprint' ) )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:787:1: (lv_fingerprint_18_0= 'object-fingerprint' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:787:1: (lv_fingerprint_18_0= 'object-fingerprint' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:788:3: lv_fingerprint_18_0= 'object-fingerprint'
                            {
                            lv_fingerprint_18_0=(Token)match(input,34,FOLLOW_34_in_ruleExport1718); if (state.failed) return current;
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
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:802:6: ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:802:6: ( (lv_resourceFingerprint_19_0= 'resource-fingerprint' ) )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:803:1: (lv_resourceFingerprint_19_0= 'resource-fingerprint' )
                            {
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:803:1: (lv_resourceFingerprint_19_0= 'resource-fingerprint' )
                            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:804:3: lv_resourceFingerprint_19_0= 'resource-fingerprint'
                            {
                            lv_resourceFingerprint_19_0=(Token)match(input,35,FOLLOW_35_in_ruleExport1755); if (state.failed) return current;
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

                    otherlv_20=(Token)match(input,22,FOLLOW_22_in_ruleExport1781); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_20, grammarAccess.getExportAccess().getSemicolonKeyword_7_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:821:3: ( (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' ) | (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' ) )*
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
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:821:4: (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:821:4: (otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:821:6: otherlv_21= 'field' ( (lv_attributes_22_0= ruleAttribute ) ) (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )* otherlv_25= ';'
            	    {
            	    otherlv_21=(Token)match(input,36,FOLLOW_36_in_ruleExport1797); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getExportAccess().getFieldKeyword_8_0_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:825:1: ( (lv_attributes_22_0= ruleAttribute ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:826:1: (lv_attributes_22_0= ruleAttribute )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:826:1: (lv_attributes_22_0= ruleAttribute )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:827:3: lv_attributes_22_0= ruleAttribute
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAttribute_in_ruleExport1818);
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:843:2: (otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) ) )*
            	    loop23:
            	    do {
            	        int alt23=2;
            	        int LA23_0 = input.LA(1);

            	        if ( (LA23_0==21) ) {
            	            alt23=1;
            	        }


            	        switch (alt23) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:843:4: otherlv_23= ',' ( (lv_attributes_24_0= ruleAttribute ) )
            	    	    {
            	    	    otherlv_23=(Token)match(input,21,FOLLOW_21_in_ruleExport1831); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_23, grammarAccess.getExportAccess().getCommaKeyword_8_0_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:847:1: ( (lv_attributes_24_0= ruleAttribute ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:848:1: (lv_attributes_24_0= ruleAttribute )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:848:1: (lv_attributes_24_0= ruleAttribute )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:849:3: lv_attributes_24_0= ruleAttribute
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleAttribute_in_ruleExport1852);
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

            	    otherlv_25=(Token)match(input,22,FOLLOW_22_in_ruleExport1866); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getExportAccess().getSemicolonKeyword_8_0_3());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:870:6: (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:870:6: (otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:870:8: otherlv_26= 'data' ( (lv_userData_27_0= ruleUserData ) ) (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )* otherlv_30= ';'
            	    {
            	    otherlv_26=(Token)match(input,37,FOLLOW_37_in_ruleExport1886); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_26, grammarAccess.getExportAccess().getDataKeyword_8_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:874:1: ( (lv_userData_27_0= ruleUserData ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:875:1: (lv_userData_27_0= ruleUserData )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:875:1: (lv_userData_27_0= ruleUserData )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:876:3: lv_userData_27_0= ruleUserData
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUserData_in_ruleExport1907);
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:892:2: (otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) ) )*
            	    loop24:
            	    do {
            	        int alt24=2;
            	        int LA24_0 = input.LA(1);

            	        if ( (LA24_0==21) ) {
            	            alt24=1;
            	        }


            	        switch (alt24) {
            	    	case 1 :
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:892:4: otherlv_28= ',' ( (lv_userData_29_0= ruleUserData ) )
            	    	    {
            	    	    otherlv_28=(Token)match(input,21,FOLLOW_21_in_ruleExport1920); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	          	newLeafNode(otherlv_28, grammarAccess.getExportAccess().getCommaKeyword_8_1_2_0());
            	    	          
            	    	    }
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:896:1: ( (lv_userData_29_0= ruleUserData ) )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:897:1: (lv_userData_29_0= ruleUserData )
            	    	    {
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:897:1: (lv_userData_29_0= ruleUserData )
            	    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:898:3: lv_userData_29_0= ruleUserData
            	    	    {
            	    	    if ( state.backtracking==0 ) {
            	    	       
            	    	      	        newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_2_1_0()); 
            	    	      	    
            	    	    }
            	    	    pushFollow(FOLLOW_ruleUserData_in_ruleExport1941);
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

            	    otherlv_30=(Token)match(input,22,FOLLOW_22_in_ruleExport1955); if (state.failed) return current;
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

            otherlv_31=(Token)match(input,14,FOLLOW_14_in_ruleExport1970); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:930:1: entryRuleUserData returns [EObject current=null] : iv_ruleUserData= ruleUserData EOF ;
    public final EObject entryRuleUserData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUserData = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:931:2: (iv_ruleUserData= ruleUserData EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:932:2: iv_ruleUserData= ruleUserData EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUserDataRule()); 
            }
            pushFollow(FOLLOW_ruleUserData_in_entryRuleUserData2006);
            iv_ruleUserData=ruleUserData();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUserData; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUserData2016); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:939:1: ruleUserData returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) ;
    public final EObject ruleUserData() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:942:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:943:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:943:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:943:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:943:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:944:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:944:1: (lv_name_0_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:945:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleUserData2058); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleUserData2075); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getUserDataAccess().getEqualsSignKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:965:1: ( (lv_expr_2_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:966:1: (lv_expr_2_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:966:1: (lv_expr_2_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:967:3: lv_expr_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUserDataAccess().getExprExpressionParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleUserData2096);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:991:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:992:2: (iv_ruleAttribute= ruleAttribute EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:993:2: iv_ruleAttribute= ruleAttribute EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAttributeRule()); 
            }
            pushFollow(FOLLOW_ruleAttribute_in_entryRuleAttribute2132);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAttribute; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAttribute2142); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1000:1: ruleAttribute returns [EObject current=null] : ( (otherlv_0= RULE_ID ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1003:28: ( ( (otherlv_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1004:1: ( (otherlv_0= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1004:1: ( (otherlv_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1005:1: (otherlv_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1005:1: (otherlv_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1006:3: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              			if (current==null) {
              	            current = createModelElement(grammarAccess.getAttributeRule());
              	        }
                      
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAttribute2186); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1025:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1026:2: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1027:2: iv_ruleQualifiedID= ruleQualifiedID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedIDRule()); 
            }
            pushFollow(FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID2222);
            iv_ruleQualifiedID=ruleQualifiedID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedID.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleQualifiedID2233); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1034:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1037:28: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1038:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1038:1: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1038:6: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID2273); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0()); 
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1045:1: (kw= '::' this_ID_2= RULE_ID )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==38) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1046:2: kw= '::' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,38,FOLLOW_38_in_ruleQualifiedID2292); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	              current.merge(kw);
            	              newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_1_0()); 
            	          
            	    }
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleQualifiedID2307); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1066:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1067:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1068:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression2354);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2364); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1075:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1078:28: ( (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1079:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1079:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            int alt27=3;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1080:5: this_LetExpression_0= ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLetExpression_in_ruleExpression2411);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1089:6: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1089:6: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1089:7: ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCastedExpression_in_ruleExpression2444);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1100:5: this_ChainExpression_2= ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleChainExpression_in_ruleExpression2472);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1118:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1119:2: (iv_ruleLetExpression= ruleLetExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1120:2: iv_ruleLetExpression= ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLetExpression_in_entryRuleLetExpression2509);
            iv_ruleLetExpression=ruleLetExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetExpression2519); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1127:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1130:28: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1131:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1131:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1131:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,39,FOLLOW_39_in_ruleLetExpression2556); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1135:1: ( (lv_identifier_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1136:1: (lv_identifier_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1136:1: (lv_identifier_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1137:3: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLetExpression2577);
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

            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleLetExpression2589); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1157:1: ( (lv_varExpr_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1158:1: (lv_varExpr_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1158:1: (lv_varExpr_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1159:3: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression2610);
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

            otherlv_4=(Token)match(input,40,FOLLOW_40_in_ruleLetExpression2622); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1179:1: ( (lv_target_5_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1180:1: (lv_target_5_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1180:1: (lv_target_5_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1181:3: lv_target_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression2643);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1205:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1206:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1207:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression2679);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCastedExpression2689); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1214:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1217:28: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1218:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1218:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1218:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleCastedExpression2726); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1222:1: ( (lv_type_1_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1223:1: (lv_type_1_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1223:1: (lv_type_1_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1224:3: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleCastedExpression2747);
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

            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleCastedExpression2759); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1244:1: ( (lv_target_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1245:1: (lv_target_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1245:1: (lv_target_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1246:3: lv_target_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCastedExpression2780);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1270:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1271:2: (iv_ruleChainExpression= ruleChainExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1272:2: iv_ruleChainExpression= ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainExpression_in_entryRuleChainExpression2816);
            iv_ruleChainExpression=ruleChainExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainExpression2826); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1279:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1282:28: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1283:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1283:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1284:5: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression2873);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ChainedExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1292:1: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==41) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1292:2: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1292:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1293:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,41,FOLLOW_41_in_ruleChainExpression2894); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1302:1: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1303:1: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1303:1: (lv_next_3_0= ruleChainedExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1304:3: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression2915);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1328:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1329:2: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1330:2: iv_ruleChainedExpression= ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression2953);
            iv_ruleChainedExpression=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainedExpression2963); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1337:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1340:28: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1341:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1341:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1342:5: this_IfExpressionKw_0= ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression3010);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1352:5: this_IfExpressionTri_1= ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression3037);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1362:5: this_SwitchExpression_2= ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSwitchExpression_in_ruleChainedExpression3064);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1378:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1379:2: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1380:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri3099);
            iv_ruleIfExpressionTri=ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionTri; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionTri3109); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1387:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1390:28: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1391:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1391:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1392:5: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleIfExpressionTri3156);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1400:1: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==42) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1400:2: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1400:2: ()
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1401:5: 
                    {
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0(),
                                  current);
                          
                    }

                    }

                    otherlv_2=(Token)match(input,42,FOLLOW_42_in_ruleIfExpressionTri3177); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1410:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1411:1: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1411:1: (lv_thenPart_3_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1412:3: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3198);
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

                    otherlv_4=(Token)match(input,40,FOLLOW_40_in_ruleIfExpressionTri3210); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1432:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1433:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1433:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1434:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3231);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1458:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1459:2: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1460:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw3269);
            iv_ruleIfExpressionKw=ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionKw; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionKw3279); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1467:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1470:28: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1471:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1471:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1471:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            {
            otherlv_0=(Token)match(input,43,FOLLOW_43_in_ruleIfExpressionKw3316); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1475:1: ( (lv_condition_1_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1476:1: (lv_condition_1_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1476:1: (lv_condition_1_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1477:3: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3337);
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

            otherlv_2=(Token)match(input,44,FOLLOW_44_in_ruleIfExpressionKw3349); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1497:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1498:1: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1498:1: (lv_thenPart_3_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1499:3: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3370);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1515:2: ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==45) ) {
                int LA31_1 = input.LA(2);

                if ( (synpred2_InternalExport()) ) {
                    alt31=1;
                }
            }
            switch (alt31) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1515:3: ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1516:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1516:6: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,45,FOLLOW_45_in_ruleIfExpressionKw3391); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1520:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1521:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1521:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1522:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3412);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1546:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1547:2: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1548:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression3451);
            iv_ruleSwitchExpression=ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSwitchExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchExpression3461); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1555:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1558:28: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1559:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1559:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1559:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,46,FOLLOW_46_in_ruleSwitchExpression3498); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1563:1: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==26) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1563:3: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleSwitchExpression3511); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1567:1: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1568:1: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1568:1: (lv_switchExpr_2_0= ruleOrExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1569:3: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression3532);
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

                    otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleSwitchExpression3544); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleSwitchExpression3558); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1593:1: ( (lv_case_5_0= ruleCase ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==48) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1594:1: (lv_case_5_0= ruleCase )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1594:1: (lv_case_5_0= ruleCase )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1595:3: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleCase_in_ruleSwitchExpression3579);
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

            otherlv_6=(Token)match(input,47,FOLLOW_47_in_ruleSwitchExpression3592); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
                  
            }
            otherlv_7=(Token)match(input,40,FOLLOW_40_in_ruleSwitchExpression3604); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1619:1: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1620:1: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1620:1: (lv_defaultExpr_8_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1621:3: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression3625);
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

            otherlv_9=(Token)match(input,14,FOLLOW_14_in_ruleSwitchExpression3637); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1649:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1650:2: (iv_ruleCase= ruleCase EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1651:2: iv_ruleCase= ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_ruleCase_in_entryRuleCase3673);
            iv_ruleCase=ruleCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCase; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCase3683); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1658:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1661:28: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1662:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1662:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1662:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,48,FOLLOW_48_in_ruleCase3720); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1666:1: ( (lv_condition_1_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1667:1: (lv_condition_1_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1667:1: (lv_condition_1_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1668:3: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase3741);
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

            otherlv_2=(Token)match(input,40,FOLLOW_40_in_ruleCase3753); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1688:1: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1689:1: (lv_thenPar_3_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1689:1: (lv_thenPar_3_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1690:3: lv_thenPar_3_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase3774);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1714:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1715:2: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1716:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression3810);
            iv_ruleOrExpression=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression3820); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1723:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1726:28: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1727:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1727:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1728:5: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression3867);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1736:1: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==49) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1736:2: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1736:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1737:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1742:2: ( (lv_operator_2_0= '||' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1743:1: (lv_operator_2_0= '||' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1743:1: (lv_operator_2_0= '||' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1744:3: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,49,FOLLOW_49_in_ruleOrExpression3894); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1757:2: ( (lv_right_3_0= ruleAndExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1758:1: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1758:1: (lv_right_3_0= ruleAndExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1759:3: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression3928);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1783:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1784:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1785:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression3966);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression3976); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1792:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1795:28: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1796:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1796:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1797:5: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression4023);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1805:1: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==50) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1805:2: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1805:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1806:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1811:2: ( (lv_operator_2_0= '&&' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1812:1: (lv_operator_2_0= '&&' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1812:1: (lv_operator_2_0= '&&' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1813:3: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,50,FOLLOW_50_in_ruleAndExpression4050); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1826:2: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1827:1: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1827:1: (lv_right_3_0= ruleImpliesExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1828:3: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression4084);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1852:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1853:2: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1854:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression4122);
            iv_ruleImpliesExpression=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpression4132); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1861:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1864:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1865:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1865:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1866:5: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4179);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1874:1: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==51) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1874:2: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1874:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1875:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1880:2: ( (lv_operator_2_0= 'implies' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1881:1: (lv_operator_2_0= 'implies' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1881:1: (lv_operator_2_0= 'implies' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1882:3: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,51,FOLLOW_51_in_ruleImpliesExpression4206); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1895:2: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1896:1: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1896:1: (lv_right_3_0= ruleRelationalExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1897:3: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4240);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1921:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1922:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1923:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression4278);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression4288); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1930:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1933:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1934:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1934:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1935:5: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4335);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1943:1: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( ((LA38_0>=52 && LA38_0<=57)) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1943:2: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1943:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1944:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1949:2: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1950:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1950:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1951:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1951:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1952:3: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,52,FOLLOW_52_in_ruleRelationalExpression4364); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1964:8: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,53,FOLLOW_53_in_ruleRelationalExpression4393); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1976:8: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,54,FOLLOW_54_in_ruleRelationalExpression4422); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1988:8: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,55,FOLLOW_55_in_ruleRelationalExpression4451); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2000:8: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,56,FOLLOW_56_in_ruleRelationalExpression4480); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2012:8: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,57,FOLLOW_57_in_ruleRelationalExpression4509); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2027:2: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2028:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2028:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2029:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4546);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2053:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2054:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2055:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression4584);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression4594); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2062:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2065:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2066:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2066:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2067:5: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4641);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2075:1: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==23||LA40_0==58) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2075:2: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2075:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2076:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2081:2: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2082:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2082:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2083:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2083:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2084:3: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,23,FOLLOW_23_in_ruleAdditiveExpression4670); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2096:8: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,58,FOLLOW_58_in_ruleAdditiveExpression4699); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2111:2: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2112:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2112:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2113:3: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4736);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2137:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2138:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2139:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4774);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression4784); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2146:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2149:28: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2150:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2150:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2151:5: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4831);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryOrInfixExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2159:1: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=59 && LA42_0<=60)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2159:2: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2159:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2160:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndAdd(
            	                  grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2165:2: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2166:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2166:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2167:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2167:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2168:3: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,59,FOLLOW_59_in_ruleMultiplicativeExpression4860); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2180:8: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,60,FOLLOW_60_in_ruleMultiplicativeExpression4889); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2195:2: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2196:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2196:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2197:3: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4926);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2221:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2222:2: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2223:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression4964);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOrInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression4974); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2230:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2233:28: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2234:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2234:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2235:5: this_UnaryExpression_0= ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression5021);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2245:5: this_InfixExpression_1= ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression5048);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2261:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2262:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2263:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression5083);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression5093); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2270:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2273:28: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2274:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2274:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2274:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2274:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2275:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2275:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2276:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2276:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2277:3: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,61,FOLLOW_61_in_ruleUnaryExpression5138); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2289:8: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,58,FOLLOW_58_in_ruleUnaryExpression5167); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2304:2: ( (lv_params_1_0= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2305:1: (lv_params_1_0= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2305:1: (lv_params_1_0= ruleInfixExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2306:3: lv_params_1_0= ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression5204);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2330:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2331:2: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2332:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression5240);
            iv_ruleInfixExpression=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression5250); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2339:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2342:28: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2343:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2343:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2344:5: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression5297);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2352:1: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop49:
            do {
                int alt49=5;
                int LA49_0 = input.LA(1);

                if ( (LA49_0==62) ) {
                    switch ( input.LA(2) ) {
                    case RULE_ID:
                        {
                        int LA49_3 = input.LA(3);

                        if ( (LA49_3==EOF||(LA49_3>=13 && LA49_3<=14)||(LA49_3>=18 && LA49_3<=19)||(LA49_3>=21 && LA49_3<=23)||LA49_3==27||LA49_3==38||(LA49_3>=40 && LA49_3<=42)||(LA49_3>=44 && LA49_3<=45)||(LA49_3>=47 && LA49_3<=60)||LA49_3==62) ) {
                            alt49=2;
                        }
                        else if ( (LA49_3==26) ) {
                            alt49=1;
                        }


                        }
                        break;
                    case 78:
                    case 79:
                    case 80:
                        {
                        alt49=2;
                        }
                        break;
                    case 63:
                        {
                        alt49=3;
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
                        alt49=4;
                        }
                        break;

                    }

                }


                switch (alt49) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2352:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2352:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2352:3: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2352:3: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2353:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_2=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5319); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2362:1: ( (lv_name_3_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2363:1: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2363:1: (lv_name_3_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2364:3: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression5340);
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

            	    otherlv_4=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression5352); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2384:1: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt46=2;
            	    int LA46_0 = input.LA(1);

            	    if ( ((LA46_0>=RULE_STRING && LA46_0<=RULE_REAL)||LA46_0==13||LA46_0==26||LA46_0==39||LA46_0==43||LA46_0==46||LA46_0==58||LA46_0==61||(LA46_0>=63 && LA46_0<=71)||(LA46_0>=73 && LA46_0<=80)) ) {
            	        alt46=1;
            	    }
            	    switch (alt46) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2384:2: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2384:2: ( (lv_params_5_0= ruleExpression ) )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2385:1: (lv_params_5_0= ruleExpression )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2385:1: (lv_params_5_0= ruleExpression )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2386:3: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression5374);
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

            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2402:2: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop45:
            	            do {
            	                int alt45=2;
            	                int LA45_0 = input.LA(1);

            	                if ( (LA45_0==21) ) {
            	                    alt45=1;
            	                }


            	                switch (alt45) {
            	            	case 1 :
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2402:4: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,21,FOLLOW_21_in_ruleInfixExpression5387); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	          
            	            	    }
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2406:1: ( (lv_params_7_0= ruleExpression ) )
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2407:1: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2407:1: (lv_params_7_0= ruleExpression )
            	            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2408:3: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression5408);
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

            	    otherlv_8=(Token)match(input,27,FOLLOW_27_in_ruleInfixExpression5424); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2429:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2429:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2429:7: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2429:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2430:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_10=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5453); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2439:1: ( (lv_type_11_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2440:1: (lv_type_11_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2440:1: (lv_type_11_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2441:3: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression5474);
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
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2458:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2458:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2458:7: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2458:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2459:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_13=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5503); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2468:1: ( (lv_name_14_0= 'typeSelect' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2469:1: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2469:1: (lv_name_14_0= 'typeSelect' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2470:3: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,63,FOLLOW_63_in_ruleInfixExpression5521); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression5546); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2487:1: ( (lv_type_16_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2488:1: (lv_type_16_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2488:1: (lv_type_16_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2489:3: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression5567);
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

            	    otherlv_17=(Token)match(input,27,FOLLOW_27_in_ruleInfixExpression5579); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2510:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2510:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2510:7: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2510:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2511:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0(),
            	                  current);
            	          
            	    }

            	    }

            	    otherlv_19=(Token)match(input,62,FOLLOW_62_in_ruleInfixExpression5608); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2520:1: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2521:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2521:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2522:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2522:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2523:3: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,64,FOLLOW_64_in_ruleInfixExpression5628); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2535:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,65,FOLLOW_65_in_ruleInfixExpression5657); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2547:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,66,FOLLOW_66_in_ruleInfixExpression5686); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2559:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,67,FOLLOW_67_in_ruleInfixExpression5715); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2571:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,68,FOLLOW_68_in_ruleInfixExpression5744); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2583:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,69,FOLLOW_69_in_ruleInfixExpression5773); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2595:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,70,FOLLOW_70_in_ruleInfixExpression5802); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2607:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,71,FOLLOW_71_in_ruleInfixExpression5831); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,26,FOLLOW_26_in_ruleInfixExpression5859); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2626:1: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
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
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2626:2: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2626:2: ( (lv_var_22_0= ruleIdentifier ) )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2627:1: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2627:1: (lv_var_22_0= ruleIdentifier )
            	            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2628:3: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression5881);
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

            	            otherlv_23=(Token)match(input,72,FOLLOW_72_in_ruleInfixExpression5893); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2648:3: ( (lv_exp_24_0= ruleExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2649:1: (lv_exp_24_0= ruleExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2649:1: (lv_exp_24_0= ruleExpression )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2650:3: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression5916);
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

            	    otherlv_25=(Token)match(input,27,FOLLOW_27_in_ruleInfixExpression5928); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2678:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2679:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2680:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression5967);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression5977); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2687:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2690:28: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2691:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2691:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2692:5: this_Literal_0= ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression6024);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2702:5: this_FeatureCall_1= ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFeatureCall_in_rulePrimaryExpression6051);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2712:5: this_ListLiteral_2= ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListLiteral_in_rulePrimaryExpression6078);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2722:5: this_ConstructorCallExpression_3= ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression6105);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2732:5: this_GlobalVarExpression_4= ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression6132);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2742:5: this_ParanthesizedExpression_5= ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression6159);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2758:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2759:2: (iv_ruleLiteral= ruleLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2760:2: iv_ruleLiteral= ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral6194);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral6204); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2767:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2770:28: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2771:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2771:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2772:5: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral6251);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2782:5: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral6278);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2792:5: this_NullLiteral_2= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral6305);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2802:5: this_RealLiteral_3= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealLiteral_in_ruleLiteral6332);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2812:5: this_StringLiteral_4= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral6359);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2828:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2829:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2830:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral6394);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral6404); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2837:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2840:28: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2841:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2841:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2842:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2842:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2843:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2843:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2844:3: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,73,FOLLOW_73_in_ruleBooleanLiteral6448); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2856:8: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,74,FOLLOW_74_in_ruleBooleanLiteral6477); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2879:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2880:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2881:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral6528);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral6538); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2888:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2891:28: ( ( (lv_val_0_0= RULE_INT ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2892:1: ( (lv_val_0_0= RULE_INT ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2892:1: ( (lv_val_0_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2893:1: (lv_val_0_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2893:1: (lv_val_0_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2894:3: lv_val_0_0= RULE_INT
            {
            lv_val_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral6579); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2918:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2919:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2920:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral6619);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral6629); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2927:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2930:28: ( ( (lv_val_0_0= 'null' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2931:1: ( (lv_val_0_0= 'null' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2931:1: ( (lv_val_0_0= 'null' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2932:1: (lv_val_0_0= 'null' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2932:1: (lv_val_0_0= 'null' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2933:3: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,75,FOLLOW_75_in_ruleNullLiteral6671); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2954:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2955:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2956:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral6719);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealLiteral6729); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2963:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2966:28: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2967:1: ( (lv_val_0_0= RULE_REAL ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2967:1: ( (lv_val_0_0= RULE_REAL ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2968:1: (lv_val_0_0= RULE_REAL )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2968:1: (lv_val_0_0= RULE_REAL )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2969:3: lv_val_0_0= RULE_REAL
            {
            lv_val_0_0=(Token)match(input,RULE_REAL,FOLLOW_RULE_REAL_in_ruleRealLiteral6770); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2993:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2994:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:2995:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral6810);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral6820); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3002:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3005:28: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3006:1: ( (lv_val_0_0= RULE_STRING ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3006:1: ( (lv_val_0_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3007:1: (lv_val_0_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3007:1: (lv_val_0_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3008:3: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral6861); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3032:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3033:2: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3034:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression6901);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParanthesizedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression6911); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3041:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3044:28: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3045:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3045:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3045:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleParanthesizedExpression6948); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression6970);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleParanthesizedExpression6981); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3070:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3071:2: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3072:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression7017);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalVarExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression7027); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3079:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3082:28: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3083:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3083:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3083:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,76,FOLLOW_76_in_ruleGlobalVarExpression7064); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3087:1: ( (lv_name_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3088:1: (lv_name_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3088:1: (lv_name_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3089:3: lv_name_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression7085);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3113:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3114:2: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3115:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall7121);
            iv_ruleFeatureCall=ruleFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureCall7131); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3122:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3125:28: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3126:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3126:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3127:5: this_OperationCall_0= ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleOperationCall_in_ruleFeatureCall7178);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3136:6: ( (lv_type_1_0= ruleType ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3136:6: ( (lv_type_1_0= ruleType ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3137:1: (lv_type_1_0= ruleType )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3137:1: (lv_type_1_0= ruleType )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3138:3: lv_type_1_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleFeatureCall7204);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3156:5: this_CollectionExpression_2= ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionExpression_in_ruleFeatureCall7232);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3166:5: this_TypeSelectExpression_3= ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall7259);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3182:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3183:2: (iv_ruleOperationCall= ruleOperationCall EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3184:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_ruleOperationCall_in_entryRuleOperationCall7294);
            iv_ruleOperationCall=ruleOperationCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCall7304); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3191:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3194:28: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3195:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3195:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3195:2: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3195:2: ( (lv_name_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3196:1: (lv_name_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3196:1: (lv_name_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3197:3: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleOperationCall7350);
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

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleOperationCall7362); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3217:1: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( ((LA55_0>=RULE_STRING && LA55_0<=RULE_REAL)||LA55_0==13||LA55_0==26||LA55_0==39||LA55_0==43||LA55_0==46||LA55_0==58||LA55_0==61||(LA55_0>=63 && LA55_0<=71)||(LA55_0>=73 && LA55_0<=80)) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3217:2: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3217:2: ( (lv_params_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3218:1: (lv_params_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3218:1: (lv_params_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3219:3: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall7384);
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3235:2: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==21) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3235:4: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleOperationCall7397); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3239:1: ( (lv_params_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3240:1: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3240:1: (lv_params_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3241:3: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall7418);
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

            otherlv_5=(Token)match(input,27,FOLLOW_27_in_ruleOperationCall7434); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3269:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3270:2: (iv_ruleListLiteral= ruleListLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3271:2: iv_ruleListLiteral= ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleListLiteral_in_entryRuleListLiteral7470);
            iv_ruleListLiteral=ruleListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListLiteral7480); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3278:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3281:28: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3282:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3282:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3282:2: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3282:2: ()
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3283:5: 
            {
            if ( state.backtracking==0 ) {

                      current = forceCreateModelElement(
                          grammarAccess.getListLiteralAccess().getListLiteralAction_0(),
                          current);
                  
            }

            }

            otherlv_1=(Token)match(input,13,FOLLOW_13_in_ruleListLiteral7526); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3292:1: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( ((LA57_0>=RULE_STRING && LA57_0<=RULE_REAL)||LA57_0==13||LA57_0==26||LA57_0==39||LA57_0==43||LA57_0==46||LA57_0==58||LA57_0==61||(LA57_0>=63 && LA57_0<=71)||(LA57_0>=73 && LA57_0<=80)) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3292:2: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3292:2: ( (lv_elements_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3293:1: (lv_elements_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3293:1: (lv_elements_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3294:3: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral7548);
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

                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3310:2: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop56:
                    do {
                        int alt56=2;
                        int LA56_0 = input.LA(1);

                        if ( (LA56_0==21) ) {
                            alt56=1;
                        }


                        switch (alt56) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3310:4: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,21,FOLLOW_21_in_ruleListLiteral7561); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3314:1: ( (lv_elements_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3315:1: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3315:1: (lv_elements_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3316:3: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral7582);
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

            otherlv_5=(Token)match(input,14,FOLLOW_14_in_ruleListLiteral7598); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3344:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3345:2: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3346:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression7634);
            iv_ruleConstructorCallExpression=ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorCallExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstructorCallExpression7644); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3353:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3356:28: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3357:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3357:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3357:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,77,FOLLOW_77_in_ruleConstructorCallExpression7681); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3361:1: ( (lv_type_1_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3362:1: (lv_type_1_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3362:1: (lv_type_1_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3363:3: lv_type_1_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression7702);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3387:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3388:2: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3389:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression7738);
            iv_ruleTypeSelectExpression=ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeSelectExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTypeSelectExpression7748); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3396:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3399:28: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3400:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3400:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3400:2: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3400:2: ( (lv_name_0_0= 'typeSelect' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3401:1: (lv_name_0_0= 'typeSelect' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3401:1: (lv_name_0_0= 'typeSelect' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3402:3: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,63,FOLLOW_63_in_ruleTypeSelectExpression7791); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleTypeSelectExpression7816); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3419:1: ( (lv_type_2_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3420:1: (lv_type_2_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3420:1: (lv_type_2_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3421:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleTypeSelectExpression7837);
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

            otherlv_3=(Token)match(input,27,FOLLOW_27_in_ruleTypeSelectExpression7849); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3449:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3450:2: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3451:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression7885);
            iv_ruleCollectionExpression=ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionExpression7895); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3458:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3461:28: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3462:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3462:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3462:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3462:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3463:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3463:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3464:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3464:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3465:3: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,64,FOLLOW_64_in_ruleCollectionExpression7940); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3477:8: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,65,FOLLOW_65_in_ruleCollectionExpression7969); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3489:8: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,66,FOLLOW_66_in_ruleCollectionExpression7998); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3501:8: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,67,FOLLOW_67_in_ruleCollectionExpression8027); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3513:8: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,68,FOLLOW_68_in_ruleCollectionExpression8056); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3525:8: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,69,FOLLOW_69_in_ruleCollectionExpression8085); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3537:8: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,70,FOLLOW_70_in_ruleCollectionExpression8114); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3549:8: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,71,FOLLOW_71_in_ruleCollectionExpression8143); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleCollectionExpression8171); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3568:1: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3568:2: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3568:2: ( (lv_var_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3569:1: (lv_var_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3569:1: (lv_var_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3570:3: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleCollectionExpression8193);
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

                    otherlv_3=(Token)match(input,72,FOLLOW_72_in_ruleCollectionExpression8205); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3590:3: ( (lv_exp_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3591:1: (lv_exp_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3591:1: (lv_exp_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3592:3: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCollectionExpression8228);
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

            otherlv_5=(Token)match(input,27,FOLLOW_27_in_ruleCollectionExpression8240); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3620:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3621:2: (iv_ruleType= ruleType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3622:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType8276);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType8286); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3629:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3632:28: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3633:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3633:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3634:5: this_CollectionType_0= ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionType_in_ruleType8333);
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3644:5: this_SimpleType_1= ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleType_in_ruleType8360);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3660:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3661:2: (iv_ruleCollectionType= ruleCollectionType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3662:2: iv_ruleCollectionType= ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionType_in_entryRuleCollectionType8395);
            iv_ruleCollectionType=ruleCollectionType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionType8405); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3669:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3672:28: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3673:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3673:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3673:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3673:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3674:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3674:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3675:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3675:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3676:3: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,78,FOLLOW_78_in_ruleCollectionType8450); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3688:8: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,79,FOLLOW_79_in_ruleCollectionType8479); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3700:8: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,80,FOLLOW_80_in_ruleCollectionType8508); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,18,FOLLOW_18_in_ruleCollectionType8536); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3719:1: ( (lv_id1_2_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3720:1: (lv_id1_2_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3720:1: (lv_id1_2_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3721:3: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleCollectionType8557);
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

            otherlv_3=(Token)match(input,19,FOLLOW_19_in_ruleCollectionType8569); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3749:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3750:2: (iv_ruleSimpleType= ruleSimpleType EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3751:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleType_in_entryRuleSimpleType8605);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleType8615); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3758:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3761:28: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3762:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3762:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3762:2: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3762:2: ( (lv_id_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3763:1: (lv_id_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3763:1: (lv_id_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3764:3: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType8661);
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

            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3780:2: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop62:
            do {
                int alt62=2;
                int LA62_0 = input.LA(1);

                if ( (LA62_0==38) ) {
                    alt62=1;
                }


                switch (alt62) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3780:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,38,FOLLOW_38_in_ruleSimpleType8674); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3784:1: ( (lv_id_2_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3785:1: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3785:1: (lv_id_2_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3786:3: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType8695);
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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3810:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3811:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3812:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier8734);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier8745); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3819:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3822:28: (this_ID_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:3823:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIdentifier8784); if (state.failed) return current;
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
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1089:7: ( ruleCastedExpression )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1089:9: ruleCastedExpression
        {
        pushFollow(FOLLOW_ruleCastedExpression_in_synpred1_InternalExport2428);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalExport

    // $ANTLR start synpred2_InternalExport
    public final void synpred2_InternalExport_fragment() throws RecognitionException {   
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1515:3: ( 'else' )
        // ../com.avaloq.tools.ddk.xtext.export/src-gen/com/avaloq/tools/ddk/xtext/export/parser/antlr/internal/InternalExport.g:1515:5: 'else'
        {
        match(input,45,FOLLOW_45_in_synpred2_InternalExport3382); if (state.failed) return ;

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
            return "1079:1: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )";
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
                        if ( (synpred1_InternalExport()) ) {s = 29;}

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
 

    public static final BitSet FOLLOW_ruleExportModel_in_entryRuleExportModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExportModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImport_in_ruleExportModel131 = new BitSet(new long[]{0x0000000010029000L});
    public static final BitSet FOLLOW_ruleExtension_in_ruleExportModel153 = new BitSet(new long[]{0x0000000010021000L});
    public static final BitSet FOLLOW_12_in_ruleExportModel167 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleExportModel179 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleInterface_in_ruleExportModel200 = new BitSet(new long[]{0x0000000000004020L});
    public static final BitSet FOLLOW_14_in_ruleExportModel213 = new BitSet(new long[]{0x0000000010021000L});
    public static final BitSet FOLLOW_ruleExport_in_ruleExportModel236 = new BitSet(new long[]{0x0000000010021002L});
    public static final BitSet FOLLOW_ruleImport_in_entryRuleImport273 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImport283 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleImport320 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImport340 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_16_in_ruleImport353 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleImport370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExtension_in_entryRuleExtension413 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExtension423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleExtension460 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExtension481 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterface_in_entryRuleInterface519 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterface529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleInterface577 = new BitSet(new long[]{0x0000000000540000L});
    public static final BitSet FOLLOW_18_in_ruleInterface590 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInterface611 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleInterface623 = new BitSet(new long[]{0x0000000000500000L});
    public static final BitSet FOLLOW_20_in_ruleInterface638 = new BitSet(new long[]{0x0000000003800020L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_ruleInterface659 = new BitSet(new long[]{0x0000000000700000L});
    public static final BitSet FOLLOW_21_in_ruleInterface672 = new BitSet(new long[]{0x0000000003800020L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_ruleInterface693 = new BitSet(new long[]{0x0000000000700000L});
    public static final BitSet FOLLOW_22_in_ruleInterface709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceItem_in_entryRuleInterfaceItem745 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceItem755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceField_in_ruleInterfaceItem802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceNavigation_in_ruleInterfaceItem829 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceExpression_in_ruleInterfaceItem856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceField_in_entryRuleInterfaceField891 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceField901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleInterfaceField944 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterfaceField978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceNavigation_in_entryRuleInterfaceNavigation1014 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceNavigation1024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleInterfaceNavigation1061 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_23_in_ruleInterfaceNavigation1079 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleInterfaceNavigation1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInterfaceExpression_in_entryRuleInterfaceExpression1149 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInterfaceExpression1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleInterfaceExpression1196 = new BitSet(new long[]{0x0000000005800000L});
    public static final BitSet FOLLOW_24_in_ruleInterfaceExpression1214 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_ruleInterfaceExpression1246 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInterfaceExpression1272 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInterfaceExpression1293 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleInterfaceExpression1305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExport_in_entryRuleExport1341 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExport1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleExport1388 = new BitSet(new long[]{0x0000000020000020L});
    public static final BitSet FOLLOW_29_in_ruleExport1406 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_ruleExport1443 = new BitSet(new long[]{0x0000000000052000L});
    public static final BitSet FOLLOW_16_in_ruleExport1456 = new BitSet(new long[]{0xA4004880440020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_30_in_ruleExport1474 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExport1509 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_18_in_ruleExport1524 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleExport1545 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleExport1557 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleExport1571 = new BitSet(new long[]{0x0000003C80004000L});
    public static final BitSet FOLLOW_31_in_ruleExport1584 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleExport1596 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_32_in_ruleExport1614 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleExport1640 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleExport1652 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExport1672 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleExport1684 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleExport1696 = new BitSet(new long[]{0x0000003C00004000L});
    public static final BitSet FOLLOW_34_in_ruleExport1718 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_35_in_ruleExport1755 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleExport1781 = new BitSet(new long[]{0x0000003000004000L});
    public static final BitSet FOLLOW_36_in_ruleExport1797 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleExport1818 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_21_in_ruleExport1831 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleAttribute_in_ruleExport1852 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_22_in_ruleExport1866 = new BitSet(new long[]{0x0000003000004000L});
    public static final BitSet FOLLOW_37_in_ruleExport1886 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleUserData_in_ruleExport1907 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_21_in_ruleExport1920 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleUserData_in_ruleExport1941 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_22_in_ruleExport1955 = new BitSet(new long[]{0x0000003000004000L});
    public static final BitSet FOLLOW_14_in_ruleExport1970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUserData_in_entryRuleUserData2006 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUserData2016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleUserData2058 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleUserData2075 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleUserData2096 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAttribute_in_entryRuleAttribute2132 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAttribute2142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAttribute2186 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQualifiedID_in_entryRuleQualifiedID2222 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQualifiedID2233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID2273 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_ruleQualifiedID2292 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleQualifiedID2307 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression2354 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_ruleExpression2411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_ruleExpression2444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_ruleExpression2472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_entryRuleLetExpression2509 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetExpression2519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleLetExpression2556 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLetExpression2577 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleLetExpression2589 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression2610 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleLetExpression2622 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression2643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression2679 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCastedExpression2689 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleCastedExpression2726 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleCastedExpression2747 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleCastedExpression2759 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCastedExpression2780 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_entryRuleChainExpression2816 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainExpression2826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression2873 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_ruleChainExpression2894 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression2915 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression2953 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainedExpression2963 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression3010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression3037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_ruleChainedExpression3064 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri3099 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionTri3109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleIfExpressionTri3156 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_ruleIfExpressionTri3177 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3198 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleIfExpressionTri3210 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri3231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw3269 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionKw3279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleIfExpressionKw3316 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3337 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_ruleIfExpressionKw3349 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3370 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_45_in_ruleIfExpressionKw3391 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw3412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression3451 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchExpression3461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleSwitchExpression3498 = new BitSet(new long[]{0x0000000004002000L});
    public static final BitSet FOLLOW_26_in_ruleSwitchExpression3511 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression3532 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleSwitchExpression3544 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleSwitchExpression3558 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_ruleCase_in_ruleSwitchExpression3579 = new BitSet(new long[]{0x0001800000000000L});
    public static final BitSet FOLLOW_47_in_ruleSwitchExpression3592 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleSwitchExpression3604 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression3625 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleSwitchExpression3637 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCase_in_entryRuleCase3673 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCase3683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleCase3720 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase3741 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_40_in_ruleCase3753 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase3774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression3810 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression3820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression3867 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_49_in_ruleOrExpression3894 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression3928 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression3966 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression3976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression4023 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50_in_ruleAndExpression4050 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression4084 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression4122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpression4132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4179 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_ruleImpliesExpression4206 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression4240 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression4278 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression4288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4335 = new BitSet(new long[]{0x03F0000000000002L});
    public static final BitSet FOLLOW_52_in_ruleRelationalExpression4364 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_53_in_ruleRelationalExpression4393 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_54_in_ruleRelationalExpression4422 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_55_in_ruleRelationalExpression4451 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_56_in_ruleRelationalExpression4480 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_57_in_ruleRelationalExpression4509 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression4546 = new BitSet(new long[]{0x03F0000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression4584 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression4594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4641 = new BitSet(new long[]{0x0400000000800002L});
    public static final BitSet FOLLOW_23_in_ruleAdditiveExpression4670 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_58_in_ruleAdditiveExpression4699 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression4736 = new BitSet(new long[]{0x0400000000800002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression4774 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression4784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4831 = new BitSet(new long[]{0x1800000000000002L});
    public static final BitSet FOLLOW_59_in_ruleMultiplicativeExpression4860 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_60_in_ruleMultiplicativeExpression4889 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression4926 = new BitSet(new long[]{0x1800000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression4964 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression4974 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression5021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression5048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression5083 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression5093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_ruleUnaryExpression5138 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_58_in_ruleUnaryExpression5167 = new BitSet(new long[]{0xA4000000040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression5204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression5240 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression5250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression5297 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5319 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression5340 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression5352 = new BitSet(new long[]{0xA40048800C0020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression5374 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_21_in_ruleInfixExpression5387 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression5408 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_27_in_ruleInfixExpression5424 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5453 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression5474 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5503 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleInfixExpression5521 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression5546 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression5567 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleInfixExpression5579 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleInfixExpression5608 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000FFL});
    public static final BitSet FOLLOW_64_in_ruleInfixExpression5628 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_65_in_ruleInfixExpression5657 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_66_in_ruleInfixExpression5686 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_67_in_ruleInfixExpression5715 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_68_in_ruleInfixExpression5744 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_69_in_ruleInfixExpression5773 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_70_in_ruleInfixExpression5802 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_71_in_ruleInfixExpression5831 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleInfixExpression5859 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression5881 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleInfixExpression5893 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression5916 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleInfixExpression5928 = new BitSet(new long[]{0x4000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression5967 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression5977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression6024 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_rulePrimaryExpression6051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_rulePrimaryExpression6078 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression6105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression6132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression6159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral6194 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral6204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral6251 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral6278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral6305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_ruleLiteral6332 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral6359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral6394 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral6404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_ruleBooleanLiteral6448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_ruleBooleanLiteral6477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral6528 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral6538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral6579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral6619 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral6629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleNullLiteral6671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral6719 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealLiteral6729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_in_ruleRealLiteral6770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral6810 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral6820 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral6861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression6901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression6911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleParanthesizedExpression6948 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression6970 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleParanthesizedExpression6981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression7017 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression7027 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_ruleGlobalVarExpression7064 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression7085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall7121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureCall7131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_ruleFeatureCall7178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_ruleFeatureCall7204 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_ruleFeatureCall7232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall7259 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_entryRuleOperationCall7294 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCall7304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleOperationCall7350 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleOperationCall7362 = new BitSet(new long[]{0xA40048800C0020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall7384 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_21_in_ruleOperationCall7397 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall7418 = new BitSet(new long[]{0x0000000008200000L});
    public static final BitSet FOLLOW_27_in_ruleOperationCall7434 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_entryRuleListLiteral7470 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListLiteral7480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_ruleListLiteral7526 = new BitSet(new long[]{0xA4004880040060F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral7548 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_21_in_ruleListLiteral7561 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral7582 = new BitSet(new long[]{0x0000000000204000L});
    public static final BitSet FOLLOW_14_in_ruleListLiteral7598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression7634 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstructorCallExpression7644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_ruleConstructorCallExpression7681 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression7702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression7738 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTypeSelectExpression7748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleTypeSelectExpression7791 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleTypeSelectExpression7816 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleType_in_ruleTypeSelectExpression7837 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleTypeSelectExpression7849 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression7885 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionExpression7895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleCollectionExpression7940 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_65_in_ruleCollectionExpression7969 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_66_in_ruleCollectionExpression7998 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_67_in_ruleCollectionExpression8027 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_68_in_ruleCollectionExpression8056 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_69_in_ruleCollectionExpression8085 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_70_in_ruleCollectionExpression8114 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_71_in_ruleCollectionExpression8143 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleCollectionExpression8171 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleCollectionExpression8193 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleCollectionExpression8205 = new BitSet(new long[]{0xA4004880040020F0L,0x000000000001FEFFL});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCollectionExpression8228 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleCollectionExpression8240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType8276 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType8286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_ruleType8333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleType8360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_entryRuleCollectionType8395 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionType8405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_ruleCollectionType8450 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_79_in_ruleCollectionType8479 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_80_in_ruleCollectionType8508 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleCollectionType8536 = new BitSet(new long[]{0x0000000000000020L,0x000000000001C000L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleCollectionType8557 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleCollectionType8569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_entryRuleSimpleType8605 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleType8615 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType8661 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_ruleSimpleType8674 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType8695 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier8734 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier8745 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIdentifier8784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_synpred1_InternalExport2428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_synpred2_InternalExport3382 = new BitSet(new long[]{0x0000000000000002L});

}