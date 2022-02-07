package com.avaloq.tools.ddk.checkcfg.parser.antlr.internal;

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
import com.avaloq.tools.ddk.checkcfg.services.CheckCfgGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalCheckCfgParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_HEX", "RULE_INT", "RULE_DECIMAL", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'check'", "'configuration'", "'{'", "'}'", "'for'", "'catalog'", "'('", "','", "')'", "'='", "'#'", "'['", "']'", "'+='", "'-='", "'*='", "'/='", "'%='", "'<'", "'>'", "'>='", "'||'", "'&&'", "'=='", "'!='", "'==='", "'!=='", "'instanceof'", "'->'", "'..<'", "'..'", "'=>'", "'<>'", "'?:'", "'+'", "'-'", "'*'", "'**'", "'/'", "'%'", "'!'", "'as'", "'++'", "'--'", "'.'", "'::'", "'?.'", "'|'", "';'", "'if'", "'else'", "'switch'", "':'", "'default'", "'case'", "'while'", "'do'", "'var'", "'val'", "'extends'", "'static'", "'import'", "'extension'", "'super'", "'new'", "'false'", "'true'", "'null'", "'typeof'", "'throw'", "'return'", "'try'", "'finally'", "'synchronized'", "'catch'", "'?'", "'&'", "'error'", "'warning'", "'info'", "'ignore'"
    };
    public static final int RULE_HEX=5;
    public static final int T__50=50;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=8;
    public static final int RULE_INT=6;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
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
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_DECIMAL=7;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;

    // delegates
    // delegators


        public InternalCheckCfgParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalCheckCfgParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalCheckCfgParser.tokenNames; }
    public String getGrammarFileName() { return "InternalCheckCfg.g"; }



     	private CheckCfgGrammarAccess grammarAccess;

        public InternalCheckCfgParser(TokenStream input, CheckCfgGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "CheckConfiguration";
       	}

       	@Override
       	protected CheckCfgGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleCheckConfiguration"
    // InternalCheckCfg.g:65:1: entryRuleCheckConfiguration returns [EObject current=null] : iv_ruleCheckConfiguration= ruleCheckConfiguration EOF ;
    public final EObject entryRuleCheckConfiguration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCheckConfiguration = null;


        try {
            // InternalCheckCfg.g:65:59: (iv_ruleCheckConfiguration= ruleCheckConfiguration EOF )
            // InternalCheckCfg.g:66:2: iv_ruleCheckConfiguration= ruleCheckConfiguration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCheckConfigurationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleCheckConfiguration=ruleCheckConfiguration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCheckConfiguration; 
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
    // $ANTLR end "entryRuleCheckConfiguration"


    // $ANTLR start "ruleCheckConfiguration"
    // InternalCheckCfg.g:72:1: ruleCheckConfiguration returns [EObject current=null] : (otherlv_0= 'check' otherlv_1= 'configuration' ( (lv_name_2_0= ruleValidID ) ) ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator ) )* (otherlv_5= '{' ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )* otherlv_7= '}' )? ) ;
    public final EObject ruleCheckConfiguration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_parameterConfigurations_3_0 = null;

        EObject lv_languageValidatorConfigurations_4_0 = null;

        EObject lv_legacyCatalogConfigurations_6_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:78:2: ( (otherlv_0= 'check' otherlv_1= 'configuration' ( (lv_name_2_0= ruleValidID ) ) ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator ) )* (otherlv_5= '{' ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )* otherlv_7= '}' )? ) )
            // InternalCheckCfg.g:79:2: (otherlv_0= 'check' otherlv_1= 'configuration' ( (lv_name_2_0= ruleValidID ) ) ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator ) )* (otherlv_5= '{' ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )* otherlv_7= '}' )? )
            {
            // InternalCheckCfg.g:79:2: (otherlv_0= 'check' otherlv_1= 'configuration' ( (lv_name_2_0= ruleValidID ) ) ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator ) )* (otherlv_5= '{' ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )* otherlv_7= '}' )? )
            // InternalCheckCfg.g:80:3: otherlv_0= 'check' otherlv_1= 'configuration' ( (lv_name_2_0= ruleValidID ) ) ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator ) )* (otherlv_5= '{' ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )* otherlv_7= '}' )?
            {
            otherlv_0=(Token)match(input,13,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getCheckConfigurationAccess().getCheckKeyword_0());
              		
            }
            otherlv_1=(Token)match(input,14,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCheckConfigurationAccess().getConfigurationKeyword_1());
              		
            }
            // InternalCheckCfg.g:88:3: ( (lv_name_2_0= ruleValidID ) )
            // InternalCheckCfg.g:89:4: (lv_name_2_0= ruleValidID )
            {
            // InternalCheckCfg.g:89:4: (lv_name_2_0= ruleValidID )
            // InternalCheckCfg.g:90:5: lv_name_2_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCheckConfigurationAccess().getNameValidIDParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_5);
            lv_name_2_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getCheckConfigurationRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_2_0,
              						"org.eclipse.xtext.xbase.Xtype.ValidID");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:107:3: ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ID) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalCheckCfg.g:108:4: (lv_parameterConfigurations_3_0= ruleConfiguredParameter )
            	    {
            	    // InternalCheckCfg.g:108:4: (lv_parameterConfigurations_3_0= ruleConfiguredParameter )
            	    // InternalCheckCfg.g:109:5: lv_parameterConfigurations_3_0= ruleConfiguredParameter
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getCheckConfigurationAccess().getParameterConfigurationsConfiguredParameterParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_5);
            	    lv_parameterConfigurations_3_0=ruleConfiguredParameter();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getCheckConfigurationRule());
            	      					}
            	      					add(
            	      						current,
            	      						"parameterConfigurations",
            	      						lv_parameterConfigurations_3_0,
            	      						"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredParameter");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalCheckCfg.g:126:3: ( (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalCheckCfg.g:127:4: (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator )
            	    {
            	    // InternalCheckCfg.g:127:4: (lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator )
            	    // InternalCheckCfg.g:128:5: lv_languageValidatorConfigurations_4_0= ruleConfiguredLanguageValidator
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getCheckConfigurationAccess().getLanguageValidatorConfigurationsConfiguredLanguageValidatorParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_6);
            	    lv_languageValidatorConfigurations_4_0=ruleConfiguredLanguageValidator();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getCheckConfigurationRule());
            	      					}
            	      					add(
            	      						current,
            	      						"languageValidatorConfigurations",
            	      						lv_languageValidatorConfigurations_4_0,
            	      						"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredLanguageValidator");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalCheckCfg.g:145:3: (otherlv_5= '{' ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )* otherlv_7= '}' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalCheckCfg.g:146:4: otherlv_5= '{' ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )* otherlv_7= '}'
                    {
                    otherlv_5=(Token)match(input,15,FOLLOW_7); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getCheckConfigurationAccess().getLeftCurlyBracketKeyword_5_0());
                      			
                    }
                    // InternalCheckCfg.g:150:4: ( (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==18) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalCheckCfg.g:151:5: (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog )
                    	    {
                    	    // InternalCheckCfg.g:151:5: (lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog )
                    	    // InternalCheckCfg.g:152:6: lv_legacyCatalogConfigurations_6_0= ruleConfiguredCatalog
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      						newCompositeNode(grammarAccess.getCheckConfigurationAccess().getLegacyCatalogConfigurationsConfiguredCatalogParserRuleCall_5_1_0());
                    	      					
                    	    }
                    	    pushFollow(FOLLOW_7);
                    	    lv_legacyCatalogConfigurations_6_0=ruleConfiguredCatalog();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      						if (current==null) {
                    	      							current = createModelElementForParent(grammarAccess.getCheckConfigurationRule());
                    	      						}
                    	      						add(
                    	      							current,
                    	      							"legacyCatalogConfigurations",
                    	      							lv_legacyCatalogConfigurations_6_0,
                    	      							"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredCatalog");
                    	      						afterParserOrEnumRuleCall();
                    	      					
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getCheckConfigurationAccess().getRightCurlyBracketKeyword_5_2());
                      			
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
    // $ANTLR end "ruleCheckConfiguration"


    // $ANTLR start "entryRuleConfiguredLanguageValidator"
    // InternalCheckCfg.g:178:1: entryRuleConfiguredLanguageValidator returns [EObject current=null] : iv_ruleConfiguredLanguageValidator= ruleConfiguredLanguageValidator EOF ;
    public final EObject entryRuleConfiguredLanguageValidator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfiguredLanguageValidator = null;


        try {
            // InternalCheckCfg.g:178:68: (iv_ruleConfiguredLanguageValidator= ruleConfiguredLanguageValidator EOF )
            // InternalCheckCfg.g:179:2: iv_ruleConfiguredLanguageValidator= ruleConfiguredLanguageValidator EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConfiguredLanguageValidatorRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConfiguredLanguageValidator=ruleConfiguredLanguageValidator();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConfiguredLanguageValidator; 
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
    // $ANTLR end "entryRuleConfiguredLanguageValidator"


    // $ANTLR start "ruleConfiguredLanguageValidator"
    // InternalCheckCfg.g:185:1: ruleConfiguredLanguageValidator returns [EObject current=null] : (otherlv_0= 'for' ( (lv_language_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_catalogConfigurations_4_0= ruleConfiguredCatalog ) )* otherlv_5= '}' ) ;
    public final EObject ruleConfiguredLanguageValidator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_language_1_0 = null;

        EObject lv_parameterConfigurations_3_0 = null;

        EObject lv_catalogConfigurations_4_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:191:2: ( (otherlv_0= 'for' ( (lv_language_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_catalogConfigurations_4_0= ruleConfiguredCatalog ) )* otherlv_5= '}' ) )
            // InternalCheckCfg.g:192:2: (otherlv_0= 'for' ( (lv_language_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_catalogConfigurations_4_0= ruleConfiguredCatalog ) )* otherlv_5= '}' )
            {
            // InternalCheckCfg.g:192:2: (otherlv_0= 'for' ( (lv_language_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_catalogConfigurations_4_0= ruleConfiguredCatalog ) )* otherlv_5= '}' )
            // InternalCheckCfg.g:193:3: otherlv_0= 'for' ( (lv_language_1_0= ruleQualifiedName ) ) otherlv_2= '{' ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )* ( (lv_catalogConfigurations_4_0= ruleConfiguredCatalog ) )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getConfiguredLanguageValidatorAccess().getForKeyword_0());
              		
            }
            // InternalCheckCfg.g:197:3: ( (lv_language_1_0= ruleQualifiedName ) )
            // InternalCheckCfg.g:198:4: (lv_language_1_0= ruleQualifiedName )
            {
            // InternalCheckCfg.g:198:4: (lv_language_1_0= ruleQualifiedName )
            // InternalCheckCfg.g:199:5: lv_language_1_0= ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getConfiguredLanguageValidatorAccess().getLanguageQualifiedNameParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_8);
            lv_language_1_0=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getConfiguredLanguageValidatorRule());
              					}
              					set(
              						current,
              						"language",
              						lv_language_1_0,
              						"org.eclipse.xtext.xbase.Xbase.QualifiedName");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_9); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getConfiguredLanguageValidatorAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalCheckCfg.g:220:3: ( (lv_parameterConfigurations_3_0= ruleConfiguredParameter ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ID) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalCheckCfg.g:221:4: (lv_parameterConfigurations_3_0= ruleConfiguredParameter )
            	    {
            	    // InternalCheckCfg.g:221:4: (lv_parameterConfigurations_3_0= ruleConfiguredParameter )
            	    // InternalCheckCfg.g:222:5: lv_parameterConfigurations_3_0= ruleConfiguredParameter
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getConfiguredLanguageValidatorAccess().getParameterConfigurationsConfiguredParameterParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_9);
            	    lv_parameterConfigurations_3_0=ruleConfiguredParameter();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getConfiguredLanguageValidatorRule());
            	      					}
            	      					add(
            	      						current,
            	      						"parameterConfigurations",
            	      						lv_parameterConfigurations_3_0,
            	      						"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredParameter");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalCheckCfg.g:239:3: ( (lv_catalogConfigurations_4_0= ruleConfiguredCatalog ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==18) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalCheckCfg.g:240:4: (lv_catalogConfigurations_4_0= ruleConfiguredCatalog )
            	    {
            	    // InternalCheckCfg.g:240:4: (lv_catalogConfigurations_4_0= ruleConfiguredCatalog )
            	    // InternalCheckCfg.g:241:5: lv_catalogConfigurations_4_0= ruleConfiguredCatalog
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getConfiguredLanguageValidatorAccess().getCatalogConfigurationsConfiguredCatalogParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_7);
            	    lv_catalogConfigurations_4_0=ruleConfiguredCatalog();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getConfiguredLanguageValidatorRule());
            	      					}
            	      					add(
            	      						current,
            	      						"catalogConfigurations",
            	      						lv_catalogConfigurations_4_0,
            	      						"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredCatalog");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            otherlv_5=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getConfiguredLanguageValidatorAccess().getRightCurlyBracketKeyword_5());
              		
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
    // $ANTLR end "ruleConfiguredLanguageValidator"


    // $ANTLR start "entryRuleConfiguredCatalog"
    // InternalCheckCfg.g:266:1: entryRuleConfiguredCatalog returns [EObject current=null] : iv_ruleConfiguredCatalog= ruleConfiguredCatalog EOF ;
    public final EObject entryRuleConfiguredCatalog() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfiguredCatalog = null;


        try {
            // InternalCheckCfg.g:266:58: (iv_ruleConfiguredCatalog= ruleConfiguredCatalog EOF )
            // InternalCheckCfg.g:267:2: iv_ruleConfiguredCatalog= ruleConfiguredCatalog EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConfiguredCatalogRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConfiguredCatalog=ruleConfiguredCatalog();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConfiguredCatalog; 
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
    // $ANTLR end "entryRuleConfiguredCatalog"


    // $ANTLR start "ruleConfiguredCatalog"
    // InternalCheckCfg.g:273:1: ruleConfiguredCatalog returns [EObject current=null] : ( () otherlv_1= 'catalog' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) )* ( (lv_checkConfigurations_5_0= ruleConfiguredCheck ) )* otherlv_6= '}' ) ;
    public final EObject ruleConfiguredCatalog() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        EObject lv_parameterConfigurations_4_0 = null;

        EObject lv_checkConfigurations_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:279:2: ( ( () otherlv_1= 'catalog' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) )* ( (lv_checkConfigurations_5_0= ruleConfiguredCheck ) )* otherlv_6= '}' ) )
            // InternalCheckCfg.g:280:2: ( () otherlv_1= 'catalog' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) )* ( (lv_checkConfigurations_5_0= ruleConfiguredCheck ) )* otherlv_6= '}' )
            {
            // InternalCheckCfg.g:280:2: ( () otherlv_1= 'catalog' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) )* ( (lv_checkConfigurations_5_0= ruleConfiguredCheck ) )* otherlv_6= '}' )
            // InternalCheckCfg.g:281:3: () otherlv_1= 'catalog' ( ( ruleQualifiedName ) ) otherlv_3= '{' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) )* ( (lv_checkConfigurations_5_0= ruleConfiguredCheck ) )* otherlv_6= '}'
            {
            // InternalCheckCfg.g:281:3: ()
            // InternalCheckCfg.g:282:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getConfiguredCatalogAccess().getConfiguredCatalogAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,18,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getConfiguredCatalogAccess().getCatalogKeyword_1());
              		
            }
            // InternalCheckCfg.g:292:3: ( ( ruleQualifiedName ) )
            // InternalCheckCfg.g:293:4: ( ruleQualifiedName )
            {
            // InternalCheckCfg.g:293:4: ( ruleQualifiedName )
            // InternalCheckCfg.g:294:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getConfiguredCatalogRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getConfiguredCatalogAccess().getCatalogCheckCatalogCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_8);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_10); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getConfiguredCatalogAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalCheckCfg.g:312:3: ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_ID) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalCheckCfg.g:313:4: (lv_parameterConfigurations_4_0= ruleConfiguredParameter )
            	    {
            	    // InternalCheckCfg.g:313:4: (lv_parameterConfigurations_4_0= ruleConfiguredParameter )
            	    // InternalCheckCfg.g:314:5: lv_parameterConfigurations_4_0= ruleConfiguredParameter
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getConfiguredCatalogAccess().getParameterConfigurationsConfiguredParameterParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_10);
            	    lv_parameterConfigurations_4_0=ruleConfiguredParameter();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getConfiguredCatalogRule());
            	      					}
            	      					add(
            	      						current,
            	      						"parameterConfigurations",
            	      						lv_parameterConfigurations_4_0,
            	      						"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredParameter");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalCheckCfg.g:331:3: ( (lv_checkConfigurations_5_0= ruleConfiguredCheck ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==66||(LA8_0>=90 && LA8_0<=93)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalCheckCfg.g:332:4: (lv_checkConfigurations_5_0= ruleConfiguredCheck )
            	    {
            	    // InternalCheckCfg.g:332:4: (lv_checkConfigurations_5_0= ruleConfiguredCheck )
            	    // InternalCheckCfg.g:333:5: lv_checkConfigurations_5_0= ruleConfiguredCheck
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getConfiguredCatalogAccess().getCheckConfigurationsConfiguredCheckParserRuleCall_5_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_11);
            	    lv_checkConfigurations_5_0=ruleConfiguredCheck();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getConfiguredCatalogRule());
            	      					}
            	      					add(
            	      						current,
            	      						"checkConfigurations",
            	      						lv_checkConfigurations_5_0,
            	      						"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredCheck");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            otherlv_6=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getConfiguredCatalogAccess().getRightCurlyBracketKeyword_6());
              		
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
    // $ANTLR end "ruleConfiguredCatalog"


    // $ANTLR start "entryRuleConfiguredCheck"
    // InternalCheckCfg.g:358:1: entryRuleConfiguredCheck returns [EObject current=null] : iv_ruleConfiguredCheck= ruleConfiguredCheck EOF ;
    public final EObject entryRuleConfiguredCheck() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfiguredCheck = null;


        try {
            // InternalCheckCfg.g:358:56: (iv_ruleConfiguredCheck= ruleConfiguredCheck EOF )
            // InternalCheckCfg.g:359:2: iv_ruleConfiguredCheck= ruleConfiguredCheck EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConfiguredCheckRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConfiguredCheck=ruleConfiguredCheck();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConfiguredCheck; 
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
    // $ANTLR end "entryRuleConfiguredCheck"


    // $ANTLR start "ruleConfiguredCheck"
    // InternalCheckCfg.g:365:1: ruleConfiguredCheck returns [EObject current=null] : ( () ( (lv_severity_1_0= ruleSeverityKind ) ) ( ( ruleQualifiedName ) ) (otherlv_3= '(' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) ) (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )* otherlv_7= ')' )? ) ;
    public final EObject ruleConfiguredCheck() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Enumerator lv_severity_1_0 = null;

        EObject lv_parameterConfigurations_4_0 = null;

        EObject lv_parameterConfigurations_6_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:371:2: ( ( () ( (lv_severity_1_0= ruleSeverityKind ) ) ( ( ruleQualifiedName ) ) (otherlv_3= '(' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) ) (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )* otherlv_7= ')' )? ) )
            // InternalCheckCfg.g:372:2: ( () ( (lv_severity_1_0= ruleSeverityKind ) ) ( ( ruleQualifiedName ) ) (otherlv_3= '(' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) ) (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )* otherlv_7= ')' )? )
            {
            // InternalCheckCfg.g:372:2: ( () ( (lv_severity_1_0= ruleSeverityKind ) ) ( ( ruleQualifiedName ) ) (otherlv_3= '(' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) ) (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )* otherlv_7= ')' )? )
            // InternalCheckCfg.g:373:3: () ( (lv_severity_1_0= ruleSeverityKind ) ) ( ( ruleQualifiedName ) ) (otherlv_3= '(' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) ) (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )* otherlv_7= ')' )?
            {
            // InternalCheckCfg.g:373:3: ()
            // InternalCheckCfg.g:374:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getConfiguredCheckAccess().getConfiguredCheckAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:380:3: ( (lv_severity_1_0= ruleSeverityKind ) )
            // InternalCheckCfg.g:381:4: (lv_severity_1_0= ruleSeverityKind )
            {
            // InternalCheckCfg.g:381:4: (lv_severity_1_0= ruleSeverityKind )
            // InternalCheckCfg.g:382:5: lv_severity_1_0= ruleSeverityKind
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getConfiguredCheckAccess().getSeveritySeverityKindEnumRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_4);
            lv_severity_1_0=ruleSeverityKind();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getConfiguredCheckRule());
              					}
              					set(
              						current,
              						"severity",
              						lv_severity_1_0,
              						"com.avaloq.tools.ddk.checkcfg.CheckCfg.SeverityKind");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:399:3: ( ( ruleQualifiedName ) )
            // InternalCheckCfg.g:400:4: ( ruleQualifiedName )
            {
            // InternalCheckCfg.g:400:4: ( ruleQualifiedName )
            // InternalCheckCfg.g:401:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getConfiguredCheckRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getConfiguredCheckAccess().getCheckCheckCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_12);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:415:3: (otherlv_3= '(' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) ) (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )* otherlv_7= ')' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==19) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalCheckCfg.g:416:4: otherlv_3= '(' ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) ) (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )* otherlv_7= ')'
                    {
                    otherlv_3=(Token)match(input,19,FOLLOW_4); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getConfiguredCheckAccess().getLeftParenthesisKeyword_3_0());
                      			
                    }
                    // InternalCheckCfg.g:420:4: ( (lv_parameterConfigurations_4_0= ruleConfiguredParameter ) )
                    // InternalCheckCfg.g:421:5: (lv_parameterConfigurations_4_0= ruleConfiguredParameter )
                    {
                    // InternalCheckCfg.g:421:5: (lv_parameterConfigurations_4_0= ruleConfiguredParameter )
                    // InternalCheckCfg.g:422:6: lv_parameterConfigurations_4_0= ruleConfiguredParameter
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getConfiguredCheckAccess().getParameterConfigurationsConfiguredParameterParserRuleCall_3_1_0());
                      					
                    }
                    pushFollow(FOLLOW_13);
                    lv_parameterConfigurations_4_0=ruleConfiguredParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getConfiguredCheckRule());
                      						}
                      						add(
                      							current,
                      							"parameterConfigurations",
                      							lv_parameterConfigurations_4_0,
                      							"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredParameter");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:439:4: (otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) ) )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0==20) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalCheckCfg.g:440:5: otherlv_5= ',' ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) )
                    	    {
                    	    otherlv_5=(Token)match(input,20,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_5, grammarAccess.getConfiguredCheckAccess().getCommaKeyword_3_2_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:444:5: ( (lv_parameterConfigurations_6_0= ruleConfiguredParameter ) )
                    	    // InternalCheckCfg.g:445:6: (lv_parameterConfigurations_6_0= ruleConfiguredParameter )
                    	    {
                    	    // InternalCheckCfg.g:445:6: (lv_parameterConfigurations_6_0= ruleConfiguredParameter )
                    	    // InternalCheckCfg.g:446:7: lv_parameterConfigurations_6_0= ruleConfiguredParameter
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getConfiguredCheckAccess().getParameterConfigurationsConfiguredParameterParserRuleCall_3_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_13);
                    	    lv_parameterConfigurations_6_0=ruleConfiguredParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getConfiguredCheckRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"parameterConfigurations",
                    	      								lv_parameterConfigurations_6_0,
                    	      								"com.avaloq.tools.ddk.checkcfg.CheckCfg.ConfiguredParameter");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getConfiguredCheckAccess().getRightParenthesisKeyword_3_3());
                      			
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
    // $ANTLR end "ruleConfiguredCheck"


    // $ANTLR start "entryRuleConfiguredParameter"
    // InternalCheckCfg.g:473:1: entryRuleConfiguredParameter returns [EObject current=null] : iv_ruleConfiguredParameter= ruleConfiguredParameter EOF ;
    public final EObject entryRuleConfiguredParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConfiguredParameter = null;


        try {
            // InternalCheckCfg.g:473:60: (iv_ruleConfiguredParameter= ruleConfiguredParameter EOF )
            // InternalCheckCfg.g:474:2: iv_ruleConfiguredParameter= ruleConfiguredParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConfiguredParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleConfiguredParameter=ruleConfiguredParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConfiguredParameter; 
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
    // $ANTLR end "entryRuleConfiguredParameter"


    // $ANTLR start "ruleConfiguredParameter"
    // InternalCheckCfg.g:480:1: ruleConfiguredParameter returns [EObject current=null] : ( () ( ( ruleValidID ) ) otherlv_2= '=' ( (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral ) ) ) ;
    public final EObject ruleConfiguredParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_newValue_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:486:2: ( ( () ( ( ruleValidID ) ) otherlv_2= '=' ( (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral ) ) ) )
            // InternalCheckCfg.g:487:2: ( () ( ( ruleValidID ) ) otherlv_2= '=' ( (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral ) ) )
            {
            // InternalCheckCfg.g:487:2: ( () ( ( ruleValidID ) ) otherlv_2= '=' ( (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral ) ) )
            // InternalCheckCfg.g:488:3: () ( ( ruleValidID ) ) otherlv_2= '=' ( (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral ) )
            {
            // InternalCheckCfg.g:488:3: ()
            // InternalCheckCfg.g:489:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getConfiguredParameterAccess().getConfiguredParameterAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:495:3: ( ( ruleValidID ) )
            // InternalCheckCfg.g:496:4: ( ruleValidID )
            {
            // InternalCheckCfg.g:496:4: ( ruleValidID )
            // InternalCheckCfg.g:497:5: ruleValidID
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getConfiguredParameterRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getConfiguredParameterAccess().getParameterFormalParameterCrossReference_1_0());
              				
            }
            pushFollow(FOLLOW_14);
            ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_2=(Token)match(input,22,FOLLOW_15); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getConfiguredParameterAccess().getEqualsSignKeyword_2());
              		
            }
            // InternalCheckCfg.g:515:3: ( (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral ) )
            // InternalCheckCfg.g:516:4: (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral )
            {
            // InternalCheckCfg.g:516:4: (lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral )
            // InternalCheckCfg.g:517:5: lv_newValue_3_0= ruleXFormalParameterDefaultValueLiteral
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getConfiguredParameterAccess().getNewValueXFormalParameterDefaultValueLiteralParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_newValue_3_0=ruleXFormalParameterDefaultValueLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getConfiguredParameterRule());
              					}
              					set(
              						current,
              						"newValue",
              						lv_newValue_3_0,
              						"com.avaloq.tools.ddk.checkcfg.CheckCfg.XFormalParameterDefaultValueLiteral");
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
    // $ANTLR end "ruleConfiguredParameter"


    // $ANTLR start "entryRuleXSimpleFormalParameterDefaultValueLiteral"
    // InternalCheckCfg.g:538:1: entryRuleXSimpleFormalParameterDefaultValueLiteral returns [EObject current=null] : iv_ruleXSimpleFormalParameterDefaultValueLiteral= ruleXSimpleFormalParameterDefaultValueLiteral EOF ;
    public final EObject entryRuleXSimpleFormalParameterDefaultValueLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSimpleFormalParameterDefaultValueLiteral = null;


        try {
            // InternalCheckCfg.g:538:82: (iv_ruleXSimpleFormalParameterDefaultValueLiteral= ruleXSimpleFormalParameterDefaultValueLiteral EOF )
            // InternalCheckCfg.g:539:2: iv_ruleXSimpleFormalParameterDefaultValueLiteral= ruleXSimpleFormalParameterDefaultValueLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXSimpleFormalParameterDefaultValueLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXSimpleFormalParameterDefaultValueLiteral=ruleXSimpleFormalParameterDefaultValueLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXSimpleFormalParameterDefaultValueLiteral; 
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
    // $ANTLR end "entryRuleXSimpleFormalParameterDefaultValueLiteral"


    // $ANTLR start "ruleXSimpleFormalParameterDefaultValueLiteral"
    // InternalCheckCfg.g:545:1: ruleXSimpleFormalParameterDefaultValueLiteral returns [EObject current=null] : (this_XBooleanLiteral_0= ruleXBooleanLiteral | this_XNumberLiteral_1= ruleXNumberLiteral | this_XStringLiteral_2= ruleXStringLiteral ) ;
    public final EObject ruleXSimpleFormalParameterDefaultValueLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_XBooleanLiteral_0 = null;

        EObject this_XNumberLiteral_1 = null;

        EObject this_XStringLiteral_2 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:551:2: ( (this_XBooleanLiteral_0= ruleXBooleanLiteral | this_XNumberLiteral_1= ruleXNumberLiteral | this_XStringLiteral_2= ruleXStringLiteral ) )
            // InternalCheckCfg.g:552:2: (this_XBooleanLiteral_0= ruleXBooleanLiteral | this_XNumberLiteral_1= ruleXNumberLiteral | this_XStringLiteral_2= ruleXStringLiteral )
            {
            // InternalCheckCfg.g:552:2: (this_XBooleanLiteral_0= ruleXBooleanLiteral | this_XNumberLiteral_1= ruleXNumberLiteral | this_XStringLiteral_2= ruleXStringLiteral )
            int alt11=3;
            switch ( input.LA(1) ) {
            case 78:
            case 79:
                {
                alt11=1;
                }
                break;
            case RULE_HEX:
            case RULE_INT:
            case RULE_DECIMAL:
                {
                alt11=2;
                }
                break;
            case RULE_STRING:
                {
                alt11=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // InternalCheckCfg.g:553:3: this_XBooleanLiteral_0= ruleXBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXSimpleFormalParameterDefaultValueLiteralAccess().getXBooleanLiteralParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XBooleanLiteral_0=ruleXBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XBooleanLiteral_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:562:3: this_XNumberLiteral_1= ruleXNumberLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXSimpleFormalParameterDefaultValueLiteralAccess().getXNumberLiteralParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XNumberLiteral_1=ruleXNumberLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XNumberLiteral_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:571:3: this_XStringLiteral_2= ruleXStringLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXSimpleFormalParameterDefaultValueLiteralAccess().getXStringLiteralParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XStringLiteral_2=ruleXStringLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XStringLiteral_2;
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
    // $ANTLR end "ruleXSimpleFormalParameterDefaultValueLiteral"


    // $ANTLR start "entryRuleXConstantUnaryOperation"
    // InternalCheckCfg.g:583:1: entryRuleXConstantUnaryOperation returns [EObject current=null] : iv_ruleXConstantUnaryOperation= ruleXConstantUnaryOperation EOF ;
    public final EObject entryRuleXConstantUnaryOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXConstantUnaryOperation = null;


        try {
            // InternalCheckCfg.g:583:64: (iv_ruleXConstantUnaryOperation= ruleXConstantUnaryOperation EOF )
            // InternalCheckCfg.g:584:2: iv_ruleXConstantUnaryOperation= ruleXConstantUnaryOperation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXConstantUnaryOperationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXConstantUnaryOperation=ruleXConstantUnaryOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXConstantUnaryOperation; 
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
    // $ANTLR end "entryRuleXConstantUnaryOperation"


    // $ANTLR start "ruleXConstantUnaryOperation"
    // InternalCheckCfg.g:590:1: ruleXConstantUnaryOperation returns [EObject current=null] : ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXConstantUnaryOperation ) ) ) | this_XSimpleFormalParameterDefaultValueLiteral_3= ruleXSimpleFormalParameterDefaultValueLiteral ) ;
    public final EObject ruleXConstantUnaryOperation() throws RecognitionException {
        EObject current = null;

        EObject lv_operand_2_0 = null;

        EObject this_XSimpleFormalParameterDefaultValueLiteral_3 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:596:2: ( ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXConstantUnaryOperation ) ) ) | this_XSimpleFormalParameterDefaultValueLiteral_3= ruleXSimpleFormalParameterDefaultValueLiteral ) )
            // InternalCheckCfg.g:597:2: ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXConstantUnaryOperation ) ) ) | this_XSimpleFormalParameterDefaultValueLiteral_3= ruleXSimpleFormalParameterDefaultValueLiteral )
            {
            // InternalCheckCfg.g:597:2: ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXConstantUnaryOperation ) ) ) | this_XSimpleFormalParameterDefaultValueLiteral_3= ruleXSimpleFormalParameterDefaultValueLiteral )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=47 && LA12_0<=48)||LA12_0==53) ) {
                alt12=1;
            }
            else if ( ((LA12_0>=RULE_STRING && LA12_0<=RULE_DECIMAL)||(LA12_0>=78 && LA12_0<=79)) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalCheckCfg.g:598:3: ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXConstantUnaryOperation ) ) )
                    {
                    // InternalCheckCfg.g:598:3: ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXConstantUnaryOperation ) ) )
                    // InternalCheckCfg.g:599:4: () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXConstantUnaryOperation ) )
                    {
                    // InternalCheckCfg.g:599:4: ()
                    // InternalCheckCfg.g:600:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getXConstantUnaryOperationAccess().getXUnaryOperationAction_0_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCheckCfg.g:606:4: ( ( ruleOpUnary ) )
                    // InternalCheckCfg.g:607:5: ( ruleOpUnary )
                    {
                    // InternalCheckCfg.g:607:5: ( ruleOpUnary )
                    // InternalCheckCfg.g:608:6: ruleOpUnary
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXConstantUnaryOperationRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXConstantUnaryOperationAccess().getFeatureJvmIdentifiableElementCrossReference_0_1_0());
                      					
                    }
                    pushFollow(FOLLOW_16);
                    ruleOpUnary();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:622:4: ( (lv_operand_2_0= ruleXConstantUnaryOperation ) )
                    // InternalCheckCfg.g:623:5: (lv_operand_2_0= ruleXConstantUnaryOperation )
                    {
                    // InternalCheckCfg.g:623:5: (lv_operand_2_0= ruleXConstantUnaryOperation )
                    // InternalCheckCfg.g:624:6: lv_operand_2_0= ruleXConstantUnaryOperation
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXConstantUnaryOperationAccess().getOperandXConstantUnaryOperationParserRuleCall_0_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_operand_2_0=ruleXConstantUnaryOperation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXConstantUnaryOperationRule());
                      						}
                      						set(
                      							current,
                      							"operand",
                      							lv_operand_2_0,
                      							"com.avaloq.tools.ddk.checkcfg.CheckCfg.XConstantUnaryOperation");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:643:3: this_XSimpleFormalParameterDefaultValueLiteral_3= ruleXSimpleFormalParameterDefaultValueLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXConstantUnaryOperationAccess().getXSimpleFormalParameterDefaultValueLiteralParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XSimpleFormalParameterDefaultValueLiteral_3=ruleXSimpleFormalParameterDefaultValueLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XSimpleFormalParameterDefaultValueLiteral_3;
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
    // $ANTLR end "ruleXConstantUnaryOperation"


    // $ANTLR start "entryRuleXFormalParameterDefaultValueLiteral"
    // InternalCheckCfg.g:655:1: entryRuleXFormalParameterDefaultValueLiteral returns [EObject current=null] : iv_ruleXFormalParameterDefaultValueLiteral= ruleXFormalParameterDefaultValueLiteral EOF ;
    public final EObject entryRuleXFormalParameterDefaultValueLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFormalParameterDefaultValueLiteral = null;


        try {
            // InternalCheckCfg.g:655:76: (iv_ruleXFormalParameterDefaultValueLiteral= ruleXFormalParameterDefaultValueLiteral EOF )
            // InternalCheckCfg.g:656:2: iv_ruleXFormalParameterDefaultValueLiteral= ruleXFormalParameterDefaultValueLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXFormalParameterDefaultValueLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXFormalParameterDefaultValueLiteral=ruleXFormalParameterDefaultValueLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXFormalParameterDefaultValueLiteral; 
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
    // $ANTLR end "entryRuleXFormalParameterDefaultValueLiteral"


    // $ANTLR start "ruleXFormalParameterDefaultValueLiteral"
    // InternalCheckCfg.g:662:1: ruleXFormalParameterDefaultValueLiteral returns [EObject current=null] : (this_XConstantUnaryOperation_0= ruleXConstantUnaryOperation | this_XConstantListLiteral_1= ruleXConstantListLiteral ) ;
    public final EObject ruleXFormalParameterDefaultValueLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_XConstantUnaryOperation_0 = null;

        EObject this_XConstantListLiteral_1 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:668:2: ( (this_XConstantUnaryOperation_0= ruleXConstantUnaryOperation | this_XConstantListLiteral_1= ruleXConstantListLiteral ) )
            // InternalCheckCfg.g:669:2: (this_XConstantUnaryOperation_0= ruleXConstantUnaryOperation | this_XConstantListLiteral_1= ruleXConstantListLiteral )
            {
            // InternalCheckCfg.g:669:2: (this_XConstantUnaryOperation_0= ruleXConstantUnaryOperation | this_XConstantListLiteral_1= ruleXConstantListLiteral )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=RULE_STRING && LA13_0<=RULE_DECIMAL)||(LA13_0>=47 && LA13_0<=48)||LA13_0==53||(LA13_0>=78 && LA13_0<=79)) ) {
                alt13=1;
            }
            else if ( (LA13_0==23) ) {
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
                    // InternalCheckCfg.g:670:3: this_XConstantUnaryOperation_0= ruleXConstantUnaryOperation
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXFormalParameterDefaultValueLiteralAccess().getXConstantUnaryOperationParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XConstantUnaryOperation_0=ruleXConstantUnaryOperation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XConstantUnaryOperation_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:679:3: this_XConstantListLiteral_1= ruleXConstantListLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXFormalParameterDefaultValueLiteralAccess().getXConstantListLiteralParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XConstantListLiteral_1=ruleXConstantListLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XConstantListLiteral_1;
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
    // $ANTLR end "ruleXFormalParameterDefaultValueLiteral"


    // $ANTLR start "entryRuleXConstantListLiteral"
    // InternalCheckCfg.g:691:1: entryRuleXConstantListLiteral returns [EObject current=null] : iv_ruleXConstantListLiteral= ruleXConstantListLiteral EOF ;
    public final EObject entryRuleXConstantListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXConstantListLiteral = null;


        try {
            // InternalCheckCfg.g:691:61: (iv_ruleXConstantListLiteral= ruleXConstantListLiteral EOF )
            // InternalCheckCfg.g:692:2: iv_ruleXConstantListLiteral= ruleXConstantListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXConstantListLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXConstantListLiteral=ruleXConstantListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXConstantListLiteral; 
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
    // $ANTLR end "entryRuleXConstantListLiteral"


    // $ANTLR start "ruleXConstantListLiteral"
    // InternalCheckCfg.g:698:1: ruleXConstantListLiteral returns [EObject current=null] : ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXConstantUnaryOperation ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )* )? otherlv_6= ']' ) ;
    public final EObject ruleXConstantListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_elements_3_0 = null;

        EObject lv_elements_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:704:2: ( ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXConstantUnaryOperation ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )* )? otherlv_6= ']' ) )
            // InternalCheckCfg.g:705:2: ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXConstantUnaryOperation ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )* )? otherlv_6= ']' )
            {
            // InternalCheckCfg.g:705:2: ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXConstantUnaryOperation ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )* )? otherlv_6= ']' )
            // InternalCheckCfg.g:706:3: () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXConstantUnaryOperation ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )* )? otherlv_6= ']'
            {
            // InternalCheckCfg.g:706:3: ()
            // InternalCheckCfg.g:707:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXConstantListLiteralAccess().getXListLiteralAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,23,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXConstantListLiteralAccess().getNumberSignKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,24,FOLLOW_18); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXConstantListLiteralAccess().getLeftSquareBracketKeyword_2());
              		
            }
            // InternalCheckCfg.g:721:3: ( ( (lv_elements_3_0= ruleXConstantUnaryOperation ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=RULE_STRING && LA15_0<=RULE_DECIMAL)||(LA15_0>=47 && LA15_0<=48)||LA15_0==53||(LA15_0>=78 && LA15_0<=79)) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalCheckCfg.g:722:4: ( (lv_elements_3_0= ruleXConstantUnaryOperation ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )*
                    {
                    // InternalCheckCfg.g:722:4: ( (lv_elements_3_0= ruleXConstantUnaryOperation ) )
                    // InternalCheckCfg.g:723:5: (lv_elements_3_0= ruleXConstantUnaryOperation )
                    {
                    // InternalCheckCfg.g:723:5: (lv_elements_3_0= ruleXConstantUnaryOperation )
                    // InternalCheckCfg.g:724:6: lv_elements_3_0= ruleXConstantUnaryOperation
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXConstantListLiteralAccess().getElementsXConstantUnaryOperationParserRuleCall_3_0_0());
                      					
                    }
                    pushFollow(FOLLOW_19);
                    lv_elements_3_0=ruleXConstantUnaryOperation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXConstantListLiteralRule());
                      						}
                      						add(
                      							current,
                      							"elements",
                      							lv_elements_3_0,
                      							"com.avaloq.tools.ddk.checkcfg.CheckCfg.XConstantUnaryOperation");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:741:4: (otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==20) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalCheckCfg.g:742:5: otherlv_4= ',' ( (lv_elements_5_0= ruleXConstantUnaryOperation ) )
                    	    {
                    	    otherlv_4=(Token)match(input,20,FOLLOW_16); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_4, grammarAccess.getXConstantListLiteralAccess().getCommaKeyword_3_1_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:746:5: ( (lv_elements_5_0= ruleXConstantUnaryOperation ) )
                    	    // InternalCheckCfg.g:747:6: (lv_elements_5_0= ruleXConstantUnaryOperation )
                    	    {
                    	    // InternalCheckCfg.g:747:6: (lv_elements_5_0= ruleXConstantUnaryOperation )
                    	    // InternalCheckCfg.g:748:7: lv_elements_5_0= ruleXConstantUnaryOperation
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXConstantListLiteralAccess().getElementsXConstantUnaryOperationParserRuleCall_3_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_19);
                    	    lv_elements_5_0=ruleXConstantUnaryOperation();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXConstantListLiteralRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"elements",
                    	      								lv_elements_5_0,
                    	      								"com.avaloq.tools.ddk.checkcfg.CheckCfg.XConstantUnaryOperation");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getXConstantListLiteralAccess().getRightSquareBracketKeyword_4());
              		
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
    // $ANTLR end "ruleXConstantListLiteral"


    // $ANTLR start "entryRuleXExpression"
    // InternalCheckCfg.g:775:1: entryRuleXExpression returns [EObject current=null] : iv_ruleXExpression= ruleXExpression EOF ;
    public final EObject entryRuleXExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpression = null;


        try {
            // InternalCheckCfg.g:775:52: (iv_ruleXExpression= ruleXExpression EOF )
            // InternalCheckCfg.g:776:2: iv_ruleXExpression= ruleXExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXExpression=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpression; 
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
    // $ANTLR end "entryRuleXExpression"


    // $ANTLR start "ruleXExpression"
    // InternalCheckCfg.g:782:1: ruleXExpression returns [EObject current=null] : this_XAssignment_0= ruleXAssignment ;
    public final EObject ruleXExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XAssignment_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:788:2: (this_XAssignment_0= ruleXAssignment )
            // InternalCheckCfg.g:789:2: this_XAssignment_0= ruleXAssignment
            {
            if ( state.backtracking==0 ) {

              		newCompositeNode(grammarAccess.getXExpressionAccess().getXAssignmentParserRuleCall());
              	
            }
            pushFollow(FOLLOW_2);
            this_XAssignment_0=ruleXAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current = this_XAssignment_0;
              		afterParserOrEnumRuleCall();
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpression"


    // $ANTLR start "entryRuleXAssignment"
    // InternalCheckCfg.g:800:1: entryRuleXAssignment returns [EObject current=null] : iv_ruleXAssignment= ruleXAssignment EOF ;
    public final EObject entryRuleXAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAssignment = null;


        try {
            // InternalCheckCfg.g:800:52: (iv_ruleXAssignment= ruleXAssignment EOF )
            // InternalCheckCfg.g:801:2: iv_ruleXAssignment= ruleXAssignment EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXAssignmentRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXAssignment=ruleXAssignment();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXAssignment; 
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
    // $ANTLR end "entryRuleXAssignment"


    // $ANTLR start "ruleXAssignment"
    // InternalCheckCfg.g:807:1: ruleXAssignment returns [EObject current=null] : ( ( () ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) ) ;
    public final EObject ruleXAssignment() throws RecognitionException {
        EObject current = null;

        EObject lv_value_3_0 = null;

        EObject this_XOrExpression_4 = null;

        EObject lv_rightOperand_7_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:813:2: ( ( ( () ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) ) )
            // InternalCheckCfg.g:814:2: ( ( () ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) )
            {
            // InternalCheckCfg.g:814:2: ( ( () ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) ) | (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? ) )
            int alt17=2;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA17_1 = input.LA(2);

                if ( (LA17_1==22) ) {
                    alt17=1;
                }
                else if ( (LA17_1==EOF||(LA17_1>=RULE_STRING && LA17_1<=RULE_ID)||(LA17_1>=15 && LA17_1<=17)||(LA17_1>=19 && LA17_1<=21)||(LA17_1>=23 && LA17_1<=59)||(LA17_1>=61 && LA17_1<=87)) ) {
                    alt17=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 1, input);

                    throw nvae;
                }
                }
                break;
            case 72:
                {
                int LA17_2 = input.LA(2);

                if ( (LA17_2==22) ) {
                    alt17=1;
                }
                else if ( (LA17_2==EOF||(LA17_2>=RULE_STRING && LA17_2<=RULE_ID)||(LA17_2>=15 && LA17_2<=17)||(LA17_2>=19 && LA17_2<=21)||(LA17_2>=23 && LA17_2<=59)||(LA17_2>=61 && LA17_2<=87)) ) {
                    alt17=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 2, input);

                    throw nvae;
                }
                }
                break;
            case 73:
                {
                int LA17_3 = input.LA(2);

                if ( (LA17_3==22) ) {
                    alt17=1;
                }
                else if ( (LA17_3==EOF||(LA17_3>=RULE_STRING && LA17_3<=RULE_ID)||(LA17_3>=15 && LA17_3<=17)||(LA17_3>=19 && LA17_3<=21)||(LA17_3>=23 && LA17_3<=59)||(LA17_3>=61 && LA17_3<=87)) ) {
                    alt17=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 3, input);

                    throw nvae;
                }
                }
                break;
            case 74:
                {
                int LA17_4 = input.LA(2);

                if ( (LA17_4==22) ) {
                    alt17=1;
                }
                else if ( (LA17_4==EOF||(LA17_4>=RULE_STRING && LA17_4<=RULE_ID)||(LA17_4>=15 && LA17_4<=17)||(LA17_4>=19 && LA17_4<=21)||(LA17_4>=23 && LA17_4<=59)||(LA17_4>=61 && LA17_4<=87)) ) {
                    alt17=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 4, input);

                    throw nvae;
                }
                }
                break;
            case 75:
                {
                int LA17_5 = input.LA(2);

                if ( (LA17_5==EOF||(LA17_5>=RULE_STRING && LA17_5<=RULE_ID)||(LA17_5>=15 && LA17_5<=17)||(LA17_5>=19 && LA17_5<=21)||(LA17_5>=23 && LA17_5<=59)||(LA17_5>=61 && LA17_5<=87)) ) {
                    alt17=2;
                }
                else if ( (LA17_5==22) ) {
                    alt17=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 17, 5, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
            case RULE_HEX:
            case RULE_INT:
            case RULE_DECIMAL:
            case 15:
            case 17:
            case 19:
            case 23:
            case 24:
            case 31:
            case 47:
            case 48:
            case 53:
            case 62:
            case 64:
            case 68:
            case 69:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 86:
                {
                alt17=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // InternalCheckCfg.g:815:3: ( () ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) )
                    {
                    // InternalCheckCfg.g:815:3: ( () ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) ) )
                    // InternalCheckCfg.g:816:4: () ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ( (lv_value_3_0= ruleXAssignment ) )
                    {
                    // InternalCheckCfg.g:816:4: ()
                    // InternalCheckCfg.g:817:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getXAssignmentAccess().getXAssignmentAction_0_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCheckCfg.g:823:4: ( ( ruleFeatureCallID ) )
                    // InternalCheckCfg.g:824:5: ( ruleFeatureCallID )
                    {
                    // InternalCheckCfg.g:824:5: ( ruleFeatureCallID )
                    // InternalCheckCfg.g:825:6: ruleFeatureCallID
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXAssignmentRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXAssignmentAccess().getFeatureJvmIdentifiableElementCrossReference_0_1_0());
                      					
                    }
                    pushFollow(FOLLOW_14);
                    ruleFeatureCallID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getXAssignmentAccess().getOpSingleAssignParserRuleCall_0_2());
                      			
                    }
                    pushFollow(FOLLOW_20);
                    ruleOpSingleAssign();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				afterParserOrEnumRuleCall();
                      			
                    }
                    // InternalCheckCfg.g:846:4: ( (lv_value_3_0= ruleXAssignment ) )
                    // InternalCheckCfg.g:847:5: (lv_value_3_0= ruleXAssignment )
                    {
                    // InternalCheckCfg.g:847:5: (lv_value_3_0= ruleXAssignment )
                    // InternalCheckCfg.g:848:6: lv_value_3_0= ruleXAssignment
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXAssignmentAccess().getValueXAssignmentParserRuleCall_0_3_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_value_3_0=ruleXAssignment();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXAssignmentRule());
                      						}
                      						set(
                      							current,
                      							"value",
                      							lv_value_3_0,
                      							"org.eclipse.xtext.xbase.Xbase.XAssignment");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:867:3: (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? )
                    {
                    // InternalCheckCfg.g:867:3: (this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )? )
                    // InternalCheckCfg.g:868:4: this_XOrExpression_4= ruleXOrExpression ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )?
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getXAssignmentAccess().getXOrExpressionParserRuleCall_1_0());
                      			
                    }
                    pushFollow(FOLLOW_21);
                    this_XOrExpression_4=ruleXOrExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_XOrExpression_4;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    // InternalCheckCfg.g:876:4: ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )?
                    int alt16=2;
                    alt16 = dfa16.predict(input);
                    switch (alt16) {
                        case 1 :
                            // InternalCheckCfg.g:877:5: ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) )
                            {
                            // InternalCheckCfg.g:877:5: ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) )
                            // InternalCheckCfg.g:878:6: ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) )
                            {
                            // InternalCheckCfg.g:888:6: ( () ( ( ruleOpMultiAssign ) ) )
                            // InternalCheckCfg.g:889:7: () ( ( ruleOpMultiAssign ) )
                            {
                            // InternalCheckCfg.g:889:7: ()
                            // InternalCheckCfg.g:890:8: 
                            {
                            if ( state.backtracking==0 ) {

                              								current = forceCreateModelElementAndSet(
                              									grammarAccess.getXAssignmentAccess().getXBinaryOperationLeftOperandAction_1_1_0_0_0(),
                              									current);
                              							
                            }

                            }

                            // InternalCheckCfg.g:896:7: ( ( ruleOpMultiAssign ) )
                            // InternalCheckCfg.g:897:8: ( ruleOpMultiAssign )
                            {
                            // InternalCheckCfg.g:897:8: ( ruleOpMultiAssign )
                            // InternalCheckCfg.g:898:9: ruleOpMultiAssign
                            {
                            if ( state.backtracking==0 ) {

                              									if (current==null) {
                              										current = createModelElement(grammarAccess.getXAssignmentRule());
                              									}
                              								
                            }
                            if ( state.backtracking==0 ) {

                              									newCompositeNode(grammarAccess.getXAssignmentAccess().getFeatureJvmIdentifiableElementCrossReference_1_1_0_0_1_0());
                              								
                            }
                            pushFollow(FOLLOW_20);
                            ruleOpMultiAssign();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              									afterParserOrEnumRuleCall();
                              								
                            }

                            }


                            }


                            }


                            }

                            // InternalCheckCfg.g:914:5: ( (lv_rightOperand_7_0= ruleXAssignment ) )
                            // InternalCheckCfg.g:915:6: (lv_rightOperand_7_0= ruleXAssignment )
                            {
                            // InternalCheckCfg.g:915:6: (lv_rightOperand_7_0= ruleXAssignment )
                            // InternalCheckCfg.g:916:7: lv_rightOperand_7_0= ruleXAssignment
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getXAssignmentAccess().getRightOperandXAssignmentParserRuleCall_1_1_1_0());
                              						
                            }
                            pushFollow(FOLLOW_2);
                            lv_rightOperand_7_0=ruleXAssignment();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getXAssignmentRule());
                              							}
                              							set(
                              								current,
                              								"rightOperand",
                              								lv_rightOperand_7_0,
                              								"org.eclipse.xtext.xbase.Xbase.XAssignment");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }


                            }
                            break;

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
    // $ANTLR end "ruleXAssignment"


    // $ANTLR start "entryRuleOpSingleAssign"
    // InternalCheckCfg.g:939:1: entryRuleOpSingleAssign returns [String current=null] : iv_ruleOpSingleAssign= ruleOpSingleAssign EOF ;
    public final String entryRuleOpSingleAssign() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpSingleAssign = null;


        try {
            // InternalCheckCfg.g:939:54: (iv_ruleOpSingleAssign= ruleOpSingleAssign EOF )
            // InternalCheckCfg.g:940:2: iv_ruleOpSingleAssign= ruleOpSingleAssign EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpSingleAssignRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpSingleAssign=ruleOpSingleAssign();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpSingleAssign.getText(); 
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
    // $ANTLR end "entryRuleOpSingleAssign"


    // $ANTLR start "ruleOpSingleAssign"
    // InternalCheckCfg.g:946:1: ruleOpSingleAssign returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '=' ;
    public final AntlrDatatypeRuleToken ruleOpSingleAssign() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:952:2: (kw= '=' )
            // InternalCheckCfg.g:953:2: kw= '='
            {
            kw=(Token)match(input,22,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(kw);
              		newLeafNode(kw, grammarAccess.getOpSingleAssignAccess().getEqualsSignKeyword());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpSingleAssign"


    // $ANTLR start "entryRuleOpMultiAssign"
    // InternalCheckCfg.g:961:1: entryRuleOpMultiAssign returns [String current=null] : iv_ruleOpMultiAssign= ruleOpMultiAssign EOF ;
    public final String entryRuleOpMultiAssign() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpMultiAssign = null;


        try {
            // InternalCheckCfg.g:961:53: (iv_ruleOpMultiAssign= ruleOpMultiAssign EOF )
            // InternalCheckCfg.g:962:2: iv_ruleOpMultiAssign= ruleOpMultiAssign EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpMultiAssignRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpMultiAssign=ruleOpMultiAssign();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpMultiAssign.getText(); 
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
    // $ANTLR end "entryRuleOpMultiAssign"


    // $ANTLR start "ruleOpMultiAssign"
    // InternalCheckCfg.g:968:1: ruleOpMultiAssign returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+=' | kw= '-=' | kw= '*=' | kw= '/=' | kw= '%=' | (kw= '<' kw= '<' kw= '=' ) | (kw= '>' (kw= '>' )? kw= '>=' ) ) ;
    public final AntlrDatatypeRuleToken ruleOpMultiAssign() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:974:2: ( (kw= '+=' | kw= '-=' | kw= '*=' | kw= '/=' | kw= '%=' | (kw= '<' kw= '<' kw= '=' ) | (kw= '>' (kw= '>' )? kw= '>=' ) ) )
            // InternalCheckCfg.g:975:2: (kw= '+=' | kw= '-=' | kw= '*=' | kw= '/=' | kw= '%=' | (kw= '<' kw= '<' kw= '=' ) | (kw= '>' (kw= '>' )? kw= '>=' ) )
            {
            // InternalCheckCfg.g:975:2: (kw= '+=' | kw= '-=' | kw= '*=' | kw= '/=' | kw= '%=' | (kw= '<' kw= '<' kw= '=' ) | (kw= '>' (kw= '>' )? kw= '>=' ) )
            int alt19=7;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt19=1;
                }
                break;
            case 27:
                {
                alt19=2;
                }
                break;
            case 28:
                {
                alt19=3;
                }
                break;
            case 29:
                {
                alt19=4;
                }
                break;
            case 30:
                {
                alt19=5;
                }
                break;
            case 31:
                {
                alt19=6;
                }
                break;
            case 32:
                {
                alt19=7;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // InternalCheckCfg.g:976:3: kw= '+='
                    {
                    kw=(Token)match(input,26,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getPlusSignEqualsSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:982:3: kw= '-='
                    {
                    kw=(Token)match(input,27,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getHyphenMinusEqualsSignKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:988:3: kw= '*='
                    {
                    kw=(Token)match(input,28,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getAsteriskEqualsSignKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:994:3: kw= '/='
                    {
                    kw=(Token)match(input,29,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getSolidusEqualsSignKeyword_3());
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalCheckCfg.g:1000:3: kw= '%='
                    {
                    kw=(Token)match(input,30,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getPercentSignEqualsSignKeyword_4());
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalCheckCfg.g:1006:3: (kw= '<' kw= '<' kw= '=' )
                    {
                    // InternalCheckCfg.g:1006:3: (kw= '<' kw= '<' kw= '=' )
                    // InternalCheckCfg.g:1007:4: kw= '<' kw= '<' kw= '='
                    {
                    kw=(Token)match(input,31,FOLLOW_22); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getLessThanSignKeyword_5_0());
                      			
                    }
                    kw=(Token)match(input,31,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getLessThanSignKeyword_5_1());
                      			
                    }
                    kw=(Token)match(input,22,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getEqualsSignKeyword_5_2());
                      			
                    }

                    }


                    }
                    break;
                case 7 :
                    // InternalCheckCfg.g:1024:3: (kw= '>' (kw= '>' )? kw= '>=' )
                    {
                    // InternalCheckCfg.g:1024:3: (kw= '>' (kw= '>' )? kw= '>=' )
                    // InternalCheckCfg.g:1025:4: kw= '>' (kw= '>' )? kw= '>='
                    {
                    kw=(Token)match(input,32,FOLLOW_23); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getGreaterThanSignKeyword_6_0());
                      			
                    }
                    // InternalCheckCfg.g:1030:4: (kw= '>' )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==32) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // InternalCheckCfg.g:1031:5: kw= '>'
                            {
                            kw=(Token)match(input,32,FOLLOW_24); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					current.merge(kw);
                              					newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getGreaterThanSignKeyword_6_1());
                              				
                            }

                            }
                            break;

                    }

                    kw=(Token)match(input,33,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpMultiAssignAccess().getGreaterThanSignEqualsSignKeyword_6_2());
                      			
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
    // $ANTLR end "ruleOpMultiAssign"


    // $ANTLR start "entryRuleXOrExpression"
    // InternalCheckCfg.g:1047:1: entryRuleXOrExpression returns [EObject current=null] : iv_ruleXOrExpression= ruleXOrExpression EOF ;
    public final EObject entryRuleXOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOrExpression = null;


        try {
            // InternalCheckCfg.g:1047:54: (iv_ruleXOrExpression= ruleXOrExpression EOF )
            // InternalCheckCfg.g:1048:2: iv_ruleXOrExpression= ruleXOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXOrExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXOrExpression=ruleXOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXOrExpression; 
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
    // $ANTLR end "entryRuleXOrExpression"


    // $ANTLR start "ruleXOrExpression"
    // InternalCheckCfg.g:1054:1: ruleXOrExpression returns [EObject current=null] : (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* ) ;
    public final EObject ruleXOrExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XAndExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:1060:2: ( (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* ) )
            // InternalCheckCfg.g:1061:2: (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* )
            {
            // InternalCheckCfg.g:1061:2: (this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )* )
            // InternalCheckCfg.g:1062:3: this_XAndExpression_0= ruleXAndExpression ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXOrExpressionAccess().getXAndExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_25);
            this_XAndExpression_0=ruleXAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XAndExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:1070:3: ( ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==34) ) {
                    int LA20_2 = input.LA(2);

                    if ( (synpred2_InternalCheckCfg()) ) {
                        alt20=1;
                    }


                }


                switch (alt20) {
            	case 1 :
            	    // InternalCheckCfg.g:1071:4: ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) ) ( (lv_rightOperand_3_0= ruleXAndExpression ) )
            	    {
            	    // InternalCheckCfg.g:1071:4: ( ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) ) )
            	    // InternalCheckCfg.g:1072:5: ( ( () ( ( ruleOpOr ) ) ) )=> ( () ( ( ruleOpOr ) ) )
            	    {
            	    // InternalCheckCfg.g:1082:5: ( () ( ( ruleOpOr ) ) )
            	    // InternalCheckCfg.g:1083:6: () ( ( ruleOpOr ) )
            	    {
            	    // InternalCheckCfg.g:1083:6: ()
            	    // InternalCheckCfg.g:1084:7: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      							current = forceCreateModelElementAndSet(
            	      								grammarAccess.getXOrExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	      								current);
            	      						
            	    }

            	    }

            	    // InternalCheckCfg.g:1090:6: ( ( ruleOpOr ) )
            	    // InternalCheckCfg.g:1091:7: ( ruleOpOr )
            	    {
            	    // InternalCheckCfg.g:1091:7: ( ruleOpOr )
            	    // InternalCheckCfg.g:1092:8: ruleOpOr
            	    {
            	    if ( state.backtracking==0 ) {

            	      								if (current==null) {
            	      									current = createModelElement(grammarAccess.getXOrExpressionRule());
            	      								}
            	      							
            	    }
            	    if ( state.backtracking==0 ) {

            	      								newCompositeNode(grammarAccess.getXOrExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0());
            	      							
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpOr();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      								afterParserOrEnumRuleCall();
            	      							
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:1108:4: ( (lv_rightOperand_3_0= ruleXAndExpression ) )
            	    // InternalCheckCfg.g:1109:5: (lv_rightOperand_3_0= ruleXAndExpression )
            	    {
            	    // InternalCheckCfg.g:1109:5: (lv_rightOperand_3_0= ruleXAndExpression )
            	    // InternalCheckCfg.g:1110:6: lv_rightOperand_3_0= ruleXAndExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXOrExpressionAccess().getRightOperandXAndExpressionParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_25);
            	    lv_rightOperand_3_0=ruleXAndExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXOrExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"rightOperand",
            	      							lv_rightOperand_3_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XAndExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

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

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXOrExpression"


    // $ANTLR start "entryRuleOpOr"
    // InternalCheckCfg.g:1132:1: entryRuleOpOr returns [String current=null] : iv_ruleOpOr= ruleOpOr EOF ;
    public final String entryRuleOpOr() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpOr = null;


        try {
            // InternalCheckCfg.g:1132:44: (iv_ruleOpOr= ruleOpOr EOF )
            // InternalCheckCfg.g:1133:2: iv_ruleOpOr= ruleOpOr EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpOrRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpOr=ruleOpOr();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpOr.getText(); 
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
    // $ANTLR end "entryRuleOpOr"


    // $ANTLR start "ruleOpOr"
    // InternalCheckCfg.g:1139:1: ruleOpOr returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '||' ;
    public final AntlrDatatypeRuleToken ruleOpOr() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:1145:2: (kw= '||' )
            // InternalCheckCfg.g:1146:2: kw= '||'
            {
            kw=(Token)match(input,34,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(kw);
              		newLeafNode(kw, grammarAccess.getOpOrAccess().getVerticalLineVerticalLineKeyword());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpOr"


    // $ANTLR start "entryRuleXAndExpression"
    // InternalCheckCfg.g:1154:1: entryRuleXAndExpression returns [EObject current=null] : iv_ruleXAndExpression= ruleXAndExpression EOF ;
    public final EObject entryRuleXAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAndExpression = null;


        try {
            // InternalCheckCfg.g:1154:55: (iv_ruleXAndExpression= ruleXAndExpression EOF )
            // InternalCheckCfg.g:1155:2: iv_ruleXAndExpression= ruleXAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXAndExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXAndExpression=ruleXAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXAndExpression; 
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
    // $ANTLR end "entryRuleXAndExpression"


    // $ANTLR start "ruleXAndExpression"
    // InternalCheckCfg.g:1161:1: ruleXAndExpression returns [EObject current=null] : (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* ) ;
    public final EObject ruleXAndExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XEqualityExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:1167:2: ( (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* ) )
            // InternalCheckCfg.g:1168:2: (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* )
            {
            // InternalCheckCfg.g:1168:2: (this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )* )
            // InternalCheckCfg.g:1169:3: this_XEqualityExpression_0= ruleXEqualityExpression ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXAndExpressionAccess().getXEqualityExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_26);
            this_XEqualityExpression_0=ruleXEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XEqualityExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:1177:3: ( ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==35) ) {
                    int LA21_2 = input.LA(2);

                    if ( (synpred3_InternalCheckCfg()) ) {
                        alt21=1;
                    }


                }


                switch (alt21) {
            	case 1 :
            	    // InternalCheckCfg.g:1178:4: ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) ) ( (lv_rightOperand_3_0= ruleXEqualityExpression ) )
            	    {
            	    // InternalCheckCfg.g:1178:4: ( ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) ) )
            	    // InternalCheckCfg.g:1179:5: ( ( () ( ( ruleOpAnd ) ) ) )=> ( () ( ( ruleOpAnd ) ) )
            	    {
            	    // InternalCheckCfg.g:1189:5: ( () ( ( ruleOpAnd ) ) )
            	    // InternalCheckCfg.g:1190:6: () ( ( ruleOpAnd ) )
            	    {
            	    // InternalCheckCfg.g:1190:6: ()
            	    // InternalCheckCfg.g:1191:7: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      							current = forceCreateModelElementAndSet(
            	      								grammarAccess.getXAndExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	      								current);
            	      						
            	    }

            	    }

            	    // InternalCheckCfg.g:1197:6: ( ( ruleOpAnd ) )
            	    // InternalCheckCfg.g:1198:7: ( ruleOpAnd )
            	    {
            	    // InternalCheckCfg.g:1198:7: ( ruleOpAnd )
            	    // InternalCheckCfg.g:1199:8: ruleOpAnd
            	    {
            	    if ( state.backtracking==0 ) {

            	      								if (current==null) {
            	      									current = createModelElement(grammarAccess.getXAndExpressionRule());
            	      								}
            	      							
            	    }
            	    if ( state.backtracking==0 ) {

            	      								newCompositeNode(grammarAccess.getXAndExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0());
            	      							
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpAnd();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      								afterParserOrEnumRuleCall();
            	      							
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:1215:4: ( (lv_rightOperand_3_0= ruleXEqualityExpression ) )
            	    // InternalCheckCfg.g:1216:5: (lv_rightOperand_3_0= ruleXEqualityExpression )
            	    {
            	    // InternalCheckCfg.g:1216:5: (lv_rightOperand_3_0= ruleXEqualityExpression )
            	    // InternalCheckCfg.g:1217:6: lv_rightOperand_3_0= ruleXEqualityExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXAndExpressionAccess().getRightOperandXEqualityExpressionParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_26);
            	    lv_rightOperand_3_0=ruleXEqualityExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXAndExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"rightOperand",
            	      							lv_rightOperand_3_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XEqualityExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // $ANTLR end "ruleXAndExpression"


    // $ANTLR start "entryRuleOpAnd"
    // InternalCheckCfg.g:1239:1: entryRuleOpAnd returns [String current=null] : iv_ruleOpAnd= ruleOpAnd EOF ;
    public final String entryRuleOpAnd() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpAnd = null;


        try {
            // InternalCheckCfg.g:1239:45: (iv_ruleOpAnd= ruleOpAnd EOF )
            // InternalCheckCfg.g:1240:2: iv_ruleOpAnd= ruleOpAnd EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpAndRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpAnd=ruleOpAnd();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpAnd.getText(); 
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
    // $ANTLR end "entryRuleOpAnd"


    // $ANTLR start "ruleOpAnd"
    // InternalCheckCfg.g:1246:1: ruleOpAnd returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= '&&' ;
    public final AntlrDatatypeRuleToken ruleOpAnd() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:1252:2: (kw= '&&' )
            // InternalCheckCfg.g:1253:2: kw= '&&'
            {
            kw=(Token)match(input,35,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(kw);
              		newLeafNode(kw, grammarAccess.getOpAndAccess().getAmpersandAmpersandKeyword());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOpAnd"


    // $ANTLR start "entryRuleXEqualityExpression"
    // InternalCheckCfg.g:1261:1: entryRuleXEqualityExpression returns [EObject current=null] : iv_ruleXEqualityExpression= ruleXEqualityExpression EOF ;
    public final EObject entryRuleXEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXEqualityExpression = null;


        try {
            // InternalCheckCfg.g:1261:60: (iv_ruleXEqualityExpression= ruleXEqualityExpression EOF )
            // InternalCheckCfg.g:1262:2: iv_ruleXEqualityExpression= ruleXEqualityExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXEqualityExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXEqualityExpression=ruleXEqualityExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXEqualityExpression; 
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
    // $ANTLR end "entryRuleXEqualityExpression"


    // $ANTLR start "ruleXEqualityExpression"
    // InternalCheckCfg.g:1268:1: ruleXEqualityExpression returns [EObject current=null] : (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* ) ;
    public final EObject ruleXEqualityExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XRelationalExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:1274:2: ( (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* ) )
            // InternalCheckCfg.g:1275:2: (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* )
            {
            // InternalCheckCfg.g:1275:2: (this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )* )
            // InternalCheckCfg.g:1276:3: this_XRelationalExpression_0= ruleXRelationalExpression ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXEqualityExpressionAccess().getXRelationalExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_27);
            this_XRelationalExpression_0=ruleXRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XRelationalExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:1284:3: ( ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) ) )*
            loop22:
            do {
                int alt22=2;
                switch ( input.LA(1) ) {
                case 36:
                    {
                    int LA22_2 = input.LA(2);

                    if ( (synpred4_InternalCheckCfg()) ) {
                        alt22=1;
                    }


                    }
                    break;
                case 37:
                    {
                    int LA22_3 = input.LA(2);

                    if ( (synpred4_InternalCheckCfg()) ) {
                        alt22=1;
                    }


                    }
                    break;
                case 38:
                    {
                    int LA22_4 = input.LA(2);

                    if ( (synpred4_InternalCheckCfg()) ) {
                        alt22=1;
                    }


                    }
                    break;
                case 39:
                    {
                    int LA22_5 = input.LA(2);

                    if ( (synpred4_InternalCheckCfg()) ) {
                        alt22=1;
                    }


                    }
                    break;

                }

                switch (alt22) {
            	case 1 :
            	    // InternalCheckCfg.g:1285:4: ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) ) ( (lv_rightOperand_3_0= ruleXRelationalExpression ) )
            	    {
            	    // InternalCheckCfg.g:1285:4: ( ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) ) )
            	    // InternalCheckCfg.g:1286:5: ( ( () ( ( ruleOpEquality ) ) ) )=> ( () ( ( ruleOpEquality ) ) )
            	    {
            	    // InternalCheckCfg.g:1296:5: ( () ( ( ruleOpEquality ) ) )
            	    // InternalCheckCfg.g:1297:6: () ( ( ruleOpEquality ) )
            	    {
            	    // InternalCheckCfg.g:1297:6: ()
            	    // InternalCheckCfg.g:1298:7: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      							current = forceCreateModelElementAndSet(
            	      								grammarAccess.getXEqualityExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	      								current);
            	      						
            	    }

            	    }

            	    // InternalCheckCfg.g:1304:6: ( ( ruleOpEquality ) )
            	    // InternalCheckCfg.g:1305:7: ( ruleOpEquality )
            	    {
            	    // InternalCheckCfg.g:1305:7: ( ruleOpEquality )
            	    // InternalCheckCfg.g:1306:8: ruleOpEquality
            	    {
            	    if ( state.backtracking==0 ) {

            	      								if (current==null) {
            	      									current = createModelElement(grammarAccess.getXEqualityExpressionRule());
            	      								}
            	      							
            	    }
            	    if ( state.backtracking==0 ) {

            	      								newCompositeNode(grammarAccess.getXEqualityExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0());
            	      							
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpEquality();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      								afterParserOrEnumRuleCall();
            	      							
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:1322:4: ( (lv_rightOperand_3_0= ruleXRelationalExpression ) )
            	    // InternalCheckCfg.g:1323:5: (lv_rightOperand_3_0= ruleXRelationalExpression )
            	    {
            	    // InternalCheckCfg.g:1323:5: (lv_rightOperand_3_0= ruleXRelationalExpression )
            	    // InternalCheckCfg.g:1324:6: lv_rightOperand_3_0= ruleXRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXEqualityExpressionAccess().getRightOperandXRelationalExpressionParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_27);
            	    lv_rightOperand_3_0=ruleXRelationalExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXEqualityExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"rightOperand",
            	      							lv_rightOperand_3_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XRelationalExpression");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
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
    // $ANTLR end "ruleXEqualityExpression"


    // $ANTLR start "entryRuleOpEquality"
    // InternalCheckCfg.g:1346:1: entryRuleOpEquality returns [String current=null] : iv_ruleOpEquality= ruleOpEquality EOF ;
    public final String entryRuleOpEquality() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpEquality = null;


        try {
            // InternalCheckCfg.g:1346:50: (iv_ruleOpEquality= ruleOpEquality EOF )
            // InternalCheckCfg.g:1347:2: iv_ruleOpEquality= ruleOpEquality EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpEqualityRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpEquality=ruleOpEquality();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpEquality.getText(); 
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
    // $ANTLR end "entryRuleOpEquality"


    // $ANTLR start "ruleOpEquality"
    // InternalCheckCfg.g:1353:1: ruleOpEquality returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '==' | kw= '!=' | kw= '===' | kw= '!==' ) ;
    public final AntlrDatatypeRuleToken ruleOpEquality() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:1359:2: ( (kw= '==' | kw= '!=' | kw= '===' | kw= '!==' ) )
            // InternalCheckCfg.g:1360:2: (kw= '==' | kw= '!=' | kw= '===' | kw= '!==' )
            {
            // InternalCheckCfg.g:1360:2: (kw= '==' | kw= '!=' | kw= '===' | kw= '!==' )
            int alt23=4;
            switch ( input.LA(1) ) {
            case 36:
                {
                alt23=1;
                }
                break;
            case 37:
                {
                alt23=2;
                }
                break;
            case 38:
                {
                alt23=3;
                }
                break;
            case 39:
                {
                alt23=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // InternalCheckCfg.g:1361:3: kw= '=='
                    {
                    kw=(Token)match(input,36,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpEqualityAccess().getEqualsSignEqualsSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:1367:3: kw= '!='
                    {
                    kw=(Token)match(input,37,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpEqualityAccess().getExclamationMarkEqualsSignKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:1373:3: kw= '==='
                    {
                    kw=(Token)match(input,38,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpEqualityAccess().getEqualsSignEqualsSignEqualsSignKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:1379:3: kw= '!=='
                    {
                    kw=(Token)match(input,39,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpEqualityAccess().getExclamationMarkEqualsSignEqualsSignKeyword_3());
                      		
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
    // $ANTLR end "ruleOpEquality"


    // $ANTLR start "entryRuleXRelationalExpression"
    // InternalCheckCfg.g:1388:1: entryRuleXRelationalExpression returns [EObject current=null] : iv_ruleXRelationalExpression= ruleXRelationalExpression EOF ;
    public final EObject entryRuleXRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXRelationalExpression = null;


        try {
            // InternalCheckCfg.g:1388:62: (iv_ruleXRelationalExpression= ruleXRelationalExpression EOF )
            // InternalCheckCfg.g:1389:2: iv_ruleXRelationalExpression= ruleXRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXRelationalExpression=ruleXRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXRelationalExpression; 
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
    // $ANTLR end "entryRuleXRelationalExpression"


    // $ANTLR start "ruleXRelationalExpression"
    // InternalCheckCfg.g:1395:1: ruleXRelationalExpression returns [EObject current=null] : (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* ) ;
    public final EObject ruleXRelationalExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XOtherOperatorExpression_0 = null;

        EObject lv_type_3_0 = null;

        EObject lv_rightOperand_6_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:1401:2: ( (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* ) )
            // InternalCheckCfg.g:1402:2: (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* )
            {
            // InternalCheckCfg.g:1402:2: (this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )* )
            // InternalCheckCfg.g:1403:3: this_XOtherOperatorExpression_0= ruleXOtherOperatorExpression ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getXOtherOperatorExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_28);
            this_XOtherOperatorExpression_0=ruleXOtherOperatorExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XOtherOperatorExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:1411:3: ( ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) ) | ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) ) )*
            loop24:
            do {
                int alt24=3;
                switch ( input.LA(1) ) {
                case 31:
                    {
                    int LA24_2 = input.LA(2);

                    if ( (synpred6_InternalCheckCfg()) ) {
                        alt24=2;
                    }


                    }
                    break;
                case 32:
                    {
                    int LA24_3 = input.LA(2);

                    if ( (synpred6_InternalCheckCfg()) ) {
                        alt24=2;
                    }


                    }
                    break;
                case 40:
                    {
                    int LA24_4 = input.LA(2);

                    if ( (synpred5_InternalCheckCfg()) ) {
                        alt24=1;
                    }


                    }
                    break;
                case 33:
                    {
                    int LA24_5 = input.LA(2);

                    if ( (synpred6_InternalCheckCfg()) ) {
                        alt24=2;
                    }


                    }
                    break;

                }

                switch (alt24) {
            	case 1 :
            	    // InternalCheckCfg.g:1412:4: ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )
            	    {
            	    // InternalCheckCfg.g:1412:4: ( ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )
            	    // InternalCheckCfg.g:1413:5: ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    {
            	    // InternalCheckCfg.g:1413:5: ( ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' ) )
            	    // InternalCheckCfg.g:1414:6: ( ( () 'instanceof' ) )=> ( () otherlv_2= 'instanceof' )
            	    {
            	    // InternalCheckCfg.g:1420:6: ( () otherlv_2= 'instanceof' )
            	    // InternalCheckCfg.g:1421:7: () otherlv_2= 'instanceof'
            	    {
            	    // InternalCheckCfg.g:1421:7: ()
            	    // InternalCheckCfg.g:1422:8: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      								current = forceCreateModelElementAndSet(
            	      									grammarAccess.getXRelationalExpressionAccess().getXInstanceOfExpressionExpressionAction_1_0_0_0_0(),
            	      									current);
            	      							
            	    }

            	    }

            	    otherlv_2=(Token)match(input,40,FOLLOW_29); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      							newLeafNode(otherlv_2, grammarAccess.getXRelationalExpressionAccess().getInstanceofKeyword_1_0_0_0_1());
            	      						
            	    }

            	    }


            	    }

            	    // InternalCheckCfg.g:1434:5: ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    // InternalCheckCfg.g:1435:6: (lv_type_3_0= ruleJvmTypeReference )
            	    {
            	    // InternalCheckCfg.g:1435:6: (lv_type_3_0= ruleJvmTypeReference )
            	    // InternalCheckCfg.g:1436:7: lv_type_3_0= ruleJvmTypeReference
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getTypeJvmTypeReferenceParserRuleCall_1_0_1_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_28);
            	    lv_type_3_0=ruleJvmTypeReference();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      							if (current==null) {
            	      								current = createModelElementForParent(grammarAccess.getXRelationalExpressionRule());
            	      							}
            	      							set(
            	      								current,
            	      								"type",
            	      								lv_type_3_0,
            	      								"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
            	      							afterParserOrEnumRuleCall();
            	      						
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCheckCfg.g:1455:4: ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) )
            	    {
            	    // InternalCheckCfg.g:1455:4: ( ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) ) )
            	    // InternalCheckCfg.g:1456:5: ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) ) ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) )
            	    {
            	    // InternalCheckCfg.g:1456:5: ( ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) ) )
            	    // InternalCheckCfg.g:1457:6: ( ( () ( ( ruleOpCompare ) ) ) )=> ( () ( ( ruleOpCompare ) ) )
            	    {
            	    // InternalCheckCfg.g:1467:6: ( () ( ( ruleOpCompare ) ) )
            	    // InternalCheckCfg.g:1468:7: () ( ( ruleOpCompare ) )
            	    {
            	    // InternalCheckCfg.g:1468:7: ()
            	    // InternalCheckCfg.g:1469:8: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      								current = forceCreateModelElementAndSet(
            	      									grammarAccess.getXRelationalExpressionAccess().getXBinaryOperationLeftOperandAction_1_1_0_0_0(),
            	      									current);
            	      							
            	    }

            	    }

            	    // InternalCheckCfg.g:1475:7: ( ( ruleOpCompare ) )
            	    // InternalCheckCfg.g:1476:8: ( ruleOpCompare )
            	    {
            	    // InternalCheckCfg.g:1476:8: ( ruleOpCompare )
            	    // InternalCheckCfg.g:1477:9: ruleOpCompare
            	    {
            	    if ( state.backtracking==0 ) {

            	      									if (current==null) {
            	      										current = createModelElement(grammarAccess.getXRelationalExpressionRule());
            	      									}
            	      								
            	    }
            	    if ( state.backtracking==0 ) {

            	      									newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_1_0_0_1_0());
            	      								
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpCompare();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									afterParserOrEnumRuleCall();
            	      								
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:1493:5: ( (lv_rightOperand_6_0= ruleXOtherOperatorExpression ) )
            	    // InternalCheckCfg.g:1494:6: (lv_rightOperand_6_0= ruleXOtherOperatorExpression )
            	    {
            	    // InternalCheckCfg.g:1494:6: (lv_rightOperand_6_0= ruleXOtherOperatorExpression )
            	    // InternalCheckCfg.g:1495:7: lv_rightOperand_6_0= ruleXOtherOperatorExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getXRelationalExpressionAccess().getRightOperandXOtherOperatorExpressionParserRuleCall_1_1_1_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_28);
            	    lv_rightOperand_6_0=ruleXOtherOperatorExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      							if (current==null) {
            	      								current = createModelElementForParent(grammarAccess.getXRelationalExpressionRule());
            	      							}
            	      							set(
            	      								current,
            	      								"rightOperand",
            	      								lv_rightOperand_6_0,
            	      								"org.eclipse.xtext.xbase.Xbase.XOtherOperatorExpression");
            	      							afterParserOrEnumRuleCall();
            	      						
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // $ANTLR end "ruleXRelationalExpression"


    // $ANTLR start "entryRuleOpCompare"
    // InternalCheckCfg.g:1518:1: entryRuleOpCompare returns [String current=null] : iv_ruleOpCompare= ruleOpCompare EOF ;
    public final String entryRuleOpCompare() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpCompare = null;


        try {
            // InternalCheckCfg.g:1518:49: (iv_ruleOpCompare= ruleOpCompare EOF )
            // InternalCheckCfg.g:1519:2: iv_ruleOpCompare= ruleOpCompare EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpCompareRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpCompare=ruleOpCompare();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpCompare.getText(); 
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
    // $ANTLR end "entryRuleOpCompare"


    // $ANTLR start "ruleOpCompare"
    // InternalCheckCfg.g:1525:1: ruleOpCompare returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '>=' | (kw= '<' kw= '=' ) | kw= '>' | kw= '<' ) ;
    public final AntlrDatatypeRuleToken ruleOpCompare() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:1531:2: ( (kw= '>=' | (kw= '<' kw= '=' ) | kw= '>' | kw= '<' ) )
            // InternalCheckCfg.g:1532:2: (kw= '>=' | (kw= '<' kw= '=' ) | kw= '>' | kw= '<' )
            {
            // InternalCheckCfg.g:1532:2: (kw= '>=' | (kw= '<' kw= '=' ) | kw= '>' | kw= '<' )
            int alt25=4;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt25=1;
                }
                break;
            case 31:
                {
                int LA25_2 = input.LA(2);

                if ( (LA25_2==EOF||(LA25_2>=RULE_STRING && LA25_2<=RULE_ID)||LA25_2==15||LA25_2==17||LA25_2==19||(LA25_2>=23 && LA25_2<=24)||LA25_2==31||(LA25_2>=47 && LA25_2<=48)||LA25_2==53||LA25_2==62||LA25_2==64||(LA25_2>=68 && LA25_2<=69)||(LA25_2>=72 && LA25_2<=84)||LA25_2==86) ) {
                    alt25=4;
                }
                else if ( (LA25_2==22) ) {
                    alt25=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 2, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                alt25=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // InternalCheckCfg.g:1533:3: kw= '>='
                    {
                    kw=(Token)match(input,33,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignEqualsSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:1539:3: (kw= '<' kw= '=' )
                    {
                    // InternalCheckCfg.g:1539:3: (kw= '<' kw= '=' )
                    // InternalCheckCfg.g:1540:4: kw= '<' kw= '='
                    {
                    kw=(Token)match(input,31,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpCompareAccess().getLessThanSignKeyword_1_0());
                      			
                    }
                    kw=(Token)match(input,22,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpCompareAccess().getEqualsSignKeyword_1_1());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:1552:3: kw= '>'
                    {
                    kw=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getGreaterThanSignKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:1558:3: kw= '<'
                    {
                    kw=(Token)match(input,31,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpCompareAccess().getLessThanSignKeyword_3());
                      		
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
    // $ANTLR end "ruleOpCompare"


    // $ANTLR start "entryRuleXOtherOperatorExpression"
    // InternalCheckCfg.g:1567:1: entryRuleXOtherOperatorExpression returns [EObject current=null] : iv_ruleXOtherOperatorExpression= ruleXOtherOperatorExpression EOF ;
    public final EObject entryRuleXOtherOperatorExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOtherOperatorExpression = null;


        try {
            // InternalCheckCfg.g:1567:65: (iv_ruleXOtherOperatorExpression= ruleXOtherOperatorExpression EOF )
            // InternalCheckCfg.g:1568:2: iv_ruleXOtherOperatorExpression= ruleXOtherOperatorExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXOtherOperatorExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXOtherOperatorExpression=ruleXOtherOperatorExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXOtherOperatorExpression; 
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
    // $ANTLR end "entryRuleXOtherOperatorExpression"


    // $ANTLR start "ruleXOtherOperatorExpression"
    // InternalCheckCfg.g:1574:1: ruleXOtherOperatorExpression returns [EObject current=null] : (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* ) ;
    public final EObject ruleXOtherOperatorExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XAdditiveExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:1580:2: ( (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* ) )
            // InternalCheckCfg.g:1581:2: (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* )
            {
            // InternalCheckCfg.g:1581:2: (this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )* )
            // InternalCheckCfg.g:1582:3: this_XAdditiveExpression_0= ruleXAdditiveExpression ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXOtherOperatorExpressionAccess().getXAdditiveExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_30);
            this_XAdditiveExpression_0=ruleXAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XAdditiveExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:1590:3: ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )*
            loop26:
            do {
                int alt26=2;
                alt26 = dfa26.predict(input);
                switch (alt26) {
            	case 1 :
            	    // InternalCheckCfg.g:1591:4: ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) )
            	    {
            	    // InternalCheckCfg.g:1591:4: ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) )
            	    // InternalCheckCfg.g:1592:5: ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) )
            	    {
            	    // InternalCheckCfg.g:1602:5: ( () ( ( ruleOpOther ) ) )
            	    // InternalCheckCfg.g:1603:6: () ( ( ruleOpOther ) )
            	    {
            	    // InternalCheckCfg.g:1603:6: ()
            	    // InternalCheckCfg.g:1604:7: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      							current = forceCreateModelElementAndSet(
            	      								grammarAccess.getXOtherOperatorExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	      								current);
            	      						
            	    }

            	    }

            	    // InternalCheckCfg.g:1610:6: ( ( ruleOpOther ) )
            	    // InternalCheckCfg.g:1611:7: ( ruleOpOther )
            	    {
            	    // InternalCheckCfg.g:1611:7: ( ruleOpOther )
            	    // InternalCheckCfg.g:1612:8: ruleOpOther
            	    {
            	    if ( state.backtracking==0 ) {

            	      								if (current==null) {
            	      									current = createModelElement(grammarAccess.getXOtherOperatorExpressionRule());
            	      								}
            	      							
            	    }
            	    if ( state.backtracking==0 ) {

            	      								newCompositeNode(grammarAccess.getXOtherOperatorExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0());
            	      							
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpOther();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      								afterParserOrEnumRuleCall();
            	      							
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:1628:4: ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) )
            	    // InternalCheckCfg.g:1629:5: (lv_rightOperand_3_0= ruleXAdditiveExpression )
            	    {
            	    // InternalCheckCfg.g:1629:5: (lv_rightOperand_3_0= ruleXAdditiveExpression )
            	    // InternalCheckCfg.g:1630:6: lv_rightOperand_3_0= ruleXAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXOtherOperatorExpressionAccess().getRightOperandXAdditiveExpressionParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_30);
            	    lv_rightOperand_3_0=ruleXAdditiveExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXOtherOperatorExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"rightOperand",
            	      							lv_rightOperand_3_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XAdditiveExpression");
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


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXOtherOperatorExpression"


    // $ANTLR start "entryRuleOpOther"
    // InternalCheckCfg.g:1652:1: entryRuleOpOther returns [String current=null] : iv_ruleOpOther= ruleOpOther EOF ;
    public final String entryRuleOpOther() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpOther = null;


        try {
            // InternalCheckCfg.g:1652:47: (iv_ruleOpOther= ruleOpOther EOF )
            // InternalCheckCfg.g:1653:2: iv_ruleOpOther= ruleOpOther EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpOtherRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpOther=ruleOpOther();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpOther.getText(); 
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
    // $ANTLR end "entryRuleOpOther"


    // $ANTLR start "ruleOpOther"
    // InternalCheckCfg.g:1659:1: ruleOpOther returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '->' | kw= '..<' | (kw= '>' kw= '..' ) | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' ) ) | kw= '<>' | kw= '?:' ) ;
    public final AntlrDatatypeRuleToken ruleOpOther() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:1665:2: ( (kw= '->' | kw= '..<' | (kw= '>' kw= '..' ) | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' ) ) | kw= '<>' | kw= '?:' ) )
            // InternalCheckCfg.g:1666:2: (kw= '->' | kw= '..<' | (kw= '>' kw= '..' ) | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' ) ) | kw= '<>' | kw= '?:' )
            {
            // InternalCheckCfg.g:1666:2: (kw= '->' | kw= '..<' | (kw= '>' kw= '..' ) | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' ) ) | kw= '<>' | kw= '?:' )
            int alt29=9;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // InternalCheckCfg.g:1667:3: kw= '->'
                    {
                    kw=(Token)match(input,41,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpOtherAccess().getHyphenMinusGreaterThanSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:1673:3: kw= '..<'
                    {
                    kw=(Token)match(input,42,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpOtherAccess().getFullStopFullStopLessThanSignKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:1679:3: (kw= '>' kw= '..' )
                    {
                    // InternalCheckCfg.g:1679:3: (kw= '>' kw= '..' )
                    // InternalCheckCfg.g:1680:4: kw= '>' kw= '..'
                    {
                    kw=(Token)match(input,32,FOLLOW_31); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_2_0());
                      			
                    }
                    kw=(Token)match(input,43,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpOtherAccess().getFullStopFullStopKeyword_2_1());
                      			
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:1692:3: kw= '..'
                    {
                    kw=(Token)match(input,43,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpOtherAccess().getFullStopFullStopKeyword_3());
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalCheckCfg.g:1698:3: kw= '=>'
                    {
                    kw=(Token)match(input,44,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpOtherAccess().getEqualsSignGreaterThanSignKeyword_4());
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalCheckCfg.g:1704:3: (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) )
                    {
                    // InternalCheckCfg.g:1704:3: (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) )
                    // InternalCheckCfg.g:1705:4: kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' )
                    {
                    kw=(Token)match(input,32,FOLLOW_32); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_5_0());
                      			
                    }
                    // InternalCheckCfg.g:1710:4: ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==32) ) {
                        int LA27_1 = input.LA(2);

                        if ( (LA27_1==EOF||(LA27_1>=RULE_STRING && LA27_1<=RULE_ID)||LA27_1==15||LA27_1==17||LA27_1==19||(LA27_1>=23 && LA27_1<=24)||LA27_1==31||(LA27_1>=47 && LA27_1<=48)||LA27_1==53||LA27_1==62||LA27_1==64||(LA27_1>=68 && LA27_1<=69)||(LA27_1>=72 && LA27_1<=84)||LA27_1==86) ) {
                            alt27=2;
                        }
                        else if ( (LA27_1==32) && (synpred8_InternalCheckCfg())) {
                            alt27=1;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 27, 1, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }
                    switch (alt27) {
                        case 1 :
                            // InternalCheckCfg.g:1711:5: ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) )
                            {
                            // InternalCheckCfg.g:1711:5: ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) )
                            // InternalCheckCfg.g:1712:6: ( ( '>' '>' ) )=> (kw= '>' kw= '>' )
                            {
                            // InternalCheckCfg.g:1717:6: (kw= '>' kw= '>' )
                            // InternalCheckCfg.g:1718:7: kw= '>' kw= '>'
                            {
                            kw=(Token)match(input,32,FOLLOW_32); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							current.merge(kw);
                              							newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_5_1_0_0_0());
                              						
                            }
                            kw=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							current.merge(kw);
                              							newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_5_1_0_0_1());
                              						
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalCheckCfg.g:1731:5: kw= '>'
                            {
                            kw=(Token)match(input,32,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					current.merge(kw);
                              					newLeafNode(kw, grammarAccess.getOpOtherAccess().getGreaterThanSignKeyword_5_1_1());
                              				
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 7 :
                    // InternalCheckCfg.g:1739:3: (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' ) )
                    {
                    // InternalCheckCfg.g:1739:3: (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' ) )
                    // InternalCheckCfg.g:1740:4: kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' )
                    {
                    kw=(Token)match(input,31,FOLLOW_33); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current.merge(kw);
                      				newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_6_0());
                      			
                    }
                    // InternalCheckCfg.g:1745:4: ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' )
                    int alt28=3;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==31) ) {
                        int LA28_1 = input.LA(2);

                        if ( (synpred9_InternalCheckCfg()) ) {
                            alt28=1;
                        }
                        else if ( (true) ) {
                            alt28=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return current;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 28, 1, input);

                            throw nvae;
                        }
                    }
                    else if ( (LA28_0==44) ) {
                        alt28=3;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 28, 0, input);

                        throw nvae;
                    }
                    switch (alt28) {
                        case 1 :
                            // InternalCheckCfg.g:1746:5: ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) )
                            {
                            // InternalCheckCfg.g:1746:5: ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) )
                            // InternalCheckCfg.g:1747:6: ( ( '<' '<' ) )=> (kw= '<' kw= '<' )
                            {
                            // InternalCheckCfg.g:1752:6: (kw= '<' kw= '<' )
                            // InternalCheckCfg.g:1753:7: kw= '<' kw= '<'
                            {
                            kw=(Token)match(input,31,FOLLOW_22); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							current.merge(kw);
                              							newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_6_1_0_0_0());
                              						
                            }
                            kw=(Token)match(input,31,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							current.merge(kw);
                              							newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_6_1_0_0_1());
                              						
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalCheckCfg.g:1766:5: kw= '<'
                            {
                            kw=(Token)match(input,31,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					current.merge(kw);
                              					newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignKeyword_6_1_1());
                              				
                            }

                            }
                            break;
                        case 3 :
                            // InternalCheckCfg.g:1772:5: kw= '=>'
                            {
                            kw=(Token)match(input,44,FOLLOW_2); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					current.merge(kw);
                              					newLeafNode(kw, grammarAccess.getOpOtherAccess().getEqualsSignGreaterThanSignKeyword_6_1_2());
                              				
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 8 :
                    // InternalCheckCfg.g:1780:3: kw= '<>'
                    {
                    kw=(Token)match(input,45,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpOtherAccess().getLessThanSignGreaterThanSignKeyword_7());
                      		
                    }

                    }
                    break;
                case 9 :
                    // InternalCheckCfg.g:1786:3: kw= '?:'
                    {
                    kw=(Token)match(input,46,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpOtherAccess().getQuestionMarkColonKeyword_8());
                      		
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
    // $ANTLR end "ruleOpOther"


    // $ANTLR start "entryRuleXAdditiveExpression"
    // InternalCheckCfg.g:1795:1: entryRuleXAdditiveExpression returns [EObject current=null] : iv_ruleXAdditiveExpression= ruleXAdditiveExpression EOF ;
    public final EObject entryRuleXAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAdditiveExpression = null;


        try {
            // InternalCheckCfg.g:1795:60: (iv_ruleXAdditiveExpression= ruleXAdditiveExpression EOF )
            // InternalCheckCfg.g:1796:2: iv_ruleXAdditiveExpression= ruleXAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXAdditiveExpression=ruleXAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXAdditiveExpression; 
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
    // $ANTLR end "entryRuleXAdditiveExpression"


    // $ANTLR start "ruleXAdditiveExpression"
    // InternalCheckCfg.g:1802:1: ruleXAdditiveExpression returns [EObject current=null] : (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleXAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XMultiplicativeExpression_0 = null;

        EObject lv_rightOperand_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:1808:2: ( (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* ) )
            // InternalCheckCfg.g:1809:2: (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* )
            {
            // InternalCheckCfg.g:1809:2: (this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )* )
            // InternalCheckCfg.g:1810:3: this_XMultiplicativeExpression_0= ruleXMultiplicativeExpression ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXAdditiveExpressionAccess().getXMultiplicativeExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_34);
            this_XMultiplicativeExpression_0=ruleXMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XMultiplicativeExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:1818:3: ( ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==47) ) {
                    int LA30_2 = input.LA(2);

                    if ( (synpred10_InternalCheckCfg()) ) {
                        alt30=1;
                    }


                }
                else if ( (LA30_0==48) ) {
                    int LA30_3 = input.LA(2);

                    if ( (synpred10_InternalCheckCfg()) ) {
                        alt30=1;
                    }


                }


                switch (alt30) {
            	case 1 :
            	    // InternalCheckCfg.g:1819:4: ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) ) ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) )
            	    {
            	    // InternalCheckCfg.g:1819:4: ( ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) ) )
            	    // InternalCheckCfg.g:1820:5: ( ( () ( ( ruleOpAdd ) ) ) )=> ( () ( ( ruleOpAdd ) ) )
            	    {
            	    // InternalCheckCfg.g:1830:5: ( () ( ( ruleOpAdd ) ) )
            	    // InternalCheckCfg.g:1831:6: () ( ( ruleOpAdd ) )
            	    {
            	    // InternalCheckCfg.g:1831:6: ()
            	    // InternalCheckCfg.g:1832:7: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      							current = forceCreateModelElementAndSet(
            	      								grammarAccess.getXAdditiveExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	      								current);
            	      						
            	    }

            	    }

            	    // InternalCheckCfg.g:1838:6: ( ( ruleOpAdd ) )
            	    // InternalCheckCfg.g:1839:7: ( ruleOpAdd )
            	    {
            	    // InternalCheckCfg.g:1839:7: ( ruleOpAdd )
            	    // InternalCheckCfg.g:1840:8: ruleOpAdd
            	    {
            	    if ( state.backtracking==0 ) {

            	      								if (current==null) {
            	      									current = createModelElement(grammarAccess.getXAdditiveExpressionRule());
            	      								}
            	      							
            	    }
            	    if ( state.backtracking==0 ) {

            	      								newCompositeNode(grammarAccess.getXAdditiveExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0());
            	      							
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpAdd();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      								afterParserOrEnumRuleCall();
            	      							
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:1856:4: ( (lv_rightOperand_3_0= ruleXMultiplicativeExpression ) )
            	    // InternalCheckCfg.g:1857:5: (lv_rightOperand_3_0= ruleXMultiplicativeExpression )
            	    {
            	    // InternalCheckCfg.g:1857:5: (lv_rightOperand_3_0= ruleXMultiplicativeExpression )
            	    // InternalCheckCfg.g:1858:6: lv_rightOperand_3_0= ruleXMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXAdditiveExpressionAccess().getRightOperandXMultiplicativeExpressionParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_34);
            	    lv_rightOperand_3_0=ruleXMultiplicativeExpression();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXAdditiveExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"rightOperand",
            	      							lv_rightOperand_3_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XMultiplicativeExpression");
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


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXAdditiveExpression"


    // $ANTLR start "entryRuleOpAdd"
    // InternalCheckCfg.g:1880:1: entryRuleOpAdd returns [String current=null] : iv_ruleOpAdd= ruleOpAdd EOF ;
    public final String entryRuleOpAdd() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpAdd = null;


        try {
            // InternalCheckCfg.g:1880:45: (iv_ruleOpAdd= ruleOpAdd EOF )
            // InternalCheckCfg.g:1881:2: iv_ruleOpAdd= ruleOpAdd EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpAddRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpAdd=ruleOpAdd();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpAdd.getText(); 
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
    // $ANTLR end "entryRuleOpAdd"


    // $ANTLR start "ruleOpAdd"
    // InternalCheckCfg.g:1887:1: ruleOpAdd returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '+' | kw= '-' ) ;
    public final AntlrDatatypeRuleToken ruleOpAdd() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:1893:2: ( (kw= '+' | kw= '-' ) )
            // InternalCheckCfg.g:1894:2: (kw= '+' | kw= '-' )
            {
            // InternalCheckCfg.g:1894:2: (kw= '+' | kw= '-' )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==47) ) {
                alt31=1;
            }
            else if ( (LA31_0==48) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // InternalCheckCfg.g:1895:3: kw= '+'
                    {
                    kw=(Token)match(input,47,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpAddAccess().getPlusSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:1901:3: kw= '-'
                    {
                    kw=(Token)match(input,48,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpAddAccess().getHyphenMinusKeyword_1());
                      		
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
    // $ANTLR end "ruleOpAdd"


    // $ANTLR start "entryRuleXMultiplicativeExpression"
    // InternalCheckCfg.g:1910:1: entryRuleXMultiplicativeExpression returns [EObject current=null] : iv_ruleXMultiplicativeExpression= ruleXMultiplicativeExpression EOF ;
    public final EObject entryRuleXMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXMultiplicativeExpression = null;


        try {
            // InternalCheckCfg.g:1910:66: (iv_ruleXMultiplicativeExpression= ruleXMultiplicativeExpression EOF )
            // InternalCheckCfg.g:1911:2: iv_ruleXMultiplicativeExpression= ruleXMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXMultiplicativeExpression=ruleXMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXMultiplicativeExpression; 
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
    // $ANTLR end "entryRuleXMultiplicativeExpression"


    // $ANTLR start "ruleXMultiplicativeExpression"
    // InternalCheckCfg.g:1917:1: ruleXMultiplicativeExpression returns [EObject current=null] : (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* ) ;
    public final EObject ruleXMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XUnaryOperation_0 = null;

        EObject lv_rightOperand_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:1923:2: ( (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* ) )
            // InternalCheckCfg.g:1924:2: (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* )
            {
            // InternalCheckCfg.g:1924:2: (this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )* )
            // InternalCheckCfg.g:1925:3: this_XUnaryOperation_0= ruleXUnaryOperation ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXMultiplicativeExpressionAccess().getXUnaryOperationParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_35);
            this_XUnaryOperation_0=ruleXUnaryOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XUnaryOperation_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:1933:3: ( ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) ) )*
            loop32:
            do {
                int alt32=2;
                switch ( input.LA(1) ) {
                case 49:
                    {
                    int LA32_2 = input.LA(2);

                    if ( (synpred11_InternalCheckCfg()) ) {
                        alt32=1;
                    }


                    }
                    break;
                case 50:
                    {
                    int LA32_3 = input.LA(2);

                    if ( (synpred11_InternalCheckCfg()) ) {
                        alt32=1;
                    }


                    }
                    break;
                case 51:
                    {
                    int LA32_4 = input.LA(2);

                    if ( (synpred11_InternalCheckCfg()) ) {
                        alt32=1;
                    }


                    }
                    break;
                case 52:
                    {
                    int LA32_5 = input.LA(2);

                    if ( (synpred11_InternalCheckCfg()) ) {
                        alt32=1;
                    }


                    }
                    break;

                }

                switch (alt32) {
            	case 1 :
            	    // InternalCheckCfg.g:1934:4: ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) ) ( (lv_rightOperand_3_0= ruleXUnaryOperation ) )
            	    {
            	    // InternalCheckCfg.g:1934:4: ( ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) ) )
            	    // InternalCheckCfg.g:1935:5: ( ( () ( ( ruleOpMulti ) ) ) )=> ( () ( ( ruleOpMulti ) ) )
            	    {
            	    // InternalCheckCfg.g:1945:5: ( () ( ( ruleOpMulti ) ) )
            	    // InternalCheckCfg.g:1946:6: () ( ( ruleOpMulti ) )
            	    {
            	    // InternalCheckCfg.g:1946:6: ()
            	    // InternalCheckCfg.g:1947:7: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      							current = forceCreateModelElementAndSet(
            	      								grammarAccess.getXMultiplicativeExpressionAccess().getXBinaryOperationLeftOperandAction_1_0_0_0(),
            	      								current);
            	      						
            	    }

            	    }

            	    // InternalCheckCfg.g:1953:6: ( ( ruleOpMulti ) )
            	    // InternalCheckCfg.g:1954:7: ( ruleOpMulti )
            	    {
            	    // InternalCheckCfg.g:1954:7: ( ruleOpMulti )
            	    // InternalCheckCfg.g:1955:8: ruleOpMulti
            	    {
            	    if ( state.backtracking==0 ) {

            	      								if (current==null) {
            	      									current = createModelElement(grammarAccess.getXMultiplicativeExpressionRule());
            	      								}
            	      							
            	    }
            	    if ( state.backtracking==0 ) {

            	      								newCompositeNode(grammarAccess.getXMultiplicativeExpressionAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_1_0());
            	      							
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpMulti();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      								afterParserOrEnumRuleCall();
            	      							
            	    }

            	    }


            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:1971:4: ( (lv_rightOperand_3_0= ruleXUnaryOperation ) )
            	    // InternalCheckCfg.g:1972:5: (lv_rightOperand_3_0= ruleXUnaryOperation )
            	    {
            	    // InternalCheckCfg.g:1972:5: (lv_rightOperand_3_0= ruleXUnaryOperation )
            	    // InternalCheckCfg.g:1973:6: lv_rightOperand_3_0= ruleXUnaryOperation
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXMultiplicativeExpressionAccess().getRightOperandXUnaryOperationParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_35);
            	    lv_rightOperand_3_0=ruleXUnaryOperation();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXMultiplicativeExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"rightOperand",
            	      							lv_rightOperand_3_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XUnaryOperation");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
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
    // $ANTLR end "ruleXMultiplicativeExpression"


    // $ANTLR start "entryRuleOpMulti"
    // InternalCheckCfg.g:1995:1: entryRuleOpMulti returns [String current=null] : iv_ruleOpMulti= ruleOpMulti EOF ;
    public final String entryRuleOpMulti() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpMulti = null;


        try {
            // InternalCheckCfg.g:1995:47: (iv_ruleOpMulti= ruleOpMulti EOF )
            // InternalCheckCfg.g:1996:2: iv_ruleOpMulti= ruleOpMulti EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpMultiRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpMulti=ruleOpMulti();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpMulti.getText(); 
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
    // $ANTLR end "entryRuleOpMulti"


    // $ANTLR start "ruleOpMulti"
    // InternalCheckCfg.g:2002:1: ruleOpMulti returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '*' | kw= '**' | kw= '/' | kw= '%' ) ;
    public final AntlrDatatypeRuleToken ruleOpMulti() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:2008:2: ( (kw= '*' | kw= '**' | kw= '/' | kw= '%' ) )
            // InternalCheckCfg.g:2009:2: (kw= '*' | kw= '**' | kw= '/' | kw= '%' )
            {
            // InternalCheckCfg.g:2009:2: (kw= '*' | kw= '**' | kw= '/' | kw= '%' )
            int alt33=4;
            switch ( input.LA(1) ) {
            case 49:
                {
                alt33=1;
                }
                break;
            case 50:
                {
                alt33=2;
                }
                break;
            case 51:
                {
                alt33=3;
                }
                break;
            case 52:
                {
                alt33=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // InternalCheckCfg.g:2010:3: kw= '*'
                    {
                    kw=(Token)match(input,49,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAccess().getAsteriskKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:2016:3: kw= '**'
                    {
                    kw=(Token)match(input,50,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAccess().getAsteriskAsteriskKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:2022:3: kw= '/'
                    {
                    kw=(Token)match(input,51,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAccess().getSolidusKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:2028:3: kw= '%'
                    {
                    kw=(Token)match(input,52,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpMultiAccess().getPercentSignKeyword_3());
                      		
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
    // $ANTLR end "ruleOpMulti"


    // $ANTLR start "entryRuleXUnaryOperation"
    // InternalCheckCfg.g:2037:1: entryRuleXUnaryOperation returns [EObject current=null] : iv_ruleXUnaryOperation= ruleXUnaryOperation EOF ;
    public final EObject entryRuleXUnaryOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXUnaryOperation = null;


        try {
            // InternalCheckCfg.g:2037:56: (iv_ruleXUnaryOperation= ruleXUnaryOperation EOF )
            // InternalCheckCfg.g:2038:2: iv_ruleXUnaryOperation= ruleXUnaryOperation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXUnaryOperationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXUnaryOperation=ruleXUnaryOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXUnaryOperation; 
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
    // $ANTLR end "entryRuleXUnaryOperation"


    // $ANTLR start "ruleXUnaryOperation"
    // InternalCheckCfg.g:2044:1: ruleXUnaryOperation returns [EObject current=null] : ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXUnaryOperation ) ) ) | this_XCastedExpression_3= ruleXCastedExpression ) ;
    public final EObject ruleXUnaryOperation() throws RecognitionException {
        EObject current = null;

        EObject lv_operand_2_0 = null;

        EObject this_XCastedExpression_3 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:2050:2: ( ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXUnaryOperation ) ) ) | this_XCastedExpression_3= ruleXCastedExpression ) )
            // InternalCheckCfg.g:2051:2: ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXUnaryOperation ) ) ) | this_XCastedExpression_3= ruleXCastedExpression )
            {
            // InternalCheckCfg.g:2051:2: ( ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXUnaryOperation ) ) ) | this_XCastedExpression_3= ruleXCastedExpression )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( ((LA34_0>=47 && LA34_0<=48)||LA34_0==53) ) {
                alt34=1;
            }
            else if ( ((LA34_0>=RULE_STRING && LA34_0<=RULE_ID)||LA34_0==15||LA34_0==17||LA34_0==19||(LA34_0>=23 && LA34_0<=24)||LA34_0==31||LA34_0==62||LA34_0==64||(LA34_0>=68 && LA34_0<=69)||(LA34_0>=72 && LA34_0<=84)||LA34_0==86) ) {
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
                    // InternalCheckCfg.g:2052:3: ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXUnaryOperation ) ) )
                    {
                    // InternalCheckCfg.g:2052:3: ( () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXUnaryOperation ) ) )
                    // InternalCheckCfg.g:2053:4: () ( ( ruleOpUnary ) ) ( (lv_operand_2_0= ruleXUnaryOperation ) )
                    {
                    // InternalCheckCfg.g:2053:4: ()
                    // InternalCheckCfg.g:2054:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElement(
                      						grammarAccess.getXUnaryOperationAccess().getXUnaryOperationAction_0_0(),
                      						current);
                      				
                    }

                    }

                    // InternalCheckCfg.g:2060:4: ( ( ruleOpUnary ) )
                    // InternalCheckCfg.g:2061:5: ( ruleOpUnary )
                    {
                    // InternalCheckCfg.g:2061:5: ( ruleOpUnary )
                    // InternalCheckCfg.g:2062:6: ruleOpUnary
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXUnaryOperationRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXUnaryOperationAccess().getFeatureJvmIdentifiableElementCrossReference_0_1_0());
                      					
                    }
                    pushFollow(FOLLOW_20);
                    ruleOpUnary();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:2076:4: ( (lv_operand_2_0= ruleXUnaryOperation ) )
                    // InternalCheckCfg.g:2077:5: (lv_operand_2_0= ruleXUnaryOperation )
                    {
                    // InternalCheckCfg.g:2077:5: (lv_operand_2_0= ruleXUnaryOperation )
                    // InternalCheckCfg.g:2078:6: lv_operand_2_0= ruleXUnaryOperation
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXUnaryOperationAccess().getOperandXUnaryOperationParserRuleCall_0_2_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_operand_2_0=ruleXUnaryOperation();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXUnaryOperationRule());
                      						}
                      						set(
                      							current,
                      							"operand",
                      							lv_operand_2_0,
                      							"org.eclipse.xtext.xbase.Xbase.XUnaryOperation");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:2097:3: this_XCastedExpression_3= ruleXCastedExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXUnaryOperationAccess().getXCastedExpressionParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XCastedExpression_3=ruleXCastedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XCastedExpression_3;
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
    // $ANTLR end "ruleXUnaryOperation"


    // $ANTLR start "entryRuleOpUnary"
    // InternalCheckCfg.g:2109:1: entryRuleOpUnary returns [String current=null] : iv_ruleOpUnary= ruleOpUnary EOF ;
    public final String entryRuleOpUnary() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpUnary = null;


        try {
            // InternalCheckCfg.g:2109:47: (iv_ruleOpUnary= ruleOpUnary EOF )
            // InternalCheckCfg.g:2110:2: iv_ruleOpUnary= ruleOpUnary EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpUnaryRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpUnary=ruleOpUnary();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpUnary.getText(); 
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
    // $ANTLR end "entryRuleOpUnary"


    // $ANTLR start "ruleOpUnary"
    // InternalCheckCfg.g:2116:1: ruleOpUnary returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '!' | kw= '-' | kw= '+' ) ;
    public final AntlrDatatypeRuleToken ruleOpUnary() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:2122:2: ( (kw= '!' | kw= '-' | kw= '+' ) )
            // InternalCheckCfg.g:2123:2: (kw= '!' | kw= '-' | kw= '+' )
            {
            // InternalCheckCfg.g:2123:2: (kw= '!' | kw= '-' | kw= '+' )
            int alt35=3;
            switch ( input.LA(1) ) {
            case 53:
                {
                alt35=1;
                }
                break;
            case 48:
                {
                alt35=2;
                }
                break;
            case 47:
                {
                alt35=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;
            }

            switch (alt35) {
                case 1 :
                    // InternalCheckCfg.g:2124:3: kw= '!'
                    {
                    kw=(Token)match(input,53,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpUnaryAccess().getExclamationMarkKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:2130:3: kw= '-'
                    {
                    kw=(Token)match(input,48,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpUnaryAccess().getHyphenMinusKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:2136:3: kw= '+'
                    {
                    kw=(Token)match(input,47,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpUnaryAccess().getPlusSignKeyword_2());
                      		
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
    // $ANTLR end "ruleOpUnary"


    // $ANTLR start "entryRuleXCastedExpression"
    // InternalCheckCfg.g:2145:1: entryRuleXCastedExpression returns [EObject current=null] : iv_ruleXCastedExpression= ruleXCastedExpression EOF ;
    public final EObject entryRuleXCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXCastedExpression = null;


        try {
            // InternalCheckCfg.g:2145:58: (iv_ruleXCastedExpression= ruleXCastedExpression EOF )
            // InternalCheckCfg.g:2146:2: iv_ruleXCastedExpression= ruleXCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXCastedExpression=ruleXCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXCastedExpression; 
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
    // $ANTLR end "entryRuleXCastedExpression"


    // $ANTLR start "ruleXCastedExpression"
    // InternalCheckCfg.g:2152:1: ruleXCastedExpression returns [EObject current=null] : (this_XPostfixOperation_0= ruleXPostfixOperation ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* ) ;
    public final EObject ruleXCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XPostfixOperation_0 = null;

        EObject lv_type_3_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:2158:2: ( (this_XPostfixOperation_0= ruleXPostfixOperation ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* ) )
            // InternalCheckCfg.g:2159:2: (this_XPostfixOperation_0= ruleXPostfixOperation ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* )
            {
            // InternalCheckCfg.g:2159:2: (this_XPostfixOperation_0= ruleXPostfixOperation ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )* )
            // InternalCheckCfg.g:2160:3: this_XPostfixOperation_0= ruleXPostfixOperation ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXCastedExpressionAccess().getXPostfixOperationParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_36);
            this_XPostfixOperation_0=ruleXPostfixOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XPostfixOperation_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:2168:3: ( ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==54) ) {
                    int LA36_2 = input.LA(2);

                    if ( (synpred12_InternalCheckCfg()) ) {
                        alt36=1;
                    }


                }


                switch (alt36) {
            	case 1 :
            	    // InternalCheckCfg.g:2169:4: ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) ) ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    {
            	    // InternalCheckCfg.g:2169:4: ( ( ( () 'as' ) )=> ( () otherlv_2= 'as' ) )
            	    // InternalCheckCfg.g:2170:5: ( ( () 'as' ) )=> ( () otherlv_2= 'as' )
            	    {
            	    // InternalCheckCfg.g:2176:5: ( () otherlv_2= 'as' )
            	    // InternalCheckCfg.g:2177:6: () otherlv_2= 'as'
            	    {
            	    // InternalCheckCfg.g:2177:6: ()
            	    // InternalCheckCfg.g:2178:7: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      							current = forceCreateModelElementAndSet(
            	      								grammarAccess.getXCastedExpressionAccess().getXCastedExpressionTargetAction_1_0_0_0(),
            	      								current);
            	      						
            	    }

            	    }

            	    otherlv_2=(Token)match(input,54,FOLLOW_29); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						newLeafNode(otherlv_2, grammarAccess.getXCastedExpressionAccess().getAsKeyword_1_0_0_1());
            	      					
            	    }

            	    }


            	    }

            	    // InternalCheckCfg.g:2190:4: ( (lv_type_3_0= ruleJvmTypeReference ) )
            	    // InternalCheckCfg.g:2191:5: (lv_type_3_0= ruleJvmTypeReference )
            	    {
            	    // InternalCheckCfg.g:2191:5: (lv_type_3_0= ruleJvmTypeReference )
            	    // InternalCheckCfg.g:2192:6: lv_type_3_0= ruleJvmTypeReference
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXCastedExpressionAccess().getTypeJvmTypeReferenceParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_36);
            	    lv_type_3_0=ruleJvmTypeReference();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXCastedExpressionRule());
            	      						}
            	      						set(
            	      							current,
            	      							"type",
            	      							lv_type_3_0,
            	      							"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
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
    // $ANTLR end "ruleXCastedExpression"


    // $ANTLR start "entryRuleXPostfixOperation"
    // InternalCheckCfg.g:2214:1: entryRuleXPostfixOperation returns [EObject current=null] : iv_ruleXPostfixOperation= ruleXPostfixOperation EOF ;
    public final EObject entryRuleXPostfixOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPostfixOperation = null;


        try {
            // InternalCheckCfg.g:2214:58: (iv_ruleXPostfixOperation= ruleXPostfixOperation EOF )
            // InternalCheckCfg.g:2215:2: iv_ruleXPostfixOperation= ruleXPostfixOperation EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXPostfixOperationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXPostfixOperation=ruleXPostfixOperation();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXPostfixOperation; 
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
    // $ANTLR end "entryRuleXPostfixOperation"


    // $ANTLR start "ruleXPostfixOperation"
    // InternalCheckCfg.g:2221:1: ruleXPostfixOperation returns [EObject current=null] : (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( () ( ( ruleOpPostfix ) ) ) )=> ( () ( ( ruleOpPostfix ) ) ) )? ) ;
    public final EObject ruleXPostfixOperation() throws RecognitionException {
        EObject current = null;

        EObject this_XMemberFeatureCall_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:2227:2: ( (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( () ( ( ruleOpPostfix ) ) ) )=> ( () ( ( ruleOpPostfix ) ) ) )? ) )
            // InternalCheckCfg.g:2228:2: (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( () ( ( ruleOpPostfix ) ) ) )=> ( () ( ( ruleOpPostfix ) ) ) )? )
            {
            // InternalCheckCfg.g:2228:2: (this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( () ( ( ruleOpPostfix ) ) ) )=> ( () ( ( ruleOpPostfix ) ) ) )? )
            // InternalCheckCfg.g:2229:3: this_XMemberFeatureCall_0= ruleXMemberFeatureCall ( ( ( () ( ( ruleOpPostfix ) ) ) )=> ( () ( ( ruleOpPostfix ) ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXPostfixOperationAccess().getXMemberFeatureCallParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_37);
            this_XMemberFeatureCall_0=ruleXMemberFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XMemberFeatureCall_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:2237:3: ( ( ( () ( ( ruleOpPostfix ) ) ) )=> ( () ( ( ruleOpPostfix ) ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==55) ) {
                int LA37_1 = input.LA(2);

                if ( (synpred13_InternalCheckCfg()) ) {
                    alt37=1;
                }
            }
            else if ( (LA37_0==56) ) {
                int LA37_2 = input.LA(2);

                if ( (synpred13_InternalCheckCfg()) ) {
                    alt37=1;
                }
            }
            switch (alt37) {
                case 1 :
                    // InternalCheckCfg.g:2238:4: ( ( () ( ( ruleOpPostfix ) ) ) )=> ( () ( ( ruleOpPostfix ) ) )
                    {
                    // InternalCheckCfg.g:2248:4: ( () ( ( ruleOpPostfix ) ) )
                    // InternalCheckCfg.g:2249:5: () ( ( ruleOpPostfix ) )
                    {
                    // InternalCheckCfg.g:2249:5: ()
                    // InternalCheckCfg.g:2250:6: 
                    {
                    if ( state.backtracking==0 ) {

                      						current = forceCreateModelElementAndSet(
                      							grammarAccess.getXPostfixOperationAccess().getXPostfixOperationOperandAction_1_0_0(),
                      							current);
                      					
                    }

                    }

                    // InternalCheckCfg.g:2256:5: ( ( ruleOpPostfix ) )
                    // InternalCheckCfg.g:2257:6: ( ruleOpPostfix )
                    {
                    // InternalCheckCfg.g:2257:6: ( ruleOpPostfix )
                    // InternalCheckCfg.g:2258:7: ruleOpPostfix
                    {
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElement(grammarAccess.getXPostfixOperationRule());
                      							}
                      						
                    }
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getXPostfixOperationAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_1_0());
                      						
                    }
                    pushFollow(FOLLOW_2);
                    ruleOpPostfix();

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


            }


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXPostfixOperation"


    // $ANTLR start "entryRuleOpPostfix"
    // InternalCheckCfg.g:2278:1: entryRuleOpPostfix returns [String current=null] : iv_ruleOpPostfix= ruleOpPostfix EOF ;
    public final String entryRuleOpPostfix() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOpPostfix = null;


        try {
            // InternalCheckCfg.g:2278:49: (iv_ruleOpPostfix= ruleOpPostfix EOF )
            // InternalCheckCfg.g:2279:2: iv_ruleOpPostfix= ruleOpPostfix EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOpPostfixRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleOpPostfix=ruleOpPostfix();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOpPostfix.getText(); 
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
    // $ANTLR end "entryRuleOpPostfix"


    // $ANTLR start "ruleOpPostfix"
    // InternalCheckCfg.g:2285:1: ruleOpPostfix returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '++' | kw= '--' ) ;
    public final AntlrDatatypeRuleToken ruleOpPostfix() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:2291:2: ( (kw= '++' | kw= '--' ) )
            // InternalCheckCfg.g:2292:2: (kw= '++' | kw= '--' )
            {
            // InternalCheckCfg.g:2292:2: (kw= '++' | kw= '--' )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==55) ) {
                alt38=1;
            }
            else if ( (LA38_0==56) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // InternalCheckCfg.g:2293:3: kw= '++'
                    {
                    kw=(Token)match(input,55,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpPostfixAccess().getPlusSignPlusSignKeyword_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:2299:3: kw= '--'
                    {
                    kw=(Token)match(input,56,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getOpPostfixAccess().getHyphenMinusHyphenMinusKeyword_1());
                      		
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
    // $ANTLR end "ruleOpPostfix"


    // $ANTLR start "entryRuleXMemberFeatureCall"
    // InternalCheckCfg.g:2308:1: entryRuleXMemberFeatureCall returns [EObject current=null] : iv_ruleXMemberFeatureCall= ruleXMemberFeatureCall EOF ;
    public final EObject entryRuleXMemberFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXMemberFeatureCall = null;


        try {
            // InternalCheckCfg.g:2308:59: (iv_ruleXMemberFeatureCall= ruleXMemberFeatureCall EOF )
            // InternalCheckCfg.g:2309:2: iv_ruleXMemberFeatureCall= ruleXMemberFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXMemberFeatureCallRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXMemberFeatureCall=ruleXMemberFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXMemberFeatureCall; 
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
    // $ANTLR end "entryRuleXMemberFeatureCall"


    // $ANTLR start "ruleXMemberFeatureCall"
    // InternalCheckCfg.g:2315:1: ruleXMemberFeatureCall returns [EObject current=null] : (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? ) )* ) ;
    public final EObject ruleXMemberFeatureCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token lv_explicitStatic_3_0=null;
        Token otherlv_8=null;
        Token lv_nullSafe_9_0=null;
        Token lv_explicitStatic_10_0=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token lv_explicitOperationCall_17_0=null;
        Token otherlv_20=null;
        Token otherlv_22=null;
        EObject this_XPrimaryExpression_0 = null;

        EObject lv_value_6_0 = null;

        EObject lv_typeArguments_12_0 = null;

        EObject lv_typeArguments_14_0 = null;

        EObject lv_memberCallArguments_18_0 = null;

        EObject lv_memberCallArguments_19_0 = null;

        EObject lv_memberCallArguments_21_0 = null;

        EObject lv_memberCallArguments_23_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:2321:2: ( (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? ) )* ) )
            // InternalCheckCfg.g:2322:2: (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? ) )* )
            {
            // InternalCheckCfg.g:2322:2: (this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? ) )* )
            // InternalCheckCfg.g:2323:3: this_XPrimaryExpression_0= ruleXPrimaryExpression ( ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getXPrimaryExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_38);
            this_XPrimaryExpression_0=ruleXPrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XPrimaryExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:2331:3: ( ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) ) | ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? ) )*
            loop47:
            do {
                int alt47=3;
                switch ( input.LA(1) ) {
                case 57:
                    {
                    int LA47_2 = input.LA(2);

                    if ( (synpred14_InternalCheckCfg()) ) {
                        alt47=1;
                    }
                    else if ( (synpred15_InternalCheckCfg()) ) {
                        alt47=2;
                    }


                    }
                    break;
                case 58:
                    {
                    int LA47_3 = input.LA(2);

                    if ( (synpred14_InternalCheckCfg()) ) {
                        alt47=1;
                    }
                    else if ( (synpred15_InternalCheckCfg()) ) {
                        alt47=2;
                    }


                    }
                    break;
                case 59:
                    {
                    int LA47_4 = input.LA(2);

                    if ( (synpred15_InternalCheckCfg()) ) {
                        alt47=2;
                    }


                    }
                    break;

                }

                switch (alt47) {
            	case 1 :
            	    // InternalCheckCfg.g:2332:4: ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) )
            	    {
            	    // InternalCheckCfg.g:2332:4: ( ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) ) )
            	    // InternalCheckCfg.g:2333:5: ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) ) ( (lv_value_6_0= ruleXAssignment ) )
            	    {
            	    // InternalCheckCfg.g:2333:5: ( ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )
            	    // InternalCheckCfg.g:2334:6: ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )=> ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign )
            	    {
            	    // InternalCheckCfg.g:2354:6: ( () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign )
            	    // InternalCheckCfg.g:2355:7: () (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign
            	    {
            	    // InternalCheckCfg.g:2355:7: ()
            	    // InternalCheckCfg.g:2356:8: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      								current = forceCreateModelElementAndSet(
            	      									grammarAccess.getXMemberFeatureCallAccess().getXAssignmentAssignableAction_1_0_0_0_0(),
            	      									current);
            	      							
            	    }

            	    }

            	    // InternalCheckCfg.g:2362:7: (otherlv_2= '.' | ( (lv_explicitStatic_3_0= '::' ) ) )
            	    int alt39=2;
            	    int LA39_0 = input.LA(1);

            	    if ( (LA39_0==57) ) {
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
            	            // InternalCheckCfg.g:2363:8: otherlv_2= '.'
            	            {
            	            otherlv_2=(Token)match(input,57,FOLLOW_39); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								newLeafNode(otherlv_2, grammarAccess.getXMemberFeatureCallAccess().getFullStopKeyword_1_0_0_0_1_0());
            	              							
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalCheckCfg.g:2368:8: ( (lv_explicitStatic_3_0= '::' ) )
            	            {
            	            // InternalCheckCfg.g:2368:8: ( (lv_explicitStatic_3_0= '::' ) )
            	            // InternalCheckCfg.g:2369:9: (lv_explicitStatic_3_0= '::' )
            	            {
            	            // InternalCheckCfg.g:2369:9: (lv_explicitStatic_3_0= '::' )
            	            // InternalCheckCfg.g:2370:10: lv_explicitStatic_3_0= '::'
            	            {
            	            lv_explicitStatic_3_0=(Token)match(input,58,FOLLOW_39); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              										newLeafNode(lv_explicitStatic_3_0, grammarAccess.getXMemberFeatureCallAccess().getExplicitStaticColonColonKeyword_1_0_0_0_1_1_0());
            	              									
            	            }
            	            if ( state.backtracking==0 ) {

            	              										if (current==null) {
            	              											current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	              										}
            	              										setWithLastConsumed(current, "explicitStatic", lv_explicitStatic_3_0 != null, "::");
            	              									
            	            }

            	            }


            	            }


            	            }
            	            break;

            	    }

            	    // InternalCheckCfg.g:2383:7: ( ( ruleFeatureCallID ) )
            	    // InternalCheckCfg.g:2384:8: ( ruleFeatureCallID )
            	    {
            	    // InternalCheckCfg.g:2384:8: ( ruleFeatureCallID )
            	    // InternalCheckCfg.g:2385:9: ruleFeatureCallID
            	    {
            	    if ( state.backtracking==0 ) {

            	      									if (current==null) {
            	      										current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	      									}
            	      								
            	    }
            	    if ( state.backtracking==0 ) {

            	      									newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getFeatureJvmIdentifiableElementCrossReference_1_0_0_0_2_0());
            	      								
            	    }
            	    pushFollow(FOLLOW_14);
            	    ruleFeatureCallID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      									afterParserOrEnumRuleCall();
            	      								
            	    }

            	    }


            	    }

            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getOpSingleAssignParserRuleCall_1_0_0_0_3());
            	      						
            	    }
            	    pushFollow(FOLLOW_20);
            	    ruleOpSingleAssign();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      							afterParserOrEnumRuleCall();
            	      						
            	    }

            	    }


            	    }

            	    // InternalCheckCfg.g:2408:5: ( (lv_value_6_0= ruleXAssignment ) )
            	    // InternalCheckCfg.g:2409:6: (lv_value_6_0= ruleXAssignment )
            	    {
            	    // InternalCheckCfg.g:2409:6: (lv_value_6_0= ruleXAssignment )
            	    // InternalCheckCfg.g:2410:7: lv_value_6_0= ruleXAssignment
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getValueXAssignmentParserRuleCall_1_0_1_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_38);
            	    lv_value_6_0=ruleXAssignment();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      							if (current==null) {
            	      								current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	      							}
            	      							set(
            	      								current,
            	      								"value",
            	      								lv_value_6_0,
            	      								"org.eclipse.xtext.xbase.Xbase.XAssignment");
            	      							afterParserOrEnumRuleCall();
            	      						
            	    }

            	    }


            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCheckCfg.g:2429:4: ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? )
            	    {
            	    // InternalCheckCfg.g:2429:4: ( ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )? )
            	    // InternalCheckCfg.g:2430:5: ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) ) (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )? ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )?
            	    {
            	    // InternalCheckCfg.g:2430:5: ( ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) ) )
            	    // InternalCheckCfg.g:2431:6: ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )=> ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) )
            	    {
            	    // InternalCheckCfg.g:2451:6: ( () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) ) )
            	    // InternalCheckCfg.g:2452:7: () (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) )
            	    {
            	    // InternalCheckCfg.g:2452:7: ()
            	    // InternalCheckCfg.g:2453:8: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      								current = forceCreateModelElementAndSet(
            	      									grammarAccess.getXMemberFeatureCallAccess().getXMemberFeatureCallMemberCallTargetAction_1_1_0_0_0(),
            	      									current);
            	      							
            	    }

            	    }

            	    // InternalCheckCfg.g:2459:7: (otherlv_8= '.' | ( (lv_nullSafe_9_0= '?.' ) ) | ( (lv_explicitStatic_10_0= '::' ) ) )
            	    int alt40=3;
            	    switch ( input.LA(1) ) {
            	    case 57:
            	        {
            	        alt40=1;
            	        }
            	        break;
            	    case 59:
            	        {
            	        alt40=2;
            	        }
            	        break;
            	    case 58:
            	        {
            	        alt40=3;
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
            	            // InternalCheckCfg.g:2460:8: otherlv_8= '.'
            	            {
            	            otherlv_8=(Token)match(input,57,FOLLOW_40); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								newLeafNode(otherlv_8, grammarAccess.getXMemberFeatureCallAccess().getFullStopKeyword_1_1_0_0_1_0());
            	              							
            	            }

            	            }
            	            break;
            	        case 2 :
            	            // InternalCheckCfg.g:2465:8: ( (lv_nullSafe_9_0= '?.' ) )
            	            {
            	            // InternalCheckCfg.g:2465:8: ( (lv_nullSafe_9_0= '?.' ) )
            	            // InternalCheckCfg.g:2466:9: (lv_nullSafe_9_0= '?.' )
            	            {
            	            // InternalCheckCfg.g:2466:9: (lv_nullSafe_9_0= '?.' )
            	            // InternalCheckCfg.g:2467:10: lv_nullSafe_9_0= '?.'
            	            {
            	            lv_nullSafe_9_0=(Token)match(input,59,FOLLOW_40); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              										newLeafNode(lv_nullSafe_9_0, grammarAccess.getXMemberFeatureCallAccess().getNullSafeQuestionMarkFullStopKeyword_1_1_0_0_1_1_0());
            	              									
            	            }
            	            if ( state.backtracking==0 ) {

            	              										if (current==null) {
            	              											current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	              										}
            	              										setWithLastConsumed(current, "nullSafe", lv_nullSafe_9_0 != null, "?.");
            	              									
            	            }

            	            }


            	            }


            	            }
            	            break;
            	        case 3 :
            	            // InternalCheckCfg.g:2480:8: ( (lv_explicitStatic_10_0= '::' ) )
            	            {
            	            // InternalCheckCfg.g:2480:8: ( (lv_explicitStatic_10_0= '::' ) )
            	            // InternalCheckCfg.g:2481:9: (lv_explicitStatic_10_0= '::' )
            	            {
            	            // InternalCheckCfg.g:2481:9: (lv_explicitStatic_10_0= '::' )
            	            // InternalCheckCfg.g:2482:10: lv_explicitStatic_10_0= '::'
            	            {
            	            lv_explicitStatic_10_0=(Token)match(input,58,FOLLOW_40); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              										newLeafNode(lv_explicitStatic_10_0, grammarAccess.getXMemberFeatureCallAccess().getExplicitStaticColonColonKeyword_1_1_0_0_1_2_0());
            	              									
            	            }
            	            if ( state.backtracking==0 ) {

            	              										if (current==null) {
            	              											current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	              										}
            	              										setWithLastConsumed(current, "explicitStatic", lv_explicitStatic_10_0 != null, "::");
            	              									
            	            }

            	            }


            	            }


            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalCheckCfg.g:2497:5: (otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>' )?
            	    int alt42=2;
            	    int LA42_0 = input.LA(1);

            	    if ( (LA42_0==31) ) {
            	        alt42=1;
            	    }
            	    switch (alt42) {
            	        case 1 :
            	            // InternalCheckCfg.g:2498:6: otherlv_11= '<' ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) ) (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )* otherlv_15= '>'
            	            {
            	            otherlv_11=(Token)match(input,31,FOLLOW_41); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_11, grammarAccess.getXMemberFeatureCallAccess().getLessThanSignKeyword_1_1_1_0());
            	              					
            	            }
            	            // InternalCheckCfg.g:2502:6: ( (lv_typeArguments_12_0= ruleJvmArgumentTypeReference ) )
            	            // InternalCheckCfg.g:2503:7: (lv_typeArguments_12_0= ruleJvmArgumentTypeReference )
            	            {
            	            // InternalCheckCfg.g:2503:7: (lv_typeArguments_12_0= ruleJvmArgumentTypeReference )
            	            // InternalCheckCfg.g:2504:8: lv_typeArguments_12_0= ruleJvmArgumentTypeReference
            	            {
            	            if ( state.backtracking==0 ) {

            	              								newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_1_1_0());
            	              							
            	            }
            	            pushFollow(FOLLOW_42);
            	            lv_typeArguments_12_0=ruleJvmArgumentTypeReference();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								if (current==null) {
            	              									current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	              								}
            	              								add(
            	              									current,
            	              									"typeArguments",
            	              									lv_typeArguments_12_0,
            	              									"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
            	              								afterParserOrEnumRuleCall();
            	              							
            	            }

            	            }


            	            }

            	            // InternalCheckCfg.g:2521:6: (otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) ) )*
            	            loop41:
            	            do {
            	                int alt41=2;
            	                int LA41_0 = input.LA(1);

            	                if ( (LA41_0==20) ) {
            	                    alt41=1;
            	                }


            	                switch (alt41) {
            	            	case 1 :
            	            	    // InternalCheckCfg.g:2522:7: otherlv_13= ',' ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) )
            	            	    {
            	            	    otherlv_13=(Token)match(input,20,FOLLOW_41); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	      							newLeafNode(otherlv_13, grammarAccess.getXMemberFeatureCallAccess().getCommaKeyword_1_1_1_2_0());
            	            	      						
            	            	    }
            	            	    // InternalCheckCfg.g:2526:7: ( (lv_typeArguments_14_0= ruleJvmArgumentTypeReference ) )
            	            	    // InternalCheckCfg.g:2527:8: (lv_typeArguments_14_0= ruleJvmArgumentTypeReference )
            	            	    {
            	            	    // InternalCheckCfg.g:2527:8: (lv_typeArguments_14_0= ruleJvmArgumentTypeReference )
            	            	    // InternalCheckCfg.g:2528:9: lv_typeArguments_14_0= ruleJvmArgumentTypeReference
            	            	    {
            	            	    if ( state.backtracking==0 ) {

            	            	      									newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_1_2_1_0());
            	            	      								
            	            	    }
            	            	    pushFollow(FOLLOW_42);
            	            	    lv_typeArguments_14_0=ruleJvmArgumentTypeReference();

            	            	    state._fsp--;
            	            	    if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	      									if (current==null) {
            	            	      										current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	            	      									}
            	            	      									add(
            	            	      										current,
            	            	      										"typeArguments",
            	            	      										lv_typeArguments_14_0,
            	            	      										"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
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

            	            otherlv_15=(Token)match(input,32,FOLLOW_40); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_15, grammarAccess.getXMemberFeatureCallAccess().getGreaterThanSignKeyword_1_1_1_3());
            	              					
            	            }

            	            }
            	            break;

            	    }

            	    // InternalCheckCfg.g:2551:5: ( ( ruleIdOrSuper ) )
            	    // InternalCheckCfg.g:2552:6: ( ruleIdOrSuper )
            	    {
            	    // InternalCheckCfg.g:2552:6: ( ruleIdOrSuper )
            	    // InternalCheckCfg.g:2553:7: ruleIdOrSuper
            	    {
            	    if ( state.backtracking==0 ) {

            	      							if (current==null) {
            	      								current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	      							}
            	      						
            	    }
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getFeatureJvmIdentifiableElementCrossReference_1_1_2_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_43);
            	    ruleIdOrSuper();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      							afterParserOrEnumRuleCall();
            	      						
            	    }

            	    }


            	    }

            	    // InternalCheckCfg.g:2567:5: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )?
            	    int alt45=2;
            	    alt45 = dfa45.predict(input);
            	    switch (alt45) {
            	        case 1 :
            	            // InternalCheckCfg.g:2568:6: ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')'
            	            {
            	            // InternalCheckCfg.g:2568:6: ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) )
            	            // InternalCheckCfg.g:2569:7: ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' )
            	            {
            	            // InternalCheckCfg.g:2573:7: (lv_explicitOperationCall_17_0= '(' )
            	            // InternalCheckCfg.g:2574:8: lv_explicitOperationCall_17_0= '('
            	            {
            	            lv_explicitOperationCall_17_0=(Token)match(input,19,FOLLOW_44); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              								newLeafNode(lv_explicitOperationCall_17_0, grammarAccess.getXMemberFeatureCallAccess().getExplicitOperationCallLeftParenthesisKeyword_1_1_3_0_0());
            	              							
            	            }
            	            if ( state.backtracking==0 ) {

            	              								if (current==null) {
            	              									current = createModelElement(grammarAccess.getXMemberFeatureCallRule());
            	              								}
            	              								setWithLastConsumed(current, "explicitOperationCall", lv_explicitOperationCall_17_0 != null, "(");
            	              							
            	            }

            	            }


            	            }

            	            // InternalCheckCfg.g:2586:6: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )?
            	            int alt44=3;
            	            alt44 = dfa44.predict(input);
            	            switch (alt44) {
            	                case 1 :
            	                    // InternalCheckCfg.g:2587:7: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) )
            	                    {
            	                    // InternalCheckCfg.g:2587:7: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) )
            	                    // InternalCheckCfg.g:2588:8: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure )
            	                    {
            	                    // InternalCheckCfg.g:2613:8: (lv_memberCallArguments_18_0= ruleXShortClosure )
            	                    // InternalCheckCfg.g:2614:9: lv_memberCallArguments_18_0= ruleXShortClosure
            	                    {
            	                    if ( state.backtracking==0 ) {

            	                      									newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXShortClosureParserRuleCall_1_1_3_1_0_0());
            	                      								
            	                    }
            	                    pushFollow(FOLLOW_45);
            	                    lv_memberCallArguments_18_0=ruleXShortClosure();

            	                    state._fsp--;
            	                    if (state.failed) return current;
            	                    if ( state.backtracking==0 ) {

            	                      									if (current==null) {
            	                      										current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	                      									}
            	                      									add(
            	                      										current,
            	                      										"memberCallArguments",
            	                      										lv_memberCallArguments_18_0,
            	                      										"org.eclipse.xtext.xbase.Xbase.XShortClosure");
            	                      									afterParserOrEnumRuleCall();
            	                      								
            	                    }

            	                    }


            	                    }


            	                    }
            	                    break;
            	                case 2 :
            	                    // InternalCheckCfg.g:2632:7: ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* )
            	                    {
            	                    // InternalCheckCfg.g:2632:7: ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* )
            	                    // InternalCheckCfg.g:2633:8: ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )*
            	                    {
            	                    // InternalCheckCfg.g:2633:8: ( (lv_memberCallArguments_19_0= ruleXExpression ) )
            	                    // InternalCheckCfg.g:2634:9: (lv_memberCallArguments_19_0= ruleXExpression )
            	                    {
            	                    // InternalCheckCfg.g:2634:9: (lv_memberCallArguments_19_0= ruleXExpression )
            	                    // InternalCheckCfg.g:2635:10: lv_memberCallArguments_19_0= ruleXExpression
            	                    {
            	                    if ( state.backtracking==0 ) {

            	                      										newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXExpressionParserRuleCall_1_1_3_1_1_0_0());
            	                      									
            	                    }
            	                    pushFollow(FOLLOW_13);
            	                    lv_memberCallArguments_19_0=ruleXExpression();

            	                    state._fsp--;
            	                    if (state.failed) return current;
            	                    if ( state.backtracking==0 ) {

            	                      										if (current==null) {
            	                      											current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	                      										}
            	                      										add(
            	                      											current,
            	                      											"memberCallArguments",
            	                      											lv_memberCallArguments_19_0,
            	                      											"org.eclipse.xtext.xbase.Xbase.XExpression");
            	                      										afterParserOrEnumRuleCall();
            	                      									
            	                    }

            	                    }


            	                    }

            	                    // InternalCheckCfg.g:2652:8: (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )*
            	                    loop43:
            	                    do {
            	                        int alt43=2;
            	                        int LA43_0 = input.LA(1);

            	                        if ( (LA43_0==20) ) {
            	                            alt43=1;
            	                        }


            	                        switch (alt43) {
            	                    	case 1 :
            	                    	    // InternalCheckCfg.g:2653:9: otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) )
            	                    	    {
            	                    	    otherlv_20=(Token)match(input,20,FOLLOW_20); if (state.failed) return current;
            	                    	    if ( state.backtracking==0 ) {

            	                    	      									newLeafNode(otherlv_20, grammarAccess.getXMemberFeatureCallAccess().getCommaKeyword_1_1_3_1_1_1_0());
            	                    	      								
            	                    	    }
            	                    	    // InternalCheckCfg.g:2657:9: ( (lv_memberCallArguments_21_0= ruleXExpression ) )
            	                    	    // InternalCheckCfg.g:2658:10: (lv_memberCallArguments_21_0= ruleXExpression )
            	                    	    {
            	                    	    // InternalCheckCfg.g:2658:10: (lv_memberCallArguments_21_0= ruleXExpression )
            	                    	    // InternalCheckCfg.g:2659:11: lv_memberCallArguments_21_0= ruleXExpression
            	                    	    {
            	                    	    if ( state.backtracking==0 ) {

            	                    	      											newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXExpressionParserRuleCall_1_1_3_1_1_1_1_0());
            	                    	      										
            	                    	    }
            	                    	    pushFollow(FOLLOW_13);
            	                    	    lv_memberCallArguments_21_0=ruleXExpression();

            	                    	    state._fsp--;
            	                    	    if (state.failed) return current;
            	                    	    if ( state.backtracking==0 ) {

            	                    	      											if (current==null) {
            	                    	      												current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	                    	      											}
            	                    	      											add(
            	                    	      												current,
            	                    	      												"memberCallArguments",
            	                    	      												lv_memberCallArguments_21_0,
            	                    	      												"org.eclipse.xtext.xbase.Xbase.XExpression");
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
            	                    break;

            	            }

            	            otherlv_22=(Token)match(input,21,FOLLOW_46); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_22, grammarAccess.getXMemberFeatureCallAccess().getRightParenthesisKeyword_1_1_3_2());
            	              					
            	            }

            	            }
            	            break;

            	    }

            	    // InternalCheckCfg.g:2684:5: ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )?
            	    int alt46=2;
            	    alt46 = dfa46.predict(input);
            	    switch (alt46) {
            	        case 1 :
            	            // InternalCheckCfg.g:2685:6: ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure )
            	            {
            	            // InternalCheckCfg.g:2691:6: (lv_memberCallArguments_23_0= ruleXClosure )
            	            // InternalCheckCfg.g:2692:7: lv_memberCallArguments_23_0= ruleXClosure
            	            {
            	            if ( state.backtracking==0 ) {

            	              							newCompositeNode(grammarAccess.getXMemberFeatureCallAccess().getMemberCallArgumentsXClosureParserRuleCall_1_1_4_0());
            	              						
            	            }
            	            pushFollow(FOLLOW_38);
            	            lv_memberCallArguments_23_0=ruleXClosure();

            	            state._fsp--;
            	            if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              							if (current==null) {
            	              								current = createModelElementForParent(grammarAccess.getXMemberFeatureCallRule());
            	              							}
            	              							add(
            	              								current,
            	              								"memberCallArguments",
            	              								lv_memberCallArguments_23_0,
            	              								"org.eclipse.xtext.xbase.Xbase.XClosure");
            	              							afterParserOrEnumRuleCall();
            	              						
            	            }

            	            }


            	            }
            	            break;

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
    // $ANTLR end "ruleXMemberFeatureCall"


    // $ANTLR start "entryRuleXPrimaryExpression"
    // InternalCheckCfg.g:2715:1: entryRuleXPrimaryExpression returns [EObject current=null] : iv_ruleXPrimaryExpression= ruleXPrimaryExpression EOF ;
    public final EObject entryRuleXPrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPrimaryExpression = null;


        try {
            // InternalCheckCfg.g:2715:59: (iv_ruleXPrimaryExpression= ruleXPrimaryExpression EOF )
            // InternalCheckCfg.g:2716:2: iv_ruleXPrimaryExpression= ruleXPrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXPrimaryExpression=ruleXPrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXPrimaryExpression; 
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
    // $ANTLR end "entryRuleXPrimaryExpression"


    // $ANTLR start "ruleXPrimaryExpression"
    // InternalCheckCfg.g:2722:1: ruleXPrimaryExpression returns [EObject current=null] : (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | ( ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression ) | this_XFeatureCall_4= ruleXFeatureCall | this_XLiteral_5= ruleXLiteral | this_XIfExpression_6= ruleXIfExpression | ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression ) | this_XBasicForLoopExpression_8= ruleXBasicForLoopExpression | this_XWhileExpression_9= ruleXWhileExpression | this_XDoWhileExpression_10= ruleXDoWhileExpression | this_XThrowExpression_11= ruleXThrowExpression | this_XReturnExpression_12= ruleXReturnExpression | this_XTryCatchFinallyExpression_13= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_14= ruleXParenthesizedExpression ) ;
    public final EObject ruleXPrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject this_XConstructorCall_0 = null;

        EObject this_XBlockExpression_1 = null;

        EObject this_XSwitchExpression_2 = null;

        EObject this_XSynchronizedExpression_3 = null;

        EObject this_XFeatureCall_4 = null;

        EObject this_XLiteral_5 = null;

        EObject this_XIfExpression_6 = null;

        EObject this_XForLoopExpression_7 = null;

        EObject this_XBasicForLoopExpression_8 = null;

        EObject this_XWhileExpression_9 = null;

        EObject this_XDoWhileExpression_10 = null;

        EObject this_XThrowExpression_11 = null;

        EObject this_XReturnExpression_12 = null;

        EObject this_XTryCatchFinallyExpression_13 = null;

        EObject this_XParenthesizedExpression_14 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:2728:2: ( (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | ( ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression ) | this_XFeatureCall_4= ruleXFeatureCall | this_XLiteral_5= ruleXLiteral | this_XIfExpression_6= ruleXIfExpression | ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression ) | this_XBasicForLoopExpression_8= ruleXBasicForLoopExpression | this_XWhileExpression_9= ruleXWhileExpression | this_XDoWhileExpression_10= ruleXDoWhileExpression | this_XThrowExpression_11= ruleXThrowExpression | this_XReturnExpression_12= ruleXReturnExpression | this_XTryCatchFinallyExpression_13= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_14= ruleXParenthesizedExpression ) )
            // InternalCheckCfg.g:2729:2: (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | ( ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression ) | this_XFeatureCall_4= ruleXFeatureCall | this_XLiteral_5= ruleXLiteral | this_XIfExpression_6= ruleXIfExpression | ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression ) | this_XBasicForLoopExpression_8= ruleXBasicForLoopExpression | this_XWhileExpression_9= ruleXWhileExpression | this_XDoWhileExpression_10= ruleXDoWhileExpression | this_XThrowExpression_11= ruleXThrowExpression | this_XReturnExpression_12= ruleXReturnExpression | this_XTryCatchFinallyExpression_13= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_14= ruleXParenthesizedExpression )
            {
            // InternalCheckCfg.g:2729:2: (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | ( ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression ) | this_XFeatureCall_4= ruleXFeatureCall | this_XLiteral_5= ruleXLiteral | this_XIfExpression_6= ruleXIfExpression | ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression ) | this_XBasicForLoopExpression_8= ruleXBasicForLoopExpression | this_XWhileExpression_9= ruleXWhileExpression | this_XDoWhileExpression_10= ruleXDoWhileExpression | this_XThrowExpression_11= ruleXThrowExpression | this_XReturnExpression_12= ruleXReturnExpression | this_XTryCatchFinallyExpression_13= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_14= ruleXParenthesizedExpression )
            int alt48=15;
            alt48 = dfa48.predict(input);
            switch (alt48) {
                case 1 :
                    // InternalCheckCfg.g:2730:3: this_XConstructorCall_0= ruleXConstructorCall
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXConstructorCallParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XConstructorCall_0=ruleXConstructorCall();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XConstructorCall_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:2739:3: this_XBlockExpression_1= ruleXBlockExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXBlockExpressionParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XBlockExpression_1=ruleXBlockExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XBlockExpression_1;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:2748:3: this_XSwitchExpression_2= ruleXSwitchExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXSwitchExpressionParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XSwitchExpression_2=ruleXSwitchExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XSwitchExpression_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:2757:3: ( ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression )
                    {
                    // InternalCheckCfg.g:2757:3: ( ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression )
                    // InternalCheckCfg.g:2758:4: ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXSynchronizedExpressionParserRuleCall_3());
                      			
                    }
                    pushFollow(FOLLOW_2);
                    this_XSynchronizedExpression_3=ruleXSynchronizedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_XSynchronizedExpression_3;
                      				afterParserOrEnumRuleCall();
                      			
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalCheckCfg.g:2775:3: this_XFeatureCall_4= ruleXFeatureCall
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXFeatureCallParserRuleCall_4());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XFeatureCall_4=ruleXFeatureCall();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XFeatureCall_4;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalCheckCfg.g:2784:3: this_XLiteral_5= ruleXLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXLiteralParserRuleCall_5());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XLiteral_5=ruleXLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XLiteral_5;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 7 :
                    // InternalCheckCfg.g:2793:3: this_XIfExpression_6= ruleXIfExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXIfExpressionParserRuleCall_6());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XIfExpression_6=ruleXIfExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XIfExpression_6;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 8 :
                    // InternalCheckCfg.g:2802:3: ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression )
                    {
                    // InternalCheckCfg.g:2802:3: ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression )
                    // InternalCheckCfg.g:2803:4: ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXForLoopExpressionParserRuleCall_7());
                      			
                    }
                    pushFollow(FOLLOW_2);
                    this_XForLoopExpression_7=ruleXForLoopExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_XForLoopExpression_7;
                      				afterParserOrEnumRuleCall();
                      			
                    }

                    }


                    }
                    break;
                case 9 :
                    // InternalCheckCfg.g:2826:3: this_XBasicForLoopExpression_8= ruleXBasicForLoopExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXBasicForLoopExpressionParserRuleCall_8());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XBasicForLoopExpression_8=ruleXBasicForLoopExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XBasicForLoopExpression_8;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 10 :
                    // InternalCheckCfg.g:2835:3: this_XWhileExpression_9= ruleXWhileExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXWhileExpressionParserRuleCall_9());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XWhileExpression_9=ruleXWhileExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XWhileExpression_9;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 11 :
                    // InternalCheckCfg.g:2844:3: this_XDoWhileExpression_10= ruleXDoWhileExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXDoWhileExpressionParserRuleCall_10());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XDoWhileExpression_10=ruleXDoWhileExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XDoWhileExpression_10;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 12 :
                    // InternalCheckCfg.g:2853:3: this_XThrowExpression_11= ruleXThrowExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXThrowExpressionParserRuleCall_11());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XThrowExpression_11=ruleXThrowExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XThrowExpression_11;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 13 :
                    // InternalCheckCfg.g:2862:3: this_XReturnExpression_12= ruleXReturnExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXReturnExpressionParserRuleCall_12());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XReturnExpression_12=ruleXReturnExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XReturnExpression_12;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 14 :
                    // InternalCheckCfg.g:2871:3: this_XTryCatchFinallyExpression_13= ruleXTryCatchFinallyExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXTryCatchFinallyExpressionParserRuleCall_13());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XTryCatchFinallyExpression_13=ruleXTryCatchFinallyExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XTryCatchFinallyExpression_13;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 15 :
                    // InternalCheckCfg.g:2880:3: this_XParenthesizedExpression_14= ruleXParenthesizedExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXPrimaryExpressionAccess().getXParenthesizedExpressionParserRuleCall_14());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XParenthesizedExpression_14=ruleXParenthesizedExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XParenthesizedExpression_14;
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
    // $ANTLR end "ruleXPrimaryExpression"


    // $ANTLR start "entryRuleXLiteral"
    // InternalCheckCfg.g:2892:1: entryRuleXLiteral returns [EObject current=null] : iv_ruleXLiteral= ruleXLiteral EOF ;
    public final EObject entryRuleXLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXLiteral = null;


        try {
            // InternalCheckCfg.g:2892:49: (iv_ruleXLiteral= ruleXLiteral EOF )
            // InternalCheckCfg.g:2893:2: iv_ruleXLiteral= ruleXLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXLiteral=ruleXLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXLiteral; 
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
    // $ANTLR end "entryRuleXLiteral"


    // $ANTLR start "ruleXLiteral"
    // InternalCheckCfg.g:2899:1: ruleXLiteral returns [EObject current=null] : (this_XCollectionLiteral_0= ruleXCollectionLiteral | ( ( ( () '[' ) )=>this_XClosure_1= ruleXClosure ) | this_XBooleanLiteral_2= ruleXBooleanLiteral | this_XNumberLiteral_3= ruleXNumberLiteral | this_XNullLiteral_4= ruleXNullLiteral | this_XStringLiteral_5= ruleXStringLiteral | this_XTypeLiteral_6= ruleXTypeLiteral ) ;
    public final EObject ruleXLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_XCollectionLiteral_0 = null;

        EObject this_XClosure_1 = null;

        EObject this_XBooleanLiteral_2 = null;

        EObject this_XNumberLiteral_3 = null;

        EObject this_XNullLiteral_4 = null;

        EObject this_XStringLiteral_5 = null;

        EObject this_XTypeLiteral_6 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:2905:2: ( (this_XCollectionLiteral_0= ruleXCollectionLiteral | ( ( ( () '[' ) )=>this_XClosure_1= ruleXClosure ) | this_XBooleanLiteral_2= ruleXBooleanLiteral | this_XNumberLiteral_3= ruleXNumberLiteral | this_XNullLiteral_4= ruleXNullLiteral | this_XStringLiteral_5= ruleXStringLiteral | this_XTypeLiteral_6= ruleXTypeLiteral ) )
            // InternalCheckCfg.g:2906:2: (this_XCollectionLiteral_0= ruleXCollectionLiteral | ( ( ( () '[' ) )=>this_XClosure_1= ruleXClosure ) | this_XBooleanLiteral_2= ruleXBooleanLiteral | this_XNumberLiteral_3= ruleXNumberLiteral | this_XNullLiteral_4= ruleXNullLiteral | this_XStringLiteral_5= ruleXStringLiteral | this_XTypeLiteral_6= ruleXTypeLiteral )
            {
            // InternalCheckCfg.g:2906:2: (this_XCollectionLiteral_0= ruleXCollectionLiteral | ( ( ( () '[' ) )=>this_XClosure_1= ruleXClosure ) | this_XBooleanLiteral_2= ruleXBooleanLiteral | this_XNumberLiteral_3= ruleXNumberLiteral | this_XNullLiteral_4= ruleXNullLiteral | this_XStringLiteral_5= ruleXStringLiteral | this_XTypeLiteral_6= ruleXTypeLiteral )
            int alt49=7;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==23) ) {
                alt49=1;
            }
            else if ( (LA49_0==24) && (synpred21_InternalCheckCfg())) {
                alt49=2;
            }
            else if ( ((LA49_0>=78 && LA49_0<=79)) ) {
                alt49=3;
            }
            else if ( ((LA49_0>=RULE_HEX && LA49_0<=RULE_DECIMAL)) ) {
                alt49=4;
            }
            else if ( (LA49_0==80) ) {
                alt49=5;
            }
            else if ( (LA49_0==RULE_STRING) ) {
                alt49=6;
            }
            else if ( (LA49_0==81) ) {
                alt49=7;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }
            switch (alt49) {
                case 1 :
                    // InternalCheckCfg.g:2907:3: this_XCollectionLiteral_0= ruleXCollectionLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXLiteralAccess().getXCollectionLiteralParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XCollectionLiteral_0=ruleXCollectionLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XCollectionLiteral_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:2916:3: ( ( ( () '[' ) )=>this_XClosure_1= ruleXClosure )
                    {
                    // InternalCheckCfg.g:2916:3: ( ( ( () '[' ) )=>this_XClosure_1= ruleXClosure )
                    // InternalCheckCfg.g:2917:4: ( ( () '[' ) )=>this_XClosure_1= ruleXClosure
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getXLiteralAccess().getXClosureParserRuleCall_1());
                      			
                    }
                    pushFollow(FOLLOW_2);
                    this_XClosure_1=ruleXClosure();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_XClosure_1;
                      				afterParserOrEnumRuleCall();
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:2933:3: this_XBooleanLiteral_2= ruleXBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXLiteralAccess().getXBooleanLiteralParserRuleCall_2());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XBooleanLiteral_2=ruleXBooleanLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XBooleanLiteral_2;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:2942:3: this_XNumberLiteral_3= ruleXNumberLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXLiteralAccess().getXNumberLiteralParserRuleCall_3());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XNumberLiteral_3=ruleXNumberLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XNumberLiteral_3;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalCheckCfg.g:2951:3: this_XNullLiteral_4= ruleXNullLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXLiteralAccess().getXNullLiteralParserRuleCall_4());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XNullLiteral_4=ruleXNullLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XNullLiteral_4;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 6 :
                    // InternalCheckCfg.g:2960:3: this_XStringLiteral_5= ruleXStringLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXLiteralAccess().getXStringLiteralParserRuleCall_5());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XStringLiteral_5=ruleXStringLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XStringLiteral_5;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 7 :
                    // InternalCheckCfg.g:2969:3: this_XTypeLiteral_6= ruleXTypeLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXLiteralAccess().getXTypeLiteralParserRuleCall_6());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XTypeLiteral_6=ruleXTypeLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XTypeLiteral_6;
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
    // $ANTLR end "ruleXLiteral"


    // $ANTLR start "entryRuleXCollectionLiteral"
    // InternalCheckCfg.g:2981:1: entryRuleXCollectionLiteral returns [EObject current=null] : iv_ruleXCollectionLiteral= ruleXCollectionLiteral EOF ;
    public final EObject entryRuleXCollectionLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXCollectionLiteral = null;


        try {
            // InternalCheckCfg.g:2981:59: (iv_ruleXCollectionLiteral= ruleXCollectionLiteral EOF )
            // InternalCheckCfg.g:2982:2: iv_ruleXCollectionLiteral= ruleXCollectionLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXCollectionLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXCollectionLiteral=ruleXCollectionLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXCollectionLiteral; 
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
    // $ANTLR end "entryRuleXCollectionLiteral"


    // $ANTLR start "ruleXCollectionLiteral"
    // InternalCheckCfg.g:2988:1: ruleXCollectionLiteral returns [EObject current=null] : (this_XSetLiteral_0= ruleXSetLiteral | this_XListLiteral_1= ruleXListLiteral ) ;
    public final EObject ruleXCollectionLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_XSetLiteral_0 = null;

        EObject this_XListLiteral_1 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:2994:2: ( (this_XSetLiteral_0= ruleXSetLiteral | this_XListLiteral_1= ruleXListLiteral ) )
            // InternalCheckCfg.g:2995:2: (this_XSetLiteral_0= ruleXSetLiteral | this_XListLiteral_1= ruleXListLiteral )
            {
            // InternalCheckCfg.g:2995:2: (this_XSetLiteral_0= ruleXSetLiteral | this_XListLiteral_1= ruleXListLiteral )
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==23) ) {
                int LA50_1 = input.LA(2);

                if ( (LA50_1==24) ) {
                    alt50=2;
                }
                else if ( (LA50_1==15) ) {
                    alt50=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }
            switch (alt50) {
                case 1 :
                    // InternalCheckCfg.g:2996:3: this_XSetLiteral_0= ruleXSetLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXCollectionLiteralAccess().getXSetLiteralParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XSetLiteral_0=ruleXSetLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XSetLiteral_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:3005:3: this_XListLiteral_1= ruleXListLiteral
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXCollectionLiteralAccess().getXListLiteralParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XListLiteral_1=ruleXListLiteral();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XListLiteral_1;
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
    // $ANTLR end "ruleXCollectionLiteral"


    // $ANTLR start "entryRuleXSetLiteral"
    // InternalCheckCfg.g:3017:1: entryRuleXSetLiteral returns [EObject current=null] : iv_ruleXSetLiteral= ruleXSetLiteral EOF ;
    public final EObject entryRuleXSetLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSetLiteral = null;


        try {
            // InternalCheckCfg.g:3017:52: (iv_ruleXSetLiteral= ruleXSetLiteral EOF )
            // InternalCheckCfg.g:3018:2: iv_ruleXSetLiteral= ruleXSetLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXSetLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXSetLiteral=ruleXSetLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXSetLiteral; 
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
    // $ANTLR end "entryRuleXSetLiteral"


    // $ANTLR start "ruleXSetLiteral"
    // InternalCheckCfg.g:3024:1: ruleXSetLiteral returns [EObject current=null] : ( () otherlv_1= '#' otherlv_2= '{' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= '}' ) ;
    public final EObject ruleXSetLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_elements_3_0 = null;

        EObject lv_elements_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3030:2: ( ( () otherlv_1= '#' otherlv_2= '{' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= '}' ) )
            // InternalCheckCfg.g:3031:2: ( () otherlv_1= '#' otherlv_2= '{' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= '}' )
            {
            // InternalCheckCfg.g:3031:2: ( () otherlv_1= '#' otherlv_2= '{' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= '}' )
            // InternalCheckCfg.g:3032:3: () otherlv_1= '#' otherlv_2= '{' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= '}'
            {
            // InternalCheckCfg.g:3032:3: ()
            // InternalCheckCfg.g:3033:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXSetLiteralAccess().getXSetLiteralAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,23,FOLLOW_8); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXSetLiteralAccess().getNumberSignKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,15,FOLLOW_47); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXSetLiteralAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalCheckCfg.g:3047:3: ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=RULE_STRING && LA52_0<=RULE_ID)||LA52_0==15||LA52_0==17||LA52_0==19||(LA52_0>=23 && LA52_0<=24)||LA52_0==31||(LA52_0>=47 && LA52_0<=48)||LA52_0==53||LA52_0==62||LA52_0==64||(LA52_0>=68 && LA52_0<=69)||(LA52_0>=72 && LA52_0<=84)||LA52_0==86) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalCheckCfg.g:3048:4: ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )*
                    {
                    // InternalCheckCfg.g:3048:4: ( (lv_elements_3_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3049:5: (lv_elements_3_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3049:5: (lv_elements_3_0= ruleXExpression )
                    // InternalCheckCfg.g:3050:6: lv_elements_3_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXSetLiteralAccess().getElementsXExpressionParserRuleCall_3_0_0());
                      					
                    }
                    pushFollow(FOLLOW_48);
                    lv_elements_3_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXSetLiteralRule());
                      						}
                      						add(
                      							current,
                      							"elements",
                      							lv_elements_3_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:3067:4: (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==20) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // InternalCheckCfg.g:3068:5: otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) )
                    	    {
                    	    otherlv_4=(Token)match(input,20,FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_4, grammarAccess.getXSetLiteralAccess().getCommaKeyword_3_1_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:3072:5: ( (lv_elements_5_0= ruleXExpression ) )
                    	    // InternalCheckCfg.g:3073:6: (lv_elements_5_0= ruleXExpression )
                    	    {
                    	    // InternalCheckCfg.g:3073:6: (lv_elements_5_0= ruleXExpression )
                    	    // InternalCheckCfg.g:3074:7: lv_elements_5_0= ruleXExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXSetLiteralAccess().getElementsXExpressionParserRuleCall_3_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_48);
                    	    lv_elements_5_0=ruleXExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXSetLiteralRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"elements",
                    	      								lv_elements_5_0,
                    	      								"org.eclipse.xtext.xbase.Xbase.XExpression");
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
                    break;

            }

            otherlv_6=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getXSetLiteralAccess().getRightCurlyBracketKeyword_4());
              		
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
    // $ANTLR end "ruleXSetLiteral"


    // $ANTLR start "entryRuleXListLiteral"
    // InternalCheckCfg.g:3101:1: entryRuleXListLiteral returns [EObject current=null] : iv_ruleXListLiteral= ruleXListLiteral EOF ;
    public final EObject entryRuleXListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXListLiteral = null;


        try {
            // InternalCheckCfg.g:3101:53: (iv_ruleXListLiteral= ruleXListLiteral EOF )
            // InternalCheckCfg.g:3102:2: iv_ruleXListLiteral= ruleXListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXListLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXListLiteral=ruleXListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXListLiteral; 
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
    // $ANTLR end "entryRuleXListLiteral"


    // $ANTLR start "ruleXListLiteral"
    // InternalCheckCfg.g:3108:1: ruleXListLiteral returns [EObject current=null] : ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= ']' ) ;
    public final EObject ruleXListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_elements_3_0 = null;

        EObject lv_elements_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3114:2: ( ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= ']' ) )
            // InternalCheckCfg.g:3115:2: ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= ']' )
            {
            // InternalCheckCfg.g:3115:2: ( () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= ']' )
            // InternalCheckCfg.g:3116:3: () otherlv_1= '#' otherlv_2= '[' ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )? otherlv_6= ']'
            {
            // InternalCheckCfg.g:3116:3: ()
            // InternalCheckCfg.g:3117:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXListLiteralAccess().getXListLiteralAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,23,FOLLOW_17); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXListLiteralAccess().getNumberSignKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,24,FOLLOW_49); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXListLiteralAccess().getLeftSquareBracketKeyword_2());
              		
            }
            // InternalCheckCfg.g:3131:3: ( ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )* )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( ((LA54_0>=RULE_STRING && LA54_0<=RULE_ID)||LA54_0==15||LA54_0==17||LA54_0==19||(LA54_0>=23 && LA54_0<=24)||LA54_0==31||(LA54_0>=47 && LA54_0<=48)||LA54_0==53||LA54_0==62||LA54_0==64||(LA54_0>=68 && LA54_0<=69)||(LA54_0>=72 && LA54_0<=84)||LA54_0==86) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // InternalCheckCfg.g:3132:4: ( (lv_elements_3_0= ruleXExpression ) ) (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )*
                    {
                    // InternalCheckCfg.g:3132:4: ( (lv_elements_3_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3133:5: (lv_elements_3_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3133:5: (lv_elements_3_0= ruleXExpression )
                    // InternalCheckCfg.g:3134:6: lv_elements_3_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXListLiteralAccess().getElementsXExpressionParserRuleCall_3_0_0());
                      					
                    }
                    pushFollow(FOLLOW_19);
                    lv_elements_3_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXListLiteralRule());
                      						}
                      						add(
                      							current,
                      							"elements",
                      							lv_elements_3_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:3151:4: (otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) ) )*
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==20) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // InternalCheckCfg.g:3152:5: otherlv_4= ',' ( (lv_elements_5_0= ruleXExpression ) )
                    	    {
                    	    otherlv_4=(Token)match(input,20,FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_4, grammarAccess.getXListLiteralAccess().getCommaKeyword_3_1_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:3156:5: ( (lv_elements_5_0= ruleXExpression ) )
                    	    // InternalCheckCfg.g:3157:6: (lv_elements_5_0= ruleXExpression )
                    	    {
                    	    // InternalCheckCfg.g:3157:6: (lv_elements_5_0= ruleXExpression )
                    	    // InternalCheckCfg.g:3158:7: lv_elements_5_0= ruleXExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXListLiteralAccess().getElementsXExpressionParserRuleCall_3_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_19);
                    	    lv_elements_5_0=ruleXExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXListLiteralRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"elements",
                    	      								lv_elements_5_0,
                    	      								"org.eclipse.xtext.xbase.Xbase.XExpression");
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

            otherlv_6=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getXListLiteralAccess().getRightSquareBracketKeyword_4());
              		
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
    // $ANTLR end "ruleXListLiteral"


    // $ANTLR start "entryRuleXClosure"
    // InternalCheckCfg.g:3185:1: entryRuleXClosure returns [EObject current=null] : iv_ruleXClosure= ruleXClosure EOF ;
    public final EObject entryRuleXClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClosure = null;


        try {
            // InternalCheckCfg.g:3185:49: (iv_ruleXClosure= ruleXClosure EOF )
            // InternalCheckCfg.g:3186:2: iv_ruleXClosure= ruleXClosure EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXClosureRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXClosure=ruleXClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXClosure; 
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
    // $ANTLR end "entryRuleXClosure"


    // $ANTLR start "ruleXClosure"
    // InternalCheckCfg.g:3192:1: ruleXClosure returns [EObject current=null] : ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' ) ;
    public final EObject ruleXClosure() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_explicitSyntax_5_0=null;
        Token otherlv_7=null;
        EObject lv_declaredFormalParameters_2_0 = null;

        EObject lv_declaredFormalParameters_4_0 = null;

        EObject lv_expression_6_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3198:2: ( ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' ) )
            // InternalCheckCfg.g:3199:2: ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' )
            {
            // InternalCheckCfg.g:3199:2: ( ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']' )
            // InternalCheckCfg.g:3200:3: ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) ) ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )? ( (lv_expression_6_0= ruleXExpressionInClosure ) ) otherlv_7= ']'
            {
            // InternalCheckCfg.g:3200:3: ( ( ( () '[' ) )=> ( () otherlv_1= '[' ) )
            // InternalCheckCfg.g:3201:4: ( ( () '[' ) )=> ( () otherlv_1= '[' )
            {
            // InternalCheckCfg.g:3207:4: ( () otherlv_1= '[' )
            // InternalCheckCfg.g:3208:5: () otherlv_1= '['
            {
            // InternalCheckCfg.g:3208:5: ()
            // InternalCheckCfg.g:3209:6: 
            {
            if ( state.backtracking==0 ) {

              						current = forceCreateModelElement(
              							grammarAccess.getXClosureAccess().getXClosureAction_0_0_0(),
              							current);
              					
            }

            }

            otherlv_1=(Token)match(input,24,FOLLOW_50); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getXClosureAccess().getLeftSquareBracketKeyword_0_0_1());
              				
            }

            }


            }

            // InternalCheckCfg.g:3221:3: ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )?
            int alt57=2;
            alt57 = dfa57.predict(input);
            switch (alt57) {
                case 1 :
                    // InternalCheckCfg.g:3222:4: ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) )
                    {
                    // InternalCheckCfg.g:3245:4: ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) )
                    // InternalCheckCfg.g:3246:5: ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) )
                    {
                    // InternalCheckCfg.g:3246:5: ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )?
                    int alt56=2;
                    int LA56_0 = input.LA(1);

                    if ( (LA56_0==RULE_ID||LA56_0==19||LA56_0==44) ) {
                        alt56=1;
                    }
                    switch (alt56) {
                        case 1 :
                            // InternalCheckCfg.g:3247:6: ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )*
                            {
                            // InternalCheckCfg.g:3247:6: ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) )
                            // InternalCheckCfg.g:3248:7: (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter )
                            {
                            // InternalCheckCfg.g:3248:7: (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter )
                            // InternalCheckCfg.g:3249:8: lv_declaredFormalParameters_2_0= ruleJvmFormalParameter
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getXClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_1_0_0_0_0());
                              							
                            }
                            pushFollow(FOLLOW_51);
                            lv_declaredFormalParameters_2_0=ruleJvmFormalParameter();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getXClosureRule());
                              								}
                              								add(
                              									current,
                              									"declaredFormalParameters",
                              									lv_declaredFormalParameters_2_0,
                              									"org.eclipse.xtext.xbase.Xbase.JvmFormalParameter");
                              								afterParserOrEnumRuleCall();
                              							
                            }

                            }


                            }

                            // InternalCheckCfg.g:3266:6: (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )*
                            loop55:
                            do {
                                int alt55=2;
                                int LA55_0 = input.LA(1);

                                if ( (LA55_0==20) ) {
                                    alt55=1;
                                }


                                switch (alt55) {
                            	case 1 :
                            	    // InternalCheckCfg.g:3267:7: otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) )
                            	    {
                            	    otherlv_3=(Token)match(input,20,FOLLOW_29); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      							newLeafNode(otherlv_3, grammarAccess.getXClosureAccess().getCommaKeyword_1_0_0_1_0());
                            	      						
                            	    }
                            	    // InternalCheckCfg.g:3271:7: ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) )
                            	    // InternalCheckCfg.g:3272:8: (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter )
                            	    {
                            	    // InternalCheckCfg.g:3272:8: (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter )
                            	    // InternalCheckCfg.g:3273:9: lv_declaredFormalParameters_4_0= ruleJvmFormalParameter
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      									newCompositeNode(grammarAccess.getXClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_1_0_0_1_1_0());
                            	      								
                            	    }
                            	    pushFollow(FOLLOW_51);
                            	    lv_declaredFormalParameters_4_0=ruleJvmFormalParameter();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      									if (current==null) {
                            	      										current = createModelElementForParent(grammarAccess.getXClosureRule());
                            	      									}
                            	      									add(
                            	      										current,
                            	      										"declaredFormalParameters",
                            	      										lv_declaredFormalParameters_4_0,
                            	      										"org.eclipse.xtext.xbase.Xbase.JvmFormalParameter");
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

                    // InternalCheckCfg.g:3292:5: ( (lv_explicitSyntax_5_0= '|' ) )
                    // InternalCheckCfg.g:3293:6: (lv_explicitSyntax_5_0= '|' )
                    {
                    // InternalCheckCfg.g:3293:6: (lv_explicitSyntax_5_0= '|' )
                    // InternalCheckCfg.g:3294:7: lv_explicitSyntax_5_0= '|'
                    {
                    lv_explicitSyntax_5_0=(Token)match(input,60,FOLLOW_52); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							newLeafNode(lv_explicitSyntax_5_0, grammarAccess.getXClosureAccess().getExplicitSyntaxVerticalLineKeyword_1_0_1_0());
                      						
                    }
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElement(grammarAccess.getXClosureRule());
                      							}
                      							setWithLastConsumed(current, "explicitSyntax", lv_explicitSyntax_5_0 != null, "|");
                      						
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            // InternalCheckCfg.g:3308:3: ( (lv_expression_6_0= ruleXExpressionInClosure ) )
            // InternalCheckCfg.g:3309:4: (lv_expression_6_0= ruleXExpressionInClosure )
            {
            // InternalCheckCfg.g:3309:4: (lv_expression_6_0= ruleXExpressionInClosure )
            // InternalCheckCfg.g:3310:5: lv_expression_6_0= ruleXExpressionInClosure
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXClosureAccess().getExpressionXExpressionInClosureParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_53);
            lv_expression_6_0=ruleXExpressionInClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXClosureRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_6_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpressionInClosure");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_7=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getXClosureAccess().getRightSquareBracketKeyword_3());
              		
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
    // $ANTLR end "ruleXClosure"


    // $ANTLR start "entryRuleXExpressionInClosure"
    // InternalCheckCfg.g:3335:1: entryRuleXExpressionInClosure returns [EObject current=null] : iv_ruleXExpressionInClosure= ruleXExpressionInClosure EOF ;
    public final EObject entryRuleXExpressionInClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionInClosure = null;


        try {
            // InternalCheckCfg.g:3335:61: (iv_ruleXExpressionInClosure= ruleXExpressionInClosure EOF )
            // InternalCheckCfg.g:3336:2: iv_ruleXExpressionInClosure= ruleXExpressionInClosure EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionInClosureRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXExpressionInClosure=ruleXExpressionInClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpressionInClosure; 
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
    // $ANTLR end "entryRuleXExpressionInClosure"


    // $ANTLR start "ruleXExpressionInClosure"
    // InternalCheckCfg.g:3342:1: ruleXExpressionInClosure returns [EObject current=null] : ( () ( ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_2= ';' )? )* ) ;
    public final EObject ruleXExpressionInClosure() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_expressions_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3348:2: ( ( () ( ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_2= ';' )? )* ) )
            // InternalCheckCfg.g:3349:2: ( () ( ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_2= ';' )? )* )
            {
            // InternalCheckCfg.g:3349:2: ( () ( ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_2= ';' )? )* )
            // InternalCheckCfg.g:3350:3: () ( ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_2= ';' )? )*
            {
            // InternalCheckCfg.g:3350:3: ()
            // InternalCheckCfg.g:3351:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXExpressionInClosureAccess().getXBlockExpressionAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:3357:3: ( ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_2= ';' )? )*
            loop59:
            do {
                int alt59=2;
                int LA59_0 = input.LA(1);

                if ( ((LA59_0>=RULE_STRING && LA59_0<=RULE_ID)||LA59_0==15||LA59_0==17||LA59_0==19||(LA59_0>=23 && LA59_0<=24)||LA59_0==31||(LA59_0>=47 && LA59_0<=48)||LA59_0==53||LA59_0==62||LA59_0==64||(LA59_0>=68 && LA59_0<=84)||LA59_0==86) ) {
                    alt59=1;
                }


                switch (alt59) {
            	case 1 :
            	    // InternalCheckCfg.g:3358:4: ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_2= ';' )?
            	    {
            	    // InternalCheckCfg.g:3358:4: ( (lv_expressions_1_0= ruleXExpressionOrVarDeclaration ) )
            	    // InternalCheckCfg.g:3359:5: (lv_expressions_1_0= ruleXExpressionOrVarDeclaration )
            	    {
            	    // InternalCheckCfg.g:3359:5: (lv_expressions_1_0= ruleXExpressionOrVarDeclaration )
            	    // InternalCheckCfg.g:3360:6: lv_expressions_1_0= ruleXExpressionOrVarDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXExpressionInClosureAccess().getExpressionsXExpressionOrVarDeclarationParserRuleCall_1_0_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_54);
            	    lv_expressions_1_0=ruleXExpressionOrVarDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXExpressionInClosureRule());
            	      						}
            	      						add(
            	      							current,
            	      							"expressions",
            	      							lv_expressions_1_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XExpressionOrVarDeclaration");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }

            	    // InternalCheckCfg.g:3377:4: (otherlv_2= ';' )?
            	    int alt58=2;
            	    int LA58_0 = input.LA(1);

            	    if ( (LA58_0==61) ) {
            	        alt58=1;
            	    }
            	    switch (alt58) {
            	        case 1 :
            	            // InternalCheckCfg.g:3378:5: otherlv_2= ';'
            	            {
            	            otherlv_2=(Token)match(input,61,FOLLOW_55); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					newLeafNode(otherlv_2, grammarAccess.getXExpressionInClosureAccess().getSemicolonKeyword_1_1());
            	              				
            	            }

            	            }
            	            break;

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
    // $ANTLR end "ruleXExpressionInClosure"


    // $ANTLR start "entryRuleXShortClosure"
    // InternalCheckCfg.g:3388:1: entryRuleXShortClosure returns [EObject current=null] : iv_ruleXShortClosure= ruleXShortClosure EOF ;
    public final EObject entryRuleXShortClosure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXShortClosure = null;


        try {
            // InternalCheckCfg.g:3388:54: (iv_ruleXShortClosure= ruleXShortClosure EOF )
            // InternalCheckCfg.g:3389:2: iv_ruleXShortClosure= ruleXShortClosure EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXShortClosureRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXShortClosure=ruleXShortClosure();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXShortClosure; 
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
    // $ANTLR end "entryRuleXShortClosure"


    // $ANTLR start "ruleXShortClosure"
    // InternalCheckCfg.g:3395:1: ruleXShortClosure returns [EObject current=null] : ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) ) ;
    public final EObject ruleXShortClosure() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token lv_explicitSyntax_4_0=null;
        EObject lv_declaredFormalParameters_1_0 = null;

        EObject lv_declaredFormalParameters_3_0 = null;

        EObject lv_expression_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3401:2: ( ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) ) )
            // InternalCheckCfg.g:3402:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) )
            {
            // InternalCheckCfg.g:3402:2: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) ) )
            // InternalCheckCfg.g:3403:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) ) ( (lv_expression_5_0= ruleXExpression ) )
            {
            // InternalCheckCfg.g:3403:3: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) ) )
            // InternalCheckCfg.g:3404:4: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) )
            {
            // InternalCheckCfg.g:3429:4: ( () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) ) )
            // InternalCheckCfg.g:3430:5: () ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_4_0= '|' ) )
            {
            // InternalCheckCfg.g:3430:5: ()
            // InternalCheckCfg.g:3431:6: 
            {
            if ( state.backtracking==0 ) {

              						current = forceCreateModelElement(
              							grammarAccess.getXShortClosureAccess().getXClosureAction_0_0_0(),
              							current);
              					
            }

            }

            // InternalCheckCfg.g:3437:5: ( ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )* )?
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==RULE_ID||LA61_0==19||LA61_0==44) ) {
                alt61=1;
            }
            switch (alt61) {
                case 1 :
                    // InternalCheckCfg.g:3438:6: ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) ) (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )*
                    {
                    // InternalCheckCfg.g:3438:6: ( (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter ) )
                    // InternalCheckCfg.g:3439:7: (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter )
                    {
                    // InternalCheckCfg.g:3439:7: (lv_declaredFormalParameters_1_0= ruleJvmFormalParameter )
                    // InternalCheckCfg.g:3440:8: lv_declaredFormalParameters_1_0= ruleJvmFormalParameter
                    {
                    if ( state.backtracking==0 ) {

                      								newCompositeNode(grammarAccess.getXShortClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_0_0_1_0_0());
                      							
                    }
                    pushFollow(FOLLOW_51);
                    lv_declaredFormalParameters_1_0=ruleJvmFormalParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      								if (current==null) {
                      									current = createModelElementForParent(grammarAccess.getXShortClosureRule());
                      								}
                      								add(
                      									current,
                      									"declaredFormalParameters",
                      									lv_declaredFormalParameters_1_0,
                      									"org.eclipse.xtext.xbase.Xbase.JvmFormalParameter");
                      								afterParserOrEnumRuleCall();
                      							
                    }

                    }


                    }

                    // InternalCheckCfg.g:3457:6: (otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) ) )*
                    loop60:
                    do {
                        int alt60=2;
                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==20) ) {
                            alt60=1;
                        }


                        switch (alt60) {
                    	case 1 :
                    	    // InternalCheckCfg.g:3458:7: otherlv_2= ',' ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) )
                    	    {
                    	    otherlv_2=(Token)match(input,20,FOLLOW_29); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							newLeafNode(otherlv_2, grammarAccess.getXShortClosureAccess().getCommaKeyword_0_0_1_1_0());
                    	      						
                    	    }
                    	    // InternalCheckCfg.g:3462:7: ( (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter ) )
                    	    // InternalCheckCfg.g:3463:8: (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter )
                    	    {
                    	    // InternalCheckCfg.g:3463:8: (lv_declaredFormalParameters_3_0= ruleJvmFormalParameter )
                    	    // InternalCheckCfg.g:3464:9: lv_declaredFormalParameters_3_0= ruleJvmFormalParameter
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      									newCompositeNode(grammarAccess.getXShortClosureAccess().getDeclaredFormalParametersJvmFormalParameterParserRuleCall_0_0_1_1_1_0());
                    	      								
                    	    }
                    	    pushFollow(FOLLOW_51);
                    	    lv_declaredFormalParameters_3_0=ruleJvmFormalParameter();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      									if (current==null) {
                    	      										current = createModelElementForParent(grammarAccess.getXShortClosureRule());
                    	      									}
                    	      									add(
                    	      										current,
                    	      										"declaredFormalParameters",
                    	      										lv_declaredFormalParameters_3_0,
                    	      										"org.eclipse.xtext.xbase.Xbase.JvmFormalParameter");
                    	      									afterParserOrEnumRuleCall();
                    	      								
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop60;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalCheckCfg.g:3483:5: ( (lv_explicitSyntax_4_0= '|' ) )
            // InternalCheckCfg.g:3484:6: (lv_explicitSyntax_4_0= '|' )
            {
            // InternalCheckCfg.g:3484:6: (lv_explicitSyntax_4_0= '|' )
            // InternalCheckCfg.g:3485:7: lv_explicitSyntax_4_0= '|'
            {
            lv_explicitSyntax_4_0=(Token)match(input,60,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              							newLeafNode(lv_explicitSyntax_4_0, grammarAccess.getXShortClosureAccess().getExplicitSyntaxVerticalLineKeyword_0_0_2_0());
              						
            }
            if ( state.backtracking==0 ) {

              							if (current==null) {
              								current = createModelElement(grammarAccess.getXShortClosureRule());
              							}
              							setWithLastConsumed(current, "explicitSyntax", lv_explicitSyntax_4_0 != null, "|");
              						
            }

            }


            }


            }


            }

            // InternalCheckCfg.g:3499:3: ( (lv_expression_5_0= ruleXExpression ) )
            // InternalCheckCfg.g:3500:4: (lv_expression_5_0= ruleXExpression )
            {
            // InternalCheckCfg.g:3500:4: (lv_expression_5_0= ruleXExpression )
            // InternalCheckCfg.g:3501:5: lv_expression_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXShortClosureAccess().getExpressionXExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXShortClosureRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_5_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXShortClosure"


    // $ANTLR start "entryRuleXParenthesizedExpression"
    // InternalCheckCfg.g:3522:1: entryRuleXParenthesizedExpression returns [EObject current=null] : iv_ruleXParenthesizedExpression= ruleXParenthesizedExpression EOF ;
    public final EObject entryRuleXParenthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXParenthesizedExpression = null;


        try {
            // InternalCheckCfg.g:3522:65: (iv_ruleXParenthesizedExpression= ruleXParenthesizedExpression EOF )
            // InternalCheckCfg.g:3523:2: iv_ruleXParenthesizedExpression= ruleXParenthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXParenthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXParenthesizedExpression=ruleXParenthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXParenthesizedExpression; 
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
    // $ANTLR end "entryRuleXParenthesizedExpression"


    // $ANTLR start "ruleXParenthesizedExpression"
    // InternalCheckCfg.g:3529:1: ruleXParenthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' ) ;
    public final EObject ruleXParenthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_XExpression_1 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3535:2: ( (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' ) )
            // InternalCheckCfg.g:3536:2: (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' )
            {
            // InternalCheckCfg.g:3536:2: (otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')' )
            // InternalCheckCfg.g:3537:3: otherlv_0= '(' this_XExpression_1= ruleXExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getXParenthesizedExpressionAccess().getLeftParenthesisKeyword_0());
              		
            }
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getXParenthesizedExpressionAccess().getXExpressionParserRuleCall_1());
              		
            }
            pushFollow(FOLLOW_45);
            this_XExpression_1=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_XExpression_1;
              			afterParserOrEnumRuleCall();
              		
            }
            otherlv_2=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXParenthesizedExpressionAccess().getRightParenthesisKeyword_2());
              		
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
    // $ANTLR end "ruleXParenthesizedExpression"


    // $ANTLR start "entryRuleXIfExpression"
    // InternalCheckCfg.g:3557:1: entryRuleXIfExpression returns [EObject current=null] : iv_ruleXIfExpression= ruleXIfExpression EOF ;
    public final EObject entryRuleXIfExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXIfExpression = null;


        try {
            // InternalCheckCfg.g:3557:54: (iv_ruleXIfExpression= ruleXIfExpression EOF )
            // InternalCheckCfg.g:3558:2: iv_ruleXIfExpression= ruleXIfExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXIfExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXIfExpression=ruleXIfExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXIfExpression; 
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
    // $ANTLR end "entryRuleXIfExpression"


    // $ANTLR start "ruleXIfExpression"
    // InternalCheckCfg.g:3564:1: ruleXIfExpression returns [EObject current=null] : ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? ) ;
    public final EObject ruleXIfExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_if_3_0 = null;

        EObject lv_then_5_0 = null;

        EObject lv_else_7_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3570:2: ( ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? ) )
            // InternalCheckCfg.g:3571:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? )
            {
            // InternalCheckCfg.g:3571:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )? )
            // InternalCheckCfg.g:3572:3: () otherlv_1= 'if' otherlv_2= '(' ( (lv_if_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_then_5_0= ruleXExpression ) ) ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )?
            {
            // InternalCheckCfg.g:3572:3: ()
            // InternalCheckCfg.g:3573:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXIfExpressionAccess().getXIfExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,62,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXIfExpressionAccess().getIfKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,19,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXIfExpressionAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalCheckCfg.g:3587:3: ( (lv_if_3_0= ruleXExpression ) )
            // InternalCheckCfg.g:3588:4: (lv_if_3_0= ruleXExpression )
            {
            // InternalCheckCfg.g:3588:4: (lv_if_3_0= ruleXExpression )
            // InternalCheckCfg.g:3589:5: lv_if_3_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXIfExpressionAccess().getIfXExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_45);
            lv_if_3_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXIfExpressionRule());
              					}
              					set(
              						current,
              						"if",
              						lv_if_3_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,21,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getXIfExpressionAccess().getRightParenthesisKeyword_4());
              		
            }
            // InternalCheckCfg.g:3610:3: ( (lv_then_5_0= ruleXExpression ) )
            // InternalCheckCfg.g:3611:4: (lv_then_5_0= ruleXExpression )
            {
            // InternalCheckCfg.g:3611:4: (lv_then_5_0= ruleXExpression )
            // InternalCheckCfg.g:3612:5: lv_then_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXIfExpressionAccess().getThenXExpressionParserRuleCall_5_0());
              				
            }
            pushFollow(FOLLOW_57);
            lv_then_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXIfExpressionRule());
              					}
              					set(
              						current,
              						"then",
              						lv_then_5_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:3629:3: ( ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==63) ) {
                int LA62_1 = input.LA(2);

                if ( (synpred25_InternalCheckCfg()) ) {
                    alt62=1;
                }
            }
            switch (alt62) {
                case 1 :
                    // InternalCheckCfg.g:3630:4: ( ( 'else' )=>otherlv_6= 'else' ) ( (lv_else_7_0= ruleXExpression ) )
                    {
                    // InternalCheckCfg.g:3630:4: ( ( 'else' )=>otherlv_6= 'else' )
                    // InternalCheckCfg.g:3631:5: ( 'else' )=>otherlv_6= 'else'
                    {
                    otherlv_6=(Token)match(input,63,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_6, grammarAccess.getXIfExpressionAccess().getElseKeyword_6_0());
                      				
                    }

                    }

                    // InternalCheckCfg.g:3637:4: ( (lv_else_7_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3638:5: (lv_else_7_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3638:5: (lv_else_7_0= ruleXExpression )
                    // InternalCheckCfg.g:3639:6: lv_else_7_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXIfExpressionAccess().getElseXExpressionParserRuleCall_6_1_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_else_7_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXIfExpressionRule());
                      						}
                      						set(
                      							current,
                      							"else",
                      							lv_else_7_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXIfExpression"


    // $ANTLR start "entryRuleXSwitchExpression"
    // InternalCheckCfg.g:3661:1: entryRuleXSwitchExpression returns [EObject current=null] : iv_ruleXSwitchExpression= ruleXSwitchExpression EOF ;
    public final EObject entryRuleXSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSwitchExpression = null;


        try {
            // InternalCheckCfg.g:3661:58: (iv_ruleXSwitchExpression= ruleXSwitchExpression EOF )
            // InternalCheckCfg.g:3662:2: iv_ruleXSwitchExpression= ruleXSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXSwitchExpression=ruleXSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXSwitchExpression; 
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
    // $ANTLR end "entryRuleXSwitchExpression"


    // $ANTLR start "ruleXSwitchExpression"
    // InternalCheckCfg.g:3668:1: ruleXSwitchExpression returns [EObject current=null] : ( () otherlv_1= 'switch' ( ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' ) | ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )* (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' ) ;
    public final EObject ruleXSwitchExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        EObject lv_declaredParam_3_0 = null;

        EObject lv_switch_5_0 = null;

        EObject lv_declaredParam_7_0 = null;

        EObject lv_switch_9_0 = null;

        EObject lv_cases_11_0 = null;

        EObject lv_default_14_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3674:2: ( ( () otherlv_1= 'switch' ( ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' ) | ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )* (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' ) )
            // InternalCheckCfg.g:3675:2: ( () otherlv_1= 'switch' ( ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' ) | ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )* (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' )
            {
            // InternalCheckCfg.g:3675:2: ( () otherlv_1= 'switch' ( ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' ) | ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )* (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}' )
            // InternalCheckCfg.g:3676:3: () otherlv_1= 'switch' ( ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' ) | ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) ) ) otherlv_10= '{' ( (lv_cases_11_0= ruleXCasePart ) )* (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )? otherlv_15= '}'
            {
            // InternalCheckCfg.g:3676:3: ()
            // InternalCheckCfg.g:3677:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXSwitchExpressionAccess().getXSwitchExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,64,FOLLOW_58); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXSwitchExpressionAccess().getSwitchKeyword_1());
              		
            }
            // InternalCheckCfg.g:3687:3: ( ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' ) | ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) ) )
            int alt64=2;
            alt64 = dfa64.predict(input);
            switch (alt64) {
                case 1 :
                    // InternalCheckCfg.g:3688:4: ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' )
                    {
                    // InternalCheckCfg.g:3688:4: ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' )
                    // InternalCheckCfg.g:3689:5: ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')'
                    {
                    // InternalCheckCfg.g:3689:5: ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) )
                    // InternalCheckCfg.g:3690:6: ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' )
                    {
                    // InternalCheckCfg.g:3700:6: (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' )
                    // InternalCheckCfg.g:3701:7: otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':'
                    {
                    otherlv_2=(Token)match(input,19,FOLLOW_29); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							newLeafNode(otherlv_2, grammarAccess.getXSwitchExpressionAccess().getLeftParenthesisKeyword_2_0_0_0_0());
                      						
                    }
                    // InternalCheckCfg.g:3705:7: ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) )
                    // InternalCheckCfg.g:3706:8: (lv_declaredParam_3_0= ruleJvmFormalParameter )
                    {
                    // InternalCheckCfg.g:3706:8: (lv_declaredParam_3_0= ruleJvmFormalParameter )
                    // InternalCheckCfg.g:3707:9: lv_declaredParam_3_0= ruleJvmFormalParameter
                    {
                    if ( state.backtracking==0 ) {

                      									newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getDeclaredParamJvmFormalParameterParserRuleCall_2_0_0_0_1_0());
                      								
                    }
                    pushFollow(FOLLOW_59);
                    lv_declaredParam_3_0=ruleJvmFormalParameter();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      									if (current==null) {
                      										current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      									}
                      									set(
                      										current,
                      										"declaredParam",
                      										lv_declaredParam_3_0,
                      										"org.eclipse.xtext.xbase.Xbase.JvmFormalParameter");
                      									afterParserOrEnumRuleCall();
                      								
                    }

                    }


                    }

                    otherlv_4=(Token)match(input,65,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							newLeafNode(otherlv_4, grammarAccess.getXSwitchExpressionAccess().getColonKeyword_2_0_0_0_2());
                      						
                    }

                    }


                    }

                    // InternalCheckCfg.g:3730:5: ( (lv_switch_5_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3731:6: (lv_switch_5_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3731:6: (lv_switch_5_0= ruleXExpression )
                    // InternalCheckCfg.g:3732:7: lv_switch_5_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getSwitchXExpressionParserRuleCall_2_0_1_0());
                      						
                    }
                    pushFollow(FOLLOW_45);
                    lv_switch_5_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      							}
                      							set(
                      								current,
                      								"switch",
                      								lv_switch_5_0,
                      								"org.eclipse.xtext.xbase.Xbase.XExpression");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }

                    otherlv_6=(Token)match(input,21,FOLLOW_8); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_6, grammarAccess.getXSwitchExpressionAccess().getRightParenthesisKeyword_2_0_2());
                      				
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:3755:4: ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) )
                    {
                    // InternalCheckCfg.g:3755:4: ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) )
                    // InternalCheckCfg.g:3756:5: ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) )
                    {
                    // InternalCheckCfg.g:3756:5: ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )?
                    int alt63=2;
                    alt63 = dfa63.predict(input);
                    switch (alt63) {
                        case 1 :
                            // InternalCheckCfg.g:3757:6: ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' )
                            {
                            // InternalCheckCfg.g:3766:6: ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' )
                            // InternalCheckCfg.g:3767:7: ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':'
                            {
                            // InternalCheckCfg.g:3767:7: ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) )
                            // InternalCheckCfg.g:3768:8: (lv_declaredParam_7_0= ruleJvmFormalParameter )
                            {
                            // InternalCheckCfg.g:3768:8: (lv_declaredParam_7_0= ruleJvmFormalParameter )
                            // InternalCheckCfg.g:3769:9: lv_declaredParam_7_0= ruleJvmFormalParameter
                            {
                            if ( state.backtracking==0 ) {

                              									newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getDeclaredParamJvmFormalParameterParserRuleCall_2_1_0_0_0_0());
                              								
                            }
                            pushFollow(FOLLOW_59);
                            lv_declaredParam_7_0=ruleJvmFormalParameter();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              									if (current==null) {
                              										current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                              									}
                              									set(
                              										current,
                              										"declaredParam",
                              										lv_declaredParam_7_0,
                              										"org.eclipse.xtext.xbase.Xbase.JvmFormalParameter");
                              									afterParserOrEnumRuleCall();
                              								
                            }

                            }


                            }

                            otherlv_8=(Token)match(input,65,FOLLOW_20); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(otherlv_8, grammarAccess.getXSwitchExpressionAccess().getColonKeyword_2_1_0_0_1());
                              						
                            }

                            }


                            }
                            break;

                    }

                    // InternalCheckCfg.g:3792:5: ( (lv_switch_9_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3793:6: (lv_switch_9_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3793:6: (lv_switch_9_0= ruleXExpression )
                    // InternalCheckCfg.g:3794:7: lv_switch_9_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getSwitchXExpressionParserRuleCall_2_1_1_0());
                      						
                    }
                    pushFollow(FOLLOW_8);
                    lv_switch_9_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      							}
                      							set(
                      								current,
                      								"switch",
                      								lv_switch_9_0,
                      								"org.eclipse.xtext.xbase.Xbase.XExpression");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }


                    }


                    }
                    break;

            }

            otherlv_10=(Token)match(input,15,FOLLOW_60); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_10, grammarAccess.getXSwitchExpressionAccess().getLeftCurlyBracketKeyword_3());
              		
            }
            // InternalCheckCfg.g:3817:3: ( (lv_cases_11_0= ruleXCasePart ) )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==RULE_ID||(LA65_0>=19 && LA65_0<=20)||LA65_0==44||LA65_0==65||LA65_0==67) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // InternalCheckCfg.g:3818:4: (lv_cases_11_0= ruleXCasePart )
            	    {
            	    // InternalCheckCfg.g:3818:4: (lv_cases_11_0= ruleXCasePart )
            	    // InternalCheckCfg.g:3819:5: lv_cases_11_0= ruleXCasePart
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getCasesXCasePartParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_60);
            	    lv_cases_11_0=ruleXCasePart();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
            	      					}
            	      					add(
            	      						current,
            	      						"cases",
            	      						lv_cases_11_0,
            	      						"org.eclipse.xtext.xbase.Xbase.XCasePart");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);

            // InternalCheckCfg.g:3836:3: (otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==66) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // InternalCheckCfg.g:3837:4: otherlv_12= 'default' otherlv_13= ':' ( (lv_default_14_0= ruleXExpression ) )
                    {
                    otherlv_12=(Token)match(input,66,FOLLOW_59); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_12, grammarAccess.getXSwitchExpressionAccess().getDefaultKeyword_5_0());
                      			
                    }
                    otherlv_13=(Token)match(input,65,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_13, grammarAccess.getXSwitchExpressionAccess().getColonKeyword_5_1());
                      			
                    }
                    // InternalCheckCfg.g:3845:4: ( (lv_default_14_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3846:5: (lv_default_14_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3846:5: (lv_default_14_0= ruleXExpression )
                    // InternalCheckCfg.g:3847:6: lv_default_14_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXSwitchExpressionAccess().getDefaultXExpressionParserRuleCall_5_2_0());
                      					
                    }
                    pushFollow(FOLLOW_61);
                    lv_default_14_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXSwitchExpressionRule());
                      						}
                      						set(
                      							current,
                      							"default",
                      							lv_default_14_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            otherlv_15=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_15, grammarAccess.getXSwitchExpressionAccess().getRightCurlyBracketKeyword_6());
              		
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
    // $ANTLR end "ruleXSwitchExpression"


    // $ANTLR start "entryRuleXCasePart"
    // InternalCheckCfg.g:3873:1: entryRuleXCasePart returns [EObject current=null] : iv_ruleXCasePart= ruleXCasePart EOF ;
    public final EObject entryRuleXCasePart() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXCasePart = null;


        try {
            // InternalCheckCfg.g:3873:50: (iv_ruleXCasePart= ruleXCasePart EOF )
            // InternalCheckCfg.g:3874:2: iv_ruleXCasePart= ruleXCasePart EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXCasePartRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXCasePart=ruleXCasePart();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXCasePart; 
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
    // $ANTLR end "entryRuleXCasePart"


    // $ANTLR start "ruleXCasePart"
    // InternalCheckCfg.g:3880:1: ruleXCasePart returns [EObject current=null] : ( () ( (lv_typeGuard_1_0= ruleJvmTypeReference ) )? (otherlv_2= 'case' ( (lv_case_3_0= ruleXExpression ) ) )? ( (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) ) | ( (lv_fallThrough_6_0= ',' ) ) ) ) ;
    public final EObject ruleXCasePart() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_fallThrough_6_0=null;
        EObject lv_typeGuard_1_0 = null;

        EObject lv_case_3_0 = null;

        EObject lv_then_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3886:2: ( ( () ( (lv_typeGuard_1_0= ruleJvmTypeReference ) )? (otherlv_2= 'case' ( (lv_case_3_0= ruleXExpression ) ) )? ( (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) ) | ( (lv_fallThrough_6_0= ',' ) ) ) ) )
            // InternalCheckCfg.g:3887:2: ( () ( (lv_typeGuard_1_0= ruleJvmTypeReference ) )? (otherlv_2= 'case' ( (lv_case_3_0= ruleXExpression ) ) )? ( (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) ) | ( (lv_fallThrough_6_0= ',' ) ) ) )
            {
            // InternalCheckCfg.g:3887:2: ( () ( (lv_typeGuard_1_0= ruleJvmTypeReference ) )? (otherlv_2= 'case' ( (lv_case_3_0= ruleXExpression ) ) )? ( (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) ) | ( (lv_fallThrough_6_0= ',' ) ) ) )
            // InternalCheckCfg.g:3888:3: () ( (lv_typeGuard_1_0= ruleJvmTypeReference ) )? (otherlv_2= 'case' ( (lv_case_3_0= ruleXExpression ) ) )? ( (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) ) | ( (lv_fallThrough_6_0= ',' ) ) )
            {
            // InternalCheckCfg.g:3888:3: ()
            // InternalCheckCfg.g:3889:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXCasePartAccess().getXCasePartAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:3895:3: ( (lv_typeGuard_1_0= ruleJvmTypeReference ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==RULE_ID||LA67_0==19||LA67_0==44) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // InternalCheckCfg.g:3896:4: (lv_typeGuard_1_0= ruleJvmTypeReference )
                    {
                    // InternalCheckCfg.g:3896:4: (lv_typeGuard_1_0= ruleJvmTypeReference )
                    // InternalCheckCfg.g:3897:5: lv_typeGuard_1_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getXCasePartAccess().getTypeGuardJvmTypeReferenceParserRuleCall_1_0());
                      				
                    }
                    pushFollow(FOLLOW_62);
                    lv_typeGuard_1_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getXCasePartRule());
                      					}
                      					set(
                      						current,
                      						"typeGuard",
                      						lv_typeGuard_1_0,
                      						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCheckCfg.g:3914:3: (otherlv_2= 'case' ( (lv_case_3_0= ruleXExpression ) ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==67) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalCheckCfg.g:3915:4: otherlv_2= 'case' ( (lv_case_3_0= ruleXExpression ) )
                    {
                    otherlv_2=(Token)match(input,67,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getXCasePartAccess().getCaseKeyword_2_0());
                      			
                    }
                    // InternalCheckCfg.g:3919:4: ( (lv_case_3_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3920:5: (lv_case_3_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3920:5: (lv_case_3_0= ruleXExpression )
                    // InternalCheckCfg.g:3921:6: lv_case_3_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXCasePartAccess().getCaseXExpressionParserRuleCall_2_1_0());
                      					
                    }
                    pushFollow(FOLLOW_63);
                    lv_case_3_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXCasePartRule());
                      						}
                      						set(
                      							current,
                      							"case",
                      							lv_case_3_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalCheckCfg.g:3939:3: ( (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) ) | ( (lv_fallThrough_6_0= ',' ) ) )
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==65) ) {
                alt69=1;
            }
            else if ( (LA69_0==20) ) {
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
                    // InternalCheckCfg.g:3940:4: (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) )
                    {
                    // InternalCheckCfg.g:3940:4: (otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) ) )
                    // InternalCheckCfg.g:3941:5: otherlv_4= ':' ( (lv_then_5_0= ruleXExpression ) )
                    {
                    otherlv_4=(Token)match(input,65,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_4, grammarAccess.getXCasePartAccess().getColonKeyword_3_0_0());
                      				
                    }
                    // InternalCheckCfg.g:3945:5: ( (lv_then_5_0= ruleXExpression ) )
                    // InternalCheckCfg.g:3946:6: (lv_then_5_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:3946:6: (lv_then_5_0= ruleXExpression )
                    // InternalCheckCfg.g:3947:7: lv_then_5_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getXCasePartAccess().getThenXExpressionParserRuleCall_3_0_1_0());
                      						
                    }
                    pushFollow(FOLLOW_2);
                    lv_then_5_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getXCasePartRule());
                      							}
                      							set(
                      								current,
                      								"then",
                      								lv_then_5_0,
                      								"org.eclipse.xtext.xbase.Xbase.XExpression");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:3966:4: ( (lv_fallThrough_6_0= ',' ) )
                    {
                    // InternalCheckCfg.g:3966:4: ( (lv_fallThrough_6_0= ',' ) )
                    // InternalCheckCfg.g:3967:5: (lv_fallThrough_6_0= ',' )
                    {
                    // InternalCheckCfg.g:3967:5: (lv_fallThrough_6_0= ',' )
                    // InternalCheckCfg.g:3968:6: lv_fallThrough_6_0= ','
                    {
                    lv_fallThrough_6_0=(Token)match(input,20,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_fallThrough_6_0, grammarAccess.getXCasePartAccess().getFallThroughCommaKeyword_3_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXCasePartRule());
                      						}
                      						setWithLastConsumed(current, "fallThrough", lv_fallThrough_6_0 != null, ",");
                      					
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
    // $ANTLR end "ruleXCasePart"


    // $ANTLR start "entryRuleXForLoopExpression"
    // InternalCheckCfg.g:3985:1: entryRuleXForLoopExpression returns [EObject current=null] : iv_ruleXForLoopExpression= ruleXForLoopExpression EOF ;
    public final EObject entryRuleXForLoopExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXForLoopExpression = null;


        try {
            // InternalCheckCfg.g:3985:59: (iv_ruleXForLoopExpression= ruleXForLoopExpression EOF )
            // InternalCheckCfg.g:3986:2: iv_ruleXForLoopExpression= ruleXForLoopExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXForLoopExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXForLoopExpression=ruleXForLoopExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXForLoopExpression; 
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
    // $ANTLR end "entryRuleXForLoopExpression"


    // $ANTLR start "ruleXForLoopExpression"
    // InternalCheckCfg.g:3992:1: ruleXForLoopExpression returns [EObject current=null] : ( ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) ) ;
    public final EObject ruleXForLoopExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_declaredParam_3_0 = null;

        EObject lv_forExpression_5_0 = null;

        EObject lv_eachExpression_7_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:3998:2: ( ( ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) ) )
            // InternalCheckCfg.g:3999:2: ( ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) )
            {
            // InternalCheckCfg.g:3999:2: ( ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) ) )
            // InternalCheckCfg.g:4000:3: ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_forExpression_5_0= ruleXExpression ) ) otherlv_6= ')' ( (lv_eachExpression_7_0= ruleXExpression ) )
            {
            // InternalCheckCfg.g:4000:3: ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) )
            // InternalCheckCfg.g:4001:4: ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' )
            {
            // InternalCheckCfg.g:4014:4: ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' )
            // InternalCheckCfg.g:4015:5: () otherlv_1= 'for' otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':'
            {
            // InternalCheckCfg.g:4015:5: ()
            // InternalCheckCfg.g:4016:6: 
            {
            if ( state.backtracking==0 ) {

              						current = forceCreateModelElement(
              							grammarAccess.getXForLoopExpressionAccess().getXForLoopExpressionAction_0_0_0(),
              							current);
              					
            }

            }

            otherlv_1=(Token)match(input,17,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getXForLoopExpressionAccess().getForKeyword_0_0_1());
              				
            }
            otherlv_2=(Token)match(input,19,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_2, grammarAccess.getXForLoopExpressionAccess().getLeftParenthesisKeyword_0_0_2());
              				
            }
            // InternalCheckCfg.g:4030:5: ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) )
            // InternalCheckCfg.g:4031:6: (lv_declaredParam_3_0= ruleJvmFormalParameter )
            {
            // InternalCheckCfg.g:4031:6: (lv_declaredParam_3_0= ruleJvmFormalParameter )
            // InternalCheckCfg.g:4032:7: lv_declaredParam_3_0= ruleJvmFormalParameter
            {
            if ( state.backtracking==0 ) {

              							newCompositeNode(grammarAccess.getXForLoopExpressionAccess().getDeclaredParamJvmFormalParameterParserRuleCall_0_0_3_0());
              						
            }
            pushFollow(FOLLOW_59);
            lv_declaredParam_3_0=ruleJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              							if (current==null) {
              								current = createModelElementForParent(grammarAccess.getXForLoopExpressionRule());
              							}
              							set(
              								current,
              								"declaredParam",
              								lv_declaredParam_3_0,
              								"org.eclipse.xtext.xbase.Xbase.JvmFormalParameter");
              							afterParserOrEnumRuleCall();
              						
            }

            }


            }

            otherlv_4=(Token)match(input,65,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_4, grammarAccess.getXForLoopExpressionAccess().getColonKeyword_0_0_4());
              				
            }

            }


            }

            // InternalCheckCfg.g:4055:3: ( (lv_forExpression_5_0= ruleXExpression ) )
            // InternalCheckCfg.g:4056:4: (lv_forExpression_5_0= ruleXExpression )
            {
            // InternalCheckCfg.g:4056:4: (lv_forExpression_5_0= ruleXExpression )
            // InternalCheckCfg.g:4057:5: lv_forExpression_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXForLoopExpressionAccess().getForExpressionXExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_45);
            lv_forExpression_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXForLoopExpressionRule());
              					}
              					set(
              						current,
              						"forExpression",
              						lv_forExpression_5_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_6=(Token)match(input,21,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getXForLoopExpressionAccess().getRightParenthesisKeyword_2());
              		
            }
            // InternalCheckCfg.g:4078:3: ( (lv_eachExpression_7_0= ruleXExpression ) )
            // InternalCheckCfg.g:4079:4: (lv_eachExpression_7_0= ruleXExpression )
            {
            // InternalCheckCfg.g:4079:4: (lv_eachExpression_7_0= ruleXExpression )
            // InternalCheckCfg.g:4080:5: lv_eachExpression_7_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXForLoopExpressionAccess().getEachExpressionXExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_eachExpression_7_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXForLoopExpressionRule());
              					}
              					set(
              						current,
              						"eachExpression",
              						lv_eachExpression_7_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXForLoopExpression"


    // $ANTLR start "entryRuleXBasicForLoopExpression"
    // InternalCheckCfg.g:4101:1: entryRuleXBasicForLoopExpression returns [EObject current=null] : iv_ruleXBasicForLoopExpression= ruleXBasicForLoopExpression EOF ;
    public final EObject entryRuleXBasicForLoopExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXBasicForLoopExpression = null;


        try {
            // InternalCheckCfg.g:4101:64: (iv_ruleXBasicForLoopExpression= ruleXBasicForLoopExpression EOF )
            // InternalCheckCfg.g:4102:2: iv_ruleXBasicForLoopExpression= ruleXBasicForLoopExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXBasicForLoopExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXBasicForLoopExpression=ruleXBasicForLoopExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXBasicForLoopExpression; 
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
    // $ANTLR end "entryRuleXBasicForLoopExpression"


    // $ANTLR start "ruleXBasicForLoopExpression"
    // InternalCheckCfg.g:4108:1: ruleXBasicForLoopExpression returns [EObject current=null] : ( () otherlv_1= 'for' otherlv_2= '(' ( ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )* )? otherlv_6= ';' ( (lv_expression_7_0= ruleXExpression ) )? otherlv_8= ';' ( ( (lv_updateExpressions_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )* )? otherlv_12= ')' ( (lv_eachExpression_13_0= ruleXExpression ) ) ) ;
    public final EObject ruleXBasicForLoopExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_initExpressions_3_0 = null;

        EObject lv_initExpressions_5_0 = null;

        EObject lv_expression_7_0 = null;

        EObject lv_updateExpressions_9_0 = null;

        EObject lv_updateExpressions_11_0 = null;

        EObject lv_eachExpression_13_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4114:2: ( ( () otherlv_1= 'for' otherlv_2= '(' ( ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )* )? otherlv_6= ';' ( (lv_expression_7_0= ruleXExpression ) )? otherlv_8= ';' ( ( (lv_updateExpressions_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )* )? otherlv_12= ')' ( (lv_eachExpression_13_0= ruleXExpression ) ) ) )
            // InternalCheckCfg.g:4115:2: ( () otherlv_1= 'for' otherlv_2= '(' ( ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )* )? otherlv_6= ';' ( (lv_expression_7_0= ruleXExpression ) )? otherlv_8= ';' ( ( (lv_updateExpressions_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )* )? otherlv_12= ')' ( (lv_eachExpression_13_0= ruleXExpression ) ) )
            {
            // InternalCheckCfg.g:4115:2: ( () otherlv_1= 'for' otherlv_2= '(' ( ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )* )? otherlv_6= ';' ( (lv_expression_7_0= ruleXExpression ) )? otherlv_8= ';' ( ( (lv_updateExpressions_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )* )? otherlv_12= ')' ( (lv_eachExpression_13_0= ruleXExpression ) ) )
            // InternalCheckCfg.g:4116:3: () otherlv_1= 'for' otherlv_2= '(' ( ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )* )? otherlv_6= ';' ( (lv_expression_7_0= ruleXExpression ) )? otherlv_8= ';' ( ( (lv_updateExpressions_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )* )? otherlv_12= ')' ( (lv_eachExpression_13_0= ruleXExpression ) )
            {
            // InternalCheckCfg.g:4116:3: ()
            // InternalCheckCfg.g:4117:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXBasicForLoopExpressionAccess().getXBasicForLoopExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,17,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXBasicForLoopExpressionAccess().getForKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,19,FOLLOW_64); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXBasicForLoopExpressionAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalCheckCfg.g:4131:3: ( ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )* )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( ((LA71_0>=RULE_STRING && LA71_0<=RULE_ID)||LA71_0==15||LA71_0==17||LA71_0==19||(LA71_0>=23 && LA71_0<=24)||LA71_0==31||(LA71_0>=47 && LA71_0<=48)||LA71_0==53||LA71_0==62||LA71_0==64||(LA71_0>=68 && LA71_0<=84)||LA71_0==86) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // InternalCheckCfg.g:4132:4: ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )*
                    {
                    // InternalCheckCfg.g:4132:4: ( (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration ) )
                    // InternalCheckCfg.g:4133:5: (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration )
                    {
                    // InternalCheckCfg.g:4133:5: (lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration )
                    // InternalCheckCfg.g:4134:6: lv_initExpressions_3_0= ruleXExpressionOrVarDeclaration
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXBasicForLoopExpressionAccess().getInitExpressionsXExpressionOrVarDeclarationParserRuleCall_3_0_0());
                      					
                    }
                    pushFollow(FOLLOW_65);
                    lv_initExpressions_3_0=ruleXExpressionOrVarDeclaration();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXBasicForLoopExpressionRule());
                      						}
                      						add(
                      							current,
                      							"initExpressions",
                      							lv_initExpressions_3_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpressionOrVarDeclaration");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:4151:4: (otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) ) )*
                    loop70:
                    do {
                        int alt70=2;
                        int LA70_0 = input.LA(1);

                        if ( (LA70_0==20) ) {
                            alt70=1;
                        }


                        switch (alt70) {
                    	case 1 :
                    	    // InternalCheckCfg.g:4152:5: otherlv_4= ',' ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) )
                    	    {
                    	    otherlv_4=(Token)match(input,20,FOLLOW_66); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_4, grammarAccess.getXBasicForLoopExpressionAccess().getCommaKeyword_3_1_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:4156:5: ( (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration ) )
                    	    // InternalCheckCfg.g:4157:6: (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration )
                    	    {
                    	    // InternalCheckCfg.g:4157:6: (lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration )
                    	    // InternalCheckCfg.g:4158:7: lv_initExpressions_5_0= ruleXExpressionOrVarDeclaration
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXBasicForLoopExpressionAccess().getInitExpressionsXExpressionOrVarDeclarationParserRuleCall_3_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_65);
                    	    lv_initExpressions_5_0=ruleXExpressionOrVarDeclaration();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXBasicForLoopExpressionRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"initExpressions",
                    	      								lv_initExpressions_5_0,
                    	      								"org.eclipse.xtext.xbase.Xbase.XExpressionOrVarDeclaration");
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
                    break;

            }

            otherlv_6=(Token)match(input,61,FOLLOW_67); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getXBasicForLoopExpressionAccess().getSemicolonKeyword_4());
              		
            }
            // InternalCheckCfg.g:4181:3: ( (lv_expression_7_0= ruleXExpression ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( ((LA72_0>=RULE_STRING && LA72_0<=RULE_ID)||LA72_0==15||LA72_0==17||LA72_0==19||(LA72_0>=23 && LA72_0<=24)||LA72_0==31||(LA72_0>=47 && LA72_0<=48)||LA72_0==53||LA72_0==62||LA72_0==64||(LA72_0>=68 && LA72_0<=69)||(LA72_0>=72 && LA72_0<=84)||LA72_0==86) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // InternalCheckCfg.g:4182:4: (lv_expression_7_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:4182:4: (lv_expression_7_0= ruleXExpression )
                    // InternalCheckCfg.g:4183:5: lv_expression_7_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getXBasicForLoopExpressionAccess().getExpressionXExpressionParserRuleCall_5_0());
                      				
                    }
                    pushFollow(FOLLOW_68);
                    lv_expression_7_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getXBasicForLoopExpressionRule());
                      					}
                      					set(
                      						current,
                      						"expression",
                      						lv_expression_7_0,
                      						"org.eclipse.xtext.xbase.Xbase.XExpression");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,61,FOLLOW_69); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_8, grammarAccess.getXBasicForLoopExpressionAccess().getSemicolonKeyword_6());
              		
            }
            // InternalCheckCfg.g:4204:3: ( ( (lv_updateExpressions_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )* )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( ((LA74_0>=RULE_STRING && LA74_0<=RULE_ID)||LA74_0==15||LA74_0==17||LA74_0==19||(LA74_0>=23 && LA74_0<=24)||LA74_0==31||(LA74_0>=47 && LA74_0<=48)||LA74_0==53||LA74_0==62||LA74_0==64||(LA74_0>=68 && LA74_0<=69)||(LA74_0>=72 && LA74_0<=84)||LA74_0==86) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // InternalCheckCfg.g:4205:4: ( (lv_updateExpressions_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )*
                    {
                    // InternalCheckCfg.g:4205:4: ( (lv_updateExpressions_9_0= ruleXExpression ) )
                    // InternalCheckCfg.g:4206:5: (lv_updateExpressions_9_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:4206:5: (lv_updateExpressions_9_0= ruleXExpression )
                    // InternalCheckCfg.g:4207:6: lv_updateExpressions_9_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXBasicForLoopExpressionAccess().getUpdateExpressionsXExpressionParserRuleCall_7_0_0());
                      					
                    }
                    pushFollow(FOLLOW_13);
                    lv_updateExpressions_9_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXBasicForLoopExpressionRule());
                      						}
                      						add(
                      							current,
                      							"updateExpressions",
                      							lv_updateExpressions_9_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpression");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:4224:4: (otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) ) )*
                    loop73:
                    do {
                        int alt73=2;
                        int LA73_0 = input.LA(1);

                        if ( (LA73_0==20) ) {
                            alt73=1;
                        }


                        switch (alt73) {
                    	case 1 :
                    	    // InternalCheckCfg.g:4225:5: otherlv_10= ',' ( (lv_updateExpressions_11_0= ruleXExpression ) )
                    	    {
                    	    otherlv_10=(Token)match(input,20,FOLLOW_20); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_10, grammarAccess.getXBasicForLoopExpressionAccess().getCommaKeyword_7_1_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:4229:5: ( (lv_updateExpressions_11_0= ruleXExpression ) )
                    	    // InternalCheckCfg.g:4230:6: (lv_updateExpressions_11_0= ruleXExpression )
                    	    {
                    	    // InternalCheckCfg.g:4230:6: (lv_updateExpressions_11_0= ruleXExpression )
                    	    // InternalCheckCfg.g:4231:7: lv_updateExpressions_11_0= ruleXExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXBasicForLoopExpressionAccess().getUpdateExpressionsXExpressionParserRuleCall_7_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_13);
                    	    lv_updateExpressions_11_0=ruleXExpression();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXBasicForLoopExpressionRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"updateExpressions",
                    	      								lv_updateExpressions_11_0,
                    	      								"org.eclipse.xtext.xbase.Xbase.XExpression");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop73;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_12=(Token)match(input,21,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_12, grammarAccess.getXBasicForLoopExpressionAccess().getRightParenthesisKeyword_8());
              		
            }
            // InternalCheckCfg.g:4254:3: ( (lv_eachExpression_13_0= ruleXExpression ) )
            // InternalCheckCfg.g:4255:4: (lv_eachExpression_13_0= ruleXExpression )
            {
            // InternalCheckCfg.g:4255:4: (lv_eachExpression_13_0= ruleXExpression )
            // InternalCheckCfg.g:4256:5: lv_eachExpression_13_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXBasicForLoopExpressionAccess().getEachExpressionXExpressionParserRuleCall_9_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_eachExpression_13_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXBasicForLoopExpressionRule());
              					}
              					set(
              						current,
              						"eachExpression",
              						lv_eachExpression_13_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXBasicForLoopExpression"


    // $ANTLR start "entryRuleXWhileExpression"
    // InternalCheckCfg.g:4277:1: entryRuleXWhileExpression returns [EObject current=null] : iv_ruleXWhileExpression= ruleXWhileExpression EOF ;
    public final EObject entryRuleXWhileExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileExpression = null;


        try {
            // InternalCheckCfg.g:4277:57: (iv_ruleXWhileExpression= ruleXWhileExpression EOF )
            // InternalCheckCfg.g:4278:2: iv_ruleXWhileExpression= ruleXWhileExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXWhileExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXWhileExpression=ruleXWhileExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXWhileExpression; 
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
    // $ANTLR end "entryRuleXWhileExpression"


    // $ANTLR start "ruleXWhileExpression"
    // InternalCheckCfg.g:4284:1: ruleXWhileExpression returns [EObject current=null] : ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) ) ;
    public final EObject ruleXWhileExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_predicate_3_0 = null;

        EObject lv_body_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4290:2: ( ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) ) )
            // InternalCheckCfg.g:4291:2: ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) )
            {
            // InternalCheckCfg.g:4291:2: ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) ) )
            // InternalCheckCfg.g:4292:3: () otherlv_1= 'while' otherlv_2= '(' ( (lv_predicate_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_body_5_0= ruleXExpression ) )
            {
            // InternalCheckCfg.g:4292:3: ()
            // InternalCheckCfg.g:4293:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXWhileExpressionAccess().getXWhileExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,68,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXWhileExpressionAccess().getWhileKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,19,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXWhileExpressionAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalCheckCfg.g:4307:3: ( (lv_predicate_3_0= ruleXExpression ) )
            // InternalCheckCfg.g:4308:4: (lv_predicate_3_0= ruleXExpression )
            {
            // InternalCheckCfg.g:4308:4: (lv_predicate_3_0= ruleXExpression )
            // InternalCheckCfg.g:4309:5: lv_predicate_3_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXWhileExpressionAccess().getPredicateXExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_45);
            lv_predicate_3_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXWhileExpressionRule());
              					}
              					set(
              						current,
              						"predicate",
              						lv_predicate_3_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,21,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getXWhileExpressionAccess().getRightParenthesisKeyword_4());
              		
            }
            // InternalCheckCfg.g:4330:3: ( (lv_body_5_0= ruleXExpression ) )
            // InternalCheckCfg.g:4331:4: (lv_body_5_0= ruleXExpression )
            {
            // InternalCheckCfg.g:4331:4: (lv_body_5_0= ruleXExpression )
            // InternalCheckCfg.g:4332:5: lv_body_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXWhileExpressionAccess().getBodyXExpressionParserRuleCall_5_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_body_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXWhileExpressionRule());
              					}
              					set(
              						current,
              						"body",
              						lv_body_5_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXWhileExpression"


    // $ANTLR start "entryRuleXDoWhileExpression"
    // InternalCheckCfg.g:4353:1: entryRuleXDoWhileExpression returns [EObject current=null] : iv_ruleXDoWhileExpression= ruleXDoWhileExpression EOF ;
    public final EObject entryRuleXDoWhileExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXDoWhileExpression = null;


        try {
            // InternalCheckCfg.g:4353:59: (iv_ruleXDoWhileExpression= ruleXDoWhileExpression EOF )
            // InternalCheckCfg.g:4354:2: iv_ruleXDoWhileExpression= ruleXDoWhileExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXDoWhileExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXDoWhileExpression=ruleXDoWhileExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXDoWhileExpression; 
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
    // $ANTLR end "entryRuleXDoWhileExpression"


    // $ANTLR start "ruleXDoWhileExpression"
    // InternalCheckCfg.g:4360:1: ruleXDoWhileExpression returns [EObject current=null] : ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' ) ;
    public final EObject ruleXDoWhileExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_body_2_0 = null;

        EObject lv_predicate_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4366:2: ( ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' ) )
            // InternalCheckCfg.g:4367:2: ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' )
            {
            // InternalCheckCfg.g:4367:2: ( () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')' )
            // InternalCheckCfg.g:4368:3: () otherlv_1= 'do' ( (lv_body_2_0= ruleXExpression ) ) otherlv_3= 'while' otherlv_4= '(' ( (lv_predicate_5_0= ruleXExpression ) ) otherlv_6= ')'
            {
            // InternalCheckCfg.g:4368:3: ()
            // InternalCheckCfg.g:4369:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXDoWhileExpressionAccess().getXDoWhileExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,69,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXDoWhileExpressionAccess().getDoKeyword_1());
              		
            }
            // InternalCheckCfg.g:4379:3: ( (lv_body_2_0= ruleXExpression ) )
            // InternalCheckCfg.g:4380:4: (lv_body_2_0= ruleXExpression )
            {
            // InternalCheckCfg.g:4380:4: (lv_body_2_0= ruleXExpression )
            // InternalCheckCfg.g:4381:5: lv_body_2_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXDoWhileExpressionAccess().getBodyXExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_70);
            lv_body_2_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXDoWhileExpressionRule());
              					}
              					set(
              						current,
              						"body",
              						lv_body_2_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,68,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getXDoWhileExpressionAccess().getWhileKeyword_3());
              		
            }
            otherlv_4=(Token)match(input,19,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getXDoWhileExpressionAccess().getLeftParenthesisKeyword_4());
              		
            }
            // InternalCheckCfg.g:4406:3: ( (lv_predicate_5_0= ruleXExpression ) )
            // InternalCheckCfg.g:4407:4: (lv_predicate_5_0= ruleXExpression )
            {
            // InternalCheckCfg.g:4407:4: (lv_predicate_5_0= ruleXExpression )
            // InternalCheckCfg.g:4408:5: lv_predicate_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXDoWhileExpressionAccess().getPredicateXExpressionParserRuleCall_5_0());
              				
            }
            pushFollow(FOLLOW_45);
            lv_predicate_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXDoWhileExpressionRule());
              					}
              					set(
              						current,
              						"predicate",
              						lv_predicate_5_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_6=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getXDoWhileExpressionAccess().getRightParenthesisKeyword_6());
              		
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
    // $ANTLR end "ruleXDoWhileExpression"


    // $ANTLR start "entryRuleXBlockExpression"
    // InternalCheckCfg.g:4433:1: entryRuleXBlockExpression returns [EObject current=null] : iv_ruleXBlockExpression= ruleXBlockExpression EOF ;
    public final EObject entryRuleXBlockExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXBlockExpression = null;


        try {
            // InternalCheckCfg.g:4433:57: (iv_ruleXBlockExpression= ruleXBlockExpression EOF )
            // InternalCheckCfg.g:4434:2: iv_ruleXBlockExpression= ruleXBlockExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXBlockExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXBlockExpression=ruleXBlockExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXBlockExpression; 
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
    // $ANTLR end "entryRuleXBlockExpression"


    // $ANTLR start "ruleXBlockExpression"
    // InternalCheckCfg.g:4440:1: ruleXBlockExpression returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_3= ';' )? )* otherlv_4= '}' ) ;
    public final EObject ruleXBlockExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_expressions_2_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4446:2: ( ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_3= ';' )? )* otherlv_4= '}' ) )
            // InternalCheckCfg.g:4447:2: ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_3= ';' )? )* otherlv_4= '}' )
            {
            // InternalCheckCfg.g:4447:2: ( () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_3= ';' )? )* otherlv_4= '}' )
            // InternalCheckCfg.g:4448:3: () otherlv_1= '{' ( ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_3= ';' )? )* otherlv_4= '}'
            {
            // InternalCheckCfg.g:4448:3: ()
            // InternalCheckCfg.g:4449:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXBlockExpressionAccess().getXBlockExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,15,FOLLOW_71); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXBlockExpressionAccess().getLeftCurlyBracketKeyword_1());
              		
            }
            // InternalCheckCfg.g:4459:3: ( ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_3= ';' )? )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( ((LA76_0>=RULE_STRING && LA76_0<=RULE_ID)||LA76_0==15||LA76_0==17||LA76_0==19||(LA76_0>=23 && LA76_0<=24)||LA76_0==31||(LA76_0>=47 && LA76_0<=48)||LA76_0==53||LA76_0==62||LA76_0==64||(LA76_0>=68 && LA76_0<=84)||LA76_0==86) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // InternalCheckCfg.g:4460:4: ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) ) (otherlv_3= ';' )?
            	    {
            	    // InternalCheckCfg.g:4460:4: ( (lv_expressions_2_0= ruleXExpressionOrVarDeclaration ) )
            	    // InternalCheckCfg.g:4461:5: (lv_expressions_2_0= ruleXExpressionOrVarDeclaration )
            	    {
            	    // InternalCheckCfg.g:4461:5: (lv_expressions_2_0= ruleXExpressionOrVarDeclaration )
            	    // InternalCheckCfg.g:4462:6: lv_expressions_2_0= ruleXExpressionOrVarDeclaration
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getXBlockExpressionAccess().getExpressionsXExpressionOrVarDeclarationParserRuleCall_2_0_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_72);
            	    lv_expressions_2_0=ruleXExpressionOrVarDeclaration();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      						if (current==null) {
            	      							current = createModelElementForParent(grammarAccess.getXBlockExpressionRule());
            	      						}
            	      						add(
            	      							current,
            	      							"expressions",
            	      							lv_expressions_2_0,
            	      							"org.eclipse.xtext.xbase.Xbase.XExpressionOrVarDeclaration");
            	      						afterParserOrEnumRuleCall();
            	      					
            	    }

            	    }


            	    }

            	    // InternalCheckCfg.g:4479:4: (otherlv_3= ';' )?
            	    int alt75=2;
            	    int LA75_0 = input.LA(1);

            	    if ( (LA75_0==61) ) {
            	        alt75=1;
            	    }
            	    switch (alt75) {
            	        case 1 :
            	            // InternalCheckCfg.g:4480:5: otherlv_3= ';'
            	            {
            	            otherlv_3=(Token)match(input,61,FOLLOW_71); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              					newLeafNode(otherlv_3, grammarAccess.getXBlockExpressionAccess().getSemicolonKeyword_2_1());
            	              				
            	            }

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);

            otherlv_4=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getXBlockExpressionAccess().getRightCurlyBracketKeyword_3());
              		
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
    // $ANTLR end "ruleXBlockExpression"


    // $ANTLR start "entryRuleXExpressionOrVarDeclaration"
    // InternalCheckCfg.g:4494:1: entryRuleXExpressionOrVarDeclaration returns [EObject current=null] : iv_ruleXExpressionOrVarDeclaration= ruleXExpressionOrVarDeclaration EOF ;
    public final EObject entryRuleXExpressionOrVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionOrVarDeclaration = null;


        try {
            // InternalCheckCfg.g:4494:68: (iv_ruleXExpressionOrVarDeclaration= ruleXExpressionOrVarDeclaration EOF )
            // InternalCheckCfg.g:4495:2: iv_ruleXExpressionOrVarDeclaration= ruleXExpressionOrVarDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXExpressionOrVarDeclarationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXExpressionOrVarDeclaration=ruleXExpressionOrVarDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXExpressionOrVarDeclaration; 
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
    // $ANTLR end "entryRuleXExpressionOrVarDeclaration"


    // $ANTLR start "ruleXExpressionOrVarDeclaration"
    // InternalCheckCfg.g:4501:1: ruleXExpressionOrVarDeclaration returns [EObject current=null] : (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression ) ;
    public final EObject ruleXExpressionOrVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject this_XVariableDeclaration_0 = null;

        EObject this_XExpression_1 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4507:2: ( (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression ) )
            // InternalCheckCfg.g:4508:2: (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression )
            {
            // InternalCheckCfg.g:4508:2: (this_XVariableDeclaration_0= ruleXVariableDeclaration | this_XExpression_1= ruleXExpression )
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( ((LA77_0>=70 && LA77_0<=71)) ) {
                alt77=1;
            }
            else if ( ((LA77_0>=RULE_STRING && LA77_0<=RULE_ID)||LA77_0==15||LA77_0==17||LA77_0==19||(LA77_0>=23 && LA77_0<=24)||LA77_0==31||(LA77_0>=47 && LA77_0<=48)||LA77_0==53||LA77_0==62||LA77_0==64||(LA77_0>=68 && LA77_0<=69)||(LA77_0>=72 && LA77_0<=84)||LA77_0==86) ) {
                alt77=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 77, 0, input);

                throw nvae;
            }
            switch (alt77) {
                case 1 :
                    // InternalCheckCfg.g:4509:3: this_XVariableDeclaration_0= ruleXVariableDeclaration
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXExpressionOrVarDeclarationAccess().getXVariableDeclarationParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XVariableDeclaration_0=ruleXVariableDeclaration();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XVariableDeclaration_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:4518:3: this_XExpression_1= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getXExpressionOrVarDeclarationAccess().getXExpressionParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XExpression_1=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XExpression_1;
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
    // $ANTLR end "ruleXExpressionOrVarDeclaration"


    // $ANTLR start "entryRuleXVariableDeclaration"
    // InternalCheckCfg.g:4530:1: entryRuleXVariableDeclaration returns [EObject current=null] : iv_ruleXVariableDeclaration= ruleXVariableDeclaration EOF ;
    public final EObject entryRuleXVariableDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVariableDeclaration = null;


        try {
            // InternalCheckCfg.g:4530:61: (iv_ruleXVariableDeclaration= ruleXVariableDeclaration EOF )
            // InternalCheckCfg.g:4531:2: iv_ruleXVariableDeclaration= ruleXVariableDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXVariableDeclarationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXVariableDeclaration=ruleXVariableDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXVariableDeclaration; 
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
    // $ANTLR end "entryRuleXVariableDeclaration"


    // $ANTLR start "ruleXVariableDeclaration"
    // InternalCheckCfg.g:4537:1: ruleXVariableDeclaration returns [EObject current=null] : ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? ) ;
    public final EObject ruleXVariableDeclaration() throws RecognitionException {
        EObject current = null;

        Token lv_writeable_1_0=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        EObject lv_type_3_0 = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_name_5_0 = null;

        EObject lv_right_7_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4543:2: ( ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? ) )
            // InternalCheckCfg.g:4544:2: ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? )
            {
            // InternalCheckCfg.g:4544:2: ( () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )? )
            // InternalCheckCfg.g:4545:3: () ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' ) ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) ) (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )?
            {
            // InternalCheckCfg.g:4545:3: ()
            // InternalCheckCfg.g:4546:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXVariableDeclarationAccess().getXVariableDeclarationAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:4552:3: ( ( (lv_writeable_1_0= 'var' ) ) | otherlv_2= 'val' )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==70) ) {
                alt78=1;
            }
            else if ( (LA78_0==71) ) {
                alt78=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }
            switch (alt78) {
                case 1 :
                    // InternalCheckCfg.g:4553:4: ( (lv_writeable_1_0= 'var' ) )
                    {
                    // InternalCheckCfg.g:4553:4: ( (lv_writeable_1_0= 'var' ) )
                    // InternalCheckCfg.g:4554:5: (lv_writeable_1_0= 'var' )
                    {
                    // InternalCheckCfg.g:4554:5: (lv_writeable_1_0= 'var' )
                    // InternalCheckCfg.g:4555:6: lv_writeable_1_0= 'var'
                    {
                    lv_writeable_1_0=(Token)match(input,70,FOLLOW_29); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_writeable_1_0, grammarAccess.getXVariableDeclarationAccess().getWriteableVarKeyword_1_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXVariableDeclarationRule());
                      						}
                      						setWithLastConsumed(current, "writeable", lv_writeable_1_0 != null, "var");
                      					
                    }

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:4568:4: otherlv_2= 'val'
                    {
                    otherlv_2=(Token)match(input,71,FOLLOW_29); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getXVariableDeclarationAccess().getValKeyword_1_1());
                      			
                    }

                    }
                    break;

            }

            // InternalCheckCfg.g:4573:3: ( ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) ) | ( (lv_name_5_0= ruleValidID ) ) )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==RULE_ID) ) {
                int LA79_1 = input.LA(2);

                if ( (synpred29_InternalCheckCfg()) ) {
                    alt79=1;
                }
                else if ( (true) ) {
                    alt79=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 79, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA79_0==19) && (synpred29_InternalCheckCfg())) {
                alt79=1;
            }
            else if ( (LA79_0==44) && (synpred29_InternalCheckCfg())) {
                alt79=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalCheckCfg.g:4574:4: ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) )
                    {
                    // InternalCheckCfg.g:4574:4: ( ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) ) )
                    // InternalCheckCfg.g:4575:5: ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )=> ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) )
                    {
                    // InternalCheckCfg.g:4588:5: ( ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) ) )
                    // InternalCheckCfg.g:4589:6: ( (lv_type_3_0= ruleJvmTypeReference ) ) ( (lv_name_4_0= ruleValidID ) )
                    {
                    // InternalCheckCfg.g:4589:6: ( (lv_type_3_0= ruleJvmTypeReference ) )
                    // InternalCheckCfg.g:4590:7: (lv_type_3_0= ruleJvmTypeReference )
                    {
                    // InternalCheckCfg.g:4590:7: (lv_type_3_0= ruleJvmTypeReference )
                    // InternalCheckCfg.g:4591:8: lv_type_3_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      								newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getTypeJvmTypeReferenceParserRuleCall_2_0_0_0_0());
                      							
                    }
                    pushFollow(FOLLOW_4);
                    lv_type_3_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      								if (current==null) {
                      									current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      								}
                      								set(
                      									current,
                      									"type",
                      									lv_type_3_0,
                      									"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
                      								afterParserOrEnumRuleCall();
                      							
                    }

                    }


                    }

                    // InternalCheckCfg.g:4608:6: ( (lv_name_4_0= ruleValidID ) )
                    // InternalCheckCfg.g:4609:7: (lv_name_4_0= ruleValidID )
                    {
                    // InternalCheckCfg.g:4609:7: (lv_name_4_0= ruleValidID )
                    // InternalCheckCfg.g:4610:8: lv_name_4_0= ruleValidID
                    {
                    if ( state.backtracking==0 ) {

                      								newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getNameValidIDParserRuleCall_2_0_0_1_0());
                      							
                    }
                    pushFollow(FOLLOW_73);
                    lv_name_4_0=ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      								if (current==null) {
                      									current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      								}
                      								set(
                      									current,
                      									"name",
                      									lv_name_4_0,
                      									"org.eclipse.xtext.xbase.Xtype.ValidID");
                      								afterParserOrEnumRuleCall();
                      							
                    }

                    }


                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:4630:4: ( (lv_name_5_0= ruleValidID ) )
                    {
                    // InternalCheckCfg.g:4630:4: ( (lv_name_5_0= ruleValidID ) )
                    // InternalCheckCfg.g:4631:5: (lv_name_5_0= ruleValidID )
                    {
                    // InternalCheckCfg.g:4631:5: (lv_name_5_0= ruleValidID )
                    // InternalCheckCfg.g:4632:6: lv_name_5_0= ruleValidID
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getNameValidIDParserRuleCall_2_1_0());
                      					
                    }
                    pushFollow(FOLLOW_73);
                    lv_name_5_0=ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      						}
                      						set(
                      							current,
                      							"name",
                      							lv_name_5_0,
                      							"org.eclipse.xtext.xbase.Xtype.ValidID");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalCheckCfg.g:4650:3: (otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) ) )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==22) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // InternalCheckCfg.g:4651:4: otherlv_6= '=' ( (lv_right_7_0= ruleXExpression ) )
                    {
                    otherlv_6=(Token)match(input,22,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_6, grammarAccess.getXVariableDeclarationAccess().getEqualsSignKeyword_3_0());
                      			
                    }
                    // InternalCheckCfg.g:4655:4: ( (lv_right_7_0= ruleXExpression ) )
                    // InternalCheckCfg.g:4656:5: (lv_right_7_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:4656:5: (lv_right_7_0= ruleXExpression )
                    // InternalCheckCfg.g:4657:6: lv_right_7_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXVariableDeclarationAccess().getRightXExpressionParserRuleCall_3_1_0());
                      					
                    }
                    pushFollow(FOLLOW_2);
                    lv_right_7_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXVariableDeclarationRule());
                      						}
                      						set(
                      							current,
                      							"right",
                      							lv_right_7_0,
                      							"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXVariableDeclaration"


    // $ANTLR start "entryRuleJvmFormalParameter"
    // InternalCheckCfg.g:4679:1: entryRuleJvmFormalParameter returns [EObject current=null] : iv_ruleJvmFormalParameter= ruleJvmFormalParameter EOF ;
    public final EObject entryRuleJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmFormalParameter = null;


        try {
            // InternalCheckCfg.g:4679:59: (iv_ruleJvmFormalParameter= ruleJvmFormalParameter EOF )
            // InternalCheckCfg.g:4680:2: iv_ruleJvmFormalParameter= ruleJvmFormalParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmFormalParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmFormalParameter=ruleJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmFormalParameter; 
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
    // $ANTLR end "entryRuleJvmFormalParameter"


    // $ANTLR start "ruleJvmFormalParameter"
    // InternalCheckCfg.g:4686:1: ruleJvmFormalParameter returns [EObject current=null] : ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) ) ;
    public final EObject ruleJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject lv_parameterType_0_0 = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4692:2: ( ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) ) )
            // InternalCheckCfg.g:4693:2: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) )
            {
            // InternalCheckCfg.g:4693:2: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) ) )
            // InternalCheckCfg.g:4694:3: ( (lv_parameterType_0_0= ruleJvmTypeReference ) )? ( (lv_name_1_0= ruleValidID ) )
            {
            // InternalCheckCfg.g:4694:3: ( (lv_parameterType_0_0= ruleJvmTypeReference ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==RULE_ID) ) {
                int LA81_1 = input.LA(2);

                if ( (LA81_1==RULE_ID||LA81_1==24||LA81_1==31||LA81_1==57) ) {
                    alt81=1;
                }
            }
            else if ( (LA81_0==19||LA81_0==44) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // InternalCheckCfg.g:4695:4: (lv_parameterType_0_0= ruleJvmTypeReference )
                    {
                    // InternalCheckCfg.g:4695:4: (lv_parameterType_0_0= ruleJvmTypeReference )
                    // InternalCheckCfg.g:4696:5: lv_parameterType_0_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getJvmFormalParameterAccess().getParameterTypeJvmTypeReferenceParserRuleCall_0_0());
                      				
                    }
                    pushFollow(FOLLOW_4);
                    lv_parameterType_0_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getJvmFormalParameterRule());
                      					}
                      					set(
                      						current,
                      						"parameterType",
                      						lv_parameterType_0_0,
                      						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
                      					afterParserOrEnumRuleCall();
                      				
                    }

                    }


                    }
                    break;

            }

            // InternalCheckCfg.g:4713:3: ( (lv_name_1_0= ruleValidID ) )
            // InternalCheckCfg.g:4714:4: (lv_name_1_0= ruleValidID )
            {
            // InternalCheckCfg.g:4714:4: (lv_name_1_0= ruleValidID )
            // InternalCheckCfg.g:4715:5: lv_name_1_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getJvmFormalParameterAccess().getNameValidIDParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_name_1_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getJvmFormalParameterRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"org.eclipse.xtext.xbase.Xtype.ValidID");
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
    // $ANTLR end "ruleJvmFormalParameter"


    // $ANTLR start "entryRuleFullJvmFormalParameter"
    // InternalCheckCfg.g:4736:1: entryRuleFullJvmFormalParameter returns [EObject current=null] : iv_ruleFullJvmFormalParameter= ruleFullJvmFormalParameter EOF ;
    public final EObject entryRuleFullJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFullJvmFormalParameter = null;


        try {
            // InternalCheckCfg.g:4736:63: (iv_ruleFullJvmFormalParameter= ruleFullJvmFormalParameter EOF )
            // InternalCheckCfg.g:4737:2: iv_ruleFullJvmFormalParameter= ruleFullJvmFormalParameter EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFullJvmFormalParameterRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFullJvmFormalParameter=ruleFullJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFullJvmFormalParameter; 
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
    // $ANTLR end "entryRuleFullJvmFormalParameter"


    // $ANTLR start "ruleFullJvmFormalParameter"
    // InternalCheckCfg.g:4743:1: ruleFullJvmFormalParameter returns [EObject current=null] : ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) ) ;
    public final EObject ruleFullJvmFormalParameter() throws RecognitionException {
        EObject current = null;

        EObject lv_parameterType_0_0 = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4749:2: ( ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) ) )
            // InternalCheckCfg.g:4750:2: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) )
            {
            // InternalCheckCfg.g:4750:2: ( ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) ) )
            // InternalCheckCfg.g:4751:3: ( (lv_parameterType_0_0= ruleJvmTypeReference ) ) ( (lv_name_1_0= ruleValidID ) )
            {
            // InternalCheckCfg.g:4751:3: ( (lv_parameterType_0_0= ruleJvmTypeReference ) )
            // InternalCheckCfg.g:4752:4: (lv_parameterType_0_0= ruleJvmTypeReference )
            {
            // InternalCheckCfg.g:4752:4: (lv_parameterType_0_0= ruleJvmTypeReference )
            // InternalCheckCfg.g:4753:5: lv_parameterType_0_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getFullJvmFormalParameterAccess().getParameterTypeJvmTypeReferenceParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_4);
            lv_parameterType_0_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getFullJvmFormalParameterRule());
              					}
              					set(
              						current,
              						"parameterType",
              						lv_parameterType_0_0,
              						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:4770:3: ( (lv_name_1_0= ruleValidID ) )
            // InternalCheckCfg.g:4771:4: (lv_name_1_0= ruleValidID )
            {
            // InternalCheckCfg.g:4771:4: (lv_name_1_0= ruleValidID )
            // InternalCheckCfg.g:4772:5: lv_name_1_0= ruleValidID
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getFullJvmFormalParameterAccess().getNameValidIDParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_name_1_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getFullJvmFormalParameterRule());
              					}
              					set(
              						current,
              						"name",
              						lv_name_1_0,
              						"org.eclipse.xtext.xbase.Xtype.ValidID");
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
    // $ANTLR end "ruleFullJvmFormalParameter"


    // $ANTLR start "entryRuleXFeatureCall"
    // InternalCheckCfg.g:4793:1: entryRuleXFeatureCall returns [EObject current=null] : iv_ruleXFeatureCall= ruleXFeatureCall EOF ;
    public final EObject entryRuleXFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFeatureCall = null;


        try {
            // InternalCheckCfg.g:4793:53: (iv_ruleXFeatureCall= ruleXFeatureCall EOF )
            // InternalCheckCfg.g:4794:2: iv_ruleXFeatureCall= ruleXFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXFeatureCallRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXFeatureCall=ruleXFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXFeatureCall; 
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
    // $ANTLR end "entryRuleXFeatureCall"


    // $ANTLR start "ruleXFeatureCall"
    // InternalCheckCfg.g:4800:1: ruleXFeatureCall returns [EObject current=null] : ( () (otherlv_1= '<' ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure ) )? ) ;
    public final EObject ruleXFeatureCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token lv_explicitOperationCall_7_0=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        EObject lv_typeArguments_2_0 = null;

        EObject lv_typeArguments_4_0 = null;

        EObject lv_featureCallArguments_8_0 = null;

        EObject lv_featureCallArguments_9_0 = null;

        EObject lv_featureCallArguments_11_0 = null;

        EObject lv_featureCallArguments_13_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:4806:2: ( ( () (otherlv_1= '<' ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure ) )? ) )
            // InternalCheckCfg.g:4807:2: ( () (otherlv_1= '<' ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure ) )? )
            {
            // InternalCheckCfg.g:4807:2: ( () (otherlv_1= '<' ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure ) )? )
            // InternalCheckCfg.g:4808:3: () (otherlv_1= '<' ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )? ( ( ruleIdOrSuper ) ) ( ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')' )? ( ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure ) )?
            {
            // InternalCheckCfg.g:4808:3: ()
            // InternalCheckCfg.g:4809:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXFeatureCallAccess().getXFeatureCallAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:4815:3: (otherlv_1= '<' ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==31) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // InternalCheckCfg.g:4816:4: otherlv_1= '<' ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>'
                    {
                    otherlv_1=(Token)match(input,31,FOLLOW_41); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getXFeatureCallAccess().getLessThanSignKeyword_1_0());
                      			
                    }
                    // InternalCheckCfg.g:4820:4: ( (lv_typeArguments_2_0= ruleJvmArgumentTypeReference ) )
                    // InternalCheckCfg.g:4821:5: (lv_typeArguments_2_0= ruleJvmArgumentTypeReference )
                    {
                    // InternalCheckCfg.g:4821:5: (lv_typeArguments_2_0= ruleJvmArgumentTypeReference )
                    // InternalCheckCfg.g:4822:6: lv_typeArguments_2_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_42);
                    lv_typeArguments_2_0=ruleJvmArgumentTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                      						}
                      						add(
                      							current,
                      							"typeArguments",
                      							lv_typeArguments_2_0,
                      							"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:4839:4: (otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) )*
                    loop82:
                    do {
                        int alt82=2;
                        int LA82_0 = input.LA(1);

                        if ( (LA82_0==20) ) {
                            alt82=1;
                        }


                        switch (alt82) {
                    	case 1 :
                    	    // InternalCheckCfg.g:4840:5: otherlv_3= ',' ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_3=(Token)match(input,20,FOLLOW_41); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getXFeatureCallAccess().getCommaKeyword_1_2_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:4844:5: ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    // InternalCheckCfg.g:4845:6: (lv_typeArguments_4_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // InternalCheckCfg.g:4845:6: (lv_typeArguments_4_0= ruleJvmArgumentTypeReference )
                    	    // InternalCheckCfg.g:4846:7: lv_typeArguments_4_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXFeatureCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_1_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_42);
                    	    lv_typeArguments_4_0=ruleJvmArgumentTypeReference();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"typeArguments",
                    	      								lv_typeArguments_4_0,
                    	      								"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop82;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,32,FOLLOW_40); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getXFeatureCallAccess().getGreaterThanSignKeyword_1_3());
                      			
                    }

                    }
                    break;

            }

            // InternalCheckCfg.g:4869:3: ( ( ruleIdOrSuper ) )
            // InternalCheckCfg.g:4870:4: ( ruleIdOrSuper )
            {
            // InternalCheckCfg.g:4870:4: ( ruleIdOrSuper )
            // InternalCheckCfg.g:4871:5: ruleIdOrSuper
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getXFeatureCallRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureJvmIdentifiableElementCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_74);
            ruleIdOrSuper();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:4885:3: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')' )?
            int alt86=2;
            alt86 = dfa86.predict(input);
            switch (alt86) {
                case 1 :
                    // InternalCheckCfg.g:4886:4: ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')'
                    {
                    // InternalCheckCfg.g:4886:4: ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) )
                    // InternalCheckCfg.g:4887:5: ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' )
                    {
                    // InternalCheckCfg.g:4891:5: (lv_explicitOperationCall_7_0= '(' )
                    // InternalCheckCfg.g:4892:6: lv_explicitOperationCall_7_0= '('
                    {
                    lv_explicitOperationCall_7_0=(Token)match(input,19,FOLLOW_44); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_explicitOperationCall_7_0, grammarAccess.getXFeatureCallAccess().getExplicitOperationCallLeftParenthesisKeyword_3_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXFeatureCallRule());
                      						}
                      						setWithLastConsumed(current, "explicitOperationCall", lv_explicitOperationCall_7_0 != null, "(");
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:4904:4: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )?
                    int alt85=3;
                    alt85 = dfa85.predict(input);
                    switch (alt85) {
                        case 1 :
                            // InternalCheckCfg.g:4905:5: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) )
                            {
                            // InternalCheckCfg.g:4905:5: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) )
                            // InternalCheckCfg.g:4906:6: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure )
                            {
                            // InternalCheckCfg.g:4931:6: (lv_featureCallArguments_8_0= ruleXShortClosure )
                            // InternalCheckCfg.g:4932:7: lv_featureCallArguments_8_0= ruleXShortClosure
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXShortClosureParserRuleCall_3_1_0_0());
                              						
                            }
                            pushFollow(FOLLOW_45);
                            lv_featureCallArguments_8_0=ruleXShortClosure();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                              							}
                              							add(
                              								current,
                              								"featureCallArguments",
                              								lv_featureCallArguments_8_0,
                              								"org.eclipse.xtext.xbase.Xbase.XShortClosure");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalCheckCfg.g:4950:5: ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* )
                            {
                            // InternalCheckCfg.g:4950:5: ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* )
                            // InternalCheckCfg.g:4951:6: ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )*
                            {
                            // InternalCheckCfg.g:4951:6: ( (lv_featureCallArguments_9_0= ruleXExpression ) )
                            // InternalCheckCfg.g:4952:7: (lv_featureCallArguments_9_0= ruleXExpression )
                            {
                            // InternalCheckCfg.g:4952:7: (lv_featureCallArguments_9_0= ruleXExpression )
                            // InternalCheckCfg.g:4953:8: lv_featureCallArguments_9_0= ruleXExpression
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXExpressionParserRuleCall_3_1_1_0_0());
                              							
                            }
                            pushFollow(FOLLOW_13);
                            lv_featureCallArguments_9_0=ruleXExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                              								}
                              								add(
                              									current,
                              									"featureCallArguments",
                              									lv_featureCallArguments_9_0,
                              									"org.eclipse.xtext.xbase.Xbase.XExpression");
                              								afterParserOrEnumRuleCall();
                              							
                            }

                            }


                            }

                            // InternalCheckCfg.g:4970:6: (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )*
                            loop84:
                            do {
                                int alt84=2;
                                int LA84_0 = input.LA(1);

                                if ( (LA84_0==20) ) {
                                    alt84=1;
                                }


                                switch (alt84) {
                            	case 1 :
                            	    // InternalCheckCfg.g:4971:7: otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) )
                            	    {
                            	    otherlv_10=(Token)match(input,20,FOLLOW_20); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      							newLeafNode(otherlv_10, grammarAccess.getXFeatureCallAccess().getCommaKeyword_3_1_1_1_0());
                            	      						
                            	    }
                            	    // InternalCheckCfg.g:4975:7: ( (lv_featureCallArguments_11_0= ruleXExpression ) )
                            	    // InternalCheckCfg.g:4976:8: (lv_featureCallArguments_11_0= ruleXExpression )
                            	    {
                            	    // InternalCheckCfg.g:4976:8: (lv_featureCallArguments_11_0= ruleXExpression )
                            	    // InternalCheckCfg.g:4977:9: lv_featureCallArguments_11_0= ruleXExpression
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      									newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXExpressionParserRuleCall_3_1_1_1_1_0());
                            	      								
                            	    }
                            	    pushFollow(FOLLOW_13);
                            	    lv_featureCallArguments_11_0=ruleXExpression();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      									if (current==null) {
                            	      										current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                            	      									}
                            	      									add(
                            	      										current,
                            	      										"featureCallArguments",
                            	      										lv_featureCallArguments_11_0,
                            	      										"org.eclipse.xtext.xbase.Xbase.XExpression");
                            	      									afterParserOrEnumRuleCall();
                            	      								
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop84;
                                }
                            } while (true);


                            }


                            }
                            break;

                    }

                    otherlv_12=(Token)match(input,21,FOLLOW_75); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_12, grammarAccess.getXFeatureCallAccess().getRightParenthesisKeyword_3_2());
                      			
                    }

                    }
                    break;

            }

            // InternalCheckCfg.g:5002:3: ( ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure ) )?
            int alt87=2;
            alt87 = dfa87.predict(input);
            switch (alt87) {
                case 1 :
                    // InternalCheckCfg.g:5003:4: ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure )
                    {
                    // InternalCheckCfg.g:5009:4: (lv_featureCallArguments_13_0= ruleXClosure )
                    // InternalCheckCfg.g:5010:5: lv_featureCallArguments_13_0= ruleXClosure
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getXFeatureCallAccess().getFeatureCallArgumentsXClosureParserRuleCall_4_0());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_featureCallArguments_13_0=ruleXClosure();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getXFeatureCallRule());
                      					}
                      					add(
                      						current,
                      						"featureCallArguments",
                      						lv_featureCallArguments_13_0,
                      						"org.eclipse.xtext.xbase.Xbase.XClosure");
                      					afterParserOrEnumRuleCall();
                      				
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
    // $ANTLR end "ruleXFeatureCall"


    // $ANTLR start "entryRuleFeatureCallID"
    // InternalCheckCfg.g:5031:1: entryRuleFeatureCallID returns [String current=null] : iv_ruleFeatureCallID= ruleFeatureCallID EOF ;
    public final String entryRuleFeatureCallID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFeatureCallID = null;


        try {
            // InternalCheckCfg.g:5031:53: (iv_ruleFeatureCallID= ruleFeatureCallID EOF )
            // InternalCheckCfg.g:5032:2: iv_ruleFeatureCallID= ruleFeatureCallID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallIDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleFeatureCallID=ruleFeatureCallID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCallID.getText(); 
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
    // $ANTLR end "entryRuleFeatureCallID"


    // $ANTLR start "ruleFeatureCallID"
    // InternalCheckCfg.g:5038:1: ruleFeatureCallID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID | kw= 'extends' | kw= 'static' | kw= 'import' | kw= 'extension' ) ;
    public final AntlrDatatypeRuleToken ruleFeatureCallID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5044:2: ( (this_ValidID_0= ruleValidID | kw= 'extends' | kw= 'static' | kw= 'import' | kw= 'extension' ) )
            // InternalCheckCfg.g:5045:2: (this_ValidID_0= ruleValidID | kw= 'extends' | kw= 'static' | kw= 'import' | kw= 'extension' )
            {
            // InternalCheckCfg.g:5045:2: (this_ValidID_0= ruleValidID | kw= 'extends' | kw= 'static' | kw= 'import' | kw= 'extension' )
            int alt88=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                alt88=1;
                }
                break;
            case 72:
                {
                alt88=2;
                }
                break;
            case 73:
                {
                alt88=3;
                }
                break;
            case 74:
                {
                alt88=4;
                }
                break;
            case 75:
                {
                alt88=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }

            switch (alt88) {
                case 1 :
                    // InternalCheckCfg.g:5046:3: this_ValidID_0= ruleValidID
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getFeatureCallIDAccess().getValidIDParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_ValidID_0=ruleValidID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(this_ValidID_0);
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:5057:3: kw= 'extends'
                    {
                    kw=(Token)match(input,72,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getFeatureCallIDAccess().getExtendsKeyword_1());
                      		
                    }

                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:5063:3: kw= 'static'
                    {
                    kw=(Token)match(input,73,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getFeatureCallIDAccess().getStaticKeyword_2());
                      		
                    }

                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:5069:3: kw= 'import'
                    {
                    kw=(Token)match(input,74,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getFeatureCallIDAccess().getImportKeyword_3());
                      		
                    }

                    }
                    break;
                case 5 :
                    // InternalCheckCfg.g:5075:3: kw= 'extension'
                    {
                    kw=(Token)match(input,75,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getFeatureCallIDAccess().getExtensionKeyword_4());
                      		
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
    // $ANTLR end "ruleFeatureCallID"


    // $ANTLR start "entryRuleIdOrSuper"
    // InternalCheckCfg.g:5084:1: entryRuleIdOrSuper returns [String current=null] : iv_ruleIdOrSuper= ruleIdOrSuper EOF ;
    public final String entryRuleIdOrSuper() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdOrSuper = null;


        try {
            // InternalCheckCfg.g:5084:49: (iv_ruleIdOrSuper= ruleIdOrSuper EOF )
            // InternalCheckCfg.g:5085:2: iv_ruleIdOrSuper= ruleIdOrSuper EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdOrSuperRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleIdOrSuper=ruleIdOrSuper();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdOrSuper.getText(); 
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
    // $ANTLR end "entryRuleIdOrSuper"


    // $ANTLR start "ruleIdOrSuper"
    // InternalCheckCfg.g:5091:1: ruleIdOrSuper returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_FeatureCallID_0= ruleFeatureCallID | kw= 'super' ) ;
    public final AntlrDatatypeRuleToken ruleIdOrSuper() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_FeatureCallID_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5097:2: ( (this_FeatureCallID_0= ruleFeatureCallID | kw= 'super' ) )
            // InternalCheckCfg.g:5098:2: (this_FeatureCallID_0= ruleFeatureCallID | kw= 'super' )
            {
            // InternalCheckCfg.g:5098:2: (this_FeatureCallID_0= ruleFeatureCallID | kw= 'super' )
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==RULE_ID||(LA89_0>=72 && LA89_0<=75)) ) {
                alt89=1;
            }
            else if ( (LA89_0==76) ) {
                alt89=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }
            switch (alt89) {
                case 1 :
                    // InternalCheckCfg.g:5099:3: this_FeatureCallID_0= ruleFeatureCallID
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getIdOrSuperAccess().getFeatureCallIDParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_FeatureCallID_0=ruleFeatureCallID();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(this_FeatureCallID_0);
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:5110:3: kw= 'super'
                    {
                    kw=(Token)match(input,76,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(kw);
                      			newLeafNode(kw, grammarAccess.getIdOrSuperAccess().getSuperKeyword_1());
                      		
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
    // $ANTLR end "ruleIdOrSuper"


    // $ANTLR start "entryRuleXConstructorCall"
    // InternalCheckCfg.g:5119:1: entryRuleXConstructorCall returns [EObject current=null] : iv_ruleXConstructorCall= ruleXConstructorCall EOF ;
    public final EObject entryRuleXConstructorCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXConstructorCall = null;


        try {
            // InternalCheckCfg.g:5119:57: (iv_ruleXConstructorCall= ruleXConstructorCall EOF )
            // InternalCheckCfg.g:5120:2: iv_ruleXConstructorCall= ruleXConstructorCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXConstructorCallRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXConstructorCall=ruleXConstructorCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXConstructorCall; 
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
    // $ANTLR end "entryRuleXConstructorCall"


    // $ANTLR start "ruleXConstructorCall"
    // InternalCheckCfg.g:5126:1: ruleXConstructorCall returns [EObject current=null] : ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? ) ;
    public final EObject ruleXConstructorCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token lv_explicitConstructorCall_8_0=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject lv_typeArguments_4_0 = null;

        EObject lv_typeArguments_6_0 = null;

        EObject lv_arguments_9_0 = null;

        EObject lv_arguments_10_0 = null;

        EObject lv_arguments_12_0 = null;

        EObject lv_arguments_14_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5132:2: ( ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? ) )
            // InternalCheckCfg.g:5133:2: ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? )
            {
            // InternalCheckCfg.g:5133:2: ( () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )? )
            // InternalCheckCfg.g:5134:3: () otherlv_1= 'new' ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )? ( ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )? ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )?
            {
            // InternalCheckCfg.g:5134:3: ()
            // InternalCheckCfg.g:5135:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXConstructorCallAccess().getXConstructorCallAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,77,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXConstructorCallAccess().getNewKeyword_1());
              		
            }
            // InternalCheckCfg.g:5145:3: ( ( ruleQualifiedName ) )
            // InternalCheckCfg.g:5146:4: ( ruleQualifiedName )
            {
            // InternalCheckCfg.g:5146:4: ( ruleQualifiedName )
            // InternalCheckCfg.g:5147:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getXConstructorCallRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXConstructorCallAccess().getConstructorJvmConstructorCrossReference_2_0());
              				
            }
            pushFollow(FOLLOW_76);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:5161:3: ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )?
            int alt91=2;
            alt91 = dfa91.predict(input);
            switch (alt91) {
                case 1 :
                    // InternalCheckCfg.g:5162:4: ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>'
                    {
                    // InternalCheckCfg.g:5162:4: ( ( '<' )=>otherlv_3= '<' )
                    // InternalCheckCfg.g:5163:5: ( '<' )=>otherlv_3= '<'
                    {
                    otherlv_3=(Token)match(input,31,FOLLOW_41); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_3, grammarAccess.getXConstructorCallAccess().getLessThanSignKeyword_3_0());
                      				
                    }

                    }

                    // InternalCheckCfg.g:5169:4: ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) )
                    // InternalCheckCfg.g:5170:5: (lv_typeArguments_4_0= ruleJvmArgumentTypeReference )
                    {
                    // InternalCheckCfg.g:5170:5: (lv_typeArguments_4_0= ruleJvmArgumentTypeReference )
                    // InternalCheckCfg.g:5171:6: lv_typeArguments_4_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXConstructorCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_3_1_0());
                      					
                    }
                    pushFollow(FOLLOW_42);
                    lv_typeArguments_4_0=ruleJvmArgumentTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                      						}
                      						add(
                      							current,
                      							"typeArguments",
                      							lv_typeArguments_4_0,
                      							"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:5188:4: (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )*
                    loop90:
                    do {
                        int alt90=2;
                        int LA90_0 = input.LA(1);

                        if ( (LA90_0==20) ) {
                            alt90=1;
                        }


                        switch (alt90) {
                    	case 1 :
                    	    // InternalCheckCfg.g:5189:5: otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_5=(Token)match(input,20,FOLLOW_41); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_5, grammarAccess.getXConstructorCallAccess().getCommaKeyword_3_2_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:5193:5: ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) )
                    	    // InternalCheckCfg.g:5194:6: (lv_typeArguments_6_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // InternalCheckCfg.g:5194:6: (lv_typeArguments_6_0= ruleJvmArgumentTypeReference )
                    	    // InternalCheckCfg.g:5195:7: lv_typeArguments_6_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXConstructorCallAccess().getTypeArgumentsJvmArgumentTypeReferenceParserRuleCall_3_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_42);
                    	    lv_typeArguments_6_0=ruleJvmArgumentTypeReference();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"typeArguments",
                    	      								lv_typeArguments_6_0,
                    	      								"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop90;
                        }
                    } while (true);

                    otherlv_7=(Token)match(input,32,FOLLOW_74); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_7, grammarAccess.getXConstructorCallAccess().getGreaterThanSignKeyword_3_3());
                      			
                    }

                    }
                    break;

            }

            // InternalCheckCfg.g:5218:3: ( ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )?
            int alt94=2;
            alt94 = dfa94.predict(input);
            switch (alt94) {
                case 1 :
                    // InternalCheckCfg.g:5219:4: ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')'
                    {
                    // InternalCheckCfg.g:5219:4: ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) )
                    // InternalCheckCfg.g:5220:5: ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' )
                    {
                    // InternalCheckCfg.g:5224:5: (lv_explicitConstructorCall_8_0= '(' )
                    // InternalCheckCfg.g:5225:6: lv_explicitConstructorCall_8_0= '('
                    {
                    lv_explicitConstructorCall_8_0=(Token)match(input,19,FOLLOW_44); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_explicitConstructorCall_8_0, grammarAccess.getXConstructorCallAccess().getExplicitConstructorCallLeftParenthesisKeyword_4_0_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXConstructorCallRule());
                      						}
                      						setWithLastConsumed(current, "explicitConstructorCall", lv_explicitConstructorCall_8_0 != null, "(");
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:5237:4: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )?
                    int alt93=3;
                    alt93 = dfa93.predict(input);
                    switch (alt93) {
                        case 1 :
                            // InternalCheckCfg.g:5238:5: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) )
                            {
                            // InternalCheckCfg.g:5238:5: ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) )
                            // InternalCheckCfg.g:5239:6: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure )
                            {
                            // InternalCheckCfg.g:5264:6: (lv_arguments_9_0= ruleXShortClosure )
                            // InternalCheckCfg.g:5265:7: lv_arguments_9_0= ruleXShortClosure
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXShortClosureParserRuleCall_4_1_0_0());
                              						
                            }
                            pushFollow(FOLLOW_45);
                            lv_arguments_9_0=ruleXShortClosure();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                              							}
                              							add(
                              								current,
                              								"arguments",
                              								lv_arguments_9_0,
                              								"org.eclipse.xtext.xbase.Xbase.XShortClosure");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalCheckCfg.g:5283:5: ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* )
                            {
                            // InternalCheckCfg.g:5283:5: ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* )
                            // InternalCheckCfg.g:5284:6: ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )*
                            {
                            // InternalCheckCfg.g:5284:6: ( (lv_arguments_10_0= ruleXExpression ) )
                            // InternalCheckCfg.g:5285:7: (lv_arguments_10_0= ruleXExpression )
                            {
                            // InternalCheckCfg.g:5285:7: (lv_arguments_10_0= ruleXExpression )
                            // InternalCheckCfg.g:5286:8: lv_arguments_10_0= ruleXExpression
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXExpressionParserRuleCall_4_1_1_0_0());
                              							
                            }
                            pushFollow(FOLLOW_13);
                            lv_arguments_10_0=ruleXExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                              								}
                              								add(
                              									current,
                              									"arguments",
                              									lv_arguments_10_0,
                              									"org.eclipse.xtext.xbase.Xbase.XExpression");
                              								afterParserOrEnumRuleCall();
                              							
                            }

                            }


                            }

                            // InternalCheckCfg.g:5303:6: (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )*
                            loop92:
                            do {
                                int alt92=2;
                                int LA92_0 = input.LA(1);

                                if ( (LA92_0==20) ) {
                                    alt92=1;
                                }


                                switch (alt92) {
                            	case 1 :
                            	    // InternalCheckCfg.g:5304:7: otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) )
                            	    {
                            	    otherlv_11=(Token)match(input,20,FOLLOW_20); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      							newLeafNode(otherlv_11, grammarAccess.getXConstructorCallAccess().getCommaKeyword_4_1_1_1_0());
                            	      						
                            	    }
                            	    // InternalCheckCfg.g:5308:7: ( (lv_arguments_12_0= ruleXExpression ) )
                            	    // InternalCheckCfg.g:5309:8: (lv_arguments_12_0= ruleXExpression )
                            	    {
                            	    // InternalCheckCfg.g:5309:8: (lv_arguments_12_0= ruleXExpression )
                            	    // InternalCheckCfg.g:5310:9: lv_arguments_12_0= ruleXExpression
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      									newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXExpressionParserRuleCall_4_1_1_1_1_0());
                            	      								
                            	    }
                            	    pushFollow(FOLLOW_13);
                            	    lv_arguments_12_0=ruleXExpression();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      									if (current==null) {
                            	      										current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                            	      									}
                            	      									add(
                            	      										current,
                            	      										"arguments",
                            	      										lv_arguments_12_0,
                            	      										"org.eclipse.xtext.xbase.Xbase.XExpression");
                            	      									afterParserOrEnumRuleCall();
                            	      								
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop92;
                                }
                            } while (true);


                            }


                            }
                            break;

                    }

                    otherlv_13=(Token)match(input,21,FOLLOW_75); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_13, grammarAccess.getXConstructorCallAccess().getRightParenthesisKeyword_4_2());
                      			
                    }

                    }
                    break;

            }

            // InternalCheckCfg.g:5335:3: ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )?
            int alt95=2;
            alt95 = dfa95.predict(input);
            switch (alt95) {
                case 1 :
                    // InternalCheckCfg.g:5336:4: ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure )
                    {
                    // InternalCheckCfg.g:5342:4: (lv_arguments_14_0= ruleXClosure )
                    // InternalCheckCfg.g:5343:5: lv_arguments_14_0= ruleXClosure
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getXConstructorCallAccess().getArgumentsXClosureParserRuleCall_5_0());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_arguments_14_0=ruleXClosure();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getXConstructorCallRule());
                      					}
                      					add(
                      						current,
                      						"arguments",
                      						lv_arguments_14_0,
                      						"org.eclipse.xtext.xbase.Xbase.XClosure");
                      					afterParserOrEnumRuleCall();
                      				
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
    // $ANTLR end "ruleXConstructorCall"


    // $ANTLR start "entryRuleXBooleanLiteral"
    // InternalCheckCfg.g:5364:1: entryRuleXBooleanLiteral returns [EObject current=null] : iv_ruleXBooleanLiteral= ruleXBooleanLiteral EOF ;
    public final EObject entryRuleXBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXBooleanLiteral = null;


        try {
            // InternalCheckCfg.g:5364:56: (iv_ruleXBooleanLiteral= ruleXBooleanLiteral EOF )
            // InternalCheckCfg.g:5365:2: iv_ruleXBooleanLiteral= ruleXBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXBooleanLiteral=ruleXBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXBooleanLiteral; 
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
    // $ANTLR end "entryRuleXBooleanLiteral"


    // $ANTLR start "ruleXBooleanLiteral"
    // InternalCheckCfg.g:5371:1: ruleXBooleanLiteral returns [EObject current=null] : ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) ;
    public final EObject ruleXBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_isTrue_2_0=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:5377:2: ( ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) ) )
            // InternalCheckCfg.g:5378:2: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            {
            // InternalCheckCfg.g:5378:2: ( () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) ) )
            // InternalCheckCfg.g:5379:3: () (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            {
            // InternalCheckCfg.g:5379:3: ()
            // InternalCheckCfg.g:5380:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXBooleanLiteralAccess().getXBooleanLiteralAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:5386:3: (otherlv_1= 'false' | ( (lv_isTrue_2_0= 'true' ) ) )
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==78) ) {
                alt96=1;
            }
            else if ( (LA96_0==79) ) {
                alt96=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;
            }
            switch (alt96) {
                case 1 :
                    // InternalCheckCfg.g:5387:4: otherlv_1= 'false'
                    {
                    otherlv_1=(Token)match(input,78,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getXBooleanLiteralAccess().getFalseKeyword_1_0());
                      			
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:5392:4: ( (lv_isTrue_2_0= 'true' ) )
                    {
                    // InternalCheckCfg.g:5392:4: ( (lv_isTrue_2_0= 'true' ) )
                    // InternalCheckCfg.g:5393:5: (lv_isTrue_2_0= 'true' )
                    {
                    // InternalCheckCfg.g:5393:5: (lv_isTrue_2_0= 'true' )
                    // InternalCheckCfg.g:5394:6: lv_isTrue_2_0= 'true'
                    {
                    lv_isTrue_2_0=(Token)match(input,79,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						newLeafNode(lv_isTrue_2_0, grammarAccess.getXBooleanLiteralAccess().getIsTrueTrueKeyword_1_1_0());
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXBooleanLiteralRule());
                      						}
                      						setWithLastConsumed(current, "isTrue", lv_isTrue_2_0 != null, "true");
                      					
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
    // $ANTLR end "ruleXBooleanLiteral"


    // $ANTLR start "entryRuleXNullLiteral"
    // InternalCheckCfg.g:5411:1: entryRuleXNullLiteral returns [EObject current=null] : iv_ruleXNullLiteral= ruleXNullLiteral EOF ;
    public final EObject entryRuleXNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXNullLiteral = null;


        try {
            // InternalCheckCfg.g:5411:53: (iv_ruleXNullLiteral= ruleXNullLiteral EOF )
            // InternalCheckCfg.g:5412:2: iv_ruleXNullLiteral= ruleXNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXNullLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXNullLiteral=ruleXNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXNullLiteral; 
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
    // $ANTLR end "entryRuleXNullLiteral"


    // $ANTLR start "ruleXNullLiteral"
    // InternalCheckCfg.g:5418:1: ruleXNullLiteral returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleXNullLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:5424:2: ( ( () otherlv_1= 'null' ) )
            // InternalCheckCfg.g:5425:2: ( () otherlv_1= 'null' )
            {
            // InternalCheckCfg.g:5425:2: ( () otherlv_1= 'null' )
            // InternalCheckCfg.g:5426:3: () otherlv_1= 'null'
            {
            // InternalCheckCfg.g:5426:3: ()
            // InternalCheckCfg.g:5427:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXNullLiteralAccess().getXNullLiteralAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,80,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXNullLiteralAccess().getNullKeyword_1());
              		
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
    // $ANTLR end "ruleXNullLiteral"


    // $ANTLR start "entryRuleXNumberLiteral"
    // InternalCheckCfg.g:5441:1: entryRuleXNumberLiteral returns [EObject current=null] : iv_ruleXNumberLiteral= ruleXNumberLiteral EOF ;
    public final EObject entryRuleXNumberLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXNumberLiteral = null;


        try {
            // InternalCheckCfg.g:5441:55: (iv_ruleXNumberLiteral= ruleXNumberLiteral EOF )
            // InternalCheckCfg.g:5442:2: iv_ruleXNumberLiteral= ruleXNumberLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXNumberLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXNumberLiteral=ruleXNumberLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXNumberLiteral; 
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
    // $ANTLR end "entryRuleXNumberLiteral"


    // $ANTLR start "ruleXNumberLiteral"
    // InternalCheckCfg.g:5448:1: ruleXNumberLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= ruleNumber ) ) ) ;
    public final EObject ruleXNumberLiteral() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5454:2: ( ( () ( (lv_value_1_0= ruleNumber ) ) ) )
            // InternalCheckCfg.g:5455:2: ( () ( (lv_value_1_0= ruleNumber ) ) )
            {
            // InternalCheckCfg.g:5455:2: ( () ( (lv_value_1_0= ruleNumber ) ) )
            // InternalCheckCfg.g:5456:3: () ( (lv_value_1_0= ruleNumber ) )
            {
            // InternalCheckCfg.g:5456:3: ()
            // InternalCheckCfg.g:5457:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXNumberLiteralAccess().getXNumberLiteralAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:5463:3: ( (lv_value_1_0= ruleNumber ) )
            // InternalCheckCfg.g:5464:4: (lv_value_1_0= ruleNumber )
            {
            // InternalCheckCfg.g:5464:4: (lv_value_1_0= ruleNumber )
            // InternalCheckCfg.g:5465:5: lv_value_1_0= ruleNumber
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXNumberLiteralAccess().getValueNumberParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_value_1_0=ruleNumber();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXNumberLiteralRule());
              					}
              					set(
              						current,
              						"value",
              						lv_value_1_0,
              						"org.eclipse.xtext.xbase.Xbase.Number");
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
    // $ANTLR end "ruleXNumberLiteral"


    // $ANTLR start "entryRuleXStringLiteral"
    // InternalCheckCfg.g:5486:1: entryRuleXStringLiteral returns [EObject current=null] : iv_ruleXStringLiteral= ruleXStringLiteral EOF ;
    public final EObject entryRuleXStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStringLiteral = null;


        try {
            // InternalCheckCfg.g:5486:55: (iv_ruleXStringLiteral= ruleXStringLiteral EOF )
            // InternalCheckCfg.g:5487:2: iv_ruleXStringLiteral= ruleXStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXStringLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXStringLiteral=ruleXStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXStringLiteral; 
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
    // $ANTLR end "entryRuleXStringLiteral"


    // $ANTLR start "ruleXStringLiteral"
    // InternalCheckCfg.g:5493:1: ruleXStringLiteral returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleXStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:5499:2: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) )
            // InternalCheckCfg.g:5500:2: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            {
            // InternalCheckCfg.g:5500:2: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            // InternalCheckCfg.g:5501:3: () ( (lv_value_1_0= RULE_STRING ) )
            {
            // InternalCheckCfg.g:5501:3: ()
            // InternalCheckCfg.g:5502:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXStringLiteralAccess().getXStringLiteralAction_0(),
              					current);
              			
            }

            }

            // InternalCheckCfg.g:5508:3: ( (lv_value_1_0= RULE_STRING ) )
            // InternalCheckCfg.g:5509:4: (lv_value_1_0= RULE_STRING )
            {
            // InternalCheckCfg.g:5509:4: (lv_value_1_0= RULE_STRING )
            // InternalCheckCfg.g:5510:5: lv_value_1_0= RULE_STRING
            {
            lv_value_1_0=(Token)match(input,RULE_STRING,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(lv_value_1_0, grammarAccess.getXStringLiteralAccess().getValueSTRINGTerminalRuleCall_1_0());
              				
            }
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getXStringLiteralRule());
              					}
              					setWithLastConsumed(
              						current,
              						"value",
              						lv_value_1_0,
              						"org.eclipse.xtext.xbase.Xtype.STRING");
              				
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
    // $ANTLR end "ruleXStringLiteral"


    // $ANTLR start "entryRuleXTypeLiteral"
    // InternalCheckCfg.g:5530:1: entryRuleXTypeLiteral returns [EObject current=null] : iv_ruleXTypeLiteral= ruleXTypeLiteral EOF ;
    public final EObject entryRuleXTypeLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXTypeLiteral = null;


        try {
            // InternalCheckCfg.g:5530:53: (iv_ruleXTypeLiteral= ruleXTypeLiteral EOF )
            // InternalCheckCfg.g:5531:2: iv_ruleXTypeLiteral= ruleXTypeLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXTypeLiteralRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXTypeLiteral=ruleXTypeLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXTypeLiteral; 
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
    // $ANTLR end "entryRuleXTypeLiteral"


    // $ANTLR start "ruleXTypeLiteral"
    // InternalCheckCfg.g:5537:1: ruleXTypeLiteral returns [EObject current=null] : ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) ( (lv_arrayDimensions_4_0= ruleArrayBrackets ) )* otherlv_5= ')' ) ;
    public final EObject ruleXTypeLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_arrayDimensions_4_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5543:2: ( ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) ( (lv_arrayDimensions_4_0= ruleArrayBrackets ) )* otherlv_5= ')' ) )
            // InternalCheckCfg.g:5544:2: ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) ( (lv_arrayDimensions_4_0= ruleArrayBrackets ) )* otherlv_5= ')' )
            {
            // InternalCheckCfg.g:5544:2: ( () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) ( (lv_arrayDimensions_4_0= ruleArrayBrackets ) )* otherlv_5= ')' )
            // InternalCheckCfg.g:5545:3: () otherlv_1= 'typeof' otherlv_2= '(' ( ( ruleQualifiedName ) ) ( (lv_arrayDimensions_4_0= ruleArrayBrackets ) )* otherlv_5= ')'
            {
            // InternalCheckCfg.g:5545:3: ()
            // InternalCheckCfg.g:5546:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXTypeLiteralAccess().getXTypeLiteralAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,81,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXTypeLiteralAccess().getTypeofKeyword_1());
              		
            }
            otherlv_2=(Token)match(input,19,FOLLOW_4); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getXTypeLiteralAccess().getLeftParenthesisKeyword_2());
              		
            }
            // InternalCheckCfg.g:5560:3: ( ( ruleQualifiedName ) )
            // InternalCheckCfg.g:5561:4: ( ruleQualifiedName )
            {
            // InternalCheckCfg.g:5561:4: ( ruleQualifiedName )
            // InternalCheckCfg.g:5562:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getXTypeLiteralRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXTypeLiteralAccess().getTypeJvmTypeCrossReference_3_0());
              				
            }
            pushFollow(FOLLOW_77);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:5576:3: ( (lv_arrayDimensions_4_0= ruleArrayBrackets ) )*
            loop97:
            do {
                int alt97=2;
                int LA97_0 = input.LA(1);

                if ( (LA97_0==24) ) {
                    alt97=1;
                }


                switch (alt97) {
            	case 1 :
            	    // InternalCheckCfg.g:5577:4: (lv_arrayDimensions_4_0= ruleArrayBrackets )
            	    {
            	    // InternalCheckCfg.g:5577:4: (lv_arrayDimensions_4_0= ruleArrayBrackets )
            	    // InternalCheckCfg.g:5578:5: lv_arrayDimensions_4_0= ruleArrayBrackets
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getXTypeLiteralAccess().getArrayDimensionsArrayBracketsParserRuleCall_4_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_77);
            	    lv_arrayDimensions_4_0=ruleArrayBrackets();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					if (current==null) {
            	      						current = createModelElementForParent(grammarAccess.getXTypeLiteralRule());
            	      					}
            	      					add(
            	      						current,
            	      						"arrayDimensions",
            	      						lv_arrayDimensions_4_0,
            	      						"org.eclipse.xtext.xbase.Xtype.ArrayBrackets");
            	      					afterParserOrEnumRuleCall();
            	      				
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop97;
                }
            } while (true);

            otherlv_5=(Token)match(input,21,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getXTypeLiteralAccess().getRightParenthesisKeyword_5());
              		
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
    // $ANTLR end "ruleXTypeLiteral"


    // $ANTLR start "entryRuleXThrowExpression"
    // InternalCheckCfg.g:5603:1: entryRuleXThrowExpression returns [EObject current=null] : iv_ruleXThrowExpression= ruleXThrowExpression EOF ;
    public final EObject entryRuleXThrowExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXThrowExpression = null;


        try {
            // InternalCheckCfg.g:5603:57: (iv_ruleXThrowExpression= ruleXThrowExpression EOF )
            // InternalCheckCfg.g:5604:2: iv_ruleXThrowExpression= ruleXThrowExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXThrowExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXThrowExpression=ruleXThrowExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXThrowExpression; 
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
    // $ANTLR end "entryRuleXThrowExpression"


    // $ANTLR start "ruleXThrowExpression"
    // InternalCheckCfg.g:5610:1: ruleXThrowExpression returns [EObject current=null] : ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) ) ;
    public final EObject ruleXThrowExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5616:2: ( ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) ) )
            // InternalCheckCfg.g:5617:2: ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) )
            {
            // InternalCheckCfg.g:5617:2: ( () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) ) )
            // InternalCheckCfg.g:5618:3: () otherlv_1= 'throw' ( (lv_expression_2_0= ruleXExpression ) )
            {
            // InternalCheckCfg.g:5618:3: ()
            // InternalCheckCfg.g:5619:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXThrowExpressionAccess().getXThrowExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,82,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXThrowExpressionAccess().getThrowKeyword_1());
              		
            }
            // InternalCheckCfg.g:5629:3: ( (lv_expression_2_0= ruleXExpression ) )
            // InternalCheckCfg.g:5630:4: (lv_expression_2_0= ruleXExpression )
            {
            // InternalCheckCfg.g:5630:4: (lv_expression_2_0= ruleXExpression )
            // InternalCheckCfg.g:5631:5: lv_expression_2_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXThrowExpressionAccess().getExpressionXExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_2_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXThrowExpressionRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXThrowExpression"


    // $ANTLR start "entryRuleXReturnExpression"
    // InternalCheckCfg.g:5652:1: entryRuleXReturnExpression returns [EObject current=null] : iv_ruleXReturnExpression= ruleXReturnExpression EOF ;
    public final EObject entryRuleXReturnExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXReturnExpression = null;


        try {
            // InternalCheckCfg.g:5652:58: (iv_ruleXReturnExpression= ruleXReturnExpression EOF )
            // InternalCheckCfg.g:5653:2: iv_ruleXReturnExpression= ruleXReturnExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXReturnExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXReturnExpression=ruleXReturnExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXReturnExpression; 
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
    // $ANTLR end "entryRuleXReturnExpression"


    // $ANTLR start "ruleXReturnExpression"
    // InternalCheckCfg.g:5659:1: ruleXReturnExpression returns [EObject current=null] : ( () otherlv_1= 'return' ( ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression ) )? ) ;
    public final EObject ruleXReturnExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_expression_2_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5665:2: ( ( () otherlv_1= 'return' ( ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression ) )? ) )
            // InternalCheckCfg.g:5666:2: ( () otherlv_1= 'return' ( ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression ) )? )
            {
            // InternalCheckCfg.g:5666:2: ( () otherlv_1= 'return' ( ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression ) )? )
            // InternalCheckCfg.g:5667:3: () otherlv_1= 'return' ( ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression ) )?
            {
            // InternalCheckCfg.g:5667:3: ()
            // InternalCheckCfg.g:5668:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXReturnExpressionAccess().getXReturnExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,83,FOLLOW_78); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXReturnExpressionAccess().getReturnKeyword_1());
              		
            }
            // InternalCheckCfg.g:5678:3: ( ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression ) )?
            int alt98=2;
            alt98 = dfa98.predict(input);
            switch (alt98) {
                case 1 :
                    // InternalCheckCfg.g:5679:4: ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:5680:4: (lv_expression_2_0= ruleXExpression )
                    // InternalCheckCfg.g:5681:5: lv_expression_2_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      					newCompositeNode(grammarAccess.getXReturnExpressionAccess().getExpressionXExpressionParserRuleCall_2_0());
                      				
                    }
                    pushFollow(FOLLOW_2);
                    lv_expression_2_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					if (current==null) {
                      						current = createModelElementForParent(grammarAccess.getXReturnExpressionRule());
                      					}
                      					set(
                      						current,
                      						"expression",
                      						lv_expression_2_0,
                      						"org.eclipse.xtext.xbase.Xbase.XExpression");
                      					afterParserOrEnumRuleCall();
                      				
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
    // $ANTLR end "ruleXReturnExpression"


    // $ANTLR start "entryRuleXTryCatchFinallyExpression"
    // InternalCheckCfg.g:5702:1: entryRuleXTryCatchFinallyExpression returns [EObject current=null] : iv_ruleXTryCatchFinallyExpression= ruleXTryCatchFinallyExpression EOF ;
    public final EObject entryRuleXTryCatchFinallyExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXTryCatchFinallyExpression = null;


        try {
            // InternalCheckCfg.g:5702:67: (iv_ruleXTryCatchFinallyExpression= ruleXTryCatchFinallyExpression EOF )
            // InternalCheckCfg.g:5703:2: iv_ruleXTryCatchFinallyExpression= ruleXTryCatchFinallyExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXTryCatchFinallyExpression=ruleXTryCatchFinallyExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXTryCatchFinallyExpression; 
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
    // $ANTLR end "entryRuleXTryCatchFinallyExpression"


    // $ANTLR start "ruleXTryCatchFinallyExpression"
    // InternalCheckCfg.g:5709:1: ruleXTryCatchFinallyExpression returns [EObject current=null] : ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) ) ;
    public final EObject ruleXTryCatchFinallyExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_expression_2_0 = null;

        EObject lv_catchClauses_3_0 = null;

        EObject lv_finallyExpression_5_0 = null;

        EObject lv_finallyExpression_7_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5715:2: ( ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) ) )
            // InternalCheckCfg.g:5716:2: ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) )
            {
            // InternalCheckCfg.g:5716:2: ( () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) ) )
            // InternalCheckCfg.g:5717:3: () otherlv_1= 'try' ( (lv_expression_2_0= ruleXExpression ) ) ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) )
            {
            // InternalCheckCfg.g:5717:3: ()
            // InternalCheckCfg.g:5718:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getXTryCatchFinallyExpressionAccess().getXTryCatchFinallyExpressionAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,84,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXTryCatchFinallyExpressionAccess().getTryKeyword_1());
              		
            }
            // InternalCheckCfg.g:5728:3: ( (lv_expression_2_0= ruleXExpression ) )
            // InternalCheckCfg.g:5729:4: (lv_expression_2_0= ruleXExpression )
            {
            // InternalCheckCfg.g:5729:4: (lv_expression_2_0= ruleXExpression )
            // InternalCheckCfg.g:5730:5: lv_expression_2_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getExpressionXExpressionParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_79);
            lv_expression_2_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_2_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:5747:3: ( ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? ) | (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) ) )
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==87) ) {
                alt101=1;
            }
            else if ( (LA101_0==85) ) {
                alt101=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }
            switch (alt101) {
                case 1 :
                    // InternalCheckCfg.g:5748:4: ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? )
                    {
                    // InternalCheckCfg.g:5748:4: ( ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )? )
                    // InternalCheckCfg.g:5749:5: ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+ ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )?
                    {
                    // InternalCheckCfg.g:5749:5: ( ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause ) )+
                    int cnt99=0;
                    loop99:
                    do {
                        int alt99=2;
                        int LA99_0 = input.LA(1);

                        if ( (LA99_0==87) ) {
                            int LA99_2 = input.LA(2);

                            if ( (synpred38_InternalCheckCfg()) ) {
                                alt99=1;
                            }


                        }


                        switch (alt99) {
                    	case 1 :
                    	    // InternalCheckCfg.g:5750:6: ( 'catch' )=> (lv_catchClauses_3_0= ruleXCatchClause )
                    	    {
                    	    // InternalCheckCfg.g:5751:6: (lv_catchClauses_3_0= ruleXCatchClause )
                    	    // InternalCheckCfg.g:5752:7: lv_catchClauses_3_0= ruleXCatchClause
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getCatchClausesXCatchClauseParserRuleCall_3_0_0_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_80);
                    	    lv_catchClauses_3_0=ruleXCatchClause();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"catchClauses",
                    	      								lv_catchClauses_3_0,
                    	      								"org.eclipse.xtext.xbase.Xbase.XCatchClause");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt99 >= 1 ) break loop99;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(99, input);
                                throw eee;
                        }
                        cnt99++;
                    } while (true);

                    // InternalCheckCfg.g:5769:5: ( ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) ) )?
                    int alt100=2;
                    int LA100_0 = input.LA(1);

                    if ( (LA100_0==85) ) {
                        int LA100_1 = input.LA(2);

                        if ( (synpred39_InternalCheckCfg()) ) {
                            alt100=1;
                        }
                    }
                    switch (alt100) {
                        case 1 :
                            // InternalCheckCfg.g:5770:6: ( ( 'finally' )=>otherlv_4= 'finally' ) ( (lv_finallyExpression_5_0= ruleXExpression ) )
                            {
                            // InternalCheckCfg.g:5770:6: ( ( 'finally' )=>otherlv_4= 'finally' )
                            // InternalCheckCfg.g:5771:7: ( 'finally' )=>otherlv_4= 'finally'
                            {
                            otherlv_4=(Token)match(input,85,FOLLOW_20); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(otherlv_4, grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyKeyword_3_0_1_0());
                              						
                            }

                            }

                            // InternalCheckCfg.g:5777:6: ( (lv_finallyExpression_5_0= ruleXExpression ) )
                            // InternalCheckCfg.g:5778:7: (lv_finallyExpression_5_0= ruleXExpression )
                            {
                            // InternalCheckCfg.g:5778:7: (lv_finallyExpression_5_0= ruleXExpression )
                            // InternalCheckCfg.g:5779:8: lv_finallyExpression_5_0= ruleXExpression
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyExpressionXExpressionParserRuleCall_3_0_1_1_0());
                              							
                            }
                            pushFollow(FOLLOW_2);
                            lv_finallyExpression_5_0=ruleXExpression();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
                              								}
                              								set(
                              									current,
                              									"finallyExpression",
                              									lv_finallyExpression_5_0,
                              									"org.eclipse.xtext.xbase.Xbase.XExpression");
                              								afterParserOrEnumRuleCall();
                              							
                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:5799:4: (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) )
                    {
                    // InternalCheckCfg.g:5799:4: (otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) ) )
                    // InternalCheckCfg.g:5800:5: otherlv_6= 'finally' ( (lv_finallyExpression_7_0= ruleXExpression ) )
                    {
                    otherlv_6=(Token)match(input,85,FOLLOW_20); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_6, grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyKeyword_3_1_0());
                      				
                    }
                    // InternalCheckCfg.g:5804:5: ( (lv_finallyExpression_7_0= ruleXExpression ) )
                    // InternalCheckCfg.g:5805:6: (lv_finallyExpression_7_0= ruleXExpression )
                    {
                    // InternalCheckCfg.g:5805:6: (lv_finallyExpression_7_0= ruleXExpression )
                    // InternalCheckCfg.g:5806:7: lv_finallyExpression_7_0= ruleXExpression
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getXTryCatchFinallyExpressionAccess().getFinallyExpressionXExpressionParserRuleCall_3_1_1_0());
                      						
                    }
                    pushFollow(FOLLOW_2);
                    lv_finallyExpression_7_0=ruleXExpression();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getXTryCatchFinallyExpressionRule());
                      							}
                      							set(
                      								current,
                      								"finallyExpression",
                      								lv_finallyExpression_7_0,
                      								"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXTryCatchFinallyExpression"


    // $ANTLR start "entryRuleXSynchronizedExpression"
    // InternalCheckCfg.g:5829:1: entryRuleXSynchronizedExpression returns [EObject current=null] : iv_ruleXSynchronizedExpression= ruleXSynchronizedExpression EOF ;
    public final EObject entryRuleXSynchronizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSynchronizedExpression = null;


        try {
            // InternalCheckCfg.g:5829:64: (iv_ruleXSynchronizedExpression= ruleXSynchronizedExpression EOF )
            // InternalCheckCfg.g:5830:2: iv_ruleXSynchronizedExpression= ruleXSynchronizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXSynchronizedExpressionRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXSynchronizedExpression=ruleXSynchronizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXSynchronizedExpression; 
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
    // $ANTLR end "entryRuleXSynchronizedExpression"


    // $ANTLR start "ruleXSynchronizedExpression"
    // InternalCheckCfg.g:5836:1: ruleXSynchronizedExpression returns [EObject current=null] : ( ( ( ( () 'synchronized' '(' ) )=> ( () otherlv_1= 'synchronized' otherlv_2= '(' ) ) ( (lv_param_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_expression_5_0= ruleXExpression ) ) ) ;
    public final EObject ruleXSynchronizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_param_3_0 = null;

        EObject lv_expression_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5842:2: ( ( ( ( ( () 'synchronized' '(' ) )=> ( () otherlv_1= 'synchronized' otherlv_2= '(' ) ) ( (lv_param_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_expression_5_0= ruleXExpression ) ) ) )
            // InternalCheckCfg.g:5843:2: ( ( ( ( () 'synchronized' '(' ) )=> ( () otherlv_1= 'synchronized' otherlv_2= '(' ) ) ( (lv_param_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_expression_5_0= ruleXExpression ) ) )
            {
            // InternalCheckCfg.g:5843:2: ( ( ( ( () 'synchronized' '(' ) )=> ( () otherlv_1= 'synchronized' otherlv_2= '(' ) ) ( (lv_param_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_expression_5_0= ruleXExpression ) ) )
            // InternalCheckCfg.g:5844:3: ( ( ( () 'synchronized' '(' ) )=> ( () otherlv_1= 'synchronized' otherlv_2= '(' ) ) ( (lv_param_3_0= ruleXExpression ) ) otherlv_4= ')' ( (lv_expression_5_0= ruleXExpression ) )
            {
            // InternalCheckCfg.g:5844:3: ( ( ( () 'synchronized' '(' ) )=> ( () otherlv_1= 'synchronized' otherlv_2= '(' ) )
            // InternalCheckCfg.g:5845:4: ( ( () 'synchronized' '(' ) )=> ( () otherlv_1= 'synchronized' otherlv_2= '(' )
            {
            // InternalCheckCfg.g:5852:4: ( () otherlv_1= 'synchronized' otherlv_2= '(' )
            // InternalCheckCfg.g:5853:5: () otherlv_1= 'synchronized' otherlv_2= '('
            {
            // InternalCheckCfg.g:5853:5: ()
            // InternalCheckCfg.g:5854:6: 
            {
            if ( state.backtracking==0 ) {

              						current = forceCreateModelElement(
              							grammarAccess.getXSynchronizedExpressionAccess().getXSynchronizedExpressionAction_0_0_0(),
              							current);
              					
            }

            }

            otherlv_1=(Token)match(input,86,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_1, grammarAccess.getXSynchronizedExpressionAccess().getSynchronizedKeyword_0_0_1());
              				
            }
            otherlv_2=(Token)match(input,19,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					newLeafNode(otherlv_2, grammarAccess.getXSynchronizedExpressionAccess().getLeftParenthesisKeyword_0_0_2());
              				
            }

            }


            }

            // InternalCheckCfg.g:5870:3: ( (lv_param_3_0= ruleXExpression ) )
            // InternalCheckCfg.g:5871:4: (lv_param_3_0= ruleXExpression )
            {
            // InternalCheckCfg.g:5871:4: (lv_param_3_0= ruleXExpression )
            // InternalCheckCfg.g:5872:5: lv_param_3_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXSynchronizedExpressionAccess().getParamXExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_45);
            lv_param_3_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXSynchronizedExpressionRule());
              					}
              					set(
              						current,
              						"param",
              						lv_param_3_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_4=(Token)match(input,21,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getXSynchronizedExpressionAccess().getRightParenthesisKeyword_2());
              		
            }
            // InternalCheckCfg.g:5893:3: ( (lv_expression_5_0= ruleXExpression ) )
            // InternalCheckCfg.g:5894:4: (lv_expression_5_0= ruleXExpression )
            {
            // InternalCheckCfg.g:5894:4: (lv_expression_5_0= ruleXExpression )
            // InternalCheckCfg.g:5895:5: lv_expression_5_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXSynchronizedExpressionAccess().getExpressionXExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_5_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXSynchronizedExpressionRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_5_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXSynchronizedExpression"


    // $ANTLR start "entryRuleXCatchClause"
    // InternalCheckCfg.g:5916:1: entryRuleXCatchClause returns [EObject current=null] : iv_ruleXCatchClause= ruleXCatchClause EOF ;
    public final EObject entryRuleXCatchClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXCatchClause = null;


        try {
            // InternalCheckCfg.g:5916:53: (iv_ruleXCatchClause= ruleXCatchClause EOF )
            // InternalCheckCfg.g:5917:2: iv_ruleXCatchClause= ruleXCatchClause EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXCatchClauseRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXCatchClause=ruleXCatchClause();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXCatchClause; 
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
    // $ANTLR end "entryRuleXCatchClause"


    // $ANTLR start "ruleXCatchClause"
    // InternalCheckCfg.g:5923:1: ruleXCatchClause returns [EObject current=null] : ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) ) ;
    public final EObject ruleXCatchClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_declaredParam_2_0 = null;

        EObject lv_expression_4_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:5929:2: ( ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) ) )
            // InternalCheckCfg.g:5930:2: ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) )
            {
            // InternalCheckCfg.g:5930:2: ( ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) ) )
            // InternalCheckCfg.g:5931:3: ( ( 'catch' )=>otherlv_0= 'catch' ) otherlv_1= '(' ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) ) otherlv_3= ')' ( (lv_expression_4_0= ruleXExpression ) )
            {
            // InternalCheckCfg.g:5931:3: ( ( 'catch' )=>otherlv_0= 'catch' )
            // InternalCheckCfg.g:5932:4: ( 'catch' )=>otherlv_0= 'catch'
            {
            otherlv_0=(Token)match(input,87,FOLLOW_56); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              				newLeafNode(otherlv_0, grammarAccess.getXCatchClauseAccess().getCatchKeyword_0());
              			
            }

            }

            otherlv_1=(Token)match(input,19,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getXCatchClauseAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalCheckCfg.g:5942:3: ( (lv_declaredParam_2_0= ruleFullJvmFormalParameter ) )
            // InternalCheckCfg.g:5943:4: (lv_declaredParam_2_0= ruleFullJvmFormalParameter )
            {
            // InternalCheckCfg.g:5943:4: (lv_declaredParam_2_0= ruleFullJvmFormalParameter )
            // InternalCheckCfg.g:5944:5: lv_declaredParam_2_0= ruleFullJvmFormalParameter
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXCatchClauseAccess().getDeclaredParamFullJvmFormalParameterParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_45);
            lv_declaredParam_2_0=ruleFullJvmFormalParameter();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXCatchClauseRule());
              					}
              					set(
              						current,
              						"declaredParam",
              						lv_declaredParam_2_0,
              						"org.eclipse.xtext.xbase.Xbase.FullJvmFormalParameter");
              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            otherlv_3=(Token)match(input,21,FOLLOW_20); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_3, grammarAccess.getXCatchClauseAccess().getRightParenthesisKeyword_3());
              		
            }
            // InternalCheckCfg.g:5965:3: ( (lv_expression_4_0= ruleXExpression ) )
            // InternalCheckCfg.g:5966:4: (lv_expression_4_0= ruleXExpression )
            {
            // InternalCheckCfg.g:5966:4: (lv_expression_4_0= ruleXExpression )
            // InternalCheckCfg.g:5967:5: lv_expression_4_0= ruleXExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXCatchClauseAccess().getExpressionXExpressionParserRuleCall_4_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_expression_4_0=ruleXExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXCatchClauseRule());
              					}
              					set(
              						current,
              						"expression",
              						lv_expression_4_0,
              						"org.eclipse.xtext.xbase.Xbase.XExpression");
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
    // $ANTLR end "ruleXCatchClause"


    // $ANTLR start "entryRuleQualifiedName"
    // InternalCheckCfg.g:5988:1: entryRuleQualifiedName returns [String current=null] : iv_ruleQualifiedName= ruleQualifiedName EOF ;
    public final String entryRuleQualifiedName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedName = null;


        try {
            // InternalCheckCfg.g:5988:53: (iv_ruleQualifiedName= ruleQualifiedName EOF )
            // InternalCheckCfg.g:5989:2: iv_ruleQualifiedName= ruleQualifiedName EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedName=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedName.getText(); 
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
    // $ANTLR end "entryRuleQualifiedName"


    // $ANTLR start "ruleQualifiedName"
    // InternalCheckCfg.g:5995:1: ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;

        AntlrDatatypeRuleToken this_ValidID_2 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6001:2: ( (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* ) )
            // InternalCheckCfg.g:6002:2: (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* )
            {
            // InternalCheckCfg.g:6002:2: (this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )* )
            // InternalCheckCfg.g:6003:3: this_ValidID_0= ruleValidID ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_81);
            this_ValidID_0=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(this_ValidID_0);
              		
            }
            if ( state.backtracking==0 ) {

              			afterParserOrEnumRuleCall();
              		
            }
            // InternalCheckCfg.g:6013:3: ( ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID )*
            loop102:
            do {
                int alt102=2;
                int LA102_0 = input.LA(1);

                if ( (LA102_0==57) ) {
                    int LA102_2 = input.LA(2);

                    if ( (LA102_2==RULE_ID) ) {
                        int LA102_3 = input.LA(3);

                        if ( (synpred42_InternalCheckCfg()) ) {
                            alt102=1;
                        }


                    }


                }


                switch (alt102) {
            	case 1 :
            	    // InternalCheckCfg.g:6014:4: ( ( '.' )=>kw= '.' ) this_ValidID_2= ruleValidID
            	    {
            	    // InternalCheckCfg.g:6014:4: ( ( '.' )=>kw= '.' )
            	    // InternalCheckCfg.g:6015:5: ( '.' )=>kw= '.'
            	    {
            	    kw=(Token)match(input,57,FOLLOW_4); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					current.merge(kw);
            	      					newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0());
            	      				
            	    }

            	    }

            	    if ( state.backtracking==0 ) {

            	      				newCompositeNode(grammarAccess.getQualifiedNameAccess().getValidIDParserRuleCall_1_1());
            	      			
            	    }
            	    pushFollow(FOLLOW_81);
            	    this_ValidID_2=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				current.merge(this_ValidID_2);
            	      			
            	    }
            	    if ( state.backtracking==0 ) {

            	      				afterParserOrEnumRuleCall();
            	      			
            	    }

            	    }
            	    break;

            	default :
            	    break loop102;
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
    // $ANTLR end "ruleQualifiedName"


    // $ANTLR start "entryRuleNumber"
    // InternalCheckCfg.g:6037:1: entryRuleNumber returns [String current=null] : iv_ruleNumber= ruleNumber EOF ;
    public final String entryRuleNumber() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumber = null;



        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalCheckCfg.g:6039:2: (iv_ruleNumber= ruleNumber EOF )
            // InternalCheckCfg.g:6040:2: iv_ruleNumber= ruleNumber EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNumberRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleNumber=ruleNumber();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNumber.getText(); 
            }
            match(input,EOF,FOLLOW_2); if (state.failed) return current;

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleNumber"


    // $ANTLR start "ruleNumber"
    // InternalCheckCfg.g:6049:1: ruleNumber returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) ) ;
    public final AntlrDatatypeRuleToken ruleNumber() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_HEX_0=null;
        Token this_INT_1=null;
        Token this_DECIMAL_2=null;
        Token kw=null;
        Token this_INT_4=null;
        Token this_DECIMAL_5=null;


        	enterRule();
        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalCheckCfg.g:6056:2: ( (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) ) )
            // InternalCheckCfg.g:6057:2: (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) )
            {
            // InternalCheckCfg.g:6057:2: (this_HEX_0= RULE_HEX | ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? ) )
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==RULE_HEX) ) {
                alt106=1;
            }
            else if ( ((LA106_0>=RULE_INT && LA106_0<=RULE_DECIMAL)) ) {
                alt106=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 106, 0, input);

                throw nvae;
            }
            switch (alt106) {
                case 1 :
                    // InternalCheckCfg.g:6058:3: this_HEX_0= RULE_HEX
                    {
                    this_HEX_0=(Token)match(input,RULE_HEX,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current.merge(this_HEX_0);
                      		
                    }
                    if ( state.backtracking==0 ) {

                      			newLeafNode(this_HEX_0, grammarAccess.getNumberAccess().getHEXTerminalRuleCall_0());
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:6066:3: ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? )
                    {
                    // InternalCheckCfg.g:6066:3: ( (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )? )
                    // InternalCheckCfg.g:6067:4: (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL ) (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )?
                    {
                    // InternalCheckCfg.g:6067:4: (this_INT_1= RULE_INT | this_DECIMAL_2= RULE_DECIMAL )
                    int alt103=2;
                    int LA103_0 = input.LA(1);

                    if ( (LA103_0==RULE_INT) ) {
                        alt103=1;
                    }
                    else if ( (LA103_0==RULE_DECIMAL) ) {
                        alt103=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 103, 0, input);

                        throw nvae;
                    }
                    switch (alt103) {
                        case 1 :
                            // InternalCheckCfg.g:6068:5: this_INT_1= RULE_INT
                            {
                            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_81); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					current.merge(this_INT_1);
                              				
                            }
                            if ( state.backtracking==0 ) {

                              					newLeafNode(this_INT_1, grammarAccess.getNumberAccess().getINTTerminalRuleCall_1_0_0());
                              				
                            }

                            }
                            break;
                        case 2 :
                            // InternalCheckCfg.g:6076:5: this_DECIMAL_2= RULE_DECIMAL
                            {
                            this_DECIMAL_2=(Token)match(input,RULE_DECIMAL,FOLLOW_81); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					current.merge(this_DECIMAL_2);
                              				
                            }
                            if ( state.backtracking==0 ) {

                              					newLeafNode(this_DECIMAL_2, grammarAccess.getNumberAccess().getDECIMALTerminalRuleCall_1_0_1());
                              				
                            }

                            }
                            break;

                    }

                    // InternalCheckCfg.g:6084:4: (kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL ) )?
                    int alt105=2;
                    int LA105_0 = input.LA(1);

                    if ( (LA105_0==57) ) {
                        int LA105_1 = input.LA(2);

                        if ( ((LA105_1>=RULE_INT && LA105_1<=RULE_DECIMAL)) ) {
                            alt105=1;
                        }
                    }
                    switch (alt105) {
                        case 1 :
                            // InternalCheckCfg.g:6085:5: kw= '.' (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL )
                            {
                            kw=(Token)match(input,57,FOLLOW_82); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              					current.merge(kw);
                              					newLeafNode(kw, grammarAccess.getNumberAccess().getFullStopKeyword_1_1_0());
                              				
                            }
                            // InternalCheckCfg.g:6090:5: (this_INT_4= RULE_INT | this_DECIMAL_5= RULE_DECIMAL )
                            int alt104=2;
                            int LA104_0 = input.LA(1);

                            if ( (LA104_0==RULE_INT) ) {
                                alt104=1;
                            }
                            else if ( (LA104_0==RULE_DECIMAL) ) {
                                alt104=2;
                            }
                            else {
                                if (state.backtracking>0) {state.failed=true; return current;}
                                NoViableAltException nvae =
                                    new NoViableAltException("", 104, 0, input);

                                throw nvae;
                            }
                            switch (alt104) {
                                case 1 :
                                    // InternalCheckCfg.g:6091:6: this_INT_4= RULE_INT
                                    {
                                    this_INT_4=(Token)match(input,RULE_INT,FOLLOW_2); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      						current.merge(this_INT_4);
                                      					
                                    }
                                    if ( state.backtracking==0 ) {

                                      						newLeafNode(this_INT_4, grammarAccess.getNumberAccess().getINTTerminalRuleCall_1_1_1_0());
                                      					
                                    }

                                    }
                                    break;
                                case 2 :
                                    // InternalCheckCfg.g:6099:6: this_DECIMAL_5= RULE_DECIMAL
                                    {
                                    this_DECIMAL_5=(Token)match(input,RULE_DECIMAL,FOLLOW_2); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      						current.merge(this_DECIMAL_5);
                                      					
                                    }
                                    if ( state.backtracking==0 ) {

                                      						newLeafNode(this_DECIMAL_5, grammarAccess.getNumberAccess().getDECIMALTerminalRuleCall_1_1_1_1());
                                      					
                                    }

                                    }
                                    break;

                            }


                            }
                            break;

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

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleNumber"


    // $ANTLR start "entryRuleJvmTypeReference"
    // InternalCheckCfg.g:6116:1: entryRuleJvmTypeReference returns [EObject current=null] : iv_ruleJvmTypeReference= ruleJvmTypeReference EOF ;
    public final EObject entryRuleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmTypeReference = null;


        try {
            // InternalCheckCfg.g:6116:57: (iv_ruleJvmTypeReference= ruleJvmTypeReference EOF )
            // InternalCheckCfg.g:6117:2: iv_ruleJvmTypeReference= ruleJvmTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmTypeReference=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmTypeReference; 
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
    // $ANTLR end "entryRuleJvmTypeReference"


    // $ANTLR start "ruleJvmTypeReference"
    // InternalCheckCfg.g:6123:1: ruleJvmTypeReference returns [EObject current=null] : ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )* ) | this_XFunctionTypeRef_3= ruleXFunctionTypeRef ) ;
    public final EObject ruleJvmTypeReference() throws RecognitionException {
        EObject current = null;

        EObject this_JvmParameterizedTypeReference_0 = null;

        EObject this_XFunctionTypeRef_3 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6129:2: ( ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )* ) | this_XFunctionTypeRef_3= ruleXFunctionTypeRef ) )
            // InternalCheckCfg.g:6130:2: ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )* ) | this_XFunctionTypeRef_3= ruleXFunctionTypeRef )
            {
            // InternalCheckCfg.g:6130:2: ( (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )* ) | this_XFunctionTypeRef_3= ruleXFunctionTypeRef )
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==RULE_ID) ) {
                alt108=1;
            }
            else if ( (LA108_0==19||LA108_0==44) ) {
                alt108=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 108, 0, input);

                throw nvae;
            }
            switch (alt108) {
                case 1 :
                    // InternalCheckCfg.g:6131:3: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )* )
                    {
                    // InternalCheckCfg.g:6131:3: (this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )* )
                    // InternalCheckCfg.g:6132:4: this_JvmParameterizedTypeReference_0= ruleJvmParameterizedTypeReference ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )*
                    {
                    if ( state.backtracking==0 ) {

                      				newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getJvmParameterizedTypeReferenceParserRuleCall_0_0());
                      			
                    }
                    pushFollow(FOLLOW_75);
                    this_JvmParameterizedTypeReference_0=ruleJvmParameterizedTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = this_JvmParameterizedTypeReference_0;
                      				afterParserOrEnumRuleCall();
                      			
                    }
                    // InternalCheckCfg.g:6140:4: ( ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets ) )*
                    loop107:
                    do {
                        int alt107=2;
                        int LA107_0 = input.LA(1);

                        if ( (LA107_0==24) ) {
                            int LA107_2 = input.LA(2);

                            if ( (LA107_2==25) ) {
                                int LA107_3 = input.LA(3);

                                if ( (synpred43_InternalCheckCfg()) ) {
                                    alt107=1;
                                }


                            }


                        }


                        switch (alt107) {
                    	case 1 :
                    	    // InternalCheckCfg.g:6141:5: ( ( () ruleArrayBrackets ) )=> ( () ruleArrayBrackets )
                    	    {
                    	    // InternalCheckCfg.g:6147:5: ( () ruleArrayBrackets )
                    	    // InternalCheckCfg.g:6148:6: () ruleArrayBrackets
                    	    {
                    	    // InternalCheckCfg.g:6148:6: ()
                    	    // InternalCheckCfg.g:6149:7: 
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							current = forceCreateModelElementAndSet(
                    	      								grammarAccess.getJvmTypeReferenceAccess().getJvmGenericArrayTypeReferenceComponentTypeAction_0_1_0_0(),
                    	      								current);
                    	      						
                    	    }

                    	    }

                    	    if ( state.backtracking==0 ) {

                    	      						newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getArrayBracketsParserRuleCall_0_1_0_1());
                    	      					
                    	    }
                    	    pushFollow(FOLLOW_75);
                    	    ruleArrayBrackets();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      						afterParserOrEnumRuleCall();
                    	      					
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop107;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:6166:3: this_XFunctionTypeRef_3= ruleXFunctionTypeRef
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getXFunctionTypeRefParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_XFunctionTypeRef_3=ruleXFunctionTypeRef();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_XFunctionTypeRef_3;
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
    // $ANTLR end "ruleJvmTypeReference"


    // $ANTLR start "entryRuleArrayBrackets"
    // InternalCheckCfg.g:6178:1: entryRuleArrayBrackets returns [String current=null] : iv_ruleArrayBrackets= ruleArrayBrackets EOF ;
    public final String entryRuleArrayBrackets() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleArrayBrackets = null;


        try {
            // InternalCheckCfg.g:6178:53: (iv_ruleArrayBrackets= ruleArrayBrackets EOF )
            // InternalCheckCfg.g:6179:2: iv_ruleArrayBrackets= ruleArrayBrackets EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getArrayBracketsRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleArrayBrackets=ruleArrayBrackets();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleArrayBrackets.getText(); 
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
    // $ANTLR end "entryRuleArrayBrackets"


    // $ANTLR start "ruleArrayBrackets"
    // InternalCheckCfg.g:6185:1: ruleArrayBrackets returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '[' kw= ']' ) ;
    public final AntlrDatatypeRuleToken ruleArrayBrackets() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:6191:2: ( (kw= '[' kw= ']' ) )
            // InternalCheckCfg.g:6192:2: (kw= '[' kw= ']' )
            {
            // InternalCheckCfg.g:6192:2: (kw= '[' kw= ']' )
            // InternalCheckCfg.g:6193:3: kw= '[' kw= ']'
            {
            kw=(Token)match(input,24,FOLLOW_53); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(kw);
              			newLeafNode(kw, grammarAccess.getArrayBracketsAccess().getLeftSquareBracketKeyword_0());
              		
            }
            kw=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(kw);
              			newLeafNode(kw, grammarAccess.getArrayBracketsAccess().getRightSquareBracketKeyword_1());
              		
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
    // $ANTLR end "ruleArrayBrackets"


    // $ANTLR start "entryRuleXFunctionTypeRef"
    // InternalCheckCfg.g:6207:1: entryRuleXFunctionTypeRef returns [EObject current=null] : iv_ruleXFunctionTypeRef= ruleXFunctionTypeRef EOF ;
    public final EObject entryRuleXFunctionTypeRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionTypeRef = null;


        try {
            // InternalCheckCfg.g:6207:57: (iv_ruleXFunctionTypeRef= ruleXFunctionTypeRef EOF )
            // InternalCheckCfg.g:6208:2: iv_ruleXFunctionTypeRef= ruleXFunctionTypeRef EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXFunctionTypeRefRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXFunctionTypeRef=ruleXFunctionTypeRef();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXFunctionTypeRef; 
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
    // $ANTLR end "entryRuleXFunctionTypeRef"


    // $ANTLR start "ruleXFunctionTypeRef"
    // InternalCheckCfg.g:6214:1: ruleXFunctionTypeRef returns [EObject current=null] : ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleXFunctionTypeRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_paramTypes_1_0 = null;

        EObject lv_paramTypes_3_0 = null;

        EObject lv_returnType_6_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6220:2: ( ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) ) )
            // InternalCheckCfg.g:6221:2: ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) )
            {
            // InternalCheckCfg.g:6221:2: ( (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) ) )
            // InternalCheckCfg.g:6222:3: (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )? otherlv_5= '=>' ( (lv_returnType_6_0= ruleJvmTypeReference ) )
            {
            // InternalCheckCfg.g:6222:3: (otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')' )?
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==19) ) {
                alt111=1;
            }
            switch (alt111) {
                case 1 :
                    // InternalCheckCfg.g:6223:4: otherlv_0= '(' ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )? otherlv_4= ')'
                    {
                    otherlv_0=(Token)match(input,19,FOLLOW_83); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_0, grammarAccess.getXFunctionTypeRefAccess().getLeftParenthesisKeyword_0_0());
                      			
                    }
                    // InternalCheckCfg.g:6227:4: ( ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )* )?
                    int alt110=2;
                    int LA110_0 = input.LA(1);

                    if ( (LA110_0==RULE_ID||LA110_0==19||LA110_0==44) ) {
                        alt110=1;
                    }
                    switch (alt110) {
                        case 1 :
                            // InternalCheckCfg.g:6228:5: ( (lv_paramTypes_1_0= ruleJvmTypeReference ) ) (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )*
                            {
                            // InternalCheckCfg.g:6228:5: ( (lv_paramTypes_1_0= ruleJvmTypeReference ) )
                            // InternalCheckCfg.g:6229:6: (lv_paramTypes_1_0= ruleJvmTypeReference )
                            {
                            // InternalCheckCfg.g:6229:6: (lv_paramTypes_1_0= ruleJvmTypeReference )
                            // InternalCheckCfg.g:6230:7: lv_paramTypes_1_0= ruleJvmTypeReference
                            {
                            if ( state.backtracking==0 ) {

                              							newCompositeNode(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_0_0());
                              						
                            }
                            pushFollow(FOLLOW_13);
                            lv_paramTypes_1_0=ruleJvmTypeReference();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElementForParent(grammarAccess.getXFunctionTypeRefRule());
                              							}
                              							add(
                              								current,
                              								"paramTypes",
                              								lv_paramTypes_1_0,
                              								"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
                              							afterParserOrEnumRuleCall();
                              						
                            }

                            }


                            }

                            // InternalCheckCfg.g:6247:5: (otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) ) )*
                            loop109:
                            do {
                                int alt109=2;
                                int LA109_0 = input.LA(1);

                                if ( (LA109_0==20) ) {
                                    alt109=1;
                                }


                                switch (alt109) {
                            	case 1 :
                            	    // InternalCheckCfg.g:6248:6: otherlv_2= ',' ( (lv_paramTypes_3_0= ruleJvmTypeReference ) )
                            	    {
                            	    otherlv_2=(Token)match(input,20,FOLLOW_29); if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      						newLeafNode(otherlv_2, grammarAccess.getXFunctionTypeRefAccess().getCommaKeyword_0_1_1_0());
                            	      					
                            	    }
                            	    // InternalCheckCfg.g:6252:6: ( (lv_paramTypes_3_0= ruleJvmTypeReference ) )
                            	    // InternalCheckCfg.g:6253:7: (lv_paramTypes_3_0= ruleJvmTypeReference )
                            	    {
                            	    // InternalCheckCfg.g:6253:7: (lv_paramTypes_3_0= ruleJvmTypeReference )
                            	    // InternalCheckCfg.g:6254:8: lv_paramTypes_3_0= ruleJvmTypeReference
                            	    {
                            	    if ( state.backtracking==0 ) {

                            	      								newCompositeNode(grammarAccess.getXFunctionTypeRefAccess().getParamTypesJvmTypeReferenceParserRuleCall_0_1_1_1_0());
                            	      							
                            	    }
                            	    pushFollow(FOLLOW_13);
                            	    lv_paramTypes_3_0=ruleJvmTypeReference();

                            	    state._fsp--;
                            	    if (state.failed) return current;
                            	    if ( state.backtracking==0 ) {

                            	      								if (current==null) {
                            	      									current = createModelElementForParent(grammarAccess.getXFunctionTypeRefRule());
                            	      								}
                            	      								add(
                            	      									current,
                            	      									"paramTypes",
                            	      									lv_paramTypes_3_0,
                            	      									"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
                            	      								afterParserOrEnumRuleCall();
                            	      							
                            	    }

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop109;
                                }
                            } while (true);


                            }
                            break;

                    }

                    otherlv_4=(Token)match(input,21,FOLLOW_84); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getXFunctionTypeRefAccess().getRightParenthesisKeyword_0_2());
                      			
                    }

                    }
                    break;

            }

            otherlv_5=(Token)match(input,44,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_5, grammarAccess.getXFunctionTypeRefAccess().getEqualsSignGreaterThanSignKeyword_1());
              		
            }
            // InternalCheckCfg.g:6282:3: ( (lv_returnType_6_0= ruleJvmTypeReference ) )
            // InternalCheckCfg.g:6283:4: (lv_returnType_6_0= ruleJvmTypeReference )
            {
            // InternalCheckCfg.g:6283:4: (lv_returnType_6_0= ruleJvmTypeReference )
            // InternalCheckCfg.g:6284:5: lv_returnType_6_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getXFunctionTypeRefAccess().getReturnTypeJvmTypeReferenceParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_returnType_6_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getXFunctionTypeRefRule());
              					}
              					set(
              						current,
              						"returnType",
              						lv_returnType_6_0,
              						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
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
    // $ANTLR end "ruleXFunctionTypeRef"


    // $ANTLR start "entryRuleJvmParameterizedTypeReference"
    // InternalCheckCfg.g:6305:1: entryRuleJvmParameterizedTypeReference returns [EObject current=null] : iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF ;
    public final EObject entryRuleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmParameterizedTypeReference = null;


        try {
            // InternalCheckCfg.g:6305:70: (iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF )
            // InternalCheckCfg.g:6306:2: iv_ruleJvmParameterizedTypeReference= ruleJvmParameterizedTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmParameterizedTypeReference=ruleJvmParameterizedTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmParameterizedTypeReference; 
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
    // $ANTLR end "entryRuleJvmParameterizedTypeReference"


    // $ANTLR start "ruleJvmParameterizedTypeReference"
    // InternalCheckCfg.g:6312:1: ruleJvmParameterizedTypeReference returns [EObject current=null] : ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )* )? ) ;
    public final EObject ruleJvmParameterizedTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        EObject lv_arguments_2_0 = null;

        EObject lv_arguments_4_0 = null;

        EObject lv_arguments_10_0 = null;

        EObject lv_arguments_12_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6318:2: ( ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )* )? ) )
            // InternalCheckCfg.g:6319:2: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )* )? )
            {
            // InternalCheckCfg.g:6319:2: ( ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )* )? )
            // InternalCheckCfg.g:6320:3: ( ( ruleQualifiedName ) ) ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )* )?
            {
            // InternalCheckCfg.g:6320:3: ( ( ruleQualifiedName ) )
            // InternalCheckCfg.g:6321:4: ( ruleQualifiedName )
            {
            // InternalCheckCfg.g:6321:4: ( ruleQualifiedName )
            // InternalCheckCfg.g:6322:5: ruleQualifiedName
            {
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElement(grammarAccess.getJvmParameterizedTypeReferenceRule());
              					}
              				
            }
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_0_0());
              				
            }
            pushFollow(FOLLOW_85);
            ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					afterParserOrEnumRuleCall();
              				
            }

            }


            }

            // InternalCheckCfg.g:6336:3: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )* )?
            int alt116=2;
            alt116 = dfa116.predict(input);
            switch (alt116) {
                case 1 :
                    // InternalCheckCfg.g:6337:4: ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )*
                    {
                    // InternalCheckCfg.g:6337:4: ( ( '<' )=>otherlv_1= '<' )
                    // InternalCheckCfg.g:6338:5: ( '<' )=>otherlv_1= '<'
                    {
                    otherlv_1=(Token)match(input,31,FOLLOW_41); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_1, grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_0());
                      				
                    }

                    }

                    // InternalCheckCfg.g:6344:4: ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) )
                    // InternalCheckCfg.g:6345:5: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    {
                    // InternalCheckCfg.g:6345:5: (lv_arguments_2_0= ruleJvmArgumentTypeReference )
                    // InternalCheckCfg.g:6346:6: lv_arguments_2_0= ruleJvmArgumentTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_42);
                    lv_arguments_2_0=ruleJvmArgumentTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                      						}
                      						add(
                      							current,
                      							"arguments",
                      							lv_arguments_2_0,
                      							"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }

                    // InternalCheckCfg.g:6363:4: (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )*
                    loop112:
                    do {
                        int alt112=2;
                        int LA112_0 = input.LA(1);

                        if ( (LA112_0==20) ) {
                            alt112=1;
                        }


                        switch (alt112) {
                    	case 1 :
                    	    // InternalCheckCfg.g:6364:5: otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    {
                    	    otherlv_3=(Token)match(input,20,FOLLOW_41); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_2_0());
                    	      				
                    	    }
                    	    // InternalCheckCfg.g:6368:5: ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) )
                    	    // InternalCheckCfg.g:6369:6: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    {
                    	    // InternalCheckCfg.g:6369:6: (lv_arguments_4_0= ruleJvmArgumentTypeReference )
                    	    // InternalCheckCfg.g:6370:7: lv_arguments_4_0= ruleJvmArgumentTypeReference
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_2_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_42);
                    	    lv_arguments_4_0=ruleJvmArgumentTypeReference();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"arguments",
                    	      								lv_arguments_4_0,
                    	      								"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop112;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,32,FOLLOW_81); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_5, grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_3());
                      			
                    }
                    // InternalCheckCfg.g:6392:4: ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )*
                    loop115:
                    do {
                        int alt115=2;
                        int LA115_0 = input.LA(1);

                        if ( (LA115_0==57) ) {
                            int LA115_2 = input.LA(2);

                            if ( (LA115_2==RULE_ID) ) {
                                int LA115_3 = input.LA(3);

                                if ( (synpred45_InternalCheckCfg()) ) {
                                    alt115=1;
                                }


                            }


                        }


                        switch (alt115) {
                    	case 1 :
                    	    // InternalCheckCfg.g:6393:5: ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )?
                    	    {
                    	    // InternalCheckCfg.g:6393:5: ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) )
                    	    // InternalCheckCfg.g:6394:6: ( ( () '.' ) )=> ( () otherlv_7= '.' )
                    	    {
                    	    // InternalCheckCfg.g:6400:6: ( () otherlv_7= '.' )
                    	    // InternalCheckCfg.g:6401:7: () otherlv_7= '.'
                    	    {
                    	    // InternalCheckCfg.g:6401:7: ()
                    	    // InternalCheckCfg.g:6402:8: 
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      								current = forceCreateModelElementAndSet(
                    	      									grammarAccess.getJvmParameterizedTypeReferenceAccess().getJvmInnerTypeReferenceOuterAction_1_4_0_0_0(),
                    	      									current);
                    	      							
                    	    }

                    	    }

                    	    otherlv_7=(Token)match(input,57,FOLLOW_4); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							newLeafNode(otherlv_7, grammarAccess.getJvmParameterizedTypeReferenceAccess().getFullStopKeyword_1_4_0_0_1());
                    	      						
                    	    }

                    	    }


                    	    }

                    	    // InternalCheckCfg.g:6414:5: ( ( ruleValidID ) )
                    	    // InternalCheckCfg.g:6415:6: ( ruleValidID )
                    	    {
                    	    // InternalCheckCfg.g:6415:6: ( ruleValidID )
                    	    // InternalCheckCfg.g:6416:7: ruleValidID
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElement(grammarAccess.getJvmParameterizedTypeReferenceRule());
                    	      							}
                    	      						
                    	    }
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getTypeJvmTypeCrossReference_1_4_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_86);
                    	    ruleValidID();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }

                    	    // InternalCheckCfg.g:6430:5: ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )?
                    	    int alt114=2;
                    	    alt114 = dfa114.predict(input);
                    	    switch (alt114) {
                    	        case 1 :
                    	            // InternalCheckCfg.g:6431:6: ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>'
                    	            {
                    	            // InternalCheckCfg.g:6431:6: ( ( '<' )=>otherlv_9= '<' )
                    	            // InternalCheckCfg.g:6432:7: ( '<' )=>otherlv_9= '<'
                    	            {
                    	            otherlv_9=(Token)match(input,31,FOLLOW_41); if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              							newLeafNode(otherlv_9, grammarAccess.getJvmParameterizedTypeReferenceAccess().getLessThanSignKeyword_1_4_2_0());
                    	              						
                    	            }

                    	            }

                    	            // InternalCheckCfg.g:6438:6: ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) )
                    	            // InternalCheckCfg.g:6439:7: (lv_arguments_10_0= ruleJvmArgumentTypeReference )
                    	            {
                    	            // InternalCheckCfg.g:6439:7: (lv_arguments_10_0= ruleJvmArgumentTypeReference )
                    	            // InternalCheckCfg.g:6440:8: lv_arguments_10_0= ruleJvmArgumentTypeReference
                    	            {
                    	            if ( state.backtracking==0 ) {

                    	              								newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_4_2_1_0());
                    	              							
                    	            }
                    	            pushFollow(FOLLOW_42);
                    	            lv_arguments_10_0=ruleJvmArgumentTypeReference();

                    	            state._fsp--;
                    	            if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              								if (current==null) {
                    	              									current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                    	              								}
                    	              								add(
                    	              									current,
                    	              									"arguments",
                    	              									lv_arguments_10_0,
                    	              									"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                    	              								afterParserOrEnumRuleCall();
                    	              							
                    	            }

                    	            }


                    	            }

                    	            // InternalCheckCfg.g:6457:6: (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )*
                    	            loop113:
                    	            do {
                    	                int alt113=2;
                    	                int LA113_0 = input.LA(1);

                    	                if ( (LA113_0==20) ) {
                    	                    alt113=1;
                    	                }


                    	                switch (alt113) {
                    	            	case 1 :
                    	            	    // InternalCheckCfg.g:6458:7: otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) )
                    	            	    {
                    	            	    otherlv_11=(Token)match(input,20,FOLLOW_41); if (state.failed) return current;
                    	            	    if ( state.backtracking==0 ) {

                    	            	      							newLeafNode(otherlv_11, grammarAccess.getJvmParameterizedTypeReferenceAccess().getCommaKeyword_1_4_2_2_0());
                    	            	      						
                    	            	    }
                    	            	    // InternalCheckCfg.g:6462:7: ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) )
                    	            	    // InternalCheckCfg.g:6463:8: (lv_arguments_12_0= ruleJvmArgumentTypeReference )
                    	            	    {
                    	            	    // InternalCheckCfg.g:6463:8: (lv_arguments_12_0= ruleJvmArgumentTypeReference )
                    	            	    // InternalCheckCfg.g:6464:9: lv_arguments_12_0= ruleJvmArgumentTypeReference
                    	            	    {
                    	            	    if ( state.backtracking==0 ) {

                    	            	      									newCompositeNode(grammarAccess.getJvmParameterizedTypeReferenceAccess().getArgumentsJvmArgumentTypeReferenceParserRuleCall_1_4_2_2_1_0());
                    	            	      								
                    	            	    }
                    	            	    pushFollow(FOLLOW_42);
                    	            	    lv_arguments_12_0=ruleJvmArgumentTypeReference();

                    	            	    state._fsp--;
                    	            	    if (state.failed) return current;
                    	            	    if ( state.backtracking==0 ) {

                    	            	      									if (current==null) {
                    	            	      										current = createModelElementForParent(grammarAccess.getJvmParameterizedTypeReferenceRule());
                    	            	      									}
                    	            	      									add(
                    	            	      										current,
                    	            	      										"arguments",
                    	            	      										lv_arguments_12_0,
                    	            	      										"org.eclipse.xtext.xbase.Xtype.JvmArgumentTypeReference");
                    	            	      									afterParserOrEnumRuleCall();
                    	            	      								
                    	            	    }

                    	            	    }


                    	            	    }


                    	            	    }
                    	            	    break;

                    	            	default :
                    	            	    break loop113;
                    	                }
                    	            } while (true);

                    	            otherlv_13=(Token)match(input,32,FOLLOW_81); if (state.failed) return current;
                    	            if ( state.backtracking==0 ) {

                    	              						newLeafNode(otherlv_13, grammarAccess.getJvmParameterizedTypeReferenceAccess().getGreaterThanSignKeyword_1_4_2_3());
                    	              					
                    	            }

                    	            }
                    	            break;

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop115;
                        }
                    } while (true);


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
    // $ANTLR end "ruleJvmParameterizedTypeReference"


    // $ANTLR start "entryRuleJvmArgumentTypeReference"
    // InternalCheckCfg.g:6493:1: entryRuleJvmArgumentTypeReference returns [EObject current=null] : iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF ;
    public final EObject entryRuleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmArgumentTypeReference = null;


        try {
            // InternalCheckCfg.g:6493:65: (iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF )
            // InternalCheckCfg.g:6494:2: iv_ruleJvmArgumentTypeReference= ruleJvmArgumentTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmArgumentTypeReference=ruleJvmArgumentTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmArgumentTypeReference; 
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
    // $ANTLR end "entryRuleJvmArgumentTypeReference"


    // $ANTLR start "ruleJvmArgumentTypeReference"
    // InternalCheckCfg.g:6500:1: ruleJvmArgumentTypeReference returns [EObject current=null] : (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) ;
    public final EObject ruleJvmArgumentTypeReference() throws RecognitionException {
        EObject current = null;

        EObject this_JvmTypeReference_0 = null;

        EObject this_JvmWildcardTypeReference_1 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6506:2: ( (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference ) )
            // InternalCheckCfg.g:6507:2: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
            {
            // InternalCheckCfg.g:6507:2: (this_JvmTypeReference_0= ruleJvmTypeReference | this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference )
            int alt117=2;
            int LA117_0 = input.LA(1);

            if ( (LA117_0==RULE_ID||LA117_0==19||LA117_0==44) ) {
                alt117=1;
            }
            else if ( (LA117_0==88) ) {
                alt117=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 117, 0, input);

                throw nvae;
            }
            switch (alt117) {
                case 1 :
                    // InternalCheckCfg.g:6508:3: this_JvmTypeReference_0= ruleJvmTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmTypeReferenceParserRuleCall_0());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_JvmTypeReference_0=ruleJvmTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_JvmTypeReference_0;
                      			afterParserOrEnumRuleCall();
                      		
                    }

                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:6517:3: this_JvmWildcardTypeReference_1= ruleJvmWildcardTypeReference
                    {
                    if ( state.backtracking==0 ) {

                      			newCompositeNode(grammarAccess.getJvmArgumentTypeReferenceAccess().getJvmWildcardTypeReferenceParserRuleCall_1());
                      		
                    }
                    pushFollow(FOLLOW_2);
                    this_JvmWildcardTypeReference_1=ruleJvmWildcardTypeReference();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			current = this_JvmWildcardTypeReference_1;
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
    // $ANTLR end "ruleJvmArgumentTypeReference"


    // $ANTLR start "entryRuleJvmWildcardTypeReference"
    // InternalCheckCfg.g:6529:1: entryRuleJvmWildcardTypeReference returns [EObject current=null] : iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF ;
    public final EObject entryRuleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmWildcardTypeReference = null;


        try {
            // InternalCheckCfg.g:6529:65: (iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF )
            // InternalCheckCfg.g:6530:2: iv_ruleJvmWildcardTypeReference= ruleJvmWildcardTypeReference EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmWildcardTypeReference=ruleJvmWildcardTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmWildcardTypeReference; 
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
    // $ANTLR end "entryRuleJvmWildcardTypeReference"


    // $ANTLR start "ruleJvmWildcardTypeReference"
    // InternalCheckCfg.g:6536:1: ruleJvmWildcardTypeReference returns [EObject current=null] : ( () otherlv_1= '?' ( ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* ) | ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* ) )? ) ;
    public final EObject ruleJvmWildcardTypeReference() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_constraints_2_0 = null;

        EObject lv_constraints_3_0 = null;

        EObject lv_constraints_4_0 = null;

        EObject lv_constraints_5_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6542:2: ( ( () otherlv_1= '?' ( ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* ) | ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* ) )? ) )
            // InternalCheckCfg.g:6543:2: ( () otherlv_1= '?' ( ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* ) | ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* ) )? )
            {
            // InternalCheckCfg.g:6543:2: ( () otherlv_1= '?' ( ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* ) | ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* ) )? )
            // InternalCheckCfg.g:6544:3: () otherlv_1= '?' ( ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* ) | ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* ) )?
            {
            // InternalCheckCfg.g:6544:3: ()
            // InternalCheckCfg.g:6545:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getJvmWildcardTypeReferenceAccess().getJvmWildcardTypeReferenceAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,88,FOLLOW_87); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getJvmWildcardTypeReferenceAccess().getQuestionMarkKeyword_1());
              		
            }
            // InternalCheckCfg.g:6555:3: ( ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* ) | ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* ) )?
            int alt120=3;
            int LA120_0 = input.LA(1);

            if ( (LA120_0==72) ) {
                alt120=1;
            }
            else if ( (LA120_0==76) ) {
                alt120=2;
            }
            switch (alt120) {
                case 1 :
                    // InternalCheckCfg.g:6556:4: ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* )
                    {
                    // InternalCheckCfg.g:6556:4: ( ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )* )
                    // InternalCheckCfg.g:6557:5: ( (lv_constraints_2_0= ruleJvmUpperBound ) ) ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )*
                    {
                    // InternalCheckCfg.g:6557:5: ( (lv_constraints_2_0= ruleJvmUpperBound ) )
                    // InternalCheckCfg.g:6558:6: (lv_constraints_2_0= ruleJvmUpperBound )
                    {
                    // InternalCheckCfg.g:6558:6: (lv_constraints_2_0= ruleJvmUpperBound )
                    // InternalCheckCfg.g:6559:7: lv_constraints_2_0= ruleJvmUpperBound
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundParserRuleCall_2_0_0_0());
                      						
                    }
                    pushFollow(FOLLOW_88);
                    lv_constraints_2_0=ruleJvmUpperBound();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                      							}
                      							add(
                      								current,
                      								"constraints",
                      								lv_constraints_2_0,
                      								"org.eclipse.xtext.xbase.Xtype.JvmUpperBound");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }

                    // InternalCheckCfg.g:6576:5: ( (lv_constraints_3_0= ruleJvmUpperBoundAnded ) )*
                    loop118:
                    do {
                        int alt118=2;
                        int LA118_0 = input.LA(1);

                        if ( (LA118_0==89) ) {
                            alt118=1;
                        }


                        switch (alt118) {
                    	case 1 :
                    	    // InternalCheckCfg.g:6577:6: (lv_constraints_3_0= ruleJvmUpperBoundAnded )
                    	    {
                    	    // InternalCheckCfg.g:6577:6: (lv_constraints_3_0= ruleJvmUpperBoundAnded )
                    	    // InternalCheckCfg.g:6578:7: lv_constraints_3_0= ruleJvmUpperBoundAnded
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmUpperBoundAndedParserRuleCall_2_0_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_88);
                    	    lv_constraints_3_0=ruleJvmUpperBoundAnded();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"constraints",
                    	      								lv_constraints_3_0,
                    	      								"org.eclipse.xtext.xbase.Xtype.JvmUpperBoundAnded");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop118;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:6597:4: ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* )
                    {
                    // InternalCheckCfg.g:6597:4: ( ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )* )
                    // InternalCheckCfg.g:6598:5: ( (lv_constraints_4_0= ruleJvmLowerBound ) ) ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )*
                    {
                    // InternalCheckCfg.g:6598:5: ( (lv_constraints_4_0= ruleJvmLowerBound ) )
                    // InternalCheckCfg.g:6599:6: (lv_constraints_4_0= ruleJvmLowerBound )
                    {
                    // InternalCheckCfg.g:6599:6: (lv_constraints_4_0= ruleJvmLowerBound )
                    // InternalCheckCfg.g:6600:7: lv_constraints_4_0= ruleJvmLowerBound
                    {
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundParserRuleCall_2_1_0_0());
                      						
                    }
                    pushFollow(FOLLOW_88);
                    lv_constraints_4_0=ruleJvmLowerBound();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                      							}
                      							add(
                      								current,
                      								"constraints",
                      								lv_constraints_4_0,
                      								"org.eclipse.xtext.xbase.Xtype.JvmLowerBound");
                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }

                    // InternalCheckCfg.g:6617:5: ( (lv_constraints_5_0= ruleJvmLowerBoundAnded ) )*
                    loop119:
                    do {
                        int alt119=2;
                        int LA119_0 = input.LA(1);

                        if ( (LA119_0==89) ) {
                            alt119=1;
                        }


                        switch (alt119) {
                    	case 1 :
                    	    // InternalCheckCfg.g:6618:6: (lv_constraints_5_0= ruleJvmLowerBoundAnded )
                    	    {
                    	    // InternalCheckCfg.g:6618:6: (lv_constraints_5_0= ruleJvmLowerBoundAnded )
                    	    // InternalCheckCfg.g:6619:7: lv_constraints_5_0= ruleJvmLowerBoundAnded
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getJvmWildcardTypeReferenceAccess().getConstraintsJvmLowerBoundAndedParserRuleCall_2_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_88);
                    	    lv_constraints_5_0=ruleJvmLowerBoundAnded();

                    	    state._fsp--;
                    	    if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      							if (current==null) {
                    	      								current = createModelElementForParent(grammarAccess.getJvmWildcardTypeReferenceRule());
                    	      							}
                    	      							add(
                    	      								current,
                    	      								"constraints",
                    	      								lv_constraints_5_0,
                    	      								"org.eclipse.xtext.xbase.Xtype.JvmLowerBoundAnded");
                    	      							afterParserOrEnumRuleCall();
                    	      						
                    	    }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop119;
                        }
                    } while (true);


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
    // $ANTLR end "ruleJvmWildcardTypeReference"


    // $ANTLR start "entryRuleJvmUpperBound"
    // InternalCheckCfg.g:6642:1: entryRuleJvmUpperBound returns [EObject current=null] : iv_ruleJvmUpperBound= ruleJvmUpperBound EOF ;
    public final EObject entryRuleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmUpperBound = null;


        try {
            // InternalCheckCfg.g:6642:54: (iv_ruleJvmUpperBound= ruleJvmUpperBound EOF )
            // InternalCheckCfg.g:6643:2: iv_ruleJvmUpperBound= ruleJvmUpperBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmUpperBoundRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmUpperBound=ruleJvmUpperBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmUpperBound; 
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
    // $ANTLR end "entryRuleJvmUpperBound"


    // $ANTLR start "ruleJvmUpperBound"
    // InternalCheckCfg.g:6649:1: ruleJvmUpperBound returns [EObject current=null] : (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmUpperBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6655:2: ( (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // InternalCheckCfg.g:6656:2: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // InternalCheckCfg.g:6656:2: (otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // InternalCheckCfg.g:6657:3: otherlv_0= 'extends' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,72,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getJvmUpperBoundAccess().getExtendsKeyword_0());
              		
            }
            // InternalCheckCfg.g:6661:3: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // InternalCheckCfg.g:6662:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // InternalCheckCfg.g:6662:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            // InternalCheckCfg.g:6663:5: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getJvmUpperBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getJvmUpperBoundRule());
              					}
              					set(
              						current,
              						"typeReference",
              						lv_typeReference_1_0,
              						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
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
    // $ANTLR end "ruleJvmUpperBound"


    // $ANTLR start "entryRuleJvmUpperBoundAnded"
    // InternalCheckCfg.g:6684:1: entryRuleJvmUpperBoundAnded returns [EObject current=null] : iv_ruleJvmUpperBoundAnded= ruleJvmUpperBoundAnded EOF ;
    public final EObject entryRuleJvmUpperBoundAnded() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmUpperBoundAnded = null;


        try {
            // InternalCheckCfg.g:6684:59: (iv_ruleJvmUpperBoundAnded= ruleJvmUpperBoundAnded EOF )
            // InternalCheckCfg.g:6685:2: iv_ruleJvmUpperBoundAnded= ruleJvmUpperBoundAnded EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmUpperBoundAndedRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmUpperBoundAnded=ruleJvmUpperBoundAnded();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmUpperBoundAnded; 
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
    // $ANTLR end "entryRuleJvmUpperBoundAnded"


    // $ANTLR start "ruleJvmUpperBoundAnded"
    // InternalCheckCfg.g:6691:1: ruleJvmUpperBoundAnded returns [EObject current=null] : (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmUpperBoundAnded() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6697:2: ( (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // InternalCheckCfg.g:6698:2: (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // InternalCheckCfg.g:6698:2: (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // InternalCheckCfg.g:6699:3: otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,89,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getJvmUpperBoundAndedAccess().getAmpersandKeyword_0());
              		
            }
            // InternalCheckCfg.g:6703:3: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // InternalCheckCfg.g:6704:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // InternalCheckCfg.g:6704:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            // InternalCheckCfg.g:6705:5: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getJvmUpperBoundAndedAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getJvmUpperBoundAndedRule());
              					}
              					set(
              						current,
              						"typeReference",
              						lv_typeReference_1_0,
              						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
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
    // $ANTLR end "ruleJvmUpperBoundAnded"


    // $ANTLR start "entryRuleJvmLowerBound"
    // InternalCheckCfg.g:6726:1: entryRuleJvmLowerBound returns [EObject current=null] : iv_ruleJvmLowerBound= ruleJvmLowerBound EOF ;
    public final EObject entryRuleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmLowerBound = null;


        try {
            // InternalCheckCfg.g:6726:54: (iv_ruleJvmLowerBound= ruleJvmLowerBound EOF )
            // InternalCheckCfg.g:6727:2: iv_ruleJvmLowerBound= ruleJvmLowerBound EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmLowerBoundRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmLowerBound=ruleJvmLowerBound();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmLowerBound; 
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
    // $ANTLR end "entryRuleJvmLowerBound"


    // $ANTLR start "ruleJvmLowerBound"
    // InternalCheckCfg.g:6733:1: ruleJvmLowerBound returns [EObject current=null] : (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmLowerBound() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6739:2: ( (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // InternalCheckCfg.g:6740:2: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // InternalCheckCfg.g:6740:2: (otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // InternalCheckCfg.g:6741:3: otherlv_0= 'super' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,76,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getJvmLowerBoundAccess().getSuperKeyword_0());
              		
            }
            // InternalCheckCfg.g:6745:3: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // InternalCheckCfg.g:6746:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // InternalCheckCfg.g:6746:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            // InternalCheckCfg.g:6747:5: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getJvmLowerBoundAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getJvmLowerBoundRule());
              					}
              					set(
              						current,
              						"typeReference",
              						lv_typeReference_1_0,
              						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
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
    // $ANTLR end "ruleJvmLowerBound"


    // $ANTLR start "entryRuleJvmLowerBoundAnded"
    // InternalCheckCfg.g:6768:1: entryRuleJvmLowerBoundAnded returns [EObject current=null] : iv_ruleJvmLowerBoundAnded= ruleJvmLowerBoundAnded EOF ;
    public final EObject entryRuleJvmLowerBoundAnded() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJvmLowerBoundAnded = null;


        try {
            // InternalCheckCfg.g:6768:59: (iv_ruleJvmLowerBoundAnded= ruleJvmLowerBoundAnded EOF )
            // InternalCheckCfg.g:6769:2: iv_ruleJvmLowerBoundAnded= ruleJvmLowerBoundAnded EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getJvmLowerBoundAndedRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleJvmLowerBoundAnded=ruleJvmLowerBoundAnded();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleJvmLowerBoundAnded; 
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
    // $ANTLR end "entryRuleJvmLowerBoundAnded"


    // $ANTLR start "ruleJvmLowerBoundAnded"
    // InternalCheckCfg.g:6775:1: ruleJvmLowerBoundAnded returns [EObject current=null] : (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) ;
    public final EObject ruleJvmLowerBoundAnded() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_typeReference_1_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6781:2: ( (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) ) )
            // InternalCheckCfg.g:6782:2: (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            {
            // InternalCheckCfg.g:6782:2: (otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) ) )
            // InternalCheckCfg.g:6783:3: otherlv_0= '&' ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            {
            otherlv_0=(Token)match(input,89,FOLLOW_29); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getJvmLowerBoundAndedAccess().getAmpersandKeyword_0());
              		
            }
            // InternalCheckCfg.g:6787:3: ( (lv_typeReference_1_0= ruleJvmTypeReference ) )
            // InternalCheckCfg.g:6788:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            {
            // InternalCheckCfg.g:6788:4: (lv_typeReference_1_0= ruleJvmTypeReference )
            // InternalCheckCfg.g:6789:5: lv_typeReference_1_0= ruleJvmTypeReference
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getJvmLowerBoundAndedAccess().getTypeReferenceJvmTypeReferenceParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_2);
            lv_typeReference_1_0=ruleJvmTypeReference();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              					if (current==null) {
              						current = createModelElementForParent(grammarAccess.getJvmLowerBoundAndedRule());
              					}
              					set(
              						current,
              						"typeReference",
              						lv_typeReference_1_0,
              						"org.eclipse.xtext.xbase.Xtype.JvmTypeReference");
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
    // $ANTLR end "ruleJvmLowerBoundAnded"


    // $ANTLR start "entryRuleQualifiedNameWithWildcard"
    // InternalCheckCfg.g:6810:1: entryRuleQualifiedNameWithWildcard returns [String current=null] : iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF ;
    public final String entryRuleQualifiedNameWithWildcard() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedNameWithWildcard = null;


        try {
            // InternalCheckCfg.g:6810:65: (iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF )
            // InternalCheckCfg.g:6811:2: iv_ruleQualifiedNameWithWildcard= ruleQualifiedNameWithWildcard EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedNameWithWildcard.getText(); 
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
    // $ANTLR end "entryRuleQualifiedNameWithWildcard"


    // $ANTLR start "ruleQualifiedNameWithWildcard"
    // InternalCheckCfg.g:6817:1: ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' ) ;
    public final AntlrDatatypeRuleToken ruleQualifiedNameWithWildcard() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_QualifiedName_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6823:2: ( (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' ) )
            // InternalCheckCfg.g:6824:2: (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' )
            {
            // InternalCheckCfg.g:6824:2: (this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*' )
            // InternalCheckCfg.g:6825:3: this_QualifiedName_0= ruleQualifiedName kw= '.' kw= '*'
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_89);
            this_QualifiedName_0=ruleQualifiedName();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(this_QualifiedName_0);
              		
            }
            if ( state.backtracking==0 ) {

              			afterParserOrEnumRuleCall();
              		
            }
            kw=(Token)match(input,57,FOLLOW_90); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(kw);
              			newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopKeyword_1());
              		
            }
            kw=(Token)match(input,49,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current.merge(kw);
              			newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getAsteriskKeyword_2());
              		
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
    // $ANTLR end "ruleQualifiedNameWithWildcard"


    // $ANTLR start "entryRuleValidID"
    // InternalCheckCfg.g:6849:1: entryRuleValidID returns [String current=null] : iv_ruleValidID= ruleValidID EOF ;
    public final String entryRuleValidID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleValidID = null;


        try {
            // InternalCheckCfg.g:6849:47: (iv_ruleValidID= ruleValidID EOF )
            // InternalCheckCfg.g:6850:2: iv_ruleValidID= ruleValidID EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getValidIDRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleValidID=ruleValidID();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleValidID.getText(); 
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
    // $ANTLR end "entryRuleValidID"


    // $ANTLR start "ruleValidID"
    // InternalCheckCfg.g:6856:1: ruleValidID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleValidID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:6862:2: (this_ID_0= RULE_ID )
            // InternalCheckCfg.g:6863:2: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_ID_0);
              	
            }
            if ( state.backtracking==0 ) {

              		newLeafNode(this_ID_0, grammarAccess.getValidIDAccess().getIDTerminalRuleCall());
              	
            }

            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValidID"


    // $ANTLR start "entryRuleXImportDeclaration"
    // InternalCheckCfg.g:6873:1: entryRuleXImportDeclaration returns [EObject current=null] : iv_ruleXImportDeclaration= ruleXImportDeclaration EOF ;
    public final EObject entryRuleXImportDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXImportDeclaration = null;


        try {
            // InternalCheckCfg.g:6873:59: (iv_ruleXImportDeclaration= ruleXImportDeclaration EOF )
            // InternalCheckCfg.g:6874:2: iv_ruleXImportDeclaration= ruleXImportDeclaration EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getXImportDeclarationRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleXImportDeclaration=ruleXImportDeclaration();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleXImportDeclaration; 
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
    // $ANTLR end "entryRuleXImportDeclaration"


    // $ANTLR start "ruleXImportDeclaration"
    // InternalCheckCfg.g:6880:1: ruleXImportDeclaration returns [EObject current=null] : (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) ) ) (otherlv_8= ';' )? ) ;
    public final EObject ruleXImportDeclaration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_static_1_0=null;
        Token lv_extension_2_0=null;
        Token lv_wildcard_4_0=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_memberName_5_0 = null;

        AntlrDatatypeRuleToken lv_importedNamespace_7_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:6886:2: ( (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) ) ) (otherlv_8= ';' )? ) )
            // InternalCheckCfg.g:6887:2: (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) ) ) (otherlv_8= ';' )? )
            {
            // InternalCheckCfg.g:6887:2: (otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) ) ) (otherlv_8= ';' )? )
            // InternalCheckCfg.g:6888:3: otherlv_0= 'import' ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) ) ) (otherlv_8= ';' )?
            {
            otherlv_0=(Token)match(input,74,FOLLOW_91); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getXImportDeclarationAccess().getImportKeyword_0());
              		
            }
            // InternalCheckCfg.g:6892:3: ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) ) )
            int alt123=3;
            alt123 = dfa123.predict(input);
            switch (alt123) {
                case 1 :
                    // InternalCheckCfg.g:6893:4: ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) )
                    {
                    // InternalCheckCfg.g:6893:4: ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) )
                    // InternalCheckCfg.g:6894:5: ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) )
                    {
                    // InternalCheckCfg.g:6894:5: ( (lv_static_1_0= 'static' ) )
                    // InternalCheckCfg.g:6895:6: (lv_static_1_0= 'static' )
                    {
                    // InternalCheckCfg.g:6895:6: (lv_static_1_0= 'static' )
                    // InternalCheckCfg.g:6896:7: lv_static_1_0= 'static'
                    {
                    lv_static_1_0=(Token)match(input,73,FOLLOW_92); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							newLeafNode(lv_static_1_0, grammarAccess.getXImportDeclarationAccess().getStaticStaticKeyword_1_0_0_0());
                      						
                    }
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElement(grammarAccess.getXImportDeclarationRule());
                      							}
                      							setWithLastConsumed(current, "static", lv_static_1_0 != null, "static");
                      						
                    }

                    }


                    }

                    // InternalCheckCfg.g:6908:5: ( (lv_extension_2_0= 'extension' ) )?
                    int alt121=2;
                    int LA121_0 = input.LA(1);

                    if ( (LA121_0==75) ) {
                        alt121=1;
                    }
                    switch (alt121) {
                        case 1 :
                            // InternalCheckCfg.g:6909:6: (lv_extension_2_0= 'extension' )
                            {
                            // InternalCheckCfg.g:6909:6: (lv_extension_2_0= 'extension' )
                            // InternalCheckCfg.g:6910:7: lv_extension_2_0= 'extension'
                            {
                            lv_extension_2_0=(Token)match(input,75,FOLLOW_92); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              							newLeafNode(lv_extension_2_0, grammarAccess.getXImportDeclarationAccess().getExtensionExtensionKeyword_1_0_1_0());
                              						
                            }
                            if ( state.backtracking==0 ) {

                              							if (current==null) {
                              								current = createModelElement(grammarAccess.getXImportDeclarationRule());
                              							}
                              							setWithLastConsumed(current, "extension", lv_extension_2_0 != null, "extension");
                              						
                            }

                            }


                            }
                            break;

                    }

                    // InternalCheckCfg.g:6922:5: ( ( ruleQualifiedNameInStaticImport ) )
                    // InternalCheckCfg.g:6923:6: ( ruleQualifiedNameInStaticImport )
                    {
                    // InternalCheckCfg.g:6923:6: ( ruleQualifiedNameInStaticImport )
                    // InternalCheckCfg.g:6924:7: ruleQualifiedNameInStaticImport
                    {
                    if ( state.backtracking==0 ) {

                      							if (current==null) {
                      								current = createModelElement(grammarAccess.getXImportDeclarationRule());
                      							}
                      						
                    }
                    if ( state.backtracking==0 ) {

                      							newCompositeNode(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeCrossReference_1_0_2_0());
                      						
                    }
                    pushFollow(FOLLOW_93);
                    ruleQualifiedNameInStaticImport();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      							afterParserOrEnumRuleCall();
                      						
                    }

                    }


                    }

                    // InternalCheckCfg.g:6938:5: ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) )
                    int alt122=2;
                    int LA122_0 = input.LA(1);

                    if ( (LA122_0==49) ) {
                        alt122=1;
                    }
                    else if ( (LA122_0==RULE_ID) ) {
                        alt122=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 122, 0, input);

                        throw nvae;
                    }
                    switch (alt122) {
                        case 1 :
                            // InternalCheckCfg.g:6939:6: ( (lv_wildcard_4_0= '*' ) )
                            {
                            // InternalCheckCfg.g:6939:6: ( (lv_wildcard_4_0= '*' ) )
                            // InternalCheckCfg.g:6940:7: (lv_wildcard_4_0= '*' )
                            {
                            // InternalCheckCfg.g:6940:7: (lv_wildcard_4_0= '*' )
                            // InternalCheckCfg.g:6941:8: lv_wildcard_4_0= '*'
                            {
                            lv_wildcard_4_0=(Token)match(input,49,FOLLOW_94); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								newLeafNode(lv_wildcard_4_0, grammarAccess.getXImportDeclarationAccess().getWildcardAsteriskKeyword_1_0_3_0_0());
                              							
                            }
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElement(grammarAccess.getXImportDeclarationRule());
                              								}
                              								setWithLastConsumed(current, "wildcard", lv_wildcard_4_0 != null, "*");
                              							
                            }

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalCheckCfg.g:6954:6: ( (lv_memberName_5_0= ruleValidID ) )
                            {
                            // InternalCheckCfg.g:6954:6: ( (lv_memberName_5_0= ruleValidID ) )
                            // InternalCheckCfg.g:6955:7: (lv_memberName_5_0= ruleValidID )
                            {
                            // InternalCheckCfg.g:6955:7: (lv_memberName_5_0= ruleValidID )
                            // InternalCheckCfg.g:6956:8: lv_memberName_5_0= ruleValidID
                            {
                            if ( state.backtracking==0 ) {

                              								newCompositeNode(grammarAccess.getXImportDeclarationAccess().getMemberNameValidIDParserRuleCall_1_0_3_1_0());
                              							
                            }
                            pushFollow(FOLLOW_94);
                            lv_memberName_5_0=ruleValidID();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              								if (current==null) {
                              									current = createModelElementForParent(grammarAccess.getXImportDeclarationRule());
                              								}
                              								set(
                              									current,
                              									"memberName",
                              									lv_memberName_5_0,
                              									"org.eclipse.xtext.xbase.Xtype.ValidID");
                              								afterParserOrEnumRuleCall();
                              							
                            }

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:6976:4: ( ( ruleQualifiedName ) )
                    {
                    // InternalCheckCfg.g:6976:4: ( ( ruleQualifiedName ) )
                    // InternalCheckCfg.g:6977:5: ( ruleQualifiedName )
                    {
                    // InternalCheckCfg.g:6977:5: ( ruleQualifiedName )
                    // InternalCheckCfg.g:6978:6: ruleQualifiedName
                    {
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElement(grammarAccess.getXImportDeclarationRule());
                      						}
                      					
                    }
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXImportDeclarationAccess().getImportedTypeJvmDeclaredTypeCrossReference_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_94);
                    ruleQualifiedName();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:6993:4: ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) )
                    {
                    // InternalCheckCfg.g:6993:4: ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) )
                    // InternalCheckCfg.g:6994:5: (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard )
                    {
                    // InternalCheckCfg.g:6994:5: (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard )
                    // InternalCheckCfg.g:6995:6: lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getXImportDeclarationAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_94);
                    lv_importedNamespace_7_0=ruleQualifiedNameWithWildcard();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      						if (current==null) {
                      							current = createModelElementForParent(grammarAccess.getXImportDeclarationRule());
                      						}
                      						set(
                      							current,
                      							"importedNamespace",
                      							lv_importedNamespace_7_0,
                      							"org.eclipse.xtext.xbase.Xtype.QualifiedNameWithWildcard");
                      						afterParserOrEnumRuleCall();
                      					
                    }

                    }


                    }


                    }
                    break;

            }

            // InternalCheckCfg.g:7013:3: (otherlv_8= ';' )?
            int alt124=2;
            int LA124_0 = input.LA(1);

            if ( (LA124_0==61) ) {
                alt124=1;
            }
            switch (alt124) {
                case 1 :
                    // InternalCheckCfg.g:7014:4: otherlv_8= ';'
                    {
                    otherlv_8=(Token)match(input,61,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_8, grammarAccess.getXImportDeclarationAccess().getSemicolonKeyword_2());
                      			
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
    // $ANTLR end "ruleXImportDeclaration"


    // $ANTLR start "entryRuleQualifiedNameInStaticImport"
    // InternalCheckCfg.g:7023:1: entryRuleQualifiedNameInStaticImport returns [String current=null] : iv_ruleQualifiedNameInStaticImport= ruleQualifiedNameInStaticImport EOF ;
    public final String entryRuleQualifiedNameInStaticImport() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQualifiedNameInStaticImport = null;


        try {
            // InternalCheckCfg.g:7023:67: (iv_ruleQualifiedNameInStaticImport= ruleQualifiedNameInStaticImport EOF )
            // InternalCheckCfg.g:7024:2: iv_ruleQualifiedNameInStaticImport= ruleQualifiedNameInStaticImport EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getQualifiedNameInStaticImportRule()); 
            }
            pushFollow(FOLLOW_1);
            iv_ruleQualifiedNameInStaticImport=ruleQualifiedNameInStaticImport();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleQualifiedNameInStaticImport.getText(); 
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
    // $ANTLR end "entryRuleQualifiedNameInStaticImport"


    // $ANTLR start "ruleQualifiedNameInStaticImport"
    // InternalCheckCfg.g:7030:1: ruleQualifiedNameInStaticImport returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ValidID_0= ruleValidID kw= '.' )+ ;
    public final AntlrDatatypeRuleToken ruleQualifiedNameInStaticImport() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        AntlrDatatypeRuleToken this_ValidID_0 = null;



        	enterRule();

        try {
            // InternalCheckCfg.g:7036:2: ( (this_ValidID_0= ruleValidID kw= '.' )+ )
            // InternalCheckCfg.g:7037:2: (this_ValidID_0= ruleValidID kw= '.' )+
            {
            // InternalCheckCfg.g:7037:2: (this_ValidID_0= ruleValidID kw= '.' )+
            int cnt125=0;
            loop125:
            do {
                int alt125=2;
                int LA125_0 = input.LA(1);

                if ( (LA125_0==RULE_ID) ) {
                    int LA125_2 = input.LA(2);

                    if ( (LA125_2==57) ) {
                        alt125=1;
                    }


                }


                switch (alt125) {
            	case 1 :
            	    // InternalCheckCfg.g:7038:3: this_ValidID_0= ruleValidID kw= '.'
            	    {
            	    if ( state.backtracking==0 ) {

            	      			newCompositeNode(grammarAccess.getQualifiedNameInStaticImportAccess().getValidIDParserRuleCall_0());
            	      		
            	    }
            	    pushFollow(FOLLOW_89);
            	    this_ValidID_0=ruleValidID();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      			current.merge(this_ValidID_0);
            	      		
            	    }
            	    if ( state.backtracking==0 ) {

            	      			afterParserOrEnumRuleCall();
            	      		
            	    }
            	    kw=(Token)match(input,57,FOLLOW_95); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      			current.merge(kw);
            	      			newLeafNode(kw, grammarAccess.getQualifiedNameInStaticImportAccess().getFullStopKeyword_1());
            	      		
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt125 >= 1 ) break loop125;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(125, input);
                        throw eee;
                }
                cnt125++;
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	leaveRule();

            }
        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQualifiedNameInStaticImport"


    // $ANTLR start "ruleSeverityKind"
    // InternalCheckCfg.g:7057:1: ruleSeverityKind returns [Enumerator current=null] : ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'error' ) | (enumLiteral_2= 'warning' ) | (enumLiteral_3= 'info' ) | (enumLiteral_4= 'ignore' ) ) ;
    public final Enumerator ruleSeverityKind() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;


        	enterRule();

        try {
            // InternalCheckCfg.g:7063:2: ( ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'error' ) | (enumLiteral_2= 'warning' ) | (enumLiteral_3= 'info' ) | (enumLiteral_4= 'ignore' ) ) )
            // InternalCheckCfg.g:7064:2: ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'error' ) | (enumLiteral_2= 'warning' ) | (enumLiteral_3= 'info' ) | (enumLiteral_4= 'ignore' ) )
            {
            // InternalCheckCfg.g:7064:2: ( (enumLiteral_0= 'default' ) | (enumLiteral_1= 'error' ) | (enumLiteral_2= 'warning' ) | (enumLiteral_3= 'info' ) | (enumLiteral_4= 'ignore' ) )
            int alt126=5;
            switch ( input.LA(1) ) {
            case 66:
                {
                alt126=1;
                }
                break;
            case 90:
                {
                alt126=2;
                }
                break;
            case 91:
                {
                alt126=3;
                }
                break;
            case 92:
                {
                alt126=4;
                }
                break;
            case 93:
                {
                alt126=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 126, 0, input);

                throw nvae;
            }

            switch (alt126) {
                case 1 :
                    // InternalCheckCfg.g:7065:3: (enumLiteral_0= 'default' )
                    {
                    // InternalCheckCfg.g:7065:3: (enumLiteral_0= 'default' )
                    // InternalCheckCfg.g:7066:4: enumLiteral_0= 'default'
                    {
                    enumLiteral_0=(Token)match(input,66,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getSeverityKindAccess().getDefaultEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_0, grammarAccess.getSeverityKindAccess().getDefaultEnumLiteralDeclaration_0());
                      			
                    }

                    }


                    }
                    break;
                case 2 :
                    // InternalCheckCfg.g:7073:3: (enumLiteral_1= 'error' )
                    {
                    // InternalCheckCfg.g:7073:3: (enumLiteral_1= 'error' )
                    // InternalCheckCfg.g:7074:4: enumLiteral_1= 'error'
                    {
                    enumLiteral_1=(Token)match(input,90,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_1, grammarAccess.getSeverityKindAccess().getErrorEnumLiteralDeclaration_1());
                      			
                    }

                    }


                    }
                    break;
                case 3 :
                    // InternalCheckCfg.g:7081:3: (enumLiteral_2= 'warning' )
                    {
                    // InternalCheckCfg.g:7081:3: (enumLiteral_2= 'warning' )
                    // InternalCheckCfg.g:7082:4: enumLiteral_2= 'warning'
                    {
                    enumLiteral_2=(Token)match(input,91,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getSeverityKindAccess().getWarningEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_2, grammarAccess.getSeverityKindAccess().getWarningEnumLiteralDeclaration_2());
                      			
                    }

                    }


                    }
                    break;
                case 4 :
                    // InternalCheckCfg.g:7089:3: (enumLiteral_3= 'info' )
                    {
                    // InternalCheckCfg.g:7089:3: (enumLiteral_3= 'info' )
                    // InternalCheckCfg.g:7090:4: enumLiteral_3= 'info'
                    {
                    enumLiteral_3=(Token)match(input,92,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getSeverityKindAccess().getInfoEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_3, grammarAccess.getSeverityKindAccess().getInfoEnumLiteralDeclaration_3());
                      			
                    }

                    }


                    }
                    break;
                case 5 :
                    // InternalCheckCfg.g:7097:3: (enumLiteral_4= 'ignore' )
                    {
                    // InternalCheckCfg.g:7097:3: (enumLiteral_4= 'ignore' )
                    // InternalCheckCfg.g:7098:4: enumLiteral_4= 'ignore'
                    {
                    enumLiteral_4=(Token)match(input,93,FOLLOW_2); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				current = grammarAccess.getSeverityKindAccess().getIgnoreEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                      				newLeafNode(enumLiteral_4, grammarAccess.getSeverityKindAccess().getIgnoreEnumLiteralDeclaration_4());
                      			
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
    // $ANTLR end "ruleSeverityKind"

    // $ANTLR start synpred1_InternalCheckCfg
    public final void synpred1_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:878:6: ( ( () ( ( ruleOpMultiAssign ) ) ) )
        // InternalCheckCfg.g:878:7: ( () ( ( ruleOpMultiAssign ) ) )
        {
        // InternalCheckCfg.g:878:7: ( () ( ( ruleOpMultiAssign ) ) )
        // InternalCheckCfg.g:879:7: () ( ( ruleOpMultiAssign ) )
        {
        // InternalCheckCfg.g:879:7: ()
        // InternalCheckCfg.g:880:7: 
        {
        }

        // InternalCheckCfg.g:881:7: ( ( ruleOpMultiAssign ) )
        // InternalCheckCfg.g:882:8: ( ruleOpMultiAssign )
        {
        // InternalCheckCfg.g:882:8: ( ruleOpMultiAssign )
        // InternalCheckCfg.g:883:9: ruleOpMultiAssign
        {
        pushFollow(FOLLOW_2);
        ruleOpMultiAssign();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred1_InternalCheckCfg

    // $ANTLR start synpred2_InternalCheckCfg
    public final void synpred2_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1072:5: ( ( () ( ( ruleOpOr ) ) ) )
        // InternalCheckCfg.g:1072:6: ( () ( ( ruleOpOr ) ) )
        {
        // InternalCheckCfg.g:1072:6: ( () ( ( ruleOpOr ) ) )
        // InternalCheckCfg.g:1073:6: () ( ( ruleOpOr ) )
        {
        // InternalCheckCfg.g:1073:6: ()
        // InternalCheckCfg.g:1074:6: 
        {
        }

        // InternalCheckCfg.g:1075:6: ( ( ruleOpOr ) )
        // InternalCheckCfg.g:1076:7: ( ruleOpOr )
        {
        // InternalCheckCfg.g:1076:7: ( ruleOpOr )
        // InternalCheckCfg.g:1077:8: ruleOpOr
        {
        pushFollow(FOLLOW_2);
        ruleOpOr();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred2_InternalCheckCfg

    // $ANTLR start synpred3_InternalCheckCfg
    public final void synpred3_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1179:5: ( ( () ( ( ruleOpAnd ) ) ) )
        // InternalCheckCfg.g:1179:6: ( () ( ( ruleOpAnd ) ) )
        {
        // InternalCheckCfg.g:1179:6: ( () ( ( ruleOpAnd ) ) )
        // InternalCheckCfg.g:1180:6: () ( ( ruleOpAnd ) )
        {
        // InternalCheckCfg.g:1180:6: ()
        // InternalCheckCfg.g:1181:6: 
        {
        }

        // InternalCheckCfg.g:1182:6: ( ( ruleOpAnd ) )
        // InternalCheckCfg.g:1183:7: ( ruleOpAnd )
        {
        // InternalCheckCfg.g:1183:7: ( ruleOpAnd )
        // InternalCheckCfg.g:1184:8: ruleOpAnd
        {
        pushFollow(FOLLOW_2);
        ruleOpAnd();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred3_InternalCheckCfg

    // $ANTLR start synpred4_InternalCheckCfg
    public final void synpred4_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1286:5: ( ( () ( ( ruleOpEquality ) ) ) )
        // InternalCheckCfg.g:1286:6: ( () ( ( ruleOpEquality ) ) )
        {
        // InternalCheckCfg.g:1286:6: ( () ( ( ruleOpEquality ) ) )
        // InternalCheckCfg.g:1287:6: () ( ( ruleOpEquality ) )
        {
        // InternalCheckCfg.g:1287:6: ()
        // InternalCheckCfg.g:1288:6: 
        {
        }

        // InternalCheckCfg.g:1289:6: ( ( ruleOpEquality ) )
        // InternalCheckCfg.g:1290:7: ( ruleOpEquality )
        {
        // InternalCheckCfg.g:1290:7: ( ruleOpEquality )
        // InternalCheckCfg.g:1291:8: ruleOpEquality
        {
        pushFollow(FOLLOW_2);
        ruleOpEquality();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred4_InternalCheckCfg

    // $ANTLR start synpred5_InternalCheckCfg
    public final void synpred5_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1414:6: ( ( () 'instanceof' ) )
        // InternalCheckCfg.g:1414:7: ( () 'instanceof' )
        {
        // InternalCheckCfg.g:1414:7: ( () 'instanceof' )
        // InternalCheckCfg.g:1415:7: () 'instanceof'
        {
        // InternalCheckCfg.g:1415:7: ()
        // InternalCheckCfg.g:1416:7: 
        {
        }

        match(input,40,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred5_InternalCheckCfg

    // $ANTLR start synpred6_InternalCheckCfg
    public final void synpred6_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1457:6: ( ( () ( ( ruleOpCompare ) ) ) )
        // InternalCheckCfg.g:1457:7: ( () ( ( ruleOpCompare ) ) )
        {
        // InternalCheckCfg.g:1457:7: ( () ( ( ruleOpCompare ) ) )
        // InternalCheckCfg.g:1458:7: () ( ( ruleOpCompare ) )
        {
        // InternalCheckCfg.g:1458:7: ()
        // InternalCheckCfg.g:1459:7: 
        {
        }

        // InternalCheckCfg.g:1460:7: ( ( ruleOpCompare ) )
        // InternalCheckCfg.g:1461:8: ( ruleOpCompare )
        {
        // InternalCheckCfg.g:1461:8: ( ruleOpCompare )
        // InternalCheckCfg.g:1462:9: ruleOpCompare
        {
        pushFollow(FOLLOW_2);
        ruleOpCompare();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred6_InternalCheckCfg

    // $ANTLR start synpred7_InternalCheckCfg
    public final void synpred7_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1592:5: ( ( () ( ( ruleOpOther ) ) ) )
        // InternalCheckCfg.g:1592:6: ( () ( ( ruleOpOther ) ) )
        {
        // InternalCheckCfg.g:1592:6: ( () ( ( ruleOpOther ) ) )
        // InternalCheckCfg.g:1593:6: () ( ( ruleOpOther ) )
        {
        // InternalCheckCfg.g:1593:6: ()
        // InternalCheckCfg.g:1594:6: 
        {
        }

        // InternalCheckCfg.g:1595:6: ( ( ruleOpOther ) )
        // InternalCheckCfg.g:1596:7: ( ruleOpOther )
        {
        // InternalCheckCfg.g:1596:7: ( ruleOpOther )
        // InternalCheckCfg.g:1597:8: ruleOpOther
        {
        pushFollow(FOLLOW_2);
        ruleOpOther();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred7_InternalCheckCfg

    // $ANTLR start synpred8_InternalCheckCfg
    public final void synpred8_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1712:6: ( ( '>' '>' ) )
        // InternalCheckCfg.g:1712:7: ( '>' '>' )
        {
        // InternalCheckCfg.g:1712:7: ( '>' '>' )
        // InternalCheckCfg.g:1713:7: '>' '>'
        {
        match(input,32,FOLLOW_32); if (state.failed) return ;
        match(input,32,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred8_InternalCheckCfg

    // $ANTLR start synpred9_InternalCheckCfg
    public final void synpred9_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1747:6: ( ( '<' '<' ) )
        // InternalCheckCfg.g:1747:7: ( '<' '<' )
        {
        // InternalCheckCfg.g:1747:7: ( '<' '<' )
        // InternalCheckCfg.g:1748:7: '<' '<'
        {
        match(input,31,FOLLOW_22); if (state.failed) return ;
        match(input,31,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred9_InternalCheckCfg

    // $ANTLR start synpred10_InternalCheckCfg
    public final void synpred10_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1820:5: ( ( () ( ( ruleOpAdd ) ) ) )
        // InternalCheckCfg.g:1820:6: ( () ( ( ruleOpAdd ) ) )
        {
        // InternalCheckCfg.g:1820:6: ( () ( ( ruleOpAdd ) ) )
        // InternalCheckCfg.g:1821:6: () ( ( ruleOpAdd ) )
        {
        // InternalCheckCfg.g:1821:6: ()
        // InternalCheckCfg.g:1822:6: 
        {
        }

        // InternalCheckCfg.g:1823:6: ( ( ruleOpAdd ) )
        // InternalCheckCfg.g:1824:7: ( ruleOpAdd )
        {
        // InternalCheckCfg.g:1824:7: ( ruleOpAdd )
        // InternalCheckCfg.g:1825:8: ruleOpAdd
        {
        pushFollow(FOLLOW_2);
        ruleOpAdd();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred10_InternalCheckCfg

    // $ANTLR start synpred11_InternalCheckCfg
    public final void synpred11_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:1935:5: ( ( () ( ( ruleOpMulti ) ) ) )
        // InternalCheckCfg.g:1935:6: ( () ( ( ruleOpMulti ) ) )
        {
        // InternalCheckCfg.g:1935:6: ( () ( ( ruleOpMulti ) ) )
        // InternalCheckCfg.g:1936:6: () ( ( ruleOpMulti ) )
        {
        // InternalCheckCfg.g:1936:6: ()
        // InternalCheckCfg.g:1937:6: 
        {
        }

        // InternalCheckCfg.g:1938:6: ( ( ruleOpMulti ) )
        // InternalCheckCfg.g:1939:7: ( ruleOpMulti )
        {
        // InternalCheckCfg.g:1939:7: ( ruleOpMulti )
        // InternalCheckCfg.g:1940:8: ruleOpMulti
        {
        pushFollow(FOLLOW_2);
        ruleOpMulti();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred11_InternalCheckCfg

    // $ANTLR start synpred12_InternalCheckCfg
    public final void synpred12_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2170:5: ( ( () 'as' ) )
        // InternalCheckCfg.g:2170:6: ( () 'as' )
        {
        // InternalCheckCfg.g:2170:6: ( () 'as' )
        // InternalCheckCfg.g:2171:6: () 'as'
        {
        // InternalCheckCfg.g:2171:6: ()
        // InternalCheckCfg.g:2172:6: 
        {
        }

        match(input,54,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred12_InternalCheckCfg

    // $ANTLR start synpred13_InternalCheckCfg
    public final void synpred13_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2238:4: ( ( () ( ( ruleOpPostfix ) ) ) )
        // InternalCheckCfg.g:2238:5: ( () ( ( ruleOpPostfix ) ) )
        {
        // InternalCheckCfg.g:2238:5: ( () ( ( ruleOpPostfix ) ) )
        // InternalCheckCfg.g:2239:5: () ( ( ruleOpPostfix ) )
        {
        // InternalCheckCfg.g:2239:5: ()
        // InternalCheckCfg.g:2240:5: 
        {
        }

        // InternalCheckCfg.g:2241:5: ( ( ruleOpPostfix ) )
        // InternalCheckCfg.g:2242:6: ( ruleOpPostfix )
        {
        // InternalCheckCfg.g:2242:6: ( ruleOpPostfix )
        // InternalCheckCfg.g:2243:7: ruleOpPostfix
        {
        pushFollow(FOLLOW_2);
        ruleOpPostfix();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred13_InternalCheckCfg

    // $ANTLR start synpred14_InternalCheckCfg
    public final void synpred14_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2334:6: ( ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign ) )
        // InternalCheckCfg.g:2334:7: ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign )
        {
        // InternalCheckCfg.g:2334:7: ( () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign )
        // InternalCheckCfg.g:2335:7: () ( '.' | ( ( '::' ) ) ) ( ( ruleFeatureCallID ) ) ruleOpSingleAssign
        {
        // InternalCheckCfg.g:2335:7: ()
        // InternalCheckCfg.g:2336:7: 
        {
        }

        // InternalCheckCfg.g:2337:7: ( '.' | ( ( '::' ) ) )
        int alt127=2;
        int LA127_0 = input.LA(1);

        if ( (LA127_0==57) ) {
            alt127=1;
        }
        else if ( (LA127_0==58) ) {
            alt127=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 127, 0, input);

            throw nvae;
        }
        switch (alt127) {
            case 1 :
                // InternalCheckCfg.g:2338:8: '.'
                {
                match(input,57,FOLLOW_39); if (state.failed) return ;

                }
                break;
            case 2 :
                // InternalCheckCfg.g:2340:8: ( ( '::' ) )
                {
                // InternalCheckCfg.g:2340:8: ( ( '::' ) )
                // InternalCheckCfg.g:2341:9: ( '::' )
                {
                // InternalCheckCfg.g:2341:9: ( '::' )
                // InternalCheckCfg.g:2342:10: '::'
                {
                match(input,58,FOLLOW_39); if (state.failed) return ;

                }


                }


                }
                break;

        }

        // InternalCheckCfg.g:2346:7: ( ( ruleFeatureCallID ) )
        // InternalCheckCfg.g:2347:8: ( ruleFeatureCallID )
        {
        // InternalCheckCfg.g:2347:8: ( ruleFeatureCallID )
        // InternalCheckCfg.g:2348:9: ruleFeatureCallID
        {
        pushFollow(FOLLOW_14);
        ruleFeatureCallID();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        pushFollow(FOLLOW_2);
        ruleOpSingleAssign();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred14_InternalCheckCfg

    // $ANTLR start synpred15_InternalCheckCfg
    public final void synpred15_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2431:6: ( ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) ) )
        // InternalCheckCfg.g:2431:7: ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) )
        {
        // InternalCheckCfg.g:2431:7: ( () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) ) )
        // InternalCheckCfg.g:2432:7: () ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) )
        {
        // InternalCheckCfg.g:2432:7: ()
        // InternalCheckCfg.g:2433:7: 
        {
        }

        // InternalCheckCfg.g:2434:7: ( '.' | ( ( '?.' ) ) | ( ( '::' ) ) )
        int alt128=3;
        switch ( input.LA(1) ) {
        case 57:
            {
            alt128=1;
            }
            break;
        case 59:
            {
            alt128=2;
            }
            break;
        case 58:
            {
            alt128=3;
            }
            break;
        default:
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 128, 0, input);

            throw nvae;
        }

        switch (alt128) {
            case 1 :
                // InternalCheckCfg.g:2435:8: '.'
                {
                match(input,57,FOLLOW_2); if (state.failed) return ;

                }
                break;
            case 2 :
                // InternalCheckCfg.g:2437:8: ( ( '?.' ) )
                {
                // InternalCheckCfg.g:2437:8: ( ( '?.' ) )
                // InternalCheckCfg.g:2438:9: ( '?.' )
                {
                // InternalCheckCfg.g:2438:9: ( '?.' )
                // InternalCheckCfg.g:2439:10: '?.'
                {
                match(input,59,FOLLOW_2); if (state.failed) return ;

                }


                }


                }
                break;
            case 3 :
                // InternalCheckCfg.g:2443:8: ( ( '::' ) )
                {
                // InternalCheckCfg.g:2443:8: ( ( '::' ) )
                // InternalCheckCfg.g:2444:9: ( '::' )
                {
                // InternalCheckCfg.g:2444:9: ( '::' )
                // InternalCheckCfg.g:2445:10: '::'
                {
                match(input,58,FOLLOW_2); if (state.failed) return ;

                }


                }


                }
                break;

        }


        }


        }
    }
    // $ANTLR end synpred15_InternalCheckCfg

    // $ANTLR start synpred16_InternalCheckCfg
    public final void synpred16_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2569:7: ( ( '(' ) )
        // InternalCheckCfg.g:2569:8: ( '(' )
        {
        // InternalCheckCfg.g:2569:8: ( '(' )
        // InternalCheckCfg.g:2570:8: '('
        {
        match(input,19,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred16_InternalCheckCfg

    // $ANTLR start synpred17_InternalCheckCfg
    public final void synpred17_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2588:8: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // InternalCheckCfg.g:2588:9: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // InternalCheckCfg.g:2588:9: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // InternalCheckCfg.g:2589:9: () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // InternalCheckCfg.g:2589:9: ()
        // InternalCheckCfg.g:2590:9: 
        {
        }

        // InternalCheckCfg.g:2591:9: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt130=2;
        int LA130_0 = input.LA(1);

        if ( (LA130_0==RULE_ID||LA130_0==19||LA130_0==44) ) {
            alt130=1;
        }
        switch (alt130) {
            case 1 :
                // InternalCheckCfg.g:2592:10: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // InternalCheckCfg.g:2592:10: ( ( ruleJvmFormalParameter ) )
                // InternalCheckCfg.g:2593:11: ( ruleJvmFormalParameter )
                {
                // InternalCheckCfg.g:2593:11: ( ruleJvmFormalParameter )
                // InternalCheckCfg.g:2594:12: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_51);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalCheckCfg.g:2597:10: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop129:
                do {
                    int alt129=2;
                    int LA129_0 = input.LA(1);

                    if ( (LA129_0==20) ) {
                        alt129=1;
                    }


                    switch (alt129) {
                	case 1 :
                	    // InternalCheckCfg.g:2598:11: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,20,FOLLOW_29); if (state.failed) return ;
                	    // InternalCheckCfg.g:2599:11: ( ( ruleJvmFormalParameter ) )
                	    // InternalCheckCfg.g:2600:12: ( ruleJvmFormalParameter )
                	    {
                	    // InternalCheckCfg.g:2600:12: ( ruleJvmFormalParameter )
                	    // InternalCheckCfg.g:2601:13: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_51);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop129;
                    }
                } while (true);


                }
                break;

        }

        // InternalCheckCfg.g:2606:9: ( ( '|' ) )
        // InternalCheckCfg.g:2607:10: ( '|' )
        {
        // InternalCheckCfg.g:2607:10: ( '|' )
        // InternalCheckCfg.g:2608:11: '|'
        {
        match(input,60,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred17_InternalCheckCfg

    // $ANTLR start synpred18_InternalCheckCfg
    public final void synpred18_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2685:6: ( ( () '[' ) )
        // InternalCheckCfg.g:2685:7: ( () '[' )
        {
        // InternalCheckCfg.g:2685:7: ( () '[' )
        // InternalCheckCfg.g:2686:7: () '['
        {
        // InternalCheckCfg.g:2686:7: ()
        // InternalCheckCfg.g:2687:7: 
        {
        }

        match(input,24,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred18_InternalCheckCfg

    // $ANTLR start synpred19_InternalCheckCfg
    public final void synpred19_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2758:4: ( ( () 'synchronized' '(' ) )
        // InternalCheckCfg.g:2758:5: ( () 'synchronized' '(' )
        {
        // InternalCheckCfg.g:2758:5: ( () 'synchronized' '(' )
        // InternalCheckCfg.g:2759:5: () 'synchronized' '('
        {
        // InternalCheckCfg.g:2759:5: ()
        // InternalCheckCfg.g:2760:5: 
        {
        }

        match(input,86,FOLLOW_56); if (state.failed) return ;
        match(input,19,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred19_InternalCheckCfg

    // $ANTLR start synpred20_InternalCheckCfg
    public final void synpred20_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2803:4: ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )
        // InternalCheckCfg.g:2803:5: ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' )
        {
        // InternalCheckCfg.g:2803:5: ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' )
        // InternalCheckCfg.g:2804:5: () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':'
        {
        // InternalCheckCfg.g:2804:5: ()
        // InternalCheckCfg.g:2805:5: 
        {
        }

        match(input,17,FOLLOW_56); if (state.failed) return ;
        match(input,19,FOLLOW_29); if (state.failed) return ;
        // InternalCheckCfg.g:2808:5: ( ( ruleJvmFormalParameter ) )
        // InternalCheckCfg.g:2809:6: ( ruleJvmFormalParameter )
        {
        // InternalCheckCfg.g:2809:6: ( ruleJvmFormalParameter )
        // InternalCheckCfg.g:2810:7: ruleJvmFormalParameter
        {
        pushFollow(FOLLOW_59);
        ruleJvmFormalParameter();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,65,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred20_InternalCheckCfg

    // $ANTLR start synpred21_InternalCheckCfg
    public final void synpred21_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:2917:4: ( ( () '[' ) )
        // InternalCheckCfg.g:2917:5: ( () '[' )
        {
        // InternalCheckCfg.g:2917:5: ( () '[' )
        // InternalCheckCfg.g:2918:5: () '['
        {
        // InternalCheckCfg.g:2918:5: ()
        // InternalCheckCfg.g:2919:5: 
        {
        }

        match(input,24,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred21_InternalCheckCfg

    // $ANTLR start synpred23_InternalCheckCfg
    public final void synpred23_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:3222:4: ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // InternalCheckCfg.g:3222:5: ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // InternalCheckCfg.g:3222:5: ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // InternalCheckCfg.g:3223:5: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // InternalCheckCfg.g:3223:5: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt132=2;
        int LA132_0 = input.LA(1);

        if ( (LA132_0==RULE_ID||LA132_0==19||LA132_0==44) ) {
            alt132=1;
        }
        switch (alt132) {
            case 1 :
                // InternalCheckCfg.g:3224:6: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // InternalCheckCfg.g:3224:6: ( ( ruleJvmFormalParameter ) )
                // InternalCheckCfg.g:3225:7: ( ruleJvmFormalParameter )
                {
                // InternalCheckCfg.g:3225:7: ( ruleJvmFormalParameter )
                // InternalCheckCfg.g:3226:8: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_51);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalCheckCfg.g:3229:6: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop131:
                do {
                    int alt131=2;
                    int LA131_0 = input.LA(1);

                    if ( (LA131_0==20) ) {
                        alt131=1;
                    }


                    switch (alt131) {
                	case 1 :
                	    // InternalCheckCfg.g:3230:7: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,20,FOLLOW_29); if (state.failed) return ;
                	    // InternalCheckCfg.g:3231:7: ( ( ruleJvmFormalParameter ) )
                	    // InternalCheckCfg.g:3232:8: ( ruleJvmFormalParameter )
                	    {
                	    // InternalCheckCfg.g:3232:8: ( ruleJvmFormalParameter )
                	    // InternalCheckCfg.g:3233:9: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_51);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop131;
                    }
                } while (true);


                }
                break;

        }

        // InternalCheckCfg.g:3238:5: ( ( '|' ) )
        // InternalCheckCfg.g:3239:6: ( '|' )
        {
        // InternalCheckCfg.g:3239:6: ( '|' )
        // InternalCheckCfg.g:3240:7: '|'
        {
        match(input,60,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred23_InternalCheckCfg

    // $ANTLR start synpred25_InternalCheckCfg
    public final void synpred25_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:3631:5: ( 'else' )
        // InternalCheckCfg.g:3631:6: 'else'
        {
        match(input,63,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred25_InternalCheckCfg

    // $ANTLR start synpred26_InternalCheckCfg
    public final void synpred26_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:3690:6: ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )
        // InternalCheckCfg.g:3690:7: ( '(' ( ( ruleJvmFormalParameter ) ) ':' )
        {
        // InternalCheckCfg.g:3690:7: ( '(' ( ( ruleJvmFormalParameter ) ) ':' )
        // InternalCheckCfg.g:3691:7: '(' ( ( ruleJvmFormalParameter ) ) ':'
        {
        match(input,19,FOLLOW_29); if (state.failed) return ;
        // InternalCheckCfg.g:3692:7: ( ( ruleJvmFormalParameter ) )
        // InternalCheckCfg.g:3693:8: ( ruleJvmFormalParameter )
        {
        // InternalCheckCfg.g:3693:8: ( ruleJvmFormalParameter )
        // InternalCheckCfg.g:3694:9: ruleJvmFormalParameter
        {
        pushFollow(FOLLOW_59);
        ruleJvmFormalParameter();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,65,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred26_InternalCheckCfg

    // $ANTLR start synpred27_InternalCheckCfg
    public final void synpred27_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:3757:6: ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )
        // InternalCheckCfg.g:3757:7: ( ( ( ruleJvmFormalParameter ) ) ':' )
        {
        // InternalCheckCfg.g:3757:7: ( ( ( ruleJvmFormalParameter ) ) ':' )
        // InternalCheckCfg.g:3758:7: ( ( ruleJvmFormalParameter ) ) ':'
        {
        // InternalCheckCfg.g:3758:7: ( ( ruleJvmFormalParameter ) )
        // InternalCheckCfg.g:3759:8: ( ruleJvmFormalParameter )
        {
        // InternalCheckCfg.g:3759:8: ( ruleJvmFormalParameter )
        // InternalCheckCfg.g:3760:9: ruleJvmFormalParameter
        {
        pushFollow(FOLLOW_59);
        ruleJvmFormalParameter();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        match(input,65,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred27_InternalCheckCfg

    // $ANTLR start synpred29_InternalCheckCfg
    public final void synpred29_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:4575:5: ( ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) ) )
        // InternalCheckCfg.g:4575:6: ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) )
        {
        // InternalCheckCfg.g:4575:6: ( ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) ) )
        // InternalCheckCfg.g:4576:6: ( ( ruleJvmTypeReference ) ) ( ( ruleValidID ) )
        {
        // InternalCheckCfg.g:4576:6: ( ( ruleJvmTypeReference ) )
        // InternalCheckCfg.g:4577:7: ( ruleJvmTypeReference )
        {
        // InternalCheckCfg.g:4577:7: ( ruleJvmTypeReference )
        // InternalCheckCfg.g:4578:8: ruleJvmTypeReference
        {
        pushFollow(FOLLOW_4);
        ruleJvmTypeReference();

        state._fsp--;
        if (state.failed) return ;

        }


        }

        // InternalCheckCfg.g:4581:6: ( ( ruleValidID ) )
        // InternalCheckCfg.g:4582:7: ( ruleValidID )
        {
        // InternalCheckCfg.g:4582:7: ( ruleValidID )
        // InternalCheckCfg.g:4583:8: ruleValidID
        {
        pushFollow(FOLLOW_2);
        ruleValidID();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred29_InternalCheckCfg

    // $ANTLR start synpred30_InternalCheckCfg
    public final void synpred30_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:4887:5: ( ( '(' ) )
        // InternalCheckCfg.g:4887:6: ( '(' )
        {
        // InternalCheckCfg.g:4887:6: ( '(' )
        // InternalCheckCfg.g:4888:6: '('
        {
        match(input,19,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred30_InternalCheckCfg

    // $ANTLR start synpred31_InternalCheckCfg
    public final void synpred31_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:4906:6: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // InternalCheckCfg.g:4906:7: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // InternalCheckCfg.g:4906:7: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // InternalCheckCfg.g:4907:7: () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // InternalCheckCfg.g:4907:7: ()
        // InternalCheckCfg.g:4908:7: 
        {
        }

        // InternalCheckCfg.g:4909:7: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt136=2;
        int LA136_0 = input.LA(1);

        if ( (LA136_0==RULE_ID||LA136_0==19||LA136_0==44) ) {
            alt136=1;
        }
        switch (alt136) {
            case 1 :
                // InternalCheckCfg.g:4910:8: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // InternalCheckCfg.g:4910:8: ( ( ruleJvmFormalParameter ) )
                // InternalCheckCfg.g:4911:9: ( ruleJvmFormalParameter )
                {
                // InternalCheckCfg.g:4911:9: ( ruleJvmFormalParameter )
                // InternalCheckCfg.g:4912:10: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_51);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalCheckCfg.g:4915:8: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop135:
                do {
                    int alt135=2;
                    int LA135_0 = input.LA(1);

                    if ( (LA135_0==20) ) {
                        alt135=1;
                    }


                    switch (alt135) {
                	case 1 :
                	    // InternalCheckCfg.g:4916:9: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,20,FOLLOW_29); if (state.failed) return ;
                	    // InternalCheckCfg.g:4917:9: ( ( ruleJvmFormalParameter ) )
                	    // InternalCheckCfg.g:4918:10: ( ruleJvmFormalParameter )
                	    {
                	    // InternalCheckCfg.g:4918:10: ( ruleJvmFormalParameter )
                	    // InternalCheckCfg.g:4919:11: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_51);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop135;
                    }
                } while (true);


                }
                break;

        }

        // InternalCheckCfg.g:4924:7: ( ( '|' ) )
        // InternalCheckCfg.g:4925:8: ( '|' )
        {
        // InternalCheckCfg.g:4925:8: ( '|' )
        // InternalCheckCfg.g:4926:9: '|'
        {
        match(input,60,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred31_InternalCheckCfg

    // $ANTLR start synpred32_InternalCheckCfg
    public final void synpred32_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5003:4: ( ( () '[' ) )
        // InternalCheckCfg.g:5003:5: ( () '[' )
        {
        // InternalCheckCfg.g:5003:5: ( () '[' )
        // InternalCheckCfg.g:5004:5: () '['
        {
        // InternalCheckCfg.g:5004:5: ()
        // InternalCheckCfg.g:5005:5: 
        {
        }

        match(input,24,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred32_InternalCheckCfg

    // $ANTLR start synpred33_InternalCheckCfg
    public final void synpred33_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5163:5: ( '<' )
        // InternalCheckCfg.g:5163:6: '<'
        {
        match(input,31,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred33_InternalCheckCfg

    // $ANTLR start synpred34_InternalCheckCfg
    public final void synpred34_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5220:5: ( ( '(' ) )
        // InternalCheckCfg.g:5220:6: ( '(' )
        {
        // InternalCheckCfg.g:5220:6: ( '(' )
        // InternalCheckCfg.g:5221:6: '('
        {
        match(input,19,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred34_InternalCheckCfg

    // $ANTLR start synpred35_InternalCheckCfg
    public final void synpred35_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5239:6: ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )
        // InternalCheckCfg.g:5239:7: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        {
        // InternalCheckCfg.g:5239:7: ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) )
        // InternalCheckCfg.g:5240:7: () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) )
        {
        // InternalCheckCfg.g:5240:7: ()
        // InternalCheckCfg.g:5241:7: 
        {
        }

        // InternalCheckCfg.g:5242:7: ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )?
        int alt138=2;
        int LA138_0 = input.LA(1);

        if ( (LA138_0==RULE_ID||LA138_0==19||LA138_0==44) ) {
            alt138=1;
        }
        switch (alt138) {
            case 1 :
                // InternalCheckCfg.g:5243:8: ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )*
                {
                // InternalCheckCfg.g:5243:8: ( ( ruleJvmFormalParameter ) )
                // InternalCheckCfg.g:5244:9: ( ruleJvmFormalParameter )
                {
                // InternalCheckCfg.g:5244:9: ( ruleJvmFormalParameter )
                // InternalCheckCfg.g:5245:10: ruleJvmFormalParameter
                {
                pushFollow(FOLLOW_51);
                ruleJvmFormalParameter();

                state._fsp--;
                if (state.failed) return ;

                }


                }

                // InternalCheckCfg.g:5248:8: ( ',' ( ( ruleJvmFormalParameter ) ) )*
                loop137:
                do {
                    int alt137=2;
                    int LA137_0 = input.LA(1);

                    if ( (LA137_0==20) ) {
                        alt137=1;
                    }


                    switch (alt137) {
                	case 1 :
                	    // InternalCheckCfg.g:5249:9: ',' ( ( ruleJvmFormalParameter ) )
                	    {
                	    match(input,20,FOLLOW_29); if (state.failed) return ;
                	    // InternalCheckCfg.g:5250:9: ( ( ruleJvmFormalParameter ) )
                	    // InternalCheckCfg.g:5251:10: ( ruleJvmFormalParameter )
                	    {
                	    // InternalCheckCfg.g:5251:10: ( ruleJvmFormalParameter )
                	    // InternalCheckCfg.g:5252:11: ruleJvmFormalParameter
                	    {
                	    pushFollow(FOLLOW_51);
                	    ruleJvmFormalParameter();

                	    state._fsp--;
                	    if (state.failed) return ;

                	    }


                	    }


                	    }
                	    break;

                	default :
                	    break loop137;
                    }
                } while (true);


                }
                break;

        }

        // InternalCheckCfg.g:5257:7: ( ( '|' ) )
        // InternalCheckCfg.g:5258:8: ( '|' )
        {
        // InternalCheckCfg.g:5258:8: ( '|' )
        // InternalCheckCfg.g:5259:9: '|'
        {
        match(input,60,FOLLOW_2); if (state.failed) return ;

        }


        }


        }


        }
    }
    // $ANTLR end synpred35_InternalCheckCfg

    // $ANTLR start synpred36_InternalCheckCfg
    public final void synpred36_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5336:4: ( ( () '[' ) )
        // InternalCheckCfg.g:5336:5: ( () '[' )
        {
        // InternalCheckCfg.g:5336:5: ( () '[' )
        // InternalCheckCfg.g:5337:5: () '['
        {
        // InternalCheckCfg.g:5337:5: ()
        // InternalCheckCfg.g:5338:5: 
        {
        }

        match(input,24,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred36_InternalCheckCfg

    // $ANTLR start synpred37_InternalCheckCfg
    public final void synpred37_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5679:4: ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )
        // InternalCheckCfg.g:
        {
        if ( (input.LA(1)>=RULE_STRING && input.LA(1)<=RULE_ID)||input.LA(1)==15||input.LA(1)==17||input.LA(1)==19||(input.LA(1)>=23 && input.LA(1)<=24)||input.LA(1)==31||(input.LA(1)>=47 && input.LA(1)<=48)||input.LA(1)==53||input.LA(1)==62||input.LA(1)==64||(input.LA(1)>=68 && input.LA(1)<=69)||(input.LA(1)>=72 && input.LA(1)<=84)||input.LA(1)==86 ) {
            input.consume();
            state.errorRecovery=false;state.failed=false;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            MismatchedSetException mse = new MismatchedSetException(null,input);
            throw mse;
        }


        }
    }
    // $ANTLR end synpred37_InternalCheckCfg

    // $ANTLR start synpred38_InternalCheckCfg
    public final void synpred38_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5750:6: ( 'catch' )
        // InternalCheckCfg.g:5750:7: 'catch'
        {
        match(input,87,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred38_InternalCheckCfg

    // $ANTLR start synpred39_InternalCheckCfg
    public final void synpred39_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:5771:7: ( 'finally' )
        // InternalCheckCfg.g:5771:8: 'finally'
        {
        match(input,85,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred39_InternalCheckCfg

    // $ANTLR start synpred42_InternalCheckCfg
    public final void synpred42_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:6015:5: ( '.' )
        // InternalCheckCfg.g:6015:6: '.'
        {
        match(input,57,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred42_InternalCheckCfg

    // $ANTLR start synpred43_InternalCheckCfg
    public final void synpred43_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:6141:5: ( ( () ruleArrayBrackets ) )
        // InternalCheckCfg.g:6141:6: ( () ruleArrayBrackets )
        {
        // InternalCheckCfg.g:6141:6: ( () ruleArrayBrackets )
        // InternalCheckCfg.g:6142:6: () ruleArrayBrackets
        {
        // InternalCheckCfg.g:6142:6: ()
        // InternalCheckCfg.g:6143:6: 
        {
        }

        pushFollow(FOLLOW_2);
        ruleArrayBrackets();

        state._fsp--;
        if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred43_InternalCheckCfg

    // $ANTLR start synpred44_InternalCheckCfg
    public final void synpred44_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:6338:5: ( '<' )
        // InternalCheckCfg.g:6338:6: '<'
        {
        match(input,31,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_InternalCheckCfg

    // $ANTLR start synpred45_InternalCheckCfg
    public final void synpred45_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:6394:6: ( ( () '.' ) )
        // InternalCheckCfg.g:6394:7: ( () '.' )
        {
        // InternalCheckCfg.g:6394:7: ( () '.' )
        // InternalCheckCfg.g:6395:7: () '.'
        {
        // InternalCheckCfg.g:6395:7: ()
        // InternalCheckCfg.g:6396:7: 
        {
        }

        match(input,57,FOLLOW_2); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred45_InternalCheckCfg

    // $ANTLR start synpred46_InternalCheckCfg
    public final void synpred46_InternalCheckCfg_fragment() throws RecognitionException {   
        // InternalCheckCfg.g:6432:7: ( '<' )
        // InternalCheckCfg.g:6432:8: '<'
        {
        match(input,31,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred46_InternalCheckCfg

    // Delegated rules

    public final boolean synpred31_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred23_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred23_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred36_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred36_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred4_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred15_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred15_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred19_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred19_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred45_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred45_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred43_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred43_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred18_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred18_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred32_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred32_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred8_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred8_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred27_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred27_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred14_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred14_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred44_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred44_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred12_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred12_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred25_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred25_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred38_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred38_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred2_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred39_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred39_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred26_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred26_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred13_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred13_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred17_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred17_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred21_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred21_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred37_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred46_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred46_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred33_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred33_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred42_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred42_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred29_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred29_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred16_InternalCheckCfg() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_InternalCheckCfg_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA16 dfa16 = new DFA16(this);
    protected DFA26 dfa26 = new DFA26(this);
    protected DFA29 dfa29 = new DFA29(this);
    protected DFA45 dfa45 = new DFA45(this);
    protected DFA44 dfa44 = new DFA44(this);
    protected DFA46 dfa46 = new DFA46(this);
    protected DFA48 dfa48 = new DFA48(this);
    protected DFA57 dfa57 = new DFA57(this);
    protected DFA64 dfa64 = new DFA64(this);
    protected DFA63 dfa63 = new DFA63(this);
    protected DFA86 dfa86 = new DFA86(this);
    protected DFA85 dfa85 = new DFA85(this);
    protected DFA87 dfa87 = new DFA87(this);
    protected DFA91 dfa91 = new DFA91(this);
    protected DFA94 dfa94 = new DFA94(this);
    protected DFA93 dfa93 = new DFA93(this);
    protected DFA95 dfa95 = new DFA95(this);
    protected DFA98 dfa98 = new DFA98(this);
    protected DFA116 dfa116 = new DFA116(this);
    protected DFA114 dfa114 = new DFA114(this);
    protected DFA123 dfa123 = new DFA123(this);
    static final String dfa_1s = "\12\uffff";
    static final String dfa_2s = "\1\10\11\uffff";
    static final String dfa_3s = "\1\4\7\0\2\uffff";
    static final String dfa_4s = "\1\127\7\0\2\uffff";
    static final String dfa_5s = "\10\uffff\1\2\1\1";
    static final String dfa_6s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\0\2\uffff}>";
    static final String[] dfa_7s = {
            "\5\10\6\uffff\3\10\1\uffff\3\10\1\uffff\3\10\1\1\1\2\1\3\1\4\1\5\1\6\1\7\33\10\1\uffff\33\10",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "876:4: ( ( ( ( () ( ( ruleOpMultiAssign ) ) ) )=> ( () ( ( ruleOpMultiAssign ) ) ) ) ( (lv_rightOperand_7_0= ruleXAssignment ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_7 = input.LA(1);

                         
                        int index16_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalCheckCfg()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_7);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA16_1 = input.LA(1);

                         
                        int index16_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalCheckCfg()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA16_2 = input.LA(1);

                         
                        int index16_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalCheckCfg()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA16_3 = input.LA(1);

                         
                        int index16_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalCheckCfg()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA16_4 = input.LA(1);

                         
                        int index16_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalCheckCfg()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_4);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA16_5 = input.LA(1);

                         
                        int index16_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalCheckCfg()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_5);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA16_6 = input.LA(1);

                         
                        int index16_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred1_InternalCheckCfg()) ) {s = 9;}

                        else if ( (true) ) {s = 8;}

                         
                        input.seek(index16_6);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_8s = "\13\uffff";
    static final String dfa_9s = "\1\1\12\uffff";
    static final String dfa_10s = "\1\4\1\uffff\10\0\1\uffff";
    static final String dfa_11s = "\1\127\1\uffff\10\0\1\uffff";
    static final String dfa_12s = "\1\uffff\1\2\10\uffff\1\1";
    static final String dfa_13s = "\2\uffff\1\2\1\3\1\5\1\6\1\7\1\4\1\0\1\1\1\uffff}>";
    static final String[] dfa_14s = {
            "\5\1\6\uffff\3\1\1\uffff\3\1\1\uffff\10\1\1\2\1\3\10\1\1\4\1\5\1\6\1\7\1\10\1\11\15\1\1\uffff\33\1",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            ""
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final char[] dfa_11 = DFA.unpackEncodedStringToUnsignedChars(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[][] dfa_14 = unpackEncodedStringArray(dfa_14s);

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = dfa_8;
            this.eof = dfa_9;
            this.min = dfa_10;
            this.max = dfa_11;
            this.accept = dfa_12;
            this.special = dfa_13;
            this.transition = dfa_14;
        }
        public String getDescription() {
            return "()* loopback of 1590:3: ( ( ( ( () ( ( ruleOpOther ) ) ) )=> ( () ( ( ruleOpOther ) ) ) ) ( (lv_rightOperand_3_0= ruleXAdditiveExpression ) ) )*";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA26_8 = input.LA(1);

                         
                        int index26_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_8);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA26_9 = input.LA(1);

                         
                        int index26_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_9);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA26_2 = input.LA(1);

                         
                        int index26_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_2);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA26_3 = input.LA(1);

                         
                        int index26_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_3);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA26_7 = input.LA(1);

                         
                        int index26_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_7);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA26_4 = input.LA(1);

                         
                        int index26_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_4);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA26_5 = input.LA(1);

                         
                        int index26_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_5);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA26_6 = input.LA(1);

                         
                        int index26_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalCheckCfg()) ) {s = 10;}

                        else if ( (true) ) {s = 1;}

                         
                        input.seek(index26_6);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 26, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_15s = "\1\37\2\uffff\1\40\7\uffff";
    static final String dfa_16s = "\1\56\2\uffff\1\53\7\uffff";
    static final String dfa_17s = "\1\uffff\1\1\1\2\1\uffff\1\4\1\5\1\7\1\10\1\11\1\3\1\6";
    static final String dfa_18s = "\13\uffff}>";
    static final String[] dfa_19s = {
            "\1\6\1\3\10\uffff\1\1\1\2\1\4\1\5\1\7\1\10",
            "",
            "",
            "\1\12\12\uffff\1\11",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[][] dfa_19 = unpackEncodedStringArray(dfa_19s);

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = dfa_8;
            this.eof = dfa_8;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "1666:2: (kw= '->' | kw= '..<' | (kw= '>' kw= '..' ) | kw= '..' | kw= '=>' | (kw= '>' ( ( ( ( '>' '>' ) )=> (kw= '>' kw= '>' ) ) | kw= '>' ) ) | (kw= '<' ( ( ( ( '<' '<' ) )=> (kw= '<' kw= '<' ) ) | kw= '<' | kw= '=>' ) ) | kw= '<>' | kw= '?:' )";
        }
    }
    static final String dfa_20s = "\116\uffff";
    static final String dfa_21s = "\1\2\115\uffff";
    static final String dfa_22s = "\1\4\1\0\114\uffff";
    static final String dfa_23s = "\1\127\1\0\114\uffff";
    static final String dfa_24s = "\2\uffff\1\2\112\uffff\1\1";
    static final String dfa_25s = "\1\uffff\1\0\114\uffff}>";
    static final String[] dfa_26s = {
            "\5\2\6\uffff\3\2\1\uffff\1\1\2\2\1\uffff\45\2\1\uffff\33\2",
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

    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final short[] dfa_21 = DFA.unpackEncodedString(dfa_21s);
    static final char[] dfa_22 = DFA.unpackEncodedStringToUnsignedChars(dfa_22s);
    static final char[] dfa_23 = DFA.unpackEncodedStringToUnsignedChars(dfa_23s);
    static final short[] dfa_24 = DFA.unpackEncodedString(dfa_24s);
    static final short[] dfa_25 = DFA.unpackEncodedString(dfa_25s);
    static final short[][] dfa_26 = unpackEncodedStringArray(dfa_26s);

    class DFA45 extends DFA {

        public DFA45(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 45;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_26;
        }
        public String getDescription() {
            return "2567:5: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_17_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )? otherlv_22= ')' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA45_1 = input.LA(1);

                         
                        int index45_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred16_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index45_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 45, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_27s = "\44\uffff";
    static final String dfa_28s = "\1\4\2\0\41\uffff";
    static final String dfa_29s = "\1\126\2\0\41\uffff";
    static final String dfa_30s = "\3\uffff\2\1\1\2\35\uffff\1\3";
    static final String dfa_31s = "\1\0\1\1\1\2\41\uffff}>";
    static final String[] dfa_32s = {
            "\4\5\1\1\6\uffff\1\5\1\uffff\1\5\1\uffff\1\2\1\uffff\1\43\1\uffff\2\5\6\uffff\1\5\14\uffff\1\3\2\uffff\2\5\4\uffff\1\5\6\uffff\1\4\1\uffff\1\5\1\uffff\1\5\3\uffff\2\5\2\uffff\15\5\1\uffff\1\5",
            "\1\uffff",
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
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_27 = DFA.unpackEncodedString(dfa_27s);
    static final char[] dfa_28 = DFA.unpackEncodedStringToUnsignedChars(dfa_28s);
    static final char[] dfa_29 = DFA.unpackEncodedStringToUnsignedChars(dfa_29s);
    static final short[] dfa_30 = DFA.unpackEncodedString(dfa_30s);
    static final short[] dfa_31 = DFA.unpackEncodedString(dfa_31s);
    static final short[][] dfa_32 = unpackEncodedStringArray(dfa_32s);

    class DFA44 extends DFA {

        public DFA44(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 44;
            this.eot = dfa_27;
            this.eof = dfa_27;
            this.min = dfa_28;
            this.max = dfa_29;
            this.accept = dfa_30;
            this.special = dfa_31;
            this.transition = dfa_32;
        }
        public String getDescription() {
            return "2586:6: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_memberCallArguments_18_0= ruleXShortClosure ) ) | ( ( (lv_memberCallArguments_19_0= ruleXExpression ) ) (otherlv_20= ',' ( (lv_memberCallArguments_21_0= ruleXExpression ) ) )* ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA44_0 = input.LA(1);

                         
                        int index44_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA44_0==RULE_ID) ) {s = 1;}

                        else if ( (LA44_0==19) ) {s = 2;}

                        else if ( (LA44_0==44) && (synpred17_InternalCheckCfg())) {s = 3;}

                        else if ( (LA44_0==60) && (synpred17_InternalCheckCfg())) {s = 4;}

                        else if ( ((LA44_0>=RULE_STRING && LA44_0<=RULE_DECIMAL)||LA44_0==15||LA44_0==17||(LA44_0>=23 && LA44_0<=24)||LA44_0==31||(LA44_0>=47 && LA44_0<=48)||LA44_0==53||LA44_0==62||LA44_0==64||(LA44_0>=68 && LA44_0<=69)||(LA44_0>=72 && LA44_0<=84)||LA44_0==86) ) {s = 5;}

                        else if ( (LA44_0==21) ) {s = 35;}

                         
                        input.seek(index44_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA44_1 = input.LA(1);

                         
                        int index44_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index44_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA44_2 = input.LA(1);

                         
                        int index44_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred17_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index44_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 44, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String[] dfa_33s = {
            "\5\2\6\uffff\3\2\1\uffff\3\2\1\uffff\1\2\1\1\43\2\1\uffff\33\2",
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
    static final short[][] dfa_33 = unpackEncodedStringArray(dfa_33s);

    class DFA46 extends DFA {

        public DFA46(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 46;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_33;
        }
        public String getDescription() {
            return "2684:5: ( ( ( () '[' ) )=> (lv_memberCallArguments_23_0= ruleXClosure ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA46_1 = input.LA(1);

                         
                        int index46_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred18_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index46_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 46, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_34s = "\40\uffff";
    static final String dfa_35s = "\1\4\26\uffff\1\0\10\uffff";
    static final String dfa_36s = "\1\126\26\uffff\1\0\10\uffff";
    static final String dfa_37s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\6\uffff\1\6\11\uffff\1\7\1\uffff\1\12\1\13\1\14\1\15\1\16\1\17\1\10\1\11";
    static final String dfa_38s = "\1\0\26\uffff\1\1\10\uffff}>";
    static final String[] dfa_39s = {
            "\4\14\1\5\6\uffff\1\2\1\uffff\1\27\1\uffff\1\35\3\uffff\2\14\6\uffff\1\5\36\uffff\1\26\1\uffff\1\3\3\uffff\1\30\1\31\2\uffff\5\5\1\1\4\14\1\32\1\33\1\34\1\uffff\1\4",
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
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_34 = DFA.unpackEncodedString(dfa_34s);
    static final char[] dfa_35 = DFA.unpackEncodedStringToUnsignedChars(dfa_35s);
    static final char[] dfa_36 = DFA.unpackEncodedStringToUnsignedChars(dfa_36s);
    static final short[] dfa_37 = DFA.unpackEncodedString(dfa_37s);
    static final short[] dfa_38 = DFA.unpackEncodedString(dfa_38s);
    static final short[][] dfa_39 = unpackEncodedStringArray(dfa_39s);

    class DFA48 extends DFA {

        public DFA48(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 48;
            this.eot = dfa_34;
            this.eof = dfa_34;
            this.min = dfa_35;
            this.max = dfa_36;
            this.accept = dfa_37;
            this.special = dfa_38;
            this.transition = dfa_39;
        }
        public String getDescription() {
            return "2729:2: (this_XConstructorCall_0= ruleXConstructorCall | this_XBlockExpression_1= ruleXBlockExpression | this_XSwitchExpression_2= ruleXSwitchExpression | ( ( ( () 'synchronized' '(' ) )=>this_XSynchronizedExpression_3= ruleXSynchronizedExpression ) | this_XFeatureCall_4= ruleXFeatureCall | this_XLiteral_5= ruleXLiteral | this_XIfExpression_6= ruleXIfExpression | ( ( ( () 'for' '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=>this_XForLoopExpression_7= ruleXForLoopExpression ) | this_XBasicForLoopExpression_8= ruleXBasicForLoopExpression | this_XWhileExpression_9= ruleXWhileExpression | this_XDoWhileExpression_10= ruleXDoWhileExpression | this_XThrowExpression_11= ruleXThrowExpression | this_XReturnExpression_12= ruleXReturnExpression | this_XTryCatchFinallyExpression_13= ruleXTryCatchFinallyExpression | this_XParenthesizedExpression_14= ruleXParenthesizedExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA48_0 = input.LA(1);

                         
                        int index48_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA48_0==77) ) {s = 1;}

                        else if ( (LA48_0==15) ) {s = 2;}

                        else if ( (LA48_0==64) ) {s = 3;}

                        else if ( (LA48_0==86) && (synpred19_InternalCheckCfg())) {s = 4;}

                        else if ( (LA48_0==RULE_ID||LA48_0==31||(LA48_0>=72 && LA48_0<=76)) ) {s = 5;}

                        else if ( ((LA48_0>=RULE_STRING && LA48_0<=RULE_DECIMAL)||(LA48_0>=23 && LA48_0<=24)||(LA48_0>=78 && LA48_0<=81)) ) {s = 12;}

                        else if ( (LA48_0==62) ) {s = 22;}

                        else if ( (LA48_0==17) ) {s = 23;}

                        else if ( (LA48_0==68) ) {s = 24;}

                        else if ( (LA48_0==69) ) {s = 25;}

                        else if ( (LA48_0==82) ) {s = 26;}

                        else if ( (LA48_0==83) ) {s = 27;}

                        else if ( (LA48_0==84) ) {s = 28;}

                        else if ( (LA48_0==19) ) {s = 29;}

                         
                        input.seek(index48_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA48_23 = input.LA(1);

                         
                        int index48_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred20_InternalCheckCfg()) ) {s = 30;}

                        else if ( (true) ) {s = 31;}

                         
                        input.seek(index48_23);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 48, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_40s = "\46\uffff";
    static final String dfa_41s = "\1\4\2\0\43\uffff";
    static final String dfa_42s = "\1\126\2\0\43\uffff";
    static final String dfa_43s = "\3\uffff\2\1\1\2\40\uffff";
    static final String dfa_44s = "\1\0\1\1\1\2\43\uffff}>";
    static final String[] dfa_45s = {
            "\4\5\1\1\6\uffff\1\5\1\uffff\1\5\1\uffff\1\2\3\uffff\3\5\5\uffff\1\5\14\uffff\1\3\2\uffff\2\5\4\uffff\1\5\6\uffff\1\4\1\uffff\1\5\1\uffff\1\5\3\uffff\21\5\1\uffff\1\5",
            "\1\uffff",
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
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_40 = DFA.unpackEncodedString(dfa_40s);
    static final char[] dfa_41 = DFA.unpackEncodedStringToUnsignedChars(dfa_41s);
    static final char[] dfa_42 = DFA.unpackEncodedStringToUnsignedChars(dfa_42s);
    static final short[] dfa_43 = DFA.unpackEncodedString(dfa_43s);
    static final short[] dfa_44 = DFA.unpackEncodedString(dfa_44s);
    static final short[][] dfa_45 = unpackEncodedStringArray(dfa_45s);

    class DFA57 extends DFA {

        public DFA57(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 57;
            this.eot = dfa_40;
            this.eof = dfa_40;
            this.min = dfa_41;
            this.max = dfa_42;
            this.accept = dfa_43;
            this.special = dfa_44;
            this.transition = dfa_45;
        }
        public String getDescription() {
            return "3221:3: ( ( ( ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> ( ( ( (lv_declaredFormalParameters_2_0= ruleJvmFormalParameter ) ) (otherlv_3= ',' ( (lv_declaredFormalParameters_4_0= ruleJvmFormalParameter ) ) )* )? ( (lv_explicitSyntax_5_0= '|' ) ) ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA57_0 = input.LA(1);

                         
                        int index57_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA57_0==RULE_ID) ) {s = 1;}

                        else if ( (LA57_0==19) ) {s = 2;}

                        else if ( (LA57_0==44) && (synpred23_InternalCheckCfg())) {s = 3;}

                        else if ( (LA57_0==60) && (synpred23_InternalCheckCfg())) {s = 4;}

                        else if ( ((LA57_0>=RULE_STRING && LA57_0<=RULE_DECIMAL)||LA57_0==15||LA57_0==17||(LA57_0>=23 && LA57_0<=25)||LA57_0==31||(LA57_0>=47 && LA57_0<=48)||LA57_0==53||LA57_0==62||LA57_0==64||(LA57_0>=68 && LA57_0<=84)||LA57_0==86) ) {s = 5;}

                         
                        input.seek(index57_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA57_1 = input.LA(1);

                         
                        int index57_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index57_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA57_2 = input.LA(1);

                         
                        int index57_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred23_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index57_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 57, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_46s = "\43\uffff";
    static final String dfa_47s = "\1\4\1\0\41\uffff";
    static final String dfa_48s = "\1\126\1\0\41\uffff";
    static final String dfa_49s = "\2\uffff\1\2\37\uffff\1\1";
    static final String dfa_50s = "\1\uffff\1\0\41\uffff}>";
    static final String[] dfa_51s = {
            "\5\2\6\uffff\1\2\1\uffff\1\2\1\uffff\1\1\3\uffff\2\2\6\uffff\1\2\14\uffff\1\2\2\uffff\2\2\4\uffff\1\2\10\uffff\1\2\1\uffff\1\2\3\uffff\2\2\2\uffff\15\2\1\uffff\1\2",
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
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_46 = DFA.unpackEncodedString(dfa_46s);
    static final char[] dfa_47 = DFA.unpackEncodedStringToUnsignedChars(dfa_47s);
    static final char[] dfa_48 = DFA.unpackEncodedStringToUnsignedChars(dfa_48s);
    static final short[] dfa_49 = DFA.unpackEncodedString(dfa_49s);
    static final short[] dfa_50 = DFA.unpackEncodedString(dfa_50s);
    static final short[][] dfa_51 = unpackEncodedStringArray(dfa_51s);

    class DFA64 extends DFA {

        public DFA64(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 64;
            this.eot = dfa_46;
            this.eof = dfa_46;
            this.min = dfa_47;
            this.max = dfa_48;
            this.accept = dfa_49;
            this.special = dfa_50;
            this.transition = dfa_51;
        }
        public String getDescription() {
            return "3687:3: ( ( ( ( ( '(' ( ( ruleJvmFormalParameter ) ) ':' ) )=> (otherlv_2= '(' ( (lv_declaredParam_3_0= ruleJvmFormalParameter ) ) otherlv_4= ':' ) ) ( (lv_switch_5_0= ruleXExpression ) ) otherlv_6= ')' ) | ( ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )? ( (lv_switch_9_0= ruleXExpression ) ) ) )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA64_1 = input.LA(1);

                         
                        int index64_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred26_InternalCheckCfg()) ) {s = 34;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index64_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 64, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_52s = "\42\uffff";
    static final String dfa_53s = "\1\4\2\0\37\uffff";
    static final String dfa_54s = "\1\126\2\0\37\uffff";
    static final String dfa_55s = "\3\uffff\1\1\1\2\35\uffff";
    static final String dfa_56s = "\1\0\1\1\1\2\37\uffff}>";
    static final String[] dfa_57s = {
            "\4\4\1\1\6\uffff\1\4\1\uffff\1\4\1\uffff\1\2\3\uffff\2\4\6\uffff\1\4\14\uffff\1\3\2\uffff\2\4\4\uffff\1\4\10\uffff\1\4\1\uffff\1\4\3\uffff\2\4\2\uffff\15\4\1\uffff\1\4",
            "\1\uffff",
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
            "",
            ""
    };

    static final short[] dfa_52 = DFA.unpackEncodedString(dfa_52s);
    static final char[] dfa_53 = DFA.unpackEncodedStringToUnsignedChars(dfa_53s);
    static final char[] dfa_54 = DFA.unpackEncodedStringToUnsignedChars(dfa_54s);
    static final short[] dfa_55 = DFA.unpackEncodedString(dfa_55s);
    static final short[] dfa_56 = DFA.unpackEncodedString(dfa_56s);
    static final short[][] dfa_57 = unpackEncodedStringArray(dfa_57s);

    class DFA63 extends DFA {

        public DFA63(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 63;
            this.eot = dfa_52;
            this.eof = dfa_52;
            this.min = dfa_53;
            this.max = dfa_54;
            this.accept = dfa_55;
            this.special = dfa_56;
            this.transition = dfa_57;
        }
        public String getDescription() {
            return "3756:5: ( ( ( ( ( ruleJvmFormalParameter ) ) ':' ) )=> ( ( (lv_declaredParam_7_0= ruleJvmFormalParameter ) ) otherlv_8= ':' ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA63_0 = input.LA(1);

                         
                        int index63_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA63_0==RULE_ID) ) {s = 1;}

                        else if ( (LA63_0==19) ) {s = 2;}

                        else if ( (LA63_0==44) && (synpred27_InternalCheckCfg())) {s = 3;}

                        else if ( ((LA63_0>=RULE_STRING && LA63_0<=RULE_DECIMAL)||LA63_0==15||LA63_0==17||(LA63_0>=23 && LA63_0<=24)||LA63_0==31||(LA63_0>=47 && LA63_0<=48)||LA63_0==53||LA63_0==62||LA63_0==64||(LA63_0>=68 && LA63_0<=69)||(LA63_0>=72 && LA63_0<=84)||LA63_0==86) ) {s = 4;}

                         
                        input.seek(index63_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA63_1 = input.LA(1);

                         
                        int index63_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_InternalCheckCfg()) ) {s = 3;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index63_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA63_2 = input.LA(1);

                         
                        int index63_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred27_InternalCheckCfg()) ) {s = 3;}

                        else if ( (true) ) {s = 4;}

                         
                        input.seek(index63_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 63, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA86 extends DFA {

        public DFA86(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 86;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_26;
        }
        public String getDescription() {
            return "4885:3: ( ( ( ( '(' ) )=> (lv_explicitOperationCall_7_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )? otherlv_12= ')' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA86_1 = input.LA(1);

                         
                        int index86_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index86_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 86, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA85 extends DFA {

        public DFA85(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 85;
            this.eot = dfa_27;
            this.eof = dfa_27;
            this.min = dfa_28;
            this.max = dfa_29;
            this.accept = dfa_30;
            this.special = dfa_31;
            this.transition = dfa_32;
        }
        public String getDescription() {
            return "4904:4: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_featureCallArguments_8_0= ruleXShortClosure ) ) | ( ( (lv_featureCallArguments_9_0= ruleXExpression ) ) (otherlv_10= ',' ( (lv_featureCallArguments_11_0= ruleXExpression ) ) )* ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA85_0 = input.LA(1);

                         
                        int index85_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA85_0==RULE_ID) ) {s = 1;}

                        else if ( (LA85_0==19) ) {s = 2;}

                        else if ( (LA85_0==44) && (synpred31_InternalCheckCfg())) {s = 3;}

                        else if ( (LA85_0==60) && (synpred31_InternalCheckCfg())) {s = 4;}

                        else if ( ((LA85_0>=RULE_STRING && LA85_0<=RULE_DECIMAL)||LA85_0==15||LA85_0==17||(LA85_0>=23 && LA85_0<=24)||LA85_0==31||(LA85_0>=47 && LA85_0<=48)||LA85_0==53||LA85_0==62||LA85_0==64||(LA85_0>=68 && LA85_0<=69)||(LA85_0>=72 && LA85_0<=84)||LA85_0==86) ) {s = 5;}

                        else if ( (LA85_0==21) ) {s = 35;}

                         
                        input.seek(index85_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA85_1 = input.LA(1);

                         
                        int index85_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred31_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index85_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA85_2 = input.LA(1);

                         
                        int index85_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred31_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index85_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 85, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA87 extends DFA {

        public DFA87(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 87;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_33;
        }
        public String getDescription() {
            return "5002:3: ( ( ( () '[' ) )=> (lv_featureCallArguments_13_0= ruleXClosure ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA87_1 = input.LA(1);

                         
                        int index87_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred32_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index87_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 87, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String[] dfa_58s = {
            "\5\2\6\uffff\3\2\1\uffff\3\2\1\uffff\10\2\1\1\34\2\1\uffff\33\2",
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
    static final short[][] dfa_58 = unpackEncodedStringArray(dfa_58s);

    class DFA91 extends DFA {

        public DFA91(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 91;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_58;
        }
        public String getDescription() {
            return "5161:3: ( ( ( '<' )=>otherlv_3= '<' ) ( (lv_typeArguments_4_0= ruleJvmArgumentTypeReference ) ) (otherlv_5= ',' ( (lv_typeArguments_6_0= ruleJvmArgumentTypeReference ) ) )* otherlv_7= '>' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA91_1 = input.LA(1);

                         
                        int index91_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred33_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index91_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 91, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA94 extends DFA {

        public DFA94(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 94;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_26;
        }
        public String getDescription() {
            return "5218:3: ( ( ( ( '(' ) )=> (lv_explicitConstructorCall_8_0= '(' ) ) ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )? otherlv_13= ')' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA94_1 = input.LA(1);

                         
                        int index94_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred34_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index94_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 94, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA93 extends DFA {

        public DFA93(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 93;
            this.eot = dfa_27;
            this.eof = dfa_27;
            this.min = dfa_28;
            this.max = dfa_29;
            this.accept = dfa_30;
            this.special = dfa_31;
            this.transition = dfa_32;
        }
        public String getDescription() {
            return "5237:4: ( ( ( ( () ( ( ( ruleJvmFormalParameter ) ) ( ',' ( ( ruleJvmFormalParameter ) ) )* )? ( ( '|' ) ) ) )=> (lv_arguments_9_0= ruleXShortClosure ) ) | ( ( (lv_arguments_10_0= ruleXExpression ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleXExpression ) ) )* ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA93_0 = input.LA(1);

                         
                        int index93_0 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (LA93_0==RULE_ID) ) {s = 1;}

                        else if ( (LA93_0==19) ) {s = 2;}

                        else if ( (LA93_0==44) && (synpred35_InternalCheckCfg())) {s = 3;}

                        else if ( (LA93_0==60) && (synpred35_InternalCheckCfg())) {s = 4;}

                        else if ( ((LA93_0>=RULE_STRING && LA93_0<=RULE_DECIMAL)||LA93_0==15||LA93_0==17||(LA93_0>=23 && LA93_0<=24)||LA93_0==31||(LA93_0>=47 && LA93_0<=48)||LA93_0==53||LA93_0==62||LA93_0==64||(LA93_0>=68 && LA93_0<=69)||(LA93_0>=72 && LA93_0<=84)||LA93_0==86) ) {s = 5;}

                        else if ( (LA93_0==21) ) {s = 35;}

                         
                        input.seek(index93_0);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA93_1 = input.LA(1);

                         
                        int index93_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred35_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index93_1);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA93_2 = input.LA(1);

                         
                        int index93_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred35_InternalCheckCfg()) ) {s = 4;}

                        else if ( (true) ) {s = 5;}

                         
                        input.seek(index93_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 93, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA95 extends DFA {

        public DFA95(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 95;
            this.eot = dfa_20;
            this.eof = dfa_21;
            this.min = dfa_22;
            this.max = dfa_23;
            this.accept = dfa_24;
            this.special = dfa_25;
            this.transition = dfa_33;
        }
        public String getDescription() {
            return "5335:3: ( ( ( () '[' ) )=> (lv_arguments_14_0= ruleXClosure ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA95_1 = input.LA(1);

                         
                        int index95_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred36_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index95_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 95, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_59s = "\1\41\115\uffff";
    static final String dfa_60s = "\1\4\40\0\55\uffff";
    static final String dfa_61s = "\1\127\40\0\55\uffff";
    static final String dfa_62s = "\41\uffff\1\2\53\uffff\1\1";
    static final String dfa_63s = "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37\55\uffff}>";
    static final String[] dfa_64s = {
            "\1\27\1\23\1\24\1\25\1\1\6\uffff\1\12\1\41\1\32\1\uffff\1\40\2\41\1\uffff\1\17\1\20\6\41\1\15\17\41\1\10\1\7\4\41\1\6\6\41\1\uffff\1\41\1\31\1\41\1\13\3\41\1\33\1\34\2\41\1\2\1\3\1\4\1\5\1\16\1\11\1\21\1\22\1\26\1\30\1\35\1\36\1\37\1\41\1\14\1\41",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
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
    static final short[] dfa_59 = DFA.unpackEncodedString(dfa_59s);
    static final char[] dfa_60 = DFA.unpackEncodedStringToUnsignedChars(dfa_60s);
    static final char[] dfa_61 = DFA.unpackEncodedStringToUnsignedChars(dfa_61s);
    static final short[] dfa_62 = DFA.unpackEncodedString(dfa_62s);
    static final short[] dfa_63 = DFA.unpackEncodedString(dfa_63s);
    static final short[][] dfa_64 = unpackEncodedStringArray(dfa_64s);

    class DFA98 extends DFA {

        public DFA98(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 98;
            this.eot = dfa_20;
            this.eof = dfa_59;
            this.min = dfa_60;
            this.max = dfa_61;
            this.accept = dfa_62;
            this.special = dfa_63;
            this.transition = dfa_64;
        }
        public String getDescription() {
            return "5678:3: ( ( 'extends' | 'static' | 'import' | 'extension' | '!' | '-' | '+' | 'new' | '{' | 'switch' | 'synchronized' | '<' | 'super' | '#' | '[' | 'false' | 'true' | 'null' | 'typeof' | 'if' | 'for' | 'while' | 'do' | 'throw' | 'return' | 'try' | '(' | RULE_ID | RULE_HEX | RULE_INT | RULE_DECIMAL | RULE_STRING )=> (lv_expression_2_0= ruleXExpression ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA98_1 = input.LA(1);

                         
                        int index98_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA98_2 = input.LA(1);

                         
                        int index98_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA98_3 = input.LA(1);

                         
                        int index98_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA98_4 = input.LA(1);

                         
                        int index98_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA98_5 = input.LA(1);

                         
                        int index98_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA98_6 = input.LA(1);

                         
                        int index98_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA98_7 = input.LA(1);

                         
                        int index98_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA98_8 = input.LA(1);

                         
                        int index98_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA98_9 = input.LA(1);

                         
                        int index98_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA98_10 = input.LA(1);

                         
                        int index98_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA98_11 = input.LA(1);

                         
                        int index98_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA98_12 = input.LA(1);

                         
                        int index98_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA98_13 = input.LA(1);

                         
                        int index98_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_13);
                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA98_14 = input.LA(1);

                         
                        int index98_14 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_14);
                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA98_15 = input.LA(1);

                         
                        int index98_15 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_15);
                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA98_16 = input.LA(1);

                         
                        int index98_16 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_16);
                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA98_17 = input.LA(1);

                         
                        int index98_17 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_17);
                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA98_18 = input.LA(1);

                         
                        int index98_18 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_18);
                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA98_19 = input.LA(1);

                         
                        int index98_19 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_19);
                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA98_20 = input.LA(1);

                         
                        int index98_20 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_20);
                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA98_21 = input.LA(1);

                         
                        int index98_21 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_21);
                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA98_22 = input.LA(1);

                         
                        int index98_22 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_22);
                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA98_23 = input.LA(1);

                         
                        int index98_23 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_23);
                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA98_24 = input.LA(1);

                         
                        int index98_24 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_24);
                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA98_25 = input.LA(1);

                         
                        int index98_25 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_25);
                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA98_26 = input.LA(1);

                         
                        int index98_26 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_26);
                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA98_27 = input.LA(1);

                         
                        int index98_27 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_27);
                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA98_28 = input.LA(1);

                         
                        int index98_28 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_28);
                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA98_29 = input.LA(1);

                         
                        int index98_29 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_29);
                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA98_30 = input.LA(1);

                         
                        int index98_30 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_30);
                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA98_31 = input.LA(1);

                         
                        int index98_31 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_31);
                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA98_32 = input.LA(1);

                         
                        int index98_32 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred37_InternalCheckCfg()) ) {s = 77;}

                        else if ( (true) ) {s = 33;}

                         
                        input.seek(index98_32);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 98, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_65s = "\117\uffff";
    static final String dfa_66s = "\1\2\116\uffff";
    static final String dfa_67s = "\1\4\1\0\115\uffff";
    static final String dfa_68s = "\1\131\1\0\115\uffff";
    static final String dfa_69s = "\2\uffff\1\2\113\uffff\1\1";
    static final String dfa_70s = "\1\uffff\1\0\115\uffff}>";
    static final String[] dfa_71s = {
            "\5\2\6\uffff\3\2\1\uffff\3\2\1\uffff\10\2\1\1\34\2\1\uffff\33\2\1\uffff\1\2",
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

    static final short[] dfa_65 = DFA.unpackEncodedString(dfa_65s);
    static final short[] dfa_66 = DFA.unpackEncodedString(dfa_66s);
    static final char[] dfa_67 = DFA.unpackEncodedStringToUnsignedChars(dfa_67s);
    static final char[] dfa_68 = DFA.unpackEncodedStringToUnsignedChars(dfa_68s);
    static final short[] dfa_69 = DFA.unpackEncodedString(dfa_69s);
    static final short[] dfa_70 = DFA.unpackEncodedString(dfa_70s);
    static final short[][] dfa_71 = unpackEncodedStringArray(dfa_71s);

    class DFA116 extends DFA {

        public DFA116(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 116;
            this.eot = dfa_65;
            this.eof = dfa_66;
            this.min = dfa_67;
            this.max = dfa_68;
            this.accept = dfa_69;
            this.special = dfa_70;
            this.transition = dfa_71;
        }
        public String getDescription() {
            return "6336:3: ( ( ( '<' )=>otherlv_1= '<' ) ( (lv_arguments_2_0= ruleJvmArgumentTypeReference ) ) (otherlv_3= ',' ( (lv_arguments_4_0= ruleJvmArgumentTypeReference ) ) )* otherlv_5= '>' ( ( ( ( () '.' ) )=> ( () otherlv_7= '.' ) ) ( ( ruleValidID ) ) ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )? )* )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA116_1 = input.LA(1);

                         
                        int index116_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_InternalCheckCfg()) ) {s = 78;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index116_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 116, _s, input);
            error(nvae);
            throw nvae;
        }
    }

    class DFA114 extends DFA {

        public DFA114(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 114;
            this.eot = dfa_65;
            this.eof = dfa_66;
            this.min = dfa_67;
            this.max = dfa_68;
            this.accept = dfa_69;
            this.special = dfa_70;
            this.transition = dfa_71;
        }
        public String getDescription() {
            return "6430:5: ( ( ( '<' )=>otherlv_9= '<' ) ( (lv_arguments_10_0= ruleJvmArgumentTypeReference ) ) (otherlv_11= ',' ( (lv_arguments_12_0= ruleJvmArgumentTypeReference ) ) )* otherlv_13= '>' )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA114_1 = input.LA(1);

                         
                        int index114_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred46_InternalCheckCfg()) ) {s = 78;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index114_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 114, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String dfa_72s = "\7\uffff";
    static final String dfa_73s = "\2\uffff\1\4\2\uffff\1\4\1\uffff";
    static final String dfa_74s = "\1\10\1\uffff\1\71\1\10\1\uffff\1\71\1\uffff";
    static final String dfa_75s = "\1\111\1\uffff\1\75\1\61\1\uffff\1\75\1\uffff";
    static final String dfa_76s = "\1\uffff\1\1\2\uffff\1\2\1\uffff\1\3";
    static final String dfa_77s = "\7\uffff}>";
    static final String[] dfa_78s = {
            "\1\2\100\uffff\1\1",
            "",
            "\1\3\3\uffff\1\4",
            "\1\5\50\uffff\1\6",
            "",
            "\1\3\3\uffff\1\4",
            ""
    };

    static final short[] dfa_72 = DFA.unpackEncodedString(dfa_72s);
    static final short[] dfa_73 = DFA.unpackEncodedString(dfa_73s);
    static final char[] dfa_74 = DFA.unpackEncodedStringToUnsignedChars(dfa_74s);
    static final char[] dfa_75 = DFA.unpackEncodedStringToUnsignedChars(dfa_75s);
    static final short[] dfa_76 = DFA.unpackEncodedString(dfa_76s);
    static final short[] dfa_77 = DFA.unpackEncodedString(dfa_77s);
    static final short[][] dfa_78 = unpackEncodedStringArray(dfa_78s);

    class DFA123 extends DFA {

        public DFA123(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 123;
            this.eot = dfa_72;
            this.eof = dfa_73;
            this.min = dfa_74;
            this.max = dfa_75;
            this.accept = dfa_76;
            this.special = dfa_77;
            this.transition = dfa_78;
        }
        public String getDescription() {
            return "6892:3: ( ( ( (lv_static_1_0= 'static' ) ) ( (lv_extension_2_0= 'extension' ) )? ( ( ruleQualifiedNameInStaticImport ) ) ( ( (lv_wildcard_4_0= '*' ) ) | ( (lv_memberName_5_0= ruleValidID ) ) ) ) | ( ( ruleQualifiedName ) ) | ( (lv_importedNamespace_7_0= ruleQualifiedNameWithWildcard ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000028102L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000028002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000050100L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000010100L,0x000000003C000004L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000010000L,0x000000003C000004L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x00218000008000F0L,0x000000000000C000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x00218000000000F0L,0x000000000000C000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x00218000020000F0L,0x000000000000C000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000002100000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x40218000818A81F0L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x00000001FC000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x000000F000000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000010380000002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000100000080100L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x00007E0180000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000100080000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0001800000000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x001E000000000002L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0040000000000002L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0180000000000002L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0E00000000000002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000100L,0x0000000000000F00L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000080000100L,0x0000000000001F00L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000100000080100L,0x0000000001000000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000100100000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0E00000001080002L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x5021900081AA81F0L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0E00000001000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x40218000818B81F0L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000110000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x40218000838A81F0L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x50219000838A81F0L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x1000000000100000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x40218000838A81F0L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x60218000818A81F2L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x40218000818A81F2L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_56 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_57 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_58 = new BitSet(new long[]{0x40219000818A81F0L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_59 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_60 = new BitSet(new long[]{0x0000100000190100L,0x000000000000000EL});
    public static final BitSet FOLLOW_61 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_62 = new BitSet(new long[]{0x0000000000100000L,0x000000000000000AL});
    public static final BitSet FOLLOW_63 = new BitSet(new long[]{0x0000000000100000L,0x0000000000000002L});
    public static final BitSet FOLLOW_64 = new BitSet(new long[]{0x60218000818A81F0L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_65 = new BitSet(new long[]{0x2000000000100000L});
    public static final BitSet FOLLOW_66 = new BitSet(new long[]{0x40218000818A81F0L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_67 = new BitSet(new long[]{0x60218000818A81F0L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_68 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_69 = new BitSet(new long[]{0x4021800081AA81F0L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_70 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_71 = new BitSet(new long[]{0x40218000818B81F0L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_72 = new BitSet(new long[]{0x60218000818B81F0L,0x00000000005FFFF1L});
    public static final BitSet FOLLOW_73 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_74 = new BitSet(new long[]{0x0000000001080002L});
    public static final BitSet FOLLOW_75 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_76 = new BitSet(new long[]{0x0000000081080002L});
    public static final BitSet FOLLOW_77 = new BitSet(new long[]{0x0000000001200000L});
    public static final BitSet FOLLOW_78 = new BitSet(new long[]{0x40218000818A81F2L,0x00000000005FFF31L});
    public static final BitSet FOLLOW_79 = new BitSet(new long[]{0x0000000000000000L,0x0000000000A00000L});
    public static final BitSet FOLLOW_80 = new BitSet(new long[]{0x0000000000000002L,0x0000000000A00000L});
    public static final BitSet FOLLOW_81 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_82 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_83 = new BitSet(new long[]{0x0000100000280100L});
    public static final BitSet FOLLOW_84 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_85 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_86 = new BitSet(new long[]{0x0200000080000002L});
    public static final BitSet FOLLOW_87 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001100L});
    public static final BitSet FOLLOW_88 = new BitSet(new long[]{0x0000000000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_89 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_90 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_91 = new BitSet(new long[]{0x0000000000000100L,0x0000000000000200L});
    public static final BitSet FOLLOW_92 = new BitSet(new long[]{0x0000000000000100L,0x0000000000000800L});
    public static final BitSet FOLLOW_93 = new BitSet(new long[]{0x0002000000000100L});
    public static final BitSet FOLLOW_94 = new BitSet(new long[]{0x2000000000000002L});
    public static final BitSet FOLLOW_95 = new BitSet(new long[]{0x0000000000000102L});

}