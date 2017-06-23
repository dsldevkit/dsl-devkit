package com.avaloq.tools.ddk.xtext.expression.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.avaloq.tools.ddk.xtext.expression.services.ExpressionGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalExpressionParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_REAL", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'let'", "'='", "':'", "'('", "')'", "'->'", "'?'", "'if'", "'then'", "'else'", "'switch'", "'{'", "'default'", "'}'", "'case'", "'||'", "'&&'", "'implies'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'!'", "'.'", "','", "'typeSelect'", "'collect'", "'select'", "'selectFirst'", "'reject'", "'exists'", "'notExists'", "'sortBy'", "'forAll'", "'|'", "'true'", "'false'", "'null'", "'GLOBALVAR'", "'new'", "'Collection'", "'List'", "'Set'", "'['", "']'", "'::'"
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
    public static final int RULE_REAL=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=9;
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


        public InternalExpressionParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalExpressionParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalExpressionParser.tokenNames; }
    public String getGrammarFileName() { return "../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     
     	private ExpressionGrammarAccess grammarAccess;
     	
        public InternalExpressionParser(TokenStream input, ExpressionGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Expression";	
       	}
       	
       	@Override
       	protected ExpressionGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleExpression"
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:73:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:74:2: (iv_ruleExpression= ruleExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:75:2: iv_ruleExpression= ruleExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleExpression_in_entryRuleExpression81);
            iv_ruleExpression=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression91); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:82:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:85:28: ( (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:86:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:86:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )
            int alt1=3;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:87:2: this_LetExpression_0= ruleLetExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getLetExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLetExpression_in_ruleExpression141);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:100:2: this_CastedExpression_1= ruleCastedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getCastedExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCastedExpression_in_ruleExpression171);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:113:2: this_ChainExpression_2= ruleChainExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpressionAccess().getChainExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleChainExpression_in_ruleExpression201);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:134:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:135:2: (iv_ruleLetExpression= ruleLetExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:136:2: iv_ruleLetExpression= ruleLetExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLetExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleLetExpression_in_entryRuleLetExpression238);
            iv_ruleLetExpression=ruleLetExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLetExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLetExpression248); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:143:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:146:28: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:147:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:147:1: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:147:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleLetExpression285); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:151:1: ( (lv_identifier_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:152:1: (lv_identifier_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:152:1: (lv_identifier_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:153:3: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleLetExpression306);
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

            otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleLetExpression318); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:173:1: ( (lv_varExpr_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:174:1: (lv_varExpr_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:174:1: (lv_varExpr_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:175:3: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression339);
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

            otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleLetExpression351); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:195:1: ( (lv_target_5_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:196:1: (lv_target_5_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:196:1: (lv_target_5_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:197:3: lv_target_5_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getLetExpressionAccess().getTargetExpressionParserRuleCall_5_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleLetExpression372);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:221:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:222:2: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:223:2: iv_ruleCastedExpression= ruleCastedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCastedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression408);
            iv_ruleCastedExpression=ruleCastedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCastedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCastedExpression418); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:230:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:233:28: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:234:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:234:1: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:234:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleCastedExpression455); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:238:1: ( (lv_type_1_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:239:1: (lv_type_1_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:239:1: (lv_type_1_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:240:3: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleCastedExpression476);
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

            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleCastedExpression488); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:260:1: ( (lv_target_3_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:261:1: (lv_target_3_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:261:1: (lv_target_3_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:262:3: lv_target_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCastedExpressionAccess().getTargetExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCastedExpression509);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:286:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:287:2: (iv_ruleChainExpression= ruleChainExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:288:2: iv_ruleChainExpression= ruleChainExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainExpression_in_entryRuleChainExpression545);
            iv_ruleChainExpression=ruleChainExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainExpression555); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:295:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:298:28: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:299:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:299:1: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:300:2: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression605);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ChainedExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:311:1: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:311:2: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:311:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:312:2: 
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

            	    otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleChainExpression629); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:324:1: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:325:1: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:325:1: (lv_next_3_0= ruleChainedExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:326:3: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleChainedExpression_in_ruleChainExpression650);
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
            	    break loop2;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:350:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:351:2: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:352:2: iv_ruleChainedExpression= ruleChainedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getChainedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression688);
            iv_ruleChainedExpression=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleChainedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleChainedExpression698); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:359:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:362:28: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:363:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:363:1: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            int alt3=3;
            switch ( input.LA(1) ) {
            case 19:
                {
                alt3=1;
                }
                break;
            case RULE_INT:
            case RULE_REAL:
            case RULE_STRING:
            case RULE_ID:
            case 15:
            case 23:
            case 37:
            case 40:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
                {
                alt3=2;
                }
                break;
            case 22:
                {
                alt3=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:364:2: this_IfExpressionKw_0= ruleIfExpressionKw
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionKwParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression748);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:377:2: this_IfExpressionTri_1= ruleIfExpressionTri
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getIfExpressionTriParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression778);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:390:2: this_SwitchExpression_2= ruleSwitchExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getChainedExpressionAccess().getSwitchExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSwitchExpression_in_ruleChainedExpression808);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:409:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:410:2: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:411:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionTriRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri843);
            iv_ruleIfExpressionTri=ruleIfExpressionTri();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionTri; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionTri853); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:418:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:421:28: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:422:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:422:1: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:423:2: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleIfExpressionTri903);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_OrExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:434:1: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:434:2: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:434:2: ()
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:435:2: 
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

                    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleIfExpressionTri927); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:447:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:448:1: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:448:1: (lv_thenPart_3_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:449:3: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri948);
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

                    otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleIfExpressionTri960); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:469:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:470:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:470:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:471:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionTriAccess().getElsePartChainedExpressionParserRuleCall_1_4_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri981);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:495:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:496:2: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:497:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIfExpressionKwRule()); 
            }
            pushFollow(FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw1019);
            iv_ruleIfExpressionKw=ruleIfExpressionKw();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIfExpressionKw; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIfExpressionKw1029); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:504:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:507:28: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:508:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:508:1: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:508:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleIfExpressionKw1066); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:512:1: ( (lv_condition_1_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:513:1: (lv_condition_1_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:513:1: (lv_condition_1_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:514:3: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw1087);
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

            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleIfExpressionKw1099); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:534:1: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:535:1: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:535:1: (lv_thenPart_3_0= ruleChainedExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:536:3: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw1120);
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

            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:552:2: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                int LA5_1 = input.LA(2);

                if ( (synpred7_InternalExpression()) ) {
                    alt5=1;
                }
            }
            switch (alt5) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:552:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,21,FOLLOW_21_in_ruleIfExpressionKw1133); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:556:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:557:1: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:557:1: (lv_elsePart_5_0= ruleChainedExpression )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:558:3: lv_elsePart_5_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw1154);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:582:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:583:2: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:584:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSwitchExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression1192);
            iv_ruleSwitchExpression=ruleSwitchExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSwitchExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSwitchExpression1202); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:591:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:594:28: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:595:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:595:1: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:595:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,22,FOLLOW_22_in_ruleSwitchExpression1239); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:599:1: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:599:3: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,15,FOLLOW_15_in_ruleSwitchExpression1252); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                          
                    }
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:603:1: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:604:1: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:604:1: (lv_switchExpr_2_0= ruleOrExpression )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:605:3: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression1273);
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

                    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleSwitchExpression1285); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                          
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,23,FOLLOW_23_in_ruleSwitchExpression1299); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:629:1: ( (lv_case_5_0= ruleCase ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==26) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:630:1: (lv_case_5_0= ruleCase )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:630:1: (lv_case_5_0= ruleCase )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:631:3: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleCase_in_ruleSwitchExpression1320);
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
            	    break loop7;
                }
            } while (true);

            otherlv_6=(Token)match(input,24,FOLLOW_24_in_ruleSwitchExpression1333); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
                  
            }
            otherlv_7=(Token)match(input,14,FOLLOW_14_in_ruleSwitchExpression1345); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:655:1: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:656:1: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:656:1: (lv_defaultExpr_8_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:657:3: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleSwitchExpression1366);
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

            otherlv_9=(Token)match(input,25,FOLLOW_25_in_ruleSwitchExpression1378); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:685:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:686:2: (iv_ruleCase= ruleCase EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:687:2: iv_ruleCase= ruleCase EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCaseRule()); 
            }
            pushFollow(FOLLOW_ruleCase_in_entryRuleCase1414);
            iv_ruleCase=ruleCase();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCase; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCase1424); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:694:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:697:28: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:698:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:698:1: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:698:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleCase1461); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:702:1: ( (lv_condition_1_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:703:1: (lv_condition_1_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:703:1: (lv_condition_1_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:704:3: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase1482);
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

            otherlv_2=(Token)match(input,14,FOLLOW_14_in_ruleCase1494); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:724:1: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:725:1: (lv_thenPar_3_0= ruleOrExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:725:1: (lv_thenPar_3_0= ruleOrExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:726:3: lv_thenPar_3_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCaseAccess().getThenParOrExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleOrExpression_in_ruleCase1515);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:750:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:751:2: (iv_ruleOrExpression= ruleOrExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:752:2: iv_ruleOrExpression= ruleOrExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOrExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleOrExpression_in_entryRuleOrExpression1551);
            iv_ruleOrExpression=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOrExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOrExpression1561); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:759:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:762:28: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:763:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:763:1: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:764:2: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression1611);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AndExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:775:1: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==27) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:775:2: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:775:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:776:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:784:2: ( (lv_operator_2_0= '||' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:785:1: (lv_operator_2_0= '||' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:785:1: (lv_operator_2_0= '||' )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:786:3: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,27,FOLLOW_27_in_ruleOrExpression1641); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:799:2: ( (lv_right_3_0= ruleAndExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:800:1: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:800:1: (lv_right_3_0= ruleAndExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:801:3: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAndExpression_in_ruleOrExpression1675);
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
            	    break loop8;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:825:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:826:2: (iv_ruleAndExpression= ruleAndExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:827:2: iv_ruleAndExpression= ruleAndExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAndExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAndExpression_in_entryRuleAndExpression1713);
            iv_ruleAndExpression=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAndExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAndExpression1723); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:834:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:837:28: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:838:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:838:1: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:839:2: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression1773);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_ImpliesExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:850:1: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==28) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:850:2: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:850:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:851:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:859:2: ( (lv_operator_2_0= '&&' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:860:1: (lv_operator_2_0= '&&' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:860:1: (lv_operator_2_0= '&&' )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:861:3: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,28,FOLLOW_28_in_ruleAndExpression1803); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:874:2: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:875:1: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:875:1: (lv_right_3_0= ruleImpliesExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:876:3: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleImpliesExpression_in_ruleAndExpression1837);
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
            	    break loop9;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:900:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:901:2: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:902:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getImpliesExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression1875);
            iv_ruleImpliesExpression=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleImpliesExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleImpliesExpression1885); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:909:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:912:28: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:913:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:913:1: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:914:2: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression1935);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_RelationalExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:925:1: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==29) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:925:2: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:925:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:926:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:934:2: ( (lv_operator_2_0= 'implies' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:935:1: (lv_operator_2_0= 'implies' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:935:1: (lv_operator_2_0= 'implies' )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:936:3: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,29,FOLLOW_29_in_ruleImpliesExpression1965); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:949:2: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:950:1: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:950:1: (lv_right_3_0= ruleRelationalExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:951:3: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression1999);
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
            	    break loop10;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:975:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:976:2: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:977:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRelationalExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression2037);
            iv_ruleRelationalExpression=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRelationalExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRelationalExpression2047); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:984:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:987:28: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:988:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:988:1: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:989:2: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression2097);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_AdditiveExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1000:1: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=30 && LA12_0<=35)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1000:2: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1000:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1001:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1009:2: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1010:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1010:1: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1011:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1011:1: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    int alt11=6;
            	    switch ( input.LA(1) ) {
            	    case 30:
            	        {
            	        alt11=1;
            	        }
            	        break;
            	    case 31:
            	        {
            	        alt11=2;
            	        }
            	        break;
            	    case 32:
            	        {
            	        alt11=3;
            	        }
            	        break;
            	    case 33:
            	        {
            	        alt11=4;
            	        }
            	        break;
            	    case 34:
            	        {
            	        alt11=5;
            	        }
            	        break;
            	    case 35:
            	        {
            	        alt11=6;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1012:3: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,30,FOLLOW_30_in_ruleRelationalExpression2129); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1024:8: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,31,FOLLOW_31_in_ruleRelationalExpression2158); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1036:8: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,32,FOLLOW_32_in_ruleRelationalExpression2187); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1048:8: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,33,FOLLOW_33_in_ruleRelationalExpression2216); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1060:8: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,34,FOLLOW_34_in_ruleRelationalExpression2245); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1072:8: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,35,FOLLOW_35_in_ruleRelationalExpression2274); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1087:2: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1088:1: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1088:1: (lv_right_3_0= ruleAdditiveExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1089:3: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression2311);
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
            	    break loop12;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1113:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1114:2: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1115:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getAdditiveExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression2349);
            iv_ruleAdditiveExpression=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleAdditiveExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleAdditiveExpression2359); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1122:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1125:28: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1126:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1126:1: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1127:2: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression2409);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_MultiplicativeExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1138:1: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=36 && LA14_0<=37)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1138:2: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1138:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1139:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1147:2: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1148:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1148:1: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1149:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1149:1: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    int alt13=2;
            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==36) ) {
            	        alt13=1;
            	    }
            	    else if ( (LA13_0==37) ) {
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1150:3: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,36,FOLLOW_36_in_ruleAdditiveExpression2441); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1162:8: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,37,FOLLOW_37_in_ruleAdditiveExpression2470); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1177:2: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1178:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1178:1: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1179:3: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression2507);
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
            	    break loop14;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1203:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1204:2: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1205:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getMultiplicativeExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression2545);
            iv_ruleMultiplicativeExpression=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleMultiplicativeExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleMultiplicativeExpression2555); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1212:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1215:28: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1216:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1216:1: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1217:2: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression2605);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_UnaryOrInfixExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1228:1: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=38 && LA16_0<=39)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1228:2: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1228:2: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1229:2: 
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1237:2: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1238:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1238:1: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1239:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1239:1: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    int alt15=2;
            	    int LA15_0 = input.LA(1);

            	    if ( (LA15_0==38) ) {
            	        alt15=1;
            	    }
            	    else if ( (LA15_0==39) ) {
            	        alt15=2;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 15, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt15) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1240:3: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,38,FOLLOW_38_in_ruleMultiplicativeExpression2637); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1252:8: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,39,FOLLOW_39_in_ruleMultiplicativeExpression2666); if (state.failed) return current;
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

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1267:2: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1268:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1268:1: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1269:3: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression2703);
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
            	    break loop16;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1293:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1294:2: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1295:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryOrInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression2741);
            iv_ruleUnaryOrInfixExpression=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryOrInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression2751); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1302:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1305:28: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1306:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1306:1: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==37||LA17_0==40) ) {
                alt17=1;
            }
            else if ( ((LA17_0>=RULE_INT && LA17_0<=RULE_ID)||LA17_0==15||LA17_0==23||(LA17_0>=43 && LA17_0<=51)||(LA17_0>=53 && LA17_0<=60)) ) {
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1307:2: this_UnaryExpression_0= ruleUnaryExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getUnaryExpressionParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression2801);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1320:2: this_InfixExpression_1= ruleInfixExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getUnaryOrInfixExpressionAccess().getInfixExpressionParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression2831);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1339:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1340:2: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1341:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getUnaryExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression2866);
            iv_ruleUnaryExpression=ruleUnaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleUnaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnaryExpression2876); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1348:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1351:28: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1352:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1352:1: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1352:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1352:2: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1353:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1353:1: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1354:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1354:1: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==40) ) {
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1355:3: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,40,FOLLOW_40_in_ruleUnaryExpression2921); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1367:8: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,37,FOLLOW_37_in_ruleUnaryExpression2950); if (state.failed) return current;
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

            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1382:2: ( (lv_params_1_0= ruleInfixExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1383:1: (lv_params_1_0= ruleInfixExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1383:1: (lv_params_1_0= ruleInfixExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1384:3: lv_params_1_0= ruleInfixExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getUnaryExpressionAccess().getParamsInfixExpressionParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_ruleUnaryExpression2987);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1408:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1409:2: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1410:2: iv_ruleInfixExpression= ruleInfixExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getInfixExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression3023);
            iv_ruleInfixExpression=ruleInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleInfixExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleInfixExpression3033); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1417:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1420:28: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1421:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1421:1: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1422:2: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_ruleInfixExpression3083);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_PrimaryExpression_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1433:1: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop23:
            do {
                int alt23=5;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==41) ) {
                    switch ( input.LA(2) ) {
                    case 43:
                        {
                        alt23=3;
                        }
                        break;
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                        {
                        alt23=4;
                        }
                        break;
                    case 58:
                    case 59:
                    case 60:
                        {
                        alt23=2;
                        }
                        break;
                    case RULE_ID:
                        {
                        int LA23_6 = input.LA(3);

                        if ( (LA23_6==EOF||LA23_6==14||(LA23_6>=16 && LA23_6<=18)||(LA23_6>=20 && LA23_6<=21)||(LA23_6>=24 && LA23_6<=39)||(LA23_6>=41 && LA23_6<=42)||LA23_6==63) ) {
                            alt23=2;
                        }
                        else if ( (LA23_6==15) ) {
                            alt23=1;
                        }


                        }
                        break;

                    }

                }


                switch (alt23) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1433:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1433:2: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1433:3: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1433:3: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1434:2: 
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

            	    otherlv_2=(Token)match(input,41,FOLLOW_41_in_ruleInfixExpression3108); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1446:1: ( (lv_name_3_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1447:1: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1447:1: (lv_name_3_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1448:3: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression3129);
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

            	    otherlv_4=(Token)match(input,15,FOLLOW_15_in_ruleInfixExpression3141); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1468:1: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt20=2;
            	    int LA20_0 = input.LA(1);

            	    if ( ((LA20_0>=RULE_INT && LA20_0<=RULE_ID)||LA20_0==12||LA20_0==15||LA20_0==19||(LA20_0>=22 && LA20_0<=23)||LA20_0==37||LA20_0==40||(LA20_0>=43 && LA20_0<=51)||(LA20_0>=53 && LA20_0<=60)) ) {
            	        alt20=1;
            	    }
            	    switch (alt20) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1468:2: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1468:2: ( (lv_params_5_0= ruleExpression ) )
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1469:1: (lv_params_5_0= ruleExpression )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1469:1: (lv_params_5_0= ruleExpression )
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1470:3: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression3163);
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

            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1486:2: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop19:
            	            do {
            	                int alt19=2;
            	                int LA19_0 = input.LA(1);

            	                if ( (LA19_0==42) ) {
            	                    alt19=1;
            	                }


            	                switch (alt19) {
            	            	case 1 :
            	            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1486:4: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,42,FOLLOW_42_in_ruleInfixExpression3176); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	          	newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	          
            	            	    }
            	            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1490:1: ( (lv_params_7_0= ruleExpression ) )
            	            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1491:1: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1491:1: (lv_params_7_0= ruleExpression )
            	            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1492:3: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {
            	            	       
            	            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0()); 
            	            	      	    
            	            	    }
            	            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression3197);
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
            	            	    break loop19;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,16,FOLLOW_16_in_ruleInfixExpression3213); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1513:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1513:6: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1513:7: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1513:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1514:2: 
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

            	    otherlv_10=(Token)match(input,41,FOLLOW_41_in_ruleInfixExpression3245); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1526:1: ( (lv_type_11_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1527:1: (lv_type_11_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1527:1: (lv_type_11_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1528:3: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression3266);
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
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1545:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1545:6: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1545:7: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1545:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1546:2: 
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

            	    otherlv_13=(Token)match(input,41,FOLLOW_41_in_ruleInfixExpression3298); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1558:1: ( (lv_name_14_0= 'typeSelect' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1559:1: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1559:1: (lv_name_14_0= 'typeSelect' )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1560:3: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,43,FOLLOW_43_in_ruleInfixExpression3316); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,15,FOLLOW_15_in_ruleInfixExpression3341); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1577:1: ( (lv_type_16_0= ruleType ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1578:1: (lv_type_16_0= ruleType )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1578:1: (lv_type_16_0= ruleType )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1579:3: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleType_in_ruleInfixExpression3362);
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

            	    otherlv_17=(Token)match(input,16,FOLLOW_16_in_ruleInfixExpression3374); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	          
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1600:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1600:6: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1600:7: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1600:7: ()
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1601:2: 
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

            	    otherlv_19=(Token)match(input,41,FOLLOW_41_in_ruleInfixExpression3406); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1613:1: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1614:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1614:1: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1615:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1615:1: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    int alt21=8;
            	    switch ( input.LA(1) ) {
            	    case 44:
            	        {
            	        alt21=1;
            	        }
            	        break;
            	    case 45:
            	        {
            	        alt21=2;
            	        }
            	        break;
            	    case 46:
            	        {
            	        alt21=3;
            	        }
            	        break;
            	    case 47:
            	        {
            	        alt21=4;
            	        }
            	        break;
            	    case 48:
            	        {
            	        alt21=5;
            	        }
            	        break;
            	    case 49:
            	        {
            	        alt21=6;
            	        }
            	        break;
            	    case 50:
            	        {
            	        alt21=7;
            	        }
            	        break;
            	    case 51:
            	        {
            	        alt21=8;
            	        }
            	        break;
            	    default:
            	        if (state.backtracking>0) {state.failed=true; return current;}
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 21, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt21) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1616:3: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,44,FOLLOW_44_in_ruleInfixExpression3426); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1628:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,45,FOLLOW_45_in_ruleInfixExpression3455); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1640:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,46,FOLLOW_46_in_ruleInfixExpression3484); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1652:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,47,FOLLOW_47_in_ruleInfixExpression3513); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1664:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,48,FOLLOW_48_in_ruleInfixExpression3542); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1676:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,49,FOLLOW_49_in_ruleInfixExpression3571); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1688:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,50,FOLLOW_50_in_ruleInfixExpression3600); if (state.failed) return current;
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
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1700:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,51,FOLLOW_51_in_ruleInfixExpression3629); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,15,FOLLOW_15_in_ruleInfixExpression3657); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1719:1: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
            	    int alt22=2;
            	    int LA22_0 = input.LA(1);

            	    if ( (LA22_0==RULE_ID) ) {
            	        int LA22_1 = input.LA(2);

            	        if ( (LA22_1==52) ) {
            	            alt22=1;
            	        }
            	    }
            	    switch (alt22) {
            	        case 1 :
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1719:2: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1719:2: ( (lv_var_22_0= ruleIdentifier ) )
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1720:1: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1720:1: (lv_var_22_0= ruleIdentifier )
            	            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1721:3: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {
            	               
            	              	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0()); 
            	              	    
            	            }
            	            pushFollow(FOLLOW_ruleIdentifier_in_ruleInfixExpression3679);
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

            	            otherlv_23=(Token)match(input,52,FOLLOW_52_in_ruleInfixExpression3691); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	                  	newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	                  
            	            }

            	            }
            	            break;

            	    }

            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1741:3: ( (lv_exp_24_0= ruleExpression ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1742:1: (lv_exp_24_0= ruleExpression )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1742:1: (lv_exp_24_0= ruleExpression )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1743:3: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression_in_ruleInfixExpression3714);
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

            	    otherlv_25=(Token)match(input,16,FOLLOW_16_in_ruleInfixExpression3726); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_25, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_3_6());
            	          
            	    }

            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1771:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1772:2: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1773:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            }
            pushFollow(FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression3765);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_rulePrimaryExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRulePrimaryExpression3775); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1780:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1783:28: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1784:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1784:1: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            int alt24=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_REAL:
            case RULE_STRING:
            case 53:
            case 54:
            case 55:
                {
                alt24=1;
                }
                break;
            case RULE_ID:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 58:
            case 59:
            case 60:
                {
                alt24=2;
                }
                break;
            case 23:
                {
                alt24=3;
                }
                break;
            case 57:
                {
                alt24=4;
                }
                break;
            case 56:
                {
                alt24=5;
                }
                break;
            case 15:
                {
                alt24=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1785:2: this_Literal_0= ruleLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleLiteral_in_rulePrimaryExpression3825);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1798:2: this_FeatureCall_1= ruleFeatureCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getFeatureCallParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleFeatureCall_in_rulePrimaryExpression3855);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1811:2: this_ListLiteral_2= ruleListLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getListLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleListLiteral_in_rulePrimaryExpression3885);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1824:2: this_ConstructorCallExpression_3= ruleConstructorCallExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getConstructorCallExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression3915);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1837:2: this_GlobalVarExpression_4= ruleGlobalVarExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getGlobalVarExpressionParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression3945);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1850:2: this_ParanthesizedExpression_5= ruleParanthesizedExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getParanthesizedExpressionParserRuleCall_5()); 
                          
                    }
                    pushFollow(FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression3975);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1869:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1870:2: (iv_ruleLiteral= ruleLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1871:2: iv_ruleLiteral= ruleLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleLiteral_in_entryRuleLiteral4010);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleLiteral4020); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1878:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1881:28: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1882:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1882:1: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            int alt25=5;
            switch ( input.LA(1) ) {
            case 53:
            case 54:
                {
                alt25=1;
                }
                break;
            case RULE_INT:
                {
                alt25=2;
                }
                break;
            case 55:
                {
                alt25=3;
                }
                break;
            case RULE_REAL:
                {
                alt25=4;
                }
                break;
            case RULE_STRING:
                {
                alt25=5;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1883:2: this_BooleanLiteral_0= ruleBooleanLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleBooleanLiteral_in_ruleLiteral4070);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1896:2: this_IntegerLiteral_1= ruleIntegerLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getIntegerLiteralParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleIntegerLiteral_in_ruleLiteral4100);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1909:2: this_NullLiteral_2= ruleNullLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getNullLiteralParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleNullLiteral_in_ruleLiteral4130);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1922:2: this_RealLiteral_3= ruleRealLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getRealLiteralParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleRealLiteral_in_ruleLiteral4160);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1935:2: this_StringLiteral_4= ruleStringLiteral
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_4()); 
                          
                    }
                    pushFollow(FOLLOW_ruleStringLiteral_in_ruleLiteral4190);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1954:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1955:2: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1956:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getBooleanLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral4225);
            iv_ruleBooleanLiteral=ruleBooleanLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleBooleanLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleBooleanLiteral4235); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1963:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1966:28: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1967:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1967:1: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1968:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1968:1: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1969:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1969:1: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==53) ) {
                alt26=1;
            }
            else if ( (LA26_0==54) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1970:3: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,53,FOLLOW_53_in_ruleBooleanLiteral4279); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:1982:8: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,54,FOLLOW_54_in_ruleBooleanLiteral4308); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2005:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2006:2: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2007:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIntegerLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral4359);
            iv_ruleIntegerLiteral=ruleIntegerLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIntegerLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIntegerLiteral4369); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2014:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2017:28: ( ( (lv_val_0_0= RULE_INT ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2018:1: ( (lv_val_0_0= RULE_INT ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2018:1: ( (lv_val_0_0= RULE_INT ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2019:1: (lv_val_0_0= RULE_INT )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2019:1: (lv_val_0_0= RULE_INT )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2020:3: lv_val_0_0= RULE_INT
            {
            lv_val_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleIntegerLiteral4410); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2044:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2045:2: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2046:2: iv_ruleNullLiteral= ruleNullLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNullLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral4450);
            iv_ruleNullLiteral=ruleNullLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNullLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNullLiteral4460); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2053:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2056:28: ( ( (lv_val_0_0= 'null' ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2057:1: ( (lv_val_0_0= 'null' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2057:1: ( (lv_val_0_0= 'null' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2058:1: (lv_val_0_0= 'null' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2058:1: (lv_val_0_0= 'null' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2059:3: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,55,FOLLOW_55_in_ruleNullLiteral4502); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2080:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2081:2: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2082:2: iv_ruleRealLiteral= ruleRealLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getRealLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral4550);
            iv_ruleRealLiteral=ruleRealLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleRealLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleRealLiteral4560); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2089:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2092:28: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2093:1: ( (lv_val_0_0= RULE_REAL ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2093:1: ( (lv_val_0_0= RULE_REAL ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2094:1: (lv_val_0_0= RULE_REAL )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2094:1: (lv_val_0_0= RULE_REAL )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2095:3: lv_val_0_0= RULE_REAL
            {
            lv_val_0_0=(Token)match(input,RULE_REAL,FOLLOW_RULE_REAL_in_ruleRealLiteral4601); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2119:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2120:2: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2121:2: iv_ruleStringLiteral= ruleStringLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getStringLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral4641);
            iv_ruleStringLiteral=ruleStringLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleStringLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleStringLiteral4651); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2128:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2131:28: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2132:1: ( (lv_val_0_0= RULE_STRING ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2132:1: ( (lv_val_0_0= RULE_STRING ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2133:1: (lv_val_0_0= RULE_STRING )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2133:1: (lv_val_0_0= RULE_STRING )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2134:3: lv_val_0_0= RULE_STRING
            {
            lv_val_0_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleStringLiteral4692); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2158:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2159:2: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2160:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getParanthesizedExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression4732);
            iv_ruleParanthesizedExpression=ruleParanthesizedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleParanthesizedExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleParanthesizedExpression4742); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2167:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2170:28: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2171:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2171:1: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2171:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_15_in_ruleParanthesizedExpression4779); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
                  
            }
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleParanthesizedExpression4804);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression_1; 
                      afterParserOrEnumRuleCall();
                  
            }
            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleParanthesizedExpression4815); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2199:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2200:2: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2201:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getGlobalVarExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression4851);
            iv_ruleGlobalVarExpression=ruleGlobalVarExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleGlobalVarExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleGlobalVarExpression4861); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2208:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2211:28: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2212:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2212:1: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2212:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,56,FOLLOW_56_in_ruleGlobalVarExpression4898); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2216:1: ( (lv_name_1_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2217:1: (lv_name_1_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2217:1: (lv_name_1_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2218:3: lv_name_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getGlobalVarExpressionAccess().getNameIdentifierParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression4919);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2242:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2243:2: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2244:2: iv_ruleFeatureCall= ruleFeatureCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getFeatureCallRule()); 
            }
            pushFollow(FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall4955);
            iv_ruleFeatureCall=ruleFeatureCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleFeatureCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleFeatureCall4965); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2251:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2254:28: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2255:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2255:1: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            int alt27=4;
            switch ( input.LA(1) ) {
            case RULE_ID:
                {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==15) ) {
                    alt27=1;
                }
                else if ( (LA27_1==EOF||LA27_1==14||(LA27_1>=16 && LA27_1<=18)||(LA27_1>=20 && LA27_1<=21)||(LA27_1>=24 && LA27_1<=39)||(LA27_1>=41 && LA27_1<=42)||LA27_1==63) ) {
                    alt27=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return current;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;
                }
                }
                break;
            case 58:
            case 59:
            case 60:
                {
                alt27=2;
                }
                break;
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
                {
                alt27=3;
                }
                break;
            case 43:
                {
                alt27=4;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2256:2: this_OperationCall_0= ruleOperationCall
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getOperationCallParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleOperationCall_in_ruleFeatureCall5015);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2268:6: ( (lv_type_1_0= ruleType ) )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2268:6: ( (lv_type_1_0= ruleType ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2269:1: (lv_type_1_0= ruleType )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2269:1: (lv_type_1_0= ruleType )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2270:3: lv_type_1_0= ruleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeTypeParserRuleCall_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleType_in_ruleFeatureCall5041);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2288:2: this_CollectionExpression_2= ruleCollectionExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getCollectionExpressionParserRuleCall_2()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionExpression_in_ruleFeatureCall5072);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2301:2: this_TypeSelectExpression_3= ruleTypeSelectExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getFeatureCallAccess().getTypeSelectExpressionParserRuleCall_3()); 
                          
                    }
                    pushFollow(FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall5102);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2320:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2321:2: (iv_ruleOperationCall= ruleOperationCall EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2322:2: iv_ruleOperationCall= ruleOperationCall EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getOperationCallRule()); 
            }
            pushFollow(FOLLOW_ruleOperationCall_in_entryRuleOperationCall5137);
            iv_ruleOperationCall=ruleOperationCall();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleOperationCall; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationCall5147); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2329:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2332:28: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2333:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2333:1: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2333:2: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2333:2: ( (lv_name_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2334:1: (lv_name_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2334:1: (lv_name_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2335:3: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleOperationCall5193);
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

            otherlv_1=(Token)match(input,15,FOLLOW_15_in_ruleOperationCall5205); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2355:1: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_INT && LA29_0<=RULE_ID)||LA29_0==12||LA29_0==15||LA29_0==19||(LA29_0>=22 && LA29_0<=23)||LA29_0==37||LA29_0==40||(LA29_0>=43 && LA29_0<=51)||(LA29_0>=53 && LA29_0<=60)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2355:2: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2355:2: ( (lv_params_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2356:1: (lv_params_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2356:1: (lv_params_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2357:3: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall5227);
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

                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2373:2: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==42) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2373:4: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,42,FOLLOW_42_in_ruleOperationCall5240); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2377:1: ( (lv_params_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2378:1: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2378:1: (lv_params_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2379:3: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleOperationCall5261);
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
                    	    break loop28;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleOperationCall5277); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2407:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2408:2: (iv_ruleListLiteral= ruleListLiteral EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2409:2: iv_ruleListLiteral= ruleListLiteral EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getListLiteralRule()); 
            }
            pushFollow(FOLLOW_ruleListLiteral_in_entryRuleListLiteral5313);
            iv_ruleListLiteral=ruleListLiteral();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleListLiteral; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleListLiteral5323); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2416:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2419:28: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2420:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2420:1: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2420:2: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2420:2: ()
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2421:2: 
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

            otherlv_1=(Token)match(input,23,FOLLOW_23_in_ruleListLiteral5372); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2433:1: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_INT && LA31_0<=RULE_ID)||LA31_0==12||LA31_0==15||LA31_0==19||(LA31_0>=22 && LA31_0<=23)||LA31_0==37||LA31_0==40||(LA31_0>=43 && LA31_0<=51)||(LA31_0>=53 && LA31_0<=60)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2433:2: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2433:2: ( (lv_elements_2_0= ruleExpression ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2434:1: (lv_elements_2_0= ruleExpression )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2434:1: (lv_elements_2_0= ruleExpression )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2435:3: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral5394);
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

                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2451:2: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==42) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2451:4: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,42,FOLLOW_42_in_ruleListLiteral5407); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	          	newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	          
                    	    }
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2455:1: ( (lv_elements_4_0= ruleExpression ) )
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2456:1: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2456:1: (lv_elements_4_0= ruleExpression )
                    	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2457:3: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {
                    	       
                    	      	        newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0()); 
                    	      	    
                    	    }
                    	    pushFollow(FOLLOW_ruleExpression_in_ruleListLiteral5428);
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
                    	    break loop30;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,25,FOLLOW_25_in_ruleListLiteral5444); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2485:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2486:2: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2487:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getConstructorCallExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression5480);
            iv_ruleConstructorCallExpression=ruleConstructorCallExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleConstructorCallExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleConstructorCallExpression5490); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2494:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2497:28: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2498:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2498:1: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2498:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,57,FOLLOW_57_in_ruleConstructorCallExpression5527); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2502:1: ( (lv_type_1_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2503:1: (lv_type_1_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2503:1: (lv_type_1_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2504:3: lv_type_1_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getConstructorCallExpressionAccess().getTypeSimpleTypeParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression5548);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2528:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2529:2: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2530:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeSelectExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression5584);
            iv_ruleTypeSelectExpression=ruleTypeSelectExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleTypeSelectExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleTypeSelectExpression5594); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2537:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2540:28: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2541:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2541:1: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2541:2: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2541:2: ( (lv_name_0_0= 'typeSelect' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2542:1: (lv_name_0_0= 'typeSelect' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2542:1: (lv_name_0_0= 'typeSelect' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2543:3: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,43,FOLLOW_43_in_ruleTypeSelectExpression5637); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,15,FOLLOW_15_in_ruleTypeSelectExpression5662); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2560:1: ( (lv_type_2_0= ruleType ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2561:1: (lv_type_2_0= ruleType )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2561:1: (lv_type_2_0= ruleType )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2562:3: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleType_in_ruleTypeSelectExpression5683);
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

            otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleTypeSelectExpression5695); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2590:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2591:2: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2592:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionExpressionRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression5731);
            iv_ruleCollectionExpression=ruleCollectionExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionExpression; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionExpression5741); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2599:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2602:28: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2603:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2603:1: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2603:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2603:2: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2604:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2604:1: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2605:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2605:1: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            int alt32=8;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt32=1;
                }
                break;
            case 45:
                {
                alt32=2;
                }
                break;
            case 46:
                {
                alt32=3;
                }
                break;
            case 47:
                {
                alt32=4;
                }
                break;
            case 48:
                {
                alt32=5;
                }
                break;
            case 49:
                {
                alt32=6;
                }
                break;
            case 50:
                {
                alt32=7;
                }
                break;
            case 51:
                {
                alt32=8;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2606:3: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,44,FOLLOW_44_in_ruleCollectionExpression5786); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2618:8: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,45,FOLLOW_45_in_ruleCollectionExpression5815); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2630:8: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,46,FOLLOW_46_in_ruleCollectionExpression5844); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2642:8: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,47,FOLLOW_47_in_ruleCollectionExpression5873); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2654:8: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,48,FOLLOW_48_in_ruleCollectionExpression5902); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2666:8: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,49,FOLLOW_49_in_ruleCollectionExpression5931); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2678:8: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,50,FOLLOW_50_in_ruleCollectionExpression5960); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2690:8: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,51,FOLLOW_51_in_ruleCollectionExpression5989); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,15,FOLLOW_15_in_ruleCollectionExpression6017); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2709:1: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==RULE_ID) ) {
                int LA33_1 = input.LA(2);

                if ( (LA33_1==52) ) {
                    alt33=1;
                }
            }
            switch (alt33) {
                case 1 :
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2709:2: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2709:2: ( (lv_var_2_0= ruleIdentifier ) )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2710:1: (lv_var_2_0= ruleIdentifier )
                    {
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2710:1: (lv_var_2_0= ruleIdentifier )
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2711:3: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleIdentifier_in_ruleCollectionExpression6039);
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

                    otherlv_3=(Token)match(input,52,FOLLOW_52_in_ruleCollectionExpression6051); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                          
                    }

                    }
                    break;

            }

            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2731:3: ( (lv_exp_4_0= ruleExpression ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2732:1: (lv_exp_4_0= ruleExpression )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2732:1: (lv_exp_4_0= ruleExpression )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2733:3: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression_in_ruleCollectionExpression6074);
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

            otherlv_5=(Token)match(input,16,FOLLOW_16_in_ruleCollectionExpression6086); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2761:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2762:2: (iv_ruleType= ruleType EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2763:2: iv_ruleType= ruleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getTypeRule()); 
            }
            pushFollow(FOLLOW_ruleType_in_entryRuleType6122);
            iv_ruleType=ruleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleType6132); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2770:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2773:28: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2774:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2774:1: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( ((LA34_0>=58 && LA34_0<=60)) ) {
                alt34=1;
            }
            else if ( (LA34_0==RULE_ID) ) {
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2775:2: this_CollectionType_0= ruleCollectionType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getCollectionTypeParserRuleCall_0()); 
                          
                    }
                    pushFollow(FOLLOW_ruleCollectionType_in_ruleType6182);
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2788:2: this_SimpleType_1= ruleSimpleType
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleSimpleType_in_ruleType6212);
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2807:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2808:2: (iv_ruleCollectionType= ruleCollectionType EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2809:2: iv_ruleCollectionType= ruleCollectionType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getCollectionTypeRule()); 
            }
            pushFollow(FOLLOW_ruleCollectionType_in_entryRuleCollectionType6247);
            iv_ruleCollectionType=ruleCollectionType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleCollectionType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleCollectionType6257); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2816:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2819:28: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2820:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2820:1: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2820:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2820:2: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2821:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2821:1: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2822:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2822:1: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            int alt35=3;
            switch ( input.LA(1) ) {
            case 58:
                {
                alt35=1;
                }
                break;
            case 59:
                {
                alt35=2;
                }
                break;
            case 60:
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2823:3: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,58,FOLLOW_58_in_ruleCollectionType6302); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2835:8: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,59,FOLLOW_59_in_ruleCollectionType6331); if (state.failed) return current;
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
                    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2847:8: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,60,FOLLOW_60_in_ruleCollectionType6360); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,61,FOLLOW_61_in_ruleCollectionType6388); if (state.failed) return current;
            if ( state.backtracking==0 ) {

                  	newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
                  
            }
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2866:1: ( (lv_id1_2_0= ruleSimpleType ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2867:1: (lv_id1_2_0= ruleSimpleType )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2867:1: (lv_id1_2_0= ruleSimpleType )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2868:3: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleSimpleType_in_ruleCollectionType6409);
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

            otherlv_3=(Token)match(input,62,FOLLOW_62_in_ruleCollectionType6421); if (state.failed) return current;
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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2896:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2897:2: (iv_ruleSimpleType= ruleSimpleType EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2898:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getSimpleTypeRule()); 
            }
            pushFollow(FOLLOW_ruleSimpleType_in_entryRuleSimpleType6457);
            iv_ruleSimpleType=ruleSimpleType();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleSimpleType; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleType6467); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2905:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;


         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2908:28: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2909:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2909:1: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2909:2: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2909:2: ( (lv_id_0_0= ruleIdentifier ) )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2910:1: (lv_id_0_0= ruleIdentifier )
            {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2910:1: (lv_id_0_0= ruleIdentifier )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2911:3: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType6513);
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

            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2927:2: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==63) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2927:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,63,FOLLOW_63_in_ruleSimpleType6526); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	          
            	    }
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2931:1: ( (lv_id_2_0= ruleIdentifier ) )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2932:1: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2932:1: (lv_id_2_0= ruleIdentifier )
            	    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2933:3: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleIdentifier_in_ruleSimpleType6547);
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
    // $ANTLR end "ruleSimpleType"


    // $ANTLR start "entryRuleIdentifier"
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2957:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2958:2: (iv_ruleIdentifier= ruleIdentifier EOF )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2959:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getIdentifierRule()); 
            }
            pushFollow(FOLLOW_ruleIdentifier_in_entryRuleIdentifier6586);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleIdentifier.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleIdentifier6597); if (state.failed) return current;

            }

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
    // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2966:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;

         enterRule(); 
            
        try {
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2969:28: (this_ID_0= RULE_ID )
            // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:2970:5: this_ID_0= RULE_ID
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleIdentifier6636); if (state.failed) return current;
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

    // $ANTLR start synpred2_InternalExpression
    public final void synpred2_InternalExpression_fragment() throws RecognitionException {   
        EObject this_CastedExpression_1 = null;


        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:100:2: (this_CastedExpression_1= ruleCastedExpression )
        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:100:2: this_CastedExpression_1= ruleCastedExpression
        {
        if ( state.backtracking==0 ) {
           
          	  /* */ 
          	
        }
        pushFollow(FOLLOW_ruleCastedExpression_in_synpred2_InternalExpression171);
        this_CastedExpression_1=ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_InternalExpression

    // $ANTLR start synpred7_InternalExpression
    public final void synpred7_InternalExpression_fragment() throws RecognitionException {   
        Token otherlv_4=null;
        EObject lv_elsePart_5_0 = null;


        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:552:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:552:4: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
        {
        otherlv_4=(Token)match(input,21,FOLLOW_21_in_synpred7_InternalExpression1133); if (state.failed) return ;
        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:556:1: ( (lv_elsePart_5_0= ruleChainedExpression ) )
        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:557:1: (lv_elsePart_5_0= ruleChainedExpression )
        {
        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:557:1: (lv_elsePart_5_0= ruleChainedExpression )
        // ../com.avaloq.tools.ddk.xtext.expression/src-gen/com/avaloq/tools/ddk/xtext/expression/parser/antlr/internal/InternalExpression.g:558:3: lv_elsePart_5_0= ruleChainedExpression
        {
        if ( state.backtracking==0 ) {
           
          	        newCompositeNode(grammarAccess.getIfExpressionKwAccess().getElsePartChainedExpressionParserRuleCall_4_1_0()); 
          	    
        }
        pushFollow(FOLLOW_ruleChainedExpression_in_synpred7_InternalExpression1154);
        lv_elsePart_5_0=ruleChainedExpression();

        state._fsp--;
        if (state.failed) return ;

        }


        }


        }
    }
    // $ANTLR end synpred7_InternalExpression

    // Delegated rules

    public final boolean synpred2_InternalExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_InternalExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred7_InternalExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalExpression_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA1 dfa1 = new DFA1(this);
    static final String DFA1_eotS =
        "\36\uffff";
    static final String DFA1_eofS =
        "\36\uffff";
    static final String DFA1_minS =
        "\1\4\1\uffff\1\0\33\uffff";
    static final String DFA1_maxS =
        "\1\74\1\uffff\1\0\33\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String DFA1_specialS =
        "\2\uffff\1\0\33\uffff}>";
    static final String[] DFA1_transitionS = {
            "\4\3\4\uffff\1\1\2\uffff\1\2\3\uffff\1\3\2\uffff\2\3\15\uffff"+
            "\1\3\2\uffff\1\3\2\uffff\11\3\1\uffff\10\3",
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

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "86:1: (this_LetExpression_0= ruleLetExpression | this_CastedExpression_1= ruleCastedExpression | this_ChainExpression_2= ruleChainExpression )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA1_2 = input.LA(1);

                         
                        int index1_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_InternalExpression()) ) {s = 29;}

                        else if ( (true) ) {s = 3;}

                         
                        input.seek(index1_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 1, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleExpression_in_entryRuleExpression81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_ruleExpression141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_ruleExpression171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_ruleExpression201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLetExpression_in_entryRuleLetExpression238 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLetExpression248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleLetExpression285 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleLetExpression306 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleLetExpression318 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression339 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleLetExpression351 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleLetExpression372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_entryRuleCastedExpression408 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCastedExpression418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleCastedExpression455 = new BitSet(new long[]{0x1C00000000000080L});
    public static final BitSet FOLLOW_ruleType_in_ruleCastedExpression476 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleCastedExpression488 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCastedExpression509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainExpression_in_entryRuleChainExpression545 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainExpression555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression605 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_ruleChainExpression629 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleChainExpression650 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_entryRuleChainedExpression688 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleChainedExpression698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_ruleChainedExpression748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_ruleChainedExpression778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_ruleChainedExpression808 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionTri_in_entryRuleIfExpressionTri843 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionTri853 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleIfExpressionTri903 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleIfExpressionTri927 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri948 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleIfExpressionTri960 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionTri981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIfExpressionKw_in_entryRuleIfExpressionKw1019 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIfExpressionKw1029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleIfExpressionKw1066 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw1087 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleIfExpressionKw1099 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw1120 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleIfExpressionKw1133 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_ruleIfExpressionKw1154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSwitchExpression_in_entryRuleSwitchExpression1192 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSwitchExpression1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleSwitchExpression1239 = new BitSet(new long[]{0x0000000000808000L});
    public static final BitSet FOLLOW_15_in_ruleSwitchExpression1252 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression1273 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleSwitchExpression1285 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleSwitchExpression1299 = new BitSet(new long[]{0x0000000005000000L});
    public static final BitSet FOLLOW_ruleCase_in_ruleSwitchExpression1320 = new BitSet(new long[]{0x0000000005000000L});
    public static final BitSet FOLLOW_24_in_ruleSwitchExpression1333 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleSwitchExpression1345 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleSwitchExpression1366 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleSwitchExpression1378 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCase_in_entryRuleCase1414 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCase1424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleCase1461 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase1482 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleCase1494 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleOrExpression_in_ruleCase1515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOrExpression_in_entryRuleOrExpression1551 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOrExpression1561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression1611 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_ruleOrExpression1641 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleAndExpression_in_ruleOrExpression1675 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_ruleAndExpression_in_entryRuleAndExpression1713 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAndExpression1723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression1773 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_28_in_ruleAndExpression1803 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_ruleAndExpression1837 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_ruleImpliesExpression_in_entryRuleImpliesExpression1875 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImpliesExpression1885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression1935 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_29_in_ruleImpliesExpression1965 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_ruleImpliesExpression1999 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_ruleRelationalExpression_in_entryRuleRelationalExpression2037 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRelationalExpression2047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression2097 = new BitSet(new long[]{0x0000000FC0000002L});
    public static final BitSet FOLLOW_30_in_ruleRelationalExpression2129 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_31_in_ruleRelationalExpression2158 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_32_in_ruleRelationalExpression2187 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_33_in_ruleRelationalExpression2216 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_34_in_ruleRelationalExpression2245 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_35_in_ruleRelationalExpression2274 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_ruleRelationalExpression2311 = new BitSet(new long[]{0x0000000FC0000002L});
    public static final BitSet FOLLOW_ruleAdditiveExpression_in_entryRuleAdditiveExpression2349 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAdditiveExpression2359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression2409 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_36_in_ruleAdditiveExpression2441 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_37_in_ruleAdditiveExpression2470 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_ruleAdditiveExpression2507 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_ruleMultiplicativeExpression_in_entryRuleMultiplicativeExpression2545 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMultiplicativeExpression2555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression2605 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_38_in_ruleMultiplicativeExpression2637 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_39_in_ruleMultiplicativeExpression2666 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_ruleMultiplicativeExpression2703 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_ruleUnaryOrInfixExpression_in_entryRuleUnaryOrInfixExpression2741 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryOrInfixExpression2751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_ruleUnaryOrInfixExpression2801 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryOrInfixExpression2831 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnaryExpression_in_entryRuleUnaryExpression2866 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnaryExpression2876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleUnaryExpression2921 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_37_in_ruleUnaryExpression2950 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_ruleUnaryExpression2987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInfixExpression_in_entryRuleInfixExpression3023 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInfixExpression3033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_ruleInfixExpression3083 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_ruleInfixExpression3108 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression3129 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleInfixExpression3141 = new BitSet(new long[]{0x1FEFF92000C990F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression3163 = new BitSet(new long[]{0x0000040000010000L});
    public static final BitSet FOLLOW_42_in_ruleInfixExpression3176 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression3197 = new BitSet(new long[]{0x0000040000010000L});
    public static final BitSet FOLLOW_16_in_ruleInfixExpression3213 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_ruleInfixExpression3245 = new BitSet(new long[]{0x1C00000000000080L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression3266 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_ruleInfixExpression3298 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_ruleInfixExpression3316 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleInfixExpression3341 = new BitSet(new long[]{0x1C00000000000080L});
    public static final BitSet FOLLOW_ruleType_in_ruleInfixExpression3362 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleInfixExpression3374 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_ruleInfixExpression3406 = new BitSet(new long[]{0x000FF00000000000L});
    public static final BitSet FOLLOW_44_in_ruleInfixExpression3426 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_45_in_ruleInfixExpression3455 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_46_in_ruleInfixExpression3484 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_47_in_ruleInfixExpression3513 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_48_in_ruleInfixExpression3542 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_49_in_ruleInfixExpression3571 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_50_in_ruleInfixExpression3600 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_51_in_ruleInfixExpression3629 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleInfixExpression3657 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleInfixExpression3679 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_ruleInfixExpression3691 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleInfixExpression3714 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleInfixExpression3726 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_rulePrimaryExpression_in_entryRulePrimaryExpression3765 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePrimaryExpression3775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_rulePrimaryExpression3825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_rulePrimaryExpression3855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_rulePrimaryExpression3885 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_rulePrimaryExpression3915 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_rulePrimaryExpression3945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_rulePrimaryExpression3975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleLiteral_in_entryRuleLiteral4010 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleLiteral4020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_ruleLiteral4070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_ruleLiteral4100 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_ruleLiteral4130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_ruleLiteral4160 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_ruleLiteral4190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleBooleanLiteral_in_entryRuleBooleanLiteral4225 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleBooleanLiteral4235 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_ruleBooleanLiteral4279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_ruleBooleanLiteral4308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIntegerLiteral_in_entryRuleIntegerLiteral4359 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIntegerLiteral4369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleIntegerLiteral4410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNullLiteral_in_entryRuleNullLiteral4450 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNullLiteral4460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_ruleNullLiteral4502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRealLiteral_in_entryRuleRealLiteral4550 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRealLiteral4560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_REAL_in_ruleRealLiteral4601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStringLiteral_in_entryRuleStringLiteral4641 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStringLiteral4651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleStringLiteral4692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleParanthesizedExpression_in_entryRuleParanthesizedExpression4732 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParanthesizedExpression4742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleParanthesizedExpression4779 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleParanthesizedExpression4804 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleParanthesizedExpression4815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleGlobalVarExpression_in_entryRuleGlobalVarExpression4851 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleGlobalVarExpression4861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleGlobalVarExpression4898 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleGlobalVarExpression4919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFeatureCall_in_entryRuleFeatureCall4955 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFeatureCall4965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_ruleFeatureCall5015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_ruleFeatureCall5041 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_ruleFeatureCall5072 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_ruleFeatureCall5102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationCall_in_entryRuleOperationCall5137 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationCall5147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleOperationCall5193 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleOperationCall5205 = new BitSet(new long[]{0x1FEFF92000C990F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall5227 = new BitSet(new long[]{0x0000040000010000L});
    public static final BitSet FOLLOW_42_in_ruleOperationCall5240 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleOperationCall5261 = new BitSet(new long[]{0x0000040000010000L});
    public static final BitSet FOLLOW_16_in_ruleOperationCall5277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleListLiteral_in_entryRuleListLiteral5313 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleListLiteral5323 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleListLiteral5372 = new BitSet(new long[]{0x1FEFF92002C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral5394 = new BitSet(new long[]{0x0000040002000000L});
    public static final BitSet FOLLOW_42_in_ruleListLiteral5407 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleListLiteral5428 = new BitSet(new long[]{0x0000040002000000L});
    public static final BitSet FOLLOW_25_in_ruleListLiteral5444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConstructorCallExpression_in_entryRuleConstructorCallExpression5480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConstructorCallExpression5490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleConstructorCallExpression5527 = new BitSet(new long[]{0x1C00000000000080L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleConstructorCallExpression5548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTypeSelectExpression_in_entryRuleTypeSelectExpression5584 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTypeSelectExpression5594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_ruleTypeSelectExpression5637 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleTypeSelectExpression5662 = new BitSet(new long[]{0x1C00000000000080L});
    public static final BitSet FOLLOW_ruleType_in_ruleTypeSelectExpression5683 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTypeSelectExpression5695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionExpression_in_entryRuleCollectionExpression5731 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionExpression5741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_ruleCollectionExpression5786 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_45_in_ruleCollectionExpression5815 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_46_in_ruleCollectionExpression5844 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_47_in_ruleCollectionExpression5873 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_48_in_ruleCollectionExpression5902 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_49_in_ruleCollectionExpression5931 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_50_in_ruleCollectionExpression5960 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_51_in_ruleCollectionExpression5989 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_ruleCollectionExpression6017 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleCollectionExpression6039 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_52_in_ruleCollectionExpression6051 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleExpression_in_ruleCollectionExpression6074 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleCollectionExpression6086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType6122 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType6132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_ruleType6182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleType6212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCollectionType_in_entryRuleCollectionType6247 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCollectionType6257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_ruleCollectionType6302 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_59_in_ruleCollectionType6331 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_60_in_ruleCollectionType6360 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_ruleCollectionType6388 = new BitSet(new long[]{0x1C00000000000080L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleCollectionType6409 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_ruleCollectionType6421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_entryRuleSimpleType6457 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleType6467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType6513 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_63_in_ruleSimpleType6526 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleIdentifier_in_ruleSimpleType6547 = new BitSet(new long[]{0x8000000000000002L});
    public static final BitSet FOLLOW_ruleIdentifier_in_entryRuleIdentifier6586 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleIdentifier6597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleIdentifier6636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCastedExpression_in_synpred2_InternalExpression171 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred7_InternalExpression1133 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_ruleChainedExpression_in_synpred7_InternalExpression1154 = new BitSet(new long[]{0x0000000000000002L});

}