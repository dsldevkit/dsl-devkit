package com.avaloq.tools.ddk.xtext.formatter.parser.antlr.internal; 

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
import com.avaloq.tools.ddk.xtext.formatter.services.FormatterTestLanguageGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalFormatterTestLanguageParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'test'", "'post'", "';'", "'='", "'+='", "'['", "','", "']'", "'void'", "'('", "')'", "':'", "'space'", "'linewrap'", "'wrapminmax'", "'indentation'", "'{'", "'}'", "'column'", "'item'", "'offset'", "'value'", "'pair'", "'padding'", "'fqn'", "'.'", "'fqnref'", "'enum'", "'`'", "'%'", "'<'", "'>'", "'datatypes'", "'kw1'", "'kw3'", "'lit1'", "'lit2'", "'lit3'"
    };
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__48=48;
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


        public InternalFormatterTestLanguageParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalFormatterTestLanguageParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalFormatterTestLanguageParser.tokenNames; }
    public String getGrammarFileName() { return "../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g"; }



     	private FormatterTestLanguageGrammarAccess grammarAccess;
     	
        public InternalFormatterTestLanguageParser(TokenStream input, FormatterTestLanguageGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Root";	
       	}
       	
       	@Override
       	protected FormatterTestLanguageGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleRoot"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:68:1: entryRuleRoot returns [EObject current=null] : iv_ruleRoot= ruleRoot EOF ;
    public final EObject entryRuleRoot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRoot = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:69:2: (iv_ruleRoot= ruleRoot EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:70:2: iv_ruleRoot= ruleRoot EOF
            {
             newCompositeNode(grammarAccess.getRootRule()); 
            pushFollow(FOLLOW_ruleRoot_in_entryRuleRoot75);
            iv_ruleRoot=ruleRoot();

            state._fsp--;

             current =iv_ruleRoot; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRoot85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRoot"


    // $ANTLR start "ruleRoot"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:77:1: ruleRoot returns [EObject current=null] : (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) ) ;
    public final EObject ruleRoot() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject this_TestLinewrap_1 = null;

        EObject this_TestIndentation_2 = null;

        EObject this_TestLinewrapMinMax_3 = null;

        EObject this_TestColumn_4 = null;

        EObject this_TestOffset_5 = null;

        EObject this_TestRightPadding_6 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:80:28: ( (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:81:1: (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:81:1: (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:81:3: otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding )
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleRoot122); 

                	newLeafNode(otherlv_0, grammarAccess.getRootAccess().getTestKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:85:1: (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding )
            int alt1=6;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt1=1;
                }
                break;
            case 26:
                {
                alt1=2;
                }
                break;
            case 25:
                {
                alt1=3;
                }
                break;
            case 29:
                {
                alt1=4;
                }
                break;
            case 31:
                {
                alt1=5;
                }
                break;
            case 34:
                {
                alt1=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:86:5: this_TestLinewrap_1= ruleTestLinewrap
                    {
                     
                            newCompositeNode(grammarAccess.getRootAccess().getTestLinewrapParserRuleCall_1_0()); 
                        
                    pushFollow(FOLLOW_ruleTestLinewrap_in_ruleRoot145);
                    this_TestLinewrap_1=ruleTestLinewrap();

                    state._fsp--;

                     
                            current = this_TestLinewrap_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:96:5: this_TestIndentation_2= ruleTestIndentation
                    {
                     
                            newCompositeNode(grammarAccess.getRootAccess().getTestIndentationParserRuleCall_1_1()); 
                        
                    pushFollow(FOLLOW_ruleTestIndentation_in_ruleRoot172);
                    this_TestIndentation_2=ruleTestIndentation();

                    state._fsp--;

                     
                            current = this_TestIndentation_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:106:5: this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax
                    {
                     
                            newCompositeNode(grammarAccess.getRootAccess().getTestLinewrapMinMaxParserRuleCall_1_2()); 
                        
                    pushFollow(FOLLOW_ruleTestLinewrapMinMax_in_ruleRoot199);
                    this_TestLinewrapMinMax_3=ruleTestLinewrapMinMax();

                    state._fsp--;

                     
                            current = this_TestLinewrapMinMax_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:116:5: this_TestColumn_4= ruleTestColumn
                    {
                     
                            newCompositeNode(grammarAccess.getRootAccess().getTestColumnParserRuleCall_1_3()); 
                        
                    pushFollow(FOLLOW_ruleTestColumn_in_ruleRoot226);
                    this_TestColumn_4=ruleTestColumn();

                    state._fsp--;

                     
                            current = this_TestColumn_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:126:5: this_TestOffset_5= ruleTestOffset
                    {
                     
                            newCompositeNode(grammarAccess.getRootAccess().getTestOffsetParserRuleCall_1_4()); 
                        
                    pushFollow(FOLLOW_ruleTestOffset_in_ruleRoot253);
                    this_TestOffset_5=ruleTestOffset();

                    state._fsp--;

                     
                            current = this_TestOffset_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:136:5: this_TestRightPadding_6= ruleTestRightPadding
                    {
                     
                            newCompositeNode(grammarAccess.getRootAccess().getTestRightPaddingParserRuleCall_1_5()); 
                        
                    pushFollow(FOLLOW_ruleTestRightPadding_in_ruleRoot280);
                    this_TestRightPadding_6=ruleTestRightPadding();

                    state._fsp--;

                     
                            current = this_TestRightPadding_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

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
    // $ANTLR end "ruleRoot"


    // $ANTLR start "entryRuleLine"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:152:1: entryRuleLine returns [EObject current=null] : iv_ruleLine= ruleLine EOF ;
    public final EObject entryRuleLine() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLine = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:153:2: (iv_ruleLine= ruleLine EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:154:2: iv_ruleLine= ruleLine EOF
            {
             newCompositeNode(grammarAccess.getLineRule()); 
            pushFollow(FOLLOW_ruleLine_in_entryRuleLine316);
            iv_ruleLine=ruleLine();

            state._fsp--;

             current =iv_ruleLine; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleLine326); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLine"


    // $ANTLR start "ruleLine"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:161:1: ruleLine returns [EObject current=null] : ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' ) ;
    public final EObject ruleLine() throws RecognitionException {
        EObject current = null;

        Token otherlv_7=null;
        Token otherlv_10=null;
        EObject this_Decl_0 = null;

        EObject this_Assign_1 = null;

        EObject this_Meth_2 = null;

        EObject this_FqnObj_3 = null;

        EObject this_FqnRef_4 = null;

        EObject this_Enumeration_5 = null;

        EObject this_SuppressedHidden_6 = null;

        EObject this_Space_8 = null;

        EObject this_Datatypes_9 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:164:28: ( ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:165:1: ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:165:1: ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:165:2: (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';'
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:165:2: (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes )
            int alt2=9;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:166:5: this_Decl_0= ruleDecl
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getDeclParserRuleCall_0_0()); 
                        
                    pushFollow(FOLLOW_ruleDecl_in_ruleLine374);
                    this_Decl_0=ruleDecl();

                    state._fsp--;

                     
                            current = this_Decl_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:176:5: this_Assign_1= ruleAssign
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getAssignParserRuleCall_0_1()); 
                        
                    pushFollow(FOLLOW_ruleAssign_in_ruleLine401);
                    this_Assign_1=ruleAssign();

                    state._fsp--;

                     
                            current = this_Assign_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:186:5: this_Meth_2= ruleMeth
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getMethParserRuleCall_0_2()); 
                        
                    pushFollow(FOLLOW_ruleMeth_in_ruleLine428);
                    this_Meth_2=ruleMeth();

                    state._fsp--;

                     
                            current = this_Meth_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:196:5: this_FqnObj_3= ruleFqnObj
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getFqnObjParserRuleCall_0_3()); 
                        
                    pushFollow(FOLLOW_ruleFqnObj_in_ruleLine455);
                    this_FqnObj_3=ruleFqnObj();

                    state._fsp--;

                     
                            current = this_FqnObj_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:206:5: this_FqnRef_4= ruleFqnRef
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getFqnRefParserRuleCall_0_4()); 
                        
                    pushFollow(FOLLOW_ruleFqnRef_in_ruleLine482);
                    this_FqnRef_4=ruleFqnRef();

                    state._fsp--;

                     
                            current = this_FqnRef_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:216:5: this_Enumeration_5= ruleEnumeration
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getEnumerationParserRuleCall_0_5()); 
                        
                    pushFollow(FOLLOW_ruleEnumeration_in_ruleLine509);
                    this_Enumeration_5=ruleEnumeration();

                    state._fsp--;

                     
                            current = this_Enumeration_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:225:6: (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:225:6: (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:226:5: this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post'
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getSuppressedHiddenParserRuleCall_0_6_0()); 
                        
                    pushFollow(FOLLOW_ruleSuppressedHidden_in_ruleLine537);
                    this_SuppressedHidden_6=ruleSuppressedHidden();

                    state._fsp--;

                     
                            current = this_SuppressedHidden_6; 
                            afterParserOrEnumRuleCall();
                        
                    otherlv_7=(Token)match(input,12,FOLLOW_12_in_ruleLine548); 

                        	newLeafNode(otherlv_7, grammarAccess.getLineAccess().getPostKeyword_0_6_1());
                        

                    }


                    }
                    break;
                case 8 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:240:5: this_Space_8= ruleSpace
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getSpaceParserRuleCall_0_7()); 
                        
                    pushFollow(FOLLOW_ruleSpace_in_ruleLine577);
                    this_Space_8=ruleSpace();

                    state._fsp--;

                     
                            current = this_Space_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:250:5: this_Datatypes_9= ruleDatatypes
                    {
                     
                            newCompositeNode(grammarAccess.getLineAccess().getDatatypesParserRuleCall_0_8()); 
                        
                    pushFollow(FOLLOW_ruleDatatypes_in_ruleLine604);
                    this_Datatypes_9=ruleDatatypes();

                    state._fsp--;

                     
                            current = this_Datatypes_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }

            otherlv_10=(Token)match(input,13,FOLLOW_13_in_ruleLine616); 

                	newLeafNode(otherlv_10, grammarAccess.getLineAccess().getSemicolonKeyword_1());
                

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
    // $ANTLR end "ruleLine"


    // $ANTLR start "entryRuleDecl"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:270:1: entryRuleDecl returns [EObject current=null] : iv_ruleDecl= ruleDecl EOF ;
    public final EObject entryRuleDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDecl = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:271:2: (iv_ruleDecl= ruleDecl EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:272:2: iv_ruleDecl= ruleDecl EOF
            {
             newCompositeNode(grammarAccess.getDeclRule()); 
            pushFollow(FOLLOW_ruleDecl_in_entryRuleDecl652);
            iv_ruleDecl=ruleDecl();

            state._fsp--;

             current =iv_ruleDecl; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDecl662); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDecl"


    // $ANTLR start "ruleDecl"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:279:1: ruleDecl returns [EObject current=null] : ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleDecl() throws RecognitionException {
        EObject current = null;

        Token lv_type_0_0=null;
        Token lv_name_1_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:282:28: ( ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:283:1: ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:283:1: ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:283:2: ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:283:2: ( (lv_type_0_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:284:1: (lv_type_0_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:284:1: (lv_type_0_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:285:3: lv_type_0_0= RULE_ID
            {
            lv_type_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDecl704); 

            			newLeafNode(lv_type_0_0, grammarAccess.getDeclAccess().getTypeIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDeclRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"type",
                    		lv_type_0_0, 
                    		"ID");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:301:2: ( (lv_name_1_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:302:1: (lv_name_1_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:302:1: (lv_name_1_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:303:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDecl726); 

            			newLeafNode(lv_name_1_0, grammarAccess.getDeclAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getDeclRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

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
    // $ANTLR end "ruleDecl"


    // $ANTLR start "entryRuleAssign"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:327:1: entryRuleAssign returns [EObject current=null] : iv_ruleAssign= ruleAssign EOF ;
    public final EObject entryRuleAssign() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssign = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:328:2: (iv_ruleAssign= ruleAssign EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:329:2: iv_ruleAssign= ruleAssign EOF
            {
             newCompositeNode(grammarAccess.getAssignRule()); 
            pushFollow(FOLLOW_ruleAssign_in_entryRuleAssign767);
            iv_ruleAssign=ruleAssign();

            state._fsp--;

             current =iv_ruleAssign; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAssign777); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssign"


    // $ANTLR start "ruleAssign"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:336:1: ruleAssign returns [EObject current=null] : ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' ) ;
    public final EObject ruleAssign() throws RecognitionException {
        EObject current = null;

        Token lv_var_0_0=null;
        Token lv_op_1_1=null;
        Token lv_op_1_2=null;
        Token otherlv_2=null;
        Token lv_val_3_0=null;
        Token otherlv_4=null;
        Token lv_val_5_0=null;
        Token otherlv_6=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:339:28: ( ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:340:1: ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:340:1: ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:340:2: ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']'
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:340:2: ( (lv_var_0_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:341:1: (lv_var_0_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:341:1: (lv_var_0_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:342:3: lv_var_0_0= RULE_ID
            {
            lv_var_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleAssign819); 

            			newLeafNode(lv_var_0_0, grammarAccess.getAssignAccess().getVarIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getAssignRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"var",
                    		lv_var_0_0, 
                    		"ID");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:358:2: ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:359:1: ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:359:1: ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:360:1: (lv_op_1_1= '=' | lv_op_1_2= '+=' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:360:1: (lv_op_1_1= '=' | lv_op_1_2= '+=' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==14) ) {
                alt3=1;
            }
            else if ( (LA3_0==15) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:361:3: lv_op_1_1= '='
                    {
                    lv_op_1_1=(Token)match(input,14,FOLLOW_14_in_ruleAssign844); 

                            newLeafNode(lv_op_1_1, grammarAccess.getAssignAccess().getOpEqualsSignKeyword_1_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAssignRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_1, null);
                    	    

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:373:8: lv_op_1_2= '+='
                    {
                    lv_op_1_2=(Token)match(input,15,FOLLOW_15_in_ruleAssign873); 

                            newLeafNode(lv_op_1_2, grammarAccess.getAssignAccess().getOpPlusSignEqualsSignKeyword_1_0_1());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAssignRule());
                    	        }
                           		setWithLastConsumed(current, "op", lv_op_1_2, null);
                    	    

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleAssign901); 

                	newLeafNode(otherlv_2, grammarAccess.getAssignAccess().getLeftSquareBracketKeyword_2());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:392:1: ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_INT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:392:2: ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:392:2: ( (lv_val_3_0= RULE_INT ) )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:393:1: (lv_val_3_0= RULE_INT )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:393:1: (lv_val_3_0= RULE_INT )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:394:3: lv_val_3_0= RULE_INT
                    {
                    lv_val_3_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAssign919); 

                    			newLeafNode(lv_val_3_0, grammarAccess.getAssignAccess().getValINTTerminalRuleCall_3_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getAssignRule());
                    	        }
                           		addWithLastConsumed(
                           			current, 
                           			"val",
                            		lv_val_3_0, 
                            		"INT");
                    	    

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:410:2: (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==17) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:410:4: otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) )
                    	    {
                    	    otherlv_4=(Token)match(input,17,FOLLOW_17_in_ruleAssign937); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getAssignAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:414:1: ( (lv_val_5_0= RULE_INT ) )
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:415:1: (lv_val_5_0= RULE_INT )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:415:1: (lv_val_5_0= RULE_INT )
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:416:3: lv_val_5_0= RULE_INT
                    	    {
                    	    lv_val_5_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleAssign954); 

                    	    			newLeafNode(lv_val_5_0, grammarAccess.getAssignAccess().getValINTTerminalRuleCall_3_1_1_0()); 
                    	    		

                    	    	        if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getAssignRule());
                    	    	        }
                    	           		addWithLastConsumed(
                    	           			current, 
                    	           			"val",
                    	            		lv_val_5_0, 
                    	            		"INT");
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,18,FOLLOW_18_in_ruleAssign975); 

                	newLeafNode(otherlv_6, grammarAccess.getAssignAccess().getRightSquareBracketKeyword_4());
                

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
    // $ANTLR end "ruleAssign"


    // $ANTLR start "entryRuleMeth"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:444:1: entryRuleMeth returns [EObject current=null] : iv_ruleMeth= ruleMeth EOF ;
    public final EObject entryRuleMeth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMeth = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:445:2: (iv_ruleMeth= ruleMeth EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:446:2: iv_ruleMeth= ruleMeth EOF
            {
             newCompositeNode(grammarAccess.getMethRule()); 
            pushFollow(FOLLOW_ruleMeth_in_entryRuleMeth1011);
            iv_ruleMeth=ruleMeth();

            state._fsp--;

             current =iv_ruleMeth; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMeth1021); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMeth"


    // $ANTLR start "ruleMeth"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:453:1: ruleMeth returns [EObject current=null] : (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' ) ;
    public final EObject ruleMeth() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_param_3_0 = null;

        EObject lv_param_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:456:28: ( (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:457:1: (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:457:1: (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:457:3: otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleMeth1058); 

                	newLeafNode(otherlv_0, grammarAccess.getMethAccess().getVoidKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:461:1: ( (lv_name_1_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:462:1: (lv_name_1_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:462:1: (lv_name_1_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:463:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleMeth1075); 

            			newLeafNode(lv_name_1_0, grammarAccess.getMethAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMethRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleMeth1092); 

                	newLeafNode(otherlv_2, grammarAccess.getMethAccess().getLeftParenthesisKeyword_2());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:483:1: ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:483:2: ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:483:2: ( (lv_param_3_0= ruleParam ) )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:484:1: (lv_param_3_0= ruleParam )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:484:1: (lv_param_3_0= ruleParam )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:485:3: lv_param_3_0= ruleParam
                    {
                     
                    	        newCompositeNode(grammarAccess.getMethAccess().getParamParamParserRuleCall_3_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleParam_in_ruleMeth1114);
                    lv_param_3_0=ruleParam();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getMethRule());
                    	        }
                           		add(
                           			current, 
                           			"param",
                            		lv_param_3_0, 
                            		"Param");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:501:2: (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==17) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:501:4: otherlv_4= ',' ( (lv_param_5_0= ruleParam ) )
                    	    {
                    	    otherlv_4=(Token)match(input,17,FOLLOW_17_in_ruleMeth1127); 

                    	        	newLeafNode(otherlv_4, grammarAccess.getMethAccess().getCommaKeyword_3_1_0());
                    	        
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:505:1: ( (lv_param_5_0= ruleParam ) )
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:506:1: (lv_param_5_0= ruleParam )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:506:1: (lv_param_5_0= ruleParam )
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:507:3: lv_param_5_0= ruleParam
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getMethAccess().getParamParamParserRuleCall_3_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleParam_in_ruleMeth1148);
                    	    lv_param_5_0=ruleParam();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getMethRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"param",
                    	            		lv_param_5_0, 
                    	            		"Param");
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
                    break;

            }

            otherlv_6=(Token)match(input,21,FOLLOW_21_in_ruleMeth1164); 

                	newLeafNode(otherlv_6, grammarAccess.getMethAccess().getRightParenthesisKeyword_4());
                

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
    // $ANTLR end "ruleMeth"


    // $ANTLR start "entryRuleParam"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:535:1: entryRuleParam returns [EObject current=null] : iv_ruleParam= ruleParam EOF ;
    public final EObject entryRuleParam() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParam = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:536:2: (iv_ruleParam= ruleParam EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:537:2: iv_ruleParam= ruleParam EOF
            {
             newCompositeNode(grammarAccess.getParamRule()); 
            pushFollow(FOLLOW_ruleParam_in_entryRuleParam1200);
            iv_ruleParam=ruleParam();

            state._fsp--;

             current =iv_ruleParam; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParam1210); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParam"


    // $ANTLR start "ruleParam"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:544:1: ruleParam returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ) ;
    public final EObject ruleParam() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_type_2_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:547:28: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:548:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:548:1: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:548:2: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:548:2: ( (lv_name_0_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:549:1: (lv_name_0_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:549:1: (lv_name_0_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:550:3: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleParam1252); 

            			newLeafNode(lv_name_0_0, grammarAccess.getParamAccess().getNameIDTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getParamRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"ID");
            	    

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleParam1269); 

                	newLeafNode(otherlv_1, grammarAccess.getParamAccess().getColonKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:570:1: ( (lv_type_2_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:571:1: (lv_type_2_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:571:1: (lv_type_2_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:572:3: lv_type_2_0= RULE_ID
            {
            lv_type_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleParam1286); 

            			newLeafNode(lv_type_2_0, grammarAccess.getParamAccess().getTypeIDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getParamRule());
            	        }
                   		addWithLastConsumed(
                   			current, 
                   			"type",
                    		lv_type_2_0, 
                    		"ID");
            	    

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
    // $ANTLR end "ruleParam"


    // $ANTLR start "entryRuleSpace"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:596:1: entryRuleSpace returns [EObject current=null] : iv_ruleSpace= ruleSpace EOF ;
    public final EObject entryRuleSpace() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpace = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:597:2: (iv_ruleSpace= ruleSpace EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:598:2: iv_ruleSpace= ruleSpace EOF
            {
             newCompositeNode(grammarAccess.getSpaceRule()); 
            pushFollow(FOLLOW_ruleSpace_in_entryRuleSpace1327);
            iv_ruleSpace=ruleSpace();

            state._fsp--;

             current =iv_ruleSpace; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSpace1337); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSpace"


    // $ANTLR start "ruleSpace"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:605:1: ruleSpace returns [EObject current=null] : (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) ) ;
    public final EObject ruleSpace() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_val_1_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:608:28: ( (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:609:1: (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:609:1: (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:609:3: otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleSpace1374); 

                	newLeafNode(otherlv_0, grammarAccess.getSpaceAccess().getSpaceKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:613:1: ( (lv_val_1_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:614:1: (lv_val_1_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:614:1: (lv_val_1_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:615:3: lv_val_1_0= RULE_ID
            {
            lv_val_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSpace1391); 

            			newLeafNode(lv_val_1_0, grammarAccess.getSpaceAccess().getValIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSpaceRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"val",
                    		lv_val_1_0, 
                    		"ID");
            	    

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
    // $ANTLR end "ruleSpace"


    // $ANTLR start "entryRuleTestLinewrap"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:639:1: entryRuleTestLinewrap returns [EObject current=null] : iv_ruleTestLinewrap= ruleTestLinewrap EOF ;
    public final EObject entryRuleTestLinewrap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestLinewrap = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:640:2: (iv_ruleTestLinewrap= ruleTestLinewrap EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:641:2: iv_ruleTestLinewrap= ruleTestLinewrap EOF
            {
             newCompositeNode(grammarAccess.getTestLinewrapRule()); 
            pushFollow(FOLLOW_ruleTestLinewrap_in_entryRuleTestLinewrap1432);
            iv_ruleTestLinewrap=ruleTestLinewrap();

            state._fsp--;

             current =iv_ruleTestLinewrap; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTestLinewrap1442); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestLinewrap"


    // $ANTLR start "ruleTestLinewrap"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:648:1: ruleTestLinewrap returns [EObject current=null] : ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* ) ;
    public final EObject ruleTestLinewrap() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_items_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:651:28: ( ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:652:1: ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:652:1: ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:652:2: () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:652:2: ()
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:653:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTestLinewrapAccess().getTestLinewrapAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FOLLOW_24_in_ruleTestLinewrap1488); 

                	newLeafNode(otherlv_1, grammarAccess.getTestLinewrapAccess().getLinewrapKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:662:1: ( (lv_items_2_0= ruleLine ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID||LA8_0==19||LA8_0==23||LA8_0==35||(LA8_0>=37 && LA8_0<=39)||LA8_0==43) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:663:1: (lv_items_2_0= ruleLine )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:663:1: (lv_items_2_0= ruleLine )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:664:3: lv_items_2_0= ruleLine
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestLinewrapAccess().getItemsLineParserRuleCall_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLine_in_ruleTestLinewrap1509);
            	    lv_items_2_0=ruleLine();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestLinewrapRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"items",
            	            		lv_items_2_0, 
            	            		"Line");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
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
    // $ANTLR end "ruleTestLinewrap"


    // $ANTLR start "entryRuleTestLinewrapMinMax"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:688:1: entryRuleTestLinewrapMinMax returns [EObject current=null] : iv_ruleTestLinewrapMinMax= ruleTestLinewrapMinMax EOF ;
    public final EObject entryRuleTestLinewrapMinMax() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestLinewrapMinMax = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:689:2: (iv_ruleTestLinewrapMinMax= ruleTestLinewrapMinMax EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:690:2: iv_ruleTestLinewrapMinMax= ruleTestLinewrapMinMax EOF
            {
             newCompositeNode(grammarAccess.getTestLinewrapMinMaxRule()); 
            pushFollow(FOLLOW_ruleTestLinewrapMinMax_in_entryRuleTestLinewrapMinMax1546);
            iv_ruleTestLinewrapMinMax=ruleTestLinewrapMinMax();

            state._fsp--;

             current =iv_ruleTestLinewrapMinMax; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTestLinewrapMinMax1556); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestLinewrapMinMax"


    // $ANTLR start "ruleTestLinewrapMinMax"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:697:1: ruleTestLinewrapMinMax returns [EObject current=null] : ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* ) ;
    public final EObject ruleTestLinewrapMinMax() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_items_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:700:28: ( ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:701:1: ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:701:1: ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:701:2: () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:701:2: ()
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:702:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTestLinewrapMinMaxAccess().getTestLinewrapMinMaxAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,25,FOLLOW_25_in_ruleTestLinewrapMinMax1602); 

                	newLeafNode(otherlv_1, grammarAccess.getTestLinewrapMinMaxAccess().getWrapminmaxKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:711:1: ( (lv_items_2_0= ruleLine ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID||LA9_0==19||LA9_0==23||LA9_0==35||(LA9_0>=37 && LA9_0<=39)||LA9_0==43) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:712:1: (lv_items_2_0= ruleLine )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:712:1: (lv_items_2_0= ruleLine )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:713:3: lv_items_2_0= ruleLine
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestLinewrapMinMaxAccess().getItemsLineParserRuleCall_2_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLine_in_ruleTestLinewrapMinMax1623);
            	    lv_items_2_0=ruleLine();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestLinewrapMinMaxRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"items",
            	            		lv_items_2_0, 
            	            		"Line");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
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
    // $ANTLR end "ruleTestLinewrapMinMax"


    // $ANTLR start "entryRuleTestIndentation"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:737:1: entryRuleTestIndentation returns [EObject current=null] : iv_ruleTestIndentation= ruleTestIndentation EOF ;
    public final EObject entryRuleTestIndentation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestIndentation = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:738:2: (iv_ruleTestIndentation= ruleTestIndentation EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:739:2: iv_ruleTestIndentation= ruleTestIndentation EOF
            {
             newCompositeNode(grammarAccess.getTestIndentationRule()); 
            pushFollow(FOLLOW_ruleTestIndentation_in_entryRuleTestIndentation1660);
            iv_ruleTestIndentation=ruleTestIndentation();

            state._fsp--;

             current =iv_ruleTestIndentation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTestIndentation1670); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestIndentation"


    // $ANTLR start "ruleTestIndentation"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:746:1: ruleTestIndentation returns [EObject current=null] : ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? ) ;
    public final EObject ruleTestIndentation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        Token lv_semi_6_0=null;
        EObject lv_sub_3_0 = null;

        EObject lv_items_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:749:28: ( ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:750:1: ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:750:1: ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:750:2: () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )?
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:750:2: ()
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:751:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTestIndentationAccess().getTestIndentationAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,26,FOLLOW_26_in_ruleTestIndentation1716); 

                	newLeafNode(otherlv_1, grammarAccess.getTestIndentationAccess().getIndentationKeyword_1());
                
            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleTestIndentation1728); 

                	newLeafNode(otherlv_2, grammarAccess.getTestIndentationAccess().getLeftCurlyBracketKeyword_2());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:764:1: ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )*
            loop10:
            do {
                int alt10=3;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==26) ) {
                    alt10=1;
                }
                else if ( (LA10_0==RULE_ID||LA10_0==19||LA10_0==23||LA10_0==35||(LA10_0>=37 && LA10_0<=39)||LA10_0==43) ) {
                    alt10=2;
                }


                switch (alt10) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:764:2: ( (lv_sub_3_0= ruleTestIndentation ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:764:2: ( (lv_sub_3_0= ruleTestIndentation ) )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:765:1: (lv_sub_3_0= ruleTestIndentation )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:765:1: (lv_sub_3_0= ruleTestIndentation )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:766:3: lv_sub_3_0= ruleTestIndentation
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestIndentationAccess().getSubTestIndentationParserRuleCall_3_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleTestIndentation_in_ruleTestIndentation1750);
            	    lv_sub_3_0=ruleTestIndentation();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestIndentationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"sub",
            	            		lv_sub_3_0, 
            	            		"TestIndentation");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:783:6: ( (lv_items_4_0= ruleLine ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:783:6: ( (lv_items_4_0= ruleLine ) )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:784:1: (lv_items_4_0= ruleLine )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:784:1: (lv_items_4_0= ruleLine )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:785:3: lv_items_4_0= ruleLine
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestIndentationAccess().getItemsLineParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLine_in_ruleTestIndentation1777);
            	    lv_items_4_0=ruleLine();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestIndentationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"items",
            	            		lv_items_4_0, 
            	            		"Line");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            otherlv_5=(Token)match(input,28,FOLLOW_28_in_ruleTestIndentation1791); 

                	newLeafNode(otherlv_5, grammarAccess.getTestIndentationAccess().getRightCurlyBracketKeyword_4());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:805:1: ( (lv_semi_6_0= ';' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==13) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:806:1: (lv_semi_6_0= ';' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:806:1: (lv_semi_6_0= ';' )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:807:3: lv_semi_6_0= ';'
                    {
                    lv_semi_6_0=(Token)match(input,13,FOLLOW_13_in_ruleTestIndentation1809); 

                            newLeafNode(lv_semi_6_0, grammarAccess.getTestIndentationAccess().getSemiSemicolonKeyword_5_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTestIndentationRule());
                    	        }
                           		setWithLastConsumed(current, "semi", true, ";");
                    	    

                    }


                    }
                    break;

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
    // $ANTLR end "ruleTestIndentation"


    // $ANTLR start "entryRuleTestColumn"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:828:1: entryRuleTestColumn returns [EObject current=null] : iv_ruleTestColumn= ruleTestColumn EOF ;
    public final EObject entryRuleTestColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestColumn = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:829:2: (iv_ruleTestColumn= ruleTestColumn EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:830:2: iv_ruleTestColumn= ruleTestColumn EOF
            {
             newCompositeNode(grammarAccess.getTestColumnRule()); 
            pushFollow(FOLLOW_ruleTestColumn_in_entryRuleTestColumn1859);
            iv_ruleTestColumn=ruleTestColumn();

            state._fsp--;

             current =iv_ruleTestColumn; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTestColumn1869); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestColumn"


    // $ANTLR start "ruleTestColumn"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:837:1: ruleTestColumn returns [EObject current=null] : ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* ) ;
    public final EObject ruleTestColumn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        EObject lv_items_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:840:28: ( ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:841:1: ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:841:1: ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:841:2: () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:841:2: ()
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:842:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTestColumnAccess().getTestColumnAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,29,FOLLOW_29_in_ruleTestColumn1915); 

                	newLeafNode(otherlv_1, grammarAccess.getTestColumnAccess().getColumnKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:851:1: ( (lv_name_2_0= RULE_ID ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:852:1: (lv_name_2_0= RULE_ID )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:852:1: (lv_name_2_0= RULE_ID )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:853:3: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTestColumn1932); 

                    			newLeafNode(lv_name_2_0, grammarAccess.getTestColumnAccess().getNameIDTerminalRuleCall_2_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTestColumnRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_2_0, 
                            		"ID");
                    	    

                    }


                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:869:3: (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==30) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:869:5: otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) )
            	    {
            	    otherlv_3=(Token)match(input,30,FOLLOW_30_in_ruleTestColumn1951); 

            	        	newLeafNode(otherlv_3, grammarAccess.getTestColumnAccess().getItemKeyword_3_0());
            	        
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:873:1: ( (lv_items_4_0= ruleLine ) )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:874:1: (lv_items_4_0= ruleLine )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:874:1: (lv_items_4_0= ruleLine )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:875:3: lv_items_4_0= ruleLine
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getTestColumnAccess().getItemsLineParserRuleCall_3_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleLine_in_ruleTestColumn1972);
            	    lv_items_4_0=ruleLine();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getTestColumnRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"items",
            	            		lv_items_4_0, 
            	            		"Line");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
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
    // $ANTLR end "ruleTestColumn"


    // $ANTLR start "entryRuleTestOffset"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:899:1: entryRuleTestOffset returns [EObject current=null] : iv_ruleTestOffset= ruleTestOffset EOF ;
    public final EObject entryRuleTestOffset() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestOffset = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:900:2: (iv_ruleTestOffset= ruleTestOffset EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:901:2: iv_ruleTestOffset= ruleTestOffset EOF
            {
             newCompositeNode(grammarAccess.getTestOffsetRule()); 
            pushFollow(FOLLOW_ruleTestOffset_in_entryRuleTestOffset2010);
            iv_ruleTestOffset=ruleTestOffset();

            state._fsp--;

             current =iv_ruleTestOffset; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTestOffset2020); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestOffset"


    // $ANTLR start "ruleTestOffset"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:908:1: ruleTestOffset returns [EObject current=null] : ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) ) ;
    public final EObject ruleTestOffset() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_value_3_0=null;
        Token otherlv_4=null;
        Token lv_first_5_0=null;
        Token lv_second_6_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:911:28: ( ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:912:1: ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:912:1: ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:912:2: () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:912:2: ()
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:913:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getTestOffsetAccess().getTestOffsetAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,31,FOLLOW_31_in_ruleTestOffset2066); 

                	newLeafNode(otherlv_1, grammarAccess.getTestOffsetAccess().getOffsetKeyword_1());
                
            otherlv_2=(Token)match(input,32,FOLLOW_32_in_ruleTestOffset2078); 

                	newLeafNode(otherlv_2, grammarAccess.getTestOffsetAccess().getValueKeyword_2());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:926:1: ( (lv_value_3_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:927:1: (lv_value_3_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:927:1: (lv_value_3_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:928:3: lv_value_3_0= RULE_ID
            {
            lv_value_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTestOffset2095); 

            			newLeafNode(lv_value_3_0, grammarAccess.getTestOffsetAccess().getValueIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTestOffsetRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_3_0, 
                    		"ID");
            	    

            }


            }

            otherlv_4=(Token)match(input,33,FOLLOW_33_in_ruleTestOffset2112); 

                	newLeafNode(otherlv_4, grammarAccess.getTestOffsetAccess().getPairKeyword_4());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:948:1: ( (lv_first_5_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:949:1: (lv_first_5_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:949:1: (lv_first_5_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:950:3: lv_first_5_0= RULE_ID
            {
            lv_first_5_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTestOffset2129); 

            			newLeafNode(lv_first_5_0, grammarAccess.getTestOffsetAccess().getFirstIDTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTestOffsetRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"first",
                    		lv_first_5_0, 
                    		"ID");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:966:2: ( (lv_second_6_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:967:1: (lv_second_6_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:967:1: (lv_second_6_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:968:3: lv_second_6_0= RULE_ID
            {
            lv_second_6_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTestOffset2151); 

            			newLeafNode(lv_second_6_0, grammarAccess.getTestOffsetAccess().getSecondIDTerminalRuleCall_6_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTestOffsetRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"second",
                    		lv_second_6_0, 
                    		"ID");
            	    

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
    // $ANTLR end "ruleTestOffset"


    // $ANTLR start "entryRuleTestRightPadding"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:992:1: entryRuleTestRightPadding returns [EObject current=null] : iv_ruleTestRightPadding= ruleTestRightPadding EOF ;
    public final EObject entryRuleTestRightPadding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestRightPadding = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:993:2: (iv_ruleTestRightPadding= ruleTestRightPadding EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:994:2: iv_ruleTestRightPadding= ruleTestRightPadding EOF
            {
             newCompositeNode(grammarAccess.getTestRightPaddingRule()); 
            pushFollow(FOLLOW_ruleTestRightPadding_in_entryRuleTestRightPadding2192);
            iv_ruleTestRightPadding=ruleTestRightPadding();

            state._fsp--;

             current =iv_ruleTestRightPadding; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTestRightPadding2202); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTestRightPadding"


    // $ANTLR start "ruleTestRightPadding"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1001:1: ruleTestRightPadding returns [EObject current=null] : (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' ) ;
    public final EObject ruleTestRightPadding() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_p1_1_0=null;
        Token lv_p2_2_0=null;
        Token otherlv_3=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1004:28: ( (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1005:1: (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1005:1: (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1005:3: otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,34,FOLLOW_34_in_ruleTestRightPadding2239); 

                	newLeafNode(otherlv_0, grammarAccess.getTestRightPaddingAccess().getPaddingKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1009:1: ( (lv_p1_1_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1010:1: (lv_p1_1_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1010:1: (lv_p1_1_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1011:3: lv_p1_1_0= RULE_ID
            {
            lv_p1_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTestRightPadding2256); 

            			newLeafNode(lv_p1_1_0, grammarAccess.getTestRightPaddingAccess().getP1IDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTestRightPaddingRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"p1",
                    		lv_p1_1_0, 
                    		"ID");
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1027:2: ( (lv_p2_2_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1028:1: (lv_p2_2_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1028:1: (lv_p2_2_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1029:3: lv_p2_2_0= RULE_ID
            {
            lv_p2_2_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTestRightPadding2278); 

            			newLeafNode(lv_p2_2_0, grammarAccess.getTestRightPaddingAccess().getP2IDTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTestRightPaddingRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"p2",
                    		lv_p2_2_0, 
                    		"ID");
            	    

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_13_in_ruleTestRightPadding2295); 

                	newLeafNode(otherlv_3, grammarAccess.getTestRightPaddingAccess().getSemicolonKeyword_3());
                

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
    // $ANTLR end "ruleTestRightPadding"


    // $ANTLR start "entryRuleFqnObj"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1057:1: entryRuleFqnObj returns [EObject current=null] : iv_ruleFqnObj= ruleFqnObj EOF ;
    public final EObject entryRuleFqnObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFqnObj = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1058:2: (iv_ruleFqnObj= ruleFqnObj EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1059:2: iv_ruleFqnObj= ruleFqnObj EOF
            {
             newCompositeNode(grammarAccess.getFqnObjRule()); 
            pushFollow(FOLLOW_ruleFqnObj_in_entryRuleFqnObj2331);
            iv_ruleFqnObj=ruleFqnObj();

            state._fsp--;

             current =iv_ruleFqnObj; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFqnObj2341); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFqnObj"


    // $ANTLR start "ruleFqnObj"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1066:1: ruleFqnObj returns [EObject current=null] : (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) ) ;
    public final EObject ruleFqnObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1069:28: ( (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1070:1: (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1070:1: (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1070:3: otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) )
            {
            otherlv_0=(Token)match(input,35,FOLLOW_35_in_ruleFqnObj2378); 

                	newLeafNode(otherlv_0, grammarAccess.getFqnObjAccess().getFqnKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1074:1: ( (lv_name_1_0= ruleFQN ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1075:1: (lv_name_1_0= ruleFQN )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1075:1: (lv_name_1_0= ruleFQN )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1076:3: lv_name_1_0= ruleFQN
            {
             
            	        newCompositeNode(grammarAccess.getFqnObjAccess().getNameFQNParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFQN_in_ruleFqnObj2399);
            lv_name_1_0=ruleFQN();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getFqnObjRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"FQN");
            	        afterParserOrEnumRuleCall();
            	    

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
    // $ANTLR end "ruleFqnObj"


    // $ANTLR start "entryRuleFQN"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1100:1: entryRuleFQN returns [String current=null] : iv_ruleFQN= ruleFQN EOF ;
    public final String entryRuleFQN() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFQN = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1101:2: (iv_ruleFQN= ruleFQN EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1102:2: iv_ruleFQN= ruleFQN EOF
            {
             newCompositeNode(grammarAccess.getFQNRule()); 
            pushFollow(FOLLOW_ruleFQN_in_entryRuleFQN2436);
            iv_ruleFQN=ruleFQN();

            state._fsp--;

             current =iv_ruleFQN.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFQN2447); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFQN"


    // $ANTLR start "ruleFQN"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1109:1: ruleFQN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleFQN() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1112:28: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1113:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1113:1: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1113:6: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFQN2487); 

            		current.merge(this_ID_0);
                
             
                newLeafNode(this_ID_0, grammarAccess.getFQNAccess().getIDTerminalRuleCall_0()); 
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1120:1: (kw= '.' this_ID_2= RULE_ID )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==36) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1121:2: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,36,FOLLOW_36_in_ruleFQN2506); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getFQNAccess().getFullStopKeyword_1_0()); 
            	        
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFQN2521); 

            	    		current.merge(this_ID_2);
            	        
            	     
            	        newLeafNode(this_ID_2, grammarAccess.getFQNAccess().getIDTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop14;
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
    // $ANTLR end "ruleFQN"


    // $ANTLR start "entryRuleFqnRef"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1141:1: entryRuleFqnRef returns [EObject current=null] : iv_ruleFqnRef= ruleFqnRef EOF ;
    public final EObject entryRuleFqnRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFqnRef = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1142:2: (iv_ruleFqnRef= ruleFqnRef EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1143:2: iv_ruleFqnRef= ruleFqnRef EOF
            {
             newCompositeNode(grammarAccess.getFqnRefRule()); 
            pushFollow(FOLLOW_ruleFqnRef_in_entryRuleFqnRef2568);
            iv_ruleFqnRef=ruleFqnRef();

            state._fsp--;

             current =iv_ruleFqnRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFqnRef2578); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFqnRef"


    // $ANTLR start "ruleFqnRef"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1150:1: ruleFqnRef returns [EObject current=null] : (otherlv_0= 'fqnref' ( ( ruleFQN ) ) ) ;
    public final EObject ruleFqnRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1153:28: ( (otherlv_0= 'fqnref' ( ( ruleFQN ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1154:1: (otherlv_0= 'fqnref' ( ( ruleFQN ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1154:1: (otherlv_0= 'fqnref' ( ( ruleFQN ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1154:3: otherlv_0= 'fqnref' ( ( ruleFQN ) )
            {
            otherlv_0=(Token)match(input,37,FOLLOW_37_in_ruleFqnRef2615); 

                	newLeafNode(otherlv_0, grammarAccess.getFqnRefAccess().getFqnrefKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1158:1: ( ( ruleFQN ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1159:1: ( ruleFQN )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1159:1: ( ruleFQN )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1160:3: ruleFQN
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getFqnRefRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getFqnRefAccess().getRefFqnObjCrossReference_1_0()); 
            	    
            pushFollow(FOLLOW_ruleFQN_in_ruleFqnRef2638);
            ruleFQN();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

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
    // $ANTLR end "ruleFqnRef"


    // $ANTLR start "entryRuleEnumeration"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1181:1: entryRuleEnumeration returns [EObject current=null] : iv_ruleEnumeration= ruleEnumeration EOF ;
    public final EObject entryRuleEnumeration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumeration = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1182:2: (iv_ruleEnumeration= ruleEnumeration EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1183:2: iv_ruleEnumeration= ruleEnumeration EOF
            {
             newCompositeNode(grammarAccess.getEnumerationRule()); 
            pushFollow(FOLLOW_ruleEnumeration_in_entryRuleEnumeration2674);
            iv_ruleEnumeration=ruleEnumeration();

            state._fsp--;

             current =iv_ruleEnumeration; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEnumeration2684); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEnumeration"


    // $ANTLR start "ruleEnumeration"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1190:1: ruleEnumeration returns [EObject current=null] : (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* ) ;
    public final EObject ruleEnumeration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Enumerator lv_val_1_0 = null;

        Enumerator lv_val_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1193:28: ( (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1194:1: (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1194:1: (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1194:3: otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )*
            {
            otherlv_0=(Token)match(input,38,FOLLOW_38_in_ruleEnumeration2721); 

                	newLeafNode(otherlv_0, grammarAccess.getEnumerationAccess().getEnumKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1198:1: ( (lv_val_1_0= ruleEnum1 ) )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=46 && LA15_0<=48)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1199:1: (lv_val_1_0= ruleEnum1 )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1199:1: (lv_val_1_0= ruleEnum1 )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1200:3: lv_val_1_0= ruleEnum1
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getEnumerationAccess().getValEnum1EnumRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleEnum1_in_ruleEnumeration2742);
            	    lv_val_1_0=ruleEnum1();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getEnumerationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"val",
            	            		lv_val_1_0, 
            	            		"Enum1");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);

            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1216:3: (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==17) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1216:5: otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) )
            	    {
            	    otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleEnumeration2756); 

            	        	newLeafNode(otherlv_2, grammarAccess.getEnumerationAccess().getCommaKeyword_2_0());
            	        
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1220:1: ( (lv_val_3_0= ruleEnum1 ) )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1221:1: (lv_val_3_0= ruleEnum1 )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1221:1: (lv_val_3_0= ruleEnum1 )
            	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1222:3: lv_val_3_0= ruleEnum1
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getEnumerationAccess().getValEnum1EnumRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleEnum1_in_ruleEnumeration2777);
            	    lv_val_3_0=ruleEnum1();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getEnumerationRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"val",
            	            		lv_val_3_0, 
            	            		"Enum1");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
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
    // $ANTLR end "ruleEnumeration"


    // $ANTLR start "entryRuleSuppressedHidden"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1246:1: entryRuleSuppressedHidden returns [EObject current=null] : iv_ruleSuppressedHidden= ruleSuppressedHidden EOF ;
    public final EObject entryRuleSuppressedHidden() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHidden = null;


         
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        	
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1250:2: (iv_ruleSuppressedHidden= ruleSuppressedHidden EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1251:2: iv_ruleSuppressedHidden= ruleSuppressedHidden EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenRule()); 
            pushFollow(FOLLOW_ruleSuppressedHidden_in_entryRuleSuppressedHidden2821);
            iv_ruleSuppressedHidden=ruleSuppressedHidden();

            state._fsp--;

             current =iv_ruleSuppressedHidden; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSuppressedHidden2831); 

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
    // $ANTLR end "entryRuleSuppressedHidden"


    // $ANTLR start "ruleSuppressedHidden"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1261:1: ruleSuppressedHidden returns [EObject current=null] : ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' ) ;
    public final EObject ruleSuppressedHidden() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_vals_2_0 = null;

        EObject lv_vals_4_0 = null;


         enterRule(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1265:28: ( ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1266:1: ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1266:1: ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1266:2: () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`'
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1266:2: ()
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1267:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSuppressedHiddenAccess().getSuppressedHiddenAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,39,FOLLOW_39_in_ruleSuppressedHidden2881); 

                	newLeafNode(otherlv_1, grammarAccess.getSuppressedHiddenAccess().getGraveAccentKeyword_1());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1276:1: ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID||LA18_0==41) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1276:2: ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1276:2: ( (lv_vals_2_0= ruleSuppressedHiddenSub ) )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1277:1: (lv_vals_2_0= ruleSuppressedHiddenSub )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1277:1: (lv_vals_2_0= ruleSuppressedHiddenSub )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1278:3: lv_vals_2_0= ruleSuppressedHiddenSub
                    {
                     
                    	        newCompositeNode(grammarAccess.getSuppressedHiddenAccess().getValsSuppressedHiddenSubParserRuleCall_2_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSuppressedHiddenSub_in_ruleSuppressedHidden2903);
                    lv_vals_2_0=ruleSuppressedHiddenSub();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSuppressedHiddenRule());
                    	        }
                           		add(
                           			current, 
                           			"vals",
                            		lv_vals_2_0, 
                            		"SuppressedHiddenSub");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1294:2: (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==40) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1294:4: otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) )
                    	    {
                    	    otherlv_3=(Token)match(input,40,FOLLOW_40_in_ruleSuppressedHidden2916); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getSuppressedHiddenAccess().getPercentSignKeyword_2_1_0());
                    	        
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1298:1: ( (lv_vals_4_0= ruleSuppressedHiddenSub ) )
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1299:1: (lv_vals_4_0= ruleSuppressedHiddenSub )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1299:1: (lv_vals_4_0= ruleSuppressedHiddenSub )
                    	    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1300:3: lv_vals_4_0= ruleSuppressedHiddenSub
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSuppressedHiddenAccess().getValsSuppressedHiddenSubParserRuleCall_2_1_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSuppressedHiddenSub_in_ruleSuppressedHidden2937);
                    	    lv_vals_4_0=ruleSuppressedHiddenSub();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getSuppressedHiddenRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"vals",
                    	            		lv_vals_4_0, 
                    	            		"SuppressedHiddenSub");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,39,FOLLOW_39_in_ruleSuppressedHidden2953); 

                	newLeafNode(otherlv_5, grammarAccess.getSuppressedHiddenAccess().getGraveAccentKeyword_3());
                

            }


            }

             leaveRule(); 
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
    // $ANTLR end "ruleSuppressedHidden"


    // $ANTLR start "entryRuleSuppressedHiddenSub"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1331:1: entryRuleSuppressedHiddenSub returns [EObject current=null] : iv_ruleSuppressedHiddenSub= ruleSuppressedHiddenSub EOF ;
    public final EObject entryRuleSuppressedHiddenSub() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHiddenSub = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1332:2: (iv_ruleSuppressedHiddenSub= ruleSuppressedHiddenSub EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1333:2: iv_ruleSuppressedHiddenSub= ruleSuppressedHiddenSub EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenSubRule()); 
            pushFollow(FOLLOW_ruleSuppressedHiddenSub_in_entryRuleSuppressedHiddenSub2993);
            iv_ruleSuppressedHiddenSub=ruleSuppressedHiddenSub();

            state._fsp--;

             current =iv_ruleSuppressedHiddenSub; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSuppressedHiddenSub3003); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSuppressedHiddenSub"


    // $ANTLR start "ruleSuppressedHiddenSub"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1340:1: ruleSuppressedHiddenSub returns [EObject current=null] : (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID ) ;
    public final EObject ruleSuppressedHiddenSub() throws RecognitionException {
        EObject current = null;

        EObject this_SuppressedHiddenSubSub_0 = null;

        EObject this_SuppressedHiddenSubID_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1343:28: ( (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1344:1: (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1344:1: (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==41) ) {
                alt19=1;
            }
            else if ( (LA19_0==RULE_ID) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1345:5: this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub
                    {
                     
                            newCompositeNode(grammarAccess.getSuppressedHiddenSubAccess().getSuppressedHiddenSubSubParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleSuppressedHiddenSubSub_in_ruleSuppressedHiddenSub3050);
                    this_SuppressedHiddenSubSub_0=ruleSuppressedHiddenSubSub();

                    state._fsp--;

                     
                            current = this_SuppressedHiddenSubSub_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1355:5: this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID
                    {
                     
                            newCompositeNode(grammarAccess.getSuppressedHiddenSubAccess().getSuppressedHiddenSubIDParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleSuppressedHiddenSubID_in_ruleSuppressedHiddenSub3077);
                    this_SuppressedHiddenSubID_1=ruleSuppressedHiddenSubID();

                    state._fsp--;

                     
                            current = this_SuppressedHiddenSubID_1; 
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
    // $ANTLR end "ruleSuppressedHiddenSub"


    // $ANTLR start "entryRuleSuppressedHiddenSubSub"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1371:1: entryRuleSuppressedHiddenSubSub returns [EObject current=null] : iv_ruleSuppressedHiddenSubSub= ruleSuppressedHiddenSubSub EOF ;
    public final EObject entryRuleSuppressedHiddenSubSub() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHiddenSubSub = null;


         
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");
        	
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1375:2: (iv_ruleSuppressedHiddenSubSub= ruleSuppressedHiddenSubSub EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1376:2: iv_ruleSuppressedHiddenSubSub= ruleSuppressedHiddenSubSub EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenSubSubRule()); 
            pushFollow(FOLLOW_ruleSuppressedHiddenSubSub_in_entryRuleSuppressedHiddenSubSub3118);
            iv_ruleSuppressedHiddenSubSub=ruleSuppressedHiddenSubSub();

            state._fsp--;

             current =iv_ruleSuppressedHiddenSubSub; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSuppressedHiddenSubSub3128); 

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
    // $ANTLR end "entryRuleSuppressedHiddenSubSub"


    // $ANTLR start "ruleSuppressedHiddenSubSub"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1386:1: ruleSuppressedHiddenSubSub returns [EObject current=null] : (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' ) ;
    public final EObject ruleSuppressedHiddenSubSub() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_idval_1_0=null;
        Token otherlv_2=null;

         enterRule(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1390:28: ( (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1391:1: (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1391:1: (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1391:3: otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>'
            {
            otherlv_0=(Token)match(input,41,FOLLOW_41_in_ruleSuppressedHiddenSubSub3169); 

                	newLeafNode(otherlv_0, grammarAccess.getSuppressedHiddenSubSubAccess().getLessThanSignKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1395:1: ( (lv_idval_1_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1396:1: (lv_idval_1_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1396:1: (lv_idval_1_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1397:3: lv_idval_1_0= RULE_ID
            {
            lv_idval_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSuppressedHiddenSubSub3186); 

            			newLeafNode(lv_idval_1_0, grammarAccess.getSuppressedHiddenSubSubAccess().getIdvalIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSuppressedHiddenSubSubRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"idval",
                    		lv_idval_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,42,FOLLOW_42_in_ruleSuppressedHiddenSubSub3203); 

                	newLeafNode(otherlv_2, grammarAccess.getSuppressedHiddenSubSubAccess().getGreaterThanSignKeyword_2());
                

            }


            }

             leaveRule(); 
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
    // $ANTLR end "ruleSuppressedHiddenSubSub"


    // $ANTLR start "entryRuleSuppressedHiddenSubID"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1428:1: entryRuleSuppressedHiddenSubID returns [EObject current=null] : iv_ruleSuppressedHiddenSubID= ruleSuppressedHiddenSubID EOF ;
    public final EObject entryRuleSuppressedHiddenSubID() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHiddenSubID = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1429:2: (iv_ruleSuppressedHiddenSubID= ruleSuppressedHiddenSubID EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1430:2: iv_ruleSuppressedHiddenSubID= ruleSuppressedHiddenSubID EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenSubIDRule()); 
            pushFollow(FOLLOW_ruleSuppressedHiddenSubID_in_entryRuleSuppressedHiddenSubID3243);
            iv_ruleSuppressedHiddenSubID=ruleSuppressedHiddenSubID();

            state._fsp--;

             current =iv_ruleSuppressedHiddenSubID; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSuppressedHiddenSubID3253); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSuppressedHiddenSubID"


    // $ANTLR start "ruleSuppressedHiddenSubID"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1437:1: ruleSuppressedHiddenSubID returns [EObject current=null] : ( (lv_idval_0_0= RULE_ID ) ) ;
    public final EObject ruleSuppressedHiddenSubID() throws RecognitionException {
        EObject current = null;

        Token lv_idval_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1440:28: ( ( (lv_idval_0_0= RULE_ID ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1441:1: ( (lv_idval_0_0= RULE_ID ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1441:1: ( (lv_idval_0_0= RULE_ID ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1442:1: (lv_idval_0_0= RULE_ID )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1442:1: (lv_idval_0_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1443:3: lv_idval_0_0= RULE_ID
            {
            lv_idval_0_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSuppressedHiddenSubID3294); 

            			newLeafNode(lv_idval_0_0, grammarAccess.getSuppressedHiddenSubIDAccess().getIdvalIDTerminalRuleCall_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSuppressedHiddenSubIDRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"idval",
                    		lv_idval_0_0, 
                    		"ID");
            	    

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
    // $ANTLR end "ruleSuppressedHiddenSubID"


    // $ANTLR start "entryRuleDatatype1"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1467:1: entryRuleDatatype1 returns [String current=null] : iv_ruleDatatype1= ruleDatatype1 EOF ;
    public final String entryRuleDatatype1() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDatatype1 = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1468:2: (iv_ruleDatatype1= ruleDatatype1 EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1469:2: iv_ruleDatatype1= ruleDatatype1 EOF
            {
             newCompositeNode(grammarAccess.getDatatype1Rule()); 
            pushFollow(FOLLOW_ruleDatatype1_in_entryRuleDatatype13335);
            iv_ruleDatatype1=ruleDatatype1();

            state._fsp--;

             current =iv_ruleDatatype1.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatatype13346); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatype1"


    // $ANTLR start "ruleDatatype1"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1476:1: ruleDatatype1 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_FQN_0= ruleFQN ;
    public final AntlrDatatypeRuleToken ruleDatatype1() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_FQN_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1479:28: (this_FQN_0= ruleFQN )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1481:5: this_FQN_0= ruleFQN
            {
             
                    newCompositeNode(grammarAccess.getDatatype1Access().getFQNParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleFQN_in_ruleDatatype13392);
            this_FQN_0=ruleFQN();

            state._fsp--;


            		current.merge(this_FQN_0);
                
             
                    afterParserOrEnumRuleCall();
                

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
    // $ANTLR end "ruleDatatype1"


    // $ANTLR start "entryRuleDatatype2"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1499:1: entryRuleDatatype2 returns [String current=null] : iv_ruleDatatype2= ruleDatatype2 EOF ;
    public final String entryRuleDatatype2() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDatatype2 = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1500:2: (iv_ruleDatatype2= ruleDatatype2 EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1501:2: iv_ruleDatatype2= ruleDatatype2 EOF
            {
             newCompositeNode(grammarAccess.getDatatype2Rule()); 
            pushFollow(FOLLOW_ruleDatatype2_in_entryRuleDatatype23437);
            iv_ruleDatatype2=ruleDatatype2();

            state._fsp--;

             current =iv_ruleDatatype2.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatatype23448); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatype2"


    // $ANTLR start "ruleDatatype2"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1508:1: ruleDatatype2 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_FQN_0= ruleFQN ;
    public final AntlrDatatypeRuleToken ruleDatatype2() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_FQN_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1511:28: (this_FQN_0= ruleFQN )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1513:5: this_FQN_0= ruleFQN
            {
             
                    newCompositeNode(grammarAccess.getDatatype2Access().getFQNParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleFQN_in_ruleDatatype23494);
            this_FQN_0=ruleFQN();

            state._fsp--;


            		current.merge(this_FQN_0);
                
             
                    afterParserOrEnumRuleCall();
                

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
    // $ANTLR end "ruleDatatype2"


    // $ANTLR start "entryRuleDatatype3"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1531:1: entryRuleDatatype3 returns [String current=null] : iv_ruleDatatype3= ruleDatatype3 EOF ;
    public final String entryRuleDatatype3() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDatatype3 = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1532:2: (iv_ruleDatatype3= ruleDatatype3 EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1533:2: iv_ruleDatatype3= ruleDatatype3 EOF
            {
             newCompositeNode(grammarAccess.getDatatype3Rule()); 
            pushFollow(FOLLOW_ruleDatatype3_in_entryRuleDatatype33539);
            iv_ruleDatatype3=ruleDatatype3();

            state._fsp--;

             current =iv_ruleDatatype3.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatatype33550); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatype3"


    // $ANTLR start "ruleDatatype3"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1540:1: ruleDatatype3 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_FQN_0= ruleFQN ;
    public final AntlrDatatypeRuleToken ruleDatatype3() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_FQN_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1543:28: (this_FQN_0= ruleFQN )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1545:5: this_FQN_0= ruleFQN
            {
             
                    newCompositeNode(grammarAccess.getDatatype3Access().getFQNParserRuleCall()); 
                
            pushFollow(FOLLOW_ruleFQN_in_ruleDatatype33596);
            this_FQN_0=ruleFQN();

            state._fsp--;


            		current.merge(this_FQN_0);
                
             
                    afterParserOrEnumRuleCall();
                

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
    // $ANTLR end "ruleDatatype3"


    // $ANTLR start "entryRuleDatatypes"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1563:1: entryRuleDatatypes returns [EObject current=null] : iv_ruleDatatypes= ruleDatatypes EOF ;
    public final EObject entryRuleDatatypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatatypes = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1564:2: (iv_ruleDatatypes= ruleDatatypes EOF )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1565:2: iv_ruleDatatypes= ruleDatatypes EOF
            {
             newCompositeNode(grammarAccess.getDatatypesRule()); 
            pushFollow(FOLLOW_ruleDatatypes_in_entryRuleDatatypes3640);
            iv_ruleDatatypes=ruleDatatypes();

            state._fsp--;

             current =iv_ruleDatatypes; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDatatypes3650); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDatatypes"


    // $ANTLR start "ruleDatatypes"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1572:1: ruleDatatypes returns [EObject current=null] : (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' ) ;
    public final EObject ruleDatatypes() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_val1_1_0 = null;

        AntlrDatatypeRuleToken lv_val2_3_0 = null;

        AntlrDatatypeRuleToken lv_val3_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1575:28: ( (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1576:1: (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1576:1: (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1576:3: otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3'
            {
            otherlv_0=(Token)match(input,43,FOLLOW_43_in_ruleDatatypes3687); 

                	newLeafNode(otherlv_0, grammarAccess.getDatatypesAccess().getDatatypesKeyword_0());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1580:1: ( (lv_val1_1_0= ruleDatatype1 ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1581:1: (lv_val1_1_0= ruleDatatype1 )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1581:1: (lv_val1_1_0= ruleDatatype1 )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1582:3: lv_val1_1_0= ruleDatatype1
            {
             
            	        newCompositeNode(grammarAccess.getDatatypesAccess().getVal1Datatype1ParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleDatatype1_in_ruleDatatypes3708);
            lv_val1_1_0=ruleDatatype1();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDatatypesRule());
            	        }
                   		set(
                   			current, 
                   			"val1",
                    		lv_val1_1_0, 
                    		"Datatype1");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,44,FOLLOW_44_in_ruleDatatypes3720); 

                	newLeafNode(otherlv_2, grammarAccess.getDatatypesAccess().getKw1Keyword_2());
                
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1602:1: ( (lv_val2_3_0= ruleDatatype2 ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1603:1: (lv_val2_3_0= ruleDatatype2 )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1603:1: (lv_val2_3_0= ruleDatatype2 )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1604:3: lv_val2_3_0= ruleDatatype2
            {
             
            	        newCompositeNode(grammarAccess.getDatatypesAccess().getVal2Datatype2ParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleDatatype2_in_ruleDatatypes3741);
            lv_val2_3_0=ruleDatatype2();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDatatypesRule());
            	        }
                   		set(
                   			current, 
                   			"val2",
                    		lv_val2_3_0, 
                    		"Datatype2");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1620:2: ( (lv_val3_4_0= ruleDatatype3 ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1621:1: (lv_val3_4_0= ruleDatatype3 )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1621:1: (lv_val3_4_0= ruleDatatype3 )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1622:3: lv_val3_4_0= ruleDatatype3
            {
             
            	        newCompositeNode(grammarAccess.getDatatypesAccess().getVal3Datatype3ParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleDatatype3_in_ruleDatatypes3762);
            lv_val3_4_0=ruleDatatype3();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDatatypesRule());
            	        }
                   		set(
                   			current, 
                   			"val3",
                    		lv_val3_4_0, 
                    		"Datatype3");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_5=(Token)match(input,45,FOLLOW_45_in_ruleDatatypes3774); 

                	newLeafNode(otherlv_5, grammarAccess.getDatatypesAccess().getKw3Keyword_5());
                

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
    // $ANTLR end "ruleDatatypes"


    // $ANTLR start "ruleEnum1"
    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1650:1: ruleEnum1 returns [Enumerator current=null] : ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) ) ;
    public final Enumerator ruleEnum1() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1652:28: ( ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) ) )
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1653:1: ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1653:1: ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) )
            int alt20=3;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt20=1;
                }
                break;
            case 47:
                {
                alt20=2;
                }
                break;
            case 48:
                {
                alt20=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1653:2: (enumLiteral_0= 'lit1' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1653:2: (enumLiteral_0= 'lit1' )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1653:4: enumLiteral_0= 'lit1'
                    {
                    enumLiteral_0=(Token)match(input,46,FOLLOW_46_in_ruleEnum13824); 

                            current = grammarAccess.getEnum1Access().getLit1EnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getEnum1Access().getLit1EnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1659:6: (enumLiteral_1= 'lit2' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1659:6: (enumLiteral_1= 'lit2' )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1659:8: enumLiteral_1= 'lit2'
                    {
                    enumLiteral_1=(Token)match(input,47,FOLLOW_47_in_ruleEnum13841); 

                            current = grammarAccess.getEnum1Access().getLit2EnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getEnum1Access().getLit2EnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1665:6: (enumLiteral_2= 'lit3' )
                    {
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1665:6: (enumLiteral_2= 'lit3' )
                    // ../com.avaloq.tools.ddk.xtext.test/src-gen/com/avaloq/tools/ddk/xtext/formatter/parser/antlr/internal/InternalFormatterTestLanguage.g:1665:8: enumLiteral_2= 'lit3'
                    {
                    enumLiteral_2=(Token)match(input,48,FOLLOW_48_in_ruleEnum13858); 

                            current = grammarAccess.getEnum1Access().getLit3EnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getEnum1Access().getLit3EnumLiteralDeclaration_2()); 
                        

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
    // $ANTLR end "ruleEnum1"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    static final String DFA2_eotS =
        "\13\uffff";
    static final String DFA2_eofS =
        "\13\uffff";
    static final String DFA2_minS =
        "\2\4\11\uffff";
    static final String DFA2_maxS =
        "\1\53\1\17\11\uffff";
    static final String DFA2_acceptS =
        "\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\1\1\2";
    static final String DFA2_specialS =
        "\13\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\16\uffff\1\2\3\uffff\1\7\13\uffff\1\3\1\uffff\1\4\1\5"+
            "\1\6\3\uffff\1\10",
            "\1\11\11\uffff\2\12",
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

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "165:2: (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes )";
        }
    }
 

    public static final BitSet FOLLOW_ruleRoot_in_entryRuleRoot75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRoot85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleRoot122 = new BitSet(new long[]{0x00000004A7000000L});
    public static final BitSet FOLLOW_ruleTestLinewrap_in_ruleRoot145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestIndentation_in_ruleRoot172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestLinewrapMinMax_in_ruleRoot199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestColumn_in_ruleRoot226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestOffset_in_ruleRoot253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestRightPadding_in_ruleRoot280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLine_in_entryRuleLine316 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLine326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecl_in_ruleLine374 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleAssign_in_ruleLine401 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleMeth_in_ruleLine428 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleFqnObj_in_ruleLine455 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleFqnRef_in_ruleLine482 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleEnumeration_in_ruleLine509 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleSuppressedHidden_in_ruleLine537 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleLine548 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleSpace_in_ruleLine577 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_ruleDatatypes_in_ruleLine604 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLine616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDecl_in_entryRuleDecl652 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDecl662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDecl704 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDecl726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAssign_in_entryRuleAssign767 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAssign777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleAssign819 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_14_in_ruleAssign844 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_15_in_ruleAssign873 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleAssign901 = new BitSet(new long[]{0x0000000000040020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAssign919 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_17_in_ruleAssign937 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleAssign954 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_18_in_ruleAssign975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMeth_in_entryRuleMeth1011 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMeth1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleMeth1058 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleMeth1075 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleMeth1092 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_ruleParam_in_ruleMeth1114 = new BitSet(new long[]{0x0000000000220000L});
    public static final BitSet FOLLOW_17_in_ruleMeth1127 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleParam_in_ruleMeth1148 = new BitSet(new long[]{0x0000000000220000L});
    public static final BitSet FOLLOW_21_in_ruleMeth1164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParam_in_entryRuleParam1200 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParam1210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleParam1252 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleParam1269 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleParam1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSpace_in_entryRuleSpace1327 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSpace1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleSpace1374 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSpace1391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestLinewrap_in_entryRuleTestLinewrap1432 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTestLinewrap1442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleTestLinewrap1488 = new BitSet(new long[]{0x000008E800880012L});
    public static final BitSet FOLLOW_ruleLine_in_ruleTestLinewrap1509 = new BitSet(new long[]{0x000008E800880012L});
    public static final BitSet FOLLOW_ruleTestLinewrapMinMax_in_entryRuleTestLinewrapMinMax1546 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTestLinewrapMinMax1556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleTestLinewrapMinMax1602 = new BitSet(new long[]{0x000008E800880012L});
    public static final BitSet FOLLOW_ruleLine_in_ruleTestLinewrapMinMax1623 = new BitSet(new long[]{0x000008E800880012L});
    public static final BitSet FOLLOW_ruleTestIndentation_in_entryRuleTestIndentation1660 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTestIndentation1670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleTestIndentation1716 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleTestIndentation1728 = new BitSet(new long[]{0x000008E814880010L});
    public static final BitSet FOLLOW_ruleTestIndentation_in_ruleTestIndentation1750 = new BitSet(new long[]{0x000008E814880010L});
    public static final BitSet FOLLOW_ruleLine_in_ruleTestIndentation1777 = new BitSet(new long[]{0x000008E814880010L});
    public static final BitSet FOLLOW_28_in_ruleTestIndentation1791 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_ruleTestIndentation1809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestColumn_in_entryRuleTestColumn1859 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTestColumn1869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleTestColumn1915 = new BitSet(new long[]{0x0000000040000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTestColumn1932 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleTestColumn1951 = new BitSet(new long[]{0x000008E800880010L});
    public static final BitSet FOLLOW_ruleLine_in_ruleTestColumn1972 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_ruleTestOffset_in_entryRuleTestOffset2010 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTestOffset2020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleTestOffset2066 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_32_in_ruleTestOffset2078 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTestOffset2095 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_ruleTestOffset2112 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTestOffset2129 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTestOffset2151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTestRightPadding_in_entryRuleTestRightPadding2192 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTestRightPadding2202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleTestRightPadding2239 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTestRightPadding2256 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTestRightPadding2278 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleTestRightPadding2295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFqnObj_in_entryRuleFqnObj2331 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFqnObj2341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleFqnObj2378 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFQN_in_ruleFqnObj2399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFQN_in_entryRuleFQN2436 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFQN2447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFQN2487 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_ruleFQN2506 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFQN2521 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_ruleFqnRef_in_entryRuleFqnRef2568 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFqnRef2578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleFqnRef2615 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleFQN_in_ruleFqnRef2638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEnumeration_in_entryRuleEnumeration2674 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEnumeration2684 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleEnumeration2721 = new BitSet(new long[]{0x0001C00000000000L});
    public static final BitSet FOLLOW_ruleEnum1_in_ruleEnumeration2742 = new BitSet(new long[]{0x0001C00000020002L});
    public static final BitSet FOLLOW_17_in_ruleEnumeration2756 = new BitSet(new long[]{0x0001C00000000000L});
    public static final BitSet FOLLOW_ruleEnum1_in_ruleEnumeration2777 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleSuppressedHidden_in_entryRuleSuppressedHidden2821 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSuppressedHidden2831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleSuppressedHidden2881 = new BitSet(new long[]{0x0000028000000010L});
    public static final BitSet FOLLOW_ruleSuppressedHiddenSub_in_ruleSuppressedHidden2903 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_40_in_ruleSuppressedHidden2916 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_ruleSuppressedHiddenSub_in_ruleSuppressedHidden2937 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_39_in_ruleSuppressedHidden2953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuppressedHiddenSub_in_entryRuleSuppressedHiddenSub2993 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSuppressedHiddenSub3003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuppressedHiddenSubSub_in_ruleSuppressedHiddenSub3050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuppressedHiddenSubID_in_ruleSuppressedHiddenSub3077 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuppressedHiddenSubSub_in_entryRuleSuppressedHiddenSubSub3118 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSuppressedHiddenSubSub3128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_41_in_ruleSuppressedHiddenSubSub3169 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSuppressedHiddenSubSub3186 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_42_in_ruleSuppressedHiddenSubSub3203 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSuppressedHiddenSubID_in_entryRuleSuppressedHiddenSubID3243 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSuppressedHiddenSubID3253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSuppressedHiddenSubID3294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatatype1_in_entryRuleDatatype13335 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatatype13346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFQN_in_ruleDatatype13392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatatype2_in_entryRuleDatatype23437 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatatype23448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFQN_in_ruleDatatype23494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatatype3_in_entryRuleDatatype33539 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatatype33550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFQN_in_ruleDatatype33596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDatatypes_in_entryRuleDatatypes3640 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDatatypes3650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleDatatypes3687 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleDatatype1_in_ruleDatatypes3708 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_ruleDatatypes3720 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleDatatype2_in_ruleDatatypes3741 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ruleDatatype3_in_ruleDatatypes3762 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ruleDatatypes3774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_ruleEnum13824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_ruleEnum13841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_ruleEnum13858 = new BitSet(new long[]{0x0000000000000002L});

}