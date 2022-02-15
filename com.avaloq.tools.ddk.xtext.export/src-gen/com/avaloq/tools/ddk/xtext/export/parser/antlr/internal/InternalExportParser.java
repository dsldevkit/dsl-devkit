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
    public String getGrammarFileName() { return "InternalExport.g"; }



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
    // InternalExport.g:64:1: entryRuleExportModel returns [EObject current=null] : iv_ruleExportModel= ruleExportModel EOF ;
    public final EObject entryRuleExportModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExportModel = null;


        try {
            // InternalExport.g:64:52: (iv_ruleExportModel= ruleExportModel EOF )
            // InternalExport.g:65:2: iv_ruleExportModel= ruleExportModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExportModelRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExportModel=ruleExportModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExportModel; 
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
    // $ANTLR end "entryRuleExportModel"


    // $ANTLR start "ruleExportModel"
    // InternalExport.g:71:1: ruleExportModel returns [EObject current=null] : ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ ) ;
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
            // InternalExport.g:77:2: ( ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ ) )
            // InternalExport.g:78:2: ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ )
            {
            // InternalExport.g:78:2: ( (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+ )
            // InternalExport.g:79:3: (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )? ( (lv_imports_5_0= ruleImport ) )+ ( (lv_extensions_6_0= ruleExtension ) )* (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )? ( (lv_exports_11_0= ruleExport ) )+
            {
            // InternalExport.g:79:3: (otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==12) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalExport.g:80:4: otherlv_0= 'export' ( (lv_extension_1_0= 'extension' ) )? ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'for' ( ( ruleQualifiedID ) )
                    {
                    otherlv_0=(Token)match(input,12,FOLLOW_3); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getExportModelAccess().getExportKeyword_0_0());
                      			
                    }
                    // InternalExport.g:84:4: ( (lv_extension_1_0= 'extension' ) )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==13) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // InternalExport.g:85:5: (lv_extension_1_0= 'extension' )
                            {
                            // InternalExport.g:85:5: (lv_extension_1_0= 'extension' )
                            // InternalExport.g:86:6: lv_extension_1_0= 'extension'
                            {
                            lv_extension_1_0=(Token)match(input,13,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(lv_extension_1_0, grammarAccess.getExportModelAccess().getExtensionExtensionKeyword_0_1_0());
                              					
                            }
                            if ( state.backtracking==0 ) {

                              						if (current==null) {
                              							current = createModelElement(grammarAccess.getExportModelRule());
                              						}
                              						setWithLastConsumed(current, "extension", lv_extension_1_0 != null, "extension");
                              					
                            }

                            }


                            }
                            break;

                    }

                    // InternalExport.g:98:4: ( (lv_name_2_0= RULE_ID ) )
                    // InternalExport.g:99:5: (lv_name_2_0= RULE_ID )
                    {
                    // InternalExport.g:99:5: (lv_name_2_0= RULE_ID )
                    // InternalExport.g:100:6: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_5); if (state.failed) return current;
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
                      							"org.eclipse.xtext.common.Terminals.ID");
                      					
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,14,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getExportModelAccess().getForKeyword_0_3());
                      			
                    }
                    // InternalExport.g:120:4: ( ( ruleQualifiedID ) )
                    // InternalExport.g:121:5: ( ruleQualifiedID )
                    {
                    // InternalExport.g:121:5: ( ruleQualifiedID )
                    // InternalExport.g:122:6: ruleQualifiedID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getExportModelRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getExportModelAccess().getTargetGrammarGrammarCrossReference_0_4_0());
                      					
                    }
                    pushFollow(FOLLOW_6);
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

            // InternalExport.g:137:3: ( (lv_imports_5_0= ruleImport ) )+
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
            	    // InternalExport.g:138:4: (lv_imports_5_0= ruleImport )
            	    {
            	    // InternalExport.g:138:4: (lv_imports_5_0= ruleImport )
            	    // InternalExport.g:139:5: lv_imports_5_0= ruleImport
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getExportModelAccess().getImportsImportParserRuleCall_1_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_7);
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
            	      						"com.avaloq.tools.ddk.xtext.export.Export.Import");
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

            // InternalExport.g:156:3: ( (lv_extensions_6_0= ruleExtension ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==13) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalExport.g:157:4: (lv_extensions_6_0= ruleExtension )
            	    {
            	    // InternalExport.g:157:4: (lv_extensions_6_0= ruleExtension )
            	    // InternalExport.g:158:5: lv_extensions_6_0= ruleExtension
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getExportModelAccess().getExtensionsExtensionParserRuleCall_2_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_8);
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
            	      						"com.avaloq.tools.ddk.xtext.export.Export.Extension");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // InternalExport.g:175:3: (otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalExport.g:176:4: otherlv_7= 'interface' otherlv_8= '{' ( (lv_interfaces_9_0= ruleInterface ) )+ otherlv_10= '}'
                    {
                    otherlv_7=(Token)match(input,15,FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getExportModelAccess().getInterfaceKeyword_3_0());
                      			
                    }
                    otherlv_8=(Token)match(input,16,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_8, grammarAccess.getExportModelAccess().getLeftCurlyBracketKeyword_3_1());
                      			
                    }
                    // InternalExport.g:184:4: ( (lv_interfaces_9_0= ruleInterface ) )+
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
                    	    // InternalExport.g:185:5: (lv_interfaces_9_0= ruleInterface )
                    	    {
                    	    // InternalExport.g:185:5: (lv_interfaces_9_0= ruleInterface )
                    	    // InternalExport.g:186:6: lv_interfaces_9_0= ruleInterface
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      						newCompositeNode(grammarAccess.getExportModelAccess().getInterfacesInterfaceParserRuleCall_3_2_0());
                    	      					
                    	    }
                    	    pushFollow(FOLLOW_10);
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
                    	      							"com.avaloq.tools.ddk.xtext.export.Export.Interface");
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

                    otherlv_10=(Token)match(input,17,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_10, grammarAccess.getExportModelAccess().getRightCurlyBracketKeyword_3_3());
                      			
                    }

                    }
                    break;

            }

            // InternalExport.g:208:3: ( (lv_exports_11_0= ruleExport ) )+
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
            	    // InternalExport.g:209:4: (lv_exports_11_0= ruleExport )
            	    {
            	    // InternalExport.g:209:4: (lv_exports_11_0= ruleExport )
            	    // InternalExport.g:210:5: lv_exports_11_0= ruleExport
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getExportModelAccess().getExportsExportParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_11);
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
            	      						"com.avaloq.tools.ddk.xtext.export.Export.Export");
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
    // InternalExport.g:231:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalExport.g:231:47: (iv_ruleImport= ruleImport EOF )
            // InternalExport.g:232:2: iv_ruleImport= ruleImport EOF
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
    // InternalExport.g:238:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;


        	enterRule();

        try {
            // InternalExport.g:244:2: ( (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? ) )
            // InternalExport.g:245:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            {
            // InternalExport.g:245:2: (otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )? )
            // InternalExport.g:246:3: otherlv_0= 'import' ( (otherlv_1= RULE_STRING ) ) (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            {
            otherlv_0=(Token)match(input,18,FOLLOW_12); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
              		
            }
            // InternalExport.g:250:3: ( (otherlv_1= RULE_STRING ) )
            // InternalExport.g:251:4: (otherlv_1= RULE_STRING )
            {
            // InternalExport.g:251:4: (otherlv_1= RULE_STRING )
            // InternalExport.g:252:5: otherlv_1= RULE_STRING
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getImportRule());
              					}
              				
            }
            otherlv_1=(Token)match(input,RULE_STRING,FOLLOW_13); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getImportAccess().getPackageEPackageCrossReference_1_0());
              				
            }

            }


            }

            // InternalExport.g:263:3: (otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalExport.g:264:4: otherlv_2= 'as' ( (lv_name_3_0= RULE_ID ) )
                    {
                    otherlv_2=(Token)match(input,19,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getImportAccess().getAsKeyword_2_0());
                      			
                    }
                    // InternalExport.g:268:4: ( (lv_name_3_0= RULE_ID ) )
                    // InternalExport.g:269:5: (lv_name_3_0= RULE_ID )
                    {
                    // InternalExport.g:269:5: (lv_name_3_0= RULE_ID )
                    // InternalExport.g:270:6: lv_name_3_0= RULE_ID
                    {
                    lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
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
                      							"org.eclipse.xtext.common.Terminals.ID");
                      					
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
    // InternalExport.g:291:1: entryRuleExtension returns [EObject current=null] : iv_ruleExtension= ruleExtension EOF ;
    public final EObject entryRuleExtension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExtension = null;


        try {
            // InternalExport.g:291:50: (iv_ruleExtension= ruleExtension EOF )
            // InternalExport.g:292:2: iv_ruleExtension= ruleExtension EOF
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
    // InternalExport.g:298:1: ruleExtension returns [EObject current=null] : (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) ;
    public final EObject ruleExtension() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_extension_1_0 = null;



        	enterRule();

        try {
            // InternalExport.g:304:2: ( (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) ) )
            // InternalExport.g:305:2: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            {
            // InternalExport.g:305:2: (otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) ) )
            // InternalExport.g:306:3: otherlv_0= 'extension' ( (lv_extension_1_0= ruleQualifiedID ) )
            {
            otherlv_0=(Token)match(input,13,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getExtensionAccess().getExtensionKeyword_0());
              		
            }
            // InternalExport.g:310:3: ( (lv_extension_1_0= ruleQualifiedID ) )
            // InternalExport.g:311:4: (lv_extension_1_0= ruleQualifiedID )
            {
            // InternalExport.g:311:4: (lv_extension_1_0= ruleQualifiedID )
            // InternalExport.g:312:5: lv_extension_1_0= ruleQualifiedID
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
              						"com.avaloq.tools.ddk.xtext.export.Export.QualifiedID");
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
    // InternalExport.g:333:1: entryRuleInterface returns [EObject current=null] : iv_ruleInterface= ruleInterface EOF ;
    public final EObject entryRuleInterface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterface = null;


        try {
            // InternalExport.g:333:50: (iv_ruleInterface= ruleInterface EOF )
            // InternalExport.g:334:2: iv_ruleInterface= ruleInterface EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInterface=ruleInterface();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterface; 
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
    // $ANTLR end "entryRuleInterface"


    // $ANTLR start "ruleInterface"
    // InternalExport.g:340:1: ruleInterface returns [EObject current=null] : ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) ;
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
            // InternalExport.g:346:2: ( ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' ) )
            // InternalExport.g:347:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            {
            // InternalExport.g:347:2: ( ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';' )
            // InternalExport.g:348:3: ( ( ruleQualifiedID ) ) (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )? (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )* otherlv_8= ';'
            {
            // InternalExport.g:348:3: ( ( ruleQualifiedID ) )
            // InternalExport.g:349:4: ( ruleQualifiedID )
            {
            // InternalExport.g:349:4: ( ruleQualifiedID )
            // InternalExport.g:350:5: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getInterfaceRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getInterfaceAccess().getTypeEClassCrossReference_0_0());
              				
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

            // InternalExport.g:364:3: (otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==20) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalExport.g:365:4: otherlv_1= '[' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ']'
                    {
                    otherlv_1=(Token)match(input,20,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getInterfaceAccess().getLeftSquareBracketKeyword_1_0());
                      			
                    }
                    // InternalExport.g:369:4: ( (lv_guard_2_0= ruleExpression ) )
                    // InternalExport.g:370:5: (lv_guard_2_0= ruleExpression )
                    {
                    // InternalExport.g:370:5: (lv_guard_2_0= ruleExpression )
                    // InternalExport.g:371:6: lv_guard_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getInterfaceAccess().getGuardExpressionParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_16);
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
                      							"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    otherlv_3=(Token)match(input,21,FOLLOW_17); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getInterfaceAccess().getRightSquareBracketKeyword_1_2());
                      			
                    }

                    }
                    break;

            }

            // InternalExport.g:393:3: (otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )* )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==22) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalExport.g:394:4: otherlv_4= '=' ( (lv_items_5_0= ruleInterfaceItem ) ) (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    {
            	    otherlv_4=(Token)match(input,22,FOLLOW_18); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_4, grammarAccess.getInterfaceAccess().getEqualsSignKeyword_2_0());
            	      			
            	    }
            	    // InternalExport.g:398:4: ( (lv_items_5_0= ruleInterfaceItem ) )
            	    // InternalExport.g:399:5: (lv_items_5_0= ruleInterfaceItem )
            	    {
            	    // InternalExport.g:399:5: (lv_items_5_0= ruleInterfaceItem )
            	    // InternalExport.g:400:6: lv_items_5_0= ruleInterfaceItem
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_19);
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
            	      							"com.avaloq.tools.ddk.xtext.export.Export.InterfaceItem");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }

            	    // InternalExport.g:417:4: (otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) ) )*
            	    loop10:
            	    do {
            	        int alt10=2;
            	        int LA10_0 = input.LA(1);

            	        if ( (LA10_0==23) ) {
            	            alt10=1;
            	        }


            	        switch (alt10) {
            	    	case 1 :
            	    	    // InternalExport.g:418:5: otherlv_6= ',' ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    {
            	    	    otherlv_6=(Token)match(input,23,FOLLOW_18); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      					newLeafNode(otherlv_6, grammarAccess.getInterfaceAccess().getCommaKeyword_2_2_0());
            	    	      				
            	    	    }
            	    	    // InternalExport.g:422:5: ( (lv_items_7_0= ruleInterfaceItem ) )
            	    	    // InternalExport.g:423:6: (lv_items_7_0= ruleInterfaceItem )
            	    	    {
            	    	    // InternalExport.g:423:6: (lv_items_7_0= ruleInterfaceItem )
            	    	    // InternalExport.g:424:7: lv_items_7_0= ruleInterfaceItem
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      							newCompositeNode(grammarAccess.getInterfaceAccess().getItemsInterfaceItemParserRuleCall_2_2_1_0());
            	    	      						
            	    	    }
            	    	    pushFollow(FOLLOW_19);
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
            	    	      								"com.avaloq.tools.ddk.xtext.export.Export.InterfaceItem");
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

            otherlv_8=(Token)match(input,24,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:451:1: entryRuleInterfaceItem returns [EObject current=null] : iv_ruleInterfaceItem= ruleInterfaceItem EOF ;
    public final EObject entryRuleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceItem = null;


        try {
            // InternalExport.g:451:54: (iv_ruleInterfaceItem= ruleInterfaceItem EOF )
            // InternalExport.g:452:2: iv_ruleInterfaceItem= ruleInterfaceItem EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceItemRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInterfaceItem=ruleInterfaceItem();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceItem; 
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
    // $ANTLR end "entryRuleInterfaceItem"


    // $ANTLR start "ruleInterfaceItem"
    // InternalExport.g:458:1: ruleInterfaceItem returns [EObject current=null] : (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) ;
    public final EObject ruleInterfaceItem() throws RecognitionException {
        EObject current = null;

        EObject this_InterfaceField_0 = null;

        EObject this_InterfaceNavigation_1 = null;

        EObject this_InterfaceExpression_2 = null;



        	enterRule();

        try {
            // InternalExport.g:464:2: ( (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression ) )
            // InternalExport.g:465:2: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
            {
            // InternalExport.g:465:2: (this_InterfaceField_0= ruleInterfaceField | this_InterfaceNavigation_1= ruleInterfaceNavigation | this_InterfaceExpression_2= ruleInterfaceExpression )
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
                    // InternalExport.g:466:3: this_InterfaceField_0= ruleInterfaceField
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceFieldParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalExport.g:475:3: this_InterfaceNavigation_1= ruleInterfaceNavigation
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceNavigationParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
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
                    // InternalExport.g:484:3: this_InterfaceExpression_2= ruleInterfaceExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getInterfaceItemAccess().getInterfaceExpressionParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
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
    // InternalExport.g:496:1: entryRuleInterfaceField returns [EObject current=null] : iv_ruleInterfaceField= ruleInterfaceField EOF ;
    public final EObject entryRuleInterfaceField() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceField = null;


        try {
            // InternalExport.g:496:55: (iv_ruleInterfaceField= ruleInterfaceField EOF )
            // InternalExport.g:497:2: iv_ruleInterfaceField= ruleInterfaceField EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceFieldRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInterfaceField=ruleInterfaceField();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceField; 
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
    // $ANTLR end "entryRuleInterfaceField"


    // $ANTLR start "ruleInterfaceField"
    // InternalExport.g:503:1: ruleInterfaceField returns [EObject current=null] : ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceField() throws RecognitionException {
        EObject current = null;

        Token lv_unordered_0_0=null;
        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalExport.g:509:2: ( ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) ) )
            // InternalExport.g:510:2: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalExport.g:510:2: ( ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) ) )
            // InternalExport.g:511:3: ( (lv_unordered_0_0= '+' ) )? ( (otherlv_1= RULE_ID ) )
            {
            // InternalExport.g:511:3: ( (lv_unordered_0_0= '+' ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==25) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalExport.g:512:4: (lv_unordered_0_0= '+' )
                    {
                    // InternalExport.g:512:4: (lv_unordered_0_0= '+' )
                    // InternalExport.g:513:5: lv_unordered_0_0= '+'
                    {
                    lv_unordered_0_0=(Token)match(input,25,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_unordered_0_0, grammarAccess.getInterfaceFieldAccess().getUnorderedPlusSignKeyword_0_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getInterfaceFieldRule());
                      					}
                      					setWithLastConsumed(current, "unordered", lv_unordered_0_0 != null, "+");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalExport.g:525:3: ( (otherlv_1= RULE_ID ) )
            // InternalExport.g:526:4: (otherlv_1= RULE_ID )
            {
            // InternalExport.g:526:4: (otherlv_1= RULE_ID )
            // InternalExport.g:527:5: otherlv_1= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getInterfaceFieldRule());
              					}
              				
            }
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:542:1: entryRuleInterfaceNavigation returns [EObject current=null] : iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF ;
    public final EObject entryRuleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceNavigation = null;


        try {
            // InternalExport.g:542:60: (iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF )
            // InternalExport.g:543:2: iv_ruleInterfaceNavigation= ruleInterfaceNavigation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceNavigationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInterfaceNavigation=ruleInterfaceNavigation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceNavigation; 
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
    // $ANTLR end "entryRuleInterfaceNavigation"


    // $ANTLR start "ruleInterfaceNavigation"
    // InternalExport.g:549:1: ruleInterfaceNavigation returns [EObject current=null] : (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject ruleInterfaceNavigation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_unordered_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalExport.g:555:2: ( (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) ) )
            // InternalExport.g:556:2: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalExport.g:556:2: (otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) ) )
            // InternalExport.g:557:3: otherlv_0= '@' ( (lv_unordered_1_0= '+' ) )? ( (otherlv_2= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getInterfaceNavigationAccess().getCommercialAtKeyword_0());
              		
            }
            // InternalExport.g:561:3: ( (lv_unordered_1_0= '+' ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==25) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalExport.g:562:4: (lv_unordered_1_0= '+' )
                    {
                    // InternalExport.g:562:4: (lv_unordered_1_0= '+' )
                    // InternalExport.g:563:5: lv_unordered_1_0= '+'
                    {
                    lv_unordered_1_0=(Token)match(input,25,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_unordered_1_0, grammarAccess.getInterfaceNavigationAccess().getUnorderedPlusSignKeyword_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getInterfaceNavigationRule());
                      					}
                      					setWithLastConsumed(current, "unordered", lv_unordered_1_0 != null, "+");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalExport.g:575:3: ( (otherlv_2= RULE_ID ) )
            // InternalExport.g:576:4: (otherlv_2= RULE_ID )
            {
            // InternalExport.g:576:4: (otherlv_2= RULE_ID )
            // InternalExport.g:577:5: otherlv_2= RULE_ID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getInterfaceNavigationRule());
              					}
              				
            }
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:592:1: entryRuleInterfaceExpression returns [EObject current=null] : iv_ruleInterfaceExpression= ruleInterfaceExpression EOF ;
    public final EObject entryRuleInterfaceExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInterfaceExpression = null;


        try {
            // InternalExport.g:592:60: (iv_ruleInterfaceExpression= ruleInterfaceExpression EOF )
            // InternalExport.g:593:2: iv_ruleInterfaceExpression= ruleInterfaceExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInterfaceExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleInterfaceExpression=ruleInterfaceExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInterfaceExpression; 
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
    // $ANTLR end "entryRuleInterfaceExpression"


    // $ANTLR start "ruleInterfaceExpression"
    // InternalExport.g:599:1: ruleInterfaceExpression returns [EObject current=null] : (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // InternalExport.g:605:2: ( (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // InternalExport.g:606:2: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // InternalExport.g:606:2: (otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')' )
            // InternalExport.g:607:3: otherlv_0= 'eval' ( (lv_ref_1_0= '@' ) )? ( (lv_unordered_2_0= '+' ) )? otherlv_3= '(' ( (lv_expr_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,27,FOLLOW_21); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getInterfaceExpressionAccess().getEvalKeyword_0());
              		
            }
            // InternalExport.g:611:3: ( (lv_ref_1_0= '@' ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==26) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalExport.g:612:4: (lv_ref_1_0= '@' )
                    {
                    // InternalExport.g:612:4: (lv_ref_1_0= '@' )
                    // InternalExport.g:613:5: lv_ref_1_0= '@'
                    {
                    lv_ref_1_0=(Token)match(input,26,FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_ref_1_0, grammarAccess.getInterfaceExpressionAccess().getRefCommercialAtKeyword_1_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getInterfaceExpressionRule());
                      					}
                      					setWithLastConsumed(current, "ref", lv_ref_1_0 != null, "@");
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalExport.g:625:3: ( (lv_unordered_2_0= '+' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==25) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalExport.g:626:4: (lv_unordered_2_0= '+' )
                    {
                    // InternalExport.g:626:4: (lv_unordered_2_0= '+' )
                    // InternalExport.g:627:5: lv_unordered_2_0= '+'
                    {
                    lv_unordered_2_0=(Token)match(input,25,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(lv_unordered_2_0, grammarAccess.getInterfaceExpressionAccess().getUnorderedPlusSignKeyword_2_0());
                      				
                    }
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElement(grammarAccess.getInterfaceExpressionRule());
                      					}
                      					setWithLastConsumed(current, "unordered", lv_unordered_2_0 != null, "+");
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,28,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getInterfaceExpressionAccess().getLeftParenthesisKeyword_3());
              		
            }
            // InternalExport.g:643:3: ( (lv_expr_4_0= ruleExpression ) )
            // InternalExport.g:644:4: (lv_expr_4_0= ruleExpression )
            {
            // InternalExport.g:644:4: (lv_expr_4_0= ruleExpression )
            // InternalExport.g:645:5: lv_expr_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getInterfaceExpressionAccess().getExprExpressionParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_24);
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
              						"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_5=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:670:1: entryRuleExport returns [EObject current=null] : iv_ruleExport= ruleExport EOF ;
    public final EObject entryRuleExport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExport = null;


        try {
            // InternalExport.g:670:47: (iv_ruleExport= ruleExport EOF )
            // InternalExport.g:671:2: iv_ruleExport= ruleExport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExportRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleExport=ruleExport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExport; 
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
    // $ANTLR end "entryRuleExport"


    // $ANTLR start "ruleExport"
    // InternalExport.g:677:1: ruleExport returns [EObject current=null] : (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' ) ;
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
            // InternalExport.g:683:2: ( (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' ) )
            // InternalExport.g:684:2: (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' )
            {
            // InternalExport.g:684:2: (otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}' )
            // InternalExport.g:685:3: otherlv_0= 'export' ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )? ( ( ruleQualifiedID ) ) (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )? (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )? otherlv_12= '{' (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )? ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )? ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )* otherlv_34= '}'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_25); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getExportAccess().getExportKeyword_0());
              		
            }
            // InternalExport.g:689:3: ( ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )? )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==30) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalExport.g:690:4: ( (lv_lookup_1_0= 'lookup' ) ) (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )?
                    {
                    // InternalExport.g:690:4: ( (lv_lookup_1_0= 'lookup' ) )
                    // InternalExport.g:691:5: (lv_lookup_1_0= 'lookup' )
                    {
                    // InternalExport.g:691:5: (lv_lookup_1_0= 'lookup' )
                    // InternalExport.g:692:6: lv_lookup_1_0= 'lookup'
                    {
                    lv_lookup_1_0=(Token)match(input,30,FOLLOW_26); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_lookup_1_0, grammarAccess.getExportAccess().getLookupLookupKeyword_1_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getExportRule());
                      						}
                      						setWithLastConsumed(current, "lookup", lv_lookup_1_0 != null, "lookup");
                      					
                    }

                    }


                    }

                    // InternalExport.g:704:4: (otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==20) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // InternalExport.g:705:5: otherlv_2= '[' ( (lv_lookupPredicate_3_0= ruleExpression ) ) otherlv_4= ']'
                            {
                            otherlv_2=(Token)match(input,20,FOLLOW_15); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_2, grammarAccess.getExportAccess().getLeftSquareBracketKeyword_1_1_0());
                              				
                            }
                            // InternalExport.g:709:5: ( (lv_lookupPredicate_3_0= ruleExpression ) )
                            // InternalExport.g:710:6: (lv_lookupPredicate_3_0= ruleExpression )
                            {
                            // InternalExport.g:710:6: (lv_lookupPredicate_3_0= ruleExpression )
                            // InternalExport.g:711:7: lv_lookupPredicate_3_0= ruleExpression
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getExportAccess().getLookupPredicateExpressionParserRuleCall_1_1_1_0());
                              						
                            }
                            pushFollow(FOLLOW_16);
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
                              								"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }

                            otherlv_4=(Token)match(input,21,FOLLOW_4); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					newLeafNode(otherlv_4, grammarAccess.getExportAccess().getRightSquareBracketKeyword_1_1_2());
                              				
                            }

                            }
                            break;

                    }


                    }
                    break;

            }

            // InternalExport.g:734:3: ( ( ruleQualifiedID ) )
            // InternalExport.g:735:4: ( ruleQualifiedID )
            {
            // InternalExport.g:735:4: ( ruleQualifiedID )
            // InternalExport.g:736:5: ruleQualifiedID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getExportRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getExportAccess().getTypeEClassCrossReference_2_0());
              				
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

            // InternalExport.g:750:3: (otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==19) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalExport.g:751:4: otherlv_6= 'as' ( (lv_qualifiedName_7_0= 'qualified' ) )? ( (lv_naming_8_0= ruleExpression ) )
                    {
                    otherlv_6=(Token)match(input,19,FOLLOW_28); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getExportAccess().getAsKeyword_3_0());
                      			
                    }
                    // InternalExport.g:755:4: ( (lv_qualifiedName_7_0= 'qualified' ) )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==31) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // InternalExport.g:756:5: (lv_qualifiedName_7_0= 'qualified' )
                            {
                            // InternalExport.g:756:5: (lv_qualifiedName_7_0= 'qualified' )
                            // InternalExport.g:757:6: lv_qualifiedName_7_0= 'qualified'
                            {
                            lv_qualifiedName_7_0=(Token)match(input,31,FOLLOW_15); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(lv_qualifiedName_7_0, grammarAccess.getExportAccess().getQualifiedNameQualifiedKeyword_3_1_0());
                              					
                            }
                            if ( state.backtracking==0 ) {

                              						if (current==null) {
                              							current = createModelElement(grammarAccess.getExportRule());
                              						}
                              						setWithLastConsumed(current, "qualifiedName", lv_qualifiedName_7_0 != null, "qualified");
                              					
                            }

                            }


                            }
                            break;

                    }

                    // InternalExport.g:769:4: ( (lv_naming_8_0= ruleExpression ) )
                    // InternalExport.g:770:5: (lv_naming_8_0= ruleExpression )
                    {
                    // InternalExport.g:770:5: (lv_naming_8_0= ruleExpression )
                    // InternalExport.g:771:6: lv_naming_8_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getExportAccess().getNamingExpressionParserRuleCall_3_2_0());
                      					
                    }
                    pushFollow(FOLLOW_29);
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
                      							"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalExport.g:789:3: (otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==20) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalExport.g:790:4: otherlv_9= '[' ( (lv_guard_10_0= ruleExpression ) ) otherlv_11= ']'
                    {
                    otherlv_9=(Token)match(input,20,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_9, grammarAccess.getExportAccess().getLeftSquareBracketKeyword_4_0());
                      			
                    }
                    // InternalExport.g:794:4: ( (lv_guard_10_0= ruleExpression ) )
                    // InternalExport.g:795:5: (lv_guard_10_0= ruleExpression )
                    {
                    // InternalExport.g:795:5: (lv_guard_10_0= ruleExpression )
                    // InternalExport.g:796:6: lv_guard_10_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getExportAccess().getGuardExpressionParserRuleCall_4_1_0());
                      					
                    }
                    pushFollow(FOLLOW_16);
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
                      							"com.avaloq.tools.ddk.xtext.expression.Expression.Expression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    otherlv_11=(Token)match(input,21,FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_11, grammarAccess.getExportAccess().getRightSquareBracketKeyword_4_2());
                      			
                    }

                    }
                    break;

            }

            otherlv_12=(Token)match(input,16,FOLLOW_30); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_12, grammarAccess.getExportAccess().getLeftCurlyBracketKeyword_5());
              		
            }
            // InternalExport.g:822:3: (otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';' )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==32) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalExport.g:823:4: otherlv_13= 'uri-fragment' otherlv_14= '=' ( (lv_fragmentUnique_15_0= 'unique' ) )? otherlv_16= 'attribute' otherlv_17= '(' ( (otherlv_18= RULE_ID ) ) otherlv_19= ')' otherlv_20= ';'
                    {
                    otherlv_13=(Token)match(input,32,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_13, grammarAccess.getExportAccess().getUriFragmentKeyword_6_0());
                      			
                    }
                    otherlv_14=(Token)match(input,22,FOLLOW_32); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_14, grammarAccess.getExportAccess().getEqualsSignKeyword_6_1());
                      			
                    }
                    // InternalExport.g:831:4: ( (lv_fragmentUnique_15_0= 'unique' ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==33) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // InternalExport.g:832:5: (lv_fragmentUnique_15_0= 'unique' )
                            {
                            // InternalExport.g:832:5: (lv_fragmentUnique_15_0= 'unique' )
                            // InternalExport.g:833:6: lv_fragmentUnique_15_0= 'unique'
                            {
                            lv_fragmentUnique_15_0=(Token)match(input,33,FOLLOW_33); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              						newLeafNode(lv_fragmentUnique_15_0, grammarAccess.getExportAccess().getFragmentUniqueUniqueKeyword_6_2_0());
                              					
                            }
                            if ( state.backtracking==0 ) {

                              						if (current==null) {
                              							current = createModelElement(grammarAccess.getExportRule());
                              						}
                              						setWithLastConsumed(current, "fragmentUnique", lv_fragmentUnique_15_0 != null, "unique");
                              					
                            }

                            }


                            }
                            break;

                    }

                    otherlv_16=(Token)match(input,34,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_16, grammarAccess.getExportAccess().getAttributeKeyword_6_3());
                      			
                    }
                    otherlv_17=(Token)match(input,28,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_17, grammarAccess.getExportAccess().getLeftParenthesisKeyword_6_4());
                      			
                    }
                    // InternalExport.g:853:4: ( (otherlv_18= RULE_ID ) )
                    // InternalExport.g:854:5: (otherlv_18= RULE_ID )
                    {
                    // InternalExport.g:854:5: (otherlv_18= RULE_ID )
                    // InternalExport.g:855:6: otherlv_18= RULE_ID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getExportRule());
                      						}
                      					
                    }
                    otherlv_18=(Token)match(input,RULE_ID,FOLLOW_24); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(otherlv_18, grammarAccess.getExportAccess().getFragmentAttributeEAttributeCrossReference_6_5_0());
                      					
                    }

                    }


                    }

                    otherlv_19=(Token)match(input,29,FOLLOW_34); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_19, grammarAccess.getExportAccess().getRightParenthesisKeyword_6_6());
                      			
                    }
                    otherlv_20=(Token)match(input,24,FOLLOW_35); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_20, grammarAccess.getExportAccess().getSemicolonKeyword_6_7());
                      			
                    }

                    }
                    break;

            }

            // InternalExport.g:875:3: ( ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';' )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=35 && LA25_0<=36)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // InternalExport.g:876:4: ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) ) otherlv_23= ';'
                    {
                    // InternalExport.g:876:4: ( ( (lv_fingerprint_21_0= 'object-fingerprint' ) ) | ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) ) )
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
                            // InternalExport.g:877:5: ( (lv_fingerprint_21_0= 'object-fingerprint' ) )
                            {
                            // InternalExport.g:877:5: ( (lv_fingerprint_21_0= 'object-fingerprint' ) )
                            // InternalExport.g:878:6: (lv_fingerprint_21_0= 'object-fingerprint' )
                            {
                            // InternalExport.g:878:6: (lv_fingerprint_21_0= 'object-fingerprint' )
                            // InternalExport.g:879:7: lv_fingerprint_21_0= 'object-fingerprint'
                            {
                            lv_fingerprint_21_0=(Token)match(input,35,FOLLOW_34); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(lv_fingerprint_21_0, grammarAccess.getExportAccess().getFingerprintObjectFingerprintKeyword_7_0_0_0());
                              						
                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getExportRule());
                              							}
                              							setWithLastConsumed(current, "fingerprint", lv_fingerprint_21_0 != null, "object-fingerprint");
                              						
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalExport.g:892:5: ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) )
                            {
                            // InternalExport.g:892:5: ( (lv_resourceFingerprint_22_0= 'resource-fingerprint' ) )
                            // InternalExport.g:893:6: (lv_resourceFingerprint_22_0= 'resource-fingerprint' )
                            {
                            // InternalExport.g:893:6: (lv_resourceFingerprint_22_0= 'resource-fingerprint' )
                            // InternalExport.g:894:7: lv_resourceFingerprint_22_0= 'resource-fingerprint'
                            {
                            lv_resourceFingerprint_22_0=(Token)match(input,36,FOLLOW_34); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(lv_resourceFingerprint_22_0, grammarAccess.getExportAccess().getResourceFingerprintResourceFingerprintKeyword_7_0_1_0());
                              						
                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getExportRule());
                              							}
                              							setWithLastConsumed(current, "resourceFingerprint", lv_resourceFingerprint_22_0 != null, "resource-fingerprint");
                              						
                            }

                            }


                            }


                            }
                            break;

                    }

                    otherlv_23=(Token)match(input,24,FOLLOW_36); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_23, grammarAccess.getExportAccess().getSemicolonKeyword_7_1());
                      			
                    }

                    }
                    break;

            }

            // InternalExport.g:912:3: ( (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' ) | (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' ) )*
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
            	    // InternalExport.g:913:4: (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' )
            	    {
            	    // InternalExport.g:913:4: (otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';' )
            	    // InternalExport.g:914:5: otherlv_24= 'field' ( (lv_attributes_25_0= ruleAttribute ) ) (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )* otherlv_28= ';'
            	    {
            	    otherlv_24=(Token)match(input,37,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_24, grammarAccess.getExportAccess().getFieldKeyword_8_0_0());
            	      				
            	    }
            	    // InternalExport.g:918:5: ( (lv_attributes_25_0= ruleAttribute ) )
            	    // InternalExport.g:919:6: (lv_attributes_25_0= ruleAttribute )
            	    {
            	    // InternalExport.g:919:6: (lv_attributes_25_0= ruleAttribute )
            	    // InternalExport.g:920:7: lv_attributes_25_0= ruleAttribute
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_1_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_37);
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
            	      								"com.avaloq.tools.ddk.xtext.export.Export.Attribute");
            	      							afterParserOrEnumRuleCall();
            	      						
            	    }

            	    }


            	    }

            	    // InternalExport.g:937:5: (otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) ) )*
            	    loop26:
            	    do {
            	        int alt26=2;
            	        int LA26_0 = input.LA(1);

            	        if ( (LA26_0==23) ) {
            	            alt26=1;
            	        }


            	        switch (alt26) {
            	    	case 1 :
            	    	    // InternalExport.g:938:6: otherlv_26= ',' ( (lv_attributes_27_0= ruleAttribute ) )
            	    	    {
            	    	    otherlv_26=(Token)match(input,23,FOLLOW_4); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      						newLeafNode(otherlv_26, grammarAccess.getExportAccess().getCommaKeyword_8_0_2_0());
            	    	      					
            	    	    }
            	    	    // InternalExport.g:942:6: ( (lv_attributes_27_0= ruleAttribute ) )
            	    	    // InternalExport.g:943:7: (lv_attributes_27_0= ruleAttribute )
            	    	    {
            	    	    // InternalExport.g:943:7: (lv_attributes_27_0= ruleAttribute )
            	    	    // InternalExport.g:944:8: lv_attributes_27_0= ruleAttribute
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      								newCompositeNode(grammarAccess.getExportAccess().getAttributesAttributeParserRuleCall_8_0_2_1_0());
            	    	      							
            	    	    }
            	    	    pushFollow(FOLLOW_37);
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
            	    	      									"com.avaloq.tools.ddk.xtext.export.Export.Attribute");
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

            	    otherlv_28=(Token)match(input,24,FOLLOW_36); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_28, grammarAccess.getExportAccess().getSemicolonKeyword_8_0_3());
            	      				
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalExport.g:968:4: (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' )
            	    {
            	    // InternalExport.g:968:4: (otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';' )
            	    // InternalExport.g:969:5: otherlv_29= 'data' ( (lv_userData_30_0= ruleUserData ) ) (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )* otherlv_33= ';'
            	    {
            	    otherlv_29=(Token)match(input,38,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_29, grammarAccess.getExportAccess().getDataKeyword_8_1_0());
            	      				
            	    }
            	    // InternalExport.g:973:5: ( (lv_userData_30_0= ruleUserData ) )
            	    // InternalExport.g:974:6: (lv_userData_30_0= ruleUserData )
            	    {
            	    // InternalExport.g:974:6: (lv_userData_30_0= ruleUserData )
            	    // InternalExport.g:975:7: lv_userData_30_0= ruleUserData
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_1_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_37);
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
            	      								"com.avaloq.tools.ddk.xtext.export.Export.UserData");
            	      							afterParserOrEnumRuleCall();
            	      						
            	    }

            	    }


            	    }

            	    // InternalExport.g:992:5: (otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) ) )*
            	    loop27:
            	    do {
            	        int alt27=2;
            	        int LA27_0 = input.LA(1);

            	        if ( (LA27_0==23) ) {
            	            alt27=1;
            	        }


            	        switch (alt27) {
            	    	case 1 :
            	    	    // InternalExport.g:993:6: otherlv_31= ',' ( (lv_userData_32_0= ruleUserData ) )
            	    	    {
            	    	    otherlv_31=(Token)match(input,23,FOLLOW_4); if (state.failed) return current;
            	    	    if ( state.backtracking==0 ) {

            	    	      						newLeafNode(otherlv_31, grammarAccess.getExportAccess().getCommaKeyword_8_1_2_0());
            	    	      					
            	    	    }
            	    	    // InternalExport.g:997:6: ( (lv_userData_32_0= ruleUserData ) )
            	    	    // InternalExport.g:998:7: (lv_userData_32_0= ruleUserData )
            	    	    {
            	    	    // InternalExport.g:998:7: (lv_userData_32_0= ruleUserData )
            	    	    // InternalExport.g:999:8: lv_userData_32_0= ruleUserData
            	    	    {
            	    	    if ( state.backtracking==0 ) {

            	    	      								newCompositeNode(grammarAccess.getExportAccess().getUserDataUserDataParserRuleCall_8_1_2_1_0());
            	    	      							
            	    	    }
            	    	    pushFollow(FOLLOW_37);
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
            	    	      									"com.avaloq.tools.ddk.xtext.export.Export.UserData");
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

            	    otherlv_33=(Token)match(input,24,FOLLOW_36); if (state.failed) return current;
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

            otherlv_34=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:1031:1: entryRuleUserData returns [EObject current=null] : iv_ruleUserData= ruleUserData EOF ;
    public final EObject entryRuleUserData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUserData = null;


        try {
            // InternalExport.g:1031:49: (iv_ruleUserData= ruleUserData EOF )
            // InternalExport.g:1032:2: iv_ruleUserData= ruleUserData EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUserDataRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleUserData=ruleUserData();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUserData; 
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
    // $ANTLR end "entryRuleUserData"


    // $ANTLR start "ruleUserData"
    // InternalExport.g:1038:1: ruleUserData returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) ;
    public final EObject ruleUserData() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        EObject lv_expr_2_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1044:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) ) )
            // InternalExport.g:1045:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            {
            // InternalExport.g:1045:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) ) )
            // InternalExport.g:1046:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= '=' ( (lv_expr_2_0= ruleExpression ) )
            {
            // InternalExport.g:1046:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalExport.g:1047:4: (lv_name_0_0= RULE_ID )
            {
            // InternalExport.g:1047:4: (lv_name_0_0= RULE_ID )
            // InternalExport.g:1048:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_31); if (state.failed) return current;
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
              						"org.eclipse.xtext.common.Terminals.ID");
              				
            }

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getUserDataAccess().getEqualsSignKeyword_1());
              		
            }
            // InternalExport.g:1068:3: ( (lv_expr_2_0= ruleExpression ) )
            // InternalExport.g:1069:4: (lv_expr_2_0= ruleExpression )
            {
            // InternalExport.g:1069:4: (lv_expr_2_0= ruleExpression )
            // InternalExport.g:1070:5: lv_expr_2_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getUserDataAccess().getExprExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_2);
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
    // $ANTLR end "ruleUserData"


    // $ANTLR start "entryRuleAttribute"
    // InternalExport.g:1091:1: entryRuleAttribute returns [EObject current=null] : iv_ruleAttribute= ruleAttribute EOF ;
    public final EObject entryRuleAttribute() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAttribute = null;


        try {
            // InternalExport.g:1091:50: (iv_ruleAttribute= ruleAttribute EOF )
            // InternalExport.g:1092:2: iv_ruleAttribute= ruleAttribute EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAttributeRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleAttribute=ruleAttribute();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAttribute; 
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
    // $ANTLR end "entryRuleAttribute"


    // $ANTLR start "ruleAttribute"
    // InternalExport.g:1098:1: ruleAttribute returns [EObject current=null] : ( (otherlv_0= RULE_ID ) ) ;
    public final EObject ruleAttribute() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;


        	enterRule();

        try {
            // InternalExport.g:1104:2: ( ( (otherlv_0= RULE_ID ) ) )
            // InternalExport.g:1105:2: ( (otherlv_0= RULE_ID ) )
            {
            // InternalExport.g:1105:2: ( (otherlv_0= RULE_ID ) )
            // InternalExport.g:1106:3: (otherlv_0= RULE_ID )
            {
            // InternalExport.g:1106:3: (otherlv_0= RULE_ID )
            // InternalExport.g:1107:4: otherlv_0= RULE_ID
            {
            if ( state.backtracking==0 ) {

              				if (current==null) {
              					current = createModelElement(grammarAccess.getAttributeRule());
              				}
              			
            }
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:1121:1: entryRuleQualifiedID returns [String current=null] : iv_ruleQualifiedID= ruleQualifiedID EOF ;
    public final String entryRuleQualifiedID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedID = null;


        try {
            // InternalExport.g:1121:51: (iv_ruleQualifiedID= ruleQualifiedID EOF )
            // InternalExport.g:1122:2: iv_ruleQualifiedID= ruleQualifiedID EOF
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
    // InternalExport.g:1128:1: ruleQualifiedID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalExport.g:1134:2: ( (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* ) )
            // InternalExport.g:1135:2: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            {
            // InternalExport.g:1135:2: (this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )* )
            // InternalExport.g:1136:3: this_ID_0= RULE_ID (kw= '::' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_38); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(this_ID_0);
              		
            }
            if ( state.backtracking==0 ) {

              			newLeafNode(this_ID_0, grammarAccess.getQualifiedIDAccess().getIDTerminalRuleCall_0());
              		
            }
            // InternalExport.g:1143:3: (kw= '::' this_ID_2= RULE_ID )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0==39) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalExport.g:1144:4: kw= '::' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,39,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				current.merge(kw);
            	      				newLeafNode(kw, grammarAccess.getQualifiedIDAccess().getColonColonKeyword_1_0());
            	      			
            	    }
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_38); if (state.failed) return current;
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
    // InternalExport.g:1161:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalExport.g:1161:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalExport.g:1162:2: iv_ruleExpression= ruleExpression EOF
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
    // InternalExport.g:1168:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;



        	enterRule();

        try {
            // InternalExport.g:1174:2: ( (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) )
            // InternalExport.g:1175:2: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            {
            // InternalExport.g:1175:2: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            int alt30=3;
            alt30 = dfa30.predict(input);
            switch (alt30) {
                case 1 :
                    // InternalExport.g:1176:3: this_LetExpression_0= ruleLetExpression
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
                    // InternalExport.g:1185:3: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    {
                    // InternalExport.g:1185:3: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    // InternalExport.g:1186:4: ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression
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
                    // InternalExport.g:1197:3: this_ChainExpression_2= ruleChainExpression
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
    // InternalExport.g:1209:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // InternalExport.g:1209:54: (iv_ruleLetExpression= ruleLetExpression EOF )
            // InternalExport.g:1210:2: iv_ruleLetExpression= ruleLetExpression EOF
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
    // InternalExport.g:1216:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // InternalExport.g:1222:2: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // InternalExport.g:1223:2: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // InternalExport.g:1223:2: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // InternalExport.g:1224:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,40,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
              		
            }
            // InternalExport.g:1228:3: ( (lv_identifier_1_0= ruleIdentifier ) )
            // InternalExport.g:1229:4: (lv_identifier_1_0= ruleIdentifier )
            {
            // InternalExport.g:1229:4: (lv_identifier_1_0= ruleIdentifier )
            // InternalExport.g:1230:5: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_31);
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

            otherlv_2=(Token)match(input,22,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
              		
            }
            // InternalExport.g:1251:3: ( (lv_varExpr_3_0= ruleExpression ) )
            // InternalExport.g:1252:4: (lv_varExpr_3_0= ruleExpression )
            {
            // InternalExport.g:1252:4: (lv_varExpr_3_0= ruleExpression )
            // InternalExport.g:1253:5: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_39);
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

            otherlv_4=(Token)match(input,41,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
              		
            }
            // InternalExport.g:1274:3: ( (lv_target_5_0= ruleExpression ) )
            // InternalExport.g:1275:4: (lv_target_5_0= ruleExpression )
            {
            // InternalExport.g:1275:4: (lv_target_5_0= ruleExpression )
            // InternalExport.g:1276:5: lv_target_5_0= ruleExpression
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
    // InternalExport.g:1297:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // InternalExport.g:1297:57: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // InternalExport.g:1298:2: iv_ruleCastedExpression= ruleCastedExpression EOF
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
    // InternalExport.g:1304:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1310:2: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // InternalExport.g:1311:2: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // InternalExport.g:1311:2: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // InternalExport.g:1312:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,28,FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
              		
            }
            // InternalExport.g:1316:3: ( (lv_type_1_0= ruleType ) )
            // InternalExport.g:1317:4: (lv_type_1_0= ruleType )
            {
            // InternalExport.g:1317:4: (lv_type_1_0= ruleType )
            // InternalExport.g:1318:5: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_24);
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

            otherlv_2=(Token)match(input,29,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
              		
            }
            // InternalExport.g:1339:3: ( (lv_target_3_0= ruleExpression ) )
            // InternalExport.g:1340:4: (lv_target_3_0= ruleExpression )
            {
            // InternalExport.g:1340:4: (lv_target_3_0= ruleExpression )
            // InternalExport.g:1341:5: lv_target_3_0= ruleExpression
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
    // InternalExport.g:1362:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // InternalExport.g:1362:56: (iv_ruleChainExpression= ruleChainExpression EOF )
            // InternalExport.g:1363:2: iv_ruleChainExpression= ruleChainExpression EOF
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
    // InternalExport.g:1369:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1375:2: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // InternalExport.g:1376:2: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // InternalExport.g:1376:2: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // InternalExport.g:1377:3: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_41);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_ChainedExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:1385:3: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==42) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalExport.g:1386:4: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // InternalExport.g:1386:4: ()
            	    // InternalExport.g:1387:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    otherlv_2=(Token)match(input,42,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	      			
            	    }
            	    // InternalExport.g:1397:4: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // InternalExport.g:1398:5: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // InternalExport.g:1398:5: (lv_next_3_0= ruleChainedExpression )
            	    // InternalExport.g:1399:6: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_41);
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
    // InternalExport.g:1421:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // InternalExport.g:1421:58: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // InternalExport.g:1422:2: iv_ruleChainedExpression= ruleChainedExpression EOF
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
    // InternalExport.g:1428:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;



        	enterRule();

        try {
            // InternalExport.g:1434:2: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // InternalExport.g:1435:2: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // InternalExport.g:1435:2: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
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
                    // InternalExport.g:1436:3: this_IfExpressionKw_0= ruleIfExpressionKw
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
                    // InternalExport.g:1445:3: this_IfExpressionTri_1= ruleIfExpressionTri
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
                    // InternalExport.g:1454:3: this_SwitchExpression_2= ruleSwitchExpression
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
    // InternalExport.g:1466:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // InternalExport.g:1466:56: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // InternalExport.g:1467:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
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
    // InternalExport.g:1473:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1479:2: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // InternalExport.g:1480:2: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // InternalExport.g:1480:2: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // InternalExport.g:1481:3: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_42);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_OrExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:1489:3: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==43) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalExport.g:1490:4: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // InternalExport.g:1490:4: ()
                    // InternalExport.g:1491:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElementAndSet(
                      						grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_2=(Token)match(input,43,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                      			
                    }
                    // InternalExport.g:1501:4: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // InternalExport.g:1502:5: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // InternalExport.g:1502:5: (lv_thenPart_3_0= ruleChainedExpression )
                    // InternalExport.g:1503:6: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_39);
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

                    otherlv_4=(Token)match(input,41,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                      			
                    }
                    // InternalExport.g:1524:4: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // InternalExport.g:1525:5: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // InternalExport.g:1525:5: (lv_elsePart_5_0= ruleChainedExpression )
                    // InternalExport.g:1526:6: lv_elsePart_5_0= ruleChainedExpression
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
    // InternalExport.g:1548:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // InternalExport.g:1548:55: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // InternalExport.g:1549:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
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
    // InternalExport.g:1555:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) ;
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
            // InternalExport.g:1561:2: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) )
            // InternalExport.g:1562:2: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            {
            // InternalExport.g:1562:2: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            // InternalExport.g:1563:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            {
            otherlv_0=(Token)match(input,44,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
              		
            }
            // InternalExport.g:1567:3: ( (lv_condition_1_0= ruleChainedExpression ) )
            // InternalExport.g:1568:4: (lv_condition_1_0= ruleChainedExpression )
            {
            // InternalExport.g:1568:4: (lv_condition_1_0= ruleChainedExpression )
            // InternalExport.g:1569:5: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_43);
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

            otherlv_2=(Token)match(input,45,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
              		
            }
            // InternalExport.g:1590:3: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // InternalExport.g:1591:4: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // InternalExport.g:1591:4: (lv_thenPart_3_0= ruleChainedExpression )
            // InternalExport.g:1592:5: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_44);
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

            // InternalExport.g:1609:3: ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
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
                    // InternalExport.g:1610:4: ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    {
                    // InternalExport.g:1611:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    // InternalExport.g:1612:5: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,46,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0_0());
                      				
                    }
                    // InternalExport.g:1616:5: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // InternalExport.g:1617:6: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // InternalExport.g:1617:6: (lv_elsePart_5_0= ruleChainedExpression )
                    // InternalExport.g:1618:7: lv_elsePart_5_0= ruleChainedExpression
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
    // InternalExport.g:1641:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // InternalExport.g:1641:57: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // InternalExport.g:1642:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
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
    // InternalExport.g:1648:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // InternalExport.g:1654:2: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // InternalExport.g:1655:2: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // InternalExport.g:1655:2: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // InternalExport.g:1656:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,47,FOLLOW_45); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
              		
            }
            // InternalExport.g:1660:3: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==28) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalExport.g:1661:4: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,28,FOLLOW_46); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                      			
                    }
                    // InternalExport.g:1665:4: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // InternalExport.g:1666:5: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // InternalExport.g:1666:5: (lv_switchExpr_2_0= ruleOrExpression )
                    // InternalExport.g:1667:6: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_24);
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

                    otherlv_3=(Token)match(input,29,FOLLOW_9); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                      			
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,16,FOLLOW_47); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalExport.g:1693:3: ( (lv_case_5_0= ruleCase ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==49) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalExport.g:1694:4: (lv_case_5_0= ruleCase )
            	    {
            	    // InternalExport.g:1694:4: (lv_case_5_0= ruleCase )
            	    // InternalExport.g:1695:5: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_47);
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
            	    break loop36;
                }
            } while (true);

            otherlv_6=(Token)match(input,48,FOLLOW_39); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
              		
            }
            otherlv_7=(Token)match(input,41,FOLLOW_46); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
              		
            }
            // InternalExport.g:1720:3: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // InternalExport.g:1721:4: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // InternalExport.g:1721:4: (lv_defaultExpr_8_0= ruleOrExpression )
            // InternalExport.g:1722:5: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0());
              				
            }
            pushFollow(FOLLOW_48);
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

            otherlv_9=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:1747:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // InternalExport.g:1747:45: (iv_ruleCase= ruleCase EOF )
            // InternalExport.g:1748:2: iv_ruleCase= ruleCase EOF
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
    // InternalExport.g:1754:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1760:2: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // InternalExport.g:1761:2: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // InternalExport.g:1761:2: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // InternalExport.g:1762:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,49,FOLLOW_46); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
              		
            }
            // InternalExport.g:1766:3: ( (lv_condition_1_0= ruleOrExpression ) )
            // InternalExport.g:1767:4: (lv_condition_1_0= ruleOrExpression )
            {
            // InternalExport.g:1767:4: (lv_condition_1_0= ruleOrExpression )
            // InternalExport.g:1768:5: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_39);
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

            otherlv_2=(Token)match(input,41,FOLLOW_46); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
              		
            }
            // InternalExport.g:1789:3: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // InternalExport.g:1790:4: (lv_thenPar_3_0= ruleOrExpression )
            {
            // InternalExport.g:1790:4: (lv_thenPar_3_0= ruleOrExpression )
            // InternalExport.g:1791:5: lv_thenPar_3_0= ruleOrExpression
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
    // InternalExport.g:1812:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // InternalExport.g:1812:53: (iv_ruleOrExpression= ruleOrExpression EOF )
            // InternalExport.g:1813:2: iv_ruleOrExpression= ruleOrExpression EOF
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
    // InternalExport.g:1819:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1825:2: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // InternalExport.g:1826:2: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // InternalExport.g:1826:2: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // InternalExport.g:1827:3: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_49);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_AndExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:1835:3: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==50) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalExport.g:1836:4: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // InternalExport.g:1836:4: ()
            	    // InternalExport.g:1837:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExport.g:1843:4: ( (lv_operator_2_0= '||' ) )
            	    // InternalExport.g:1844:5: (lv_operator_2_0= '||' )
            	    {
            	    // InternalExport.g:1844:5: (lv_operator_2_0= '||' )
            	    // InternalExport.g:1845:6: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,50,FOLLOW_46); if (state.failed) return current;
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

            	    // InternalExport.g:1857:4: ( (lv_right_3_0= ruleAndExpression ) )
            	    // InternalExport.g:1858:5: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // InternalExport.g:1858:5: (lv_right_3_0= ruleAndExpression )
            	    // InternalExport.g:1859:6: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_49);
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
    // InternalExport.g:1881:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // InternalExport.g:1881:54: (iv_ruleAndExpression= ruleAndExpression EOF )
            // InternalExport.g:1882:2: iv_ruleAndExpression= ruleAndExpression EOF
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
    // InternalExport.g:1888:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1894:2: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // InternalExport.g:1895:2: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // InternalExport.g:1895:2: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // InternalExport.g:1896:3: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_50);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_ImpliesExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:1904:3: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==51) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalExport.g:1905:4: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // InternalExport.g:1905:4: ()
            	    // InternalExport.g:1906:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExport.g:1912:4: ( (lv_operator_2_0= '&&' ) )
            	    // InternalExport.g:1913:5: (lv_operator_2_0= '&&' )
            	    {
            	    // InternalExport.g:1913:5: (lv_operator_2_0= '&&' )
            	    // InternalExport.g:1914:6: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,51,FOLLOW_46); if (state.failed) return current;
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

            	    // InternalExport.g:1926:4: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // InternalExport.g:1927:5: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // InternalExport.g:1927:5: (lv_right_3_0= ruleImpliesExpression )
            	    // InternalExport.g:1928:6: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_50);
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
    // InternalExport.g:1950:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // InternalExport.g:1950:58: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // InternalExport.g:1951:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
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
    // InternalExport.g:1957:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:1963:2: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // InternalExport.g:1964:2: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // InternalExport.g:1964:2: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // InternalExport.g:1965:3: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_51);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_RelationalExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:1973:3: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==52) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalExport.g:1974:4: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // InternalExport.g:1974:4: ()
            	    // InternalExport.g:1975:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExport.g:1981:4: ( (lv_operator_2_0= 'implies' ) )
            	    // InternalExport.g:1982:5: (lv_operator_2_0= 'implies' )
            	    {
            	    // InternalExport.g:1982:5: (lv_operator_2_0= 'implies' )
            	    // InternalExport.g:1983:6: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,52,FOLLOW_46); if (state.failed) return current;
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

            	    // InternalExport.g:1995:4: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // InternalExport.g:1996:5: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // InternalExport.g:1996:5: (lv_right_3_0= ruleRelationalExpression )
            	    // InternalExport.g:1997:6: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_51);
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
    // InternalExport.g:2019:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // InternalExport.g:2019:61: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // InternalExport.g:2020:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
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
    // InternalExport.g:2026:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // InternalExport.g:2032:2: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // InternalExport.g:2033:2: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // InternalExport.g:2033:2: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // InternalExport.g:2034:3: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_52);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_AdditiveExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:2042:3: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( ((LA41_0>=53 && LA41_0<=58)) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalExport.g:2043:4: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // InternalExport.g:2043:4: ()
            	    // InternalExport.g:2044:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExport.g:2050:4: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // InternalExport.g:2051:5: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // InternalExport.g:2051:5: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // InternalExport.g:2052:6: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // InternalExport.g:2052:6: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
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
            	            // InternalExport.g:2053:7: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,53,FOLLOW_46); if (state.failed) return current;
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
            	            // InternalExport.g:2064:7: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,54,FOLLOW_46); if (state.failed) return current;
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
            	            // InternalExport.g:2075:7: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,55,FOLLOW_46); if (state.failed) return current;
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
            	            // InternalExport.g:2086:7: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,56,FOLLOW_46); if (state.failed) return current;
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
            	            // InternalExport.g:2097:7: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,57,FOLLOW_46); if (state.failed) return current;
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
            	            // InternalExport.g:2108:7: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,58,FOLLOW_46); if (state.failed) return current;
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

            	    // InternalExport.g:2121:4: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // InternalExport.g:2122:5: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // InternalExport.g:2122:5: (lv_right_3_0= ruleAdditiveExpression )
            	    // InternalExport.g:2123:6: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_52);
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
    // InternalExport.g:2145:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // InternalExport.g:2145:59: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // InternalExport.g:2146:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
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
    // InternalExport.g:2152:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:2158:2: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // InternalExport.g:2159:2: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // InternalExport.g:2159:2: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // InternalExport.g:2160:3: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_53);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_MultiplicativeExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:2168:3: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==25||LA43_0==59) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // InternalExport.g:2169:4: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // InternalExport.g:2169:4: ()
            	    // InternalExport.g:2170:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndAdd(
            	      						grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExport.g:2176:4: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // InternalExport.g:2177:5: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // InternalExport.g:2177:5: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // InternalExport.g:2178:6: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // InternalExport.g:2178:6: (lv_name_2_1= '+' | lv_name_2_2= '-' )
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
            	            // InternalExport.g:2179:7: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,25,FOLLOW_46); if (state.failed) return current;
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
            	            // InternalExport.g:2190:7: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,59,FOLLOW_46); if (state.failed) return current;
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

            	    // InternalExport.g:2203:4: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // InternalExport.g:2204:5: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // InternalExport.g:2204:5: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // InternalExport.g:2205:6: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_53);
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
    // InternalExport.g:2227:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // InternalExport.g:2227:65: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // InternalExport.g:2228:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
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
    // InternalExport.g:2234:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;



        	enterRule();

        try {
            // InternalExport.g:2240:2: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // InternalExport.g:2241:2: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // InternalExport.g:2241:2: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // InternalExport.g:2242:3: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_54);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_UnaryOrInfixExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:2250:3: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( ((LA45_0>=60 && LA45_0<=61)) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalExport.g:2251:4: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // InternalExport.g:2251:4: ()
            	    // InternalExport.g:2252:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndAdd(
            	      						grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExport.g:2258:4: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // InternalExport.g:2259:5: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // InternalExport.g:2259:5: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // InternalExport.g:2260:6: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // InternalExport.g:2260:6: (lv_name_2_1= '*' | lv_name_2_2= '/' )
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
            	            // InternalExport.g:2261:7: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,60,FOLLOW_46); if (state.failed) return current;
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
            	            // InternalExport.g:2272:7: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,61,FOLLOW_46); if (state.failed) return current;
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

            	    // InternalExport.g:2285:4: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // InternalExport.g:2286:5: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // InternalExport.g:2286:5: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // InternalExport.g:2287:6: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_54);
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
    // InternalExport.g:2309:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // InternalExport.g:2309:63: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // InternalExport.g:2310:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
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
    // InternalExport.g:2316:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;



        	enterRule();

        try {
            // InternalExport.g:2322:2: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // InternalExport.g:2323:2: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // InternalExport.g:2323:2: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
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
                    // InternalExport.g:2324:3: this_UnaryExpression_0= ruleUnaryExpression
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
                    // InternalExport.g:2333:3: this_InfixExpression_1= ruleInfixExpression
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
    // InternalExport.g:2345:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // InternalExport.g:2345:56: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // InternalExport.g:2346:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
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
    // InternalExport.g:2352:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;



        	enterRule();

        try {
            // InternalExport.g:2358:2: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // InternalExport.g:2359:2: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // InternalExport.g:2359:2: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // InternalExport.g:2360:3: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // InternalExport.g:2360:3: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // InternalExport.g:2361:4: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // InternalExport.g:2361:4: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // InternalExport.g:2362:5: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // InternalExport.g:2362:5: (lv_name_0_1= '!' | lv_name_0_2= '-' )
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
                    // InternalExport.g:2363:6: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,62,FOLLOW_46); if (state.failed) return current;
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
                    // InternalExport.g:2374:6: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,59,FOLLOW_46); if (state.failed) return current;
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

            // InternalExport.g:2387:3: ( (lv_params_1_0= ruleInfixExpression ) )
            // InternalExport.g:2388:4: (lv_params_1_0= ruleInfixExpression )
            {
            // InternalExport.g:2388:4: (lv_params_1_0= ruleInfixExpression )
            // InternalExport.g:2389:5: lv_params_1_0= ruleInfixExpression
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
    // InternalExport.g:2410:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // InternalExport.g:2410:56: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // InternalExport.g:2411:2: iv_ruleInfixExpression= ruleInfixExpression EOF
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
    // InternalExport.g:2417:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // InternalExport.g:2423:2: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // InternalExport.g:2424:2: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // InternalExport.g:2424:2: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // InternalExport.g:2425:3: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_55);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_PrimaryExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExport.g:2433:3: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
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
            	    // InternalExport.g:2434:4: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // InternalExport.g:2434:4: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // InternalExport.g:2435:5: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // InternalExport.g:2435:5: ()
            	    // InternalExport.g:2436:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_2=(Token)match(input,63,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	      				
            	    }
            	    // InternalExport.g:2446:5: ( (lv_name_3_0= ruleIdentifier ) )
            	    // InternalExport.g:2447:6: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // InternalExport.g:2447:6: (lv_name_3_0= ruleIdentifier )
            	    // InternalExport.g:2448:7: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_23);
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

            	    otherlv_4=(Token)match(input,28,FOLLOW_56); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	      				
            	    }
            	    // InternalExport.g:2469:5: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt49=2;
            	    int LA49_0 = input.LA(1);

            	    if ( ((LA49_0>=RULE_ID && LA49_0<=RULE_REAL)||LA49_0==16||LA49_0==28||LA49_0==40||LA49_0==44||LA49_0==47||LA49_0==59||LA49_0==62||(LA49_0>=64 && LA49_0<=72)||(LA49_0>=74 && LA49_0<=81)) ) {
            	        alt49=1;
            	    }
            	    switch (alt49) {
            	        case 1 :
            	            // InternalExport.g:2470:6: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // InternalExport.g:2470:6: ( (lv_params_5_0= ruleExpression ) )
            	            // InternalExport.g:2471:7: (lv_params_5_0= ruleExpression )
            	            {
            	            // InternalExport.g:2471:7: (lv_params_5_0= ruleExpression )
            	            // InternalExport.g:2472:8: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {

            	              								newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0());
            	              							
            	            }
            	            pushFollow(FOLLOW_57);
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

            	            // InternalExport.g:2489:6: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop48:
            	            do {
            	                int alt48=2;
            	                int LA48_0 = input.LA(1);

            	                if ( (LA48_0==23) ) {
            	                    alt48=1;
            	                }


            	                switch (alt48) {
            	            	case 1 :
            	            	    // InternalExport.g:2490:7: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,23,FOLLOW_15); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	      							newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	      						
            	            	    }
            	            	    // InternalExport.g:2494:7: ( (lv_params_7_0= ruleExpression ) )
            	            	    // InternalExport.g:2495:8: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // InternalExport.g:2495:8: (lv_params_7_0= ruleExpression )
            	            	    // InternalExport.g:2496:9: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {

            	            	      									newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0());
            	            	      								
            	            	    }
            	            	    pushFollow(FOLLOW_57);
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
            	            	    break loop48;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,29,FOLLOW_55); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	      				
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalExport.g:2521:4: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // InternalExport.g:2521:4: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // InternalExport.g:2522:5: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // InternalExport.g:2522:5: ()
            	    // InternalExport.g:2523:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_10=(Token)match(input,63,FOLLOW_40); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	      				
            	    }
            	    // InternalExport.g:2533:5: ( (lv_type_11_0= ruleType ) )
            	    // InternalExport.g:2534:6: (lv_type_11_0= ruleType )
            	    {
            	    // InternalExport.g:2534:6: (lv_type_11_0= ruleType )
            	    // InternalExport.g:2535:7: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_55);
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
            	    // InternalExport.g:2554:4: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // InternalExport.g:2554:4: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // InternalExport.g:2555:5: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // InternalExport.g:2555:5: ()
            	    // InternalExport.g:2556:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_13=(Token)match(input,63,FOLLOW_58); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	      				
            	    }
            	    // InternalExport.g:2566:5: ( (lv_name_14_0= 'typeSelect' ) )
            	    // InternalExport.g:2567:6: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // InternalExport.g:2567:6: (lv_name_14_0= 'typeSelect' )
            	    // InternalExport.g:2568:7: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,64,FOLLOW_23); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,28,FOLLOW_40); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	      				
            	    }
            	    // InternalExport.g:2584:5: ( (lv_type_16_0= ruleType ) )
            	    // InternalExport.g:2585:6: (lv_type_16_0= ruleType )
            	    {
            	    // InternalExport.g:2585:6: (lv_type_16_0= ruleType )
            	    // InternalExport.g:2586:7: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_24);
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

            	    otherlv_17=(Token)match(input,29,FOLLOW_55); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	      				
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalExport.g:2609:4: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // InternalExport.g:2609:4: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // InternalExport.g:2610:5: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // InternalExport.g:2610:5: ()
            	    // InternalExport.g:2611:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_19=(Token)match(input,63,FOLLOW_59); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	      				
            	    }
            	    // InternalExport.g:2621:5: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // InternalExport.g:2622:6: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // InternalExport.g:2622:6: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // InternalExport.g:2623:7: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // InternalExport.g:2623:7: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
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
            	            // InternalExport.g:2624:8: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,65,FOLLOW_23); if (state.failed) return current;
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
            	            // InternalExport.g:2635:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,66,FOLLOW_23); if (state.failed) return current;
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
            	            // InternalExport.g:2646:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,67,FOLLOW_23); if (state.failed) return current;
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
            	            // InternalExport.g:2657:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,68,FOLLOW_23); if (state.failed) return current;
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
            	            // InternalExport.g:2668:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,69,FOLLOW_23); if (state.failed) return current;
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
            	            // InternalExport.g:2679:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,70,FOLLOW_23); if (state.failed) return current;
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
            	            // InternalExport.g:2690:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,71,FOLLOW_23); if (state.failed) return current;
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
            	            // InternalExport.g:2701:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,72,FOLLOW_23); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,28,FOLLOW_15); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	      				
            	    }
            	    // InternalExport.g:2718:5: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
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
            	            // InternalExport.g:2719:6: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // InternalExport.g:2719:6: ( (lv_var_22_0= ruleIdentifier ) )
            	            // InternalExport.g:2720:7: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // InternalExport.g:2720:7: (lv_var_22_0= ruleIdentifier )
            	            // InternalExport.g:2721:8: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {

            	              								newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0());
            	              							
            	            }
            	            pushFollow(FOLLOW_60);
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

            	            otherlv_23=(Token)match(input,73,FOLLOW_15); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	              					
            	            }

            	            }
            	            break;

            	    }

            	    // InternalExport.g:2743:5: ( (lv_exp_24_0= ruleExpression ) )
            	    // InternalExport.g:2744:6: (lv_exp_24_0= ruleExpression )
            	    {
            	    // InternalExport.g:2744:6: (lv_exp_24_0= ruleExpression )
            	    // InternalExport.g:2745:7: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_24);
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

            	    otherlv_25=(Token)match(input,29,FOLLOW_55); if (state.failed) return current;
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
    // InternalExport.g:2772:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // InternalExport.g:2772:58: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // InternalExport.g:2773:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
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
    // InternalExport.g:2779:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // InternalExport.g:2785:2: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // InternalExport.g:2786:2: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // InternalExport.g:2786:2: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
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
                    // InternalExport.g:2787:3: this_Literal_0= ruleLiteral
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
                    // InternalExport.g:2796:3: this_FeatureCall_1= ruleFeatureCall
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
                    // InternalExport.g:2805:3: this_ListLiteral_2= ruleListLiteral
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
                    // InternalExport.g:2814:3: this_ConstructorCallExpression_3= ruleConstructorCallExpression
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
                    // InternalExport.g:2823:3: this_GlobalVarExpression_4= ruleGlobalVarExpression
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
                    // InternalExport.g:2832:3: this_ParanthesizedExpression_5= ruleParanthesizedExpression
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
    // InternalExport.g:2844:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // InternalExport.g:2844:48: (iv_ruleLiteral= ruleLiteral EOF )
            // InternalExport.g:2845:2: iv_ruleLiteral= ruleLiteral EOF
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
    // InternalExport.g:2851:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;



        	enterRule();

        try {
            // InternalExport.g:2857:2: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // InternalExport.g:2858:2: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // InternalExport.g:2858:2: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
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
                    // InternalExport.g:2859:3: this_BooleanLiteral_0= ruleBooleanLiteral
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
                    // InternalExport.g:2868:3: this_IntegerLiteral_1= ruleIntegerLiteral
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
                    // InternalExport.g:2877:3: this_NullLiteral_2= ruleNullLiteral
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
                    // InternalExport.g:2886:3: this_RealLiteral_3= ruleRealLiteral
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
                    // InternalExport.g:2895:3: this_StringLiteral_4= ruleStringLiteral
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
    // InternalExport.g:2907:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // InternalExport.g:2907:55: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // InternalExport.g:2908:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
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
    // InternalExport.g:2914:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;


        	enterRule();

        try {
            // InternalExport.g:2920:2: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // InternalExport.g:2921:2: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // InternalExport.g:2921:2: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // InternalExport.g:2922:3: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // InternalExport.g:2922:3: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // InternalExport.g:2923:4: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // InternalExport.g:2923:4: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
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
                    // InternalExport.g:2924:5: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,74,FOLLOW_2); if (state.failed) return current;
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
                    // InternalExport.g:2935:5: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,75,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:2951:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // InternalExport.g:2951:55: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // InternalExport.g:2952:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
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
    // InternalExport.g:2958:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExport.g:2964:2: ( ( (lv_val_0_0= RULE_INT ) ) )
            // InternalExport.g:2965:2: ( (lv_val_0_0= RULE_INT ) )
            {
            // InternalExport.g:2965:2: ( (lv_val_0_0= RULE_INT ) )
            // InternalExport.g:2966:3: (lv_val_0_0= RULE_INT )
            {
            // InternalExport.g:2966:3: (lv_val_0_0= RULE_INT )
            // InternalExport.g:2967:4: lv_val_0_0= RULE_INT
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
    // InternalExport.g:2986:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // InternalExport.g:2986:52: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // InternalExport.g:2987:2: iv_ruleNullLiteral= ruleNullLiteral EOF
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
    // InternalExport.g:2993:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExport.g:2999:2: ( ( (lv_val_0_0= 'null' ) ) )
            // InternalExport.g:3000:2: ( (lv_val_0_0= 'null' ) )
            {
            // InternalExport.g:3000:2: ( (lv_val_0_0= 'null' ) )
            // InternalExport.g:3001:3: (lv_val_0_0= 'null' )
            {
            // InternalExport.g:3001:3: (lv_val_0_0= 'null' )
            // InternalExport.g:3002:4: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,76,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:3017:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // InternalExport.g:3017:52: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // InternalExport.g:3018:2: iv_ruleRealLiteral= ruleRealLiteral EOF
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
    // InternalExport.g:3024:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExport.g:3030:2: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // InternalExport.g:3031:2: ( (lv_val_0_0= RULE_REAL ) )
            {
            // InternalExport.g:3031:2: ( (lv_val_0_0= RULE_REAL ) )
            // InternalExport.g:3032:3: (lv_val_0_0= RULE_REAL )
            {
            // InternalExport.g:3032:3: (lv_val_0_0= RULE_REAL )
            // InternalExport.g:3033:4: lv_val_0_0= RULE_REAL
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
    // InternalExport.g:3052:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // InternalExport.g:3052:54: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalExport.g:3053:2: iv_ruleStringLiteral= ruleStringLiteral EOF
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
    // InternalExport.g:3059:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExport.g:3065:2: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // InternalExport.g:3066:2: ( (lv_val_0_0= RULE_STRING ) )
            {
            // InternalExport.g:3066:2: ( (lv_val_0_0= RULE_STRING ) )
            // InternalExport.g:3067:3: (lv_val_0_0= RULE_STRING )
            {
            // InternalExport.g:3067:3: (lv_val_0_0= RULE_STRING )
            // InternalExport.g:3068:4: lv_val_0_0= RULE_STRING
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
    // InternalExport.g:3087:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // InternalExport.g:3087:64: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // InternalExport.g:3088:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
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
    // InternalExport.g:3094:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;



        	enterRule();

        try {
            // InternalExport.g:3100:2: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // InternalExport.g:3101:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // InternalExport.g:3101:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // InternalExport.g:3102:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
              		
            }
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1());
              		
            }
            pushFollow(FOLLOW_24);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Expression_1;
              			afterParserOrEnumRuleCall();
              		
            }
            otherlv_2=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:3122:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // InternalExport.g:3122:60: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // InternalExport.g:3123:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
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
    // InternalExport.g:3129:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalExport.g:3135:2: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // InternalExport.g:3136:2: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // InternalExport.g:3136:2: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // InternalExport.g:3137:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,77,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
              		
            }
            // InternalExport.g:3141:3: ( (lv_name_1_0= ruleIdentifier ) )
            // InternalExport.g:3142:4: (lv_name_1_0= ruleIdentifier )
            {
            // InternalExport.g:3142:4: (lv_name_1_0= ruleIdentifier )
            // InternalExport.g:3143:5: lv_name_1_0= ruleIdentifier
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
    // InternalExport.g:3164:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // InternalExport.g:3164:52: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // InternalExport.g:3165:2: iv_ruleFeatureCall= ruleFeatureCall EOF
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
    // InternalExport.g:3171:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;



        	enterRule();

        try {
            // InternalExport.g:3177:2: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // InternalExport.g:3178:2: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // InternalExport.g:3178:2: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
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
                    // InternalExport.g:3179:3: this_OperationCall_0= ruleOperationCall
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
                    // InternalExport.g:3188:3: ( (lv_type_1_0= ruleType ) )
                    {
                    // InternalExport.g:3188:3: ( (lv_type_1_0= ruleType ) )
                    // InternalExport.g:3189:4: (lv_type_1_0= ruleType )
                    {
                    // InternalExport.g:3189:4: (lv_type_1_0= ruleType )
                    // InternalExport.g:3190:5: lv_type_1_0= ruleType
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
                    // InternalExport.g:3208:3: this_CollectionExpression_2= ruleCollectionExpression
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
                    // InternalExport.g:3217:3: this_TypeSelectExpression_3= ruleTypeSelectExpression
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
    // InternalExport.g:3229:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // InternalExport.g:3229:54: (iv_ruleOperationCall= ruleOperationCall EOF )
            // InternalExport.g:3230:2: iv_ruleOperationCall= ruleOperationCall EOF
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
    // InternalExport.g:3236:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // InternalExport.g:3242:2: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // InternalExport.g:3243:2: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // InternalExport.g:3243:2: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // InternalExport.g:3244:3: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // InternalExport.g:3244:3: ( (lv_name_0_0= ruleIdentifier ) )
            // InternalExport.g:3245:4: (lv_name_0_0= ruleIdentifier )
            {
            // InternalExport.g:3245:4: (lv_name_0_0= ruleIdentifier )
            // InternalExport.g:3246:5: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_23);
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

            otherlv_1=(Token)match(input,28,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalExport.g:3267:3: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( ((LA58_0>=RULE_ID && LA58_0<=RULE_REAL)||LA58_0==16||LA58_0==28||LA58_0==40||LA58_0==44||LA58_0==47||LA58_0==59||LA58_0==62||(LA58_0>=64 && LA58_0<=72)||(LA58_0>=74 && LA58_0<=81)) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // InternalExport.g:3268:4: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // InternalExport.g:3268:4: ( (lv_params_2_0= ruleExpression ) )
                    // InternalExport.g:3269:5: (lv_params_2_0= ruleExpression )
                    {
                    // InternalExport.g:3269:5: (lv_params_2_0= ruleExpression )
                    // InternalExport.g:3270:6: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_57);
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

                    // InternalExport.g:3287:4: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==23) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // InternalExport.g:3288:5: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,23,FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	      				
                    	    }
                    	    // InternalExport.g:3292:5: ( (lv_params_4_0= ruleExpression ) )
                    	    // InternalExport.g:3293:6: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // InternalExport.g:3293:6: (lv_params_4_0= ruleExpression )
                    	    // InternalExport.g:3294:7: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_57);
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
                    	    break loop57;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:3321:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // InternalExport.g:3321:52: (iv_ruleListLiteral= ruleListLiteral EOF )
            // InternalExport.g:3322:2: iv_ruleListLiteral= ruleListLiteral EOF
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
    // InternalExport.g:3328:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;



        	enterRule();

        try {
            // InternalExport.g:3334:2: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // InternalExport.g:3335:2: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // InternalExport.g:3335:2: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // InternalExport.g:3336:3: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // InternalExport.g:3336:3: ()
            // InternalExport.g:3337:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getListLiteralAccess().getListLiteralAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,16,FOLLOW_61); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
              		
            }
            // InternalExport.g:3347:3: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( ((LA60_0>=RULE_ID && LA60_0<=RULE_REAL)||LA60_0==16||LA60_0==28||LA60_0==40||LA60_0==44||LA60_0==47||LA60_0==59||LA60_0==62||(LA60_0>=64 && LA60_0<=72)||(LA60_0>=74 && LA60_0<=81)) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // InternalExport.g:3348:4: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // InternalExport.g:3348:4: ( (lv_elements_2_0= ruleExpression ) )
                    // InternalExport.g:3349:5: (lv_elements_2_0= ruleExpression )
                    {
                    // InternalExport.g:3349:5: (lv_elements_2_0= ruleExpression )
                    // InternalExport.g:3350:6: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_62);
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

                    // InternalExport.g:3367:4: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==23) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // InternalExport.g:3368:5: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,23,FOLLOW_15); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	      				
                    	    }
                    	    // InternalExport.g:3372:5: ( (lv_elements_4_0= ruleExpression ) )
                    	    // InternalExport.g:3373:6: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // InternalExport.g:3373:6: (lv_elements_4_0= ruleExpression )
                    	    // InternalExport.g:3374:7: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_62);
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
                    	    break loop59;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,17,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:3401:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // InternalExport.g:3401:66: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // InternalExport.g:3402:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
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
    // InternalExport.g:3408:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;



        	enterRule();

        try {
            // InternalExport.g:3414:2: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // InternalExport.g:3415:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // InternalExport.g:3415:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // InternalExport.g:3416:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,78,FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
              		
            }
            // InternalExport.g:3420:3: ( (lv_type_1_0= ruleSimpleType ) )
            // InternalExport.g:3421:4: (lv_type_1_0= ruleSimpleType )
            {
            // InternalExport.g:3421:4: (lv_type_1_0= ruleSimpleType )
            // InternalExport.g:3422:5: lv_type_1_0= ruleSimpleType
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
    // InternalExport.g:3443:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // InternalExport.g:3443:61: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // InternalExport.g:3444:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
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
    // InternalExport.g:3450:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;



        	enterRule();

        try {
            // InternalExport.g:3456:2: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // InternalExport.g:3457:2: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // InternalExport.g:3457:2: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // InternalExport.g:3458:3: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // InternalExport.g:3458:3: ( (lv_name_0_0= 'typeSelect' ) )
            // InternalExport.g:3459:4: (lv_name_0_0= 'typeSelect' )
            {
            // InternalExport.g:3459:4: (lv_name_0_0= 'typeSelect' )
            // InternalExport.g:3460:5: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,64,FOLLOW_23); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,28,FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalExport.g:3476:3: ( (lv_type_2_0= ruleType ) )
            // InternalExport.g:3477:4: (lv_type_2_0= ruleType )
            {
            // InternalExport.g:3477:4: (lv_type_2_0= ruleType )
            // InternalExport.g:3478:5: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_24);
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

            otherlv_3=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:3503:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // InternalExport.g:3503:61: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // InternalExport.g:3504:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
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
    // InternalExport.g:3510:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // InternalExport.g:3516:2: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // InternalExport.g:3517:2: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // InternalExport.g:3517:2: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // InternalExport.g:3518:3: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // InternalExport.g:3518:3: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // InternalExport.g:3519:4: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // InternalExport.g:3519:4: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // InternalExport.g:3520:5: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // InternalExport.g:3520:5: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
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
                    // InternalExport.g:3521:6: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,65,FOLLOW_23); if (state.failed) return current;
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
                    // InternalExport.g:3532:6: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,66,FOLLOW_23); if (state.failed) return current;
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
                    // InternalExport.g:3543:6: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,67,FOLLOW_23); if (state.failed) return current;
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
                    // InternalExport.g:3554:6: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,68,FOLLOW_23); if (state.failed) return current;
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
                    // InternalExport.g:3565:6: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,69,FOLLOW_23); if (state.failed) return current;
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
                    // InternalExport.g:3576:6: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,70,FOLLOW_23); if (state.failed) return current;
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
                    // InternalExport.g:3587:6: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,71,FOLLOW_23); if (state.failed) return current;
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
                    // InternalExport.g:3598:6: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,72,FOLLOW_23); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,28,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalExport.g:3615:3: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
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
                    // InternalExport.g:3616:4: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // InternalExport.g:3616:4: ( (lv_var_2_0= ruleIdentifier ) )
                    // InternalExport.g:3617:5: (lv_var_2_0= ruleIdentifier )
                    {
                    // InternalExport.g:3617:5: (lv_var_2_0= ruleIdentifier )
                    // InternalExport.g:3618:6: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_60);
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

                    otherlv_3=(Token)match(input,73,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                      			
                    }

                    }
                    break;

            }

            // InternalExport.g:3640:3: ( (lv_exp_4_0= ruleExpression ) )
            // InternalExport.g:3641:4: (lv_exp_4_0= ruleExpression )
            {
            // InternalExport.g:3641:4: (lv_exp_4_0= ruleExpression )
            // InternalExport.g:3642:5: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_24);
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

            otherlv_5=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:3667:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalExport.g:3667:45: (iv_ruleType= ruleType EOF )
            // InternalExport.g:3668:2: iv_ruleType= ruleType EOF
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
    // InternalExport.g:3674:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;



        	enterRule();

        try {
            // InternalExport.g:3680:2: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // InternalExport.g:3681:2: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // InternalExport.g:3681:2: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
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
                    // InternalExport.g:3682:3: this_CollectionType_0= ruleCollectionType
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
                    // InternalExport.g:3691:3: this_SimpleType_1= ruleSimpleType
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
    // InternalExport.g:3703:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // InternalExport.g:3703:55: (iv_ruleCollectionType= ruleCollectionType EOF )
            // InternalExport.g:3704:2: iv_ruleCollectionType= ruleCollectionType EOF
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
    // InternalExport.g:3710:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // InternalExport.g:3716:2: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // InternalExport.g:3717:2: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // InternalExport.g:3717:2: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // InternalExport.g:3718:3: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // InternalExport.g:3718:3: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // InternalExport.g:3719:4: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // InternalExport.g:3719:4: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // InternalExport.g:3720:5: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // InternalExport.g:3720:5: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
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
                    // InternalExport.g:3721:6: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,79,FOLLOW_63); if (state.failed) return current;
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
                    // InternalExport.g:3732:6: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,80,FOLLOW_63); if (state.failed) return current;
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
                    // InternalExport.g:3743:6: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,81,FOLLOW_63); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,20,FOLLOW_40); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
              		
            }
            // InternalExport.g:3760:3: ( (lv_id1_2_0= ruleSimpleType ) )
            // InternalExport.g:3761:4: (lv_id1_2_0= ruleSimpleType )
            {
            // InternalExport.g:3761:4: (lv_id1_2_0= ruleSimpleType )
            // InternalExport.g:3762:5: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_16);
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

            otherlv_3=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
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
    // InternalExport.g:3787:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // InternalExport.g:3787:51: (iv_ruleSimpleType= ruleSimpleType EOF )
            // InternalExport.g:3788:2: iv_ruleSimpleType= ruleSimpleType EOF
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
    // InternalExport.g:3794:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;



        	enterRule();

        try {
            // InternalExport.g:3800:2: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // InternalExport.g:3801:2: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // InternalExport.g:3801:2: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // InternalExport.g:3802:3: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // InternalExport.g:3802:3: ( (lv_id_0_0= ruleIdentifier ) )
            // InternalExport.g:3803:4: (lv_id_0_0= ruleIdentifier )
            {
            // InternalExport.g:3803:4: (lv_id_0_0= ruleIdentifier )
            // InternalExport.g:3804:5: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_38);
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

            // InternalExport.g:3821:3: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==39) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // InternalExport.g:3822:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,39,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	      			
            	    }
            	    // InternalExport.g:3826:4: ( (lv_id_2_0= ruleIdentifier ) )
            	    // InternalExport.g:3827:5: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // InternalExport.g:3827:5: (lv_id_2_0= ruleIdentifier )
            	    // InternalExport.g:3828:6: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_38);
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
    // InternalExport.g:3850:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalExport.g:3850:50: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalExport.g:3851:2: iv_ruleIdentifier= ruleIdentifier EOF
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
    // InternalExport.g:3857:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalExport.g:3863:2: (this_ID_0= RULE_ID )
            // InternalExport.g:3864:2: this_ID_0= RULE_ID
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

    // $ANTLR start synpred1_InternalExport
    public final void synpred1_InternalExport_fragment() throws RecognitionException {   
        // InternalExport.g:1186:4: ( ruleCastedExpression )
        // InternalExport.g:1186:5: ruleCastedExpression
        {
        pushFollow(FOLLOW_2);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalExport

    // $ANTLR start synpred2_InternalExport
    public final void synpred2_InternalExport_fragment() throws RecognitionException {   
        // InternalExport.g:1610:4: ( 'else' )
        // InternalExport.g:1610:5: 'else'
        {
        match(input,46,FOLLOW_2); if (state.failed) return ;

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
    static final String dfa_1s = "\36\uffff";
    static final String dfa_2s = "\1\4\1\uffff\1\0\33\uffff";
    static final String dfa_3s = "\1\121\1\uffff\1\0\33\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String dfa_5s = "\2\uffff\1\0\33\uffff}>";
    static final String[] dfa_6s = {
            "\4\3\10\uffff\1\3\13\uffff\1\2\13\uffff\1\1\3\uffff\1\3\2\uffff\1\3\13\uffff\1\3\2\uffff\1\3\1\uffff\11\3\1\uffff\10\3",
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

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA30 extends DFA {

        public DFA30(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 30;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "1175:2: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )";
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
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000004B000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x000000000000B000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x000000000000B002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000001500000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x48009100100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000000000E000010L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000001C00000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000002000010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000016000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000012000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000040000010L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000100010L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000190000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x48009100900100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000110000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000007900020000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000007800020000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000006000020000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000010L,0x0000000000038000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000010010000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x48000000100100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0003000000000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0010000000000002L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x07E0000000000002L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0800000002000002L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x48009100300100F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x0000000020800000L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000000000L,0x00000000000001FEL});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x48009100100300F0L,0x000000000003FDFFL});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000820000L});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000100000L});

}