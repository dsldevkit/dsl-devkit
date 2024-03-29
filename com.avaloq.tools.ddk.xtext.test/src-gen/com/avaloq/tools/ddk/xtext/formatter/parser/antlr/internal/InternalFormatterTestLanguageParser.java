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
    public String getGrammarFileName() { return "InternalFormatterTestLanguage.g"; }



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
    // InternalFormatterTestLanguage.g:65:1: entryRuleRoot returns [EObject current=null] : iv_ruleRoot= ruleRoot EOF ;
    public final EObject entryRuleRoot() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRoot = null;


        try {
            // InternalFormatterTestLanguage.g:65:45: (iv_ruleRoot= ruleRoot EOF )
            // InternalFormatterTestLanguage.g:66:2: iv_ruleRoot= ruleRoot EOF
            {
             newCompositeNode(grammarAccess.getRootRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRoot=ruleRoot();

            state._fsp--;

             current =iv_ruleRoot; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:72:1: ruleRoot returns [EObject current=null] : (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) ) ;
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
            // InternalFormatterTestLanguage.g:78:2: ( (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) ) )
            // InternalFormatterTestLanguage.g:79:2: (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) )
            {
            // InternalFormatterTestLanguage.g:79:2: (otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding ) )
            // InternalFormatterTestLanguage.g:80:3: otherlv_0= 'test' (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding )
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getRootAccess().getTestKeyword_0());
            		
            // InternalFormatterTestLanguage.g:84:3: (this_TestLinewrap_1= ruleTestLinewrap | this_TestIndentation_2= ruleTestIndentation | this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax | this_TestColumn_4= ruleTestColumn | this_TestOffset_5= ruleTestOffset | this_TestRightPadding_6= ruleTestRightPadding )
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
                    // InternalFormatterTestLanguage.g:85:4: this_TestLinewrap_1= ruleTestLinewrap
                    {

                    				newCompositeNode(grammarAccess.getRootAccess().getTestLinewrapParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_2);
                    this_TestLinewrap_1=ruleTestLinewrap();

                    state._fsp--;


                    				current = this_TestLinewrap_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalFormatterTestLanguage.g:94:4: this_TestIndentation_2= ruleTestIndentation
                    {

                    				newCompositeNode(grammarAccess.getRootAccess().getTestIndentationParserRuleCall_1_1());
                    			
                    pushFollow(FOLLOW_2);
                    this_TestIndentation_2=ruleTestIndentation();

                    state._fsp--;


                    				current = this_TestIndentation_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalFormatterTestLanguage.g:103:4: this_TestLinewrapMinMax_3= ruleTestLinewrapMinMax
                    {

                    				newCompositeNode(grammarAccess.getRootAccess().getTestLinewrapMinMaxParserRuleCall_1_2());
                    			
                    pushFollow(FOLLOW_2);
                    this_TestLinewrapMinMax_3=ruleTestLinewrapMinMax();

                    state._fsp--;


                    				current = this_TestLinewrapMinMax_3;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 4 :
                    // InternalFormatterTestLanguage.g:112:4: this_TestColumn_4= ruleTestColumn
                    {

                    				newCompositeNode(grammarAccess.getRootAccess().getTestColumnParserRuleCall_1_3());
                    			
                    pushFollow(FOLLOW_2);
                    this_TestColumn_4=ruleTestColumn();

                    state._fsp--;


                    				current = this_TestColumn_4;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 5 :
                    // InternalFormatterTestLanguage.g:121:4: this_TestOffset_5= ruleTestOffset
                    {

                    				newCompositeNode(grammarAccess.getRootAccess().getTestOffsetParserRuleCall_1_4());
                    			
                    pushFollow(FOLLOW_2);
                    this_TestOffset_5=ruleTestOffset();

                    state._fsp--;


                    				current = this_TestOffset_5;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 6 :
                    // InternalFormatterTestLanguage.g:130:4: this_TestRightPadding_6= ruleTestRightPadding
                    {

                    				newCompositeNode(grammarAccess.getRootAccess().getTestRightPaddingParserRuleCall_1_5());
                    			
                    pushFollow(FOLLOW_2);
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
    // InternalFormatterTestLanguage.g:143:1: entryRuleLine returns [EObject current=null] : iv_ruleLine= ruleLine EOF ;
    public final EObject entryRuleLine() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLine = null;


        try {
            // InternalFormatterTestLanguage.g:143:45: (iv_ruleLine= ruleLine EOF )
            // InternalFormatterTestLanguage.g:144:2: iv_ruleLine= ruleLine EOF
            {
             newCompositeNode(grammarAccess.getLineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLine=ruleLine();

            state._fsp--;

             current =iv_ruleLine; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:150:1: ruleLine returns [EObject current=null] : ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' ) ;
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
            // InternalFormatterTestLanguage.g:156:2: ( ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' ) )
            // InternalFormatterTestLanguage.g:157:2: ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' )
            {
            // InternalFormatterTestLanguage.g:157:2: ( (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';' )
            // InternalFormatterTestLanguage.g:158:3: (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes ) otherlv_10= ';'
            {
            // InternalFormatterTestLanguage.g:158:3: (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes )
            int alt2=9;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // InternalFormatterTestLanguage.g:159:4: this_Decl_0= ruleDecl
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getDeclParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_4);
                    this_Decl_0=ruleDecl();

                    state._fsp--;


                    				current = this_Decl_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalFormatterTestLanguage.g:168:4: this_Assign_1= ruleAssign
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getAssignParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_4);
                    this_Assign_1=ruleAssign();

                    state._fsp--;


                    				current = this_Assign_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalFormatterTestLanguage.g:177:4: this_Meth_2= ruleMeth
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getMethParserRuleCall_0_2());
                    			
                    pushFollow(FOLLOW_4);
                    this_Meth_2=ruleMeth();

                    state._fsp--;


                    				current = this_Meth_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 4 :
                    // InternalFormatterTestLanguage.g:186:4: this_FqnObj_3= ruleFqnObj
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getFqnObjParserRuleCall_0_3());
                    			
                    pushFollow(FOLLOW_4);
                    this_FqnObj_3=ruleFqnObj();

                    state._fsp--;


                    				current = this_FqnObj_3;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 5 :
                    // InternalFormatterTestLanguage.g:195:4: this_FqnRef_4= ruleFqnRef
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getFqnRefParserRuleCall_0_4());
                    			
                    pushFollow(FOLLOW_4);
                    this_FqnRef_4=ruleFqnRef();

                    state._fsp--;


                    				current = this_FqnRef_4;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 6 :
                    // InternalFormatterTestLanguage.g:204:4: this_Enumeration_5= ruleEnumeration
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getEnumerationParserRuleCall_0_5());
                    			
                    pushFollow(FOLLOW_4);
                    this_Enumeration_5=ruleEnumeration();

                    state._fsp--;


                    				current = this_Enumeration_5;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 7 :
                    // InternalFormatterTestLanguage.g:213:4: (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' )
                    {
                    // InternalFormatterTestLanguage.g:213:4: (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' )
                    // InternalFormatterTestLanguage.g:214:5: this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post'
                    {

                    					newCompositeNode(grammarAccess.getLineAccess().getSuppressedHiddenParserRuleCall_0_6_0());
                    				
                    pushFollow(FOLLOW_5);
                    this_SuppressedHidden_6=ruleSuppressedHidden();

                    state._fsp--;


                    					current = this_SuppressedHidden_6;
                    					afterParserOrEnumRuleCall();
                    				
                    otherlv_7=(Token)match(input,12,FOLLOW_4); 

                    					newLeafNode(otherlv_7, grammarAccess.getLineAccess().getPostKeyword_0_6_1());
                    				

                    }


                    }
                    break;
                case 8 :
                    // InternalFormatterTestLanguage.g:228:4: this_Space_8= ruleSpace
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getSpaceParserRuleCall_0_7());
                    			
                    pushFollow(FOLLOW_4);
                    this_Space_8=ruleSpace();

                    state._fsp--;


                    				current = this_Space_8;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 9 :
                    // InternalFormatterTestLanguage.g:237:4: this_Datatypes_9= ruleDatatypes
                    {

                    				newCompositeNode(grammarAccess.getLineAccess().getDatatypesParserRuleCall_0_8());
                    			
                    pushFollow(FOLLOW_4);
                    this_Datatypes_9=ruleDatatypes();

                    state._fsp--;


                    				current = this_Datatypes_9;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            otherlv_10=(Token)match(input,13,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:254:1: entryRuleDecl returns [EObject current=null] : iv_ruleDecl= ruleDecl EOF ;
    public final EObject entryRuleDecl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDecl = null;


        try {
            // InternalFormatterTestLanguage.g:254:45: (iv_ruleDecl= ruleDecl EOF )
            // InternalFormatterTestLanguage.g:255:2: iv_ruleDecl= ruleDecl EOF
            {
             newCompositeNode(grammarAccess.getDeclRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDecl=ruleDecl();

            state._fsp--;

             current =iv_ruleDecl; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:261:1: ruleDecl returns [EObject current=null] : ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleDecl() throws RecognitionException {
        EObject current = null;

        Token lv_type_0_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:267:2: ( ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) ) )
            // InternalFormatterTestLanguage.g:268:2: ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) )
            {
            // InternalFormatterTestLanguage.g:268:2: ( ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) ) )
            // InternalFormatterTestLanguage.g:269:3: ( (lv_type_0_0= RULE_ID ) ) ( (lv_name_1_0= RULE_ID ) )
            {
            // InternalFormatterTestLanguage.g:269:3: ( (lv_type_0_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:270:4: (lv_type_0_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:270:4: (lv_type_0_0= RULE_ID )
            // InternalFormatterTestLanguage.g:271:5: lv_type_0_0= RULE_ID
            {
            lv_type_0_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_type_0_0, grammarAccess.getDeclAccess().getTypeIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDeclRule());
            					}
            					addWithLastConsumed(
            						current,
            						"type",
            						lv_type_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFormatterTestLanguage.g:287:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:288:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:288:4: (lv_name_1_0= RULE_ID )
            // InternalFormatterTestLanguage.g:289:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getDeclAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDeclRule());
            					}
            					addWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

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
    // InternalFormatterTestLanguage.g:309:1: entryRuleAssign returns [EObject current=null] : iv_ruleAssign= ruleAssign EOF ;
    public final EObject entryRuleAssign() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssign = null;


        try {
            // InternalFormatterTestLanguage.g:309:47: (iv_ruleAssign= ruleAssign EOF )
            // InternalFormatterTestLanguage.g:310:2: iv_ruleAssign= ruleAssign EOF
            {
             newCompositeNode(grammarAccess.getAssignRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssign=ruleAssign();

            state._fsp--;

             current =iv_ruleAssign; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:316:1: ruleAssign returns [EObject current=null] : ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' ) ;
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
            // InternalFormatterTestLanguage.g:322:2: ( ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' ) )
            // InternalFormatterTestLanguage.g:323:2: ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' )
            {
            // InternalFormatterTestLanguage.g:323:2: ( ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']' )
            // InternalFormatterTestLanguage.g:324:3: ( (lv_var_0_0= RULE_ID ) ) ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) ) otherlv_2= '[' ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )? otherlv_6= ']'
            {
            // InternalFormatterTestLanguage.g:324:3: ( (lv_var_0_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:325:4: (lv_var_0_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:325:4: (lv_var_0_0= RULE_ID )
            // InternalFormatterTestLanguage.g:326:5: lv_var_0_0= RULE_ID
            {
            lv_var_0_0=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(lv_var_0_0, grammarAccess.getAssignAccess().getVarIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAssignRule());
            					}
            					setWithLastConsumed(
            						current,
            						"var",
            						lv_var_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFormatterTestLanguage.g:342:3: ( ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) ) )
            // InternalFormatterTestLanguage.g:343:4: ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) )
            {
            // InternalFormatterTestLanguage.g:343:4: ( (lv_op_1_1= '=' | lv_op_1_2= '+=' ) )
            // InternalFormatterTestLanguage.g:344:5: (lv_op_1_1= '=' | lv_op_1_2= '+=' )
            {
            // InternalFormatterTestLanguage.g:344:5: (lv_op_1_1= '=' | lv_op_1_2= '+=' )
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
                    // InternalFormatterTestLanguage.g:345:6: lv_op_1_1= '='
                    {
                    lv_op_1_1=(Token)match(input,14,FOLLOW_8); 

                    						newLeafNode(lv_op_1_1, grammarAccess.getAssignAccess().getOpEqualsSignKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssignRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalFormatterTestLanguage.g:356:6: lv_op_1_2= '+='
                    {
                    lv_op_1_2=(Token)match(input,15,FOLLOW_8); 

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

            otherlv_2=(Token)match(input,16,FOLLOW_9); 

            			newLeafNode(otherlv_2, grammarAccess.getAssignAccess().getLeftSquareBracketKeyword_2());
            		
            // InternalFormatterTestLanguage.g:373:3: ( ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_INT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalFormatterTestLanguage.g:374:4: ( (lv_val_3_0= RULE_INT ) ) (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )*
                    {
                    // InternalFormatterTestLanguage.g:374:4: ( (lv_val_3_0= RULE_INT ) )
                    // InternalFormatterTestLanguage.g:375:5: (lv_val_3_0= RULE_INT )
                    {
                    // InternalFormatterTestLanguage.g:375:5: (lv_val_3_0= RULE_INT )
                    // InternalFormatterTestLanguage.g:376:6: lv_val_3_0= RULE_INT
                    {
                    lv_val_3_0=(Token)match(input,RULE_INT,FOLLOW_10); 

                    						newLeafNode(lv_val_3_0, grammarAccess.getAssignAccess().getValINTTerminalRuleCall_3_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssignRule());
                    						}
                    						addWithLastConsumed(
                    							current,
                    							"val",
                    							lv_val_3_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    // InternalFormatterTestLanguage.g:392:4: (otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==17) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // InternalFormatterTestLanguage.g:393:5: otherlv_4= ',' ( (lv_val_5_0= RULE_INT ) )
                    	    {
                    	    otherlv_4=(Token)match(input,17,FOLLOW_11); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getAssignAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalFormatterTestLanguage.g:397:5: ( (lv_val_5_0= RULE_INT ) )
                    	    // InternalFormatterTestLanguage.g:398:6: (lv_val_5_0= RULE_INT )
                    	    {
                    	    // InternalFormatterTestLanguage.g:398:6: (lv_val_5_0= RULE_INT )
                    	    // InternalFormatterTestLanguage.g:399:7: lv_val_5_0= RULE_INT
                    	    {
                    	    lv_val_5_0=(Token)match(input,RULE_INT,FOLLOW_10); 

                    	    							newLeafNode(lv_val_5_0, grammarAccess.getAssignAccess().getValINTTerminalRuleCall_3_1_1_0());
                    	    						

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getAssignRule());
                    	    							}
                    	    							addWithLastConsumed(
                    	    								current,
                    	    								"val",
                    	    								lv_val_5_0,
                    	    								"org.eclipse.xtext.common.Terminals.INT");
                    	    						

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

            otherlv_6=(Token)match(input,18,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:425:1: entryRuleMeth returns [EObject current=null] : iv_ruleMeth= ruleMeth EOF ;
    public final EObject entryRuleMeth() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMeth = null;


        try {
            // InternalFormatterTestLanguage.g:425:45: (iv_ruleMeth= ruleMeth EOF )
            // InternalFormatterTestLanguage.g:426:2: iv_ruleMeth= ruleMeth EOF
            {
             newCompositeNode(grammarAccess.getMethRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMeth=ruleMeth();

            state._fsp--;

             current =iv_ruleMeth; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:432:1: ruleMeth returns [EObject current=null] : (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' ) ;
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
            // InternalFormatterTestLanguage.g:438:2: ( (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' ) )
            // InternalFormatterTestLanguage.g:439:2: (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' )
            {
            // InternalFormatterTestLanguage.g:439:2: (otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')' )
            // InternalFormatterTestLanguage.g:440:3: otherlv_0= 'void' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )? otherlv_6= ')'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getMethAccess().getVoidKeyword_0());
            		
            // InternalFormatterTestLanguage.g:444:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:445:4: (lv_name_1_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:445:4: (lv_name_1_0= RULE_ID )
            // InternalFormatterTestLanguage.g:446:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_12); 

            					newLeafNode(lv_name_1_0, grammarAccess.getMethAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMethRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,20,FOLLOW_13); 

            			newLeafNode(otherlv_2, grammarAccess.getMethAccess().getLeftParenthesisKeyword_2());
            		
            // InternalFormatterTestLanguage.g:466:3: ( ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ID) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalFormatterTestLanguage.g:467:4: ( (lv_param_3_0= ruleParam ) ) (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )*
                    {
                    // InternalFormatterTestLanguage.g:467:4: ( (lv_param_3_0= ruleParam ) )
                    // InternalFormatterTestLanguage.g:468:5: (lv_param_3_0= ruleParam )
                    {
                    // InternalFormatterTestLanguage.g:468:5: (lv_param_3_0= ruleParam )
                    // InternalFormatterTestLanguage.g:469:6: lv_param_3_0= ruleParam
                    {

                    						newCompositeNode(grammarAccess.getMethAccess().getParamParamParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_param_3_0=ruleParam();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMethRule());
                    						}
                    						add(
                    							current,
                    							"param",
                    							lv_param_3_0,
                    							"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Param");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalFormatterTestLanguage.g:486:4: (otherlv_4= ',' ( (lv_param_5_0= ruleParam ) ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==17) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalFormatterTestLanguage.g:487:5: otherlv_4= ',' ( (lv_param_5_0= ruleParam ) )
                    	    {
                    	    otherlv_4=(Token)match(input,17,FOLLOW_6); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getMethAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalFormatterTestLanguage.g:491:5: ( (lv_param_5_0= ruleParam ) )
                    	    // InternalFormatterTestLanguage.g:492:6: (lv_param_5_0= ruleParam )
                    	    {
                    	    // InternalFormatterTestLanguage.g:492:6: (lv_param_5_0= ruleParam )
                    	    // InternalFormatterTestLanguage.g:493:7: lv_param_5_0= ruleParam
                    	    {

                    	    							newCompositeNode(grammarAccess.getMethAccess().getParamParamParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_14);
                    	    lv_param_5_0=ruleParam();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getMethRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"param",
                    	    								lv_param_5_0,
                    	    								"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Param");
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

            otherlv_6=(Token)match(input,21,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:520:1: entryRuleParam returns [EObject current=null] : iv_ruleParam= ruleParam EOF ;
    public final EObject entryRuleParam() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParam = null;


        try {
            // InternalFormatterTestLanguage.g:520:46: (iv_ruleParam= ruleParam EOF )
            // InternalFormatterTestLanguage.g:521:2: iv_ruleParam= ruleParam EOF
            {
             newCompositeNode(grammarAccess.getParamRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParam=ruleParam();

            state._fsp--;

             current =iv_ruleParam; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:527:1: ruleParam returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ) ;
    public final EObject ruleParam() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_type_2_0=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:533:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) ) )
            // InternalFormatterTestLanguage.g:534:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) )
            {
            // InternalFormatterTestLanguage.g:534:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) ) )
            // InternalFormatterTestLanguage.g:535:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= ':' ( (lv_type_2_0= RULE_ID ) )
            {
            // InternalFormatterTestLanguage.g:535:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:536:4: (lv_name_0_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:536:4: (lv_name_0_0= RULE_ID )
            // InternalFormatterTestLanguage.g:537:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_15); 

            					newLeafNode(lv_name_0_0, grammarAccess.getParamAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParamRule());
            					}
            					addWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,22,FOLLOW_6); 

            			newLeafNode(otherlv_1, grammarAccess.getParamAccess().getColonKeyword_1());
            		
            // InternalFormatterTestLanguage.g:557:3: ( (lv_type_2_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:558:4: (lv_type_2_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:558:4: (lv_type_2_0= RULE_ID )
            // InternalFormatterTestLanguage.g:559:5: lv_type_2_0= RULE_ID
            {
            lv_type_2_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_type_2_0, grammarAccess.getParamAccess().getTypeIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParamRule());
            					}
            					addWithLastConsumed(
            						current,
            						"type",
            						lv_type_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

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
    // InternalFormatterTestLanguage.g:579:1: entryRuleSpace returns [EObject current=null] : iv_ruleSpace= ruleSpace EOF ;
    public final EObject entryRuleSpace() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpace = null;


        try {
            // InternalFormatterTestLanguage.g:579:46: (iv_ruleSpace= ruleSpace EOF )
            // InternalFormatterTestLanguage.g:580:2: iv_ruleSpace= ruleSpace EOF
            {
             newCompositeNode(grammarAccess.getSpaceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSpace=ruleSpace();

            state._fsp--;

             current =iv_ruleSpace; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:586:1: ruleSpace returns [EObject current=null] : (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) ) ;
    public final EObject ruleSpace() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_val_1_0=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:592:2: ( (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) ) )
            // InternalFormatterTestLanguage.g:593:2: (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) )
            {
            // InternalFormatterTestLanguage.g:593:2: (otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) ) )
            // InternalFormatterTestLanguage.g:594:3: otherlv_0= 'space' ( (lv_val_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getSpaceAccess().getSpaceKeyword_0());
            		
            // InternalFormatterTestLanguage.g:598:3: ( (lv_val_1_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:599:4: (lv_val_1_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:599:4: (lv_val_1_0= RULE_ID )
            // InternalFormatterTestLanguage.g:600:5: lv_val_1_0= RULE_ID
            {
            lv_val_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_val_1_0, grammarAccess.getSpaceAccess().getValIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSpaceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"val",
            						lv_val_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

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
    // InternalFormatterTestLanguage.g:620:1: entryRuleTestLinewrap returns [EObject current=null] : iv_ruleTestLinewrap= ruleTestLinewrap EOF ;
    public final EObject entryRuleTestLinewrap() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestLinewrap = null;


        try {
            // InternalFormatterTestLanguage.g:620:53: (iv_ruleTestLinewrap= ruleTestLinewrap EOF )
            // InternalFormatterTestLanguage.g:621:2: iv_ruleTestLinewrap= ruleTestLinewrap EOF
            {
             newCompositeNode(grammarAccess.getTestLinewrapRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestLinewrap=ruleTestLinewrap();

            state._fsp--;

             current =iv_ruleTestLinewrap; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:627:1: ruleTestLinewrap returns [EObject current=null] : ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* ) ;
    public final EObject ruleTestLinewrap() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_items_2_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:633:2: ( ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* ) )
            // InternalFormatterTestLanguage.g:634:2: ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* )
            {
            // InternalFormatterTestLanguage.g:634:2: ( () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )* )
            // InternalFormatterTestLanguage.g:635:3: () otherlv_1= 'linewrap' ( (lv_items_2_0= ruleLine ) )*
            {
            // InternalFormatterTestLanguage.g:635:3: ()
            // InternalFormatterTestLanguage.g:636:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTestLinewrapAccess().getTestLinewrapAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,24,FOLLOW_16); 

            			newLeafNode(otherlv_1, grammarAccess.getTestLinewrapAccess().getLinewrapKeyword_1());
            		
            // InternalFormatterTestLanguage.g:646:3: ( (lv_items_2_0= ruleLine ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==RULE_ID||LA8_0==19||LA8_0==23||LA8_0==35||(LA8_0>=37 && LA8_0<=39)||LA8_0==43) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalFormatterTestLanguage.g:647:4: (lv_items_2_0= ruleLine )
            	    {
            	    // InternalFormatterTestLanguage.g:647:4: (lv_items_2_0= ruleLine )
            	    // InternalFormatterTestLanguage.g:648:5: lv_items_2_0= ruleLine
            	    {

            	    					newCompositeNode(grammarAccess.getTestLinewrapAccess().getItemsLineParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_16);
            	    lv_items_2_0=ruleLine();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTestLinewrapRule());
            	    					}
            	    					add(
            	    						current,
            	    						"items",
            	    						lv_items_2_0,
            	    						"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Line");
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
    // InternalFormatterTestLanguage.g:669:1: entryRuleTestLinewrapMinMax returns [EObject current=null] : iv_ruleTestLinewrapMinMax= ruleTestLinewrapMinMax EOF ;
    public final EObject entryRuleTestLinewrapMinMax() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestLinewrapMinMax = null;


        try {
            // InternalFormatterTestLanguage.g:669:59: (iv_ruleTestLinewrapMinMax= ruleTestLinewrapMinMax EOF )
            // InternalFormatterTestLanguage.g:670:2: iv_ruleTestLinewrapMinMax= ruleTestLinewrapMinMax EOF
            {
             newCompositeNode(grammarAccess.getTestLinewrapMinMaxRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestLinewrapMinMax=ruleTestLinewrapMinMax();

            state._fsp--;

             current =iv_ruleTestLinewrapMinMax; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:676:1: ruleTestLinewrapMinMax returns [EObject current=null] : ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* ) ;
    public final EObject ruleTestLinewrapMinMax() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_items_2_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:682:2: ( ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* ) )
            // InternalFormatterTestLanguage.g:683:2: ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* )
            {
            // InternalFormatterTestLanguage.g:683:2: ( () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )* )
            // InternalFormatterTestLanguage.g:684:3: () otherlv_1= 'wrapminmax' ( (lv_items_2_0= ruleLine ) )*
            {
            // InternalFormatterTestLanguage.g:684:3: ()
            // InternalFormatterTestLanguage.g:685:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTestLinewrapMinMaxAccess().getTestLinewrapMinMaxAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,25,FOLLOW_16); 

            			newLeafNode(otherlv_1, grammarAccess.getTestLinewrapMinMaxAccess().getWrapminmaxKeyword_1());
            		
            // InternalFormatterTestLanguage.g:695:3: ( (lv_items_2_0= ruleLine ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID||LA9_0==19||LA9_0==23||LA9_0==35||(LA9_0>=37 && LA9_0<=39)||LA9_0==43) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalFormatterTestLanguage.g:696:4: (lv_items_2_0= ruleLine )
            	    {
            	    // InternalFormatterTestLanguage.g:696:4: (lv_items_2_0= ruleLine )
            	    // InternalFormatterTestLanguage.g:697:5: lv_items_2_0= ruleLine
            	    {

            	    					newCompositeNode(grammarAccess.getTestLinewrapMinMaxAccess().getItemsLineParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_16);
            	    lv_items_2_0=ruleLine();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTestLinewrapMinMaxRule());
            	    					}
            	    					add(
            	    						current,
            	    						"items",
            	    						lv_items_2_0,
            	    						"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Line");
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
    // InternalFormatterTestLanguage.g:718:1: entryRuleTestIndentation returns [EObject current=null] : iv_ruleTestIndentation= ruleTestIndentation EOF ;
    public final EObject entryRuleTestIndentation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestIndentation = null;


        try {
            // InternalFormatterTestLanguage.g:718:56: (iv_ruleTestIndentation= ruleTestIndentation EOF )
            // InternalFormatterTestLanguage.g:719:2: iv_ruleTestIndentation= ruleTestIndentation EOF
            {
             newCompositeNode(grammarAccess.getTestIndentationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestIndentation=ruleTestIndentation();

            state._fsp--;

             current =iv_ruleTestIndentation; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:725:1: ruleTestIndentation returns [EObject current=null] : ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? ) ;
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
            // InternalFormatterTestLanguage.g:731:2: ( ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? ) )
            // InternalFormatterTestLanguage.g:732:2: ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? )
            {
            // InternalFormatterTestLanguage.g:732:2: ( () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )? )
            // InternalFormatterTestLanguage.g:733:3: () otherlv_1= 'indentation' otherlv_2= '{' ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )* otherlv_5= '}' ( (lv_semi_6_0= ';' ) )?
            {
            // InternalFormatterTestLanguage.g:733:3: ()
            // InternalFormatterTestLanguage.g:734:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTestIndentationAccess().getTestIndentationAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,26,FOLLOW_17); 

            			newLeafNode(otherlv_1, grammarAccess.getTestIndentationAccess().getIndentationKeyword_1());
            		
            otherlv_2=(Token)match(input,27,FOLLOW_18); 

            			newLeafNode(otherlv_2, grammarAccess.getTestIndentationAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalFormatterTestLanguage.g:748:3: ( ( (lv_sub_3_0= ruleTestIndentation ) ) | ( (lv_items_4_0= ruleLine ) ) )*
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
            	    // InternalFormatterTestLanguage.g:749:4: ( (lv_sub_3_0= ruleTestIndentation ) )
            	    {
            	    // InternalFormatterTestLanguage.g:749:4: ( (lv_sub_3_0= ruleTestIndentation ) )
            	    // InternalFormatterTestLanguage.g:750:5: (lv_sub_3_0= ruleTestIndentation )
            	    {
            	    // InternalFormatterTestLanguage.g:750:5: (lv_sub_3_0= ruleTestIndentation )
            	    // InternalFormatterTestLanguage.g:751:6: lv_sub_3_0= ruleTestIndentation
            	    {

            	    						newCompositeNode(grammarAccess.getTestIndentationAccess().getSubTestIndentationParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_18);
            	    lv_sub_3_0=ruleTestIndentation();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getTestIndentationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"sub",
            	    							lv_sub_3_0,
            	    							"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.TestIndentation");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalFormatterTestLanguage.g:769:4: ( (lv_items_4_0= ruleLine ) )
            	    {
            	    // InternalFormatterTestLanguage.g:769:4: ( (lv_items_4_0= ruleLine ) )
            	    // InternalFormatterTestLanguage.g:770:5: (lv_items_4_0= ruleLine )
            	    {
            	    // InternalFormatterTestLanguage.g:770:5: (lv_items_4_0= ruleLine )
            	    // InternalFormatterTestLanguage.g:771:6: lv_items_4_0= ruleLine
            	    {

            	    						newCompositeNode(grammarAccess.getTestIndentationAccess().getItemsLineParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_18);
            	    lv_items_4_0=ruleLine();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getTestIndentationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"items",
            	    							lv_items_4_0,
            	    							"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Line");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            otherlv_5=(Token)match(input,28,FOLLOW_19); 

            			newLeafNode(otherlv_5, grammarAccess.getTestIndentationAccess().getRightCurlyBracketKeyword_4());
            		
            // InternalFormatterTestLanguage.g:793:3: ( (lv_semi_6_0= ';' ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==13) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalFormatterTestLanguage.g:794:4: (lv_semi_6_0= ';' )
                    {
                    // InternalFormatterTestLanguage.g:794:4: (lv_semi_6_0= ';' )
                    // InternalFormatterTestLanguage.g:795:5: lv_semi_6_0= ';'
                    {
                    lv_semi_6_0=(Token)match(input,13,FOLLOW_2); 

                    					newLeafNode(lv_semi_6_0, grammarAccess.getTestIndentationAccess().getSemiSemicolonKeyword_5_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getTestIndentationRule());
                    					}
                    					setWithLastConsumed(current, "semi", lv_semi_6_0 != null, ";");
                    				

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
    // InternalFormatterTestLanguage.g:811:1: entryRuleTestColumn returns [EObject current=null] : iv_ruleTestColumn= ruleTestColumn EOF ;
    public final EObject entryRuleTestColumn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestColumn = null;


        try {
            // InternalFormatterTestLanguage.g:811:51: (iv_ruleTestColumn= ruleTestColumn EOF )
            // InternalFormatterTestLanguage.g:812:2: iv_ruleTestColumn= ruleTestColumn EOF
            {
             newCompositeNode(grammarAccess.getTestColumnRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestColumn=ruleTestColumn();

            state._fsp--;

             current =iv_ruleTestColumn; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:818:1: ruleTestColumn returns [EObject current=null] : ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* ) ;
    public final EObject ruleTestColumn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        EObject lv_items_4_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:824:2: ( ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* ) )
            // InternalFormatterTestLanguage.g:825:2: ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* )
            {
            // InternalFormatterTestLanguage.g:825:2: ( () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )* )
            // InternalFormatterTestLanguage.g:826:3: () otherlv_1= 'column' ( (lv_name_2_0= RULE_ID ) )? (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )*
            {
            // InternalFormatterTestLanguage.g:826:3: ()
            // InternalFormatterTestLanguage.g:827:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTestColumnAccess().getTestColumnAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,29,FOLLOW_20); 

            			newLeafNode(otherlv_1, grammarAccess.getTestColumnAccess().getColumnKeyword_1());
            		
            // InternalFormatterTestLanguage.g:837:3: ( (lv_name_2_0= RULE_ID ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalFormatterTestLanguage.g:838:4: (lv_name_2_0= RULE_ID )
                    {
                    // InternalFormatterTestLanguage.g:838:4: (lv_name_2_0= RULE_ID )
                    // InternalFormatterTestLanguage.g:839:5: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_21); 

                    					newLeafNode(lv_name_2_0, grammarAccess.getTestColumnAccess().getNameIDTerminalRuleCall_2_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getTestColumnRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"name",
                    						lv_name_2_0,
                    						"org.eclipse.xtext.common.Terminals.ID");
                    				

                    }


                    }
                    break;

            }

            // InternalFormatterTestLanguage.g:855:3: (otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==30) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalFormatterTestLanguage.g:856:4: otherlv_3= 'item' ( (lv_items_4_0= ruleLine ) )
            	    {
            	    otherlv_3=(Token)match(input,30,FOLLOW_22); 

            	    				newLeafNode(otherlv_3, grammarAccess.getTestColumnAccess().getItemKeyword_3_0());
            	    			
            	    // InternalFormatterTestLanguage.g:860:4: ( (lv_items_4_0= ruleLine ) )
            	    // InternalFormatterTestLanguage.g:861:5: (lv_items_4_0= ruleLine )
            	    {
            	    // InternalFormatterTestLanguage.g:861:5: (lv_items_4_0= ruleLine )
            	    // InternalFormatterTestLanguage.g:862:6: lv_items_4_0= ruleLine
            	    {

            	    						newCompositeNode(grammarAccess.getTestColumnAccess().getItemsLineParserRuleCall_3_1_0());
            	    					
            	    pushFollow(FOLLOW_21);
            	    lv_items_4_0=ruleLine();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getTestColumnRule());
            	    						}
            	    						add(
            	    							current,
            	    							"items",
            	    							lv_items_4_0,
            	    							"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Line");
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
    // InternalFormatterTestLanguage.g:884:1: entryRuleTestOffset returns [EObject current=null] : iv_ruleTestOffset= ruleTestOffset EOF ;
    public final EObject entryRuleTestOffset() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestOffset = null;


        try {
            // InternalFormatterTestLanguage.g:884:51: (iv_ruleTestOffset= ruleTestOffset EOF )
            // InternalFormatterTestLanguage.g:885:2: iv_ruleTestOffset= ruleTestOffset EOF
            {
             newCompositeNode(grammarAccess.getTestOffsetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestOffset=ruleTestOffset();

            state._fsp--;

             current =iv_ruleTestOffset; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:891:1: ruleTestOffset returns [EObject current=null] : ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) ) ;
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
            // InternalFormatterTestLanguage.g:897:2: ( ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) ) )
            // InternalFormatterTestLanguage.g:898:2: ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) )
            {
            // InternalFormatterTestLanguage.g:898:2: ( () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) ) )
            // InternalFormatterTestLanguage.g:899:3: () otherlv_1= 'offset' otherlv_2= 'value' ( (lv_value_3_0= RULE_ID ) ) otherlv_4= 'pair' ( (lv_first_5_0= RULE_ID ) ) ( (lv_second_6_0= RULE_ID ) )
            {
            // InternalFormatterTestLanguage.g:899:3: ()
            // InternalFormatterTestLanguage.g:900:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTestOffsetAccess().getTestOffsetAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,31,FOLLOW_23); 

            			newLeafNode(otherlv_1, grammarAccess.getTestOffsetAccess().getOffsetKeyword_1());
            		
            otherlv_2=(Token)match(input,32,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getTestOffsetAccess().getValueKeyword_2());
            		
            // InternalFormatterTestLanguage.g:914:3: ( (lv_value_3_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:915:4: (lv_value_3_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:915:4: (lv_value_3_0= RULE_ID )
            // InternalFormatterTestLanguage.g:916:5: lv_value_3_0= RULE_ID
            {
            lv_value_3_0=(Token)match(input,RULE_ID,FOLLOW_24); 

            					newLeafNode(lv_value_3_0, grammarAccess.getTestOffsetAccess().getValueIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTestOffsetRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_3_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_4=(Token)match(input,33,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getTestOffsetAccess().getPairKeyword_4());
            		
            // InternalFormatterTestLanguage.g:936:3: ( (lv_first_5_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:937:4: (lv_first_5_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:937:4: (lv_first_5_0= RULE_ID )
            // InternalFormatterTestLanguage.g:938:5: lv_first_5_0= RULE_ID
            {
            lv_first_5_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_first_5_0, grammarAccess.getTestOffsetAccess().getFirstIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTestOffsetRule());
            					}
            					setWithLastConsumed(
            						current,
            						"first",
            						lv_first_5_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFormatterTestLanguage.g:954:3: ( (lv_second_6_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:955:4: (lv_second_6_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:955:4: (lv_second_6_0= RULE_ID )
            // InternalFormatterTestLanguage.g:956:5: lv_second_6_0= RULE_ID
            {
            lv_second_6_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_second_6_0, grammarAccess.getTestOffsetAccess().getSecondIDTerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTestOffsetRule());
            					}
            					setWithLastConsumed(
            						current,
            						"second",
            						lv_second_6_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

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
    // InternalFormatterTestLanguage.g:976:1: entryRuleTestRightPadding returns [EObject current=null] : iv_ruleTestRightPadding= ruleTestRightPadding EOF ;
    public final EObject entryRuleTestRightPadding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTestRightPadding = null;


        try {
            // InternalFormatterTestLanguage.g:976:57: (iv_ruleTestRightPadding= ruleTestRightPadding EOF )
            // InternalFormatterTestLanguage.g:977:2: iv_ruleTestRightPadding= ruleTestRightPadding EOF
            {
             newCompositeNode(grammarAccess.getTestRightPaddingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTestRightPadding=ruleTestRightPadding();

            state._fsp--;

             current =iv_ruleTestRightPadding; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:983:1: ruleTestRightPadding returns [EObject current=null] : (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' ) ;
    public final EObject ruleTestRightPadding() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_p1_1_0=null;
        Token lv_p2_2_0=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:989:2: ( (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' ) )
            // InternalFormatterTestLanguage.g:990:2: (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' )
            {
            // InternalFormatterTestLanguage.g:990:2: (otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';' )
            // InternalFormatterTestLanguage.g:991:3: otherlv_0= 'padding' ( (lv_p1_1_0= RULE_ID ) ) ( (lv_p2_2_0= RULE_ID ) ) otherlv_3= ';'
            {
            otherlv_0=(Token)match(input,34,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getTestRightPaddingAccess().getPaddingKeyword_0());
            		
            // InternalFormatterTestLanguage.g:995:3: ( (lv_p1_1_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:996:4: (lv_p1_1_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:996:4: (lv_p1_1_0= RULE_ID )
            // InternalFormatterTestLanguage.g:997:5: lv_p1_1_0= RULE_ID
            {
            lv_p1_1_0=(Token)match(input,RULE_ID,FOLLOW_6); 

            					newLeafNode(lv_p1_1_0, grammarAccess.getTestRightPaddingAccess().getP1IDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTestRightPaddingRule());
            					}
            					setWithLastConsumed(
            						current,
            						"p1",
            						lv_p1_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalFormatterTestLanguage.g:1013:3: ( (lv_p2_2_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:1014:4: (lv_p2_2_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:1014:4: (lv_p2_2_0= RULE_ID )
            // InternalFormatterTestLanguage.g:1015:5: lv_p2_2_0= RULE_ID
            {
            lv_p2_2_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_p2_2_0, grammarAccess.getTestRightPaddingAccess().getP2IDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTestRightPaddingRule());
            					}
            					setWithLastConsumed(
            						current,
            						"p2",
            						lv_p2_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:1039:1: entryRuleFqnObj returns [EObject current=null] : iv_ruleFqnObj= ruleFqnObj EOF ;
    public final EObject entryRuleFqnObj() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFqnObj = null;


        try {
            // InternalFormatterTestLanguage.g:1039:47: (iv_ruleFqnObj= ruleFqnObj EOF )
            // InternalFormatterTestLanguage.g:1040:2: iv_ruleFqnObj= ruleFqnObj EOF
            {
             newCompositeNode(grammarAccess.getFqnObjRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFqnObj=ruleFqnObj();

            state._fsp--;

             current =iv_ruleFqnObj; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1046:1: ruleFqnObj returns [EObject current=null] : (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) ) ;
    public final EObject ruleFqnObj() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1052:2: ( (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) ) )
            // InternalFormatterTestLanguage.g:1053:2: (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) )
            {
            // InternalFormatterTestLanguage.g:1053:2: (otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) ) )
            // InternalFormatterTestLanguage.g:1054:3: otherlv_0= 'fqn' ( (lv_name_1_0= ruleFQN ) )
            {
            otherlv_0=(Token)match(input,35,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getFqnObjAccess().getFqnKeyword_0());
            		
            // InternalFormatterTestLanguage.g:1058:3: ( (lv_name_1_0= ruleFQN ) )
            // InternalFormatterTestLanguage.g:1059:4: (lv_name_1_0= ruleFQN )
            {
            // InternalFormatterTestLanguage.g:1059:4: (lv_name_1_0= ruleFQN )
            // InternalFormatterTestLanguage.g:1060:5: lv_name_1_0= ruleFQN
            {

            					newCompositeNode(grammarAccess.getFqnObjAccess().getNameFQNParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_name_1_0=ruleFQN();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFqnObjRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_1_0,
            						"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.FQN");
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
    // InternalFormatterTestLanguage.g:1081:1: entryRuleFQN returns [String current=null] : iv_ruleFQN= ruleFQN EOF ;
    public final String entryRuleFQN() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFQN = null;


        try {
            // InternalFormatterTestLanguage.g:1081:43: (iv_ruleFQN= ruleFQN EOF )
            // InternalFormatterTestLanguage.g:1082:2: iv_ruleFQN= ruleFQN EOF
            {
             newCompositeNode(grammarAccess.getFQNRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFQN=ruleFQN();

            state._fsp--;

             current =iv_ruleFQN.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1088:1: ruleFQN returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleFQN() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1094:2: ( (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* ) )
            // InternalFormatterTestLanguage.g:1095:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            {
            // InternalFormatterTestLanguage.g:1095:2: (this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )* )
            // InternalFormatterTestLanguage.g:1096:3: this_ID_0= RULE_ID (kw= '.' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_25); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getFQNAccess().getIDTerminalRuleCall_0());
            		
            // InternalFormatterTestLanguage.g:1103:3: (kw= '.' this_ID_2= RULE_ID )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==36) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalFormatterTestLanguage.g:1104:4: kw= '.' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,36,FOLLOW_6); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getFQNAccess().getFullStopKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_25); 

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
    // InternalFormatterTestLanguage.g:1121:1: entryRuleFqnRef returns [EObject current=null] : iv_ruleFqnRef= ruleFqnRef EOF ;
    public final EObject entryRuleFqnRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFqnRef = null;


        try {
            // InternalFormatterTestLanguage.g:1121:47: (iv_ruleFqnRef= ruleFqnRef EOF )
            // InternalFormatterTestLanguage.g:1122:2: iv_ruleFqnRef= ruleFqnRef EOF
            {
             newCompositeNode(grammarAccess.getFqnRefRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFqnRef=ruleFqnRef();

            state._fsp--;

             current =iv_ruleFqnRef; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1128:1: ruleFqnRef returns [EObject current=null] : (otherlv_0= 'fqnref' ( ( ruleFQN ) ) ) ;
    public final EObject ruleFqnRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1134:2: ( (otherlv_0= 'fqnref' ( ( ruleFQN ) ) ) )
            // InternalFormatterTestLanguage.g:1135:2: (otherlv_0= 'fqnref' ( ( ruleFQN ) ) )
            {
            // InternalFormatterTestLanguage.g:1135:2: (otherlv_0= 'fqnref' ( ( ruleFQN ) ) )
            // InternalFormatterTestLanguage.g:1136:3: otherlv_0= 'fqnref' ( ( ruleFQN ) )
            {
            otherlv_0=(Token)match(input,37,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getFqnRefAccess().getFqnrefKeyword_0());
            		
            // InternalFormatterTestLanguage.g:1140:3: ( ( ruleFQN ) )
            // InternalFormatterTestLanguage.g:1141:4: ( ruleFQN )
            {
            // InternalFormatterTestLanguage.g:1141:4: ( ruleFQN )
            // InternalFormatterTestLanguage.g:1142:5: ruleFQN
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFqnRefRule());
            					}
            				

            					newCompositeNode(grammarAccess.getFqnRefAccess().getRefFqnObjCrossReference_1_0());
            				
            pushFollow(FOLLOW_2);
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
    // InternalFormatterTestLanguage.g:1160:1: entryRuleEnumeration returns [EObject current=null] : iv_ruleEnumeration= ruleEnumeration EOF ;
    public final EObject entryRuleEnumeration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEnumeration = null;


        try {
            // InternalFormatterTestLanguage.g:1160:52: (iv_ruleEnumeration= ruleEnumeration EOF )
            // InternalFormatterTestLanguage.g:1161:2: iv_ruleEnumeration= ruleEnumeration EOF
            {
             newCompositeNode(grammarAccess.getEnumerationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEnumeration=ruleEnumeration();

            state._fsp--;

             current =iv_ruleEnumeration; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1167:1: ruleEnumeration returns [EObject current=null] : (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* ) ;
    public final EObject ruleEnumeration() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Enumerator lv_val_1_0 = null;

        Enumerator lv_val_3_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1173:2: ( (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* ) )
            // InternalFormatterTestLanguage.g:1174:2: (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* )
            {
            // InternalFormatterTestLanguage.g:1174:2: (otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )* )
            // InternalFormatterTestLanguage.g:1175:3: otherlv_0= 'enum' ( (lv_val_1_0= ruleEnum1 ) )+ (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )*
            {
            otherlv_0=(Token)match(input,38,FOLLOW_26); 

            			newLeafNode(otherlv_0, grammarAccess.getEnumerationAccess().getEnumKeyword_0());
            		
            // InternalFormatterTestLanguage.g:1179:3: ( (lv_val_1_0= ruleEnum1 ) )+
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
            	    // InternalFormatterTestLanguage.g:1180:4: (lv_val_1_0= ruleEnum1 )
            	    {
            	    // InternalFormatterTestLanguage.g:1180:4: (lv_val_1_0= ruleEnum1 )
            	    // InternalFormatterTestLanguage.g:1181:5: lv_val_1_0= ruleEnum1
            	    {

            	    					newCompositeNode(grammarAccess.getEnumerationAccess().getValEnum1EnumRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_val_1_0=ruleEnum1();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getEnumerationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"val",
            	    						lv_val_1_0,
            	    						"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Enum1");
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

            // InternalFormatterTestLanguage.g:1198:3: (otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==17) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalFormatterTestLanguage.g:1199:4: otherlv_2= ',' ( (lv_val_3_0= ruleEnum1 ) )
            	    {
            	    otherlv_2=(Token)match(input,17,FOLLOW_26); 

            	    				newLeafNode(otherlv_2, grammarAccess.getEnumerationAccess().getCommaKeyword_2_0());
            	    			
            	    // InternalFormatterTestLanguage.g:1203:4: ( (lv_val_3_0= ruleEnum1 ) )
            	    // InternalFormatterTestLanguage.g:1204:5: (lv_val_3_0= ruleEnum1 )
            	    {
            	    // InternalFormatterTestLanguage.g:1204:5: (lv_val_3_0= ruleEnum1 )
            	    // InternalFormatterTestLanguage.g:1205:6: lv_val_3_0= ruleEnum1
            	    {

            	    						newCompositeNode(grammarAccess.getEnumerationAccess().getValEnum1EnumRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_28);
            	    lv_val_3_0=ruleEnum1();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getEnumerationRule());
            	    						}
            	    						add(
            	    							current,
            	    							"val",
            	    							lv_val_3_0,
            	    							"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Enum1");
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
    // InternalFormatterTestLanguage.g:1227:1: entryRuleSuppressedHidden returns [EObject current=null] : iv_ruleSuppressedHidden= ruleSuppressedHidden EOF ;
    public final EObject entryRuleSuppressedHidden() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHidden = null;



        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();

        try {
            // InternalFormatterTestLanguage.g:1229:2: (iv_ruleSuppressedHidden= ruleSuppressedHidden EOF )
            // InternalFormatterTestLanguage.g:1230:2: iv_ruleSuppressedHidden= ruleSuppressedHidden EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSuppressedHidden=ruleSuppressedHidden();

            state._fsp--;

             current =iv_ruleSuppressedHidden; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:1239:1: ruleSuppressedHidden returns [EObject current=null] : ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' ) ;
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
            // InternalFormatterTestLanguage.g:1246:2: ( ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' ) )
            // InternalFormatterTestLanguage.g:1247:2: ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' )
            {
            // InternalFormatterTestLanguage.g:1247:2: ( () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`' )
            // InternalFormatterTestLanguage.g:1248:3: () otherlv_1= '`' ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )? otherlv_5= '`'
            {
            // InternalFormatterTestLanguage.g:1248:3: ()
            // InternalFormatterTestLanguage.g:1249:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSuppressedHiddenAccess().getSuppressedHiddenAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,39,FOLLOW_29); 

            			newLeafNode(otherlv_1, grammarAccess.getSuppressedHiddenAccess().getGraveAccentKeyword_1());
            		
            // InternalFormatterTestLanguage.g:1259:3: ( ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID||LA18_0==41) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalFormatterTestLanguage.g:1260:4: ( (lv_vals_2_0= ruleSuppressedHiddenSub ) ) (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )*
                    {
                    // InternalFormatterTestLanguage.g:1260:4: ( (lv_vals_2_0= ruleSuppressedHiddenSub ) )
                    // InternalFormatterTestLanguage.g:1261:5: (lv_vals_2_0= ruleSuppressedHiddenSub )
                    {
                    // InternalFormatterTestLanguage.g:1261:5: (lv_vals_2_0= ruleSuppressedHiddenSub )
                    // InternalFormatterTestLanguage.g:1262:6: lv_vals_2_0= ruleSuppressedHiddenSub
                    {

                    						newCompositeNode(grammarAccess.getSuppressedHiddenAccess().getValsSuppressedHiddenSubParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_30);
                    lv_vals_2_0=ruleSuppressedHiddenSub();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSuppressedHiddenRule());
                    						}
                    						add(
                    							current,
                    							"vals",
                    							lv_vals_2_0,
                    							"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.SuppressedHiddenSub");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalFormatterTestLanguage.g:1279:4: (otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==40) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalFormatterTestLanguage.g:1280:5: otherlv_3= '%' ( (lv_vals_4_0= ruleSuppressedHiddenSub ) )
                    	    {
                    	    otherlv_3=(Token)match(input,40,FOLLOW_31); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getSuppressedHiddenAccess().getPercentSignKeyword_2_1_0());
                    	    				
                    	    // InternalFormatterTestLanguage.g:1284:5: ( (lv_vals_4_0= ruleSuppressedHiddenSub ) )
                    	    // InternalFormatterTestLanguage.g:1285:6: (lv_vals_4_0= ruleSuppressedHiddenSub )
                    	    {
                    	    // InternalFormatterTestLanguage.g:1285:6: (lv_vals_4_0= ruleSuppressedHiddenSub )
                    	    // InternalFormatterTestLanguage.g:1286:7: lv_vals_4_0= ruleSuppressedHiddenSub
                    	    {

                    	    							newCompositeNode(grammarAccess.getSuppressedHiddenAccess().getValsSuppressedHiddenSubParserRuleCall_2_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_30);
                    	    lv_vals_4_0=ruleSuppressedHiddenSub();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getSuppressedHiddenRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"vals",
                    	    								lv_vals_4_0,
                    	    								"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.SuppressedHiddenSub");
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

            otherlv_5=(Token)match(input,39,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:1316:1: entryRuleSuppressedHiddenSub returns [EObject current=null] : iv_ruleSuppressedHiddenSub= ruleSuppressedHiddenSub EOF ;
    public final EObject entryRuleSuppressedHiddenSub() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHiddenSub = null;


        try {
            // InternalFormatterTestLanguage.g:1316:60: (iv_ruleSuppressedHiddenSub= ruleSuppressedHiddenSub EOF )
            // InternalFormatterTestLanguage.g:1317:2: iv_ruleSuppressedHiddenSub= ruleSuppressedHiddenSub EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenSubRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSuppressedHiddenSub=ruleSuppressedHiddenSub();

            state._fsp--;

             current =iv_ruleSuppressedHiddenSub; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1323:1: ruleSuppressedHiddenSub returns [EObject current=null] : (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID ) ;
    public final EObject ruleSuppressedHiddenSub() throws RecognitionException {
        EObject current = null;

        EObject this_SuppressedHiddenSubSub_0 = null;

        EObject this_SuppressedHiddenSubID_1 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1329:2: ( (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID ) )
            // InternalFormatterTestLanguage.g:1330:2: (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID )
            {
            // InternalFormatterTestLanguage.g:1330:2: (this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub | this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID )
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
                    // InternalFormatterTestLanguage.g:1331:3: this_SuppressedHiddenSubSub_0= ruleSuppressedHiddenSubSub
                    {

                    			newCompositeNode(grammarAccess.getSuppressedHiddenSubAccess().getSuppressedHiddenSubSubParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SuppressedHiddenSubSub_0=ruleSuppressedHiddenSubSub();

                    state._fsp--;


                    			current = this_SuppressedHiddenSubSub_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFormatterTestLanguage.g:1340:3: this_SuppressedHiddenSubID_1= ruleSuppressedHiddenSubID
                    {

                    			newCompositeNode(grammarAccess.getSuppressedHiddenSubAccess().getSuppressedHiddenSubIDParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
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
    // InternalFormatterTestLanguage.g:1352:1: entryRuleSuppressedHiddenSubSub returns [EObject current=null] : iv_ruleSuppressedHiddenSubSub= ruleSuppressedHiddenSubSub EOF ;
    public final EObject entryRuleSuppressedHiddenSubSub() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHiddenSubSub = null;



        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");

        try {
            // InternalFormatterTestLanguage.g:1354:2: (iv_ruleSuppressedHiddenSubSub= ruleSuppressedHiddenSubSub EOF )
            // InternalFormatterTestLanguage.g:1355:2: iv_ruleSuppressedHiddenSubSub= ruleSuppressedHiddenSubSub EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenSubSubRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSuppressedHiddenSubSub=ruleSuppressedHiddenSubSub();

            state._fsp--;

             current =iv_ruleSuppressedHiddenSubSub; 
            match(input,EOF,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:1364:1: ruleSuppressedHiddenSubSub returns [EObject current=null] : (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' ) ;
    public final EObject ruleSuppressedHiddenSubSub() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_idval_1_0=null;
        Token otherlv_2=null;


        	enterRule();
        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");

        try {
            // InternalFormatterTestLanguage.g:1371:2: ( (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' ) )
            // InternalFormatterTestLanguage.g:1372:2: (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' )
            {
            // InternalFormatterTestLanguage.g:1372:2: (otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>' )
            // InternalFormatterTestLanguage.g:1373:3: otherlv_0= '<' ( (lv_idval_1_0= RULE_ID ) ) otherlv_2= '>'
            {
            otherlv_0=(Token)match(input,41,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getSuppressedHiddenSubSubAccess().getLessThanSignKeyword_0());
            		
            // InternalFormatterTestLanguage.g:1377:3: ( (lv_idval_1_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:1378:4: (lv_idval_1_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:1378:4: (lv_idval_1_0= RULE_ID )
            // InternalFormatterTestLanguage.g:1379:5: lv_idval_1_0= RULE_ID
            {
            lv_idval_1_0=(Token)match(input,RULE_ID,FOLLOW_32); 

            					newLeafNode(lv_idval_1_0, grammarAccess.getSuppressedHiddenSubSubAccess().getIdvalIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSuppressedHiddenSubSubRule());
            					}
            					setWithLastConsumed(
            						current,
            						"idval",
            						lv_idval_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,42,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:1406:1: entryRuleSuppressedHiddenSubID returns [EObject current=null] : iv_ruleSuppressedHiddenSubID= ruleSuppressedHiddenSubID EOF ;
    public final EObject entryRuleSuppressedHiddenSubID() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSuppressedHiddenSubID = null;


        try {
            // InternalFormatterTestLanguage.g:1406:62: (iv_ruleSuppressedHiddenSubID= ruleSuppressedHiddenSubID EOF )
            // InternalFormatterTestLanguage.g:1407:2: iv_ruleSuppressedHiddenSubID= ruleSuppressedHiddenSubID EOF
            {
             newCompositeNode(grammarAccess.getSuppressedHiddenSubIDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSuppressedHiddenSubID=ruleSuppressedHiddenSubID();

            state._fsp--;

             current =iv_ruleSuppressedHiddenSubID; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1413:1: ruleSuppressedHiddenSubID returns [EObject current=null] : ( (lv_idval_0_0= RULE_ID ) ) ;
    public final EObject ruleSuppressedHiddenSubID() throws RecognitionException {
        EObject current = null;

        Token lv_idval_0_0=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1419:2: ( ( (lv_idval_0_0= RULE_ID ) ) )
            // InternalFormatterTestLanguage.g:1420:2: ( (lv_idval_0_0= RULE_ID ) )
            {
            // InternalFormatterTestLanguage.g:1420:2: ( (lv_idval_0_0= RULE_ID ) )
            // InternalFormatterTestLanguage.g:1421:3: (lv_idval_0_0= RULE_ID )
            {
            // InternalFormatterTestLanguage.g:1421:3: (lv_idval_0_0= RULE_ID )
            // InternalFormatterTestLanguage.g:1422:4: lv_idval_0_0= RULE_ID
            {
            lv_idval_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(lv_idval_0_0, grammarAccess.getSuppressedHiddenSubIDAccess().getIdvalIDTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getSuppressedHiddenSubIDRule());
            				}
            				setWithLastConsumed(
            					current,
            					"idval",
            					lv_idval_0_0,
            					"org.eclipse.xtext.common.Terminals.ID");
            			

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
    // InternalFormatterTestLanguage.g:1441:1: entryRuleDatatype1 returns [String current=null] : iv_ruleDatatype1= ruleDatatype1 EOF ;
    public final String entryRuleDatatype1() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDatatype1 = null;


        try {
            // InternalFormatterTestLanguage.g:1441:49: (iv_ruleDatatype1= ruleDatatype1 EOF )
            // InternalFormatterTestLanguage.g:1442:2: iv_ruleDatatype1= ruleDatatype1 EOF
            {
             newCompositeNode(grammarAccess.getDatatype1Rule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatype1=ruleDatatype1();

            state._fsp--;

             current =iv_ruleDatatype1.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1448:1: ruleDatatype1 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_FQN_0= ruleFQN ;
    public final AntlrDatatypeRuleToken ruleDatatype1() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_FQN_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1454:2: (this_FQN_0= ruleFQN )
            // InternalFormatterTestLanguage.g:1455:2: this_FQN_0= ruleFQN
            {

            		newCompositeNode(grammarAccess.getDatatype1Access().getFQNParserRuleCall());
            	
            pushFollow(FOLLOW_2);
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
    // InternalFormatterTestLanguage.g:1468:1: entryRuleDatatype2 returns [String current=null] : iv_ruleDatatype2= ruleDatatype2 EOF ;
    public final String entryRuleDatatype2() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDatatype2 = null;


        try {
            // InternalFormatterTestLanguage.g:1468:49: (iv_ruleDatatype2= ruleDatatype2 EOF )
            // InternalFormatterTestLanguage.g:1469:2: iv_ruleDatatype2= ruleDatatype2 EOF
            {
             newCompositeNode(grammarAccess.getDatatype2Rule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatype2=ruleDatatype2();

            state._fsp--;

             current =iv_ruleDatatype2.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1475:1: ruleDatatype2 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_FQN_0= ruleFQN ;
    public final AntlrDatatypeRuleToken ruleDatatype2() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_FQN_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1481:2: (this_FQN_0= ruleFQN )
            // InternalFormatterTestLanguage.g:1482:2: this_FQN_0= ruleFQN
            {

            		newCompositeNode(grammarAccess.getDatatype2Access().getFQNParserRuleCall());
            	
            pushFollow(FOLLOW_2);
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
    // InternalFormatterTestLanguage.g:1495:1: entryRuleDatatype3 returns [String current=null] : iv_ruleDatatype3= ruleDatatype3 EOF ;
    public final String entryRuleDatatype3() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDatatype3 = null;


        try {
            // InternalFormatterTestLanguage.g:1495:49: (iv_ruleDatatype3= ruleDatatype3 EOF )
            // InternalFormatterTestLanguage.g:1496:2: iv_ruleDatatype3= ruleDatatype3 EOF
            {
             newCompositeNode(grammarAccess.getDatatype3Rule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatype3=ruleDatatype3();

            state._fsp--;

             current =iv_ruleDatatype3.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1502:1: ruleDatatype3 returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_FQN_0= ruleFQN ;
    public final AntlrDatatypeRuleToken ruleDatatype3() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_FQN_0 = null;



        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1508:2: (this_FQN_0= ruleFQN )
            // InternalFormatterTestLanguage.g:1509:2: this_FQN_0= ruleFQN
            {

            		newCompositeNode(grammarAccess.getDatatype3Access().getFQNParserRuleCall());
            	
            pushFollow(FOLLOW_2);
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
    // InternalFormatterTestLanguage.g:1522:1: entryRuleDatatypes returns [EObject current=null] : iv_ruleDatatypes= ruleDatatypes EOF ;
    public final EObject entryRuleDatatypes() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDatatypes = null;


        try {
            // InternalFormatterTestLanguage.g:1522:50: (iv_ruleDatatypes= ruleDatatypes EOF )
            // InternalFormatterTestLanguage.g:1523:2: iv_ruleDatatypes= ruleDatatypes EOF
            {
             newCompositeNode(grammarAccess.getDatatypesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDatatypes=ruleDatatypes();

            state._fsp--;

             current =iv_ruleDatatypes; 
            match(input,EOF,FOLLOW_2); 

            }

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
    // InternalFormatterTestLanguage.g:1529:1: ruleDatatypes returns [EObject current=null] : (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' ) ;
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
            // InternalFormatterTestLanguage.g:1535:2: ( (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' ) )
            // InternalFormatterTestLanguage.g:1536:2: (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' )
            {
            // InternalFormatterTestLanguage.g:1536:2: (otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3' )
            // InternalFormatterTestLanguage.g:1537:3: otherlv_0= 'datatypes' ( (lv_val1_1_0= ruleDatatype1 ) ) otherlv_2= 'kw1' ( (lv_val2_3_0= ruleDatatype2 ) ) ( (lv_val3_4_0= ruleDatatype3 ) ) otherlv_5= 'kw3'
            {
            otherlv_0=(Token)match(input,43,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getDatatypesAccess().getDatatypesKeyword_0());
            		
            // InternalFormatterTestLanguage.g:1541:3: ( (lv_val1_1_0= ruleDatatype1 ) )
            // InternalFormatterTestLanguage.g:1542:4: (lv_val1_1_0= ruleDatatype1 )
            {
            // InternalFormatterTestLanguage.g:1542:4: (lv_val1_1_0= ruleDatatype1 )
            // InternalFormatterTestLanguage.g:1543:5: lv_val1_1_0= ruleDatatype1
            {

            					newCompositeNode(grammarAccess.getDatatypesAccess().getVal1Datatype1ParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_33);
            lv_val1_1_0=ruleDatatype1();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDatatypesRule());
            					}
            					set(
            						current,
            						"val1",
            						lv_val1_1_0,
            						"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Datatype1");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,44,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getDatatypesAccess().getKw1Keyword_2());
            		
            // InternalFormatterTestLanguage.g:1564:3: ( (lv_val2_3_0= ruleDatatype2 ) )
            // InternalFormatterTestLanguage.g:1565:4: (lv_val2_3_0= ruleDatatype2 )
            {
            // InternalFormatterTestLanguage.g:1565:4: (lv_val2_3_0= ruleDatatype2 )
            // InternalFormatterTestLanguage.g:1566:5: lv_val2_3_0= ruleDatatype2
            {

            					newCompositeNode(grammarAccess.getDatatypesAccess().getVal2Datatype2ParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_6);
            lv_val2_3_0=ruleDatatype2();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDatatypesRule());
            					}
            					set(
            						current,
            						"val2",
            						lv_val2_3_0,
            						"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Datatype2");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFormatterTestLanguage.g:1583:3: ( (lv_val3_4_0= ruleDatatype3 ) )
            // InternalFormatterTestLanguage.g:1584:4: (lv_val3_4_0= ruleDatatype3 )
            {
            // InternalFormatterTestLanguage.g:1584:4: (lv_val3_4_0= ruleDatatype3 )
            // InternalFormatterTestLanguage.g:1585:5: lv_val3_4_0= ruleDatatype3
            {

            					newCompositeNode(grammarAccess.getDatatypesAccess().getVal3Datatype3ParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_34);
            lv_val3_4_0=ruleDatatype3();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDatatypesRule());
            					}
            					set(
            						current,
            						"val3",
            						lv_val3_4_0,
            						"com.avaloq.tools.ddk.xtext.formatter.FormatterTestLanguage.Datatype3");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,45,FOLLOW_2); 

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
    // InternalFormatterTestLanguage.g:1610:1: ruleEnum1 returns [Enumerator current=null] : ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) ) ;
    public final Enumerator ruleEnum1() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalFormatterTestLanguage.g:1616:2: ( ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) ) )
            // InternalFormatterTestLanguage.g:1617:2: ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) )
            {
            // InternalFormatterTestLanguage.g:1617:2: ( (enumLiteral_0= 'lit1' ) | (enumLiteral_1= 'lit2' ) | (enumLiteral_2= 'lit3' ) )
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
                    // InternalFormatterTestLanguage.g:1618:3: (enumLiteral_0= 'lit1' )
                    {
                    // InternalFormatterTestLanguage.g:1618:3: (enumLiteral_0= 'lit1' )
                    // InternalFormatterTestLanguage.g:1619:4: enumLiteral_0= 'lit1'
                    {
                    enumLiteral_0=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getEnum1Access().getLit1EnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getEnum1Access().getLit1EnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalFormatterTestLanguage.g:1626:3: (enumLiteral_1= 'lit2' )
                    {
                    // InternalFormatterTestLanguage.g:1626:3: (enumLiteral_1= 'lit2' )
                    // InternalFormatterTestLanguage.g:1627:4: enumLiteral_1= 'lit2'
                    {
                    enumLiteral_1=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getEnum1Access().getLit2EnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getEnum1Access().getLit2EnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalFormatterTestLanguage.g:1634:3: (enumLiteral_2= 'lit3' )
                    {
                    // InternalFormatterTestLanguage.g:1634:3: (enumLiteral_2= 'lit3' )
                    // InternalFormatterTestLanguage.g:1635:4: enumLiteral_2= 'lit3'
                    {
                    enumLiteral_2=(Token)match(input,48,FOLLOW_2); 

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
    static final String dfa_1s = "\13\uffff";
    static final String dfa_2s = "\2\4\11\uffff";
    static final String dfa_3s = "\1\53\1\17\11\uffff";
    static final String dfa_4s = "\2\uffff\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\1\1\2";
    static final String dfa_5s = "\13\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\16\uffff\1\2\3\uffff\1\7\13\uffff\1\3\1\uffff\1\4\1\5\1\6\3\uffff\1\10",
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

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "158:3: (this_Decl_0= ruleDecl | this_Assign_1= ruleAssign | this_Meth_2= ruleMeth | this_FqnObj_3= ruleFqnObj | this_FqnRef_4= ruleFqnRef | this_Enumeration_5= ruleEnumeration | (this_SuppressedHidden_6= ruleSuppressedHidden otherlv_7= 'post' ) | this_Space_8= ruleSpace | this_Datatypes_9= ruleDatatypes )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x00000004A7000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200010L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000220000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x000008E800880012L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000008E814880010L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000040000012L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x000008E800880010L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0001C00000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0001C00000020002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000028000000010L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000018000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000020000000010L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000200000000000L});

}