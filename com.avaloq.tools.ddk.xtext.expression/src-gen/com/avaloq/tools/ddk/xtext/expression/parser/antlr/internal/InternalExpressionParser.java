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
    public String getGrammarFileName() { return "InternalExpression.g"; }



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
    // InternalExpression.g:64:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalExpression.g:64:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalExpression.g:65:2: iv_ruleExpression= ruleExpression EOF
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
    // InternalExpression.g:71:1: ruleExpression returns [EObject current=null] : (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_LetExpression_0 = null;

        EObject this_CastedExpression_1 = null;

        EObject this_ChainExpression_2 = null;



        	enterRule();

        try {
            // InternalExpression.g:77:2: ( (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression ) )
            // InternalExpression.g:78:2: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            {
            // InternalExpression.g:78:2: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )
            int alt1=3;
            alt1 = dfa1.predict(input);
            switch (alt1) {
                case 1 :
                    // InternalExpression.g:79:3: this_LetExpression_0= ruleLetExpression
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
                    // InternalExpression.g:88:3: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    {
                    // InternalExpression.g:88:3: ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression )
                    // InternalExpression.g:89:4: ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression
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
                    // InternalExpression.g:100:3: this_ChainExpression_2= ruleChainExpression
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
    // InternalExpression.g:112:1: entryRuleLetExpression returns [EObject current=null] : iv_ruleLetExpression= ruleLetExpression EOF ;
    public final EObject entryRuleLetExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLetExpression = null;


        try {
            // InternalExpression.g:112:54: (iv_ruleLetExpression= ruleLetExpression EOF )
            // InternalExpression.g:113:2: iv_ruleLetExpression= ruleLetExpression EOF
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
    // InternalExpression.g:119:1: ruleLetExpression returns [EObject current=null] : (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) ;
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
            // InternalExpression.g:125:2: ( (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) ) )
            // InternalExpression.g:126:2: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            {
            // InternalExpression.g:126:2: (otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) ) )
            // InternalExpression.g:127:3: otherlv_0= 'let' ( (lv_identifier_1_0= ruleIdentifier ) ) otherlv_2= '=' ( (lv_varExpr_3_0= ruleExpression ) ) otherlv_4= ':' ( (lv_target_5_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,12,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getLetExpressionAccess().getLetKeyword_0());
              		
            }
            // InternalExpression.g:131:3: ( (lv_identifier_1_0= ruleIdentifier ) )
            // InternalExpression.g:132:4: (lv_identifier_1_0= ruleIdentifier )
            {
            // InternalExpression.g:132:4: (lv_identifier_1_0= ruleIdentifier )
            // InternalExpression.g:133:5: lv_identifier_1_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLetExpressionAccess().getIdentifierIdentifierParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_4);
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

            otherlv_2=(Token)match(input,13,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getLetExpressionAccess().getEqualsSignKeyword_2());
              		
            }
            // InternalExpression.g:154:3: ( (lv_varExpr_3_0= ruleExpression ) )
            // InternalExpression.g:155:4: (lv_varExpr_3_0= ruleExpression )
            {
            // InternalExpression.g:155:4: (lv_varExpr_3_0= ruleExpression )
            // InternalExpression.g:156:5: lv_varExpr_3_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getLetExpressionAccess().getVarExprExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_6);
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

            otherlv_4=(Token)match(input,14,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getLetExpressionAccess().getColonKeyword_4());
              		
            }
            // InternalExpression.g:177:3: ( (lv_target_5_0= ruleExpression ) )
            // InternalExpression.g:178:4: (lv_target_5_0= ruleExpression )
            {
            // InternalExpression.g:178:4: (lv_target_5_0= ruleExpression )
            // InternalExpression.g:179:5: lv_target_5_0= ruleExpression
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
    // InternalExpression.g:200:1: entryRuleCastedExpression returns [EObject current=null] : iv_ruleCastedExpression= ruleCastedExpression EOF ;
    public final EObject entryRuleCastedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCastedExpression = null;


        try {
            // InternalExpression.g:200:57: (iv_ruleCastedExpression= ruleCastedExpression EOF )
            // InternalExpression.g:201:2: iv_ruleCastedExpression= ruleCastedExpression EOF
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
    // InternalExpression.g:207:1: ruleCastedExpression returns [EObject current=null] : (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) ;
    public final EObject ruleCastedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_type_1_0 = null;

        EObject lv_target_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:213:2: ( (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) ) )
            // InternalExpression.g:214:2: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            {
            // InternalExpression.g:214:2: (otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) ) )
            // InternalExpression.g:215:3: otherlv_0= '(' ( (lv_type_1_0= ruleType ) ) otherlv_2= ')' ( (lv_target_3_0= ruleExpression ) )
            {
            otherlv_0=(Token)match(input,15,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getCastedExpressionAccess().getLeftParenthesisKeyword_0());
              		
            }
            // InternalExpression.g:219:3: ( (lv_type_1_0= ruleType ) )
            // InternalExpression.g:220:4: (lv_type_1_0= ruleType )
            {
            // InternalExpression.g:220:4: (lv_type_1_0= ruleType )
            // InternalExpression.g:221:5: lv_type_1_0= ruleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCastedExpressionAccess().getTypeTypeParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_8);
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

            otherlv_2=(Token)match(input,16,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getCastedExpressionAccess().getRightParenthesisKeyword_2());
              		
            }
            // InternalExpression.g:242:3: ( (lv_target_3_0= ruleExpression ) )
            // InternalExpression.g:243:4: (lv_target_3_0= ruleExpression )
            {
            // InternalExpression.g:243:4: (lv_target_3_0= ruleExpression )
            // InternalExpression.g:244:5: lv_target_3_0= ruleExpression
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
    // InternalExpression.g:265:1: entryRuleChainExpression returns [EObject current=null] : iv_ruleChainExpression= ruleChainExpression EOF ;
    public final EObject entryRuleChainExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainExpression = null;


        try {
            // InternalExpression.g:265:56: (iv_ruleChainExpression= ruleChainExpression EOF )
            // InternalExpression.g:266:2: iv_ruleChainExpression= ruleChainExpression EOF
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
    // InternalExpression.g:272:1: ruleChainExpression returns [EObject current=null] : (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) ;
    public final EObject ruleChainExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_ChainedExpression_0 = null;

        EObject lv_next_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:278:2: ( (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* ) )
            // InternalExpression.g:279:2: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            {
            // InternalExpression.g:279:2: (this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )* )
            // InternalExpression.g:280:3: this_ChainedExpression_0= ruleChainedExpression ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getChainExpressionAccess().getChainedExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_9);
            this_ChainedExpression_0=ruleChainedExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_ChainedExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:288:3: ( () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==17) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalExpression.g:289:4: () otherlv_2= '->' ( (lv_next_3_0= ruleChainedExpression ) )
            	    {
            	    // InternalExpression.g:289:4: ()
            	    // InternalExpression.g:290:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getChainExpressionAccess().getChainExpressionFirstAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    otherlv_2=(Token)match(input,17,FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_2, grammarAccess.getChainExpressionAccess().getHyphenMinusGreaterThanSignKeyword_1_1());
            	      			
            	    }
            	    // InternalExpression.g:300:4: ( (lv_next_3_0= ruleChainedExpression ) )
            	    // InternalExpression.g:301:5: (lv_next_3_0= ruleChainedExpression )
            	    {
            	    // InternalExpression.g:301:5: (lv_next_3_0= ruleChainedExpression )
            	    // InternalExpression.g:302:6: lv_next_3_0= ruleChainedExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getChainExpressionAccess().getNextChainedExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_9);
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
    // InternalExpression.g:324:1: entryRuleChainedExpression returns [EObject current=null] : iv_ruleChainedExpression= ruleChainedExpression EOF ;
    public final EObject entryRuleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleChainedExpression = null;


        try {
            // InternalExpression.g:324:58: (iv_ruleChainedExpression= ruleChainedExpression EOF )
            // InternalExpression.g:325:2: iv_ruleChainedExpression= ruleChainedExpression EOF
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
    // InternalExpression.g:331:1: ruleChainedExpression returns [EObject current=null] : (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) ;
    public final EObject ruleChainedExpression() throws RecognitionException {
        EObject current = null;

        EObject this_IfExpressionKw_0 = null;

        EObject this_IfExpressionTri_1 = null;

        EObject this_SwitchExpression_2 = null;



        	enterRule();

        try {
            // InternalExpression.g:337:2: ( (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression ) )
            // InternalExpression.g:338:2: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
            {
            // InternalExpression.g:338:2: (this_IfExpressionKw_0= ruleIfExpressionKw | this_IfExpressionTri_1= ruleIfExpressionTri | this_SwitchExpression_2= ruleSwitchExpression )
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
                    // InternalExpression.g:339:3: this_IfExpressionKw_0= ruleIfExpressionKw
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
                    // InternalExpression.g:348:3: this_IfExpressionTri_1= ruleIfExpressionTri
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
                    // InternalExpression.g:357:3: this_SwitchExpression_2= ruleSwitchExpression
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
    // InternalExpression.g:369:1: entryRuleIfExpressionTri returns [EObject current=null] : iv_ruleIfExpressionTri= ruleIfExpressionTri EOF ;
    public final EObject entryRuleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionTri = null;


        try {
            // InternalExpression.g:369:56: (iv_ruleIfExpressionTri= ruleIfExpressionTri EOF )
            // InternalExpression.g:370:2: iv_ruleIfExpressionTri= ruleIfExpressionTri EOF
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
    // InternalExpression.g:376:1: ruleIfExpressionTri returns [EObject current=null] : (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) ;
    public final EObject ruleIfExpressionTri() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_OrExpression_0 = null;

        EObject lv_thenPart_3_0 = null;

        EObject lv_elsePart_5_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:382:2: ( (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? ) )
            // InternalExpression.g:383:2: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            {
            // InternalExpression.g:383:2: (this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )? )
            // InternalExpression.g:384:3: this_OrExpression_0= ruleOrExpression ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getIfExpressionTriAccess().getOrExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_10);
            this_OrExpression_0=ruleOrExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_OrExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:392:3: ( () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==18) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalExpression.g:393:4: () otherlv_2= '?' ( (lv_thenPart_3_0= ruleChainedExpression ) ) otherlv_4= ':' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    // InternalExpression.g:393:4: ()
                    // InternalExpression.g:394:5: 
                    {
                    if ( state.backtracking==0 ) {

                      					current = forceCreateModelElementAndSet(
                      						grammarAccess.getIfExpressionTriAccess().getIfExpressionConditionAction_1_0(),
                      						current);
                      				
                    }

                    }

                    otherlv_2=(Token)match(input,18,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_2, grammarAccess.getIfExpressionTriAccess().getQuestionMarkKeyword_1_1());
                      			
                    }
                    // InternalExpression.g:404:4: ( (lv_thenPart_3_0= ruleChainedExpression ) )
                    // InternalExpression.g:405:5: (lv_thenPart_3_0= ruleChainedExpression )
                    {
                    // InternalExpression.g:405:5: (lv_thenPart_3_0= ruleChainedExpression )
                    // InternalExpression.g:406:6: lv_thenPart_3_0= ruleChainedExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getIfExpressionTriAccess().getThenPartChainedExpressionParserRuleCall_1_2_0());
                      					
                    }
                    pushFollow(FOLLOW_6);
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

                    otherlv_4=(Token)match(input,14,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_4, grammarAccess.getIfExpressionTriAccess().getColonKeyword_1_3());
                      			
                    }
                    // InternalExpression.g:427:4: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // InternalExpression.g:428:5: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // InternalExpression.g:428:5: (lv_elsePart_5_0= ruleChainedExpression )
                    // InternalExpression.g:429:6: lv_elsePart_5_0= ruleChainedExpression
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
    // InternalExpression.g:451:1: entryRuleIfExpressionKw returns [EObject current=null] : iv_ruleIfExpressionKw= ruleIfExpressionKw EOF ;
    public final EObject entryRuleIfExpressionKw() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpressionKw = null;


        try {
            // InternalExpression.g:451:55: (iv_ruleIfExpressionKw= ruleIfExpressionKw EOF )
            // InternalExpression.g:452:2: iv_ruleIfExpressionKw= ruleIfExpressionKw EOF
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
    // InternalExpression.g:458:1: ruleIfExpressionKw returns [EObject current=null] : (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) ;
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
            // InternalExpression.g:464:2: ( (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? ) )
            // InternalExpression.g:465:2: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            {
            // InternalExpression.g:465:2: (otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )? )
            // InternalExpression.g:466:3: otherlv_0= 'if' ( (lv_condition_1_0= ruleChainedExpression ) ) otherlv_2= 'then' ( (lv_thenPart_3_0= ruleChainedExpression ) ) ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            {
            otherlv_0=(Token)match(input,19,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getIfExpressionKwAccess().getIfKeyword_0());
              		
            }
            // InternalExpression.g:470:3: ( (lv_condition_1_0= ruleChainedExpression ) )
            // InternalExpression.g:471:4: (lv_condition_1_0= ruleChainedExpression )
            {
            // InternalExpression.g:471:4: (lv_condition_1_0= ruleChainedExpression )
            // InternalExpression.g:472:5: lv_condition_1_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getIfExpressionKwAccess().getConditionChainedExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_11);
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

            otherlv_2=(Token)match(input,20,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getIfExpressionKwAccess().getThenKeyword_2());
              		
            }
            // InternalExpression.g:493:3: ( (lv_thenPart_3_0= ruleChainedExpression ) )
            // InternalExpression.g:494:4: (lv_thenPart_3_0= ruleChainedExpression )
            {
            // InternalExpression.g:494:4: (lv_thenPart_3_0= ruleChainedExpression )
            // InternalExpression.g:495:5: lv_thenPart_3_0= ruleChainedExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getIfExpressionKwAccess().getThenPartChainedExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_12);
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

            // InternalExpression.g:512:3: ( ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==21) ) {
                int LA5_1 = input.LA(2);

                if ( (synpred2_InternalExpression()) ) {
                    alt5=1;
                }
            }
            switch (alt5) {
                case 1 :
                    // InternalExpression.g:513:4: ( 'else' )=> (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    {
                    // InternalExpression.g:514:4: (otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) ) )
                    // InternalExpression.g:515:5: otherlv_4= 'else' ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    {
                    otherlv_4=(Token)match(input,21,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      					newLeafNode(otherlv_4, grammarAccess.getIfExpressionKwAccess().getElseKeyword_4_0_0());
                      				
                    }
                    // InternalExpression.g:519:5: ( (lv_elsePart_5_0= ruleChainedExpression ) )
                    // InternalExpression.g:520:6: (lv_elsePart_5_0= ruleChainedExpression )
                    {
                    // InternalExpression.g:520:6: (lv_elsePart_5_0= ruleChainedExpression )
                    // InternalExpression.g:521:7: lv_elsePart_5_0= ruleChainedExpression
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
    // InternalExpression.g:544:1: entryRuleSwitchExpression returns [EObject current=null] : iv_ruleSwitchExpression= ruleSwitchExpression EOF ;
    public final EObject entryRuleSwitchExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSwitchExpression = null;


        try {
            // InternalExpression.g:544:57: (iv_ruleSwitchExpression= ruleSwitchExpression EOF )
            // InternalExpression.g:545:2: iv_ruleSwitchExpression= ruleSwitchExpression EOF
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
    // InternalExpression.g:551:1: ruleSwitchExpression returns [EObject current=null] : (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) ;
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
            // InternalExpression.g:557:2: ( (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' ) )
            // InternalExpression.g:558:2: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            {
            // InternalExpression.g:558:2: (otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}' )
            // InternalExpression.g:559:3: otherlv_0= 'switch' (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )? otherlv_4= '{' ( (lv_case_5_0= ruleCase ) )* otherlv_6= 'default' otherlv_7= ':' ( (lv_defaultExpr_8_0= ruleOrExpression ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,22,FOLLOW_13); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getSwitchExpressionAccess().getSwitchKeyword_0());
              		
            }
            // InternalExpression.g:563:3: (otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalExpression.g:564:4: otherlv_1= '(' ( (lv_switchExpr_2_0= ruleOrExpression ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,15,FOLLOW_14); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_1, grammarAccess.getSwitchExpressionAccess().getLeftParenthesisKeyword_1_0());
                      			
                    }
                    // InternalExpression.g:568:4: ( (lv_switchExpr_2_0= ruleOrExpression ) )
                    // InternalExpression.g:569:5: (lv_switchExpr_2_0= ruleOrExpression )
                    {
                    // InternalExpression.g:569:5: (lv_switchExpr_2_0= ruleOrExpression )
                    // InternalExpression.g:570:6: lv_switchExpr_2_0= ruleOrExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getSwitchExpressionAccess().getSwitchExprOrExpressionParserRuleCall_1_1_0());
                      					
                    }
                    pushFollow(FOLLOW_8);
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

                    otherlv_3=(Token)match(input,16,FOLLOW_15); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getSwitchExpressionAccess().getRightParenthesisKeyword_1_2());
                      			
                    }

                    }
                    break;

            }

            otherlv_4=(Token)match(input,23,FOLLOW_16); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_4, grammarAccess.getSwitchExpressionAccess().getLeftCurlyBracketKeyword_2());
              		
            }
            // InternalExpression.g:596:3: ( (lv_case_5_0= ruleCase ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==26) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalExpression.g:597:4: (lv_case_5_0= ruleCase )
            	    {
            	    // InternalExpression.g:597:4: (lv_case_5_0= ruleCase )
            	    // InternalExpression.g:598:5: lv_case_5_0= ruleCase
            	    {
            	    if ( state.backtracking==0 ) {

            	      					newCompositeNode(grammarAccess.getSwitchExpressionAccess().getCaseCaseParserRuleCall_3_0());
            	      				
            	    }
            	    pushFollow(FOLLOW_16);
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
            	    break loop7;
                }
            } while (true);

            otherlv_6=(Token)match(input,24,FOLLOW_6); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_6, grammarAccess.getSwitchExpressionAccess().getDefaultKeyword_4());
              		
            }
            otherlv_7=(Token)match(input,14,FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_7, grammarAccess.getSwitchExpressionAccess().getColonKeyword_5());
              		
            }
            // InternalExpression.g:623:3: ( (lv_defaultExpr_8_0= ruleOrExpression ) )
            // InternalExpression.g:624:4: (lv_defaultExpr_8_0= ruleOrExpression )
            {
            // InternalExpression.g:624:4: (lv_defaultExpr_8_0= ruleOrExpression )
            // InternalExpression.g:625:5: lv_defaultExpr_8_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSwitchExpressionAccess().getDefaultExprOrExpressionParserRuleCall_6_0());
              				
            }
            pushFollow(FOLLOW_17);
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

            otherlv_9=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:650:1: entryRuleCase returns [EObject current=null] : iv_ruleCase= ruleCase EOF ;
    public final EObject entryRuleCase() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCase = null;


        try {
            // InternalExpression.g:650:45: (iv_ruleCase= ruleCase EOF )
            // InternalExpression.g:651:2: iv_ruleCase= ruleCase EOF
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
    // InternalExpression.g:657:1: ruleCase returns [EObject current=null] : (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) ;
    public final EObject ruleCase() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject lv_condition_1_0 = null;

        EObject lv_thenPar_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:663:2: ( (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) ) )
            // InternalExpression.g:664:2: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            {
            // InternalExpression.g:664:2: (otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) ) )
            // InternalExpression.g:665:3: otherlv_0= 'case' ( (lv_condition_1_0= ruleOrExpression ) ) otherlv_2= ':' ( (lv_thenPar_3_0= ruleOrExpression ) )
            {
            otherlv_0=(Token)match(input,26,FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getCaseAccess().getCaseKeyword_0());
              		
            }
            // InternalExpression.g:669:3: ( (lv_condition_1_0= ruleOrExpression ) )
            // InternalExpression.g:670:4: (lv_condition_1_0= ruleOrExpression )
            {
            // InternalExpression.g:670:4: (lv_condition_1_0= ruleOrExpression )
            // InternalExpression.g:671:5: lv_condition_1_0= ruleOrExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCaseAccess().getConditionOrExpressionParserRuleCall_1_0());
              				
            }
            pushFollow(FOLLOW_6);
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

            otherlv_2=(Token)match(input,14,FOLLOW_14); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_2, grammarAccess.getCaseAccess().getColonKeyword_2());
              		
            }
            // InternalExpression.g:692:3: ( (lv_thenPar_3_0= ruleOrExpression ) )
            // InternalExpression.g:693:4: (lv_thenPar_3_0= ruleOrExpression )
            {
            // InternalExpression.g:693:4: (lv_thenPar_3_0= ruleOrExpression )
            // InternalExpression.g:694:5: lv_thenPar_3_0= ruleOrExpression
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
    // InternalExpression.g:715:1: entryRuleOrExpression returns [EObject current=null] : iv_ruleOrExpression= ruleOrExpression EOF ;
    public final EObject entryRuleOrExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrExpression = null;


        try {
            // InternalExpression.g:715:53: (iv_ruleOrExpression= ruleOrExpression EOF )
            // InternalExpression.g:716:2: iv_ruleOrExpression= ruleOrExpression EOF
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
    // InternalExpression.g:722:1: ruleOrExpression returns [EObject current=null] : (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) ;
    public final EObject ruleOrExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_AndExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:728:2: ( (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* ) )
            // InternalExpression.g:729:2: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            {
            // InternalExpression.g:729:2: (this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )* )
            // InternalExpression.g:730:3: this_AndExpression_0= ruleAndExpression ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getOrExpressionAccess().getAndExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_18);
            this_AndExpression_0=ruleAndExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_AndExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:738:3: ( () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==27) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalExpression.g:739:4: () ( (lv_operator_2_0= '||' ) ) ( (lv_right_3_0= ruleAndExpression ) )
            	    {
            	    // InternalExpression.g:739:4: ()
            	    // InternalExpression.g:740:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getOrExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExpression.g:746:4: ( (lv_operator_2_0= '||' ) )
            	    // InternalExpression.g:747:5: (lv_operator_2_0= '||' )
            	    {
            	    // InternalExpression.g:747:5: (lv_operator_2_0= '||' )
            	    // InternalExpression.g:748:6: lv_operator_2_0= '||'
            	    {
            	    lv_operator_2_0=(Token)match(input,27,FOLLOW_14); if (state.failed) return current;
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

            	    // InternalExpression.g:760:4: ( (lv_right_3_0= ruleAndExpression ) )
            	    // InternalExpression.g:761:5: (lv_right_3_0= ruleAndExpression )
            	    {
            	    // InternalExpression.g:761:5: (lv_right_3_0= ruleAndExpression )
            	    // InternalExpression.g:762:6: lv_right_3_0= ruleAndExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getOrExpressionAccess().getRightAndExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_18);
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
    // InternalExpression.g:784:1: entryRuleAndExpression returns [EObject current=null] : iv_ruleAndExpression= ruleAndExpression EOF ;
    public final EObject entryRuleAndExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndExpression = null;


        try {
            // InternalExpression.g:784:54: (iv_ruleAndExpression= ruleAndExpression EOF )
            // InternalExpression.g:785:2: iv_ruleAndExpression= ruleAndExpression EOF
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
    // InternalExpression.g:791:1: ruleAndExpression returns [EObject current=null] : (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) ;
    public final EObject ruleAndExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_ImpliesExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:797:2: ( (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* ) )
            // InternalExpression.g:798:2: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            {
            // InternalExpression.g:798:2: (this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )* )
            // InternalExpression.g:799:3: this_ImpliesExpression_0= ruleImpliesExpression ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getAndExpressionAccess().getImpliesExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_19);
            this_ImpliesExpression_0=ruleImpliesExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_ImpliesExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:807:3: ( () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==28) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalExpression.g:808:4: () ( (lv_operator_2_0= '&&' ) ) ( (lv_right_3_0= ruleImpliesExpression ) )
            	    {
            	    // InternalExpression.g:808:4: ()
            	    // InternalExpression.g:809:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getAndExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExpression.g:815:4: ( (lv_operator_2_0= '&&' ) )
            	    // InternalExpression.g:816:5: (lv_operator_2_0= '&&' )
            	    {
            	    // InternalExpression.g:816:5: (lv_operator_2_0= '&&' )
            	    // InternalExpression.g:817:6: lv_operator_2_0= '&&'
            	    {
            	    lv_operator_2_0=(Token)match(input,28,FOLLOW_14); if (state.failed) return current;
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

            	    // InternalExpression.g:829:4: ( (lv_right_3_0= ruleImpliesExpression ) )
            	    // InternalExpression.g:830:5: (lv_right_3_0= ruleImpliesExpression )
            	    {
            	    // InternalExpression.g:830:5: (lv_right_3_0= ruleImpliesExpression )
            	    // InternalExpression.g:831:6: lv_right_3_0= ruleImpliesExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAndExpressionAccess().getRightImpliesExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_19);
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
    // InternalExpression.g:853:1: entryRuleImpliesExpression returns [EObject current=null] : iv_ruleImpliesExpression= ruleImpliesExpression EOF ;
    public final EObject entryRuleImpliesExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImpliesExpression = null;


        try {
            // InternalExpression.g:853:58: (iv_ruleImpliesExpression= ruleImpliesExpression EOF )
            // InternalExpression.g:854:2: iv_ruleImpliesExpression= ruleImpliesExpression EOF
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
    // InternalExpression.g:860:1: ruleImpliesExpression returns [EObject current=null] : (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) ;
    public final EObject ruleImpliesExpression() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_0=null;
        EObject this_RelationalExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:866:2: ( (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* ) )
            // InternalExpression.g:867:2: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            {
            // InternalExpression.g:867:2: (this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )* )
            // InternalExpression.g:868:3: this_RelationalExpression_0= ruleRelationalExpression ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRelationalExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_20);
            this_RelationalExpression_0=ruleRelationalExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_RelationalExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:876:3: ( () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==29) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalExpression.g:877:4: () ( (lv_operator_2_0= 'implies' ) ) ( (lv_right_3_0= ruleRelationalExpression ) )
            	    {
            	    // InternalExpression.g:877:4: ()
            	    // InternalExpression.g:878:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getImpliesExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExpression.g:884:4: ( (lv_operator_2_0= 'implies' ) )
            	    // InternalExpression.g:885:5: (lv_operator_2_0= 'implies' )
            	    {
            	    // InternalExpression.g:885:5: (lv_operator_2_0= 'implies' )
            	    // InternalExpression.g:886:6: lv_operator_2_0= 'implies'
            	    {
            	    lv_operator_2_0=(Token)match(input,29,FOLLOW_14); if (state.failed) return current;
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

            	    // InternalExpression.g:898:4: ( (lv_right_3_0= ruleRelationalExpression ) )
            	    // InternalExpression.g:899:5: (lv_right_3_0= ruleRelationalExpression )
            	    {
            	    // InternalExpression.g:899:5: (lv_right_3_0= ruleRelationalExpression )
            	    // InternalExpression.g:900:6: lv_right_3_0= ruleRelationalExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getImpliesExpressionAccess().getRightRelationalExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_20);
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
    // InternalExpression.g:922:1: entryRuleRelationalExpression returns [EObject current=null] : iv_ruleRelationalExpression= ruleRelationalExpression EOF ;
    public final EObject entryRuleRelationalExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationalExpression = null;


        try {
            // InternalExpression.g:922:61: (iv_ruleRelationalExpression= ruleRelationalExpression EOF )
            // InternalExpression.g:923:2: iv_ruleRelationalExpression= ruleRelationalExpression EOF
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
    // InternalExpression.g:929:1: ruleRelationalExpression returns [EObject current=null] : (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) ;
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
            // InternalExpression.g:935:2: ( (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* ) )
            // InternalExpression.g:936:2: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            {
            // InternalExpression.g:936:2: (this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )* )
            // InternalExpression.g:937:3: this_AdditiveExpression_0= ruleAdditiveExpression ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getRelationalExpressionAccess().getAdditiveExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_21);
            this_AdditiveExpression_0=ruleAdditiveExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_AdditiveExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:945:3: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=30 && LA12_0<=35)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalExpression.g:946:4: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) ) ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    {
            	    // InternalExpression.g:946:4: ()
            	    // InternalExpression.g:947:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndSet(
            	      						grammarAccess.getRelationalExpressionAccess().getBooleanOperationLeftAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExpression.g:953:4: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) ) )
            	    // InternalExpression.g:954:5: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    {
            	    // InternalExpression.g:954:5: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' ) )
            	    // InternalExpression.g:955:6: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
            	    {
            	    // InternalExpression.g:955:6: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' | lv_operator_2_3= '>=' | lv_operator_2_4= '<=' | lv_operator_2_5= '>' | lv_operator_2_6= '<' )
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
            	            // InternalExpression.g:956:7: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,30,FOLLOW_14); if (state.failed) return current;
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
            	            // InternalExpression.g:967:7: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,31,FOLLOW_14); if (state.failed) return current;
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
            	            // InternalExpression.g:978:7: lv_operator_2_3= '>='
            	            {
            	            lv_operator_2_3=(Token)match(input,32,FOLLOW_14); if (state.failed) return current;
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
            	            // InternalExpression.g:989:7: lv_operator_2_4= '<='
            	            {
            	            lv_operator_2_4=(Token)match(input,33,FOLLOW_14); if (state.failed) return current;
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
            	            // InternalExpression.g:1000:7: lv_operator_2_5= '>'
            	            {
            	            lv_operator_2_5=(Token)match(input,34,FOLLOW_14); if (state.failed) return current;
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
            	            // InternalExpression.g:1011:7: lv_operator_2_6= '<'
            	            {
            	            lv_operator_2_6=(Token)match(input,35,FOLLOW_14); if (state.failed) return current;
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

            	    // InternalExpression.g:1024:4: ( (lv_right_3_0= ruleAdditiveExpression ) )
            	    // InternalExpression.g:1025:5: (lv_right_3_0= ruleAdditiveExpression )
            	    {
            	    // InternalExpression.g:1025:5: (lv_right_3_0= ruleAdditiveExpression )
            	    // InternalExpression.g:1026:6: lv_right_3_0= ruleAdditiveExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getRelationalExpressionAccess().getRightAdditiveExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_21);
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
    // InternalExpression.g:1048:1: entryRuleAdditiveExpression returns [EObject current=null] : iv_ruleAdditiveExpression= ruleAdditiveExpression EOF ;
    public final EObject entryRuleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAdditiveExpression = null;


        try {
            // InternalExpression.g:1048:59: (iv_ruleAdditiveExpression= ruleAdditiveExpression EOF )
            // InternalExpression.g:1049:2: iv_ruleAdditiveExpression= ruleAdditiveExpression EOF
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
    // InternalExpression.g:1055:1: ruleAdditiveExpression returns [EObject current=null] : (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) ;
    public final EObject ruleAdditiveExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_MultiplicativeExpression_0 = null;

        EObject lv_params_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:1061:2: ( (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* ) )
            // InternalExpression.g:1062:2: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            {
            // InternalExpression.g:1062:2: (this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )* )
            // InternalExpression.g:1063:3: this_MultiplicativeExpression_0= ruleMultiplicativeExpression ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getMultiplicativeExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_22);
            this_MultiplicativeExpression_0=ruleMultiplicativeExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_MultiplicativeExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:1071:3: ( () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=36 && LA14_0<=37)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalExpression.g:1072:4: () ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) ) ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    {
            	    // InternalExpression.g:1072:4: ()
            	    // InternalExpression.g:1073:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndAdd(
            	      						grammarAccess.getAdditiveExpressionAccess().getOperationCallParamsAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExpression.g:1079:4: ( ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) ) )
            	    // InternalExpression.g:1080:5: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    {
            	    // InternalExpression.g:1080:5: ( (lv_name_2_1= '+' | lv_name_2_2= '-' ) )
            	    // InternalExpression.g:1081:6: (lv_name_2_1= '+' | lv_name_2_2= '-' )
            	    {
            	    // InternalExpression.g:1081:6: (lv_name_2_1= '+' | lv_name_2_2= '-' )
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
            	            // InternalExpression.g:1082:7: lv_name_2_1= '+'
            	            {
            	            lv_name_2_1=(Token)match(input,36,FOLLOW_14); if (state.failed) return current;
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
            	            // InternalExpression.g:1093:7: lv_name_2_2= '-'
            	            {
            	            lv_name_2_2=(Token)match(input,37,FOLLOW_14); if (state.failed) return current;
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

            	    // InternalExpression.g:1106:4: ( (lv_params_3_0= ruleMultiplicativeExpression ) )
            	    // InternalExpression.g:1107:5: (lv_params_3_0= ruleMultiplicativeExpression )
            	    {
            	    // InternalExpression.g:1107:5: (lv_params_3_0= ruleMultiplicativeExpression )
            	    // InternalExpression.g:1108:6: lv_params_3_0= ruleMultiplicativeExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getAdditiveExpressionAccess().getParamsMultiplicativeExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_22);
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
    // InternalExpression.g:1130:1: entryRuleMultiplicativeExpression returns [EObject current=null] : iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF ;
    public final EObject entryRuleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplicativeExpression = null;


        try {
            // InternalExpression.g:1130:65: (iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF )
            // InternalExpression.g:1131:2: iv_ruleMultiplicativeExpression= ruleMultiplicativeExpression EOF
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
    // InternalExpression.g:1137:1: ruleMultiplicativeExpression returns [EObject current=null] : (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) ;
    public final EObject ruleMultiplicativeExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_2_1=null;
        Token lv_name_2_2=null;
        EObject this_UnaryOrInfixExpression_0 = null;

        EObject lv_params_3_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:1143:2: ( (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* ) )
            // InternalExpression.g:1144:2: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            {
            // InternalExpression.g:1144:2: (this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )* )
            // InternalExpression.g:1145:3: this_UnaryOrInfixExpression_0= ruleUnaryOrInfixExpression ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getUnaryOrInfixExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_23);
            this_UnaryOrInfixExpression_0=ruleUnaryOrInfixExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_UnaryOrInfixExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:1153:3: ( () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=38 && LA16_0<=39)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalExpression.g:1154:4: () ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) ) ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    {
            	    // InternalExpression.g:1154:4: ()
            	    // InternalExpression.g:1155:5: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      					current = forceCreateModelElementAndAdd(
            	      						grammarAccess.getMultiplicativeExpressionAccess().getOperationCallParamsAction_1_0(),
            	      						current);
            	      				
            	    }

            	    }

            	    // InternalExpression.g:1161:4: ( ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) ) )
            	    // InternalExpression.g:1162:5: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    {
            	    // InternalExpression.g:1162:5: ( (lv_name_2_1= '*' | lv_name_2_2= '/' ) )
            	    // InternalExpression.g:1163:6: (lv_name_2_1= '*' | lv_name_2_2= '/' )
            	    {
            	    // InternalExpression.g:1163:6: (lv_name_2_1= '*' | lv_name_2_2= '/' )
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
            	            // InternalExpression.g:1164:7: lv_name_2_1= '*'
            	            {
            	            lv_name_2_1=(Token)match(input,38,FOLLOW_14); if (state.failed) return current;
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
            	            // InternalExpression.g:1175:7: lv_name_2_2= '/'
            	            {
            	            lv_name_2_2=(Token)match(input,39,FOLLOW_14); if (state.failed) return current;
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

            	    // InternalExpression.g:1188:4: ( (lv_params_3_0= ruleUnaryOrInfixExpression ) )
            	    // InternalExpression.g:1189:5: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    {
            	    // InternalExpression.g:1189:5: (lv_params_3_0= ruleUnaryOrInfixExpression )
            	    // InternalExpression.g:1190:6: lv_params_3_0= ruleUnaryOrInfixExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getMultiplicativeExpressionAccess().getParamsUnaryOrInfixExpressionParserRuleCall_1_2_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_23);
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
    // InternalExpression.g:1212:1: entryRuleUnaryOrInfixExpression returns [EObject current=null] : iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF ;
    public final EObject entryRuleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryOrInfixExpression = null;


        try {
            // InternalExpression.g:1212:63: (iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF )
            // InternalExpression.g:1213:2: iv_ruleUnaryOrInfixExpression= ruleUnaryOrInfixExpression EOF
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
    // InternalExpression.g:1219:1: ruleUnaryOrInfixExpression returns [EObject current=null] : (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) ;
    public final EObject ruleUnaryOrInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject this_UnaryExpression_0 = null;

        EObject this_InfixExpression_1 = null;



        	enterRule();

        try {
            // InternalExpression.g:1225:2: ( (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression ) )
            // InternalExpression.g:1226:2: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
            {
            // InternalExpression.g:1226:2: (this_UnaryExpression_0= ruleUnaryExpression | this_InfixExpression_1= ruleInfixExpression )
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
                    // InternalExpression.g:1227:3: this_UnaryExpression_0= ruleUnaryExpression
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
                    // InternalExpression.g:1236:3: this_InfixExpression_1= ruleInfixExpression
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
    // InternalExpression.g:1248:1: entryRuleUnaryExpression returns [EObject current=null] : iv_ruleUnaryExpression= ruleUnaryExpression EOF ;
    public final EObject entryRuleUnaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnaryExpression = null;


        try {
            // InternalExpression.g:1248:56: (iv_ruleUnaryExpression= ruleUnaryExpression EOF )
            // InternalExpression.g:1249:2: iv_ruleUnaryExpression= ruleUnaryExpression EOF
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
    // InternalExpression.g:1255:1: ruleUnaryExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) ;
    public final EObject ruleUnaryExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_1=null;
        Token lv_name_0_2=null;
        EObject lv_params_1_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:1261:2: ( ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) ) )
            // InternalExpression.g:1262:2: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            {
            // InternalExpression.g:1262:2: ( ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) ) )
            // InternalExpression.g:1263:3: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) ) ( (lv_params_1_0= ruleInfixExpression ) )
            {
            // InternalExpression.g:1263:3: ( ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) ) )
            // InternalExpression.g:1264:4: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            {
            // InternalExpression.g:1264:4: ( (lv_name_0_1= '!' | lv_name_0_2= '-' ) )
            // InternalExpression.g:1265:5: (lv_name_0_1= '!' | lv_name_0_2= '-' )
            {
            // InternalExpression.g:1265:5: (lv_name_0_1= '!' | lv_name_0_2= '-' )
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
                    // InternalExpression.g:1266:6: lv_name_0_1= '!'
                    {
                    lv_name_0_1=(Token)match(input,40,FOLLOW_14); if (state.failed) return current;
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
                    // InternalExpression.g:1277:6: lv_name_0_2= '-'
                    {
                    lv_name_0_2=(Token)match(input,37,FOLLOW_14); if (state.failed) return current;
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

            // InternalExpression.g:1290:3: ( (lv_params_1_0= ruleInfixExpression ) )
            // InternalExpression.g:1291:4: (lv_params_1_0= ruleInfixExpression )
            {
            // InternalExpression.g:1291:4: (lv_params_1_0= ruleInfixExpression )
            // InternalExpression.g:1292:5: lv_params_1_0= ruleInfixExpression
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
    // InternalExpression.g:1313:1: entryRuleInfixExpression returns [EObject current=null] : iv_ruleInfixExpression= ruleInfixExpression EOF ;
    public final EObject entryRuleInfixExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInfixExpression = null;


        try {
            // InternalExpression.g:1313:56: (iv_ruleInfixExpression= ruleInfixExpression EOF )
            // InternalExpression.g:1314:2: iv_ruleInfixExpression= ruleInfixExpression EOF
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
    // InternalExpression.g:1320:1: ruleInfixExpression returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) ;
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
            // InternalExpression.g:1326:2: ( (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* ) )
            // InternalExpression.g:1327:2: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            {
            // InternalExpression.g:1327:2: (this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )* )
            // InternalExpression.g:1328:3: this_PrimaryExpression_0= rulePrimaryExpression ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            {
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getInfixExpressionAccess().getPrimaryExpressionParserRuleCall_0());
              		
            }
            pushFollow(FOLLOW_24);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_PrimaryExpression_0;
              			afterParserOrEnumRuleCall();
              		
            }
            // InternalExpression.g:1336:3: ( ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' ) | ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) ) | ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' ) | ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' ) )*
            loop23:
            do {
                int alt23=5;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==41) ) {
                    switch ( input.LA(2) ) {
                    case 58:
                    case 59:
                    case 60:
                        {
                        alt23=2;
                        }
                        break;
                    case RULE_ID:
                        {
                        int LA23_4 = input.LA(3);

                        if ( (LA23_4==EOF||LA23_4==14||(LA23_4>=16 && LA23_4<=18)||(LA23_4>=20 && LA23_4<=21)||(LA23_4>=24 && LA23_4<=39)||(LA23_4>=41 && LA23_4<=42)||LA23_4==63) ) {
                            alt23=2;
                        }
                        else if ( (LA23_4==15) ) {
                            alt23=1;
                        }


                        }
                        break;
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

                    }

                }


                switch (alt23) {
            	case 1 :
            	    // InternalExpression.g:1337:4: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    {
            	    // InternalExpression.g:1337:4: ( () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')' )
            	    // InternalExpression.g:1338:5: () otherlv_2= '.' ( (lv_name_3_0= ruleIdentifier ) ) otherlv_4= '(' ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )? otherlv_8= ')'
            	    {
            	    // InternalExpression.g:1338:5: ()
            	    // InternalExpression.g:1339:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getOperationCallTargetAction_1_0_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_2=(Token)match(input,41,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_2, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_0_1());
            	      				
            	    }
            	    // InternalExpression.g:1349:5: ( (lv_name_3_0= ruleIdentifier ) )
            	    // InternalExpression.g:1350:6: (lv_name_3_0= ruleIdentifier )
            	    {
            	    // InternalExpression.g:1350:6: (lv_name_3_0= ruleIdentifier )
            	    // InternalExpression.g:1351:7: lv_name_3_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getNameIdentifierParserRuleCall_1_0_2_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_25);
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

            	    otherlv_4=(Token)match(input,15,FOLLOW_26); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_4, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_0_3());
            	      				
            	    }
            	    // InternalExpression.g:1372:5: ( ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )* )?
            	    int alt20=2;
            	    int LA20_0 = input.LA(1);

            	    if ( ((LA20_0>=RULE_INT && LA20_0<=RULE_ID)||LA20_0==12||LA20_0==15||LA20_0==19||(LA20_0>=22 && LA20_0<=23)||LA20_0==37||LA20_0==40||(LA20_0>=43 && LA20_0<=51)||(LA20_0>=53 && LA20_0<=60)) ) {
            	        alt20=1;
            	    }
            	    switch (alt20) {
            	        case 1 :
            	            // InternalExpression.g:1373:6: ( (lv_params_5_0= ruleExpression ) ) (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            {
            	            // InternalExpression.g:1373:6: ( (lv_params_5_0= ruleExpression ) )
            	            // InternalExpression.g:1374:7: (lv_params_5_0= ruleExpression )
            	            {
            	            // InternalExpression.g:1374:7: (lv_params_5_0= ruleExpression )
            	            // InternalExpression.g:1375:8: lv_params_5_0= ruleExpression
            	            {
            	            if ( state.backtracking==0 ) {

            	              								newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_0_0());
            	              							
            	            }
            	            pushFollow(FOLLOW_27);
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

            	            // InternalExpression.g:1392:6: (otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) ) )*
            	            loop19:
            	            do {
            	                int alt19=2;
            	                int LA19_0 = input.LA(1);

            	                if ( (LA19_0==42) ) {
            	                    alt19=1;
            	                }


            	                switch (alt19) {
            	            	case 1 :
            	            	    // InternalExpression.g:1393:7: otherlv_6= ',' ( (lv_params_7_0= ruleExpression ) )
            	            	    {
            	            	    otherlv_6=(Token)match(input,42,FOLLOW_5); if (state.failed) return current;
            	            	    if ( state.backtracking==0 ) {

            	            	      							newLeafNode(otherlv_6, grammarAccess.getInfixExpressionAccess().getCommaKeyword_1_0_4_1_0());
            	            	      						
            	            	    }
            	            	    // InternalExpression.g:1397:7: ( (lv_params_7_0= ruleExpression ) )
            	            	    // InternalExpression.g:1398:8: (lv_params_7_0= ruleExpression )
            	            	    {
            	            	    // InternalExpression.g:1398:8: (lv_params_7_0= ruleExpression )
            	            	    // InternalExpression.g:1399:9: lv_params_7_0= ruleExpression
            	            	    {
            	            	    if ( state.backtracking==0 ) {

            	            	      									newCompositeNode(grammarAccess.getInfixExpressionAccess().getParamsExpressionParserRuleCall_1_0_4_1_1_0());
            	            	      								
            	            	    }
            	            	    pushFollow(FOLLOW_27);
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
            	            	    break loop19;
            	                }
            	            } while (true);


            	            }
            	            break;

            	    }

            	    otherlv_8=(Token)match(input,16,FOLLOW_24); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_8, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_0_5());
            	      				
            	    }

            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalExpression.g:1424:4: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    {
            	    // InternalExpression.g:1424:4: ( () otherlv_10= '.' ( (lv_type_11_0= ruleType ) ) )
            	    // InternalExpression.g:1425:5: () otherlv_10= '.' ( (lv_type_11_0= ruleType ) )
            	    {
            	    // InternalExpression.g:1425:5: ()
            	    // InternalExpression.g:1426:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getFeatureCallTargetAction_1_1_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_10=(Token)match(input,41,FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_10, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_1_1());
            	      				
            	    }
            	    // InternalExpression.g:1436:5: ( (lv_type_11_0= ruleType ) )
            	    // InternalExpression.g:1437:6: (lv_type_11_0= ruleType )
            	    {
            	    // InternalExpression.g:1437:6: (lv_type_11_0= ruleType )
            	    // InternalExpression.g:1438:7: lv_type_11_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_1_2_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_24);
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
            	    // InternalExpression.g:1457:4: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    {
            	    // InternalExpression.g:1457:4: ( () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')' )
            	    // InternalExpression.g:1458:5: () otherlv_13= '.' ( (lv_name_14_0= 'typeSelect' ) ) otherlv_15= '(' ( (lv_type_16_0= ruleType ) ) otherlv_17= ')'
            	    {
            	    // InternalExpression.g:1458:5: ()
            	    // InternalExpression.g:1459:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getTypeSelectExpressionTargetAction_1_2_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_13=(Token)match(input,41,FOLLOW_28); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_13, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_2_1());
            	      				
            	    }
            	    // InternalExpression.g:1469:5: ( (lv_name_14_0= 'typeSelect' ) )
            	    // InternalExpression.g:1470:6: (lv_name_14_0= 'typeSelect' )
            	    {
            	    // InternalExpression.g:1470:6: (lv_name_14_0= 'typeSelect' )
            	    // InternalExpression.g:1471:7: lv_name_14_0= 'typeSelect'
            	    {
            	    lv_name_14_0=(Token)match(input,43,FOLLOW_25); if (state.failed) return current;
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

            	    otherlv_15=(Token)match(input,15,FOLLOW_7); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_15, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_2_3());
            	      				
            	    }
            	    // InternalExpression.g:1487:5: ( (lv_type_16_0= ruleType ) )
            	    // InternalExpression.g:1488:6: (lv_type_16_0= ruleType )
            	    {
            	    // InternalExpression.g:1488:6: (lv_type_16_0= ruleType )
            	    // InternalExpression.g:1489:7: lv_type_16_0= ruleType
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getTypeTypeParserRuleCall_1_2_4_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_8);
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

            	    otherlv_17=(Token)match(input,16,FOLLOW_24); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_17, grammarAccess.getInfixExpressionAccess().getRightParenthesisKeyword_1_2_5());
            	      				
            	    }

            	    }


            	    }
            	    break;
            	case 4 :
            	    // InternalExpression.g:1512:4: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    {
            	    // InternalExpression.g:1512:4: ( () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')' )
            	    // InternalExpression.g:1513:5: () otherlv_19= '.' ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) ) otherlv_21= '(' ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )? ( (lv_exp_24_0= ruleExpression ) ) otherlv_25= ')'
            	    {
            	    // InternalExpression.g:1513:5: ()
            	    // InternalExpression.g:1514:6: 
            	    {
            	    if ( state.backtracking==0 ) {

            	      						current = forceCreateModelElementAndSet(
            	      							grammarAccess.getInfixExpressionAccess().getCollectionExpressionTargetAction_1_3_0(),
            	      							current);
            	      					
            	    }

            	    }

            	    otherlv_19=(Token)match(input,41,FOLLOW_29); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_19, grammarAccess.getInfixExpressionAccess().getFullStopKeyword_1_3_1());
            	      				
            	    }
            	    // InternalExpression.g:1524:5: ( ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) ) )
            	    // InternalExpression.g:1525:6: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    {
            	    // InternalExpression.g:1525:6: ( (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' ) )
            	    // InternalExpression.g:1526:7: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
            	    {
            	    // InternalExpression.g:1526:7: (lv_name_20_1= 'collect' | lv_name_20_2= 'select' | lv_name_20_3= 'selectFirst' | lv_name_20_4= 'reject' | lv_name_20_5= 'exists' | lv_name_20_6= 'notExists' | lv_name_20_7= 'sortBy' | lv_name_20_8= 'forAll' )
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
            	            // InternalExpression.g:1527:8: lv_name_20_1= 'collect'
            	            {
            	            lv_name_20_1=(Token)match(input,44,FOLLOW_25); if (state.failed) return current;
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
            	            // InternalExpression.g:1538:8: lv_name_20_2= 'select'
            	            {
            	            lv_name_20_2=(Token)match(input,45,FOLLOW_25); if (state.failed) return current;
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
            	            // InternalExpression.g:1549:8: lv_name_20_3= 'selectFirst'
            	            {
            	            lv_name_20_3=(Token)match(input,46,FOLLOW_25); if (state.failed) return current;
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
            	            // InternalExpression.g:1560:8: lv_name_20_4= 'reject'
            	            {
            	            lv_name_20_4=(Token)match(input,47,FOLLOW_25); if (state.failed) return current;
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
            	            // InternalExpression.g:1571:8: lv_name_20_5= 'exists'
            	            {
            	            lv_name_20_5=(Token)match(input,48,FOLLOW_25); if (state.failed) return current;
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
            	            // InternalExpression.g:1582:8: lv_name_20_6= 'notExists'
            	            {
            	            lv_name_20_6=(Token)match(input,49,FOLLOW_25); if (state.failed) return current;
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
            	            // InternalExpression.g:1593:8: lv_name_20_7= 'sortBy'
            	            {
            	            lv_name_20_7=(Token)match(input,50,FOLLOW_25); if (state.failed) return current;
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
            	            // InternalExpression.g:1604:8: lv_name_20_8= 'forAll'
            	            {
            	            lv_name_20_8=(Token)match(input,51,FOLLOW_25); if (state.failed) return current;
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

            	    otherlv_21=(Token)match(input,15,FOLLOW_5); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      					newLeafNode(otherlv_21, grammarAccess.getInfixExpressionAccess().getLeftParenthesisKeyword_1_3_3());
            	      				
            	    }
            	    // InternalExpression.g:1621:5: ( ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|' )?
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
            	            // InternalExpression.g:1622:6: ( (lv_var_22_0= ruleIdentifier ) ) otherlv_23= '|'
            	            {
            	            // InternalExpression.g:1622:6: ( (lv_var_22_0= ruleIdentifier ) )
            	            // InternalExpression.g:1623:7: (lv_var_22_0= ruleIdentifier )
            	            {
            	            // InternalExpression.g:1623:7: (lv_var_22_0= ruleIdentifier )
            	            // InternalExpression.g:1624:8: lv_var_22_0= ruleIdentifier
            	            {
            	            if ( state.backtracking==0 ) {

            	              								newCompositeNode(grammarAccess.getInfixExpressionAccess().getVarIdentifierParserRuleCall_1_3_4_0_0());
            	              							
            	            }
            	            pushFollow(FOLLOW_30);
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

            	            otherlv_23=(Token)match(input,52,FOLLOW_5); if (state.failed) return current;
            	            if ( state.backtracking==0 ) {

            	              						newLeafNode(otherlv_23, grammarAccess.getInfixExpressionAccess().getVerticalLineKeyword_1_3_4_1());
            	              					
            	            }

            	            }
            	            break;

            	    }

            	    // InternalExpression.g:1646:5: ( (lv_exp_24_0= ruleExpression ) )
            	    // InternalExpression.g:1647:6: (lv_exp_24_0= ruleExpression )
            	    {
            	    // InternalExpression.g:1647:6: (lv_exp_24_0= ruleExpression )
            	    // InternalExpression.g:1648:7: lv_exp_24_0= ruleExpression
            	    {
            	    if ( state.backtracking==0 ) {

            	      							newCompositeNode(grammarAccess.getInfixExpressionAccess().getExpExpressionParserRuleCall_1_3_5_0());
            	      						
            	    }
            	    pushFollow(FOLLOW_8);
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

            	    otherlv_25=(Token)match(input,16,FOLLOW_24); if (state.failed) return current;
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
    // InternalExpression.g:1675:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // InternalExpression.g:1675:58: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // InternalExpression.g:1676:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
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
    // InternalExpression.g:1682:1: rulePrimaryExpression returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) ;
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
            // InternalExpression.g:1688:2: ( (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression ) )
            // InternalExpression.g:1689:2: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
            {
            // InternalExpression.g:1689:2: (this_Literal_0= ruleLiteral | this_FeatureCall_1= ruleFeatureCall | this_ListLiteral_2= ruleListLiteral | this_ConstructorCallExpression_3= ruleConstructorCallExpression | this_GlobalVarExpression_4= ruleGlobalVarExpression | this_ParanthesizedExpression_5= ruleParanthesizedExpression )
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
                    // InternalExpression.g:1690:3: this_Literal_0= ruleLiteral
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
                    // InternalExpression.g:1699:3: this_FeatureCall_1= ruleFeatureCall
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
                    // InternalExpression.g:1708:3: this_ListLiteral_2= ruleListLiteral
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
                    // InternalExpression.g:1717:3: this_ConstructorCallExpression_3= ruleConstructorCallExpression
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
                    // InternalExpression.g:1726:3: this_GlobalVarExpression_4= ruleGlobalVarExpression
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
                    // InternalExpression.g:1735:3: this_ParanthesizedExpression_5= ruleParanthesizedExpression
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
    // InternalExpression.g:1747:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // InternalExpression.g:1747:48: (iv_ruleLiteral= ruleLiteral EOF )
            // InternalExpression.g:1748:2: iv_ruleLiteral= ruleLiteral EOF
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
    // InternalExpression.g:1754:1: ruleLiteral returns [EObject current=null] : (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        EObject this_BooleanLiteral_0 = null;

        EObject this_IntegerLiteral_1 = null;

        EObject this_NullLiteral_2 = null;

        EObject this_RealLiteral_3 = null;

        EObject this_StringLiteral_4 = null;



        	enterRule();

        try {
            // InternalExpression.g:1760:2: ( (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral ) )
            // InternalExpression.g:1761:2: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
            {
            // InternalExpression.g:1761:2: (this_BooleanLiteral_0= ruleBooleanLiteral | this_IntegerLiteral_1= ruleIntegerLiteral | this_NullLiteral_2= ruleNullLiteral | this_RealLiteral_3= ruleRealLiteral | this_StringLiteral_4= ruleStringLiteral )
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
                    // InternalExpression.g:1762:3: this_BooleanLiteral_0= ruleBooleanLiteral
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
                    // InternalExpression.g:1771:3: this_IntegerLiteral_1= ruleIntegerLiteral
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
                    // InternalExpression.g:1780:3: this_NullLiteral_2= ruleNullLiteral
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
                    // InternalExpression.g:1789:3: this_RealLiteral_3= ruleRealLiteral
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
                    // InternalExpression.g:1798:3: this_StringLiteral_4= ruleStringLiteral
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
    // InternalExpression.g:1810:1: entryRuleBooleanLiteral returns [EObject current=null] : iv_ruleBooleanLiteral= ruleBooleanLiteral EOF ;
    public final EObject entryRuleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBooleanLiteral = null;


        try {
            // InternalExpression.g:1810:55: (iv_ruleBooleanLiteral= ruleBooleanLiteral EOF )
            // InternalExpression.g:1811:2: iv_ruleBooleanLiteral= ruleBooleanLiteral EOF
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
    // InternalExpression.g:1817:1: ruleBooleanLiteral returns [EObject current=null] : ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) ;
    public final EObject ruleBooleanLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_1=null;
        Token lv_val_0_2=null;


        	enterRule();

        try {
            // InternalExpression.g:1823:2: ( ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) ) )
            // InternalExpression.g:1824:2: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            {
            // InternalExpression.g:1824:2: ( ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) ) )
            // InternalExpression.g:1825:3: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            {
            // InternalExpression.g:1825:3: ( (lv_val_0_1= 'true' | lv_val_0_2= 'false' ) )
            // InternalExpression.g:1826:4: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
            {
            // InternalExpression.g:1826:4: (lv_val_0_1= 'true' | lv_val_0_2= 'false' )
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
                    // InternalExpression.g:1827:5: lv_val_0_1= 'true'
                    {
                    lv_val_0_1=(Token)match(input,53,FOLLOW_2); if (state.failed) return current;
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
                    // InternalExpression.g:1838:5: lv_val_0_2= 'false'
                    {
                    lv_val_0_2=(Token)match(input,54,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:1854:1: entryRuleIntegerLiteral returns [EObject current=null] : iv_ruleIntegerLiteral= ruleIntegerLiteral EOF ;
    public final EObject entryRuleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntegerLiteral = null;


        try {
            // InternalExpression.g:1854:55: (iv_ruleIntegerLiteral= ruleIntegerLiteral EOF )
            // InternalExpression.g:1855:2: iv_ruleIntegerLiteral= ruleIntegerLiteral EOF
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
    // InternalExpression.g:1861:1: ruleIntegerLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_INT ) ) ;
    public final EObject ruleIntegerLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExpression.g:1867:2: ( ( (lv_val_0_0= RULE_INT ) ) )
            // InternalExpression.g:1868:2: ( (lv_val_0_0= RULE_INT ) )
            {
            // InternalExpression.g:1868:2: ( (lv_val_0_0= RULE_INT ) )
            // InternalExpression.g:1869:3: (lv_val_0_0= RULE_INT )
            {
            // InternalExpression.g:1869:3: (lv_val_0_0= RULE_INT )
            // InternalExpression.g:1870:4: lv_val_0_0= RULE_INT
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
    // InternalExpression.g:1889:1: entryRuleNullLiteral returns [EObject current=null] : iv_ruleNullLiteral= ruleNullLiteral EOF ;
    public final EObject entryRuleNullLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNullLiteral = null;


        try {
            // InternalExpression.g:1889:52: (iv_ruleNullLiteral= ruleNullLiteral EOF )
            // InternalExpression.g:1890:2: iv_ruleNullLiteral= ruleNullLiteral EOF
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
    // InternalExpression.g:1896:1: ruleNullLiteral returns [EObject current=null] : ( (lv_val_0_0= 'null' ) ) ;
    public final EObject ruleNullLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExpression.g:1902:2: ( ( (lv_val_0_0= 'null' ) ) )
            // InternalExpression.g:1903:2: ( (lv_val_0_0= 'null' ) )
            {
            // InternalExpression.g:1903:2: ( (lv_val_0_0= 'null' ) )
            // InternalExpression.g:1904:3: (lv_val_0_0= 'null' )
            {
            // InternalExpression.g:1904:3: (lv_val_0_0= 'null' )
            // InternalExpression.g:1905:4: lv_val_0_0= 'null'
            {
            lv_val_0_0=(Token)match(input,55,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:1920:1: entryRuleRealLiteral returns [EObject current=null] : iv_ruleRealLiteral= ruleRealLiteral EOF ;
    public final EObject entryRuleRealLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRealLiteral = null;


        try {
            // InternalExpression.g:1920:52: (iv_ruleRealLiteral= ruleRealLiteral EOF )
            // InternalExpression.g:1921:2: iv_ruleRealLiteral= ruleRealLiteral EOF
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
    // InternalExpression.g:1927:1: ruleRealLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_REAL ) ) ;
    public final EObject ruleRealLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExpression.g:1933:2: ( ( (lv_val_0_0= RULE_REAL ) ) )
            // InternalExpression.g:1934:2: ( (lv_val_0_0= RULE_REAL ) )
            {
            // InternalExpression.g:1934:2: ( (lv_val_0_0= RULE_REAL ) )
            // InternalExpression.g:1935:3: (lv_val_0_0= RULE_REAL )
            {
            // InternalExpression.g:1935:3: (lv_val_0_0= RULE_REAL )
            // InternalExpression.g:1936:4: lv_val_0_0= RULE_REAL
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
    // InternalExpression.g:1955:1: entryRuleStringLiteral returns [EObject current=null] : iv_ruleStringLiteral= ruleStringLiteral EOF ;
    public final EObject entryRuleStringLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStringLiteral = null;


        try {
            // InternalExpression.g:1955:54: (iv_ruleStringLiteral= ruleStringLiteral EOF )
            // InternalExpression.g:1956:2: iv_ruleStringLiteral= ruleStringLiteral EOF
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
    // InternalExpression.g:1962:1: ruleStringLiteral returns [EObject current=null] : ( (lv_val_0_0= RULE_STRING ) ) ;
    public final EObject ruleStringLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_val_0_0=null;


        	enterRule();

        try {
            // InternalExpression.g:1968:2: ( ( (lv_val_0_0= RULE_STRING ) ) )
            // InternalExpression.g:1969:2: ( (lv_val_0_0= RULE_STRING ) )
            {
            // InternalExpression.g:1969:2: ( (lv_val_0_0= RULE_STRING ) )
            // InternalExpression.g:1970:3: (lv_val_0_0= RULE_STRING )
            {
            // InternalExpression.g:1970:3: (lv_val_0_0= RULE_STRING )
            // InternalExpression.g:1971:4: lv_val_0_0= RULE_STRING
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
    // InternalExpression.g:1990:1: entryRuleParanthesizedExpression returns [EObject current=null] : iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF ;
    public final EObject entryRuleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParanthesizedExpression = null;


        try {
            // InternalExpression.g:1990:64: (iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF )
            // InternalExpression.g:1991:2: iv_ruleParanthesizedExpression= ruleParanthesizedExpression EOF
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
    // InternalExpression.g:1997:1: ruleParanthesizedExpression returns [EObject current=null] : (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) ;
    public final EObject ruleParanthesizedExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;



        	enterRule();

        try {
            // InternalExpression.g:2003:2: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) )
            // InternalExpression.g:2004:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            {
            // InternalExpression.g:2004:2: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
            // InternalExpression.g:2005:3: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getParanthesizedExpressionAccess().getLeftParenthesisKeyword_0());
              		
            }
            if ( state.backtracking==0 ) {

              			newCompositeNode(grammarAccess.getParanthesizedExpressionAccess().getExpressionParserRuleCall_1());
              		
            }
            pushFollow(FOLLOW_8);
            this_Expression_1=ruleExpression();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			current = this_Expression_1;
              			afterParserOrEnumRuleCall();
              		
            }
            otherlv_2=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:2025:1: entryRuleGlobalVarExpression returns [EObject current=null] : iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF ;
    public final EObject entryRuleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVarExpression = null;


        try {
            // InternalExpression.g:2025:60: (iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF )
            // InternalExpression.g:2026:2: iv_ruleGlobalVarExpression= ruleGlobalVarExpression EOF
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
    // InternalExpression.g:2032:1: ruleGlobalVarExpression returns [EObject current=null] : (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleGlobalVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:2038:2: ( (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) ) )
            // InternalExpression.g:2039:2: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            {
            // InternalExpression.g:2039:2: (otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) ) )
            // InternalExpression.g:2040:3: otherlv_0= 'GLOBALVAR' ( (lv_name_1_0= ruleIdentifier ) )
            {
            otherlv_0=(Token)match(input,56,FOLLOW_3); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getGlobalVarExpressionAccess().getGLOBALVARKeyword_0());
              		
            }
            // InternalExpression.g:2044:3: ( (lv_name_1_0= ruleIdentifier ) )
            // InternalExpression.g:2045:4: (lv_name_1_0= ruleIdentifier )
            {
            // InternalExpression.g:2045:4: (lv_name_1_0= ruleIdentifier )
            // InternalExpression.g:2046:5: lv_name_1_0= ruleIdentifier
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
    // InternalExpression.g:2067:1: entryRuleFeatureCall returns [EObject current=null] : iv_ruleFeatureCall= ruleFeatureCall EOF ;
    public final EObject entryRuleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeatureCall = null;


        try {
            // InternalExpression.g:2067:52: (iv_ruleFeatureCall= ruleFeatureCall EOF )
            // InternalExpression.g:2068:2: iv_ruleFeatureCall= ruleFeatureCall EOF
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
    // InternalExpression.g:2074:1: ruleFeatureCall returns [EObject current=null] : (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) ;
    public final EObject ruleFeatureCall() throws RecognitionException {
        EObject current = null;

        EObject this_OperationCall_0 = null;

        EObject lv_type_1_0 = null;

        EObject this_CollectionExpression_2 = null;

        EObject this_TypeSelectExpression_3 = null;



        	enterRule();

        try {
            // InternalExpression.g:2080:2: ( (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression ) )
            // InternalExpression.g:2081:2: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
            {
            // InternalExpression.g:2081:2: (this_OperationCall_0= ruleOperationCall | ( (lv_type_1_0= ruleType ) ) | this_CollectionExpression_2= ruleCollectionExpression | this_TypeSelectExpression_3= ruleTypeSelectExpression )
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
                    // InternalExpression.g:2082:3: this_OperationCall_0= ruleOperationCall
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
                    // InternalExpression.g:2091:3: ( (lv_type_1_0= ruleType ) )
                    {
                    // InternalExpression.g:2091:3: ( (lv_type_1_0= ruleType ) )
                    // InternalExpression.g:2092:4: (lv_type_1_0= ruleType )
                    {
                    // InternalExpression.g:2092:4: (lv_type_1_0= ruleType )
                    // InternalExpression.g:2093:5: lv_type_1_0= ruleType
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
                    // InternalExpression.g:2111:3: this_CollectionExpression_2= ruleCollectionExpression
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
                    // InternalExpression.g:2120:3: this_TypeSelectExpression_3= ruleTypeSelectExpression
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
    // InternalExpression.g:2132:1: entryRuleOperationCall returns [EObject current=null] : iv_ruleOperationCall= ruleOperationCall EOF ;
    public final EObject entryRuleOperationCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationCall = null;


        try {
            // InternalExpression.g:2132:54: (iv_ruleOperationCall= ruleOperationCall EOF )
            // InternalExpression.g:2133:2: iv_ruleOperationCall= ruleOperationCall EOF
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
    // InternalExpression.g:2139:1: ruleOperationCall returns [EObject current=null] : ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
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
            // InternalExpression.g:2145:2: ( ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // InternalExpression.g:2146:2: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // InternalExpression.g:2146:2: ( ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // InternalExpression.g:2147:3: ( (lv_name_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // InternalExpression.g:2147:3: ( (lv_name_0_0= ruleIdentifier ) )
            // InternalExpression.g:2148:4: (lv_name_0_0= ruleIdentifier )
            {
            // InternalExpression.g:2148:4: (lv_name_0_0= ruleIdentifier )
            // InternalExpression.g:2149:5: lv_name_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getOperationCallAccess().getNameIdentifierParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_25);
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

            otherlv_1=(Token)match(input,15,FOLLOW_26); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getOperationCallAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalExpression.g:2170:3: ( ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_INT && LA29_0<=RULE_ID)||LA29_0==12||LA29_0==15||LA29_0==19||(LA29_0>=22 && LA29_0<=23)||LA29_0==37||LA29_0==40||(LA29_0>=43 && LA29_0<=51)||(LA29_0>=53 && LA29_0<=60)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalExpression.g:2171:4: ( (lv_params_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    {
                    // InternalExpression.g:2171:4: ( (lv_params_2_0= ruleExpression ) )
                    // InternalExpression.g:2172:5: (lv_params_2_0= ruleExpression )
                    {
                    // InternalExpression.g:2172:5: (lv_params_2_0= ruleExpression )
                    // InternalExpression.g:2173:6: lv_params_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_27);
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

                    // InternalExpression.g:2190:4: (otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==42) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // InternalExpression.g:2191:5: otherlv_3= ',' ( (lv_params_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,42,FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getOperationCallAccess().getCommaKeyword_2_1_0());
                    	      				
                    	    }
                    	    // InternalExpression.g:2195:5: ( (lv_params_4_0= ruleExpression ) )
                    	    // InternalExpression.g:2196:6: (lv_params_4_0= ruleExpression )
                    	    {
                    	    // InternalExpression.g:2196:6: (lv_params_4_0= ruleExpression )
                    	    // InternalExpression.g:2197:7: lv_params_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getOperationCallAccess().getParamsExpressionParserRuleCall_2_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_27);
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
                    	    break loop28;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:2224:1: entryRuleListLiteral returns [EObject current=null] : iv_ruleListLiteral= ruleListLiteral EOF ;
    public final EObject entryRuleListLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleListLiteral = null;


        try {
            // InternalExpression.g:2224:52: (iv_ruleListLiteral= ruleListLiteral EOF )
            // InternalExpression.g:2225:2: iv_ruleListLiteral= ruleListLiteral EOF
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
    // InternalExpression.g:2231:1: ruleListLiteral returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleListLiteral() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_elements_2_0 = null;

        EObject lv_elements_4_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:2237:2: ( ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' ) )
            // InternalExpression.g:2238:2: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            {
            // InternalExpression.g:2238:2: ( () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}' )
            // InternalExpression.g:2239:3: () otherlv_1= '{' ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )? otherlv_5= '}'
            {
            // InternalExpression.g:2239:3: ()
            // InternalExpression.g:2240:4: 
            {
            if ( state.backtracking==0 ) {

              				current = forceCreateModelElement(
              					grammarAccess.getListLiteralAccess().getListLiteralAction_0(),
              					current);
              			
            }

            }

            otherlv_1=(Token)match(input,23,FOLLOW_31); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getListLiteralAccess().getLeftCurlyBracketKeyword_1());
              		
            }
            // InternalExpression.g:2250:3: ( ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )* )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_INT && LA31_0<=RULE_ID)||LA31_0==12||LA31_0==15||LA31_0==19||(LA31_0>=22 && LA31_0<=23)||LA31_0==37||LA31_0==40||(LA31_0>=43 && LA31_0<=51)||(LA31_0>=53 && LA31_0<=60)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalExpression.g:2251:4: ( (lv_elements_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    {
                    // InternalExpression.g:2251:4: ( (lv_elements_2_0= ruleExpression ) )
                    // InternalExpression.g:2252:5: (lv_elements_2_0= ruleExpression )
                    {
                    // InternalExpression.g:2252:5: (lv_elements_2_0= ruleExpression )
                    // InternalExpression.g:2253:6: lv_elements_2_0= ruleExpression
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_32);
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

                    // InternalExpression.g:2270:4: (otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==42) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // InternalExpression.g:2271:5: otherlv_3= ',' ( (lv_elements_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,42,FOLLOW_5); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      					newLeafNode(otherlv_3, grammarAccess.getListLiteralAccess().getCommaKeyword_2_1_0());
                    	      				
                    	    }
                    	    // InternalExpression.g:2275:5: ( (lv_elements_4_0= ruleExpression ) )
                    	    // InternalExpression.g:2276:6: (lv_elements_4_0= ruleExpression )
                    	    {
                    	    // InternalExpression.g:2276:6: (lv_elements_4_0= ruleExpression )
                    	    // InternalExpression.g:2277:7: lv_elements_4_0= ruleExpression
                    	    {
                    	    if ( state.backtracking==0 ) {

                    	      							newCompositeNode(grammarAccess.getListLiteralAccess().getElementsExpressionParserRuleCall_2_1_1_0());
                    	      						
                    	    }
                    	    pushFollow(FOLLOW_32);
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
                    	    break loop30;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,25,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:2304:1: entryRuleConstructorCallExpression returns [EObject current=null] : iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF ;
    public final EObject entryRuleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstructorCallExpression = null;


        try {
            // InternalExpression.g:2304:66: (iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF )
            // InternalExpression.g:2305:2: iv_ruleConstructorCallExpression= ruleConstructorCallExpression EOF
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
    // InternalExpression.g:2311:1: ruleConstructorCallExpression returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) ;
    public final EObject ruleConstructorCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_type_1_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:2317:2: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) ) )
            // InternalExpression.g:2318:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            {
            // InternalExpression.g:2318:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) ) )
            // InternalExpression.g:2319:3: otherlv_0= 'new' ( (lv_type_1_0= ruleSimpleType ) )
            {
            otherlv_0=(Token)match(input,57,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_0, grammarAccess.getConstructorCallExpressionAccess().getNewKeyword_0());
              		
            }
            // InternalExpression.g:2323:3: ( (lv_type_1_0= ruleSimpleType ) )
            // InternalExpression.g:2324:4: (lv_type_1_0= ruleSimpleType )
            {
            // InternalExpression.g:2324:4: (lv_type_1_0= ruleSimpleType )
            // InternalExpression.g:2325:5: lv_type_1_0= ruleSimpleType
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
    // InternalExpression.g:2346:1: entryRuleTypeSelectExpression returns [EObject current=null] : iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF ;
    public final EObject entryRuleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTypeSelectExpression = null;


        try {
            // InternalExpression.g:2346:61: (iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF )
            // InternalExpression.g:2347:2: iv_ruleTypeSelectExpression= ruleTypeSelectExpression EOF
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
    // InternalExpression.g:2353:1: ruleTypeSelectExpression returns [EObject current=null] : ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) ;
    public final EObject ruleTypeSelectExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_type_2_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:2359:2: ( ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' ) )
            // InternalExpression.g:2360:2: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            {
            // InternalExpression.g:2360:2: ( ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')' )
            // InternalExpression.g:2361:3: ( (lv_name_0_0= 'typeSelect' ) ) otherlv_1= '(' ( (lv_type_2_0= ruleType ) ) otherlv_3= ')'
            {
            // InternalExpression.g:2361:3: ( (lv_name_0_0= 'typeSelect' ) )
            // InternalExpression.g:2362:4: (lv_name_0_0= 'typeSelect' )
            {
            // InternalExpression.g:2362:4: (lv_name_0_0= 'typeSelect' )
            // InternalExpression.g:2363:5: lv_name_0_0= 'typeSelect'
            {
            lv_name_0_0=(Token)match(input,43,FOLLOW_25); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,15,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getTypeSelectExpressionAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalExpression.g:2379:3: ( (lv_type_2_0= ruleType ) )
            // InternalExpression.g:2380:4: (lv_type_2_0= ruleType )
            {
            // InternalExpression.g:2380:4: (lv_type_2_0= ruleType )
            // InternalExpression.g:2381:5: lv_type_2_0= ruleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getTypeSelectExpressionAccess().getTypeTypeParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_8);
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

            otherlv_3=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:2406:1: entryRuleCollectionExpression returns [EObject current=null] : iv_ruleCollectionExpression= ruleCollectionExpression EOF ;
    public final EObject entryRuleCollectionExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionExpression = null;


        try {
            // InternalExpression.g:2406:61: (iv_ruleCollectionExpression= ruleCollectionExpression EOF )
            // InternalExpression.g:2407:2: iv_ruleCollectionExpression= ruleCollectionExpression EOF
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
    // InternalExpression.g:2413:1: ruleCollectionExpression returns [EObject current=null] : ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
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
            // InternalExpression.g:2419:2: ( ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // InternalExpression.g:2420:2: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // InternalExpression.g:2420:2: ( ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')' )
            // InternalExpression.g:2421:3: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) ) otherlv_1= '(' ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )? ( (lv_exp_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            // InternalExpression.g:2421:3: ( ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) ) )
            // InternalExpression.g:2422:4: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            {
            // InternalExpression.g:2422:4: ( (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' ) )
            // InternalExpression.g:2423:5: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
            {
            // InternalExpression.g:2423:5: (lv_name_0_1= 'collect' | lv_name_0_2= 'select' | lv_name_0_3= 'selectFirst' | lv_name_0_4= 'reject' | lv_name_0_5= 'exists' | lv_name_0_6= 'notExists' | lv_name_0_7= 'sortBy' | lv_name_0_8= 'forAll' )
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
                    // InternalExpression.g:2424:6: lv_name_0_1= 'collect'
                    {
                    lv_name_0_1=(Token)match(input,44,FOLLOW_25); if (state.failed) return current;
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
                    // InternalExpression.g:2435:6: lv_name_0_2= 'select'
                    {
                    lv_name_0_2=(Token)match(input,45,FOLLOW_25); if (state.failed) return current;
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
                    // InternalExpression.g:2446:6: lv_name_0_3= 'selectFirst'
                    {
                    lv_name_0_3=(Token)match(input,46,FOLLOW_25); if (state.failed) return current;
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
                    // InternalExpression.g:2457:6: lv_name_0_4= 'reject'
                    {
                    lv_name_0_4=(Token)match(input,47,FOLLOW_25); if (state.failed) return current;
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
                    // InternalExpression.g:2468:6: lv_name_0_5= 'exists'
                    {
                    lv_name_0_5=(Token)match(input,48,FOLLOW_25); if (state.failed) return current;
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
                    // InternalExpression.g:2479:6: lv_name_0_6= 'notExists'
                    {
                    lv_name_0_6=(Token)match(input,49,FOLLOW_25); if (state.failed) return current;
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
                    // InternalExpression.g:2490:6: lv_name_0_7= 'sortBy'
                    {
                    lv_name_0_7=(Token)match(input,50,FOLLOW_25); if (state.failed) return current;
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
                    // InternalExpression.g:2501:6: lv_name_0_8= 'forAll'
                    {
                    lv_name_0_8=(Token)match(input,51,FOLLOW_25); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,15,FOLLOW_5); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionExpressionAccess().getLeftParenthesisKeyword_1());
              		
            }
            // InternalExpression.g:2518:3: ( ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|' )?
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
                    // InternalExpression.g:2519:4: ( (lv_var_2_0= ruleIdentifier ) ) otherlv_3= '|'
                    {
                    // InternalExpression.g:2519:4: ( (lv_var_2_0= ruleIdentifier ) )
                    // InternalExpression.g:2520:5: (lv_var_2_0= ruleIdentifier )
                    {
                    // InternalExpression.g:2520:5: (lv_var_2_0= ruleIdentifier )
                    // InternalExpression.g:2521:6: lv_var_2_0= ruleIdentifier
                    {
                    if ( state.backtracking==0 ) {

                      						newCompositeNode(grammarAccess.getCollectionExpressionAccess().getVarIdentifierParserRuleCall_2_0_0());
                      					
                    }
                    pushFollow(FOLLOW_30);
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

                    otherlv_3=(Token)match(input,52,FOLLOW_5); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      				newLeafNode(otherlv_3, grammarAccess.getCollectionExpressionAccess().getVerticalLineKeyword_2_1());
                      			
                    }

                    }
                    break;

            }

            // InternalExpression.g:2543:3: ( (lv_exp_4_0= ruleExpression ) )
            // InternalExpression.g:2544:4: (lv_exp_4_0= ruleExpression )
            {
            // InternalExpression.g:2544:4: (lv_exp_4_0= ruleExpression )
            // InternalExpression.g:2545:5: lv_exp_4_0= ruleExpression
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCollectionExpressionAccess().getExpExpressionParserRuleCall_3_0());
              				
            }
            pushFollow(FOLLOW_8);
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

            otherlv_5=(Token)match(input,16,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:2570:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalExpression.g:2570:45: (iv_ruleType= ruleType EOF )
            // InternalExpression.g:2571:2: iv_ruleType= ruleType EOF
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
    // InternalExpression.g:2577:1: ruleType returns [EObject current=null] : (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_CollectionType_0 = null;

        EObject this_SimpleType_1 = null;



        	enterRule();

        try {
            // InternalExpression.g:2583:2: ( (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType ) )
            // InternalExpression.g:2584:2: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
            {
            // InternalExpression.g:2584:2: (this_CollectionType_0= ruleCollectionType | this_SimpleType_1= ruleSimpleType )
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
                    // InternalExpression.g:2585:3: this_CollectionType_0= ruleCollectionType
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
                    // InternalExpression.g:2594:3: this_SimpleType_1= ruleSimpleType
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
    // InternalExpression.g:2606:1: entryRuleCollectionType returns [EObject current=null] : iv_ruleCollectionType= ruleCollectionType EOF ;
    public final EObject entryRuleCollectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCollectionType = null;


        try {
            // InternalExpression.g:2606:55: (iv_ruleCollectionType= ruleCollectionType EOF )
            // InternalExpression.g:2607:2: iv_ruleCollectionType= ruleCollectionType EOF
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
    // InternalExpression.g:2613:1: ruleCollectionType returns [EObject current=null] : ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) ;
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
            // InternalExpression.g:2619:2: ( ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' ) )
            // InternalExpression.g:2620:2: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            {
            // InternalExpression.g:2620:2: ( ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']' )
            // InternalExpression.g:2621:3: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) ) otherlv_1= '[' ( (lv_id1_2_0= ruleSimpleType ) ) otherlv_3= ']'
            {
            // InternalExpression.g:2621:3: ( ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) ) )
            // InternalExpression.g:2622:4: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            {
            // InternalExpression.g:2622:4: ( (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' ) )
            // InternalExpression.g:2623:5: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
            {
            // InternalExpression.g:2623:5: (lv_cl_0_1= 'Collection' | lv_cl_0_2= 'List' | lv_cl_0_3= 'Set' )
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
                    // InternalExpression.g:2624:6: lv_cl_0_1= 'Collection'
                    {
                    lv_cl_0_1=(Token)match(input,58,FOLLOW_33); if (state.failed) return current;
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
                    // InternalExpression.g:2635:6: lv_cl_0_2= 'List'
                    {
                    lv_cl_0_2=(Token)match(input,59,FOLLOW_33); if (state.failed) return current;
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
                    // InternalExpression.g:2646:6: lv_cl_0_3= 'Set'
                    {
                    lv_cl_0_3=(Token)match(input,60,FOLLOW_33); if (state.failed) return current;
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

            otherlv_1=(Token)match(input,61,FOLLOW_7); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              			newLeafNode(otherlv_1, grammarAccess.getCollectionTypeAccess().getLeftSquareBracketKeyword_1());
              		
            }
            // InternalExpression.g:2663:3: ( (lv_id1_2_0= ruleSimpleType ) )
            // InternalExpression.g:2664:4: (lv_id1_2_0= ruleSimpleType )
            {
            // InternalExpression.g:2664:4: (lv_id1_2_0= ruleSimpleType )
            // InternalExpression.g:2665:5: lv_id1_2_0= ruleSimpleType
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getCollectionTypeAccess().getId1SimpleTypeParserRuleCall_2_0());
              				
            }
            pushFollow(FOLLOW_34);
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

            otherlv_3=(Token)match(input,62,FOLLOW_2); if (state.failed) return current;
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
    // InternalExpression.g:2690:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // InternalExpression.g:2690:51: (iv_ruleSimpleType= ruleSimpleType EOF )
            // InternalExpression.g:2691:2: iv_ruleSimpleType= ruleSimpleType EOF
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
    // InternalExpression.g:2697:1: ruleSimpleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_id_0_0 = null;

        AntlrDatatypeRuleToken lv_id_2_0 = null;



        	enterRule();

        try {
            // InternalExpression.g:2703:2: ( ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* ) )
            // InternalExpression.g:2704:2: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            {
            // InternalExpression.g:2704:2: ( ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )* )
            // InternalExpression.g:2705:3: ( (lv_id_0_0= ruleIdentifier ) ) (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            {
            // InternalExpression.g:2705:3: ( (lv_id_0_0= ruleIdentifier ) )
            // InternalExpression.g:2706:4: (lv_id_0_0= ruleIdentifier )
            {
            // InternalExpression.g:2706:4: (lv_id_0_0= ruleIdentifier )
            // InternalExpression.g:2707:5: lv_id_0_0= ruleIdentifier
            {
            if ( state.backtracking==0 ) {

              					newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_0_0());
              				
            }
            pushFollow(FOLLOW_35);
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

            // InternalExpression.g:2724:3: (otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==63) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalExpression.g:2725:4: otherlv_1= '::' ( (lv_id_2_0= ruleIdentifier ) )
            	    {
            	    otherlv_1=(Token)match(input,63,FOLLOW_3); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      				newLeafNode(otherlv_1, grammarAccess.getSimpleTypeAccess().getColonColonKeyword_1_0());
            	      			
            	    }
            	    // InternalExpression.g:2729:4: ( (lv_id_2_0= ruleIdentifier ) )
            	    // InternalExpression.g:2730:5: (lv_id_2_0= ruleIdentifier )
            	    {
            	    // InternalExpression.g:2730:5: (lv_id_2_0= ruleIdentifier )
            	    // InternalExpression.g:2731:6: lv_id_2_0= ruleIdentifier
            	    {
            	    if ( state.backtracking==0 ) {

            	      						newCompositeNode(grammarAccess.getSimpleTypeAccess().getIdIdentifierParserRuleCall_1_1_0());
            	      					
            	    }
            	    pushFollow(FOLLOW_35);
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
    // InternalExpression.g:2753:1: entryRuleIdentifier returns [String current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final String entryRuleIdentifier() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleIdentifier = null;


        try {
            // InternalExpression.g:2753:50: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalExpression.g:2754:2: iv_ruleIdentifier= ruleIdentifier EOF
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
    // InternalExpression.g:2760:1: ruleIdentifier returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_ID_0= RULE_ID ;
    public final AntlrDatatypeRuleToken ruleIdentifier() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;


        	enterRule();

        try {
            // InternalExpression.g:2766:2: (this_ID_0= RULE_ID )
            // InternalExpression.g:2767:2: this_ID_0= RULE_ID
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

    // $ANTLR start synpred1_InternalExpression
    public final void synpred1_InternalExpression_fragment() throws RecognitionException {   
        // InternalExpression.g:89:4: ( ruleCastedExpression )
        // InternalExpression.g:89:5: ruleCastedExpression
        {
        pushFollow(FOLLOW_2);
        ruleCastedExpression();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_InternalExpression

    // $ANTLR start synpred2_InternalExpression
    public final void synpred2_InternalExpression_fragment() throws RecognitionException {   
        // InternalExpression.g:513:4: ( 'else' )
        // InternalExpression.g:513:5: 'else'
        {
        match(input,21,FOLLOW_2); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_InternalExpression

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
    public final boolean synpred1_InternalExpression() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_InternalExpression_fragment(); // can never throw exception
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
    static final String dfa_1s = "\36\uffff";
    static final String dfa_2s = "\1\4\1\uffff\1\0\33\uffff";
    static final String dfa_3s = "\1\74\1\uffff\1\0\33\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\uffff\1\3\31\uffff\1\2";
    static final String dfa_5s = "\2\uffff\1\0\33\uffff}>";
    static final String[] dfa_6s = {
            "\4\3\4\uffff\1\1\2\uffff\1\2\3\uffff\1\3\2\uffff\2\3\15\uffff\1\3\2\uffff\1\3\2\uffff\11\3\1\uffff\10\3",
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

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "78:2: (this_LetExpression_0= ruleLetExpression | ( ( ruleCastedExpression )=>this_CastedExpression_1= ruleCastedExpression ) | this_ChainExpression_2= ruleChainExpression )";
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
                        if ( (synpred1_InternalExpression()) ) {s = 29;}

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
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x1FEFF92000C890F0L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x1C00000000000080L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000808000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x1FEFF920008080F0L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000005000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000FC0000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x1FEFF92000C990F0L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000040000010000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x000FF00000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0010000000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x1FEFF92002C890F0L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000040002000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x8000000000000002L});

}